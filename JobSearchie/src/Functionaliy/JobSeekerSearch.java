package Functionaliy;

import Controllers.JobSeekerHandler;
import database.DatabaseManager;
import entities.Job;
import entities.JobSeeker;
import entities.Recruiter;
import entities.Session;

import java.sql.Date;
import java.sql.SQLException;

import static testing.RandomGen.*;

public class JobSeekerSearch {
    private Session session;
    private DatabaseManager db;

    public static void main(String[] args) throws SQLException {
        JobSeekerSearch program = new JobSeekerSearch();
        program.session = new Session();
        program.db = new DatabaseManager();
        program.run();
    }

    public void run() throws SQLException {
        JobSeeker js = getRandomJobSeeker();
        db.insertJobSeeker(js);
        session.setUserLoggedIn(js);
        db.insertSession(session);
        JobSeekerHandler jsh = new JobSeekerHandler();
        //insertFiftyJobs();

        //jsh.jobSearch();

        session.setLogoutTime(new Date(System.currentTimeMillis()));
        db.updateSession(session);
    }

    public void insertFiftyJobs() throws SQLException {
        //This is for testing purposes only
        for(int i = 0; i < 10; i++) {
            Recruiter recruiter = getRandomRecruiter();
            db.insertRecruiter(recruiter);
            for(int j = 0; j < 5; j++) {
                Job job = getRandomJob(recruiter);
                db.insertJob(job);
            }
        }

    }
}
