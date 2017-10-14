SELECT 
	r_uc_plan_group.uc_plan_group_description  	
	, sum(case 
            when ((wellBaby_space = 34) AND (wellBaby_34_w01 + wellBaby_34_w02 + wellBaby_34_w03) >= 3)
                then 1 
            when ((wellBaby_space = 3)  AND (wellBaby_3_w01 +  wellBaby_3_w02 +  wellBaby_3_w03 + wellBaby_3_w04) >= 4) 
                then 1 
            when ((wellBaby_space = 45) AND (wellBaby_45_w01+ wellBaby_45_w02 + wellBaby_45_w03 + wellBaby_45_w05) >= 4) 
                then 1 
            else 0 end) AS wellBaby 
	, sum(case 
            when (wellBaby_space = 614) AND ((healthy614_h01_614 + healthy614_h02_614 + healthy614_h03_614) >= 3 ) 
                then  1 else 0 	end) AS healthy614 
	, sum(case 
            when (wellBaby_space = 15) AND ((healthy15up_h01_15 + healthy15up_h02_15 + healthy15up_h03_15 + healthy15up_h04_15) >= 4) 
                then 1   else 0   end) AS healthy15up 
FROM  r_uc_plan_group
        LEFT JOIN  (SELECT 	t_health_family.t_health_family_id 
                , t_health_family.patient_name 
                , t_health_family.patient_last_name 
                ,CASE  WHEN ((r_plan_group.plan_group_number = '1' )  OR (r_plan_group.plan_group_number = '2' ) )  
                            THEN  r_plan_group.plan_group_number 
                    WHEN (((r_plan_group.plan_group_number = '3' ) OR (r_plan_group.plan_group_number = '4' ) ) 
                            AND ((t_patient_payment.patient_payment_main_hospital IN (
                                Select  hospital_incup_code from r_hospital_incup))  
                            OR (t_patient_payment.patient_payment_sub_hospital IN (
                                Select  hospital_incup_code from r_hospital_incup))) ) 
                            THEN '3' 
                    WHEN (((r_plan_group.plan_group_number = '3' ) OR (r_plan_group.plan_group_number = '4' ) ) 
                            AND ( ((t_patient_payment.patient_payment_main_hospital = '') 
                            AND (t_patient_payment.patient_payment_sub_hospital = '')) 
                            OR ((t_patient_payment.patient_payment_main_hospital NOT IN (
                                    Select  hospital_incup_code from r_hospital_incup))  
                            AND (t_patient_payment.patient_payment_sub_hospital NOT IN (
                                    Select  hospital_incup_code from r_hospital_incup))) )) 
                            THEN '4' 
                    WHEN ((r_plan_group.plan_group_number <> '1' ) AND (r_plan_group.plan_group_number <> '2' ) 
                            AND (r_plan_group.plan_group_number <> '3' ) AND (r_plan_group.plan_group_number <> '4' ) ) 
                            THEN  '5' 
                    END AS plan_group_id	
                ,sum(case when ((r_healthy_group.healthy_group_number = 'WellBaby') 
                            AND (r_healthy_group_survey.b_health_survey_id IN (
                                select r_healthy_group_survey.b_health_survey_id from r_healthy_group_survey 
                                inner join r_healthy_subgroup on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                                where r_healthy_subgroup.healthy_subgroup_number = 'w01')) 
                                    AND ( (to_number((substring(' ' || 
                                        age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) ,'YYYY-MM-DD')
                                            , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) = 3) 
                                ) 
                            then 1  else 0 
                    end) AS wellBaby_34_w01 
                , sum(case when ((r_healthy_group.healthy_group_number = 'WellBaby') 
                            AND (r_healthy_group_survey.b_health_survey_id IN (
                                select r_healthy_group_survey.b_health_survey_id 
                                from r_healthy_group_survey 
                                inner join r_healthy_subgroup on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                                where r_healthy_subgroup.healthy_subgroup_number = 'w02')) 
                                    AND ( (to_number((substring(' ' || 
                                    age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) ,'YYYY-MM-DD')
                                    , to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) = 3) 
                                ) 
                            then 1        else 0 
                    end) AS wellBaby_34_w02 
        	, sum(case when ((r_healthy_group.healthy_group_number = 'WellBaby') 
                            AND (r_healthy_group_survey.b_health_survey_id IN (
                                select r_healthy_group_survey.b_health_survey_id 
                                from r_healthy_group_survey 
                                inner join r_healthy_subgroup on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                                where r_healthy_subgroup.healthy_subgroup_number = 'w03')) 
                                    AND ( (to_number((substring(' ' || 
                                    age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10),'YYYY-MM-DD')
                                    ,to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) = 3) 
                                )    
                            then 1         else 0 
                    end) AS wellBaby_34_w03 
        	, sum(case when ((r_healthy_group.healthy_group_number = 'WellBaby') 
                AND (r_healthy_group_survey.b_health_survey_id IN (select r_healthy_group_survey.b_health_survey_id 
                    from r_healthy_group_survey inner join r_healthy_subgroup 
                    on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                    where r_healthy_subgroup.healthy_subgroup_number = 'w01')) 
                AND ( ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                                      ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) IS NULL) 
                    OR 
                    (( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                                      ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 0) 
                        AND 
                    ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                                      ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) < 3)) 
                     ) 
                ) 
                then 1                 else 0                     end) AS wellBaby_3_w01 
            , sum(case when ((r_healthy_group.healthy_group_number = 'WellBaby') 
                AND (r_healthy_group_survey.b_health_survey_id IN (select r_healthy_group_survey.b_health_survey_id 
                    from r_healthy_group_survey inner join r_healthy_subgroup 
                    on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                    where r_healthy_subgroup.healthy_subgroup_number = 'w02')) 
                AND ( ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) IS NULL) 
                    OR 
                    (( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                   ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 0) 
                AND  
                ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) < 3)) 
                      )                 ) 
                then 1                 else 0                     end) AS wellBaby_3_w02 
            , sum(case when ((r_healthy_group.healthy_group_number = 'WellBaby') 
                AND (r_healthy_group_survey.b_health_survey_id IN (select r_healthy_group_survey.b_health_survey_id 
                    from r_healthy_group_survey inner join r_healthy_subgroup 
                    on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                    where r_healthy_subgroup.healthy_subgroup_number = 'w03')) 
                AND ( ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) IS NULL) 
                    OR 
                    (( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                                      ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 0) 
                        AND 
                    ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                                      ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) < 3)) 
                      ) ) 
                then 1                 else 0                     end) AS wellBaby_3_w03 
           , sum(case when ((r_healthy_group.healthy_group_number = 'WellBaby') 
                AND (r_healthy_group_survey.b_health_survey_id IN (select r_healthy_group_survey.b_health_survey_id 
                    from r_healthy_group_survey inner join r_healthy_subgroup 
                    on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                    where r_healthy_subgroup.healthy_subgroup_number = 'w04')) 
                AND ( ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) IS NULL) 
                    OR 
                    (( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10)  
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 0) 
                        AND 
                    ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                                      ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) < 3)) 
                      ) 
                ) 
                then 1                else 0                     end) AS wellBaby_3_w04 
          , sum(case when ((r_healthy_group.healthy_group_number = 'WellBaby') 
                AND (r_healthy_group_survey.b_health_survey_id IN (select r_healthy_group_survey.b_health_survey_id 
                    from r_healthy_group_survey inner join r_healthy_subgroup 
                    on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                    where r_healthy_subgroup.healthy_subgroup_number = 'w01')) 
                AND (( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 4) 
                        AND 
                    ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) <= 5)) 
                ) 
                then 1                 else 0                     end)  AS wellBaby_45_w01 
          , sum(case when ((r_healthy_group.healthy_group_number = 'WellBaby') 
                AND (r_healthy_group_survey.b_health_survey_id IN (select r_healthy_group_survey.b_health_survey_id 
                    from r_healthy_group_survey inner join r_healthy_subgroup 
                    on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                    where r_healthy_subgroup.healthy_subgroup_number = 'w02')) 
                AND (( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                                      ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 4) 
                        AND 
                    ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) <= 5)) 
                ) 
                then 1                 else 0                    end)  AS wellBaby_45_w02 
         , sum(case when ((r_healthy_group.healthy_group_number = 'WellBaby') 
                AND (r_healthy_group_survey.b_health_survey_id IN (select r_healthy_group_survey.b_health_survey_id 
                    from r_healthy_group_survey inner join r_healthy_subgroup 
                    on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                    where r_healthy_subgroup.healthy_subgroup_number = 'w03')) 
                AND (( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 4) 
                        AND 
                    ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) <= 5)) 
                ) 
                then 1                 else 0                 end)  AS wellBaby_45_w03 
          , sum(case when ((r_healthy_group.healthy_group_number = 'WellBaby') 
                AND (r_healthy_group_survey.b_health_survey_id IN (select r_healthy_group_survey.b_health_survey_id 
                    from r_healthy_group_survey inner join r_healthy_subgroup 
                    on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                    where r_healthy_subgroup.healthy_subgroup_number = 'w05')) 
                AND (( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 4) 
                AND 
                    ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10)  
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) <= 5)) 
                ) 
                then 1                 else 0                     end)  AS wellBaby_45_w05 
          , case when r_healthy_group.healthy_group_number = 'WellBaby' 
                    and (( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                   ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 4) 
                        AND 
                    ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) <= 5)) 
                then  45 
                when r_healthy_group.healthy_group_number = 'WellBaby' 
                    and ( ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) IS NULL) 
                    OR 
                    (( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 0) 
                        AND 
                    ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) < 3)) 
                      ) 
                then 3 
                when r_healthy_group.healthy_group_number = 'WellBaby' 
                    and ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) = 3) 
                then 34 
                when r_healthy_group.healthy_group_number = 'Healthy(6-14)' 
                then 614  
                when r_healthy_group.healthy_group_number = 'Healthy(15Up)' 
                then 15                 else 0                     end AS wellBaby_space 
         , sum(case when  ((r_healthy_group.healthy_group_number = 'Healthy(6-14)') 
                AND (r_healthy_group_survey.b_health_survey_id IN (select r_healthy_group_survey.b_health_survey_id 
                    from r_healthy_group_survey inner join r_healthy_subgroup 
                    on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                    where r_healthy_subgroup.healthy_subgroup_number = 'h01_614')) 
                AND (( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 6) 
                AND 
                    ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) <= 14)) 
                ) 
                then 1                 else 0                 end) AS  healthy614_h01_614 
          , sum(case when  ((r_healthy_group.healthy_group_number = 'Healthy(6-14)') 
                AND (r_healthy_group_survey.b_health_survey_id IN (select r_healthy_group_survey.b_health_survey_id 
                    from r_healthy_group_survey inner join r_healthy_subgroup 
                    on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                    where r_healthy_subgroup.healthy_subgroup_number = 'h02_614')) 
                AND (( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10)  
                   ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 6) 
                        AND 
                    ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) <= 14)) 
                ) 
                then 1                 else 0                     end) AS  healthy614_h02_614 
          , sum(case when  ((r_healthy_group.healthy_group_number = 'Healthy(6-14)') 
                AND (r_healthy_group_survey.b_health_survey_id IN (select r_healthy_group_survey.b_health_survey_id 
                    from r_healthy_group_survey inner join r_healthy_subgroup 
                    on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                    where r_healthy_subgroup.healthy_subgroup_number = 'h03_614')) 
                AND (( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 6) 
                 AND  
                    ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) <= 14)) 
                ) 
                then 1                 else 0                 end) AS  healthy614_h03_614 
         , sum(case when  ((r_healthy_group.healthy_group_number = 'Healthy(15Up)') 
                AND (r_healthy_group_survey.b_health_survey_id IN (select r_healthy_group_survey.b_health_survey_id 
                    from r_healthy_group_survey inner join r_healthy_subgroup 
                    on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                    where r_healthy_subgroup.healthy_subgroup_number = 'h01_15')) 
                AND ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 15) 
                ) 
                then 1                 else 0                 end) AS healthy15up_h01_15 
         , sum(case when  ((r_healthy_group.healthy_group_number = 'Healthy(15Up)') 
                AND (r_healthy_group_survey.b_health_survey_id IN (select r_healthy_group_survey.b_health_survey_id 
                    from r_healthy_group_survey inner join r_healthy_subgroup 
                    on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                    where r_healthy_subgroup.healthy_subgroup_number = 'h02_15')) 
                AND ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 15) 
                ) 
                then 1                 else 0                     end) AS healthy15up_h02_15 
         , sum(case when  ((r_healthy_group.healthy_group_number = 'Healthy(15Up)') 
                AND (r_healthy_group_survey.b_health_survey_id IN (select r_healthy_group_survey.b_health_survey_id 
                    from r_healthy_group_survey inner join r_healthy_subgroup 
                    on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                    where r_healthy_subgroup.healthy_subgroup_number = 'h03_15')) 
                AND ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 15) 
                ) 
                then 1                 else 0                 end) AS healthy15up_h03_15 
         , sum(case when  ((r_healthy_group.healthy_group_number = 'Healthy(15Up)') 
                AND (r_healthy_group_survey.b_health_survey_id IN (select r_healthy_group_survey.b_health_survey_id 
                    from r_healthy_group_survey inner join r_healthy_subgroup 
                    on r_healthy_subgroup.r_healthy_subgroup_id = r_healthy_group_survey.r_healthy_subgroup_id 
                    where r_healthy_subgroup.healthy_subgroup_number = 'h04_15')) 
                AND ( (to_number((substring(' ' || age(to_date(substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                  ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999')) >= 15) 
                ) 
                then 1                 else 0                 end) AS healthy15up_h04_15 
        FROM t_health_check_healthy 
                INNER JOIN  t_health_family       ON t_health_family.t_health_family_id = t_health_check_healthy.t_health_family_id 
                INNER JOIN t_patient_payment         ON t_health_family.t_health_family_id = t_patient_payment.t_health_family_id 
                INNER JOIN b_contract_plans         ON t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id 
                INNER JOIN r_plan_group_map_pttype         ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype 
                INNER JOIN r_plan_group         ON r_plan_group_map_pttype.r_plan_group_id = r_plan_group.r_plan_group_id 
                inner JOIN r_healthy_group_survey ON substr(t_health_check_healthy.b_health_survey_id,12,2) = substr(r_healthy_group_survey.b_health_survey_id ,17,2)
                INNER JOIN r_healthy_subgroup         ON r_healthy_group_survey.r_healthy_subgroup_id = r_healthy_subgroup.r_healthy_subgroup_id 
                INNER JOIN r_healthy_group         ON r_healthy_subgroup.r_healthy_group_id = r_healthy_group.r_healthy_group_id 
        WHERE  t_health_check_healthy.health_check_healthy_active = '1'  
                AND t_patient_payment.patient_payment_priority = '0' 
                AND substring(t_health_check_healthy.health_check_healthy_date,1,10) 
                    Between  ? and ?
        GROUP BY t_health_family.t_health_family_id 
                , t_health_family.patient_name 
                , t_health_family.patient_last_name 
                , t_health_check_healthy.health_check_healthy_date 
                , plan_group_id 
                , wellBaby_space 
        ) AS query1 ON query1.plan_group_id = r_uc_plan_group.uc_plan_group_number 
GROUP BY r_uc_plan_group.uc_plan_group_description 
        ,r_uc_plan_group.uc_plan_group_number 
ORDER BY r_uc_plan_group.uc_plan_group_number 
