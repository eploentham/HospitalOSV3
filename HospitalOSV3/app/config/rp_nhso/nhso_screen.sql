SELECT distinct t_health_family.patient_pid as p_id
     , t_health_family.patient_name || ' ' || t_health_family.patient_last_name as name
     , t_health_family.patient_birthday as birthday
     , t_nhso_service.service_patient_age as age
     , t_patient.patient_house as house
     , t_patient.patient_road as road
     , f_address1.address_description as tambon
     , f_address2.address_description as amphur
     , f_address3.address_description  as province
     , t_patient.patient_phone_number as tel_number
     , t_patient.f_sex_id as sex
     , t_patient.f_patient_education_type_id as education
     , t_nhso_family.family_income as income
     , t_nhso_family.f_nhso_family_income_enough_id as income_enough
     , t_nhso_family.f_nhso_family_job_type_id as job_type
     , t_nhso_family.family_job_name as job_name
     , family_history.father_dm as father_dm
     , family_history.father_ht as father_ht
     , family_history.father_acs as father_acs
     , family_history.mother_dm as mother_dm
     , family_history.mother_ht as mother_ht
     , family_history.mother_acs as mother_acs
     , family_history.other_dm as other_dm
     , family_history.other_ht as other_ht
     , family_history.other_acs as other_acs
     , risk_factor.risk_01 as risk_test
     , risk_factor.risk_02 as risk_fat
     , risk_factor.risk_03 as risk_dm
     , risk_factor.risk_04 as risk_dt
     , risk_factor.risk_05 as risk_ht
     , risk_factor.risk_06 as risk_liver
     , risk_factor.f_nhso_medication_status_id as if_other
     , risk_factor.risk_factor_medication_name as if_durg_name
     , excercise.excercise_is_usually as excercise
     , excercise.frequency as excercise_frequency
     , excercise.PER_Week as excercise_per_week
     , excercise.time_period as excercise_time_period
     , excercise.minute_time as excercise_minute_time
     , excercise.excercise_run as excercise_run
     , excercise.excercise_walk as excercise_walk
     , excercise.excercise_other as excercise_other
     , excercise.excercise_name as excercise_name
     , food_taste.f_nhso_food_source_id as food_source
     , meat_01.f_nhso_food_behavior_frequency_id as meat_time
     , meat_01.meat as meat_description
     , coconut_02.f_nhso_food_behavior_frequency_id as coconut_time
     , coconut_02.coconut as coconut_description
     , food_taste.f_nhso_food_taste_id as taste
     , food_taste.food_taste_other as taste_other
     , fruit_03.f_nhso_food_behavior_frequency_id as fruit_time
     , fruit_03.fruit as fruit_description
     , bearkfast_04.f_nhso_food_behavior_frequency_id as breakfast_time
     , bearkfast_04.bearkfast as breakfast_description
     , fat_11.f_nhso_food_behavior_frequency_id as fat_frequency
     , homo_12.f_nhso_food_behavior_frequency_id as homo_frequency
     , dh_13.f_nhso_food_behavior_frequency_id as hypertension_frequency
     , dh_13.dh as hypertension_durg_name
     , cigar_01.f_nhso_drug_usual_status_id as cigar_status
     , cigar_01.dope_drug_first_age as cigar_first_age
     , cigar_01.dope_drug_frequency as cigar_frequency
     , cigar_01.dope_drug_number_year_take as cigar_year_take
     , cigar_01.dope_drug_number_year_stop as cigar_year_stop
     , drink_02.f_nhso_drug_usual_status_id as drink_status
     , drink_02.dope_drug_first_age as drink_first_age
     , drink_02.dope_drug_frequency as drink_frequency
     , drink_02.dope_drug_number_year_take as drink_year_take
     , drink_02.dope_drug_number_year_stop as drink_year_stop
     , case when(person_ht.ht = 'ãšÃéÍ¹')
        then '1'
        else '0' end as hot_heart
     , case when(person_ir.ir = 'Ë§ØŽË§ÔŽ§èÒÂ')
        then '1'
        else '0' end as  irritated
     , case when(person_serious.strain = 'àÍÒšÃÔ§àÍÒšÑ§')
        then '1'
        else '0' end as  serious
     , case when(person_afraid.af = 'ÃÙéÊÖ¡¡ÅÑÇ/ÇÔµ¡¡Ñ§ÇÅ')
        then '1'
        else '0' end as  afraid
     , case when(person_excite.ex = 'µ×è¹àµé¹§èÒÂ')
        then '1'
        else '0' end as  excite
     , case when(person_other.ot = 'Í×è¹æ')
        then '1'
        else '0' end as  other
     , person_other.strain_description as other_description
     , case when(job_hard.ha = '§Ò¹Ë¹Ñ¡')
        then '1'
        else '0' end as hard_working
     , case when(job_relex.re = 'äÁèÁÕàÇÅÒŸÑ¡ŒèÍ¹')
        then '1'
        else '0' end as  tired
     , case when(job_frindProblem.problem = 'àŸ×èÍ¹ÃèÇÁ§Ò¹ÁÕ»Ñ­ËÒ')
        then '1'
        else '0' end as  problem
     , case when(job_Workbad.bad = '·Ó§Ò¹äÁèäŽéŽÑè§ãš')
        then '1'
        else '0' end as  workbad
     , case when(job_fault.wrong = '¶Ù¡µÓË¹ÔºèÍÂ')
        then '1'
        else '0' end as  fault
     , case when(job_ground.gro = '§Ò¹ÃÒºÃ×è¹ŽÕäÁèÁÕ»Ñ­ËÒ')
        then '1'
        else '0' end as  good
     , case when(family_respon.ra = 'µéÍ§ÃÑºŒÔŽªÍºÁÒ¡')
        then '1'
        else '0' end as more_response
     , case when(family_relationbad.relat = 'ÊÑÁŸÑ¹žÀÒŸäÁè€èÍÂŽÕ')
        then '1'
        else '0' end as  relationbad
     , case when(family_nounder.gro = 'äÁè€èÍÂà¢éÒãš¡Ñ¹')
        then '1'
        else '0' end as  not_understand
     , case when(family_retreat.problem = 'µèÒ§€¹µèÒ§ÍÂÙè')
        then '1'
        else '0' end as  retreat
     , case when(family_costup.bad = '€èÒãªéšèÒÂÁÒ¡')
        then '1'
        else '0' end as  more_cost
     , case when(family_nohousekeeping.wrong = 'äÁèÁÕ€¹ªèÇÂ·Ó§Ò¹ºéÒ¹')
        then '1'
        else '0' end as  nohousekeeping
     , case when(family_noproblem.npro = 'à»ç¹ÊØ¢ŽÕ äÁèÁÕ»Ñ­ËÒ')
        then '1'
        else '0' end as  noproblem
     , case when(social_nointerest.interest = 'äÁè€èÍÂäŽéÊ¹ãš')
        then '1'
        else '0'end as  no_interest
     , case when(social_trouble.busy = 'ªèÒ§ÇØè¹ÇÒÂ ¹èÒàº×èÍ')
        then '1'
        else '0' end as  trouble
     , case when(social_goodluck.lucky = 'âª€ŽÕÁÕàŸ×èÍ¹Ê¹Ô·äÁèà€ÃÕÂŽ')
        then '1'
        else '0' end as  have_friend
     , case when(social_selfish.see = 'ÁÕáµè€¹àËç¹á¡èµÑÇàÍÒà»ÃÕÂº')
        then '1'
        else '0' end as  selfish
     , case when(social_normal.nor = '·ÓãšäŽéÊºÒÂæ')
        then '1'
        else '0' end as  happy
     , t_nhso_vital_sign.vital_sign_weight as weight
     , t_nhso_vital_sign.vital_sign_height as height
     , t_nhso_vital_sign.vital_sign_bmi as bmi
     , t_nhso_vital_sign.vital_sign_waist_inch as waist_inch
     , t_nhso_vital_sign.vital_sign_hip_inch as hip_inch
     , t_nhso_vital_sign.vital_sign_blood_sugar as blood_sugar
     , case when (t_nhso_doctor_conclusion.doctor_conclusion_in_risk_condition = '0')
       then '0'
       else '1' END as conclusion_risk
     , t_nhso_doctor_conclusion.doctor_conclusion_risk_topic as risk_topic
     , t_nhso_doctor_conclusion.doctor_conclusion_chronic_disease as chronic_disease
     , case when (t_nhso_doctor_conclusion.doctor_conclusion_is_change_behavior = '0')
       then '0'
       else '1' END as change_behavior
     , case when (t_nhso_cause_behavior.cause_behavior_used_to_take = '0')
       then '0'
       else '1' END as used_to_take
     , case when(t_nhso_cause_behavior.cause_behavior_take_time = '')
            then '0'
            else cast(t_nhso_cause_behavior.cause_behavior_take_time as int)end as total_behavior
     , t_nhso_cause_behavior.f_nhso_cause_behavior_frequency_id as behavior_frequency
     , t_nhso_cause_behavior.cause_behavior_frequency_description as behavior_description
     , t_nhso_cause_behavior.f_nhso_cause_behavior_feeling_id as feeling_description
     , t_nhso_cause_behavior.cause_behavior_feeling_description as behavior_feeling_description
     
FROM   t_nhso_service
     LEFT JOIN t_health_family ON t_nhso_service.t_health_family_id = t_health_family.t_health_family_id
     LEFT JOIN t_patient ON t_patient.t_health_family_id = t_health_family.t_health_family_id
     INNER JOIN t_visit ON (t_visit.t_patient_id = t_patient.t_patient_id and t_visit.f_visit_status_id <> '4')
     LEFT JOIN f_address as f_address1 ON f_address1.f_address_id = t_patient.patient_tambon 
     LEFT JOIN f_address as f_address2 ON f_address2.f_address_id = t_patient.patient_amphur 
     LEFT JOIN f_address as f_address3 ON f_address3.f_address_id = t_patient.patient_changwat  
     LEFT JOIN t_nhso_vital_sign  ON t_nhso_vital_sign.t_nhso_service_id = t_nhso_service.t_nhso_service_id 
     LEFT JOIN 
          (select father.t_nhso_service_id,father.t_health_family_id
                      ,max(case when (father.f_nhso_relate_person_id = '01' and father.f_nhso_disease_group_id = '01')
                            then father.f_nhso_disease_status_id
                            else '0' end) as father_dm
                      ,max(case when (father.f_nhso_relate_person_id = '01' and father.f_nhso_disease_group_id = '02')
                            then father.f_nhso_disease_status_id
                            else '0' end) as father_ht
                      ,max(case when (father.f_nhso_relate_person_id = '01' and father.f_nhso_disease_group_id = '03')
                            then father.f_nhso_disease_status_id
                            else '0' end) as father_acs
                      ,max(case when (father.f_nhso_relate_person_id = '02' and father.f_nhso_disease_group_id = '01')
                            then father.f_nhso_disease_status_id
                            else '0' end) as mother_dm
                      ,max(case when (father.f_nhso_relate_person_id = '02' and father.f_nhso_disease_group_id = '02')
                            then father.f_nhso_disease_status_id
                            else '0' end) as mother_ht
                      ,max(case when (father.f_nhso_relate_person_id = '02' and father.f_nhso_disease_group_id = '03')
                            then father.f_nhso_disease_status_id
                            else '0' end) as mother_acs
                      ,max(case when (father.f_nhso_relate_person_id = '03' and father.f_nhso_disease_group_id = '01')
                            then father.f_nhso_disease_status_id
                            else '0' end) as other_dm
                      ,max(case when (father.f_nhso_relate_person_id = '03' and father.f_nhso_disease_group_id = '02')
                            then father.f_nhso_disease_status_id
                            else '0' end) as other_ht
                      ,max(case when (father.f_nhso_relate_person_id = '03' and father.f_nhso_disease_group_id = '03')
                            then father.f_nhso_disease_status_id
                            else '0' end) as other_acs
           from t_nhso_service
                left join t_nhso_family_history as father on father.t_nhso_service_id = t_nhso_service.t_nhso_service_id
           group by father.t_nhso_service_id,father.t_health_family_id
                ) as family_history ON t_nhso_service.t_nhso_service_id = family_history.t_nhso_service_id
     LEFT JOIN t_nhso_family ON t_nhso_family.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN  
          (select t_nhso_risk_factor.t_nhso_service_id
                        , max(case when (t_nhso_risk_factor.f_nhso_risk_type_id = '01')
                                    then t_nhso_risk_factor.f_nhso_risk_status_id
                                    else '0' end) as risk_01
                        , max(case when (t_nhso_risk_factor.f_nhso_risk_type_id = '02')
                                   then t_nhso_risk_factor.f_nhso_risk_status_id
                                   else '0' end) as risk_02
                        , max(case when (t_nhso_risk_factor.f_nhso_risk_type_id = '03')
                                   then t_nhso_risk_factor.f_nhso_risk_status_id
                                   else '0' end) as risk_03
                        , max(case when (t_nhso_risk_factor.f_nhso_risk_type_id = '04')
                                   then t_nhso_risk_factor.f_nhso_risk_status_id
                                   else '0' end) as risk_04
                        , max(case when (t_nhso_risk_factor.f_nhso_risk_type_id = '05')
                                   then t_nhso_risk_factor.f_nhso_risk_status_id
                                   else '0' end) as risk_05
                        , max(case when (t_nhso_risk_factor.f_nhso_risk_type_id = '06')
                                   then t_nhso_risk_factor.f_nhso_risk_status_id
                                   else '0' end) as risk_06
                        , t_nhso_risk_factor.f_nhso_medication_status_id
                        , t_nhso_risk_factor.risk_factor_medication_name             
           from t_nhso_service
                left join t_nhso_risk_factor on t_nhso_risk_factor.t_nhso_service_id = t_nhso_service.t_nhso_service_id
           group by t_nhso_risk_factor.t_nhso_service_id
                        ,t_nhso_risk_factor.f_nhso_medication_status_id
                        ,t_nhso_risk_factor.risk_factor_medication_name
           ) as risk_factor ON risk_factor.t_nhso_service_id = t_nhso_service.t_nhso_service_id      
     LEFT JOIN 
          (select t_nhso_excercise.t_nhso_service_id
                      , t_nhso_excercise.excercise_is_usually
                      , t_nhso_excercise.f_nhso_excercise_frequency_id as frequency
                      , t_nhso_excercise.excercise_time_week as PER_Week
                      , t_nhso_excercise.f_nhso_excercise_time_period_id as time_period
                      , t_nhso_excercise.excercise_minute_time as minute_time
                      , case when(t_nhso_excercise.f_nhso_excercise_group_id = '01')
                             then '1'
                             else '0' end as excercise_run
                      , case when(t_nhso_excercise.f_nhso_excercise_group_id = '02')
                             then '1'
                             else '0' end as excercise_walk
                      , case when(t_nhso_excercise.f_nhso_excercise_group_id = '99')
                             then '1'
                             else '0' end as excercise_other
                      , t_nhso_excercise.excercise_name
            from t_nhso_excercise
            ) as excercise ON t_nhso_service.t_nhso_service_id = excercise.t_nhso_service_id
     LEFT JOIN
            (select t_nhso_food.t_nhso_service_id 
                    , t_nhso_food.f_nhso_food_source_id 
                    , t_nhso_food.f_nhso_food_taste_id
                    , t_nhso_food.food_taste_other
             from t_nhso_food 
             ) as food_taste ON food_taste.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_food_behavior.t_nhso_service_id
                , t_nhso_food_behavior.f_nhso_food_group_id
                , t_nhso_food_behavior.f_nhso_food_behavior_frequency_id
            ,t_nhso_food_behavior.food_behavior_frequency_description as meat
        from  t_nhso_food_behavior
        where  t_nhso_food_behavior.f_nhso_food_group_id = '01'
        ) as meat_01 ON t_nhso_service.t_nhso_service_id = meat_01.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_food_behavior.t_nhso_service_id
                , t_nhso_food_behavior.f_nhso_food_group_id
                , t_nhso_food_behavior.f_nhso_food_behavior_frequency_id
                , t_nhso_food_behavior.food_behavior_frequency_description  as coconut
         from  t_nhso_food_behavior
         where  t_nhso_food_behavior.f_nhso_food_group_id = '02'
        ) as coconut_02 ON t_nhso_service.t_nhso_service_id = coconut_02.t_nhso_service_id
    LEFT JOIN
        (select t_nhso_food_behavior.t_nhso_service_id
                , t_nhso_food_behavior.f_nhso_food_behavior_frequency_id
                , t_nhso_food_behavior.f_nhso_food_group_id
                , t_nhso_food_behavior.food_behavior_frequency_description as fruit
         from  t_nhso_food_behavior
         where  t_nhso_food_behavior.f_nhso_food_group_id = '03'
        ) as fruit_03 ON t_nhso_service.t_nhso_service_id = fruit_03.t_nhso_service_id
    LEFT JOIN
        (select t_nhso_food_behavior.t_nhso_service_id
                , t_nhso_food_behavior.f_nhso_food_behavior_frequency_id
                , t_nhso_food_behavior.f_nhso_food_group_id
                ,t_nhso_food_behavior.food_behavior_frequency_description as bearkfast
         from  t_nhso_food_behavior
         where  t_nhso_food_behavior.f_nhso_food_group_id = '04'
        ) as bearkfast_04 ON t_nhso_service.t_nhso_service_id = bearkfast_04.t_nhso_service_id
    LEFT JOIN
        (select t_nhso_food_behavior.t_nhso_service_id
                , t_nhso_food_behavior.f_nhso_food_behavior_frequency_id
                , t_nhso_food_behavior.f_nhso_food_group_id
         from  t_nhso_food_behavior
         where  t_nhso_food_behavior.f_nhso_food_group_id = '11'
        ) as fat_11 ON t_nhso_service.t_nhso_service_id = fat_11.t_nhso_service_id 
    LEFT JOIN
        (select t_nhso_food_behavior.t_nhso_service_id
                , t_nhso_food_behavior.f_nhso_food_behavior_frequency_id
                , t_nhso_food_behavior.f_nhso_food_group_id
         from  t_nhso_food_behavior
         where  t_nhso_food_behavior.f_nhso_food_group_id = '12'
        ) as homo_12 ON t_nhso_service.t_nhso_service_id = homo_12.t_nhso_service_id
    LEFT JOIN
        (select t_nhso_food_behavior.t_nhso_service_id
                , t_nhso_food_behavior.f_nhso_food_behavior_frequency_id
                , t_nhso_food_behavior.f_nhso_food_group_id
                , t_nhso_food_behavior.food_behavior_frequency_description as dh
         from  t_nhso_food_behavior
            inner join f_nhso_food_group on t_nhso_food_behavior.f_nhso_food_group_id = f_nhso_food_group.f_nhso_food_group_id
            inner join f_nhso_food_behavior_frequency 
            on f_nhso_food_behavior_frequency.f_nhso_food_behavior_frequency_id = t_nhso_food_behavior.f_nhso_food_behavior_frequency_id
         where  t_nhso_food_behavior.f_nhso_food_group_id = '13'
        ) as dh_13 ON t_nhso_service.t_nhso_service_id = dh_13.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_dope_drug.t_nhso_service_id 
            , t_nhso_dope_drug.f_nhso_drug_type_id
            , t_nhso_dope_drug.f_nhso_drug_usual_status_id
            , t_nhso_dope_drug.dope_drug_first_age , t_nhso_dope_drug.dope_drug_frequency
            , t_nhso_dope_drug.dope_drug_number_year_take , t_nhso_dope_drug.dope_drug_number_year_stop
         from t_nhso_dope_drug
         where t_nhso_dope_drug.f_nhso_drug_type_id = '01'
        ) as cigar_01 ON t_nhso_service.t_nhso_service_id = cigar_01.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_dope_drug.t_nhso_service_id
            , t_nhso_dope_drug.f_nhso_drug_type_id
            , t_nhso_dope_drug.f_nhso_drug_usual_status_id
            , t_nhso_dope_drug.dope_drug_first_age 
            , t_nhso_dope_drug.dope_drug_frequency
            , t_nhso_dope_drug.dope_drug_number_year_take 
            , t_nhso_dope_drug.dope_drug_number_year_stop
         from t_nhso_dope_drug
         where t_nhso_dope_drug.f_nhso_drug_type_id = '02'
         ) as drink_02 ON t_nhso_service.t_nhso_service_id = drink_02.t_nhso_service_id
     LEFT JOIN t_nhso_doctor_conclusion
        ON t_nhso_doctor_conclusion.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN t_nhso_cause_behavior 
        ON t_nhso_cause_behavior.t_nhso_service_id = t_nhso_service.t_nhso_service_id 
     LEFT JOIN
        (select t_nhso_strain.t_nhso_service_id
                ,t_nhso_strain.strain_topic as ht
         from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
         where b_nhso_strain_topic.strain_topic_description = 'ãšÃéÍ¹'
         ) as person_ht ON person_ht.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_strain.t_nhso_service_id
                ,t_nhso_strain.strain_topic as ir
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'Ë§ØŽË§ÔŽ§èÒÂ'
        ) as person_ir ON person_ir.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as strain
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'àÍÒšÃÔ§àÍÒšÑ§'
         ) as person_serious ON person_serious.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as af
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'ÃÙéÊÖ¡¡ÅÑÇ/ÇÔµ¡¡Ñ§ÇÅ') as person_afraid
        ON person_afraid.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as ex
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'µ×è¹àµé¹§èÒÂ') as person_excite
       ON person_excite.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as ot , t_nhso_strain.strain_description
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'Í×è¹æ'
          ) as person_other ON person_other.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
        (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as ha
         from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
         where b_nhso_strain_topic.strain_topic_description = '§Ò¹Ë¹Ñ¡'
        ) as job_hard ON job_hard.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as re
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'äÁèÁÕàÇÅÒŸÑ¡ŒèÍ¹'
         ) as job_relex ON job_relex.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as problem
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'àŸ×èÍ¹ÃèÇÁ§Ò¹ÁÕ»Ñ­ËÒ'
         ) as job_frindProblem ON job_frindProblem.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as bad
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = '·Ó§Ò¹äÁèäŽéŽÑè§ãš'
          ) as job_Workbad ON job_Workbad.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as wrong
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = '¶Ù¡µÓË¹ÔºèÍÂ'
         ) as job_fault ON job_fault.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as gro
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = '§Ò¹ÃÒºÃ×è¹ŽÕäÁèÁÕ»Ñ­ËÒ'
         ) as job_ground ON job_ground.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as ra
         from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
         where b_nhso_strain_topic.strain_topic_description = 'µéÍ§ÃÑºŒÔŽªÍºÁÒ¡'
        ) as family_respon ON family_respon.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as relat
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'ÊÑÁŸÑ¹žÀÒŸäÁè€èÍÂŽÕ'
         ) as family_relationbad ON family_relationbad.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as gro
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'äÁè€èÍÂà¢éÒãš¡Ñ¹') as family_nounder
        ON family_nounder.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as problem
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'µèÒ§€¹µèÒ§ÍÂÙè'
         ) as family_retreat ON job_frindProblem.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as bad
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = '€èÒãªéšèÒÂÁÒ¡'
          ) as family_costup ON family_costup.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as wrong
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'äÁèÁÕ€¹ªèÇÂ·Ó§Ò¹ºéÒ¹'
          ) as family_nohousekeeping ON family_nohousekeeping.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as npro
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'à»ç¹ÊØ¢ŽÕ äÁèÁÕ»Ñ­ËÒ') as family_noproblem
        ON family_noproblem.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as interest
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'äÁè€èÍÂäŽéÊ¹ãš'
         ) as social_nointerest ON social_nointerest.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as busy
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'ªèÒ§ÇØè¹ÇÒÂ ¹èÒàº×èÍ'
         ) as social_trouble ON social_trouble.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as lucky
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'âª€ŽÕÁÕàŸ×èÍ¹Ê¹Ô·äÁèà€ÃÕÂŽ'
         ) as social_goodluck ON social_goodluck.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as see
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'ÁÕáµè€¹àËç¹á¡èµÑÇàÍÒà»ÃÕÂº'
          ) as social_selfish ON social_selfish.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as nor
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = '·ÓãšäŽéÊºÒÂæ'
          ) as social_normal ON social_normal.t_nhso_service_id = t_nhso_service.t_nhso_service_id
WHERE   substring(t_visit.visit_financial_discharge_time,1,10) >= ?
      AND substring(t_visit.visit_financial_discharge_time,1,10) <= ?
--      AND t_visit.visit_money_discharge_status = '1'
     
