--ทดสอบภาษาไทย
(SELECT 
	b_site.b_visit_office_id AS PCUCODE 
	, t_health_family.health_family_hn_hcis AS PID
	, CASE  
		WHEN (t_health_family_planing.f_health_family_planing_method_id = '0'        
                        OR t_health_family_planing.f_health_family_planing_method_id = '1'        
			OR  t_health_family_planing.f_health_family_planing_method_id = '2'       
			OR  t_health_family_planing.f_health_family_planing_method_id = '3'      
			OR  t_health_family_planing.f_health_family_planing_method_id = '4'       
			OR  t_health_family_planing.f_health_family_planing_method_id = '5'     
			OR  t_health_family_planing.f_health_family_planing_method_id = '6'       
			OR  t_health_family_planing.f_health_family_planing_method_id = '7' )       
			THEN t_health_family_planing.f_health_family_planing_method_id      
		ELSE '0' END AS FPTYPE 
	, CASE WHEN (t_health_family_planing.f_health_family_planing_method_id = '1'        
			OR  t_health_family_planing.f_health_family_planing_method_id = '2' 
			OR  t_health_family_planing.f_health_family_planing_method_id = '3'  )
                    THEN t_health_family_planing.f_health_family_planing_id
                ELSE '3' END AS NOFP 
	, CASE WHEN  t_health_family_planing.health_family_planing_parity is not null
        THEN t_health_family_planing.health_family_planing_parity
        ELSE '' END AS NUMSON
	, CASE  
		WHEN (t_health_family_planing.update_record_date_time is not null)  
        then (cast(substring(t_health_family_planing.update_record_date_time,1,4) as float)-543)      
				|| substring(t_health_family_planing.update_record_date_time,6,2)        
				|| substring(t_health_family_planing.update_record_date_time,9,2)  
        ELSE  (cast(substring(t_health_family.modify_date_time,1,4) as float)-543)      
				|| substring(t_health_family.modify_date_time,6,2)        
				|| substring(t_health_family.modify_date_time,9,2)   END  AS UPDATE 
			
FROM t_health_family
	INNER JOIN t_health_home  ON t_health_home.t_health_home_id = t_health_family.t_health_home_id        
	INNER JOIN t_health_village  ON t_health_village.t_health_village_id = t_health_home.t_health_village_id        
	LEFT JOIN ( 
		SELECT t_health_family_planing.t_health_family_id AS t_health_family_id
                ,max(t_health_family_planing.health_family_planing_date) AS family_planing_date                   
			FROM t_health_family_planing WHERE health_family_planing_active = '1'
			GROUP BY t_health_family_planing.t_health_family_id
        ) AS fp1  ON ( fp1.t_health_family_id = t_health_family.t_health_family_id )  
     LEFT JOIN t_health_family_planing on (t_health_family.t_health_family_id = t_health_family_planing.t_health_family_id
            AND fp1.family_planing_date = t_health_family_planing.health_family_planing_date
            and t_health_family_planing.health_family_planing_active = '1' )
	,b_site
			
WHERE  t_health_village.village_moo <> '0'
    AND health_family_active = '1'
    AND  t_health_family.f_sex_id  = '2'        
	AND t_health_family.f_patient_marriage_status_id = '2'        
	AND (cast(substring('' || age(to_date(?,'YYYY-MM-DD')
           ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') as int) > 15       
	AND cast(substring('' ||  age(to_date(?,'YYYY-MM-DD')
           ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') as int) < 45 )  
) 
