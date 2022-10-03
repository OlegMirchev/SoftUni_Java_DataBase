DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
    SELECT e.first_name, e.last_name FROM `employees` AS e
    WHERE e.salary > 35000
    ORDER BY e.first_name ASC, e.last_name ASC, e.employee_id ASC;
END $$
DELIMITER ;

CALL usp_get_employees_salary_above_35000();

DELIMITER \\
CREATE PROCEDURE usp_get_employees_salary_above(numb DOUBLE(19,4))
BEGIN
    SELECT e.first_name, e.last_name FROM `employees` AS e
    WHERE e.salary >= numb
    ORDER BY e.first_name ASC, e.last_name ASC, e.employee_id ASC;
END 
\\

CALL usp_get_employees_salary_above(45000);
-- DROP PROCEDURE usp_get_employees_salary_above;

DELIMITER \\
CREATE PROCEDURE usp_get_towns_starting_with(letter VARCHAR(45))
BEGIN
    SELECT t.name AS 'town_name' FROM `towns` AS t
    WHERE t.name LIKE concat(letter, '', '%')
    ORDER BY `town_name` ASC;
END 
\\

CALL usp_get_towns_starting_with('b');
-- DROP PROCEDURE usp_get_towns_starting_with;

DELIMITER \\
CREATE PROCEDURE usp_get_employees_from_town(town_name VARCHAR(45))
BEGIN
    SELECT e.first_name, e.last_name FROM `employees` AS e
    JOIN `addresses` AS a ON a.address_id = e.address_id
    JOIN `towns` AS t ON t.town_id = a.town_id
    WHERE t.name = town_name
    ORDER BY e.first_name ASC, e.last_name ASC, e.employee_id ASC;
END 
\\

CALL usp_get_employees_from_town('Sofia');
-- DROP PROCEDURE usp_get_employees_from_town;

CREATE FUNCTION ufn_get_salary_level(salary DOUBLE(19, 4))
RETURNS VARCHAR(50)
DETERMINISTIC
RETURN (
CASE 
       WHEN salary < 30000 THEN 'Low'
       WHEN salary <= 50000 THEN 'Average'
       ELSE 'High'
END);
       
SELECT e.salary, ufn_get_salary_level(e.salary) AS 'salary_Level'
FROM `employees` AS e;
DROP FUNCTION ufn_get_salary_level;

DELIMITER \\
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(50))
BEGIN
SELECT e.first_name, e.last_name FROM `employees` AS e
WHERE ufn_get_salary_level(e.salary) = salary_level
ORDER BY e.first_name DESC, e.last_name DESC;
END
\\

CALL usp_get_employees_by_salary_level('High');
DROP PROCEDURE usp_get_employees_by_salary_level;

CREATE FUNCTION ufn_is_word_comprised(set_of_letters VARCHAR(50), word VARCHAR(50))
RETURNS BIT
DETERMINISTIC
RETURN (
       IF(word REGEXP( concat('^[', set_of_letters, ']+$')), 1, 0)
);

SELECT ufn_is_word_comprised('oistmiahf', 'Sofia');
DROP FUNCTION ufn_is_word_comprised;

DELIMITER \\
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
SELECT concat_ws(' ', ah.first_name, ah.last_name) AS 'full_name'
FROM `account_holders` AS ah
ORDER BY `full_name` ASC, ah.id ASC;
END
\\

CALL usp_get_holders_full_name();
DROP PROCEDURE usp_get_holders_full_name;

DELIMITER \\
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(numb DECIMAL(19,4))
BEGIN
SELECT ah.first_name, ah.last_name
FROM `account_holders` AS ah
JOIN `accounts` AS a ON a.account_holder_id = ah.id
GROUP BY a.account_holder_id
HAVING SUM(a.balance) > numb
ORDER BY a.account_holder_id ASC;
END
\\

CALL usp_get_holders_with_balance_higher_than(7000);
DROP PROCEDURE usp_get_holders_with_balance_higher_than;

DELIMITER \\
CREATE FUNCTION ufn_calculate_future_value(sum DECIMAL(19,4), interest_rate DOUBLE(19,4), years INT)
RETURNS DECIMAL(19,4)
DETERMINISTIC
BEGIN
DECLARE future_value DECIMAL(19,4);
SET future_value := sum * pow((1 + interest_rate), years);
RETURN future_value;
END
\\

DELIMITER \\
CREATE PROCEDURE usp_calculate_future_value_for_account(id INT, interest_rate DECIMAL(19,4))
BEGIN
SELECT a.id AS 'account_id', ah.first_name, ah.last_name, 
a.balance AS 'current_balance', ufn_calculate_future_value(a.balance, interest_rate, 5) AS 'balance_in_5_years'
FROM `account_holders` AS ah
JOIN `accounts` AS a ON a.account_holder_id = ah.id
WHERE a.id = id;
END
\\

CALL usp_calculate_future_value_for_account(1, 0.1);
DROP PROCEDURE usp_calculate_future_value_for_account;

DELIMITER \\
CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL(19,4))
BEGIN
START TRANSACTION;
 IF(money_amount < 0) 
    THEN ROLLBACK;
 ELSE
    UPDATE `accounts` AS a
    SET a.balance = a.balance + money_amount
    WHERE a.id = account_id;
 END IF;
COMMIT;
END
\\

CALL usp_deposit_money(1, 10);
DROP PROCEDURE usp_deposit_money;

DELIMITER \\
CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL(19,4))
BEGIN
 START TRANSACTION;
  IF(money_amount < 0 OR money_amount > (SELECT a.balance FROM `accounts` AS a
                                                          WHERE a.id = account_id))
   THEN ROLLBACK;
  ELSE
   UPDATE `accounts` AS a
   SET a.balance = a.balance - money_amount
   WHERE a.id = account_id;
  END IF;
COMMIT;
END
\\

CALL usp_withdraw_money(1, 10);
DROP PROCEDURE usp_withdraw_money;

DELIMITER \\
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, money_amount DECIMAL(19,4))
BEGIN
START TRANSACTION;
CASE
WHEN from_account_id = to_account_id OR from_account_id <= 0 OR to_account_id <= 0 THEN ROLLBACK;
WHEN money_amount < 0 OR money_amount > (SELECT a.balance FROM `accounts` AS a
                                                          WHERE a.id = from_account_id) THEN ROLLBACK;
ELSE
UPDATE `accounts` AS a
SET a.balance = a.balance - money_amount
WHERE a.id = from_account_id;
UPDATE `accounts` AS a
SET a.balance = a.balance + money_amount
WHERE a.id = to_account_id;
END CASE;
COMMIT;
END
\\

CALL usp_transfer_money(1, 2, 10);
DROP PROCEDURE usp_transfer_money;

