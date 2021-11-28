package entities;

import java.util.ArrayList;
import java.util.Date;

public class Job {
    private int id;
    private String jobTitle;
    private Date postDate;
    private String company;
    private String category;
    private Location location;
    private String workType;
    private String workingArrangement;
    private int compensation;
    private String jobLevel;
    private String description;
    private String advertisingStatus;
    private ArrayList<String> keywords;
    private ArrayList<Application> applications;
    private ArrayList<Complaint> complaints;

    public Job() {
        this.id = -1;
        this.jobTitle = "";
        this.postDate = new Date();
        this.company = "";
        this.category = "";
        this.location = new Location();
        this.workType = "";
        this.workingArrangement = "";
        this.compensation = -1;
        this.jobLevel = "";
        this.description = "";
        this.advertisingStatus = "";
        this.keywords = new ArrayList<String>();
        this.applications = new ArrayList<Application>();
        this.complaints = new ArrayList<Complaint>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public Job(String jobTitle, Date postDate, String company, String category, Location location, String workType, String workingArrangement, int compensation, String jobLevel, String description, String advertisingStatus, ArrayList<String> keywords, ArrayList<Application> applications, ArrayList<Complaint> complaints) {
        this.jobTitle = jobTitle;
        this.postDate = postDate;
        this.company = company;
        this.category = category;
        this.location = location;
        this.workType = workType;
        this.workingArrangement = workingArrangement;
        this.compensation = compensation;
        this.jobLevel = jobLevel;
        this.description = description;
        this.advertisingStatus = advertisingStatus;
        this.keywords = keywords;
        this.applications = applications;
        this.complaints = complaints;
    }

    public void display() {
        System.out.println(jobTitle);
        System.out.println(postDate);
        System.out.println(company);
        System.out.println(category);
        System.out.println(location.getCountry() + " " + location.getState() + " " + location.getCity() + " " + location.getPostcode());
        System.out.println(workType + ",");
        System.out.println(workingArrangement);
        System.out.println(compensation);
        System.out.println(description);
        System.out.println(advertisingStatus);
        keywords.forEach(System.out::print);
        applications.forEach(System.out::print);
        complaints.forEach(System.out::print);
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Date getPostDate() {
        return postDate;
    }

    public String getCategory() {
        return category;
    }

    public String getCompany() {
        return company;
    }

    public int getCompensation() {
        return compensation;
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

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setCompensation(int compensation) {
        this.compensation = compensation;
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

    public void setApplications(ArrayList<Application> applications) {
        this.applications = applications;
    }

    public void setComplaints(ArrayList<Complaint> complaints) {
        this.complaints = complaints;
    }
}
