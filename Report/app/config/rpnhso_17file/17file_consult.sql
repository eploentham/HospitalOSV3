SELECT
substring(t_health_counsel.t_health_counsel_id,4)  as CONSULT_ID
,substring(t_nhso_service_pp.t_nhso_service_pp_id,6)  as T_SERVICE_ID
,case when(b_nhso_map_concounseling.f_nhso_counseling_id = '09')    
	then '1'
	else '0'  
	end  as 	CRONIC
,case when(b_nhso_map_concounseling.f_nhso_counseling_id = '10')    
	then '1'
	else '0' 
	end  as TENSION
,case when(b_nhso_map_concounseling.f_nhso_counseling_id = '11')    
	then '1'
	else '0'  
	end  as DRUG
,case when(b_nhso_map_concounseling.f_nhso_counseling_id = '12')    
	then '1'
	else '0' 
	end  as COUNSELING
,t_health_counsel.counsel_remark as COUNSELING_DETAIL
,case when b_nhso_map_concounseling.f_nhso_counseling_id IN ('09','10','11','12')
    then t_health_counsel.counsel_detail 
    else '' end  as COUNSEL_DETAIL
,t_nhso_service_pp.pp_totalpay as  TOTALPAY
        ,t_visit.visit_vn as vn
from t_health_counsel
    inner join t_visit ON t_health_counsel.t_visit_id = t_visit.t_visit_id 
    inner join t_nhso_service_pp on t_health_counsel.t_health_counsel_id = t_nhso_service_pp.pp_id
    inner join t_patient on t_patient.t_patient_id = t_visit.t_patient_id  and t_patient.patient_active = '1'
    inner join b_nhso_map_concounseling ON t_health_counsel.b_health_counsel_type_id = b_nhso_map_concounseling.b_health_service_type_id
    ,b_site

where 
counsel_active = '1'     AND 
t_visit.f_visit_status_id <> '4' 
	AND t_nhso_service_pp.pp_active = '1'
    AND  substr(t_visit.visit_begin_visit_time,1,10 )  >=  ?
	AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?