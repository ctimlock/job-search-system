import Controllers.AdminHandler;
import Controllers.UserHandler;
import entities.Admin;
import entities.JobSeeker;
import entities.User;

public class JobSearchie {
    public static void main(String[] args) {
        JobSearchie session = new JobSearchie();
        session.run();
    }

    public void run() {
        UserHandler.welcomeScreen();
        UserHandler.loginOrRegister();
    }

    public static void end() {
        System.exit(1);
    }
}
