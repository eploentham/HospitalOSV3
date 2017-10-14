
--ตารางรายชื่อฟิลด์ที่อยู่ในแบบสำรวจ

CREATE TABLE b_health_age_survey (
	b_health_age_survey_id character varying(255) NOT NULL,	
	health_age_survey_start character varying(255) ,
	health_age_survey_end character varying(255) ,
	b_health_survey_id character varying(255),
	health_age_survey_record_date_time character varying(255),
	health_age_survey_modify_date_time character varying(255),
	health_age_survey_cancle_date_time character varying(255),
	health_age_survey_staff_record character varying(255),
	health_age_survey_staff_modify character varying(255),
	health_age_survey_staff_cancle character varying(255),
	health_age_survey_active 	character varying(255),
	health_age_survey_is_lifetime character varying(1)
);

ALTER TABLE public.b_health_age_survey OWNER TO postgres;

--
-- Data for Name: b_health_age_survey; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO b_health_age_survey VALUES ('7810000000001','0','5','7860000000001','','','','','','','1','0');
INSERT INTO b_health_age_survey VALUES ('7810000000002','0','5','7860000000002','','','','','','','1','0');
INSERT INTO b_health_age_survey VALUES ('7810000000003','0','5','7860000000003','','','','','','','1','0');
INSERT INTO b_health_age_survey VALUES ('7810000000004','0','5','7860000000004','','','','','','','1','0');
INSERT INTO b_health_age_survey VALUES ('7810000000005','0','5','7860000000005','','','','','','','1','0');
INSERT INTO b_health_age_survey VALUES ('7810000000006','0','5','7860000000006','','','','','','','1','0');
INSERT INTO b_health_age_survey VALUES ('7810000000007','0','5','7860000000007','','','','','','','1','0');
INSERT INTO b_health_age_survey VALUES ('7810000000008','0','2','7860000000008','','','','','','','1','0');
INSERT INTO b_health_age_survey VALUES ('7810000000009','4','5','7860000000009','','','','','','','1','0');
INSERT INTO b_health_age_survey VALUES ('7810000000010','6','14','7860000000001','','','','','','','1','0');
INSERT INTO b_health_age_survey VALUES ('7810000000011','6','14','7860000000002','','','','','','','1','0');
INSERT INTO b_health_age_survey VALUES ('7810000000012','6','14','7860000000003','','','','','','','1','0');
INSERT INTO b_health_age_survey VALUES ('7810000000013','6','14','7860000000004','','','','','','','1','0');
INSERT INTO b_health_age_survey VALUES ('7810000000014','6','14','7860000000005','','','','','','','1','0');
INSERT INTO b_health_age_survey VALUES ('7810000000015','6','14','7860000000006','','','','','','','1','0');
INSERT INTO b_health_age_survey VALUES ('7810000000016','6','14','7860000000007','','','','','','','1','0');
INSERT INTO b_health_age_survey VALUES ('7810000000017','15','','7860000000001','','','','','','','1','1');
INSERT INTO b_health_age_survey VALUES ('7810000000018','15','','7860000000002','','','','','','','1','1');
INSERT INTO b_health_age_survey VALUES ('7810000000019','15','','7860000000003','','','','','','','1','1');
INSERT INTO b_health_age_survey VALUES ('7810000000020','15','','7860000000011','','','','','','','1','1');
INSERT INTO b_health_age_survey VALUES ('7810000000021','15','','7860000000009','','','','','','','1','1');
INSERT INTO b_health_age_survey VALUES ('7810000000022','15','','7860000000012','','','','','','','1','1');

ALTER TABLE ONLY b_health_age_survey
    ADD CONSTRAINT b_health_age_survey_id_pk PRIMARY KEY (b_health_age_survey_id);

ALTER INDEX public.b_health_age_survey_id_pk OWNER TO postgres;

--ตารางรายชื่อฟิลด์ที่อยู่ในแบบสำรวจ
CREATE TABLE b_health_survey (
b_health_survey_id 	character varying(255)	NOT NULL,
health_survey_description 	character varying(255),
health_survey_active 	character varying(255)
);

ALTER TABLE public.b_health_survey OWNER TO postgres;

--
-- Data for Name: b_health_survey; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO b_health_survey VALUES ('7860000000001', 'ซักประวัติ','1');
INSERT INTO b_health_survey VALUES ('7860000000002','สัมภาษณ์','1');
INSERT INTO b_health_survey VALUES ('7860000000003','ตรวจร่างกาย','1');
INSERT INTO b_health_survey VALUES ('7860000000004','ประเมินพัฒนาการ/พฤติกรรม','1');
INSERT INTO b_health_survey VALUES ('7860000000005','ชั่งน้ำหนัก','1');
INSERT INTO b_health_survey VALUES ('7860000000006','วัดส่วนสูง','1');
INSERT INTO b_health_survey VALUES ('7860000000007','ประเมินภาวะโภชนาการ','1');
INSERT INTO b_health_survey VALUES ('7860000000008', 'วัดเส้นรอบศีรษะ','1');
INSERT INTO b_health_survey VALUES ('7860000000009','วัดความดันโลหิต','1');
INSERT INTO b_health_survey VALUES ('7860000000010','ภาวะโภชนาการ','1');
INSERT INTO b_health_survey VALUES ('7860000000011','ดัชนีมวลกายและเส้นรอบเอว','1');
INSERT INTO b_health_survey VALUES ('7860000000012', 'ตรวจสายตา','1');

ALTER TABLE ONLY b_health_survey
    ADD CONSTRAINT b_health_survey_id_pk PRIMARY KEY (b_health_survey_id);

ALTER INDEX public.b_health_survey_id_pk OWNER TO postgres;


--ตารางการตรวจประเมินสุขภาพ
CREATE TABLE t_health_check_healthy (
	t_health_check_healthy_id character varying(255)	NOT NULL,
	t_health_family_id character varying(255),
	health_family_age character varying(255),
	t_patient_id character varying(255),
	t_visit_id character varying(255),
	health_check_healthy_date character varying(255),
	health_check_healthy_record_date_time character varying(255),
	health_check_healthy_modify_date_time character varying(255),
	health_check_healthy_cancle_date_time character varying(255) ,
	health_check_healthy_staff_record 	character varying(255),
	health_check_healthy_staff_modify 	character varying(255),
	health_check_healthy_staff_cancle 	character varying(255),
	health_check_healthy_active character varying(255),
	b_health_survey_id character varying(255),
	b_health_age_survey_id character varying(255),
	health_check_healthy_result character varying(255),
	health_check_health_age_start character varying(255),
	health_check_healthy_age_end character varying(255),
	health_age_survey_is_lifetime character varying(1) 	
);

ALTER TABLE public.t_health_check_healthy OWNER TO postgres;
ALTER TABLE ONLY t_health_check_healthy
    ADD CONSTRAINT t_health_check_healthy_id_pk PRIMARY KEY (t_health_check_healthy_id);

ALTER INDEX public.t_health_check_healthy_id_pk OWNER TO postgres;


CREATE TABLE t_health_uncontagious (
    t_health_uncontagious_id character varying(255) NOT NULL,
    t_health_family_id character varying(255),
    b_health_disease_id character varying(255),
    t_patient_id character varying(255),
    t_visit_id character varying(255),
    health_uncontagious_icd10_number character varying(255),
    health_uncontagious_get_well character varying(255),
    health_uncontagious_staff_record character varying(255),
    health_uncontagious_staff_modify character varying(255),
    health_uncontagious_staff_cancel character varying(255),
    health_uncontagious_record_date_time character varying(255),
    health_uncontagious_modify_date_time character varying(255),
    health_uncontagious_cancel_date_time character varying(255),
    health_uncontagious_active character varying(255),
    health_uncontagious_survey_date character varying(255),
    health_contagious_type character varying(255)

);
ALTER TABLE public.t_health_uncontagious OWNER TO postgres;
ALTER TABLE ONLY t_health_uncontagious
    ADD CONSTRAINT t_health_uncontagious_id_pk PRIMARY KEY (t_health_uncontagious_id);

ALTER INDEX public.t_health_uncontagious_id_pk OWNER TO postgres;

CREATE TABLE b_health_disease (
    b_health_disease_id character varying(255) NOT NULL,
    health_disease_number character varying(255),
    health_disease_description character varying(255),
    health_disease_common_name character varying(255),    
    health_disease_contagious character varying(255),
    health_disease_active character varying(255)
);
ALTER TABLE public.b_health_disease OWNER TO postgres;
ALTER TABLE ONLY b_health_disease
    ADD CONSTRAINT b_health_disease_id_pk PRIMARY KEY (b_health_disease_id);

ALTER INDEX public.b_health_disease_id_pk OWNER TO postgres;

INSERT INTO b_health_disease VALUES ('7830000000000','99', 'N/A', 'N/A','0','1');

CREATE TABLE t_health_maim (
    t_health_maim_id character varying(255) NOT NULL,
    t_health_family_id character varying(255),
    b_health_maim_id character varying(255),
    t_patient_id character varying(255),
    t_visit_id character varying(255), 
    health_maim_description character varying(255),
    health_maim_treat character varying(255),
    health_maim_registry character varying(255),
    health_maim_staff_record character varying(255),
    health_maim_staff_modify character varying(255),
    health_maim_staff_cancel character varying(255),
    health_maim_record_date_time character varying(255),
    health_maim_modify_date_time character varying(255),
    health_maim_cancel_date_time character varying(255),
    health_maim_active character varying(255),
    health_maim_survey_date character varying(255)
);
ALTER TABLE public.t_health_maim OWNER TO postgres;
ALTER TABLE ONLY t_health_maim
    ADD CONSTRAINT t_health_maim_id_pk PRIMARY KEY (t_health_maim_id);

ALTER INDEX public.t_health_maim_id_pk OWNER TO postgres;

CREATE TABLE b_health_maim (
    b_health_maim_id character varying(255) NOT NULL,
    health_maim_number character varying(255),
    health_maim_description character varying(255),    
    health_maim_active character varying(255)
);
--
-- Data for Name: f_health_survey; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO b_health_maim VALUES ('785000000000000001','01', 'การมองเห็น', '1');
INSERT INTO b_health_maim VALUES ('785000000000000002','02', 'การเคลื่อนไหว', '1');
INSERT INTO b_health_maim VALUES ('785000000000000003','03', 'ทางจิตใจ', '1');
INSERT INTO b_health_maim VALUES ('785000000000000004','04', 'สติปัญญา', '1');

ALTER TABLE public.b_health_maim OWNER TO postgres;
ALTER TABLE ONLY b_health_maim
    ADD CONSTRAINT b_health_maim_id_pk PRIMARY KEY (b_health_maim_id);

ALTER INDEX public.b_health_maim_id_pk OWNER TO postgres;


---ฝากครรภ์
ALTER TABLE t_health_pregnancy ADD COLUMN  t_health_family_id character varying(255) default '';
ALTER TABLE t_health_anc ADD COLUMN  t_health_family_id character varying(255) default '';
ALTER TABLE t_health_anc ADD COLUMN  health_anc_baby_thalassemia character varying(255) default '0';
ALTER TABLE t_health_anc_detail ADD  COLUMN t_health_family_id character varying(255) default '';
ALTER TABLE t_health_pregnancy ADD COLUMN health_pregnancy_survey_date character varying(255) default '';
ALTER TABLE t_health_anc ADD COLUMN health_anc_survey character varying(255) default '';
UPDATE t_health_pregnancy SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_pregnancy.t_patient_id = t_patient.t_patient_id;
UPDATE t_health_anc SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_anc.t_patient_id = t_patient.t_patient_id;
UPDATE t_health_anc_detail SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_anc_detail.t_patient_id = t_patient.t_patient_id;

---ให้คำปรึกษา
ALTER TABLE t_health_counsel ADD COLUMN t_health_family_id character varying(255) default '';
ALTER TABLE t_health_counsel ADD COLUMN counsel_survey_date character varying(255)  default '';
UPDATE t_health_counsel SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_counsel.t_patient_id = t_patient.t_patient_id;

---ข้อมูลการคลอด
ALTER TABLE t_health_delivery ADD COLUMN t_health_family_id character varying(255) default '';
ALTER TABLE t_health_delivery ADD COLUMN health_delivery_survey_date character varying(255) default '';
UPDATE t_health_delivery SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_delivery.t_patient_id = t_patient.t_patient_id;

---ทันตกรรม
ALTER TABLE t_health_dental ADD COLUMN t_health_family_id character varying(255) default '';
ALTER TABLE t_health_dental ADD COLUMN dental_survey_date character varying(255)  default '';
UPDATE t_health_dental SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_dental.t_patient_id = t_patient.t_patient_id;

---ฟื้นฟูสมรรถภาพ
ALTER TABLE t_health_efficiency ADD COLUMN t_health_family_id character varying(255) default '';
ALTER TABLE t_health_efficiency ADD COLUMN efficiency_survey_date character varying(255)  default ''; 
UPDATE t_health_efficiency SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_efficiency.t_patient_id = t_patient.t_patient_id;

---รับวัคซีน
ALTER TABLE t_health_epi ADD COLUMN t_health_family_id character varying(255) default '';
ALTER TABLE t_health_epi_detail ADD COLUMN t_health_family_id character varying(255) default '';
ALTER TABLE t_health_epi ADD COLUMN epi_survey_date character varying(255)  default '';
UPDATE t_health_epi SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_epi.t_patient_id = t_patient.t_patient_id;
UPDATE t_health_epi_detail SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_epi_detail.t_patient_id = t_patient.t_patient_id;


---รับวัคซีนจากที่อื่น
ALTER TABLE t_health_epi_outsite ADD COLUMN t_health_family_id character varying(255) default '';
UPDATE t_health_epi_outsite SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_epi_outsite.t_patient_id = t_patient.t_patient_id;

---วางแผนครอบครัว
ALTER TABLE t_health_family_planing ADD COLUMN t_health_family_id character varying(255) default '';
ALTER TABLE t_health_family_planing ADD COLUMN health_family_planing_date character varying(255) default '';
UPDATE t_health_family_planing SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_family_planing.t_patient_id = t_patient.t_patient_id;

---พัฒนาการ
ALTER TABLE t_health_grow ADD COLUMN t_health_family_id character varying(255) default '';
ALTER TABLE t_health_grow_history ADD COLUMN t_health_family_id character varying(255) default '';
ALTER TABLE t_health_grow_history ADD COLUMN grow_survey_date character varying(255)  default '';
UPDATE t_health_grow SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_grow.t_patient_id = t_patient.t_patient_id;
UPDATE t_health_grow_history SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_grow_history.t_patient_id = t_patient.t_patient_id;

---โภชนาการ
ALTER TABLE t_health_nutrition ADD COLUMN t_health_family_id character varying(255) default '';
ALTER TABLE t_health_nutrition ADD COLUMN nutrition_survey_date character varying(255)  default '';
UPDATE t_health_nutrition SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_nutrition.t_patient_id = t_patient.t_patient_id;

---ดูแลหลังคลอดแม่
ALTER TABLE t_health_postpartum ADD COLUMN t_health_family_id character varying(255) default '';
ALTER TABLE t_health_postpartum ADD COLUMN postpartum_survey_date character varying(255) default '';
UPDATE t_health_postpartum SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_postpartum.t_patient_id = t_patient.t_patient_id;

---ข้อมูลเด็กทารก
ALTER TABLE t_health_pp ADD COLUMN t_health_family_id character varying(255) default '';
ALTER TABLE t_health_pp ADD COLUMN pp_survey_date character varying(255)  default '';
UPDATE t_health_pp SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_pp.t_patient_id = t_patient.t_patient_id;

---ดูแลหลังคลอดลูก
ALTER TABLE t_health_pp_care ADD COLUMN t_health_family_id character varying(255) default '';
ALTER TABLE t_health_pp_care ADD COLUMN pp_care_survey_date character varying(255) default '';
UPDATE t_health_pp_care SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_pp_care.t_patient_id = t_patient.t_patient_id;

---ตรวจสุขภาพประจำปี
ALTER TABLE t_health_check_health_year ADD COLUMN t_health_family_id character varying(255) default '';
ALTER TABLE t_health_check_health_year ADD COLUMN health_survey_date character varying(255)  default '';
UPDATE t_health_check_health_year SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_check_health_year.t_patient_id = t_patient.t_patient_id;


---ข้อมูลบุคคล
ALTER TABLE t_health_family ADD COLUMN f_patient_discharge_status_id character varying(255) default '';
ALTER TABLE t_health_family ADD COLUMN patient_discharge_date_time character varying(255) default '';


---เยี่ยมบ้าน
ALTER TABLE t_health_visit_home ADD COLUMN t_health_family_id character varying(255)  default '';
UPDATE t_health_visit_home SET t_health_family_id = t_patient.t_health_family_id FROM t_patient WHERE t_patient.t_health_family_id <> '' AND t_health_visit_home.t_patient_id = t_patient.t_patient_id;

---สร้าง Index ของตาราง t_health_family
CREATE INDEX patient_id ON t_health_family USING btree (t_patient_id);
CREATE INDEX pid ON t_health_family USING btree (patient_pid);
CREATE INDEX primary_key ON t_health_family USING btree (t_health_family_id);

---ข้อมูลเลข sequence ของ HCIS
INSERT INTO b_sequence_data VALUES ('hn_hcis', 'HCIS Number', '000000000','1');

---สร้าง Index ตาราง t_health_agr
CREATE INDEX agr_village_id ON t_health_agr USING btree (t_health_village_id);
CREATE UNIQUE INDEX agr_primary_key ON t_health_agr USING btree (t_health_agr_id);

---สร้าง Index ตาราง t_health_agr_history
CREATE INDEX agr_id ON t_health_agr_history USING btree (t_health_agr_id);
CREATE UNIQUE INDEX agr_history_primary_key ON t_health_agr_history USING btree (t_health_agr_history_id);

---สร้าง Index ตาราง t_health_anc
CREATE INDEX anc_patient_id ON t_health_anc USING btree (t_patient_id);
CREATE UNIQUE INDEX anc_primary_key ON t_health_anc USING btree (t_health_anc_id);
CREATE INDEX anc_family_id ON t_health_anc USING btree (t_health_family_id);
CREATE INDEX anc_visit_id ON t_health_anc USING btree (t_visit_id);
CREATE INDEX anc_pregnancy_id ON t_health_anc USING btree (t_health_pregnancy_id);

---สร้าง Index ตาราง t_health_anc_detail
CREATE INDEX anc_detail_patient_id ON t_health_anc_detail USING btree (t_patient_id);
CREATE UNIQUE INDEX anc_detail_primary_key ON t_health_anc_detail USING btree (t_health_anc_detail_id);
CREATE INDEX anc_detail_family_id ON t_health_anc_detail USING btree (t_health_family_id);
CREATE INDEX anc_detail_visit_id ON t_health_anc_detail USING btree (t_visit_id);
CREATE INDEX anc_detail_pregnancy_id ON t_health_anc_detail USING btree (t_health_pregnancy_id);
CREATE INDEX anc_detail_anc_id ON t_health_anc_detail USING btree (t_health_anc_id);

---สร้าง Index ตาราง t_health_check_body 
CREATE UNIQUE INDEX check_body_primary_key ON t_health_check_body USING btree (t_health_check_body_id);
CREATE UNIQUE INDEX check_body_student_id ON t_health_check_body USING btree (t_health_student_id);
CREATE INDEX check_body_school_id ON t_health_check_body USING btree (t_health_visit_school_id);
CREATE INDEX check_body_office_id ON t_health_check_body USING btree (b_visit_refer_office_id);

---สร้าง Index ตาราง t_health_check_ears 
CREATE UNIQUE INDEX check_ears_primary_key ON t_health_check_ears USING btree (t_health_check_ears_id);
CREATE UNIQUE INDEX check_ears_student_id ON t_health_check_ears USING btree (t_health_student_id);
CREATE INDEX check_ears_school_id ON t_health_check_ears USING btree (t_health_visit_school_id);
CREATE INDEX check_ears_office_id ON t_health_check_ears USING btree (b_visit_refer_office_id);

---สร้าง Index ตาราง t_health_check_eyes 
CREATE UNIQUE INDEX check_eyes_primary_key ON t_health_check_eyes USING btree (t_health_check_eyes_id);
CREATE UNIQUE INDEX check_eyes_student_id ON t_health_check_eyes USING btree (t_health_student_id);
CREATE INDEX check_eyes_school_id ON t_health_check_eyes USING btree (t_health_visit_school_id);
CREATE INDEX check_eyes_office_id ON t_health_check_eyes USING btree (b_visit_refer_office_id);

---สร้าง Index ตาราง t_health_check_fe_blood 
CREATE UNIQUE INDEX check_blood_primary_key ON t_health_check_fe_blood USING btree (t_health_check_fe_blood_id);
CREATE UNIQUE INDEX check_blood_student_id ON t_health_check_fe_blood USING btree (t_health_student_id);
CREATE INDEX check_blood_school_id ON t_health_check_fe_blood USING btree (t_health_visit_school_id);
CREATE INDEX check_blood_office_id ON t_health_check_fe_blood USING btree (b_visit_refer_office_id);

---สร้าง Index ตาราง t_health_check_goiter 
CREATE UNIQUE INDEX check_goiter_primary_key ON t_health_check_goiter USING btree (t_health_check_goiter_id);
CREATE UNIQUE INDEX check_goiter_student_id ON t_health_check_goiter USING btree (t_health_student_id);
CREATE INDEX check_goiter_school_id ON t_health_check_goiter USING btree (t_health_visit_school_id);
CREATE INDEX check_goiter_office_id ON t_health_check_goiter USING btree (b_visit_refer_office_id);

---สร้าง Index ตาราง t_health_check_health_year 
CREATE INDEX check_health_year_patient_id ON t_health_check_health_year USING btree (t_patient_id);
CREATE UNIQUE INDEX check_health_year_primary_key ON t_health_check_health_year USING btree (t_health_check_health_year_id);
CREATE INDEX check_health_year_family_id ON t_health_check_health_year USING btree (t_health_family_id);
CREATE INDEX check_health_year_visit_id ON t_health_check_health_year USING btree (t_visit_id);
CREATE INDEX check_health_year_activity_id ON t_health_check_health_year USING btree (b_health_check_health_year_activity_id);

---สร้าง Index ตาราง t_health_check_healthy 
CREATE INDEX check_healthy_patient_id ON t_health_check_healthy USING btree (t_patient_id);
CREATE INDEX check_healthy_family_id ON t_health_check_healthy USING btree (t_health_family_id);
CREATE INDEX check_healthy_visit_id ON t_health_check_healthy USING btree (t_visit_id);
CREATE INDEX check_healthy_survey_id ON t_health_check_healthy USING btree (b_health_survey_id);
CREATE INDEX check_healthy_age_survey_id ON t_health_check_healthy USING btree (b_health_age_survey_id);

---สร้าง Index ตาราง t_health_check_lice 
CREATE UNIQUE INDEX check_lice_primary_key ON t_health_check_lice USING btree (t_health_check_lice_id);
CREATE UNIQUE INDEX check_lice_student_id ON t_health_check_lice USING btree (t_health_student_id);
CREATE INDEX check_lice_school_id ON t_health_check_lice USING btree (t_health_visit_school_id);
CREATE INDEX check_lice_office_id ON t_health_check_lice USING btree (b_visit_refer_office_id);

---สร้าง Index ตาราง t_health_check_nutrition 
CREATE UNIQUE INDEX check_nutrition_primary_key ON t_health_check_nutrition USING btree (t_health_check_nutrition_id);
CREATE UNIQUE INDEX check_nutrition_student_id ON t_health_check_nutrition USING btree (t_health_student_id);
CREATE INDEX check_nutrition_school_id ON t_health_check_nutrition USING btree (t_health_visit_school_id);
CREATE INDEX check_nutrition_office_id ON t_health_check_nutrition USING btree (b_visit_refer_office_id);

---สร้าง Index ตาราง t_health_check_other 
CREATE UNIQUE INDEX other_primary_key ON t_health_check_other USING btree (t_health_check_other_id);
CREATE UNIQUE INDEX other_student_id ON t_health_check_other USING btree (t_health_student_id);
CREATE INDEX other_school_id ON t_health_check_other USING btree (t_health_visit_school_id);
CREATE INDEX other_office_id ON t_health_check_other USING btree (b_visit_refer_office_id);

---สร้าง Index ตาราง t_health_check_student_dental 
CREATE UNIQUE INDEX student_dental_primary_key ON t_health_check_student_dental USING btree (t_health_check_student_dental_id);
CREATE INDEX student_dental_student_id ON t_health_check_student_dental USING btree (t_health_student_id);
CREATE INDEX student_dental_school_id ON t_health_check_student_dental USING btree (t_health_visit_school_id);
CREATE INDEX student_dental_office_id ON t_health_check_student_dental USING btree (b_visit_refer_office_id);

---สร้าง Index ตาราง t_health_check_student_health 
CREATE UNIQUE INDEX student_health_primary_key ON t_health_check_student_health USING btree (t_health_check_student_health_id);
CREATE UNIQUE INDEX student_health_student_id ON t_health_check_student_health USING btree (t_health_student_id);
CREATE INDEX student_health_school_id ON t_health_check_student_health USING btree (t_health_visit_school_id);
CREATE INDEX student_health_office_id ON t_health_check_student_health USING btree (b_visit_refer_office_id);

---สร้าง Index ตาราง t_health_check_thalassemia 
CREATE UNIQUE INDEX thalassemia_primary_key ON t_health_check_thalassemia USING btree (t_health_check_thalassemia_id);
CREATE UNIQUE INDEX thalassemia_student_id ON t_health_check_thalassemia USING btree (t_health_student_id);
CREATE INDEX thalassemia_school_id ON t_health_check_thalassemia USING btree (t_health_visit_school_id);
CREATE INDEX thalassemia_office_id ON t_health_check_thalassemia USING btree (b_visit_refer_office_id);

---สร้าง Index ตาราง t_health_check_worm 
CREATE UNIQUE INDEX worm_primary_key ON t_health_check_worm USING btree (t_health_check_worm_id);
CREATE UNIQUE INDEX worm_student_id ON t_health_check_worm USING btree (t_health_student_id);
CREATE INDEX worm_school_id ON t_health_check_worm USING btree (t_health_visit_school_id);
CREATE INDEX worm_office_id ON t_health_check_worm USING btree (b_visit_refer_office_id);

---สร้าง Index ตาราง t_health_company 
CREATE UNIQUE INDEX company_primary_key ON t_health_company USING btree (t_health_company_id);
CREATE INDEX company_village_id ON t_health_company USING btree (t_health_village_id);

---สร้าง Index ตาราง t_health_company_history 
CREATE UNIQUE INDEX company_history_primary_key ON t_health_company_history USING btree (t_health_company_history_id);
CREATE INDEX company_history_company_id ON t_health_company_history USING btree (t_health_company_id);
CREATE INDEX company_history_company_type_id ON t_health_company_history USING btree (b_health_company_type_id);

---สร้าง Index ตาราง t_health_counsel 
CREATE UNIQUE INDEX counsel_primary_key ON t_health_counsel USING btree (t_health_counsel_id);
CREATE INDEX counsel_patient_id ON t_health_counsel USING btree (t_patient_id);
CREATE INDEX counsel_family_id ON t_health_counsel USING btree (t_health_family_id);
CREATE INDEX counsel_visit_id ON t_health_counsel USING btree (t_visit_id);
CREATE INDEX counsel_type_id ON t_health_counsel USING btree (b_health_counsel_type_id);

---สร้าง Index ตาราง t_health_delivery 
CREATE UNIQUE INDEX delivery_primary_key ON t_health_delivery USING btree (t_health_delivery_id);
CREATE INDEX delivery_patient_id ON t_health_delivery USING btree (t_patient_id);
CREATE INDEX delivery_family_id ON t_health_delivery USING btree (t_health_family_id);
CREATE INDEX delivery_visit_id ON t_health_delivery USING btree (t_visit_id);
CREATE INDEX delivery_icd10_id ON t_health_delivery USING btree (b_icd10_id);

---สร้าง Index ตาราง t_health_dental 
CREATE UNIQUE INDEX dental_primary_key ON t_health_dental USING btree (t_health_dental_id);
CREATE INDEX dental_patient_id ON t_health_dental USING btree (t_patient_id);
CREATE INDEX dental_family_id ON t_health_dental USING btree (t_health_family_id);
CREATE INDEX dental_visit_id ON t_health_dental USING btree (t_visit_id);

---สร้าง Index ตาราง t_health_drug_history 
CREATE UNIQUE INDEX drug_history_primary_key ON t_health_drug_history USING btree (t_health_drug_history_id);
CREATE INDEX drug_history_drug_id ON t_health_drug_history USING btree (t_health_drug_id);

---สร้าง Index ตาราง t_health_drug_stock 
CREATE UNIQUE INDEX drug_stock_primary_key ON t_health_drug_stock USING btree (t_health_drug_stock_id);
CREATE INDEX drug_stock_office_id ON t_health_drug_stock USING btree (b_visit_office_id);
CREATE UNIQUE INDEX drug_stock_item_id ON t_health_drug_stock USING btree (b_item_id);

---สร้าง Index ตาราง t_health_efficiency 
CREATE UNIQUE INDEX efficiency_primary_key ON t_health_efficiency USING btree (t_health_efficiency_id);
CREATE INDEX efficiency_patient_id ON t_health_efficiency USING btree (t_patient_id);
CREATE INDEX efficiency_family_id ON t_health_efficiency USING btree (t_health_family_id);
CREATE INDEX efficiency_visit_id ON t_health_efficiency USING btree (t_visit_id);

---สร้าง Index ตาราง t_health_epi 
CREATE UNIQUE INDEX epi_primary_key ON t_health_epi USING btree (t_health_epi_id);
CREATE INDEX epi_patient_id ON t_health_epi USING btree (t_patient_id);
CREATE INDEX epi_family_id ON t_health_epi USING btree (t_health_family_id);
CREATE INDEX epi_visit_id ON t_health_epi USING btree (t_visit_id);
CREATE INDEX epi_office_id ON t_health_epi USING btree (b_visit_office_id);

---สร้าง Index ตาราง t_health_epi_detail 
CREATE UNIQUE INDEX epi_detail_primary_key ON t_health_epi_detail USING btree (t_health_epi_detail_id);
CREATE INDEX epi_detail_patient_id ON t_health_epi_detail USING btree (t_patient_id);
CREATE INDEX epi_detail_family_id ON t_health_epi_detail USING btree (t_health_family_id);
CREATE INDEX epi_detail_visit_id ON t_health_epi_detail USING btree (t_visit_id);
CREATE INDEX epi_detail_epi_id ON t_health_epi_detail USING btree (t_health_epi_id);
CREATE INDEX epi_detail_epi_set_id ON t_health_epi_detail USING btree (b_health_epi_set_id);

---สร้าง Index ตาราง t_health_epi_outsite 
CREATE UNIQUE INDEX epi_outsite_primary_key ON t_health_epi_outsite USING btree (t_health_epi_outsite_id);
CREATE INDEX epi_outsite_patient_id ON t_health_epi_outsite USING btree (t_patient_id);
CREATE INDEX epi_outsite_family_id ON t_health_epi_outsite USING btree (t_health_family_id);
CREATE INDEX epi_visit_outsite_office_id ON t_health_epi_outsite USING btree (b_visit_office_id);
CREATE INDEX epi_outsite_office_id ON t_health_epi_outsite USING btree (b_epi_outsite_office_id);

---สร้าง Index ตาราง t_health_family 
CREATE INDEX family_home_id ON t_health_family USING btree (t_health_home_id);

---สร้าง Index ตาราง t_health_family_planing 
CREATE UNIQUE INDEX family_planing_primary_key ON t_health_family_planing USING btree (t_health_family_planing_id);
CREATE INDEX family_planing_patient_id ON t_health_family_planing USING btree (t_patient_id);
CREATE INDEX family_planing_family_id ON t_health_family_planing USING btree (t_health_family_id);
CREATE INDEX family_planing_visit_id ON t_health_family_planing USING btree (t_visit_id);

---สร้าง Index ตาราง t_health_grow 
CREATE UNIQUE INDEX grow_primary_key ON t_health_grow USING btree (t_health_grow_id);
CREATE INDEX grow_patient_id ON t_health_grow USING btree (t_patient_id);
CREATE INDEX grow_family_id ON t_health_grow USING btree (t_health_family_id);
CREATE INDEX grow_visit_id ON t_health_grow USING btree (t_visit_id);
CREATE INDEX grow_office_id ON t_health_grow USING btree (b_visit_office_id);

---สร้าง Index ตาราง t_health_grow_history 
CREATE UNIQUE INDEX grow_history_primary_key ON t_health_grow_history USING btree (t_health_grow_history_id);
CREATE INDEX grow_history_patient_id ON t_health_grow_history USING btree (t_patient_id);
CREATE INDEX grow_history_family_id ON t_health_grow_history USING btree (t_health_family_id);
CREATE INDEX grow_history_visit_id ON t_health_grow_history USING btree (t_visit_id);
CREATE INDEX grow_history_grow_id ON t_health_grow_history USING btree (t_health_grow_id);

---สร้าง Index ตาราง t_health_home 
CREATE UNIQUE INDEX home_primary_key ON t_health_home USING btree (t_health_home_id);
CREATE INDEX home_village_id ON t_health_home USING btree (t_health_village_id);
CREATE INDEX home_office_id ON t_health_home USING btree (b_visit_office_id);

---สร้าง Index ตาราง t_health_home_bug_control 
CREATE UNIQUE INDEX bug_control_primary_key ON t_health_home_bug_control USING btree (t_health_home_bug_control_id);
CREATE UNIQUE INDEX bug_control_sub_home_id ON t_health_home_bug_control USING btree (t_health_sub_home_id);

---สร้าง Index ตาราง t_health_home_food_standard 
CREATE UNIQUE INDEX food_primary_key ON t_health_home_food_standard USING btree (t_health_home_food_standard_id);
CREATE UNIQUE INDEX food_sub_home_id ON t_health_home_food_standard USING btree (t_health_sub_home_id);

---สร้าง Index ตาราง t_health_home_herb 
CREATE UNIQUE INDEX herb_primary_key ON t_health_home_herb USING btree (t_health_home_herb_id);
CREATE INDEX herb_home_id ON t_health_home_herb USING btree (t_health_home_id);

---สร้าง Index ตาราง t_health_home_house_standard 
CREATE UNIQUE INDEX house_standard_primary_key ON t_health_home_house_standard USING btree (t_health_home_house_standard_id);
CREATE UNIQUE INDEX house_standard_sub_home_id ON t_health_home_house_standard USING btree (t_health_sub_home_id);

---สร้าง Index ตาราง t_health_home_pet 
CREATE UNIQUE INDEX pet_primary_key ON t_health_home_pet USING btree (t_health_home_pet_id);
CREATE INDEX pet_home_id ON t_health_home_pet USING btree (t_health_home_id);

---สร้าง Index ตาราง t_health_home_water_eradicate 
CREATE UNIQUE INDEX water_eradicate_primary_key ON t_health_home_water_eradicate USING btree (t_health_home_water_eradicate_id);
CREATE UNIQUE INDEX water_sub_home_id ON t_health_home_water_eradicate USING btree (t_health_sub_home_id);


---สร้าง Index ตาราง t_health_maim 
CREATE INDEX maim_patient_id ON t_health_maim USING btree (t_patient_id);
CREATE INDEX maim_family_id ON t_health_maim USING btree (t_health_family_id);
CREATE INDEX maim_visit_id ON t_health_maim USING btree (t_visit_id);

---สร้าง Index ตาราง t_health_market 
CREATE UNIQUE INDEX market_primary_key ON t_health_market USING btree (t_health_market_id);
CREATE INDEX market_village_id ON t_health_market USING btree (t_health_village_id);

---สร้าง Index ตาราง t_health_market_history 
CREATE UNIQUE INDEX market_history_primary_key ON t_health_market_history USING btree (t_health_market_history_id);
CREATE INDEX history_market_id ON t_health_market_history USING btree (t_health_market_id);

---สร้าง Index ตาราง t_health_nutrition 
CREATE UNIQUE INDEX nutrition_primary_key ON t_health_nutrition USING btree (t_health_nutrition_id);
CREATE INDEX nutrition_patient_id ON t_health_nutrition USING btree (t_patient_id);
CREATE INDEX nutrition_family_id ON t_health_nutrition USING btree (t_health_family_id);
CREATE INDEX nutrition_visit_id ON t_health_nutrition USING btree (t_visit_id);
CREATE INDEX nutrition_office_id ON t_health_nutrition USING btree (b_visit_office_id);

---สร้าง Index ตาราง t_health_postpartum 
CREATE UNIQUE INDEX postpartum_primary_key ON t_health_postpartum USING btree (t_health_postpartum_id);
CREATE INDEX postpartum_patient_id ON t_health_postpartum USING btree (t_patient_id);
CREATE INDEX postpartum_family_id ON t_health_postpartum USING btree (t_health_family_id);
CREATE INDEX postpartum_visit_id ON t_health_postpartum USING btree (t_visit_id);
CREATE INDEX postpartum_delivery_id ON t_health_postpartum USING btree (t_health_delivery_id);

---สร้าง Index ตาราง t_health_pp 
CREATE UNIQUE INDEX pp_primary_key ON t_health_pp USING btree (t_health_pp_id);
CREATE INDEX pp_patient_id ON t_health_pp USING btree (t_patient_id);
CREATE INDEX pp_family_id ON t_health_pp USING btree (t_health_family_id);
CREATE INDEX pp_visit_id ON t_health_pp USING btree (t_visit_id);

---สร้าง Index ตาราง t_health_pp_care 
CREATE UNIQUE INDEX pp_care_primary_key ON t_health_pp_care USING btree (t_health_pp_care_id);
CREATE INDEX pp_care_patient_id ON t_health_pp_care USING btree (t_patient_id);
CREATE INDEX pp_care_family_id ON t_health_pp_care USING btree (t_health_family_id);
CREATE INDEX pp_care_visit_id ON t_health_pp_care USING btree (t_visit_id);

---สร้าง Index ตาราง t_health_pregnancy 
CREATE UNIQUE INDEX pregnancy_primary_key ON t_health_pregnancy USING btree (t_health_pregnancy_id);
CREATE INDEX pregnancy_patient_id ON t_health_pregnancy USING btree (t_patient_id);
CREATE INDEX pregnancy_family_id ON t_health_pregnancy USING btree (t_health_family_id);
CREATE INDEX pregnancy_visit_id ON t_health_pregnancy USING btree (t_visit_id);
CREATE INDEX pregnancy_office_id ON t_health_pregnancy USING btree (b_visit_office_id);

---สร้าง Index ตาราง t_health_resource 
CREATE UNIQUE INDEX resource_primary_key ON t_health_resource USING btree (t_health_resource_id);
CREATE INDEX resource_village_id ON t_health_resource USING btree (t_health_village_id);

---สร้าง Index ตาราง t_health_resource_history 
CREATE UNIQUE INDEX resource_history_primary_key ON t_health_resource_history USING btree (t_health_resource_history_id);
CREATE INDEX history_resource_id ON t_health_resource_history USING btree (t_health_resource_id);

---สร้าง Index ตาราง t_health_school 
CREATE UNIQUE INDEX school_primary_key ON t_health_school USING btree (t_health_school_id);
CREATE INDEX school_village_id ON t_health_school USING btree (t_health_village_id);

---สร้าง Index ตาราง t_health_school_history 
CREATE UNIQUE INDEX school_history_primary_key ON t_health_school_history USING btree (t_health_school_history_id);
CREATE INDEX history_school_id ON t_health_school_history USING btree (t_health_school_id);
CREATE INDEX school_history_max_class_id ON t_health_school_history USING btree (b_health_school_max_class_id);

---สร้าง Index ตาราง t_health_student 
CREATE UNIQUE INDEX student_primary_key ON t_health_student USING btree (t_health_student_id);
CREATE INDEX student_visit_school_id ON t_health_student USING btree (t_health_visit_school_id);

---สร้าง Index ตาราง t_health_sub_home 
CREATE UNIQUE INDEX sub_home_primary_key ON t_health_sub_home USING btree (t_health_sub_home_id);
CREATE INDEX sub_home_home_id ON t_health_sub_home USING btree (t_health_home_id);

---สร้าง Index ตาราง t_health_temple 
CREATE UNIQUE INDEX temple_primary_key ON t_health_temple USING btree (t_health_temple_id);
CREATE INDEX temple_village_id ON t_health_temple USING btree (t_health_village_id);

---สร้าง Index ตาราง t_health_temple_history 
CREATE UNIQUE INDEX temple_history_primary_key ON t_health_temple_history USING btree (t_health_temple_history_id);
CREATE INDEX history_temple_id ON t_health_temple_history USING btree (t_health_temple_id);

---สร้าง Index ตาราง t_health_temple_history_detail 
CREATE UNIQUE INDEX history_detail_primary_key ON t_health_temple_history_detail USING btree (t_health_temple_history_detail_id);
CREATE INDEX detail_temple_history_id ON t_health_temple_history_detail USING btree (t_health_temple_history_id);

---สร้าง Index ตาราง t_health_uncontagious 
CREATE INDEX uncontagious_patient_id ON t_health_uncontagious USING btree (t_patient_id);
CREATE INDEX uncontagious_family_id ON t_health_uncontagious USING btree (t_health_family_id);
CREATE INDEX uncontagious_visit_id ON t_health_uncontagious USING btree (t_visit_id);
CREATE INDEX uncontagious_disease_id ON t_health_uncontagious USING btree (b_health_disease_id);

---สร้าง Index ตาราง t_health_village 
CREATE UNIQUE INDEX village_primary_key ON t_health_village USING btree (t_health_village_id);
CREATE INDEX village_location_id ON t_health_village USING btree (b_health_village_location_id);

---สร้าง Index ตาราง t_health_visit_home 
CREATE UNIQUE INDEX visit_home_primary_key ON t_health_visit_home USING btree (t_health_visit_home_id);
CREATE INDEX visit_home_family_id ON t_health_visit_home USING btree (t_health_family_id);
CREATE INDEX visit_home_patient_id ON t_health_visit_home USING btree (t_patient_id);

---สร้าง Index ตาราง t_health_visit_school 
CREATE UNIQUE INDEX visit_school_primary_key ON t_health_visit_school USING btree (t_health_visit_school_id);
CREATE INDEX visit_school_school_id ON t_health_visit_school USING btree (t_health_school_id);

---สร้าง Index ตาราง t_health_water 
CREATE UNIQUE INDEX water_primary_key ON t_health_water USING btree (t_health_water_id);
CREATE INDEX water_village_id ON t_health_water USING btree (t_health_village_id);

---สร้าง Index ตาราง t_health_water_history 
CREATE UNIQUE INDEX water_history_primary_key ON t_health_water_history USING btree (t_health_water_history_id);
CREATE UNIQUE INDEX history_water_id ON t_health_water_history USING btree (t_health_water_id);

--แก้ไข indexใหม่ หนึ่ง
drop index check_body_student_id;
CREATE  INDEX check_body_student_id
  ON t_health_check_body
  USING btree
  (t_health_student_id);

  drop index check_ears_student_id;
  CREATE  INDEX check_ears_student_id
  ON t_health_check_ears
  USING btree
  (t_health_student_id);

 drop index check_eyes_student_id;
  CREATE  INDEX check_eyes_student_id
  ON t_health_check_eyes
  USING btree
  (t_health_student_id);

drop index check_blood_student_id;
  CREATE  INDEX check_blood_student_id
  ON t_health_check_fe_blood
  USING btree
  (t_health_student_id);

drop index check_goiter_student_id;
  CREATE  INDEX check_goiter_student_id
  ON t_health_check_goiter
  USING btree
  (t_health_student_id);

  CREATE UNIQUE INDEX check_healthy_primary_key
  ON t_health_check_healthy
  USING btree
  (t_health_check_healthy_id);

drop index check_lice_student_id;
  CREATE  INDEX check_lice_student_id
  ON t_health_check_lice
  USING btree
  (t_health_student_id);

drop index check_nutrition_student_id;
CREATE  INDEX check_nutrition_student_id
  ON t_health_check_nutrition
  USING btree
  (t_health_student_id);

drop index other_student_id;
  CREATE  INDEX other_student_id
  ON t_health_check_other
  USING btree
  (t_health_student_id);

drop index student_health_student_id;
  CREATE  INDEX student_health_student_id
  ON t_health_check_student_health
  USING btree
  (t_health_student_id);

drop index thalassemia_student_id;
  CREATE  INDEX thalassemia_student_id
  ON t_health_check_thalassemia
  USING btree
  (t_health_student_id);

drop index worm_student_id;
  CREATE  INDEX worm_student_id
  ON t_health_check_worm
  USING btree
  (t_health_student_id);

drop index drug_stock_item_id;
  CREATE  INDEX drug_stock_item_id
  ON t_health_drug_stock
  USING btree
  (b_item_id);

drop index bug_control_sub_home_id;
  CREATE  INDEX bug_control_sub_home_id
  ON t_health_home_bug_control
  USING btree
  (t_health_sub_home_id);

drop index food_sub_home_id;
  CREATE  INDEX food_sub_home_id
  ON t_health_home_food_standard
  USING btree
  (t_health_sub_home_id);

drop index herb_home_id;
  CREATE  INDEX herb_home_id
  ON t_health_home_herb
  USING btree
  (t_health_home_id);

drop index house_standard_sub_home_id;
  CREATE  INDEX house_standard_sub_home_id
  ON t_health_home_house_standard
  USING btree
  (t_health_sub_home_id);

drop index water_sub_home_id;
  CREATE  INDEX water_sub_home_id
  ON t_health_home_water_eradicate
  USING btree
  (t_health_sub_home_id);

CREATE UNIQUE INDEX maim_primary_key
  ON t_health_maim
  USING btree
  (t_health_maim_id);


drop index sub_home_home_id;
CREATE  INDEX sub_home_home_id
  ON t_health_sub_home
  USING btree
  (t_health_home_id);

  CREATE UNIQUE INDEX uncontagious_primary_key
  ON t_health_uncontagious
  USING btree
  (t_health_uncontagious_id);

drop index history_water_id;
CREATE  INDEX history_water_id
  ON t_health_water_history
  USING btree
  (t_health_water_id);

--แก้ไขส่วนของ primary key
alter table t_borrow_film_xray add borrow_to_other varchar(255) default '';
alter table t_borrow_opdcard add borrow_opd_to_other varchar(255) default '';

ALTER TABLE t_health_sub_home ADD PRIMARY KEY (t_health_sub_home_id);
ALTER TABLE b_health_agr_code ADD PRIMARY KEY (b_health_agr_code_id);
ALTER TABLE b_health_agr_type ADD PRIMARY KEY (b_health_agr_type_id);
ALTER TABLE b_health_check_health_year_activity ADD PRIMARY KEY (b_health_check_health_year_activity_id);
ALTER TABLE b_health_company_type ADD PRIMARY KEY (b_health_company_type_id);
ALTER TABLE b_health_epi_group ADD PRIMARY KEY (b_health_epi_group_id);
ALTER TABLE b_health_epi_item ADD PRIMARY KEY (b_health_epi_item_id);
ALTER TABLE b_health_epi_item_drug_setup ADD PRIMARY KEY (b_health_epi_item_drug_setup_id);
ALTER TABLE b_health_family_planing_group ADD PRIMARY KEY (b_health_family_planing_group_id);
ALTER TABLE b_health_family_planing_item ADD PRIMARY KEY (b_health_family_planing_item_id);	 
ALTER TABLE b_health_family_planing_item_drug_setup ADD PRIMARY KEY (b_health_family_planing_item_drug_setup_id);
ALTER TABLE b_health_home_pet_type ADD PRIMARY KEY (b_health_home_pet_type_id);
ALTER TABLE b_health_market_type ADD PRIMARY KEY (b_health_market_type_id);
ALTER TABLE b_health_market_type_lock ADD PRIMARY KEY (b_health_market_type_lock_id);
ALTER TABLE b_health_resource ADD PRIMARY KEY (b_health_resource_id);
ALTER TABLE b_health_service_type ADD PRIMARY KEY (b_health_service_type_id);
ALTER TABLE b_health_temple_personel ADD PRIMARY KEY (b_health_temple_personel_id);
ALTER TABLE b_health_temple_type ADD PRIMARY KEY (b_health_temple_type_id);
ALTER TABLE b_health_village_location ADD PRIMARY KEY (b_health_village_location_id);
ALTER TABLE b_health_water_type ADD PRIMARY KEY (b_health_water_type_id);
ALTER TABLE b_school_class ADD PRIMARY KEY (b_school_class_id);
ALTER TABLE f_health_anc_section ADD PRIMARY KEY (f_health_anc_section_id);
ALTER TABLE f_health_birth_method ADD PRIMARY KEY (f_health_birth_method_id);
ALTER TABLE f_health_check_result ADD PRIMARY KEY (f_health_check_result_id);
ALTER TABLE f_health_community_resorce_owner ADD PRIMARY KEY (f_health_community_resorce_owner_id);
ALTER TABLE f_health_community_standard ADD PRIMARY KEY (f_health_community_standard_id);
ALTER TABLE r_hospital_incup ADD PRIMARY KEY (hospital_incup_id);
ALTER TABLE t_health_agr ADD PRIMARY KEY (t_health_agr_id);
ALTER TABLE t_health_agr_history ADD PRIMARY KEY (t_health_agr_history_id);
ALTER TABLE t_health_anc ADD PRIMARY KEY (t_health_anc_id);
ALTER TABLE t_health_anc_detail ADD PRIMARY KEY (t_health_anc_detail_id);
ALTER TABLE t_health_check_body ADD PRIMARY KEY (t_health_check_body_id);
ALTER TABLE t_health_check_ears ADD PRIMARY KEY (t_health_check_ears_id);
ALTER TABLE t_health_check_eyes ADD PRIMARY KEY (t_health_check_eyes_id);
ALTER TABLE t_health_check_fe_blood ADD PRIMARY KEY (t_health_check_fe_blood_id);
ALTER TABLE t_health_check_goiter ADD PRIMARY KEY (t_health_check_goiter_id);
ALTER TABLE t_health_check_health_year ADD PRIMARY KEY (t_health_check_health_year_id);
ALTER TABLE t_health_check_lice ADD PRIMARY KEY (t_health_check_lice_id);
ALTER TABLE t_health_check_nutrition ADD PRIMARY KEY (t_health_check_nutrition_id);
ALTER TABLE t_health_check_other ADD PRIMARY KEY (t_health_check_other_id);
ALTER TABLE t_health_check_student_dental ADD PRIMARY KEY (t_health_check_student_dental_id);
ALTER TABLE t_health_check_student_health ADD PRIMARY KEY (t_health_check_student_health_id);
ALTER TABLE t_health_check_thalassemia ADD PRIMARY KEY (t_health_check_thalassemia_id);
ALTER TABLE t_health_check_worm ADD PRIMARY KEY (t_health_check_worm_id);
ALTER TABLE t_health_company ADD PRIMARY KEY (t_health_company_id);
ALTER TABLE t_health_company_history ADD PRIMARY KEY (t_health_company_history_id);
ALTER TABLE t_health_counsel ADD PRIMARY KEY (t_health_counsel_id);
ALTER TABLE t_health_delivery ADD PRIMARY KEY (t_health_delivery_id);
ALTER TABLE t_health_dental ADD PRIMARY KEY (t_health_dental_id);
ALTER TABLE t_health_drug_history ADD PRIMARY KEY (t_health_drug_history_id);	
ALTER TABLE t_health_drug_stock ADD PRIMARY KEY (t_health_drug_stock_id);
ALTER TABLE t_health_efficiency ADD PRIMARY KEY (t_health_efficiency_id);
ALTER TABLE t_health_epi ADD PRIMARY KEY (t_health_epi_id);
ALTER TABLE t_health_epi_detail ADD PRIMARY KEY (t_health_epi_detail_id);
ALTER TABLE t_health_epi_outsite ADD PRIMARY KEY (t_health_epi_outsite_id);
ALTER TABLE t_health_family ADD PRIMARY KEY (t_health_family_id);
ALTER TABLE t_health_family_planing ADD PRIMARY KEY (t_health_family_planing_id);
ALTER TABLE t_health_grow ADD PRIMARY KEY (t_health_grow_id);
ALTER TABLE t_health_grow_history ADD PRIMARY KEY (t_health_grow_history_id);
ALTER TABLE t_health_home ADD PRIMARY KEY (t_health_home_id);
ALTER TABLE t_health_home_bug_control ADD PRIMARY KEY (t_health_home_bug_control_id);
ALTER TABLE t_health_home_food_standard ADD PRIMARY KEY (t_health_home_food_standard_id);
ALTER TABLE t_health_home_herb ADD PRIMARY KEY (t_health_home_herb_id);
ALTER TABLE t_health_home_house_standard ADD PRIMARY KEY (t_health_home_house_standard_id);
ALTER TABLE t_health_home_pet ADD PRIMARY KEY (t_health_home_pet_id);
ALTER TABLE t_health_home_water_eradicate ADD PRIMARY KEY (t_health_home_water_eradicate_id);
ALTER TABLE t_health_market ADD PRIMARY KEY (t_health_market_id);
ALTER TABLE t_health_market_history ADD PRIMARY KEY (t_health_market_history_id);
ALTER TABLE t_health_nutrition ADD PRIMARY KEY (t_health_nutrition_id);
ALTER TABLE t_health_postpartum ADD PRIMARY KEY (t_health_postpartum_id);
ALTER TABLE t_health_pp ADD PRIMARY KEY (t_health_pp_id);
ALTER TABLE t_health_pp_care ADD PRIMARY KEY (t_health_pp_care_id);
ALTER TABLE t_health_pregnancy ADD PRIMARY KEY (t_health_pregnancy_id);
ALTER TABLE t_health_resource ADD PRIMARY KEY (t_health_resource_id);
ALTER TABLE t_health_resource_history ADD PRIMARY KEY (t_health_resource_history_id);
ALTER TABLE t_health_school ADD PRIMARY KEY (t_health_school_id);
ALTER TABLE t_health_school_history ADD PRIMARY KEY (t_health_school_history_id);
ALTER TABLE t_health_student ADD PRIMARY KEY (t_health_student_id);
ALTER TABLE t_health_temple ADD PRIMARY KEY (t_health_temple_id);
ALTER TABLE t_health_temple_history ADD PRIMARY KEY (t_health_temple_history_id);
ALTER TABLE t_health_temple_history_detail ADD PRIMARY KEY (t_health_temple_history_detail_id);
ALTER TABLE t_health_village ADD PRIMARY KEY (t_health_village_id);
ALTER TABLE t_health_visit_home ADD PRIMARY KEY (t_health_visit_home_id);
ALTER TABLE t_health_visit_school ADD PRIMARY KEY (t_health_visit_school_id);
ALTER TABLE t_health_water ADD PRIMARY KEY (t_health_water_id);
ALTER TABLE t_health_water_history ADD PRIMARY KEY (t_health_water_history_id);

UPDATE t_patient SET t_health_family_id = t_health_family.t_health_family_id FROM t_health_family WHERE t_health_family.t_patient_id <>'' AND   t_health_family.t_patient_id = t_patient.t_patient_id ;
UPDATE t_patient SET patient_has_home_in_pcu = '1' where t_health_family_id <> '' and t_health_family_id notnull;

--เพิ่มฟิลด์เพื่อเก็บข้อมูลวันที่ย้ายเข้าบ้านของประชากร
alter table t_health_family add column patient_move_in_date_time character varying(255) default '';


--06/06/06  สร้างฟิลด์ให้สามารถบันทึีกที่อยู่ของหมู่บ้านได้  henbe

ALTER TABLE t_health_village ADD COLUMN village_ampur varchar(255) DEFAULT '';
ALTER TABLE t_health_village ADD COLUMN village_changwat varchar(255) DEFAULT '';
ALTER TABLE t_health_village ADD COLUMN village_tambon varchar(255) DEFAULT '' ;

--ปิดแล้วห้ามแก้ไขเป็นอันขาด henbe 06/11/49

INSERT INTO s_health_version VALUES ('9710000000003','3','Health, Community Edition','0.04.0449','0.04.0449','2549-04-30 22:00:00');
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

insert into s_script_update_log values ('pcu','update_pcu_ph5.sql',(select current_date) || ','|| (select current_time),'update PCU ph4 -> ph5');
