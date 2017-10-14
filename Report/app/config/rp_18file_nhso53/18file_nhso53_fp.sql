--FP_53
SELECT  
	b_site.b_visit_office_id  AS PCUCODE  --NOT NULL
    ,t_health_family.health_family_hn_hcis as PID --NOT NULL
	,case when t_visit.visit_vn is not null and t_visit.visit_vn <> ''
            then t_visit.visit_vn
         else '' end AS SEQ 	--NOT NULL	
	, CASE  WHEN length(t_health_family_planing.update_record_date_time)>9
			THEN   (to_number(substring(t_health_family_planing.update_record_date_time,1,4),'9999')-543)       
				|| substring(t_health_family_planing.update_record_date_time,6,2)         
				|| substring(t_health_family_planing.update_record_date_time,9,2)        
		ELSE ''   END AS DATE_SERV  --NOT NULL
	, t_health_family_planing.f_health_family_planing_method_id AS FPTYPE --NOT NULL 
    , case when b_nhso_map_drug.f_nhso_drug_id  is not null and b_nhso_map_drug.f_nhso_drug_id <>''
        then substr(b_nhso_map_drug.f_nhso_drug_id,1,24)
    else '' end   AS DID
    , t_health_family_planing.health_famlily_planing_supply_qty AS AMOUNT   
	, b_site.b_visit_office_id AS FPPLACE 
    , CASE when length(t_health_family_planing.update_record_date_time)=19
                then (to_number(substring(t_health_family_planing.update_record_date_time,1,4),'9999') - 543)      
                       || substring(t_health_family_planing.update_record_date_time,6,2)      
                        || substring(t_health_family_planing.update_record_date_time,9,2)  
                        || substring(t_health_family_planing.update_record_date_time,12,2)  
                        || substring(t_health_family_planing.update_record_date_time,15,2)  
                        || substring(t_health_family_planing.update_record_date_time,18,2)    
                when length(t_health_family_planing.update_record_date_time)=16
                then (to_number(substring(t_health_family_planing.update_record_date_time,1,4),'9999') - 543)      
                       || substring(t_health_family_planing.update_record_date_time,6,2)      
                        || substring(t_health_family_planing.update_record_date_time,9,2)  
                        || substring(t_health_family_planing.update_record_date_time,12,2)  
                        || substring(t_health_family_planing.update_record_date_time,15,2)  
                        || '00'    
		ELSE ''  END AS D_UPDATE --NOT NULL
		


FROM   t_health_family_planing 
	INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_health_family_planing.t_health_family_id  
	INNER JOIN b_health_family_planing_group  ON t_health_family_planing.b_health_family_planing_group_id = b_health_family_planing_group.b_health_family_planing_group_id        
	LEFT JOIN t_visit ON t_visit.t_visit_id = t_health_family_planing.t_visit_id 
   LEFT JOIN  t_order   ON (t_order.f_order_status_id <> '3'
        AND t_order.f_order_status_id <> '0'
        AND (t_order.f_item_group_id = '1'
        OR t_order.f_item_group_id = '4')
        AND t_visit.t_visit_id = t_order.t_visit_id )
    LEFT JOIN b_item ON b_item.b_item_id = t_order.b_item_id 
    LEFT JOIN b_nhso_map_drug on t_order.b_item_id = b_nhso_map_drug.b_item_id
	,b_site
where   t_health_family_planing.health_family_planing_active = '1'   
    AND ( t_health_family_planing.f_health_family_planing_method_id = '1'        
                OR  t_health_family_planing.f_health_family_planing_method_id = '2'       
                OR  t_health_family_planing.f_health_family_planing_method_id = '3'      
                OR  t_health_family_planing.f_health_family_planing_method_id = '4'       
                OR  t_health_family_planing.f_health_family_planing_method_id = '5'     
                OR  t_health_family_planing.f_health_family_planing_method_id = '6'       
                OR  t_health_family_planing.f_health_family_planing_method_id = '7' )      
    AND  t_health_family.f_sex_id  = '2'          
    AND health_family_active = '1'   
    AND substring(t_health_family_planing.update_record_date_time,1,10) >= ?
	AND substring(t_health_family_planing.update_record_date_time,1,10) <= ?
  --AND substring(t_health_family_planing.update_record_date_time,1,10) >= '2552-12-23'
 -- AND substring(t_health_family_planing.update_record_date_time,1,10) <= '2552-12-23'


