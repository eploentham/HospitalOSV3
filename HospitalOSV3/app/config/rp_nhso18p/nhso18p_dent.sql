sELECT distinct
substring(t_health_dental.t_health_dental_id,4)  as 	dent_id
,b_site.b_visit_office_id || t_visit.visit_vn  as t_service_id

,case when (t_nhso_dental.dental_check_dent = '0')
    then '0'
    else '1'
    end as 	check_dent
,case when (t_nhso_dental.dental_fluoride = '0')
    then '0'
    else '1'
    end as 	fluoride
,case when (t_nhso_dental.dental_enamel = '0')
    then '0'
    else '1'
    end as 	enamel
, case 
		when (length(t_health_dental.dental_record_time)>=10) 
        then to_char(to_date(to_number(
        substr(t_health_dental.dental_record_time,1,4),'9999')-543 || 
        substr(t_health_dental.dental_record_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''    
		   end as 	record_date
, case 
		when (length(t_health_dental.dental_modify_time)>=10) 
        then to_char(to_date(to_number(
        substr(t_health_dental.dental_modify_time,1,4),'9999')-543 || 
        substr(t_health_dental.dental_modify_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''     
		   end as 	modify_date
,b_site.b_visit_office_id as pcucode
,  case  when (length(t_visit.visit_begin_visit_time) >=10) 
        then to_char(to_date(to_number(
        substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
        substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''  
   end as date_serv 
 ,t_patient.patient_pid as pid -- ‡æ‘Ë¡ø‘≈¥Ïµ“¡ datadict
,'1' as active

from t_health_dental 
    INNER JOIN t_visit ON t_health_dental.t_visit_id = t_visit.t_visit_id
    INNER JOIN t_patient ON t_health_dental.t_patient_id = t_patient.t_patient_id
    INNER JOIN t_nhso_dental ON t_nhso_dental.t_health_dental_id = t_health_dental.t_health_dental_id
    ,b_site

where t_visit.f_visit_status_id <> '4'
    and t_health_dental.dental_active = '1'
 AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
	AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?
