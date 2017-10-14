DROP TABLE t_nhso_anc;
DROP TABLE b_nhso_map_concounseling;
DROP TABLE f_nhso_concounseling;
DROP TABLE t_nhso_postpartum;
DROP TABLE t_nhso_dental;
DROP TABLE t_nhso_epi;
DROP TABLE b_nhso_map_epi;
DROP TABLE f_nhso_epi_group;
DROP TABLE t_nhso_nutrition;
DROP TABLE t_nhso_home;
DROP TABLE t_nhso_visit_home;
DROP TABLE f_nhso_survey_employee;
DROP TABLE f_visit_home_check_health;
DROP TABLE f_visit_home_disease;
DROP TABLE t_nhso_patient_url;
DROP TABLE t_nhso_patient_payment;
DROP TABLE f_nhso_main_inscl;
DROP TABLE b_nhso_map_drug;
DROP TABLE f_nhso_drug;
DROP TABLE b_nhso_map_plan;
DROP TABLE b_nhso_map_fp_drug;
DROP TABLE f_nhso_fp_drug;
DROP TABLE t_nhso_assess_conclusion;
DROP TABLE f_nhso_assess_conclusion_topic;

ALTER TABLE t_nhso_vital_sign
	DROP COLUMN vital_sign_result;
ALTER TABLE t_nhso_vital_sign
	DROP COLUMN vital_sign_result_other;

ALTER TABLE t_nhso_risk_factor
	DROP COLUMN risk_factor_comment;

ALTER TABLE t_nhso_service
	DROP COLUMN service_modify_date_time;
ALTER TABLE t_nhso_service
	DROP COLUMN service_modify_employee;

ALTER TABLE t_nhso_doctor_conclusion
	DROP COLUMN doctor_conclusion_result_suggest;
ALTER TABLE t_nhso_doctor_conclusion
	DROP COLUMN doctor_conclusion_result_manual_change;
ALTER TABLE t_nhso_doctor_conclusion
	DROP COLUMN doctor_conclusion_result_other_change;
ALTER TABLE t_nhso_doctor_conclusion
	DROP COLUMN doctor_conclusion_result_other_change_detail;

DELETE FROM s_nhso_version where s_version_id = '9701000000015';
DELETE FROM f_nhso_excercise_group where f_nhso_excercise_group_id = '03';
-----------------------------------------------------------------

ALTER TABLE t_nhso_vital_sign
	ADD COLUMN vital_sign_result character varying(255);
ALTER TABLE t_nhso_vital_sign
	ADD COLUMN vital_sign_result_other character varying(255);

ALTER TABLE t_nhso_risk_factor
	ADD COLUMN risk_factor_comment character varying(255);

ALTER TABLE t_nhso_service
	ADD COLUMN service_modify_date_time character varying(255);
ALTER TABLE t_nhso_service
	ADD COLUMN service_modify_employee character varying(255);	

ALTER TABLE t_nhso_doctor_conclusion
	ADD COLUMN doctor_conclusion_result_suggest character varying(255);
ALTER TABLE t_nhso_doctor_conclusion
	ADD COLUMN doctor_conclusion_result_manual_change character varying(255);
ALTER TABLE t_nhso_doctor_conclusion
	ADD COLUMN doctor_conclusion_result_other_change character varying(255);
ALTER TABLE t_nhso_doctor_conclusion
	ADD COLUMN doctor_conclusion_result_other_change_detail character varying(255);

--ประเภทการออกกำลังกาย
INSERT INTO f_nhso_excercise_group VALUES ('03','เล่นกีฬา');
--ความถี่ในการออกกำลังกาย
UPDATE f_nhso_excercise_frequency SET excercise_frequency_description='ไม่แน่นอน' WHERE f_nhso_excercise_frequency_id = '03';
--ฝากครรภ์
CREATE TABLE t_nhso_anc (
	t_nhso_anc_id character varying(255) NOT NULL,
	t_health_anc_id character varying(255),
	health_anc_cbc character varying(255),
	health_anc_of character varying(255),
	health_anc_dcip character varying(255),
	health_anc_mouth character varying(255),
	health_anc_ua character varying(255),
	health_anc_hbt_confirm character varying(255),
	health_anc_vdrl_confirm character varying(255),
	health_anc_other character varying(255),
	health_anc_other_detail character varying(255),
	health_anc_dent_clean character varying(255),
	health_anc_active character varying(255), 
	CONSTRAINT t_nhso_anc_pkey PRIMARY KEY (t_nhso_anc_id)
);

--จับคู่หัวข้อการให้คำแนะนำ
CREATE TABLE b_nhso_map_concounseling (
	b_nhso_map_counseling_id character varying(255) NOT NULL,
	f_nhso_counseling_id character varying(255),
	b_health_service_type_id character varying(255),
	CONSTRAINT b_nhso_map_concounseling_pkey PRIMARY KEY (b_nhso_map_counseling_id)
);

--รายการหัวข้อการให้คำแนะนำ
CREATE TABLE f_nhso_concounseling (
	f_nhso_counseling_id character varying(255),
	counseling_name character varying(255),
	CONSTRAINT f_nhso_concounseling_pkey PRIMARY KEY (f_nhso_counseling_id)
);
insert into f_nhso_concounseling values ('01','การให้คำปรึกษาก่อนเจาะเลือด');
insert into f_nhso_concounseling values ('02','การให้คำปรึกษา HIV ก่อนเจาะเลือด');
insert into f_nhso_concounseling values ('03','การให้คำปรึกษา ทาลัสซีเมีย ก่อนเจาะเลือด');
insert into f_nhso_concounseling values ('04','การให้คำปรึกษาอื่น ก่อนเจาะเลือด');
insert into f_nhso_concounseling values ('05','การให้คำปรึกษาหลังเจาะเลือด');
insert into f_nhso_concounseling values ('06','การให้คำปรึกษา HIV หลังเจาะเลือด');
insert into f_nhso_concounseling values ('07','การให้คำปรึกษา ทาลัสซีเมีย หลังเจาะเลือด');
insert into f_nhso_concounseling values ('08','การให้คำปรึกษาอื่น หลังเจาะเลือด');
insert into f_nhso_concounseling values ('09','ให้คำปรึกษาโรคเรื้อรัง');
insert into f_nhso_concounseling values ('10','ให้คำปรึกษาความเครียด');
insert into f_nhso_concounseling values ('11','ให้คำปรึกษาการใช้ยา');
insert into f_nhso_concounseling values ('12','การให้คำปรึกษา counseling');

--การตรวจหลังคลอด
CREATE TABLE t_nhso_postpartum (
	t_nhso_postpartum_id character varying(255),
	t_health_postpartum_id character varying(255),
	health_postpartum_exam character varying(255),
	health_postpartum_guide character varying(255),
	health_postpartum_active character varying(255),
	CONSTRAINT t_nhso_postpartum_pkey PRIMARY KEY (t_nhso_postpartum_id)
);

--ทันตกรรม
CREATE TABLE t_nhso_dental (
	t_nhso_dental_id character varying(255), 
	t_health_dental_id character varying(255), 
	dental_fluoride character varying(255), 
	dental_enamel character varying(255), 
	dental_active character varying(255), 
	dental_check_dent character varying(255),
	CONSTRAINT t_nhso_dental_pkey PRIMARY KEY (t_nhso_dental_id)
);

--การรับวัคซีน
CREATE TABLE t_nhso_epi (
	t_nhso_epi_id character varying(255),
	t_health_epi_id character varying(255),
	epi_group character varying(255),
	epi_active character varying(255),	
	CONSTRAINT t_nhso_epi_pkey PRIMARY KEY (t_nhso_epi_id)
);

--จับคู่กลุ่มวัคซีน
CREATE TABLE b_nhso_map_epi (
	b_nhso_map_epi_id character varying(255),
	f_nhso_epi_group_id character varying(255),
	b_health_epi_group_id character varying(255),
	CONSTRAINT b_nhso_map_epi_pkey PRIMARY KEY (b_nhso_map_epi_id)
);

--รายการกลุ่มวัคซีน
CREATE TABLE f_nhso_epi_group (
	f_nhso_epi_group_id character varying(255),
	epi_group_name character varying(255),
	CONSTRAINT f_nhso_epi_group_pkey PRIMARY KEY (f_nhso_epi_group_id)
);

insert into f_nhso_epi_group values ('01','BCG');
insert into f_nhso_epi_group values ('02','HB1');
insert into f_nhso_epi_group values ('03','OPV1');
insert into f_nhso_epi_group values ('04','DTP1');
insert into f_nhso_epi_group values ('05','HB2');
insert into f_nhso_epi_group values ('06','OPV2');
insert into f_nhso_epi_group values ('07','DTP2');
insert into f_nhso_epi_group values ('08','OPV3');
insert into f_nhso_epi_group values ('09','DTP3');
insert into f_nhso_epi_group values ('10','HB3');
insert into f_nhso_epi_group values ('11','M');
insert into f_nhso_epi_group values ('12','MMR');
insert into f_nhso_epi_group values ('13','OPV4');
insert into f_nhso_epi_group values ('14','DTP4');
insert into f_nhso_epi_group values ('15','JE1');
insert into f_nhso_epi_group values ('16','JE2');
insert into f_nhso_epi_group values ('17','JE3');
insert into f_nhso_epi_group values ('18','OPV5');
insert into f_nhso_epi_group values ('19','DTP5');
insert into f_nhso_epi_group values ('99','อื่นๆ');

--จับคู่เวชภัณฑ์วางแผนครอบครัว
CREATE TABLE b_nhso_map_fp_drug (
	b_nhso_map_fp_drug_id character varying(255),
	f_nhso_fp_drug_id character varying(255),
	b_health_family_planing_group_id character varying(255),
	CONSTRAINT b_nhso_map_fp_drug_pkey PRIMARY KEY (b_nhso_map_fp_drug_id)
);

--รายการเวชภัณฑ์วางแผนครอบครัว
CREATE TABLE f_nhso_fp_drug (
	f_nhso_fp_drug_id character varying(255),
	fp_drug_name character varying(255),
	CONSTRAINT f_nhso_fp_drug_pkey PRIMARY KEY (f_nhso_fp_drug_id)
);
insert into f_nhso_fp_drug values ('01','ยาเม็ด');
insert into f_nhso_fp_drug values ('02','ยาฉีด');
insert into f_nhso_fp_drug values ('03','หมันชาย');
insert into f_nhso_fp_drug values ('04','หมันหญิง');
insert into f_nhso_fp_drug values ('05','ห่วงอนามัย');
insert into f_nhso_fp_drug values ('06','ยาฝัง');
insert into f_nhso_fp_drug values ('07','ถุงยางอนามัย');
insert into f_nhso_fp_drug values ('99','อื่นๆ');

--ข้อมูลโภชนาการ
CREATE TABLE t_nhso_nutrition (
	t_nhso_nutrition_id character varying(255),
	t_health_nutrition_id character varying(255),
	health_nutrition_weight_growup character varying(255),
	health_nutrition_height_growup character varying(255),
	health_nutrition_assess_grow character varying(255),
	heatth_nutrition_thyroid character varying(255),
	health_nutrition_takecare character varying(255),
	health_nutrition_active character varying(255),
	CONSTRAINT t_nhso_nutrition_pkey PRIMARY KEY (t_nhso_nutrition_id)
);

--ข้อมูลบ้าน
CREATE TABLE t_nhso_home (
	t_nhso_home_id character varying(255), 
	t_health_home_id character varying(255), 
	health_home_trog character varying(255), 
	health_home_soi character varying(255), 
	health_active character varying(255), 
	CONSTRAINT t_nhso_home_pkey PRIMARY KEY (t_nhso_home_id)
);

--ข้อมูลการเยี่ยมบ้านของผู้ป่วย
CREATE TABLE t_nhso_visit_home (
	t_nhso_visit_home_id character varying(255),
	t_health_visit_home_id character varying(255),
	f_visit_home_check_health_id character varying(255),
	visit_home_health_suggest character varying(255),
	visit_home_drugvaccine character varying(255),
	visit_home_other character varying(255),
	visit_home_other_detail character varying(255),
	f_nhso_survey_employee_id character varying(255),
	f_visit_home_disease_id character varying(255),
	visit_home_disease_detail character varying(255),
	visit_home_suggest character varying(255),
	visit_home_physio character varying(255),
	visit_home_wound character varying(255),
	visit_home_ng character varying(255),
	visit_home_urine_tube character varying(255),
	visit_home_neck_pierce character varying(255),
	visit_home_other_activity character varying(255),
	visit_home_other_activity_detail character varying(255),
	visit_home_active character varying(255),
	CONSTRAINT t_nhso_visit_home_pkey PRIMARY KEY (t_nhso_visit_home_id)
);

--ข้อมูลการไปเยี่ยม
CREATE TABLE f_visit_home_check_health (
	f_visit_home_check_health_id character varying(255),
	check_health_description character varying(255),
	CONSTRAINT f_visit_home_check_health_pkey PRIMARY KEY (f_visit_home_check_health_id)
);
insert into f_visit_home_check_health values ('01','ปกติ');
insert into f_visit_home_check_health values ('02','มีปัจจัยเสี่ยง');
insert into f_visit_home_check_health values ('03','เป็นโรค');

--เจ้าหน้าที่ผู้ให้บริการ
CREATE TABLE f_nhso_survey_employee (
	f_nhso_survey_employee_id character varying(255),
	survey_employee_description character varying(255),
	CONSTRAINT f_nhso_survey_employee_pkey PRIMARY KEY (f_nhso_survey_employee_id)
);
insert into f_nhso_survey_employee values ('00','ไม่ระบุ');
insert into f_nhso_survey_employee values ('01','พยาบาล');
insert into f_nhso_survey_employee values ('02','เจ้าหน้าที่สาธารณสุข');
insert into f_nhso_survey_employee values ('03','แพทย์');
insert into f_nhso_survey_employee values ('04','นักกายภาพบำบัด');
insert into f_nhso_survey_employee values ('99','อื่นๆ');

--โรคที่เป็น
CREATE TABLE f_visit_home_disease (
	f_visit_home_disease_id character varying(255),
	disease_description character varying(255),
	CONSTRAINT f_visit_home_disease_pkey PRIMARY KEY (f_visit_home_disease_id)
);
insert into f_visit_home_disease values ('00','ไม่ระบุ');
insert into f_visit_home_disease values ('01','โรคความดันโลหิตสูง(ที่ควบคุมไม่ได้)');
insert into f_visit_home_disease values ('02','โรคเบาหวาน(ที่ควบคุมไม่ได้),');
insert into f_visit_home_disease values ('03','โรคหลอดเลือดหัวใจ,');
insert into f_visit_home_disease values ('04','เส้นเลือดในสมองตีบตัน(Stroke),');
insert into f_visit_home_disease values ('05','โรคหลอดลมอุดกั้นเรื้อรัง(COPD),');
insert into f_visit_home_disease values ('06','โรคหอบหืด(Asthma),');
insert into f_visit_home_disease values ('07','ผู้ป่วยโรคเอดส์,');
insert into f_visit_home_disease values ('08','วัณโรค,');
insert into f_visit_home_disease values ('09','มารดาและทารกหลังคลอด,');
insert into f_visit_home_disease values ('10','ผู้ป่วยระยะสุดท้าย,');
insert into f_visit_home_disease values ('11','อื่นๆ');

 CREATE TABLE t_nhso_assess_conclusion (
	t_nhso_assess_conclusion_id character varying(255),
	t_nhso_service_id character varying(255),
	f_nhso_assess_conclusion_topic_id character varying(255),
	assess_conclusion_status character varying(255),
	assess_conclusion_notice character varying(255),
	assess_conclusion_record_employee character varying(255),
	assess_conclusion_record_date_time character varying(255),
	t_health_family_id character varying(255),
	CONSTRAINT t_nhso_assess_conclusion_pkey PRIMARY KEY (t_nhso_assess_conclusion_id)
);

CREATE TABLE f_nhso_assess_conclusion_topic (
	f_nhso_assess_conclusion_topic_id character varying(255),
	topic_description character varying(255),
	CONSTRAINT f_nhso_assess_conclusion_topic_pkey PRIMARY KEY (f_nhso_assess_conclusion_topic_id)
);
insert into f_nhso_assess_conclusion_topic values ('01','ประวัติครอบครัว');
insert into f_nhso_assess_conclusion_topic values ('02','พฤติกรรมการสูบบุหรี่');
insert into f_nhso_assess_conclusion_topic values ('03','พฤติกรรมดื่มแอลกอฮอล์');
insert into f_nhso_assess_conclusion_topic values ('04','โรคเบาหวาน');
insert into f_nhso_assess_conclusion_topic values ('05','โรคความดันโลหิตสูง');
insert into f_nhso_assess_conclusion_topic values ('06','โรคหัวใจหลอดเลือด');
insert into f_nhso_assess_conclusion_topic values ('07','การบริโภคอาหาร');
insert into f_nhso_assess_conclusion_topic values ('08','การรับประทานยา');
insert into f_nhso_assess_conclusion_topic values ('09','การออกกำลังกาย');
insert into f_nhso_assess_conclusion_topic values ('10','เครียด');
insert into f_nhso_assess_conclusion_topic values ('11','ดัชนีมวลกาย(BMI)เกิน');
insert into f_nhso_assess_conclusion_topic values ('12','สัดส่วนเอว/สะโพก(Waist-Hip Ratio)เกิน');
insert into f_nhso_assess_conclusion_topic values ('13','ไขมันในเลือดสูง');


INSERT INTO s_nhso_version("s_version_id", "version_number", "version_description", "version_application_number", "version_database_number", "version_update_time")
VALUES('9701000000015','2','Hospital OS, Community Edition','3.13.1048','0.1.251007','2550-10-25 18:00:00');


--t_nhso_patient_payment เก็บข้อมูล
CREATE TABLE t_nhso_patient_payment (
    t_nhso_patient_payment_pid character varying(255) NOT NULL,
    t_nhso_patient_payment_title character varying(255),
    t_nhso_patient_payment_fname character varying(255),
    t_nhso_patient_payment_lname character varying(255),
    t_nhso_patient_payment_sex character varying(255),
    t_nhso_patient_payment_birthdate character varying(255),
   t_nhso_patient_payment_chat character varying(255),
   t_nhso_patient_payment_address character varying(255),
    t_nhso_patient_payment_maininscl character varying(255),
   t_nhso_patient_payment_subinscl character varying(255),
    t_nhso_patient_payment_startdate character varying(255),
    t_nhso_patient_payment_expdate character varying(255),
    t_nhso_patient_payment_hmain character varying(255),
    t_nhso_patient_payment_hsub character varying(255),
    t_nhso_patient_payment_purchaseprovince character varying(255),
    t_nhso_patient_payment_primaryprovince character varying(255),
    t_nhso_patient_payment_status character varying(255),
    t_nhso_patient_payment_carid character varying(255),
    t_nhso_patient_payment_csmbs character varying(255),
    t_nhso_patient_payment_sss character varying(255),
    t_nhso_patient_payment_uw character varying(255),
    t_nhso_patient_payment_frg character varying(255),
    t_nhso_patient_payment_bfc character varying(255),
    t_nhso_patient_payment_age character varying(255),
    t_nhso_patient_payment_paidmodel character varying(255),
    t_nhso_patient_payment_mastercup_id character varying(255),
    t_nhso_patient_payment_count_select character varying(255),
    t_nhso_patient_payment_nrd character varying(255),
    t_nhso_patient_payment_nation character varying(255),
    t_nhso_patient_payment_vet character varying(255),
    t_nhso_patient_payment_primary_sss character varying(255),
    t_nhso_patient_payment_trandate character varying(255),
    t_nhso_patient_payment_status_dola character varying(255),
    t_nhso_patient_payment_hosp_sss character varying(255),
    t_nhso_patient_payment_pvt character varying(255),
    t_nhso_patient_payment_disablility character varying(255),
    t_nhso_patient_payment_startdate_sss character varying(255),
    t_nhso_patient_payment_ssi character varying(255),
    t_nhso_patient_payment_mpid character varying(255),
    t_nhso_patient_payment_fpid character varying(255),
    t_nhso_patient_payment_mname character varying(255),PRIMARY KEY (t_nhso_patient_payment_pid)
);

-- เก็บข้อมูล Url ที่จะดาวโหลด
CREATE TABLE t_nhso_patient_url (
    nhso_patient_url character varying(255) NOT NULL,
    nhso_patient_downloaded character varying(255),
    CONSTRAINT t_nhso_patient_url_pkey PRIMARY KEY (nhso_patient_url)
);

-- สิทธิ์หลัก
CREATE TABLE f_nhso_main_inscl (
     f_nhso_main_inscl_id character varying(255) NOT NULL,
     nhso_main_inscl_name character varying(255),
     nhso_main_seq character varying(255),
     nhso_main_right_id character varying(255),
     nhso_main_right_name character varying(255),
     nhso_main_right_order character varying(255),
     nhso_main_right_main character varying(255),
     nhso_main_right_desc character varying(255),
     nhso_main_used character varying(255),
     CONSTRAINT f_nhso_main_inscl_pkey PRIMARY KEY (f_nhso_main_inscl_id)
);
-- map ยา
CREATE TABLE b_nhso_map_drug (
     b_nhso_map_drug_id character varying(255) NOT NULL,
     f_nhso_drug_id character varying(255),
     b_item_id character varying(255),
     CONSTRAINT b_nhso_map_drug_pkey PRIMARY KEY (b_nhso_map_drug_id)
);
-- ยาหลัก
CREATE TABLE f_nhso_drug (
     f_nhso_drug_id character varying(255) NOT NULL,
     nhso_drug_trade_name character varying(255),
     nhso_drug_register_no character varying(255),
     nhso_drug_name character varying(255),
     nhso_drug_company character varying(255),
     CONSTRAINT f_nhso_drug_pkey PRIMARY KEY (f_nhso_drug_id)
);
-- map สิทธิ์
CREATE TABLE b_nhso_map_plan (
     b_nhso_map_plan_id character varying(255) NOT NULL,
     f_nhso_main_inscl_id character varying(255),
     b_contract_plan_id character varying(255),
     CONSTRAINT b_nhso_map_plan_pkey PRIMARY KEY (b_nhso_map_plan_id)
);
-- ใส่ข้อมูลใน f_nhso_main_inscl
 insert into f_nhso_main_inscl values ('1','UCS','สิทธิประกันสุขภาพถ้วนหน้า(UCS)','1','U','','Y');
 insert into f_nhso_main_inscl values ('2','WEL','สิทธิประกันสุขภาพถ้วนหน้า ประเภทมีสิทธิย่อย(WEL)','4','U','','Y');
 insert into f_nhso_main_inscl values ('3','SSS','สิทธิประกันสังคม','17','S','','Y');
 insert into f_nhso_main_inscl values ('4','OFC','สิทธิข้าราชการ/สิทธิรัฐวิสาหกิจ','20','C','','Y');
 insert into f_nhso_main_inscl values ('5','FRG','สถานะคนไทยในต่างประเทศ','35','O','','Y');
 insert into f_nhso_main_inscl values ('12','SOF','สิทธิประกันสังคมและสิทธิข้าราชการ/สิทธิรัฐวิสาหกิจ','25','S','','Y');
 insert into f_nhso_main_inscl values ('13','BFC','สิทธิข้าราชการการเมือง/นักการเมือง','40','C','','Y');
 insert into f_nhso_main_inscl values ('14','','ไม่มีสิทธิใดๆ','90','N','','Y');
 insert into f_nhso_main_inscl values ('20','NRD','สถานะคนต่างด้าว','70','O','','Y');
 insert into f_nhso_main_inscl values ('21','PBF','สิทธิครูเอกชน/สิทธิข้าราชการการเมือง','80','C','','Y');
 insert into f_nhso_main_inscl values ('22','VET','สิทธิทหารผ่านศึก','71','O','','Y');
 insert into f_nhso_main_inscl values ('23','PSO','สิทธิประกันสังคม/สิทธิครูเอกชน/สิทธิข้าราชการ','81','S','','Y');
 insert into f_nhso_main_inscl values ('24','VOF','สิทธิทหารผ่านศึก/สิทธิข้าราชการ','72','C','','Y');
 insert into f_nhso_main_inscl values ('25','VSS','สิทธิประกันสังคม/สิทธิทหารผ่านศึก','73','S','','Y');
 insert into f_nhso_main_inscl values ('26','VBF','สิทธิทหารผ่านศึก/สิทธิข้าราชการการเมือง','74','C','','Y');
 insert into f_nhso_main_inscl values ('27','VSO','สิทธิประกันสังคม/สิทธิทหารผ่านศึก/สิทธิข้าราชการ','75','S','','Y');
 insert into f_nhso_main_inscl values ('28','VSB','สิทธิประกันสังคม/สิทธิทหารผ่านศึก/สิทธิข้าราชการการเมือง','76','S','','Y');
 insert into f_nhso_main_inscl values ('29','PVT','สิทธิครูเอกชน','77','O','','Y');
 insert into f_nhso_main_inscl values ('30','PSS','สิทธิประกันสังคม/สิทธิครูเอกชน','78','S','','Y');
 insert into f_nhso_main_inscl values ('31','POF','สิทธิครูเอกชน/สิทธิข้าราชการ','79','C','','Y');
 insert into f_nhso_main_inscl values ('32','PSB','สิทธิประกันสังคม/สิทธิครูเอกชน/สิทธิข้าราชการการเมือง','82','S','','Y');
 insert into f_nhso_main_inscl values ('33','VPT','สิทธิครูเอกชน/สิทธิทหารผ่านศึก','83','O','','Y');
 insert into f_nhso_main_inscl values ('34','VPS','สิทธิครูเอกชน/สิทธิประกันสังคม/สิทธิทหารผ่านศึก','84','S','','Y');
 insert into f_nhso_main_inscl values ('35','VPO','สิทธิครูเอกชน/สิทธิทหารผ่านศึก/สิทธิข้าราชการ','85','C','','Y');
 insert into f_nhso_main_inscl values ('36','VPB','สิทธิครูเอกชน/สิทธิทหารผ่านศึก/สิทธิข้าราชการการเมือง','86','S','','Y');
 insert into f_nhso_main_inscl values ('37','SBF','สิทธิประกันสังคมและสิทธิข้าราชการการเมือง/นักการเมือง','31','S','','Y');
 insert into f_nhso_main_inscl values ('38','SSI','สิทธิประกันสังคมกรณีทุพลภาพ','18','S','','Y');
 insert into f_nhso_main_inscl values ('39','SIF','สิทธิประกันสังคมทุพลภาพและสิทธิข้าราชการ/สิทธิรัฐวิสาหกิจ','91','S','','Y');
 insert into f_nhso_main_inscl values ('41','PSI','สิทธิครูเอกชน/สิทธิประกันสังคมแบบทุพพลภาพ','92','S','','Y');
 insert into f_nhso_main_inscl values ('42','VIO','สิทธิประกันสังคมแบบทุพพลภาพ/สิทธิทหารผ่านศึก/สิทธิข้าราชการ','93','S','','Y');
 insert into f_nhso_main_inscl values ('43','VSI','สิทธิประกันสังคมแบบทุพพลภาพ/สิทธิทหารผ่านศึก','94','S','','Y');


update f_nhso_main_inscl set nhso_main_right_id = f_nhso_main_inscl_id, nhso_main_right_name = nhso_main_inscl_name;

-- ใส่ข้อมลใน f_nhso_drug
 insert into f_nhso_drug values ('201020170099999930881327','2A 342/26','BABY GRIPE (5 PAGODAS BRAND)','"GRIPE MIXTURE SOLUTION OR DROPS, ORAL                       "','บริษัท 5 พระเจดีย?โอสถ จํากัด');
 insert into f_nhso_drug values ('101711090003130170581335','1C 36/31','BECLOFORTE INHALER','"BECLOMETHASONE DIPROPIONATE 250 MCG/1 DOSE INHALER,          "','บริษัท แกล็กโซ เวลคัม วิทยาศรม จํากัด');
 insert into f_nhso_drug values ('201100200028488120381336','2C 9/49(NC)','AVANDAMET     (2 MG/1000 MG)','"ROSIGLITAZONE+METFORMIN 2 MG+1 G TABLET, ORAL                "','บริษัท  แกล็กโซสมิทไคล?น (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('100222255004290510281020','1C 125/47','ACICLOVIR 250 MG','"ACYCLOVIR SODIUM 250 MG/1 AMP. OR VIAL INJECTION, POWDER      "','บริษัท คอสม?า เมดิคอล จํากัด');
 insert into f_nhso_drug values ('218030820018098120181341','2B 1/35','ASMETON-S','AMINOPHYLLINE+CHLORPHENIRAMINE+METHOXYPHENAMINE+NOSCAP ','บริษัท เคนยากุ (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('100322000014020320381349','1C 1268/28','5 - FU TAB. 100 KYOWA','"FLUOROURACIL 100 MG/1 CAP. OR TAB. TABLET, ORAL             "','บริษัท  เคียววา ฮัคโค(ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('100410000003520220381730','1C 3/50','ADSEL-5','"SELEGILINE HYDROCHLORIDE 5 MG/1 CAP. OR TAB. TABLET, ORAL     "','บริษัท แคสป?าฟาร?มาซูติคอล (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('100480000002300140281037','1C 14/37','ACTOVEGIN 20% JELLY','DEPROTEINISED HEAMODERIVATIVE OF CALF\'S BLOOD 20 %W/W GEL       ','บริษัท ชีวาเคมิคัลส? จํากัด');
 insert into f_nhso_drug values ('101869000003030420381040','1A 590/44','B 100 MCG TABLET','"CYANOCOBALAMIN 100 MCG/1 CAP. OR TAB. TABLET, ORAL         "','บริษัท ชุมชนเภสัชกรรม จํากัด (มหาชน)');
 insert into f_nhso_drug values ('201110400018135120381040','2A 277/43','B - FORTE TABLET','"VITAMIN B1+B6+B12 250 MG+250 MG+1 MG TABLET, ORAL          "','บริษัท ชุมชนเภสัชกรรม จํากัด (มหาชน)');
 insert into f_nhso_drug values ('100322000011170510181041','1C 243/41','"5 - FLUOROURACIL ""EBEWE"" 5000 MG."','"FLUOROURACIL 5,000 MG/100 ML INJECTION                      "','บริษัท  ชูมิตร 1967 จํากัด');
 insert into f_nhso_drug values ('102075000000660110181043','1C 16/39(N)','ADENOCOR','ADENOSINE 6 MG/2 ML INJECTION                                   ','บริษัท ซาโนฟ??-ซินเตลาโบ (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('219011300019999910181047','2C 51/44','AQSIA','EYE TONICS INJECTION                                          ','บริษัท ซิลลิค ฟาร?มา จํากัด');
 insert into f_nhso_drug values ('100752000004490520381053','1A 40/43','ASPAMOL','"PARACETAMOL 500 MG/1 CAP. OR TAB. TABLET, ORAL            "','บริษัท ซีโนฟาร?ม (ไทย) จํากัด');
 insert into f_nhso_drug values ('101173000001010641581054','1C 30/35','AOSEPT','"HYDROGEN PEROXIDE 30 MG/1 ML SOLUTION, EXTERNAL               "','บริษัท ซีบาวิชั่น (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('201070700019999930781055','2A 199/40','BABI-LYTE','"ORAL ELECTROLYTE SOL. POWDER OR GRANULE, ORAL               "','บริษัท  ซีฟาม จํากัด');
 insert into f_nhso_drug values ('100072000014200420381373','1C 476/40','ARCALION 200','"SULBUTIAMINE 200 MG/1 CAP. OR TAB. TABLET, ORAL               "','บริษัท เซอร?เวียร? (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('101337000003720220381376','1C 16/45','AUTIDINE - 20','"FAMOTIDINE 20 MG/1 CAP. OR TAB. TABLET, ORAL                 "','บริษัท ไซแทคฟาร?มาซูติคอล จํากัด');
 insert into f_nhso_drug values ('100736000003990220781624','1A 77/42','ASPILETS TABLETS','"ASPIRIN 80 MG/1 CAP. OR TAB. TABLET, CHEWABLE             "','บริษัท ไซอเมริกันฟาร?มาซูติเคิ้ลส? จํากัด');
 insert into f_nhso_drug values ('101895000012620420381057','1C 36/41','ADDITIVA VITAMIN C KIWI','"ASCORBIC ACID 1,000 MG/1 CAP. OR TAB. TABLET, ORAL              "','บริษัท  ฐิติรัตน?สานนท? จํากัด');
 insert into f_nhso_drug values ('100302133000590510181480','1C 47/50','ADRIM','DOXORUBICIN HYDROCHLORIDE 10 MG/5 ML INJECTION                ','บริษัท ดาเบอร? ฟาร?มา (ไทยแลนด?) จํากัด');
 insert into f_nhso_drug values ('124837484002160330681481','1C 20/49(N)','BANAN DRY SYRUP','"CEFPODOXIME PROXETIL 50 MG/1 G SYRUP OR SUSPENSION, DRY     "','บริษัท ดีเคเอสเอช (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('100735087004120220181062','1C 292/41','AFLOXAN','PROGLUMETACIN DIMALEATE 150 MG/1 CAP. OR TAB. CAPSULE        ','บริษัท ดีทแฮล?ม จํากัด');
 insert into f_nhso_drug values ('100736000003910120381620','1A 283/44','ASPIRIN - 60','"ASPIRIN 60 MG/1 CAP. OR TAB. TABLET, ORAL                 "','บริษัท ตราเจ็ดดาว จํากัด');
 insert into f_nhso_drug values ('100736000003920120381067','1A 912/29','ASPIRIN TABLETS (FOR CHILDREN)','"ASPIRIN 65 MG/1 CAP. OR TAB. TABLET, ORAL                  "','บริษัท ถ?วยทองโอสถ จํากัด');
 insert into f_nhso_drug values ('101895000013850120381067','1A 2065/29','ASCORBIC ACID TABLETS','"ASCORBIC  ACID 50 MG/1 CAP. OR TAB. TABLET, ORAL            "','บริษัท ถ?วยทองโอสถ จํากัด');
 insert into f_nhso_drug values ('124977000003090420381073','1B 2/40(N)','BASEN TABLETS 0.2','"VOGLIBOSE 0.2 MG/1 CAP. OR TAB. TABLET, ORAL                    "','บริษัท ทาเคดา (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('124977000003140120381073','1B 3/40(N)','BASEN TABLETS 0.3','"VOGLIBOSE 0.3 MG/1 CAP. OR TAB. TABLET, ORAL                    "','บริษัท ทาเคดา (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('219020100017162160681084','2A 34/37','ARCHIFEN','CHLORAMPHENICOL+LIDOCAINE (10 MG+20 MG)/1 ML SOLUTION OR      ','บริษัท ที.พี.ดรัก แลบบอราทอรี่ส?(1969) จํากัด');
 insert into f_nhso_drug values ('140532000003850120181706','1C 3/44(N)','ARTRODAR','DIACEREIN 50 MG/1 CAP. OR TAB. CAPSULE                        ','บริษัท ทีอาร?บี เชอร?เม็ดดิก?า (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('100072000014200420381079','1A 368/49','ACTIMIN 200','"SULBUTIAMINE 200 MG/1 CAP. OR TAB. TABLET, ORAL                 "','บริษัท ที.โอ.เคมีคอลส? (1979) จํากัด');
 insert into f_nhso_drug values ('100354000020531430581081','1A 574/38','BACOTAN SYRUP','HYOSCINE-N-BUTYLBROMIDE 5 MG/5 ML SYRUP                         ','บริษัท ที.โอ.ฟาร?ม?า จํากัด');
 insert into f_nhso_drug values ('100176302004290620381080','1A 633/42','AMALIN 250','"AMOXYCILLIN TRIHYDRATE 250 MG/1 CAP. OR TAB. TABLET, ORAL   "','บริษัท ที.โอ.แลบ จํากัด');
 insert into f_nhso_drug values ('201110530029999920381386','2C 37/44','B - 50','"VITAMIN B COMPLEX+AMINO ACID+FOLIC ACID TABLET, ORAL       "','บริษัท โททอล แคร? จํากัด (สาขา 1)');
 insert into f_nhso_drug values ('100070000004200420381393','1A 393/46','ANABEN','"ALBENDAZOLE 200 MG/1 CAP. OR TAB. TABLET, ORAL               "','บริษัท ไทยแจแปนแลบบอเรเตอรี่ส? จํากัด');
 insert into f_nhso_drug values ('211010220017537110181398','2A 483/30','5% DEXTROSE AND 0.225% SODIUM','DEXTROSE+SODIUM CHLORIDE (5 G+0.225 G)/100 ML INJECTION     ','บริษัท ไทยนครพัฒนา จํากัด');
 insert into f_nhso_drug values ('218030220047916120381389','2A 207/28','ASMOPHEN 84','THEOPHYLLINE+CHLORPHENIRAMINE+PHENYLEPHRINE 130 MG+2      ','บริษัท ไทย พี.ดี.เคมีคอล จํากัด');
 insert into f_nhso_drug values ('100994022001170610181408','1A 1735/30','5% DEXTROSE       INJECTION B.P.','DEXTROSE ANHYDROUS 5 G/100 ML INJECTION                 ','บริษัท ไทยโอซูก?า จํากัด');
 insert into f_nhso_drug values ('100752000000940330581095','1A 864/30','ACETOPHEN SYRUP','PARACETAMOL 120 MG/5 ML SYRUP                               ','บริษัท นิด?า ฟาร?มา อินคอร?ปอเรชั่น จํากัด');
 insert into f_nhso_drug values ('101158133003610420381099','1A 844/41','BELEN','"BROMHEXINE HYDROCHLORIDE 8 MG/1 CAP. OR TAB. TABLET, ORAL   "','บริษัท นิวไลฟ?ฟาร?ม?า จํากัด');
 insert into f_nhso_drug values ('201110520019999920881107','2C 58/47','BEGROCIT EFFERVESCENT TABLETS','"VITAMIN B COMPLEX+VITAMIN C TABLET, EFFERVESCENT            "','บริษัท นีโอฟาร?ม จํากัด');
 insert into f_nhso_drug values ('101527000000921010181412','1C 7/36','ATG-FRESENIUS','IMMUNOGLOBULIN 20 MG/1 ML INJECTION                           ','บริษัท แนชเชอเริลมีเดีย จํากัด');
 insert into f_nhso_drug values ('100653133003750120381414','1C 552/29','APRESOLINE 25','"HYDRALAZINE HYDROCHLORIDE 25 MG/1 CAP. OR TAB. TABLET, ORAL   "','บริษัท โนวาร?ตีส (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('101445149014990210181415','1C 342/33','ACTRAPID HM 100 I.U./ML.','"INSULIN HUMAN, MONOCOMPONENT 100 UNIT/1 ML INJECTION            "','บริษัท โนโวนอร?ดิสค?ฟาร?มา (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('100203000002620110281109','1C 126/37','AZACTAM','"AZTREONAM 1 G/1 AMP. OR VIAL INJECTION, POWDER                 "','บริษัท บริสตอล-ไมเยอร? สควิบบ?(ประเทศไทย)จํากัด');
 insert into f_nhso_drug values ('219011200019999960581110','2C 12/43','BAUSCH & LOMB SENSITIVE EYES SALINE','"CONTACT LENS PREPARATION SOLUTION OR DROPS, EYE              "','บริษัท บอช แอนด? ลอมบ? (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('101761056003120420981113','1A 724/42','BAIDY LOZENGES ORANGE','DEQUALINIUM CHLORIDE 0.25 MG/1 CAP. OR TAB. LOZENGE OR          ','บริษัท บางกอกแล็ป แอนด? คอสเมติค จํากัด');
 insert into f_nhso_drug values ('100764999004290620181725','1C 97/47','ARTRONIL','GLUCOSAMINE SULFATE SODIUM CHLORIDE 250 MG/1 CAP. OR TAB.     ','บริษัท บีเจซี เทรดดิ้ง จํากัด');
 insert into f_nhso_drug values ('100997204001170310181124','1C 274/47','5% GLUCOSE INTRAVENOUS INFUSION','GLUCOSE MONOHYDRATE 5 %W/V INJECTION                    ','บริษัท บี.บราวน? (ประเทศไทย)จํากัด');
 insert into f_nhso_drug values ('100736000004350220381122','1A 371/29','ACTORIN','"ASPIRIN 325 MG/1 CAP. OR TAB. TABLET, ORAL                      "','บริษัท บี.เอ็ล.ฮั้ว จํากัด');
 insert into f_nhso_drug values ('201110510019999910181126','2A 232/33','B100 COMPLEX H.C.','VITAMIN B COMPLEX INJECTION                                  ','บริษัท  บุคคโลเทรดดิ้ง จํากัด');
 insert into f_nhso_drug values ('100736000004350220481130','1A 168/32','A.S.A. 325','"ASPIRIN 325 MG/1 CAP. OR TAB. TABLET, ENTERIC COATED         "','บริษัท บูรพาโอสถ จํากัด');
 insert into f_nhso_drug values ('100715000004290620381130','1A 749/38','ANAGAN','"MEFENAMIC ACID 250 MG/1 CAP. OR TAB. TABLET, ORAL            "','บริษัท บูรพาโอสถ จํากัด');
 insert into f_nhso_drug values ('100736000004320120381130','1A 557/43','ASPACO 300','"ASPIRIN 300 MG/1 CAP. OR TAB. TABLET, ORAL                "','บริษัท บูรพาโอสถ จํากัด');
 insert into f_nhso_drug values ('100722000004200420381419','1A 52/35','BEFEN 200 TABLETS','"IBUPROFEN 200 MG/1 CAP. OR TAB. TABLET, ORAL                "','บริษัท เบสซี่แอรอน จํากัด');
 insert into f_nhso_drug values ('100551464003850310281423','1C 260/31','ACTILYSE','RECOMBINANT HUMAN TISSUE-TYPE PLASMINOGEN ACTIVATED 50          ','บริษัท เบอริงเกอร? อินเกลไฮม? (ไทย) จํากัด');
 insert into f_nhso_drug values ('100672000000800410181421','1C 153/32','AETHOXYSKLEROL 1%','POLIDOCANOL 1 G/100 ML INJECTION                             ','บริษัท เบอร?ลินฟาร?มาซูติคอลอินดัสตรี้ จํากัด');
 insert into f_nhso_drug values ('101174009002940120381422','1C 67/38','AFTACH','"TRIAMCINOLONE ACETONIDE 25 MCG/1 CAP. OR TAB. TABLET, ORAL   "','บริษัท เบอร?ลี่ ยุคเกอร? จํากัด (มหาชน)');
 insert into f_nhso_drug values ('100994022001170610181424','1C 12/44','5% DEXTROSE       IN WATER SOLUTION FOR','DEXTROSE ANHYDROUS 5 G/100 ML INJECTION                 ','บริษัท แบ็กซ?เตอร?เฮลธ?แคร? (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('100689000000190170481424','1C 174/44','AERRANE','ISOFLURANE 100 %V/V INHALATION                                ','บริษัท แบ็กซ?เตอร?เฮลธ?แคร? (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('100667000003520220181429','1C 931/29','ADALAT 5','NIFEDIPINE 5 MG/1 CAP. OR TAB. CAPSULE                          ','บริษัท ไบเออร?ไทย จํากัด');
 insert into f_nhso_drug values ('101902000002830121181433','1C 174/47','ALPHADOL','"ALFACALCIDOL 0.25 MCG/1 CAP. OR TAB. CAPSULE, SOFT         "','บริษัท ไบโอจีนีเทค จํากัด');
 insert into f_nhso_drug values ('100302133000590510181434','1C 323/41','ADRIM','DOXORUBICIN HYDROCHLORIDE 10 MG/5 ML INJECTION                ','บริษัท ไบโอไซน? จํากัด');
 insert into f_nhso_drug values ('100663133003910120381436','1C 64/38','ALTIAZEM','"DILTIAZEM HYDROCHLORIDE 60 MG/1 CAP. OR TAB. TABLET, ORAL  "','บริษัท  ไบโอฟาร?ม เคมิคัลส? จํากัด');
 insert into f_nhso_drug values ('100751133003850120181438','1A 488/36','ANALAB CAPSULES','TRAMADOL HYDROCHLORIDE 50 MG/1 CAP. OR TAB. CAPSULE          ','บริษัท ไบโอแลป จํากัด');
 insert into f_nhso_drug values ('100354000013620120381133','1A 627/42','BATOCINE','"HYOSCINE BUTYLBROMIDE 10 MG/1 CAP. OR TAB. TABLET, ORAL         "','บริษัท ปรูฟฟ? จํากัด');
 insert into f_nhso_drug values ('100099000003850310281441','1C 135/46','AMPHOTERICIN B FOR INJECTION U.S.P.','"AMPHOTERICIN B 50 MG/1 AMP. OR VIAL INJECTION, POWDER      "','บริษัท แปซิฟ?ค เฮลธ?แคร?(ไทยแลนด?)จํากัด');
 insert into f_nhso_drug values ('101761056003120420981442','1A 177/32','BARON','DEQUALINIUM CHLORIDE 0.25 MG/1 CAP. OR TAB. LOZENGE OR          ','บริษัท โปรเกรสแลบ (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('100006000004320120381445','1A 479/33','APURINOL 300','"ALLOPURINOL 300 MG/1 CAP. OR TAB. TABLET, ORAL                "','บริษัท  โปลิฟาร?ม จํากัด');
 insert into f_nhso_drug values ('218050100188396120381153','2A 139/28','ACETACOL','PARACETAMOL+CHLORPHENIRAMINE+PHENYLPROPANOLAMINE 500        ','บริษัท พี.พี.แลบอราตอรี่ส? จํากัด');
 insert into f_nhso_drug values ('100558135001100710181483','1C 37/49','4% LIDOCAINE HYDROCHLORIDE','LIDOCAINE HYDROCHLORIDE ANHYDROUS 4 %W/V INJECTION          ','บริษัท พีแอล เอเชีย แปซิฟ?ค (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('201110400017853120381163','2A 575/31','B1-6-12','"VITAMIN B1+B6+B12 100 MG+5 MG+50 MCG TABLET, ORAL            "','บริษัท ฟาร?มา ซัพพลาย จํากัด');
 insert into f_nhso_drug values ('100222000000950710181166','1C 206/43','ACICLOVIR INTRAVENOUS INFUSION','ACYCLOVIR 25 MG/1 ML INJECTION                                ','บริษัท  ฟาร?มาเซีย (ประเทศไทย) จํากัด');
 insert into f_nhso_drug values ('100207232002040640281168','1C 247/49','ACNEGON ANTI - ACNE GEL','CLINDAMYCIN PHOSPHATE 1 %W/W GEL                              ','บริษัท  ฟาร?มาแลนด? (1982) จํากัด');
 insert into f_nhso_drug values ('100439000003850120381168','1C 234/49','ATENOLOL TABLETS B.P. 50 MG','"ATENOLOL 50 MG/1 CAP. OR TAB. TABLET, ORAL                    "','บริษัท ฟาร?มาแลนด? (1982) จํากัด');
 insert into f_nhso_drug values ('100736000003920120381169','1A 511/42','ASRINA 65','"ASPIRIN 65 MG/1 CAP. OR TAB. TABLET, ORAL                  "','บริษัท ฟาร?มาสันต?แล็บบอราตอรี่ส?จํากัด');
 insert into f_nhso_drug values ('100942000000780210181484','1A 259/49','0.9% SODIUM CHLORIDE NSS','SODIUM CHLORIDE 0.9 G/100 ML INJECTION                    ','บริษัท ฟาร?ม?า อินโนวา จํากัด');
 insert into f_nhso_drug values ('211010220017540110181453','2C 87/47','0.9% W/V SODIUM CHLORIDE & 5% W/V','DEXTROSE+SODIUM CHLORIDE (5 G+0.9 G)/100 ML INJECTION     ','บริษัท เฟรเซนีอุส คาบี (ไทยแลนด?) จํากัด');
 insert into f_nhso_drug values ('201020140017688130481617','2A 91/46','ALTICON','ALUMINIUM HYDROXIDE+MAGNESIUM HYDROXIDE+SIMETHICONE        ','บริษัทเภสัชกรรม เค.บี. จํากัด');
 insert into f_nhso_drug values ('218020100028171120981456','2A 22/35','BACAL LOZENGES','NEOMYCIN+BACITRACIN+AMYLOCAINE 3.6 MG+100 U+0.5 MG              ','บริษัท เภสัชกรรมนครพัฒนา จํากัด');
 insert into f_nhso_drug values ('201110530039999920381457','2A 94/30','BEEFOLIC','"VITAMIN B COMPLEX+FOLIC ACID TABLET, ORAL                 "','บริษัท เภสัชกรรมศรีประสิทธิ์ จํากัด');
 insert into f_nhso_drug values ('218050100018030120381176','2A 20/38','ACTIL','"TRIPROLIDINE+PSEUDOEPHEDRINE 2.5 MG+60 MG TABLET, ORAL          "','บริษัท มาซา แลบ จํากัด');
 insert into f_nhso_drug values ('100302133000590710181178','1C 142/37','A.D.MYCIN INJECTION 10 MG.','DOXORUBICIN HYDROCHLORIDE 2 MG/1 ML INJECTION                 ','บริษัท มาสุ จํากัด');
 insert into f_nhso_drug values ('101385002001900540181179','1A 171/48','B - DERM','BETAMETHASONE VALERATE 0.1 %W/W CREAM                      ','บริษัท มิลลิเมด จํากัด');
 insert into f_nhso_drug values ('100764998014490520181488','1A 10/49(E)','ARIFLEX','GLUCOSAMINE SULFATE DiPOTASSIUM CHLORIDE 500 MG/1 CAP. OR     ','บริษัท เมก?า ไลฟ?ไซแอ็นซ? จํากัด');
 insert into f_nhso_drug values ('100805133003620120381462','1C 228/34','ANXIOLAN (10)','"BUSPIRONE HYDROCHLORIDE 10 MG/1 CAP. OR TAB. TABLET, ORAL     "','บริษัท เมดไลน? จํากัด');
 insert into f_nhso_drug values ('218030220017589120181463','2A 126/30','ASMACAP CAPSULES','THEOPHYLLINE+GLYCERYL GUAIACOLATE (50 MG+30 MG)/5 ML        ','บริษัท เมดิแคป จํากัด');
 insert into f_nhso_drug values ('100598134004070320381674','1A 157/49','ADOMET','"METHYLDOPA SESQUIHYDRATE 125 MG/1 CAP. OR TAB. TABLET, ORAL   "','บริษัท เมดิซีน โปรดักส? จํากัด');
 insert into f_nhso_drug values ('101208017002780140881674','1A 30/50','ABS TOXINE','"CHARCOAL, ACTIVATED 50 G POWDER, EXTERNAL                     "','บริษัท เมดิซีน โปรดักส? จํากัด');
 insert into f_nhso_drug values ('100702255000950610181738','1C 187/45','ALMIRAL INJECTION','DICLOFENAC SODIUM 75 MG/3 ML INJECTION                        ','บริษัท เมโดเคมี (ไทยแลนด?) จํากัด');
 insert into f_nhso_drug values ('100655133004200420381467','1C 77/42','ARATAC 200','"AMIODARONE HYDROCLORIDE 200 MG/1 CAP. OR TAB. TABLET, ORAL    "','บริษัท เมอร?ค จํากัด');
 insert into f_nhso_drug values ('100722000004510320381470','1A 36/42','AMBUFEN 600','"IBUPROFEN 600  MG/1 CAP. OR TAB. TABLET, ORAL            "','บริษัท  แมคโครฟาร? จํากัด');
 insert into f_nhso_drug values ('100475000003620120381470','1A 392/46','BAFEN','"BACLOFEN 10 MG/1 CAP. OR TAB. TABLET, ORAL                      "','บริษัท แมคโครฟาร? จํากัด');
 insert into f_nhso_drug values ('100722000004410320381470','1A 35/42','AMBUFEN 400','"IBUPROFEN 400  MG/1 CAP. OR TAB. TABLET, ORAL            "','บริษัท  แมคโครฟาร? จํากัด');
 insert into f_nhso_drug values ('100090280001480810181472','1A 151/35','AKACIN-500','AMIKACIN SULFATE 250 MG/1 ML INJECTION                    ','บริษัท โมเดอร?นแมนู จํากัด');
 insert into f_nhso_drug values ('100803082000590530581194','1C 332/42','ATARAX (SYRUP)','HYDROXYZINE   DIHYDROCHLORIDE 10 MG/5 ML SYRUP                ','บริษัท ยูซีบี ฟาร?มา (ไทยแลนด?) จํากัด');
