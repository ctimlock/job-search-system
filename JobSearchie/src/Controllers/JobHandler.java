package Controllers;

import entities.*;
import utilities.UserIO;

import java.util.*;

public class JobHandler {

    public JobHandler() {}

    public Job createJob(){
        UserIO.displayHeading("Create a New Job");
        UserIO.displayBody("You are now required to enter the following requested information in order to create a job. At any time you wish to go back to the previous screen, please type ‘back’");
        //Get next job id from database.
        String jobTitle = enterJobTitle();
        String company = enterCompany();
        String category = enterCategory();
        Location location = enterLocation();
        String workType = enterWorkType();
        String workingArrangement = enterWorkingArrangement();
        int compensation = enterCompensation();
        //TODO: add in keywords and category.
        String jobLevel = enterJobLevel();
        String description = enterDescription();
        UserIO.displayBody("You have now finished providing the required information.");

        return new Job();
    }

    public String enterJobTitle() {

        UserIO.displayBody("Please enter the name of the job that you would like to create:");

        return UserIO.getInput();
    }

    public String enterCompany() {

        UserIO.displayBody("Please enter the name of the company:");

        return UserIO.getInput();
    }

    public String enterCategory() {

        UserIO.displayBody("Please enter the category of the job:");
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "Banking and Financial Services");
        map.put("2", "Construction");
        map.put("3", "Education");
        map.put("4", "Health");
        map.put("5", "Hospitality and Entertainment");
        map.put("6", "Technology");
        UserIO.displayOptions(map);

        return map.get(UserIO.getInput());
    }

    public Location enterLocation() {

        UserIO.displayBody("Please enter the country:");
        String country = UserIO.getInput();
        UserIO.displayBody("Please enter the state:");
        String state = UserIO.getInput();
        UserIO.displayBody("Please enter the suburb:");
        String suburb = UserIO.getInput();
        UserIO.displayBody("Please enter the post code:");
        String postcode = UserIO.getInput();

        return new Location(country, state, suburb, postcode);
    }

    public String enterWorkType() {

        UserIO.displayBody("Please enter the type of work (Full time, Part time, Contract/ Temp, Casual/ Vacation):");
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "Full time");
        map.put("2", "Part time");
        map.put("3", "Contract/ Temporary");
        map.put("4", "Casual/ Vacation");
        UserIO.displayOptions(map);

        return map.get(UserIO.getInput());
    }

    public String enterWorkingArrangement() {

        UserIO.displayBody("Please enter the working arrangement (Work from home, Office, or Combination):");
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "Office");
        map.put("2", "Work from home");
        map.put("3", "Combination");
        UserIO.displayOptions(map);

        return map.get(UserIO.getInput());
    }

    public String enterDescription() {

        UserIO.displayBody("Please enter a description of this job:");

        return UserIO.getInput();
    }

    public int enterCompensation() {

        UserIO.displayBody("Please enter the compensation level of this job or leave blank if you don’t wish to include this information:");
        System.out.print("$");

        return Integer.parseInt(UserIO.getInput());
    }

    public String enterJobLevel() {

        UserIO.displayBody("Please enter the level of this job:");
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "Entry level");
        map.put("2", "Mid level");
        map.put("3", "Senior level");
        UserIO.displayOptions(map);

        return map.get(UserIO.getInput());
    }

    public String enterAdvertisingStatus() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "Save this job and advertise it.");
        map.put("2", "Save this job but don’t advertise it.");
        map.put("3", "Return home without saving.");
        UserIO.displayOptions(map);

        return map.get(UserIO.getInput());
    }

    public static void main(String[] args) {

        JobHandler jobHandler = new JobHandler();
        Job newJob = jobHandler.createJob();
        System.out.println("Please review the job details:");
        newJob.display();
        newJob.setAdvertisingStatus(jobHandler.enterAdvertisingStatus());
    }

}
