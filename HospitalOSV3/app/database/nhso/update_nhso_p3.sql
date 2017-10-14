--ALTER TABLE t_patient ALTER sql_template_sql SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_firstname SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_lastname SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_xn SET DEFAULT '';
ALTER TABLE t_patient ALTER f_sex_id SET DEFAULT '1';
ALTER TABLE t_patient ALTER patient_birthday SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_house SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_road SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_moo SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_tambon SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_amphur SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_changwat SET DEFAULT '';
ALTER TABLE t_patient ALTER f_patient_marriage_status_id SET DEFAULT '1';
ALTER TABLE t_patient ALTER f_patient_occupation_id SET DEFAULT '000';
ALTER TABLE t_patient ALTER f_patient_race_id SET DEFAULT '99';
ALTER TABLE t_patient ALTER f_patient_nation_id SET DEFAULT '99';
ALTER TABLE t_patient ALTER f_patient_religion_id SET DEFAULT '1';
ALTER TABLE t_patient ALTER f_patient_education_type_id SET DEFAULT '11';
ALTER TABLE t_patient ALTER f_patient_family_status_id SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_father_firstname SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_mother_firstname SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_couple_firstname SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_move_in_date_time SET DEFAULT '2550-01-01,12:00:00';
ALTER TABLE t_patient ALTER f_patient_discharge_status_id SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_discharge_date_time SET DEFAULT '';
ALTER TABLE t_patient ALTER f_patient_blood_group_id SET DEFAULT '1';
ALTER TABLE t_patient ALTER f_patient_foreigner_id SET DEFAULT '1';
ALTER TABLE t_patient ALTER f_patient_area_status_id SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_father_pid SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_mather_pid SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_couple_pid SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_community_status SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_private_doctor SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_pid SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_mother_lastname SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_father_lastname SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_couple_lastname SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_phone_number SET DEFAULT '';
ALTER TABLE t_patient ALTER f_patient_relation_id SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_contact_phone_number SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_contact_sex_id SET DEFAULT '1';
ALTER TABLE t_patient ALTER patient_contact_house SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_contact_moo SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_contact_changwat SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_contact_amphur SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_contact_tambon SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_contact_road SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_contact_firstname SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_contact_lastname SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_birthday_true SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_merged SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_record_date_time SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_update_date_time SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_staff_record SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_staff_modify SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_staff_cancel SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_active SET DEFAULT '1';
ALTER TABLE t_patient ALTER patient_drugallergy SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_language_for_print SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_patient_mobile_phone SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_contact_mobile_phone SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_has_home_in_pcu SET DEFAULT '0';
ALTER TABLE t_patient ALTER t_health_family_id SET DEFAULT '';
ALTER TABLE t_patient ALTER patient_other_country_address SET DEFAULT '0';
ALTER TABLE t_patient ALTER patient_is_other_country SET DEFAULT '0';

--เพิ่ม Column ในตาราง เพื่อรองรับ Pattern ใหม่
--13-11-07
--pu
---- ตาราง t_nhso_anc
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_date_tt character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_health_study character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_pregnant_exam character varying(255); 
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_assess_health character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_urine_sugar character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_dent_summary character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_dent_treat character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_dent_treat_description character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_hbsag character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_mcv character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_husband_of character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_husband_dcip character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_husband_mcv character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_husband_thalassemia character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_thalassemia_summary character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_husband_of_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_husband_dcip_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_husband_mcv_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_of_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_dcip_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_mcv_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_hiv_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_hbsag_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_vdrl_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_ancres_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_hb_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_albumin_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_vdrl_confirm_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_cbc_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_ua_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_hbt_confirm_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_detail_sugar_result character varying(255);
ALTER TABLE t_nhso_anc ADD COLUMN health_anc_assess_bmi character varying(255);

--เพิ่ม Column ในตาราง เพื่อรองรับ Pattern ใหม่
--13-11-07
--pu
---ตาราง t_nhso_postpartum
ALTER TABLE t_nhso_postpartum ADD COLUMN health_postpartum_pap_smear character varying(255);

--เพิ่ม Column ในตาราง เพื่อรองรับ Pattern ใหม่
--13-11-07
--pu
---ตาราง t_nhso_dental
ALTER TABLE t_nhso_dental ADD COLUMN dental_tooth_check character varying(255);
ALTER TABLE t_nhso_dental ADD COLUMN dental_mouth_clean character varying(255);

--เพิ่ม Column ในตาราง เพื่อรองรับ Pattern ใหม่
--13-11-07
--pu
---ตาราง t_nhso_visit_home
ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_doctor character varying(255);
ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_nurse character varying(255);
ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_healt_officer character varying(255);
ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_physio_officer character varying(255);
ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_other_employee character varying(255);
ALTER TABLE t_nhso_visit_home ADD COLUMN visit_home_other_employee_description character varying(255);

--เพิ่ม Column ในตาราง เพื่อรองรับ Pattern ใหม่
--13-11-07
--pu
---ตาราง t_nhso_nutrition
ALTER TABLE t_nhso_nutrition ADD COLUMN health_nutriton_assess_grow_description character varying(255);
alter table t_nhso_nutrition drop column health_nutrition_active;

--เพิ่ม ตาราง เพื่อรองรับ Pattern ใหม่
--13-11-07
--pu
---ตาราง t_nhso_pregnancy
CREATE TABLE t_nhso_pregnancy (
	t_nhso_pregnancy_id character varying(255) NOT NULL,
	t_health_pregnancy_id character varying(255),
	nhso_pregnancy_pregnant_qty character varying(255),
	nhso_pregnancy_deliver_qty character varying(255),
	nhso_pregnancy_miscarry_qty character varying(255),
	nhso_pregnancy_live_baby_qty character varying(255),
	nhso_pregnancy_husband_pid character varying(255),
	nhso_pregnancy_abnomal_other character varying(255),
	CONSTRAINT t_nhso_pregnancy_pkey PRIMARY KEY (t_nhso_pregnancy_id)
);

--เพิ่ม ตาราง เพื่อรองรับ Pattern ใหม่
--13-11-07
--pu
---ตาราง t_nhso_pp_care
CREATE TABLE t_nhso_pp_care (
	t_nhso_pp_care_id character varying(255) NOT NULL,
	t_health_pp_care_id character varying(255),
	nhso_pp_care_thyroid character varying(255),
	nhso_pp_care_baby_food character varying(255),
	CONSTRAINT t_nhso_pp_care_pkey PRIMARY KEY (t_nhso_pp_care_id)
);

--เพิ่ม ตาราง เพื่อรองรับ Pattern ใหม่
--13-11-07
--pu
---ตาราง f_nhso_baby_food
CREATE TABLE f_nhso_baby_food (
	f_nhso_baby_food_id character varying(255) NOT NULL,
	baby_food_description character varying(255),
	CONSTRAINT f_nhso_baby_food_pkey PRIMARY KEY (f_nhso_baby_food_id)
);

--เพิ่ม ตาราง เพื่อรองรับ Pattern ใหม่
--13-11-07
--pu
---ตาราง t_nhso_pp
CREATE TABLE t_nhso_pp (
	t_nhso_pp_id character varying(255) NOT NULL,
	t_health_pp_id character varying(255),
	pp_pregnant_age character varying(255),
	CONSTRAINT t_nhso_pp_pkey PRIMARY KEY (t_nhso_pp_id)
);

--เพิ่มตารางเพื่อรองรับ Pattern ใหม่
--13-11-07
--Ton
--ตาราง t_nhso_patient
CREATE TABLE t_nhso_patient (
    t_nhso_patient_id character varying(255) NOT NULL,
    t_patient_id character varying(255),
    nhso_patient_soi character varying(255),
    nhso_patient_moo character varying(255),
    nhso_patient_zipcode character varying(255),
    nhso_patient_addreg_status character varying(255),
    nhso_patient_addreg_house character varying(255),
    nhso_patient_addreg_moo character varying(255),
    nhso_patient_addreg_village character varying(255),
   nhso_patient_addreg_soi character varying(255),
   nhso_patient_addreg_road character varying(255),
    nhso_patient_addreg_phone character varying(255),
   nhso_patient_addreg_mobile character varying(255),
   nhso_patient_addreg_zipcode character varying(255),
   nhso_patient_addreg_changwat character varying(255),
   nhso_patient_addreg_amphur character varying(255),
   nhso_patient_addreg_tambon character varying(255),PRIMARY KEY (t_nhso_patient_id)
);

INSERT INTO s_nhso_version("s_version_id", "version_number", "version_description", "version_application_number", "version_database_number", "version_update_time")
VALUES('9701000000016',	'3',	'Hospital OS, Community Edition',	'3.13.1150',	'0.1.141107',	'2550-11-14 18:00:00');


--เพิ่ม ตาราง เพื่อรองรับ Pattern ใหม่
--15-11-07
--aut
---ตาราง f_nhso_baby_food
CREATE TABLE t_nhso_delivery (
	t_nhso_delivery_id character varying(255) NOT NULL,
	t_health_delivery_id character varying(255),
	nhso_delivery_pregnant_age character varying(255),
	CONSTRAINT t_nhso_delivery_pkey PRIMARY KEY (t_nhso_delivery_id)
);
