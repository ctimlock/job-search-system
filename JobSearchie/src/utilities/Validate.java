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

    private void resetAttempts() {
        incorrectAttempts = 0;
    }
    public boolean isValidEmail(String input, String email) {
        checkAttempts();
        email = /*"^(.+)@(.+)$";*/ "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPattern = Pattern.compile(email, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
        //return false;
    }
    public boolean isValidLength(String string, int min, int max) {
        return false;
    }
    public boolean isValidPassword(String password) {
        return false;
    }
    public boolean isValidPostCode(String postCode) {
        return false;
    }
}
