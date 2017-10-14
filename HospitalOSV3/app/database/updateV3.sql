--ให้ update ฐานข้อมูลนี้จากเวอร์ชันข้างล่าง  from database version
--9800999090999','9','Hospital OS, Community Edition','2.08.0548','2.07.0548','2548-05-31 18:29:00


--------------------------------------------------------------------------------------------------------3.00
-- ของเก่ามีอยู่แล้ว
drop table f_visit_nutrition_level;
CREATE TABLE f_visit_nutrition_level
(
  f_visit_nutrition_level_id varchar(255) NOT NULL,
  visit_nutrition_level_description varchar(255),
  visit_nutrition_level_max varchar(255),
  visit_nutrition_level_min varchar(255),
  CONSTRAINT f_visit_nutrition_level_pkey PRIMARY KEY (f_visit_nutrition_level_id)
) ;

ALTER TABLE f_visit_nutrition_level OWNER TO postgres;

insert into f_visit_nutrition_level values('1','ผอมระดับ 1','19.9','18.5');
insert into f_visit_nutrition_level values('2','ผอมระดับ 2','18.4','17.0');
insert into f_visit_nutrition_level values('3','ผอมระดับ 3','16.9','16.0');
insert into f_visit_nutrition_level values('4','ผอมระดับ 4','15.9','0');
insert into f_visit_nutrition_level values('5','อ้วนระดับ1','29.9','25.0');
insert into f_visit_nutrition_level values('6','อ้วนระดับ2','39.9','30.0');
insert into f_visit_nutrition_level values('N','ปกติ','24.9','20.0');
insert into f_visit_nutrition_level values('7','อ้วนระดับ3','100','40');

-- เพื่อให้การแสดงผลที่คิวผู้ป่วยไม่ต้องทำการคิวรีหลายครั้ัง หลายขั้นตอน
alter table t_visit_queue_despense add  vn  varchar(255) default '';
alter table t_visit_queue_despense add  service_point_name  varchar(255) default '';
alter table t_visit_queue_despense add  hn  varchar(255) default '';
alter table t_visit_queue_despense add  prename  varchar(255) default '';
alter table t_visit_queue_despense add  firstname  varchar(255) default '';
alter table t_visit_queue_despense add  lastname  varchar(255) default '';

INSERT INTO s_version VALUES ('9700999999999', '9', 'Hospital OS, Community Edition', '3.00.0548', '3.00.0548', '2548-06-30 12:00:00');
--------------------------------------------------------------------------------------------------------3.01
-- tableid  "904"
CREATE TABLE f_tab
(
  f_tab_id varchar(255) NOT NULL,
  description varchar(255),
  CONSTRAINT f_tab_pkey PRIMARY KEY (f_tab_id)
) ;
insert into f_tab values('1','ข้อมูลผู้ป่วย');
insert into f_tab values('2','การรับบริการ');
insert into f_tab values('3','การตรวจร่างกาย');
insert into f_tab values('4','การตรวจ/รักษา');
insert into f_tab values('5','การวินิจฉัย');
insert into f_tab values('6','การเงิน');
insert into f_tab values('7','ข้อมูลแลป');
insert into f_tab values('8','ข้อมูลรังสี');

-- เพื่อให้ผู้ใช้เลือกได้ว่าหลังจากที่ตนเองเลือกผู้ป่วยแล้วโปรแกรมจะแสดงข้อมูลในแถบใดให้อัตโนมัติ
alter table b_employee add  default_tab  varchar(255) default '';

update b_employee set default_tab = '1' where f_employee_authentication_id = '1';
update b_employee set default_tab = '3' where f_employee_authentication_id = '2';
update b_employee set default_tab = '3' where f_employee_authentication_id = '3';
update b_employee set default_tab = '7' where f_employee_authentication_id = '4';
update b_employee set default_tab = '8' where f_employee_authentication_id = '5';
update b_employee set default_tab = '4' where f_employee_authentication_id = '6';
update b_employee set default_tab = '6' where f_employee_authentication_id = '7';
update b_employee set default_tab = '5' where f_employee_authentication_id = '8';
update b_employee set default_tab = '' where f_employee_authentication_id = '9';
update b_employee set default_tab = '' where f_employee_authentication_id = '10';
update b_employee set default_tab = '' where f_employee_authentication_id = '11';
update b_employee set default_tab = '' where f_employee_authentication_id = '12';

--1','เวชระเบียน
--2','พยาบาล
--3','แพทย์
--4','LAB
--5','X-RAY
--6','เภสัชกร
--7','แคชเชียร์
--8','เวชสถิติ
--9','ADMIN
--10','ONE STOP SERVICE
--11','งานประกัน
--12','งานส่งเสริม     
--13','งานผู้ป่วยใน     

update b_item_drug_frequency set item_drug_frequency_factor = '2' where item_drug_frequency_description like '%วันละ 2 %';
update b_item_drug_frequency set item_drug_frequency_factor = '3' where item_drug_frequency_description like '%วันละ 3 %';
update b_item_drug_frequency set item_drug_frequency_factor = '4' where item_drug_frequency_description like '%วันละ 4 %';
update b_item_drug_frequency set item_drug_frequency_factor = '5' where item_drug_frequency_description like '%วันละ 5 %';

INSERT INTO s_version VALUES ('9701000000000', '10', 'Hospital OS, Community Edition', '3.01.0748', '3.01.0748', '2548-07-20 12:00:00');
--------------------------------------------------------------------------------------------------------3.02
-- เพิ่มภาษาสำหรับการพิมพ์ กรณีผู้ป่วยต่างชาติอ่านภาษาไทยไม่ออก UC savePatient()
alter table t_patient add  language_for_print  varchar(255) default '';

-- เพิ่ม ว่า visit ครั้งนี้เป็นการมาครั้งแรกของผู้ป่วยคนนี้ UC visitPatient()
alter table t_visit add  first_visit  varchar(255) default '0';

-- จุดบริการที่ทำการสั่งรายการตรวจเหล่านี้ให้กับผู้ป่วย UC saveOrderItem()
-- มีแล้ว alter table t_order add  order_service_point  varchar(255);

-- รายการยาที่มีการแก้ไขวิธีใช้ต้องมีการบันทึกผู้แก้ทุกคนไว้เสมอ UC saveOrderItem()
alter table t_order_drug add  active  varchar(255) default '1';
alter table t_order_drug add  modifier  varchar(255) default '';
alter table t_order_drug add  modify_datetime  varchar(255) default '';

update b_item_drug set item_drug_caution = '' where item_drug_caution='null';
update b_item_drug set item_drug_description = '' where item_drug_description='null';
update b_contract_plans set contract_plans_description = 'ผู้มีสิทธิลดหย่อนตามระเบียบสาธารณสุข' where b_contract_plans_id = '2121340126613';
update b_item_price set item_price=20 where item_price = 0 ;

INSERT INTO s_version VALUES ('9701000000001', '11', 'Hospital OS, Community Edition', '3.02.0748', '3.02.0748', '2548-07-28 12:00:00');
--------------------------------------------------------------------------------------------------------3.03
-- เปลี่ยนแปลงหน่วยยาที่ยกเลิกแล้วให้มาใช้หน่วยยาที่ยังไม่ยกเลิกแทน
UPDATE b_item_drug SET item_drug_purch_uom = '2520000000030' WHERE item_drug_purch_uom = '2520000000012';
UPDATE b_item_drug SET item_drug_purch_uom = '2520000000002' WHERE item_drug_purch_uom = '2520000000015';
UPDATE b_item_drug SET item_drug_purch_uom = '2520000000007' WHERE item_drug_purch_uom = '2520000000019';
UPDATE b_item_drug SET item_drug_purch_uom = '2520000000009' WHERE item_drug_purch_uom = '2520000000029';
UPDATE b_item_drug SET item_drug_purch_uom = '2520000000003' WHERE item_drug_purch_uom = '2520000000033';
UPDATE b_item_drug SET item_drug_purch_uom = '2520000000026' WHERE item_drug_purch_uom = '2520000000038';

--drop table b_health_epi_group;
--drop table b_health_epi_item;
--drop table b_health_epi_item_drug_setup;
--drop table f_health_anc_section;
--drop table f_health_family_planing;
----drop table f_health_family_planing_method;
--drop table f_health_home_charactor;
--drop table f_health_home_community_charac;
--drop table f_health_home_garbage_method;
--drop table f_health_home_water_type;
--drop table f_health_postpartum_birth_method;
--drop table f_health_postpartum_birth_place;
--drop table f_health_postpartum_episiotomy_type;
--drop table f_health_postpartum_givebirth_result;
--drop table f_health_postpartum_status_result;
--drop table f_health_pregnancy_birth_doctor_type;
--drop table f_health_pregnancy_birth_high_risk;
--drop table f_health_pregnancy_conduct;
--drop table f_health_pregnancy_posture_baby_status;
--drop table f_health_pregnancy_pregnant_level;
--drop table f_health_pregnancy_uterus_level;
--drop table t_health_anc;
--drop table t_health_anc_detail;
--drop table t_health_delivery;
--drop table t_health_epi;
--drop table t_health_epi_detail;
--drop table t_health_family_planing;
--drop table t_health_home;
--drop table t_health_postpartum;
--drop table t_health_pp;
--drop table t_health_pregnancy;
INSERT INTO s_version VALUES ('9701000000002', '12', 'Hospital OS, Community Edition', '3.03.0748', '3.03.0748', '2548-08-11 12:00:00');
--------------------------------------------------------------------------------------------------------3.04

insert  into  f_employee_authentication (f_employee_authentication_id,employee_authentication_description) values ('13','งานผู้ป่วยใน');

update b_item_drug set item_drug_qty = '20' where item_drug_purch_uom = '2520000000009' and item_drug_qty = '0';
update b_item_drug set item_drug_dose = '1' where item_drug_purch_uom = '2520000000009' and item_drug_dose = '0';
update b_item_drug set item_drug_qty = '1' where item_drug_qty = '0';


delete from b_item_auto where item_auto_number = '04';
delete from b_item_auto where item_auto_number = '06';
delete from b_item_auto where item_auto_number = '07';
delete from b_item_auto where item_auto_number = '08';
delete from b_item_auto where item_auto_number = '09';

-- insert into  b_item_auto ('b_item_auto_id, item_auto_number, b_item_id, b_contract_plans_id, item_auto_mon_fri_show, item_auto_saturday_show, item_auto_sunday_show, mon_time_mon_fri_start, mon_time_mon_fri_stop, item_auto_saturday_show_start_time, item_auto_saturday_show_stop_time, item_auto_sunday_show_start_time, item_auto_sunday_show_stop_time, item_auto_mon_fri_show_all_time, item_auto_saturday_show_all_time, item_auto_sunday_show_all_time) FROM stdin;
insert into  b_item_auto values ('1130514280500','06','1740000001332','','1','0','0','16:30','23:59','00:00','00:00','00:00','00:00','0','0','0');
insert into  b_item_auto values ('1130514280501','07','1740000001332','','1','1','1','00:01','08:30','00:00','00:00','00:00','00:00','0','1','1');
insert into  b_item_auto values ('1130514280502','08','1740000001268','','1','0','0','16:30','23:59','00:00','00:00','00:00','00:00','0','0','0');
insert into  b_item_auto values ('1130514280503','09','1740000001268','','1','1','1','00:01','08:30','00:00','00:00','00:00','00:00','0','1','1');


alter table t_patient add  patient_mobile_phone  varchar(255) default '';
alter table t_patient add  contact_mobile_phone  varchar(255) default '';

update b_employee set f_employee_authentication_id = '13' where employee_login = 'ipd';

INSERT INTO s_version VALUES ('9701000000003', '13', 'Hospital OS, Community Edition', '3.04.0848', '3.04.0848', '2548-08-23 12:00:00');
--------------------------------------------------------------------------------------------------------3.04

--รายการเหล่านี้ เป็นการ update ไปเป็น 8.2 จาก 8.1 หากพบ error ไม่ต้องตกใจ--------------------

--เพิ่ม record ในตาราง f_employee_authentication เพิ่มผู้ป่วยในเข้าไปในระบบ เพิ่มแล้วนะใน update ก่อนหน้านี้
--insert into f_employee_authentication (f_employee_authentication_id,employee_authentication_description) values ('13','พยาบาลผู้ป่วยใน');

--เพิ่ม Column visit_patient_self_doctor ในตาราง t_visit  
ALTER TABLE t_visit ADD COLUMN visit_patient_self_doctor varchar (255) Default '';

--เพิ่ม Column setup_authorization_autounlock ในตาราง b_setup_authorization
ALTER TABLE b_setup_authorization  ADD COLUMN setup_authorization_autounlock varchar (255) Default '5';

--เปลี่ยนขนาดการเก็บข้อมูลของฟิลด์ template_sign_symptom_description เป็น 3000
ALTER TABLE b_template_sign_symptom RENAME          template_sign_symptom_description TO template_sign_symptom_description1;
ALTER TABLE b_template_sign_symptom  ADD COLUMN  template_sign_symptom_description varchar (3000);
UPDATE b_template_sign_symptom SET template_sign_symptom_description = template_sign_symptom_description1;

Alter table b_template_sign_symptom drop column  template_sign_symptom_description1;

-------หากพบ error ไม่ต้องตกใจ เพราะฐานข้อมูลของคุณอาจเป็นเวอร์ชันนี้ก็ได้-----------------------------

INSERT INTO s_version VALUES ('9701000000004', '14', 'Hospital OS, Community Edition', '3.05.0848', '3.05.0948', '2548-09-07 12:00:00');
--------------------------------------------------------------------------------------------------------3.05
-- tableid  "905"
CREATE TABLE f_gui_action
(
  f_gui_action_id varchar(255) NOT NULL,
  gui_name varchar(255),
  note varchar(255),
  CONSTRAINT f_guit_action_pkey PRIMARY KEY (f_gui_action_id)
)  ;

insert into  f_gui_action values ('0100','เมนูระบบ',' ');
insert into  f_gui_action values ('0101','ออกจากระบบ',' ');
insert into  f_gui_action values ('0200','เมนูมุมมอง',' ');
insert into  f_gui_action values ('0201','รีเฟรช',' ');
insert into  f_gui_action values ('0202','ปลดล็อก',' ');
insert into  f_gui_action values ('0203','ค้นหาผู้ป่วย',' ');
insert into  f_gui_action values ('0300','เมนูแถบ',' ');
insert into  f_gui_action values ('0400','เมนูเครื่องมือ',' ');
insert into  f_gui_action values ('0401','การนัดหมาย',' ');
insert into  f_gui_action values ('0402','รายการนัดทั้งหมด',' ');
insert into  f_gui_action values ('0403','ประวัติการรับบริการ',' ');
insert into  f_gui_action values ('0404','ประวัติรายการสั่งตรวจ',' ');
insert into  f_gui_action values ('0405','ประวัติการคิดเงิน',' ');
insert into  f_gui_action values ('0406','จับคู่คำนำหน้ากับเพศ',' ');
insert into  f_gui_action values ('0407','ลำดับเลข Sequence ล่าสุด',' ');
insert into  f_gui_action values ('0408','การ refer ผู้ป่วย',' ');
insert into  f_gui_action values ('0409','รายงานต่าง ๆ ',' ');
insert into  f_gui_action values ('0410','Lab Refer Out',' ');
insert into  f_gui_action values ('0411','Lab Refer In',' ');
insert into  f_gui_action values ('0500','เมนูผู้ป่วยทั่วไป',' ');
insert into  f_gui_action values ('0501','กองทุนยา/การจำหน่ายยา',' ');
insert into  f_gui_action values ('0502','ค้างบันทึก',' ');
insert into  f_gui_action values ('0503','ยกเลิกการเข้ารับบริการ',' ');
insert into  f_gui_action values ('0504','ข้อมูลการเกิดอุบัติเหตุ',' ');
insert into  f_gui_action values ('0505','ข้อมูลการตาย',' ');
insert into  f_gui_action values ('0506','ข้อมูลโรคเรื้อรัง',' ');
insert into  f_gui_action values ('0507','ข้อมูลโรคเฝ้าระวัง',' ');
insert into  f_gui_action values ('0600','เมนูผู้ป่วยใน',' ');
insert into  f_gui_action values ('0601','Admit',' ');
insert into  f_gui_action values ('0602','ย้อนกลับการ Admit',' ');
insert into  f_gui_action values ('0603','ฝากนอน',' ');
insert into  f_gui_action values ('0604','ยกเลิกการฝากนอน',' ');
insert into  f_gui_action values ('0605','ส่งผู้ป่วยกลับวอร์ด',' ');
insert into  f_gui_action values ('0606','ประวัติการสั่งยาต่อเนื่อง',' ');
insert into  f_gui_action values ('0700','เมนูการสั่งตรวจ',' ');
insert into  f_gui_action values ('0701','การคืนยา',' ');
insert into  f_gui_action values ('0702','สั่งชุดรายการตรวจรักษา',' ');
insert into  f_gui_action values ('0703','สั่งรายการเหมือนครั้งที่แล้ว',' ');
insert into  f_gui_action values ('0704','สั่งรายการเหมือนวันที่แล้ว',' ');
insert into  f_gui_action values ('0705','สั่งยาต่อเนื่อง',' ');
insert into  f_gui_action values ('0800','เมนูรายการพิมพ์',' ');
insert into  f_gui_action values ('0801','ใบรายการตรวจรักษา',' ');
insert into  f_gui_action values ('0802','ใบ Summary',' ');
insert into  f_gui_action values ('0803','ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ',' ');
insert into  f_gui_action values ('0804','ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ',' ');
insert into  f_gui_action values ('0805','ใบสรุปค่าใช้จ่ายตามรายการ',' ');
insert into  f_gui_action values ('0806','รายการยาที่เลือก',' ');
insert into  f_gui_action values ('0807','ใบ OPD Card',' ');
insert into  f_gui_action values ('0808','พิมพ์สติ๊กเกอร์ยา',' ');
insert into  f_gui_action values ('0809','ใบ Index',' ');
insert into  f_gui_action values ('0810','พิมพ์ใบ Index Xray',' ');
insert into  f_gui_action values ('0811','พิมพ์ผลแลบ',' ');
insert into  f_gui_action values ('0900','เมนูการย้อนกลับ',' ');
insert into  f_gui_action values ('0901','ย้อนกลับทางการเงิน',' ');
insert into  f_gui_action values ('0902','ย้อนกลับทางการแพทย์',' ');
insert into  f_gui_action values ('1000','เมนูตัวช่วย',' ');
insert into  f_gui_action values ('1001','การเชื่อมต่อฐานข้อมูล',' ');
insert into  f_gui_action values ('1002','ข้อมูลโปรแกรม',' ');
insert into  f_gui_action values ('1003','กำ้หนด path รูปแบบอักษร',' ');
insert into  f_gui_action values ('1004','กำ้หนด path งานพิมพ์',' ');
insert into  f_gui_action values ('2100','แถบรายชื่อ',' ');
insert into  f_gui_action values ('2200','แถบรายชื่อตามจุดบริการ',' ');
insert into  f_gui_action values ('2300','แถบรายชื่อในวอร์ด',' ');
insert into  f_gui_action values ('2400','แถบข้อมูลผู้ป่วย',' ');
insert into  f_gui_action values ('2500','แถบการรับบริการ',' ');
insert into  f_gui_action values ('2600','แถบบันทึกอาการเจ็บป่วย',' ');
insert into  f_gui_action values ('2700','แถบรายการตรวจรักษา',' ');
insert into  f_gui_action values ('2800','แถบการวินิจฉัย',' ');
insert into  f_gui_action values ('2900','แถบการเงิน',' ');
insert into  f_gui_action values ('3000','แถบแลบ',' ');
insert into  f_gui_action values ('3100','แถบรังสี',' ');
insert into  f_gui_action values ('5100','ผู้ดูแลระบบ',' ');
insert into  f_gui_action values ('5101','ตั้งค่าของระบบ',' ');
insert into  f_gui_action values ('5102','คนไข้ที่ถูกล็อก',' ');
insert into  f_gui_action values ('5103','สิทธิการใช้งาน',' ');
insert into  f_gui_action values ('5200','รายชื่อจุดบริการ',' ');
insert into  f_gui_action values ('5201','รายชื่อวอร์ด',' ');
insert into  f_gui_action values ('5202','รายชื่อจุดบริการ',' ');
insert into  f_gui_action values ('5203','รายชื่อคลินิก',' ');
insert into  f_gui_action values ('5204','สถานพยาบาล',' ');
insert into  f_gui_action values ('5205','กำหนดคิว',' ');
insert into  f_gui_action values ('5300','รายการกลุ่ม',' ');
insert into  f_gui_action values ('5301','รายการ Oder',' ');
insert into  f_gui_action values ('5302','รายการใบเสร็จ',' ');
insert into  f_gui_action values ('5400','รายการตรวจรักษา',' ');
insert into  f_gui_action values ('5401','รายการตรวจรักษา',' ');
insert into  f_gui_action values ('5402','รายการอัตโนมัติ',' ');
insert into  f_gui_action values ('5403','อาการที่พบบ่อย',' ');
insert into  f_gui_action values ('5404','รายการยาชุด',' ');
insert into  f_gui_action values ('5405','Dx ที่พบบ่อย',' ');
insert into  f_gui_action values ('5406','ด้านการฉาย X-ray',' ');
insert into  f_gui_action values ('5407','ท่าการฉาย X-ray',' ');
insert into  f_gui_action values ('5408','ฟิล์มสำหรับ X-ray',' ');
insert into  f_gui_action values ('5500','รายละเอียดยา',' ');
insert into  f_gui_action values ('5501','ช่วงเวลาที่ใช้ยา',' ');
insert into  f_gui_action values ('5502','วิธีการใช้ยา',' ');
insert into  f_gui_action values ('5503','หน่วยยา',' ');
insert into  f_gui_action values ('5600','รายการ ICD',' ');
insert into  f_gui_action values ('5601','รายการ ICD-10',' ');
insert into  f_gui_action values ('5602','รายการ ICD-9',' ');
insert into  f_gui_action values ('5603','รายการโรคเรื้อรัง',' ');
insert into  f_gui_action values ('5604','รายการกลุ่ม ICD-10',' ');
insert into  f_gui_action values ('5700','การชำระเงิน',' ');
insert into  f_gui_action values ('5701','ผู้ชำระเงิน',' ');
insert into  f_gui_action values ('5702','รายการส่วนลด',' ');
insert into  f_gui_action values ('5703','สิทธิการรักษา',' ');
insert into  f_gui_action values ('5800','รายการอื่นๆ',' ');
insert into  f_gui_action values ('5801','ผู้ใช้งาน',' ');
insert into  f_gui_action values ('5802','แสดงเลข Sequence',' ');
insert into  f_gui_action values ('5803','สถานพยาบาลที่ติดตั้ง',' ');
insert into  f_gui_action values ('5900','รายงาน',' ');
insert into  f_gui_action values ('5901','รายงาน SQL',' ');
insert into  f_gui_action values ('5902','รายการคำสั่ง SQL',' ');
insert into  f_gui_action values ('5903','รายงาน SQL ตามวันที่',' ');
insert into  f_gui_action values ('5904','รายงานคำสั่ง SQL ตามวันที่',' ');

-- tableid  "906"
CREATE TABLE f_gui_action_authen
(
  f_gui_action_authen_id varchar(255) NOT NULL,
  f_gui_action_id varchar(255),
  gui_name varchar(255),
  f_authentication_id varchar(255),
  authentication_name varchar(255),
  is_read varchar(255),
  is_write varchar(255),
  note varchar(255),
  CONSTRAINT f_guit_action_authen_pkey PRIMARY KEY (f_gui_action_authen_id)
)  ;

insert into  f_gui_action_authen values ('1','0100','เมนูระบบ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('2','0101','ออกจากระบบ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('3','0200','เมนูมุมมอง','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('4','0201','รีเฟรช','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('5','0202','ปลดล็อก','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('6','0203','ค้นหาผู้ป่วย','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('7','0300','เมนูแถบ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('8','0400','เมนูเครื่องมือ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('9','0401','การนัดหมาย','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('10','0402','รายการนัดทั้งหมด','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('11','0403','ประวัติการรับบริการ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('12','0404','ประวัติรายการสั่งตรวจ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('13','0405','ประวัติการคิดเงิน','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('14','0406','จับคู่คำนำหน้ากับเพศ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('15','0407','ลำดับเลข Sequence ล่าสุด','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('16','0408','การ refer ผู้ป่วย','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('17','0409','รายงานต่าง ๆ ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('18','0500','เมนูผู้ป่วยทั่วไป','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('19','0501','กองทุนยา/การจำหน่ายยา','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('20','0502','ค้างบันทึก','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('21','0503','ยกเลิกการเข้ารับบริการ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('22','0504','ข้อมูลการเกิดอุบัติเหตุ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('23','0505','ข้อมูลการตาย','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('24','0506','ข้อมูลโรคเรื้อรัง','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('25','0507','ข้อมูลโรคเฝ้าระวัง','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('26','0600','เมนูผู้ป่วยใน','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('27','0601','Admit','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('28','0602','ย้อนกลับการ Admit','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('29','0603','ฝากนอน','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('30','0604','ยกเลิกการฝากนอน','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('31','0605','ส่งผู้ป่วยกลับวอร์ด','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('32','0606','ประวัติการสั่งยาต่อเนื่อง','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('33','0700','เมนูการสั่งตรวจ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('34','0701','การคืนยา','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('35','0702','สั่งชุดรายการตรวจรักษา','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('36','0703','สั่งรายการเหมือนครั้งที่แล้ว','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('37','0704','สั่งรายการเหมือนวันที่แล้ว','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('38','0705','สั่งยาต่อเนื่อง','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('39','0800','เมนูรายการพิมพ์','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('40','0801','ใบรายการตรวจรักษา','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('41','0802','ใบ Summary','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('42','0803','ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('43','0804','ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('44','0805','ใบสรุปค่าใช้จ่ายตามรายการ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('45','0806','รายการยาที่เลือก','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('46','0807','ใบ OPD Card','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('47','0808','พิมพ์สติ๊กเกอร์ยา','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('48','0809','ใบ Index','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('49','0900','เมนูการย้อนกลับ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('50','0901','ย้อนกลับทางการเงิน','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('51','0902','ย้อนกลับทางการแพทย์','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('52','1000','เมนูตัวช่วย','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('53','1001','การเชื่อมต่อฐานข้อมูล','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('54','1002','ข้อมูลโปรแกรม','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('55','1003','กำ้หนด path รูปแบบอักษร','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('56','1004','กำ้หนด path งานพิมพ์','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('57','2100','แถบรายชื่อ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('58','2200','แถบรายชื่อตามจุดบริการ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('59','2300','แถบรายชื่อในวอร์ด','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('60','2400','แถบข้อมูลผู้ป่วย','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('61','2500','แถบการรับบริการ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('62','2600','แถบบันทึกอาการเจ็บป่วย','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('63','2700','แถบรายการตรวจรักษา','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('64','2800','แถบการวินิจฉัย','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('65','2900','แถบการเงิน','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('110','3000','แถบแลบ','10','ONE STOP SERVICE','0','1',' ');
insert into  f_gui_action_authen values ('111','3100','แถบรังสี','10','ONE STOP SERVICE','0','1',' ');
insert into  f_gui_action_authen values ('66','5100','ผู้ดูแลระบบ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('67','5101','ตั้งค่าของระบบ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('68','5102','คนไข้ที่ถูกล็อก','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('69','5103','สิทธิการใช้งาน','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('70','5200','รายชื่อจุดบริการ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('71','5201','รายชื่อวอร์ด','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('72','5202','รายชื่อจุดบริการ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('73','5203','รายชื่อคลินิก','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('74','5204','สถานพยาบาล','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('75','5205','กำหนดคิว','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('76','5300','รายการกลุ่ม','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('77','5301','รายการ Oder','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('78','5302','รายการใบเสร็จ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('79','5400','รายการตรวจรักษา','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('80','5401','รายการตรวจรักษา','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('81','5402','รายการอัตโนมัติ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('82','5403','อาการที่พบบ่อย','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('83','5404','รายการยาชุด','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('84','5405','Dx ที่พบบ่อย','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('85','5406','ด้านการฉาย X-ray','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('86','5407','ท่าการฉาย X-ray','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('87','5408','ฟิล์มสำหรับ X-ray','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('88','5500','รายละเอียดยา','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('89','5501','ช่วงเวลาที่ใช้ยา','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('90','5502','วิธีการใช้ยา','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('91','5503','หน่วยยา','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('92','5600','รายการ ICD','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('93','5601','รายการ ICD-10','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('94','5602','รายการ ICD-9','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('95','5603','รายการโรคเรื้อรัง','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('96','5604','รายการกลุ่ม ICD-10','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('97','5700','การชำระเงิน','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('98','5701','ผู้ชำระเงิน','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('99','5702','รายการส่วนลด','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('100','5703','สิทธิการรักษา','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('101','5800','รายการอื่นๆ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('102','5801','ผู้ใช้งาน','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('103','5802','แสดงเลข Sequence','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('104','5803','สถานพยาบาลที่ติดตั้ง','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('105','5900','รายงาน','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('106','5901','รายงาน SQL','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('107','5902','รายการคำสั่ง SQL','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('108','5903','รายงาน SQL ตามวันที่','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('109','5904','รายงานคำสั่ง SQL ตามวันที่','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('154','0410','Lab Refer Out','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('155','0411','Lab Refer In','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('112','0810','พิมพ์ใบ Index Xray','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('113','0811','พิมพ์ผลแลบ','10','ONE STOP SERVICE','1','1',' ');
insert into  f_gui_action_authen values ('114','0410','Lab Refer Out','1','เวชระเบียน','0','0',' ');
insert into  f_gui_action_authen values ('115','0411','Lab Refer In','1','เวชระเบียน','0','0',' ');
insert into  f_gui_action_authen values ('116','0810','พิมพ์ใบ Index Xray','1','เวชระเบียน','0','0',' ');
insert into  f_gui_action_authen values ('117','0811','พิมพ์ผลแลบ','1','เวชระเบียน','0','0',' ');
insert into  f_gui_action_authen values ('118','0410','Lab Refer Out','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('119','0411','Lab Refer In','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('120','0810','พิมพ์ใบ Index Xray','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('121','0811','พิมพ์ผลแลบ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('122','0410','Lab Refer Out','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('123','0411','Lab Refer In','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('124','0810','พิมพ์ใบ Index Xray','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('125','0811','พิมพ์ผลแลบ','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('126','0410','Lab Refer Out','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('127','0411','Lab Refer In','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('128','0810','พิมพ์ใบ Index Xray','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('129','0811','พิมพ์ผลแลบ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('130','0410','Lab Refer Out','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('131','0411','Lab Refer In','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('132','0810','พิมพ์ใบ Index Xray','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('133','0811','พิมพ์ผลแลบ','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('134','0410','Lab Refer Out','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('135','0411','Lab Refer In','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('136','0810','พิมพ์ใบ Index Xray','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('137','0811','พิมพ์ผลแลบ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('138','0410','Lab Refer Out','7','แคชเชียร์','0','0',' ');
insert into  f_gui_action_authen values ('139','0411','Lab Refer In','7','แคชเชียร์','0','0',' ');
insert into  f_gui_action_authen values ('140','0810','พิมพ์ใบ Index Xray','7','แคชเชียร์','0','0',' ');
insert into  f_gui_action_authen values ('141','0811','พิมพ์ผลแลบ','7','แคชเชียร์','0','0',' ');
insert into  f_gui_action_authen values ('142','0410','Lab Refer Out','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('143','0411','Lab Refer In','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('144','0810','พิมพ์ใบ Index Xray','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('145','0811','พิมพ์ผลแลบ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('146','0410','Lab Refer Out','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('147','0411','Lab Refer In','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('148','0810','พิมพ์ใบ Index Xray','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('149','0811','พิมพ์ผลแลบ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('150','0410','Lab Refer Out','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('151','0411','Lab Refer In','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('152','0810','พิมพ์ใบ Index Xray','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('153','0811','พิมพ์ผลแลบ','13','งานผู้ป่วยใน','1','1','  ');
insert into  f_gui_action_authen values ('000000006110867478','0100','เมนูระบบ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000004232726361','0101','ออกจากระบบ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000000215124340','0200','เมนูมุมมอง','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000006192078927','0201','รีเฟรช','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000007422437734','0202','ปลดล็อก','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000003074575091','0203','ค้นหาผู้ป่วย','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000000653654177','0300','เมนูแถบ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000005382676551','0400','เมนูเครื่องมือ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000007964104825','0401','การนัดหมาย','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000000568902725','0402','รายการนัดทั้งหมด','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000006289157294','0403','ประวัติการรับบริการ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000003271589882','0404','ประวัติรายการสั่งตรวจ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000005425622772','0405','ประวัติการคิดเงิน','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000002540489800','0406','จับคู่คำนำหน้ากับเพศ','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000005872198674','0407','ลำดับเลข Sequence ล่าสุด','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000006834297322','0408','การ refer ผู้ป่วย','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000009803111491','0409','รายงานต่าง ๆ ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000007428808956','0500','เมนูผู้ป่วยทั่วไป','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000002302854151','0501','กองทุนยา/การจำหน่ายยา','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000001083183081','0502','ค้างบันทึก','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000006384303966','0503','ยกเลิกการเข้ารับบริการ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000003851246032','0504','ข้อมูลการเกิดอุบัติเหตุ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000005491681046','0505','ข้อมูลการตาย','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000005429708658','0506','ข้อมูลโรคเรื้อรัง','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000007804163757','0507','ข้อมูลโรคเฝ้าระวัง','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000000696544180','0600','เมนูผู้ป่วยใน','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000008262157666','0601','Admit','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000005557912239','0602','ย้อนกลับการ Admit','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000003799104297','0603','ฝากนอน','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000003994380045','0604','ยกเลิกการฝากนอน','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000000646456242','0605','ส่งผู้ป่วยกลับวอร์ด','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000003536597382','0606','ประวัติการสั่งยาต่อเนื่อง','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000007063435939','0700','เมนูการสั่งตรวจ','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000006222998441','0701','การคืนยา','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000001608060398','0702','สั่งชุดรายการตรวจรักษา','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000000469830215','0703','สั่งรายการเหมือนครั้งที่แล้ว','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000003699558726','0704','สั่งรายการเหมือนวันที่แล้ว','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000007945349015','0705','สั่งยาต่อเนื่อง','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000006218875369','0800','เมนูรายการพิมพ์','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000005370132894','0801','ใบรายการตรวจรักษา','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000007153804148','0802','ใบ Summary','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000008705180037','0803','ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000000625120798','0804','ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000001136026001','0805','ใบสรุปค่าใช้จ่ายตามรายการ','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000005242726304','0806','รายการยาที่เลือก','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000003968063626','0807','ใบ OPD Card','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000006755433446','0808','พิมพ์สติ๊กเกอร์ยา','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000003525339346','0809','ใบ Index','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000000900030995','0900','เมนูการย้อนกลับ','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000007558342008','0901','ย้อนกลับทางการเงิน','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000003656256938','0902','ย้อนกลับทางการแพทย์','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000001837973793','1000','เมนูตัวช่วย','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000008612216350','1001','การเชื่อมต่อฐานข้อมูล','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000004521497331','1002','ข้อมูลโปรแกรม','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000002583721562','1003','กำ้หนด path รูปแบบอักษร','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000000656564505','1004','กำ้หนด path งานพิมพ์','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000000161400790','2100','แถบรายชื่อ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000007632467110','2200','แถบรายชื่อตามจุดบริการ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000008537874722','2300','แถบรายชื่อในวอร์ด','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000000440760951','2400','แถบข้อมูลผู้ป่วย','4','LAB','1','0',' ');
insert into  f_gui_action_authen values ('000000005256693552','2500','แถบการรับบริการ','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000001873938836','2600','แถบบันทึกอาการเจ็บป่วย','4','LAB','1','0',' ');
insert into  f_gui_action_authen values ('000000001794247984','2700','แถบรายการตรวจรักษา','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000001030604913','2800','แถบการวินิจฉัย','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000004910859625','2900','แถบการเงิน','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000005124677156','3000','แถบแลบ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000009169397473','3100','แถบรังสี','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000006186752307','5100','ผู้ดูแลระบบ','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000008006921998','5101','ตั้งค่าของระบบ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000000026448746','5102','คนไข้ที่ถูกล็อก','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000006251190711','5103','สิทธิการใช้งาน','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000009180051279','5200','รายชื่อจุดบริการ','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000004399728619','5201','รายชื่อวอร์ด','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000001539127909','5202','รายชื่อจุดบริการ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000008631255009','5203','รายชื่อคลินิก','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000009140255759','5204','สถานพยาบาล','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000001110412185','5205','กำหนดคิว','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000007047081777','5300','รายการกลุ่ม','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000002296141183','5301','รายการ Oder','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000005383415447','5302','รายการใบเสร็จ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000001697050448','5400','รายการตรวจรักษา','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000001045949235','5401','รายการตรวจรักษา','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000000765400041','5402','รายการอัตโนมัติ','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000002259752793','5403','อาการที่พบบ่อย','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000002722733750','5404','รายการยาชุด','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000004417439114','5405','Dx ที่พบบ่อย','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000009308906028','5406','ด้านการฉาย X-ray','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000000198602489','5407','ท่าการฉาย X-ray','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000005392632904','5408','ฟิล์มสำหรับ X-ray','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000009090799522','5500','รายละเอียดยา','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000004632408625','5501','ช่วงเวลาที่ใช้ยา','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000009574252092','5502','วิธีการใช้ยา','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000004894553444','5503','หน่วยยา','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000002493111368','5600','รายการ ICD','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000001789424858','5601','รายการ ICD-10','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000002776290369','5602','รายการ ICD-9','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000004752849812','5603','รายการโรคเรื้อรัง','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000002606589378','5604','รายการกลุ่ม ICD-10','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000008759233151','5700','การชำระเงิน','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000006440335171','5701','ผู้ชำระเงิน','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000008593466974','5702','รายการส่วนลด','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000003679576627','5703','สิทธิการรักษา','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000003124869719','5800','รายการอื่นๆ','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000000179778460','5801','ผู้ใช้งาน','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000007118265233','5802','แสดงเลข Sequence','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000004678943535','5803','สถานพยาบาลที่ติดตั้ง','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000004591663033','5900','รายงาน','4','LAB','0','1',' ');
insert into  f_gui_action_authen values ('000000000356449874','5901','รายงาน SQL','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000003799678790','5902','รายการคำสั่ง SQL','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000007425114428','5903','รายงาน SQL ตามวันที่','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000001705988599','5904','รายงานคำสั่ง SQL ตามวันที่','4','LAB','1','1',' ');
insert into  f_gui_action_authen values ('000000007437869029','0100','เมนูระบบ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000008832139102','0101','ออกจากระบบ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000009016958139','0200','เมนูมุมมอง','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000001065564815','0201','รีเฟรช','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000003311019416','0202','ปลดล็อก','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000007214600903','0203','ค้นหาผู้ป่วย','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000003632299325','0300','เมนูแถบ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000002556752124','0400','เมนูเครื่องมือ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000000557343661','0401','การนัดหมาย','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000003488881051','0402','รายการนัดทั้งหมด','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000002080820043','0403','ประวัติการรับบริการ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000004710954955','0404','ประวัติรายการสั่งตรวจ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000009140926311','0405','ประวัติการคิดเงิน','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000002621427276','0406','จับคู่คำนำหน้ากับเพศ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000003589635549','0407','ลำดับเลข Sequence ล่าสุด','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000000141371434','0408','การ refer ผู้ป่วย','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000007221595249','0409','รายงานต่าง ๆ ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000003791512300','0500','เมนูผู้ป่วยทั่วไป','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000003217848483','0501','กองทุนยา/การจำหน่ายยา','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000002202974120','0502','ค้างบันทึก','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000001876935494','0503','ยกเลิกการเข้ารับบริการ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000001436748084','0504','ข้อมูลการเกิดอุบัติเหตุ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000000024895925','0505','ข้อมูลการตาย','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000000512744059','0506','ข้อมูลโรคเรื้อรัง','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000002401982192','0507','ข้อมูลโรคเฝ้าระวัง','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000005807198827','0600','เมนูผู้ป่วยใน','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000005658522103','0601','Admit','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000004773823640','0602','ย้อนกลับการ Admit','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000003552617124','0603','ฝากนอน','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000003492267470','0604','ยกเลิกการฝากนอน','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000000381727643','0605','ส่งผู้ป่วยกลับวอร์ด','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000003466394811','0606','ประวัติการสั่งยาต่อเนื่อง','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000008608462674','0700','เมนูการสั่งตรวจ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000006909918045','0701','การคืนยา','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000003724053728','0702','สั่งชุดรายการตรวจรักษา','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000006399724948','0703','สั่งรายการเหมือนครั้งที่แล้ว','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000002453334779','0704','สั่งรายการเหมือนวันที่แล้ว','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000000585685886','0705','สั่งยาต่อเนื่อง','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000009921087082','0800','เมนูรายการพิมพ์','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000007863971277','0801','ใบรายการตรวจรักษา','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000003006075613','0802','ใบ Summary','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000001199322707','0803','ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000004097075169','0804','ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000000176966781','0805','ใบสรุปค่าใช้จ่ายตามรายการ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000005281858853','0806','รายการยาที่เลือก','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000007839809382','0807','ใบ OPD Card','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000003749065932','0808','พิมพ์สติ๊กเกอร์ยา','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000005790358170','0809','ใบ Index','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000006123825757','0900','เมนูการย้อนกลับ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000008684862535','0901','ย้อนกลับทางการเงิน','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000001506996035','0902','ย้อนกลับทางการแพทย์','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000009801102231','1000','เมนูตัวช่วย','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000009217166478','1001','การเชื่อมต่อฐานข้อมูล','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000000408950583','1002','ข้อมูลโปรแกรม','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000009372671604','1003','กำ้หนด path รูปแบบอักษร','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000003708471389','1004','กำ้หนด path งานพิมพ์','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000009382542576','2100','แถบรายชื่อ','9','ADMIN','1','0',' ');
insert into  f_gui_action_authen values ('000000007024227548','2200','แถบรายชื่อตามจุดบริการ','9','ADMIN','1','0',' ');
insert into  f_gui_action_authen values ('000000001681596519','2300','แถบรายชื่อในวอร์ด','9','ADMIN','1','0',' ');
insert into  f_gui_action_authen values ('000000005563356606','2400','แถบข้อมูลผู้ป่วย','9','ADMIN','1','0',' ');
insert into  f_gui_action_authen values ('000000002342996054','2500','แถบการรับบริการ','9','ADMIN','1','0',' ');
insert into  f_gui_action_authen values ('000000005571680055','2600','แถบบันทึกอาการเจ็บป่วย','9','ADMIN','1','0',' ');
insert into  f_gui_action_authen values ('000000003885124262','2700','แถบรายการตรวจรักษา','9','ADMIN','1','0',' ');
insert into  f_gui_action_authen values ('000000001275797315','2800','แถบการวินิจฉัย','9','ADMIN','1','0',' ');
insert into  f_gui_action_authen values ('000000001168268726','2900','แถบการเงิน','9','ADMIN','1','0',' ');
insert into  f_gui_action_authen values ('000000007105175877','3000','แถบแลบ','9','ADMIN','1','0',' ');
insert into  f_gui_action_authen values ('000000006916419103','3100','แถบรังสี','9','ADMIN','1','0',' ');
insert into  f_gui_action_authen values ('000000006778311263','5100','ผู้ดูแลระบบ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000003422712559','5101','ตั้งค่าของระบบ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000002748994537','5102','คนไข้ที่ถูกล็อก','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000002382233248','5103','สิทธิการใช้งาน','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000000986734088','5200','รายชื่อจุดบริการ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000003677743021','5201','รายชื่อวอร์ด','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000006259806239','5202','รายชื่อจุดบริการ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000002113519374','5203','รายชื่อคลินิก','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000005056921750','5204','สถานพยาบาล','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000004497049422','5205','กำหนดคิว','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000006486961686','5300','รายการกลุ่ม','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000006711413955','5301','รายการ Oder','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000004304805688','5302','รายการใบเสร็จ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000005262542427','5400','รายการตรวจรักษา','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000005400341078','5401','รายการตรวจรักษา','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000001641141863','5402','รายการอัตโนมัติ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000005338938938','5403','อาการที่พบบ่อย','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000002281289163','5404','รายการยาชุด','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000009942905214','5405','Dx ที่พบบ่อย','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000006154910837','5406','ด้านการฉาย X-ray','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000002448802360','5407','ท่าการฉาย X-ray','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000005498442252','5408','ฟิล์มสำหรับ X-ray','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000008865479257','5500','รายละเอียดยา','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000009865878197','5501','ช่วงเวลาที่ใช้ยา','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000004098788558','5502','วิธีการใช้ยา','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000006838445821','5503','หน่วยยา','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000008914192225','5600','รายการ ICD','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000004148784989','5601','รายการ ICD-10','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000002496917243','5602','รายการ ICD-9','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000002706559294','5603','รายการโรคเรื้อรัง','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000008266384402','5604','รายการกลุ่ม ICD-10','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000000708875928','5700','การชำระเงิน','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000006638977752','5701','ผู้ชำระเงิน','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000008505008474','5702','รายการส่วนลด','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000008948686852','5703','สิทธิการรักษา','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000007639231333','5800','รายการอื่นๆ','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000001972937129','5801','ผู้ใช้งาน','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000006152839270','5802','แสดงเลข Sequence','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000004121367403','5803','สถานพยาบาลที่ติดตั้ง','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000004077656299','5900','รายงาน','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000007885087122','5901','รายงาน SQL','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000006545813268','5902','รายการคำสั่ง SQL','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000000575928504','5903','รายงาน SQL ตามวันที่','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000008177789270','5904','รายงานคำสั่ง SQL ตามวันที่','9','ADMIN','1','1',' ');
insert into  f_gui_action_authen values ('000000007578551772','0100','เมนูระบบ','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000007652452317','0101','ออกจากระบบ','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000001568329693','0200','เมนูมุมมอง','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000009525104781','0201','รีเฟรช','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000003596412176','0202','ปลดล็อก','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000005514602739','0203','ค้นหาผู้ป่วย','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000000198104555','0300','เมนูแถบ','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000006357004006','0400','เมนูเครื่องมือ','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000006235844683','0401','การนัดหมาย','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000009805657073','0402','รายการนัดทั้งหมด','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000008944026477','0403','ประวัติการรับบริการ','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000009987197605','0404','ประวัติรายการสั่งตรวจ','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000001406318396','0405','ประวัติการคิดเงิน','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000007796835885','0406','จับคู่คำนำหน้ากับเพศ','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000006679871092','0407','ลำดับเลข Sequence ล่าสุด','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000003688107870','0408','การ refer ผู้ป่วย','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000009104024819','0409','รายงานต่าง ๆ ','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000000591697651','0500','เมนูผู้ป่วยทั่วไป','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000000519214228','0501','กองทุนยา/การจำหน่ายยา','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000000951025536','0502','ค้างบันทึก','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000003606367645','0503','ยกเลิกการเข้ารับบริการ','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000003311312329','0504','ข้อมูลการเกิดอุบัติเหตุ','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000006176091785','0505','ข้อมูลการตาย','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000008522278442','0506','ข้อมูลโรคเรื้อรัง','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000001734466650','0507','ข้อมูลโรคเฝ้าระวัง','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000006547295580','0600','เมนูผู้ป่วยใน','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000007178744386','0601','Admit','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000002904678789','0602','ย้อนกลับการ Admit','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000006088284841','0603','ฝากนอน','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000009847172315','0604','ยกเลิกการฝากนอน','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000000236552880','0605','ส่งผู้ป่วยกลับวอร์ด','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000005117039164','0606','ประวัติการสั่งยาต่อเนื่อง','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000006028116463','0700','เมนูการสั่งตรวจ','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000009256297808','0701','การคืนยา','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000001335666888','0702','สั่งชุดรายการตรวจรักษา','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000007420553630','0703','สั่งรายการเหมือนครั้งที่แล้ว','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000000357821623','0704','สั่งรายการเหมือนวันที่แล้ว','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000000012834904','0705','สั่งยาต่อเนื่อง','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000004726718647','0800','เมนูรายการพิมพ์','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000007449364398','0801','ใบรายการตรวจรักษา','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000002159330653','0802','ใบ Summary','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000002377046515','0803','ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000004868402126','0804','ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000009723645576','0805','ใบสรุปค่าใช้จ่ายตามรายการ','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000008034582904','0806','รายการยาที่เลือก','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000003273437232','0807','ใบ OPD Card','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000009603589388','0808','พิมพ์สติ๊กเกอร์ยา','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000001877050192','0809','ใบ Index','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000001130205886','0900','เมนูการย้อนกลับ','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000008934028767','0901','ย้อนกลับทางการเงิน','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000001370050097','0902','ย้อนกลับทางการแพทย์','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000003096389181','1000','เมนูตัวช่วย','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000009751878172','1001','การเชื่อมต่อฐานข้อมูล','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000004053337870','1002','ข้อมูลโปรแกรม','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000002085724161','1003','กำ้หนด path รูปแบบอักษร','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000005249914756','1004','กำ้หนด path งานพิมพ์','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000001119466205','2100','แถบรายชื่อ','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000001100372410','2200','แถบรายชื่อตามจุดบริการ','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000006498894225','2300','แถบรายชื่อในวอร์ด','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000002971610002','2400','แถบข้อมูลผู้ป่วย','5','X-RAY','1','0',' ');
insert into  f_gui_action_authen values ('000000002870658431','2500','แถบการรับบริการ','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000009380244535','2600','แถบบันทึกอาการเจ็บป่วย','5','X-RAY','1','0',' ');
insert into  f_gui_action_authen values ('000000008256491610','2700','แถบรายการตรวจรักษา','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000007572390555','2800','แถบการวินิจฉัย','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000003289966893','2900','แถบการเงิน','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000007197748577','3000','แถบแลบ','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000002719163038','3100','แถบรังสี','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000001065147181','5100','ผู้ดูแลระบบ','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000004921603859','5101','ตั้งค่าของระบบ','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000002047532698','5102','คนไข้ที่ถูกล็อก','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000000639610371','5103','สิทธิการใช้งาน','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000000819070018','5200','รายชื่อจุดบริการ','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000005218880988','5201','รายชื่อวอร์ด','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000003140245178','5202','รายชื่อจุดบริการ','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000007221115285','5203','รายชื่อคลินิก','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000002399712586','5204','สถานพยาบาล','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000002189423302','5205','กำหนดคิว','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000007771313180','5300','รายการกลุ่ม','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000001786041927','5301','รายการ Oder','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000008004768342','5302','รายการใบเสร็จ','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000002614626389','5400','รายการตรวจรักษา','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000008048769579','5401','รายการตรวจรักษา','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000004309507442','5402','รายการอัตโนมัติ','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000000571548465','5403','อาการที่พบบ่อย','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000005353427159','5404','รายการยาชุด','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000007318813115','5405','Dx ที่พบบ่อย','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000006411299104','5406','ด้านการฉาย X-ray','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000009110665751','5407','ท่าการฉาย X-ray','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000002484601025','5408','ฟิล์มสำหรับ X-ray','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000006262550300','5500','รายละเอียดยา','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000006049507034','5501','ช่วงเวลาที่ใช้ยา','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000009111443822','5502','วิธีการใช้ยา','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000006598437553','5503','หน่วยยา','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000007084635596','5600','รายการ ICD','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000007122231505','5601','รายการ ICD-10','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000002968089493','5602','รายการ ICD-9','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000003417784695','5603','รายการโรคเรื้อรัง','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000000474291664','5604','รายการกลุ่ม ICD-10','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000007459116593','5700','การชำระเงิน','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000009291674954','5701','ผู้ชำระเงิน','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000005110098903','5702','รายการส่วนลด','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000004091453827','5703','สิทธิการรักษา','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000001272351156','5800','รายการอื่นๆ','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000006701503067','5801','ผู้ใช้งาน','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000001146271835','5802','แสดงเลข Sequence','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000003215551776','5803','สถานพยาบาลที่ติดตั้ง','5','X-RAY','1','0',' ');
insert into  f_gui_action_authen values ('000000005037167302','5900','รายงาน','5','X-RAY','0','1',' ');
insert into  f_gui_action_authen values ('000000007230431037','5901','รายงาน SQL','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000000368649583','5902','รายการคำสั่ง SQL','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000003891709184','5903','รายงาน SQL ตามวันที่','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000008984761067','5904','รายงานคำสั่ง SQL ตามวันที่','5','X-RAY','1','1',' ');
insert into  f_gui_action_authen values ('000000004528845415','0100','เมนูระบบ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000001946846437','0101','ออกจากระบบ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000001311691490','0200','เมนูมุมมอง','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000002386611053','0201','รีเฟรช','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000006908424130','0202','ปลดล็อก','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000001674385260','0203','ค้นหาผู้ป่วย','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000007441474643','0300','เมนูแถบ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000006278739067','0400','เมนูเครื่องมือ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000004874908108','0401','การนัดหมาย','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000005928700907','0402','รายการนัดทั้งหมด','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000001842052602','0403','ประวัติการรับบริการ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000005708998490','0404','ประวัติรายการสั่งตรวจ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000004268867129','0405','ประวัติการคิดเงิน','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000002241270596','0406','จับคู่คำนำหน้ากับเพศ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000009051412861','0407','ลำดับเลข Sequence ล่าสุด','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000002481680901','0408','การ refer ผู้ป่วย','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000005379134887','0409','รายงานต่าง ๆ ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000007046135693','0500','เมนูผู้ป่วยทั่วไป','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000008062248223','0501','กองทุนยา/การจำหน่ายยา','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000007906478458','0502','ค้างบันทึก','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000000446711296','0503','ยกเลิกการเข้ารับบริการ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000005450603322','0504','ข้อมูลการเกิดอุบัติเหตุ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000008115734138','0505','ข้อมูลการตาย','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000005088683624','0506','ข้อมูลโรคเรื้อรัง','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000003920365056','0507','ข้อมูลโรคเฝ้าระวัง','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000004295140793','0600','เมนูผู้ป่วยใน','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000000197600439','0601','Admit','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000007836371360','0602','ย้อนกลับการ Admit','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000006973036505','0603','ฝากนอน','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000000238610908','0604','ยกเลิกการฝากนอน','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000001062976297','0605','ส่งผู้ป่วยกลับวอร์ด','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000008745905179','0606','ประวัติการสั่งยาต่อเนื่อง','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000002251020747','0700','เมนูการสั่งตรวจ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000008208506263','0701','การคืนยา','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000009985623958','0702','สั่งชุดรายการตรวจรักษา','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000007786874071','0703','สั่งรายการเหมือนครั้งที่แล้ว','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000009603310049','0704','สั่งรายการเหมือนวันที่แล้ว','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000001971858768','0705','สั่งยาต่อเนื่อง','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000007108822703','0800','เมนูรายการพิมพ์','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000009279828634','0801','ใบรายการตรวจรักษา','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000008626359063','0802','ใบ Summary','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000007811018589','0803','ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000006269297653','0804','ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000009544957119','0805','ใบสรุปค่าใช้จ่ายตามรายการ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000009259931829','0806','รายการยาที่เลือก','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000004329507129','0807','ใบ OPD Card','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000004472237449','0808','พิมพ์สติ๊กเกอร์ยา','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000009697102719','0809','ใบ Index','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000007382575955','0900','เมนูการย้อนกลับ','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000006266670134','0901','ย้อนกลับทางการเงิน','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000004814578124','0902','ย้อนกลับทางการแพทย์','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000008072290664','1000','เมนูตัวช่วย','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000008380663161','1001','การเชื่อมต่อฐานข้อมูล','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000007865866696','1002','ข้อมูลโปรแกรม','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000009585374218','1003','กำ้หนด path รูปแบบอักษร','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000004593198322','1004','กำ้หนด path งานพิมพ์','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000008002825020','2100','แถบรายชื่อ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000001819450416','2200','แถบรายชื่อตามจุดบริการ','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000005516220553','2300','แถบรายชื่อในวอร์ด','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000005015769874','2400','แถบข้อมูลผู้ป่วย','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000006462001424','2500','แถบการรับบริการ','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000003104813937','2600','แถบบันทึกอาการเจ็บป่วย','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000003711323401','2700','แถบรายการตรวจรักษา','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000006446438981','2800','แถบการวินิจฉัย','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000008710214174','2900','แถบการเงิน','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000003129277419','3000','แถบแลบ','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000002655967218','3100','แถบรังสี','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000006779192574','5100','ผู้ดูแลระบบ','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000001086563743','5101','ตั้งค่าของระบบ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000008517630073','5102','คนไข้ที่ถูกล็อก','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000003883757365','5103','สิทธิการใช้งาน','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000009773970116','5200','รายชื่อจุดบริการ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000002126842412','5201','รายชื่อวอร์ด','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000000604633300','5202','รายชื่อจุดบริการ','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000006377165418','5203','รายชื่อคลินิก','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000004382661184','5204','สถานพยาบาล','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000003223542919','5205','กำหนดคิว','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000006235824817','5300','รายการกลุ่ม','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000007582538388','5301','รายการ Oder','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000001626230755','5302','รายการใบเสร็จ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000003037938781','5400','รายการตรวจรักษา','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000007833467090','5401','รายการตรวจรักษา','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000006039880325','5402','รายการอัตโนมัติ','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000004836263093','5403','อาการที่พบบ่อย','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000008091295427','5404','รายการยาชุด','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000000850507555','5405','Dx ที่พบบ่อย','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000003059350245','5406','ด้านการฉาย X-ray','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000009396486328','5407','ท่าการฉาย X-ray','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000000800167200','5408','ฟิล์มสำหรับ X-ray','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000009713926601','5500','รายละเอียดยา','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000008620562359','5501','ช่วงเวลาที่ใช้ยา','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000006254598432','5502','วิธีการใช้ยา','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000005371126867','5503','หน่วยยา','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000009149870483','5600','รายการ ICD','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000009727417920','5601','รายการ ICD-10','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000009421989481','5602','รายการ ICD-9','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000001433157399','5603','รายการโรคเรื้อรัง','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000006386480849','5604','รายการกลุ่ม ICD-10','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000007820333483','5700','การชำระเงิน','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000001438713181','5701','ผู้ชำระเงิน','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000005061336389','5702','รายการส่วนลด','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000001146342614','5703','สิทธิการรักษา','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000005529375883','5800','รายการอื่นๆ','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000002353328747','5801','ผู้ใช้งาน','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000006933326863','5802','แสดงเลข Sequence','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000004568998126','5803','สถานพยาบาลที่ติดตั้ง','13','งานผู้ป่วยใน','1','0',' ');
insert into  f_gui_action_authen values ('000000002045953127','5900','รายงาน','13','งานผู้ป่วยใน','0','1',' ');
insert into  f_gui_action_authen values ('000000007152003677','5901','รายงาน SQL','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000007760580952','5902','รายการคำสั่ง SQL','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000005244980282','5903','รายงาน SQL ตามวันที่','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000000697347999','5904','รายงานคำสั่ง SQL ตามวันที่','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('000000003768527201','0100','เมนูระบบ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000001849055620','0101','ออกจากระบบ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000000396339473','0200','เมนูมุมมอง','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000001927528658','0201','รีเฟรช','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000008816652475','0202','ปลดล็อก','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000000669625618','0203','ค้นหาผู้ป่วย','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000002904655216','0300','เมนูแถบ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009819302572','0400','เมนูเครื่องมือ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000001008456053','0401','การนัดหมาย','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009656275568','0402','รายการนัดทั้งหมด','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000000366031694','0403','ประวัติการรับบริการ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000000537029996','0404','ประวัติรายการสั่งตรวจ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000002857158404','0405','ประวัติการคิดเงิน','2','พยาบาล','0','1',' ');
insert into  f_gui_action_authen values ('000000003100537682','0406','จับคู่คำนำหน้ากับเพศ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000000643603505','0407','ลำดับเลข Sequence ล่าสุด','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000006625743855','0408','การ refer ผู้ป่วย','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009710124200','0409','รายงานต่าง ๆ ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000003973536687','0500','เมนูผู้ป่วยทั่วไป','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009491582001','0501','กองทุนยา/การจำหน่ายยา','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000001971674108','0502','ค้างบันทึก','2','พยาบาล','0','1',' ');
insert into  f_gui_action_authen values ('000000001417524600','0503','ยกเลิกการเข้ารับบริการ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000007782391529','0504','ข้อมูลการเกิดอุบัติเหตุ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009740896444','0505','ข้อมูลการตาย','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000008410803775','0506','ข้อมูลโรคเรื้อรัง','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000006014404003','0507','ข้อมูลโรคเฝ้าระวัง','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000002155615042','0600','เมนูผู้ป่วยใน','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000008264996395','0601','Admit','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000006466238155','0602','ย้อนกลับการ Admit','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000006678666957','0603','ฝากนอน','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000006752543860','0604','ยกเลิกการฝากนอน','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000006485396964','0605','ส่งผู้ป่วยกลับวอร์ด','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009501793630','0606','ประวัติการสั่งยาต่อเนื่อง','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000002924761374','0700','เมนูการสั่งตรวจ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000001304720820','0701','การคืนยา','2','พยาบาล','0','1',' ');
insert into  f_gui_action_authen values ('000000002530065750','0702','สั่งชุดรายการตรวจรักษา','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000002698367706','0703','สั่งรายการเหมือนครั้งที่แล้ว','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000004502602842','0704','สั่งรายการเหมือนวันที่แล้ว','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000003122542539','0705','สั่งยาต่อเนื่อง','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000008323765087','0800','เมนูรายการพิมพ์','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000005691282858','0801','ใบรายการตรวจรักษา','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000001793546804','0802','ใบ Summary','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009238374967','0803','ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000002867979395','0804','ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000000450940054','0805','ใบสรุปค่าใช้จ่ายตามรายการ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009629986177','0806','รายการยาที่เลือก','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000002507733098','0807','ใบ OPD Card','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000003759071726','0808','พิมพ์สติ๊กเกอร์ยา','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000004045762473','0809','ใบ Index','2','พยาบาล','0','1',' ');
insert into  f_gui_action_authen values ('000000002412412941','0900','เมนูการย้อนกลับ','2','พยาบาล','0','1',' ');
insert into  f_gui_action_authen values ('000000005453008712','0901','ย้อนกลับทางการเงิน','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000002596831413','0902','ย้อนกลับทางการแพทย์','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009295543750','1000','เมนูตัวช่วย','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000007157549375','1001','การเชื่อมต่อฐานข้อมูล','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000000409621460','1002','ข้อมูลโปรแกรม','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000006351587456','1003','กำ้หนด path รูปแบบอักษร','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000005004959463','1004','กำ้หนด path งานพิมพ์','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000007683421846','2100','แถบรายชื่อ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009622358341','2200','แถบรายชื่อตามจุดบริการ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000005986581033','2300','แถบรายชื่อในวอร์ด','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000001461142828','2400','แถบข้อมูลผู้ป่วย','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000007522014315','2500','แถบการรับบริการ','2','พยาบาล','0','1',' ');
insert into  f_gui_action_authen values ('000000004340061003','2600','แถบบันทึกอาการเจ็บป่วย','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000005673304622','2700','แถบรายการตรวจรักษา','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000006185861734','2800','แถบการวินิจฉัย','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000001347401794','2900','แถบการเงิน','2','พยาบาล','0','1',' ');
insert into  f_gui_action_authen values ('000000007669707204','3000','แถบแลบ','2','พยาบาล','0','1',' ');
insert into  f_gui_action_authen values ('000000000791028981','3100','แถบรังสี','2','พยาบาล','0','1',' ');
insert into  f_gui_action_authen values ('000000009551979894','5100','ผู้ดูแลระบบ','2','พยาบาล','0','1',' ');
insert into  f_gui_action_authen values ('000000004717036929','5101','ตั้งค่าของระบบ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000001976320974','5102','คนไข้ที่ถูกล็อก','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000000567759030','5103','สิทธิการใช้งาน','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009805518643','5200','รายชื่อจุดบริการ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009746118007','5201','รายชื่อวอร์ด','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000007953047397','5202','รายชื่อจุดบริการ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000001698372864','5203','รายชื่อคลินิก','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000002278787517','5204','สถานพยาบาล','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009969744664','5205','กำหนดคิว','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009136102362','5300','รายการกลุ่ม','2','พยาบาล','0','1',' ');
insert into  f_gui_action_authen values ('000000002566711156','5301','รายการ Oder','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000008136677823','5302','รายการใบเสร็จ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000001094819365','5400','รายการตรวจรักษา','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000002602216145','5401','รายการตรวจรักษา','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000003801905773','5402','รายการอัตโนมัติ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000003867367856','5403','อาการที่พบบ่อย','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000005603432837','5404','รายการยาชุด','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000003861562623','5405','Dx ที่พบบ่อย','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009815249175','5406','ด้านการฉาย X-ray','2','พยาบาล','0','1',' ');
insert into  f_gui_action_authen values ('000000003910321323','5407','ท่าการฉาย X-ray','2','พยาบาล','0','1',' ');
insert into  f_gui_action_authen values ('000000008241724042','5408','ฟิล์มสำหรับ X-ray','2','พยาบาล','0','1',' ');
insert into  f_gui_action_authen values ('000000001547073150','5500','รายละเอียดยา','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000007692416647','5501','ช่วงเวลาที่ใช้ยา','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000005308601211','5502','วิธีการใช้ยา','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000006312980245','5503','หน่วยยา','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000005401410843','5600','รายการ ICD','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000004356880351','5601','รายการ ICD-10','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000005079164975','5602','รายการ ICD-9','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000003391005162','5603','รายการโรคเรื้อรัง','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009275780563','5604','รายการกลุ่ม ICD-10','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000000273859014','5700','การชำระเงิน','2','พยาบาล','0','1',' ');
insert into  f_gui_action_authen values ('000000008113948590','5701','ผู้ชำระเงิน','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000007579546105','5702','รายการส่วนลด','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000009380137766','5703','สิทธิการรักษา','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000008130167244','5800','รายการอื่นๆ','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000005856179611','5801','ผู้ใช้งาน','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000005765841438','5802','แสดงเลข Sequence','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000006862247295','5803','สถานพยาบาลที่ติดตั้ง','2','พยาบาล','1','0',' ');
insert into  f_gui_action_authen values ('000000007903847637','5900','รายงาน','2','พยาบาล','0','1',' ');
insert into  f_gui_action_authen values ('000000003740511449','5901','รายงาน SQL','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000003451112774','5902','รายการคำสั่ง SQL','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000002943441009','5903','รายงาน SQL ตามวันที่','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000005528475018','5904','รายงานคำสั่ง SQL ตามวันที่','2','พยาบาล','1','1',' ');
insert into  f_gui_action_authen values ('000000008442201851','0100','เมนูระบบ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000005688091404','0101','ออกจากระบบ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000004301802386','0200','เมนูมุมมอง','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000000960547979','0201','รีเฟรช','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000004349706299','0202','ปลดล็อก','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000008415435521','0203','ค้นหาผู้ป่วย','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000006168910877','0300','เมนูแถบ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000002874392801','0400','เมนูเครื่องมือ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000002356692251','0401','การนัดหมาย','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000001051159141','0402','รายการนัดทั้งหมด','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000000694156530','0403','ประวัติการรับบริการ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000001437519103','0404','ประวัติรายการสั่งตรวจ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000003063623536','0405','ประวัติการคิดเงิน','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000000591961487','0406','จับคู่คำนำหน้ากับเพศ','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000008531034474','0407','ลำดับเลข Sequence ล่าสุด','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000007660389495','0408','การ refer ผู้ป่วย','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000000785706983','0409','รายงานต่าง ๆ ','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000004662607923','0500','เมนูผู้ป่วยทั่วไป','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000002312716865','0501','กองทุนยา/การจำหน่ายยา','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000007668072189','0502','ค้างบันทึก','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000004383127062','0503','ยกเลิกการเข้ารับบริการ','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000005969281842','0504','ข้อมูลการเกิดอุบัติเหตุ','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000001709586733','0505','ข้อมูลการตาย','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000002907267064','0506','ข้อมูลโรคเรื้อรัง','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000005861143281','0507','ข้อมูลโรคเฝ้าระวัง','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000003433090514','0600','เมนูผู้ป่วยใน','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000001099318197','0601','Admit','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000002780710412','0602','ย้อนกลับการ Admit','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000007931759965','0603','ฝากนอน','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000005914677291','0604','ยกเลิกการฝากนอน','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000002530465837','0605','ส่งผู้ป่วยกลับวอร์ด','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000004491975343','0606','ประวัติการสั่งยาต่อเนื่อง','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000007285321033','0700','เมนูการสั่งตรวจ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000002888151723','0701','การคืนยา','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000008623429160','0702','สั่งชุดรายการตรวจรักษา','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000005987208272','0703','สั่งรายการเหมือนครั้งที่แล้ว','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000001189520041','0704','สั่งรายการเหมือนวันที่แล้ว','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000002682322193','0705','สั่งยาต่อเนื่อง','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000002328037857','0800','เมนูรายการพิมพ์','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000001796347889','0801','ใบรายการตรวจรักษา','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000001214964706','0802','ใบ Summary','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000002979473356','0803','ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000003044901024','0804','ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000008304896640','0805','ใบสรุปค่าใช้จ่ายตามรายการ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000009380562761','0806','รายการยาที่เลือก','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000000146707218','0807','ใบ OPD Card','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000001518053218','0808','พิมพ์สติ๊กเกอร์ยา','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000004528325400','0809','ใบ Index','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000008818647894','0900','เมนูการย้อนกลับ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000003672117105','0901','ย้อนกลับทางการเงิน','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000009119316513','0902','ย้อนกลับทางการแพทย์','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000006814712573','1000','เมนูตัวช่วย','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000003178900904','1001','การเชื่อมต่อฐานข้อมูล','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000009487845729','1002','ข้อมูลโปรแกรม','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000000670195864','1003','กำ้หนด path รูปแบบอักษร','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000001067225519','1004','กำ้หนด path งานพิมพ์','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000006421759848','2100','แถบรายชื่อ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000001284332009','2200','แถบรายชื่อตามจุดบริการ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000003785006147','2300','แถบรายชื่อในวอร์ด','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000004056580846','2400','แถบข้อมูลผู้ป่วย','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000007396157182','2500','แถบการรับบริการ','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000002054822863','2600','แถบบันทึกอาการเจ็บป่วย','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000001869330351','2700','แถบรายการตรวจรักษา','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000006184622818','2800','แถบการวินิจฉัย','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000001653061116','2900','แถบการเงิน','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000009056488954','3000','แถบแลบ','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000002370790133','3100','แถบรังสี','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000001663684375','5100','ผู้ดูแลระบบ','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000006315436939','5101','ตั้งค่าของระบบ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000004134253443','5102','คนไข้ที่ถูกล็อก','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000006654505875','5103','สิทธิการใช้งาน','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000008492403063','5200','รายชื่อจุดบริการ','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000002506888621','5201','รายชื่อวอร์ด','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000007925701475','5202','รายชื่อจุดบริการ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000002963511445','5203','รายชื่อคลินิก','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000009837190119','5204','สถานพยาบาล','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000003835379681','5205','กำหนดคิว','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000000850209876','5300','รายการกลุ่ม','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000009324782234','5301','รายการ Oder','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000000469551291','5302','รายการใบเสร็จ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000008735070669','5400','รายการตรวจรักษา','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000005982699571','5401','รายการตรวจรักษา','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000008974420889','5402','รายการอัตโนมัติ','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000006762991883','5403','อาการที่พบบ่อย','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000009088455874','5404','รายการยาชุด','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000008922179822','5405','Dx ที่พบบ่อย','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000006900941273','5406','ด้านการฉาย X-ray','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000006419238199','5407','ท่าการฉาย X-ray','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000007482618402','5408','ฟิล์มสำหรับ X-ray','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000007587515715','5500','รายละเอียดยา','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000002443477930','5501','ช่วงเวลาที่ใช้ยา','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000004572031750','5502','วิธีการใช้ยา','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000000697567079','5503','หน่วยยา','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000000507622173','5600','รายการ ICD','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000009532141300','5601','รายการ ICD-10','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000006207768018','5602','รายการ ICD-9','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000007581253382','5603','รายการโรคเรื้อรัง','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000005620547035','5604','รายการกลุ่ม ICD-10','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000003120627690','5700','การชำระเงิน','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000000945762103','5701','ผู้ชำระเงิน','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000005335016539','5702','รายการส่วนลด','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000002855676429','5703','สิทธิการรักษา','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000007636583606','5800','รายการอื่นๆ','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000005136946764','5801','ผู้ใช้งาน','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000009414834292','5802','แสดงเลข Sequence','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000002845556466','5803','สถานพยาบาลที่ติดตั้ง','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000000863119471','5900','รายงาน','6','เภสัชกร','0','1',' ');
insert into  f_gui_action_authen values ('000000007968314213','5901','รายงาน SQL','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000008747908511','5902','รายการคำสั่ง SQL','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000000998595723','5903','รายงาน SQL ตามวันที่','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000004565248185','5904','รายงานคำสั่ง SQL ตามวันที่','6','เภสัชกร','1','1',' ');
insert into  f_gui_action_authen values ('000000002801439509','0100','เมนูระบบ','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000009770745963','0101','ออกจากระบบ','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000000861174870','0200','เมนูมุมมอง','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000001387927288','0201','รีเฟรช','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000002326930794','0202','ปลดล็อก','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000009914042336','0203','ค้นหาผู้ป่วย','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000003207458517','0300','เมนูแถบ','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000006077763424','0400','เมนูเครื่องมือ','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000006282670999','0401','การนัดหมาย','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000003591313185','0402','รายการนัดทั้งหมด','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000007617465787','0403','ประวัติการรับบริการ','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000007918334557','0404','ประวัติรายการสั่งตรวจ','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000003462229625','0405','ประวัติการคิดเงิน','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000001492360516','0406','จับคู่คำนำหน้ากับเพศ','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000001438727490','0407','ลำดับเลข Sequence ล่าสุด','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000000419473460','0408','การ refer ผู้ป่วย','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000005530840575','0409','รายงานต่าง ๆ ','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000007039299904','0500','เมนูผู้ป่วยทั่วไป','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000006974117268','0501','กองทุนยา/การจำหน่ายยา','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000003763747963','0502','ค้างบันทึก','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000008769857064','0503','ยกเลิกการเข้ารับบริการ','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000002232016704','0504','ข้อมูลการเกิดอุบัติเหตุ','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000001498214460','0505','ข้อมูลการตาย','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000005288762850','0506','ข้อมูลโรคเรื้อรัง','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000001286607596','0507','ข้อมูลโรคเฝ้าระวัง','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000008791560212','0600','เมนูผู้ป่วยใน','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000006276820767','0601','Admit','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000003371800057','0602','ย้อนกลับการ Admit','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000007455097879','0603','ฝากนอน','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000009504123113','0604','ยกเลิกการฝากนอน','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000000149015446','0605','ส่งผู้ป่วยกลับวอร์ด','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000000483703221','0606','ประวัติการสั่งยาต่อเนื่อง','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000008394013611','0700','เมนูการสั่งตรวจ','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000008974898668','0701','การคืนยา','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000000428403523','0702','สั่งชุดรายการตรวจรักษา','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000001891804781','0703','สั่งรายการเหมือนครั้งที่แล้ว','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000007141999237','0704','สั่งรายการเหมือนวันที่แล้ว','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000002620241534','0705','สั่งยาต่อเนื่อง','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000008889977482','0800','เมนูรายการพิมพ์','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000007517083242','0801','ใบรายการตรวจรักษา','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000009856200548','0802','ใบ Summary','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000007658207646','0803','ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000003431270922','0804','ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000005788628370','0805','ใบสรุปค่าใช้จ่ายตามรายการ','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000009838575380','0806','รายการยาที่เลือก','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000000101680362','0807','ใบ OPD Card','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000006631261061','0808','พิมพ์สติ๊กเกอร์ยา','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000009988885641','0809','ใบ Index','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000009485492125','0900','เมนูการย้อนกลับ','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000007314938997','0901','ย้อนกลับทางการเงิน','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000000877727882','0902','ย้อนกลับทางการแพทย์','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000009546037239','1000','เมนูตัวช่วย','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000006359653711','1001','การเชื่อมต่อฐานข้อมูล','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000006563658482','1002','ข้อมูลโปรแกรม','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000005993558852','1003','กำ้หนด path รูปแบบอักษร','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000001005272361','1004','กำ้หนด path งานพิมพ์','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000003499394998','2100','แถบรายชื่อ','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000006169976778','2200','แถบรายชื่อตามจุดบริการ','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000009364737304','2300','แถบรายชื่อในวอร์ด','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000005998586552','2400','แถบข้อมูลผู้ป่วย','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000000808924277','2500','แถบการรับบริการ','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000007378401615','2600','แถบบันทึกอาการเจ็บป่วย','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000000457534792','2700','แถบรายการตรวจรักษา','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000006433141527','2800','แถบการวินิจฉัย','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000003914087380','2900','แถบการเงิน','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000001844972263','3000','แถบแลบ','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000001309679351','3100','แถบรังสี','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000000331110124','5100','ผู้ดูแลระบบ','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000009221756590','5101','ตั้งค่าของระบบ','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000001781231145','5102','คนไข้ที่ถูกล็อก','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000002090045464','5103','สิทธิการใช้งาน','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000005279316778','5200','รายชื่อจุดบริการ','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000009086954538','5201','รายชื่อวอร์ด','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000007216201515','5202','รายชื่อจุดบริการ','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000006143675334','5203','รายชื่อคลินิก','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000000284877807','5204','สถานพยาบาล','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000008970345237','5205','กำหนดคิว','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000007583707100','5300','รายการกลุ่ม','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000002073704802','5301','รายการ Oder','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000000983435992','5302','รายการใบเสร็จ','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000009259735111','5400','รายการตรวจรักษา','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000002899011142','5401','รายการตรวจรักษา','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000001359035716','5402','รายการอัตโนมัติ','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000007609476615','5403','อาการที่พบบ่อย','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000008032746811','5404','รายการยาชุด','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000003157892063','5405','Dx ที่พบบ่อย','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000008766358591','5406','ด้านการฉาย X-ray','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000009913594830','5407','ท่าการฉาย X-ray','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000007453840113','5408','ฟิล์มสำหรับ X-ray','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000003272914052','5500','รายละเอียดยา','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000009387399065','5501','ช่วงเวลาที่ใช้ยา','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000008299486291','5502','วิธีการใช้ยา','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000005727372217','5503','หน่วยยา','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000004855793304','5600','รายการ ICD','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000000517025842','5601','รายการ ICD-10','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000005853814425','5602','รายการ ICD-9','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000008569407149','5603','รายการโรคเรื้อรัง','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000005461395724','5604','รายการกลุ่ม ICD-10','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000008785119469','5700','การชำระเงิน','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000001655003249','5701','ผู้ชำระเงิน','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000009014493239','5702','รายการส่วนลด','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000000528050143','5703','สิทธิการรักษา','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000002964874931','5800','รายการอื่นๆ','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000001772392137','5801','ผู้ใช้งาน','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000002039652376','5802','แสดงเลข Sequence','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000003180495389','5803','สถานพยาบาลที่ติดตั้ง','1','เวชระเบียน','1','0',' ');
insert into  f_gui_action_authen values ('000000000702468860','5900','รายงาน','1','เวชระเบียน','0','1',' ');
insert into  f_gui_action_authen values ('000000000705083233','5901','รายงาน SQL','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000000966330628','5902','รายการคำสั่ง SQL','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000002305924327','5903','รายงาน SQL ตามวันที่','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000003047792704','5904','รายงานคำสั่ง SQL ตามวันที่','1','เวชระเบียน','1','1',' ');
insert into  f_gui_action_authen values ('000000002660198386','0100','เมนูระบบ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000005892084007','0101','ออกจากระบบ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000009870224806','0200','เมนูมุมมอง','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000004113215706','0201','รีเฟรช','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000009033158330','0202','ปลดล็อก','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000000507282512','0203','ค้นหาผู้ป่วย','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000004310662020','0300','เมนูแถบ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000004447111367','0400','เมนูเครื่องมือ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000005727981680','0401','การนัดหมาย','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000002568136088','0402','รายการนัดทั้งหมด','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000008022885395','0403','ประวัติการรับบริการ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000005781140647','0404','ประวัติรายการสั่งตรวจ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000001543496676','0405','ประวัติการคิดเงิน','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000005025856746','0406','จับคู่คำนำหน้ากับเพศ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000003454283029','0407','ลำดับเลข Sequence ล่าสุด','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000001695055930','0408','การ refer ผู้ป่วย','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000006751746549','0409','รายงานต่าง ๆ ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000009352790888','0500','เมนูผู้ป่วยทั่วไป','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000004122348250','0501','กองทุนยา/การจำหน่ายยา','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000003440054315','0502','ค้างบันทึก','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000009207762498','0503','ยกเลิกการเข้ารับบริการ','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000009959870848','0504','ข้อมูลการเกิดอุบัติเหตุ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000003995865322','0505','ข้อมูลการตาย','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000002671471715','0506','ข้อมูลโรคเรื้อรัง','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000003855263621','0507','ข้อมูลโรคเฝ้าระวัง','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000008765746482','0600','เมนูผู้ป่วยใน','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000001108357425','0601','Admit','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000003163878451','0602','ย้อนกลับการ Admit','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000000330742822','0603','ฝากนอน','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000001956480073','0604','ยกเลิกการฝากนอน','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000009017385179','0605','ส่งผู้ป่วยกลับวอร์ด','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000001716399401','0606','ประวัติการสั่งยาต่อเนื่อง','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000002518777052','0700','เมนูการสั่งตรวจ','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000006765695841','0701','การคืนยา','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000002779921851','0702','สั่งชุดรายการตรวจรักษา','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000004403352201','0703','สั่งรายการเหมือนครั้งที่แล้ว','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000003374301095','0704','สั่งรายการเหมือนวันที่แล้ว','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000004708597804','0705','สั่งยาต่อเนื่อง','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000004483173936','0800','เมนูรายการพิมพ์','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000008085054803','0801','ใบรายการตรวจรักษา','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000008102969679','0802','ใบ Summary','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000009119279162','0803','ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000007051854408','0804','ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000008665928494','0805','ใบสรุปค่าใช้จ่ายตามรายการ','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000008878620704','0806','รายการยาที่เลือก','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000009069269030','0807','ใบ OPD Card','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000004047057414','0808','พิมพ์สติ๊กเกอร์ยา','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000006971780334','0809','ใบ Index','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000004677251322','0900','เมนูการย้อนกลับ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000008786270155','0901','ย้อนกลับทางการเงิน','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000007478525206','0902','ย้อนกลับทางการแพทย์','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000000315419649','1000','เมนูตัวช่วย','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000000519800640','1001','การเชื่อมต่อฐานข้อมูล','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000001545562205','1002','ข้อมูลโปรแกรม','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000005271236823','1003','กำ้หนด path รูปแบบอักษร','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000002829526925','1004','กำ้หนด path งานพิมพ์','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000001441428586','2100','แถบรายชื่อ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000009672561674','2200','แถบรายชื่อตามจุดบริการ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000004958887703','2300','แถบรายชื่อในวอร์ด','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000000467957467','2400','แถบข้อมูลผู้ป่วย','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000007842868018','2500','แถบการรับบริการ','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000007773538108','2600','แถบบันทึกอาการเจ็บป่วย','8','เวชสถิติ','1','0',' ');
insert into  f_gui_action_authen values ('000000000420976412','2700','แถบรายการตรวจรักษา','8','เวชสถิติ','1','0',' ');
insert into  f_gui_action_authen values ('000000007211432356','2800','แถบการวินิจฉัย','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000000529822585','2900','แถบการเงิน','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000004602511343','3000','แถบแลบ','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000004036842532','3100','แถบรังสี','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000007460829264','5100','ผู้ดูแลระบบ','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000005709265899','5101','ตั้งค่าของระบบ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000000075861806','5102','คนไข้ที่ถูกล็อก','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000007499158679','5103','สิทธิการใช้งาน','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000005861055624','5200','รายชื่อจุดบริการ','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000002486895213','5201','รายชื่อวอร์ด','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000007862208599','5202','รายชื่อจุดบริการ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000000710999528','5203','รายชื่อคลินิก','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000009870751477','5204','สถานพยาบาล','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000007997881921','5205','กำหนดคิว','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000000703618057','5300','รายการกลุ่ม','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000009684770682','5301','รายการ Oder','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000002277831422','5302','รายการใบเสร็จ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000008203445709','5400','รายการตรวจรักษา','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000005165229405','5401','รายการตรวจรักษา','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000001167904112','5402','รายการอัตโนมัติ','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000000558146060','5403','อาการที่พบบ่อย','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000005598680622','5404','รายการยาชุด','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000004863893350','5405','Dx ที่พบบ่อย','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000003839383102','5406','ด้านการฉาย X-ray','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000007484306510','5407','ท่าการฉาย X-ray','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000008602441955','5408','ฟิล์มสำหรับ X-ray','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000006392437137','5500','รายละเอียดยา','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000003352785150','5501','ช่วงเวลาที่ใช้ยา','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000002713978128','5502','วิธีการใช้ยา','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000001237999193','5503','หน่วยยา','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000000134687679','5600','รายการ ICD','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000001245685505','5601','รายการ ICD-10','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000008221721992','5602','รายการ ICD-9','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000005863548293','5603','รายการโรคเรื้อรัง','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000008957394496','5604','รายการกลุ่ม ICD-10','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000005791892319','5700','การชำระเงิน','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000001385721619','5701','ผู้ชำระเงิน','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000006174570964','5702','รายการส่วนลด','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000001368716941','5703','สิทธิการรักษา','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000006242138651','5800','รายการอื่นๆ','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000008300501556','5801','ผู้ใช้งาน','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000002475411420','5802','แสดงเลข Sequence','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000002148947997','5803','สถานพยาบาลที่ติดตั้ง','8','เวชสถิติ','1','0',' ');
insert into  f_gui_action_authen values ('000000000580480904','5900','รายงาน','8','เวชสถิติ','0','1',' ');
insert into  f_gui_action_authen values ('000000006459011803','5901','รายงาน SQL','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000006305341073','5902','รายการคำสั่ง SQL','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000002687670000','5903','รายงาน SQL ตามวันที่','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000000917809752','5904','รายงานคำสั่ง SQL ตามวันที่','8','เวชสถิติ','1','1',' ');
insert into  f_gui_action_authen values ('000000000332209622','0100','เมนูระบบ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000006904767264','0101','ออกจากระบบ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000005302547718','0200','เมนูมุมมอง','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000009950509470','0201','รีเฟรช','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000005571549701','0202','ปลดล็อก','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000007720130773','0203','ค้นหาผู้ป่วย','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000000668482608','0300','เมนูแถบ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000006531447267','0400','เมนูเครื่องมือ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000007569542360','0401','การนัดหมาย','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000007171433000','0402','รายการนัดทั้งหมด','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000005151866033','0403','ประวัติการรับบริการ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000008332112118','0404','ประวัติรายการสั่งตรวจ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000007935820291','0405','ประวัติการคิดเงิน','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000004244220439','0406','จับคู่คำนำหน้ากับเพศ','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000005323189152','0407','ลำดับเลข Sequence ล่าสุด','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000004818865552','0408','การ refer ผู้ป่วย','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000008080912167','0409','รายงานต่าง ๆ ','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000006118244644','0500','เมนูผู้ป่วยทั่วไป','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000000212274361','0501','กองทุนยา/การจำหน่ายยา','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000004322906971','0502','ค้างบันทึก','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000002547185026','0503','ยกเลิกการเข้ารับบริการ','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000002007908678','0504','ข้อมูลการเกิดอุบัติเหตุ','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000007973918209','0505','ข้อมูลการตาย','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000004852694229','0506','ข้อมูลโรคเรื้อรัง','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000005986191767','0507','ข้อมูลโรคเฝ้าระวัง','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000004665002091','0600','เมนูผู้ป่วยใน','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000002547745444','0601','Admit','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000009479204768','0602','ย้อนกลับการ Admit','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000006753915222','0603','ฝากนอน','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000008582110833','0604','ยกเลิกการฝากนอน','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000007624590144','0605','ส่งผู้ป่วยกลับวอร์ด','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000003618560429','0606','ประวัติการสั่งยาต่อเนื่อง','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000006980175180','0700','เมนูการสั่งตรวจ','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000001835488662','0701','การคืนยา','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000009772739226','0702','สั่งชุดรายการตรวจรักษา','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000003343178977','0703','สั่งรายการเหมือนครั้งที่แล้ว','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000003665723657','0704','สั่งรายการเหมือนวันที่แล้ว','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000006719060475','0705','สั่งยาต่อเนื่อง','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000009930882194','0800','เมนูรายการพิมพ์','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000000971762425','0801','ใบรายการตรวจรักษา','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000007870322834','0802','ใบ Summary','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000002846620374','0803','ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000002640636499','0804','ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000006620060191','0805','ใบสรุปค่าใช้จ่ายตามรายการ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000009377363235','0806','รายการยาที่เลือก','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000005237177232','0807','ใบ OPD Card','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000005370022329','0808','พิมพ์สติ๊กเกอร์ยา','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000002138501251','0809','ใบ Index','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000002355350676','0900','เมนูการย้อนกลับ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000009048795386','0901','ย้อนกลับทางการเงิน','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000006070284698','0902','ย้อนกลับทางการแพทย์','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000006919417496','1000','เมนูตัวช่วย','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000003483847150','1001','การเชื่อมต่อฐานข้อมูล','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000002089160171','1002','ข้อมูลโปรแกรม','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000005711975335','1003','กำ้หนด path รูปแบบอักษร','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000003425727703','1004','กำ้หนด path งานพิมพ์','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000007771226601','2100','แถบรายชื่อ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000006006499327','2200','แถบรายชื่อตามจุดบริการ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000003962214603','2300','แถบรายชื่อในวอร์ด','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000003974590081','2400','แถบข้อมูลผู้ป่วย','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000008863861863','2500','แถบการรับบริการ','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000007152371932','2600','แถบบันทึกอาการเจ็บป่วย','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000000393582768','2700','แถบรายการตรวจรักษา','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000009303672434','2800','แถบการวินิจฉัย','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000000031136673','2900','แถบการเงิน','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000008508233766','3000','แถบแลบ','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000000656092681','3100','แถบรังสี','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000003754329321','5100','ผู้ดูแลระบบ','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000009207928010','5101','ตั้งค่าของระบบ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000004780576483','5102','คนไข้ที่ถูกล็อก','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000006897218956','5103','สิทธิการใช้งาน','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000001525117200','5200','รายชื่อจุดบริการ','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000006485983634','5201','รายชื่อวอร์ด','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000003794827188','5202','รายชื่อจุดบริการ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000004380972671','5203','รายชื่อคลินิก','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000003380716634','5204','สถานพยาบาล','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000005267875845','5205','กำหนดคิว','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000008656089963','5300','รายการกลุ่ม','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000001735525981','5301','รายการ Oder','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000003445474171','5302','รายการใบเสร็จ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000008931980393','5400','รายการตรวจรักษา','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000006795193267','5401','รายการตรวจรักษา','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000000549905716','5402','รายการอัตโนมัติ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000001267416941','5403','อาการที่พบบ่อย','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000003304197867','5404','รายการยาชุด','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000003981410829','5405','Dx ที่พบบ่อย','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000001143792118','5406','ด้านการฉาย X-ray','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000005207730237','5407','ท่าการฉาย X-ray','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000001491899136','5408','ฟิล์มสำหรับ X-ray','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000003658597214','5500','รายละเอียดยา','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000005250474945','5501','ช่วงเวลาที่ใช้ยา','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000009392333281','5502','วิธีการใช้ยา','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000001707837408','5503','หน่วยยา','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000004549388858','5600','รายการ ICD','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000003081531807','5601','รายการ ICD-10','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000007956325514','5602','รายการ ICD-9','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000000596543285','5603','รายการโรคเรื้อรัง','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000002372493587','5604','รายการกลุ่ม ICD-10','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000001782813892','5700','การชำระเงิน','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000007118721127','5701','ผู้ชำระเงิน','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000004272979941','5702','รายการส่วนลด','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000004730997487','5703','สิทธิการรักษา','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000004644833971','5800','รายการอื่นๆ','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000007744021596','5801','ผู้ใช้งาน','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000006792998262','5802','แสดงเลข Sequence','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000001754998197','5803','สถานพยาบาลที่ติดตั้ง','7','แคชเชียร์','1','0',' ');
insert into  f_gui_action_authen values ('000000005596674272','5900','รายงาน','7','แคชเชียร์','0','1',' ');
insert into  f_gui_action_authen values ('000000008478829073','5901','รายงาน SQL','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000001097407582','5902','รายการคำสั่ง SQL','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000008919770335','5903','รายงาน SQL ตามวันที่','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000001491039347','5904','รายงานคำสั่ง SQL ตามวันที่','7','แคชเชียร์','1','1',' ');
insert into  f_gui_action_authen values ('000000002295828515','0100','เมนูระบบ','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000000606229406','0101','ออกจากระบบ','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000002611056222','0200','เมนูมุมมอง','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000005479103027','0201','รีเฟรช','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000007334125421','0202','ปลดล็อก','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000000586980054','0203','ค้นหาผู้ป่วย','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000003471966742','0300','เมนูแถบ','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000008377307601','0400','เมนูเครื่องมือ','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000006294047371','0401','การนัดหมาย','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000007180585888','0402','รายการนัดทั้งหมด','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000007341368660','0403','ประวัติการรับบริการ','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000001344291866','0404','ประวัติรายการสั่งตรวจ','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000007326174406','0405','ประวัติการคิดเงิน','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000008540802365','0406','จับคู่คำนำหน้ากับเพศ','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000008681524859','0407','ลำดับเลข Sequence ล่าสุด','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000002414641255','0408','การ refer ผู้ป่วย','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000003348494996','0409','รายงานต่าง ๆ ','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000006927775034','0500','เมนูผู้ป่วยทั่วไป','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000006793140783','0501','กองทุนยา/การจำหน่ายยา','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000001477164773','0502','ค้างบันทึก','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000006032224824','0503','ยกเลิกการเข้ารับบริการ','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000002793645210','0504','ข้อมูลการเกิดอุบัติเหตุ','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000000828870498','0505','ข้อมูลการตาย','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000003138924706','0506','ข้อมูลโรคเรื้อรัง','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000005315972445','0507','ข้อมูลโรคเฝ้าระวัง','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000001907868225','0600','เมนูผู้ป่วยใน','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000005641713459','0601','Admit','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000004582258750','0602','ย้อนกลับการ Admit','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000005239571278','0603','ฝากนอน','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000004392178594','0604','ยกเลิกการฝากนอน','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000006842637649','0605','ส่งผู้ป่วยกลับวอร์ด','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000006000542030','0606','ประวัติการสั่งยาต่อเนื่อง','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000002133036759','0700','เมนูการสั่งตรวจ','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000000169902083','0701','การคืนยา','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000000092141531','0702','สั่งชุดรายการตรวจรักษา','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000004489469916','0703','สั่งรายการเหมือนครั้งที่แล้ว','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000001752755197','0704','สั่งรายการเหมือนวันที่แล้ว','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000008781580123','0705','สั่งยาต่อเนื่อง','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000006613369838','0800','เมนูรายการพิมพ์','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000008374136695','0801','ใบรายการตรวจรักษา','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000007826143229','0802','ใบ Summary','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000002778636656','0803','ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000004189781084','0804','ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000007868219225','0805','ใบสรุปค่าใช้จ่ายตามรายการ','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000005647663112','0806','รายการยาที่เลือก','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000002979084208','0807','ใบ OPD Card','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000000498258324','0808','พิมพ์สติ๊กเกอร์ยา','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000009690076796','0809','ใบ Index','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000000863217227','0900','เมนูการย้อนกลับ','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000000754033041','0901','ย้อนกลับทางการเงิน','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000003528946880','0902','ย้อนกลับทางการแพทย์','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000007801604510','1000','เมนูตัวช่วย','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000005386586713','1001','การเชื่อมต่อฐานข้อมูล','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000007211029079','1002','ข้อมูลโปรแกรม','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000002055986084','1003','กำ้หนด path รูปแบบอักษร','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000009789184086','1004','กำ้หนด path งานพิมพ์','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000009510719045','2100','แถบรายชื่อ','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000001650014309','2200','แถบรายชื่อตามจุดบริการ','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000007648705603','2300','แถบรายชื่อในวอร์ด','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000008572099895','2400','แถบข้อมูลผู้ป่วย','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000001708309557','2500','แถบการรับบริการ','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000001989970459','2600','แถบบันทึกอาการเจ็บป่วย','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000008550733188','2700','แถบรายการตรวจรักษา','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000002102295131','2800','แถบการวินิจฉัย','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000005360265638','2900','แถบการเงิน','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000007999069130','3000','แถบแลบ','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000004904862682','3100','แถบรังสี','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000009510367110','5100','ผู้ดูแลระบบ','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000004707277872','5101','ตั้งค่าของระบบ','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000004108419029','5102','คนไข้ที่ถูกล็อก','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000004264413513','5103','สิทธิการใช้งาน','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000000756211275','5200','รายชื่อจุดบริการ','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000000688868626','5201','รายชื่อวอร์ด','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000002349265449','5202','รายชื่อจุดบริการ','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000006107210645','5203','รายชื่อคลินิก','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000008258737451','5204','สถานพยาบาล','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000001015923080','5205','กำหนดคิว','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000007525766874','5300','รายการกลุ่ม','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000004156198306','5301','รายการ Oder','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000003207605088','5302','รายการใบเสร็จ','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000007816854903','5400','รายการตรวจรักษา','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000006120230571','5401','รายการตรวจรักษา','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000005711096255','5402','รายการอัตโนมัติ','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000006300930626','5403','อาการที่พบบ่อย','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000000504194314','5404','รายการยาชุด','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000007624965627','5405','Dx ที่พบบ่อย','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000007908394531','5406','ด้านการฉาย X-ray','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000002506442140','5407','ท่าการฉาย X-ray','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000001127528832','5408','ฟิล์มสำหรับ X-ray','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000002004666859','5500','รายละเอียดยา','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000001852374452','5501','ช่วงเวลาที่ใช้ยา','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000002109570804','5502','วิธีการใช้ยา','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000004410367268','5503','หน่วยยา','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000009815517565','5600','รายการ ICD','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000006897737026','5601','รายการ ICD-10','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000005790481517','5602','รายการ ICD-9','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000004912640673','5603','รายการโรคเรื้อรัง','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000002141959181','5604','รายการกลุ่ม ICD-10','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000009014804638','5700','การชำระเงิน','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000009763063790','5701','ผู้ชำระเงิน','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000009317188342','5702','รายการส่วนลด','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000005959341988','5703','สิทธิการรักษา','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000009131158289','5800','รายการอื่นๆ','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000008112419531','5801','ผู้ใช้งาน','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000002707724308','5802','แสดงเลข Sequence','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000006790441597','5803','สถานพยาบาลที่ติดตั้ง','3','แพทย์','1','0',' ');
insert into  f_gui_action_authen values ('000000009618959495','5900','รายงาน','3','แพทย์','0','1',' ');
insert into  f_gui_action_authen values ('000000003383396011','5901','รายงาน SQL','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000003887418852','5902','รายการคำสั่ง SQL','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000004596081937','5903','รายงาน SQL ตามวันที่','3','แพทย์','1','1',' ');
insert into  f_gui_action_authen values ('000000009685099032','5904','รายงานคำสั่ง SQL ตามวันที่','3','แพทย์','1','1',' ');


INSERT INTO s_version VALUES ('9701000000006', '16', 'Hospital OS, Community Edition', '3.06.0948', '3.06.0948', '2548-09-09 12:00:00');
--------------------------------------------------------------------------------------------------------3.06


-- ไม่ต้อง update ตาราง patient เพราะว่ามันมีรหัสซ้ำกันไม่รู้ว่า 03 นี้เป็น ป.6 หรือ ม.ต้น กันแน่แต่เปลี่ยนรหัสให้ ม.ต้น เป็น 13 แล้ว
update f_patient_education_type set f_patient_education_type_id = '13' where  patient_education_type_description = 'มัธยมศึกษาตอนต้น';

-- tableid  "907"
CREATE TABLE f_appointment_status
(
  f_appointment_status_id varchar(255) NOT NULL,
  name varchar(255),
  note varchar(255),
  CONSTRAINT f_appointment_status_pkey PRIMARY KEY (f_appointment_status_id)
)  ;

--insert into  f_appointment_status (f_appointment_status_id, name, note) FROM stdin;
insert into  f_appointment_status values ('0','รอการนัด','เมื่อมีการนัดหมายเกิดขึ้น');
insert into  f_appointment_status values  ('1','มาตามนัด','เมื่อมีการ visit จากการนัดมีเลข visit_id');
insert into  f_appointment_status values  ('2','ผิดนัด','เมื่อถึงวันที่นัดแล้วไม่มีการ visit จากการนัด');
insert into  f_appointment_status values  ('3','ยกเลิกนัด','เมื่อผู้นัดเห็นว่าการนัดนี้ผิดปกติใช้ได้สำหรับผู้ทำการนัดเท่านั้น');
--\.

--เพิ่ม Column สถานะการนัด
ALTER TABLE t_patient_appointment ADD COLUMN status varchar (255) Default '0';
ALTER TABLE b_contract_plans ADD COLUMN sort_index varchar (255) Default '99';



INSERT INTO s_version VALUES ('9701000000007', '17', 'Hospital OS, Community Edition', '3.07.0948', '3.07.0948', '2548-09-11 12:00:00');
--------------------------------------------------------------------------------------------------------3.07

drop table f_patient_education_type;
CREATE TABLE f_patient_education_type (
    f_patient_education_type_id character varying(255) NOT NULL,
    patient_education_type_description character varying(255) NOT NULL,
    patient_education_type_sort_index character varying(255)
) ;

-- insert into  f_patient_education_type (f_patient_education_type_id, patient_education_type_description, patient_education_type_sort_index) FROM stdin;
insert into  f_patient_education_type values ('11','ไม่ระบุ','00');
insert into  f_patient_education_type values ('01','ไม่เรียน','01');
insert into  f_patient_education_type values ('02','ประถมศึกษาปีที่ 4','02');
insert into  f_patient_education_type values ('03','ประถมศึกษาปีที่ 6','03');
insert into  f_patient_education_type values ('13','มัธยมศึกษาตอนต้น','04');
insert into  f_patient_education_type values ('04','มัธยมศึกษาตอนปลาย','05');
insert into  f_patient_education_type values ('06','ปวช.','06');
insert into  f_patient_education_type values ('07','ปวส.','07');
insert into  f_patient_education_type values ('08','อนุปริญญา','08');
insert into  f_patient_education_type values ('09','ปริญญาตรี','09');
insert into  f_patient_education_type values ('10','สูงกว่าปริญญาตรี','10');
--\.


ALTER TABLE t_visit_queue_lab ADD COLUMN visit_queue_lab_remain varchar (255) Default '0';

INSERT INTO s_version VALUES ('9701000000008', '18', 'Hospital OS, Community Edition', '3.08.0948', '3.08.0948', '2548-09-22 12:00:00');
--------------------------------------------------------------------------------------------------------3.07


 insert into  f_gui_action_authen values ('156','0100','เมนูระบบ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('157','0101','ออกจากระบบ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('158','0200','เมนูมุมมอง','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('159','0201','รีเฟรช','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('160','0202','ปลดล็อก','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('161','0203','ค้นหาผู้ป่วย','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('162','0300','เมนูแถบ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('163','0400','เมนูเครื่องมือ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('164','0401','การนัดหมาย','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('165','0402','รายการนัดทั้งหมด','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('166','0403','ประวัติการรับบริการ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('167','0404','ประวัติรายการสั่งตรวจ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('168','0405','ประวัติการคิดเงิน','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('169','0406','จับคู่คำนำหน้ากับเพศ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('170','0407','ลำดับเลข Sequence ล่าสุด','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('171','0408','การ refer ผู้ป่วย','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('172','0409','รายงานต่าง ๆ ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('173','0500','เมนูผู้ป่วยทั่วไป','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('174','0501','กองทุนยา/การจำหน่ายยา','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('175','0502','ค้างบันทึก','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('176','0503','ยกเลิกการเข้ารับบริการ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('177','0504','ข้อมูลการเกิดอุบัติเหตุ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('178','0505','ข้อมูลการตาย','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('179','0506','ข้อมูลโรคเรื้อรัง','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('180','0507','ข้อมูลโรคเฝ้าระวัง','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('181','0600','เมนูผู้ป่วยใน','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('182','0601','Admit','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('183','0602','ย้อนกลับการ Admit','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('184','0603','ฝากนอน','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('185','0604','ยกเลิกการฝากนอน','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('186','0605','ส่งผู้ป่วยกลับวอร์ด','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('187','0606','ประวัติการสั่งยาต่อเนื่อง','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('188','0700','เมนูการสั่งตรวจ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('189','0701','การคืนยา','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('190','0702','สั่งชุดรายการตรวจรักษา','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('191','0703','สั่งรายการเหมือนครั้งที่แล้ว','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('192','0704','สั่งรายการเหมือนวันที่แล้ว','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('193','0705','สั่งยาต่อเนื่อง','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('194','0800','เมนูรายการพิมพ์','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('195','0801','ใบรายการตรวจรักษา','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('196','0802','ใบ Summary','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('197','0803','ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('198','0804','ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('199','0805','ใบสรุปค่าใช้จ่ายตามรายการ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('200','0806','รายการยาที่เลือก','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('201','0807','ใบ OPD Card','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('202','0808','พิมพ์สติ๊กเกอร์ยา','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('203','0809','ใบ Index','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('204','0900','เมนูการย้อนกลับ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('205','0901','ย้อนกลับทางการเงิน','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('206','0902','ย้อนกลับทางการแพทย์','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('207','1000','เมนูตัวช่วย','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('208','1001','การเชื่อมต่อฐานข้อมูล','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('209','1002','ข้อมูลโปรแกรม','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('210','1003','กำ้หนด path รูปแบบอักษร','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('211','1004','กำ้หนด path งานพิมพ์','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('212','2100','แถบรายชื่อ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('213','2200','แถบรายชื่อตามจุดบริการ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('214','2300','แถบรายชื่อในวอร์ด','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('215','2400','แถบข้อมูลผู้ป่วย','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('216','2500','แถบการรับบริการ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('217','2600','แถบบันทึกอาการเจ็บป่วย','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('218','2700','แถบรายการตรวจรักษา','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('219','2800','แถบการวินิจฉัย','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('220','2900','แถบการเงิน','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('221','3000','แถบแลบ','12','งานส่งเสริม','0','1',' ');
 insert into  f_gui_action_authen values ('222','3100','แถบรังสี','12','งานส่งเสริม','0','1',' ');
 insert into  f_gui_action_authen values ('223','5100','ผู้ดูแลระบบ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('224','5101','ตั้งค่าของระบบ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('225','5102','คนไข้ที่ถูกล็อก','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('226','5103','สิทธิการใช้งาน','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('227','5200','รายชื่อจุดบริการ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('228','5201','รายชื่อวอร์ด','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('229','5202','รายชื่อจุดบริการ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('230','5203','รายชื่อคลินิก','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('231','5204','สถานพยาบาล','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('232','5205','กำหนดคิว','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('233','5300','รายการกลุ่ม','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('234','5301','รายการ Oder','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('235','5302','รายการใบเสร็จ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('236','5400','รายการตรวจรักษา','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('237','5401','รายการตรวจรักษา','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('238','5402','รายการอัตโนมัติ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('239','5403','อาการที่พบบ่อย','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('240','5404','รายการยาชุด','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('241','5405','Dx ที่พบบ่อย','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('242','5406','ด้านการฉาย X-ray','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('243','5407','ท่าการฉาย X-ray','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('244','5408','ฟิล์มสำหรับ X-ray','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('245','5500','รายละเอียดยา','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('246','5501','ช่วงเวลาที่ใช้ยา','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('247','5502','วิธีการใช้ยา','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('248','5503','หน่วยยา','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('249','5600','รายการ ICD','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('250','5601','รายการ ICD-10','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('251','5602','รายการ ICD-9','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('252','5603','รายการโรคเรื้อรัง','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('253','5604','รายการกลุ่ม ICD-10','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('254','5700','การชำระเงิน','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('255','5701','ผู้ชำระเงิน','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('256','5702','รายการส่วนลด','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('257','5703','สิทธิการรักษา','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('258','5800','รายการอื่นๆ','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('259','5801','ผู้ใช้งาน','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('260','5802','แสดงเลข Sequence','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('261','5803','สถานพยาบาลที่ติดตั้ง','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('262','5900','รายงาน','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('263','5901','รายงาน SQL','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('264','5902','รายการคำสั่ง SQL','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('265','5903','รายงาน SQL ตามวันที่','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('266','5904','รายงานคำสั่ง SQL ตามวันที่','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('267','0410','Lab Refer Out','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('268','0411','Lab Refer In','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('269','0810','พิมพ์ใบ Index Xray','12','งานส่งเสริม','1','1',' ');
 insert into  f_gui_action_authen values ('270','0811','พิมพ์ผลแลบ','12','งานส่งเสริม','1','1','');

 ALTER TABLE t_visit ADD COLUMN patient_age varchar (255) Default '';
ALTER TABLE t_visit_queue_transfer ADD COLUMN lab_status varchar(255)  DEFAULT  '0';

--เพิ่ม Column ชื่อโรคภาษาไทยใน Dxtemplate Author by sumo 22/09/2548
ALTER TABLE b_template_dx ADD COLUMN template_dx_thaidescription varchar (255) default '';

INSERT INTO s_version VALUES ('9701000000009', '19', 'Hospital OS, Community Edition', '3.09.0948', '3.09.0948', '2548-09-25 12:00:00');
--------------------------------------------------------------------------------------------------------3.10

--เพิ่ม Column หมายเลข visit_id กรณีที่ผู้ป่วยมาตามนัด henbe 27/09/2548
ALTER TABLE t_patient_appointment ADD COLUMN vn varchar (255) Default '';


--เพิ่มตารางในการเก็บชนิดของผลแลป
-- tableid  "901"
CREATE TABLE b_lab_result_type
(
   b_lab_result_id varchar(255), 
   b_lab_result_number varchar(255), 
   b_result_name varchar(255), 
  CONSTRAINT b_lab_result_type_pkey PRIMARY KEY (b_lab_result_id)
) ;

--เพิ่มตารางในการเก็บรายละเอียดของชนิดผลแลป
-- tableid  "902"
CREATE TABLE b_lab_result_detial
(
  b_lab_result_detail_id varchar(255) NOT NULL,
  b_lab_result_id varchar(255),
  b_lab_result_detail_name varchar(255),
  b_lab_result_value varchar(255),
  CONSTRAINT b_lab_result_detial_pkey PRIMARY KEY (b_lab_result_detail_id)
) 
WITH  OIDS;

--เพิ่ม Column ในตาราง b_item_lab_result
ALTER TABLE b_item_lab_result ADD COLUMN b_lab_result_type varchar(255) default '';
ALTER TABLE b_item_lab_result ADD COLUMN result_max varchar(255) default '';
ALTER TABLE b_item_lab_result ADD COLUMN result_min varchar(255) default '';
ALTER TABLE b_item_lab_result ADD COLUMN b_result_id varchar(255) default '';

-- tableid  "903" เป็นรายชื่อสถานพยาบาลในเขต
CREATE TABLE r_hospital_incup (
    hospital_incup_id character varying(255) NOT NULL,
    hospital_incup_code character varying(255),
    hospital_incup_name character varying(255)
);

--เพิ่ม Column  order_status  t_order_drug เพื่อเก็บสถานะของผู้ยืนยัน ดำเนินการ จ่าย
-- เหตุที่ต้อง comment เพราะว่ามันนานมาก ทำให้การ update ต้องรอนาน เลยแก้ปัญหาโดยการ
-- ให้โปรแกรมเช็คว่าไม่เท่ากับ 0 เป็นรายการที่ใช้งานแทน
-- update t_order_drug set active = '1';

ALTER TABLE t_order_drug ADD COLUMN order_status varchar(255) default '';

--ปรับปรุงฐานข้อมูลให้ค่า พิมพ์เป็นค่าปกติ
update b_item set item_printable = '0' where item_printable='null';
update b_item_drug set item_drug_printable = '0' where item_drug_printable='null';

INSERT INTO s_version VALUES ('9701000000010', '20', 'Hospital OS, Community Edition', '3.10.0948', '3.10.0948', '2548-10-02 12:00:00');
--------ปิดเพื่อ test เอง------------------------------------------------------------------------------------------------3.10
--ปรับปรุงค่า type ของ icd_type ในตาราง ให้ถูกต้อง

update b_template_dx set template_dx_icd_type = '1' where char_length(b_icd10_number)=5;
update b_template_dx set template_dx_icd_type = '2' where char_length(b_icd10_number)=3;
delete from b_template_dx where b_icd10_number = 'null';

insert into  f_gui_action_authen values ('271','0812','พิมพ์ป้ายชื่อผู้ป่วยใน','10','ONE STOP SERVICE','0','0',' ');
insert into  f_gui_action_authen values ('272','0812','พิมพ์ป้ายชื่อผู้ป่วยใน','4','LAB','0','0',' ');
insert into  f_gui_action_authen values ('273','0812','พิมพ์ป้ายชื่อผู้ป่วยใน','9','ADMIN','0','0',' ');
insert into  f_gui_action_authen values ('274','0812','พิมพ์ป้ายชื่อผู้ป่วยใน','5','X-RAY','0','0',' ');
insert into  f_gui_action_authen values ('275','0812','พิมพ์ป้ายชื่อผู้ป่วยใน','13','งานผู้ป่วยใน','1','1',' ');
insert into  f_gui_action_authen values ('276','0812','พิมพ์ป้ายชื่อผู้ป่วยใน','2','พยาบาล','0','0',' ');
insert into  f_gui_action_authen values ('277','0812','พิมพ์ป้ายชื่อผู้ป่วยใน','6','เภสัชกร','0','0',' ');
insert into  f_gui_action_authen values ('278','0812','พิมพ์ป้ายชื่อผู้ป่วยใน','1','เวชระเบียน','0','0',' ');
insert into  f_gui_action_authen values ('279','0812','พิมพ์ป้ายชื่อผู้ป่วยใน','8','เวชสถิติ','0','0',' ');
insert into  f_gui_action_authen values ('280','0812','พิมพ์ป้ายชื่อผู้ป่วยใน','7','แคชเชียร์','0','0',' ');
insert into  f_gui_action_authen values ('281','0812','พิมพ์ป้ายชื่อผู้ป่วยใน','3','แพทย์','0','0',' ');
insert into  f_gui_action_authen values ('282','0812','พิมพ์ป้ายชื่อผู้ป่วยใน','12','งานส่งเสริม','0','0',' ');

update  f_group_rp505  set group_rp505_number = trim(group_rp505_number);
update  f_group_rp504  set group_rp504_number = trim(group_rp504_number);
update  f_group_rp506  set group_rp506_number = trim(group_rp506_number);

  insert into  f_gui_action_authen values ('283','2701','ปุ่มคิดเงิน','1','เวชระเบียน','0','0',' ');
  insert into  f_gui_action_authen values ('284','2701','ปุ่มคิดเงิน','2','พยาบาล','0','0',' ');
  insert into  f_gui_action_authen values ('285','2701','ปุ่มคิดเงิน','3','แพทย์','0','0',' ');
  insert into  f_gui_action_authen values ('286','2701','ปุ่มคิดเงิน','4','LAB','0','0',' ');
  insert into  f_gui_action_authen values ('287','2701','ปุ่มคิดเงิน','5','X-RAY','0','0',' ');
  insert into  f_gui_action_authen values ('288','2701','ปุ่มคิดเงิน','6','เภสัชกร','1','0',' ');
  insert into  f_gui_action_authen values ('289','2701','ปุ่มคิดเงิน','7','แคชเชียร์','1','0',' ');
  insert into  f_gui_action_authen values ('290','2701','ปุ่มคิดเงิน','8','เวชสถิติ','0','0',' ');
  insert into  f_gui_action_authen values ('291','2701','ปุ่มคิดเงิน','9','ADMIN','0','0',' ');
  insert into  f_gui_action_authen values ('292','2701','ปุ่มคิดเงิน','10','ONE STOP SERVICE','1','0',' ');
  insert into  f_gui_action_authen values ('293','2701','ปุ่มคิดเงิน','12','งานส่งเสริม','1','0',' ');
  insert into  f_gui_action_authen values ('294','2701','ปุ่มคิดเงิน','13','งานผู้ป่วยใน','0','0',' ');
  insert into  f_gui_action_authen values ('295','2702','ปุ่มยืนยัน','1','เวชระเบียน','0','0',' ');
  insert into  f_gui_action_authen values ('296','2702','ปุ่มยืนยัน','2','พยาบาล','1','0',' ');
  insert into  f_gui_action_authen values ('297','2702','ปุ่มยืนยัน','3','แพทย์','1','0',' ');
  insert into  f_gui_action_authen values ('298','2702','ปุ่มยืนยัน','4','LAB','1','0',' ');
  insert into  f_gui_action_authen values ('299','2702','ปุ่มยืนยัน','5','X-RAY','1','0',' ');
  insert into  f_gui_action_authen values ('300','2702','ปุ่มยืนยัน','6','เภสัชกร','1','0',' ');
  insert into  f_gui_action_authen values ('301','2702','ปุ่มยืนยัน','7','แคชเชียร์','1','0',' ');
  insert into  f_gui_action_authen values ('302','2702','ปุ่มยืนยัน','8','เวชสถิติ','0','0',' ');
  insert into  f_gui_action_authen values ('303','2702','ปุ่มยืนยัน','9','ADMIN','0','0',' ');
  insert into  f_gui_action_authen values ('304','2702','ปุ่มยืนยัน','10','ONE STOP SERVICE','1','0',' ');
  insert into  f_gui_action_authen values ('305','2702','ปุ่มยืนยัน','12','งานส่งเสริม','1','0',' ');
  insert into  f_gui_action_authen values ('306','2702','ปุ่มยืนยัน','13','งานผู้ป่วยใน','1','0',' ');
  insert into  f_gui_action_authen values ('307','2703','ปุ่มดำเนินการ','1','เวชระเบียน','0','0',' ');
  insert into  f_gui_action_authen values ('308','2703','ปุ่มดำเนินการ','2','พยาบาล','1','0',' ');
  insert into  f_gui_action_authen values ('309','2703','ปุ่มดำเนินการ','3','แพทย์','1','0',' ');
  insert into  f_gui_action_authen values ('310','2703','ปุ่มดำเนินการ','4','LAB','1','0',' ');
  insert into  f_gui_action_authen values ('311','2703','ปุ่มดำเนินการ','5','X-RAY','1','0',' ');
  insert into  f_gui_action_authen values ('312','2703','ปุ่มดำเนินการ','6','เภสัชกร','1','0',' ');
  insert into  f_gui_action_authen values ('313','2703','ปุ่มดำเนินการ','7','แคชเชียร์','1','0',' ');
  insert into  f_gui_action_authen values ('314','2703','ปุ่มดำเนินการ','8','เวชสถิติ','0','0',' ');
  insert into  f_gui_action_authen values ('315','2703','ปุ่มดำเนินการ','9','ADMIN','0','0',' ');
  insert into  f_gui_action_authen values ('316','2703','ปุ่มดำเนินการ','10','ONE STOP SERVICE','1','0',' ');
  insert into  f_gui_action_authen values ('317','2703','ปุ่มดำเนินการ','12','งานส่งเสริม','1','0',' ');
  insert into  f_gui_action_authen values ('318','2703','ปุ่มดำเนินการ','13','งานผู้ป่วยใน','1','0',' ');
  insert into  f_gui_action_authen values ('319','2704','ปุ่มจ่าย','1','เวชระเบียน','0','0',' ');
  insert into  f_gui_action_authen values ('320','2704','ปุ่มจ่าย','2','พยาบาล','0','0',' ');
  insert into  f_gui_action_authen values ('321','2704','ปุ่มจ่าย','3','แพทย์','0','0',' ');
  insert into  f_gui_action_authen values ('322','2704','ปุ่มจ่าย','4','LAB','0','0',' ');
  insert into  f_gui_action_authen values ('323','2704','ปุ่มจ่าย','5','X-RAY','0','0',' ');
  insert into  f_gui_action_authen values ('324','2704','ปุ่มจ่าย','6','เภสัชกร','1','0',' ');
  insert into  f_gui_action_authen values ('325','2704','ปุ่มจ่าย','7','แคชเชียร์','0','0',' ');
  insert into  f_gui_action_authen values ('326','2704','ปุ่มจ่าย','8','เวชสถิติ','0','0',' ');
  insert into  f_gui_action_authen values ('327','2704','ปุ่มจ่าย','9','ADMIN','0','0',' ');
  insert into  f_gui_action_authen values ('328','2704','ปุ่มจ่าย','10','ONE STOP SERVICE','1','0',' ');
  insert into  f_gui_action_authen values ('329','2704','ปุ่มจ่าย','12','งานส่งเสริม','1','0',' ');
  insert into  f_gui_action_authen values ('330','2704','ปุ่มจ่าย','13','งานผู้ป่วยใน','0','0',' ');
  insert into  f_gui_action_authen values ('331','2705','ปุ่มยกเลิก','1','เวชระเบียน','0','0',' ');
  insert into  f_gui_action_authen values ('332','2705','ปุ่มยกเลิก','2','พยาบาล','1','0',' ');
  insert into  f_gui_action_authen values ('333','2705','ปุ่มยกเลิก','3','แพทย์','1','0',' ');
  insert into  f_gui_action_authen values ('334','2705','ปุ่มยกเลิก','4','LAB','0','0',' ');
  insert into  f_gui_action_authen values ('335','2705','ปุ่มยกเลิก','5','X-RAY','0','0',' ');
  insert into  f_gui_action_authen values ('336','2705','ปุ่มยกเลิก','6','เภสัชกร','1','0',' ');
  insert into  f_gui_action_authen values ('337','2705','ปุ่มยกเลิก','7','แคชเชียร์','0','0',' ');
  insert into  f_gui_action_authen values ('338','2705','ปุ่มยกเลิก','8','เวชสถิติ','0','0',' ');
  insert into  f_gui_action_authen values ('339','2705','ปุ่มยกเลิก','9','ADMIN','0','0',' ');
  insert into  f_gui_action_authen values ('340','2705','ปุ่มยกเลิก','10','ONE STOP SERVICE','1','0',' ');
  insert into  f_gui_action_authen values ('341','2705','ปุ่มยกเลิก','12','งานส่งเสริม','1','0',' ');
  insert into  f_gui_action_authen values ('342','2705','ปุ่มยกเลิก','13','งานผู้ป่วยใน','1','0',' ');
 


-- tableid  "908" เป็นรายชื่อสถานพยาบาลในเขต
-- t_patient_hn_history_other_patient_id กรณีที่มีการ merge ผู้ป่วยในฐานข้อมูล hospitalOS
-- คอยก่อนเพราะว่า sql มันเขียนผิดวะ

--CREATE TABLE t_patient_hn_history (
--    t_patient_hn_history_id character varying(255) NOT NULL,
--    t_patient_id character varying(255),
--    patient_hn_history_hn character varying(255),
--    patient_hn_history_other_patient_id character varying(255),
--    patient_hn_history_description character varying(255),
--    PRIMARY KEY (t_patient_hn_history_id);
--);

ALTER TABLE t_visit ADD COLUMN visit_pcu_service varchar(255) DEFAULT  '0';
ALTER TABLE t_visit ADD COLUMN visit_hospital_service varchar(255) DEFAULT  '0';
ALTER TABLE t_patient ADD COLUMN patient_has_home_in_pcu varchar(255) DEFAULT  '0';

ALTER TABLE t_billing_invoice ADD COLUMN billing_invoice_patient_share_ceil varchar(255) DEFAULT  '0';
ALTER TABLE t_billing_invoice_billing_subgroup ADD COLUMN billing_invoice_billing_subgroup_patient_share_ceil varchar(255)  DEFAULT  '0';
ALTER TABLE t_billing_invoice_item ADD COLUMN billing_invoice_item_patient_share_ceil varchar(255)  DEFAULT  '0';
ALTER TABLE t_billing_invoice ADD COLUMN billing_invoice_payer_share_ceil varchar(255) DEFAULT  '0';
ALTER TABLE t_billing_invoice_billing_subgroup ADD COLUMN billing_invoice_billing_subgroup_payer_share_ceil varchar(255)  DEFAULT  '0';
ALTER TABLE t_billing_invoice_item ADD COLUMN billing_invoice_item_payer_share_ceil varchar(255)  DEFAULT  '0';

update b_contract_plans set contract_plans_description = 'ทหารผ่านศึก  นอกเขตจังหวัด' where b_contract_plans_id = '2120000000032';

------เตรียมไปท้ายเหมือง-----------------------------------------------------------------------------------------3.11

INSERT INTO s_version VALUES ('9701000000011', '21', 'Hospital OS, Community Edition', '3.11.1048', '3.11.1048', '2548-10-05 12:00:00');
------เตรียมไปท้ายเหมือง-----------------------------------------------------------------------------------------3.11

update b_item_price set item_price_number = '' where item_price_number = 'null';
update b_item_price set item_price_cost = '0' where item_price_cost = 'null';
update b_item_price set item_price_cost = '0' where item_price_cost is null;
--//จำนวนยาซ้ำ
delete from b_item_drug_dose where b_item_drug_dose_id='2740000000004';

insert into f_order_status values ('6','ค้างรายงานผล');

CREATE TABLE f_lab_result_type
(
   f_lab_result_type_id varchar(255), 
   result_name varchar(255), 
    PRIMARY KEY (f_lab_result_type_id)
) ;

insert into f_lab_result_type values('0','ตัวเลขจำนวนเต็ม');
insert into f_lab_result_type values('1','ตัวเลขทศนิยม');
insert into f_lab_result_type values('2','ข้อความ 1 บรรทัด');
insert into f_lab_result_type values('3','ข้อความหลายบรรทัด');
insert into f_lab_result_type values('4','เลือกจากรายการ');

--b_lab_result_type','--------------->','f_lab_result_type_id
--b_result_id','','--------------->','b_lab_result_group_id
ALTER TABLE b_item_lab_result RENAME COLUMN b_lab_result_type TO f_lab_result_type_id;
ALTER TABLE b_item_lab_result RENAME COLUMN b_result_id TO b_lab_result_group_id;


--เพิ่มตารางในการเก็บชนิดของผลแลป
-- tableid  "901"
DROP TABLE b_lab_result_type;
CREATE TABLE b_lab_result_group
(
   b_lab_result_group_id varchar(255), 
   b_lab_result_number varchar(255), 
   b_result_name varchar(255), 
    PRIMARY KEY (b_lab_result_group_id)
) ;

ALTER TABLE b_item_lab_group ADD COLUMN item_name varchar(255) default '';
update b_item_lab_result set f_lab_result_type_id = '2';

------ท้ายเหมือง---19ตค--------------------------------------------------------------------------------------3.11
INSERT INTO s_version VALUES ('9701000000012', '22', 'Hospital OS, Community Edition', '3.12.1048', '3.12.1048', '2548-10-19 21:00:00');
------ท้ายเหมือง---19ตค--------------------------------------------------------------------------------------3.11
CREATE TABLE f_lab_status
(
   f_lab_status_id varchar(255), 
   lab_status_name varchar(255), 
    PRIMARY KEY (f_lab_status_id)
) ;

insert into f_lab_status values('0','ไม่มีแลบ');
insert into f_lab_status values('1','รอผล');
insert into f_lab_status values('2','บางส่วน');
insert into f_lab_status values('3','รายงาน');

------------------------------------------ Modify 21/10/2548-------------------------------------------
ALTER TABLE t_visit_queue_despense RENAME COLUMN vn TO visit_queue_despense_vn;
ALTER TABLE t_visit_queue_despense RENAME COLUMN service_point_name TO visit_queue_despense_service_point_name;
ALTER TABLE t_visit_queue_despense RENAME COLUMN hn TO visit_queue_despense_hn;
ALTER TABLE t_visit_queue_despense RENAME COLUMN prename TO visit_queue_despense_prename;
ALTER TABLE t_visit_queue_despense RENAME COLUMN firstname TO visit_queue_despense_firstname;
ALTER TABLE t_visit_queue_despense RENAME COLUMN lastname TO visit_queue_despense_lastname;

ALTER TABLE b_employee RENAME COLUMN default_tab TO b_employee_default_tab;

--ยังไม่ได้ทำการเพิ่มใน Object
--ยังไม่ได้ทำการเพิ่มใน Object
ALTER TABLE t_patient RENAME COLUMN patient_mobile_phone TO patient_patient_mobile_phone;
ALTER TABLE t_patient RENAME COLUMN contact_mobile_phone TO patient_contact_mobile_phone;
ALTER TABLE t_patient RENAME COLUMN language_for_print TO patient_language_for_print;

-- เพิ่ม ว่า visit ครั้งนี้เป็นการมาครั้งแรกของผู้ป่วยคนนี้ UC visitPatient()
ALTER TABLE t_visit RENAME COLUMN first_visit TO visit_first_visit;
ALTER TABLE t_visit RENAME COLUMN patient_age TO visit_patient_age;
ALTER TABLE t_visit ADD visit_lab_status_id  varchar(255) default '0';

ALTER TABLE t_order_drug RENAME COLUMN active TO order_drug_active;
ALTER TABLE t_order_drug RENAME COLUMN modifier TO order_drug_modifier;
ALTER TABLE t_order_drug RENAME COLUMN modify_datetime TO order_drug_modify_datetime;
ALTER TABLE t_order_drug RENAME COLUMN order_status TO order_drug_order_status;

ALTER TABLE t_patient_appointment RENAME COLUMN status TO patient_appointment_status;
ALTER TABLE t_patient_appointment RENAME COLUMN vn TO patient_appointment_vn;

ALTER TABLE b_contract_plans RENAME COLUMN sort_index TO contract_plans_sort_index;

ALTER TABLE t_visit_queue_transfer RENAME COLUMN lab_status TO visit_queue_transfer_lab_status;

--เพิ่ม Column ในตาราง b_item_lab_result','
ALTER TABLE b_item_lab_result RENAME COLUMN result_max TO item_lab_result_result_max;
ALTER TABLE b_item_lab_result RENAME COLUMN result_min TO item_lab_result_result_min;

ALTER TABLE b_item_lab_result RENAME COLUMN b_lab_result_group_id TO item_lab_result_group_id;
ALTER TABLE b_item_lab_group RENAME COLUMN item_name TO item_lab_group_item_name;

ALTER TABLE f_tab RENAME COLUMN description TO tab_description;

ALTER TABLE f_gui_action RENAME COLUMN gui_name TO gui_action_name;
ALTER TABLE f_gui_action RENAME COLUMN note TO gui_action_note;

ALTER TABLE f_gui_action_authen RENAME COLUMN gui_name TO gui_action_authen_gui_name;
ALTER TABLE f_gui_action_authen RENAME COLUMN authentication_name TO gui_action_authen_authentication_name;
ALTER TABLE f_gui_action_authen RENAME COLUMN is_read TO gui_action_authen_is_read;
ALTER TABLE f_gui_action_authen RENAME COLUMN is_write TO gui_action_authen_is_write;
ALTER TABLE f_gui_action_authen RENAME COLUMN note TO gui_action_authen_note;

ALTER TABLE f_appointment_status RENAME COLUMN name TO appointment_status_name;
--ยังไม่ได้แก้ object
ALTER TABLE f_appointment_status RENAME COLUMN note TO appointment_status_note;

--เอาตารางนี้ออกไปแล้ว   เปลี่ยนเป็น f_lab_result_type

--สร้างอันนี้แหละชัวร์ ยังไม่ได้สร้าง object ใหม่  
ALTER TABLE f_lab_result_type RENAME COLUMN result_name TO lab_result_type_name;

ALTER TABLE b_lab_result_group RENAME COLUMN b_lab_result_number TO lab_result_group_number;
ALTER TABLE b_lab_result_group RENAME COLUMN b_result_name TO lab_result_group_name;

------แก้ชื่อฟิลด์ที่ท้ายเหมือง---22ตค--------------------------------------------------------------------------------------3.11
INSERT INTO s_version VALUES ('9701000000013', '23', 'Hospital OS, Community Edition', '3.13.1048', '3.13.1048', '2548-10-21 22:25:00');
------แก้ชื่อฟิลด์ที่ท้ายเหมือง---22ตค--------------------------------------------------------------------------------------3.11

-- การเงิน มองเห็นปุ่มยกเลิก
update f_gui_action_authen set gui_action_authen_is_read = '1' where f_gui_action_authen_id = '337';

-- admin มองเห็น sequence ได้แต่เพียงผู้เดียว
update f_gui_action_authen set gui_action_authen_is_read = '0' where f_gui_action_id = '5802';
update f_gui_action_authen set gui_action_authen_is_read = '1' where f_gui_action_authen_id = '000000006152839270';

update b_sequence_data set sequence_data_pattern = '4800000' where b_sequence_data_id ='rn';

update f_service_group set service_group_description  = 'คลินิกใช้การบันทึกรหัส ICD-10, ICD-9 ' where f_service_group_id  = '7' ;

delete from b_template_dx where template_dx_description ilike 'aids';
------------sp7b----------------------------------
update f_gui_action_authen set gui_action_authen_is_read = '0' where f_gui_action_authen_id = '5802';

update t_visit set visit_hospital_service = '1';

------แก้ชื่อฟิลด์ที่ท้ายเหมือง---22ตค--------------------------------------------------------------------------------------3.11
INSERT INTO s_version VALUES ('9701000000014', '24', 'Hospital OS, Community Edition', '3.01.1148', '3.13.1048', '2548-11-08 22:25:00');
------แก้ชื่อฟิลด์ที่ท้ายเหมือง---22ตค--------------------------------------------------------------------------------------3.11
