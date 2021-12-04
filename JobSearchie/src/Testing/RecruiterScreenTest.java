package Testing;

import Controllers.RecruiterHandler;
import Database.DatabaseManager;
import Entities.Recruiter;

import java.io.IOException;
import java.sql.SQLException;

public class RecruiterScreenTest {

    public static void main(String[] args) throws SQLException, IOException {
        DatabaseManager db = new DatabaseManager();
        Recruiter recruiter = db.getRecruiter("tim_@monash.org.au");
        RecruiterHandler recruiterHandler = new RecruiterHandler();
        recruiterHandler.home(recruiter, db);
    }

}
