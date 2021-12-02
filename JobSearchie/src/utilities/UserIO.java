package utilities;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class UserIO {
    private static final int CHAR_WIDTH = 100;

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
        Boolean success = false;
        Date date = Date.valueOf("1901-01-01");
        do
        {
            try
            {
                String input = scanner.nextLine();

                String[] chunk = input.split("[/-]");

                String dateString = chunk[2] + "-" + chunk[1] + "-" + chunk[0];

                date = Date.valueOf(dateString);
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
        UserIO.displayOptions(options);
        UserIO.displayBody(question);

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
}
