package testing;

import database.DatabaseManager;
import entities.*;

import java.sql.Date;
import java.sql.SQLException;

public class DatabaseTest {
    private void run() throws SQLException {
        System.out.println(RandomGen.getRandomPhoneNumber());
        System.exit(1);
        DatabaseManager db = new DatabaseManager();

        Recruiter recruiter = RandomGen.getRandomRecruiter();
        JobSeeker jobSeeker = RandomGen.getRandomJobSeeker();
        Job job = RandomGen.getRandomJob();
        db.insertJob(job);

        System.out.println(job.getId());


        Application app = RandomGen.getRandomApplication();

        Invitation invitation = new Invitation();
        invitation.setJob(job);
        invitation.setLocationOfInterview(RandomGen.getRandomLocation());
        invitation.setJobSeeker(jobSeeker);
        invitation.setRecruiter(recruiter);
        invitation.setAccepted(false);
        invitation.setAttachedMessage("Hey we would like to consider you for this job, let arrange a time to meet!.");
        invitation.setDateOfInterview(new Date(2022-1900+10+15));
        invitation.setDateSent(new Date(System.currentTimeMillis()));
        db.insertInvitation(invitation);

        System.out.println(invitation.getId());

        Invitation invi = db.getInvitation(1);

        System.out.println(invi.getJob().getJobTitle());


        db.close();
    }

    public static void main(String[] args) throws SQLException {
        new DatabaseTest().run();
    }


}
