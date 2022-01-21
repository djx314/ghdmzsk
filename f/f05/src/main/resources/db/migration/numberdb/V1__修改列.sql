CREATE TABLE count_plan (
	id int NOT NULL PRIMARY KEY AUTOINCREMENT,
	first_outer_name varchar(255) NOT NULL,
	first_outer_type varchar(255) NOT NULL,
	first_inner_name varchar(255) NOT NULL,
	first_inner_type varchar(255) NOT NULL,
	second_outer_name varchar(255) NOT NULL,
	second_outer_type varchar(255) NOT NULL,
	second_inner_name varchar(255) NOT NULL,
	second_inner_type varchar(255) NOT NULL,
	counter_result_id int default null
);
