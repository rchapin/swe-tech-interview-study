SELECT
    e.id,
    e.first_name,
    e.last_name,
    d.name
FROM
    employee e
    JOIN department d ON e.department_id = d.id;
