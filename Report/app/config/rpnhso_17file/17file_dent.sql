SELECT distinct
substring(t_nhso_dental_adult.t_nhso_dental_adult_id,6)  as DENT_ID
,b_site.b_visit_office_id ||  substr(t_nhso_service_pp.t_nhso_service_pp_id,11)  as T_SERVICE_ID
,case when (t_nhso_dental_adult.dental_adult_consultdent = '0')
    then '0'
    else '1'
    end as  CONSULT_DENT
,''  as OTHERE_DENT 
,case when (t_nhso_dental_adult.dental_adult_toothimpaction = '0')
    then '0'
    else '1'
    end as  TOOTH_IMPACTION
,case when (t_nhso_dental_adult.dental_adult_residual_ridge_gum = '0')
    then '0'
    else '1'
    end as  RESIDUAL_RIDGE_GUM
,case when (t_nhso_dental_adult.dental_adult_dentalcaries = '0')
    then '0'
    else '1'
    end as  DENTALCARIES
,t_nhso_dental_adult.dental_adult_dentalcaries_no as DENTALCARIES_NO
,case when (t_nhso_dental_adult.dental_adult_gumnormal = '0')
    then '0'
    else '1'
    end as  GUM_NORMAL
,case when (t_nhso_dental_adult.dental_adult_calculus = '0')
    then '0'
    else '1'
    end as  CALCULUS
,case when (t_nhso_dental_adult.dental_periodontali = '0')
    then '0'
    else '1'
    end as  PERIODONTALL
,t_nhso_dental_adult.dental_adult_gum_etc  as GUN_OTHER

,case when (trim(t_nhso_dental_adult.dental_adult_crown) = '')
   then '0'
   else trim(t_nhso_dental_adult.dental_adult_crown)
   end  as CROWN
,case when (trim(t_nhso_dental_adult.dental_adult_pulltooth) = '')
   then '0'
   else trim(t_nhso_dental_adult.dental_adult_pulltooth)
   end  as PULL_TOOTH
,case when (trim(t_nhso_dental_adult.dental_adult_rct) = '')
   then '0'
   else trim(t_nhso_dental_adult.dental_adult_rct)
   end  as RCT
,case when (trim(t_nhso_dental_adult.dental_adult_wte) = '')
   then '0' 
   else trim(t_nhso_dental_adult.dental_adult_wte)
   end  as WTE
,case when (trim(t_nhso_dental_adult.dental_adult_denture) = '')
   then '0'
   else trim(t_nhso_dental_adult.dental_adult_denture)
   end  as DENTURE
,case when (trim(t_nhso_dental_adult.dental_adult_calculus_scrape) = '')
   then '0' 
   else trim(t_nhso_dental_adult.dental_adult_calculus_scrape)
   end  as CALCULUS_SCRAPE
,case when (t_nhso_dental_adult.dental_adult_tooth_root_scrape = '0')
    then '0'
    else '1'
    end as  TOOTH_ROOT_SCRAPE
,t_nhso_dental_adult.dental_adult_other_dentshould  as OTHERDENT
,case when (t_nhso_dental_adult.dental_adult_service_tartar_scrape = '0')
    then '0'
    else '1'
    end as  SERVICE_TARTAR_SCRAPE
,t_nhso_dental_adult.dental_adult_certificate_id_dentist  as CERTIFICATE_ID_DENTIST
,t_nhso_service_pp.pp_totalpay  as TOTALPAY
        ,t_visit.visit_vn as vn
from t_health_dental 
    INNER JOIN t_visit ON t_health_dental.t_visit_id = t_visit.t_visit_id
    inner join t_nhso_dental_adult on t_health_dental.t_health_dental_id = t_nhso_dental_adult.t_health_dental_id
   inner join t_nhso_service_pp on t_nhso_dental_adult.t_nhso_dental_adult_id = t_nhso_service_pp.pp_id
    inner join t_patient on t_visit.t_patient_id = t_patient.t_patient_id 
    LEFT JOIN t_nhso_dental ON t_nhso_dental.t_health_dental_id = t_health_dental.t_health_dental_id or( t_health_dental.dental_active = '1')
    ,b_site

where t_visit.f_visit_status_id <> '4'
	AND t_nhso_service_pp.pp_active = '1'
    AND  substr(t_visit.visit_begin_visit_time,1,10 )  >=  ?
	AND  substr(t_visit.visit_begin_visit_time,1,10 )  <=  ?
