package entities;

import java.util.Date;
import java.util.ArrayList;

public class JobSeeker extends User {
    private String currentJobName;
    private String currentJobLevel;
    private int expectedCompensation;
    private ArrayList<String> keywords;
    private ArrayList<String> skillSet;
    private String resumeDir;
    private String coverLetterDir;

    public JobSeeker() {
        super();
        currentJobName = "";
        currentJobLevel = "";
        expectedCompensation = -1;
        keywords = new ArrayList<>();
        skillSet = new ArrayList<>();
        resumeDir = "";
        coverLetterDir = "";
    }
    public JobSeeker(String firstName, String lastName, Location location, String contactNumber, String email,
                     String password, Date dateOfBirth, Date dateCreated) {
        super(firstName, lastName, location, contactNumber, email, password, dateOfBirth, dateCreated);
        this.currentJobName = "";
        this.currentJobLevel = "";
        this.expectedCompensation = -1;
        this.keywords = new ArrayList<>();
        this.skillSet = new ArrayList<>();
        this.resumeDir = "";
        this.coverLetterDir = "";
    }


    public JobSeeker(int id, String firstName, String lastName, Location location, String contactNumber, String email,
                     String password, Date dateOfBirth, Date dateCreated, String currentJobName, String currentJobLevel,
                     int expectedCompensation, ArrayList<String> keywords, ArrayList<String> skillSet, String resumeDir, String coverLetterDir) {
        super(id, firstName, lastName, location, contactNumber, email, password, dateOfBirth, dateCreated);
        this.currentJobName = currentJobName;
        this.currentJobLevel = currentJobLevel;
        this.expectedCompensation = expectedCompensation;
        this.keywords = keywords;
        this.skillSet = skillSet;
        this.resumeDir = resumeDir;
        this.coverLetterDir = coverLetterDir;
    }

    public String getCoverLetterDir() {
        return coverLetterDir;
    }

    public String getCurrentJobLevel() {
        return currentJobLevel;
    }

    public String getCurrentJobName() {
        return currentJobName;
    }

    public int getExpectedCompensation() {
        return expectedCompensation;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public String getResumeDir() {
        return resumeDir;
    }

    public ArrayList<String> getSkillSet() {
        return skillSet;
    }

    public void setCoverLetterDir(String coverLetterDir) {
        this.coverLetterDir = coverLetterDir;
    }

    public void setCurrentJobLevel(String currentJobLevel) {
        this.currentJobLevel = currentJobLevel;
    }

    public void setCurrentJobName(String currentJobName) {
        this.currentJobName = currentJobName;
    }

    public void setExpectedCompensation(int expectedCompensation) {
        this.expectedCompensation = expectedCompensation;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public void setResumeDir(String resumeDir) {
        this.resumeDir = resumeDir;
    }

    public void setSkillSet(ArrayList<String> skillSet) {
        this.skillSet = skillSet;
    }




}