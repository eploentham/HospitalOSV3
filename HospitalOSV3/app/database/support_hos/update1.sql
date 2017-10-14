CREATE TABLE public.t_receipt_detail ( 
    t_receipt_detail_id 	varchar(255) NOT NULL,
    t_billing_id        	varchar(255) NOT NULL,
    t_billing_receipt_id	varchar(255) NOT NULL,
    t_visit_id          	varchar(255) NOT NULL,
    book_number         	varchar(255) NULL,
    print_receipt_number	varchar(255) NULL,
    receipt_name        	varchar(255) NULL,
    patient_name        	varchar(255) NULL,
    patient_hn          	varchar(255) NULL,
    receipt_type        	varchar(255) NULL,
    PRIMARY KEY(t_receipt_detail_id)
);

INSERT INTO s_sph_version VALUES ('9750000000001', '03', 'Support HOS, Community Edition', '1.2.111010', '1.2.111010', '2553-10-11 11:30:00');

INSERT INTO s_script_update_log VALUES ('Support HOS for Niranam Clinic','update1.sql',(SELECT current_date) || ','|| (SELECT current_time),'เริ่มต้นการใช้งานโมดูล Suport Hos');
