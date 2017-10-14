select  distinct
     b_site.b_visit_office_id || substring(t_health_nutrition.t_health_nutrition_id,9) as NUTRI_ID
	, b_site.b_visit_office_id   AS PCUCODE  
    , case when  (t_health_family.patient_pid IS NULL or t_health_family.patient_pid = '')
                then t_patient.patient_pid 
                else t_health_family.patient_pid
       end  as CID
    , t_visit.visit_vn  AS SEQ 
	, CASE WHEN length(t_visit.visit_begin_visit_time)>=10
            THEN(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        
                    || substring(t_visit.visit_begin_visit_time,6,2)        
                    || substring(t_visit.visit_begin_visit_time,9,2)
            ELSE '' END AS DATE_SERV 
    , t_health_nutrition.health_nutrition_age AS AGEMONTH  
	, CASE WHEN (t_health_nutrition.health_nutrition_weight IS NOT NULL        
			AND t_health_nutrition.health_nutrition_weight <> ''        
			AND t_health_nutrition.health_nutrition_weight <> 'null' )        
			THEN (cast(t_health_nutrition.health_nutrition_weight as float) * 1000)        
		ELSE 0  END AS WEIGHT  
	, t_health_nutrition.health_nutrition_high  AS "HEIGHT" 
    , CASE WHEN t_health_nutrition.f_health_nutrition_level_id is null
                THEN ''
              WHEN t_health_nutrition.f_health_nutrition_level_id='10'
                THEN '3'
              WHEN t_health_nutrition.f_health_nutrition_level_id='11'
                THEN '2'
              WHEN t_health_nutrition.f_health_nutrition_level_id='12'
                THEN '1'
              WHEN t_health_nutrition.f_health_nutrition_level_id='09'
                THEN '4'
              WHEN t_health_nutrition.f_health_nutrition_level_id='08'
                THEN '5'
              ELSE '' END AS NLEVEL 	
      , CASE WHEN length(t_health_nutrition.record_date_time)>=10
            THEN(to_number(substring(t_health_nutrition.record_date_time,1,4),'9999')-543)        
                    || substring(t_health_nutrition.record_date_time,6,2)        
                    || substring(t_health_nutrition.record_date_time,9,2)
            ELSE '' END as NOTEDATE
    , t_health_nutrition.health_nutrition_active as ACTIVE
    , b_site.b_visit_office_id || substring(t_visit.t_visit_id,9) as SERVICE_ID
FROM  t_health_nutrition 	  
    INNER JOIN t_visit  ON t_visit.t_visit_id = t_health_nutrition.t_visit_id       
    INNER JOIN t_patient ON t_patient.t_patient_id = t_visit.t_patient_id
    INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id
    ,b_site
 WHERE  
    t_health_nutrition.health_nutrition_active = '1'    
    AND t_visit.f_visit_status_id <> '4' 
    AND cast(t_visit.visit_patient_age as numeric) < 6
    AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
	AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?
