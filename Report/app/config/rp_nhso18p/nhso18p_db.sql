SELECT distinct
substring(t_health_postpartum.t_health_postpartum_id,4)  as db_id
,b_site.b_visit_office_id ||  t_visit.visit_vn  as t_service_id

,t_health_postpartum.health_postpartum_pregnant_number as pregnancy_number
,t_health_postpartum.health_postpartum_visit as dbno
,t_nhso_postpartum.health_postpartum_exam as chkbio
,case when (t_visit_physical_exam.t_visit_physical_exam_id is null
    or t_visit_physical_exam.t_visit_physical_exam_id = 'null'
    or t_visit_physical_exam.t_visit_physical_exam_id = '')
then '0'
else '1'
end as 	chkbody
,t_nhso_postpartum.health_postpartum_guide as 	guids
,case when (t_health_family_planing.t_health_family_planing_id  is null
        or t_health_family_planing.t_health_family_planing_id = 'null'
        or t_health_family_planing.t_health_family_planing_id = '')
then '0'
else '1'
end as 	fp
,case when (t_health_family_planing.health_family_planing_cervix_method is null
or t_health_family_planing.health_family_planing_cervix_method = 'null'
or t_health_family_planing.health_family_planing_cervix_method = '')
then '0'
else '1'
end as 	pap
, case  when (length(t_health_family_planing.record_date_time)>=10)
        then to_char(to_date(to_number(
        substr(t_health_family_planing.record_date_time,1,4),'9999')-543 || 
        substr(t_health_family_planing.record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''     
		end  as 	pap_date
, case  when (length(t_health_delivery.health_delivery_born_date)>=10)
        then to_char(to_date(to_number(
        substr(t_health_delivery.health_delivery_born_date,1,4),'9999')-543 || 
        substr(t_health_delivery.health_delivery_born_date,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''   
		 end  as 	son_date

, case  when (length(t_health_postpartum.record_date_time)>=10)
        then to_char(to_date(to_number(
        substr(t_health_postpartum.record_date_time,1,4),'9999')-543 || 
        substr(t_health_postpartum.record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''  
		 end  as 	record_date
, case  when (length(t_health_postpartum.update_date_time)>=10)   
        then to_char(to_date(to_number(
        substr(t_health_postpartum.update_date_time,1,4),'9999')-543 || 
        substr(t_health_postpartum.update_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''    
		   end as 	modify_date 
,b_site.b_visit_office_id as pcucode
,  case  when (length(t_visit.visit_begin_visit_time) >=10) 
        then to_char(to_date(to_number(
        substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
        substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''  
   end as date_serv 
 ,t_patient.patient_pid as pid --‡æ‘Ë¡ø‘≈¥Ïµ“¡ datadict
,'1' as active



from t_health_postpartum
    inner join t_visit ON t_health_postpartum.t_visit_id = t_visit.t_visit_id
    --inner join t_health_delivery ON (t_visit.t_visit_id = t_health_delivery.t_visit_id
    --and t_health_delivery.health_delivery_active = '1')
    inner join t_patient on t_visit.t_patient_id = t_patient.t_patient_id
    inner join t_health_delivery ON (t_patient.t_patient_id = t_health_delivery.t_patient_id 
            and cast(t_health_delivery.gravida_number as numeric) = cast(t_health_postpartum.health_postpartum_pregnant_number as numeric)
            and t_health_delivery.health_delivery_active = '1')
    left join t_nhso_postpartum ON t_nhso_postpartum.t_health_postpartum_id = t_health_postpartum.t_health_postpartum_id
    left join t_visit_physical_exam ON t_visit_physical_exam.t_visit_id = t_visit.t_visit_id
    left join t_health_family_planing ON (t_health_family_planing.t_health_family_id = t_health_postpartum.t_health_family_id
    and t_health_family_planing.health_family_planing_active = '1')
    ,b_site
where t_visit.f_visit_status_id <> '4'
   AND t_health_postpartum.health_postpartum_active <> '0'
AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?
    
    
