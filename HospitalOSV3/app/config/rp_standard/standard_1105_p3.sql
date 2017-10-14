select  r_uc_plan_group.uc_plan_group_description  
    ,wellBaby,DPT,BCG  ,MMR  ,Hep  ,baby_fail  ,baby_exceed,baby
    ,healthy614,measles ,child_fail ,child_exceed,child
    ,healthy15up ,man_fail ,man_exceed

from r_uc_plan_group 
-------------------------------------------------------------------------------
LEFT JOIN(
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
                    and (substring(t_visit.visit_begin_visit_time,1,10)  
                        BETWEEN '2550-06-01' and '2550-07-01')
                group by  t_patient.t_patient_id 
                    ,t_visit.t_visit_id 
                    ,plan_group_id 
                    ,baby 
                    ,child 
            ) AS query1   ON query1.plan_group_id = r_uc_plan_group.uc_plan_group_number 

GROUP BY   r_uc_plan_group.uc_plan_group_description  
        ,r_uc_plan_group.uc_plan_group_number  
ORDER BY   r_uc_plan_group.uc_plan_group_number  
)as dent on r_uc_plan_group.uc_plan_group_description = dent.uc_plan_group_description
-------------------------------------------------------------------------------
LEFT JOIN(
 SELECT r_uc_plan_group.uc_plan_group_description 
        ,case when sum(query1.DPT) IS NOT NULL
		then sum(query1.DPT) 
		else 0 
	end as DPT  
        ,case when sum(query1.BCG) IS NOT NULL 
		then sum(query1.BCG) 
		else 0 
	end as BCG  
        ,case when sum(query1.MMR) IS NOT NULL
		then sum(query1.MMR) 
		else 0 
	end as MMR  
        ,case when sum(query1.Hep) IS NOT NULL 
		then sum(query1.Hep) 
		else 0 
	end as Hep  
FROM   r_uc_plan_group 
        LEFT JOIN   (select  
                distinct         t_health_family.patient_hn 
                , t_health_family.patient_name 
                , t_health_family.patient_last_name 
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
                , r_epi_group.epi_group_number 
                , case when r_epi_group.epi_group_number = 'DPT' 
                        then 1                 else 0                   end AS DPT 
                 , case when r_epi_group.epi_group_number = 'BCG' 
                        then 1                 else 0                   end AS BCG 
                 , case when r_epi_group.epi_group_number = 'MMR' 
                        then 1                 else 0                   end AS MMR 
                 , case when r_epi_group.epi_group_number = 'Hep.B' 
                        then 1         else 0                  end AS Hep 
                from   t_health_epi
                    INNER JOIN t_health_family  ON t_health_family.t_health_family_id = t_health_epi.t_health_family_id 
                    LEFT JOIN t_patient_payment   ON (t_health_family.t_health_family_id = t_patient_payment.t_health_family_id  
                            and t_patient_payment.patient_payment_priority = '0'  )
                    LEFT JOIN b_contract_plans   ON t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id 
                    LEFT JOIN t_health_epi_detail  ON (t_health_epi.t_health_epi_id = t_health_epi_detail.t_health_epi_id 
                            AND t_health_epi_detail.health_epi_detail_active = '1' )
                    LEFT JOIN r_plan_group_map_pttype  ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype 
                    LEFT JOIN r_plan_group ON r_plan_group_map_pttype.r_plan_group_id = r_plan_group.r_plan_group_id 
                    LEFT JOIN r_epi_group_item  ON t_health_epi_detail.b_health_epi_set_id = r_epi_group_item.b_health_epi_group_id 
                    LEFT JOIN r_epi_group  ON r_epi_group_item.r_epi_group_id = r_epi_group.r_epi_group_id 
                where  t_health_epi.health_epi_active = '1' 
                    and (substring(t_health_epi.record_date_time,1,10) 
                        BETWEEN '2550-06-01' and '2550-07-01')
            ) AS query1  ON query1.plan_group_id = r_uc_plan_group.uc_plan_group_number 
GROUP BY   
        r_uc_plan_group.uc_plan_group_description  
        ,r_uc_plan_group.uc_plan_group_number  
ORDER BY   
        r_uc_plan_group.uc_plan_group_number  
)as epi on r_uc_plan_group.uc_plan_group_description = epi.uc_plan_group_description
-------------------------------------------------------------------------------
LEFT JOIN(
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
                INNER JOIN r_healthy_group_survey         ON t_health_check_healthy.b_health_survey_id = r_healthy_group_survey.b_health_survey_id 
                INNER JOIN r_healthy_subgroup         ON r_healthy_group_survey.r_healthy_subgroup_id = r_healthy_subgroup.r_healthy_subgroup_id 
                INNER JOIN r_healthy_group         ON r_healthy_subgroup.r_healthy_group_id = r_healthy_group.r_healthy_group_id 
        WHERE  t_health_check_healthy.health_check_healthy_active = '1'  
                AND t_patient_payment.patient_payment_priority = '0' 
                AND (substring(t_health_check_healthy.health_check_healthy_date,1,10)   
                        BETWEEN '2550-06-01' and '2550-07-01')
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
)as healthy on r_uc_plan_group.uc_plan_group_description = healthy.uc_plan_group_description
-------------------------------------------------------------------------------
LEFT JOIN(
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
                 and (substring(t_health_epi.record_date_time,1,10)   
                        between '2550-06-01' and '2550-07-01')
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
)as mmr on r_uc_plan_group.uc_plan_group_description = mmr.uc_plan_group_description
-------------------------------------------------------------------------------
LEFT JOIN(
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
                    (to_number(t_health_nutrition.health_nutrition_bmi,'999.99') < 18.5)  
                    ) 
                then 1                 else 0                  end  AS man_fail   
            ,case when  ((to_number((substring(' ' || age(to_date(substring(t_health_nutrition.record_date_time,0,11)  
                 ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') ),'999') > 15) 
                  AND  
                    (to_number(t_health_nutrition.health_nutrition_bmi,'999.99') > 24.9)  
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
                AND (substring(t_health_nutrition.record_date_time,1,10)  
                        BETWEEN '2550-06-01' and '2550-07-01')
     
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
)as nutri on r_uc_plan_group.uc_plan_group_description = nutri.uc_plan_group_description
-------------------------------------------------------------------------------


