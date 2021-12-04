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

/**
 * Controller class for the Recruiter entity.
 *
 * @author Charlie Timlock, Levi Quilliam, Tim Perkins, and Merrill Nguyen
 * @version 1.0
 */
public class RecruiterHandler extends UserHandler{

    /**
     * Creates the home screen for the recruiuter.
     *
     * @param recruiter the Recruiter user for the home screen.
     * @param db the DatabaseManager.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void home(Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {
        boolean flag = true;
        do
        {
            UserIO.displayHeading("Home Page");
            String[] options = {
                    "Create a job listing",
                    "View my jobs",
                    "Messages (coming soon)",
                    "Offer interview (coming soon)",
                    "Search for a job seeker (coming soon)",
                    "Profile management",
                    "Log Out",
            };
            String userInput = UserIO.menuSelectorKey("Please enter one of the following:", options);

            switch (userInput) {
                case ("0") -> postJob(recruiter, db);
                case ("1") -> viewMyJobs(recruiter, db);
                case ("2") -> sendMessage(recruiter, db);
                //case ("3") -> offerInterview(recruiter, db);
                //case ("4") -> searchJobSeeker();
                case ("5") -> profileManagement(recruiter, db);
                case ("6") -> {
                    flag = false;
                    UserIO.displayBody("Logging out.");
                }
                default -> throw new IllegalStateException("Unexpected value: " + userInput);
            }
        } while (flag);
    }

    /**
     * Method option to delete a job.
     *
     * @param recruiter The Recruiter using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @param job The Job to delete.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void deleteJob(Recruiter recruiter, DatabaseManager db, Job job) throws SQLException, IOException {
        UserIO.displayHeading("Deleting job");
        UserIO.displayBody("This option is not yet implemented coming soon.");
        UserIO.displayBody("Returning to view my jobs.");
        viewMyJobsMenu(recruiter, db, job);
    }

    /**
     * Creates the screen to ask the user if they would like to delete a profile.
     *
     * @param recruiter The Recruiter using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void deleteProfile(Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {

        String[] options = {
                "Yes (coming soon)",
                "No",
                "Back"
        };
        String userInput = UserIO.menuSelectorKey("Are you sure you would like to delete your profile?:", options);

        switch (userInput) {
            case ("0") -> deleteProfileYes(recruiter, db);
            case ("1") -> deleteProfileNo(recruiter, db);
            case ("2") -> {UserIO.displayBody("Returning to Profile Management");}

        }
    }

    /**
     * Method option to not delete a profile.
     *
     * @param recruiter The Recruiter using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void deleteProfileNo(Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayBody("Your profile has NOT been deleted.");
        UserIO.displayBody("Returning to profile management");
        profileManagement(recruiter, db);
    }

    /**
     * Method option to not delete a profile.
     *
     * @param recruiter The Recruiter using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void deleteProfileYes(Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayBody("This option is not yet implemented coming soon");
        UserIO.displayBody("Returning to profile management");
        profileManagement(recruiter, db);
    }

    /**
     * Method to ask the recruiter to review their job posting and then post their job.
     *
     * @param recruiter The Recruiter using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
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

    /**
     * Creates the profile management screen.
     *
     * @param recruiter The Recruiter using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void profileManagement(Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {
        Boolean flag = true;

        do
        {
            UserIO.displayHeading("Profile Management");
            String[] options = {
                    "Update profile (coming soon)",
                    "Delete profile",
                    "Home",
            };
            String userInput = UserIO.menuSelectorKey("Please enter one of the following:", options);

            switch (userInput) {
                case ("0") -> updateProfile(recruiter, db);
                case ("1") -> deleteProfile(recruiter, db);
                case ("2") -> {
                    UserIO.displayBody("Returning to Home.");
                    flag = false;
                }
                default -> throw new IllegalStateException("Unexpected value: " + userInput);
            }
        } while (flag);
    }

    /**
     * Method to view jobs posted.
     *
     * @param recruiter The Recruiter using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
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

    /**
     * Creates the screen for the recruiter to select an option on their job.
     *
     * @param recruiter The Recruiter using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @param job The Job to view menu.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void viewMyJobsMenu(Recruiter recruiter, DatabaseManager db, Job job) throws SQLException, IOException
    {
    boolean flag = true;
    do
    {
        UserIO.displayHeading("Options for jobs");

        String[] options = {
                "View job applicants",
                "Search for highly ranked job seekers (coming soon)",
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
            case ("6") -> {
                UserIO.displayBody("Returning to the Home page.");
                flag = false;
            }
        }
    } while (flag);

    }

    /**
     * Method to search for a job seeker.
     */
    public void searchJobSeeker() {
        UserIO.displayBody("Please enter a role you would like to recruit, or enter home to return home");

        // Reads in jobseeker database
        // Select rows of job seekers open to job
        // Add filter (compensation, location, keyword)
        // Display results
        // Select Job Seeker
        // Options on Job Seeker
    }

    /**
     * Method to select a job seeker.
     *
     * @return JobSeeker object.
     */
    public JobSeeker selectJobSeeker() {
        return new JobSeeker();
    }

    /**
     * Method to filter job seekers.
     *
     * @return ArrayList<JobSeeker> of job seekers.
     */
    public ArrayList<JobSeeker> filterJobSeeker() {
        return new ArrayList<JobSeeker>();
    }

    /**
     * Creates screen to ask the recruiter on what to do with job seeker who applied for the job.
     */
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

    /**
     * Creates the screen for the recruiter to send a message to a job seeker.
     *
     * @param recruiter The Recruiter using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void sendMessage (Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Sending message");
        UserIO.displayBody("This option is not yet implemented coming soon");
        UserIO.displayBody("Returning to home.");
    }

    /**
     * Method to submit the job.
     *
     * @param recruiter The Recruiter using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @param job The Job to submit to database.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void submitJobYes(Recruiter recruiter, DatabaseManager db, Job job) throws SQLException, IOException {
        db.insertJob(job);
        UserIO.displayBody("You have submitted the job. Thank you for using Job Searchie.");
        UserIO.displayBody("Returning to home screen now");
    }

    /**
     * Method to not submit the job.
     *
     * @param recruiter The Recruiter using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void submitJobNo(Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayBody("You have not submitted");
        UserIO.displayBody("Returning to home screen now");
    }

    /**
     * Method to update advertising status.
     *
     * @param recruiter The Recruiter using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @param job The Job to set advertising status.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void updateAdvertisingStatus (Recruiter recruiter, DatabaseManager db, Job job) throws SQLException, IOException {
        UserIO.displayHeading("Updating job advertising status");
        UserIO.displayBody("This option is not yet implemented coming soon");
        UserIO.displayBody("Returning to view my jobs.");
        viewMyJobsMenu(recruiter, db, job);
    }

    /**
     * Method to update job details.
     *
     * @param recruiter The Recruiter using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @param job The Job to update.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void updateJob (Recruiter recruiter, DatabaseManager db, Job job) throws SQLException, IOException {
        UserIO.displayHeading("Updating job details");
        UserIO.displayBody("This option is not yet implemented coming soon");
        UserIO.displayBody("Returning to view my jobs.");
        viewMyJobsMenu(recruiter, db, job);
    }

    /**
     * Method to update profile.
     *
     * @param recruiter The Recruiter using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void updateProfile(Recruiter recruiter, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Updating profile");
        UserIO.displayBody("This option is not yet implemented coming soon");
        UserIO.displayBody("Returning to profile management");
        profileManagement(recruiter, db);
    }

    /**
     * Method to view job applicants for a job.
     *
     * @param recruiter The Recruiter using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @param job The Job to view applicants.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void viewJobApplicants(Recruiter recruiter, DatabaseManager db, Job job) throws SQLException, IOException {
        UserIO.displayHeading("Displaying applicants for " + job.getJobTitle());
        ArrayList<Application> applications = db.getJobApplications(job);
        for (Application application: applications) {
            UserIO.displayBody("id: " + application.getId());
            UserIO.displayBody("Cover letter:" + application.getCoverLetterDir());
            UserIO.displayBody("Resume: " + application.getResumeDir());
            UserIO.displayBody("Application date: " + application.getApplicationDate());
            UserIO.displayBody("Recruiter ID: " + application.getJob().getAuthor().getEmail());
            UserIO.displayBody("Applicant: " + application.getJobSeeker().getEmail());
            System.out.println();
            //FileManager.openFileFromJS("resume", application.getId() + application.getResumeDir());
        }
        viewMyJobsMenu(recruiter, db, job);
    }

}
