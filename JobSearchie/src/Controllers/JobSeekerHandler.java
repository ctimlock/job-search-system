package Controllers;

import database.DatabaseManager;
import entities.JobSeeker;
import utilities.UserIO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;



public class JobSeekerHandler extends UserHandler {

    public void home(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayTitle("Home");
        String[] options = {
                "Search for a job",
                "View watchlist (coming soon)",
                "Messages (coming soon)",
                "View pending job applications (coming soon)",
                "View job interviews",
                "Profile management",
                "Log out"
        };

        String userInput = UserIO.menuSelectorKey("Please enter one of the above options:", options);

        switch (userInput) {
            case ("0") -> jobSearch(jobSeeker, db);
            case ("1") -> watchlist(jobSeeker, db);
            case ("2") -> sendMessage(jobSeeker, db);
            case ("3") -> pendingJobApplications(jobSeeker, db);
            case ("4") -> jobInterviews(jobSeeker, db);
            case ("5") -> profileManagement(jobSeeker, db);
            case ("6") -> logOut();
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    public void sendMessage (JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Sending message");
        UserIO.displayBody("This option is not yet implemented coming soon");
        UserIO.displayBody("Returning to home.");
        home(jobSeeker, db);
    }

    /**
     * Called when the jobSeeker elects to search for a job.
     */
    public void jobSearch(JobSeeker jobSeeker, DatabaseManager db) {
        String searchTerm = getSearchTerm();
        boolean filterResults = getShouldFilter();
        //Field, filter
        HashMap<String, String> filters = filterResults ? getFilters() : null;
        boolean sortResults = getShouldSort();
        //Field, (ascending/descending)
        HashMap<String, String> sort = sortResults ? getSort() : null;

    }

    //TODO: Get filters

    public HashMap<String, String> getFilters() {
        HashMap<String, String> filters =  new HashMap<>();
        while (true) {
            String[] options = {
                    "Personal relevance score",
                    "Compensation",
                    "Location",
                    "Days since posted"};
            UserIO.displayOptions(options);
            String selectedOption = UserIO.menuSelectorValue("How would you like to filter results?", options);

            //TODO: Up to here
            switch (selectedOption) {
                // case "0" -> getPersonalRelevanceScoreFilter();
                // case "1" -> getCompensationFilter();
                // case "2" -> getLocationFilter();
                // case "3" -> getDateFilter();
            }
            break;
        }


        return filters;
    }


    public HashMap<String, String> getSort() {
        return new HashMap<>();
    }

    //TODO: This can be refactored with getShouldFilter
    public boolean getShouldSort() {
        UserIO.displayBody("Would you like to sort to the search results? (yes/no)");
        //TODO: Maybe we have .toLowerCase() inside the UserIO class?
        String input = UserIO.getInput().toLowerCase();
        //TODO: This should be a validate option.
        int tries = 0;
        while (!input.equals("yes") && !input.equals("no")) {
            if(tries++ <= 3) {
                System.out.println("Please enter either 'yes' or 'no'. You have " + (3 - tries) + " remaining.");
                input = UserIO.getInput().toLowerCase();
            } else {
                exit();
            }
        }
        return input.equals("yes");
    }

    public boolean getShouldFilter() {
        UserIO.displayBody("Would you like to apply a filter to the search? (yes/no)");
        //TODO: Maybe we have .toLowerCase() inside the UserIO class?
        String input = UserIO.getInput().toLowerCase();
        //TODO: This should be a validate option.
        int tries = 0;
        while (!input.equals("yes") && !input.equals("no")) {
            if(tries++ <= 3) {
                System.out.println("Please enter either 'yes' or 'no'. You have " + (3 - tries) + " remaining.");
                input = UserIO.getInput().toLowerCase();
            } else {
                exit();
            }
        }
        return input.equals("yes");
    }

    private void exit() {
        System.exit(0);
    }

    public String getSearchTerm(){
        UserIO.displayTitleAndBody("Job Search", """
            Please enter a job position you would like to search for:""");
        return UserIO.getInput();
    }

    public void sortJobSearchResults(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayBody("How would you like to sort results?: ");
        String[] options = {
                "Personal relevance score",
                "Compensation ascending",
                "Compensation descending",
                "Days since posted ascending",
                "Days since posted descending"
        };
        String userInput = UserIO.menuSelectorKey("Please enter one of the above options:", options);

        switch (userInput) {
            //case ("0") -> personal relevance score
            case ("1") -> resultsCompensationRange(jobSeeker, db);
            case ("2") -> resultsCompensationRange(jobSeeker, db);
            //case ("3") ->    days since posted ascending
            //case ("4") ->    days since posted descending
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    public void resultsCompensationRange(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayBody("What is the minimum compensation you would like to consider? ");
        String minCompensation = UserIO.getInput();

        UserIO.displayBody("What is the maximum compensation you would like to consider? ");
        String maxCompensation = UserIO.getInput();

        additionalSearchFilter(jobSeeker, db);
    }

    public void additionalSearchFilter(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayBody("Would you like to another filter? ");
        String[] options = {
            "Yes",
            "No"
        };

        String userInput = UserIO.menuSelectorKey("Please enter one of the above options:", options);

        switch (userInput) {
            case ("0") -> sortJobSearchResults(jobSeeker, db);
            case ("1") -> displayJobSearchResults(jobSeeker, db);
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    public void displayJobSearchResults(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayBody("Here is a list of jobs that match your search");
        String[] options = {
            "Example - Finance Officer - Seek Pty Ltd - Applied today",
            "Example - Finance Officer - Seek Pty Ltd - Applied today",
            "TO SEE MORE JOBS",
            "TO RETURN HOME"
        };

        String userInput = UserIO.menuSelectorKey("Please select number corresponding to a job to see its details", options);

        switch (userInput) {
           // case ("0") -> displayJob(); // TODO : CALL JOB
           // case ("1") -> displayJob(); // TODO : CALL JOB
           // case ("2") -> // more 1 - TODO : TO SEE MORE JOB applications on my watchlist - IF NO MORE JOB applications, DISPLAY A MESSAGE THAT INDICATES THIS.
            case ("3") -> home(jobSeeker, db);
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    ///////////////////////////////
    public void displayJobSearchJob(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
            // TODO : display particular job here.
         String[] options = {
            "Apply for this job",
            "Add to watchlist",
            "View company (coming soon)",
            "Report job (coming soon)",
            "Go back"
         };

         String userInput = UserIO.menuSelectorKey("Please enter one of the above options", options);

         switch (userInput) {
            case ("0") -> jobApplication(jobSeeker, db);
            case ("1") -> addJob(jobSeeker, db);
            case ("2") -> viewJobSearchJobCompany(jobSeeker, db);
            case ("3") -> reportJobSearchJob(jobSeeker, db);
            case ("4") -> displayJobSearchResults(jobSeeker, db);
         }
    }

    public void jobApplication(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {   //cover letter, resume, question answers
        UserIO.displayBody("Please advise why you are interested in this particular job.?");
        UserIO.getInput();
        UserIO.displayBody("Please attach your cover letter and resume.");
        UserIO.displayBody("Congratualions! You have successfully applied for this job.");
        //  Add application date to job descrition.  use method to add job to pending applications list.
        displayJobSearchResults(jobSeeker, db);
    }

    public void addJob(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        //Update pending watchlist    TO DO : how to select a particular job. Update watchlist
        UserIO.displayBody("This job has been added to your watchlist.");
        displayJobSearchJob(jobSeeker, db);
    }

    public void reportJobSearchJob(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {    // TODO : Where does the report go to?  Updates pending job application?
        UserIO.displayBody("What is your complaint?");
        UserIO.getInput();
        UserIO.displayBody("Thank you for reporting this job, we will look into it. Redirecting you to your job search results.");
        displayJobSearchResults(jobSeeker, db);
    }

    public void viewJobSearchJobCompany(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
            // TODO : display particular company relating to a job search job here.
        String[] options = {
            "View all jobs by this company",
            "Report company",
            "GO BACK"
        };

        String userInput = UserIO.menuSelectorKey("Please enter one of the above options", options);

        switch (userInput) {
            //case ("0") -> viewCompanyJobs(jobSeeker, db);
            case ("1") -> reportJobSearchJobCompany(jobSeeker, db);
            case ("2") -> displayJobSearchJob(jobSeeker, db);
        }
    }

    public void reportJobSearchJobCompany(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {    // TODO : Where does the report go to?  Updates pending job application?
        UserIO.displayBody("What is your complaint?");
        UserIO.getInput();
        UserIO.displayBody("Thank you for reporting this company, we will look into it. Redirecting you to your job search results.");
        displayJobSearchResults(jobSeeker, db);
    }

    // 2. VIEW WATCHLIST

    public void watchlist(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Watchlist");
        UserIO.displayBody("This option is not yet implemented coming soon.");
        UserIO.displayBody("Returning to view my jobs.");
        home(jobSeeker, db);
    }

    // 4. VIEW PENDING JOB APPLICATIONS

    public void pendingJobApplications(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Viewing pending job applications");
        UserIO.displayBody("This option is not yet implemented coming soon.");
        UserIO.displayBody("Returning to view my jobs.");
        home(jobSeeker, db);
    }

    // 5. VIEW JOB INTERVIEWS FUNCTIONS

    public void jobInterviews(JobSeeker jobSeeker, DatabaseManager db) {
        UserIO.displayHeading("Viewing job interviews");


//        String[] options = {
//                "Example - Finance Officer - Seek Pty Ltd - Applied today",
//                "Example - Finance Officer - Seek Pty Ltd - Applied today",
//                "TO SEE MORE JOB INTERVIEWS ON YOUR WATCHLIST",
//                "TO REMOVE A JOB INTERVIEW ON YOUR WATCHLIST",
//                "TO RETURN HOME"
//        };//display rejecteJobs
//
//        String userInput = UserIO.menuSelectorKey("Please select number corresponding to job interview to see its details", options);
//
//        switch (userInput) {
//            case ("1") -> displayJobInterviewDetails(); // TODO : CALL JOB
//            case ("2") -> displayJobInterviewDetails(); // TODO : CALL JOB
//           // case ("3") -> // more 1 - TODO : TO SEE MORE JOB interviews on my watchlist - IF NO MORE JOB interview, DISPLAY A MESSAGE THAT INDICATES THIS.
//            case ("4") -> removeJobInterview();
//            case ("5") -> home();
//            default -> throw new IllegalStateException("Unexpected value: " + userInput);
//        }
    }

    public void displayJobInterviewDetails(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        // TODO : display particular job interview here.
        String[] options = {
                "Cancel application and interview",
                "TO GO BACK",
                "TO RETURN HOME"
        };

        String userInput = UserIO.menuSelectorKey("Please enter one of the above options", options);

        switch (userInput) {
            case ("0") -> cancelApplicationPlusInterview(jobSeeker, db);
            case ("1") -> jobInterviews(jobSeeker, db);
            case ("2") -> home(jobSeeker, db);
        }
    }

    public void removeJobInterview(JobSeeker jobSeeker, DatabaseManager db) {
        UserIO.displayBody("This job interview has been removed from your watchlist. Returning you to your list of job interviews.");
        //Update job interviews
        jobInterviews(jobSeeker, db);
    }

    public void cancelApplicationPlusInterview(JobSeeker jobSeeker, DatabaseManager db) {
        UserIO.displayBody("This job application and its associated interview have been cancelled. Returning you to your list of job interviews.");
        //Update job interviews
        jobInterviews(jobSeeker, db);
    }


    // 6. PROFILE MANAGEMENT FUNCTIONS

    public void profileManagement(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Profile Management");
        String[] options = {
                "Update profile (coming soon)",
                "Delete profile",
                "Back",
                "Home",
        };
        String userInput = UserIO.menuSelectorKey("Please enter one of the following:", options);

        switch (userInput) {
            case ("0") -> updateProfile(jobSeeker, db);
            case ("1") -> deleteProfile(jobSeeker, db);
            case ("2") -> home(jobSeeker, db);
            case ("3") -> home(jobSeeker, db);
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    public void deleteProfile(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {

        String[] options = {
                "Yes (coming soon)",
                "No",
                "Back",
                "Home"
        };
        String userInput = UserIO.menuSelectorKey("Are you sure you would like to delete your profile?:", options);

        switch (userInput) {
            case ("0") -> deleteProfileYes(jobSeeker, db);
            case ("1") -> deleteProfileNo(jobSeeker, db);
            case ("2") -> profileManagement(jobSeeker, db);
            case ("3") -> home(jobSeeker, db);

        }
    }

    public void deleteProfileNo(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayBody("Your profile has NOT been deleted.");
        UserIO.displayBody("Returning to profile management");
        profileManagement(jobSeeker, db);
    }

    public void deleteProfileYes(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayBody("This option is not yet implemented coming soon");
        UserIO.displayBody("Returning to profile management");
        profileManagement(jobSeeker, db);
    }

    public void updateProfile(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Updating profile");
        UserIO.displayBody("This option is not yet implemented coming soon");
        UserIO.displayBody("Returning to profile management");
        profileManagement(jobSeeker, db);
    }

    // 7. Logout

    public void logOut() {
        UserIO.displayBody("You have now been logged out.  You will now be redirected to the Job Searchie Welcome Screen.");
        //JobSearchie.welcomeScreen();  //TODO: fix
    }
}
