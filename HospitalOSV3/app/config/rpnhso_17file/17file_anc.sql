SELECT distinct
        b_site.b_visit_office_id || substr(t_health_anc.t_health_anc_id,9)  as ANC_ID
        ,b_site.b_visit_office_id ||  substr(t_nhso_service_pp.t_nhso_service_pp_id,11)  as T_SERVICE_ID
        ,case when (t_health_pregnancy.health_pregnancy_gravida_number is not null and trim(t_health_pregnancy.health_pregnancy_gravida_number) <> '')
                then cast(cast(t_health_pregnancy.health_pregnancy_gravida_number as numeric) as varchar) 
                else '' 
        end as PREGNANCY_NUMBER 
        ,case when (t_health_anc.health_anc_no is not null and trim(t_health_anc.health_anc_no) <> '') 
                then cast(cast(t_health_anc.health_anc_no as numeric) as varchar) 
                else '' 
        end as ANC_NUMBER
        ,case when (t_health_anc.health_anc_gravida_week is not null and trim(t_health_anc.health_anc_gravida_week) <> '') 
                then cast(cast(t_health_anc.health_anc_gravida_week as numeric) as varchar) 
                else '' 
        end as GRAVIDA_WEEK
        ,case when (t_nhso_anc.health_anc_assess_health = '1' and t_nhso_anc.health_anc_assess_health_value in ('1','2'))
                    then  t_nhso_anc.health_anc_assess_health_value
                else '0'
         end as  BODYCHECK
        ,case when (t_nhso_anc.health_anc_pregnant_exam = '1' and t_nhso_anc.health_anc_pregnant_exam_value in ('1','2','3')) 
                    then t_nhso_anc.health_anc_pregnant_exam_value 
                else '0' 
         end as PREGNANCY
        ,case when (t_nhso_anc.health_anc_estimate_serious = '1' and t_nhso_anc.health_anc_estimate_serious_value in ('1','2','3'))
                    then t_nhso_anc.health_anc_estimate_serious_value
                else '0' 
          end as STRAIT
        ,case when ((t_health_anc.health_anc_tt ='1') OR (t_nhso_anc.nhso_anc_tetanus = '1'))
                    then '1'  
                else '2' 
         end as TT
        ,case when ((t_health_anc.health_anc_tt ='1') OR (t_nhso_anc.nhso_anc_tetanus = '1')) and t_health_anc.f_health_anc_tt_type in ('1','2','3')  
                    then t_health_anc.f_health_anc_tt_type
                    when  ((t_health_anc.health_anc_tt ='1') OR (t_nhso_anc.nhso_anc_tetanus = '1')) and t_health_anc.f_health_anc_tt_type = '4'
                    then trim(t_nhso_anc.nhso_anc_tetanus_at) 
                    else ''
        end as TT_NO
        ,case when t_nhso_anc.health_anc_sugar_albumin = '1'  and t_nhso_anc.health_anc_sugar_albumin_type is not null and trim(t_nhso_anc.health_anc_sugar_albumin_type) <> ''
                    then t_nhso_anc.health_anc_sugar_albumin_type
                when t_nhso_anc.health_anc_sugar_albumin = '0'
                    then t_nhso_anc.health_anc_sugar_albumin 
                else '0'
         end as USUGA
        ,case when t_nhso_anc.health_anc_albumin_result in ('0','1','2') 
                    then t_nhso_anc.health_anc_albumin_result
                when t_nhso_anc.health_anc_urine_sugar in ('0','1','2')
                    then t_nhso_anc.health_anc_urine_sugar 
                else '0'
         end as UPRO
        ,case when t_nhso_anc.health_anc_ofmcv = '1' and t_nhso_anc.nhso_anc_ofmcv_type is not null and trim(t_nhso_anc.nhso_anc_ofmcv_type) <> ''
                    then t_nhso_anc.nhso_anc_ofmcv_type
                    else '0' 
         end as OF_MCV 
        ,case when t_nhso_anc.health_anc_dcip_escreen = '1' and t_nhso_anc.nhso_dcip_escreen_type is not null and trim(t_nhso_anc.nhso_dcip_escreen_type) <> ''
                    then t_nhso_anc.nhso_dcip_escreen_type 
                    else '0'
        end as DCIP_ESCREEN 
        ,case when (t_nhso_anc.health_anc_cbc = '1' and t_nhso_anc.health_anc_cbc_type is not null and trim(t_nhso_anc.health_anc_cbc_type) <> '')
                    then t_nhso_anc.health_anc_cbc_type 
                else '0'
          end as CBC
        ,case when (t_health_anc.health_anc_hct  in ('1','2','3') )  
                    then t_health_anc.health_anc_hct
                else '0'
          end as  HCT
        ,case when (t_health_anc.health_anc_vdrl  in ('1','2','3') ) 
                    then t_health_anc.health_anc_vdrl    
                else '0' 
         end as VDRL
        ,case when (t_nhso_anc.health_anc_hbsag = '1'  and t_nhso_anc.nhso_anc_hbsag_type is not null and trim(t_nhso_anc.nhso_anc_hbsag_type) <> '') 
                    then t_nhso_anc.nhso_anc_hbsag_type   
                    else '0' 
         end as HBSAG
        ,case when (t_health_anc.health_anc_hiv = '1' and t_nhso_anc.nhso_anc_hiv_type is not null and trim(t_nhso_anc.nhso_anc_hiv_type) <> '') 
                    then  t_nhso_anc.nhso_anc_hiv_type
                    else '0' 
         end as HIV
        ,case when (t_nhso_anc.nhso_anc_blood_group in ('1','2','3','4') )
                    then t_nhso_anc.nhso_anc_blood_group
                when t_patient.f_patient_blood_group_id = '2'
                    then '1'
                when t_patient.f_patient_blood_group_id = '3'
                    then '2'
                when t_patient.f_patient_blood_group_id = '4'
                    then '3'
                when t_patient.f_patient_blood_group_id = '5'
                    then '4'
              else ''
         end as BLOODGROUP
        , case when t_nhso_anc.nhso_anc_rh = '1' and t_nhso_anc.nhso_anc_rh_group in ('1','2')
        	    then t_nhso_anc.nhso_anc_rh_group
              when (t_nhso_patient.nhso_patient_rh_group in ('1','2')) 
                    then t_nhso_patient.nhso_patient_rh_group
              else '0'
          end as RHGROUP
        ,'' as OTHER_LAB
        ,case when (select( b_site.b_visit_office_id || substring(pp_id,11)) 
                             from t_nhso_service_pp where pp_name = 'THALASSEMIA' and t_nhso_service_pp.t_visit_id = t_visit.t_visit_id and pp_active = '1' limit 1)  is not null
                then (select( b_site.b_visit_office_id || substring(pp_id,11)) 
                            from t_nhso_service_pp where pp_name = 'THALASSEMIA' and t_nhso_service_pp.t_visit_id = t_visit.t_visit_id and pp_active = '1' limit 1)
                else ''
           end  as THALAS_ID
        ,case when (t_nhso_anc.health_anc_ultrasound = '1') 
                    then t_nhso_anc.health_anc_ultrasound_type
                else '0'
         end as ULTRASOUND
        ,'' as OTHER_XRAY 
        ,case when (select ( b_site.b_visit_office_id || substring(pp_id,9)) 
                            from t_nhso_service_pp where pp_name = 'ANCDENT' and t_nhso_service_pp.t_visit_id = t_visit.t_visit_id and pp_active = '1' limit 1) is not null 
                  then (select ( b_site.b_visit_office_id || substring(pp_id,9)) 
                            from t_nhso_service_pp where pp_name = 'ANCDENT' and t_nhso_service_pp.t_visit_id = t_visit.t_visit_id and pp_active = '1' limit 1)
                  else ''
        end as DENT_ID
        ,t_nhso_anc.nhso_anc_office_id as HCODE_ANC
        ,case when(t_nhso_anc.health_anc_give_iodine IN ('0','1'))
                    then t_nhso_anc.health_anc_give_iodine
                else '0'
         end AS I2
        ,case when(t_nhso_anc.health_anc_give_folic IN ('0','1'))
                    then t_nhso_anc.health_anc_give_folic
                else '0'
         end as VITB9
        ,case when(t_nhso_anc.health_anc_give_fe IN ('0','1'))
                    then t_nhso_anc.health_anc_give_fe 
                else '0'
         end as FESO4
        ,case when t_nhso_anc.health_anc_personal_advice in ('0','1')
                     then t_nhso_anc.health_anc_personal_advice 
              else '0' 
         end as CONSULT_PERSON
        ,case when (select max(case when(b_nhso_map_concounseling.f_nhso_counseling_id = '01') 
                            then '1'
                                  when(b_nhso_map_concounseling.f_nhso_counseling_id = '05') 
                                    then '2'
                                  else '0'  end )
                            from t_health_counsel inner join b_nhso_map_concounseling 
                                on t_health_counsel.b_health_counsel_type_id = b_nhso_map_concounseling.b_health_service_type_id
                            where t_health_counsel.t_visit_id = t_visit.t_visit_id
                                and b_nhso_map_concounseling.f_nhso_counseling_id in ('01','05')) is not null
                 then (select max(case when(b_nhso_map_concounseling.f_nhso_counseling_id = '01') 
                            then '1'
                                  when(b_nhso_map_concounseling.f_nhso_counseling_id = '05') 
                                    then '2'
                                  else '0'  end )
                               from t_health_counsel inner join b_nhso_map_concounseling 
                                        on t_health_counsel.b_health_counsel_type_id = b_nhso_map_concounseling.b_health_service_type_id
                               where t_health_counsel.t_visit_id = t_visit.t_visit_id
                                        and b_nhso_map_concounseling.f_nhso_counseling_id in ('01','05'))
                else '0' 
        end as CONSULT_CHECKBLOOD
        ,case when (select max(case when(b_nhso_map_concounseling.f_nhso_counseling_id = '02') 
                                        then '1'
                                              when(b_nhso_map_concounseling.f_nhso_counseling_id = '06') 
                                                then '2'
                                              else '0'  end )
                               from t_health_counsel inner join b_nhso_map_concounseling 
                                        on t_health_counsel.b_health_counsel_type_id = b_nhso_map_concounseling.b_health_service_type_id
                               where t_health_counsel.t_visit_id = t_visit.t_visit_id
                                        and b_nhso_map_concounseling.f_nhso_counseling_id in ('02','06')) is not null
                   then (select max(case when(b_nhso_map_concounseling.f_nhso_counseling_id = '02') 
                                        then '1'
                                              when(b_nhso_map_concounseling.f_nhso_counseling_id = '06') 
                                                then '2'
                                              else '0'  end )
                               from t_health_counsel inner join b_nhso_map_concounseling 
                                        on t_health_counsel.b_health_counsel_type_id = b_nhso_map_concounseling.b_health_service_type_id
                               where t_health_counsel.t_visit_id = t_visit.t_visit_id
                                        and b_nhso_map_concounseling.f_nhso_counseling_id in ('02','06'))
                   else '0'                      
        end as CHECKBLOODHIV
        ,case when (select max(case when(b_nhso_map_concounseling.f_nhso_counseling_id = '03') 
                                        then '1'
                                              when(b_nhso_map_concounseling.f_nhso_counseling_id = '07') 
                                                then '2'
                                              else '0'  end )
                              from t_health_counsel inner join b_nhso_map_concounseling 
                                        on t_health_counsel.b_health_counsel_type_id = b_nhso_map_concounseling.b_health_service_type_id
                              where t_health_counsel.t_visit_id = t_visit.t_visit_id
                                        and b_nhso_map_concounseling.f_nhso_counseling_id in ('03','07')) is not null
                    then (select max(case when(b_nhso_map_concounseling.f_nhso_counseling_id = '03') 
                                        then '1'
                                              when(b_nhso_map_concounseling.f_nhso_counseling_id = '07') 
                                                then '2'
                                              else '0'  end )
                              from t_health_counsel inner join b_nhso_map_concounseling 
                                        on t_health_counsel.b_health_counsel_type_id = b_nhso_map_concounseling.b_health_service_type_id
                              where t_health_counsel.t_visit_id = t_visit.t_visit_id
                                        and b_nhso_map_concounseling.f_nhso_counseling_id in ('03','07')) 
                    else '0'
        end as CHECKBLOODTHALAS
        ,case when (select max(case when(b_nhso_map_concounseling.f_nhso_counseling_id = '04') 
                                        then 'การให้คำปรึกษาอื่น ก่อนเจาะเลือด'
                                              when(b_nhso_map_concounseling.f_nhso_counseling_id = '08') 
                                                then 'การให้คำปรึกษาอื่น หลังเจาะเลือด'
                                              else ' '  end )
                               from t_health_counsel inner join b_nhso_map_concounseling 
                                        on t_health_counsel.b_health_counsel_type_id = b_nhso_map_concounseling.b_health_service_type_id
                               where t_health_counsel.t_visit_id = t_visit.t_visit_id
                                        and b_nhso_map_concounseling.f_nhso_counseling_id in ('04','08')) is not null 
                   then (select max(case when(b_nhso_map_concounseling.f_nhso_counseling_id = '04') 
                                        then 'การให้คำปรึกษาอื่น ก่อนเจาะเลือด'
                                              when(b_nhso_map_concounseling.f_nhso_counseling_id = '08') 
                                                then 'การให้คำปรึกษาอื่น หลังเจาะเลือด'
                                              else ' '  end )
                               from t_health_counsel inner join b_nhso_map_concounseling 
                                        on t_health_counsel.b_health_counsel_type_id = b_nhso_map_concounseling.b_health_service_type_id
                               where t_health_counsel.t_visit_id = t_visit.t_visit_id
                                        and b_nhso_map_concounseling.f_nhso_counseling_id in ('04','08'))
                              || t_nhso_anc.nhso_anc_consult_other 
                    else t_nhso_anc.nhso_anc_consult_other 
        end as CONSULT_OTHER
        ,case when t_nhso_anc.health_anc_group_advice in ('0','1')
                     then t_nhso_anc.health_anc_group_advice 
              else '0' 
         end as CONSULT_GROUP 
        ,t_nhso_service_pp.pp_totalpay as totalpay
        ,visit_vn as vn
from t_health_anc
    inner join t_visit on t_health_anc.t_visit_id = t_visit.t_visit_id
    inner join t_patient on t_visit.t_patient_id = t_patient.t_patient_id
    inner join t_nhso_patient on t_patient.t_patient_id = t_nhso_patient.t_patient_id
    inner join t_health_pregnancy  on t_health_pregnancy.t_health_pregnancy_id = t_health_anc.t_health_pregnancy_id
    inner join t_health_anc_detail on t_health_anc_detail.t_health_anc_id = t_health_anc.t_health_anc_id
    inner join t_nhso_anc on t_nhso_anc.t_health_anc_id = t_health_anc.t_health_anc_id
    inner join t_nhso_service_pp on t_nhso_anc.t_nhso_anc_id = t_nhso_service_pp.pp_id
    ,b_site
where t_visit.f_visit_status_id <> '4'
        and t_nhso_service_pp.pp_active = '1'
        and t_health_anc.health_anc_active = '1'
        and substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
        and substr(t_visit.visit_begin_visit_time,1,10 )  <= ?
