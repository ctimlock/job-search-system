package Controllers;

import Database.DatabaseManager;
import Entities.Job;
import Entities.JobSeeker;
import Utilities.UserIO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JobSearchHandler {
    private static final int JOBS_PER_PAGE = 10;
    /**
     * Main search method which is used by a job seeker.
     * @param db The database instance which is used throughout the program.
     * @param jobSeeker The job seeker who is currently logged in.
     */
    public static void search(JobSeeker jobSeeker, DatabaseManager db) {
        UserIO.displayTitleAndBody("Search", "Let us help you find your next dream job!");
        String searchTerm = getSearchTerm();
        ArrayList<Job> jobs;
        do {
            jobs = db.getAllJobs();
            jobs = defaultFilter(jobs, searchTerm);
            if (jobs.size() == 0) {
                String[] options = new String[] {"Yes", "No"};
                String selection = UserIO.menuSelectorKey("We couldn't find any jobs matching your search term, would you like to try again?", options);
                if (!selection.equals("0"))
                    searchTerm = getSearchTerm();
                else
                    return;
            } else {
                jobs = filterJobs(jobs, jobSeeker);
                if (jobs.size() == 0) {
                    String[] options = new String[]{"Yes", "No"};
                    String selection = UserIO.menuSelectorKey("Sorry there are no jobs that satisfy the filters applied, would you like to try again?", options);
                    if (!selection.equals("0")) {
                        searchTerm = getSearchTerm();
                    } else {
                        return;
                    }
                } else {
                    break;
                }
            }
        } while (true);
        jobs = sortJobs(jobs, jobSeeker, searchTerm);
        displayJobs(jobs, jobSeeker, db);
    }

    private static void displayJobs(ArrayList<Job> jobs, JobSeeker jobSeeker, DatabaseManager db) {
        int pageNo = 0;
        int totalPages = jobs.size() / JOBS_PER_PAGE;
        do {
            try {
                printJobs(jobs.subList(pageNo * JOBS_PER_PAGE, pageNo * JOBS_PER_PAGE + JOBS_PER_PAGE), jobSeeker, pageNo);
            } catch (Exception e) {
                printJobs(jobs.subList(pageNo * JOBS_PER_PAGE, jobs.size()), jobSeeker, pageNo);
            }
            UserIO.printCenter("Page " + (pageNo + 1) + " of " + (totalPages + 1));
            ArrayList<String[]> options = buildOptions(pageNo, totalPages);

            String stringSelection = UserIO.getSelection("Please select one of the above options", options);
            System.out.println();
            if (stringSelection.equals("previous"))
                pageNo--;
            else if (stringSelection.equals("next"))
                pageNo++;
            else if (stringSelection.equals("back"))
                JobSearchHandler.search(jobSeeker, db);
            else if (stringSelection.equals("home"))
                break;
            else
                displayJobDetail(jobs.get(Integer.parseInt(stringSelection)-1), jobSeeker);
        } while (true);
    }

    private static void displayJobDetail(Job job,JobSeeker jobSeeker) {
        UserIO.printJobDetail(job, jobSeeker);
        do {
            String[] options = new String[]{"Apply for job", "Report job", "Go Back"};
            String selection = UserIO.menuSelectorKey("Please select an option from above: ", options);
            if (selection.equals("0"))
                applyForJob(job, jobSeeker);
            else if (selection.equals("1"))
                UserIO.displayBody("Functionality coming soon!");
            else
                break;
        } while (true);
    }

    private static void applyForJob(Job job, JobSeeker jobSeeker) {
        //TODO: Create this
    }

    private static ArrayList<String[]> buildOptions(int pageNo, int totalPages) {
        ArrayList<String[]> options = new ArrayList<>();
        StringBuilder jobNos = new StringBuilder();
        for (int i = pageNo * JOBS_PER_PAGE + 1; i < pageNo * JOBS_PER_PAGE + 11; i++) {
            jobNos.append(i);
            jobNos.append(",");
        }
        options.add(new String[]{"A job number to view its details: {job number}", jobNos.substring(0, jobNos.length() - 1)});
        if (pageNo < totalPages)
            options.add(new String[]{"`next` to got to the next page:", "next"});
        if (pageNo > 0)
            options.add(new String[]{"`previous` to got to the previous page:", "previous"});
        options.add(new String[]{"`back` to return to the search menu:", "back"});
        options.add(new String[]{"`home` to return home:", "home"});
        return options;
    }

    private static void printJobs(List<Job> jobs, JobSeeker jobSeeker, int pageNo) {
        UserIO.printJobHeading();
        int counter = 1;
        for (Job job : jobs) {
            UserIO.printJobSummary(job, job.getPersonalRelevancy(jobSeeker), (pageNo * 10 + counter));
            counter++;
        }
    }

    private static ArrayList<Job> defaultFilter(ArrayList<Job> jobs, String searchTerm) {

        return reverseJobs(jobs.stream()
                .filter(job -> job.getCosine(searchTerm) >= 10) //TODO: Make dynamic
                .filter(Job::getIsAdvertised)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    private static ArrayList<Job> filterJobs(ArrayList<Job> jobs, JobSeeker jobSeeker) {
        String[] options = new String[]{"Yes", "No"};
        String selection = UserIO.menuSelectorKey("Would you like to filter the results?", options);
        int counter = 0;
        while (selection.equals("0") && counter < 4) {
            jobs = applyFilter(jobs, jobSeeker);
            selection = UserIO.menuSelectorKey("Would you like to apply another filter?", options);
            counter++;
        }
        if (counter != 0 )
            UserIO.displayBody("All filters have now been applied");
        return jobs;
    }

    private static ArrayList<Job> sortJobs(ArrayList<Job> jobs, JobSeeker jobSeeker, String searchTerm) {
        String[] options = new String[]{"Yes", "No"};
        String selection = UserIO.menuSelectorKey("Would you like to sort the result?", options);
        if (selection.equals("0"))
            return applySort(jobs, jobSeeker, searchTerm);
        else
            return defaultSort(jobs, searchTerm);
    }

    private static ArrayList<Job> applySort(ArrayList<Job> jobs, JobSeeker jobSeeker, String searchTerm) {
        String[] options = new String[]{
                "Sort by personal relevancy descending",
                "Sort by compensation ascending",
                "Sort by compensation descending"};
        String option = UserIO.menuSelectorKey("Please select an option to sort by: ", options);
        switch (option) {
            case "0":
                return sortByRelevancy(jobs, jobSeeker);
            case "1":
                return sortByCompensationDec(jobs);
            case "2":
                return sortByCompensationAsc(jobs);
            default:
                System.out.println("Error should not be able to reach here");
                return jobs;
        }
    }

    private static ArrayList<Job> sortByCompensationDec(ArrayList<Job> jobs) {
        return reverseJobs(jobs.stream().sorted(Comparator.comparing(Job::getCompensation)).collect(Collectors.toCollection(ArrayList::new)));
    }

    private static ArrayList<Job> sortByCompensationAsc(ArrayList<Job> jobs) {
        return jobs.stream().sorted(Comparator.comparing(Job::getCompensation)).collect(Collectors.toCollection(ArrayList::new));
    }

    private static ArrayList<Job> sortByRelevancy(ArrayList<Job> jobs, JobSeeker jobSeeker) {
        return reverseJobs(jobs.stream().sorted(Comparator.comparing(job -> job.getPersonalRelevancy(jobSeeker))).collect(Collectors.toCollection(ArrayList::new)));
    }

    private static ArrayList<Job> applyFilter(ArrayList<Job> jobs, JobSeeker jobSeeker) {
        String[] options = new String[]{"Filter by minimum personal relevancy", "Filter by maximum personal relevancy", "Filter by minimum compensation", "Filter by maximum compensation",};
        String option = UserIO.menuSelectorKey("Please select an option to filter by: ", options);
        switch (option) {
            case "0":
                return filterByMinimumPersonalRelevancy(jobs, jobSeeker);
            case "1":
                return filterByMaximumPersonalRelevancy(jobs, jobSeeker);
            case "2":
                return filterByMinimumCompensation(jobs);
            case "3":
                return filterByMaximumCompensation(jobs);
            default:
                System.out.println("Error in JobSearchHandler -> applyFilter.");
                return jobs;
        }
    }

    private static ArrayList<Job> filterByMinimumPersonalRelevancy(ArrayList<Job> jobs, JobSeeker jobSeeker) {
        UserIO.displayBody("Please enter the minimum personal relevancy you would like to filter by");
        int minimum = UserIO.getNumericAttribute(0, 100);
        return jobs.stream().filter(job -> job.getPersonalRelevancy(jobSeeker) >= minimum).collect(Collectors.toCollection(ArrayList::new));
    }

    private static ArrayList<Job> filterByMaximumPersonalRelevancy(ArrayList<Job> jobs, JobSeeker jobSeeker) {
        UserIO.displayBody("Please enter the maximum personal relevancy you would like to filter by");
        int maximum = UserIO.getNumericAttribute(0, 100);
        return jobs.stream().filter(job -> job.getPersonalRelevancy(jobSeeker) <= maximum).collect(Collectors.toCollection(ArrayList::new));
    }

    private static ArrayList<Job> filterByMaximumCompensation(ArrayList<Job> jobs) {
        UserIO.displayBody("Please enter the maximum compensation you would like to filter by");
        int maximum = UserIO.getNumericAttribute(0, 1000000);
        return jobs.stream().filter(job -> job.getCompensation() <= maximum).collect(Collectors.toCollection(ArrayList::new));
    }

    private static ArrayList<Job> filterByMinimumCompensation(ArrayList<Job> jobs) {
        UserIO.displayBody("Please enter the minimum compensation you would like to filter by");
        int minimum = UserIO.getNumericAttribute(0, 1000000);
        return jobs.stream().filter(job -> job.getCompensation() >= minimum).collect(Collectors.toCollection(ArrayList::new));
    }

    private static String getSearchTerm() {
        UserIO.displayBody("Please enter a term you would like to search for:");
        return UserIO.getInput();
    }

    private static ArrayList<Job> defaultSort(ArrayList<Job> jobs, String searchTerm) {
        return reverseJobs(jobs.stream().sorted(Comparator.comparing(job -> job.getCosine(searchTerm))).collect(Collectors.toCollection(ArrayList::new)));
    }

    private static ArrayList<Job> reverseJobs(ArrayList<Job> jobs) {
        if(jobs.size() > 1) {
            Job value = jobs.remove(0);
            reverseJobs(jobs);
            jobs.add(value);
        }
        return jobs;
    }

}
