SELECT
	t.id,
    t.last_name,
    t.first_name,
    t.middle_name,
    e.`name`,
    e.city
FROM
	teacher AS t
    JOIN teacher_employers AS t_e ON t.id=t_e.teachers_id
    JOIN employer AS e ON t_e.employers_id=e.id
WHERE
	NOT t.approved;