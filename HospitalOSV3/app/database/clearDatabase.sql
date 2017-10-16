delete from t_visit;
--delete from b_auto_payment;
delete from t_accident;
delete from t_billing;
delete from t_billing_invoice;
delete from t_billing_invoice_billing_subgroup;
delete from t_billing_invoice_item;
delete from t_billing_receipt;
delete from t_billing_receipt_billing_subgroup;
delete from t_billing_receipt_item;
delete from t_chronic;
delete from t_death;
delete from t_diag_icd10;
delete from t_diag_icd9;
delete from t_diag_icd9_participate_or;
delete from t_drugfund;
delete from t_drugfund_billing;
delete from t_drugfund_billing_item;
delete from t_drugfund_billing_receipt;
delete from t_drugfund_billing_receipt_item;
delete from t_drugfund_order_item;
delete from t_drugfund_visit;
delete from t_lab_refer_in_order;
delete from t_lab_refer_in_order_result;
delete from t_lab_refer_in_patient;
delete from t_lab_refer_in_visit;
delete from t_lab_refer_out_order;
delete from t_lab_refer_out_order_form;
delete from t_order;
delete from t_order_continue;
delete from t_order_drug;
delete from t_order_drug_return;
---delete from t_patient;
delete from t_patient_appointment;
delete from t_patient_drug_allergy;
delete from t_patient_payment;
delete from t_result_lab;
delete from t_result_xray;
delete from t_result_xray_position;
delete from t_result_xray_size;
delete from t_surveil;
delete from t_visit_diag_map;
delete from t_visit_discharge_advice;
delete from t_visit_payment;
delete from t_visit_physical_exam;
delete from t_visit_primary_symptom;
delete from t_visit_queue_coding;
delete from t_visit_queue_despense;
delete from t_visit_queue_lab;
delete from t_visit_queue_map;
delete from t_visit_queue_transfer;
delete from t_visit_queue_xray;
delete from t_visit_refer_in_out;
delete from t_visit_reverse_admit;
delete from t_visit_service;
delete from t_visit_vital_sign;

update b_visit_queue_setup set visit_queue_setup_queue = '1' ;
update b_sequence_data set sequence_data_value = '1' where b_sequence_data_id ='rn';
update b_sequence_data set sequence_data_value = '1' where b_sequence_data_id ='hn';
update b_sequence_data set sequence_data_value = '1' where b_sequence_data_id ='an';
update b_sequence_data set sequence_data_value = '1' where b_sequence_data_id ='vn';
update b_sequence_data set sequence_data_value = '1' where b_sequence_data_id ='xn';
update b_sequence_data set sequence_data_value = '1' where b_sequence_data_id ='dfn';

-----3216544854345	3	คิวความดัน	r=204,g=255,b=51	1	1
-----2224247153539	2	คิวเบาหวาน	r=255,g=0,b=0	1	1
-----2226142006880	5	คิว ทันตกรรม	r=153,g=204,b=255	1	1
-----2227229376560	4	คิว Lab	r=0,g=153,b=102	1	1
-----2226942598177	7	คิวบริการปรึกษา	r=204,g=153,b=255	1	1
-----2220317811881	6	คิว PCU	r=255,g=102,b=153	1	1
-----3217538747849	1	คิว OPD	r=255,g=255,b=0	1	1
UPDATE b_visit_queue_setup  SET visit_queue_setup_queue = '1' where visit_queue_setup_number = '1';
UPDATE b_visit_queue_setup  SET visit_queue_setup_queue = '1' where visit_queue_setup_number = '2';
UPDATE b_visit_queue_setup  SET visit_queue_setup_queue = '1' where visit_queue_setup_number = '3';
UPDATE b_visit_queue_setup  SET visit_queue_setup_queue = '1' where visit_queue_setup_number = '4';
UPDATE b_visit_queue_setup  SET visit_queue_setup_queue = '1' where visit_queue_setup_number = '5';
UPDATE b_visit_queue_setup  SET visit_queue_setup_queue = '1' where visit_queue_setup_number = '6';
UPDATE b_visit_queue_setup  SET visit_queue_setup_queue = '1' where visit_queue_setup_number = '7';
