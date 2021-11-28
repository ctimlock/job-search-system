package entities;

import java.util.ArrayList;
import java.util.Date;

public class JobSeeker extends User {
    private String currentJobName;
    private String currentJobLevel;
    private Location location;
    private String contactNumber;
    private Date dateOfBirth;
    private int expectedCompensation;
    private ArrayList<String> keywords;
    private String resumeDir;

    public JobSeeker() {
        super();
        currentJobName = "";
        currentJobLevel = "";
        contactNumber = "";
        resumeDir = "";
        location = null;
        dateOfBirth = null;
        keywords = null;
        expectedCompensation = -1;
    }

    public JobSeeker(String firstName, String lastName, String email, String password, Date dateCreated) {
        super(firstName, lastName, email, password, dateCreated);
        currentJobName = "";
        currentJobLevel = "";
        contactNumber = "";
        resumeDir = "";
        location = null;
        dateOfBirth = null;
        keywords = null;
        expectedCompensation = -1;
    }

    public JobSeeker(int id, String firstName, String lastName, String email, String password, Date dateCreated) {
        super(id, firstName, lastName, email, password, dateCreated);
        currentJobName = "";
        currentJobLevel = "";
        contactNumber = "";
        resumeDir = "";
        location = null;
        dateOfBirth = null;
        keywords = null;
        expectedCompensation = -1;
    }

    public JobSeeker(String firstName, String lastName, String email, String password, Date dateCreated, String currentJobName, String currentJobLevel, String contactNumber, String resumeDir, Location location, Date dateOfBirth, ArrayList<String> keywords, int expectedCompensation) {
        super(firstName, lastName, email, password, dateCreated);
        this.currentJobName = currentJobName;
        this.currentJobLevel = currentJobLevel;
        this.contactNumber = contactNumber;
        this.resumeDir = resumeDir;
        this.location = location;
        this.dateOfBirth = dateOfBirth;
        this.keywords = keywords;
        this.expectedCompensation = expectedCompensation;
    }

    public JobSeeker(int id, String firstName, String lastName, String email, String password, Date dateCreated, String currentJobName, String currentJobLevel, String contactNumber, String resumeDir, Location location, Date dateOfBirth, ArrayList<String> keywords, int expectedCompensation) {
        super(id, firstName, lastName, email, password, dateCreated);
        this.currentJobName = currentJobName;
        this.currentJobLevel = currentJobLevel;
        this.contactNumber = contactNumber;
        this.resumeDir = resumeDir;
        this.location = location;
        this.dateOfBirth = dateOfBirth;
        this.keywords = keywords;
        this.expectedCompensation = expectedCompensation;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getCurrentJobLevel() {
        return currentJobLevel;
    }

    public String getCurrentJobName() {
        return currentJobName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getExpectedCompensation() {
        return expectedCompensation;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public Location getLocation() {
        return location;
    }

    public String getResumeDir() {
        return resumeDir;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setCurrentJobLevel(String currentJobLevel) {
        this.currentJobLevel = currentJobLevel;
    }

    public void setCurrentJobName(String currentJobName) {
        this.currentJobName = currentJobName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setExpectedCompensation(int expectedCompensation) {
        this.expectedCompensation = expectedCompensation;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setResumeDir(String resumeDir) {
        this.resumeDir = resumeDir;
    }
}