package database;

import java.io.File;
import java.io.IOException;


public class JobDatabase extends DatabaseIO {

    public JobDatabase() throws IOException {

        super(new File(new java.io.File(".").getCanonicalPath() + "/JobSearchie/database/job.csv"));
    }

    public boolean doesJobExist(String job) {

        return true;
    }

}
