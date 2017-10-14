--ทดสอบภาษาไทย
(
--anc------------------------------------------------------------------------------------------- 276
SELECT count(*) ,'anc' as name 
FROM t_health_anc 
WHERE t_health_anc.health_anc_active = '1'      
	AND t_health_anc.record_date_time >= '2550-06-01' 
    AND t_health_anc.record_date_time <= '2550-07-01' 
) 
union
 (
--appoint------------------------------------------------------------------------------------------- 560  8296
SELECT count(*) ,'appoint' as name 
FROM t_patient_appointment 
WHERE  t_patient_appointment.patient_appointment_record_date_time >= '2550-06-01' 
	AND t_patient_appointment.patient_appointment_record_date_time <= '2550-07-01'
)
 union
 (
--card------------------------------------------------------------------------------------------- 1046
SELECT count(*) ,'card' as name 
from  t_patient_payment
	INNER JOIN t_health_family  ON t_patient_payment.t_health_family_id = t_health_family.t_health_family_id
	INNER JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    INNER JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
WHERE  t_health_village.village_moo <> '0'    
	and t_patient_payment.patient_payment_record_date >= '2550-06-01'
	and t_patient_payment.patient_payment_record_date <= '2550-07-01'
) 
union
 (
--chronic-------------------------------------------------------------------------------------------48
SELECT count(*) ,'chronic' as name 
FROM t_chronic  
	INNER JOIN t_health_family  ON t_chronic.t_health_family_id = t_health_family.t_health_family_id
	INNER JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    INNER JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
WHERE  t_health_village.village_moo <> '0'
    and  t_chronic.modify_date_time >= '2550-06-01'
	and t_chronic.modify_date_time <= '2550-07-01'
) 
union
 (
--death------------------------------------------------------------------------------------------530
SELECT count(*) ,'death' as name 
FROM t_death
    INNER JOIN  t_health_family ON t_health_family.t_health_family_id = t_death.t_health_family_id 
WHERE  t_death.death_active = '1'        
	AND t_death.death_date_time >= '2550-06-01'        
	AND t_death.death_date_time <= '2550-07-01'
) 
union
 (
--drug-------------------------------------------------------------------------------------------76
SELECT  count(*) ,'diag' as name 
FROM   t_diag_icd10
where  t_diag_icd10.diag_icd10_active = '1' 
    AND t_diag_icd10.diag_icd10_diagnosis_date >= '2550-06-01'               
	AND t_diag_icd10.diag_icd10_diagnosis_date <= '2550-07-01'  
) 
 union
 (
--drug-------------------------------------------------------------------------------------------76
SELECT  count(*) ,'drug' as name 
FROM   t_order
where  t_order.f_item_group_id = '1' 
    AND t_order.f_order_status_id <> '3'
    AND t_order.order_date_time >= '2550-06-01'               
	AND t_order.order_date_time <= '2550-07-01'  
) 
union
(
--epi-------------------------------------------------------------------------------------------9547
SELECT  count(*) ,'epi' as name 
FROM   t_health_epi
WHERE  	t_health_epi.health_epi_active = '1'        
	AND t_health_epi.record_date_time >= '2550-06-01'
	AND t_health_epi.record_date_time <= '2550-07-01'
)
 union
 (
--fp-------------------------------------------------------------------------------------------0
SELECT  count(*) ,'fp' as name 
FROM   t_health_family_planing 
	INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_health_family_planing.t_health_family_id  
where   t_health_family_planing.health_family_planing_active = '1'       
    AND  t_health_family.f_sex_id  = '2' 
	AND t_health_family_planing.health_family_planing_date >= '2550-06-01'      
	AND t_health_family_planing.health_family_planing_date <= '2550-07-01'
) 
union
(
--home----------fail--------------------------------------------------------------------------------- 8058
select count(*) ,'home' as name 
FROM  t_health_home 
    INNER JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
where t_health_village.village_moo <> '0'
)
union
--mch------------------------------------------------------------------------------------------- 234
(
SELECT  count(*),'mch' as name
FROM   t_health_pregnancy
WHERE record_date_time >= '2550-06-01'
    and record_date_time <= '2550-07-01'
)
union
--nutri------------------------------------------------------------------------------------------ 6056
(
SELECT  count(*) ,'nutri' as name 
FROM t_health_nutrition
WHERE  t_health_nutrition.health_nutrition_active = '1'
    AND t_health_nutrition.record_date_time >= '2550-06-01'          
	AND t_health_nutrition.record_date_time <= '2550-07-01' 
) 
union
(
--person--���ࢵ   ���͡ࢵ������Ѻ��ԡ��-----------------------------------------------------------------------------------------
SELECT count(*) ,'person_in' as name 
FROM t_health_family
	INNER JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    INNER JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
WHERE  t_health_village.village_moo <> '0'
) 
union
(
SELECT count(*) ,'person_out' as name 
from  t_visit 
    INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id
    INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id
	INNER JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    INNER JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
where t_health_village.village_moo = '0'
and t_visit.visit_begin_visit_time >= '2550-06-01'
and t_visit.visit_begin_visit_time <= '2550-07-01'
)
union
--mch------------------------------------------------------------------------------------------- 234
(
SELECT  count(*),'pp' as name
FROM   t_health_delivery
    INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_health_delivery.t_health_family_id
	INNER JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    INNER JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
where t_health_village.village_moo = '0'
    AND t_health_delivery.health_delivery_active = '1'          
	AND t_health_delivery.update_date_time >= '2550-06-01'          
	AND t_health_delivery.update_date_time <= '2550-07-01' 
)
--proced-------------------------------------------------------------------------------------------
union
(
SELECT count(*)  ,'proced' as name 
FROM t_diag_icd9
	INNER JOIN t_visit   ON (t_visit.t_visit_id = t_diag_icd9.diag_icd9_vn)
	INNER JOIN t_patient  ON (t_visit.t_patient_id = t_patient.t_patient_id )
	INNER JOIN t_health_family  ON t_health_family.t_health_family_id = t_patient.t_health_family_id 

WHERE t_visit.f_visit_status_id <> '4'
    AND t_diag_icd9.diag_icd9_active = '1'
    AND t_visit.visit_begin_visit_time >= '2550-06-01' 
    AND t_visit.visit_begin_visit_time <= '2550-07-01' 
)
union
(
--service-------------------------------------------------------------------------------------------
SELECT  count(*)  ,'service' as name 
FROM  t_visit
	INNER JOIN t_patient   ON t_visit.t_patient_id = t_patient.t_patient_id  
	INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id 
WHERE t_visit.f_visit_status_id <> '4'  
    AND t_visit.visit_begin_visit_time >= '2550-06-01' 
    AND t_visit.visit_begin_visit_time <= '2550-07-01' 
) 
union
 (
--surveil----��ͧ�ա�� update t_visit_id ���������������ա�� update-------------------------------------------------
SELECT  count(*) ,'surveil' as name 
FROM  t_surveil
	INNER JOIN t_visit ON t_visit.t_visit_id = t_surveil.t_visit_id
WHERE t_visit.f_visit_status_id <> '4'
    AND t_visit.visit_begin_visit_time >= '2550-06-01' 
    AND t_visit.visit_begin_visit_time <= '2550-07-01'
) 
union
(
--mch------------------------------------------------------------------------------------------- 234
SELECT count(*),'woman_in' as name
FROM  t_health_family  			
	INNER JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    INNER JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
WHERE  t_health_village.village_moo <> '0'
    AND  t_health_family.f_sex_id  = '2'        
	AND t_health_family.f_patient_marriage_status_id = '2'        
	AND (cast(substring('' || age(to_date('2550-06-01','YYYY-MM-DD')
           ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') as int) >15       
	AND cast(substring('' ||  age(to_date('2550-06-01','YYYY-MM-DD')
           ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') as int) < 45 )  
)
union
(
SELECT count(*) ,'woman_out' as name 
from  t_health_family_planing 
    INNER JOIN t_visit  ON (t_health_family_planing.t_visit_id = t_visit.t_visit_id )     
    INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id
    INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id
	INNER JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    INNER JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id

WHERE  t_health_village.village_moo = '0'
    AND  t_health_family.f_sex_id  = '2'        
	AND t_health_family.f_patient_marriage_status_id = '2'        
	AND to_number(visit_patient_age,'999') >15       
	AND to_number(visit_patient_age,'999') < 45   
    AND t_visit.visit_begin_visit_time >= '2550-06-01' 
    AND t_visit.visit_begin_visit_time <= '2550-07-01'
)
