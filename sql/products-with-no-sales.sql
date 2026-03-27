SELECT
    s.product_id,
    p.id,
    p.name
FROM
    product p -- LEFT JOIN sales s
    FULL
    JOIN sales s ON p.id = s.product_id -- WHERE
    --     s.product_id is NULL
GROUP BY
    p.name,
    p.id,
    s.product_id;
