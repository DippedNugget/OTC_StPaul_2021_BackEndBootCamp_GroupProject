-- This is the Database Creation SQL file
-- OTC_StPaul_2021_BackEndBootCamp
-- Created by Charlee Thao, Andrew Cham, & Adam Fite

CREATE database IF NOT EXISTS Lego_Store;

use Lego_Store;

DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS customers;

CREATE TABLE products(
	product_id int(11) NOT NULL auto_increment PRIMARY KEY,
	theme varchar(30) NOT NULL,
	set_name varchar(30) NOT NULL,
	piece_count int(11) NOT NULL,
	quantity int(11) NOT NULL
);

CREATE TABLE customers(
	customer_id int(11) NOT NULL auto_increment PRIMARY KEY,
	full_name varchar(60) NOT NULL,
	address varchar(60),
	phone varchar(14),
	email varchar(255) NOT NULL,
	join_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE sales(
	sale_id int(11) NOT NULL auto_increment PRIMARY KEY,
	quantity int(11) NOT NULL,
	price decimal(5, 2) NOT NULL,
	product_id int(11) NOT NULL,
	customer_id int(11) NOT NULL,
	FOREIGN KEY (product_id) REFERENCES products (product_id),
	FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
);