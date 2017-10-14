--ANC_53
 SELECT
	b_site.b_visit_office_id AS PCUCODE 	--NOT NULL
    , t_health_family.patient_pid as CID --ต้องใช้เชื่อมโยงกับ 12 แฟ้ม
    , t_visit.visit_vn AS SEQ 		--NOT NULL
    , case when t_visit.visit_begin_visit_time is not null
                then (to_number(substring(t_visit.visit_begin_visit_time,1,5),'9999')-543)
                        || substring(t_visit.visit_begin_visit_time,6,2)
                        || substring(t_visit.visit_begin_visit_time,9,2)
              when  t_health_anc.health_anc_survey is not null and trim(t_health_anc.health_anc_survey) <> ''
                then (to_number(substring(t_health_anc.health_anc_survey,1,5),'9999')-543)
                        || substring(t_health_anc.health_anc_survey,6,2)
                        || substring(t_health_anc.health_anc_survey,9,2)
               else (to_number(substring(t_health_anc.modify_date_time,1,5),'9999')-543)
                        || substring(t_health_anc.modify_date_time,6,2)
                        || substring(t_health_anc.modify_date_time,9,2)
     end AS DATE_SERV --NOT NULL
	, t_health_pregnancy.b_visit_office_id AS APLACE
	, t_health_pregnancy.health_pregnancy_gravida_number AS GRAVIDA
    , t_health_anc.f_health_anc_section AS ANCNO
	, t_health_anc.health_anc_gravida_week AS  GA
	, case when t_health_anc.health_anc_exam<>'2'
		then '1'
		else '2' end AS ANCRES
    , case when length(t_health_anc.modify_date_time) >= 10
                then cast(substring(t_health_anc.modify_date_time,1,4) as numeric) - 543
                         || replace(replace(replace(substring(t_health_anc.modify_date_time,5),'-',''),',',''),':','')
                when length(t_health_anc.record_date_time) >= 10
                then cast(substring(t_health_anc.record_date_time,1,4) as numeric) - 543
                         || replace(replace(replace(substring(t_health_anc.record_date_time,5),'-',''),',',''),':','')
                else ''
       end as D_UPDATE --NOT NULL
FROM t_health_anc
	INNER JOIN t_health_family ON t_health_anc.t_health_family_id = t_health_family.t_health_family_id
	LEFT JOIN t_visit ON t_health_anc.t_visit_id = t_visit.t_visit_id
    LEFT JOIN t_health_pregnancy ON t_health_anc.t_health_pregnancy_id = t_health_pregnancy.t_health_pregnancy_id
	,b_site
WHERE  t_health_anc.health_anc_active = '1'
    AND health_family_active = '1'
	AND (((substring(t_health_anc.modify_date_time,1,10) >= ?  AND substring(t_health_anc.modify_date_time,1,10) <= ?)
              OR  (substring(t_health_anc.health_anc_survey,1,10) >= ?  AND substring(t_health_anc.health_anc_survey,1,10) <= ?))
              AND (case when t_visit.t_visit_id is not null
                                        and (substring(t_visit.visit_begin_visit_time,1,10) >= ?
                                        and substring(t_visit.visit_begin_visit_time,1,10) <= ?
    and t_visit.f_visit_type_id <> 'S')
                              then true
                              when t_visit.t_visit_id is null
                              then true
                              else false
                     end)
              )
