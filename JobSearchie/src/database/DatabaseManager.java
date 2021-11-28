package database;

import entities.JobSeeker;
import entities.Location;
import entities.Recruiter;
import entities.User;

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
     * Prepared statement that will search for a User in the database given an email address. Will return all
     * Users with that email.
     * @see UserDB.Query
     */
    private PreparedStatement queryJobSeekerByEmail;
    /**
     * Prepared statement that will search for a User ID in the database given an email address. Will return all
     * Users ID's with that email.
     * @see UserDB.Query
     */
    private PreparedStatement queryJobSeekerIdByEmail;
    /**
     * Prepared statement that will search for a Keyword ID in the database given a keyword. Will return all
     * keyword ID's with that keyword.
     * @see KeywordDB.Query
     */
    private PreparedStatement queryKeywordId;
    /**
     * Prepared statement that will search for a Location ID in the database given a country, state, city and postcode.
     * Will return all Location ID's with that location.
     * @see LocationDB.Query
     */
    private PreparedStatement queryLocationId;
    /**
     * Prepared statement that will search for all entries in the User_Keyword table based on UserId and KeywordId,
     * returns all rows that match the criteria.
     * @see UserKeywordDB.Query
     */
    private PreparedStatement queryCheckUserKeyword;
    /**
     * Prepared statement that will insert a keyword into the keyword table.
     * @see KeywordDB.Insert
     */
    private PreparedStatement insertKeyword;
    /**
     * Prepared statement that will insert an entry into the user_keyword table given a UserId and KeywordId.
     * @see UserKeywordDB.Insert
     */
    private PreparedStatement insertUserKeyword;
    /**
     * Prepared statement that will insert a Job Seeker into the user table.
     * @see UserDB.Insert
     */
    private PreparedStatement insertIntoJobSeeker;
    /**
     * Prepared statement that will insert a Recruiter into the user table.
     * @see UserDB.Insert
     */
    private PreparedStatement insertIntoRecruiter;
    /**
     * Prepared statement that will insert a Job into the job table.
     * @see JobDB.Insert
     */
    private PreparedStatement insertIntoJob;
    /**
     * Prepared statement that will insert a location into the Location table.
     * @see LocationDB.Insert
     */
    private PreparedStatement insertIntoLocation;
    /**
     * Prepared statement that will search for a User ID in the database given an email address. Will return all
     * Users ID's with that email.
     * @see UserDB.Query
     */
    private PreparedStatement queryRecruiterByEmail;
    /**
     * Prepared statement that will search for a User ID in the database given an email address. Will return all
     * Users ID's with that email.
     * @see UserDB.Query
     */
    private PreparedStatement queryRecruiterIdByEmail;
    /**
     * Prepared statement that will return all keyword ID's associated with a given user ID. Used to populate
     * a users keywords.
     * @see UserKeywordDB.Query
     */
    private PreparedStatement queryUserKeywords;
    /**
     * Prepared statement that will search for a keyword given its Id.
     * @see KeywordDB.Query
     */
    private PreparedStatement queryKeyword;


    /**
     * dateFormat is used to convert all dates through the DatabaseManager class. e.g., it can be used to convert the
     * string date of 29/11/2021 to a Date object. It is initialised in the constructor.
     */
    private final SimpleDateFormat dateFormat;
    /**
     * The connection is initialised in the constructor and opens a connection to the database. It remains open until the
     * close method is called.
     */
    private Connection conn;

    /**
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
     * When method is called it closes all open PreparedStatements and finally closes the database connection. This
     * should only be called when the user is ready to close the entire application.
     */
    public void close() {
        try {
            if (queryJobSeekerByEmail != null)
                queryJobSeekerByEmail.close();
            if (queryJobSeekerIdByEmail != null)
                queryJobSeekerIdByEmail.close();
            if (queryLocationId != null)
                queryLocationId.close();
            if (queryKeywordId != null)
                queryKeywordId.close();
            if (queryUserKeywords != null)
                queryUserKeywords.close();
            if (queryKeyword != null)
                queryKeyword.close();
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
     * Takes a result set and returns a User object. Returns a null object if a User cannot be created.
     * @param resultSet ResultSet returned from a query to the user table or view.
     * @return Returns a User object.
     */
    private User resultToUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getInt(UserDB.Column.ID));
            user.setFirstName(resultSet.getString(UserDB.Column.FIRSTNAME));
            user.setLastName(resultSet.getString(UserDB.Column.LASTNAME));
            user.setEmail(resultSet.getString(UserDB.Column.EMAIL));
            user.setPassword(resultSet.getString(UserDB.Column.PASSWORD));
            user.setDateCreated(dateFormat.parse(resultSet.getString(UserDB.Column.DATECREATED)));
            return user;
        } catch (SQLException e) {
            System.out.println("Error parsing resultSet to User: " + e.getMessage());
            return null;
        } catch (ParseException e) {
            System.out.println("Error parsing date from User: " + e.getMessage());
            return null;
        }
    }

    /**
     * Takes a result set and returns a Recruiter object. Returns a null object if a Recruiter cannot be created.
     * User the resultToUser to fill out the base class, then gets additional Recruiter information.
     * @param resultSet ResultSet returned from a query to the user table or view.
     * @return Returns a Recruiter object.
     */
    private Recruiter resultToRecruiter(ResultSet resultSet) {
        User baseUser = resultToUser(resultSet);
        if (baseUser == null)
            return null;
        Recruiter recruiter = new Recruiter(baseUser.getId(), baseUser.getFirstName(), baseUser.getLastName(), baseUser.getEmail(), baseUser.getPassword(), baseUser.getDateCreated());
        try {
            recruiter.setContactNumber(resultSet.getString(UserDB.Column.CONTACTNUMBER));
            recruiter.setDateOfBirth(dateFormat.parse(resultSet.getString(UserDB.Column.DATEOFBIRTH)));
            recruiter.setCompanyName(resultSet.getString(UserDB.Column.COMPANYNAME));
            recruiter.setRecruitingSpecialty(resultSet.getString(UserDB.Column.RECRUITINGSPECIALTY));
            return recruiter;
        } catch (SQLException e) {
            System.out.println("Error parsing resultSet to Recruiter: " + e.getMessage());
            return null;
        } catch (ParseException e) {
            System.out.println("Error parsing date from Recruiter: " + e.getMessage());
            return null;
        }
    }

    /**
     * Query to check if an email exists in the user table.
     * @param email The email you would like to check against.
     * @return True if the email exists in the database, false otherwise.
     */
    public boolean doesEmailExist(String email) {
        try {
            queryJobSeekerByEmail.setString(1, email);
            ResultSet results = queryJobSeekerByEmail.executeQuery();
            return results.next();
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return false;
        }
    }

    /**
     * Gets the number of rows in a given table.
     * @param table The table name that will be queried.
     * @return The number of rows contained in that table.
     */
    private int getCount(String table) {
        String sql = "SELECT COUNT(*) FROM " + table;
        try (Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(sql)) {
            return results.getInt(1);
        } catch (SQLException e) {
            System.out.println("Error getting count: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Given an email returns a JobSeeker object. Returns null if email doesn't exist.
     * @param email The email you would like to check against and get the Job Seeker from.
     * @return A Job Seeker object with the given email address.
     */
    public JobSeeker getJobSeekerFromEmail(String email) {
        try {
            queryJobSeekerByEmail.setString(1, email);
            ResultSet results = queryJobSeekerByEmail.executeQuery();
            results.next();
            return resultToJobSeeker(results);
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Inserts a user keyword into the user_keyword table. Checks to see if the combination exists, if not it will be
     * inserted, if it does the method will return without inserting.
     * @param userId The userId to match the keyword against.
     * @param keyword The keywordId to associate with the user.
     * @throws SQLException Throws SQLException if the given combination cannot be inserted. Would be thrown if the
     * pair already exists, but this shouldn't happen as its already checked.
     */
    private void insertUserKeyword(int userId, String keyword) throws SQLException {
        queryCheckUserKeyword.setInt(1, userId);
        int keywordId = insertKeyword(keyword);
        queryCheckUserKeyword.setInt(2, keywordId);
        ResultSet results = queryCheckUserKeyword.executeQuery();
        if (!results.next()) {
            insertUserKeyword.setInt(1, userId);
            insertUserKeyword.setInt(2, keywordId);
            int affectedRows = insertUserKeyword.executeUpdate();
            if (affectedRows != 1)
                throw new SQLException("Couldn't insert user keyword.");
        }
    }

    /**
     * Gets all the keywords associated with the given user.
     * @param userId The userId of who you would like to get all associated keywords.
     * @return Returns an ArrayList of strings which represent th users keywords.
     * @throws SQLException Throws an SQLException if the the table cannot be queried or and incorrect column name was
     * given.
     */
    private ArrayList<String> queryUserKeywords (int userId) throws SQLException {
        ArrayList<String> keywords = new ArrayList<>();
        ArrayList<Integer> keywordIds = new ArrayList<>();
        queryUserKeywords.setInt(1, userId);
        ResultSet results = queryKeywordId.executeQuery();
        while (results.next())
            keywordIds.add(results.getInt(UserKeywordDB.Column.KEYWORDID));
        for (int id : keywordIds) {
            queryKeyword.setInt(1, id);
            ResultSet result = queryKeywordId.executeQuery();
            while(result.next()) {
                keywords.add(result.getString(KeywordDB.Column.KEYWORD));
            }
        }
        return keywords;
    }

    /**
     * Inserts a keyword into the keyword table. Checks to see if that keyword already exists and inserts if it doesn't.
     * @param keyword The keyword to be inserted.
     * @return The id of the keyword once it has been inserted or the id of the matching keyword.
     * @throws SQLException Throws an SQLException if the method cannot insert the keyword.
     */
    private int insertKeyword(String keyword) throws SQLException {
        queryKeywordId.setString(1, keyword);
        ResultSet results = queryKeywordId.executeQuery();
        if (results.next()) {
            return results.getInt(KeywordDB.Column.ID);
        } else {
            insertKeyword.setString(1, keyword);
            int affectedRows = insertKeyword.executeUpdate();
            if (affectedRows != 1)
                throw new SQLException("Couldn't insert keyword.");
            ResultSet generatedKeys = insertKeyword.getGeneratedKeys();
            if (generatedKeys.next())
                return generatedKeys.getInt(1);
            else
                throw new SQLException("Couldn't get id from location.");
        }
    }

    /**
     * Inserts a Job Seeker into the user table if it doesn't already exist.
     * @param jobSeeker The JobSeeker object to be inserted.
     * @return Returns the id of the JobSeeker once it has been inserted or the id of the existing matching Job Seeker.
     * @throws SQLException Throws and SQLException if the Job Seeker could not be inserted.
     */
    public int insertJobSeeker(JobSeeker jobSeeker) throws SQLException {
        if (doesEmailExist(jobSeeker.getEmail())) {
            System.out.println("Email exists");
            return getJobSeekerFromEmail(jobSeeker.getEmail()).getId();
        } else {
            queryJobSeekerIdByEmail.setString(1, jobSeeker.getEmail());
            ResultSet results = queryLocationId.executeQuery();
            if (results.next()) {
                return results.getInt(LocationDB.Column.ID);
            } else {
                conn.setAutoCommit(false);
                int locationId = insertLocation(jobSeeker.getLocation());
                insertIntoJobSeeker.setString(1, jobSeeker.getFirstName());
                insertIntoJobSeeker.setString(2, jobSeeker.getLastName());
                insertIntoJobSeeker.setString(3, jobSeeker.getEmail());
                insertIntoJobSeeker.setString(4, jobSeeker.getPassword());
                insertIntoJobSeeker.setInt(5, locationId);
                insertIntoJobSeeker.setString(6, jobSeeker.getContactNumber());
                insertIntoJobSeeker.setString(7, dateFormat.format(jobSeeker.getDateCreated()));
                insertIntoJobSeeker.setString(8, dateFormat.format(jobSeeker.getDateOfBirth()));
                insertIntoJobSeeker.setString(9, jobSeeker.getCurrentJobName());
                insertIntoJobSeeker.setString(10, jobSeeker.getCurrentJobLevel());
                insertIntoJobSeeker.setInt(11, jobSeeker.getExpectedCompensation());
                insertIntoJobSeeker.setString(12, jobSeeker.getResumeDir());
                int affectedRows = insertIntoJobSeeker.executeUpdate();
                if (affectedRows != 1)
                    throw new SQLException("Couldn't insert location.");
                conn.commit();
                conn.setAutoCommit(true);
                ResultSet generatedKeys = insertIntoJobSeeker.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    jobSeeker.getKeywords().forEach(keyword -> {
                        try {
                            insertUserKeyword(userId, keyword);
                        } catch (SQLException e) {
                            System.out.println("Unable to insert user keyword: " + e.getMessage());
                        }
                    });
                    return generatedKeys.getInt(1);
                } else
                    throw new SQLException("Couldn't get id from Job Seeker.");
            }
        }
    }

    /**
     * Inserts a location into the location table if it doesn't already exist.
     * @param location The location to be inserted.
     * @return Returns the id of the location once it has been inserted or the id of the of the existing matching location.
     * @throws SQLException Throws an SLQException if the location could not be inserted.
     */
    private int insertLocation(Location location) throws SQLException {
        queryLocationId.setString(1, location.getCountry());
        queryLocationId.setString(2, location.getState());
        queryLocationId.setString(3, location.getCity());
        queryLocationId.setString(4, location.getPostcode());
        ResultSet results = queryLocationId.executeQuery();
        if (results.next()) {
            return results.getInt(LocationDB.Column.ID);
        } else {
            insertIntoLocation.setString(1, location.getCountry());
            insertIntoLocation.setString(2, location.getState());
            insertIntoLocation.setString(3, location.getCity());
            insertIntoLocation.setString(4, location.getPostcode());
            int affectedRows = insertIntoLocation.executeUpdate();
            if (affectedRows != 1)
                throw new SQLException("Couldn't insert location.");
            ResultSet generatedKeys = insertIntoLocation.getGeneratedKeys();
            if (generatedKeys.next())
                return generatedKeys.getInt(1);
            else
                throw new SQLException("Couldn't get id from location.");
        }
    }

    /**
     * Opens the database and initilises the prepared statements.
     * @return Retruns true if the database opened correctly and all prepared statements have correct SQL syntax.
     */
    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            queryJobSeekerByEmail = conn.prepareStatement(UserDB.Query.JOBSEEKER_BY_EMAIL);
            queryJobSeekerIdByEmail = conn.prepareStatement(UserDB.Query.JOBSEEKER_ID_BY_EMAIL, Statement.RETURN_GENERATED_KEYS);
            queryLocationId = conn.prepareStatement(LocationDB.Query.LOCATION_ID, Statement.RETURN_GENERATED_KEYS);
            queryKeywordId = conn.prepareStatement(KeywordDB.Query.KEYWORD_ID, Statement.RETURN_GENERATED_KEYS);
            queryUserKeywords = conn.prepareStatement(UserKeywordDB.Query.USER_KEYWORD, Statement.RETURN_GENERATED_KEYS);
            queryCheckUserKeyword = conn.prepareStatement(UserKeywordDB.Query.CHECK_USER_KEYWORD, Statement.RETURN_GENERATED_KEYS);
            queryKeyword = conn.prepareStatement(KeywordDB.Query.KEYWORD, Statement.RETURN_GENERATED_KEYS);
            insertKeyword = conn.prepareStatement(KeywordDB.Insert.INSERT_KEYWORD, Statement.RETURN_GENERATED_KEYS);
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
     * Parses a result set to return a JobSeeker object. If the results aren't correct to return a Job Seeker it will
     * return null.
     * @param resultSet The ResultSet from a query to the user View or Table.
     * @return Returns a JobSeeker object or null if the results cannot be parsed.
     */
    private JobSeeker resultToJobSeeker(ResultSet resultSet) {
        User baseUser = resultToUser(resultSet);
        if (baseUser == null)
            return null;
        JobSeeker jobSeeker = new JobSeeker(baseUser.getId(), baseUser.getFirstName(), baseUser.getLastName(), baseUser.getEmail(), baseUser.getPassword(), baseUser.getDateCreated());
        try {
            jobSeeker.setContactNumber(resultSet.getString(UserDB.Column.CONTACTNUMBER));
            jobSeeker.setDateOfBirth(dateFormat.parse(resultSet.getString(UserDB.Column.DATEOFBIRTH)));
            jobSeeker.setCurrentJobName(resultSet.getString(UserDB.Column.CURRENTJOBNAME));
            jobSeeker.setCurrentJobLevel(resultSet.getString(UserDB.Column.CURRENTJOBLEVEL));
            jobSeeker.setExpectedCompensation(resultSet.getInt(UserDB.Column.EXPECTEDCOMPENSATION));
            jobSeeker.setResumeDir(resultSet.getString(UserDB.Column.RESUMEDIR));
            jobSeeker.setLocation(resultToLocation(resultSet));
            //
            jobSeeker.setKeywords(queryUserKeywords(baseUser.getId()));
            return jobSeeker;
        } catch (SQLException e) {
            System.out.println("Error parsing resultSet to JobSeeker: " + e.getMessage());
            return null;
        } catch (ParseException e) {
            System.out.println("Error parsing date from Job Seeker: " + e.getMessage());
            return null;
        }
    }

    /**
     * Parses a result set to return a Location object. If the results aren't correct to return a Location it will
     * return null.
     * @param resultSet The ResultSet from a query to the location Table.
     * @return Returns a Location object or null if the results cannot be parsed.
     */
    private Location resultToLocation(ResultSet resultSet) {
        Location location = new Location();
        try {
            location.setId(resultSet.getInt(LocationDB.Column.ID));
            location.setCountry(resultSet.getString(LocationDB.Column.COUNTRY));
            location.setState(resultSet.getString(LocationDB.Column.STATE));
            location.setCity(resultSet.getString(LocationDB.Column.CITY));
            location.setPostcode(resultSet.getString(LocationDB.Column.POSTCODE));
            return location;
        } catch (SQLException e) {
            System.out.println("Error parsing resultSet to Location: " + e.getMessage());
            return null;
        }
    }
}
