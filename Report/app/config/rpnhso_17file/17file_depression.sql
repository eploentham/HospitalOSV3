SELECT distinct
substring(t_nhso_depression.t_nhso_depression_id,6)  as DEPRES_ID
,b_site.b_visit_office_id ||  substr(t_nhso_service_pp.t_nhso_service_pp_id,11)  as T_SERVICE_ID
,case when (t_nhso_depression.depression_check2q in ('1','2'))    
      then t_nhso_depression.depression_check2q
      else '2' 
      end as CHECK2Q
,case when (t_nhso_depression.depression_check9q IN ('0','1','2'))  
	then t_nhso_depression.depression_check9q
      else '0' 
      end as CHECK9Q
,case when (t_nhso_depression.depression_check8q IN ('0','1','2'))  
	then t_nhso_depression.depression_check8q
      else '0' 
      end as CHECK8Q
,case when (t_nhso_depression.depression_consult_depres IN ('0','1'))  
	then t_nhso_depression.depression_consult_depres
         else '0' 
        end as CONSULT_DEPRES
,case when (t_nhso_depression.depression_forroe9q1 IN ('0','1','2'))  
	then t_nhso_depression.depression_forroe9q1
      else '0' 
      end as FORROW9Q1
,case when (t_nhso_depression.depression_forroe9q2 IN ('0','1','2'))  
	then t_nhso_depression.depression_forroe9q2
      else '0' 
      end as FORROW9Q2
,case when (t_nhso_depression.depression_forroe9q3 IN ('0','1','2')) 
	then t_nhso_depression.depression_forroe9q3
      else '0' 
      end as FORROW9Q3
,case when (t_nhso_depression.depression_forroe9q4 IN ('0','1','2'))  
	then  t_nhso_depression.depression_forroe9q4  
      else '0' 
      end as FORROW9Q4
,t_nhso_service_pp.pp_totalpay as TOTALPAY
        ,t_visit.visit_vn as vn
from t_nhso_depression
inner join t_visit on t_nhso_depression.t_visit_id = t_visit.t_visit_id
inner join t_nhso_service_pp on t_nhso_depression.t_nhso_depression_id = t_nhso_service_pp.pp_id
,b_site

where t_visit.f_visit_status_id <> '4'
    and t_nhso_depression.depression_active = '1'
	AND t_nhso_service_pp.pp_active = '1'
AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?