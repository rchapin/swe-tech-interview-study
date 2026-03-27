SELECT
    t.name,
    t.nth_value
FROM
    (
        SELECT
            concat(e.first_name, ' ', e.last_name) as name,
            e.salary,
            NTH_VALUE (e.salary, 2) OVER (PARTITION BY e.salary)
        FROM
            employee e
    ) t
WHERE
    t.nth_value IS NOT NULL;
