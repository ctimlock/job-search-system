package database;

import static database.UserKeywordDB.Column.*;

public class UserKeywordDB {

    public static final String NAME = "user_keyword";

    public static class View{}

    public static class Column{
        public static final String USERID = "userId";
        public static final String KEYWORDID = "keywordId";
    }
    public static class Query{
        public static final String CHECK_USER_KEYWORD = "SELECT * FROM " + NAME + " WHERE " + USERID + " = ? AND " + KEYWORDID + " = ?";
        public static final String USER_KEYWORD = "SELECT * FROM " + NAME + " WHERE " + USERID + " = ?";

    }
    public static class Insert{
        public static final String USER_KEYWORD = "INSERT INTO " + NAME + " (" + USERID + ", " + KEYWORDID + ") VALUES (?, ?)";

    }
    public static class Update{}
    public static class Delete{}
}
