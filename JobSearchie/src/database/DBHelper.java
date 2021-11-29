package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public interface DBHelper {
    /**
     * dateFormat is used to convert all dates through the DatabaseManager class. e.g., it can be used to convert the
     * string date of 29/11/2021 to a Date object. It is initialised in the constructor.
     */
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    void close() throws SQLException;

    void open(Connection conn) throws SQLException;
}
