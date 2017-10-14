SELECT  distinct
	b_site.b_visit_office_id AS PCUCODE 
	, t_health_family.health_family_hn_hcis AS PID
        ,t_visit.visit_vn  AS SEQ 
	, CASE WHEN length(t_visit.visit_begin_visit_time)>=10
            THEN(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        
		|| substring(t_visit.visit_begin_visit_time,6,2)        
		|| substring(t_visit.visit_begin_visit_time,9,2)
            ELSE '' END AS DATE_SERV 
	, CASE WHEN length(b_item.item_number)>6
            THEN ''
            ELSE b_item.item_number
            END AS DID
	, t_order.order_qty AS AMOUNT 
        ,t_order.order_price AS DRUGPRIC 
        ,CASE  WHEN   (t_order.order_cost <> ''  AND t_order.order_cost <> 'null')   
                THEN t_order.order_cost 
                ELSE '0.00' END AS DRUGCOST
,t_order.order_common_name as DNAME
	, CASE WHEN (b_nhso_map_drug.f_nhso_drug_id is not null and length(b_nhso_map_drug.f_nhso_drug_id)>23)
            THEN substring(b_nhso_map_drug.f_nhso_drug_id,1,24)
        ELSE '' END AS DRUG_CODE_STD
        ,b_item_drug_uom.item_drug_uom_description as unit
        ,'' as unit_packing
        ,CASE when length(order_date_time)>=19
           then (to_number(substring(order_date_time,1,4),'9999')-543)
            ||substring(order_date_time,6,2)
            ||substring(order_date_time,9,2)
            ||substring(order_date_time,12,2)
            ||substring(order_date_time,15,2)
            ||substring(order_date_time,18,2)
        else '' end as d_update

FROM t_visit
    INNER JOIN  t_order   ON (t_order.f_order_status_id <> '3'
        AND t_order.f_order_status_id <> '0'
        AND (t_order.f_item_group_id = '1'
        OR t_order.f_item_group_id = '4')
        AND t_visit.t_visit_id = t_order.t_visit_id )      
    INNER JOIN b_item ON b_item.b_item_id = t_order.b_item_id
    INNER JOIN t_patient ON (t_visit.t_patient_id = t_patient.t_patient_id)
    inner JOIN t_health_family ON (t_health_family.t_health_family_id = t_patient.t_health_family_id)
    inner join t_order_drug on t_order.t_order_id = t_order_drug.t_order_id
    inner join b_item_drug_uom on b_item_drug_uom.b_item_drug_uom_id = t_order_drug.b_item_drug_uom_id_purch
    LEFT JOIN b_nhso_map_drug ON b_nhso_map_drug.b_item_id = t_order.b_item_id
	,b_site

where   t_order.f_order_status_id <>'3' 
    AND t_visit.f_visit_status_id <> '4'   
    AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
    AND substring(t_visit.visit_begin_visit_time,1,10) <= ?


