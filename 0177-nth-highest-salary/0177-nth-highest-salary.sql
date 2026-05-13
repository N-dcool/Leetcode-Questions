CREATE FUNCTION getNthHighestSalary(N IN NUMBER) RETURN NUMBER IS
result NUMBER;
BEGIN
    /* Write your PL/SQL query statement below */

    SELECT COALESCE(
        (SELECT MAX(salary) FROM (
                SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) as rnk FROM Employee
            ) WHERE rnk = N), 
            NULL
    ) INTO result FROM dual;

    RETURN result;
END;