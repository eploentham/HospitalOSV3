-- อับเดตจาก DEMO
ALTER TABLE b_welfare_map_plan ADD COLUMN staff_record varchar(255);
ALTER TABLE b_welfare_map_plan ADD COLUMN record_datetime varchar(255);

CREATE TABLE s_welfare_version
(
  s_version_id varchar(255) NOT NULL,
  version_number varchar(255),
  version_description varchar(255),
  version_application_number varchar(255),
  version_database_number varchar(255),
  version_update_time varchar(255),
  CONSTRAINT s_welfare_version_pkey PRIMARY KEY (s_version_id)
) 
WITH OIDS;
ALTER TABLE s_welfare_version OWNER TO postgres;

insert into  s_welfare_version (s_version_id, version_number, version_description, version_application_number, version_database_number, version_update_time) values 
('9701000000014','1','Hospital OS, Community Edition','1.01.01042006','3.13.1048','2548-03-24 22:25:00');

CREATE INDEX ucdata_pid
  ON b_walfare_ucdata
  USING btree
  (welfare_ucdata_pid);

  CREATE INDEX ucdata_pk
  ON b_walfare_ucdata
  USING btree
  (welfare_ucdata_id);
