SELECT distinct 
b_site.b_visit_office_id || substring(t_health_family_planing.t_health_family_planing_id,9) as  fp_id
,b_site.b_visit_office_id || substring(t_nhso_service_pp.t_nhso_service_pp_id,11)  as t_service_id
,case when(t_health_family_planing.f_health_family_planing_method_id = '1') 
     then '1'
     else '0'
     end as drug
,case when (t_health_family_planing.f_health_family_planing_method_id = '1' and trim(t_health_family_planing.health_famlily_planing_supply_qty) <> '') 
    then t_health_family_planing.health_famlily_planing_supply_qty 
    else '0' 
    end  as drug_dispend
,case when (t_health_family_planing.f_health_family_planing_method_id = '2') 
    then '1'
    else '0' 
    end as 	drug_ijt
,case when (t_health_family_planing.f_health_family_planing_method_id = '7') 
   then '1'
    else '0' 
    end as 	hemp_women
,case when (t_health_family_planing.f_health_family_planing_method_id = '6') 
    then '1'
    else '0'
    end as 	hemp_men
,case when (t_health_family_planing.f_health_family_planing_method_id = '3')
    then '1'
    else '0' 
    end as 	hoop
,case when (t_health_family_planing.f_health_family_planing_method_id = '4')
   then '1'
    else '0' 
    end as 	drug_burty
,case when (t_health_family_planing.f_health_family_planing_method_id = '5')
    then '1'
    else '0' 
    end as 	condom
,case when (t_health_family_planing.f_health_family_planing_method_id = '5' and trim(t_health_family_planing.health_famlily_planing_supply_qty) <> '') 
    then t_health_family_planing.health_famlily_planing_supply_qty 
    else '0' 
    end as 	condom_dispend
 ,t_nhso_service_pp.pp_totalpay as totalpay
        ,t_visit.visit_vn as vn
FROM t_health_family_planing 
    inner join t_nhso_service_pp on t_health_family_planing.t_health_family_planing_id = t_nhso_service_pp.pp_id
    inner join t_visit on t_health_family_planing.t_visit_id = t_visit.t_visit_id  
    inner join t_patient on t_visit.t_patient_id = t_patient.t_patient_id 
    ,b_site
WHERE t_visit.f_visit_status_id <> '4'
    and health_family_planing_active = '1'
    and t_nhso_service_pp.pp_active = '1'
    and t_nhso_service_pp.pp_name = 'FP'
    and  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
	and  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?
