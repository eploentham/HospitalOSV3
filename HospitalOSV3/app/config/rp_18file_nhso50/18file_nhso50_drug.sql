SELECT  
	b_site.b_visit_office_id AS PCUCODE 
	, t_health_family.health_family_hn_hcis AS PID		
	,t_visit.visit_vn AS SEQ 		
	, CASE   
		WHEN  (t_visit.visit_begin_visit_time <> ''        
			AND t_visit.visit_begin_visit_time <> 'null')        
			THEN  (to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        
				||substring(t_visit.visit_begin_visit_time,6,2)        
				|| substring(t_visit.visit_begin_visit_time,9,2)        
		ELSE ''  END AS DATE_SERV 
	, b_item.item_number AS DID 
	, t_order.order_qty AS AMOUNT 
	, ceil(t_order.order_price * t_order.order_qty) AS DRUGPRIC 
	, CASE  
		WHEN   (t_order.order_cost <> ''       
			AND t_order.order_cost <> 'null')       
			THEN    (to_number(t_order.order_cost,'99999999.99') * t_order.order_qty)       
		ELSE '0'   END AS DRUGCOST 
		
FROM t_visit
	INNER JOIN t_patient ON (t_visit.t_patient_id = t_patient.t_patient_id)       
	INNER JOIN t_health_family ON (t_health_family.t_health_family_id = t_patient.t_health_family_id)
    INNER JOIN  t_order   ON (t_visit.t_visit_id = t_order.t_visit_id )      
    INNER JOIN b_item ON b_item.b_item_id = t_order.b_item_id
	,b_site

where  t_order.f_order_status_id <> '3'
        AND (t_order.f_item_group_id = '1'
        OR t_order.f_item_group_id = '4')
        AND f_order_status_id<>'3'       
    AND health_family_active = '1'
    AND  substring(t_visit.visit_begin_visit_time,1,10) >= ?           
	AND substring(t_visit.visit_begin_visit_time,1,10) <= ? 


