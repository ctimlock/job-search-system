package database;

import entities.Admin;
import entities.JobSeeker;
import entities.Recruiter;

import java.sql.*;

import static database.Parser.*;
import static database.UserDB.Column.*;

public class UserDB implements DBHelper {
    public static final String NAME = "user";
    /**
     * Prepared statement that will search for a User given an email adress.
     *
     * @see KeywordDB.Query
     */
    private PreparedStatement queryUserByEmail;
    /**
     * Prepared statement that will insert an Admin to the database.
     *
     * @see KeywordDB.Query
     */
    private PreparedStatement insertIntoAdmin;
    /**
     * Prepared statement that will insert a Job Seeker into the user table.
     *
     * @see UserDB.Insert
     */
    private PreparedStatement insertIntoJobSeeker;
    /**
     * Prepared statement that will insert a Recruiter into the user table.
     *
     * @see UserDB.Insert
     */
    private PreparedStatement insertIntoRecruiter;
    /**
     * Prepared statement that will search for an account type given a user email address.
     *
     * @see KeywordDB.Query
     */
    private PreparedStatement queryUserAccountTypeByEmail;

    @Override
    public void close() throws SQLException {
        if (queryUserAccountTypeByEmail != null)
            queryUserAccountTypeByEmail.close();
        if (insertIntoAdmin != null)
            insertIntoAdmin.close();

        if (insertIntoJobSeeker != null)
            insertIntoJobSeeker.close();
        if (insertIntoRecruiter != null)
            insertIntoRecruiter.close();
    }

    /**
     * TESTED
     * Checks to see if the user email exists in the database. Note: User emails are unique.
     *
     * @param email The email to be checked against.
     * @return True if user email exists in the database false otherwise.
     */
    public boolean doesUserExist(String email) {
        return getUserAccountType(email) != null;
    }

    /**
     * TESTED
     * Given an email returns an Admin object. Returns null if email doesn't exist or user is not of a Admin type.
     *
     * @param email The email you would like to check against and get the Admin from.
     * @return An Admin object with the given email address.
     */
    public Admin getAdmin(String email) {
        try {
            queryUserByEmail.setString(1, email);
            ResultSet result = queryUserByEmail.executeQuery();
            if (result.next())
                return parseAdmin(result);
            else
                return null;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * TESTED
     * Given an email returns a JobSeeker object. Returns null if email doesn't exist or user is not of a JobSeeker type.
     *
     * @param email The email you would like to check against and get the Job Seeker from.
     * @return A Job Seeker object with the given email address.
     */
    public JobSeeker getJobSeeker(String email) {
        try {
            queryUserByEmail.setString(1, email);
            ResultSet result = queryUserByEmail.executeQuery();
            if (result.next()) {
                return parseJobSeeker(result);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * TESTED
     * Given an email returns a Recruiter object. Returns null if email doesn't exist or user is not of a Recruiter type.
     *
     * @param email The email you would like to check against and get the Recruiter from.
     * @return A Recruiter object with the given email address.
     */
    public Recruiter getRecruiter(String email) {
        try {
            queryUserByEmail.setString(1, email);
            ResultSet result = queryUserByEmail.executeQuery();
            if (result.next()) {
                return parseRecruiter(result);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * TESTED
     * Gets the account type of the user based on their email. Returns null if the user doesn't exist.
     *
     * @param email The email to be checked against
     * @return Returns either User, Job Seeker, Recruiter, Admin or null if user doesn' exist.
     */
    public String getUserAccountType(String email) {
        try {
            queryUserAccountTypeByEmail.setString(1, email);
            ResultSet result = queryUserAccountTypeByEmail.executeQuery();
            if (result.next())
                return result.getString(UserDB.Column.ACCOUNTTYPE);
            else
                return null;
        } catch (SQLException e) {
            System.out.println("Error querying user table: " + e.getMessage());
            return null;
        }
    }

    /**
     * TESTED
     * Takes an Admin object and inserts it into the database.  Also updates the Admin id when it has successfully
     * been inserted into the database.
     *
     * @param admin An Admin object which will then be inserted into the dataase.
     * @throws SQLException Thrown is certain fields of the Admin are not filled in or it cannot be inserted for
     *                      any other reason.
     */
    public String insertAdmin(Admin admin) throws SQLException {
        if (getAdmin(admin.getEmail()) != null)
            throw new SQLException("Admin " + admin.getEmail() + " already exists in database.");
        else {
            insertIntoAdmin.setString(1, admin.getFirstName());
            insertIntoAdmin.setString(2, admin.getLastName());
            insertIntoAdmin.setString(3, admin.getEmail());
            insertIntoAdmin.setString(4, admin.getPassword());
            insertIntoAdmin.setString(5, dateFormat.format(admin.getDateCreated()));

            int affectedRows = insertIntoAdmin.executeUpdate();
            if (affectedRows == 1) {
                return admin.getEmail();
            } else {
                throw new SQLException("Error inserting Admin rows affected");}
        }
    }

    /**
     * TESTED
     * Inserts a Job Seeker into the user table if it doesn't already exist. Once the job Seeker has been inserted, it
     * will update their id before returning.
     *
     * @param jobSeeker The JobSeeker object to be inserted.
     * @throws SQLException Throws and SQLException if the Job Seeker could not be inserted.
     */
    public String insertJobSeeker(JobSeeker jobSeeker) throws SQLException {
        if (getJobSeeker(jobSeeker.getEmail()) != null)
            throw new SQLException("Job Seeker " + jobSeeker.getEmail() + " already exists in database.");
        else {
            insertIntoJobSeeker.setString(1, jobSeeker.getFirstName());
            insertIntoJobSeeker.setString(2, jobSeeker.getLastName());
            insertIntoJobSeeker.setString(3, jobSeeker.getEmail());
            insertIntoJobSeeker.setString(4, jobSeeker.getPassword());
            insertIntoJobSeeker.setInt(5, jobSeeker.getLocation().getId());
            insertIntoJobSeeker.setString(6, jobSeeker.getContactNumber());
            insertIntoJobSeeker.setString(7, dateFormat.format(jobSeeker.getDateCreated()));
            insertIntoJobSeeker.setString(8, dateFormat.format(jobSeeker.getDateOfBirth()));
            insertIntoJobSeeker.setString(9, jobSeeker.getCurrentJobName());
            insertIntoJobSeeker.setString(10, jobSeeker.getCurrentJobLevel());
            insertIntoJobSeeker.setInt(11, jobSeeker.getExpectedCompensation());
            insertIntoJobSeeker.setString(12, jobSeeker.getResumeDir());
            int affectedRows = insertIntoJobSeeker.executeUpdate();
            if (affectedRows == 1) {
                return jobSeeker.getEmail();
            } else {
                throw new SQLException("Error inserting Job Seeker");
        }
    }}

    /**
     * TESTED
     * Takes a Recruiter object and inserts it into the database. Also updates the Recruiter id when it has sucessfully
     * been inserted into the database.
     *
     * @param recruiter Recruiter object to be inserted into the database.
     * @throws SQLException Throws an exception if the Recruiter is already in the database or something else went wrong.
     */
    public String insertRecruiter(Recruiter recruiter) throws SQLException {
        String recruiterEmail = getRecruiter(recruiter.getEmail()).getEmail();
        if (recruiterEmail != null) {
            return recruiterEmail;
        } else {
            insertIntoRecruiter.setString(1, recruiter.getFirstName());
            insertIntoRecruiter.setString(2, recruiter.getLastName());
            insertIntoRecruiter.setString(3, recruiter.getEmail());
            insertIntoRecruiter.setString(4, recruiter.getPassword());
            insertIntoRecruiter.setString(5, dateFormat.format(recruiter.getDateCreated()));
            insertIntoRecruiter.setString(6, recruiter.getCompanyName());
            insertIntoRecruiter.setString(7, recruiter.getRecruitingSpecialty());
            insertIntoRecruiter.setString(8, recruiter.getContactNumber());
            insertIntoRecruiter.setString(9, dateFormat.format(recruiter.getDateOfBirth()));
            int affectedRows = insertIntoRecruiter.executeUpdate();
            if (affectedRows == 1) {
                return recruiter.getEmail();
            } else {
                throw new SQLException("Couldn't insert Recruiter, updated more or less than one row.");
            }
        }
    }

    /**
     * The connection is initialised in the constructor and opens a connection to the database. It remains open until the
     * close method is called.
     */

    @Override
    public void open(Connection conn) throws SQLException {
        queryUserAccountTypeByEmail = conn.prepareStatement(UserDB.Query.ACCOUNTTYPE_BY_EMAIL);
        queryUserByEmail = conn.prepareStatement(UserDB.Query.USER_BY_EMAIL);

        insertIntoAdmin = conn.prepareStatement(UserDB.Insert.ADMIN);
        insertIntoJobSeeker = conn.prepareStatement(UserDB.Insert.JOBSEEKER);
        insertIntoRecruiter = conn.prepareStatement(UserDB.Insert.RECRUITER);
    }

    public static class View {}

    public static class Column {
        public static final String ACCOUNTTYPE = "accountType";
        public static final String FIRSTNAME = "firstName";
        public static final String LASTNAME = "lastName";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String LOCATIONID = "locationId";
        public static final String CONTACTNUMBER = "contactNumber";
        public static final String DATECREATED = "dateCreated";
        public static final String DATEOFBIRTH = "dateOfBirth";
        public static final String CURRENTJOBNAME = "currentJobName";
        public static final String CURRENTJOBLEVEL = "currentJobLevel";
        public static final String EXPECTEDCOMPENSATION = "expectedCompensation";
        public static final String RESUMEDIR = "resumeDir";
        public static final String COMPANYNAME = "companyName";
        public static final String RECRUITINGSPECIALTY = "recruitingSpecialty";
    }

    public static class Query {
        public static final String USER_BY_EMAIL = "SELECT * FROM " + NAME + " WHERE " + EMAIL + " = ?";
        public static final String ACCOUNTTYPE_BY_EMAIL = "SELECT " + ACCOUNTTYPE + " FROM " + NAME + " WHERE " + EMAIL + " = ?";
    }

    public static class Insert {
        public static final String JOBSEEKER = "INSERT INTO " + NAME + " (" + ACCOUNTTYPE + ", " + FIRSTNAME + ", " + LASTNAME + ", " + EMAIL + ", " + PASSWORD + ", " + LOCATIONID + ", " + CONTACTNUMBER + ", " + DATECREATED + ", " + DATEOFBIRTH + ", " + CURRENTJOBNAME + ", " + CURRENTJOBLEVEL + ", " + EXPECTEDCOMPENSATION + ", " + RESUMEDIR + ") VALUES ('Job Seeker', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        public static final String RECRUITER = "INSERT INTO " + NAME + " (" + ACCOUNTTYPE + ", " + FIRSTNAME + ", " + LASTNAME + ", " + EMAIL + ", " + PASSWORD + ", " + DATECREATED + ", " + COMPANYNAME + ", " + RECRUITINGSPECIALTY + ", " + CONTACTNUMBER + ", " + DATEOFBIRTH + ") VALUES ('Recruiter', ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        public static final String ADMIN = "INSERT INTO " + NAME + " (" + ACCOUNTTYPE + ", " + FIRSTNAME + ", " + LASTNAME + ", " + EMAIL + ", " + PASSWORD + ", " + DATECREATED + ") VALUES ('Admin', ?, ?, ?, ?, ?)";

    }

    public static class Update {}

    public static class Delete {}
}
