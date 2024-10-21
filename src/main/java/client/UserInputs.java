package client;

import communication.Request;

import java.util.Scanner;

public class UserInputs {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private final UserInputsValidator validator;
    private Request request;
    private Scanner scanner;

    public UserInputs() {
        validator = new UserInputsValidator();
        request = new Request();
        scanner = new Scanner(System.in);
    }

    public String registrationInfo() {
        String firstName = getUserFirstName();
        String lastName = getUserLastName();
        String email = getUserEmail();
        String password = setUserPassword().trim();

        return request.register(firstName, lastName, email, password);
    }

    public String loginInfo() {
        String email = getUserEmail();
        String password = getUserPassword().trim();

        return request.login(email, password);
    }

    public String userCommands() {
        return "";
    }

    public String getUserFirstName() {

        while (true) {
            System.out.println("Enter your first name: ");
            this.firstName = scanner.nextLine();
            if (validator.isValidName(firstName)) {
                return firstName;
            } else {
                System.out.println("Invalid first name. Please enter a valid name");
            }
        }
    }

    public String getUserLastName() {

        while (true) {
            System.out.println("Enter your last name: ");
            this.lastName = scanner.nextLine();
            if (validator.isValidName(lastName)) {
                return lastName;
            } else {
                System.out.println("Invalid last name. Please enter a valid  last name");
            }
        }
    }

    public String getUserEmail() {

        while (true) {
            System.out.println("Enter your email: ");
            this.email = scanner.nextLine();

            if (validator.isValidEmail(email)) {
                return email;
            } else {
                System.out.println("Invalid email. Please enter a valid email");
            }
        }
    }

    public String setUserPassword() {
        String password1 = "";
        String password2 = "";

        while (true) {
            System.out.println("Enter passsword: ");
            password1 = scanner.nextLine();

            System.out.println("Confirm passsword: ");
            password2 = scanner.nextLine();

            if (!password1.equals(password2)) {
                System.out.println("Password mismatch, try again!");
                continue;
            }

            if (validator.isValidPassword(password1)) {
                this.password = password1;
                return this.password;
            } else {
                System.out.println("Invalid password!");
            }
        }
    }

    public String getUserPassword() {
        String password = "";

        while (true) {
            System.out.println("Enter passsword: ");
            password = scanner.nextLine();

            if (validator.isValidPassword(password)) {
                this.password = password;
                return this.password;
            } else {
                System.out.println("Invalid password!");
            }
        }
    }

    public String getTransactionType() {
        String transactionType = null;
        
        while (true) {
            System.out.println("Enter transaction type: ");
            transactionType = scanner.nextLine();

            if(validator.isValidTransType(transactionType)) {
                return transactionType;
            } else {
                System.out.println("Invalid transaction type.");
            }
        }
    }

    public float getTransactionAmount() {
        String  transactionAmount = null;
        while (true) {
            System.out.println("Enter transaction amount: ");
            transactionAmount = scanner.nextLine();

            if(validator.isNumber(transactionAmount)) {
                return Float.parseFloat(transactionAmount);
            } else {
                System.out.println("Invalid transaction amount.");
            }
        }
    }

    public String transactionInfo() {
        String transactionType = getTransactionType();
        Float transactionAmount = getTransactionAmount();

        return request.transaction(transactionType, transactionAmount, this.email);
    }

    public String handleUserInputs(String userInput) {
        if (userInput.equals("register")) {
            return registrationInfo();
        } else if (userInput.equals("login")) {
            return loginInfo();
        } else {
            return transactionInfo();
        }
    }
}
