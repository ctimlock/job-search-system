import Controllers.JobHandler;
import database.DatabaseManager;
import entities.*;
import utilities.UserIO;
import utilities.Validate;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class JobSearchie {
    private Session session;

    public static void main(String[] args) throws SQLException, ParseException {
        JobSearchie program = new JobSearchie();
        program.session = new Session();
        program.run();
    }

    public void run() throws SQLException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DatabaseManager db = new DatabaseManager();
        if(!db.open()) {
            System.out.println("Can't open database");
            return;
        }

//        Location loc = new Location("Australia", "Tasmania", "Trowutta", "7330");
//        ArrayList<String> keywords = new ArrayList<>();
//        keywords.add("Accounting");
//        keywords.add("Economics");
//        keywords.add("Study");
//        keywords.add("Focus");
//
//        ArrayList<String> categories = new ArrayList<>();
//        categories.add("Accounting");
//        categories.add("Economics");
//        categories.add("Study");
//        categories.add("Accounting");

        //Recruiter recruiter = new Recruiter("James", "Bond", "james_bond@hotmail.com", "Abcabc123", new Date(), "Seek Pty Ltd", "Computer Science", "0459797824", new Date());

        //Job job = new Job("Software Dev", recruiter, new Date(), new Date(), new Date(), "Seek Pty Ltf", categories, loc, "Full Time", "WFH", 95625, "Entry Level", "Excellent opportunity as an entry level software developer", true, keywords);

        //db.insertJob(job);

        //JobHandler jobHandler = new JobHandler();
        //Job newJob = jobHandler.createJob(recruiter);
        //UserIO.displayHeading("Please review the job details:");
        //newJob.display();
        //db.insertJob(newJob);

        //System.out.println(db.getJob(1).getCompany());

        //System.out.println("Here");
        db.close();

        welcomeScreen();
        loginOrRegister();

    }

    public void welcomeScreen () {
        UserIO.displayTitleAndBody("Welcome to Job Searchie", """
                Job Searchie is a job listing market place.  We aim to provide a superb experience for both job seekers and recruiters.

                At any time you may enter `exit` if you would like to exit the program.

                Enjoy searching for your next dream job or finding the perfect employee.

                You will now be redirected to the registration and login page""");
    }

    private void printLoginOrRegisterScreen() {
        UserIO.displayHeading("Login or Register");
        UserIO.displayBody("Please select one of the options:");
        String[] options = {"Login", "Register", "Exit"};
        UserIO.displayOptions(options);
    }

    public void loginOrRegister() throws SQLException {
        printLoginOrRegisterScreen();
        Validate validator = new Validate();
        String userInput = UserIO.getInput().strip();
        String[] options = new String[]{"1", "2", "3"};
        while (!validator.isValidOption(userInput, options)) {
            UserIO.displayBody("`" + userInput + "` is not a valid option please enter a valid option:");
            userInput = UserIO.getInput();
        }
        switch (userInput) {
            case ("1") -> login();
            case ("2") -> register();
            case ("3") -> exit();
            default -> throw new IllegalStateException("Unexpected value: " + userInput + "User Handler class, login or register class");
        }
    }

    public void login() {
        UserIO.displayBody("Please enter your email address");
        String email = UserIO.getInput();
        //Does email exist (Database)
        String password = UserIO.getInput();
        //Does password match? (Database)

        //populate user from database
        //giver to session.
        //session.setUser(new JobSeeker());
    }
    /**
     * This method will collect an email address and password from a new user and save them into the UserDatabase??
     */
    public void register() throws SQLException {
        UserIO.displayBody("Please enter your email address");
        String userEmailInput = UserIO.getInput().strip();
        Validate validator = new Validate();

        while (!validator.isValidEmail(userEmailInput))
        {
            UserIO.displayBody("`" + userEmailInput + "` is not a valid option please enter a valid option:");
            userEmailInput = UserIO.getInput();
        }

        String userPasswordInput;
        String passwordA = "00";
        String passwordB = "01";
        do
        {
            validator.resetAttempts();
            UserIO.displayBody("""
                    Please enter a password that matches the following criteria:
                    1. Has at least 8 characters in length.
                    2. Includes at least one capital and one lower case letter.
                    3. Includes at least one number and one special character.""");
            passwordA = UserIO.getInput().strip();

            while (!validator.isValidPassword(passwordA))
            {
                UserIO.displayBody("""
                        The password you entered does not match the criteria.  Please ensure your password:
                        1. Is at least 8 characters in length.
                        2. Includes at least one capital and one lower case letter. 
                        3. Includes at least one number and one special character.""");

                passwordA = UserIO.getInput();
            }
            UserIO.displayBody("Please re-enter your password: ");
            passwordB = UserIO.getInput().strip();

            if (!passwordA.equals(passwordB))
            {
                UserIO.displayBody("Your password does not match. Please try again: ");
            }
            userPasswordInput = passwordB;
        } while (!passwordA.equals(passwordB));
        //TODO: pass email and password into create user.
        UserIO.displayHeading("Profile setup");
        UserIO.displayBody("""
                It looks like this is the first time you’ve logged into Job Searchie. Please take some time to answer a
                few questions and assist us in giving you the best opportunity to find your dream job or recruit the
                perfect employee.
        """);
        String[] options = {
                "Job seeker",
                "Recruiter"
        };
        String accountType = UserIO.menuSelectorValue("Which account type would you like to set up", options);
        String firstName = UserIO.enterAttribute("first name", 1, 30);
        String lastName = UserIO.enterAttribute("last name", 1, 30);
        //User user = new User(accountType, firstName, lastName, userEmailInput, passwordB, new Date());
        //user.display();

        //TODO: insert user into database
        DatabaseManager db = new DatabaseManager();
        if (accountType.equals("1")) {
            //JobSeeker jobSeeker = new JobSeeker(accountType, firstName, lastName, userEmailInput, passwordB, new Date());
            //db.insertJobSeeker(jobSeeker);
            //jobSeeker.display();
            System.out.println("Finish this later");
        }
        else {
            String company = UserIO.enterAttribute("company", 4, 30);
            String speciality = UserIO.enterAttribute("recruiting speciality", 4, 30);
            String contactNumber = UserIO.enterAttribute("contact number", 8, 12);
            //TODO DOB
            //Recruiter recruiter = new Recruiter(accountType, firstName, lastName, userEmailInput, passwordB, new Date(), company, speciality, new Date());
            //db.insertRecruiter(recruiter);
            //recruiter.display();
        }
    }

    public void exit() {
        System.exit(1);
    }
}
