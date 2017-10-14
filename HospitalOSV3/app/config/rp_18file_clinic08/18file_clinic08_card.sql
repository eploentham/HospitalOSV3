SELECT 
	b_site.b_visit_office_id AS PCUCODE 
, case when  (t_health_family.patient_pid IS NULL or t_health_family.patient_pid = '')
                then ''
                else t_health_family.patient_pid
       end  as CID
, case when  (t_health_family.health_family_hn_hcis IS NULL or t_health_family.health_family_hn_hcis = '')
                then ''
                else t_health_family.health_family_hn_hcis
       end AS PID
    , case when b_nhso_map_plan.f_nhso_sub_inscl_id is not null 
                        and trim(b_nhso_map_plan.f_nhso_sub_inscl_id) <> 'null' 
                then trim(b_nhso_map_plan.f_nhso_sub_inscl_id)
                else '' 
       end  AS INSTYPE 
	,  CASE  
		WHEN (t_patient_payment.patient_payment_card_number <> ''      
			and t_patient_payment.patient_payment_card_number <> 'null')       
			then t_patient_payment.patient_payment_card_number      
		ELSE '' END AS INSID 
	, CASE  
		WHEN (t_patient_payment.patient_payment_card_issue_date <> ''       
			and t_patient_payment.patient_payment_card_issue_date <> 'null')       
			then (to_number(substring(t_patient_payment.patient_payment_card_issue_date,1,4),'9999')-543)       
				|| substring(t_patient_payment.patient_payment_card_issue_date,6,2)       
				|| substring(t_patient_payment.patient_payment_card_issue_date,9,2) 
		ELSE '' END  AS START_DATE 
	, CASE  
		WHEN (t_patient_payment.patient_payment_card_expire_date <> ''      
			and t_patient_payment.patient_payment_card_expire_date <> 'null')       
			then (to_number(substring(t_patient_payment.patient_payment_card_expire_date,1,4),'9999')-543)       
				|| substring(t_patient_payment.patient_payment_card_expire_date,6,2)       
				|| substring(t_patient_payment.patient_payment_card_expire_date,9,2) 
		ELSE '' END  AS EXPIR_DATE 
	, CASE  
		WHEN (t_patient_payment.patient_payment_main_hospital <> ''      
			and t_patient_payment.patient_payment_main_hospital <> 'null')       
			then   t_patient_payment.patient_payment_main_hospital 
		ELSE '' END AS MAIN 
	, CASE 
		WHEN (t_patient_payment.patient_payment_sub_hospital <> ''      
			and t_patient_payment.patient_payment_sub_hospital <> 'null')       
			then   t_patient_payment.patient_payment_sub_hospital 
		ELSE '' END AS SUB 
	, CASE  
		WHEN   (t_patient_payment.patient_payment_record_date <> ''      
			and t_patient_payment.patient_payment_record_date <> 'null')        
			then (to_number(substring(t_patient_payment.patient_payment_record_date,1,4),'9999')-543)        
				|| substring(t_patient_payment.patient_payment_record_date,6,2)       
				|| substring(t_patient_payment.patient_payment_record_date,9,2) 
		ELSE '' END AS UPDATE_DATE 
    ,'1' as ACTIVE
from  t_patient_payment
	LEFT JOIN  b_contract_plans ON t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id       
	INNER JOIN t_health_family  ON (t_patient_payment.t_health_family_id = t_health_family.t_health_family_id AND t_health_family.health_family_active = '1')
	LEFT JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    LEFT JOIN t_health_village ON (t_health_home.t_health_village_id = t_health_village.t_health_village_id
            and t_health_village.village_moo <> '0' )
    LEFT JOIN b_nhso_map_plan ON b_nhso_map_plan.b_contract_plan_id = b_contract_plans.b_contract_plans_id
    ,b_site
WHERE      
 t_patient_payment.patient_payment_priority = '0'
	and substring(t_patient_payment.patient_payment_record_date,1,10) >= ?
	and substring(t_patient_payment.patient_payment_record_date,1,10) <= ?
