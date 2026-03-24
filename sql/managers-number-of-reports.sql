SELECT
    CONCAT(e.first_name, ' ', e.last_name),
    t.report_count
FROM
    employees e
JOIN
    (
    SELECT
        e.reports_to,
        COUNT(e.reports_to) as report_count
    FROM employees e
    WHERE e.reports_to IS NOT NULL
    GROUP BY e.reports_to
    ) t
    ON e.id = t.reports_to
;


