/**
 *
 */
package util;

import java.io.*;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Properties;
import model.Automobile;

/**
 * @author Li Pei
 *
 *         Andrew ID : lip
 */
public class DatabaseIO {
	private static final String URL = "jdbc:mysql://localhost:3306";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "";

	/*
	 * createDatabase()
	 * 
	 * create a database
	 * 
	 * 
	 */

	public void createDataBase() {
		Statement statement = null;
		BufferedReader br = null; // buffer reader
		try {
			// get connection
			Connection connection = null;
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			if (connection != null) {
				System.out.println("Connected to Database Successfully!");
			}
			statement = connection.createStatement();
			String command = null;
			
			
			// get the execute command list for execute
			Properties executelist = new Properties();
			try {
				FileInputStream in = new FileInputStream("databaseinputfile/executedatabase.Properties");
				executelist.load(in);
				in.close();
			}  catch (IOException e){
				// catch IOException
					System.out.println("Error -- " + e.toString());
			}
			
			
			
			// firstly delete the database with name "save_automobile"
			// to avoid "DATABASE ALREADY EXIST" exception
			try {
				command = executelist.getProperty("DeleteDataBase");
				statement.executeUpdate(command);
			} catch (Exception e) {
				// if there is no such database, then do nothing and skip
			}
			
			
			// insert auto name and base price in Automobile table
			br = new BufferedReader(new FileReader(new File("databaseinputfile/createdatabase.sql")));
			// read in the create database and tables command from file;
			while ((command = br.readLine()) != null) {
				statement.executeUpdate(command);
			}
			// close the file stream;
			
			br.close();
			connection.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * addToDatabase()
	 * 
	 * add an automobile into database
	 * 
	 * 
	 */
	public int[] addToDatabase(Automobile auto, int autoID, int optSetIDStart, int optionIDStart) {
		int[] idrecorder = new int[2]; // [0] for option set id, [1] for option
										// id
		int optionSetID = optSetIDStart;
		int optionID = optionIDStart;
		LinkedHashMap<String, Float> options = null;

		String[] optionSetName = { "Color", "Transmission", "ABS/Traction Control", "Side Impact Airbags",
				"Power Moonroof" };

		try {

			// get connection
			Connection connection = null;
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			if (connection != null) {
				System.out.println("Connected to Database Successfully!");
			}
			
			
			// get the execute command list for execute
			Properties executelist = new Properties();
			try {
				FileInputStream in = new FileInputStream("databaseinputfile/executedatabase.Properties");
				executelist.load(in);
				in.close();
			}  catch (IOException e){
				// catch IOException
					System.out.println("Error -- " + e.toString());
			}

			// insert auto name and base price in Automobile table
			PreparedStatement preparedStatement = connection.prepareStatement(executelist.getProperty("AddAutomobile"));
			preparedStatement.setInt(1, autoID);
			preparedStatement.setString(2, auto.getName());
			preparedStatement.setFloat(3, auto.getBasePrice());
			preparedStatement.executeUpdate();

			// insert option set name into OptionSet table
			for (int i = 0; i < optionSetName.length; i++) {
				preparedStatement = connection.prepareStatement(executelist.getProperty("AddOptionSet"));
				preparedStatement.setInt(1, optionSetID);
				preparedStatement.setString(2, optionSetName[i]);
				preparedStatement.setInt(3, autoID);
				preparedStatement.executeUpdate();
				
				
				// insert options into AutoOption table
				options = auto.getOptionSetMap(optionSetName[i]);
				for (String option : options.keySet()) {
					
					preparedStatement = connection.prepareStatement(executelist.getProperty("AddOption"));
					preparedStatement.setInt(1, optionID);
					preparedStatement.setString(2, option);
					preparedStatement.setFloat(3, options.get(option));
					preparedStatement.setInt(4, optionSetID);
					preparedStatement.setInt(5, autoID);
					preparedStatement.executeUpdate();
					
					optionID++;// add option id
				}

				optionSetID++;// add option set id
			}
			// set the new starter optioset id and option id
			idrecorder[0] = optionSetID;
			idrecorder[1] = optionID;

			System.out.println("Add " + auto.getName() + " To Database Successfully!");
			// close connection
			preparedStatement.close();
			connection.close();

		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		return idrecorder;
	}

	/*
	 * deleteAutoInDatabase()
	 * 
	 * delete an automobile into database
	 * 
	 */
	public void deleteAutoInDatabase(String AutoName) {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			if (connection != null) {
				System.out.println("Connected to Database Successfully!");
			}
			
			// get the execute command list for execute
			Properties executelist = new Properties();
			try {
				FileInputStream in = new FileInputStream("databaseinputfile/executedatabase.Properties");
				executelist.load(in);
				in.close();
			}  catch (IOException e){
				// catch IOException
					System.out.println("Error -- " + e.toString());
			}

			
			// get the AutoId for this AutoName
			PreparedStatement preparedStatement = connection.prepareStatement(executelist.getProperty("SelectAutomobileByName"));
			preparedStatement.setString(1, AutoName);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			int AutoID = Integer.parseInt(rs.getString("AutoID"));
			
			
			// delete all the options of this AutoId
			preparedStatement=connection.prepareStatement(executelist.getProperty("DeleteAutoOption"));
			preparedStatement.setInt(1, AutoID);
			preparedStatement.executeUpdate();
			
			// delete all the option set of this AutoId
			preparedStatement=connection.prepareStatement(executelist.getProperty("DeleteOptionSet"));
			preparedStatement.setInt(1, AutoID);
			preparedStatement.executeUpdate();

			// delete the automobile of this AutoId
			preparedStatement=connection.prepareStatement(executelist.getProperty("DeleteAutomobile"));
			preparedStatement.setInt(1, AutoID);
			preparedStatement.executeUpdate();
			
			System.out.println("Delete " + AutoName + " Success!");

			// close the connection
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 

	}

	/*
	 * updateAutoBasePrice()
	 * 
	 * update an automobile's base price in database
	 * 
	 */

	public void updateAutoBasePrice(String AutoName, float newBasePrice) {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			if (connection != null) {
				System.out.println("Connected to Database Successfully!");
			}
			
			// get the execute command list for execute
			Properties executelist = new Properties();
			try {
				FileInputStream in = new FileInputStream("databaseinputfile/executedatabase.Properties");
				executelist.load(in);
				in.close();
			}  catch (IOException e){
				// catch IOException
					System.out.println("Error -- " + e.toString());
			}

			
			// update the base price
			PreparedStatement preparedStatement = connection.prepareStatement(executelist.getProperty("UpdateAutoBasePrice"));
			preparedStatement.setFloat(1,newBasePrice);
			preparedStatement.setString(2, AutoName);
			preparedStatement.executeUpdate();
			
			System.out.println("Update " + AutoName + " BasePrice Successfully");

			// close the connection
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Automobile" + AutoName + " not in the database");
		} 
	}

	/*
	 * updateAutoOptionPrice()
	 * 
	 * update an automobile's option price in database
	 * 
	 */

	public void updateAutoOptionPrice(String AutoName, String OptionSetName, String OptionName, float newOptionPrice) {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			if (connection != null) {
				System.out.println("Connected to Database Successfully!");
			}

			// get the execute command list for execute
			Properties executelist = new Properties();
			try {
				FileInputStream in = new FileInputStream("databaseinputfile/executedatabase.Properties");
				executelist.load(in);
				in.close();
			}  catch (IOException e){
				// catch IOException
					System.out.println("Error -- " + e.toString());
			}

			
			// get the AutoId for this AutoName
			PreparedStatement preparedStatement = connection.prepareStatement(executelist.getProperty("SelectAutomobileByName"));
			preparedStatement.setString(1, AutoName);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			int AutoId = Integer.parseInt(rs.getString("AutoId"));

			
			// get the OptionId for this AutoName
			preparedStatement = connection.prepareStatement(executelist.getProperty("GetOptionSetID"));
			preparedStatement.setString(1, OptionSetName);
			preparedStatement.setInt(2, AutoId);
			rs = preparedStatement.executeQuery();
			rs.next();
			int OptionSetId = Integer.parseInt(rs.getString("OptionSetId"));

			
			
			// change the option price with optionset id, auto id and option
			// name
			preparedStatement = connection.prepareStatement(executelist.getProperty("UpdateOptionPrice"));
			preparedStatement.setFloat(1, newOptionPrice);
			preparedStatement.setInt(2, OptionSetId);
			preparedStatement.setInt(3, AutoId);
			preparedStatement.setString(4, OptionName);
			preparedStatement.executeUpdate();

			System.out.println("Update " + AutoName + " " + OptionSetName + " " + OptionName + " Successfully");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 

	}

}
