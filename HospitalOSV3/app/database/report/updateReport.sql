--
-- PostgreSQL database dump
--

SET client_encoding = 'UNICODE';
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'Standard public schema';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = true;

--
-- Name: r_disease_504_code; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_disease_504_code (
    code character varying,
    begin_code character varying,
    end_code character varying
);


ALTER TABLE public.r_disease_504_code OWNER TO postgres;

--
-- Name: r_disease_505_code; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_disease_505_code (
    code character varying,
    begin_code character varying,
    end_code character varying
);


ALTER TABLE public.r_disease_505_code OWNER TO postgres;



ALTER TABLE public.r_hospital_incup OWNER TO postgres;

--
-- Name: r_map_pttype; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_map_pttype (
    map_type_id character varying,
    plan_type_id character varying,
    pttype character varying
);


ALTER TABLE public.r_map_pttype OWNER TO postgres;

--
-- Name: r_map_uc_plan; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_map_uc_plan (
    r_map_uc_plan_type_id character varying,
    pttype character varying
);


ALTER TABLE public.r_map_uc_plan OWNER TO postgres;

--
-- Name: r_plan_type; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_plan_type (
    plan_type_id character varying NOT NULL,
    plan_type_name character varying,
    plan_type_description character varying
);


ALTER TABLE public.r_plan_type OWNER TO postgres;

--
-- Name: r_sql_template; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_sql_template (
    r_sql_template_id character varying(255) NOT NULL,
    sql_template_number character varying(255),
    sql_template_description character varying(255),
    sql_template_sql character varying(255),
    sql_template_is_query_by_date character varying(4000),
    sql_template_active character varying(255)
);


ALTER TABLE public.r_sql_template OWNER TO postgres;

--
-- Name: r_uc_plan_type; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_uc_plan_type (
    r_uc_plan_type_id character varying,
    uc_plan_name character varying,
    uc_plan_description character varying
);


ALTER TABLE public.r_uc_plan_type OWNER TO postgres;

--
-- Data for Name: r_disease_504_code; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY r_disease_504_code (code, begin_code, end_code) FROM stdin;
1	A00	A99.99
1	B00	B99.99
2	C00	C97.99
2	D00	D48.99
3	D50	D89.99
4	E00	E99.99
5	F00	F99.99
6	G00	G99.99
7	H00	H59.99
8	H60	H95.99
9	I00	I99.99
10	J00	J99.99
11	K00	K93.99
12	L00	L99.99
13	M00	M99.99
14	N00	N99.99
15	O00	O79.99
15	O85	O99.99
16	P00	P96.99
17	Q00	Q99.99
18	R00	R99.99
19	X40	X49.99
19	X60	X69.99
19	X85	X90.99
19	Y10	Y19.99
20	V01	V99.99
20	Y85	Y85.99
21	W00	W99.99
21	X00	X19.99
21	X20	X29.99
21	X30	X39.99
21	X50	X59.99
21	X70	X84.99
21	X91	X99.99
21	Y00	Y09.99
21	Y20	Y36.99
21	Y40	Y84.99
21	Y86	Y89.99
99	D90.00	D99.99
99	K94.00	K99.99
99	O80.00	O84.99
99	P97.00	P99.99
99	S00.00	S99.99
99	T00.00	T99.99
99	U00.00	U99.99
99	V00.99	V09.99
99	X40.00	X49.99
99	X60.00	X69.99
99	Y10.00	Y19.99
99	Y90.00	Y99.99
99	Z00.00	Z99.99
\.


--
-- Data for Name: r_disease_505_code; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY r_disease_505_code (code, begin_code, end_code) FROM stdin;
1	A01	A02.98
2	A03	A09.99
3	A15	A19.99
4	A30	A30.99
5	A83	A86.99
6	A91	A92.99
7	B15	B19.99
8	B20	B24.99
9	B50	B54.99
10	A20	A28.99
10	A31	A32.99
10	A38	A44.99
10	A46	A49.99
10	A50	A60.99
10	A63	A71.99
10	A74	A75.99
10	A77	A82.99
10	A87	A90.99
10	A93	A96.99
10	B25	B27.99
10	B30	B30.99
10	B33	B49.99
10	B55	B60.99
10	B64	B83.99
10	B85	B892.99
10	B94	B97.99
10	B99	B99.99
11	C22	C22.99
12	C34	C34.99
13	C50	C50.99
14	C53	C53.99
15	D50	D53.99
15	D55	D55.99
15	D57	D77.99
15	D80	D84.99
15	D86	D86.99
15	D89	D89.99
16	D56	D56.99
17	E00	E07.99
18	E10	E14.99
19	E15	E16.99
19	E20	E32.99
19	E34	E35.99
19	E40	E46.99
19	E50	E56.99
19	E58	E61.99
19	E63	E68.99
19	E70	E80.99
19	E83	E90.99
20	F04	F07.99
20	F09	F09.99
21	F10	F19.99
22	F20	F25.99
22	F28	F34.99
23	F38	F39.99
24	F40	F45.99
24	F48	F48.99
25	F70	F73.99
25	F78	F79.99
26	G40	G41.99
27	G00	G13.99
27	G20	G26.99
27	G30	G32.99
27	G35	G37.99
27	G43	G47.99
27	G50	G64.99
27	G70	G73.99
27	G80	G83.99
27	G90	G99.99
28	H00	H06.99
28	H10	H11.99
28	H13	H13.99
28	H15	H22.99
28	H25	H28.99
28	H30	H36.99
28	H40	H40.99
28	H42	H55.99
28	H57	H62.99
29	H65	H75.99
29	H80	H83.99
29	H90	H95.99
30	I00	I02.99
31	I05	I13.99
32	I15	I15.99
33	I20	I52.99
35	I60	I74.99
36	I77	I89.99
36	I95	I95.99
36	I97	I99.99
37	J00	J06.99
37	J30	J37.99
38	J10	J18.99
40	J40	J44.99
40	J47	J47.99
41	J45	J46.99
42	J20	J22.99
42	J60	J70.99
42	J80	J82.99
42	J84	J86.99
42	J90	J96.99
42	J98	J99.99
43	K25	K27.99
44	K25	K38.99
45	K40	K46.99
46	K50	K52.99
46	K55	K55.99
46	K65	K67.99
47	K56	K56.99
48	K70	K70.99
49	K80	K81.99
50	K00	K14.99
50	K20	K23.99
50	K28	K31.99
50	K57	K63.99
50	K71	K77.99
50	K82	K83.99
50	K85	K87.99
50	K90	K93.99
51	L00	L05.99
51	L08	L08.99
51	L10	L14.99
51	L20	L30.99
51	L40	L45.99
51	L50	L60.99
51	L62	L68.99
51	L70	L75.99
51	L80	L99.99
52	M00	M03.99
52	M05	M25.99
52	M40	M43.99
52	M45	M51.99
52	M53	M54.99
52	M60	M63.99
52	M65	M68.99
52	M70	M73.99
52	M75	M77.99
52	M79	M96.99
52	M99	M99.99
53	M30	M36.99
54	N17	N23.99
57	N40	N51.99
58	N60	N64.99
59	N70	N77.99
59	N80	N97.99
59	N98	N98.99
60	N00	N08.99
60	N10	N16.99
60	N25	N37.99
60	N39	N39.99
60	N99	N99.99
61	O00	O08.99
63	O10	O16.99
63	O20	O26.99
63	O28	O36.99
63	O40	O48.99
63	O60	O75.99
63	O81	O92.99
63	O95	O99.99
64	P10	P15.99
65	P00	P05.99
65	P07	P08.99
65	P20	P29.99
65	P35	P39.99
65	P50	P61.99
65	P70	P72.99
65	P74	P78.99
65	P80	P81.99
65	P83	P83.99
65	P90	P96.99
66	Q00	Q07.99
66	Q10	Q18.99
66	Q20	Q28.99
66	Q30	Q45.99
66	Q50	Q56.99
66	Q60	Q93.99
66	Q95	Q99.99
67	R00	R07.99
67	R09	R23.99
67	R25	R27.99
67	R29	R36.99
67	R39	R64.99
67	R68	R87.99
67	R89	R96.99
67	R98	R99.99
68	V01	V06.99
68	V09	V99.99
70	Y85	Y85.99
71	X40	X49.99
71	X60	X69.99
71	X85	X99.99
71	Y10	Y19.99
72	W00	W45.99
72	W49	W60.99
72	W64	W70.99
72	W73	W81.99
72	W83	W94.99
72	W99	X06.99
72	X08	X39.99
72	X50	X54.99
72	X57	X59.99
72	Y86	Y86.99
73	X70	X84.99
74	X91	Y36.99
75	Y40	Y66.99
75	Y69	Y84.99
75	Y87	Y89.99
99	A33	A37.99
99	C00	C33.99
99	C37	C52.99
99	C54	C58.99
99	C60	C85.99
99	C88	C88.99
99	C90	C97.99
99	D00	D07.99
99	D09	D48.9
99	F00	F03.99
99	F10	F19.99
99	F50	F55.99
99	F59	F66.99
99	F68	F69.99
99	F80	F84.99
99	F88	F95.99
99	F98	F99.99
99	S00	S99.99
99	T00	T75.99
99	T78	T88.99
99	T90	T98.99
99	Y90	Y91.99
99	Y95	Y98.99
99	Z00	Z04.99
99	Z08	Z13.99
99	Z20	Z65.99
99	Z70	Z76.99
99	Z80	Z99.99
10	A98	B09.99
\.





--
-- Data for Name: r_map_pttype; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY r_map_pttype (map_type_id, plan_type_id, pttype) FROM stdin;
1	1	A2
2	2	A7
3	3	UC
4	4	AE
5	4	AL
6	4	AF
7	4	AJ
8	4	AK
9	4	AB
10	4	AG
11	4	AA
12	4	AD
13	4	AC
15	5	AR
16	6	A1
17	6	AM
18	6	AN
19	6	AP
20	6	A9
\.


--
-- Data for Name: r_map_uc_plan; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY r_map_uc_plan (r_map_uc_plan_type_id, pttype) FROM stdin;
1	UC
2	AE
3	AL
4	AF
5	AJ
6	AK
7	AB
8	AG
9	AA
10	AD
11	AC
\.


--
-- Data for Name: r_plan_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY r_plan_type (plan_type_id, plan_type_name, plan_type_description) FROM stdin;
1	ข้าราชการ / รัฐวิสาหกิจ	ข้าราชการ / รัฐวิสาหกิจ
2	ประกันสังคม	ประกันสังคม
3	UC บัตรทองไม่มี ท.	UC บัตรทองไม่มี ท.
4	UC บัตรทองมี ท.	UC บัตรทองมี ท.
5	แรงงานต่างด้าวที่ขึ้นทะเบียน	แรงงานต่างด้าวที่ขึ้นทะเบียน
6	อื่นๆ (ต่างด้าวไม่ขึ้นทะเบียน,สิทธิไม่ชัดเจน,ไม่ใช้สิทธิ)	อื่นๆ (ต่างด้าวไม่ขึ้นทะเบียน,สิทธิไม่ชัดเจน,ไม่ใช้สิทธิ)
\.


--
-- Data for Name: r_sql_template; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY r_sql_template (r_sql_template_id, sql_template_number, sql_template_description, sql_template_sql, sql_template_is_query_by_date, sql_template_active) FROM stdin;
000008583004959	01	test_sql	select visit_hn,visit_vn,visit_begin_visit_time from t_visit where  substring(visit_begin_visit_time,0,11) >= ? and  substring(visit_begin_visit_time,0,11) <=  ?;	1	0
000002417656910	03	test_sql2	select t_visit from t_visit where t_visit.hn like '%123453';	0	0
000009646207667	02	test_sql1	select * from b_employee;	0	0
\.


--
-- Data for Name: r_uc_plan_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY r_uc_plan_type (r_uc_plan_type_id, uc_plan_name, uc_plan_description) FROM stdin;
1	UC ในเครือข่าย	UC ในเครือข่าย
2	UC นอกเครือข่าย	UC นอกเครือข่าย  
3	สิทธิอื่นๆ	สิทธิอื่นๆ  
\.

--
-- Name: id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_plan_type
    ADD CONSTRAINT id PRIMARY KEY (plan_type_id);


ALTER INDEX public.id OWNER TO postgres;

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
---------------------------------------------------------------------------------------------------------------------------------------


DROP TABLE r_antibiotic;
DROP TABLE r_aric;
DROP TABLE r_aric_code;
DROP TABLE r_aric_group;
DROP TABLE r_disease_504_code ;
DROP TABLE r_disease_505_code;
DROP TABLE r_eye_disease_code ;
DROP TABLE r_eye_group;
DROP TABLE r_map_pttype;
DROP TABLE r_map_uc_plan;
DROP TABLE r_plan_type;
DROP TABLE r_sql_template;
DROP TABLE r_uc_plan_type;
--
CREATE TABLE r_accident_group (
    r_accident_group_id character varying(255) NOT NULL,
    accident_group_number character varying(255),
    accident_group_description character varying(255),
    accident_group_active character varying(255)
);

CREATE TABLE r_accident_group_code (
    r_accident_group_code_id character varying(255) NOT NULL,
    r_accident_group_id character varying(255),
    accident_group_code_begin character varying(255),
    accident_group_code_end character varying(255)
);

CREATE TABLE r_aric_antibiotic (
    r_aric_antibiotic_id character varying(255) NOT NULL,
    b_item_id character varying(255),
    aric_antibiotic_code character varying(255),
    aric_antibiotic_common_name character varying(255)
);

CREATE TABLE r_aric_disease_code (
    r_aric_disease_code_id character varying(255) NOT NULL,
    r_aric_group_id character varying(255),
    aric_disease_code_begin character varying(255),
    aric_disease_code_end character varying(255)
);

CREATE TABLE r_aric_group (
    r_aric_group_id character varying(255) NOT NULL,
    aric_group_number character varying(255),
    aric_group_description character varying(255)
);

COMMENT ON TABLE r_aric_group IS 'กลุ่มของ ARIC';

CREATE TABLE r_aric_subgroup (
    r_aric_subgroup_id character varying(255) NOT NULL,
    aric_subgroup_number character varying(255),
    aric_subgroup_description character varying(255),
    r_aric_group_id character varying(255)
);

CREATE TABLE r_eye_disease_code (
    r_eye_disease_code_id character varying(255) NOT NULL,
    r_eye_group_id character varying(255),
    eye_disease_code_begin character varying(255),
    eye_disease_code_end character varying(255)
);

CREATE TABLE r_eye_group (
    r_eye_group_id character varying(255) NOT NULL,
    eye_group_number character varying(255),
    eye_group_description_th character varying(255),
    eye_group_description_en character varying(255),
    eye_group_active character varying(255)
);

CREATE TABLE r_plan_group (
    r_plan_group_id character varying(255) NOT NULL,
    plan_group_number character varying(255),
    plan_group_description character varying(255),
    plan_group_active character varying(255)
);

CREATE TABLE r_plan_group_map_pttype (
    r_plan_group_map_pttype_id character varying(255) NOT NULL,
    r_plan_group_id character varying(255),
    plan_group_map_pttype_pttype character varying(255)
);

CREATE TABLE r_rp504_disease_code (
    r_rp504_disease_code_id character varying(255) NOT NULL,
    f_group_rp504_id character varying(255),
    rp504_disease_code_number character varying(255),
    rp504_disease_code_begin character varying(255),
    rp504_disease_code_end character varying(255)
);

CREATE TABLE r_rp505_disease_code (
    r_rp505_disease_code_id character varying(255) NOT NULL,
    f_group_rp505_id character varying(255),
    rp505_disease_code_number character varying(255),
    rp505_disease_code_begin character varying(255),
    rp505_disease_code_end character varying(255)
);

CREATE TABLE r_sql_template (
    r_sql_template_id character varying(255) NOT NULL,
    sql_template_number character varying(255),
    sql_template_description character varying(255),
    sql_template_sql character varying(255),
    sql_template_is_query_by_date character varying(4000),
    sql_template_active character varying(255)
);

CREATE TABLE r_uc_plan_group (
    r_uc_plan_group_id character varying(255) NOT NULL,
    uc_plan_group_number character varying(255),
    uc_plan_group_description character varying(255),
    uc_plan_group_active character varying(255)
);

CREATE TABLE r_uc_plan_group_pttype (
    r_uc_plan_group_pttype_id character varying(255) NOT NULL,
    uc_plan_group_pttype_number character varying(255),
    uc_plan_group_pttype_pttype character varying(255)
);


ALTER TABLE ONLY r_accident_group_code
    ADD CONSTRAINT r_accident_group_code_pk PRIMARY KEY (r_accident_group_code_id);

ALTER TABLE ONLY r_accident_group
    ADD CONSTRAINT r_accident_group_pk PRIMARY KEY (r_accident_group_id);

ALTER TABLE ONLY r_aric_antibiotic
    ADD CONSTRAINT r_aric_antibiotic_id PRIMARY KEY (r_aric_antibiotic_id);

ALTER TABLE ONLY r_aric_disease_code
    ADD CONSTRAINT r_aric_disease_code_id PRIMARY KEY (r_aric_disease_code_id);

ALTER TABLE ONLY r_aric_group
    ADD CONSTRAINT r_aric_group_id_pk PRIMARY KEY (r_aric_group_id);

ALTER TABLE ONLY r_aric_subgroup
    ADD CONSTRAINT r_aric_subgroup_id PRIMARY KEY (r_aric_subgroup_id);

ALTER TABLE ONLY r_eye_disease_code
    ADD CONSTRAINT r_eye_disease_code_id PRIMARY KEY (r_eye_disease_code_id);

ALTER TABLE ONLY r_eye_group
    ADD CONSTRAINT r_eye_disease_group_id PRIMARY KEY (r_eye_group_id);

ALTER TABLE ONLY r_hospital_incup
    ADD CONSTRAINT r_hospital_incup_id_pk PRIMARY KEY (hospital_incup_id);

ALTER TABLE ONLY r_plan_group
    ADD CONSTRAINT r_plan_group_id PRIMARY KEY (r_plan_group_id);

ALTER TABLE ONLY r_plan_group_map_pttype
    ADD CONSTRAINT r_plan_group_map_pttype_id_pk PRIMARY KEY (r_plan_group_map_pttype_id);

ALTER TABLE ONLY r_rp504_disease_code
    ADD CONSTRAINT r_rp504_disease_code_id_pk PRIMARY KEY (r_rp504_disease_code_id);

ALTER TABLE ONLY r_rp505_disease_code
    ADD CONSTRAINT r_rp505_disease_code_id_pk PRIMARY KEY (r_rp505_disease_code_id);

ALTER TABLE ONLY r_sql_template
    ADD CONSTRAINT r_sql_template_id_pk PRIMARY KEY (r_sql_template_id);

ALTER TABLE ONLY r_uc_plan_group
    ADD CONSTRAINT r_uc_plan_group_id_pk PRIMARY KEY (r_uc_plan_group_id);

ALTER TABLE ONLY r_uc_plan_group_pttype
    ADD CONSTRAINT r_uc_plan_group_pttype_id_pk PRIMARY KEY (r_uc_plan_group_pttype_id);


CREATE INDEX aric_antibiotic_item ON r_aric_antibiotic USING btree (b_item_id);

CREATE INDEX aric_code_begin ON r_aric_disease_code USING btree (aric_disease_code_begin);

CREATE INDEX aric_code_end ON r_aric_disease_code USING btree (aric_disease_code_end);

CREATE INDEX aric_group_id_ind ON r_aric_subgroup USING btree (r_aric_group_id);

CREATE INDEX code_begin ON r_accident_group_code USING btree (accident_group_code_begin);

CREATE INDEX code_end ON r_accident_group_code USING btree (accident_group_code_end);

CREATE INDEX eye_code_begin ON r_eye_disease_code USING btree (eye_disease_code_begin);

CREATE INDEX eye_code_end ON r_eye_disease_code USING btree (eye_disease_code_end);

CREATE INDEX eye_disease_group_number ON r_eye_group USING btree (eye_group_number);

CREATE INDEX f_group_rp504_ind ON r_rp504_disease_code USING btree (f_group_rp504_id);

CREATE INDEX f_group_rp505_ind ON r_rp505_disease_code USING btree (f_group_rp505_id);

CREATE INDEX plan_group_id_ide ON r_plan_group_map_pttype USING btree (r_plan_group_id);

CREATE INDEX plan_group_map_ind ON r_plan_group_map_pttype USING btree (plan_group_map_pttype_pttype);

CREATE INDEX r_eye_group_id_ind ON r_eye_disease_code USING btree (r_eye_group_id);

CREATE INDEX rp504_code_begin ON r_rp504_disease_code USING btree (rp504_disease_code_begin);

CREATE INDEX rp504_code_end ON r_rp504_disease_code USING btree (rp504_disease_code_end);

CREATE INDEX rp505_code_begin ON r_rp505_disease_code USING btree (rp505_disease_code_begin);

CREATE INDEX rp505_code_end ON r_rp505_disease_code USING btree (rp505_disease_code_end);

CREATE INDEX up_plan_group_pttype_ind ON r_uc_plan_group_pttype USING btree (uc_plan_group_pttype_pttype);



INSERT INTO r_accident_group VALUES ('8150000000001', '1', 'อุบัติเหตุจากการขนส่ง', '1');
INSERT INTO r_accident_group VALUES ('8150000000002', '2', 'อุบัติเหตุพลัดตกหรือหกล้ม', '1');
INSERT INTO r_accident_group VALUES ('8150000000003', '3', 'อุบัติเหตุสัมผัสกับแรงเชิงกลของวัตถุสิ่งของ', '1');
INSERT INTO r_accident_group VALUES ('8150000000004', '4', 'สัมผัสกับแรงเชิงกลของสัตว์/คน', '1');
INSERT INTO r_accident_group VALUES ('8150000000005', '5', 'อุบัติเหตุจากการตกน้ำ จมน้ำ', '1');
INSERT INTO r_accident_group VALUES ('8150000000006', '6', 'อุบัติเหตุที่คุกคามการหายใจ', '1');
INSERT INTO r_accident_group VALUES ('8150000000007', '7', 'อุบัติเหตุสัมผัสกระแสไฟฟ้า รังสีและอุณหภูมิ', '1');
INSERT INTO r_accident_group VALUES ('8150000000008', '8', 'อุบัติเหตุสัมผัสควันไฟ และเปลวไฟ', '1');
INSERT INTO r_accident_group VALUES ('8150000000009', '9', 'อุบัติเหตุสัมผัสความร้อน ของร้อน', '1');
INSERT INTO r_accident_group VALUES ('8150000000010', '10', 'อุบัติเหตุสัมผัสพิษจากสัตว์หรือพืช', '1');
INSERT INTO r_accident_group VALUES ('8150000000011', '11', 'อุบัติเหตุสัมผัสพลังงานจากธรรมชาติ', '1');
INSERT INTO r_accident_group VALUES ('8150000000012', '12', 'อุบัติเหตุสัมผัสพิษและสารอื่นๆ', '1');
INSERT INTO r_accident_group VALUES ('8150000000013', '13', 'อุบัติเหตุการออกแรงเกิน', '1');
INSERT INTO r_accident_group VALUES ('8150000000014', '14', 'อุบัติเหตุสัมผัสกับสิ่งไม่ทราบแน่ชัด', '1');
INSERT INTO r_accident_group VALUES ('8150000000015', '15', 'ทำร้ายตัวเองด้วยวิธีต่างๆ', '1');
INSERT INTO r_accident_group VALUES ('8150000000016', '16', 'ถูกทำร้ายด้วยวิธีต่างๆ', '1');
INSERT INTO r_accident_group VALUES ('8150000000017', '17', 'บาดเจ็บโดยไม่ทราบเจตนา', '1');
INSERT INTO r_accident_group VALUES ('8150000000018', '18', 'ดำเนินการทางกฏหมายหรือสงคราม', '1');
INSERT INTO r_accident_group VALUES ('8150000000019', '19', 'ไม่ทราบทั้งสาเหตุและเจตนา', '1');

INSERT INTO r_accident_group_code VALUES ('8160000000001', '8150000000001', 'V01', 'V99.99');
INSERT INTO r_accident_group_code VALUES ('8160000000002', '8150000000002', 'W00', 'W19.99');
INSERT INTO r_accident_group_code VALUES ('8160000000003', '8150000000003', 'W20', 'W49.99');
INSERT INTO r_accident_group_code VALUES ('8160000000004', '8150000000004', 'W50', 'W64.99');
INSERT INTO r_accident_group_code VALUES ('8160000000005', '8150000000005', 'W65', 'W74.99');
INSERT INTO r_accident_group_code VALUES ('8160000000006', '8150000000006', 'W75', 'W84.99');
INSERT INTO r_accident_group_code VALUES ('8160000000007', '8150000000007', 'W85', 'W99.99');
INSERT INTO r_accident_group_code VALUES ('8160000000008', '8150000000008', 'X00', 'X09.99');
INSERT INTO r_accident_group_code VALUES ('8160000000009', '8150000000009', 'X10', 'X19.99');
INSERT INTO r_accident_group_code VALUES ('8160000000010', '8150000000010', 'X20', 'X29.99');
INSERT INTO r_accident_group_code VALUES ('8160000000011', '8150000000011', 'X30', 'X39.99');
INSERT INTO r_accident_group_code VALUES ('8160000000012', '8150000000012', 'X40', 'X49.99');
INSERT INTO r_accident_group_code VALUES ('8160000000013', '8150000000013', 'X50', 'X57.99');
INSERT INTO r_accident_group_code VALUES ('8160000000014', '8150000000014', 'X58', 'X59.99');
INSERT INTO r_accident_group_code VALUES ('8160000000015', '8150000000015', 'X60', 'X84.99');
INSERT INTO r_accident_group_code VALUES ('8160000000016', '8150000000016', 'X85', 'X99.99');
INSERT INTO r_accident_group_code VALUES ('8160000000017', '8150000000016', 'Y00', 'Y09.99');
INSERT INTO r_accident_group_code VALUES ('8160000000018', '8150000000017', 'Y10', 'Y33.99');
INSERT INTO r_accident_group_code VALUES ('8160000000019', '8150000000018', 'Y35', 'Y36.99');
INSERT INTO r_accident_group_code VALUES ('8160000000020', '8150000000019', 'Y34', 'Y34.99');


INSERT INTO r_aric_disease_code VALUES ('8120000000001', '8090000000001', 'J00', 'J00');
INSERT INTO r_aric_disease_code VALUES ('8120000000002', '8090000000001', 'J06', 'J06.99');
INSERT INTO r_aric_disease_code VALUES ('8120000000003', '8090000000001', 'J02', 'J02.99');
INSERT INTO r_aric_disease_code VALUES ('8120000000004', '8090000000001', 'J03', 'J03.99');
INSERT INTO r_aric_disease_code VALUES ('8120000000005', '8090000000001', 'J20', 'J20.99');
INSERT INTO r_aric_disease_code VALUES ('8120000000006', '8090000000002', 'J12', 'J12.99');
INSERT INTO r_aric_disease_code VALUES ('8120000000007', '8090000000002', 'J18', 'J18.99');

INSERT INTO r_aric_group VALUES ('8090000000001', '1', 'URI');
INSERT INTO r_aric_group VALUES ('8090000000002', '2', 'ปอดบวม');

INSERT INTO r_aric_subgroup VALUES ('8100000000002', 'ARIC02', 'URI ไม่รับยา Antibiotic', '8090000000001');
INSERT INTO r_aric_subgroup VALUES ('8100000000004', 'ARIC04', 'ปอดบวม ไม่รับยา Antibiotic', '8090000000002');
INSERT INTO r_aric_subgroup VALUES ('8100000000003', 'ARIC03', 'ปอดบวม รับยา Antibiotic', '8090000000002');
INSERT INTO r_aric_subgroup VALUES ('8100000000001', 'ARIC01', 'URI รับยา Antibiotic', '8090000000001');

INSERT INTO r_eye_disease_code VALUES ('8140000000001', '8130000000001', 'H25', 'H26.99');
INSERT INTO r_eye_disease_code VALUES ('8140000000002', '8130000000002', 'H40', 'H42.99');
INSERT INTO r_eye_disease_code VALUES ('8140000000003', '8130000000003', 'B02.3', 'B02.39');
INSERT INTO r_eye_disease_code VALUES ('8140000000004', '8130000000003', 'H10', 'H10.99');
INSERT INTO r_eye_disease_code VALUES ('8140000000005', '8130000000004', 'P39.1', 'P39.19');
INSERT INTO r_eye_disease_code VALUES ('8140000000006', '8130000000005', 'H16', 'H16.09');
INSERT INTO r_eye_disease_code VALUES ('8140000000007', '8130000000006', 'A71', 'A71.99');
INSERT INTO r_eye_disease_code VALUES ('8140000000008', '8130000000007', 'H11', 'H11.09');
INSERT INTO r_eye_disease_code VALUES ('8140000000009', '8130000000008', 'E05', 'E05.99');
INSERT INTO r_eye_disease_code VALUES ('8140000000010', '8130000000009', 'S05', 'S05.99');
INSERT INTO r_eye_disease_code VALUES ('8140000000011', '8130000000010', 'H52', 'H52.99');
INSERT INTO r_eye_disease_code VALUES ('8140000000012', '8130000000011', 'H00', 'H06.39');
INSERT INTO r_eye_disease_code VALUES ('8140000000014', '8130000000011', 'H13', 'H13.89');
INSERT INTO r_eye_disease_code VALUES ('8140000000015', '8130000000011', 'H15', 'H22.89');
INSERT INTO r_eye_disease_code VALUES ('8140000000016', '8130000000011', 'H30', 'H36.89');
INSERT INTO r_eye_disease_code VALUES ('8140000000017', '8130000000011', 'H42', 'H51.99');
INSERT INTO r_eye_disease_code VALUES ('8140000000018', '8130000000011', 'H53', 'H55.09');
INSERT INTO r_eye_disease_code VALUES ('8140000000019', '8130000000011', 'H57', 'H62.99');
INSERT INTO r_eye_disease_code VALUES ('8140000000020', '8130000000011', 'H65', 'H71.09');
INSERT INTO r_eye_disease_code VALUES ('8140000000021', '8130000000011', 'H72', 'H75.99');
INSERT INTO r_eye_disease_code VALUES ('8140000000022', '8130000000011', 'H80', 'H83.09');
INSERT INTO r_eye_disease_code VALUES ('8140000000023', '8130000000011', 'H90', 'H95.19');

INSERT INTO r_eye_group VALUES ('8130000000001', '1', 'ต้อกระจก', 'Cataract', '1');
INSERT INTO r_eye_group VALUES ('8130000000002', '2', 'ต้อหิน', 'Glaucoma', '1');
INSERT INTO r_eye_group VALUES ('8130000000004', '4', 'เยื่อตาอักเสบในเด็กแรกเกิด', 'Neonatal Ophthalmia', '1');
INSERT INTO r_eye_group VALUES ('8130000000005', '5', 'แผลในตาดำ', 'Cornia Ulcer', '1');
INSERT INTO r_eye_group VALUES ('8130000000006', '6', 'ริดสีดวงตา', 'Trachoma', '1');
INSERT INTO r_eye_group VALUES ('8130000000007', '7', 'ต้อเนื้อ', 'Pterygium', '1');
INSERT INTO r_eye_group VALUES ('8130000000008', '8', 'โรคตาจากการขาดสารอาหาร', 'Eye disease due to Malnutrition', '1');
INSERT INTO r_eye_group VALUES ('8130000000009', '9', 'บาดเจ็บที่ตา', 'Eye Injuries', '1');
INSERT INTO r_eye_group VALUES ('8130000000010', '10', 'สายตาผิดปกติ', 'Refractive error', '1');
INSERT INTO r_eye_group VALUES ('8130000000011', '11', 'อื่นๆ', 'Others', '1');
INSERT INTO r_eye_group VALUES ('8130000000003', '3', 'เยื่อตาอักเสบ', 'Conjunctivitis', '1');

INSERT INTO r_plan_group VALUES ('8030000000001', '1', 'ข้าราชการ / รัฐวิสาหกิจ', '1');
INSERT INTO r_plan_group VALUES ('8030000000002', '2', 'ประกันสังคม', '1');
INSERT INTO r_plan_group VALUES ('8030000000003', '3', 'UC บัตรทองไม่มี ท.', '1');
INSERT INTO r_plan_group VALUES ('8030000000004', '4', 'UC บัตรทองมี ท.', '1');
INSERT INTO r_plan_group VALUES ('8030000000005', '5', 'แรงงานต่างด้าวที่ขึ้นทะเบียน', '1');
INSERT INTO r_plan_group VALUES ('8030000000006', '6', 'อื่นๆ (ต่างด้าวไม่ขึ้นทะเบียน,สิทธิไม่ชัดเจน,ไม่ใช้สิทธิ)', '1');

INSERT INTO r_plan_group_map_pttype VALUES ('8040000000002', '8030000000002', 'A7');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000003', '8030000000003', 'UC');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000004', '8030000000004', 'AE');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000005', '8030000000004', 'AL');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000006', '8030000000004', 'AF');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000007', '8030000000004', 'AJ');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000008', '8030000000004', 'AK');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000009', '8030000000004', 'AB');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000010', '8030000000004', 'AG');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000011', '8030000000004', 'AA');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000012', '8030000000004', 'AD');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000013', '8030000000004', 'AC');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000015', '8030000000005', 'AR');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000016', '8030000000006', 'A1');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000017', '8030000000006', 'AM');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000018', '8030000000006', 'AN');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000019', '8030000000006', 'AP');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000020', '8030000000006', 'A9');
INSERT INTO r_plan_group_map_pttype VALUES ('8040000000001', '8030000000001', 'A2');

INSERT INTO r_rp504_disease_code VALUES ('8070000000001', '3060000000001', '1', 'A00', 'A99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000002', '3060000000001', '1', 'B00', 'B99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000003', '3060000000002', '2', 'C00', 'C97.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000004', '3060000000002', '2', 'D00', 'D48.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000005', '3060000000003', '3', 'D50', 'D89.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000006', '3060000000004', '4', 'E00', 'E99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000007', '3060000000005', '5', 'F00', 'F99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000008', '3060000000006', '6', 'G00', 'G99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000009', '3060000000007', '7', 'H00', 'H59.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000010', '3060000000008', '8', 'H60', 'H95.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000011', '3060000000009', '9', 'I00', 'I99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000012', '3060000000010', '10', 'J00', 'J99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000013', '3060000000011', '11', 'K00', 'K93.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000014', '3060000000012', '12', 'L00', 'L99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000015', '3060000000013', '13', 'M00', 'M99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000016', '3060000000014', '14', 'N00', 'N99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000017', '3060000000015', '15', 'O00', 'O79.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000018', '3060000000015', '15', 'O85', 'O99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000019', '3060000000016', '16', 'P00', 'P96.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000020', '3060000000017', '17', 'Q00', 'Q99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000021', '3060000000018', '18', 'R00', 'R99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000022', '3060000000019', '19', 'X40', 'X49.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000023', '3060000000019', '19', 'X60', 'X69.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000024', '3060000000019', '19', 'X85', 'X90.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000025', '3060000000019', '19', 'Y10', 'Y19.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000026', '3060000000020', '20', 'V01', 'V99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000027', '3060000000020', '20', 'Y85', 'Y85.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000028', '3060000000021', '21', 'W00', 'W99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000029', '3060000000021', '21', 'X00', 'X19.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000030', '3060000000021', '21', 'X20', 'X29.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000031', '3060000000021', '21', 'X30', 'X39.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000032', '3060000000021', '21', 'X50', 'X59.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000033', '3060000000021', '21', 'X70', 'X84.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000034', '3060000000021', '21', 'X91', 'X99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000035', '3060000000021', '21', 'Y00', 'Y09.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000036', '3060000000021', '21', 'Y20', 'Y36.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000037', '3060000000021', '21', 'Y40', 'Y84.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000038', '3060000000021', '21', 'Y86', 'Y89.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000039', '3060000000022', '99', 'D90', 'D99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000040', ' 3060000000022', '99', 'K94', 'K99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000041', '3060000000022', '99', 'O80', 'O84.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000042', '3060000000022', '99', 'P97', 'P99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000043', '3060000000022', '99', 'S00', 'S99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000044', '3060000000022', '99', 'T00', 'T99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000045', '3060000000022', '99', 'U00', 'U99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000046', '3060000000022', '99', 'V00', 'V09.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000050', '3060000000022', '99', 'Y90', 'Y99.99');
INSERT INTO r_rp504_disease_code VALUES ('8070000000051', '3060000000022', '99', 'Z00', 'Z99.99');

INSERT INTO r_rp505_disease_code VALUES ('8080000000001', '3070000000001', '1', 'A01', 'A02.98');
INSERT INTO r_rp505_disease_code VALUES ('8080000000002', '3070000000002', '2', 'A03', 'A09.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000003', '3070000000003', '3', 'A15', 'A19.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000004', '3070000000004', '4', 'A30', 'A30.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000005', '3070000000005', '5', 'A83', 'A86.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000006', '3070000000006', '6', 'A91', 'A92.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000007', '3070000000007', '7', 'B15', 'B19.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000008', '3070000000008', '8', 'B20', 'B24.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000009', '3070000000009', '9', 'B50', 'B54.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000010', '3070000000010', '10', 'A20', 'A28.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000011', '3070000000010', '10', 'A31', 'A32.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000012', '3070000000010', '10', 'A38', 'A44.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000013', '3070000000010', '10', 'A46', 'A49.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000014', '3070000000010', '10', 'A50', 'A60.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000015', '3070000000010', '10', 'A63', 'A71.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000016', '3070000000010', '10', 'A74', 'A75.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000017', '3070000000010', '10', 'A77', 'A82.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000018', '3070000000010', '10', 'A87', 'A90.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000019', '3070000000010', '10', 'A93', 'A96.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000020', '3070000000010', '10', 'B25', 'B27.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000021', '3070000000010', '10', 'B30', 'B30.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000022', '3070000000010', '10', 'B33', 'B49.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000023', '3070000000010', '10', 'B55', 'B60.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000024', '3070000000010', '10', 'B64', 'B83.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000025', '3070000000010', '10', 'B85', 'B89.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000026', '3070000000010', '10', 'B94', 'B97.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000027', '3070000000010', '10', 'B99', 'B99.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000028', '3070000000011', '11', 'C22', 'C22.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000029', '3070000000012', '12', 'C34', 'C34.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000030', '3070000000013', '13', 'C50', 'C50.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000031', '3070000000014', '14', 'C53', 'C53.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000032', '3070000000015', '15', 'D50', 'D53.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000033', '3070000000015', '15', 'D55', 'D55.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000034', '3070000000015', '15', 'D57', 'D77.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000035', '3070000000015', '15', 'D80', 'D84.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000036', '3070000000015', '15', 'D86', 'D86.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000037', '3070000000015', '15', 'D89', 'D89.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000038', '3070000000016', '16', 'D56', 'D56.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000039', '3070000000017', '17', 'E00', 'E07.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000040', '3070000000018', '18', 'E10', 'E14.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000041', '3070000000019', '19', 'E15', 'E16.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000042', '3070000000019', '19', 'E20', 'E32.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000043', '3070000000019', '19', 'E34', 'E35.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000044', '3070000000019', '19', 'E40', 'E46.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000045', '3070000000019', '19', 'E50', 'E56.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000046', '3070000000019', '19', 'E58', 'E61.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000047', '3070000000019', '19', 'E63', 'E68.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000048', '3070000000019', '19', 'E70', 'E80.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000049', '3070000000019', '19', 'E83', 'E90.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000050', '3070000000020', '20', 'F04', 'F07.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000051', '3070000000020', '20', 'F09', 'F09.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000052', '3070000000021', '21', 'F10', 'F19.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000053', '3070000000022', '22', 'F20', 'F25.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000054', '3070000000022', '22', 'F28', 'F34.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000055', '3070000000023', '23', 'F38', 'F39.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000056', '3070000000024', '24', 'F40', 'F45.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000057', '3070000000024', '24', 'F48', 'F48.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000058', '3070000000025', '25', 'F70', 'F73.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000059', '3070000000025', '25', 'F78', 'F79.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000060', '3070000000026', '26', 'G40', 'G41.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000061', '3070000000027', '27', 'G00', 'G13.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000062', '3070000000027', '27', 'G20', 'G26.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000063', '3070000000027', '27', 'G30', 'G32.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000064', '3070000000027', '27', 'G35', 'G37.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000065', '3070000000027', '27', 'G43', 'G47.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000066', '3070000000027', '27', 'G50', 'G64.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000067', '3070000000027', '27', 'G70', 'G73.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000068', '3070000000027', '27', 'G80', 'G83.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000069', '3070000000027', '27', 'G90', 'G99.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000070', '3070000000028', '28', 'H00', 'H06.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000071', '3070000000028', '28', 'H10', 'H11.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000072', '3070000000028', '28', 'H13', 'H13.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000073', '3070000000028', '28', 'H15', 'H22.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000074', '3070000000028', '28', 'H25', 'H28.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000075', '3070000000028', '28', 'H30', 'H36.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000076', '3070000000028', '28', 'H40', 'H40.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000077', '3070000000028', '28', 'H42', 'H55.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000078', '3070000000028', '28', 'H57', 'H62.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000079', '3070000000029', '29', 'H65', 'H75.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000080', '3070000000029', '29', 'H80', 'H83.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000081', '3070000000029', '29', 'H90', 'H95.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000082', '3070000000030', '30', 'I00', 'I02.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000083', '3070000000031', '31', 'I05', 'I13.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000084', '3070000000032', '32', 'I15', 'I15.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000085', '3070000000033', '33', 'I20', 'I52.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000086', '3070000000035', '35', 'I60', 'I74.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000087', '3070000000036', '36', 'I77', 'I89.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000088', '3070000000036', '36', 'I95', 'I95.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000089', '3070000000036', '36', 'I97', 'I99.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000090', '3070000000037', '37', 'J00', 'J06.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000091', '3070000000037', '37', 'J30', 'J37.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000092', '3070000000040', '40', 'J40', 'J44.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000093', '3070000000040', '40', 'J47', 'J47.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000094', '3070000000041', '41', 'J45', 'J46.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000095', '3070000000042', '42', 'J20', 'J22.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000096', '3070000000042', '42', 'J60', 'J70.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000097', '3070000000042', '42', 'J80', 'J82.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000098', '3070000000042', '42', 'J84', 'J86.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000099', '3070000000042', '42', 'J90', 'J96.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000100', '3070000000042', '42', 'J98', 'J99.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000101', '3070000000043', '43', 'K25', 'K27.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000102', '3070000000044', '44', 'K25', 'K38.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000103', '3070000000045', '45', 'K40', 'K46.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000104', '3070000000046', '46', 'K50', 'K52.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000105', '3070000000046', '46', 'K55', 'K55.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000106', '3070000000046', '46', 'K65', 'K67.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000107', '3070000000047', '47', 'K56', 'K56.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000108', '3070000000048', '48', 'K70', 'K70.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000109', '3070000000049', '49', 'K80', 'K81.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000110', '3070000000050', '50', 'K00', 'K14.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000111', '3070000000050', '50', 'K20', 'K23.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000112', '3070000000050', '50', 'K28', 'K31.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000113', '3070000000050', '50', 'K57', 'K63.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000114', '3070000000050', '50', 'K71', 'K77.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000115', '3070000000050', '50', 'K82', 'K83.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000116', '3070000000050', '50', 'K85', 'K87.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000117', '3070000000050', '50', 'K90', 'K93.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000118', '3070000000051', '51', 'L00', 'L05.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000119', '3070000000051', '51', 'L08', 'L08.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000120', '3070000000051', '51', 'L10', 'L14.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000121', '3070000000051', '51', 'L20', 'L30.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000122', '3070000000051', '51', 'L40', 'L45.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000123', '3070000000051', '51', 'L50', 'L60.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000124', '3070000000051', '51', 'L62', 'L68.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000125', '3070000000051', '51', 'L70', 'L75.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000126', '3070000000051', '51', 'L80', 'L99.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000127', '3070000000052', '52', 'M00', 'M03.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000128', '3070000000052', '52', 'M05', 'M25.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000129', '3070000000052', '52', 'M40', 'M43.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000130', '3070000000052', '52', 'M45', 'M51.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000131', '3070000000052', '52', 'M53', 'M54.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000132', '3070000000052', '52', 'M60', 'M63.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000133', '3070000000052', '52', 'M65', 'M68.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000134', '3070000000052', '52', 'M70', 'M73.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000135', '3070000000052', '52', 'M75', 'M77.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000136', '3070000000052', '52', 'M79', 'M96.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000137', '3070000000052', '52', 'M99', 'M99.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000138', '3070000000053', '53', 'M30', 'M36.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000139', '3070000000054', '54', 'N17', 'N23.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000140', '3070000000057', '57', 'N40', 'N51.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000141', '3070000000058', '58', 'N60', 'N64.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000142', '3070000000059', '59', 'N70', 'N77.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000143', '3070000000059', '59', 'N80', 'N97.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000144', '3070000000059', '59', 'N98', 'N98.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000145', '3070000000060', '60', 'N00', 'N08.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000146', '3070000000060', '60', 'N10', 'N16.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000147', '3070000000060', '60', 'N25', 'N37.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000148', '3070000000060', '60', 'N39', 'N39.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000149', '3070000000060', '60', 'N99', 'N99.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000150', '3070000000061', '61', 'O00', 'O08.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000151', '3070000000063', '63', 'O10', 'O16.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000152', '3070000000063', '63', 'O20', 'O26.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000153', '3070000000063', '63', 'O28', 'O36.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000154', '3070000000063', '63', 'O40', 'O48.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000155', '3070000000063', '63', 'O60', 'O75.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000156', '3070000000063', '63', 'O81', 'O92.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000157', '3070000000063', '63', 'O95', 'O99.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000158', '3070000000064', '64', 'P10', 'P15.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000159', '3070000000065', '65', 'P00', 'P05.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000160', '3070000000065', '65', 'P07', 'P08.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000161', '3070000000065', '65', 'P20', 'P29.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000162', '3070000000065', '65', 'P35', 'P39.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000163', '3070000000065', '65', 'P50', 'P61.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000164', '3070000000065', '65', 'P70', 'P72.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000165', '3070000000065', '65', 'P74', 'P78.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000166', '3070000000065', '65', 'P80', 'P81.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000167', '3070000000065', '65', 'P83', 'P83.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000168', '3070000000065', '65', 'P90', 'P96.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000169', '3070000000066', '66', 'Q00', 'Q07.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000170', '3070000000066', '66', 'Q10', 'Q18.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000171', '3070000000066', '66', 'Q20', 'Q28.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000172', '3070000000066', '66', 'Q30', 'Q45.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000173', '3070000000066', '66', 'Q50', 'Q56.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000174', '3070000000066', '66', 'Q60', 'Q93.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000175', '3070000000066', '66', 'Q95', 'Q99.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000176', '3070000000067', '67', 'R00', 'R07.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000177', '3070000000067', '67', 'R09', 'R23.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000178', '3070000000067', '67', 'R25', 'R27.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000179', '3070000000067', '67', 'R29', 'R36.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000180', '3070000000067', '67', 'R39', 'R64.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000181', '3070000000067', '67', 'R68', 'R87.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000182', '3070000000067', '67', 'R89', 'R96.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000183', '3070000000067', '67', 'R98', 'R99.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000184', '3070000000068', '68', 'V01', 'V06.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000185', '3070000000068', '68', 'V09', 'V99.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000186', '3070000000070', '70', 'Y85', 'Y85.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000187', '3070000000071', '71', 'X40', 'X49.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000188', '3070000000071', '71', 'X60', 'X69.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000189', '3070000000071', '71', 'X85', 'X99.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000190', '3070000000071', '71', 'Y10', 'Y19.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000191', '3070000000072', '72', 'W00', 'W45.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000192', '3070000000072', '72', 'W49', 'W60.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000193', '3070000000072', '72', 'W64', 'W70.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000194', '3070000000072', '72', 'W73', 'W81.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000195', '3070000000072', '72', 'W83', 'W94.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000196', '3070000000072', '72', 'W99', 'X06.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000197', '3070000000072', '72', 'X08', 'X39.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000198', '3070000000072', '72', 'X50', 'X54.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000199', '3070000000072', '72', 'X57', 'X59.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000200', '3070000000072', '72', 'Y86', 'Y86.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000201', '3070000000073', '73', 'X70', 'X84.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000202', '3070000000074', '74', 'X91', 'Y36.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000203', '3070000000075', '75', 'Y40', 'Y66.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000204', '3070000000075', '75', 'Y69', 'Y84.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000205', '3070000000075', '75', 'Y87', 'Y89.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000206', '3070000000076', '99', 'A33', 'A37.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000207', '3070000000076', '99', 'C00', 'C33.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000208', '3070000000076', '99', 'C37', 'C52.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000209', '3070000000076', '99', 'C54', 'C58.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000210', '3070000000076', '99', 'C60', 'C85.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000211', '3070000000076', '99', 'C88', 'C88.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000212', '3070000000076', '99', 'C90', 'C97.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000213', '3070000000076', '99', 'D00', 'D07.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000214', '3070000000076', '99', 'D09', 'D48.9');
INSERT INTO r_rp505_disease_code VALUES ('8080000000215', '3070000000076', '99', 'F00', 'F03.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000216', '3070000000076', '99', 'F10', 'F19.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000217', '3070000000076', '99', 'F50', 'F55.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000218', '3070000000076', '99', 'F59', 'F66.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000219', '3070000000076', '99', 'F68', 'F69.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000230', '3070000000076', '99', 'F80', 'F84.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000231', '3070000000076', '99', 'F88', 'F95.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000232', '3070000000076', '99', 'F98', 'F99.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000233', '3070000000076', '99', 'S00', 'S99.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000234', '3070000000076', '99', 'T00', 'T75.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000235', '3070000000076', '99', 'T78', 'T88.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000236', '3070000000076', '99', 'T90', 'T98.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000237', '3070000000076', '99', 'Y90', 'Y91.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000238', '3070000000076', '99', 'Y95', 'Y98.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000239', '3070000000076', '99', 'Z00', 'Z04.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000240', '3070000000076', '99', 'Z08', 'Z13.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000241', '3070000000076', '99', 'Z20', 'Z65.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000242', '3070000000076', '99', 'Z70', 'Z76.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000243', '3070000000076', '99', 'Z80', 'Z99.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000244', '3070000000010', '10', 'A98', 'B09.99');
INSERT INTO r_rp505_disease_code VALUES ('8080000000245', '3070000000038', '38', 'J10', 'J18.99');

INSERT INTO r_uc_plan_group VALUES ('8050000000001', '1', 'UC ในเครือข่าย', '1');
INSERT INTO r_uc_plan_group VALUES ('8050000000002', '2', 'UC นอกเครือข่าย', '1');
INSERT INTO r_uc_plan_group VALUES ('8050000000003', '3', 'สิทธิอื่นๆ', '1  ');

INSERT INTO r_uc_plan_group_pttype VALUES ('8060000000001', '1', 'UC');
INSERT INTO r_uc_plan_group_pttype VALUES ('8060000000002', '2', 'AE');
INSERT INTO r_uc_plan_group_pttype VALUES ('8060000000003', '3', 'AL');
INSERT INTO r_uc_plan_group_pttype VALUES ('8060000000004', '4', 'AF');
INSERT INTO r_uc_plan_group_pttype VALUES ('8060000000005', '5', 'AJ');
INSERT INTO r_uc_plan_group_pttype VALUES ('8060000000006', '6', 'AK');
INSERT INTO r_uc_plan_group_pttype VALUES ('8060000000007', '7', 'AB');
INSERT INTO r_uc_plan_group_pttype VALUES ('8060000000008', '8', 'AG');
INSERT INTO r_uc_plan_group_pttype VALUES ('8060000000009', '9', 'AA');
INSERT INTO r_uc_plan_group_pttype VALUES ('8060000000010', '10', 'AD');
INSERT INTO r_uc_plan_group_pttype VALUES ('8060000000011', '11', 'AC');

CREATE TABLE s_report_version (
    report_version_number character varying(255),
    s_report_version_id character varying(255) NOT NULL,
    report_version_description character varying(255),
    report_version_application_number character varying(255),
    report_version_notice character varying(255),
    report_version_update_date_time character varying(255)
);

ALTER TABLE ONLY s_report_version
    ADD CONSTRAINT s_report_version_id PRIMARY KEY (s_report_version_id);

INSERT INTO s_report_version VALUES ('8170000000001', '01', 'Report Center', '1.00.1048', 'for hospitalOS v3 db : 3.13.1048 and pcu db : 0.03.1148', '2548-10-14 14:45:00');
insert into s_report_version values ('01','8170000000001','Report Center','1.00.1048','for hospitalOS v3 db : 3.13.1048 and pcu db : 0.02.1048','2548-10-14 14:45:00');
