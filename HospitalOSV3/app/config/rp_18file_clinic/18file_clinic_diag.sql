 SELECT 
	b_site.b_visit_office_id AS PCUCODE 
	, t_health_family.patient_pid AS PID
    ,CASE WHEN (length(t_visit.visit_vn)>8)
            then substr(t_visit.visit_vn, length(t_visit.visit_vn)-7,length(t_visit.visit_vn))
        WHEN (length(t_visit.visit_an)>8 and f_visit_type_id = '1')
            then substr(t_visit.visit_an, length(t_visit.visit_an)-7,length(t_visit.visit_an))
        WHEN (f_visit_type_id = '1')
            then t_visit.visit_an
         else t_visit.visit_vn END AS SEQ 
	, CASE WHEN length(t_visit.visit_begin_visit_time)>=10
            THEN(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        
		|| substring(t_visit.visit_begin_visit_time,6,2)        
		|| substring(t_visit.visit_begin_visit_time,9,2)
            ELSE '' END AS DATE_SERV 
	, CASE WHEN t_diag_icd10.f_diag_icd10_type_id is not null
		then t_diag_icd10.f_diag_icd10_type_id
		else '' end AS DIAGTYPE 
	, CASE WHEN t_diag_icd10.diag_icd10_number is not null
		then t_diag_icd10.diag_icd10_number
		else '' end AS DIAGCODE  
FROM t_visit
    LEFT JOIN t_diag_icd10 ON (t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn 
        AND t_diag_icd10.diag_icd10_active = '1' )
	LEFT JOIN t_patient  ON  (t_visit.t_patient_id = t_patient.t_patient_id)
	LEFT JOIN t_health_family ON (t_health_family.t_health_family_id = t_patient.t_health_family_id)	
    ,b_site


where t_visit.f_visit_status_id <> '4'   
    AND health_family_active = '1'
    AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
	AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?


