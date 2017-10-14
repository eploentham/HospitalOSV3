SELECT 
	b_site.b_visit_office_id AS PCUCODE 
	, t_health_family.health_family_hn_hcis AS CID
    , t_visit.visit_vn  AS SEQ 
	, CASE WHEN length(t_visit.visit_begin_visit_time)>=10
            THEN(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        
                    || substring(t_visit.visit_begin_visit_time,6,2)        
                    || substring(t_visit.visit_begin_visit_time,9,2)
            ELSE '' END AS DATE_SERV 
    , ((to_number(substring(t_patient_appointment.patient_appointment_date,1,4),'9999')-543)       
            || substring(t_patient_appointment.patient_appointment_date,6,2)       
            || substring(t_patient_appointment.patient_appointment_date,9,2)) AS APDATE 
    , '' AS APTYPE 
	,  CASE  
		WHEN (t_diag_icd10.diag_icd10_number IS NULL       
			OR t_diag_icd10.diag_icd10_number = ''    
			OR t_diag_icd10.diag_icd10_number = 'null')       
			THEN ''       
            ELSE replace(t_diag_icd10.diag_icd10_number,'.','') END AS APDIAG 
    , CASE WHEN length(t_patient_appointment.patient_appointment_update_date_time)>=10
            THEN(to_number(substring(t_patient_appointment.patient_appointment_update_date_time,1,4),'9999')-543)        
                    || substring(t_patient_appointment.patient_appointment_update_date_time,6,2)        
                    || substring(t_patient_appointment.patient_appointment_update_date_time,9,2)
            ELSE '' END as UPDATE_DATE
	,t_patient_appointment.patient_appointment_active as ACTIVE
FROM t_patient_appointment 
	LEFT JOIN t_patient ON t_patient_appointment.t_patient_id = t_patient.t_patient_id  
	LEFT JOIN t_health_family  ON t_health_family.t_health_family_id = t_patient.t_health_family_id  
	LEFT JOIN t_visit ON (t_visit.t_visit_id=t_patient_appointment.t_visit_id 
        AND t_visit.f_visit_status_id <> '4') 
	LEFT JOIN t_diag_icd10  ON (t_patient_appointment.t_visit_id = t_diag_icd10.diag_icd10_vn 
		AND t_diag_icd10.f_diag_icd10_type_id = '1')
    ,b_site

WHERE 
    t_patient_appointment.patient_appointment_active = '1' 
    AND t_health_family.health_family_active = '1'
    AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
	AND substring(t_visit.visit_begin_visit_time,1,10) <= ?

