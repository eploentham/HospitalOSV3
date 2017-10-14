---------------------------------------------------------- DENTAL Version 1.1.070707----------------------------------------
--- สร้างตาราง t_dental_order ใหม่ เนื่องจากมีการเปลี่ยนแปลง Flow การทำงาน
DROP TABLE t_dental_order;
CREATE TABLE t_dental_order (
    t_dental_order_id character varying(255) NOT NULL,
    t_patient_id character varying(255),
    t_visit_id character varying(255),
    t_order_id character varying(255),
    b_dental_item_subgroup_id character varying(255),
    b_dental_uom_id character varying(255),
    dental_order_doctor character varying(255),
    dental_order_comment character varying(4000),
    dental_order_staff_record character varying(255),
    dental_order_staff_modify character varying(255),
    dental_order_record_date_time character varying(255),
    dental_order_modify_date_time character varying(255)
);
ALTER TABLE ONLY t_dental_order
    ADD CONSTRAINT t_dental_order_pkey PRIMARY KEY (t_dental_order_id);
ALTER INDEX public.t_dental_order_pkey OWNER TO postgres;

--- สร้างตาราง t_dental_treat ใหม่ เนื่องจากมีการเปลี่ยนแปลง Flow การทำงาน
DROP TABLE t_dental_treat;
CREATE TABLE t_dental_treat (
    t_dental_treat_id character varying(255) NOT NULL,
    t_patient_id character varying(255),
    t_visit_id character varying(255),
    t_diag_icd_10_id character varying(255),
    t_dental_order_id character varying(255) ,
    t_order_id character varying(255) ,
    f_dental_treat_type_id character varying(255),
    dental_treat_tooth character varying(255),
    dental_treat_side_m character varying(255),
    dental_treat_side_l character varying(255),
    dental_treat_side_d character varying(255),
    dental_treat_side_b character varying(255),
    dental_treat_side_o character varying(255),
    dental_treat_side_r character varying(255),
    b_dental_organ_id character varying(255)
);
ALTER TABLE ONLY t_dental_treat
    ADD CONSTRAINT t_dental_treat_pkey PRIMARY KEY (t_dental_treat_id);
ALTER INDEX public.t_dental_treat_pkey OWNER TO postgres;

--ลบสิทธิในระบบทันตกรรม
delete from f_employee_authentication where f_employee_authentication_id = '14' and employee_authentication_description = 'ทันตกรรม';

--ลบ icd9 ที่ไม่เป็นมาตรฐานสากล
delete from b_icd9 where b_icd9_id >= '0541592369604' and b_icd9_id <= '0541592369647';

--ลบ icd10 ที่ไม่เป็นมาตรฐานสากล
delete from b_icd10 where b_icd10_id >= '550000039053' and b_icd10_id <= '550000039379';

CREATE TABLE b_dental_employee (
    b_dental_employee_id character varying(255) NOT NULL,
    b_employee_id character varying(255),
    employee_login character varying(255),
    employee_firstname character varying(255),
    employee_lastname character varying(255)
);
ALTER TABLE ONLY b_dental_employee
    ADD CONSTRAINT b_dental_employee_pkey PRIMARY KEY (b_dental_employee_id);
ALTER INDEX public.b_dental_employee_pkey OWNER TO postgres;

CREATE TABLE b_dentalrp_service_point (
    b_dentalrp_service_point_id character varying(255) NOT NULL,
    b_service_point_id character varying(255),
    service_point_description character varying(255)
);
ALTER TABLE ONLY b_dentalrp_service_point
    ADD CONSTRAINT b_dentalrp_service_point_pkey PRIMARY KEY (b_dentalrp_service_point_id);
ALTER INDEX public.b_dentalrp_service_point_pkey OWNER TO postgres;

-- ตารางสำหรับรายงานกระทรวง
CREATE TABLE f_dentalrp_government_part1_plan (
    f_dentalrp_government_part1_plan_id character varying(255) NOT NULL,
    f_dentalrp_government_part1_plan_description character varying(255)
);
ALTER TABLE ONLY f_dentalrp_government_part1_plan
    ADD CONSTRAINT f_dentalrp_government_part1_plan_pkey PRIMARY KEY (f_dentalrp_government_part1_plan_id);
ALTER INDEX public.f_dentalrp_government_part1_plan_pkey OWNER TO postgres;
INSERT INTO f_dentalrp_government_part1_plan VALUES ('1', 'ข้าราชการ/รัฐวิสาหกิจ/เบิกต้นสังกัด (ครั้ง)');
INSERT INTO f_dentalrp_government_part1_plan VALUES ('2', 'ประกันสังคม (ครั้ง)');
INSERT INTO f_dentalrp_government_part1_plan VALUES ('3', 'สิทธิ UC (ครั้ง)');
INSERT INTO f_dentalrp_government_part1_plan VALUES ('4', 'สิทธิอื่นๆ (ครั้ง)');

CREATE TABLE b_dentalrp_government_part1_general (
    b_dentalrp_government_part1_general_id character varying(255) NOT NULL,
    f_dentalrp_government_part1_plan_id character varying(255),
    b_contract_plans_id character varying(255),
    b_contract_plans_number character varying(255),
    b_contract_plans_description character varying(255)
);
ALTER TABLE ONLY b_dentalrp_government_part1_general
    ADD CONSTRAINT b_dentalrp_government_part1_general_pkey PRIMARY KEY (b_dentalrp_government_part1_general_id);
ALTER INDEX public.b_dentalrp_government_part1_general_pkey OWNER TO postgres;

CREATE TABLE f_dentalrp_government_part2_item (
    f_dentalrp_government_part2_item_id character varying(255) NOT NULL,
    f_dentalrp_government_part2_item_description character varying(255)
);
ALTER TABLE ONLY f_dentalrp_government_part2_item
    ADD CONSTRAINT f_dentalrp_government_part2_item_pkey PRIMARY KEY (f_dentalrp_government_part2_item_id);
ALTER INDEX public.f_dentalrp_government_part2_item_pkey OWNER TO postgres;
INSERT INTO f_dentalrp_government_part2_item VALUES ('1', 'ตรวจสุขภาพช่องปาก (ครั้ง)');
INSERT INTO f_dentalrp_government_part2_item VALUES ('2', 'sealant (ครั้ง/ซี่)');
INSERT INTO f_dentalrp_government_part2_item VALUES ('3', 'ฟลูออไรด์ (ครั้ง)');
INSERT INTO f_dentalrp_government_part2_item VALUES ('4', 'PRR (ครั้ง/ซี่)');
INSERT INTO f_dentalrp_government_part2_item VALUES ('5', 'การฝึกปฏิบัติแปรงฟัน (ครั้ง)');
INSERT INTO f_dentalrp_government_part2_item VALUES ('6', 'ทำความสะอาดช่องปาก (ครั้ง)');

CREATE TABLE b_dentalrp_government_part2_support_protect (
    b_dentalrp_government_part2_support_protect_id character varying(255) NOT NULL,
    f_dentalrp_government_part2_item_id character varying(255),
    b_item_id character varying(255),
    b_item_number character varying(255),
    b_item_common_name character varying(255)
);
ALTER TABLE ONLY b_dentalrp_government_part2_support_protect
    ADD CONSTRAINT b_dentalrp_government_part2_support_protect_pkey PRIMARY KEY (b_dentalrp_government_part2_support_protect_id);
ALTER INDEX public.b_dentalrp_government_part2_support_protect_pkey OWNER TO postgres;

CREATE TABLE f_dentalrp_government_part3_item (
    f_dentalrp_government_part3_item_id character varying(255) NOT NULL,
    f_dentalrp_government_part3_item_description character varying(255)
);
ALTER TABLE ONLY f_dentalrp_government_part3_item
    ADD CONSTRAINT f_dentalrp_government_part3_item_pkey PRIMARY KEY (f_dentalrp_government_part3_item_id);
ALTER INDEX public.f_dentalrp_government_part3_item_pkey OWNER TO postgres;
INSERT INTO f_dentalrp_government_part3_item VALUES ('1', 'ถอนฟัน (ครั้ง/ซี่)');
INSERT INTO f_dentalrp_government_part3_item VALUES ('2', 'อุดฟัน (ครั้ง/ซี่)');
INSERT INTO f_dentalrp_government_part3_item VALUES ('3', 'ขูดหินปูนทั้งปาก (ครั้ง)');
INSERT INTO f_dentalrp_government_part3_item VALUES ('4', 'ศัลยกรรมช่องปาก (ครั้ง)');
INSERT INTO f_dentalrp_government_part3_item VALUES ('5', 'ทันตกรรมหัตถการ (ครั้ง/ซี่)');
INSERT INTO f_dentalrp_government_part3_item VALUES ('6', 'รักษาคลองรากฟัน (ครั้ง/ซี่)');
INSERT INTO f_dentalrp_government_part3_item VALUES ('7', 'ปริทันต์ (ครั้ง)');
INSERT INTO f_dentalrp_government_part3_item VALUES ('8', 'ทันตกรรมสำหรับเด็ก (ครั้ง)');
INSERT INTO f_dentalrp_government_part3_item VALUES ('9', 'ทันตกรรมประดิษฐ์ (ครั้ง/ชิ้น)');
INSERT INTO f_dentalrp_government_part3_item VALUES ('10', 'ทันตกรรมจัดฟัน (คน/ครั้ง)');

CREATE TABLE b_dentalrp_government_part3_treat (
    b_dentalrp_government_part3_treat_id character varying(255) NOT NULL,
    f_dentalrp_government_part3_item_id character varying(255),
    b_item_id character varying(255),
    b_item_number character varying(255),
    b_item_common_name character varying(255)
);
ALTER TABLE ONLY b_dentalrp_government_part3_treat
    ADD CONSTRAINT b_dentalrp_government_part3_treat_pkey PRIMARY KEY (b_dentalrp_government_part3_treat_id);
ALTER INDEX public.b_dentalrp_government_part3_treat_pkey OWNER TO postgres;

CREATE TABLE f_dentalrp_government_part4_plan (
    f_dentalrp_government_part4_plan_id character varying(255) NOT NULL,
    f_dentalrp_government_part4_plan_description character varying(255)
);
ALTER TABLE ONLY f_dentalrp_government_part4_plan
    ADD CONSTRAINT f_dentalrp_government_part4_plan_pkey PRIMARY KEY (f_dentalrp_government_part4_plan_id);
ALTER INDEX public.f_dentalrp_government_part4_plan_pkey OWNER TO postgres;
INSERT INTO f_dentalrp_government_part4_plan VALUES ('1', 'ข้าราชการ/รัฐวิสาหกิจ');
INSERT INTO f_dentalrp_government_part4_plan VALUES ('2', 'ประกันสังคม');
INSERT INTO f_dentalrp_government_part4_plan VALUES ('3', 'UC ผู้สูงอายุ');
INSERT INTO f_dentalrp_government_part4_plan VALUES ('4', 'สิทธิ UC อื่น');
INSERT INTO f_dentalrp_government_part4_plan VALUES ('5', 'สิทธิอื่น');

CREATE TABLE f_dentalrp_government_part4_item (
    f_dentalrp_government_part4_item_id character varying(255) NOT NULL,
    f_dentalrp_government_part4_item_description character varying(255)
);
ALTER TABLE ONLY f_dentalrp_government_part4_item
    ADD CONSTRAINT f_dentalrp_government_part4_item_pkey PRIMARY KEY (f_dentalrp_government_part4_item_id);
ALTER INDEX public.f_dentalrp_government_part4_item_pkey OWNER TO postgres;
INSERT INTO f_dentalrp_government_part4_item VALUES ('1', 'ARPD (คน/ชิ้น)');
INSERT INTO f_dentalrp_government_part4_item VALUES ('2', 'MRPD,Removeable Bridge (คน/ชิ้น)');
INSERT INTO f_dentalrp_government_part4_item VALUES ('3', 'Single Complete Denture (คน)');
INSERT INTO f_dentalrp_government_part4_item VALUES ('4', 'Complete Denture (คน)');

CREATE TABLE b_dentalrp_government_part4_invent_plan (
    b_dentalrp_government_part4_invent_plan_id character varying(255) NOT NULL,
    f_dentalrp_government_part4_plan_id character varying(255),
    b_contract_plans_id character varying(255),
    b_contract_plans_number character varying(255),
    b_contract_plans_description character varying(255)
);
ALTER TABLE ONLY b_dentalrp_government_part4_invent_plan
    ADD CONSTRAINT b_dentalrp_government_part4_invent_plan_pkey PRIMARY KEY (b_dentalrp_government_part4_invent_plan_id);
ALTER INDEX public.b_dentalrp_government_part4_invent_plan_pkey OWNER TO postgres;

CREATE TABLE b_dentalrp_government_part4_invent_item (
    b_dentalrp_government_part4_invent_item_id character varying(255) NOT NULL,
    f_dentalrp_government_part4_item_id character varying(255),
    b_item_id character varying(255),
    b_item_number character varying(255),
    b_item_common_name character varying(255)
);
ALTER TABLE ONLY b_dentalrp_government_part4_invent_item
    ADD CONSTRAINT b_dentalrp_government_part4_invent_item_pkey PRIMARY KEY (b_dentalrp_government_part4_invent_item_id);
ALTER INDEX public.b_dentalrp_government_part4_invent_item_pkey OWNER TO postgres;

INSERT INTO s_dental_version VALUES ('9730000000002', '2', 'Dental Module Version 1', '1.1.070707', '1.1.070707', '2550-07-07 17:33:33');

---------------------------------------------------------- END OF Version 1.1.070707-----------------------------------------