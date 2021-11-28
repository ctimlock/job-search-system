package database;

import entities.JobSeeker;
import entities.Location;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DatabaseManager {
    private static final String DATABASE_NAME = "database.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:JobSearchie/database/" + DATABASE_NAME;

    public static final String LOCATION_TABLE = "location";
    public static final String LOCATION_COLUMN_ID = "id";
    public static final String LOCATION_COLUMN_COUNTRY = "country";
    public static final String LOCATION_COLUMN_STATE = "state";
    public static final String LOCATION_COLUMN_CITY = "city";
    public static final String LOCATION_COLUMN_POSTCODE = "postcode";

    public static final String KEYWORD_TABLE = "keyword";
    public static final String KEYWORD_COLUMN_ID = "id";
    public static final String KEYWORD_COLUMN_KEYWORD = "keyword";

    public static final String USER_KEYWORD_TABLE = "user_keyword";
    public static final String USER_KEYWORD_COLUMN_USERID = "userId";
    public static final String USER_KEYWORD_COLUMN_KEYWORDID = "keywordId";

    public static final String CATEGORY_TABLE = "category";
    public static final String CATEGORY_COLUMN_ID = "id";
    public static final String CATEGORY_COLUMN_CATEGORY = "category";

    public static final String SKILL_TABLE = "skill";
    public static final String SKILL_COLUMN_ID = "id";
    public static final String SKILL_COLUMN_CATEGORY = "skill";

    public static final String USER_TABLE = "user";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_ACCOUNTTYPE = "accountType";
    public static final String USER_COLUMN_FIRSTNAME = "firstName";
    public static final String USER_COLUMN_LASTNAME = "lastName";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_LOCATIONID = "locationId";
    public static final String USER_COLUMN_CONTACTNUMBER = "contactNumber";
    public static final String USER_COLUMN_DATECREATED = "dateCreated";
    public static final String USER_COLUMN_DATEOFBIRTH = "dateOfBirth";
    public static final String USER_COLUMN_CURRENTJOBNAME = "currentJobName";
    public static final String USER_COLUMN_CURRENTJOBLEVEL = "currentJobLevel";
    public static final String USER_COLUMN_EXPECTEDCOMPENSATION = "expectedCompensation";
    public static final String USER_COLUMN_RESUMEDIR = "resumeDir";
    public static final String USER_COLUMN_COVERLETTERDIR = "coverLetterDir";
    public static final String USER_COLUMN_COMPANYNAME = "companyName";
    public static final String USER_COLUMN_RECRUITINGSPECIALTY = "recruitingSpecialty";

    public static final String JOB_TABLE = "job";
    public static final String JOB_COLUMN_ID = "id";
    public static final String JOB_COLUMN_JOBTITLE = "jobTitle";
    public static final String JOB_COLUMN_COMPANYNAME = "companyName";
    public static final String JOB_COLUMN_CATEGORYID = "categoryId";
    public static final String JOB_COLUMN_LOCATIONID = "locationId";
    public static final String JOB_COLUMN_WORKTYPE = "workType";
    public static final String JOB_COLUMN_WORKINGARRANGEMENT = "workingArrangement";
    public static final String JOB_COLUMN_COMPENSATION = "compensation";
    public static final String JOB_COLUMN_JOBLEVEL = "jobLevel";
    public static final String JOB_COLUMN_DESCRIPTION = "description";
    public static final String JOB_COLUMN_DATECREATED = "dateCreated";

    public static final String USER_VIEW = "user_full";

    public static final String INSERT_KEYWORD = "INSERT INTO " + KEYWORD_TABLE + " (" + KEYWORD_COLUMN_KEYWORD + ") VALUES (?)";

    public static final String INSERT_USER_KEYWORD = "INSERT INTO " + USER_KEYWORD_TABLE + " (" + USER_KEYWORD_COLUMN_USERID + ", " + USER_KEYWORD_COLUMN_KEYWORDID + ") VALUES (?, ?)";

    public static final String QUERY_LOCATION_ID = "SELECT " + LOCATION_COLUMN_ID + " FROM " + LOCATION_TABLE + " WHERE " + LOCATION_COLUMN_COUNTRY + " = ? AND " + LOCATION_COLUMN_STATE + " = ? AND " + LOCATION_COLUMN_CITY + " = ? AND " + LOCATION_COLUMN_POSTCODE + " = ?";

    private static final String INSERT_LOCATION = "INSERT INTO " + LOCATION_TABLE + " (" + LOCATION_COLUMN_COUNTRY + ", " + LOCATION_COLUMN_STATE + ", " + LOCATION_COLUMN_CITY + ", " + LOCATION_COLUMN_POSTCODE + ") VALUES (?, ?, ?, ?)";

    private static final String INSERT_JOBSEEKER = "INSERT INTO " + USER_TABLE + " (" + USER_COLUMN_ACCOUNTTYPE + ", " + USER_COLUMN_FIRSTNAME + ", " + USER_COLUMN_LASTNAME + ", " + USER_COLUMN_EMAIL + ", " + USER_COLUMN_PASSWORD + ", " + USER_COLUMN_LOCATIONID + ", " + USER_COLUMN_CONTACTNUMBER + ", " + USER_COLUMN_DATECREATED + ", " + USER_COLUMN_DATEOFBIRTH + ", " + USER_COLUMN_CURRENTJOBNAME + ", " + USER_COLUMN_CURRENTJOBLEVEL + ", " + USER_COLUMN_EXPECTEDCOMPENSATION + ", " + USER_COLUMN_RESUMEDIR + ", " + USER_COLUMN_COVERLETTERDIR + ") VALUES ('Job Seeker', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_RECRUITER = "INSERT INTO " + USER_TABLE + " (" + USER_COLUMN_ACCOUNTTYPE + ", " + USER_COLUMN_FIRSTNAME + ", " + USER_COLUMN_LASTNAME + ", " + USER_COLUMN_EMAIL + ", " + USER_COLUMN_PASSWORD + ", " + USER_COLUMN_LOCATIONID + ", " + USER_COLUMN_CONTACTNUMBER + ", " + USER_COLUMN_DATECREATED + ", " + USER_COLUMN_DATEOFBIRTH + ", " + USER_COLUMN_COMPANYNAME + ", " + USER_COLUMN_RECRUITINGSPECIALTY + ") VALUES ('Recruiter', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_JOB = "INSERT INTO " + JOB_TABLE + " (" + JOB_COLUMN_ID + ", " + JOB_COLUMN_JOBTITLE + ", " + JOB_COLUMN_COMPANYNAME + ", " + JOB_COLUMN_CATEGORYID + ", " + JOB_COLUMN_LOCATIONID + ", " + JOB_COLUMN_WORKTYPE + ", " + JOB_COLUMN_WORKINGARRANGEMENT + ", " + JOB_COLUMN_COMPENSATION + ", " + JOB_COLUMN_JOBLEVEL + ", " + JOB_COLUMN_DESCRIPTION + ", " + JOB_COLUMN_DATECREATED + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String QUERY_JOBSEEKER_BY_EMAIL = "SELECT * FROM " + USER_VIEW + " WHERE " + USER_COLUMN_EMAIL + " = ?";

    private static final String QUERY_JOBSEEKER_ID_BY_EMAIL = "SELECT id FROM " + USER_TABLE + " WHERE " + USER_COLUMN_EMAIL + " = ?";

    public static final String QUERY_KEYWORD_ID = "SELECT id FROM " + KEYWORD_TABLE + " WHERE " + KEYWORD_COLUMN_KEYWORD + " = ?";

    public static final String QUERY_USER_KEYWORD = "SELECT * FROM " + USER_KEYWORD_TABLE + " WHERE " + USER_KEYWORD_COLUMN_USERID +
            " = ? AND " + USER_KEYWORD_COLUMN_KEYWORDID + " = ?";

    private PreparedStatement queryJobSeekerByEmail;
    private PreparedStatement queryJobSeekerIdByEmail;
    private PreparedStatement queryKeywordId;
    private PreparedStatement queryLocationId;
    private PreparedStatement queryUserKeyword;
    private PreparedStatement insertKeyword;
    private PreparedStatement insertUserKeyword;
    private PreparedStatement insertIntoJobSeeker;
    private PreparedStatement insertIntoRecruiter;
    private PreparedStatement insertIntoJob;
    private PreparedStatement insertIntoLocation;

    private final SimpleDateFormat dateFormat;
    private Connection conn;

    public DatabaseManager() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

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
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

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

    private int getCount(String table) {
        String sql = "SELECT COUNT(*) FROM " + table;
        try (Statement statement = conn.createStatement(); ResultSet results = statement.executeQuery(sql)) {
            return results.getInt(1);
        } catch (SQLException e) {
            System.out.println("Error getting count: " + e.getMessage());
            return -1;
        }
    }

    public JobSeeker getJobSeekerFromEmail(String email) {
        try {
            queryJobSeekerByEmail.setString(1, email);
            ResultSet results = queryJobSeekerByEmail.executeQuery();
            results.next();
            return resultToJobSeekerWithoutJoin(results);
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public void insertUserKeyword(int userId, String keyword) throws SQLException {
        queryUserKeyword.setInt(1, userId);
        int keywordId = insertKeyword(keyword);
        queryUserKeyword.setInt(2, keywordId);
        ResultSet results = queryUserKeyword.executeQuery();
        if (!results.next()) {
            insertUserKeyword.setInt(1, userId);
            insertUserKeyword.setInt(2, keywordId);
            int affectedRows = insertUserKeyword.executeUpdate();
            if (affectedRows != 1)
                throw new SQLException("Couldn't insert user keyword.");
        }
    }

    private int insertKeyword(String keyword) throws SQLException {
        queryKeywordId.setString(1, keyword);
        ResultSet results = queryKeywordId.executeQuery();
        if (results.next()) {
            return results.getInt(KEYWORD_COLUMN_ID);
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

    public int insertJobSeeker(JobSeeker jobSeeker) throws SQLException {
        queryJobSeekerIdByEmail.setString(1, jobSeeker.getEmail());
        ResultSet results = queryLocationId.executeQuery();
        if (results.next()) {
            return results.getInt(LOCATION_COLUMN_ID);
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

    private int insertLocation(Location location) throws SQLException {
        queryLocationId.setString(1, location.getCountry());
        queryLocationId.setString(2, location.getState());
        queryLocationId.setString(3, location.getCity());
        queryLocationId.setString(4, location.getPostcode());
        ResultSet results = queryLocationId.executeQuery();
        if (results.next()) {
            return results.getInt(LOCATION_COLUMN_ID);
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

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            queryJobSeekerByEmail = conn.prepareStatement(QUERY_JOBSEEKER_BY_EMAIL);
            queryJobSeekerIdByEmail = conn.prepareStatement(QUERY_JOBSEEKER_ID_BY_EMAIL, Statement.RETURN_GENERATED_KEYS);
            queryLocationId = conn.prepareStatement(QUERY_LOCATION_ID, Statement.RETURN_GENERATED_KEYS);
            queryKeywordId = conn.prepareStatement(QUERY_KEYWORD_ID, Statement.RETURN_GENERATED_KEYS);
            queryUserKeyword = conn.prepareStatement(QUERY_USER_KEYWORD, Statement.RETURN_GENERATED_KEYS);
            insertKeyword = conn.prepareStatement(INSERT_KEYWORD, Statement.RETURN_GENERATED_KEYS);
            insertUserKeyword = conn.prepareStatement(INSERT_USER_KEYWORD, Statement.RETURN_GENERATED_KEYS);
            insertIntoJobSeeker = conn.prepareStatement(INSERT_JOBSEEKER, Statement.RETURN_GENERATED_KEYS);
            insertIntoRecruiter = conn.prepareStatement(INSERT_RECRUITER, Statement.RETURN_GENERATED_KEYS);
            insertIntoJob = conn.prepareStatement(INSERT_JOB, Statement.RETURN_GENERATED_KEYS);
            insertIntoLocation = conn.prepareStatement(INSERT_LOCATION, Statement.RETURN_GENERATED_KEYS);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't open database: " + e.getMessage());
            return false;
        }
    }

    private JobSeeker resultToJobSeekerWithoutJoin(ResultSet resultSet) {
        JobSeeker jobSeeker = new JobSeeker();
        try {
            jobSeeker.setId(resultSet.getInt(USER_COLUMN_ID));
            jobSeeker.setFirstName(resultSet.getString(USER_COLUMN_FIRSTNAME));
            jobSeeker.setLastName(resultSet.getString(USER_COLUMN_LASTNAME));
            jobSeeker.setEmail(resultSet.getString(USER_COLUMN_EMAIL));
            jobSeeker.setPassword(resultSet.getString(USER_COLUMN_PASSWORD));
            jobSeeker.setContactNumber(resultSet.getString(USER_COLUMN_CONTACTNUMBER));
            jobSeeker.setDateOfBirth(dateFormat.parse(resultSet.getString(USER_COLUMN_DATEOFBIRTH)));
            jobSeeker.setDateCreated(dateFormat.parse(resultSet.getString(USER_COLUMN_DATECREATED)));
            jobSeeker.setCurrentJobName(resultSet.getString(USER_COLUMN_CURRENTJOBNAME));
            jobSeeker.setCurrentJobLevel(resultSet.getString(USER_COLUMN_CURRENTJOBLEVEL));
            jobSeeker.setExpectedCompensation(resultSet.getInt(USER_COLUMN_EXPECTEDCOMPENSATION));
            jobSeeker.setResumeDir(resultSet.getString(USER_COLUMN_RESUMEDIR));
            jobSeeker.setLocation(resultToLocationWithoutJoin(resultSet));
            return jobSeeker;
        } catch (SQLException e) {
            System.out.println("Error parsing resultSet to JobSeeker: " + e.getMessage());
            return null;
        } catch (ParseException e) {
            System.out.println("Error parsing date from Job Seeker: " + e.getMessage());
            return null;
        }
    }

    private Location resultToLocationWithoutJoin(ResultSet resultSet) {
        Location location = new Location();
        try {
            location.setId(resultSet.getInt(LOCATION_COLUMN_ID));
            location.setCountry(resultSet.getString(LOCATION_COLUMN_COUNTRY));
            location.setState(resultSet.getString(LOCATION_COLUMN_STATE));
            location.setCity(resultSet.getString(LOCATION_COLUMN_CITY));
            location.setPostcode(resultSet.getString(LOCATION_COLUMN_POSTCODE));
            return location;
        } catch (SQLException e) {
            System.out.println("Error parsing resultSet to Location: " + e.getMessage());
            return null;
        }
    }
}
