SELECT
	r.id,
    r.course,
    r.mark + 1,
    r.review_date,
    r.`text`,
    r.approved
FROM 
	review AS r
WHERE
	NOT r.approved;