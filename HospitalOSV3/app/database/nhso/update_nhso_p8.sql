
--ลบไม่ระบุเพศออกจากตาราง f_sex
--30-11-07
--ton
--ตาราง f_sex

delete from f_sex where f_sex_id = '3';

--เพิ่มสถานพยาบาลที่ตรวจของการรับบริการฝากครรภ์
ALTER TABLE t_nhso_anc
	DROP COLUMN nhso_anc_office_id;
ALTER TABLE t_nhso_anc
	ADD COLUMN nhso_anc_office_id varchar(255) NULL DEFAULT '';

update f_gui_action_authen set gui_action_authen_is_read = '0'  where f_gui_action_id = '0600'
	or f_gui_action_id = '2300' or f_gui_action_id = '5201';

--version 

INSERT INTO s_nhso_version("s_version_id", "version_number", "version_description", "version_application_number", "version_database_number", "version_update_time")
VALUES('9701000000021',	'8',	'Hospital OS, Community Edition',	'3.13.1150',	'0.1.031207',	'2550-12-03 20:00:00');

