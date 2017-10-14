select distinct 
b_site.b_visit_office_id || substring(t_nhso_screen_cancer.t_nhso_screen_cancer_id,9) as BREAST_ID
,b_site.b_visit_office_id ||  substr(t_nhso_service_pp.t_nhso_service_pp_id,11)  as T_SERVICE_ID
,case when (screen_cancer_cervix_exam in ('0','1','2') )
	then screen_cancer_cervix_exam
	else '0'
	end as RESULTPAPSMEAR
,case when t_nhso_screen_cancer.screen_cancer_breast_exam in ('1','2')
      then t_nhso_screen_cancer.screen_cancer_breast_exam
      else '' 
      end as RESULTBREAST
,case when t_nhso_screen_cancer.screen_cancer_inform_breast_result in ('0','1')
      then  t_nhso_screen_cancer.screen_cancer_inform_breast_result
      else '0' 
      end as RESULTBREAST_INFORM
,replace(t_nhso_screen_cancer.screen_cancer_breast_exam_notice,'\n',' ') as RESULT
,t_nhso_service_pp.pp_totalpay as totalpay
        ,t_visit.visit_vn as vn
FROM t_nhso_screen_cancer 
INNER JOIN t_visit ON t_nhso_screen_cancer.t_visit_id = t_visit.t_visit_id
INNER JOIN t_nhso_service_pp ON t_nhso_screen_cancer.t_nhso_screen_cancer_id = t_nhso_service_pp.pp_id
INNER JOIN t_patient ON t_patient.t_patient_id = t_visit.t_patient_id 
,b_site

WHERE  
    t_nhso_screen_cancer.screen_cancer_active = '1'   
    and t_visit.f_visit_status_id <> '4' 
    and t_nhso_service_pp.pp_active = '1'
    and t_nhso_service_pp.pp_name = 'BREASTCANCER' 
    and screen_cancer_breast_check = '1'
    and substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
    and substr(t_visit.visit_begin_visit_time,1,10 )  <= ?