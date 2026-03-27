SELECT
    t.employee_name,
    t.department_name,
    t.salary
FROM
    (
        -- The sub-query generates the ranked results of the join query.
        SELECT
            concat(e.first_name, ' ', e.last_name) AS employee_name,
            d.name AS department_name,
            s.salary,
            RANK() OVER(
                PARTITION BY d.id
                ORDER BY
                    s.salary DESC
            )
        FROM
            employee e
            JOIN department d ON e.department_id = d.id
            JOIN salary s ON e.salary = s.id
    ) t
WHERE
    t.RANK = 1;
