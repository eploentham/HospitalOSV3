SELECT 
	  b_site.b_visit_office_id || substring(t_health_family.t_health_family_id,9) as WOMAN_ID
    , b_site.b_visit_office_id AS PCUCODE 
	, t_health_family.patient_pid AS CID
	, CASE WHEN (t_health_family_planing.f_health_family_planing_method_id = '0'       
			OR  t_health_family_planing.f_health_family_planing_method_id = '1'        
			OR  t_health_family_planing.f_health_family_planing_method_id = '2'       
			OR  t_health_family_planing.f_health_family_planing_method_id = '3'      
			OR  t_health_family_planing.f_health_family_planing_method_id = '4'       
			OR  t_health_family_planing.f_health_family_planing_method_id = '5'     
			OR  t_health_family_planing.f_health_family_planing_method_id = '6'       
			OR  t_health_family_planing.f_health_family_planing_method_id = '7' )       
			THEN t_health_family_planing.f_health_family_planing_method_id        
		WHEN (t_health_family_planing.f_health_family_planing_method_id = '8'       
			OR t_health_family_planing.f_health_family_planing_method_id = '9' )       
			THEN '8'  
		ELSE '' END AS FPTYPE 
	, CASE WHEN t_health_family_planing.f_health_family_planing_id is not null
		THEN t_health_family_planing.f_health_family_planing_id
		ELSE '' END AS NOFP 
	, CASE WHEN t_health_family_planing.health_family_planing_parity is not null
		THEN t_health_family_planing.health_family_planing_parity
		ELSE '0' END  AS NUMSON
	, CASE  WHEN length(t_health_family.modify_date_time) >= 10
                THEN (to_number(substring(t_health_family.modify_date_time,1,4),'9999')-543)        
                        || substring(t_health_family.modify_date_time,6,2)        
                        || substring(t_health_family.modify_date_time,9,2) 
                ELSE '' END  AS UPDATE_DATE 
    , CASE WHEN length(t_health_family.record_date_time)>=10
            THEN(to_number(substring(t_health_family.record_date_time,1,4),'9999')-543)        
                        || substring(t_health_family.record_date_time,6,2)        
                        || substring(t_health_family.record_date_time,9,2)
            ELSE '' END  as NOTEDATE
    , CASE WHEN length(fp1.visit_begin_visit_time)>=10
            THEN(to_number(substring(fp1.visit_begin_visit_time,1,4),'9999')-543)        
                    || substring(fp1.visit_begin_visit_time,6,2)        
                    || substring(fp1.visit_begin_visit_time,9,2)
            ELSE '' END AS DATE_SERV 
    ,'1' as ACTIVE
FROM t_health_family  
	INNER JOIN t_health_home  ON t_health_home.t_health_home_id = t_health_family.t_health_home_id        
	INNER JOIN t_health_village  ON t_health_village.t_health_village_id = t_health_home.t_health_village_id        
	INNER JOIN ( 
                SELECT t_health_family_planing.t_health_family_id AS t_health_family_id
                    ,max(t_health_family_planing.health_family_planing_date) AS family_planing_date    
                    ,t_visit.visit_begin_visit_time as visit_begin_visit_time
                FROM t_health_family_planing INNER JOIN t_visit
                    ON t_health_family_planing.t_visit_id = t_visit.t_visit_id
                WHERE health_family_planing_active = '1'
                GROUP BY t_health_family_planing.t_health_family_id
                    ,t_visit.visit_begin_visit_time
                ) AS fp1  
    ON ( fp1.t_health_family_id = t_health_family.t_health_family_id )  
     LEFT JOIN t_health_family_planing on (t_health_family.t_health_family_id = t_health_family_planing.t_health_family_id
            AND fp1.family_planing_date = t_health_family_planing.health_family_planing_date
            and t_health_family_planing.health_family_planing_active = '1' )
	,b_site			
WHERE 
 t_health_family.health_family_active = '1'
    AND  t_health_family.f_sex_id  = '2'        
	AND t_health_family.f_patient_marriage_status_id = '2'        
	AND (cast(substring('' || age(to_date(?,'YYYY-MM-DD')
           ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') as int) > 15       
	AND cast(substring('' ||  age(to_date(?,'YYYY-MM-DD')
           ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') as int) < 45 )  
