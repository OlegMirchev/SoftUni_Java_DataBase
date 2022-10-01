CREATE DATABASE `test`;
USE `test`;

CREATE TABLE `people`(
`person_id` INT AUTO_INCREMENT UNIQUE NOT NULL,
`first_name` VARCHAR(20) NOT NULL,
`salary` DECIMAL(10, 2),
`passport_id` INT UNIQUE NOT NULL
);

CREATE TABLE `passports`(
`passport_id` INT AUTO_INCREMENT UNIQUE NOT NULL,
`passport_number` VARCHAR(20) UNIQUE NOT NULL
) AUTO_INCREMENT = 101;

INSERT INTO `people`(`first_name`, `salary`, `passport_id`) VALUES
('Roberto', 43300, 102),
('Tom', 56100, 103),
('Yana', 60200, 101);

INSERT INTO `passports`(`passport_number`) VALUES
('N34FG21B'),
('K65LO4R7'),
('ZE657QP2');

ALTER TABLE `people`
ADD CONSTRAINT `pk_person` 
PRIMARY KEY(`person_id`),
ADD CONSTRAINT `fk_person_passport`
FOREIGN KEY (`passport_id`)
REFERENCES `passports`(`passport_id`);

CREATE TABLE `manufacturers`(
`manufacturer_id` INT AUTO_INCREMENT UNIQUE NOT NULL,
`name` VARCHAR(20) NOT NULL,
`established_on` DATE -- NOT NULL DEFAULT NOW()
);

CREATE TABLE `models`(
`model_id` INT AUTO_INCREMENT UNIQUE NOT NULL,
`name` VARCHAR(20) NOT NULL,
`manufacturer_id` INT NOT NULL
) AUTO_INCREMENT = 101;

INSERT INTO `manufacturers`(`name`, `established_on`) VALUES
('BMW', '1916-03-01'),
('Tesla', '2003-01-01'),
('Lada', '1966-05-01');

INSERT INTO `models`(`name`, `manufacturer_id`) VALUES
('X1', 1),
('i6', 1),
('Model S', 2),
('Model X', 2),
('Model 3', 2),
('Nova', 3);

ALTER TABLE `models`
ADD CONSTRAINT `pk_models`
PRIMARY KEY (`model_id`),
ADD CONSTRAINT `fk_manufacturers_models`
FOREIGN KEY (`manufacturer_id`)
REFERENCES `manufacturers`(`manufacturer_id`);

DROP TABLE `exams`;
DROP TABLE `students`;
DROP TABLE `students_exams`;

CREATE TABLE `exams`(
`exam_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(20) NOT NULL
) AUTO_INCREMENT = 101;

CREATE TABLE `students`(
`student_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(20) NOT NULL
);

CREATE TABLE `students_exams`(
`student_id` INT NOT NULL,
`exam_id` INT NOT NULL
);

INSERT INTO `exams` (`name`) VALUES
('Spring MVC'),
('Neo4j'),
('Oracle 11g');

INSERT INTO `students` (`name`) VALUES
('Mila'),
('Toni'),
('Ron');

INSERT INTO `students_exams`(`student_id`, `exam_id`) VALUES
(1, 101),
(1, 102),
(2, 101),
(3, 103),
(2, 102),
(2, 103);

ALTER TABLE `students_exams`
ADD CONSTRAINT `pk_exams_students`
PRIMARY KEY (`student_id`, `exam_id`),
ADD CONSTRAINT `fk_exams_students`
FOREIGN KEY (`exam_id`)
REFERENCES `exams`(`exam_id`),
ADD CONSTRAINT `fk_students_exams`
FOREIGN KEY (`student_id`)
REFERENCES `students`(`student_id`);

DROP TABLE `teachers`;
CREATE TABLE `teachers`(
`teacher_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(20) NOT NULL,
`manager_id` INT
) AUTO_INCREMENT = 101;

INSERT INTO `teachers` (`name`, `manager_id`) VALUES
('John', NULL),
('Maya', 106),
('Silvia', 106),
('Ted', 105),
('Mark', 101),
('Greta', 101);

ALTER TABLE `teachers`
ADD CONSTRAINT `fk_teachers`
FOREIGN KEY (`manager_id`)
REFERENCES `teachers`(`teacher_id`);

CREATE TABLE `cities`(
`city_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(50)
);

CREATE TABLE `customers`(
`customer_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(50),
`birthday` DATE,
`city_id` INT,
CONSTRAINT `fk_customers_cities`
FOREIGN KEY (`city_id`)
REFERENCES `cities`(`city_id`)
);

CREATE TABLE `orders`(
`order_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`customer_id` INT,
CONSTRAINT `fk_orders_customers`
FOREIGN KEY (`customer_id`)
REFERENCES `customers`(`customer_id`)
);

CREATE TABLE `item_types`(
`item_type_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(50)
);

CREATE TABLE `items`(
`item_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(50),
`item_type_id` INT,
CONSTRAINT `fk_items_item_types`
FOREIGN KEY (`item_type_id`)
REFERENCES `item_types`(`item_type_id`)
);

CREATE TABLE `order_items`(
`order_id` INT NOT NULL,
`item_id` INT NOT NULL,
CONSTRAINT `pk_orders_items`
PRIMARY KEY (`order_id`, `item_id`),
CONSTRAINT `fk_order_items_orders`
FOREIGN KEY (`order_id`)
REFERENCES `orders`(`order_id`),
CONSTRAINT `fk_orders_items`
FOREIGN KEY (`item_id`)
REFERENCES `items`(`item_id`)
);

USE `geography`;

SELECT m.mountain_range, p.peak_name, p.elevation AS 'peak_elevation' 
FROM `mountains` AS m
JOIN `peaks` AS p ON p.mountain_id = m.id
WHERE m.mountain_range LIKE 'Rila'
ORDER BY p.elevation DESC;
