-- SELECT
--     concat(e.first_name, ' ', e.last_name),
--     e.salary,
--     RANK () OVER (
--         ORDER BY e.salary
--         DESC
--     ) as rank
-- FROM employees e
-- ;

SELECT
    t.name,
    t.nth_value
FROM
    (
    SELECT
        concat(e.first_name, ' ', e.last_name) as name,
        e.salary,
        NTH_VALUE (e.salary, 2) OVER (PARTITION BY e.salary)
    FROM employees e
    ) t
WHERE t.nth_value IS NOT NULL
;
