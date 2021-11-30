import database.DatabaseManager;
import entities.*;
import utilities.UserIO;
import utilities.Validate;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
