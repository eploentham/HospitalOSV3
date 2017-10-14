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
                 BETWEEN ? and ?)
        ) AS query1 On r_plan_group.r_plan_group_id = query1.r_plan_group_id  
group by   
           r_plan_group.plan_group_description  
           , r_plan_group.plan_group_number 
           ,r_plan_group.r_plan_group_id  
order by   
           r_plan_group.plan_group_number 
