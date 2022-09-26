CREATE TABLE `minions`(
`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(20),
`age` INT
);

CREATE TABLE `towns`(
`town_id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(20)
);

ALTER TABLE `minions`
ADD COLUMN `town_id` INT,
ADD CONSTRAINT `fk_mini`
FOREIGN KEY `minions` (`town_id`)
REFERENCES `towns` (`id`);

INSERT INTO `towns`(`id`, `name`)
VALUES('1', 'Sofia');
INSERT INTO `towns`(`id`, `name`)
VALUES('2', 'Plovdiv');
INSERT INTO `towns`(`id`, `name`)
VALUES('3', 'Varna');

INSERT INTO `minions`(`id`, `name`, `age`, `town_id`)
VALUES('1', 'Kevin', '22', '1');
INSERT INTO `minions`(`id`, `name`, `age`, `town_id`)
VALUES('2', 'Bob', '15', '3');
INSERT INTO `minions`(`id`, `name`, `age`, `town_id`)
VALUES('3', 'Steward', NULL, '2');

TRUNCATE TABLE `minions`;

DROP TABLE `minions`;
DROP TABLE `towns`;

CREATE TABLE `people`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(200) NOT NULL,
`picture` INT(2),
`height` FLOAT(2),
`weight` FLOAT(2),
`gender` VARCHAR(1) NOT NULL,
`birthdate` DATE NOT NULL,
`biography` TEXT
);

INSERT INTO `people`(`id`, `name`, `picture`, `height`, `weight`, `gender`, `birthdate`, `biography`)
VALUES('1', 'Kevin', '1', '1.73', '2.53', 'm', '2000-04-20', 'test');
INSERT INTO `people`(`id`, `name`, `picture`, `height`, `weight`, `gender`, `birthdate`, `biography`)
VALUES('2', 'Bob', '1', '1.73', '2.53', 'm', '2000-04-20', 'test');
INSERT INTO `people`(`id`, `name`, `picture`, `height`, `weight`, `gender`, `birthdate`, `biography`)
VALUES('3', 'Pesho', '1', '1.73', '2.53', 'm', '2000-04-20', 'test');
INSERT INTO `people`(`id`, `name`, `picture`, `height`, `weight`, `gender`, `birthdate`, `biography`)
VALUES('4', 'Gosho', '1', '1.73', '2.53', 'm', '2000-04-20', 'test');
INSERT INTO `people`(`id`, `name`, `picture`, `height`, `weight`, `gender`, `birthdate`, `biography`)
VALUES('5', 'Sasho', '1', '1.73', '2.53', 'm', '2000-04-20', 'test');

CREATE TABLE `users`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`username` VARCHAR(30) NOT NULL,
`password` INT(26),
`profile_picture` BLOB,
`last_login_time` TIME,
`is_deleted` BOOLEAN
);

INSERT INTO `users`(`id`, `username`, `password`, `profile_picture`, `last_login_time`, `is_deleted`)
VALUES('1', 'Kevin', '1234', '0.800', '01:01:01', FALSE);
INSERT INTO `users`(`id`, `username`, `password`, `profile_picture`, `last_login_time`, `is_deleted`)
VALUES('2', 'Bob', '1234', '0.800', '01:01:01', FALSE);
INSERT INTO `users`(`id`, `username`, `password`, `profile_picture`, `last_login_time`, `is_deleted`)
VALUES('3', 'Pesho', '1234', '0.800', '01:01:01', FALSE);
INSERT INTO `users`(`id`, `username`, `password`, `profile_picture`, `last_login_time`, `is_deleted`)
VALUES('4', 'Gosho', '1234', '0.800', '01:01:01', FALSE);
INSERT INTO `users`(`id`, `username`, `password`, `profile_picture`, `last_login_time`, `is_deleted`)
VALUES('5', 'Sasho', '1234', '0.800', '01:01:01', FALSE);

ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT `pk_users`
PRIMARY KEY `users`(`id`, `username`);

ALTER TABLE `users`
CHANGE COLUMN `last_login_time` `last_login_time` DATETIME
DEFAULT NOW();

ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT `pk_users`
PRIMARY KEY `users`(`id`),
CHANGE COLUMN `username` `username` VARCHAR(30) UNIQUE;