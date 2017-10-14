SELECT distinct
    b_site.b_visit_office_id || substring(t_chronic.t_chronic_id,9) as CHRONIC_ID
	,  b_site.b_visit_office_id AS PCUCODE 
	, case when  (t_health_family.patient_pid IS NULL or t_health_family.patient_pid = '')
                then t_patient.patient_pid 
                else t_health_family.patient_pid
       end  as CID
	, t_health_family.health_family_hn_hcis AS PID
	, CASE  
		WHEN length(t_chronic.chronic_diagnosis_date)>=10   
			THEN (to_number(substring(t_chronic.chronic_diagnosis_date,1,4),'9999')-543)       
				|| substring(t_chronic.chronic_diagnosis_date,6,2)       
				|| substring(t_chronic.chronic_diagnosis_date,9,2) 
		ELSE '' END AS DATEDX 
	, substring(t_chronic.chronic_icd10,1,3) || substring(t_chronic.chronic_icd10,5) AS CHRONIC 
	, CASE  
		WHEN  length(t_chronic.chronic_discharge_date)>=10       
			THEN  (to_number(substring(t_chronic.chronic_discharge_date,1,4),'9999')-543)       
				|| substring(t_chronic.chronic_discharge_date,6,2)       
				|| substring(t_chronic.chronic_discharge_date,9,2) 
		ELSE '' END  AS DATEDIS 
	, t_chronic.f_chronic_discharge_status_id AS TYPEDIS 
	, CASE  
		WHEN length(t_chronic.modify_date_time)>=10
            THEN  (to_number(substring(t_chronic.modify_date_time,1,4),'9999')-543)       
                    || substring(t_chronic.modify_date_time,6,2)       
                    || substring(t_chronic.modify_date_time,9,2) 
		ELSE '' END  AS UPDATE_DATE 
        ,CASE WHEN length(t_chronic.record_date_time)>=10
            THEN(to_number(substring(t_chronic.record_date_time,1,4),'9999')-543)        
                    || substring(t_chronic.record_date_time,6,2)        
                    || substring(t_chronic.record_date_time,9,2)
            ELSE '' END  as NOTEDATE
	, CASE WHEN length(t_visit.visit_begin_visit_time)>=10
            THEN(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        
                    || substring(t_visit.visit_begin_visit_time,6,2)        
                    || substring(t_visit.visit_begin_visit_time,9,2)
            ELSE '' END AS DATE_SERV 
    ,t_chronic.chronic_active as ACTIVE 
FROM t_chronic  
    INNER JOIN t_visit ON t_visit.t_visit_id = t_chronic.t_visit_id
    INNER JOIN t_patient ON t_patient.t_patient_id = t_visit.t_patient_id
	INNER JOIN t_health_family  ON t_chronic.t_health_family_id = t_health_family.t_health_family_id
	LEFT JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    LEFT JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
    ,b_site
WHERE   
    t_chronic.chronic_active = '1'    
    and  t_visit.f_visit_status_id <> '4'   
    and  substring(t_visit.visit_begin_visit_time,1,10) >= ?
	and substring(t_visit.visit_begin_visit_time,1,10) <= ?
