CREATE DATABASE ManagementEmployee;

-- CREATE TABLE ACCOUNT
CREATE TABLE account(
	id INT PRIMARY KEY AUTO_INCREMENT,
	userName VARCHAR(100) NOT NULL,
	password VARCHAR(50) NOT NULL,
	roleId INT,
	FOREIGN KEY(roleId) REFERENCES ROLE(id) 					-- 1 role has many account
	
);
INSERT INTO 
	ACCOUNT(userName, PASSWORD)
VALUES 
	("thipt1", "12345"),
	("ngatt1", "12345");
	
-- CREATE TABLE ROLE
CREATE TABLE role(
	id INT PRIMARY KEY AUTO_INCREMENT,
	roleName VARCHAR(100) NOT NULL
);
INSERT INTO
	ROLE(roleName)
VALUES 
	("Admin");

-- CREATE TABLE EMPLOYEE
CREATE TABLE employee(
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
	FOREIGN KEY(departmentId) REFERENCES department(id), 		 -- 1 department has many employee
	accountId INT UNIQUE,
	FOREIGN KEY(accountId) REFERENCES account(id), 				 -- 1 employee has 1 account
	departmentManager int,
	FOREIGN KEY(departmentManager) REFERENCES department(id)	-- 1 departmentManager has many employee
);
INSERT INTO 
	employee(firstName, lastName,gender, birthday,hire_date,phoneNumber, email, salary,personIncomeTax)
VALUES
	("thi", "pham", 1, "1999-06-05", "2023-02-10", "0123456789","phamthi123@gmail.com", 1000, 100),
	("nga", "tran", 1, "1996-09-22", "2023-02-10", "0123456789","ngatran1199@gmail.com", 1100, 100)



-- CREATE TABLE DEPARTMENT
CREATE TABLE department(
	id INT PRIMARY KEY AUTO_INCREMENT,
	departmentName VARCHAR(255),
	departmentManager int
);
INSERT INTO
	department(departmentName)
VALUES
	("Sale"),
	("HR"),
	("Solution")

SELECT * FROM employee;
