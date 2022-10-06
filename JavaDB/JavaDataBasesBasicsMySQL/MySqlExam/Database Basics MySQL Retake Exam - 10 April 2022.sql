CREATE DATABASE `softuni_imdb`;

CREATE TABLE `countries`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) UNIQUE NOT NULL,
`continent` VARCHAR(30) NOT NULL,
`currency` VARCHAR(5) NOT NULL
);

CREATE TABLE `actors`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(50) NOT NULL,
`last_name` VARCHAR(50) NOT NULL,
`birthdate` DATE NOT NULL,
`height` INT,
`awards` INT,
`country_id` INT NOT NULL,
CONSTRAINT `fk_actors_countries`
FOREIGN KEY (`country_id`)
REFERENCES `countries`(`id`)
);

CREATE TABLE `movies_additional_info`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`rating` DECIMAL(10, 2) NOT NULL,
`runtime` INT NOT NULL,
`picture_url` VARCHAR(80) NOT NULL,
`budget` DECIMAL(10, 2),
`release_date` DATE NOT NULL,
`has_subtitles` TINYINT(1),
`description` TEXT
);

CREATE TABLE `movies`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`title` VARCHAR(70) UNIQUE NOT NULL,
`country_id` INT NOT NULL,
`movie_info_id` INT UNIQUE NOT NULL,
CONSTRAINT `fk_country_countries`
FOREIGN KEY (`country_id`)
REFERENCES `countries`(`id`),
CONSTRAINT `fk_movie_info_movies`
FOREIGN KEY (`movie_info_id`)
REFERENCES `movies_additional_info`(`id`)
);

CREATE TABLE `movies_actors`(
`movie_id` INT,
`actor_id` INT,
CONSTRAINT `fk_movies_actors_movies`
FOREIGN KEY (`movie_id`)
REFERENCES `movies`(`id`),
CONSTRAINT `fk_movies_actors_actors`
FOREIGN KEY (`actor_id`)
REFERENCES `actors`(`id`)
);


CREATE TABLE `genres`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE `genres_movies`(
`genre_id` INT,
`movie_id` INT,
CONSTRAINT `fk_genre_genres`
FOREIGN KEY (`genre_id`)
REFERENCES `genres`(`id`),
CONSTRAINT `fk_movie_movies`
FOREIGN KEY (`movie_id`)
REFERENCES `movies`(`id`)
);

INSERT INTO `actors`
(`first_name`, `last_name`, `birthdate`, `height`, `awards`, `country_id`)
SELECT reverse(a.first_name), reverse(a.last_name), 
date_sub(a.birthdate, INTERVAL 2 DAY), a.height + 10,
a.country_id, (SELECT c.id FROM `countries` AS c WHERE c.id = 3)
FROM `actors` AS a
WHERE a.id <= 10;

UPDATE `movies_additional_info` AS mai
SET mai.runtime = mai.runtime - 10
WHERE mai.id >= 15 AND mai.id <= 25;

DELETE FROM `countries` AS c
WHERE c.id > 50;
-- Two variant:
-- DELETE c
-- FROM `countries` AS c
-- LEFT JOIN `movies` AS m ON c.id = m.country_id
-- WHERE m.country_id IS NULL;

SELECT * FROM `countries` AS c
ORDER BY c.currency DESC, c.id ASC;

SELECT mai.id, m.title, mai.runtime, mai.budget, mai.release_date 
FROM `movies_additional_info` AS mai
JOIN `movies` AS m ON mai.id = m.movie_info_id
WHERE YEAR(mai.release_date) >= 1996 AND YEAR(mai.release_date) <= 1999
ORDER BY mai.runtime ASC, mai.id ASC LIMIT 20;

SELECT concat_ws(' ', a.first_name, a.last_name) AS 'full_name',
concat(reverse(a.last_name), char_length(a.last_name), '@cast.com') AS 'email',
2022 - YEAR(a.birthdate) AS 'age', a.height 
FROM `actors` AS a
LEFT JOIN `movies_actors` AS ma ON ma.actor_id = a.id
LEFT JOIN `movies` AS m ON m.id = ma.movie_id
WHERE m.title IS NULL
ORDER BY a.height ASC;

SELECT c.name, COUNT(m.country_id) AS 'movies_count' FROM `countries` AS c
JOIN `movies` AS m ON c.id = m.country_id
GROUP BY c.id
HAVING `movies_count` >= 7
ORDER BY c.name DESC;

SELECT m.title,
CASE 
  WHEN mai.rating <= 4 THEN 'poor'
  WHEN mai.rating > 4 AND mai.rating <= 7 THEN 'good'
ELSE 'excellent'
END AS 'rating',
IF(mai.has_subtitles = 1, 'english', '-') AS 'subtitles', mai.budget
FROM `movies` AS m
JOIN `movies_additional_info` AS mai ON mai.id = m.movie_info_id
ORDER BY mai.budget DESC;

DELIMITER \\
CREATE FUNCTION udf_actor_history_movies_count(full_name VARCHAR(50))
RETURNS INT
DETERMINISTIC
BEGIN
RETURN (SELECT COUNT(a.id) AS 'history_movies' FROM `actors` AS a
JOIN `movies_actors` AS ma ON ma.actor_id = a.id
JOIN `movies` AS m ON m.id = ma.movie_id
JOIN `genres_movies` AS gm ON gm.movie_id = m.id
JOIN `genres` AS g ON g.id = gm.genre_id
WHERE concat(a.first_name, ' ', a.last_name) LIKE full_name
AND g.name LIKE 'History'
GROUP BY a.id);
END
\\

DELIMITER \\
CREATE PROCEDURE udp_award_movie(movie_title VARCHAR(50))
BEGIN
SELECT concat(a.first_name, ' ', a.last_name) AS 'full_name',
a.awards, '->', a.awards + 1 AS 'awards'
FROM `movies` AS m
JOIN `movies_actors` AS ma ON ma.movie_id = m.id
JOIN `actors` AS a ON a.id = ma.actor_id
WHERE m.title LIKE movie_title
GROUP BY `full_name`
ORDER BY a.id ASC;
END
\\