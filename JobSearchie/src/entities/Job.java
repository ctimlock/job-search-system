package entities;

import java.util.ArrayList;
import java.util.Date;

public class Job {

    private String jobTitle;
    private Date postDate;
    private String company;
    private ArrayList<String> listOfCategories;
    private Location location;
    private String workType;
    private String workingArrangement;
    private String description;
    private String advertisingStatus;
    private ArrayList<String> keywords;
    private int uniqueIdentifier;
    private ArrayList<Application> applications;
    private ArrayList<Complaint> complaints;

    public Job() {
        this.jobTitle = "";
        this.postDate = new Date();
        this.company = "";
        this.listOfCategories = new ArrayList<String>();
        this.location = new Location();
        this.workType = "";
        this.workingArrangement = "";
        this.description = "";
        this.advertisingStatus = "";
        this.keywords = new ArrayList<String>();
        this.uniqueIdentifier = -1;
        this.applications = new ArrayList<Application>();
        this.complaints = new ArrayList<Complaint>();
    }

    public Job(String jobTitle, Date postDate, String company, ArrayList<String> listOfCategories, Location location, String workType, String workingArrangement, String description, String advertisingStatus, ArrayList<String> keywords, int uniqueIdentifier) {
        this.jobTitle = jobTitle;
        this.postDate = postDate;
        this.company = company;
        this.listOfCategories = listOfCategories;
        this.location = location;
        this.workType = workType;
        this.workingArrangement = workingArrangement;
        this.description = description;
        this.advertisingStatus = advertisingStatus;
        this.keywords = keywords;
        this.uniqueIdentifier = uniqueIdentifier;
        this.applications = new ArrayList<Application>();
        this.complaints = new ArrayList<Complaint>();
    }

    public Job(String jobTitle, Date postDate, String company, ArrayList<String> listOfCategories, Location location, String workType, String workingArrangement, String description, String advertisingStatus, ArrayList<String> keywords, int uniqueIdentifier, ArrayList<Application> applications, ArrayList<Complaint> complaints) {
        this.jobTitle = jobTitle;
        this.postDate = postDate;
        this.company = company;
        this.listOfCategories = listOfCategories;
        this.location = location;
        this.workType = workType;
        this.workingArrangement = workingArrangement;
        this.description = description;
        this.advertisingStatus = advertisingStatus;
        this.keywords = keywords;
        this.uniqueIdentifier = uniqueIdentifier;
        this.applications = applications;
        this.complaints = complaints;
    }

    public void display() {
        System.out.print(jobTitle + ",");
        System.out.print(postDate + ",");
        System.out.print(company + ",");
        listOfCategories.forEach(System.out::print);
        System.out.print(location + ",");
        System.out.print(workType + ",");
        System.out.print(workingArrangement + ",");
        System.out.print(description + ",");
        System.out.print(advertisingStatus + ",");
        keywords.forEach(System.out::print);
        System.out.print(uniqueIdentifier + ",");
        applications.forEach(System.out::print);
        complaints.forEach(System.out::print);
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Date getPostDate() {
        return postDate;
    }

    public String getCompany() {
        return company;
    }

    public ArrayList<String> getListOfCategories() {
        return listOfCategories;
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

    public String getDescription() {
        return description;
    }

    public String getAdvertisingStatus() {
        return advertisingStatus;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public int getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public ArrayList<Application> getApplications() {
        return applications;
    }

    public ArrayList<Complaint> getComplaints() {
        return complaints;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setListOfCategories(ArrayList<String> listOfCategories) {
        this.listOfCategories = listOfCategories;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdvertisingStatus(String advertisingStatus) {
        this.advertisingStatus = advertisingStatus;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public void setUniqueIdentifier(int uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public void setApplications(ArrayList<Application> applications) {
        this.applications = applications;
    }

    public void setComplaints(ArrayList<Complaint> complaints) {
        this.complaints = complaints;
    }
}
