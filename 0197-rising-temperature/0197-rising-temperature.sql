/* Write your PL/SQL query statement below */

select id from (
    select id , temperature, recordDate,
        LAG(temperature) Over(order by recordDate) as prevTemp,
        LAG(recordDate) Over(order by recordDate) as prevDate
        from weather 
) where recordDate - prevDate = 1 AND temperature > prevTemp;