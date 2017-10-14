SELECT distinct
substring(t_nhso_dental.t_nhso_dental_id,6)  as 	dentchild_id 
,b_site.b_visit_office_id ||  substr(t_nhso_service_pp.t_nhso_service_pp_id,11)  as T_SERVICE_ID
,case when (t_nhso_dental.dental_check_dent = '0')
    then '0'
    else '1'
    end as 	CONSULT_DENT
,case when (t_nhso_dental.dental_enamel_tooth = '1')
    then '1'
    else '2'
    end as 	check_sealant
,case when (trim(t_nhso_dental.dental_goalsealant) = '')
    then '0'
    else trim(t_nhso_dental.dental_goalsealant)
    end as goal_sealant 
,case when (trim(t_nhso_dental.dental_enamel_tooth_amount) = '')
   then '0'
   else trim(t_nhso_dental.dental_enamel_tooth_amount) 
   end as dent_no
,t_nhso_dental.dental_enamel_tooth  as sealant 
,case when (t_nhso_dental.dental_fluoride = '0')
    then '0'
    else '1'
    end as 	fluolide
,case when (trim(t_health_dental.dental_num_tooth) = '' or t_health_dental.dental_num_tooth = '00')
   then '0'
   else trim(t_health_dental.dental_num_tooth)
   end as permant
,case when (trim(t_health_dental.dental_num_bad_tooth) = '' or t_health_dental.dental_num_bad_tooth = '00')
   then '0'
   else trim(t_health_dental.dental_num_bad_tooth)
   end as permantcar 
,case when (trim(t_health_dental.dental_num_milktooth) = '' or t_health_dental.dental_num_milktooth = '00')
   then '0'
   else trim(t_health_dental.dental_num_milktooth)
   end as milktooth 
,case when (trim(t_health_dental.dental_num_bad_milktooth) = '' or t_health_dental.dental_num_bad_milktooth = '00')
   then '0'
   else trim(t_health_dental.dental_num_bad_milktooth)
   end as milktoothcar
,case when (t_nhso_dental.dental_periodontali = '0') 
	then '0' 
	else '1'
    end as PERIODONTALL
,case when (t_nhso_dental.dental_mouth_clean = '0') 
	then '0' 
	else '1'
    end as dentclean
,t_nhso_dental.dental_doctor_card_id as certificate_id_dentist 
,t_nhso_service_pp.pp_totalpay as totalpay 
        ,t_visit.visit_vn as vn
from t_health_dental 
    INNER JOIN t_visit ON t_health_dental.t_visit_id = t_visit.t_visit_id
    inner join t_patient on t_visit.t_patient_id = t_patient.t_patient_id 
    LEFT JOIN t_nhso_dental ON t_nhso_dental.t_health_dental_id = t_health_dental.t_health_dental_id 
     inner join t_nhso_service_pp on t_nhso_dental.t_nhso_dental_id = t_nhso_service_pp.pp_id
    ,b_site
where t_visit.f_visit_status_id <> '4'
	AND t_nhso_service_pp.pp_active = '1'
    AND  substr(t_visit.visit_begin_visit_time,1,10 )  >=  ?
	AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?
