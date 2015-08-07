Name : Li Pei
Andrew ID : lip

Read me for understanding how to run project 1 unit 5 

You could see readMe.txt and class diagram.jpg in their own project folder, or just see them in this file.

You could see all the results in ResultSnapShot.pdf, or run as guided below please!

1. Load lip_project1_Server and lip_project1_servlet projects to Eclipse.

2. Open server project, server package, run Server.java as Java Application, do not stop it until you finish all your work.

3. Run client to upload car first. Open servlet project, client package, run SocketClient.java as unit 4. Input “1”
   in terminal and choose listed car to upload. After successfully upload at least 1 car, you could press 0 to close this terminal client.

4. Run servlet on your Tomcat. And then use browswer or Eclipse to access the page.
	4.1 Use web browser : Make sure you have upload automobile first. Then input “http://localhost:8080/lip_project1_servlet/index.html”
	as address. You can see the index page. Click on “Click Start” on the page and you can select a car, then configure and see result. 
	
	4.2 Use Eclipse : Make sure you have uploaded automobile first. You could right click and choose to run the project on server. Then 
	the index page will pop up.  Click on “Click Start” on the page and you can select a car, then configure and see result.

5. Stop server and Tomcat server at last.


Key Point for this project :

1. SelectModel.java is the first servlet for you to choose a model.
2. ClientOption.java is the second servlet for you to configure a model.
3. ResultPage.jsp is the final page for your configure result.




---------------------------------------------------------------------------------------------------------------------------------------------
Below is the Project Structure for your understanding, you could also see them in their project folder.


-- 1 lip_project1_server project

1. Project Organization
	(package) server

		—- (interface) SocketServerInterface
			—- openConnection
			—- hanldeSession
			—- closeSession

		—- (interface) SocketServerConstants : deciding the port Debug and autolist’s file name
			—- iDAYTIME_PORT
			—- DEBUG

		—- (class) Server : run the iterative Server and accept client socket
			—- main function

		—- (class) DefaultSocketServer
			working thread and implements the SocketServerInterface
			—- openConnection
			—- hanldeSession
			—- closeSession

		—- (interface) AutoServer
			—- build an auto with Properties Object and save it in linkedHashMap in buildAuto
			—- build auto with incoming txt file from client
			—- return all automobile name in linkedHashMap
			—- send the request model from server to client

		—- (class) BuildCarModelOptions
			—- implement all method in AutoServer
			—- static BuildAuto object

		
	(package) adapter

		-- (interface) CreateAuto
			-- 1 buildAuto
			-- 2 printAuto

		-- (interface) FixAuto
			-- 1 FixAuto

		-- (interface) UpdateAuto
			-- 1 updateOptionSetName
			-- 2 updateOptionPrice

 		—- (interface) EditAuto
			—- 1 edit : update option set name or option price		

		-- (class) BuildAuto
			extends ProxyAutomobile, implement CreateAuto, FixAuto, UpdateAuto, AutoServer
			
		-- (class) ProxyAutomobile
			-- 1 CreateAuto's method
			-- 2 FixAuto's method
			-- 3 UpdateAuto's method
			-- 4 Save car model in .ser file
			-- 5 load car model from .ser file
			—- 6 edit : update option set name or option price
			—- 7 implement method in AutoServer

	
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

	(package) scale
		—- (class) EditOption : class to operate thread method 
				—- 1 Thread update option set name
				—- 2 Thread update option price

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
	required no saved file in server end, only readMe and diagram here







-- 2 lip_project1_servlet Project

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








