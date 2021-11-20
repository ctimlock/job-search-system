package utilities;

import Controllers.UserHandler;

public class Validate {
    private final int MAX_ATTEMPTS = 3;
    private int incorrectAttempts = 0;

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
        if (incorrectAttempts >= MAX_ATTEMPTS)
            UserHandler.exit();
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
    public boolean isValidEmail(String email) {
        return false;
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
