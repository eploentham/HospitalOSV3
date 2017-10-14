
--เพิ่มประเภทใบเสร็จเงินเชื่อ 
INSERT INTO f_sph_billing_credit_number_type (f_sph_billing_credit_number_type_id, billing_credit_number_type_desc) 
	VALUES ('5', 'ใบเสร็จเงินเชื่อตามสิทธิ พรบ. OPD');
INSERT INTO f_sph_billing_credit_number_type (f_sph_billing_credit_number_type_id, billing_credit_number_type_desc) 
	VALUES ('6', 'ใบเสร็จเงินเชื่อตามสิทธิ พรบ. IPD');

--เพิ่ม Sequence ของใบวางบิล 
INSERT INTO b_sph_sequence_data (b_sph_sequence_data_id, sequence_data_description, sequence_data_billing_type, sequence_data_visit_type, sequence_data_pre_value, sequence_data_pattern, sequence_data_value, sequence_data_active) 
VALUES ('stmipd', 'IPD Statement Number(changpuek module)', 'statement', 'IPD', '57', 'yy00000', '1', '1');
INSERT INTO b_sph_sequence_data (b_sph_sequence_data_id, sequence_data_description, sequence_data_billing_type, sequence_data_visit_type, sequence_data_pre_value, sequence_data_pattern, sequence_data_value, sequence_data_active) 
VALUES ('stmopd', 'OPD Statement Number(changpuek module)', 'statement', 'OPD', '67', 'yy00000', '1', '1');

--
CREATE TABLE t_sph_statement_number ( 
    t_sph_statement_number_id     	varchar(255) NOT NULL,
    t_visit_id	varchar(255) NOT NULL,
    t_billing_id	varchar(255) NOT NULL,
    t_sph_billing_credit_number_id	varchar(255) NOT NULL,
    statement_number              	varchar(255) NOT NULL,
    statement_visit_type              	varchar(255) NOT NULL,
    statement_type              	varchar(255) NOT NULL,
    statement_company             	varchar(255) NOT NULL,
    statement_code                	varchar(255) NOT NULL,
    statement_status              	varchar(255) NOT NULL,
    statement_date                	varchar(255) NOT NULL,
    statement_name                	varchar(255) NOT NULL,
    statement_bill_no             	varchar(255) NOT NULL,
    statement_amount              	varchar(255) NOT NULL,
    statement_discount            	varchar(255) NOT NULL,
    statement_netpay              	varchar(255) NOT NULL,
    statement_print_no              	varchar(255) NOT NULL,
    statement_active              	varchar(255) NOT NULL,
    statement_record_datetime     	varchar(255) NOT NULL,
    statement_record_staff        	varchar(255) NOT NULL,
    statement_modify_datetime     	varchar(255) NULL,
    statement_modify_staff        	varchar(255) NULL
);

CREATE UNIQUE INDEX t_sph_statement_number_id_pk
    ON public.t_sph_statement_number(t_sph_statement_number_id);

-- เพิ่ม version 
INSERT INTO s_sph_version VALUES ('shp101000000002', '02', 'SPH(CASH), CHANGPUEK HOSPITAL EDITION', '1.1.0410', '1.1.0410', '2553-04-07 16:42:31');

insert into s_script_update_log values ('SPH Module','update_sph_20100407.sql',(select current_date) || ','|| (select current_time),'ปรับปรุงฐานข้อมูล SPH สำหรับ พรบ.');
