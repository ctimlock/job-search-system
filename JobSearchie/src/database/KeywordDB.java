package database;

import static database.KeywordDB.Column.*;

public class KeywordDB {

    public static final String NAME = "keyword";

    public static class Column {
        public static final String ID = "id";
        public static final String KEYWORD = "keyword";
    }

    public static class Query {
        public static final String KEYWORD_ID = "SELECT " + ID + " FROM " + NAME + " WHERE " + Column.KEYWORD + " = ?";
        public static final String KEYWORD = "SELECT * FROM " + NAME + " WHERE " + ID + " = ?";
    }

    public static class Insert {
        public static final String INSERT_KEYWORD = "INSERT INTO " + NAME + " (" + KEYWORD + ") VALUES (?)";
    }

    public static class Update {}

    public static class Delete {}
}
