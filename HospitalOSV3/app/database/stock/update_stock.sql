CREATE TABLE s_stock_version (
    s_stock_version_id character varying(255) NOT NULL,
    stock_version_number character varying(255) DEFAULT ''::character varying,
    stock_version_description character varying(255) DEFAULT ''::character varying,
    stock_version_application_number character varying(255) DEFAULT ''::character varying,
    stock_version_database_number character varying(255) DEFAULT ''::character varying,
    stock_version_update_time character varying(255) DEFAULT ''::character varying
);
ALTER TABLE ONLY s_stock_version
    ADD CONSTRAINT s_stock_version_pkey PRIMARY KEY (s_stock_version_id);

CREATE UNIQUE INDEX st_version_index ON s_stock_version USING btree (s_stock_version_id);
INSERT INTO s_stock_version VALUES ('st00100000001', '01', 'Stock Module', '1.0.230307', '1.0.090307', '2550-03-23 18:00:00');