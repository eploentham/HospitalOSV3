--EPI_53
SELECT distinct epi53.*
FROM
(SELECT
	b_site.b_visit_office_id AS PCUCODE  --NOT NULL
    ,t_health_family.health_family_hn_hcis as PID --NOT NULL
	,case when t_visit.visit_vn is not null
                then t_visit.visit_vn
                else ''
      end  AS SEQ
    , case when t_visit.visit_begin_visit_time is not null
                then (to_number(substring(t_visit.visit_begin_visit_time,1,5),'9999')-543)
                        || substring(t_visit.visit_begin_visit_time,6,2)
                        || substring(t_visit.visit_begin_visit_time,9,2)
              when  t_health_epi.epi_survey_date is not null and trim(t_health_epi.epi_survey_date) <> ''
                then (to_number(substring(t_health_epi.epi_survey_date,1,5),'9999')-543)
                        || substring(t_health_epi.epi_survey_date,6,2)
                        || substring(t_health_epi.epi_survey_date,9,2)
               else (to_number(substring(t_health_epi.modify_date_time,1,5),'9999')-543)
                        || substring(t_health_epi.modify_date_time,6,2)
                        || substring(t_health_epi.modify_date_time,9,2)
     end AS DATE_SERV --NOT NULL
	, b_health_epi_group.health_epi_group_description_particular AS VCCTYPE  --NOT NULL
	, b_site.b_visit_office_id AS VCCPLACE
        , case when length(t_health_epi.modify_date_time) >= 10
                then case when length(cast(substring(t_health_epi.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi.modify_date_time,5),'-',''),',',''),':','')) = 14
                                  then (cast(substring(t_health_epi.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi.modify_date_time,5),'-',''),',',''),':',''))
                                  when length(cast(substring(t_health_epi.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi.modify_date_time,5),'-',''),',',''),':','')) =12
                                  then (cast(substring(t_health_epi.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi.modify_date_time,5),'-',''),',',''),':','')) || '00'
                                  when length(cast(substring(t_health_epi.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi.modify_date_time,5),'-',''),',',''),':','')) =10
                                  then (cast(substring(t_health_epi.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi.modify_date_time,5),'-',''),',',''),':','')) || '0000'
                                  when length(cast(substring(t_health_epi.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi.modify_date_time,5),'-',''),',',''),':','')) = 8
                                  then (cast(substring(t_health_epi.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi.modify_date_time,5),'-',''),',',''),':','')) || '000000'
                                  else ''
                           end
                else ''
       end as D_UPDATE --NOT NULL

FROM   t_health_epi
	INNER JOIN t_health_epi_detail  ON (t_health_epi.t_health_epi_id = t_health_epi_detail.t_health_epi_id AND health_epi_detail_active = '1')
	INNER JOIN b_health_epi_group  ON t_health_epi_detail.b_health_epi_set_id = b_health_epi_group.b_health_epi_group_id
	INNER JOIN t_health_family  ON t_health_epi_detail.t_health_family_id = t_health_family.t_health_family_id
	INNER JOIN t_visit  ON (t_visit.t_visit_id = t_health_epi.t_visit_id AND t_visit.f_visit_status_id <> '4')
    ,b_site
WHERE  	t_health_epi.health_epi_active = '1'
    AND ((substring(t_health_epi.modify_date_time,1,10) >=  ?  AND substring(t_health_epi.modify_date_time,1,10) <=  ?)
              OR (case when t_visit.t_visit_id is not null
                                        and (substring(t_visit.visit_begin_visit_time,1,10) >=  ?
                                        and substring(t_visit.visit_begin_visit_time,1,10) <=  ?
    and t_visit.f_visit_type_id <> 'S')
                              then true
                              when t_visit.t_visit_id is null
                              then true
                              else false
                     end)
              )

UNION

SELECT
     b_site.b_visit_office_id AS PCUCODE  --NOT NULL
    ,t_health_family.health_family_hn_hcis as PID --NOT NULL
    ,'' AS SEQ
    , case when  t_health_epi.epi_survey_date is not null and trim(t_health_epi.epi_survey_date) <> ''
                then (to_number(substring(t_health_epi.epi_survey_date,1,5),'9999')-543)
                        || substring(t_health_epi.epi_survey_date,6,2)
                        || substring(t_health_epi.epi_survey_date,9,2)
               else (to_number(substring(t_health_epi.modify_date_time,1,5),'9999')-543)
                        || substring(t_health_epi.modify_date_time,6,2)
                        || substring(t_health_epi.modify_date_time,9,2)
     end AS DATE_SERV --NOT NULL
	, b_health_epi_group.health_epi_group_description_particular AS VCCTYPE  --NOT NULL
	, b_site.b_visit_office_id AS VCCPLACE
        , case when length(t_health_epi.modify_date_time) >= 10
                then case when length(cast(substring(t_health_epi.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi.modify_date_time,5),'-',''),',',''),':','')) = 14
                                  then (cast(substring(t_health_epi.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi.modify_date_time,5),'-',''),',',''),':',''))
                                  when length(cast(substring(t_health_epi.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi.modify_date_time,5),'-',''),',',''),':','')) =12
                                  then (cast(substring(t_health_epi.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi.modify_date_time,5),'-',''),',',''),':','')) || '00'
                                  when length(cast(substring(t_health_epi.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi.modify_date_time,5),'-',''),',',''),':','')) =10
                                  then (cast(substring(t_health_epi.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi.modify_date_time,5),'-',''),',',''),':','')) || '0000'
                                  when length(cast(substring(t_health_epi.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi.modify_date_time,5),'-',''),',',''),':','')) = 8
                                  then (cast(substring(t_health_epi.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi.modify_date_time,5),'-',''),',',''),':','')) || '000000'
                                  else ''
                           end
                else ''
       end as D_UPDATE --NOT NULL

FROM   t_health_epi
	INNER JOIN t_health_epi_detail  ON (t_health_epi.t_health_epi_id = t_health_epi_detail.t_health_epi_id AND health_epi_detail_active = '1')
	INNER JOIN b_health_epi_group  ON t_health_epi_detail.b_health_epi_set_id = b_health_epi_group.b_health_epi_group_id
	INNER JOIN t_health_family  ON t_health_epi_detail.t_health_family_id = t_health_family.t_health_family_id
    ,b_site
WHERE  	t_health_epi.health_epi_active = '1'
    AND (t_health_epi.t_visit_id is null OR t_health_epi.t_visit_id = '')
    AND substring(t_health_epi.epi_survey_date,1,10) >=  ?
                AND substring(t_health_epi.epi_survey_date,1,10) <=  ?

UNION

select
b_site.b_visit_office_id as PCUCODE
,t_health_family.health_family_hn_hcis as PID
--เลขลำดับของ Out Site คือ เลขที่โปรแกรมสร้างเรียงลำดับไม่ซ้ำกัน
,'' as SEQ
, (to_number(substring(t_health_epi_outsite.epi_outsite_date,1,5),'9999')-543)
		|| substring(t_health_epi_outsite.epi_outsite_date,6,2)
		|| substring(t_health_epi_outsite.epi_outsite_date,9,2) AS DATE_SERV
, b_health_epi_group.health_epi_group_description_particular AS VCCTYPE
, t_health_epi_outsite.b_epi_outsite_office_id AS VCCPLACE
, case when length(t_health_epi_outsite.modify_date_time) >= 10
                then case when length(cast(substring(t_health_epi_outsite.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi_outsite.modify_date_time,5),'-',''),',',''),':','')) = 14
                                  then (cast(substring(t_health_epi_outsite.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi_outsite.modify_date_time,5),'-',''),',',''),':',''))
                                  when length(cast(substring(t_health_epi_outsite.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi_outsite.modify_date_time,5),'-',''),',',''),':','')) =12
                                  then (cast(substring(t_health_epi_outsite.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi_outsite.modify_date_time,5),'-',''),',',''),':','')) || '00'
                                  when length(cast(substring(t_health_epi_outsite.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi_outsite.modify_date_time,5),'-',''),',',''),':','')) =10
                                  then (cast(substring(t_health_epi_outsite.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi_outsite.modify_date_time,5),'-',''),',',''),':','')) || '0000'
                                  when length(cast(substring(t_health_epi_outsite.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi_outsite.modify_date_time,5),'-',''),',',''),':','')) = 8
                                  then (cast(substring(t_health_epi_outsite.modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_epi_outsite.modify_date_time,5),'-',''),',',''),':','')) || '000000'
                                  else ''
                           end
                else ''
       end as D_UPDATE --NOT NULL
from t_health_epi_outsite
	inner join b_health_epi_group  on t_health_epi_outsite.b_health_epi_group_id = b_health_epi_group.b_health_epi_group_id
	inner join t_health_family  on t_health_epi_outsite.t_health_family_id = t_health_family.t_health_family_id
    ,b_site
where t_health_epi_outsite.epi_outsite_active = '1'
    and substring(t_health_epi_outsite.epi_outsite_date,1,10) >= ?
    and substring(t_health_epi_outsite.epi_outsite_date,1,10) <= ?) epi53
