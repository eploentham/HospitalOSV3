select distinct t_patient.patient_hn  as HN 
         ,f_patient_prefix.patient_prefix_description as prefix 
         ,t_patient.patient_firstname as firstname 
         ,t_patient.patient_lastname as lastname  
         ,CASE WHEN (t_patient.f_sex_id = '1')       THEN 'Male'  
         WHEN (t_patient.f_sex_id = '2')        THEN 'Female'  
         WHEN ((t_patient.f_sex_id <> '1') and (t_patient.f_sex_id <> '2'))        THEN 'Non-Spec'  END as sex 
         ,t_surveil.surveil_icd10_number as icd10 
         ,SUBSTRING(t_surveil.surveil_sick_date,1,10) AS  start_date  
         ,f_chronic_discharge_status.chronic_discharge_status_description as status_description 
         ,t_patient.patient_house || ' หมู่ที่' || t_patient.patient_moo || ' ต.' || tambol.address_description || ' อ.' || amphur.address_description || ' จ.' || f_address.address_description     as address   
      
         ,substring(t_visit.visit_begin_visit_time,1,10) as visit_time  
         ,CASE WHEN ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)years') ) > '1')
         THEN  substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)years')    
         WHEN  ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)year') )  IS NOT NULL)  
         AND ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year')   from '(...)years') ) IS NULL)   
         THEN substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from'(...)year')    
         WHEN ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)year') ) IS NULL)    
         AND ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year')   from '(...)years') ) IS NULL)  THEN ''  END AS Year   
         ,CASE WHEN ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)mons') ) > '1')
         THEN  substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)mons')    
         WHEN  ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)mon') )   IS NOT NULL)  
         AND ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year')   from '(...)mons') ) IS NULL)   
         THEN substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)mon')    
         WHEN ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)mons') ) IS NULL)   
         AND ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year')   from '(...)mon') ) IS NULL)  THEN ''  END AS Month   
         ,CASE WHEN ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)days') ) > '1')
         THEN  substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)days')    
         WHEN  ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)day') )   IS NOT NULL)  
         AND ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD')- interval '543 year')   from '(...)days') ) IS NULL)   
         THEN substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)day')    
         WHEN ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year') from '(...)days') )   IS NULL)   
         AND ((substring(' ' || age(to_date(t_patient.patient_birthday,'YYYY-MM-DD') - interval '543 year')   from '(...)day') ) IS NULL)  THEN '' END AS Day  
         ,t_visit.visit_vn as VN_AN   
         ,t_patient.patient_father_firstname || ' ' ||t_patient.patient_father_lastname  as father_name   
         ,t_patient.patient_mother_firstname || ' ' ||  t_patient.patient_mother_lastname  as mother_name 
         ,b_group_icd10.group_icd10_group_rp506  as code506  
         ,t_visit.visit_dx as visit_dx 
         , CASE WHEN (t_diag_icd10.diag_icd10_staff_doctor <> '')  
          THEN (b_employee.employee_firstname || '  ' || b_employee.employee_lastname)   
          ELSE ''   
          END  AS DoctorTreat  
         ,f_patient_occupation.patient_occupation_description as occupation 
         ,f_patient_nation.patient_nation_description as nation 
         ,f_patient_marriage_status.patient_marriage_status_description as marriage
         ,CASE WHEN (t_visit.f_visit_type_id = '1')        THEN 'IPD_patient' 
         WHEN (t_visit.f_visit_type_id = '0')        THEN 'OPD_patient'  
         WHEN ((t_visit.f_visit_type_id <> '0') and (t_visit.f_visit_type_id <> '1'))  
         THEN ''  END as visit_type  
         from b_group_icd10,t_visit INNER JOIN t_surveil ON (t_visit.t_patient_id = t_surveil.t_patient_id 
             AND t_visit.f_visit_status_id <> '4' )
         INNER JOIN t_diag_icd10 ON (t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id 
         AND t_diag_icd10.diag_icd10_number = t_surveil.surveil_icd10_number 
          AND t_diag_icd10.diag_icd10_active ='1') 
         LEFT JOIN t_patient ON  t_visit.t_patient_id = t_patient.t_patient_id  
         LEFT JOIN f_patient_prefix ON t_patient.f_patient_prefix_id = f_patient_prefix.f_patient_prefix_id 
         LEFT JOIN f_sex ON t_patient.f_sex_id = f_sex.f_sex_id 
         LEFT JOIN b_employee ON b_employee.b_employee_id = t_diag_icd10.diag_icd10_staff_doctor 
         LEFT JOIN f_chronic_discharge_status ON t_surveil.f_chronic_discharge_status_id = f_chronic_discharge_status.f_chronic_discharge_status_id 
         LEFT JOIN f_address as tambol ON t_patient.patient_tambon = tambol.f_address_id  
         LEFT JOIN f_address as amphur ON t_patient.patient_amphur = amphur.f_address_id  
         LEFT JOIN f_address ON t_patient.patient_changwat  = f_address.f_address_id   
         LEFT JOIN f_patient_occupation ON t_patient.f_patient_occupation_id = f_patient_occupation.f_patient_occupation_id 
         LEFT JOIN f_patient_nation ON t_patient.f_patient_nation_id = f_patient_nation.f_patient_nation_id 
         LEFT JOIN f_patient_marriage_status ON t_patient.f_patient_marriage_status_id = f_patient_marriage_status.f_patient_marriage_status_id 
         LEFT JOIN f_visit_type ON t_visit.f_visit_type_id = f_visit_type.f_visit_type_id 
         WHERE   SUBSTRING(t_visit.visit_financial_discharge_time,1,10) >= ?
         AND   SUBSTRING(t_visit.visit_financial_discharge_time,1,10) <= ?
         AND   (b_group_icd10.group_icd10_group_rp506 <> '')    
         AND   (b_group_icd10.group_icd10_group_rp506 <> '99') 
         AND (SUBSTRING(t_surveil.surveil_icd10_number,1,3) = b_group_icd10.group_icd10_number) 
