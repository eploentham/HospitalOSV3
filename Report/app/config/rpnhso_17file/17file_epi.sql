select  
    q.epi_id   
    ,q.t_service_id   
    ,max(	bcg	) as 	bcg
    ,max(	hb1	) as 	hb1
    ,max(	opv1	) as 	opv1
    ,max(	dtp1	) as 	dtp1
    ,max(	hb2	) as 	hb2
    ,max(	opv2	) as 	opv2
    ,max(	dtp2	) as 	dtp2
    ,max(	opv3	) as 	opv3
    ,max(	dtp3	) as 	dtp3
    ,max(	hb3	) as 	hb3
    ,max(	m	) as 	m
    ,max(	mmr	) as 	mmr
    ,max(	opv4	) as 	opv4
    ,max(	dtp4	) as 	dtp4
    ,max(	je1	) as 	je1
    ,max(	je2	) as 	je2
    ,max(	je3	) as 	je3
    ,max(	opv5	) as 	opv5
    ,max(	dtp5	) as 	dtp5
    ,max(	dtphb2	) as 	dtphb2
    ,max(	dtphb4	) as 	dtphb4
    ,max(	dtphb6	) as 	dtphb6
    ,max(	dtphbopv2	) as 	dtphbopv2
    ,max(	dtphbopv4	) as 	dtphbopv4
    ,max(	dtphbopv6	) as 	dtphbopv6
    ,max(	mmr2	) as 	mmr2
    ,q.totalpay
    ,q.vn as vn
from (
SELECT
b_site.b_visit_office_id 
        		|| substring(t_visit.visit_begin_visit_time,6,2)       
                || substring((to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543),3,2)
|| substring(t_health_epi.t_health_epi_id,9) as epi_id
,b_site.b_visit_office_id 
        		|| substring(t_visit.visit_begin_visit_time,6,2)       
                || substring((to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543),3,2)
|| substring(t_nhso_service_pp.t_nhso_service_pp_id,11)  as t_service_id
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '01') then '1'
    else '0' end as 	bcg
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '02') then '1'
    else '0' end as 	hb1
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '03') then '1'
    else '0' end as 	opv1
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '04') then '1'
    else '0' end as 	dtp1
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '05') then '1'
    else '0' end as 	hb2
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '06') then '1'
    else '0' end as 	opv2
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '07') then '1'
    else '0' end as 	dtp2
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '08') then '1'
    else '0' end as 	opv3
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '09') then '1'
    else '0' end as 	dtp3
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '10') then '1'
    else '0' end as 	hb3
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '11') then '1'
    else '0' end as 	m
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '12') then '1'
    else '0' end as 	mmr
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '13') then '1'
    else '0' end as 	opv4
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '14') then '1'
    else '0' end as 	dtp4
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '15') then '1'
    else '0' end as 	je1
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '16') then '1'
    else '0' end as 	je2
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '17') then '1'
    else '0' end as 	je3
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '18') then '1'
    else '0' end as 	opv5
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '19') then '1'
    else '0' end as 	dtp5
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '20') then '1'
    else '0' end as 	dtphb2
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '21') then '1'
    else '0' end as 	dtphb4
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '22') then '1'
    else '0' end as 	dtphb6
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '23') then '1'
    else '0' end as 	dtphbopv2
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '24') then '1'
    else '0' end as 	dtphbopv4
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '25') then '1'
    else '0' end as 	dtphbopv6
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '26') then '1'
    else '0' end as 	mmr2
,t_nhso_service_pp.pp_totalpay   as totalpay
,t_visit.visit_vn  as vn
from t_health_epi
    inner join t_visit ON t_health_epi.t_visit_id = t_visit.t_visit_id 
    inner join t_patient on t_visit.t_patient_id = t_patient.t_patient_id 
    inner join t_nhso_epi ON t_health_epi.t_health_epi_id = t_nhso_epi.t_health_epi_id
    inner join t_nhso_service_pp on t_nhso_epi.t_nhso_epi_id = t_nhso_service_pp.pp_id
    left join t_health_epi_detail ON t_health_epi_detail.t_health_epi_id = t_health_epi.t_health_epi_id
    left join b_nhso_map_epi ON b_nhso_map_epi.b_health_epi_group_id = t_health_epi_detail.b_health_epi_set_id
    ,b_site
where t_visit.f_visit_status_id <> '4'
    and health_epi_active = '1'
    and t_nhso_service_pp.pp_active = '1'
    and t_health_epi_detail.health_epi_detail_active = '1'
    and cast(t_visit.visit_patient_age as numeric) < 6
    and  substr(t_visit.visit_begin_visit_time,1,10 ) >= ?
         and  substr(t_visit.visit_begin_visit_time,1,10 ) <= ?
) as q 
group by 
     epi_id 
,    t_service_id
,   q.totalpay
,   q.vn

