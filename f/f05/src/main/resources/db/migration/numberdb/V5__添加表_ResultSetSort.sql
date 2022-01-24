CREATE TABLE result_set_sort (
	id int NOT NULL PRIMARY KEY auto_increment,
	is_limited tinyint(1) not null,
	first_start int not null,
	second_start int not null,
	parent int not null
);
