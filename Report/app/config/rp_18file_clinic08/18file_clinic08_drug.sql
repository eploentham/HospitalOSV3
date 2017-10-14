SELECT 
	b_site.b_visit_office_id AS PCUCODE 
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
	, CASE WHEN (b_nhso_map_drug.f_nhso_drug_id is not null and length(b_nhso_map_drug.f_nhso_drug_id)>23)
            THEN b_nhso_map_drug.f_nhso_drug_id
        ELSE '' END AS DID 
        ,b_item_drug_uom.item_drug_uom_description AS UNIT -- เพิ่มตาม datadict
        ,'' AS UNIT_PACKING -- เพิ่มตาม datadict(ยังไม่เก็บใน hos)
	, t_order.order_qty AS AMOUNT 
        ,t_order.order_price AS DRUGPRIC 
        ,CASE  WHEN   (t_order.order_cost <> ''  AND t_order.order_cost <> 'null')   
                THEN t_order.order_cost 
                ELSE '0.00' END AS DRUGCOST
        ,b_site.b_visit_office_id || substr(t_order.t_order_id,9) as id
        , CASE WHEN length(t_order.order_date_time)>=10
            THEN(to_number(substring(t_order.order_date_time,1,4),'9999')-543)        
		|| substring(t_order.order_date_time,6,2)        
		|| substring(t_order.order_date_time,9,2)
            ELSE '' END AS notedate 
        ,'1' as active
        ,b_site.b_visit_office_id || substr(t_visit.t_visit_id,9) as SERVICE_ID
FROM t_visit
    INNER JOIN  t_order   ON (t_order.f_order_status_id <> '3'
        AND t_order.f_order_status_id <> '0'
        AND (t_order.f_item_group_id = '1'
        OR t_order.f_item_group_id = '4')
        AND t_visit.t_visit_id = t_order.t_visit_id )   
    LEFT JOIN t_order_drug ON t_order.t_order_id = t_order_drug.t_order_id
    LEFT JOIN b_item_drug_uom ON t_order_drug.b_item_drug_uom_id_purch = b_item_drug_uom.b_item_drug_uom_id
    INNER JOIN b_item ON b_item.b_item_id = t_order.b_item_id
	INNER JOIN t_patient ON (t_visit.t_patient_id = t_patient.t_patient_id)       
	INNER JOIN t_health_family ON (t_health_family.t_health_family_id = t_patient.t_health_family_id)
    LEFT JOIN b_nhso_map_drug ON b_nhso_map_drug.b_item_id = t_order.b_item_id
	,b_site
where   t_order.f_order_status_id <>'3' 
    AND t_visit.f_visit_status_id <> '4'   
    AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
    AND substring(t_visit.visit_begin_visit_time,1,10) <= ?

GROUP BY PCUCODE ,CID,SEQ ,DATE_SERV ,DID,UNIT,UNIT_PACKING,AMOUNT,DRUGPRIC,DRUGCOST,id,notedate,active,SERVICE_ID


