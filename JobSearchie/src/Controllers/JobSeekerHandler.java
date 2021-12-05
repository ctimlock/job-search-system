package Controllers;

import Database.DatabaseManager;
import Database.FileManager;
import Entities.Application;
import Entities.Invitation;
import Entities.Job;
import Entities.JobSeeker;
import Utilities.UserIO;

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
        boolean flag = true;

        do
        {
            UserIO.displayTitle("Home");
            String[] options = {
                    "Search for a job",
                    "View watchlist (coming soon)",
                    "Messages (coming soon)",
                    "View pending job applications (coming soon)",
                    "View job interviews",
                    "Profile management",
                    "Logout"
            };

            String userInput = UserIO.menuSelectorKey("Please enter one of the above options:", options);

            switch (userInput)
            {
                case ("0") -> JobSearchHandler.search(jobSeeker, db);
                case ("1") -> watchlist(jobSeeker, db);
                case ("2") -> sendMessage(jobSeeker, db);
                case ("3") -> pendingJobApplications(jobSeeker, db);
                case ("4") -> jobInterviews(jobSeeker, db);
                case ("5") -> profileManagement(jobSeeker, db);
                case ("6") -> {flag = false; UserIO.displayBody("Logging out.");}
                default -> throw new IllegalStateException("Unexpected value: " + userInput);
            }
        } while (flag);
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
        UserIO.displayBody("This option is not yet implemented, coming soon.");
        UserIO.displayBody("Returning to Home page.");
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
        UserIO.displayBody("This option is not yet implemented, coming soon.");
        UserIO.displayBody("Returning to Home page.");
    }

    // 5. VIEW JOB INTERVIEWS FUNCTIONS
    /**
     * Creates the view job interviews screen.
     *
     * @param jobSeeker jobSeeker as JobSeeker
     * @param db DatabaseManager as db
     */
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

    /**
     * Method to view invitation.
     *
     * @param invitation as Invitation.
     */
    private void viewInvitation(Invitation invitation)
    {
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
        boolean flag = true;
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
                case ("0") -> updateProfile(jobSeeker, db);
                case ("1") -> deleteProfile(jobSeeker, db);
                case ("2") -> {
                    flag = false;
                    UserIO.displayBody("Returning to Home page.");
                }
                default -> throw new IllegalStateException("Unexpected value: " + userInput);
            }
        } while (flag);

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
        boolean flag = true;
        do
        {
            String[] options = {
                    "Yes (coming soon)",
                    "No",
                    "Back"
            };
            String userInput = UserIO.menuSelectorKey("Are you sure you would like to delete your profile?:", options);

            switch (userInput)
            {
                case ("0") -> deleteProfileYes(jobSeeker, db);
                case ("1") -> deleteProfileNo(jobSeeker, db);
                case ("2") -> {
                    flag = false;
                    UserIO.displayBody("Returning to profile management page.");
                }

            }
        } while (flag);
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
}
