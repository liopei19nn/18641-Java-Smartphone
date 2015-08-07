Name : Li Pei
Andrew ID : lip

Read me for understanding coding for whole Project1 Unit2


1. Run the project
	-- 1 Unzip the .zip file, import the whole project file into eclipse
	-- 2 Run Driver.java to see the required method test for autoBuilder
	-- 3 Run DriverChoice.java to see the method about choice of Automobile
	-- 4 Run DriverMultiChoice.java to see the method about operation of multi-input automobile
	-- 5 Run DriverTest Exception to see the test of all exception and check the log of exception in
		 exception_log.txt

2. Project Organization
	(package) adapter

		-- (interface) CreateAuto
			-- 1 buildAuto
			-- 2 printAuto

		-- (interface) FixAuto
			-- 1 FixAuto

		-- (interface) UpdateAuto
			-- 1 updateOptionSetName
			-- 2 updateOptionPrice

		-- (class) BuildAuto
			extends ProxyAutomobile, implement CreateAuto, FixAuto, UpdateAuto
			
		-- (class) ProxyAutomobile
			-- 1 CreateAuto's method
			-- 2 FixAuto's method
			-- 3 UpdateAuto's method
			-- 4 Save car model in .ser file
			-- 5 load car model from .ser file

	(package) driver
		-- (class) Driver : test buildAuto's required method
		-- (class) DriverChoice : test method about choice of Automobile class
		-- (class) DriverMultiAutomobile : test method about multi-input automobile
		-- (class) DriverTestException : test all custom exception 

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
		-- （class) Fleet : A linkedHashMap of Automobile
			-- 1 set automobile from file or object
			-- 2 get automobile
			-- 3 update automobile option set name
			-- 4 update automobile option name
			-- 5 delete automobile from this Fleet


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

	(package) test
		-- (class) TestAutomotiveOption : test all methods about option operation in Automotive class
		-- (class) TestAutomotiveOptionSet : test all the methods about optionset operation
										 in Automotive class

	(package) util
		-- (class) FileIO : input file, serialization and deserialization of object
			-- 1 readInAutomotive : read in automotive configuration file
			-- 2 serializeOutput : serialization of automotive object
			-- 3 serializeInput : deserialization of automotive object

3. All helper files
	-- 1 CustomException.txt : list all exception and its according error number
	-- 2 default_car_model.txt : default car model file
	-- 3 Default_NISSAN_GTR.ser : default loading car model
	-- 4 exception_log.txt : run time exception log output file
	-- 5 FlawFocus.txt : a model Focus missing base price and power moonroof option price
	-- 6 Focus.txt : input file of a automotive
	-- 7 Nissan.txt : input file of a automotive
	-- 8 NISSAN GTR.ser : serialization output of an NISSAN GTR automotive object
	-- 9 input_format.txt : explain the input format of Focus.txt
	-- 10 readMe.txt : the file help you understand
	-- 11 Unit2Diagram.jpg : class diagram of this project and unit2
	-- 12 test_output : all outputs of driver and test classes
		-- 1 test_output_DirverMultiAutomobile.txt : test input multiple automobile and operation
		-- 2 test_output_Driver.txt : test required method for operation buildAuto
		-- 3 test_output_DriverChoice.txt : test choice method of Automobile
		-- 4 test_output_DriverTestException.txt : test exception handler
		-- 5 test_output_option.txt : test option operation 
		-- 6 test_output_optionSet.txt : test option set operation

