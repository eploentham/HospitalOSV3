UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000009' WHERE r_eye_disease_code_id = '6010000000009';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000010' WHERE r_eye_disease_code_id = '6010000000010';

UPDATE r_eye_disease_code SET r_eye_group_id = '8130000000009' WHERE r_eye_group_id = '6000000000009';
UPDATE r_eye_disease_code SET r_eye_group_id = '8130000000010' WHERE r_eye_group_id = '6000000000010';



CREATE INDEX visit_financial_discharge_time
    ON public.t_visit(visit_financial_discharge_time);

delete from f_patient_occupation where f_patient_occupation_id = '905'
    or f_patient_occupation_id = '126'
    or f_patient_occupation_id = '803'
    or f_patient_occupation_id = '503';


ALTER TABLE ONLY f_patient_occupation
    ADD CONSTRAINT f_patient_occupation_pkey PRIMARY KEY ( f_patient_occupation_id);

insert into f_patient_occupation values ('905','แม่บ้าน');
insert into f_patient_occupation values ('126','นักแสดง นางแบบ');
insert into f_patient_occupation values ('803','ช่างซ่อมเครื่องไฟฟ้า');
insert into f_patient_occupation values ('203','ข้าราชการทหารเรือ');
insert into f_patient_occupation values ('503','ทำนา');

CREATE VIEW public.t_patient_view
AS
SELECT
	"t_patient"."t_patient_id",
	"t_patient"."patient_hn",
	"t_patient"."patient_firstname",
	"t_patient"."patient_lastname",
	"t_patient"."patient_birthday",
	"t_patient"."patient_pid",
	"f_sex"."sex_description",
	"t_patient"."f_patient_relation_id",
	"f_patient_prefix"."patient_prefix_description",
	"f_patient_occupation"."patient_occupation_description",
	"f_patient_nation"."patient_nation_description",
	"f_patient_marriage_status"."patient_marriage_status_description",
	"f_patient_blood_group"."patient_blood_group_description",
	"tambon"."address_description" as patient_tambon ,
	"amphur"."address_description" as patient_amphur,
	"changwat"."address_description" as patient_changwat
FROM
	"public"."t_patient" "t_patient" 
	left JOIN "public"."f_patient_marriage_status"  "f_patient_marriage_status" 
			ON "t_patient"."f_patient_marriage_status_id" = "f_patient_marriage_status"."f_patient_marriage_status_id" 
	left JOIN "public"."f_patient_nation" "f_patient_nation" 
			ON "t_patient"."f_patient_race_id" = "f_patient_nation"."f_patient_nation_id" 
	left JOIN "public"."f_patient_blood_group" "f_patient_blood_group" 
			ON "t_patient"."f_patient_blood_group_id" = "f_patient_blood_group"."f_patient_blood_group_id" 
	left JOIN "public"."f_patient_occupation"  "f_patient_occupation" 
			ON "t_patient"."f_patient_occupation_id" = "f_patient_occupation"."f_patient_occupation_id" 
	left JOIN "public"."f_sex" "f_sex" 
			ON "t_patient"."f_sex_id" = "f_sex"."f_sex_id" 
	left JOIN "public"."f_patient_relation"	"f_patient_relation" 
			ON "t_patient"."f_patient_religion_id" = "f_patient_relation"."f_patient_relation_id" 
	left JOIN "public"."f_patient_prefix"  "f_patient_prefix" 
			ON "t_patient"."f_patient_prefix_id" = "f_patient_prefix"."f_patient_prefix_id" 
	left JOIN (select * from f_address where address_tambon_type = '1') as changwat
			ON "t_patient"."patient_changwat" = "changwat"."f_address_id"
	left JOIN (select * from f_address where address_tambon_type = '2') as amphur
			ON t_patient.patient_amphur = "amphur"."f_address_id"
	left JOIN (select * from f_address where address_tambon_type = '3') as tambon
			ON "t_patient"."patient_tambon" = "tambon"."f_address_id"

order by t_patient.patient_hn;

