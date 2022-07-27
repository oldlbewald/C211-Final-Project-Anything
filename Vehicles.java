package RentalCompany;

import java.util.*;
import java.io.*;

public class Vehicles {

    String type;
    String make;
    String model;
    int year;
    String color;
    private boolean isRented;

    //Default Constructor
    public Vehicles() {
        type = "";
        make = "";
        model = "";
        year = 0;
        color = "";

    }

    //Constructor
    public Vehicles(String type, String make, String model, int year, String color) {
        this.type = type;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.isRented = isRented;
    }

    public static void outputArray(Vehicles[] cars) {
        for (Vehicles car : cars) {
            car.output();
        }
    }


    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getName() {
        return make + " " + model;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }



    //Output Method
    public void output() {
        System.out.println("" + "/" + type + "/" + make + "/" + model + "/" + year + "/" + color);
    }
	
	public static void writeFile(String filename, ArrayList<Vehicles> newVehicles) {
        try (FileWriter output = new FileWriter(filename);
			BufferedWriter b = new BufferedWriter(output);
			PrintWriter p = new PrintWriter(b);) {
				
			for (int i = 0; i < newVehicles.size(); i++) {
				Vehicles newVehicle = newVehicles.get(i);
				p.println(newVehicle.type + "," + newVehicle.make + "," + newVehicle.model
							+ "," + newVehicle.year + "," + newVehicle.color);
			}				
			
        } catch (IOException e) {
        }
    }
	
	public static ArrayList<Vehicles> loadVehicles(String fileName) {
		
        ArrayList<Vehicles> availableCars = new ArrayList<>();
        
        try {
            try (Scanner x = new Scanner(new File(fileName))) {
                x.useDelimiter("[ \n]");
                while (x.hasNext()) {
                    String line = x.nextLine();
					
					//System.out.println(line);
					
					String[] details = line.split(",");

					String type = details[0], make = details[1], model = details[2], color = details[4];
					int year = Integer.parseInt(details[3]);
					
					Vehicles vehicle = new Vehicles(type, make, model, year, color);

                    availableCars.add(vehicle);
                }
            }
        } catch (FileNotFoundException e) {

        }
		
        return availableCars;
    }
}
