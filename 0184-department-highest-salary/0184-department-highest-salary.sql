/* Write your PL/SQL query statement below */

select Department, Employee, salary 
    from(
        select d.name as  Department , e.name as Employee, e.salary,
            DENSE_RANK() over(partition by e.departmentId order by e.salary DESC) as rnk 
            from Employee e left join Department d
                on e.departmentId = d.id
    ) where rnk = 1; 