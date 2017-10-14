
(SELECT 
	t_patient.patient_hn AS HN  
	, '' AS AN 
	, CASE WHEN (length(t_visit.visit_begin_visit_time)>=10)
            then to_char(to_date(to_number(
            substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 ||
            substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')
                    ELSE ''   END AS  DATE 
	--, b_item_16_group.item_16_group_number  AS CHRGITEM
        ,case when b_item.r_rp1253_charitem_id is not null and billing_invoice_item_draw = '1'
                then b_item.r_rp1253_charitem_id || '1'
                when b_item.r_rp1253_charitem_id is not null
                then b_item.r_rp1253_charitem_id || '2'
                else ''
         end AS CHRGITEM
	,sum(t_billing_invoice_item.billing_invoice_item_total) as AMOUNT  
	,t_patient.patient_pid AS PERSON_ID
	,t_visit.visit_vn AS SEQ
    --,billing_invoice_item_draw as DRAW

FROM  t_billing_invoice_item  
	INNER JOIN t_visit ON (t_billing_invoice_item.t_visit_id = t_visit.t_visit_id)  
	INNER JOIN t_patient ON (t_patient.t_patient_id = t_visit.t_patient_id)  
	inner JOIN t_order ON (t_order.t_order_id = t_billing_invoice_item.t_order_item_id)
        inner join b_item ON b_item.b_item_id = t_order.b_item_id
    --inner join b_item_16_group on t_order.b_item_16_group_id = b_item_16_group.b_item_16_group_id

where t_visit.f_visit_status_id <> '4' 
	AND t_billing_invoice_item.billing_invoice_item_active = '1' 
    AND f_visit_type_id = '0'
	AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
	AND substring(t_visit.visit_begin_visit_time,1,10) <= ?
    and t_visit.f_visit_type_id <> 'S'

group by 
	HN  
	, AN  
	, DATE  
	, CHRGITEM  
	, PERSON_ID
	, SEQ
 --   ,DRAW
ORDER BY HN)

UNION

(SELECT 
	t_patient.patient_hn AS HN  
	, t_visit.visit_vn AS AN 
	, CASE WHEN (length(t_visit.visit_begin_visit_time)>=10)
then to_char(to_date(to_number(
substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END AS  DATE 
	--, b_item_16_group.item_16_group_number  AS CHRGITEM
        ,case when b_item.r_rp1253_charitem_id is not null and billing_invoice_item_draw = '1'
                then b_item.r_rp1253_charitem_id || '1'
                when b_item.r_rp1253_charitem_id is not null
                then b_item.r_rp1253_charitem_id || '2'
                else ''
         end AS CHRGITEM
	,sum(t_billing_invoice_item.billing_invoice_item_total) as AMOUNT  
	,t_patient.patient_pid AS PERSON_ID
	,t_visit.visit_vn AS SEQ
   -- ,billing_invoice_item_draw as DRAW

FROM  t_billing_invoice_item  
	INNER JOIN t_visit ON (t_billing_invoice_item.t_visit_id = t_visit.t_visit_id)  
	INNER JOIN t_patient ON (t_patient.t_patient_id = t_visit.t_patient_id)  
	inner JOIN t_order ON (t_order.t_order_id = t_billing_invoice_item.t_order_item_id)
        inner join b_item ON b_item.b_item_id = t_order.b_item_id
    --inner join b_item_16_group on t_order.b_item_16_group_id = b_item_16_group.b_item_16_group_id

where t_visit.f_visit_status_id <> '4' 
	AND t_billing_invoice_item.billing_invoice_item_active = '1' 
    AND f_visit_type_id = '1'
       AND substring(t_visit.visit_staff_doctor_discharge_date_time,1,10) >= ?
       AND substring(t_visit.visit_staff_doctor_discharge_date_time,1,10) <= ?

group by 
	HN  
	, AN  
	,  DATE  
	, CHRGITEM  
	,PERSON_ID 
	,SEQ
    --,DRAW
ORDER BY HN)
