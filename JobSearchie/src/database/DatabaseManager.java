package database;

import entities.JobSeeker;
import entities.Location;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DatabaseManager {
    private static final String DATABASE_NAME = "database.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:JobSearchie/database/" + DATABASE_NAME;

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

    public int insertJobSeeker(JobSeeker jobSeeker) throws SQLException {
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

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            queryJobSeekerByEmail = conn.prepareStatement(UserDB.Query.JOBSEEKER_BY_EMAIL);
            queryJobSeekerIdByEmail = conn.prepareStatement(UserDB.Query.JOBSEEKER_ID_BY_EMAIL, Statement.RETURN_GENERATED_KEYS);
            queryLocationId = conn.prepareStatement(LocationDB.Query.LOCATION_ID, Statement.RETURN_GENERATED_KEYS);
            queryKeywordId = conn.prepareStatement(KeywordDB.Query.QUERY_KEYWORD_ID, Statement.RETURN_GENERATED_KEYS);
            queryUserKeyword = conn.prepareStatement(UserKeywordDB.Query.QUERY_USER_KEYWORD, Statement.RETURN_GENERATED_KEYS);
            insertKeyword = conn.prepareStatement(KeywordDB.Insert.INSERT_KEYWORD, Statement.RETURN_GENERATED_KEYS);
            insertUserKeyword = conn.prepareStatement(UserKeywordDB.Insert.INSERT_USER_KEYWORD, Statement.RETURN_GENERATED_KEYS);
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

    private JobSeeker resultToJobSeekerWithoutJoin(ResultSet resultSet) {
        //TODO: get keywords
        JobSeeker jobSeeker = new JobSeeker();
        try {
            jobSeeker.setId(resultSet.getInt(UserDB.Column.ID));
            jobSeeker.setFirstName(resultSet.getString(UserDB.Column.FIRSTNAME));
            jobSeeker.setLastName(resultSet.getString(UserDB.Column.LASTNAME));
            jobSeeker.setEmail(resultSet.getString(UserDB.Column.EMAIL));
            jobSeeker.setPassword(resultSet.getString(UserDB.Column.PASSWORD));
            jobSeeker.setContactNumber(resultSet.getString(UserDB.Column.CONTACTNUMBER));
            jobSeeker.setDateOfBirth(dateFormat.parse(resultSet.getString(UserDB.Column.DATEOFBIRTH)));
            jobSeeker.setDateCreated(dateFormat.parse(resultSet.getString(UserDB.Column.DATECREATED)));
            jobSeeker.setCurrentJobName(resultSet.getString(UserDB.Column.CURRENTJOBNAME));
            jobSeeker.setCurrentJobLevel(resultSet.getString(UserDB.Column.CURRENTJOBLEVEL));
            jobSeeker.setExpectedCompensation(resultSet.getInt(UserDB.Column.EXPECTEDCOMPENSATION));
            jobSeeker.setResumeDir(resultSet.getString(UserDB.Column.RESUMEDIR));
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
