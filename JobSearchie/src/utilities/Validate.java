package utilities;

import Controllers.UserHandler;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    private final int MAX_ATTEMPTS = 3;
    private int incorrectAttempts = 0;

    public boolean isValidOption(String input, String[] options) {
        checkAttempts();
        return Arrays.asList(options).contains(input);
    }
    public boolean isInRange(int value, int min, int max, boolean inclusive) {
        checkAttempts();
        if ((inclusive && isInRangeInclusive(value, min, max)) || (!inclusive && isInRangeExclusive(value, min, max))) {
            incorrectAttempts = 0;
            return true;
        } else {
            incorrectAttempts ++;
            return false;
        }
    }

    private void checkAttempts() {
        //if (incorrectAttempts >= MAX_ATTEMPTS)
            //UserHandler.exit();
    }

    private boolean isInRangeInclusive(int value, int min, int max) {
        return value >= min && value <= max;
    }

    private boolean isInRangeExclusive(int value, int min, int max) {
        return value > min && value < max;
    }

    public void resetAttempts() {
        incorrectAttempts = 0;
    }

    /**
     * This method will validate an email address enter into JobSearchie.
     * @param input Accepts the email entered by the user as a string.
     * @return Whether the inputted email matches the regex criteria, as a boolean.
     */
    public boolean isValidEmail(String input) {
        checkAttempts();
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(input);
        return matcher.find();
        //return false;
    }
    public boolean isValidLength(String string, int min, int max) {
        boolean valid = false;
        int length = string.length();
        if (length >= min && length <= max) {
            valid = true;
        }
        return valid;
    }

    public boolean isValidMenuOption(String[] array, int input) {
        boolean valid = false;
        int arrayLength = array.length;
        if (input <= arrayLength && input > 0) {
            valid = true;
        }
        return valid;
    }

    /**
     * This method will validate a password enter into JobSearchie.
     * @param password Accepts the password entered by the user as a string.
     * @return Whether the inputted password matches the regex criteria, as a boolean.
     */
    public boolean isValidPassword(String password) {
        checkAttempts();
        Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.find();
    }
    public boolean isValidPostCode(String postCode) {
        return false;
    }
}
