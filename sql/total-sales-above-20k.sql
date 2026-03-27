SELECT
    concat(e.first_name, ' ', e.last_name) AS name,
    sum(s.price) AS total_sales
FROM
    employee e
    JOIN sales s ON e.id = s.employee_id
GROUP BY
    "name"
HAVING
    sum(s.price) > 20000;
