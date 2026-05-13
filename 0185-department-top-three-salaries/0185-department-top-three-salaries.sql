/* Write your PL/SQL query statement below */


select Department, Employee, salary 
    from (
        select d.name as Department, e.name as Employee, e.salary, 
            DENSE_RANK() OVER(partition by d.name order by salary DESC) as rnk
                from Employee e 
                inner join Department d on e.departmentId = d.id;
    ) where rnk <= 3;

-- select d.name as Department, e.name as Employee, e.salary, 
--     ROW_NUMBER() OVER(partition by d.name order by salary DESC) as rnk
--         from Employee e 
--         inner join Department d on e.departmentId = d.id;