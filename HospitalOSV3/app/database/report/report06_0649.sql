-- เพิ่มข้อมูลให้ตาราง r_accident_group จาก Phase น่าน  22/06/2549
INSERT INTO r_accident_group(r_accident_group_id, accident_group_number, accident_group_description, accident_group_active) VALUES('8150000000020', '1.1', 'จักรยานยนต์', '1');
INSERT INTO r_accident_group(r_accident_group_id, accident_group_number, accident_group_description, accident_group_active) VALUES('8150000000021', '1.2', 'รถยนต์อื่น ๆ', '1');
INSERT INTO r_accident_group(r_accident_group_id, accident_group_number, accident_group_description, accident_group_active) VALUES('8150000000022', '1.3', 'อื่น ๆ เช่น รถจักรยาน/คนเดิน/รถไถ ฯลฯ', '1');

-- เพิ่มข้อมูลให้ตาราง r_accident_group_code จาก Phase น่าน 22/06/2549
INSERT INTO r_accident_group_code(r_accident_group_code_id, r_accident_group_id, accident_group_code_begin, accident_group_code_end) 
    VALUES('8160000000021', '8150000000020', 'V20', 'V29.99');
INSERT INTO r_accident_group_code(r_accident_group_code_id, r_accident_group_id, accident_group_code_begin, accident_group_code_end) 
    VALUES('8160000000022', '8150000000021', 'V40', 'V49.99');
INSERT INTO r_accident_group_code(r_accident_group_code_id, r_accident_group_id, accident_group_code_begin, accident_group_code_end) 
    VALUES('8160000000023', '8150000000022', 'V10', 'V19.99');
INSERT INTO r_accident_group_code(r_accident_group_code_id, r_accident_group_id, accident_group_code_begin, accident_group_code_end) 
    VALUES('8160000000024', '8150000000022', 'V30', 'V39.99');
INSERT INTO r_accident_group_code(r_accident_group_code_id, r_accident_group_id, accident_group_code_begin, accident_group_code_end) 
    VALUES('8160000000025', '8150000000022', 'V50', 'V99.99');
INSERT INTO r_accident_group_code(r_accident_group_code_id, r_accident_group_id, accident_group_code_begin, accident_group_code_end) 
    VALUES('8160000000026', '8150000000022', 'V01', 'V09.99');
 

 -- เพิ่มตาราง r_ncd_item_group จากรายงาน น่าน
--
-- Name: r_ncd_item_group; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_ncd_item_group (
    r_ncd_item_group_id character varying(255) NOT NULL,
    ncd_item_group_number character varying(255) NOT NULL,
    ncd_item_group_description character varying(255) NOT NULL
);


ALTER TABLE public.r_ncd_item_group OWNER TO postgres;

--
-- Name: TABLE r_ncd_item_group; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_ncd_item_group IS 'เก็บข้อมูลกลุ่มรายการตรวจรักษาของ NCD ที่ต้องการออกรายงาน';


--
-- Name: r_ncd_item_group_map_item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_ncd_item_group_map_item (
    r_ncd_item_group_map_item_id character varying(255) NOT NULL,
    r_ncd_item_group_id character varying(255) NOT NULL,
    b_item_id character varying(255) NOT NULL,
    ncd_item_group_map_item_number character varying(255) NOT NULL,
    ncd_item_group_map_item_description character varying(255) NOT NULL
);


ALTER TABLE public.r_ncd_item_group_map_item OWNER TO postgres;

--
-- Name: TABLE r_ncd_item_group_map_item; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_ncd_item_group_map_item IS 'เก็บข้อมูลการ map กลุ่มตรวจรักษาของ NCD กับ รายการตรวจรักษา';


--
-- Name: r_opd_item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_opd_item (
    r_opd_item_id character varying(255) NOT NULL,
    b_item_id character varying(255) NOT NULL,
    opd_item_number character varying(255) NOT NULL,
    opd_item_common_name character varying(255) NOT NULL,
    opd_item_description character varying(255) NOT NULL
);


ALTER TABLE public.r_opd_item OWNER TO postgres;

--
-- Name: TABLE r_opd_item; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_opd_item IS 'เก็บข้อมูลรายการตรวจรักษา ที่ต้องการดูจำนวนผู้ที่ได้รับการตรวจในรายการตรวจดังกล่าว';


--
-- Name: r_operating_item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_operating_item (
    r_operating_item_id character varying(255) NOT NULL,
    b_item_id character varying(255) NOT NULL,
    operating_item_number character varying(255) NOT NULL,
    operating_item_description character varying(255),
    operating_item_common_name character varying(255)
);


ALTER TABLE public.r_operating_item OWNER TO postgres;

--
-- Name: TABLE r_operating_item; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_operating_item IS 'ตารางเก็บข้อมูลรายการตรวจรักษาที่เป็นรายการผ่าตัด';


--
-- Name: r_refer_office; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_refer_office (
    r_refer_office_id character varying(255) NOT NULL,
    refer_office_code character varying(255) NOT NULL,
    refer_office_description character varying(255),
    r_refer_office_type_id character varying(255) NOT NULL
);


ALTER TABLE public.r_refer_office OWNER TO postgres;

--
-- Name: TABLE r_refer_office; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_refer_office IS 'เก็บข้อมูลสถานพยาบาลที่ต้องการดูข้อมูล refer';


--
-- Name: r_refer_office_type; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_refer_office_type (
    r_refer_office_type_id character varying(255) NOT NULL,
    refer_office_type_description character varying(255) NOT NULL
);


ALTER TABLE public.r_refer_office_type OWNER TO postgres;

--
-- Name: TABLE r_refer_office_type; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_refer_office_type IS 'เก็บชนิดของสถานพยาบาล เช่น สอ , รพช , รพท รพศ';


--
-- Name: r_service_point_type; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_service_point_type (
    r_service_point_type_id character varying(255) NOT NULL,
    service_point_type_number character varying(255) NOT NULL,
    service_point_type_description character varying(255) NOT NULL
);


ALTER TABLE public.r_service_point_type OWNER TO postgres;

--
-- Name: TABLE r_service_point_type; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_service_point_type IS 'เก็บข้อมูลชนิดของจุดบริการสำหรับการออกรายงาน';


--
-- Name: r_service_point_type_map; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_service_point_type_map (
    r_service_point_type_map_id character varying(255) NOT NULL,
    r_service_point_type_id character varying(255) NOT NULL,
    b_service_point_id character varying(255) NOT NULL,
    service_point_description character varying(255) NOT NULL,
    service_point_type_description character varying(255) NOT NULL
);


ALTER TABLE public.r_service_point_type_map OWNER TO postgres;

--
-- Name: TABLE r_service_point_type_map; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_service_point_type_map IS 'เก็บข้อมูลการ map ระหว่างชนิดจุดบริการที่จะออกรายงาน กับ จุดบริการของโรงพยาบาล';


--
-- Data for Name: r_ncd_item_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

-- insert into  r_ncd_item_group values ('r_ncd_item_group_id, ncd_item_group_number, ncd_item_group_description) FROM stdin;
insert into  r_ncd_item_group values ('8440000000001','1','FBS');
insert into  r_ncd_item_group values ('8440000000002','2','ส่งพบโภชนากร');
insert into  r_ncd_item_group values ('8440000000003','3','ส่งพบเภสัชกร');





--
-- Data for Name: r_refer_office_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

--insert into  r_refer_office_type values ('r_refer_office_type_id, refer_office_type_description) FROM stdin;
insert into  r_refer_office_type values ('01','สอ.');
insert into  r_refer_office_type values ('02','รพช.');
insert into  r_refer_office_type values ('03','รพท. หรือ รพศ.');



--
-- Data for Name: r_service_point_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

-- insert into  r_service_point_type values (' (r_service_point_type_id, service_point_type_number, service_point_type_description) FROM stdin;
insert into  r_service_point_type values ('8420000000001','1','อุบัติเหตุ-ฉุกเฉิน');
insert into  r_service_point_type values ('8420000000002','2','OPD');
insert into  r_service_point_type values ('8420000000003','3','NCD');





--
-- Name: r_ncd_item_group_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_ncd_item_group
    ADD CONSTRAINT r_ncd_item_group_id_pk PRIMARY KEY (r_ncd_item_group_id);


ALTER INDEX public.r_ncd_item_group_id_pk OWNER TO postgres;

--
-- Name: r_ncd_item_group_map_item_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_ncd_item_group_map_item
    ADD CONSTRAINT r_ncd_item_group_map_item_id_pk PRIMARY KEY (r_ncd_item_group_map_item_id);


ALTER INDEX public.r_ncd_item_group_map_item_id_pk OWNER TO postgres;

--
-- Name: r_opd_item_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_opd_item
    ADD CONSTRAINT r_opd_item_id_pk PRIMARY KEY (r_opd_item_id);


ALTER INDEX public.r_opd_item_id_pk OWNER TO postgres;

--
-- Name: r_operating_item_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_operating_item
    ADD CONSTRAINT r_operating_item_id_pk PRIMARY KEY (r_operating_item_id);


ALTER INDEX public.r_operating_item_id_pk OWNER TO postgres;

--
-- Name: r_refer_office_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_refer_office
    ADD CONSTRAINT r_refer_office_id_pk PRIMARY KEY (r_refer_office_id);


ALTER INDEX public.r_refer_office_id_pk OWNER TO postgres;

--
-- Name: r_refer_office_type_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_refer_office_type
    ADD CONSTRAINT r_refer_office_type_id_pk PRIMARY KEY (r_refer_office_type_id);


ALTER INDEX public.r_refer_office_type_id_pk OWNER TO postgres;

--
-- Name: r_service_point_type_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_service_point_type
    ADD CONSTRAINT r_service_point_type_id_pk PRIMARY KEY (r_service_point_type_id);


ALTER INDEX public.r_service_point_type_id_pk OWNER TO postgres;

--
-- Name: r_service_point_type_map_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_service_point_type_map
    ADD CONSTRAINT r_service_point_type_map_id_pk PRIMARY KEY (r_service_point_type_map_id);


ALTER INDEX public.r_service_point_type_map_id_pk OWNER TO postgres;

insert into s_script_update_log values ('report','report06_0649.sql',(select current_date) || ','|| (select current_time),'เป็น update report ตัวที่ 6');

-- insert into  s_report_version values ('(s_report_version_id, report_version_number, report_version_description, report_version_application_number, report_version_notice, report_version_update_date_time) FROM stdin;
insert into  s_report_version values ('9720000000006','06','HospitalOS Report For Nan','1.06.220606','for hospitalOS v3 db : 3.14n.050506 and pcu db : 0.04.0449','2549-06-22 11:16:00');

