package Controllers;

import entities.Job;
import entities.Location;
import entities.Recruiter;
import utilities.UserIO;

import java.sql.Date;
import java.util.ArrayList;

public class JobHandler {

    public JobHandler() {}

    public Job createJob(Recruiter recruiter){
        UserIO.displayHeading("Create a New Job");
        UserIO.displayBody("You are now required to enter the following requested information in order to create a job. At any time you wish to go back to the previous screen, please type ‘back’");
        String jobTitle = enterJobTitle();
        String company = enterCompany();
        ArrayList<String> categories = enterCategories();
        Location location = enterLocation();
        String workType = enterWorkType();
        String workingArrangement = enterWorkingArrangement();
        int compensation = enterCompensation();
        String jobLevel = enterJobLevel();
        String description = enterDescription();
        ArrayList<String> keywords = enterKeywords();
        boolean isAdvertised = enterIsAdvertised();
        Date advertiseDate = null;
        if (isAdvertised) {
            advertiseDate = new Date(System.currentTimeMillis());
        }
        UserIO.displayBody("You have now finished providing the required information.");

        return new Job(jobTitle, recruiter, new Date(System.currentTimeMillis()), advertiseDate, null, company, categories, location, workType, workingArrangement, compensation, jobLevel, description, isAdvertised, keywords);
    }


    public String enterJobTitle() {

        return UserIO.enterAttribute("Job title", 4, 30);
    }

    public String enterCompany() {

        return UserIO.enterAttribute("Company name", 4, 30);
    }

    public ArrayList<String> enterCategories() {
        ArrayList<String> categories = new ArrayList<>();
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
        categories.add(UserIO.menuSelectorValue("Please enter the category of the job:", options));

        String[] addAnother = {"Yes", "no"};
        String inputAdd = UserIO.menuSelectorKey("Would you like to add another category", addAnother);
        while (inputAdd.equals("0")) {
            categories.add(UserIO.menuSelectorValue("Please enter the category of the job:", options));
            inputAdd = UserIO.menuSelectorKey("Would you like to add another category", addAnother);
        }

        return categories;
    }

    public Location enterLocation() {

        String[] countries = {
                "Australia"
        };
        String country = UserIO.menuSelectorValue("Please select the country", countries);
        String[] states = {
                "ACT",
                "NSW",
                "NT",
                "QLD",
                "SA",
                "TAS",
                "VIC",
                "WA"
        };
        String state = UserIO.menuSelectorValue("Please select the state", states);
        String suburb = UserIO.enterAttribute("Suburb", 1, 30);
        String postcode = UserIO.enterAttribute("Postcode", 4, 4);

        return new Location(country, state, suburb, postcode);
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

    public boolean enterIsAdvertised() {

        String[] options = {
                "Yes",
                "No"
        };
        String userInput = UserIO.menuSelectorKey("Would you like to advertise this job?:", options);

        boolean isAdvertised;

        switch  (userInput) {
            case ("0") -> isAdvertised = true;
            case ("1") -> isAdvertised = false;
            default -> isAdvertised = false;
        }
        return isAdvertised;
    }

    public void saveJob(Job job) {}

    public ArrayList<String> enterKeywords() {

        ArrayList<String> keywords = new ArrayList<>();
        keywords.add(UserIO.enterAttribute("Keyword", 4, 30));
        String[] addAnother = {"Yes", "no"};
        String inputAdd = UserIO.menuSelectorKey("Would you like to add another keyword", addAnother);
        while (inputAdd.equals("0")) {
            keywords.add(UserIO.enterAttribute("Keyword", 4, 30));
            inputAdd = UserIO.menuSelectorKey("Would you like to add another keyword", addAnother);
        }

        return keywords;
    }

    public static void main(String[] args) {

        Recruiter recruiter = new Recruiter("James", "Bond", "james_bond@hotmail.com", "Abcabc123", new Date(System.currentTimeMillis()), "Seek Pty Ltd", "Computer Science", "0459797824", new Date(System.currentTimeMillis()));
        JobHandler jobHandler = new JobHandler();
        Job newJob = jobHandler.createJob(recruiter);
        UserIO.displayHeading("Please review the job details:");
        newJob.display();

    }

}
