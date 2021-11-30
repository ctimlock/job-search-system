package entities;

import java.sql.Date;

public class Admin extends User {
    public Admin() {
        super("Admin");
    }

    public Admin(String firstName, String lastName, String email, String password, Date dateCreated) {
        super("Admin", firstName, lastName, email, password, dateCreated);
    }

    public static Admin generateAdminForTesting() {
        return new Admin("James", "Bond", "james@bond.com", "LiveAndLetDie", new Date(System.currentTimeMillis()));
    }
}
