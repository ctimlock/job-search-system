package testing;

import Controllers.UserHandler;
import database.DatabaseManager;

import javax.swing.*;

public class LoginAndRegistrationTest
{
    public static void main(String[] args)
    {
        UserHandler userHandler = new UserHandler();
        DatabaseManager db = new DatabaseManager();
        JFrame frame = new JFrame();
        userHandler.beginLoginOrRegistration(db);
    }
}
