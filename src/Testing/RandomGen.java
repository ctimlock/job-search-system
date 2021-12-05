package Testing;

import Entities.*;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomGen {

    public static String getRandomTypeOfInterview() {
        ArrayList<String> type = new ArrayList<>();
        type.add("Face to Face");
        type.add("Panel");
        type.add("Group");
        type.add("Online");
        return type.get(new Random().nextInt(type.size()));
    }

    public static Invitation getRandomInvitation(Recruiter recruiter, Job job, JobSeeker jobSeeker) {
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

    public static String getRandomApplicationStatus() {
        ArrayList<String> status = new ArrayList<>();
        status.add("Pending");
        status.add("Job Offered");
        status.add("Rejected");
        status.add("Interview Offered");
        return status.get(new Random().nextInt(status.size()));
    }

    public static Application getRandomApplication(Job job, JobSeeker jobSeeker) {
        Application application = new Application();
        application.setJob(job);
        application.setJobSeeker(jobSeeker);
        application.setApplicationDate(getRandomDate());
        application.setStatus(getRandomApplicationStatus());
        application.setCoverLetterDir(getRandomDirectory());
        application.setResumeDir(getRandomDirectory());
        return application;
    }

    public static String getRandomKeyword() {
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
        keywords.add("Integration");
        keywords.add("Agile");
        keywords.add("Server");
        keywords.add("Api");
        keywords.add("Android");
        keywords.add("Embed");
        keywords.add("Angry");
        keywords.add("Efficient");
        keywords.add("Tested");
        keywords.add("Cohesive");
        return keywords.get(new Random().nextInt(keywords.size()));
    }

    public static String getRandomCategory() {
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
        categories.add("Defence");
        categories.add("Construction");
        categories.add("Art");
        categories.add("Mining");
        categories.add("Health");
        return categories.get(new Random().nextInt(categories.size()));
    }

    public static String getRandomFirstName() {
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
        firstName.add("Homer");
        firstName.add("Moe");
        firstName.add("Rosey");
        firstName.add("Elizabeth");
        firstName.add("Ann-Marie");
        return firstName.get(new Random().nextInt(firstName.size()));
    }

    public static String getRandomLastName() {
        ArrayList<String> lastName = new ArrayList<>();
        lastName.add("Boag");
        lastName.add("Foil");
        lastName.add("Hemmingway");
        lastName.add("Jackson");
        lastName.add("Yang");
        lastName.add("Harrington");
        lastName.add("Scotchnetter");
        lastName.add("Faircoth");
        lastName.add("Smith");
        lastName.add("Baker");
        lastName.add("Wilson");
        lastName.add("Pang");
        lastName.add("Yang");
        lastName.add("Putin");
        lastName.add("Jakes");
        lastName.add("McLaren");
        return lastName.get(new Random().nextInt(lastName.size()));
    }

    public static String getRandomCountry() {
        ArrayList<String> country = new ArrayList<>();
        country.add("Australia");
        return country.get(new Random().nextInt(country.size()));
    }

    public static String getRandomState() {
        ArrayList<String> country = new ArrayList<>();
        country.add("Tasmania");
        country.add("Victoria");
        country.add("New South Wales");
        country.add("Queensland");
        country.add("Northern Territory");
        country.add("Western Australia");
        country.add("Australian Capital Territory");
        country.add("South Australia");
        return country.get(new Random().nextInt(country.size()));
    }

    public static String getRandomCity() {
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
        return country.get(new Random().nextInt(country.size()));
    }

    public static String getRandomPostcode() {
        return String.valueOf(new Random().nextInt(8999) + 1000);
    }

    public static String getRandomAdminEmail() {
        StringBuilder sb = new StringBuilder();
        sb.append(getRandomFirstName());
        sb.append(getRandomLastName());

        if (new Random().nextInt(2) == 1) {
            sb.append(getRandomSeparator());
        }
        sb.append("@");
        sb.append(getRandomEmailProvider());
        sb.append(getRandomDomain());
        return sb.toString().toLowerCase();
    }

    public static String getRandomRecruiterEmail() {
        StringBuilder sb = new StringBuilder();
        sb.append(getRandomFirstName());

        if (new Random().nextInt(2) == 1) {
            sb.append(getRandomSeparator());
        }
        if (new Random().nextInt(2) == 1) {
            sb.append(getRandomLastName());
        }
        sb.append("@");
        sb.append(getRandomEmailProvider());
        sb.append(getRandomDomain());
        return sb.toString().toLowerCase();
    }

    public static String getRandomJobSeekerEmail() {
        StringBuilder sb = new StringBuilder();
        sb.append(getRandomLastName());

        if (new Random().nextInt(2) == 1) {
            sb.append(getRandomSeparator());
        }
        if (new Random().nextInt(2) == 1) {
            sb.append(getRandomFirstName());
        }
        sb.append("@");
        sb.append(getRandomEmailProvider());
        sb.append(getRandomDomain());
        return sb.toString().toLowerCase();
    }

    public static String getRandomSeparator() {
        ArrayList<String> separator = new ArrayList<>();
        separator.add("-");
        separator.add("_");
        separator.add(".");
        return separator.get(new Random().nextInt(separator.size()));
    }

    public static String getRandomEmailProvider() {
        ArrayList<String> provider = new ArrayList<>();
        provider.add("hotmail");
        provider.add("gmail");
        provider.add("outlook");
        provider.add("yahoo");
        provider.add("icloud");
        provider.add("monash");
        return provider.get(new Random().nextInt(provider.size()));
    }

    public static String getRandomDomain() {
        ArrayList<String> provider = new ArrayList<>();
        provider.add(".com");
        provider.add(".com.au");
        provider.add(".edu");
        provider.add(".edu.au");
        provider.add(".org");
        provider.add(".org.au");
        return provider.get(new Random().nextInt(provider.size()));
    }

    public static String getRandomNumber() {
        return String.valueOf(new Random().nextInt(1000));
    }

    public static String getRandomJobTitle() {
        ArrayList<String> title = new ArrayList<>();
        title.add("Software Developer");
        title.add("Engineer");
        title.add("Farmer");
        title.add("Economist");
        title.add("Finance Officer");
        title.add("Financial Advisor");
        return title.get(new Random().nextInt(title.size()));
    }

    public static Location getRandomLocation() {
        Location location = new Location();
        location.setCountry(getRandomCountry());
        location.setState(getRandomState());
        location.setCity(getRandomCity());
        location.setPostcode(getRandomPostcode());
        return location;
    }

    public static Date getNow() {
        return new Date(System.currentTimeMillis());
    }

    public static Date getRandomDate() {
        Random rand = new Random();
        long now = System.currentTimeMillis();
        long change = new Random().nextInt(54635458);
        if (rand.nextInt(2) == 1)
            return new Date(now + change);
        else
            return new Date(now - change);
    }

    public static String getRandomJobLevel() {
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

    public static String getRandomPhoneNumber() {
        return "04" + new Random().nextInt(99999999);
    }

    public static String getRandomDirectory() {
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

    public static String getRandomExtension() {
        ArrayList<String> extension = new ArrayList<>();
        extension.add(".csv");
        extension.add(".doc");
        extension.add(".xlsx");
        extension.add(".txt");
        extension.add(".pdf");
        extension.add(".db");
        extension.add(".java");
        extension.add(".class");
        extension.add(".c");
        return extension.get(new Random().nextInt(extension.size()));
    }

    public static int getRandomCompensation() {
        return new Random().nextInt(250000);
    }

    public static String getRandomPassword() {
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

    public static String getRandomCompany() {
        ArrayList<String> comapny = new ArrayList<>();
        comapny.add("Seek Pty Ltd");
        comapny.add("Deloitte Pty Ltd");
        comapny.add("KPMG");
        comapny.add("Ey");
        comapny.add("PwC");
        comapny.add("Google");
        comapny.add("Facebook");
        comapny.add("Netflix");
        comapny.add("Amazon");
        return comapny.get(new Random().nextInt(comapny.size()));
    }

    public static Admin getRandomAdmin() {
        Admin admin = new Admin();
        admin.setEmail(getRandomAdminEmail());
        admin.setFirstName(getRandomFirstName());
        admin.setDateCreated(getNow());
        admin.setPassword(getRandomPassword());
        admin.setAccountType("Admin");
        return admin;
    }

    public static Recruiter getRandomRecruiter() {
        Recruiter recruiter = new Recruiter();
        recruiter.setEmail(getRandomRecruiterEmail());
        recruiter.setDateOfBirth(getRandomDate());
        recruiter.setContactNumber(getRandomPhoneNumber());
        recruiter.setFirstName(getRandomFirstName());
        recruiter.setLastName(getRandomLastName());
        recruiter.setDateCreated(getNow());
        recruiter.setPassword(getRandomPassword());
        recruiter.setAccountType("Recruiter");
        recruiter.setRecruitingSpecialty(getRandomKeyword());
        recruiter.setCompanyName(getRandomCompany());
        return recruiter;
    }

    public static JobSeeker getRandomJobSeeker() {
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setLocation(getRandomLocation());
        jobSeeker.setCurrentJobLevel(getRandomJobLevel());
        jobSeeker.setEmail(getRandomJobSeekerEmail());
        jobSeeker.setDateOfBirth(getRandomDate());
        jobSeeker.setContactNumber(getRandomPhoneNumber());
        jobSeeker.setCurrentJobName(getRandomJobTitle());
        jobSeeker.setResumeContent(getRandomKeywords().stream().map(word -> word + " ").collect(Collectors.joining()));
        jobSeeker.setExpectedCompensation(getRandomCompensation());
        jobSeeker.setFirstName(getRandomFirstName());
        jobSeeker.setLastName(getRandomLastName());
        jobSeeker.setDateCreated(getNow());
        jobSeeker.setPassword(getRandomPassword());
        jobSeeker.setAccountType("Job Seeker");
        jobSeeker.setKeywords(getRandomKeywords());
        return jobSeeker;
    }

    public static String getRandomWorkType() {
        ArrayList<String> workType = new ArrayList<>();
        workType.add("Full Time");
        workType.add("Part Time");
        workType.add("Casual");
        workType.add("Permanent");
        workType.add("Contract");
        workType.add("Temporary");
        workType.add("Flexible");
        return workType.get(new Random().nextInt(workType.size()));
    }

    public static String getRandomWorkingArrangement() {
        ArrayList<String> workingArrangement = new ArrayList<>();
        workingArrangement.add("Office");
        workingArrangement.add("Work From Home");
        workingArrangement.add("Flexible");
        workingArrangement.add("Hybrid");
        return workingArrangement.get(new Random().nextInt(workingArrangement.size()));
    }

    public static ArrayList<String> getRandomKeywords() {
        ArrayList<String> keywords = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(5) + 5; i++) {
            keywords.add(getRandomKeyword());
        }
        return keywords;
    }

    public static ArrayList<String> getRandomCategories() {
        ArrayList<String> categories = new ArrayList<>();
        for (int i = 0; i < new Random().nextInt(5) + 5; i++) {
            categories.add(getRandomCategory());
        }
        return categories;
    }

    public static boolean getRandomBoolean() {
        return new Random().nextBoolean();
    }

    public static String getRandomDescription() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < new Random().nextInt(50) + 50; i++) {
            sb.append(getRandomKeyword()).append(" ");
        }
        return sb.toString();
    }

    public static Job getRandomJob(Recruiter recruiter) {
        Job job = new Job();
        job.setJobTitle(getRandomJobTitle());
        job.setLocation(getRandomLocation());
        job.setAuthor(recruiter);
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
