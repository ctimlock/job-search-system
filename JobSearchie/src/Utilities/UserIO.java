package Utilities;

import Entities.Job;
import Entities.JobSeeker;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class UserIO {
    private static final int CHAR_WIDTH = 100;

    public static <E> void displayArrayList(ArrayList<E> arrayList) {
        try {
            for (E e: arrayList){
                System.out.println(e);
            }
        }
        catch (NullPointerException n) {
            System.out.print("");
        }
    }

    public static void displayBody(String body) {
        StringBuilder sb = new StringBuilder();
        AtomicInteger currentCount = new AtomicInteger();
        Arrays
            .stream(body.split(" "))
            .forEach((word) -> {
                if (word.length() + currentCount.get() <= CHAR_WIDTH) {
                    sb
                        .append(word)
                        .append(" ");
                    currentCount.addAndGet(word.length() + 1);
                } else {
                    sb
                        .append("\n")
                        .append(word)
                        .append(" ");
                    currentCount.set(word.length() + 1);
                }
            });
        System.out.println(sb);
    }
    public static void displayHeading(String heading) {
        displayMain(heading, "-");
    }

    public static void displayOptions(String[] options) {
        //Add in the CHAR_WIDTH functionality
        for (int i=1; i<=options.length; i++) {
            System.out.println(i + ". " + options[i-1]);
        }
    }

    public static void displayTitle(String title) {
        displayMain(title, "=");
    }

    private static void displayMain(String string, String separator) {
        StringBuilder sb = new StringBuilder();
        int spacesBefore = (CHAR_WIDTH - string.length()) / 2;
        int spacesAfter = (CHAR_WIDTH - string.length() - spacesBefore);
        sb
                .append(" ".repeat(spacesBefore))
                .append(string)
                .append(" ".repeat(spacesAfter))
                .append("\n")
                .append(separator.repeat(CHAR_WIDTH));
        System.out.println(sb);
    }

    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void displayLineBreak(String separator) {
        System.out.println(separator.repeat(CHAR_WIDTH));
    }

    public static void displayTitleAndBody(String title, String body) {
        displayTitle(title);
        displayBody(body);
        displayLineBreak("=");
    }

    //TODO: Make this work nicely :'(
    public static void clearScreen()
    {
        for (int i = 0; i < 5; i++)
        {
            System.out.println();
        }
    }

    public static void clearScreenAndAddTitle(String title)
    {
        UserIO.clearScreen();

        UserIO.displayTitle(title);
    }

    public static String enterAttribute(String attributeString, int minLength, int maxLength) {
        Validate valid = new Validate();
        String attributeValue;
        boolean flag;
        do {
            UserIO.displayBody("Please enter " + attributeString + ":");
            attributeValue = UserIO.getInput().trim();
            flag = valid.isValidLength(attributeValue, minLength, maxLength);
            if (!flag) {
                UserIO.displayBody("The " + attributeString + " must be between " + minLength + " and " + maxLength + " characters:");
            }
        }
        while (!flag);
        return attributeValue;
    }

    public static Date enterSQLDate()
    {
        Scanner scanner = new Scanner(System.in).useDelimiter("/");
        boolean success = false;
        Date date = Date.valueOf("1901-01-01");
        do
        {
            try
            {
                String input = scanner.nextLine();

                String[] chunk = input.split("[./-]");

                String dateString = chunk[2] + "-" + chunk[1] + "-" + chunk[0];

                date = Date.valueOf(dateString);
                if (date.after(new Date(System.currentTimeMillis())) || date.before(Date.valueOf("1901-01-01")))
                {
                    throw new Exception();
                }
                success = true;
            } catch (Exception e)
            {
                UserIO.displayBody("Invalid date entered. Please try again.");
            }

        } while (!success);
        return date;
    }





    private static int getNumericInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static int getNumericAttribute(String attributeName, int minLength, int maxLength) {
        Validate valid = new Validate();
        int numbericValue;
        boolean flag;
        do {
            UserIO.displayBody("Please enter " + attributeName + ":");
            numbericValue = UserIO.getNumericInput();
            flag = valid.isValidIntegerLength(numbericValue, minLength, maxLength);
            if (!flag) {
                UserIO.displayBody("The " + attributeName + " must be between " + minLength + " and " + maxLength + " characters:");
            }
        }
        while (!flag);
        return numbericValue;
    }


    ///////////

    public static String menuSelectorValue(String question, String[] options) {
        UserIO.displayBody(question);
        UserIO.displayOptions(options);

        boolean flag = false;
        int optionIndex = 0;
        do {
            try {
                String input = UserIO.getInput().trim();
                optionIndex = Integer.parseInt(input) - 1;
                if (optionIndex <= options.length-1 && optionIndex >= 0) {
                    flag = true;
                }
                else {
                    UserIO.displayBody("An invalid option has been selected");
                    UserIO.displayBody("Please select an option between 1 and " + options.length);
                }
            }
            catch (NumberFormatException e) {
                UserIO.displayBody("You have not entered a number.");
                UserIO.displayBody("Please select an option between 1 and " + options.length);
            }
            catch (Exception e) {
                UserIO.displayBody("An error has occurred.");
                UserIO.displayBody("Please select an option between 1 and " + options.length);
            }
        }
        while (!flag);

        return options[optionIndex];
    }

    public static String menuSelectorKey(String question, String[] options) {
        UserIO.displayBody(question);
        UserIO.displayOptions(options);

        boolean flag = false;
        int optionIndex = 0;
        do {
            try {
                String input = UserIO.getInput().trim();
                optionIndex = Integer.parseInt(input) - 1;
                if (optionIndex <= options.length-1 && optionIndex >= 0) {
                    flag = true;
                }
                else {
                    UserIO.displayBody("An invalid option has been selected");
                    UserIO.displayBody("Please select an option between 1 and " + options.length);
                }
            }
            catch (NumberFormatException e) {
                UserIO.displayBody("You have not entered a number.");
                UserIO.displayBody("Please select an option between 1 and " + options.length);
            }
            catch (Exception e) {
                UserIO.displayBody("An error has occurred.");
                UserIO.displayBody("Please select an option between 1 and " + options.length);
            }
        }
        while (!flag);

        return String.valueOf(optionIndex);
    }
    private static void printBlock(String text, String separator, int charWidth) {
        if (text == null) {
            text = "N/A";
        }
        int textLength = text.length();
        int sepLength = separator.length();
        if ((textLength + sepLength) > charWidth)
            text = text.substring(0, charWidth - separator.length() - 3) + "...";
        textLength = text.length();

        int remaining = charWidth - textLength - sepLength;
        text = " ".repeat(remaining/2) + text;
        if (remaining % 2 == 0)
            text += " ".repeat(remaining/2);
        else
            text += " ".repeat(remaining/2 + 1);
        text += separator;
        System.out.print(text);
    }

    public static void printJobHeading() {
        displayHeading("Search Results");
        printBlock("No.", " | ", 10);
        printBlock("Job Title", " | ", 20);
        printBlock("Company", " | ", 20);
        printBlock("State, City",  " | ", 20);
        printBlock("Compensation", " | ", 20);
        printBlock("Personal Relevancy", " | ", 20);
        System.out.println("\n" + "-".repeat(CHAR_WIDTH));
    }

    public static void printJobSummary(Job job, int relevancy, int number) {
        double amount = job.getCompensation();
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        String compensation = "$" + formatter.format(amount);

        printBlock(String.valueOf(number), " - ", 10);
        printBlock(job.getJobTitle(), " - ", 20);
        printBlock(job.getCompany(), " - ", 20);
        printBlock(job.getLocation().getState() + ", " + job.getLocation().getCity(),  " - ", 20);
        printBlock(compensation, " - ", 20);
        printBlock(String.valueOf(relevancy), " - ", 20);
        System.out.println("\n");
    }

    public static int getNumericAttribute(int minLength, int maxLength) {
        Validate valid = new Validate();
        int numberValue;
        boolean flag;
        do {
            numberValue = UserIO.getNumericInput();
            flag = valid.isValidIntegerLength(numberValue, minLength, maxLength);
            if (!flag) {
                UserIO.displayBody("The entered value must be between " + minLength + " and " + maxLength + " characters:");
            }
        }
        while (!flag);
        return numberValue;
    }

    public static void printCenter(String text) {
        int remaining = CHAR_WIDTH - text.length();
        text = " ".repeat(remaining/2) + text;
        if (remaining % 2 == 0)
            text += " ".repeat(remaining/2);
        else
            text += " ".repeat(remaining/2 + 1);
        System.out.println(text);
    }

    public static void printJobDetail(Job job, JobSeeker jobSeeker) {
        System.out.println(job.getId()); //TODO: Delete
        System.out.println("Title: " + job.getJobTitle());
        System.out.println("Company: " + job.getCompany());
        System.out.println("Date posted: " + job.getDateListed());
    }




    public static String getSelection(String strapLine, ArrayList<String[]> options) {
        int tries = 0;

        options.forEach(element -> displayBody(element[0]));
        System.out.println(strapLine);
        String selection = getInput();
        selection = selection.strip().toLowerCase();
        while (!isValidSelection(selection, options)) {
            if (tries <= 3) {
                displayBody("That was not a valid selection, please enter one of the below options.");
                //TODO: here
                System.out.println("Please try again: ");
                tries++;
                selection = getInput();
            } else {
                System.out.println("You have entered to many incorrect attempts.\nShutting program down...");
                System.exit(1);
            }
        }
        return selection;
    }

    private static boolean isValidSelection (String selection, ArrayList<String[]> options) {
        for (String[] word : options) {
            String[] values = word[1].split(",");
            for (String value : values) {
                if (selection.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
}
