SELECT  
	b_site.b_visit_office_id  AS PCUCODE  
	,t_health_family.health_family_hn_hcis AS PID		
	,t_visit.visit_vn AS SEQ 		
	, CASE  
		WHEN(t_health_family_planing.update_record_date_time <> ''        
			AND t_health_family_planing.update_record_date_time <> 'null')        
			THEN   (to_number(substring(t_health_family_planing.update_record_date_time,1,4),'9999')-543)       
				|| substring(t_health_family_planing.update_record_date_time,6,2)         
				|| substring(t_health_family_planing.update_record_date_time,9,2)        
		ELSE ''   END AS DATE_SERV  
	, t_health_family_planing.f_health_family_planing_method_id AS FPTYPE 
	, CASE  
		WHEN (b_health_family_planing_group.health_family_planing_group_number IS NOT NULL)       
			THEN b_health_family_planing_group.health_family_planing_group_number        
		ELSE ''  END AS DID
	, t_health_family_planing.health_famlily_planing_supply_qty AS AMOUNT 
	, b_site.b_visit_office_id AS FPPLACE 

FROM   t_health_family_planing 
	INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_health_family_planing.t_health_family_id  
	LEFT JOIN b_health_family_planing_group  ON t_health_family_planing.b_health_family_planing_group_id = b_health_family_planing_group.b_health_family_planing_group_id        
	LEFT JOIN t_visit ON t_visit.t_visit_id = t_health_family_planing.t_visit_id 
	,b_site


where   t_health_family_planing.health_family_planing_active = '1'   
    AND ( t_health_family_planing.f_health_family_planing_method_id = '1'        
                OR  t_health_family_planing.f_health_family_planing_method_id = '2'       
                OR  t_health_family_planing.f_health_family_planing_method_id = '3'      
                OR  t_health_family_planing.f_health_family_planing_method_id = '4'       
                OR  t_health_family_planing.f_health_family_planing_method_id = '5'     
                OR  t_health_family_planing.f_health_family_planing_method_id = '6'       
                OR  t_health_family_planing.f_health_family_planing_method_id = '7' )      
    AND  t_health_family.f_sex_id  = '2'          
    AND health_family_active = '1'   
	AND substring(t_health_family_planing.update_record_date_time,1,10) >= ?
	AND substring(t_health_family_planing.update_record_date_time,1,10) <= ?


