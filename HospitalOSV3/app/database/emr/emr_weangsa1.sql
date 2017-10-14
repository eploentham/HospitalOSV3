
alter table s_emr_version rename s_version_emr_id to s_emr_version_id;
alter table s_emr_version rename version_emr_number to emr_version_number;
alter table s_emr_version rename version_emr_description to emr_version_description;
alter table s_emr_version rename version_emr_application_number to emr_version_application_number;
alter table s_emr_version rename version_emr_database_number to emr_version_database_number;
alter table s_emr_version rename version_emr_update_time to emr_version_update_time;


CREATE TABLE t_transfer_group_file_patient (
 t_transfer_group_file_patient_id character varying(255) not null,
 t_patient_id character varying(255),
 t_visit_id character varying(255),
 transfer_group_file_patient_description character varying(255),
 transfer_group_file_patient_date_time character varying(255),
 transfer_group_file_patient_active character varying(255)
);

INSERT INTO t_transfer_group_file_patient ( t_transfer_group_file_patient_id, t_patient_id, t_visit_id, transfer_group_file_patient_description, transfer_group_file_patient_date_time, transfer_group_file_patient_active) VALUES('VE0029947177745','206112650670931264','255112653653923734','image_group 1','2549-12-21,10:27:46','1');

INSERT INTO s_emr_version VALUES ('VE0020000000002', '2', 'EMR Module', '1.0.231206', '1.0.231206', '2549-12-23 18:00:00');


