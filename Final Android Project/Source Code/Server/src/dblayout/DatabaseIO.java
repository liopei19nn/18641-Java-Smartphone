package dblayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.DatabasePerson;

/**
 * 
 * @team Three Threads
 * @author Li Pei
 * @AndrewID : lip
 * 
 */
public class DatabaseIO {
	// database URL
	private static final String URL = "jdbc:mysql://localhost:3306";
	// database USER_NAME													
	private static final String USER_NAME = "threethreads";
	// database password
	private static final String PASSWORD = "";
	// database insert sentence prepare statement
	private static final String ADD_SENTENCE = "INSERT INTO TEAMUPFATDOWN.USERS VALUES (?,?,?,?,?,?,?,?,?,?);";

	/*
	 * selectallfromdatabase()
	 * 
	 * 1. get all user information in the database, 
	 * 2. save each row all in a string
	 * 3. save all string in an arraylist
	 * 4. return a string arraylist
	 * 
	 */

	public static ArrayList<String> selectallfromdatabase() {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs;
		
		// save each user as a string in storeusers
		ArrayList<String> storeusers = new ArrayList<>();

		try {
			
			// test if connected to database
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			if (connection != null) {
				System.out.println("Connected to Database Successfully!");
			}
			
			// Select all from database
			String querysentence = "SELECT * FROM TEAMUPFATDOWN.USERS;";
			statement = connection.createStatement();
			rs = statement.executeQuery(querysentence);
			
			
			// 1. read a line from result set, a line is a user
			// 2. set line information into a DatabasePerson
			// 3. turn this DatabasePerson into string
			while (rs.next()) {
				DatabasePerson person = new DatabasePerson();
				
				// 2 set line information into a Database person
				person.setUserName(rs.getString("username"));
				person.setPassWord(rs.getInt("password"));
				person.setNickName(rs.getString("nickname"));
				person.setGender(rs.getString("gender"));
				person.setRival(rs.getString("rivalname"));
				person.setAge(rs.getInt("age"));
				person.setHeight(rs.getInt("height"));
				person.setWeight(rs.getInt("weight"));
				person.setCalConsumption(rs.getInt("calConsumption"));
				person.setCalGoal(rs.getInt("calGoal"));
				
				// 3. turn this DatabasePerson into string
				storeusers.add(person.toString());
			}

			// close the connection
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// return all users information into array list
		return storeusers;

	}// End of selectallfromdatabase()
	
	
	
	/*
	 * selectonefromdatabase()
	 * 
	 * 1. get one user information in the database, 
	 * 2. save the informaiton into a DatabasePerson
	 * 3. return the DatabasePerson as a String
	 * 
	 * if username not exist, it will return a string
	 * "", which length is 0 but not null
	 * 
	 */
	
	
	public static String selectonefromdatabase(String username) {
		Connection connection = null;
		Statement statement = null;
		ResultSet rs;
		
		// save the DatabasePerson into this string
		String returnResult = "";

		try {
			// test the connection
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			if (connection != null) {
				System.out.println("Connected to Database Successfully!");
			}
			
			// request the user with "username" from database
			StringBuilder querysentence = new StringBuilder();
			querysentence.append("SELECT * FROM TEAMUPFATDOWN.USERS");
			querysentence.append(" where username='" + username + "';");
			statement = connection.createStatement();
			rs = statement.executeQuery(querysentence.toString());
			
			// read a user back
			// if user not exist, the while loop will be skipped
			while (rs.next()) {
				DatabasePerson person = new DatabasePerson();
				
				// set the information in a DatabasePerson
				person.setUserName(rs.getString("username"));
				person.setPassWord(rs.getInt("password"));
				person.setNickName(rs.getString("nickname"));
				person.setGender(rs.getString("gender"));
				person.setRival(rs.getString("rivalname"));
				person.setAge(rs.getInt("age"));
				person.setHeight(rs.getInt("height"));
				person.setWeight(rs.getInt("weight"));
				person.setCalConsumption(rs.getInt("calConsumption"));
				person.setCalGoal(rs.getInt("calGoal"));
				
				// return the DatabasePerson as a string
				returnResult = person.toString();

			}

			// close the connection
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnResult;

	}// End of selectonefromdatabase()

	
	
	/*
	 * addonetodatabase()
	 * 
	 * used to insert or update a DatabasePerson in database
	 * 
	 * 1. Delete the row with DatabaserPerson's username first
	 * 2. Insert the DatabasePerson into database
	 * 
	 * 
	 */
	
	public static void addonetodatabase(DatabasePerson person) {
		Connection connection = null;
		Statement statement = null;

		try {
			
			// test connection
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			if (connection != null) {
				System.out.println("Connected to Database Successfully!");
			}
			
			// delete the DatabasePerson first
			StringBuilder cleanquery = new StringBuilder();
			cleanquery.append("DELETE FROM TEAMUPFATDOWN.USERS WHERE username = '");
			cleanquery.append(person.getUserName());
			cleanquery.append("';");
			statement = connection.createStatement();
			statement.executeUpdate(cleanquery.toString());
			
			
			
			// add the DatabasePerson use prepare statement 
			PreparedStatement preparedStatement = connection.prepareStatement(ADD_SENTENCE);
			preparedStatement.setString(1, person.getUserName());
			preparedStatement.setInt(2, person.getPassWord());
			preparedStatement.setString(3, person.getNickName());
			preparedStatement.setString(4, person.getGender());
			preparedStatement.setString(5, person.getRival());
			preparedStatement.setInt(6, person.getAge());
			preparedStatement.setInt(7, person.getHeight());
			preparedStatement.setInt(8, person.getWeight());
			preparedStatement.setInt(9, person.getCalConsumption());
			preparedStatement.setInt(10, person.getCalGoal());
			preparedStatement.executeUpdate();

			// close the connection
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}// End of addonetodatabase()

}
