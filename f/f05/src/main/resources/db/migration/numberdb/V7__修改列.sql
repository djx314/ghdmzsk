DROP TABLE count_plan;
CREATE TABLE count_plan (
	id INT NOT NULL,
	first_outer_name TEXT NOT NULL,
	first_outer_type TEXT NOT NULL,
	first_inner_name TEXT NOT NULL,
	first_inner_type TEXT NOT NULL,
	second_outer_name TEXT NOT NULL,
	second_outer_type TEXT NOT NULL,
	second_inner_name TEXT NOT NULL,
	second_inner_type TEXT NOT NULL,
	PRIMARY KEY ("id")
)
;
