----------------------------------------------------------Stock_Hosv3----22-03-2550--------------------------------------------------------------
CREATE TABLE s_stock_version (
    s_stock_version_id character varying(255) NOT NULL,
    stock_version_number character varying(255) DEFAULT ''::character varying,
    stock_version_description character varying(255) DEFAULT ''::character varying,
    stock_version_application_number character varying(255) DEFAULT ''::character varying,
    stock_version_database_number character varying(255) DEFAULT ''::character varying,
    stock_version_update_time character varying(255) DEFAULT ''::character varying
);
ALTER TABLE ONLY s_stock_version
    ADD CONSTRAINT s_stock_version_pkey PRIMARY KEY (s_stock_version_id);

CREATE UNIQUE INDEX st_version_index ON s_stock_version USING btree (s_stock_version_id);
INSERT INTO s_stock_version VALUES ('st00100000001', '01', 'Stock Module', '1.0.090307', '1.0.090307', '2550-03-09 18:00:00');
INSERT INTO s_stock_version VALUES ('st00100000002', '02', 'Stock Module', '1.0.220307', '1.0.090307', '2550-03-22 13:00:00');

----------------------------------------- iMed-1.10.0-RC1 -------------------------------------------------
-- ????? seq ?????????? Running ???????
INSERT INTO imed.seq_receipt_number VALUES('700', '00', '0.0.0.0', '0', '0', '0', '0', '70', '');
INSERT INTO imed.seq_receipt_number VALUES('701', '00', '0.0.0.0', '0', '0', '0', '1', '70', '');

-----------------------------------------------------------
-- ?????? 18 ??. 2549
-- Modifier Pook
-- iMed1.10.0 MNH-Billing-13 ??????? Order ???????? max-min ???????????????????? 
-----------------------------------------------------------
--ALTER TABLE imed.item ADD max_price VARCHAR(255);

-----------------------------------------------------------
-- ?????? 24 ??. 2549
-- Modifier Rung
-- iMed1.10.0 MNH-Lab-2 ?????????????????????????????
-----------------------------------------------------------
--ALTER TABLE imed.order_item RENAME receive_specimen TO keep_specimen_eid;
--ALTER TABLE imed.assign_lab ADD receive_all_specimen VARCHAR(255);
--UPDATE imed.assign_lab SET receive_all_specimen = '1' WHERE assign_order_status = '2' OR assign_order_status = '3' OR assign_order_status = '4';
--UPDATE imed.assign_lab SET receive_all_specimen = '0' WHERE assign_order_status = '1';

-----------------------------------------------------------
-- ?????? 29 ?.?. 2549
-- Modifier Pook
-- iMed1.10.0 MNH-OPD-3 Bug-1
-----------------------------------------------------------
ALTER TABLE imed.appointment ADD make_appointment_visit_id VARCHAR(255);

-----------------------------------------------------------
-- ?????? 4 ?.?. 50
-- Modifier Rung
-----------------------------------------------------------
CREATE TABLE imed.imed_version (
	imed_version_id VARCHAR(255) NOT NULL PRIMARY KEY
);
-- insert ???????? ear ?? UPDATE imed.imed.????????
-- INSERT INTO imed.imed_version VALUES('1.10.0'); 

---------------------------------- End iMed-1.10.0 ------------------------------------------

-----------------------------------------------------------
-- ?????? 29 ?.?. 2549
-- Modifier tune
-- iMed1.10.0 ????? field stock_card.receive_date,stock_card.receive_time
-----------------------------------------------------------
ALTER TABLE imed.stock_card ADD receive_date VARCHAR(255);
ALTER TABLE imed.stock_card ADD receive_time VARCHAR(255);
CREATE INDEX stk_card_receive_date ON imed.stock_card(receive_date);
CREATE INDEX stk_card_receive_time ON imed.stock_card(receive_time);
UPDATE imed.stock_card set receive_date = update_date,receive_time = update_time;

-----------------------------------------------------------
-- ?????? 8 ?.?. 2550
-- Modifier tune
-- iMed1.10.0 ????? ????? stock_exchange, stock_exchange_detail ?????????????????????
-----------------------------------------------------------
CREATE TABLE imed.stock_exchange(
stock_exchange_id VARCHAR(255) NOT NULL PRIMARY KEY,
exchange_from_stock_id VARCHAR(255),
exchange_to_distributor_id VARCHAR(255),
exchange_number VARCHAR(255),
exchange_eid VARCHAR(255),
exchange_date VARCHAR(255),
exchange_time VARCHAR(255),
item_id VARCHAR(255),
lot_number VARCHAR(255),
exchange_qty VARCHAR(255),
exchange_note VARCHAR(255),
fix_stock_exchange_status_id VARCHAR(255)
);
CREATE INDEX stk_ex_stock_exchange_id ON imed.stock_exchange(stock_exchange_id);
CREATE INDEX stk_ex_exchange_from_stock_id ON imed.stock_exchange(exchange_from_stock_id);
CREATE INDEX stk_ex_exchange_to_distributor_id ON imed.stock_exchange(exchange_to_distributor_id);
CREATE INDEX stk_ex_exchange_number ON imed.stock_exchange(exchange_number);
CREATE INDEX stk_ex_exchange_eid ON imed.stock_exchange(exchange_eid);
CREATE INDEX stk_ex_exchange_date ON imed.stock_exchange(exchange_date);
CREATE INDEX stk_ex_exchange_time ON imed.stock_exchange(exchange_time);
CREATE INDEX stk_ex_item_id ON imed.stock_exchange(item_id);
CREATE INDEX stk_ex_lot_number ON imed.stock_exchange(lot_number);
CREATE INDEX stk_ex_fix_stock_exchange_status_id ON imed.stock_exchange(fix_stock_exchange_status_id);


CREATE TABLE imed.stock_exchange_detail(
stock_exchange_detail_id VARCHAR(255) NOT NULL PRIMARY KEY,
stock_exchange_id VARCHAR(255),
item_id VARCHAR(255),
lot_number VARCHAR(255),
expire_date VARCHAR(255),
big_unit_id VARCHAR(255),
small_unit_id VARCHAR(255),
mid_unit_rate VARCHAR(255),
unit_rate VARCHAR(255),
qty VARCHAR(255),
comment VARCHAR(255)
);

CREATE INDEX stk_ex_de_stock_exchange_detail_id ON imed.stock_exchange_detail(stock_exchange_detail_id);
CREATE INDEX stk_ex_de_stock_exchange_id ON imed.stock_exchange_detail(stock_exchange_id);
CREATE INDEX stk_ex_de_item_id ON imed.stock_exchange_detail(item_id);

-----------------------------------------------------------
-- ?????? 8 ?.?. 2550
-- Modifier tune
-- iMed1.10.0 ????? field stock.seq_exchange_number
-----------------------------------------------------------
ALTER TABLE imed.stock ADD seq_exchange_number VARCHAR(255);

-----------------------------------------------------------
-- ?????? 9 ?.?. 2550
-- Modifier Pook
-- iMed1.10.0 -- BT-MNH-10 ????? field order_drug_cursor ???????????????????????? 
-----------------------------------------------------------
ALTER TABLE imed.employee_type_default ADD order_drug_cursor VARCHAR(255);

-----------------------------------------------------------
-- ?????? 10 ?.?. 2550
-- Modifier Pook
-- iMed1.10.0 -- BT-MNH-14 ?????????????????????????????????
-- ????? field order_item.dose_note ?????????? Dose ?????????????????
-----------------------------------------------------------
ALTER TABLE imed.order_item ADD dose_note VARCHAR(255);

-----------------------------------------------------------
-- ?????? 16 ?.?. 2550
-- Modifier tune
-- iMed1.10.0 
-- ????? field stock_auth.make_exchange 
-----------------------------------------------------------
ALTER TABLE imed.stock_auth ADD make_exchange VARCHAR(255);


-----------------------------------------------------------
-- ?????? 17 ?.?. 2550
-- Modifier Pook
-- iMed1.10.0 
-- ????? field employee.active
-----------------------------------------------------------
ALTER TABLE imed.employee ADD active VARCHAR(255);
UPDATE imed.employee SET active = '1';

-----------------------------------------------------------
-- ?????? 18 ?.?. 2550
-- Modifier Pook
-- iMed1.10.0 
-- ????? field employee_role.base_employee_role_id
-----------------------------------------------------------
ALTER TABLE imed.employee_role ADD base_employee_role_id VARCHAR(255);


-----------------------------------------------------------
-- ?????? 19 ?.?. 2550
-- Modifier tune
-- iMed1.10.0 ????? field stock.seq_print_receive_order_number
-----------------------------------------------------------
ALTER TABLE imed.stock ADD seq_print_receive_order_number VARCHAR(255);
ALTER TABLE imed.stock_card ADD receive_order_number VARCHAR(255);
CREATE INDEX stk_receive_order_number ON imed.stock_card(receive_order_number);


-----------------------------------------------------------
-- ?????? 23 ?.?. 2550
-- Modifier tune
-- iMed1.10.0 ????? field distributor.active,manufacturer.active
-----------------------------------------------------------
ALTER TABLE imed.distributor ADD active VARCHAR(255);
ALTER TABLE imed.manufacturer ADD active VARCHAR(255);
UPDATE imed.distributor set active ='1';
UPDATE imed.manufacturer set active ='1';

-----------------------------------------------------------
-- ?????? 10 ?.?. 2550
-- Modifier tune
-- iMed1.10.0 ????? field stock_card.update_date_time,stock_order.purchasing_offer_date_time,stock_request.req_date_time
-----------------------------------------------------------

ALTER TABLE imed.stock_card ADD update_date_time VARCHAR(255);
CREATE INDEX stk_card_update_date_time ON imed.stock_card(update_date_time);



ALTER TABLE imed.stock_order ADD purchasing_offer_date_time VARCHAR(255);
CREATE INDEX stk_order_purchasing_offer_date_time ON imed.stock_order(purchasing_offer_date_time);


ALTER TABLE imed.stock_request ADD req_date_time VARCHAR(255);
CREATE INDEX stk_request_req_date_time ON imed.stock_request(req_date_time);



UPDATE imed.stock_card set update_date_time =update_date||update_time;
UPDATE imed.stock_order set purchasing_offer_date_time =purchasing_offer_date||purchasing_offer_time;
UPDATE imed.stock_request set req_date_time =req_date||req_time;
UPDATE imed.stock_mgnt set cost_purchase_no_discount = cost_purchase;

-----------------------------------------------------------
-- ?????? 26 ?.?. 2550
-- Modifier george
-- iMed-1.10-BD-330 default ???????????????????????
-----------------------------------------------------------
ALTER TABLE imed.template_phyex_detail ADD phyex_result VARCHAR(255);

---------------------------------- End iMed-1.10.2 01/02/2007------------------------------------------



-----------------------------------------------------------
-- ?????? 02 ?.?. 2550
-- Modifier Pook
-- iMed-1.10 BT-WPT-276 ?????????????????????????????????????????????
-----------------------------------------------------------
CREATE TABLE imed.base_cancel_appointment_reason (
	base_cancel_appointment_reason_id VARCHAR(255) NOT NULL PRIMARY KEY,
	description VARCHAR(255)
);

-----------------------------------------------------------
-- ?????? 02 ?.?. 2550
-- Modifier Pook
-- iMed-1.10 BT-WPT-276 
-----------------------------------------------------------
ALTER TABLE imed.appointment ADD base_cancel_appointment_reason_id VARCHAR(255);


-----------------------------------------------------------
-- ?????? 05 ?.?. 2550
-- Modifier Eddies
-- iMed-1.10 BT-WPT-252 
-----------------------------------------------------------
UPDATE imed.base_service_point SET setup_queue_column = setup_queue_column||'0';

-----------------------------------------------------------
-- ?????? 06 ?.?. 2550
-- Modifier Pook
-- iMed-1.10 BT-WPT-278 ????? field ????????????????????????? request lab
-----------------------------------------------------------
ALTER TABLE imed.base_lab_type ADD print_order VARCHAR(255);

-----------------------------------------------------------
-- ?????? 09 ?.?. 2550
-- Modifier Pook
-- iMed-1.10 BT-WPT--50[3] ??????????????????????????????????????????/??????????
-----------------------------------------------------------
CREATE TABLE imed.base_cancel_receipt_reason (
	base_cancel_receipt_reason_id VARCHAR(255) NOT NULL PRIMARY KEY,
	description VARCHAR(255)
);

ALTER TABLE imed.receipt ADD base_cancel_receipt_reason_id VARCHAR(255);

-----------------------------------------------------------
-- ?????? 14 ?.?. 2550
-- Modifier Pook
-- iMed-1.10 BT-VR-52 ???????? OPD Card ??????? ?????????????????????????? 5 ??
-----------------------------------------------------------
ALTER TABLE imed.patient ADD manage_file_eid VARCHAR(255);
ALTER TABLE imed.patient ADD manage_file_date VARCHAR(255);
ALTER TABLE imed.patient ADD manage_file_time VARCHAR(255);


-----------------------------------------------------------
-- ?????? 8 ??.?. 2550
-- Modifier rung
-- BT-KDH-134 ????????????????????????? ICD10,9
-----------------------------------------------------------
---------------------*********** Optional ?????? site ??? site ?????? WPT **********************----------------------------
CREATE TABLE imed.temp_diagnosis_icd10 AS SELECT * FROM diagnosis_icd10;
ALTER TABLE imed.diagnosis_icd10 DROP COLUMN beginning_diagnosis;
ALTER TABLE imed.diagnosis_icd10 DROP COLUMN beginning_diagnosis_th;
ALTER TABLE imed.diagnosis_icd10 ADD beginning_diagnosis VARCHAR(255);
ALTER TABLE imed.diagnosis_icd10 ADD beginning_diagnosis_th VARCHAR(255);
UPDATE imed.diagnosis_icd10 SET beginning_diagnosis = temp_diagnosis_icd10.beginning_diagnosis, beginning_diagnosis_th = temp_diagnosis_icd10.beginning_diagnosis_th WHERE diagnosis_icd10.diagnosis_icd10_id = temp_diagnosis_icd10.diagnosis_icd10_id;
DROP TABLE imed.temp_diagnosis_icd10;

CREATE TABLE imed.tmp_doctor_diagnosis (
	doctor_diagnosis_id VARCHAR(255) NOT NULL PRIMARY KEY,
	visit_id VARCHAR(255),
	beginning_diagnosis VARCHAR(255),
	beginning_diagnosis_th VARCHAR(255),
	note TEXT,
	secret_status VARCHAR(255) DEFAULT '0',
	doctor_eid VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
INSERT INTO imed.tmp_doctor_diagnosis (SELECT * from doctor_diagnosis WHERE doctor_diagnosis_id NOT IN (SELECT diagnosis_icd10_id FROM diagnosis_icd10) AND beginning_diagnosis IS NOT NULL AND beginning_diagnosis <> '');
INSERT INTO imed.diagnosis_icd10 (SELECT doctor_diagnosis_id, visit_id, '', '', '', doctor_eid, '', modify_date, modify_time, note, modify_eid, modify_date, modify_time, beginning_diagnosis, beginning_diagnosis_th FROM tmp_doctor_diagnosis);
DROP TABLE imed.tmp_doctor_diagnosis;
----------------------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------
-- ?????? 16 ??.?. 2550
-- Modifier Rung
-- iMed1.10.0 ????? index ???????????? ?????????????????????? query ???
-----------------------------------------------------------
CREATE INDEX appointment_appoint_date ON imed.appointment(appoint_date);
CREATE INDEX appointment_appoint_time ON imed.appointment(appoint_time);

--------------------------- END iMed-1.10.3 ----------------------------------------------

---------------------------------------------------------------------------------------
--********************** ???????????????? iMed-1.11 *********************************--
--************************** ?????????? ???????? ************************************--
---------------------------------------------------------------------------------------

-----------------------------------------------------------
-- ?????? 19 ?.?. 2550
-- Modifier tune
-- iMed1.10.0 ????? ????? stock_setup_order
-----------------------------------------------------------
CREATE TABLE imed.stock_setup_order(
stock_setup_order_id VARCHAR(255) NOT NULL PRIMARY KEY,
item_id VARCHAR(255),
distributor_id VARCHAR(255),
manufacturer_id VARCHAR(255),
active VARCHAR(255)
);
CREATE INDEX stk_setup_order_item_id ON imed.stock_setup_order(item_id);
CREATE INDEX stk_setup_order_distributor_id ON imed.stock_setup_order(distributor_id);
CREATE INDEX stk_setup_order_manufacturer_id ON imed.stock_setup_order(manufacturer_id);
CREATE INDEX stk_setup_order_active ON imed.stock_setup_order(active);

-----------------------------------------------------------
-- ?????? 19 ?.?. 2550
-- Modifier tune
-- iMed1.10.0 ????? ????? stock_setup_order
-----------------------------------------------------------
CREATE TABLE imed.stock_setup_order_detail(
stock_setup_order_detail_id VARCHAR(255) NOT NULL PRIMARY KEY,
stock_setup_order_id VARCHAR(255),
big_unit_id VARCHAR(255),
small_unit_id VARCHAR(255),
mid_unit_rate VARCHAR(255),
unit_rate VARCHAR(255),
vat VARCHAR(255),
fix_tax_type_id VARCHAR(255),
order_unit_price VARCHAR(255),
discount VARCHAR(255),
discount_percent VARCHAR(255),
order_qty VARCHAR(255),
free_qty VARCHAR(255),
active_date VARCHAR(255),
modify_date VARCHAR(255),
modify_time VARCHAR(255),
modify_eid VARCHAR(255)
);
CREATE INDEX stk_setup_order_detail_setup_order_id ON imed.stock_setup_order_detail(stock_setup_order_id);
CREATE INDEX stk_setup_order_big_unit_id ON imed.stock_setup_order_detail(big_unit_id);
CREATE INDEX stk_setup_order_small_unit_id ON imed.stock_setup_order_detail(small_unit_id);
CREATE INDEX stk_setup_order_active_date ON imed.stock_setup_order_detail(active_date);
CREATE INDEX stk_setup_order_modify_date ON imed.stock_setup_order_detail(modify_date);
CREATE INDEX stk_setup_order_modify_time ON imed.stock_setup_order_detail(modify_time);
CREATE INDEX stk_setup_order_modify_eid ON imed.stock_setup_order_detail(modify_eid);

-----------------------------------------------------------
-- ?????? 19 ?.?. 2550
-- Modifier tune
-- iMed1.10.0 ????? ????? stock_request_order
-----------------------------------------------------------
CREATE TABLE imed.stock_request_order(
stock_request_order_id VARCHAR(255) NOT NULL PRIMARY KEY,
stock_request_order_number VARCHAR(255),
stock_id VARCHAR(255),
distributor_id VARCHAR(255),
manufacturer_id VARCHAR(255),
item_id VARCHAR(255),
big_unit_id VARCHAR(255),
small_unit_id VARCHAR(255),
mid_unit_rate VARCHAR(255),
unit_rate VARCHAR(255),
suggest_order_qty VARCHAR(255),
fix_order_qty VARCHAR(255),
fix_free_order_qty VARCHAR(255),
req_order_qty VARCHAR(255),
free_req_order_qty VARCHAR(255),
req_order_date_time VARCHAR(255),
req_order_eid VARCHAR(255),
fix_req_order_status VARCHAR(255),
comment VARCHAR(255) 
);
CREATE INDEX stk_req_order_distributor_id ON imed.stock_request_order(distributor_id);
CREATE INDEX stk_req_order_manufacturer_id ON imed.stock_request_order(manufacturer_id);
CREATE INDEX stk_req_order_item_id ON imed.stock_request_order(item_id);
CREATE INDEX stk_req_order_date_time ON imed.stock_request_order(req_order_date_time);

-----------------------------------------------------------
-- ?????? 23 ?.?. 2550
-- Modifier tune
-- iMed1.10.0 ????? item.is_bidding,item.bidder_id,item.quota_qty,item.quota_unit_id
-----------------------------------------------------------
ALTER TABLE imed.item ADD is_bidding VARCHAR(255);
ALTER TABLE imed.item ADD bidder_id VARCHAR(255);
ALTER TABLE imed.item ADD quota_qty VARCHAR(255);
ALTER TABLE imed.item ADD quota_unit_id VARCHAR(255);

ALTER TABLE imed.stock RENAME COLUMN seq_pr_number TO seq_po_number;
ALTER TABLE imed.stock ADD seq_pr_number VARCHAR(255);
ALTER TABLE imed.stock_order ADD purchasing_request_number VARCHAR(255);
CREATE INDEX stk_prequest_number ON imed.stock_order(purchasing_request_number);

-----------------------------------------------------------
-- ?????? 7 ??.?. 2550
-- Modifier tune
-- iMed1.10.0 ????? ??????????? field stock_order.comments ???? stock_order.purchasing_offer_notem ???????? field order_comments,stock_free_item.comments
-----------------------------------------------------------

ALTER TABLE imed.stock_order RENAME COLUMN comments TO purchasing_offer_note;
ALTER TABLE imed.stock_order ADD order_comments VARCHAR(255);
ALTER TABLE imed.stock_order ADD order_not_received_comments VARCHAR(255);
ALTER TABLE imed.stock_free_item ADD comments VARCHAR(255);

-----------------------------------------------------------
-- ?????? 7 ??.?. 2550
-- Modifier tune
-- iMed1.10.0 field stock_card.order_item_id
-----------------------------------------------------------

ALTER TABLE imed.stock_card ADD order_item_id VARCHAR(255);
CREATE INDEX stk_card_order_item_id ON imed.stock_card(order_item_id);

-----------------------------------------------------------
-- ?????? 12 ??.?. 2550
-- Modifier tune
-- iMed1.10.0 field stock_mgnt.critical_quantity,stock_auth.make_request_order
-----------------------------------------------------------
ALTER TABLE imed.stock_mgnt ADD critical_quantity VARCHAR(255);
ALTER TABLE imed.stock_auth ADD make_request_order VARCHAR(255);

----------------------------------------------------------
-- ?????? 14 ??.?. 2550
-- Modifier Eddies
-- OR-03-???????????????????
-----------------------------------------------------------
CREATE TABLE imed.op_doctor_schedule (
	op_doctor_schedule_id VARCHAR(255) NOT NULL PRIMARY KEY,
	employee_id VARCHAR(255),
	fix_day_of_week VARCHAR(255),
	start_time VARCHAR(255),
	end_time VARCHAR(255)
);
CREATE INDEX op_doctor_schedule_employee_id ON imed.op_doctor_schedule(employee_id);

----------------------------------------------------------
-- ?????? 15 ??.?. 2550
-- Modifier Pook
-- OR-01-??????????????????? set ??????
-----------------------------------------------------------
CREATE TABLE imed.op_set_operation(
	op_set_operation_id VARCHAR(255) NOT NULL PRIMARY KEY,
	op_set_id VARCHAR(255),
	operation_name VARCHAR(255),
	operation_position VARCHAR(255),
	op_registered_operation_id VARCHAR(255),
	re_operation VARCHAR(255)
);


-----------------------------------------------------------
-- ?????? 16 ??.?. 2550
-- Modifier Pook
-- OR-01-??????????????????????????????
-----------------------------------------------------------
CREATE TABLE imed.templete_operation_position(
	templete_operation_position_id VARCHAR(255) NOT NULL PRIMARY KEY,
	description VARCHAR(255),
	base_templete_operation_id VARCHAR(255)
);
CREATE INDEX position_base_templete_operation ON imed.templete_operation_position(base_templete_operation_id);

-----------------------------------------------------------
-- ?????? 02 ?.?. 2550
-- Modifier tune
-- iMed1.10.0 field stock_order.is_insert,stock_request_order.is_insert,stock_mgnt.is_not_update_stock_mgnt
-----------------------------------------------------------
ALTER TABLE imed.stock_order ADD is_insert VARCHAR(255);
ALTER TABLE imed.stock_request_order ADD is_insert VARCHAR(255);
ALTER TABLE imed.stock_mgnt ADD is_not_update_stock_mgnt VARCHAR(255);

---------------------------------------------Stock_Hosv3----24-04-2550------------------------------------------------
INSERT INTO s_stock_version VALUES ('st00100000003', '03', 'Stock Module', '1.0.240407', '1.0.240407', '2550-04-24 18:00:00');