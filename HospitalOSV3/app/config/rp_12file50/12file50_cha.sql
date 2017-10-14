/*ทดสอบภาษาไทย*/
(SELECT 
	t_patient.patient_hn AS HN  
	, '' AS AN 
	, CASE WHEN (length(t_visit.visit_begin_visit_time)>=10)
then to_char(to_date(to_number(
substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END AS  DATE 
	, CASE 
		WHEN (b_item_billing_subgroup.f_item_billing_group_id = '00' 
			OR  b_item_billing_subgroup.f_item_billing_group_id = '01' 
			OR  b_item_billing_subgroup.f_item_billing_group_id = '02' 
			OR  b_item_billing_subgroup.f_item_billing_group_id = '03' 
			OR  b_item_billing_subgroup.f_item_billing_group_id = '04' 
			OR  b_item_billing_subgroup.f_item_billing_group_id = '05' 
			OR  b_item_billing_subgroup.f_item_billing_group_id = '06' 
			OR  b_item_billing_subgroup.f_item_billing_group_id = '07' 
			OR  b_item_billing_subgroup.f_item_billing_group_id = '08')  
			THEN b_item_billing_subgroup.f_item_billing_group_id   
		ELSE '09'  END AS CHRGITEM  
	,sum(t_billing_invoice_billing_subgroup.billing_invoice_billing_subgroup_total) as AMOUNT  
	,t_patient.patient_pid AS PERSON_ID

FROM  t_billing_invoice_billing_subgroup  
	INNER JOIN t_visit ON (t_billing_invoice_billing_subgroup.t_visit_id = t_visit.t_visit_id)  
	INNER JOIN t_patient ON (t_patient.t_patient_id = t_visit.t_patient_id)  
	LEFT JOIN b_item_billing_subgroup  
		ON (b_item_billing_subgroup.b_item_billing_subgroup_id = t_billing_invoice_billing_subgroup.b_item_billing_subgroup_id)  

where t_visit.f_visit_status_id <> '4' 
	AND t_billing_invoice_billing_subgroup.billing_invoice_billing_subgroup_active = '1' 
    AND f_visit_type_id = '0'
	AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
	AND substring(t_visit.visit_begin_visit_time,1,10) <= ?

group by 
	HN  
	, AN  
	,  DATE  
	, CHRGITEM  
	,PERSON_ID
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
	, CASE 
		WHEN (b_item_billing_subgroup.f_item_billing_group_id = '00' 
			OR  b_item_billing_subgroup.f_item_billing_group_id = '01' 
			OR  b_item_billing_subgroup.f_item_billing_group_id = '02' 
			OR  b_item_billing_subgroup.f_item_billing_group_id = '03' 
			OR  b_item_billing_subgroup.f_item_billing_group_id = '04' 
			OR  b_item_billing_subgroup.f_item_billing_group_id = '05' 
			OR  b_item_billing_subgroup.f_item_billing_group_id = '06' 
			OR  b_item_billing_subgroup.f_item_billing_group_id = '07' 
			OR  b_item_billing_subgroup.f_item_billing_group_id = '08')  
			THEN b_item_billing_subgroup.f_item_billing_group_id   
		ELSE '09'  END AS CHRGITEM  
	,sum(t_billing_invoice_billing_subgroup.billing_invoice_billing_subgroup_total) as AMOUNT  
	,t_patient.patient_pid AS PERSON_ID

FROM  t_billing_invoice_billing_subgroup  
	INNER JOIN t_visit ON (t_billing_invoice_billing_subgroup.t_visit_id = t_visit.t_visit_id)  
	INNER JOIN t_patient ON (t_patient.t_patient_id = t_visit.t_patient_id)  
	INNER JOIN b_item_billing_subgroup  
		ON (b_item_billing_subgroup.b_item_billing_subgroup_id = t_billing_invoice_billing_subgroup.b_item_billing_subgroup_id)  

where t_visit.f_visit_status_id <> '4' 
	AND t_billing_invoice_billing_subgroup.billing_invoice_billing_subgroup_active = '1' 
    AND f_visit_type_id = '1'
       AND substring(t_visit.visit_staff_doctor_discharge_date_time,1,10) >= ?
       AND substring(t_visit.visit_staff_doctor_discharge_date_time,1,10) <= ?

group by 
	HN  
	, AN  
	,  DATE  
	, CHRGITEM  
	,PERSON_ID
ORDER BY HN)
