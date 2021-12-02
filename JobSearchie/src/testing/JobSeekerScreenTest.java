package testing;

import Controllers.JobSeekerHandler;
import database.DatabaseManager;
import entities.JobSeeker;

import java.io.IOException;
import java.sql.SQLException;

public class JobSeekerScreenTest {

    public static void main (String[] args) throws SQLException, IOException {
        DatabaseManager db = new DatabaseManager();
        JobSeeker jobSeeker = new JobSeeker();
        JobSeekerHandler jobSeekerHandler = new JobSeekerHandler();
        jobSeekerHandler.home(jobSeeker, db);
    }

}
