--APPOINT_53

SELECT distinct b_site.b_visit_office_id AS PCUCODE --NOT NULL
    ,t_health_family.health_family_hn_hcis as PID --NOT NULL
	,t_visit.visit_vn AS SEQ 	--NOT NULL	
	,(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)       
			|| substring(t_visit.visit_begin_visit_time,6,2)       
			|| substring(t_visit.visit_begin_visit_time,9,2) AS DATE_SERV --NOT NULL
    ,(to_number(substring(t_patient_appointment.patient_appointment_date,1,4),'9999')-543)       
			|| substring(t_patient_appointment.patient_appointment_date,6,2)       
			|| substring(t_patient_appointment.patient_appointment_date,9,2) AS APDATE  --NOT NULL 
    , substring(t_patient_appointment.r_rp1853_aptype_id,1,3) AS APTYPE --NOT NULL 
	, replace(t_diag_icd10.diag_icd10_number,'.','') AS APDIAG 
    , case when length(t_patient_appointment.patient_appointment_update_date_time) >= 10
                then case when length(cast(substring(t_patient_appointment.patient_appointment_update_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_patient_appointment.patient_appointment_update_date_time,5),'-',''),',',''),':','')) = 14
                                  then (cast(substring(t_patient_appointment.patient_appointment_update_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_patient_appointment.patient_appointment_update_date_time,5),'-',''),',',''),':',''))
                                  when length(cast(substring(t_patient_appointment.patient_appointment_update_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_patient_appointment.patient_appointment_update_date_time,5),'-',''),',',''),':','')) =12
                                  then (cast(substring(t_patient_appointment.patient_appointment_update_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_patient_appointment.patient_appointment_update_date_time,5),'-',''),',',''),':','')) || '00'
                                  when length(cast(substring(t_patient_appointment.patient_appointment_update_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_patient_appointment.patient_appointment_update_date_time,5),'-',''),',',''),':','')) =10
                                  then (cast(substring(t_patient_appointment.patient_appointment_update_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_patient_appointment.patient_appointment_update_date_time,5),'-',''),',',''),':','')) || '0000'
                                  when length(cast(substring(t_patient_appointment.patient_appointment_update_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_patient_appointment.patient_appointment_update_date_time,5),'-',''),',',''),':','')) = 8
                                  then (cast(substring(t_patient_appointment.patient_appointment_update_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_patient_appointment.patient_appointment_update_date_time,5),'-',''),',',''),':','')) || '000000'
                                  else ''
                           end
                when length(t_patient_appointment.patient_appointment_record_date_time) >= 10
                then case when length(cast(substring(t_patient_appointment.patient_appointment_record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_patient_appointment.patient_appointment_record_date_time,5),'-',''),',',''),':','')) = 14
                                  then (cast(substring(t_patient_appointment.patient_appointment_record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_patient_appointment.patient_appointment_record_date_time,5),'-',''),',',''),':',''))
                                  when length(cast(substring(t_patient_appointment.patient_appointment_record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_patient_appointment.patient_appointment_record_date_time,5),'-',''),',',''),':','')) =12
                                  then (cast(substring(t_patient_appointment.patient_appointment_record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_patient_appointment.patient_appointment_record_date_time,5),'-',''),',',''),':','')) || '00'
                                  when length(cast(substring(t_patient_appointment.patient_appointment_record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_patient_appointment.patient_appointment_record_date_time,5),'-',''),',',''),':','')) =10
                                  then (cast(substring(t_patient_appointment.patient_appointment_record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_patient_appointment.patient_appointment_record_date_time,5),'-',''),',',''),':','')) || '0000'
                                  when length(cast(substring(t_patient_appointment.patient_appointment_record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_patient_appointment.patient_appointment_record_date_time,5),'-',''),',',''),':','')) = 8
                                  then (cast(substring(t_patient_appointment.patient_appointment_record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_patient_appointment.patient_appointment_record_date_time,5),'-',''),',',''),':','')) || '000000'
                                  else ''
                           end
                else ''
       end as D_UPDATE --NOT NULL 
FROM t_patient_appointment 
	INNER JOIN t_patient ON t_patient_appointment.t_patient_id = t_patient.t_patient_id  
	INNER JOIN t_health_family  ON t_health_family.t_health_family_id = t_patient.t_health_family_id  
	LEFT JOIN t_visit     ON (t_visit.t_visit_id=t_patient_appointment.t_visit_id 
            AND t_visit.f_visit_status_id <> '4') 
	LEFT JOIN t_diag_icd10  ON (t_patient_appointment.t_visit_id = t_diag_icd10.diag_icd10_vn 
		AND t_diag_icd10.f_diag_icd10_type_id = '1')
    ,b_site

WHERE patient_appointment_active = '1'       
    AND health_family_active = '1'
    AND length(t_visit.visit_begin_visit_time)>9
    AND length(patient_appointment_date)>9 
    AND  substring(t_visit.visit_begin_visit_time,1,10) >= ?
    AND substring(t_visit.visit_begin_visit_time,1,10) <= ?
    and t_visit.f_visit_type_id <> 'S'

