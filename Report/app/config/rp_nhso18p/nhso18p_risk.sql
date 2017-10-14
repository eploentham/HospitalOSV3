
SELECT distinct
substring(t_nhso_service.t_nhso_service_id,6) as risk_id
,b_site.b_visit_office_id ||  t_visit.visit_vn  as t_service_id

,case when (t_health_family.f_patient_education_type_id = '1' )
    then '5'
    when (t_health_family.f_patient_education_type_id = '2' 
        or t_health_family.f_patient_education_type_id = '3')
    then '1'
    when (t_health_family.f_patient_education_type_id = '4' 
        or t_health_family.f_patient_education_type_id = '13')
    then '2'
    when (t_health_family.f_patient_education_type_id = '7' 
        or t_health_family.f_patient_education_type_id = '8')
    then '3'
    when (t_health_family.f_patient_education_type_id = '9' 
        or t_health_family.f_patient_education_type_id = '10'
        or t_health_family.f_patient_education_type_id = '11')
    then '4'
    else '5'
    end as 	education
,t_nhso_family.family_income as 	receive
,substring(t_nhso_family.f_nhso_family_income_enough_id,2) as pay
,case when (t_nhso_family.f_nhso_family_job_type_id = '01')
      then '1'
      when (t_nhso_family.f_nhso_family_job_type_id = '02')
      then '2'
      when (t_nhso_family.f_nhso_family_job_type_id = '03')
      then '3'
      when (t_nhso_family.f_nhso_family_job_type_id = '04')
      then '4'
      else '5'
      end as 	work_type
,t_nhso_family.family_job_name as 	work_type_other
,substr(family_history.father_dm,2) as 	father_diabetes
,substr(family_history.father_ht,2) as 	father_heightbloodpressure
,substr(family_history.father_acs,2) as 	father_heartdisease
,substr(family_history.mother_dm,2) as 	mother_diabetes
,substr(family_history.mother_ht,2) as 	mother_heightbloodpressure
,substr(family_history.mother_acs,2) as 	mother_heartdisease
,substr(family_history.other_dm,2) as 	kinship_diabetes
,substr(family_history.other_ht,2) as 	kinship_heightbloodpressure
,substr(family_history.other_acs,2) as 	kinship_heartdisease
,substr(risk_factor.risk_01,2) as 	healthcheck
,substr(risk_factor.risk_02,2) as 	fatchild
,substr(risk_factor.risk_03,2) as 	diabetes
,substr(risk_factor.risk_04,2) as 	heightbloodpressure
,substr(risk_factor.risk_05,2) as 	heartdisease
,substr(risk_factor.risk_06,2) as 	liverdisease
,case when (risk_factor.f_nhso_medication_status_id = '01')
       then '1'
    when (risk_factor.f_nhso_medication_status_id = '02')
       then '2'
    when (risk_factor.f_nhso_medication_status_id = '03')
       then '3'
    when (risk_factor.f_nhso_medication_status_id = '99')
       then '4'
    else ''
     end as 	theatdisease
,excercise.excercise_is_usually as 	exercise
,substr(excercise.frequency,2) as 	excercise_frequency
,substr(excercise.time_period,2) as 	excercise_longtime
,excercise.excercise_run as 	excercise_run
,excercise.excercise_walk as 	excercise_walk
,excercise.excercise_sport as 	excercise_sport
,CASE WHEN excercise.excercise_sport = '1'
    THEN excercise.excercise_name
    ELSE '' END  as 	excercise_sport_detail
,excercise.excercise_other as 	excercise_other
,CASE WHEN excercise.excercise_other = '1'
    THEN excercise.excercise_name
    ELSE '' END as 	excercise_other_detail
,substr(food_taste.f_nhso_food_source_id,2) as 	food
,case when(meat_01.f_nhso_food_behavior_frequency_id = '01')
      then '1' 
      when(meat_01.f_nhso_food_behavior_frequency_id = '03')
      then '2' 
      when(meat_01.f_nhso_food_behavior_frequency_id = '02')
      then '3' 
      else meat_01.f_nhso_food_behavior_frequency_id
      end as 	fat_food
,meat_01.food_behavior_frequency_description as 	fat_food_other
,case when(coconut_02.f_nhso_food_behavior_frequency_id = '01')
      then '1' 
      when(coconut_02.f_nhso_food_behavior_frequency_id = '03')
      then '2' 
      when(coconut_02.f_nhso_food_behavior_frequency_id = '02')
      then '3' 
      else coconut_02.f_nhso_food_behavior_frequency_id
      end as 	coconutcream_food
,coconut_02.food_behavior_frequency_description as 	coconutcream_food_other
,case when(food_taste.f_nhso_food_taste_id = '01')
      then '3' 
      when(food_taste.f_nhso_food_taste_id = '03')
      then '2' 
      when(food_taste.f_nhso_food_taste_id = '02')
      then '1' 
      else food_taste.f_nhso_food_taste_id
      end  as 	flavor_food
,food_taste.food_taste_other as 	flavor_food_other
,case when(fruit_03.f_nhso_food_behavior_frequency_id = '01')
      then '1' 
      when(fruit_03.f_nhso_food_behavior_frequency_id = '03')
      then '2' 
      when(fruit_03.f_nhso_food_behavior_frequency_id = '02')
      then '3' 
      else fruit_03.f_nhso_food_behavior_frequency_id
      end as 	fruit
,fruit_03.food_behavior_frequency_description as 	fruit_other
,case when(bearkfast_04.f_nhso_food_behavior_frequency_id = '06')
      then '1' 
      when(bearkfast_04.f_nhso_food_behavior_frequency_id = '02'
            or bearkfast_04.f_nhso_food_behavior_frequency_id = '03'
            or bearkfast_04.f_nhso_food_behavior_frequency_id = '08' )
      then '2' 
      when(bearkfast_04.f_nhso_food_behavior_frequency_id = '04')
      then '3' 
      else bearkfast_04.f_nhso_food_behavior_frequency_id
      end as 	breakfast
,case when(fat_11.f_nhso_food_behavior_frequency_id = '07')
      then '1' 
      when(fat_11.f_nhso_food_behavior_frequency_id = '05')
      then '2' 
      when(fat_11.f_nhso_food_behavior_frequency_id = '04')
      then '3' 
      else fat_11.f_nhso_food_behavior_frequency_id
      end as 	drug_fat
,case when(homo_12.f_nhso_food_behavior_frequency_id = '07')
      then '1' 
      when(homo_12.f_nhso_food_behavior_frequency_id = '05')
      then '2' 
      when(homo_12.f_nhso_food_behavior_frequency_id = '04')
      then '3' 
      else homo_12.f_nhso_food_behavior_frequency_id
      end as 	drug_hormone
,case when(dh_13.f_nhso_food_behavior_frequency_id = '07')
      then '1' 
      when(dh_13.f_nhso_food_behavior_frequency_id = '05')
      then '2' 
      when(dh_13.f_nhso_food_behavior_frequency_id = '04')
      then '3' 
      else dh_13.f_nhso_food_behavior_frequency_id
      end as 	drug_bloodpressure
,dh_13.food_behavior_frequency_description as 	drug_name
,CASE WHEN cigar_01.f_nhso_drug_usual_status_id='01'
    THEN '0'
    WHEN cigar_01.f_nhso_drug_usual_status_id='02'
    THEN '1'
    WHEN cigar_01.f_nhso_drug_usual_status_id='03'
    THEN '2'
    ELSE cigar_01.f_nhso_drug_usual_status_id END  as 	smoke
,cigar_01.dope_drug_first_age as 	firstsmoke
,cigar_01.dope_drug_frequency as 	presentsmoke
,cigar_01.dope_drug_number_year_take as 	longtimesmoke
,cigar_01.dope_drug_number_year_stop as 	stopsmoke
,CASE WHEN drink_02.f_nhso_drug_usual_status_id='01'
    THEN '0'
    WHEN drink_02.f_nhso_drug_usual_status_id='02'
    THEN '1'
    WHEN drink_02.f_nhso_drug_usual_status_id='03'
    THEN '2'
    ELSE drink_02.f_nhso_drug_usual_status_id END  as 	drink
,drink_02.dope_drug_first_age as 	firstdrink
,drink_02.dope_drug_frequency  as 	presendrink
,drink_02.dope_drug_number_year_take as 	longtimedrink
,drink_02.dope_drug_number_year_stop as 	stopdrink
,  case when(person_ht.strain_topic is not null)
    then '1'
    else '0' end as 	charecter1
 , case when(person_ir.strain_topic is not null)
    then '1'
    else '0' end as 	 charecter2
 , case when(person_serious.strain_topic is not null)
    then '1'
    else '0' end as 	charecter3
 , case when(person_afraid.strain_topic is not null)
    then '1'
    else '0' end as 	 charecter4
 , case when(person_excite.strain_topic is not null)
    then '1'
    else '0' end as 	 charecter5
 , case when(person_other.strain_topic is not null)
    then '1'
    else '0' end as 	 charecter6
 , case when(person_other.strain_description is not null)
    then person_other.strain_description
    else '' end  as 	charecterdetail
 , case when(job_hard.strain_topic is not null)
    then '1'
    else '0' end as 	work1
 , case when(job_relex.strain_topic is not null)
    then '1'
    else '0' end as 	 work2
 , case when(job_frindProblem.strain_topic is not null)
    then '1'
    else '0' end as 	 work3
 , case when(job_Workbad.strain_topic is not null)
    then '1'
    else '0' end as 	 work4
 , case when(job_fault.strain_topic is not null)
    then '1'
    else '0' end as 	 work5
 , case when(job_ground.strain_topic is not null)
    then '1'
    else '0' end as 	 work6
 , case when(family_respon.strain_topic is not null)
    then '1'
    else '0' end as 	family1
 , case when(family_relationbad.strain_topic is not null)
    then '1'
    else '0' end as 	family2
 , case when(family_nounder.strain_topic is not null)
    then '1'
    else '0' end as 	 family3
 , case when(family_retreat.strain_topic is not null)
    then '1'
    else '0' end as 	 family4
 , case when(family_costup.strain_topic is not null)
    then '1'
    else '0' end as 	 family5
 , case when(family_nohousekeeping.strain_topic is not null)
    then '1'
    else '0' end as 	family6
 , case when(family_noproblem.strain_topic is not null)
    then '1'
    else '0' end as 	 family7
 , case when(social_nointerest.strain_topic is not null)
    then '1'
    else '0'end as 	 social1
 , case when(social_trouble.strain_topic is not null)
    then '1'
    else '0' end as 	social2
 , case when(social_goodluck.strain_topic is not null)
    then '1'
    else '0' end as 	 social3
 , case when(social_selfish.strain_topic is not null)
    then '1'
    else '0' end as 	 social4
 , case when(social_normal.strain_topic is not null)
    then '1'
    else '0' end as 	social5
,t_nhso_vital_sign.vital_sign_weight as 	weight
,t_nhso_vital_sign.vital_sign_height as 	height
,t_nhso_vital_sign.vital_sign_bmi as 	bmi
,t_nhso_vital_sign.vital_sign_waist_inch as 	buttocks
,t_nhso_vital_sign.vital_sign_hip_inch as 	waist_width
,t_nhso_vital_sign.vital_sign_blood_sugar as 	dtx
,CASE WHEN t_nhso_vital_sign.vital_sign_pressure='/'
    THEN '' 
    ELSE t_nhso_vital_sign.vital_sign_pressure END as 	bloodpressure
,case when (t_visit_physical_exam.t_visit_physical_exam_id <> '')
    then '1'
    else '0' end as 	bodycheck 
,t_nhso_vital_sign.vital_sign_result as 	result
,t_nhso_vital_sign.vital_sign_result_other as 	result_other
,CASE WHEN assess_conclusion.family_history is null THEN '' else '' end as 	heart_family_history
,CASE WHEN assess_conclusion.smoke_behaviour is null THEN '' else '' end as 	heart_smoke_behaviour
,CASE WHEN assess_conclusion.drink_behaviour is null THEN '' else '' end as 	heart_drink_behaviour
,CASE WHEN assess_conclusion.diabutes is null THEN '' else '' end as 	heart_diabutes
,CASE WHEN assess_conclusion.bloodpressure is null THEN '' else '' end as 	heart_bloodpressure
,CASE WHEN assess_conclusion.disease is null THEN '' else '' end as 	heart_disease
,CASE WHEN assess_conclusion.food_consume is null THEN '' else '' end as 	heart_food_consume
,CASE WHEN assess_conclusion.drug_consume is null THEN '' else '' end as 	heart_drug_consume
,CASE WHEN assess_conclusion.exercise is null THEN '' else '' end as 	heart_exercise
,CASE WHEN assess_conclusion.tension is null THEN '' else '' end as 	heart_tension
,CASE WHEN assess_conclusion.bmi is null THEN '' else '' end as 	heart_bmi
,CASE WHEN assess_conclusion.waist is null THEN '' else '' end as 	heart_waist
,CASE WHEN assess_conclusion.fat is null THEN '' else '' end as 	heart_fat
,CASE WHEN assess_conclusion.assess_conclusion_notice is null THEN '' else '' end as 	heart_comment
,t_nhso_doctor_conclusion.doctor_conclusion_in_risk_condition as 	result_risk
,t_nhso_doctor_conclusion.doctor_conclusion_risk_topic as 	result_risk_detail
,case when (t_nhso_doctor_conclusion.doctor_conclusion_chronic_disease <> '')
       then '1' else '0'
       end as 	result_cronic
,t_nhso_doctor_conclusion.doctor_conclusion_chronic_disease as 	result_cronic_detail
,t_nhso_doctor_conclusion.doctor_conclusion_result_suggest as 	result_suggest
,t_nhso_doctor_conclusion.doctor_conclusion_result_manual_change as 	result_manual_change
,t_nhso_doctor_conclusion.doctor_conclusion_result_other_change_detail as 	result_other_change
,t_nhso_cause_behavior.cause_behavior_used_to_take as 	changebehaviour
,t_nhso_cause_behavior.cause_behavior_take_time as 	changebehaviour_count
,case when(t_nhso_cause_behavior.f_nhso_cause_behavior_frequency_id = '01')
      then '1' 
      when(t_nhso_cause_behavior.f_nhso_cause_behavior_frequency_id = '02')
      then '2' 
      when(t_nhso_cause_behavior.f_nhso_cause_behavior_frequency_id = '03')
      then '3' 
      else t_nhso_cause_behavior.f_nhso_cause_behavior_frequency_id
      end as 	changebehaviour_after
,t_nhso_cause_behavior.cause_behavior_frequency_description as 	changebehaviour_after_detail
,case when(t_nhso_cause_behavior.f_nhso_cause_behavior_feeling_id = '01')
      then '1'
      when(t_nhso_cause_behavior.f_nhso_cause_behavior_feeling_id = '02')
      then '2'
      when(t_nhso_cause_behavior.f_nhso_cause_behavior_feeling_id = '03')
      then '3'
      when(t_nhso_cause_behavior.f_nhso_cause_behavior_feeling_id = '99')
      then '4'
      else t_nhso_cause_behavior.f_nhso_cause_behavior_feeling_id
      end as 	changebehaviour_feel
,t_nhso_cause_behavior.cause_behavior_feeling_description as 	changebehaviour_feel_detail
, case when length(t_nhso_service.service_date_time)>=10
        then to_char(to_date(to_number(
        substr(t_nhso_service.service_date_time,1,4),'9999')-543 || 
        substr(t_nhso_service.service_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''   
   end as 	record_date
, case when length(t_nhso_service.service_modify_date_time)>=10
        then to_char(to_date(to_number(
        substr(t_nhso_service.service_modify_date_time,1,4),'9999')-543 || 
        substr(t_nhso_service.service_modify_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''
    end as 	modify_date
,b_site.b_visit_office_id as pcucode
,  case  when (length(t_visit.visit_begin_visit_time) >=10) 
        then to_char(to_date(to_number(
        substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
        substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''  
   end as date_serv 
   ,t_patient.patient_pid as pid--เพิ่มฟิลด์ ตาม datadict
,'1' as active
FROM   t_nhso_service
    INNER JOIN t_visit ON (t_visit.t_visit_id = t_nhso_service.t_visit_id)
    LEFT JOIN t_patient ON t_patient.t_patient_id = t_visit.t_patient_id
    LEFT JOIN t_health_family ON t_nhso_service.t_health_family_id = t_health_family.t_health_family_id
    LEFT JOIN t_visit_physical_exam ON t_visit_physical_exam.t_visit_id = t_visit.t_visit_id
    LEFT JOIN t_nhso_vital_sign  ON t_nhso_vital_sign.t_nhso_service_id = t_nhso_service.t_nhso_service_id 
    LEFT JOIN 
          (select father.t_nhso_service_id,father.t_health_family_id
                      ,max(case when (father.f_nhso_relate_person_id = '01' and father.f_nhso_disease_group_id = '01')
                            then father.f_nhso_disease_status_id
                            else '0' end) as 	father_dm
                      ,max(case when (father.f_nhso_relate_person_id = '01' and father.f_nhso_disease_group_id = '02')
                            then father.f_nhso_disease_status_id
                            else '0' end) as 	father_ht
                      ,max(case when (father.f_nhso_relate_person_id = '01' and father.f_nhso_disease_group_id = '03')
                            then father.f_nhso_disease_status_id
                            else '0' end) as 	father_acs
                      ,max(case when (father.f_nhso_relate_person_id = '02' and father.f_nhso_disease_group_id = '01')
                            then father.f_nhso_disease_status_id
                            else '0' end) as 	mother_dm
                      ,max(case when (father.f_nhso_relate_person_id = '02' and father.f_nhso_disease_group_id = '02')
                            then father.f_nhso_disease_status_id
                            else '0' end) as 	mother_ht
                      ,max(case when (father.f_nhso_relate_person_id = '02' and father.f_nhso_disease_group_id = '03')
                            then father.f_nhso_disease_status_id
                            else '0' end) as 	mother_acs
                      ,max(case when (father.f_nhso_relate_person_id = '03' and father.f_nhso_disease_group_id = '01')
                            then father.f_nhso_disease_status_id
                            else '0' end) as 	other_dm
                      ,max(case when (father.f_nhso_relate_person_id = '03' and father.f_nhso_disease_group_id = '02')
                            then father.f_nhso_disease_status_id
                            else '0' end) as 	other_ht
                      ,max(case when (father.f_nhso_relate_person_id = '03' and father.f_nhso_disease_group_id = '03')
                            then father.f_nhso_disease_status_id
                            else '0' end) as 	other_acs
           from t_nhso_service
                left join t_nhso_family_history as 	father on father.t_nhso_service_id = t_nhso_service.t_nhso_service_id
           group by father.t_nhso_service_id,father.t_health_family_id
                ) as 	family_history ON t_nhso_service.t_nhso_service_id = family_history.t_nhso_service_id
     LEFT JOIN t_nhso_family ON t_nhso_family.t_nhso_service_id = t_nhso_service.t_nhso_service_id
     LEFT JOIN  
          (select t_nhso_risk_factor.t_nhso_service_id
                        , max(case when (t_nhso_risk_factor.f_nhso_risk_type_id = '01')
                                    then t_nhso_risk_factor.f_nhso_risk_status_id
                                    else '0' end) as 	risk_01
                        , max(case when (t_nhso_risk_factor.f_nhso_risk_type_id = '02')
                                   then t_nhso_risk_factor.f_nhso_risk_status_id
                                   else '0' end) as 	risk_02
                        , max(case when (t_nhso_risk_factor.f_nhso_risk_type_id = '03')
                                   then t_nhso_risk_factor.f_nhso_risk_status_id
                                   else '0' end) as 	risk_03
                        , max(case when (t_nhso_risk_factor.f_nhso_risk_type_id = '04')
                                   then t_nhso_risk_factor.f_nhso_risk_status_id
                                   else '0' end) as 	risk_04
                        , max(case when (t_nhso_risk_factor.f_nhso_risk_type_id = '05')
                                   then t_nhso_risk_factor.f_nhso_risk_status_id
                                   else '0' end) as 	risk_05
                        , max(case when (t_nhso_risk_factor.f_nhso_risk_type_id = '06')
                                   then t_nhso_risk_factor.f_nhso_risk_status_id
                                   else '0' end) as 	risk_06
                        , t_nhso_risk_factor.f_nhso_medication_status_id
                        , t_nhso_risk_factor.risk_factor_medication_name             
           from t_nhso_service
                left join t_nhso_risk_factor on t_nhso_risk_factor.t_nhso_service_id = t_nhso_service.t_nhso_service_id
           group by t_nhso_risk_factor.t_nhso_service_id
                        ,t_nhso_risk_factor.f_nhso_medication_status_id
                        ,t_nhso_risk_factor.risk_factor_medication_name
           ) as 	risk_factor ON risk_factor.t_nhso_service_id = t_nhso_service.t_nhso_service_id      
     LEFT JOIN 
          (select t_nhso_excercise.t_nhso_service_id
                      , t_nhso_excercise.excercise_is_usually
                      , t_nhso_excercise.f_nhso_excercise_frequency_id as 	frequency
                      , t_nhso_excercise.excercise_time_week as 	PER_Week
                      , t_nhso_excercise.f_nhso_excercise_time_period_id as 	time_period
                      , t_nhso_excercise.excercise_minute_time as 	minute_time
                      , case when(t_nhso_excercise.f_nhso_excercise_group_id = '01')
                             then '1'
                             else '0' end as 	excercise_run
                      , case when(t_nhso_excercise.f_nhso_excercise_group_id = '02')
                             then '1'
                             else '0' end as 	excercise_walk
                      , case when(t_nhso_excercise.f_nhso_excercise_group_id = '03')
                             then '1'
                             else '0' end as 	excercise_sport
                      , case when(t_nhso_excercise.f_nhso_excercise_group_id = '99')
                             then '1'
                             else '0' end as 	excercise_other
                      , t_nhso_excercise.excercise_name
            from t_nhso_excercise
            ) as 	excercise ON t_nhso_service.t_nhso_service_id = excercise.t_nhso_service_id
    LEFT JOIN t_nhso_food as 	food_taste ON food_taste.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN t_nhso_food_behavior as 	meat_01 ON (t_nhso_service.t_nhso_service_id = meat_01.t_nhso_service_id and  meat_01.f_nhso_food_group_id = '01')
    LEFT JOIN t_nhso_food_behavior as 	coconut_02 ON (t_nhso_service.t_nhso_service_id = coconut_02.t_nhso_service_id and  coconut_02.f_nhso_food_group_id = '02')
    LEFT JOIN t_nhso_food_behavior as 	fruit_03 ON (t_nhso_service.t_nhso_service_id = fruit_03.t_nhso_service_id and  fruit_03.f_nhso_food_group_id = '03')
    LEFT JOIN t_nhso_food_behavior as 	bearkfast_04 ON (t_nhso_service.t_nhso_service_id = bearkfast_04.t_nhso_service_id and  bearkfast_04.f_nhso_food_group_id = '04')
    LEFT JOIN  t_nhso_food_behavior  as fat_11 ON (t_nhso_service.t_nhso_service_id = fat_11.t_nhso_service_id and fat_11.f_nhso_food_group_id = '11')
    LEFT JOIN t_nhso_food_behavior as 	homo_12 ON (t_nhso_service.t_nhso_service_id = homo_12.t_nhso_service_id and  homo_12.f_nhso_food_group_id = '12')
    LEFT JOIN t_nhso_food_behavior as 	dh_13 ON (t_nhso_service.t_nhso_service_id = dh_13.t_nhso_service_id and  dh_13.f_nhso_food_group_id = '13')
    LEFT JOIN t_nhso_dope_drug as 	cigar_01 ON (t_nhso_service.t_nhso_service_id = cigar_01.t_nhso_service_id and cigar_01.f_nhso_drug_type_id = '01')
    LEFT JOIN t_nhso_dope_drug as 	drink_02 ON (t_nhso_service.t_nhso_service_id = drink_02.t_nhso_service_id and drink_02.f_nhso_drug_type_id = '02')
    LEFT JOIN t_nhso_doctor_conclusion  ON t_nhso_doctor_conclusion.t_nhso_service_id = t_nhso_service.t_nhso_service_id
    LEFT JOIN t_nhso_cause_behavior  ON t_nhso_cause_behavior.t_nhso_service_id = t_nhso_service.t_nhso_service_id 
    LEFT JOIN t_nhso_strain as	person_ht ON (person_ht.t_nhso_service_id = t_nhso_service.t_nhso_service_id and person_ht.strain_topic = 'ใจร้อน')
    LEFT JOIN t_nhso_strain as person_ir ON (person_ir.t_nhso_service_id = t_nhso_service.t_nhso_service_id and person_ir.strain_topic = 'หงุดหงิดง่าย')
    LEFT JOIN t_nhso_strain as person_serious ON (person_serious.t_nhso_service_id = t_nhso_service.t_nhso_service_id and person_serious.strain_topic = 'เอาจริงเอาจัง')
    LEFT JOIN t_nhso_strain as person_afraid ON (person_afraid.t_nhso_service_id = t_nhso_service.t_nhso_service_id and person_afraid.strain_topic = 'รู้สึกกลัว/วิตกกังวล')
    LEFT JOIN t_nhso_strain as person_excite ON (person_excite.t_nhso_service_id = t_nhso_service.t_nhso_service_id and person_excite.strain_topic = 'ตื่นเต้นง่าย')
    LEFT JOIN t_nhso_strain as 	person_other ON (person_other.t_nhso_service_id = t_nhso_service.t_nhso_service_id and person_other.strain_topic = 'อื่นๆ')
    LEFT JOIN t_nhso_strain as job_hard ON (job_hard.t_nhso_service_id = t_nhso_service.t_nhso_service_id and job_hard.strain_topic = 'งานหนัก')
    LEFT JOIN t_nhso_strain as job_relex ON (job_relex.t_nhso_service_id = t_nhso_service.t_nhso_service_id and job_relex.strain_topic = 'ไม่มีเวลาพักผ่อน')
    LEFT JOIN t_nhso_strain as job_frindProblem ON (job_frindProblem.t_nhso_service_id = t_nhso_service.t_nhso_service_id and job_frindProblem.strain_topic = 'เพื่อนร่วมงานมีปัญหา')
    LEFT JOIN t_nhso_strain as job_Workbad ON (job_Workbad.t_nhso_service_id = t_nhso_service.t_nhso_service_id and job_Workbad.strain_topic = 'ทำงานไม่ได้ดั่งใจ')
    LEFT JOIN t_nhso_strain as job_fault ON (job_fault.t_nhso_service_id = t_nhso_service.t_nhso_service_id and job_fault.strain_topic = 'ถูกตำหนิบ่อย')
    LEFT JOIN t_nhso_strain as job_ground ON (job_ground.t_nhso_service_id = t_nhso_service.t_nhso_service_id and job_ground.strain_topic = 'งานราบรื่นดีไม่มีปัญหา')
    LEFT JOIN t_nhso_strain as family_respon ON (family_respon.t_nhso_service_id = t_nhso_service.t_nhso_service_id and family_respon.strain_topic = 'ต้องรับผิดชอบมาก')
    LEFT JOIN t_nhso_strain as family_relationbad ON (family_relationbad.t_nhso_service_id = t_nhso_service.t_nhso_service_id and family_relationbad.strain_topic = 'สัมพันธภาพไม่ค่อยดี')
    LEFT JOIN  t_nhso_strain as family_nounder ON (family_nounder.t_nhso_service_id = t_nhso_service.t_nhso_service_id and family_nounder.strain_topic = 'ไม่ค่อยเข้าใจกัน')
    LEFT JOIN t_nhso_strain as family_retreat ON (job_frindProblem.t_nhso_service_id = t_nhso_service.t_nhso_service_id and family_retreat.strain_topic = 'ต่างคนต่างอยู่')
    LEFT JOIN t_nhso_strain as family_costup ON (family_costup.t_nhso_service_id = t_nhso_service.t_nhso_service_id and family_costup.strain_topic = 'ค่าใช้จ่ายมาก')
    LEFT JOIN t_nhso_strain as family_nohousekeeping ON (family_nohousekeeping.t_nhso_service_id = t_nhso_service.t_nhso_service_id and family_nohousekeeping.strain_topic = 'ไม่มีคนช่วยทำงานบ้าน')
    LEFT JOIN t_nhso_strain as family_noproblem ON (family_noproblem.t_nhso_service_id = t_nhso_service.t_nhso_service_id and family_noproblem.strain_topic = 'เป็นสุขดี ไม่มีปัญหา')
    LEFT JOIN t_nhso_strain as 	social_nointerest ON (social_nointerest.t_nhso_service_id = t_nhso_service.t_nhso_service_id and social_nointerest.strain_topic = 'ไม่ค่อยได้สนใจ')
    LEFT JOIN t_nhso_strain as social_trouble ON (social_trouble.t_nhso_service_id = t_nhso_service.t_nhso_service_id and social_trouble.strain_topic = 'ช่างวุ่นวาย น่าเบื่อ')
    LEFT JOIN t_nhso_strain as social_goodluck ON (social_goodluck.t_nhso_service_id = t_nhso_service.t_nhso_service_id and social_goodluck.strain_topic = 'โชคดีมีเพื่อนสนิทไม่เครียด')
    LEFT JOIN t_nhso_strain as 	social_selfish ON (social_selfish.t_nhso_service_id = t_nhso_service.t_nhso_service_id and social_selfish.strain_topic = 'มีแต่คนเห็นแก่ตัวเอาเปรียบ')
    LEFT JOIN t_nhso_strain as 	social_normal ON (social_normal.t_nhso_service_id = t_nhso_service.t_nhso_service_id and social_normal.strain_topic = 'ทำใจได้สบายๆ')
    LEFT JOIN 
          (select t_nhso_assess_conclusion.t_nhso_service_id
                      , case when(t_nhso_assess_conclusion.f_nhso_assess_conclusion_topic_id = '01')
                             then '1'
                             else '0' end as 	family_history
                      , case when(t_nhso_assess_conclusion.f_nhso_assess_conclusion_topic_id = '02')
                             then '1'
                             else '0' end as 	smoke_behaviour
                      , case when(t_nhso_assess_conclusion.f_nhso_assess_conclusion_topic_id = '03')
                             then '1'
                             else '0' end as 	drink_behaviour
                      , case when(t_nhso_assess_conclusion.f_nhso_assess_conclusion_topic_id = '04')
                             then '1'
                             else '0' end as 	diabutes
                      , case when(t_nhso_assess_conclusion.f_nhso_assess_conclusion_topic_id = '05')
                             then '1'
                             else '0' end as 	bloodpressure
                      , case when(t_nhso_assess_conclusion.f_nhso_assess_conclusion_topic_id = '06')
                             then '1'
                             else '0' end as 	disease
                      , case when(t_nhso_assess_conclusion.f_nhso_assess_conclusion_topic_id = '07')
                             then '1'
                             else '0' end as 	food_consume
                      , case when(t_nhso_assess_conclusion.f_nhso_assess_conclusion_topic_id = '08')
                             then '1'
                             else '0' end as 	drug_consume
                      , case when(t_nhso_assess_conclusion.f_nhso_assess_conclusion_topic_id = '09')
                             then '1'
                             else '0' end as 	exercise
                      , case when(t_nhso_assess_conclusion.f_nhso_assess_conclusion_topic_id = '10')
                             then '1'
                             else '0' end as 	tension
                      , case when(t_nhso_assess_conclusion.f_nhso_assess_conclusion_topic_id = '11')
                             then '1'
                             else '0' end as 	bmi
                      , case when(t_nhso_assess_conclusion.f_nhso_assess_conclusion_topic_id = '12')
                             then '1'
                             else '0' end as 	waist
                      , case when(t_nhso_assess_conclusion.f_nhso_assess_conclusion_topic_id = '13')
                             then '1'
                             else '0' end as 	fat 
                      ,t_nhso_assess_conclusion.assess_conclusion_notice as 	assess_conclusion_notice
            from t_nhso_assess_conclusion
            ) as 	assess_conclusion ON t_nhso_service.t_nhso_service_id = assess_conclusion.t_nhso_service_id
       ,b_site

WHERE 
 t_visit.f_visit_status_id <> '4' 
    AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
	AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?
