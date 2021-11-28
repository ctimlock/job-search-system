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
    private boolean isAdvertised;
    private ArrayList<String> keywords;

    public Job() {
        this.id = -1;
        this.jobTitle = "";
        this.postDate = null;
        this.company = "";
        this.category = "";
        this.location = null;
        this.workType = "";
        this.workingArrangement = "";
        this.compensation = -1;
        this.jobLevel = "";
        this.description = "";
        this.isAdvertised = false;
        this.keywords = null;
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

    public Job(String jobTitle, Date postDate, String company, String category, Location location, String workType, String workingArrangement, int compensation, String jobLevel, String description, boolean isAdvertised, ArrayList<String> keywords) {
        id = -1;
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
        this.isAdvertised = isAdvertised;
        this.keywords = keywords;
    }

    public Job(int id, String jobTitle, Date postDate, String company, String category, Location location, String workType, String workingArrangement, int compensation, String jobLevel, String description, boolean isAdvertised, ArrayList<String> keywords) {
        this.id = id;
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
        this.isAdvertised = isAdvertised;
        this.keywords = keywords;
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
        System.out.println(isAdvertised);
        keywords.forEach(System.out::print);
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

    public boolean getIsAdvertised() {
        return isAdvertised;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
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

    public void setAdvertisingStatus(boolean isAdvertised) {
        this.isAdvertised = isAdvertised;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }
}
