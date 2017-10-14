--ANC_53
 SELECT 				
	b_site.b_visit_office_id AS PCUCODE 	--NOT NULL			
    ,t_health_family.health_family_hn_hcis as PID --NOT NULL	
	,t_visit.visit_vn AS SEQ 		--NOT NULL		
	, CASE WHEN length(t_health_anc.modify_date_time)>9
			THEN   (to_number(substring(t_health_anc.modify_date_time,1,4),'9999')-543)        	
				|| substring(t_health_anc.modify_date_time,6,2)       
				|| substring(t_health_anc.modify_date_time,9,2)       
			ELSE ''  END  AS DATE_SERV  	--NOT NULL	
	, t_health_pregnancy.b_visit_office_id AS APLACE			
	, t_health_pregnancy.health_pregnancy_gravida_number AS GRAVIDA	
    , t_health_anc.f_health_anc_section AS ANCNO 
	, t_health_anc.health_anc_gravida_week AS  GA			
	, case when t_health_anc.health_anc_exam<>'2'			
		then '1'		
		else '2' end AS ANCRES 	
	  ,CASE when length(t_health_anc.modify_date_time) = 19
                then (to_number(substring(t_health_anc.modify_date_time,1,4),'9999') - 543)      
                       || substring(t_health_anc.modify_date_time,6,2)      
                        || substring(t_health_anc.modify_date_time,9,2)  
                        || substring(t_health_anc.modify_date_time,12,2)  
                        || substring(t_health_anc.modify_date_time,15,2)  
                        || substring(t_health_anc.modify_date_time,18,2)   
		ELSE ''  END AS D_UPDATE  --NOT NULL	
FROM t_health_anc 			
	INNER JOIN t_health_family ON t_health_anc.t_health_family_id = t_health_family.t_health_family_id      			
	LEFT JOIN t_visit ON t_health_anc.t_visit_id = t_visit.t_visit_id 			
    LEFT JOIN t_health_pregnancy ON t_health_anc.t_health_pregnancy_id = t_health_pregnancy.t_health_pregnancy_id      			
	,b_site
WHERE  t_health_anc.health_anc_active = '1'         
    AND health_family_active = '1'   				
	AND substring(t_health_anc.modify_date_time,1,10) >= ?			
    AND substring(t_health_anc.modify_date_time,1,10) <= ?				
    --AND substring(t_health_anc.modify_date_time,1,10) >= '2552-01-01'			
    --AND substring(t_health_anc.modify_date_time,1,10) <= '2552-09-31'
