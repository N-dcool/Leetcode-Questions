/* Write your PL/SQL query statement below */
SELECT COALESCE(
    (SELECT DISTINCT salary 
     FROM (
         SELECT salary, DENSE_RANK() OVER (ORDER BY salary DESC) AS rnk
         FROM Employee
     ) ranked
     WHERE rnk = 2),
    NULL
) AS SecondHighestSalary from dual;

-- select COALESCE(select salary from (select salary, DENSE_RANK() OVER (order by salary DESC) as rnk
-- from Employee ) where rnk = 2, NULL) as SecondHighestSalary;


-- select salary as SecondHighestSalary from (select salary, DENSE_RANK() OVER (order by salary DESC) as rnk
-- from Employee ) where rnk = 2;