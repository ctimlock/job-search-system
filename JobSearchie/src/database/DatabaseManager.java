package database;

import entities.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
    /**
     * dateFormat is used to convert all dates through the DatabaseManager class. e.g., it can be used to convert the
     * string date of 29/11/2021 to a Date object. It is initialised in the constructor.
     */
    private final SimpleDateFormat dateFormat;
    /**
     * Prepared statement that will search for a Keyword ID in the database given a keyword. Will return all
     * keyword ID's with that keyword.
     *
     * @see KeywordDB.Query
     */
    private PreparedStatement queryKeywordId;
    /**
     * Prepared statement that will search for a Location ID in the database given a country, state, city and postcode.
     * Will return all Location ID's with that location.
     *
     * @see LocationDB.Query
     */
    private PreparedStatement queryLocationId;
    /**
     * Prepared statement that will insert a keyword into the keyword table.
     *
     * @see KeywordDB.Insert
     */
    private PreparedStatement insertKeyword;
    /**
     * Prepared statement that will insert an entry into the user_keyword table given a UserId and KeywordId.
     *
     * @see UserKeywordDB.Insert
     */
    private PreparedStatement insertUserKeyword;
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
     * Prepared statement that will insert a Job into the job table.
     *
     * @see JobDB.Insert
     */
    private PreparedStatement insertIntoJob;
    /**
     * Prepared statement that will insert a location into the Location table.
     *
     * @see LocationDB.Insert
     */
    private PreparedStatement insertIntoLocation;
    /**
     * Prepared statement that will return all keyword ID's associated with a given user ID. Used to populate
     * a users keywords.
     *
     * @see UserKeywordDB.Query
     */
    private PreparedStatement queryUserKeywords;
    /**
     * Prepared statement that will search for a keyword given its Id.
     *
     * @see KeywordDB.Query
     */
    private PreparedStatement queryKeyword;
    /**
     * Prepared statement that will search for a location given country, state, city and postcode.
     *
     * @see KeywordDB.Query
     */
    private PreparedStatement queryLocation;
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
     * Prepared statement that will search for an account type given a user email address.
     *
     * @see KeywordDB.Query
     */
    private PreparedStatement queryUserAccountTypeByEmail;
    /**
     * The connection is initialised in the constructor and opens a connection to the database. It remains open until the
     * close method is called.
     */
    private Connection conn;

    /**
     * TESTED
     * Default and only constructor for the DatabaseManager class. Initialises the SQLite Driver Connection and
     * the SimpleDateFormat.
     */
    public DatabaseManager() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
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
            if (insertIntoAdmin != null)
                insertIntoAdmin.close();
            if (queryLocationId != null)
                queryLocationId.close();
            if (queryLocation != null)
                queryLocation.close();
            if (queryKeywordId != null)
                queryKeywordId.close();
            if (queryUserKeywords != null)
                queryUserKeywords.close();
            if (queryKeyword != null)
                queryKeyword.close();
            if (queryUserAccountTypeByEmail != null)
                queryUserAccountTypeByEmail.close();
            if (insertKeyword != null)
                insertKeyword.close();
            if (insertUserKeyword != null)
                insertUserKeyword.close();
            if (insertIntoJobSeeker != null)
                insertIntoJobSeeker.close();
            if (insertIntoRecruiter != null)
                insertIntoRecruiter.close();
            if (insertIntoJob != null)
                insertIntoJob.close();
            if (insertIntoLocation != null)
                insertIntoLocation.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("Couldn't close database: " + e.getMessage());
        }
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
            if (result.next())
                return parseJobSeeker(result);
            else
                return null;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * TESTED
     * Queries the keyword table to retrun the keyword associated the keyword id.
     *
     * @param id The keyword Id that is to be returned.
     * @return Returns the keyword if it exists and null if it doesn't exist.
     */
    private String getKeyword(int id) {
        try {
            queryKeyword.setInt(1, id);
            ResultSet results = queryKeyword.executeQuery();
            if (results.next()) {
                return results.getString(KeywordDB.Column.KEYWORD);
            } else
                return null;
        } catch (SQLException e) {
            System.out.println("Error querying keyword table to get keyword: " + e.getMessage());
            return null;
        }
    }

    /**
     * TESTED
     * Queries the keyword table to see if a keyword exists.
     *
     * @param keyword The keyword to query in the keyword table.
     * @return Return the keywords associated id or -1 if the keyword doesn't exist.
     */
    private int getKeywordId(String keyword) {
        try {
            queryKeywordId.setString(1, keyword);
            ResultSet results = queryKeywordId.executeQuery();
            if (results.next()) {
                return results.getInt(KeywordDB.Column.ID);
            } else
                return -1;
        } catch (SQLException e) {
            System.out.println("Error querying keyword table to get keywordId: " + e.getMessage());
            return -1;
        }
    }

    /**
     * TESTED
     * Queries the database to get a location based on its Id.
     *
     * @param locationId The id of the location to be returned.
     * @return Returns a Location object if it exists in the database otherwise null
     */
    public Location getLocation(int locationId) {
        try {
            queryLocation.setInt(1, locationId);
            ResultSet results = queryLocation.executeQuery();
            if (results.next()) {
                return parseLocation(results);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Couldn't query location table: " + e.getMessage());
            return null;
        }
    }

    /**
     * TESTED
     * Given a location, queries the database to see if it exists, if it does, it will return the id of that location.
     *
     * @param location The Location object to see if exists.
     * @return Returns a the id of the location if its in the database, -1 if not.
     */
    public int getLocationId(Location location) {
        try {
            queryLocationId.setString(1, location.getCountry());
            queryLocationId.setString(2, location.getState());
            queryLocationId.setString(3, location.getCity());
            queryLocationId.setString(4, location.getPostcode());
            ResultSet results = queryLocationId.executeQuery();
            if (results.next()) {
                return results.getInt(LocationDB.Column.ID);
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Couldn't query locationId from location table: " + e.getMessage());
            return -1;
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
            if (result.next())
                return parseRecruiter(result);
            else
                return null;
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
     * Returns a list of the keyword Id's associated with a given userId. May return null if no keywords are associated
     * otherwise will retrun an arraylist of integers which represent the keyword ids associated with a given user.
     *
     * @param userId The user Id to be checked against.
     * @return Returns an ArrayList of keyword ids if 1 or more exist or null of none exist.
     */
    private ArrayList<Integer> getUserKeywordIds(int userId) {
        ArrayList<Integer> keywordIds = new ArrayList<>();
        try {
            queryUserKeywords.setInt(1, userId);
            ResultSet results = queryUserKeywords.executeQuery();
            while (results.next()) {
                keywordIds.add(results.getInt(UserKeywordDB.Column.KEYWORDID));
            }
            return keywordIds.size() == 0 ? null : keywordIds;
        } catch (SQLException e) {
            System.out.println("Error querying user_keyword table to get keywordIds: " + e.getMessage());
            return null;
        }
    }

    /**
     * TESTED
     * Gets all the keywords associated with the given user.
     *
     * @param userId The userId of who you would like to get all associated keywords.
     * @return Returns an ArrayList of strings which represent th users keywords. Returns null if no keywords are
     * associated with the userId.
     * given.
     */
    private ArrayList<String> getUserKeywords(int userId) {
        ArrayList<Integer> keywordIds = getUserKeywordIds(userId);
        if (keywordIds != null) {
            ArrayList<String> keywords = new ArrayList<>();
            keywordIds.forEach(id -> keywords.add(getKeyword(id)));
            return keywords;
        } else
            return null;
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
    public void insertAdmin(Admin admin) throws SQLException {
        if (getAdmin(admin.getEmail()) != null)
            throw new SQLException("Admin " + admin.getEmail() + " already exists in database.");
        else {
            insertIntoAdmin.setString(1, admin.getFirstName());
            insertIntoAdmin.setString(2, admin.getLastName());
            insertIntoAdmin.setString(3, admin.getEmail());
            insertIntoAdmin.setString(4, admin.getPassword());
            insertIntoAdmin.setString(5, dateFormat.format(admin.getDateCreated()));

            int affectedRows = insertIntoAdmin.executeUpdate();
            if (affectedRows != 1)
                throw new SQLException("Error inserting Admin " + affectedRows + " rows affected");
            ResultSet generatedKeys = insertIntoAdmin.getGeneratedKeys();
            if (generatedKeys.next())
                admin.setId(generatedKeys.getInt(1));
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
    public void insertJobSeeker(JobSeeker jobSeeker) throws SQLException {
        if (getJobSeeker(jobSeeker.getEmail()) != null)
            throw new SQLException("Job Seeker " + jobSeeker.getEmail() + " already exists in database.");
        else {
            insertIntoJobSeeker.setString(1, jobSeeker.getFirstName());
            insertIntoJobSeeker.setString(2, jobSeeker.getLastName());
            insertIntoJobSeeker.setString(3, jobSeeker.getEmail());
            insertIntoJobSeeker.setString(4, jobSeeker.getPassword());
            insertIntoJobSeeker.setInt(5, insertLocation(jobSeeker.getLocation()));
            insertIntoJobSeeker.setString(6, jobSeeker.getContactNumber());
            insertIntoJobSeeker.setString(7, dateFormat.format(jobSeeker.getDateCreated()));
            insertIntoJobSeeker.setString(8, dateFormat.format(jobSeeker.getDateOfBirth()));
            insertIntoJobSeeker.setString(9, jobSeeker.getCurrentJobName());
            insertIntoJobSeeker.setString(10, jobSeeker.getCurrentJobLevel());
            insertIntoJobSeeker.setInt(11, jobSeeker.getExpectedCompensation());
            insertIntoJobSeeker.setString(12, jobSeeker.getResumeDir());
            int affectedRows = insertIntoJobSeeker.executeUpdate();
            if (affectedRows != 1)
                throw new SQLException("Error inserting Job Seeker " + affectedRows + " rows affected");
            ResultSet generatedKeys = insertIntoJobSeeker.getGeneratedKeys();
            if (generatedKeys.next())
                jobSeeker.setId(generatedKeys.getInt(1));
            insertJobSeekerKeywords(jobSeeker);
        }
    }

    /**
     * TESTED
     * Inserts all user keywords into the user_keyword table. Uses insertKeyword function.
     * @param jobSeeker The job seeker whos keywords will be added against.
     */
    public void insertJobSeekerKeywords(JobSeeker jobSeeker) {
        int jobSeekerId = jobSeeker.getId();
        jobSeeker.getKeywords().forEach(keyword -> {
            try {
                insertUserKeyword(jobSeekerId, keyword);
            } catch (SQLException e) {
                System.out.println("Error inserting user keywords: " + e.getMessage());
            }
        });
    }

    /**
     * TESTED
     * Inserts a keyword into the keyword table. Checks to see if that keyword already exists and inserts if it doesn't.
     *
     * @param keyword The keyword to be inserted.
     * @return The id of the keyword once it has been inserted or the id of the matching keyword.
     * @throws SQLException Throws an SQLException if the method cannot insert the keyword.
     */
    private int insertKeyword(String keyword) throws SQLException {
        int id = getKeywordId(keyword);
        if (id != -1)
            return id;
        else {
            insertKeyword.setString(1, keyword);
            int affectedRows = insertKeyword.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert keyword, updated more than one row.");
            } else {
                ResultSet generatedKeys = insertKeyword.getGeneratedKeys();
                if (generatedKeys.next())
                    return generatedKeys.getInt(1);
                else
                    throw new SQLException("Couldn't get id from keyword after insert.");
            }
        }
    }

    /**
     * TESTED
     * Inserts a location into the location table if it doesn't already exist.
     *
     * @param location The location to be inserted.
     * @return Returns the id of the location once it has been inserted or the id of the of the existing matching location.
     * @throws SQLException Throws an SLQException if the location could not be inserted.
     */
    public int insertLocation(Location location) throws SQLException {
        int locationId = getLocationId(location);
        if (locationId != -1)
            return locationId;
        else {
            insertIntoLocation.setString(1, location.getCountry());
            insertIntoLocation.setString(2, location.getState());
            insertIntoLocation.setString(3, location.getCity());
            insertIntoLocation.setString(4, location.getPostcode());
            int affectedRows = insertIntoLocation.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert location, updated more than one row.");
            } else {
                ResultSet generatedKeys = insertIntoLocation.getGeneratedKeys();
                if (generatedKeys.next())
                    return generatedKeys.getInt(1);
                else
                    throw new SQLException("Couldn't get id from location after insert.");
            }
        }
    }

    /**
     * TESTED
     * Takes a Recruiter object and inserts it into the database. Also updates the Recruiter id when it has sucessfully
     * been inserted into the database.
     *
     * @param recruiter Recruiter object to be inserted into the database.
     * @throws SQLException Throws an exception if the Recruiter is already in the database or something else went wrong.
     */
    public void insertRecruiter(Recruiter recruiter) throws SQLException {
        if (getRecruiter(recruiter.getEmail()) != null) {
            throw new SQLException("Recruiter " + recruiter.getEmail() + " already exists in database.");
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
            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert Recruiter, updated more or less than one row.");
            }
            ResultSet generatedKeys = insertIntoRecruiter.getGeneratedKeys();
            if (generatedKeys.next())
                recruiter.setId(generatedKeys.getInt(1));
        }
    }

    /**
     * TESTED
     * Inserts a user keyword into the user_keyword table. Checks to see if the keyword exists, if not will be added.
     * Then checks to see if userKeyword combonation exists, addes it if not.
     *
     * @param userId  The userId to match the keyword against.
     * @param keyword The keywordId to associate with the user.
     * @throws SQLException Throws SQLException if the given combination cannot be inserted. Would be thrown if the
     *                      pair already exists, but this shouldn't happen as its already checked.
     */
    public void insertUserKeyword(int userId, String keyword) throws SQLException {
        int keywordId = insertKeyword(keyword);
        ArrayList<Integer> userKeywordIds = getUserKeywordIds(userId);
        if (userKeywordIds == null || !userKeywordIds.contains(keywordId)) {
            insertUserKeyword.setInt(1, userId);
            insertUserKeyword.setInt(2, keywordId);
            int affectedRows = insertUserKeyword.executeUpdate();
            if (affectedRows != 1)
                throw new SQLException("Couldn't insert user keyword.");
        }
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
            queryKeyword = conn.prepareStatement(KeywordDB.Query.KEYWORD, Statement.RETURN_GENERATED_KEYS);
            queryKeywordId = conn.prepareStatement(KeywordDB.Query.KEYWORD_ID, Statement.RETURN_GENERATED_KEYS);
            insertKeyword = conn.prepareStatement(KeywordDB.Insert.INSERT_KEYWORD, Statement.RETURN_GENERATED_KEYS);
            queryLocationId = conn.prepareStatement(LocationDB.Query.LOCATION_ID, Statement.RETURN_GENERATED_KEYS);
            queryLocation = conn.prepareStatement(LocationDB.Query.LOCATION, Statement.RETURN_GENERATED_KEYS);
            queryUserAccountTypeByEmail = conn.prepareStatement(UserDB.Query.ACCOUNTTYPE_BY_EMAIL);
            insertIntoAdmin = conn.prepareStatement(UserDB.Insert.ADMIN);
            queryUserByEmail = conn.prepareStatement(UserDB.Query.USER_BY_EMAIL);
            queryUserKeywords = conn.prepareStatement(UserKeywordDB.Query.USER_KEYWORD, Statement.RETURN_GENERATED_KEYS);
            insertUserKeyword = conn.prepareStatement(UserKeywordDB.Insert.USER_KEYWORD, Statement.RETURN_GENERATED_KEYS);
            insertIntoJobSeeker = conn.prepareStatement(UserDB.Insert.JOBSEEKER, Statement.RETURN_GENERATED_KEYS);
            insertIntoRecruiter = conn.prepareStatement(UserDB.Insert.RECRUITER, Statement.RETURN_GENERATED_KEYS);
            insertIntoJob = conn.prepareStatement(JobDB.Insert.JOB, Statement.RETURN_GENERATED_KEYS);
            insertIntoLocation = conn.prepareStatement(LocationDB.Insert.INSERT_LOCATION, Statement.RETURN_GENERATED_KEYS);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't open database: " + e.getMessage());
            return false;
        }
    }

    /**
     * TESTED
     * Takes a resultSet from a query to the user table and parses the data into an Admin object.
     *
     * @param result ResultSet from user table query.
     * @return Returns an Admin object.
     */
    private Admin parseAdmin(ResultSet result) {
        User user = parseUser(result);
        if (user != null && user.getAccountType().equals("Admin")) {
            return new Admin(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getDateCreated());
        } else
            return null;
    }

    /**
     * TESTED
     * Takes a resultSet from a query to the user table and parses the data into a user object.
     *
     * @param result ResultSet from user object.
     * @return Returns a user object.
     */
    private JobSeeker parseJobSeeker(ResultSet result) {
        User user = parseUser(result);
        if (user != null && user.getAccountType().equals("Job Seeker")) {
            JobSeeker jobSeeker = new JobSeeker(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getDateCreated());
            try {
                jobSeeker.setCurrentJobName(result.getString(UserDB.Column.CURRENTJOBNAME));
                jobSeeker.setCurrentJobLevel(result.getString(UserDB.Column.CURRENTJOBLEVEL));
                jobSeeker.setContactNumber(result.getString(UserDB.Column.CONTACTNUMBER));
                jobSeeker.setResumeDir(result.getString(UserDB.Column.RESUMEDIR));
                jobSeeker.setLocation(getLocation(result.getInt(UserDB.Column.LOCATIONID)));
                jobSeeker.setDateOfBirth(dateFormat.parse(result.getString(UserDB.Column.DATEOFBIRTH)));
                jobSeeker.setExpectedCompensation(result.getInt(UserDB.Column.EXPECTEDCOMPENSATION));
                jobSeeker.setKeywords(getUserKeywords(result.getInt(UserDB.Column.ID)));
                return jobSeeker;
            } catch (SQLException | ParseException e) {
                System.out.println("Error parsing Job Seeker from Result Set: " + e.getMessage());
                return null;
            }
        } else
            return null;
    }

    /**
     * TESTED
     * Takes a result set and converts it to a location.
     *
     * @param result ResultSet from a location table query.
     * @return Returns a location based on the query results, null if it cannot find data.
     */
    private Location parseLocation(ResultSet result) {
        Location location = new Location();
        try {
            location.setId(result.getInt(LocationDB.Column.ID));
            location.setCountry(result.getString(LocationDB.Column.COUNTRY));
            location.setState(result.getString(LocationDB.Column.STATE));
            location.setCity(result.getString(LocationDB.Column.CITY));
            location.setPostcode(result.getString(LocationDB.Column.POSTCODE));
            return location;
        } catch (SQLException e) {
            System.out.println("Error parsing to Location: " + e.getMessage());
            return null;
        }
    }

    /**
     * TESTED
     * Takes a resultSet from a query to the user table and parses the data into a Recruiter object.
     *
     * @param result ResultSet from user table.
     * @return Returns a Recruiter object.
     */
    private Recruiter parseRecruiter(ResultSet result) {
        User user = parseUser(result);
        if (user != null && user.getAccountType().equals("Recruiter")) {
            Recruiter recruiter = new Recruiter(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getDateCreated());
            try {
                //System.out.println(result.getString(UserDB.Column.COMPANYNAME));
                recruiter.setCompanyName(result.getString(UserDB.Column.COMPANYNAME));
                recruiter.setRecruitingSpecialty(result.getString(UserDB.Column.RECRUITINGSPECIALTY));
                recruiter.setContactNumber(result.getString(UserDB.Column.CONTACTNUMBER));
                recruiter.setDateOfBirth(dateFormat.parse(result.getString(UserDB.Column.DATEOFBIRTH)));
                return recruiter;
            } catch (SQLException | ParseException e) {
                System.out.println("Error parsing Recruiter from Result Set: " + e.getMessage());
                return null;
            }
        } else
            return null;
    }

    /**
     * TESTED
     * Used to parse a result set to a generic User object.
     *
     * @param result The result set from a query to the user table or user view.
     * @return Returns a User object if the user email exists, null otherwise.
     */
    private User parseUser(ResultSet result) {
        try {
            if (result.isClosed()) {
                return null;
            } else {
                User user = new User();
                user.setId(result.getInt(UserDB.Column.ID));
                user.setAccountType(result.getString(UserDB.Column.ACCOUNTTYPE));
                user.setFirstName(result.getString(UserDB.Column.FIRSTNAME));
                user.setLastName(result.getString(UserDB.Column.LASTNAME));
                user.setEmail(result.getString(UserDB.Column.EMAIL));
                user.setPassword(result.getString(UserDB.Column.PASSWORD));
                user.setDateCreated(dateFormat.parse(result.getString(UserDB.Column.DATECREATED)));
                return user;
            }
        } catch (SQLException | ParseException e) {
            System.out.println("Error parsing resultSet to user object: " + e.getMessage());
            return null;
        }
    }
}
