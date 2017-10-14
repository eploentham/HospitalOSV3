 SELECT 			
	  b_site.b_visit_office_id || substring(t_health_anc.t_health_anc_id,9) as ANC_ID 
    , b_site.b_visit_office_id  AS PCUCODE  
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
	, t_health_pregnancy.b_visit_office_id AS APLACE		
	, t_health_pregnancy.health_pregnancy_gravida_number AS GRAVIDA		
	, t_health_anc.f_health_anc_section AS ANCNO  		
	, t_health_anc.health_anc_gravida_week AS  GA		
	, case when t_health_anc.health_anc_exam<>'2'		
            then '1'	
            else '2' end AS ANCRES 	
     , CASE WHEN length(t_health_anc.record_date_time)>=10
            THEN(to_number(substring(t_health_anc.record_date_time,1,4),'9999')-543)        
                    || substring(t_health_anc.record_date_time,6,2)        
                    || substring(t_health_anc.record_date_time,9,2)
            ELSE '' END  as NOTEDATE
    ,t_health_anc.health_anc_active as ACTIVE
    ,b_site.b_visit_office_id || substring(t_visit.t_visit_id,9) as SERVICE_ID
FROM t_health_anc
        INNER JOIN t_health_pregnancy ON t_health_anc.t_health_pregnancy_id = t_health_pregnancy.t_health_pregnancy_id
        INNER JOIN t_visit ON t_health_anc.t_visit_id = t_visit.t_visit_id
        INNER JOIN t_patient ON t_patient.t_patient_id = t_visit.t_patient_id
        INNER JOIN t_health_family ON t_patient.t_health_family_id = t_health_family.t_health_family_id
	,b_site
WHERE  
t_health_anc.health_anc_active = '1'    
AND  t_visit.f_visit_status_id <> '4'   
    AND  substring(t_visit.visit_begin_visit_time,1,10 )  >= ?
    AND  substring(t_visit.visit_begin_visit_time,1,10 )  <= ?
