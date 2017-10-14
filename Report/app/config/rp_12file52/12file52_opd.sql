/*ทดสอบภาษาไทย*/
SELECT 
	t_patient.patient_hn AS HN  
	,(t_visit.f_visit_type_id || b_report_12files_map_clinic.b_report_12files_std_clinic_id || '1') AS CLINIC  
	, CASE WHEN (length(t_visit.visit_begin_visit_time)>=10)
then to_char(to_date(to_number(
substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END AS DATEOPD   
	,t_patient.patient_pid AS PERSON_ID
	,t_visit.visit_vn AS SEQ
	, substr(visit_begin_visit_time,12,2) || substr(visit_begin_visit_time,15,2) as TIMEOPD 
        ,'1' AS UUC  

FROM  t_visit
	INNER JOIN t_patient  ON (t_patient.t_patient_id = t_visit.t_patient_id) 
	LEFT JOIN t_diag_icd10  ON (t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id
        AND t_diag_icd10.f_diag_icd10_type_id = '1')
	LEFT JOIN b_report_12files_map_clinic  ON (t_diag_icd10.b_visit_clinic_id = b_report_12files_map_clinic.b_visit_clinic_id)  

WHERE  t_visit.f_visit_status_id <> '4'
	AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
	AND substring(t_visit.visit_begin_visit_time,1,10) <= ?

GROUP  BY  HN,CLINIC  ,DATEOPD,	 PERSON_ID,SEQ,TIMEOPD
 ORDER  BY  HN,CLINIC  ,DATEOPD


