select 
q1.counsel_id   
,q1.t_service_id 

,   max(q1.befor_check_blood  ) as befor_check_blood
,   max(q1.befor_check_blood_hiv  ) as befor_check_blood_hiv
,   max(q1.befor_check_blood_thalassaemia  ) as befor_check_blood_thalassaemia
,   max(q1.befor_check_blood_other  ) as befor_check_blood_other
, max(replace(q1.befor_check_blood_detail,'\n','')) as befor_check_blood_detail
,   max(q1.after_check_blood  ) as after_check_blood
,   max(q1.after_check_blood_hiv  ) as after_check_blood_hiv
,   max(q1.after_check_blood_thalassaemia   ) as after_check_blood_thalassaemia
,   max(q1.after_check_blood_other ) as after_check_blood_other
,max(replace(q1.after_check_blood_detail,'\n','')) as after_check_blood_detail
,   max(q1.cronic  ) as cronic
,   max(q1.tension  ) as tension
,   max(q1.drug  ) as drug
,   max(q1.counseling  ) as counseling
,  ''  as counseling_detail
,  max(replace(q1.counsel_detail,'\n',''))  as counsel_detail
,   q1.record_date 
,  max( q1.modify_date) as modify_date 
,   q1.pcucode    
,q1.date_serv
,q1.pid --เพิ่ม ฟิลด์ตาม datadict
,'1' as active
from (
SELECT
substring(t_visit.t_visit_id,4)  as 	counsel_id
,b_site.b_visit_office_id ||  t_visit.visit_vn  as t_service_id

,case when(b_nhso_map_concounseling.f_nhso_counseling_id = '01')    then '1'
    else '0'  end as 	befor_check_blood
,case when(b_nhso_map_concounseling.f_nhso_counseling_id = '02')    then '1'
    else '0' end  as 	befor_check_blood_hiv
,case when(b_nhso_map_concounseling.f_nhso_counseling_id = '03')    then '1'
    else '0' end  as 	befor_check_blood_thalassaemia
,case when(b_nhso_map_concounseling.f_nhso_counseling_id = '04')    then '1'
    else '0' end  as 	befor_check_blood_other
,case when b_nhso_map_concounseling.f_nhso_counseling_id IN ('01','02','03','04')
    then t_health_counsel.counsel_detail 
    else ''  end as 	befor_check_blood_detail
,case when(b_nhso_map_concounseling.f_nhso_counseling_id = '05')    then '1'
    else '0' end  as 	after_check_blood
,case when(b_nhso_map_concounseling.f_nhso_counseling_id = '06')    then '1'
    else '0' end  as 	after_check_blood_hiv
,case when(b_nhso_map_concounseling.f_nhso_counseling_id = '07')    then '1'
    else '0'  end  as 	after_check_blood_thalassaemia
,case when(b_nhso_map_concounseling.f_nhso_counseling_id = '08')    then '1'
    else '0' end  as 	after_check_blood_other
,case when b_nhso_map_concounseling.f_nhso_counseling_id IN ('05','06','07','08')
    then t_health_counsel.counsel_detail 
    else ''  end  as 	after_check_blood_detail
,case when(b_nhso_map_concounseling.f_nhso_counseling_id = '09')    then '1'
    else '0'  end  as 	cronic
,case when(b_nhso_map_concounseling.f_nhso_counseling_id = '10')    then '1'
    else '0' end  as 	tension
,case when(b_nhso_map_concounseling.f_nhso_counseling_id = '11')    then '1'
    else '0'  end  as 	drug
,case when(b_nhso_map_concounseling.f_nhso_counseling_id = '12')    then '1'
    else '0' end  as 	counseling
,'' as counseling_detail
,case when b_nhso_map_concounseling.f_nhso_counseling_id IN ('09','10','11','12')
    then t_health_counsel.counsel_detail 
    else '' end  as 	 counsel_detail
,case when (length(t_health_counsel.counsel_record_time)>=10)
        then to_char(to_date(to_number(
        substr(t_health_counsel.counsel_record_time,1,4),'9999')-543 || 
        substr(t_health_counsel.counsel_record_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''  
         end  as 	record_date
,case when (length(t_health_counsel.counsel_modify_time)>=10)
        then to_char(to_date(to_number(
        substr(t_health_counsel.counsel_modify_time,1,4),'9999')-543 || 
        substr(t_health_counsel.counsel_modify_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''   
         end as 	modify_date 
,b_site.b_visit_office_id as pcucode
,  case  when (length(t_visit.visit_begin_visit_time) >=10) 
        then to_char(to_date(to_number(
        substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
        substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''  
   end as date_serv 
,t_patient.patient_pid as pid

from t_health_counsel
    inner join t_visit ON t_health_counsel.t_visit_id = t_visit.t_visit_id 
    inner join t_patient ON t_health_counsel.t_patient_id = t_patient.t_patient_id -- join t_patient เพิ่ม
    inner join b_nhso_map_concounseling ON t_health_counsel.b_health_counsel_type_id = b_nhso_map_concounseling.b_health_service_type_id
    ,b_site

where 
counsel_active = '1'     AND 
t_visit.f_visit_status_id <> '4' 
AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?
) as q1
group by 
q1.counsel_id 
,q1.t_service_id   
,q1.record_date 
,q1.pcucode    
,q1.date_serv
,q1.pid   
