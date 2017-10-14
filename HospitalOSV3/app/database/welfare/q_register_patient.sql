SELECT b_site.b_visit_office_id as HCODE	
    , '1' as ST	
    , patient_hn as HN	
    , patient_pid as PID
    , '1' as IDTYPE	
    , '' as MEMBERNO	
    , patient_prefix.patient_prefix_description as KUMNUM	
    , patient_firstname || ' ' || patient_lastname as NAME	
    , t_patient.patient_birthday as DOB	
    , t_patient.patient_phone_number as CONTACT	
    , t_patient.f_sex_id as SEX	
    , t_welfare_directdraw_patient.f_organization_id as ORGCODE	
    , t_welfare_directdraw_patient.f_address_id as ORGPROV	
    , t_welfare_directdraw_patient.directdraw_patient_assurance_number as AUTHNO	
    , t_welfare_directdraw_patient.directdraw_patient_register_date_time as AUTHDATE	
    , t_welfare_directdraw_patient.f_welfare_occupation_type_id as BENFTYPE	
    , t_welfare_directdraw_patient.directdraw_patient_owner_pid as CSPID	
    , owner_prefix.patient_prefix_description as CSSALUTE	
    , t_welfare_directdraw_patient.directdraw_patient_owner_fname 
        || ' ' || t_welfare_directdraw_patient.directdraw_patient_owner_lname as CSNAME	
    , t_welfare_directdraw_patient.f_welfare_plan_owner_status_type_id as CSREL	
    , '' as DRDX	
    , '' as DX	
    , '' as DXOTHER	
    , '' as DXDATE	
    , '' as SPOP	
    , '' as REGTYPE	
    , '' as REGDATETIM	
    , 'r%' as OPCODE	
    , '' as AGENTS	
    , '' as FPDATA	
    , '' as CHKSUM	
    , '' as TEMPL	
    , '' as FLAG	
    , '' as TRSTAT	
    , '' as RESERVE1	
    , '' as RESERVE2
    , patient_hn
    , t_patient.f_patient_prefix_id
    , patient_firstname
    , patient_lastname
    , patient_pid
    , t_welfare_directdraw_patient.* 
FROM t_welfare_directdraw_patient 
    INNER JOIN t_patient ON (t_welfare_directdraw_patient.t_patient_id = t_patient.t_patient_id
        and t_patient.patient_hn like ?
	and t_patient.patient_firstname like ?
	and t_patient.patient_lastname like ?)
    LEFT JOIN (select * from f_patient_prefix) as patient_prefix 
        ON patient_prefix.f_patient_prefix_id = t_patient.f_patient_prefix_id 
    LEFT JOIN (select * from f_patient_prefix) as owner_prefix 
        ON owner_prefix.f_patient_prefix_id = t_patient.f_patient_prefix_id 
    , b_site left join b_visit_office on b_site.b_visit_office_id = b_visit_office.b_visit_office_id
WHERE (directdraw_patient_register_date_time BETWEEN ? AND ?) 
    AND f_welfare_particular_status_type_id LIKE ? 
    AND t_patient.patient_active = '1' 
    AND t_welfare_directdraw_patient.directdraw_patient_active = '1'
ORDER BY directdraw_patient_register_date_time
