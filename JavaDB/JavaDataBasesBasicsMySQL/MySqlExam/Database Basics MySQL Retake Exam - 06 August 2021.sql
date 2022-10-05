CREATE DATABASE `sgd`;

CREATE TABLE `addresses`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL
);

CREATE TABLE `offices`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`workspace_capacity` INT NOT NULL,
`website` VARCHAR(50),
`address_id` INT NOT NULL,
CONSTRAINT `fk_offices_addresses`
FOREIGN KEY (`address_id`)
REFERENCES `addresses`(`id`)
);

CREATE TABLE `employees`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(30) NOT NULL,
`last_name` VARCHAR(30) NOT NULL,
`age` INT NOT NULL,
`salary` DECIMAL(10, 2) NOT NULL,
`job_title` VARCHAR(20) NOT NULL,
`happiness_level` CHAR(1) NOT NULL
);

CREATE TABLE `teams`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL,
`office_id` INT NOT NULL,
`leader_id` INT UNIQUE NOT NULL,
CONSTRAINT `fk_teams_offices`
FOREIGN KEY (`office_id`)
REFERENCES `offices`(`id`),
CONSTRAINT `fk_teams_employees`
FOREIGN KEY (`leader_id`)
REFERENCES `employees`(`id`)
);

CREATE TABLE `games`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) UNIQUE NOT NULL,
`description` TEXT,
`rating` FLOAT NOT NULL DEFAULT 5.5,
`budget` DECIMAL(10, 2) NOT NULL,
`release_date` DATE,
`team_id` INT NOT NULL,
CONSTRAINT `fk_games_teams`
FOREIGN KEY (`team_id`)
REFERENCES `teams`(`id`)
);

CREATE TABLE `categories`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(10) NOT NULL
);

CREATE TABLE `games_categories`(
`game_id` INT NOT NULL,
`category_id` INT NOT NULL,
CONSTRAINT `pk_games_categories`
PRIMARY KEY (`game_id`, `category_id`),
CONSTRAINT `fk_games_categories_games`
FOREIGN KEY (`game_id`)
REFERENCES `games`(`id`),
CONSTRAINT `fk_games_categories_categories`
FOREIGN KEY (`category_id`)
REFERENCES `categories`(`id`)
);

INSERT INTO `games`(`name`, `rating`, `budget`, `team_id`)
SELECT reverse(lower(substring(t.name, 2, char_length(t.name)))),
t.id, t.leader_id * 1000, t.id
FROM `teams` AS t
WHERE t.id BETWEEN 1 AND 9;

UPDATE `employees` AS e
SET e.salary = e.salary + 1000
WHERE e.age <= 40 AND e.salary <= 5000;

DELETE g FROM `games` AS g
LEFT JOIN `games_categories` AS gc ON gc.game_id = g.id
LEFT JOIN `categories` AS c ON c.id = gc.category_id
WHERE g.release_date IS NULL AND gc.category_id IS NULL;

SELECT e.first_name, e.last_name, e.age, e.salary, e.happiness_level
FROM `employees` AS e
ORDER BY e.salary ASC, e.id ASC;

SELECT t.name AS 'team_name', a.name AS 'address_name', 
char_length(a.name) AS 'count_of_characters' 
FROM `teams` AS t
LEFT JOIN `offices` AS o ON o.id = t.office_id
LEFT JOIN `addresses` AS a ON a.id = o.address_id
WHERE o.website IS NOT NULL
ORDER BY t.name, a.name ASC;

SELECT c.name, COUNT(*) AS 'games_count', round(AVG(g.budget), 2) AS 'avg_budget', 
MAX(g.rating) AS 'max_rating'
FROM `categories` AS c
JOIN `games_categories` AS gc ON gc.category_id = c.id
JOIN `games` AS g ON g.id = gc.game_id
GROUP BY c.id
HAVING `max_rating` >= 9.5
ORDER BY `games_count` DESC, c.name ASC;

SELECT g.name, g.release_date, concat(substring(g.description, 1, 10), '...') AS 'summary',
CASE
   WHEN MONTH(g.release_date) = 2 THEN 'Q1'
   WHEN MONTH(g.release_date) IN(4,6) THEN 'Q2'
   WHEN MONTH(g.release_date) = 8 THEN 'Q3'
WHEN MONTH(g.release_date) IN(10,12) THEN 'Q4'
END AS 'quarter', t.name AS 'team_name'
FROM `games` AS g
JOIN `teams` AS t ON t.id = g.team_id
WHERE YEAR(g.release_date) = 2022 AND MONTH(g.release_date) % 2 = 0
AND RIGHT(g.name, 1) = 2
ORDER BY `quarter` ASC;

SELECT g.name, 
IF(g.budget < 50000, 'Normal budget', 'Insufficient budget') AS 'budget_level',
t.name AS 'team_name', a.name AS 'address_name'
FROM `games` AS g
LEFT JOIN `games_categories` AS gc ON gc.game_id = g.id
LEFT JOIN `categories` AS c ON c.id = gc.category_id
LEFT JOIN `teams` AS t ON g.team_id = t.id
LEFT JOIN `offices` AS o ON o.id = t.office_id
LEFT JOIN `addresses` AS a ON a.id = o.address_id
WHERE g.release_date IS NULL AND c.name IS NULL
ORDER BY g.name ASC;

DELIMITER \\
CREATE FUNCTION udf_game_info_by_name(game_name VARCHAR(20))
RETURNS TEXT
DETERMINISTIC
BEGIN
RETURN (SELECT 
concat_ws(' ', 'The', g.name, 'is developed by a', 
t.name, 'in an office with an address', a.name) AS 'info'
FROM `games` AS g
JOIN `teams` AS t ON g.team_id = t.id
JOIN `offices` AS o ON o.id = t.office_id
JOIN `addresses` AS a ON a.id = o.address_id
WHERE g.name LIKE game_name);
END
\\

DELIMITER \\
CREATE PROCEDURE udp_update_budget(min_game_rating FLOAT)
BEGIN
UPDATE `games` AS g
LEFT JOIN `games_categories` AS gc ON gc.game_id = g.id
LEFT JOIN `categories` AS c ON c.id = gc.category_id
SET g.budget = g.budget + 100000, g.release_date = g.release_date + 1
WHERE c.name IS NULL AND g.rating > min_game_rating AND g.release_date IS NOT NULL;
END
\\