--ทดสอบภาษาไทย
select  
	b_site.b_visit_office_id   AS PCUCODE  
        , t_health_family.patient_pid AS PID
        ,CASE WHEN t_visit.visit_vn is not null
            THEN substring(t_visit.visit_vn,2,8) 
            ELSE ''  END AS SEQ 
	, CASE WHEN length(t_visit.visit_begin_visit_time)>=10
            THEN(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        
		|| substring(t_visit.visit_begin_visit_time,6,2)        
		|| substring(t_visit.visit_begin_visit_time,9,2)
            ELSE '' END AS DATE_SERV 
    , CASE
		WHEN ((substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
			, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') IS NOT NULL )       
			AND  (substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') IS NOT NULL ))         
			THEN  ((cast(substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') as float) * 12 )   
				+  cast(substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') as float))        
		WHEN  ((substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
			, to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year') IS NOT NULL ) 
			AND   (substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  
				,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') IS NULL ))       
			THEN  (cast(substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
			, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') as float) * 12 )       
		WHEN  ((substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)   
			,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') IS NULL )        
			AND   (substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') IS NOT NULL ))        
			THEN  cast(substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)   
				,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') as float)        
		ELSE 0  END  AS AGEMONTH  
	, CASE  
		WHEN (t_health_nutrition.health_nutrition_weight IS NOT NULL        
			AND t_health_nutrition.health_nutrition_weight <> ''        
			AND t_health_nutrition.health_nutrition_weight <> 'null' )        
			THEN (cast(t_health_nutrition.health_nutrition_weight as float) * 1000)        
		ELSE 0  END AS WEIGHT  
	, t_health_nutrition.health_nutrition_high  AS HEIGHT 
    , CASE WHEN t_nhso_nutrition.health_nutrition_weight_growup is null
        THEN ''
      WHEN t_nhso_nutrition.health_nutrition_weight_growup='3'
        THEN 'N'
      WHEN t_nhso_nutrition.health_nutrition_weight_growup='4'
        THEN '3'
      WHEN t_nhso_nutrition.health_nutrition_weight_growup='5'
        THEN '4'
      ELSE '' END AS NLEVEL 	

		
FROM  t_health_nutrition 	  
    INNER JOIN t_visit  ON t_visit.t_visit_id = t_health_nutrition.t_visit_id           
	LEFT JOIN t_patient on t_patient.t_patient_id = t_visit.t_patient_id
	LEFT JOIN t_health_family  ON t_patient.t_health_family_id = t_health_family.t_health_family_id        
    LEFT JOIN t_nhso_nutrition ON t_nhso_nutrition.t_health_nutrition_id = t_health_nutrition.t_health_nutrition_id
    ,b_site


 WHERE  t_health_nutrition.health_nutrition_active = '1'
    AND t_visit.f_visit_status_id <> '4'   
    AND health_family_active = '1'
    AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
	AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?
