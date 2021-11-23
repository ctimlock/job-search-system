package database;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class UserDatabase extends DatabaseIO {


    public UserDatabase() throws IOException {
        super(new File(new java.io.File(".").getCanonicalPath() + "/JobSearchie/database/user.csv"));

    }

    public boolean doesEmailExist(String email) {
        return true;
    }

}
