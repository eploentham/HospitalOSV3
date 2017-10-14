--แก้ปัญหาการเลือกผู้ป่วยจากหน้าจอแลบและ xray ที่ช้ามาก
--henbe 20/12/2549
DROP INDEX f_order_status_id;

--pu 20/12/2549 
-- ปรับปรุงข้อมูล healthy thailand
UPDATE f_health_behav_sexbeh SET behav_sexbeh_name = 'ไม่มีเพศสัมพันธ์' WHERE f_health_behav_sexbeh_id = '1';

--henbe 21/12/2549 
--ป้องกันการซ้ำของเลขเหล่านี้ AN HN VN
create sequence t_visit_vn;
create sequence t_patient_hn;
create sequence t_visit_an;

--henbe 21/12/2549 
--แก้ไข default ข้อมูลเดิมของปุ่มวิเศษ
delete from b_option_detail where  b_option_detail_id   like 'b1_command';
insert into b_option_detail values('b1_command','"c:\\Program Files\\Internet Explorer\\IEXPLORE.EXE" "http://ucsearch.nhso.go.th/index.html"','');

--pu 08/01/2550
ALTER TABLE r_eye_disease_code RENAME eye_disease_code_start_code TO eye_disease_code_begin;
ALTER TABLE r_eye_disease_code RENAME eye_disease_code_end_code TO eye_disease_code_end;

UPDATE r_eye_disease_code SET r_eye_group_id = '8130000000001' WHERE r_eye_group_id = '6000000000001';
UPDATE r_eye_disease_code SET r_eye_group_id = '8130000000002' WHERE r_eye_group_id = '6000000000002';
UPDATE r_eye_disease_code SET r_eye_group_id = '8130000000003' WHERE r_eye_group_id = '6000000000003';
UPDATE r_eye_disease_code SET r_eye_group_id = '8130000000004' WHERE r_eye_group_id = '6000000000004';
UPDATE r_eye_disease_code SET r_eye_group_id = '8130000000005' WHERE r_eye_group_id = '6000000000005';
UPDATE r_eye_disease_code SET r_eye_group_id = '8130000000006' WHERE r_eye_group_id = '6000000000006';
UPDATE r_eye_disease_code SET r_eye_group_id = '8130000000007' WHERE r_eye_group_id = '6000000000007';
UPDATE r_eye_disease_code SET r_eye_group_id = '8130000000008' WHERE r_eye_group_id = '6000000000008';
UPDATE r_eye_disease_code SET r_eye_group_id = '8130000000009' WHERE r_eye_group_id = '6000000000009';
UPDATE r_eye_disease_code SET r_eye_group_id = '8130000000010' WHERE r_eye_group_id = '6000000000010';
UPDATE r_eye_disease_code SET r_eye_group_id = '8130000000011' WHERE r_eye_group_id = '6000000000011';

UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000001' WHERE r_eye_disease_code_id = '6010000000001';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000002' WHERE r_eye_disease_code_id = '6010000000002';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000003' WHERE r_eye_disease_code_id = '6010000000003';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000004' WHERE r_eye_disease_code_id = '6010000000004';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000005' WHERE r_eye_disease_code_id = '6010000000005';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000006' WHERE r_eye_disease_code_id = '6010000000006';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000007' WHERE r_eye_disease_code_id = '6010000000007';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000008' WHERE r_eye_disease_code_id = '6010000000008';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000009' WHERE r_eye_disease_code_id = '6010000000009';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000010' WHERE r_eye_disease_code_id = '6010000000010';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000011' WHERE r_eye_disease_code_id = '6010000000011';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000012' WHERE r_eye_disease_code_id = '6010000000012';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000013' WHERE r_eye_disease_code_id = '6010000000013';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000014' WHERE r_eye_disease_code_id = '6010000000014';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000015' WHERE r_eye_disease_code_id = '6010000000015';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000016' WHERE r_eye_disease_code_id = '6010000000016';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000017' WHERE r_eye_disease_code_id = '6010000000017';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000018' WHERE r_eye_disease_code_id = '6010000000018';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000019' WHERE r_eye_disease_code_id = '6010000000019';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000020' WHERE r_eye_disease_code_id = '6010000000020';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000021' WHERE r_eye_disease_code_id = '6010000000021';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000022' WHERE r_eye_disease_code_id = '6010000000022';
UPDATE r_eye_disease_code SET r_eye_disease_code_id = '8140000000023' WHERE r_eye_disease_code_id = '6010000000023';

DELETE FROM r_eye_group WHERE r_eye_group_id  like '600%';

--henbe 080107 แก้ให้การค้นหน่วยยาไม่เกิด exception
update b_item_drug_frequency set item_drug_frequency_description = '' where item_drug_frequency_description is null;
update b_item_drug_instruction set item_drug_instruction_description = '' where item_drug_instruction_description is null;

insert into f_gui_action_authen values ('906060713','0607','เมนูจำหน่ายผู้ป่วยใน','13','งานผู้ป่วยใน','1','1','');
insert into f_gui_action_authen values ('906060710','0607','เมนูจำหน่ายผู้ป่วยใน','10','ONE STOP SERVICE','1','1','');

--ปิดแล้วห้ามแก้ไขเป็นอันขาด
insert into s_script_update_log values ('hospitalOS','updateV3_74.sql',(select current_date) || ','|| (select current_time),'แก้ปัญหาการเลือกผู้ป่วยจากหน้าจอแลบและ xray ที่ช้ามาก');
------
INSERT INTO s_version VALUES ('9701000000030', '30', 'Hospital OS, Community Edition', '3.7.250706', '3.16.201206', '2549-12-20 12:25:00');
---

