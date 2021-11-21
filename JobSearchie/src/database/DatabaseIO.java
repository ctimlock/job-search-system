package database;

import java.io.File;
import java.io.IOException;

public class DatabaseIO {
    private String cwd;


    public DatabaseIO() throws IOException {
        cwd = new String(new File(".").getCanonicalPath());
    }
}
