SELECT 
 tmp_service.SERVICE_ID
,tmp_service.PCUCODE
,tmp_service.CID
,tmp_service.SEQ
,tmp_service.DATE_SERV
,tmp_service.LOCATE
,tmp_service.PTTYPE
,tmp_service.INTIME
,tmp_service.PRICE
,tmp_service.INSTYPE
,tmp_service.INSID
,tmp_service.MAIN
,tmp_service.PAY
,tmp_service.REFERIN
,tmp_service.REFINHOS
,tmp_service.REFEROUT
,tmp_service.REFOUHOS
, CASE WHEN length(t_visit_service.update_time)>=10
         THEN(to_number(substring(t_visit_service.update_time,1,4),'9999')-543)        
		|| substring(t_visit_service.update_time,6,2)        
		|| substring(t_visit_service.update_time,9,2)
         ELSE '' END as NOTEDATE
,tmp_service.HN
,tmp_service.TIMEVISIT
,tmp_service.ACTIVE
FROM ( 
SELECT  distinct 

    b_site.b_visit_office_id || substr(t_visit.t_visit_id,9) as SERVICE_ID

	, b_site.b_visit_office_id as	PCUCODE  

	,  case when  (t_health_family.patient_pid IS NULL or t_health_family.patient_pid = '')

                then t_patient.patient_pid 

                else t_health_family.patient_pid

        end  as CID

    , t_visit.visit_vn  AS SEQ 

	, CASE WHEN length(t_visit.visit_begin_visit_time)>=10

            THEN(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        

                    || substring(t_visit.visit_begin_visit_time,6,2)        

                    || substring(t_visit.visit_begin_visit_time,9,2)

            ELSE '' END AS DATE_SERV 

	, CASE  			

		WHEN ( t_health_village.village_moo <> '0')        		

			THEN '1'   	

		ELSE '2'   END  as LOCATE  

	, t_visit.visit_first_visit as	PTTYPE 

	, case when (substring(t_visit.visit_begin_visit_time,12,5)) >= '08.00'  

                        and (substring(t_visit.visit_begin_visit_time,12,5)) <= '16.30'

               then '1'

               else '2'

     end as	INTIME

	, SUM(CASE WHEN (t_billing.billing_total IS NOT NULL )        		

			THEN  t_billing.billing_total   	

		ELSE 0   END) as PRICE  

	, case when  b_nhso_map_plan.f_nhso_sub_inscl_id is not null 

                        and trim( b_nhso_map_plan.f_nhso_sub_inscl_id) <> 'null'

                then trim( b_nhso_map_plan.f_nhso_sub_inscl_id)

                else ''

      end AS INSTYPE  

	, t_visit_payment.visit_payment_card_number as INSID

	, t_visit_payment.visit_payment_main_hospital as  MAIN  

	, SUM(CASE 			

		WHEN (t_billing.billing_patient_share IS NOT NULL )  		

			THEN t_billing.billing_patient_share   	

		else 0   END) as PAY  

	, CASE  WHEN b_visit_office_id_refer_in <> ''        		

			AND b_visit_office_id_refer_in <> 'null'         	

			THEN '1'   	

		ELSE '0'   END as REFERIN  

	, t_visit.b_visit_office_id_refer_in as REFINHOS  

	, CASE  			

		WHEN b_visit_office_id_refer_out <> ''        		

			AND b_visit_office_id_refer_out<> 'null'        	

			THEN '1'  	

		ELSE '0'  END as REFEROUT  

	, t_visit.b_visit_office_id_refer_out as REFOUHOS 
       ,t_patient.patient_hn as HN
    ,replace(substring(t_visit.visit_begin_visit_time,12,5),':','') as TIMEVISIT
    ,'1' as ACTIVE
    ,t_visit.t_visit_id as t_visit_id

FROM t_visit   

	INNER JOIN t_patient  ON t_visit.t_patient_id = t_patient.t_patient_id  

	INNER JOIN t_visit_payment  ON (t_visit.t_visit_id = t_visit_payment.t_visit_id  

		AND t_visit_payment.visit_payment_priority = '0' 

		AND t_visit_payment.visit_payment_active = '1'  )

	INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id 

	LEFT JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id

	LEFT JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id

	LEFT JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id

	LEFT JOIN t_billing  ON (t_visit.t_visit_id = t_billing.t_visit_id 

        AND t_billing.billing_active = '1') 

    LEFT JOIN b_nhso_map_plan ON b_nhso_map_plan.b_contract_plan_id = b_contract_plans.b_contract_plans_id
	,b_site 

WHERE   t_visit.f_visit_status_id <> '4'    
     AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
     AND substring(t_visit.visit_begin_visit_time,1,10) <= ?

GROUP BY  
	PCUCODE, CID, SEQ, DATE_SERV, LOCATE,PTTYPE, INTIME
	, INSTYPE,INSID, MAIN, REFERIN, REFINHOS, REFEROUT, REFOUHOS ,t_visit.t_visit_id
    ,t_patient.patient_hn,t_visit.visit_begin_visit_time

ORDER BY  SEQ 
) AS tmp_service INNER JOIN (select t_visit_service.t_visit_id ,min(assign_date_time) as update_time
                                from t_visit_service group by t_visit_service.t_visit_id ) 
                 AS t_visit_service ON tmp_service.t_visit_id = t_visit_service.t_visit_id
