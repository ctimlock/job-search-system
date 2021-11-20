package Controllers;

import entities.JobSeeker;
import entities.User;
import utilities.UserIO;
import utilities.Validate;

public abstract class UserHandler {
    public static void welcomeScreen () {
        UserIO.displayTitleAndBody("Welcome to Job Searchie", """
                Job Searchie is a job listing market place.  We aim to provide a superb experience for both job seekers and recruiters.

                At any time you may enter `exit` if you would like to exit the program.

                Enjoy searching for your next dream job or finding the perfect employee.

                You will now be redirected to the registration and login page""");
    }

    public static int loginOrRegister() {
        UserIO.displayHeadingAndBody("", "");
        int selection = UserIO.getIntegerInput();
        while (! new Validate().isInRange(selection, 1, 3, true)) {
            //try again
            //get more user input
            selection = 1;
        }
        if (selection == 1)
            login();
        else if (selection == 2)
            register();
        else if (selection == 3)
            exit();

        return 1;
    }

    public static User login() {
        return new JobSeeker();
    }

    public static User register() {
        return new JobSeeker();
    }

    public static void exit() {
        System.exit(1);
    }
}
