/* Write your PL/SQL query statement below */


SELECT score, DENSE_RANK() OVER (ORDER BY score DESC) as "rank" 
    FROM Scores;