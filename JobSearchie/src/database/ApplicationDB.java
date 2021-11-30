package database;

import entities.Application;

import java.sql.*;

import static database.ApplicationDB.Column.*;
import static database.Parser.parseApplication;


public class ApplicationDB implements DBHelper {
    public static final String NAME = "application";
    /**
     * Prepared statement that will insert a Application into the application table.
     *
     * @see ApplicationDB.Insert
     */
    private PreparedStatement insertApplication;
    private PreparedStatement queryApplication;

    @Override
    public void close() throws SQLException {
        if (insertApplication != null)
            insertApplication.close();
        if (queryApplication != null)
            queryApplication.close();
    }

    @Override
    public void open(Connection conn) throws SQLException {
        insertApplication = conn.prepareStatement(ApplicationDB.Insert.APPLICATION, Statement.RETURN_GENERATED_KEYS);
        queryApplication = conn.prepareStatement(ApplicationDB.Query.APPLICATION, Statement.RETURN_GENERATED_KEYS);
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

    public Application insertApplication(Application application, JobDB jobDB, JobKeywordDB jobKeywordDB, JobCategoryDB jobCategoryDB, UserDB userDB, UserKeywordDB userKeywordDB, LocationDB locationDB) throws SQLException {
        if (application.getId()!= -1)
            return application;
        else {
            application.setJob(jobDB.insertJob(application.getJob(), userDB, locationDB, jobKeywordDB, jobCategoryDB));
            application.setJobSeeker(userDB.insertJobSeeker(application.getJobSeeker(), locationDB, userKeywordDB));

            insertApplication.setInt(1, application.getId());
            insertApplication.setInt(2, application.getJob().getId());
            insertApplication.setString(3, application.getJobSeeker().getEmail());
            insertApplication.setString(4, application.getCoverLetterDir());
            insertApplication.setString(5, application.getResumeDir());
            insertApplication.setString(6, application.getStatus());
            insertApplication.setDate(7, application.getApplicationDate());
            int affectedRows = insertApplication.executeUpdate();
            if (affectedRows != 1)
                throw new SQLException("Error inserting Application Seeker");
            ResultSet generatedKey = insertApplication.getGeneratedKeys();
            if(generatedKey.next()) {
                application.setId(generatedKey.getInt(1));
                return application;
            }
            else
                throw new SQLException("Could not get inserted application Id");
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
    }

    public static class Insert {
        public static final String APPLICATION = "INSERT INTO " + NAME + " (" + ID + ", " + JOBID + ", " + USEREMAIL + ", " + COVERLETTERDIR + ", " + RESUMEDIR + ", " + STATUS + ", " + DATE + ") VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    public static class Update {}

    public static class Delete {}
}
