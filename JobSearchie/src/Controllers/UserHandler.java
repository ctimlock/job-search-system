package Controllers;

import database.DatabaseIO;
import entities.JobSeeker;
import entities.User;
import utilities.UserIO;
import utilities.Validate;

import java.util.HashMap;

public abstract class UserHandler {
    public static void welcomeScreen () {
        UserIO.displayTitleAndBody("Welcome to Job Searchie", """
                Job Searchie is a job listing market place.  We aim to provide a superb experience for both job seekers and recruiters.

                At any time you may enter `exit` if you would like to exit the program.

                Enjoy searching for your next dream job or finding the perfect employee.

                You will now be redirected to the registration and login page""");
    }

    private static void printLoginOrRegister() {
        UserIO.displayHeading("Login or Register");
        HashMap<String, String> loginOptions = new HashMap<>();
        loginOptions.put("1", "Login");
        loginOptions.put("2", "Register");
        loginOptions.put("3", "Exit");
        UserIO.displayOptions(loginOptions);
        UserIO.displayBody("Please select one of the above options:");
    }

    public static User loginOrRegister() {
        User user;
        Validate validator = new Validate();
        String userInput = UserIO.getInput().strip();
        String[] options = new String[]{"1", "2", "3"};
        while (!validator.isValidOption(userInput, options)) {
            UserIO.displayBody("`" + userInput + "` is not a valid option please enter a valid option:");
            userInput = UserIO.getInput();
        }
        switch (userInput) {
            case ("1") -> user = login();
            case ("2") -> user = register();
            case ("3") -> exit();
            default -> throw new IllegalStateException("Unexpected value: " + userInput + "User Handler class, login or register class");
        }
        return new JobSeeker();
    }

    public static User login() {
        UserIO.displayBody("Please enter your email address");
        String email = UserIO.getInput();
        //DatabaseIO.doesEmailExist();
        return new JobSeeker();
    }

    public static User register() {
        return new JobSeeker();
    }

    public static void exit() {
        System.exit(1);
    }
}
