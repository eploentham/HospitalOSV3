
SELECT 
	distinct b_site.b_visit_office_id AS PCUCODE  --NOT NULL 
	, t_health_family.patient_pid AS CID 
        ,t_health_family.health_family_hn_hcis as PID --NOT NULL
	, CASE  
		WHEN length( trim(t_chronic.chronic_diagnosis_date)) >9    
			THEN (to_number(substring(t_chronic.chronic_diagnosis_date,1,4),'9999')-543)       
				|| substring(t_chronic.chronic_diagnosis_date,6,2)       
				|| substring(t_chronic.chronic_diagnosis_date,9,2) 
		ELSE '' END AS DATEDX 
        ,  replace(t_chronic.chronic_icd10,'.','') AS CHRONIC
	, CASE  WHEN  length( trim(t_chronic.chronic_discharge_date)) >9    
			THEN  (to_number(substring(t_chronic.chronic_discharge_date,1,4),'9999')-543)       
				|| substring(t_chronic.chronic_discharge_date,6,2)       
				|| substring(t_chronic.chronic_discharge_date,9,2) 
		ELSE '' END  AS DATEDIS 
	, t_chronic.f_chronic_discharge_status_id AS TYPEDIS  
        , case  when length(t_chronic.record_date_time) >= 10
                then case when length(cast(substring(t_chronic.record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_chronic.record_date_time,5),'-',''),',',''),':','')) = 14
                                  then (cast(substring(t_chronic.record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_chronic.record_date_time,5),'-',''),',',''),':',''))
                                  when length(cast(substring(t_chronic.record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_chronic.record_date_time,5),'-',''),',',''),':','')) =12
                                  then (cast(substring(t_chronic.record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_chronic.record_date_time,5),'-',''),',',''),':','')) || '00'
                                  when length(cast(substring(t_chronic.record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_chronic.record_date_time,5),'-',''),',',''),':','')) =10
                                  then (cast(substring(t_chronic.record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_chronic.record_date_time,5),'-',''),',',''),':','')) || '0000'
                                  when length(cast(substring(t_chronic.record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_chronic.record_date_time,5),'-',''),',',''),':','')) = 8
                                  then (cast(substring(t_chronic.record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_chronic.record_date_time,5),'-',''),',',''),':','')) || '000000'
                                  else '' end
else ''
                           end as D_UPDATE --NOT NULL 
FROM t_chronic  
	INNER JOIN t_health_family  ON t_chronic.t_health_family_id = t_health_family.t_health_family_id
	INNER JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    INNER JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
    ,b_site

WHERE  
 t_chronic.chronic_active <> '0'      
  AND health_family_active = '1'
  AND  substring(t_chronic.record_date_time,1,10) >= ?
  AND substring(t_chronic.record_date_time,1,10) <= ? 