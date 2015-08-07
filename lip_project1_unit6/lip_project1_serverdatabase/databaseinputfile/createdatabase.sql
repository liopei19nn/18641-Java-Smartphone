CREATE DATABASE save_automobile;
USE save_automobile;
CREATE TABLE Automobile(AutoId INT NOT NULL,AutoName varchar(255) NOT NULL,BasePrice FLOAT NOT NULL,PRIMARY KEY (AutoId));
CREATE TABLE OptionSet(OptionSetId INT NOT NULL,OptionSetName varchar(255) NOT NULL,AutoId INT NOT NULL,PRIMARY KEY (OptionSetID),FOREIGN KEY (AutoID) REFERENCES Automobile(AutoID));
CREATE TABLE AutoOption(OptionId INT NOT NULL,OptionName varchar(255) NOT NULL,OptionPrice FLOAT NOT NULL,OptionSetId INT NOT NULL,AutoID INT NOT NULL,PRIMARY KEY (OptionId),FOREIGN KEY (OptionSetId) REFERENCES OptionSet(OptionSetId));