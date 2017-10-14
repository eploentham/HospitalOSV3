--don't use thai language
select distinct(pat.*)
from (
(SELECT distinct
	b_site.b_visit_office_id as HCODE
	, t_patient.patient_hn as HN
	, substring(t_patient.patient_changwat from 1 for 2) as CHANGWAT
	, substring(t_patient.patient_amphur from 3 for 2) as AMPHUR
	, CASE WHEN (length(t_patient.patient_birthday)>=10)
                then to_char(to_date(to_number(
                        substr(t_patient.patient_birthday,1,4),'9999')-543 ||
                        substr(t_patient.patient_birthday,5,6),'yyyy-mm-dd'),'yyyymmdd')
                ELSE ''   END AS DOB
	, t_patient.f_sex_id as SEX
	, f_patient_marriage_status.r_rp1853_marriage_id as MARRIAGE
	, f_patient_occupation.r_rp1853_occupation_id AS OCCUPA
	, f_patient_nation.r_rp1853_nation_id  AS NATION
        , case when (t_patient.f_patient_foreigner_id in ('2','3','4')
                    or t_health_family.r_rp1853_foreign_id in ('11','12','13','14','21','22','23'))
                then case when length(t_health_family.t_health_family_id) = 18
                            then '234' || substring(t_health_family.t_health_family_id,9)
                          when length(t_health_family.t_health_family_id) = 15
                            then '234' || substring(t_health_family.t_health_family_id,4)
                          else ''
                      end
                else t_health_family.patient_pid
          end as PERSON_ID
        , case when f_patient_prefix is not null then
            (t_patient.patient_firstname || ' ' ||  t_patient.patient_lastname || ',' || f_patient_prefix.patient_prefix_description)
            else
            (t_patient.patient_firstname || ' ' ||  t_patient.patient_lastname)
            end as NAMEPAT
        , f_patient_prefix.patient_prefix_description as TITLE
	, case when length(t_patient.patient_firstname)>40
            then substring(t_patient.patient_firstname,1,40)
      else t_patient.patient_firstname  
      end AS FNAME
	, case when length(t_patient.patient_lastname)>40
            then substring(t_patient.patient_lastname,1,40)
      else t_patient.patient_lastname  
      end AS LNAME
        , case when t_health_family.patient_pid is not null and trim(t_health_family.patient_pid) <> ''
                then '1'
                else ''
          end as IDTYPE
FROM
	b_site,t_visit
	INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id
        INNER JOIN t_health_family on t_patient.t_health_family_id = t_health_family.t_health_family_id
        LEFT JOIN f_patient_prefix on f_patient_prefix.f_patient_prefix_id = t_patient.f_patient_prefix_id
        INNER JOIN f_patient_occupation on t_patient.f_patient_occupation_id = f_patient_occupation.f_patient_occupation_id
        INNER JOIN f_patient_marriage_status on t_patient.f_patient_marriage_status_id = f_patient_marriage_status.f_patient_marriage_status_id
        LEFT JOIN f_patient_nation on t_patient.f_patient_nation_id = f_patient_nation.f_patient_nation_id

WHERE  t_visit.f_visit_status_id <> '4'
	AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
	AND substring(t_visit.visit_begin_visit_time,1,10) <= ?
    and t_visit.f_visit_type_id <> 'S'

ORDER BY HN)

UNION

(SELECT distinct
	b_site.b_visit_office_id as HCODE
	, t_patient.patient_hn as HN
	, substring(t_patient.patient_changwat from 1 for 2) as CHANGWAT
	, substring(t_patient.patient_amphur from 3 for 2) as AMPHUR
	, CASE WHEN (length(t_patient.patient_birthday)>=10)
                then to_char(to_date(to_number(
                        substr(t_patient.patient_birthday,1,4),'9999')-543 ||
                        substr(t_patient.patient_birthday,5,6),'yyyy-mm-dd'),'yyyymmdd')
                ELSE ''   END AS DOB
	, t_patient.f_sex_id as SEX
	, f_patient_marriage_status.r_rp1853_marriage_id as MARRIAGE
	, f_patient_occupation.r_rp1853_occupation_id AS OCCUPA
	, f_patient_nation.r_rp1853_nation_id  AS NATION
        , case when (t_patient.f_patient_foreigner_id in ('2','3','4')
                    or t_health_family.r_rp1853_foreign_id in ('11','12','13','14','21','22','23'))
                then case when length(t_health_family.t_health_family_id) = 18
                            then '234' || substring(t_health_family.t_health_family_id,9)
                          when length(t_health_family.t_health_family_id) = 15
                            then '234' || substring(t_health_family.t_health_family_id,4)
                          else ''
                      end
                else t_health_family.patient_pid
          end as PERSON_ID
        , case when f_patient_prefix is not null then
            (t_patient.patient_firstname || ' ' ||  t_patient.patient_lastname || ',' || f_patient_prefix.patient_prefix_description)
            else
            (t_patient.patient_firstname || ' ' ||  t_patient.patient_lastname)
            end as NAMEPAT
        , f_patient_prefix.patient_prefix_description as TITLE
	, case when length(t_patient.patient_firstname)>40
            then substring(t_patient.patient_firstname,1,40)
      else t_patient.patient_firstname  
      end AS FNAME
	, case when length(t_patient.patient_lastname)>40
            then substring(t_patient.patient_lastname,1,40)
      else t_patient.patient_lastname  
      end AS LNAME
        , case when t_health_family.patient_pid is not null and trim(t_health_family.patient_pid) <> ''
                then '1'
                else ''
          end as IDTYPE
FROM
	b_site,t_visit
	INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id
        INNER JOIN t_health_family on t_patient.t_health_family_id = t_health_family.t_health_family_id
        LEFT JOIN f_patient_prefix on f_patient_prefix.f_patient_prefix_id = t_patient.f_patient_prefix_id
        INNER JOIN f_patient_occupation on t_patient.f_patient_occupation_id = f_patient_occupation.f_patient_occupation_id
        INNER JOIN f_patient_marriage_status on t_patient.f_patient_marriage_status_id = f_patient_marriage_status.f_patient_marriage_status_id
        LEFT JOIN f_patient_nation on t_patient.f_patient_nation_id = f_patient_nation.f_patient_nation_id

WHERE  t_visit.f_visit_status_id <> '4'
    AND t_visit.f_visit_type_id = '1'
       AND substring(t_visit.visit_staff_doctor_discharge_date_time,1,10) >= ?
       AND substring(t_visit.visit_staff_doctor_discharge_date_time,1,10) <= ?
    and t_visit.f_visit_type_id <> 'S'

ORDER BY HN) ) pat
