CREATE TABLE public.s_nameless_version ( 
    s_nameless_version_id              	varchar(255) NOT NULL,
    version_nameless_number            	varchar(255) NULL,
    version_nameless_description       	varchar(255) NULL,
    version_nameless_application_number	varchar(255) NULL,
    version_nameless_database_number   	varchar(255) NULL,
    version_nameless_update_time       	varchar(255) NULL 
);

CREATE SEQUENCE s_visit_the_withdraw_seq;

CREATE TABLE t_visit_the_withdraw_seq (
	t_visit_the_withdraw_seq_id varchar(255),
        t_visit_the_withdraw_seq_no int DEFAULT NEXTVAL('s_visit_the_withdraw_seq'),
        visit_the_withdraw_seq_year varchar(255),
PRIMARY KEY (t_visit_the_withdraw_seq_no)
);

CREATE TABLE t_visit_the_withdraw (
        t_visit_the_withdraw_id varchar(255),
	b_contract_plans_id varchar(255),
        withdraw_visit_begin_time varchar(255),
        withdraw_patient_hn varchar(255),
        withdraw_patient_name varchar(255),
        withdraw_billing_invoice_total varchar(255),
        withdraw_order_name varchar(255),
	withdraw_bill_no varchar(255),
PRIMARY KEY (t_visit_the_withdraw_id)
);

INSERT INTO s_nameless_version VALUES ('9750000000001', '1', 'NAMELESS, Community Edition', '1.00.140510', '1.00.140510', '2553-05-14 12:00:00');