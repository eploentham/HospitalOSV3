select '131' as spid
    ,'' as field2
    ,t_visit.visit_begin_visit_time as time_in
    ,b_site.b_visit_office_id as hospital_no
    ,case when t_welfare_billtrans.billtrans_invoice_number is null then
	lpad(t_visit.visit_vn,9,'0') ||0|| replace(substr(t_billing_invoice.t_billing_invoice_date_time,12,19),':','')  
     else 
	t_welfare_billtrans.billtrans_invoice_number
     end as invoice_no 
    ,t_billing_invoice.t_billing_invoice_date_time as bill_no
    ,t_welfare_directdraw_patient.directdraw_patient_hn as hn
    ,'' as field8
    ,t_billing_invoice.billing_invoice_payer_share as payer_paid
    ,'0.00' as field10
    ,'' as field11
    ,''  as field12 
    ,t_visit.visit_begin_visit_time
    ,t_patient.patient_firstname
    ,t_patient.patient_lastname
    ,t_patient.f_patient_prefix_id

    ,t_welfare_billtrans.t_welfare_billtrans_id
    ,t_welfare_billtrans.f_welfare_billtrans_checkcode_type_id
    ,t_welfare_billtrans.billtrans_response_date_time
    ,t_welfare_billtrans.billtrans_response_file_name
    ,t_welfare_billtrans.billtrans_sent_date_time
    ,t_welfare_billtrans.f_welfare_particular_status_type_id
    ,t_welfare_billtrans.billtrans_sent_staff
    ,t_welfare_billtrans.billtrans_response_staff
    ,t_billing_invoice.t_billing_id
    ,t_welfare_billtrans.billtrans_invoice_number
    ,t_welfare_billtrans.billtrans_active
    ,t_welfare_billtrans.billtrans_cancel_date_time
    ,t_welfare_billtrans.billtrans_cancel_staff

from t_welfare_directdraw_patient
    inner join t_billing_invoice on (
        t_billing_invoice.t_patient_id = t_welfare_directdraw_patient.t_patient_id
        and (t_welfare_directdraw_patient.f_welfare_direct_patient_checkcode_type_id = 'A'
                or t_welfare_directdraw_patient.f_welfare_direct_patient_checkcode_type_id = 'U'
                or t_welfare_directdraw_patient.f_welfare_direct_patient_checkcode_type_id = 'V')
        and t_billing_invoice.billing_invoice_active = '1'
        and (substr(t_billing_invoice.t_billing_invoice_date_time,1,11) >= substr(t_welfare_directdraw_patient.directdraw_patient_approve_date_time,1,11)
		or t_welfare_directdraw_patient.directdraw_patient_approve_date_time = '')
        and cast(t_billing_invoice.billing_invoice_payer_share as float) > 0.0
	and t_welfare_directdraw_patient.directdraw_patient_active = '1'
    )
    inner join t_patient on (
        t_patient.t_patient_id = t_welfare_directdraw_patient.t_patient_id
        and t_patient.patient_hn like ?
        and t_patient.patient_firstname like ?
        and t_patient.patient_lastname like ?
        AND t_patient.patient_active = '1'
    )
    inner join t_visit on (
        t_billing_invoice.t_visit_id = t_visit.t_visit_id
        and t_visit.f_visit_type_id = '0'
        and (f_visit_status_id = '2' or f_visit_status_id = '3')
        and t_visit.visit_begin_visit_time between ? and ?
    )
    inner join t_visit_payment on (
        t_billing_invoice.t_payment_id = t_visit_payment.t_visit_payment_id
        and t_visit_payment.visit_payment_priority = '0'
        and t_visit_payment.visit_payment_active = '1'
    )
    inner join b_welfare_direct_draw_map_plan 
        on t_visit_payment.b_contract_plans_id = b_welfare_direct_draw_map_plan.b_contract_plans_id 
    left join b_contract_plans on b_contract_plans.b_contract_plans_id = t_visit_payment.b_contract_plans_id
    left join b_contract_payer on (
        b_contract_payer.b_contract_payer_id = b_contract_plans.b_contract_payer_id
        and b_contract_payer.contract_payer_number = '01')
    left join t_welfare_billtrans on t_billing_invoice.t_billing_id = t_welfare_billtrans.t_billing_id
	and t_welfare_billtrans.billtrans_active = '1'
    ,b_site

 where case when t_welfare_billtrans.f_welfare_particular_status_type_id is null 
        then '1'
        else t_welfare_billtrans.f_welfare_particular_status_type_id
        end like ?
    order by bill_no  