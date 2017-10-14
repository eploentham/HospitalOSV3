--ทดสอบภาษาไทย
select PCUCODE  	
    ,PID	
    ,GRAVIDA 	
    ,LMP  	
    ,EDC 	
    ,max(VDRL_RS) as VDRL_RS
    ,max(HB_RS) as HB_RS
    ,max(HIV_RS ) as HIV_RS
    ,max(DATEHCT ) as  	DATEHCT
    ,max(HCT_RS) as HCT_RS
    ,max(THALASS) as THALASS
    ,max(DENTAL) as DENTAL
    ,max(TCARIES  ) as 	TCARIES
    ,max(TARTAR  ) as 	TARTAR
    ,max(GUMINF  ) as 	GUMINF
    ,BDATE  
    ,BRESULT 
    ,BPLACE  
    ,BHOSP  
    ,BTYPE  
    ,BDOCTOR  
    ,LBORN  
    ,SBORN  
,max(PPCARE1) as PPCARE1
,max(PPCARE2  ) as PPCARE2
,max(PPCARE3 ) as PPCARE3
    ,PPRES  
    , max(UPDATE ) as UPDATE

from (SELECT  
	b_site.b_visit_office_id AS PCUCODE  
	, t_health_family.patient_pid AS PID
	, CASE  
                    WHEN (length(sub0.health_postpartum_pregnant_number) = 1)       
                        THEN '0'||sub0.health_postpartum_pregnant_number       
                    ELSE sub0.health_postpartum_pregnant_number END AS GRAVIDA 
	, CASE 
                    WHEN (length(t_health_pregnancy.health_pregnancy_menses_issue_date)>=10)
then to_char(to_date(to_number(
substr(t_health_pregnancy.health_pregnancy_menses_issue_date,1,4),'9999')-543 || 
substr(t_health_pregnancy.health_pregnancy_menses_issue_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END AS LMP  
	, CASE
                    WHEN (length(t_health_pregnancy.health_pregnancy_menses_expire_date)>=10)
then to_char(to_date(to_number(
substr(t_health_pregnancy.health_pregnancy_menses_expire_date,1,4),'9999')-543 || 
substr(t_health_pregnancy.health_pregnancy_menses_expire_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END AS EDC 
	, CASE WHEN t_health_anc.health_anc_vdrl is not null
		THEN t_health_anc.health_anc_vdrl
		ELSE '0'  END AS VDRL_RS 
	, CASE WHEN t_health_anc.health_anc_hb is not null
		THEN t_health_anc.health_anc_hb
		ELSE '0'  END   AS HB_RS
	, CASE WHEN t_health_anc.health_anc_hiv is not null
		THEN t_health_anc.health_anc_hiv
		ELSE '0' END  AS HIV_RS 
	,  CASE WHEN (length(t_health_anc.health_anc_hct_date)>=10)
then to_char(to_date(to_number(
substr(t_health_anc.health_anc_hct_date,1,4),'9999')-543 || 
substr(t_health_anc.health_anc_hct_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
            ELSE ''   END AS DATEHCT  
	, CASE WHEN t_health_anc.health_anc_hct_result  is not null
		THEN t_health_anc.health_anc_hct_result
		ELSE '0' END  AS HCT_RS
	, CASE WHEN t_health_anc.health_anc_thalassemia  is not null
                AND t_health_anc.health_anc_thalassemia <>'2'
		THEN '1'
		ELSE '2' END  AS THALASS
	, CASE WHEN t_health_anc.health_anc_dental_exam is not null
		THEN t_health_anc.health_anc_dental_exam
		ELSE '0' END  as DENTAL
	,CASE 
        WHEN (t_health_anc.health_anc_dental_exam = '1')                  
        THEN t_health_anc.health_anc_dental_caries   
        ELSE ''   END AS TCARIES  
    , CASE 
                    WHEN (t_health_anc.health_anc_dental_exam = '1')                 
                        THEN t_health_anc.health_anc_dental_tartar   
                    ELSE ''   END AS TARTAR  
    , CASE 
                    WHEN (t_health_anc.health_anc_dental_exam = '1')   
                        THEN t_health_anc.health_anc_dental_gum   
                    ELSE ''   END AS GUMINF  
	, CASE  
		WHEN (length(t_health_delivery.health_delivery_born_date)>=10)
then to_char(to_date(to_number(
substr(t_health_delivery.health_delivery_born_date,1,4),'9999')-543 || 
substr(t_health_delivery.health_delivery_born_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
		ELSE ''   END AS BDATE  
	, CASE WHEN b_icd10.icd10_number is not null
		THEN b_icd10.icd10_number
		ELSE '0' END AS BRESULT 
	,CASE WHEN  t_health_delivery.f_health_postpartum_birth_place_id is not null
		THEN t_health_delivery.f_health_postpartum_birth_place_id
		ELSE '0' END AS BPLACE  
	, CASE WHEN t_health_delivery.b_visit_office_birth_place is not null
		THEN t_health_delivery.b_visit_office_birth_place
		ELSE '0' END AS BHOSP  
	, CASE  
		WHEN (t_health_delivery.f_health_birth_method_id = '1'         
			OR  t_health_delivery.f_health_birth_method_id = '2'        
			OR  t_health_delivery.f_health_birth_method_id = '3'       
			OR  t_health_delivery.f_health_birth_method_id = '4'       
			OR  t_health_delivery.f_health_birth_method_id = '5'        
			OR  t_health_delivery.f_health_birth_method_id = '6')       
			THEN t_health_delivery.f_health_birth_method_id   
		ELSE '' END AS BTYPE  
	, CASE WHEN t_health_delivery.f_health_pregnancy_birth_doctor_type_id  is not null
		THEN t_health_delivery.f_health_pregnancy_birth_doctor_type_id
		ELSE '0' END AS BDOCTOR  
	, CASE WHEN t_health_delivery.health_delivery_parity  is not null
		THEN t_health_delivery.health_delivery_parity
		ELSE '0' END AS LBORN  
	, CASE WHEN t_health_delivery.health_delivery_stillborn  is not null
		THEN t_health_delivery.health_delivery_stillborn
		ELSE '0' END AS SBORN  
	, CASE  
		WHEN SUB0.health_postpartum_visit = '1' AND  length(t_visit.visit_begin_visit_time)>=10
then to_char(to_date(to_number(
substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd') 
		ELSE ''   END AS PPCARE1
	, CASE  
		WHEN SUB0.health_postpartum_visit = '2' AND length(t_visit.visit_begin_visit_time)>=10
then to_char(to_date(to_number(
substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd') 
		ELSE ''   END AS PPCARE2  
	, CASE  
		WHEN SUB0.health_postpartum_visit = '3' AND  length(t_visit.visit_begin_visit_time)>=10
then to_char(to_date(to_number(
substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd') 
		ELSE ''   END AS PPCARE3 
	, CASE  
		WHEN (t_health_delivery.f_health_postpartum_status_result_id = '1'       
			OR  t_health_delivery.f_health_postpartum_status_result_id = '2')       
			THEN t_health_delivery.f_health_postpartum_status_result_id   
		ELSE ''   END AS PPRES  
	,CASE  WHEN (length(SUB0.update_date_time)>=10)
then to_char(to_date(to_number(
substr(SUB0.update_date_time,1,4),'9999')-543 || 
substr(SUB0.update_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd') 
		ELSE ''   END AS  UPDATE 	


FROM  t_health_postpartum  AS SUB0 
    INNER JOIN t_visit on (t_visit.t_visit_id = SUB0.t_visit_id
        AND t_visit.f_visit_status_id <> '4')
	INNER JOIN t_health_family ON (t_health_family.t_health_family_id = SUB0.t_health_family_id 
        AND t_health_family.health_family_active = '1')
    LEFT JOIN t_health_delivery  ON (t_health_delivery.gravida_number = SUB0.health_postpartum_pregnant_number
        AND t_health_delivery.t_health_family_id = SUB0.t_health_family_id
        AND t_health_delivery.health_delivery_active = '1')
	LEFT JOIN t_health_pregnancy    ON (SUB0.health_postpartum_pregnant_number = t_health_pregnancy.health_pregnancy_gravida_number
        AND t_health_pregnancy.t_health_family_id = SUB0.t_health_family_id
        AND t_health_pregnancy.health_pregnancy_active = '1')
    LEFT JOIN t_health_anc ON (t_health_anc.t_health_pregnancy_id = t_health_pregnancy.t_health_pregnancy_id
        AND t_health_anc.health_anc_active = '1')
	LEFT JOIN b_icd10   ON (t_health_delivery.b_icd10_id = b_icd10.b_icd10_id )
	,b_site 
			
WHERE  SUB0.health_postpartum_active = '1'
    AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
    AND substring(t_visit.visit_begin_visit_time,1,10) <= ?
) as query 

group by
    PCUCODE  	
    ,PID	
    ,GRAVIDA 	
    ,LMP  	
    ,EDC 	
    ,BDATE  
    ,BRESULT 
    ,BPLACE  
    ,BHOSP  
    ,BTYPE  
    ,BDOCTOR  
    ,LBORN  
    ,SBORN  
    ,PPRES  
