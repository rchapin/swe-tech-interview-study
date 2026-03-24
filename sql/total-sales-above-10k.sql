SELECT
    t.total_sale,
    e.first_name,
    e.last_name
FROM
    (
    -- Create a sub select that enables us to aggregate the total sales
    -- figure and using the HAVING clause filter for any value > 10k to
    -- determine the employee ids that match that criteria.
    SELECT
        SUM(s.price) as total_sale,
        s.employees_id
    FROM
        sales s
    GROUP BY
        s.employees_id
    HAVING
        SUM(s.price) > 10000
    ) t
-- Then do a JOIN on the employees table to resolve the employee's name.
JOIN employees e ON
    t.employees_id = e.id
;
