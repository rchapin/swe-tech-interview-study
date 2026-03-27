SELECT
    d.name
FROM
    department d
    LEFT OUTER JOIN employee e ON e.department_id = d.id
WHERE
    e.department_id IS NULL;
