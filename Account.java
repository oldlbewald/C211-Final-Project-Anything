import java.io.File;
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
			System.out.println("Username you entered was " + username);
		} else {
			System.out.println("The username you entered was empty or not valid. Type it again:");
			username = scan.nextLine();
			checkUsername(username);
		}
	}

	// Method to check if the password entered is blank, null or has spaces
	public static void checkPassword(String password) {
		if (password != null && !password.isEmpty() && !password.isBlank()) {
			System.out.println("The password you entered was " + password);
		} else {
			System.out.println("The password you entered was empty or not valid. Type it again:");
			password = scan.nextLine();
			checkPassword(password);
		}
	}

	// Method to check if the user-name & password exist inside of the Accounts.txt
	// file
	public static void verifyLogin(String username, String password, String fileName) {
		boolean found = false;
		String searchUsername = "";
		String searchPassword = "";

		try {
			Scanner x = new Scanner(new File(fileName));
			x.useDelimiter("[ \n]");

			while (x.hasNext() && !found) {
				searchUsername = x.next();
				searchPassword = x.next();

				if (searchUsername.trim().equals(username.trim()) && searchPassword.trim().equals(password.trim())) {
					found = true;
				}
			}
			x.close();
			System.out.println(found);
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

}
