/**
 * 
 */
package test;

import exception.AutoException;
import model.Automobile;
import util.FileIO;

/**
 * @author Li Pei
 * @andrew_id lip
 */
public class TestAutomotiveOption {
	public static void main(String[] args) throws AutoException {
		Automobile FordZTW = new FileIO().readInAutomotive("Focus.txt");
		System.out.println("Input a new car");
		System.out.println("*******************************");
		FordZTW.print();
		System.out.println("*******************************");

		// Test option get methods
		System.out.println("*******************************");
		System.out.println("Test getOptionPrice()");
		System.out.println("Option Price of ABS is "
				+ FordZTW.getOptionPrice("Brakes/Traction Control", "ABS"));

		System.out.println("*******************************");

		// Test option set methods
		System.out.println("*******************************");
		System.out.println("Test setOption()");
		FordZTW.setOption("Transmission", "Test Set", 9999);
		FordZTW.print();
		System.out.println("*******************************");

		// Test option delete methods
		System.out.println("*******************************");
		System.out.println("Test deleteOption()");
		FordZTW.deleteOption("Transmission", "Test Set");
		FordZTW.print();
		System.out.println("*******************************");

		// Test option update methods
		System.out.println("*******************************");

		FordZTW.updateOptionPrice("Side Impact Airbags", "None",
				(float) 9999.99);
		FordZTW.printAllOptionSet();
		System.out.println("*******************************");

	}
}
