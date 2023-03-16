CREATE DATABASE IF NOT EXISTS ManagementEmployee;

USE ManagementEmployee;
-- CREATE TABLE ACCOUNT
CREATE TABLE IF NOT EXISTS account(
	id INT PRIMARY KEY AUTO_INCREMENT,
	userName VARCHAR(100) NOT NULL UNIQUE,
	password VARCHAR(50) NOT NULL
);
INSERT INTO 
	ACCOUNT(userName, password)
VALUES 
	("admin", "12345");

	

-- CREATE TABLE DEPARTMENT
CREATE TABLE IF NOT EXISTS department(
	id INT PRIMARY KEY AUTO_INCREMENT,
	departmentName VARCHAR(255),
	deptAddress VARCHAR(255)	
);
INSERT INTO
	department(departmentName,deptAddress )
VALUES
	("Sale","Floor 1"),
	("HR", "Floor 2"),
	("Solution", "Floor 3");


-- CREATE TABLE EMPLOYEE
CREATE TABLE IF NOT EXISTS employee(
	id INT PRIMARY KEY AUTO_INCREMENT,
	firstName VARCHAR(255) NOT NULL, 
	lastName VARCHAR(255) NOT NULL, 
	gender BIT NOT NULL, 
	birthday DATETIME NOT NULL, 
	hire_date DATETIME NOT NULL, 
	phoneNumber VARCHAR(255),
	email VARCHAR(255) NOT NULL, 
	salary float NOT NULL,
	personIncomeTax FLOAT NOT NULL,
	departmentId INT,
	dept_ManagerID INT UNIQUE, 
	FOREIGN KEY(departmentId) REFERENCES department(id),	 
	FOREIGN KEY(dept_ManagerID) REFERENCES department(id)  

);
INSERT INTO 
	employee(firstName, lastName,gender, birthday,hire_date,phoneNumber, email, salary,personIncomeTax)
VALUES
	("thi", "pham", 1, "1999-06-05", "2023-02-10", "0123456789","phamthi123@gmail.com", 1000, 100),
	("nga", "tran", 1, "1996-09-22", "2023-02-10", "0123456789","ngatran1199@gmail.com", 1100, 100);


