select 
substring(t_health_visit_home.t_health_visit_home_id,4) as hhc_id
,b_site.b_visit_office_id ||  substr(t_nhso_service_pp_out.t_nhso_service_pp_out_id,11) as T_SERVICE_ID
,case when(t_nhso_visit_home.visit_home_highblood_pressure IN ('0','1')) 
then t_nhso_visit_home.visit_home_highblood_pressure
else '0'
end as heightbloodpressure
,case when(t_nhso_visit_home.visit_home_diabetes IN ('0','1')) 
then t_nhso_visit_home.visit_home_diabetes
else '0'
end as diabetes
,case when(t_nhso_visit_home.visit_home_heart_diabetes IN ('0','1')) 
then t_nhso_visit_home.visit_home_heart_diabetes
else '0'
end as heartdisease
,case when(t_nhso_visit_home.visit_home_heart_storke IN ('0','1')) 
then t_nhso_visit_home.visit_home_heart_storke
else '0'
end as stroke
,case when(t_nhso_visit_home.visit_home_copd IN ('0','1')) 
then t_nhso_visit_home.visit_home_copd
else '0'
end as copd
,case when(t_nhso_visit_home.visit_home_asthma IN ('0','1')) 
then t_nhso_visit_home.visit_home_asthma
else '0'
end as asthma
,case when(t_nhso_visit_home.visit_home_tuberculosis IN ('0','1')) 
then t_nhso_visit_home.visit_home_tuberculosis
else '0'
end as tuberculosis
,case when(t_nhso_visit_home.visit_home_renalfail IN ('0','1')) 
then t_nhso_visit_home.visit_home_renalfail
else '0'
end as renalfail
,case when(t_nhso_visit_home.visit_home_aids IN ('0','1')) 
then t_nhso_visit_home.visit_home_aids
else '0'
end as aids
,case when(t_nhso_visit_home.visit_home_patien_final IN ('0','1')) 
then t_nhso_visit_home.visit_home_patien_final
else '0'
end as patientfinal
,case when(t_nhso_visit_home.visit_home_child_and_mom IN ('0','1')) 
then t_nhso_visit_home.visit_home_child_and_mom
else '0'
end as childandmom
,case when(t_nhso_visit_home.visit_home_forrow_child IN ('0','1')) 
then t_nhso_visit_home.visit_home_forrow_child
else '0'
end as forrowchild
,case when(t_nhso_visit_home.visit_home_other IN ('0','1')) 
then t_nhso_visit_home.visit_home_other
else '0'
end as otherdisease
,t_nhso_visit_home.visit_home_disease_detail as  disease_detail
,case when(t_nhso_visit_home.visit_home_health_suggest IN ('0','1')) 
then t_nhso_visit_home.visit_home_health_suggest
else '0'
end as suggest
,case when(t_nhso_visit_home.visit_home_drug_advice IN ('0','1')) 
then t_nhso_visit_home.visit_home_drug_advice
else '0'
end as drug
,case when(t_nhso_visit_home.visit_home_train IN ('0','1')) 
then t_nhso_visit_home.visit_home_train
else '0'
end as skill
,case when(t_nhso_visit_home.visit_home_physio IN ('0','1')) 
then t_nhso_visit_home.visit_home_physio
else '0'
end as physio
,case when(t_nhso_visit_home.visit_home_wound IN ('0','1')) 
then t_nhso_visit_home.visit_home_wound
else '0'
end as wound
,case when(t_nhso_visit_home.visit_home_ng IN ('0','1')) 
then t_nhso_visit_home.visit_home_ng
else '0'
end as ng
,case when(t_nhso_visit_home.visit_home_urine_tube IN ('0','1')) 
then t_nhso_visit_home.visit_home_urine_tube
else '0'
end as urine_tube
,case when(t_nhso_visit_home.visit_home_neck_pierce IN ('0','1')) 
then t_nhso_visit_home.visit_home_neck_pierce
else '0'
end as neck_pierce
,case when(t_nhso_visit_home.visit_home_clean_wen IN ('0','1')) 
then t_nhso_visit_home.visit_home_clean_wen
else '0'
end as renalcare
,case when(t_nhso_visit_home.visit_home_other_activity IN ('0','1')) 
then t_nhso_visit_home.visit_home_other_activity
else '0'
end as other_activity
,t_nhso_visit_home.visit_home_other_activity_detail  as other_detail
,case when(t_nhso_visit_home.visit_home_person_service IN ('1','2','3','4','5','6','7')) 
then t_nhso_visit_home.visit_home_person_service
else ''
end as person_service
,t_nhso_visit_home.visit_home_other_job_name as other_service
,t_nhso_visit_home.visit_home_pid as pid_service
,t_nhso_visit_home.visit_home_job_certificate_id as CERTNO
,case when (t_nhso_visit_home.visit_home_get_from IN ('1','2','3','4','5','6'))
then t_nhso_visit_home.visit_home_get_from
else ''
end as channel
,t_nhso_service_pp_out.pp_totalpay as totalpay

 from t_health_visit_home
INNER JOIN t_nhso_visit_home ON t_health_visit_home.t_health_visit_home_id = t_nhso_visit_home.t_health_visit_home_id
INNER JOIN  t_nhso_service_pp_out
ON t_health_visit_home.t_health_visit_home_id = t_nhso_service_pp_out.pp_id
INNER JOIN t_health_family ON t_health_visit_home.t_health_family_id = t_health_family.t_health_family_id
,b_site

WHERE 
--t_nhso_visit_home.visit_home_active = '1'     
 t_nhso_service_pp_out.pp_active = '1'
AND substr(t_health_visit_home.visit_home_date,1,10 )  >= ?
	AND  substr(t_health_visit_home.visit_home_date,1,10 )  <= ?
