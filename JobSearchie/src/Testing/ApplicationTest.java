package Testing;

import Controllers.JobSeekerHandler;
import Database.DatabaseManager;
import Entities.Job;
import Entities.JobSeeker;

import javax.swing.*;

public class ApplicationTest
{
    public static void main(String[] args)
    {
        JFrame jFrame = new JFrame();
        JobSeekerHandler jobSeekerHandler = new JobSeekerHandler();
        JobSeeker seeker = new JobSeeker();
        Job job = new Job();
        DatabaseManager db = new DatabaseManager();
        jobSeekerHandler.applyForJob(seeker, db, job);
    }
}
