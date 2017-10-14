select r_plan_group.plan_group_description
    ,opd.patient_incup,opd.patient_outcup,opd.visit_incup,opd.visit_outcup
    ,pcu.patient_incup,pcu.patient_outcup,pcu.visit_incup,pcu.visit_outcup
    ,ipd_incup,ipd_outcup,day_stay_incup,day_stay_outcup
    ,referin_incup,referin_inchangwat,referin_outchangwat,referout_incup,referout_inchangwat,referout_outchangwat
from r_plan_group
------------------------------------------------------------------------------------
LEFT JOIN(
SELECT r_plan_group.plan_group_description  
          ,sum(case when ((query1.t_visit_id IS NOT NULL and query1.t_visit_id <> '')  
                        AND ((patient_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))  
                        OR (patient_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup)
                        OR patient_payment_main_hospital = '')))  
               then 1  
               else 0 end ) AS ipd_incup  
          ,sum(case when ((query1.t_visit_id IS NOT NULL and query1.t_visit_id <> '') 
                        AND ((patient_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))   
                        AND (patient_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))))  
               then 1  
               else 0 end) AS ipd_outcup  
          ,sum(case when (((patient_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))   
                        OR (patient_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup))) 
                        AND ((to_date(substring(visit_staff_doctor_discharge_date_time,1,10),'yyyy-mm-dd') -  
                          to_date(substring(visit_begin_admit_date_time,1,10),'yyyy-mm-dd')) = 0 ))  
               then 1  
               when (((patient_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))   
                     OR (patient_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup))) 
                       AND ((to_date(substring(visit_staff_doctor_discharge_date_time,1,10),'yyyy-mm-dd') -  
                          to_date(substring(visit_begin_admit_date_time,1,10),'yyyy-mm-dd')) > 0 ))  
               then   
                   to_date(substring(visit_staff_doctor_discharge_date_time,1,10),'yyyy-mm-dd') -  
                   to_date(substring(visit_begin_admit_date_time,1,10),'yyyy-mm-dd')  
               else 0 end) AS day_stay_incup  
          ,sum(case when (((patient_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))   
                       AND (patient_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))) 
                       AND ((to_date(substring(visit_staff_doctor_discharge_date_time,1,10),'yyyy-mm-dd') -  
                         to_date(substring(visit_begin_admit_date_time,1,10),'yyyy-mm-dd')) = 0 ))  
               then 1  
               when (((patient_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))   
                       AND (patient_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))) 
                       AND ((to_date(substring(visit_staff_doctor_discharge_date_time,1,10),'yyyy-mm-dd') -  
                         to_date(substring(visit_begin_admit_date_time,1,10),'yyyy-mm-dd')) > 0 ))  
               then   
                   to_date(substring(visit_staff_doctor_discharge_date_time,1,10),'yyyy-mm-dd') -  
                   to_date(substring(visit_begin_admit_date_time,1,10),'yyyy-mm-dd')  
               else 0 end) AS day_stay_outcup  

FROM r_plan_group 
    LEFT JOIN  
        (SELECT  t_visit.t_visit_id
                ,t_visit.t_patient_id
                ,t_visit.visit_staff_doctor_discharge_date_time  
                ,t_visit.visit_begin_admit_date_time
                ,patient_payment_main_hospital
                ,t_patient_payment.patient_payment_sub_hospital
                ,b_contract_plans.contract_plans_pttype
                ,CASE WHEN (r_plan_group_map_pttype.r_plan_group_id IS NULL OR  
                    r_plan_group_map_pttype.r_plan_group_id = '' OR  
                     r_plan_group_map_pttype.r_plan_group_id = 'null' ) 
                    THEN '8030000000006'  
                    ELSE r_plan_group_map_pttype.r_plan_group_id  
                    END AS r_plan_group_id 
        FROM  t_visit 
                LEFT JOIN t_patient ON t_patient.t_patient_id = t_visit.t_patient_id
                LEFT JOIN t_patient_payment ON t_patient.t_health_family_id = t_patient_payment.t_health_family_id 
            LEFT JOIN b_contract_plans ON t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id
            LEFT JOIN r_plan_group_map_pttype ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype  
        WHERE  t_visit.f_visit_status_id <> '4'
            AND visit_doctor_discharge_status = '1'
            AND (t_visit.f_visit_type_id = '1')  
            AND (SUBSTRING(t_visit.visit_staff_doctor_discharge_date_time,1,10)
                 BETWEEN '2550-06-01' and '2550-07-01')
         ) AS query1 ON r_plan_group.r_plan_group_id = query1.r_plan_group_id  
GROUP BY  
    r_plan_group.plan_group_description  
    ,r_plan_group.plan_group_number 
    ,r_plan_group.r_plan_group_id  
ORDER BY  
    r_plan_group.plan_group_number 
) as ipd on ipd.plan_group_description = r_plan_group.plan_group_description
-------patient_incup,patient_outcup,visit_incup,visit_outcup-----------------------------------------------------------------------------
LEFT JOIN (
select  r_plan_group.plan_group_description AS plan_group_description 
    ,sum(case when  (query1.visit_first_visit = '1'  
                       AND ((patient_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))   
                       OR (patient_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup)
                        OR patient_payment_main_hospital = '')))  
             then  1  
             else  0 end) AS patient_incup 
    ,sum(case when  (query1.visit_first_visit = '1' 
                       AND ((patient_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))   
                       AND (patient_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup)
                        AND patient_payment_main_hospital <> '' )))  
             then  1  
             else  0 end) AS patient_outcup 
    ,sum(case when (((patient_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))   
                    OR (patient_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup)
                    OR patient_payment_main_hospital = ''))  
                   AND t_visit_id IS NOT NULL)  
         then 1  
         else 0 end ) AS visit_incup  
    ,sum(case when (((patient_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))   
                   AND (patient_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))) 
                   AND patient_payment_main_hospital <> '' 
                    AND t_visit_id IS NOT NULL)  
         then 1  
         else 0 end ) AS visit_outcup  
FROM r_plan_group  
    LEFT JOIN  
         (SELECT t_visit.t_visit_id
            , t_visit.visit_first_visit
            , t_visit.t_patient_id
            , patient_payment_main_hospital
            , t_patient_payment.patient_payment_sub_hospital
            , b_contract_plans.contract_plans_pttype
            , CASE WHEN (r_plan_group_map_pttype.r_plan_group_id IS NULL OR  
                     r_plan_group_map_pttype.r_plan_group_id = '' OR  
                     r_plan_group_map_pttype.r_plan_group_id = 'null' ) 
                THEN '8030000000006'  
                ELSE r_plan_group_map_pttype.r_plan_group_id  
                END AS r_plan_group_id 
         FROM b_site,t_visit 
                LEFT JOIN t_patient ON t_patient.t_patient_id = t_visit.t_patient_id
                LEFT JOIN t_patient_payment ON t_patient.t_health_family_id = t_patient_payment.t_health_family_id 
            LEFT JOIN b_contract_plans  ON t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id
            LEFT JOIN r_plan_group_map_pttype ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype  
         
        WHERE  (t_visit.f_visit_status_id <> '4')              
             AND (SUBSTRING(t_visit.visit_begin_visit_time,1,10) 
                 BETWEEN '2550-06-01' and '2550-07-01')
        ) AS query1  On r_plan_group.r_plan_group_id = query1.r_plan_group_id  
group by   
         r_plan_group.plan_group_description  
         , r_plan_group.r_plan_group_id 
         , r_plan_group.plan_group_number 
order by  
         r_plan_group.plan_group_number 
) as opd on opd.plan_group_description = r_plan_group.plan_group_description
----patient_incup,patient_outcup,visit_incup,visit_outcup--------------------------------------------------------------------------------
LEFT JOIN(
select  
    r_plan_group.plan_group_description AS plan_group_description 
    ,sum(case when  (query1.visit_first_visit = '1'  
                       AND ((patient_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))   
                       OR (patient_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup))))  
             then  1  
             else  0 end) AS patient_incup 
    ,sum(case when  (query1.visit_first_visit = '1' 
                       AND ((patient_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))   
                       AND (patient_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))))  
             then  1  
             else  0 end) AS patient_outcup 
    ,sum(case when (((patient_payment_main_hospital IN (Select  hospital_incup_code from r_hospital_incup))   
                    OR (patient_payment_sub_hospital IN (Select  hospital_incup_code from r_hospital_incup)))  
                   AND t_visit_id IS NOT NULL)  
         then 1  
         else 0 end ) AS visit_incup  
    ,sum(case when (((patient_payment_main_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))   
                   AND (patient_payment_sub_hospital NOT IN (Select  hospital_incup_code from r_hospital_incup))) 
                   AND t_visit_id IS NOT NULL)  
         then 1  
         else 0 end ) AS visit_outcup  
FROM r_plan_group  
    LEFT JOIN  
         (SELECT t_visit.t_visit_id
            , t_visit.visit_first_visit
            , t_visit.t_patient_id
            , CASE WHEN t_patient_payment.patient_payment_main_hospital =''
                THEN b_site.b_site_id
                ELSE t_patient_payment.patient_payment_main_hospital 
                END AS patient_payment_main_hospital
            ,t_patient_payment.patient_payment_sub_hospital
            , b_contract_plans.contract_plans_pttype
            ,CASE WHEN (r_plan_group_map_pttype.r_plan_group_id IS NULL OR  
                     r_plan_group_map_pttype.r_plan_group_id = '' OR  
                     r_plan_group_map_pttype.r_plan_group_id = 'null' ) 
                THEN '8030000000006'  
                ELSE r_plan_group_map_pttype.r_plan_group_id  
                END AS r_plan_group_id 
         FROM   t_visit 
            LEFT JOIN t_patient ON t_patient.t_patient_id = t_visit.t_patient_id
            LEFT JOIN t_patient_payment ON t_patient.t_health_family_id = t_patient_payment.t_health_family_id 
            INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id        
            inner JOIN t_health_home ON (t_health_family.t_health_home_id = t_health_home.t_health_home_id
                and t_health_home.health_home_house <> '0')
            inner JOIN t_health_village ON (t_health_home.t_health_village_id = t_health_village.t_health_village_id    
                and t_health_village.village_moo <> '0')
            INNER JOIN b_contract_plans ON t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id
            LEFT JOIN r_plan_group_map_pttype ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype  
         WHERE (t_visit.f_visit_status_id <> '4')   
             AND (t_visit.f_visit_type_id = '0')    
             AND (SUBSTRING(t_visit.visit_begin_visit_time,1,10) 
                  BETWEEN '2550-06-01' and '2550-07-01')
        ) AS query1  On r_plan_group.r_plan_group_id = query1.r_plan_group_id  
group by   
         r_plan_group.plan_group_description  
         , r_plan_group.r_plan_group_id 
         , r_plan_group.plan_group_number 
order by  
         r_plan_group.plan_group_number 
) as pcu on pcu.plan_group_description = r_plan_group.plan_group_description
----referin_incup,referin_inchangwat,referin_outchangwat,referout_incup,referout_inchangwat,referout_outchangwat----------------------------------------------------------
LEFT JOIN(
SELECT  r_plan_group.plan_group_description  
    ,sum(case 
        when (query1.b_visit_office_id_refer_in IN (Select  hospital_incup_code from r_hospital_incup) 
            AND query1.f_visit_refer_type_id = '0')  
            then 1 
        when (query1.b_visit_office_id_refer_in =''
            AND query1.f_visit_refer_type_id = '0')  
            then 1 else 0 end) AS referin_incup
    ,sum(case 
        when ((b_visit_office_id_refer_in NOT IN (Select  hospital_incup_code from r_hospital_incup))  
            AND query1.f_visit_refer_type_id = '0'
            AND ((query1.changwat_referin <> '')  
            AND (query1.changwat_referin  IN (select  substring(site_changwat,1,2) from b_site))))  
            then 1  else 0 end) AS referin_inchangwat  
    ,sum(case 
        when ((b_visit_office_id_refer_in NOT IN (Select  hospital_incup_code from r_hospital_incup))  
            AND query1.f_visit_refer_type_id = '0' 
            AND ((query1.changwat_referin <> '') 
            AND (query1.changwat_referin  NOT IN (select  substring(site_changwat,1,2) from b_site))))  
            then 1  else 0 end) AS referin_outchangwat  
    ,sum(case 
        when (b_visit_office_id_refer_out IN (Select  hospital_incup_code from r_hospital_incup)  
           AND query1.f_visit_refer_type_id = '1' ) 
           then 1  else 0 end) AS referout_incup
    ,sum(case 
        when ((b_visit_office_id_refer_out NOT IN (Select  hospital_incup_code from r_hospital_incup))  
           AND query1.f_visit_refer_type_id = '1'            
           AND ((query1.changwat_referout <> '')  
           AND (query1.changwat_referout  IN (select  substring(site_changwat,1,2) from b_site))))  
           then 1
        when (query1.b_visit_office_id_refer_out =''
            AND query1.f_visit_refer_type_id = '1')
            then 1  else 0 end) AS referout_inchangwat  
    ,sum(case 
        when  ((b_visit_office_id_refer_out NOT IN (Select  hospital_incup_code from r_hospital_incup))  
           AND query1.f_visit_refer_type_id = '1'  
           AND ((query1.changwat_referout <> '') 
           AND (query1.changwat_referout  NOT IN (select  substring(site_changwat,1,2) from b_site))))  
           then 1  else 0 end) AS referout_outchangwat  
          
FROM  r_plan_group  
    LEFT JOIN  
           (SELECT   
               t_visit.t_visit_id  
               ,t_visit.t_patient_id  
               ,b_visit_office_id_refer_in  
               ,b_visit_office_id_refer_out  
               ,t_visit_refer_in_out.f_visit_refer_type_id
               , CASE WHEN (r_plan_group_map_pttype.r_plan_group_id IS NULL OR  
                      r_plan_group_map_pttype.r_plan_group_id = '' OR  
                      r_plan_group_map_pttype.r_plan_group_id = 'null' ) 
                    THEN '8030000000006'  
                    ELSE r_plan_group_map_pttype.r_plan_group_id  
                    END AS r_plan_group_id 
               ,case when (b_visit_office_id_refer_in <> '')  
                    then (select visit_office_changwat from b_visit_office where b_visit_office_id = b_visit_office_id_refer_in)  
                    else ''  
                    end AS changwat_referin  
               ,case when (b_visit_office_id_refer_out <> '')  
                    then (select visit_office_changwat from b_visit_office where b_visit_office_id = b_visit_office_id_refer_out)  
                    else ''  
                    end AS changwat_referout  
           FROM  t_visit_refer_in_out 
                INNER JOIN t_visit ON t_visit_refer_in_out.t_visit_id = t_visit.t_visit_id
                LEFT JOIN t_patient ON t_patient.t_patient_id = t_visit.t_patient_id
                LEFT JOIN t_patient_payment ON t_patient.t_health_family_id = t_patient_payment.t_health_family_id   
                LEFT JOIN b_contract_plans ON t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id  
                LEFT JOIN r_plan_group_map_pttype ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype  
           WHERE   (t_visit.f_visit_status_id <> '4' )   
                AND visit_refer_in_out_active = '1'
               AND (SUBSTRING(t_visit.visit_begin_visit_time,1,10)
                  BETWEEN '2550-06-01' and '2550-07-01')
        ) AS query1 On r_plan_group.r_plan_group_id = query1.r_plan_group_id  
group by   
           r_plan_group.plan_group_description  
           , r_plan_group.plan_group_number 
           ,r_plan_group.r_plan_group_id  
order by   
           r_plan_group.plan_group_number 
) as refer on refer.plan_group_description = r_plan_group.plan_group_description
