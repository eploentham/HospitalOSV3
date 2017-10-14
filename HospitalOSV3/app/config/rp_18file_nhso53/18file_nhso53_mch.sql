--MCH_53
SELECT  
	b_site.b_visit_office_id AS PCUCODE  --NOT NULL	
    ,t_health_family.health_family_hn_hcis as PID --NOT NULL	
	,cast(SUB0.health_postpartum_pregnant_number as numeric) AS GRAVIDA --NOT NULL	
	, CASE WHEN (length(t_health_pregnancy.health_pregnancy_menses_issue_date)>=10)
        then to_char(to_date(to_number(
        substr(t_health_pregnancy.health_pregnancy_menses_issue_date,1,4),'9999')-543 || 
        substr(t_health_pregnancy.health_pregnancy_menses_issue_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE t_health_pregnancy.health_pregnancy_menses_issue_date   END AS LMP     
	, CASE WHEN (length(t_health_pregnancy.health_pregnancy_menses_expire_date)>=10)
        then to_char(to_date(to_number(
        substr(t_health_pregnancy.health_pregnancy_menses_expire_date,1,4),'9999')-543 || 
        substr(t_health_pregnancy.health_pregnancy_menses_expire_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE t_health_pregnancy.health_pregnancy_menses_expire_date  END AS EDC 
   
	, min(CASE WHEN (t_health_anc.health_anc_vdrl ='0'  
                        OR t_health_anc.health_anc_vdrl =''
                        OR t_health_anc.health_anc_vdrl is null) then '8' --����Ǩ
               WHEN t_health_anc.health_anc_vdrl ='3' then '9' --�ͼŵ�Ǩ
               else t_health_anc.health_anc_vdrl END) AS VDRL_RS 
	, min(CASE WHEN (t_health_anc.health_anc_hb ='0' 
                OR t_health_anc.health_anc_hb =''
                 OR t_health_anc.health_anc_hb is null) then '8'
            WHEN t_health_anc.health_anc_hb ='3' then '9' --�ͼŵ�Ǩ                  
               else t_health_anc.health_anc_hb END) AS HB_RS
	, min(CASE WHEN (t_health_anc.health_anc_hiv ='0'
                 OR t_health_anc.health_anc_hiv =''
                 OR t_health_anc.health_anc_hiv is null) then '8'
                WHEN t_health_anc.health_anc_hiv ='3' then '9' --�ͼŵ�Ǩ     
               else t_health_anc.health_anc_hiv END) AS  HIV_RS 
	,  min(CASE WHEN (length(t_health_anc.health_anc_hct_date)>=10  and t_health_anc.health_anc_hct <> '0' )
            then to_char(to_date(to_number(
            substr(t_health_anc.health_anc_hct_date,1,4),'9999')-543 || 
            substr(t_health_anc.health_anc_hct_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                        ELSE ''   END) AS DATEHCT  
	, min( CASE WHEN t_health_anc.health_anc_hct_result  is not null
            THEN t_health_anc.health_anc_hct_result    ELSE '' END)  AS HCT_RS
	, min(CASE WHEN (t_health_anc.health_anc_thalassemia ='0' 
                OR t_health_anc.health_anc_thalassemia =''
                OR t_health_anc.health_anc_thalassemia is null) then '8'
                WHEN t_health_anc.health_anc_thalassemia ='3' then '9' --�ͼŵ�Ǩ     
               else t_health_anc.health_anc_thalassemia END) AS THALASS
	, min(CASE WHEN (t_health_anc.health_anc_dental_exam=''
                OR t_health_anc.health_anc_dental_exam IS NULL
                OR t_health_anc.health_anc_dental_exam ='0') THEN '0' --������Ǩ
                ELSE '1' END) AS DENTAL
	, min(CASE WHEN (t_health_anc.health_anc_dental_caries = ''
                OR t_health_anc.health_anc_dental_caries IS NULL) THEN '0'
                ELSE t_health_anc.health_anc_dental_caries END) AS TCARIES  
   , min(CASE WHEN (t_health_anc.health_anc_dental_tartar='0'
                OR t_health_anc.health_anc_dental_tartar='1')
                THEN t_health_anc.health_anc_dental_tartar  ELSE '8' END) AS TARTAR  
    , min(CASE WHEN (t_health_anc.health_anc_dental_gum ='0'
                OR t_health_anc.health_anc_dental_gum ='1')
                THEN t_health_anc.health_anc_dental_gum  ELSE '8' END) AS GUMINF  
	, CASE WHEN (length(t_health_delivery.health_delivery_born_date)>=10)
                then to_char(to_date(to_number(
                substr(t_health_delivery.health_delivery_born_date,1,4),'9999')-543 || 
                substr(t_health_delivery.health_delivery_born_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                ELSE '' END AS BDATE  
	, b_icd10.icd10_number AS BRESULT 
	, t_health_delivery.f_health_postpartum_birth_place_id AS BPLACE  
	, t_health_delivery.b_visit_office_birth_place AS BHOSP  
	, CASE  WHEN (t_health_delivery.f_health_birth_method_id = '1'         
			OR  t_health_delivery.f_health_birth_method_id = '2'        
			OR  t_health_delivery.f_health_birth_method_id = '3'       
			OR  t_health_delivery.f_health_birth_method_id = '4'       
			OR  t_health_delivery.f_health_birth_method_id = '5' )       
			THEN t_health_delivery.f_health_birth_method_id   
		ELSE '' END AS BTYPE  
	, t_health_delivery.f_health_pregnancy_birth_doctor_type_id AS BDOCTOR  
	, CASE WHEN (t_health_delivery.health_delivery_parity is null or t_health_delivery.health_delivery_parity='') THEN '0'
            ELSE t_health_delivery.health_delivery_parity END AS LBORN
	, CASE WHEN (t_health_delivery.health_delivery_stillborn is null or t_health_delivery.health_delivery_stillborn='') THEN '0'
            ELSE t_health_delivery.health_delivery_stillborn END AS SBORN
	, max(CASE WHEN (length(SUB1.record_date_time)>=10 and SUB1.health_postpartum_visit='1')
        then to_char(to_date(to_number(
        substr(SUB1.record_date_time,1,4),'9999')-543 || 
        substr(SUB1.record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END) AS PPCARE1
	, max(CASE WHEN (length(SUB1.record_date_time)>=10 and SUB1.health_postpartum_visit='2')
        then to_char(to_date(to_number(
        substr(SUB1.record_date_time,1,4),'9999')-543 || 
        substr(SUB1.record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END) AS PPCARE2
	, max(CASE WHEN (length(SUB1.record_date_time)>=10 and SUB1.health_postpartum_visit='3')
        then to_char(to_date(to_number(
        substr(SUB1.record_date_time,1,4),'9999')-543 || 
        substr(SUB1.record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END) AS PPCARE3
	, case when SUB1.health_postpartum_summary_verify='0' then '2'
        else SUB1.health_postpartum_summary_verify end AS PPRES  

 ,max(CASE WHEN (length(SUB0.record_date_time)>=10)  
 	         then (to_number(substring(SUB0.record_date_time,1,4),'9999') - 543)      
                        || substring(SUB0.record_date_time,6,2)      
                        || substring(SUB0.record_date_time,9,2) 
                        || substring(SUB0.record_date_time,12,2) 
                        || substring(SUB0.record_date_time,15,2) 
                        || substring(SUB0.record_date_time,18,2) 
                    
         		ELSE ''  END) AS D_UPDATE  --NOT NULL 

FROM (select t_health_family_id,health_postpartum_pregnant_number,t_health_delivery_id,max(record_date_time) as record_date_time
         from t_health_postpartum    GROUP BY t_health_family_id,health_postpartum_pregnant_number,t_health_delivery_id) as SUB0
    INNER JOIN t_health_family ON SUB0.t_health_family_id = t_health_family.t_health_family_id  
    LEFT JOIN  t_health_pregnancy ON (t_health_pregnancy.t_health_family_id = SUB0.t_health_family_id
        and t_health_pregnancy.health_pregnancy_gravida_number = SUB0.health_postpartum_pregnant_number)
    LEFT JOIN t_health_postpartum as SUB1 on (SUB1.t_health_family_id = SUB0.t_health_family_id
        and SUB1.health_postpartum_pregnant_number = SUB0.health_postpartum_pregnant_number)
    LEFT JOIN t_health_delivery ON (SUB0.t_health_family_id = t_health_delivery.t_health_family_id 
        and t_health_delivery.gravida_number = SUB0.health_postpartum_pregnant_number)
    LEFT JOIN t_health_anc ON (t_health_anc.t_health_pregnancy_id = t_health_pregnancy.t_health_pregnancy_id )
    LEFT JOIN b_icd10   ON (t_health_delivery.b_icd10_id = b_icd10.b_icd10_id )
	,b_site
			
WHERE  health_family_active = '1'
    AND substring(SUB0.record_date_time,1,10) >= ?        
    AND substring(SUB0.record_date_time,1,10) <= ?

group by pcucode,pid,gravida,lmp,edc,bdate,bresult,bplace,bhosp,btype
,bdoctor,lborn,sborn,ppres 
 order by pid
