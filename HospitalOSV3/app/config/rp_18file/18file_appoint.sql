--ทดสอบภาษาไทย
SELECT 
	b_site.b_visit_office_id AS PCUCODE 
	, t_health_family.health_family_hn_hcis AS PID		
	,t_visit.visit_vn AS SEQ 		
	, CASE  
		WHEN (t_visit.visit_begin_visit_time IS NULL        
			OR t_visit.visit_begin_visit_time = ''                
			OR t_visit.visit_begin_visit_time = 'null')   THEN ''       
		ELSE (to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)       
			|| substring(t_visit.visit_begin_visit_time,6,2)       
			|| substring(t_visit.visit_begin_visit_time,9,2) END AS DATE_SERV 
        , ((to_number(substring(t_patient_appointment.patient_appointment_date,1,4),'9999')-543)       
			|| substring(t_patient_appointment.patient_appointment_date,6,2)       
			|| substring(t_patient_appointment.patient_appointment_date,9,2)) AS APDATE 
        ,CASE WHEN lower(t_patient_appointment.patient_appointment) like '%bcg%' then '110'
              WHEN lower(t_patient_appointment.patient_appointment) like '%dts1%' then '021'
              WHEN lower(t_patient_appointment.patient_appointment) like '%dts2%' then '022'
              WHEN lower(t_patient_appointment.patient_appointment) like '%dts3%' then '023'
              WHEN lower(t_patient_appointment.patient_appointment) like '%dts4%' then '024'

              WHEN lower(t_patient_appointment.patient_appointment) like '%dtp1%' then '031'
              WHEN lower(t_patient_appointment.patient_appointment) like '%dtp2%' then '032'
              WHEN lower(t_patient_appointment.patient_appointment) like '%dtp3%' then '033'
              WHEN lower(t_patient_appointment.patient_appointment) like '%dtp4%' then '034'
              WHEN lower(t_patient_appointment.patient_appointment) like '%dtp5%' then '035'

             WHEN lower(t_patient_appointment.patient_appointment) like '%hbv1%' then '041'
             WHEN lower(t_patient_appointment.patient_appointment) like '%hbv2%' then '042'
             WHEN lower(t_patient_appointment.patient_appointment) like '%hbv3%' then '043'


             WHEN lower(t_patient_appointment.patient_appointment) like '%je1%' then '051'
             WHEN lower(t_patient_appointment.patient_appointment) like '%je2%' then '052'
             WHEN lower(t_patient_appointment.patient_appointment) like '%je3%' then '053'

             WHEN lower(t_patient_appointment.patient_appointment) like '%MEASLES/MMR%' then '061'
             WHEN lower(t_patient_appointment.patient_appointment) like '%MMR%' then '072'

             WHEN lower(t_patient_appointment.patient_appointment) like '%opv1%' then '081'
             WHEN lower(t_patient_appointment.patient_appointment) like '%opv2%' then '082'
             WHEN lower(t_patient_appointment.patient_appointment) like '%opv3%' then '083'
             WHEN lower(t_patient_appointment.patient_appointment) like '%opv4%' then '084'
             WHEN lower(t_patient_appointment.patient_appointment) like '%opv5%' then '085'

             WHEN lower(t_patient_appointment.patient_appointment) like '%opvs1%' then '086'
             WHEN lower(t_patient_appointment.patient_appointment) like '%opvs2%' then '087'
             WHEN lower(t_patient_appointment.patient_appointment) like '%opvs3%' then '088'
             WHEN lower(t_patient_appointment.patient_appointment) like '%opvc%' then '089'

             WHEN lower(t_patient_appointment.patient_appointment) like '%dtanc1%' then '201'
             WHEN lower(t_patient_appointment.patient_appointment) like '%dtanc2%' then '202'
             WHEN lower(t_patient_appointment.patient_appointment) like '%dtanc3%' then '203'
             WHEN lower(t_patient_appointment.patient_appointment) like '%dtanc4%' then '204'
             WHEN lower(t_patient_appointment.patient_appointment) like '%dtanc5%' then '205'

             WHEN lower(t_patient_appointment.patient_appointment) like '%tt1/dt1%' then '101'
             WHEN lower(t_patient_appointment.patient_appointment) like '%tt1/dt12%' then '102'
             WHEN lower(t_patient_appointment.patient_appointment) like '%tt1/dt13%' then '103'
             WHEN lower(t_patient_appointment.patient_appointment) like '%tt1/dt14%' then '104'
             WHEN lower(t_patient_appointment.patient_appointment) like '%tt1/dt15%' then '105'

             WHEN lower(t_patient_appointment.patient_appointment) like '%dtphb1%' then '091'
             WHEN lower(t_patient_appointment.patient_appointment) like '%dtphb2%' then '092'
             WHEN lower(t_patient_appointment.patient_appointment) like '%dtphb3%' then '093'

             WHEN lower(t_patient_appointment.patient_appointment) like '%rabies%vaccine1%' then '111'
             WHEN lower(t_patient_appointment.patient_appointment) like '%rabies%vaccine2%' then '112'
             WHEN lower(t_patient_appointment.patient_appointment) like '%rabies%vaccine3%' then '113'
             WHEN lower(t_patient_appointment.patient_appointment) like '%rabies%vaccine4%' then '114'
             WHEN lower(t_patient_appointment.patient_appointment) like '%rabies%vaccine5%' then '115'

             WHEN lower(t_patient_appointment.patient_appointment) like '%flu%' then '815'
             WHEN (lower(t_patient_appointment.patient_appointment) like '%วางแผนครอบครัว%' OR (t_patient_appointment.patient_appointment) like '%fp%' )then '121'
             WHEN (lower(t_patient_appointment.patient_appointment) like '%ตรวจครรภ์%' OR (t_patient_appointment.patient_appointment) like '%anc%' )then '131'

             WHEN lower(t_patient_appointment.patient_appointment) like '%รับยา%' then '151'
             WHEN lower(t_patient_appointment.patient_appointment) like '%ชั่งน้ำหนัก%' then '161'
             WHEN lower(t_patient_appointment.patient_appointment) like '%คลอด%' then '171'
            WHEN (lower(t_patient_appointment.patient_appointment) like '%ฟังผลการรักษา%' OR (t_patient_appointment.patient_appointment) like '%f/u%' )then '131'

ELSE '181' END AS APTYPE 
	,  CASE  
		WHEN (t_diag_icd10.diag_icd10_number IS NULL       
			OR t_diag_icd10.diag_icd10_number = ''    
			OR t_diag_icd10.diag_icd10_number = 'null')       
			THEN ''       
		ELSE t_diag_icd10.diag_icd10_number END AS APDIAG 

FROM t_patient_appointment 
	LEFT JOIN t_patient ON t_patient_appointment.t_patient_id = t_patient.t_patient_id  
	LEFT JOIN t_health_family  ON t_health_family.t_health_family_id = t_patient.t_health_family_id  
	LEFT JOIN t_visit     ON (t_visit.t_visit_id=t_patient_appointment.t_visit_id 
        AND t_visit.f_visit_status_id <> '4') 
	LEFT JOIN t_diag_icd10  ON (t_patient_appointment.t_visit_id = t_diag_icd10.diag_icd10_vn 
		AND t_diag_icd10.f_diag_icd10_type_id = '1')
	,b_site

WHERE  patient_appointment_active = '1' 
    AND health_family_active = '1'
  AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
	AND substring(t_visit.visit_begin_visit_time,1,10) <= ?

