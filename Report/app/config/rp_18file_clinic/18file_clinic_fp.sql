SELECT  
	b_site.b_visit_office_id  AS PCUCODE  
	,t_health_family.patient_pid AS PID
    ,CASE WHEN (length(t_visit.visit_vn)>8)
            then substr(t_visit.visit_vn, length(t_visit.visit_vn)-7,length(t_visit.visit_vn))
        WHEN (length(t_visit.visit_an)>8 and f_visit_type_id = '1')
            then substr(t_visit.visit_an, length(t_visit.visit_an)-7,length(t_visit.visit_an))
        WHEN (f_visit_type_id = '1')
            then t_visit.visit_an
         else t_visit.visit_vn END AS SEQ 
	, CASE WHEN length(t_visit.visit_begin_visit_time)>=10
            THEN(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        
		|| substring(t_visit.visit_begin_visit_time,6,2)        
		|| substring(t_visit.visit_begin_visit_time,9,2)
            ELSE '' END AS DATE_SERV 
	, CASE  
		WHEN (t_health_family_planing.f_health_family_planing_method_id = '0'        
			OR   t_health_family_planing.f_health_family_planing_method_id = '1'        
			OR   t_health_family_planing.f_health_family_planing_method_id = '2'        
			OR   t_health_family_planing.f_health_family_planing_method_id = '3'       
			OR   t_health_family_planing.f_health_family_planing_method_id = '4'       
			OR   t_health_family_planing.f_health_family_planing_method_id = '5'       
			OR   t_health_family_planing.f_health_family_planing_method_id = '6'       
			OR   t_health_family_planing.f_health_family_planing_method_id = '7' )       
			THEN t_health_family_planing.f_health_family_planing_method_id        
		WHEN (t_health_family_planing.f_health_family_planing_method_id = '8'        
			OR t_health_family_planing.f_health_family_planing_method_id ='9')         
			THEN '8' 
		ELSE ''  END AS FPTYPE 
	, CASE  
		WHEN (b_health_family_planing_group.health_family_planing_group_number IS NOT NULL)       
			THEN b_health_family_planing_group.health_family_planing_group_number        
		ELSE ''  END AS DID
	, to_number(t_health_family_planing.health_famlily_planing_supply_qty,'999') AS AMOUNT 
	, b_site.b_visit_office_id AS FPPLACE 

FROM   t_health_family_planing 
	LEFT JOIN t_health_family ON t_health_family.t_health_family_id = t_health_family_planing.t_health_family_id  
	LEFT JOIN b_health_family_planing_group  ON t_health_family_planing.b_health_family_planing_group_id = b_health_family_planing_group.b_health_family_planing_group_id        
	LEFT JOIN t_visit ON t_visit.t_visit_id = t_health_family_planing.t_visit_id 
	,b_site


where   t_health_family_planing.health_family_planing_active = '1'       
    AND  t_health_family.f_sex_id  = '2'       
    AND t_visit.f_visit_status_id <> '4'   
    AND health_family_active = '1'
    AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
	AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?


