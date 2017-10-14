SELECT
substring(t_health_family_planing.t_health_family_planing_id,4) as  fp_id
,b_site.b_visit_office_id ||  t_visit.visit_vn  as t_service_id

,case when(t_health_family_planing.f_health_family_planing_method_id = '1') then '1'
    else '0' end as 	drug
,case when (t_health_family_planing.f_health_family_planing_method_id = '1') then t_health_family_planing.health_famlily_planing_supply_qty 
    else '' end as 	drug_dispend
,case when (t_health_family_planing.f_health_family_planing_method_id = '2') then '1'
    else '0' end as 	drug_ijt
,case when (t_health_family_planing.f_health_family_planing_method_id = '7') then '1'
    else '0' end as 	hemp_women
,case when (t_health_family_planing.f_health_family_planing_method_id = '6') then '1'
    else '0' end as 	hemp_men
,case when (t_health_family_planing.f_health_family_planing_method_id = '3') then '1'
    else '0' end as 	hoop
,case when (t_health_family_planing.f_health_family_planing_method_id = '4') then '1'
    else '0' end as 	drug_burty
,case when (t_health_family_planing.f_health_family_planing_method_id = '5') then '1'
    else '0' end as 	condom
,case when (t_health_family_planing.f_health_family_planing_method_id = '5') then t_health_family_planing.health_famlily_planing_supply_qty 
    else '' end as 	condom_dispend
, case  when length(t_health_family_planing.record_date_time)>=10 
        then to_char(to_date(to_number(
        substr(t_health_family_planing.record_date_time,1,4),'9999')-543 || 
        substr(t_health_family_planing.record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''    
		 end  as 	record_date
, case  when length(t_health_family_planing.update_record_date_time)>=10 
        then to_char(to_date(to_number(
        substr(t_health_family_planing.update_record_date_time,1,4),'9999')-543 || 
        substr(t_health_family_planing.update_record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''   
		   end as 	modify_date 
,b_site.b_visit_office_id as pcucode
, case when length(t_visit.visit_begin_visit_time)>=10 
        then to_char(to_date(to_number(
        substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
        substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''   
		 end  as date_serv
,t_patient.patient_pid as pid --‡æ‘Ë¡ø‘≈¥Ïµ“¡ datadict
,'1' as active
FROM t_health_family_planing 
    INNER JOIN t_visit ON t_health_family_planing.t_visit_id = t_visit.t_visit_id  
    INNER JOIN t_patient ON t_health_family_planing.t_patient_id = t_patient.t_patient_id--join ‡æ‘Ë¡
    ,b_site

WHERE t_visit.f_visit_status_id <> '4'
  AND health_family_planing_active = '1'
AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
	AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?  
