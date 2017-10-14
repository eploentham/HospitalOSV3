SELECT distinct
substring(t_nhso_dmht_pp.t_nhso_dmht_pp_id,6) as dmht_id 
,b_site.b_visit_office_id ||  substr(t_nhso_service_pp.t_nhso_service_pp_id,11)  as T_SERVICE_ID
,case when (t_nhso_dmht_pp.diabetes_has = '1')
        then '1'
        when (t_nhso_dmht_pp.diabetes_has = '0')
	then '0'
	else '2'
        end as 	diabetesonly
,case when (t_nhso_dmht_pp.diabetes_complication = '1')
        then '1'
        else '0'
        end as diabetescomplication 
,case when (t_nhso_dmht_pp.diabetes_icd10 is not null )
        then diaetesDM.icd10_number
        else ''
        end as diabetesicd10
,case when(t_nhso_dmht_pp.diabetes_food='0')
        then '0'
        else '1'
        end as diabetesfood
,case when(t_nhso_dmht_pp.diabetes_exercise = '0')
        then '0'
        else '1'
        end  as diabetesexercise
,case when(t_nhso_dmht_pp.diabetes_footcheck = '0')
        then '0'
        else '1'
        end as diabetesfootcheck
,case when (t_nhso_dmht_pp.diabetes_drug = '0')
        then '0'
        else '1'
        end as diabetesdrug
,case when (t_nhso_dmht_pp.diabetes_knowconplication = '0')
        then '0'
        else '1'
        end as diabetesknowcomplication
,case when (t_nhso_dmht_pp.diabetes_other = '0')
        then '0'
        else '1'
        end as diabetesother
,(t_nhso_dmht_pp.diabetes_other_str) as diabetesotherstr
,t_nhso_dmht_pp.vital_sign_blood_sugar as diabetesdtx
,t_nhso_dmht_pp.nhso_vital_sign_fbs_result as diabetesfbs
,case when (t_nhso_dmht_pp.hbd_has='1')
        then '1'
	when (t_nhso_dmht_pp.hbd_has='0')
	then '0'
        else '2'
        end as hbonly
,case when(t_nhso_dmht_pp.hbd_complication = '1')
        then '1'
        else '0'
        end as hbcomplication
,hbdDM.icd10_number  as hbicd10 
,t_nhso_dmht_pp.hbd_food  as hbfood
,case when (t_nhso_dmht_pp.hbd_exercise = '0')
        then '0'
        else '1'
        end as hbexercise
,case when (t_nhso_dmht_pp.hbd_drinksmoke = '0')
        then '0'
        else '1'
        end as hbdrinksmoke
,case when  (t_nhso_dmht_pp.hbd_drug = '0')
        then '0'
        else '1'
        end as hbdrug
,case when (t_nhso_dmht_pp.hbd_strain = '0') 
        then '0'
        else '1'
        end as hbstrain
,case when (t_nhso_dmht_pp.hbd_other = '0') 
        then '0'
        else '1'
        end as hbother
,case when  (t_nhso_dmht_pp.hbd_other_str = '0')  
        then '0'
        else '1'
        end as hbotherstr
,substr(t_nhso_dmht_pp.vital_sign_pressure,1,position('/' in t_nhso_dmht_pp.vital_sign_pressure)-1) as hbsystolic 
,substr(t_nhso_dmht_pp.vital_sign_pressure,position('/' in t_nhso_dmht_pp.vital_sign_pressure)+1,(length(t_nhso_dmht_pp.vital_sign_pressure)-position('/' in t_nhso_dmht_pp.vital_sign_pressure))) as hbdiastolic
,t_nhso_service_pp.pp_totalpay as totalpay 
,pp_name
        ,t_visit.visit_vn as vn
FROM   t_visit 
    INNER JOIN t_patient on t_visit.t_patient_id = t_patient.t_patient_id 
    inner join t_nhso_dmht_pp on t_patient.t_health_family_id = t_nhso_dmht_pp.t_health_family_id
    inner join t_nhso_service_pp on t_nhso_dmht_pp.t_nhso_dmht_pp_id = t_nhso_service_pp.pp_id 
   LEFT JOIN b_icd10 as diaetesDM ON diaetesDM.b_icd10_id = t_nhso_dmht_pp.diabetes_icd10
   LEFT JOIN b_icd10 as hbdDM ON hbdDM.b_icd10_id = t_nhso_dmht_pp.hbd_icd10
    ,b_site

WHERE 
 t_visit.f_visit_status_id <> '4' 
 and t_nhso_service_pp.pp_active = '1'
 AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
   AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?