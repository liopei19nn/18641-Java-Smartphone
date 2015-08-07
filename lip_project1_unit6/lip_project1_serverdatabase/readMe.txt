Name : Li Pei
Andrew ID : lip

Read me for understanding coding for whole Project1 Unit 6 Server with Database Part

In this project, you could run project like a server same with Unit 5, or run program in driver package to access database method.

-- 1 To run this project for Unit 6 console output, firstly,run your local mysql database, make sure there is a default superuser named “root” with no password.
	Then Load project in Eclipse, open lip_project1_serverdatabase project, in the driver package, run Driver.java, you could see all the processing, exception handling and output in console terminal.


-- 2 To run server, open server package and run Server.java file, everything is the same in Unit 5.




1. Project Organization

	(package) adapter
Update!	-- (interface) JDBCAuto
			-- 1 JDBC_buildAuto
			-- 2 JDBC_deleteAuto
			-- 3 JDBC_updateAutoBasePrice
			-- 4 JDBC_updateAutoOptionPrice

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
			extends ProxyAutomobile, implement CreateAuto, FixAuto, UpdateAuto, AutoServer, JDBCAuto
			
		-- (class) ProxyAutomobile
			-- 1 CreateAuto's method
			-- 2 FixAuto's method
			-- 3 UpdateAuto's method
			-- 4 Save car model in .ser file
			-- 5 load car model from .ser file
			—- 6 edit : update option set name or option price
			—- 7 implement method in AutoServer
!Update		-- 8 JDBC_buildAuto : build an automobile in database and linkedhashmap (LHM)
!Update		-- 9 JDBC_deleteAuto : delete an automobile in database and LHM
!Update		-- 10 JDBC_updateAutoBasePrice : update an automobile base price in database LHM
!Update		-- 11 JDBC_updateAutoOptionPrice : update an option price in database and LHM


!update
	(package)	driver
		-- (class) Driver : run this class for test. This class including operate database and 								corresponding output in console terminal

		-- (class) Driver_PrintHelper : Print Automobile table, OptionSet table, AutoOption table 
						separately or in one run

	(package) util
		-- (class) FileIO : input file, serialization and deserialization of object
			—- read in a .Properties file and return a car
			—- read in a .txt file and return a car
			—- read in a Properties object and return a car
			—- save a car
			—- load a car
			—- return a list of name of automobile file

!Update	-- (class) DatabaseIO : input,delete,update data in database
			-- createDataBase : delete database with name “save_automobile” first and create a new
						database with name “save_automobile” in “root” user.
			-- addToDatabase : add an automobile totally in database
			-- deleteAutoInDatabase : delete an automobile totally in database
			-- updateAutoBasePrice : update an automobile’s base price
			-- updateAutoOptionPrice : update an option price

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
		     —- 9 add method to get option set name by option set name to test thread method for update option set name

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


			-- (inner class) Option : option in a option set
				-- 1 getter and setter for option name
				-- 2 getter and setter for option price

	(package) scale
		—- (class) EditOption : class to operate thread method 
				—- 1 Thread update option set name
				—- 2 Thread update option price

		—- (class) EditOptionEnum : enumeration of all edit options you could do with automobile





3. All helper files
	(folder) lib 
		-- 1 mysql-connector-java-5.1.36-bin.jar : This file is JDBC Jar for build and access database.

	(folder) databaseinputfile : This folder is used for input file in driver to test database related function, due to the fact that for Unit 5 , there should not be any file on server side, so I build this folder to store the input file for this Unit 6
		-- 1 AutomobileFileList.txt
		-- 2 Charger.txt
		-- 3 Focus.Properties
		-- 4 GTR.Properties
		-- 5 Mustang.txt
		-- 6 createdatabase.sql : mysql command for create a database and tables in database “root” user
		-- 7 executedatabase.Properties ： mysql command for execute database create, delete, update operation
		-- 8 printdatabase.Properties : mysql command for print the database in console

