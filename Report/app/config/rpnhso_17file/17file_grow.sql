SELECT 
distinct substring(t_health_nutrition.t_health_nutrition_id,4)  as grow_id
,b_site.b_visit_office_id ||  substr(t_nhso_service_pp.t_nhso_service_pp_id,11)  as t_service_id
,t_visit.visit_patient_age  as 	growth_year
,to_number(t_health_nutrition.health_nutrition_age,'99')%12  as 	growth_month
,t_health_nutrition.health_nutrition_weight  as 	growth_weight
,t_health_nutrition.health_nutrition_high   as 	growth_height
,CASE when t_nhso_nutrition.health_nutrition_weight_growup  = '1' 
    then '1'
    when t_nhso_nutrition.health_nutrition_weight_growup  = '2' 
   then '2'
    when t_nhso_nutrition.health_nutrition_weight_growup  = '3' 
   then '3'
   when t_nhso_nutrition.health_nutrition_weight_growup  = '4' 
   then '4'
    else '5' 
    END as 	weight_growup
,CASE WHEN t_nhso_nutrition.health_nutrition_height_growup = '1'
    THEN '1'
    WHEN t_nhso_nutrition.health_nutrition_height_growup = '2'
    THEN '2'
    WHEN t_nhso_nutrition.health_nutrition_height_growup = '3'
    THEN '3'
    WHEN t_nhso_nutrition.health_nutrition_height_growup = '4'
    THEN '4'
    ELSE '5'
    END as 	height_growup
,case when (t_visit_physical_exam.t_visit_physical_exam_id is null
    or t_visit_physical_exam.t_visit_physical_exam_id = 'null'
    or t_visit_physical_exam.t_visit_physical_exam_id = '')
    then '0'
    else '1' end as 	check_body
,case when( t_nhso_nutrition.health_nutrition_assess_grow = '0')
  then '0'
  else '1'
  end as 	assess_grow
,replace(t_health_nutrition.health_nutrition_result,'\n','') as 	assess_comment
,case when( t_nhso_nutrition.heatth_nutrition_thyroid = '0' )
 then '0'
 else '1'
 end as 	thyroid
,case when (t_nhso_nutrition.health_nutrition_takecare='0') 
then '0'
else '1'
end as 	takecare
, t_nhso_service_pp.pp_totalpay  as totalpay
        ,t_visit.visit_vn as vn
from t_health_nutrition
    INNER JOIN t_visit ON t_health_nutrition.t_visit_id = t_visit.t_visit_id
    INNER JOIN t_patient on t_visit.t_patient_id = t_patient.t_patient_id 
    LEFT JOIN t_health_family ON t_health_family.t_health_family_id = t_health_nutrition.t_health_family_id
    INNER JOIN t_nhso_nutrition ON t_nhso_nutrition.t_health_nutrition_id = t_health_nutrition.t_health_nutrition_id
    LEFT JOIN t_visit_physical_exam ON t_visit_physical_exam.t_visit_id = t_visit.t_visit_id
     inner join t_nhso_service_pp on t_nhso_nutrition.t_nhso_nutrition_id = t_nhso_service_pp.pp_id
    ,b_site
    
where t_visit.f_visit_status_id <> '4'
     and t_health_nutrition.health_nutrition_active  = '1'
     and t_nhso_service_pp.pp_active = '1'
    AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
	AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?
