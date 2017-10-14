(SELECT 
	 b_site.b_visit_office_id || substring(t_billing_invoice_billing_subgroup.t_billing_invoice_billing_subgroup_id,9) as OPA_ID 
    , b_site.b_visit_office_id   AS PCUCODE  
	, case when  (t_health_family.patient_pid IS NULL or t_health_family.patient_pid = '')
                then t_patient.patient_pid 
                else t_health_family.patient_pid
       end  as CID
    ,t_visit.visit_vn as SEQ
	, CASE WHEN (length(t_visit.visit_begin_visit_time)>=10)
                THEN to_char(to_date(to_number(
                        substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
                        substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                 ELSE ''   
        END  AS DATE_SERV  
	, CASE WHEN (b_item_billing_subgroup.f_item_billing_group_id = '00'
                            or b_item_billing_subgroup.f_item_billing_group_id = '02')  
                        THEN '1'
                  WHEN (b_item_billing_subgroup.f_item_billing_group_id = '01')  
                        THEN '2'
                  WHEN (b_item_billing_subgroup.f_item_billing_group_id = '04')  
                        THEN '3'
                  WHEN (b_item_billing_subgroup.f_item_billing_group_id = '05')  
                        THEN '9'
                  WHEN (b_item_billing_subgroup.f_item_billing_group_id = '09')  
                        THEN '11'
                  ELSE '99'  END AS CHRGITEM  
	,sum(t_billing_invoice_billing_subgroup.billing_invoice_billing_subgroup_total) as AMOUNT  
	, CASE WHEN (length(t_billing.billing_billing_date_time)>=10)
                then to_char(to_date(to_number(
                        substring(t_billing.billing_billing_date_time,1,4),'9999')-543 || 
                        substring(t_billing.billing_billing_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                ELSE ''   
        END  AS NOTEDATE 
    ,t_billing_invoice_billing_subgroup.billing_invoice_billing_subgroup_active as active
    ,b_site.b_visit_office_id || substring(t_visit.t_visit_id,9) as SERVICE_ID
FROM  t_billing_invoice_billing_subgroup  
	INNER JOIN t_visit ON (t_billing_invoice_billing_subgroup.t_visit_id = t_visit.t_visit_id)  
	INNER JOIN t_patient ON (t_patient.t_patient_id = t_visit.t_patient_id)  
    INNER JOIN t_billing ON t_billing.t_billing_id = t_billing_invoice_billing_subgroup.t_billing_id
    INNER JOIN t_health_family  ON t_patient.t_health_family_id = t_health_family.t_health_family_id
	LEFT JOIN b_item_billing_subgroup  
		ON (b_item_billing_subgroup.b_item_billing_subgroup_id = t_billing_invoice_billing_subgroup.b_item_billing_subgroup_id)  
        ,b_site
WHERE t_visit.f_visit_status_id <> '4' 
	AND t_billing_invoice_billing_subgroup.billing_invoice_billing_subgroup_active = '1' 
    AND t_visit.f_visit_type_id = '0'   
	AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
	AND substring(t_visit.visit_begin_visit_time,1,10) <= ?
GROUP BY
	pcucode,cid,seq,date_serv
	, CHRGITEM  
    ,t_billing_invoice_billing_subgroup.t_billing_invoice_billing_subgroup_id 
    ,t_billing.billing_billing_date_time
    ,t_billing_invoice_billing_subgroup.billing_invoice_billing_subgroup_active,SERVICE_ID)
