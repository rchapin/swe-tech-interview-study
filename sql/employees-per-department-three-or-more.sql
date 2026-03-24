SELECT
    d.name,
    COUNT(e.departments_id) AS employee_count
FROM departments d
JOIN employees e
    ON e.departments_id = d.id
GROUP BY
    d.name
HAVING
    COUNT(e.departments_id) >= 3
;
