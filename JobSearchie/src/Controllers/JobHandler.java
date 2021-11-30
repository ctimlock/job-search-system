package Controllers;

import entities.*;
import utilities.UserIO;
import utilities.Validate;

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

        return UserIO.enterAttribute("Job title", 4, 30);
    }

    public String enterCompany() {

        return UserIO.enterAttribute("Company name", 4, 30);
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

        return UserIO.menuSelectorValue("Please enter the category of the job:", options);
    }

    public Location enterLocation() {

        return new Location(
                UserIO.enterAttribute("Country", 4, 30),
                UserIO.enterAttribute("State", 1, 30),
                UserIO.enterAttribute("Suburb", 1, 30),
                UserIO.enterAttribute("Postcode", 1, 30)
        );
    }

    public String enterWorkType() {

        String[] options = {
                "Full time",
                "Part time",
                "Contract/ Temporary",
                "Casual/ Vacation"
        };

        return UserIO.menuSelectorValue("Please enter the type of work:", options);
    }

    public String enterWorkingArrangement() {

        String[] options = {
                "Office",
                "Remote",
                "Hybrid"
        };

        return UserIO.menuSelectorValue("Please enter the working arrangement:", options);
    }

    public String enterDescription() {

        return UserIO.enterAttribute("job description", 4, 2000);
    }

    public int enterCompensation() {

        UserIO.displayBody("Please enter the compensation level of this job or enter 0 if you don’t wish to include this information:");
        System.out.print("$");
        boolean flag = false;
        int input = -1;
        do {
            try {
                input = Integer.parseInt(UserIO.getInput().trim());
                if (input >= 0) {
                    flag = true;
                }
                else {
                    UserIO.displayBody("Compensation cannot be negative please re-enter");
                }
            }
            catch (Exception e) {
                UserIO.displayBody("Please enter an integer:");
            }
        }
        while (!flag);

        return input;
    }

    public String enterJobLevel() {

        String[] options = {
                "Entry level",
                "Mid level",
                "Senior level"
        };

        return UserIO.menuSelectorValue("Please enter the level of this job:", options);
    }

    public void advertiseJob(Job job) {
        String[] options = {
                "Yes",
                "No"
        };
        String userInput = UserIO.menuSelectorKey("Would you like to advertise this job?:", options);

        /*
        switch  (userInput) {
            case ("1") -> saveJob(job);
            case ("2") -> home();
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
