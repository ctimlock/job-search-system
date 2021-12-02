package database;

import entities.Application;
import entities.Job;

import java.sql.*;
import java.util.ArrayList;

import static database.ApplicationDB.Column.*;
import static database.Parser.parseApplication;


public class ApplicationDB implements DBHelper {
    public static final String NAME = "application";
    /**
     * Prepared statement that will insert a Application into the application table.
     *
     * @see ApplicationDB.Insert
     */
    private final PreparedStatement insertApplication;
    private final PreparedStatement queryApplication;
    private final PreparedStatement queryApplicationByJob;

    public ApplicationDB(Connection conn) throws SQLException {
        insertApplication = conn.prepareStatement(ApplicationDB.Insert.APPLICATION, Statement.RETURN_GENERATED_KEYS);
        queryApplication = conn.prepareStatement(ApplicationDB.Query.APPLICATION, Statement.RETURN_GENERATED_KEYS);
        queryApplicationByJob = conn.prepareStatement(Query.APPLICATION_BY_JOB);
    }

    @Override
    public void close() throws SQLException {
        if (insertApplication != null)
            insertApplication.close();
        if (queryApplication != null)
            queryApplication.close();
        if (queryApplicationByJob != null)
            queryApplicationByJob.close();
    }

    public Application getApplication(int applicationId, UserDB userDB, UserKeywordDB userKeywordDB, LocationDB locationDB, JobDB jobDB, JobKeywordDB jobKeywordDB, JobCategoryDB jobCategoryDB) {
        try {
            queryApplication.setInt(1, applicationId);
            ResultSet result = queryApplication.executeQuery();
            if (result.next()) {
                return parseApplication(result, userDB, userKeywordDB, locationDB, jobDB, jobKeywordDB, jobCategoryDB);
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error querying applicationId = " + applicationId + ": " + e.getMessage());
            return null;
        }
    }

    public Application insertApplication(Application application) throws SQLException {
        if (application.getId()!= -1)
            return application;
        else {
            insertApplication.setInt(1, application.getJob().getId());
            insertApplication.setString(2, application.getJobSeeker().getEmail());
            insertApplication.setString(3, application.getCoverLetterDir());
            insertApplication.setString(4, application.getResumeDir());
            insertApplication.setString(5, application.getStatus());
            insertApplication.setDate(6, application.getApplicationDate());
            int affectedRows = insertApplication.executeUpdate();
            if (affectedRows != 1)
                throw new SQLException("Error inserting Application");
            ResultSet generatedKey = insertApplication.getGeneratedKeys();
            if(generatedKey.next()) {
                application.setId(generatedKey.getInt(1));
                return application;
            } else {
                throw new SQLException("Could not get inserted application Id");
            }
        }
    }

    public ArrayList<Application> getJobApplications(Job job, UserDB userDB, UserKeywordDB userKeywordDB, LocationDB locationDB, JobDB jobDB, JobKeywordDB jobKeywordDB, JobCategoryDB jobCategoryDB) {
        ArrayList<Application> applications = new ArrayList<>();
        try {
            queryApplicationByJob.setInt(1, job.getId());
            ResultSet result = queryApplicationByJob.executeQuery();
            while (result.next()) {
                applications.add(parseApplication(result, userDB, userKeywordDB, locationDB, jobDB, jobKeywordDB, jobCategoryDB));
            }
            return applications;
        } catch (SQLException e) {
            System.out.println("Error querying applicationId = " + job.getId() + ": " + e.getMessage());
            return null;
        }
    }

    public static class View {}

    public static class Column {
        public static final String ID = "id";
        public static final String JOBID = "jobId";
        public static final String USEREMAIL = "userEmail";
        public static final String COVERLETTERDIR = "coverLetterDir";
        public static final String RESUMEDIR = "resumeDir";
        public static final String STATUS = "status";
        public static final String DATE = "date";
    }

    public static class Query {
        public static final String APPLICATION = "SELECT * FROM " + NAME + " WHERE " + ID + " = ?";
        public static final String APPLICATION_BY_JOB = "SELECT * FROM " + NAME + " WHERE " + JOBID + " = ?";
    }

    public static class Insert {
        public static final String APPLICATION = "INSERT INTO " + NAME + " (" + JOBID + ", " + USEREMAIL + ", " + COVERLETTERDIR + ", " + RESUMEDIR + ", " + STATUS + ", " + DATE + ") VALUES (?, ?, ?, ?, ?, ?)";
    }

    public static class Update {}

    public static class Delete {}
}
