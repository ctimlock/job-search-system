package utilities;

import java.io.IOException;
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


    public static void displayHeadingAndBody(String heading, String body) {}

    public static void clearScreen()
    {
        String operatingSystem = System.getProperty("os.name");

        try
        {
            if (operatingSystem.contains("Windows"))
            {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else
            {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        } catch (IOException | InterruptedException ignored)
        {
        }
    }

    public static String enterAttribute(String attributeName, int minLength, int maxLength) {
        Validate valid = new Validate();
        String attributeValue;
        boolean flag;
        do {
            UserIO.displayBody("Please enter the " + attributeName + ":");
            attributeValue = UserIO.getInput().trim();
            flag = valid.isValidLength(attributeValue, minLength, maxLength);
            if (!flag) {
                UserIO.displayBody("The " + attributeName + " must be between " + minLength + " and " + maxLength + " characters:");
            }
        }
        while (!flag);
        return attributeValue;
    }

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
                    UserIO.displayBody("Please enter an integer between 1 and " + options.length);
                }
            }
            catch (NumberFormatException e) {
                UserIO.displayBody("Integer parsing error:");
                UserIO.displayBody("Please enter an integer between 1 and " + options.length);
            }
            catch (Exception e) {
                UserIO.displayBody("Other error");
                UserIO.displayBody("Please enter an integer between 1 and " + options.length);
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
                    UserIO.displayBody("Please enter an integer between 1 and " + options.length);
                }
            }
            catch (NumberFormatException e) {
                UserIO.displayBody("Integer parsing error:");
                UserIO.displayBody("Please enter an integer between 1 and " + options.length);
            }
            catch (Exception e) {
                UserIO.displayBody("Other error");
                UserIO.displayBody("Please enter an integer between 1 and " + options.length);
            }
        }
        while (!flag);

        return String.valueOf(optionIndex);
    }
}
