package database;

import entities.*;

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

    private UserDB userDB;
    private LocationDB locationDB;
    private KeywordDB keywordDB;
    private CategoryDB categoryDB;
    private JobDB jobDB;
    private UserKeywordDB userKeywordDB;
    private JobKeywordDB jobKeywordDB;
    private JobCategoryDB jobCategoryDB;
    private ApplicationDB applicationDB;


    private Connection conn;

    /**
     * TESTED
     * Default and only constructor for the DatabaseManager class. Initialises the SQLite Driver Connection and
     * the SimpleDateFormat.
     */
    public DatabaseManager() {
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
            locationDB.close();
            keywordDB.close();
            categoryDB.close();
            jobDB.close();
            userKeywordDB.close();
            jobKeywordDB.close();
            jobCategoryDB.close();
            applicationDB.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("Couldn't close database: " + e.getMessage());
        }
    }

    public Admin getAdmin(String email) {
        return userDB.getAdmin(email);
    }

    public Application getApplication(int applicationId) {
        return applicationDB.getApplication(applicationId, userDB, userKeywordDB, locationDB, jobDB, jobKeywordDB, jobCategoryDB);
    }

    public Job getJob(int id) {
        return jobDB.getJob(id, userDB, locationDB, jobKeywordDB, jobCategoryDB);
    }

    public JobSeeker getJobSeeker(String email) {
        return userDB.getJobSeeker(email, userKeywordDB, locationDB);
    }

    public Recruiter getRecruiter(String email) {
        return userDB.getRecruiter(email);
    }

    public Admin insertAdmin(Admin admin) throws SQLException {
        return userDB.insertAdmin(admin);
    }

    public Application insertApplication(Application application) throws SQLException {
        return applicationDB.insertApplication(application);
    }

    public Job insertJob(Job job) throws SQLException {
        return jobDB.insertJob(job, locationDB, jobKeywordDB, jobCategoryDB);
    }

    public JobSeeker insertJobSeeker(JobSeeker jobSeeker) throws SQLException {
        return userDB.insertJobSeeker(jobSeeker, locationDB, userKeywordDB);
    }

    public Recruiter insertRecruiter(Recruiter recruiter) throws SQLException {
        return userDB.insertRecruiter(recruiter);
    }

//    public ArrayList<Job> getAllJobs() {
//        return jobDB.getAllJobs();
//    }

    /**
     * TESTED
     * Opens the database and initilises the prepared statements.
     *
     * @return Retruns true if the database opened correctly and all prepared statements have correct SQL syntax.
     */
    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            locationDB = new LocationDB(conn);
            keywordDB = new KeywordDB(conn);
            categoryDB = new CategoryDB(conn);
            jobDB = new JobDB(conn);
            userDB = new UserDB(conn);
            userKeywordDB = new UserKeywordDB(conn);
            jobKeywordDB = new JobKeywordDB(conn);
            jobCategoryDB = new JobCategoryDB(conn);
            applicationDB = new ApplicationDB(conn);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't open database: " + e.getMessage());
            return false;
        }
    }
}
