select  t_nhso_dmht_summary.t_visit_id as	t_visit_id
        , t_nhso_dmht_summary.summary_date_time as	summary_da
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '01' and t_nhso_dmht_summary.summary_topic = '01')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	height
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '01' and t_nhso_dmht_summary.summary_topic = '02')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	weight
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '01' and t_nhso_dmht_summary.summary_topic = '03')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	bmi 
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '01' and t_nhso_dmht_summary.summary_topic = '04')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	waist
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '01' and t_nhso_dmht_summary.summary_topic = '05')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	fbs
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '01' and t_nhso_dmht_summary.summary_topic = '06')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	dtx
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '01' and t_nhso_dmht_summary.summary_topic = '07')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	bp
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '02' and t_nhso_dmht_summary.summary_topic = '01')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	metformin
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '02' and t_nhso_dmht_summary.summary_topic = '02')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	sulfonylur
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '02' and t_nhso_dmht_summary.summary_topic = '03')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	alpha_gl 
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '02' and t_nhso_dmht_summary.summary_topic = '04')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	biguanide
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '02' and t_nhso_dmht_summary.summary_topic = '05')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	glitazones
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '02' and t_nhso_dmht_summary.summary_topic = '06')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	insulin
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '02' and t_nhso_dmht_summary.summary_topic = '07')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	others_dm
----------------------------------------------------------------------------------------------	
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '03' and t_nhso_dmht_summary.summary_topic = '01')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	ace_i
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '03' and t_nhso_dmht_summary.summary_topic = '02')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	arb
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '03' and t_nhso_dmht_summary.summary_topic = '03')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	a_blocker
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '03' and t_nhso_dmht_summary.summary_topic = '04')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	b_blocker
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '03' and t_nhso_dmht_summary.summary_topic = '05')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	a_b_blocke
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '03' and t_nhso_dmht_summary.summary_topic = '06')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	ccb
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '03' and t_nhso_dmht_summary.summary_topic = '07')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	diuretic
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '03' and t_nhso_dmht_summary.summary_topic = '08')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	fibrate 
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '03' and t_nhso_dmht_summary.summary_topic = '09')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	statin
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '03' and t_nhso_dmht_summary.summary_topic = '10')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	methyldopa
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '03' and t_nhso_dmht_summary.summary_topic = '11')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	aspirin
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '03' and t_nhso_dmht_summary.summary_topic = '12')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	others_ht         
------------------------------------------------------------------------------------------	
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '05' and t_nhso_dmht_summary.summary_topic = '01')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	hct
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '05' and t_nhso_dmht_summary.summary_topic = '02')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	hba1c
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '05' and t_nhso_dmht_summary.summary_topic = '03')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	total_chol
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '05' and t_nhso_dmht_summary.summary_topic = '04')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	hdl
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '05' and t_nhso_dmht_summary.summary_topic = '05')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	ldl
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '05' and t_nhso_dmht_summary.summary_topic = '06')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	tg
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '05' and t_nhso_dmht_summary.summary_topic = '07')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	bun
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '05' and t_nhso_dmht_summary.summary_topic = '08')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	creatinine 
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '05' and t_nhso_dmht_summary.summary_topic = '09')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	uric_acid
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '05' and t_nhso_dmht_summary.summary_topic = '10')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	albumin
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '05' and t_nhso_dmht_summary.summary_topic = '11')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	sugar
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '05' and t_nhso_dmht_summary.summary_topic = '12')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	urine_mirc
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '05' and t_nhso_dmht_summary.summary_topic = '13')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	ekg
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '05' and t_nhso_dmht_summary.summary_topic = '14')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	chest_xray
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '05' and t_nhso_dmht_summary.summary_topic = '15')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	other_test
---------------------------------------------------------------------------------------------	
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '06' and t_nhso_dmht_summary.summary_topic = '01')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	eyes
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '06' and t_nhso_dmht_summary.summary_topic = '02')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	kidney
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '06' and t_nhso_dmht_summary.summary_topic = '03')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	nerve 
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '06' and t_nhso_dmht_summary.summary_topic = '04')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	heart
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '06' and t_nhso_dmht_summary.summary_topic = '05')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	glands
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '06' and t_nhso_dmht_summary.summary_topic = '06')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	admission
        , max(case when(t_nhso_dmht_summary.f_nhso_dmht_summary_group_id = '06' and t_nhso_dmht_summary.summary_topic = '99')	
                   then t_nhso_dmht_summary.summary_detail	
                   else '' end) as 	dx_other              
from t_nhso_dmht_summary
    inner join t_visit	on t_visit.t_visit_id = t_nhso_dmht_summary.t_visit_id
WHERE t_visit.f_visit_status_id <> '4'	
      AND substring(t_visit.visit_begin_visit_time,1,10) >= ? 	
      AND substring(t_visit.visit_begin_visit_time,1,10) <= ?	
group by t_nhso_dmht_summary.summary_date_time	
         , t_nhso_dmht_summary.t_visit_id	
