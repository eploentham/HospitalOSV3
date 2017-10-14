select 
		      t_billing_invoice.t_billing_id as billing_id_opb
		      ,b_item_16_group.item_16_group_number as billmaud
		      ,sum(t_billing_invoice_item.billing_invoice_item_payer_share) as amount
		      ,sum(t_billing_invoice_item.billing_invoice_item_patient_share) as paid
		      ,t_billing_invoice.t_visit_id
		      ,b_item.item_common_name
		      from t_billing_invoice 
		      inner join t_visit_payment on t_billing_invoice.t_payment_id = t_visit_payment.t_visit_payment_id
		      inner join b_welfare_direct_draw_map_plan on t_visit_payment.b_contract_plans_id = b_welfare_direct_draw_map_plan.b_contract_plans_id
		      inner join t_billing_invoice_item on t_billing_invoice.t_billing_invoice_id = t_billing_invoice_item.t_billing_invoice_id
		      inner join b_item on b_item.b_item_id = t_billing_invoice_item.b_item_id
		      left join b_item_16_group on b_item_16_group.b_item_16_group_id = b_item.b_item_16_group_id
		      where t_billing_invoice.t_visit_id in (?)
                      group by 
                      t_billing_invoice.t_billing_id
                      ,b_item_16_group.item_16_group_number
                      ,t_billing_invoice_item.billing_invoice_item_payer_share
                      ,t_billing_invoice_item.billing_invoice_item_patient_share
                      ,t_billing_invoice.t_visit_id
                      ,b_item.item_common_name
                      order by t_billing_invoice.t_visit_id