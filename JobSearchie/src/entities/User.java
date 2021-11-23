package entities;

import java.util.Date;

public abstract class User {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Location location;
    private String contactNumber;
    private Date dateOfBirth;

    public User() {
        id = -1;
        firstName = "";
        lastName = "";
        location = new Location();
        contactNumber = "";
        email = "";
        password = "";
        dateOfBirth = new Date();
    }

    public User(int id, String firstName, String lastName, Location location, String contactNumber, String email, String password, Date date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.contactNumber = contactNumber;
        this.email = email;
        this.password = password;
        this.dateOfBirth = date;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Date getDate() {
        return dateOfBirth;
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

    public Location getLocation() {
        return location;
    }


    public String getPassword() {
        return password;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setDate(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
