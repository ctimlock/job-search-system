package utilities;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.ProcessBuilder;

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

    public static void displayOptions(HashMap<String, String> options) {
        //Add in the CHAR_WIDTH functionality
        options.forEach((key, value) -> {
            System.out.println("  " + key + ". " + value);
        });
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

    public static String menuSelector(String question, String[] array) {
        UserIO.displayBody(question);
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            map.put(String.valueOf(i+1), array[i]);
        }
        UserIO.displayOptions(map);
        return map.get(UserIO.getInput());
    }

    public static String menuSelectorSwitch(String question, String[] array) {
        UserIO.displayBody(question);
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            map.put(String.valueOf(i+1), array[i]);
        }
        UserIO.displayOptions(map);
        return UserIO.getInput();
    }
}
