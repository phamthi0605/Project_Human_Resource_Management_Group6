CREATE DATABASE if NOT EXISTS	hr_management;
USE hr_management;
-- department table
CREATE TABLE department(
	id INT PRIMARY KEY AUTO_INCREMENT,
	department_name VARCHAR(200),
	address VARCHAR(200)
			
);

INSERT INTO department(department_name, address)
VALUES
		('HR', '1 floor'),
		('Sale', '2 floor'),
		('Accounting', '3 floor');

		

-- employee table
CREATE TABLE employee(
	id INT PRIMARY KEY AUTO_INCREMENT,
	employee_id VARCHAR(200) NOT NULL UNIQUE,
	full_name VARCHAR(200) NOT NULL,
	position VARCHAR(200),
	age INT UNSIGNED,
	phone VARCHAR(200) UNIQUE,
	email VARCHAR(200) UNIQUE,
	salary FLOAT,
	person_Income_Tax FLOAT,
	hire_date DATE,
	end_date DATE,
	department_id INT,
	is_manager ENUM('1'),
	UNIQUE (department_id, is_manager),
	FOREIGN KEY (department_id) REFERENCES department(id)
);


INSERT INTO employee(employee_id, full_name, position, age, phone, email, salary, hire_date, end_date)
VALUES
		('1', 'Pham Thi Thi', 'developer', 24, '0123456789', 'thipt1@gmail.com', 1000, '2019-02-12', NULL),  
		('2', 'Tran Thi Nga', 'tester', 26, '076721783', 'ngatran12@gmail.com',900, '2020-03-12', NULL);
	


-- bảng admin
CREATE TABLE ADMIN(
	id INT PRIMARY KEY AUTO_INCREMENT,
	full_name VARCHAR(200),
	username VARCHAR(200),
	password VARCHAR(200)		
);

INSERT INTO admin(full_name, username, password)
VALUES
	('phamthi', 'thipt1', '1234');
		


		



















