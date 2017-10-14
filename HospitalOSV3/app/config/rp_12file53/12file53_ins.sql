(SELECT 
	t_patient.patient_hn AS HN  
	, b_contract_plans.contract_plans_pttype AS INSCL  
	, b_contract_plans.r_rp1853_instype_id AS SUBTYPE  
	, t_patient_payment.patient_payment_card_number AS CID
	, CASE WHEN (length(t_patient_payment.patient_payment_card_issue_date)>=10)
then to_char(to_date(to_number(
substr(t_patient_payment.patient_payment_card_issue_date,1,4),'9999')-543 || 
substr(t_patient_payment.patient_payment_card_issue_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END AS DATEIN  
	, CASE WHEN (length(t_patient_payment.patient_payment_card_expire_date)>=10)
then to_char(to_date(to_number(
substr(t_patient_payment.patient_payment_card_expire_date,1,4),'9999')-543 || 
substr(t_patient_payment.patient_payment_card_expire_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END AS DATEEXP 
	, t_patient_payment.patient_payment_main_hospital AS HOSPMAIN
	,t_patient_payment.patient_payment_sub_hospital AS HOSPSUB 

 FROM 	t_patient
	INNER JOIN t_visit ON (t_patient.t_patient_id = t_visit.t_patient_id) 
	LEFT JOIN t_patient_payment ON (t_patient.t_health_family_id = t_patient_payment.t_health_family_id)
	LEFT JOIN b_contract_plans ON (t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id) 
 
 WHERE t_visit.f_visit_status_id <> '4'  
	AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
	AND substring(t_visit.visit_begin_visit_time,1,10) <= ?
    and t_visit.f_visit_type_id <> 'S'
group by
	 HN  
	, INSCL  
	, SUBTYPE  
	, CID  
	, DATEIN  
	, DATEEXP 
	, HOSPMAIN
	, HOSPSUB 

ORDER BY HN)
--case OPD
UNION
--case IPD
(SELECT 
	t_patient.patient_hn AS HN  
	, b_contract_plans.contract_plans_pttype AS INSCL  
	, b_contract_plans.r_rp1853_instype_id AS SUBTYPE  
	, t_patient_payment.patient_payment_card_number AS CID  
	, CASE WHEN (length(t_patient_payment.patient_payment_card_issue_date)>=10)
then to_char(to_date(to_number(
substr(t_patient_payment.patient_payment_card_issue_date,1,4),'9999')-543 || 
substr(t_patient_payment.patient_payment_card_issue_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END AS DATEIN  
	, CASE WHEN (length(t_patient_payment.patient_payment_card_expire_date)>=10)
then to_char(to_date(to_number(
substr(t_patient_payment.patient_payment_card_expire_date,1,4),'9999')-543 || 
substr(t_patient_payment.patient_payment_card_expire_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END AS DATEEXP 
	, t_patient_payment.patient_payment_main_hospital AS HOSPMAIN
	,t_patient_payment.patient_payment_sub_hospital AS HOSPSUB 

 FROM 	t_patient
	INNER JOIN t_visit ON (t_patient.t_patient_id = t_visit.t_patient_id) 
	LEFT JOIN t_patient_payment ON (t_patient.t_health_family_id = t_patient_payment.t_health_family_id)
	LEFT JOIN b_contract_plans ON (t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id) 
 
 WHERE t_visit.f_visit_status_id <> '4'  
    AND t_visit.f_visit_type_id = '1'
       AND substring(t_visit.visit_staff_doctor_discharge_date_time,1,10) >= ?
       AND substring(t_visit.visit_staff_doctor_discharge_date_time,1,10) <= ?
    and t_visit.f_visit_type_id <> 'S'
group by
	 HN  
	, INSCL  
	, SUBTYPE  
	, CID  
	, DATEIN  
	, DATEEXP 
	, HOSPMAIN
	, HOSPSUB 

ORDER BY HN)
