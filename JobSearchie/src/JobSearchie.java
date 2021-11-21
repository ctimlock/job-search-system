import Controllers.AdminHandler;
import Controllers.UserHandler;
import entities.Admin;
import entities.JobSeeker;
import entities.User;
import utilities.UserIO;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class JobSearchie {
    private User user;

    public static void main(String[] args) {
        JobSearchie session = new JobSearchie();
        session.run();
    }

    public void run() {
        UserHandler.welcomeScreen();
        //user = UserHandler.loginOrRegister();
    }
}
