package entities;

import java.sql.Date;

public class Application {
    private int id;
    private JobSeeker jobSeeker;
    private Job job;
    private String coverLetterDir;
    private String resumeDir;
    private String status;
    private Date applicationDate;

    public Application() {
        id = -1;
        jobSeeker = null;
        job = null;
        coverLetterDir = "";
        resumeDir = "";
        status = "";
        applicationDate = null;
    }

    public Application(JobSeeker jobSeeker, Job job,  String coverLetterDir, String resumeDir, String status, Date applicationDate) {
        id = -1;
        this.jobSeeker = jobSeeker;
        this.job = job;
        this.coverLetterDir = coverLetterDir;
        this.resumeDir = resumeDir;
        this.status = status;
        this.applicationDate = applicationDate;
    }

    public Application(int id, JobSeeker jobSeeker, Job job,  String coverLetterDir, String resumeDir, String status, Date applicationDate) {
        this.id = -1;
        this.jobSeeker = jobSeeker;
        this.job = job;
        this.coverLetterDir = coverLetterDir;
        this.resumeDir = resumeDir;
        this.status = status;
        this.applicationDate = applicationDate;
    }

    public void display() {
        System.out.println("id: " + id);
        System.out.println("coverLetterDir: " + coverLetterDir);
        System.out.println("resumeDir: " + resumeDir);
        System.out.println("status: " + status);
        System.out.println("applicationDate: " + applicationDate);
        System.out.println("JOB SEEKER--");
        jobSeeker.display();
        System.out.println("JOB--");
        job.display();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getCoverLetterDir() {
        return coverLetterDir;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public String getResumeDir() {
        return resumeDir;
    }

    public String getStatus() {
        return status;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setCoverLetterDir(String coverLetterDir) {
        this.coverLetterDir = coverLetterDir;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public void setResumeDir(String resumeDir) {
        this.resumeDir = resumeDir;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setApplicationDate(Date applicationDate) {this.applicationDate = applicationDate;}
}
