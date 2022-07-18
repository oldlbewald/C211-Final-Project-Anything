

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
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

    public static void performAction(newAcount NA, Account A, int choice) throws FileNotFoundException {
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

                // newAcount.checkUsernameDupe("Accounts.txt", newusername);
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

    public static void mainMenu() {
        System.out.println("MAIN MENU");
        System.out.println(" Welcome to Java Car Rentals  :");

        System.out.println("Would you like to...");
        System.out.println("1) Rent");
        System.out.println("2) Return ");
        System.out.println("3) Exit ");
        int userSelection  = UI.readInt("Select an option ",1,3);

        switch (userSelection) {
            case 1 -> rentalMenu();
            case 2 -> returnMenu();
            case 3 -> quit = true;
                        
        }

    }
    
    public static void rentalMenu() {

            //display  Available Cars
            System.out.println(" Available Cars :");
            for (int i = 0; i < availableCars.size() ; i++) {
                System.out.println("(" + (i + 1) + ") " + availableCars.get(i).getName());
            }

        // read user Input
           int  userSelection = UI.readInt("Enter a number to select the car you'd like to rent",1,5);


            if (userSelection <= availableCars.size()) {
        //Inform the user of a successful rent
                System.out.println(" Thank you! You are now renting  " +availableCars.get(userSelection -1).getName());

                try (FileWriter output = new FileWriter("Accounts.txt", true);
				BufferedWriter b = new BufferedWriter(output);
				PrintWriter p = new PrintWriter(b);) {

			p.println(availableCars.get(userSelection -1).getName());
		} catch (IOException ex) {
                    Logger.getLogger(RentalCompany.class.getName()).log(Level.SEVERE, null, ex);
                }
		 
                //Updating the car Rental status
                rentedCars.add( availableCars.get(userSelection -1));
                availableCars.remove(userSelection-1);

            }
        else {
                System.out.println("Car selection invalid, please try again ");
            }
        mainMenu();
    }
    
    
    
    public static void returnMenu() {
        if (rentedCars.isEmpty()){
            System.out.println("\n\nSorry, there are no available cars to rent.returning to the main menu…\n\n");
            mainMenu();
            return;
        }
        //display  Rented Cars
        System.out.println(" Rented Cars :");
        for (int i = 0; i < rentedCars.size() ; i++) {

            System.out.println("(" + (i + 1) + ") "+ rentedCars.get(i).getName());
        }

        // read user Inout
       int  userSelection = UI.readInt("Enter a number to select the car you'd like to return",1,3);

        if (userSelection <= rentedCars.size()) {
            //Inform the user of a successful rent
            System.out.println(" Thank you, you have returned   " +rentedCars.get(userSelection -1).getName());
            availableCars.add( rentedCars.get(userSelection -1));
            rentedCars.remove(userSelection-1);

        }
        else {
            System.out.println("Car selection invalid, please try again ");


        }
        mainMenu();
        
    }



        
    
    public static void main(String[] args) throws FileNotFoundException {

        Vehicles v1 = new Vehicles("Car", "Ford", "Mustang", 2015, "Black");
        Vehicles v2 = new Vehicles("Truck", "Dodge", "Ram", 2017, "Blue");
        Vehicles v3 = new Vehicles("SUV", "Chrysler", "Town & Country", 2025, "White");
        Vehicles v4 = new Vehicles("Car", "Tesla", "S", 2022, "Red");
        Vehicles v5 = new Vehicles("SUV", "Jeep", "Grand Cherokee", 2021, "Grey");

        availableCars.add(v1);
        availableCars.add(v2);
        availableCars.add(v3);
        availableCars.add(v4);
        availableCars.add(v5);
       int choice;
	Scanner scan = new Scanner(System.in);
	Account accounts = new Account();
        newAcount newaccount = new newAcount();

		while (!quit) {
			printMainMenuOne();
			System.out.println("Enter your choice");
			choice = scan.nextInt();
                        performAction(newaccount, accounts, choice);
                        
                        while (!start) {
			mainMenu();
			System.out.println("Enter your choice");
			choice = scan.nextInt();
                        }
                        scan.close();
                        System.out.println("Thanks for using Express Rental Service, have a great day!");
		}

    }
}
