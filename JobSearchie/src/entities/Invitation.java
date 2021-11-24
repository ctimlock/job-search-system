package entities;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

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
    private OffsetDateTime timeStamp;
    private Location location;
    private Message message;
    private String type;


    //TODO: Javadoc
    public Invitation()
    {
        this.id = 0;
        this.jobSeeker = new JobSeeker( );
        this.recruiter = new Recruiter();
        this.timeStamp = OffsetDateTime.of(0,0,0,0,0,0,0, ZoneOffset.of("+0"));
        this.location = new Location();
        this.message = new Message();
        this.type = "";
    }

    //TODO: Javadoc
    public Invitation(int id, JobSeeker jobSeeker, Recruiter recruiter, OffsetDateTime timeStamp, Location location, Message message, String type)
    {
        this.id = id;
        this.jobSeeker = jobSeeker;
        this.recruiter = recruiter;
        this.timeStamp = timeStamp;
        this.location = location;
        this.message = message;
        this.type = type;
    }

    //TODO: Javadoc
    public void displayInvitation(OffsetDateTime appliedDate, OffsetDateTime listedDate, String jobTitle, String companyName, String jobCategory)
    {
        System.out.println("Job Title: " + jobTitle);
        System.out.println("Posted: " + listedDate);
        System.out.println("Applied: " + appliedDate);
        System.out.println("Company: " + companyName);
        System.out.println("Category: " + jobCategory);
        System.out.println("Job Interview Date: " + this.timeStamp);
        System.out.println("Interviewers: " + this.timeStamp);
        System.out.println("Meeting location: " + this.location);
    }

    //TODO: Javadoc
    public int getId()
    {
        return id;
    }

    //TODO: Javadoc
    public JobSeeker getJobSeeker()
    {
        return jobSeeker;
    }

    //TODO: Javadoc
    public Location getLocation()
    {
        return location;
    }

    //TODO: Javadoc
    public Message getMessage()
    {
        return message;
    }

    //TODO: Javadoc
    public Recruiter getRecruiter()
    {
        return recruiter;
    }

    //TODO: Javadoc
    public OffsetDateTime getTimeStamp()
    {
        return timeStamp;
    }

    //TODO: Javadoc
    public String getType()
    {
        return type;
    }

    //TODO: Javadoc
    public void setId(int id)
    {
        this.id = id;
    }

    //TODO: Javadoc
    public void setJobSeeker(JobSeeker jobSeeker)
    {
        this.jobSeeker = jobSeeker;
    }

    //TODO: Javadoc
    public void setLocation(Location location)
    {
        this.location = location;
    }

    //TODO: Javadoc
    public void setMessage(Message message)
    {
        this.message = message;
    }

    //TODO: Javadoc
    public void setRecruiter(Recruiter recruiter)
    {
        this.recruiter = recruiter;
    }

    //TODO: Javadoc
    public void setTimeStamp(OffsetDateTime timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    //TODO: Javadoc
    public void setType(String type)
    {
        this.type = type;
    }
}