CREATE TABLE "b_nhso_service_item" ( 
	"b_nhso_service_item_id"	varchar(255) NOT NULL,
	"f_nhso_service_id"     varchar(255) NULL DEFAULT ''::character varying,
	"b_item_id"             	varchar(255) NULL DEFAULT ''::character varying,
	"item_number"           	varchar(255) NULL DEFAULT ''::character varying,
	"item_name"             	varchar(255) NULL DEFAULT ''::character varying,
	"default_service_price" 	varchar(255) NULL DEFAULT ''::character varying,
	"active"                	varchar(255) NULL DEFAULT ''::character varying,
	"staff_record"          	varchar(255) NULL DEFAULT ''::character varying,
	"record_date_time"      	varchar(255) NULL DEFAULT ''::character varying,
	PRIMARY KEY("b_nhso_service_item_id")
);
CREATE INDEX "f_nhso_service_id_index"
	ON "b_nhso_service_item"("f_nhso_service_id");

UPDATE "f_health_anc_section"
SET "health_anc_section_description"='ช่วงที่ 1 อายุครรภ์น้อยกว่า 6 เดือน'
WHERE "f_health_anc_section_id"='1';

UPDATE "f_health_anc_section"
SET "health_anc_section_description"='ช่วงที่ 2 อายุครรภ์ 6-7 เดือน'
WHERE "f_health_anc_section_id"='2';

UPDATE "f_health_anc_section"
SET "health_anc_section_description"='ช่วงที่ 3 อายุครรภ์ตั้งแต่ 7 เดือนขึ้นไปถึง 8 เดือน'
WHERE "f_health_anc_section_id"='3';

UPDATE "f_health_anc_section"
SET "health_anc_section_description"='ช่วงที่ 4 อายุครรภ์มากกว่า 8 เดือน'
WHERE "f_health_anc_section_id"='4';

UPDATE f_health_home_charactor
    SET health_home_charactor_description='บ้านเดี่ยว' where f_health_home_charactor_id='05';

UPDATE f_health_home_charactor
    SET health_home_charactor_description='แฟลต'  where f_health_home_charactor_id='06';

INSERT INTO f_health_home_charactor(f_health_home_charactor_id, health_home_charactor_description) 
    VALUES('09', 'บ้านแฝด');

INSERT INTO f_health_home_charactor(f_health_home_charactor_id, health_home_charactor_description) 
    VALUES('10', 'คอนโด');

CREATE TABLE "f_nhso_service" ( 
	"f_nhso_service_id"	varchar(255) NOT NULL,
	"nhso_service_pp"  	varchar(255) NULL DEFAULT ''::character varying,
	"nhso_service_des" 	varchar(255) NULL DEFAULT ''::character varying,
	PRIMARY KEY("f_nhso_service_id")
);

INSERT INTO "f_nhso_service"("f_nhso_service_id", "nhso_service_pp", "nhso_service_des")
VALUES('1', 'FP', 'วางแผนครอบครัว');

INSERT INTO "f_nhso_service"("f_nhso_service_id", "nhso_service_pp", "nhso_service_des")
VALUES('2', 'ANC', 'ฝากครรภ์');

INSERT INTO "f_nhso_service"("f_nhso_service_id", "nhso_service_pp", "nhso_service_des")
VALUES('3', 'DENT', 'ทันตกรรม');

INSERT INTO "f_nhso_service"("f_nhso_service_id", "nhso_service_pp", "nhso_service_des")
VALUES('4', 'DENTCHILD', 'ทันตกรรมเด็ก');

INSERT INTO "f_nhso_service"("f_nhso_service_id", "nhso_service_pp", "nhso_service_des")
VALUES('5', 'PNC', 'ดูแลแม่หลังคลอด');

INSERT INTO "f_nhso_service"("f_nhso_service_id", "nhso_service_pp", "nhso_service_des")
VALUES('6', 'EPI', 'วัคซีน');

INSERT INTO "f_nhso_service"("f_nhso_service_id", "nhso_service_pp", "nhso_service_des")
VALUES('7', 'GROW', 'พัฒนาการเด็ก');

INSERT INTO "f_nhso_service"("f_nhso_service_id", "nhso_service_pp", "nhso_service_des")
VALUES('8', 'CONSULT', 'ให้คำปรึกษาทั่วไป');

INSERT INTO "f_nhso_service"("f_nhso_service_id", "nhso_service_pp", "nhso_service_des")
VALUES('9', 'DMHT', 'ให้คำปรึกษาDMHT');

INSERT INTO "f_nhso_service"("f_nhso_service_id", "nhso_service_pp", "nhso_service_des")
VALUES('10', 'BREASTCANCER', 'ตรวจมะเร็งเต้านม');

INSERT INTO "f_nhso_service"("f_nhso_service_id", "nhso_service_pp", "nhso_service_des")
VALUES('11', 'PAPSMEAR', 'ตรวจมะเร็งปากมดลูก');

INSERT INTO "f_nhso_service"("f_nhso_service_id", "nhso_service_pp", "nhso_service_des")
VALUES('12', 'THALASSEMIA', 'ตรวจธาลัสซีเมีย');

INSERT INTO "f_nhso_service"("f_nhso_service_id", "nhso_service_pp", "nhso_service_des")
VALUES('13', 'DEPRESSION', 'คัดกรองภาวะซึมเศร้า');

CREATE TABLE t_nhso_family_address ( 
"t_nhso_family_address_id" varchar(255) NOT NULL ,
"t_health_family_id" varchar(255) NULL DEFAULT ''::character varying,
"f_health_home_charactor_id" varchar(255) NULL DEFAULT ''::character varying,
"family_address_house_no" varchar(255) NULL DEFAULT ''::character varying,
"family_address_community" varchar(255) NULL DEFAULT ''::character varying,
"family_address_village" varchar(255) NULL DEFAULT ''::character varying,
"family_address_soi" varchar(255) NULL DEFAULT ''::character varying,
"family_address_road" varchar(255) NULL DEFAULT ''::character varying,
"family_address_province" varchar(255) NULL DEFAULT ''::character varying,
"family_address_area" varchar(255) NULL DEFAULT ''::character varying,
"family_address_district" varchar(255) NULL DEFAULT ''::character varying,
"family_address_other" varchar(255) NULL DEFAULT ''::character varying,
PRIMARY KEY(t_nhso_family_address_id)
);

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_tt_no" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_group_advice" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_hiv_type" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_hbsag_type" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_hb_type" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_hct_type" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_sugar_albumin_type" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_ultrasound_type" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_vdrl_type" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_cbc_type" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_ua_type" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_blood" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_blood_group" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_rh" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_rh_group" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_ofmcv_type" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_dcip_escreen_type" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_baby_thalassemia_type" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_lab_summary" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_consult_other" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_personal_advice" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_give_folic" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_give_fe" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_give_iodine" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_estimate_serious" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_estimate_serious_value" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_pregnant_exam_value" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_assess_health_value" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_tetanus" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_tetanus_at" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_bring_pragnant" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_bring_pragnant_amount" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_ultrasound" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_ultrasound_value" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_sugar_albumin" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_sugar_albumin_value" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_ofmcv" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_ofmcv_value" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_dcip_escreen" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_dcip_escreen_value" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_wisdom_tooth_extraction_amount" varchar(255) NULL DEFAULT ''::character varying;
ALTER TABLE t_nhso_anc
	ADD COLUMN "health_anc_lime_stone_tooth_extraction_amount" varchar(255) NULL DEFAULT ''::character varying;

CREATE TABLE t_nhso_community ( 
"t_nhso_community_id" varchar(255)NOT NULL,
"community_code" varchar(255) NULL DEFAULT ''::character varying,
"community_name" varchar(255) NULL DEFAULT ''::character varying,
"community_has_certified" varchar(255) NULL DEFAULT ''::character varying,
"community_soi" varchar(255) NULL DEFAULT ''::character varying,
"community_road" varchar(255) NULL DEFAULT ''::character varying,
"community_district" varchar(255) NULL DEFAULT ''::character varying,
"community_area" varchar(255) NULL DEFAULT ''::character varying,
"community_province" varchar(255) NULL DEFAULT ''::character varying,
"community_leader_name" varchar(255) NULL DEFAULT ''::character varying,
"community_leader_pid" varchar(255) NULL DEFAULT ''::character varying,
"community_volunteer_name" varchar(255) NULL DEFAULT ''::character varying,
"community_volunteer_pid" varchar(255) NULL DEFAULT ''::character varying,
"community_volunteer_code" varchar(255) NULL DEFAULT ''::character varying,
"community_volunteer_desc" varchar(255) NULL DEFAULT ''::character varying,
"community_record_date_time" varchar(255) NULL DEFAULT ''::character varying,
"community_modify_date_time" varchar(255) NULL DEFAULT ''::character varying,
"community_cancel_date_time" varchar(255) NULL DEFAULT ''::character varying,
"community_staff_record" varchar(255) NULL DEFAULT ''::character varying,
"community_staff_modify" varchar(255) NULL DEFAULT ''::character varying,
"community_staff_cancel" varchar(255) NULL DEFAULT ''::character varying,
"community_active" varchar(255) NULL DEFAULT ''::character varying,
PRIMARY KEY(t_nhso_community_id)
);

ALTER TABLE t_nhso_dental ADD COLUMN dental_doctor_card_id varchar(255) NULL DEFAULT ''::character varying;		

ALTER TABLE t_nhso_dental ADD COLUMN dental_enamel_tooth varchar(255) NULL DEFAULT ''::character varying;		

ALTER TABLE t_nhso_dental ADD COLUMN dental_enamel_tooth_amount varchar(255) NULL DEFAULT ''::character varying;		

ALTER TABLE t_nhso_dental ADD COLUMN dental_periodontali varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_dental ADD COLUMN dental_goalsealant varchar(255) NULL DEFAULT ''::character varying;	

CREATE TABLE t_nhso_dental_adult
(
  t_nhso_dental_adult_id varchar(255) NOT NULL ,
  t_health_dental_id varchar(255) NULL DEFAULT ''::character varying,
  t_nhso_anc_id varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_certificate_id_dentist varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_consultdent varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_toothimpaction varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_dentclean varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_denture_need varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_residual_ridge_gum varchar(255) NULL DEFAULT ''::character varying,
  dental_dental_adult_permantcar varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_dentalcaries_no varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_dentalcaries varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_permant varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_gumnormal varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_calculus varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_gum_etc varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_crown varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_pulltooth varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_rct varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_wte varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_denture varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_calculus_scrape varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_tooth_root_scrape varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_service_tartar_scrape varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_treat_dent varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_treat_dent_desc varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_other_dentshould varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_doctor_service varchar(255) NULL DEFAULT ''::character varying,
  dental_periodontali varchar(255) NULL DEFAULT ''::character varying,
  dental_type varchar(255) NULL DEFAULT ''::character varying,
  dental_adult_dent_exam varchar(255) NULL DEFAULT ''::character varying,
  PRIMARY KEY (t_nhso_dental_adult_id)
);

ALTER TABLE t_nhso_dental_adult OWNER TO postgres;

CREATE TABLE t_nhso_depression ( 
"t_nhso_depression_id"           varchar(255) NOT NULL,
"t_visit_id"           varchar(255) NULL DEFAULT ''::character varying,
"depression_check2q" varchar(255) NULL DEFAULT ''::character varying,
"depression_check9q" varchar(255) NULL DEFAULT ''::character varying,
"depression_check8q" varchar(255) NULL DEFAULT ''::character varying,
"depression_consult_depres" varchar(255) NULL DEFAULT ''::character varying,
"depression_consult_depres_name" varchar(255) NULL DEFAULT ''::character varying,
"depression_consult_depres_position" varchar(255) NULL DEFAULT ''::character varying,
"depression_consult_depres_certified_id" varchar(255) NULL DEFAULT ''::character varying,
"depression_consult_depres_pid" varchar(255) NULL DEFAULT ''::character varying,
"depression_forroe9q1" varchar(255) NULL DEFAULT ''::character varying,
"depression_forroe9q1_date" varchar(255) NULL DEFAULT ''::character varying,
"depression_forroe9q2" varchar(255) NULL DEFAULT ''::character varying,
"depression_forroe9q2_date" varchar(255) NULL DEFAULT ''::character varying,
"depression_forroe9q3" varchar(255) NULL DEFAULT ''::character varying,
"depression_forroe9q3_date" varchar(255) NULL DEFAULT ''::character varying,
"depression_forroe9q4" varchar(255) NULL DEFAULT ''::character varying,
"depression_forroe9q4_date" varchar(255) NULL DEFAULT ''::character varying,
"depression_record_date_time" varchar(255) NULL DEFAULT ''::character varying,
"depression_modify_date_time" varchar(255) NULL DEFAULT ''::character varying,
"depression_cancel_date_time" varchar(255) NULL DEFAULT ''::character varying,
"depression_staff_record" varchar(255) NULL DEFAULT ''::character varying,
"depression_staff_modify" varchar(255) NULL DEFAULT ''::character varying,
"depression_staff_cancel" varchar(255) NULL DEFAULT ''::character varying,
"depression_active" varchar(255) NULL DEFAULT ''::character varying,
PRIMARY KEY(t_nhso_depression_id)
);

CREATE TABLE t_nhso_dmht_pp ( 
    t_nhso_dmht_pp_id           	varchar(255) NOT NULL,
    t_visit_id                      varchar(255) NULL DEFAULT ''::character varying,
    t_patient_id                    varchar(255) NULL DEFAULT ''::character varying,
    t_health_family_id              varchar(255) NULL DEFAULT ''::character varying,
    vital_sign_weight              	varchar(255) NULL DEFAULT ''::character varying,
    vital_sign_height              	varchar(255) NULL DEFAULT ''::character varying,
    vital_sign_bmi                 	varchar(255) NULL DEFAULT ''::character varying,
    vital_sign_pressure            	varchar(255) NULL DEFAULT ''::character varying,
    vital_sign_waist_inch          	varchar(255) NULL DEFAULT ''::character varying,
    vital_sign_hip_inch            	varchar(255) NULL DEFAULT ''::character varying,
    vital_sign_blood_sugar         	varchar(255) NULL DEFAULT ''::character varying,
    nhso_vital_sign_time_after_meal	varchar(255) NULL DEFAULT ''::character varying,
    nhso_vital_sign_fbs            	varchar(255) NULL DEFAULT ''::character varying,
    nhso_vital_sign_fbs_result     	varchar(255) NULL DEFAULT ''::character varying,
    vital_sign_result              	varchar(255) NULL DEFAULT ''::character varying,
    vital_sign_result_other        	varchar(255) NULL DEFAULT ''::character varying,
    diabetes_has                    varchar(255) NULL DEFAULT ''::character varying,
    diabetes_complication           varchar(255) NULL DEFAULT ''::character varying,
    diabetes_icd10                  varchar(255) NULL DEFAULT ''::character varying,
    diabetes_food                   varchar(255) NULL DEFAULT ''::character varying,
    diabetes_exercise               varchar(255) NULL DEFAULT ''::character varying,
    diabetes_footcheck              varchar(255) NULL DEFAULT ''::character varying,
    diabetes_drug                   varchar(255) NULL DEFAULT ''::character varying,
    diabetes_knowconplication       varchar(255) NULL DEFAULT ''::character varying,
    diabetes_other                  varchar(255) NULL DEFAULT ''::character varying,
    diabetes_other_str              varchar(255) NULL DEFAULT ''::character varying,
    hbd_has                         varchar(255) NULL DEFAULT ''::character varying,
    hbd_complication                varchar(255) NULL DEFAULT ''::character varying,
    hbd_icd10                       varchar(255) NULL DEFAULT ''::character varying,
    hbd_food                        varchar(255) NULL DEFAULT ''::character varying,
    hbd_exercise                    varchar(255) NULL DEFAULT ''::character varying,
    hbd_drinksmoke                  varchar(255) NULL DEFAULT ''::character varying,
    hbd_drug                        varchar(255) NULL DEFAULT ''::character varying,
    hbd_strain                      varchar(255) NULL DEFAULT ''::character varying,
    hbd_other                       varchar(255) NULL DEFAULT ''::character varying,
    hbd_other_str                   varchar(255) NULL DEFAULT ''::character varying,
    visit_begin_date_time           varchar(255) NULL DEFAULT ''::character varying,
    record_date_time                varchar(255) NULL DEFAULT ''::character varying,
    modify_date_time                varchar(255) NULL DEFAULT ''::character varying,
    cancel_date_time                varchar(255) NULL DEFAULT ''::character varying,
    active                          varchar(255) NULL DEFAULT ''::character varying,
    PRIMARY KEY(t_nhso_dmht_pp_id)
);

ALTER TABLE "t_nhso_home"
	ADD COLUMN "t_community_id" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE "t_nhso_home"
	ADD COLUMN "pid_owner" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE "t_nhso_home"
	ADD COLUMN "incup" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_consult_pp"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_consult_fp"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_consult_anc"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_consult_npc"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_consult_mum"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_consult_epi"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_consult_grow"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_consult_old"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_consult_cripple"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_consult_other_desc"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_person_servey"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_person_other"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_miss_appointment"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_activity"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_leader_job"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_leader_job_other"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_leader_name"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_leader_certified_id"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_home_visit
	ADD COLUMN "home_visit_leader_pid"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE "t_nhso_patient"
	ADD COLUMN "nhso_patient_etc_office_name" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE "t_nhso_patient"
	ADD COLUMN "nhso_patient_etc_office_tel" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE "t_nhso_patient"
	ADD COLUMN "nhso_patient_etc_mobile" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE "t_nhso_patient"
	ADD COLUMN "nhso_patient_etc_email" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE "t_nhso_patient"
	ADD COLUMN "nhso_patient_etc_detail_etc" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE "t_nhso_patient"
	ADD COLUMN "nhso_patient_rh_group" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE "t_nhso_patient"
	ADD COLUMN "nhso_patient_etc_office_name" varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_screen_cancer
	ADD COLUMN "screen_cancer_breast_check"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_screen_cancer
	ADD COLUMN "screen_cancer_inform_breast_result"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_screen_cancer
	ADD COLUMN "screen_cancer_uterus_check"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_screen_cancer
	ADD COLUMN "screen_cancer_inform_uterus_result"  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_screen_cancer
	ADD COLUMN "screen_cancer_follow_uterus"  varchar(255) NULL DEFAULT ''::character varying;



CREATE INDEX t_nhso_service_pp_id_pk
    ON t_nhso_service_pp(t_nhso_service_pp_id);

CREATE TABLE "t_nhso_service_pp_out" ( 
	"t_nhso_service_pp_out_id"	varchar(255) NOT NULL ,
	"pp_name"             	 varchar(255) NULL DEFAULT ''::character varying,
	"pp_id"             	 varchar(255) NULL DEFAULT ''::character varying,
	"pp_recompen"             	 varchar(255) NULL DEFAULT ''::character varying,
	"pp_totalpay"         	 varchar(255) NULL DEFAULT ''::character varying,
	"pp_active"           	 varchar(255) NULL DEFAULT ''::character varying,
	"record_date_time"    	 varchar(255) NULL DEFAULT ''::character varying,
	"service_date"        	 varchar(255) NULL DEFAULT ''::character varying,
	"service_new"       	 varchar(255) NULL DEFAULT ''::character varying ,
	"t_health_family_id"         varchar(255) NULL DEFAULT ''::character varying
	);

CREATE INDEX t_nhso_service_pp_out_id_pk
    ON t_nhso_service_pp_out(t_nhso_service_pp_out_id);

CREATE TABLE t_nhso_thalassemia ( 
"t_nhso_thalassemia_id"            varchar(255) NOT NULL,
"t_visit_id"           varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_no"	 varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_pragnant_no" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_pragnant_period" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_hbtyping" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_hbtyping_type" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_hbtyping_value" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_alpha" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_alpha_type" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_alpha_value" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_pnd" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_pnd_type" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_pnd_value" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_mutation" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_mutation_type" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_mutation_value" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_ofmcv_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_ofmcv_type_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_ofmcv_value_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_dcipescreen_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_dcipescreen_type_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_dcipescreen_value_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_hbtyping_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_hbtyping_type_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_hbtyping_value_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_alpha_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_alpha_type_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_alpha_value_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_pnd_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_pnd_type_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_pnd_value_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_mutation_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_mutation_type_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_mutation_value_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_husband_pid_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_card_type_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_card_name_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_card_id_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_check_sum_hb" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_check_sum" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_card_type" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_pid" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_other_card" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_other_card_id" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_record_date_time" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_modify_date_time" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_cancel_date_time" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_staff_record" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_staff_modify" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_staff_cancel" varchar(255) NULL DEFAULT ''::character varying,
"thalassemia_active" varchar(255) NULL DEFAULT ''::character varying,
PRIMARY KEY(t_nhso_thalassemia_id)
);

ALTER TABLE t_nhso_visit_home
    ADD COLUMN visit_home_clean_wen   varchar(255) NULL DEFAULT ''::character varying;
ALTER TABLE t_nhso_visit_home
    ADD COLUMN  visit_home_drug_advice   varchar(255) NULL DEFAULT ''::character varying;
ALTER TABLE t_nhso_visit_home
    ADD COLUMN  b_nhso_job_id   varchar(255) NULL DEFAULT ''::character varying;
ALTER TABLE t_nhso_visit_home
    ADD COLUMN   visit_home_job_name   varchar(255) NULL DEFAULT ''::character varying;
ALTER TABLE t_nhso_visit_home
    ADD COLUMN   visit_home_leader_name   varchar(255) NULL DEFAULT ''::character varying;
ALTER TABLE t_nhso_visit_home
    ADD COLUMN   visit_home_job_certificate_id   varchar(255) NULL DEFAULT ''::character varying;
ALTER TABLE t_nhso_visit_home
    ADD COLUMN    visit_home_pid   varchar(255) NULL DEFAULT ''::character varying;
ALTER TABLE t_nhso_visit_home
    ADD COLUMN    visit_home_train   varchar(255) NULL DEFAULT ''::character varying;

INSERT INTO "f_nhso_service"("f_nhso_service_id", "nhso_service_pp", "nhso_service_des")
VALUES('14', 'ANCCONSULT', 'ให้คำปรึกษาหญิงตั้งครรภ์');

INSERT INTO "f_nhso_service"("f_nhso_service_id", "nhso_service_pp", "nhso_service_des")
VALUES('15', 'ANCDENT', 'ทันตกรรมหญิงตั้งครรภ์');

ALTER TABLE t_nhso_service_pp
	ADD COLUMN "t_order_id" varchar(255) NULL DEFAULT ''::character varying;


ALTER TABLE t_nhso_pp ADD COLUMN nhso_pp_cordblood varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_pp ADD COLUMN nhso_pp_perforateblood varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_pp ADD COLUMN nhso_pp_serum varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_main_disease varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_main_disease_other varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_highblood_pressure varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_diabetes varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_heart_diabetes varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_heart_storke varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_copd varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_asthma varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_tuberculosis varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_child_and_mom varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_patien_final varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_renalfail varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_forrow_child varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_aids varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_patien_live  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_patien_live_state  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_patien_live_problem_meet  varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_patien_live_action varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_patien_live_careplans varchar(255) NULL DEFAULT ''::character varying;
        
ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_patien_live_sugges varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_consent varchar(255) NULL DEFAULT ''::character varying;
        
ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_don_consent_detail  varchar(255) NULL DEFAULT ''::character varying;     

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_appointment  varchar(255) NULL DEFAULT ''::character varying;     

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_appointment_nextdate varchar(255) NULL DEFAULT ''::character varying;     
            
ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_nomeet_patient varchar(255) NULL DEFAULT ''::character varying;  

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_novisit_patient varchar(255) NULL DEFAULT ''::character varying;  
      
ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_point_service varchar(255) NULL DEFAULT ''::character varying;  	
   
ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_house_no varchar(255) NULL DEFAULT ''::character varying;  

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_community varchar(255) NULL DEFAULT ''::character varying;  

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_village varchar(255) NULL DEFAULT ''::character varying;  

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_soi varchar(255) NULL DEFAULT ''::character varying;  

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_province varchar(255) NULL DEFAULT ''::character varying;  

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_area varchar(255) NULL DEFAULT ''::character varying;  

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_district varchar(255) NULL DEFAULT ''::character varying;  

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_respon_area varchar(255) NULL DEFAULT ''::character varying;  

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_style_home varchar(255) NULL DEFAULT ''::character varying;  

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_style_detail varchar(255) NULL DEFAULT ''::character varying;  

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_person_service varchar(255) NULL DEFAULT ''::character varying;  

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_other_job_name varchar(255) NULL DEFAULT ''::character varying;  

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_person_service_head varchar(255) NULL DEFAULT ''::character varying;  

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_other_service_head varchar(255) NULL DEFAULT ''::character varying; 

ALTER TABLE t_nhso_pregnancy ADD COLUMN nhso_pregnancy_notremember_last_men_date varchar(255) NULL DEFAULT ''::character varying;

ALTER TABLE t_nhso_pregnancy ADD COLUMN nhso_pregnancy_notremember_pragnant_time varchar(255) NULL DEFAULT ''::character varying; 

CREATE TABLE "t_nhso_service_pp" ( 
	"t_nhso_service_pp_id"	varchar(255) NOT NULL,
	"t_visit_id"          	 varchar(255) NULL DEFAULT ''::character varying,
	"t_order_id"          	 varchar(255) NULL DEFAULT ''::character varying,
	"pp_name"             	 varchar(255) NULL DEFAULT ''::character varying,
	"pp_id"             	 varchar(255) NULL DEFAULT ''::character varying,
	"pp_recompen"            varchar(255) NULL DEFAULT ''::character varying,
	"pp_totalpay"         	 varchar(255) NULL DEFAULT ''::character varying,
	"pp_active"           	 varchar(255) NULL DEFAULT ''::character varying,
	"record_date_time"    	 varchar(255) NULL DEFAULT ''::character varying,
	"service_date"        	 varchar(255) NULL DEFAULT ''::character varying,
	"service_new"       	 varchar(255) NULL DEFAULT ''::character varying 
	);

ALTER TABLE t_nhso_nutrition ADD COLUMN health_nutrition_check_body varchar(255) NULL DEFAULT ''::character
varying;

ALTER TABLE t_nhso_nutrition ADD COLUMN health_nutrition_thyroid varchar(255) NULL DEFAULT ''::character
varying;

ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_road varchar(255) NULL DEFAULT ''::character varying;  

INSERT INTO s_nhso_version("s_version_id", "version_number", "version_description", "version_application_number", "version_database_number", "version_update_time")
VALUES('9701000000117', '17', 'Hospital OS, Community Edition', '3.14.1051', '0.5.300909', '2552-10-07,19:00:00');

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

insert into s_script_update_log values ('nhso','update_nhso_ph7.sql',(select current_date) || ','|| (select current_time),'update NHSO ph6 -> ph7');