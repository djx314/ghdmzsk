ALTER TABLE count_plan ADD COLUMN first_outer_type TEXT NOT NULL DEFAULT '';
ALTER TABLE count_plan ADD COLUMN first_inner_name TEXT NOT NULL DEFAULT '';
ALTER TABLE count_plan ADD COLUMN first_inner_type TEXT NOT NULL DEFAULT '';
ALTER TABLE count_plan ADD COLUMN second_outer_name TEXT NOT NULL DEFAULT '';
ALTER TABLE count_plan ADD COLUMN second_outer_type TEXT NOT NULL DEFAULT '';
ALTER TABLE count_plan ADD COLUMN second_inner_name TEXT NOT NULL DEFAULT '';
ALTER TABLE count_plan ADD COLUMN second_inner_type TEXT NOT NULL DEFAULT '';