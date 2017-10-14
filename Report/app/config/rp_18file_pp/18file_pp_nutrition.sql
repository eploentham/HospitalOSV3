--ทดสอบภาษาไทย
select  
	b_site.b_visit_office_id   AS PCUCODE  
	, t_health_family.patient_pid AS CID		
	,t_visit.visit_vn AS SEQ 		
	, CASE WHEN length(t_health_nutrition.modify_date_time)>10 
        THEN (cast(substring(t_health_nutrition.modify_date_time,1,4) as float)-543)        
				|| substring(t_health_nutrition.modify_date_time,6,2)        
				|| substring(t_health_nutrition.modify_date_time,9,2)        
          ELSE '' end AS DATE_SERV
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
    , CASE WHEN t_health_nutrition.f_health_nutrition_level_id='10' THEN 'N'
        WHEN t_health_nutrition.f_health_nutrition_level_id='11' THEN '1'
        WHEN t_health_nutrition.f_health_nutrition_level_id='12' THEN '2'
        WHEN t_health_nutrition.f_health_nutrition_level_id='09' THEN '1'
        WHEN t_health_nutrition.f_health_nutrition_level_id='08' THEN '2'
        ELSE '' END AS NLEVEL 	
		
FROM  t_health_nutrition 	  
    INNER JOIN t_visit  ON t_visit.t_visit_id = t_health_nutrition.t_visit_id           
    INNER JOIN t_health_family  ON t_health_nutrition.t_health_family_id = t_health_family.t_health_family_id
    ,b_site

 
 WHERE  t_health_nutrition.health_nutrition_active = '1'      
    AND health_family_active = '1'
    AND length(t_health_nutrition.modify_date_time)>10
    AND substring(t_health_nutrition.modify_date_time,1,10) >= ?
	AND substring(t_health_nutrition.modify_date_time,1,10) <= ?
