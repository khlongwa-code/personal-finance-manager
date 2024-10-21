package client;

import java.util.Arrays;
import java.util.List;

public class UserInputsValidator {
    /**
     * Validates if the name contains only alphabets.
     *
     * @param name The name to validate
     * @return true if the name is all alphabets, otherwise false
     */
    public boolean isValidName(String name) {
        return name.matches("^[a-zA-Z]+$");
    }

    /**
     * Validates if the email is in the correct format.
     *
     * @param email The email to validate
     * @return true if the email is in the correct format, otherwise false
     */
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    /**
     * Validates if the password is in the correct format
     * Passwrd should atleast be 8 characters long, have atleast one:
     * -lower case and upper case alphabet, digit, symbol
     * @param password The password to validate
     * @return true if the password is in the correct format otherwise false
     */
    public boolean isValidPassword (String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(passwordRegex);
    }

    /**
     * Validates if the user input is a number
     * @param input a string user input
     * @return true if the input is a number else false
     */
    public boolean isNumber(String input) {
        String inputRegex = "^(0|[1-9]\\d*)(\\.\\d{0,2})?$";
        return input.matches(inputRegex);
    }

    /**
     * Checks if the transaction type is valid
     * @param transactionType
     * @return true if the transaction type is valid else false
     */
    public boolean isValidTransType(String transactionType) {
        List<String> types = Arrays.asList("income", "expense", "transfer");
        return types.contains(transactionType);
    }
}
