CREATE DATABASE `online_store`;

CREATE TABLE `customers`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(20) NOT NULL,
`last_name` VARCHAR(20) NOT NULL,
`phone` VARCHAR(30) UNIQUE NOT NULL,
`address` VARCHAR(60) NOT NULL,
`discount_card` BIT(1) NOT NULL DEFAULT FALSE
);

CREATE TABLE `orders`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`order_datetime` DATETIME NOT NULL,
`customer_id` INT NOT NULL,
CONSTRAINT `fk_orders_customers`
FOREIGN KEY (`customer_id`)
REFERENCES `customers`(`id`)
);

CREATE TABLE `brands`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) UNIQUE NOT NULL
);

CREATE TABLE `categories`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) UNIQUE NOT NULL
);

CREATE TABLE `reviews`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`content` TEXT,
`rating` DECIMAL(10,2) NOT NULL,
`picture_url` VARCHAR(80) NOT NULL,
`published_at` DATETIME NOT NULL
);

CREATE TABLE `products`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL,
`price` DECIMAL(19,2) NOT NULL,
`quantity_in_stock` INT,
`description` TEXT,
`brand_id` INT NOT NULL,
`category_id` INT NOT NULL,
`review_id` INT,
CONSTRAINT `fk_products_brands`
FOREIGN KEY (`brand_id`)
REFERENCES `brands`(`id`),
CONSTRAINT `fk_products_categories`
FOREIGN KEY (`category_id`)
REFERENCES `categories`(`id`),
CONSTRAINT `fk_products_reviews`
FOREIGN KEY (`review_id`)
REFERENCES `reviews`(`id`)
);

CREATE TABLE `orders_products`(
`order_id` INT,
`product_id` INT,
CONSTRAINT `fk_orders_products_orders`
FOREIGN KEY (`order_id`)
REFERENCES `orders`(`id`),
CONSTRAINT `fk_orders_products_products`
FOREIGN KEY (`product_id`)
REFERENCES `products`(`id`)
);

INSERT INTO `reviews`(`content`, `picture_url`, `published_at`, `rating`)
SELECT LEFT(p.description, 15), reverse(p.name),
'2010-10-10', p.price / 8
FROM `products` AS p
WHERE p.id >= 5;

UPDATE `products` AS p
SET p.quantity_in_stock = p.quantity_in_stock - 5
WHERE p.quantity_in_stock >= 60 AND p.quantity_in_stock <= 70;

DELETE c FROM `customers` AS c
LEFT JOIN `orders` AS o ON o.customer_id = c.id
WHERE o.customer_id IS NULL;

SELECT c.id, c.name FROM `categories` AS c
ORDER BY c.name DESC;

SELECT p.id, p.brand_id, p.name, p.quantity_in_stock
FROM `products` AS p
WHERE p.price > 1000 AND p.quantity_in_stock < 30
ORDER BY p.quantity_in_stock ASC, p.id ASC;

SELECT * FROM `reviews` AS r
WHERE r.content LIKE 'My%' AND char_length(r.content) > 61
ORDER BY r.rating DESC;

SELECT concat_ws(' ', c.first_name, c.last_name) AS 'full_name',
c.address, o.order_datetime
FROM `customers` AS c
JOIN `orders` AS o ON o.customer_id = c.id
WHERE YEAR(o.order_datetime) <= 2018
ORDER BY `full_name` DESC;

SELECT COUNT(p.category_id) AS 'items_count',
c.name, SUM(p.quantity_in_stock) AS 'total_quantity'
FROM `categories` AS c
JOIN `products` AS p ON p.category_id = c.id 
GROUP BY p.category_id
ORDER BY `items_count` DESC, `total_quantity` ASC LIMIT 5;

DELIMITER \\
CREATE FUNCTION udf_customer_products_count(`name` VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
RETURN (SELECT COUNT(c.id) AS 'total_products'
FROM `customers` AS c
JOIN `orders` AS o ON o.customer_id = c.id
JOIN `orders_products` AS op ON op.order_id = o.id
JOIN `products` AS p ON p.id = op.product_id
WHERE c.first_name LIKE `name`
GROUP BY c.id);
END
\\

DELIMITER \\
CREATE PROCEDURE udp_reduce_price(category_name VARCHAR(50))
BEGIN
UPDATE `products` AS p
JOIN `categories` AS c ON c.id = p.category_id
JOIN `reviews` AS r ON r.id = p.review_id
SET p.price = p.price * 0.70
WHERE r.rating < 4 AND c.name LIKE category_name;
END
\\