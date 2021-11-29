package database;

import entities.Admin;
import entities.Job;
import entities.JobSeeker;
import entities.Recruiter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    /**
     * The name of the database file stored in the database folder.
     */
    private static final String DATABASE_NAME = "database.db";
    /**
     * Generic SQLite connection string with a relative reference to the database folder. Also uses the database name
     * to get the exact file.
     */
    private static final String CONNECTION_STRING = "jdbc:sqlite:JobSearchie/database/" + DATABASE_NAME;

    private final UserDB userDB;
    private final Parser parser;
    private final LocationDB locationDB;
    private final KeywordDB keywordDB;
    private final CategoryDB categoryDB;
    private final JobDB jobDB;
    private final UserKeywordDB userKeywordDB;
    private final JobKeywordDB jobKeywordDB;
    private final JobCategoryDB jobCategoryDB;


    private Connection conn;

    /**
     * TESTED
     * Default and only constructor for the DatabaseManager class. Initialises the SQLite Driver Connection and
     * the SimpleDateFormat.
     */
    public DatabaseManager() {
        parser = new Parser();
        locationDB = new LocationDB();
        keywordDB = new KeywordDB();
        categoryDB = new CategoryDB();
        jobDB = new JobDB();
        userDB = new UserDB();
        userKeywordDB = new UserKeywordDB();
        jobKeywordDB = new JobKeywordDB();
        jobCategoryDB = new JobCategoryDB();

        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            open();
        } catch (SQLException e) {
            System.out.println("Error creating a DriverManager Instance: " + e.getMessage());
        }
    }

    /**
     * TESTED
     * When method is called it closes all open PreparedStatements and finally closes the database connection. This
     * should only be called when the user is ready to close the entire application.
     */
    public void close() {
        try {
            userDB.close();
            parser.close();
            locationDB.close();
            keywordDB.close();
            categoryDB.close();
            jobDB.close();
            userKeywordDB.close();
            jobKeywordDB.close();
            jobCategoryDB.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("Couldn't close database: " + e.getMessage());
        }
    }

    public void insertJob(Job job) throws SQLException {
        job.getLocation().setId(locationDB.insertLocation(job.getLocation()));
        job.getAuthor().setEmail(userDB.insertRecruiter(job.getAuthor()));
        job.setId(jobDB.insertJob(job));
        jobKeywordDB.insertJobKeywords(job);
        jobCategoryDB.insertJobCategories(job);
    }

    public Job getJob(int id) {
        Job job = jobDB.getJob(id);
        job.setAuthor(userDB.getRecruiter(job.getAuthor().getEmail()));
        job.setLocation(locationDB.getLocation(job.getLocation().getId()));
        job.setKeywords(jobKeywordDB.getJobKeywords(job.getId()));
        job.setCategories(jobCategoryDB.getJobCategories(job.getId()));
        return job;
    }

    public Admin getAdmin(String email) {
        return userDB.getAdmin(email);
    }

    public JobSeeker getJobSeeker(String email) {
        JobSeeker jobSeeker = userDB.getJobSeeker(email);
        jobSeeker.setKeywords(userKeywordDB.getUserKeywords(email));
        jobSeeker.setLocation(locationDB.getLocation(jobSeeker.getLocation().getId()));
        return jobSeeker;
    }

    public Recruiter getRecruiter(String email) {
        return userDB.getRecruiter(email);
    }

    public void insertAdmin(Admin admin) throws SQLException {
        userDB.insertAdmin(admin);
    }

    public void insertJobSeeker(JobSeeker jobSeeker) throws SQLException {
        jobSeeker.getLocation().setId(locationDB.insertLocation(jobSeeker.getLocation()));
        userDB.insertJobSeeker(jobSeeker);
        userKeywordDB.insertJobSeekerKeywords(jobSeeker);
    }

    public void insertRecruiter(Recruiter recruiter) throws SQLException {
        userDB.insertRecruiter(recruiter);
    }

    /**
     * TESTED
     * Opens the database and initilises the prepared statements.
     *
     * @return Retruns true if the database opened correctly and all prepared statements have correct SQL syntax.
     */
    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            userDB.open(conn);
            parser.open(conn);
            locationDB.open(conn);
            categoryDB.open(conn);
            keywordDB.open(conn);
            jobDB.open(conn);
            userKeywordDB.open(conn);
            jobKeywordDB.open(conn);
            jobCategoryDB.open(conn);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't open database: " + e.getMessage());
            return false;
        }
    }
}
