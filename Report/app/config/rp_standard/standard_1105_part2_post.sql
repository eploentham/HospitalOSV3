SELECT 
                      r_uc_plan_group.uc_plan_group_description   
                      ,CASE WHEN (sum(query1.BeforeMch) > 0) 
                               THEN sum(query1.BeforeMch) 
                               ELSE 0 
                      END AS BeforeMch 
                      ,CASE WHEN (sum(query1.BeforeMch_visit) > 0) 
                               THEN sum(query1.BeforeMch_visit) 
                               ELSE 0 
                      END AS BeforeMch_visit 
                      ,CASE WHEN (sum(query1.AfterMch) > 0) 
                               THEN sum(query1.AfterMch) 
                               ELSE 0 
                      END AS AfterMch 
                      ,CASE WHEN (sum(query1.AfterMch_visit) > 0) 
                               THEN sum(query1.AfterMch_visit) 
                               ELSE 0 
                      END AS AfterMch_visit 
                      ,CASE WHEN (sum(query1.BirthUsualInfant) > 0) 
                               THEN sum(query1.BirthUsualInfant) 
                               ELSE 0 
                      END AS BirthUsualInfant 
                      ,CASE WHEN (sum(query1.BirthUnUsualInfant) > 0) 
                               THEN sum(query1.BirthUnUsualInfant) 
                               ELSE 0 
                      END AS BirthUnUsualInfant 
                      ,CASE WHEN (sum(query1.treatment_dental) > 0) 
                               THEN sum(query1.treatment_dental) 
                               ELSE 0 
                      END AS treatment_dental 
FROM r_uc_plan_group
         LEFT JOIN  (SELECT 
                      CASE  WHEN ((r_plan_group.plan_group_number = '1' ) 
                               OR (r_plan_group.plan_group_number = '2' )) 
                               THEN  r_plan_group.plan_group_number 
                             WHEN (((r_plan_group.plan_group_number = '3' ) 
                               OR (r_plan_group.plan_group_number = '4' ) )
                               AND ((visit_payment_main_hospital IN (Select  hospital_incup_code 
                               FROM r_hospital_incup))  OR (visit_payment_sub_hospital IN 
                               (Select  hospital_incup_code from r_hospital_incup))) )
                               THEN '3' 
                             WHEN (((r_plan_group.plan_group_number = '3' ) 
                               OR (r_plan_group.plan_group_number = '4' ) )
                               AND ( ((t_visit_payment.visit_payment_main_hospital = '')
                               AND (t_visit_payment.visit_payment_sub_hospital = '')) 
                               OR ((t_visit_payment.visit_payment_main_hospital NOT IN (Select  hospital_incup_code
                               FROM r_hospital_incup))  AND (visit_payment_sub_hospital NOT IN 
                               (Select  hospital_incup_code from r_hospital_incup))) ))
                               THEN '4' 
                             WHEN ((r_plan_group.plan_group_number <> '1' ) 
                               AND (r_plan_group.plan_group_number <> '2' )
                               AND (r_plan_group.plan_group_number <> '3' ) 
                               AND (r_plan_group.plan_group_number <> '4' ) )
                               THEN  '5'              
                             END AS plan_group_id 
                      ,t_patient.t_patient_id   
                      , CASE WHEN ((substring(t_diag_icd10.diag_icd10_number,1,3) >= 'Z32'
                          AND substring(t_diag_icd10.diag_icd10_number,1,3) <= 'Z36'))
                          THEN 1                          ELSE 0
                          END AS BeforeMch                    
                      , sum(CASE WHEN ((substring(t_diag_icd10.diag_icd10_number,1,3) >= 'Z32'
                          AND substring(t_diag_icd10.diag_icd10_number,1,3) <= 'Z36')) 
                          THEN 1                          ELSE 0
                          END) AS BeforeMch_visit 
                      , CASE WHEN (substring(t_diag_icd10.diag_icd10_number,1,3) = 'Z39')
                          THEN 1                          ELSE 0
                          END AS AfterMch  
                      , sum(CASE WHEN (substring(t_diag_icd10.diag_icd10_number,1,3) = 'Z39')
                            THEN 1                        ELSE 0 
                            END) AS AfterMch_visit  
                      , CASE WHEN (substring(t_diag_icd10.diag_icd10_number,1,3) = 'Z38')
                          THEN 1                      ELSE 0
                          END AS BirthUsualInfant   
                      , CASE WHEN ((substring(t_diag_icd10.diag_icd10_number,1,3) >= 'P00'
                            AND substring(t_diag_icd10.diag_icd10_number,1,3) <= 'P15'))
                          THEN 1                       ELSE 0  
                          END AS BirthUnUsualInfant  
                      , CASE WHEN ((substring(t_diag_icd10.diag_icd10_number,1,3) >= 'K00'
                            AND substring(t_diag_icd10.diag_icd10_number,1,3) <= 'K14')) 
                              THEN 1                           ELSE 0 
                              END AS treatment_dental 
                FROM  t_diag_icd10  
                    LEFT JOIN t_visit ON  t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id
                    INNER JOIN t_patient ON (t_visit.t_patient_id = t_patient.t_patient_id 
                         AND t_diag_icd10.f_diag_icd10_type_id = '1'
                         AND t_diag_icd10.diag_icd10_active = '1' )
                    INNER JOIN t_visit_payment ON (t_visit.t_visit_id = t_visit_payment.t_visit_id
                        AND t_visit_payment.visit_payment_priority = '0'
                        AND t_visit_payment.visit_payment_active = '1')
                    INNER JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id
                    LEFT JOIN r_plan_group_map_pttype ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype
                    LEFT JOIN r_plan_group ON r_plan_group.r_plan_group_id = r_plan_group_map_pttype.r_plan_group_id   
                
                WHERE  t_visit.f_visit_status_id <> '4' 
                      AND SUBSTRING(t_visit.visit_financial_discharge_time,1,10)  Between ? AND ?
                GROUP BY  plan_group_id  
                        ,t_patient.t_patient_id  
                        ,BeforeMch  
                        ,AfterMch  
                        ,BirthUsualInfant     
                        ,BirthUnUsualInfant  
                        ,treatment_dental 
                 ORDER BY  plan_group_id 
            ) AS query1 ON query1.plan_group_id = r_uc_plan_group.uc_plan_group_number  

GROUP BY        r_uc_plan_group.uc_plan_group_description 
                      ,r_uc_plan_group.uc_plan_group_number 
ORDER BY        r_uc_plan_group.uc_plan_group_number



