SELECT
    d.name
FROM
    departments d
LEFT OUTER JOIN employees e
    ON e.departments_id = d.id
WHERE
    e.departments_id IS NULL
;
 
