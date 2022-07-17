import java.io.FileNotFoundException;
import java.util.Scanner;

public class RentalCompany {

	static Scanner scan = new Scanner(System.in);
	static boolean quit = false;
	static boolean start = true;

	// Interface function for main menu
	public static void printMainMenuOne() {
		System.out.println("Possible actions:");
		System.out.println("0 - Quit the program");
		System.out.println("1 - log in");
		System.out.println("2 - Create an account");
	}

	public static void performAction(newAcount NA, Account A, int choice) throws FileNotFoundException {
		// Strings used for logging in
		String username;
		String password;

		// Strings used when creating a new account
		String newusername;
		String newpassword;

		switch (choice) {

		// Quit application case
		case 0:
			quit = true;
			break;

		// Login case
		case 1:
			System.out.println("Enter your username:");
			username = scan.nextLine();
			while (username == null || username.isEmpty() || username.isBlank() || username.contains(" ")) {
				System.out.println("Username was either blank or had spaces. Enter it again:");
				username = scan.nextLine();
			}

			System.out.println("Enter your password:");
			password = scan.nextLine();
			while (password == null || password.isEmpty() || password.isBlank() || password.contains(" ")) {
				System.out.println("Password was either blank or had spaces. Enter it again:");
				password = scan.nextLine();
			}

			// if login is successful display next actions to choose from
			if (A.verifyLogin(username, password, "Accounts.txt") != false) {
				System.out.println("You have successfully logged in");
				quit = true;
				start = false;

			} else {
				System.out.println("Try Logging in again.");
			}
			break;

		// Create new account case
		case 2:
			System.out.println("Let's get you signed up");
			System.out.println("Enter the username you want to use for this account:");
			newusername = scan.nextLine();
			while (newusername == null || newusername.isEmpty() || newusername.isBlank() || newusername.contains(" ")) {
				System.out.println("Username was either blank or had spaces. Enter it again:");
				newusername = scan.nextLine();
			}
			System.out.println("your username will be " + newusername);

			// newAcount.checkUsernameDupe("Accounts.txt", newusername);

			System.out.println("Enter the password you want to use for this account:");
			newpassword = scan.nextLine();
			while (newpassword == null || newpassword.isEmpty() || newpassword.isBlank() || newpassword.contains(" ")) {
				System.out.println("Password was either blank or had spaces. Enter it again:");
				newpassword = scan.nextLine();
			}
			System.out.println("your username will be " + newpassword);

			NA.writeFile("Accounts.txt", newusername, newpassword);

			break;

		default:
			System.out.println("Unknown option, try again.");

		}
	}

	// Interface function for when a user is logged in
	public static void printMenu() {
		System.out.println("Possible actions:");
		System.out.println("0 - Quit the program");
		System.out.println("1 - View our vehicle inventory");
		System.out.println("2 - Add a vehicle");
		System.out.println("3 - Return a vehicle");
		// System.out.println("4 - View your vehicle inventory");

	}

	public static void performActiontwo(Vehicles aList, int choice, Vehicles[] vehicles) {
		switch (choice) {
		case 0:
			start = false;
			break;
		case 1:
			System.out.println("Here is the selection of vehicles available:");
			Vehicles.outputArray(vehicles);
			break;
		case 2:
			System.out.println("Enter the vehicles value to add to your account:");

			break;
		case 3:
			System.out.println("Thank you for returning it!");
			break;

		default:
			System.out.println("Unknown option, try again.");

		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Vehicles myList = new Vehicles();
		Vehicles v1 = new Vehicles("Car", "Ford", "Mustang", 2015, "Black");
		Vehicles v2 = new Vehicles("Truck", "Dodge", "Ram", 2017, "Blue");
		Vehicles v3 = new Vehicles("SUV", "Chrysler", "Town & Country", 2025, "White");
		Vehicles v4 = new Vehicles("Car", "Tesla", "S", 2022, "Red");
		Vehicles v5 = new Vehicles("SUV", "Jeep", "Grand Cherokee", 2021, "Grey");

		Vehicles[] vehicles = { v1, v2, v3, v4, v5 };
		int choice;
		Scanner scan = new Scanner(System.in);

		Account accounts = new Account();

		newAcount newaccount = new newAcount();

		while (!quit) {
			printMainMenuOne();
			System.out.println("Enter your choice");
			choice = scan.nextInt();
			performAction(newaccount, accounts, choice);
		}

		while (!start) {
			printMenu();
			System.out.println("Enter your choice");
			choice = scan.nextInt();
			performActiontwo(myList, choice, vehicles);
		}
		scan.close();
		System.out.println("Thanks for using Express Rental Service, have a great day!");
	}

}
