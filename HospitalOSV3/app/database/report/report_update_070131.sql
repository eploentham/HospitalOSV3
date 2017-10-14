
-- Table: t_report_clinic_12files
-- DROP TABLE t_report_clinic_12files;


CREATE TABLE t_report_clinic_12files
(
  t_report_clinic_12files_id varchar(255) NOT NULL,
  report_clinic_12files_description varchar(255) DEFAULT ''::character varying,
  report_clinic_12files_description_en varchar(255) DEFAULT ''::character varying,
  CONSTRAINT t_report_clinic_12files_id PRIMARY KEY (t_report_clinic_12files_id)
) 
WITH OIDS;
ALTER TABLE t_report_clinic_12files OWNER TO postgres;

-- DATA t_report_clinic_12Files 
INSERT INTO t_report_clinic_12Files(t_report_clinic_12files_id,report_clinic_12files_description,report_clinic_12files_description_en)VALUES('01','อายุรกรรม','MEDICINE');
INSERT INTO t_report_clinic_12Files(t_report_clinic_12files_id,report_clinic_12files_description,report_clinic_12files_description_en)VALUES('02','ศัลยกรรม','SURGERY');
INSERT INTO t_report_clinic_12Files(t_report_clinic_12files_id,report_clinic_12files_description,report_clinic_12files_description_en)VALUES('03','สูติกรรม','OB');
INSERT INTO t_report_clinic_12Files(t_report_clinic_12files_id,report_clinic_12files_description,report_clinic_12files_description_en)VALUES('04','นรีเวชกรรม','GYN');
INSERT INTO t_report_clinic_12Files(t_report_clinic_12files_id,report_clinic_12files_description,report_clinic_12files_description_en)VALUES('05','กุมารเวช','PED');
INSERT INTO t_report_clinic_12Files(t_report_clinic_12files_id,report_clinic_12files_description,report_clinic_12files_description_en)VALUES('06','โสต ศอ นาสิก','ENT');
INSERT INTO t_report_clinic_12Files(t_report_clinic_12files_id,report_clinic_12files_description,report_clinic_12files_description_en)VALUES('07','จักษุ','EYE');
INSERT INTO t_report_clinic_12Files(t_report_clinic_12files_id,report_clinic_12files_description,report_clinic_12files_description_en)VALUES('08','ศัลยกรรมกระดูก','ORTHOPEDICS');
INSERT INTO t_report_clinic_12Files(t_report_clinic_12files_id,report_clinic_12files_description,report_clinic_12files_description_en)VALUES('09','จิตเวช','PSYCHIATRY');
INSERT INTO t_report_clinic_12Files(t_report_clinic_12files_id,report_clinic_12files_description,report_clinic_12files_description_en)VALUES('10','รังสีวิทยา','RADIOLOGY');
INSERT INTO t_report_clinic_12Files(t_report_clinic_12files_id,report_clinic_12files_description,report_clinic_12files_description_en)VALUES('11','ทันตกรรม','DENTAL');
INSERT INTO t_report_clinic_12Files(t_report_clinic_12files_id,report_clinic_12files_description,report_clinic_12files_description_en)VALUES('12','อื่นๆ','OTHER');


-- Table: t_report_map_clinic

-- DROP TABLE t_report_map_clinic;

CREATE TABLE t_report_map_clinic
(
  t_report_map_clinic_id varchar(255) NOT NULL,
  b_visit_clinic_id varchar(255) DEFAULT ''::character varying,
  visit_clinic_number varchar(255) DEFAULT ''::character varying,
  visit_clinic_description varchar(255) DEFAULT ''::character varying,
  t_report_clinic_12files_id varchar(255) DEFAULT '1'::character varying,
  CONSTRAINT t_report_map_clinic_id PRIMARY KEY (t_report_map_clinic_id)
) 
WITH OIDS;
ALTER TABLE t_report_map_clinic OWNER TO postgres;

insert into s_report_version values ('9720000000007','07','HospitalOS Report For Hos37','1.7.310107','for hospitalOS v3 db : 3.14n.050506 and pcu db : 0.04.0449','2550-01-31 11:16:00');

