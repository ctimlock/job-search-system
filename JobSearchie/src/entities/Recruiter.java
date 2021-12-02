package entities;

import java.sql.Date;

public class Recruiter extends User {
    private String companyName;
    private String recruitingSpecialty;
    private String contactNumber;
    private Date dateOfBirth;

    public Recruiter() {
        super("Recruiter");
        companyName = "";
        recruitingSpecialty = "";
        contactNumber = "";
        dateOfBirth = null;
    }

    public Recruiter(String email) {
        super("Recruiter", email);
        companyName = "";
        recruitingSpecialty = "";
        contactNumber = "";
        dateOfBirth = null;
    }

    public Recruiter(String firstName, String lastName, String email, String password, Date dateCreated) {
        super("Recruiter", firstName, lastName, email, password, dateCreated);
        companyName = "";
        recruitingSpecialty = "";
        contactNumber = "";
        dateOfBirth = null;
    }

    public Recruiter(String firstName, String lastName, String email, String password, Date dateCreated, String companyName, String recruitingSpecialty, String contactNumber, Date dateOfBirth) {
        super("Recruiter", firstName, lastName, email, password, dateCreated);
        this.companyName = companyName;
        this.recruitingSpecialty = recruitingSpecialty;
        this.contactNumber = contactNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public void display() {
        super.display();
        System.out.println("companyName: " + companyName);
        System.out.println("recruitingSpecialty: " + recruitingSpecialty);
        System.out.println("contactNumber: " + contactNumber);
        System.out.println("dateOfBirth: " + dateOfBirth);
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getRecruitingSpecialty() {
        return recruitingSpecialty;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setRecruitingSpecialty(String recruitingSpecialty) {
        this.recruitingSpecialty = recruitingSpecialty;
    }
}
