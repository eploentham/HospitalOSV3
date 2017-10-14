alter table s_lab_version add primary key (s_lab_version_id);

INSERT INTO s_lab_version VALUES ('9750000000004', '4', 'LAB, Community Edition', '1.07.020610', '1.07.020610', (select current_date) || ','|| (select current_time));

INSERT INTO s_script_update_log VALUES ('LAB_for Yaring','update_lab_kvp_4.sql',(select current_date) || ','|| (select current_time),'alter table s_lab_version add primary key (s_lab_version_id)');