 SELECT 			
	t_health_pregnancy.b_visit_office_id AS PCUCODE 		
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
	, t_health_pregnancy.b_visit_office_id AS APLACE		
	, t_health_pregnancy.health_pregnancy_gravida_number AS GRAVIDA		
	, t_health_anc.f_health_anc_section AS ANCNO  		
	, t_health_anc.health_anc_gravida_week AS  GA		
	, case when t_health_anc.health_anc_exam<>'2'		
		then '1'	
		else '2' end AS ANCRES 	
FROM t_health_anc
	LEFT JOIN t_health_pregnancy ON t_health_anc.t_health_pregnancy_id = t_health_pregnancy.t_health_pregnancy_id
	INNER JOIN t_visit ON t_health_anc.t_visit_id = t_visit.t_visit_id
        INNER JOIN t_patient ON t_patient.t_patient_id = t_visit.t_patient_id
        INNER JOIN t_health_family ON t_patient.t_health_family_id = t_health_family.t_health_family_id

WHERE  t_health_anc.health_anc_active = '1'
    AND  t_visit.f_visit_status_id <> '4'   
    AND health_family_active = '1'
    AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
    AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?		
