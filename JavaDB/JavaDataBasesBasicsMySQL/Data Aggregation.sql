SELECT COUNT(*) AS 'count' FROM `wizzard_deposits`;

SELECT MAX(`magic_wand_size`) AS 'longest_magic_wand' FROM `wizzard_deposits`;

SELECT `deposit_group`, MAX(`magic_wand_size`) AS 'longest_magic_wand'
FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY `longest_magic_wand` ASC, `deposit_group` ASC;

SELECT `deposit_group`
FROM `wizzard_deposits` AS w
GROUP BY `deposit_group`
HAVING MIN(w.`magic_wand_size`) LIMIT 1;

SELECT `deposit_group`, SUM(`deposit_amount`) AS 'total_sum'
FROM `wizzard_deposits` AS w
GROUP BY `deposit_group`
ORDER BY `total_sum` ASC;

SELECT w.deposit_group, SUM(w.deposit_amount) AS 'total_sum'
FROM `wizzard_deposits` AS w
WHERE w.magic_wand_creator LIKE 'Ollivander family'
GROUP BY w.deposit_group
ORDER BY w.deposit_group ASC;

SELECT w.deposit_group, SUM(w.deposit_amount) AS 'total_sum'
FROM `wizzard_deposits` AS w
WHERE w.magic_wand_creator LIKE 'Ollivander family'
GROUP BY w.deposit_group
HAVING `total_sum` < 150000
ORDER BY `total_sum` DESC;

SELECT w.deposit_group, w.magic_wand_creator, MIN(w.deposit_charge) AS 'min_deposit_charge'
FROM `wizzard_deposits` AS w
GROUP BY w.deposit_group, w.magic_wand_creator
ORDER BY w.magic_wand_creator ASC, w.deposit_group;

SELECT 
  CASE
    WHEN w.age <= 10 THEN '[0-10]'
    WHEN w.age <= 20 THEN '[11-20]'
    WHEN w.age <= 30 THEN '[21-30]'
    WHEN w.age <= 40 THEN '[31-40]'
    WHEN w.age <= 50 THEN '[41-50]'
    WHEN w.age <= 60 THEN '[51-60]'
    ELSE '[61+]'
  END AS 'age_group',
COUNT(*) AS 'wizard_count' 
FROM `wizzard_deposits` AS w
GROUP BY `age_group`
ORDER BY `age_group` ASC;

SELECT left(w.first_name, 1) AS 'first_letter' 
FROM `wizzard_deposits` AS w
WHERE w.deposit_group LIKE 'Troll Chest'
GROUP BY `first_letter`
ORDER BY `first_letter` ASC;

SELECT w.deposit_group, w.is_deposit_expired, AVG(w.deposit_interest) AS 'average_interest' 
FROM `wizzard_deposits` AS w
WHERE w.deposit_start_date > '1985-01-01'
GROUP BY w.deposit_group, w.is_deposit_expired
ORDER BY w.deposit_group DESC, w.is_deposit_expired ASC;

USE `soft_uni`;

SELECT e.department_id, MIN(e.salary) AS 'minimum_salary' 
FROM `employees` AS e
-- WHERE e.department_id IN(2,5,7) two variant
GROUP BY e.department_id
HAVING e.department_id IN(2,5,7)
ORDER BY e.department_id ASC;

SELECT e.department_id, 
if(e.department_id = 1, AVG(e.salary) + 5000, AVG(e.salary)) AS 'avg_salary' 
FROM `employees` AS e
WHERE e.manager_id != 42 AND e.salary > 30000
GROUP BY e.department_id
ORDER BY e.department_id ASC;

SELECT e.department_id, MAX(e.salary) AS 'max_salary'
FROM `employees` AS e
GROUP BY e.department_id
HAVING `max_salary` NOT BETWEEN 30000 AND 70000
ORDER BY e.department_id ASC;

SELECT COUNT(e.salary) AS ''
FROM `employees` AS e
WHERE isnull(e.manager_id);

SELECT e1.department_id, (SELECT DISTINCT e2.salary FROM `employees` AS e2
WHERE e1.department_id = e2.department_id
ORDER BY e2.salary DESC LIMIT 2,1) AS 'third_highest_salary'
FROM `employees` AS e1
GROUP BY e1.department_id
HAVING `third_highest_salary` IS NOT NULL
ORDER BY e1.department_id;

SELECT e.department_id, SUM(e.salary) AS 'total_salary'
FROM `employees` AS e
GROUP BY e.department_id
ORDER BY e.department_id;