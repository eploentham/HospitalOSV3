--drop table history_dlocation;
CREATE TABLE b_nhso_dlocation	(
	b_nhso_dlocation_id varchar(255) ,
        b_nhso_dlocation_hospital_id varchar(255) ,
	b_nhso_dlocation_name varchar(255) ,
        b_nhso_dlocation_driver varchar(255) ,
        b_nhso_dlocation_password varchar(255) ,
        b_nhso_dlocation_url varchar(255) ,
        b_nhso_dlocation_username varchar(255) ,
        b_nhso_dlocation_type varchar(255) 
	) ;

insert into b_nhso_dlocation values('hs0011234567890','12341','Upload','oracle.jdbc.driver.OracleDriver','chung','jdbc:oracle:thin:@192.168.1.55:1521:oracle','blackguard','1');
insert into b_nhso_dlocation values('hs0011234567891','12342','BPPDS','oracle.jdbc.driver.OracleDriver','chung','jdbc:oracle:thin:@192.168.1.55:1521:oracle','blackguard','2');

--drop table b_nhso_version;
CREATE TABLE b_nhso_upload_version (
	code character varying(255),
	name character varying(255),
	text character varying(255)
);
insert into b_nhso_upload_version values ('hs0021234567890123','1.1.170308','Start From Hospital 3.8');
