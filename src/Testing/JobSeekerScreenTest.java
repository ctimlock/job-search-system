package Testing;

import Controllers.JobSeekerHandler;
import Database.DatabaseManager;
import Entities.JobSeeker;

import java.io.IOException;
import java.sql.SQLException;

public class JobSeekerScreenTest {

    public static void main (String[] args) throws SQLException, IOException {
        DatabaseManager db = new DatabaseManager();
        JobSeeker jobSeeker = db.getJobSeeker("yang_chris@outlook.com.au");
        JobSeekerHandler jobSeekerHandler = new JobSeekerHandler();
        jobSeekerHandler.home(jobSeeker, db);
    }

}
