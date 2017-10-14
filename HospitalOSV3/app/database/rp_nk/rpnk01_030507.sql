-- Table: b_report_12files_map_clinic
DROP TABLE t_rpnk_receipt_paid;
DROP TABLE b_rpnk_map_service_point;
DROP TABLE b_rpnk_map_plan_er_opd;
DROP TABLE b_rpnk_map_plan_dental;
DROP TABLE f_rpnk_plan_type_er_opd;
DROP TABLE f_rpnk_plan_type_dental;
DROP TABLE f_rpnk_service_point_type;
DROP TABLE t_rpnk_receipt_paid;
CREATE TABLE t_rpnk_receipt_paid
(
  t_rpnk_receipt_paid_id varchar(255) NOT NULL,
  t_billing_id varchar(255) DEFAULT ''::character varying,
  f_rpnk_paid_status_id varchar(255) DEFAULT ''::character varying,
  rpnk_receipt_paid double precision,
  rpnk_receipt_paid_no varchar(255) DEFAULT ''::character varying,
  rpnk_receipt_paid_date varchar(255) DEFAULT ''::character varying,
  rpnk_receipt_paid_staff varchar(255) DEFAULT ''::character varying,
  rpnk_receipt_paid_active varchar(255) DEFAULT ''::character varying,
  rpnk_receipt_draw_date varchar(255) DEFAULT ''::character varying,
  rpnk_receipt_draw_staff varchar(255) DEFAULT ''::character varying,
  rpnk_receipt_lost_date varchar(255) DEFAULT ''::character varying,
  rpnk_receipt_lost_staff varchar(255) DEFAULT ''::character varying,
  CONSTRAINT t_rpnk_receipt_paid_id PRIMARY KEY (t_rpnk_receipt_paid_id)
);
DROP TABLE f_rpnk_paid_status;
CREATE TABLE f_rpnk_paid_status
(
  f_rpnk_paid_status_id varchar(255) DEFAULT ''::character varying,
  rpnk_paid_status_description varchar(255) DEFAULT ''::character varying,
  CONSTRAINT f_rpnk_paid_status_id PRIMARY KEY (f_rpnk_paid_status_id)
);
insert into f_rpnk_paid_status values ('','รอชำระ');
insert into f_rpnk_paid_status values ('01','ตั้งเบิก');
insert into f_rpnk_paid_status values ('02','ชำระแล้ว');
insert into f_rpnk_paid_status values ('03','หนี้สูญ');

DROP TABLE b_rpnk_receipt_paid_type;
CREATE TABLE b_rpnk_receipt_paid_type
(
  b_rpnk_receipt_paid_type_id varchar(255) NOT NULL,
  rpnk_receipt_paid_type_name varchar(255) DEFAULT ''::character varying,
  CONSTRAINT b_rpnk_receipt_paid_type_id PRIMARY KEY (b_rpnk_receipt_paid_type_id)
);

insert into b_rpnk_receipt_paid_type values ('1','ผู้ป่วยค้าง');
insert into b_rpnk_receipt_paid_type values ('2','CSCD');
insert into b_rpnk_receipt_paid_type values ('3','CSMBS');
insert into b_rpnk_receipt_paid_type values ('4','ต้นสังกัด');
insert into b_rpnk_receipt_paid_type values ('5','เทศบาลนคร');
insert into b_rpnk_receipt_paid_type values ('6','อื่นๆ');

------------------Pu---04/05/50------START---------------------------------------------
---ตารางประเภทสิทธิการรักษา
CREATE TABLE f_rpnk_plan_type (
    f_rpnk_plan_type_id character varying(255) NOT NULL,
    rpnk_plan_type_description character varying(255),
    rpnk_plan_type_detail character varying(255)
);
ALTER TABLE ONLY f_rpnk_plan_type
    ADD CONSTRAINT f_rpnk_plan_type_pkey PRIMARY KEY (f_rpnk_plan_type_id);

INSERT INTO f_rpnk_plan_type  VALUES ('00', 'ไม่ระบุ','ไม่ระบุ');
INSERT INTO f_rpnk_plan_type  VALUES ('01', 'ขรก./รัฐวิสากิจ','ยังไม่ลงทะเบียน :ข้าราชการ/รัฐวิสาหกิจ');
INSERT INTO f_rpnk_plan_type  VALUES ('02', 'ขรก.ส่วนท้องถิ่น','ผู้ป่วยนอก : ข้าราชการส่วนท้องถิ่น,ผู้ป่วยใน : ข้าราชการส่วนท้องถิ่น');
INSERT INTO f_rpnk_plan_type  VALUES ('03', 'นอกเขต','นอกเขต');
INSERT INTO f_rpnk_plan_type  VALUES ('04', 'ประกันสังคม','ประกันสังคม');
INSERT INTO f_rpnk_plan_type  VALUES ('05', '(รับชำระ)อื่นๆ','พรบ.ผู้ประสบภัยจากรถ,แรงงานต่างด้าว ขึ้นทะเบียน,แรงงานต่างด้าว ไม่ขึ้นทะเบียน,ครูเอกชน(ผู้ป่วยนอก)');
INSERT INTO f_rpnk_plan_type  VALUES ('06', 'ผู้ป่วยค้าง','เทศบาล(นครศรีฯ) : ข้าราชการ/ลูกจ้างประจำ(เซ็น)');
INSERT INTO f_rpnk_plan_type  VALUES ('07', 'CSCD','ผู้ป่วยนอก : เบิกจ่ายตรงกรมบัญชีกลาง');
INSERT INTO f_rpnk_plan_type  VALUES ('08', 'CSMBS','ผู้ป่วยใน : เบิกจ่ายตรงกรมบัญชีกลาง');
INSERT INTO f_rpnk_plan_type  VALUES ('09', 'ต้นสังกัด','ครูเอกชน(ผู้ป่วยใน)');
INSERT INTO f_rpnk_plan_type  VALUES ('10', 'เทศบาลนคร','เทศบาล(นครศรีฯ) :ผู้ป่วยใน');
INSERT INTO f_rpnk_plan_type  VALUES ('11', '(ค้างชำระ)อื่นๆ','รพ.ต้นสังกัด');
INSERT INTO f_rpnk_plan_type  VALUES ('12', 'ท.ในเขต','(ในเขต)บัตรประกันสุขภาพถ้วนหน้า ฉุกเฉิน,นอกเขต:บัตรประกันสุขภาพถ้วนหน้า');
INSERT INTO f_rpnk_plan_type  VALUES ('13', 'อนุเคราะห์','อนุเคราะห์');
INSERT INTO f_rpnk_plan_type  VALUES ('14', 'ทหารผ่านศึก','เหรียญพิทักษ์เสรีชน/ทหารผ่านศึก');
INSERT INTO f_rpnk_plan_type  VALUES ('15', 'พนง.กองแพทย์','กองการแพทย์ เทศบาลนครศรี ฯ');

---ตารางการจับคู่ประเภทสิทธิการรักษา กับ สิทธิการรักษาในโปรแกรม
CREATE TABLE b_rpnk_map_plan (
    b_rpnk_map_plan_id character varying(255) NOT NULL,
    rpnk_map_plan_std_id character varying(255),
    rpnk_map_plan_ref_id character varying(255)
);
ALTER TABLE ONLY b_rpnk_map_plan
    ADD CONSTRAINT b_rpnk_map_plan_pkey PRIMARY KEY (b_rpnk_map_plan_id);

------------------Pu---04/05/50-----END---------------------------------------------


--ตารางรายการกลุ่มสิทธิสำหรับรายงานสรุปกิจกรรมบริการแผนกฉุกเฉิน และแผนกผู้ป่วยนอก
CREATE TABLE f_rpnk_plan_type_er_opd (
    f_rpnk_plan_type_er_opd_id character varying(255) NOT NULL,
    plan_type_er_opd_description character varying(255),
    CONSTRAINT f_rpnk_plan_type_er_opd_pkey PRIMARY KEY (f_rpnk_plan_type_er_opd_id)
);

INSERT INTO f_rpnk_plan_type_er_opd VALUES('1','ข้าราชการ/รัฐวิสาหกิจ/เบิกต้นสังกัด');
INSERT INTO f_rpnk_plan_type_er_opd VALUES('2','ประกันสังคม');
INSERT INTO f_rpnk_plan_type_er_opd VALUES('3','บัตรทอง');
INSERT INTO f_rpnk_plan_type_er_opd VALUES('4','แรงงานต่างด้าวที่ขึ้นทะเบียน');
INSERT INTO f_rpnk_plan_type_er_opd VALUES('99','อื่นๆ');
INSERT INTO f_rpnk_plan_type_er_opd VALUES('0','ไม่ระบุ');

--ตารางรายการกลุ่มสิทธิสำหรับรายงานสรุปกิจกรรมบริการแผนกทันตกรรม
CREATE TABLE f_rpnk_plan_type_dental (
    f_rpnk_plan_type_dental_id character varying(255) NOT NULL,
    plan_type_dental_description character varying(255),
    CONSTRAINT f_rpnk_plan_type_dental_pkey PRIMARY KEY (f_rpnk_plan_type_dental_id)
);

INSERT INTO f_rpnk_plan_type_dental VALUES('1','ข้าราชการ/รัฐวิสาหกิจ/เบิกต้นสังกัด');
INSERT INTO f_rpnk_plan_type_dental VALUES('2','ประกันสังคม');
INSERT INTO f_rpnk_plan_type_dental VALUES('3','UC ในเครือข่าย');
INSERT INTO f_rpnk_plan_type_dental VALUES('4','UC นอกเครือข่าย');
INSERT INTO f_rpnk_plan_type_dental VALUES('99','สิทธิอื่นๆ');
INSERT INTO f_rpnk_plan_type_dental VALUES('0','ไม่ระบุ');

--ตารางข้อมูลจับคู่สิทธิสำหรับรายงานสรุปกิจกรรมบริการแผนกฉุกเฉิน และแผนกผู้ป่วยนอก
CREATE TABLE b_rpnk_map_plan_er_opd (
    b_rpnk_map_plan_er_opd_id character varying(255) NOT NULL,
    map_plan_er_opd_std_id character varying(255),
    map_plan_er_opd_ref_id character varying(255),
    CONSTRAINT b_rpnk_map_plan_er_opd_pkey PRIMARY KEY (b_rpnk_map_plan_er_opd_id)
);

--ตารางข้อมูลจับคู่สิทธิสำหรับรายงานสรุปกิจกรรมบริการแแผนกทันตกรรม
CREATE TABLE b_rpnk_map_plan_dental (
    b_rpnk_map_plan_dental_id character varying(255) NOT NULL,
    map_plan_dental_std_id character varying(255),
    map_plan_dental_ref_id character varying(255),
    CONSTRAINT b_rpnk_map_plan_dental_pkey PRIMARY KEY (b_rpnk_map_plan_dental_id)
);

--ตารางรายการข้อมูลจับคู่จุดบริการ
DROP TABLE b_rpnk_map_service_point ;
DROP TABLE f_rpnk_service_point_type;

------------------Henbe--18/05/50-----Start---------------------------------------------

--ตารางข้อมูลจับคู่สิทธิสำหรับรายงาน
CREATE TABLE b_rpnk_map_6opd (
    b_rpnk_map_6opd_id character varying(255) NOT NULL,
    b_service_point_id character varying(255),
    f_rpnk_6opd_type_id character varying(255),
    CONSTRAINT b_rpnk_map_6opd_pkey PRIMARY KEY (b_rpnk_map_6opd_id)
);
--ตารางรายการข้อมูลอ้างอิง
CREATE TABLE f_rpnk_6opd_type (
    f_rpnk_6opd_type_id character varying(255) NOT NULL,
    rpnk_6opd_type_description character varying(255),
    CONSTRAINT f_rpnk_6opd_type_pkey PRIMARY KEY (f_rpnk_6opd_type_id)
);
insert into f_rpnk_6opd_type values ('','ไม่ระบุ');
insert into f_rpnk_6opd_type values ('01','จุดบริการ OPD');

------------------------------
--ตารางข้อมูลจับคู่สิทธิสำหรับรายงาน
CREATE TABLE b_rpnk_map_14dental_icd (
    b_rpnk_map_14dental_icd_id character varying(255) NOT NULL,
    b_icd10_id character varying(255),
    f_rpnk_14dental_type_id character varying(255),
    CONSTRAINT b_rpnk_map_14dental_icd_pkey PRIMARY KEY (b_rpnk_map_14dental_icd_id)
);
--ตารางรายการข้อมูลอ้างอิง
CREATE TABLE f_rpnk_14dental_type (
    f_rpnk_14dental_type_id character varying(255) NOT NULL,
    rpnk_14dental_type_description character varying(255),
    CONSTRAINT f_rpnk_14dental_type_pkey PRIMARY KEY (f_rpnk_14dental_type_id)
);
insert into f_rpnk_14dental_type values ('','ไม่ระบุ');
insert into f_rpnk_14dental_type values ('01','การรักษาทางทันตกรรม');
insert into f_rpnk_14dental_type values ('02','ทันตกรรมป้องกัน');
------------------------------
--ตารางข้อมูลจับคู่สิทธิสำหรับรายงาน
drop table b_rpnk_map_10ipd ;
CREATE TABLE b_rpnk_map_10ipd (
    b_rpnk_map_10ipd_id character varying(255) NOT NULL,
    b_ward_id character varying(255),
    f_rpnk_10ipd_ward_id character varying(255),
    CONSTRAINT b_rpnk_map_10ipd_pkey PRIMARY KEY (b_rpnk_map_10ipd_id)
);
--ตารางรายการข้อมูลอ้างอิง
drop table f_rpnk_10ipd_ward;
CREATE TABLE f_rpnk_10ipd_ward (
    f_rpnk_10ipd_ward_id character varying(255) NOT NULL,
    rpnk_10ipd_ward_description character varying(255),
    CONSTRAINT f_rpnk_10ipd_ward_pkey PRIMARY KEY (f_rpnk_10ipd_ward_id)
);
insert into f_rpnk_10ipd_ward values ('','ไม่ระบุ');
insert into f_rpnk_10ipd_ward values ('01','IPD');
insert into f_rpnk_10ipd_ward values ('02','LR');
--------------------------------ตารางข้อมูลจับคู่สิทธิสำหรับรายงาน1111111111111111111
CREATE TABLE b_rpnk_map_07er (
    b_rpnk_map_07er_id character varying(255) NOT NULL,
    b_service_point_id character varying(255),
    f_rpnk_07er_type_id character varying(255),
    CONSTRAINT b_rpnk_map_07er_pkey PRIMARY KEY (b_rpnk_map_07er_id)
);
--ตารางรายการข้อมูลอ้างอิง
CREATE TABLE f_rpnk_07er_type (
    f_rpnk_07er_type_id character varying(255) NOT NULL,
    rpnk_07er_type_description character varying(255),
    CONSTRAINT f_rpnk_07er_type_pkey PRIMARY KEY (f_rpnk_07er_type_id)
);
insert into f_rpnk_07er_type values ('','ไม่ระบุ');
insert into f_rpnk_07er_type values ('01','จุดบริการ ER');

--------------------------------ตารางข้อมูลจับคู่สิทธิสำหรับรายงาน
CREATE TABLE b_rpnk_map_08opd (
    b_rpnk_map_08opd_id character varying(255) NOT NULL,
    b_service_point_id character varying(255),
    f_rpnk_08opd_type_id character varying(255),
    CONSTRAINT b_rpnk_map_08opd_pkey PRIMARY KEY (b_rpnk_map_08opd_id)
);
--ตารางรายการข้อมูลอ้างอิง
CREATE TABLE f_rpnk_08opd_type (
    f_rpnk_08opd_type_id character varying(255) NOT NULL,
    rpnk_08opd_type_description character varying(255),
    CONSTRAINT f_rpnk_08opd_type_pkey PRIMARY KEY (f_rpnk_08opd_type_id)
);
insert into f_rpnk_08opd_type values ('','ไม่ระบุ');
insert into f_rpnk_08opd_type values ('01','จุดบริการ OPD');
------------------------------
--------------------------------ตารางข้อมูลจับคู่สิทธิสำหรับรายงาน
CREATE TABLE b_rpnk_map_14dental_sp (
    b_rpnk_map_14dental_sp_id character varying(255) NOT NULL,
    b_service_point_id character varying(255),
    f_rpnk_14dental_sp_id character varying(255),
    CONSTRAINT b_rpnk_map_14dental_sp_pkey PRIMARY KEY (b_rpnk_map_14dental_sp_id)
);
--ตารางรายการข้อมูลอ้างอิง
CREATE TABLE f_rpnk_14dental_sp (
    f_rpnk_14dental_sp_id character varying(255) NOT NULL,
    rpnk_14dental_sp_description character varying(255),
    CONSTRAINT f_rpnk_14dental_sp_pkey PRIMARY KEY (f_rpnk_14dental_sp_id)
);
insert into f_rpnk_14dental_sp values ('','ไม่ระบุ');
insert into f_rpnk_14dental_sp values ('01','จุดบริการ ทันตกรรม');
------------------------------

---ตารางเก็บ version ของรายงานเทศบาลนครฯ
drop table s_rpnk_version ;
CREATE TABLE s_rpnk_version (
    s_rpnk_version_id character varying(255) NOT NULL,
    rpnk_version_number character varying(255),
    rpnk_version_description character varying(255),
    rpnk_version_application_number character varying(255),
    rpnk_version_notice character varying(255),
    rpnk_version_update_date_time character varying(255),
    CONSTRAINT s_rpnk_version_pkey PRIMARY KEY (s_rpnk_version_id)
);
-------------------------------

------------------Henbe--18/05/50-----End---------------------------------------------

------------------------------insert command here---------------------------------------
delete from s_rpnk_version where s_rpnk_version_id = '9720000000001';

insert into s_rpnk_version  (s_rpnk_version_id, rpnk_version_number, rpnk_version_description, rpnk_version_application_number, rpnk_version_notice, rpnk_version_update_date_time)  values('9720000000001','01','ReportNakorn by HospitalOS Customize','1.1.030507','for hospitalOS v3 db : 3.14n.050506 and pcu db : 0.04.0449','2550-06-07 11:16:00')  

------------------Pu--06/06/50-----End---------------------------------------------
