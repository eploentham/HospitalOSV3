SELECT
substring(t_nhso_home_visit.t_nhso_home_visit_id,6) as 	hv_id
,substring(t_nhso_home_visit.t_nhso_home_visit_id,6)  as 	hva_id
,t_patient.patient_pid as 	pid
	, CASE when f_patient_prefix.patient_prefix_description is not null
            THEN f_patient_prefix.patient_prefix_description
            ELSE '' END AS prefix_id
,t_health_family.patient_name as 	name
,t_health_family.patient_last_name as 	lname
,t_nhso_home_visit.home_visit_health as 	 chkhealth
,t_nhso_home_visit.home_visit_health_guide as 	suggest
,t_nhso_home_visit.home_visit_health_follow as 	drugvaccine
,t_nhso_home_visit.home_visit_health_other_activity as 	other
,t_nhso_home_visit.home_visit_health_other_activity_detail as 	other_detail
,t_nhso_home_visit.home_visit_service_nurse as 	nurse_survey
,t_nhso_home_visit.home_visit_service_health_officer as 	authority_survey
,case 
    when (length(t_nhso_home_visit.home_visit_record_date_time)>=10)   
        then to_char(to_date(to_number(
        substr(t_nhso_home_visit.home_visit_record_date_time,1,4),'9999')-543 || 
        substr(t_nhso_home_visit.home_visit_record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''    
        end as 	record_date
,case 
    when (length(t_nhso_home_visit.home_visit_modify_date_time)>=10)  
        then to_char(to_date(to_number(
        substr(t_nhso_home_visit.home_visit_modify_date_time,1,4),'9999')-543 || 
        substr(t_nhso_home_visit.home_visit_modify_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''
        end as 	modify_date
,b_site.b_visit_office_id as pcucode
,case when (length(t_nhso_home_visit.home_visit_date)>=10)  
        then to_char(to_date(to_number(
        substr(t_nhso_home_visit.home_visit_date,1,4),'9999')-543 || 
        substr(t_nhso_home_visit.home_visit_date,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''  
		    end as 	date_serv
,'1' as active
FROM t_nhso_home_visit 
    INNER JOIN t_health_family ON t_nhso_home_visit.t_health_family_id = t_health_family.t_health_family_id 
    INNER JOIN t_patient ON t_health_family.t_health_family_id = t_patient.t_health_family_id
    INNER JOIN t_health_home ON t_health_home.t_health_home_id = t_health_family.t_health_home_id
    INNER JOIN t_health_village ON t_health_village.t_health_village_id = t_health_home.t_health_village_id
    LEFT JOIN f_patient_prefix ON t_health_family.f_prefix_id = f_patient_prefix.f_patient_prefix_id
    ,b_site

where t_health_village.village_moo <> '0'
AND substr(t_nhso_home_visit.home_visit_date,1,10 )  >= ?
	AND  substr(t_nhso_home_visit.home_visit_date,1,10 )  <= ? 
