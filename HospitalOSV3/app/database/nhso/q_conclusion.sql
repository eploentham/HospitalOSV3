SELECT service_id
,CASE WHEN FATHER_DM = '01' 
        OR FATHER_DT = '01'
        OR FATHER_HT = '01'
        OR MOTHER_DM = '01'
        OR MOTHER_DT = '01'
        OR MOTHER_HT = '01'
        OR SISTER_DM = '01'
        OR SISTER_DT = '01'
        OR SISTER_HT = '01'
   THEN '1'
   ELSE '0'
END AS family_history
,CASE WHEN CIGAR_status = '02'
        OR CIGAR_status = '03'
   THEN '1'
   ELSE '0'
END AS cigar
,CASE WHEN DRINK_status = '02'
        OR DRINK_status = '03'
   THEN '1'
   ELSE '0'
END AS alcohol
,CASE WHEN RISK_DM = '01'
   THEN '1'
   ELSE '0'
END AS dm
,CASE WHEN RISK_DT = '01'
   THEN '1'
   ELSE '0'
END AS ht
,CASE WHEN RISK_HT = '01'
   THEN '1'
   ELSE '0'
END AS heart
,CASE WHEN MEAT_TIME = '01'
        OR COCONUT_TIME = '01'
   THEN '1'
   ELSE '0'
END AS MEAT
,CASE WHEN FAT_frequency = '01'
        OR DH_frequency = '01'
   THEN '1'
   ELSE '0'
END AS DRUG
,CASE WHEN excercise = '0'
   THEN '1'
   ELSE '0'
END AS exercise
,CASE WHEN 
           hot_heart = '1'
        OR irritated = '1'
        OR serious = '1'
        OR afraid = '1'
        OR excite = '1'
        OR hard_working = '1'
        OR tired = '1'
        OR problem = '1'
        OR workbad = '1'
        OR fault = '1'
        OR more_respon = '1'
        OR relationbad = '1'
        OR not_understand = '1'
        OR retreat = '1'
        OR more_cost = '1'
        OR nohousekeeping = '1'
        OR no_interest = '1'
        OR trouble = '1'
        OR selfish = '1'
    THEN '1'
    ELSE '0'
END AS strain
, hip_inch AS waist_hip
,'0' as fat
, CASE WHEN bmi>23 
    THEN '1'
    ELSE '0'
END AS bmi
FROM (
SELECT distinct t_nhso_service.t_nhso_service_id as service_id,
      FATHER_01.f_nhso_disease_status_id AS FATHER_DM
     , FATHER_02.f_nhso_disease_status_id AS FATHER_DT
     , FATHER_03.f_nhso_disease_status_id AS FATHER_HT
     , MOTHER_01.f_nhso_disease_status_id AS MOTHER_DM
     , MOTHER_02.f_nhso_disease_status_id AS MOTHER_DT 
     , MOTHER_03.f_nhso_disease_status_id AS MOTHER_HT
     , SISTER_01.f_nhso_disease_status_id AS SISTER_DM
     , SISTER_02.f_nhso_disease_status_id AS SISTER_DT 
     , SISTER_03.f_nhso_disease_status_id AS SISTER_HT
     , RISK_01.f_nhso_risk_status_id AS RISK_TEST
     , RISK_02.f_nhso_risk_status_id AS RISK_FAT
     , RISK_03.f_nhso_risk_status_id AS RISK_DM
     , RISK_04.f_nhso_risk_status_id AS RISK_DT
     , RISK_05.f_nhso_risk_status_id AS RISK_HT
     , RISK_06.f_nhso_risk_status_id AS RISK_LIVER
     , IF_99.medication_status_description AS IF_OTHER
     , IF_99.risk_factor_medication_name AS IF_DURG_NAME
     , EXCERCISE.excercise_is_usually AS excercise
     , EXCERCISE.frequency AS excercise_frequency
     , EXCERCISE.PER_Week AS excercise_PER_Week
     , EXCERCISE.time_period AS excercise_time_period
     , EXCERCISE.minute_time AS excercise_minute_time
     , food_taste.food_source_description AS food_source
     , MEAL_01.f_nhso_food_behavior_frequency_id AS MEAT_TIME
     , COCONUT_02.f_nhso_food_behavior_frequency_id AS COCONUT_TIME
     , food_taste.food_taste_description as taste
     , food_taste.food_taste_other as taste_other
     , FRUIT_03.food_behavior_frequency_description AS FRUIT_TIME
     , FRUIT_03.FRUIT as FRUIT_description
     , BEARKFAST_04.food_behavior_frequency_description AS BEARKFAST_TIME
     , BEARKFAST_04.BEARKFAST as BEARKFAST_description
     , FAT_11.f_nhso_food_behavior_frequency_id AS FAT_frequency
     , HOMO_12.f_nhso_food_behavior_frequency_id AS HOMO_frequency
     , DH_13.f_nhso_food_behavior_frequency_id AS DH_frequency
     , CIGAR_01.f_nhso_drug_usual_status_id AS CIGAR_status
     , CIGAR_01.dope_drug_first_age AS CIGAR_first_age
     , CIGAR_01.dope_drug_frequency AS CIGAR_frequency
     , CIGAR_01.dope_drug_number_year_take AS CIGAR_year_take
     , DRINK_02.f_nhso_drug_usual_status_id AS DRINK_status
     , DRINK_02.dope_drug_first_age as DRINK_first_age
     , DRINK_02.dope_drug_frequency as DRINK_frequency
     , DRINK_02.dope_drug_number_year_take as DRINK_year_take
     , case when(person_ht.ht = 'ใจร้อน')
        then '1'
        else '0' end as hot_heart
     , case when(person_ir.ir = 'หงุดหงิดง่าย')
        then '1'
        else '0' end as  irritated
     , case when(person_serious.strain = 'เอาจริงเอาจัง')
        then '1'
        else '0' end as  serious
     , case when(person_afraid.af = 'รู้สึกกลัว/วิตกกังวล')
        then '1'
        else '0' end as  afraid
     , case when(person_excite.ex = 'ตื่นเต้นง่าย')
        then '1'
        else '0' end as  excite
     , case when(person_other.ot = 'อื่นๆ')
        then '1'
        else '0' end as  other
     , case when(job_hard.ha = 'งานหนัก')
        then '1'
        else '0' end as hard_working
     , case when(job_relex.re = 'ไม่มีเวลาพักผ่อน')
        then '1'
        else '0' end as  tired
     , case when(job_frindProblem.problem = 'เพื่อนร่วมงานมีปัญหา')
        then '1'
        else '0' end as  problem
     , case when(job_Workbad.bad = 'ทำงานไม่ได้ดั่งใจ')
        then '1'
        else '0' end as  workbad
     , case when(job_fault.wrong = 'ถูกตำหนิบ่อย')
        then '1'
        else '0' end as  fault
     , case when(job_ground.gro = 'งานราบรื่นดีไม่มีปัญหา')
        then '1'
        else '0' end as  good
     , case when(family_respon.ra = 'ต้องรับผิดชอบมาก')
        then '1'
        else '0' end as more_respon
     , case when(family_relationbad.relat = 'สัมพันธภาพไม่ค่อยดี')
        then '1'
        else '0' end as  relationbad
     , case when(family_nounder.gro = 'ไม่ค่อยเข้าใจกัน')
        then '1'
        else '0' end as  not_understand
     , case when(family_retreat.problem = 'ต่างคนต่างอยู่')
        then '1'
        else '0' end as  retreat
     , case when(family_costup.bad = 'ค่าใช้จ่ายมาก')
        then '1'
        else '0' end as  more_cost
     , case when(family_nohousekeeping.wrong = 'ไม่มีคนช่วยทำงานบ้าน')
        then '1'
        else '0' end as  nohousekeeping
     , case when(family_noproblem.npro = 'เป็นสุขดี ไม่มีปัญหา')
        then '1'
        else '0' end as  noproblem
     , case when(social_nointerest.interest = 'ไม่ค่อยได้สนใจ')
        then '1'
        else '0'end as  no_interest
     , case when(social_trouble.busy = 'ช่างวุ่นวาย น่าเบื่อ')
        then '1'
        else '0' end as  trouble
     , case when(social_goodluck.lucky = 'โชคดีมีเพื่อนสนิทไม่เครียด')
        then '1'
        else '0' end as  have_friend
     , case when(social_selfish.see = 'มีแต่คนเห็นแก่ตัวเอาเปรียบ')
        then '1'
        else '0' end as  selfish
     , case when(social_normal.nor = 'ทำใจได้สบายๆ')
        then '1'
        else '0' end as  happy
     , case when t_nhso_vital_sign.vital_sign_waist_inch = ''
            then 0
            else cast(t_nhso_vital_sign.vital_sign_waist_inch as float) 
        end as waist_inch
     , case when t_nhso_vital_sign.vital_sign_hip_inch = '' 
            then 0
            else cast(t_nhso_vital_sign.vital_sign_hip_inch as float) 
        end as hip_inch
     , case when t_nhso_vital_sign.vital_sign_bmi = ''
            then 0
            else cast(t_nhso_vital_sign.vital_sign_bmi as float)
        end as bmi
FROM   t_nhso_service  
     LEFT JOIN t_nhso_vital_sign  ON t_nhso_vital_sign.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     INNER JOIN t_health_family
        ON (t_nhso_service.t_health_family_id = t_health_family.t_health_family_id
            AND t_nhso_service.t_nhso_service_id like ?)
     LEFT JOIN
       (select t_nhso_family_history.t_nhso_service_id,t_nhso_family_history.t_health_family_id,f_nhso_disease_status.f_nhso_disease_status_id
        from t_nhso_family_history
            inner join f_nhso_disease_status on f_nhso_disease_status.f_nhso_disease_status_id = t_nhso_family_history.f_nhso_disease_status_id
        where t_nhso_family_history.f_nhso_relate_person_id = '01' and t_nhso_family_history.f_nhso_disease_group_id = '01') AS FATHER_01
        ON FATHER_01.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN  
       (select t_nhso_family_history.t_nhso_service_id,t_nhso_family_history.t_health_family_id,f_nhso_disease_status.f_nhso_disease_status_id
        from t_nhso_family_history
            inner join f_nhso_disease_status on f_nhso_disease_status.f_nhso_disease_status_id = t_nhso_family_history.f_nhso_disease_status_id
        where t_nhso_family_history.f_nhso_relate_person_id = '01' and t_nhso_family_history.f_nhso_disease_group_id = '02') AS FATHER_02
        ON FATHER_02.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN  
       (select t_nhso_family_history.t_nhso_service_id,t_nhso_family_history.t_health_family_id,f_nhso_disease_status.f_nhso_disease_status_id
        from t_nhso_family_history
            inner join f_nhso_disease_status on f_nhso_disease_status.f_nhso_disease_status_id = t_nhso_family_history.f_nhso_disease_status_id
        where t_nhso_family_history.f_nhso_relate_person_id = '01' and t_nhso_family_history.f_nhso_disease_group_id = '02') AS FATHER_03
        ON FATHER_03.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
       (select t_nhso_family_history.t_nhso_service_id,t_nhso_family_history.t_health_family_id,f_nhso_disease_status.f_nhso_disease_status_id
        from t_nhso_family_history
            inner join f_nhso_disease_status on f_nhso_disease_status.f_nhso_disease_status_id = t_nhso_family_history.f_nhso_disease_status_id
        where t_nhso_family_history.f_nhso_relate_person_id = '02' and t_nhso_family_history.f_nhso_disease_group_id = '01') AS MOTHER_01
        ON MOTHER_01.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN  
       (select t_nhso_family_history.t_nhso_service_id,t_nhso_family_history.t_health_family_id,f_nhso_disease_status.f_nhso_disease_status_id
        from t_nhso_family_history
            inner join f_nhso_disease_status on f_nhso_disease_status.f_nhso_disease_status_id = t_nhso_family_history.f_nhso_disease_status_id
        where t_nhso_family_history.f_nhso_relate_person_id = '02' and t_nhso_family_history.f_nhso_disease_group_id = '02') AS MOTHER_02
        ON MOTHER_02.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN  
       (select t_nhso_family_history.t_nhso_service_id,t_nhso_family_history.t_health_family_id,f_nhso_disease_status.f_nhso_disease_status_id
        from t_nhso_family_history
            inner join f_nhso_disease_status on f_nhso_disease_status.f_nhso_disease_status_id = t_nhso_family_history.f_nhso_disease_status_id
        where t_nhso_family_history.f_nhso_relate_person_id = '02' and t_nhso_family_history.f_nhso_disease_group_id = '02') AS MOTHER_03
        ON MOTHER_03.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
       (select t_nhso_family_history.t_nhso_service_id,t_nhso_family_history.t_health_family_id,f_nhso_disease_status.f_nhso_disease_status_id
        from t_nhso_family_history
            inner join f_nhso_disease_status on f_nhso_disease_status.f_nhso_disease_status_id = t_nhso_family_history.f_nhso_disease_status_id
        where t_nhso_family_history.f_nhso_relate_person_id = '03' and t_nhso_family_history.f_nhso_disease_group_id = '01') AS SISTER_01
        ON SISTER_01.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN  
       (select t_nhso_family_history.t_nhso_service_id,t_nhso_family_history.t_health_family_id,f_nhso_disease_status.f_nhso_disease_status_id
        from t_nhso_family_history
            inner join f_nhso_disease_status on f_nhso_disease_status.f_nhso_disease_status_id = t_nhso_family_history.f_nhso_disease_status_id
        where t_nhso_family_history.f_nhso_relate_person_id = '03' and t_nhso_family_history.f_nhso_disease_group_id = '02') AS SISTER_02
        ON SISTER_02.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN  
       (select t_nhso_family_history.t_nhso_service_id,t_nhso_family_history.t_health_family_id,f_nhso_disease_status.f_nhso_disease_status_id
        from t_nhso_family_history
            inner join f_nhso_disease_status on f_nhso_disease_status.f_nhso_disease_status_id = t_nhso_family_history.f_nhso_disease_status_id
        where t_nhso_family_history.f_nhso_relate_person_id = '03' and t_nhso_family_history.f_nhso_disease_group_id = '02') AS SISTER_03
        ON SISTER_03.t_nhso_service_id = t_nhso_service.t_nhso_service_id 
     LEFT JOIN t_nhso_family 
        ON t_nhso_family.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN f_nhso_family_income_enough 
        ON f_nhso_family_income_enough.f_nhso_family_income_enough_id = t_nhso_family.f_nhso_family_income_enough_id
     LEFT JOIN f_nhso_family_job_type 
        ON f_nhso_family_job_type.f_nhso_family_job_type_id = t_nhso_family.f_nhso_family_job_type_id
     LEFT JOIN
      (select t_nhso_risk_factor.t_nhso_service_id,f_nhso_risk_status.f_nhso_risk_status_id
       from  t_nhso_risk_factor
            inner join f_nhso_risk_status on f_nhso_risk_status.f_nhso_risk_status_id = t_nhso_risk_factor.f_nhso_risk_status_id
       where  t_nhso_risk_factor.f_nhso_risk_type_id = '01') as RISK_01
        ON RISK_01.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
      (select t_nhso_risk_factor.t_nhso_service_id,f_nhso_risk_status.f_nhso_risk_status_id
       from  t_nhso_risk_factor
            inner join f_nhso_risk_status on f_nhso_risk_status.f_nhso_risk_status_id = t_nhso_risk_factor.f_nhso_risk_status_id
       where  t_nhso_risk_factor.f_nhso_risk_type_id = '02') as RISK_02
        ON RISK_02.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
      (select t_nhso_risk_factor.t_nhso_service_id,f_nhso_risk_status.f_nhso_risk_status_id
       from  t_nhso_risk_factor
            inner join f_nhso_risk_status on f_nhso_risk_status.f_nhso_risk_status_id = t_nhso_risk_factor.f_nhso_risk_status_id
       where  t_nhso_risk_factor.f_nhso_risk_type_id = '03') as RISK_03
        ON RISK_03.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
      (select t_nhso_risk_factor.t_nhso_service_id,f_nhso_risk_status.f_nhso_risk_status_id
       from  t_nhso_risk_factor
            inner join f_nhso_risk_status on f_nhso_risk_status.f_nhso_risk_status_id = t_nhso_risk_factor.f_nhso_risk_status_id
       where  t_nhso_risk_factor.f_nhso_risk_type_id = '04') as RISK_04
        ON RISK_04.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
      (select t_nhso_risk_factor.t_nhso_service_id,f_nhso_risk_status.f_nhso_risk_status_id
       from  t_nhso_risk_factor
            inner join f_nhso_risk_status on f_nhso_risk_status.f_nhso_risk_status_id = t_nhso_risk_factor.f_nhso_risk_status_id
       where  t_nhso_risk_factor.f_nhso_risk_type_id = '05') as RISK_05
        ON RISK_05.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_risk_factor.t_nhso_service_id,f_nhso_risk_status.f_nhso_risk_status_id
         from  t_nhso_risk_factor
            inner join f_nhso_risk_status on f_nhso_risk_status.f_nhso_risk_status_id = t_nhso_risk_factor.f_nhso_risk_status_id
       where  t_nhso_risk_factor.f_nhso_risk_type_id = '06') as RISK_06
        ON RISK_06.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
       (select t_nhso_risk_factor.t_nhso_service_id,f_nhso_medication_status.medication_status_description,t_nhso_risk_factor.risk_factor_medication_name
        from t_nhso_risk_factor
        inner join f_nhso_medication_status 
        on t_nhso_risk_factor.f_nhso_medication_status_id = f_nhso_medication_status.f_nhso_medication_status_id) AS IF_99
       ON  t_nhso_service.t_nhso_service_id = IF_99.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_excercise.t_nhso_service_id
            , t_nhso_excercise.excercise_is_usually AS excercise_is_usually
            , f_nhso_excercise_frequency.f_nhso_excercise_frequency_id AS frequency
            , t_nhso_excercise.excercise_time_week AS PER_Week
            , f_nhso_excercise_time_period.f_nhso_excercise_time_period_id AS time_period
            , t_nhso_excercise.excercise_minute_time AS minute_time
         from t_nhso_excercise
            inner join f_nhso_excercise_group AS excercise_01
             on t_nhso_excercise.f_nhso_excercise_group_id = excercise_01.f_nhso_excercise_group_id
            inner join f_nhso_excercise_group AS excercise_02
             on t_nhso_excercise.f_nhso_excercise_group_id = excercise_02.f_nhso_excercise_group_id 
            inner join f_nhso_excercise_time_period
            on t_nhso_excercise.f_nhso_excercise_time_period_id = f_nhso_excercise_time_period.f_nhso_excercise_time_period_id
            inner join f_nhso_excercise_frequency
            on t_nhso_excercise.f_nhso_excercise_frequency_id = f_nhso_excercise_frequency.f_nhso_excercise_frequency_id) AS EXCERCISE
         ON t_nhso_service.t_nhso_service_id = EXCERCISE.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_food.t_nhso_service_id , f_nhso_food_source.food_source_description , f_nhso_food_taste.food_taste_description
            , t_nhso_food.food_taste_other
         from t_nhso_food 
            inner join f_nhso_food_source on f_nhso_food_source.f_nhso_food_source_id = t_nhso_food.f_nhso_food_source_id
            inner join f_nhso_food_taste on f_nhso_food_taste.f_nhso_food_taste_id = t_nhso_food.f_nhso_food_taste_id) AS food_taste
         ON food_taste.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_food_behavior.t_nhso_service_id
        ,f_nhso_food_behavior_frequency.f_nhso_food_behavior_frequency_id
        from  t_nhso_food_behavior
            inner join f_nhso_food_group on t_nhso_food_behavior.f_nhso_food_group_id = f_nhso_food_group.f_nhso_food_group_id
            inner join f_nhso_food_behavior_frequency 
            on f_nhso_food_behavior_frequency.f_nhso_food_behavior_frequency_id = t_nhso_food_behavior.f_nhso_food_behavior_frequency_id
        where  t_nhso_food_behavior.f_nhso_food_group_id = '01') AS MEAL_01
        ON t_nhso_service.t_nhso_service_id = MEAL_01.t_nhso_service_id
    LEFT JOIN
        (select t_nhso_food_behavior.t_nhso_service_id
            ,f_nhso_food_behavior_frequency.f_nhso_food_behavior_frequency_id
         from  t_nhso_food_behavior
            inner join f_nhso_food_group on t_nhso_food_behavior.f_nhso_food_group_id = f_nhso_food_group.f_nhso_food_group_id
            inner join f_nhso_food_behavior_frequency 
            on f_nhso_food_behavior_frequency.f_nhso_food_behavior_frequency_id = t_nhso_food_behavior.f_nhso_food_behavior_frequency_id
         where  t_nhso_food_behavior.f_nhso_food_group_id = '02') AS COCONUT_02
        ON t_nhso_service.t_nhso_service_id = COCONUT_02.t_nhso_service_id
    LEFT JOIN
        (select t_nhso_food_behavior.t_nhso_service_id,f_nhso_food_group.food_group_description
            ,f_nhso_food_behavior_frequency.food_behavior_frequency_description
            ,t_nhso_food_behavior.food_behavior_frequency_description as FRUIT
         from  t_nhso_food_behavior
            inner join f_nhso_food_group on t_nhso_food_behavior.f_nhso_food_group_id = f_nhso_food_group.f_nhso_food_group_id
            inner join f_nhso_food_behavior_frequency 
            on f_nhso_food_behavior_frequency.f_nhso_food_behavior_frequency_id = t_nhso_food_behavior.f_nhso_food_behavior_frequency_id
         where  t_nhso_food_behavior.f_nhso_food_group_id = '03') AS FRUIT_03
        ON t_nhso_service.t_nhso_service_id = FRUIT_03.t_nhso_service_id
    LEFT JOIN
        (select t_nhso_food_behavior.t_nhso_service_id,f_nhso_food_group.food_group_description
            ,f_nhso_food_behavior_frequency.food_behavior_frequency_description
            ,t_nhso_food_behavior.food_behavior_frequency_description as BEARKFAST
         from  t_nhso_food_behavior
            inner join f_nhso_food_group on t_nhso_food_behavior.f_nhso_food_group_id = f_nhso_food_group.f_nhso_food_group_id
            inner join f_nhso_food_behavior_frequency 
            on f_nhso_food_behavior_frequency.f_nhso_food_behavior_frequency_id = t_nhso_food_behavior.f_nhso_food_behavior_frequency_id
         where  t_nhso_food_behavior.f_nhso_food_group_id = '04') AS BEARKFAST_04
        ON t_nhso_service.t_nhso_service_id = BEARKFAST_04.t_nhso_service_id
    LEFT JOIN
        (select t_nhso_food_behavior.t_nhso_service_id
            ,f_nhso_food_behavior_frequency.f_nhso_food_behavior_frequency_id
         from  t_nhso_food_behavior
            inner join f_nhso_food_group on t_nhso_food_behavior.f_nhso_food_group_id = f_nhso_food_group.f_nhso_food_group_id
            inner join f_nhso_food_behavior_frequency 
            on f_nhso_food_behavior_frequency.f_nhso_food_behavior_frequency_id = t_nhso_food_behavior.f_nhso_food_behavior_frequency_id
         where  t_nhso_food_behavior.f_nhso_food_group_id = '11') AS FAT_11
        ON t_nhso_service.t_nhso_service_id = FAT_11.t_nhso_service_id 
    LEFT JOIN
        (select t_nhso_food_behavior.t_nhso_service_id
            ,f_nhso_food_behavior_frequency.f_nhso_food_behavior_frequency_id
         from  t_nhso_food_behavior
            inner join f_nhso_food_group on t_nhso_food_behavior.f_nhso_food_group_id = f_nhso_food_group.f_nhso_food_group_id
            inner join f_nhso_food_behavior_frequency 
            on f_nhso_food_behavior_frequency.f_nhso_food_behavior_frequency_id = t_nhso_food_behavior.f_nhso_food_behavior_frequency_id
         where  t_nhso_food_behavior.f_nhso_food_group_id = '12') AS HOMO_12
        ON t_nhso_service.t_nhso_service_id = HOMO_12.t_nhso_service_id
    LEFT JOIN
        (select t_nhso_food_behavior.t_nhso_service_id
            ,f_nhso_food_behavior_frequency.f_nhso_food_behavior_frequency_id
         from  t_nhso_food_behavior
            inner join f_nhso_food_group on t_nhso_food_behavior.f_nhso_food_group_id = f_nhso_food_group.f_nhso_food_group_id
            inner join f_nhso_food_behavior_frequency 
            on f_nhso_food_behavior_frequency.f_nhso_food_behavior_frequency_id = t_nhso_food_behavior.f_nhso_food_behavior_frequency_id
         where  t_nhso_food_behavior.f_nhso_food_group_id = '13') AS DH_13
        ON t_nhso_service.t_nhso_service_id = DH_13.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_dope_drug.t_nhso_service_id
            , f_nhso_drug_usual_status.f_nhso_drug_usual_status_id
            , t_nhso_dope_drug.dope_drug_first_age , t_nhso_dope_drug.dope_drug_frequency
            , t_nhso_dope_drug.dope_drug_number_year_take
         from t_nhso_dope_drug
            inner join f_nhso_drug_type on t_nhso_dope_drug.f_nhso_drug_type_id = f_nhso_drug_type.f_nhso_drug_type_id
            left join f_nhso_drug_usual_status on f_nhso_drug_usual_status.f_nhso_drug_usual_status_id = t_nhso_dope_drug.f_nhso_drug_usual_status_id
         where t_nhso_dope_drug.f_nhso_drug_type_id = '01') AS CIGAR_01
         ON t_nhso_service.t_nhso_service_id = CIGAR_01.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_dope_drug.t_nhso_service_id
            , f_nhso_drug_usual_status.f_nhso_drug_usual_status_id
            , t_nhso_dope_drug.dope_drug_first_age , t_nhso_dope_drug.dope_drug_frequency
            , t_nhso_dope_drug.dope_drug_number_year_take
         from t_nhso_dope_drug
            inner join f_nhso_drug_type on t_nhso_dope_drug.f_nhso_drug_type_id = f_nhso_drug_type.f_nhso_drug_type_id
            left join f_nhso_drug_usual_status on f_nhso_drug_usual_status.f_nhso_drug_usual_status_id = t_nhso_dope_drug.f_nhso_drug_usual_status_id
         where t_nhso_dope_drug.f_nhso_drug_type_id = '02') AS DRINK_02
         ON t_nhso_service.t_nhso_service_id = DRINK_02.t_nhso_service_id
    LEFT JOIN
        (select t_nhso_strain.t_nhso_service_id
                ,t_nhso_strain.strain_topic as ht
         from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
         where b_nhso_strain_topic.strain_topic_description = 'ใจร้อน'
         ) as person_ht ON person_ht.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_strain.t_nhso_service_id
                ,t_nhso_strain.strain_topic as ir
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'หงุดหงิดง่าย'
        ) as person_ir ON person_ir.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as strain
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'เอาจริงเอาจัง'
         ) as person_serious ON person_serious.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as af
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'รู้สึกกลัว/วิตกกังวล') as person_afraid
        ON person_afraid.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as ex
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'ตื่นเต้นง่าย') as person_excite
       ON person_excite.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as ot , t_nhso_strain.strain_description
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'อื่นๆ'
          ) as person_other ON person_other.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
        (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as ha
         from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
         where b_nhso_strain_topic.strain_topic_description = 'งานหนัก'
        ) as job_hard ON job_hard.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as re
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'ไม่มีเวลาพักผ่อน'
         ) as job_relex ON job_relex.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as problem
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'เพื่อนร่วมงานมีปัญหา'
         ) as job_frindProblem ON job_frindProblem.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as bad
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'ทำงานไม่ได้ดั่งใจ'
          ) as job_Workbad ON job_Workbad.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as wrong
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'ถูกตำหนิบ่อย'
         ) as job_fault ON job_fault.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as gro
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'งานราบรื่นดีไม่มีปัญหา'
         ) as job_ground ON job_ground.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as ra
         from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
         where b_nhso_strain_topic.strain_topic_description = 'ต้องรับผิดชอบมาก'
        ) as family_respon ON family_respon.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
        (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as relat
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'สัมพันธภาพไม่ค่อยดี'
         ) as family_relationbad ON family_relationbad.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as gro
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'ไม่ค่อยเข้าใจกัน') as family_nounder
        ON family_nounder.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as problem
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'ต่างคนต่างอยู่'
         ) as family_retreat ON job_frindProblem.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as bad
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'ค่าใช้จ่ายมาก'
          ) as family_costup ON family_costup.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as wrong
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'ไม่มีคนช่วยทำงานบ้าน'
          ) as family_nohousekeeping ON family_nohousekeeping.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as npro
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'เป็นสุขดี ไม่มีปัญหา') as family_noproblem
        ON family_noproblem.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as interest
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'ไม่ค่อยได้สนใจ'
         ) as social_nointerest ON social_nointerest.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as busy
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'ช่างวุ่นวาย น่าเบื่อ'
         ) as social_trouble ON social_trouble.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as lucky
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'โชคดีมีเพื่อนสนิทไม่เครียด'
         ) as social_goodluck ON social_goodluck.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as see
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'มีแต่คนเห็นแก่ตัวเอาเปรียบ'
          ) as social_selfish ON social_selfish.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN
         (select t_nhso_strain.t_nhso_service_id,t_nhso_strain.strain_topic as nor
          from t_nhso_strain
          inner join b_nhso_strain_topic on t_nhso_strain.strain_topic = b_nhso_strain_topic.strain_topic_description
          where b_nhso_strain_topic.strain_topic_description = 'ทำใจได้สบายๆ'
          ) as social_normal ON social_normal.t_nhso_service_id = t_nhso_service.t_nhso_service_id
) as q1
