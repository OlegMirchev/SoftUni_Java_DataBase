USE `soft_uni`;

SELECT e.employee_id, e.job_title, a.address_id, a.address_text 
FROM `employees` AS e
JOIN `addresses` AS a ON a.address_id = e.address_id
ORDER BY a.address_id LIMIT 5;

SELECT e.first_name, e.last_name, t.name AS 'town', a.address_text 
FROM `employees` AS e
JOIN `addresses` AS a ON a.address_id = e.address_id
JOIN `towns` AS t ON t.town_id = a.town_id
ORDER BY e.first_name, e.last_name LIMIT 5;

SELECT e.employee_id, e.first_name, e.last_name, d.name AS 'department_name' 
FROM `employees` AS e
JOIN `departments` AS d ON d.department_id = e.department_id
WHERE d.name LIKE 'Sales'
ORDER BY e.employee_id DESC;

SELECT e.employee_id, e.first_name, e.salary, d.name AS 'department_name' 
FROM `employees` AS e
JOIN `departments` AS d ON d.department_id = e.department_id
WHERE e.salary > 15000
ORDER BY d.department_id DESC LIMIT 5;

SELECT e.employee_id, e.first_name
FROM `employees` AS e
LEFT JOIN `employees_projects` AS ep ON ep.employee_id = e.employee_id
WHERE ep.project_id IS NULL
ORDER BY e.employee_id DESC LIMIT 3;
-- Two variant:
-- SELECT e.employee_id, e.first_name
-- FROM `employees` AS e
-- LEFT JOIN `employees_projects` AS ep ON ep.employee_id = e.employee_id
-- LEFT JOIN `projects` AS p ON p.project_id = ep.project_id
-- ORDER BY e.employee_id DESC LIMIT 3;

SELECT e.first_name, e.last_name, e.hire_date, d.name AS 'dept_name'
FROM `employees` AS e
JOIN `departments` AS d ON d.department_id = e.department_id
WHERE e.hire_date > '1999-01-01' AND d.name IN ('Sales', 'Finance')
ORDER BY e.hire_date ASC;

SELECT e.employee_id, e.first_name, p.name AS 'project_name'
FROM `employees` AS e
JOIN `employees_projects` AS ep ON ep.employee_id = e.employee_id
JOIN `projects` AS p ON p.project_id = ep.project_id
WHERE DATE(p.start_date) > '2002-08-13' AND p.end_date IS NULL
ORDER BY e.first_name ASC, `project_name` ASC LIMIT 5;

SELECT e.employee_id, e.first_name, 
if(year(p.start_date) >= 2005, NULL, p.name) AS 'project_name'
FROM `employees` AS e
LEFT JOIN `employees_projects` AS ep ON ep.employee_id = e.employee_id
LEFT JOIN `projects` AS p ON p.project_id = ep.project_id
WHERE e.employee_id = 24
ORDER BY `project_name` ASC;

SELECT m.employee_id, m.first_name, m.manager_id, e.first_name AS 'manager_name' 
FROM `employees` AS e
JOIN `employees` AS m ON e.employee_id = m.manager_id
WHERE m.manager_id IN(3, 7)
ORDER BY m.first_name ASC;

SELECT m.employee_id, concat_ws(' ', m.first_name, m.last_name) AS 'employee_name', 
concat_ws(' ', e.first_name, e.last_name) AS 'manager_name', d.name AS 'deparment_name'
FROM `employees` AS e
JOIN `employees` AS m ON e.employee_id = m.manager_id
JOIN `departments` AS d ON d.department_id = m.department_id
ORDER BY m.employee_id ASC LIMIT 5;

SELECT AVG(e.salary) AS 'min_average_salary'
FROM `employees` AS e
GROUP BY e.department_id
ORDER BY `min_average_salary` ASC LIMIT 1;

USE `geography`;

SELECT c.country_code, m.mountain_range, p.peak_name, p.elevation
FROM `countries` AS c
JOIN `mountains_countries` AS mc ON mc.country_code = c.country_code
JOIN `mountains` AS m ON m.id = mc.mountain_id
JOIN `peaks` AS p ON p.mountain_id = m.id
WHERE c.country_code LIKE 'BG' AND p.elevation > 2835
ORDER BY p.elevation DESC;

SELECT c.country_code, COUNT(*) AS 'mountain_range'
FROM `countries` AS c
JOIN `mountains_countries` AS mc ON mc.country_code = c.country_code
JOIN `mountains` AS m ON m.id = mc.mountain_id
WHERE c.country_code IN('BG', 'RU', 'US')
GROUP BY c.country_code
ORDER BY `mountain_range` DESC;

SELECT c.country_name, r.river_name
FROM `countries` AS c
LEFT JOIN `countries_rivers` AS cr ON cr.country_code = c.country_code
LEFT JOIN `rivers` AS r ON r.id = cr.river_id
WHERE c.continent_code LIKE 'AF'
ORDER BY c.country_name ASC LIMIT 5;

SELECT COUNT(*) AS 'country_count'
FROM `countries` AS c
LEFT JOIN `mountains_countries` AS mc ON mc.country_code = c.country_code
LEFT JOIN `mountains` AS m ON m.id = mc.mountain_id
WHERE m.mountain_range IS NULL;




