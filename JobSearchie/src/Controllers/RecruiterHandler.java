package Controllers;

import entities.JobSeeker;
import utilities.UserIO;

import java.util.ArrayList;
import java.util.HashMap;

public class RecruiterHandler extends UserHandler{

    public void home() {
        UserIO.displayBody("Please enter one of the follow options:");
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "Create a job listing");
        map.put("2", "View my jobs");
        map.put("3", "Messages");
        map.put("4", "Search for a job seeker");
        map.put("5", "Profile management");
        UserIO.displayOptions(map);
        String userInput = map.get(UserIO.getInput());

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
        UserIO.displayBody("Please enter one of the follow options:");
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "Update profile");
        map.put("2", "Delete profile");
        map.put("3", "Home");
        UserIO.displayOptions(map);
        String userInput = map.get(UserIO.getInput());

        switch (userInput) {
            case ("1") -> updateProfile();
            case ("2") -> deleteProfile();
            case ("3") -> home();
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    public void deleteProfile() {
        UserIO.displayBody("Are you sure you would like to delete your profile?:");
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "Yes");
        map.put("2", "No");
        UserIO.displayOptions(map);
        String userInput = map.get(UserIO.getInput());

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
        UserIO.displayBody("Please enter one of the following:");
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "Read resume");
        map.put("2", "Read cover letter");
        map.put("3", "Send message");
        map.put("4", "Offer interview");
        map.put("5", "Report job seeker to admin");
        map.put("6", "Remove job seeker from search");
        map.put("7", "Home");
        UserIO.displayOptions(map);
        String userInput = map.get(UserIO.getInput());

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
