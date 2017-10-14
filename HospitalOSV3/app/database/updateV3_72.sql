------เป็นความผิดพลาดหลังจากไปขึ้นระบบให้ที่เวียงสาแล้วพบบักของโปรแกรมที่เกิดจากฐานข้อมูล

alter table t_borrow_film_xray add borrow_to_other varchar(255) default '';
alter table t_borrow_opdcard add borrow_opd_to_other varchar(255) default '';
drop index sub_home_home_id;

CREATE INDEX sub_home_home_id ON t_health_sub_home USING btree (t_health_home_id);

------ amp 25/7/2549 --------
------ เปลี่ยนชื่อ column  ในกรณีของฐานข้อมูลเวียงสา---
alter table b_template_dx rename column b_clinic_id to b_visit_clinic_id;
------henbe 27/7/2549--------
------  b_visit_clinic_id ในฐานตั้งต้นยังไม่มีต้องเพิ่มก่อน  ส่วน b_clinic_id 
-- นั้นมันเพิ่มมาเฉพาะของเวียงสาเท่านั้นไม่ได้มีอยู่ใน script column  ---
alter table b_template_dx add column b_visit_clinic_id varchar(255) default '1313085667988';

CREATE INDEX herb_home_id ON t_health_home_herb USING btree (t_health_home_id);

------ amp 31/7/2549 --------
------ เพิ่มสถานะการนัด ---
INSERT INTO f_appointment_status VALUES ('4', 'มาก่อนนัด', 'มาเข้ารับบริการก่อนวันที่นัด');
INSERT INTO f_appointment_status VALUES ('5', 'มาหลังนัด', 'มาเข้ารับบริการหลังวันที่นัด');

------ tuk 31/07/2549 ----------
------- เพิ่ม column เวลาฉายรังสี -----------
alter table t_result_xray add column result_xray_time varchar (255)  default '';

----Pu 04/08/2549---------
---เพิ่มตาราง รายการ Dose ย่อ---
CREATE TABLE b_item_drug_dose_shortcut (
    b_item_drug_dose_shortcut_id character varying(25) NOT NULL,
    item_drug_dose_shortcut_number character varying(255),
    item_drug_dose_shortcut_description character varying(255),
    b_item_drug_instruction_id character varying(255),
    b_item_drug_frequency_id character varying(255),
    item_drug_dose_shortcut_qty character varying(255),
    item_drug_dose_shortcut_active character varying(255)
);
INSERT INTO b_item_drug_dose_shortcut VALUES ('2910000000001', '', '', '', '', '', '1');

ALTER TABLE public.b_item_drug_dose_shortcut OWNER TO postgres;
ALTER TABLE ONLY b_item_drug_dose_shortcut
    ADD CONSTRAINT b_item_drug_dose_shortcut_id PRIMARY KEY (b_item_drug_dose_shortcut_id);
ALTER INDEX public.b_item_drug_dose_shortcut_id OWNER TO postgres;



INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('512', '5508', 'รายการ Dose ย่อ', '1', 'เวชระเบียน', '0', '0', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('513', '5508', 'รายการ Dose ย่อ', '2', 'พยาบาล', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('514', '5508', 'รายการ Dose ย่อ', '3', 'แพทย์', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('515', '5508', 'รายการ Dose ย่อ', '4', 'LAB', '0', '0', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('516', '5508', 'รายการ Dose ย่อ', '5', 'X-RAY', '0', '0', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('517', '5508', 'รายการ Dose ย่อ', '6', 'เภสัชกร', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('518', '5508', 'รายการ Dose ย่อ', '7', 'แคชเชียร์', '0', '0', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('519', '5508', 'รายการ Dose ย่อ', '8', 'เวชสถิติ', '0', '0', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('520', '5508', 'รายการ Dose ย่อ', '9', 'ADMIN', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('521', '5508', 'รายการ Dose ย่อ', '10', 'ONE STOP SERVICE', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('522', '5508', 'รายการ Dose ย่อ', '11', 'งานประกัน', '0', '0', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('523', '5508', 'รายการ Dose ย่อ', '12', 'งานส่งเสริม', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('524', '5508', 'รายการ Dose ย่อ', '13', 'งานผู้ป่วยใน', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('525', '5508', 'รายการ Dose ย่อ', '14', 'ทันตกรรม', '1', '1', ' ');

----sumo 04/08/2549---------
---เพิ่มตาราง ตัวช่วยคำแนะนำ---
CREATE TABLE b_guide (
    b_guide_id character varying(255) NOT NULL,
    guide_number character varying(255),
    guide_description character varying(255),
    guide_active character varying(255),
    CONSTRAINT b_guide_pkey PRIMARY KEY (b_guide_id)
);
---สร้าง Index ของ b_guide_id ให้ตาราง b_guide-----
CREATE INDEX guide_id ON b_guide USING btree (b_guide_id);
INSERT INTO b_guide VALUES ('3130000000001', '01', 'สุขศึกษารายบุคคล : ', '1');
INSERT INTO b_guide VALUES ('3130000000002', '02', 'สุขศึกษารายกลุ่ม : ', '1');
INSERT INTO b_guide VALUES ('3130000000003', '03', 'สุขศึกษารายครอบครัว : ', '1');
INSERT INTO b_guide VALUES ('3130000000004', '04', 'D-Method โรค : ', '1');
INSERT INTO b_guide VALUES ('3130000000005', '05', 'นัด Home visit : ', '1');
INSERT INTO b_guide VALUES ('3130000000006', '06', 'นัด Home health care : ', '1');
INSERT INTO b_guide VALUES ('3130000000007', '07', 'อื่นๆ : ', '1');
INSERT INTO b_guide VALUES ('3130000000008', '08', 'การออกกำลังกาย', '1');
INSERT INTO b_guide VALUES ('3130000000009', '09', 'การควบคุมอาหาร', '1');
INSERT INTO b_guide VALUES ('3130000000010', '10', 'การปฏิบัติตนทั่วไป', '1');
INSERT INTO b_guide VALUES ('3130000000011', '11', 'การใช้ยาที่ถูกต้อง', '1');
INSERT INTO b_guide VALUES ('3130000000012', '12', 'การพักผ่อนและลดความเครียด', '1');
INSERT INTO b_guide VALUES ('3130000000013', '13', 'การมาตรวจตามนัด', '1');
INSERT INTO b_guide VALUES ('3130000000014', '14', 'สมุนไพรที่เกี่ยวข้อง', '1');
INSERT INTO b_guide VALUES ('3130000000015', '15', 'การให้ความรู้เรื่องโรคและภาวะแทรกซ้อน', '1');
INSERT INTO b_guide VALUES ('3130000000016', '16', 'ดื่มน้ำมากๆ', '1');
INSERT INTO b_guide VALUES ('3130000000017', '17', 'อย่ากินเค็ม', '1');
INSERT INTO b_guide VALUES ('3130000000018', '18', 'อย่ากินหวาน', '1');
INSERT INTO b_guide VALUES ('3130000000019', '19', 'อย่านอนดึก', '1');
INSERT INTO b_guide VALUES ('3130000000020', '20', 'พักผ่อนให้เพียงพอ', '1');

----sumo 08/08/2549---------
--- เพิ่ม field เก็บข้อมูลการนัด,การ admit ,การ refer ,appointment_id,ตัวช่วยคำนวณวันที่นัด และสาเหตุการนัดในตาราง t_visit ----
alter table t_visit add visit_have_appointment varchar(255) default '0';
alter table t_visit add visit_have_admit varchar(255) default '0';
alter table t_visit add visit_have_refer varchar(255) default '0';
alter table t_visit add t_patient_appointment_id varchar(255) default '';
alter table t_visit add visit_cal_date_appointment varchar(255) default '';
alter table t_visit add visit_cause_appointment varchar(255) default '';
----sumo 08/08/2549---------
---เพิ่มตาราง ช่วงเวลาการนัด---
CREATE TABLE f_calculate_date_appointment (
    f_calculate_date_appointment_id character varying(255) NOT NULL,
    calculate_date_appointment_description character varying(255),
    CONSTRAINT f_calculate_date_appointment_pkey PRIMARY KEY (f_calculate_date_appointment_id)
);
---สร้าง Index ของ cal_date_appointment_id ให้ตาราง f_calculate_date_appointment -----
CREATE INDEX cal_date_appointment_id ON f_calculate_date_appointment USING btree (f_calculate_date_appointment_id);
INSERT INTO f_calculate_date_appointment VALUES ('01', '1 W');
INSERT INTO f_calculate_date_appointment VALUES ('02', '2 W');
INSERT INTO f_calculate_date_appointment VALUES ('03', '3 W');
INSERT INTO f_calculate_date_appointment VALUES ('04', '4 W');
INSERT INTO f_calculate_date_appointment VALUES ('05', '6 W');
INSERT INTO f_calculate_date_appointment VALUES ('06', '8 W');
INSERT INTO f_calculate_date_appointment VALUES ('07', '12 W');
----sumo 09/08/2549---------
---เพิ่ม field เก็บข้อมูลคำแนะนำ ในตาราง t_visit_diag_map---
alter table t_visit_diag_map add t_visit_discharge_advice_id varchar(255) default '';
CREATE INDEX visit_advice_id ON t_visit_diag_map USING btree (t_visit_discharge_advice_id);
-----------------------------------------------------------------------------------------------------------------------------------------

----Pu 08/08/2549---------
--เพิ่มตารางรายการ Item ที่ Map กับ Dx--
CREATE TABLE b_template_dx_map_item (
    b_template_dx_map_item_id character varying(25) NOT NULL,
    b_template_dx_id character varying(255),
    b_item_id character varying(255)
);
ALTER TABLE public.b_template_dx_map_item OWNER TO postgres;
ALTER TABLE ONLY b_template_dx_map_item
    ADD CONSTRAINT b_template_dx_map_item_id PRIMARY KEY (b_template_dx_map_item_id);
ALTER INDEX public.b_template_dx_map_item_id OWNER TO postgres;

--เพิ่มฟิลด์ข้อมูล งานวางแผนครอบครัว
alter table t_health_family_planing add health_family_planing_cervix_method varchar(255) default '';
--เพิ่มฟิลด์ข้อมูล Vitalsign ในการเยื่ยมบ้าน
CREATE TABLE t_health_visit_home_vitalsign
(
   t_health_visit_home_vitalsign_id varchar(255), 
   health_vitalsign_height varchar(255), 
   health_vitalsign_weight varchar(255), 
   health_vitalsign_blood_presure varchar(255), 
   health_vitalsign_temperature varchar(255), 
   health_vitalsign_heart_rate varchar(255), 
   health_vitalsign_respiratory_rate varchar(255), 
   f_visit_nutrition_level_id varchar(255), 
   health_vitalsign_bmi varchar(255), 
   health_vitalsign_check_date varchar(255), 
   health_vitalsign_check_time varchar(255), 
   health_vitalsign_record_time varchar(255), 
   health_vitalsign_record_date varchar(255), 
   health_vitalsign_staff_record varchar(255), 
   health_vitalsign_staff_modify varchar(255), 
   health_vitalsign_modify_date_time varchar(255), 
   health_vitalsign_cancle_date_time varchar(255), 
   health_vitalsign_staff_cancle varchar(255), 
   health_vitalsign_active varchar(255), 
   t_health_visit_home_id varchar(255), 
    PRIMARY KEY ( t_health_visit_home_vitalsign_id)
) WITH OIDS;

CREATE INDEX  homeVisit_vitalSign
  ON t_health_visit_home_vitalsign
  USING btree
  (t_health_visit_home_id);

  CREATE UNIQUE INDEX homevisit_vitalsign_pk
  ON t_health_visit_home_vitalsign
  USING btree
  (t_health_visit_home_vitalsign_id);

----amp 10/08/2549---------
--เพิ่มตารางเกี่ยวกับตัวช่วยนัด--
CREATE TABLE b_template_appointment (
    b_template_appointment_id character varying(255) NOT NULL,
    template_appointment_name character varying(255),
    template_appointment_date character varying(255),
    template_appointment_date_to character varying(255),
    template_appointment_time character varying(255),
    template_appointment_aptype character varying(255),
    template_appointment_service_point character varying(255),
    template_appointment_doctor character varying(255),
    template_appointment_description character varying(4000),
    template_appointment_queue_visit_id character varying(255),
    template_appointment_staff_record character varying(255),
    template_appointment_record_date_time character varying(255),
    template_appointment_staff_update character varying(255),
    template_appointment_update_date_time character varying(255)
);
INSERT INTO b_template_appointment VALUES ('2930000000001', 'DM', '', '', '', '', '', '', '', '', '', '', '', '');
INSERT INTO b_template_appointment VALUES ('2930000000002', 'HT', '', '', '', '', '', '', '', '', '', '', '', '');
INSERT INTO b_template_appointment VALUES ('2930000000003', 'H', '', '', '', '', '', '', '', '', '', '', '', '');
ALTER TABLE ONLY b_template_appointment
    ADD CONSTRAINT b_template_appointment_pkey PRIMARY KEY (b_template_appointment_id);
ALTER INDEX public.b_template_appointment_pkey OWNER TO postgres;

---เพิ่มตารางเก็บ Item ตัวช่วยนัด---
CREATE TABLE b_template_appointment_item (
    b_template_appointment_item_id character varying(255) NOT NULL,
    b_template_appointment_id character varying(255),
    b_item_id character varying(255)
);
ALTER TABLE ONLY b_template_appointment_item
    ADD CONSTRAINT b_template_appointment_item_pkey PRIMARY KEY (b_template_appointment_item_id);
ALTER INDEX public.b_template_appointment_item_pkey OWNER TO postgres;

---หนึ่งเพิ่มการเก็บข้อมูลการฝากครรถ์
ALTER TABLE t_health_anc ADD COLUMN health_anc_hct_result varchar(255) DEFAULT '';
ALTER TABLE t_health_anc ADD COLUMN f_health_anc_tt_type varchar(255) DEFAULT '';


CREATE TABLE f_health_tt_type (
    tt_type_number character varying(255) NOT NULL,
    tt_type_description character varying(255)
);
ALTER TABLE public.f_health_tt_type OWNER TO postgres;

--COPY f_health_tt_type (tt_type_number, tt_type_description) FROM stdin;
insert into f_health_tt_type values ('1','เข็มที่1');
insert into f_health_tt_type values ('2','เข็มที่2');
insert into f_health_tt_type values ('3','กระตุ้น');
insert into f_health_tt_type values ('4','ในระยะคุ้มครอง');
------------------
------การปรับแก้ในเวอร์ชัน 3.07 สำหรับ update จากเวอร์ชัน 3.06
create table s_script_update_log(
	script_update_log_modname varchar(255),
	script_update_log_filename varchar(255),
	script_update_log_datetime varchar(255),
	script_update_log_note varchar(255)
);

------------- tuk: 25/08/2549 ----------------
------------- เพิ่มรหัสฟิล์ม Xray ----------------
update f_xray_film_size set xray_film_size_number = '01' where f_xray_film_size_id = '1';
update f_xray_film_size set xray_film_size_number = '02' where f_xray_film_size_id = '2';
update f_xray_film_size set xray_film_size_number = '03' where f_xray_film_size_id = '3';
update f_xray_film_size set xray_film_size_number = '04' where f_xray_film_size_id = '4';
update f_xray_film_size set xray_film_size_number = '05' where f_xray_film_size_id = '5';
update f_xray_film_size set xray_film_size_number = '06' where f_xray_film_size_id = '6';
update f_xray_film_size set xray_film_size_number = '07' where f_xray_film_size_id = '7';

---sumo 06/09/2549-----
---เพิ่มหัวข้อ authen ของเมนูพิมพ์ใบสรุปค่าใช้จ่ายตาม 16 กลุ่ม กับเมนูพิมพใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)----
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('0813', 'ใบสรุปค่าใช้จ่ายตาม 16 กลุ่ม', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('0814', 'ใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)', '');
------Pu: 28/08/2549------
---เพิ่มสถานะการมองเห็นเมนูการพิมพ์รายการ 16 กลุ่มาตรฐาน---
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('539', '0813', 'รายการ 16 กลุ่มมาตรฐาน', '1', 'เวชระเบียน', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('540', '0813', 'รายการ 16 กลุ่มมาตรฐาน', '2', 'พยาบาล', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('541', '0813', 'รายการ 16 กลุ่มมาตรฐาน', '3', 'แพทย์', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('542', '0813', 'รายการ 16 กลุ่มมาตรฐาน', '4', 'LAB', '0', '0', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('543', '0813', 'รายการ 16 กลุ่มมาตรฐาน', '5', 'X-RAY', '0', '0', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('544', '0813', 'รายการ 16 กลุ่มมาตรฐาน', '6', 'เภสัชกร', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('545', '0813', 'รายการ 16 กลุ่มมาตรฐาน', '7', 'แคชเชียร์', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('546', '0813', 'รายการ 16 กลุ่มมาตรฐาน', '8', 'เวชสถิติ', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('547', '0813', 'รายการ 16 กลุ่มมาตรฐาน', '9', 'ADMIN', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('548', '0813', 'รายการ 16 กลุ่มมาตรฐาน', '10', 'ONE STOP SERVICE', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('549', '0813', 'รายการ 16 กลุ่มมาตรฐาน', '11', 'งานประกัน', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('550', '0813', 'รายการ 16 กลุ่มมาตรฐาน', '12', 'งานส่งเสริม', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('551', '0813', 'รายการ 16 กลุ่มมาตรฐาน', '13', 'งานผู้ป่วยใน', '1', '1', ' ');
----sumo 21/08/2549---------
--update field template_dx_guideafterdx กับ template_dx_thaidescription ให้เท่ากับ '' ถ้ามีข้อมูลเป็น  null--
update b_template_dx set template_dx_guideafterdx = '' where template_dx_guideafterdx is null;
update b_template_dx set template_dx_thaidescription= '' where template_dx_thaidescription is null;
---sumo 06/09/2549-----
---เพิ่ม authen ของเมนูพิมพใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)----
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('554', '0814', 'ใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)', '1', 'เวชระเบียน', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('555', '0814', 'ใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)', '2', 'พยาบาล', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('556', '0814', 'ใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)', '3', 'แพทย์', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('557', '0814', 'ใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)', '4', 'LAB', '0', '0', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('558', '0814', 'ใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)', '5', 'X-RAY', '0', '0', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('559', '0814', 'ใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)', '6', 'เภสัชกร', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('560', '0814', 'ใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)', '7', 'แคชเชียร์', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('561', '0814', 'ใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)', '8', 'เวชสถิติ', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('562', '0814', 'ใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)', '9', 'ADMIN', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('563', '0814', 'ใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)', '10', 'ONE STOP SERVICE', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('564', '0814', 'ใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)', '11', 'งานประกัน', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('565', '0814', 'ใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)', '12', 'งานส่งเสริม', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('566', '0814', 'ใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)', '13', 'งานผู้ป่วยใน', '1', '1', ' ');
INSERT INTO f_gui_action_authen (f_gui_action_authen_id, f_gui_action_id, gui_action_authen_gui_name, f_authentication_id, gui_action_authen_authentication_name, gui_action_authen_is_read, gui_action_authen_is_write, gui_action_authen_note) VALUES ('567', '0814', 'ใบสรุปค่าใช้จ่ายตามรายการ(รวมรายการชื่อเดียวกัน)', '14', 'ทันตกรรม', '1', '1', ' ');

-------  Tuk 06/09/2549 ------------
------- ีupdate รหัสประเภทโรคของกุมารเวช ให้เป็น 00:03
update b_visit_clinic set visit_clinic_number = '00:03' where visit_clinic_description like 'กุมารเวช';

--henbe 23/09/06--------------------
-------เปลี่ยนแปลงรหัสอาชีพให้ถูกต้องตามมาตรฐานรายงาน 12 แฟ้ม
-----------------------------------------------------------------------
update t_patient set f_patient_occupation_id = '126' where f_patient_occupation_id = '125' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '125' where f_patient_occupation_id = '124' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '124' where f_patient_occupation_id = '123' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '123' where f_patient_occupation_id = '122' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '122' where f_patient_occupation_id = '121' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '121' where f_patient_occupation_id = '120' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '120' where f_patient_occupation_id = '119' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '119' where f_patient_occupation_id = '118' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '118' where f_patient_occupation_id = '117' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '117' where f_patient_occupation_id = '116' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '116' where f_patient_occupation_id = '115' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '115' where f_patient_occupation_id = '114' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '114' where f_patient_occupation_id = '113' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '113' where f_patient_occupation_id = '112' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '112' where f_patient_occupation_id = '111' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '111' where f_patient_occupation_id = '110' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '110' where f_patient_occupation_id = '109' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '109' where f_patient_occupation_id = '108' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '108' where f_patient_occupation_id = '107' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '107' where f_patient_occupation_id = '106' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '106' where f_patient_occupation_id = '105' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_patient set f_patient_occupation_id = '105' where f_patient_occupation_id = '104' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 

update t_health_family set f_patient_occupation_id = '126' where f_patient_occupation_id = '125' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '125' where f_patient_occupation_id = '124' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '124' where f_patient_occupation_id = '123' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '123' where f_patient_occupation_id = '122' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '122' where f_patient_occupation_id = '121' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '121' where f_patient_occupation_id = '120' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '120' where f_patient_occupation_id = '119' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '119' where f_patient_occupation_id = '118' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '118' where f_patient_occupation_id = '117' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '117' where f_patient_occupation_id = '116' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '116' where f_patient_occupation_id = '115' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '115' where f_patient_occupation_id = '114' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '114' where f_patient_occupation_id = '113' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '113' where f_patient_occupation_id = '112' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '112' where f_patient_occupation_id = '111' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '111' where f_patient_occupation_id = '110' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '110' where f_patient_occupation_id = '109' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '109' where f_patient_occupation_id = '108' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '108' where f_patient_occupation_id = '107' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '107' where f_patient_occupation_id = '106' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '106' where f_patient_occupation_id = '105' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 
update t_health_family set f_patient_occupation_id = '105' where f_patient_occupation_id = '104' and (select patient_occupation_description from f_patient_occupation where f_patient_occupation_id = '104') = 'ช่างเทคนิควิศวกรรม'; 

delete from f_patient_occupation where f_patient_occupation_id= '127';
delete from f_patient_occupation where f_patient_occupation_id= '126';
delete from f_patient_occupation where f_patient_occupation_id= '125';
delete from f_patient_occupation where f_patient_occupation_id= '124';
delete from f_patient_occupation where f_patient_occupation_id= '123';
delete from f_patient_occupation where f_patient_occupation_id= '122';
delete from f_patient_occupation where f_patient_occupation_id= '121';
delete from f_patient_occupation where f_patient_occupation_id= '120';
delete from f_patient_occupation where f_patient_occupation_id= '119';
delete from f_patient_occupation where f_patient_occupation_id= '118';
delete from f_patient_occupation where f_patient_occupation_id= '117';
delete from f_patient_occupation where f_patient_occupation_id= '116';
delete from f_patient_occupation where f_patient_occupation_id= '115';
delete from f_patient_occupation where f_patient_occupation_id= '114';
delete from f_patient_occupation where f_patient_occupation_id= '113';
delete from f_patient_occupation where f_patient_occupation_id= '112';
delete from f_patient_occupation where f_patient_occupation_id= '111';
delete from f_patient_occupation where f_patient_occupation_id= '110';
delete from f_patient_occupation where f_patient_occupation_id= '109';
delete from f_patient_occupation where f_patient_occupation_id= '108';
delete from f_patient_occupation where f_patient_occupation_id= '107';
delete from f_patient_occupation where f_patient_occupation_id= '106';
delete from f_patient_occupation where f_patient_occupation_id= '105';

update f_patient_occupation set f_patient_occupation_id = '127' where patient_occupation_description like 'นักร้อง';
update f_patient_occupation set f_patient_occupation_id = '126' where patient_occupation_description like 'นักแสดง';
update f_patient_occupation set f_patient_occupation_id = '125' where patient_occupation_description like 'นักหนังสือพิมพ์';
update f_patient_occupation set f_patient_occupation_id = '124' where patient_occupation_description like 'นักข่าว';
update f_patient_occupation set f_patient_occupation_id = '123' where patient_occupation_description like 'นักประพันธ์';
update f_patient_occupation set f_patient_occupation_id = '122' where patient_occupation_description like 'ช่างภาพ';
update f_patient_occupation set f_patient_occupation_id = '121' where patient_occupation_description like 'ช่างศิลป์';
update f_patient_occupation set f_patient_occupation_id = '120' where patient_occupation_description like 'จิตรกร';
update f_patient_occupation set f_patient_occupation_id = '119' where patient_occupation_description like 'ประติมากร';
update f_patient_occupation set f_patient_occupation_id = '118' where patient_occupation_description like 'อัยการ';
update f_patient_occupation set f_patient_occupation_id = '117' where patient_occupation_description like 'ผู้พิพากษา';
update f_patient_occupation set f_patient_occupation_id = '116' where patient_occupation_description like 'พนักงานที่ทำงานช่วยเหลือด้านกา';
update f_patient_occupation set f_patient_occupation_id = '115' where patient_occupation_description like 'ผู้ปฏิบัติงานทางเทคนิคการแพทย์';
update f_patient_occupation set f_patient_occupation_id = '114' where patient_occupation_description like 'เภสัชกร';
update f_patient_occupation set f_patient_occupation_id = '113' where patient_occupation_description like 'พยาบาล';
update f_patient_occupation set f_patient_occupation_id = '112' where patient_occupation_description like 'อาจารย์โรงเรียน';
update f_patient_occupation set f_patient_occupation_id = '111' where patient_occupation_description like 'อาจารย์มหาวิทยาลัย';
update f_patient_occupation set f_patient_occupation_id = '110' where patient_occupation_description like 'สัตวแพทย์';
update f_patient_occupation set f_patient_occupation_id = '109' where patient_occupation_description like 'ทันตแพทย์';
update f_patient_occupation set f_patient_occupation_id = '108' where patient_occupation_description like 'ศัลยแพทย์';
update f_patient_occupation set f_patient_occupation_id = '107' where patient_occupation_description like 'แพทย์';
update f_patient_occupation set f_patient_occupation_id = '106' where patient_occupation_description like 'นักวิทยาศาสตร์';
update f_patient_occupation set f_patient_occupation_id = '105' where patient_occupation_description like 'ช่างเทคนิควิศวกรรม';


--henbe 3/10/2549
--เพิ่่ม option ในส่วนของหน้าจอการสั่งยา

INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('2706', 'ปุ่มพิมพ์ใบสั่งยา', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('2707', 'ปุ่มพิมพ์สติกเกอร์ยา', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('2701', 'ปุ่มคิดเงิน', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('2702', 'ปุ่มยืนยัน', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('2703', 'ปุ่มดำเนินการ', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('2704', 'ปุ่มจ่าย', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('2705', 'ปุ่มยกเลิก', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('5409', 'ชนิดรายงานผลแลป', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('5410', 'อวัยวะร่างกาย', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('5411', 'จับคู่ระดับโภชนาการ', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('5504', 'จำนวนยาที่ใช้สำหรับการพิมพ์', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('5505', 'ยามาตรฐาน', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('5506', 'จัดกลุ่มยามาตรฐาน', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('5507', 'จับคู่ยาที่มีปฏิกิริยาต่อกัน', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('5508', 'รายการ Dose ย่อ', '');


--henbe 11/10/2549
--เพิ่่ม option ในส่วนของลำดับเลขให้ตรวจสอบว่ายังใช้แบบเดิมหรือจะใช้แบบใหม่
alter table b_sequence_data add  sequence_data_active varchar(255) default '0';

INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('5508', 'รายการ Dose ย่อ', '');

--henbe 12/10/2549

INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('2905', 'ปุ่มภาพก่อนพิมพ์ใบเสร็จ', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('2906', 'ปุ่มพิมพ์ใบเสร็จ', '');

--henbe 23/10/2549
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('0607', 'เมนูจำหน่ายผู้ป่วยใน', '');
delete from b_option_detail where b_option_detail_id = 'b1_command';
delete from b_option_detail where b_option_detail_id = 'b1_description';
delete from b_option_detail where b_option_detail_id = 'b1_icon';
insert into b_option_detail values('b1_command','iexplore','');
insert into b_option_detail values('b1_description','ตรวจสอบสิทธิผู้ป่วย','');
insert into b_option_detail values('b1_icon','b1.gif','');

--henbe 24/10/2549
ALTER TABLE t_visit_refer_in_out alter visit_refer_in_out_summary_treatment type varchar(5000);
ALTER TABLE t_visit_refer_in_out alter visit_refer_in_out_family_symptom type varchar(5000);
ALTER TABLE t_visit_refer_in_out alter visit_refer_in_out_current_symptom type varchar(5000);
ALTER TABLE t_visit_refer_in_out alter visit_refer_in_out_summary_investigation type varchar(5000);
ALTER TABLE t_visit_refer_in_out alter visit_refer_in_out_treatment_continue type varchar(5000);
ALTER TABLE t_visit_refer_in_out alter visit_refer_in_out_notice type varchar(5000);
ALTER TABLE t_visit_refer_in_out alter visit_refer_in_out_treatment_result type varchar(5000);
ALTER TABLE t_visit_refer_in_out alter visit_refer_in_out_lab_result type varchar(5000);

alter table t_visit_discharge_advice alter visit_discharge_advice_advice type varchar(5000);
alter table t_visit alter visit_diagnosis_notice type varchar(5000);

--henbe 29/10/2549
update b_item_auto set item_auto_saturday_show ='0',     item_auto_sunday_show ='0' where b_item_auto_id = '113112652494845170';


--ปิดแล้วห้ามแก้ไขเป็นอันขาด
insert into s_script_update_log values ('hospitalOS','updateV3_72.sql',(select current_date) || ','|| (select current_time),'เปลี่ยนเวอร์ชันจาก 3.7 เป็น 3.7 หลังจากน่าน');
------
INSERT INTO s_version VALUES ('9701000000018', '28', 'Hospital OS, Community Edition', '3.7.250706', '3.14.270706', '2549-07-27 12:25:00');
---

