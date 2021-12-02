import Controllers.Login;
import Controllers.UserHandler;
import database.DatabaseManager;
import entities.Session;

import javax.swing.*;
import java.sql.SQLException;

public class JobSearchie
{
    private Session session;
    private DatabaseManager db;
    private JFrame frameInit;

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

    public void run() {
        DatabaseManager db = new DatabaseManager();



        db.close();
    }

    public void exit() {
        db.close();
        System.exit(1);
    }
}
