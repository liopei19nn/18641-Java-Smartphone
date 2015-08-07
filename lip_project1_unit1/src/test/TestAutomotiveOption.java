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
public class TestAutomotiveOption {
	public static void main(String[] args) {
		FileIO fio = new FileIO();
		Automotive FordZTW = fio.readInAutomotive("focus.txt");
		System.out.println("Input a new car");
		System.out.println("*******************************");
		FordZTW.print();
		System.out.println("*******************************");
		
		
		//Test option get methods
		System.out.println("*******************************");
		System.out.println("Test getOptionPrice()");
		System.out.println("Option Price of ABS is " + FordZTW.getOptionPrice("Brakes/Traction Control", "ABS"));
		System.out.println("Option Price of ABS is " + FordZTW.getOptionPrice("Brakes/Traction Control", 1));
		System.out.println("Option Price of ABS is " + FordZTW.getOptionPrice(2, "ABS"));
		System.out.println("Option Price of ABS is " + FordZTW.getOptionPrice(2, 1));
		System.out.println("*******************************");
		
		//Test option set methods
		System.out.println("*******************************");
		System.out.println("Test setOption()");
		FordZTW.deleteOption("Transmission", 0);
		FordZTW.setOption("Transmission", "Automatic", 9999);
		FordZTW.setOption(1, 1, "Test setOption()", 815);
		FordZTW.print();
		System.out.println("*******************************");
		
		//Test option delete methods
		System.out.println("*******************************");
		System.out.println("Test deleteOption()");
		FordZTW.deleteOption("Transmission","Test setOption()");
		FordZTW.deleteOption(1,"Standard");
		FordZTW.deleteOption("Brakes/Traction Control",0);
		FordZTW.deleteOption(2,1);
		FordZTW.print();	
		System.out.println("*******************************");
		
		//Test option update methods
		System.out.println("*******************************");
		FordZTW.updateOptionName(0, 0, "updatename0");
		FordZTW.updateOptionName("Color", 1, "updatename1");
		FordZTW.updateOptionName(0, "Infra-Red Clearcoat", "updatename2");
		FordZTW.updateOptionName("Color", "Grabber Green Clearcoat Metallic", "updatename3");
		FordZTW.updateOptionPrice(0, "Sangria Red Clearcoat Metallic", 4);
		FordZTW.updateOptionPrice("Color", 5, 5);
		FordZTW.updateOptionPrice(0, 6, 6);
		FordZTW.updateOptionPrice(0, "CD Silver Clearcoat Metallic", 7);
		FordZTW.printAllOptionSet();
		System.out.println("*******************************");		
		
	}
}
