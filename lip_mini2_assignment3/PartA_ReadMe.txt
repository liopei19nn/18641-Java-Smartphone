Assignment 3 Part A 
Score Record And Statistics

Name : Li Pei
Andrew ID : lip

This is the read me for part A of Assignment 3. To run the project, load the project file into 
Android Studio and run on the “REAL” Nexus 7 please. 

Attention!! - Emulator cannot run the project correctly due to unknown reason. Please run it on 
a real Nexus 7 or similar tablet devices.

Below is the class description for class and package

(package)database

	--(class)DatabaseConnector : Used to connect to database, you could insert score, delete one score or delete
								all score in database with this class method

(package)exception
	--(class)AutoException : Used to handle input exception, build alert dialog and log in System.out


(package)model
	--(class)Statistics : Here is where a static linked hash map stored to save the data. You could add one, delete one
							or delete all of the linked hash map. This will also help check the input legal, by check 							if the id is already exist. 
	
(package)ui
	--(class)ClearButton : Fragment is implemented here. This button interact with main activity and could help clear 								all data in database and Statistics and refresh the main screen
	
	--(class)Deleteone : Activity to delete one score.
	
	--(class)MainPage : main ui page and keep a list of scores

	--(class)Mainpage_Adapter : fill the main page with list of scores

(package)util
	--(interface)OnFragmentInteractionListener : Interface for interaction between fragment and main activity.










