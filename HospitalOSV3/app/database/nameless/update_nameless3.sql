CREATE TABLE t_invoice_billing_detail
(
  t_invoice_billing_detail_id character varying(255) NOT NULL,
  t_visit_id character varying(255) NOT NULL,
  invoice_book_number character varying(255),
  invoice_print_receipt_number character varying(255),
  invoice_receipt_name character varying(255),
  invoice_patient_name character varying(255),
  invoice_patient_hn character varying(255),
  invoice_receipt_type character varying(255),
  invoice_number character varying(255),
  invoice_print_type character varying(255),
  CONSTRAINT t_invoice_billing_detail_pkey PRIMARY KEY (t_invoice_billing_detail_id)
);

INSERT INTO s_nameless_version VALUES ('9750000000003', '3', 'NAMELESS, Community Edition', '1.03.121010', '1.03.121010', '2553-10-12 12:00:00');