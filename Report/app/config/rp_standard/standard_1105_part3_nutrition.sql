 SELECT r_uc_plan_group.uc_plan_group_description  
        , case when (sum(baby_fail) IS NOT NULL) 
        	then sum(baby_fail)  
        	else 0         end as baby_fail  
        , case when (sum(baby_exceed) IS NOT NULL)  
        	then sum(baby_exceed)  
        	else 0         end as baby_exceed  
        , case when (sum(child_fail) IS NOT NULL)  
        	then sum(child_fail)  
        	else 0         end as child_fail 
        , case when (sum(child_exceed) IS NOT NULL) 
        	then sum(child_exceed)  
        	else 0          end as child_exceed 
        , case when (sum(man_fail) IS NOT NULL)  
        	then sum(man_fail)  
        	else 0          end as man_fail 
        , case when (sum(man_exceed) IS NOT NULL) 
        	then sum(man_exceed)  
        	else 0  
        end as man_exceed 
FROM         r_uc_plan_group 
        LEFT JOIN (select        distinct       t_health_family.t_health_family_id 
            , t_health_family.patient_name 
            , t_health_family.patient_last_name 
            ,CASE  WHEN ((r_plan_group.plan_group_number = '1' )
                        OR (r_plan_group.plan_group_number = '2' ) )  
                        THEN  r_plan_group.plan_group_number  
                WHEN (((r_plan_group.plan_group_number = '3' ) 
                        OR (r_plan_group.plan_group_number = '4' ) ) 
                        AND ((t_patient_payment.patient_payment_main_hospital IN (
                            Select  hospital_incup_code   from r_hospital_incup))  
                        OR (t_patient_payment.patient_payment_sub_hospital IN  (
                            Select  hospital_incup_code from r_hospital_incup))) ) 
                        THEN '3'  
                WHEN (((r_plan_group.plan_group_number = '3' ) 
                        OR (r_plan_group.plan_group_number = '4' ) ) 
                        AND ( ((t_patient_payment.patient_payment_main_hospital = '')  
                        AND (t_patient_payment.patient_payment_sub_hospital = ''))  
                        OR ((t_patient_payment.patient_payment_main_hospital NOT IN (
                                Select  hospital_incup_code  from r_hospital_incup))  
                        AND (t_patient_payment.patient_payment_sub_hospital NOT IN  (
                                Select  hospital_incup_code from r_hospital_incup))) )) 
                        THEN '4'  
                WHEN ((r_plan_group.plan_group_number <> '1' ) 
                        AND (r_plan_group.plan_group_number <> '2' )  
                        AND (r_plan_group.plan_group_number <> '3' ) 
                        AND (r_plan_group.plan_group_number <> '4' ) ) 
                        THEN  '5'  
                END AS plan_group_id 
            ,case when (((to_number((substring(' ' || age(to_date(substring(t_health_nutrition.record_date_time,0,11)  
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') IS NULL) 
                OR ((to_number((substring(' ' || age(to_date(substring(t_health_nutrition.record_date_time,0,11)  
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') >= 0) 
                      AND (to_number((substring(' ' || age(to_date(substring(t_health_nutrition.record_date_time,0,11)  
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') <=5)))  
                    AND  
                     ( t_health_nutrition.f_health_nutrition_level_id = '1'  
                    OR t_health_nutrition.f_health_nutrition_level_id = '2'  
                    OR t_health_nutrition.f_health_nutrition_level_id = '3'  
                    OR t_health_nutrition.f_health_nutrition_level_id = '4' 
                    OR t_health_nutrition.f_health_nutrition_level_id = '11' 
                    OR t_health_nutrition.f_health_nutrition_level_id = '12' ) 
                    ) 
                then 1                 else 0                  end AS baby_fail   
           , case when (((to_number((substring(' ' || age(to_date(substring(t_health_nutrition.record_date_time,0,11)  
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') IS NULL) 
                OR ((to_number((substring(' ' || age(to_date(substring(t_health_nutrition.record_date_time,0,11)  
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') >= 0) 
                      AND (to_number((substring(' ' || age(to_date(substring(t_health_nutrition.record_date_time,0,11)  
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') <=5) 
                )) AND  
                     ( t_health_nutrition.f_health_nutrition_level_id  = '5'  
                       OR  t_health_nutrition.f_health_nutrition_level_id = '6'  
                       OR  t_health_nutrition.f_health_nutrition_level_id = '7'  
                       OR  t_health_nutrition.f_health_nutrition_level_id = '08' 
                       OR  t_health_nutrition.f_health_nutrition_level_id = '09')  
                    ) 
                then 1                 else 0                  end AS baby_exceed  
           , case when (((to_number((substring(' ' || age(to_date(substring(t_health_nutrition.record_date_time,0,11)  
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') >= 6) 
                      AND (to_number((substring(' ' || age(to_date(substring(t_health_nutrition.record_date_time,0,11)  
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') <=14) 
                ) AND  
                     ( t_health_nutrition.f_health_nutrition_level_id = '1'  
                    OR t_health_nutrition.f_health_nutrition_level_id = '2'  
                    OR t_health_nutrition.f_health_nutrition_level_id = '3'  
                    OR t_health_nutrition.f_health_nutrition_level_id = '4' 
                    OR t_health_nutrition.f_health_nutrition_level_id = '11' 
                    OR t_health_nutrition.f_health_nutrition_level_id = '12' ) 
                    ) 
                then 1                 else 0                  end  AS child_fail  
            ,case when (((to_number((substring(' ' || age(to_date(substring(t_health_nutrition.record_date_time,0,11)  
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') >= 6) 
                      AND (to_number((substring(' ' || age(to_date(substring(t_health_nutrition.record_date_time,0,11)  
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') <=14) 
                ) AND  
                     ( t_health_nutrition.f_health_nutrition_level_id  = '5'  
                       OR  t_health_nutrition.f_health_nutrition_level_id = '6'  
                       OR  t_health_nutrition.f_health_nutrition_level_id = '7'  
                       OR  t_health_nutrition.f_health_nutrition_level_id = '08' 
                       OR  t_health_nutrition.f_health_nutrition_level_id = '09')  
                    ) 
                then 1                 else 0                  end  AS child_exceed  
            ,case when  ((to_number((substring(' ' || age(to_date(substring(t_health_nutrition.record_date_time,0,11)  
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') > 15) 
                 AND  
                    (to_number(t_health_nutrition.health_nutrition_bmi,'99.99') < 18.5)  
                    ) 
                then 1                 else 0                  end  AS man_fail   
            ,case when  ((to_number((substring(' ' || age(to_date(substring(t_health_nutrition.record_date_time,0,11)  
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') > 15) 
                  AND  
                    (to_number(t_health_nutrition.health_nutrition_bmi,'99.99') > 24.9)  
                    ) 
                then 1                 else 0                  end  AS man_exceed  
        from     t_health_nutrition 
                INNER JOIN t_health_family      ON t_health_family.t_health_family_id = t_health_nutrition.t_health_family_id 
                LEFT JOIN t_patient_payment          	ON (t_health_family.t_health_family_id = t_patient_payment.t_health_family_id 
                        AND t_patient_payment.patient_payment_priority = '0') 
                LEFT JOIN b_contract_plans          	ON t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id	 
                LEFT JOIN r_plan_group_map_pttype         	ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype 
                LEFT JOIN r_plan_group         	ON r_plan_group_map_pttype.r_plan_group_id = r_plan_group.r_plan_group_id 
        where       t_health_nutrition.health_nutrition_active = '1'
                AND (substring(t_health_nutrition.record_date_time,1,10) Between  ? and ?)
     
        group by   t_health_family.t_health_family_id 
                , t_health_family.patient_name 
                , t_health_family.patient_last_name 
                , plan_group_id 
                , baby_fail 
                , baby_exceed 
                , child_fail 
                , child_exceed 
                , man_fail 
                , man_exceed
        ) AS query1    ON query1.plan_group_id = r_uc_plan_group.uc_plan_group_number 

GROUP BY   r_uc_plan_group.uc_plan_group_description  
        ,r_uc_plan_group.uc_plan_group_number  
ORDER BY   r_uc_plan_group.uc_plan_group_number   
