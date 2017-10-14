---DRUG_53
SELECT  distinct
	b_site.b_visit_office_id AS HCODE --NOT NULL
    ,t_visit.visit_hn as HN --NOT NULL
    , CASE WHEN t_visit.f_visit_type_id = '1' then t_visit.visit_vn
        ELSE '' end as AN --NOT NULL
    , case when t_visit.f_visit_type_id = '0'
                then (CASE WHEN b_report_12files_std_clinic_id is not null
                                    then (t_visit.f_visit_type_id || b_report_12files_std_clinic_id || '1')
                                    ELSE ''
                        end )
                when t_visit.f_visit_type_id = '1'
                then (t_visit.f_visit_type_id || (select b_report_12files_std_clinic_id from b_report_12files_map_clinic
                                                                where b_report_12files_map_clinic.b_visit_clinic_id = t_visit.b_visit_clinic_id limit 1) || '1')
                else ''
        end as CLINIC
    ,t_health_family.patient_pid as PERSON_ID
    , CASE WHEN length(t_visit.visit_begin_visit_time)>=10
            THEN(to_number(substring(t_visit.visit_begin_visit_time,1,4),'9999')-543)
                || substring(t_visit.visit_begin_visit_time,6,2)
                || substring(t_visit.visit_begin_visit_time,9,2)
            ELSE '' END AS DATE_SERV --NOT NULL
    , b_item.item_number AS DID --NOT NULL
    , t_order.order_common_name AS DID --NOT NULL
    , t_order.order_qty AS AMOUNT
    , t_order.order_price AS DRUGPRIC
    , t_order.order_cost AS DRUGCOST
    , substr(b_nhso_map_drug.f_nhso_drug_id,1,24)  AS DIDSTD
    , b_item_drug_uom.item_drug_uom_description as UNIT
    , item_unit_packing_qty as UNIT_PACKING
    ,t_visit.visit_vn  AS SEQ --NOT NULL
FROM t_visit
	INNER JOIN t_patient ON (t_visit.t_patient_id = t_patient.t_patient_id)
	INNER JOIN t_health_family ON (t_health_family.t_health_family_id = t_patient.t_health_family_id)
        INNER JOIN  t_order   ON (t_order.f_order_status_id <> '3'
        AND t_order.f_order_status_id <> '0'
        AND (t_order.f_item_group_id = '1'
        OR t_order.f_item_group_id = '4')
        AND t_visit.t_visit_id = t_order.t_visit_id )
    LEFT JOIN t_diag_icd10 ON (t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id and t_diag_icd10.diag_icd10_active = '1' and t_diag_icd10.f_diag_icd10_type_id = '1')
    LEFT JOIN b_report_12files_map_clinic ON  b_report_12files_map_clinic.b_visit_clinic_id = t_diag_icd10.b_visit_clinic_id
    INNER JOIN b_item ON b_item.b_item_id = t_order.b_item_id
    LEFT JOIN b_nhso_map_drug on t_order.b_item_id = b_nhso_map_drug.b_item_id
    LEFT JOIN t_order_drug on t_order.t_order_id=t_order_drug.t_order_id
    LEFT JOIN b_item_drug_uom on t_order_drug.b_item_drug_uom_id_purch=b_item_drug_uom.b_item_drug_uom_id
	,b_site

where   t_order.f_order_status_id <>'3'
    AND t_visit.f_visit_status_id <> '4'
    AND substring(t_visit.visit_begin_visit_time,1,10) >= ?
    AND substring(t_visit.visit_begin_visit_time,1,10) <= ?
    and t_visit.f_visit_type_id <> 'S'
