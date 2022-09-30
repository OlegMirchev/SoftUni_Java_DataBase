USE `soft_uni`;

SELECT * FROM `departments`
ORDER BY `department_id`;

SELECT `name` FROM `departments`
ORDER BY `department_id`;

SELECT `first_name`, `last_name`, `salary` FROM `employees`
ORDER BY `employee_id`;

SELECT `first_name`, `middle_name`, `last_name` FROM `employees`
ORDER BY `employee_id`;

SELECT concat(`first_name`, '.', `last_name`, '@softuni.bg') 
AS `full_ email_address` FROM `employees`;

SELECT DISTINCT `salary` FROM `employees`
ORDER BY `salary` ASC;

SELECT * FROM `employees` 
WHERE `job_title` = 'Sales Representative'
ORDER BY `employee_id`;

SELECT `first_name`, `last_name`, `job_title` FROM `employees`
AS e
WHERE e.salary BETWEEN 20000 AND 30000
ORDER BY `employee_id`;

SELECT concat_ws(' ', `first_name`, `middle_name`, `last_name`) 
AS `Full Name`
FROM `employees`
WHERE salary = 25000 OR salary = 14000 OR salary = 12500
OR salary = 23600;

SELECT `first_name`, `last_name` FROM `employees` AS e
WHERE e.manager_id is NULL;

SELECT `first_name`, `last_name` FROM `employees` AS e
ORDER BY e.salary DESC LIMIT 5;

SELECT `first_name`, `last_name` FROM `employees` AS e
WHERE e.department_id != 4;

SELECT * FROM `employees` AS e
ORDER BY e.salary DESC, e.first_name ASC, 
e.last_name DESC, e.middle_name ASC, e.employee_id;

CREATE VIEW `v_employees_salaries` 
AS SELECT `first_name`, `last_name`, `salary` FROM `employees`;

CREATE VIEW `v_employees_job_titles` 
AS SELECT concat(`first_name`, ' ', IFNULL(concat(`middle_name`, ' '),''), `last_name`) AS `full_name`, `job_title`
FROM `employees`;

SELECT DISTINCT `job_title` FROM `employees` AS e
ORDER BY e.job_title ASC;

SELECT * FROM `projects` AS p
ORDER BY p.start_date, p.name, p.project_id LIMIT 10;

SELECT `first_name`, `last_name`, `hire_date` FROM `employees` AS e
ORDER BY e.hire_date DESC LIMIT 7;

UPDATE `employees` AS e
SET e.salary = e.salary * 1.12
WHERE e.department_id IN(1, 2, 4, 11);

SELECT `salary` FROM `employees`;

DELETE FROM `employees` AS e
WHERE e.employee_id IN(1,2);

SELECT `employee_id` FROM `employees`;

DROP DATABASE `soft_uni`;