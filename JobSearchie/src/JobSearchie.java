import entities.JobSeeker;
import entities.User;

public class JobSearchie {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        User user = new JobSeeker();

        user.setFirstName("Levi");

        System.out.println(user.getFirstName());
    }
}
