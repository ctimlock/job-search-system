package entities;

import utilities.UserIO;

import java.util.ArrayList;
import java.sql.Date;

public class Job {
    private int id;
    private String jobTitle;
    private Recruiter author;
    private Date dateCreated;
    private Date dateListed;
    private Date dateDeListed;
    private String company;
    private ArrayList<String> categories;
    private Location location;
    private String workType;
    private String workingArrangement;
    private int compensation;
    private String jobLevel;
    private String description;
    private boolean isAdvertised;
    private ArrayList<String> keywords;

    public Job() {
        id = -1;
        jobTitle = "";
        author = null;
        dateCreated = null;
        dateListed = null;
        dateDeListed = null;
        company = "";
        categories = null;
        location = null;
        workType = "";
        workingArrangement = "";
        compensation = -1;
        jobLevel = "";
        description = "";
        isAdvertised = false;
        keywords = null;
    }

    public Job(String jobTitle, Recruiter author, Date dateCreated, Date dateListed, Date dateDeListed, String company, ArrayList<String> categories, Location location, String workType, String workingArrangement, int compensation, String jobLevel, String description, boolean isAdvertised, ArrayList<String> keywords) {
        id = -1;
        this.jobTitle = jobTitle;
        this.author = author;
        this.dateCreated = dateCreated;
        this.dateListed = dateListed;
        this.dateDeListed = dateDeListed;
        this.company = company;
        this.categories = categories;
        this.location = location;
        this.workType = workType;
        this.workingArrangement = workingArrangement;
        this.compensation = compensation;
        this.jobLevel = jobLevel;
        this.description = description;
        this.isAdvertised = isAdvertised;
        this.keywords = keywords;
    }

    public Job(int id, String jobTitle, Recruiter author, Date dateCreated, Date dateListed, Date dateDeListed, String company, ArrayList<String> categories, Location location, String workType, String workingArrangement, int compensation, String jobLevel, String description, boolean isAdvertised, ArrayList<String> keywords) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.author = author;
        this.dateCreated = dateCreated;
        this.dateListed = dateListed;
        this.dateDeListed = dateDeListed;
        this.company = company;
        this.categories = categories;
        this.location = location;
        this.workType = workType;
        this.workingArrangement = workingArrangement;
        this.compensation = compensation;
        this.jobLevel = jobLevel;
        this.description = description;
        this.isAdvertised = isAdvertised;
        this.keywords = keywords;
    }

    public void display() {
        UserIO.displayBody("Job title: " + jobTitle);
        UserIO.displayBody("Date created: " + dateCreated);
        UserIO.displayBody("Date listed: " + dateListed);
        UserIO.displayBody("Date de-listed: " + dateDeListed);
        UserIO.displayBody("Company: " + company);
        UserIO.displayBody("Categories: ");
        UserIO.displayArrayList(categories);
        UserIO.displayBody("Location: " + location.getCountry() + " " + location.getState() + " " + location.getCity() + " " + location.getPostcode());
        UserIO.displayBody("Work type: " + workType);
        UserIO.displayBody("Working arrangement: " + workingArrangement);
        UserIO.displayBody("Compensation: " + compensation);
        UserIO.displayBody("Job level: " + jobLevel);
        UserIO.displayBody("Description: " + description);
        UserIO.displayBody("Currently advertised: " + isAdvertised);
        UserIO.displayBody("Keywords: ");
        UserIO.displayArrayList(keywords);
    }

    public Recruiter getAuthor() {
        return author;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public String getCompany() {
        return company;
    }

    public int getCompensation() {
        return compensation;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateDeListed() {
        return dateDeListed;
    }

    public Date getDateListed() {
        return dateListed;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public Location getLocation() {
        return location;
    }

    public String getWorkType() {
        return workType;
    }

    public String getWorkingArrangement() {
        return workingArrangement;
    }

    public boolean getIsAdvertised() {
        return isAdvertised;
    }

    public void setAuthor(Recruiter author) {
        this.author = author;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setCompensation(int compensation) {
        this.compensation = compensation;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateDeListed(Date dateDeListed) {
        this.dateDeListed = dateDeListed;
    }

    public void setDateListed(Date dateListed) {
        this.dateListed = dateListed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsAdvertised(boolean isAdvertised) {
        this.isAdvertised = isAdvertised;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public void setWorkingArrangement(String workingArrangement) {
        this.workingArrangement = workingArrangement;
    }

}
