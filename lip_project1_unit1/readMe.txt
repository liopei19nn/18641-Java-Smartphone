Name : Li Pei
Andrew ID : lip

Read me for understanding coding for whole Project1 Unit1


1. Run the project
	-- 1 Unzip the .zip file, import the whole project file into eclipse
	-- 2 The input file is "Focus.txt", and the serilization output is auto.ser
	-- 3 Run the "Driver" class in "driver" package to see the input automotive and restored automotive


2. Project Organization

	(package) driver
		-- (class) Driver : test this project as a whole

	(package) model
		-- (class) Automotive : car configuration class
			-- 1 getter and setter for name, make and model
			-- 2 getter and setter for base price

			about option set operation
			-- 3 get option set number
			-- 4 get option set by name or index
			-- 5 set option set by name or index
			-- 6 delete option set by name or index
			-- 7 update option set by name or index
			-- 8 update option set name by name or index
			-- 9 print all option set

			about option operation
			-- 10 get option by name or index
			-- 11 get option price by name or index
			-- 12 set option by name or index
			-- 13 delete option by name or index
			-- 14 update option name by name or index
			-- 15 update option price by name or index

			print method
			-- 16 print base and whole option set with option

		-- (class) Optionset : option set of car configuration
			-- 1 getter and setter of name
			-- 2 get option number of option set
			-- 3 get option by name or index
			-- 4 set option by name or index
			-- 5 delete option by name or index
			-- 6 update option name by name or index
			-- 7 update option price by name or index
			-- 8 print all options of this option set

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
			-- 3 serializeInput : 

	
3. Special Point
	-- 1 All the methods in class "OptionSet" and "Option“ are protected
	-- 2 All the variables in class "Automotive","OptionSet" and "Option" are private
	-- 3 Some methods in class "Automotive" are protected because they return "Optionset" or "Option"
	 	which should not be accessed from outside the package.
	-- 4 Class in other package who want to access option or optionset must use the method in 
		"Automotive" class. 
	-- 5 All methods in "Automotive", "Optionset" and "Option" are tested, their output according
		to different method are printed in "test_output_optionset .txt" and "test_output_option.txt". 


4. All helper files
	-- 1 auto.ser : serialization output of an automotive object
	-- 2 Focus.txt : input file of a automotive
	-- 3 readMe.txt : the file help you understand
	-- 4 test_output_option.txt : test all the methods about option operation in Automotive class and 
								print here.
	-- 5 test_output_optionset.txt : test all the methods about optionset operation in Automotive class
								and print here.
	-- 6 Unit1Diagram.jpg : class diagram of this project and unit1
	-- 7 input_format.txt : explain the input format of Focus.txt