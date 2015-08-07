/**
 * 
 */
package driver;

import model.Automotive;
import util.FileIO;

/**
 * @author Li Pei
 * @andrew_id lip 
 */
public class Driver {
	public static void main(String[] args) {
		// build a FordZTW with file input
		FileIO fio = new FileIO();
		Automotive FordZTW = fio.readInAutomotive("focus.txt");
		System.out.println("Using file IO to build a car");
		System.out.println("*******************************");
		FordZTW.print();
		System.out.println("*******************************");
		
		// save a FordZTW with serialization
		fio.serializeOutput(FordZTW);
		
		
		// restore a Ford ZTW with deserialization
		Automotive newFordZTW = fio.serializeInput("auto.ser");
		System.out.println("After deserialization");
		System.out.println("*******************************");
		newFordZTW.print();
		System.out.println("*******************************");
	}
	
	

}
