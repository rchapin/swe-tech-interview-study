SELECT
    es.employee_name,
    es.department, 
    es.salary,
    RANK() OVER(PARTITION BY es.department ORDER BY es.salary DESC)
FROM
    (
    SELECT
        concat(e.first_name, ' ', e.last_name) as employee_name,
        s.salary,
        d.name as department
    FROM employees e
    JOIN salary s
        ON e.id = s.employees_id
    JOIN departments d
        ON e.departments_id = d.id
    ) as es
;
    
