SELECT
    m.name
FROM
    (
    SELECT
        DISTINCT(reports_to) as id
    FROM
        employees
    WHERE
        employees.reports_to IS NOT NULL
    ) w
RIGHT JOIN
    (
    SELECT
        e.id,
        concat(e.first_name, ' ', e.last_name) as name
    FROM
        employees e
    WHERE
        reports_to IS NULL
    ) m
    ON w.id = m.id
WHERE w.id IS NULL
;

