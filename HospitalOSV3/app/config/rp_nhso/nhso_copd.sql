select t_diag_icd10.diag_icd10_number as 	icd_code
       , t_visit.visit_hn as 	pcu_code
       , substring(t_visit.visit_begin_visit_time,1,10) as 	date_recor
       , t_patient.patient_birthday as 	birthday
       , case when(t_visit.visit_patient_age = '')	
              then '0'	
              else cast(t_visit.visit_patient_age as int) end as 	age
       , t_patient.f_sex_id as 	sex
       , t_patient.f_patient_occupation_id as 	occupation
       , substring(t_diag_icd10.diag_icd10_diagnosis_date,1,10) as 	date_diag
       , t_nhso_copd_diagnosis.f_nhso_copd_comorbidities_id as 	comorbidit
       , t_nhso_copd_diagnosis.diagnosis_comor_other as 	comor_des
       , t_nhso_copd_diagnosis.f_nhso_disease_control_id as 	disease_co
       , t_nhso_copd_diagnosis.diagnosis_follow_up as 	follow_up
       , vital_sign.vital_sign_height as 	height
       , vital_sign.vital_sign_weight as 	weight
       , vital_sign.vital_sign_bp as 	bp
       , vital_sign.vital_sign_pr as 	pr
       , vital_sign.vital_sign_rr as 	rr
       , vital_sign.vital_sign_temp as 	temp
       , examine.examine_01 as 	asthma_mor
       , examine.examine_02 as 	asthma_act
       , examine.examine_03 as 	asthma_dai
       , examine.examine_04 as 	asthma_sti
       , examine.examine_05 as 	asthma_les
       , examine.examine_06 as 	cough_more
       , examine.examine_07 as 	sputum_mor
       , examine.examine_08 as 	scream_som
       , examine.examine_09 as 	less10
       , examine.examine_10 as 	more10
       , examine.examine_11 as 	more20
       , examine.examine_12 as 	more30
       , examine.examine_13 as 	around_pol
       , examine.examine_14 as 	ball_of_me
       , examine.examine_99 as 	other_hist
       , examine.examine_topic_other as 	ot_his_des
       , examine.examine_20 as 	short_acti
       , examine.examine_20_des as 	total_shor
       , examine.examine_21 as 	meet_docto
       , examine.examine_21_des as 	total_meet
       , examine.examine_22 as 	keep_er
       , examine.examine_22_des as 	total_keep
       , examine.examine_23 as 	admit_asth
       , examine.examine_23_des as 	total_admi
       , examine.examine_24 as 	sick_leave
       , examine.examine_24_des as 	total_sick
       , examine.examine_25 as 	push_conve
       , examine.examine_25_des as 	total_push
       , examine.examine_26 as 	type_drug_
       , examine.examine_26_des as 	total_type
       , examine.examine_27 as 	stop_cigar
       , physical_exam.exam_01 as 	muscle_bre
       , physical_exam.exam_01_des as 	muscle_des
       , physical_exam.exam_02 as 	chest_circ
       , physical_exam.exam_02_des as 	chest_des
       , physical_exam.exam_03 as 	lungs_scre
       , physical_exam.exam_03_des as 	lungs_des
       , physical_exam.exam_04 as 	exhale_lon
       , physical_exam.exam_04_des  as 	exhale_des
       , physical_exam.exam_05 as 	breath_les
       , physical_exam.exam_05_des as 	breath_des
       , physical_exam.exam_99 as 	other_body
       , physical_exam.exam_99_other as 	ot_bdy_des
       , physical_exam.exam_99_des as 	ot_bdy_res
       , physical_exam.exam_11 as 	x_lungs
       , physical_exam.exam_11_des as 	x_lung_des
       , physical_exam.exam_12 as 	peak_flow
       , physical_exam.exam_12_des as 	peak_f_des
       , physical_exam.exam_13 as 	ecg
       , physical_exam.exam_13_des as 	ecg_descri
       , treat_guide.guide_method_01 as 	lungs1
       , treat_guide.guide_method_02 as 	lungs2
       , treat_guide.guide_method_03 as 	lungs3
       , treat_guide.guide_method_04 as 	lungs4
       , treat_guide.guide_method_05 as 	lungs_acut
       , treat_guide.guide_method_06 as 	take_of_le
       , treat_guide.guide_method_07 as 	recover_lu
       , treat_guide.guide_method_08 as 	refer
       , medication.medication_drug_01 as 	cor_drg
       , medication.medication_dose_01 as 	cor_dos
       , medication.medication_drug_02 as 	theo_drg
       , medication.medication_dose_02 as 	theo_dos
       , medication.medication_drug_03 as 	sustain_dr
       , medication.medication_dose_03 as 	sustain_do
       , medication.medication_drug_04 as 	antic_drg
       , medication.medication_dose_04 as 	antc_dos
       , medication.medication_drug_05 as 	ago_s_drg
       , medication.medication_dose_05 as 	ago_s_dos
       , medication.medication_drug_06 as 	ago_l_drg
       , medication.medication_dose_06 as 	ago_l_dos
       , medication.medication_drug_07 as 	antib_drg
       , medication.medication_dose_07 as 	antib_dos
       , medication.medication_drug_08 as 	oxygen_dru
       , medication.medication_dose_08 as 	oxygen_dos
       , medication.medication_drug_99 as 	other_drug
       , medication.medication_dose_99 as 	other_dose
       , non_medication.non_medication_01 as 	disease_at
       , non_medication.non_medication_02 as 	push_recov
       , non_medication.non_medication_03 as 	eat_drug_c
       , non_medication.non_medication_04 as 	exercise_c
       , non_medication.non_medication_05 as 	suggest_st
       , non_medication.non_medication_06 as 	protect_in
       , non_medication.non_medication_07 as 	suggest_in
from t_nhso_copd_diagnosis	
        inner join t_visit on (t_visit.t_visit_id = t_nhso_copd_diagnosis.t_visit_id and t_visit.f_visit_status_id <> '4')	
        left join t_patient on t_visit.t_patient_id = t_patient.t_patient_id        	
        left join t_diag_icd10 on(t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id 	
                                    and t_diag_icd10.diag_icd10_active = '1'	
                                    and t_diag_icd10.f_diag_icd10_type_id = '1')	
        left join (select t_nhso_copd_examine.t_visit_id as 	visit_id
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '01')	
                                    then t_nhso_copd_examine.examine_topic_is_have	
                                    else '0' end) as 	examine_01
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '02')	
                                   then t_nhso_copd_examine.examine_topic_is_have	
                                   else '0' end) as 	examine_02
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '03')	
                                   then t_nhso_copd_examine.examine_topic_is_have	
                                   else '0' end) as 	examine_03
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '04')	
                                   then t_nhso_copd_examine.examine_topic_is_have	
                                   else '0' end) as 	examine_04
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '05')	
                                   then t_nhso_copd_examine.examine_topic_is_have	
                                   else '0' end) as 	examine_05
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '06')	
                                   then t_nhso_copd_examine.examine_topic_is_have	
                                   else '0' end) as 	examine_06
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '07')	
                                    then t_nhso_copd_examine.examine_topic_is_have	
                                    else '0' end) as 	examine_07
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '08')	
                                   then t_nhso_copd_examine.examine_topic_is_have	
                                   else '0' end) as 	examine_08
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '09')	
                                   then t_nhso_copd_examine.examine_topic_is_have	
                                   else '0' end) as 	examine_09
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '10')	
                                   then t_nhso_copd_examine.examine_topic_is_have	
                                   else '0' end) as 	examine_10
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '11')	
                                   then t_nhso_copd_examine.examine_topic_is_have	
                                   else '0' end) as 	examine_11
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '12')	
                                   then t_nhso_copd_examine.examine_topic_is_have	
                                   else '0' end) as 	examine_12
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '13')	
                                   then t_nhso_copd_examine.examine_topic_is_have	
                                   else '0' end) as 	examine_13
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '14')	
                                   then t_nhso_copd_examine.examine_topic_is_have	
                                   else '0' end) as 	examine_14
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '99')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_99
                        , max(case when(f_nhso_copd_examine_topic_id = '99' and examine_topic_other is not null and examine_topic_other <> '')	
                                   then t_nhso_copd_examine.examine_topic_other	
                                   else '' end) as 	examine_topic_other
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '20')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_20
                        , max(case when(f_nhso_copd_examine_topic_id = '20' and examine_topic_other is not null and examine_topic_other <> '')	
                                   then t_nhso_copd_examine.examine_topic_other	
                                   else '' end) as 	examine_20_des 
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '21')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_21
                        , max(case when(f_nhso_copd_examine_topic_id = '21' and examine_topic_other is not null and examine_topic_other <> '')	
                                   then t_nhso_copd_examine.examine_topic_other	
                                   else '' end) as 	examine_21_des 
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '22')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_22
                        , max(case when(f_nhso_copd_examine_topic_id = '22' and examine_topic_other is not null and examine_topic_other <> '')	
                                   then t_nhso_copd_examine.examine_topic_other	
                                   else '' end) as 	examine_22_des
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '23')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_23
                        , max(case when(f_nhso_copd_examine_topic_id = '23' and examine_topic_other is not null and examine_topic_other <> '')	
                                   then t_nhso_copd_examine.examine_topic_other	
                                   else '' end) as 	examine_23_des 
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '24')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_24
                        , max(case when(f_nhso_copd_examine_topic_id = '24' and examine_topic_other is not null and examine_topic_other <> '')	
                                   then t_nhso_copd_examine.examine_topic_other	
                                   else '' end) as 	examine_24_des 
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '25')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_25
                        , max(case when(f_nhso_copd_examine_topic_id = '25' and examine_topic_other is not null and examine_topic_other <> '')	
                                   then t_nhso_copd_examine.examine_topic_other	
                                   else '' end) as 	examine_25_des
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '26')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_26
                        , max(case when(f_nhso_copd_examine_topic_id = '26' and examine_topic_other is not null and examine_topic_other <> '')	
                                   then t_nhso_copd_examine.examine_topic_other	
                                   else '' end) as 	examine_26_des
                        , max(case when (t_nhso_copd_examine.f_nhso_copd_examine_topic_id = '27')	
                               then f_nhso_drug_usual_status_id	
                               else '0' end) as 	examine_27
                    from t_nhso_copd_examine	
                    group by t_nhso_copd_examine.t_visit_id	
                    ) as 	examine on examine.visit_id = t_visit.t_visit_id
        left join  (select t_nhso_copd_physical_exam.t_visit_id as 	visit_id
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '01')	
                                   then t_nhso_copd_physical_exam.physical_exam_topic_is_have	
                                   else '0' end) as 	exam_01
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '01' and physical_exam_result is not null and physical_exam_result <> '')	
                                   then t_nhso_copd_physical_exam.physical_exam_result	
                                   else '' end) as 	exam_01_des
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '02')	
                                   then t_nhso_copd_physical_exam.physical_exam_topic_is_have	
                                   else '0' end) as 	exam_02
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '02' and physical_exam_result is not null and physical_exam_result <> '')	
                                   then t_nhso_copd_physical_exam.physical_exam_result	
                                   else '' end) as 	exam_02_des
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '03')	
                                   then t_nhso_copd_physical_exam.physical_exam_topic_is_have	
                                   else '0' end) as 	exam_03
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '03' and physical_exam_result is not null and physical_exam_result <> '')	
                                   then t_nhso_copd_physical_exam.physical_exam_result	
                                   else '' end) as 	exam_03_des
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '04')	
                                   then t_nhso_copd_physical_exam.physical_exam_topic_is_have	
                                   else '0' end) as 	exam_04
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '04' and physical_exam_result is not null and physical_exam_result <> '')	
                                   then t_nhso_copd_physical_exam.physical_exam_result	
                                   else '' end) as 	exam_04_des
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '05')	
                                   then t_nhso_copd_physical_exam.physical_exam_topic_is_have	
                                   else '0' end) as 	exam_05
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '05' and physical_exam_result is not null and physical_exam_result <> '')	
                                   then t_nhso_copd_physical_exam.physical_exam_result	
                                   else '' end) as 	exam_05_des
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '99')	
                                   then t_nhso_copd_physical_exam.physical_exam_topic_is_have	
                                   else '0' end) as 	exam_99
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '99' and physical_exam_topic_other is not null and physical_exam_topic_other <> '')	
                                   then t_nhso_copd_physical_exam.physical_exam_topic_other	
                                   else '' end) as 	exam_99_other                     
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '99' and physical_exam_result is not null and physical_exam_result <> '')	
                                   then t_nhso_copd_physical_exam.physical_exam_result	
                                   else '' end) as 	exam_99_des
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '11')	
                                   then t_nhso_copd_physical_exam.physical_exam_topic_is_have	
                                   else '0' end) as 	exam_11
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '11' and physical_exam_result is not null and physical_exam_result <> '')	
                                   then t_nhso_copd_physical_exam.physical_exam_result	
                                   else '' end) as 	exam_11_des
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '12')	
                                   then t_nhso_copd_physical_exam.physical_exam_topic_is_have	
                                   else '0' end) as 	exam_12
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '12' and physical_exam_result is not null and physical_exam_result <> '')	
                                   then t_nhso_copd_physical_exam.physical_exam_result	
                                   else '' end) as 	exam_12_des
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '13')	
                                   then t_nhso_copd_physical_exam.physical_exam_topic_is_have	
                                   else '0' end) as 	exam_13
                        , max(case when(f_nhso_copd_physical_exam_topic_id = '13' and physical_exam_result is not null and physical_exam_result <> '')	
                                   then t_nhso_copd_physical_exam.physical_exam_result	
                                   else '' end) as 	exam_13_des
                  from t_nhso_copd_physical_exam	
                  group by t_nhso_copd_physical_exam.t_visit_id	
                  ) as 	physical_exam on physical_exam.visit_id = t_visit.t_visit_id
        left join (select t_nhso_copd_treat_guide.t_visit_id as 	visit_id
                        , max(case when(f_nhso_copd_guide_method_id = '01')	
                                   then t_nhso_copd_treat_guide.guide_is_select	
                                   else '' end) as 	guide_method_01       
                        , max(case when(f_nhso_copd_guide_method_id = '02')	
                                   then guide_is_select	
                                   else '' end) as 	guide_method_02
                        , max(case when(f_nhso_copd_guide_method_id = '03')	
                                   then guide_is_select	
                                   else '' end) as 	guide_method_03
                        , max(case when(f_nhso_copd_guide_method_id = '04')	
                                   then guide_is_select	
                                   else '' end) as 	guide_method_04
                        , max(case when(f_nhso_copd_guide_method_id = '05')	
                                   then guide_is_select	
                                   else '' end) as 	guide_method_05
                        , max(case when(f_nhso_copd_guide_method_id = '06')	
                                   then guide_is_select	
                                   else '' end) as 	guide_method_06
                        , max(case when(f_nhso_copd_guide_method_id = '07')	
                                   then guide_is_select	
                                   else '' end) as 	guide_method_07
                        , max(case when(f_nhso_copd_guide_method_id = '08')	
                                   then guide_is_select	
                                   else '' end) as 	guide_method_08
                  from t_nhso_copd_treat_guide	
                  group by t_nhso_copd_treat_guide.t_visit_id	
                  ) as 	treat_guide on treat_guide.visit_id = t_visit.t_visit_id
        left join (select t_nhso_copd_medication.t_visit_id as 	visit_id
                        , max(case when(f_nhso_copd_medication_topic_id = '01' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_copd_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_01      
                        , max(case when(f_nhso_copd_medication_topic_id = '02' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_copd_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_02 
                        , max(case when(f_nhso_copd_medication_topic_id = '03' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_copd_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_03 
                        , max(case when(f_nhso_copd_medication_topic_id = '04' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_copd_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_04 
                        , max(case when(f_nhso_copd_medication_topic_id = '05' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_copd_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_05
                        , max(case when(f_nhso_copd_medication_topic_id = '06' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_copd_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_06      
                        , max(case when(f_nhso_copd_medication_topic_id = '07' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_copd_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_07 
                        , max(case when(f_nhso_copd_medication_topic_id = '08' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_copd_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_08 
                        , max(case when(f_nhso_copd_medication_topic_id = '99' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_copd_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_99 
                        , max(case when(f_nhso_copd_medication_topic_id = '01' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_copd_medication.medication_dose	
                                   else '' end) as 	medication_dose_01      
                        , max(case when(f_nhso_copd_medication_topic_id = '02' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_copd_medication.medication_dose	
                                   else '' end) as 	medication_dose_02 
                        , max(case when(f_nhso_copd_medication_topic_id = '03' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_copd_medication.medication_dose	
                                   else '' end) as 	medication_dose_03 
                        , max(case when(f_nhso_copd_medication_topic_id = '04' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_copd_medication.medication_dose	
                                   else '' end) as 	medication_dose_04 
                        , max(case when(f_nhso_copd_medication_topic_id = '05' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_copd_medication.medication_dose	
                                   else '' end) as 	medication_dose_05
                        , max(case when(f_nhso_copd_medication_topic_id = '06' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_copd_medication.medication_dose	
                                   else '' end) as 	medication_dose_06      
                        , max(case when(f_nhso_copd_medication_topic_id = '07' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_copd_medication.medication_dose	
                                   else '' end) as 	medication_dose_07 
                        , max(case when(f_nhso_copd_medication_topic_id = '08' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_copd_medication.medication_dose	
                                   else '' end) as 	medication_dose_08 
                        , max(case when(f_nhso_copd_medication_topic_id = '99' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_copd_medication.medication_dose	
                                   else '' end) as 	medication_dose_99   
                  from t_nhso_copd_medication	
                  group by t_nhso_copd_medication.t_visit_id	
                  ) as 	medication on medication.visit_id = t_visit.t_visit_id
          left join (select t_nhso_copd_non_medication.t_visit_id as 	visit_id
                        , max(case when(f_nhso_copd_non_medication_topic_id = '01')	
                                   then t_nhso_copd_non_medication.non_medication_is_select	
                                   else '' end) as 	non_medication_01      
                        , max(case when(f_nhso_copd_non_medication_topic_id = '02')	
                                   then t_nhso_copd_non_medication.non_medication_is_select	
                                   else '' end) as 	non_medication_02 
                        , max(case when(f_nhso_copd_non_medication_topic_id = '03')	
                                   then t_nhso_copd_non_medication.non_medication_is_select	
                                   else '' end) as 	non_medication_03 
                        , max(case when(f_nhso_copd_non_medication_topic_id = '04')	
                                   then t_nhso_copd_non_medication.non_medication_is_select	
                                   else '' end) as 	non_medication_04 
                        , max(case when(f_nhso_copd_non_medication_topic_id = '05')	
                                   then t_nhso_copd_non_medication.non_medication_is_select	
                                   else '' end) as 	non_medication_05
                        , max(case when(f_nhso_copd_non_medication_topic_id = '06')	
                                   then t_nhso_copd_non_medication.non_medication_is_select	
                                   else '' end) as 	non_medication_06      
                        , max(case when(f_nhso_copd_non_medication_topic_id = '07')	
                                   then t_nhso_copd_non_medication.non_medication_is_select	
                                   else '' end) as 	non_medication_07 
                    from t_nhso_copd_non_medication	
                    group by t_nhso_copd_non_medication.t_visit_id	
                    ) as 	non_medication on non_medication.visit_id = t_visit.t_visit_id
          left join (select t_visit_vital_sign.t_visit_id	
                        , case when(t_visit_vital_sign.visit_vital_sign_height = '')	
                               then '0'	
                               else cast(t_visit_vital_sign.visit_vital_sign_height as 	float) end 
                          as 	vital_sign_height
                        , case when(t_visit_vital_sign.visit_vital_sign_weight = '')	
                               then '0'	
                               else cast(t_visit_vital_sign.visit_vital_sign_weight as 	float) end 
                          as 	vital_sign_weight
                        , case when(t_visit_vital_sign.visit_vital_sign_blood_presure = '')	
                              then '0'	
                              else t_visit_vital_sign.visit_vital_sign_blood_presure end 	
                          as 	vital_sign_bp
                        , case when(t_visit_vital_sign.visit_vital_sign_heart_rate = '')	
                               then '0'	
                               else cast(t_visit_vital_sign.visit_vital_sign_heart_rate as 	float) end 
                          as 	vital_sign_pr
                        , case when(t_visit_vital_sign.visit_vital_sign_respiratory_rate = '')	
                               then '0'	
                               else cast(t_visit_vital_sign.visit_vital_sign_respiratory_rate as 	float) end
                          as 	vital_sign_rr     
                        , case when(t_visit_vital_sign.visit_vital_sign_temperature = '')	
                               then '0'	
                               else cast(t_visit_vital_sign.visit_vital_sign_temperature as 	float) end 
                          as 	vital_sign_temp 
                 from t_visit_vital_sign	
                     inner join	
                        (select distinct t_visit.t_visit_id  	
                                , max(visit_vital_sign_check_date) as 	check_date
                                , max(visit_vital_sign_check_time) as 	check_time
                         from t_visit 	
                                inner join t_visit_vital_sign on (t_visit.t_visit_id = t_visit_vital_sign.t_visit_id 	
                                                                and t_visit_vital_sign.visit_vital_sign_active = '1')  	
                         where substring(visit_vital_sign_check_date,1,10) >= ? 	
                            AND substring(visit_vital_sign_check_date,1,10) <= ?	
                         group by t_visit.t_visit_id	
                         ) q1 on q1.t_visit_id = t_visit_vital_sign.t_visit_id 	
                                 and t_visit_vital_sign.visit_vital_sign_check_date = q1.check_date	
                                 and t_visit_vital_sign.visit_vital_sign_check_time = q1.check_time	
                   ) as 	vital_sign on vital_sign.t_visit_id = t_visit.t_visit_id
WHERE t_visit.f_visit_status_id <> '4'	
      AND substring(t_visit.visit_begin_visit_time,1,10) >= ? 	
      AND substring(t_visit.visit_begin_visit_time,1,10) <= ?	
