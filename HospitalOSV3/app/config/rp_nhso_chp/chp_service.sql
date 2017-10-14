SELECT  b_site.b_visit_office_id                as	PCUCODE  
    , t_health_family.health_family_hn_hcis as PID 
	, t_visit.visit_vn                                  AS SEQ 	
	, (to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        			
		|| substring(t_visit.visit_begin_visit_time,6,2)       		
		|| substring(t_visit.visit_begin_visit_time,9,2) as	DATE_SERV  
	, CASE WHEN ( t_health_village is null)   THEN null
               WHEN ( t_health_village.village_moo <> '0')   THEN '1'   	
               ELSE '2'   END                          as LOCATE
    , t_visit.visit_first_visit as PTTYPE
    , CASE WHEN substring(t_visit.visit_begin_visit_time,12,5) > '08:30' 
                AND substring(t_visit.visit_begin_visit_time,12,5) < '16:30' THEN '1'
                ELSE '2' END                            as INTIME
	, SUM(t_billing.billing_total)                   as PRICE 
	, r_rp1853_instype_id   as	INSTYPE   
	, t_visit_payment.visit_payment_card_number          as INSID
	, t_visit_payment.visit_payment_main_hospital         as MAIN  
	, SUM(t_billing.billing_patient_share)                      as PAY  
	, CASE  WHEN referin.visit_refer_in_out_refer_hospital <>''	THEN '1'   	
            ELSE '0'   END                                  as REFERIN  
	, referin.visit_refer_in_out_refer_hospital                as	REFINHOS  
	, CASE  WHEN referout.visit_refer_in_out_refer_hospital <>''	THEN '1'  	
            ELSE '0'  END                                    as REFEROUT  
	, referout.visit_refer_in_out_refer_hospital               as REFOUHOS 
    , CASE  
		WHEN length(t_visit.visit_begin_visit_time)>18
 	         then (to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999') - 543)      
                        || substring(t_visit.visit_begin_visit_time,6,2)      
                        || substring(t_visit.visit_begin_visit_time,9,2) 
                        || substring(t_visit.visit_begin_visit_time,12,2) 
                        || substring(t_visit.visit_begin_visit_time,15,2) 
                        || substring(t_visit.visit_begin_visit_time,18,2)  
         		ELSE ''  END AS D_UPDATE  --NOT NULL

FROM t_visit   
	INNER JOIN t_patient  ON t_visit.t_patient_id = t_patient.t_patient_id  
	INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id 
	INNER JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    INNER JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
	LEFT JOIN t_visit_payment  ON (t_visit.t_visit_id = t_visit_payment.t_visit_id   
            AND t_visit_payment.visit_payment_priority = '0' 
            AND t_visit_payment.visit_payment_active = '1' )
        LEFT JOIN b_contract_plans on  t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id 
	LEFT JOIN t_billing  ON (t_visit.t_visit_id = t_billing.t_visit_id 
            AND t_billing.billing_active = '1') 
   LEFT JOIN t_visit_refer_in_out  referin on (t_visit.t_visit_id = referin.t_visit_id and referin.f_visit_refer_type_id='0')
   LEFT JOIN t_visit_refer_in_out  referout on (t_visit.t_visit_id = referout.t_visit_id and referout.f_visit_refer_type_id='1')
	,b_site 

WHERE   t_visit.f_visit_status_id <> '4'        
    AND health_family_active = '1'
	AND substring(t_visit.visit_begin_visit_time,1,16) >= ?
    AND substring(t_visit.visit_begin_visit_time,1,16) <= ?
GROUP BY  
	 PCUCODE, PID, SEQ, DATE_SERV, LOCATE, INTIME
	,INSTYPE,INSID, MAIN, REFERIN, REFINHOS, REFEROUT, REFOUHOS ,D_UPDATE,PTTYPE
	
ORDER BY  SEQ