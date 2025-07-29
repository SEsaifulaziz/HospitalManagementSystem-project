CREATE DATABASE hotelmanagementsystem;
USE hotelmanagementsystem;

CREATE TABLE login(
 username VARCHAR(50),
 password VARCHAR(50)
);

INSERT INTO login
VALUES
('saifulaziz', 'topik712');

CREATE TABLE employee(
  name VARCHAR(50),
  age VARCHAR(50),
  gender VARCHAR(50),
  job VARCHAR(50),
  salary INT,
  phone VARCHAR(50),
  email  VARCHAR(50),
  cnic INT
);

select * from employee;


CREATE TABLE room(
  roomnumber VARCHAR(50),
  availability VARCHAR(50),
  status VARCHAR(50),
  price INT, 
  type VARCHAR(50)
);

SELECT * FROM room;

CREATE TABLE driver(
  name VARCHAR(50),
  age VARCHAR(50),
  gender VARCHAR(50),
  company VARCHAR(50),
  model VARCHAR(50),
  availability VARCHAR(50),
  location VARCHAR(50)
);

SELECT * FROM driver;

CREATE TABLE customer(
  id VARCHAR(50),
  number VARCHAR(50), 
  name VARCHAR(50),
  gender VARCHAR(50),
  country VARCHAR(50),
  room VARCHAR(50), 
  checkintime VARCHAR(50),
  deposit INT
);

SELECT * FROM customer;

CREATE TABLE department(
department VARCHAR(50),
budget INT
);

INSERT INTO department
VALUES
('Front Office', 500000),
('Housekeeping', 40000),
('Food and Beverge', 23000),
('Kitchen or Food Production', 540000),
('Security', 320000);

SELECT * FROM department;



SET SQL_SAFE_UPDATES = 1;