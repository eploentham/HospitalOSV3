SELECT distinct
b_site.b_visit_office_id || substring(t_health_postpartum.t_health_postpartum_id,9)  as PNC_ID
,b_site.b_visit_office_id ||  substr(t_nhso_service_pp.t_nhso_service_pp_id,11)  as  T_SERVICE_ID
,cast(t_health_delivery.gravida_number as numeric) as PREGNANCY_NUMBER
,case when t_health_delivery.health_delivery_born_date is null 
                    then '' 
          when  (length(t_health_delivery.health_delivery_born_date)>=10)
                    then to_char(to_date(to_number( substr(t_health_delivery.health_delivery_born_date,1,4),'9999')-543 || 
                                substr(t_health_delivery.health_delivery_born_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
          else ''   
 end AS SON_DATE
,cast(t_health_postpartum.health_postpartum_visit as numeric) as PNC
,case when t_nhso_postpartum.health_postpartum_exam in ('0','1','2')
                    then t_nhso_postpartum.health_postpartum_exam
          else '0' 
 end as CHECKBODY
,case when t_nhso_postpartum.health_postpartum_exam in ('0','1') 
                    then t_nhso_postpartum.health_postpartum_exam
          else '0' end as FP
,case when t_nhso_postpartum.health_postpartum_guide in ('0','1') 
                    then '1' 
          else '0' end as MUM
,case when t_nhso_postpartum.health_postpartum_pap_smear in ('0','1','2') 
                    then t_nhso_postpartum.health_postpartum_pap_smear
         else '0' end as PAPSMEAR
, case when t_nhso_postpartum.health_postpartum_pap_smear = '2'
                    then (select( b_site.b_visit_office_id || substring(pp_id,9)) 
                                from t_nhso_service_pp where pp_name = 'PAPSMEAR' and t_nhso_service_pp.t_visit_id = t_visit.t_visit_id and pp_active = '1')
           else '' 
  end as PAPID 
,case when (t_visit.visit_have_refer IN ('0','1')) 
	then t_visit.visit_have_refer
	else '0'
	end AS REFERPAP
,max(case when (t_nhso_screen_cancer.screen_cancer_breast_check IN ('1','0'))
    then t_nhso_screen_cancer.screen_cancer_breast_check
    else '0'
 end )as BREAST
,case when t_nhso_screen_cancer.screen_cancer_breast_check = '1' 
    then b_site.b_visit_office_id || substring(t_nhso_screen_cancer.t_nhso_screen_cancer_id,9)
    else ''
 end as BREASTID
,t_nhso_service_pp.pp_totalpay AS TOTALPAY
        ,t_visit.visit_vn as vn
from t_health_postpartum 
inner join t_nhso_postpartum on t_health_postpartum.t_health_postpartum_id = t_nhso_postpartum.t_health_postpartum_id
inner join t_visit on t_health_postpartum.t_visit_id = t_visit.t_visit_id
inner join t_patient on t_patient.t_patient_id = t_visit.t_patient_id
inner join t_nhso_service_pp on (t_nhso_service_pp.t_visit_id  = t_visit.t_visit_id AND t_nhso_service_pp.pp_active = '1' and t_nhso_service_pp.pp_name like 'PNC%')
left join t_health_delivery ON t_patient.t_patient_id = t_health_delivery.t_patient_id
left join t_health_pregnancy  on t_health_pregnancy.t_patient_id = t_patient.t_patient_id
left join t_nhso_screen_cancer on t_nhso_screen_cancer.t_visit_id = t_visit.t_visit_id
,b_site
where 
t_visit.f_visit_status_id <> '4'
AND t_health_postpartum.health_postpartum_active = '1'
AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?
group by
PNC_ID,
T_SERVICE_ID,
PREGNANCY_NUMBER,
SON_DATE
,PNC
,CHECKBODY
,FP
,MUM
,PAPSMEAR
,PAPID
,REFERPAP
,BREASTID
,TOTALPAY
,vn