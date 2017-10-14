 SELECT 
	 f_group_rp506.group_rp506_number AS code
         , f_group_rp506.group_rp506_description_th AS description_th
         , f_group_rp506.group_rp506_description_en AS description_en
         , case when (temp.male > 0) then temp.male else  0 end AS male
         , case when (temp.female > 0) then temp.female else  0 end AS female
         , case when (temp.non_spec > 0) then temp.non_spec else  0 end AS non_spec
         , case when (temp.total > 0) then temp.total else 0 end AS total
         FROM 
	 ( f_group_rp506 LEFT JOIN
	 ( SELECT 
                	 f_group_rp506.group_rp506_number AS code
                         , f_group_rp506.group_rp506_description_th AS des
                         , f_group_rp506.group_rp506_description_en AS des_en
                         , SUM(CASE when t_patient.f_sex_id = '1' THEN 1 ELSE 0 END) AS male
                         , SUM(CASE when t_patient.f_sex_id = '2' THEN 1 ELSE 0 END) AS female
                         , SUM(CASE when (t_patient.f_sex_id <> '2' and t_patient.f_sex_id <> '1') THEN 1 ELSE 0 END) AS non_spec
                         , COUNT(t_diag_icd10.t_diag_icd10_id) AS total
		 FROM 
			 f_group_rp506
                         , b_group_icd10
                         , t_patient
                         , t_visit
                         , t_diag_icd10
		 WHERE 
			 t_patient.t_patient_id = t_visit.t_patient_id
                         AND t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn 
			 AND t_visit.f_visit_status_id <> '4' 
                         AND t_diag_icd10.diag_icd10_active = '1' 			
                         AND SUBSTRING(t_diag_icd10.diag_icd10_number,1,3) = b_group_icd10.group_icd10_number
			 AND f_group_rp506.group_rp506_number = b_group_icd10.group_icd10_group_rp506
			 AND (SUBSTRING(t_visit.visit_financial_discharge_time from 1 for 10)
                 BETWEEN ? and ?)
		 GROUP BY 
			 f_group_rp506.group_rp506_number 
                         , f_group_rp506.group_rp506_description_th 
                         , f_group_rp506.group_rp506_description_en ) AS temp 
             ON temp.code = f_group_rp506.group_rp506_number)
     WHERE  
         f_group_rp506.group_rp506_number <> '99'  
     ORDER BY 
	 to_number(f_group_rp506.group_rp506_number,'99')
