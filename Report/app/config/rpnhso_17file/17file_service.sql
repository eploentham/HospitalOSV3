SELECT distinct 
b_site.b_visit_office_id ||  substr(t_nhso_service_pp.t_nhso_service_pp_id,11)  as T_SERVICE_ID
,t_patient.patient_hn AS HN
,b_site.b_visit_office_id  as PCUCODE
, case when length(t_visit.visit_begin_visit_time)>=10 
        then to_char(to_date(to_number(
        substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
        substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''     
		 end  as 	DATE_SERV
, case when  (t_health_family.patient_pid IS NULL or t_health_family.patient_pid = '')
                then t_patient.patient_pid 
                else t_health_family.patient_pid
       end  as PID
,t_patient.patient_house AS ADDNO
,t_patient.patient_moo AS MOO
,t_nhso_patient.nhso_patient_addreg_village AS MOONAME
,'' AS COMMUNITY
,t_nhso_patient.nhso_patient_soi AS SOI
,t_patient.patient_road AS ROAD
,substring(t_patient.patient_tambon,5,2) AS TAMBON
,substring(t_patient.patient_amphur,3,2) AS AMPHUR
,substring(t_patient.patient_changwat,1,4) AS PROVINCE
,t_patient.patient_phone_number AS PHONE_HOME
,t_patient.patient_phone_number AS PHONE_WORK
,t_nhso_patient.nhso_patient_addreg_mobile AS HAND_PHONE
, case when t_nhso_home.incup is null or t_nhso_home.incup = ''
            then '0'
            else t_nhso_home.incup 
end as RESPONSIBLE
,case when(t_nhso_service_pp.service_new IN ('1','2'))
	then t_nhso_service_pp.service_new
	else ''
	end AS PTTYPE
,'V2' AS STATUS_INSERT
,t_nhso_service_pp.pp_recompen AS RECOMPEN
,t_nhso_service_pp.pp_name AS PP_NAME
        ,t_visit.visit_vn as vn
FROM t_visit
INNER JOIN t_nhso_service_pp ON t_nhso_service_pp.t_visit_id = t_visit.t_visit_id
INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id  
INNER JOIN t_nhso_patient ON t_nhso_patient.t_patient_id = t_patient.t_patient_id
INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id
INNER JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
LEFT JOIN  t_nhso_home ON t_health_home.t_health_home_id = t_nhso_home.t_health_home_id
LEFT JOIN t_nhso_patient_payment ON t_nhso_patient_payment.t_nhso_patient_payment_pid = t_health_family.patient_pid
,b_site
where t_visit.f_visit_status_id <> '4'
and t_nhso_service_pp.pp_active ='1'
and t_patient.patient_active = '1'
    and  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
	and  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?

UNION

SELECT distinct 
b_site.b_visit_office_id ||  substr(t_nhso_service_pp_out.t_nhso_service_pp_out_id,11)  as T_SERVICE_ID
,case when t_patient.patient_hn is not null 
        then t_patient.patient_hn
        else '' 
end AS HN
,b_site.b_visit_office_id  as PCUCODE
, case when length(t_nhso_service_pp_out.service_date)>=10 
        then to_char(to_date(to_number(
        substr(t_nhso_service_pp_out.service_date,1,4),'9999')-543 || 
        substr(t_nhso_service_pp_out.service_date,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''     
		 end  as 	DATE_SERV
, case when  (t_health_family.patient_pid IS NULL or t_health_family.patient_pid = '')
                then t_patient.patient_pid 
                else t_health_family.patient_pid
       end  as PID
,t_patient.patient_house AS ADDNO
,t_patient.patient_moo AS MOO
,t_nhso_patient.nhso_patient_addreg_village AS MOONAME
,'' AS COMMUNITY
,t_nhso_patient.nhso_patient_soi AS SOI
,t_patient.patient_road AS ROAD
,substring(t_patient.patient_tambon,5,2) AS TAMBON
,substring(t_patient.patient_amphur,3,2) AS AMPHUR
,substring(t_patient.patient_changwat,1,4) AS PROVINCE
,t_patient.patient_phone_number AS PHONE_HOME
,t_patient.patient_phone_number AS PHONE_WORK
,t_nhso_patient.nhso_patient_addreg_mobile AS HAND_PHONE
, case when t_nhso_service_pp_out.pp_name = 'HHC' 
            then (case when t_nhso_visit_home.visit_home_respon_area is null or t_nhso_visit_home.visit_home_respon_area = ''
                        then '0'
                        else t_nhso_visit_home.visit_home_respon_area 
                    end)
            else (case when t_nhso_home.incup is null or t_nhso_home.incup = ''
                        then '0'
                        else t_nhso_home.incup 
                    end)
end as RESPONSIBLE
,case when(t_nhso_service_pp_out.service_new IN ('1','2'))
	then t_nhso_service_pp_out.service_new
	else ''
	end AS PTTYPE
,'V2' AS STATUS_INSERT
,t_nhso_service_pp_out.pp_recompen AS RECOMPEN
,t_nhso_service_pp_out.pp_name AS PP_NAME
,'' as vn
FROM t_nhso_service_pp_out 
INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_nhso_service_pp_out.t_health_family_id 
LEFT JOIN t_nhso_visit_home ON t_nhso_service_pp_out.pp_id = t_nhso_visit_home.t_nhso_visit_home_id
LEFT JOIN t_health_home ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
LEFT JOIN  t_nhso_home ON t_health_home.t_health_home_id = t_nhso_home.t_health_home_id
LEFT JOIN t_patient ON t_health_family.t_health_family_id = t_patient.t_health_family_id 
LEFT JOIN t_nhso_patient ON t_nhso_patient.t_patient_id = t_patient.t_patient_id
LEFT JOIN t_nhso_patient_payment ON t_nhso_patient_payment.t_nhso_patient_payment_pid = t_health_family.patient_pid
,b_site
WHERE t_nhso_service_pp_out.pp_active ='1'
    and t_health_family.health_family_active ='1'
    and  substr(t_nhso_service_pp_out.service_date,1,10 )  >= ?
	and  substr(t_nhso_service_pp_out.service_date,1,10 )  <= ?
