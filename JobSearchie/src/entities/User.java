package entities;

import java.sql.Date;

public class User {
    private String accountType;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date dateCreated;

    public User() {
        accountType = "User";
        firstName = "";
        lastName = "";
        email = "";
        password = "";
        dateCreated = null;
    }

    public User(String accountType) {
        this.accountType = accountType;
        firstName = "";
        lastName = "";
        email = "";
        password = "";
        dateCreated = null;
    }

    public User(String accountType, String email) {
        this.accountType = accountType;
        firstName = "";
        lastName = "";
        this.email = email;
        password = "";
        dateCreated = null;
    }

    public User(String accountType, String firstName, String lastName, String email, String password, Date dateCreated) {
        this.accountType = accountType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
    }

    public void display() {
        System.out.println("accountType: " + accountType);
        System.out.println("firstName: " + firstName);
        System.out.println("lastName: " + lastName);
        System.out.println("email: " + email);
        System.out.println("password: " + password);
        System.out.println("dateCreated: " + dateCreated);
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
