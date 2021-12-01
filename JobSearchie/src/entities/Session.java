package entities;

import Controllers.AdminHandler;
import Controllers.JobSeekerHandler;
import Controllers.RecruiterHandler;
import Controllers.UserHandler;

import java.sql.Date;

public class Session {
    private int id;
    private User user;
    private Date loginTime;
    private Date logoutTime;

    public Session() {
        id = -1;
        user = null;
        loginTime = new Date(System.currentTimeMillis());
        logoutTime = null;
    }

    public Session(User userLoggedIn, Date loginTime, Date logoutTime) {
        id = -1;
        this.user = userLoggedIn;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session(int id, User userLoggedIn, Date loginTime, Date logoutTime) {
        this.id = id;
        this.user = userLoggedIn;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
    }

    public User getUserLoggedIn() {
        return user;
    }

    public void setUserLoggedIn(User userLoggedIn) {
        this.user = userLoggedIn;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public UserHandler getUserHandler() {
        UserHandler userHandler;
        switch (user.getClass().toString().split("\\.")[1]) {
            case ("Admin") -> userHandler = new AdminHandler();
            case ("Recruiter") -> userHandler = new RecruiterHandler();
            case ("JobSeeker") -> userHandler = new JobSeekerHandler();
            default -> throw new IllegalStateException("Unexpected value: " + user.getClass().toString().split("\\.")[1] + "Session class - getUserHandler.");
        }
        return userHandler;
    }
}
