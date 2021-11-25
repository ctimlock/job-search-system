import Controllers.UserHandler;
import entities.Session;
import entities.User;
import utilities.UserIO;
import utilities.Validate;
import java.util.HashMap;

public class JobSearchie {
    private Session session;

    public static void main(String[] args) {
        JobSearchie program = new JobSearchie();
        program.session = new Session();
        program.run();
    }

    public void run() {
        welcomeScreen();
        loginOrRegister();
        UserHandler userHandler = session.getUserHandler();
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
        HashMap<String, String> loginOptions = new HashMap<>();
        loginOptions.put("1", "Login");
        loginOptions.put("2", "Register");
        loginOptions.put("3", "Exit");
        UserIO.displayOptions(loginOptions);
        UserIO.displayBody("Please select one of the above options:");
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

      public void register(){
        UserIO.displayBody("Please enter your email address");
        Validate validator3 = new Validate();
        String userInput = UserIO.getInput().strip();
        String emailRegex = /*"^(.+)@(.+)$"; */ "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

        while (!validator3.isValidEmail(userInput, emailRegex)) {
            UserIO.displayBody("`" + userInput + "` is not a valid option please enter a valid option:");
            userInput = UserIO.getInput();
        }
        UserIO.displayBody("Please enter a password that has more than 8 characters which include an upper case letter and a special character: ");
        registerPassword();
        //if (userInput){

        //}
        /*switch (userInput) {
            case ("1") -> login();
            case ("2") -> register();
            case ("3") -> exit();
            default -> throw new IllegalStateException("Unexpected value: " + userInput + "User Handler class, login or register class");
        }*/

    }

   /* public void registerEmail(){
        UserIO.displayBody("Please enter your email address");
        Validate validator3 = new Validate();
        String userInput = UserIO.getInput().strip();
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

        while (!validator3.isValidEmail(userInput, emailRegex)) {
            UserIO.displayBody("`" + userInput + "` is not a valid option please enter a valid option:");
            userInput = UserIO.getInput();
        }
    }*/

    public void registerPassword(){

    }

    public void exit() {
        System.exit(1);
    }
}
