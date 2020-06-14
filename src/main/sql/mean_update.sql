UPDATE 
	teacher AS t,
    (SELECT 
		t_r.teacher_id AS `id`, 
        AVG(r.mark) AS `mean`, 
        COUNT(r.mark) AS `count`
	FROM 
		teacher_reviews AS t_r 
        JOIN review AS r ON t_r.reviews_id=r.id
	WHERE 
		r.approved
	GROUP BY 
		t_r.teacher_id) AS cur
SET 
	t.reviews_mean=(cur.`mean`+1),
    t.reviews_count=cur.`count`
WHERE 
	t.id=cur.id;