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
public class TestAutomotiveOptionSet {
	public static void main(String[] args) throws AutoException {

		Automobile FordZTW = new FileIO().readInAutomotive("focus.txt");
		System.out.println("Input a new car");
		System.out.println("*******************************");
		FordZTW.print();
		System.out.println("*******************************");

		// Test option set get methods
		System.out.println("*******************************");
		System.out.println("Test getOptionSetListSize()");
		System.out.println("Option set list size is "
				+ FordZTW.getOptionSetListSize());
		System.out.println("*******************************");

		// Test option set set methods
		System.out.println("*******************************");
		System.out.println("Test setOptionSet()");

		FordZTW.setOptionSet("Test Optionset set method");

		FordZTW.printAllOptionSet();
		System.out.println("*******************************");

		// Test option set delete method
		System.out.println("*******************************");
		System.out.println("Test deleteOptionSet()");
		FordZTW.deleteOptionSet("Test Optionset set method");
		FordZTW.printAllOptionSet();
		System.out.println("*******************************");

		// Test option set update method
		System.out.println("*******************************");
		FordZTW.updateOptionSetName("Color", "updateColor");
		FordZTW.printAllOptionSet();
		System.out.println("*******************************");

	}
}
