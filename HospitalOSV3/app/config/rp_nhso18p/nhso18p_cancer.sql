SELECT
substring(t_nhso_screen_cancer.t_nhso_screen_cancer_id,4) as cancer_id
,b_site.b_visit_office_id || t_visit.visit_vn  as t_service_id

,case when (t_nhso_screen_cancer.screen_cancer_cervix_exam = '0')
 then '0'
 else '1'
 end as 	uterus_cancer
,case when (t_nhso_screen_cancer.screen_cancer_cervix_exam = '0')
 then '0'
 else '1' end
  as 	method_pap
,t_nhso_screen_cancer.screen_cancer_cervix_exam_notice   as 	uterus_cancer_detail
,case
    when (t_nhso_screen_cancer.screen_cancer_breast_exam = '0')
    then '0'
    else '1'
    end as 	cream_cancer
,replace(t_nhso_screen_cancer.screen_cancer_breast_exam_notice,'\n','')   as 	cream_cancer_detail
,  case  when (length(t_nhso_screen_cancer.screen_cancer_record_date_time) >=10) 
        then to_char(to_date(to_number(
        substr(t_nhso_screen_cancer.screen_cancer_record_date_time,1,4),'9999')-543 || 
        substr(t_nhso_screen_cancer.screen_cancer_record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else '' 
  end		 as 	record_date
,  case  when (length(t_nhso_screen_cancer.screen_cancer_record_date_time) >=10) 
        then to_char(to_date(to_number(
        substr(t_nhso_screen_cancer.screen_cancer_record_date_time,1,4),'9999')-543 || 
        substr(t_nhso_screen_cancer.screen_cancer_record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''  
   end as 	modify_date 
,b_site.b_visit_office_id as pcucode
,  case  when (length(t_visit.visit_begin_visit_time) >=10) 
        then to_char(to_date(to_number(
        substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
        substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''  
   end as date_serv 
 ,t_patient.patient_pid as pid --เพิ่มฟิลด์ตาม datadict
,'1' as active

FROM t_nhso_screen_cancer 
INNER JOIN t_visit ON t_nhso_screen_cancer.t_visit_id = t_visit.t_visit_id  
INNER JOIN t_patient ON t_nhso_screen_cancer.t_patient_id = t_patient.t_patient_id -- join กับ t_patient เพิ่ม
,b_site

WHERE  
screen_cancer_active = '1'    AND
 t_visit.f_visit_status_id <> '4'
AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?

    
