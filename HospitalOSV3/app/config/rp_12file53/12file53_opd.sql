
SELECT DISTINCT
	t_patient.patient_hn AS HN
	,case when  b_report_12files_map_clinic.b_report_12files_std_clinic_id is not null
               then (t_visit.f_visit_type_id || b_report_12files_map_clinic.b_report_12files_std_clinic_id || '1')
               else ''
      end AS CLINIC
	, case when (length(t_visit.visit_begin_visit_time)>=10)
                then to_char(to_date(to_number(
                        substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 ||
                        substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')
                    ELSE ''   END AS DATEOPD
	,t_patient.patient_pid AS PERSON_ID
	,case when t_visit.f_visit_type_id = '0'
                then t_visit.visit_vn
              else t_visit.visit_an
      end AS SEQ
	, substr(visit_begin_visit_time,12,2) || substr(visit_begin_visit_time,15,2) as TIMEOPD
    --,'1' AS UUC --ตรวจสอบจากสิทธิที่รับบริการจึงจะถูกต้อง คือ สิทธิ UC คือ UCS หรือ WEL หรือ ว่าง
    ,case when r_rp1853_instype.maininscl in ('UCS','WEL','')
            then '1'
            else '2'
     end as UUC

FROM  t_visit
	INNER JOIN t_patient  ON (t_patient.t_patient_id = t_visit.t_patient_id)
    LEFT JOIN t_visit_payment ON (t_visit_payment.t_visit_id = t_visit.t_visit_id and t_visit_payment.visit_payment_active = '1'
        and t_visit_payment.visit_payment_priority = '0')
    LEFT JOIN b_contract_plans ON t_visit_payment.b_contract_plans_id = b_contract_plans.b_contract_plans_id
    LEFT JOIN r_rp1853_instype ON b_contract_plans.r_rp1853_instype_id = r_rp1853_instype.id
	LEFT JOIN t_diag_icd10  ON (t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id
        AND t_diag_icd10.f_diag_icd10_type_id = '1')
	LEFT JOIN b_report_12files_map_clinic  ON (t_diag_icd10.b_visit_clinic_id = b_report_12files_map_clinic.b_visit_clinic_id)

WHERE  t_visit.f_visit_status_id <> '4'
	AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
	AND substring(t_visit.visit_begin_visit_time,1,10) <= ?
    and t_visit.f_visit_type_id <> 'S'

 ORDER  BY  HN,CLINIC  ,DATEOPD
