
SELECT distinct
b_site.b_visit_office_id  as pcucode
,substring(t_health_anc.t_health_anc_id,4)  as anc_id
,b_site.b_visit_office_id || t_visit.visit_vn  as t_service_id

,t_health_pregnancy.health_pregnancy_gravida_number as 	pregnancy_number
,t_health_anc.health_anc_no as 	anc_number
,t_health_anc.health_anc_gravida_week as 	gravida_week
,case when (t_health_anc.health_anc_vdrl = '0') then '0'
        else '1' end as 	vdrl
,case when (t_nhso_anc.health_anc_cbc= '0') then '0'
        else '1' end  as 	cbc
,case when (t_health_anc.health_anc_hiv = '0') then '0'
    else '1' end as 	hiv
,case when (t_health_anc.health_anc_hct = '0') then '0'
    else '1'  end as 	hct
,case when (t_nhso_anc.health_anc_of = '0') then '0'
    else '1' end as 	of
,case when (t_nhso_anc.health_anc_dcip = '0') then '0'
    else '1' end as 	dcip
,case when (t_health_anc.health_anc_dental_exam = '0') then '0'
    else '1' end as 	dental
,case when (t_nhso_anc.health_anc_mouth = '0') then '0'
    else '1' end as 	mouth
    ,case when (t_health_anc.health_anc_thalassemia ='0') then '0'
    else '1' end as 	thalassemia
,case when (t_health_anc.health_anc_tt ='0') then '0'
    else '1' end as 	tt
,case when (t_health_anc.health_anc_dental_tartar = '0') then '0'
    else '1' end as 	detail_vaginal
,case when (t_nhso_anc.health_anc_health_study = 'null'
    and t_nhso_anc.health_anc_health_study is null
    and t_nhso_anc.health_anc_health_study = '') then '0'
    else '1' end as 	hygiene
,case when (t_health_anc.health_anc_hct= '0') then '0'
    else '1' end as 	ua
,case when (t_health_anc_detail.health_anc_detail_headache <> '') then '1'
    else '0' end as 	 body_check
,case when (t_health_anc.health_anc_exam = '0') then '0'
    else '1' end as 	pregnent_check
,case when (t_nhso_anc.health_anc_hbt_confirm='0') then '0'
    else '1' end as 	comfirm_hbt
,case when (t_nhso_anc.health_anc_vdrl_confirm = '0') then '0'
    else '1' end as 	comfirm_vdrl
,case when (t_nhso_anc.health_anc_other= '0') then '0'
    else '1' end as 	lab_other
,case when (t_nhso_anc.health_anc_other_detail = '0') then '0'
    else '1' end as 	lab_detail
,case when t_nhso_anc.health_anc_dent_clean <> '1' then '0'
    else '1' end as	dent_clean
,'' as 	t_counsel_id
,nhso_anc_not_decayed_tooth as notmal_tooth
,nhso_anc_decayed_tooth as decayed_tooth
,nhso_anc_decayed_tooth_total as decayed_tooth_total
,nhso_anc_tooth_impaction as tooth_impaction
,nhso_anc_gum_empty as residual_ridge_gum
,nhso_anc_dental_fillings as dental_fillings
,nhso_anc_dental_fillings_total as dental_fillings_total
,nhso_anc_pull_out_tooth as pull_out_tooth
,nhso_anc_pull_out_tooth_total as pull_out_tooth_total
,nhso_anc_treat_tooth_root as treat_tooth_root
,nhso_anc_treat_tooth_root_total as treat_tooth_root_total
,nhso_anc_chop_tooth_impaction as wisdom_tooth_extraction
,nhso_anc_denture as denture
,nhso_anc_denture_total as denture_total
,health_anc_dent_clean  as tartar_scrape
,nhso_anc_smooth_root_tooth as tooth_root_scrape
,nhso_anc_treat_other as treat_other
,nhso_anc_treat_other_detail as treat_other_detail
,nhso_anc_gum_normal as normal_gum
,nhso_anc_inflame_gum as inflame_gum
,nhso_anc_tartar as tartar
,nhso_anc_periodontal_inflame as periodontal_disease
,nhso_anc_gum_periodontal_etc as gum_periodontal_other
,nhso_anc_gum_periodontal_etc_detail as gum_periodontal_other_detail
,nhso_anc_dental_clean_all as service_tartar_scrape
,nhso_anc_introduce_brush_teeth as suggest_teach_tooth_brush
,nhso_anc_license_isjob as certificate_id
,b_site.b_visit_office_id as hcode_anc
,t_patient.patient_phone_number as phone
, case when (length(t_health_pregnancy.record_date_time) >=10)     
        then to_char(to_date(to_number(
        substr(t_health_pregnancy.record_date_time,1,4),'9999')-543 || 
        substr(t_health_pregnancy.record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''
		   end as 	record_date
, case  when (length(t_health_pregnancy.modify_date_time) >=10)    
        then to_char(to_date(to_number(
        substr(t_health_pregnancy.modify_date_time,1,4),'9999')-543 || 
        substr(t_health_pregnancy.modify_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''  
		   end as 	modify_date
,  case  when (length(t_visit.visit_begin_visit_time) >=10) 
        then to_char(to_date(to_number(
        substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
        substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''  
   end as date_serv 
--konshow =>> panda ,'' as tor --‡æ‘Ë¡ø‘≈¥Ïµ“¡ datadict
,t_patient.patient_pid as pid -- ‡æ‘Ë¡ ø‘≈¥Ï pid
,'1' as active
from t_health_anc
inner join t_visit on t_health_anc.t_visit_id = t_visit.t_visit_id
inner join t_patient on t_health_anc.t_health_family_id = t_patient.t_health_family_id
--left join t_health_counsel on t_health_counsel.t_visit_id = t_visit.t_visit_id
inner join t_health_pregnancy  on (t_health_pregnancy.t_health_pregnancy_id = t_health_anc.t_health_pregnancy_id)
left join t_health_anc_detail on (t_health_anc_detail.t_health_anc_id = t_health_anc.t_health_anc_id and t_health_anc_detail.health_anc_detail_active='1')
left join t_nhso_anc on t_nhso_anc.t_health_anc_id = t_health_anc.t_health_anc_id

,b_site

where t_visit.f_visit_status_id <> '4'
  and t_health_anc.health_anc_active = '1'
and t_health_pregnancy.health_pregnancy_active='1'
  AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
  AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?

