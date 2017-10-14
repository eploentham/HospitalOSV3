select '131' as spid
    ,'' as field2
    ,t_visit.visit_begin_visit_time as time_in
    ,b_site.b_visit_office_id as hospital_no
    ,t_visit.visit_vn  as invoice_no
    ,t_billing_invoice.t_billing_invoice_date_time as bill_no
    ,t_patient.patient_hn as hn
    ,'' as field8
    ,t_billing_invoice.billing_invoice_payer_share as payer_paid
    ,'0.00' as field10
    ,'' as field11
    ,''  as field12
from t_billing_invoice 
    left join t_visit on t_billing_invoice.t_visit_id = t_visit.t_visit_id
    left join t_patient on t_patient.t_patient_id = t_visit.t_patient_id
    left join t_visit_payment on (t_visit.t_visit_id = t_visit_payment.t_visit_id
        and t_visit_payment.visit_payment_priority = '0'
        and t_visit_payment.visit_payment_active = '1')
    left join b_contract_plans on b_contract_plans.b_contract_plans_id = t_visit_payment.b_contract_plans_id
    left join b_contract_payer on (
        b_contract_payer.b_contract_payer_id = b_contract_plans.b_contract_payer_id
        and b_contract_payer.contract_payer_number = '01')
    ,b_site 
where t_billing_invoice.t_billing_invoice_date_time > ? 
    and t_billing_invoice.t_billing_invoice_date_time < ?
    order by bill_no