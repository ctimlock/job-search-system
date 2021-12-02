package Controllers;

import database.DatabaseManager;
import entities.JobSeeker;
import entities.Location;
import entities.Recruiter;
import entities.User;
import utilities.UserIO;
import utilities.Validate;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Login
{
    /**
     * This method will run the process for login in or registering as a user, and will pass back the user account.
     * @param db The database manager object being used.
     * @return The logged in user as a User derived object.
     */
    public User beginLoginOrRegistration(DatabaseManager db)
    {
        JFrame jFrame = new JFrame();
        User sessionUser = null;
        welcomeScreen();
        String userAction = loginOrRegisterSelector();
        switch (userAction)
        {
            case "Login" -> sessionUser = login(null, db);
            case "Register" -> sessionUser = register(null, db);
            case "Exit" -> System.exit(0);
        }
        return sessionUser;
    }



    private User createUser(String emailAddress, DatabaseManager db)
    {

        String sectionTitle = "Registration";
        UserIO.clearScreenAndAddTitle(sectionTitle);

        String password = setPassword();

        UserIO.clearScreenAndAddTitle(sectionTitle);

        String firstName = UserIO.enterAttribute("your first name", 1, 30);

        UserIO.clearScreenAndAddTitle(sectionTitle);

        String lastName = UserIO.enterAttribute("your last name", 1, 30);

        UserIO.clearScreenAndAddTitle(sectionTitle);

        String accTypeSelection = UserIO.menuSelectorKey("Please select the type of account you'd like to create.", new String[]{"Job Seeker", "Recruiter"});

        switch (accTypeSelection)
        {
            case "0" -> {return registerJobSeeker(firstName, lastName, emailAddress, password, db);}
            case "1" -> {return registerRecruiter(firstName, lastName, emailAddress, password, db);}
            default -> throw new IllegalStateException("Unexpected value: " + accTypeSelection);
        }
    }

    private int enterCompensation()
    {
        UserIO.displayBody("Please enter your expected yearly salary:");
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

    private String enterEmail()
    {
        Validate validator = new Validate();
        UserIO.displayBody("Please enter your email address:");
        String email = UserIO.getInput();
        while (!validator.isValidEmail(email))
        {
            UserIO.displayBody("""
                        Invalid email entered.
                        Please try again:""");
            email = UserIO.getInput();
        }
        return email;
    }

    private ArrayList<String> enterKeywords()
    {
        ArrayList<String> keywords = new ArrayList<>();
        UserIO.displayBody("""
                Please enter at least one keyword which you identify with or align to.
                These keywords will help us match you to relevant jobs.
                Multiple keywords should be separated by a single comma.
                
                E.g: Excel, Finance, Customer Service, Administration
                """);
        keywords.add(UserIO.enterAttribute(" one or more keywords", 0, 120));
        String[] addAnother = {"Yes", "No"};
        String inputAdd;
        do {
            UserIO.clearScreenAndAddTitle("Keywords");
            inputAdd = UserIO.menuSelectorKey("Would you like to add another keyword", addAnother);
            if (inputAdd.equals("0"))
            {
                keywords.add(UserIO.enterAttribute("Keyword", 0, 120));
            }
        } while (inputAdd.equals("0"));
        return keywords;
    }

    private User login(String email, DatabaseManager db)
    {

        String sectionTitle = "Login";

        if (email == null)
        {
            UserIO.clearScreenAndAddTitle(sectionTitle);
            UserIO.displayBody("Please enter your email address.");

            email = UserIO.getInput();
        }

        String accType = db.getUserType(email);

        if (accType == null)
        {
            UserIO.clearScreenAndAddTitle(sectionTitle);
            String sel = UserIO.menuSelectorKey("""
                    There was no account found linked with that email address.
                    Do you wish to register an account or enter your email again?""", new String[]{"Register", "Login"});
            switch (sel)
            {
                case "0" -> {return register(email, db);}
                case "1" -> {return login(null, db);}
                default -> throw new IllegalStateException("Unexpected value: " + sel);
            }
        }

        User retrievedUser;

        switch (accType)
        {
            case "Recruiter" -> retrievedUser = db.getRecruiter(email);
            case "Job Seeker" -> retrievedUser = db.getJobSeeker(email);

            default -> throw new IllegalStateException("Unexpected account type: " + accType);
        }


        UserIO.clearScreenAndAddTitle(sectionTitle);
        UserIO.displayBody("Please enter your password.");
        String enteredPassword = UserIO.getInput();
        int attempts = 3;

        while (!enteredPassword.equals(retrievedUser.getPassword()))
        {
            attempts--;
            if (attempts == 0)
            {
                UserIO.displayBody("Password attempt limit reached. Press enter to exit the program.");
                UserIO.getInput();
                System.exit(0);
            }
            UserIO.clearScreenAndAddTitle(sectionTitle);
            UserIO.displayBody("Incorrect password. You have " + attempts + " attempts remaining. Please try again.");

            enteredPassword = UserIO.getInput();
        }
        UserIO.clearScreenAndAddTitle(sectionTitle);
        UserIO.displayBody("Login successful. Press \"Enter\" to be redirected to the Home page.");
        UserIO.getInput();
        return retrievedUser;
    }

    private String loginOrRegisterSelector()
    {
        UserIO.clearScreenAndAddTitle("Login or Register");
        String[] options = {"Login", "Register", "Exit"};
        return UserIO.menuSelectorValue("Please select one of the options", options);
    }

    private User register(String emailAddress, DatabaseManager db)
    {
        if (emailAddress == null)
        {
            UserIO.displayTitle("Registration");
            emailAddress = enterEmail();
        }

        if (db.getUserType(emailAddress) != null)
        {
            UserIO.clearScreenAndAddTitle("Registration");

            String loginChoice = UserIO.menuSelectorKey("""
                    An account with that email address already exists.
                    Would you like to try to log in, or enter a different email address?""", new String[]{"Log In", "Re-enter email address"});

            switch (loginChoice)
            {
                case "0" -> {return login(emailAddress, db);}
                case "1" -> {return register(emailAddress, db);}
                default -> throw new IllegalStateException("Unexpected value: " + loginChoice);
            }
        } else
        {
            return createUser(emailAddress, db);
        }
    }

    private User registerJobSeeker(String fName, String lName, String emailAddress, String password, DatabaseManager db)
    {
        String sectionTitle = "Registration";
        UserIO.clearScreenAndAddTitle(sectionTitle);
        UserIO.displayBody("Please enter your date of birth in the form of dd/mm/yyyy");
        Date dateOfBirth = UserIO.enterSQLDate();
        UserIO.clearScreenAndAddTitle(sectionTitle);
        String contactNumber = UserIO.enterAttribute(" your preferred contact number", 8, 12);
        UserIO.clearScreenAndAddTitle(sectionTitle);
        String jobLevel = selectJobLevel();
        UserIO.clearScreenAndAddTitle(sectionTitle);
        String jobName = UserIO.enterAttribute(" your current or most recent job name", 3, 30);
        UserIO.clearScreenAndAddTitle(sectionTitle);
        ArrayList<String> keywords = enterKeywords();
        UserIO.clearScreenAndAddTitle(sectionTitle);
        String userChoice = UserIO.menuSelectorKey("Do you wish to upload a resume to help recruiters find you?", new String[]{"Yes", "No"});
        String resume = null;
        if ("0".equals(userChoice))
        {
            try
            {
                resume =  database.FileManager.returnSelectedFileAsString("Please select a resume file");
            } catch (IOException e)
            {
                UserIO.displayBody("There was an error in uploading your resume.");
            }
        }
        UserIO.clearScreenAndAddTitle(sectionTitle);
        int expectedCompensation = enterCompensation();
        UserIO.clearScreenAndAddTitle(sectionTitle);
        String country = UserIO.enterAttribute(" which country you live in", 4, 30);
        String state = UserIO.enterAttribute(" which state you live in", 4, 30);
        String city = UserIO.enterAttribute(" which suburb you live in", 4, 30);
        String postcode = UserIO.enterAttribute(" your postcode", 3, 6);
        Location location = new Location(country, state, city, postcode);

        JobSeeker newJobSeeker = new JobSeeker(fName, lName, emailAddress, password, new Date(System.currentTimeMillis()), jobName, jobLevel, contactNumber, resume, location, dateOfBirth, keywords, expectedCompensation);
        JobSeeker insertedJobseeker = null;
        try
        {
            insertedJobseeker = db.insertJobSeeker(newJobSeeker);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }


        UserIO.clearScreenAndAddTitle(sectionTitle);
        UserIO.displayBody("Thank you, your account has now been set up. Press \"Enter\" to be redirected to the home page.");
        UserIO.getInput();
        return insertedJobseeker;
    }

    private User registerRecruiter(String fName, String lName, String emailAddress, String password, DatabaseManager db)
    {
        String sectionTitle = "Registration";
        UserIO.clearScreenAndAddTitle(sectionTitle);
        UserIO.displayBody("Please enter your date of birth in the form of dd/mm/yyyy");
        Date dateOfBirth = UserIO.enterSQLDate();
        UserIO.clearScreenAndAddTitle(sectionTitle);
        String contactNumber = UserIO.enterAttribute("your preferred contact number", 8, 12);
        UserIO.clearScreenAndAddTitle(sectionTitle);
        String companyName = UserIO.enterAttribute("the company you work for", 3, 30);
        UserIO.clearScreenAndAddTitle(sectionTitle);
        String recruitingSpecialty = UserIO.enterAttribute("your recruiting specialty", 3, 30);

        Recruiter newRecruiter = new Recruiter(fName, lName, emailAddress, password, new Date(System.currentTimeMillis()),companyName,recruitingSpecialty,contactNumber,dateOfBirth);
        Recruiter insertedRecruiter = null;
        try
        {
            insertedRecruiter = db.insertRecruiter(newRecruiter);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        UserIO.clearScreenAndAddTitle(sectionTitle);
        UserIO.displayBody("Thank you, your account has now been set up. Press \"Enter\" to be redirected to the home page.");
        UserIO.getInput();
        return insertedRecruiter;
    }

    private String selectJobLevel()
    {
        String[] jobLevel = {
                "Never employed",
                "Student",
                "Entry level",
                "Mid level",
                "High level",
                "Executive",
                "Other",
                "Prefer not to say"
        };
        String userInput = UserIO.menuSelectorKey("Please select your current or most recent job level:", jobLevel);

        switch (userInput) {
            case "0" -> userInput = "Never employed";
            case "1" -> userInput = "Student";
            case "2" -> userInput = "Entry level";
            case "3" -> userInput = "Mid level";
            case "4" -> userInput = "High level";
            case "5" -> userInput = "Executive";
            case "6" -> userInput = "Other";
            case "7" -> userInput = "Prefer not to say";
            default -> throw new IllegalStateException("Unexpected value: " + userInput);
        }
        return userInput;
    }

    public String setPassword()
    {
        Validate validator = new Validate();
        String inputPassword;
        String confirmedPassword;
        do
        {
            validator.resetAttempts();
            UserIO.displayBody("""
                    Please enter a password that matches the following criteria:
                    1. Has at least 8 characters in length.
                    2. Includes at least one capital and one lower case letter.
                    3. Includes at least one number and one special character.""");
            inputPassword = UserIO.getInput().strip();

            while (!validator.isValidPassword(inputPassword))
            {
                UserIO.displayBody("""
                        The password you entered does not match the criteria.  Please ensure your password:
                        1. Is at least 8 characters in length.
                        2. Includes at least one capital and one lower case letter.
                        3. Includes at least one number and one special character.""");

                inputPassword = UserIO.getInput();
            }
            UserIO.displayBody("Please re-enter your password: ");
            confirmedPassword = UserIO.getInput().strip();

            if (!inputPassword.equals(confirmedPassword))
            {
                UserIO.displayBody("Your password does not match. Please try again: ");
            }

        } while (!inputPassword.equals(confirmedPassword));

        return confirmedPassword;
    }

    private void welcomeScreen () {
        UserIO.displayTitleAndBody("Welcome to Job Searchie", """
                Job Searchie is a job listing market place.  We aim to provide a superb experience for both job seekers and recruiters.

                Enjoy searching for your next dream job or finding the perfect employee.

                Press enter to be redirected to the registration and login page""");
        UserIO.getInput();
    }
}
