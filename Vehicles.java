

public class Vehicles {
	String type;
	String make;
	String model;
	int year;
	String color;
	
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
		}
		
	public static void outputArray(Vehicles[] cars) {
		for (int i = 0; i < cars.length; i++) {
			cars[i].output();
		}
	}
	
	//Output Method
		public void output() {
			System.out.println("" + type + "/" + make + "/" + model + "/" + year + "/" + color);
		}
}
