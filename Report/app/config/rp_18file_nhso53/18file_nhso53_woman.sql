--WOMEN_53
SELECT 
	b_site.b_visit_office_id AS PCUCODE  --NOT NULL
    ,t_health_family.health_family_hn_hcis as PID --NOT NULL
	, CASE  WHEN (t_health_family_planing.f_health_family_planing_method_id = '1'        
                OR  t_health_family_planing.f_health_family_planing_method_id = '2'       
                OR  t_health_family_planing.f_health_family_planing_method_id = '3'      
                OR  t_health_family_planing.f_health_family_planing_method_id = '4'       
                OR  t_health_family_planing.f_health_family_planing_method_id = '5'     
                OR  t_health_family_planing.f_health_family_planing_method_id = '6'       
                OR  t_health_family_planing.f_health_family_planing_method_id = '7' )       
                THEN t_health_family_planing.f_health_family_planing_method_id 
		ELSE '0' END AS FPTYPE   --NOT NULL
	, t_health_family_planing.f_health_family_planing_id AS NOFP 
	, t_health_family_planing.health_family_planing_parity AS NUMSON
    ,CASE when length(t_health_family_planing.record_date_time)=19
                then (to_number(substring(t_health_family_planing.record_date_time,1,4),'9999') - 543)      
                       || substring(t_health_family_planing.record_date_time,6,2)      
                        || substring(t_health_family_planing.record_date_time,9,2)  
                        || substring(t_health_family_planing.record_date_time,12,2)  
                        || substring(t_health_family_planing.record_date_time,15,2)  
                        || substring(t_health_family_planing.record_date_time,18,2)     
                when length(t_health_family_planing.record_date_time)=10
                then (to_number(substring(t_health_family_planing.record_date_time,1,4),'9999') - 543)      
                       || substring(t_health_family_planing.record_date_time,6,2)      
                        || substring(t_health_family_planing.record_date_time,9,2)  ||'000000'
		ELSE ''  END AS D_UPDATE --NOT NULL 
     ,t_health_family.patient_name || ' ' || t_health_family.patient_last_name
FROM t_health_family  
	INNER JOIN t_health_home  ON t_health_home.t_health_home_id = t_health_family.t_health_home_id        
	INNER JOIN t_health_village  ON t_health_village.t_health_village_id = t_health_home.t_health_village_id        
	INNER JOIN ( 
		SELECT t_health_family_planing.t_health_family_id AS t_health_family_id
                ,max(t_health_family_planing.health_family_planing_date) AS family_planing_date
			FROM t_health_family_planing WHERE health_family_planing_active = '1'
			GROUP BY t_health_family_planing.t_health_family_id
        ) AS fp1  ON ( fp1.t_health_family_id = t_health_family.t_health_family_id )  
     LEFT JOIN t_health_family_planing on (t_health_family.t_health_family_id = t_health_family_planing.t_health_family_id
            AND fp1.family_planing_date = t_health_family_planing.health_family_planing_date  )
	,b_site			

WHERE  t_health_village.village_moo <> '0'
    AND  t_health_family.f_sex_id  = '2'          
    AND health_family_active = '1'    
	AND t_health_family.f_patient_marriage_status_id = '2'  
    --AND to_number(substr('2552',1,4),'9999') - to_number(substr(t_health_family.patient_birthday,1,4),'9999')  >=15        
	--AND to_number(substr('2552',1,4),'9999') - to_number(substr(t_health_family.patient_birthday,1,4),'9999')  < 45  
    AND to_number(substr(?,1,4),'9999') - to_number(substr(t_health_family.patient_birthday,1,4),'9999')  >=15        
	AND to_number(substr(?,1,4),'9999') - to_number(substr(t_health_family.patient_birthday,1,4),'9999')  < 45   