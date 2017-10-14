SELECT distinct(person.*)
from (
(SELECT distinct
	b_site.b_visit_office_id AS PCUCODE
        , case when (t_patient.f_patient_foreigner_id in ('2','3','4')
                    or t_health_family.r_rp1853_foreign_id in ('11','12','13','14','21','22','23'))
                then case when length(t_health_family.t_health_family_id) = 18
                            then '234' || substring(t_health_family.t_health_family_id,9)
                          when length(t_health_family.t_health_family_id) = 15
                            then '234' || substring(t_health_family.t_health_family_id,4)
                          else ''
                      end
                else t_health_family.patient_pid
          end as CID
	, t_health_family.health_family_hn_hcis AS PID
	, t_health_home.home_serial AS HID
	, case when f_patient_prefix.r_rp1853_prefix_id is not null
                then f_patient_prefix.r_rp1853_prefix_id
                else ''
          end AS PRENAME
	, t_health_family.patient_name AS NAME
	, t_health_family.patient_last_name AS LNAME
	, case when t_patient.patient_hn is not null
                then t_patient.patient_hn
                else ''
      end AS HN
	, case when t_health_family.f_sex_id is not null and trim(t_health_family.f_sex_id) in ('1','2')
                then t_health_family.f_sex_id
                else ''
      end AS SEX
    , CASE when length(t_health_family.patient_birthday)>9 and t_health_family.patient_birthday_true = '1'
           then  (to_number(substring(t_health_family.patient_birthday,1,4),'9999') - 543)
                    || substring(t_health_family.patient_birthday,6,2)
                    || substring(t_health_family.patient_birthday,9,2)
           when length(t_health_family.patient_birthday)>9 and t_health_family.patient_birthday_true <> '1'
                   then (to_number(substring(t_health_family.patient_birthday,1,4),'9999') - 543)
                    || '0101'
          ELSE ''  END AS BIRTH
	, t_health_home.health_home_house AS HOUSE
	,  case when t_health_village.village_moo='' then  '0'
            else t_health_village.village_moo end AS VILLAGE
	,  case when substring(t_health_village.village_tambon,5,2)='' then '0'
            else substring(t_health_village.village_tambon,5,2) end AS TAMBON
	,  case when substring(t_health_village.village_ampur,3,2)='' then '0'
            else substring(t_health_village.village_ampur,3,2) end AS AMPUR
	,  case when substring(t_health_village.village_changwat,1,2)='' then '0'
            else substring(t_health_village.village_changwat,1,2) end AS CHANGWAT
	, r_rp1853_marriage_id AS MSTATUS
	, case when f_patient_occupation.r_rp1853_occupation_id is not null
             then f_patient_occupation.r_rp1853_occupation_id
             else ''
      end AS OCCUPA
	, case when race.r_rp1853_nation_id is not null
            then race.r_rp1853_nation_id
            else ''
      end AS RACE
	, case when nation.r_rp1853_nation_id is not null
            then nation.r_rp1853_nation_id
            else ''
      end AS NATION
	, case when t_health_family.f_patient_religion_id is not null and trim(t_health_family.f_patient_religion_id) <> 'null'
		then t_health_family.f_patient_religion_id
		else ''
	   end AS RELIGION
	, case when r_rp1853_education_id is not null and r_rp1853_education_id <>''
            then r_rp1853_education_id
            else '' end AS EDUCATE
	, t_health_family.f_patient_family_status_id AS FSTATUS
	, (t_health_family.patient_father_pid) AS FATHER
	, (t_health_family.patient_mother_pid) AS MOTHER
	, (t_health_family.patient_couple_id) AS COUPLE
	, CASE
		when t_health_family.patient_move_in_date_time IS NOT NULL
			and length(t_health_family.patient_move_in_date_time)>9
			then (to_number(substring(t_health_family.patient_move_in_date_time,1,4),'9999')-543)
				|| substring(t_health_family.patient_move_in_date_time,6,2)
				|| substring(t_health_family.patient_move_in_date_time,9,2)
		ELSE '' END AS MOVEIN
	, CASE when (t_health_family.f_patient_discharge_status_id='1'
            or t_health_family.f_patient_discharge_status_id='2'
            or t_health_family.f_patient_discharge_status_id='3')
			then t_health_family.f_patient_discharge_status_id
		ELSE '9'  END AS DISCHAR
	, CASE when t_health_family.patient_discharge_date_time IS NOT NULL
			and length(t_health_family.patient_discharge_date_time)>9
			then (to_number(substring(t_health_family.patient_discharge_date_time,1,4),'9999')-543)
				|| substring(t_health_family.patient_discharge_date_time,6,2)
				|| substring(t_health_family.patient_discharge_date_time,9,2)
		ELSE '' END AS DDISCH
	, case when r_rp1853_blood_id is not null and r_rp1853_blood_id <>''
            then r_rp1853_blood_id
            else '' end AS BGROUP
	,case when t_health_family.r_rp1853_foreign_id is not null and  t_health_family.r_rp1853_foreign_id <> ''
			and trim(t_health_family.r_rp1853_foreign_id) <> 'null'
            then t_health_family.r_rp1853_foreign_id
            else '' end AS LABOR
	,village_tambon||village_moo AS VHID
	,t_health_family.f_patient_area_status_id AS TYPEAREA
    , case when length(t_health_family.modify_date_time) >= 10
                then cast(substring(t_health_family.modify_date_time,1,4) as numeric) - 543
                         || replace(replace(replace(substring(t_health_family.modify_date_time,5),'-',''),',',''),':','')
                when length(t_health_family.record_date_time) >= 10
                then cast(substring(t_health_family.record_date_time,1,4) as numeric) - 543
                         || replace(replace(replace(substring(t_health_family.record_date_time,5),'-',''),',',''),':','')
                else ''
       end as D_UPDATE --NOT NULL
from  t_health_family
     INNER JOIN t_health_home ON  t_health_family.t_health_home_id = t_health_home.t_health_home_id
     INNER JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
     LEFT JOIN t_patient ON t_patient.t_health_family_id = t_health_family.t_health_family_id
	 LEFT JOIN f_patient_prefix ON t_health_family.f_prefix_id = f_patient_prefix.f_patient_prefix_id
     LEFT JOIN f_patient_occupation on t_health_family.f_patient_occupation_id = f_patient_occupation.f_patient_occupation_id
     LEFT JOIN f_patient_blood_group on f_patient_blood_group.f_patient_blood_group_id = t_health_family.f_patient_blood_group_id
     LEFT JOIN f_patient_marriage_status on f_patient_marriage_status.f_patient_marriage_status_id = t_health_family.f_patient_marriage_status_id
     LEFT JOIN f_patient_education_type on f_patient_education_type.f_patient_education_type_id = t_health_family.f_patient_education_type_id
     LEFT JOIN f_patient_nation race on t_health_family.f_patient_race_id = race.f_patient_nation_id
     LEFT JOIN f_patient_nation nation on t_health_family.f_patient_nation_id = nation.f_patient_nation_id
   ,b_site

WHERE  t_health_village.village_moo <> '0'
    and health_family_active = '1'
    and substring(t_health_family.modify_date_time,1,16) >= ?
    and substring(t_health_family.modify_date_time,1,16) >= ?
)
union
(SELECT distinct
	b_site.b_visit_office_id AS PCUCODE
        , case when (t_patient.f_patient_foreigner_id in ('2','3','4')
                    or t_health_family.r_rp1853_foreign_id in ('11','12','13','14','21','22','23'))
                then case when length(t_health_family.t_health_family_id) = 18
                            then '234' || substring(t_health_family.t_health_family_id,9)
                          when length(t_health_family.t_health_family_id) = 15
                            then '234' || substring(t_health_family.t_health_family_id,4)
                          else ''
                      end
                else t_health_family.patient_pid
          end as CID
	, t_health_family.health_family_hn_hcis AS PID
	, t_health_home.home_serial AS HID
	, case when f_patient_prefix.r_rp1853_prefix_id is not null
                then f_patient_prefix.r_rp1853_prefix_id
                else ''
          end AS PRENAME
	, t_health_family.patient_name AS NAME
	, t_health_family.patient_last_name AS LNAME
	, t_patient.patient_hn AS HN
	, case when t_health_family.f_sex_id is not null and trim(t_health_family.f_sex_id) in ('1','2')
                then t_health_family.f_sex_id
                else ''
      end AS SEX
     , CASE  when length(t_health_family.patient_birthday)>9 and t_health_family.patient_birthday_true = '1'
           then  (to_number(substring(t_health_family.patient_birthday,1,4),'9999') - 543)
                    || substring(t_health_family.patient_birthday,6,2)
                    || substring(t_health_family.patient_birthday,9,2)
           when length(t_health_family.patient_birthday)>9 and t_health_family.patient_birthday_true <> '1'
           then (to_number(substring(t_health_family.patient_birthday,1,4),'9999') - 543)
                    || '0101'
          ELSE ''  END AS BIRTH
	, t_health_home.health_home_house AS HOUSE
	,  case when t_health_village.village_moo='' then  '0'
            else t_health_village.village_moo end AS VILLAGE
	,  case when substring(t_health_village.village_tambon,5,2)='' then '0'
            else substring(t_health_village.village_tambon,5,2) end AS TAMBON
	,  case when substring(t_health_village.village_ampur,3,2)='' then '0'
            else substring(t_health_village.village_ampur,3,2) end AS AMPUR
	,  case when substring(t_health_village.village_changwat,1,2)='' then '0'
            else substring(t_health_village.village_changwat,1,2) end AS CHANGWAT
	, r_rp1853_marriage_id AS MSTATUS
	, case when f_patient_occupation.r_rp1853_occupation_id is not null
             then f_patient_occupation.r_rp1853_occupation_id
             else ''
      end AS OCCUPA
	, case when race.r_rp1853_nation_id is not null
            then race.r_rp1853_nation_id
            else ''
      end AS RACE
	, case when nation.r_rp1853_nation_id is not null
            then nation.r_rp1853_nation_id
            else ''
      end AS NATION
	, case when t_health_family.f_patient_religion_id is not null and trim(t_health_family.f_patient_religion_id) <> 'null'
		then t_health_family.f_patient_religion_id
		else ''
	  end AS RELIGION
	, case when r_rp1853_education_id is not null and r_rp1853_education_id <>''
                then r_rp1853_education_id
                else '' end AS EDUCATE
	, t_health_family.f_patient_family_status_id AS FSTATUS
	, (t_health_family.patient_father_pid) AS FATHER
	, (t_health_family.patient_mother_pid) AS MOTHER
	, (t_health_family.patient_couple_id) AS COUPLE
	, CASE
		when t_health_family.patient_move_in_date_time IS NOT NULL
			and length(t_health_family.patient_move_in_date_time)>9
			then (to_number(substring(t_health_family.patient_move_in_date_time,1,4),'9999')-543)
				|| substring(t_health_family.patient_move_in_date_time,6,2)
				|| substring(t_health_family.patient_move_in_date_time,9,2)
		ELSE '' END AS MOVEIN
	, CASE when (t_health_family.f_patient_discharge_status_id='1'
            or t_health_family.f_patient_discharge_status_id='2'
            or t_health_family.f_patient_discharge_status_id='3')
			then t_health_family.f_patient_discharge_status_id
		ELSE '9'  END AS DISCHAR
	, CASE when t_health_family.patient_discharge_date_time IS NOT NULL
			and length(t_health_family.patient_discharge_date_time)>9
			then (to_number(substring(t_health_family.patient_discharge_date_time,1,4),'9999')-543)
				|| substring(t_health_family.patient_discharge_date_time,6,2)
				|| substring(t_health_family.patient_discharge_date_time,9,2)
		ELSE '' END AS DDISCH
	, case when r_rp1853_blood_id is not null and r_rp1853_blood_id <>''
            then r_rp1853_blood_id
            else '' end AS BGROUP
	,case when t_health_family.r_rp1853_foreign_id is not null and  t_health_family.r_rp1853_foreign_id <> ''
			and trim(t_health_family.r_rp1853_foreign_id) <> 'null'
			then t_health_family.r_rp1853_foreign_id
            else '' end AS LABOR
	,case when village_moo = '0'
        then ''
        else village_tambon||village_moo end AS VHID
	,t_health_family.f_patient_area_status_id AS TYPEAREA
    , case when length(t_health_family.modify_date_time) >= 10
                then cast(substring(t_health_family.modify_date_time,1,4) as numeric) - 543
                         || replace(replace(replace(substring(t_health_family.modify_date_time,5),'-',''),',',''),':','')
                when length(t_health_family.record_date_time) >= 10
                then cast(substring(t_health_family.record_date_time,1,4) as numeric) - 543
                         || replace(replace(replace(substring(t_health_family.record_date_time,5),'-',''),',',''),':','')
                else ''
       end as D_UPDATE --NOT NULL
from  t_visit
    INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id
    INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id
	 LEFT JOIN f_patient_prefix ON t_health_family.f_prefix_id = f_patient_prefix.f_patient_prefix_id
	INNER JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    INNER JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
     LEFT JOIN f_patient_occupation on t_health_family.f_patient_occupation_id = f_patient_occupation.f_patient_occupation_id
     LEFT JOIN f_patient_blood_group on f_patient_blood_group.f_patient_blood_group_id = t_health_family.f_patient_blood_group_id
     LEFT JOIN f_patient_marriage_status on f_patient_marriage_status.f_patient_marriage_status_id = t_health_family.f_patient_marriage_status_id
     LEFT JOIN f_patient_education_type on f_patient_education_type.f_patient_education_type_id = t_health_family.f_patient_education_type_id
     LEFT JOIN f_patient_nation race on t_health_family.f_patient_race_id = race.f_patient_nation_id
     LEFT JOIN f_patient_nation nation on t_health_family.f_patient_nation_id = nation.f_patient_nation_id
    ,b_site
where
    health_family_active = '1'
    and substring(t_visit.visit_begin_visit_time,1,16) >= ?
    and substring(t_visit.visit_begin_visit_time,1,16) <= ?) ) person