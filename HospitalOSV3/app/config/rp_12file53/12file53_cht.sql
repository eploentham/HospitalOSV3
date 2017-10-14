
(SELECT
	t_patient.patient_hn AS HN
	,'' AS AN
	, CASE WHEN (length(t_visit.visit_begin_visit_time)>=10)
then to_char(to_date(to_number(
substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 ||
substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')
                    ELSE ''   END AS  DATE
	, sum(CASE
        WHEN f_visit_type_id = '1'
            THEN 0
            ELSE t_billing.billing_total END)  AS TOTAL
	,  sum(CASE
        WHEN f_visit_type_id = '1'
            THEN 0
            ELSE t_billing.billing_paid END)  AS PAID
	, case when b_contract_plans.r_rp1853_instype_id is not null
                then b_contract_plans.r_rp1853_instype_id
                else ''
      end AS PTTYPE
	, t_patient.patient_pid as PERSON_ID
	,case when t_visit.f_visit_type_id = '0'
            then  t_visit.visit_vn
            else t_visit.visit_an
     end AS SEQ
FROM t_billing
	INNER JOIN t_visit ON (t_billing.t_visit_id = t_visit.t_visit_id)
	INNER JOIN t_patient ON (t_patient.t_patient_id = t_visit.t_patient_id)
	LEFT JOIN t_visit_payment ON (t_visit_payment.t_visit_id = t_visit.t_visit_id )
	LEFT JOIN b_contract_plans ON (b_contract_plans.b_contract_plans_id = t_visit_payment.b_contract_plans_id)

where t_visit.f_visit_status_id <> '4'
        AND t_billing.billing_active = '1'
		AND t_visit_payment.visit_payment_priority = '0'
		AND t_visit_payment.visit_payment_active = '1'
	AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
	AND substring(t_visit.visit_begin_visit_time,1,10) <= ?
    and t_visit.f_visit_type_id <> 'S'
group by
	 HN
	,  AN
	,  DATE
	,  PTTYPE
        ,PERSON_ID
        ,SEQ
ORDER BY HN)

union

(SELECT
	t_patient.patient_hn AS HN
	, t_visit.visit_vn AS AN
	, CASE WHEN (length(t_visit.visit_begin_visit_time)>=10)
then to_char(to_date(to_number(
substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 ||
substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')
                    ELSE ''   END AS  DATE
	, sum(t_billing.billing_total) AS TOTAL
	, sum(t_billing.billing_paid) AS PAID
	, case when b_contract_plans.r_rp1853_instype_id is not null
                then b_contract_plans.r_rp1853_instype_id
                else ''
      end AS PTTYPE
	, t_patient.patient_pid as PERSON_ID
	,t_visit.visit_vn AS SEQ
FROM
	t_billing
	INNER JOIN t_visit ON (t_billing.t_visit_id = t_visit.t_visit_id)
	INNER JOIN t_patient ON (t_patient.t_patient_id = t_visit.t_patient_id)
	LEFT JOIN t_visit_payment ON (t_visit_payment.t_visit_id = t_visit.t_visit_id  )
	LEFT JOIN b_contract_plans ON (b_contract_plans.b_contract_plans_id = t_visit_payment.b_contract_plans_id)

where t_visit.f_visit_status_id <> '4'
		AND t_visit_payment.visit_payment_priority = '0'
		AND t_visit_payment.visit_payment_active = '1'
	AND t_billing.billing_active = '1'
    AND f_visit_type_id = '1'
       AND substring(t_visit.visit_staff_doctor_discharge_date_time,1,10) >= ?
       AND substring(t_visit.visit_staff_doctor_discharge_date_time,1,10) <= ?
    and t_visit.f_visit_type_id <> 'S'
group by
	 HN
	,  AN
	,  DATE
	,  PTTYPE
    ,PERSON_ID
    ,SEQ
ORDER BY HN)
