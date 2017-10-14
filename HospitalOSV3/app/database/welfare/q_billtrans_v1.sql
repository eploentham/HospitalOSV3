select '131' as spid
    ,'' as field2
    ,visit.begin_visit_time as time_in
    ,site.off_code as hospital_no
    ,visit.vn  as invoice_no
    ,receipt_no as bill_no
    ,visit.hn as hn
    ,'' as field8
    ,total as payer_paid
    ,'0.00' as field10
    ,'' as field11
    ,''  as field12
from billing 
    left join visit on billing.vn = visit.vn
    left join payment on visit.vn = payment.vn
    ,site 
where receipt_date between ? and ? 
    and payment.plan_code = 'PL01'
