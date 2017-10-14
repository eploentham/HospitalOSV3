SELECT 
	b_site.b_visit_office_id AS PCUCODE 
	,t_health_family.health_family_hn_hcis AS PID		
	,t_visit.visit_vn AS SEQ 		
	, to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543       
				|| substring(t_visit.visit_begin_visit_time,6,2)       
				|| substring(t_visit.visit_begin_visit_time,9,2) AS DATE_SERV 
	, substring(t_diag_icd9.diag_icd9_icd9_number,1,2)        
		|| substring(t_diag_icd9.diag_icd9_icd9_number,4,2) AS PROCED 
	, (Q.order_price * Q.order_qty) AS SERVPRIC
		
FROM b_site,t_diag_icd9  
	INNER JOIN t_visit  ON (t_visit.t_visit_id = t_diag_icd9.diag_icd9_vn)
	INNER JOIN t_patient  ON (t_visit.t_patient_id = t_patient.t_patient_id )
	INNER JOIN t_health_family  ON t_health_family.t_health_family_id = t_patient.t_health_family_id 
    LEFT JOIN (
        select b_item_service.icd9_number,t_order.order_qty,t_order.order_price,t_visit_id 
        from t_order
            inner join b_item_service on b_item_service.b_item_id = t_order.b_item_id
        where icd9_number <> '' and order_price <> '0'
            and t_order.f_item_group_id = '5'
             and t_order.f_order_status_id <> '3'
            AND t_order.order_date_time >= ?
            AND t_order.order_date_time <= ?
        group by b_item_service.icd9_number,t_order.order_qty,t_order.order_price,t_visit_id 
        ) as Q on (Q.icd9_number = t_diag_icd9.diag_icd9_icd9_number and Q.t_visit_id = t_visit.t_visit_id)


WHERE t_visit.f_visit_status_id <> '4'      
    AND health_family_active = '1'
    AND t_diag_icd9.diag_icd9_active = '1'
    AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
    AND substring(t_visit.visit_begin_visit_time,1,10) <= ?

GROUP By  PCUCODE,PID,SEQ,DATE_SERV,PROCED, Q.order_price , Q.order_qty


