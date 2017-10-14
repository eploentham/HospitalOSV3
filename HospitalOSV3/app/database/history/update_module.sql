--drop table history_dlocation;
CREATE TABLE history_dlocation	(
	history_dlocation_id varchar(255) ,
        history_dlocation_hospital_id varchar(255) ,
	history_dlocation_name varchar(255) ,
        history_dlocation_driver varchar(255) ,
        history_dlocation_password varchar(255) ,
        history_dlocation_url varchar(255) ,
        history_dlocation_username varchar(255) ,
        history_dlocation_type varchar(255) 
	) ;

insert into history_dlocation values('hs0011234567890','12341','รพท้ายเหมือง','org.postgresql.Driver','password','jdbc:postgresql://localhost/jld','postgres','1');
insert into history_dlocation values('hs0011234567891','12342','ศูนย์จักษุรักษา','org.postgresql.Driver','password','jdbc:postgresql://localhost/jld2','postgres','1');
insert into history_dlocation values('hs0011234567892','12343','รพสามพราน','org.postgresql.Driver','password','jdbc:postgresql://localhost/hospital_osv3','postgres','3');
insert into history_dlocation values('hs0011234567893','12344','รพชุมแสง','org.postgresql.Driver','password','jdbc:postgresql://192.168.1.21/maechai','postgres','3');

--drop table history_version;
CREATE TABLE history_version (
	code character varying(255),
	name character varying(255),
	text character varying(255)
);
insert into history_version values ('hs0021234567890123','1.01.030206','Start From Hospital 3.06');
