

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    private static  Scanner scanner = new Scanner(System.in);
    public static int readInt (String question ,int min ,int max) {


        try {
            System.out.println(question);
            System.out.print("Number(" +min +"-"+ max +") :");
            int userInput = scanner.nextInt();
            scanner.nextLine();
            if (userInput >= min && userInput <= max){
                return userInput;
            }
            System.out.println("Number outside the valid range , please try again ...");
            return readInt(question,min,max);

        }catch (InputMismatchException exception){
            System.out.println("Invalid Data Type ");
            scanner.nextLine();
            return readInt(question,min,max);

        }catch (Exception exception){
            scanner.nextLine();
            System.out.println(exception.getClass());
            return readInt(question,min,max);

        }



    }

}
