ALTER TABLE t_patient_payment ADD COLUMN checkplan_date character varying(10) DEFAULT '';

INSERT INTO s_version VALUES ('9701000000042', '42', 'Hospital OS, Community Edition', '3.9.9', '3.18.031210', '2553-12-03 18:20:00');

INSERT INTO s_script_update_log VALUES ('hospitalOS','updateV3_99.sql',(select current_date) || ','|| (select current_time),'ปรับแก้สำหรับ hospitalOS3.9.9');

