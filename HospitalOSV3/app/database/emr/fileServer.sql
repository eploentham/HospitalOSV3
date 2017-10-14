--Use HospitalOS version 0.5.230106

CREATE TABLE t_transfer_file_patient (
    t_transfer_file_patient_id character varying(255) NOT NULL,
    transfer_file_patient_active character varying(255),
    transfer_file_patient_update_time character varying(255),
    transfer_file_patient_file_name character varying(255),
    transfer_file_patient_path character varying(255),
    t_visit_id character varying(255),
    t_patient_id character varying(255),
    transfer_file_patient_description character varying(4000),
    transfer_file_patient_head_description character varying(255),
    transfer_file_patient_server character varying(255)
);
ALTER TABLE ONLY t_transfer_file_patient
    ADD CONSTRAINT t_transfer_file_patient_id PRIMARY KEY (t_transfer_file_patient_id);
CREATE INDEX t_patient_id_transfer_id ON t_transfer_file_patient USING btree (t_patient_id);
CREATE INDEX t_visit_id_tranfer_file_index ON t_transfer_file_patient USING btree (t_visit_id);