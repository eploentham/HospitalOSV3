-- ทดสอบภาษาไทย
(SELECT 
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
	, t_health_home.health_home_house AS HOUSE
	, CASE 
		when t_health_village.village_moo = '00'      
			OR t_health_village.village_moo = '0'      
			then t_health_home.health_home_moo      
		ELSE t_health_village.village_moo END AS VILLAGE
	, CASE 
		when (t_health_village.village_moo = '00'      
			OR t_health_village.village_moo = '0')      
			and t_health_home.health_home_tambon IS NOT NULL      
			and t_health_home.health_home_tambon <> ''      
			and UPPER(t_health_home.health_home_tambon) <> 'NULL'      
			then SUBSTRING(t_health_home.health_home_tambon,5,2)      
		when (t_health_village.village_moo <> '00'      OR t_health_village.village_moo <> '0')      
			and t_health_village.village_tambon IS NOT NULL      
			and t_health_village.village_tambon <> ''      
			and UPPER(t_health_village.village_tambon) <> 'NULL'      
			then SUBSTRING(t_health_village.village_tambon,5,2)      
		ELSE '' END AS TAMBON
	, CASE 
		when (t_health_village.village_moo = '00'      
			OR t_health_village.village_moo = '0')      
			and t_health_home.health_home_amphur IS NOT NULL      
			and t_health_home.health_home_amphur <> ''      
			and UPPER(t_health_home.health_home_amphur) <> 'NULL'      
			then SUBSTRING(t_health_home.health_home_amphur,3,2)      
		when (t_health_village.village_moo <> '00'      
			OR t_health_village.village_moo <> '0')      
			and t_health_village.village_ampur IS NOT NULL      
			and t_health_village.village_ampur <> ''      
			and UPPER(t_health_village.village_ampur) <> 'NULL'      
			then SUBSTRING(t_health_village.village_ampur,3,2)      
		ELSE '' END AS AMPUR
	, CASE 
		when (t_health_village.village_moo = '00'      
			OR t_health_village.village_moo = '0')      
			and t_health_home.health_home_changwat IS NOT NULL      
			and t_health_home.health_home_changwat <> ''      
			and UPPER(t_health_home.health_home_changwat) <> 'NULL'      
			then SUBSTRING(t_health_home.health_home_changwat,1,2)      
		when (t_health_village.village_moo <> '00'      
			OR t_health_village.village_moo <> '0')      
			and t_health_village.village_changwat IS NOT NULL      
			and t_health_village.village_changwat <> ''      
			and UPPER(t_health_village.village_changwat) <> 'NULL'      
			then SUBSTRING(t_health_village.village_changwat,1,2)      
		ELSE '' END AS CHANGWAT
	, t_health_family.f_patient_marriage_status_id AS MSTATUS
	, t_health_family.f_patient_occupation_id AS OCCUPA
	, t_health_family.f_patient_race_id AS RACE
	, t_health_family.f_patient_nation_id AS NATION
	, CASE 
		when (t_health_family.f_patient_religion_id IS NOT NULL      
			and t_health_family.f_patient_religion_id <> ''       
			and t_health_family.f_patient_religion_id <> 'null')       
			then t_health_family.f_patient_religion_id  
		ELSE ''  END AS RELIGION
	, t_health_family.f_patient_education_type_id AS EDUCATE
	, t_health_family.f_patient_family_status_id AS FSTATUS
	, (t_health_family.patient_father_firstname || '  '|| t_health_family.patient_father_lastname) AS FATHER
	, (t_health_family.patient_mother_firstname || '  '|| t_health_family.patient_mother_lastname) AS MOTHER
	, (t_health_family.patient_couple_firstname || '  '|| t_health_family.patient_couple_lastname) AS COUPLE
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
	, CASE when length(t_health_family.modify_date_time)>=10
                then (to_number(substring(t_health_family.modify_date_time,1,4),'9999') - 543)      
                        || substring(t_health_family.modify_date_time,6,2)      
                        || substring(t_health_family.modify_date_time,9,2)      
		ELSE ''  END AS UPDATE   
from  t_health_village
     INNER JOIN t_health_home ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
	 INNER JOIN t_health_family ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
         LEFT JOIN t_patient ON t_patient.t_health_family_id = t_health_family.t_health_family_id
	 LEFT JOIN f_patient_prefix ON t_health_family.f_prefix_id = f_patient_prefix.f_patient_prefix_id
   ,b_site

WHERE  t_health_village.village_moo <> '0' and length(t_health_family.patient_pid)=13
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
	, t_health_home.health_home_house AS HOUSE
	, CASE 
		when t_health_village.village_moo = '00'      
			OR t_health_village.village_moo = '0'      
			then t_health_home.health_home_moo      
		ELSE t_health_village.village_moo END AS VILLAGE
	, CASE 
		when (t_health_village.village_moo = '00'      
			OR t_health_village.village_moo = '0')      
			and t_health_home.health_home_tambon IS NOT NULL      
			and t_health_home.health_home_tambon <> ''      
			and UPPER(t_health_home.health_home_tambon) <> 'NULL'      
			then SUBSTRING(t_health_home.health_home_tambon,5,2)      
		when (t_health_village.village_moo <> '00'      OR t_health_village.village_moo <> '0')      
			and t_health_village.village_tambon IS NOT NULL      
			and t_health_village.village_tambon <> ''      
			and UPPER(t_health_village.village_tambon) <> 'NULL'      
			then SUBSTRING(t_health_village.village_tambon,5,2)      
		ELSE '' END AS TAMBON
	, CASE 
		when (t_health_village.village_moo = '00'      
			OR t_health_village.village_moo = '0')      
			and t_health_home.health_home_amphur IS NOT NULL      
			and t_health_home.health_home_amphur <> ''      
			and UPPER(t_health_home.health_home_amphur) <> 'NULL'      
			then SUBSTRING(t_health_home.health_home_amphur,3,2)      
		when (t_health_village.village_moo <> '00'      
			OR t_health_village.village_moo <> '0')      
			and t_health_village.village_ampur IS NOT NULL      
			and t_health_village.village_ampur <> ''      
			and UPPER(t_health_village.village_ampur) <> 'NULL'      
			then SUBSTRING(t_health_village.village_ampur,3,2)      
		ELSE '' END AS AMPUR
	, CASE 
		when (t_health_village.village_moo = '00'      
			OR t_health_village.village_moo = '0')      
			and t_health_home.health_home_changwat IS NOT NULL      
			and t_health_home.health_home_changwat <> ''      
			and UPPER(t_health_home.health_home_changwat) <> 'NULL'      
			then SUBSTRING(t_health_home.health_home_changwat,1,2)      
		when (t_health_village.village_moo <> '00'      
			OR t_health_village.village_moo <> '0')      
			and t_health_village.village_changwat IS NOT NULL      
			and t_health_village.village_changwat <> ''      
			and UPPER(t_health_village.village_changwat) <> 'NULL'      
			then SUBSTRING(t_health_village.village_changwat,1,2)      
		ELSE '' END AS CHANGWAT
	, t_health_family.f_patient_marriage_status_id AS MSTATUS
	, t_health_family.f_patient_occupation_id AS OCCUPA
	, t_health_family.f_patient_race_id AS RACE
	, t_health_family.f_patient_nation_id AS NATION
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
	, (t_health_family.patient_father_firstname || '  '|| t_health_family.patient_father_lastname) AS FATHER
	, (t_health_family.patient_mother_firstname || '  '|| t_health_family.patient_mother_lastname) AS MOTHER
	, (t_health_family.patient_couple_firstname || '  '|| t_health_family.patient_couple_lastname) AS COUPLE
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
	, CASE when length(t_health_family.modify_date_time)>=10
                then (to_number(substring(t_health_family.modify_date_time,1,4),'9999') - 543)      
                        || substring(t_health_family.modify_date_time,6,2)      
                        || substring(t_health_family.modify_date_time,9,2)      
		ELSE ''  END AS UPDATE   
from  t_visit 
    INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id
    INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id
    LEFT JOIN f_patient_prefix ON t_health_family.f_prefix_id = f_patient_prefix.f_patient_prefix_id
    LEFT JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
    LEFT JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id 
    ,b_site 

where  substring(t_visit.visit_begin_visit_time,1,10) >= ?
    and substring(t_visit.visit_begin_visit_time,1,10) <= ?   
    AND health_family_active = '1'
    and length(t_health_family.patient_pid)=13
)
