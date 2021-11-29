package database;

import static database.UserDB.Column.*;
import static database.UserDB.View.*;

public class UserDB {
    public static final String NAME = "user";

    public static class View{}

    public static class Column{
        public static final String ID = "id";
        public static final String ACCOUNTTYPE = "accountType";
        public static final String FIRSTNAME = "firstName";
        public static final String LASTNAME = "lastName";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String LOCATIONID = "locationId";
        public static final String CONTACTNUMBER = "contactNumber";
        public static final String DATECREATED = "dateCreated";
        public static final String DATEOFBIRTH = "dateOfBirth";
        public static final String CURRENTJOBNAME = "currentJobName";
        public static final String CURRENTJOBLEVEL = "currentJobLevel";
        public static final String EXPECTEDCOMPENSATION = "expectedCompensation";
        public static final String RESUMEDIR = "resumeDir";
        public static final String COMPANYNAME = "companyName";
        public static final String RECRUITINGSPECIALTY = "recruitingSpecialty";
    }

    public static class Query{
        public static final String USER_BY_EMAIL = "SELECT * FROM " + NAME + " WHERE " + EMAIL + " = ?";
        public static final String USER_BY_ID = "SELECT * FROM " + NAME + " WHERE " + EMAIL + " = ?";
        public static final String JOBSEEKER_ID_BY_EMAIL = "SELECT " + ID + " FROM " + NAME + " WHERE " + EMAIL + " = ?";
        public static final String ACCOUNTTYPE_BY_EMAIL = "SELECT " + ACCOUNTTYPE + " FROM " + NAME + " WHERE " + EMAIL + " = ?";
        private static final String RECRUITER_ID_BY_EMAIL = "SELECT " + ID + " FROM " + NAME + " WHERE " + EMAIL + " = ?";
        private static final String RECRUITER_BY_EMAIL = "SELECT * FROM " + NAME + " WHERE " + EMAIL + " = ?";
    }

    public static class Insert{
        public static final String JOBSEEKER = "INSERT INTO " + NAME + " (" + ACCOUNTTYPE + ", " + FIRSTNAME + ", " + LASTNAME + ", " + EMAIL + ", " + PASSWORD + ", " + LOCATIONID + ", " + CONTACTNUMBER + ", " + DATECREATED + ", " + DATEOFBIRTH + ", " + CURRENTJOBNAME + ", " + CURRENTJOBLEVEL + ", " + EXPECTEDCOMPENSATION + ", " + RESUMEDIR + ") VALUES ('Job Seeker', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        public static final String RECRUITER = "INSERT INTO " + NAME + " (" + ACCOUNTTYPE + ", " + FIRSTNAME + ", " + LASTNAME + ", " + EMAIL + ", " + PASSWORD + ", " + DATECREATED + ", " + COMPANYNAME + ", " + RECRUITINGSPECIALTY + ", " + CONTACTNUMBER + ", " + DATEOFBIRTH + ") VALUES ('Recruiter', ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        public static final String ADMIN = "INSERT INTO " + NAME + " (" + ACCOUNTTYPE + ", " + FIRSTNAME + ", " + LASTNAME + ", " + EMAIL + ", " + PASSWORD + ", " + DATECREATED + ") VALUES ('Admin', ?, ?, ?, ?, ?)";

    }

    public static class Update{}

    public static class Delete{}
}
