--NUTRITION_53 
 SELECT distinct nutri53.* 
FROM 
(select
    b_site.b_visit_office_id   AS PCUCODE   --NOT NULL
    ,t_health_family.health_family_hn_hcis as PID --NOT NULL
    ,t_visit.visit_vn AS SEQ 	--NOT NULL
    , case when t_visit.visit_begin_visit_time is not null
                then (to_number(substring(t_visit.visit_begin_visit_time,1,5),'9999')-543)
                        || substring(t_visit.visit_begin_visit_time,6,2)
                        || substring(t_visit.visit_begin_visit_time,9,2)
              when  t_health_nutrition.nutrition_survey_date is not null and trim(t_health_nutrition.nutrition_survey_date) <> ''
                then (to_number(substring(t_health_nutrition.nutrition_survey_date,1,5),'9999')-543)
                        || substring(t_health_nutrition.nutrition_survey_date,6,2)
                        || substring(t_health_nutrition.nutrition_survey_date,9,2)
               else (to_number(substring(t_health_nutrition.modify_date_time,1,5),'9999')-543)
                        || substring(t_health_nutrition.modify_date_time,6,2)
                        || substring(t_health_nutrition.modify_date_time,9,2)
     end AS DATE_SERV --NOT NULL
    , CASE
		WHEN ((substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
			, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') IS NOT NULL )
			AND  (substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') IS NOT NULL ))
			THEN  ((cast(substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') as float) * 12 )
				+  cast(substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') as float))
		WHEN  ((substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
			, to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year') IS NOT NULL )
			AND   (substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)
				,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') IS NULL ))
			THEN  (cast(substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
			, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') as float) * 12 )
		WHEN  ((substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)
			,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') IS NULL )
			AND   (substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') IS NOT NULL ))
			THEN  cast(substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)
				,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') as float)
		ELSE 0  END  AS AGEMONTH
	, t_health_nutrition.health_nutrition_weight AS WEIGHT
	, t_health_nutrition.health_nutrition_high  AS HEIGHT
    , case  when f_health_nutrition_level_id='08' then '5'
            when f_health_nutrition_level_id='09' then '4'
            when f_health_nutrition_level_id='10' then '3'
            when f_health_nutrition_level_id='11' then '2'
            when f_health_nutrition_level_id='12' then '1'
            else '' end AS NLEVEL
  , case when length(t_health_nutrition.modify_date_time) >= 10
                then case when length(cast(substring(t_health_nutrition.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_nutrition.modify_date_time,5),'-',''),',',''),':','')) = 14
                                  then (cast(substring(t_health_nutrition.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_nutrition.modify_date_time,5),'-',''),',',''),':',''))
                                  when length(cast(substring(t_health_nutrition.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_nutrition.modify_date_time,5),'-',''),',',''),':','')) =12
                                  then (cast(substring(t_health_nutrition.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_nutrition.modify_date_time,5),'-',''),',',''),':','')) || '00'
                                  when length(cast(substring(t_health_nutrition.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_nutrition.modify_date_time,5),'-',''),',',''),':','')) =10
                                  then (cast(substring(t_health_nutrition.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_nutrition.modify_date_time,5),'-',''),',',''),':','')) || '0000'
                                  when length(cast(substring(t_health_nutrition.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_nutrition.modify_date_time,5),'-',''),',',''),':','')) = 8
                                  then (cast(substring(t_health_nutrition.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_nutrition.modify_date_time,5),'-',''),',',''),':','')) || '000000'
                                  else ''
                           end
                else ''
       end as D_UPDATE --NOT NULL
FROM  t_health_nutrition
    INNER JOIN t_visit  ON t_visit.t_visit_id = t_health_nutrition.t_visit_id and t_visit.f_visit_status_id <> '4' 
    INNER JOIN t_health_family  ON t_health_nutrition.t_health_family_id = t_health_family.t_health_family_id
    ,b_site
 WHERE  t_health_nutrition.health_nutrition_active = '1'
    AND health_family_active = '1'
    AND ((substring(t_health_nutrition.modify_date_time,1,10) >= ?  AND substring(t_health_nutrition.modify_date_time,1,10) <= ?)
              AND (case when t_visit.t_visit_id is not null
                                        and (substring(t_visit.visit_begin_visit_time,1,10) >= ?
                                        and substring(t_visit.visit_begin_visit_time,1,10) <= ?)
                              then true
                              when t_visit.t_visit_id is null
                              then true
                              else false
                     end)
              )

UNION 

 select
    b_site.b_visit_office_id   AS PCUCODE   --NOT NULL
    ,t_health_family.health_family_hn_hcis as PID --NOT NULL
    ,t_visit.visit_vn AS SEQ 	--NOT NULL
    , case when t_visit.visit_begin_visit_time is not null
                then (to_number(substring(t_visit.visit_begin_visit_time,1,5),'9999')-543)
                        || substring(t_visit.visit_begin_visit_time,6,2)
                        || substring(t_visit.visit_begin_visit_time,9,2)
              when  t_health_nutrition.nutrition_survey_date is not null and trim(t_health_nutrition.nutrition_survey_date) <> ''
                then (to_number(substring(t_health_nutrition.nutrition_survey_date,1,5),'9999')-543)
                        || substring(t_health_nutrition.nutrition_survey_date,6,2)
                        || substring(t_health_nutrition.nutrition_survey_date,9,2)
               else (to_number(substring(t_health_nutrition.modify_date_time,1,5),'9999')-543)
                        || substring(t_health_nutrition.modify_date_time,6,2)
                        || substring(t_health_nutrition.modify_date_time,9,2)
     end AS DATE_SERV --NOT NULL
    , CASE
		WHEN ((substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
			, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') IS NOT NULL )
			AND  (substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') IS NOT NULL ))
			THEN  ((cast(substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') as float) * 12 )
				+  cast(substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') as float))
		WHEN  ((substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
			, to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year') IS NOT NULL )
			AND   (substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)
				,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') IS NULL ))
			THEN  (cast(substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
			, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') as float) * 12 )
		WHEN  ((substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)
			,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') IS NULL )
			AND   (substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') IS NOT NULL ))
			THEN  cast(substring(' ' || age(to_date(substring(t_health_nutrition.modify_date_time,1,10)
				,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') as float)
		ELSE 0  END  AS AGEMONTH
	, t_health_nutrition.health_nutrition_weight AS WEIGHT
	, t_health_nutrition.health_nutrition_high  AS HEIGHT
    , case  when f_health_nutrition_level_id='08' then '5'
            when f_health_nutrition_level_id='09' then '4'
            when f_health_nutrition_level_id='10' then '3'
            when f_health_nutrition_level_id='11' then '2'
            when f_health_nutrition_level_id='12' then '1'
            else '' end AS NLEVEL
  , case when length(t_health_nutrition.modify_date_time) >= 10
                then case when length(cast(substring(t_health_nutrition.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_nutrition.modify_date_time,5),'-',''),',',''),':','')) = 14
                                  then (cast(substring(t_health_nutrition.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_nutrition.modify_date_time,5),'-',''),',',''),':',''))
                                  when length(cast(substring(t_health_nutrition.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_nutrition.modify_date_time,5),'-',''),',',''),':','')) =12
                                  then (cast(substring(t_health_nutrition.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_nutrition.modify_date_time,5),'-',''),',',''),':','')) || '00'
                                  when length(cast(substring(t_health_nutrition.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_nutrition.modify_date_time,5),'-',''),',',''),':','')) =10
                                  then (cast(substring(t_health_nutrition.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_nutrition.modify_date_time,5),'-',''),',',''),':','')) || '0000'
                                  when length(cast(substring(t_health_nutrition.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_nutrition.modify_date_time,5),'-',''),',',''),':','')) = 8
                                  then (cast(substring(t_health_nutrition.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_nutrition.modify_date_time,5),'-',''),',',''),':','')) || '000000'
                                  else ''
                           end
                else ''
       end as D_UPDATE --NOT NULL
FROM  t_health_nutrition
    LEFT JOIN t_visit  ON t_visit.t_visit_id = t_health_nutrition.t_visit_id and t_visit.f_visit_status_id <> '4' 
    INNER JOIN t_health_family  ON t_health_nutrition.t_health_family_id = t_health_family.t_health_family_id
    ,b_site
 WHERE  t_health_nutrition.health_nutrition_active = '1'
    AND health_family_active = '1'
    AND  (substring(t_health_nutrition.nutrition_survey_date,1,10) >= ?  AND substring(t_health_nutrition.nutrition_survey_date,1,10) <= ?) 

UNION

select distinct
b_site.b_visit_office_id AS PCUCODE
,t_health_family.health_family_hn_hcis as PID
,t_visit.visit_vn AS SEQ
,CASE WHEN length(t_visit.visit_begin_visit_time)>10
        THEN (cast(substring(t_visit.visit_begin_visit_time,1,4) as float)-543)
				|| substring(t_visit.visit_begin_visit_time,6,2)
				|| substring(t_visit.visit_begin_visit_time,9,2)
        ELSE '' end AS DATE_SERV
    , CASE
		WHEN ((substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)  ,'YYYY-MM-DD')
			, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') IS NOT NULL )
			AND  (substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') IS NOT NULL ))
			THEN  ((cast(substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') as float) * 12 )
				+  cast(substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') as float))
		WHEN  ((substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)  ,'YYYY-MM-DD')
			, to_date(t_health_family.patient_birthday,'YYYY-MM-DD'))  from '(...)year') IS NOT NULL )
			AND   (substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)
				,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') IS NULL ))
			THEN  (cast(substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)  ,'YYYY-MM-DD')
			, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') as float) * 12 )
		WHEN  ((substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)
			,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)year') IS NULL )
			AND   (substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)  ,'YYYY-MM-DD')
				, to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') IS NOT NULL ))
			THEN  cast(substring(' ' || age(to_date(substring(t_visit.visit_begin_visit_time,1,10)
				,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) from '(...)mon') as float)
		ELSE 0  END  AS AGEMONTH
,t_visit_vital_sign.visit_vital_sign_weight AS WEIGHT
,t_visit_vital_sign.visit_vital_sign_height AS HEIGHT
,case when t_visit_vital_sign.f_visit_nutrition_level_id ='08' then '5'
    when t_visit_vital_sign.f_visit_nutrition_level_id ='09' then '4'
    when t_visit_vital_sign.f_visit_nutrition_level_id ='10' then '3'
    when t_visit_vital_sign.f_visit_nutrition_level_id ='11' then '2'
    when t_visit_vital_sign.f_visit_nutrition_level_id ='12' then '1'
    else '' end AS NLEVEL
    , case when length(t_visit_vital_sign.visit_vital_sign_modify_date_time) >= 10
                then case when length(cast(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,5),'-',''),',',''),':','')) = 14
                                  then (cast(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,5),'-',''),',',''),':',''))
                                  when length(cast(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,5),'-',''),',',''),':','')) =12
                                  then (cast(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,5),'-',''),',',''),':','')) || '00'
                                  when length(cast(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,5),'-',''),',',''),':','')) =10
                                  then (cast(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,5),'-',''),',',''),':','')) || '0000'
                                  when length(cast(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,5),'-',''),',',''),':','')) = 8
                                  then (cast(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,5),'-',''),',',''),':','')) || '000000'
                                  else ''
                           end
                else  (cast(substring(t_visit_vital_sign.record_date,1,4) as numeric) - 543) 
                     || replace(substring(t_visit_vital_sign.record_date,5),'-','')
                     || replace(t_visit_vital_sign.record_time,':','')
       end as D_UPDATE --NOT NULL
from t_visit_vital_sign inner join t_visit on t_visit.t_visit_id = t_visit_vital_sign.t_visit_id
    inner join t_patient on t_visit.t_patient_id = t_patient.t_patient_id
    inner join t_health_family on t_patient.t_health_family_id = t_health_family.t_health_family_id
    , b_site
where trim(t_visit_vital_sign.visit_vital_sign_weight) <> ''
    and t_visit.f_visit_status_id <> '4'
    and t_visit_vital_sign.visit_vital_sign_active = '1'
    and ((substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,1,10) >= ?
                and substring(t_visit_vital_sign.visit_vital_sign_modify_date_time,1,10) <= ?)
            OR
            (substring(t_visit.visit_begin_visit_time,1,10) >= ?
                and substring(t_visit.visit_begin_visit_time,1,10) <= ?)) 

) nutri53