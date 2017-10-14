drop table f_health_nutrition_level;
UPDATE b_health_service_type SET service_type_description = 'หลังตรวจเลือด' WHERE b_health_service_type_id = '7252364274730';
DELETE from b_school_class where b_school_class_id = '7337986584727';
alter table  t_health_dental add  dental_false_teeth_need varchar(255) default '';

INSERT INTO s_health_version VALUES ('9710000000003', '3', 'Health, Community Edition', '0.03.1148', '0.03.1148', '2548-11-08 12:00:00');
------เตรียมแอ่วเหนือ-----------------------------------------------------------------------------------------0.03

insert into s_script_update_log values ('pcu','update_pcu_ph3.sql',(select current_date) || ','|| (select current_time),'update PCU ph2 -> ph3');
