package Controllers;

import entities.JobSeeker;
import entities.User;
import utilities.UserIO;
//import src.JobSearchie;    //TODO: fix



public class JobSeekerHandler extends UserHandler {

    public void startJobSeekerHandler() {
        JobSeekerHandler jsh = new JobSeekerHandler();
        jsh.home();
    }

    public void home() {
        UserIO.displayTitleAndBody("Home", "");
        String[] options = {
                "Search for a job",
                "View watchlist",
                "Messages",               //                  TODO : How are we doing messages?
                "View pending job applications",
                "View job interviews",
                "View rejected jobs",
                "Profile management",
                "Log out"
        };
        String userInput = UserIO.menuSelectorKey("Please enter one of the above options:", options);

        switch (userInput) {
            case ("1") -> jobSearch();
            case ("2") -> watchlist();
            //case ("3") -> new MessageHandler().viewMessages();   // TODO : Messages
            case ("4") -> pendingJobApplications();
            case ("5") -> jobInterviews();
            case ("6") -> rejectedJobs();
            case ("7") -> profileManagement();
            case ("8") -> logOut();
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    // 1. Job search functions

    public void jobSearch() {
        initiateJobSearch();
        sortJobSearchResults();
    }

    public String initiateJobSearch(){
        UserIO.displayTitleAndBody("Job Search", """
            Please enter a job position you would like to search for:""");

        String job = UserIO.getInput();
        return job;     // TODO : needs fixing - return value goes nowhere.
    }

    public void sortJobSearchResults() {
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
            //case ("1") -> personal relevance score
            case ("2") -> resultsCompensationRange();
            case ("3") -> resultsCompensationRange();
            //case ("4") ->    days since posted ascending
            //case ("5") ->    days since posted descending
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    public void resultsCompensationRange(){
        UserIO.displayBody("What is the minimum compensation you would like to consider? ");
        String minCompensation = UserIO.getInput();

        UserIO.displayBody("What is the maximum compensation you would like to consider? ");
        String maxCompensation = UserIO.getInput();

        additionalSearchFilter();
    }

    public void additionalSearchFilter() {
        UserIO.displayBody("Would you like to another filter? ");
        String[] options = {
            "Yes",
            "No"
        };

        String userInput = UserIO.menuSelectorKey("Please enter one of the above options:", options);

        switch (userInput) {
            case ("1") -> sortJobSearchResults();
            case ("2") -> displayJobSearchResults();
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    public void displayJobSearchResults() {
        UserIO.displayBody("Here is a list of jobs that match your search");
        String[] options = {
            "Example - Finance Officer - Seek Pty Ltd - Applied today",
            "Example - Finance Officer - Seek Pty Ltd - Applied today",
            "TO SEE MORE JOBS",
            "TO RETURN HOME"
        };

        String userInput = UserIO.menuSelectorKey("Please select number corresponding to a job to see its details", options);

        switch (userInput) {
           // case ("1") -> displayJob(); // TODO : CALL JOB
           // case ("2") -> displayJob(); // TODO : CALL JOB
           // case ("3") -> // more 1 - TODO : TO SEE MORE JOB applications on my watchlist - IF NO MORE JOB applications, DISPLAY A MESSAGE THAT INDICATES THIS.
            case ("4") -> home();
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    ///////////////////////////////
    public void displayJobSearchJob() {
            // TODO : display particular job here.
         String[] options = {
            "Apply for this job",
            "Add to watchlist",
            "View company",
            "Report job",
            "Go back"
         };

         String userInput = UserIO.menuSelectorKey("Please enter one of the above options", options);

         switch (userInput) {
            case ("1") -> jobApplication();
            case ("2") -> addJob();
            case ("3") -> viewJobSearchJobCompany();
            case ("4") -> reportJobSearchJob();
            case ("5") -> displayJobSearchResults();
         }
    }

    public void jobApplication() {   //cover letter, resume, question answers
        UserIO.displayBody("Please advise why you are interested in this particular job.?");
        UserIO.getInput();
        UserIO.displayBody("Please attach your cover letter and resume.");
        UserIO.displayBody("Congratualions! You have successfully applied for this job.");
        //  Add application date to job descrition.  use method to add job to pending applications list.
        displayJobSearchResults();
    }


    public void addJob() {
        //Update pending watchlist    TO DO : how to select a particular job. Update watchlist
        UserIO.displayBody("This job has been added to your watchlist.");
        displayJobSearchJob();
    }

    public void reportJobSearchJob() {    // TODO : Where does the report go to?  Updates pending job application?
            UserIO.displayBody("What is your complaint?");
            UserIO.getInput();
            UserIO.displayBody("Thank you for reporting this job, we will look into it. Redirecting you to your job search results.");
            displayJobSearchResults();
    }



    public void viewJobSearchJobCompany() {
            // TODO : display particular company relating to a job search job here.
        String[] options = {
            "View all jobs by this company",
            "Report company",
            "GO BACK"
        };

        String userInput = UserIO.menuSelectorKey("Please enter one of the above options", options);

        switch (userInput) {
            case ("1") -> viewCompanyJobs();
            case ("2") -> reportJobSearchJobCompany();
            case ("3") -> displayJobSearchJob();
        }
    }

    public void reportJobSearchJobCompany() {    // TODO : Where does the report go to?  Updates pending job application?
        UserIO.displayBody("What is your complaint?");
        UserIO.getInput();
        UserIO.displayBody("Thank you for reporting this company, we will look into it. Redirecting you to your job search results.");
        displayJobSearchResults();
    }

    // 2. VIEW WATCHLIST

    public void watchlist(){
        UserIO.displayTitleAndBody("Watchlist", "");
        String[] options = {
                "Example - Finance Officer - Seek Pty Ltd - Applied today",
                "Example - Finance Officer - Seek Pty Ltd - Applied today",
                "TO SEE MORE JOBS ON YOUR WATCHLIST",
                "TO REMOVE A JOB FROM YOUR WATCHLIST",
                "TO RETURN HOME"
        };

        String userInput = UserIO.menuSelectorKey("Please select number corresponding to a job to see its details", options);

        switch (userInput) {
            case ("1") -> displayWatchlistJob(); // TODO : CALL JOB
            case ("2") -> displayWatchlistJob(); // TODO : CALL JOB
           // case ("3") -> // more 1 - TODO : TO SEE MORE JOB applications on my watchlist - IF NO MORE JOB applications, DISPLAY A MESSAGE THAT INDICATES THIS.
            case ("4") -> removeWatchlistJob();
            case ("5") -> home();
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    public void displayWatchlistJob() {
        // TODO : display particular job here.
        String[] options = {
                "View compnay",
                "Remove job",
                "Report job",
                "TO GO BACK",
                "TO RETURN HOME"
        };

        String userInput = UserIO.menuSelectorKey("Please enter one of the above options", options);

        switch (userInput) {
            case ("1") -> viewWatchlistJobCompany();
            case ("2") -> removeWatchlistJob();
            case ("3") -> reportWatchlistJob();
            case ("4") -> watchlist();
            case ("5") -> home();
        }
    }

    public void reportWatchlistJob() {    // TODO : Where does the report go to?  Updates pending job application?
        UserIO.displayBody("What is your complaint?");
        UserIO.getInput();
        UserIO.displayBody("Thank you for reporting this job, we will look into it. Redirecting you to your watchlist.");
        watchlist();
    }

    public void removeWatchlistJob() {
        //Update pending watchlist    TO DO : how to select a particular job.
        UserIO.displayBody("This job has been removed from your watchlist. Returning you to your watchlist.");
        watchlist();
    }

    public void viewWatchlistJobCompany() {
        // TODO : display particular company relating to a pending job application here.
        String[] options = {
            "View all jobs by this company",
            "Report company",
            "GO BACK"
        };

        String userInput = UserIO.menuSelectorKey("Please enter one of the above options", options);

        switch (userInput) {
             case ("1") -> viewCompanyJobs();
             case ("2") -> reportWatchlistJobCompany();
             case ("3") -> displayWatchlistJob();
        }
    }

    public void reportWatchlistJobCompany() {    // TODO : Where does the report go to?  Updates watchlist?
        UserIO.displayBody("What is your complaint?");
        UserIO.getInput();
        UserIO.displayBody("Thank you for reporting this job, we will look into it. Redirecting you to your watchlist.");
        watchlist();
    }


    // 4. VIEW PENDING JOB APPLICATIONS      (NEEDS MORE FUNCTIONALITY COMPARED WITH OPTION 5?)

    public void pendingJobApplications() {
        UserIO.displayTitleAndBody("Pending job applications", "");
        String[] options = {
                "Example - Finance Officer - Seek Pty Ltd - Applied today",
                "Example - Finance Officer - Seek Pty Ltd - Applied today",
                "TO SEE MORE JOB PENDING JOB APPLICATIONS ON YOUR WATCHLIST",
                "TO REMOVE A PENDING JOB APPLICATION ON YOUR WATCHLIST",
                "TO RETURN HOME"
        };

        String userInput = UserIO.menuSelectorKey("Please select number corresponding to job interview to see its details", options);

        switch (userInput) {
            case ("1") -> displayPendingJobApplication(); // TODO : CALL JOB
            case ("2") -> displayPendingJobApplication(); // TODO : CALL JOB
           // case ("3") -> // more 1 - TODO : TO SEE MORE JOB applications on my watchlist - IF NO MORE JOB applications, DISPLAY A MESSAGE THAT INDICATES THIS.
            case ("4") -> removePendingJobApplication();
            case ("5") -> home();
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    public void displayPendingJobApplication() {
        // TODO : display particular job application here.
        String[] options = {
                "View compnay",
                "Remove application",
                "Report job",
                "TO GO BACK",
                "TO RETURN HOME"
        };

        String userInput = UserIO.menuSelectorKey("Please enter one of the above options", options);

        switch (userInput) {
            case ("1") -> viewCompany();
            case ("2") -> removePendingJobApplication();
            case ("3") -> reportJob();
            case ("4") -> pendingJobApplications();
            case ("5") -> home();
        }
    }

    public void removePendingJobApplication() {
        //Update pending job applications list
        UserIO.displayBody("This job application has been removed from your watchlist. Returning you to your list of pending job applications.");
        pendingJobApplications();
    }

    public void viewCompany() {
        // TODO : display particular company relating to a pending job application here.
        String[] options = {
                "View all jobs by this company",
                "Report company",
                "GO BACK"
        };

        String userInput = UserIO.menuSelectorKey("Please enter one of the above options", options);

        switch (userInput) {
            case ("1") -> viewCompanyJobs();
            case ("2") -> reportCompany();
            case ("3") -> displayPendingJobApplication();
        }
    }

    public void reportCompany() {    // TODO : Where does the report go to?  Updates pending job application?
        UserIO.displayBody("What is your complaint?");
        UserIO.getInput();
        UserIO.displayBody("Thank you for reporting this company, we will look into it. Redirecting you to your pending job applications list.");
        pendingJobApplications();
    }

    public void viewCompanyJobs() {
        // TODO : Display jobs related to a particular company
    }

    public void reportJob() {    // TODO : Where does the report go to?  Updates pending job application?
        UserIO.displayBody("What is your complaint?");
        UserIO.getInput();
        UserIO.displayBody("Thank you for reporting this job, we will look into it. Redirecting you to your pending job applications list.");
        pendingJobApplications();
    }


    // 5. VIEW JOB INTERVIEWS FUNCTIONS

    public void jobInterviews() {
        UserIO.displayTitleAndBody("Job interviews", "");
        String[] options = {
                "Example - Finance Officer - Seek Pty Ltd - Applied today",
                "Example - Finance Officer - Seek Pty Ltd - Applied today",
                "TO SEE MORE JOB INTERVIEWS ON YOUR WATCHLIST",
                "TO REMOVE A JOB INTERVIEW ON YOUR WATCHLIST",
                "TO RETURN HOME"
        };//display rejecteJobs

        String userInput = UserIO.menuSelectorKey("Please select number corresponding to job interview to see its details", options);

        switch (userInput) {
            case ("1") -> displayJobInterviewDetails(); // TODO : CALL JOB
            case ("2") -> displayJobInterviewDetails(); // TODO : CALL JOB
           // case ("3") -> // more 1 - TODO : TO SEE MORE JOB interviews on my watchlist - IF NO MORE JOB interview, DISPLAY A MESSAGE THAT INDICATES THIS.
            case ("4") -> removeJobInterview();
            case ("5") -> home();
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    public void displayJobInterviewDetails() {
        // TODO : display particular job interview here.
        String[] options = {
                "Cancel application and interview",
                "TO GO BACK",
                "TO RETURN HOME"
        };

        String userInput = UserIO.menuSelectorKey("Please enter one of the above options", options);

        switch (userInput) {
            case ("1") -> cancelApplicationPlusInterview();
            case ("2") -> jobInterviews();
            case ("3") -> home();
        }
    }

    public void removeJobInterview() {
        UserIO.displayBody("This job interview has been removed from your watchlist. Returning you to your list of job interviews.");
        //Update job interviews
        jobInterviews();
    }

    public void cancelApplicationPlusInterview() {
        UserIO.displayBody("This job application and its associated interview have been cancelled. Returning you to your list of job interviews.");
        //Update job interviews
        jobInterviews();
    }

    // 6. VIEW REJECTED JOBS
    public void rejectedJobs() {
        UserIO.displayTitleAndBody("Rejected jobs", "");
        String[] options = {
                "Carpenter",
                "Software designer",
                "TO SEE MORE REJECTED JOBS",
                "TO RETURN HOME"
        };//display rejecteJobs

        String userInput = UserIO.menuSelectorKey("Please select number corresponding to job to see its details", options);

        switch (userInput) {
            case ("1") -> displayRejectedJob(); //TODO : CALL JOB
            case ("2") -> displayRejectedJob(); //TODO : CALL JOB
            //case ("3") -> //TODO : SEE MORE JOBS - IF NO MORE JOBS, DISPLAY A MESSAGE THAT INDICATES THIS.
            case ("4") -> home();
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    public void displayRejectedJob() {
        // TODO : display particular job here.
        String[] options = {
                "TO GO BACK",
                "TO RETURN HOME"
        };

        String userInput = UserIO.menuSelectorKey("Please enter one of the above options", options);

        switch (userInput) {
            case ("1") -> rejectedJobs();
            case ("2") -> home();
        }
    }

    // 7. PROFILE MANAGEMENT FUNCTIONS

    public void profileManagement() {
        UserIO.displayTitleAndBody("Profile Management", "");
                //*TODO: DISPLAY USER'S PROFILE*// """);             //TODO: Display user's profile
        String[] options = {
                "Modify details",
                "Delete profile",
                "Home"
        };

        String userInput = UserIO.menuSelectorKey("Please enter one of the above options:", options);

        switch (userInput) {
            case ("1") -> editProfile();
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

        String userInput = UserIO.menuSelectorKey("Are you sure your would like to delete your profile? ", options);

        switch (userInput) {
            case ("1") -> deleteProfileYes();
            case ("2") -> deleteProfileNo();
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
    }

    public void deleteProfileYes() {
            UserIO.displayBody("Your profile has been deleted. Your will now be redirected to the Job Searchie Welcome screen.");
    }

    public void deleteProfileNo() {
            UserIO.displayBody("Your profile has not been deleted.");
            profileManagement();
    }

    public void editProfile() {
        String[] options = {
                "Name",
                "Date of birth",
                "Email",
                "Contact number",
                "Location",
                "Current or most recent job level",
                "Current or most recent title",
                "Identifiable keyword(s)",
                "Expected compensation",
                "TO GO HOME",
                "TO GO BACK"
        };

        String userInput = UserIO.menuSelectorKey("Please enter one of the above options:", options);

        /*switch (userInput) {     //TODO: FUNCTIONALITY NEEDS TO BE INCLUDED
            case ("1") -> User.setFirstName().setLastName();   // TODO :What would you like to changes your name to?, etc
            case ("2") -> User.setDate();
            case ("3") -> User.setEmail();
            case ("4") -> User.setContactNumber();
            case ("5") -> User.setLocation();
            case ("6") -> JobSeeker.setCurrentJobLevel();
            case ("7") -> JobSeeker.setCurrentJobName();
            case ("8") -> JobSeeker.setKeywords();
            case ("home") -> home();
            case ("back") -> ??
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }*/
    }

    // 8. Logout

    public void logOut() {
        UserIO.displayBody("You have now been logged out.  You will now be redirected to the Job Searchie Welcome Screen.");
        //JobSearchie.welcomeScreen();  //TODO: fix
    }
}
