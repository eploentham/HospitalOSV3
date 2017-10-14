CREATE TABLE t_welfare_opbills ( 
	t_welfare_opbills_id      	varchar(255) NOT NULL,
	t_welfare_billtrans_id    	varchar(255) NULL,
	t_welfare_opbills_invno   	varchar(255) NULL,
	t_welfare_opbills_billmaud	varchar(255) NULL,
	t_welfare_opbills_amount  	varchar(255) NULL,
	t_welfare_opbills_paid    	varchar(255) NULL,
    CONSTRAINT t_welfare_opbills_pkey PRIMARY KEY (t_welfare_opbills_id)
);

INSERT INTO s_welfare_version("s_version_id", "version_number", "version_description", "version_application_number", "version_database_number", "version_update_time")
VALUES('9701000000018', '5', 'Hospital OS, Community Edition', '1.05.15082008', '3.16.0851', '2551-08-14 12:00:00')