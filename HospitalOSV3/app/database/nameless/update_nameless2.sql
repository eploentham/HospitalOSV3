ALTER TABLE t_visit_the_withdraw ADD COLUMN withdraw_period varchar(255) NULL default '';

CREATE TABLE t_nameless_clinic_log (
        t_nameless_clinic_log_id varchar(255),
	log_user varchar(255),
        log_action varchar(255),
	log_record_date_time varchar(255),
PRIMARY KEY (t_nameless_clinic_log_id)
);

INSERT INTO s_nameless_version VALUES ('9750000000002', '1', 'NAMELESS, Community Edition', '1.00.180510', '1.00.180510', '2553-05-14 12:00:00');