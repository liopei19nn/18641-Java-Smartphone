/**
 * 
 */
package hw1Parking;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class ParkedCar {
	/*
	 * instance variable of car's make, model, color, license number, and parked
	 * minutes
	 */
	private String make = null;
	private String model = null;
	private String color = null;
	private String licenseNumber = null;
	private int parkedMinutes = 0;

	/*
	 * ParkedCar() set the parked car's information if input is illegal, keep
	 * the default value
	 * 
	 * @param : make,model,color,licenseNumber,parkedMinutes
	 * 
	 */
	public ParkedCar(String make, String model, String color,
			String licenseNumber, int parkedMinutes) {
			this.make = make;
			this.model = model;
			this.color = color;
			this.licenseNumber = licenseNumber;
		if (parkedMinutes >= 0) {
			this.parkedMinutes = parkedMinutes;
		}
	}

	/*
	 * getMake() : get the car's make
	 * 
	 * @param make
	 */
	public String getMake() {
		return make;
	}

	/*
	 * setMake() : set the car's make
	 * 
	 * @param make
	 */
	public void setMake(String make) {
			this.make = make;
	}

	/*
	 * getModel() : get the car's model
	 * 
	 * @param model
	 */
	public String getModel() {
		return model;
	}

	/*
	 * setModel() : set the car's model
	 * 
	 * @param model
	 */
	public void setModel(String model) {
			this.model = model;
	}

	/*
	 * getColor() : get the car's color
	 * 
	 * @param color
	 */
	public String getColor() {
		return color;
	}

	/*
	 * setColor() : set the car's color
	 * 
	 * @param color
	 */
	public void setColor(String color) {
			this.color = color;
	}

	/*
	 * getLicenseNumber() : get the car's license number
	 * 
	 * @param licensenumber
	 */
	public String getLicenseNumber() {
		return licenseNumber;
	}

	/*
	 * setlicenseNumber() : set the car's license number
	 * 
	 * @param licensenumber
	 */
	public void setlicenseNumber(String licenseNumber) {
			this.licenseNumber = licenseNumber;
	}

	/*
	 * getParkedMinutes() : get the car's parking minutes
	 * 
	 * @param parkedMinutes
	 */
	public int getParkedMinutes() {
		return parkedMinutes;
	}

	/*
	 * setParkedMinutes() : set the car's parking minutes
	 * 
	 * @param parkedMinutes
	 */
	public void setParkedMinutes(int parkedMinutes) {
		if (parkedMinutes >= 0) {
			this.parkedMinutes = parkedMinutes;
		}
	}
}
