--EPI_53
SELECT  
	b_site.b_visit_office_id AS PCUCODE  --NOT NULL
    ,t_health_family.health_family_hn_hcis as PID --NOT NULL
	,case when t_visit.visit_vn is not null 
                then t_visit.visit_vn
                else ''
      end  AS SEQ 		
	, (to_number(substring(t_health_epi.modify_date_time,1,5),'9999')-543)        
		|| substring(t_health_epi.modify_date_time,6,2)        
		|| substring(t_health_epi.modify_date_time,9,2) AS DATE_SERV --NOT NULL
	, b_health_epi_group.health_epi_group_description_particular AS VCCTYPE  --NOT NULL
	, b_site.b_visit_office_id AS VCCPLACE 
  ,CASE when length(t_health_epi.modify_date_time) = 19
                then (to_number(substring(t_health_epi.modify_date_time,1,4),'9999') - 543)      
                       || substring(t_health_epi.modify_date_time,6,2)      
                        || substring(t_health_epi.modify_date_time,9,2)  
                        || substring(t_health_epi.modify_date_time,12,2)  
                        || substring(t_health_epi.modify_date_time,15,2)  
                        || substring(t_health_epi.modify_date_time,18,2)  
             when length(t_health_epi.modify_date_time) = 16
                then (to_number(substring(t_health_epi.modify_date_time,1,4),'9999') - 543)      
                       || substring(t_health_epi.modify_date_time,6,2)      
                        || substring(t_health_epi.modify_date_time,9,2)  
                        || substring(t_health_epi.modify_date_time,12,2)  
                        || substring(t_health_epi.modify_date_time,15,2)  
                        || '00'  
            when length(t_health_epi.modify_date_time) = 11
                then (to_number(substring(t_health_epi.modify_date_time,1,4),'9999') - 543)      
                       || substring(t_health_epi.modify_date_time,6,2)      
                       || substring(t_health_epi.modify_date_time,9,2)  
                       || '000000'  
		ELSE ''  END AS D_UPDATE  

FROM   t_health_epi 
	INNER JOIN t_health_epi_detail  ON (t_health_epi.t_health_epi_id = t_health_epi_detail.t_health_epi_id AND health_epi_detail_active = '1')
	INNER JOIN b_health_epi_group  ON t_health_epi_detail.b_health_epi_set_id = b_health_epi_group.b_health_epi_group_id        
	INNER JOIN t_health_family  ON t_health_epi_detail.t_health_family_id = t_health_family.t_health_family_id        
	LEFT JOIN t_visit  ON (t_visit.t_visit_id = t_health_epi.t_visit_id AND t_visit.f_visit_status_id <> '4') 
    ,b_site
WHERE  	t_health_epi.health_epi_active = '1'         
    AND substring(t_health_epi.modify_date_time,1,10) >= ?
    AND substring(t_health_epi.modify_date_time,1,10) <= ?

UNION

select 
b_site.b_visit_office_id as PCUCODE
,t_health_family.health_family_hn_hcis as PID
,'' as SEQ 
, (to_number(substring(t_health_epi_outsite.epi_outsite_date,1,5),'9999')-543)        
		|| substring(t_health_epi_outsite.epi_outsite_date,6,2)        
		|| substring(t_health_epi_outsite.epi_outsite_date,9,2) AS DATE_SERV
, b_health_epi_group.health_epi_group_description_particular AS VCCTYPE
, t_health_epi_outsite.b_epi_outsite_office_id AS VCCPLACE 
, CASE when length(t_health_epi_outsite.modify_date_time) = 19
                then (to_number(substring(t_health_epi_outsite.modify_date_time,1,4),'9999') - 543)      
                       || substring(t_health_epi_outsite.modify_date_time,6,2)      
                        || substring(t_health_epi_outsite.modify_date_time,9,2)  
                        || substring(t_health_epi_outsite.modify_date_time,12,2)  
                        || substring(t_health_epi_outsite.modify_date_time,15,2)  
                        || substring(t_health_epi_outsite.modify_date_time,18,2)  
             when length(t_health_epi_outsite.modify_date_time) = 16
                then (to_number(substring(t_health_epi_outsite.modify_date_time,1,4),'9999') - 543)      
                       || substring(t_health_epi_outsite.modify_date_time,6,2)      
                        || substring(t_health_epi_outsite.modify_date_time,9,2)  
                        || substring(t_health_epi_outsite.modify_date_time,12,2)  
                        || substring(t_health_epi_outsite.modify_date_time,15,2)  
                        || '00'  
            when length(t_health_epi_outsite.modify_date_time) = 11
                then (to_number(substring(t_health_epi_outsite.modify_date_time,1,4),'9999') - 543)      
                       || substring(t_health_epi_outsite.modify_date_time,6,2)      
                       || substring(t_health_epi_outsite.modify_date_time,9,2)  
                       || '000000'  
		ELSE ''  END AS D_UPDATE  
from t_health_epi_outsite
	inner join b_health_epi_group  on t_health_epi_outsite.b_health_epi_group_id = b_health_epi_group.b_health_epi_group_id        
	inner join t_health_family  on t_health_epi_outsite.t_health_family_id = t_health_family.t_health_family_id    
    ,b_site
where t_health_epi_outsite.epi_outsite_active = '1'
    and substring(t_health_epi_outsite.epi_outsite_date,1,10) >= ?
    and substring(t_health_epi_outsite.epi_outsite_date,1,10) <= ?
