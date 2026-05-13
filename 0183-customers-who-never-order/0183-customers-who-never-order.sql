/* Write your PL/SQL query statement below */

select c.name as Customers
    from customers c left join orders o on c.id = o.customerId 
        where o.customerId is NULL;