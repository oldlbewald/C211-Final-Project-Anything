
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Account {

    String username;
    String password;

    static Scanner scan = new Scanner(System.in);

    // Constructor
    public Account() {
        username = "";
        password = "";
    }

    // Constructor
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Method to check if the user-name entered is blank, null or has spaces
    public static void checkUsername(String username) {
        if (username != null && !username.isEmpty() && !username.isBlank()) {
        } else {
            System.out.println("The username you entered was empty or not valid. Type it again:");
            username = scan.nextLine();
            checkUsername(username);
        }
    }

    // Method to check if the password entered is blank, null or has spaces
    public static void checkPassword(String password) {
        if (password != null && !password.isEmpty() && !password.isBlank()) {
        } else {
            System.out.println("The password you entered was empty or not valid. Type it again:");
            password = scan.nextLine();
            checkPassword(password);
        }
    }

    public boolean verifyLogin(String username, String password, String fileName) {
        boolean found = false;
        String searchUsername = "";
        String searchPassword = "";

        try {
            try (Scanner x = new Scanner(new File(fileName))) {
                x.useDelimiter("[ \n]");
                while (x.hasNext() && !found) {
                    searchUsername = x.next();
                    searchPassword = x.next();

                    if (searchUsername.trim().equals(username.trim()) && searchPassword.trim().equals(password.trim())) {
                        return true;
                    }
                }
                // return false;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Username or password was inncorect.");
        }
        return found;
    }

    public static boolean verifyLoginName(String username, String fileName) {
        boolean found = false;
        String searchUsername = "";

        try {
            try (Scanner x = new Scanner(new File(fileName))) {
                x.useDelimiter("[ \n]");
                while (x.hasNext() && !found) {
                    searchUsername = x.next();

                    if (searchUsername.trim().equals(username.trim())) {
                        return true;
                    }
                }
                // return false;
            }
        } catch (FileNotFoundException e) {

        }
        return found;
    }
}
