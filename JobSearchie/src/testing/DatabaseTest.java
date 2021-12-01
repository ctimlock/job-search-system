package testing;

import database.DatabaseManager;
import entities.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class DatabaseTest {
    private void run() throws SQLException {
        System.out.println(getRandomPhoneNumber());
        System.exit(1);
        DatabaseManager db = new DatabaseManager();

        Recruiter recruiter = getRandomRecruiter();
        JobSeeker jobSeeker = getRandomJobSeeker();
        Job job = getRandomJob();
        db.insertJob(job);

        System.out.println(job.getId());


        Application app = getRandomApplication();

        Invitation invitation = new Invitation();
        invitation.setJob(job);
        invitation.setLocationOfInterview(getRandomLocation());
        invitation.setJobSeeker(jobSeeker);
        invitation.setRecruiter(recruiter);
        invitation.setAccepted(false);
        invitation.setAttachedMessage("Hey we would like to consider you for this job, let arrange a time to meet!.");
        invitation.setDateOfInterview(new Date(2022-1900+10+15));
        invitation.setDateSent(new Date(System.currentTimeMillis()));
        db.insertInvitation(invitation);

        System.out.println(invitation.getId());

        Invitation invi = db.getInvitation(1);

        System.out.println(invi.getJob().getJobTitle());


        db.close();
    }

    public static void main(String[] args) throws SQLException {
        new DatabaseTest().run();
    }

    private String getRandomTypeOfInterview() {
        ArrayList<String> type = new ArrayList<>();
        type.add("Face to Face");
        type.add("Panel");
        type.add("Group");
        type.add(null);
        type.add("Online");
        return type.get(new Random().nextInt(type.size()));
    }

    private Invitation getRandomInvitation(Recruiter recruiter, Job job, JobSeeker jobSeeker) {
        Invitation invitation = new Invitation();
        invitation.setJob(job);
        invitation.setRecruiter(recruiter);
        invitation.setJobSeeker(jobSeeker);
        invitation.setDateSent(getRandomDate());
        invitation.setAccepted(getRandomBoolean());
        invitation.setDateOfInterview(getRandomDate());
        invitation.setAttachedMessage(getRandomDescription());
        invitation.setDateSent(getRandomDate());
        invitation.setLocationOfInterview(getRandomLocation());
        invitation.setTypeOfInterview(getRandomTypeOfInterview());
        return invitation;
    }

    private Invitation getRandomInvitation() {
        Invitation invitation = new Invitation();
        //get Application set details such as job, recruiter job seeker
        invitation.setDateSent(getRandomDate());
        invitation.setAccepted(getRandomBoolean());
        invitation.setDateOfInterview(getRandomDate());
        invitation.setAttachedMessage(getRandomDescription());
        invitation.setDateSent(getRandomDate());
        invitation.setLocationOfInterview(getRandomLocation());
        invitation.setTypeOfInterview(getRandomTypeOfInterview());
        return invitation;
    }

    private String getRandomApplicationStatus() {
        ArrayList<String> status = new ArrayList<>();
        status.add("Pending");
        status.add("Job Offered");
        status.add("Rejected");
        status.add(null);
        status.add("Interview Offered");
        return status.get(new Random().nextInt(status.size()));
    }

    private Application getRandomApplication() {
        Application application = new Application();
        //Get random job which is advertised;
        //Get random job Seeker
        application.setApplicationDate(getRandomDate());
        application.setStatus(getRandomApplicationStatus());
        application.setCoverLetterDir(getRandomDirectory());
        application.setResumeDir(getRandomDirectory());
        return application;
    }

    private Application getRandomApplication(Job job, JobSeeker jobSeeker) {
        Application application = new Application();
        application.setJob(job);
        application.setJobSeeker(jobSeeker);
        application.setApplicationDate(getRandomDate());
        application.setStatus(getRandomApplicationStatus());
        application.setCoverLetterDir(getRandomDirectory());
        application.setResumeDir(getRandomDirectory());
        return application;
    }

    private String getRandomKeyword() {
        ArrayList<String> keywords = new ArrayList<>();
        keywords.add("Happy");
        keywords.add("Hard");
        keywords.add("Fast");
        keywords.add("Statistical");
        keywords.add("Fit");
        keywords.add("Funny");
        keywords.add("Tall");
        keywords.add("Short");
        keywords.add("Genius");
        keywords.add("Dumb");
        keywords.add("Slow");
        keywords.add("");
        keywords.add("-1");
        keywords.add(null);
        keywords.add(null);
        keywords.add("Test many words");
        return keywords.get(new Random().nextInt(keywords.size()));
    }

    private String getRandomCategory() {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Economics");
        categories.add("Finance");
        categories.add("Computer Science");
        categories.add("Maths");
        categories.add("Physics");
        categories.add("Science");
        categories.add("English");
        categories.add("French");
        categories.add("Sport");
        categories.add("Education");
        categories.add("Social Services");
        categories.add("");
        categories.add("-1");
        categories.add(null);
        categories.add(null);
        categories.add("Health");
        return categories.get(new Random().nextInt(categories.size()));
    }

    private String getRandomFirstName() {
        ArrayList<String> firstName = new ArrayList<>();
        firstName.add("James");
        firstName.add("Henry");
        firstName.add("Taylor");
        firstName.add("Hannah");
        firstName.add("Sarah");
        firstName.add("Emily");
        firstName.add("Chris");
        firstName.add("Tim");
        firstName.add("Merrill");
        firstName.add("Levi");
        firstName.add("Charlie");
        firstName.add("");
        firstName.add("-1");
        firstName.add(null);
        firstName.add(null);
        firstName.add("Two words");
        return firstName.get(new Random().nextInt(firstName.size()));
    }

    private String getRandomLastName() {
        ArrayList<String> firstName = new ArrayList<>();
        firstName.add("Boag");
        firstName.add("Foil");
        firstName.add("Hemmingway");
        firstName.add("Jackson");
        firstName.add("Yang");
        firstName.add("Harrington");
        firstName.add("Scotchnetter");
        firstName.add("Faircoth");
        firstName.add("Smith");
        firstName.add("Baker");
        firstName.add("Wilson");
        firstName.add("");
        firstName.add("-1");
        firstName.add(null);
        firstName.add(null);
        firstName.add("Jim Taylor");
        return firstName.get(new Random().nextInt(firstName.size()));
    }

    private String getRandomCountry() {
        ArrayList<String> country = new ArrayList<>();
        country.add("Australia");
        country.add("China");
        country.add("Vietnam");
        country.add("United Kingdom");
        country.add("America");
        country.add("Mexico");
        country.add("Brazil");
        country.add("Wales");
        country.add("Romania");
        country.add("Germany");
        country.add("Netherlands");
        country.add("");
        country.add("-1");
        country.add(null);
        country.add(null);
        country.add("USA");
        return country.get(new Random().nextInt(country.size()));
    }

    private String getRandomState() {
        ArrayList<String> country = new ArrayList<>();
        country.add("Tasmania");
        country.add("Victoria");
        country.add("New South Wales");
        country.add("Queensland");
        country.add("Northern Territory");
        country.add("Western Australia");
        country.add("Australian Capital Territory");
        country.add("South Australia");
        country.add("Colorado");
        country.add("Jacksonville");
        country.add("Arizona");
        country.add("");
        country.add("-1");
        country.add(null);
        country.add(null);
        country.add("Utah");
        return country.get(new Random().nextInt(country.size()));
    }

    private String getRandomCity() {
        ArrayList<String> country = new ArrayList<>();
        country.add("Hobart");
        country.add("Burnie");
        country.add("Melbourne");
        country.add("Sydney");
        country.add("Ulverston");
        country.add("Launceston");
        country.add("Devenport");
        country.add("Smithton");
        country.add("Perth");
        country.add("Denver");
        country.add("Moab");
        country.add("");
        country.add("-1");
        country.add(null);
        country.add(null);
        country.add("New York");
        return country.get(new Random().nextInt(country.size()));
    }

    private String getRandomPostcode() {
        return String.valueOf(new Random().nextInt(8999) + 1000);
    }

    private String getRandomEmail() {
        StringBuilder sb = new StringBuilder();
        if (new Random().nextInt(2) == 1) {
            sb.append(getRandomFirstName());
        }
        if (new Random().nextInt(10) == 1) {
            sb.append(getRandomKeyword());
        }
        if (new Random().nextInt(2) == 1) {
            sb.append(getRandomSeparator());
        }
        if (new Random().nextInt(2) == 1) {
            sb.append(getRandomLastName());
        }
        sb.append("@");
        sb.append(getRandomEmailProvider());
        sb.append(getRandomDomain());
        return sb.toString();
    }

    private String getRandomSeparator() {
        ArrayList<String> separator = new ArrayList<>();
        separator.add("-");
        separator.add("_");
        separator.add(".");
        return separator.get(new Random().nextInt(separator.size()));
    }

    public String getRandomEmailProvider() {
        ArrayList<String> provider = new ArrayList<>();
        provider.add("hotmail");
        provider.add("gmail");
        provider.add("outlook");
        provider.add("yahoo");
        provider.add("icloud");
        provider.add("monash");
        return provider.get(new Random().nextInt(provider.size()));
    }

    public String getRandomDomain() {
        ArrayList<String> provider = new ArrayList<>();
        provider.add(".com");
        provider.add(".com.au");
        provider.add(".edu");
        provider.add(".edu.au");
        provider.add(".org");
        provider.add(".org.au");
        return provider.get(new Random().nextInt(provider.size()));
    }

    private String getRandomNumber() {
        return String.valueOf(new Random().nextInt(1000));
    }

    private String getRandomJobTitle() {
        ArrayList<String> title = new ArrayList<>();
        title.add("Software Developer");
        title.add("Engineer");
        title.add("Farmer");
        title.add("Economist");
        title.add("Finance Officer");
        title.add("Financial Advisor");
        return title.get(new Random().nextInt(title.size()));
    }

    private Location getRandomLocation() {
        Location location = new Location();
        location.setCountry(getRandomCountry());
        location.setState(getRandomState());
        location.setCity(getRandomCity());
        location.setPostcode(getRandomPostcode());
        return location;
    }

    private Date getNow() {
        return new Date(System.currentTimeMillis());
    }

    private Date getRandomDate() {
        Random rand = new Random();
        long now = System.currentTimeMillis();
        long change = (long) new Random().nextInt(54635458);
        if (rand.nextInt(2) == 1)
            return new Date(now + change);
        else
            return new Date(now - change);
    }

    private String getRandomJobLevel() {
        ArrayList<String> jobLevel = new ArrayList<>();
        jobLevel.add("Vacationer");
        jobLevel.add("Undergraduate");
        jobLevel.add("Graduate");
        jobLevel.add("Analyst");
        jobLevel.add("Senior Analyst");
        jobLevel.add("Manager");
        jobLevel.add("Associate Director");
        jobLevel.add("Director");
        jobLevel.add("Principal");
        jobLevel.add("Partner");
        return jobLevel.get(new Random().nextInt(jobLevel.size()));
    }

    private String getRandomPhoneNumber() {
        return "04" + new Random().nextInt(99999999);
    }

    private String getRandomDirectory() {
        StringBuilder sb = new StringBuilder();
        sb.append("C:");
        for (int i = 0; i < new Random().nextInt(10); i++) {
            sb.append(getRandomKeyword());
            sb.append("/");
        }
        sb.append(getRandomKeyword());
        sb.append(getRandomExtension());
        return sb.toString();
    }

    public String getRandomExtension() {
        ArrayList<String> extension = new ArrayList<>();
        extension.add(".csv");
        extension.add(".doc");
        extension.add(".xlsx");
        extension.add(".txt");
        extension.add(".pdf");
        extension.add(".db");
        extension.add(".java");
        extension.add(".class");
        extension.add(null);
        extension.add(".c");
        return extension.get(new Random().nextInt(extension.size()));
    }

    private int getRandomCompensation() {
        return new Random().nextInt(10000000);
    }

    private String getRandomPassword() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        sb.append(getRandomKeyword());
        for (int i = 0; i < rand.nextInt(5); i++) {
            if (rand.nextInt(3) == 1) {
                sb.append(getRandomKeyword());
            }
            if (rand.nextInt(5) == 1) {
                sb.append(getRandomSeparator());
            }
            if (rand.nextInt(2) == 1) {
                sb.append(rand.nextInt(300));
            }
        }
        return sb.toString();
    }

    private String getRandomCompany() {
        ArrayList<String> comapny = new ArrayList<>();
        comapny.add("Seek Pty Ltd");
        comapny.add("Deloitte Pty Ltd");
        comapny.add("KPMG");
        comapny.add("Ey");
        comapny.add("PwC");
        comapny.add("Google");
        comapny.add("Facebook");
        comapny.add("Netflix");
        comapny.add(null);
        comapny.add("Amazon");
        return comapny.get(new Random().nextInt(comapny.size()));
    }

    private Admin getRandomAdmin() {
        Admin admin = new Admin();
        admin.setEmail(getRandomEmail());
        admin.setFirstName(getRandomFirstName());
        admin.setDateCreated(getNow());
        admin.setPassword(getRandomPassword());
        admin.setAccountType("Recruiter");
        return admin;
    }

    private Recruiter getRandomRecruiter() {
        Recruiter recruiter = new Recruiter();
        recruiter.setEmail(getRandomEmail());
        recruiter.setDateOfBirth(getRandomDate());
        recruiter.setContactNumber(getRandomPhoneNumber());
        recruiter.setFirstName(getRandomFirstName());
        recruiter.setDateCreated(getNow());
        recruiter.setPassword(getRandomPassword());
        recruiter.setAccountType("Recruiter");
        recruiter.setRecruitingSpecialty(getRandomKeyword());
        recruiter.setCompanyName(getRandomCompany());
        return recruiter;
    }

    private JobSeeker getRandomJobSeeker() {
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setLocation(getRandomLocation());
        jobSeeker.setCurrentJobLevel(getRandomJobLevel());
        jobSeeker.setEmail(getRandomEmail());
        jobSeeker.setDateOfBirth(getRandomDate());
        jobSeeker.setContactNumber(getRandomPhoneNumber());
        jobSeeker.setCurrentJobName(getRandomJobTitle());
        jobSeeker.setResumeDir(getRandomDirectory());
        jobSeeker.setExpectedCompensation(getRandomCompensation());
        jobSeeker.setFirstName(getRandomFirstName());
        jobSeeker.setDateCreated(getNow());
        jobSeeker.setPassword(getRandomPassword());
        jobSeeker.setAccountType("Job Seeker");
        jobSeeker.setKeywords(getRandomKeywords());
        return jobSeeker;
    }

    private String getRandomWorkType() {
        ArrayList<String> workType = new ArrayList<>();
        workType.add("Full Time");
        workType.add("Part Time");
        workType.add("Casual");
        workType.add("Permanent");
        workType.add("Contract");
        workType.add("Temporary");
        workType.add("Flexible");
        workType.add(null);
        return workType.get(new Random().nextInt(workType.size()));
    }

    private String getRandomWorkingArrangement() {
        ArrayList<String> workingArrangement = new ArrayList<>();
        workingArrangement.add("Office");
        workingArrangement.add("Work From Home");
        workingArrangement.add("Flexible");
        workingArrangement.add("Hybrid");
        workingArrangement.add(null);
        return workingArrangement.get(new Random().nextInt(workingArrangement.size()));
    }

    private ArrayList<String> getRandomKeywords() {
        ArrayList<String> keywords = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(4); i++) {
            keywords.add(getRandomKeyword());
        }
        return keywords;
    }

    private ArrayList<String> getRandomCategories() {
        ArrayList<String> categories = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(4); i++) {
            categories.add(getRandomCategory());
        }
        return categories;
    }

    public boolean getRandomBoolean() {
        return new Random().nextBoolean();
    }

    private String getRandomDescription() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < new Random().nextInt(50) + 50; i++) {
            sb.append(getRandomKeyword());
        }
        return sb.toString();
    }

    public Job getRandomJob() {
        Job job = new Job();
        job.setLocation(getRandomLocation());
        job.setAuthor(getRandomRecruiter());
        job.setJobLevel(getRandomJobLevel());
        job.setCompany(getRandomCompany());
        job.setCompensation(getRandomCompensation());
        job.setDateCreated(getNow());
        job.setDateListed(getRandomDate());
        job.setDateDeListed(getRandomDate());
        job.setKeywords(getRandomKeywords());
        job.setCategories(getRandomCategories());
        job.setIsAdvertised(getRandomBoolean());
        job.setDescription(getRandomDescription());
        job.setWorkingArrangement(getRandomWorkingArrangement());
        job.setWorkType(getRandomWorkType());
        return job;
    }
}
