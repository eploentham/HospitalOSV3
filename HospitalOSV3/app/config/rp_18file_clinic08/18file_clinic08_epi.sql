SELECT  
	  b_site.b_visit_office_id || substr(t_health_epi.t_health_epi_id,9) as EPI_ID
    , b_site.b_visit_office_id AS PCUCODE  
	, case when  (t_health_family.patient_pid IS NULL or t_health_family.patient_pid = '')
                then t_patient.patient_pid 
                else t_health_family.patient_pid
       end  as CID
        ,t_visit.visit_vn  AS SEQ 
	, CASE WHEN length(t_visit.visit_begin_visit_time)>=10
            THEN(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        
                    || substring(t_visit.visit_begin_visit_time,6,2)        
                    || substring(t_visit.visit_begin_visit_time,9,2)
            ELSE '' END AS DATE_SERV 
	, CASE WHEN b_health_epi_group.health_epi_group_description_particular is not null
		THEN b_health_epi_group.health_epi_group_description_particular
		ELSE '' END AS VCCTYPE  
	, t_health_epi.b_visit_office_id AS VCCPLACE  
    , CASE WHEN length(t_health_epi.record_date_time)>=10
            THEN(to_number(substring(t_health_epi.record_date_time,1,4),'9999')-543)        
                    || substring(t_health_epi.record_date_time,6,2)        
                    || substring(t_health_epi.record_date_time,9,2)
            ELSE '' END as NOTEDATE
        ,t_health_epi.health_epi_active as ACTIVE
        ,b_site.b_visit_office_id || substring(t_visit.t_visit_id,9) as SERVICE_ID
FROM    t_health_epi 
	INNER JOIN t_health_epi_detail  ON (t_health_epi.t_health_epi_id = t_health_epi_detail.t_health_epi_id 
                AND health_epi_detail_active = '1')
    INNER JOIN t_visit  ON (t_visit.t_visit_id = t_health_epi_detail.t_visit_id) 
    INNER JOIN t_patient ON t_patient.t_patient_id = t_visit.t_patient_id
	INNER JOIN b_health_epi_group  ON t_health_epi_detail.b_health_epi_set_id = b_health_epi_group.b_health_epi_group_id        
	INNER JOIN t_health_family  ON t_health_epi_detail.t_health_family_id = t_health_family.t_health_family_id        
    ,b_site
WHERE  	
t_health_epi.health_epi_active = '1'  AND t_visit.f_visit_status_id <> '4'   
    AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
	AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?


