SELECT
    s.products_id, p.id, p.name
FROM
    products p
    -- LEFT JOIN sales s
    FULL JOIN sales s
    ON p.id = s.products_id
-- WHERE
--     s.products_id is NULL
GROUP BY
    p.name, p.id, s.products_id
;

