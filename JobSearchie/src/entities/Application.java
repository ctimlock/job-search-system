package entities;

import java.util.ArrayList;

public class Application {
    private JobSeeker jobSeeker;
    private String coverLetter;
    private String resume;
    private ArrayList<String> questionAnswers;
    private String status;

    public Application() {
        jobSeeker = new JobSeeker();
        coverLetter = "";
        resume = "";
        questionAnswers = new ArrayList<String>();
        status = "";
    }

    public Application(JobSeeker jobSeeker, String coverLetter, String resume, ArrayList<String> questionAnswers, String status) {
        this.jobSeeker = jobSeeker;
        this.coverLetter = coverLetter;
        this.resume = resume;
        this.questionAnswers = questionAnswers;
        this.status = status;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public String getResume() {
        return resume;
    }

    public ArrayList<String> getQuestionAnswers() {
        return questionAnswers;
    }

    public String getStatus() {
        return status;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setQuestionAnswers(ArrayList<String> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
