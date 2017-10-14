select 
    b_site.b_visit_office_id || substring(SUB1.t_health_pp_care_id,9)  as PP_ID
    ,b_site.b_visit_office_id || substring(t_nhso_service_pp.t_nhso_service_pp_id,11)  as  T_SERVICE_ID
    ,case when (length(t_health_delivery.health_delivery_born_date)>=10)
            then to_char(to_date(to_number(substr(t_health_delivery.health_delivery_born_date,1,4),'9999')-543 
                                || substr(t_health_delivery.health_delivery_born_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
            else ''   
     end as SON_DATE
    ,case when(t_nhso_pp.nhso_pp_cordblood in ('0','1')) 
        then t_nhso_pp.nhso_pp_cordblood 
        else '0'
        end AS CORDB
    ,case when (t_nhso_pp.nhso_pp_perforateblood  in ('0','1'))  
        then t_nhso_pp.nhso_pp_perforateblood
        else '0'
        end AS BABYFOOT
    ,case when(t_nhso_pp.nhso_pp_serum in ('0','1')) 
        then t_nhso_pp.nhso_pp_serum
        else '0'
        end AS SERUM
    ,'0' AS FOROWUP
    ,'' AS DATEFOROW
    ,t_nhso_service_pp.pp_totalpay as TOTALPAY
        ,'' as vn
from t_health_pp_care as SUB1
    inner join t_patient on SUB1.t_patient_id = t_patient.t_patient_id
    inner join t_health_family  as mother  on (mother.t_health_family_id = t_patient.t_health_family_id)
    inner join t_health_family on t_health_family.t_health_family_id = SUB1.t_health_family_id
    inner join t_health_pp on (t_health_pp.t_health_family_id= SUB1.t_health_family_id
        and t_health_pp.pp_active  = '1')
	inner join t_nhso_pp on t_health_pp.t_health_pp_id = t_nhso_pp.t_health_pp_id
	inner join t_nhso_service_pp on t_nhso_pp.t_nhso_pp_id = t_nhso_service_pp.pp_id
    left join t_health_delivery on (mother.t_health_family_id = t_health_delivery.t_health_family_id
        and t_health_pp.pp_gravida = t_health_delivery.gravida_number
        and t_health_delivery.health_delivery_active = '1')
    ,b_site
where  SUB1.pp_care_active  = '1'  
	and t_nhso_service_pp.pp_active = '1'
    and t_health_family.health_family_active = '1'
        and substring(SUB1.pp_care_record_date_time,1,10) >= ?
        and substring(SUB1.pp_care_record_date_time,1,10) <= ?
