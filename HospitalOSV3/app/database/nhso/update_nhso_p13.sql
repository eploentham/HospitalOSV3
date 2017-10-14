CREATE TABLE t_nhso_consult( 
	"t_nhso_consult_id"        	varchar(255) NOT NULL DEFAULT '',
	"t_nhso_service_id"        	varchar(255) NOT NULL DEFAULT '',
	"diabetes_has"             	varchar(255) NULL DEFAULT '',
	"diabetes_icd10"           	varchar(255) NULL DEFAULT '',
	"diabetes_food"            	varchar(255) NULL DEFAULT '',
	"diabetes_exercise"        	varchar(255) NULL DEFAULT '',
	"diabetes_footcheck"       	varchar(255) NULL DEFAULT '',
	"diabetes_drug"            	varchar(255) NULL DEFAULT '',
	"diabetes_knowconplication"	varchar(255) NULL DEFAULT '',
	"diabetes_other"           	varchar(255) NULL DEFAULT '',
	"diabetes_other_str"       	varchar(255) NULL DEFAULT '',
	"hbd_has"                  	varchar(255) NULL DEFAULT '',
	"hbd_icd10"                	varchar(255) NULL DEFAULT '',
	"hbd_food"                 	varchar(255) NULL DEFAULT '',
	"hbd_exercise"             	varchar(255) NULL DEFAULT '',
	"hbd_drinksmoke"           	varchar(255) NULL DEFAULT '',
	"hbd_drug"                 	varchar(255) NULL DEFAULT '',
	"hbd_strain"               	varchar(255) NULL DEFAULT '',
	"hbd_other"                	varchar(255) NULL DEFAULT '',
	"hbd_other_str"            	varchar(255) NULL DEFAULT '',
    CONSTRAINT t_nhso_consult_pkey PRIMARY KEY ("t_nhso_consult_id")
	);

CREATE INDEX t_nhso_consult_service_fkey
	ON t_nhso_consult("t_nhso_service_id");

ALTER TABLE t_nhso_pp
	ADD COLUMN "nhso_pp_dead_cause_etc" varchar(255) NULL DEFAULT '';

INSERT INTO f_nhso_epi_group(f_nhso_epi_group_id, epi_group_name) 
    VALUES('20', 'DTP_HB2');

INSERT INTO f_nhso_epi_group(f_nhso_epi_group_id, epi_group_name) 
    VALUES('21', 'DTP_HB4');

INSERT INTO f_nhso_epi_group(f_nhso_epi_group_id, epi_group_name) 
    VALUES('22', 'DTP_HB6');

ALTER TABLE t_nhso_visit_home
	ADD COLUMN "visit_home_type_patient" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_visit_home
	ADD COLUMN "visit_home_get_from" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_visit_home
	ADD COLUMN "visit_home_get_from_hos_name" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_visit_home
	ADD COLUMN "visit_home_result_follow_visit" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_visit_home
	ADD COLUMN "visit_home_classify_patient_group" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_not_decayed_tooth" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_decayed_tooth" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_decayed_tooth_total" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_tooth_impaction" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_gum_empty" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_gum_normal" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_inflame_gum" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_periodontal_inflame" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_gum_periodontal_etc" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_gum_periodontal_etc_detail" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_dental_fillings" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_dental_fillings_total" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_pull_out_tooth" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_pull_out_tooth_total" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_treat_tooth_root" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_treat_tooth_root_total" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_chop_tooth_impaction" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_denture" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_denture_total" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_smooth_root_tooth" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_treat_other" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_treat_other_detail" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_tartar" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_dental_clean_all" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_introduce_brush_teeth" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_employee_id" varchar(255) NULL DEFAULT '';

ALTER TABLE t_nhso_anc
	ADD COLUMN "nhso_anc_license_isjob" varchar(255) NULL DEFAULT '';

UPDATE s_nhso_version
SET "version_application_number"='3.13.1048', "version_database_number"='0.1.240907'
WHERE "s_version_id"='9701000000014';

UPDATE s_nhso_version
SET "version_application_number"='3.13.1048', "version_database_number"='0.1.251007'
WHERE "s_version_id"='9701000000015';

UPDATE s_nhso_version
SET "version_application_number"='3.13.1150', "version_database_number"='0.1.141107'
WHERE "s_version_id"='9701000000016';

UPDATE s_nhso_version
SET "version_application_number"='3.13.1150', "version_database_number"='0.1.161107'
WHERE "s_version_id"='9701000000017';

UPDATE s_nhso_version
SET "version_application_number"='3.13.1150', "version_database_number"='0.1.171107'
WHERE "s_version_id"='9701000000018';

UPDATE s_nhso_version
SET "version_application_number"='3.13.1150', "version_database_number"='0.1.191107'
WHERE "s_version_id"='9701000000019';

UPDATE s_nhso_version
SET "version_application_number"='3.13.1150', "version_database_number"='0.1.261107'
WHERE "s_version_id"='9701000000020';

UPDATE s_nhso_version
SET "version_application_number"='3.13.1150', "version_database_number"='0.1.031207'
WHERE "s_version_id"='9701000000021';

UPDATE s_nhso_version
SET "version_application_number"='3.13.1150', "version_database_number"='0.1.081207'
WHERE "s_version_id"='9701000000022';

UPDATE s_nhso_version
SET "version_application_number"='3.13.1150', "version_database_number"='0.2.220108'
WHERE "s_version_id"='9701000000110';

UPDATE s_nhso_version
SET "version_application_number"='3.14.0351', "version_database_number"='0.3.310308'
WHERE "s_version_id"='9701000000111';

UPDATE s_nhso_version
SET "version_application_number"='3.14.0351', "version_database_number"='0.4.300708'
WHERE "s_version_id"='9701000000112';

INSERT INTO s_nhso_version("s_version_id", "version_number", "version_description", "version_application_number", "version_database_number", "version_update_time")
VALUES('9701000000113', '13', 'Hospital OS, Community Edition', '3.14.1051', '0.5.271008', '2551-10-27 18:00:00');
