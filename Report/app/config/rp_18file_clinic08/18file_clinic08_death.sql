--death
SELECT 
	b_site.b_visit_office_id AS PCUCODE 
	, case when  (t_health_family.patient_pid IS NULL or t_health_family.patient_pid = '')
                then t_patient.patient_pid
                else t_health_family.patient_pid
       end  as CID
	, t_health_family.patient_pid AS PID
	, CASE 
		WHEN ( t_death.death_date_time <> ''  
			and t_death.death_date_time <> 'null'  )       
			THEN  (to_number(substring(t_death.death_date_time,1,4),'9999')-543)       
				|| substring(t_death.death_date_time,6,2)       
				|| substring(t_death.death_date_time,9,2) 
		ELSE '' END AS DDEATH 
	, cdeath_a.icd10_number as CDEATH_A
	, cdeath_b.icd10_number as CDEATH_B
	, cdeath_c.icd10_number as CDEATH_C
	, cdeath_d.icd10_number as CDEATH_D
	, t_death.death_external_cause_of_injury AS ODISEASE 
	, t_death.death_cause AS CDEATH 
	, t_death.death_site AS PDEATH 
,t_death.death_active as active
FROM  t_death 
    INNER JOIN  t_health_family ON t_health_family.t_health_family_id = t_death.t_health_family_id 
    LEFT JOIN b_icd10 as cdeath_a on t_death.death_primary_diagnosis = cdeath_a.b_icd10_id
    LEFT JOIN b_icd10 as cdeath_b on t_death.death_comorbidity = cdeath_b.b_icd10_id
    LEFT JOIN b_icd10 as cdeath_c on t_death.death_complication = cdeath_c.b_icd10_id
    LEFT JOIN b_icd10 as cdeath_d on t_death.death_others = cdeath_d.b_icd10_id
	,b_site

WHERE  
t_death.death_active = '1'
--AND t_health_family.health_family_active = '1'
	AND substring(t_death.death_date_time,1,10) >= ?
	AND substring(t_death.death_date_time,1,10) <= ?
