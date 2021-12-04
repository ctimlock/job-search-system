package Controllers;

import database.DatabaseManager;
import database.FileManager;
import entities.*;
import utilities.UserIO;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;


/**
 * Controller class for the JobSeeker entity.
 *
 * @author Charlie Timlock, Levi Quilliam, Tim Perkins, and Merrill Nguyen
 * @version v1.0
 */
public class JobSeekerHandler extends UserHandler {

    /**
     * Creates the home screen for the Job Seeker.
     *
     * @param jobSeeker the JobSeeker user for the home screen.
     * @param db the DatabaseManager.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
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
            case ("6") -> logout(jobSeeker, db);
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    /**
     * This method will submit an application for a job.
     *
     * @param jobSeeker The applicant.
     * @param db The database manager.
     * @param job The job being applied to.
     */
    public void applyForJob(JobSeeker jobSeeker, DatabaseManager db , Job job)
    {
        // 1 - Select a resume and CV file
        // 2 - create an application record in the DB with the file extension
        // 3 - read the id of that application
        // 4 - save files to fileStorage under %applicationID%+%extension%
        String sectionHeading = "Application";
        UserIO.displayTitle(sectionHeading);

        String coverLetterPath = "";
        String resumePath = "";
        String coverLetterExt = "";
        String resumeExt = "";

        String selection = UserIO.menuSelectorValue("Do you wish to add a cover letter to your application?", new String[]{"Yes", "No"});
        if (selection.equals("Yes"))
        {
            coverLetterPath = FileManager.selectFilePath("Select a cover letter file", new String[]{"pdf","docx","doc","txt"});
            coverLetterExt = FileManager.getExtensionFromPath(coverLetterPath);
        }

        UserIO.clearScreenAndAddTitle(sectionHeading);

        selection = UserIO.menuSelectorValue("Do you wish to add a resume to your application?", new String[]{"Yes", "No"});
        if (selection.equals("Yes"))
        {
            resumePath = FileManager.selectFilePath("Select a resume file", new String[]{"pdf","docx","doc","txt"});
            resumeExt = FileManager.getExtensionFromPath(resumePath);
        }

        String status = "Pending";

        try
        {
            Application submitted = db.insertApplication(new Application(jobSeeker, job, coverLetterExt, resumeExt, status, new Date(System.currentTimeMillis())));
            String applicationID = String.valueOf(submitted.getId());
            if (!coverLetterPath.isEmpty())
            {
                FileManager.moveFileToJSStorage(FileManager.COVER_LETTER_DIRECTORY, coverLetterPath, applicationID);
            }
            if (!resumePath.isEmpty())
            {
                FileManager.moveFileToJSStorage(FileManager.RESUME_DIRECTORY, resumePath, applicationID);
            }

            UserIO.clearScreenAndAddTitle(sectionHeading);

            UserIO.displayBody("Application submitted. Press \"Enter\" to exit.");
            UserIO.getInput();

        } catch (SQLException | IOException e)
        {
            UserIO.clearScreenAndAddTitle(sectionHeading);
            UserIO.displayBody("There was an issue in submitting your application. Please try again.");
            UserIO.displayBody("Press \"Enter\" to exit.");
            UserIO.getInput();
        }
    }

    /**
     * Creates the messages screen.
     *
     * @param jobSeeker The JobSeeker using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void sendMessage (JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Sending message");
        UserIO.displayBody("This option is not yet implemented coming soon");
        UserIO.displayBody("Returning to home.");
        home(jobSeeker, db);
    }

    /**
     * Called when the jobSeeker elects to search for a job.
     *
     * @param jobSeeker The JobSeeker using the program.
     * @param db The DatabaseManager handling the databaseIO.
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

    /**
     * Method to filter job
     * @return
     */
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

    /**
     * Method to sort jobs.
     * @return
     */
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

    /**
     * Creates the watchlist screen.
     *
     * @param jobSeeker The JobSeeker using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void watchlist(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Watchlist");
        UserIO.displayBody("This option is not yet implemented coming soon.");
        UserIO.displayBody("Returning to view my jobs.");
        home(jobSeeker, db);
    }

    // 4. VIEW PENDING JOB APPLICATIONS
    /**
     * Creates the pending job applications screen.
     * @param jobSeeker The JobSeeker using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void pendingJobApplications(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Viewing pending job applications");
        UserIO.displayBody("This option is not yet implemented coming soon.");
        UserIO.displayBody("Returning to view my jobs.");
        home(jobSeeker, db);
    }

    // 5. VIEW JOB INTERVIEWS FUNCTIONS

    public void jobInterviews(JobSeeker jobSeeker, DatabaseManager db)
    {
        UserIO.displayHeading("Viewing job interviews");

        ArrayList<Invitation> invitations = db.getAllInvitations();

        invitations.removeIf(invitation -> !Objects.equals(invitation.getJobSeeker().getEmail(), jobSeeker.getEmail()));

        class sortByDate implements Comparator<Invitation>
        {
            @Override
            public int compare(Invitation i1, Invitation i2)
            {
                return i1.getDateSent().compareTo(i2.getDateSent());
            }
        }

        int pagesize = 5;
        invitations.sort(new sortByDate());
        int pages = (int) Math.ceil((double) invitations.size()/ (double) pagesize);
        int currPage = 1;
        String[] navOptions = {
                "Enter 'view' to view an invitation",
                "Enter 'exit' to exit",
                "Enter 'next' to see the next page",
                "Enter 'prev' to see the previous page"
        };

        boolean exit = false;

        do
        {
            UserIO.clearScreen();
            UserIO.displayTitleAndBody("Invitations", "Page " + currPage + " of " + pages + ".");

            for (int i = (currPage * pagesize) - pagesize; i < currPage * pagesize && i < invitations.size(); i++)
            {
                Invitation invite = invitations.get(i);
                UserIO.displayBody(i + 1 + ". " + invite.getJob().getJobTitle() + " at " + invite.getJob().getCompany() + ". Sent on " + invite.getDateSent().toString());
            }

            for (String string: navOptions)
            {
                UserIO.displayBody(string);
            }

            String sel = UserIO.getInput().toLowerCase();
            switch (sel)
            {
                case "exit" -> exit = true;
                case "next" -> {
                    if (currPage < pages)
                    {
                        currPage++;
                    } else
                    {
                        UserIO.displayBody("No more pages");
                    }
                }
                case "prev" -> {
                    if (currPage > 1)
                    {
                        currPage--;
                    } else
                    {
                        UserIO.displayBody("No more pages");
                    }
                }
                case "view" -> {
                    UserIO.displayBody("Enter the invitation number");
                    int selection = Integer.parseInt(UserIO.getInput());
                    if (Math.abs(selection) < invitations.size())
                    {
                        viewInvitation(invitations.get(selection));
                    } else
                    {
                        UserIO.getInput();
                    }
                }
            }

        } while (!exit);
    }

    private void viewInvitation(Invitation invitation)
    {
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
    /**
     * Creates the profile management screen.
     *
     * @param jobSeeker The JobSeeker using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
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

    /**
     * Creates the delete profile screen.
     *
     * @param jobSeeker The JobSeeker using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
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

    /**
     * Creates the screen after the job seeker decides not to delete their profile.
     *
     * @param jobSeeker The JobSeeker using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void deleteProfileNo(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayBody("Your profile has NOT been deleted.");
        UserIO.displayBody("Returning to profile management");
        profileManagement(jobSeeker, db);
    }

    /**
     * Creates the screen after the job seeker decides to delete their profile.
     *
     * @param jobSeeker The JobSeeker using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void deleteProfileYes(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayBody("This option is not yet implemented coming soon");
        UserIO.displayBody("Returning to profile management");
        profileManagement(jobSeeker, db);
    }

    /**
     * Creates the update profile screen.
     *
     * @param jobSeeker The JobSeeker using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void updateProfile(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Updating profile");
        UserIO.displayBody("This option is not yet implemented coming soon");
        UserIO.displayBody("Returning to profile management");
        profileManagement(jobSeeker, db);
    }

    // 7. LOGOUT
    /**
     * Creates screen to ask the user if they would like to logout.
     *
     * @param jobSeeker The JobSeeker using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void logout(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        String[] options = {
                "Yes",
                "No"
        };
        String option = UserIO.menuSelectorKey("Are you sure you would like to logout?", options);

        switch (option) {
            case ("0") -> logoutYes(jobSeeker, db);
            case ("1") -> logoutNo(jobSeeker, db);
        }
    }

    /**
     * Returns user to the home page.
     *
     * @param jobSeeker The JobSeeker using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void logoutNo(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Returning to Home Page");
        home(jobSeeker, db);
    }

    /**
     * Logs the user out by shutting down the console.
     *
     * @param jobSeeker The JobSeeker using the program.
     * @param db The DatabaseManager handling the databaseIO.
     * @throws SQLException handles SQL exception.
     * @throws IOException handles IOException.
     */
    public void logoutYes(JobSeeker jobSeeker, DatabaseManager db) throws SQLException, IOException {
        UserIO.displayHeading("Thank you using JobSearchie. Logging out now bye :)");
        System.exit(0);
    }
}
