SELECT e.id, e.first_name, e.last_name, d.name
FROM employees e
JOIN departments d
ON e.departments_id = d.id;
