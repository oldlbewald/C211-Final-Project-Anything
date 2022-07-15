import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalCompany {

	static Scanner scan = new Scanner(System.in);
	static boolean quit = false;

	// Interface function
	public static void printMainMenuOne() {
		System.out.println("Possible actions:");
		System.out.println("0 - Quit the program");
		System.out.println("1 - log in");
		System.out.println("2 - Create an account");

		// test case for account file reader
		System.out.println("3 - print accounts from test");
	}

	public static void performAction(newAcount NA, Account A, int choice) throws FileNotFoundException {
		String username;
		String password;

		// String used when creating a new account
		String newusername;
		String newpassword;

		switch (choice) {
		case 0:
			quit = true;
			break;
		case 1:
			System.out.println("Enter your username:");
			username = scan.nextLine();
			Account.checkUsername(username);
			System.out.println("Enter your password:");
			password = scan.nextLine();
			Account.checkPassword(password);
			break;
		case 2:
			System.out.println("Let's get you signed up");
			System.out.println("Enter the username you want to use for this account:");
			newusername = scan.nextLine();
			newAcount.checkUsername(newusername);

			System.out.println("Enter the password you want to use for this account:");
			newpassword = scan.nextLine();
			newAcount.checkPassword(newpassword);

			NA.writeFile("Accounts.txt", newusername, newpassword);
			break;
		case 3:
			System.out.println("Reading from the file: Accounts.txt");
			ArrayList<Account> AccountsFromFile = new ArrayList<Account>();
			AccountsFromFile = Account.readAccountsFromFile("Accounts.txt");
			System.out.println(AccountsFromFile);
			System.out.println("The first account in the list is  " + AccountsFromFile.get(0));

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
			quit = true;
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
		// Account [] account = {};

		newAcount newaccount = new newAcount();

		while (!quit) {
			printMainMenuOne();
			System.out.println("Enter your choice");
			choice = scan.nextInt();
			performAction(newaccount, accounts, choice);
		}
		System.out.println("Thanks for using Express Rental Service, have a great day!");
	}

}
