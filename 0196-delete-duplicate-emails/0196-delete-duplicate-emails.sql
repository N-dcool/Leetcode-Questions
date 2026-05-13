/* Write your PL/SQL query statement below */

delete from person 
    where id not in (
        select MIN(id) 
            from person
                group by email);