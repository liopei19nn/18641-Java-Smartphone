Assignment 2
Name : Li Pei
Andrew ID : lip

This assignment is the Android App for calculating and saving mortgage.

To run the program, run your Android Virtual Device (Minimum Requirement is Froyo, Nexus_4 is recommended) and run the program on this virtual device. 

You can see the app snapshot in ResultSnapshot.pdf, see the class diagram in Assignment2Diagram.jpg load the project folder MortGage


------------------------------------------------------------------------------------------
Below is the Package and class Description

(Package) lip.cmu.com.mortgage
(package) calculator
	(class)AddMortgage : add mortgage page activity for calculate an mortgage and add it in database

(package) database 
	(class)DatabaseConnector : used to create table, get one or all mortgage from database, insert one mortgage into database

(package) ui
	(class)MainPage : main activity for this app, use option menu for add mortgage, list view for all entry in database
	(class)ViewMortgage : view one mortgage in this activity.

(xml): AndroidManifest.xml : for all activities information

(res):
	(package)drawable
		(png)icon.png
		(xml)textview_border.xml : text border for view contact

	(package)layout
		(xml) : activity_main_page.xml : main page xml
		(xml) : add_mortgage.xml : add mortgage xml
		(xml) : view_mortgage.xml : view mortgage xml

	(package)menu 
		(xml) : menu_main_page.xml : option menu for main page
	
	(package)values
		(xml)strings.xml : all strings id and value pair
		(xml)styles.xml : styles for option menu and page








