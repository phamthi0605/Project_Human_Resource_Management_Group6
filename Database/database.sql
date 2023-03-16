CREATE DATABASE ManagementEmployee;
CREATE TABLE employee(
	employeeID INT PRIMARY KEY AUTO_INCREMENT,
	firstName VARCHAR(255) NOT NULL,
	lastName VARCHAR(255) NOT NULL,
	gender VARCHAR(15) NOT NULL,
	birthday DATETIME NOT NULL,
	phoneNumber VARCHAR(20) NOT NULL,
	email VARCHAR(50) NOT NULL,
	hire_date DATETIME NOT NULL,
	salary FLOAT NOT NULL,
	jobTitle VARCHAR(255) NOT NULL,
	personIncomeTax FLOAT NOT NULL,
	department_id INT,
	FOREIGN KEY department_id REFERENCES department(departmentID) -- 1 department has many employee
);
CREATE TABLE department(
	departmentID INT PRIMARY KEY AUTO_INCREMENT,
	departmentName VARCHAR(255) NOT NULL,
);
