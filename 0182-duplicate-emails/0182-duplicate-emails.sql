/* Write your PL/SQL query statement below */

select email from 
    (select email, count(*) cnt 
        from Person group by email) 
            where cnt > 1;
