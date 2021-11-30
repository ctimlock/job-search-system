import database.DatabaseManager;
import entities.*;
import utilities.UserIO;
import utilities.Validate;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class JobSearchie {
    private Session session;

    public static void main(String[] args) throws SQLException {
        JobSearchie program = new JobSearchie();
        program.session = new Session();
        program.run();
    }

    public void run() throws SQLException {
        DatabaseManager db = new DatabaseManager();
        if(!db.open()) {
            System.out.println("Can't open database");
            return;
        }

        Location loc = new Location("Australia", "Tasmania", "Trowutta", "7330");
        ArrayList<String> keywords = new ArrayList<>();
        keywords.add("Accounting");
        keywords.add("Economics");
        keywords.add("Study");
        keywords.add("Focus");

        ArrayList<String> categories = new ArrayList<>();
        categories.add("Accounting");
        categories.add("Economics");
        categories.add("Study");
        categories.add("Accounting");

        Recruiter recruiter = new Recruiter("James", "Bond", "james_bond@hotmail.com", "Abcabc123", new Date(System.currentTimeMillis()), "Seek Pty Ltd", "Computer Science", "0459797824", new Date(System.currentTimeMillis()));
        JobSeeker jobSeeker = new JobSeeker("Jim", "levinstine", "jim_levinstine@icloud.com", "NONEHAHA", new Date(System.currentTimeMillis()), "CEO", "Execuitive", "0456789845", "", loc, new Date(System.currentTimeMillis()), keywords, 95000);
        Job job = new Job("Hiphop rapper", recruiter, new Date(System.currentTimeMillis()), null, null, "Seek Pty Ltf", categories, loc, "Full Time", "WFH", 95625, "Entry Level", "Excellent opportunity as an entry level software developer", true, keywords);
        try {
            db.insertJob(job);
        } catch (SQLException e) {
            System.out.println("Could not insert Job: " + e.getMessage());
        }
        System.out.println(job.getId());


        Application app = new Application();
        app.setJob(job);
        app.setResumeDir("resumeDirHere");
        app.setCoverLetterDir("coverLetterDirHere");
        app.setJobSeeker(jobSeeker);
        app.setApplicationDate(new Date(System.currentTimeMillis()));
        app.setStatus("Pending");
        db.insertApplication(app);

        db.close();
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

    public void loginOrRegister() {
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
    public void register()
    {
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

    }

    public void exit() {
        System.exit(1);
    }
}
