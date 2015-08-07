Name : Li Pei
Andrew ID : lip

Read me for understanding the whole project for Assignment 2

1. Run the project
	-- 1 Unzip the .zip file, import the whole project file into Eclipse.
	-- 2 Open the package "run", open the class "MainRunning", and run this class
	to see the output according to the instruction.

2. Project Organization 

	(package) exception : all custom exceptions
		(class) StudentNumberOutOfBound : handle the exception whose input student number above 40
		(class) StudentScoreNumberException : handle the exception that number of input scores is not 5

	(package) prototype : all interface and abstract class
		(interface) Printer : interface for classes need to print
		(abstract class) People : abstract of student, this class has no instance field and method,and 
					I think in future if we have class like teacher or officer, this class 
					can be used for some abstraction
		(abstract class) AbstractStatistics : abstract of statistics and generic for subclass of people

	(package) run : running the project
		(class) MainRunning : run the whole project

	(package) statistics : package for Statistics
		(class) Statistics : extends AbstractStatistics and implement Printer interface

	(package) student : package for student
		(class) Student : extends people and implement Printer interface

	(package) test : test for all class routines
		(class) TestStatistics : test Statistics class, test a list of 10 student with only 3 students instance
		(class) TestStudent : test Student class
					-- 1 test SID setter and getter
					-- 2 test scores setter and getter
					-- 3 test toPrintValue()
					-- 4 test input not 4 digit scenario
					-- 5 test input more than 5 scores scenario
					-- 6 test input less than 5 scores scenario
		(class) TestUtil : test Util class
					-- 1 test input 15 students
					-- 2 test input 0 students
					-- 3 test input 45 students

	(package) util : package for Util
		(class) Util : Reads the file and builds student array. Open the file using FileReader object. 
				In a loop read a line using readLine method. 
				Tokenize each line using StringTokenizer Object. 
				Each token is converted from String to Integer using parseInt method. 
				Value is then saved in the right property of Student Object. 

3. Other helper files
	-- 1 filename.txt : normal student input with title and 15 students
	-- 2 filename1.txt : exceptional input with title and 0 students
	-- 3 filename2.txt : exceptional input with title and 45 students
	-- 4 statistic.txt : manually calculation of statistics for 15 students to test Statistics class
	-- 5 Diagram.jpg : class, interface and exception diagram for project
	-- 6 test_output.txt : all test output are collected in this file
	



	