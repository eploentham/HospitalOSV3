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
       , t_nhso_asthma_diagnosis.f_nhso_asthma_comorbidities_id as 	comorbidit
       , t_nhso_asthma_diagnosis.diagnosis_comor_other as 	comor_des
       , t_nhso_asthma_diagnosis.f_nhso_disease_control_id as 	disease_co
       , t_nhso_asthma_diagnosis.diagnosis_follow_up as 	follow_up
       , vital_sign.vital_sign_height as 	height
       , vital_sign.vital_sign_weight as 	weight
       , vital_sign.vital_sign_bp as 	bp
       , vital_sign.vital_sign_pr as 	pr
       , vital_sign.vital_sign_rr as 	rr
       , vital_sign.vital_sign_temp as 	temp
       , asthma_examine.examine_01 as 	night_asth
       , asthma_examine.examine_02 as 	breath_scr
       , asthma_examine.examine_03 as 	asthma_lig
       , asthma_examine.examine_04 as 	cough_ofte
       , asthma_examine.examine_05 as 	cough_nigh
       , asthma_examine.examine_06 as 	allergic_w
       , asthma_examine.examine_07 as 	 baby_pare
       , asthma_examine.examine_08 as 	cough_ligh
       , asthma_examine.examine_09 as 	sputum_mor
       , asthma_examine.examine_10 as 	rash_skin
       , asthma_examine.examine_11 as 	parent_ast
       , asthma_examine.examine_12 as 	sister_ast
       , asthma_examine.examine_13 as 	absence_as
       , asthma_examine.examine_14 as 	feed_dog
       , asthma_examine.examine_15 as 	feed_cat
       , asthma_examine.examine_16 as 	cigar_more
       , asthma_examine.examine_17 as 	room_carpe
       , asthma_examine.examine_18 as 	ever_lungs
       , asthma_examine.examine_19 as 	near_tree_
       , asthma_examine.examine_20 as 	asthma_eat
       , asthma_examine.examine_21 as 	short_acti
       , asthma_examine.exam_21_des as 	total_shor
       , asthma_examine.examine_22 as 	meet_docto
       , asthma_examine.exam_22_des as 	total_meet
       , asthma_examine.examine_23 as 	admit_asth
       , asthma_examine.exam_23_des as 	total_admi
       , asthma_examine.examine_24 as 	keep_er
       , asthma_examine.exam_24_des as 	total_keep
       , asthma_examine.examine_25 as 	sick_leave
       , asthma_examine.exam_25_des as 	total_sick
       , asthma_examine.examine_26 as 	push_conve
       , asthma_examine.exam_26_des as 	total_push
       , asthma_examine.examine_27 as 	type_drug_
       , asthma_examine.exam_27_des as 	total_type
       , physical_exam.exam_01 as 	green_oxyg
       , physical_exam.exam_01_des as 	total_gree
       , physical_exam.exam_02 as 	muscle_bre
       , physical_exam.exam_02_des as 	muscle_des
       , physical_exam.exam_03 as 	lungs_scre
       , physical_exam.exam_03_des as 	lungs_desc
       , physical_exam.exam_04 as 	effect_str
       , physical_exam.exam_04_des as 	eff_desc
       , physical_exam.exam_05 as 	xray_first
       , physical_exam.exam_05_des as 	xray_desc
       , physical_exam.exam_06 as 	peak_flow
       , physical_exam.exam_06_des as 	pf_des
       , treat_guide.guide_method_01 as 	estimate_l
       , treat_guide.guide_method_02 as 	esti_athma
       , treat_guide.guide_method_03 as 	result_con
       , treat_guide.guide_method_04 as 	refer
       , asthma_medication.medication_drug_01 as 	corti_drug
       , asthma_medication.medication_dose_01 as 	corti_dose
       , asthma_medication.medication_drug_02 as 	theo_drug
       , asthma_medication.medication_dose_02 as 	theo_dose
       , asthma_medication.medication_drug_03 as 	sustain_dr
       , asthma_medication.medication_dose_03 as 	sustain_do
       , asthma_medication.medication_drug_04 as 	leu_drug
       , asthma_medication.medication_dose_04 as 	leu_dose
       , asthma_medication.medication_drug_05 as 	antcho_drg
       , asthma_medication.medication_dose_05 as 	antcho_dos
       , asthma_medication.medication_drug_06 as 	crom_drg
       , asthma_medication.medication_dose_06 as 	crom_dos
       , asthma_medication.medication_drug_07 as 	ago_s_drg
       , asthma_medication.medication_dose_07 as 	ago_s_dos
       , asthma_medication.medication_drug_08 as 	ago_l_drg
       , asthma_medication.medication_dose_08 as 	ago_l_dos
       , asthma_medication.medication_drug_09 as 	epi_drug
       , asthma_medication.medication_dose_09 as 	epi_dose
       , asthma_medication.medication_drug_10 as 	antbio_drg
       , asthma_medication.medication_dose_10 as 	antbio_dos
       , asthma_medication.medication_drug_11 as 	oxygen_dru
       , asthma_medication.medication_dose_11 as 	oxygen_dos
       , asthma_medication.medication_drug_99 as 	other_drug
       , asthma_medication.medication_dose_99 as 	other_dose
       , asthma_non_medication.non_medication_01 as 	disease_at
       , asthma_non_medication.non_medication_02 as 	push_recov
       , asthma_non_medication.non_medication_03 as 	eat_drug_c
       , asthma_non_medication.non_medication_04 as 	exercise_c
       , asthma_non_medication.non_medication_05 as 	protect_in
       , asthma_non_medication.non_medication_06 as 	air_machin
from t_nhso_asthma_diagnosis	
        inner join t_visit on (t_visit.t_visit_id = t_nhso_asthma_diagnosis.t_visit_id and t_visit.f_visit_status_id <> '4')	
        left join t_patient on t_visit.t_patient_id = t_patient.t_patient_id        	
        left join t_diag_icd10 on(t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id 	
                                    and t_diag_icd10.diag_icd10_active = '1'	
                                    and t_diag_icd10.f_diag_icd10_type_id = '1')	
        left join (select t_nhso_asthma_examine.t_visit_id as 	visit_id
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '01')	
                                    then examine_topic_is_have	
                                    else '0' end) as 	examine_01
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '02')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_02
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '03')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_03
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '04')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_04
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '05')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_05
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '06')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_06
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '07')	
                                    then examine_topic_is_have	
                                    else '0' end) as 	examine_07
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '08')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_08
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '09')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_09
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '10')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_10
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '11')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_11
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '12')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_12
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '13')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_13
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '14')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_14
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '15')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_15
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '16')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_16
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '17')	
                                    then examine_topic_is_have	
                                    else '0' end) as 	examine_17
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '18')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_18
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '19')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_19
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '20')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_20
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '21')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_21
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '22')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_22
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '23')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_23
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '24')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_24
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '25')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_25
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '26')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_26
                        , max(case when (t_nhso_asthma_examine.f_nhso_asthma_examine_topic_id = '27')	
                                   then examine_topic_is_have	
                                   else '0' end) as 	examine_27
                        , max(case when(f_nhso_asthma_examine_topic_id = '21' and examine_time_week is not null and examine_time_week <> '')	
                                   then t_nhso_asthma_examine.examine_time_week	
                                   else '' end) as 	exam_21_des
                        , max(case when(f_nhso_asthma_examine_topic_id = '22' and examine_time_week is not null and examine_time_week <> '')	
                                   then t_nhso_asthma_examine.examine_time_week	
                                   else '' end) as 	exam_22_des
                        , max(case when(f_nhso_asthma_examine_topic_id = '23' and examine_time_week is not null and examine_time_week <> '')	
                                   then t_nhso_asthma_examine.examine_time_week	
                                   else '' end) as 	exam_23_des
                        , max(case when(f_nhso_asthma_examine_topic_id = '24' and examine_time_week is not null and examine_time_week <> '')	
                                   then t_nhso_asthma_examine.examine_time_week	
                                   else '' end) as 	exam_24_des
                        , max(case when(f_nhso_asthma_examine_topic_id = '25' and examine_time_week is not null and examine_time_week <> '')	
                                   then t_nhso_asthma_examine.examine_time_week	
                                   else '' end) as 	exam_25_des
                        , max(case when(f_nhso_asthma_examine_topic_id = '26' and examine_time_week is not null and examine_time_week <> '')	
                                   then t_nhso_asthma_examine.examine_time_week	
                                   else '' end) as 	exam_26_des
                        , max(case when(f_nhso_asthma_examine_topic_id = '27' and examine_time_week is not null and examine_time_week <> '')	
                                   then t_nhso_asthma_examine.examine_time_week	
                                   else '' end) as 	exam_27_des
                    from t_nhso_asthma_examine 	
                    group by t_nhso_asthma_examine.t_visit_id	
                    ) as 	asthma_examine on asthma_examine.visit_id = t_visit.t_visit_id
          left join (select t_nhso_asthma_physical_exam.t_visit_id as 	visit_id
                        , max(case when(f_nhso_asthma_physical_exam_topic_id = '01')	
                                   then t_nhso_asthma_physical_exam.physical_exam_topic_is_have	
                                   else '0' end) as 	exam_01
                        , max(case when(f_nhso_asthma_physical_exam_topic_id = '01' and physical_exam_result is not null and physical_exam_result <> '')	
                                   then t_nhso_asthma_physical_exam.physical_exam_result	
                                   else '' end) as 	exam_01_des
                        , max(case when(f_nhso_asthma_physical_exam_topic_id = '02')	
                                   then t_nhso_asthma_physical_exam.physical_exam_topic_is_have	
                                   else '0' end) as 	exam_02
                        , max(case when(f_nhso_asthma_physical_exam_topic_id = '02' and physical_exam_result is not null and physical_exam_result <> '')	
                                   then t_nhso_asthma_physical_exam.physical_exam_result	
                                   else '' end) as 	exam_02_des
                        , max(case when(f_nhso_asthma_physical_exam_topic_id = '03')	
                                   then t_nhso_asthma_physical_exam.physical_exam_topic_is_have	
                                   else '0' end) as 	exam_03
                        , max(case when(f_nhso_asthma_physical_exam_topic_id = '03' and physical_exam_result is not null and physical_exam_result <> '')	
                                   then t_nhso_asthma_physical_exam.physical_exam_result	
                                   else '' end) as 	exam_03_des
                        , max(case when(f_nhso_asthma_physical_exam_topic_id = '04')	
                                   then t_nhso_asthma_physical_exam.physical_exam_topic_is_have	
                                   else '0' end) as 	exam_04
                        , max(case when(f_nhso_asthma_physical_exam_topic_id = '04' and physical_exam_result is not null and physical_exam_result <> '')	
                                   then t_nhso_asthma_physical_exam.physical_exam_result	
                                   else '' end) as 	exam_04_des
                        , max(case when(f_nhso_asthma_physical_exam_topic_id = '05')	
                                   then t_nhso_asthma_physical_exam.physical_exam_topic_is_have	
                                   else '0' end) as 	exam_05
                        , max(case when(f_nhso_asthma_physical_exam_topic_id = '05' and physical_exam_result is not null and physical_exam_result <> '')	
                                   then t_nhso_asthma_physical_exam.physical_exam_result	
                                   else '' end) as 	exam_05_des
                        , max(case when(f_nhso_asthma_physical_exam_topic_id = '06')	
                                   then t_nhso_asthma_physical_exam.physical_exam_topic_is_have	
                                   else '0' end) as 	exam_06
                        , max(case when(f_nhso_asthma_physical_exam_topic_id = '06' and physical_exam_result is not null and physical_exam_result <> '')	
                                   then t_nhso_asthma_physical_exam.physical_exam_result	
                                   else '' end) as 	exam_06_des                   
                     from t_nhso_asthma_physical_exam	
                     group by t_nhso_asthma_physical_exam.t_visit_id	
                     ) as 	physical_exam on physical_exam.visit_id = t_visit.t_visit_id
           left join (select t_nhso_asthma_treat_guide.t_visit_id as 	visit_id
                        , max(case when(f_nhso_asthma_guide_method_id = '01')	
                                   then t_nhso_asthma_treat_guide.guide_is_select	
                                   else '0' end) as 	guide_method_01       
                        , max(case when(f_nhso_asthma_guide_method_id = '02')	
                                   then t_nhso_asthma_treat_guide.guide_is_select	
                                   else '0' end) as 	guide_method_02
                        , max(case when(f_nhso_asthma_guide_method_id = '03')	
                                   then t_nhso_asthma_treat_guide.guide_is_select	
                                   else '0' end) as 	guide_method_03
                        , max(case when(f_nhso_asthma_guide_method_id = '04')	
                                   then t_nhso_asthma_treat_guide.guide_is_select	
                                   else '0' end) as 	guide_method_04                       
                       from t_nhso_asthma_treat_guide	
                       group by t_nhso_asthma_treat_guide.t_visit_id	
                       ) as 	treat_guide on treat_guide.visit_id = t_visit.t_visit_id
            left join (select t_nhso_asthma_medication.t_visit_id as 	visit_id
                        , max(case when(f_nhso_asthma_medication_topic_id = '01' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_asthma_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_01      
                        , max(case when(f_nhso_asthma_medication_topic_id = '02' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_asthma_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_02 
                        , max(case when(f_nhso_asthma_medication_topic_id = '03' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_asthma_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_03 
                        , max(case when(f_nhso_asthma_medication_topic_id = '04' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_asthma_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_04 
                        , max(case when(f_nhso_asthma_medication_topic_id = '05' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_asthma_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_05
                        , max(case when(f_nhso_asthma_medication_topic_id = '06' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_asthma_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_06      
                        , max(case when(f_nhso_asthma_medication_topic_id = '07' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_asthma_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_07 
                        , max(case when(f_nhso_asthma_medication_topic_id = '08' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_asthma_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_08 
                        , max(case when(f_nhso_asthma_medication_topic_id = '09' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_asthma_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_09      
                        , max(case when(f_nhso_asthma_medication_topic_id = '10' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_asthma_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_10 
                        , max(case when(f_nhso_asthma_medication_topic_id = '11' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_asthma_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_11
                        , max(case when(f_nhso_asthma_medication_topic_id = '99' and medication_drug_name is not null and medication_drug_name <> '')	
                                   then t_nhso_asthma_medication.medication_drug_name	
                                   else '' end) as 	medication_drug_99 
                        , max(case when(f_nhso_asthma_medication_topic_id = '01' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_asthma_medication.medication_dose	
                                   else '' end) as 	medication_dose_01      
                        , max(case when(f_nhso_asthma_medication_topic_id = '02' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_asthma_medication.medication_dose	
                                   else '' end) as 	medication_dose_02 
                        , max(case when(f_nhso_asthma_medication_topic_id = '03' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_asthma_medication.medication_dose	
                                   else '' end) as 	medication_dose_03 
                        , max(case when(f_nhso_asthma_medication_topic_id = '04' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_asthma_medication.medication_dose	
                                   else '' end) as 	medication_dose_04 
                        , max(case when(f_nhso_asthma_medication_topic_id = '05' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_asthma_medication.medication_dose	
                                   else '' end) as 	medication_dose_05
                        , max(case when(f_nhso_asthma_medication_topic_id = '06' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_asthma_medication.medication_dose	
                                   else '' end) as 	medication_dose_06      
                        , max(case when(f_nhso_asthma_medication_topic_id = '07' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_asthma_medication.medication_dose	
                                   else '' end) as 	medication_dose_07 
                        , max(case when(f_nhso_asthma_medication_topic_id = '08' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_asthma_medication.medication_dose	
                                   else '' end) as 	medication_dose_08
                        , max(case when(f_nhso_asthma_medication_topic_id = '09' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_asthma_medication.medication_dose	
                                   else '' end) as 	medication_dose_09
                        , max(case when(f_nhso_asthma_medication_topic_id = '10' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_asthma_medication.medication_dose	
                                   else '' end) as 	medication_dose_10
                        , max(case when(f_nhso_asthma_medication_topic_id = '11' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_asthma_medication.medication_dose	
                                   else '' end) as 	medication_dose_11 
                        , max(case when(f_nhso_asthma_medication_topic_id = '99' and medication_dose is not null and medication_dose <> '')	
                                   then t_nhso_asthma_medication.medication_dose	
                                   else '' end) as 	medication_dose_99   
                     from t_nhso_asthma_medication	
                     group by t_nhso_asthma_medication.t_visit_id	
                     ) as 	asthma_medication on  asthma_medication.visit_id = t_visit.t_visit_id
          left join (select t_nhso_asthma_non_medication.t_visit_id as 	visit_id
                        , max(case when(f_nhso_asthma_non_medication_topic_id = '01')	
                                   then t_nhso_asthma_non_medication.non_medication_is_select	
                                   else '' end) as 	non_medication_01      
                        , max(case when(f_nhso_asthma_non_medication_topic_id = '02')	
                                   then t_nhso_asthma_non_medication.non_medication_is_select	
                                   else '' end) as 	non_medication_02 
                        , max(case when(f_nhso_asthma_non_medication_topic_id = '03')	
                                   then t_nhso_asthma_non_medication.non_medication_is_select	
                                   else '' end) as 	non_medication_03 
                        , max(case when(f_nhso_asthma_non_medication_topic_id = '04')	
                                   then t_nhso_asthma_non_medication.non_medication_is_select	
                                   else '' end) as 	non_medication_04 
                        , max(case when(f_nhso_asthma_non_medication_topic_id = '05')	
                                   then t_nhso_asthma_non_medication.non_medication_is_select	
                                   else '' end) as 	non_medication_05
                        , max(case when(f_nhso_asthma_non_medication_topic_id = '06')	
                                   then t_nhso_asthma_non_medication.non_medication_is_select	
                                   else '' end) as 	non_medication_06                              
                    from t_nhso_asthma_non_medication	
                    group by t_nhso_asthma_non_medication.t_visit_id	
                    ) as 	asthma_non_medication on asthma_non_medication.visit_id = t_visit.t_visit_id
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
                   )as vital_sign on vital_sign.t_visit_id = t_visit.t_visit_id	
WHERE t_visit.f_visit_status_id <> '4'	
      AND substring(t_visit.visit_begin_visit_time,1,10) >= ? 	
      AND substring(t_visit.visit_begin_visit_time,1,10) <= ?	
--      AND t_visit.visit_money_discharge_status = '1'	
      
