SELECT
    d.name,
    COUNT(e.department_id) AS employee_count
FROM
    department d
    JOIN employee e ON e.department_id = d.id
GROUP BY
    d.name;
