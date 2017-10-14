SELECT  distinct 
	  b_site.b_visit_office_id || substring(t_health_family_planing.t_health_family_planing_id,9) as FP_ID
    , b_site.b_visit_office_id  AS PCUCODE  
	, case when  (t_health_family.patient_pid IS NULL or t_health_family.patient_pid = '')
                then ''
                else t_health_family.patient_pid
       end  as CID
        ,t_visit.visit_vn  AS SEQ 
	, CASE WHEN length(t_visit.visit_begin_visit_time)>=10
            THEN(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)        
                    || substring(t_visit.visit_begin_visit_time,6,2)        
                    || substring(t_visit.visit_begin_visit_time,9,2)
            ELSE '' END AS DATE_SERV 
	, CASE  WHEN (t_health_family_planing.f_health_family_planing_method_id = '0'        
			OR   t_health_family_planing.f_health_family_planing_method_id = '1'        
			OR   t_health_family_planing.f_health_family_planing_method_id = '2'        
			OR   t_health_family_planing.f_health_family_planing_method_id = '3'       
			OR   t_health_family_planing.f_health_family_planing_method_id = '4'       
			OR   t_health_family_planing.f_health_family_planing_method_id = '5'       
			OR   t_health_family_planing.f_health_family_planing_method_id = '6'       
			OR   t_health_family_planing.f_health_family_planing_method_id = '7' )       
			THEN t_health_family_planing.f_health_family_planing_method_id        
		WHEN (t_health_family_planing.f_health_family_planing_method_id = '8'        
			OR t_health_family_planing.f_health_family_planing_method_id ='9')         
			THEN '8' 
		ELSE ''  END AS FPTYPE 
	, CASE WHEN (b_nhso_map_drug.f_nhso_drug_id is not null 
                            and length(b_nhso_map_drug.f_nhso_drug_id)>23)
            THEN b_nhso_map_drug.f_nhso_drug_id
        ELSE '' END AS DID 
	, case when t_health_family_planing.health_famlily_planing_supply_qty is not null 
                        and trim(t_health_family_planing.health_famlily_planing_supply_qty) <> ''
                then cast(cast(t_health_family_planing.health_famlily_planing_supply_qty as numeric) as varchar)
                else '' 
      end AS AMOUNT 
	, b_site.b_visit_office_id AS FPPLACE 
    , CASE WHEN length(t_health_family_planing.record_date_time)>=10
            THEN(to_number(substring(t_health_family_planing.record_date_time,1,4),'9999')-543)        
                    || substring(t_health_family_planing.record_date_time,6,2)        
                    || substring(t_health_family_planing.record_date_time,9,2)
            ELSE '' END  as NOTEDATE
    , t_health_family_planing.health_family_planing_active  as ACTIVE
    , b_site.b_visit_office_id || substring(t_visit.t_visit_id,9) as SERVICE_ID
FROM   t_health_family_planing 
    INNER JOIN t_visit ON t_visit.t_visit_id = t_health_family_planing.t_visit_id 
    INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id 
	INNER JOIN t_health_family ON (t_health_family.t_health_family_id = t_patient.t_health_family_id  
            AND t_health_family.f_sex_id  = '2'  AND t_health_family.health_family_active = '1')
    LEFT JOIN b_health_family_planing_group  
            ON t_health_family_planing.b_health_family_planing_group_id = b_health_family_planing_group.b_health_family_planing_group_id        
    LEFT JOIN t_order ON t_order.t_visit_id = t_visit.t_visit_id and t_order.f_order_status_id <> '3'
                            and t_order.b_item_id in (select b_item_id from b_health_family_planing_item 
                                                                    where b_health_family_planing_group_id = b_health_family_planing_group.b_health_family_planing_group_id)
    LEFT JOIN b_item ON t_order.b_item_id = b_item.b_item_id
    LEFT JOIN b_item_subgroup on (b_item.b_item_subgroup_id = b_item_subgroup.b_item_subgroup_id   
                                    and (b_item_subgroup.f_item_group_id = '1' or  b_item_subgroup.f_item_group_id = '4'))
    LEFT JOIN b_nhso_map_drug ON b_nhso_map_drug.b_item_id = b_item.b_item_id
	,b_site
where   
    t_health_family_planing.health_family_planing_active = '1'           
    AND t_visit.f_visit_status_id <> '4'   
    AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
	AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?
