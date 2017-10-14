
--เพิ่ม ตาราง เพื่อรองรับ Pattern ใหม่
--17-11-07
--pu
--ตาราง t_nhso_postpartum
ALTER TABLE t_nhso_postpartum ADD COLUMN nhso_postpartum_office_id character varying(255);

--ตาราง t_nsho_epi
ALTER TABLE t_nhso_epi ADD COLUMN nhso_epi_office_id character varying(255);

--ตาราง t_nhso_pp 
ALTER TABLE t_nhso_pp ADD COLUMN  nhso_pp_office_id character varying(255);

--ตาราง t_nhso_pregnancy
ALTER TABLE t_nhso_pregnancy ADD COLUMN  nhso_pregnancy_office_id character varying(255);

--ตาราง t_nhso_pp_care
ALTER TABLE t_nhso_pp_care ADD COLUMN  nhso_pp_care_office_id character varying(255);


-- สิทธิ์หลัก
drop table f_nhso_sub_inscl ;
CREATE TABLE f_nhso_sub_inscl (
     nhso_sub_inscl_seq character varying(255),
     nhso_sub_inscl_id character varying(255) NOT NULL,
     nhso_sub_inscl_name character varying(255),
     nhso_sub_inscl_dateexp character varying(255),
     nhso_sub_main_inscl character varying(255),
     CONSTRAINT f_nhso_sub_inscl_pkey PRIMARY KEY (nhso_sub_inscl_id)
);

delete from b_nhso_map_plan;
-- ใส่ข้อมูลใน f_nhso_main_inscl

 insert into f_nhso_sub_inscl values ('1','60','อาสาสมัครมาเลเรีย','ตามวันหมดอายุของบัตรประจำตัว','WEL');
 insert into f_nhso_sub_inscl values ('2','61','บุคคลในครอบครัวของอาสาสมัครมาเลเรีย','ตามวันหมดอายุของเจ้าของบัตรประจำตัว','WEL');
 insert into f_nhso_sub_inscl values ('3','62','ช่างสุขภัณฑ์หมู่บ้าน','ตามวันหมดอายุของบัตรประจำตัว','WEL');
 insert into f_nhso_sub_inscl values ('4','63','บุคคลในครอบครัวของช่างสุขภัณฑ์หมู่บ้าน','ตามวันหมดอายุของเจ้าของบัตรประจำตัว','WEL');
 insert into f_nhso_sub_inscl values ('5','64','ผู้บริหารโรงเรียนและครูของโรงเรียนเอกชนที่สอนศาสนาอิสลาม','ตามวาระที่รับมอบหมาย','WEL');
 insert into f_nhso_sub_inscl values ('6','65','บุคคลในครอบครัวของผู้บริหารโรงเรียนและครูของโรงเรียนเอกชนที่สอนศาสนาอิสลาม','ตามวันหมดอายุของเจ้าของบัตรประจำตัว','WEL');
 insert into f_nhso_sub_inscl values ('7','66','ผู้ได้รับพระราชทานเหรียญราชการชายแดน','Noexp','WEL');
 insert into f_nhso_sub_inscl values ('8','67','ผู้ได้รับพระราชทานเหรียญพิทักษ์เสรีชน','Noexp','WEL');
 insert into f_nhso_sub_inscl values ('9','68','สมาชิกผู้บริจาคโลหิตของสภากาชาดไทย ซึ่งบริจาคโลหิตตั้งแต่ 18 ครั้ง ขึ้นไป','Noexp','WEL');
 insert into f_nhso_sub_inscl values ('10','69','หมออาสาหมู่บ้านตามโครงการของกระทรวงกลาโหม','Noexp','WEL');
 insert into f_nhso_sub_inscl values ('11','70','อาสาสมัครคุมประพฤ กระทรวงยุติธรรม','ตามวาระที่ได้รับมอบหมาย','WEL');
 insert into f_nhso_sub_inscl values ('12','71','เด็กอายุไม่เกิน 12 ปีบริบูรณ์','ณ วันที่ ครบ 12 ปีบริบูรณ์','WEL');
 insert into f_nhso_sub_inscl values ('13','72','ผู้มีรายได้น้อย','3 ปี ','WEL');
 insert into f_nhso_sub_inscl values ('14','73','นักเรียนมัธยมศึกษาตอนต้น','3 ปี ','WEL');
 insert into f_nhso_sub_inscl values ('15','74','บุคคลผู้พิการ','Noexp','WEL');
 insert into f_nhso_sub_inscl values ('16','75','ทหารผ่านศึกชั้น 1-3 ที่มีบัตรทหารผ่านศึก รวมถึงผู้ได้รับพระราชทาน','Noexp','WEL');
 insert into f_nhso_sub_inscl values ('17','76','พระภิกษุ สามเณร และแม่ชีในพระพุทธศาสนาซึ่งหนังสือสุทธิรับรอง','ตามสถานะภาพที่ปรากฎ','WEL');
 insert into f_nhso_sub_inscl values ('18','77','ผู้มีอายุเกิน 60 ปีบริบูรณ์','Noexp','WEL');
 insert into f_nhso_sub_inscl values ('19','78','อื่น ๆ','1 ปี','WEL');
 insert into f_nhso_sub_inscl values ('20','79','ว่างงาน','1 ปี','WEL');
 insert into f_nhso_sub_inscl values ('21','80','บุคคลในครอบครัวทหารผ่านศึกชั้น 1-3 รวมถึงผู้ได้รับพระราชทานเหรียญสมรภูมิ','Noexp','WEL');
 insert into f_nhso_sub_inscl values ('22','81','ผู้นำชุมชน (กำนัน สารวัตรกำนัน ผู้ใหญ่บ้าน ผู้ช่วยผู้ใหญ่บ้านและแพทย์ประจำตำบล)','ตามวาระที่ได้รับมอบหมาย','WEL');
 insert into f_nhso_sub_inscl values ('23','82','อาสาสมัครสาธารณสุขประจำหมู่บ้าน (อสม.) อาสาสมัครสาธารณสุข','ตามวันหมดอายุของบัตรประจำตัว','WEL');
 insert into f_nhso_sub_inscl values ('24','83','ผู้นำศาสนาอิสลาม ( อิหม่าม คอเต็บ บิหลั่น)','ตามวันหมดอายุของบัตรประจำตัว','WEL');
 insert into f_nhso_sub_inscl values ('25','84','บุคคลในครอบครัวของผู้นำศาสนาอิสลามของผู้นำศาสนาอิสลาม ( อิหม่าม คอเต็บ บิหลั่น)','ตามวันหมดอายุของเจ้าของบัตรประจำตัว','WEL');
 insert into f_nhso_sub_inscl values ('26','85','ผู้ได้รับพระราชทานเหรียญงานสงครามในทวีปยุโรป','ตามวันหมดอายุของบัตรประจำตัว','WEL');
 insert into f_nhso_sub_inscl values ('27','86','บุคคลในครอบครัวของผู้ได้รับพระราชทานเหรียญงานสงครามในทวีปยุโรป','ตามวันหมดอายุของเจ้าของบัตรประจำตัว','WEL');
 insert into f_nhso_sub_inscl values ('28','87','บุคคลในครอบครัวของผู้นำชุมชน (กำนัน สารวัตรกำนัน ผู้ใหญ่บ้าน ผู้ช่วยผู้ใหญ่บ้านและแพทย์ประจำตำบล)','ตามวันหมดอายุของเจ้าของบัตรประจำตัว','WEL');
 insert into f_nhso_sub_inscl values ('29','88','บุคคลในครอบครัวของอาสาสมัครสาธารณสุขประจำหมู่บ้าน (อสม.) อาสาสมัครสาธารณสุข','ตามวันหมดอายุของเจ้าของบัตรประจำตัว','WEL');
 insert into f_nhso_sub_inscl values ('30','89','ช่วงอายุ 12-59 ปี','ระหว่างช่วงอายุ 12 - 59 ปี','UCS');
 insert into f_nhso_sub_inscl values ('31','90','ทหารเกณฑ์','ตามวันที่ปลดประจำการ','WEL');
 insert into f_nhso_sub_inscl values ('32','91','ผู้ที่พำนักในสถานที่ภายใต้การดูแลของส่วนราชการ(ราชทัณฑ์)','ตามวันที่พ้นโทษ','WEL');
 insert into f_nhso_sub_inscl values ('33','92','ผู้ที่พำนักในสถานที่ภายใต้การดูแลของส่วนราชการ (สถานพินิจและสถานสงเคราะห์)','ตามช่วงเวลาที่อยู่ในความดูแล','WEL');
 insert into f_nhso_sub_inscl values ('34','93','นักเรียนทหาร','ตามวันที่จบการศึกษา','WEL');
 insert into f_nhso_sub_inscl values ('35','94','ทหารผ่นศึกชั้น 4 ที่มีบัตรทหารผ่านศึก รวมถึงผู้ได้รับพระราชทาน','Noexp','WEL');
 insert into f_nhso_sub_inscl values ('36','95','บุคคลในครอบครัวทหารผ่านศึกชั้น 4 รวมถึงผู้ได้รับพระราชทานเหรียญสมรภูมิ','Noexp','WEL');
 insert into f_nhso_sub_inscl values ('37','96','ทหารพราน','ตามวันหมดอายุของบัตรประจำตัว','WEL');
 insert into f_nhso_sub_inscl values ('38','PN','ผู้รับเบี้ยหวัดบำนาญ','NoExp','OFC');
 insert into f_nhso_sub_inscl values ('42','O1','สิทธิเบิกกรมบัญชีกลาง(ข้าราชการ)','','OFC');
 insert into f_nhso_sub_inscl values ('43','O2','สิทธิเบิกกรมบัญชีกลาง(ลูกจ้างประจำ)','','OFC');
 insert into f_nhso_sub_inscl values ('44','O3','สิทธิเบิกกรมบัญชีกลาง(ผู้รับเบี้ยหวัดบำนาญ)','','OFC');
 insert into f_nhso_sub_inscl values ('45','O4','สิทธิเบิกกรมบัญชีกลาง(บุคคลในครอบครัว)','','OFC');
 insert into f_nhso_sub_inscl values ('46','O5','สิทธิเบิกกรมบัญชีกลาง(บุคคลในครอบครัวผู้รับเบี้ยหวัดบำนาญ)','','OFC');
 insert into f_nhso_sub_inscl values ('47','97','บุคคลในครอบครัวทหารของกรมสวัสดิการ 3 เหล่าทัพ','Noexp','WEL');
 insert into f_nhso_sub_inscl values ('48','98','บุคคลในครอบครัวทหารผ่านศึกนอกประจำการบัตรชั้นที่ 1','Noexp','WEL');


--version 

INSERT INTO s_nhso_version("s_version_id", "version_number", "version_description", "version_application_number", "version_database_number", "version_update_time")
VALUES('9701000000018',	'5',	'Hospital OS, Community Edition',	'3.13.1150',	'0.1.171107',	'2550-11-17 18:00:00');

