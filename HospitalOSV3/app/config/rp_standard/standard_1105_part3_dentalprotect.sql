 SELECT r_uc_plan_group.uc_plan_group_description  
        ,case when sum(query1.baby) IS NOT NULL 
            then sum(query1.baby)             else 0 
            end as baby  
        ,case when sum(query1.child) IS NOT NULL 
            then sum(query1.child)		else 0 
            end as child  
FROM  r_uc_plan_group 
        LEFT JOIN (select  
                    distinct t_patient.t_patient_id 
                    ,t_visit.t_visit_id 
                    ,CASE  WHEN ((r_plan_group.plan_group_number = '1' ) OR (r_plan_group.plan_group_number = '2' ) )  
                            THEN  r_plan_group.plan_group_number  
                        WHEN (((r_plan_group.plan_group_number = '3' ) OR (r_plan_group.plan_group_number = '4' ) ) 
                                AND ((t_patient_payment.patient_payment_main_hospital IN 
                                    (Select  hospital_incup_code from r_hospital_incup))
                                OR (t_patient_payment.patient_payment_sub_hospital IN  
                                    (Select  hospital_incup_code from r_hospital_incup))) ) 
                            THEN '3'  
                        WHEN (((r_plan_group.plan_group_number = '3' ) OR (r_plan_group.plan_group_number = '4' ) ) 
                                AND ( ((t_patient_payment.patient_payment_main_hospital = '') 
                                AND (t_patient_payment.patient_payment_sub_hospital = ''))  
                                OR ((t_patient_payment.patient_payment_main_hospital NOT IN 
                                    (Select  hospital_incup_code  from r_hospital_incup))  
                                AND (t_patient_payment.patient_payment_sub_hospital NOT IN  
                                    (Select  hospital_incup_code from r_hospital_incup))) )) 
                            THEN '4'  
                        WHEN ((r_plan_group.plan_group_number <> '1' ) AND (r_plan_group.plan_group_number <> '2' )  
                                AND (r_plan_group.plan_group_number <> '3' ) AND (r_plan_group.plan_group_number <> '4' ) ) 
                            THEN  '5'  
                        END AS plan_group_id 
                    ,CASE WHEN (to_number(t_visit.visit_patient_age,'999') >= 0
                          AND to_number(t_visit.visit_patient_age,'999') <=5 ) 
                        THEN 1      ELSE 0  
                        END AS baby 
                    , CASE WHEN (to_number(t_visit.visit_patient_age,'999') >= 6
                          AND to_number(t_visit.visit_patient_age,'999') <=14 ) 
                        then 1                else 0  
                        end  AS child 
                from  t_order  
                    INNER JOIN  t_visit ON t_visit.t_visit_id = t_order.t_visit_id  
                    LEFT JOIN t_patient  ON t_patient.t_patient_id = t_visit.t_patient_id 
                    LEFT JOIN t_health_family  ON t_health_family.t_health_family_id = t_patient.t_health_family_id 
                    LEFT JOIN t_patient_payment  ON (t_health_family.t_health_family_id = t_patient_payment.t_health_family_id  
                            and t_patient_payment.patient_payment_priority = '0' )
                    LEFT JOIN b_contract_plans  ON t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id 
                    LEFT JOIN r_plan_group_map_pttype ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype 
                    LEFT JOIN r_plan_group ON r_plan_group_map_pttype.r_plan_group_id = r_plan_group.r_plan_group_id 
                    INNER JOIN r_dental_protect_item on r_dental_protect_item.b_item_id = t_order.b_item_id
                where  t_visit.f_visit_status_id <> '4' 
                    and t_order.f_order_status_id <> '0' 
                    and t_order.f_order_status_id <> '3' 
                    and substring(t_visit.visit_begin_visit_time,1,10) Between  ? and ?
                group by  t_patient.t_patient_id 
                    ,t_visit.t_visit_id 
                    ,plan_group_id 
                    ,baby 
                    ,child 
            ) AS query1   ON query1.plan_group_id = r_uc_plan_group.uc_plan_group_number 

GROUP BY   r_uc_plan_group.uc_plan_group_description  
        ,r_uc_plan_group.uc_plan_group_number  
ORDER BY   r_uc_plan_group.uc_plan_group_number  
