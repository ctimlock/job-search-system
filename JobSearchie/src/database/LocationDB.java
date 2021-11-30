package database;

import entities.Location;

import java.sql.*;

import static database.LocationDB.Column.*;
import static database.Parser.parseLocation;

public class LocationDB implements DBHelper {
    public static final String NAME = "location";
    private PreparedStatement queryLocationId;
    private PreparedStatement queryLocation;
    private PreparedStatement insertIntoLocation;

    @Override
    public void close() throws SQLException {
//        private PreparedStatement queryLocationId;
//        private PreparedStatement queryLocation;
//        private PreparedStatement insertIntoLocation;
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
     * Inserts a location into the location table if it doesn't already exist.
     *
     * @param location The location to be inserted.
     * @return Returns the id of the location once it has been inserted or the id of the of the existing matching location.
     * @throws SQLException Throws an SLQException if the location could not be inserted.
     */
    public Location insertLocation(Location location) throws SQLException {
        if (getLocationId(location) == -1){
            insertIntoLocation.setString(1, location.getCountry());
            insertIntoLocation.setString(2, location.getState());
            insertIntoLocation.setString(3, location.getCity());
            insertIntoLocation.setString(4, location.getPostcode());
            int affectedRows = insertIntoLocation.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert location, updated more than one row.");
            } else {
                ResultSet generatedKeys = insertIntoLocation.getGeneratedKeys();
                if (generatedKeys.next()) {
                    location.setId(generatedKeys.getInt(1));
                    return location;
                }
                else
                    throw new SQLException("Couldn't get id from location after insert.");
            }
        } else {
            return location;
        }
    }

    @Override
    public void open(Connection conn) throws SQLException {
        queryLocationId = conn.prepareStatement(LocationDB.Query.LOCATION_ID, Statement.RETURN_GENERATED_KEYS);
        queryLocation = conn.prepareStatement(LocationDB.Query.LOCATION, Statement.RETURN_GENERATED_KEYS);
        insertIntoLocation = conn.prepareStatement(LocationDB.Insert.INSERT_LOCATION, Statement.RETURN_GENERATED_KEYS);
    }

    public void populateLocation(Location location) {
        try {
            queryLocation.setInt(1, location.getId());
            ResultSet results = queryLocation.executeQuery();
            if (results.next()) {
                location.setCountry(results.getString(COUNTRY));
                location.setCountry(results.getString(STATE));
                location.setCountry(results.getString(CITY));
                location.setCountry(results.getString(POSTCODE));
            }
        } catch (SQLException e) {
            System.out.println("Couldn't query location table: " + e.getMessage());
        }
    }

    public static class View {}

    public static class Column {
        public static final String ID = "id";
        public static final String COUNTRY = "country";
        public static final String STATE = "state";
        public static final String CITY = "city";
        public static final String POSTCODE = "postcode";
    }

    public static class Query {
        public static final String LOCATION_ID = "SELECT " + ID + " FROM " + NAME + " WHERE " + COUNTRY + " = ? AND " + STATE + " = ? AND " + CITY + " = ? AND " + POSTCODE + " = ?";
        public static final String LOCATION = "SELECT * FROM " + NAME + " WHERE " + ID + " = ?";
    }

    public static class Insert {
        public static final String INSERT_LOCATION = "INSERT INTO " + NAME + " (" + COUNTRY + ", " + STATE + ", " + CITY + ", " + POSTCODE + ") VALUES (?, ?, ?, ?)";
    }

    public static class Update {}

    public static class Delete {}
}
