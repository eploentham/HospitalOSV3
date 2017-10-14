create table backup_t_patient_payment as 
select * from t_patient_payment;

update t_patient_payment set 
patient_payment_sub_hospital = patient_payment_main_hospital
,patient_payment_main_hospital = patient_payment_sub_hospital 
where t_patient_payment.patient_payment_sub_hospital in ( select b_visit_office_id from b_site )
and t_patient_payment.patient_payment_main_hospital not in ( select b_visit_office_id from b_site );

create table backup_t_visit_payment as 
select * from t_visit_payment;

update t_visit_payment set 
visit_payment_sub_hospital = visit_payment_main_hospital
,visit_payment_main_hospital = visit_payment_sub_hospital
where t_visit_payment.visit_payment_sub_hospital in ( select b_visit_office_id from b_site )
and t_visit_payment.visit_payment_main_hospital not in ( select b_visit_office_id from b_site );

--ลบข้อมูลจากการ Repair เดิม 
delete from t_nhso_service_pp 
where record_date_time like '%00:00:00';

--ลบข้อมูล service ที่ไม่มีชื่อกิจกรรม 
delete from t_nhso_service_pp 
where pp_name = '';
--ลบข้อมูล service ของ ANC ที่ไม่สัมพันธ์กัน
delete from t_nhso_service_pp 
where t_nhso_service_pp_id in (select t_nhso_service_pp.t_nhso_service_pp_id 
from t_nhso_service_pp inner join t_health_anc on t_health_anc.t_health_anc_id = t_nhso_service_pp.pp_id
where t_nhso_service_pp.t_visit_id <> t_health_anc.t_visit_id);
--ลบข้อมูล service ของ CONSULT ที่ไม่สัมพันธ์กัน
delete from t_nhso_service_pp
where t_nhso_service_pp.t_nhso_service_pp_id in (select t_nhso_service_pp.t_nhso_service_pp_id from t_health_counsel 
inner join t_nhso_service_pp on t_health_counsel.t_health_counsel_id = t_nhso_service_pp.pp_id
where t_nhso_service_pp.t_visit_id <> t_health_counsel.t_visit_id);
--ลบข้อมูล service ของ DENT ที่ไม่สัมพันธ์กัน
delete from t_nhso_service_pp
where t_nhso_service_pp.t_nhso_service_pp_id in (select t_nhso_service_pp.t_nhso_service_pp_id
from t_nhso_dental_adult inner join t_health_dental on t_health_dental.t_health_dental_id = t_nhso_dental_adult.t_health_dental_id
inner join t_nhso_service_pp on t_nhso_dental_adult.t_nhso_dental_adult_id = t_nhso_service_pp.pp_id
where t_nhso_service_pp.t_visit_id <> t_health_dental.t_visit_id);
--ลบข้อมูล service ของ CANCER,PAPSMEAR ที่ไม่สัมพันธ์กัน
delete from t_nhso_service_pp 
where t_nhso_service_pp.t_nhso_service_pp_id in (select t_nhso_service_pp.t_nhso_service_pp_id
from t_nhso_screen_cancer inner join t_nhso_service_pp 
on t_nhso_screen_cancer.t_nhso_screen_cancer_id = t_nhso_service_pp.pp_id
where t_nhso_screen_cancer.t_visit_id <> t_nhso_service_pp.t_visit_id);
--ลบข้อมูล service ของ DENTCHILD ที่ไม่สัมพันธ์กัน
delete from t_nhso_service_pp 
where t_nhso_service_pp.t_nhso_service_pp_id in (select t_nhso_service_pp.t_nhso_service_pp_id
from t_health_dental inner join t_nhso_dental on t_health_dental.t_health_dental_id = t_nhso_dental.t_health_dental_id
inner join t_nhso_service_pp on t_nhso_dental.t_nhso_dental_id = t_nhso_service_pp.pp_id
where t_health_dental.t_visit_id <> t_nhso_service_pp.t_visit_id);
--ลบข้อมูล service ของ DEPRESSION ที่ไม่สัมพันธ์กัน
delete from t_nhso_service_pp 
where t_nhso_service_pp.t_nhso_service_pp_id in (select t_nhso_service_pp.t_nhso_service_pp_id from t_nhso_depression 
inner join t_nhso_service_pp on t_nhso_depression.t_nhso_depression_id = t_nhso_service_pp.pp_id 
where t_nhso_depression.t_visit_id <> t_nhso_service_pp.t_visit_id);
--ลบข้อมูล service ของ DMHT ที่ไม่สัมพันธ์กัน
delete from t_nhso_service_pp 
where t_nhso_service_pp.t_nhso_service_pp_id in (select t_nhso_service_pp.t_nhso_service_pp_id from t_nhso_dmht_pp 
inner join t_nhso_service_pp on t_nhso_dmht_pp.t_nhso_dmht_pp_id = t_nhso_service_pp.pp_id 
where t_nhso_dmht_pp.t_visit_id <> t_nhso_service_pp.t_visit_id);
--ลบข้อมูล service ของ EPI ที่ไม่สัมพันธ์กัน
delete from t_nhso_service_pp 
where t_nhso_service_pp.t_nhso_service_pp_id in (select t_nhso_service_pp.t_nhso_service_pp_id from t_health_epi 
inner join t_nhso_service_pp on t_health_epi.t_health_epi_id = t_nhso_service_pp.pp_id 
where t_health_epi.t_visit_id <> t_nhso_service_pp.t_visit_id);
--ลบข้อมูล service ของ FP ที่ไม่สัมพันธ์กัน
delete from t_nhso_service_pp 
where t_nhso_service_pp.t_nhso_service_pp_id in (select t_nhso_service_pp.t_nhso_service_pp_id from t_health_family_planing 
inner join t_nhso_service_pp on t_health_family_planing.t_health_family_planing_id = t_nhso_service_pp.pp_id 
where t_health_family_planing.t_visit_id <> t_nhso_service_pp.t_visit_id);
--ลบข้อมูล service ของ GROW ที่ไม่สัมพันธ์กัน
delete from t_nhso_service_pp 
where t_nhso_service_pp.t_nhso_service_pp_id in (select t_nhso_service_pp.t_nhso_service_pp_id from t_health_nutrition 
inner join t_nhso_service_pp on t_health_nutrition.t_health_nutrition_id = t_nhso_service_pp.pp_id 
where t_health_nutrition.t_visit_id <> t_nhso_service_pp.t_visit_id);
--ลบข้อมูล service ของ PNC ที่ไม่สัมพันธ์กัน
delete from t_nhso_service_pp 
where t_nhso_service_pp.t_nhso_service_pp_id in (select t_nhso_service_pp.t_nhso_service_pp_id from t_health_postpartum 
inner join t_nhso_service_pp on t_health_postpartum.t_health_postpartum_id = t_nhso_service_pp.pp_id 
where t_health_postpartum.t_visit_id <> t_nhso_service_pp.t_visit_id);
--ลบข้อมูล service ของ THALASSEMIA ที่ไม่สัมพันธ์กัน
delete from t_nhso_service_pp 
where t_nhso_service_pp.t_nhso_service_pp_id in (select t_nhso_service_pp.t_nhso_service_pp_id from t_nhso_thalassemia 
inner join t_nhso_service_pp on t_nhso_thalassemia.t_nhso_thalassemia_id = t_nhso_service_pp.pp_id 
where t_nhso_thalassemia.t_visit_id <> t_nhso_service_pp.t_visit_id);

--กำหนด Primarykey ของตาราง t_nhso_service_pp
ALTER TABLE t_nhso_service_pp
	ADD CONSTRAINT nhso_service_pp_id_pk
	PRIMARY KEY (t_nhso_service_pp_id);

--ANC
insert into t_nhso_service_pp 
(select distinct
'nh150' || substring(t_health_anc.t_health_anc_id,4) || '02' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,'' as t_order_id
, 'ANC' as pp_name  
, t_health_anc.t_health_anc_id as pp_id
,'' as  pp_recompen
,'' as pp_totalpay
, t_health_anc.health_anc_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
from
(select t_health_anc.t_visit_id,t_health_anc.t_health_anc_id
FROM t_health_anc
    left join t_nhso_service_pp on t_health_anc.t_health_anc_id = t_nhso_service_pp.pp_id
where t_health_anc.record_date_time > '2552-10'
and t_nhso_service_pp.t_nhso_service_pp_id is null
and t_health_anc.health_anc_active = '1') visit_anc 

inner join t_health_anc on visit_anc.t_health_anc_id = t_health_anc.t_health_anc_id
inner join t_visit on t_health_anc.t_visit_id = t_visit.t_visit_id );

--ANCCONSEL
insert into t_nhso_service_pp 
(select distinct
'nh150' || substring(t_health_counsel.t_health_counsel_id,4) || '14' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,'' as t_order_id
, 'ANCCONSULT' as pp_name  
, t_health_counsel.t_health_counsel_id as pp_id
,'' as pp_recompen
,'' as pp_totalpay
, t_health_counsel.counsel_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
from
(select distinct t_health_counsel.t_visit_id 
,t_health_counsel.t_health_counsel_id 
from t_health_counsel 
    inner join t_health_anc on t_health_counsel.t_visit_id = t_health_anc.t_visit_id
    inner join b_nhso_map_concounseling on t_health_counsel.b_health_counsel_type_id = b_nhso_map_concounseling.b_health_service_type_id
    left join t_nhso_service_pp on t_health_counsel.t_health_counsel_id = t_nhso_service_pp.pp_id
where t_health_counsel.counsel_record_time > '2552-10'
and b_nhso_map_concounseling.f_nhso_counseling_id in ('01','02','03','04','05','06','07','08') 
and t_nhso_service_pp.t_nhso_service_pp_id is null and t_health_counsel.counsel_active = '1') visit_counsel 

inner join t_health_counsel on visit_counsel.t_health_counsel_id = t_health_counsel.t_health_counsel_id
inner join t_visit on t_health_counsel.t_visit_id = t_visit.t_visit_id );

--ANCDENT
insert into t_nhso_service_pp 
(select distinct
'nh150' || substring(t_nhso_dental_adult.t_nhso_dental_adult_id,4) || '15' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,'' as t_order_id
, 'ANCDENT' as pp_name  
, t_nhso_dental_adult.t_nhso_dental_adult_id as pp_id
, '' as  pp_recompen
, '' as pp_totalpay
, t_health_dental.dental_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
from
(select t_health_dental.t_visit_id,t_nhso_dental_adult.t_nhso_dental_adult_id
FROM t_health_dental 
    inner join t_nhso_dental_adult on t_health_dental.t_health_dental_id = t_nhso_dental_adult.t_health_dental_id
    inner join t_health_anc on t_health_dental.t_visit_id = t_health_anc.t_visit_id
    left join t_nhso_service_pp on t_nhso_dental_adult.t_nhso_dental_adult_id = t_nhso_service_pp.pp_id
where t_health_dental.dental_record_time > '2552-10'
and t_nhso_service_pp.t_nhso_service_pp_id is null and t_health_dental.dental_active = '1') visit_ancdental

inner join t_nhso_dental_adult on t_nhso_dental_adult.t_nhso_dental_adult_id = visit_ancdental.t_nhso_dental_adult_id
inner join t_health_dental on t_health_dental.t_health_dental_id = t_nhso_dental_adult.t_health_dental_id
inner join t_visit on t_health_dental.t_visit_id = t_visit.t_visit_id );

--CANCER
insert into t_nhso_service_pp 
(select distinct
'nh150' || substr(t_nhso_screen_cancer.t_nhso_screen_cancer_id,6) || '10' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,'' as t_order_id
, 'BREASTCANCER' as pp_name  
, t_nhso_screen_cancer.t_nhso_screen_cancer_id as pp_id
,'' as  pp_recompen 
,'' as pp_totalpay
, t_nhso_screen_cancer.screen_cancer_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
from
(select t_nhso_screen_cancer.t_visit_id,t_nhso_screen_cancer.t_nhso_screen_cancer_id
FROM t_nhso_screen_cancer 
    left join t_nhso_service_pp 
        on (t_nhso_screen_cancer.t_nhso_screen_cancer_id = t_nhso_service_pp.pp_id 
                and t_nhso_service_pp.pp_name = 'BREASTCANCER')
where t_nhso_screen_cancer.screen_cancer_record_date_time > '2552-10'
and t_nhso_service_pp.t_nhso_service_pp_id is null
and t_nhso_screen_cancer.screen_cancer_breast_check = '1'
and t_nhso_screen_cancer.screen_cancer_breast_exam in ('1','2','3') and t_nhso_screen_cancer.screen_cancer_active = '1') visit_cancer 

inner join t_nhso_screen_cancer on visit_cancer.t_nhso_screen_cancer_id = t_nhso_screen_cancer.t_nhso_screen_cancer_id
inner join t_visit on t_nhso_screen_cancer.t_visit_id = t_visit.t_visit_id );

--CONSULT
insert into t_nhso_service_pp 
(select distinct
'nh150'||substr(t_health_counsel.t_health_counsel_id,4) || '08' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,''  as t_order_id
, 'CONSULT' as pp_name  
, t_health_counsel.t_health_counsel_id as pp_id
, '' as  pp_recompen
, '' as pp_totalpay
, t_health_counsel.counsel_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
from
( select distinct t_health_counsel.t_visit_id
,t_health_counsel.t_health_counsel_id 
from t_health_counsel 
    left join t_health_anc on t_health_counsel.t_visit_id = t_health_anc.t_visit_id
    left join t_nhso_service_pp on t_health_counsel.t_health_counsel_id = t_nhso_service_pp.pp_id and t_nhso_service_pp.pp_name = 'CONSULT'
where t_health_counsel.counsel_record_time  > '2552-10'
and t_nhso_service_pp.t_nhso_service_pp_id is null
and t_health_anc.t_health_anc_id is null and t_health_counsel.counsel_active = '1' ) visit_counsel

inner join t_health_counsel on visit_counsel.t_health_counsel_id = t_health_counsel.t_health_counsel_id
inner join t_visit on t_health_counsel.t_visit_id = t_visit.t_visit_id);

--DENT
insert into t_nhso_service_pp 
(select distinct 
'nh150' || substr(t_nhso_dental_adult.t_nhso_dental_adult_id,4) || '03' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,''  as t_order_id
, 'DENT' as pp_name  
, t_nhso_dental_adult.t_nhso_dental_adult_id as pp_id
, '' as  pp_recompen
, '' as pp_totalpay
, t_health_dental.dental_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
from
(select t_health_dental.t_visit_id,t_nhso_dental_adult.t_nhso_dental_adult_id
from t_health_dental 
    inner join t_nhso_dental_adult on t_health_dental.t_health_dental_id = t_nhso_dental_adult.t_health_dental_id
    left join t_health_anc on t_health_dental.t_visit_id = t_health_anc.t_visit_id
    left join t_nhso_service_pp on t_nhso_dental_adult.t_nhso_dental_adult_id = t_nhso_service_pp.pp_id
where t_health_dental.dental_record_time > '2552-10'
and t_nhso_service_pp.t_nhso_service_pp_id is null
and t_health_anc.t_health_anc_id is null and t_health_dental.dental_active = '1') visit_dent

inner join t_nhso_dental_adult on visit_dent.t_nhso_dental_adult_id = t_nhso_dental_adult.t_nhso_dental_adult_id
inner join t_health_dental on t_health_dental.t_health_dental_id = t_nhso_dental_adult.t_health_dental_id
inner join t_visit on t_health_dental.t_visit_id = t_visit.t_visit_id );

--DENTCHILD
insert into t_nhso_service_pp 
(select distinct
'nh150' || substr(t_nhso_dental.t_nhso_dental_id,4) || '04' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
, '' as t_order_id
, 'DENTCHILD' as pp_name  
, t_nhso_dental.t_nhso_dental_id as pp_id
, '' as  pp_recompen
, '' as pp_totalpay
, t_health_dental.dental_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
from

(select t_health_dental.t_visit_id,t_nhso_dental.t_nhso_dental_id
from t_health_dental 
   inner join t_nhso_dental on t_health_dental.t_health_dental_id = t_nhso_dental.t_health_dental_id
   left join t_nhso_service_pp on t_nhso_dental.t_nhso_dental_id = t_nhso_service_pp.pp_id
where t_health_dental.dental_record_time > '2552-10'
and t_nhso_service_pp.t_nhso_service_pp_id is null and t_health_dental.dental_active = '1'
  ) visit_dentchild

inner join t_nhso_dental on visit_dentchild.t_nhso_dental_id = t_nhso_dental.t_nhso_dental_id
inner join t_health_dental on t_health_dental.t_health_dental_id = t_nhso_dental.t_health_dental_id 
inner join t_visit on t_health_dental.t_visit_id = t_visit.t_visit_id );

--DEPRESSION
insert into t_nhso_service_pp 
(select distinct
'nh150' || substr(t_nhso_depression.t_nhso_depression_id,6) || '13' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,'' as t_order_id
, 'DEPRESSION' as pp_name  
, t_nhso_depression.t_nhso_depression_id as pp_id
, '' as  pp_recompen
,'' as pp_totalpay
, t_nhso_depression.depression_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
from
(select t_nhso_depression.t_visit_id,t_nhso_depression_id

FROM t_nhso_depression 
    left join t_nhso_service_pp on t_nhso_depression.t_nhso_depression_id = t_nhso_service_pp.pp_id
where t_nhso_depression.depression_record_date_time > '2552-10'
and t_nhso_service_pp.t_nhso_service_pp_id is null and t_nhso_depression.depression_active ='1') visit_depress

inner join t_nhso_depression on visit_depress.t_nhso_depression_id = t_nhso_depression.t_nhso_depression_id
inner join t_visit on t_nhso_depression.t_visit_id = t_visit.t_visit_id );

--DMHT
insert into t_nhso_service_pp 
(select distinct
'nh150' || substr(t_nhso_dmht_pp.t_nhso_dmht_pp_id,6)|| '09' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
, '' as t_order_id
, 'DMHT' as pp_name  
, t_nhso_dmht_pp.t_nhso_dmht_pp_id as pp_id
, '' as  pp_recompen
, '' as pp_totalpay
, t_nhso_dmht_pp.active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
from
(select t_nhso_dmht_pp.t_visit_id,t_nhso_dmht_pp_id
FROM t_nhso_dmht_pp 
    left join t_nhso_service_pp on t_nhso_dmht_pp.t_nhso_dmht_pp_id = t_nhso_service_pp.pp_id 
where t_nhso_dmht_pp.record_date_time > '2552-10'
and t_nhso_service_pp.t_nhso_service_pp_id is null and t_nhso_dmht_pp.active ='1') visit_dmht

inner join t_nhso_dmht_pp on visit_dmht.t_nhso_dmht_pp_id = t_nhso_dmht_pp.t_nhso_dmht_pp_id
inner join t_visit on t_nhso_dmht_pp.t_visit_id = t_visit.t_visit_id );

--EPI
insert into t_nhso_service_pp 
(select distinct
'nh150'|| substr(t_health_epi.t_health_epi_id,4) || '06' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,'' as t_order_id
, 'EPI' as pp_name  
, t_health_epi.t_health_epi_id as pp_id
,'' as  pp_recompen
, '' as pp_totalpay
, t_health_epi.health_epi_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
from
(select t_health_epi.t_visit_id,t_health_epi_id
FROM t_health_epi
    left join t_nhso_service_pp on t_health_epi.t_health_epi_id = t_nhso_service_pp.pp_id
  where t_health_epi.record_date_time > '2552-10'
and t_nhso_service_pp.t_nhso_service_pp_id is null and t_health_epi.health_epi_active ='1') visit_epi

inner join t_health_epi on visit_epi.t_health_epi_id = t_health_epi.t_health_epi_id
inner join t_visit on t_health_epi.t_visit_id = t_visit.t_visit_id );

--FP
insert into t_nhso_service_pp 
(select distinct
'nh150' || substr(t_health_family_planing.t_health_family_planing_id,4) || '01' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,''  as t_order_id
, 'FP' as pp_name  
, t_health_family_planing.t_health_family_planing_id as pp_id
, '' as  pp_recompen
, '' as pp_totalpay
, t_health_family_planing.health_family_planing_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
from
(select t_health_family_planing.t_visit_id,t_health_family_planing_id
FROM t_health_family_planing 
    left join t_nhso_service_pp on t_health_family_planing.t_health_family_planing_id = t_nhso_service_pp.pp_id
where t_health_family_planing.record_date_time > '2552-10'
and t_nhso_service_pp.t_nhso_service_pp_id is null and t_health_family_planing.health_family_planing_active ='1') visit_fp 

inner join t_health_family_planing on visit_fp.t_health_family_planing_id = t_health_family_planing.t_health_family_planing_id
inner join t_visit on t_health_family_planing.t_visit_id = t_visit.t_visit_id );

--GROW
insert into t_nhso_service_pp 
(select  distinct
'nh150'||substr(t_health_nutrition.t_health_nutrition_id,4)|| '07' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,''  as t_order_id
, 'GROW' as pp_name  
, t_health_nutrition.t_health_nutrition_id as pp_id
, '' as  pp_recompen
,'' as pp_totalpay
, t_health_nutrition.health_nutrition_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
from
(select t_health_nutrition.t_visit_id,t_health_nutrition_id
FROM t_health_nutrition 
    left join t_nhso_service_pp on t_health_nutrition.t_health_nutrition_id = t_nhso_service_pp.pp_id
where t_health_nutrition.record_date_time > '2552-10'
and t_nhso_service_pp.t_nhso_service_pp_id is null and t_health_nutrition.health_nutrition_active ='1') visit_grow

inner join t_health_nutrition on visit_grow.t_health_nutrition_id = t_health_nutrition.t_health_nutrition_id
inner join t_visit on t_health_nutrition.t_visit_id = t_visit.t_visit_id );

--PAPSMEAR
insert into t_nhso_service_pp 
(select distinct
'nh150'|| substr(t_nhso_screen_cancer.t_nhso_screen_cancer_id,6) || '11' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,'' as t_order_id
, 'PAPSMEAR' as pp_name  
, t_nhso_screen_cancer.t_nhso_screen_cancer_id as pp_id
, '' as  pp_recompen
, ''as pp_totalpay
, t_nhso_screen_cancer.screen_cancer_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as 
varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
from
(select t_nhso_screen_cancer.t_visit_id,t_nhso_screen_cancer.t_nhso_screen_cancer_id
FROM t_nhso_screen_cancer 
    left join t_nhso_service_pp 
    on (t_nhso_screen_cancer.t_nhso_screen_cancer_id = t_nhso_service_pp.pp_id and  
t_nhso_service_pp.pp_name='PAPSMEAR')
where t_nhso_screen_cancer.screen_cancer_record_date_time > '2552-10'
and t_nhso_service_pp.t_nhso_service_pp_id is null
and screen_cancer_uterus_check = '1'
and t_nhso_screen_cancer.screen_cancer_cervix_exam in ('1','2','3') and 
t_nhso_screen_cancer.screen_cancer_active ='1') visit_cancer 

inner join t_nhso_screen_cancer on visit_cancer.t_nhso_screen_cancer_id = 
t_nhso_screen_cancer.t_nhso_screen_cancer_id
inner join t_visit on t_nhso_screen_cancer.t_visit_id = t_visit.t_visit_id );

--PNC
insert into t_nhso_service_pp 
(select distinct
'nh150'||substr(t_health_postpartum.t_health_postpartum_id,4) ||  '05' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,'' as t_order_id
, 'PNC' as pp_name  
, t_health_postpartum.t_health_postpartum_id as pp_id
, '' as  pp_recompen
,'' as pp_totalpay
, t_health_postpartum.health_postpartum_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
from
(select t_health_postpartum.t_visit_id,t_health_postpartum_id
FROM t_health_postpartum 
    left join t_nhso_service_pp on t_health_postpartum.t_health_postpartum_id = t_nhso_service_pp.pp_id
where t_health_postpartum.record_date_time  > '2552-10'
and t_nhso_service_pp.t_nhso_service_pp_id is null and t_health_postpartum.health_postpartum_active='1') visit_pnc

inner join t_health_postpartum on visit_pnc.t_health_postpartum_id = t_health_postpartum.t_health_postpartum_id
inner join t_visit  on t_health_postpartum.t_visit_id = t_visit.t_visit_id );

--THALASSEMIA
insert into t_nhso_service_pp 
(select distinct
'nh150' || substr(t_nhso_thalassemia.t_nhso_thalassemia_id,6) || '12' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,'' as t_order_id
, 'THALASSEMIA' as pp_name  
, t_nhso_thalassemia.t_nhso_thalassemia_id as pp_id
, '' as  pp_recompen 
, '' as pp_totalpay
, t_nhso_thalassemia.thalassemia_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
from
(select t_nhso_thalassemia.t_visit_id,t_nhso_thalassemia.t_nhso_thalassemia_id
FROM t_nhso_thalassemia 
    left join t_nhso_service_pp on t_nhso_thalassemia.t_nhso_thalassemia_id = t_nhso_service_pp.pp_id
where t_nhso_thalassemia.thalassemia_record_date_time > '2552-10'
and t_nhso_service_pp.t_nhso_service_pp_id is null and t_nhso_thalassemia.thalassemia_active ='1') visit_thalass

inner join t_nhso_thalassemia on visit_thalass.t_nhso_thalassemia_id = t_nhso_thalassemia.t_nhso_thalassemia_id
inner join t_visit on t_nhso_thalassemia.t_visit_id = t_visit.t_visit_id );
-------------------------
INSERT INTO s_nhso_version("s_version_id", "version_number", "version_description", "version_application_number",  "version_database_number", "version_update_time")
 VALUES('9701000000119', '19', 'Hospital OS, Community Edition', '3.14.1051', '0.7.281209', '2552-12-28,19:00:00');

insert into s_script_update_log values ('nhso','update_nhso_ph19.sql',(select current_date) || ','|| (select  current_time),'update NHSO ph18 -> ph19');
