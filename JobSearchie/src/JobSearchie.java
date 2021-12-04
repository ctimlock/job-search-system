import Controllers.*;
import database.DatabaseManager;
import entities.*;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import utilities.UserIO;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
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
    public static void main(String[] args)
    {
        JobSearchie program = new JobSearchie();
        program.frameInit = new JFrame();
        program.db = new DatabaseManager();
        Login loginRegistration = new Login();
        program.session = new Session(loginRegistration.beginLoginOrRegistration(program.db));

        try
        {
            switch (program.session.getUserType())
            {
                case ("Admin") -> {
                    /* Admin home not yet implemented!!!
                    AdminHandler handler = new AdminHandler();
                    Admin admin = (Admin) program.session.getUser();
                    handler.home(admin, program.db);*/
                }
                case ("Recruiter") -> {
                    RecruiterHandler handler = new RecruiterHandler();
                    Recruiter recruiter = (Recruiter) program.session.getUser();
                    handler.home(recruiter, program.db);
                }
                case ("JobSeeker") -> {
                    JobSeekerHandler handler = new JobSeekerHandler();
                    JobSeeker jobSeeker = (JobSeeker) program.session.getUser();
                    handler.home(jobSeeker, program.db);
                }
            }

            program.session.setLogoutTime(new Date(System.currentTimeMillis()));
            program.db.insertSession(program.session);
        } catch (Exception e)
        {
            //Badnews
        }

        program.exit();
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
