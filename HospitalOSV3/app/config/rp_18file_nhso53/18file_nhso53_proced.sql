--PROCED_53
SELECT 
	b_site.b_visit_office_id AS PCUCODE --NOT NULL
    ,t_health_family.health_family_hn_hcis as PID --NOT NULL	
	,t_visit.visit_vn AS SEQ 	--NOT NULL
	, to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543       
				|| substring(t_visit.visit_begin_visit_time,6,2)       
				|| substring(t_visit.visit_begin_visit_time,9,2) AS DATE_SERV --NOT NULL
    ,replace(diag_icd9_icd9_number,'.','') AS PROCED --NOT NULL
    ,b_icd9.cost AS SERVPRIC  
	,CASE when t_diag_icd9.diag_icd9_update_date_time <> ''
                then (to_number(substring(t_diag_icd9.diag_icd9_update_date_time,1,4),'9999') - 543)      
                       || substring(t_diag_icd9.diag_icd9_update_date_time,6,2)      
                        || substring(t_diag_icd9.diag_icd9_update_date_time,9,2)  
                        || substring(t_diag_icd9.diag_icd9_update_date_time,12,2)  
                        || substring(t_diag_icd9.diag_icd9_update_date_time,15,2)  
                        || substring(t_diag_icd9.diag_icd9_update_date_time,18,2)    
		ELSE (to_number(substring(t_diag_icd9.diag_icd9_record_date_time,1,4),'9999') - 543)      
                       || substring(t_diag_icd9.diag_icd9_record_date_time,6,2)      
                        || substring(t_diag_icd9.diag_icd9_record_date_time,9,2)  
                        || substring(t_diag_icd9.diag_icd9_record_date_time,12,2)  
                        || substring(t_diag_icd9.diag_icd9_record_date_time,15,2)  
                        || substring(t_diag_icd9.diag_icd9_record_date_time,18,2)   END AS D_UPDATE  

FROM t_diag_icd9  
	INNER JOIN t_visit  ON (t_visit.t_visit_id = t_diag_icd9.diag_icd9_vn)
	INNER JOIN t_patient  ON (t_visit.t_patient_id = t_patient.t_patient_id )
	INNER JOIN t_health_family  ON t_health_family.t_health_family_id = t_patient.t_health_family_id  
    INNER JOIN b_icd9 ON diag_icd9_icd9_number = b_icd9.icd9_number
	,b_site 
WHERE t_visit.f_visit_status_id <> '4'
    AND t_diag_icd9.diag_icd9_active = '1'
    AND health_family_active = '1'
    AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
    AND substring(t_visit.visit_begin_visit_time,1,10) <= ?
    and t_visit.f_visit_type_id <> 'S'


	
