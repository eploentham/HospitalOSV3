SELECT  
	b_site.b_visit_office_id AS PCUCODE  
	, t_health_family.patient_pid AS CID		
	,t_visit.visit_vn AS SEQ 		
	, (to_number(substring(t_health_epi.modify_date_time,1,5),'9999')-543)        
		|| substring(t_health_epi.modify_date_time,6,2)        
		|| substring(t_health_epi.modify_date_time,9,2) AS DATE_SERV 
	, CASE WHEN b_health_epi_group.health_epi_group_description_particular is not null
		THEN b_health_epi_group.health_epi_group_description_particular
		ELSE '' END AS VCCTYPE  
	, b_site.b_visit_office_id AS VCCPLACE  
FROM    t_health_epi 
	LEFT JOIN t_health_epi_detail  ON (t_health_epi.t_health_epi_id = t_health_epi_detail.t_health_epi_id        
            AND health_epi_detail_active = '1')
	LEFT JOIN b_health_epi_group  ON t_health_epi_detail.b_health_epi_set_id = b_health_epi_group.b_health_epi_group_id        
	INNER JOIN t_health_family  ON t_health_epi_detail.t_health_family_id = t_health_family.t_health_family_id        
	LEFT JOIN t_visit  ON (t_visit.t_visit_id = t_health_epi_detail.t_visit_id AND t_visit.f_visit_status_id <> '4') 
    ,b_site


WHERE  	t_health_epi.health_epi_active = '1'       
    AND health_family_active = '1'       
	AND substring(t_health_epi.modify_date_time,1,10) >= ?
	AND substring(t_health_epi.modify_date_time,1,10) <= ?


