-- ÇÑ¹·Õèá¡éä¢
SELECT 
	distinct b_site.b_visit_office_id AS PCUCODE 
	, t_health_family.patient_pid AS PID 
	, t_health_family.health_family_hn_hcis AS P_ID
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
		ELSE '' END  AS UPDATE 
		
FROM t_chronic  
    INNER JOIN t_visit on t_visit.t_visit_id = t_chronic.t_visit_id
	INNER JOIN t_health_family  ON t_chronic.t_health_family_id = t_health_family.t_health_family_id
	LEFT JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    LEFT JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
    ,b_site

WHERE   t_chronic.chronic_active <> '0'
    and t_visit.f_visit_status_id <> '4'   
    AND health_family_active = '1'
    and  substring(t_visit.visit_begin_visit_time,1,10) >= ?
	and substring(t_visit.visit_begin_visit_time,1,10) <= ?
