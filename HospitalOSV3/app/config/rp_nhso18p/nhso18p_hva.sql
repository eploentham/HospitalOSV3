SELECT 
b_site.b_visit_office_id as pcucode
,substring(t_nhso_home_visit.t_nhso_home_visit_id,6)  as 	hva_id
,case when (length(t_nhso_home_visit.home_visit_date)>=10)  
        then to_char(to_date(to_number(
        substr(t_nhso_home_visit.home_visit_date,1,4),'9999')-543 || 
        substr(t_nhso_home_visit.home_visit_date,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''  
		    end as 	servicedate
,t_health_village.village_tambon || t_health_home.health_home_moo as 	catm
,t_health_home.health_home_house as 	house_id
,t_health_home.health_home_moo as 	village_no
,t_health_village.village_name as 	village_name
,t_nhso_home.health_home_trog as 	trog
,t_nhso_home.health_home_soi as 	soi
,t_health_home.health_home_road as 	road
,substring(t_health_village.village_tambon,5,2) as 	tambon
,substring(t_health_village.village_ampur,3,2) as 	amphur
,substring(t_health_village.village_changwat,1,2) as 	changwat
,count(t_nhso_home_visit.t_health_family_id) as 	hvtotal
,max(case when (length(t_nhso_home_visit.home_visit_record_date_time)>=10)   
        then to_char(to_date(to_number(
        substr(t_nhso_home_visit.home_visit_record_date_time,1,4),'9999')-543 || 
        substr(t_nhso_home_visit.home_visit_record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''     
		    end) as 	record_date
,max(case when (length(t_nhso_home_visit.home_visit_modify_date_time)>=10)  
        then to_char(to_date(to_number(
        substr(t_nhso_home_visit.home_visit_modify_date_time,1,4),'9999')-543 || 
        substr(t_nhso_home_visit.home_visit_modify_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''    
                    end) as 	modify_date
,case when (length(t_nhso_home_visit.home_visit_date)>=10)  
        then to_char(to_date(to_number(
        substr(t_nhso_home_visit.home_visit_date,1,4),'9999')-543 || 
        substr(t_nhso_home_visit.home_visit_date,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''  
		    end as 	date_serv
,'1' as active
FROM t_nhso_home_visit
    INNER JOIN t_health_home ON t_health_home.t_health_home_id = t_nhso_home_visit.t_health_home_id
    INNER JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
    LEFT JOIN t_nhso_home ON t_nhso_home.t_health_home_id = t_health_home.t_health_home_id
    ,b_site

WHERE
     substr(t_nhso_home_visit.home_visit_date,1,10 )  >= ?
	and  substr(t_nhso_home_visit.home_visit_date,1,10 )  <= ?
GROUP BY
t_health_home.t_health_home_id
,servicedate
,pcucode
,t_health_village.village_changwat
,t_health_home.health_home_moo
,t_health_home.health_home_house
,t_health_village.village_name
,t_health_home.health_home_road
,t_health_village.village_tambon
,t_health_village.village_ampur
,t_nhso_home.health_home_trog
,t_nhso_home.health_home_soi 
,t_health_home.home_active 
,t_nhso_home_visit.t_nhso_home_visit_id
