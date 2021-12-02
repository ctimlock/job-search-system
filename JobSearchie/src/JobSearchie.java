import Controllers.JobHandler;
import database.DatabaseManager;
import entities.*;
import utilities.UserIO;
import utilities.Validate;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class JobSearchie
{
    private Session session;

    public static void main(String[] args) throws SQLException
    {
        JFrame frameInit = new JFrame();
        JobSearchie program = new JobSearchie();
        program.session = new Session();
        program.run();
    }

    public void run() throws SQLException
    {
        DatabaseManager db = new DatabaseManager();
        if(!db.open())
        {
            System.out.println("Can't open database");
            return;
        }

        Location loc = new Location("Australia", "Tasmania", "Trowutta", "7330");
        ArrayList<String> keywords = new ArrayList<>();
        keywords.add("Accounting");
        keywords.add("Economics");
        keywords.add("Study");
        keywords.add("Focus");

        ArrayList<String> categories = new ArrayList<>();
        categories.add("Accounting");
        categories.add("Economics");
        categories.add("Study");
        categories.add("Accounting");

        Recruiter recruiter = new Recruiter("James", "Bond", "james_bond@hotmail.com", "Abcabc123", new Date(System.currentTimeMillis()), "Seek Pty Ltd", "Computer Science", "0459797824", new Date(System.currentTimeMillis()));
        JobSeeker jobSeeker = new JobSeeker("Jim", "levinstine", "jim_levinstine@icloud.com", "NONEHAHA", new Date(System.currentTimeMillis()), "CEO", "Execuitive", "0456789845", "", loc, new Date(System.currentTimeMillis()), keywords, 95000);
        Job job = new Job("Hiphop rapper", recruiter, new Date(System.currentTimeMillis()), null, null, "Seek Pty Ltf", categories, loc, "Full Time", "WFH", 95625, "Entry Level", "Excellent opportunity as an entry level software developer", true, keywords);
        try {
            db.insertJob(job);
        } catch (SQLException e) {
            System.out.println("Could not insert Job: " + e.getMessage());
        }
        System.out.println(job.getId());


        Application app = new Application();
        app.setJob(job);
        app.setResumeDir("resumeDirHere");
        app.setCoverLetterDir("coverLetterDirHere");
        app.setJobSeeker(jobSeeker);
        app.setApplicationDate(new Date(System.currentTimeMillis()));
        app.setStatus("Pending");
        db.insertApplication(app);
        System.out.println("Here");
        ArrayList<Job> allJobs = db.getAllJobs();
        System.out.println(allJobs.size());

        Invitation invitation = new Invitation();
        invitation.setJob(job);
        invitation.setLocationOfInterview(loc);
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

    public void exit() {
        System.exit(1);
    }
}
