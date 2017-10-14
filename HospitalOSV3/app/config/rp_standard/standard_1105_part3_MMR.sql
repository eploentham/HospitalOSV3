 SELECT r_uc_plan_group.uc_plan_group_description  
         ,case when sum(query1.measles) IS NOT NULL 
                then sum(query1.measles) 
                else 0 	end as measles 
FROM r_uc_plan_group 
        LEFT JOIN  (select   distinct  t_health_family.t_health_family_id 
             ,CASE  WHEN ((r_plan_group.plan_group_number = '1' ) OR (r_plan_group.plan_group_number = '2' ) )  
                     THEN  r_plan_group.plan_group_number  
                     WHEN (((r_plan_group.plan_group_number = '3' ) OR (r_plan_group.plan_group_number = '4' ) ) 
                            AND ((t_patient_payment.patient_payment_main_hospital IN (Select  hospital_incup_code  
                            from r_hospital_incup))  OR (t_patient_payment.patient_payment_sub_hospital IN  
                            (Select  hospital_incup_code from r_hospital_incup))) ) 
                     THEN '3'  
                     WHEN (((r_plan_group.plan_group_number = '3' ) OR (r_plan_group.plan_group_number = '4' ) ) 
                            AND ( ((t_patient_payment.patient_payment_main_hospital = '')  
                            AND (t_patient_payment.patient_payment_sub_hospital = ''))  
                            OR ((t_patient_payment.patient_payment_main_hospital NOT IN (Select  hospital_incup_code  
                            from r_hospital_incup))  AND (t_patient_payment.patient_payment_sub_hospital NOT IN  
                            (Select  hospital_incup_code from r_hospital_incup))) )) 
                     THEN '4'  
                     WHEN ((r_plan_group.plan_group_number <> '1' ) AND (r_plan_group.plan_group_number <> '2' )  
                            AND (r_plan_group.plan_group_number <> '3' ) AND (r_plan_group.plan_group_number <> '4' ) ) 
                     THEN  '5'  
                     END AS plan_group_id 
            , 1 as measles 
            from     t_health_epi                   
                     INNER JOIN  t_health_family                         ON t_health_family.t_health_family_id = t_health_epi.t_health_family_id 
                     LEFT JOIN t_patient_payment                          ON (t_health_family.t_health_family_id = t_patient_payment.t_health_family_id  
                            AND t_patient_payment.patient_payment_priority = '0' )
                     LEFT JOIN b_contract_plans                         ON t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id 
                     LEFT JOIN t_health_epi_detail                          ON (t_health_epi.t_health_epi_id = t_health_epi_detail.t_health_epi_id 
                            AND t_health_epi_detail.health_epi_detail_active = '1' )
                     LEFT JOIN r_plan_group_map_pttype                         ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype 
                     LEFT JOIN r_plan_group                         ON r_plan_group_map_pttype.r_plan_group_id = r_plan_group.r_plan_group_id 
                     LEFT JOIN r_epi_group_item                          ON t_health_epi_detail.b_health_epi_set_id = r_epi_group_item.b_health_epi_group_id 
                     LEFT JOIN r_epi_group                         ON r_epi_group_item.r_epi_group_id = r_epi_group.r_epi_group_id 
                     INNER JOIN r_epi_measles_item    ON r_epi_measles_item.b_health_epi_group_id = t_health_epi_detail.b_health_epi_set_id
           where  t_health_epi.health_epi_active = '1' 
                 and substring(t_health_epi.record_date_time,1,10) Between  ? and ?
                 and ((to_number((substring(' ' || age(to_date(substring(t_health_epi_detail.record_date_time,1,10)  
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') IS NULL) 
                    OR ((to_number((substring(' ' || age(to_date(substring(t_health_epi_detail.record_date_time,1,10)  
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') >= 0) 
                          AND (to_number((substring(' ' || age(to_date(substring(t_health_epi_detail.record_date_time,1,10)  
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') <=5) 
                    )) 
            group by      t_health_family.t_health_family_id 
                    ,plan_group_id 
        ) AS query1   ON query1.plan_group_id = r_uc_plan_group.uc_plan_group_number 

GROUP BY   r_uc_plan_group.uc_plan_group_description  
         ,r_uc_plan_group.uc_plan_group_number  

ORDER BY    r_uc_plan_group.uc_plan_group_number  
