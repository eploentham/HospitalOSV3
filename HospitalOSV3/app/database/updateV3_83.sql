create table r_rp1853_drugcompany as (select distinct company from b_nhso_drugcode24);

ALTER TABLE t_order ADD COLUMN  hight_alert varchar(255);
ALTER TABLE b_item_drug ADD COLUMN  hight_alert varchar(255);
ALTER TABLE b_item_drug ADD COLUMN  hight_alert_des text;
--รอซ่อมตาราง dose set unique key ของ b_item_id 
create table temp_item_drug as (select * from b_item_drug where b_item_id in(
select b_item_id from (
select count(b_item_id) as count,b_item_id from b_item_drug group by b_item_id) as q
where q.count>1));

delete from b_item_drug where b_item_id in (
select b_item_id from (
select count(b_item_id) as count,b_item_id from b_item_drug group by b_item_id) as q
where q.count>1);

drop index item_id_drug_key;
CREATE UNIQUE INDEX item_id_drug_key	ON b_item_drug(b_item_id);

insert into b_item_drug  (select max(b_item_drug_id ),
max(item_drug_number ),
max(item_drug_use_uom ),
max(item_drug_purch_uom ),
max(item_drug_caution ),
max(item_drug_description ),
max(item_drug_day_time ),
max(item_drug_printable ),
max(b_item_drug_instruction_id ),
max(b_item_drug_frequency_id ),
max(b_item_id ),
max(item_drug_special_prescription ),
max(item_drug_special_prescription_text ),
max(item_drug_dose ),
max(item_drug_qty  ) from temp_item_drug group by b_item_id);
--เพื่อเพิ่มค่า high alert drug,description
----------------------------------------------
create table r_rp1253_adpcode (
id varchar(255),
name varchar(255)
); 
ALTER TABLE "r_rp1253_adpcode"
	ADD CONSTRAINT "r_rp1253_adpcode_pkey"
	PRIMARY KEY ("id");
insert into r_rp1253_adpcode  values ('HC011','การฟอกโลหิต');
insert into r_rp1253_adpcode  values ('HC012','การล้างไตทางช่องท้อง');
insert into r_rp1253_adpcode  values ('HC02','การให้เคมีบำบัดและหรือรังสีรักษา ในผู้ป่วยโรคมะเร็ง');
insert into r_rp1253_adpcode  values ('HC04','ค่ายาในการรักษาผู้ป่วยเยื่อหุ้มสมองอักเสบจากเชื้อรา');
insert into r_rp1253_adpcode  values ('HC051','CMV RETINITIS 1 สัปดาห์');
insert into r_rp1253_adpcode  values ('HC052','CMV RETINITIS 2 สัปดาห์');
insert into r_rp1253_adpcode  values ('HC07','การให้สารเมทาโดนในผู้ติดสารเสพติด');
insert into r_rp1253_adpcode  values ('HC08','การรักษาด้วยออกซิเจนความกดดันสูง');
insert into r_rp1253_adpcode  values ('CABC1','ยาเคมีบำบัดพื้นฐาน');
insert into r_rp1253_adpcode  values ('CABC2','Paclit');
insert into r_rp1253_adpcode  values ('CABC31','Docetaxel 80 mg');
insert into r_rp1253_adpcode  values ('CABC32','Docetaxel 20 mg');
insert into r_rp1253_adpcode  values ('CABC4','Capecitabine');
insert into r_rp1253_adpcode  values ('CABH11','Tomoxifen 10 mg');
insert into r_rp1253_adpcode  values ('CABH12','Tomoxifen 20 mg');
insert into r_rp1253_adpcode  values ('CABH21','Megestrol acetate');
insert into r_rp1253_adpcode  values ('CABH31','Letrozole');
insert into r_rp1253_adpcode  values ('CABH32','Anatrozole 1 mg');
insert into r_rp1253_adpcode  values ('CABR1','รังสีวางแผน (Simulator)');
insert into r_rp1253_adpcode  values ('CABR2','รังสีวางแผน (CT Simulator)');
insert into r_rp1253_adpcode  values ('CABR3','รังสีรักษา');
insert into r_rp1253_adpcode  values ('OI001','Coronary angiography ( CAG )');
insert into r_rp1253_adpcode  values ('OI003','Endoscope retrograde cholangio pancreatography ( ERCP )');
insert into r_rp1253_adpcode  values ('OI004','ขยายหลอดเลือดหัวใจเส้นเดียว ( Single vessel percutaneous coronary intervention )');
insert into r_rp1253_adpcode  values ('OI005','ขยายหลอดเลือดหัวใจหลายเส้น ( Multiple vessel percutaneous coronary intervention )');
insert into r_rp1253_adpcode  values ('OI006','การตรวจสวนและจี้ไฟฟ้าหัวใจ');
insert into r_rp1253_adpcode  values ('OI007','การใส่เครื่องกระตุ้นไฟฟ้าหัวใจ ( Pace maker Implantation )');
insert into r_rp1253_adpcode  values ('OI008','การใส่เครื่องช็อกไฟฟ้าหัวใจในร่างกาย (AICD)');
insert into r_rp1253_adpcode  values ('OI009','การขยายลิ้นหัวใจด้วยลูกโป่ง (Percutaneous valvuloplasty )');
insert into r_rp1253_adpcode  values ('IP002','การให้ยารักษาการติดเชื้อราในสมอง Cryptococcal Meningitis สำหรับผู้ป่วย HIV');
insert into r_rp1253_adpcode  values ('IP003','การล้างไต/ฟอกเลือดล้างไต กรณีผู้ป่วยไตวายเฉียบพลัน(peritoneal and hemodialysis for acute renal failure)');
insert into r_rp1253_adpcode  values ('IP004','ฉีดยา Ganciclovir เข้าที่วิเทรียส กรณี CMV Retinitis ทุก 1 สัปดาห์');
insert into r_rp1253_adpcode  values ('IP005','ฉีดยา Ganciclovir เข้าที่วิเทรียส กรณี CMV Retinitis ทุก 2 สัปดาห์');
insert into r_rp1253_adpcode  values ('IP006','การรักษาด้วยออกซิเจนความกดดันสูง (Hyperbaric Oxygen Therapy)');
insert into r_rp1253_adpcode  values ('10001','ฝากครรภ์ครั้งแรก ประกันสังคมส่งเงินสมทบไม่ครบ 7 เดือน');
insert into r_rp1253_adpcode  values ('10002','ฝากครรภ์ครั้งต่อมา ประกันสังคมส่งเงินสมทบไม่ครบ 7 เดือน');
insert into r_rp1253_adpcode  values ('10003','ตรวจหลังคลอด ประกันสังคมส่งเงินสมทบไม่ครบ 7 เดือน');
insert into r_rp1253_adpcode  values ('20001','กรณี ยาฉีด (Injection)');
insert into r_rp1253_adpcode  values ('20002','กรณี ยากิน (Oral Pill)');
insert into r_rp1253_adpcode  values ('20003','กรณี การใส่ห่วง (IUD)');
insert into r_rp1253_adpcode  values ('20004','กรณี ยาฝัง (Implant)');
insert into r_rp1253_adpcode  values ('ART7RF','การขอรับค่าใช้จ่ายกรณีเหตุสมควร (มาตรา 7 )');
insert into r_rp1253_adpcode  values ('DMISHD','การเบิก SHUNT ในผู้ป่วยฟอดเลือด (HD)');
insert into r_rp1253_adpcode  values ('Q35670','โรคปากแหว่งเพดานโหว่');
insert into r_rp1253_adpcode  values ('Z34003','ประกันสังคมส่งเงินสมทบไม่ครบ 3 เดือน');
insert into r_rp1253_adpcode  values ('Z34007','ประกันสังคมส่งเงินสมทบไม่ครบ 7 เดือน');
insert into r_rp1253_adpcode  values ('Z39000','เด็กแรกเกิดเสียชีวิตก่อนลงทะเบียน');
insert into r_rp1253_adpcode  values ('Z46300','ฟันเทียมพระราชทาน');
insert into r_rp1253_adpcode  values ('Z75000','ผู้ป่วยสิทธิว่างเสียชิวิตก่อนลงทะเบียน');
insert into r_rp1253_adpcode  values ('Z75REF','ศูนย์สำรองเตียง');

ALTER TABLE b_item ADD COLUMN  r_rp1253_adpcode_id varchar(255);


--wait for final
update r_healthy_group_survey set b_health_survey_id = substring(b_health_survey_id,1,3) ||substring(b_health_survey_id,9,10);
---update report 1853----------------------------------------------------

ALTER TABLE b_item add item_unit_packing_qty varchar(255);

--ALTER TABLE b_item	ADD COLUMN  r_rp1853_drug24_id varchar(255);
--pu : 12/01/10 : ยกเลิกการใช้งาน ฟิลด์ r_rp1853_drug24_id เนื่องจากยกเลิกการใช้งานตาราง r_rp1853_drug24
--ALTER TABLE b_item DROP COLUMN  r_rp1853_drug24_id;


--2.icd10_description 
ALTER TABLE b_icd10  ALTER COLUMN  icd10_description  TYPE varchar(4000);

--3 temp_b_icd10  icd10 
create table temp_b_icd10 as 
(select  max(b_icd10_id) as b_icd10_id
,icd10_number as icd10_number
,max(icd10_description) as icd10_description
,max(icd10_other_description) as icd10_other_description
,max(icd10_generate_code) as icd10_generate_code
,max(active53) as active53
from b_icd10 group by icd10_number);

--4. icd10  b_icd10
delete from b_icd10;

--5. indexicd10_number  unique 
CREATE UNIQUE INDEX  b_icd10_number_pkey ON b_icd10 (icd10_number);
--6.insert  icd10 
INSERT INTO b_icd10 (select b_icd10_id,icd10_number,icd10_description,icd10_other_description,icd10_generate_code,active53 from temp_b_icd10);

drop table temp_b_icd10;
---update report 1853----------------------------------------------------
--แฟ้ม NURTI
-- ใช้ข้อมูลจากการลง Vital SIgns มาออกแฟ้มด้วย 
update t_visit_vital_sign set visit_vital_sign_modify_date_time = (record_date|| ','  || record_time) where visit_vital_sign_modify_date_time = ''; 
--FP 
update t_health_family_planing set update_record_date_time = record_date_time where update_record_date_time='';
--chronic
update t_chronic set modify_date_time = record_date_time where modify_date_time ='';
--anc
update t_health_anc set modify_date_time = record_date_time where modify_date_time ='';
--ซ่อมฐานข้อมูลเคสประชากรมีวันที่บันทึกเป็นค่าว่าง
update t_health_family set record_date_time='2552-12-21,12:12:12' where record_date_time='' ;
update t_health_family set modify_date_time= record_date_time where modify_date_time='' ;

--ปรับหน้าจอ setup สิทธิการใช้งาน ให้อ้างอิงกับทุกส่วนของโปรแกรมได้
update f_gui_action set f_gui_action_id = '08055' where  f_gui_action_id ='0814';
update f_gui_action set f_gui_action_id = '08057' where f_gui_action_id ='0813';
update f_gui_action_authen set f_gui_action_id = '08055' where  f_gui_action_id ='0814';
update f_gui_action_authen set f_gui_action_id = '08057' where f_gui_action_id ='0813';
insert into f_gui_action values ('1005','อ่านข่าวประจำวัน','');
update f_gui_action set f_gui_action_id = '0102' where  f_gui_action_id ='0201';
insert into f_gui_action values ('0103','เปลี่ยนผู้ใช้งาน','');
insert into f_gui_action values ('0104','Look and Feel','');
insert into f_gui_action values ('0105','Font','');
update f_gui_action set f_gui_action_id = '0106' where f_gui_action_id ='0202';
update f_gui_action set f_gui_action_id = '0107' where f_gui_action_id ='0203';
update f_gui_action set f_gui_action_id = '0110' where f_gui_action_id ='0101';
update f_gui_action set f_gui_action_id = '0420' where f_gui_action_id ='0409';
update f_gui_action set f_gui_action_id = '0419' where f_gui_action_id ='0408';
update f_gui_action set f_gui_action_id = '0416' where f_gui_action_id ='0410';
update f_gui_action set f_gui_action_id = '0417' where f_gui_action_id ='0411';
update f_gui_action set f_gui_action_id = '04075' where f_gui_action_id ='0414';
update f_gui_action_authen set f_gui_action_id = '0102' where  f_gui_action_id ='0201';
update f_gui_action_authen set f_gui_action_id = '0106' where f_gui_action_id ='0202';
update f_gui_action_authen set f_gui_action_id = '0107' where f_gui_action_id ='0203';
update f_gui_action_authen set f_gui_action_id = '0110' where f_gui_action_id ='0101';
update f_gui_action_authen set f_gui_action_id = '0420' where f_gui_action_id ='0409';
update f_gui_action_authen set f_gui_action_id = '0419' where f_gui_action_id ='0408';
update f_gui_action_authen set f_gui_action_id = '0416' where f_gui_action_id ='0410';
update f_gui_action_authen set f_gui_action_id = '0417' where f_gui_action_id ='0411';
update f_gui_action_authen set f_gui_action_id = '04075' where f_gui_action_id ='0414';

insert into f_gui_action values ('5a00','รายการแลป เอ็กซเรย์','');
update f_gui_action set f_gui_action_id = '5a02' where f_gui_action_id = '5406';
update f_gui_action set f_gui_action_id = '5a03' where f_gui_action_id = '5407';
update f_gui_action set f_gui_action_id = '5a04' where f_gui_action_id = '5408';
update f_gui_action set f_gui_action_id = '5a01' where f_gui_action_id = '5409';
update f_gui_action_authen set f_gui_action_id = '5a02' where f_gui_action_id = '5406';
update f_gui_action_authen set f_gui_action_id = '5a03' where f_gui_action_id = '5407';
update f_gui_action_authen set f_gui_action_id = '5a04' where f_gui_action_id = '5408';
update f_gui_action_authen set f_gui_action_id = '5a01' where f_gui_action_id = '5409';
insert into f_gui_action values ('52045','สถานพยาบาลในเขต','');
insert into f_gui_action values ('54015','กำหนดราคา','');
insert into f_gui_action values ('54045','คำแนะนำหลังตรวจ','');

update f_gui_action set f_gui_action_id = '55035' where f_gui_action_id ='5508'; 
update f_gui_action_authen set f_gui_action_id = '55035' where f_gui_action_id ='5508'; 

insert into f_gui_action values ('56035','กลุ่มโรค NCD','');
insert into f_gui_action values ('5704','ผู้ป่วยค้างชำระ','');
insert into f_gui_action values ('5804','ข้อมูลข่าวสาร','');
insert into f_gui_action values ('5h00','รายงาน 18 แฟ้ม 53','');
insert into f_gui_action values ('5h01','จับคู่ยา 24 หลัก','');
insert into f_gui_action values ('5h02','จับคู่สิทธิ','');
insert into f_gui_action values ('5h03','จับคู่คำนำหน้าชื่อ','');
insert into f_gui_action values ('5h04','จับคู่อาชีพ','');

ALTER TABLE t_health_epi_outsite ADD COLUMN b_health_epi_group_id varchar(255) NULL;
alter table b_health_epi_group add active varchar(255) default '1';
alter table t_health_family add father_family_id varchar(255);
alter table t_health_family add mother_family_id varchar(255);
alter table t_health_family add couple_family_id varchar(255);

create table f_item_lab_type(
id varchar(255),
name varchar(255));
ALTER TABLE ONLY f_item_lab_type ADD CONSTRAINT f_item_lab_type_pkey PRIMARY KEY (id); 

insert into f_item_lab_type values ('1','จุลชีววิทยา');
insert into f_item_lab_type values ('2','จุลทรรศน์วิทยาคลีนิค');
insert into f_item_lab_type values ('3','อิมมูนวิทยา');
insert into f_item_lab_type values ('4','Blood Bank');
 
alter table b_item add f_item_lab_type_id varchar(255);
update b_item set f_item_lab_type_id = '1' where item_number = 'L1050';
update b_item set f_item_lab_type_id = '1' where item_number = 'L1068';
update b_item set f_item_lab_type_id = '1' where item_number = 'L1081';
update b_item set f_item_lab_type_id = '1' where item_number = 'L1106';
update b_item set f_item_lab_type_id = '1' where item_number = 'L1165';
update b_item set f_item_lab_type_id = '1' where item_number = 'L1183';
update b_item set f_item_lab_type_id = '1' where item_number = 'L1184';
update b_item set f_item_lab_type_id = '1' where item_number = 'L1191';
update b_item set f_item_lab_type_id = '1' where item_number = 'L1195';
update b_item set f_item_lab_type_id = '1' where item_number = 'L1217';
update b_item set f_item_lab_type_id = '1' where item_number = 'LG055';
update b_item set f_item_lab_type_id = '1' where item_number = 'LG056';
update b_item set f_item_lab_type_id = '1' where item_number = 'LG093';
update b_item set f_item_lab_type_id = '1' where item_number = 'LG094';
update b_item set f_item_lab_type_id = '1' where item_number = 'LG162';
update b_item set f_item_lab_type_id = '1' where item_number = 'LG173';
update b_item set f_item_lab_type_id = '1' where item_number = 'LG294';
update b_item set f_item_lab_type_id = '2' where item_number = '1237';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1003';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1040';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1067';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1092';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1118';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1139';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1152';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1157';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1159';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1167';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1182';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1187';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1206';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1208';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1211';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1212';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1213';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1219';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1220';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1221';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1225';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1226';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1230';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1260';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1261';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1262';
update b_item set f_item_lab_type_id = '2' where item_number = 'L1263';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG054';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG057';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG077';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG084';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG091';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG092';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG095';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG132';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG147';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG178';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG179';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG187';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG194';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG195';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG211';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG216';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG222';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG232';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG259';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG263';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG266';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG270';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG271';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG272';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG273';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG300';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG301';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG302';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG303';
update b_item set f_item_lab_type_id = '2' where item_number = 'LG310';
update b_item set f_item_lab_type_id = '4' where item_number = 'L1236';
update b_item set f_item_lab_type_id = '4' where item_number = 'LG082';
update b_item set f_item_lab_type_id = '4' where item_number = 'LG083';
update b_item set f_item_lab_type_id = '4' where item_number = 'LG085';
update b_item set f_item_lab_type_id = '4' where item_number = 'LG165';
update b_item set f_item_lab_type_id = '4' where item_number = 'LG183';
update b_item set f_item_lab_type_id = '4' where item_number = 'LG185';
update b_item set f_item_lab_type_id = '4' where item_number = 'LG252';
update b_item set f_item_lab_type_id = '4' where item_number = 'LG253';
update b_item set f_item_lab_type_id = '4' where item_number = 'LG305';
update b_item set f_item_lab_type_id = '4' where item_number = 'LG306';
update b_item set f_item_lab_type_id = '4' where item_number = 'LG307';
update b_item set f_item_lab_type_id = '4' where item_number = 'LG308';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1001';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1014';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1015';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1017';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1021';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1023';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1024';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1031';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1049';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1072';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1073';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1085';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1086';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1087';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1088';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1089';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1100';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1110';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1126';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1135';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1162';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1170';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1171';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1190';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1194';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1201';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1202';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1204';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1229';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1232';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1234';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1241';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1264';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1278';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1279';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1280';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1281';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1287';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1288';
update b_item set f_item_lab_type_id = '3' where item_number = 'L1300';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG012';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG013';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG015';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG047';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG049';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG050';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG051';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG052';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG053';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG087';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG088';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG123';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG124';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG125';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG126';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG127';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG128';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG129';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG130';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG131';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG145';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG150';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG151';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG152';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG153';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG154';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG155';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG156';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG157';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG158';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG159';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG160';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG161';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG164';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG166';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG169';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG176';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG182';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG184';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG186';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG191';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG193';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG196';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG204';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG206';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG209';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG245';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG246';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG247';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG248';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG260';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG262';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG296';
update b_item set f_item_lab_type_id = '3' where item_number = 'LG309'; 
---update report 1853----------------------------------------------------
create table r_rplog_export (
id varchar(255),
date_time varchar(255),
ip_address varchar(255),
b_employee_id varchar(255),
result text);
ALTER TABLE ONLY r_rplog_export ADD CONSTRAINT r_rplog_export_pkey PRIMARY KEY (id); 

ALTER TABLE r_rplog_export	ADD COLUMN file_name varchar(255) NULL;
ALTER TABLE r_rplog_export	ADD COLUMN file_export_name text NULL;
ALTER TABLE r_rplog_export	ADD COLUMN during varchar(255) NULL;

create table b_site_news(
id varchar(255),
date_time varchar(255),
employee_id varchar(255),
topic varchar(255),
text text);
ALTER TABLE ONLY b_site_news ADD CONSTRAINT b_site_news_pkey PRIMARY KEY (id); 

    insert into b_site_news values ('1','1','1','ทดสอบนำเสนอข่าว HospitalOS','<html><body> 
<h2>ยินดีต้อนรับสู่ชุมชน Hospital OS</h2>
  <h3><font color=\'blue\'> Hospital OS คือ โปรแกรมที่ใช้ในการบริหารงานโรงพยาบาล รองรับการทำงานทั้งในส่วนของ OPD และ IPD เป็นหลัก โดยการสนับสนุนจาก ชมรมข้อมูล ข่าวสารทางการแพทย์และสาธารณสุขไทยมูลนิธิสาธารสุขแห่งชาติ (http://www.thainhf.org) และสำนักงานกองทุนสนับสนุนการวิจัย (สกว.) (http://trf.or.th) บนหลักการ Open Source GNU General Public License
     แนวคิดของ Hospital OS คือ "การสร้างและพัฒนาชุมชนแห่งการเรียนรู้ที่ยั่งยืน"โดยความร่วมมือและ ช่วยเหลือกัน จากทีมงานสู่สมาชิกและจากสมาชิกสู่สมาชิกด้วยกันเองจนกลายเป็นชุมชนที่มี ความเข้มแข็งสามารถ อยู่ได้ด้วยตัวเอง
</font></h3>
</body></html>');

--ซ้อมข้อมูลการสำรวจบ้านให้ไม่เกิดข้อมูลซ้ำซ้อน

create table temp_sub_home_we as select max(t_health_sub_home_id) as t_health_home_water_eradicate_id
,max(t_health_sub_home_id) as t_health_sub_home_id
,max(f_health_home_water_type_id) as f_health_home_water_type_id
,max(f_health_home_water_supply_id) as f_health_home_water_supply_id
,max(health_home_water) as health_home_water
,max(health_home_water_supply) as health_home_water_supply
,max(health_home_toilet) as health_home_toilet
,max(health_home_garbage) as health_home_garbage
,max(health_home_water_eradicate) as health_home_water_eradicate
,max(f_health_home_garbage_method_id)   as   f_health_home_garbage_method_id
  from t_health_home_water_eradicate group by t_health_sub_home_id;

delete from t_health_home_water_eradicate;
insert into t_health_home_water_eradicate select * from temp_sub_home_we;
drop table temp_sub_home_we;
-----------------------------------------------------------------------------------
create table temp_sub_home_we as select max(t_health_sub_home_id) as t_health_home_house_standard_id
,max( t_health_sub_home_id) as t_health_sub_home_id
,max( health_home_durability) as health_home_durability
,max( health_home_care) as health_home_care
,max( health_home_light) as health_home_light
,max( health_home_cleanness) as health_home_cleanness
,max( health_home_ventilation) as health_home_ventilation
,max( health_home_house_standard    ) as health_home_house_standard  
  from t_health_home_house_standard group by t_health_sub_home_id;

delete from t_health_home_house_standard;
insert into t_health_home_house_standard select * from temp_sub_home_we;
drop table temp_sub_home_we;
-----------------------------------------------------------------------------------
create table temp_sub_home_we as select max(t_health_sub_home_id) as t_health_home_food_standard_id   
,max( t_health_sub_home_id) as t_health_sub_home_id
,max( health_home_mixture_food) as health_home_mixture_food
,max( health_home_food_vessel) as health_home_food_vessel
,max( health_home_food_cover) as health_home_food_cover
,max( health_home_food_wash) as health_home_food_wash
,max( health_home_food_keep) as health_home_food_keep
,max( health_home_kitchen_garbage) as health_home_kitchen_garbage
,max( health_home_kitchen_cleanness) as health_home_kitchen_cleanness
,max( health_home_food_standard) as health_home_food_standard
,max( health_home_iodine) as health_home_iodine
,max( health_home_salt_iodine) as health_home_salt_iodine
,max( health_home_product_iodine    ) as health_home_product_iodine    
  from t_health_home_food_standard group by t_health_sub_home_id;

delete from t_health_home_food_standard;
insert into t_health_home_food_standard select * from temp_sub_home_we;
drop table temp_sub_home_we;
-----------------------------------------------------------------------------------
create table temp_sub_home_we as select max(t_health_sub_home_id) as t_health_home_bug_control_id 
,max( t_health_sub_home_id) as t_health_sub_home_id
,max( health_home_rat_control) as health_home_rat_control
,max( health_home_cockroach_control) as health_home_cockroach_control
,max( health_home_fly_control) as health_home_fly_control
,max( health_home_bug_control) as health_home_bug_control
,max( health_home_pet) as health_home_pet
,max( f_health_home_dung_control_id) as f_health_home_dung_control_id
,max( health_home_animal_control    ) as health_home_animal_control    

  from t_health_home_bug_control group by t_health_sub_home_id;

delete from t_health_home_bug_control;
insert into t_health_home_bug_control select * from temp_sub_home_we;
drop table temp_sub_home_we;
-----------------------------------------------------------------------------------
---จัดการข้อมูลพื้นฐาน-------------------------------------------------------------------------
insert into f_patient_area_status values ('0','มาอาศัยในเขตรับผิดชอบแต่ไม่ได้อยู่ตามทะเบียนบ้าน');

insert into f_patient_religion values ('4','ฮินดู');
insert into f_patient_religion values ('6','ไม่นับถือศาสนา');
insert into f_patient_religion values ('9','ไม่ทราบ');
insert into f_patient_religion values ('7','อื่นๆ');

insert into f_patient_occupation values ('105','ช่างเทคนิควิศวกรรม');
insert into f_patient_occupation values ('106','นักวิทยาศาสตร์');
insert into f_patient_occupation values ('107','แพทย์');
insert into f_patient_occupation values ('108','ศัลยแพทย์');
insert into f_patient_occupation values ('109','ทันตแพทย์');
insert into f_patient_occupation values ('110','สัตวแพทย์');
insert into f_patient_occupation values ('111','อาจารย์มหาวิทยาลัย');
insert into f_patient_occupation values ('112','อาจารย์โรงเรียน');
insert into f_patient_occupation values ('113','พยาบาล');
insert into f_patient_occupation values ('114','เภสัชกร');
insert into f_patient_occupation values ('115','ผู้ปฏิบัติงานทางเทคนิคการแพทย์');
insert into f_patient_occupation values ('116','พนักงานที่ทำงานช่วยเหลือด้านกา');
insert into f_patient_occupation values ('117','ผู้พิพากษา');
insert into f_patient_occupation values ('118','อัยการ');
insert into f_patient_occupation values ('119','ประติมากร');
insert into f_patient_occupation values ('120','จิตรกร');
insert into f_patient_occupation values ('121','ช่างศิลป์');
insert into f_patient_occupation values ('122','ช่างภาพ');
insert into f_patient_occupation values ('123','นักประพันธ์');
insert into f_patient_occupation values ('124','นักข่าว');
insert into f_patient_occupation values ('125','นักหนังสือพิมพ์');
insert into f_patient_occupation values ('126','นางแบบ');
insert into f_patient_occupation values ('127','นักร้อง');
insert into f_patient_occupation values ('139','นักแสดง');
--ข้อมูลซ้ำซ้อนกัน
delete from f_patient_occupation where f_patient_occupation_id = '503';
delete from f_patient_occupation where f_patient_occupation_id = '803';
delete from f_patient_occupation where f_patient_occupation_id = '905';
delete from f_patient_occupation where f_patient_occupation_id = '904'; 
insert into f_patient_occupation values ('503','ทำนา');
insert into f_patient_occupation values ('803','ช่างซ่อมเครื่องไฟฟ้า');
insert into f_patient_occupation values ('905','นักศึกษา');
insert into f_patient_occupation values ('904','แม่บ้าน');


---------------------------------------------------------------------
create table r_rp1853_marriage (
id varchar(255),
name varchar(255) 
); 
ALTER TABLE ONLY r_rp1853_marriage ADD CONSTRAINT r_rp1853_marriage_pkey PRIMARY KEY (id); 
insert into r_rp1853_marriage values ('1','โสด');
insert into r_rp1853_marriage values ('2','คู่');
insert into r_rp1853_marriage values ('3','หม้าย');
insert into r_rp1853_marriage values ('4','หย่า');
insert into r_rp1853_marriage values ('5','แยก');
insert into r_rp1853_marriage values ('6','สมณะ');
insert into r_rp1853_marriage values ('9','ไม่ทราบ');
alter table f_patient_marriage_status add r_rp1853_marriage_id varchar(255);
update f_patient_marriage_status set r_rp1853_marriage_id = '1' where f_patient_marriage_status_id ='1';
update f_patient_marriage_status set r_rp1853_marriage_id = '2' where f_patient_marriage_status_id ='2';
update f_patient_marriage_status set r_rp1853_marriage_id = '5' where f_patient_marriage_status_id ='3';
update f_patient_marriage_status set r_rp1853_marriage_id = '4' where f_patient_marriage_status_id ='4';
update f_patient_marriage_status set r_rp1853_marriage_id = '3' where f_patient_marriage_status_id ='5';
update f_patient_marriage_status set r_rp1853_marriage_id = '6' where f_patient_marriage_status_id ='6';
---------------------------------------------------------------------
create table r_rp1853_blood (
id varchar(255),
name varchar(255) 
); 
ALTER TABLE ONLY r_rp1853_blood ADD CONSTRAINT r_rp1853_blood_pkey PRIMARY KEY (id); 
insert into r_rp1853_blood values ('1','A');
insert into r_rp1853_blood values ('2','B');
insert into r_rp1853_blood values ('3','AB');
insert into r_rp1853_blood values ('4','O');
 
alter table  f_patient_blood_group add r_rp1853_blood_id varchar(255);
update f_patient_blood_group set r_rp1853_blood_id = '1' where f_patient_blood_group_id ='2' ;
update f_patient_blood_group set r_rp1853_blood_id = '2' where f_patient_blood_group_id ='3' ;
update f_patient_blood_group set r_rp1853_blood_id = '3' where f_patient_blood_group_id ='4' ;
update f_patient_blood_group set r_rp1853_blood_id = '4' where f_patient_blood_group_id ='5' ;

create table r_rp1853_education (
id varchar(255),
name varchar(255) 
); 

ALTER TABLE ONLY r_rp1853_education ADD CONSTRAINT r_rp1853_education_pkey PRIMARY KEY (id); 

insert into r_rp1853_education values ('0','ไม่ได้รับการศึกษา');
insert into r_rp1853_education values ('1','ก่อนประถมศึกษา');
insert into r_rp1853_education values ('2','ประถมศึกษา');
insert into r_rp1853_education values ('3','มัธยมศึกษาตอนต้น');
insert into r_rp1853_education values ('4','มัธยมศึกษาตอนปลาย');
insert into r_rp1853_education values ('5','อนุปริญญา');
insert into r_rp1853_education values ('6','ปริญญาตรี');
insert into r_rp1853_education values ('7','ปริญญาโท');
insert into r_rp1853_education values ('8','ปริญญาเอก');
insert into r_rp1853_education values ('9','ไม่ทราบ');
alter table  f_patient_education_type add r_rp1853_education_id varchar(255);
insert into f_patient_education_type values ('14','ปริญญาโท','11','7');
insert into f_patient_education_type values ('15','ปริญญาเอก','12','8');
update f_patient_education_type set r_rp1853_education_id = '0' where f_patient_education_type_id ='01' ;
update f_patient_education_type set r_rp1853_education_id = '' where f_patient_education_type_id ='02' ;
update f_patient_education_type set r_rp1853_education_id = '2' where f_patient_education_type_id ='03' ;
update f_patient_education_type set r_rp1853_education_id = '4' where f_patient_education_type_id ='04'  ;
update f_patient_education_type set r_rp1853_education_id = '4' where f_patient_education_type_id ='06' ;
update f_patient_education_type set r_rp1853_education_id = '5' where f_patient_education_type_id ='07' ;
update f_patient_education_type set r_rp1853_education_id = '5' where f_patient_education_type_id ='08' ;
update f_patient_education_type set r_rp1853_education_id = '6' where f_patient_education_type_id ='09' ;
update f_patient_education_type set r_rp1853_education_id = '' where f_patient_education_type_id ='10' ;
update f_patient_education_type set r_rp1853_education_id = '9' where f_patient_education_type_id ='11' ;
update f_patient_education_type set r_rp1853_education_id = '3' where f_patient_education_type_id ='13' ;
 
---------------------------------------------------------------------

alter table f_patient_occupation add r_rp1853_occupation_id varchar(255);
create table r_rp1853_occupation (
id varchar(255),
name varchar(255) 
); 
ALTER TABLE ONLY r_rp1853_occupation ADD CONSTRAINT r_rp1853_occupation_pkey PRIMARY KEY (id); 

insert into r_rp1853_occupation values ('000','ไม่มีอาชีพ');
insert into r_rp1853_occupation values ('001','เกษตรกรรม');
insert into r_rp1853_occupation values ('002','รับจ้าง');
insert into r_rp1853_occupation values ('003','ค้าขาย');
insert into r_rp1853_occupation values ('004','รับราชการ');
insert into r_rp1853_occupation values ('005','รัฐวิสาหกิจ');
insert into r_rp1853_occupation values ('006','นักการเมือง');
insert into r_rp1853_occupation values ('007','ทหาร,ตำรวจ');
insert into r_rp1853_occupation values ('008','ประมง');
insert into r_rp1853_occupation values ('009','ครู');
insert into r_rp1853_occupation values ('010','อื่นๆ');
insert into r_rp1853_occupation values ('012','เลี้ยงสัตว์');
insert into r_rp1853_occupation values ('013','นักบวช,สมณะ');
insert into r_rp1853_occupation values ('014','งานบ้าน');
insert into r_rp1853_occupation values ('015','นักเรียน');
insert into r_rp1853_occupation values ('900','หญิงบริการ');
insert into r_rp1853_occupation values ('901','ไม่ระบุ'); 


update f_patient_occupation set r_rp1853_occupation_id = '000' where f_patient_occupation_id = '000';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '101';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '102';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '103';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '105';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '106';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '107';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '108';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '109';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '110';
update f_patient_occupation set r_rp1853_occupation_id = '009' where f_patient_occupation_id = '111';
update f_patient_occupation set r_rp1853_occupation_id = '009' where f_patient_occupation_id = '112';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '113';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '114';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '115';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '116';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '117';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '118';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '119';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '120';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '121';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '122';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '123';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '124';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '125';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '126';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '126';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '127';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '128';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '129';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '130';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '131';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '132';
update f_patient_occupation set r_rp1853_occupation_id = '013' where f_patient_occupation_id = '133';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '134';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '135';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '136';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '137';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '138';
update f_patient_occupation set r_rp1853_occupation_id = '004' where f_patient_occupation_id = '201';
update f_patient_occupation set r_rp1853_occupation_id = '007' where f_patient_occupation_id = '202';
update f_patient_occupation set r_rp1853_occupation_id = '007' where f_patient_occupation_id = '204';
update f_patient_occupation set r_rp1853_occupation_id = '007' where f_patient_occupation_id = '205';
update f_patient_occupation set r_rp1853_occupation_id = '006' where f_patient_occupation_id = '206';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '207';
update f_patient_occupation set r_rp1853_occupation_id = '004' where f_patient_occupation_id = '208';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '209';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '210';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '211';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '212';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '213';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '214';
update f_patient_occupation set r_rp1853_occupation_id = '004' where f_patient_occupation_id = '215';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '216';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '301';
update f_patient_occupation set r_rp1853_occupation_id = '005' where f_patient_occupation_id = '302';
update f_patient_occupation set r_rp1853_occupation_id = '005' where f_patient_occupation_id = '303';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '401';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '402';
update f_patient_occupation set r_rp1853_occupation_id = '002' where f_patient_occupation_id = '403';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '404';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '405';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '406';
update f_patient_occupation set r_rp1853_occupation_id = '001' where f_patient_occupation_id = '501';
update f_patient_occupation set r_rp1853_occupation_id = '001' where f_patient_occupation_id = '502';
update f_patient_occupation set r_rp1853_occupation_id = '001' where f_patient_occupation_id = '503';
update f_patient_occupation set r_rp1853_occupation_id = '001' where f_patient_occupation_id = '503';
update f_patient_occupation set r_rp1853_occupation_id = '001' where f_patient_occupation_id = '504';
update f_patient_occupation set r_rp1853_occupation_id = '001' where f_patient_occupation_id = '505';
update f_patient_occupation set r_rp1853_occupation_id = '012' where f_patient_occupation_id = '506';
update f_patient_occupation set r_rp1853_occupation_id = '008' where f_patient_occupation_id = '507';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '508';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '509';
update f_patient_occupation set r_rp1853_occupation_id = '003' where f_patient_occupation_id = '601';
update f_patient_occupation set r_rp1853_occupation_id = '003' where f_patient_occupation_id = '602';
update f_patient_occupation set r_rp1853_occupation_id = '003' where f_patient_occupation_id = '603';
update f_patient_occupation set r_rp1853_occupation_id = '003' where f_patient_occupation_id = '604';
update f_patient_occupation set r_rp1853_occupation_id = '003' where f_patient_occupation_id = '605';
update f_patient_occupation set r_rp1853_occupation_id = '003' where f_patient_occupation_id = '606';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '701';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '702';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '703';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '704';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '705';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '706';
update f_patient_occupation set r_rp1853_occupation_id = '900' where f_patient_occupation_id = '707';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '708';
update f_patient_occupation set r_rp1853_occupation_id = '002' where f_patient_occupation_id = '709';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '710';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '711';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '712';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '713';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '714';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '715';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '716';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '717';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '718';
update f_patient_occupation set r_rp1853_occupation_id = '900' where f_patient_occupation_id = '719';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '720';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '801';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '802';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '803';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '803';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '804';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '805';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '806';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '807';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '808';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '809';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '810';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '811';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '812';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '813';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '814';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '815';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '816';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '817';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '818';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '819';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '820';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '821';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '822';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '823';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '824';
update f_patient_occupation set r_rp1853_occupation_id = '015' where f_patient_occupation_id = '900';
update f_patient_occupation set r_rp1853_occupation_id = '013' where f_patient_occupation_id = '901';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '902';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '903';
update f_patient_occupation set r_rp1853_occupation_id = '010' where f_patient_occupation_id = '905';
update f_patient_occupation set r_rp1853_occupation_id = '014' where f_patient_occupation_id = '905'; 
update f_patient_occupation set r_rp1853_occupation_id = '009' where f_patient_occupation_id = '906';
update f_patient_occupation set r_rp1853_occupation_id = '901' where f_patient_occupation_id = '999';
--------------------------------------------------------------------------------------
alter table t_health_family add r_rp1853_foreign_id varchar(255);

create table r_rp1853_foreign (
id varchar(255),
name varchar(255) 
); 

ALTER TABLE ONLY r_rp1853_foreign ADD CONSTRAINT r_rp1853_foreign_pkey PRIMARY KEY (id); 

insert into r_rp1853_foreign values ('11','ต่างด้าวที่ขึ้นทะเบียนกับกระทรวงมหาดไทย และที่มีเลข 13 หลัก ที่ขึ้นต้นด้วยเลข 6,7');
insert into r_rp1853_foreign values ('12','ต่างด้าวที่ขึ้นทะเบียนกับกระทรวงแรงงานเพื่อขอทำงานที่มีรหัสที่ขึ้นต้นด้วยเลข 0 และได้ Workpermitted');
insert into r_rp1853_foreign values ('13',' ต่างด้าวที่ขึ้นทะเบียนกับกระทรวงมหาดไทยที่มีรหัสที่ขึ้นต้นด้วยเลข 0');
insert into r_rp1853_foreign values ('14','ต่างด้าวที่ขออนุญาตทำงานถูกต้องตามกฎหมายกับกระทรวงแรงงาน  โดยมี Passport/Visa เป็นหลักฐานในการขอนุญาต');
insert into r_rp1853_foreign values ('21','ต่างด้าวที่อพยพ และอยู่ในค่าย/ศูนย์พักพิง');
insert into r_rp1853_foreign values ('22','ต่างด้าวที่ติดตามรหัส 11 และ 12 ได้แก่สามึ/ภรรยา/บุตร/ญาติ');
insert into r_rp1853_foreign values ('23','หมายถึงกลุ่มอื่นๆ');

create table r_rp1853_nutritionlevel (
id varchar(255),
name varchar(255) 
); 

ALTER TABLE ONLY r_rp1853_nutritionlevel ADD CONSTRAINT r_rp1853_nutritionlevel_pkey PRIMARY KEY (id); 

insert into r_rp1853_nutritionlevel values ('1','ต่ำกว่าเกณฑ์');
insert into r_rp1853_nutritionlevel values ('2','ค่อนข้างต่ำ');
insert into r_rp1853_nutritionlevel values ('3','ปกติ');
insert into r_rp1853_nutritionlevel values ('4','ค่อนข้างสูง');
insert into r_rp1853_nutritionlevel values ('5','สูงเกินเกณฑ์');

alter table t_health_nutrition add r_rp1853_nutritionlevel_id varchar(255);

create table r_rp1853_vcctype (
id varchar(255),
name varchar(255),
description varchar(255) ,
type varchar(255) ,
age varchar(255) 
); 
ALTER TABLE ONLY r_rp1853_vcctype ADD CONSTRAINT r_rp1853_vcctype_pkey PRIMARY KEY (id); 

insert into r_rp1853_vcctype values ('010','BCG','บีซีจี','ฉีด','0');
insert into r_rp1853_vcctype values ('021','dTs1','ดีทีเอส1','ฉีด','ป1');
insert into r_rp1853_vcctype values ('022','dTs2','ดีทีเอส 2','ฉีด','ป1');
insert into r_rp1853_vcctype values ('023','dTs3','ดีทีเอส 3','ฉีด','ป2');
insert into r_rp1853_vcctype values ('024','dTs4','ดีทีเอส 4','ฉีด','ป6');
insert into r_rp1853_vcctype values ('031','DTP1','ดีทีพี1','ฉีด','2');
insert into r_rp1853_vcctype values ('032','DTP2','ดีทีพี2','ฉีด','4');
insert into r_rp1853_vcctype values ('033','DTP3','ดีทีพี3','ฉีด','6');
insert into r_rp1853_vcctype values ('034','DTP4','ดีทีพี กระตุ้น 1','ฉีด','18');
insert into r_rp1853_vcctype values ('035','DTP5','ดีทีพี กระตุ้น 2','ฉีด','48');
insert into r_rp1853_vcctype values ('041','HBV1','ตับอักเสบบี 1','ฉีด','0');
insert into r_rp1853_vcctype values ('042','HBV2','ตับอักเสบบี 2 ','ฉีด','2');
insert into r_rp1853_vcctype values ('043','HBV3','ตับอักเสบบี3','ฉีด','6');
insert into r_rp1853_vcctype values ('051','JE1','ไข้สมองอักเสบเจอี 1','ฉีด','18');
insert into r_rp1853_vcctype values ('052','JE2','ไข้สมองอักเสบเจอี 2','ฉีด','18');
insert into r_rp1853_vcctype values ('053','JE3','ไข้สมองอักเสบเจอี 3','ฉีด','30');
insert into r_rp1853_vcctype values ('061','MEASLES/MMR','หัด คางทูม หัดเยอรมัน','ฉีด','9');
insert into r_rp1853_vcctype values ('072 ','MMR','หัด คางทูม หัดเยอรมัน','ฉีด','ป1');
insert into r_rp1853_vcctype values ('081','OPV1','โอพีวี 1','กิน','2');
insert into r_rp1853_vcctype values ('082','OPV2','โอพีวี 2','กิน','4');
insert into r_rp1853_vcctype values ('083','OPV3 ','โอพีวี 3','กิน','6');
insert into r_rp1853_vcctype values ('084','OPV4','โอพีวี กระตุ้น 1','กิน','18');
insert into r_rp1853_vcctype values ('085','OPV5','โอพีวี กระตุ้น 2','กิน','48');
insert into r_rp1853_vcctype values ('086','OPVs1','โอพีวีเอส 1','กิน','ป1 ');
insert into r_rp1853_vcctype values ('087','OPVs2','โอพีวีเอส2','กิน','ป1');
insert into r_rp1853_vcctype values ('088','OPVs3','โอพีวีเอส 3','กิน','ป2');
insert into r_rp1853_vcctype values ('089','OPVC','โอพีวีซี','กิน','ให้การรณรงค์');
insert into r_rp1853_vcctype values ('201','dTANC 1','ดีทีเอเอ็นซี เข็ม 1','ฉีด','สำหรับหญิงมีครรภ์');
insert into r_rp1853_vcctype values ('202','dTANC 2','ดีทีเอเอ็นซี  เข็ม 2','ฉีด','สำหรับหญิงมีครรภ์');
insert into r_rp1853_vcctype values ('203','dTANC 3 ','ดีทีเอเอ็นซี  เข็ม 3','ฉีด','สำหรับหญิงมีครรภ์');
insert into r_rp1853_vcctype values ('204','dTANC 4 ','ดีทีเอเอ็นซี เข็ม 4 (กระตุ้น)','ฉีด','สำหรับหญิงมีครรภ์');
insert into r_rp1853_vcctype values ('205','dTANC 5 ','ดีทีเอเอ็นซี เข็ม 5 (กระตุ้น)','ฉีด','สำหรับหญิงมีครรภ์');
insert into r_rp1853_vcctype values ('101','TT1/dT1','ทีที/ดีที  เข็ม 1','ฉีด','บาดแผล');
insert into r_rp1853_vcctype values ('102','TT2/dT2','ทีที /ดีที เข็ม 2','ฉีด','บาดแผล');
insert into r_rp1853_vcctype values ('103','TT3/dT3','ทีที/ดีที   เข็ม 3','ฉีด','บาดแผล');
insert into r_rp1853_vcctype values ('104','TT4/dT4*','ทีที/ดีที เข็ม 4 (กระตุ้น)','ฉีด','บาดแผล');
insert into r_rp1853_vcctype values ('105','TT5/dT5 *','ทีที/ดีที เข็ม 5 (กระตุ้น)','ฉีด','บาดแผล');
insert into r_rp1853_vcctype values ('091','DTPHB 1','ดีทีพีตับอักเสบบี 1','เข็ม','2');
insert into r_rp1853_vcctype values ('092','DTPHB 2 ','ดีทีพีตับอักเสบบี 2','เข็ม','4');
insert into r_rp1853_vcctype values ('093','DTPHB 3 ','ดีทีพีตับอักเสบบี 3','เข็ม','6');
insert into r_rp1853_vcctype values ('111','Rabies Vaccine 1','วัคซีนพิษสุนัขบ้า','เข็ม','');
insert into r_rp1853_vcctype values ('112','Rabies Vaccine 2','วัคซีนพิษสุนัขบ้า','เข็ม','');
insert into r_rp1853_vcctype values ('113','Rabies Vaccine 3','วัคซีนพิษสุนัขบ้า','เข็ม','');
insert into r_rp1853_vcctype values ('114','Rabies Vaccine 4','วัคซีนพิษสุนัขบ้า','เข็ม','');
insert into r_rp1853_vcctype values ('115','Rabies Vaccine 5','วัคซีนพิษสุนัขบ้า','เข็ม','');
insert into r_rp1853_vcctype values ('815','Flu','ไข้หวัดใหญ่','เข็ม','');
---------------------------------------------------------------------
alter table b_icd9 add cost varchar(255); 

create table r_rp1853_aptype (
id varchar(255),
name varchar(255),
description varchar(255) 
); 
ALTER TABLE ONLY r_rp1853_aptype ADD CONSTRAINT r_rp1853_aptype_pkey PRIMARY KEY (id); 
alter table t_patient_appointment add r_rp1853_aptype_id varchar(255);

insert into r_rp1853_aptype values ('010','BCG','บีซีจี');
insert into r_rp1853_aptype values ('021','dTs1','ดีทีเอส1');
insert into r_rp1853_aptype values ('022','dTs2','ดีทีเอส 2');
insert into r_rp1853_aptype values ('023','dTs3','ดีทีเอส 3');
insert into r_rp1853_aptype values ('024','dTs4','ดีทีเอส 4');
insert into r_rp1853_aptype values ('031','DTP1','ดีทีพี1');
insert into r_rp1853_aptype values ('032','DTP2','ดีทีพี2');
insert into r_rp1853_aptype values ('033','DTP3','ดีทีพี3');
insert into r_rp1853_aptype values ('034','DTP4','ดีทีพี กระตุ้น 1');
insert into r_rp1853_aptype values ('035','DTP5','ดีทีพี กระตุ้น 2');
insert into r_rp1853_aptype values ('041','HBV1','ตับอักเสบบี 1');
insert into r_rp1853_aptype values ('042','HBV2','ตับอักเสบบี 2 ');
insert into r_rp1853_aptype values ('043','HBV3','ตับบอักเสบบี 3');
insert into r_rp1853_aptype values ('051','JE1','ไข้สมองอักเสบเจอี 1');
insert into r_rp1853_aptype values ('052','JE2','ไข้สมองอักเสบเจอี 2');
insert into r_rp1853_aptype values ('053','JE3','ไข้สมองอักเสบเจอี 3');
insert into r_rp1853_aptype values ('061','MEASLES/MMR','หัด คางทูม หัดเยอรมัน');
insert into r_rp1853_aptype values ('072','MMR','หัด คางทูม หัดเยอรมัน');
insert into r_rp1853_aptype values ('081','OPV1','โอพีวี 1');
insert into r_rp1853_aptype values ('082','OPV2','โอพีวี 2');
insert into r_rp1853_aptype values ('083','OPV3','โอพีวี 3');
insert into r_rp1853_aptype values ('084','OPV4','โอพีวี กระตุ้น 1');
insert into r_rp1853_aptype values ('085','OPV5','โอพีวี กระตุ้น 2');
insert into r_rp1853_aptype values ('086','OPVs1','โอพีวีเอส 1');
insert into r_rp1853_aptype values ('087','OPVs2','โอพีวีเอส2');
insert into r_rp1853_aptype values ('088','OPVs3','โอพีวีเอส 3');
insert into r_rp1853_aptype values ('089','OPVC','โอพีวีซี');
insert into r_rp1853_aptype values ('201','dTANC 1','ดีทีเอเอ็นซี เข็ม 1');
insert into r_rp1853_aptype values ('202','dTANC 2','ดีทีเอเอ็นซี  เข็ม 2');
insert into r_rp1853_aptype values ('203','dTANC 3','ดีทีเอเอ็นซี  เข็ม 3');
insert into r_rp1853_aptype values ('204','dTANC 4','ดีทีเอเอ็นซีเข็ม 4 กระตุ้น');
insert into r_rp1853_aptype values ('205','dTANC 5','ดีทีเอเอ็นซีเข็ม 5 กระตุ้น');
insert into r_rp1853_aptype values ('101','TT1/dT1','ทีที/ดีที  เข็ม 1');
insert into r_rp1853_aptype values ('102','TT2/dT2','ทีที /ดีที เข็ม 2');
insert into r_rp1853_aptype values ('103','TT3/dT3','ทีที/ดีที   เข็ม 3');
insert into r_rp1853_aptype values ('104','TT4/dT4','ทีที/ดีที กระตุ้น');
insert into r_rp1853_aptype values ('105','TT5/dT5 ','ทีที/ดีที กระตุ้น');
insert into r_rp1853_aptype values ('091','DTPHB 1','ดีทีพีตับเอกเสบบี 1');
insert into r_rp1853_aptype values ('092','DTPHB 2','ดีทีพีตับเอกเสบบี 2');
insert into r_rp1853_aptype values ('093','DTPHB 3','ดีทีพีตับเอกเสบบี 3');
insert into r_rp1853_aptype values ('111','Rabies Vaccine 1','วัคซีนพิษสุนัขบ้า');
insert into r_rp1853_aptype values ('112','Rabies Vaccine 2','วัคซีนพิษสุนัขบ้า');
insert into r_rp1853_aptype values ('113','Rabies Vaccine 3','วัคซีนพิษสุนัขบ้า');
insert into r_rp1853_aptype values ('114','Rabies Vaccine 4','วัคซีนพิษสุนัขบ้า');
insert into r_rp1853_aptype values ('115','Rabies Vaccine 5','วัคซีนพิษสุนัขบ้า');
insert into r_rp1853_aptype values ('815','Flu','ไข้หวัดใหญ่');
insert into r_rp1853_aptype values ('121','วางแผนครอบครัว','');
insert into r_rp1853_aptype values ('131','ตรวจครรภ์','');
insert into r_rp1853_aptype values ('141','ตรวจหลังคลอด','');
insert into r_rp1853_aptype values ('151','รับยา','');
insert into r_rp1853_aptype values ('161','ชั่งน้ำหนัก','');
insert into r_rp1853_aptype values ('171','คลอด','');
insert into r_rp1853_aptype values ('181','ฟังผลการรักษา','');
-------------------------------------------------------

create table r_rp1853_prefix (
id varchar(255),
name varchar(255),
description varchar(255) 
); 
ALTER TABLE ONLY r_rp1853_prefix ADD CONSTRAINT r_rp1853_prefix_pkey PRIMARY KEY (id); 

ALTER TABLE f_patient_prefix add r_rp1853_prefix_id varchar(255); 
-------------------------------------------------------
insert into r_rp1853_prefix values ('1','ด.ช.','เด็กชาย');
insert into r_rp1853_prefix values ('2','ด.ญ.','เด็กหญิง');
insert into r_rp1853_prefix values ('3','นาย','นาย');
insert into r_rp1853_prefix values ('4','น.ส.','นางสาว');
insert into r_rp1853_prefix values ('5','นาง','นาง');
insert into r_rp1853_prefix values ('6','น.ช.ม.ล.','นักโทษชายหม่อมหลวง');
insert into r_rp1853_prefix values ('7','น.ช.','นักโทษชาย');
insert into r_rp1853_prefix values ('8','น.ญ.','นักโทษหญิง'); 
insert into r_rp1853_prefix values ('9','น.ช.จ.ส.อ.','นักโทษชายจ่าสิบเอก');
insert into r_rp1853_prefix values ('10','น.ช.จ.อ.','นักโทษชายจ่าเอก');
insert into r_rp1853_prefix values ('11','น.ช.พลฯ.','นักโทษชายพลทหาร');
insert into r_rp1853_prefix values ('12','น.ช.ร.ต.','นักโทษชายร้อยตรี');
insert into r_rp1853_prefix values ('99','-','พระเจ้าหลานเธอ พระองค์เจ้า');
insert into r_rp1853_prefix values ('100','พระบาทสมเด็จพระเจ้าอยู่หัว','พระบาทสมเด็จพระเจ้าอยู่หัว');
insert into r_rp1853_prefix values ('101','สมเด็จพระนางเจ้า','สมเด็จพระนางเจ้า');
insert into r_rp1853_prefix values ('102','สมเด็จพระศรีนครินทราบรมราชชนนี','สมเด็จพระศรีนครินทราบรมราชชนนี');
insert into r_rp1853_prefix values ('103','พลโทสมเด็จพระบรมโอรสาธิราช','พลโทสมเด็จพระบรมโอรสาธิราช');
insert into r_rp1853_prefix values ('104','พลตรีสมเด็จพระเทพรัตนราชสุดา','พลตรีสมเด็จพระเทพรัตนราชสุดา');
insert into r_rp1853_prefix values ('105','พระเจ้าวรวงศ์เธอพระองค์หญิง','พระเจ้าวรวงศ์เธอพระองค์หญิง');
insert into r_rp1853_prefix values ('106','พระเจ้าวรวงศ์เธอ พระองค์เจ้า','พระเจ้าวรวงศ์เธอ พระองค์เจ้า');
insert into r_rp1853_prefix values ('107','สมเด็จพระราชชนนี','สมเด็จพระราชชนนี');
insert into r_rp1853_prefix values ('108','สมเด็จพระเจ้าพี่นางเธอเจ้าฟ้า','สมเด็จพระเจ้าพี่นางเธอเจ้าฟ้า');
insert into r_rp1853_prefix values ('109','สมเด็จพระ','สมเด็จพระ');
insert into r_rp1853_prefix values ('110','สมเด็จพระเจ้าลูกเธอ','สมเด็จพระเจ้าลูกเธอ');
insert into r_rp1853_prefix values ('111','สมเด็จพระเจ้าลูกยาเธอ','สมเด็จพระเจ้าลูกยาเธอ');
insert into r_rp1853_prefix values ('112','สมเด็จเจ้าฟ้า','สมเด็จเจ้าฟ้า');
insert into r_rp1853_prefix values ('113','พระเจ้าบรมวงศ์เธอ','พระเจ้าบรมวงศ์เธอ');
insert into r_rp1853_prefix values ('114','พระเจ้าวรวงศ์เธอ','พระเจ้าวรวงศ์เธอ');
insert into r_rp1853_prefix values ('115','พระเจ้าหลานเธอ','พระเจ้าหลานเธอ');
insert into r_rp1853_prefix values ('116','พระเจ้าหลานยาเธอ','พระเจ้าหลานยาเธอ');
insert into r_rp1853_prefix values ('117','พระวรวงศ์เธอ','พระวรวงศ์เธอ');
insert into r_rp1853_prefix values ('118','สมเด็จพระเจ้าภคินีเธอ','สมเด็จพระเจ้าภคินีเธอ');
insert into r_rp1853_prefix values ('119','พระองค์เจ้า','พระองค์เจ้า');
insert into r_rp1853_prefix values ('120','ม.จ.','หม่อมเจ้า');
insert into r_rp1853_prefix values ('121','ม.ร.ว.','หม่อมราชวงศ์');
insert into r_rp1853_prefix values ('122','ม.ล.','หม่อมหลวง');
insert into r_rp1853_prefix values ('123','พระยา','พระยา');
insert into r_rp1853_prefix values ('124','หลวง','หลวง');
insert into r_rp1853_prefix values ('125','ขุน','ขุน');
insert into r_rp1853_prefix values ('126','หมื่น','หมื่น');
insert into r_rp1853_prefix values ('127','เจ้าคุณ','เจ้าคุณ');
insert into r_rp1853_prefix values ('128','พระวรวงศ์เธอพระองค์เจ้า','พระวรวงศ์เธอพระองค์เจ้า');
insert into r_rp1853_prefix values ('129','คุณ','คุณ');
insert into r_rp1853_prefix values ('130','คุณหญิง','คุณหญิง');
insert into r_rp1853_prefix values ('131','ท่านผู้หญิง ม.ล.','ท่านผู้หญิงหม่อมหลวง');
insert into r_rp1853_prefix values ('132','ศจ.น.พ.','ศาสตราจารย์นายแพทย์');
insert into r_rp1853_prefix values ('133','พ.ญ.คุณหญิง','แพทย์หญิงคุณหญิง');
insert into r_rp1853_prefix values ('134','น.พ.','นายแพทย์');
insert into r_rp1853_prefix values ('135','พ.ญ.','แพทย์หญิง');
insert into r_rp1853_prefix values ('136','ท.พ.','ทัณตแพทย์');
insert into r_rp1853_prefix values ('137','ท.ญ.','ทัณตแพทย์หญิง');
insert into r_rp1853_prefix values ('138','สพ.','สัตวแพทย์'); 
insert into r_rp1853_prefix values ('139','สญ.','สัตวแพทย์หญิง');
insert into r_rp1853_prefix values ('140','ดร.','ดอกเตอร์');
insert into r_rp1853_prefix values ('141','ผศ.','ผู้ช่วยศาสตราจารย์');
insert into r_rp1853_prefix values ('142','รศ.','รองศาสตราจารย์');
insert into r_rp1853_prefix values ('143','ศจ.','ศาสตราจารย์');
insert into r_rp1853_prefix values ('144','ภก.','เภสัชกรชาย');
insert into r_rp1853_prefix values ('145','ภญ.','เภสัชกรหญิง');
insert into r_rp1853_prefix values ('146','หม่อม','หม่อม');
insert into r_rp1853_prefix values ('147','รองอำมาตย์เอก','รองอำมาตย์เอก');
insert into r_rp1853_prefix values ('148','ท้าว','ท้าว');
insert into r_rp1853_prefix values ('149','เจ้า','เจ้า');
insert into r_rp1853_prefix values ('150','ท่านผู้หญิง','ท่านผู้หญิง');
insert into r_rp1853_prefix values ('151','คุณพระ','คุณพระ');
insert into r_rp1853_prefix values ('152','ศจ.คุณหญิง','ศาสตราจารย์คุณหญิง');
insert into r_rp1853_prefix values ('153','ซิสเตอร์','ซิสเตอร์');
insert into r_rp1853_prefix values ('154','เจ้าชาย','เจ้าชาย');
insert into r_rp1853_prefix values ('155','เจ้าหญิง','เจ้าหญิง');
insert into r_rp1853_prefix values ('156','รองเสวกตรี','รองเสวกตรี');
insert into r_rp1853_prefix values ('157','เสด็จเจ้า','เสด็จเจ้า');
insert into r_rp1853_prefix values ('158','เอกอัครราชฑูต','เอกอัครราชฑูต');
insert into r_rp1853_prefix values ('159','พลสารวัตร','พลสารวัตร');
insert into r_rp1853_prefix values ('160','สมเด็จเจ้า','สมเด็จเจ้า');
insert into r_rp1853_prefix values ('161','เจ้าฟ้า','เจ้าฟ้า');
insert into r_rp1853_prefix values ('162','รองอำมาตย์ตรี','รองอำมาตย์ตรี');
insert into r_rp1853_prefix values ('163','ม.จ.หญิง','หม่อมเจ้าหญิง');
insert into r_rp1853_prefix values ('164','ทูลกระหม่อม','ทูลกระหม่อม');
insert into r_rp1853_prefix values ('165','ศ.ดร.','ศาสตราจารย์ดอกเตอร์');
insert into r_rp1853_prefix values ('166','เจ้านาง','เจ้านาง');
insert into r_rp1853_prefix values ('167','จ่าสำรอง','จ่าสำรอง');
insert into r_rp1853_prefix values ('200','พล.อ.','พลเอก');
insert into r_rp1853_prefix values ('201','ว่าที่ พล.อ.','ว่าที่พลเอก');
insert into r_rp1853_prefix values ('202','พล.ท.','พลโท');
insert into r_rp1853_prefix values ('204','พล.ต.','พลตรี');
insert into r_rp1853_prefix values ('205','ว่าที่ พล.ต.','ว่าที่พลตรี');
insert into r_rp1853_prefix values ('206','พ.อ.(พิเศษ)','พันเอกพิเศษ');
insert into r_rp1853_prefix values ('207','ว่าที่ พ.อ.(พิเศษ)','ว่าที่พันเอกพิเศษ');
insert into r_rp1853_prefix values ('208','พ.อ.','พันเอก');
insert into r_rp1853_prefix values ('209','ว่าที่ พ.อ.','ว่าที่พันเอก');
insert into r_rp1853_prefix values ('210','พ.ท.','พันโท');
insert into r_rp1853_prefix values ('211','ว่าที่ พ.ท.','ว่าที่พันโท');
insert into r_rp1853_prefix values ('212','พ.ต.','พันตรี');
insert into r_rp1853_prefix values ('213','ว่าที่ พ.ต.','ว่าที่พันตรี');
insert into r_rp1853_prefix values ('214','ร.อ.','ร้อยเอก');
insert into r_rp1853_prefix values ('215','ว่าที่ ร.อ.','ว่าที่ร้อยเอก');
insert into r_rp1853_prefix values ('216','ร.ท.','ร้อยโท');
insert into r_rp1853_prefix values ('217','ว่าที่ ร.ท.','ว่าที่ร้อยโท');
insert into r_rp1853_prefix values ('218','ร.ต.','ร้อยตรี');
insert into r_rp1853_prefix values ('219','ว่าที่ ร.ต.','ว่าที่ร้อยตรี');
insert into r_rp1853_prefix values ('220','จ.ส.อ.','จ่าสิบเอก');
insert into r_rp1853_prefix values ('221','จ.ส.ท.','จ่าสิบโท');
insert into r_rp1853_prefix values ('222','จ.ส.ต.','จ่าสิบตรี');
insert into r_rp1853_prefix values ('223','ส.อ.','สิบเอก');
insert into r_rp1853_prefix values ('224','ส.ท.','สิบโท');
insert into r_rp1853_prefix values ('225','ส.ต.','สิบตรี');
insert into r_rp1853_prefix values ('226','พลฯ','พลทหาร');
insert into r_rp1853_prefix values ('227','นนร.','นักเรียนนายร้อย');
insert into r_rp1853_prefix values ('228','นนส.','นักเรียนนายสิบ');
insert into r_rp1853_prefix values ('229','พล.จ.','พลจัตวา');
insert into r_rp1853_prefix values ('230','พลฯ อาสา','พลฯ อาสาสมัคร');
insert into r_rp1853_prefix values ('231','ร.อ.ม.จ.','ร้อยเอกหม่อมเจ้า');
insert into r_rp1853_prefix values ('232','พล.ท.ม.จ.','พลโทหม่อมเจ้า');
insert into r_rp1853_prefix values ('233','ร.ต.ม.จ.','ร้อยตรีหม่อมเจ้า');
insert into r_rp1853_prefix values ('234','ร.ท.ม.จ.','ร้อยโทหม่อมเจ้า');
insert into r_rp1853_prefix values ('235','พ.ท.ม.จ.','พันโทหม่อมเจ้า');
insert into r_rp1853_prefix values ('236','พ.อ.ม.จ.','พันเอกหม่อมเจ้า');
insert into r_rp1853_prefix values ('237','พ.ต.ม.ร.ว.','พันตรีหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('238','พ.ท.ม.ร.ว.','พันโทหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('239','ส.ต.ม.ร.ว.','สิบตรีหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('240','พ.อ.ม.ร.ว.','พันเอกหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('241','จ.ส.อ.ม.ร.ว.','จ่าสิบเอกหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('242','ร.อ.ม.ร.ว.','ร้อยเอกหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('243','ร.ต.ม.ร.ว.','ร้อยตรีหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('244','ส.อ.ม.ร.ว.','สิบเอกหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('245','ร.ท.ม.ร.ว.','ร้อยโทหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('246','พ.อ.(พิเศษ)ม.ร.ว.','พันเอก(พิเศษ)หม่อมราชวงศ์');
insert into r_rp1853_prefix values ('247','พลฯม.ล.','พลฯหม่อมหลวง');
insert into r_rp1853_prefix values ('248','ร.อ.ม.ล.','ร้อยเอกหม่อมหลวง');
insert into r_rp1853_prefix values ('249','ส.ท.ม.ล.','สิบโทหม่อมหลวง');
insert into r_rp1853_prefix values ('250','พล.ท.ม.ล.','พลโทหม่อมหลวง');
insert into r_rp1853_prefix values ('251','ร.ท.ม.ล.','ร้อยโทหม่อมหลวง');
insert into r_rp1853_prefix values ('252','ร.ต.ม.ล.','ร้อยตรีหม่อมหลวง');
insert into r_rp1853_prefix values ('253','ส.อ.ม.ล.','สิบเอกหม่อมหลวง');
insert into r_rp1853_prefix values ('254','พล.ต.ม.ล.','พลตรีหม่อมหลวง');
insert into r_rp1853_prefix values ('255','พ.ต.ม.ล.','พันตรีหม่อมหลวง');
insert into r_rp1853_prefix values ('256','พ.อ.ม.ล.','พันเอกหม่อมหลวง');
insert into r_rp1853_prefix values ('257','พ.ท.ม.ล.','พันโทหม่อมหลวง');
insert into r_rp1853_prefix values ('258','จ.ส.ต.ม.ล.','จ่าสิบตรีหม่อมหลวง');
insert into r_rp1853_prefix values ('259','นนร.ม.ล.','นักเรียนนายร้อยหม่อมหลวง');
insert into r_rp1853_prefix values ('260','ว่าที่ร.ต.ม.ล.','ว่าที่ร้อยตรีหม่อมหลวง');
insert into r_rp1853_prefix values ('261','จ.ส.อ.ม.ล.','จ่าสิบเอกหม่อมหลวง');
insert into r_rp1853_prefix values ('262','ร.อ.น.พ.','ร้อยเอกนายแพทย์');
insert into r_rp1853_prefix values ('263','ร.อ.พ.ญ.','ร้อยเอกแพทย์หญิง');
insert into r_rp1853_prefix values ('264','ร.ท.น.พ.','ร้อยโทนายแพทย์');
insert into r_rp1853_prefix values ('265','พ.ต.น.พ.','พันตรีนายแพทย์');
insert into r_rp1853_prefix values ('266','ว่าที่ ร.ท.น.พ.','ว่าที่ร้อยโทนายแพทย์');
insert into r_rp1853_prefix values ('267','พ.อ.น.พ.','พันเอกนายแพทย์');
insert into r_rp1853_prefix values ('268','ร.ต.น.พ.','ร้อยตรีนายแพทย์');
insert into r_rp1853_prefix values ('269','ร.ท.พ.ญ.','ร้อยโทแพทย์หญิง');
insert into r_rp1853_prefix values ('270','พล.ต.น.พ.','พลตรีนายแพทย์');
insert into r_rp1853_prefix values ('271','พ.ท.น.พ.','พันโทนายแพทย์');
insert into r_rp1853_prefix values ('272','จอมพล','จอมพล');
insert into r_rp1853_prefix values ('273','พ.ท.หลวง','พันโทหลวง'); 
insert into r_rp1853_prefix values ('274','พ.ต.พระเจ้าวรวงศ์เธอพระองค์เจ้','พตพระเจ้าวรวงศ์เธอพระองค์เจ้า');
insert into r_rp1853_prefix values ('275','ศจ.พ.อ.','ศาสตราจารย์พันเอก');
insert into r_rp1853_prefix values ('276','พ.ต.หลวง','พันตรีหลวง');
insert into r_rp1853_prefix values ('277','พล.ท.หลวง','พลโทหลวง');
insert into r_rp1853_prefix values ('278','ร.ท.ดร.','ร้อยโทดอกเตอร์');
insert into r_rp1853_prefix values ('279','ร.อ.ดร.','ร้อยเอกดอกเตอร์'); 
insert into r_rp1853_prefix values ('280','ส.ห.','สารวัตรทหาร');
insert into r_rp1853_prefix values ('281','ร.ต.ดร.','ร้อยตรีดอกเตอร์');
insert into r_rp1853_prefix values ('282','พ.ต.คุณหญิง','พันตรีคุณหญิง');
insert into r_rp1853_prefix values ('283','จ.ส.ต.ม.ร.ว.','จ่าสิบตรีหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('284','พล.จ.หลวง','พลจัตวาหลวง');
insert into r_rp1853_prefix values ('285','พล.ต.ม.ร.ว.','พลตมรว');
insert into r_rp1853_prefix values ('286','พ.ต.พระเจ้าวรวงศ์เธอพระองค์','พันตรีพระเจ้าวรวงศ์เธอพระองค์');
insert into r_rp1853_prefix values ('287','ท่านผู้หญิง ม.ร.ว.','ท่านผู้หญิงหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('288','ศจ.ร.อ.','ศาสตราจารย์ร้อยเอก');
insert into r_rp1853_prefix values ('289','พ.ท.คุณหญิง','พันโทคุณหญิง');
insert into r_rp1853_prefix values ('290','ร.ต.พ.ญ.','ร้อยตรีแพทย์หญิง');
insert into r_rp1853_prefix values ('291','พล.อ.มล.','พลเอกหม่อมหลวง');
insert into r_rp1853_prefix values ('292','ว่าที่ ร.ต.ม.ร.ว.','ว่าที่ร้อยตรีหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('293','พ.อ.หญิง คุณหญิง','พันเอกหญิงคุณหญิง');
insert into r_rp1853_prefix values ('294','จ.ส.อ.พิเศษ','จ่าสิบเอกพิเศษ');
insert into r_rp1853_prefix values ('351','พล.ร.อ.','พลเรือเอก');
insert into r_rp1853_prefix values ('352','ว่าที่ พล.ร.อ.','ว่าที่พลเรือเอก');
insert into r_rp1853_prefix values ('353','พล.ร.ท.','พลเรือโท');
insert into r_rp1853_prefix values ('354','ว่าที่ พล.ร.ท.','ว่าที่พลเรือโท');
insert into r_rp1853_prefix values ('355','พล.ร.ต.','พลเรือตรี');
insert into r_rp1853_prefix values ('356','ว่าที่ พล.ร.ต.','ว่าที่พลเรือตรี');
insert into r_rp1853_prefix values ('357','น.อ.พิเศษ','นาวาเอกพิเศษ');
insert into r_rp1853_prefix values ('358','ว่าที่ น.อ.พิเศษ','ว่าที่นาวาเอกพิเศษ');
insert into r_rp1853_prefix values ('359','น.อ.','นาวาเอก');
insert into r_rp1853_prefix values ('360','ว่าที่ น.อ.','ว่าที่นาวาเอก');
insert into r_rp1853_prefix values ('361','น.ท.','นาวาโท');
insert into r_rp1853_prefix values ('362','ว่าที่ น.ท.','ว่าที่นาวาโท');
insert into r_rp1853_prefix values ('363','น.ต.','นาวาตรี');
insert into r_rp1853_prefix values ('364','ว่าที่ น.ต.','ว่าที่นาวาตรี');
insert into r_rp1853_prefix values ('365','ร.อ.','เรือเอก');
insert into r_rp1853_prefix values ('366','ว่าที่ ร.อ.','ว่าที่เรือเอก');
insert into r_rp1853_prefix values ('367','ร.ท.','เรือโท');
insert into r_rp1853_prefix values ('368','ว่าที่ ร.ท.','ว่าที่เรือโท');
insert into r_rp1853_prefix values ('369','ร.ต.','เรือตรี');
insert into r_rp1853_prefix values ('370','ว่าที่ ร.ต.','ว่าที่เรือตรี');
insert into r_rp1853_prefix values ('371','พ.จ.อ.','พันจ่าเอก');
insert into r_rp1853_prefix values ('372','พ.จ.ท.','พันจ่าโท');
insert into r_rp1853_prefix values ('373','พ.จ.ต.','พันจ่าตรี');
insert into r_rp1853_prefix values ('374','จ.อ.','จ่าเอก');
insert into r_rp1853_prefix values ('375','จ.ท.','จ่าโท');
insert into r_rp1853_prefix values ('376','จ.ต.','จ่าตรี');
insert into r_rp1853_prefix values ('377','พลฯ','พลฯทหารเรือ');
insert into r_rp1853_prefix values ('378','นนร.','นักเรียนนายเรือ');
insert into r_rp1853_prefix values ('379','นรจ.','นักเรียนจ่าทหารเรือ');
insert into r_rp1853_prefix values ('380','พล.ร.จ.','พลเรือจัตวา');
insert into r_rp1853_prefix values ('381','น.ท.ม.จ.','นาวาโทหม่อมเจ้า');
insert into r_rp1853_prefix values ('382','น.อ.ม.จ.','นาวาเอกหม่อมเจ้า');
insert into r_rp1853_prefix values ('383','น.ต.ม.จ.','นาวาตรีหม่อมเจ้า');
insert into r_rp1853_prefix values ('384','พล.ร.ต.ม.ร.ว.','พลเรือตรีหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('385','น.อ.ม.ร.ว.','นาวาเอกหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('386','น.ท.ม.ร.ว.','นาวาโทหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('387','น.ต.ม.ร.ว.','นาวาตรีหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('388','น.ท.ม.ล.','นาวาโทหม่อมหลวง');
insert into r_rp1853_prefix values ('389','น.ต.ม.ล.','นาวาตรีหม่อมหลวง');
insert into r_rp1853_prefix values ('390','พ.จ.อ.ม.ล.','พันจ่าเอกหม่อมหลวง');
insert into r_rp1853_prefix values ('391','น.ต.พ.ญ.','นาวาตรีแพทย์หญิง');
insert into r_rp1853_prefix values ('392','น.อ.หลวง','นาวาอากาศเอกหลวง');
insert into r_rp1853_prefix values ('393','พล.ร.ต.ม.จ.','พลเรือตรีหม่อมเจ้า');
insert into r_rp1853_prefix values ('395','น.ต.น.พ.','นาวาตรีนายแพทย์');
insert into r_rp1853_prefix values ('396','พล.ร.ต.ม.ล.','พลเรือตรีหม่อมหลวง');
insert into r_rp1853_prefix values ('500','พล.อ.อ.','พลอากาศเอก');
insert into r_rp1853_prefix values ('501','ว่าที่ พล.อ.อ.','ว่าที่พลอากาศเอก');
insert into r_rp1853_prefix values ('502','พล.อ.ท.','พลอากาศโท');
insert into r_rp1853_prefix values ('503','ว่าที่ พล.อ.ท.','ว่าที่พลอากาศโท');
insert into r_rp1853_prefix values ('504','พล.อ.ต.','พลอากาศตรี');
insert into r_rp1853_prefix values ('505','ว่าที่ พล.อ.ต.','ว่าที่พลอากาศตรี');
insert into r_rp1853_prefix values ('506','น.อ.พิเศษ','นาวาอากาศเอกพิเศษ');
insert into r_rp1853_prefix values ('507','ว่าที่ น.อ.พิเศษ','ว่าที่นาวาอากาศเอกพิเศษ');
insert into r_rp1853_prefix values ('508','น.อ.','นาวาอากาศเอก');
insert into r_rp1853_prefix values ('509','ว่าที่ น.อ.','ว่าที่นาวาอากาศเอก');
insert into r_rp1853_prefix values ('510','น.ท.','นาวาอากาศโท');
insert into r_rp1853_prefix values ('511','ว่าที่ น.ท.','ว่าที่นาวาอากาศโท');
insert into r_rp1853_prefix values ('512','น.ต.','นาวาอากาศตรี');
insert into r_rp1853_prefix values ('513','ว่าที่ น.ต.','ว่าที่นาวาอากาศตรี');
insert into r_rp1853_prefix values ('514','ร.อ.','เรืออากาศเอก');
insert into r_rp1853_prefix values ('515','ว่าที่ ร.อ.','ว่าที่เรืออากาศเอก');
insert into r_rp1853_prefix values ('516','ร.ท.','เรืออากาศโท');
insert into r_rp1853_prefix values ('517','ว่าที่ ร.ท.','ว่าที่เรืออากาศโท');
insert into r_rp1853_prefix values ('518','ร.ต.','เรืออากาศตรี');
insert into r_rp1853_prefix values ('519','ว่าที่ ร.ต.','ว่าที่เรืออากาศตรี');
insert into r_rp1853_prefix values ('520','พ.อ.อ.','พันจ่าอากาศเอก');
insert into r_rp1853_prefix values ('521','พ.อ.ท.','พันจ่าอากาศโท');
insert into r_rp1853_prefix values ('522','พ.อ.ต.','พันจ่าอากาศตรี');
insert into r_rp1853_prefix values ('523','จ.อ.','จ่าอากาศเอก');
insert into r_rp1853_prefix values ('524','จ.ท.','จ่าอากาศโท');
insert into r_rp1853_prefix values ('525','จ.ต.','จ่าอากาศตรี');
insert into r_rp1853_prefix values ('526','พลฯ','พลฯทหารอากาศ');
insert into r_rp1853_prefix values ('527','นนอ.','นักเรียนนายเรืออากาศ');
insert into r_rp1853_prefix values ('528','นจอ.','นักเรียนจ่าอากาศ');
insert into r_rp1853_prefix values ('529','น.พ.อ.','นักเรียนพยาบาลทหารอากาศ');
insert into r_rp1853_prefix values ('530','พ.อ.อ.ม.ร.ว.','พันอากาศเอกหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('531','พล.อ.ต.ม.ร.ว.','พลอากาศตรีหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('532','พล.อ.ท.ม.ล.','พลอากาศโทหม่อมหลวง');
insert into r_rp1853_prefix values ('533','ร.ท.ขุน','เรืออากาศโทขุน');
insert into r_rp1853_prefix values ('534','พ.อ.อ.ม.ล.','พันจ่าอากาศเอกหม่อมหลวง');
insert into r_rp1853_prefix values ('535','ร.อ.น.พ.','เรืออากาศเอกนายแพทย์');
insert into r_rp1853_prefix values ('536','พล.อ.อ.ม.ร.ว.','พล.อ.อ.ม.ร.ว.');
insert into r_rp1853_prefix values ('537','พล.อ.ต.ม.ล.','พลอากาศตรีหม่อมหลวง');
insert into r_rp1853_prefix values ('538','พล.อ.จ.','พลอากาศจัตวา');
insert into r_rp1853_prefix values ('539','พล.อ.ท.ม.ร.ว.','พลอากาศโทหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('540','น.อ.ม.ล.','นาวาอากาศเอกหม่อมหลวง');
insert into r_rp1853_prefix values ('606','พระครูพิบูลสมณธรรม','พระครูพิบูลสมณธรรม');
insert into r_rp1853_prefix values ('651','พล.ต.อ.','พลตำรวจเอก');
insert into r_rp1853_prefix values ('652','ว่าที่ พล.ต.อ.','ว่าที่พลตำรวจเอก');
insert into r_rp1853_prefix values ('653','พล.ต.ท.','พลตำรวจโท');
insert into r_rp1853_prefix values ('654','ว่าที่ พล.ต.ท.','ว่าที่พลตำรวจโท');
insert into r_rp1853_prefix values ('655','พล.ต.ต.','พลตำรวจตรี');
insert into r_rp1853_prefix values ('656','ว่าที่ พล.ต.ต.','ว่าที่พลตำรวจตรี');
insert into r_rp1853_prefix values ('657','พล.ต.จ.','พลตำรวจจัตวา');
insert into r_rp1853_prefix values ('658','ว่าที่พล.ต.จ.','ว่าที่พลตำรวจจัตวา');
insert into r_rp1853_prefix values ('659','พ.ต.อ. (พิเศษ)','พันตำรวจเอก (พิเศษ)');
insert into r_rp1853_prefix values ('660','ว่าที่ พ.ต.อ.พิเศษ','ว่าที่พันตำรวจเอก(พิเศษ)');
insert into r_rp1853_prefix values ('661','พ.ต.อ.','พันตำรวจเอก');
insert into r_rp1853_prefix values ('662','ว่าที่ พ.ต.อ.','ว่าที่พันตำรวจเอก');
insert into r_rp1853_prefix values ('663','พ.ต.ท.','พันตำรวจโท');
insert into r_rp1853_prefix values ('664','ว่าที่ พ.ต.ท.','ว่าที่พันตำรวจโท');
insert into r_rp1853_prefix values ('665','พ.ต.ต.','พันตำรวจตรี');
insert into r_rp1853_prefix values ('666','ว่าที่ พ.ต.ต.','ว่าที่พันตำรวจตรี');
insert into r_rp1853_prefix values ('667','ร.ต.อ.','ร้อยตำรวจเอก');
insert into r_rp1853_prefix values ('668','ว่าที่ ร.ต.อ.','ว่าที่ร้อยตำรวจเอก');
insert into r_rp1853_prefix values ('669','ร.ต.ท.','ร้อยตำรวจโท');
insert into r_rp1853_prefix values ('670','ว่าที่ ร.ต.ท.','ว่าที่ร้อยตำรวจโท');
insert into r_rp1853_prefix values ('671','ร.ต.ต.','ร้อยตำรวจตรี');
insert into r_rp1853_prefix values ('672','ว่าที่ ร.ต.ต.','ว่าที่ร้อยตำรวจตรี');
insert into r_rp1853_prefix values ('673','ด.ต.','นายดาบตำรวจ');
insert into r_rp1853_prefix values ('674','จ.ส.ต.','จ่าสิบตำรวจ');
insert into r_rp1853_prefix values ('675','ส.ต.อ.','สิบตำรวจเอก');
insert into r_rp1853_prefix values ('676','ส.ต.ท.','สิบตำรวจโท');
insert into r_rp1853_prefix values ('677','ส.ต.ต.','สิบตำรวจตรี');
insert into r_rp1853_prefix values ('678','นรต.','นักเรียนนายร้อยตำรวจ');
insert into r_rp1853_prefix values ('679','นสต.','นักเรียนนายสิบตำรวจ');
insert into r_rp1853_prefix values ('680','นพต.','นักเรียนพลตำรวจ');
insert into r_rp1853_prefix values ('681','พลฯ','พลตำรวจ');
insert into r_rp1853_prefix values ('682','พลฯพิเศษ','พลตำรวจพิเศษ');
insert into r_rp1853_prefix values ('683','พลฯอาสา','พลตำรวจอาสาสมัคร');
insert into r_rp1853_prefix values ('684','พลฯสำรอง','พลตำรวจสำรอง');
insert into r_rp1853_prefix values ('685','พลฯสำรองพิเศษ','พลตำรวจสำรองพิเศษ');
insert into r_rp1853_prefix values ('686','พลฯสมัคร','พลตำรวจสมัคร');
insert into r_rp1853_prefix values ('687','อส.','สมาชิกอาสารักษาดินแดน');
insert into r_rp1853_prefix values ('688','ก.ญ.','นายกองใหญ่');
insert into r_rp1853_prefix values ('689','ก.อ.','นายกองเอก');
insert into r_rp1853_prefix values ('690','ก.ท.','นายกองโท');
insert into r_rp1853_prefix values ('691','ก.ต.','นายกองตรี');
insert into r_rp1853_prefix values ('692','มว.อ.','นายหมวดเอก');
insert into r_rp1853_prefix values ('693','มว.ท.','นายหมวดโท');
insert into r_rp1853_prefix values ('694','มว.ต.','นายหมวดตรี');
insert into r_rp1853_prefix values ('695','ม.ญ.','นายหมู่ใหญ่');
insert into r_rp1853_prefix values ('696','ม.อ.','นายหมู่เอก');
insert into r_rp1853_prefix values ('697','ม.ท.','นายหมู่โท');
insert into r_rp1853_prefix values ('698','ม.ต.','นายหมู่ตรี');
insert into r_rp1853_prefix values ('699','สมาชิกเอก','สมาชิกเอก');
insert into r_rp1853_prefix values ('700','สมาชิกโท','สมาชิกโท');
insert into r_rp1853_prefix values ('701','สมาชิกตรี','สมาชิกตรี');
insert into r_rp1853_prefix values ('702','อส.ทพ.','อาสาสมัครทหารพราน');
insert into r_rp1853_prefix values ('703','พ.ต.ท.ม.จ.','พันตำรวจโทหม่อมเจ้า'); 
insert into r_rp1853_prefix values ('704','พ.ต.อ.ม.จ.','พันตำรวจเอกหม่อมเจ้า');
insert into r_rp1853_prefix values ('705','นรต.ม.จ.','นักเรียนนายร้อยตำรวจหม่อมเจ้า');
insert into r_rp1853_prefix values ('706','พล.ต.ต.ม.ร.ว.','พลตำรวจตรีหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('707','พ.ต.ต.ม.ร.ว.','พันตำรวจตรีหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('708','พ.ต.ท.ม.ร.ว.','พันตำรวจโทหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('709','พ.ต.อ.ม.ร.ว.','พันตำรวจเอกหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('710','ร.ต.อ.ม.ร.ว.','ร้อยตำรวจเอกหม่อมราชวงศ์');
insert into r_rp1853_prefix values ('711','ส.ต.อ.ม.ล.','สิบตำรวจเอกหม่อมหลวง');
insert into r_rp1853_prefix values ('712','พ.ต.อ.ม.ล.','พันตำรวจเอกหม่อมหลวง');
insert into r_rp1853_prefix values ('713','พ.ต.ท.ม.ล.','พันตำรวจโทหม่อมหลวง');
insert into r_rp1853_prefix values ('714','นรต.ม.ล.','นักเรียนนายร้อยตำรวจหม่อมหลวง');
insert into r_rp1853_prefix values ('715','ร.ต.ท.ม.ล.','ร้อยตำรวจโทหม่อมหลวง');
insert into r_rp1853_prefix values ('716','ด.ต.ม.ล.','นายดาบตำรวจหม่อมหลวง');
insert into r_rp1853_prefix values ('717','พ.ต.ต.ม.ล.','พันตำรวจตรีหม่อมหลวง');
insert into r_rp1853_prefix values ('718','ศจ.น.พ.พ.ต.อ.','ศาสตราจารย์นายแพทย์พันตำรวจเอก');
insert into r_rp1853_prefix values ('719','พ.ต.ท.น.พ.','พันตำรวจโทนายแพทย์');
insert into r_rp1853_prefix values ('720','ร.ต.ท.น.พ.','ร้อยตำรวจโทนายแพทย์');
insert into r_rp1853_prefix values ('721','ร.ต.อ.น.พ.','ร้อยตำรวจเอกนายแพทย์');
insert into r_rp1853_prefix values ('722','พ.ต.ต.นพ.','พันตำรวจตรีนายแพทย์');
insert into r_rp1853_prefix values ('723','พ.ต.อ.น.พ.','พันตำรวจเอกนายแพทย์');
insert into r_rp1853_prefix values ('724','พ.ต.ต.หลวง','พันตำรวจตรีหลวง');
insert into r_rp1853_prefix values ('725','ร.ต.ท.ดร.','ร้อยตำรวจโทดอกเตอร์');
insert into r_rp1853_prefix values ('726','พ.ต.อ.ดร.','พันตำรวจเอกดอกเตอร์');
insert into r_rp1853_prefix values ('727','ร.ต.อ.ม.ล.','ร้อยตำรวจเอกหม่อมหลวง');
insert into r_rp1853_prefix values ('729','พ.ต.อ.หญิง ท่านผู้หญิง','พันตำรวจเอกหญิง ท่านผู้หญิง');
insert into r_rp1853_prefix values ('730','พล.ต.ต.ม.ล.','พลตำรวจตรีหม่อมหลวง');
insert into r_rp1853_prefix values ('731','พล.ต.หญิง คุณหญิง','พล.ต.หญิง คุณหญิง');
insert into r_rp1853_prefix values ('732','ว่าที่ ส.อ.','ว่าที่สิบเอก');
insert into r_rp1853_prefix values ('733','พล.ต.อ.ดร.','พลตำรวจเอกดอกเตอร์');
insert into r_rp1853_prefix values ('800','สมเด็จพระสังฆราชเจ้า','สมเด็จพระสังฆราชเจ้า');
insert into r_rp1853_prefix values ('801','สมเด็จพระสังฆราช','สมเด็จพระสังฆราช');
insert into r_rp1853_prefix values ('802','สมเด็จพระราชาคณะ','สมเด็จพระราชาคณะ');
insert into r_rp1853_prefix values ('803','รองสมเด็จพระราชาคณะ','รองสมเด็จพระราชาคณะ');
insert into r_rp1853_prefix values ('804','พระราชาคณะ','พระราชาคณะ');
insert into r_rp1853_prefix values ('805','พระเปรียญธรรม','พระเปรียญธรรม');
insert into r_rp1853_prefix values ('806','พระหิรัญยบัฏ','พระหิรัญยบัฏ');
insert into r_rp1853_prefix values ('807','พระสัญญาบัตร','พระสัญญาบัตร');
insert into r_rp1853_prefix values ('808','พระราช','พระราช');
insert into r_rp1853_prefix values ('809','พระเทพ','พระเทพ');
insert into r_rp1853_prefix values ('810','พระปลัดขวา','พระปลัดขวา');
insert into r_rp1853_prefix values ('811','พระปลัดซ้าย','พระปลัดซ้าย');
insert into r_rp1853_prefix values ('812','พระครูปลัด','พระครูปลัด');
insert into r_rp1853_prefix values ('813','พระครูปลัดสุวัฒนญาณคุณ','พระครูปลัดสุวัฒนญาณคุณ');
insert into r_rp1853_prefix values ('814','พระครูปลัดอาจารย์วัฒน์','พระครูปลัดอาจารย์วัฒน์');
insert into r_rp1853_prefix values ('815','พระครูปลัดวิมลสิริวัฒน์','พระครูวิมลสิริวัฒน์');
insert into r_rp1853_prefix values ('816','พระสมุห์','พระสมุห์');
insert into r_rp1853_prefix values ('817','พระครูสมุห์','พระครูสมุห์');
insert into r_rp1853_prefix values ('818','พระครู','พระครู');
insert into r_rp1853_prefix values ('819','พระครูใบฎีกา','พระครูใบฎีกา');
insert into r_rp1853_prefix values ('820','พระครูธรรมธร','พระครูธรรมธร');
insert into r_rp1853_prefix values ('821','พระครูวิมลภาณ','พระครูวิมลภาณ');
insert into r_rp1853_prefix values ('822','พระครูศัพทมงคล','พระครูศัพทมงคล');
insert into r_rp1853_prefix values ('823','พระครูสังฆภารวิชัย','พระครูสังฆภารวิชัย');
insert into r_rp1853_prefix values ('824','พระครูสังฆรักษ์','พระครูสังฆรักษ์');
insert into r_rp1853_prefix values ('825','พระครูสังฆวิชัย','พระครูสังฆวิชัย');
insert into r_rp1853_prefix values ('826','พระครูสังฆวิชิต','พระครูสังฆวิชิต');
insert into r_rp1853_prefix values ('827','พระปิฎก','พระปิฎก');
insert into r_rp1853_prefix values ('828','พระปริยัติ','พระปริยัติ');
insert into r_rp1853_prefix values ('829','เจ้าอธิการ','เจ้าอธิการ');
insert into r_rp1853_prefix values ('830','พระอธิการ','พระอธิการ');
insert into r_rp1853_prefix values ('831','พระ','พระ');
insert into r_rp1853_prefix values ('832','ส.ณ.','สามเณร');
insert into r_rp1853_prefix values ('833','พระปลัด','พระปลัด');
insert into r_rp1853_prefix values ('834','สมเด็จพระอริยวงศาคตญาณ','สมเด็จพระอริยวงศาคตญาณ');
insert into r_rp1853_prefix values ('835','พระคาร์ดินัล','พระคาร์ดินัล');
insert into r_rp1853_prefix values ('836','พระสังฆราช','พระสังฆราช');
insert into r_rp1853_prefix values ('837','พระคุณเจ้า','พระคุณเจ้า');
insert into r_rp1853_prefix values ('838','บาทหลวง','บาทหลวง');
insert into r_rp1853_prefix values ('839','พระมหา','พระมหา');
insert into r_rp1853_prefix values ('840','พระราชปัญญา','พระราชปัญญา');
insert into r_rp1853_prefix values ('841','ภาราดา','ภาราดา');
insert into r_rp1853_prefix values ('842','พระศรีปริยัติธาดา','พระศรีปริยัติธาดา');
insert into r_rp1853_prefix values ('843','พระญาณโศภณ','พระญาณโศภณ');
insert into r_rp1853_prefix values ('844','สมเด็จพระมหาวีรวงศ์','สมเด็จพระมหาวีรวงศ์');
insert into r_rp1853_prefix values ('845','พระโสภณธรรมาภรณ์','พระโสภณธรรมาภรณ์');
insert into r_rp1853_prefix values ('846','พระวิริวัฒน์วิสุทธิ์','พระวิริวัฒน์วิสุทธิ์');
insert into r_rp1853_prefix values ('847','พระญาณ','พระญาณ');
insert into r_rp1853_prefix values ('848','พระอัครสังฆราช','พระอัครสังฆราช');
insert into r_rp1853_prefix values ('849','พระธรรม','พระธรรม');
insert into r_rp1853_prefix values ('850','พระสาสนโสภณ','พระสาสนโสภณ');
insert into r_rp1853_prefix values ('851','พระธรรมโสภณ','พระธรรมโสภณ');
insert into r_rp1853_prefix values ('852','พระอุดมสารโสภณ','พระอุดมสารโสภณ');
insert into r_rp1853_prefix values ('853','พระครูวิมลญาณโสภณ','พระครูวิมลญาณโสภณ');
insert into r_rp1853_prefix values ('854','พระครูปัญญาภรณโสภณ','พระครูปัญญาภรณโสภณ');
insert into r_rp1853_prefix values ('855','พระครูโสภณปริยัติคุณ','พระครูโสภณปริยัติคุณ');
insert into r_rp1853_prefix values ('856','พระอธิธรรม','พระอธิธรรม');
insert into r_rp1853_prefix values ('857','พระราชญาณ','พระราชญาณ');
insert into r_rp1853_prefix values ('858','พระสุธีวัชโรดม','พระสุธีวัชโรดม');
insert into r_rp1853_prefix values ('859','รองเจ้าอธิการ','รองเจ้าอธิการ');
insert into r_rp1853_prefix values ('860','พระครูวินัยธร','พระครูวินัยธร');
insert into r_rp1853_prefix values ('861','พระศรีวชิราภรณ์','พระศรีวชิราภรณ์');
insert into r_rp1853_prefix values ('862','พระราชบัณฑิต','พระราชบัณฑิต');
insert into r_rp1853_prefix values ('863','แม่ชี','แม่ชี');
insert into r_rp1853_prefix values ('864','นักบวช','นักบวช');
insert into r_rp1853_prefix values ('865','พระรัตน','พระรัตน');
insert into r_rp1853_prefix values ('866','พระโสภณปริยัติธรรม','พระโสภณปริยัติธรรม');
insert into r_rp1853_prefix values ('867','พระครูวิศาลปัญญาคุณ','พระครูวิศาลปัญญาคุณ');
insert into r_rp1853_prefix values ('868','พระศรีปริยัติโมลี','พระศรีปริยัติโมลี');
insert into r_rp1853_prefix values ('869','พระครูวัชรสีลาภรณ์','พระครูวัชรสีลาภรณ์');
insert into r_rp1853_prefix values ('870','พระครูพิพัฒน์บรรณกิจ','พระครูพิพัฒน์บรรณกิจ');
insert into r_rp1853_prefix values ('871','พระครูวิบูลธรรมกิจ','พระครูวิบูลธรรมกิจ');
insert into r_rp1853_prefix values ('872','พระครูพัฒนสารคุณ','พระครูพัฒนสารคุณ');
insert into r_rp1853_prefix values ('873','พระครูสุวรรณพัฒนคุณ','พระครูสุวรรณพัฒนคุณ');
insert into r_rp1853_prefix values ('874','พระครูพรหมวีรสุนทร','พระครูพรหมวีรสุนทร');
insert into r_rp1853_prefix values ('875','พระครูอุปถัมภ์นันทกิจ','พระครูอุปถัมภ์นันทกิจ');
insert into r_rp1853_prefix values ('876','พระครูวิจารณ์สังฆกิจ','พระครูวิจารณ์สังฆกิจ');
insert into r_rp1853_prefix values ('877','พระครูวิมลสารวิสุทธิ์','พระครูวิมลสารวิสุทธิ์');
insert into r_rp1853_prefix values ('878','พระครูไพศาลศุภกิจ','พระครูไพศาลศุภกิจ');
insert into r_rp1853_prefix values ('879','พระครูโอภาสธรรมพิมล','พระครูโอภาสธรรมพิมล');
insert into r_rp1853_prefix values ('880','พระครูพิพิธวรคุณ','พระครูพิพิธวรคุณ');
insert into r_rp1853_prefix values ('881','พระครูสุนทรปภากร','พระครูสุนทรปภากร');
insert into r_rp1853_prefix values ('882','พระครูสิริชัยสถิต','พระครูสิริชัยสถิต');
insert into r_rp1853_prefix values ('883','พระครูเกษมธรรมานันท์','พระครูเกษมธรรมานันท์');
insert into r_rp1853_prefix values ('884','พระครูถาวรสันติคุณ','พระครูถาวรสันติคุณ');
insert into r_rp1853_prefix values ('885','พระครูวิสุทธาจารวิมล','พระครูวิสุทธาจารวิมล');
insert into r_rp1853_prefix values ('886','พระครูปภัสสราธิคุณ','พระครูปภัสสราธิคุณ');
insert into r_rp1853_prefix values ('887','พระครูวรสังฆกิจ','พระครูวรสังฆกิจ');
insert into r_rp1853_prefix values ('888','พระครูไพบูลชัยสิทธิ์','พระครูไพบูลชัยสิทธิ์');
insert into r_rp1853_prefix values ('889','พระครูโกวิทธรรมโสภณ','พระครูโกวิทธรรมโสภณ');
insert into r_rp1853_prefix values ('890','พระครูสุพจน์วราภรณ์','พระครูสุพจน์วราภรณ์');
insert into r_rp1853_prefix values ('891','พระครูไพโรจน์อริญชัย','พระครูไพโรจน์อริญชัย');
insert into r_rp1853_prefix values ('892','พระครูสุนทรคณาภิรักษ์','พระครูสุนทรคณาภิรักษ์');
insert into r_rp1853_prefix values ('893','พระสรภาณโกศล','พระสรภาณโกศล');
insert into r_rp1853_prefix values ('894','พระครูประโชติธรรมรัตน์','พระครูประโชติธรรมรัตน์');
insert into r_rp1853_prefix values ('895','พระครูจารุธรรมกิตติ์','พระครูจารุธรรมกิตติ์');
insert into r_rp1853_prefix values ('896','พระครูพิทักษ์พรหมรังษี','พระครูพิทักษ์พรหมรังษี');
insert into r_rp1853_prefix values ('897','พระศรีปริยัติบัณฑิต','พระศรีปริยัติบัณฑิต');
insert into r_rp1853_prefix values ('898','พระครูพุทธิธรรมานุศาสน์','พระครูพุทธิธรรมานุศาสน์');
insert into r_rp1853_prefix values ('899','พระธรรมเมธาจารย์','พระธรรมเมธาจารย์');
insert into r_rp1853_prefix values ('900','พระครูกิตติกาญจนวงศ์','พระครูกิตติกาญจนวงศ์');
insert into r_rp1853_prefix values ('901','พระครูปลัดสัมพิพัฒนวิริยาจารย์','พระครูปลัดสัมพิพัฒนวิริยาจารย์');
insert into r_rp1853_prefix values ('902','พระครูศีลกันตาภรณ์','พระครูศีลกันตาภรณ์');
insert into r_rp1853_prefix values ('903','พระครูประกาศพุทธพากย์','พระครูประกาศพุทธพากย์');
insert into r_rp1853_prefix values ('904','พระครูอมรวิสุทธิคุณ','พระครูอมรวิสุทธิคุณ');
insert into r_rp1853_prefix values ('905','พระครูสุทัศน์ธรรมาภิรม','พระครูสุทัศน์ธรรมาภิรม');
insert into r_rp1853_prefix values ('906','พระครูอุปถัมภ์วชิโรภาส','พระครูอุปถัมภ์วชิโรภาส');
insert into r_rp1853_prefix values ('907','พระครูสุนทรสมณคุณ','พระครูสุนทรสมณคุณ');
insert into r_rp1853_prefix values ('908','พระพรหมมุนี','พระพรหมมุนี');
insert into r_rp1853_prefix values ('909','พระครูสิริคุณารักษ์','พระครูสิริคุณารักษ์');
insert into r_rp1853_prefix values ('910','พระครูวิชิตพัฒนคุณ','พระครูวิชิตพัฒนคุณ');
insert into r_rp1853_prefix values ('911','พระครูพิบูลโชติธรรม','พระครูพิบูลโชติธรรม');
insert into r_rp1853_prefix values ('912','พระพิศาลสารคุณ','พระพิศาลสารคุณ');
insert into r_rp1853_prefix values ('913','พระรัตนมงคลวิสุทธ์','พระรัตนมงคลวิสุทธ์');
insert into r_rp1853_prefix values ('914','พระครูโสภณคุณานุกูล','พระครูโสภณคุณานุกูล');
insert into r_rp1853_prefix values ('915','พระครูผาสุกวิหารการ','พระครูผาสุกวิหารการ');
insert into r_rp1853_prefix values ('916','พระครูวชิรวุฒิกร','พระครูวชิรวุฒิกร');
insert into r_rp1853_prefix values ('917','พระครูกาญจนยติกิจ','พระครูกาญจนยติกิจ');
insert into r_rp1853_prefix values ('918','พระครูบวรรัตนวงศ์','พระครูบวรรัตนวงศ์');
insert into r_rp1853_prefix values ('919','พระราชพัชราภรณ์','พระราชพัชราภรณ์');
insert into r_rp1853_prefix values ('920','พระครูพิพิธอุดมคุณ','พระครูพิพิธอุดมคุณ');
insert into r_rp1853_prefix values ('921','องสุตบทบวร','องสุตบทบวร');
insert into r_rp1853_prefix values ('922','พระครูจันทเขมคุณ','พระครูจันทเขมคุณ');
insert into r_rp1853_prefix values ('923','พระครูศีลสารวิสุทธิ์','พระครูศีลสารวิสุทธิ์');
insert into r_rp1853_prefix values ('924','พระครูสุธรรมโสภิต','พระครูสุธรรมโสภิต');
insert into r_rp1853_prefix values ('925','พระครูอุเทศธรรมนิวิฐ','พระครูอุเทศธรรมนิวิฐ');
insert into r_rp1853_prefix values ('926','พระครูบรรณวัตร','พระครูบรรณวัตร');
insert into r_rp1853_prefix values ('927','พระครูวิสุทธาจาร','พระครูวิสุทธาจาร');
insert into r_rp1853_prefix values ('928','พระครูสุนทรวรวัฒน์','พระครูสุนทรวรวัฒน์');
insert into r_rp1853_prefix values ('929','พระเทพชลธารมุนี ศรีชลบุราจารย์','พระเทพชลธารมุนี ศรีชลบุราจารย์');
insert into r_rp1853_prefix values ('930','พระครูโสภณสมุทรคุณ','พระครูโสภณสมุทรคุณ');
insert into r_rp1853_prefix values ('931','พระราชเมธาภรณ์','พระราชเมธาภรณ์');
insert into r_rp1853_prefix values ('932','พระครูศรัทธาธรรมโสภณ','พระครูศรัทธาธรรมโสภณ');
insert into r_rp1853_prefix values ('933','พระครูสังฆบริรักษ์','พระครูสังฆบริรักษ์');
insert into r_rp1853_prefix values ('934','พระมหานายก','พระมหานายก');
insert into r_rp1853_prefix values ('935','พระครูโอภาสสมาจาร','พระครูโอภาสสมาจาร');
insert into r_rp1853_prefix values ('936','พระครูศรีธวัชคุณาภรณ์','พระครูศรีธวัชคุณาภรณ์');
insert into r_rp1853_prefix values ('937','พระครูโสภิตวัชรกิจ','พระครูโสภิตวัชรกิจ');
insert into r_rp1853_prefix values ('938','พระราชวชิราภรณ์','พระราชวชิราภรณ์');
insert into r_rp1853_prefix values ('939','พระครูสุนทรวรธัช','พระครูสุนทรวรธัช');
insert into r_rp1853_prefix values ('940','พระครูอาทรโพธิกิจ','พระครูอาทรโพธิกิจ');
insert into r_rp1853_prefix values ('941','พระครูวิบูลกาญจนกิจ','พระครูวิบูลกาญจนกิจ');
insert into r_rp1853_prefix values ('942','พระพรหมวชิรญาณ','พระพรหมวชิรญาณ');
insert into r_rp1853_prefix values ('943','พระครูสุพจน์วรคุณ','พระครูสุพจน์วรคุณ');
insert into r_rp1853_prefix values ('944','พระราชวิมลโมลี','พระราชาวิมลโมลี');
insert into r_rp1853_prefix values ('945','พระครูอมรธรรมนายก','พระครูอมรธรรมนายก');
insert into r_rp1853_prefix values ('946','พระครูพิศิษฎ์ศาสนการ','พระครูพิศิษฎ์ศาสนการ');
insert into r_rp1853_prefix values ('947','พระครูเมธีธรรมานุยุต','พระครูเมธีธรรมานุยุต');
insert into r_rp1853_prefix values ('948','พระครูปิยสีลสาร','พระครูปิยสีลสาร');
insert into r_rp1853_prefix values ('949','พระครูสถิตบุญวัฒน์','พระครูสถิตบุญวัฒน์');
insert into r_rp1853_prefix values ('950','พระครูนิเทศปิยธรรม','พระครูนิเทศปิยธรรม');
insert into r_rp1853_prefix values ('951','พระครูวิสุทธิ์กิจจานุกูล','พระครูวิสุทธิ์กิจจานุกูล');
insert into r_rp1853_prefix values ('952','พระครูสถิตย์บุญวัฒน์','พระครูสถิตย์บุญวัฒน์');
insert into r_rp1853_prefix values ('953','พระครูประโชติธรรมานุกูล','พระครูประโชติธรรมานุกูล');
insert into r_rp1853_prefix values ('954','พระเทพญาณกวี','พระเทพญาณกวี');
insert into r_rp1853_prefix values ('955','พระครูพิพัฒน์ชินวงศ์','พระครูพิพัฒน์ชินวงศ์');
insert into r_rp1853_prefix values ('956','พระครูสมุทรขันตยาภรณ์','พระครูสมุทรขันตยาภรณ์');
insert into r_rp1853_prefix values ('957','พระครูภาวนาวรกิจ','พระครูภาวนาวรกิจ');
insert into r_rp1853_prefix values ('958','พระครูศรีศาสนคุณ','พระครูศรีศาสนคุณ');
insert into r_rp1853_prefix values ('959','พระครูวิบูลย์ธรรมศาสก์','พระครูวิบูลย์ธรรมศาสก์');
-------------------------------------------------------
update f_patient_prefix set r_rp1853_prefix_id = '1' where f_patient_prefix_id = '001';
update f_patient_prefix set r_rp1853_prefix_id = '2' where f_patient_prefix_id = '002';
update f_patient_prefix set r_rp1853_prefix_id = '3' where f_patient_prefix_id = '003';
update f_patient_prefix set r_rp1853_prefix_id = '4' where f_patient_prefix_id = '004';
update f_patient_prefix set r_rp1853_prefix_id = '5' where f_patient_prefix_id = '005';
update f_patient_prefix set r_rp1853_prefix_id = '120' where f_patient_prefix_id = '120';
update f_patient_prefix set r_rp1853_prefix_id = '121' where f_patient_prefix_id = '121';
update f_patient_prefix set r_rp1853_prefix_id = '122' where f_patient_prefix_id = '122';
update f_patient_prefix set r_rp1853_prefix_id = '129' where f_patient_prefix_id = '129';
update f_patient_prefix set r_rp1853_prefix_id = '132' where f_patient_prefix_id = '132';
update f_patient_prefix set r_rp1853_prefix_id = '134' where f_patient_prefix_id = '134';
update f_patient_prefix set r_rp1853_prefix_id = '136' where f_patient_prefix_id = '136';
update f_patient_prefix set r_rp1853_prefix_id = '140' where f_patient_prefix_id = '140';
update f_patient_prefix set r_rp1853_prefix_id = '146' where f_patient_prefix_id = '146';
update f_patient_prefix set r_rp1853_prefix_id = '208' where f_patient_prefix_id = '208';
update f_patient_prefix set r_rp1853_prefix_id = '209' where f_patient_prefix_id = '209';
update f_patient_prefix set r_rp1853_prefix_id = '210' where f_patient_prefix_id = '210';
update f_patient_prefix set r_rp1853_prefix_id = '211' where f_patient_prefix_id = '211';
update f_patient_prefix set r_rp1853_prefix_id = '212' where f_patient_prefix_id = '212';
update f_patient_prefix set r_rp1853_prefix_id = '213' where f_patient_prefix_id = '213';
update f_patient_prefix set r_rp1853_prefix_id = '214' where f_patient_prefix_id = '214';
update f_patient_prefix set r_rp1853_prefix_id = '215' where f_patient_prefix_id = '215';
update f_patient_prefix set r_rp1853_prefix_id = '216' where f_patient_prefix_id = '216';
update f_patient_prefix set r_rp1853_prefix_id = '217' where f_patient_prefix_id = '217';
update f_patient_prefix set r_rp1853_prefix_id = '218' where f_patient_prefix_id = '218';
update f_patient_prefix set r_rp1853_prefix_id = '219' where f_patient_prefix_id = '219';
update f_patient_prefix set r_rp1853_prefix_id = '222' where f_patient_prefix_id = '220';
update f_patient_prefix set r_rp1853_prefix_id = '221' where f_patient_prefix_id = '221';
update f_patient_prefix set r_rp1853_prefix_id = '223' where f_patient_prefix_id = '223';
update f_patient_prefix set r_rp1853_prefix_id = '224' where f_patient_prefix_id = '224';
update f_patient_prefix set r_rp1853_prefix_id = '225' where f_patient_prefix_id = '225';
update f_patient_prefix set r_rp1853_prefix_id = '226' where f_patient_prefix_id = '226';
update f_patient_prefix set r_rp1853_prefix_id = '228' where f_patient_prefix_id = '228';
update f_patient_prefix set r_rp1853_prefix_id = '252' where f_patient_prefix_id = '252';
update f_patient_prefix set r_rp1853_prefix_id = '271' where f_patient_prefix_id = '271';
update f_patient_prefix set r_rp1853_prefix_id = '355' where f_patient_prefix_id = '355';
update f_patient_prefix set r_rp1853_prefix_id = '357' where f_patient_prefix_id = '357';
update f_patient_prefix set r_rp1853_prefix_id = '359' where f_patient_prefix_id = '359';
update f_patient_prefix set r_rp1853_prefix_id = '360' where f_patient_prefix_id = '360';
update f_patient_prefix set r_rp1853_prefix_id = '361 ' where f_patient_prefix_id = '361';
update f_patient_prefix set r_rp1853_prefix_id = '363' where f_patient_prefix_id = '363';
update f_patient_prefix set r_rp1853_prefix_id = '364' where f_patient_prefix_id = '364';
update f_patient_prefix set r_rp1853_prefix_id = '371' where f_patient_prefix_id = '371';
update f_patient_prefix set r_rp1853_prefix_id = '372' where f_patient_prefix_id = '372';
update f_patient_prefix set r_rp1853_prefix_id = '373' where f_patient_prefix_id = '373';
update f_patient_prefix set r_rp1853_prefix_id = '374' where f_patient_prefix_id = '374';
update f_patient_prefix set r_rp1853_prefix_id = '375' where f_patient_prefix_id = '375';
update f_patient_prefix set r_rp1853_prefix_id = '376' where f_patient_prefix_id = '376';
update f_patient_prefix set r_rp1853_prefix_id = '377' where f_patient_prefix_id = '377';
update f_patient_prefix set r_rp1853_prefix_id = '379' where f_patient_prefix_id = '379';
update f_patient_prefix set r_rp1853_prefix_id = '389' where f_patient_prefix_id = '389';
update f_patient_prefix set r_rp1853_prefix_id = '391' where f_patient_prefix_id = '391';
update f_patient_prefix set r_rp1853_prefix_id = '500' where f_patient_prefix_id = '500';
update f_patient_prefix set r_rp1853_prefix_id = '502' where f_patient_prefix_id = '502';
update f_patient_prefix set r_rp1853_prefix_id = '504' where f_patient_prefix_id = '504';
update f_patient_prefix set r_rp1853_prefix_id = '508' where f_patient_prefix_id = '508';
update f_patient_prefix set r_rp1853_prefix_id = '510' where f_patient_prefix_id = '510';
update f_patient_prefix set r_rp1853_prefix_id = '512' where f_patient_prefix_id = '512';
update f_patient_prefix set r_rp1853_prefix_id = '513' where f_patient_prefix_id = '513';
update f_patient_prefix set r_rp1853_prefix_id = '514' where f_patient_prefix_id = '514';
update f_patient_prefix set r_rp1853_prefix_id = '515' where f_patient_prefix_id = '515';
update f_patient_prefix set r_rp1853_prefix_id = '516' where f_patient_prefix_id = '516';
update f_patient_prefix set r_rp1853_prefix_id = '517' where f_patient_prefix_id = '517';
update f_patient_prefix set r_rp1853_prefix_id = '518' where f_patient_prefix_id = '518';
update f_patient_prefix set r_rp1853_prefix_id = '519' where f_patient_prefix_id = '519';
update f_patient_prefix set r_rp1853_prefix_id = '520' where f_patient_prefix_id = '520';
update f_patient_prefix set r_rp1853_prefix_id = '521' where f_patient_prefix_id = '521';
update f_patient_prefix set r_rp1853_prefix_id = '522' where f_patient_prefix_id = '522';
update f_patient_prefix set r_rp1853_prefix_id = '523' where f_patient_prefix_id = '523';
update f_patient_prefix set r_rp1853_prefix_id = '524' where f_patient_prefix_id = '524';
update f_patient_prefix set r_rp1853_prefix_id = '525' where f_patient_prefix_id = '525';
update f_patient_prefix set r_rp1853_prefix_id = '526' where f_patient_prefix_id = '526';
update f_patient_prefix set r_rp1853_prefix_id = '527' where f_patient_prefix_id = '527';
update f_patient_prefix set r_rp1853_prefix_id = '528' where f_patient_prefix_id = '528';
update f_patient_prefix set r_rp1853_prefix_id = '655' where f_patient_prefix_id = '655';
update f_patient_prefix set r_rp1853_prefix_id = '661' where f_patient_prefix_id = '661';
update f_patient_prefix set r_rp1853_prefix_id = '663' where f_patient_prefix_id = '663';
update f_patient_prefix set r_rp1853_prefix_id = '664' where f_patient_prefix_id = '664';
update f_patient_prefix set r_rp1853_prefix_id = '665' where f_patient_prefix_id = '665';
update f_patient_prefix set r_rp1853_prefix_id = '666' where f_patient_prefix_id = '666';
update f_patient_prefix set r_rp1853_prefix_id = '667' where f_patient_prefix_id = '667';
update f_patient_prefix set r_rp1853_prefix_id = '668' where f_patient_prefix_id = '668';
update f_patient_prefix set r_rp1853_prefix_id = '669' where f_patient_prefix_id = '669';
update f_patient_prefix set r_rp1853_prefix_id = '670' where f_patient_prefix_id = '670';
update f_patient_prefix set r_rp1853_prefix_id = '671' where f_patient_prefix_id = '671';
update f_patient_prefix set r_rp1853_prefix_id = '672' where f_patient_prefix_id = '672';
update f_patient_prefix set r_rp1853_prefix_id = '673' where f_patient_prefix_id = '673';
update f_patient_prefix set r_rp1853_prefix_id = '675' where f_patient_prefix_id = '675';
update f_patient_prefix set r_rp1853_prefix_id = '676' where f_patient_prefix_id = '676';
update f_patient_prefix set r_rp1853_prefix_id = '677' where f_patient_prefix_id = '677';
update f_patient_prefix set r_rp1853_prefix_id = '678' where f_patient_prefix_id = '678';
update f_patient_prefix set r_rp1853_prefix_id = '681' where f_patient_prefix_id = '681';
update f_patient_prefix set r_rp1853_prefix_id = '684' where f_patient_prefix_id = '684';
update f_patient_prefix set r_rp1853_prefix_id = '685' where f_patient_prefix_id = '685';
update f_patient_prefix set r_rp1853_prefix_id = '686' where f_patient_prefix_id = '686';
update f_patient_prefix set r_rp1853_prefix_id = '812' where f_patient_prefix_id = '812';
update f_patient_prefix set r_rp1853_prefix_id = '816' where f_patient_prefix_id = '816';
update f_patient_prefix set r_rp1853_prefix_id = '818' where f_patient_prefix_id = '818';
update f_patient_prefix set r_rp1853_prefix_id = '819' where f_patient_prefix_id = '819';
update f_patient_prefix set r_rp1853_prefix_id = '828' where f_patient_prefix_id = '828';
update f_patient_prefix set r_rp1853_prefix_id = '830' where f_patient_prefix_id = '830';
update f_patient_prefix set r_rp1853_prefix_id = '831' where f_patient_prefix_id = '831';
update f_patient_prefix set r_rp1853_prefix_id = '832' where f_patient_prefix_id = '832';
update f_patient_prefix set r_rp1853_prefix_id = '838' where f_patient_prefix_id = '838';
update f_patient_prefix set r_rp1853_prefix_id = '839' where f_patient_prefix_id = '839';
update f_patient_prefix set r_rp1853_prefix_id = '200' where f_patient_prefix_id = '841';
update f_patient_prefix set r_rp1853_prefix_id = '202' where f_patient_prefix_id = '842';
update f_patient_prefix set r_rp1853_prefix_id = '204' where f_patient_prefix_id = '843';
update f_patient_prefix set r_rp1853_prefix_id = '135 ' where f_patient_prefix_id = '844';

-------------------------------------------------------
create table r_rp1853_icddeath (
id varchar(255),
name varchar(255),
description varchar(255) ,
icd varchar(255) 
); 
ALTER TABLE ONLY r_rp1853_icddeath ADD CONSTRAINT r_rp1853_icddeath_pkey PRIMARY KEY (id); 

insert into r_rp1853_icddeath values ('001','Certain infectious and parasitic diseases',' 001  โรคติดเชื้อและปรสิต ','A00-B99');
insert into r_rp1853_icddeath values ('002','Cholera','อหิวาตกโรค',' A00');
insert into r_rp1853_icddeath values ('003','Diarrhoea and gastroenteritis of presumed  infectious origin','อาการท้องร่วง กระเพาะและลำไส้อักเสบซึ่งสัณนิษฐานว่าเกิดจากการติดเชื้อ  ','A09');
insert into r_rp1853_icddeath values ('004','Other intestinal infectious diseases',' โรคติดเชื้ออื่น ๆ ของไส้ ','A01-A08');
insert into r_rp1853_icddeath values ('005','Respiratory tuberculosis','วัณโรคทางเดินหายใจ ','A15-A16');
insert into r_rp1853_icddeath values ('006','Other tuberculosis','วัณโรคอื่น ๆ',' A17-A19');
insert into r_rp1853_icddeath values ('007','Plague',' กาฬโรค ','A20');
insert into r_rp1853_icddeath values ('008','Tetanus','บาดทะยัก',' A33-A35');
insert into r_rp1853_icddeath values ('009','Diphtheria','โรคคอตีบ ','A36');
insert into r_rp1853_icddeath values ('010','Whooping cough','โรคไอกรน',' A37');
insert into r_rp1853_icddeath values ('011','Meningococcal infection','การติดเชื้อเมนิงโกคอคคัส ','A39');
insert into r_rp1853_icddeath values ('012','Septicaemia','โลหิตเป็นพิษ ','A40-A41');
insert into r_rp1853_icddeath values ('013','Infections with a predominantly sexual mode of transmission','การติดเชื้อที่ผ่านทางการมีเพศสัมพันธ์เป็นส่วนใหญ่ ','A50-A64');
insert into r_rp1853_icddeath values ('014','Acute poliomyelitis','โรคโปลิโอเฉียบพลัน (โปลิโอ)','A80');
insert into r_rp1853_icddeath values ('015','Rabies','โรคพิษสุนัขบ้า ','A82');
insert into r_rp1853_icddeath values ('016','Yellow fever','ไข้เหลือง','A95');
insert into r_rp1853_icddeath values ('017','Other arthropod-borne viral fevers and viral haemorrhagic  fevers','ไข้จากไวรัสที่นำโดยแมลงและไข้เลือดออกที่เกิดจากไวรัสอื่น ๆ','A90-A94,A96-A99');
insert into r_rp1853_icddeath values ('018','Measles','หัด ','B05');
insert into r_rp1853_icddeath values ('019','Viral hepatitis','โรคตับอักเสบจากเชื้อไวรัส  ','B15-B19');
insert into r_rp1853_icddeath values ('020','Human immunodeficiency virus (HIV) disease','โรคภูมิคุ้มกันบกพร่องเนื่องจากไวรัส ','B20-B24');
insert into r_rp1853_icddeath values ('021','Malaria','มาลาเรีย','B50-B54');
insert into r_rp1853_icddeath values ('022','Leishmaniasis','โรคลิซมาเนีย ','B55');
insert into r_rp1853_icddeath values ('023','Trypanosomiasis','โรคทริปาโนโซม','B56-B57');
insert into r_rp1853_icddeath values ('024','Schistosomiasis','โรคติดเชื้อซิลโตโซม (มิลฮาร์ซิเอลิส) ','B65');
insert into r_rp1853_icddeath values ('025','Remainder of certain infectious and parasitic diseases','โรคติดเชื้อและปรสิตที่เหลืออยู่ (A21-A32,A38,A42-A49,','A65-A79,A81,A83-A89,B00-B04,B06-B09,B25-B49,B58-   B64,B66-B94,B99');
insert into r_rp1853_icddeath values ('026','Neoplasms','เนื้องอก','C00-D48');
insert into r_rp1853_icddeath values ('027','Malignant neoplasm of  lip, oral cavity and pharynx','เนื้องอกร้ายของริมฝีปาก ช่องปากและคอหอย ','C00-C14');
insert into r_rp1853_icddeath values ('028','Malignant neoplasm of oesophagus','เนื้องอกร้ายที่หลอดอาหาร ','C15');
insert into r_rp1853_icddeath values ('029','Malignant neoplasm of stomach','เนื้องอกร้ายที่กระเพาะอาหาร ','C16');
insert into r_rp1853_icddeath values ('030','Malignant neoplasm of colon, rectum and anus','เนื้องอกร้ายที่ลำไส้ใหญ่ เร็คตัมและทวารหนัก ','C18-C21');
insert into r_rp1853_icddeath values ('031','Malignant neoplasm of liver and intrahepatic bile ducts','เนื้องอกร้ายที่ตับและท่อน้ำดีในตับ ','C22');
insert into r_rp1853_icddeath values ('032','Malignant neoplasm of pancreas','เนื้องอกร้ายที่ตับอ่อน ','C25');
insert into r_rp1853_icddeath values ('033','Malignant neoplasm of larynx','เนื้องอกร้ายที่กล่องเสียง','C32');
insert into r_rp1853_icddeath values ('034','Malignant neoplasm of trachea,  bronchus and lung','เนื้องอกร้ายที่หลอดคอ หลอดลมใหญ่และปอด','C33-C34');
insert into r_rp1853_icddeath values ('035','Malignant melanoma of skin','เนื้องอกร้ายเมลาโนมาของผิวหนัง ','C43'); 
insert into r_rp1853_icddeath values ('036','Malignant neoplasm of breast Female','เนื้องอกร้ายที่เต้านม หญิง','C50');
insert into r_rp1853_icddeath values ('037','Malignant neoplasm of cervix uteri','เนื้องอกร้ายที่ปากมดลูก ','C53');
insert into r_rp1853_icddeath values ('038','Malignant neoplasm of other and unspecified parts of uterus','เนื้องอกร้ายอื่น ๆ และที่มิได้ระบุส่วนของมดลูก ','C54-C55');
insert into r_rp1853_icddeath values ('039','Malignant neoplasm of ovary','เนื้องอกร้ายที่รังไข่ ','C56');
insert into r_rp1853_icddeath values ('040','Malignant neoplasm of prostate','เนื้องอกร้ายที่ต่อมลูกหมาก','C61');
insert into r_rp1853_icddeath values ('041','Malignant neoplasm of bladder','เนื้องอกร้ายที่กระเพาะปัสสาวะ ','C67');
insert into r_rp1853_icddeath values ('042','Malignant neoplasm of meninges,  brain and other parts of central nervous system','เนื้องอกร้ายที่เยื่อหุ้มสมอง สมอง และส่วนอื่น ๆ ของระบบประสาทส่วนกลาง ','C70-C72');
insert into r_rp1853_icddeath values ('043','Non-Hodgkin''s lymphoma','เนื้องอกร้ายลิมโฟมาที่ไม่ใช่โรคฮอดกินส์ ','C82-C85');
insert into r_rp1853_icddeath values ('044','Multiple myeloma and malignant plasma cell neoplasms','เนื้องอกร้ายมัลติเปิลมัยอีโลมา และเนื้องอกชนิดร้ายของพลาสมาเซลล์ ','C90');
insert into r_rp1853_icddeath values ('045','Leukaemia','ลิวคีเมีย','C91-C95');
insert into r_rp1853_icddeath values ('046','Remainder of malignant neoplasms','เนื้องอกชนิดร้ายที่เหลืออยู่  ','C17,C23-C24,C26-C31,C37-C41,C44-C49,C51-C52,C57-C60,  C62-C66 C68-C69,C73-C81,C88,C96-C97');
insert into r_rp1853_icddeath values ('047','Remainder of neoplasms','เนื้องอกที่เหลืออยู่','D00-D48');
insert into r_rp1853_icddeath values ('048','Diseases of the blood and blood-forming organs and certain disorders involving the immune mechanism','โรคเลือดและอวัยวะสร้างเลือดและความผิดปกติเกี่ยวกับกลไกของภูมิคุ้มกันบางชนิด ','D50-D89');
insert into r_rp1853_icddeath values ('049','Anaemias','โลหิตจาง ','D50-D64');
insert into r_rp1853_icddeath values ('050','Remainder of diseases of the blood and blood- forming  organs and certain disorders involving the immune mechanism','โรคเลือดและอวัยวะสร้างเลือดและความผิดปกติที่เกี่ยวกับกลไก ของภูมิคุ้มกันบางชนิดที่เหลืออยู่ ','D65-D89');
insert into r_rp1853_icddeath values ('051','Endocrine, nutritional and metabolic diseases','โรคของต่อมไร้ท่อ โภชนาการและเมตะบอลิซึม ','E00-E88');
insert into r_rp1853_icddeath values ('052',' Diabetes mellitus','เบาหวาน','E10-E14');
insert into r_rp1853_icddeath values ('053',' Malnutrition','ภาวะทุพโภชนาการ','E40-E46');
insert into r_rp1853_icddeath values ('054','Remainder of endocrine,  nutritional and metabolic diseases','โรคต่อมไร้ท่อ โภชนาการและเมตะบอสลิซึมที่เหลืออยู่ที่','E00-E07,E15-E34,E50-E88');
insert into r_rp1853_icddeath values ('055','Mental and behavioural disorders','โรคทางจิตเวชและความผิดปกติของพฤติกรรม','F01-F99');
insert into r_rp1853_icddeath values ('056','Mental and behavioural disorders due to psychoactive  substance   use','ความผิดปกติทางจิตและพฤติกรรมเนื่องจากการใช้วัตถุออกฤทธิ์ ต่อจิตประสาท','F10-F19');
insert into r_rp1853_icddeath values ('057','Remainder of mental and behavioural disorders','โรคทางจิตเวชและความผิดปกติของพฤติกรรมที่เหลืออยู่','F01-F09,F20 -F99');
insert into r_rp1853_icddeath values ('058','Diseases of the nervous system','โรคระบบประสาท ','G00-G98');
insert into r_rp1853_icddeath values ('059','Meningitis','เยื่อหุ้มสมองอักเสบ ','G00,G03');
insert into r_rp1853_icddeath values ('060','Alzheimer''s disease','โรคแอลไซเมอร์',' G30');
insert into r_rp1853_icddeath values ('061','Remainder of diseases of the nervous system','โรคระบบประสาทที่เหลืออยู่ ','G04-G25, G31-G98');
insert into r_rp1853_icddeath values ('062','Diseases of the eye and adnexa','โรคของตาและส่วนประกอบของตา ','H00-H57');
insert into r_rp1853_icddeath values ('063','Diseases of the ear and mastoid process','โรคของหูและปุ่มกกหู ','H60-H93');
insert into r_rp1853_icddeath values ('064','Diseases of the circulatory system','โรคระบบไหลเวียนโลหิต','I00-I99');
insert into r_rp1853_icddeath values ('065','Acute rheumatic fever and chronic rheumatic heart diseases','ไข้รูมาติกเฉียบพลันและโรคหัวใจรูมาติกเรื้อรัง ','I00-I09');
insert into r_rp1853_icddeath values ('066','Hypertensive diseases','ความดันโลหิตสูง ','I10-I15');
insert into r_rp1853_icddeath values ('067','Ischaemic heart diseases','โรคหัวใจขาดเลือด','I20-I25');
insert into r_rp1853_icddeath values ('068','Other heart diseases','โรคหัวใจอื่น','I26-I52');
insert into r_rp1853_icddeath values ('069','Cerebrovascular diseases','โรคหลอดเลือดในสมอง','I60-I69');
insert into r_rp1853_icddeath values ('070','Atherosclerosis','อะเธอโรสเคลอโรสีส ','I70');
insert into r_rp1853_icddeath values ('071','Remainder of diseases of the circulatory system','โรคระบบไหลเวียนโลหิตที่เหลืออยู่','I71-I99');
insert into r_rp1853_icddeath values ('072','Diseases of the respiratory system','โรคของทางเดินระบบหายใจ','J00-J98');
insert into r_rp1853_icddeath values ('073','Influenza','ไข้หวัดใหญ่ ','J10-J11');
insert into r_rp1853_icddeath values ('074','Pneumonia','ปอดบวม','J12-J18');
insert into r_rp1853_icddeath values ('075','Other acute lower respiratory infections','โรคของทางเดินหายใจส่วนล่างเฉียบพลันอื่น ๆ','J20-J22');
insert into r_rp1853_icddeath values ('076','Chronic lower respiratory diseases','โรคของทางเดินหายใจส่วนล่างเรื้อรัง','J40-J47');
insert into r_rp1853_icddeath values ('077','Remainder of diseases of the respiratory system','โรคของระบบทางเดินหายใจที่เหลืออยู่ ','J00-J06,J30-J39, J60-J98');
insert into r_rp1853_icddeath values ('078','Diseases of the digestive system','โรคระบบย่อยอาหาร ','K00-K92');
insert into r_rp1853_icddeath values ('079','Gastric and duodenal ulcer','แผลเปื่อยกระเพาะอาหารและดูโอเดนั่ม ','K25-K27');
insert into r_rp1853_icddeath values ('080',' Diseases of the liver','โรคของตับ ','K70-K76');
insert into r_rp1853_icddeath values ('081','Remainder of diseases of the digestive system',' โรคระบบย่อยอาหารที่เหลืออยู่ ','K00-K22,K28-K66,K80  -K92');
insert into r_rp1853_icddeath values ('082','Diseases of the skin and subcutaneous tissue','โรคของผิวหนังและเนื้อเยื่อใต้ผิวหนัง','L00-L98');
insert into r_rp1853_icddeath values ('083','Diseases of the musculoskeletal system and connective tissue ','โรคของระบบกล้ามเนื้อกระดูกและเนื้อเยื่อประสาน','M00-M99');
insert into r_rp1853_icddeath values ('084','Diseases of the genitourinary system','โรคของระบบสืบพันธุ์และทางเดินปัสสาวะ','N00-N99');
insert into r_rp1853_icddeath values ('085','Glomerular and renal tubulo-interstitial diseases','โรคของหน่วยไตและโรคของเนื้อเยื่อระหว่างท่อในไต ','N00-N15');
insert into r_rp1853_icddeath values ('086','Remainder of diseases of the genito-urinary system','โรคของระบบสืบพันธุ์และทางเดินปัสสาวะที่เหลืออยู่','N17-N98');
insert into r_rp1853_icddeath values ('087','Pregnancy , childbirth and the puerperium','การตั้งครรภ์ การคลอด และระยะหลังคลอด','O00-O99');
insert into r_rp1853_icddeath values ('088','Pregnancy with abortive outcome','การตั้งครรภ์ที่สิ้นสุดโดยการแท้ง ','O00-O08');
insert into r_rp1853_icddeath values ('089','Other direct obstetric deaths','การตายเนื่องจาก การคลอดโดยตรงอื่น ๆ','O10-O92');
insert into r_rp1853_icddeath values ('090','Indirect obstetric deaths','การตายเนื่องจาก การคลอด ที่มิใช่โดยตรง','O98-O99');
insert into r_rp1853_icddeath values ('091','Remainder of pregnancy ,childbirth and the puerperium','การตั้งครรภ์ การคลอดและระยะหลังคลอดที่เหลืออยู่','O95-O97');
insert into r_rp1853_icddeath values ('092','Certain conditions originating in the perinatal period','ภาวะบางอย่างที่เกิดในระยะปริกำเนิด ','P00-P96');
insert into r_rp1853_icddeath values ('093','Congenital malformations, deformations and chromosomal    abnormalities','ความผิดปกติ ความพิการแต่กำเนิด และโครโมโซมผิดปกติ ','Q00-Q99');
insert into r_rp1853_icddeath values ('094','Symptoms, signs and abnormal clinical and laboratory findings, not elsewhere classified','อาการ อาการแสดงและสิ่งผิดปกติที่พบจากการตรวจทางคลินิกและตรวจทางห้องปฏิบัติการที่มิได้มีรหัสระบุไว้ ','R00-R99');
insert into r_rp1853_icddeath values ('095','External causes of morbidity and mortality','สาเหตุภายนอกของการป่วยและการตาย ','V01-Y89');
insert into r_rp1853_icddeath values ('096','Transport accidents','อุบัติเหตุการขนส่ง','V01-V99');
insert into r_rp1853_icddeath values ('097','Falls','การพลัดตก ','W00-W19');
insert into r_rp1853_icddeath values ('098','Accidental drowning and submersion','อุบัติเหตุการตกน้ำ  และการจมน้ำ','W65-W74');
insert into r_rp1853_icddeath values ('099','Exposure to smoke, fire and flames','การสัมผัสควัน ไฟและเปลวไฟ','X00-X09');
insert into r_rp1853_icddeath values ('100','Accidental poisoning by and exposure to noxious substances','การเป็นพิษโดยอุบัติเหตุจากการสัมผัสกับสารเป็นพิษ ','X40-X49');
insert into r_rp1853_icddeath values ('101','Intentional self-harm','การมีเจตนาทำร้ายตนเอง',' X60-X84');
insert into r_rp1853_icddeath values ('102','Assault','การทำร้ายผู้อื่น','X85-Y09');
insert into r_rp1853_icddeath values ('103','All other external causes','สาเหตุจากภายนอกอื่น ๆ ทั้งหมด','W20-W64,W75-W99, X10-X39,X50-X59,Y10-Y89');
---------------------------------------------------------
create table r_rp1853_icdchronic (
id varchar(255),
name varchar(255),
description varchar(255) ,
icd varchar(255) 
); 
ALTER TABLE ONLY r_rp1853_icdchronic ADD CONSTRAINT r_rp1853_icdchronic_pkey PRIMARY KEY (id); 

insert into r_rp1853_icdchronic values ('1','Hypertensive','ความดันโลหิตสูง','I10-I15');
insert into r_rp1853_icdchronic values ('2','Asthma','โรคหอบหืด ','J45');
insert into r_rp1853_icdchronic values ('3','Ischaemic heart','หัวใจขาดเลือด ','I20-I25');
insert into r_rp1853_icdchronic values ('4','Neoplasms','มะเร็ง ','C00-D48');
insert into r_rp1853_icdchronic values ('5','Anaemias','โลหิตจาง ','D50-D64');
insert into r_rp1853_icdchronic values ('6','Mood disorders','โรคซึมเศร้า ','F30-F39');
insert into r_rp1853_icdchronic values ('7','Cerebrovascular','โรคหลอดเลือดสมอง ','I60-I69');
insert into r_rp1853_icdchronic values ('8','Paralytic','อัมพฤกษ์ อัมพาต ','G80-G83');
insert into r_rp1853_icdchronic values ('9','Renal failure','ไตวาย ','N17-N19');
insert into r_rp1853_icdchronic values ('10','Diabetes mellitus','เบาหวาน ','E10-E14');
insert into r_rp1853_icdchronic values ('11','Chronic bronchitis','หลอดลมอักเสบเรื้อรัง ','J42');
insert into r_rp1853_icdchronic values ('12','Emphysema','ถุงลมปอดโป่งพอง ','J43');
insert into r_rp1853_icdchronic values ('13','Diseases of heart','โรคหัวใจ','I05-I09,I20-I25,I26-I28,I30-I52');
insert into r_rp1853_icdchronic values ('14','Alcohol','พิษสุราเรื้อรัง ','F10.0,F10.2');
insert into r_rp1853_icdchronic values ('15','Chronic obstructive pulmonary','โรคทางเดินหายใจอุดกั่นเรื้องรัง ','J44');
insert into r_rp1853_icdchronic values ('16','Tuberculosis','วัณโรค','A15-A19');
-----------------------------------------------------

create table r_rp1853_instype (
id varchar(255),
inscl_name varchar(255),
inscl_dateexp varchar(255) ,
maininscl varchar(255) 
); 
ALTER TABLE ONLY r_rp1853_instype ADD CONSTRAINT r_rp1853_instype_pkey PRIMARY KEY (id); 

ALTER TABLE b_contract_plans add r_rp1853_instype_id varchar(255);
 
insert into r_rp1853_instype values ('01','สิทธิประกันสังคม','','SSS');
insert into r_rp1853_instype values ('02','สิทธิข้าราชการ/สิทธิรัฐวิสาหกิจ','','OFC');
insert into r_rp1853_instype values ('03','สถานะคนไทยในต่างประเทศ','','FRG');
insert into r_rp1853_instype values ('04','สิทธิประกันสังคมและสิทธิข้าราชการ/สิทธิรัฐวิสาหกิจ','','SOF');
insert into r_rp1853_instype values ('05','สิทธิข้าราชการการเมือง/นักการเมือง','','BFC');
insert into r_rp1853_instype values ('06','ไม่มีสิทธิใดๆ','','');
insert into r_rp1853_instype values ('07','สถานะคนต่างด้าว','','NRD');
insert into r_rp1853_instype values ('08','สิทธิครูเอกชน/สิทธิข้าราชการการเมือง','','PBF');
insert into r_rp1853_instype values ('09','สิทธิทหารผ่านศึก','','VET');
insert into r_rp1853_instype values ('10','สิทธิประกันสังคม/สิทธิครูเอกชน/สิทธิข้าราชการ','','PSO');
insert into r_rp1853_instype values ('11','สิทธิทหารผ่านศึก/สิทธิข้าราชการ','','VOF');
insert into r_rp1853_instype values ('12','สิทธิประกันสังคม/สิทธิทหารผ่านศึก','','VSS');
insert into r_rp1853_instype values ('13','สิทธิทหารผ่านศึก/สิทธิข้าราชการการเมือง','','VBF');
insert into r_rp1853_instype values ('14','สิทธิประกันสังคม/สิทธิทหารผ่านศึก/สิทธิข้าราชการ','','VSO');
insert into r_rp1853_instype values ('15','สิทธิประกันสังคม/สิทธิทหารผ่านศึก/สิทธิข้าราชการการเมือง','','VSB');
insert into r_rp1853_instype values ('16','สิทธิครูเอกชน','','PVT');
insert into r_rp1853_instype values ('17','สิทธิประกันสังคม/สิทธิครูเอกชน','','PSS');
insert into r_rp1853_instype values ('18','สิทธิครูเอกชน/สิทธิข้าราชการ','','POF');
insert into r_rp1853_instype values ('19','สิทธิประกันสังคม/สิทธิครูเอกชน/สิทธิข้าราชการการเมือง','','PSB');
insert into r_rp1853_instype values ('20','สิทธิครูเอกชน/สิทธิทหารผ่านศึก','','VPT');
insert into r_rp1853_instype values ('21','สิทธิครูเอกชน/สิทธิประกันสังคม/สิทธิทหารผ่านศึก','','VPS');
insert into r_rp1853_instype values ('22','สิทธิครูเอกชน/สิทธิทหารผ่านศึก/สิทธิข้าราชการ','','VPO');
insert into r_rp1853_instype values ('23','สิทธิครูเอกชน/สิทธิทหารผ่านศึก/สิทธิข้าราชการการเมือง','','VPB');
insert into r_rp1853_instype values ('24','สิทธิประกันสังคมและสิทธิข้าราชการการเมือง/นักการเมือง','','SBF');
insert into r_rp1853_instype values ('25','สิทธิประกันสังคมกรณีทุพลภาพ','','SSI');
insert into r_rp1853_instype values ('26','สิทธิประกันสังคมทุพลภาพและสิทธิข้าราชการ/สิทธิรัฐวิสาหกิจ','','SIF');
insert into r_rp1853_instype values ('27','สิทธิครูเอกชน/สิทธิประกันสังคมแบบทุพพลภาพ','','PSI');
insert into r_rp1853_instype values ('28','สิทธิประกันสังคมแบบทุพพลภาพ/สิทธิทหารผ่านศึก/สิทธิข้าราชการ','','VIO');
insert into r_rp1853_instype values ('29','สิทธิประกันสังคมแบบทุพพลภาพ/สิทธิทหารผ่านศึก','','VSI');
insert into r_rp1853_instype values ('60','อาสาสมัครมาเลเรีย','ตามวันหมดอายุของบัตรประจำตัว','WEL');
insert into r_rp1853_instype values ('61','บุคคลในครอบครัวของอาสาสมัครมาเลเรีย','ตามวันหมดอายุของเจ้าของบัตรประจำตัว','WEL');
insert into r_rp1853_instype values ('62','ช่างสุขภัณฑ์หมู่บ้าน','ตามวันหมดอายุของบัตรประจำตัว','WEL');
insert into r_rp1853_instype values ('63','บุคคลในครอบครัวของช่างสุขภัณฑ์หมู่บ้าน','ตามวันหมดอายุของเจ้าของบัตรประจำตัว','WEL');
insert into r_rp1853_instype values ('64','ผู้บริหารโรงเรียนและครูของโรงเรียนเอกชนที่สอนศาสนาอิสลาม','ตามวาระที่รับมอบหมาย','WEL');
insert into r_rp1853_instype values ('65','บุคคลในครอบครัวของผู้บริหารโรงเรียนและครูของโรงเรียนเอกชนที่สอนศาสนาอิสลาม','ตามวันหมดอายุของเจ้าของบัตรประจำตัว','WEL');
insert into r_rp1853_instype values ('66','ผู้ได้รับพระราชทานเหรียญราชการชายแดน','Noexp','WEL');
insert into r_rp1853_instype values ('67','ผู้ได้รับพระราชทานเหรียญพิทักษ์เสรีชน','Noexp','WEL');
insert into r_rp1853_instype values ('68','สมาชิกผู้บริจาคโลหิตของสภากาชาดไทย ซึ่งบริจาคโลหิตตั้งแต่ 18 ครั้ง ขึ้นไป','Noexp','WEL');
insert into r_rp1853_instype values ('69','หมออาสาหมู่บ้านตามโครงการของกระทรวงกลาโหม','Noexp','WEL');
insert into r_rp1853_instype values ('70','อาสาสมัครคุมประพฤ กระทรวงยุติธรรม','ตามวาระที่ได้รับมอบหมาย','WEL');
insert into r_rp1853_instype values ('71','เด็กอายุไม่เกิน 12 ปีบริบูรณ์','ณ วันที่ ครบ 12 ปีบริบูรณ์','WEL');
insert into r_rp1853_instype values ('72','ผู้มีรายได้น้อย','3 ปี ','WEL');
insert into r_rp1853_instype values ('73','นักเรียนมัธยมศึกษาตอนต้น','3 ปี ','WEL');
insert into r_rp1853_instype values ('74','บุคคลผู้พิการ','Noexp','WEL');
insert into r_rp1853_instype values ('75','ทหารผ่านศึกชั้น 1-3 ที่มีบัตรทหารผ่านศึก รวมถึงผู้ได้รับพระราชทาน','Noexp','WEL');
insert into r_rp1853_instype values ('76','พระภิกษุ สามเณร และแม่ชีในพระพุทธศาสนาซึ่งหนังสือสุทธิรับรอง','ตามสถานะภาพที่ปรากฎ','WEL');
insert into r_rp1853_instype values ('77','ผู้มีอายุเกิน 60 ปีบริบูรณ์','Noexp','WEL');
insert into r_rp1853_instype values ('78','อื่น ๆ','1 ปี','WEL');
insert into r_rp1853_instype values ('79','ว่างงาน','1 ปี','WEL');
insert into r_rp1853_instype values ('80','บุคคลในครอบครัวทหารผ่านศึกชั้น 1-3 รวมถึงผู้ได้รับพระราชทานเหรียญสมรภูมิ','Noexp','WEL');
insert into r_rp1853_instype values ('81','ผู้นำชุมชน (กำนัน สารวัตรกำนัน ผู้ใหญ่บ้าน ผู้ช่วยผู้ใหญ่บ้านและแพทย์ประจำตำบล)','ตามวาระที่ได้รับมอบหมาย','WEL');
insert into r_rp1853_instype values ('82','อาสาสมัครสาธารณสุขประจำหมู่บ้าน (อสม.) อาสาสมัครสาธารณสุข','ตามวันหมดอายุของบัตรประจำตัว','WEL');
insert into r_rp1853_instype values ('83','ผู้นำศาสนาอิสลาม ( อิหม่าม คอเต็บ บิหลั่น)','ตามวันหมดอายุของบัตรประจำตัว','WEL');
insert into r_rp1853_instype values ('84','บุคคลในครอบครัวของผู้นำศาสนาอิสลามของผู้นำศาสนาอิสลาม ( อิหม่าม คอเต็บ บิหลั่น)','ตามวันหมดอายุของเจ้าของบัตรประจำตัว','WEL');
insert into r_rp1853_instype values ('85','ผู้ได้รับพระราชทานเหรียญงานสงครามในทวีปยุโรป','ตามวันหมดอายุของบัตรประจำตัว','WEL');
insert into r_rp1853_instype values ('86','บุคคลในครอบครัวของผู้ได้รับพระราชทานเหรียญงานสงครามในทวีปยุโรป','ตามวันหมดอายุของเจ้าของบัตรประจำตัว','WEL');
insert into r_rp1853_instype values ('87','บุคคลในครอบครัวของผู้นำชุมชน (กำนัน สารวัตรกำนัน ผู้ใหญ่บ้าน ผู้ช่วยผู้ใหญ่บ้านและแพทย์ประจำตำบล)','ตามวันหมดอายุของเจ้าของบัตรประจำตัว','WEL');
insert into r_rp1853_instype values ('88','บุคคลในครอบครัวของอาสาสมัครสาธารณสุขประจำหมู่บ้าน (อสม.) อาสาสมัครสาธารณสุข','ตามวันหมดอายุของเจ้าของบัตรประจำตัว','WEL');
insert into r_rp1853_instype values ('89','ช่วงอายุ 12-59 ปี','ระหว่างช่วงอายุ 12 - 59 ปี','UCS');
insert into r_rp1853_instype values ('90','ทหารเกณฑ์','ตามวันที่ปลดประจำการ','WEL');
insert into r_rp1853_instype values ('91','ผู้ที่พำนักในสถานที่ภายใต้การดูแลของส่วนราชการ(ราชทัณฑ์)','ตามวันที่พ้นโทษ','WEL');
insert into r_rp1853_instype values ('92','ผู้ที่พำนักในสถานที่ภายใต้การดูแลของส่วนราชการ    (สถานพินิจและสถานสงเคราะห์)','ตามช่วงเวลาที่อยู่ในความดูแล','WEL');
insert into r_rp1853_instype values ('93','นักเรียนทหาร','ตามวันที่จบการศึกษา','WEL');
insert into r_rp1853_instype values ('94','ทหารผ่นศึกชั้น 4 ที่มีบัตรทหารผ่านศึก รวมถึงผู้ได้รับพระราชทาน','Noexp','WEL');
insert into r_rp1853_instype values ('95','บุคคลในครอบครัวทหารผ่านศึกชั้น 4 รวมถึงผู้ได้รับพระราชทานเหรียญสมรภูมิ','Noexp','WEL');
insert into r_rp1853_instype values ('96','ทหารพราน','ตามวันหมดอายุของบัตรประจำตัว','WEL');
insert into r_rp1853_instype values ('97','บุคคลในครอบครัวทหารของกรมสวัสดิการ 3 เหล่าทัพ','Noexp','WEL');
insert into r_rp1853_instype values ('98','บุคคลในครอบครัวทหารผ่านศึกนอกประจำการบัตรชั้นที่ 1','Noexp','WEL');

update b_contract_plans set r_rp1853_instype_id = '0' where b_contract_plans_id = '6';
update b_contract_plans set r_rp1853_instype_id = '2120000000001' where b_contract_plans_id = '2';
update b_contract_plans set r_rp1853_instype_id = '2120000000002' where b_contract_plans_id = '2';
update b_contract_plans set r_rp1853_instype_id = '2120000000003' where b_contract_plans_id = '1';
update b_contract_plans set r_rp1853_instype_id = '2120000000004' where b_contract_plans_id = '1';
update b_contract_plans set r_rp1853_instype_id = '2120000000005' where b_contract_plans_id = '78';
update b_contract_plans set r_rp1853_instype_id = '2120000000006' where b_contract_plans_id = '78';
update b_contract_plans set r_rp1853_instype_id = '2120000000007' where b_contract_plans_id = '78';
update b_contract_plans set r_rp1853_instype_id = '2120000000008' where b_contract_plans_id = '78';
update b_contract_plans set r_rp1853_instype_id = '2120000000009' where b_contract_plans_id = '71';
update b_contract_plans set r_rp1853_instype_id = '2120000000010' where b_contract_plans_id = '71';
update b_contract_plans set r_rp1853_instype_id = '2120000000011' where b_contract_plans_id = '71';
update b_contract_plans set r_rp1853_instype_id = '2120000000012' where b_contract_plans_id = '77';
update b_contract_plans set r_rp1853_instype_id = '2120000000013' where b_contract_plans_id = '77';
update b_contract_plans set r_rp1853_instype_id = '2120000000014' where b_contract_plans_id = '77';
update b_contract_plans set r_rp1853_instype_id = '2120000000015' where b_contract_plans_id = '72';
update b_contract_plans set r_rp1853_instype_id = '2120000000016' where b_contract_plans_id = '72';
update b_contract_plans set r_rp1853_instype_id = '2120000000017' where b_contract_plans_id = '72';
update b_contract_plans set r_rp1853_instype_id = '2120000000018' where b_contract_plans_id = '82';
update b_contract_plans set r_rp1853_instype_id = '2120000000019' where b_contract_plans_id = '82';
update b_contract_plans set r_rp1853_instype_id = '2120000000020' where b_contract_plans_id = '82';
update b_contract_plans set r_rp1853_instype_id = '2120000000021' where b_contract_plans_id = '81';
update b_contract_plans set r_rp1853_instype_id = '2120000000022' where b_contract_plans_id = '81';
update b_contract_plans set r_rp1853_instype_id = '2120000000023' where b_contract_plans_id = '81';
update b_contract_plans set r_rp1853_instype_id = '2120000000024' where b_contract_plans_id = '73';
update b_contract_plans set r_rp1853_instype_id = '2120000000025' where b_contract_plans_id = '73';
update b_contract_plans set r_rp1853_instype_id = '2120000000026' where b_contract_plans_id = '73';
update b_contract_plans set r_rp1853_instype_id = '2120000000027' where b_contract_plans_id = '74';
update b_contract_plans set r_rp1853_instype_id = '2120000000028' where b_contract_plans_id = '74';
update b_contract_plans set r_rp1853_instype_id = '2120000000029' where b_contract_plans_id = '74';
update b_contract_plans set r_rp1853_instype_id = '2120000000030' where b_contract_plans_id = '9';
update b_contract_plans set r_rp1853_instype_id = '2120000000031' where b_contract_plans_id = '9';
update b_contract_plans set r_rp1853_instype_id = '2120000000032' where b_contract_plans_id = '9';
update b_contract_plans set r_rp1853_instype_id = '2120000000033' where b_contract_plans_id = '76';
update b_contract_plans set r_rp1853_instype_id = '2120000000034' where b_contract_plans_id = '76';
update b_contract_plans set r_rp1853_instype_id = '2120000000035' where b_contract_plans_id = '76';
update b_contract_plans set r_rp1853_instype_id = '2120000000036' where b_contract_plans_id = '78';
update b_contract_plans set r_rp1853_instype_id = '2120000000037' where b_contract_plans_id = '78';
update b_contract_plans set r_rp1853_instype_id = '2120000000038' where b_contract_plans_id = '78';
update b_contract_plans set r_rp1853_instype_id = '2120000000039' where b_contract_plans_id = '7';
update b_contract_plans set r_rp1853_instype_id = '2120000000040' where b_contract_plans_id = '7';
update b_contract_plans set r_rp1853_instype_id = '2120000000041' where b_contract_plans_id = '79';
update b_contract_plans set r_rp1853_instype_id = '2120000000042' where b_contract_plans_id = '79';
update b_contract_plans set r_rp1853_instype_id = '2120000000043' where b_contract_plans_id = '78';
update b_contract_plans set r_rp1853_instype_id = '2120000000044' where b_contract_plans_id = '78';
update b_contract_plans set r_rp1853_instype_id = '2121340126613' where b_contract_plans_id = '78';


------------------------------------------------------
create table r_rp1853_surveilcomplicate (
id varchar(255),
name varchar(255),
description varchar(255) 
); 
ALTER TABLE ONLY r_rp1853_surveilcomplicate ADD CONSTRAINT r_rp1853_surveilcomplicate_pkey PRIMARY KEY (id); 

insert into r_rp1853_surveilcomplicate values ('221','Pneumonia','');
insert into r_rp1853_surveilcomplicate values ('222','Diarrhoea','');
insert into r_rp1853_surveilcomplicate values ('223','Encephalitis','');
insert into r_rp1853_surveilcomplicate values ('224','Otitis media','');
insert into r_rp1853_surveilcomplicate values ('225','อื่น ๆ','');
insert into r_rp1853_surveilcomplicate values ('226','ไม่ทราบ','');
insert into r_rp1853_surveilcomplicate values ('227','1 + 2','');
insert into r_rp1853_surveilcomplicate values ('228','1 + 3','');
insert into r_rp1853_surveilcomplicate values ('229','1 + 5','');
insert into r_rp1853_surveilcomplicate values ('371','Syphilis - Primary + Secondary','');
insert into r_rp1853_surveilcomplicate values ('372','Late+latent+VDRL 1:2,1:4,1;8','');
insert into r_rp1853_surveilcomplicate values ('413','Other VD - Trichononas,..etc.','');
insert into r_rp1853_surveilcomplicate values ('414','Other VD - Unknown','');
insert into r_rp1853_surveilcomplicate values ('981','รถยนต์โดยสาร คว่ำ/ชนกัน','');
insert into r_rp1853_surveilcomplicate values ('982','รถบรรทุก คว่ำ/ชนกัน','');
insert into r_rp1853_surveilcomplicate values ('983','รถเก๋ง คว่ำ/ชน','');
insert into r_rp1853_surveilcomplicate values ('984','รถจักรยานยนต์ คว่ำ/ชนกัน','');
insert into r_rp1853_surveilcomplicate values ('985','คนเดินถนน ถูกรถยนต์ชน','');
insert into r_rp1853_surveilcomplicate values ('986','คนเดินถนน ถูกรถจักรยานยนต์ชน','');
insert into r_rp1853_surveilcomplicate values ('987','คนขี่จักรยานถูกรถยนต์/จักรฯชน','');
insert into r_rp1853_surveilcomplicate values ('988','ตกจากรถยนต์/รถจักรยานยนต์','');
insert into r_rp1853_surveilcomplicate values ('989','ไม่ทราบทุกประเภท','');
------------------------------------------------------
alter table t_surveil add r_rp1853_surveiloganism_id varchar(255);
alter table t_surveil add r_rp1853_surveilcomplicate_id varchar(255); 

create table r_rp1853_surveiloganism (
id varchar(255),
name varchar(255)
); 
ALTER TABLE ONLY r_rp1853_surveiloganism ADD CONSTRAINT r_rp1853_surveiloganism_pkey PRIMARY KEY (id); 

insert into r_rp1853_surveiloganism values ('011','Cholera ELTOR INABA (E.I)');
insert into r_rp1853_surveiloganism values ('012','Cholera ELTOR OGAWA (E.O)');
insert into r_rp1853_surveiloganism values ('013','Cholera ELTOR HIROSHIMA (E.H)');
insert into r_rp1853_surveiloganism values ('016','Cholera 0139 (0139)');
insert into r_rp1853_surveiloganism values ('019','Unknown');
insert into r_rp1853_surveiloganism values ('031','V.P (Vibrio Parahaemolyticus)');
insert into r_rp1853_surveiloganism values ('032','Salmonella');
insert into r_rp1853_surveiloganism values ('033','Staphylococcus');
insert into r_rp1853_surveiloganism values ('034','Botulism (Clostridium botu.)');
insert into r_rp1853_surveiloganism values ('035','Clostridium Perfringens');
insert into r_rp1853_surveiloganism values ('039','Unknown');
insert into r_rp1853_surveiloganism values ('301','PF (Plasmodium Falciparum)');
insert into r_rp1853_surveiloganism values ('302','PV (Plasmodium Vivax)');
insert into r_rp1853_surveiloganism values ('303','PM (Plssmodium Malariae)');
insert into r_rp1853_surveiloganism values ('304','Mixed');
insert into r_rp1853_surveiloganism values ('309','Unknown');
insert into r_rp1853_surveiloganism values ('341','T.B. Lymph node');
insert into r_rp1853_surveiloganism values ('342','T.B. Spine');
insert into r_rp1853_surveiloganism values ('343','T.B. Hips, Bones, Joints');
insert into r_rp1853_surveiloganism values ('344','T.B. Peritonium');
insert into r_rp1853_surveiloganism values ('345','T.B. Skin');
insert into r_rp1853_surveiloganism values ('346','T.B. Gastro-Intestinal Tract');
insert into r_rp1853_surveiloganism values ('347','T.B. Genito-Urinary organs');
insert into r_rp1853_surveiloganism values ('348','T.B. other organs');
insert into r_rp1853_surveiloganism values ('349','Unknown');
insert into r_rp1853_surveiloganism values ('471','Organophosphate group');
insert into r_rp1853_surveiloganism values ('472','Carbamate group');
insert into r_rp1853_surveiloganism values ('473','Chlorinated Hydrocarbon');
insert into r_rp1853_surveiloganism values ('474','Pyrethrum, Syn. Pyrethroids');
insert into r_rp1853_surveiloganism values ('475','Herbicide');
insert into r_rp1853_surveiloganism values ('476','Paris Green, 1080 Zine Phos.');
insert into r_rp1853_surveiloganism values ('477','ไม่ทราบว?าสารเคมีอยู่ในกลุ่มใด');
insert into r_rp1853_surveiloganism values ('479','ไม่ระบุชนิด');
insert into r_rp1853_surveiloganism values ('491','Manganese poisoning');
insert into r_rp1853_surveiloganism values ('492','Hg poisoning');
insert into r_rp1853_surveiloganism values ('493','Arsenic poisoning');
insert into r_rp1853_surveiloganism values ('494','พิษจากโลหะอื่นๆ');
insert into r_rp1853_surveiloganism values ('499','Unknown');
insert into r_rp1853_surveiloganism values ('501','เบนซิน');
insert into r_rp1853_surveiloganism values ('502','โทลูอิน');
insert into r_rp1853_surveiloganism values ('503','โซลิน');
insert into r_rp1853_surveiloganism values ('504','อื่นๆ');
insert into r_rp1853_surveiloganism values ('509','ไม่ทราบ, ไม่ระบ ุ');
insert into r_rp1853_surveiloganism values ('561','งูเห่า');
insert into r_rp1853_surveiloganism values ('562','งูแมวเซา');
insert into r_rp1853_surveiloganism values ('563','งูกะปะ');
insert into r_rp1853_surveiloganism values ('564','งูสามเหลี่ยม');
insert into r_rp1853_surveiloganism values ('565','งูเขียวหางไหม้');
insert into r_rp1853_surveiloganism values ('566','งูจงอาง');
insert into r_rp1853_surveiloganism values ('567','งูทะเล');
insert into r_rp1853_surveiloganism values ('568','งูพิษอื่นๆ');
insert into r_rp1853_surveiloganism values ('569','ไม่ทราบชนิดง ู');
insert into r_rp1853_surveiloganism values ('571','ยาปฏิชีวนะ ชนิดกิน-ฉีด');
insert into r_rp1853_surveiloganism values ('572','ยานอนหลับ');
insert into r_rp1853_surveiloganism values ('573','ยากล่อมประสาท');
insert into r_rp1853_surveiloganism values ('574','ยาระงับปวด');
insert into r_rp1853_surveiloganism values ('575','ยาคุมกำเนิด');
insert into r_rp1853_surveiloganism values ('576','ยาแก้แพ้');
insert into r_rp1853_surveiloganism values ('577','ยาทาภายนอก');
insert into r_rp1853_surveiloganism values ('578','ยาบำรุงร่างกาย');
insert into r_rp1853_surveiloganism values ('579','ไม่ทราบ, ไม่ระบ ุ');
insert into r_rp1853_surveiloganism values ('601','ยาฆ่าแมลง');
insert into r_rp1853_surveiloganism values ('602','ยาฆ่าหญ้า');
insert into r_rp1853_surveiloganism values ('603','ยาเบื่อหนู');
insert into r_rp1853_surveiloganism values ('604','ยาเบื่อสุนัข');
insert into r_rp1853_surveiloganism values ('605','ยาเบื่อปูนา');
insert into r_rp1853_surveiloganism values ('606','กรด, ด่าง');
insert into r_rp1853_surveiloganism values ('607','ยาระงับ-กล่อมประสาท, ยานอนหลับ');
insert into r_rp1853_surveiloganism values ('608','สารเคมีอื่นๆ');
insert into r_rp1853_surveiloganism values ('609','ไม่ทราบ, ไม่ระบ ุ');
insert into r_rp1853_surveiloganism values ('641','ฝุ่นแร่');
insert into r_rp1853_surveiloganism values ('642','ฝุ่นหิน');
insert into r_rp1853_surveiloganism values ('643','เส้นใยแอสเบสตอส');
insert into r_rp1853_surveiloganism values ('644','ฝุ่นชานอ้อย');
insert into r_rp1853_surveiloganism values ('645','ฝุ่นฝ้าย');
insert into r_rp1853_surveiloganism values ('646','ฝุ่นถ่าน');
insert into r_rp1853_surveiloganism values ('647','อื่นๆ');
insert into r_rp1853_surveiloganism values ('649','ไม่ทราบ, ไม่ระบุ');
insert into r_rp1853_surveiloganism values ('671','Caission ''s Disease');
insert into r_rp1853_surveiloganism values ('672','เสียง');
insert into r_rp1853_surveiloganism values ('673','แสง');
insert into r_rp1853_surveiloganism values ('674','ความร้อน');
insert into r_rp1853_surveiloganism values ('675','ความสั่นสะเทือน');
insert into r_rp1853_surveiloganism values ('676','กัมมันตภาพรังสี');
insert into r_rp1853_surveiloganism values ('677','อื่นๆ');
insert into r_rp1853_surveiloganism values ('679','ไม่ทราบ, ไม่ระบุ');
------------------------------------------------------
CREATE TABLE b_nhso_map_drug (
    b_nhso_map_drug_id character varying(255) NOT NULL,
    f_nhso_drug_id character varying(255),
    b_item_id character varying(255),
    b_nhso_drugcode24_id character varying(255)
);
ALTER TABLE ONLY b_nhso_map_drug
    ADD CONSTRAINT b_nhso_map_drug_pkey PRIMARY KEY (b_nhso_map_drug_id);

CREATE UNIQUE INDEX  b_nhso_map_drug_key ON b_nhso_map_drug(b_item_id);

ALTER TABLE "public"."f_health_anc_section" RENAME "health_anc_section_description" TO "health_anc_section_description_old";
ALTER TABLE "public"."f_health_anc_section"
	ADD COLUMN "health_anc_section_description" varchar(255) NULL;
UPDATE "public"."f_health_anc_section"
SET "health_anc_section_description"='ช่วงที่ 4 อายุครรภ์ 36-39 สัปดาห์'
WHERE "f_health_anc_section_id"='4';
UPDATE "public"."f_health_anc_section"
SET "health_anc_section_description"='ช่วงที่ 1 อายุครรภ์ 1-27 สัปดาห์(ควรก่อน 12 สัปดาห์)'
WHERE "f_health_anc_section_id"='1';
UPDATE "public"."f_health_anc_section"
SET "health_anc_section_description"='ช่วงที่ 2 อายุครรภ์ 28-31 สัปดาห์'
WHERE "f_health_anc_section_id"='2';
UPDATE "public"."f_health_anc_section"
SET "health_anc_section_description"='ช่วงที่ 3 อายุครรภ์ 32-35 สัปดาห์'
WHERE "f_health_anc_section_id"='3';
ALTER TABLE "public"."f_patient_nation"
	ADD COLUMN "r_rp1853_nation_id" varchar(255) NULL;
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('076', 'ไนจีเรีย', '076');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('077', 'สหรัฐอาหรับเอมิเรตส์', '077');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('078', 'กินี', '078');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('081', 'ปาปัวนิวกินี', '081');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('082', 'ม้ง', '082');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('083', 'เมี่ยน', '083');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('093', 'รอให้สัญชาติไทย*', '093');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('100', 'อัฟกัน', '100');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('101', 'บาห์เรน', '101');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('102', 'ภูฏาน', '102');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('103', 'จอร์แดน', '103');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('104', 'เกาหลีเหนือ', '104');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('105', 'มัลดีฟ', '105');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('106', 'มองโกเลีย', '106');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('107', 'โอมาน', '107');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('108', 'กาตาร์', '108');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('109', 'เยเมน', '109');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('110', 'เยเมน(ใต้)**', '110');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('111', 'หมู่เกาะฟิจิ', '111');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('112', 'คิริบาส', '112');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('113', 'นาอูรู', '113');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('114', 'หมู่เกาะโซโลมอน', '114');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('115', 'ตองก้า', '115');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('116', 'ตูวาลู', '116');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('117', 'วานูอาตู', '117');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('118', 'ซามัว', '118');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('119', 'แอลเบเนีย', '119');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('120', 'อันดอร์รา', '120');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('121', 'เยอรมนีตะวันออก**', '121');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('122', 'ไอซ์แลนด์', '122');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('123', 'ลิกเตนสไตน์', '123');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('124', 'โมนาโก', '124');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('125', 'ซานมารีโน', '125');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('126', 'บริติช', '126');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('127', 'แอลจีเรีย', '127');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('128', 'แองโกลา', '128');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('129', 'เบนิน', '129');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('130', 'บอตสวานา', '130');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('131', 'บูร์กินาฟาโซ', '131');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('132', 'บุรุนดี', '132');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('133', 'แคเมอรูน', '133');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('134', 'เคปเวิร์ด', '134');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('135', 'แอฟริกากลาง', '135');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('136', 'ชาด', '136');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('137', 'คอโมโรส', '137');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('138', 'คองโก', '138');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('139', 'โกตดิวัวร์', '139');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('140', 'จิบูตี', '140');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('141', 'อิเควทอเรียลกินี', '141');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('142', 'กาบอง', '142');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('143', 'แกมเบีย', '143');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('144', 'กานา', '144');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('145', 'กินีบีสเซา', '145');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('146', 'เลโซโท', '146');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('147', 'ไลบีเรีย', '147');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('148', 'ลิเบีย', '148');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('149', 'มาลากาซี', '149');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('150', 'มาลาวี', '150');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('151', 'มาลี', '151');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('152', 'มอริเตเนีย', '152');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('153', 'มอริเชียส', '153');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('154', 'โมร็อกโก', '154');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('155', 'โมซัมบิก', '155');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('156', 'ไนเจอร์', '156');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('157', 'รวันดา', '157');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('158', 'เซาโตเมและปรินซิเป', '158');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('159', 'เซเนกัล', '159');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('160', 'เซเชลส์', '160');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('161', 'เซียร์ราลีโอน', '161');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('162', 'โซมาลี', '162');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('163', 'ซูดาน', '163');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('164', 'สวาซี', '164');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('165', 'แทนซาเนีย', '165');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('166', 'โตโก', '166');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('167', 'ตูนิเซีย', '167');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('168', 'ยูกันดา', '168');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('169', 'ซาอีร์', '169');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('170', 'แซมเบีย', '170');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('171', 'ซิมบับเว', '171');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('172', 'แอนติกาและบาร์บูดา', '172');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('173', 'บาฮามาส', '173');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('174', 'บาร์เบโดส', '174');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('175', 'เบลิซ', '175');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('176', 'คอสตาริกา', '176');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('177', 'โดมินิกา', '177');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('178', 'โดมินิกัน', '178');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('179', 'เอลซัลวาดอร์', '179');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('180', 'เกรเนดา', '180');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('181', 'กัวเตมาลา', '181');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('182', 'เฮติ', '182');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('183', 'ฮอนดูรัส', '183');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('184', 'จาเมกา', '184');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('185', 'นิการากัว', '185');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('186', 'เซนต์คิตส์และเนวิส', '186');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('187', 'เซนต์ลูเซีย', '187');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('188', 'เซนต์วินเซนต์และเกรนาดีนส์', '188');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('189', 'ตรินิแดดและโตเบโก', '189');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('190', 'โบลีเวีย', '190');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('191', 'เอกวาดอร์', '191');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('192', 'กายอานา', '192');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('193', 'ปารากวัย', '193');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('194', 'ซูรินาเม', '194');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('195', 'อาหรับ', '195');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('196', 'คะฉิ่น', '196');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('197', 'ว้า', '197');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('198', 'ไทยใหญ่', '198');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('199', 'ไทยลื้อ', '199');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('200', 'ขมุ', '200');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('201', 'ตองสู', '201');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('202', 'เงี้ยว**', '202');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('203', 'ละว้า', '203');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('204', 'แม้ว*', '204');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('205', 'ปะหร่อง', '205');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('206', 'ถิ่น', '206');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('207', 'ปะโอ', '207');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('208', 'มอญ', '208');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('209', 'มลาบรี', '209');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('210', 'เฮาะ**', '210');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('211', 'สก๊อตแลนด์**', '211');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('212', 'จีน (จีนฮ่ออิสระ)', '212');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('213', 'จีนอพยพ**', '213');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('214', 'จีน (จีนฮ่ออพยพ)', '214');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('215', 'ไต้หวัน**', '215');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('216', 'ยูเครน', '216');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('217', 'อาณานิคมอังกฤษ**', '217');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('218', 'ดูไบ**', '218');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('219', 'จีน(ฮ่องกง)', '219');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('220', 'จีน(ไต้หวัน)', '220');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('221', 'โครเอเชีย', '221');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('222', 'บริทิธ**', '222');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('223', 'คาซัค', '223');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('224', 'อาร์เมเนีย', '224');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('225', 'อาเซอร์ไบจาน', '225');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('226', 'จอร์เจีย', '226');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('227', 'คีร์กีซ', '227');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('228', 'ทาจิก', '228');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('229', 'อุซเบก', '229');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('230', 'หมู่เกาะมาร์แชลล์', '230');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('231', 'ไมโครนีเซีย', '231');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('232', 'ปาเลา', '232');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('233', 'เบลารุส', '233');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('234', 'บอสเนียและเฮอร์เซโกวีนา', '234');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('235', 'เติร์กเมน', '235');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('236', 'เอสโตเนีย', '236');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('237', 'ลัตเวีย', '237');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('238', 'ลิทัวเนีย', '238');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('239', 'มาซิโดเนีย', '239');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('240', 'มอลโดวา', '240');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('241', 'สโลวัก', '241');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('242', 'สโลวีน', '242');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('243', 'เอริเทรีย', '243');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('244', 'นามิเบีย', '244');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('245', 'โบลิเวีย', '245');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('246', 'หมู่เกาะคุก', '246');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('247', 'เนปาล (เนปาลอพยพ)', '247');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('248', 'มอญ(ผู้พลัดถิ่นสัญชาติพม่า)', '248');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('249', 'ไทยใหญ่(ผู้พลัดถิ่นสัญชาติพม่า', '249');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('250', 'เวียดนาม(ญวนอพยพ)', '250');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('251', 'มาเลเชีย(อดีต จคม.)', '251');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('252', 'จีน(อดีต จคม.)', '252');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('253', 'สิงคโปร์(อดีต จคม.)', '253');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('254', 'กะเหรี่ยง(ผู้หลบหนีเข้าเมือง)', '254');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('255', 'มอญ(ผู้หลบหนีเข้าเมือง)', '255');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('256', 'ไทยใหญ่(ผู้หลบหนีเข้าเมือง)', '256');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('257', 'กัมพูชา(ผู้หลบหนีเข้าเมือง)', '257');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('258', 'มอญ(ชุมชนบนพื้นที่สูง)', '258');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('259', 'กะเหรี่ยง(ชุมชนบนพื้นที่สูง)', '259');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('260', 'ปาเลสไตน์', '260');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('261', 'ติมอร์ตะวันออก', '261');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('262', 'สละสัญชาติไทย', '262');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('263', 'เซอร์เบีย แอนด์ มอนเตเนโกร', '263');
INSERT INTO public.f_patient_nation(f_patient_nation_id, patient_nation_description, r_rp1853_nation_id) VALUES('989', 'บุคคลที่ไม่มีสถานะทางทะเบียน', '989');

CREATE TABLE "r_rp1853_nation"  ( 
	"id"  	varchar(255) NOT NULL,
	"name"	varchar(255) NULL 
	);
ALTER TABLE "r_rp1853_nation"
	ADD CONSTRAINT "r_rp1853_nation_pkey"
	PRIMARY KEY ("id");
INSERT INTO public.r_rp1853_nation(id, name) VALUES('000','ไม่ระบุ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('001','อังกฤษ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('002','ปอร์ตุเกส');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('003','ดัตช์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('004','เยอรมัน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('005','ฝรั่งเศส');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('006','เดนมาร์ก');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('007','สวีเดน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('008','สวิสเซอร์แลนด์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('009','อิตาลี');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('010','นอร์เวย์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('011','ออสเตรีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('012','ไอริช');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('013','ฟินแลนด์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('014','เบลเยี่ยม');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('015','เสปญ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('016','รัสเซีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('017','โปแลนด์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('018','เชคโกสโลวาเกีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('019','ฮังการี');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('020','กรีก');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('021','ยูโกสลาฟ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('022','ลักเซมเบอร์ก');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('023','วาติกัน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('024','มอลต้า');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('025','ลีซู');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('026','บัลแกเรีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('027','โรมาเนีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('028','ไซปรัส');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('029','อเมริกา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('030','แคนาดา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('031','เม็กซิโก');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('032','คิวบา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('033','อาร์เจนตินา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('034','บราซิล');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('035','ชิลี');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('036','อาข่า');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('037','โคลัมเบีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('038','ลั๊ว');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('039','เปรู');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('040','ปานามา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('041','อุรุกวัย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('042','เวเนสุเอล่า');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('043','เปอร์โตริโก้');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('044','จีน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('045','อินเดีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('046','เวียดนาม');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('047','ญี่ปุ่น');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('048','พม่า');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('049','ฟิลิปปินส์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('050','มาเลเซีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('051','อินโดนีเซีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('052','ปากีสถาน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('053','เกาหลีใต้');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('054','สิงคโปร์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('055','เนปาล');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('056','ลาว');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('057','กัมพูชา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('058','ศรีลังกา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('059','ซาอุดิอารเบีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('060','อิสราเอล');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('061','เลบานอน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('062','อิหร่าน(เปอร์เซีย)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('063','ตุรกี');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('064','บังคลาเทศ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('065','ถูกถอนสัญชาติ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('066','ซิเรีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('067','อิรัค');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('068','คูเวต');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('069','บรูไน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('070','อาฟริกาใต้');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('071','กะเหรี่ยง');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('072','ลาหู่');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('073','เคนยา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('074','อิยิปต์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('075','เอธิโอเปีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('076','ไนจีเรีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('077','สหรัฐอาหรับเอมิเรตส์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('078','กินี');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('079','ออสเตรเลีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('080','นิวซีแลนด์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('081','ปาปัวนิวกินี');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('082','ม้ง');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('083','เมี่ยน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('084','ชาวเขาที่ไม่ได้รับสัญชาติไทย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('086','จีนฮ่อ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('087','อดีตทหารจีนคณะชาติ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('088','พม่าพลัดถิ่น');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('089','ผู้อพยพเชื้อสายจากกัมพูชา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('090','ผู้อพยพอินโดจีนสัญชาติลาว');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('091','ผู้อพยพอินโดจีนสัญชาติกัมพูชา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('092','ผู้อพยพอินโดจีนสัญชาติเวียดนาม');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('093','รอให้สัญชาติไทย*');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('094','ไทย-อิสลาม, อิสลาม-ไทย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('095','ไทย-จีน, จีน-ไทย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('096','ไร้สัญชาติ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('994','อื่นๆ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('098','ไม่ได้สัญชาติไทยตาม ปว.337');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('099','ไทย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('100','อัฟกัน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('101','บาห์เรน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('102','ภูฏาน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('103','จอร์แดน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('104','เกาหลีเหนือ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('105','มัลดีฟ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('106','มองโกเลีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('107','โอมาน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('108','กาตาร์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('109','เยเมน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('110','เยเมน(ใต้)**');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('111','หมู่เกาะฟิจิ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('112','คิริบาส');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('113','นาอูรู');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('114','หมู่เกาะโซโลมอน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('115','ตองก้า');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('116','ตูวาลู');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('117','วานูอาตู');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('118','ซามัว');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('119','แอลเบเนีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('120','อันดอร์รา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('121','เยอรมนีตะวันออก**');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('122','ไอซ์แลนด์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('123','ลิกเตนสไตน์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('124','โมนาโก');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('125','ซานมารีโน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('126','บริติช');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('127','แอลจีเรีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('128','แองโกลา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('129','เบนิน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('130','บอตสวานา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('131','บูร์กินาฟาโซ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('132','บุรุนดี');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('133','แคเมอรูน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('134','เคปเวิร์ด');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('135','แอฟริกากลาง');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('136','ชาด');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('137','คอโมโรส');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('138','คองโก');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('139','โกตดิวัวร์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('140','จิบูตี');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('141','อิเควทอเรียลกินี');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('142','กาบอง');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('143','แกมเบีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('144','กานา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('145','กินีบีสเซา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('146','เลโซโท');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('147','ไลบีเรีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('148','ลิเบีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('149','มาลากาซี');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('150','มาลาวี');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('151','มาลี');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('152','มอริเตเนีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('153','มอริเชียส');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('154','โมร็อกโก');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('155','โมซัมบิก');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('156','ไนเจอร์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('157','รวันดา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('158','เซาโตเมและปรินซิเป');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('159','เซเนกัล');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('160','เซเชลส์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('161','เซียร์ราลีโอน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('162','โซมาลี');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('163','ซูดาน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('164','สวาซี');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('165','แทนซาเนีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('166','โตโก');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('167','ตูนิเซีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('168','ยูกันดา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('169','ซาอีร์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('170','แซมเบีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('171','ซิมบับเว');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('172','แอนติกาและบาร์บูดา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('173','บาฮามาส');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('174','บาร์เบโดส');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('175','เบลิซ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('176','คอสตาริกา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('177','โดมินิกา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('178','โดมินิกัน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('179','เอลซัลวาดอร์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('180','เกรเนดา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('181','กัวเตมาลา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('182','เฮติ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('183','ฮอนดูรัส');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('184','จาเมกา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('185','นิการากัว');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('186','เซนต์คิตส์และเนวิส');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('187','เซนต์ลูเซีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('188','เซนต์วินเซนต์และเกรนาดีนส์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('189','ตรินิแดดและโตเบโก');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('190','โบลีเวีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('191','เอกวาดอร์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('192','กายอานา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('193','ปารากวัย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('194','ซูรินาเม');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('195','อาหรับ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('196','คะฉิ่น');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('197','ว้า');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('198','ไทยใหญ่');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('199','ไทยลื้อ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('200','ขมุ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('201','ตองสู');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('202','เงี้ยว**');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('203','ละว้า');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('204','แม้ว*');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('205','ปะหร่อง');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('206','ถิ่น');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('207','ปะโอ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('208','มอญ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('209','มลาบรี');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('210','เฮาะ**');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('211','สก๊อตแลนด์**');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('212','จีน (จีนฮ่ออิสระ)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('213','จีนอพยพ**');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('214','จีน (จีนฮ่ออพยพ)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('215','ไต้หวัน**');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('216','ยูเครน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('217','อาณานิคมอังกฤษ**');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('218','ดูไบ**');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('219','จีน(ฮ่องกง)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('220','จีน(ไต้หวัน)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('221','โครเอเชีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('222','บริทิธ**');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('223','คาซัค');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('224','อาร์เมเนีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('225','อาเซอร์ไบจาน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('226','จอร์เจีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('227','คีร์กีซ');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('228','ทาจิก');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('229','อุซเบก');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('230','หมู่เกาะมาร์แชลล์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('231','ไมโครนีเซีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('232','ปาเลา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('233','เบลารุส');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('234','บอสเนียและเฮอร์เซโกวีนา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('235','เติร์กเมน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('236','เอสโตเนีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('237','ลัตเวีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('238','ลิทัวเนีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('239','มาซิโดเนีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('240','มอลโดวา');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('241','สโลวัก');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('242','สโลวีน');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('243','เอริเทรีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('244','นามิเบีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('245','โบลิเวีย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('246','หมู่เกาะคุก');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('247','เนปาล (เนปาลอพยพ)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('248','มอญ(ผู้พลัดถิ่นสัญชาติพม่า)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('249','ไทยใหญ่(ผู้พลัดถิ่นสัญชาติพม่า');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('250','เวียดนาม(ญวนอพยพ)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('251','มาเลเชีย(อดีต จคม.)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('252','จีน(อดีต จคม.)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('253','สิงคโปร์(อดีต จคม.)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('254','กะเหรี่ยง(ผู้หลบหนีเข้าเมือง)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('255','มอญ(ผู้หลบหนีเข้าเมือง)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('256','ไทยใหญ่(ผู้หลบหนีเข้าเมือง)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('257','กัมพูชา(ผู้หลบหนีเข้าเมือง)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('258','มอญ(ชุมชนบนพื้นที่สูง)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('259','กะเหรี่ยง(ชุมชนบนพื้นที่สูง)');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('260','ปาเลสไตน์');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('261','ติมอร์ตะวันออก');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('262','สละสัญชาติไทย');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('263','เซอร์เบีย แอนด์ มอนเตเนโกร');
INSERT INTO public.r_rp1853_nation(id, name) VALUES('989','บุคคลที่ไม่มีสถานะทางทะเบียน');
UPDATE public.f_patient_nation SET r_rp1853_nation_id='000'    WHERE f_patient_nation_id='0';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='001'    WHERE f_patient_nation_id='01';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='002'    WHERE f_patient_nation_id='02';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='003'    WHERE f_patient_nation_id='03';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='004'    WHERE f_patient_nation_id='04';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='005'    WHERE f_patient_nation_id='05';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='006'    WHERE f_patient_nation_id='06';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='007'    WHERE f_patient_nation_id='07';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='008'    WHERE f_patient_nation_id='08';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='009'    WHERE f_patient_nation_id='09';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='010'    WHERE f_patient_nation_id='10';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='011'    WHERE f_patient_nation_id='11';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='012'    WHERE f_patient_nation_id='12';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='013'    WHERE f_patient_nation_id='13';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='014'    WHERE f_patient_nation_id='14';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='015'    WHERE f_patient_nation_id='15';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='016'    WHERE f_patient_nation_id='16';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='017'    WHERE f_patient_nation_id='17';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='018'    WHERE f_patient_nation_id='18';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='019'    WHERE f_patient_nation_id='19';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='020'    WHERE f_patient_nation_id='20';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='021'    WHERE f_patient_nation_id='21';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='022'    WHERE f_patient_nation_id='22';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='023'    WHERE f_patient_nation_id='23';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='024'    WHERE f_patient_nation_id='24';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='026'    WHERE f_patient_nation_id='26';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='027'    WHERE f_patient_nation_id='27';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='028'    WHERE f_patient_nation_id='28';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='029'    WHERE f_patient_nation_id='29';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='030'    WHERE f_patient_nation_id='30';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='031'    WHERE f_patient_nation_id='31';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='032'    WHERE f_patient_nation_id='32';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='033'    WHERE f_patient_nation_id='33';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='034'    WHERE f_patient_nation_id='34';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='035'    WHERE f_patient_nation_id='35';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='037'    WHERE f_patient_nation_id='37';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='039'    WHERE f_patient_nation_id='39';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='040'    WHERE f_patient_nation_id='40';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='041'    WHERE f_patient_nation_id='41';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='042'    WHERE f_patient_nation_id='42';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='043'    WHERE f_patient_nation_id='43';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='044'    WHERE f_patient_nation_id='44';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='045'    WHERE f_patient_nation_id='45';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='046'    WHERE f_patient_nation_id='46';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='047'    WHERE f_patient_nation_id='47';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='048'    WHERE f_patient_nation_id='48';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='049'    WHERE f_patient_nation_id='49';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='050'    WHERE f_patient_nation_id='50';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='051'    WHERE f_patient_nation_id='51';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='052'    WHERE f_patient_nation_id='52';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='053'    WHERE f_patient_nation_id='53';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='054'    WHERE f_patient_nation_id='54';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='055'    WHERE f_patient_nation_id='55';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='056'    WHERE f_patient_nation_id='56';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='057'    WHERE f_patient_nation_id='57';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='058'    WHERE f_patient_nation_id='58';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='059'    WHERE f_patient_nation_id='59';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='060'    WHERE f_patient_nation_id='60';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='061'    WHERE f_patient_nation_id='61';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='062'    WHERE f_patient_nation_id='62';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='063'    WHERE f_patient_nation_id='63';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='064'    WHERE f_patient_nation_id='64';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='066'    WHERE f_patient_nation_id='66';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='067'    WHERE f_patient_nation_id='67';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='068'    WHERE f_patient_nation_id='68';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='069'    WHERE f_patient_nation_id='69';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='070'    WHERE f_patient_nation_id='70';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='073'    WHERE f_patient_nation_id='73';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='074'    WHERE f_patient_nation_id='74';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='075'    WHERE f_patient_nation_id='75';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='079'    WHERE f_patient_nation_id='79';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='080'    WHERE f_patient_nation_id='80';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='084'    WHERE f_patient_nation_id='84';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='086'    WHERE f_patient_nation_id='86';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='087'    WHERE f_patient_nation_id='87';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='088'    WHERE f_patient_nation_id='88';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='090'    WHERE f_patient_nation_id='90';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='091'    WHERE f_patient_nation_id='91';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='092'    WHERE f_patient_nation_id='92';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='094'    WHERE f_patient_nation_id='94';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='095'    WHERE f_patient_nation_id='95';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='096'    WHERE f_patient_nation_id='96';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='994'    WHERE f_patient_nation_id='97';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='098'    WHERE f_patient_nation_id='98';
UPDATE public.f_patient_nation SET r_rp1853_nation_id='099'    WHERE f_patient_nation_id='99';

ALTER TABLE "b_item"
	ADD COLUMN "r_rp1253_charitem_id" varchar(255) NULL;
CREATE TABLE "r_rp1253_charitem" ( 
	"id"  	varchar(255) NOT NULL,
	"name"	varchar(255) NULL 
	);
ALTER TABLE "r_rp1253_charitem"
	ADD CONSTRAINT "r_rp1253_charitem_pkey"
	PRIMARY KEY ("id");
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('1', 'ค่าห้อง / ค่าอาหาร');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('2', 'อวัยวะเทียม / อุปกรณ์บำบัดรักษา');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('3', 'ยาและสารอาหารทางเส้นเลือดที่ใช้ใน รพ.');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('4', 'ยาที่นำไปใช้ต่อที่บ้าน');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('5', 'เวชภัณฑ์ที่ไม่ใช่ยา');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('6', 'บริการโลหิตและส่วนประกอบของโลหิต');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('7', 'ตรวจวินิจฉัยทางเทคนิคการแพทย์และพยาธิวิทยา');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('8', 'ตรวจวินิจฉัยและรักษาทางรังสีวิทยา');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('9', 'ตรวจวินิจฉัยโดยวิธีพิเศษอื่น ๆ');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('A', 'อุปกรณ์ของใช้และเครื่องมือทางการแพทย์');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('B', 'ทำหัตถการ และบริการวิสัญญี');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('C', 'ค่าบริการทางการพยาบาล');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('D', 'บริการทางทันตกรรม');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('E', 'บริการทางกายภาพบำบัด และเวชกรรมฟื้นฟู');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('F', 'บริการฝังเข็ม/การบำบัดของผู้ประกอบโรคศิลปะอื่น ๆ');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('G', 'ค่าห้องผ่าตัดและห้องคลอด');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('H', 'ค่าธรรมเนียมบุคลากรทางการแพทย์');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('I', 'บริการอื่นๆ และส่งเสริมป้องกันโรค');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('J', 'บริการอื่นๆ ที่ยังไม่จัดหมวด');
INSERT INTO "public"."r_rp1253_charitem"("id", "name")
VALUES('K', 'พรบ.');


INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('2501','บันทึกสิทธิการรักษา',' ');
ALTER TABLE t_health_nutrition DROP COLUMN r_rp1853_nutritionlevel_id;
ALTER TABLE b_item_service ADD COLUMN item_service_active varchar(255) default '1';
ALTER TABLE b_item_service ADD COLUMN item_service_record_date_time varchar(255) default '';

insert into f_patient_marriage_status values('9','ไม่ทราบ','9');

update t_patient set f_patient_discharge_status_id = '9' where f_patient_discharge_status_id not in ('1,2,3');
update t_patient set patient_discharge_date_time = patient_update_date_time where f_patient_discharge_status_id = '9';
update t_health_family set f_patient_discharge_status_id = '9' where f_patient_discharge_status_id not in ('1,2,3');
update t_health_family set patient_discharge_date_time = modify_date_time where f_patient_discharge_status_id = '9';

insert into b_health_epi_group values ('7409928669246','Flu2009','816','1');

update t_patient_appointment set r_rp1853_aptype_id = '181' where (r_rp1853_aptype_id = '' or r_rp1853_aptype_id is null);

update t_health_anc set modify_date_time = modify_date_time || ':00' where length(modify_date_time) = 16;
update t_health_anc set modify_date_time = record_date_time where length(modify_date_time) <> 19;

INSERT INTO s_version VALUES ('9701000000033', '33', 'Hospital OS, Community Edition', '3.9', '3.17.241108', '2552-11-24 11:20:00');

INSERT INTO s_script_update_log VALUES ('hospitalOS','updateV3_83.sql',(select current_date) || ','|| (select current_time),'ปรับแก้สำหรับ hospitalOS3.9');
