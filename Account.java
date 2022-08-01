//Info C211 Summer 2022
//Group project - Vehicle Rental Company
//Team:
//Darian Collier
//Tyler Delgadillo
//Lachlan Ewald
//
//Account.java by Darian Collier
//

package RentalCompany;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class Account {

    String username;
    String password;
    int rentedVehicleID;

    static Scanner scan = new Scanner(System.in);

    // Constructor
    public Account() {
        username = "";
        password = "";
        rentedVehicleID = -1;
    }

    // Constructor
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.rentedVehicleID = -1;
    }

    int getRentedVehicleID() {
        return this.rentedVehicleID;
    }

    void setRentedVehicleID(int rentedVehicleID) {
        this.rentedVehicleID = rentedVehicleID;
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


    // Method to verify if a new account user-name matches that of a present user
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

    boolean verifynewUsername(String newusername, String fileName) {
        boolean found = false;
        String searchUsername = "";
        String searchPassword = "";

        try {
            try (Scanner x = new Scanner(new File(fileName))) {
                x.useDelimiter("[ \n]");
                while (x.hasNext() && !found) {
                    searchUsername = x.next();
                    searchPassword = x.next();

                    if (searchUsername.trim().equals(newusername.trim())) {
                        return true;
                    }
                }
                // return false;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error...");
        }
        return found;
    }


    public static class newAccount {

        static Scanner scan = new Scanner(System.in);
        private String newusername;
        private String newpassword;

        public newAccount() {
            String newusername = "";
            String newpassword = "";
        }

        public newAccount(String newusername, String newpassword) {
            this.newusername = newusername;
            this.newpassword = newpassword;

        }


        // Method to create a new account for a user, storing their inputed user-name
        // and password into the Accounts.txt file on a new line
        public void writeFile(String filename, String newusername, String newpassword) {
            try (FileWriter output = new FileWriter("Accounts.txt", true);
                 BufferedWriter b = new BufferedWriter(output);
                 PrintWriter p = new PrintWriter(b);) {

                p.println(newusername + " " + newpassword);
            } catch (IOException e) {
            }
        }
    }
}
