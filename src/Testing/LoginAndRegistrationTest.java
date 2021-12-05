package Testing;

import Controllers.LoginHandler;
import Database.DatabaseManager;

import javax.swing.*;

public class LoginAndRegistrationTest
{
    public static void main(String[] args)
    {
        LoginHandler loginHandler = new LoginHandler();
        DatabaseManager db = new DatabaseManager();
        JFrame frame = new JFrame();
        loginHandler.startLogin(db);
    }
}
