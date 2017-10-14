select 
 CASE WHEN b_item_16_group.b_item_16_group_id='3120000000001'
         THEN '1'
 WHEN b_item_16_group.b_item_16_group_id='3120000000002'
         THEN '2'
 WHEN b_item_16_group.b_item_16_group_id='3120000000003'
         THEN '3'
WHEN b_item_16_group.b_item_16_group_id='3120000000004'
         THEN '4'
 WHEN b_item_16_group.b_item_16_group_id='3120000000005'
         THEN '5'
WHEN b_item_16_group.b_item_16_group_id='3120000000006'
         THEN '6'
WHEN b_item_16_group.b_item_16_group_id='3120000000007'
         THEN '7'
 WHEN b_item_16_group.b_item_16_group_id='3120000000008'
         THEN '8'
WHEN b_item_16_group.b_item_16_group_id='3120000000009'
         THEN '9'
 WHEN b_item_16_group.b_item_16_group_id='3120000000010'
         THEN 'A'
 WHEN b_item_16_group.b_item_16_group_id='3120000000011'
         THEN 'B'
WHEN b_item_16_group.b_item_16_group_id='3120000000012'
         THEN 'C'
 WHEN b_item_16_group.b_item_16_group_id='3120000000013'
         THEN 'D'
WHEN b_item_16_group.b_item_16_group_id='3120000000014'
         THEN 'E'
WHEN b_item_16_group.b_item_16_group_id='3120000000015'
         THEN 'F'
WHEN b_item_16_group.b_item_16_group_id='3120000000016'
         THEN 'G'
ELSE '' END  AS billmaud
,sum(t_billing_invoice_item.billing_invoice_item_payer_share) as amount
,sum(t_billing_invoice_item.billing_invoice_item_patient_share) as paid
,t_billing_invoice.t_visit_id
,b_item.item_common_name
from t_billing_invoice 
inner join t_visit_payment on t_billing_invoice.t_payment_id = t_visit_payment.t_visit_payment_id
inner join b_welfare_direct_draw_map_plan on t_visit_payment.b_contract_plans_id = b_welfare_direct_draw_map_plan.b_contract_plans_id
inner join t_billing_invoice_item on t_billing_invoice.t_billing_invoice_id = t_billing_invoice_item.t_billing_invoice_id
inner join b_item on b_item.b_item_id = t_billing_invoice_item.b_item_id
inner join b_item_16_group on b_item_16_group.b_item_16_group_id = b_item.b_item_16_group_id
where t_billing_invoice.t_visit_id in (+visit+)
group by b_item_16_group.b_item_16_group_id
,t_billing_invoice_item.billing_invoice_item_patient_share
,t_billing_invoice.t_visit_id
,b_item.item_common_name
order by t_billing_invoice.t_visit_id
