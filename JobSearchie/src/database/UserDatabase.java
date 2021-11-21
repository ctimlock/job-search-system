package database;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class UserDatabase extends DatabaseIO {
    File directory = new File(new java.io.File(".").getCanonicalPath() + "/JobSearchie/database/user.csv");

    public void readDatabase() {
        try {
            Scanner sc = new Scanner(directory);
            while (sc.hasNextLine())
                System.out.println(sc.nextLine());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public UserDatabase() throws IOException {
    }


    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }
}
