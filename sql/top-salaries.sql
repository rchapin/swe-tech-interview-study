DROP TABLE IF EXISTS top_salaries;
CREATE TABLE top_salaries AS 
    SELECT
        concat(e.first_name, ' ', e.last_name) AS employee_name,
        d.name,
        s.salary
    FROM
        employees e
    JOIN salary s
        ON e.id = s.employees_id
    JOIN departments d
        ON e.departments_id = d.id
    WHERE
        s.salary > 1500
;
