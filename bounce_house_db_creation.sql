DROP DATABASE IF EXISTS bounce_house_db;
CREATE DATABASE bounce_house_db;
USE bounce_house_db;

CREATE TABLE customers
(
	customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

DROP TABLE IF EXISTS order_audit;

CREATE TABLE order_audit
(
	order_id INT NOT NULL,
    product_id INT NOT NULL,
    order_total FLOAT NOT NULL,
    action_type VARCHAR(50) NOT NULL,
    action_date DATETIME NOT NULL
);

CREATE TABLE categories
(
	category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_type VARCHAR(50) NOT NULL
);

CREATE TABLE rental_products
(
	product_id INT AUTO_INCREMENT PRIMARY KEY,
    category_id INT,
    product_name VARCHAR(50) NOT NULL,
    dimensions VARCHAR(50),
    price INT NOT NULL,
    qauntity INT,
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

CREATE TABLE orders
(
	order_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    customer_id INT,
    order_date DATE NOT NULL,
    rental_days_amount INT NOT NULL DEFAULT 1,
    order_total INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES rental_products(product_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE order_products
(
	item_id INT PRIMARY KEY AUTO_INCREMENT,
	product_id INT,
    order_id INT,
    quantity_taken INT,
    FOREIGN KEY (product_id) REFERENCES rental_products(product_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);


CREATE TABLE product_images
(
	product_id INT,
    image BLOB,
    FOREIGN KEY (product_id) REFERENCES rental_products(product_id)
);


CREATE TABLE rental_reviews
(
	review_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    customer_id INT,
    product_id INT,
    review_text VARCHAR(50),
    rating INT,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (product_id) REFERENCES rental_products(product_id)
);


CREATE TABLE payments
(
	payment_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    card_type VARCHAR(20) NOT NULL,
    card_number VARCHAR(20) NOT NULL,
    card_expires DATE NOT NULL,
    billing_address VARCHAR(50) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

CREATE TABLE rental_dates
(
	rent_date_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    FOREIGN KEY (product_id) REFERENCES rental_products(product_id),
    FOREIGN KEY (order_product_id) REFERENCES order_products(order_product_id)
);



CREATE TABLE rental_addresses
(
	addr_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    zip_code VARCHAR(50) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- make order products table
-- include the address as part of the order
-- fix classes in intelij



CREATE VIEW product_summary AS 
SELECT product_name, order_total FROM rental_products, orders;

CREATE VIEW taken_dates AS 
SELECT p.product_id, product_name, start_date, end_date, quantity AS amount_available 
FROM rental_dates d JOIN rental_products p ON d.product_id = p.product_id;

CREATE VIEW product_ratings AS
SELECT AVG(rating) AS average_rating, p.product_id, product_name FROM rental_reviews r JOIN rental_products p ON r.product_id = p.product_id;

-- triggers

DELIMITER $$

CREATE TRIGGER order_after_insert AFTER INSERT ON orders FOR EACH ROW
BEGIN
INSERT INTO order_audit(order_id, product_id, order_total, action_type, action_date)
VALUES (NEW.order_id, NEW.product_id, NEW.order_total, 'INSERTED',NOW());

END $$
DELIMITER ;

DELIMITER $$

DROP TRIGGER IF EXISTS order_after_delete;

CREATE TRIGGER order_after_delete AFTER DELETE ON orders FOR EACH ROW
BEGIN
INSERT INTO order_audit(order_id, product_id, order_total, action_type, action_date)
VALUES (OLD.order_id, OLD.product_id, OLD.order_total, 'INSERTED',NOW());

END $$
DELIMITER ;



DROP PROCEDURE IF EXISTS createOrder;

DELIMITER $$
CREATE PROCEDURE createOrder(IN in_customer_id INT, IN in_product_id INT, 
IN in_rental_days_amount INT, IN in_start_date DATE, IN in_end_date DATE, IN amount_chosen INT)
BEGIN
DECLARE total_price INT;
DECLARE rental_days INT;

-- add to dates
INSERT INTO rental_dates(product_id, start_date, end_date)
VALUES (in_product_id, in_start_date, in_end_date); 

-- find how many days user wants to rent
SELECT DATEDIFF(start_date, end_date) INTO rental_days
FROM rental_dates
WHERE product_id = in_product_id;

-- get the price
SELECT price, (price * amount_chosen) AS total INTO total_price
FROM rental_products 
WHERE product_id = in_product_id; 

-- make an order
INSERT INTO orders(customer_id, order_date, start_date, end_date, rental_days_amount, order_total)
VALUES(in_customer_id, curdate(), in_start_date, in_end_date, rental_days, total_price);


INSERT INTO order_products(product_id, order_id, quantity_taken)
VALUES(in_product_id, in_order_id, amount_chosen);


END $$
DELIMITER ;

UPDATE rental_products
SET quantity = 1
WHERE product_id = 1;


DROP PROCEDURE cancelOrder;

DELIMITER $$
CREATE PROCEDURE cancelOrder(IN i_order_id INT)
BEGIN

DELETE FROM rental_dates WHERE order_id = i_order_id;

DELETE FROM order_products WHERE order_id = i_order_id;

DELETE FROM orders WHERE order_id = i_order_id;



END $$
DELIMITER ;

CALL cancelOrder(3);

SELECT * FROM orders;
SELECT * FROM rental_dates;
SELECT * FROM order_products;



DROP PROCEDURE checkAvailableProducts;

-- need to check if product is available before order is created
DELIMITER $$
CREATE PROCEDURE checkAvailableProducts(IN user_start_date DATE, IN user_end_date DATE)
BEGIN
 

 -- SELECT * 
 -- FROM taken_dates
 -- WHERE product_id NOT IN 
 -- (start_date <= user_end_date AND end_date >= user_start_date);
 
 

SELECT p.product_id, p.product_name, p.price, p.quantity - IFNULL(SUM(op.quantity_taken), 0) AS amount_available
FROM rental_products p LEFT JOIN order_products op ON p.product_id = op.product_id LEFT JOIN rental_dates d ON op.order_product_id = d.order_product_id
AND (d.start_date <= user_end_date AND d.end_date >= user_start_date)
GROUP BY p.product_id, p.product_name, p.quantity, p.price
HAVING amount_available > 0;


END $$
DELIMITER ;

SELECT * FROM rental_products;


UPDATE rental_products
SET quantity = quantity + 2
WHERE product_id = 1;

CALL checkAvailableProducts('2025-04-26','2025-04-30');

TRUNCATE ordrs;
TRUNCATE order_products;
TRUNCATE rental_dates;

SELECT * FROM rental_products;

 INSERT INTO orders(product_id, customer_id, order_date, rental_days_amount, order_total)
 VALUES (1, 1, '2025-04-26', 3, 400);
 
  INSERT INTO orders(product_id, customer_id, order_date, rental_days_amount, order_total)
 VALUES (2, 1, '2025-04-26', 3, 400);
 
INSERT INTO rental_dates(product_id, start_date, end_date)
VALUES (1, '2025-04-26','2025-04-30');
 
 SELECT * FROM orders;
 
 SELECT * FROM rental_dates;
 
 SELECT * FROM rental_products;
 
 INSERT INTO order_products(product_id, order_id, quantity_taken)
 VALUES (1, 2, 1);
 
 INSERT INTO order_products(product_id, order_id, quantity_taken)
 VALUES (2,3,1);
 
 SELECT * FROM order_products;
 
CALL checkAvailableProducts('2025-04-26','2025-04-30');

UPDATE rental_products
SET quantity = 2
WHERE product_id = 1;

INSERT INTO customers (first_name, last_name, phone_number, password)
VALUES('john','doe','1234','password');

SELECT * FROM customers;


INSERT INTO categories(category_type)
VALUES('wet/dry bounce house combo');

INSERT INTO categories(category_type)
VALUES('water slide');

INSERT INTO categories(category_type)
VALUES('slip n slide');

INSERT INTO categories(category_type)
VALUES('party tent');

INSERT INTO categories(category_type)
VALUES('tables chairs');

INSERT INTO categories(category_type)
VALUES('marquee');


SELECT * FROM categories;

SELECT * FROM rental_products;

-- bounce houses
INSERT INTO rental_products(category_id, product_name, dimensions, price, quantity)
VALUES(1, 'Mega Tropical', '15x7', 325.00, 1);

INSERT INTO rental_products(category_id, product_name, dimensions, price, quantity)
VALUES(1, 'Firehouse', '13x7', 325.00, 1);

INSERT INTO rental_products(category_id, product_name, dimensions, price, quantity)
VALUES(1, 'Mini Marble', '15x7', 275.00, 1);

INSERT INTO rental_products(category_id, product_name, dimensions, price, quantity)
VALUES(1, 'Pretty Pink', '10x6', 325.00, 1);

-- water slides
INSERT INTO rental_products(category_id, product_name, dimensions, price, quantity)
VALUES(2, 'Island Drop Single Lane WaterSlide', '25L x 8W X 15H', 350.00, 1);

INSERT INTO rental_products(category_id, product_name, dimensions, price, quantity)
VALUES(2, 'Tiki Shot Dual Lane Center Climb', '25L x 12W x 16H', 425.00, 1);

INSERT INTO rental_products(category_id, product_name, dimensions, price, quantity)
VALUES(2, 'Liberty Lane', '22L x 8W X 24H', 575.00, 1);

-- slip n slide
INSERT INTO rental_products(category_id, product_name, dimensions, price, quantity)
VALUES(3, 'Tropical Marble Dual Lane', '35L x 10W x 7H', 325.00, 1 );

-- party tent
INSERT INTO rental_products(category_id, product_name, dimensions, price, quantity)
VALUES(4, 'High Peak Frame Tent', '20L x 20W x 20H', 400.00, 1);

INSERT INTO rental_products(category_id, product_name, price, quantity)
VALUES(4, 'LED Bistro Lights', 50.00, 1);

-- tables chairs
INSERT INTO rental_products(category_id, product_name, dimensions, price, quantity)
VALUES(5, 'White Rectangle Folding Table', '6L', 12.00, 3);

INSERT INTO rental_products(category_id, product_name, dimensions, price, quantity)
VALUES(5, 'Round White Plastic Folding Table', '60in', 12.00, 6);

INSERT INTO rental_products(category_id, product_name, price, quantity)
VALUES(5, 'Kids White Folding Chair', 2.00, 24);

INSERT INTO rental_products(category_id, product_name, price, quantity)
VALUES(5, 'Kids White Folding Table', 10.00, 4);

INSERT INTO rental_products(category_id, product_name, price, quantity)
VALUES(5, 'White Folding Chair', 2.25, 45);

-- marquee
INSERT INTO rental_products(category_id, product_name, price)
VALUES(6, 'Marquee Letters', 50.00);

INSERT INTO rental_products(category_id, product_name, price)
VALUES(6, 'Marquee Numbers', 50.00);


SELECT * FROM rental_products;


