Read me for server

1. Set Up Server MySQL database.
Run your PC based MySQL first. Log in with default user “root” in database. Create database named “TEAMUPFATDOWN”, and create a table using following SQL sentence :

CREATE TABLE USERS(
	username varchar(255) NOT NULL,
	password int NOT NULL,
	nickname varchar(255) DEFAULT 'EMPTY',
	gender varchar(255) DEFAULT 'EMPTY',
	rivalname varchar(255) DEFAULT 'EMPTY',
	age int DEFAULT 0,
	height int DEFAULT 0,
	weight int DEFAULT 0,
	calConsumption int DEFAULT 0,
	calGoal int DEFAULT 0,
	PRIMARY KEY (username)	
)


After create database for application, create a user named “threethreads” with NO password, and grant all privilege on the database “TEAMUPFATDOWN” to this user. 


2. Run Server 
Run server to handle the communication between client and server. Load the server project into Eclipse and run Server.java class, you will see the terminal showing 

		Listening on port 2333

which means that the server has run successfully. You do not need to install JDBC and rebuild path for server project, all required support ready for use.

Notice that if you miss any step for running server or database, our App would not work.

