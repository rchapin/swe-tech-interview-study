SELECT
    t.employee_name
FROM
    -- First, we generate a table that executes the rank function over the
    -- sum of all of the prices of the sales grouped by the sales person.
    (
    SELECT
        s.employees_id,
        concat(e.first_name, ' ', e.last_name) as employee_name,
        RANK() OVER(ORDER BY SUM(s.price)) as rank
    FROM
        sales s
    JOIN employees e
        ON s.employees_id = e.id
    GROUP BY
        s.employees_id,
        employee_name
    ) t
-- Then, in the outer query, we filter on the rank of the salesperson for
-- which we are searching.
WHERE
    t.rank = 2
;
