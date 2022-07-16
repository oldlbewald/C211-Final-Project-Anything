import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class newAcount {
	String newusername;
	String newpassword;

	static Scanner scan = new Scanner(System.in);

	// Constructor
	public newAcount() {
		newusername = "";
		newpassword = "";
	}

	// Constructor
	public newAcount(String newusername, String newpassword) {
		this.newusername = newusername;
		this.newpassword = newpassword;
	}

	// Method to check if the new user-name entered is blank, null or has spaces
	public static void checkUsername(String newusername) {
		if (newusername != null && !newusername.isEmpty() && !newusername.isBlank() && !newusername.contains(" ")) {
			System.out.println("Your username for this account will be " + newusername);
		} else {
			System.out.println(
					"The username you entered was empty, had spaces or was not a valid username. Type it again:");
			newusername = scan.nextLine();
			checkUsername(newusername);
		}
	}

	// Method to check if the new password entered is blank, null or has spaces
	public static void checkPassword(String newpassword) {
		if (newpassword != null && !newpassword.isEmpty() && !newpassword.isBlank() && !newpassword.contains(" ")) {
			System.out.println("Your password for this account will be " + newpassword);
			System.out.println("Thanks for creating an account with Express Rental Service!");
		} else {
			System.out.println(
					"The password you entered was empty, had spaces or was not a valid password. Type it again:");
			newpassword = scan.nextLine();
			checkPassword(newpassword);
		}
	}

	// Method to create a new account for a user, storing their inputed user-name
	// and password into the Accounts.txt file on a new line
	public void writeFile(String filename, String newusername, String newpassword) {
		try (FileWriter output = new FileWriter("Accounts.txt", true);
				BufferedWriter b = new BufferedWriter(output);
				PrintWriter p = new PrintWriter(b);) {

			p.println(newusername + " " + newpassword);
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
