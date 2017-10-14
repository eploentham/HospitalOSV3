SELECT  
	b_site.b_visit_office_id AS PCUCODE  
	, t_health_family.patient_pid AS PID
    ,CASE  
		WHEN (t_visit.f_visit_type_id = '1'        
			AND length(t_visit.visit_an) = 9)       
			THEN substring(t_visit.visit_an,2,8)       
		WHEN (t_visit.f_visit_type_id = '0'        
			AND length(t_visit.visit_vn) = 9)       
			THEN substring(t_visit.visit_vn,2,8)       
		ELSE ''  END AS SEQ 
	, CASE WHEN length(t_visit.visit_begin_visit_time)>=10
            THEN(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        
		|| substring(t_visit.visit_begin_visit_time,6,2)        
		|| substring(t_visit.visit_begin_visit_time,9,2)
            ELSE '' END AS DATE_SERV 
	, CASE WHEN b_health_epi_group.health_epi_group_description_particular is not null
		THEN b_health_epi_group.health_epi_group_description_particular
		ELSE '' END AS VCCTYPE  
	, b_site.b_visit_office_id AS VCCPLACE  
FROM    t_health_epi 
	LEFT JOIN t_health_epi_detail  ON (t_health_epi.t_health_epi_id = t_health_epi_detail.t_health_epi_id        
            AND health_epi_detail_active = '1')
	LEFT JOIN b_health_epi_group  ON t_health_epi_detail.b_health_epi_set_id = b_health_epi_group.b_health_epi_group_id        
	LEFT JOIN t_visit  ON (t_visit.t_visit_id = t_health_epi_detail.t_visit_id AND t_visit.f_visit_status_id <> '4') 
	LEFT JOIN t_patient on t_patient.t_patient_id = t_visit.t_patient_id
	LEFT JOIN t_health_family  ON t_patient.t_health_family_id = t_health_family.t_health_family_id        
    ,b_site


WHERE  	t_health_epi.health_epi_active = '1'        
    AND t_visit.f_visit_status_id <> '4'   
    AND health_family_active = '1'
    AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
	AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?


