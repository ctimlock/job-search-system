package entities;

import java.sql.Date;

/**
 * @author Charlie Timlock
 * @version 1.0
 * This class contains the details of an interview invitation within the Job Searchie system.
 */
public class Invitation
{
    private int id;
    private JobSeeker jobSeeker;
    private Recruiter recruiter;
    private Job job;
    private Date dateSent;
    private Date dateOfInterview;
    private Location locationOfInterview;
    private String attachedMessage;
    private String typeOfInterview;
    private boolean accepted;

    public Invitation() {
        id = -1;
        jobSeeker = null;
        recruiter = null;
        job = null;
        dateSent = null;
        dateOfInterview = null;
        locationOfInterview = null;
        attachedMessage = null;
        typeOfInterview = null;
        accepted = false;
    }

    public Invitation(JobSeeker jobSeeker, Recruiter recruiter, Job job, Date dateSent, Date dateOfInterview, Location locationOfInterview, String attachedMessage, String typeOfInterview, boolean accepted) {
        id = -1;
        this.jobSeeker = jobSeeker;
        this.recruiter = recruiter;
        this.job = job;
        this.dateSent = dateSent;
        this.dateOfInterview = dateOfInterview;
        this.locationOfInterview = locationOfInterview;
        this.attachedMessage = attachedMessage;
        this.typeOfInterview = typeOfInterview;
        this.accepted = accepted;
    }

    public Invitation(int id, JobSeeker jobSeeker, Recruiter recruiter, Job job, Date dateSent, Date dateOfInterview, Location locationOfInterview, String attachedMessage, String typeOfInterview, boolean accepted) {
        this.id = id;
        this.jobSeeker = jobSeeker;
        this.recruiter = recruiter;
        this.job = job;
        this.dateSent = dateSent;
        this.dateOfInterview = dateOfInterview;
        this.locationOfInterview = locationOfInterview;
        this.attachedMessage = attachedMessage;
        this.typeOfInterview = typeOfInterview;
        this.accepted = accepted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public Date getDateOfInterview() {
        return dateOfInterview;
    }

    public void setDateOfInterview(Date dateOfInterview) {
        this.dateOfInterview = dateOfInterview;
    }

    public Location getLocationOfInterview() {
        return locationOfInterview;
    }

    public void setLocationOfInterview(Location locationOfInterview) {
        this.locationOfInterview = locationOfInterview;
    }

    public String getAttachedMessage() {
        return attachedMessage;
    }

    public void setAttachedMessage(String attachedMessage) {
        this.attachedMessage = attachedMessage;
    }

    public String getTypeOfInterview() {
        return typeOfInterview;
    }

    public void setTypeOfInterview(String typeOfInterview) {
        this.typeOfInterview = typeOfInterview;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
