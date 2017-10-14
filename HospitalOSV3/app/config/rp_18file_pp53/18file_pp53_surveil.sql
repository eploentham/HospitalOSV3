--SURVEIL_53
SELECT  DISTINCT   
	b_site.b_visit_office_id AS PCUCODE --NOT NULL
        , t_health_family.patient_pid as CID --NOT NULL --ต้องใช้เชื่อมโยงกับ 12 แฟ้ม
        ,t_health_family.health_family_hn_hcis as PID --NOT NULL
	,t_visit.visit_vn AS SEQ 	--NOT NULL
	, CASE WHEN (visit_begin_visit_time <> '' and visit_begin_visit_time <> 'null')       
			THEN (to_number(substring(visit_begin_visit_time,1,4),'9999')-543)        
				|| substring(visit_begin_visit_time,6,2)  
				|| substring(visit_begin_visit_time,9,2)       
            ELSE  ''    END AS DATE_SERV --NOT NULL
	, CASE WHEN t_surveil.surveil_icd10_number is not NULL     
            THEN replace(t_surveil.surveil_icd10_number,'.','')      
            ELSE '' END AS DIAGCODE  --NOT NULL
	, substr(b_group_icd10.group_icd10_group_rp506,1,2) AS CODE506 
	, CASE WHEN length(t_surveil.surveil_sick_date)>9
			THEN (to_number(substring(t_surveil.surveil_sick_date,1,4),'9999')-543)        
				|| substring(t_surveil.surveil_sick_date,6,2) 
				|| substring(t_surveil.surveil_sick_date,9,2)       
            ELSE ''   END AS ILLDATE 
	, patient_house  AS ILLHOUSE
	,  case when trim(patient_moo)='' then  '0'
            when trim(patient_moo)='00' then  '0'
            else patient_moo end AS ILLVILL
	,  case when SUBSTRING(t_patient.patient_tambon,5,2)='' then '0'
            else SUBSTRING(t_patient.patient_tambon,5,2) end AS ILLTAMB
	,  case when SUBSTRING(t_patient.patient_tambon,3,2)='' then '0'
            else SUBSTRING(t_patient.patient_tambon,3,2) end AS ILLAMPU
	,  case when SUBSTRING(t_patient.patient_tambon,1,2)='' then '0'
            else SUBSTRING(t_patient.patient_tambon,1,2) end AS ILLCHAN 
	,CASE WHEN t_surveil.f_chronic_discharge_status_id in ('1','2','3','4') 
          THEN t_surveil.f_chronic_discharge_status_id 
          ELSE '4' 
	END AS PTSTAT
	, CASE WHEN length(t_death.death_date_time)>9 THEN 
            (to_number(substring(t_death.death_date_time,1,4),'9999')-543)      
            || substring(t_death.death_date_time,6,2)       
            || substring(t_death.death_date_time,9,2) 
            ELSE '' END AS DATE_DEATH 
	, t_surveil.r_rp1853_surveilcomplicate_id AS COMPLICA 
 	, t_surveil.r_rp1853_surveiloganism_id AS ORGANISM  
        , case when length(t_visit.visit_begin_visit_time) >= 10
                then case when length(cast(substring(t_visit.visit_begin_visit_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit.visit_begin_visit_time,5),'-',''),',',''),':','')) = 14
                                  then (cast(substring(t_visit.visit_begin_visit_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit.visit_begin_visit_time,5),'-',''),',',''),':',''))
                                  when length(cast(substring(t_visit.visit_begin_visit_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit.visit_begin_visit_time,5),'-',''),',',''),':','')) =12
                                  then (cast(substring(t_visit.visit_begin_visit_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit.visit_begin_visit_time,5),'-',''),',',''),':','')) || '00'
                                  when length(cast(substring(t_visit.visit_begin_visit_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit.visit_begin_visit_time,5),'-',''),',',''),':','')) =10
                                  then (cast(substring(t_visit.visit_begin_visit_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit.visit_begin_visit_time,5),'-',''),',',''),':','')) || '0000'
                                  when length(cast(substring(t_visit.visit_begin_visit_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit.visit_begin_visit_time,5),'-',''),',',''),':','')) = 8
                                  then (cast(substring(t_visit.visit_begin_visit_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit.visit_begin_visit_time,5),'-',''),',',''),':','')) || '000000'
                                  else ''
                           end
                else ''
       end as D_UPDATE --NOT NULL
FROM  t_surveil
	INNER JOIN t_visit ON t_visit.t_visit_id = t_surveil.t_visit_id
	INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id
	INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id 
	LEFT JOIN b_group_icd10 ON substring(t_surveil.surveil_icd10_number,1,3) = b_group_icd10.group_icd10_number 
	LEFT JOIN t_death ON (t_death.t_health_family_id = t_health_family.t_health_family_id 
        AND t_death.death_active = '1') 
    ,b_site
	
WHERE t_visit.f_visit_status_id <> '4'      
    AND health_family_active = '1'
 AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
 AND substring(t_visit.visit_begin_visit_time,1,10) <= ?
    and t_visit.f_visit_type_id <> 'S'
