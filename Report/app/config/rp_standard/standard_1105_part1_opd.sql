select  r_plan_group.plan_group_description AS plan_group_description 
    ,sum(case when  (query1.visit_first_visit = '1'  
                       AND ((patient_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))   
                       OR (patient_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup)
                        OR patient_payment_main_hospital = '')))  
             then  1  
             else  0 end) AS patient_incup 
    ,sum(case when  (query1.visit_first_visit = '1' 
                       AND ((patient_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))   
                       AND (patient_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup)
                        AND patient_payment_main_hospital <> '' )))  
             then  1  
             else  0 end) AS patient_outcup 
    ,sum(case when (((patient_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))   
                    OR (patient_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup)
                    OR patient_payment_main_hospital = ''))  
                   AND t_visit_id IS NOT NULL)  
         then 1  
         else 0 end ) AS visit_incup  
    ,sum(case when (((patient_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))   
                   AND (patient_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))) 
                   AND patient_payment_main_hospital <> '' 
                    AND t_visit_id IS NOT NULL)  
         then 1  
         else 0 end ) AS visit_outcup  
FROM r_plan_group  
    LEFT JOIN  
         (SELECT t_visit.t_visit_id
            , t_visit.visit_first_visit
            , t_visit.t_patient_id
            , patient_payment_main_hospital
            , t_patient_payment.patient_payment_sub_hospital
            , b_contract_plans.contract_plans_pttype
            , CASE WHEN (r_plan_group_map_pttype.r_plan_group_id IS NULL OR  
                     r_plan_group_map_pttype.r_plan_group_id = '' OR  
                     r_plan_group_map_pttype.r_plan_group_id = 'null' ) 
                THEN '8030000000006'  
                ELSE r_plan_group_map_pttype.r_plan_group_id  
                END AS r_plan_group_id 
         FROM b_site,t_visit 
                LEFT JOIN t_patient ON t_patient.t_patient_id = t_visit.t_patient_id
                LEFT JOIN t_patient_payment ON t_patient.t_health_family_id = t_patient_payment.t_health_family_id 
            LEFT JOIN b_contract_plans  ON t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id
            LEFT JOIN r_plan_group_map_pttype ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype  
         
        WHERE t_visit.f_visit_status_id <> '4'
          AND f_visit_type_id = '0'
          AND (SUBSTRING(t_visit.visit_begin_visit_time,1,10) BETWEEN ? and ?)
        ) AS query1  On r_plan_group.r_plan_group_id = query1.r_plan_group_id  
group by   
         r_plan_group.plan_group_description  
         , r_plan_group.r_plan_group_id 
         , r_plan_group.plan_group_number 
order by  
         r_plan_group.plan_group_number 

