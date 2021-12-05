package Testing;

import Controllers.Login;
import Database.DatabaseManager;

import javax.swing.*;

public class LoginAndRegistrationTest
{
    public static void main(String[] args)
    {
        Login login = new Login();
        DatabaseManager db = new DatabaseManager();
        JFrame frame = new JFrame();
        login.beginLoginOrRegistration(db);
    }
}
