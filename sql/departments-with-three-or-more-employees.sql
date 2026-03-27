SELECT
    d.name AS department_name,
    count(e.id)
FROM
    employee e
    JOIN department d ON e.department_id = d.id
WHERE
    e.department_id IS NOT NULL
GROUP BY
    department_name
HAVING
    count(e.id) >= 3
