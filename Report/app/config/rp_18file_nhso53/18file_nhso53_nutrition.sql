--NUTRITION_53
select  
	b_site.b_visit_office_id   AS PCUCODE   --NOT NULL
    ,t_health_family.health_family_hn_hcis as PID --NOT NULL
	,t_visit.visit_vn AS SEQ 	--NOT NULL	
    ,CASE WHEN length(t_visit.visit_begin_visit_time)>10 
        THEN (cast(substring(t_visit.visit_begin_visit_time,1,4) as float)-543)        
				|| substring(t_visit.visit_begin_visit_time,6,2)        
				|| substring(t_visit.visit_begin_visit_time,9,2)        
        ELSE '' end AS DATE_SERV --NOT NULL
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
	, t_health_nutrition.health_nutrition_weight AS WEIGHT  
	, t_health_nutrition.health_nutrition_high  AS HEIGHT 
    , case when r_rp1853_nutritionlevel_id is not null 
then r_rp1853_nutritionlevel_id else '' end AS NLEVEL 
  ,CASE when length(t_health_nutrition.modify_date_time)=19
                then (to_number(substring(t_health_nutrition.modify_date_time,1,4),'9999') - 543)      
                       || substring(t_health_nutrition.modify_date_time,6,2)      
                        || substring(t_health_nutrition.modify_date_time,9,2)  
                        || substring(t_health_nutrition.modify_date_time,12,2)  
                        || substring(t_health_nutrition.modify_date_time,15,2)  
                        || substring(t_health_nutrition.modify_date_time,18,2)    
            when length(t_health_nutrition.modify_date_time)=16
                then (to_number(substring(t_health_nutrition.modify_date_time,1,4),'9999') - 543)      
                       || substring(t_health_nutrition.modify_date_time,6,2)      
                        || substring(t_health_nutrition.modify_date_time,9,2)  
                        || substring(t_health_nutrition.modify_date_time,12,2)  
                        || substring(t_health_nutrition.modify_date_time,15,2)  
                        || '00'   
		ELSE ''  END AS D_UPDATE --NOT NULL
		
FROM  t_health_nutrition 	  
    INNER JOIN t_visit  ON t_visit.t_visit_id = t_health_nutrition.t_visit_id           
    INNER JOIN t_health_family  ON t_health_nutrition.t_health_family_id = t_health_family.t_health_family_id 
    ,b_site
 
 
 WHERE  t_health_nutrition.health_nutrition_active = '1'      
    AND health_family_active = '1'
    AND length(t_health_nutrition.modify_date_time)>10
    AND substring(t_health_nutrition.modify_date_time,1,10) >= ?
	AND substring(t_health_nutrition.modify_date_time,1,10) <= ?

--    AND substring(t_health_nutrition.modify_date_time,1,10) >= '2551-03-01'
--	AND substring(t_health_nutrition.modify_date_time,1,10) <= '2552-09-31'

UNION 

select distinct
b_site.b_visit_office_id AS PCUCODE
,t_health_family.health_family_hn_hcis as PID
,t_visit.visit_vn AS SEQ 
,CASE WHEN length(t_visit.visit_begin_visit_time)>10 
        THEN (cast(substring(t_visit.visit_begin_visit_time,1,4) as float)-543)        
				|| substring(t_visit.visit_begin_visit_time,6,2)        
				|| substring(t_visit.visit_begin_visit_time,9,2)        
        ELSE '' end AS DATE_SERV
    , CASE
		WHEN ((substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)  ,'YYYY-MM-DD')
			, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') IS NOT NULL )       
			AND  (substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') IS NOT NULL ))         
			THEN  ((cast(substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') as float) * 12 )   
				+  cast(substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') as float))        
		WHEN  ((substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)  ,'YYYY-MM-DD')
			, to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year') IS NOT NULL ) 
			AND   (substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)  
				,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') IS NULL ))       
			THEN  (cast(substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)  ,'YYYY-MM-DD')
			, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') as float) * 12 )       
		WHEN  ((substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)   
			,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') IS NULL )        
			AND   (substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') IS NOT NULL ))        
			THEN  cast(substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)   
				,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') as float)        
		ELSE 0  END  AS AGEMONTH  
,t_visit_vital_sign.visit_vital_sign_weight AS WEIGHT  
,t_visit_vital_sign.visit_vital_sign_height AS HEIGHT 
,case when t_visit_vital_sign.f_visit_nutrition_level_id ='08'
then '5'
when t_visit_vital_sign.f_visit_nutrition_level_id ='09'
then '4'
when t_visit_vital_sign.f_visit_nutrition_level_id ='10'
then '3'
when t_visit_vital_sign.f_visit_nutrition_level_id ='11'
then '2'
when t_visit_vital_sign.f_visit_nutrition_level_id ='12'
then '1'
else '' end AS NLEVEL 
  ,CASE when length(t_visit_vital_sign.visit_vital_sign_modify_date_time)=19
                then (to_number(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,1,4),'9999') - 543)      

                       || substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,6,2)      
                        || substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,9,2)  
                        || substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,12,2)  

                        || substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,15,2)  
                        || substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,18,2)    
            when length(t_visit_vital_sign.visit_vital_sign_modify_date_time)=16
                then (to_number(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,1,4),'9999') - 543)      
                       || substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,6,2)      
                        || substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,9,2)  
                        || substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,12,2)  
                        || substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,15,2)  
                        || '00'   
		ELSE ''  END AS D_UPDATE --NOT NULL
from t_visit_vital_sign inner join t_visit on t_visit.t_visit_id = t_visit_vital_sign.t_visit_id
inner join t_patient on t_visit.t_patient_id = t_patient.t_patient_id 
inner join t_health_family on t_patient.t_health_family_id = t_health_family.t_health_family_id
, b_site
where trim(t_visit_vital_sign.visit_vital_sign_weight) <> '' 
and t_visit.f_visit_status_id <> '4' 
and t_visit_vital_sign.visit_vital_sign_active = '1'
and substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,1,10) >= ?
and substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,1,10) <= ?
