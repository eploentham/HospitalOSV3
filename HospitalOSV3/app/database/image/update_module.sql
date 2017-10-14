
CREATE TABLE image_image_link	(
	 image_link_id varchar(255) ,
	 patient varchar(255) ,
	 visit varchar(255) ,
	 image_path varchar(255) ,
	 note varchar(255) 
	) ;
CREATE TABLE image_version (
	code character varying(255),
	name character varying(255),
	text character varying(255)
);
CREATE TABLE image_path (
	code character varying(255),
	name character varying(255)
);
insert into image_path values ('1','C:/Documents and Settings/Administrator/Desktop/temp');
insert into image_version values ('1','1.01.03jun04','Start From Hospital 1.24e Appoint 1.01');
