
DROP TABLE f_health_reason;

drop table f_health_edu_sys;
drop table f_health_work_place;

drop table t_health_to_be;
drop table f_health_behav_drug;
drop table f_health_behav_strain;
drop table f_health_behav_mood;
drop table f_health_behav_sexbeh;
drop table f_health_behav_sexprotect;
drop table f_health_behav_eat;
drop table f_health_behav_ciga;
drop table f_health_behav_alcohol;
drop table f_health_behav_hx;
drop table f_health_behav_accident;
drop table f_health_result;
drop table f_health_behav_p_type;
drop table t_health_behavior;
drop table f_health_exercise;
drop table t_health_exercise;
drop table f_health_result;
drop table t_health_pressure;
drop table t_health_sweet;
drop table t_health_diabetes;
drop table f_health_is_join_agr;
drop table t_health_elder_agr;
drop table f_product_evalua;
drop table b_health_product;
drop table f_health_screen_num;
drop table t_health_food_screen;

--ทูบีนัมเบอร์วัน
--ตารางรายการเหตุผลในการสมัครโครงการ to be no.1
CREATE TABLE f_health_reason (
    f_health_reason_id character varying(255) NOT NULL,
    reason_name character varying(255),
    CONSTRAINT f_health_reason_pkey PRIMARY KEY (f_health_reason_id)
);

INSERT INTO f_health_reason VALUES('1','เพื่อนชักชวน');
INSERT INTO f_health_reason VALUES('2','มีความสนใจ');
INSERT INTO f_health_reason VALUES('99','อื่นๆ');

--ตารางรายการระดับการศึกษาของผู้สมัครโครงการ to be no.1
--CREATE TABLE f_health_edu_sys (
--    f_health_edu_sys_id character varying(255) NOT NULL,
--    edu_sys_name character varying(255),
--    CONSTRAINT f_health_edu_pkey PRIMARY KEY (f_health_edu_sys_id)
--);

--INSERT INTO f_health_edu_sys VALUES('','');

--ตารางสถานที่เรียน/ทำงานของผู้สมัครโครงการ to be no.1
CREATE TABLE f_health_work_place (
    f_health_work_place_id character varying(255) NOT NULL,
    work_place character varying(255),
    CONSTRAINT f_health_work_place_pkey PRIMARY KEY (f_health_work_place_id)
);

--INSERT INTO f_health_work_place VALUES('','');

--ตารางรายชื่อโรงเรียนเพิ่มเติมจาก PCU
CREATE TABLE f_health_tobe_school (
    f_health_tobe_school_id character varying(255) NOT NULL,
    tobe_school_name character varying(255),
    CONSTRAINT f_healthy_tobe_pkey PRIMARY KEY (f_health_tobe_school_id)
);

INSERT INTO f_health_tobe_school VALUES ('99','อื่นๆ');

--ตารางสมาชิก to be no.1
CREATE TABLE t_health_to_be (
    t_health_to_be_id character varying(255) NOT NULL,
    t_health_family_id character varying(255),
    to_be_is_member character varying(255),
    to_be_regis_date character varying(255),
    to_be_work_place character varying(255),
    to_be_edu_sys character varying(255),
    to_be_reason character varying(255),
    to_be_opinion character varying(255),
    to_be_record_time character varying(255),
    to_be_modify_time character varying(255),
    to_be_cancel_time character varying(255),
    to_be_staff_record character varying(255),
    to_be_staff_modify character varying(255),
    to_be_staff_cancel character varying(255),
    to_be_active character varying(255),
    CONSTRAINT t_health_to_be_pkey PRIMARY KEY (t_health_to_be_id)
);

--คัดกรองพฤติกรรม
--ตารางรายการพฤติกรรมการเสพสารเสพติด
CREATE TABLE f_health_behav_drug (
    f_health_behav_drug_id character varying(255) NOT NULL,
    behav_drug_name character varying(255),
    CONSTRAINT f_health_behav_drug_pkey PRIMARY KEY (f_health_behav_drug_id)
);

INSERT INTO f_health_behav_drug VALUES ('1','ไม่เสพ');
INSERT INTO f_health_behav_drug VALUES ('2','เสพยาบ้า');
INSERT INTO f_health_behav_drug VALUES ('3','ดมสารละเหย');
INSERT INTO f_health_behav_drug VALUES ('4','ติดฝิ่น');
INSERT INTO f_health_behav_drug VALUES ('5','ติดเฮโรอีน');
INSERT INTO f_health_behav_drug VALUES ('6','เสพสารอื่นๆ');

--ตารางรายการภาวะความเครียด
CREATE TABLE f_health_behav_strain (
    f_health_behav_strain_id character varying(255) NOT NULL,
    behav_strain_name character varying(255),
    CONSTRAINT f_health_behav_strain_pkey PRIMARY KEY (f_health_behav_strain_id)
);

INSERT INTO f_health_behav_strain VALUES ('1','ไม่เครียด');
INSERT INTO f_health_behav_strain VALUES ('2','เครียด กำจัดเหมาะสม');
INSERT INTO f_health_behav_strain VALUES ('3','เครียด กำจัดไม่เหมาะสม');

--ตารางรายการภาวะทางอารมณ์
CREATE TABLE f_health_behav_mood (
    f_health_behav_mood_id character varying(255) NOT NULL,
    behav_mood_name character varying(255),
    CONSTRAINT f_health_behav_mood_pkey PRIMARY KEY (f_health_behav_mood_id)
);

INSERT INTO f_health_behav_mood VALUES('1','ปกติ');
INSERT INTO f_health_behav_mood VALUES('2','โกรธง่าย');
INSERT INTO f_health_behav_mood VALUES('3','มักอาละวาด/เอะอะโวยวาย');
INSERT INTO f_health_behav_mood VALUES('4','ซึมเศร้า');

--ตารางรายการพฤติกรรมทางเพศ
CREATE TABLE f_health_behav_sexbeh (
    f_health_behav_sexbeh_id character varying(255) NOT NULL,
    behav_sexbeh_name character varying(255),
    CONSTRAINT f_health_behav_sexbeh_pkey PRIMARY KEY (f_health_behav_sexbeh_id)
);

INSERT INTO f_health_behav_sexbeh VALUES ('1','"ไม่มีเพศสัมพันธ์');
INSERT INTO f_health_behav_sexbeh VALUES ('2','ไม่สำส่อนทางเพศ');
INSERT INTO f_health_behav_sexbeh VALUES ('3','มีคู่นอนมากกว่า 1 คน');
INSERT INTO f_health_behav_sexbeh VALUES ('4','เบี่ยงเบนทางเพศ');
INSERT INTO f_health_behav_sexbeh VALUES ('5','ตรวจ HIV ภายใน 1 ปีที่ผ่านมา');

-- ตารางรายการการป้องกันทางเพศ
CREATE TABLE f_health_behav_sexprotect (
    f_health_behav_sexprotect_id character varying(255) NOT NULL,
    behav_sexprotect_name character varying(255),
    CONSTRAINT f_health_behav_sexprotect_pkey PRIMARY KEY (f_health_behav_sexprotect_id)
);

INSERT INTO f_health_behav_sexprotect VALUES ('1','ป้องกัน');
INSERT INTO f_health_behav_sexprotect VALUES ('2','ไม่ป้องกัน');

--ตารางรายการพฤติกรรมการรับประทาน
CREATE TABLE f_health_behav_eat (
    f_health_behav_eat_id character varying(255) NOT NULL,
    behav_eat_name character varying(255),
    CONSTRAINT f_health_behav_eat_pkey PRIMARY KEY (f_health_behav_eat_id)
);

INSERT INTO f_health_behav_eat VALUES ('1','ชอบกิน');
INSERT INTO f_health_behav_eat VALUES ('2','นานๆ ครั้ง');
INSERT INTO f_health_behav_eat VALUES ('3','ไม่ชอบกิน');

--ตารางรายการพฤติกรรมการสูบบุหรี่
CREATE TABLE f_health_behav_ciga (
    f_health_behav_ciga_id character varying(255) NOT NULL,
    behav_ciga_name character varying(255),
    CONSTRAINT f_health_behav_ciga_pkey PRIMARY KEY (f_health_behav_ciga_id)
);

INSERT INTO f_health_behav_ciga VALUES ('1','ไม่สูบ');
INSERT INTO f_health_behav_ciga VALUES ('2','สูบบางครั้ง');
INSERT INTO f_health_behav_ciga VALUES ('3','สูบวันละ 1-5 มวน');
INSERT INTO f_health_behav_ciga VALUES ('4','สูบวันละ 6-10 มวน');
INSERT INTO f_health_behav_ciga VALUES ('5','สูบวันละ 11 มวนขึ้นไป');
INSERT INTO f_health_behav_ciga VALUES ('6','เคยสูบและเลิกแล้ว');

--ตารางรายการพฤติกรรมการดื่มเครื่องดื่มแอลกอฮอร์
CREATE TABLE f_health_behav_alcohol (
    f_health_behav_alcohol_id character varying(255) NOT NULL,
    behav_alcohol_name character varying(255),
    CONSTRAINT f_health_behav_alcohol_pkey PRIMARY KEY (f_health_behav_alcohol_id)
);

INSERT INTO f_health_behav_alcohol VALUES ('1','ไม่ดื่ม');
INSERT INTO f_health_behav_alcohol VALUES ('2','ดื่ม 1-2 ครั้ง/สัปดาห์');
INSERT INTO f_health_behav_alcohol VALUES ('3','ดื่ม 3-7 ครั้ง/สัปดาห์');
INSERT INTO f_health_behav_alcohol VALUES ('4','ดื่มบางครั้งไม่ทุกสัปดาห์');
INSERT INTO f_health_behav_alcohol VALUES ('5','เคยดื่มและเลิกแล้ว');

--ตารางรายการประวัติครอบครัวเกี่ยวกับโรค
CREATE TABLE f_health_behav_hx (
    f_health_behav_hx_id character varying(255) NOT NULL,
    behav_hx_name character varying(255),
    CONSTRAINT f_health_behav_hx_pkey PRIMARY KEY (f_health_behav_hx_id)
);

INSERT INTO f_health_behav_hx VALUES ('1','ไม่มี(บุคคลสุขภาพดี)');
INSERT INTO f_health_behav_hx VALUES ('2','มี ญาติสายตรง');
INSERT INTO f_health_behav_hx VALUES ('3','มี ไม่ใช่ญาติสายตรง');
INSERT INTO f_health_behav_hx VALUES ('4','มีทั้งญาติสายตรง และไม่ใช่สายตรง');

--ตารางรายการพฤติกรรมการป้องกันอุบัติเหตุ
CREATE TABLE f_health_behav_accident (
    f_health_behav_accident_id character varying(255) NOT NULL,
    behav_accident_name character varying(255),
    CONSTRAINT f_health_behav_accident_pkey PRIMARY KEY (f_health_behav_accident_id)
);

INSERT INTO f_health_behav_accident VALUES ('2','ปฏิบัติตามกฏจราจรบางครั้ง');
INSERT INTO f_health_behav_accident VALUES ('3','ปฏิบัติตามกฏจราจรเกือบทุกครั้ง');
INSERT INTO f_health_behav_accident VALUES ('4','ปฏิบัติตามกฏจราจรทุกครั้ง');
INSERT INTO f_health_behav_accident VALUES ('5','ขับรถโดยประมาท');

--ตารางรายการสรุปผลการตรวจโรค
CREATE TABLE f_health_result (
    f_health_result_id character varying(255) NOT NULL,
    result_name character varying(255),
    CONSTRAINT f_health_result_pkey PRIMARY KEY (f_health_result_id)
);

INSERT INTO f_health_result VALUES ('1','ปกติ');
INSERT INTO f_health_result VALUES ('2','เสี่ยง');
INSERT INTO f_health_result VALUES ('3','ป่วย/ผิดปกติ');
INSERT INTO f_health_result VALUES ('4','พิการ/ทุพพลภาพ');

--ตารางรายการประเภทบุคคล
CREATE TABLE f_health_behav_p_type (
    f_health_behav_p_type_id character varying(255) NOT NULL,
    behav_p_type_name character varying(255),
    CONSTRAINT f_health_behav_p_type_pkey PRIMARY KEY (f_health_behav_p_type_id)
);

INSERT INTO f_health_behav_p_type VALUES ('1','บุคคลสุขภาพดี');
INSERT INTO f_health_behav_p_type VALUES ('2','บุคคลกลุ่มเสี่ยง');
INSERT INTO f_health_behav_p_type VALUES ('3','บุคคลกลุ่มป่วย/โรคไม่ติดต่อเรื้อรัง');
INSERT INTO f_health_behav_p_type VALUES ('4','พิการและทุพพลภาพ');

--ตารางข้อมูลคัดกรองพฤติกรรม
CREATE TABLE t_health_behavior (
    t_health_behavior_id character varying(255) NOT NULL,
    behavior_idy character varying(255),
    behavior_date_serv character varying(255),
    behavior_weight character varying(255),
    behavior_height character varying(255),
    behavior_bmi character varying(255),
    behavior_waist character varying(255),
    behavior_hx_ht character varying(255),
    behavior_hx_dm character varying(255),
    behavior_hx_hdd character varying(255),
    t_health_family_id character varying(255),
    behavior_strain character varying(255),
    behavior_mood character varying(255),
    behavior_sex_behavior character varying(255),
    behavior_sex_protect character varying(255),
    behavior_fat character varying(255),
    behavior_salt character varying(255),
    behavior_sweet character varying(255),
    behavior_egg character varying(255),
    behavior_rare character varying(255),
    behavior_ciga character varying(255),
    behavior_alcohol character varying(255),
    behavior_drug character varying(255),
    behavior_accident character varying(255),
    behavior_ht_result character varying(255),
    behavior_dm_result character varying(255),
    behavior_cancerv_result character varying(255),
    behavior_cabrea_result character varying(255),
    behavior_chronic character varying(255),
    behavior_p_type character varying(255),
    behavior_drug_other character varying(255),
    behavior_record_time character varying(255),
    behavior_modify_time character varying(255),
    behavior_cancel_time character varying(255),
    behavior_staff_record character varying(255),
    behavior_staff_modify character varying(255),
    behavior_staff_cancel character varying(255),
    behavior_active character varying(255),
    behavior_is_chronic character varying(255),
    CONSTRAINT t_health_behavior_pkey PRIMARY KEY (t_health_behavior_id)
);

--ออกกำลังกาย
--ตารางรายการประเภทการออกกำลังกาย
CREATE TABLE f_health_exercise (
    f_health_exercise_id character varying(255) NOT NULL,
    exercise_name character varying(255),
    CONSTRAINT f_health_exercise_pkey PRIMARY KEY (f_health_exercise_id)
);

INSERT INTO f_health_exercise VALUES ('1','ไม่ออกกำลังกาย');
INSERT INTO f_health_exercise VALUES ('2','ออกกำลังกายมีแบบแผน');
INSERT INTO f_health_exercise VALUES ('3','ออกกำลังกายไม่มีแบบแผน');

--ตารางข้อมูลการออกกำลังกาย
CREATE TABLE t_health_exercise (
    t_health_exercise_id character varying(255) NOT NULL,
    exercise_idy character varying(255),
    exercise_date_serv character varying(255),
    f_health_exercise_id character varying(255),
    t_health_family_id character varying(255),
    exercise_record_time character varying(255),
    exercise_modify_time character varying(255),
    exercise_cancel_time character varying(255),
    exercise_staff_record character varying(255),
    exercise_staff_modify character varying(255),
    exercise_staff_cancel character varying(255),
    exercise_active character varying(255),
    CONSTRAINT t_health_exercise_pkey PRIMARY KEY (t_health_exercise_id)
);

--คัดกรองความดัน/เบาหวาน
--ตารางข้อมูลคัดกรองความดัน
CREATE TABLE t_health_pressure (
    t_health_pressure_id character varying(255) NOT NULL,
    pressure_idy character varying(255),
    pressure_date_serv character varying(255),
    pressure_systolic character varying(255),
    pressure_diastolic character varying(255),
    t_health_family_id character varying(255),
    pressure_result character varying(255),
    pressure_record_time character varying(255),
    pressure_modify_time character varying(255),
    pressure_cancel_time character varying(255),
    pressure_staff_record character varying(255),
    pressure_staff_modify character varying(255),
    pressure_staff_cancel character varying(255),
    pressure_active character varying(255),
    CONSTRAINT t_health_pressure_pkey PRIMARY KEY (t_health_pressure_id)
);

--ตารางข้อมูลคัดกรองเบาหวาน
CREATE TABLE t_health_diabetes (
    t_health_diabetes_id character varying(255) NOT NULL,
    diabetes_idy character varying(255),
    diabetes_date_serv character varying(255),
    diabetes_verbal_scan_result character varying(255),
    diabetes_bisugar character varying(255),
    t_health_family_id character varying(255),
    diabetes_result_dm character varying(255),
    diabetes_record_time character varying(255),
    diabetes_modify_time character varying(255),
    diabetes_cancel_time character varying(255),
    diabetes_staff_record character varying(255),
    diabetes_staff_modify character varying(255),
    diabetes_staff_cancel character varying(255),
    diabetes_active character varying(255),
    CONSTRAINT t_health_diabetes_pkey PRIMARY KEY (t_health_diabetes_id)
);

--ผู้สูงอายุร่วมกิจกรรม
--ตารางรายการการเข้าร่วมชมรมของผู้สูงอายุ
CREATE TABLE f_health_is_join_agr (
    f_health_is_join_agr_id character varying(255) NOT NULL,
    is_join_agr_name character varying(255),
    CONSTRAINT f_health_is_join_agr_pkey PRIMARY KEY (f_health_is_join_agr_id)
);

INSERT INTO f_health_is_join_agr VALUES ('1','เข้าร่วมกิจกรรม');
INSERT INTO f_health_is_join_agr VALUES ('2','ไม่เข้าร่วมกิจกรรม');

--ตารางข้อมูลผู้สูงอายุที่ร่วมกิจกรรมชมรม
CREATE TABLE t_health_elder_agr (
    t_health_elder_agr_id character varying(255) NOT NULL,
    elder_agr_idy character varying(255),
    elder_agr_date_serv character varying(255),
    elder_agr_is_join character varying(255),
    t_health_agr_id character varying(255),
    t_health_family_id character varying(255),
    elder_agr_record_time character varying(255),
    elder_agr_modify_time character varying(255),
    elder_agr_cancel_time character varying(255),
    elder_agr_staff_record character varying(255),
    elder_agr_staff_modify character varying(255),
    elder_agr_staff_cancel character varying(255),
    elder_agr_active character varying(255),
    CONSTRAINT t_health_elder_agr_pkey PRIMARY KEY (t_health_elder_agr_id)
);

