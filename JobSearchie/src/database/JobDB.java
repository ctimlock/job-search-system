package database;

import static database.JobDB.Column.*;

public class JobDB {
    public static final String NAME = "job";

    public static class View{}

    public static class Column{

        public static final String ID = "id";
        public static final String JOBTITLE = "jobTitle";
        public static final String COMPANYNAME = "companyName";
        public static final String CATEGORYID = "categoryId";
        public static final String LOCATIONID = "locationId";
        public static final String WORKTYPE = "workType";
        public static final String WORKINGARRANGEMENT = "workingArrangement";
        public static final String COMPENSATION = "compensation";
        public static final String JOBLEVEL = "jobLevel";
        public static final String DESCRIPTION = "description";
        public static final String DATECREATED = "dateCreated";
    }

    public static class Query{}

    public static class Insert{
        public static final String JOB = "INSERT INTO " + NAME + " (" + ID + ", " + JOBTITLE + ", " + COMPANYNAME + ", " + CATEGORYID + ", " + LOCATIONID + ", " + WORKTYPE + ", " + WORKINGARRANGEMENT + ", " + COMPENSATION + ", " + JOBLEVEL + ", " + DESCRIPTION + ", " + DATECREATED + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    }

    public static class Update{}

    public static class Delete{}
}
