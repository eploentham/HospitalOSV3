select 
	PCUCODE
	,CID
        ,M_CID
	,GRAVIDA
	,BDATE
	,BPLACE
	,BHOSP
	,BTYPE
	,BDOCTOR
	,BWEIGTH
	,ASPHYXIA
	,VITK
	, max(BCARE1) as		 BCARE1
	, max(BCARE2) as		 BCARE2
	, max(BCARE3) as		 BCARE3
	, max(BCRES) as BCRES
	, max(UPDATE ) as UPDATE
        ,ID as ID
        ,DATE_SERV as DATE_SERV
        ,'' as NOTEDATE
,active
from (
SELECT  
	b_site.b_visit_office_id as 			 PCUCODE
	,t_health_family.patient_pid as			 CID
    , t_patient.patient_pid AS M_CID
	, t_health_pp.pp_gravida  as		 GRAVIDA
	, CASE			
		WHEN (t_health_delivery.health_delivery_born_date <> ''		
			AND t_health_delivery.health_delivery_born_date <> 'null')	
			THEN   (to_number(substring(t_health_delivery.health_delivery_born_date,1,4),'9999')-543)	
			||  substring(t_health_delivery.health_delivery_born_date,6,2)	
			||  substring(t_health_delivery.health_delivery_born_date,9,2)	
		ELSE ''   END as		 BDATE
	, CASE			
		WHEN (t_health_delivery.f_health_postpartum_birth_place_id IS NOT NULL		
			AND t_health_delivery.f_health_postpartum_birth_place_id <> ''	
			AND t_health_delivery.f_health_postpartum_birth_place_id <> 'null')	
			THEN t_health_delivery.f_health_postpartum_birth_place_id	
		else ''  end as		 BPLACE
	, CASE			
		WHEN (t_health_delivery.b_visit_office_birth_place IS NOT NULL		
			AND t_health_delivery.b_visit_office_birth_place <> ''	
			AND t_health_delivery.b_visit_office_birth_place <> 'null')	
			THEN t_health_delivery.b_visit_office_birth_place	
		else ''  end as		 BHOSP
	,  CASE			
		WHEN (t_health_delivery.f_health_birth_method_id = '1'		
			OR    t_health_delivery.f_health_birth_method_id = '2'	
			OR    t_health_delivery.f_health_birth_method_id = '3'	
			OR    t_health_delivery.f_health_birth_method_id = '4'	
			OR    t_health_delivery.f_health_birth_method_id = '5' )	
			THEN t_health_delivery.f_health_birth_method_id	
		ELSE ''  END as		 BTYPE
	,  CASE			
		WHEN (t_health_delivery.f_health_pregnancy_birth_doctor_type_id = '1'		
			OR  t_health_delivery.f_health_pregnancy_birth_doctor_type_id = '2'	
			OR  t_health_delivery.f_health_pregnancy_birth_doctor_type_id = '3'	
			OR  t_health_delivery.f_health_pregnancy_birth_doctor_type_id = '4'	
			OR  t_health_delivery.f_health_pregnancy_birth_doctor_type_id = '5')	
			THEN t_health_delivery.f_health_pregnancy_birth_doctor_type_id	
		ELSE '' END as		 BDOCTOR
	,   t_health_pp.pp_weight  as			 BWEIGTH
	, CASE
			WHEN (t_health_pp.pp_lost_oxygen= '0')
				THEN '1'
			WHEN (t_health_pp.pp_lost_oxygen = '1')
				THEN '0'
			ELSE '' END AS ASPHYXIA
	, t_health_pp.pp_vit_k AS VITK
	, (CASE WHEN SUB1.pp_care_number = '1'
            THEN (to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)
						||substring(t_visit.visit_begin_visit_time,6,2)
						||substring(t_visit.visit_begin_visit_time,9,2)
			ELSE '' END) as		 BCARE1
	,(CASE WHEN SUB1.pp_care_number = '2'
            THEN (to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)
					||substring(t_visit.visit_begin_visit_time,6,2)
					||substring(t_visit.visit_begin_visit_time,9,2)
			ELSE '' END	) as		 BCARE2
	, (CASE WHEN SUB1.pp_care_number = '3'
            THEN (to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)
					||substring(t_visit.visit_begin_visit_time,6,2)
					||substring(t_visit.visit_begin_visit_time,9,2)
            ELSE '' END) as		 BCARE3
	, CASE
			WHEN (SUB1.pp_care_result = '1' OR SUB1.pp_care_result = '2')
				THEN SUB1.pp_care_result
			ELSE ''   END as			 BCRES
	, CASE WHEN length(SUB1.pp_care_modify_date_time)>=10
        THEN (to_number(substring(SUB1.pp_care_modify_date_time,1,4),'9999')-543)
				|| substring(SUB1.pp_care_modify_date_time,6,2)
				|| substring(SUB1.pp_care_modify_date_time,9,2)
        ELSE (to_number(substring(SUB1.pp_care_record_date_time,1,4),'9999')-543)
				|| substring(SUB1.pp_care_record_date_time,6,2)
				|| substring(SUB1.pp_care_record_date_time,9,2) END  as			 UPDATE 
        ,t_health_pp.t_health_pp_id as ID
	, CASE WHEN (length(t_visit.visit_begin_visit_time)>=10)
then to_char(to_date(to_number(
substr(t_visit.visit_begin_visit_time,1,4),'9999')-543 || 
substr(t_visit.visit_begin_visit_time,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END  AS DATE_SERV  
,SUB1.pp_care_active as active
FROM  t_health_pp_care as SUB1
        INNER JOIN t_health_pp ON (t_health_pp.t_health_family_id = SUB1.t_health_family_id  and t_health_pp.pp_active  = '1')
        INNER JOIN t_health_family ON t_health_pp.t_health_family_id = t_health_family.t_health_family_id 
        
        INNER JOIN t_visit on (t_visit.t_visit_id = SUB1.t_visit_id
            AND t_visit.f_visit_status_id <> '4')
        INNER JOIN t_patient on (t_patient.t_patient_id = t_visit.t_patient_id
            AND t_patient.patient_active = '1')
        LEFT JOIN t_health_delivery   ON (t_patient.t_health_family_id = t_health_delivery.t_health_family_id
            and cast(t_health_pp.pp_gravida as numeric) = cast(t_health_delivery.gravida_number as numeric)
            and t_health_delivery.health_delivery_active = '1')
        ,b_site

	WHERE  
SUB1.pp_care_active='1' AND 
substring(t_visit.visit_begin_visit_time,1,10) >= ?
        AND substring(t_visit.visit_begin_visit_time,1,10) <= ?
) as q

GROUP BY 
	PCUCODE
	,CID
	,GRAVIDA
	,BDATE
	,BPLACE
	,BHOSP
	,BTYPE
	,BDOCTOR
	,BWEIGTH
	,ASPHYXIA
	,VITK
	,M_CID
	,CID
        ,ID
        ,DATE_SERV
,active
