alter table count_plan add column first_start int not null after first_inner_type;
alter table count_plan add column second_start int not null after second_inner_type;
alter table count_set add column first_start int not null after count_set;
alter table count_set add column second_start int not null after count_set;
