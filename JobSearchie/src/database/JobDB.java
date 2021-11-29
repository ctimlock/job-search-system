package database;

import entities.Job;

import java.sql.*;

import static database.JobDB.Column.*;
import static database.Parser.parseJob;

public class JobDB implements DBHelper {
    public static final String NAME = "job";
    /**
     * Prepared statement that will insert a Job into the job table.
     *
     * @see JobDB.Insert
     */
    private PreparedStatement insertJob;
    private PreparedStatement queryJob;

    @Override
    public void close() throws SQLException {
        if (insertJob != null)
            insertJob.close();
        if (queryJob != null)
            queryJob.close();
    }

    @Override
    public void open(Connection conn) throws SQLException {
        insertJob = conn.prepareStatement(JobDB.Insert.JOB, Statement.RETURN_GENERATED_KEYS);
        queryJob = conn.prepareStatement(JobDB.Query.JOB, Statement.RETURN_GENERATED_KEYS);
    }

    public Job getJob(int jobId) {
        try {
            queryJob.setInt(1, jobId);
            ResultSet result = queryJob.executeQuery();
            if (result.next())
                return parseJob(result);
            else
                return null;
        } catch (SQLException e) {
            System.out.println("Error querying jobId = " + jobId + ": " + e.getMessage());
            return null;
        }
    }

    public int insertJob(Job job) throws SQLException {
        if (getJob(job.getId()) != null)
            throw new SQLException("Job already exists in database.");
        else {
            insertJob.setString(1, job.getJobTitle());
            insertJob.setString(2, job.getAuthor().getEmail());
            insertJob.setString(3, dateFormat.format(job.getDateCreated()));
            insertJob.setString(4, dateFormat.format(job.getDateListed()));
            insertJob.setString(5, dateFormat.format(job.getDateDeListed()));
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
            if(generatedKey.next())
                return generatedKey.getInt(1);
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
        public static final String JOB = "SELECT * FROM " + NAME + " WHERE " + ID + " = ?";
    }

    public static class Insert {
        public static final String JOB = "INSERT INTO " + NAME + " (" + JOBTITLE + ", " + RECRUITEREMAIL + ", " + DATECREATED + ", " + DATELISTED + ", " + DATEDELISTED + ", " + COMPANYNAME + ", " + LOCATIONID + ", " + WORKTYPE + ", " + WORKINGARRANGEMENT + ", " + COMPENSATION + ", " + JOBLEVEL + ", " + DESCRIPTION + ", " + ISADVERTISED + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    public static class Update {}

    public static class Delete {}
}
