 ((SELECT 
	b_site.b_visit_office_id AS PCUCODE
	, case when t_health_family.patient_pid IS NOT NULL and t_health_family.patient_pid <> ''
                    then t_health_family.patient_pid
                    else t_patient.patient_pid
      end AS CID
	, t_health_family.health_family_hn_hcis AS PID
	, to_char(t_health_home.home_serial,'00000') AS HID
	, CASE when f_patient_prefix.patient_prefix_description is not null
            THEN f_patient_prefix.patient_prefix_description
            ELSE '' END AS PRENAME
	, t_health_family.patient_name AS NAME
	, t_health_family.patient_last_name AS LNAME
	, t_patient.patient_hn AS HN
	, t_health_family.f_sex_id AS SEX
	, CASE when (t_health_family.patient_birthday is not null 
                            and t_health_family.patient_birthday <> '' 
                            and t_health_family.patient_birthday <> 'null')
			then (to_number(substring(t_health_family.patient_birthday,1,4),'9999') - 543)
				|| substring(t_health_family.patient_birthday,6,2)
				|| substring(t_health_family.patient_birthday,9,2)
		ELSE ''  END AS BIRTH
    , t_nhso_patient.nhso_patient_addreg_house AS HOUSE     
	, case when trim(nhso_patient_addreg_moo) <> '' 
            then nhso_patient_addreg_moo
            else '0' 
      end as VILLAGE
	, CASE when t_nhso_patient.nhso_patient_addreg_tambon IS NOT NULL
                            and trim(t_nhso_patient.nhso_patient_addreg_tambon) <> ''
                            and UPPER(t_nhso_patient.nhso_patient_addreg_tambon) <> 'NULL'
                 then SUBSTRING(t_nhso_patient.nhso_patient_addreg_tambon,5,2)
                  ELSE '' END AS TAMBON
	, CASE when t_nhso_patient.nhso_patient_addreg_amphur IS NOT NULL
                            and t_nhso_patient.nhso_patient_addreg_amphur <> ''
                            and UPPER(t_nhso_patient.nhso_patient_addreg_amphur) <> 'NULL'
                then SUBSTRING(t_nhso_patient.nhso_patient_addreg_amphur,3,2)
                ELSE '' END AS AMPUR
	, CASE when t_nhso_patient.nhso_patient_addreg_changwat IS NOT NULL
                        and t_nhso_patient.nhso_patient_addreg_changwat <> ''
                        and UPPER(t_nhso_patient.nhso_patient_addreg_changwat) <> 'NULL'
                then SUBSTRING(t_nhso_patient.nhso_patient_addreg_changwat,1,2)
                ELSE '' END AS CHANGWAT
	, t_health_family.f_patient_marriage_status_id AS MSTATUS
	, case when (t_health_family.f_patient_occupation_id = '000') then '000'
           when (t_health_family.f_patient_occupation_id = '502') then '001'
           when (t_health_family.f_patient_occupation_id = '403') then '002'
           when (t_health_family.f_patient_occupation_id = '606') then '003'
           when (t_health_family.f_patient_occupation_id IN ('201','202','204','208')) then '004'
           when (t_health_family.f_patient_occupation_id IN ('301','302')) then '005'
           when (t_health_family.f_patient_occupation_id = '206') then '006'
           when (t_health_family.f_patient_occupation_id IN ('803','203','205')) then '007'
           when (t_health_family.f_patient_occupation_id = '507') then '008'
           when (t_health_family.f_patient_occupation_id = '906') then '009'
           when (t_health_family.f_patient_occupation_id = '506') then '012'
           when (t_health_family.f_patient_occupation_id = '901') then '013'
           when (t_health_family.f_patient_occupation_id = '904') then '014'
           when (t_health_family.f_patient_occupation_id IN ('900','905')) then '015'
           when (t_health_family.f_patient_occupation_id = '901') then '999'
          else '010' end AS OCCUPA
	, case when length(t_health_family.f_patient_race_id) = 1 then '00'|| t_health_family.f_patient_race_id 
           when length(t_health_family.f_patient_race_id) = 2 then '0'|| t_health_family.f_patient_race_id
            else t_health_family.f_patient_race_id end AS RACE
	, case when length(t_health_family.f_patient_nation_id) = 1 then '00'|| t_health_family.f_patient_nation_id 
           when length(t_health_family.f_patient_nation_id) = 2 then '0'|| t_health_family.f_patient_nation_id
            else t_health_family.f_patient_nation_id end AS NATION
	, CASE
		when (t_health_family.f_patient_religion_id IS NOT NULL
			and t_health_family.f_patient_religion_id <> ''
			and t_health_family.f_patient_religion_id <> 'null')
			then t_health_family.f_patient_religion_id
		ELSE ''  END AS RELIGION
	, CASE WHEN length(t_health_family.f_patient_education_type_id)=2
            THEN substring(t_health_family.f_patient_education_type_id,1,1)
            ELSE t_health_family.f_patient_education_type_id END AS EDUCATE
	, t_health_family.f_patient_family_status_id AS FSTATUS
	, case when length(t_health_family.patient_father_pid) = 13
 then t_health_family.patient_father_pid
else '' end AS FATHER
	, case when length(t_health_family.patient_mother_pid) = 13
then t_health_family.patient_mother_pid
else '' end  AS MOTHER
	, case when length(t_health_family.patient_couple_id) = 13
then t_health_family.patient_couple_id
else '' end  AS COUPLE
/*,t_health_family.patient_father_pid as FATHER
,t_health_family.patient_mother_pid as MOTHER
,t_health_family.patient_couple_id as COUPLE*/
	, CASE
		when t_health_family.patient_move_in_date_time IS NOT NULL
			and t_health_family.patient_move_in_date_time <> ''
			and UPPER(t_health_family.patient_move_in_date_time) <> 'NULL'
			then (to_number(substring(t_health_family.patient_move_in_date_time,1,4),'9999')-543)
				|| substring(t_health_family.patient_move_in_date_time,6,2)
				|| substring(t_health_family.patient_move_in_date_time,9,2)
		ELSE '' END AS MOVEIN
	, CASE
		when t_health_family.f_patient_discharge_status_id IS NOT NULL
			and t_health_family.f_patient_discharge_status_id <> ''
			and t_health_family.f_patient_discharge_status_id <> '0'
			then t_health_family.f_patient_discharge_status_id
		ELSE '' END AS DISCHAR
	, CASE
		when t_health_family.patient_discharge_date_time IS NOT NULL
			and t_health_family.patient_discharge_date_time <> ''
			and UPPER(t_health_family.patient_discharge_date_time) <> 'NULL'
			then (to_number(substring(t_health_family.patient_discharge_date_time,1,4),'9999')-543)
				|| substring(t_health_family.patient_discharge_date_time,6,2)
				|| substring(t_health_family.patient_discharge_date_time,9,2)
		ELSE '' END AS DDISCH
	, CASE
		when (t_health_family.f_patient_blood_group_id = '2')
			then '1'
		when (t_health_family.f_patient_blood_group_id = '3')
			then '2'
		when (t_health_family.f_patient_blood_group_id = '4')
			then '3'
		when (t_health_family.f_patient_blood_group_id = '5')
			then '4'
		ELSE ''      END AS BGROUP
	, '' AS LABOR
    ,t_patient.f_patient_area_status_id as TYPEAREA --เพิ่มตาม datadict
	, CASE when length(t_health_family.modify_date_time)>=10
                then (to_number(substring(t_health_family.modify_date_time,1,4),'9999') - 543)
                        || substring(t_health_family.modify_date_time,6,2)
                        || substring(t_health_family.modify_date_time,9,2)
		ELSE ''  END AS UPDATE
	, CASE when length(t_health_family.record_date_time)>=10
                then (to_number(substring(t_health_family.record_date_time,1,4),'9999') - 543)
                        || substring(t_health_family.record_date_time,6,2)
                        || substring(t_health_family.record_date_time,9,2)
		ELSE ''  END AS NOTEDATE
,'1' as active 
from  t_health_family
     inner join t_patient on t_patient.t_health_family_id = t_health_family.t_health_family_id
     inner join t_nhso_patient on t_nhso_patient.t_patient_id =  t_patient.t_patient_id
     inner join t_health_home on t_health_family.t_health_home_id = t_health_home.t_health_home_id
     left join t_nhso_home on t_nhso_home.t_health_home_id = t_health_home.t_health_home_id
     left join f_patient_prefix on t_health_family.f_prefix_id = f_patient_prefix.f_patient_prefix_id
   ,b_site

WHERE  
    substring(t_health_family.modify_date_time,1,10) >= ?
    and substring(t_health_family.modify_date_time,1,10) <= ?
)
union 
( 
SELECT 
 	b_site.b_visit_office_id AS PCUCODE
	, t_health_family.patient_pid AS CID
	, t_health_family.health_family_hn_hcis AS PID
	, to_char(t_health_home.home_serial,'00000') AS HID
	, CASE when f_patient_prefix.patient_prefix_description is not null
            THEN f_patient_prefix.patient_prefix_description
            ELSE '' END AS PRENAME
	, t_health_family.patient_name AS NAME
	, t_health_family.patient_last_name AS LNAME
	, t_patient.patient_hn AS HN
	, t_health_family.f_sex_id AS SEX
	, CASE
		when (t_health_family.patient_birthday <> ''
			and t_health_family.patient_birthday <> 'null')
			then (to_number(substring(t_health_family.patient_birthday,1,4),'9999') - 543)
				|| substring(t_health_family.patient_birthday,6,2)
				|| substring(t_health_family.patient_birthday,9,2)
		ELSE ''  END AS BIRTH
, t_nhso_patient.nhso_patient_addreg_house AS HOUSE     
	, case when trim(nhso_patient_addreg_moo) <> '' 
            then nhso_patient_addreg_moo
            else '0' 
      end as VILLAGE
	, CASE when t_nhso_patient.nhso_patient_addreg_tambon IS NOT NULL
                            and trim(t_nhso_patient.nhso_patient_addreg_tambon) <> ''
                            and UPPER(t_nhso_patient.nhso_patient_addreg_tambon) <> 'NULL'
                 then SUBSTRING(t_nhso_patient.nhso_patient_addreg_tambon,5,2)
                  ELSE '' END AS TAMBON
	, CASE when t_nhso_patient.nhso_patient_addreg_amphur IS NOT NULL
                            and t_nhso_patient.nhso_patient_addreg_amphur <> ''
                            and UPPER(t_nhso_patient.nhso_patient_addreg_amphur) <> 'NULL'
                then SUBSTRING(t_nhso_patient.nhso_patient_addreg_amphur,3,2)
                ELSE '' END AS AMPUR
	, CASE when t_nhso_patient.nhso_patient_addreg_changwat IS NOT NULL
                        and t_nhso_patient.nhso_patient_addreg_changwat <> ''
                        and UPPER(t_nhso_patient.nhso_patient_addreg_changwat) <> 'NULL'
                then SUBSTRING(t_nhso_patient.nhso_patient_addreg_changwat,1,2)
                ELSE '' END AS CHANGWAT
	, t_health_family.f_patient_marriage_status_id AS MSTATUS
	, case when (t_health_family.f_patient_occupation_id = '000') then '000'
           when (t_health_family.f_patient_occupation_id = '502') then '001'
           when (t_health_family.f_patient_occupation_id = '403') then '002'
           when (t_health_family.f_patient_occupation_id = '606') then '003'
           when (t_health_family.f_patient_occupation_id IN ('201','202','204','208')) then '004'
           when (t_health_family.f_patient_occupation_id IN ('301','302')) then '005'
           when (t_health_family.f_patient_occupation_id = '206') then '006'
           when (t_health_family.f_patient_occupation_id IN ('803','203','205')) then '007'
           when (t_health_family.f_patient_occupation_id = '507') then '008'
           when (t_health_family.f_patient_occupation_id = '906') then '009'
           when (t_health_family.f_patient_occupation_id = '506') then '012'
           when (t_health_family.f_patient_occupation_id = '901') then '013'
           when (t_health_family.f_patient_occupation_id = '904') then '014'
           when (t_health_family.f_patient_occupation_id IN ('900','905')) then '015'
           when (t_health_family.f_patient_occupation_id = '901') then '999'
          else '010' end AS OCCUPA
	, case when length(t_health_family.f_patient_race_id) = 1 then '00'|| t_health_family.f_patient_race_id 
           when length(t_health_family.f_patient_race_id) = 2 then '0'|| t_health_family.f_patient_race_id
            else t_health_family.f_patient_race_id end AS RACE
	, case when length(t_health_family.f_patient_nation_id) = 1 then '00'|| t_health_family.f_patient_nation_id 
           when length(t_health_family.f_patient_nation_id) = 2 then '0'|| t_health_family.f_patient_nation_id
            else t_health_family.f_patient_nation_id end AS NATION
	, CASE
		when (t_health_family.f_patient_religion_id IS NOT NULL
			and t_health_family.f_patient_religion_id <> ''
			and t_health_family.f_patient_religion_id <> 'null')
			then t_health_family.f_patient_religion_id
		ELSE ''  END AS RELIGION
	, CASE WHEN length(t_health_family.f_patient_education_type_id)=2
            THEN substring(t_health_family.f_patient_education_type_id,1,1)
            ELSE t_health_family.f_patient_education_type_id END AS EDUCATE
	, t_health_family.f_patient_family_status_id AS FSTATUS
, case when length(t_health_family.patient_father_pid) = 13
 then t_health_family.patient_father_pid
else '' end AS FATHER
	, case when length(t_health_family.patient_mother_pid) = 13
then t_health_family.patient_mother_pid
else '' end  AS MOTHER
	, case when length(t_health_family.patient_couple_id) = 13
then t_health_family.patient_couple_id
else '' end  AS COUPLE

	, CASE
		when t_health_family.patient_move_in_date_time IS NOT NULL
			and t_health_family.patient_move_in_date_time <> ''
			and UPPER(t_health_family.patient_move_in_date_time) <> 'NULL'
			then (to_number(substring(t_health_family.patient_move_in_date_time,1,4),'9999')-543)
				|| substring(t_health_family.patient_move_in_date_time,6,2)
				|| substring(t_health_family.patient_move_in_date_time,9,2)
		ELSE '' END AS MOVEIN
	, CASE
		when t_health_family.f_patient_discharge_status_id IS NOT NULL
			and t_health_family.f_patient_discharge_status_id <> ''
			and t_health_family.f_patient_discharge_status_id <> '0'
			then t_health_family.f_patient_discharge_status_id
		ELSE '' END AS DISCHAR
	, CASE
		when t_health_family.patient_discharge_date_time IS NOT NULL
			and t_health_family.patient_discharge_date_time <> ''
			and UPPER(t_health_family.patient_discharge_date_time) <> 'NULL'
			then (to_number(substring(t_health_family.patient_discharge_date_time,1,4),'9999')-543)
				|| substring(t_health_family.patient_discharge_date_time,6,2)
				|| substring(t_health_family.patient_discharge_date_time,9,2)
		ELSE '' END AS DDISCH
	, CASE
		when (t_health_family.f_patient_blood_group_id = '2')
			then '1'
		when (t_health_family.f_patient_blood_group_id = '3')
			then '2'
		when (t_health_family.f_patient_blood_group_id = '4')
			then '3'
		when (t_health_family.f_patient_blood_group_id = '5')
			then '4'
		ELSE ''      END AS BGROUP
	, '' AS LABOR
,t_patient.f_patient_area_status_id as TYPEAREA --เพิ่มตาม datadict
	, CASE when length(t_health_family.modify_date_time)>=10
                then (to_number(substring(t_health_family.modify_date_time,1,4),'9999') - 543)
                        || substring(t_health_family.modify_date_time,6,2)
                        || substring(t_health_family.modify_date_time,9,2)
		ELSE ''  END AS UPDATE
	, CASE when length(t_health_family.record_date_time)>=10
                then (to_number(substring(t_health_family.record_date_time,1,4),'9999') - 543)
                        || substring(t_health_family.record_date_time,6,2)
                        || substring(t_health_family.record_date_time,9,2)
		ELSE ''  END AS NOTEDATE
,'1' as active 
from  t_visit
    INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id
    INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id
    INNER JOIN t_nhso_patient ON t_patient.t_patient_id = t_nhso_patient.t_patient_id
    LEFT JOIN f_patient_prefix ON t_health_family.f_prefix_id = f_patient_prefix.f_patient_prefix_id
    LEFT JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    ,b_site

where 
    substring(t_visit.visit_begin_visit_time,1,10) >= ?
    and substring(t_visit.visit_begin_visit_time,1,10) <= ?
 )) 
