

ALTER TABLE b_welfare_map_plan ADD COLUMN welfare_map_plan_id varchar(255) not null;

insert into  s_welfare_version (s_version_id, version_number, version_description, version_application_number, version_database_number, version_update_time) values 
('9701000000015','2','Hospital OS, Community Edition','1.02.04012007','3.14.0150','2550-01-04 22:25:00')
