ALTER TABLE count_plan DROP COLUMN first_outer_name;
ALTER TABLE count_plan ADD COLUMN first_outer_name TEXT NOT NULL DEFAULT '';