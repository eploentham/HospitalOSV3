-----------------------------------------------------------------------------------------------------------------------------------------------------------------
--neung from update_pcu_ph5 06/11/49

--เพิ่มฟิลด์เพื่อออกรายงาน 18 แฟ้ม
ALTER TABLE t_health_home ADD COLUMN home_serial serial;

--Update ข้อมูลวางแผนครอบครัว
update t_health_family_planing set health_family_planing_date = substring(t_visit.visit_begin_visit_time,0,11) 
from t_visit
where t_health_family_planing.t_visit_id = t_visit.t_visit_id;

--ตรวจสอบว่า HCIS NUMBER มีความยาวเกินหรือไม่
update t_health_family set health_family_hn_hcis = '' where length(health_family_hn_hcis) > 6 ;

--ตรวจสอบว่า HCIS Number ซ้ำกันหรือไม่
update t_health_family set health_family_hn_hcis = ''
from (select health_family_hn_hcis,count(health_family_hn_hcis) as count from t_health_family where health_family_hn_hcis <> '' group by health_family_hn_hcis) as sub1
where t_health_family.health_family_hn_hcis = sub1.health_family_hn_hcis and count > 1;

--อับเดตรหัส HCIS Number ให้เป็น 6 หลัก
update b_sequence_data set sequence_data_pattern = '000000',sequence_data_active = '1'
where b_sequence_data_id = 'hn_hcis';

--อับเดต หมายเลข HCIS ให้เป็น MAX
update b_sequence_data set sequence_data_value = sub1.max
from (select cast (max(to_number(t_health_family.health_family_hn_hcis,'999999')) as varchar) as max , cast('hn_hcis' as varchar) as id  from t_health_family where t_health_family.health_family_hn_hcis <> '') as sub1
where b_sequence_data.b_sequence_data_id = sub1.id ;

--อับเดตข้อมูล t_health_family ในส่วนที่ไม่ถูกต้อง
update t_health_family set health_family_hn_hcis = '00000'||health_family_hn_hcis
where length(health_family_hn_hcis) = 1;
update t_health_family set health_family_hn_hcis = '0000'||health_family_hn_hcis
where length(health_family_hn_hcis) = 2;
update t_health_family set health_family_hn_hcis = '000'||health_family_hn_hcis
where length(health_family_hn_hcis) = 3;
update t_health_family set health_family_hn_hcis = '00'||health_family_hn_hcis
where length(health_family_hn_hcis) = 4;
update t_health_family set health_family_hn_hcis = '0'||health_family_hn_hcis
where length(health_family_hn_hcis) = 5;

update t_health_village set village_tambon = q.health_home_tambon
from (select t_health_village.village_moo,t_health_home.health_home_tambon,t_health_home.health_home_amphur,t_health_home.health_home_changwat 
from t_health_village INNER JOIN t_health_home ON t_health_village.t_health_village_id = t_health_home.t_health_village_id 
and (t_health_village.village_moo <> '0' and t_health_village.village_moo <> '00')
group by t_health_village.village_moo,t_health_home.health_home_tambon,t_health_home.health_home_amphur,t_health_home.health_home_changwat
order by t_health_village.village_moo) as q
where  q.village_moo = t_health_village.village_moo  and village_tambon = '' ;

update t_health_village set village_ampur = q.health_home_amphur
from (select t_health_village.village_moo,t_health_home.health_home_tambon,t_health_home.health_home_amphur,t_health_home.health_home_changwat 
from t_health_village INNER JOIN t_health_home ON t_health_village.t_health_village_id = t_health_home.t_health_village_id 
and (t_health_village.village_moo <> '0' and t_health_village.village_moo <> '00')
group by t_health_village.village_moo,t_health_home.health_home_tambon,t_health_home.health_home_amphur,t_health_home.health_home_changwat
order by t_health_village.village_moo) as q
where  q.village_moo = t_health_village.village_moo and village_ampur = '';

update t_health_village set village_changwat = q.health_home_changwat
from (select t_health_village.village_moo,t_health_home.health_home_tambon,t_health_home.health_home_amphur,t_health_home.health_home_changwat 
from t_health_village INNER JOIN t_health_home ON t_health_village.t_health_village_id = t_health_home.t_health_village_id 
and (t_health_village.village_moo <> '0' and t_health_village.village_moo <> '00')
group by t_health_village.village_moo,t_health_home.health_home_tambon,t_health_home.health_home_amphur,t_health_home.health_home_changwat
order by t_health_village.village_moo) as q
where  q.village_moo = t_health_village.village_moo and  village_changwat = '';

--henbe 30/11/2549 ยังขาดข้อมูลในส่วนนี้ทำให้การแสดงผลในหน้าสิทธิการมองเห็นแสดงได้ไม่ครบ
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('0812', 'พิมพ์ป้ายชื่อผู้ป่วยใน', '');

--ปิดแล้วห้ามแก้ไขเป็นอันขาด
insert into s_script_update_log values ('hospitalOS','updateV3_73.sql',(select current_date) || ','|| (select current_time),'เปลี่ยนเวอร์ชันจาก 3.7 เป็น 3.7 หลังจากหล่มสัก');
------
INSERT INTO s_version VALUES ('9701000000019', '29', 'Hospital OS, Community Edition', '3.7.250706', '3.15.061106', '2549-11-06 12:25:00');
---