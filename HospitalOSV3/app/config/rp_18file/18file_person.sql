--ทดสอบภาษาไทย
(SELECT 
	b_site.b_visit_office_id AS PCUCODE
	, t_health_family.patient_pid AS CID
	, t_health_family.health_family_hn_hcis AS PID
	, to_char(t_health_home.home_serial,'00000') AS HID
	, f_patient_prefix.patient_prefix_description AS PRENAME
	, t_health_family.patient_name AS NAME
	, t_health_family.patient_last_name AS LNAME
	, t_patient.patient_hn AS HN
	, t_health_family.f_sex_id AS SEX
	, CASE 
		when (length(t_health_family.patient_birthday) >9)
			then (to_number(substring(t_health_family.patient_birthday,1,4),'9999') - 543)      
				|| substring(t_health_family.patient_birthday,6,2)      
				|| substring(t_health_family.patient_birthday,9,2) 
		ELSE ''  END AS BIRTH 
	, t_health_home.health_home_house AS HOUSE
	,  CASE WHEN length(t_health_village.village_moo) = 1 then '0' || t_health_village.village_moo else t_health_village.village_moo end AS VILLAGE
	,  SUBSTRING(t_health_village.village_tambon,5,2) AS TAMBON
	,  SUBSTRING(t_health_village.village_ampur,3,2) AS AMPUR
	,  SUBSTRING(t_health_village.village_changwat,1,2)  AS CHANGWAT
	, CASE when (t_health_family.f_patient_marriage_status_id = '1'
              OR t_health_family.f_patient_marriage_status_id = '2'
              OR t_health_family.f_patient_marriage_status_id = '3'
              OR t_health_family.f_patient_marriage_status_id = '4'
              OR t_health_family.f_patient_marriage_status_id = '5'
              OR t_health_family.f_patient_marriage_status_id = '6')      
             THEN t_health_family.f_patient_marriage_status_id
             ELSE '9' END AS MSTATUS
	, case when (t_health_family.f_patient_occupation_id = '403') then '002'
           when (t_health_family.f_patient_occupation_id = '502') then '001'
           when (t_health_family.f_patient_occupation_id = '507') then '008'
           when (t_health_family.f_patient_occupation_id = '606') then '003'
           when (t_health_family.f_patient_occupation_id = '900') then '015'
           when (t_health_family.f_patient_occupation_id = '902') then '010'
          else '010' end AS OCCUPA
	, case when length(t_health_family.f_patient_race_id) = 1 then '00'|| t_health_family.f_patient_race_id 
           when length(t_health_family.f_patient_race_id) = 2 then '0'|| t_health_family.f_patient_race_id
            else t_health_family.f_patient_race_id end AS RACE
	, case when length(t_health_family.f_patient_nation_id) = 1 then '00'|| t_health_family.f_patient_nation_id 
           when length(t_health_family.f_patient_nation_id) = 2 then '0'|| t_health_family.f_patient_nation_id
            else t_health_family.f_patient_nation_id end AS NATION
	, t_health_family.f_patient_religion_id AS RELIGION
	, t_health_family.f_patient_education_type_id AS EDUCATE
	, t_health_family.f_patient_family_status_id AS FSTATUS
	, (t_health_family.patient_father_pid) AS FATHER
	, (t_health_family.patient_mother_pid) AS MOTHER
	, (t_health_family.patient_couple_id) AS COUPLE
	, CASE 
		when t_health_family.patient_move_in_date_time IS NOT NULL      
			and length(t_health_family.patient_move_in_date_time) >9
			then (to_number(substring(t_health_family.patient_move_in_date_time,1,4),'9999')-543)     
				|| substring(t_health_family.patient_move_in_date_time,6,2)      
				|| substring(t_health_family.patient_move_in_date_time,9,2)      
		ELSE '' END AS MOVEIN
	, CASE when (t_health_family.f_patient_discharge_status_id = '1'
              OR t_health_family.f_patient_discharge_status_id = '2'
              OR t_health_family.f_patient_discharge_status_id = '3')      
             THEN t_health_family.f_patient_discharge_status_id
             ELSE '9' END AS DISCHAR
	, CASE 
		when t_health_family.patient_discharge_date_time IS NOT NULL      
			and length(t_health_family.patient_discharge_date_time) >9
			then (to_number(substring(t_health_family.patient_discharge_date_time,1,4),'9999')-543)      
				|| substring(t_health_family.patient_discharge_date_time,6,2)      
				|| substring(t_health_family.patient_discharge_date_time,9,2)      
		ELSE '' END AS DDISCH
	, CASE 
		when (t_health_family.f_patient_blood_group_id = '2')  then '1'      
		when (t_health_family.f_patient_blood_group_id = '3')  then '2'      
		when (t_health_family.f_patient_blood_group_id = '4')  then '3'      
		when (t_health_family.f_patient_blood_group_id = '5')  then '4'      
		ELSE ''      END AS BGROUP
	, CASE when (t_health_family.f_patient_nation_id = '99') then ''
           when (t_health_family.f_patient_nation_id <> '99' and t_patient.f_patient_foreigner_id = '2') then '11'
           when (t_health_family.f_patient_nation_id <> '99' and t_patient.f_patient_foreigner_id = '3') then '21'
           when (t_health_family.f_patient_nation_id <> '99' and t_patient.f_patient_foreigner_id = '') then '23'
    else ''  end AS LABOR
	, CASE when length(t_health_family.modify_date_time)>=10
                then (to_number(substring(t_health_family.modify_date_time,1,4),'9999') - 543)      
                        || substring(t_health_family.modify_date_time,6,2)      
                        || substring(t_health_family.modify_date_time,9,2)      
		ELSE ''  END AS UPDATE   
        , village_number as VHID
        , t_health_family.f_patient_area_status_id as TYPEAREA
from  t_health_village
     INNER JOIN t_health_home ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
     INNER JOIN t_health_family ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
     LEFT JOIN t_patient ON t_patient.t_health_family_id = t_health_family.t_health_family_id
	 LEFT JOIN f_patient_prefix ON t_health_family.f_prefix_id = f_patient_prefix.f_patient_prefix_id
   ,b_site

WHERE  t_health_village.village_moo <> '0'
    AND health_family_active = '1'
)
union
(

SELECT 
	b_site.b_visit_office_id AS PCUCODE
	, t_health_family.patient_pid AS CID
	,t_health_family.health_family_hn_hcis AS PID
	, to_char(t_health_home.home_serial,'00000') AS HID
	, f_patient_prefix.patient_prefix_description AS PRENAME
	, t_health_family.patient_name AS NAME
	, t_health_family.patient_last_name AS LNAME
	, t_patient.patient_hn AS HN
	, t_health_family.f_sex_id AS SEX
	, CASE 
		when (length(t_health_family.patient_birthday) >9)
			then (to_number(substring(t_health_family.patient_birthday,1,4),'9999') - 543)      
				|| substring(t_health_family.patient_birthday,6,2)      
				|| substring(t_health_family.patient_birthday,9,2) 
		ELSE ''  END AS BIRTH 
	, t_health_home.health_home_house AS HOUSE
	,   CASE WHEN length(t_health_village.village_moo) = 1 then '0' || t_health_village.village_moo else t_health_village.village_moo end  AS VILLAGE
	,  SUBSTRING(t_health_village.village_tambon,5,2) AS TAMBON
	,  SUBSTRING(t_health_village.village_ampur,3,2) AS AMPUR
	,  SUBSTRING(t_health_village.village_changwat,1,2)  AS CHANGWAT
	, CASE when (t_health_family.f_patient_marriage_status_id = '1'
              OR t_health_family.f_patient_marriage_status_id = '2'
              OR t_health_family.f_patient_marriage_status_id = '3'
              OR t_health_family.f_patient_marriage_status_id = '4'
              OR t_health_family.f_patient_marriage_status_id = '5'
              OR t_health_family.f_patient_marriage_status_id = '6')      
             THEN t_health_family.f_patient_marriage_status_id
             ELSE '9' END AS MSTATUS
	, case when (t_health_family.f_patient_occupation_id = '403') then '002'
           when (t_health_family.f_patient_occupation_id = '502') then '001'
           when (t_health_family.f_patient_occupation_id = '507') then '008'
           when (t_health_family.f_patient_occupation_id = '606') then '003'
           when (t_health_family.f_patient_occupation_id = '900') then '015'
           when (t_health_family.f_patient_occupation_id = '902') then '010'
          else '010' end AS OCCUPA
	, case when length(t_health_family.f_patient_race_id) = 1 then '00'|| t_health_family.f_patient_race_id 
           when length(t_health_family.f_patient_race_id) = 2 then '0'|| t_health_family.f_patient_race_id
            else t_health_family.f_patient_race_id end AS RACE
	, case when length(t_health_family.f_patient_nation_id) = 1 then '00'|| t_health_family.f_patient_nation_id 
           when length(t_health_family.f_patient_nation_id) = 2 then '0'|| t_health_family.f_patient_nation_id
            else t_health_family.f_patient_nation_id end AS NATION
--	, t_health_family.f_patient_nation_id AS NATION
	, t_health_family.f_patient_religion_id AS RELIGION
	, t_health_family.f_patient_education_type_id AS EDUCATE
	, t_health_family.f_patient_family_status_id AS FSTATUS
	, (t_health_family.patient_father_pid) AS FATHER
	, (t_health_family.patient_mother_pid) AS MOTHER
	, (t_health_family.patient_couple_id) AS COUPLE
	, CASE 
		when t_health_family.patient_move_in_date_time IS NOT NULL      
			and length(t_health_family.patient_move_in_date_time) >9
			then (to_number(substring(t_health_family.patient_move_in_date_time,1,4),'9999')-543)     
				|| substring(t_health_family.patient_move_in_date_time,6,2)      
				|| substring(t_health_family.patient_move_in_date_time,9,2)      
		ELSE '' END AS MOVEIN
	, CASE when (t_health_family.f_patient_discharge_status_id = '1'
              OR t_health_family.f_patient_discharge_status_id = '2'
              OR t_health_family.f_patient_discharge_status_id = '3')      
             THEN t_health_family.f_patient_discharge_status_id
             ELSE '9' END AS DISCHAR
	, CASE 
		when t_health_family.patient_discharge_date_time IS NOT NULL      
			and length(t_health_family.patient_discharge_date_time) >9
			then (to_number(substring(t_health_family.patient_discharge_date_time,1,4),'9999')-543)      
				|| substring(t_health_family.patient_discharge_date_time,6,2)      
				|| substring(t_health_family.patient_discharge_date_time,9,2)      
		ELSE '' END AS DDISCH
	, CASE 
		when (t_health_family.f_patient_blood_group_id = '2')  then '1'      
		when (t_health_family.f_patient_blood_group_id = '3')  then '2'      
		when (t_health_family.f_patient_blood_group_id = '4')  then '3'      
		when (t_health_family.f_patient_blood_group_id = '5')  then '4'      
		ELSE ''      END AS BGROUP
	, CASE when (t_health_family.f_patient_nation_id = '99') then ''
           when (t_health_family.f_patient_nation_id <> '99' and t_patient.f_patient_foreigner_id = '2') then '11'
           when (t_health_family.f_patient_nation_id <> '99' and t_patient.f_patient_foreigner_id = '3') then '21'
           when (t_health_family.f_patient_nation_id <> '99' and t_patient.f_patient_foreigner_id = '') then '23'
    else ''  end AS LABOR
	, CASE when length(t_health_family.modify_date_time)>=10
                then (to_number(substring(t_health_family.modify_date_time,1,4),'9999') - 543)      
                        || substring(t_health_family.modify_date_time,6,2)      
                        || substring(t_health_family.modify_date_time,9,2)      
		ELSE ''  END AS UPDATE   
        , village_number as VHID
        , t_health_family.f_patient_area_status_id as TYPEAREA
from  t_visit
    INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id
    INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id
	 LEFT JOIN f_patient_prefix ON t_health_family.f_prefix_id = f_patient_prefix.f_patient_prefix_id
    INNER JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    INNER JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id 
	,b_site
--01/11/2550 - 23/01/2551
where t_health_village.village_moo = '0'
    AND health_family_active = '1'
    and substring(t_visit.visit_begin_visit_time,1,10) >= ?
    and substring(t_visit.visit_begin_visit_time,1,10) <= ?
)
