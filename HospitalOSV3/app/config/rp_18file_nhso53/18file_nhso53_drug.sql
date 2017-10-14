select
q1.PCUCODE
,q1.PID
,q1.SEQ
,q1.DATE_SERV
,q1.DID
,sum(q1.AMOUNT)
,q1.DRUGPRIC
,q1.DRUGCOST
,q1.DNAME
,q1.DIDSTD
,q1.UNIT
,q1.UNIT_PACKING
,q1.D_UPDATE

from

(SELECT  distinct
	b_site.b_visit_office_id AS PCUCODE --NOT NULL
    ,t_health_family.health_family_hn_hcis as PID --NOT NULL
    ,t_visit.visit_vn  AS SEQ --NOT NULL
	, CASE WHEN length(t_visit.visit_begin_visit_time)>=10
            THEN(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        
                || substring(t_visit.visit_begin_visit_time,6,2)        
                || substring(t_visit.visit_begin_visit_time,9,2)
            ELSE '' END AS DATE_SERV --NOT NULL
	, b_item.item_number AS DID --NOT NULL
	, t_order.order_qty AS AMOUNT 
    , t_order.order_price AS DRUGPRIC 
    , t_order.order_cost AS DRUGCOST  
    , t_order.order_common_name as DNAME
    , substr(b_nhso_map_drug.f_nhso_drug_id,1,24)  AS DIDSTD 
    , b_item_drug_uom.item_drug_uom_description as UNIT
    , case when (item_unit_packing_qty = '' or item_unit_packing_qty is null) then 'NULL' else item_unit_packing_qty end as UNIT_PACKING   
   , case when length(t_visit.visit_begin_visit_time) >= 10
                then case when length(cast(substring(t_visit.visit_begin_visit_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit.visit_begin_visit_time,5),'-',''),',',''),':','')) = 14
                                  then (cast(substring(t_visit.visit_begin_visit_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit.visit_begin_visit_time,5),'-',''),',',''),':',''))
                                  when length(cast(substring(t_visit.visit_begin_visit_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit.visit_begin_visit_time,5),'-',''),',',''),':','')) =12
                                  then (cast(substring(t_visit.visit_begin_visit_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit.visit_begin_visit_time,5),'-',''),',',''),':','')) || '00'
                                  when length(cast(substring(t_visit.visit_begin_visit_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit.visit_begin_visit_time,5),'-',''),',',''),':','')) =10
                                  then (cast(substring(t_visit.visit_begin_visit_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit.visit_begin_visit_time,5),'-',''),',',''),':','')) || '0000'
                                  when length(cast(substring(t_visit.visit_begin_visit_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit.visit_begin_visit_time,5),'-',''),',',''),':','')) = 8
                                  then (cast(substring(t_visit.visit_begin_visit_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit.visit_begin_visit_time,5),'-',''),',',''),':','')) || '000000'
                                  else ''
                           end
                else ''
       end as D_UPDATE --NOT NULL
FROM t_visit
	INNER JOIN t_patient ON (t_visit.t_patient_id = t_patient.t_patient_id)       
	INNER JOIN t_health_family ON (t_health_family.t_health_family_id = t_patient.t_health_family_id) 
    INNER JOIN  t_order   ON (t_order.f_order_status_id <> '3'
        AND t_order.f_order_status_id <> '0'
        AND (t_order.f_item_group_id = '1'
        OR t_order.f_item_group_id = '4')
        AND t_visit.t_visit_id = t_order.t_visit_id )
    INNER JOIN b_item ON b_item.b_item_id = t_order.b_item_id 
    LEFT JOIN b_nhso_map_drug on t_order.b_item_id = b_nhso_map_drug.b_item_id
    LEFT JOIN t_order_drug on t_order.t_order_id=t_order_drug.t_order_id 
    LEFT JOIN b_item_drug_uom on t_order_drug.b_item_drug_uom_id_purch=b_item_drug_uom.b_item_drug_uom_id
	,b_site

where   t_order.f_order_status_id <>'3' 
    AND t_visit.f_visit_status_id <> '4'  
    AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
    AND substring(t_visit.visit_begin_visit_time,1,10) <= ? ) as q1

group by
q1.PCUCODE
,q1.PID
,q1.SEQ
,q1.DATE_SERV
,q1.did
,q1.DRUGPRIC
,q1.DRUGCOST
,q1.DNAME
,q1.DIDSTD
,q1.UNIT
,q1.UNIT_PACKING
,q1.D_UPDATE