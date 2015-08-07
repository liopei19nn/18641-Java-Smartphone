/**
 *
 */
package driver;

import adapter.BuildAuto;
import adapter.JDBCAuto;
import util.DatabaseIO;

/**
 * @author Li Pei
 *
 * Andrew ID : lip
 */
public class Driver {
	public static void main(String[] args) {
		
		// create a database with name "save_automobile"
		// in default superuser "root"
		// it will drop the database with Name "save_automobile" firstly
		// so you could run this Driver class multiple times
		new DatabaseIO().createDataBase();
		
		// build the autos to execute JDBC relevant method
		JDBCAuto autos = new BuildAuto();
		
		// add the autos to database and linked hash map and see the result
		// from database
		System.out.println("************************************************");
		System.out.println("Processing Adding Car To Database");
		System.out.println();
		autos.JDBC_buildAuto("databaseinputfile/Mustang.txt");
		autos.JDBC_buildAuto("databaseinputfile/Charger.txt");
		autos.JDBC_buildAuto("databaseinputfile/Focus.Properties");
		autos.JDBC_buildAuto("databaseinputfile/GTR.Properties");
		System.out.println("************************************************");
		new Driver_PrintHelper().printTable();
		System.out.println();
		
		// delete the autos in database and linked hash map and see the result
		// from database
		System.out.println("************************************************");
		System.out.println("Processing Delete Car in Database");
		System.out.println();
		// Try delete a car first
		autos.JDBC_deleteAuto("Ford Mustang");
		autos.JDBC_deleteAuto("Ford Focus");
		autos.JDBC_deleteAuto("Nissan GTR");
		
		// Try delete the same car to see car not exist the exception
		// you could see the exception "Error -- *********"
		autos.JDBC_deleteAuto("Ford Mustang");
		
		System.out.println("************************************************");
		new Driver_PrintHelper().printTable();
		System.out.println();
		
		// update the autos in database and linked hash map and see the result
		// from database
		System.out.println("************************************************");
		System.out.println("Processing Updating Car in Database");
		System.out.println();
		
		// update an automobile's base price
		autos.JDBC_updateAutoBasePrice("Dodge Charger", 18641);
		
		// update an option price
		autos.JDBC_updateAutoOptionPrice("Dodge Charger", "Color", "Gold", 18641);
		
		// update an option price
		autos.JDBC_updateAutoOptionPrice("Dodge Charger", "Power Moonroof", "Convertible",18641);
		
		// update an not exist automobile's base price 
		// you could see the exception "Error -- *********"
		autos.JDBC_updateAutoBasePrice("Ford Mustang", 18641);
		
		// update an not exist automobile's option price
		// you could see the exception "Error -- *********"
		autos.JDBC_updateAutoOptionPrice("Ford Mustang", "Power Moonroof", "Convertible",18641);
		
		System.out.println("************************************************");
		new Driver_PrintHelper().printTable();

		
	}
}
