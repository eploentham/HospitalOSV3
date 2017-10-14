 SELECT r_plan_group.plan_group_description  
          ,sum(case when ((query1.t_visit_id IS NOT NULL and query1.t_visit_id <> '')  
                        AND ((patient_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))  
                        OR (patient_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup)
                        OR patient_payment_main_hospital = '')))  
               then 1  
               else 0 end ) AS ipd_incup  
          ,sum(case when ((query1.t_visit_id IS NOT NULL and query1.t_visit_id <> '') 
                        AND ((patient_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))   
                        AND (patient_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))))  
               then 1  
               else 0 end) AS ipd_outcup  
          ,sum(case when (((patient_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))   
                        OR (patient_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup))) 
                        AND ((to_date(substring(visit_staff_doctor_discharge_date_time,1,10),'yyyy-mm-dd') -  
                          to_date(substring(visit_begin_admit_date_time,1,10),'yyyy-mm-dd')) = 0 ))  
               then 1  
               when (((patient_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))   
                     OR (patient_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup))) 
                       AND ((to_date(substring(visit_staff_doctor_discharge_date_time,1,10),'yyyy-mm-dd') -  
                          to_date(substring(visit_begin_admit_date_time,1,10),'yyyy-mm-dd')) > 0 ))  
               then   
                   to_date(substring(visit_staff_doctor_discharge_date_time,1,10),'yyyy-mm-dd') -  
                   to_date(substring(visit_begin_admit_date_time,1,10),'yyyy-mm-dd')  
               else 0 end) AS day_stay_incup  
          ,sum(case when (((patient_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))   
                       AND (patient_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))) 
                       AND ((to_date(substring(visit_staff_doctor_discharge_date_time,1,10),'yyyy-mm-dd') -  
                         to_date(substring(visit_begin_admit_date_time,1,10),'yyyy-mm-dd')) = 0 ))  
               then 1  
               when (((patient_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))   
                       AND (patient_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))) 
                       AND ((to_date(substring(visit_staff_doctor_discharge_date_time,1,10),'yyyy-mm-dd') -  
                         to_date(substring(visit_begin_admit_date_time,1,10),'yyyy-mm-dd')) > 0 ))  
               then   
                   to_date(substring(visit_staff_doctor_discharge_date_time,1,10),'yyyy-mm-dd') -  
                   to_date(substring(visit_begin_admit_date_time,1,10),'yyyy-mm-dd')  
               else 0 end) AS day_stay_incup  

FROM r_plan_group 
    LEFT JOIN  
        (SELECT  t_visit.t_visit_id
                ,t_visit.t_patient_id
                ,t_visit.visit_staff_doctor_discharge_date_time  
                ,t_visit.visit_begin_admit_date_time
                ,patient_payment_main_hospital
                ,t_patient_payment.patient_payment_sub_hospital
                ,b_contract_plans.contract_plans_pttype
                ,CASE WHEN (r_plan_group_map_pttype.r_plan_group_id IS NULL OR  
                    r_plan_group_map_pttype.r_plan_group_id = '' OR  
                     r_plan_group_map_pttype.r_plan_group_id = 'null' ) 
                    THEN '8030000000006'  
                    ELSE r_plan_group_map_pttype.r_plan_group_id  
                    END AS r_plan_group_id 
        FROM  t_visit 
                LEFT JOIN t_patient ON t_patient.t_patient_id = t_visit.t_patient_id
                LEFT JOIN t_patient_payment ON t_patient.t_health_family_id = t_patient_payment.t_health_family_id 
            LEFT JOIN b_contract_plans ON t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id
            LEFT JOIN r_plan_group_map_pttype ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype  
        WHERE  t_visit.f_visit_status_id <> '4'
            AND (t_visit.f_visit_type_id = '1')  
            AND (SUBSTRING(t_visit.visit_staff_doctor_discharge_date_time,1,10)
                 BETWEEN ? and ?)
         ) AS query1 ON r_plan_group.r_plan_group_id = query1.r_plan_group_id  
GROUP BY  
    r_plan_group.plan_group_description  
    ,r_plan_group.plan_group_number 
    ,r_plan_group.r_plan_group_id  
ORDER BY  
    r_plan_group.plan_group_number 
