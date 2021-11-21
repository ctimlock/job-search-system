package entities;

import java.util.ArrayList;

public class Recruiter extends User{
    private String companyName;
    private String recruitingSpecialty;
    private ArrayList<Job> jobListings;
    private ArrayList<Invitation> invitationsOffered;

    public Recruiter(){
        super();
        companyName = "";
        recruitingSpecialty = "";
        jobListings = new ArrayList<Job>();
        invitationsOffered = new ArrayList<Invitation>();
    }

    public Recruiter(String companyName, String recruitingSpecialty, ArrayList<Job> jobListings, ArrayList<Invitation> invitationsOffered) {
        super();
        this.companyName = companyName;
        this.recruitingSpecialty = recruitingSpecialty;
        this.jobListings = jobListings;
        this.invitationsOffered = invitationsOffered;
    }

    public void display(){

    }
    public String getCompanyName() {
        return companyName;
    }

    public ArrayList<Invitation> getInvitationsOffered() {
        return invitationsOffered;
    }

    public ArrayList<Job> getJobListings() {
        return jobListings;
    }

    public String getRecruitingSpecialty() {
        return recruitingSpecialty;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setInvitationsOffered(ArrayList<Invitation> invitationsOffered) {
        this.invitationsOffered = invitationsOffered;
    }

    public void setJobListings(ArrayList<Job> jobListings) {
        this.jobListings = jobListings;
    }

    public void setRecruitingSpecialty(String recruitingSpecialty) {
        this.recruitingSpecialty = recruitingSpecialty;
    }

}
