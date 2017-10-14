SELECT  b_site.b_visit_office_id as			 PCUCODE  
	,  t_health_family.health_family_hn_hcis as			 PID		
	,t_visit.visit_vn AS SEQ 		
	, (to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        			
		|| substring(t_visit.visit_begin_visit_time,6,2)       		
		|| substring(t_visit.visit_begin_visit_time,9,2) as		 DATE_SERV 
	, CASE WHEN ( t_health_village is null)   THEN null
               WHEN ( t_health_village.village_moo <> '0')   THEN '1'   	
		ELSE '2'   END  as		 LOCATE  
	, t_patient.t_patient_id as			 PTTYPE 
	, substring(t_visit.visit_begin_visit_time,12,5) as			 INTIME
	, SUM(CASE WHEN (t_billing.billing_total IS NOT NULL )  
            THEN  t_billing.billing_total   	
		ELSE 0   END) as		 PRICE  
	, b_contract_plans.contract_plans_pttype as			 INSTYPE  
	, t_visit_payment.visit_payment_card_number as			 INSID
	, t_visit_payment.visit_payment_main_hospital as			 MAIN  
	, SUM(CASE WHEN (t_billing.billing_patient_share IS NOT NULL )  		
			THEN t_billing.billing_patient_share   	
		else 0   END) as		 PAY  
	, CASE  WHEN b_visit_office_id_refer_in <> ''        		
			AND b_visit_office_id_refer_in <> 'null'         	
			THEN '1'   	
		ELSE '0'   END as		 REFERIN  
	, t_visit.b_visit_office_id_refer_in as			 REFINHOS  
	, CASE  WHEN b_visit_office_id_refer_out <> ''        		
			AND b_visit_office_id_refer_out<> 'null'        	
			THEN '1'  	
		ELSE '0'  END as		 REFEROUT  
	, t_visit.b_visit_office_id_refer_out as			 REFOUHOS 
	, t_visit.visit_first_visit as			 first_visit 

FROM t_visit   
	INNER JOIN t_patient  ON t_visit.t_patient_id = t_patient.t_patient_id  
	INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id 
	INNER JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    INNER JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
	INNER JOIN t_visit_payment  ON (t_visit.t_visit_id = t_visit_payment.t_visit_id
            AND t_visit_payment.visit_payment_priority = '0' 
            AND t_visit_payment.visit_payment_active = '1' )
	INNER JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id
	INNER JOIN t_billing  ON (t_visit.t_visit_id = t_billing.t_visit_id
            AND t_billing.billing_active = '1') 
	,b_site 

	
WHERE   t_visit.f_visit_status_id <> '4'        
    AND health_family_active = '1'
    AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
    AND substring(t_visit.visit_begin_visit_time,1,10) <= ?
	
GROUP BY  
	PCUCODE, PID, SEQ, DATE_SERV, LOCATE,PTTYPE, INTIME
	, INSTYPE,INSID, MAIN, REFERIN, REFINHOS, REFEROUT, REFOUHOS ,first_visit 
	
ORDER BY  SEQ 
