# Write your MySQL query statement below


select name as Employee from Employee e
    Where salary > (select salary from Employee m Where e.managerId = m.id);