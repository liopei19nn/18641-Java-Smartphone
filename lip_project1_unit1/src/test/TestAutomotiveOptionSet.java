/**
 * 
 */
package test;

import model.Automotive;
import util.FileIO;

/**
 * @author Li Pei
 * @andrew_id lip 
 */
public class TestAutomotiveOptionSet {
	public static void main(String[] args) {
		FileIO fio = new FileIO();
		Automotive FordZTW = fio.readInAutomotive("focus.txt");
		System.out.println("Input a new car");
		System.out.println("*******************************");
		FordZTW.print();
		System.out.println("*******************************");
		
		
		
		//Test option set get methods
		System.out.println("*******************************");
		System.out.println("Test getOptionSetListSize()");
		System.out.println("Option set list size is " + FordZTW.getOptionSetListSize());
		System.out.println("*******************************");
		
		
		//Test option set set methods
		System.out.println("*******************************");
		System.out.println("Test setOptionSet()");
		FordZTW.setOptionSet("Color",0);
		FordZTW.setOptionSet(1, "Test Set Method", 0);
		FordZTW.printAllOptionSet();
		System.out.println("*******************************");
		
		//Test option set delete method
		System.out.println("*******************************");
		System.out.println("Test deleteOptionSet()");
		FordZTW.deleteOptionSet(2);
		FordZTW.deleteOptionSet("Side Impact Airbags");
		FordZTW.printAllOptionSet();
		System.out.println("*******************************");
		
		
		//Test option set update method
		System.out.println("*******************************");
		FordZTW.updateOptionSet("Color",0);
		FordZTW.updateOptionSet(2,"Brakes/Traction Control",0);
		FordZTW.updateOptionSetName(1, "Transmission");
		FordZTW.updateOptionSetName("Power Moonroof", "Test update option set name");
		FordZTW.printAllOptionSet();
		System.out.println("*******************************");
		
		
	}
}
