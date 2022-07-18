
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

    Vehicles(String honda, String accord, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
       public String getMake() {
        return make;
    }


    public String getModel() {
        return model;
    }

    public String getName() {
        return  make +" "+model;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", isRented=" + isRented +
                '}';
    }
	
	//Output Method
		public void output() {
			System.out.println("" + carPrice + "/" + type + "/" + make + "/" + model + "/" + year + "/" + color);
		}
}
