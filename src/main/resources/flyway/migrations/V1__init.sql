CREATE TABLE gender (
id int NOT NULL AUTO_INCREMENT,
gender varchar(45) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE passenger (
id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(45) NOT NULL,
last_name VARCHAR(45) NOT NULL,
date_of_birth DATE NOT NULL,
gender_id INT NOT NULL,
email VARCHAR(45) NULL,
address VARCHAR(45) NULL,
PRIMARY KEY (id),
FOREIGN KEY (gender_id) REFERENCES gender (id)
);

CREATE TABLE staff (
id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(45) NOT NULL,
last_name VARCHAR(45) NOT NULL,
date_of_birth DATE NOT NULL,
gender_id INT NOT NULL,
title VARCHAR(45) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (gender_id) REFERENCES gender (id)
);

CREATE TABLE plane (
id INT NOT NULL AUTO_INCREMENT,
manufacturer VARCHAR(45) NOT NULL,
model VARCHAR(45) NOT NULL,
seats VARCHAR(45) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE aircrew (
id INT NOT NULL AUTO_INCREMENT,
staff_id INT NOT NULL,
plane_id INT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (staff_id) REFERENCES staff (id),
FOREIGN KEY (plane_id) REFERENCES plane (id)
);

CREATE TABLE city (
id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(45) NOT NULL,
country VARCHAR(45) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE route (
id INT NOT NULL AUTO_INCREMENT,
departure_city_id INT NOT NULL,
departure_date_time DATETIME NOT NULL,
arrival_city_id INT NOT NULL,
arrival_date_time DATETIME NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (departure_city_id) REFERENCES city (id),
FOREIGN KEY (arrival_city_id) REFERENCES city (id)
);

CREATE TABLE plane_route (
id INT NOT NULL AUTO_INCREMENT,
plane_id INT NOT NULL,
route_id INT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (plane_id) REFERENCES plane (id),
FOREIGN KEY (route_id) REFERENCES route (id)
);


CREATE TABLE payment_status (
id INT NOT NULL AUTO_INCREMENT,
status VARCHAR(45) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE order_status (
id INT NOT NULL AUTO_INCREMENT,
status VARCHAR(45) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE orders (
id INT NOT NULL AUTO_INCREMENT,
order_status_id INT NOT NULL,
passenger_id INT NOT NULL,
plane_id INT NOT NULL,
route_id INT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (order_status_id) REFERENCES order_status (id),
FOREIGN KEY (passenger_id) REFERENCES passenger (id),
FOREIGN KEY (plane_id) REFERENCES plane (id),
FOREIGN KEY (route_id) REFERENCES route (id)
);

CREATE TABLE payment (
id INT NOT NULL AUTO_INCREMENT,
order_id INT NOT NULL,
payment_status_id INT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (order_id) REFERENCES orders (id),
FOREIGN KEY (payment_status_id) REFERENCES payment_status (id)
);

CREATE TABLE ticket (
id INT NOT NULL AUTO_INCREMENT,
order_id INT NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (order_id) REFERENCES orders (id)
);