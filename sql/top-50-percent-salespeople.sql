SELECT
    CONCAT(e.first_name, ' ', e.last_name),
    totals.total_price,
    totals.percentile
FROM
    (
        SELECT
            s.employee_id,
            SUM(s.price) AS total_price,
            PERCENT_RANK() OVER(
                ORDER BY
                    SUM(s.price) DESC
            ) as percentile
        FROM
            sales s
        GROUP BY
            s.employee_id
    ) totals
    JOIN employee e ON e.id = totals.employee_id
WHERE
    totals.percentile <= 0.5
ORDER BY
    totals.total_price DESC;
