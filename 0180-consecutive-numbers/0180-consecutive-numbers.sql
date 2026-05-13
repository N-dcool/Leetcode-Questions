/* Write your PL/SQL query statement below */

select distinct num as ConsecutiveNums from (
select distinct num, count(*) as cnt, ordering from (select num, 
    (ROW_NUMBER() OVER(order by id) -
    ROW_NUMBER() OVER(partition by num order by id)) as ordering
        from logs) group by num, ordering) where cnt >= 3;