package Controllers;

import database.DatabaseManager;
import database.FileManager;
import entities.Application;
import entities.Job;
import entities.JobSeeker;
import entities.Recruiter;
import utilities.UserIO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecruiterHandler extends UserHandler{

    public void home(Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Home Page");
        String[] options = {
                "Create a job listing",
                "View my jobs",
                "Messages (coming soon)",
                "Offer interview",
                "Search for a job seeker",
                "Profile management"
        };
        String userInput = UserIO.menuSelectorKey("Please enter one of the following:", options);

        switch (userInput) {
            case ("0") -> postJob(recruiter, db);
            case ("1") -> viewMyJobs(recruiter, db);
            case ("2") -> sendMessage(recruiter, db);
            //case ("3") -> offerInterview(recruiter, db);
            //case ("4") -> searchJobSeeker();
            case ("5") -> profileManagement(recruiter, db);
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    public void deleteJob(Recruiter recruiter, DatabaseManager db, Job job) throws SQLException, IOException {
        UserIO.displayHeading("Deleting job");
        UserIO.displayBody("This option is not yet implemented coming soon.");
        UserIO.displayBody("Returning to view my jobs.");
        viewMyJobsMenu(recruiter, db, job);
    }

    public void deleteProfile(Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {

        String[] options = {
                "Yes (coming soon)",
                "No",
                "Back",
                "Home"
        };
        String userInput = UserIO.menuSelectorKey("Are you sure you would like to delete your profile?:", options);

        switch (userInput) {
            case ("0") -> deleteProfileYes(recruiter, db);
            case ("1") -> deleteProfileNo(recruiter, db);
            case ("2") -> profileManagement(recruiter, db);
            case ("3") -> home(recruiter, db);

        }
    }

    public void deleteProfileNo(Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayBody("Your profile has NOT been deleted.");
        UserIO.displayBody("Returning to profile management");
        profileManagement(recruiter, db);
    }

    public void deleteProfileYes(Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayBody("This option is not yet implemented coming soon");
        UserIO.displayBody("Returning to profile management");
        profileManagement(recruiter, db);
    }

    public void postJob(Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {
        Job job = new JobHandler().createJob(recruiter);
        UserIO.displayHeading("Please review the job details:");
        job.display();

        String[] options = {
                "Yes",
                "Return home"
        };
        UserIO.displayHeading("Confirm Submission");
        String option = UserIO.menuSelectorKey("Please confirm if you like to submit this job?", options);

        switch (option) {
            case ("0") -> submitJobYes(recruiter, db, job);
            case ("1") -> submitJobNo(recruiter, db);
        }
    }

    public void profileManagement(Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Profile Management");
        String[] options = {
                "Update profile (coming soon)",
                "Delete profile",
                "Back",
                "Home",
        };
        String userInput = UserIO.menuSelectorKey("Please enter one of the following:", options);

        switch (userInput) {
            case ("0") -> updateProfile(recruiter, db);
            case ("1") -> deleteProfile(recruiter, db);
            case ("2") -> home(recruiter, db);
            case ("3") -> home(recruiter, db);
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    public void viewMyJobs(Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Viewing my jobs");
        ArrayList<Job> jobs = db.getAllJobs();
        ArrayList<String> optionsList = new ArrayList<>();
        for (Job job : jobs) {
            optionsList.add(job.getJobTitle() + " - " + job.getCompany() +" - "+ job.getDateListed());
        }
        String[] options = new String[optionsList.size()];
        options = optionsList.toArray(options);
        int userInput = Integer.parseInt(UserIO.menuSelectorKey("Please select the job you wish to view", options));
        Job job = jobs.get(userInput);
        UserIO.displayHeading("Displaying job details");
        job.display();
        viewMyJobsMenu(recruiter, db, job);
    }

    public void viewMyJobsMenu(Recruiter recruiter, DatabaseManager db, Job job) throws SQLException, IOException {

        UserIO.displayHeading("Options for job");

        String[] options = {
                "View job applicants",
                "Search for highly ranked job seekers",
                "Change advertising status of this job (coming soon)",
                "Update this job (coming soon)",
                "Delete this job (coming soon)",
                "Back",
                "Home"
        };

        String userInput = UserIO.menuSelectorKey("Please select an option", options);

        switch (userInput) {
            case ("0") -> viewJobApplicants(recruiter, db, job);
            case ("1") -> searchJobSeeker();
            case ("2") -> updateAdvertisingStatus(recruiter, db, job);
            case ("3") -> updateJob(recruiter, db, job);
            case ("4") -> deleteJob(recruiter, db, job);
            case ("5") -> viewMyJobs(recruiter, db);
            case ("6") -> home(recruiter, db);
        }
    }

    public void searchJobSeeker() {
        UserIO.displayBody("Please enter a role you would like to recruit, or enter home to return home");

        // Reads in jobseeker database
        // Select rows of job seekers open to job
        // Add filter (compensation, location, keyword)
        // Display results
        // Select Job Seeker
        // Options on Job Seeker
    }

    public JobSeeker selectJobSeeker() {
        return new JobSeeker();
    }

    public ArrayList<JobSeeker> filterJobSeeker() {
        return new ArrayList<JobSeeker>();
    }

    public void menuJobSeeker() {

        String[] options = {
                "Read resume",
                "Read cover letter",
                "Send message",
                "Offer interview",
                "Report job seeker to admin",
                "Remove job seeker from search",
                "Home"
        };
        String userInput = UserIO.menuSelectorKey("Please enter one of the following:", options);

        /*
        switch (userInput) {
            case ("1") -> readResume();
            case ("2") -> readCoverLetter();
            case ("3") -> sendMessage();
            case ("4") -> offerInterview();
            case ("5") -> reportJobSeeker();
            case ("6") -> removeJobSeeker();
            case ("7") -> home();
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
        */
    }

    public void sendMessage (Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Sending message");
        UserIO.displayBody("This option is not yet implemented coming soon");
        UserIO.displayBody("Returning to home.");
        home(recruiter, db);
    }

    public void submitJobYes(Recruiter recruiter, DatabaseManager db, Job job) throws SQLException, IOException {
        db.insertJob(job);
        UserIO.displayBody("You have submitted the job. Thank you for using Job Searchie.");
        UserIO.displayBody("Returning to home screen now");
        home(recruiter, db);
    }

    public void submitJobNo(Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayBody("You have not submitted");
        UserIO.displayBody("Returning to home screen now");
        home(recruiter, db);
    }

    public void updateAdvertisingStatus (Recruiter recruiter, DatabaseManager db, Job job) throws SQLException, IOException {
        UserIO.displayHeading("Updating job advertising status");
        UserIO.displayBody("This option is not yet implemented coming soon");
        UserIO.displayBody("Returning to view my jobs.");
        viewMyJobsMenu(recruiter, db, job);
    }

    public void updateJob (Recruiter recruiter, DatabaseManager db, Job job) throws SQLException, IOException {
        UserIO.displayHeading("Updating job details");
        UserIO.displayBody("This option is not yet implemented coming soon");
        UserIO.displayBody("Returning to view my jobs.");
        viewMyJobsMenu(recruiter, db, job);
    }


    public void updateProfile(Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Updating profile");
        UserIO.displayBody("This option is not yet implemented coming soon");
        UserIO.displayBody("Returning to profile management");
        profileManagement(recruiter, db);
    }

    public void viewJobApplicants(Recruiter recruiter, DatabaseManager db, Job job) throws SQLException, IOException {
        UserIO.displayHeading("Displaying applicants for " + job.getJobTitle());
        ArrayList<Application> applications = db.getJobApplications(job);
        for (Application application: applications) {
            UserIO.displayBody("id: " + application.getId());
            UserIO.displayBody("Cover letter:" + application.getCoverLetterDir());
            UserIO.displayBody("Resume: " + application.getResumeDir());
            UserIO.displayBody("Application date: " + application.getApplicationDate());
            System.out.println();
            //FileManager.openFileFromJS("resume", application.getId() + application.getResumeDir());
        }
        viewMyJobsMenu(recruiter, db, job);

    }

}
