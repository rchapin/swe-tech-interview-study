SELECT
    concat(e1.first_name, ' ', e1.last_name) AS manager
FROM
    employees e1
LEFT JOIN employees e2
    ON e1.reports_to = e2.id
WHERE
    e2 is NULL
;
