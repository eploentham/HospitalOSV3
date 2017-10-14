SELECT r_uc_plan_group.uc_plan_group_description ,
                  CASE WHEN (sum(query1.womb_cancer) > 0) THEN sum(query1.womb_cancer) ELSE 0 END AS womb_cancer ,
                  CASE WHEN (sum(query1.breast_cancer) > 0) THEN sum(query1.breast_cancer) ELSE 0 END AS breast_cancer ,
                  CASE WHEN (sum(query1.thalassemia) > 0) THEN sum(query1.thalassemia) ELSE 0 END AS thalassemia ,
                  CASE WHEN (sum(query1.thalassemia_mom) > 0) THEN sum(query1.thalassemia_mom) ELSE 0 END AS thalassemia_mom ,
                  CASE WHEN (sum(query1.iodine_thiroid) > 0) THEN sum(query1.iodine_thiroid) ELSE 0 END AS iodine_thiroid 
                  FROM r_uc_plan_group LEFT JOIN (SELECT DISTINCT t_patient.t_patient_id ,
                  CASE  WHEN ((r_plan_group.plan_group_number = '1' ) 
                  OR (r_plan_group.plan_group_number = '2' ) ) 
                  THEN  r_plan_group.plan_group_number 
                  WHEN (((r_plan_group.plan_group_number = '3' ) 
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
                  AND (r_plan_group.plan_group_number <> '4' ) ) THEN  '5' END AS plan_group_id ,
                  CASE WHEN ( r_rp11_disease.r_rp11_disease_id = '8280000000001') THEN 1 ELSE 0 END AS womb_cancer 
                  ,CASE WHEN ( r_rp11_disease.r_rp11_disease_id = '8280000000002') THEN 1 ELSE 0 END AS breast_cancer ,
                  CASE WHEN ( r_rp11_disease.r_rp11_disease_id = '8280000000003') THEN 1 ELSE 0 END AS thalassemia,
                  CASE WHEN (r_rp11_disease.r_rp11_disease_id = '8280000000004') THEN 1 ELSE 0 END AS thalassemia_mom ,
                  CASE WHEN (( r_rp11_disease.r_rp11_disease_id = '8280000000005')  
                  AND (to_number((substring(' ' || age(to_date(substring(t_visit.visit_financial_discharge_time,1,10) ,'YYYY-MM-DD'), 
                  to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') IS NULL 
                  AND ((to_number((substring(' ' || age(to_date(substring(t_visit.visit_financial_discharge_time,1,10) ,'YYYY-MM-DD'), 
                  to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') ),'999') <=1) 
                  OR (to_number((substring(' ' || age(to_date(substring(t_visit.visit_financial_discharge_time,1,10) 
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') ),'999') IS NULL) ))) THEN 1 ELSE 0 END AS iodine_thiroid 
                  FROM t_health_family 
		  INNER JOIN t_patient 
                  ON t_health_family.t_health_family_id = t_patient.t_health_family_id 
                  INNER JOIN  t_visit ON t_patient.t_patient_id = t_visit.t_patient_id 
                  INNER JOIN t_order ON t_visit.t_visit_id = t_order.t_visit_id 
                  INNER JOIN r_rp11_item ON t_order.b_item_id = r_rp11_item.b_item_id 
                  INNER JOIN r_rp11_disease ON r_rp11_item.r_rp11_disease_id = r_rp11_disease.r_rp11_disease_id 
		  INNER JOIN t_patient_payment 
                  ON t_health_family.t_health_family_id = t_patient_payment.t_health_family_id 
                  INNER JOIN b_contract_plans 
                  ON t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id 
                  INNER JOIN r_plan_group_map_pttype 
                  ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype 
                  INNER JOIN r_plan_group 
                  ON r_plan_group_map_pttype.r_plan_group_id = r_plan_group.r_plan_group_id 
                  
                  WHERE 
		  t_visit.f_visit_status_id <> '4' 
                  AND t_visit.visit_first_visit = '1'
                  AND t_order.f_order_status_id <> '0' 
                  AND t_order.f_order_status_id <> '3' 
                  AND t_patient_payment.patient_payment_priority = '0' 
                  AND (SUBSTRING(t_visit.visit_financial_discharge_time,1,10) 
                  BETWEEN ? and ?)

                  GROUP BY t_patient.t_patient_id ,plan_group_id ,womb_cancer ,
                  breast_cancer ,thalassemia ,thalassemia_mom ,iodine_thiroid) AS query1 
                  ON query1.plan_group_id = r_uc_plan_group.uc_plan_group_number 
                  GROUP BY r_uc_plan_group.uc_plan_group_description ,r_uc_plan_group.uc_plan_group_number 
                  ORDER BY r_uc_plan_group.uc_plan_group_number
