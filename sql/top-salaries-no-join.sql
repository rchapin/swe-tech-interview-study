DROP TABLE IF EXISTS top_salaries;

CREATE TABLE top_salaries AS
SELECT
    e.first_name,
    e.last_name,
    s.salary,
    d.name
FROM
    employee e,
    salary s,
    department d
WHERE
    e.salary = s.id
    AND e.department_id = d.id
    AND s.salary > 1500;
