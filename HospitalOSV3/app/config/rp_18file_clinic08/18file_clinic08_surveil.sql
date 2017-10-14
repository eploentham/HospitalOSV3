SELECT  DISTINCT   
    b_site.b_visit_office_id || substring(t_surveil.t_surveil_id,9) as SURVEIL_ID
	, b_site.b_visit_office_id AS PCUCODE 
	, case when  (t_health_family.patient_pid IS NULL or t_health_family.patient_pid = '')
                then t_patient.patient_pid 
                else t_health_family.patient_pid
       end  as CID
	, CASE WHEN t_health_family.health_family_hn_hcis is not null
		THEN t_health_family.health_family_hn_hcis 
		ELSE '' END AS PID
    , t_visit.visit_vn  AS SEQ 
	, CASE WHEN length(t_visit.visit_begin_visit_time)>=10
            THEN(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        
                    || substring(t_visit.visit_begin_visit_time,6,2)        
                    || substring(t_visit.visit_begin_visit_time,9,2)
            ELSE '' END AS DATE_SERV 
	, CASE WHEN t_surveil.surveil_icd10_number is not null
            THEN replace(t_surveil.surveil_icd10_number,'.','') 
            ELSE '' END AS DIAGCODE 
	, CASE WHEN length(b_group_icd10.group_icd10_group_rp506)=3
            THEN substring(b_group_icd10.group_icd10_group_rp506,2,2)
            WHEN length(b_group_icd10.group_icd10_group_rp506)=1
            THEN '0' || b_group_icd10.group_icd10_group_rp506
            ELSE b_group_icd10.group_icd10_group_rp506 END AS CODE506 
	, CASE WHEN (t_surveil.surveil_sick_date <> '' and t_surveil.surveil_sick_date <> 'null')       
			THEN (to_number(substring(t_surveil.surveil_sick_date,1,4),'9999')-543)        
				|| substring(t_surveil.surveil_sick_date,6,2) 
				|| substring(t_surveil.surveil_sick_date,9,2)       
		ELSE ' '   END AS ILLDATE 
	, CASE WHEN  t_health_home.health_home_house is not null
		THEN t_health_home.health_home_house 
		ELSE '' END  AS ILLHOUSE
	, CASE WHEN  t_health_home.health_home_moo is not null
		THEN t_health_home.health_home_moo 
		ELSE '' END   AS ILLVILL
	, CASE WHEN t_health_village.village_moo <> '0'      
			AND t_health_village.village_moo <> '00'      
			THEN substring(t_health_village.village_tambon,5,2)      
		WHEN (t_health_village.village_moo = '0'      
			OR t_health_village.village_moo = '00')     
			AND t_health_home.health_home_tambon <> ''      
			THEN substring(t_health_home.health_home_tambon,5,2)      
		ELSE '' END AS ILLTAMB
	, CASE WHEN t_health_village.village_moo <> '0'      
			AND t_health_village.village_moo <> '00'     
			THEN substring(t_health_village.village_tambon,3,2)       
		WHEN (t_health_village.village_moo = '0'      
			OR t_health_village.village_moo = '00')     
			AND t_health_home.health_home_tambon <> ''      
			THEN substring(t_health_home.health_home_tambon,3,2)      
		ELSE '' END AS ILLAMPU
	, CASE WHEN t_health_village.village_moo <> '0'      
			AND t_health_village.village_moo <> '00'      
			THEN substring(t_health_village.village_tambon,1,2)      
		WHEN (t_health_village.village_moo = '0'      
			OR t_health_village.village_moo = '00')      
			AND t_health_home.health_home_tambon <> ''      
			THEN substring(t_health_home.health_home_tambon,1,2)      
		ELSE '' END AS ILLCHAN 
	, t_surveil.f_chronic_discharge_status_id AS PTSTAT
	, CASE WHEN t_death.death_date_time is not null THEN 
            (to_number(substring(t_death.death_date_time,1,4),'9999')-543)      
                || substring(t_death.death_date_time,6,2)       
                || substring(t_death.death_date_time,9,2) 
            ELSE '' END AS DATE_DEATH 
	, t_surveil.surveil_complicate AS COMPLICA 
	, '' AS ORGANISM  
    ,CASE WHEN (t_surveil.surveil_sick_date <> '' and t_surveil.surveil_sick_date <> 'null')       
			THEN (to_number(substring(t_surveil.surveil_sick_date,1,4),'9999')-543)        
				|| substring(t_surveil.surveil_sick_date,6,2) 
				|| substring(t_surveil.surveil_sick_date,9,2)       
		ELSE ' '   END AS NOTEDATE
    ,'1' as ACTIVE
    ,b_site.b_visit_office_id || substring(t_visit.t_visit_id,9) as SERVICE_ID
FROM  t_surveil
	INNER JOIN t_visit ON t_visit.t_visit_id = t_surveil.t_visit_id
	INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_surveil.t_health_family_id 
	LEFT JOIN t_patient ON t_surveil.t_patient_id = t_patient.t_patient_id
	LEFT JOIN b_group_icd10 ON substring(t_surveil.surveil_icd10_number,1,3) = b_group_icd10.group_icd10_number 
	LEFT JOIN t_death ON (t_death.t_health_family_id = t_health_family.t_health_family_id 
        AND t_death.death_active = '1'
        AND t_death.t_visit_id = t_visit.t_visit_id)
	LEFT JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    LEFT JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
    ,b_site
WHERE t_visit.f_visit_status_id <> '4'   
    AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
    AND substring(t_visit.visit_begin_visit_time,1,10) <= ?