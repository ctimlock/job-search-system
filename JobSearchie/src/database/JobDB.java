package database;

import entities.Job;

import java.sql.*;
import java.util.ArrayList;

import static database.JobDB.Column.*;
import static database.Parser.parseJob;

public class JobDB implements DBHelper {
    public static final String NAME = "job";
    /**
     * Prepared statement that will insert a Job into the job table.
     */
    private final PreparedStatement insertJob;
    /**
     * Prepared statement which will query a job and all it's atributes.
     */
    private final PreparedStatement queryJobById;
    private final PreparedStatement queryAllJobs;

    public JobDB(Connection conn) throws SQLException {
        insertJob = conn.prepareStatement(Insert.JOB, Statement.RETURN_GENERATED_KEYS);
        queryJobById = conn.prepareStatement(Query.JOB_BY_ID);
        queryAllJobs = conn.prepareStatement(Query.ALL_JOBS);
    }

    /**
     * Closes all prepared statements.
     * @throws SQLException Throws an SQLException if a prepared statement is unable to be closed.
     */
    @Override
    public void close() throws SQLException {
        if (insertJob != null)
            insertJob.close();
        if (queryJobById != null)
            queryJobById.close();
        if (queryAllJobs != null)
            queryAllJobs.close();
    }


    public Job getJob(int jobId, UserDB userDB, LocationDB locationDB, JobKeywordDB jobKeywordDB, JobCategoryDB jobCategoryDB) {
        try {
            queryJobById.setInt(1, jobId);
            ResultSet result = queryJobById.executeQuery();
            if (result.next()) {
                Job job = parseJob(result, userDB, locationDB);
                if (job != null) {
                    job.setKeywords(jobKeywordDB.getJobKeywords(job.getId()));
                    job.setCategories(jobCategoryDB.getJobCategories(job.getId()));
                    return job;
                }
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error querying jobId = " + jobId + ": " + e.getMessage());
            return null;
        }
    }

    public ArrayList<Job> getAllJobs(UserDB userDB, LocationDB locationDB) {
        try {
            ArrayList<Job> jobs = new ArrayList<>();
            ResultSet results = queryAllJobs.executeQuery();
            while (results.next()) {
                jobs.add(Parser.parseJob(results, userDB, locationDB));
            }
            return jobs;
        } catch (SQLException e) {
            System.out.println("Error querying all jobs: " + e.getMessage());
            return null;
        }
    }

    /**
     * Inserts a job into the database. If any of the job foreign keys don't exist such as location, them location
     * will be inserted into the database.
     * @param job The job to be inserted into the database.
     * @param locationDB LocationBD helper class used to insert or get job location.
     * @param jobKeywordDB JobKeywordDB helper class used to insert job keywords.
     * @param jobCategoryDB JobCategoryDB helper class used to insert job categories.
     * @return Returns the job that has been inserted into the database.
     * @throws SQLException Throws an SQLException if job cannot be inserted.
     */
    public Job insertJob(Job job , LocationDB locationDB, JobKeywordDB jobKeywordDB, JobCategoryDB jobCategoryDB) throws SQLException {
        if (job.getId()!= -1) {
            return job;
        } else {
            job.setLocation(locationDB.insertLocation(job.getLocation()));

            insertJob.setString(1, job.getJobTitle());
            insertJob.setString(2, job.getAuthor().getEmail());
            insertJob.setDate(3, job.getDateCreated());
            insertJob.setDate(4, job.getDateListed());
            insertJob.setDate(5, job.getDateDeListed());
            insertJob.setString(6, job.getCompany());
            insertJob.setInt(7, job.getLocation().getId());
            insertJob.setString(8, job.getWorkType());
            insertJob.setString(9, job.getWorkingArrangement());
            insertJob.setInt(10, job.getCompensation());
            insertJob.setString(11, job.getJobLevel());
            insertJob.setString(12, job.getDescription());
            insertJob.setBoolean(13, job.getIsAdvertised());
            int affectedRows = insertJob.executeUpdate();
            if (affectedRows != 1)
                throw new SQLException("Error inserting Job Seeker");
            ResultSet generatedKey = insertJob.getGeneratedKeys();
            if(generatedKey.next()) {
                job.setId(generatedKey.getInt(1));
                jobKeywordDB.insertJobKeywords(job);
                jobCategoryDB.insertJobCategories(job);
                return job;
            }
            else
                throw new SQLException("Could not get inserted job Id");
            }
    }

    public static class View {}

    public static class Column {
        public static final String ID = "id";
        public static final String JOBTITLE = "jobTitle";
        public static final String RECRUITEREMAIL = "recruiterEmail";
        public static final String DATECREATED = "dateCreated";
        public static final String DATELISTED = "dateListed";
        public static final String DATEDELISTED = "dateDelisted";
        public static final String COMPANYNAME = "companyName";
        public static final String LOCATIONID = "locationId";
        public static final String WORKTYPE = "workType";
        public static final String WORKINGARRANGEMENT = "workingArrangement";
        public static final String COMPENSATION = "compensation";
        public static final String JOBLEVEL = "jobLevel";
        public static final String DESCRIPTION = "description";
        public static final String ISADVERTISED = "isAdvertised";
    }

    public static class Query {
        public static final String JOB_BY_ID = "SELECT * FROM " + NAME + " WHERE " + ID + " = ?";
        public static final String ALL_JOBS = "SELECT * FROM " + NAME;
    }

    public static class Insert {
        public static final String JOB = "INSERT INTO " + NAME + " (" + JOBTITLE + ", " + RECRUITEREMAIL + ", " + DATECREATED + ", " + DATELISTED + ", " + DATEDELISTED + ", " + COMPANYNAME + ", " + LOCATIONID + ", " + WORKTYPE + ", " + WORKINGARRANGEMENT + ", " + COMPENSATION + ", " + JOBLEVEL + ", " + DESCRIPTION + ", " + ISADVERTISED + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    public static class Update {}

    public static class Delete {}
}
