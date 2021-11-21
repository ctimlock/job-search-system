package entities;

import java.util.Date;
import java.util.ArrayList;

public class JobSeeker extends User {
    private Date dateOfBirth;
    private String currentJobName;
    private String currentJobLevel;
    private int expectedCompensation;
    private ArrayList<String> keywords;
    private String skillSet;
    private String resume;
    private String coverLetter;

    public JobSeeker() {
        super();
        dateOfBirth = new Date();
        currentJobName = "";
        currentJobLevel = "";
        expectedCompensation = -1;
        keywords = new ArrayList<String>();
        skillSet = "";
        resume = "";
        coverLetter = "";
    }

    public JobSeeker(Date dateOfBirth, String currentJobName, String currentJobLevel, int expectedCompensation, ArrayList<String> keywords, String skillSet, String resume, String coverLetter) {
        super();
        this.dateOfBirth = dateOfBirth;
        this.currentJobName = currentJobName;
        this.currentJobLevel = currentJobLevel;
        this.expectedCompensation = expectedCompensation;
        this.keywords = keywords;
        this.skillSet = skillSet;
        this.resume = resume;
        this.coverLetter = coverLetter;
    }
    /* this is an additional constructor that I didn't have.
     TODO: resolve this stuff
    public JobSeeker(String firstName, String lastName, Location location, String contactNumber, String email, String password, Date date, Boolean isLoggedIn, Date dateOfBirth, String currentJobName, String currentJobLevel, int expectedCompensation, String[] keywords, String[] skillSet) {
        super(firstName, lastName, location, contactNumber, email, password, date, isLoggedIn);
        this.dateOfBirth = dateOfBirth;
        this.currentJobName = currentJobName;
        this.currentJobLevel = currentJobLevel;
        this.expectedCompensation = expectedCompensation;
        this.keywords = keywords;
        this.skillSet = skillSet;
    }  */

    public String getCoverLetter() {
        return coverLetter;
    }

    public String getCurrentJobLevel() {
        return currentJobLevel;
    }

    public void displayJobSeeker(){

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

    public String getResume() {
        return resume;
    }

    public String getSkillSet() {
        return skillSet;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
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

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }




}