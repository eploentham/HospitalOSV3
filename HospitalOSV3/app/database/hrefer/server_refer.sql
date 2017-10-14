 
CREATE TABLE b_sp_office (
    b_sp_office_id character varying(255) NOT NULL,
    sp_office_name character varying(255),
    sp_office_changwat character varying(255),
    sp_office_amphur character varying(255),
    sp_office_tambon character varying(255),
    sp_office_url character varying(255),
    sp_activate_key character varying(255),
    sp_activate character varying(255)
);
 
CREATE TABLE t_sp_refer_in_out (
    t_sp_refer_in_out_id character varying(255) NOT NULL,
    sp_refer_in_out_office_source_id character varying(255),
    sp_refer_in_out_office_source_name character varying(255),
    sp_refer_in_out_in_out_office_destination_id character varying(255),
    sp_refer_in_out_office_destination_name character varying(255),
    sp_refer_in_out_patient_pid character varying(255),
    sp_refer_in_out_patient_send_hn character varying(255),
    sp_refer_in_out_patient_receive_hn character varying(255),
    sp_refer_in_out_patient_firstname character varying(255),
    sp_refer_in_out_patient_lastname character varying(255),
    sp_refer_in_out_history_symptom character varying(4000),
    sp_refer_in_out_current_symptom character varying(4000),
    sp_refer_in_out_treatment character varying(4000),
    sp_refer_in_out_lab character varying(4000),
    sp_refer_in_out_diagnosis character varying(255),
    sp_refer_in_out_doctor_id character varying(255),
    sp_refer_in_out_doctor_name character varying(255),
    sp_refer_in_out_cause character varying(255),
    sp_refer_in_out_send_record_date_time character varying(255),
    sp_refer_in_out_receive_record_date_time character varying(255),
    sp_refer_in_out_patient_birthday character varying(255),
    sp_refer_in_out_patient_house character varying(255),
    sp_refer_in_out_patient_road character varying(255),
    sp_refer_in_out_patient_moo character varying(255),
    sp_refer_in_out_patient_tambon character varying(255),
    sp_refer_in_out_patient_amphur character varying(255),
    sp_refer_in_out_patient_changwat character varying(255),
    sp_refer_in_out_result_diagnosis character varying(255),
    sp_refer_in_out_result_treatment character varying(4000),
    sp_refer_in_out_result_lab character varying(4000),
    sp_refer_in_out_result_treatment_continue character varying(255),
    sp_refer_in_out_result_doctor_id character varying(255),
    sp_refer_in_out_result_doctor_name character varying(255),
    sp_refer_in_out_doctor_accept character varying(255),
    sp_refer_in_out_result_accept character varying(255) DEFAULT 0,
    sp_refer_in_out_send_result_date_time character varying(255),
    sp_refer_in_out_receive_result_date_time character varying(255),
    sp_refer_in_out_active character varying(255),
    t_visit_refer_in_out_send_id character varying(255),
    t_visit_refer_in_out_receive_id character varying(255),
    sp_refer_in_out_message character varying(1024),
    sp_refer_in_out_message_receive character varying(255),
    sp_refer_in_out_receive character varying(255),
    sp_refer_in_out_receive_result character varying(255),
    sp_refer_in_out_patient_prefix character varying(255),
    sp_refer_in_out_receive_finish_refer character varying(255),
    sp_refer_in_out_receive_accept character varying(255)
);
 
CREATE TABLE t_sp_refer_static (
    t_sp_static_id character varying(255) NOT NULL,
    sp_static_office_source_id character varying(255),
    sp_static_office_destination_id character varying(255),
    sp_static_message character varying(2000),
    sp_static_request_status character varying(255),
    sp_static_refer_result character varying(255),
    sp_static_recorde_date_time character varying(255),
    sp_static_modify_date_time character varying(255)
);
ALTER TABLE public.t_sp_refer_static OWNER TO postgres;
 
ALTER TABLE ONLY b_sp_office
    ADD CONSTRAINT b_sp_office_pkey PRIMARY KEY (b_sp_office_id);
 
ALTER TABLE ONLY t_sp_refer_in_out
    ADD CONSTRAINT t_sp_refer_in_out_id PRIMARY KEY (t_sp_refer_in_out_id);
 
ALTER TABLE ONLY t_sp_refer_static
    ADD CONSTRAINT t_sp_refer_static_pkey PRIMARY KEY (t_sp_static_id);
 
CREATE UNIQUE INDEX b_sp_office_id ON b_sp_office USING btree (b_sp_office_id);
 
CREATE INDEX destination_id ON t_sp_refer_static USING btree (sp_static_office_destination_id);
 
CREATE INDEX in_out_receive_id ON t_sp_refer_in_out USING btree (t_visit_refer_in_out_receive_id);
 
CREATE INDEX in_out_send_id ON t_sp_refer_in_out USING btree (t_visit_refer_in_out_send_id);
 
CREATE INDEX pk_id ON t_sp_refer_static USING btree (t_sp_static_id);
 
CREATE INDEX source_id ON t_sp_refer_static USING btree (sp_static_office_source_id);
 
CREATE UNIQUE INDEX sp_activate_key ON b_sp_office USING btree (sp_activate_key);
 
CREATE UNIQUE INDEX t_sp_refer_in_out_pk ON t_sp_refer_in_out USING btree (t_sp_refer_in_out_id);
 

