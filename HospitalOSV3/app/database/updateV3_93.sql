
ALTER TABLE "r_rp1253_adpcode"
	ADD CONSTRAINT "id_pkey"
	PRIMARY KEY ("id");
	
--ขอเพิ่มวันที่ และ ผู้ใช้งาน เมื่อกด visit จากระบบ เพราะจะสามารถดึงรายงานได้สะดวกขึ้น และได้ข้อมูลที่ถูกต้อง
--โดยกำหนดให้เพิ่ม Field ในตาราง t_visit ดังนี้
ALTER TABLE t_visit ADD COLUMN visit_record_date_time varchar(255) NULL;
ALTER TABLE t_visit ADD COLUMN visit_record_staff varchar(255) NULL;

--การจัดการข้อมูลเดิม จะต้อง update ข้อมูลทั้งสอง field นี้จากข้อมูลในตาราง t_visit_service โดยนำ SQL ไป update ดังนี้ (*ใช้เวลานานมากอาจจะไม่ต้อง update ก็ได้)
--henbe said ห้าม update
--update t_visit set  visit_record_date_time = visit.assign_date_time
--From     (select t_visit_id,min(assign_date_time) as assign_date_time   from t_visit_service group by t_visit_id) visit
--where t_visit.t_visit_id = visit.t_visit_id and t_visit.visit_record_date_time is null;

--ขอเพิ่มวันที่ และ ผู้ใช้งาน เมื่อกด จำหน่ายทางการเงิน จากระบบ 
--โดยกำหนดให้เพิ่ม Field ในตาราง t_visit ดังนี้
ALTER TABLE t_visit ADD COLUMN visit_financial_record_date_time varchar(255) NULL;
ALTER TABLE t_visit ADD COLUMN visit_financial_record_staff varchar(255) NULL;

--การจัดการกับข้อมูลเดิม ให้นำวันที่จำหน่ายทางการเงินเดิมมา update SQL ดังนี้ 
--henbe ไม่ให้แก้ให้ทำการปรับปรุงเองนอกกระบวนการปรับปรุงฐานเพราะว่า
--การปรับปรุงฐานเพียงเพื่อทำให้ฐานใช้งานได้กับเวอร์ชันใหม่เท่านั้นหากต้องการปรับปรุงจะต้องไม่ใช้เวลานานเกินไป
--หรือทำเป็นฟังชันการแก้ไขโดยเฉพาะเลย
--update t_visit set visit_financial_record_date_time = visit_financial_discharge_time
--where visit_financial_record_date_time is null;  
----------------------------------------------------------------------------------------------------------
--henbe add primary key of company and other
ALTER TABLE r_rp1853_drugcompany
	ADD CONSTRAINT "company_pkey"
	PRIMARY KEY ("company");

delete from f_health_abnormal;
ALTER TABLE f_health_abnormal
	ADD CONSTRAINT "f_health_abnormal_id_pkey"
	PRIMARY KEY ("f_health_abnormal_id");
INSERT INTO f_health_abnormal VALUES ('0', 'ไม่พบอาการผิดปกติ');
INSERT INTO f_health_abnormal VALUES ('1', 'อาเจียน');
INSERT INTO f_health_abnormal VALUES ('2', 'เวียนศรีษะ');
INSERT INTO f_health_abnormal VALUES ('3', 'ตาพร่ามัว');
INSERT INTO f_health_abnormal VALUES ('4', 'ท้องผูก');
INSERT INTO f_health_abnormal VALUES ('5', 'หลอดเลือดดำพอง');
INSERT INTO f_health_abnormal VALUES ('6', 'ริดสีดวงทวาร');

create table temp_occu as select * from f_patient_occupation ;
delete from f_patient_occupation;
ALTER TABLE f_patient_occupation
	ADD CONSTRAINT "f_patient_occupation_id_pkey"
	PRIMARY KEY ("f_patient_occupation_id");
insert into f_patient_occupation 
select f_patient_occupation_id,max(patient_occupation_description),max(r_rp1853_occupation_id) from temp_occu group by f_patient_occupation_id;

CREATE SEQUENCE s_health_version_seq;
ALTER TABLE s_health_version ADD COLUMN  id int DEFAULT NEXTVAL('s_health_version_seq');
ALTER TABLE ONLY s_health_version 
    ADD CONSTRAINT s_health_version_pkey PRIMARY KEY (id);
ALTER INDEX public.s_health_version_pkey OWNER TO postgres;

CREATE SEQUENCE t_print_drug_sticker_seq; 
ALTER TABLE t_print_drug_sticker ADD COLUMN  id int DEFAULT NEXTVAL('t_print_drug_sticker_seq');
ALTER TABLE ONLY t_print_drug_sticker  
    ADD CONSTRAINT t_print_drug_sticker_pkey PRIMARY KEY (id);
ALTER INDEX public.t_print_drug_sticker_pkey OWNER TO postgres;

CREATE SEQUENCE t_print_point_seq;
ALTER TABLE t_print_point ADD COLUMN  id int DEFAULT NEXTVAL('t_print_point_seq');
ALTER TABLE ONLY t_print_point  
    ADD CONSTRAINT t_print_point_pkey PRIMARY KEY (id);
ALTER INDEX public.t_print_point_pkey OWNER TO postgres;

CREATE SEQUENCE t_transfer_group_file_patient_seq;
ALTER TABLE t_transfer_group_file_patient ADD COLUMN  id int DEFAULT NEXTVAL('t_transfer_group_file_patient_seq');
ALTER TABLE ONLY t_transfer_group_file_patient  
    ADD CONSTRAINT t_transfer_group_file_patient_pkey PRIMARY KEY (id);
ALTER INDEX public.t_transfer_group_file_patient_pkey OWNER TO postgres;

CREATE SEQUENCE s_script_update_log_seq;
ALTER TABLE s_script_update_log ADD COLUMN  id int DEFAULT NEXTVAL('s_script_update_log_seq');
ALTER TABLE ONLY s_script_update_log  
    ADD CONSTRAINT s_script_update_log_pkey PRIMARY KEY (id);
ALTER INDEX public.s_script_update_log_pkey OWNER TO postgres;
----------------------------------------------------------------------------------------------------------
--เพิ่มฟิลด์สำหรับเก็บข้อมูล diagicd10,9 เพิ่ม primary_report
ALTER TABLE t_diag_icd10 ADD primary_report varchar(255);
ALTER TABLE t_diag_icd9 ADD primary_report varchar(255);
CREATE TABLE t_hospitalos_log (
        t_hospitalos_log_id varchar(255),
        hospitalos_log_object_id varchar(255),
        hospitalos_log_object_note varchar(255),
        hospitalos_log_table_name varchar(255),
        hospitalos_log_uc_name varchar(255),
        hospitalos_log_ip_address varchar(255),
        hospitalos_log_record_staff varchar(255),
        hospitalos_log_record_date_time varchar(255),
        hospitalos_log_status varchar(255),
PRIMARY KEY (t_hospitalos_log_id)
);
update t_patient set f_patient_foreigner_id = '1' where f_patient_nation_id = '99';
update t_patient set f_patient_foreigner_id = '1' where f_patient_race_id = '99';
update t_health_family set r_rp1853_foreign_id = '' where  f_patient_nation_id = '99';
update t_health_family set r_rp1853_foreign_id = '' where f_patient_race_id = '99';
----------------------------------------------------------------------------------------------------------
--LionHearth create table f_billing_receipt_model and add field of t_billing_receipt,t_order
CREATE TABLE f_billing_receipt_model ( 
    f_billing_receipt_model_id         	varchar(255) NOT NULL,
    receipt_model_description  	varchar(255) NULL,
    PRIMARY KEY(f_billing_receipt_model_id)
);
INSERT INTO f_billing_receipt_model(f_billing_receipt_model_id, receipt_model_description) VALUES('1', 'เงินสด');
INSERT INTO f_billing_receipt_model(f_billing_receipt_model_id, receipt_model_description) VALUES('2', 'เครดิต');
ALTER TABLE t_billing_receipt ADD COLUMN f_billing_receipt_model_id varchar(255) NULL ;
ALTER TABLE t_order ADD COLUMN order_specified varchar(255) NULL;
ALTER TABLE t_order ADD COLUMN order_specified_data varchar(255) NULL;
ALTER TABLE b_item ADD COLUMN item_specified varchar(255) NULL ;
CREATE TABLE f_order_specified ( 
    f_order_specified_id         	varchar(255) NOT NULL,
    order_specified_description  	varchar(255) NULL,
    PRIMARY KEY(f_order_specified_id)
);
INSERT INTO f_order_specified(f_order_specified_id, order_specified_description) VALUES('1', 'DFแพทย์');
ALTER TABLE t_health_family ADD COLUMN health_family_foreigner_card_no varchar(255) NULL;
CREATE TABLE b_auto_report_bug ( 
    b_auto_report_bug_id         	varchar(255) NOT NULL,
    report_bug_smtp_host  	varchar(255) NULL,
    report_bug_user_name  	varchar(255) NULL,
    report_bug_password  	varchar(255) NULL,
    report_bug_mail_from  	varchar(255) NULL,
    report_bug_mail_to  	varchar(255) NULL,
    PRIMARY KEY(b_auto_report_bug_id)
);
INSERT INTO b_auto_report_bug(b_auto_report_bug_id, report_bug_smtp_host, report_bug_user_name, report_bug_password, report_bug_mail_from, report_bug_mail_to) 
    VALUES('1', 'smtp.gmail.com', 'hospitalos.bug.reporter@gmail.com', 'hospitalos.bug.reporter123', 'hospitalos.bug.reporter@gmail.com', 'hospitalos.bug.reporter@gmail.com');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('5805','ตั้งค่าระบบส่งเมลแจ้งบัก','');
----------------------------------------------------------------------------------------------------------
drop table temp_item_drug;
--เพิ่มเลขประชากรในตาราง visit เพื่อเก็บข้อมูลสำรวจ 
insert into b_sequence_data values ('vn_survey','Survey VN','Syy000000','1','1');
 
INSERT INTO s_version VALUES ('9701000000036', '36', 'Hospital OS, Community Edition', '3.9.3', '3.18.160410', '2553-04-16 18:20:00');

INSERT INTO s_script_update_log VALUES ('hospitalOS','updateV3_93.sql',(select current_date) || ','|| (select current_time),'ปรับแก้สำหรับ hospitalOS3.9');
