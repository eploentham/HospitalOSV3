SELECT distinct
b_site.b_visit_office_id || substring(t_nhso_dmht_pp.t_nhso_dmht_pp_id,11) as dmht_id 
,b_site.b_visit_office_id ||  substring(t_nhso_service_pp.t_nhso_service_pp_id,11)  as T_SERVICE_ID
,case when (t_nhso_dmht_pp.diabetes_has = '1' and t_nhso_dmht_pp.diabetes_complication = '1')
        then '2'
        when (t_nhso_dmht_pp.diabetes_has = '1' and (t_nhso_dmht_pp.diabetes_complication <> '1'))
        then '1'
        else '0'
        end as 	diabetesonly
,case when (t_nhso_dmht_pp.diabetes_has = '1' and t_nhso_dmht_pp.diabetes_complication = '1')
        then '1'
        else '0'
        end as diabetescomplication 
,case when t_nhso_dmht_pp.diabetes_has = '1' 
        then (case when (t_nhso_dmht_pp.diabetes_icd10 is not null and trim(t_nhso_dmht_pp.diabetes_icd10) <> '' )
                        then replace(trim(diaetesDM.icd10_number),'.','')
                        else ''
                    end )
        else ''
    end as diabetesicd10
,case when(t_nhso_dmht_pp.diabetes_food='1')
        then '1'
        else '0'
        end as diabetesfood
,case when(t_nhso_dmht_pp.diabetes_exercise = '1')
        then '1'
        else '0'
        end  as diabetesexercise
,case when(t_nhso_dmht_pp.diabetes_footcheck = '1')
        then '1'
        else '0'
        end as diabetesfootcheck
,case when (t_nhso_dmht_pp.diabetes_drug = '1')
        then '1'
        else '0'
        end as diabetesdrug
,case when (t_nhso_dmht_pp.diabetes_knowconplication = '1')
        then '1'
        else '0'
        end as diabetesknowcomplication
,case when (t_nhso_dmht_pp.diabetes_other = '1')
        then '1'
        else '0'
        end as diabetesother
,case when t_nhso_dmht_pp.diabetes_other = '1' 
        then trim(t_nhso_dmht_pp.diabetes_other_str) 
        else '' 
end as diabetesotherstr
,case when (t_nhso_dmht_pp.vital_sign_blood_sugar is not null and trim(t_nhso_dmht_pp.vital_sign_blood_sugar) <> '')
        then trim(t_nhso_dmht_pp.vital_sign_blood_sugar) 
        else ''
end as diabetesdtx
,case when (t_nhso_dmht_pp.nhso_vital_sign_fbs_result is not null and trim(t_nhso_dmht_pp.nhso_vital_sign_fbs_result) <> '') 
        then trim(t_nhso_dmht_pp.nhso_vital_sign_fbs_result) 
        else '' 
end as diabetesfbs
,case when (t_nhso_dmht_pp.hbd_has='1' and t_nhso_dmht_pp.hbd_complication = '1')
        then '2'
        when (t_nhso_dmht_pp.hbd_has='1' and t_nhso_dmht_pp.hbd_complication <> '1')
        then '1'
        else '0'
 end as hbonly
,case when(t_nhso_dmht_pp.hbd_has='1' and t_nhso_dmht_pp.hbd_complication = '1')
        then '1'
        else '0'
 end as hbcomplication
,case when t_nhso_dmht_pp.hbd_has = '1' 
        then (case when (hbdDM.icd10_number is not null and trim(hbdDM.icd10_number) <> '' )
                        then replace(trim(hbdDM.icd10_number),'.','')
                        else ''
                    end )
        else ''
    end as hbicd10
,case when (t_nhso_dmht_pp.hbd_food = '1')
        then '1'
        else '0'
        end as hbfood
,case when (t_nhso_dmht_pp.hbd_exercise = '1')
        then '1'
        else '0'
        end as hbexercise
,case when (t_nhso_dmht_pp.hbd_drinksmoke = '1')
        then '1'
        else '0'
        end as hbdrinksmoke
,case when  (t_nhso_dmht_pp.hbd_drug = '1')
        then '1'
        else '0'
        end as hbdrug
,case when (t_nhso_dmht_pp.hbd_strain = '1') 
        then '1'
        else '0'
        end as hbstrain
,case when (t_nhso_dmht_pp.hbd_other = '1') 
        then '1'
        else '0'
        end as hbother
,case when  (t_nhso_dmht_pp.hbd_other = '1' and t_nhso_dmht_pp.hbd_other_str is not null)  
        then trim(t_nhso_dmht_pp.hbd_other_str) 
        else ''
        end as hbotherstr
, case when (t_nhso_dmht_pp.vital_sign_pressure is not null  and trim(t_nhso_dmht_pp.vital_sign_pressure) <> '' )
            then substring(t_nhso_dmht_pp.vital_sign_pressure,1,position('/' in t_nhso_dmht_pp.vital_sign_pressure) -1) 
            else '' 
    end as hbsystolic 
, case when (t_nhso_dmht_pp.vital_sign_pressure is not null  and trim(t_nhso_dmht_pp.vital_sign_pressure) <> '' )
            then substring(t_nhso_dmht_pp.vital_sign_pressure,position('/' in t_nhso_dmht_pp.vital_sign_pressure)+1
                        ,(length(t_nhso_dmht_pp.vital_sign_pressure)-position('/' in t_nhso_dmht_pp.vital_sign_pressure))) 
            else '' 
  end as hbdiastolic
,t_nhso_service_pp.pp_totalpay as totalpay 
        ,visit_vn as vn
from t_visit 
   --pee add--
   inner join t_nhso_dmht_pp on t_visit.t_visit_id = t_nhso_dmht_pp.t_visit_id
   inner join t_patient on t_visit.t_patient_id = t_patient.t_patient_id 
   ---End--  
   -- inner join t_patient on t_visit.t_patient_id = t_patient.t_patient_id 
   --inner join t_nhso_dmht_pp on t_patient.t_health_family_id = t_nhso_dmht_pp.t_health_family_id
    inner join t_nhso_service_pp on t_nhso_dmht_pp.t_nhso_dmht_pp_id = t_nhso_service_pp.pp_id 
    left join b_icd10 as diaetesDM on diaetesDM.b_icd10_id = t_nhso_dmht_pp.diabetes_icd10
    left join b_icd10 as hbdDM on hbdDM.b_icd10_id = t_nhso_dmht_pp.hbd_icd10
    ,b_site
WHERE 
 t_visit.f_visit_status_id <> '4' 
 and t_nhso_service_pp.pp_active = '1'
 and t_nhso_dmht_pp.active = '1'
 and  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
 and  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?
