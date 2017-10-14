--ทดสอบภาษาไทย
SELECT 
	b_site.b_visit_office_id AS PCUCODE 
	, t_health_family.patient_pid AS CID 
	, t_health_family.health_family_hn_hcis AS PID
	, case when (b_contract_plans.contract_plans_pttype = 'AD') then '74'
           when (b_contract_plans.contract_plans_pttype = 'AA') then '71'
           when (b_contract_plans.contract_plans_pttype = 'AE') then '75'
           when (b_contract_plans.contract_plans_pttype = 'AL') then '78'
           when (b_contract_plans.contract_plans_pttype = 'AF') then '76'
           when (b_contract_plans.contract_plans_pttype = 'AJ') then '82'
           when (b_contract_plans.contract_plans_pttype = 'AG') then '77'
           when (b_contract_plans.contract_plans_pttype = 'AC') then '73'
           when (b_contract_plans.contract_plans_pttype = 'AB') then '72'
           when (b_contract_plans.contract_plans_pttype = 'AK') then '81'
           when (b_contract_plans.contract_plans_pttype = 'UC') then '89'
           when (b_contract_plans.contract_plans_pttype = 'A2') then '02'
           when (b_contract_plans.contract_plans_pttype = 'A7') then '01'
           when (b_contract_plans.contract_plans_pttype = 'AR') then '07'
           when (b_contract_plans.contract_plans_pttype = 'AP') then '06'
           when (b_contract_plans.contract_plans_pttype = 'A9') then '78'
           when (b_contract_plans.contract_plans_pttype = 'A1') then '06'
           when (b_contract_plans.contract_plans_pttype = 'AM') then '78'
           when (b_contract_plans.contract_plans_pttype = 'AN') then '06'
        ELSE '78' END  AS INSTYPE 
	,  t_patient_payment.patient_payment_card_number AS INSID 
	, CASE WHEN (length(t_patient_payment.patient_payment_card_issue_date)>=10)
then to_char(to_date(to_number(
substr(t_patient_payment.patient_payment_card_issue_date,1,4),'9999')-543 || 
substr(t_patient_payment.patient_payment_card_issue_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END AS START 
	, CASE WHEN (length(t_patient_payment.patient_payment_card_expire_date)>=10)
then to_char(to_date(to_number(
substr(t_patient_payment.patient_payment_card_expire_date,1,4),'9999')-543 || 
substr(t_patient_payment.patient_payment_card_expire_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END AS EXPIR 
	, t_patient_payment.patient_payment_main_hospital AS MAIN 
	, t_patient_payment.patient_payment_sub_hospital AS SUB 
	, CASE WHEN (length(t_patient_payment.patient_payment_record_date)>=10)
then to_char(to_date(to_number(
substr(t_patient_payment.patient_payment_record_date,1,4),'9999')-543 || 
substr(t_patient_payment.patient_payment_record_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END AS UPDATE 

------------------------------------------------------------------------------------------

from t_health_village 
        INNER JOIN t_health_home ON  t_health_home.t_health_village_id = t_health_village.t_health_village_id
        INNER JOIN t_health_family ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
        INNER JOIN t_patient_payment ON t_patient_payment.t_health_family_id = t_health_family.t_health_family_id
        INNER join  b_contract_plans on t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id    
        ,b_site

WHERE  t_health_village.village_moo <> '0'      
    AND health_family_active = '1'

union

SELECT 
	b_site.b_visit_office_id AS PCUCODE 
	, t_health_family.patient_pid AS CID 
	, t_health_family.health_family_hn_hcis AS PID
	, case when (b_contract_plans.contract_plans_pttype = 'AD') then '74'
           when (b_contract_plans.contract_plans_pttype = 'AA') then '71'
           when (b_contract_plans.contract_plans_pttype = 'AE') then '75'
           when (b_contract_plans.contract_plans_pttype = 'AL') then '78'
           when (b_contract_plans.contract_plans_pttype = 'AF') then '76'
           when (b_contract_plans.contract_plans_pttype = 'AJ') then '82'
           when (b_contract_plans.contract_plans_pttype = 'AG') then '77'
           when (b_contract_plans.contract_plans_pttype = 'AC') then '73'
           when (b_contract_plans.contract_plans_pttype = 'AB') then '72'
           when (b_contract_plans.contract_plans_pttype = 'AK') then '81'
           when (b_contract_plans.contract_plans_pttype = 'UC') then '89'
           when (b_contract_plans.contract_plans_pttype = 'A2') then '02'
           when (b_contract_plans.contract_plans_pttype = 'A7') then '01'
           when (b_contract_plans.contract_plans_pttype = 'AR') then '07'
           when (b_contract_plans.contract_plans_pttype = 'AP') then '06'
           when (b_contract_plans.contract_plans_pttype = 'A9') then '78'
           when (b_contract_plans.contract_plans_pttype = 'A1') then '06'
           when (b_contract_plans.contract_plans_pttype = 'AM') then '78'
           when (b_contract_plans.contract_plans_pttype = 'AN') then '06'
        ELSE '78' END  AS INSTYPE 
	,  t_patient_payment.patient_payment_card_number AS INSID 
	, CASE WHEN (length(t_patient_payment.patient_payment_card_issue_date)>=10)
then to_char(to_date(to_number(
substr(t_patient_payment.patient_payment_card_issue_date,1,4),'9999')-543 || 
substr(t_patient_payment.patient_payment_card_issue_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END AS START 
	, CASE WHEN (length(t_patient_payment.patient_payment_card_expire_date)>=10)
then to_char(to_date(to_number(
substr(t_patient_payment.patient_payment_card_expire_date,1,4),'9999')-543 || 
substr(t_patient_payment.patient_payment_card_expire_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END AS EXPIR 
	, t_patient_payment.patient_payment_main_hospital AS MAIN 
	, t_patient_payment.patient_payment_sub_hospital AS SUB 
	, CASE WHEN (length(t_patient_payment.patient_payment_record_date)>=10)
then to_char(to_date(to_number(
substr(t_patient_payment.patient_payment_record_date,1,4),'9999')-543 || 
substr(t_patient_payment.patient_payment_record_date,5,6),'yyyy-mm-dd'),'yyyymmdd') 
                    ELSE ''   END AS UPDATE 
from  t_visit 
    INNER JOIN t_patient ON t_visit.t_patient_id = t_patient.t_patient_id
    INNER JOIN t_health_family ON t_health_family.t_health_family_id = t_patient.t_health_family_id
        INNER JOIN t_health_home  ON t_health_family.t_health_home_id = t_health_home.t_health_home_id
        INNER JOIN t_health_village  ON  t_health_home.t_health_village_id = t_health_village.t_health_village_id
        INNER JOIN t_patient_payment ON t_patient_payment.t_health_family_id = t_health_family.t_health_family_id
        INNER join  b_contract_plans on t_patient_payment.b_contact_plans_id = b_contract_plans.b_contract_plans_id    
        ,b_site

where t_health_village.village_moo = '0'      
    AND health_family_active = '1'
    and substring(t_visit.visit_begin_visit_time,1,10) >= ?
    and substring(t_visit.visit_begin_visit_time,1,10) <= ?
