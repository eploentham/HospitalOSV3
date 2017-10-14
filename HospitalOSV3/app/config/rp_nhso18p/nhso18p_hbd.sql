SELECT distinct
b_site.b_visit_office_id as pcucode
,substring(t_nhso_service.t_nhso_service_id,6) as hbd_id
,b_site.b_visit_office_id ||  t_visit.visit_vn  as t_service_id

,case when (t_nhso_consult.diabetes_has = '1')
        then '1'
        else '0'
        end as 	diabetesonly
,case when (t_nhso_consult.diabetes_has = '2')
        then '1'
        else '0'
        end as diabetescomplication
,case when (diaetesDM.icd10_number is not null)
        then diaetesDM.icd10_number
        else ''
        end as diabetesicd10
,case when (t_nhso_consult.diabetes_food is not null)
        then t_nhso_consult.diabetes_food
        else ''
        end as diabetesfood
,case when (t_nhso_consult.diabetes_exercise is not null)
        then t_nhso_consult.diabetes_exercise
        else ''
        end  as diabetesexercise
,case when (t_nhso_consult.diabetes_footcheck is not null)
        then t_nhso_consult.diabetes_footcheck
        else ''
        end as diabetesfootcheck
,case when (t_nhso_consult.diabetes_drug is not null)
        then t_nhso_consult.diabetes_drug
        else ''
        end as diabetesdrug
,case when (t_nhso_consult.diabetes_knowconplication is not null)
        then t_nhso_consult.diabetes_knowconplication
        else ''
        end as diabetesknowconplication
,case when (t_nhso_consult.diabetes_other is not null)
        then t_nhso_consult.diabetes_other
        else ''
        end as diabetesother
,case when (t_nhso_consult.diabetes_other_str is not null)
        then t_nhso_consult.diabetes_other_str
        else ''
        end as diabetesotherstr
,t_nhso_vital_sign.vital_sign_blood_sugar as diabetesdtx
,t_nhso_vital_sign.nhso_vital_sign_fbs_result as diabetesfbs
,case when (t_nhso_consult.hbd_has = '1')
        then '1'
        else '0'
        end as hbdonly
,case when (t_nhso_consult.hbd_has = '2')
        then '1'
        else '0'
        end as hbdcomplication
,case when (hbdDM.icd10_number is not null)
        then hbdDM.icd10_number
        else ''
        end as hbdicd10
,case when (t_nhso_consult.hbd_food is not null)
        then t_nhso_consult.hbd_food
        else ''
        end as hbdfood
,case when (t_nhso_consult.hbd_exercise is not null)
        then t_nhso_consult.hbd_exercise
        else ''
        end as hbdexercise
,case when (t_nhso_consult.hbd_drinksmoke is not null)
        then t_nhso_consult.hbd_drinksmoke
        else ''
        end as hbddrinksmoke
,case when (t_nhso_consult.hbd_drug is not null)
        then t_nhso_consult.hbd_drug
        else ''
        end as hbddrug
,case when (t_nhso_consult.hbd_strain is not null)
        then t_nhso_consult.hbd_strain
        else ''
        end as hbdstrain
,case when (t_nhso_consult.hbd_other is not null)
        then t_nhso_consult.hbd_other
        else ''
        end as hbdother
,case when (t_nhso_consult.hbd_other_str is not null)
        then t_nhso_consult.hbd_other_str
        else ''
        end as hbdotherstr
,t_nhso_vital_sign.vital_sign_pressure as pressure
, case when length(t_nhso_service.service_date_time)>=10
        then to_char(to_date(to_number(substr(t_nhso_service.service_date_time,1,4),'9999')-543
            || substr(t_nhso_service.service_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')
        else '' end as 	record_date
, case when length(t_nhso_service.service_modify_date_time)>=10
        then to_char(to_date(to_number(substr(t_nhso_service.service_modify_date_time,1,4),'9999')-543
            || substr(t_nhso_service.service_modify_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')
        else '' end as 	modify_date
,  case  when (length(t_visit.visit_begin_visit_time) >=10) 
        then to_char(to_date(to_number(substr(t_visit.visit_begin_visit_time,1,4),'9999')-543
            || substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')
        else '' end as date_serv
,t_patient.patient_pid as pid -- เพิ่มฟิลด์ ตาม datadict
,'1' as active
FROM   t_nhso_service
    INNER JOIN t_visit ON t_visit.t_visit_id = t_nhso_service.t_visit_id
   -- INNER JOIN t_health_family ON t_nhso_service.t_health_family_id = t_health_family.t_health_family_id -- join เพิ่ม
    INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id
    LEFT JOIN t_nhso_consult ON t_nhso_consult.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN t_nhso_vital_sign  ON t_nhso_vital_sign.t_nhso_service_id = t_nhso_service.t_nhso_service_id 
    LEFT JOIN b_icd10 as diaetesDM ON diaetesDM.b_icd10_id = t_nhso_consult.diabetes_icd10
    LEFT JOIN b_icd10 as hbdDM ON hbdDM.b_icd10_id = t_nhso_consult.hbd_icd10
    ,b_site

WHERE 
 t_visit.f_visit_status_id <> '4' 
  AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
	AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ? 