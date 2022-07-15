import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Account {
	String username;
	String password;
	// public Object AccountsFromFile;

	static Scanner scan = new Scanner(System.in);

	public Account() {
		username = "";
		password = "";
	}

	public Account(String newusername, String newpassword) {
		this.username = newusername;
		this.password = newpassword;
	}
	
	public static void checkUsername(String username) {
		if (username != null && !username.isEmpty() && !username.isBlank()) {
			System.out.println("Username you entered was " + username);
		} else {
			System.out.println("The username you entered was empty or not valid. Type it again:");
			username = scan.nextLine();
			checkUsername(username);
		}
	}

	public static void checkPassword(String password) {
		if (password != null && !password.isEmpty() && !password.isBlank()) {
			System.out.println("The password you entered was " + password);
		} else {
			System.out.println("The password you entered was empty or not valid. Type it again:");
			password = scan.nextLine();
			checkPassword(password);
		}
	}
	
	public static ArrayList<Account> readAccountsFromFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Scanner s = new Scanner(file);

		ArrayList<Account> accountlist = new ArrayList<Account>();

		// while loop to scan each line
		while (s.hasNextLine()) {
			String line = s.nextLine();

			// Finds the separation with the space between username and password
			String[] items = line.split(" ");

			// Test to see if its reading the file
			System.out.println(items[0]);
			System.out.println(items[1]);

			// Create variables for both items found from file scanner
			String username = items[0];
			String password = items[1];

			// create new object with variables
			Account currentAccounts = new Account(username, password);
			accountlist.add(currentAccounts);
		}

		s.close();
		return accountlist;
	}
}
