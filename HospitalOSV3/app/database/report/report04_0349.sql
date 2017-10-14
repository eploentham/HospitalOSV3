-- แก้ไขข้อมูล รหัสของรายงานโรคตา
UPDATE  r_eye_disease_code SET
	eye_disease_code_begin = 'E50'
	,eye_disease_code_end = 'E50.99'
	WHERE  r_eye_disease_code_id = '8140000000009' and r_eye_group_id = '8130000000008';

-- แก้ไขขนาด Field ของตาราง สำหรับบันทึก SQL
Alter table r_sql_template 
	Alter column sql_template_sql Type character varying(4000);

-- เพิ่มข้อมูล version
INSERT INTO s_report_version (s_report_version_id, report_version_number, report_version_description, report_version_application_number, report_version_notice, report_version_update_date_time) 
	VALUES ('9720000000004', '04', 'HospitalOS Report', '1.04.240306', 'for hospitalOS v3 db : 3.13.1048 and pcu db : 0.03.1148', '2549-03-24 18:00:00');
