SELECT 
	b_site.b_visit_office_id AS PCUCODE  --NOT NULL
    ,t_health_family.health_family_hn_hcis as PID --NOT NULL
	,t_visit.visit_vn AS SEQ 		--NOT NULL
	, (to_number(substring(t_visit.visit_begin_visit_time,1,5),'9999')-543)       
		|| substring(t_visit.visit_begin_visit_time,6,2)       
		|| substring(t_visit.visit_begin_visit_time,9,2) AS DATE_SERV --NOT NULL 
	, CASE WHEN t_diag_icd10.f_diag_icd10_type_id is not null
		then t_diag_icd10.f_diag_icd10_type_id
		else '' end AS DIAGTYPE 
	, CASE WHEN t_diag_icd10.diag_icd10_number is not null
		then replace(t_diag_icd10.diag_icd10_number,'.','')
		else '' end AS DIAGCODE  --NOT NULL
        ,case when t_diag_icd10.diag_icd10_update_date_time <> ''
		 then (to_number(substring(t_diag_icd10.diag_icd10_update_date_time,1,4),'9999') - 543)      
                        || substring(t_diag_icd10.diag_icd10_update_date_time,6,2)      
                        || substring(t_diag_icd10.diag_icd10_update_date_time,9,2)     
                        || substring(t_diag_icd10.diag_icd10_update_date_time,12,2)     
                        || substring(t_diag_icd10.diag_icd10_update_date_time,15,2)     
                        || substring(t_diag_icd10.diag_icd10_update_date_time,18,2) 
		 else  (to_number(substring(t_diag_icd10.diag_icd10_record_date_time,1,4),'9999') - 543)      
                        || substring(t_diag_icd10.diag_icd10_record_date_time,6,2)      
                        || substring(t_diag_icd10.diag_icd10_record_date_time,9,2)     
                        || substring(t_diag_icd10.diag_icd10_record_date_time,12,2)     
                        || substring(t_diag_icd10.diag_icd10_record_date_time,15,2)     
                        || substring(t_diag_icd10.diag_icd10_record_date_time,18,2)  
		  END AS D_UPDATE    --NOT NULL

    
FROM t_visit
	INNER JOIN t_patient  ON  (t_visit.t_patient_id = t_patient.t_patient_id)
	INNER JOIN t_health_family ON (t_health_family.t_health_family_id = t_patient.t_health_family_id)	
    INNER JOIN t_diag_icd10 ON (t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn 
        AND t_diag_icd10.diag_icd10_active = '1' )
    ,b_site

where  t_visit.f_visit_status_id <> '4'        
    AND health_family_active = '1'
	AND substring(t_visit.visit_begin_visit_time,1,16) >= ?
	AND substring(t_visit.visit_begin_visit_time,1,16) <= ?