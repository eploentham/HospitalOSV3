select 
substring(t_health_visit_home.t_health_visit_home_id,4) as hvs_id
,t_patient.patient_pid as pid
,t_nhso_visit_home.visit_home_highblood_pressure as highblood_pressure  --à¾ÔèÁ¿ÔÅ´ìµÒÁ datadict
,t_nhso_visit_home.visit_home_diabetes as diabetes  --à¾ÔèÁ¿ÔÅ´ìµÒÁ datadict
,t_nhso_visit_home.visit_home_heart_diabetes as heart_diabetes  --à¾ÔèÁ¿ÔÅ´ìµÒÁ datadict
,t_nhso_visit_home.visit_home_heart_storke as storke  --à¾ÔèÁ¿ÔÅ´ìµÒÁ datadict
,t_nhso_visit_home.visit_home_asthma as asthma  --à¾ÔèÁ¿ÔÅ´ìµÒÁ datadict
,t_nhso_visit_home.visit_home_copd as copd  --à¾ÔèÁ¿ÔÅ´ìµÒÁ datadict
,t_nhso_visit_home.visit_home_tuberculosis as tuberculosis --à¾ÔèÁ¿ÔÅ´ìµÒÁ datadict
,t_nhso_visit_home.visit_home_aids as aids --à¾ÔèÁ¿ÔÅ´ìµÒÁ datadict
,t_nhso_visit_home.visit_home_child_and_mom as child_and_mom --à¾ÔèÁ¿ÔÅ´ìµÒÁ datadict
,t_nhso_visit_home.visit_home_patien_final as patient_final  --à¾ÔèÁ¿ÔÅ´ìµÒÁ datadict
,t_nhso_visit_home.visit_home_other  as otherdisease   --à¾ÔèÁ¿ÔÅ´ìµÒÁ datadict
,t_nhso_visit_home.f_visit_home_check_health_id as disease
,t_nhso_visit_home.visit_home_disease_detail as disease_detail
,t_nhso_visit_home.visit_home_doctor as 	doctor_service
,t_nhso_visit_home.visit_home_nurse as 	nurse_service
,t_nhso_visit_home.visit_home_physio_officer as 	physio_service
,t_nhso_visit_home.visit_home_health_suggest as suggest
,t_nhso_visit_home.visit_home_physio as physio
,t_nhso_visit_home.visit_home_wound as wound
,t_nhso_visit_home.visit_home_ng as ng
,t_nhso_visit_home.visit_home_urine_tube as urine_tube
,t_nhso_visit_home.visit_home_neck_pierce as neck_pierce
,t_nhso_visit_home.visit_home_other as other_activity
,t_nhso_visit_home.visit_home_other_detail as other_detail
,case when (t_health_home.health_home_house = '0' 
      and t_health_home.health_home_moo = '0')      then '0'
      else '1'      end as 	responsible
,t_health_home.health_home_house as 	 house_id
,t_health_home.health_home_moo as 	village_no
,t_health_village.village_name as 	village_name
,t_nhso_home.health_home_trog as 	trog
,t_nhso_home.health_home_soi as 	soi
,t_health_home.health_home_road as 	road
,substring(t_health_village.village_tambon,5,2) as 	tambon
,substring(t_health_village.village_ampur,3,2) as 	amphur
,substring(t_health_village.village_changwat,1,2) as 	changwat
,case when (length(t_health_visit_home.visit_home_record_time)>=10)
        then to_char(to_date(to_number(
        substr(t_health_visit_home.visit_home_record_time,1,4),'9999')-543 || 
        substr(t_health_visit_home.visit_home_record_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else ''   
		    end as 	record_date
,case when (length(t_health_visit_home.visit_home_modify_time)>=10)
        then to_char(to_date(to_number(
        substr(t_health_visit_home.visit_home_modify_time,1,4),'9999')-543 || 
        substr(t_health_visit_home.visit_home_modify_time,5,6),'yyyy-mm-dd'),'yyyymmdd')  else '' 
	end as 	modify_date
,case when (length(t_health_visit_home.visit_home_date)>=10)
        then to_char(to_date(to_number(
        substr(t_health_visit_home.visit_home_date,1,4),'9999')-543 || 
        substr(t_health_visit_home.visit_home_date,5,6),'yyyy-mm-dd'),'yyyymmdd')  else '' 
	end as 	date_serv --à¾ÔèÁ¿ÔÅ´ìµÒÁ datadict

,b_site.b_visit_office_id as pcucode
,case when (t_nhso_visit_home.visit_home_type_patient = '1')      then '1'
      else '2'      end as patient_type
,t_nhso_visit_home.visit_home_get_from as receive_channel
,t_nhso_visit_home.visit_home_get_from_other_detail as receive_channel_detail
,t_nhso_visit_home.visit_home_get_from_hos_name as hcode
,t_nhso_visit_home.visit_home_result_follow_visit as result_visit
,t_nhso_visit_home.visit_home_classify_patient_group as patient_group
,'1' as active
 from t_health_visit_home
INNER JOIN t_health_family ON t_health_visit_home.t_health_family_id = t_health_family.t_health_family_id
INNER JOIN t_health_home ON t_health_home.t_health_home_id = t_health_family.t_health_home_id
INNER JOIN t_health_village ON t_health_home.t_health_village_id = t_health_village.t_health_village_id
INNER JOIN t_nhso_visit_home ON t_health_visit_home.t_health_visit_home_id = t_nhso_visit_home.t_health_visit_home_id
LEFT JOIN t_nhso_home ON t_nhso_home.t_health_home_id = t_health_home.t_health_home_id
INNER JOIN t_patient ON t_patient.t_health_family_id = t_health_family.t_health_family_id and t_patient.patient_active = '1'
,b_site

WHERE 
visit_home_active = '1'     
AND substr(t_health_visit_home.visit_home_date,1,10 )  >= ?
	AND  substr(t_health_visit_home.visit_home_date,1,10 )  <= ?
