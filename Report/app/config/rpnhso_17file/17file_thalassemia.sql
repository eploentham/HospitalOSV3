SELECT distinct
 b_site.b_visit_office_id || substring(t_nhso_thalassemia.t_nhso_thalassemia_id,11) as THALAS_ID
,b_site.b_visit_office_id || substring(t_nhso_service_pp.t_nhso_service_pp_id,11)  as T_SERVICE_ID
,case when t_nhso_thalassemia.thalassemia_hbtyping_type in ('0','1','2') 
      then t_nhso_thalassemia.thalassemia_hbtyping_type 
      else '0'
      end as HBTYPING
,case when t_nhso_thalassemia.thalassemia_alpha_type in ('0','1','2' ) 
      then t_nhso_thalassemia.thalassemia_alpha_type
      else '0' 
      end as ALPHATHALASSEMIA
,case when t_nhso_thalassemia.thalassemia_pnd_type in ('0','1','2' ) 
      then t_nhso_thalassemia.thalassemia_pnd_type
      else '0' 
      end as PND
,case when t_nhso_thalassemia.thalassemia_mutation_type in ('0','1','2' ) 
      then t_nhso_thalassemia.thalassemia_mutation_type 
      else '0' 
      end as MUTATION
,t_nhso_thalassemia.thalassemia_husband_pid_hb as PID_HB
,case when(t_nhso_thalassemia.thalassemia_card_type_hb in ('1','2','3','4'))
    then t_nhso_thalassemia.thalassemia_card_type_hb
    else ''
    end as PID_TYPE
,case when t_nhso_thalassemia.thalassemia_card_type_hb = '4'
     then t_nhso_thalassemia.thalassemia_other_card
     else ''
    end as PID_TYPE_NOTE
,case when t_nhso_thalassemia.thalassemia_ofmcv_type_hb in ('1','2') 
      then t_nhso_thalassemia.thalassemia_ofmcv_type_hb
      else '' 
      end  as OF_MCV_HB
,case when t_nhso_thalassemia.thalassemia_dcipescreen_type_hb in ('1','2') 
      then t_nhso_thalassemia.thalassemia_dcipescreen_type_hb
      else '' 
      end  as DCIP_ESCREEN_HB
--,t_nhso_thalassemia.thalassemia_pid as PID_PN
--,t_nhso_thalassemia.thalassemia_card_type as PID_TYPE_PN
--,t_nhso_thalassemia.thalassemia_other_card as PID_TYPE_NOTE_PN
,case when t_nhso_thalassemia.thalassemia_hbtyping_type_hb in ('1','2') 
      then t_nhso_thalassemia.thalassemia_hbtyping_type_hb
      else '' 
      end  as HBTYPING_HB 
,case when t_nhso_thalassemia.thalassemia_alpha_type_hb in ('1','2' ) 
      then  t_nhso_thalassemia.thalassemia_alpha_type_hb
      else '' 
      end  as ALPHATHALASSEMIA_HB 
/*,case when (t_nhso_thalassemia.thalassemia_pnd_type_hb = '1' ) 
then '1' 
      when (t_nhso_thalassemia.thalassemia_pnd_type_hb = '2' ) 
      then '2' 
      else '' 
      end  as PND_HB */
,case when t_nhso_thalassemia.thalassemia_mutation_type_hb in ('1','2') 
     then t_nhso_thalassemia.thalassemia_mutation_type_hb
     else '' 
     end  as MUTATION_HB 
,t_nhso_service_pp.pp_totalpay as totalpay_hb
        ,t_visit.visit_vn as vn
from t_nhso_thalassemia
inner join t_visit on t_nhso_thalassemia.t_visit_id = t_visit.t_visit_id
inner join t_nhso_service_pp on t_nhso_thalassemia.t_nhso_thalassemia_id = t_nhso_service_pp.pp_id
,b_site
where t_visit.f_visit_status_id <> '4'
    AND t_nhso_service_pp.pp_active = '1'
    and t_nhso_thalassemia.thalassemia_active = '1'
  AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
  AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?
