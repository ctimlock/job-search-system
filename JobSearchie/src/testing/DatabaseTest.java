package testing;

import database.DatabaseManager;
import entities.Application;
import entities.Job;
import entities.JobSeeker;
import entities.Recruiter;

import java.sql.SQLException;

public class DatabaseTest {
    private DatabaseManager db;

    private Application insertAndGetApplication(Job job, JobSeeker jobSeeker) {
        Application application = RandomGen.getRandomApplication(job, jobSeeker);
        try {
            return db.insertApplication(application);
        } catch (SQLException e) {
            System.out.println("Error inserting application" + e.getMessage());
            application.display();
            System.exit(1);
            return null;
        }
    }

    private Job insertAndGetJob(Recruiter recruiter) {
        Job job = RandomGen.getRandomJob(recruiter);
        try {
            return db.insertJob(job);
        } catch (SQLException e) {
            System.out.println("Error inserting job" + e.getMessage());
            job.display();
            System.exit(1);
            return null;
        }
    }

    private JobSeeker insertAndGetJobSeeker() {
        JobSeeker jobSeeker = RandomGen.getRandomJobSeeker();
        try {
            return db.insertJobSeeker(jobSeeker);
        } catch (SQLException e) {
            System.out.println("Error inserting job seeker" + e.getMessage());
            jobSeeker.display();
            System.exit(1);
            return null;
        }
    }

    private Recruiter insertAndGetRecruiter() {
        Recruiter recruiter = RandomGen.getRandomRecruiter();
        try {
            return db.insertRecruiter(recruiter);
        } catch (SQLException e) {
            System.out.println("Error inserting recruiter" + e.getMessage());
            recruiter.display();
            System.exit(1);
            return null;
        }
    }

    private void insertApplication(Application application) {
        try {
            db.insertApplication(application);
        } catch (SQLException e) {
            System.out.println("Error inserting application" + e.getMessage());
            application.display();
            System.exit(1);
        }

    }

    private void insertManyJobApplications() {
        for (int i = 0; i < 10; i++) {
            Recruiter recruiter = insertAndGetRecruiter();
            Job job = insertAndGetJob(recruiter);

            for (int j = 0; j < 3; j++) {
                JobSeeker jobSeeker = insertAndGetJobSeeker();
                insertApplication(RandomGen.getRandomApplication(job, jobSeeker));
            }

        }
    }

    public void getAllApplicationsById() {
        Job job = db.getJob(146);

        System.out.println(job.getJobTitle());
        System.out.println(db.getJobApplications(job).size());
    }

    public void testGetJob() {
        try {
            for (int i = 0; i < 10; i++) {
                Recruiter recruiter = RandomGen.getRandomRecruiter();
                Job job1 = RandomGen.getRandomJob(recruiter);
                Job job2 = db.insertJob(job1);
                System.out.println(job2.getDateCreated());
                if (!job1.getJobTitle().equals(job2.getJobTitle())) {
                    System.out.println("Titles don't match");
                    throw new Exception("TEST getJob: FAILED");
                }
                if (!job1.getDateCreated().equals(job2.getDateCreated())) {
                    System.out.println("Date created doesn't match");
                    throw new Exception("TEST getJob: FAILED");
                }
                if (job1.getDateListed() != job2.getDateListed()) {
                    System.out.println("Date listed doesn't match");
                    throw new Exception("TEST getJob: FAILED");
                }
                if (job1.getDateDeListed() != job2.getDateDeListed()) {
                    System.out.println("Date de listed doesn't match");
                    throw new Exception("TEST getJob: FAILED");
                }
            }
            System.out.println("TEST 'getJob': PASSED");
        } catch (SQLException throwables) {
            System.out.println("Error getting job");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        DatabaseTest dbt = new DatabaseTest();
        dbt.db = new DatabaseManager();
        dbt.run();
    }

    private void run() {
        insertManyJobApplications();
        db.close();
    }


}
