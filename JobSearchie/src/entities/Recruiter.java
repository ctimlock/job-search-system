package entities;

import java.util.Date;

public class Recruiter extends User {
    private String companyName;
    private String recruitingSpecialty;
    private String contactNumber;
    private Date dateOfBirth;

    public Recruiter() {
        super();
        companyName = "";
        recruitingSpecialty = "";
    }

    public Recruiter(String firstName, String lastName, String email, String password, Date dateCreated) {
        super(firstName, lastName, email, password, dateCreated);
        this.companyName = "";
        this.recruitingSpecialty = "";
    }

    public Recruiter(int id, String firstName, String lastName, String email, String password, Date dateCreated) {
        super(id, firstName, lastName, email, password, dateCreated);
        this.companyName = "";
        this.recruitingSpecialty = "";
    }

    public Recruiter(String firstName, String lastName, String email, String password, Date dateCreated, String companyName, String recruitingSpecialty) {
        super(firstName, lastName, email, password, dateCreated);
        this.companyName = companyName;
        this.recruitingSpecialty = recruitingSpecialty;
    }

    public Recruiter(int id, String firstName, String lastName, String email, String password, Date dateCreated, String companyName, String recruitingSpecialty) {
        super(id, firstName, lastName, email, password, dateCreated);
        companyName = companyName;
        recruitingSpecialty = recruitingSpecialty;
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
