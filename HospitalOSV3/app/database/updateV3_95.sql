--เพิ่มรอบเอวของ vital sign และเพิ่มรหััสในตาราง f_chronic_discharge_status
ALTER TABLE t_visit_vital_sign ADD COLUMN visit_vital_sign_waistline varchar(255) NULL default '';

INSERT INTO f_chronic_discharge_status (f_chronic_discharge_status_id, chronic_discharge_status_description) VALUES ('10', 'ออกจากพื้นที่');

CREATE TABLE t_lis_ln
(
  t_lis_ln_id character varying(255) NOT NULL,
  lab_number character varying(255) NOT NULL,
  t_visit_id character varying(255) NOT NULL,
  exec_datetime character varying(255) NOT NULL,
  rec_datetime timestamp without time zone NOT NULL DEFAULT now(),
  update_datetime character varying(255),
  user_confirm character varying(255),
  user_confirm_datetime character varying(255),
  user_operate character varying(255),
  user_operate_datetime character varying(255),
  CONSTRAINT t_lis_ln_pkey PRIMARY KEY (t_lis_ln_id),
  CONSTRAINT t_lis_ln_lab_number_key UNIQUE (lab_number)
);

CREATE INDEX t_lis_ln_exec_datetime_idx
  ON t_lis_ln
  USING btree
  (exec_datetime);

CREATE INDEX t_lis_ln_lab_number_idx
  ON t_lis_ln
  USING btree
  (lab_number);

CREATE INDEX t_lis_ln_t_visit_id_idx
  ON t_lis_ln
  USING btree
  (t_visit_id);

CREATE TABLE t_lis_order
(
  t_lis_order_id character varying(255) NOT NULL,
  lab_number character varying(255) NOT NULL,
  t_order_id character varying(255) NOT NULL,
  status character varying(1) NOT NULL DEFAULT '1'::character varying,
  exec_staff character varying(255) NOT NULL,
  exec_datetime timestamp without time zone NOT NULL DEFAULT now(),
  cancel_staff character varying(255),
  cancel_datetime timestamp without time zone,
  CONSTRAINT t_lis_order_pkey PRIMARY KEY (t_lis_order_id),
  CONSTRAINT t_lis_order_t_order_id_key UNIQUE (t_order_id)
);

CREATE INDEX t_lis_order_t_order_id_idx
  ON t_lis_order
  USING btree
  (t_order_id);

INSERT INTO s_version VALUES ('9701000000038', '38', 'Hospital OS, Community Edition', '3.9.5', '3.18.090910', '2553-09-09 18:20:00');

INSERT INTO s_script_update_log VALUES ('hospitalOS','updateV3_95.sql',(select current_date) || ','|| (select current_time),'ปรับแก้สำหรับ hospitalOS3.9.5');

