package testing;

import Controllers.RecruiterHandler;
import database.DatabaseManager;
import entities.Recruiter;

import java.io.IOException;
import java.sql.SQLException;

public class RecruiterScreenTest {

    public static void main(String[] args) throws SQLException, IOException {
        DatabaseManager db = new DatabaseManager();
        Recruiter recruiter = new Recruiter();
        RecruiterHandler recruiterHandler = new RecruiterHandler();
        recruiterHandler.home(recruiter, db);
    }

}
