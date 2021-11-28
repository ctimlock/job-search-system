package entities;

import java.util.Date;

public class Admin extends User {
    public Admin() {
        super();
    }

    public Admin(String firstName, String lastName, String email, String password, Date dateCreated) {
        super(firstName, lastName, email, password, dateCreated);
    }
}
