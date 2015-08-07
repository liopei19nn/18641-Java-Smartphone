Name : Li Pei
Andrew ID : lip

Read me for understanding coding for whole Project1 Unit6 Client Servlet Part

This client servlet is totally the same with client servlet in Unit 5. You could run SocketClient.java to connect to user like Unit 4, or run the 
project on Tomcat to run this like a servlet in Unit 5.

First, you could run SocketClient.java and use terminal to dialog with server. You could do upload and configure and close in terminal.

Second is servlet style. After you upload at least one car to server, you could run this servlet on Tomcat. You could use this servlet through web browser or Eclipse itself. By accessing “localhost:8080/lip_project1_servlet/index.html”, you could select a car from server, configure this car and get the result page. 

1. Project Organization

Update!(Package) servlet
		-- (class)SelectModel : Used in the web page for selecting a car
			-- init : initiate the servlet
			-- doGet : get method for servlet
			-- doPost : post method for servlet
			-- destroy : close client and this servlet
			
		-- (class)ClientOption : Used in the web page for configure a car
			-- init : initiate the servlet
			-- doGet : get method for servlet
			-- doPost : post method for servlet
			-- destroy : close client and this servlet


		-- (class) ServletUtilities : Offered by instructor for simplify operation for output web page.

Update!(Folder)WebContent : save HTML,CSS and JSP file
		-- (Folder) css
			-- (CSS) styles.css : This is copied from Tomcat server’s css file.

		-- (html) index.html :  This is the index page of my project, you could read some FYI and click on “Click Start”
							to start select car model.

		-- (JSP) ResultPage.jsp : This is the JSP file to present the configure result.

	(Package) client
		
		—- (interface) SocketClientInterface
			—- openConnection
			—- hanldeSession
			—- closeSession
		—- (interface) SocketClientConstants : deciding the port Debug and autolist’s file name
			—- iDAYTIME_PORT
			—- DEBUG
			—- automobile file list
		—- (class)SocketClinet
			working thread and implements the SocketClientInterface
			—- openConnection
			—- hanldeSession
			—- closeSession
			-- return object output stream
			-- return object input stream
			-- return if connection to server is open.

	(package) exception
		-- (class) AutoException
			-- log the exception in exception_log.txt

		-- (class) CustomExceptionEnum
			-- enum class for enumeration all exceptions

		-- (class) CustomIOException
			-- extends AutoException, but for inner IO exception handle

		-- (class) FixHelper
			-- fix all exception listed in CustomExceptionEnum

	(package) model
		-- (class) Automotive : car configuration class
			-- 1 getter for name, make and model, setter make and model
			-- 2 getter and setter for base price

			about option set operation
			-- 3 get option set number
			-- 4 get option set by name
			-- 5 set option set by name
			-- 6 delete option set by name
			-- 7 update option set name by name
			-- 8 print all option set
			—- 9 method to get option set name by option set name to test thread method for update option set name
			-- 10 input a optionset name and get all options and their price in linked hash map

			about option operation
			-- 9 get option by name
			-- 10 get option price by name
			-- 11 set option by name
			-- 12 delete option by name
			-- 13 update option price by name or index

			print method
			-- 14 print base and whole option set with option

			about choice
			-- 15 set option choice
			-- 16 get option choice
			-- 17 get option choice price
			-- 18 get total price under these choice and base price
			-- 19 print choice
			-- 20 print total price

		-- (class) Optionset : option set of car configuration
			-- 1 getter and setter of name
			-- 2 get option number of option set
			-- 3 get option by name
			-- 4 set option by name
			-- 5 delete option by name
			-- 7 update option price by name or index
			-- 8 print all options of this option set
			-- 9 set choice
			-- 10 gete choice name
			-- 11 get choice price
			-- 12 return all options and their price in linked hash map


			-- (inner class) Option : option in a option set
				-- 1 getter and setter for option name
				-- 2 getter and setter for option price


		—- (class) EditOptionEnum : enumeration of all edit options you could do with automobile

	(package) util
		-- (class) FileIO : input file, serialization and deserialization of object
			—- read in a .Properties file and return a car
			—- read in a .txt file and return a car
			—- read in a Properties object and return a car
			—- save a car
			—- load a car
			—- return a list of name of automobile file

3. All helper files
	All this files is used for upload car using SockedClient run as Java Application in terminal.
	—- 1 AutomobileFileList.txt : save all the available car file name below (2-5).
	—- 2 Charger.txt
	—- 3 GTR.Properties
	—- 4 Focus.Properties
	—- 5 Mustang.txt