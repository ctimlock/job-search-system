import Controllers.Login;
import Controllers.UserHandler;
import database.DatabaseManager;
import entities.Session;

import javax.swing.*;
import java.sql.SQLException;

/**
 * This is the driver class of the program.
 * @author  Team R
 * @version 1.0
 */
public class JobSearchie
{
    private Session session;
    private DatabaseManager db;
    private JFrame frameInit;

    /**
     * This is the main method which begins the program execution.
     * @param args  An array of string passed in as command line parameters.
     * @throws SQLException If there is an access error with the database.
     */
    public static void main(String[] args) throws SQLException
    {
        JobSearchie program = new JobSearchie();
        program.frameInit = new JFrame();
        program.db = new DatabaseManager();
        Login loginRegistration = new Login();
        program.session = new Session(loginRegistration.beginLoginOrRegistration(program.db));
        UserHandler userHandler = program.session.getUserHandler();
        //program.run();
    }

    /**
     *
     */
    public void run() {
        DatabaseManager db = new DatabaseManager();



        db.close();
    }

    /**
     * This method closes the database and terminates the program.
     */
    public void exit() {
        db.close();
        System.exit(1);
    }
}
