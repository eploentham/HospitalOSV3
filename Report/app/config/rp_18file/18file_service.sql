SELECT  distinct 
	b_site.b_visit_office_id as			 PCUCODE  
	,  t_health_family.health_family_hn_hcis as			 PID
	, t_visit.visit_vn as		 SEQ 
	, (to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        			
		|| substring(t_visit.visit_begin_visit_time,6,2)       		
		|| substring(t_visit.visit_begin_visit_time,9,2) as		 DATE_SERV 
	, CASE  			
		WHEN ( t_health_village.village_moo <> '0')        		
			THEN '1'   	
		ELSE '2'   END  as		 LOCATE  
	, t_patient.t_patient_id as			 PTTYPE 
	, substring(t_visit.visit_begin_visit_time,12,5) as			 INTIME  
	, SUM(CASE 			
		WHEN (t_billing.billing_total IS NOT NULL )        		
			THEN  t_billing.billing_total   	
		ELSE 0   END) as		 PRICE  
	, case when (b_contract_plans.contract_plans_pttype = 'AD') then '74'
           when (b_contract_plans.contract_plans_pttype = 'AA') then '71'
           when (b_contract_plans.contract_plans_pttype = 'AE') then '75'
           when (b_contract_plans.contract_plans_pttype = 'AL') then '78'
           when (b_contract_plans.contract_plans_pttype = 'AF') then '76'
           when (b_contract_plans.contract_plans_pttype = 'AJ') then '82'
           when (b_contract_plans.contract_plans_pttype = 'AG') then '77'
           when (b_contract_plans.contract_plans_pttype = 'AC') then '73'
           when (b_contract_plans.contract_plans_pttype = 'AB') then '72'
           when (b_contract_plans.contract_plans_pttype = 'AK') then '81'
           when (b_contract_plans.contract_plans_pttype = 'UC') then '89'
           when (b_contract_plans.contract_plans_pttype = 'A2') then '02'
           when (b_contract_plans.contract_plans_pttype = 'A7') then '01'
           when (b_contract_plans.contract_plans_pttype = 'AR') then '07'
           when (b_contract_plans.contract_plans_pttype = 'AP') then '06'
           when (b_contract_plans.contract_plans_pttype = 'A9') then '78'
           when (b_contract_plans.contract_plans_pttype = 'A1') then '06'
           when (b_contract_plans.contract_plans_pttype = 'AM') then '78'
           when (b_contract_plans.contract_plans_pttype = 'AN') then '06'
        ELSE '78' END  AS INSTYPE  
	, t_visit_payment.visit_payment_card_number as			 INSID
	, t_visit_payment.visit_payment_main_hospital as			 MAIN  
	, SUM(CASE 			
		WHEN (t_billing.billing_patient_share IS NOT NULL )  		
			THEN t_billing.billing_patient_share   	
		else 0   END) as		 PAY  
	, CASE  			
		WHEN b_visit_office_id_refer_in <> ''        		
			AND b_visit_office_id_refer_in <> 'null'         	
			THEN '1'   	
		ELSE '0'   END as		 REFERIN  
	, t_visit.b_visit_office_id_refer_in as			 REFINHOS  
	, CASE  			
		WHEN b_visit_office_id_refer_out <> ''        		
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
	INNER JOIN t_visit_payment  ON (t_visit.t_visit_id = t_visit_payment.t_visit_id   )
	INNER JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id
	INNER JOIN t_billing  ON (t_visit.t_visit_id = t_billing.t_visit_id )
	,b_site
	
WHERE   t_visit.f_visit_status_id <> '4'  
    AND t_billing.billing_active = '1'
    AND health_family_active = '1'
    AND t_visit_payment.visit_payment_priority = '0' 
    AND t_visit_payment.visit_payment_active = '1' 
    AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
    AND substring(t_visit.visit_begin_visit_time,1,10) <= ?
	
GROUP BY  
	PCUCODE, PID, SEQ, DATE_SERV, LOCATE,PTTYPE, INTIME
	, INSTYPE,INSID, MAIN, REFERIN, REFINHOS, REFEROUT, REFOUHOS ,first_visit 
	
ORDER BY  SEQ 


