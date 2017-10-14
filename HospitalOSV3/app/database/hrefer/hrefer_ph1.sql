CREATE TABLE t_sa_refer_in_out (
    t_sa_refer_in_out_id character varying(255) NOT NULL,
    sa_refer_in_out_office_source_id character varying(255),
    sa_refer_in_out_office_destination_id character varying(255),
    sa_refer_in_out_office_source_name character varying(255),
    sa_refer_in_out_office_destination_name character varying(255),
    sa_refer_in_out_patient_pid character varying(255),
    sa_refer_in_out_patient_send_hn character varying(255),
    sa_refer_in_out_patient_receive_hn character varying(255),
    sa_refer_in_out_patient_prefix character varying(255),
    sa_refer_in_out_patient_firstname character varying(255),
    sa_refer_in_out_patient_lastname character varying(255),
    sa_refer_in_out_history_symptom character varying(4000),
    sa_refer_in_out_current_symptom character varying(4000),
    sa_refer_in_out_treatment character varying(4000),
    sa_refer_in_out_lab character varying(4000),
    sa_refer_in_out_diagnosis character varying(255),
    sa_refer_in_out_doctor_id character varying(255),
    sa_refer_in_out_doctor_name character varying(255),
    sa_refer_in_out_cause character varying(255),
    sa_refer_in_out_send_record_date_time character varying(255),
    sa_refer_in_out_receive_record_date_time character varying(255),
    sa_refer_in_out_patient_birthday character varying(255),
    sa_refer_in_out_patient_house character varying(255),
    sa_refer_in_out_patient_road character varying(255),
    sa_refer_in_out_patient_moo character varying(255),
    sa_refer_in_out_patient_tambon character varying(255),
    sa_refer_in_out_patient_amphur character varying(255),
    sa_refer_in_out_patient_changwat character varying(255),
    sa_refer_in_out_result_diagnosis character varying(255),
    sa_refer_in_out_result_treatment character varying(4000),
    sa_refer_in_out_result_lab character varying(4000),
    sa_refer_in_out_result_treatment_continue character varying(255),
    sa_refer_in_out_result_doctor_id character varying(255),
    sa_refer_in_out_result_doctor_name character varying(255),
    sa_refer_in_out_doctor_accept character varying(255),
    sa_refer_in_out_result_accept character varying(255),
    sa_refer_in_out_receive_finish_refer character varying(255),
    sa_refer_in_out_send_result_date_time character varying(255),
    sa_refer_in_out_receive_result_date_time character varying(255),
    sa_refer_in_out_active character varying(255),
    t_visit_refer_in_out_send_id character varying(255),
    t_visit_refer_in_out_receive_id character varying(255)
);
ALTER TABLE public.t_sa_refer_in_out OWNER TO postgres;

ALTER TABLE ONLY t_sa_refer_in_out
    ADD CONSTRAINT t_sa_refer_in_out_id PRIMARY KEY (t_sa_refer_in_out_id);
ALTER INDEX public.t_sa_refer_in_out_id OWNER TO postgres;

CREATE INDEX sa_in_out_receive_id ON t_sa_refer_in_out USING btree (t_visit_refer_in_out_receive_id);
ALTER INDEX public.sa_in_out_receive_id OWNER TO postgres;

CREATE INDEX sa_in_out_send_id ON t_sa_refer_in_out USING btree (t_visit_refer_in_out_send_id);
ALTER INDEX public.sa_in_out_send_id OWNER TO postgres;

CREATE UNIQUE INDEX t_sa_refer_in_out_pk ON t_sa_refer_in_out USING btree (t_sa_refer_in_out_id);
ALTER INDEX public.t_sa_refer_in_out_pk OWNER TO postgres;

create table b_hrefer_siteconf(
url varchar(255),
namespace varchar(255)
);

create table s_hrefer_version(
id varchar(255),
version_app varchar(255),
version_db varchar(255),
date_update varchar(255)
);
ALTER TABLE ONLY s_hrefer_version ADD CONSTRAINT s_hrefer_version_id PRIMARY KEY (id);

insert into s_hrefer_version values ('01','1.0.180209','1.0.180209','2552-02-18,12:12:00');

