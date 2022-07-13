import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class newAcount {
String newusername;
String newpassword;

static Scanner scan = new Scanner(System.in);

public newAcount() {
	newusername = "";
	newpassword = "";
}
public newAcount(String newusername, String newpassword) {
	this.newusername = newusername;
	this.newpassword = newpassword;
}

/*public void createAccount(String newusername, String newpassword)
{
	System.out.print("Welcome to Interprise! Lets get you signed up, what is your name? ");
	newusername = scan.nextLine();
	
	System.out.println("Enter a password you would like to use with this account: ");
	newpassword = scan.next();
}*/

public static void checkUsername(String newusername) {
	if (newusername != null && !newusername.isEmpty() && !newusername.isBlank()) {
		System.out.println("Your username for this account will be " + newusername);
	} else {
		System.out.println("The username you entered was empty or not valid. Type it again:");
		newusername = scan.nextLine();
		checkUsername(newusername);
	}
}

public static void checkPassword(String newpassword) {
	if (newpassword != null && !newpassword.isEmpty() && !newpassword.isBlank()) {
		System.out.println("Your password for this account will be " + newpassword);
		System.out.println("Thanks for creating an account with ");
	} else {
		System.out.println("The password you entered was empty or not valid. Type it again:");
		newpassword = scan.nextLine();
		checkPassword(newpassword);
	}
}


public void writeFile(String filename, String newusername, String newpassword)
{
	try (FileWriter output = new FileWriter("Accounts.txt", true); 
		BufferedWriter b = new BufferedWriter(output);
			PrintWriter p = new PrintWriter(b);)
	{
		
		p.println(newusername + " " + newpassword);
	}

	catch (IOException e) {
		e.printStackTrace();
	}
}
}
