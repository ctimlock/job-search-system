package entities;

import java.util.Date;

public abstract class User  {
    private String firstName;
    private String lastName;
    private Location location;
    private String contactNumber;
    private String email;
    private String password;
    private Date date;
    private Boolean isLoggedIn;

    public User() {
        firstName = "";
        lastName = "";
        location = new Location();
        contactNumber = "";
        email = "";
        password = "";
        date = new Date();
        isLoggedIn = false;
    }

    public User(String firstName, String lastName, Location location, String contactNumber, String email, String password, Date date, Boolean isLoggedIn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.contactNumber = contactNumber;
        this.email = email;
        this.password = password;
        this.date = date;
        this.isLoggedIn = isLoggedIn;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
