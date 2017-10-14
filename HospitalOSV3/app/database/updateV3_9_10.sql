INSERT INTO f_item_lab_type (id, "name") VALUES ('5', 'เคมีคลินิค');
INSERT INTO f_item_lab_type (id, "name") VALUES ('6', 'โลหิตวิทยา');

CREATE TABLE f_pp_result_detail (
        f_pp_result_detail_id varchar(255),
        pp_result_detail_description varchar(255),
PRIMARY KEY (f_pp_result_detail_id)
);

INSERT INTO f_pp_result_detail (f_pp_result_detail_id, pp_result_detail_description) VALUES ('1', 'ใช่');

INSERT INTO f_pp_result_detail (f_pp_result_detail_id, pp_result_detail_description) VALUES ('0', 'ไม่');

CREATE TABLE b_ws_config (
        b_ws_config_id varchar(255),
	ws_config_url varchar(255),
        ws_config_username varchar(255),
	ws_config_password varchar(255),
PRIMARY KEY (b_ws_config_id)
);

CREATE TABLE b_ws_nhso_right (
        b_ws_nhso_right_id varchar(255),
	ws_nhso_right_des varchar(255),
        ws_nhso_right_active varchar(255),
PRIMARY KEY (b_ws_nhso_right_id)
);

CREATE TABLE b_map_nhso_plan (
        b_map_nhso_plan_id varchar(255),
	b_ws_nhso_right_id varchar(255),
	b_contract_plans_id varchar(255),
PRIMARY KEY (b_map_nhso_plan_id)
);

ALTER TABLE t_health_pregnancy ADD COLUMN pregnancy_p character varying(255) DEFAULT '';

ALTER TABLE t_health_pregnancy ADD COLUMN pregnancy_a character varying(255) DEFAULT '';

ALTER TABLE t_health_pregnancy ADD COLUMN pregnancy_l character varying(255) DEFAULT '';

ALTER TABLE t_health_postpartum ADD COLUMN postpartum_place_hos character varying(255) DEFAULT '';

ALTER TABLE t_visit_vital_sign ADD COLUMN visit_vital_sign_waistline_inch character varying(255) DEFAULT '';

ALTER TABLE b_icd9 ADD COLUMN icd_10_tm character varying(255) DEFAULT '';

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937599', 'สิทธิประกันสังคม', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937600', 'สิทธิข้าราชการ/สิทธิรัฐวิสาหกิจ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937601', 'สถานะคนไทยในต่างประเทศ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937602', 'สิทธิประกันสังคมและสิทธิข้าราชการ/สิทธิรัฐวิสาหกิจ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937603', 'สิทธิข้าราชการการเมือง/นักการเมือง', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937604', 'ไม่มีสิทธิใดๆ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937605', 'สถานะคนต่างด้าว', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937606', 'สิทธิครูเอกชน/สิทธิข้าราชการการเมือง', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937607', 'สิทธิทหารผ่านศึก', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937608', 'สิทธิประกันสังคม/สิทธิครูเอกชน/สิทธิข้าราชการ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937609', 'สิทธิทหารผ่านศึก/สิทธิข้าราชการ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937610', 'สิทธิประกันสังคม/สิทธิทหารผ่านศึก', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937611', 'สิทธิทหารผ่านศึก/สิทธิข้าราชการการเมือง', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937612', 'สิทธิประกันสังคม/สิทธิทหารผ่านศึก/สิทธิข้าราชการ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937613', 'สิทธิประกันสังคม/สิทธิทหารผ่านศึก/สิทธิข้าราชการการเมือง', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937614', 'สิทธิครูเอกชน', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937615', 'สิทธิประกันสังคม/สิทธิครูเอกชน', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937616', 'สิทธิครูเอกชน/สิทธิข้าราชการ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937617', 'สิทธิประกันสังคม/สิทธิครูเอกชน/สิทธิข้าราชการการเมือง', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937618', 'สิทธิครูเอกชน/สิทธิทหารผ่านศึก', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937619', 'สิทธิครูเอกชน/สิทธิประกันสังคม/สิทธิทหารผ่านศึก', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937620', 'สิทธิครูเอกชน/สิทธิทหารผ่านศึก/สิทธิข้าราชการ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937621', 'สิทธิครูเอกชน/สิทธิทหารผ่านศึก/สิทธิข้าราชการการเมือง', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937622', 'สิทธิประกันสังคมและสิทธิข้าราชการการเมือง/นักการเมือง', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937623', 'สิทธิประกันสังคมกรณีทุพลภาพ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937624', 'สิทธิประกันสังคมทุพลภาพและสิทธิข้าราชการ/สิทธิรัฐวิสาหกิจ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937625', 'สิทธิครูเอกชน/สิทธิประกันสังคมแบบทุพพลภาพ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937626', 'สิทธิประกันสังคมแบบทุพพลภาพ/สิทธิทหารผ่านศึก/สิทธิข้าราชการ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937627', 'สิทธิประกันสังคมแบบทุพพลภาพ/สิทธิทหารผ่านศึก', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937628', 'อาสาสมัครมาเลเรีย', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937629', 'บุคคลในครอบครัวของอาสาสมัครมาเลเรีย', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937630', 'ช่างสุขภัณฑ์หมู่บ้าน', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937631', 'บุคคลในครอบครัวของช่างสุขภัณฑ์หมู่บ้าน', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937632', 'ผู้บริหารโรงเรียนและครูของโรงเรียนเอกชนที่สอนศาสนาอิสลาม', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937633', 'บุคคลในครอบครัวของผู้บริหารโรงเรียนและครูของโรงเรียนเอกชนที่สอนศาสนาอิสลาม', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937634', 'ผู้ได้รับพระราชทานเหรียญราชการชายแดน', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937635', 'ผู้ได้รับพระราชทานเหรียญพิทักษ์เสรีชน', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937636', 'สมาชิกผู้บริจาคโลหิตของสภากาชาดไทย ซึ่งบริจาคโลหิตตั้งแต่ 18 ครั้ง ขึ้นไป', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937637', 'หมออาสาหมู่บ้านตามโครงการของกระทรวงกลาโหม', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937638', 'อาสาสมัครคุมประพฤ กระทรวงยุติธรรม', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937639', 'เด็กอายุไม่เกิน 12 ปีบริบูรณ์', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937640', 'ผู้มีรายได้น้อย', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937641', 'นักเรียนมัธยมศึกษาตอนต้น', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937642', 'บุคคลผู้พิการ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937643', 'ทหารผ่านศึกชั้น 1-3 ที่มีบัตรทหารผ่านศึก รวมถึงผู้ได้รับพระราชทาน', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937644', 'พระภิกษุ สามเณร และแม่ชีในพระพุทธศาสนาซึ่งหนังสือสุทธิรับรอง', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937645', 'ผู้มีอายุเกิน 60 ปีบริบูรณ์', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937646', 'อื่น ๆ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937647', 'ว่างงาน', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937648', 'บุคคลในครอบครัวทหารผ่านศึกชั้น 1-3 รวมถึงผู้ได้รับพระราชทานเหรียญสมรภูมิ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937649', 'ผู้นำชุมชน (กำนัน สารวัตรกำนัน ผู้ใหญ่บ้าน ผู้ช่วยผู้ใหญ่บ้านและแพทย์ประจำตำบล)', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937650', 'อาสาสมัครสาธารณสุขประจำหมู่บ้าน (อสม.) อาสาสมัครสาธารณสุข', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937651', 'ผู้นำศาสนาอิสลาม ( อิหม่าม คอเต็บ บิหลั่น)', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937652', 'บุคคลในครอบครัวของผู้นำศาสนาอิสลามของผู้นำศาสนาอิสลาม ( อิหม่าม คอเต็บ บิหลั่น)', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937653', 'ผู้ได้รับพระราชทานเหรียญงานสงครามในทวีปยุโรป', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937654', 'บุคคลในครอบครัวของผู้ได้รับพระราชทานเหรียญงานสงครามในทวีปยุโรป', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937655', 'บุคคลในครอบครัวของผู้นำชุมชน (กำนัน สารวัตรกำนัน ผู้ใหญ่บ้าน ผู้ช่วยผู้ใหญ่บ้านและแพทย์ประจำตำบล)', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937656', 'บุคคลในครอบครัวของอาสาสมัครสาธารณสุขประจำหมู่บ้าน (อสม.) อาสาสมัครสาธารณสุข', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937657', 'ช่วงอายุ 12-59 ปี', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937658', 'ทหารเกณฑ์', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937659', 'ผู้ที่พำนักในสถานที่ภายใต้การดูแลของส่วนราชการ(ราชทัณฑ์)', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937660', 'ผู้ที่พำนักในสถานที่ภายใต้การดูแลของส่วนราชการ    (สถานพินิจและสถานสงเคราะห์)', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937661', 'นักเรียนทหาร', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937662', 'ทหารผ่นศึกชั้น 4 ที่มีบัตรทหารผ่านศึก รวมถึงผู้ได้รับพระราชทาน', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937663', 'บุคคลในครอบครัวทหารผ่านศึกชั้น 4 รวมถึงผู้ได้รับพระราชทานเหรียญสมรภูมิ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937664', 'ทหารพราน', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937665', 'บุคคลในครอบครัวทหารของกรมสวัสดิการ 3 เหล่าทัพ', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937666', 'บุคคลในครอบครัวทหารผ่านศึกนอกประจำการบัตรชั้นที่ 1', '1');

INSERT INTO b_ws_nhso_right (b_ws_nhso_right_id, ws_nhso_right_des, ws_nhso_right_active) VALUES ('232204863937667', 'บุคคลที่มีปัญหาสถานะและสิทธิ', '1');

INSERT INTO b_ws_config (b_ws_config_id, ws_config_url,ws_config_username,ws_config_password) VALUES ('1', 'http://ucws.nhso.go.th/RightsSearchService/RightsSearchServiceService?wsdl','username','password');

update f_item_lab_type set name = 'จุลชีววิทยาคลินิก(Microbiology)' where id = '1';

update f_item_lab_type set name = 'จุลทรรศน์ศาสตร์(Macroscopy)' where id = '2';

update f_item_lab_type set name = 'ภูมิคุ้มกันวิทยา/อิมมูนวิทยา(Serology)' where id = '3';

update f_item_lab_type set name = 'ธนาคารเลือด(Blood Bank)' where id = '4';

update f_item_lab_type set name = 'เคมีคลีนิค( Chemeclinic)' where id = '5';

update f_item_lab_type set name = 'โลหิตวิทยาคลินิก (Hemotology)' where id = '6';

INSERT INTO f_item_lab_type (id, "name") VALUES ('7', 'เซลล์วิทยาคลินิก (Cytology)');

INSERT INTO s_version VALUES ('9701000000043', '43', 'Hospital OS, Community Edition', '3.9.10', '3.18.131210', '2553-12-13 18:20:00');

INSERT INTO s_script_update_log VALUES ('hospitalOS','updateV3_9_10.sql',(select current_date) || ','|| (select current_time),'ปรับแก้สำหรับ hospitalOS3.9.10');

