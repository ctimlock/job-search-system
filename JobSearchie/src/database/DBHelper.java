package database;

import java.sql.SQLException;

public interface DBHelper {
    void close() throws SQLException;
}
