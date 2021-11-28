package Controllers;

import database.DatabaseIO;
import entities.*;
import utilities.UserIO;

import java.io.FileReader;
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

        String[] options = {
                "Agricultural Forestry and Fishing",
                "Mining",
                "Manufacturing",
                "Electricity Gas Water and Waste Services",
                "Construction",
                "Wholesale Trade",
                "Retail Trade",
                "Accommodation and Food Services",
                "Transport Postal and Warehousing",
                "Information Media and Telecommunications",
                "Financial and Insurance Services",
                "Rental Hiring and Real Estate Services",
                "Professional Scientific and Technical Services",
                "Administrative and Support Services",
                "Public Administration and Safety",
                "Education and Training",
                "Health Care and Social Assistance",
                "Arts and Recreation Services",
                "Other Services"
        };
        return UserIO.menuSelector("Please enter the category of the job:", options);
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

        String[] options = {
                "Full time",
                "Part time",
                "Contract/ Temporary",
                "Casual/ Vacation"
        };

        return UserIO.menuSelector("Please enter the type of work:", options);
    }

    public String enterWorkingArrangement() {

        String[] options = {
                "Office",
                "Remote",
                "Hybrid"
        };

        return UserIO.menuSelector("Please enter the working arrangement:", options);
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

        String[] options = {
                "Entry level",
                "Mid level",
                "Senior level"
        };

        return UserIO.menuSelector("Please enter the level of this job:", options);
    }

    public void advertiseJob(Job job) {
        String[] options = {
                "Yes",
                "No"
        };
        String userInput = UserIO.menuSelectorSwitch("Would you like to advertise this job?:", options);

        /*
        switch  (userInput) {
            case ("1") -> saveJob(job);
            case ("2") -> ;
            default ->
        }
        */

    }

    public void saveJob(Job job) {

        String jobTitle = job.getJobTitle();


        // DatabaseIO dbio = new DatabaseIO();

    }

    public static void main(String[] args) {

        //DatabaseIO db = new DatabaseIO(new FileReader("category.csv"));
        JobHandler jobHandler = new JobHandler();
        Job newJob = jobHandler.createJob();
        //System.out.println("Please review the job details:");
        //newJob.display();
        //newJob.setAdvertisingStatus(jobHandler.enterAdvertisingStatus());
    }

}
