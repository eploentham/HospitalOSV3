SELECT
	r_uc_plan_group.uc_plan_group_description 
    ,CASE WHEN (SUM(query2.thalassemia_infant) > 0) 
            THEN SUM(query2.thalassemia_infant) 
     ELSE 0 
	 END AS thalassemia_infant 
    ,CASE WHEN (SUM(query2.thiroid) > 0) 
            THEN SUM(query2.thiroid) 
	 ELSE 0 
	 END AS thiroid 
FROM
r_uc_plan_group 
LEFT JOIN (	SELECT
                CASE WHEN ((query1.plan_group_number = '1') OR (query1.plan_group_number = '2')) 
						THEN query1.plan_group_number 
				WHEN ((query1.plan_group_number = '3') OR (query1.plan_group_number = '4')) 
                    AND ((query1.patient_payment_main_hospital IN (SELECT hospital_incup_code FROM r_hospital_incup)) OR
						 (query1.patient_payment_sub_hospital IN (SELECT hospital_incup_code FROM r_hospital_incup)))
						THEN '3' 
				WHEN (((query1.plan_group_number = '3') OR (query1.plan_group_number = '4'))
                    AND (query1.patient_payment_main_hospital NOT IN (SELECT hospital_incup_code FROM r_hospital_incup))
                    AND (query1.patient_payment_sub_hospital NOT IN (SELECT hospital_incup_code FROM r_hospital_incup)))
                        THEN '4' 
				WHEN ((query1.plan_group_number <> '1') AND (query1.plan_group_number <> '2') AND (query1.plan_group_number <> '3') 
                    AND (query1.plan_group_number <> '4' )) 
						THEN '5' 
				END AS plan_group_id
                ,CASE WHEN (( query1.r_rp11_disease_id = '8280000000003') 
                        AND query1.t_patient_id IN (SELECT t_patient.t_patient_id 
                                                    FROM t_health_family 
													INNER JOIN t_patient ON t_health_family.t_health_family_id = t_patient.t_health_family_id 
													INNER JOIN t_visit ON t_patient.t_patient_id = t_visit.t_patient_id 
													INNER JOIN t_diag_icd10 ON t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn 
													INNER JOIN t_order ON t_visit.t_visit_id = t_order.t_visit_id 
													INNER JOIN r_rp11_item ON t_order.b_item_id = r_rp11_item.b_item_id 
													INNER JOIN r_rp11_disease ON r_rp11_item.r_rp11_disease_id = r_rp11_disease.r_rp11_disease_id 
													WHERE r_rp11_disease.r_rp11_disease_id = '8280000000003' 
                                                        AND SUBSTRING(t_diag_icd10.diag_icd10_number,1,3) = 'D56' 
                                                        AND t_visit.f_visit_status_id <> '4' 
														AND t_order.f_order_status_id <> '0' 
														AND t_order.f_order_status_id <> '3' 
														AND t_diag_icd10.diag_icd10_active = '1' 
														AND SUBSTRING(t_visit.visit_financial_discharge_time,1,10) < ? 
													GROUP BY
														t_patient.t_patient_id 
                                                    )
							) 
                            THEN 1 
				ELSE 0 
				END AS thalassemia_infant
				,CASE WHEN (( query1.r_rp11_disease_id = '8280000000005') 
						AND query1.t_patient_id IN (SELECT t_patient.t_patient_id 
													FROM t_health_family 
													INNER JOIN t_patient ON t_health_family.t_health_family_id = t_patient.t_health_family_id 
													INNER JOIN t_visit ON t_patient.t_patient_id = t_visit.t_patient_id 
													INNER JOIN t_diag_icd10 ON t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn 
													INNER JOIN t_order ON t_visit.t_visit_id = t_order.t_visit_id 
													INNER JOIN r_rp11_item ON t_order.b_item_id = r_rp11_item.b_item_id 
													INNER JOIN r_rp11_disease ON r_rp11_item.r_rp11_disease_id = r_rp11_disease.r_rp11_disease_id 
													WHERE r_rp11_disease.r_rp11_disease_id = '8280000000005'
                                                        AND (to_number((SUBSTRING(' ' || age(to_date(SUBSTRING(t_visit.visit_financial_discharge_time,1,10) ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) FROM '(...)year')),'999') IS NULL
                                                            AND((to_number((SUBSTRING(' ' || age(to_date(SUBSTRING(t_visit.visit_financial_discharge_time,1,10) ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) FROM '(...)mon')),'999') <=1)
                                                                OR (to_number((SUBSTRING(' ' || age(to_date(SUBSTRING(t_visit.visit_financial_discharge_time,1,10) ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) FROM '(...)mon') ),'999') IS NULL)
                                                               )
                                                            )
                                                        AND	(SUBSTRING(t_diag_icd10.diag_icd10_number,1,3) = 'E00' 
                                                            OR SUBSTRING(t_diag_icd10.diag_icd10_number,1,3) = 'E01'
                                                            OR SUBSTRING(t_diag_icd10.diag_icd10_number,1,3) = 'E02'
                                                            OR SUBSTRING(t_diag_icd10.diag_icd10_number,1,3) = 'E05'
                                                            )
                                                        AND t_visit.f_visit_status_id <> '4' 
														AND t_order.f_order_status_id <> '0' 
														AND	t_order.f_order_status_id <> '3' 
														AND	t_diag_icd10.diag_icd10_active = '1' 
														AND SUBSTRING(t_visit.visit_financial_discharge_time,1,10) < ? 
													GROUP BY
														t_patient.t_patient_id 
                                                    )
						AND (to_number((SUBSTRING(' ' || age(to_date(SUBSTRING(t_visit.visit_financial_discharge_time,1,10),'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) FROM '(...)year') ),'999') IS NULL
                            AND((to_number((SUBSTRING(' ' || age(to_date(SUBSTRING(t_visit.visit_financial_discharge_time,1,10),'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) FROM '(...)mon') ),'999') <=1)
                                OR (to_number((SUBSTRING(' ' || age(to_date(SUBSTRING(t_visit.visit_financial_discharge_time,1,10),'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) FROM '(...)mon') ),'999') IS NULL)
                                )
                            )
                        AND (SUBSTRING(t_diag_icd10.diag_icd10_number,1,3) = 'E00'
                            OR SUBSTRING(t_diag_icd10.diag_icd10_number,1,3) = 'E01'
                            OR SUBSTRING(t_diag_icd10.diag_icd10_number,1,3) = 'E02'
                            OR SUBSTRING(t_diag_icd10.diag_icd10_number,1,3) = 'E05')
                            ) 
                            THEN 1 
				ELSE 0 
				END AS thiroid 
			FROM (SELECT DISTINCT t_patient.t_patient_id
                        ,r_plan_group.plan_group_number
                        ,r_rp11_disease.r_rp11_disease_id
                        ,t_patient_payment.patient_payment_main_hospital 
						,t_patient_payment.patient_payment_sub_hospital 
                        ,t_health_family.t_health_family_id
                    FROM t_health_family 
					INNER JOIN t_patient ON t_health_family.t_health_family_id = t_patient.t_health_family_id 
					INNER JOIN t_visit ON t_patient.t_patient_id = t_visit.t_patient_id 
					INNER JOIN t_order ON t_visit.t_visit_id = t_order.t_visit_id 
					INNER JOIN r_rp11_item ON t_order.b_item_id = r_rp11_item.b_item_id 
					INNER JOIN r_rp11_disease ON r_rp11_item.r_rp11_disease_id = r_rp11_disease.r_rp11_disease_id 
					INNER JOIN t_patient_payment ON t_health_family.t_health_family_id = t_patient_payment.t_health_family_id 
					INNER JOIN b_contract_plans ON t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id 
					INNER JOIN r_plan_group_map_pttype ON b_contract_plans.contract_plans_pttype = r_plan_group_map_pttype.plan_group_map_pttype_pttype 
					INNER JOIN r_plan_group ON r_plan_group_map_pttype.r_plan_group_id = r_plan_group.r_plan_group_id 
					LEFT JOIN ((SELECT t_visit.t_visit_id 
								FROM t_visit 
								INNER JOIN t_order ON t_visit.t_visit_id = t_order.t_visit_id 
								INNER JOIN t_diag_icd10 ON t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn 
								INNER JOIN r_rp11_item ON t_order.b_item_id = r_rp11_item.b_item_id 
								INNER JOIN r_rp11_disease ON r_rp11_item.r_rp11_disease_id = r_rp11_disease.r_rp11_disease_id 
								WHERE r_rp11_disease.r_rp11_disease_id = '8280000000003'
                                    AND SUBSTRING(t_diag_icd10.diag_icd10_number,1,3) = 'D56' 
									AND t_visit.f_visit_status_id <> '4' 
									AND t_order.f_order_status_id <> '0' 
									AND t_order.f_order_status_id <> '3' 
									AND t_diag_icd10.diag_icd10_active = '1'
                                    AND SUBSTRING(t_visit.visit_financial_discharge_time,1,10) < ? 
								GROUP BY
									t_visit.t_visit_id 

								UNION

                                SELECT t_visit.t_visit_id 
                                FROM t_health_family 
                                INNER JOIN t_patient ON t_health_family.t_health_family_id = t_patient.t_health_family_id 
                                INNER JOIN t_visit ON t_patient.t_patient_id = t_visit.t_patient_id 
                                INNER JOIN t_diag_icd10 ON t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn 
                                INNER JOIN t_order ON t_visit.t_visit_id = t_order.t_visit_id 
                                INNER JOIN r_rp11_item ON t_order.b_item_id = r_rp11_item.b_item_id 
                                INNER JOIN r_rp11_disease ON r_rp11_item.r_rp11_disease_id = r_rp11_disease.r_rp11_disease_id 
                                WHERE r_rp11_disease.r_rp11_disease_id = '8280000000005'
                                    AND (to_number((SUBSTRING(' ' || age(to_date(SUBSTRING(t_visit.visit_financial_discharge_time,1,10) ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) FROM '(...)year') ),'999') IS NULL
                                        AND	((to_number((SUBSTRING(' ' || age(to_date(SUBSTRING(t_visit.visit_financial_discharge_time,1,10) ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) FROM '(...)mon') ),'999') <=1)
                                            OR (to_number((SUBSTRING(' ' || age(to_date(SUBSTRING(t_visit.visit_financial_discharge_time,1,10) ,'YYYY-MM-DD'), to_date(t_health_family.patient_birthday,'YYYY-MM-DD')) FROM '(...)mon') ),'999') IS NULL)
                                            )
                                        )
                                    AND (SUBSTRING(t_diag_icd10.diag_icd10_number,1,3) = 'E00'
                                        OR SUBSTRING(t_diag_icd10.diag_icd10_number,1,3) = 'E01'
                                        OR SUBSTRING(t_diag_icd10.diag_icd10_number,1,3) = 'E02'
                                        OR SUBSTRING(t_diag_icd10.diag_icd10_number,1,3) = 'E05')
                                    AND	t_visit.f_visit_status_id <> '4'
                                    AND t_order.f_order_status_id <> '0'
                                    AND t_order.f_order_status_id <> '3'
                                    AND t_diag_icd10.diag_icd10_active = '1'
                                    AND SUBSTRING(t_visit.visit_financial_discharge_time,1,10) < ? 
                                GROUP BY
                                    t_visit.t_visit_id 			
							)
                            ) AS query_t  ON query_t.t_visit_id = t_visit.t_visit_id 
					WHERE t_visit.f_visit_status_id <> '4'
                        AND query_t.t_visit_id IS NULL
                        AND t_order.f_order_status_id <> '0'
                        AND t_order.f_order_status_id <> '3'
                        AND t_patient_payment.patient_payment_priority = '0' 
						AND (SUBSTRING(t_visit.visit_financial_discharge_time,1,10) BETWEEN ? AND ?) 
				) AS query1 
            INNER JOIN t_visit ON query1.t_patient_id = t_visit.t_patient_id 
			INNER JOIN t_health_family ON query1.t_health_family_id = t_health_family.t_health_family_id 
			INNER JOIN t_diag_icd10 ON t_visit.t_visit_id = t_diag_icd10.diag_icd10_vn 
			GROUP BY query1.t_patient_id
                    ,plan_group_id
                    ,thalassemia_infant
                    ,thiroid 
            ) AS query2 ON query2.plan_group_id = r_uc_plan_group.uc_plan_group_number 
GROUP BY
        r_uc_plan_group.uc_plan_group_description ,
		r_uc_plan_group.uc_plan_group_number 
ORDER BY
		r_uc_plan_group.uc_plan_group_number