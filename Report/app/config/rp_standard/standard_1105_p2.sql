select r_uc_plan_group.uc_plan_group_description 
    ,abort_ch,BirthAndDeath,Baby7DayDeath,MotherDeath,IPDDeath,OPDDeath,'-' as Lab
    ,BeforeMch,BeforeMch_visit,AfterMch,AfterMch_visit,BirthUsualInfant,BirthUnUsualInfant
    ,childless_female,childless_male,childless_drug,childless_other,treatment_dental
from r_uc_plan_group
------------------------------------------------------------------------------------
LEFT JOIN(
SELECT 
          r_uc_plan_group.uc_plan_group_description 
          ,CASE WHEN (sum(query1.childless_female) > 0) 
                   THEN sum(query1.childless_female) 
                   ELSE 0 
          END AS childless_female 
          ,CASE WHEN (sum(query1.childless_male) > 0) 
                   THEN sum(query1.childless_male) 
                   ELSE 0 
          END AS childless_male 
          ,CASE WHEN (sum(query1.childless_drug) > 0) 
                   THEN sum(query1.childless_drug) 
                   ELSE 0 
          END AS childless_drug 
          ,CASE WHEN (sum(query1.childless_other) > 0) 
                   THEN sum(query1.childless_other) 
                   ELSE 0 
          END AS childless_other 
FROM r_uc_plan_group 
        LEFT JOIN (SELECT 
              CASE  WHEN ((r_plan_group.plan_group_number = '1' ) 
                        OR (r_plan_group.plan_group_number = '2' ) ) 
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
                        OR ((t_visit_payment.visit_payment_main_hospital NOT IN 
                        (Select  hospital_incup_code 
                        FROM r_hospital_incup))  AND (visit_payment_sub_hospital NOT IN 
                        (Select  hospital_incup_code from r_hospital_incup))) ))
                        THEN '4' 
                      WHEN ((r_plan_group.plan_group_number <> '1' ) 
                        AND (r_plan_group.plan_group_number <> '2' ) 
                        AND (r_plan_group.plan_group_number <> '3' ) 
                        AND (r_plan_group.plan_group_number <> '4' ) )
                        THEN  '5' 
                        END AS plan_group_id
             
               , CASE WHEN (t_health_family_planing.f_health_family_planing_method_id = '7')
                      THEN 1 
                      ELSE 0 
                      END AS  childless_female
                 , CASE WHEN (t_health_family_planing.f_health_family_planing_method_id = '6') 
                      THEN 1 
                      ELSE 0 
                      END AS  childless_male 
                 , CASE WHEN (t_health_family_planing.f_health_family_planing_method_id = '4') 
                      THEN 1 
                      ELSE 0 
                      END AS  childless_drug 
                 , CASE WHEN t_health_family_planing.f_health_family_planing_method_id <> '7'
                      AND t_health_family_planing.f_health_family_planing_method_id <> '6' 
                      AND t_health_family_planing.f_health_family_planing_method_id <> '4' 
                      THEN 1 
                      ELSE 0 
                      END AS  childless_other 
               FROM t_visit 
                    INNER JOIN t_health_family_planing ON t_visit.t_visit_id = t_health_family_planing.t_visit_id
                    INNER JOIN t_visit_payment ON t_visit.t_visit_id = t_visit_payment.t_visit_id
                    INNER JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id
                    LEFT JOIN r_plan_group_map_pttype ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype
                    LEFT JOIN r_plan_group ON r_plan_group.r_plan_group_id = r_plan_group_map_pttype.r_plan_group_id 
                WHERE  t_visit.f_visit_status_id <>'4'
                   AND t_visit_payment.visit_payment_priority='0' 
                   AND t_visit_payment.visit_payment_active='1'
                   AND (SUBSTRING(visit_begin_visit_time,1,10)
                        BETWEEN '2550-06-01' and '2550-07-01')
                   AND t_health_family_planing.health_family_planing_active='1'
        )  AS query1 ON query1.plan_group_id = r_uc_plan_group.uc_plan_group_number 

GROUP BY r_uc_plan_group.uc_plan_group_number 
       ,r_uc_plan_group.uc_plan_group_description 
ORDER BY r_uc_plan_group.uc_plan_group_number
) as fp on fp.uc_plan_group_description = r_uc_plan_group.uc_plan_group_description 
-------BeforeMch,BeforeMch_visit,AfterMch,AfterMch_visit,BirthUsualInfant,BirthUnUsualInfant,treatment_dental-----------------------------------------------------------------------------
LEFT JOIN (
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
                FROM  t_health_pp 
                    LEFT JOIN t_visit ON t_health_pp.t_visit_id = t_visit.t_visit_id
                    LEFT JOIN t_diag_icd10 ON (t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id
                         AND t_diag_icd10.f_diag_icd10_type_id = '1'
                         AND t_diag_icd10.diag_icd10_active = '1' )
                    LEFT JOIN t_patient ON (t_visit.t_patient_id = t_patient.t_patient_id )
                    LEFT JOIN t_visit_payment ON (t_visit.t_visit_id = t_visit_payment.t_visit_id
                        AND t_visit_payment.visit_payment_priority = '0'
                        AND t_visit_payment.visit_payment_active = '1')
                    LEFT JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id
                    LEFT JOIN r_plan_group_map_pttype ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype
                    LEFT JOIN r_plan_group ON r_plan_group.r_plan_group_id = r_plan_group_map_pttype.r_plan_group_id   
                
                WHERE  t_health_pp.pp_active = '1' 
                      AND t_visit.f_visit_status_id <> '4' 
                      AND (SUBSTRING(t_visit.visit_financial_discharge_time,1,10) 
                        BETWEEN '2550-06-01' and '2550-07-01')
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
) as post on post.uc_plan_group_description = r_uc_plan_group.uc_plan_group_description 
------abort_ch,BirthAndDeath,Baby7DayDeath,MotherDeath,IPDDeath,OPDDeath-----------------------------------------------------------------------------------
LEFT JOIN(
SELECT 
                      r_uc_plan_group.uc_plan_group_description 
                      ,CASE WHEN (sum(query1.abort_ch) > 0) 
                               THEN sum(query1.abort_ch) 
                               ELSE 0 
                      END AS abort_ch 
                      ,CASE WHEN (sum(query1.BirthAndDeath) > 0) 
                               THEN sum(query1.BirthAndDeath) 
                               ELSE 0 
                      END AS BirthAndDeath 
                      ,CASE WHEN (sum(query1.Baby7DayDeath) > 0) 
                               THEN sum(query1.Baby7DayDeath) 
                               ELSE 0 
                      END AS Baby7DayDeath 
                      ,CASE WHEN (sum(query1.MotherDeath) > 0) 
                               THEN sum(query1.MotherDeath) 
                               ELSE 0 
                      END AS MotherDeath 
                      ,CASE WHEN (sum(query1.IPDDeath) > 0) 
                               THEN sum(query1.IPDDeath) 
                               ELSE 0 
                      END AS IPDDeath 
                      ,CASE WHEN (sum(query1.OPDDeath) > 0) 
                               THEN sum(query1.OPDDeath) 
                               ELSE 0 
                      END AS OPDDeath 
FROM  r_uc_plan_group 
        LEFT JOIN (SELECT 	
                      CASE WHEN ((r_plan_group.plan_group_number = '1' ) 
                                 OR (r_plan_group.plan_group_number = '2' )) 
                             THEN r_plan_group.plan_group_number 
                             WHEN (((r_plan_group.plan_group_number = '3' ) 
                                 OR (r_plan_group.plan_group_number = '4' )) 
                                 AND ((visit_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup)) 
                                 OR (visit_payment_sub_hospital IN (Select hospital_incup_code from r_hospital_incup))) ) 
                             THEN '3' 
                             WHEN (((r_plan_group.plan_group_number = '3' ) 
                                 OR (r_plan_group.plan_group_number = '4' )) 
                                 AND ( ((t_visit_payment.visit_payment_main_hospital = '')  
                                 AND (t_visit_payment.visit_payment_sub_hospital = '')) 
                                 OR ((t_visit_payment.visit_payment_main_hospital NOT IN (Select  hospital_incup_code 
                                 from r_hospital_incup))  AND (visit_payment_sub_hospital NOT IN 
                                 (Select  hospital_incup_code from r_hospital_incup))) )) 
                             THEN '4' 
                             WHEN ((r_plan_group.plan_group_number <> '1' ) AND (r_plan_group.plan_group_number <> '2' ) 
                                 AND (r_plan_group.plan_group_number <> '3' ) AND (r_plan_group.plan_group_number <> '4' ))    
                             THEN '5' 
                            END AS plan_group_id 
                      ,t_patient.t_patient_id 
                      ,sum(CASE WHEN (substring(t_diag_icd10.diag_icd10_number,1,3) >= 'O00' 
                          AND substring(t_diag_icd10.diag_icd10_number,1,3) <= 'O08') 
                          THEN 1                ELSE 0 
                          END) AS abort_ch 
                      , CASE WHEN (substring(t_diag_icd10.diag_icd10_number,1,3) = 'P95') 
                          THEN 1                       ELSE 0 
                          END AS BirthAndDeath   
                      ,CASE WHEN ((((t_visit.f_visit_opd_discharge_status_id = '52') 
                          OR (t_visit.f_visit_opd_discharge_status_id = '55')) 
                          OR (((t_visit.f_visit_ipd_discharge_type_id = '8') 
                          OR (t_visit.f_visit_ipd_discharge_type_id = '9')) 
                          OR ((t_visit.f_visit_ipd_discharge_status_id = '8') 
                          OR (t_visit.f_visit_ipd_discharge_status_id = '9')))) 
                          AND ((to_date(t_visit.visit_begin_visit_time,'yyyy-mm-dd') - to_date(t_patient.patient_birthday,'yyyy-mm-dd') ) <= 7)) 
                          THEN 1                           ELSE 0 
                          END AS Baby7DayDeath 
                      ,CASE WHEN ((((t_visit.f_visit_opd_discharge_status_id = '52') 
                          OR (t_visit.f_visit_opd_discharge_status_id = '55')) 
                          OR (((t_visit.f_visit_ipd_discharge_type_id = '8') 
                          OR (t_visit.f_visit_ipd_discharge_type_id = '9')) 
                          OR ((t_visit.f_visit_ipd_discharge_status_id = '8') 
                          OR (t_visit.f_visit_ipd_discharge_status_id = '9')))) 
                          AND (substring(t_diag_icd10.diag_icd10_number,1,3) >= 'O00' 
                          AND substring(t_diag_icd10.diag_icd10_number,1,3) <= 'O99')) 
                          THEN 1                           ELSE 0 
                          END AS MotherDeath 
                      , CASE WHEN ((t_visit.f_visit_type_id = '1')  
                          AND (substring(t_diag_icd10.diag_icd10_number,1,3) NOT IN ('O00','O01','O02','O03','O04','O05','O06','O07','O08')) )  
                          THEN 1                          ELSE 0 
                          END AS IPDDeath  
                      , CASE WHEN (t_visit.f_visit_type_id = '0'
                          AND (substring(t_diag_icd10.diag_icd10_number,1,3) NOT IN ('O00','O01','O02','O03','O04','O05','O06','O07','O08'))) 
                          THEN 1                            ELSE 0 
                          END AS OPDDeath  
                FROM t_death
                    LEFT JOIN t_visit ON  (t_death.t_visit_id  = t_visit.t_visit_id 
                            AND t_visit.f_visit_status_id <> '4' )
                    LEFT JOIN t_diag_icd10 ON (t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id 
                           AND t_diag_icd10.f_diag_icd10_type_id = '1' 
                           AND t_diag_icd10.diag_icd10_active = '1')
                    LEFT JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id
                    LEFT JOIN t_visit_payment ON (t_visit.t_visit_id = t_visit_payment.t_visit_id 
                           AND (t_visit_payment.visit_payment_priority = '0' )     
                           AND (t_visit_payment.visit_payment_active = '1' )   )
                    LEFT JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id
                    LEFT JOIN r_plan_group_map_pttype ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype
                    LEFT JOIN r_plan_group  ON r_plan_group.r_plan_group_id = r_plan_group_map_pttype.r_plan_group_id  
                WHERE  --(SUBSTRING(t_visit.visit_financial_discharge_time,1,10) 
                         --       BETWEEN '2550-06-01' and '2550-07-01')
                         (SUBSTRING(t_death.death_date_time,1,10) 
                                BETWEEN '2550-06-01' and '2550-07-01')
                GROUP BY   t_patient.t_patient_id 
                      ,plan_group_id  
                      ,BirthAndDeath  ,Baby7DayDeath  ,MotherDeath  ,IPDDeath  ,OPDDeath  
                ORDER BY  plan_group_id 
           ) AS query1 ON query1.plan_group_id = r_uc_plan_group.uc_plan_group_number 
GROUP BY        r_uc_plan_group.uc_plan_group_description 
                      ,r_uc_plan_group.uc_plan_group_number 
ORDER BY       r_uc_plan_group.uc_plan_group_number
) as pre on pre.uc_plan_group_description = r_uc_plan_group.uc_plan_group_description 
-----------------------------------------------------------------------------------------



