CREATE TABLE b_stock (      
b_stock_id character varying(255) NOT NULL,  
stock_name character varying(255) DEFAULT ''::character varying,  
stock_pr_no character varying(255) DEFAULT ''::character varying,  
stock_request_no character varying(255) DEFAULT ''::character varying,  
stock_po_no character varying(255) DEFAULT ''::character varying,  
    PRIMARY KEY(b_stock_id)     
);     


CREATE TABLE t_stock_item (      
t_stock_item_id character varying(255) NOT NULL,  
b_stock_id character varying(255) DEFAULT ''::character varying,  
b_stock_distributor_id character varying(255) DEFAULT ''::character varying,  
b_item_id character varying(255) DEFAULT ''::character varying,  
stock_item_code character varying(255) DEFAULT ''::character varying,  
stock_item_name character varying(255) DEFAULT ''::character varying,  
b_stock_item_drug_type_id character varying(255) DEFAULT ''::character varying,
b_item_subgroup_id character varying(255) DEFAULT ''::character varying,
stock_item_safety_stock double precision,  
stock_item_safety_day double precision,  
stock_item_vat double precision,  
stock_item_previous_qty double precision,  
stock_item_cur_qty double precision,  
stock_item_max_qty double precision,  
stock_item_min_qty double precision,  
stock_item_max_suggest double precision,  
stock_item_min_suggest double precision,  
stock_item_max_day double precision,  
stock_item_min_day double precision,  
stock_item_use_rate_day double precision,  
stock_item_use_rate double precision,  
stock_item_update_date_time character varying(255) DEFAULT ''::character varying,
stock_item_update_eid character varying(255) DEFAULT ''::character varying,  
stock_item_order_uom_id character varying(255) DEFAULT ''::character varying,  
stock_item_dispense_uom_id character varying(255) DEFAULT ''::character varying,  
stock_item_mid_uom_id character varying(255) DEFAULT ''::character varying,  
stock_item_dispense_uom_rate double precision,    
stock_item_mid_uom_rate double precision,    
stock_item_receive_date_time character varying(255) DEFAULT ''::character varying,  
stock_item_receive_eid character varying(255) DEFAULT ''::character varying,  
stock_item_cancel_date_time character varying(255) DEFAULT ''::character varying,  
stock_item_cancel_eid character varying(255) DEFAULT ''::character varying,  
stock_item_active character varying(255) DEFAULT ''::character varying,  
stock_item_note character varying(255) DEFAULT ''::character varying,  
stock_item_barcode character varying(255) DEFAULT ''::character varying,  
stock_item_avg_cost double precision,    
stock_item_price_sale double precision,    
    PRIMARY KEY( t_stock_item_id)     
);     



CREATE TABLE t_stock_lot_item (        
t_stock_lot_item_id character varying(255) NOT NULL,    
t_stock_item_id character varying(255) DEFAULT ''::character varying,    
stock_lot_item_lot_no character varying(255) DEFAULT ''::character varying,    
stock_lot_item_lot_no_in character varying(255) DEFAULT ''::character varying,    
stock_lot_item_invoice_no character varying(255) DEFAULT ''::character varying,    
t_stock_other_id character varying(255) DEFAULT ''::character varying,    
t_stock_df_disp_id character varying(255) DEFAULT ''::character varying,    
t_stock_request_id character varying(255) DEFAULT ''::character varying,    
t_stock_request_item_id character varying(255) DEFAULT ''::character varying,    
t_stock_adjust_id character varying(255) DEFAULT ''::character varying,    
t_stock_order_id character varying(255) DEFAULT ''::character varying,    
t_stock_order_item_id character varying(255) DEFAULT ''::character varying,    
t_stock_pat_disp_id character varying(255) DEFAULT ''::character varying,    
b_stock_distributor_id character varying(255) DEFAULT ''::character varying,  
stock_lot_item_produce_date character varying(255) DEFAULT ''::character varying,  
stock_lot_item_expire_date character varying(255) DEFAULT ''::character varying,  
stock_lot_item_return_day double precision,
f_stock_method_id character varying(255) DEFAULT ''::character varying,  
f_stock_adjust_method_id character varying(255) DEFAULT ''::character varying,  
stock_lot_item_receive_date_time character varying(255) DEFAULT ''::character varying,  
stock_lot_item_receive_eid character varying(255) DEFAULT ''::character varying,  
stock_lot_item_update_date_time character varying(255) DEFAULT ''::character varying,  
stock_lot_item_update_eid character varying(255) DEFAULT ''::character varying,  
stock_lot_item_record_date_time character varying(255) DEFAULT ''::character varying,  
stock_lot_item_record_eid character varying(255) DEFAULT ''::character varying,  
stock_lot_item_previous_qty double precision,    
stock_lot_item_previous_qty_lot double precision,    
stock_lot_item_qty double precision,    
stock_lot_item_free_qty double precision,    
stock_lot_item_update_qty double precision,    
stock_lot_item_update_qty_lot double precision,    
stock_lot_item_mid_uom_cost double precision,     
stock_lot_item_order_uom_cost double precision,     
stock_lot_item_dispense_uom_cost double precision,     
stock_lot_item_discount double precision,     
stock_lot_item_discount_percent double precision,     
f_stock_order_status_id character varying(255) DEFAULT ''::character varying,   
stock_lot_item_active character varying(255) DEFAULT ''::character varying,   
stock_lot_item_order_uom_id character varying(255) DEFAULT ''::character varying,   
stock_lot_item_dispense_uom_id character varying(255) DEFAULT ''::character varying,   
stock_lot_item_mid_uom_id character varying(255) DEFAULT ''::character varying,   
stock_lot_item_dispense_uom_rate double precision,     
stock_lot_item_mid_uom_rate double precision,     
stock_lot_item_vat double precision,     
stock_lot_item_order_name character varying(255) DEFAULT ''::character varying,   
f_tax_type_id character varying(255) DEFAULT ''::character varying,   
stock_lot_item_note character varying(255) DEFAULT ''::character varying,   
f_stock_sub_method_id character varying(255) DEFAULT ''::character varying,   
    PRIMARY KEY(t_stock_lot_item_id)       
);       
       
CREATE TABLE t_stock_other (    
t_stock_other_id character varying(255) NOT NULL,
b_stock_id character varying(255) DEFAULT ''::character varying,
stock_other_dep_id character varying(255) DEFAULT ''::character varying,
stock_other_fund_code character varying(255) DEFAULT ''::character varying,
stock_other_fund_name character varying(255) DEFAULT ''::character varying,
f_tax_type_id character varying(255) DEFAULT ''::character varying,
stock_other_note character varying(255) DEFAULT ''::character varying,
stock_other_receive_eid character varying(255) DEFAULT ''::character varying,
stock_other_receive_date_time character varying(255) DEFAULT ''::character varying,
stock_other_active character varying(255) DEFAULT ''::character varying,
    PRIMARY KEY(t_stock_other_id)   
);   
   
       
CREATE TABLE t_stock_order (        
t_stock_order_id character varying(255) NOT NULL,    
stock_order_credit_on_pay character varying(255) DEFAULT ''::character varying,    
stock_order_req_no character varying(255) DEFAULT ''::character varying,    
stock_order_no character varying(255) DEFAULT ''::character varying,    
stock_order_purchasing_offer_eid character varying(255) DEFAULT ''::character varying,    
stock_order_purchasing_offer_date_time character varying(255) DEFAULT ''::character varying,    
b_stock_id character varying(255) DEFAULT ''::character varying,    
stock_order_purchasing_offer_dep character varying(255) DEFAULT ''::character varying,    
stock_order_purchasing_offer_person character varying(255) DEFAULT ''::character varying,    
stock_order_purchasing_offer_vat double precision,
stock_order_purchasing_offer_cause character varying(255) DEFAULT ''::character varying,    
stock_order_cash_limit double precision,
stock_order_needs_date character varying(255) DEFAULT ''::character varying,    
stock_order_payer_dep character varying(255) DEFAULT ''::character varying,    
stock_order_purchasing_offer_note character varying(255) DEFAULT ''::character varying,    
f_stock_order_status_id character varying(255) DEFAULT ''::character varying,    
stock_order_payer character varying(255) DEFAULT ''::character varying,    
stock_order_active character varying(255) DEFAULT ''::character varying,    
b_stock_distributor_id character varying(255) DEFAULT ''::character varying,    
    PRIMARY KEY(t_stock_order_id)       
);       
       
CREATE TABLE t_stock_order_item (        
t_stock_order_item_id character varying(255) NOT NULL,    
t_stock_order_id character varying(255) DEFAULT ''::character varying,    
t_stock_item_id character varying(255) DEFAULT ''::character varying,    
stock_order_item_qty double precision,      
stock_order_free_qty double precision,      
stock_order_uom_id character varying(255) DEFAULT ''::character varying,    
stock_order_offer_discount double precision,      
stock_order_offer_discount_percent double precision,      
stock_order_dispense_uom_rate double precision,  
stock_order_mid_uom_rate double precision,  
stock_order_comments character varying(255) DEFAULT ''::character varying,    
f_stock_order_status_id character varying(255) DEFAULT ''::character varying,    
stock_order_uom_price double precision,      
    PRIMARY KEY( t_stock_order_item_id)       
);       


CREATE TABLE t_stock_request (        
t_stock_request_id character varying(255) NOT NULL,    
stock_request_disp_id character varying(255) DEFAULT ''::character varying,    
stock_request_recv_id character varying(255) DEFAULT ''::character varying,    
stock_request_no character varying(255) DEFAULT ''::character varying,    
stock_request_sp_id character varying(255) DEFAULT ''::character varying,    
stock_request_cause character varying(255) DEFAULT ''::character varying,    
stock_request_disp_no character varying(255) DEFAULT ''::character varying,    
stock_request_eid character varying(255) DEFAULT ''::character varying,    
stock_request_cancel_eid character varying(255) DEFAULT ''::character varying,   
stock_request_date_time character varying(255) DEFAULT ''::character varying,   
stock_request_cancel_date_time character varying(255) DEFAULT ''::character varying,   
stock_request_active character varying(255) DEFAULT ''::character varying,   
    PRIMARY KEY( t_stock_request_id)      
);      
      
CREATE TABLE t_stock_request_item (       
t_stock_request_item_id character varying(255) DEFAULT ''::character varying,   
t_stock_request_id character varying(255) DEFAULT ''::character varying,   
t_stock_item_id character varying(255) DEFAULT ''::character varying,   
stock_request_uom_id character varying(255) DEFAULT ''::character varying,   
stock_request_uom_price double precision,     
stock_request_qty double precision,     
stock_request_cause character varying(255) DEFAULT ''::character varying,   
    PRIMARY KEY( t_stock_request_item_id)      
);       


CREATE TABLE t_stock_adjust (        
t_stock_adjust_id character varying(255) NOT NULL,    
b_stock_id character varying(255) DEFAULT ''::character varying,    
stock_adjust_eid character varying(255) DEFAULT ''::character varying,    
stock_adjust_date_time character varying(255) DEFAULT ''::character varying,    
stock_adjust_cancel_eid character varying(255) DEFAULT ''::character varying,    
stock_adjust_cancel_date_time character varying(255) DEFAULT ''::character varying,    
stock_adjust_active character varying(255) DEFAULT ''::character varying,    
stock_adjust_note character varying(255) DEFAULT ''::character varying,    
    PRIMARY KEY( t_stock_adjust_id)       
);       
       
       

CREATE TABLE t_stock_patient_dispense (        
t_stock_patient_dispense_id character varying(255) NOT NULL,    
b_stock_id character varying(255) DEFAULT ''::character varying,    
t_order_id character varying(255) DEFAULT ''::character varying,    
t_order_drug_return_id character varying(255) DEFAULT ''::character varying,    
stock_patient_dispense_eid character varying(255) DEFAULT ''::character varying,    
stock_patient_cancel_dispense_eid character varying(255) DEFAULT ''::character varying,    
stock_patient_dispense_return_eid character varying(255) DEFAULT ''::character varying,    
stock_patient_dispense_qty double precision,      
stock_patient_dispense_return_qty double precision, 
stock_patient_dispense_date_time character varying(255) DEFAULT ''::character varying,    
stock_patient_cancel_dispense_date_time character varying(255) DEFAULT ''::character varying,    
stock_patient_dispense_return_date_time character varying(255) DEFAULT ''::character varying,    
stock_patient_active character varying(255) DEFAULT ''::character varying,    
stock_patient_dispense_note character varying(255) DEFAULT ''::character varying,    
    PRIMARY KEY( t_stock_patient_dispense_id)       
);       
       
CREATE TABLE t_stock_drugfund_dispense (    
t_stock_drugfund_dispense_id character varying(255) NOT NULL,
b_stock_id character varying(255) DEFAULT ''::character varying,
t_drugfund_order_item_id character varying(255) DEFAULT ''::character varying,
stock_drugfund_dispense_eid character varying(255) DEFAULT ''::character varying,
stock_drugfund_cancel_dispense_eid character varying(255) DEFAULT ''::character varying,
stock_drugfund_dispense_qty double precision,  
stock_drugfund_dispense_date_time character varying(255) DEFAULT ''::character varying,
stock_drugfund_cancel_dispense_date_time character varying(255) DEFAULT ''::character varying,
stock_drugfund_active character varying(255) DEFAULT ''::character varying,
stock_drugfund_dispense_note character varying(255) DEFAULT ''::character varying,
    PRIMARY KEY(t_stock_drugfund_dispense_id)   
);   
  
       
CREATE TABLE b_stock_department (        
b_stock_department_id character varying(255) NOT NULL,    
stock_department_no character varying(255) DEFAULT ''::character varying,    
stock_department_name character varying(255) DEFAULT ''::character varying,    
stock_department_detail character varying(255) DEFAULT ''::character varying,    
stock_department_active character varying(255) DEFAULT ''::character varying,    
stock_department_record_date_time character varying(255) DEFAULT ''::character varying,    
stock_department_record_eid character varying(255) DEFAULT ''::character varying,    
stock_department_update_date_time character varying(255) DEFAULT ''::character varying,    
stock_department_update_eid character varying(255) DEFAULT ''::character varying,    
stock_department_cancel_date_time character varying(255) DEFAULT ''::character varying,    
stock_department_cancel_eid character varying(255) DEFAULT ''::character varying,    
    PRIMARY KEY(b_stock_department_id)       
);       
       
       
CREATE TABLE b_stock_option (        
b_stock_option_id character varying(255) NOT NULL,    
stock_option_description character varying(255) DEFAULT ''::character varying,    
stock_option_enable character varying(255) DEFAULT ''::character varying,    
stock_option_addition character varying(255) DEFAULT ''::character varying,    
stock_option_record_date_time character varying(255) DEFAULT ''::character varying,    
stock_option_record_eid character varying(255) DEFAULT ''::character varying,    
stock_option_update_date_time character varying(255) DEFAULT ''::character varying,    
stock_option_update_eid character varying(255) DEFAULT ''::character varying,    
    PRIMARY KEY(b_stock_option_id)       
);       
       
       
CREATE TABLE b_stock_user (        
b_stock_user_id character varying(255) NOT NULL,    
b_employee_id character varying(255) DEFAULT ''::character varying,    
stock_user_fname character varying(255) DEFAULT ''::character varying,    
stock_user_lname character varying(255) DEFAULT ''::character varying,    
stock_user_comment character varying(255) DEFAULT ''::character varying,    
stock_user_record_date_time character varying(255) DEFAULT ''::character varying,    
stock_user_record_eid character varying(255) DEFAULT ''::character varying,    
stock_user_update_date_time character varying(255) DEFAULT ''::character varying,    
stock_user_update_eid character varying(255) DEFAULT ''::character varying,    
stock_user_cancel_date_time character varying(255) DEFAULT ''::character varying,    
stock_user_cancel_eid character varying(255) DEFAULT ''::character varying,    
stock_user_active character varying(255) DEFAULT ''::character varying,    
    PRIMARY KEY(b_stock_user_id)       
);       


       
       
CREATE TABLE b_stock_distributor (        
b_stock_distributor_id character varying(255) NOT NULL,    
stock_distributor_code character varying(255) DEFAULT ''::character varying,    
stock_distributor_name character varying(255) DEFAULT ''::character varying,    
stock_distributor_member_no character varying(255) DEFAULT ''::character varying,    
stock_distributor_taxpayer_no character varying(255) DEFAULT ''::character varying,    
stock_distributor_pay_type character varying(255) DEFAULT ''::character varying,    
stock_distributor_credit_on_pay character varying(255) DEFAULT ''::character varying,    
stock_distributor_address character varying(255) DEFAULT ''::character varying,    
stock_distributor_office_tel character varying(255) DEFAULT ''::character varying,    
stock_distributor_office_fax character varying(255) DEFAULT ''::character varying,    
stock_distributor_mail character varying(255) DEFAULT ''::character varying,    
stock_distributor_contact_person character varying(255) DEFAULT ''::character varying,    
stock_distributor_deliver_day character varying(255) DEFAULT ''::character varying,    
stock_distributor_discount_percent character varying(255) DEFAULT ''::character varying,    
stock_distributor_comment character varying(255) DEFAULT ''::character varying,    
stock_distributor_active character varying(255) DEFAULT ''::character varying,    
stock_distributor_record_date_time character varying(255) DEFAULT ''::character varying,    
stock_distributor_record_eid character varying(255) DEFAULT ''::character varying,    
stock_distributor_update_date_time character varying(255) DEFAULT ''::character varying,    
stock_distributor_update_eid character varying(255) DEFAULT ''::character varying,    
stock_distributor_cancel_date_time character varying(255) DEFAULT ''::character varying,    
stock_distributor_cancel_eid character varying(255) DEFAULT ''::character varying,    
    PRIMARY KEY(b_stock_distributor_id)       
);       
       
       
       
       
CREATE TABLE b_stock_item_repack (        
b_stock_item_repack_id character varying(255) NOT NULL,    
stock_item_src_id character varying(255) DEFAULT ''::character varying,    
stock_item_dest_id character varying(255) DEFAULT ''::character varying,    
stock_item_rate double precision,      
stock_item_repack_active character varying(255) DEFAULT ''::character varying,    
stock_item_repack_record_date_time character varying(255) DEFAULT ''::character varying,    
stock_item_repack_record_eid character varying(255) DEFAULT ''::character varying,    
stock_item_repack_update_date_time character varying(255) DEFAULT ''::character varying,    
stock_item_repack_update_eid character varying(255) DEFAULT ''::character varying,    
stock_item_repack_cancel_date_time character varying(255) DEFAULT ''::character varying,    
stock_item_repack_cancel_eid character varying(255) DEFAULT ''::character varying,    
    PRIMARY KEY(b_stock_item_repack_id)       
);       


       
CREATE TABLE f_stock_gui_action (        
f_stock_gui_action_id character varying(255) NOT NULL,    
stock_gui_action_name character varying(255) DEFAULT ''::character varying,    
stock_gui_action_note character varying(255) DEFAULT ''::character varying,    
    PRIMARY KEY(f_stock_gui_action_id)       
);       

DELETE FROM f_stock_gui_action;
COPY f_stock_gui_action (f_stock_gui_action_id, stock_gui_action_name, stock_gui_action_note) FROM stdin;
01000	เมนูการตั้งค่าการใช้งานระบบคลัง	
02000	เมนูการจัดการระบบคลัง	
03000	เมนูรายงานของระบบคลัง	
04000	แถบรายการตรวจรักษา	
05000	เมนูการรับคืนยาผู้ป่วยใน	
06000	เมนูกองทุนยา	
01100	เมนูผู้ใช้งานและสิทธิการใช้งานระบบคลัง	
01200	เมนูรายการยาและเวชภัณฑ์ในคลัง	
01300	เมนูการตั้งค่าระบบคลัง	
01400	เมนูคลังยาและเวชภัณฑ์	
01500	เมนูผู้แทนจำหน่าย	
01600	เมนูหน่วยงาน	
01700	เมนูประเภทยา	
02100	เมนูการจัดการของในคลัง	
02200	เมนูการปรับยอด	
02300	เมนูการซื้อและรับเข้าจากการซื้อ	
02400	เมนูการเบิกและรับเข้าจากการเบิก	
02500	เมนูการรับเข้าอื่นๆ	
03100	รายงานการจ่ายออกยาและเวชภัณฑ์	
03200	รายงานการรับเข้ายาและเวชภัณฑ์	
03300	รายงานยาและเวชภัณฑ์ใกล้หมดอายุ	
03400	รายงานสรุปมูลค่าและจำนวนยาและเวชภัณฑ์คงคลัง	
03500	รายงานรายการยาและเวชภัณฑ์ที่ไม่เคลื่อนไหว	
03600	รายงานการปรับยอดยาและเวชภัณฑ์	
03700	รายงานบันทึกรายละเอียดการสั่งซื้อยาและเวชภัณฑ์	
03800	รายงานประวัติการสั่งซื้อยาและเวชภัณฑ์	
03900	รายงานรับเข้า/จ่ายออก/ปรับยอด/คงเหลือ ของยาและเวชภัณฑ์	
04100	ปุ่มจ่าย	
04200	ปุ่มยกเลิก	
05100	ปุ่มบันทึก	
06100	ปุ่มคิดเงิน	
06200	ปุ่มยกเลิกการคิดเงิน  	
\.

CREATE TABLE b_stock_gui_action_authen (        
b_stock_gui_action_authen_id character varying(255) NOT NULL,    
b_stock_id character varying(255) DEFAULT ''::character varying,    
b_stock_user_id character varying(255) DEFAULT ''::character varying,    
f_stock_gui_action_id character varying(255) DEFAULT ''::character varying,    
stock_gui_action_name character varying(255) DEFAULT ''::character varying,
f_stock_authentication_id character varying(255) DEFAULT ''::character varying,
gui_action_authen_authentication_name character varying(255) DEFAULT ''::character varying,
gui_action_authen_is_read character varying(255) DEFAULT ''::character varying,    
gui_action_authen_is_write character varying(255) DEFAULT ''::character varying,    
gui_action_authen_note character varying(255) DEFAULT ''::character varying,    
    PRIMARY KEY(b_stock_gui_action_authen_id)       
);       
       
DELETE FROM b_stock_gui_action_authen;
COPY b_stock_gui_action_authen(b_stock_gui_action_authen_id,b_stock_id,b_stock_user_id,f_stock_gui_action_id,stock_gui_action_name,f_stock_authentication_id,gui_action_authen_authentication_name,gui_action_authen_is_read,gui_action_authen_is_write,gui_action_authen_note) FROM stdin;
00001	1	00000	01000	เมนูการตั้งค่าการใช้งานระบบคลัง	15	คลังยา/เวชภัณฑ์	1	1	
00002	1	00000	02000	เมนูการจัดการระบบคลัง	15	คลังยา/เวชภัณฑ์	1	1	
00003	1	00000	03000	เมนูรายงานของระบบคลัง	15	คลังยา/เวชภัณฑ์	1	1	
00004	1	00000	04000	แถบรายการตรวจรักษา	15	คลังยา/เวชภัณฑ์	1	1	
00005	1	00000	05000	เมนูการรับคืนยาผู้ป่วยใน	15	คลังยา/เวชภัณฑ์	1	1	
00006	1	00000	06000	เมนูกองทุนยา	15	คลังยา/เวชภัณฑ์	1	1	
00007	1	00000	01100	เมนูผู้ใช้งานและสิทธิการใช้งานระบบคลัง	15	คลังยา/เวชภัณฑ์	1	1	
00008	1	00000	01200	เมนูรายการยาและเวชภัณฑ์ในคลัง	15	คลังยา/เวชภัณฑ์	1	1	
00009	1	00000	01300	เมนูการตั้งค่าระบบคลัง	15	คลังยา/เวชภัณฑ์	1	1	
00010	1	00000	01400	เมนูคลังยาและเวชภัณฑ์	15	คลังยา/เวชภัณฑ์	1	1	
00011	1	00000	01500	เมนูผู้แทนจำหน่าย	15	คลังยา/เวชภัณฑ์	1	1	
00012	1	00000	01600	เมนูหน่วยงาน	15	คลังยา/เวชภัณฑ์	1	1	
00013	1	00000	01700	เมนูประเภทยา	15	คลังยา/เวชภัณฑ์	1	1	
00014	1	00000	02100	เมนูการจัดการของในคลัง	15	คลังยา/เวชภัณฑ์	1	1	
00015	1	00000	02200	เมนูการปรับยอด	15	คลังยา/เวชภัณฑ์	1	1	
00016	1	00000	02300	เมนูการซื้อและรับเข้าจากการซื้อ	15	คลังยา/เวชภัณฑ์	1	1	
00017	1	00000	02400	เมนูการเบิกและรับเข้าจากการเบิก	15	คลังยา/เวชภัณฑ์	1	1	
00018	1	00000	02500	เมนูการรับเข้าอื่นๆ	15	คลังยา/เวชภัณฑ์	1	1	
00019	1	00000	03100	รายงานการจ่ายออกยาและเวชภัณฑ์	15	คลังยา/เวชภัณฑ์	1	1	
00020	1	00000	03200	รายงานการรับเข้ายาและเวชภัณฑ์	15	คลังยา/เวชภัณฑ์	1	1	
00021	1	00000	03300	รายงานยาและเวชภัณฑ์ใกล้หมดอายุ	15	คลังยา/เวชภัณฑ์	1	1	
00022	1	00000	03400	รายงานสรุปมูลค่าและจำนวนยาและเวชภัณฑ์คงคลัง	15	คลังยา/เวชภัณฑ์	1	1	
00023	1	00000	03500	รายงานรายการยาและเวชภัณฑ์ที่ไม่เคลื่อนไหว	15	คลังยา/เวชภัณฑ์	1	1	
00024	1	00000	03600	รายงานการปรับยอดยาและเวชภัณฑ์	15	คลังยา/เวชภัณฑ์	1	1	
00025	1	00000	03700	รายงานบันทึกรายละเอียดการสั่งซื้อยาและเวชภัณฑ์	15	คลังยา/เวชภัณฑ์	1	1	
00026	1	00000	03800	รายงานประวัติการสั่งซื้อยาและเวชภัณฑ์	15	คลังยา/เวชภัณฑ์	1	1	
00027	1	00000	03900	รายงานรับเข้า/จ่ายออก/ปรับยอด/คงเหลือ ของยาและเวชภัณฑ์	15	คลังยา/เวชภัณฑ์	1	1	
00028	1	00000	04100	ปุ่มจ่าย	15	คลังยา/เวชภัณฑ์	1	1	
00029	1	00000	04200	ปุ่มยกเลิก	15	คลังยา/เวชภัณฑ์	1	1	
00030	1	00000	05100	ปุ่มบันทึก	15	คลังยา/เวชภัณฑ์	1	1	
00031	1	00000	06100	ปุ่มคิดเงิน	15	คลังยา/เวชภัณฑ์	1	1	
00032	1	00000	06200	ปุ่มยกเลิกการคิดเงิน  	15	คลังยา/เวชภัณฑ์	1	1	
\.


CREATE TABLE b_stock_service_point (        
b_stock_service_point_id character varying(255) NOT NULL,    
b_service_point_id character varying(255) DEFAULT ''::character varying,    
b_stock_id character varying(255) DEFAULT ''::character varying,    
stock_service_point_active character varying(255) DEFAULT ''::character varying,    
    PRIMARY KEY(b_stock_service_point_id)       
);       
CREATE TABLE b_stock_item_distributor (        
b_stock_item_distributor_id character varying(255) NOT NULL,    
t_stock_item_id character varying(255) DEFAULT ''::character varying,    
b_distributor_id character varying(255) DEFAULT ''::character varying,    
stock_item_distributor_order_uom_id character varying(255) DEFAULT ''::character varying,    
stock_item_distributor_order_uom_rate double precision,  
stock_item_distributor_dispense_uom_rate double precision,  
stock_item_distributor_mid_uom_rate double precision,  
stock_item_distributor_uom_price double precision,  
stock_item_distributor_record_date_time character varying(255) DEFAULT ''::character varying,    
stock_item_distributor_record_eid character varying(255) DEFAULT ''::character varying,    
stock_item_distributor_update_date_time character varying(255) DEFAULT ''::character varying,    
stock_item_distributor_update_eid character varying(255) DEFAULT ''::character varying,    
stock_item_distributor_cancel_date_time character varying(255) DEFAULT ''::character varying,       
stock_item_distributor_cancel_eid character varying(255) DEFAULT ''::character varying,       
stock_item_distributor_active character varying(255) DEFAULT ''::character varying,       
    PRIMARY KEY( b_stock_item_distributor_id)       
);       
       
CREATE TABLE s_stock_hos_version (       
 s_stock_hos_version_id character varying(255) NOT NULL,    
stock_hos_version_number character varying(255) DEFAULT ''::character varying,    
stock_hos_version_description character varying(255) DEFAULT ''::character varying,    
stock_hos_version_application_number character varying(255) DEFAULT ''::character varying,    
stock_hos_version_database_number character varying(255) DEFAULT ''::character varying,    
stock_hos_version_update_time character varying(255) DEFAULT ''::character varying,    
    PRIMARY KEY( s_stock_hos_version_id)       
);       
       
CREATE TABLE b_stock_item_drug_type (       
b_stock_item_drug_type_id character varying(255) NOT NULL,    
stock_item_drug_type_no character varying(255) DEFAULT ''::character varying,    
stock_item_drug_type_name character varying(255) DEFAULT ''::character varying,    
stock_item_drug_type_record_date_time character varying(255) DEFAULT ''::character varying,    
stock_item_drug_type_record_eid character varying(255) DEFAULT ''::character varying,    
stock_item_drug_type_update_date_time character varying(255) DEFAULT ''::character varying,    
stock_item_drug_type_update_eid character varying(255) DEFAULT ''::character varying,    
stock_item_drug_type_cancel_date_time character varying(255) DEFAULT ''::character varying,    
stock_item_drug_type_cancel_eid character varying(255) DEFAULT ''::character varying,    
stock_item_drug_type_active character varying(255) DEFAULT ''::character varying,    
    PRIMARY KEY( b_stock_item_drug_type_id)       
);       


CREATE TABLE f_stock_sub_method (    
 f_stock_sub_method_id character varying(255) NOT NULL, 
stock_sub_method_description character varying(255) DEFAULT ''::character varying,    
PRIMARY KEY(f_stock_sub_method_id)
);

CREATE TABLE f_stock_method (    
 f_stock_method_id character varying(255) NOT NULL, 
stock_method_description character varying(255) DEFAULT ''::character varying,    
PRIMARY KEY(f_stock_method_id)
);

DELETE FROM f_stock_sub_method;
COPY f_stock_sub_method(f_stock_sub_method_id,stock_sub_method_description) FROM stdin;
0	ยอดยกมา
1	รับจากการซื้อ
2	รับจากการเบิก
3	คืนยาจากผู้ป่วย
4	คืนยาจากกองทุนยา
5	รับจากอื่นๆ
6	ขายให้ผู้ป่วย
7	ใช้กับผู้ป่วย
8	ส่งคืน
9	เสียหาย
10	จ่ายให้กองทุนยา
11	ใช้ในหน่วยงาน
12	หมดอายุ
13	ยกเลิก
14	อื่นๆ
\.

DELETE FROM f_stock_method;
COPY f_stock_method(f_stock_method_id,stock_method_description) FROM stdin;
0	ยอดยกมา
1	รับเข้า
2	จ่ายออก
3	ปรับยอด
4	ยกเลิก
\.

CREATE TABLE b_stock_option_detail (    
 b_stock_option_detail_id character varying(255) NOT NULL, 
stock_option_detail_description character varying(255) DEFAULT ''::character varying,    
stock_option_detail_addition character varying(255) DEFAULT ''::character varying,  
stock_option_detail_record_date_time character varying(255) DEFAULT ''::character varying,  
stock_option_detail_record_eid character varying(255) DEFAULT ''::character varying,  
PRIMARY KEY(b_stock_option_detail_id)
);

CREATE TABLE f_stock_order_status ( 
    f_stock_order_status_id character varying(255) NOT NULL, 
    stock_order_status_description character varying(255) DEFAULT ''::character varying,
    PRIMARY KEY(f_stock_order_status_id)
);

DELETE FROM f_stock_order_status;
COPY f_stock_order_status(f_stock_order_status_id,stock_order_status_description) FROM stdin;
1	ขอซื้อ
2	ใบขอซื้อ
3	รับเข้าบางส่วน
4	รับเข้า
5	ไม่รับเข้า 
6	ใบสั่งซื้อ
7	ใบตรวจรับพัสดุ
8	ขอเบิก
9	ยกเลิก
10	ใบเบิกจากคลัง
11	ใบเบิกรพ.
\.

INSERT INTO s_stock_hos_version VALUES ('sth0190000000000001', '01', 'Stock Hos Module', '1.0.060508', '1.0.060508', '2551-05-06 18:00:00');
