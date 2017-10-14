select b_site.b_visit_office_id AS PCUCODE
	,t_health_home.home_serial AS HID
	,t_health_home.health_home_number AS HOUSE_ID
	, t_health_home.health_home_house || ' ' || t_health_home.health_home_road AS HOUSE
	, trim(to_char(to_number(t_health_village.village_moo,'00'),'00')) AS VILLAGE
	, t_health_home.health_home_road AS ROAD
	, SUBSTRING(t_health_village.village_tambon,5,2) AS TAMBON
	, SUBSTRING(t_health_village.village_ampur,3,2) AS AMPUR
	,SUBSTRING(t_health_village.village_changwat,1,2) AS CHANGWAT
	,CASE WHEN (t_health_home.health_home_family ='' 
                            OR t_health_home.health_home_family IS NULL ) THEN '0'
        ELSE t_health_home.health_home_family END AS NFAMILY
	, CASE 	when (t_health_home.health_home_responsible_zone = '0') then '2'          	
		when (t_health_home.health_home_responsible_zone = '1')         		
			then t_health_home.health_home_responsible_zone         	
		ELSE ''  END as LOCATYPE
	, t_health_home.health_home_volunteer as VHVID 
	, SUB0.health_family_hn_hcis as HEADID
	, t_health_home_water_eradicate.health_home_toilet AS TOILET
	, t_health_home_water_eradicate.health_home_water AS WATER
	, t_health_home_water_eradicate.f_health_home_water_type_id AS WATTYPE
	, CASE WHEN t_health_home_water_eradicate.f_health_home_garbage_method_id in ('1','2','3','4')
	    THEN t_health_home_water_eradicate.f_health_home_garbage_method_id
	    ELSE '' END AS GARBAGE
	, t_health_home_house_standard.health_home_care AS HCARE
	, t_health_home_house_standard.health_home_durability AS DURABLE
	, t_health_home_house_standard.health_home_cleanness AS CLEAN
	, t_health_home_house_standard.health_home_ventilation AS VENTILA
	, t_health_home_house_standard.health_home_light AS LIGHT
	, t_health_home_water_eradicate.health_home_water_eradicate AS WATERTM
	, t_health_home_food_standard.health_home_mixture_food AS MFOOD
	, t_health_home_bug_control.health_home_bug_control AS BCTRL
	, t_health_home_bug_control.health_home_animal_control AS ACTRL
     ,village_tambon||village_moo as VHID
	
    , case when length(t_health_sub_home.sub_home_modify_date_time) >= 10
                then case when length(cast(substring(t_health_sub_home.sub_home_modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_sub_home.sub_home_modify_date_time,5),'-',''),',',''),':','')) = 14
                                  then (cast(substring(t_health_sub_home.sub_home_modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_sub_home.sub_home_modify_date_time,5),'-',''),',',''),':',''))
                          when length(cast(substring(t_health_sub_home.sub_home_modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_sub_home.sub_home_modify_date_time,5),'-',''),',',''),':','')) =12
                                  then (cast(substring(t_health_sub_home.sub_home_modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_sub_home.sub_home_modify_date_time,5),'-',''),',',''),':','')) || '00'
                          when length(cast(substring(t_health_sub_home.sub_home_modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_sub_home.sub_home_modify_date_time,5),'-',''),',',''),':','')) =10
                                  then (cast(substring(t_health_sub_home.sub_home_modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_sub_home.sub_home_modify_date_time,5),'-',''),',',''),':','')) || '0000'
                          when length(cast(substring(t_health_sub_home.sub_home_modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_sub_home.sub_home_modify_date_time,5),'-',''),',',''),':','')) = 8
                                  then (cast(substring(t_health_sub_home.sub_home_modify_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_sub_home.sub_home_modify_date_time,5),'-',''),',',''),':','')) || '000000'
                          else ''
                      end
      when length(t_health_sub_home.sub_home_record_date_time) >= 10
               then case when length(cast(substring(t_health_sub_home.sub_home_record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_sub_home.sub_home_record_date_time,5),'-',''),',',''),':','')) = 14
                                  then (cast(substring(t_health_sub_home.sub_home_record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_sub_home.sub_home_record_date_time,5),'-',''),',',''),':',''))
                           when length(cast(substring(t_health_sub_home.sub_home_record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_sub_home.sub_home_record_date_time,5),'-',''),',',''),':','')) =12
                                  then (cast(substring(t_health_sub_home.sub_home_record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_sub_home.sub_home_record_date_time,5),'-',''),',',''),':','')) || '00'
                           when length(cast(substring(t_health_sub_home.sub_home_record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_sub_home.sub_home_record_date_time,5),'-',''),',',''),':','')) =10
                                  then (cast(substring(t_health_sub_home.sub_home_record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_sub_home.sub_home_record_date_time,5),'-',''),',',''),':','')) || '0000'
                           when length(cast(substring(t_health_sub_home.sub_home_record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_sub_home.sub_home_record_date_time,5),'-',''),',',''),':','')) = 8
                                  then (cast(substring(t_health_sub_home.sub_home_record_date_time,1,4) as numeric) - 543
                                                || replace(replace(replace(substring(t_health_sub_home.sub_home_record_date_time,5),'-',''),',',''),':','')) || '000000'
                           else ''
                 end
	   else ''
       end as D_UPDATE --NOT NULL

from  t_health_home  
	INNER JOIN t_health_village  ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
	INNER JOIN ( SELECT t_health_home_id AS home,max(sub_home_record_date_time) AS d  
			from  t_health_sub_home  WHERE sub_home_active = '1' group by home
            ) AS q  ON (t_health_home.t_health_home_id = q.home   )
	INNER join t_health_sub_home  ON (t_health_sub_home.t_health_home_id = t_health_home.t_health_home_id 
            and t_health_sub_home.sub_home_record_date_time = q.d)        
	LEFT JOIN  t_health_family as SUB0 ON (t_health_home.t_health_home_id = SUB0.t_health_home_id   
            AND f_patient_family_status_id = '1')
	left join  t_health_home_water_eradicate  ON (t_health_home_water_eradicate.t_health_sub_home_id = t_health_sub_home.t_health_sub_home_id)         
	left join t_health_home_house_standard  ON 	(t_health_home_house_standard.t_health_sub_home_id = t_health_sub_home.t_health_sub_home_id)          
	left join t_health_home_food_standard  ON (t_health_home_food_standard.t_health_sub_home_id = t_health_sub_home.t_health_sub_home_id)         
	left join t_health_home_bug_control  ON (t_health_home_bug_control.t_health_sub_home_id = t_health_sub_home.t_health_sub_home_id) 
    ,b_site
where t_health_village.village_moo <> '0'
    AND t_health_home.home_active = '1'
