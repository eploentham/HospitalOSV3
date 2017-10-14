ALTER TABLE t_visit ADD COLUMN service_location character varying(255) DEFAULT '';

INSERT INTO s_version VALUES ('9701000000041', '41', 'Hospital OS, Community Edition', '3.9.8', '3.18.271010', '2553-10-27 18:20:00');

INSERT INTO s_script_update_log VALUES ('hospitalOS','updateV3_98.sql',(select current_date) || ','|| (select current_time),'ปรับแก้สำหรับ hospitalOS3.9.8');

