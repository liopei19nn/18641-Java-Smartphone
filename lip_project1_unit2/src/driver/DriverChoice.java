package driver;

import exception.AutoException;
import util.FileIO;
import model.Automobile;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class DriverChoice {
	public static void main(String[] args) throws AutoException {
		Automobile auto = new FileIO().readInAutomotive("Focus.txt");
		auto.setOptionChoice("Color", "Fort Knox Gold Clearcoat Metallic");
		auto.setOptionChoice("Transmission", "Standard");
		auto.setOptionChoice("Brakes/Traction Control", "ABS");
		auto.setOptionChoice("Side Impact Airbags", "Selected");
		auto.setOptionChoice("Power Moonroof", "Selected");
		System.out.println("Base price is : " + auto.getBasePrice());
		auto.printChoice();
		auto.printTotalPrice();
	}
}
