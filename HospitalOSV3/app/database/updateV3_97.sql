ALTER TABLE t_result_lab ADD COLUMN result_lab_normal_flag character varying(255) DEFAULT '';

INSERT INTO s_version VALUES ('9701000000040', '40', 'Hospital OS, Community Edition', '3.9.7', '3.18.131010', '2553-10-13 18:20:00');

INSERT INTO s_script_update_log VALUES ('hospitalOS','updateV3_97.sql',(select current_date) || ','|| (select current_time),'ปรับแก้สำหรับ hospitalOS3.9.7');

