DROP DATABASE IF EXISTS bounce_house_db;
CREATE DATABASE bounce_house_db;
USE bounce_house_db;


CREATE TABLE admin
(
	admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
	password VARCHAR(255)
);

INSERT INTO admin(username, password) VALUES('secretuser','secretpassword');



CREATE TABLE customers
(
	customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(50) NOT NULL
);


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
    category_id INT NOT NULL,
    product_name VARCHAR(50) NOT NULL,
    dimensions VARCHAR(50),
    price INT NOT NULL,
    quantity INT,
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

CREATE TABLE orders
(
	order_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    customer_id INT NOT NULL,
    order_date DATETIME NOT NULL,
    rental_days_amount INT NOT NULL DEFAULT 1,
    order_total INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES rental_products(product_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);




CREATE TABLE order_products
(
	order_product_id INT AUTO_INCREMENT PRIMARY KEY,
	product_id INT NOT NULL,
    order_id INT NOT NULL,
    quantity_taken INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES rental_products(product_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);


CREATE TABLE product_images
(
	product_id INT NOT NULL,
    image_url VARCHAR (255),
    FOREIGN KEY (product_id) REFERENCES rental_products(product_id)
);


CREATE TABLE rental_reviews
(
	review_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    customer_id INT NOT NULL,
    product_id INT NOT NULL,
    review_text VARCHAR(50),
    rating INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (product_id) REFERENCES rental_products(product_id)
);

CREATE TABLE payments
(
	payment_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    card_type VARCHAR(20) NOT NULL,
    card_number VARCHAR(20) NOT NULL,
    card_expires VARCHAR(50) NOT NULL,
    billing_address VARCHAR(50) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);



CREATE TABLE rental_dates
(
	rent_date_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    order_product_id INT,
    order_id INT,
    FOREIGN KEY (order_product_id) REFERENCES order_products(order_product_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
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
    street VARCHAR(100) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);



CREATE VIEW upcoming_orders AS 
SELECT o.order_date, d.start_date,
p.product_id, p.product_name, op.quantity_taken, o.order_total
FROM rental_products p JOIN order_products op ON p.product_id = op.product_id JOIN rental_dates d ON d.order_product_id = op.order_product_id 
JOIN orders o ON o.order_id = d.order_id
WHERE d.start_date >= current_date();



CREATE VIEW customer_info AS
SELECT o.order_id, a.street, a.city, a.state, a.zip_code, c.first_name, c.last_name, c.phone_number, d.start_date
FROM rental_dates d JOIN rental_products p ON d.product_id = p.product_id JOIN order_products op ON p.product_id = op.product_id JOIN orders o ON op.order_id = o.order_id JOIN customers c ON c.customer_id = o.order_id
JOIN rental_addresses a ON a.customer_id = c.customer_id
WHERE d.start_date >= current_date();




-- CREATE VIEW taken_dates AS 
-- SELECT p.product_id, product_name, start_date, end_date, quantity AS amount_available 
-- FROM rental_dates d JOIN rental_products p ON d.product_id = p.product_id;



CREATE VIEW product_ratings AS
SELECT r.review_text, AVG(rating) AS average_rating, p.product_id, product_name FROM rental_reviews r JOIN rental_products p ON r.product_id = p.product_id
GROUP BY r.review_text, p.product_name, r.product_id;

-- triggers

DELIMITER $$

CREATE TRIGGER order_after_insert AFTER INSERT ON orders FOR EACH ROW
BEGIN
INSERT INTO order_audit(order_id, product_id, order_total, action_type, action_date)
VALUES (NEW.order_id, NEW.product_id, NEW.order_total, 'INSERTED',NOW());

END $$
DELIMITER ;

DELIMITER $$



CREATE TRIGGER order_after_delete AFTER DELETE ON orders FOR EACH ROW
BEGIN
INSERT INTO order_audit(order_id, product_id, order_total, action_type, action_date)
VALUES (OLD.order_id, OLD.product_id, OLD.order_total, 'DELETED', NOW());

END $$
DELIMITER ;

-- trigger after insert capitalize address states
-- 


DROP PROCEDURE IF EXISTS createOrder;

DELIMITER $$
CREATE PROCEDURE createOrder(IN in_customer_id INT, IN in_product_id INT, IN in_start_date DATE, IN in_end_date DATE, IN amount_chosen INT, OUT out_order_id INT)
BEGIN
DECLARE total_price DECIMAL(10,2);
DECLARE rental_days INT;
DECLARE unit_price DECIMAL(10,2);
DECLARE new_order_id INT;
DECLARE new_order_product_id INT;


SET rental_days = DATEDIFF(in_end_date, in_start_date);



SELECT price INTO unit_price FROM rental_products WHERE product_id = in_product_id;

SET total_price = unit_price * amount_chosen;

-- make the order
INSERT INTO orders(customer_id, product_id, order_date, rental_days_amount, order_total)
VALUES(in_customer_id, in_product_id, current_date(), rental_days, total_price);


SET new_order_id = last_insert_id();

INSERT INTO order_products(product_id, order_id, quantity_taken)
VALUES(in_product_id, new_order_id, amount_chosen);

SET new_order_product_id = last_insert_id();

INSERT INTO rental_dates(product_id, start_date, end_date, order_product_id, order_id)
VALUES(in_product_id, in_start_date, in_end_date, new_order_product_id, new_order_id);

SET out_order_id = new_order_id;

END $$
DELIMITER ;



DELIMITER $$
CREATE PROCEDURE cancelOrder(IN i_order_id INT)
BEGIN

DELETE FROM rental_dates WHERE order_id = i_order_id;

DELETE FROM order_products WHERE order_id = i_order_id;

DELETE FROM orders WHERE order_id = i_order_id;

DELETE FROM payments WHERE order_id = i_order_id;



END $$
DELIMITER ;



-- need to check if product is available before order is created
DELIMITER $$
CREATE PROCEDURE checkAvailableProducts(IN user_start_date DATE, IN user_end_date DATE)
BEGIN


-- SELECT p.product_id, p.category_id, p.product_name, p.dimensions, p.price, p.quantity - IFNULL(SUM(op.quantity_taken), 0) AS amount_available, pi.image_url
-- FROM rental_products p LEFT JOIN product_images pi ON p.product_id = pi.product_id LEFT JOIN order_products op ON p.product_id = op.product_id LEFT JOIN rental_dates d ON op.order_product_id = d.order_product_id
-- AND (d.start_date <= user_end_date AND d.end_date >= user_start_date)
-- GROUP BY p.product_id, p.category_id, p.product_name, p.dimensions, p.quantity, p.price, pi.image_url
-- HAVING amount_available > 0;

-- if a rental date overlaps with users date, 
-- then subtract the total quantity with the amount taken for that date, 
-- otherwise dont subtract anything. 
-- if quantity taken is null, subtract with zero

SELECT 
        p.product_id, p.category_id, p.product_name, p.dimensions, p.price, p.quantity - IFNULL(SUM( IF (d.start_date <= user_end_date AND d.end_date >= user_start_date, op.quantity_taken, 0)),   0) AS amount_available, pi.image_url
    FROM rental_products p
    LEFT JOIN product_images pi ON p.product_id = pi.product_id
    LEFT JOIN order_products op ON p.product_id = op.product_id
    LEFT JOIN rental_dates d ON op.order_product_id = d.order_product_id
    GROUP BY p.product_id, p.category_id, p.product_name, p.dimensions, p.price, p.quantity, pi.image_url
    HAVING amount_available > 0;

END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE addAddress(IN i_customer_id INT, IN i_street VARCHAR(100), IN i_city VARCHAR(100), IN i_state VARCHAR(2), IN i_zip_code VARCHAR(10))
BEGIN

INSERT INTO rental_addresses(customer_id, street, city, state, zip_code)
VALUES (i_customer_id, i_street, i_city, i_state, i_zip_code);


END $$
DELIMITER ;



-- categories
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

-- PRODUCTS

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

-- tables and chairs
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

-- IMAGES
SELECT product_id, product_name FROM rental_products;
-- Combos
INSERT INTO product_images (product_id, image_url) VALUES (4, 'images/combos/pretty_pink.png');
INSERT INTO product_images (product_id, image_url) VALUES (1, 'images/combos/mega_tropical.png');
INSERT INTO product_images (product_id, image_url) VALUES (2, 'images/combos/firehouse.png');
INSERT INTO product_images (product_id, image_url) VALUES (3, 'images/combos/mini_marble.png');

-- Water Slides
INSERT INTO product_images (product_id, image_url) VALUES (5, 'images/water slides/island_drop_single_lane.png');
INSERT INTO product_images (product_id, image_url) VALUES (6, 'images/water slides/tiki_shot_dual_lane.png');
INSERT INTO product_images (product_id, image_url) VALUES (7, 'images/water slides/liberty_lane.png');

-- Slip n Slide
INSERT INTO product_images (product_id, image_url) VALUES (8, 'images/slip n slide/marble_slide.png');

-- Tent Stuff
INSERT INTO product_images (product_id, image_url) VALUES (9, 'images/tent stuff/1729227463928_20x20 HighPeakTent.png');
INSERT INTO product_images (product_id, image_url) VALUES (10, 'images/tent stuff/1729228275915_LEDBistroLights.png');

-- Tables & Chairs
INSERT INTO product_images (product_id, image_url) VALUES (11, 'images/tables and chairs/white_rectangle_table.png');
INSERT INTO product_images (product_id, image_url) VALUES (12, 'images/tables and chairs/white_round_table.png');
INSERT INTO product_images (product_id, image_url) VALUES (13, 'images/tables and chairs/kids_chair.png');
INSERT INTO product_images (product_id, image_url) VALUES (14, 'images/tables and chairs/kids_table.png');
INSERT INTO product_images (product_id, image_url) VALUES (15, 'images/tables_chairs/white_chair.png');

-- Marquee
INSERT INTO product_images (product_id, image_url) VALUES (16, 'images/marquee/marquee_letters.png');
INSERT INTO product_images (product_id, image_url) VALUES (17, 'images/marquee/marquee_numbers.png');


