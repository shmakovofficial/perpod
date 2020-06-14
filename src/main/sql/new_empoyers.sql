SELECT
	e.id,
    e.`name`,
    e.city,
    e.approved
FROM
	employer AS e
WHERE
	NOT e.approved;