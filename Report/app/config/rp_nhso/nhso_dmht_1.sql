select t_diag_icd10.diag_icd10_number as 	icd_code
       , t_visit.visit_hn as 	pcu_code
       , substring(t_visit.visit_begin_visit_time,1,10) as 	date_recor
       , t_patient.patient_birthday as 	birthday
       , case when(t_visit.visit_patient_age = '')	
              then '0'	
              else cast(t_visit.visit_patient_age as int) end as 	age
       , t_patient.f_sex_id as 	sex
       , t_patient.f_patient_occupation_id as 	occupation
       , t_nhso_dmht_diagnosis.f_nhso_family_income_enough_id as 	income
       , t_nhso_dmht_diagnosis.f_nhso_dm_diag_type_id as 	dm_type
       , t_nhso_dmht_diagnosis.diagnosis_dm_date_time as 	dm_date
       , t_nhso_dmht_diagnosis.f_nhso_ht_diag_type_id as 	ht_type
       , t_nhso_dmht_diagnosis.diagnosis_ht_date_time as 	ht_date
       , t_nhso_dmht_diagnosis.f_nhso_dmht_comorbidities_id as 	comor
       , t_nhso_dmht_diagnosis.diagnosis_comor_other as 	comor_othe
       , history.history_09 as 	strain_lev
       , history.history_08 as 	use_cigar
       , history.history_08_des as 	cigar_freq
       , history.history_01 as 	drink
       , history.history_01_des as 	drink_des
       , history.history_02 as 	man4
       , history.history_02_des as 	man4_des
       , history.history_03 as 	women2
       , history.history_03_des as 	women2_des
       , history.history_04 as 	exer30
       , history.history_04_des as 	exer30_des
       , history.history_05 as 	eat_savori
       , history.history_05_des as 	eat_sa_des
       , history.history_06 as 	eat_sw
       , history.history_06_des as 	eat_sw_des
       , history.history_07 as 	eat_st
       , history.history_07_des as 	eat_st_des
       , history_family.family_history_01 as 	dm
       , history_family.family_history_01_des as 	dm_status
       , history_family.family_history_02 as 	ht_family
       , history_family.family_history_02_des as 	ht_status
       , history_family.family_history_03 as 	cvd_55
       , history_family.family_history_03_des as 	cvd_55_sta
       , history_family.family_history_04 as 	cvd_65
       , history_family.family_history_04_des as 	cvd_65_sta
       , history_family.family_history_05 as 	ihd_55
       , history_family.family_history_05_des as 	ihd_55_des
       , history_family.family_history_06 as 	ihd_65
       , history_family.family_history_06_des as 	ihd_65_des
       , history_family.family_history_07 as 	dyslip
       , history_family.family_history_07_des as 	dyslip_des
       , physical_exam.physical_01 as 	cataract
       , physical_exam.physical_01_des as 	cataract_d
       , physical_exam.physical_02 as 	nonpro
       , physical_exam.physical_02_des as 	nonpro_des
       , physical_exam.physical_03 as 	prolife
       , physical_exam.physical_03_des as 	prolife_de
       , physical_exam.physical_04 as 	ht
       , physical_exam.physical_04_des as 	ht_des
       , physical_exam.physical_05 as 	sens
       , physical_exam.physical_05_des as 	sens_des
       , physical_exam.physical_06 as 	motor
       , physical_exam.physical_06_des as 	motor_des
       , physical_exam.physical_07 as 	auton
       , physical_exam.physical_07_des as 	auton_des
       , physical_exam.physical_08 as 	fungal
       , physical_exam.physical_08_des as 	fungal_des
       , physical_exam.physical_09 as 	erosion
       , physical_exam.physical_09_des as 	erosion_de
       , physical_exam.physical_10 as 	ulcer
       , physical_exam.physical_10_des as 	ulcer_des
       , physical_exam.physical_11 as 	struct
       , physical_exam.physical_11_des as 	struct_des
       , physical_exam.physical_12 as 	neuro
       , physical_exam.physical_12_des as 	neuro_des
       , physical_exam.physical_13 as 	vasc
       , physical_exam.physical_13_des as 	vasc_des
       , physical_exam.physical_14 as 	infec
       , physical_exam.physical_14_des as 	infec_des
       , physical_exam.physical_15 as 	dental
       , physical_exam.physical_15_des as 	dental_des
       , physical_exam.physical_16 as 	gingi
       , physical_exam.physical_16_des as 	gingi_des
       , physical_exam.physical_17 as 	ot_phy
       , physical_exam.physical_17_des as 	ot_phy_des
       , physical_exam.physical_18 as 	cardio
       , physical_exam.physical_18_des as 	card_des
       , physical_exam.physical_19 as 	heart_murm
       , physical_exam.physical_19_des as 	heart_des
       , physical_exam.physical_20 as 	dys
       , physical_exam.physical_20_des as 	dys_des
       , non_medication.non_medication_01 as 	change_att
       , non_medication.non_medication_02 as 	eat_approp
       , non_medication.non_medication_03 as 	eat_drug_c
       , non_medication.non_medication_04 as 	excer_app
       , non_medication.non_medication_05 as 	protect_ac
from t_nhso_dmht_diagnosis	
        inner join t_visit on (t_visit.t_visit_id = t_nhso_dmht_diagnosis.t_visit_id and t_visit.f_visit_status_id <> '4')	
        left join t_patient on t_visit.t_patient_id = t_patient.t_patient_id        	
        left join t_diag_icd10 on(t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id 	
                                    and t_diag_icd10.diag_icd10_active = '1'	
                                    and t_diag_icd10.f_diag_icd10_type_id = '1')	
        left join (select t_nhso_dmht_history.t_visit_id as 	visit_id
                        , max(case when (t_nhso_dmht_history.f_nhso_dmht_history_topic_id = '01')	
                                    then t_nhso_dmht_history.f_nhso_food_behavior_frequency_id	
                                    else '0' end) as 	history_01
                        , max(case when (t_nhso_dmht_history.f_nhso_dmht_history_topic_id = '02')	
                                   then t_nhso_dmht_history.f_nhso_food_behavior_frequency_id	
                                   else '0' end) as 	history_02
                        , max(case when (t_nhso_dmht_history.f_nhso_dmht_history_topic_id = '03')	
                                   then t_nhso_dmht_history.f_nhso_food_behavior_frequency_id	
                                   else '0' end) as 	history_03
                        , max(case when (t_nhso_dmht_history.f_nhso_dmht_history_topic_id = '04')	
                                   then t_nhso_dmht_history.f_nhso_food_behavior_frequency_id	
                                   else '0' end) as 	history_04
                        , max(case when (t_nhso_dmht_history.f_nhso_dmht_history_topic_id = '05')	
                                   then t_nhso_dmht_history.f_nhso_food_behavior_frequency_id	
                                   else '0' end) as 	history_05
                        , max(case when (t_nhso_dmht_history.f_nhso_dmht_history_topic_id = '06')	
                                   then t_nhso_dmht_history.f_nhso_food_behavior_frequency_id	
                                   else '0' end) as 	history_06
                        , max(case when (t_nhso_dmht_history.f_nhso_dmht_history_topic_id = '07')	
                                    then t_nhso_dmht_history.f_nhso_food_behavior_frequency_id	
                                    else '0' end) as 	history_07
                        , max(case when(f_nhso_dmht_history_topic_id = '01' and history_topic_other is not null and history_topic_other <> '')	
                                   then t_nhso_dmht_history.history_topic_other	
                                   else '' end) as 	history_01_des                       
                        , max(case when(f_nhso_dmht_history_topic_id = '02' and history_topic_other is not null and history_topic_other <> '')	
                                   then t_nhso_dmht_history.history_topic_other	
                                   else '' end) as 	history_02_des
                        , max(case when(f_nhso_dmht_history_topic_id = '03' and history_topic_other is not null and history_topic_other <> '')	
                                   then t_nhso_dmht_history.history_topic_other	
                                   else '' end) as 	history_03_des                       
                        , max(case when(f_nhso_dmht_history_topic_id = '04' and history_topic_other is not null and history_topic_other <> '')	
                                   then t_nhso_dmht_history.history_topic_other	
                                   else '' end) as 	history_04_des
                        , max(case when(f_nhso_dmht_history_topic_id = '05' and history_topic_other is not null and history_topic_other <> '')	
                                   then t_nhso_dmht_history.history_topic_other	
                                   else '' end) as 	history_05_des
                        , max(case when(f_nhso_dmht_history_topic_id = '06' and history_topic_other is not null and history_topic_other <> '')	
                                   then t_nhso_dmht_history.history_topic_other	
                                   else '' end) as 	history_06_des                       
                        , max(case when(f_nhso_dmht_history_topic_id = '07' and history_topic_other is not null and history_topic_other <> '')	
                                   then t_nhso_dmht_history.history_topic_other	
                                   else '' end) as 	history_07_des
                        , max(case when(f_nhso_dmht_history_topic_id = '08')	
                                   then t_nhso_dmht_history.history_used_to_cigar	
                                   else '' end) as 	history_08
                        , max(case when(f_nhso_dmht_history_topic_id = '08')	
                                   then t_nhso_dmht_history.history_cigar_frequency	
                                   else '' end) as 	history_08_des
                        , max(case when(f_nhso_dmht_history_topic_id = '09')	
                                   then t_nhso_dmht_history.history_strain_level	
                                   else '' end) as 	history_09
                    from t_nhso_dmht_history	
                    group by t_nhso_dmht_history.t_visit_id	
                    ) as 	history on history.visit_id = t_visit.t_visit_id
         left join (select t_nhso_dmht_family_history.t_visit_id as 	visit_id
                        , max(case when (t_nhso_dmht_family_history.f_nhso_disease_group_id = '01')	
                                    then t_nhso_dmht_family_history.f_nhso_disease_status_id	
                                    else '0' end) as 	family_history_01
                        , max(case when (t_nhso_dmht_family_history.f_nhso_disease_group_id = '02')	
                                   then t_nhso_dmht_family_history.f_nhso_disease_status_id	
                                   else '0' end) as 	family_history_02
                        , max(case when (t_nhso_dmht_family_history.f_nhso_disease_group_id = '04' and t_nhso_dmht_family_history.f_nhso_age_range_id = '01')	
                                   then t_nhso_dmht_family_history.f_nhso_disease_status_id	
                                   else '0' end) as 	family_history_03
                        , max(case when (t_nhso_dmht_family_history.f_nhso_disease_group_id = '04' and t_nhso_dmht_family_history.f_nhso_age_range_id = '02')	
                                   then t_nhso_dmht_family_history.f_nhso_disease_status_id	
                                   else '0' end) as 	family_history_04
                        , max(case when (t_nhso_dmht_family_history.f_nhso_disease_group_id = '05' and t_nhso_dmht_family_history.f_nhso_age_range_id = '01')	
                                   then t_nhso_dmht_family_history.f_nhso_disease_status_id	
                                   else '0' end) as 	family_history_05
                        , max(case when (t_nhso_dmht_family_history.f_nhso_disease_group_id = '05' and t_nhso_dmht_family_history.f_nhso_age_range_id = '02')	
                                   then t_nhso_dmht_family_history.f_nhso_disease_status_id	
                                   else '0' end) as 	family_history_06
                        , max(case when (t_nhso_dmht_family_history.f_nhso_disease_group_id = '06')	
                                    then t_nhso_dmht_family_history.f_nhso_disease_status_id	
                                    else '0' end) as 	family_history_07
                        , max(case when(f_nhso_disease_group_id = '01' and family_history_relate_person is not null and family_history_relate_person <> '')	
                                   then t_nhso_dmht_family_history.family_history_relate_person	
                                   else '' end) as 	family_history_01_des                       
                        , max(case when(f_nhso_disease_group_id = '02' and family_history_relate_person is not null and family_history_relate_person <> '')	
                                   then t_nhso_dmht_family_history.family_history_relate_person	
                                   else '' end) as 	family_history_02_des
                        , max(case when(f_nhso_disease_group_id = '04' and t_nhso_dmht_family_history.f_nhso_age_range_id = '01' 	
                                        and family_history_relate_person is not null and family_history_relate_person <> '')	
                                   then t_nhso_dmht_family_history.family_history_relate_person	
                                   else '' end) as 	family_history_03_des                       
                        , max(case when(f_nhso_disease_group_id = '04' and t_nhso_dmht_family_history.f_nhso_age_range_id = '02' 	
                                        and family_history_relate_person is not null and family_history_relate_person <> '')	
                                   then t_nhso_dmht_family_history.family_history_relate_person	
                                   else '' end) as 	family_history_04_des
                        , max(case when(f_nhso_disease_group_id = '05' and t_nhso_dmht_family_history.f_nhso_age_range_id = '01' 	
                                        and family_history_relate_person is not null and family_history_relate_person <> '')	
                                   then t_nhso_dmht_family_history.family_history_relate_person	
                                   else '' end) as 	family_history_05_des
                        , max(case when(f_nhso_disease_group_id = '05' and t_nhso_dmht_family_history.f_nhso_age_range_id = '02' 	
                                        and family_history_relate_person is not null and family_history_relate_person <> '')	
                                   then t_nhso_dmht_family_history.family_history_relate_person	
                                   else '' end) as 	family_history_06_des                       
                        , max(case when(f_nhso_disease_group_id = '06' and family_history_relate_person is not null and family_history_relate_person <> '')	
                                   then t_nhso_dmht_family_history.family_history_relate_person	
                                   else '' end) as 	family_history_07_des
                    from t_nhso_dmht_family_history	
                    group by t_nhso_dmht_family_history.t_visit_id	
                    ) as 	history_family on history_family.visit_id = t_visit.t_visit_id
        left join (select t_nhso_dmht_non_medication.t_visit_id as 	visit_id
                        , max(case when (t_nhso_dmht_non_medication.f_nhso_dmht_non_medication_topic_id = '01')	
                                    then t_nhso_dmht_non_medication.non_medication_is_select	
                                    else '0' end) as 	non_medication_01
                        , max(case when (t_nhso_dmht_non_medication.f_nhso_dmht_non_medication_topic_id = '02')	
                                   then t_nhso_dmht_non_medication.non_medication_is_select	
                                   else '0' end) as 	non_medication_02
                        , max(case when (t_nhso_dmht_non_medication.f_nhso_dmht_non_medication_topic_id = '03')	
                                   then t_nhso_dmht_non_medication.non_medication_is_select	
                                   else '0' end) as 	non_medication_03
                        , max(case when (t_nhso_dmht_non_medication.f_nhso_dmht_non_medication_topic_id = '04')	
                                   then t_nhso_dmht_non_medication.non_medication_is_select	
                                   else '0' end) as 	non_medication_04
                        , max(case when (t_nhso_dmht_non_medication.f_nhso_dmht_non_medication_topic_id = '05')	
                                   then t_nhso_dmht_non_medication.non_medication_is_select	
                                   else '0' end) as 	non_medication_05
                    from t_nhso_dmht_non_medication	
                    group by t_nhso_dmht_non_medication.t_visit_id	
                    ) as 	non_medication on non_medication.visit_id = t_visit.t_visit_id
        left join (select t_nhso_dmht_physical_exam.t_visit_id as 	visit_id
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '01') 	
                                      then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_01    
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '01')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_01_des
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '02')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_02
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '02')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_02_des
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '03')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_03
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '03')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_03_des
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '04')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_04   
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '04')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_04_des
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '05')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_05
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '05')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_05_des    
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '06')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_06
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '06')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_06_des
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '07')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_07
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '07')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_07_des
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '08')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_08
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '08')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_08_des
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '09')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_09    
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '09')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_09_des
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '10')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_10
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '10')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_10_des
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '11')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_11
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '11')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_11_des
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '12')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_12   
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '12')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_12_des
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '13')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_13
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '13')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_13_des    
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '14')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_14
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '14')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_14_des
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '15')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_15
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '15')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_15_des
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '16')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_16
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '16')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_16_des
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '17')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_17
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '17')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_17_des
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '18')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_18
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '18')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_18_des 
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '19')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_19
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '19')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_19_des
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '20')	
                                       then t_nhso_dmht_physical_exam.physical_exam_result	
                                       else '' end) as 	physical_20
                            , max(case when(t_nhso_dmht_physical_exam.f_nhso_dmht_physical_exam_topic_id = '20')	
                                       then t_nhso_dmht_physical_exam.physical_exam_note	
                                       else '' end) as 	physical_20_des 
                    from t_nhso_dmht_physical_exam	
                    group by t_nhso_dmht_physical_exam.t_visit_id	
                    ) as 	physical_exam on t_visit.t_visit_id = physical_exam.visit_id
WHERE t_visit.f_visit_status_id <> '4'	
      AND substring(t_visit.visit_begin_visit_time,1,10) >= ? 	
      AND substring(t_visit.visit_begin_visit_time,1,10) <= ?	
