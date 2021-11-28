package Controllers;

import entities.JobSeeker;
import utilities.UserIO;

import java.util.ArrayList;
import java.util.HashMap;

public class RecruiterHandler extends UserHandler{

    public void home() {

        String[] options = {
                "Create a job listing",
                "View my jobs",
                "Messages",
                "Offer interview",
                "Search for a job seeker",
                "Profile management"
        };
        String userInput = UserIO.menuSelectorSwitch("Please enter one of the following:", options);

        switch (userInput) {
            case ("1") -> new JobHandler().createJob();
            //case ("2") -> viewMyJobs();
            //case ("3") -> new MessageHandler().viewMessage();
            //case ("4") -> searchJobSeeker();
            case ("5") -> profileManagement();
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    public void profileManagement() {

        String[] options = {
                "Update profile",
                "Delete profile",
                "Home",
        };
        String userInput = UserIO.menuSelectorSwitch("Please enter one of the following:", options);

        switch (userInput) {
            case ("1") -> updateProfile();
            case ("2") -> deleteProfile();
            case ("3") -> home();
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    public void deleteProfile() {

        String[] options = {
                "Yes",
                "No"
        };
        String userInput = UserIO.menuSelectorSwitch("Are you sure you would like to delete your profile?:", options);

        switch (userInput) {
            case ("1") -> deleteProfileYes();
            case ("2") -> deleteProfileNo();
            case ("3") -> home();
            default -> throw new IllegalStateException("Unexpected value: " + userInput);

        }
    }

    public void deleteProfileNo() {
        UserIO.displayBody("Your profile has NOT been deleted.");
        profileManagement();
    }

    public void deleteProfileYes() {
        UserIO.displayBody("Your profile has been deleted. You will now be redirected to the JobSearchie welcome screen");
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
        String userInput = UserIO.menuSelectorSwitch("Please enter one of the following:", options);

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

    public void updateProfile() {
        UserIO.displayBody("Please enter the number corresponding to the field you would like to change:");
    }

    public static void main(String[] args) {


    }

}
