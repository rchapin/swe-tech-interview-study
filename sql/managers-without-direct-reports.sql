SELECT
    t.manager
FROM
    (
        SELECT
            e.id,
            concat (e.first_name, ' ', e.last_name) AS manager
        FROM
            employee e
        WHERE
            e.reports_to IS NULL
    ) t
    LEFT OUTER JOIN employee e ON t.id = e.reports_to
WHERE
    e.id IS NULL;
