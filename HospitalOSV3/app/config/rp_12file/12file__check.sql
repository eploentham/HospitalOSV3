--aer------------------------------------------------------------------------------------------- 276
(SELECT count(t_visit.visit_vn) ,'aer' as name
FROM t_accident
	inner join t_visit	ON t_visit.t_visit_id = t_accident.t_visit_id  
WHERE t_visit.f_visit_status_id <> '4'  
	AND t_visit.visit_begin_visit_time >= '2550-06-01'
	AND t_visit.visit_begin_visit_time <= '2550-07-01'  
--cht------------------------------------------------------------------------------------------- 560  8296
)
--OPD €é¹šÒ¡ ÇÑ¹·Õè visit
union
--IPD €é¹šÒ¡ ÇÑ¹·Õè discharge
(SELECT count(t_visit.t_visit_id) ,'cht' as name
FROM t_billing 
	INNER JOIN t_visit ON (t_billing.t_visit_id = t_visit.t_visit_id) 

where t_visit.f_visit_status_id <> '4' 
        AND t_billing.billing_active = '1'
	AND t_visit.visit_begin_visit_time >= '2550-06-01'
	AND t_visit.visit_begin_visit_time <= '2550-07-01'
)
--OPD €é¹šÒ¡ ÇÑ¹·Õè visit
union
--IPD €é¹šÒ¡ ÇÑ¹·Õè discharge
(SELECT count(t_visit.t_visit_id) ,'cht' as name
FROM  t_billing 
	INNER JOIN t_visit ON (t_billing.t_visit_id = t_visit.t_visit_id) 

where t_visit.f_visit_status_id <> '4' 
	AND t_billing.billing_active = '1' 
    AND f_visit_type_id = '1' 
	AND t_visit.visit_staff_doctor_discharge_date_time >= '2550-06-01'
	AND t_visit.visit_staff_doctor_discharge_date_time <= '2550-07-01'
)
--OPD €é¹šÒ¡ ÇÑ¹·Õè visit
union
--IPD €é¹šÒ¡ ÇÑ¹·Õè discharge
(SELECT 	count(t_visit.t_visit_id) ,'idx' as name
FROM 	t_diag_icd10          
	INNER JOIN t_visit ON (t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id) 

WHERE  t_visit.f_visit_type_id = '1'       
	AND t_visit.f_visit_status_id <> '4'     
	AND t_diag_icd10.diag_icd10_active = '1'    
	AND t_visit.visit_staff_doctor_discharge_date_time >= '2550-06-01'
	AND t_visit.visit_staff_doctor_discharge_date_time <= '2550-07-01'
)
--OPD €é¹šÒ¡ ÇÑ¹·Õè visit
union
--IPD €é¹šÒ¡ ÇÑ¹·Õè discharge
(SELECT 	count(t_visit.t_visit_id) ,'iop' as name
FROM  t_diag_icd9  
	INNER JOIN t_visit ON (t_diag_icd9.diag_icd9_vn = t_visit.t_visit_id )  

WHERE   t_visit.f_visit_type_id = '1'  
	AND t_visit.f_visit_status_id <> '4'  
	AND t_diag_icd9.diag_icd9_active = '1'  
	AND t_visit.visit_staff_doctor_discharge_date_time >= '2550-06-01'
	AND t_visit.visit_staff_doctor_discharge_date_time <= '2550-07-01'
--ipd-------------------------------------------------------------------------------------------530
)
--OPD €é¹šÒ¡ ÇÑ¹·Õè visit
union
--IPD €é¹šÒ¡ ÇÑ¹·Õè discharge
(SELECT 	count(t_visit.t_visit_id) ,'ipd' as name
FROM  t_visit   
	INNER JOIN t_patient ON (t_patient.t_patient_id = t_visit.t_patient_id)  

where  t_visit.f_visit_type_id = '1'  
	AND t_visit.f_visit_status_id <> '4'  
	AND t_visit.visit_staff_doctor_discharge_date_time >= '2550-06-01'
	AND t_visit.visit_staff_doctor_discharge_date_time <= '2550-07-01'
--irf-------------------------------------------------------------------------------------------76
)
--OPD €é¹šÒ¡ ÇÑ¹·Õè visit
union
--IPD €é¹šÒ¡ ÇÑ¹·Õè discharge
(SELECT 	count(t_visit.t_visit_id) ,'irf' as name
FROM   t_visit_refer_in_out
	inner join t_visit  on t_visit.t_visit_id = t_visit_refer_in_out.t_visit_id

WHERE   t_visit.f_visit_status_id <> '4' 
	AND visit_refer_in_out_active = '1'
	AND t_visit.f_visit_type_id = '1'
	AND f_visit_refer_type_id = '1'-- refer out is 1 in data base hos
	AND t_visit.visit_staff_doctor_discharge_date_time >= '2550-06-01'
	AND t_visit.visit_staff_doctor_discharge_date_time <= '2550-07-01'

--odx-------------------------------------------------------------------------------------------9547
)
--OPD €é¹šÒ¡ ÇÑ¹·Õè visit
union
--IPD €é¹šÒ¡ ÇÑ¹·Õè discharge
(SELECT 	count(t_visit.t_visit_id) ,'odx' as name
FROM 	t_diag_icd10  
	INNER JOIN t_visit ON (t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id) 

WHERE   t_visit.f_visit_status_id <> '4' 
	AND t_diag_icd10.diag_icd10_active = '1' 
	AND t_visit.f_visit_type_id = '0'
	AND t_visit.visit_begin_visit_time >= '2550-06-01'
	AND t_visit.visit_begin_visit_time <= '2550-07-01'
--oop-------------------------------------------------------------------------------------------0
)
--OPD €é¹šÒ¡ ÇÑ¹·Õè visit
union
--IPD €é¹šÒ¡ ÇÑ¹·Õè discharge
(SELECT 	count(t_visit.t_visit_id) ,'oop' as name
FROM t_diag_icd9   
	INNER JOIN t_visit ON (t_diag_icd9.diag_icd9_vn = t_visit.t_visit_id)  

WHERE t_visit.f_visit_status_id <> '4'  
	AND t_visit.f_visit_type_id = '0'
	AND t_diag_icd9.diag_icd9_active = '1'  
	AND t_visit.visit_begin_visit_time >= '2550-06-01'
	AND t_visit.visit_begin_visit_time <= '2550-07-01'

--opd------------------------------------------------------------------------------------------- 8058
)
--OPD €é¹šÒ¡ ÇÑ¹·Õè visit
union
--IPD €é¹šÒ¡ ÇÑ¹·Õè discharge
(SELECT 	count(t_visit.t_visit_id) ,'opd' as name
FROM t_visit 

where  t_visit.f_visit_status_id <> '4'
	AND t_visit.visit_begin_visit_time >= '2550-06-01'
	AND t_visit.visit_begin_visit_time <= '2550-07-01'

--orf------------------------------------------------------------------------------------------- 234
)
--OPD €é¹šÒ¡ ÇÑ¹·Õè visit
union
--IPD €é¹šÒ¡ ÇÑ¹·Õè discharge
(SELECT 	count(t_visit_refer_in_out) ,'orf' as name
FROM t_visit_refer_in_out
	INNER join t_visit  on (t_visit.t_visit_id = t_visit_refer_in_out.t_visit_id) 

WHERE    t_visit.f_visit_status_id <> '4' 
	AND visit_refer_in_out_active = '1'
	AND t_visit.f_visit_type_id = '0'
	AND t_visit.visit_begin_visit_time >= '2550-06-01'
	AND t_visit.visit_begin_visit_time <= '2550-07-01'
)
--OPD €é¹šÒ¡ ÇÑ¹·Õè visit
union
--IPD €é¹šÒ¡ ÇÑ¹·Õè discharge
(
--pat-ins------------------------------------------------------------------------------------------ 6056
select count(distinct t_patient_id),'pat' as name from 
((
SELECT 	t_patient.t_patient_id
FROM b_site	, t_visit  
	INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id 

WHERE  t_visit.f_visit_status_id <> '4' 
	AND t_visit.visit_begin_visit_time >= '2550-06-01'
	AND t_visit.visit_begin_visit_time <= '2550-07-01'
)
--case OPD
UNION
--case IPD
(SELECT 	t_patient.t_patient_id
FROM b_site	, t_visit  
	INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id 
  
WHERE  t_visit.f_visit_status_id <> '4' 
    AND t_visit.f_visit_type_id = '1'
	AND t_visit.visit_staff_doctor_discharge_date_time >= '2550-06-01'
	AND t_visit.visit_staff_doctor_discharge_date_time <= '2550-07-01'
  
)) as q1 ) 

