package javaapplicationaccount;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Accounts {
    private static final int MAX_ACCOUNTS = 100;
    private static Accounts[] acs = new Accounts[MAX_ACCOUNTS];
    private static int accountCount = 0;

    private int aid;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    private static Scanner sc = new Scanner(System.in);

    public Accounts(int aid, String firstName, String lastName, String email, String username, String password) {
        this.aid = aid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public static void main(String[] args) {
        getAccounts();
    }

    public static void getAccounts() {
        int userCount = getUserCount();

        for (int i = 0; i < userCount; i++) {
            System.out.println("\nEnter details of User " + (i + 1));
            int ID = getIntInput("ID: ");
            String fName = getStringInput("First name: ");
            String lName = getStringInput("Last name: ");
            String email = getStringInput("Email: ");
            String username = getStringInput("Username: ");
            String password = getPasswordInput();

            if (duplicateVerify(ID, email, username)) {
                i--; 
                continue;
            }

            acs[accountCount++] = new Accounts(ID, fName, lName, email, username, password);
        }

        displayAccounts();
    }

    private static int getUserCount() {
        while (true) {
            System.out.print("Add number of users: ");
            try {
                int userCount = sc.nextInt();
                sc.nextLine();
                if (userCount <= 0) {
                    throw new InputMismatchException();
                }
                return userCount;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid positive number.");
                sc.next(); 
            }
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = sc.nextInt();
                sc.nextLine(); 
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next();
            }
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    private static String getPasswordInput() {
        while (true) {
            System.out.println("\nPassword criteria:"
                    + "\n- Must be more than 8 characters"
                    + "\n- Must not be a common password (e.g., 'admin', 'password', '1234')"
                    + "\n- Must contain at least one uppercase letter, one lowercase letter, one number, and one special character");
            String password = getStringInput("Password: ");
            if (passwordVerify(password)) {
                return password;
            }
            System.out.println("Please enter a valid password.");
        }
    }

    private static boolean passwordVerify(String password) {
        if (password.length() <= 8) {
            System.out.println("Password is invalid, must be more than 8 characters.");
            return false;
        }

        if (password.equals("admin") || password.equals("password") || password.equals("1234")) {
            System.out.println("Password is invalid, must not be a common password.");
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }

        if (!hasUppercase) {
            System.out.println("Password is invalid, must contain at least one uppercase letter.");
            return false;
        }
        if (!hasLowercase) {
            System.out.println("Password is invalid, must contain at least one lowercase letter.");
            return false;
        }
        if (!hasDigit) {
            System.out.println("Password is invalid, must contain at least one number.");
            return false;
        }
        if (!hasSpecialChar) {
            System.out.println("Password is invalid, must contain at least one special character.");
            return false;
        }

        return true;
    }

    private static boolean duplicateVerify(int id, String email, String username) {
        for (int j = 0; j < accountCount; j++) {
            if (id == acs[j].aid) {
                System.out.println("Input invalid, ID already exists.");
                return true;
            } else if (email.equals(acs[j].email)) {
                System.out.println("Input invalid, Email already exists.");
                return true;
            } else if (username.equals(acs[j].username)) {
                System.out.println("Input invalid, Username already exists.");
                return true;
            }
        }
        return false;
    }

    private static void displayAccounts() {
        System.out.printf("\n\n%-5s %-10s %-10s %-20s %-10s\n", "ID", "First Name", "Last Name", "Email", "Username");
        for (int i = 0; i < accountCount; i++) {
            acs[i].viewAccounts();
        }
    }

    public void viewAccounts() {
        System.out.printf("%-5d %-10s %-10s %-20s %-10s\n", aid, firstName, lastName, email, username);
    }
}

