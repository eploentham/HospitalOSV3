SELECT 
distinct substring(t_health_nutrition.t_health_nutrition_id,4)  as growth_id
,b_site.b_visit_office_id ||  t_visit.visit_vn  as t_service_id

,t_visit.visit_patient_age  as 	growth_year
,to_number(t_health_nutrition.health_nutrition_age,'99')%12  as 	growth_month
,t_health_nutrition.health_nutrition_weight  as 	growth_weight
,t_health_nutrition.health_nutrition_high   as 	growth_height
,CASE WHEN t_nhso_nutrition.health_nutrition_weight_growup  is not null 
    then t_nhso_nutrition.health_nutrition_weight_growup
    else '' END as 	weight_growup
,CASE WHEN t_nhso_nutrition.health_nutrition_height_growup  is not null
    THEN t_nhso_nutrition.health_nutrition_height_growup 
    ELSE '' END as 	height_growup
,case when (t_visit_physical_exam.t_visit_physical_exam_id is null
    or t_visit_physical_exam.t_visit_physical_exam_id = 'null'
    or t_visit_physical_exam.t_visit_physical_exam_id = '')
    then '0'
    else '1' end as 	check_body

,case when t_nhso_grow_history.nhso_grow_history_assess_grow <>''
    then t_nhso_grow_history.nhso_grow_history_assess_grow
    else t_nhso_nutrition.health_nutrition_assess_grow end as assess_grow -- ปรับแก้ตาม datadict เอาฟิลด์จากตาราง t_nhso_grow_history
--,t_nhso_nutrition.health_nutrition_assess_grow as 	assess_grow
,replace(t_health_nutrition.health_nutrition_result,'\n','') as 	assess_comment
,t_nhso_nutrition.heatth_nutrition_thyroid as 	thyroid
,t_nhso_nutrition.health_nutrition_takecare as 	takecare
, case when (length(t_health_nutrition.record_date_time)>=10)
        then to_char(to_date(to_number(
        substr(t_health_nutrition.record_date_time,1,4),'9999')-543 || 
        substr(t_health_nutrition.record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''    
	 end as 	record_date
, case when (length(t_health_nutrition.modify_date_time)>=10)
        then to_char(to_date(to_number(
        substr(t_health_nutrition.modify_date_time,1,4),'9999')-543 || 
        substr(t_health_nutrition.modify_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''
        end as 	modify_date
,b_site.b_visit_office_id as pcucode
, case when length(t_visit.visit_begin_visit_time)>=10 
        then to_char(to_date(to_number(
        substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
        substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''  
		 end  as date_serv
 ,t_patient.patient_pid as pid -- เพิ่มฟิลด์ตาม datadict
,'1' as active



from t_health_nutrition
    INNER JOIN t_visit ON t_health_nutrition.t_visit_id = t_visit.t_visit_id
    INNER JOIN t_patient ON t_health_nutrition.t_patient_id = t_patient.t_patient_id -- join t_patient เพิ่ม
    INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_health_nutrition.t_health_family_id
    INNER JOIN t_nhso_nutrition ON t_nhso_nutrition.t_health_nutrition_id = t_health_nutrition.t_health_nutrition_id
    LEFT JOIN t_health_grow_history ON t_visit.t_visit_id = t_health_grow_history.t_visit_id-- join เพิ่ม
    LEFT JOIN t_nhso_grow_history ON t_health_grow_history.t_health_grow_history_id = t_nhso_grow_history.t_health_grow_history_id -- join เพิ่ม
    LEFT JOIN t_visit_physical_exam ON t_visit_physical_exam.t_visit_id = t_visit.t_visit_id
    ,b_site
    


where t_visit.f_visit_status_id <> '4'
    and t_health_family.health_family_active <> '0'
  and t_health_nutrition.health_nutrition_active  <> '0'
AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?
