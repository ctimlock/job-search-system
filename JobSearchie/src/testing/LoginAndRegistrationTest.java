package testing;

import Controllers.UserHandler;
import database.DatabaseManager;
import database.FileManager;
import utilities.UserIO;

import javax.swing.*;
import java.sql.Date;

public class LoginAndRegistrationTest
{
    public static void main(String[] args)
    {
        UserHandler userHandler = new UserHandler();
        DatabaseManager db = new DatabaseManager();
        JFrame frame = new JFrame();
        userHandler.beginRegistration(null, db);
    }
}
