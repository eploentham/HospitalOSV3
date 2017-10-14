
ALTER TABLE "axisnhso2010_sendlog"
	ADD CONSTRAINT "axisnhso2010_sendlog_pkey"
	PRIMARY KEY ("log_id");

create table temp_f_nhso_icd10_2007 as SELECT * FROM f_nhso_icd10_2007;
delete from f_nhso_icd10_2007;
ALTER TABLE "f_nhso_icd10_2007"
	ADD CONSTRAINT "f_nhso_icd10_2007_pkey"
	PRIMARY KEY ("code");
insert into f_nhso_icd10_2007 (
select distinct * from temp_f_nhso_icd10_2007
);
drop table temp_f_nhso_icd10_2007;

create table temp_f_nhso_icd9cm_2007 as SELECT * FROM f_nhso_icd9cm_2007;
delete from f_nhso_icd9cm_2007;
ALTER TABLE "f_nhso_icd9cm_2007"
	ADD CONSTRAINT "f_nhso_icd9cm_2007_pkey"
	PRIMARY KEY ("code");
insert into f_nhso_icd9cm_2007 (
select distinct * from temp_f_nhso_icd9cm_2007
);
drop table temp_f_nhso_icd9cm_2007;

 INSERT INTO s_nhso_version("s_version_id", "version_number", "version_description", "version_application_number",  "version_database_number", "version_update_time")
 VALUES('9701000000123', '23', 'Hospital OS, Community Edition', '3.14.1051', '0.10.020610', '2553-06-02,12:00:00');

insert into s_script_update_log values ('nhso','update_nhso_ph23.sql',(select current_date) || ','|| (select  current_time),'update NHSO ph22 -> ph23');