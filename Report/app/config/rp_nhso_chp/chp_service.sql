SELECT  distinct 
	b_site.b_visit_office_id as	PCUCODE  
	, t_health_family.health_family_hn_hcis AS PID
    , t_visit.visit_vn  AS SEQ 
	, CASE WHEN length(t_visit.visit_begin_visit_time)>=10
            THEN(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        
                    || substring(t_visit.visit_begin_visit_time,6,2)        
                    || substring(t_visit.visit_begin_visit_time,9,2)
            ELSE '' END AS DATE_SERV 
	, CASE  			
		WHEN ( t_health_village.village_moo <> '0')        		
			THEN '1'   	
		ELSE '2'   END  as		 LOCATE  
	, t_visit.visit_first_visit as			 PTTYPE 
	, substring(t_visit.visit_begin_visit_time,12,5) as			 INTIME
	, SUM(CASE 			
		WHEN (t_billing.billing_total IS NOT NULL )        		
			THEN  t_billing.billing_total   	
		ELSE 0   END) as		 PRICE  
    ,b_contract_plans.contract_plans_sort_index AS INSTYPE   
	, t_visit_payment.visit_payment_card_number as			 INSID
	, t_visit_payment.visit_payment_main_hospital as			 MAIN  
	, SUM(CASE 			
		WHEN (t_billing.billing_patient_share IS NOT NULL )  		
			THEN t_billing.billing_patient_share   	
		else 0   END) as		 PAY  
	, CASE  WHEN t_visit_refer_in_out.f_visit_refer_type_id <> ''        		
			AND t_visit_refer_in_out.f_visit_refer_type_id  <> 'null' 
			AND t_visit_refer_in_out.f_visit_refer_type_id  = '0'          	
			THEN '1'   	
		ELSE '0'   END as		 REFERIN  
	, case when t_visit_refer_in_out.f_visit_refer_type_id  = '0' 
            then  t_visit_refer_in_out.visit_refer_in_out_refer_hospital
            else '' end as	 REFINHOS
	, CASE  			
		WHEN t_visit_refer_in_out.f_visit_refer_type_id <> ''        		
			AND t_visit_refer_in_out.f_visit_refer_type_id <> 'null'  
			AND t_visit_refer_in_out.f_visit_refer_type_id  = '1'       	
			THEN '1'  	
		ELSE '0'  END as		 REFEROUT  
	, case when t_visit_refer_in_out.f_visit_refer_type_id  = '1'
            then  t_visit_refer_in_out.visit_refer_in_out_refer_hospital
            else ''  end as REFOUHOS  
        ,CASE when length(visit_begin_visit_time)>=19
           then (to_number(substring(visit_begin_visit_time,1,4),'9999')-543)
            ||substring(visit_begin_visit_time,6,2)
            ||substring(visit_begin_visit_time,9,2)
            ||substring(visit_begin_visit_time,12,2)
            ||substring(visit_begin_visit_time,15,2)
            ||substring(visit_begin_visit_time,18,2)
        else '' end as d_update
FROM t_visit   
	INNER JOIN t_patient  ON t_visit.t_patient_id = t_patient.t_patient_id  
	INNER JOIN t_visit_payment  ON (t_visit.t_visit_id = t_visit_payment.t_visit_id  
		AND t_visit_payment.visit_payment_priority = '0' 
		AND t_visit_payment.visit_payment_active = '1'  )
	LEFT JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id 
	LEFT JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
	LEFT JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
	LEFT JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id
	LEFT JOIN t_billing  ON (t_visit.t_visit_id = t_billing.t_visit_id 
        AND t_billing.billing_active = '1') 
    LEFT JOIN t_visit_refer_in_out ON (t_visit.t_visit_id = t_visit_refer_in_out.t_visit_id
            AND visit_refer_in_out_active = '1')
    LEFT join (select t_visit_id,min(assign_date_time) as update_time from t_visit_service 
                WHERE  substring(assign_date_time,1,10) >= ?
                AND substring(assign_date_time,1,10) <= ?
                group by t_visit_id) 
            as t_visit_service on t_visit.t_visit_id = t_visit_service.t_visit_id
	,b_site 
WHERE   t_visit.f_visit_status_id <> '4'     
     AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
    AND substring(t_visit.visit_begin_visit_time,1,10) <= ?
GROUP BY  
	PCUCODE, PID, SEQ, DATE_SERV, LOCATE,PTTYPE, INTIME
	, INSTYPE
,INSID, MAIN, REFERIN, REFINHOS, REFEROUT, REFOUHOS ,d_update
ORDER BY  SEQ 