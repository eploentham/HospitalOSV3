 SELECT 
      b_site.b_visit_office_id AS PCUCODE 
    , case when  (t_health_family.patient_pid IS NULL or t_health_family.patient_pid = '')
                then t_patient.patient_pid 
                else t_health_family.patient_pid
       end  as CID
    ,t_visit.visit_vn  AS SEQ 
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
    , b_site.b_visit_office_id || substring(t_diag_icd10.t_diag_icd10_id,9) as id
         ,CASE WHEN length(diag_icd10_record_date_time)>=10
            THEN(to_number(substring(diag_icd10_record_date_time,1,4),'9999')-543)        
                    || substring(diag_icd10_record_date_time,6,2)        
                    || substring(diag_icd10_record_date_time,9,2)
            ELSE '' END AS notedate 
    ,'1' as active
    ,b_site.b_visit_office_id || substring(t_visit.t_visit_id,9) as SERVICE_ID
FROM t_visit
    INNER JOIN t_diag_icd10 ON (t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn 
        AND t_diag_icd10.diag_icd10_active = '1' )
	INNER JOIN t_patient  ON  (t_visit.t_patient_id = t_patient.t_patient_id)
	INNER JOIN t_health_family ON (t_health_family.t_health_family_id = t_patient.t_health_family_id)	
    ,b_site
where 
t_visit.f_visit_status_id <> '4'   
    AND  substring(t_visit.visit_begin_visit_time,1,10 )  >= ?
	AND  substring(t_visit.visit_begin_visit_time,1,10 )  <= ?
