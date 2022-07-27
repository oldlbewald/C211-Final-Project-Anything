package RentalCompany;
import RentalCompany.Account.newAccount;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RentalCompany {

    static Scanner scan = new Scanner(System.in);
    static boolean quit = false;
    static boolean start = true;

    static ArrayList<Vehicles> availableCars = new ArrayList<>();
    static ArrayList<Vehicles> rentedCars = new ArrayList<>();

    // Interface function for main menu
    public static void printMainMenuOne() {
        System.out.println("Possible actions:");
        System.out.println("0 - Quit the program");
        System.out.println("1 - log in");
        System.out.println("2 - Create an account");
    }

    public static void performAction(newAccount NA, Account A, int choice) throws FileNotFoundException, IOException {
        // Strings used for logging in
        String username;
        String password;

        // Strings used when creating a new account
        String newusername;
        String newpassword;

        switch (choice) {
            case 0 ->
                quit = true;
            case 1 -> {
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
                    mainMenu();

                } else {
                    System.out.println("Try Logging in again.");
                }
            }
            case 2 -> {
                System.out.println("Let's get you signed up");
                System.out.println("Enter the username you want to use for this account:");
                newusername = scan.nextLine();
                while (newusername == null || newusername.isEmpty() || newusername.isBlank() || newusername.contains(" ")) {
                    System.out.println("Username was either blank or had spaces. Enter it again:");
                    newusername = scan.nextLine();
                }
                System.out.println("Your username will be " + newusername);

               // newAccount.checkUsernameDupe("Accounts.txt", newusername);
                System.out.println("Enter the password you want to use for this account:");
                newpassword = scan.nextLine();
                while (newpassword == null || newpassword.isEmpty() || newpassword.isBlank() || newpassword.contains(" ")) {
                    System.out.println("Password was either blank or had spaces. Enter it again:");
                    newpassword = scan.nextLine();
                }
                System.out.println("Your password will be " + newpassword);

                NA.writeFile("Accounts.txt", newusername, newpassword);
                System.out.println("Your Account was successfully created. Try logging in now.");
            }

            default ->
                System.out.println("Unknown option, try again.");

        }
        // Quit application case
        // Login case
        // Create new account case
    }

    public static void rentalMenu() throws IOException {
        System.out.println("Please confirm your username:");
        String username;
        username = scan.nextLine();

        if (Account.verifyLoginName(username, "Rentals.txt") == true) {
            System.out.println("Only one vehicle per customer is allowed. Please return current vehicle to rent another one.");
            mainMenu();
        } else {

            //display  Available Cars
            System.out.println(" Available Cars :");

            for (int i = 0; i < availableCars.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + availableCars.get(i).getName());
            }

            // read user Input
            int userSelection = UI.readInt("Enter a number to select the car you'd like to rent");

            if (userSelection <= availableCars.size()) {
                //Inform the user of a successful rent
                System.out.println(" Thank you! You are now renting  " + availableCars.get(userSelection - 1).getName());

                try (FileWriter output = new FileWriter("Rentals.txt", true);
                        BufferedWriter b = new BufferedWriter(output);
                        PrintWriter p = new PrintWriter(b);) {

                    p.println(username + " is renting " + availableCars.get(userSelection - 1).getName());
                } catch (IOException ex) {
                    Logger.getLogger(RentalCompany.class.getName()).log(Level.SEVERE, null, ex);
                }

                //Updating the car Rental status
                rentedCars.add(availableCars.get(userSelection - 1));
                availableCars.remove(userSelection - 1);

            } else {
                System.out.println("Car selection invalid, please try again ");
            }
            mainMenu();
        }
    }

    public static void returnMenu() throws FileNotFoundException, IOException {

        if (rentedCars.isEmpty()) {
            System.out.println("\n\nSorry, there are no available cars to rent.returning to the main menu…\n\n");
            mainMenu();
            return;
        }
        //display  Rented Cars
        System.out.println(" Rented Cars :");
        for (int i = 0; i < rentedCars.size(); i++) {

            System.out.println("(" + (i + 1) + ") " + rentedCars.get(i).getName());
        }
        // read user Inout
        int userSelection = UI.readInt("Enter a number to select the car you'd like to return");

        try {

            File inFile = new File("Rentals.txt");

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            try (BufferedReader br = new BufferedReader(new FileReader("Rentals.txt"));
                    PrintWriter pw = new PrintWriter(new FileWriter(tempFile))) {

                String line = null;

                //Read from the original file and write to the new
                //unless content matches data to be removed.
                while ((line = br.readLine()) != null) {

                    if (!line.trim().contains(rentedCars.get(userSelection - 1).getName())) {

                        pw.println(line);
                        pw.flush();
                    }
                }
            }

            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile)) {
                System.out.println("Could not rename file");
            }

        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

        if (userSelection <= rentedCars.size()) {
            //Inform the user of a successful rent
            System.out.println(" Thank you, you have returned   " + rentedCars.get(userSelection - 1).getName());

            availableCars.add(rentedCars.get(userSelection - 1));
            rentedCars.remove(userSelection - 1);

        } else {
            System.out.println("Car selection invalid, please try again ");

        }
        mainMenu();
    }

    public static void mainMenu() throws IOException {
        System.out.println("MAIN MENU");
        System.out.println(" Welcome to Java Car Rentals  :");

        System.out.println("Would you like to...");
        System.out.println("1) Rent");
        System.out.println("2) Return ");
        System.out.println("3) Exit ");
        int userSelection = UI.readInt("Select an option ");

        switch (userSelection) {
            case 1 ->
                rentalMenu();
            case 2 ->
                returnMenu();
            case 3 ->
                quit = true;

        }

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
		
	availableCars = Vehicles.loadVehicles("Vehicles.txt");

        int choice;
        Scanner scan = new Scanner(System.in);
        Account accounts = new Account();
        newAccount newaccount = new newAccount();

        while (!quit) {
            printMainMenuOne();
            System.out.println("Enter your choice");
            choice = scan.nextInt();
            performAction(newaccount, accounts, choice);

            while (!start) {
                mainMenu();
                System.out.println("Enter your choice");

            }
            //scan.close();
            System.out.println("Thanks for using Express Rental Service, have a great day!");
        }
		
		Vehicles.writeFile("Vehicles.txt", availableCars);

    }
}
