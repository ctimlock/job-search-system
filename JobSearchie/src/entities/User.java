package entities;

import java.util.Date;

public class User {
    private int id;
    private String userType;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date dateCreated;

    public User() {
        id = -1;
        userType = "User";
        firstName = "";
        lastName = "";
        email = "";
        password = "";
        dateCreated = null;
    }

    public User(String userType) {
        id = -1;
        this.userType = userType;
        firstName = "";
        lastName = "";
        email = "";
        password = "";
        dateCreated = null;
    }

    public User(String userType, String firstName, String lastName, String email, String password, Date dateCreated) {
        this.id = -1;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
    }

    public User(int id, String userType, String firstName, String lastName, String email, String password, Date dateCreated) {
        this.id = id;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public int getId() {
        return id;
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

    public void setId(int id) {this.id = id;}

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
