SELECT
    concat(e.first_name, ' ', e.last_name) AS name
FROM
    employee e
    JOIN sales s ON e.id = s.employee_id
GROUP BY
    name
HAVING
    count(s.id) >= 3;
