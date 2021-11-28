package database;

import static database.LocationDB.Column.*;

public class LocationDB {
    public static final String NAME = "location";

    public static class View{}

    public static class Column{
        public static final String ID = "id";
        public static final String COUNTRY = "country";
        public static final String STATE = "state";
        public static final String CITY = "city";
        public static final String POSTCODE = "postcode";
    }

    public static class Query{
        public static final String LOCATION_ID = "SELECT " + ID + " FROM " + NAME + " WHERE " + COUNTRY + " = ? AND " + STATE + " = ? AND " + CITY + " = ? AND " + POSTCODE + " = ?";
    }
    public static class Insert{
        public static final String INSERT_LOCATION = "INSERT INTO " + NAME + " (" + COUNTRY + ", " + STATE + ", " + CITY + ", " + POSTCODE + ") VALUES (?, ?, ?, ?)";
    }

    public static class Update{}
    public static class Delete{}
}
