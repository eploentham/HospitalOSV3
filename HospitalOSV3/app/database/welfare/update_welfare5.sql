
ALTER TABLE t_welfare_billtrans 
	RENAME billtrans_response_number TO billtrans_response_file_name
;

ALTER TABLE t_welfare_directdraw_patient 
	RENAME directdraw_patient_member_number TO directdraw_patient_response_file_name
;

ALTER TABLE t_welfare_directdraw_patient
	ADD COLUMN directdraw_patient_active varchar(255) NULL DEFAULT ''
;
ALTER TABLE t_welfare_directdraw_patient
	ADD COLUMN directdraw_patient_cancel_date_time varchar(255) NULL DEFAULT ''
;
ALTER TABLE t_welfare_directdraw_patient
	ADD COLUMN directdraw_patient_cancel_staff varchar(255) NULL DEFAULT ''
;


ALTER TABLE t_welfare_billtrans
	ADD COLUMN billtrans_active varchar(255) NULL DEFAULT ''
;
ALTER TABLE t_welfare_billtrans
	ADD COLUMN billtrans_cancel_date_time varchar(255) NULL DEFAULT ''
;
ALTER TABLE t_welfare_billtrans
	ADD COLUMN billtrans_cancel_staff varchar(255) NULL DEFAULT ''
;

update t_welfare_directdraw_patient set directdraw_patient_active = '1';
update t_welfare_billtrans set billtrans_active = '1';

--���ҧ�红�����ʶҹ��Һ�ŷ�����͡ billtrans
CREATE TABLE b_welfare_site (
    b_welfare_site_id character varying(255) NOT NULL,
    b_visit_office_id character varying(255),
    welfare_site_name character varying(255),
    welfare_site_station character varying(255),
    welfare_site_tambon character varying(255),
    welfare_site_district character varying(255),
    welfare_site_province character varying(255),
    welfare_site_postcode character varying(255),
    welfare_site_is_same_hos_site character varying(255),
    CONSTRAINT b_welfare_site_pkey PRIMARY KEY (b_welfare_site_id)
);

insert into s_welfare_version (s_version_id, version_number, version_description, version_application_number, version_database_number, version_update_time) values
('9701000000017','4','Hospital OS, Community Edition','1.04.15012008','3.15.0151v','2551-01-15 17:30:00');
