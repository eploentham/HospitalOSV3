SELECT r_uc_plan_group.uc_plan_group_description ,
                count(query1.t_health_family_id) AS terminated_pregnance 
                FROM r_uc_plan_group LEFT JOIN (SELECT DISTINCT t_health_family.t_health_family_id ,
                t_health_pregnancy.health_pregnancy_gravida_number ,
                CASE  WHEN ((r_plan_group.plan_group_number = '1' ) 
                OR (r_plan_group.plan_group_number = '2' ) ) 
                THEN  r_plan_group.plan_group_number WHEN (((r_plan_group.plan_group_number = '3' ) 
                OR (r_plan_group.plan_group_number = '4' ) ) 
                AND ((t_patient_payment.patient_payment_main_hospital IN (Select  hospital_incup_code 
                from r_hospital_incup))  OR (t_patient_payment.patient_payment_sub_hospital IN 
                (Select  hospital_incup_code from r_hospital_incup))) ) THEN '3' 
                WHEN (((r_plan_group.plan_group_number = '3' ) 
                OR (r_plan_group.plan_group_number = '4' ) ) 
                AND ( ((t_patient_payment.patient_payment_main_hospital = '') 
                AND (t_patient_payment.patient_payment_sub_hospital = '')) 
                OR ((t_patient_payment.patient_payment_main_hospital NOT IN (Select  hospital_incup_code 
                from r_hospital_incup))  AND (t_patient_payment.patient_payment_sub_hospital NOT IN 
                (Select  hospital_incup_code from r_hospital_incup))) )) THEN '4' 
                WHEN ((r_plan_group.plan_group_number <> '1' ) 
                AND (r_plan_group.plan_group_number <> '2' ) 
                AND (r_plan_group.plan_group_number <> '3' ) 
                AND (r_plan_group.plan_group_number <> '4' ) ) THEN '5' END AS plan_group_id 
                
		FROM t_health_pregnancy 
                INNER JOIN  t_health_anc 
                ON (t_health_pregnancy.t_health_pregnancy_id = t_health_anc.t_health_pregnancy_id 
                AND  t_health_anc.health_anc_baby_thalassemia ='2') 
                INNER JOIN t_health_family 
                ON t_health_anc.t_health_family_id = t_health_family.t_health_family_id 
                INNER JOIN t_health_delivery ON (t_health_delivery.t_health_family_id = t_health_family.t_health_family_id 
                AND t_health_delivery.gravida_number = t_health_pregnancy.health_pregnancy_gravida_number 
                AND t_health_delivery.f_health_postpartum_givebirth_result_id = '2' ) 
                INNER JOIN t_patient_payment 
                ON t_health_family.t_health_family_id = t_patient_payment.t_health_family_id 
                INNER JOIN b_contract_plans 
                ON t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id	
                INNER JOIN r_plan_group_map_pttype 
                ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype 
                INNER JOIN r_plan_group 
                ON r_plan_group_map_pttype.r_plan_group_id = r_plan_group.r_plan_group_id 
                
		WHERE t_patient_payment.patient_payment_priority = '0' 
                AND ((SUBSTRING(t_health_delivery.record_date_time,1,10) 
                BETWEEN ? and ?) 
                OR (SUBSTRING(t_health_delivery.health_delivery_survey_date,1,10) 
                BETWEEN ? and ?)) 
                GROUP BY t_health_family.t_health_family_id ,plan_group_id ,
                t_health_pregnancy.health_pregnancy_gravida_number ) AS query1 
                ON query1.plan_group_id = r_uc_plan_group.uc_plan_group_number 
                GROUP BY r_uc_plan_group.uc_plan_group_description ,
                r_uc_plan_group.uc_plan_group_number 
                ORDER BY r_uc_plan_group.uc_plan_group_number;
