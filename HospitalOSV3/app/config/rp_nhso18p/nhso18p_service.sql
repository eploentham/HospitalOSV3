
SELECT distinct 
b_site.b_visit_office_id ||  t_visit.visit_vn  as t_service_id
,t_patient.patient_pid as pid
, case  when length(t_visit.visit_begin_visit_time)>=10
        then to_char(to_date(to_number(
        substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
        substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''
		   end as 	dateservice

,t_nhso_patient_payment_maininscl as maininscl
,t_nhso_patient_payment_purchaseprovince as primaryprovince
,t_nhso_patient_payment_hmain as hmain
,t_nhso_patient_payment_hsub as hsub
,extract(year from age(to_date(visit_begin_visit_time,'yyyy-mm-dd'),to_date(t_patient.patient_birthday,'yyyy-mm-dd'))) as 	age
,case when (t_nhso_patient_payment_paidmodel is not null and trim(t_nhso_patient_payment_paidmodel) <> '')
        then cast(cast(t_nhso_patient_payment_paidmodel as numeric) as varchar)
        else ''
        end as paidmodel
,t_nhso_patient_payment_mastercup_id as 	companyid 
,t_health_family.f_sex_id as 	sex
,b_site.b_visit_office_id as 	service_point
, case when length(t_visit_service.assign_date_time)>=10 
        then to_char(to_date(to_number(
        substr(t_visit_service.assign_date_time,1,4),'9999')-543 || 
        substr(t_visit_service.assign_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''    
        end as 	record_date
,b_site.b_visit_office_id  as pcucode
, case when length(t_visit.visit_begin_visit_time)>=10 
        then to_char(to_date(to_number(
        substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
        substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''     
		 end  as date_serv
,'1' as active
FROM t_visit 
INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id
INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id
LEFT JOIN t_nhso_patient_payment ON t_nhso_patient_payment.t_nhso_patient_payment_pid = t_health_family.patient_pid
LEFT join (select t_visit_id,min(assign_date_time) as assign_date_time from  t_visit_service group by t_visit_id) as t_visit_service
on t_visit_service.t_visit_id = t_visit.t_visit_id
,b_site

where t_visit.f_visit_status_id <> '4'
   and  substr(t_visit.visit_begin_visit_time,1,10 )  >= ?
	and  substr(t_visit.visit_begin_visit_time,1,10 )  <= ?

