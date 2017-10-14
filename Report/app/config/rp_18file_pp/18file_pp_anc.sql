 SELECT 				
	t_health_pregnancy.b_visit_office_id AS PCUCODE 			
	,t_health_family.patient_pid AS CID			
	,t_visit.visit_vn AS SEQ 	
	, CASE WHEN (length(t_health_anc.modify_date_time)>=10)
        then to_char(to_date(to_number(
        substr(t_health_anc.modify_date_time,1,4),'9999')-543 || 
        substr(t_health_anc.modify_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END AS DATE_SERV 		
	, t_health_pregnancy.b_visit_office_id AS APLACE			
	, t_health_pregnancy.health_pregnancy_gravida_number AS GRAVIDA			
	, t_health_anc.f_health_anc_section AS ANCNO  			
	, t_health_anc.health_anc_gravida_week AS  GA			
	, case when t_health_anc.health_anc_exam<>'2'			
		then '1'		
		else '2' end AS ANCRES 		
	, t_health_anc.health_anc_notice AS NOTICE

FROM 	t_health_anc			
	LEFT JOIN t_health_pregnancy  ON t_health_anc.t_health_pregnancy_id = t_health_pregnancy.t_health_pregnancy_id      			
	INNER JOIN t_health_family ON t_health_anc.t_health_family_id = t_health_family.t_health_family_id      			
	LEFT JOIN t_visit ON t_health_anc.t_visit_id = t_visit.t_visit_id 			

WHERE  t_health_anc.health_anc_active = '1'        
    AND health_family_active = '1'    				
    AND substring(t_health_anc.modify_date_time,1,10) >= ?			
    AND substring(t_health_anc.modify_date_time,1,10) <= ?				




