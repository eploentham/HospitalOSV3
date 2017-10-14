ALTER TABLE public.t_nhso_vital_sign
	DROP CONSTRAINT t_nhso_vital_sign_pkey CASCADE ;
ALTER TABLE public.t_nhso_strain
	DROP CONSTRAINT t_nhso_strain_pkey CASCADE ;
ALTER TABLE public.t_nhso_risk_factor
	DROP CONSTRAINT t_nhso_risk_factor_pkey CASCADE ;
ALTER TABLE public.t_nhso_food_behavior
	DROP CONSTRAINT t_nhso_food_behavior_pkey CASCADE ;
ALTER TABLE public.t_nhso_food
	DROP CONSTRAINT t_nhso_food_pkey CASCADE ;
ALTER TABLE public.t_nhso_family_history
	DROP CONSTRAINT t_nhso_family_history_pkey CASCADE ;
ALTER TABLE public.t_nhso_family
	DROP CONSTRAINT t_nhso_family_pkey CASCADE ;
ALTER TABLE public.t_nhso_excercise
	DROP CONSTRAINT t_nhso_excercise_pkey CASCADE ;
ALTER TABLE public.t_nhso_dope_drug
	DROP CONSTRAINT t_nhso_dope_drug_pkey CASCADE ;
--ALTER TABLE public.t_nhso_doctor_conclusion_topic_detail
--	DROP CONSTRAINT t_nhso_doctor_conclusion_topic_detail_pkey CASCADE ;
ALTER TABLE public.t_nhso_doctor_conclusion
	DROP CONSTRAINT t_nhso_doctor_conclusion_pkey CASCADE ;
ALTER TABLE public.t_nhso_cause_behavior
	DROP CONSTRAINT t_nhso_cause_behavior_pkey CASCADE ;
ALTER TABLE public.f_nhso_strain_group
	DROP CONSTRAINT f_nhso_strain_group_pkey CASCADE ;
ALTER TABLE public.f_nhso_risk_type
	DROP CONSTRAINT f_nhso_risk_type_pkey CASCADE ;
ALTER TABLE public.f_nhso_risk_status
	DROP CONSTRAINT f_nhso_risk_status_pkey CASCADE ;
ALTER TABLE public.f_nhso_relate_person
	DROP CONSTRAINT f_nhso_relate_person_pkey CASCADE ;
ALTER TABLE public.f_nhso_medication_status
	DROP CONSTRAINT f_nhso_medication_status_pkey CASCADE ;
ALTER TABLE public.f_nhso_food_taste
	DROP CONSTRAINT f_nhso_food_taste_pkey CASCADE ;
ALTER TABLE public.f_nhso_food_source
	DROP CONSTRAINT f_nhso_food_source_pkey CASCADE ;
ALTER TABLE public.f_nhso_food_group
	DROP CONSTRAINT f_nhso_food_group_pkey CASCADE ;
ALTER TABLE public.f_nhso_food_behavior_frequency
	DROP CONSTRAINT f_nhso_food_behavior_frequency_pkey CASCADE ;
ALTER TABLE public.f_nhso_family_job_type
	DROP CONSTRAINT f_nhso_family_job_type_pkey CASCADE ;
ALTER TABLE public.f_nhso_family_income_enough
	DROP CONSTRAINT f_nhso_family_income_enough_pkey CASCADE ;
ALTER TABLE public.f_nhso_excercise_time_period
	DROP CONSTRAINT f_nhso_excercise_time_period_pkey CASCADE ;
ALTER TABLE public.f_nhso_excercise_group
	DROP CONSTRAINT f_nhso_excercise_group_pkey CASCADE ;
ALTER TABLE public.f_nhso_excercise_frequency
	DROP CONSTRAINT f_nhso_excercise_frequency_pkey CASCADE ;
ALTER TABLE public.f_nhso_drug_usual_status
	DROP CONSTRAINT f_nhso_drug_usual_status_pkey CASCADE ;
ALTER TABLE public.f_nhso_drug_type
	DROP CONSTRAINT f_nhso_drug_type_pkey CASCADE ;
ALTER TABLE public.f_nhso_disease_status
	DROP CONSTRAINT f_nhso_disease_status_pkey CASCADE ;
ALTER TABLE public.f_nhso_disease_group
	DROP CONSTRAINT f_nhso_disease_group_pkey CASCADE ;
ALTER TABLE public.f_nhso_cause_behavior_frequency
	DROP CONSTRAINT f_nhso_cause_behavior_frequency_pkey CASCADE ;
ALTER TABLE public.f_nhso_cause_behavior_feeling
	DROP CONSTRAINT f_nhso_cause_behavior_feeling_pkey CASCADE ;
--ALTER TABLE public.f_nhso_doctor_conclusion_topic
--	DROP CONSTRAINT f_nhso_doctor_conclusion_topic_pkey CASCADE ;
ALTER TABLE public.b_nhso_strain_topic
	DROP CONSTRAINT b_nhso_strain_topic_pkey CASCADE ;
ALTER TABLE public.t_nhso_service
	DROP CONSTRAINT t_nhso_service_pkey CASCADE ;
ALTER TABLE t_nhso_copd_treat_guide
	DROP CONSTRAINT t_nhso_copd_treat_guide_pkey CASCADE ;
ALTER TABLE t_nhso_copd_physical_exam
	DROP CONSTRAINT t_nhso_copd_physical_exam_pkey CASCADE ;
ALTER TABLE t_nhso_copd_non_medication
	DROP CONSTRAINT t_nhso_copd_non_medication_pkey CASCADE ;
ALTER TABLE t_nhso_copd_medication
	DROP CONSTRAINT t_nhso_copd_medication_pkey CASCADE ;
ALTER TABLE t_nhso_copd_examine
	DROP CONSTRAINT t_nhso_copd_examine_pkey CASCADE ;
ALTER TABLE t_nhso_copd_diagnosis
	DROP CONSTRAINT t_nhso_copd_diagnosis_pkey CASCADE ;
ALTER TABLE t_nhso_asthma_treat_guide
	DROP CONSTRAINT t_nhso_asthma_treat_guide_pkey CASCADE ;
ALTER TABLE t_nhso_asthma_physical_exam
	DROP CONSTRAINT t_nhso_asthma_physical_exam_pkey CASCADE ;
ALTER TABLE t_nhso_asthma_non_medication
	DROP CONSTRAINT t_nhso_asthma_non_medication_pkey CASCADE ;
ALTER TABLE t_nhso_asthma_medication
	DROP CONSTRAINT t_nhso_asthma_medication_pkey CASCADE ;
ALTER TABLE t_nhso_asthma_examine
	DROP CONSTRAINT t_nhso_asthma_examine_pkey CASCADE ;
ALTER TABLE t_nhso_asthma_diagnosis
	DROP CONSTRAINT t_nhso_asthma_diagnosis_pkey CASCADE ;
ALTER TABLE f_nhso_physical_exam_topic_group
	DROP CONSTRAINT f_nhso_physical_exam_topic_group_pkey CASCADE ;
ALTER TABLE f_nhso_disease_control
	DROP CONSTRAINT f_nhso_disease_control_pkey CASCADE ;
ALTER TABLE f_nhso_copd_physical_exam_topic
	DROP CONSTRAINT f_nhso_copd_physical_exam_topic_pkey CASCADE ;
ALTER TABLE f_nhso_copd_non_medication_topic
	DROP CONSTRAINT f_nhso_copd_non_medication_topic_pkey CASCADE ;
ALTER TABLE f_nhso_copd_medication_topic
	DROP CONSTRAINT f_nhso_copd_medication_topic_pkey CASCADE ;
ALTER TABLE f_nhso_copd_guide_method
	DROP CONSTRAINT f_nhso_copd_guide_method_pkey CASCADE ;
ALTER TABLE f_nhso_copd_examine_topic_group
	DROP CONSTRAINT f_nhso_copd_examine_topic_group_pkey CASCADE ;
ALTER TABLE f_nhso_copd_examine_topic
	DROP CONSTRAINT f_nhso_copd_examine_topic_pkey CASCADE ;
ALTER TABLE f_nhso_copd_comorbidities
	DROP CONSTRAINT f_nhso_copd_comorbidities_pkey CASCADE ;
ALTER TABLE f_nhso_asthma_physical_exam_topic
	DROP CONSTRAINT f_nhso_asthma_physical_exam_topic_pkey CASCADE ;
ALTER TABLE f_nhso_asthma_non_medication_topic
	DROP CONSTRAINT f_nhso_asthma_non_medication_topic_pkey CASCADE ;
ALTER TABLE f_nhso_asthma_medication_topic
	DROP CONSTRAINT f_nhso_asthma_medication_topic_pkey CASCADE ;
ALTER TABLE f_nhso_asthma_guide_method
	DROP CONSTRAINT f_nhso_asthma_guide_method_pkey CASCADE ;
ALTER TABLE f_nhso_asthma_examine_topic
	DROP CONSTRAINT f_nhso_asthma_examine_topic_pkey CASCADE ;
ALTER TABLE f_nhso_asthma_comorbidities
	DROP CONSTRAINT f_nhso_asthma_comorbidities_pkey CASCADE ;
ALTER TABLE b_nhso_map_item_copd_medication
	DROP CONSTRAINT b_nhso_map_item_copd_medication_pkey CASCADE ;
ALTER TABLE b_nhso_map_copd_lab
	DROP CONSTRAINT b_nhso_map_copd_lab_pkey CASCADE ;
ALTER TABLE b_nhso_map_asthma_lab
	DROP CONSTRAINT b_nhso_map_asthma_lab_pkey CASCADE ;
ALTER TABLE b_nhso_map_item_asthma_medication
	DROP CONSTRAINT b_nhso_map_item_asthma_medication_pkey CASCADE ;
ALTER TABLE t_nhso_dmht_physical_exam
	DROP CONSTRAINT t_nhso_dmht_physical_exam_pkey CASCADE ;
ALTER TABLE t_nhso_dmht_history
	DROP CONSTRAINT t_nhso_dmht_history_pkey CASCADE ;
ALTER TABLE t_nhso_dmht_family_history
	DROP CONSTRAINT t_nhso_dmht_family_history_pkey CASCADE ;
ALTER TABLE t_nhso_dmht_diagnosis
	DROP CONSTRAINT t_nhso_dmht_diagnosis_pkey CASCADE ;
ALTER TABLE f_nhso_map_lab_ht_item
	DROP CONSTRAINT f_nhso_map_lab_ht_item_pkey CASCADE ;
ALTER TABLE f_nhso_map_lab_dmht_investigation_item
	DROP CONSTRAINT f_nhso_map_lab_dmht_investigation_item_pkey CASCADE ;
ALTER TABLE f_nhso_map_lab_dm_item
	DROP CONSTRAINT f_nhso_map_lab_dm_item_pkey CASCADE ;
ALTER TABLE f_nhso_ht_diag_type
	DROP CONSTRAINT f_nhso_ht_diag_type_pkey CASCADE ;
ALTER TABLE f_nhso_dmht_summary_group
	DROP CONSTRAINT f_nhso_dmht_summary_group_pkey CASCADE ;
ALTER TABLE f_nhso_dmht_physical_exam_topic_group
	DROP CONSTRAINT f_nhso_dmht_physical_exam_topic_group_pkey CASCADE ;
ALTER TABLE f_nhso_dmht_physical_exam_topic
	DROP CONSTRAINT f_nhso_dmht_physical_exam_topic_pkey CASCADE ;
ALTER TABLE f_nhso_dmht_history_topic
	DROP CONSTRAINT f_nhso_dmht_history_topic_pkey CASCADE ;
ALTER TABLE f_nhso_dmht_comorbidities
	DROP CONSTRAINT f_nhso_dmht_comorbidities_pkey CASCADE ;
ALTER TABLE f_nhso_dm_diag_type
	DROP CONSTRAINT f_nhso_dm_diag_type_pkey CASCADE ;
ALTER TABLE f_nhso_age_range
	DROP CONSTRAINT f_nhso_age_range_pkey CASCADE ;
ALTER TABLE b_nhso_map_lab_ht
	DROP CONSTRAINT b_nhso_map_lab_ht_pkey CASCADE ;
ALTER TABLE b_nhso_map_lab_dmht_investigation
	DROP CONSTRAINT b_nhso_map_lab_dmht_investigation_pkey CASCADE ;
ALTER TABLE b_nhso_map_lab_dm
	DROP CONSTRAINT b_nhso_map_lab_dm_pkey CASCADE ;
ALTER TABLE b_nhso_map_employee_dmht
	DROP CONSTRAINT b_nhso_map_employee_dmht_pkey CASCADE ;
ALTER TABLE f_nhso_dmht_non_medication_topic
	DROP CONSTRAINT f_nhso_dmht_non_medication_topic_pkey CASCADE ;
ALTER TABLE f_nhso_dmht_employee_type
	DROP CONSTRAINT f_nhso_dmht_employee_type_pkey CASCADE ;
ALTER TABLE t_nhso_dmht_non_medication
	DROP CONSTRAINT t_nhso_dmht_non_medication_pkey CASCADE ;	
DROP INDEX t_nhso_service_pkey;
DROP INDEX t_nhso_vital_sign_pkey;
DROP INDEX t_nhso_strain_pkey;
DROP INDEX t_nhso_risk_factor_pkey;
DROP INDEX t_nhso_food_behavior_pkey;
DROP INDEX t_nhso_food_pkey;
DROP INDEX t_nhso_family_history_pkey;
DROP INDEX t_nhso_family_pkey;
DROP INDEX t_nhso_excercise_pkey;
DROP INDEX t_nhso_dope_drug_pkey;
--DROP INDEX t_nhso_doctor_conclusion_topic_detail_pkey;
DROP INDEX t_nhso_doctor_conclusion_pkey;
DROP INDEX t_nhso_cause_behavior_pkey;
DROP INDEX f_nhso_strain_group_pkey;
DROP INDEX f_nhso_risk_type_pkey;
DROP INDEX f_nhso_risk_status_pkey;
DROP INDEX f_nhso_relate_person_pkey;
DROP INDEX f_nhso_medication_status_pkey;
DROP INDEX f_nhso_food_taste_pkey;
DROP INDEX f_nhso_food_source_pkey;
DROP INDEX f_nhso_food_group_pkey;
DROP INDEX f_nhso_food_behavior_frequency_pkey;
DROP INDEX f_nhso_family_job_type_pkey;
DROP INDEX f_nhso_family_income_enough_pkey;
DROP INDEX f_nhso_excercise_time_period_pkey;
DROP INDEX f_nhso_excercise_group_pkey;
DROP INDEX f_nhso_excercise_frequency_pkey;
DROP INDEX f_nhso_drug_usual_status_pkey;
DROP INDEX f_nhso_drug_type_pkey;
DROP INDEX f_nhso_disease_status_pkey;
DROP INDEX f_nhso_disease_group_pkey;
DROP INDEX f_nhso_cause_behavior_frequency_pkey;
DROP INDEX f_nhso_cause_behavior_feeling_pkey;
--DROP INDEX f_nhso_doctor_conclusion_topic_pkey;
DROP INDEX b_nhso_strain_topic_pkey;
DROP TABLE public.t_nhso_service;
DROP TABLE public.t_nhso_vital_sign;
DROP TABLE public.t_nhso_strain;
DROP TABLE public.t_nhso_risk_factor;
DROP TABLE public.t_nhso_food_behavior;
DROP TABLE public.t_nhso_food;
DROP TABLE public.t_nhso_family_history;
DROP TABLE public.t_nhso_family;
DROP TABLE public.t_nhso_excercise;
DROP TABLE public.t_nhso_dope_drug;
--DROP TABLE public.t_nhso_doctor_conclusion_topic_detail;
DROP TABLE public.t_nhso_doctor_conclusion;
DROP TABLE public.t_nhso_cause_behavior;
DROP TABLE public.f_nhso_strain_group;
DROP TABLE public.f_nhso_risk_type;
DROP TABLE public.f_nhso_risk_status;
DROP TABLE public.f_nhso_relate_person;
DROP TABLE public.f_nhso_medication_status;
DROP TABLE public.f_nhso_food_taste;
DROP TABLE public.f_nhso_food_source;
DROP TABLE public.f_nhso_food_group;
DROP TABLE public.f_nhso_food_behavior_frequency;
DROP TABLE public.f_nhso_family_job_type;
DROP TABLE public.f_nhso_family_income_enough;
DROP TABLE public.f_nhso_excercise_time_period;
DROP TABLE public.f_nhso_excercise_group;
DROP TABLE public.f_nhso_excercise_frequency;
DROP TABLE public.f_nhso_drug_usual_status;
DROP TABLE public.f_nhso_drug_type;
DROP TABLE public.f_nhso_disease_status;
DROP TABLE public.f_nhso_disease_group;
DROP TABLE public.f_nhso_cause_behavior_frequency;
DROP TABLE public.f_nhso_cause_behavior_feeling;
--DROP TABLE public.f_nhso_doctor_conclusion_topic;
DROP TABLE public.b_nhso_strain_topic;
DROP TABLE t_nhso_copd_treat_guide;
DROP TABLE t_nhso_copd_physical_exam;
DROP TABLE t_nhso_copd_non_medication;
DROP TABLE t_nhso_copd_medication;
DROP TABLE t_nhso_copd_examine;
DROP TABLE t_nhso_copd_diagnosis;
DROP TABLE t_nhso_asthma_treat_guide;
DROP TABLE t_nhso_asthma_physical_exam;
DROP TABLE t_nhso_asthma_non_medication;
DROP TABLE t_nhso_asthma_medication;
DROP TABLE t_nhso_asthma_examine;
DROP TABLE t_nhso_asthma_diagnosis;
DROP TABLE f_nhso_physical_exam_topic_group;
DROP TABLE f_nhso_disease_control;
DROP TABLE f_nhso_copd_physical_exam_topic;
DROP TABLE f_nhso_copd_non_medication_topic;
DROP TABLE f_nhso_copd_medication_topic;
DROP TABLE f_nhso_copd_guide_method;
DROP TABLE f_nhso_copd_examine_topic_group;
DROP TABLE f_nhso_copd_examine_topic;
DROP TABLE f_nhso_copd_comorbidities;
DROP TABLE f_nhso_asthma_physical_exam_topic;
DROP TABLE f_nhso_asthma_non_medication_topic;
DROP TABLE f_nhso_asthma_medication_topic;
DROP TABLE f_nhso_asthma_guide_method;
DROP TABLE f_nhso_asthma_examine_topic;
DROP TABLE f_nhso_asthma_comorbidities;
DROP TABLE b_nhso_map_item_copd_medication;
DROP TABLE b_nhso_map_copd_lab;
DROP TABLE b_nhso_map_asthma_lab;
DROP TABLE b_nhso_map_item_asthma_medication;
DROP TABLE t_nhso_dmht_physical_exam;
DROP TABLE t_nhso_dmht_history;
DROP TABLE t_nhso_dmht_family_history;
DROP TABLE t_nhso_dmht_diagnosis;
DROP TABLE f_nhso_map_lab_ht_item;
DROP TABLE f_nhso_map_lab_dmht_investigation_item;
DROP TABLE f_nhso_map_lab_dm_item;
DROP TABLE f_nhso_ht_diag_type;
DROP TABLE f_nhso_dmht_summary_group;
DROP TABLE f_nhso_dmht_physical_exam_topic_group;
DROP TABLE f_nhso_dmht_physical_exam_topic;
DROP TABLE f_nhso_dmht_history_topic;
DROP TABLE f_nhso_dmht_comorbidities;
DROP TABLE f_nhso_dm_diag_type;
DROP TABLE f_nhso_age_range;
DROP TABLE b_nhso_map_lab_ht;
DROP TABLE b_nhso_map_lab_dmht_investigation;
DROP TABLE b_nhso_map_lab_dm;
DROP TABLE b_nhso_map_employee_dmht;
DROP TABLE f_nhso_dmht_non_medication_topic;
DROP TABLE f_nhso_dmht_employee_type;
DROP TABLE t_nhso_dmht_non_medication;
DROP TABLE f_nhso_map_item;
DROP TABLE b_nhso_map_item;
DROP TABLE s_nhso_version;

--ตารางประวัติส่วนบุคคล
CREATE TABLE t_nhso_family (
    t_nhso_family_id character varying(255) NOT NULL,
    t_health_family_id character varying(255),
    family_income character varying(255),
    f_nhso_family_income_enough_id character varying(255),
    f_nhso_family_job_type_id character varying(255),
    family_job_name character varying(255),
    t_nhso_service_id character varying(255), 
    family_record_employee character varying(255),
    family_record_date_time character varying(255), 
    CONSTRAINT t_nhso_family_pkey PRIMARY KEY (t_nhso_family_id)
);

--ตารางค่าใช้จ่ายรายเดือน
CREATE TABLE f_nhso_family_income_enough (
    f_nhso_family_income_enough_id character varying(255) NOT NULL,
    income_enough_description character varying(255),
    CONSTRAINT f_nhso_family_income_enough_pkey PRIMARY KEY (f_nhso_family_income_enough_id)
);
insert into f_nhso_family_income_enough values ('01','พอเพียง');
insert into f_nhso_family_income_enough values ('02','ไม่พอเพียง');
insert into f_nhso_family_income_enough values ('03','เป็นหนี้');
insert into f_nhso_family_income_enough values ('04','เหลือเก็บ');

--ตารางลักษณะงาน
CREATE TABLE f_nhso_family_job_type (
    f_nhso_family_job_type_id character varying(255) NOT NULL,
    family_job_type_description character varying(255),
    CONSTRAINT f_nhso_family_job_type_pkey PRIMARY KEY (f_nhso_family_job_type_id)
);
insert into f_nhso_family_job_type values ('01','ทำงานกลางแจ้ง');
insert into f_nhso_family_job_type values ('02','นั่งกับโต๊ะ');
insert into f_nhso_family_job_type values ('03','เคลื่อนไหว เดินไปมาบ้างเล็กน้อย');
insert into f_nhso_family_job_type values ('04','เคลื่อนไหว เดินไปมาส่วนใหญ่');
insert into f_nhso_family_job_type values ('99','อื่นๆ');

--ตารางครอบครัว/ประวัติ
CREATE TABLE t_nhso_family_history (
    t_nhso_family_history_id character varying(255) NOT NULL,
    t_health_family_id character varying(255),
    f_nhso_relate_person_id character varying(255),
    f_nhso_disease_group_id character varying(255),
    f_nhso_disease_status_id character varying(255),
    t_nhso_service_id character varying(255),
    family_history_record_employee character varying(255),
    family_history_record_date_time character varying(255),
    CONSTRAINT t_nhso_family_history_pkey PRIMARY KEY (t_nhso_family_history_id)
);

--ครอบครัว/บุคคลที่เกี่ยวข้อง
CREATE TABLE f_nhso_relate_person (
    f_nhso_relate_person_id character varying(255) NOT NULL,
    relate_person_description character varying(255),
    CONSTRAINT f_nhso_relate_person_pkey PRIMARY KEY (f_nhso_relate_person_id)
);
insert into f_nhso_relate_person values ('01','บิดา');
insert into f_nhso_relate_person values ('02','มารดา');
insert into f_nhso_relate_person values ('03','พี่น้อง');

--กลุ่มโรค
CREATE TABLE f_nhso_disease_group (
    f_nhso_disease_group_id character varying(255) NOT NULL,
    disease_group_description character varying(255),
    CONSTRAINT f_nhso_disease_group_pkey PRIMARY KEY (f_nhso_disease_group_id)
);
insert into f_nhso_disease_group values ('01','โรคเบาหวาน');
insert into f_nhso_disease_group values ('02','ความดันโลหิตสูง');
insert into f_nhso_disease_group values ('03','โรคหัวใจหลอดเลือด');

--สถานะของแต่ละกลุ่มโรค
CREATE TABLE f_nhso_disease_status (
    f_nhso_disease_status_id character varying(255) NOT NULL,
    disease_status_description character varying(255),
    CONSTRAINT f_nhso_disease_status_pkey PRIMARY KEY (f_nhso_disease_status_id)
);
insert into f_nhso_disease_status values ('01','มี');
insert into f_nhso_disease_status values ('02','ไม่มี');
insert into f_nhso_disease_status values ('03','ไม่ทราบ');

--พฤติกรรมเสี่ยง
CREATE TABLE t_nhso_risk_factor (
    t_nhso_risk_factor_id character varying(255) NOT NULL,
    t_health_family_id character varying(255),
    f_nhso_medication_status_id character varying(255),
    risk_factor_medication_name character varying(255),
    f_nhso_risk_type_id character varying(255),
    f_nhso_risk_status_id character varying(255),
    t_nhso_service_id character varying(255),
    risk_factor_record_employee character varying(255),
    risk_factor_record_date_time character varying(255),
    CONSTRAINT t_nhso_risk_factor_pkey PRIMARY KEY (t_nhso_risk_factor_id)
);

--วิธีการรักษา
CREATE TABLE f_nhso_medication_status (
    f_nhso_medication_status_id character varying(255) NOT NULL,
    medication_status_description character varying(255),
    CONSTRAINT f_nhso_medication_status_pkey PRIMARY KEY (f_nhso_medication_status_id)
);
insert into f_nhso_medication_status values ('01','สม่ำเสมอตามแพทย์นัด');
insert into f_nhso_medication_status values ('02','ไม่แน่นอน');
insert into f_nhso_medication_status values ('03','หายแล้ว');
insert into f_nhso_medication_status values ('99','อื่นๆ');

--กิจกรรมที่เกิดปัญหา
CREATE TABLE f_nhso_risk_type (
    f_nhso_risk_type_id character varying(255) NOT NULL,
    risk_type_description character varying(255),
    CONSTRAINT f_nhso_risk_type_pkey PRIMARY KEY (f_nhso_risk_type_id)
);
insert into f_nhso_risk_type values ('01','ตรวจสุขภาพประจำปี');
insert into f_nhso_risk_type values ('02','อ้วนในวัยเด็ก');
insert into f_nhso_risk_type values ('03','โรคเบาหวาน');
insert into f_nhso_risk_type values ('04','ความดันโลหิตสูง');
insert into f_nhso_risk_type values ('05','โรคหัวใจและหลอดเลือด');
insert into f_nhso_risk_type values ('06','โรคตับ');

--สถานะของกิจกรรม
CREATE TABLE f_nhso_risk_status (
    f_nhso_risk_status_id character varying(255) NOT NULL,
    risk_status_description character varying(255),
    CONSTRAINT f_nhso_risk_status_pkey PRIMARY KEY (f_nhso_risk_status_id)
);
insert into f_nhso_risk_status values ('01','มี');
insert into f_nhso_risk_status values ('02','ไม่มี');
insert into f_nhso_risk_status values ('03','ไม่ทราบ');

--การออกกำลังกาย
CREATE TABLE t_nhso_excercise (
    t_nhso_excercise_id character varying(255) NOT NULL,
    t_health_family_id character varying(255),
    excercise_is_usually character varying(255),
    f_nhso_excercise_frequency_id character varying(255),
    excercise_time_week character varying(255),
    f_nhso_excercise_time_period_id character varying(255),
    excercise_minute_time character varying(255),
    f_nhso_excercise_group_id character varying(255),
    excercise_name character varying(255),
    excercise_record_employee character varying(255),
    excercise_record_date_time character varying(255),
    t_nhso_service_id character varying(255),
    CONSTRAINT t_nhso_excercise_pkey PRIMARY KEY (t_nhso_excercise_id)
);

--ความถี่ในการออกำลังกาย
CREATE TABLE f_nhso_excercise_frequency (
    f_nhso_excercise_frequency_id character varying(255) NOT NULL,
    excercise_frequency_description character varying(255),
    CONSTRAINT f_nhso_excercise_frequency_pkey PRIMARY KEY (f_nhso_excercise_frequency_id)
);
insert into f_nhso_excercise_frequency values ('01','ทุกวัน');
insert into f_nhso_excercise_frequency values ('02','มากกว่า 2 ครั้ง/สัปดาห์');
insert into f_nhso_excercise_frequency values ('03','ทุกวัน');

--เวลาการออกกำลังกาย
CREATE TABLE f_nhso_excercise_time_period (
    f_nhso_excercise_time_period_id character varying(255) NOT NULL,
    excercise_time_period_description character varying(255),
    CONSTRAINT f_nhso_excercise_time_period_pkey PRIMARY KEY (f_nhso_excercise_time_period_id)
);
insert into f_nhso_excercise_time_period values ('01','น้อยกว่า 30 นาที/ครั้ง');
insert into f_nhso_excercise_time_period values ('02','ตั้งแต่ 30 นาทีขึ้นไป/ครั้ง');
insert into f_nhso_excercise_time_period values ('03','ไม่แน่นอน');

--ประเภทการออกกำลังกาย
CREATE TABLE f_nhso_excercise_group (
    f_nhso_excercise_group_id character varying(255) NOT NULL,
    excercise_group_description character varying(255),
    CONSTRAINT f_nhso_excercise_group_pkey PRIMARY KEY (f_nhso_excercise_group_id)
);
insert into f_nhso_excercise_group values ('01','วิ่ง');
insert into f_nhso_excercise_group values ('02','เดิน');
insert into f_nhso_excercise_group values ('99','อื่นๆ');

--การบริโภคอาหาร/รับประทานยา
CREATE TABLE t_nhso_food (
    t_nhso_food_id character varying(255) NOT NULL,
    t_health_family_id character varying(255),
    f_nhso_food_source_id character varying(255),
    f_nhso_food_taste_id character varying(255),
    food_taste_other character varying(255),
    food_record_employee character varying(255),
    food_record_date_time character varying(255),
    t_nhso_service_id character varying(255),
    CONSTRAINT t_nhso_food_pkey PRIMARY KEY (t_nhso_food_id)
);

--แหล่งอาหาร
CREATE TABLE f_nhso_food_source (
    f_nhso_food_source_id character varying(255) NOT NULL,
    food_source_description character varying(255),
    CONSTRAINT f_nhso_food_source_pkey PRIMARY KEY (f_nhso_food_source_id)
);
insert into f_nhso_food_source values ('01','ทำทานเอง');
insert into f_nhso_food_source values ('02','ซื้อสำเร็จรูป');
insert into f_nhso_food_source values ('03','ทั้งสองอย่าง');

--รสชาติ
CREATE TABLE f_nhso_food_taste (
    f_nhso_food_taste_id character varying(255) NOT NULL,
    food_taste_description character varying(255),
    CONSTRAINT f_nhso_food_taste_pkey PRIMARY KEY (f_nhso_food_taste_id)
);
insert into f_nhso_food_taste values ('01','หวาน');
insert into f_nhso_food_taste values ('02','เค็ม');
insert into f_nhso_food_taste values ('03','เผ็ด');
insert into f_nhso_food_taste values ('99','อื่นๆ');

--พฤติกรรมการรับประทานอาหาร
CREATE TABLE t_nhso_food_behavior (
    t_nhso_food_behavior_id character varying(255) NOT NULL,
    t_nhso_food_id character varying(255),
    f_nhso_food_group_id character varying(255),
    food_behavior_food_name character varying(255),
    f_nhso_food_behavior_frequency_id character varying(255),
    food_behavior_frequency_description character varying(255),
    t_nhso_service_id character varying(255),
    t_health_family_id character varying(255),    
    CONSTRAINT t_nhso_food_behavior_pkey PRIMARY KEY (t_nhso_food_behavior_id)
);

--ประเภทอาหาร
CREATE TABLE f_nhso_food_group (
    f_nhso_food_group_id character varying(255) NOT NULL,
    food_group_description character varying(255),
    CONSTRAINT f_nhso_food_group_pkey PRIMARY KEY (f_nhso_food_group_id)
);
insert into f_nhso_food_group values ('01','เนื้อสัตว์ติดมัน');
insert into f_nhso_food_group values ('02','แกงกะทิ');
insert into f_nhso_food_group values ('03','ผัก ผลไม้');
insert into f_nhso_food_group values ('04','อาหารมื้อเช้า');
insert into f_nhso_food_group values ('11','ยาลดไขมัน');
insert into f_nhso_food_group values ('12','ฮอร์โมนทดแทน/วัยทอง');
insert into f_nhso_food_group values ('13','ยาลดความดันโลหิต');

--ความถึ่ในการรับประทานอาหาร
CREATE TABLE f_nhso_food_behavior_frequency (
    f_nhso_food_behavior_frequency_id character varying(255) NOT NULL,
    food_behavior_frequency_description character varying(255),
    CONSTRAINT f_nhso_food_behavior_frequency_pkey PRIMARY KEY (f_nhso_food_behavior_frequency_id)
);
insert into f_nhso_food_behavior_frequency values ('01','ทุกวัน');
insert into f_nhso_food_behavior_frequency values ('03','นานๆครั้ง');
insert into f_nhso_food_behavior_frequency values ('02','บางครั้ง');
insert into f_nhso_food_behavior_frequency values ('04','ไม่กิน');
insert into f_nhso_food_behavior_frequency values ('05','เคยกิน');
insert into f_nhso_food_behavior_frequency values ('99','อื่นๆ');

--การเสพสารเสพติด
CREATE TABLE t_nhso_dope_drug (
    t_nhso_dope_drug_id character varying(255) NOT NULL,
    t_health_family_id character varying(255),
    f_nhso_drug_type_id  character varying(255),
    f_nhso_drug_usual_status_id character varying(255),
    dope_drug_first_age character varying(255),
    dope_drug_frequency character varying(255),
    dope_drug_number_year_take character varying(255),
    dope_drug_number_year_stop character varying(255),
    dope_drug_record_employee character varying(255),
    dope_drug_record_date_time character varying(255),
    t_nhso_service_id character varying(255),
    dope_drug_is_select character varying(255),
    CONSTRAINT t_nhso_dope_drug_pkey PRIMARY KEY (t_nhso_dope_drug_id)
);

--ประเภทอาหาร
CREATE TABLE f_nhso_drug_type (
    f_nhso_drug_type_id character varying(255) NOT NULL,
    drug_type_description character varying(255),
    CONSTRAINT f_nhso_drug_type_pkey PRIMARY KEY (f_nhso_drug_type_id)
);
insert into f_nhso_drug_type values ('01','สูบบุหรี่');
insert into f_nhso_drug_type values ('02','ดื่มสุรา');

--ความถึ่ในการเสพ
CREATE TABLE f_nhso_drug_usual_status (
    f_nhso_drug_usual_status_id character varying(255) NOT NULL,
    drug_usual_status_description character varying(255),
    CONSTRAINT f_nhso_drug_usual_status_pkey PRIMARY KEY (f_nhso_drug_usual_status_id)
);
insert into f_nhso_drug_usual_status values ('01','ไม่เสพ');
insert into f_nhso_drug_usual_status values ('02','เสพ');
insert into f_nhso_drug_usual_status values ('03','เคยเสพ');

--พฤติกรรมความเครียด
CREATE TABLE t_nhso_strain (
    t_nhso_strain_id character varying(255) NOT NULL,
    t_health_family_id character varying(255),
    f_nhso_strain_group_id character varying(255),
    strain_topic character varying(255),
    strain_description character varying(255),
    strain_record_employee character varying(255),
    strain_record_date_time character varying(255),
    t_nhso_service_id character varying(255),
    CONSTRAINT t_nhso_strain_pkey PRIMARY KEY (t_nhso_strain_id)
);

--กลุ่มความเครียด
CREATE TABLE f_nhso_strain_group (
    f_nhso_strain_group_id character varying(255) NOT NULL,
    strain_group_description character varying(255),
    CONSTRAINT f_nhso_strain_group_pkey PRIMARY KEY (f_nhso_strain_group_id)
);
insert into f_nhso_strain_group values ('01','บุคลิกส่วนตัว');
insert into f_nhso_strain_group values ('02','งานและความรับผิดชอบ');
insert into f_nhso_strain_group values ('03','ครอบครัว');
insert into f_nhso_strain_group values ('04','สังคม');

--หัวข้อย่อยความเครียด
CREATE TABLE b_nhso_strain_topic (
    b_nhso_strain_topic_id character varying(255) NOT NULL,
    f_nhso_strain_group_id character varying(255),
    strain_topic_description character varying(255),    
    CONSTRAINT b_nhso_strain_topic_pkey PRIMARY KEY (b_nhso_strain_topic_id)
);
insert into b_nhso_strain_topic values ('nh00100000001','01','ใจร้อน');
insert into b_nhso_strain_topic values ('nh00100000002','01','หงุดหงิดง่าย');
insert into b_nhso_strain_topic values ('nh00100000003','01','เอาจริงเอาจัง');
insert into b_nhso_strain_topic values ('nh00100000004','01','รู้สึกกลัว/วิตกกังวล');
insert into b_nhso_strain_topic values ('nh00100000005','01','ตื่นเต้นง่าย');
insert into b_nhso_strain_topic values ('nh00100000006','01','อื่นๆ');
insert into b_nhso_strain_topic values ('nh00100000007','02','งานหนัก');
insert into b_nhso_strain_topic values ('nh00100000008','02','ไม่มีเวลาพักผ่อน');
insert into b_nhso_strain_topic values ('nh00100000009','02','เพื่อนร่วมงานมีปัญหา');
insert into b_nhso_strain_topic values ('nh00100000010','02','ทำงานไม่ได้ดั่งใจ');
insert into b_nhso_strain_topic values ('nh00100000011','02','ถูกตำหนิบ่อย');
insert into b_nhso_strain_topic values ('nh00100000012','02','งานราบรื่นดีไม่มีปัญหา');
insert into b_nhso_strain_topic values ('nh00100000013','03','ต้องรับผิดชอบมาก');
insert into b_nhso_strain_topic values ('nh00100000014','03','สัมพันธภาพไม่ค่อยดี');
insert into b_nhso_strain_topic values ('nh00100000015','03','ไม่ค่อยเข้าใจกัน');
insert into b_nhso_strain_topic values ('nh00100000016','03','ต่างคนต่างอยู่');
insert into b_nhso_strain_topic values ('nh00100000017','03','ค่าใช้จ่ายมาก');
insert into b_nhso_strain_topic values ('nh00100000018','03','ไม่มีคนช่วยทำงานบ้าน');
insert into b_nhso_strain_topic values ('nh00100000019','03','เป็นสุขดี ไม่มีปัญหา');
insert into b_nhso_strain_topic values ('nh00100000020','04','ไม่ค่อยได้สนใจ');
insert into b_nhso_strain_topic values ('nh00100000021','04','ช่างวุ่นวาย น่าเบื่อ');
insert into b_nhso_strain_topic values ('nh00100000022','04','โชคดีมีเพื่อนสนิทไม่เครียด');
insert into b_nhso_strain_topic values ('nh00100000023','04','มีแต่คนเห็นแก่ตัวเอาเปรียบ');
insert into b_nhso_strain_topic values ('nh00100000024','04','ทำใจได้สบายๆ');

--การตรวจร่างกาย
CREATE TABLE t_nhso_vital_sign (
    t_nhso_vital_sign_id character varying(255) NOT NULL,
    t_visit_vital_sign_id character varying(255),
    vital_sign_waist_inch character varying(255),
    vital_sign_hip_inch character varying(255),
    vital_sign_is_normal character varying(255),
    vital_sign_abnormal_description character varying(255),
    vital_sign_weight character varying(255),
    vital_sign_height character varying(255),
    vital_sign_bmi character varying(255),
    vital_sign_pressure character varying(255),
    vital_sign_blood_sugar character varying(255),
    t_nhso_service_id character varying(255),
    t_health_family_id character varying(255),
    CONSTRAINT t_nhso_vital_sign_pkey PRIMARY KEY (t_nhso_vital_sign_id)
);

--สรุปความเห็นแพทย์
CREATE TABLE t_nhso_doctor_conclusion (
    t_nhso_doctor_conclusion_id character varying(255) NOT NULL,
    t_health_family_id character varying(255),
    doctor_conclusion_in_risk_condition character varying(255),
    doctor_conclusion_risk_topic character varying(255),
    doctor_conclusion_chronic_disease character varying(255),
    doctor_conclusion_is_change_behavior character varying(255),
    doctor_conclusion_record_employee character varying(255),
    doctor_conclusion_record_date_time character varying(255),
    doctor_conclusion_conclusion character varying(255),
    t_nhso_service_id character varying(255),
    CONSTRAINT t_nhso_doctor_conclusion_pkey PRIMARY KEY (t_nhso_doctor_conclusion_id)
);

--รายละเอียดวิธีการปรับเปลี่ยนพฤติกรรม
--CREATE TABLE t_nhso_doctor_conclusion_topic_detail (
--    t_nhso_doctor_conclusion_topic_detail_id character varying(255) NOT NULL,
--    t_nhso_doctor_conclusion_id character varying(255),
--    f_nhso_doctor_conclusion_topic_id character varying(255),
--    conclusion_topic_name character varying(255),    
--    t_nhso_service_id character varying(255),
--    CONSTRAINT t_nhso_doctor_conclusion_topic_detail_pkey PRIMARY KEY (t_nhso_doctor_conclusion_topic_detail_id)
--);

--หัวข้อการปรับเปลี่ยนพฤติกรรม
--CREATE TABLE f_nhso_doctor_conclusion_topic (
--    f_nhso_doctor_conclusion_topic_id character varying(255) NOT NULL,
--    conclusion_topic_description character varying(255),    
--    CONSTRAINT f_nhso_doctor_conclusion_topic_pkey PRIMARY KEY (f_nhso_doctor_conclusion_topic_id)
--);
--insert into f_nhso_doctor_conclusion_topic values ('01','เข้าอบรม/ให้คำปรึกษา');
--insert into f_nhso_doctor_conclusion_topic values ('02','ได้รับคู่มือการพัฒนาพฤติกรรม');
--insert into f_nhso_doctor_conclusion_topic values ('99','อื่นๆ');

--ตารางสรุปผลการดำเนินงาน
CREATE TABLE t_nhso_cause_behavior (
    t_nhso_cause_behavior_id character varying(255) NOT NULL,
    t_health_family_id character varying(255),
    cause_behavior_used_to_take character varying(255),
    cause_behavior_take_time character varying(255),
    f_nhso_cause_behavior_frequency_id character varying(255),
    cause_behavior_frequency_description character varying(255),
    f_nhso_cause_behavior_feeling_id character varying(255),
    cause_behavior_feeling_description character varying(255),
    cause_behavior_record_employee character varying(255),
    cause_behavior_record_date_time character varying(255),
    t_nhso_service_id character varying(255),
    CONSTRAINT t_nhso_cause_behavior_pkey PRIMARY KEY (t_nhso_cause_behavior_id)
);

--ความถี่ในการปรับเปลี่ยนพฤติกรรม
CREATE TABLE f_nhso_cause_behavior_frequency (
    f_nhso_cause_behavior_frequency_id character varying(255) NOT NULL,
    frequency_description character varying(255),    
    CONSTRAINT f_nhso_cause_behavior_frequency_pkey PRIMARY KEY (f_nhso_cause_behavior_frequency_id)
);
insert into f_nhso_cause_behavior_frequency values ('01','ได้นำไปปฏิบัติประจำ');
insert into f_nhso_cause_behavior_frequency values ('02','ได้นำไปบฏิบัติบ้าง ไม่สม่ำเสมอ');
insert into f_nhso_cause_behavior_frequency values ('03','ไม่ได้ปฏิบัติ');
insert into f_nhso_cause_behavior_frequency values ('99','อื่นๆ');

--ความรู้สึกต่อกระบวนการที่ได้รับ
CREATE TABLE f_nhso_cause_behavior_feeling (
    f_nhso_cause_behavior_feeling_id character varying(255) NOT NULL,
    feeling_description character varying(255),
    CONSTRAINT f_nhso_cause_behavior_feeling_pkey PRIMARY KEY (f_nhso_cause_behavior_feeling_id)
);
insert into f_nhso_cause_behavior_feeling values ('01','พึงพอใจ มีประโยชน์');
insert into f_nhso_cause_behavior_feeling values ('02','ผลลัพธ์ไม่แน่นอน');
insert into f_nhso_cause_behavior_feeling values ('03','ไม่ได้ผล');
insert into f_nhso_cause_behavior_feeling values ('99','อื่นๆ');

--ข้อมูลการเข้ารับบริการ
CREATE TABLE t_nhso_service (
    t_nhso_service_id character varying(255) NOT NULL,
    service_record_employee character varying(255),
    service_date_time character varying(255),
    service_patient_age character varying(255),
    service_service_point character varying(255),
    t_health_family_id character varying(255),
    t_visit_id character varying(255),
    CONSTRAINT t_nhso_service_pkey PRIMARY KEY (t_nhso_service_id)
);

--ตารางจับคู่รายการตรวจรักษาทีเป็น dtx และ fsb
CREATE TABLE b_nhso_map_item ( 
	b_nhso_map_item_id	varchar(255) NOT NULL,
	b_item_id         	varchar(255) NULL,
	f_nhso_map_item_id	varchar(255) NULL 
	);
ALTER TABLE b_nhso_map_item
	ADD CONSTRAINT b_nhso_map_item_pkey
	PRIMARY KEY (b_nhso_map_item_id);

--ตารางประเภทรายการตรวจรักษา dtx,fsb
CREATE TABLE f_nhso_map_item ( 
	f_nhso_map_item_id  	varchar(255) NOT NULL,
	map_item_description	varchar(255) NULL 
	);

ALTER TABLE f_nhso_map_item
	ADD CONSTRAINT f_nhso_map_item_pkey
	PRIMARY KEY (f_nhso_map_item_id);

INSERT INTO f_nhso_map_item(f_nhso_map_item_id, map_item_description)
  VALUES('01', 'DTX');
INSERT INTO f_nhso_map_item(f_nhso_map_item_id, map_item_description)
  VALUES('02', 'FBS');

CREATE INDEX t_nhso_service_id ON t_nhso_service USING btree (t_nhso_service_id);

--version
CREATE TABLE s_nhso_version
(
  s_version_id varchar(255) NOT NULL,
  version_number varchar(255),
  version_description varchar(255),
  version_application_number varchar(255),
  version_database_number varchar(255),
  version_update_time varchar(255),
  CONSTRAINT s_nhso_version_pkey PRIMARY KEY (s_version_id)
) 
WITH OIDS;
ALTER TABLE s_nhso_version OWNER TO postgres;


INSERT INTO s_nhso_version("s_version_id", "version_number", "version_description", "version_application_number", "version_database_number", "version_update_time")
VALUES('9701000000014',	'1',	'Hospital OS, Community Edition',	'3.13.1048',	'0.1.240907',	'2550-09-24 18:00:00');

--ตารางข้อมูลการลง dx โรค COPD
CREATE TABLE t_nhso_copd_diagnosis (
    t_nhso_copd_diagnosis_id character varying(255) NOT NULL,
    t_visit_id character varying(255),
    f_nhso_copd_comorbidities_id character varying(255),
    diagnosis_comor_other character varying(255),
    f_nhso_disease_control_id character varying(255),
    diagnosis_follow_up character varying(255),
    diagnosis_record_employee character varying(255),
    diagnosis_record_date_time character varying(255),
    CONSTRAINT t_nhso_copd_diagnosis_pkey PRIMARY KEY (t_nhso_copd_diagnosis_id)
);

--ตารางประเภท Co-morbidties
CREATE TABLE f_nhso_copd_comorbidities (
    f_nhso_copd_comorbidities_id character varying(255) NOT NULL,
    comorbidities_description character varying(255),
    CONSTRAINT f_nhso_copd_comorbidities_pkey PRIMARY KEY (f_nhso_copd_comorbidities_id)
);

insert into f_nhso_copd_comorbidities values ('01','Heart failure');
insert into f_nhso_copd_comorbidities values ('02','IHD');
insert into f_nhso_copd_comorbidities values ('03','CVD');
insert into f_nhso_copd_comorbidities values ('04','อื่นๆ');

--ตารางประเภทการควบคุมโรค (Disease Control)
CREATE TABLE f_nhso_disease_control (
    f_nhso_disease_control_id character varying(255) NOT NULL,
    comorbidities_description character varying(255),
    CONSTRAINT f_nhso_disease_control_pkey PRIMARY KEY (f_nhso_disease_control_id)
);

insert into f_nhso_disease_control values ('01','Controlled');
insert into f_nhso_disease_control values ('02','Uncontrolled');
insert into f_nhso_disease_control values ('03','Not known');

--ตารางข้อมูลการซักประวัตโรค COPD
CREATE TABLE t_nhso_copd_examine (
    t_nhso_copd_examine_id character varying(255) NOT NULL,
    t_visit_id character varying(255),
    f_nhso_copd_examine_topic_id character varying(255),
    f_nhso_drug_usual_status_id character varying(255),
    examine_topic_other character varying(255),
    examine_topic_is_have character varying(255),
    examine_time_week character varying(255),
    examine_record_employee character varying(255),
    examine_record_date_time character varying(255),
    CONSTRAINT t_nhso_copd_examine_pkey PRIMARY KEY (t_nhso_copd_examine_id)
);

--ตารางหัวข้อการซักประวัติโรค COPD
CREATE TABLE f_nhso_copd_examine_topic (
    f_nhso_copd_examine_topic_id character varying(255) NOT NULL,
    f_nhso_copd_examine_topic_group_id character varying(255),
    topic_description character varying(255),
    CONSTRAINT f_nhso_copd_examine_topic_pkey PRIMARY KEY (f_nhso_copd_examine_topic_id)
);

insert into f_nhso_copd_examine_topic values ('01','01','มีอาการหอบเหนื่อยเรื้อรังและเป็นมากขึ้นเรื่อยๆ');
insert into f_nhso_copd_examine_topic values ('02','01','มีอาการหอบเหนื่อยในขณะที่ทำกิจกรรมมากกว่ากิจวัตรประจำวัน');
insert into f_nhso_copd_examine_topic values ('03','01','มีอาการหอบเหนื่อยในขณะที่ทำกิจวัตรประจำวัน');
insert into f_nhso_copd_examine_topic values ('04','01','มีอาการหอบเหนื่อยในขณะออกแรงไม่มาก');
insert into f_nhso_copd_examine_topic values ('05','01','มีอาการหอบเหนื่อยในขณะที่อยู่เฉยๆ');
insert into f_nhso_copd_examine_topic values ('06','01','มีอาการไอนานมากกว่า 3 เดือนต่อปี เป็นเวลา 2 ปีติดกัน');
insert into f_nhso_copd_examine_topic values ('07','01','มีเสมหะมากโดยเฉพาะตอนเช้า');
insert into f_nhso_copd_examine_topic values ('08','01','การหายใจมีเสียงหวีดเป็นครั้งคราว');
insert into f_nhso_copd_examine_topic values ('09','01','กำลังหรือเคยสูบบุหรี่น้อยกว่า 10 pack-year');
insert into f_nhso_copd_examine_topic values ('10','01','กำลังหรือเคยสูบบุหรี่มากกว่า 10 pack-year');
insert into f_nhso_copd_examine_topic values ('11','01','กำลังหรือเคยสูบบุหรี่มากกว่า 20 pack-year');
insert into f_nhso_copd_examine_topic values ('12','01','กำลังหรือเคยสูบบุหรี่มากกว่า 30 pack-year');
insert into f_nhso_copd_examine_topic values ('13','01','อยู่ในสภาพแวดล้อมที่เป็นพิษ');
insert into f_nhso_copd_examine_topic values ('14','01','การรับประทานยาลูกกลอน');
insert into f_nhso_copd_examine_topic values ('20','02','จำนวนการใช้ short acting beta 2 agonist/Anticholinergic');
insert into f_nhso_copd_examine_topic values ('21','02','จำนวนวันที่ต้องพบแพทย์ที่คลินิกหรือโรงพยาบาล (ไม่รวมวันนัด)');
insert into f_nhso_copd_examine_topic values ('22','02','จำนวนวันที่ต้องไปรักษาที่ ER');
insert into f_nhso_copd_examine_topic values ('23','02','จำนวนวันที่ต้องนอนรักษาในโรงพยาบาล หรือนอนในห้อง Observ เนื่องจากโรคหอบหืด');
insert into f_nhso_copd_examine_topic values ('24','02','จำนวนวันที่ขาดงานเพราะอาการหอบ');
insert into f_nhso_copd_examine_topic values ('25','02','สิ่งกระตุ้นให้มีอาการเปลี่ยนแปลง');
insert into f_nhso_copd_examine_topic values ('26','02','ชนิดของยารักษาโรคหอบหืดทั้งหมดที่ใช้อยู่ (ระบุจำนวนชนิดของยาที่ใช้อยู่)');
insert into f_nhso_copd_examine_topic values ('27','02','หยุดสูบบุหรี่แล้วหรือไม่');
insert into f_nhso_copd_examine_topic values ('99','01','อื่นๆ');

--ตารางกลุ่มหัวข้อการซักประวัติโรค COPD
CREATE TABLE f_nhso_copd_examine_topic_group (
    f_nhso_copd_examine_topic_group_id character varying(255) NOT NULL,
    topic_group_description character varying(255),
    CONSTRAINT f_nhso_copd_examine_topic_group_pkey PRIMARY KEY (f_nhso_copd_examine_topic_group_id)
);

insert into f_nhso_copd_examine_topic_group values ('01','การซักประวัติ');
insert into f_nhso_copd_examine_topic_group values ('02','ลักษณะอาการ');

--ตารางข้อมูลการตรวจร่างกายโรค COPD
CREATE TABLE t_nhso_copd_physical_exam (
    t_nhso_copd_physical_exam_id character varying(255) NOT NULL,
    t_visit_id character varying(255),
    f_nhso_copd_physical_exam_topic_id character varying(255),
    physical_exam_topic_other character varying(255),
    physical_exam_topic_is_have character varying(255),
    physical_exam_result character varying(255),
    physical_exam_record_employee character varying(255),
    physical_exam_record_date_time character varying(255),
    CONSTRAINT t_nhso_copd_physical_exam_pkey PRIMARY KEY (t_nhso_copd_physical_exam_id)
);

--ตารางหัวข้อการตรวจร่างกายโรค COPD
CREATE TABLE f_nhso_copd_physical_exam_topic (
    f_nhso_copd_physical_exam_topic_id character varying(255) NOT NULL,
    f_nhso_physical_exam_topic_group_id character varying(255),
    topic_description character varying(255),
    CONSTRAINT f_nhso_copd_physical_exam_topic_pkey PRIMARY KEY (f_nhso_copd_physical_exam_topic_id)
);

insert into f_nhso_copd_physical_exam_topic values ('01','01','การใช้กล้ามเนื้อช่วยการหายใจ');
insert into f_nhso_copd_physical_exam_topic values ('02','01','รูปร่างทรวงอกมีลักษณะกลม');
insert into f_nhso_copd_physical_exam_topic values ('03','01','ฟังเสียงปอดมีเสียงหวีด');
insert into f_nhso_copd_physical_exam_topic values ('04','01','ฟังเสียงหายใจออกยาวขึ้น');
insert into f_nhso_copd_physical_exam_topic values ('05','01','ฟังเสียงหายใจลดลง');
insert into f_nhso_copd_physical_exam_topic values ('11','02','เอ็กซเรย์ปอด (เฉพาะครั้งแรกหรือเมื่อมีข้อบ่งชี้)');
insert into f_nhso_copd_physical_exam_topic values ('12','02','การตรวจสมรรถภาพปอดหรือเป่า peak flow');
insert into f_nhso_copd_physical_exam_topic values ('13','02','ECG');
insert into f_nhso_copd_physical_exam_topic values ('99','01','อื่นๆ (ถ้ามี)');

--ตารางกลุ่มหัวข้อการตรวจร่างกาย
CREATE TABLE f_nhso_physical_exam_topic_group (
    f_nhso_physical_exam_topic_group_id character varying(255) NOT NULL,
    topic_group_description character varying(255),
    CONSTRAINT f_nhso_physical_exam_topic_group_pkey PRIMARY KEY (f_nhso_physical_exam_topic_group_id)
);

insert into f_nhso_physical_exam_topic_group values ('01','การตรวจร่างกาย');
insert into f_nhso_physical_exam_topic_group values ('02','การตรวจทางห้องปฏิบัติการ');

--ตารางข้อมูลแนวทางการรักษา copd
CREATE TABLE t_nhso_copd_treat_guide (
    t_nhso_copd_treat_guide_id character varying(255) NOT NULL,
    t_visit_id character varying(255),
    f_nhso_copd_guide_method_id character varying(255),
    guide_record_employee character varying(255),
    guide_record_date_time character varying(255),
    guide_is_select character varying(255),
    CONSTRAINT t_nhso_copd_treat_guide_pkey PRIMARY KEY (t_nhso_copd_treat_guide_id)
);

--ตารางหัวข้อแนวทางการรักษา copd
CREATE TABLE f_nhso_copd_guide_method (
    f_nhso_copd_guide_method_id character varying(255) NOT NULL,
    guide_description character varying(255),
    CONSTRAINT f_nhso_copd_guide_method_pkey PRIMARY KEY (f_nhso_copd_guide_method_id)
);

insert into f_nhso_copd_guide_method values ('01','ผู้ป่วยเป็นโรคปอดอุดกั้นเรื้อรังความรุนแรงระดับที่ 1');
insert into f_nhso_copd_guide_method values ('02','ผู้ป่วยเป็นโรคปอดอุดกั้นเรื้อรังความรุนแรงระดับที่ 2');
insert into f_nhso_copd_guide_method values ('03','ผู้ป่วยเป็นโรคปอดอุดกั้นเรื้อรังความรุนแรงระดับที่ 3');
insert into f_nhso_copd_guide_method values ('04','ผู้ป่วยเป็นโรคปอดอุดกั้นเรื้อรังความรุนแรงระดับที่ 4');
insert into f_nhso_copd_guide_method values ('05','ผู้ป่วยเป็นโรคปอดอุดกั้นเรื้อรังขณะมีอาการเฉียบพลัน');
insert into f_nhso_copd_guide_method values ('06','ให้การรักษาตามระดับความรุนแรงของโรค');
insert into f_nhso_copd_guide_method values ('07','มีการฟื้นฟูสมรรถภาพปอด');
insert into f_nhso_copd_guide_method values ('08','มีการส่งต่อผู้ป่วย');

--ตารางหัวข้อแนวทางการรักษา asthma
CREATE TABLE f_nhso_asthma_guide_method (
    f_nhso_asthma_guide_method_id character varying(255) NOT NULL,
    guide_description character varying(255),
    CONSTRAINT f_nhso_asthma_guide_method_pkey PRIMARY KEY (f_nhso_asthma_guide_method_id)
);

insert into f_nhso_asthma_guide_method values ('04','มีการส่งต่อผู้ป่วย');
insert into f_nhso_asthma_guide_method values ('01','มีการประเมินความรุนแรงของโรค');
insert into f_nhso_asthma_guide_method values ('02','มีการประเมินความรุนแรงของโรคหืดกำเริบเฉียบพลัน');
insert into f_nhso_asthma_guide_method values ('03','มีการประเมินผลลัพธ์ในการควบคุมโรคหอบหืด');

--ตารางข้อมูลการให้การรักษา copd
CREATE TABLE t_nhso_copd_medication (
    t_nhso_copd_medication_id character varying(255) NOT NULL,
    t_visit_id character varying(255),
    f_nhso_copd_medication_topic_id character varying(255),
    medication_drug_name character varying(255),
    medication_dose character varying(255),
    medication_record_employee character varying(255),
    medication_record_date_time character varying(255),
    CONSTRAINT t_nhso_copd_medication_pkey PRIMARY KEY (t_nhso_copd_medication_id)
);

--ตารางหัวข้อการให้การรักษา
CREATE TABLE f_nhso_copd_medication_topic (
    f_nhso_copd_medication_topic_id character varying(255) NOT NULL,
    topic_description character varying(255),
    CONSTRAINT f_nhso_copd_medication_topic_pkey PRIMARY KEY (f_nhso_copd_medication_topic_id)
);

insert into f_nhso_copd_medication_topic values ('01','Corticosteroid ชนิดสูด');
insert into f_nhso_copd_medication_topic values ('02','Theophylline');
insert into f_nhso_copd_medication_topic values ('03','Sustain Release Methylxanthines');
insert into f_nhso_copd_medication_topic values ('04','Anticholinergic');
insert into f_nhso_copd_medication_topic values ('05','B2-Agonist Short Acting');
insert into f_nhso_copd_medication_topic values ('06','B2-Agonist Long Acting');
insert into f_nhso_copd_medication_topic values ('07','Antibiotics');
insert into f_nhso_copd_medication_topic values ('08','Oxygen');
insert into f_nhso_copd_medication_topic values ('99','อื่นๆ');

--ตารางหัวข้อการให้การรักษา
CREATE TABLE f_nhso_asthma_medication_topic (
    f_nhso_asthma_medication_topic_id character varying(255) NOT NULL,
    topic_description character varying(255),
    CONSTRAINT f_nhso_asthma_medication_topic_pkey PRIMARY KEY (f_nhso_asthma_medication_topic_id)
);

insert into f_nhso_asthma_medication_topic values ('01','Corticosteroid ชนิดสูด');
insert into f_nhso_asthma_medication_topic values ('02','Theophylline');
insert into f_nhso_asthma_medication_topic values ('03','Sustain Release Methylxanthines');
insert into f_nhso_asthma_medication_topic values ('04','Anticholinergic');
insert into f_nhso_asthma_medication_topic values ('05','B2-Agonist Short Acting');
insert into f_nhso_asthma_medication_topic values ('06','B2-Agonist Long Acting');
insert into f_nhso_asthma_medication_topic values ('07','Antibiotics');
insert into f_nhso_asthma_medication_topic values ('08','Oxygen');
insert into f_nhso_asthma_medication_topic values ('09','Leukotriene Modifier');
insert into f_nhso_asthma_medication_topic values ('10','Cromolyn Sodium');
insert into f_nhso_asthma_medication_topic values ('11','ยาฉีด Epinephrine/Adrenaline');
insert into f_nhso_asthma_medication_topic values ('99','อื่นๆ');

--ตารางข้อมูลการให้คำแนะนำโรค copd
CREATE TABLE t_nhso_copd_non_medication (
    t_nhso_copd_non_medication_id character varying(255) NOT NULL,
    t_visit_id character varying(255),
    f_nhso_copd_non_medication_topic_id character varying(255),
    non_medication_record_employee character varying(255),
    non_medication_record_date_time character varying(255),
    non_medication_is_select character varying(255),
    CONSTRAINT t_nhso_copd_non_medication_pkey PRIMARY KEY (t_nhso_copd_non_medication_id)
);

--ตารางหัวข้อการให้คำแนะนำ COPD
CREATE TABLE f_nhso_copd_non_medication_topic (
    f_nhso_copd_non_medication_topic_id character varying(255) NOT NULL,
    topic_description character varying(255),
    CONSTRAINT f_nhso_copd_non_medication_topic_pkey PRIMARY KEY (f_nhso_copd_non_medication_topic_id)
);

insert into f_nhso_copd_non_medication_topic values ('01','เรื่องโรค สาเหตุ ชนิดของโรค เพื่อปรับทัศนคติเกี่ยวกับโรคให้ถูกต้อง');
insert into f_nhso_copd_non_medication_topic values ('02','เรื่องสิ่งกระตุ้นที่ทำให้อาการกำเริบ');
insert into f_nhso_copd_non_medication_topic values ('03','เรื่องยาที่ใช้รักษาและการรับประทานยาที่ถูกต้อง การสูดยาที่ถูกต้อง');
insert into f_nhso_copd_non_medication_topic values ('04','เรื่องการออกกำลังกายที่เหมาะสม');
insert into f_nhso_copd_non_medication_topic values ('05','แนะนำให้หยุดสูบบุหรี่');
insert into f_nhso_copd_non_medication_topic values ('06','เรื่องการป้องกันและการแก้ไขภาวะแทรกซ้อนเฉียบพลัน');
insert into f_nhso_copd_non_medication_topic values ('07','แนะนำให้ฉีดวัคซีนป้องกันไข้หวัดใหญ่ ปีละ 1 ครั้ง');

--ตารางหัวข้อการให้คำแนะนำ asthma
CREATE TABLE f_nhso_asthma_non_medication_topic (
    f_nhso_asthma_non_medication_topic_id character varying(255) NOT NULL,
    topic_description character varying(255),
    CONSTRAINT f_nhso_asthma_non_medication_topic_pkey PRIMARY KEY (f_nhso_asthma_non_medication_topic_id)
);
insert into f_nhso_asthma_non_medication_topic values ('01','เรื่องโรค สาเหตุ ชนิดของโรค เพื่อปรับทัศนคติเกี่ยวกับโรคให้ถูกต้อง');
insert into f_nhso_asthma_non_medication_topic values ('02','เรื่องสิ่งกระตุ้นที่ทำให้อาการกำเริบ');
insert into f_nhso_asthma_non_medication_topic values ('03','เรื่องยาที่ใช้รักษาและการรับประทานยาที่ถูกต้อง การสูดยาที่ถูกต้อง');
insert into f_nhso_asthma_non_medication_topic values ('04','เรื่องการออกกำลังกายที่เหมาะสม');
insert into f_nhso_asthma_non_medication_topic values ('05','เรื่องการป้องกันและการแก้ไขภาวะแทรกซ้อนเฉียบพลัน');
insert into f_nhso_asthma_non_medication_topic values ('06','เรื่องการใช้เครื่องฟอกอากาศ');

--ตารางข้อมูลการลง dx โรค Asthma
CREATE TABLE t_nhso_asthma_diagnosis (
    t_nhso_asthma_diagnosis_id character varying(255) NOT NULL,
    t_visit_id character varying(255),
    f_nhso_asthma_comorbidities_id character varying(255),
    diagnosis_comor_other character varying(255),
    f_nhso_disease_control_id character varying(255),
    diagnosis_follow_up character varying(255),
    diagnosis_record_employee character varying(255),
    diagnosis_record_date_time character varying(255),
    CONSTRAINT t_nhso_asthma_diagnosis_pkey PRIMARY KEY (t_nhso_asthma_diagnosis_id)
);

--ตารางประเภท Co-morbidties โรค Asthma
CREATE TABLE f_nhso_asthma_comorbidities (
    f_nhso_asthma_comorbidities_id character varying(255) NOT NULL,
    comorbidities_description character varying(255),
    CONSTRAINT f_nhso_asthma_comorbidities_pkey PRIMARY KEY (f_nhso_asthma_comorbidities_id)
);

insert into f_nhso_asthma_comorbidities values ('01','Allergic conjunctivitis');
insert into f_nhso_asthma_comorbidities values ('02','Allergic dermatitis');
insert into f_nhso_asthma_comorbidities values ('03','Allergic rhinitis');
insert into f_nhso_asthma_comorbidities values ('99','อื่นๆ');

--ตารางข้อมูลการซักประวัตโรค Asthma
CREATE TABLE t_nhso_asthma_examine (
    t_nhso_asthma_examine_id character varying(255) NOT NULL,
    t_visit_id character varying(255),
    f_nhso_asthma_examine_topic_id character varying(255),
    examine_topic_other character varying(255),
    examine_topic_is_have character varying(255),
    examine_time_week character varying(255),
    examine_record_employee character varying(255),
    examine_record_date_time character varying(255),
    CONSTRAINT t_nhso_asthma_examine_pkey PRIMARY KEY (t_nhso_asthma_examine_id)
);

--ตารางหัวข้อการซักประวัติโรค Asthma
CREATE TABLE f_nhso_asthma_examine_topic (
    f_nhso_asthma_examine_topic_id character varying(255) NOT NULL,
    topic_description character varying(255),
    CONSTRAINT f_nhso_asthma_examine_topic_pkey PRIMARY KEY (f_nhso_asthma_examine_topic_id)
);

insert into f_nhso_asthma_examine_topic values ('01','ตื่นมาตอนกลางคืนเพราะหอบเหนื่อย');
insert into f_nhso_asthma_examine_topic values ('02','หายใจมีเสียงหวีด');
insert into f_nhso_asthma_examine_topic values ('03','หอบเหนื่อยแน่นหน้าอกตอนกลางวัน');
insert into f_nhso_asthma_examine_topic values ('04','ไอบ่อยๆตอนกลางวัน');
insert into f_nhso_asthma_examine_topic values ('05','ตื่นขึ้นมาไอตอนกลางคืน');
insert into f_nhso_asthma_examine_topic values ('06','มีอาการแพ้อากาศ');
insert into f_nhso_asthma_examine_topic values ('07','ตอนเด็กบิดาหรือมารดาสูบบุหรี่หรือไม่');
insert into f_nhso_asthma_examine_topic values ('08','ไอตอนเช้า');
insert into f_nhso_asthma_examine_topic values ('09','มีเสมหะบ่อยๆตอนเช้า');
insert into f_nhso_asthma_examine_topic values ('10','มีอาการผื่นแพ้ผิวหนัง');
insert into f_nhso_asthma_examine_topic values ('11','บิดาหรือมารดาเป็นโรคหอบหืดหรือไม่');
insert into f_nhso_asthma_examine_topic values ('12','พี่น้องเป็นโรคหอบหืดหรือไม่');
insert into f_nhso_asthma_examine_topic values ('13','ท่านต้องขาดงานหรือการเรียนเพราะอาการหอบหืดหรือไม่');
insert into f_nhso_asthma_examine_topic values ('14','ท่านเลี้ยงสุนัขหรือไม่');
insert into f_nhso_asthma_examine_topic values ('15','ท่านเลี้ยงแมวหรือไม่');
insert into f_nhso_asthma_examine_topic values ('16','ท่านสูบบุหรี่นานเกิน 1 ปีหรือไม่');
insert into f_nhso_asthma_examine_topic values ('17','ห้องทำงานหรือห้องที่อาศัยประจำที่บ้านมีพรมปูพื้นหรือไม่');
insert into f_nhso_asthma_examine_topic values ('18','เคยป่วยเป็นโรคทางปอดอื่นที่ไม่ใช่หอบหืดหรือไม่');
insert into f_nhso_asthma_examine_topic values ('19','เมื่ออยู่ใกล้ต้นไม้ หญ้าหรือเกสรดอกไม้จะมีอาการเหนื่อยหรือไม่');
insert into f_nhso_asthma_examine_topic values ('20','มีอาการหอบเหนื่อยหลังรับประทานอาหารชนิดใดหรือไม่');
insert into f_nhso_asthma_examine_topic values ('21','จำนวนการใช้ short acting beta 2 agonist (ระบุจำนวนชนิดของยาที่ใช้อยู่)');
insert into f_nhso_asthma_examine_topic values ('22','จำนวนวันที่ต้องไปพบแพทย์ที่คลินิกหรือโรงพยาบาล (ไม่รวมวันนัด)');
insert into f_nhso_asthma_examine_topic values ('23','จำนวนวันที่ต้องนอนรักษาในโรงพยาบาลหรือนอนในห้อง Observ เนื่องจากโรคหอบหืด');
insert into f_nhso_asthma_examine_topic values ('24','จำนวนวันที่ต้องไปรักษาที่ ER');
insert into f_nhso_asthma_examine_topic values ('25','จำนวนวันที่ขาดงานเพราะอาการหอบ');
insert into f_nhso_asthma_examine_topic values ('26','สิ่งกระตุ้นให้มีอาการเปลี่ยนแปลง');
insert into f_nhso_asthma_examine_topic values ('27','ชนิดของยารักษาโรคหอบหืดทั้งหมดที่ใช้อยู่ (ระบุจำนวนชนิดของยาที่ใช้อยู่)');

--ตารางข้อมูลการตรวจร่างกายโรค Asthma
CREATE TABLE t_nhso_asthma_physical_exam (
    t_nhso_asthma_physical_exam_id character varying(255) NOT NULL,
    t_visit_id character varying(255),
    f_nhso_asthma_physical_exam_topic_id character varying(255),
    f_nhso_physical_exam_topic_group_id character varying(255),
    physical_exam_topic_other character varying(255),
    physical_exam_topic_is_have character varying(255),
    physical_exam_result character varying(255),
    physical_exam_record_employee character varying(255),
    physical_exam_record_date_time character varying(255),
    CONSTRAINT t_nhso_asthma_physical_exam_pkey PRIMARY KEY (t_nhso_asthma_physical_exam_id)
);

--ตารางหัวข้อการตรวจร่างกายโรค Asthma
CREATE TABLE f_nhso_asthma_physical_exam_topic (
    f_nhso_asthma_physical_exam_topic_id character varying(255) NOT NULL,
    f_nhso_physical_exam_topic_group_id character varying(255),
    topic_description character varying(255),
    CONSTRAINT f_nhso_asthma_physical_exam_topic_pkey PRIMARY KEY (f_nhso_asthma_physical_exam_topic_id)
);

insert into f_nhso_asthma_physical_exam_topic values ('01','01','ถ้าตรวจพบว่าผู้ป่วยเขียวให้วัดค่าออกซิเจนปลายนิ้ว');
insert into f_nhso_asthma_physical_exam_topic values ('02','01','การใช้กล้ามเนื้อช่วยการหายใจ');
insert into f_nhso_asthma_physical_exam_topic values ('03','01','ฟังเสียงหายใจมีเสียงหวีด');
insert into f_nhso_asthma_physical_exam_topic values ('04','01','ตรวจผลข้างเคียงของการใช้ยาสเตียรอยด์');
insert into f_nhso_asthma_physical_exam_topic values ('05','02','เอ็กซเรย์ปอด (เฉพาะครั้งแรกหรือเมื่อมีข้อบ่งชี้)');
insert into f_nhso_asthma_physical_exam_topic values ('06','02','การตรวจสมรรถภาพปอดหรือเป่า peak flow');

--ตารางข้อมูลแนวทางการรักษา Asthma
CREATE TABLE t_nhso_asthma_treat_guide (
    t_nhso_asthma_treat_guide_id character varying(255) NOT NULL,
    t_visit_id character varying(255),
    f_nhso_asthma_guide_method_id character varying(255),
    guide_record_employee character varying(255),
    guide_record_date_time character varying(255),
    guide_is_select character varying(255),
    CONSTRAINT t_nhso_asthma_treat_guide_pkey PRIMARY KEY (t_nhso_asthma_treat_guide_id)
);

--ตารางข้อมูลการให้การรักษา asthma
CREATE TABLE t_nhso_asthma_medication (
    t_nhso_asthma_medication_id character varying(255) NOT NULL,
    t_visit_id character varying(255),
    f_nhso_asthma_medication_topic_id character varying(255),
    medication_drug_name character varying(255),
    medication_dose character varying(255),
    medication_record_employee character varying(255),
    medication_record_date_time character varying(255),
    CONSTRAINT t_nhso_asthma_medication_pkey PRIMARY KEY (t_nhso_asthma_medication_id)
);

--ตารางข้อมูลการให้คำแนะนำโรค asthma
CREATE TABLE t_nhso_asthma_non_medication (
    t_nhso_asthma_non_medication_id character varying(255) NOT NULL,
    t_visit_id character varying(255),
    f_nhso_asthma_non_medication_topic_id character varying(255),
    non_medication_record_employee character varying(255),
    non_medication_record_date_time character varying(255),
    non_medication_is_select character varying(255),
    CONSTRAINT t_nhso_asthma_non_medication_pkey PRIMARY KEY (t_nhso_asthma_non_medication_id)
);

--ตาราง map item ที่เป็นการตรวจทางห้องปฏิบัติการ copd
CREATE TABLE b_nhso_map_copd_lab ( 
	b_nhso_map_copd_lab_id character varying(255) NOT NULL,
	b_item_id character varying(255),
	f_nhso_copd_physical_exam_topic_id character varying(255),
	CONSTRAINT b_nhso_map_copd_lab_pkey PRIMARY KEY (b_nhso_map_copd_lab_id)
);

--ตาราง map item ที่เป็นรายการ medications copd
CREATE TABLE b_nhso_map_item_copd_medication ( 
	b_nhso_map_item_copd_medication_id character varying(255) NOT NULL,
	b_item_id character varying(255),
	f_nhso_copd_medication_topic_id character varying(255),
	CONSTRAINT b_nhso_map_item_copd_medication_pkey PRIMARY KEY (b_nhso_map_item_copd_medication_id)
);

--ตาราง map item ที่เป็นการตรวจทางห้องปฏิบัติการ asthma
CREATE TABLE b_nhso_map_asthma_lab ( 
	b_nhso_map_asthma_lab_id character varying(255) NOT NULL,
	b_item_id character varying(255),
	f_nhso_asthma_physical_exam_topic_id character varying(255),
	CONSTRAINT b_nhso_map_asthma_lab_pkey PRIMARY KEY (b_nhso_map_asthma_lab_id)
);

--ตาราง map item ที่เป็นรายการ medications asthma
CREATE TABLE b_nhso_map_item_asthma_medication ( 
	b_nhso_map_item_asthma_medication_id character varying(255) NOT NULL,
	b_item_id character varying(255),
	f_nhso_asthma_medication_topic_id character varying(255),
	CONSTRAINT b_nhso_map_item_asthma_medication_pkey PRIMARY KEY (b_nhso_map_item_asthma_medication_id)
);

--ตารางข้อมูลการลง dx โรค dm ht
CREATE TABLE t_nhso_dmht_diagnosis (
    t_nhso_dmht_diagnosis_id character varying(255) NOT NULL,
    t_visit_id character varying(255),
    f_nhso_dm_diag_type_id character varying(255),
    f_nhso_ht_diag_type_id character varying(255),
    diagnosis_dm_date_time character varying(255),
    diagnosis_ht_date_time character varying(255),
    f_nhso_dmht_comorbidities_id character varying(255),
    diagnosis_comor_other character varying(255),
    diagnosis_record_employee character varying(255),
    diagnosis_record_date_time character varying(255),
    f_nhso_family_income_enough_id character varying(255),
    CONSTRAINT t_nhso_dmht_diagnosis_pkey PRIMARY KEY (t_nhso_dmht_diagnosis_id)
);

--ตารางประเภท dx ของ dm
CREATE TABLE f_nhso_dm_diag_type (
    f_nhso_dm_diag_type_id character varying(255) NOT NULL,
    topic_description character varying(255),
    CONSTRAINT f_nhso_dm_diag_type_pkey PRIMARY KEY (f_nhso_dm_diag_type_id)
);

insert into f_nhso_dm_diag_type values ('01','Type 1 DM');
insert into f_nhso_dm_diag_type values ('02','Type 2 DM');
insert into f_nhso_dm_diag_type values ('03','Pregnancy with DM');
insert into f_nhso_dm_diag_type values ('99','Other type');

--ตารางประเภท dx ของ ht
CREATE TABLE f_nhso_ht_diag_type (
    f_nhso_ht_diag_type_id character varying(255) NOT NULL,
    topic_description character varying(255),
    CONSTRAINT f_nhso_ht_diag_type_pkey PRIMARY KEY (f_nhso_ht_diag_type_id)
);

insert into f_nhso_ht_diag_type values ('01','Essential HT');
insert into f_nhso_ht_diag_type values ('02','Secondary HT');

--ตารางประเภท comor ของฟอร์ม dm-ht
CREATE TABLE f_nhso_dmht_comorbidities (
    f_nhso_dmht_comorbidities_id character varying(255) NOT NULL,
    comorbidities_description character varying(255),
    CONSTRAINT f_nhso_dmht_comorbidities_pkey PRIMARY KEY (f_nhso_dmht_comorbidities_id)
);

insert into f_nhso_dmht_comorbidities values ('01','Nephropathy');
insert into f_nhso_dmht_comorbidities values ('02','Retinopathy');
insert into f_nhso_dmht_comorbidities values ('03','Dyslipidemia');
insert into f_nhso_dmht_comorbidities values ('04','Heart failure');
insert into f_nhso_dmht_comorbidities values ('05','IHD');
insert into f_nhso_dmht_comorbidities values ('06','CVD');
insert into f_nhso_dmht_comorbidities values ('07','Ulcer');
insert into f_nhso_dmht_comorbidities values ('99','Others');

--ตารางจับคู่รายการตรวจรักษาทีเป็น dtx และ fsb
CREATE TABLE b_nhso_map_lab_dm ( 
	b_nhso_map_lab_dm_id character varying(255) NOT NULL,
	b_item_id character varying(255),
	f_nhso_map_lab_dm_item_id character varying(255),
	CONSTRAINT b_nhso_map_lab_dm_pkey PRIMARY KEY (b_nhso_map_lab_dm_id)
);

--ตารางประเภทรายการตรวจรักษา dtx,fsb
CREATE TABLE f_nhso_map_lab_dm_item ( 
	f_nhso_map_lab_dm_item_id character varying(255) NOT NULL,
	map_item_description character varying(255),
	CONSTRAINT f_nhso_map_lab_dm_item_pkey PRIMARY KEY (f_nhso_map_lab_dm_item_id)
);

INSERT INTO f_nhso_map_lab_dm_item VALUES('01', 'Metformin');
INSERT INTO f_nhso_map_lab_dm_item VALUES('02', 'Sulfonylurea');
INSERT INTO f_nhso_map_lab_dm_item VALUES('03', 'Alpha-GL');
INSERT INTO f_nhso_map_lab_dm_item VALUES('04', 'Biguanide');
INSERT INTO f_nhso_map_lab_dm_item VALUES('05', 'Glitazones');
INSERT INTO f_nhso_map_lab_dm_item VALUES('06', 'Insulin');
INSERT INTO f_nhso_map_lab_dm_item VALUES('07', 'Others');

--ตารางจับคู่รายการตรวจรักษาทีเป็น dtx และ fsb
CREATE TABLE b_nhso_map_lab_ht ( 
	b_nhso_map_lab_ht_id character varying(255) NOT NULL,
	b_item_id character varying(255),
	f_nhso_map_lab_ht_item_id character varying(255),
	CONSTRAINT b_nhso_map_lab_ht_pkey PRIMARY KEY (b_nhso_map_lab_ht_id)
);

--ตารางประเภทรายการตรวจรักษา dtx,fsb
CREATE TABLE f_nhso_map_lab_ht_item ( 
	f_nhso_map_lab_ht_item_id character varying(255) NOT NULL,
	map_item_description character varying(255),
	CONSTRAINT f_nhso_map_lab_ht_item_pkey PRIMARY KEY (f_nhso_map_lab_ht_item_id)
);

INSERT INTO f_nhso_map_lab_ht_item VALUES('01', 'ACE-1');
INSERT INTO f_nhso_map_lab_ht_item VALUES('02', 'ARB');
INSERT INTO f_nhso_map_lab_ht_item VALUES('03', 'A-blocker');
INSERT INTO f_nhso_map_lab_ht_item VALUES('04', 'B-blocker');
INSERT INTO f_nhso_map_lab_ht_item VALUES('05', 'A-B blocker');
INSERT INTO f_nhso_map_lab_ht_item VALUES('06', 'CCB');
INSERT INTO f_nhso_map_lab_ht_item VALUES('07', 'Diuretic');
INSERT INTO f_nhso_map_lab_ht_item VALUES('08', 'Fibrate');
INSERT INTO f_nhso_map_lab_ht_item VALUES('09', 'Statin');
INSERT INTO f_nhso_map_lab_ht_item VALUES('10', 'Methyldopa');
INSERT INTO f_nhso_map_lab_ht_item VALUES('11', 'Aspiria');
INSERT INTO f_nhso_map_lab_ht_item VALUES('12', 'Others');

--ตารางข้อมูลประวัติส่วนตัวโรค dm ht
CREATE TABLE t_nhso_dmht_history ( 
	t_nhso_dmht_history_id character varying(255) NOT NULL,
	t_visit_id character varying(255),
	f_nhso_dmht_history_topic_id character varying(255),
	f_nhso_food_behavior_frequency_id character varying(255),
	history_topic_other character varying(255),
	history_strain_level character varying(255),
	history_used_to_cigar character varying(255),
	history_cigar_frequency character varying(255),
	history_record_employee character varying(255),
	history_record_date_time character varying(255),
	CONSTRAINT t_nhso_dmht_history_pkey PRIMARY KEY (t_nhso_dmht_history_id)
);

--ตารางหัวข้อประวัติส่วนตัว
CREATE TABLE f_nhso_dmht_history_topic ( 
	f_nhso_dmht_history_topic_id character varying(255) NOT NULL,
	history_topic_description character varying(255),
	CONSTRAINT f_nhso_dmht_history_topic_pkey PRIMARY KEY (f_nhso_dmht_history_topic_id)
);

INSERT INTO f_nhso_dmht_history_topic VALUES('01', 'การดื่มแอลกอฮอล์');
INSERT INTO f_nhso_dmht_history_topic VALUES('02', 'ชายดื่ม > 4 ดื่มมาตรฐาน/วัน');
INSERT INTO f_nhso_dmht_history_topic VALUES('03', 'หญิ่งดื่ม > 2 ดื่มมาตรฐาน/วัน');
INSERT INTO f_nhso_dmht_history_topic VALUES('04', 'การออกกำลังอย่างต่อเนื่อง 30 นาที');
INSERT INTO f_nhso_dmht_history_topic VALUES('05', 'รับประทานของหมักดอง');
INSERT INTO f_nhso_dmht_history_topic VALUES('06', 'รับประทานขนมหวาน');
INSERT INTO f_nhso_dmht_history_topic VALUES('07', 'รับประทานของมัน');
INSERT INTO f_nhso_dmht_history_topic VALUES('08', 'ท่านกำลังหรือเคยสูบบุหรี่');
INSERT INTO f_nhso_dmht_history_topic VALUES('09', 'ระดับความเครียด');

--ตารางข้อมูลประวัติครอบครัวโรค dm ht
CREATE TABLE t_nhso_dmht_family_history ( 
	t_nhso_dmht_family_history_id character varying(255) NOT NULL,
	t_visit_id character varying(255),
	family_history_relate_person character varying(255),
	f_nhso_disease_group_id character varying(255),
	f_nhso_age_range_id character varying(255),
	f_nhso_disease_status_id character varying(255),
	family_history_record_employee character varying(255),
	family_history_record_date_time character varying(255),
	CONSTRAINT t_nhso_dmht_family_history_pkey PRIMARY KEY (t_nhso_dmht_family_history_id)
);

--ตารางประเภทโรคของประวัติครอบครัวในฟอร์ม dm-ht
insert into f_nhso_disease_group values ('04','CVD');
insert into f_nhso_disease_group values ('05','IHD');
insert into f_nhso_disease_group values ('06','Dyslipidemia');

--ตารางช่วงอายุ dm-ht
CREATE TABLE f_nhso_age_range ( 
	f_nhso_age_range_id character varying(255) NOT NULL,
	age_range_description character varying(255),
	CONSTRAINT f_nhso_age_range_pkey PRIMARY KEY (f_nhso_age_range_id)
);

INSERT INTO f_nhso_age_range VALUES('01', 'ในชายอายุ < 55 ปี');
INSERT INTO f_nhso_age_range VALUES('02', 'ในหญิงอายุ < 65 ปี');

--ตารางข้อมูลการตรวจร่างกายโรค dm ht
CREATE TABLE t_nhso_dmht_physical_exam ( 
	t_nhso_dmht_physical_exam_id character varying(255) NOT NULL,
	t_visit_id character varying(255),
	f_nhso_dmht_physical_exam_topic_id character varying(255),
	physical_exam_result character varying(255),
	physical_exam_note character varying(255),
	physical_exam_date_time character varying(255),
	physical_exam_record_employee character varying(255),
	physical_exam_record_date_time character varying(255),
	CONSTRAINT t_nhso_dmht_physical_exam_pkey PRIMARY KEY (t_nhso_dmht_physical_exam_id)
);

--ตารางหัวข้อการตรวจร่างกายฟอร์ม dm-ht
CREATE TABLE f_nhso_dmht_physical_exam_topic_group ( 
	f_nhso_dmht_physical_exam_topic_group_id character varying(255) NOT NULL,
	physical_exam_topic_description character varying(255),
	CONSTRAINT f_nhso_dmht_physical_exam_topic_group_pkey PRIMARY KEY (f_nhso_dmht_physical_exam_topic_group_id)
);

INSERT INTO f_nhso_dmht_physical_exam_topic_group VALUES('01', 'Eye and Retina Exam');
INSERT INTO f_nhso_dmht_physical_exam_topic_group VALUES('02', 'Neurosystem');
INSERT INTO f_nhso_dmht_physical_exam_topic_group VALUES('03', 'Skin');
INSERT INTO f_nhso_dmht_physical_exam_topic_group VALUES('04', 'Extremities exam');
INSERT INTO f_nhso_dmht_physical_exam_topic_group VALUES('05', 'Oral Hygiene');
INSERT INTO f_nhso_dmht_physical_exam_topic_group VALUES('06', 'Heart');

--ตารางหัวข้อการตรวจร่างกายฟอร์ม dm-ht
CREATE TABLE f_nhso_dmht_physical_exam_topic ( 
	f_nhso_dmht_physical_exam_topic_id character varying(255) NOT NULL,
	f_nhso_dmht_physical_exam_topic_group_id character varying(255),
	physical_exam_topic_description character varying(255),
	CONSTRAINT f_nhso_dmht_physical_exam_topic_pkey PRIMARY KEY (f_nhso_dmht_physical_exam_topic_id)
);

INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('01', '01', 'Cataract');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('02', '01', 'Nonproliferative retinopathy');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('03', '01', 'Proliferative retinopathy');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('04', '01', 'Hypertensive retinopathy');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('05', '02', 'Sensory neuropathy');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('06', '02', 'Motor neuropathy');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('07', '02', 'Autonomic neuropathy');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('08', '03', 'Fungal and bacterial infection');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('09', '03', 'Erosion with callus');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('10', '03', 'Ulcer');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('11', '04', 'Structural abnormalities');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('12', '04', 'Neuropathy');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('13', '04', 'Vascular disease');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('14', '04', 'Infection/Ulcer');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('15', '05', 'Dental caries');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('16', '05', 'Gingivitis');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('17', '05', 'Others');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('18', '06', 'Cardiomegaly');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('19', '06', 'Heart murmur and abnormal heart sound');
INSERT INTO f_nhso_dmht_physical_exam_topic VALUES('20', '06', 'Dysrhythmia');

--ตารางจับคู่รายการตรวจรักษา investigation ของฟอร์ม dm-ht
CREATE TABLE b_nhso_map_lab_dmht_investigation ( 
	b_nhso_map_lab_dmht_investigation_id character varying(255) NOT NULL,
	b_item_id character varying(255),
	f_nhso_map_lab_dmht_investigation_item_id character varying(255),
	CONSTRAINT b_nhso_map_lab_dmht_investigation_pkey PRIMARY KEY (b_nhso_map_lab_dmht_investigation_id)
);

--ตารางรายการ lab investigation ของฟอร์ม dm-ht
CREATE TABLE f_nhso_map_lab_dmht_investigation_item ( 
	f_nhso_map_lab_dmht_investigation_item_id character varying(255) NOT NULL,
	dmht_investigation_item_description character varying(255),
	CONSTRAINT f_nhso_map_lab_dmht_investigation_item_pkey PRIMARY KEY (f_nhso_map_lab_dmht_investigation_item_id)
);

INSERT INTO f_nhso_map_lab_dmht_investigation_item VALUES('01', 'Hct');
INSERT INTO f_nhso_map_lab_dmht_investigation_item VALUES('02', 'HbA1c');
INSERT INTO f_nhso_map_lab_dmht_investigation_item VALUES('03', 'Total Cholesterol');
INSERT INTO f_nhso_map_lab_dmht_investigation_item VALUES('04', 'HDL');
INSERT INTO f_nhso_map_lab_dmht_investigation_item VALUES('05', 'LDL');
INSERT INTO f_nhso_map_lab_dmht_investigation_item VALUES('06', 'TG');
INSERT INTO f_nhso_map_lab_dmht_investigation_item VALUES('07', 'BUN');
INSERT INTO f_nhso_map_lab_dmht_investigation_item VALUES('08', 'Creatinine');
INSERT INTO f_nhso_map_lab_dmht_investigation_item VALUES('09', 'Uric acid');
INSERT INTO f_nhso_map_lab_dmht_investigation_item VALUES('10', 'Urine Albumin');
INSERT INTO f_nhso_map_lab_dmht_investigation_item VALUES('11', 'Urine Sugar');
INSERT INTO f_nhso_map_lab_dmht_investigation_item VALUES('12', 'Urine micro-albumin');
INSERT INTO f_nhso_map_lab_dmht_investigation_item VALUES('13', 'EKG');
INSERT INTO f_nhso_map_lab_dmht_investigation_item VALUES('14', 'Chest X-ray');
INSERT INTO f_nhso_map_lab_dmht_investigation_item VALUES('15', 'Other tests as indicated');

--ตารางบันทึกข้อมูลประวัติการรักษาต่างๆ ของฟอร์ม dm-ht
CREATE TABLE t_nhso_dmht_summary ( 
	t_nhso_dmht_summary_id character varying(255) NOT NULL,
	f_nhso_dmht_summary_group_id character varying(255),
	summary_topic character varying(255),
	summary_date_time character varying(255),
	summary_is_have character varying(255),
	summary_detail character varying(255),
	t_visit_id character varying(255),
	t_family_id character varying(255),
	summary_record_employee character varying(255),
	summary_record_date_time character varying(255),
	CONSTRAINT t_nhso_dmht_summary_pkey PRIMARY KEY (t_nhso_dmht_summary_id)
);

--ตารางรายการกลุ่มประวัติ ของฟอร์ม dm-ht
CREATE TABLE f_nhso_dmht_summary_group ( 
	f_nhso_dmht_summary_group_id character varying(255) NOT NULL,
	summary_group_description character varying(255),
	CONSTRAINT f_nhso_dmht_summary_group_pkey PRIMARY KEY (f_nhso_dmht_summary_group_id)
);

INSERT INTO f_nhso_dmht_summary_group VALUES('01', 'Vital Sign');
INSERT INTO f_nhso_dmht_summary_group VALUES('02', 'Medication DM');
INSERT INTO f_nhso_dmht_summary_group VALUES('03', 'Medication HT');
INSERT INTO f_nhso_dmht_summary_group VALUES('05', 'Investigation');
INSERT INTO f_nhso_dmht_summary_group VALUES('06', 'Dx History');

--ตารางจับคู่หมอ ของฟอร์ม dm-ht
CREATE TABLE b_nhso_map_employee_dmht ( 
	b_nhso_map_dmht_employee_id character varying(255) NOT NULL,
	b_employee_id character varying(255),
	f_nhso_map_employee_dmht_id character varying(255),
	CONSTRAINT b_nhso_map_dmht_employee_pkey PRIMARY KEY (b_nhso_map_dmht_employee_id)
);

--ตารางรายการประเภทหมอ dm-ht
CREATE TABLE f_nhso_dmht_employee_type ( 
	f_nhso_dmht_employee_type_id character varying(255) NOT NULL,
	employee_description character varying(255),
	CONSTRAINT f_nhso_dmht_employee_type_pkey PRIMARY KEY (f_nhso_dmht_employee_type_id)
);

INSERT INTO f_nhso_dmht_employee_type VALUES('01', 'จักษุแพทย์');
INSERT INTO f_nhso_dmht_employee_type VALUES('02', 'อายุรแพทย์โรคไต');
INSERT INTO f_nhso_dmht_employee_type VALUES('03', 'อายุรแพทย์ระบบประสาท');
INSERT INTO f_nhso_dmht_employee_type VALUES('04', 'อายุรแพทย์หัวใจ');
INSERT INTO f_nhso_dmht_employee_type VALUES('05', 'อายุรแพทย์ต่อมไร้ท่อ');
INSERT INTO f_nhso_dmht_employee_type VALUES('06', 'Admission');
INSERT INTO f_nhso_dmht_employee_type VALUES('99', 'Other');

--ตารางบันทึกข้อมูลการให้คำแนะนำ ของฟอร์ม dm-ht
CREATE TABLE t_nhso_dmht_non_medication ( 
	t_nhso_dmht_non_medication_id character varying(255) NOT NULL,
	f_nhso_dmht_non_medication_topic_id character varying(255),	
	non_medication_is_select character varying(255),
	t_visit_id character varying(255),
	non_medication_record_employee character varying(255),
	non_medication_record_date_time character varying(255),
	CONSTRAINT t_nhso_dmht_non_medication_pkey PRIMARY KEY (t_nhso_dmht_non_medication_id)
);

--ตารางรายการหัวข้อการให้คำแนะนำ dm-ht
CREATE TABLE f_nhso_dmht_non_medication_topic ( 
	f_nhso_dmht_non_medication_topic_id character varying(255) NOT NULL,
	non_medication_type_description character varying(255),
	CONSTRAINT f_nhso_dmht_non_medication_topic_pkey PRIMARY KEY (f_nhso_dmht_non_medication_topic_id)
);

INSERT INTO f_nhso_dmht_non_medication_topic VALUES('01', 'เรื่องโรค สาเหตุ ชนิดของโรค เพื่อปรับทัศนคติเกี่ยวกับโรคให้ถูกต้อง');
INSERT INTO f_nhso_dmht_non_medication_topic VALUES('02', 'เรื่องอาหาร และการเลือกรับประทานอาหารที่เหมาะสมกับโรค');
INSERT INTO f_nhso_dmht_non_medication_topic VALUES('03', 'เรื่องยาที่ใช้รักษาและการรับประทานยาที่ถูกต้องตามชนิด ขนาด เวลา');
INSERT INTO f_nhso_dmht_non_medication_topic VALUES('04', 'เรื่องการออกกำลังกายที่เหมาะสมกับสภาวะของสุขภาพร่างกาย');
INSERT INTO f_nhso_dmht_non_medication_topic VALUES('05', 'เรื่องการป้องกันและการแก้ไขภาวะแทรกซ้อนเฉียบพลัน');
