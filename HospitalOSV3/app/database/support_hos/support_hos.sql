--
-- PostgreSQL database dump
--

--
-- Name: b_billing_receipt_creditor; Type: TABLE; Schema: public; Owner: Jiraporn; Tablespace: 
--

CREATE TABLE b_billing_receipt_creditor (
    b_billing_receipt_creditor_id character varying(255) NOT NULL,
    billing_receipt_creditor_code character varying(255),
    billing_receipt_creditor_name character varying(255),
    billing_receipt_creditor_active character varying(255)
);


ALTER TABLE public.b_billing_receipt_creditor OWNER TO "Postgres";

--
-- Name: b_sph_doctor_fee_item; Type: TABLE; Schema: public; Owner: Postgres; Tablespace: 
--

CREATE TABLE b_sph_doctor_fee_item (
    b_sph_doctor_fee_item_id character varying(255) NOT NULL,
    b_item_id character varying(255) NOT NULL,
    doctor_fee_item_name character varying(255) NOT NULL,
    doctor_fee_item_active character varying(255) NOT NULL,
    doctor_fee_item_record_datetime character varying(255) NOT NULL,
    doctor_fee_item_record_staff character varying(255) NOT NULL,
    doctor_fee_item_cancel_datetime character varying(255),
    doctor_fee_item_cancel_staff character varying(255),
    doctor_fee_item_code character varying(255)
);


ALTER TABLE public.b_sph_doctor_fee_item OWNER TO "Postgres";

--
-- Name: b_sph_sequence_data; Type: TABLE; Schema: public; Owner: Postgres; Tablespace: 
--

CREATE TABLE b_sph_sequence_data (
    b_sph_sequence_data_id character varying(255) NOT NULL,
    sequence_data_description character varying(255),
    sequence_data_billing_type character varying(255),
    sequence_data_visit_type character varying(255),
    sequence_data_pre_value character varying(255),
    sequence_data_pattern character varying(255),
    sequence_data_value character varying(255),
    sequence_data_active character varying(255) DEFAULT '0'::character varying
);


ALTER TABLE public.b_sph_sequence_data OWNER TO "Postgres";

--
-- Name: f_billing_receipt_type; Type: TABLE; Schema: public; Owner: Postgres; Tablespace: 
--

CREATE TABLE f_billing_receipt_type (
    f_billing_receipt_type_id character varying(255) NOT NULL,
    billing_receipt_type_description character varying(255)
);


ALTER TABLE public.f_billing_receipt_type OWNER TO "Postgres";

--
-- Name: f_sph_billing_credit_number_type; Type: TABLE; Schema: public; Owner: Postgres; Tablespace: 
--

CREATE TABLE f_sph_billing_credit_number_type (
    f_sph_billing_credit_number_type_id character varying(255) NOT NULL,
    billing_credit_number_type_desc character varying(255) NOT NULL
);


ALTER TABLE public.f_sph_billing_credit_number_type OWNER TO "Postgres";

--
-- Name: pg_logdir_ls; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW pg_logdir_ls AS
    SELECT a.filetime, a.filename FROM pg_logdir_ls() a(filetime timestamp without time zone, filename text);


ALTER TABLE public.pg_logdir_ls OWNER TO postgres;

--
-- Name: s_sph_version; Type: TABLE; Schema: public; Owner: Postgres; Tablespace: 
--

CREATE TABLE s_sph_version (
    s_sph_version_id character varying(255) NOT NULL,
    version_sph_number character varying(255),
    version_sph_description character varying(255),
    version_sph_application_number character varying(255),
    version_sph_database_number character varying(255),
    version_sph_update_time character varying(255)
);


ALTER TABLE public.s_sph_version OWNER TO "Postgres";

--
-- Name: t_billing_deduct; Type: TABLE; Schema: public; Owner: Postgres; Tablespace: 
--

CREATE TABLE t_billing_deduct (
    t_billing_deduct_id character varying(255) NOT NULL,
    t_billing_id character varying(255),
    billing_deduct_baht character varying(255),
    billing_deduct_remark character varying(255)
);


ALTER TABLE public.t_billing_deduct OWNER TO "Postgres";

--
-- Name: t_billing_deduct_subgroup; Type: TABLE; Schema: public; Owner: Postgres; Tablespace: 
--

CREATE TABLE t_billing_deduct_subgroup (
    t_billing_deduct_subgroup_id character varying(255) NOT NULL,
    t_billing_id character varying(255),
    t_visit_id character varying(255),
    t_billing_invoice_billing_subgroup_id character varying(255),
    billing_deduct_subgroup_percent character varying(255),
    billing_deduct_subgroup_baht character varying(255)
);


ALTER TABLE public.t_billing_deduct_subgroup OWNER TO "Postgres";

--
-- Name: t_billing_receipt_detail; Type: TABLE; Schema: public; Owner: Postgres; Tablespace: 
--

CREATE TABLE t_billing_receipt_detail (
    t_billing_receipt_detail_id character varying(255) NOT NULL,
    t_billing_receipt_id character varying(255),
    t_patient_id character varying(255),
    f_billing_receipt_type_id character varying(255),
    b_billing_receipt_creditor_id character varying(255),
    billing_receipt_detail_creditor_code character varying(255),
    billing_receipt_detail_creditor_name character varying(255),
    billing_receipt_detail_credit_number character varying(255),
    billing_receipt_detail_remark character varying(255),
    billing_receipt_detail_paid character varying(255)
);


ALTER TABLE public.t_billing_receipt_detail OWNER TO "Postgres";

--
-- Name: t_sph_billing_credit_number; Type: TABLE; Schema: public; Owner: Postgres; Tablespace: 
--

CREATE TABLE t_sph_billing_credit_number (
    t_sph_billing_credit_number_id character varying(255) NOT NULL,
    billing_credit_number character varying(255) NOT NULL,
    t_billing_id character varying(255) NOT NULL,
    t_visit_id character varying(255) NOT NULL,
    f_billing_credit_number_type_id character varying(255) NOT NULL,
    billing_credit_print_no character varying(255) NOT NULL,
    billing_credit_number_record_datetime character varying(255) NOT NULL,
    billing_credit_number_record_staff character varying(255) NOT NULL,
    billing_credit_number_modify_datetime character varying(255),
    billing_credit_number_modify_staff character varying(255),
    billing_credit_number_active character varying(255) NOT NULL,
    credit_total character varying(255),
    credit_patient_share character varying(255),
    credit_payer_share character varying(255),
    credit_paid character varying(255),
    credit_remain character varying(255),
    patient_prefix character varying(255),
    patient_firstname character varying(255),
    patient_lastname character varying(255),
    patient_gender character varying(255),
    patient_hn character varying(255),
    patient_pid character varying(255),
    patient_house character varying(255),
    patient_moo character varying(255),
    patient_road character varying(255),
    patient_province character varying(255),
    patient_amphur character varying(255),
    patient_tambol character varying(255),
    patient_age character varying(255),
    patient_vn_or_an character varying(255),
    patient_plans character varying(255),
    patient_card_no character varying(255),
    patient_hos_main character varying(255),
    patient_hos_sub character varying(255),
    patient_diagnosis character varying(255),
    staff_record character varying(255),
    billing_date character varying(255)
);


ALTER TABLE public.t_sph_billing_credit_number OWNER TO "Postgres";

--
-- Name: t_sph_billing_receipt_number; Type: TABLE; Schema: public; Owner: Postgres; Tablespace: 
--

CREATE TABLE t_sph_billing_receipt_number (
    t_sph_billing_receipt_number_id character varying(255) NOT NULL,
    billing_receipt_number character varying(255) NOT NULL,
    t_billing_id character varying(255) NOT NULL,
    t_billing_receipt_id character varying(255) NOT NULL,
    t_visit_id character varying(255) NOT NULL,
    f_visit_type_id character varying(255) NOT NULL,
    billing_receipt_opddf character varying(255) NOT NULL,
    billing_receipt_opddf_doctor character varying(255) NOT NULL,
    billing_receipt_print_no character varying(255) NOT NULL,
    billing_receipt_number_record_datetime character varying(255) NOT NULL,
    billing_receipt_number_record_staff character varying(255) NOT NULL,
    billing_receipt_number_modify_datetime character varying(255),
    billing_receipt_number_modify_staff character varying(255),
    billing_receipt_number_active character varying(255) NOT NULL,
    receipt_total character varying(255),
    patient_prefix character varying(255),
    patient_firstname character varying(255),
    patient_lastname character varying(255),
    patient_gender character varying(255),
    patient_hn character varying(255),
    patient_pid character varying(255),
    patient_house character varying(255),
    patient_moo character varying(255),
    patient_road character varying(255),
    patient_province character varying(255),
    patient_amphur character varying(255),
    patient_tambol character varying(255),
    patient_age character varying(255),
    patient_vn_or_an character varying(255),
    patient_plans character varying(255),
    patient_card_no character varying(255),
    patient_hos_main character varying(255),
    patient_hos_sub character varying(255),
    patient_diagnosis character varying(255),
    staff_record character varying(255),
    billing_date character varying(255),
    credit_number_reference_id character varying(255),
    credit_number_reference character varying(255)
);


ALTER TABLE public.t_sph_billing_receipt_number OWNER TO "Postgres";

--
-- Name: t_sph_credit_number_detail; Type: TABLE; Schema: public; Owner: Postgres; Tablespace: 
--

CREATE TABLE t_sph_credit_number_detail (
    t_sph_credit_number_detail_id character varying(255) NOT NULL,
    t_sph_billing_credit_number_id character varying(255) NOT NULL,
    t_billing_invoice_billing_subgroup_id character varying(255),
    billing_description character varying(255) NOT NULL,
    total_amount character varying(255) NOT NULL,
    doctor_fee character varying(255) NOT NULL
);


ALTER TABLE public.t_sph_credit_number_detail OWNER TO "Postgres";

--
-- Name: t_sph_credit_number_detail_df; Type: TABLE; Schema: public; Owner: Postgres; Tablespace: 
--

CREATE TABLE t_sph_credit_number_detail_df (
    t_sph_credit_number_detail_df_id character varying(255) NOT NULL,
    t_sph_credit_number_detail_id character varying(255) NOT NULL,
    t_sph_billing_credit_number_id character varying(255) NOT NULL,
    t_billing_invoice_item_id character varying(255) NOT NULL,
    t_order_id character varying(255) NOT NULL,
    b_item_id character varying(255) NOT NULL,
    doctor_id character varying(255) NOT NULL,
    doctor_name character varying(255) NOT NULL,
    doctor_code character varying(255) NOT NULL,
    total_amount character varying(255) NOT NULL,
    detail_df_record_datetime character varying(255) NOT NULL,
    detail_df_staff_record character varying(255) NOT NULL
);


ALTER TABLE public.t_sph_credit_number_detail_df OWNER TO "Postgres";

--
-- Name: t_sph_receipt_number_detail; Type: TABLE; Schema: public; Owner: Postgres; Tablespace: 
--

CREATE TABLE t_sph_receipt_number_detail (
    t_sph_receipt_number_detail_id character varying(255) NOT NULL,
    t_sph_billing_receipt_number_id character varying(255) NOT NULL,
    t_billing_receipt_billing_subgroup_id character varying(255),
    billing_description character varying(255) NOT NULL,
    total_amount character varying(255) NOT NULL,
    doctor_fee character varying(255) NOT NULL
);


ALTER TABLE public.t_sph_receipt_number_detail OWNER TO "Postgres";

--
-- Name: t_sph_receipt_number_detail_df; Type: TABLE; Schema: public; Owner: Postgres; Tablespace: 
--

CREATE TABLE t_sph_receipt_number_detail_df (
    t_sph_receipt_number_detail_df_id character varying(255) NOT NULL,
    t_sph_receipt_number_detail_id character varying(255) NOT NULL,
    t_sph_billing_receipt_number_id character varying(255) NOT NULL,
    t_billing_receipt_item_id character varying(255) NOT NULL,
    t_order_id character varying(255) NOT NULL,
    b_item_id character varying(255) NOT NULL,
    doctor_fee_description character varying(255) NOT NULL,
    doctor_id character varying(255) NOT NULL,
    doctor_name character varying(255) NOT NULL,
    doctor_code character varying(255) NOT NULL,
    total_amount character varying(255) NOT NULL,
    detail_df_record_datetime character varying(255) NOT NULL,
    detail_df_staff_record character varying(255) NOT NULL
);


ALTER TABLE public.t_sph_receipt_number_detail_df OWNER TO "Postgres";

--
-- Data for Name: b_billing_receipt_creditor; Type: TABLE DATA; Schema: public; Owner: Postgres
--



--
-- Data for Name: b_sph_doctor_fee_item; Type: TABLE DATA; Schema: public; Owner: Postgres
--



--
-- Data for Name: b_sph_sequence_data; Type: TABLE DATA; Schema: public; Owner: Postgres
--

INSERT INTO b_sph_sequence_data (b_sph_sequence_data_id, sequence_data_description, sequence_data_billing_type, sequence_data_visit_type, sequence_data_pre_value, sequence_data_pattern, sequence_data_value, sequence_data_active) VALUES ('ccnopd', 'OPD credit number(changpuek module)', 'credit', 'OPD', '69', 'yy00000', '1', '1');
INSERT INTO b_sph_sequence_data (b_sph_sequence_data_id, sequence_data_description, sequence_data_billing_type, sequence_data_visit_type, sequence_data_pre_value, sequence_data_pattern, sequence_data_value, sequence_data_active) VALUES ('crnopd', 'OPD receipt number(changpuek module)', 'cash', 'OPD', '44', 'yy00000', '1', '1');
INSERT INTO b_sph_sequence_data (b_sph_sequence_data_id, sequence_data_description, sequence_data_billing_type, sequence_data_visit_type, sequence_data_pre_value, sequence_data_pattern, sequence_data_value, sequence_data_active) VALUES ('ccnipd', 'IPD credit number(changpuek module)', 'credit', 'IPD', '59', 'yy00000', '1', '1');
INSERT INTO b_sph_sequence_data (b_sph_sequence_data_id, sequence_data_description, sequence_data_billing_type, sequence_data_visit_type, sequence_data_pre_value, sequence_data_pattern, sequence_data_value, sequence_data_active) VALUES ('crnipd', 'IPD receipt number(changpuek module)', 'cash', 'IPD', '33', 'yy00000', '1', '1');


--
-- Data for Name: f_billing_receipt_type; Type: TABLE DATA; Schema: public; Owner: Postgres
--

INSERT INTO f_billing_receipt_type (f_billing_receipt_type_id, billing_receipt_type_description) VALUES ('1', 'เงินสด');
INSERT INTO f_billing_receipt_type (f_billing_receipt_type_id, billing_receipt_type_description) VALUES ('2', 'บัตรเครดิต');


--
-- Data for Name: f_sph_billing_credit_number_type; Type: TABLE DATA; Schema: public; Owner: Postgres
--

INSERT INTO f_sph_billing_credit_number_type (f_sph_billing_credit_number_type_id, billing_credit_number_type_desc) VALUES ('1', 'ใบเสร็จเงินเชื่อตามสิทธิ OPD');
INSERT INTO f_sph_billing_credit_number_type (f_sph_billing_credit_number_type_id, billing_credit_number_type_desc) VALUES ('2', 'ใบเสร็จเงินเชื่อค้างชำระ OPD');
INSERT INTO f_sph_billing_credit_number_type (f_sph_billing_credit_number_type_id, billing_credit_number_type_desc) VALUES ('3', 'ใบเสร็จเงินเชื่อตามสิทธิ IPD');
INSERT INTO f_sph_billing_credit_number_type (f_sph_billing_credit_number_type_id, billing_credit_number_type_desc) VALUES ('4', 'ใบเสร็จเงินเชื่อค้างชำระ IPD');


--
-- Data for Name: s_sph_version; Type: TABLE DATA; Schema: public; Owner: Postgres
--

INSERT INTO s_sph_version (s_sph_version_id, version_sph_number, version_sph_description, version_sph_application_number, version_sph_database_number, version_sph_update_time) VALUES ('shp101000000001', '01', 'SPH(CASH), CHANGPUEK HOSPITAL EDITION', '1.0.0410', '1.0.0410', '2553-04-05 13:51:31');


--
-- Data for Name: t_billing_deduct; Type: TABLE DATA; Schema: public; Owner: Postgres
--



--
-- Data for Name: t_billing_deduct_subgroup; Type: TABLE DATA; Schema: public; Owner: Postgres
--



--
-- Data for Name: t_billing_receipt_detail; Type: TABLE DATA; Schema: public; Owner: Postgres
--



--
-- Data for Name: t_sph_billing_credit_number; Type: TABLE DATA; Schema: public; Owner: Postgres
--



--
-- Data for Name: t_sph_billing_receipt_number; Type: TABLE DATA; Schema: public; Owner: Postgres
--



--
-- Data for Name: t_sph_credit_number_detail; Type: TABLE DATA; Schema: public; Owner: Postgres
--



--
-- Data for Name: t_sph_credit_number_detail_df; Type: TABLE DATA; Schema: public; Owner: Postgres
--



--
-- Data for Name: t_sph_receipt_number_detail; Type: TABLE DATA; Schema: public; Owner: Postgres
--



--
-- Data for Name: t_sph_receipt_number_detail_df; Type: TABLE DATA; Schema: public; Owner: Postgres
--



--
-- Name: b_billing_receipt_creditor_id; Type: CONSTRAINT; Schema: public; Owner: Postgres; Tablespace: 
--

ALTER TABLE ONLY b_billing_receipt_creditor
    ADD CONSTRAINT b_billing_receipt_creditor_id PRIMARY KEY (b_billing_receipt_creditor_id);


ALTER INDEX public.b_billing_receipt_creditor_id OWNER TO "Postgres";

--
-- Name: b_sph_doctor_fee_item_pkey; Type: CONSTRAINT; Schema: public; Owner: Postgres; Tablespace: 
--

ALTER TABLE ONLY b_sph_doctor_fee_item
    ADD CONSTRAINT b_sph_doctor_fee_item_pkey PRIMARY KEY (b_sph_doctor_fee_item_id);


ALTER INDEX public.b_sph_doctor_fee_item_pkey OWNER TO "Postgres";

--
-- Name: b_sph_sequence_data_pkey; Type: CONSTRAINT; Schema: public; Owner: Postgres; Tablespace: 
--

ALTER TABLE ONLY b_sph_sequence_data
    ADD CONSTRAINT b_sph_sequence_data_pkey PRIMARY KEY (b_sph_sequence_data_id);


ALTER INDEX public.b_sph_sequence_data_pkey OWNER TO "Postgres";

--
-- Name: f_billing_receipt_type_id; Type: CONSTRAINT; Schema: public; Owner: Postgres; Tablespace: 
--

ALTER TABLE ONLY f_billing_receipt_type
    ADD CONSTRAINT f_billing_receipt_type_id PRIMARY KEY (f_billing_receipt_type_id);


ALTER INDEX public.f_billing_receipt_type_id OWNER TO "Postgres";

--
-- Name: f_sph_billing_credit_number_type_pkey; Type: CONSTRAINT; Schema: public; Owner: Postgres; Tablespace: 
--

ALTER TABLE ONLY f_sph_billing_credit_number_type
    ADD CONSTRAINT f_sph_billing_credit_number_type_pkey PRIMARY KEY (f_sph_billing_credit_number_type_id);


ALTER INDEX public.f_sph_billing_credit_number_type_pkey OWNER TO "Postgres";

--
-- Name: s_sph_version_pkey; Type: CONSTRAINT; Schema: public; Owner: Postgres; Tablespace: 
--

ALTER TABLE ONLY s_sph_version
    ADD CONSTRAINT s_sph_version_pkey PRIMARY KEY (s_sph_version_id);


ALTER INDEX public.s_sph_version_pkey OWNER TO "Postgres";

--
-- Name: t_billing_deduct_id; Type: CONSTRAINT; Schema: public; Owner: Postgres; Tablespace: 
--

ALTER TABLE ONLY t_billing_deduct
    ADD CONSTRAINT t_billing_deduct_id PRIMARY KEY (t_billing_deduct_id);


ALTER INDEX public.t_billing_deduct_id OWNER TO "Postgres";

--
-- Name: t_billing_deduct_subgroup_id; Type: CONSTRAINT; Schema: public; Owner: Postgres; Tablespace: 
--

ALTER TABLE ONLY t_billing_deduct_subgroup
    ADD CONSTRAINT t_billing_deduct_subgroup_id PRIMARY KEY (t_billing_deduct_subgroup_id);


ALTER INDEX public.t_billing_deduct_subgroup_id OWNER TO "Postgres";

--
-- Name: t_billing_receipt_detail_id; Type: CONSTRAINT; Schema: public; Owner: Postgres; Tablespace: 
--

ALTER TABLE ONLY t_billing_receipt_detail
    ADD CONSTRAINT t_billing_receipt_detail_id PRIMARY KEY (t_billing_receipt_detail_id);


ALTER INDEX public.t_billing_receipt_detail_id OWNER TO "Postgres";

--
-- Name: t_sph_billing_credit_number_pkey; Type: CONSTRAINT; Schema: public; Owner: Postgres; Tablespace: 
--

ALTER TABLE ONLY t_sph_billing_credit_number
    ADD CONSTRAINT t_sph_billing_credit_number_pkey PRIMARY KEY (t_sph_billing_credit_number_id);


ALTER INDEX public.t_sph_billing_credit_number_pkey OWNER TO "Postgres";

--
-- Name: t_sph_billing_receipt_number_pkey; Type: CONSTRAINT; Schema: public; Owner: Postgres; Tablespace: 
--

ALTER TABLE ONLY t_sph_billing_receipt_number
    ADD CONSTRAINT t_sph_billing_receipt_number_pkey PRIMARY KEY (t_sph_billing_receipt_number_id);


ALTER INDEX public.t_sph_billing_receipt_number_pkey OWNER TO "Postgres";

--
-- Name: t_sph_credit_number_detail_df_pkey; Type: CONSTRAINT; Schema: public; Owner: Postgres; Tablespace: 
--

ALTER TABLE ONLY t_sph_credit_number_detail_df
    ADD CONSTRAINT t_sph_credit_number_detail_df_pkey PRIMARY KEY (t_sph_credit_number_detail_df_id);


ALTER INDEX public.t_sph_credit_number_detail_df_pkey OWNER TO "Postgres";

--
-- Name: t_sph_credit_number_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: Postgres; Tablespace: 
--

ALTER TABLE ONLY t_sph_credit_number_detail
    ADD CONSTRAINT t_sph_credit_number_detail_pkey PRIMARY KEY (t_sph_credit_number_detail_id);


ALTER INDEX public.t_sph_credit_number_detail_pkey OWNER TO "Postgres";

--
-- Name: t_sph_receipt_number_detail_df_pkey; Type: CONSTRAINT; Schema: public; Owner: Postgres; Tablespace: 
--

ALTER TABLE ONLY t_sph_receipt_number_detail_df
    ADD CONSTRAINT t_sph_receipt_number_detail_df_pkey PRIMARY KEY (t_sph_receipt_number_detail_df_id);


ALTER INDEX public.t_sph_receipt_number_detail_df_pkey OWNER TO "Postgres";

--
-- Name: t_sph_receipt_number_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: Postgres; Tablespace: 
--

ALTER TABLE ONLY t_sph_receipt_number_detail
    ADD CONSTRAINT t_sph_receipt_number_detail_pkey PRIMARY KEY (t_sph_receipt_number_detail_id);


ALTER INDEX public.t_sph_receipt_number_detail_pkey OWNER TO "Postgres";

--
-- Name: f_billing_credit_number_type_id_pk; Type: INDEX; Schema: public; Owner: Postgres; Tablespace: 
--

CREATE UNIQUE INDEX f_billing_credit_number_type_id_pk ON f_sph_billing_credit_number_type USING btree (f_sph_billing_credit_number_type_id);


ALTER INDEX public.f_billing_credit_number_type_id_pk OWNER TO "Postgres";

--
-- Name: s_sph_version_id_pk; Type: INDEX; Schema: public; Owner: Postgres; Tablespace: 
--

CREATE UNIQUE INDEX s_sph_version_id_pk ON s_sph_version USING btree (s_sph_version_id);


ALTER INDEX public.s_sph_version_id_pk OWNER TO "Postgres";

--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

INSERT INTO s_sph_version VALUES ('shp101000000001', '01', 'SPH(CASH), CHANGPUEK HOSPITAL EDITION', '1.0.0410', '1.0.0410', '2553-04-05 13:51:31');

insert into s_script_update_log values ('SPH Module','support_hos.sql',(select current_date) || ','|| (select current_time),'เริ่มต้นการใช้งาน SPH ใหม่');
