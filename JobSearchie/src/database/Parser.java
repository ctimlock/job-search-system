package database;

import entities.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Parser implements DBHelper {
    @Override
    public void close() throws SQLException {

    }

    @Override
    public void open(Connection conn) throws SQLException {

    }

    /**
     * TESTED
     * Takes a resultSet from a query to the user table and parses the data into an Admin object.
     *
     * @param result ResultSet from user table query.
     * @return Returns an Admin object.
     */
    public static Admin parseAdmin(ResultSet result) {
        User user = parseUser(result);
        if (user != null && user.getAccountType().equals("Admin")) {
            return new Admin(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getDateCreated());
        } else
            return null;
    }

    public static Job parseJob(ResultSet result, UserDB userDB, LocationDB locationDB) {
        Job job = new Job();
        try {
            job.setId(result.getInt(JobDB.Column.ID));
            job.setJobTitle(result.getString(JobDB.Column.JOBTITLE));
            job.setAuthor(userDB.getRecruiter(result.getString(JobDB.Column.RECRUITEREMAIL)));
            job.setDateCreated(result.getDate(JobDB.Column.DATECREATED));
            job.setDateListed(result.getDate(JobDB.Column.DATELISTED));
            job.setDateDeListed(result.getDate(JobDB.Column.DATEDELISTED));
            job.setCompany(result.getString(JobDB.Column.COMPANYNAME));
            job.setLocation(locationDB.getLocation(result.getInt(JobDB.Column.LOCATIONID)));
            job.setWorkType(result.getString(JobDB.Column.WORKTYPE));
            job.setWorkingArrangement(result.getString(JobDB.Column.WORKINGARRANGEMENT));
            job.setCompensation(result.getInt(JobDB.Column.COMPENSATION));
            job.setJobLevel(result.getString(JobDB.Column.JOBLEVEL));
            job.setDescription(result.getString(JobDB.Column.DESCRIPTION));
            job.setIsAdvertised(result.getBoolean(JobDB.Column.ISADVERTISED));
            return job;
        } catch (SQLException e) {
            System.out.println("Error parsing job: " + e.getMessage());
            return null;
        }
    }

    public static Application parseApplication(ResultSet result, UserDB userDB, UserKeywordDB userKeywordDB, LocationDB locationDB, JobDB jobDB, JobKeywordDB jobKeywordDB, JobCategoryDB jobCategoryDB) {
            try {
                Application application = new Application();
                application.setId(result.getInt(ApplicationDB.Column.ID));
                application.setJobSeeker(userDB.getJobSeeker(ApplicationDB.Column.USEREMAIL, userKeywordDB, locationDB));
                application.setJob(jobDB.getJob(result.getInt(ApplicationDB.Column.JOBID),userDB, locationDB, jobKeywordDB, jobCategoryDB));
                application.setCoverLetterDir(result.getString(ApplicationDB.Column.COVERLETTERDIR));
                application.setResumeDir(result.getString(ApplicationDB.Column.RESUMEDIR));
                application.setStatus(result.getString(ApplicationDB.Column.STATUS));
                application.setApplicationDate(result.getDate(ApplicationDB.Column.DATE));
                return application;
            } catch (SQLException e) {
                System.out.println("Error parsing Job Seeker from Result Set: " + e.getMessage());
                return null;
            }
    }

    /**
     * TESTED
     * Takes a resultSet from a query to the user table and parses the data into a user object.
     *
     * @param result ResultSet from user object.
     * @return Returns a user object.
     */
    public static JobSeeker parseJobSeeker(ResultSet result, LocationDB locationDB) {
        User user = parseUser(result);
        if (user != null && user.getAccountType().equals("Job Seeker")) {
            JobSeeker jobSeeker = new JobSeeker(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getDateCreated());
            try {
                jobSeeker.setCurrentJobName(result.getString(UserDB.Column.CURRENTJOBNAME));
                jobSeeker.setCurrentJobLevel(result.getString(UserDB.Column.CURRENTJOBLEVEL));
                jobSeeker.setContactNumber(result.getString(UserDB.Column.CONTACTNUMBER));
                jobSeeker.setResumeDir(result.getString(UserDB.Column.RESUMEDIR));
                jobSeeker.setLocation(locationDB.getLocation(result.getInt(UserDB.Column.LOCATIONID)));
                jobSeeker.setDateOfBirth(result.getDate(UserDB.Column.DATEOFBIRTH));
                return jobSeeker;
            } catch (SQLException e) {
                System.out.println("Error parsing Job Seeker from Result Set: " + e.getMessage());
                return null;
            }
        } else
            return null;
    }

    /**
     * TESTED
     * Takes a result set and converts it to a location.
     *
     * @param result ResultSet from a location table query.
     * @return Returns a location based on the query results, null if it cannot find data.
     */
    public static Location parseLocation(ResultSet result) {
        Location location = new Location();
        try {
            location.setId(result.getInt(LocationDB.Column.ID));
            location.setCountry(result.getString(LocationDB.Column.COUNTRY));
            location.setState(result.getString(LocationDB.Column.STATE));
            location.setCity(result.getString(LocationDB.Column.CITY));
            location.setPostcode(result.getString(LocationDB.Column.POSTCODE));
            return location;
        } catch (SQLException e) {
            System.out.println("Error parsing to Location: " + e.getMessage());
            return null;
        }
    }

    /**
     * TESTED
     * Takes a resultSet from a query to the user table and parses the data into a Recruiter object.
     *
     * @param result ResultSet from user table.
     * @return Returns a Recruiter object.
     */
    public static Recruiter parseRecruiter(ResultSet result) {
        User user = parseUser(result);
        if (user != null && user.getAccountType().equals("Recruiter")) {
            Recruiter recruiter = new Recruiter(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getDateCreated());
            try {
                recruiter.setCompanyName(result.getString(UserDB.Column.COMPANYNAME));
                recruiter.setRecruitingSpecialty(result.getString(UserDB.Column.RECRUITINGSPECIALTY));
                recruiter.setContactNumber(result.getString(UserDB.Column.CONTACTNUMBER));
                recruiter.setDateOfBirth(result.getDate(UserDB.Column.DATEOFBIRTH));
                return recruiter;
            } catch (SQLException e) {
                System.out.println("Error parsing Recruiter from Result Set: " + e.getMessage());
                return null;
            }
        } else
            return null;
    }

    /**
     * TESTED
     * Used to parse a result set to a generic User object.
     *
     * @param result The result set from a query to the user table or user view.
     * @return Returns a User object if the user email exists, null otherwise.
     */
    public static User parseUser(ResultSet result) {
        try {
            if (result.isClosed()) {
                return null;
            } else {
                User user = new User();
                user.setAccountType(result.getString(UserDB.Column.ACCOUNTTYPE));
                user.setFirstName(result.getString(UserDB.Column.FIRSTNAME));
                user.setLastName(result.getString(UserDB.Column.LASTNAME));
                user.setEmail(result.getString(UserDB.Column.EMAIL));
                user.setPassword(result.getString(UserDB.Column.PASSWORD));
                user.setDateCreated(result.getDate(UserDB.Column.DATECREATED));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Error parsing resultSet to user object: " + e.getMessage());
            return null;
        }
    }
}
