select 
	b_site.b_visit_office_id AS PCUCODE
	, to_char(t_health_home.home_serial,'00000') AS HID
	, t_health_home.health_home_number AS HOUSE_ID
	, t_health_home.health_home_house AS HOUSE
	, t_health_village.village_moo AS VILLAGE
	, t_health_home.health_home_road AS ROAD
	, SUBSTRING(t_health_village.village_tambon,5,2) AS TAMBON
	, SUBSTRING(t_health_village.village_ampur,3,2) AS AMPUR
	,SUBSTRING(t_health_village.village_changwat,1,2) AS CHANGWAT
	, t_health_home.health_home_family AS NFAMILY
	, CASE 
		when (t_health_home.health_home_responsible_zone = '0')         
			then '2'          
		when (t_health_home.health_home_responsible_zone = '1')         
			then t_health_home.health_home_responsible_zone         
		ELSE ''  END AS LOCATYPE
	, t_health_home.health_home_volunteer AS VHVID
	, t_health_home.health_home_owner as 		 HEADID
	, CASE WHEN t_health_home_water_eradicate.health_home_toilet IS NOT NULL     
            THEN t_health_home_water_eradicate.health_home_toilet
            ELSE '' END AS TOILET
	, CASE WHEN t_health_home_water_eradicate.health_home_water IS NOT NULL     
            then t_health_home_water_eradicate.health_home_water
            ELSE '' END AS WATER
	, CASE WHEN t_health_home_water_eradicate.f_health_home_water_type_id IS NOT NULL     
            then t_health_home_water_eradicate.f_health_home_water_type_id
            ELSE '' END AS WATTYPE
	, CASE WHEN t_health_home_water_eradicate.f_health_home_garbage_method_id IS NOT NULL     
            then t_health_home_water_eradicate.f_health_home_garbage_method_id
            ELSE '' END AS GARBAGE
	, CASE WHEN t_health_home_house_standard.health_home_care IS NOT NULL     
            then t_health_home_house_standard.health_home_care
            ELSE '' END AS HCARE
	, CASE WHEN t_health_home_house_standard.health_home_durability IS NOT NULL     
            then t_health_home_house_standard.health_home_durability
            ELSE '' END AS DURABLE
	, CASE WHEN t_health_home_house_standard.health_home_cleanness IS NOT NULL     
            then t_health_home_house_standard.health_home_cleanness
            ELSE '' END AS CLEAN
	, CASE WHEN t_health_home_house_standard.health_home_ventilation IS NOT NULL     
            then t_health_home_house_standard.health_home_ventilation
            ELSE '' END AS VENTILA
	, CASE WHEN t_health_home_house_standard.health_home_light IS NOT NULL     
            then t_health_home_house_standard.health_home_light
            ELSE '' END AS LIGHT
	, CASE WHEN t_health_home_water_eradicate.health_home_water_eradicate IS NOT NULL     
            then t_health_home_water_eradicate.health_home_water_eradicate
            ELSE '' END AS WATERTM
	, CASE WHEN t_health_home_food_standard.health_home_mixture_food IS NOT NULL     
            then t_health_home_food_standard.health_home_mixture_food
            ELSE '' END AS MFOOD
	, CASE WHEN t_health_home_bug_control.health_home_bug_control IS NOT NULL     
            then t_health_home_bug_control.health_home_bug_control
            ELSE '' END AS BCTRL
	, CASE WHEN t_health_home_bug_control.health_home_animal_control IS NOT NULL     
            then t_health_home_bug_control.health_home_animal_control
            ELSE '' END AS ACTRL
	, CASE when length(t_health_home.home_modify_date_time) >= 10
            then (to_number(substring(t_health_home.home_modify_date_time,1,4),'9999')-543)     
                    ||   substring(t_health_home.home_modify_date_time,6,2)     
                    ||   substring(t_health_home.home_modify_date_time,9,2)      
            when length(t_health_home.home_record_date_time) >= 10
            then (to_number(substring(t_health_home.home_record_date_time,1,4),'9999')-543)     
                    ||   substring(t_health_home.home_record_date_time,6,2)     
                    ||   substring(t_health_home.home_record_date_time,9,2)  
            ELSE '' END as UPDATE
        ,t_health_home.t_health_home_id as ID
        ,'' as NOTEDATE
,t_health_home.home_active as active
from  t_health_home 
	LEFT JOIN t_health_village  ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
	LEFT JOIN ( SELECT t_health_home_id AS home,max(sub_home_record_date_time) AS d  
			from  t_health_sub_home  WHERE sub_home_active = '1' group by home
        ) AS q  ON (t_health_home.t_health_home_id = q.home   )
	left join t_health_sub_home  ON (t_health_sub_home.t_health_home_id = t_health_home.t_health_home_id 
            and t_health_sub_home.sub_home_record_date_time = q.d)        
	left join t_health_home_water_eradicate  ON (t_health_home_water_eradicate.t_health_sub_home_id = t_health_sub_home.t_health_sub_home_id)         
	left join t_health_home_house_standard  ON 	(t_health_home_house_standard.t_health_sub_home_id = t_health_sub_home.t_health_sub_home_id)          
	left join t_health_home_food_standard  ON (t_health_home_food_standard.t_health_sub_home_id = t_health_sub_home.t_health_sub_home_id)         
	left join t_health_home_bug_control  ON (t_health_home_bug_control.t_health_sub_home_id = t_health_sub_home.t_health_sub_home_id) 
    ,b_site

where t_health_village.village_moo <> '0'
    AND t_health_home.home_active = '1'
    AND substring(t_health_home.home_modify_date_time,1,10) >= ?
    AND substring(t_health_home.home_modify_date_time,1,10) <= ?


