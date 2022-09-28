USE `soft_uni`;

SELECT `first_name`, `last_name` FROM `employees` AS e
WHERE e.first_name LIKE 'Sa%'
ORDER BY e.employee_id;

SELECT `first_name`, `last_name` FROM `employees` AS e
WHERE e.last_name LIKE '%ei%'
ORDER BY e.employee_id;

SELECT `first_name` FROM `employees` AS e
WHERE e.department_id IN(3, 10) AND
year(e.hire_date) BETWEEN 1995 AND 2005
ORDER BY e.employee_id;

SELECT `first_name`, `last_name` FROM `employees` AS e
WHERE e.job_title NOT LIKE '%engineer%'
ORDER BY e.employee_id;

SELECT `name` FROM `towns` AS e
WHERE char_length(e.name) IN(5,6)
ORDER BY e.name ASC;

SELECT `town_id`, `name` FROM `towns` AS e
WHERE lower(left(e.name, 1) IN('m', 'b', 'k', 'e'))
ORDER BY e.name ASC;

SELECT `town_id`, `name` FROM `towns` AS e
WHERE upper(substring(e.name, 1, 1) NOT IN('R', 'B', 'D'))
ORDER BY e.name ASC;

CREATE VIEW `v_employees_hired_after_2000`
AS SELECT `first_name`, `last_name` FROM `employees` AS e
WHERE year(e.hire_date) > 2000;

SELECT * FROM `v_employees_hired_after_2000`;

SELECT `first_name`, `last_name` FROM `employees` AS e
WHERE char_length(e.last_name) = 5;

USE `geography`;

SELECT `country_name`, `iso_code` FROM `countries` AS c
WHERE c.country_name LIKE '%A%A%A%'
ORDER BY c.iso_code;
-- Two variant: 
SELECT `country_name`, `iso_code` FROM `countries` AS c
WHERE (char_length(c.country_name) -
char_length(replace(lower(c.country_name), 'a', ''))) >= 3
ORDER BY c.iso_code;

SELECT `peak_name`, `river_name`,
lower(concat(left(p.peak_name, char_length(p.peak_name) - 1), r.river_name)) AS `mix`
FROM `peaks` AS p, `rivers` AS r
WHERE lower(right(p.peak_name, 1)) = lower(left(r.river_name, 1)) 
ORDER BY `mix` ASC;

USE `diablo`;

SELECT `name`, substring(`start`, 1, 10) AS `start` FROM `games` AS g
WHERE year(g.start) IN(2011, 2012)
ORDER BY g.start, g.name LIMIT 50;

SELECT `user_name`, `ip_address` FROM `users` AS u
WHERE u.ip_address LIKE '___.1%.%.___'
ORDER BY u.user_name ASC;

SELECT `user_name`, 
substring_index(`email`, '@', -1) AS 'email provider'
FROM `users` AS u
-- WHERE right(`email`, locate(`email`, '@'))
-- WHERE substring(`email`, locate(`email`, '@'), char_length(`email`) - locate(`email`, '@'))
ORDER BY `email provider` ASC, u.user_name;
-- Two variant:
SELECT `user_name`, REGEXP_REPLACE(`email`, '.*@', '') AS 'email provider'
FROM `users` AS u
ORDER BY `email provider` ASC, u.user_name;

SELECT `name` AS 'game',
CASE
   WHEN hour(g.start) >= 0 AND hour(g.start) < 12 THEN 'Morning'
   WHEN hour(g.start) >= 12 AND hour(g.start) < 18 THEN 'Afternoon'
   ELSE 'Evening'
END AS 'Part of the Day',
CASE
   WHEN g.duration <= 3 THEN 'Extra Short'
   WHEN g.duration BETWEEN 3 AND 6 THEN 'Short'
   WHEN g.duration BETWEEN 6 AND 10 THEN 'Long'
   ELSE 'Extra Long'
END AS 'Duration'
FROM `games` AS g;

USE `orders`;

SELECT `product_name`, `order_date`, 
adddate(o.order_date, INTERVAL 3 DAY) AS 'pay_due',
adddate(o.order_date, INTERVAL 1 MONTH) AS 'deliver_due'
FROM `orders` AS o;

