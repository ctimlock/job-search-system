package entities;

import java.util.Date;

public class JobSeeker extends User {
    private Date dateOfBirth;
    private String currentJobName;
    private String currentJobLevel;
    private int expectedCompensation;
    private String[] keywords;
    private String[] skillSet;

    public JobSeeker() {
        super();
        dateOfBirth = new Date();
        currentJobName = "";
        currentJobLevel = "";
        expectedCompensation = -1;
        keywords = new String[0];
        skillSet = new String[0];
    }

    public JobSeeker(Date dateOfBirth, String currentJobName, String currentJobLevel, int expectedCompensation, String[] keywords, String[] skillSet) {
        super();
        this.dateOfBirth = dateOfBirth;
        this.currentJobName = currentJobName;
        this.currentJobLevel = currentJobLevel;
        this.expectedCompensation = expectedCompensation;
        this.keywords = keywords;
        this.skillSet = skillSet;
    }

    public JobSeeker(String firstName, String lastName, Location location, String contactNumber, String email, String password, Date date, Boolean isLoggedIn, Date dateOfBirth, String currentJobName, String currentJobLevel, int expectedCompensation, String[] keywords, String[] skillSet) {
        super(firstName, lastName, location, contactNumber, email, password, date, isLoggedIn);
        this.dateOfBirth = dateOfBirth;
        this.currentJobName = currentJobName;
        this.currentJobLevel = currentJobLevel;
        this.expectedCompensation = expectedCompensation;
        this.keywords = keywords;
        this.skillSet = skillSet;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCurrentJobName() {
        return currentJobName;
    }

    public void setCurrentJobName(String currentJobName) {
        this.currentJobName = currentJobName;
    }

    public String getCurrentJobLevel() {
        return currentJobLevel;
    }

    public void setCurrentJobLevel(String currentJobLevel) {
        this.currentJobLevel = currentJobLevel;
    }

    public int getExpectedCompensation() {
        return expectedCompensation;
    }

    public void setExpectedCompensation(int expectedCompensation) {
        this.expectedCompensation = expectedCompensation;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public String[] getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(String[] skillSet) {
        this.skillSet = skillSet;
    }
}
