SELECT
	concat(e1.first_name, ' ', e1.last_name) AS manager
FROM
	employee e1
WHERE
	e1.reports_to IS NULL;
