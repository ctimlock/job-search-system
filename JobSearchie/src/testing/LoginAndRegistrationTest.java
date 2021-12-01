package testing;

import Controllers.UserHandler;
import database.DatabaseManager;
import utilities.UserIO;

import java.sql.Date;

public class LoginAndRegistrationTest
{
    public static void main(String[] args)
    {
        UserHandler userHandler = new UserHandler();
        DatabaseManager db = new DatabaseManager();
        userHandler.beginRegistration(null, db);
    }
}
