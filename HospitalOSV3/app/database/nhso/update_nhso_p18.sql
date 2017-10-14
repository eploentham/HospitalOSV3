--check database version 0.5.300909
 alter table t_nhso_thalassemia add t_health_family_id varchar(255); 
 alter table t_nhso_thalassemia add t_patient_id varchar(255);
 alter table t_nhso_depression add t_health_family_id varchar(255);
 alter table t_nhso_depression add t_patient_id varchar(255);
 alter table t_nhso_home_visit add home_visit_person_service_doctor varchar(255);
 alter table t_nhso_home_visit add home_visit_service_nursingprofession varchar(255); 
 alter table t_nhso_home_visit add home_visit_person_service_physiotherapist varchar(255);
 alter table t_nhso_home_visit add home_visit_person_service_welfare varchar(255);
 alter table t_nhso_home_visit add home_visit_person_service_psychologist varchar(255);
 alter table t_nhso_home_visit add home_visit_person_service_pharmacists varchar(255);
 alter table t_nhso_home_visit add home_visit_person_service_other varchar(255);
 alter table t_nhso_visit_home add visit_home_person_service_doctor varchar(255);
 alter table t_nhso_visit_home add visit_home_person_service_nursingProfession varchar(255);
 alter table t_nhso_visit_home add visit_home_person_service_physiotherapist varchar(255);
 alter table t_nhso_visit_home add visit_home_person_service_welfare varchar(255);
 alter table t_nhso_visit_home add visit_home_person_service_psychologist varchar(255);
 alter table t_nhso_visit_home add visit_home_person_service_pharmacists varchar(255); 
 alter table t_nhso_visit_home add visit_home_person_service_other varchar(255);
 alter table t_nhso_consult add diabetes_complication varchar(255);
 alter table t_nhso_consult add hbd_complication varchar(255);

insert into t_nhso_service_pp 
(
select 
'nh150' || substring(t_health_anc.t_health_anc_id,4) || t_health_anc.health_anc_active || '02' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,case when (t_order.t_order_id is not null)
        then t_order.t_order_id
        else '' 
 end  as t_order_id
, 'ANC' as pp_name  
, t_health_anc.t_health_anc_id as pp_id
,'' as  pp_recompen 
,case when (t_order.t_order_id is not null)
        then cast(floor(cast(order_qty as numeric) * cast(order_price as numeric)) as varchar)
        else ''
 end as pp_totalpay
, t_health_anc.health_anc_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
 from
 (select t_health_anc.t_visit_id
 FROM t_health_anc
    left join t_nhso_service_pp 
 on t_health_anc.t_health_anc_id = t_nhso_service_pp.pp_id
 where t_health_anc.record_date_time > '2552-10'
 and t_nhso_service_pp.t_nhso_service_pp_id is null) visit_anc 
 inner join t_health_anc on visit_anc.t_visit_id = t_health_anc.t_visit_id
 inner join t_visit on t_health_anc.t_visit_id = t_visit.t_visit_id 

 left join t_order on (t_visit.t_visit_id = t_order.t_visit_id 
        and t_order.b_item_id in (select b_item_id from b_nhso_service_item where f_nhso_service_id = '2' and active = '1'))
 );



insert into t_nhso_service_pp 
(select distinct
 'nh150'||substr(t_health_counsel.t_health_counsel_id,4) || t_health_counsel.counsel_active || '08' as t_nhso_service_pp_id
 ,t_visit.t_visit_id as t_visit_id 
 ,case when (t_order.t_order_id is not null)
        then t_order.t_order_id
        else '' 
 end  as t_order_id
 , 'CONSULT' as pp_name  
 , t_health_counsel.t_health_counsel_id as pp_id
 , '' as  pp_recompen 
 ,case when (t_order.t_order_id is not null)
        then cast(floor(cast(order_qty as numeric) * cast(order_price as numeric)) as varchar)
        else ''
 end as pp_totalpay
, t_health_counsel.counsel_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
 from


 ( select distinct t_health_counsel.t_visit_id

 from t_health_counsel 
   left join t_health_anc on t_health_counsel.t_visit_id = t_health_anc.t_visit_id
    inner join b_nhso_map_concounseling ON t_health_counsel.b_health_counsel_type_id = b_nhso_map_concounseling.b_health_service_type_id
    left join t_nhso_service_pp on t_health_counsel.t_health_counsel_id = t_nhso_service_pp.pp_id
 where t_health_counsel.counsel_record_time  > '2552-10'
 and t_nhso_service_pp.t_nhso_service_pp_id is null
 and t_health_anc.t_health_anc_id is null

 ) visit_counsel

 inner join t_health_counsel 
 on visit_counsel.t_visit_id = t_health_counsel.t_visit_id
 inner join t_visit 
 on t_health_counsel.t_visit_id = t_visit.t_visit_id 
 left join t_visit_payment 
 on (t_visit.t_visit_id = t_visit_payment.t_visit_id and t_visit_payment.b_contract_plans_id = '0000000000000')
 left join t_order 
 on (t_visit.t_visit_id = t_order.t_visit_id 
    and t_order.b_item_id in (select b_item_id from b_nhso_service_item where f_nhso_service_id = '8' and active = '1'))

 );









 insert into t_nhso_service_pp 
 (
 select 
 'nh150' || substring(t_health_dental.t_health_dental_id,4) || t_health_dental.dental_active || '15' as t_nhso_service_pp_id
 ,t_visit.t_visit_id as t_visit_id 
 ,case when (t_order.t_order_id is not null)
        then t_order.t_order_id
        else '' 
 end  as t_order_id
 , 'ANCDENT' as pp_name  
 , t_health_dental.t_health_dental_id as pp_id
 , '' as  pp_recompen
 ,case when (t_order.t_order_id is not null)
        then cast(floor(cast(order_qty as numeric) * cast(order_price as numeric)) as varchar)
        else ''
 end as pp_totalpay
 , t_health_dental.dental_active as pp_active
 , (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
 , t_visit.visit_begin_visit_time as service_date
 , '1' as service_new
 from
 (select t_health_dental.t_visit_id
 FROM t_health_dental 
 inner join t_nhso_dental_adult 
 on t_health_dental.t_health_dental_id = t_nhso_dental_adult.t_health_dental_id
 left join t_nhso_service_pp on t_nhso_dental_adult.t_nhso_dental_adult_id = t_nhso_service_pp.pp_id
 where t_health_dental.dental_record_time > '2552-10'
 and t_nhso_dental_adult.t_nhso_anc_id <>''
 and t_nhso_service_pp.t_nhso_service_pp_id is null
 or t_nhso_service_pp.t_nhso_service_pp_id =''
 ) visit_ancdental
 inner join t_health_dental on visit_ancdental.t_visit_id = t_health_dental.t_visit_id
 inner join t_visit on t_health_dental.t_visit_id = t_visit.t_visit_id 

 left join t_order on (t_visit.t_visit_id = t_order.t_visit_id 
        and t_order.b_item_id in (select b_item_id from b_nhso_service_item where f_nhso_service_id = '15' and active = '1'))
);


insert into t_nhso_service_pp 
 (select distinct
 'nh150' || substr(t_nhso_screen_cancer.t_nhso_screen_cancer_id,6) || t_nhso_screen_cancer.screen_cancer_active || '10' as t_nhso_service_pp_id
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
 (select t_nhso_screen_cancer.t_visit_id
 FROM t_nhso_screen_cancer 
    left join t_nhso_service_pp 
 on (t_nhso_screen_cancer.t_nhso_screen_cancer_id = t_nhso_service_pp.pp_id and  t_nhso_service_pp.pp_name='BREASTCANCER')
 where t_nhso_screen_cancer.screen_cancer_record_date_time > '2552-10'
 and t_nhso_screen_cancer.screen_cancer_breast_exam in ('1','2','3')
 and t_nhso_service_pp.t_nhso_service_pp_id is null) visit_cancer 
 inner join t_nhso_screen_cancer on visit_cancer.t_visit_id = t_nhso_screen_cancer.t_visit_id
 inner join t_visit on t_nhso_screen_cancer.t_visit_id = t_visit.t_visit_id 

);



  
insert into t_nhso_service_pp 
 (select 
 distinct 'nh150' || substr(t_health_dental.t_health_dental_id,4) ||t_health_dental.dental_active || '03' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,case when (t_order.t_order_id is not null)
        then t_order.t_order_id
        else '' 
 end  as t_order_id
, 'DENT' as pp_name  
, t_health_dental.t_health_dental_id as pp_id
, '' as  pp_recompen 
,case when (t_order.t_order_id is not null)
        then cast(floor(cast(order_qty as numeric) * cast(order_price as numeric)) as varchar)
        else ''
 end as pp_totalpay
, t_health_dental.dental_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
from

(select t_health_dental.t_visit_id

 FROM t_health_dental 
 inner join t_nhso_dental_adult 
 on t_health_dental.t_health_dental_id = t_nhso_dental_adult.t_health_dental_id

   left join t_nhso_service_pp on t_nhso_dental_adult.t_nhso_dental_adult_id = t_nhso_service_pp.pp_id
 where t_health_dental.dental_record_time > '2552-10'
 and t_nhso_dental_adult.t_nhso_anc_id ='' 
 and t_nhso_service_pp.t_nhso_service_pp_id is null) visit_dent
 inner join t_health_dental on visit_dent.t_visit_id = t_health_dental.t_visit_id
 inner join t_visit on t_health_dental.t_visit_id = t_visit.t_visit_id 

 left join t_order on (t_visit.t_visit_id = t_order.t_visit_id 
        and t_order.b_item_id in (select b_item_id from b_nhso_service_item where f_nhso_service_id = '3' and active = '1'))
 );


insert into t_nhso_service_pp 
 (select distinct
 'nh150' || substr(t_health_dental.t_health_dental_id,4) || t_health_dental.dental_active || '04' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,case when (t_order.t_order_id is not null)
        then t_order.t_order_id
        else '' 
 end  as t_order_id
, 'DENTCHILD' as pp_name  
, t_health_dental.t_health_dental_id as pp_id
, '' as  pp_recompen 
,case when (t_order.t_order_id is not null)
        then cast(floor(cast(order_qty as numeric) * cast(order_price as numeric)) as varchar)
        else ''
 end as pp_totalpay
, t_health_dental.dental_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
 from

(select t_health_dental.t_visit_id

 FROM t_health_dental 
 inner join t_nhso_dental
 on t_health_dental.t_health_dental_id = t_nhso_dental.t_health_dental_id

   left join t_nhso_service_pp on t_nhso_dental.t_nhso_dental_id = t_nhso_service_pp.pp_id
 where t_health_dental.dental_record_time > '2552-10'

 and t_nhso_service_pp.t_nhso_service_pp_id is null 

  ) visit_dentchild



 inner join t_health_dental on visit_dentchild.t_visit_id = t_health_dental.t_visit_id
 inner join t_visit on t_health_dental.t_visit_id = t_visit.t_visit_id 

 left join t_order on (t_visit.t_visit_id = t_order.t_visit_id 
        and t_order.b_item_id in (select b_item_id from b_nhso_service_item where f_nhso_service_id = '4' and active = '1'))
);


insert into t_nhso_service_pp 
 (select distinct
'nh150' || substr(t_nhso_depression.t_nhso_depression_id,6) ||t_nhso_depression.depression_active || '13' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,case when (t_order.t_order_id is not null)
        then t_order.t_order_id
        else '' 
 end  as t_order_id
, 'DEPRESSION' as pp_name  
, t_nhso_depression.t_nhso_depression_id as pp_id
, '' as  pp_recompen 
,case when (t_order.t_order_id is not null)
        then cast(floor(cast(order_qty as numeric) * cast(order_price as numeric)) as varchar)
        else ''
 end as pp_totalpay
, t_nhso_depression.depression_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
 from
(select t_nhso_depression.t_visit_id

 FROM t_nhso_depression 
    left join t_nhso_service_pp 
 on t_nhso_depression.t_nhso_depression_id = t_nhso_service_pp.pp_id
 where t_nhso_depression.depression_record_date_time > '2552-10'
 and t_nhso_service_pp.t_nhso_service_pp_id is null) visit_depress


 inner join t_nhso_depression on visit_depress.t_visit_id = t_nhso_depression.t_visit_id
 inner join t_visit on t_nhso_depression.t_visit_id = t_visit.t_visit_id 

 left join t_order on (t_visit.t_visit_id = t_order.t_visit_id 
        and t_order.b_item_id in (select b_item_id from b_nhso_service_item where f_nhso_service_id = '13' and active = '1'))
);


 insert into t_nhso_service_pp 
 (select distinct
 'nh150' || substr(t_nhso_dmht_pp.t_nhso_dmht_pp_id,6) ||t_nhso_dmht_pp.active || '09' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,case when (t_order.t_order_id is not null)
        then t_order.t_order_id
        else '' 
 end  as t_order_id
, 'DMHT' as pp_name  
, t_nhso_dmht_pp.t_nhso_dmht_pp_id as pp_id
, '' as  pp_recompen 
,case when (t_order.t_order_id is not null)
        then cast(floor(cast(order_qty as numeric) * cast(order_price as numeric)) as varchar)
        else ''
 end as pp_totalpay
, t_nhso_dmht_pp.active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
 from
 (select t_nhso_dmht_pp.t_visit_id

 FROM t_nhso_dmht_pp 
    left join t_nhso_service_pp 
 on t_nhso_dmht_pp.t_nhso_dmht_pp_id = t_nhso_service_pp.pp_id 
 where t_nhso_dmht_pp.record_date_time > '2552-10'
 and t_nhso_service_pp.t_nhso_service_pp_id is null) visit_dmht


 inner join t_nhso_dmht_pp on visit_dmht.t_visit_id = t_nhso_dmht_pp.t_visit_id
 inner join t_visit on t_nhso_dmht_pp.t_visit_id = t_visit.t_visit_id 

 left join t_order on (t_visit.t_visit_id = t_order.t_visit_id 
        and t_order.b_item_id in (select b_item_id from b_nhso_service_item where f_nhso_service_id = '9' and active = '1'))
);

 insert into t_nhso_service_pp 
 (select distinct
'nh150'|| substr(t_health_epi.t_health_epi_id,4) || t_health_epi.health_epi_active || '06' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,case when (t_order.t_order_id is not null)
        then t_order.t_order_id
        else '' 
 end  as t_order_id
, 'EPI' as pp_name  
, t_health_epi.t_health_epi_id as pp_id
, '' as  pp_recompen 
,case when (t_order.t_order_id is not null)
        then cast(floor(cast(order_qty as numeric) * cast(order_price as numeric)) as varchar)
        else ''
 end as pp_totalpay
, t_health_epi.health_epi_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
 from
(select t_health_epi.t_visit_id

 FROM t_health_epi
    left join t_nhso_service_pp on t_health_epi.t_health_epi_id = t_nhso_service_pp.pp_id
  where t_health_epi.record_date_time > '2552-10'
 and t_nhso_service_pp.t_nhso_service_pp_id is null) visit_epi



 inner join t_health_epi on visit_epi.t_visit_id = t_health_epi.t_visit_id
 inner join t_visit on t_health_epi.t_visit_id = t_visit.t_visit_id 

 left join t_order on (t_visit.t_visit_id = t_order.t_visit_id 
        and t_order.b_item_id in (select b_item_id from b_nhso_service_item where f_nhso_service_id = '6' and active = '1'))
);






 insert into t_nhso_service_pp 
 (select distinct
'nh150' || substr(t_health_family_planing.t_health_family_planing_id,4) || t_health_family_planing.health_family_planing_active || '01' as  t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,case when (t_order.t_order_id is not null)
        then t_order.t_order_id
        else '' 
 end  as t_order_id
, 'FP' as pp_name  
, t_health_family_planing.t_health_family_planing_id as pp_id
, '' as  pp_recompen 
,case when (t_order.t_order_id is not null)
        then cast(floor(cast(order_qty as numeric) * cast(order_price as numeric)) as varchar)
        else ''
 end as pp_totalpay
, t_health_family_planing.health_family_planing_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
 from
 (select t_health_family_planing.t_visit_id
 FROM t_health_family_planing 
    left join t_nhso_service_pp on t_health_family_planing.t_health_family_planing_id = t_nhso_service_pp.pp_id
 where t_health_family_planing.record_date_time > '2552-10'
 and t_nhso_service_pp.t_nhso_service_pp_id is null) visit_fp 
 inner join t_health_family_planing on visit_fp.t_visit_id = t_health_family_planing.t_visit_id
 inner join t_visit on t_health_family_planing.t_visit_id = t_visit.t_visit_id 

 left join t_order on (t_visit.t_visit_id = t_order.t_visit_id 
        and t_order.b_item_id in (select b_item_id from b_nhso_service_item where f_nhso_service_id = '1' and active = '1'))
);


insert into t_nhso_service_pp 
 (select   distinct
'nh150'||substr(t_health_nutrition.t_health_nutrition_id,4)||t_health_nutrition.health_nutrition_active || '07' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,case when (t_order.t_order_id is not null)
        then t_order.t_order_id
        else '' 
 end  as t_order_id
, 'GROW' as pp_name  
, t_health_nutrition.t_health_nutrition_id as pp_id
,''  as  pp_recompen 
,case when (t_order.t_order_id is not null)
        then cast(floor(cast(order_qty as numeric) * cast(order_price as numeric)) as varchar)
        else ''
 end as pp_totalpay

, t_health_nutrition.health_nutrition_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
 from


 (select t_health_nutrition.t_visit_id
 FROM t_health_nutrition 
    left join t_nhso_service_pp 
 on t_health_nutrition.t_health_nutrition_id = t_nhso_service_pp.pp_id
 where t_health_nutrition.record_date_time > '2552-10'
 and t_nhso_service_pp.t_nhso_service_pp_id is null) visit_grow



 inner join t_health_nutrition 
 on visit_grow.t_visit_id = t_health_nutrition.t_visit_id
 inner join t_visit 
 on t_health_nutrition.t_visit_id = t_visit.t_visit_id 

 left join t_order on (t_visit.t_visit_id = t_order.t_visit_id 
        and t_order.b_item_id in (select b_item_id from b_nhso_service_item where f_nhso_service_id = '7' and active = '1'))


 );


 insert into t_nhso_service_pp 
 (select distinct
'nh150'|| substr(t_nhso_screen_cancer.t_nhso_screen_cancer_id,6) ||  t_nhso_screen_cancer.screen_cancer_active  || '11' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,'' as t_order_id
, 'PAPSMEAR' as pp_name  
, t_nhso_screen_cancer.t_nhso_screen_cancer_id as pp_id
, '' as  pp_recompen 
,''as pp_totalpay
, t_nhso_screen_cancer.screen_cancer_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new



 from
 (select t_nhso_screen_cancer.t_visit_id

 FROM t_nhso_screen_cancer 
    left join t_nhso_service_pp 
 on (t_nhso_screen_cancer.t_nhso_screen_cancer_id = t_nhso_service_pp.pp_id and  t_nhso_service_pp.pp_name='PAPSMEAR')
 where t_nhso_screen_cancer.screen_cancer_record_date_time > '2552-10'
 and t_nhso_service_pp.t_nhso_service_pp_id is null
 and t_nhso_screen_cancer.screen_cancer_cervix_exam in ('1','2','3')) visit_cancer 

 inner join t_nhso_screen_cancer on visit_cancer.t_visit_id = t_nhso_screen_cancer.t_visit_id
 inner join t_visit on t_nhso_screen_cancer.t_visit_id = t_visit.t_visit_id 

);


insert into t_nhso_service_pp 
 (select 
'nh150'||substr(t_health_postpartum.t_health_postpartum_id,4) ||  t_health_postpartum.health_postpartum_active  || '05' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,case when (t_order.t_order_id is not null)
        then t_order.t_order_id
        else '' 
 end  as t_order_id
, 'PNC' as pp_name  
, t_health_postpartum.t_health_postpartum_id as pp_id
,'' as  pp_recompen
,case when (t_order.t_order_id is not null)
        then cast(floor(cast(order_qty as numeric) * cast(order_price as numeric)) as varchar)
        else ''
 end as pp_totalpay
, t_health_postpartum.health_postpartum_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
 from

 (select t_health_postpartum.t_visit_id
 FROM t_health_postpartum 
    left join t_nhso_service_pp 
 on t_health_postpartum.t_health_postpartum_id = t_nhso_service_pp.pp_id
 where t_health_postpartum.record_date_time  > '2552-10'
 and t_nhso_service_pp.t_nhso_service_pp_id is null) visit_pnc


 inner join t_health_postpartum 
 on visit_pnc.t_visit_id = t_health_postpartum.t_visit_id
 inner join t_visit 
 on t_health_postpartum.t_visit_id = t_visit.t_visit_id 

 left join t_order on (t_visit.t_visit_id = t_order.t_visit_id 
        and t_order.b_item_id in (select b_item_id from b_nhso_service_item where f_nhso_service_id = '5' and active = '1'))
);


insert into t_nhso_service_pp 
 (select 
'nh150' || substr(t_nhso_thalassemia.t_nhso_thalassemia_id,6) || '12' as t_nhso_service_pp_id
,t_visit.t_visit_id as t_visit_id 
,case when (t_order.t_order_id is not null)
        then t_order.t_order_id
        else '' 
 end  as t_order_id
, 'THALASSEMIA' as pp_name  
, t_nhso_thalassemia.t_nhso_thalassemia_id as pp_id
, '' as  pp_recompen
,case when (t_order.t_order_id is not null)
        then cast(floor(cast(order_qty as numeric) * cast(order_price as numeric)) as varchar)
        else ''
 end as pp_totalpay
, t_nhso_thalassemia.thalassemia_active as pp_active
, (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
, t_visit.visit_begin_visit_time as service_date
, '1' as service_new
 from


(select t_nhso_thalassemia.t_visit_id
 FROM t_nhso_thalassemia 
    left join t_nhso_service_pp 
 on t_nhso_thalassemia.t_nhso_thalassemia_id = t_nhso_service_pp.pp_id
 where t_nhso_thalassemia.thalassemia_record_date_time > '2552-10'
 and t_nhso_service_pp.t_nhso_service_pp_id is null) visit_thalass


 inner join t_nhso_thalassemia on visit_thalass.t_visit_id = t_nhso_thalassemia.t_visit_id
 inner join t_visit on t_nhso_thalassemia.t_visit_id = t_visit.t_visit_id 

 left join t_order on (t_visit.t_visit_id = t_order.t_visit_id 
        and t_order.b_item_id in (select b_item_id from b_nhso_service_item where f_nhso_service_id = '12' and active = '1'))
);

insert into t_nhso_service_pp 
 (
 select 
 'nh150' || substring(t_health_counsel.t_health_counsel_id,4) || t_health_counsel.counsel_active || '14' as t_nhso_service_pp_id
 ,t_visit.t_visit_id as t_visit_id 
 ,''  as t_order_id
 , 'ANCCONSULT' as pp_name  
 , t_health_counsel.t_health_counsel_id as pp_id
, '' as  pp_recompen
 ,''as pp_totalpay
 , t_health_counsel.counsel_active as pp_active
 , (cast(substring(cast(CURRENT_DATE as varchar),1,4) as numeric) + 543 || substring(cast(CURRENT_DATE as varchar),5) || ',00:00:00') as record_date_time
 , t_visit.visit_begin_visit_time as service_date
 , '1' as service_new
 from


 (select distinct t_health_counsel.t_visit_id

 from t_health_counsel 
    inner join t_health_anc on t_health_counsel.t_visit_id = t_health_anc.t_visit_id
    inner join b_nhso_map_concounseling ON t_health_counsel.b_health_counsel_type_id = b_nhso_map_concounseling.b_health_service_type_id
    left join t_nhso_service_pp on t_health_counsel.t_health_counsel_id = t_nhso_service_pp.pp_id
 where t_health_counsel.counsel_record_time > '2552-10'
 and b_nhso_map_concounseling.f_nhso_counseling_id in ('01','02','03','04','05','06','07','08')
  and t_nhso_service_pp.t_nhso_service_pp_id is null
) visit_counsel 



 inner join t_health_counsel 
 on visit_counsel.t_visit_id = t_health_counsel.t_visit_id
 inner join t_visit 
 on t_health_counsel.t_visit_id = t_visit.t_visit_id 

 );

 INSERT INTO s_nhso_version("s_version_id", "version_number", "version_description", "version_application_number",  "version_database_number", "version_update_time")
 VALUES('9701000000118', '18', 'Hospital OS, Community Edition', '3.14.1051', '0.6.041109', '2552-11-04,19:00:00');

 insert into s_script_update_log values ('nhso','update_nhso_ph18.sql',(select current_date) || ','|| (select  current_time),'update NHSO ph6 -> ph7');

