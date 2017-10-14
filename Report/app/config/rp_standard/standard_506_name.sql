SELECT 
             b_icd10.icd10_number AS code 
             , b_icd10.icd10_description AS name 
             , SUM(CASE when t_patient.f_sex_id = '1' THEN 1 ELSE 0 END) AS male 
             , SUM(CASE when t_patient.f_sex_id = '2' THEN 1 ELSE 0 END) AS female 
             , SUM(CASE when (t_patient.f_sex_id <> '2' and t_patient.f_sex_id <> '1') THEN 1 ELSE 0 END) AS non_spec 
             , COUNT(t_diag_icd10.t_diag_icd10_id) AS total 
FROM t_diag_icd10
             LEFT JOIN  t_visit on t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn  
             LEFT JOIN t_patient on t_patient.t_patient_id = t_visit.t_patient_id
             LEFT JOIN  b_icd10 on t_diag_icd10.diag_icd10_number = b_icd10.icd10_number 
             LEFT JOIN  b_group_icd10 on SUBSTRING(t_diag_icd10.diag_icd10_number,0,4) = b_group_icd10.group_icd10_number 
             LEFT JOIN  f_group_rp506 on f_group_rp506.group_rp506_number = b_group_icd10.group_icd10_group_rp506 
WHERE  t_visit.f_visit_status_id <> '4' 
             AND f_group_rp506.group_rp506_number <> '99' 
             AND t_diag_icd10.diag_icd10_active = '1'  
             AND (SUBSTRING(t_visit.visit_financial_discharge_time from 1 for 10)
                 BETWEEN ? and ?)
GROUP BY 
             b_icd10.icd10_number 
             , b_icd10.icd10_description 
ORDER BY 
             b_icd10.icd10_number
