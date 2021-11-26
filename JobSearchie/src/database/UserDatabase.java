package database;

import entities.Admin;
import entities.JobSeeker;
import entities.Recruiter;
import entities.User;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class UserDatabase extends DatabaseIO {
    public UserDatabase() throws IOException {
        super(new File(new java.io.File(".").getCanonicalPath() + "/JobSearchie/database/user.csv"));
    }

    public boolean doesEmailExist(String email) {
        //TODO: complete this method
        return true;
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<HashMap<String, String>> resultSet = super.getLines();
        return resultSet
                .stream()
                .map(this::mapToUser)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Admin mapAdmin(HashMap<String, String> user) {
        //TODO: Complete this method.
        return new Admin();
    }

    private JobSeeker mapJobSeeker(HashMap<String, String> user) {
        //TODO: refactor this to map a general user
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setId(Integer.parseInt(user.get("id")));
        jobSeeker.setFirstName(user.get("firstName"));
        jobSeeker.setLastName(user.get("lastName"));
        jobSeeker.setEmail(user.get("email"));
        jobSeeker.setPassword(user.get("password"));
        jobSeeker.setContactNumber(user.get("contactNumber"));
        jobSeeker.setCurrentJobName(user.get("currentJobName"));
        jobSeeker.setCurrentJobLevel(user.get("currentJobLevel"));
        jobSeeker.setExpectedCompensation(Integer.parseInt(user.get("expectedCompensation")));
        try {
            jobSeeker.setDateCreated(super.getFormatter().parse(user.get("dateCreated")));
            jobSeeker.setDateOfBirth(super.getFormatter().parse(user.get("dateOfBirth")));
            jobSeeker.setLocation(new LocationDatabase().getLocation(Integer.parseInt(user.get("locationId"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobSeeker;
    }

    private Recruiter mapRecruiter(HashMap<String, String> user) {
        //TODO: Complete this method.
        return new Recruiter();
    }

    private User mapToUser(HashMap<String, String> user) {
        String accountType = user.get("accountType");
        return switch (accountType) {
            case "Job Seeker" -> mapJobSeeker(user);
            case "Recruiter" -> mapRecruiter(user);
            case "Admin" -> mapAdmin(user);
            default -> throw new IllegalAccessError("Error mapping user to object, incorrect account type of " + accountType + ". See UserDatabase class - mapToUser method.");
        };
    }
}
