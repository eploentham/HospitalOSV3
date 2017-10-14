select  
q.epi_id   
,q.t_service_id  

,max(	q.bcg	) as 	bcg
,max(	q.hb1	) as 	hb1
,max(	q.opv1	) as 	opv1
,max(	q.dtp1	) as 	dtp1
,max(	q.hb2	) as 	hb2
,max(	q.opv2	) as 	opv2
,max(	q.dtp2	) as 	dtp2
,max(	q.opv3	) as 	opv3
,max(	q.dtp3	) as 	dtp3
,max(	q.hb3	) as 	hb3
,max(	q.m	) as 	m
,max(	q.mmr	) as 	mmr
,max(	q.opv4	) as 	opv4
,max(	q.dtp4	) as 	dtp4
,max(	q.je1	) as 	je1
,max(	q.je2	) as 	je2
,max(	q.je3	) as 	je3
,max(	q.opv5	) as 	opv5
,max(	q.dtp5	) as 	dtp5
,   q.record_date
,    q.modify_date 
,    q.pcucode  
,q.date_serv as date_serv
,max(	q.dtp_hb2	) as 	dtp_hb2
,max(	q.dtp_hb4	) as 	dtp_hb4
,max(	q.dtp_hb6	) as 	dtp_hb6
,max(	q.dtphbopv2	) as 	dtphbopv2
,max(	q.dtphbopv4	) as 	dtphbopv4
,max(	q.dtphbopv6	) as 	dtphbopv6
,max(	q.mmr2	) as 	mmr2
,q.pid as pid  --‡æ‘Ë¡ø‘≈¥Ïµ“¡ datadict
,'1' as active
from (


SELECT
substring(t_visit.t_visit_id,4)  as epi_id
,b_site.b_visit_office_id ||  t_visit.visit_vn  as t_service_id

,t_nhso_epi.epi_group as 	groups
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
, case when length(t_health_epi.record_date_time)>=10 
        then to_char(to_date(to_number(
        substr(t_health_epi.record_date_time,1,4),'9999')-543 || 
        substr(t_health_epi.record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''   
		 end  as 	record_date
, case when length(t_health_epi.modify_date_time)>=10
        then to_char(to_date(to_number(
        substr(t_health_epi.modify_date_time,1,4),'9999')-543 || 
        substr(t_health_epi.modify_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''   
                end as 	modify_date 
,substring(t_health_epi_detail.t_health_epi_detail_id,4,5)  as  pcucode
, case when length(t_visit.visit_begin_visit_time)>=10 
        then to_char(to_date(to_number(
        substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
        substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''      
		 end  as date_serv
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '20') then '1'
    else '0' end as 	dtp_hb2
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '21') then '1'
    else '0' end as 	dtp_hb4
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '22') then '1'
    else '0' end as 	dtp_hb6
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '23') then '1'
    else '0' end as 	dtphbopv2
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '24') then '1'
    else '0' end as 	dtphbopv4
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '25') then '1'
    else '0' end as 	dtphbopv6
,case when(b_nhso_map_epi.f_nhso_epi_group_id = '26') then '1'
    else '0' end as 	mmr2
    ,t_patient.patient_pid as pid --‡æ‘Ë¡ø‘≈¥Ïµ“¡ datadict


from t_health_epi
    inner join t_visit ON t_health_epi.t_visit_id = t_visit.t_visit_id 
    inner join t_patient ON t_health_epi.t_patient_id = t_patient.t_patient_id
    LEFT join t_health_epi_detail ON t_health_epi_detail.t_health_epi_id = t_health_epi.t_health_epi_id
    LEFT join t_nhso_epi ON t_health_epi.t_health_epi_id = t_nhso_epi.t_health_epi_id
    LEFT join b_nhso_map_epi ON b_nhso_map_epi.b_health_epi_group_id = t_health_epi_detail.b_health_epi_set_id
,b_site

where t_visit.f_visit_status_id <> '4'
    and health_epi_active <> '0'
    and t_health_epi_detail.health_epi_detail_active <> '0'
  AND  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
	AND  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?
) as q 
group by 
q.epi_id 
,q.t_service_id 
,q.groups
, q.record_date  
, q.modify_date
, q.pcucode 
,q.date_serv

,q.pid
