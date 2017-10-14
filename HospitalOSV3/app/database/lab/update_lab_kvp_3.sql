

INSERT INTO s_lab_version VALUES ('9750000000003', '3', 'LAB, Community Edition', '1.07.260510', '1.07.260510', (select current_date) || ','|| (select current_time));

INSERT INTO s_script_update_log VALUES ('LAB_for Yaring','update_lab_kvp_3.sql',(select current_date) || ','|| (select current_time),'Alter table add execute_date_time index');

