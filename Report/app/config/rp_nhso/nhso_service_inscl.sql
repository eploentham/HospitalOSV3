select 
        b_site.b_visit_office_id as pcucode,
        t_nhso_patient_payment_pid as pid,
	CASE WHEN length(t_visit.visit_begin_visit_time)>=10
            THEN(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        
		|| substring(t_visit.visit_begin_visit_time,6,2)        
		|| substring(t_visit.visit_begin_visit_time,9,2)
            ELSE '' END AS DATE_SERV ,
        t_nhso_patient_payment_maininscl as maininscl,
        t_nhso_patient_payment_subinscl as subinscl,
        t_nhso_patient_payment_hmain as hmain,
        t_nhso_patient_payment_hsub as hsub,
        case when length(t_nhso_patient_payment_paidmodel) <> 1 then ''
            else t_nhso_patient_payment_paidmodel end as paid_model,
        t_nhso_patient_payment_purchaseprovince as purchaseprovince
from t_nhso_visit_payment 
    inner join t_visit on t_visit.t_visit_id = t_nhso_visit_payment.t_visit_id
    ,b_site
where substring(t_visit.visit_begin_visit_time,1,10) >= ?
    AND substring(t_visit.visit_begin_visit_time,1,10) <= ?
