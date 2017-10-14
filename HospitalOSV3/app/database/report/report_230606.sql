--
-- PostgreSQL database dump
--
--
-- Name: r_accident_group; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_accident_group (
    r_accident_group_id character varying(255) NOT NULL,
    accident_group_number character varying(255),
    accident_group_description character varying(255),
    accident_group_active character varying(255)
);


ALTER TABLE public.r_accident_group OWNER TO postgres;

--
-- Name: r_accident_group_code; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_accident_group_code (
    r_accident_group_code_id character varying(255) NOT NULL,
    r_accident_group_id character varying(255),
    accident_group_code_begin character varying(255),
    accident_group_code_end character varying(255)
);


ALTER TABLE public.r_accident_group_code OWNER TO postgres;

--
-- Name: r_aric_antibiotic; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_aric_antibiotic (
    r_aric_antibiotic_id character varying(255) NOT NULL,
    b_item_id character varying(255),
    aric_antibiotic_code character varying(255),
    aric_antibiotic_common_name character varying(255)
);


ALTER TABLE public.r_aric_antibiotic OWNER TO postgres;

--
-- Name: r_aric_disease_code; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_aric_disease_code (
    r_aric_disease_code_id character varying(255) NOT NULL,
    r_aric_group_id character varying(255),
    aric_disease_code_begin character varying(255),
    aric_disease_code_end character varying(255)
);


ALTER TABLE public.r_aric_disease_code OWNER TO postgres;

--
-- Name: r_aric_group; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_aric_group (
    r_aric_group_id character varying(255) NOT NULL,
    aric_group_number character varying(255),
    aric_group_description character varying(255)
);


ALTER TABLE public.r_aric_group OWNER TO postgres;

--
-- Name: TABLE r_aric_group; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_aric_group IS 'กลุ่มของ ARIC';


--
-- Name: r_aric_subgroup; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_aric_subgroup (
    r_aric_subgroup_id character varying(255) NOT NULL,
    aric_subgroup_number character varying(255),
    aric_subgroup_description character varying(255),
    r_aric_group_id character varying(255)
);


ALTER TABLE public.r_aric_subgroup OWNER TO postgres;

--
-- Name: r_dental_protect_item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_dental_protect_item (
    r_dental_protect_item_id character varying(255) NOT NULL,
    b_item_id character varying(255) NOT NULL,
    dental_protect_item_number character varying(255) NOT NULL,
    dental_protect_item_common_name character varying(255) NOT NULL
);


ALTER TABLE public.r_dental_protect_item OWNER TO postgres;

--
-- Name: TABLE r_dental_protect_item; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_dental_protect_item IS 'รายการ item ที่เป็นรายการสำหรับป้องกันทางทันตกรรม';


--
-- Name: r_epi_activity_age_group; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_epi_activity_age_group (
    r_epi_activity_age_group_id character varying(255) NOT NULL,
    epi_activity_age_group_number character varying(255),
    epi_activity_age_group_description character varying(255),
    epi_activity_age_group_begin character varying(255),
    epi_activity_age_group_end character varying(255)
);


ALTER TABLE public.r_epi_activity_age_group OWNER TO postgres;

--
-- Name: r_epi_activity_group; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_epi_activity_group (
    r_epi_activity_group_id character varying(255) NOT NULL,
    epi_activity_group_number character varying(255),
    epi_activity_group_description character varying(255)
);


ALTER TABLE public.r_epi_activity_group OWNER TO postgres;

--
-- Name: r_epi_activity_subgroup; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_epi_activity_subgroup (
    r_epi_activity_subgroup_id character varying(255) NOT NULL,
    r_epi_activity_group_id character varying(255),
    r_epi_activity_age_group character varying(255),
    b_health_epi_group_id character varying(255)
);


ALTER TABLE public.r_epi_activity_subgroup OWNER TO postgres;

--
-- Name: r_epi_age_group; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_epi_age_group (
    r_epi_age_group_id character varying(255) NOT NULL,
    epi_age_group_number character varying(255) NOT NULL,
    epi_age_group_description character varying(255),
    epi_age_group_start character varying(255),
    epi_age_group_end character varying(255),
    r_epi_age_group_type_id character varying(255) NOT NULL,
    r_epi_group_clinic_id character varying(255) NOT NULL
);


ALTER TABLE public.r_epi_age_group OWNER TO postgres;

--
-- Name: TABLE r_epi_age_group; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_epi_age_group IS 'ตารางเก็บกลุ่มอายุของการให้วัคซีน';


--
-- Name: r_epi_age_group_item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_epi_age_group_item (
    r_epi_age_group_item_id character varying(255) NOT NULL,
    r_epi_age_group_id character varying(255) NOT NULL,
    r_epi_group_clinic_id character varying(255) NOT NULL,
    b_health_epi_group_id character varying(255) NOT NULL,
    epi_age_group_item_description character varying(255) NOT NULL
);


ALTER TABLE public.r_epi_age_group_item OWNER TO postgres;

--
-- Name: TABLE r_epi_age_group_item; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_epi_age_group_item IS 'ตารางเก็บข้อมูลรายการวัคซีนของกลุ่มอายุ';


--
-- Name: r_epi_age_group_type; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_epi_age_group_type (
    r_epi_age_group_type_id character varying(255) NOT NULL,
    epi_age_group_type_number character varying(255) NOT NULL,
    epi_age_group_type_description character varying(255) NOT NULL
);


ALTER TABLE public.r_epi_age_group_type OWNER TO postgres;

--
-- Name: TABLE r_epi_age_group_type; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_epi_age_group_type IS 'ตารางเก็บข้อมูลชนิดของกลุ่มอายุวัคซีน';


--
-- Name: r_epi_group; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_epi_group (
    r_epi_group_id character varying(255) NOT NULL,
    epi_group_number character varying(255) NOT NULL,
    r_epi_group_description character varying(255) NOT NULL
);


ALTER TABLE public.r_epi_group OWNER TO postgres;

--
-- Name: TABLE r_epi_group; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_epi_group IS 'กลุ่มวัคซีนที่ใช้สำหรับออกรายงาน 11รง5 ส่วนที่ 3 ปีงบประมาณ 2549';


--
-- Name: r_epi_group_clinic; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_epi_group_clinic (
    r_epi_group_clinic_id character varying(255) NOT NULL,
    epi_group_clinic_number character varying(255) NOT NULL,
    epi_group_clinic_description character varying(255) NOT NULL
);


ALTER TABLE public.r_epi_group_clinic OWNER TO postgres;

--
-- Name: TABLE r_epi_group_clinic; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_epi_group_clinic IS 'ตารางเก็บคลินิกของการออกรายงานวัคซีน';


--
-- Name: r_epi_group_item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_epi_group_item (
    r_epi_group_item_id character varying(255) NOT NULL,
    r_epi_group_id character varying(255) NOT NULL,
    b_health_epi_group_id character varying(255) NOT NULL,
    epi_group_item_description character varying(255) NOT NULL
);


ALTER TABLE public.r_epi_group_item OWNER TO postgres;

--
-- Name: TABLE r_epi_group_item; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_epi_group_item IS 'รายการวัคซีนที่อยู่ในกลุ่มวัคซีนแต่ละกลุ่ม สำหรับออกรายงาน 11รง5 ส่วนที่ 3 ปีงบประมาณ 2549';


--
-- Name: r_epi_measles_item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_epi_measles_item (
    r_epi_measles_item_id character varying(255) NOT NULL,
    b_health_epi_group_id character varying(255) NOT NULL,
    epi_measles_item_description character varying(255) NOT NULL
);


ALTER TABLE public.r_epi_measles_item OWNER TO postgres;

--
-- Name: TABLE r_epi_measles_item; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_epi_measles_item IS 'รายการวัคซีนที่อยู่ในกลุ่ม วัคซีนหัดเยอรมัน (MMR)';


--
-- Name: r_epi_tt_item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_epi_tt_item (
    r_epi_tt_item_id character varying(255) NOT NULL,
    b_health_epi_group_id character varying(255) NOT NULL,
    epi_tt_item_description character varying(255) NOT NULL
);


ALTER TABLE public.r_epi_tt_item OWNER TO postgres;

--
-- Name: TABLE r_epi_tt_item; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_epi_tt_item IS 'ตารางเก็บรายการวัคซีนของกลุ่ม TT';


--
-- Name: r_eye_disease_code; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_eye_disease_code (
    r_eye_disease_code_id character varying(255) NOT NULL,
    r_eye_group_id character varying(255),
    eye_disease_code_begin character varying(255),
    eye_disease_code_end character varying(255)
);


ALTER TABLE public.r_eye_disease_code OWNER TO postgres;

--
-- Name: r_eye_group; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_eye_group (
    r_eye_group_id character varying(255) NOT NULL,
    eye_group_number character varying(255),
    eye_group_description_th character varying(255),
    eye_group_description_en character varying(255),
    eye_group_active character varying(255)
);


ALTER TABLE public.r_eye_group OWNER TO postgres;

--
-- Name: r_healthy_group; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_healthy_group (
    r_healthy_group_id character varying(255) NOT NULL,
    healthy_group_number character varying(255) NOT NULL,
    healthy_group_description character varying(255) NOT NULL
);


ALTER TABLE public.r_healthy_group OWNER TO postgres;

--
-- Name: TABLE r_healthy_group; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_healthy_group IS 'กลุ่มการตรวจสุขภาพตาม 11รง5 ส่วนที่ 3';


--
-- Name: r_healthy_group_survey; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_healthy_group_survey (
    r_healthy_group_survey_id character varying(255) NOT NULL,
    r_healthy_subgroup_id character varying(255) NOT NULL,
    b_health_survey_id character varying(255) NOT NULL,
    healthy_group_survey_description character varying(255) DEFAULT ''::character varying
);


ALTER TABLE public.r_healthy_group_survey OWNER TO postgres;

--
-- Name: TABLE r_healthy_group_survey; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_healthy_group_survey IS 'รายการสำรวจที่อยู่ในกลุ่มการตรวจสุขภาพตาม 11รง5 ส่วนที่ 3';


--
-- Name: r_healthy_subgroup; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_healthy_subgroup (
    r_healthy_subgroup_id character varying(255) NOT NULL,
    r_healthy_group_id character varying(255) NOT NULL,
    healthy_subgroup_number character varying(255) NOT NULL,
    healthy_subgroup_description character varying(255)
);


ALTER TABLE public.r_healthy_subgroup OWNER TO postgres;

--
-- Name: TABLE r_healthy_subgroup; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_healthy_subgroup IS 'ตารางเก็บข้อมูลหัวข้อของ กิจกรรมการประเมินสุขภาพ';


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
-- Name: r_nutrition_map; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_nutrition_map (
    r_nutrition_map_id character varying(255) NOT NULL,
    f_visit_nutrition_level_id character varying(255) NOT NULL,
    nutrition_map_description character varying(255) NOT NULL,
    r_nutrition_type_id character varying(255) NOT NULL
);


ALTER TABLE public.r_nutrition_map OWNER TO postgres;

--
-- Name: TABLE r_nutrition_map; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_nutrition_map IS 'ตาราง Map ข้อมูล ภาวะโภชนาการ ว่าขาด เกิน หรือ ปกติ';


--
-- Name: r_nutrition_type; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_nutrition_type (
    r_nutrition_type_id character varying(255) NOT NULL,
    nutrition_type_number character varying(255) NOT NULL,
    nutrition_type_description character varying(255) NOT NULL
);


ALTER TABLE public.r_nutrition_type OWNER TO postgres;

--
-- Name: TABLE r_nutrition_type; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE r_nutrition_type IS 'ตารางเก็บปรพเภทกลุ่มของ ภาวะโภชนาการ';


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
-- Name: r_plan_group; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_plan_group (
    r_plan_group_id character varying(255) NOT NULL,
    plan_group_number character varying(255),
    plan_group_description character varying(255),
    plan_group_active character varying(255)
);


ALTER TABLE public.r_plan_group OWNER TO postgres;

--
-- Name: r_plan_group_map_pttype; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_plan_group_map_pttype (
    r_plan_group_map_pttype_id character varying(255) NOT NULL,
    r_plan_group_id character varying(255),
    plan_group_map_pttype_pttype character varying(255)
);


ALTER TABLE public.r_plan_group_map_pttype OWNER TO postgres;

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
-- Name: r_resident_age_group; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_resident_age_group (
    r_resident_age_group_id character varying(255) NOT NULL,
    resident_age_group_number character varying(255),
    resident_age_group_begin character varying(255),
    resident_age_group_end character varying(255)
);


ALTER TABLE public.r_resident_age_group OWNER TO postgres;

--
-- Name: r_rp11_disease; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_rp11_disease (
    r_rp11_disease_id character varying(255) NOT NULL,
    rp11_disease_number character varying(255),
    rp11_disease_description character varying(255)
);


ALTER TABLE public.r_rp11_disease OWNER TO postgres;

--
-- Name: r_rp11_item; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_rp11_item (
    r_rp11_item_id character varying(255) NOT NULL,
    r_rp11_disease_id character varying(255),
    b_item_id character varying(255),
    rp11_item_number character varying(255),
    rp11_item_description character varying(255)
);


ALTER TABLE public.r_rp11_item OWNER TO postgres;

--
-- Name: r_rp504_disease_code; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_rp504_disease_code (
    r_rp504_disease_code_id character varying(255) NOT NULL,
    f_group_rp504_id character varying(255),
    rp504_disease_code_number character varying(255),
    rp504_disease_code_begin character varying(255),
    rp504_disease_code_end character varying(255)
);


ALTER TABLE public.r_rp504_disease_code OWNER TO postgres;

--
-- Name: r_rp505_disease_code; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_rp505_disease_code (
    r_rp505_disease_code_id character varying(255) NOT NULL,
    f_group_rp505_id character varying(255),
    rp505_disease_code_number character varying(255),
    rp505_disease_code_begin character varying(255),
    rp505_disease_code_end character varying(255)
);


ALTER TABLE public.r_rp505_disease_code OWNER TO postgres;

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
-- Name: r_sql_template; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_sql_template (
    r_sql_template_id character varying(255) NOT NULL,
    sql_template_number character varying(255),
    sql_template_description character varying(255),
    sql_template_sql character varying(4000),
    sql_template_is_query_by_date character varying(255),
    sql_template_active character varying(255)
);


ALTER TABLE public.r_sql_template OWNER TO postgres;

--
-- Name: r_uc_plan_group; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_uc_plan_group (
    r_uc_plan_group_id character varying(255) NOT NULL,
    uc_plan_group_number character varying(255),
    uc_plan_group_description character varying(255),
    uc_plan_group_active character varying(255)
);


ALTER TABLE public.r_uc_plan_group OWNER TO postgres;

--
-- Name: r_uc_plan_group_pttype; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE r_uc_plan_group_pttype (
    r_uc_plan_group_pttype_id character varying(255) NOT NULL,
    uc_plan_group_pttype_number character varying(255),
    uc_plan_group_pttype_pttype character varying(255)
);


ALTER TABLE public.r_uc_plan_group_pttype OWNER TO postgres;

--
-- Name: s_report_version; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE s_report_version (
    s_report_version_id character varying(255) NOT NULL,
    report_version_number character varying(255),
    report_version_description character varying(255),
    report_version_application_number character varying(255),
    report_version_notice character varying(255),
    report_version_update_date_time character varying(255)
);


ALTER TABLE public.s_report_version OWNER TO postgres;

--
-- Data for Name: r_accident_group; Type: TABLE DATA; Schema: public; Owner: postgres
--


--COPY r_accident_group (r_accident_group_id, accident_group_number, accident_group_description, accident_group_active) FROM stdin;');
insert into r_accident_group values ('8150000000001','1','อุบัติเหตุจากการขนส่ง','1');
insert into r_accident_group values ('8150000000002','2','อุบัติเหตุพลัดตกหรือหกล้ม','1');
insert into r_accident_group values ('8150000000003','3','อุบัติเหตุสัมผัสกับแรงเชิงกลของวัตถุสิ่งของ','1');
insert into r_accident_group values ('8150000000004','4','สัมผัสกับแรงเชิงกลของสัตว์/คน','1');
insert into r_accident_group values ('8150000000005','5','อุบัติเหตุจากการตกน้ำ จมน้ำ','1');
insert into r_accident_group values ('8150000000006','6','อุบัติเหตุที่คุกคามการหายใจ','1');
insert into r_accident_group values ('8150000000007','7','อุบัติเหตุสัมผัสกระแสไฟฟ้า รังสีและอุณหภูมิ','1');
insert into r_accident_group values ('8150000000008','8','อุบัติเหตุสัมผัสควันไฟ และเปลวไฟ','1');
insert into r_accident_group values ('8150000000009','9','อุบัติเหตุสัมผัสความร้อน ของร้อน','1');
insert into r_accident_group values ('8150000000010','10','อุบัติเหตุสัมผัสพิษจากสัตว์หรือพืช','1');
insert into r_accident_group values ('8150000000011','11','อุบัติเหตุสัมผัสพลังงานจากธรรมชาติ','1');
insert into r_accident_group values ('8150000000012','12','อุบัติเหตุสัมผัสพิษและสารอื่นๆ','1');
insert into r_accident_group values ('8150000000013','13','อุบัติเหตุการออกแรงเกิน','1');
insert into r_accident_group values ('8150000000014','14','อุบัติเหตุสัมผัสกับสิ่งไม่ทราบแน่ชัด','1');
insert into r_accident_group values ('8150000000015','15','ทำร้ายตัวเองด้วยวิธีต่างๆ','1');
insert into r_accident_group values ('8150000000016','16','ถูกทำร้ายด้วยวิธีต่างๆ','1');
insert into r_accident_group values ('8150000000017','17','บาดเจ็บโดยไม่ทราบเจตนา','1');
insert into r_accident_group values ('8150000000018','18','ดำเนินการทางกฏหมายหรือสงคราม','1');
insert into r_accident_group values ('8150000000019','19','ไม่ทราบทั้งสาเหตุและเจตนา','1');
insert into r_accident_group values ('8150000000020','1.1','จักรยานยนต์','1');
insert into r_accident_group values ('8150000000021','1.2','รถยนต์อื่น ๆ','1');
insert into r_accident_group values ('8150000000022','1.3','อื่น ๆ เช่น รถจักรยาน/คนเดิน/รถไถ ฯลฯ','1');



--
-- Data for Name: r_accident_group_code; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_accident_group_code (r_accident_group_code_id, r_accident_group_id, accident_group_code_begin, accident_group_code_end) FROM stdin;');
insert into r_accident_group_code values ('8160000000001','8150000000001','V01','V99.99');
insert into r_accident_group_code values ('8160000000002','8150000000002','W00','W19.99');
insert into r_accident_group_code values ('8160000000003','8150000000003','W20','W49.99');
insert into r_accident_group_code values ('8160000000004','8150000000004','W50','W64.99');
insert into r_accident_group_code values ('8160000000005','8150000000005','W65','W74.99');
insert into r_accident_group_code values ('8160000000006','8150000000006','W75','W84.99');
insert into r_accident_group_code values ('8160000000007','8150000000007','W85','W99.99');
insert into r_accident_group_code values ('8160000000008','8150000000008','X00','X09.99');
insert into r_accident_group_code values ('8160000000009','8150000000009','X10','X19.99');
insert into r_accident_group_code values ('8160000000010','8150000000010','X20','X29.99');
insert into r_accident_group_code values ('8160000000011','8150000000011','X30','X39.99');
insert into r_accident_group_code values ('8160000000012','8150000000012','X40','X49.99');
insert into r_accident_group_code values ('8160000000013','8150000000013','X50','X57.99');
insert into r_accident_group_code values ('8160000000014','8150000000014','X58','X59.99');
insert into r_accident_group_code values ('8160000000015','8150000000015','X60','X84.99');
insert into r_accident_group_code values ('8160000000016','8150000000016','X85','X99.99');
insert into r_accident_group_code values ('8160000000017','8150000000016','Y00','Y09.99');
insert into r_accident_group_code values ('8160000000018','8150000000017','Y10','Y33.99');
insert into r_accident_group_code values ('8160000000019','8150000000018','Y35','Y36.99');
insert into r_accident_group_code values ('8160000000020','8150000000019','Y34','Y34.99');
insert into r_accident_group_code values ('8160000000021','8150000000020','V20','V29.99');
insert into r_accident_group_code values ('8160000000022','8150000000021','V40','V49.99');
insert into r_accident_group_code values ('8160000000023','8150000000022','V10','V19.99');
insert into r_accident_group_code values ('8160000000024','8150000000022','V30','V39.99');
insert into r_accident_group_code values ('8160000000025','8150000000022','V50','V99.99');
insert into r_accident_group_code values ('8160000000026','8150000000022','V01','V09.99');




--
-- Data for Name: r_aric_disease_code; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_aric_disease_code (r_aric_disease_code_id, r_aric_group_id, aric_disease_code_begin, aric_disease_code_end) FROM stdin;');
insert into r_aric_disease_code values ('8120000000001','8090000000001','J00','J00');
insert into r_aric_disease_code values ('8120000000002','8090000000001','J06','J06.99');
insert into r_aric_disease_code values ('8120000000003','8090000000001','J02','J02.99');
insert into r_aric_disease_code values ('8120000000004','8090000000001','J03','J03.99');
insert into r_aric_disease_code values ('8120000000005','8090000000001','J20','J20.99');
insert into r_aric_disease_code values ('8120000000006','8090000000002','J12','J12.99');
insert into r_aric_disease_code values ('8120000000007','8090000000002','J18','J18.99');



--
-- Data for Name: r_aric_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_aric_group (r_aric_group_id, aric_group_number, aric_group_description) FROM stdin;');
insert into r_aric_group values ('8090000000001','1','URI');
insert into r_aric_group values ('8090000000002','2','ปอดบวม');



--
-- Data for Name: r_aric_subgroup; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_aric_subgroup (r_aric_subgroup_id, aric_subgroup_number, aric_subgroup_description, r_aric_group_id) FROM stdin;');
insert into r_aric_subgroup values ('8100000000002','ARIC02','URI ไม่รับยา Antibiotic','8090000000001');
insert into r_aric_subgroup values ('8100000000004','ARIC04','ปอดบวม ไม่รับยา Antibiotic','8090000000002');
insert into r_aric_subgroup values ('8100000000003','ARIC03','ปอดบวม รับยา Antibiotic','8090000000002');
insert into r_aric_subgroup values ('8100000000001','ARIC01','URI รับยา Antibiotic','8090000000001');




--
-- Data for Name: r_epi_activity_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_epi_activity_group (r_epi_activity_group_id, epi_activity_group_number, epi_activity_group_description) FROM stdin;');
insert into r_epi_activity_group values ('8220000000001','1','คลีนิคเด็กดี');
insert into r_epi_activity_group values ('8220000000002','2','ป้องกันโรคบาดทะยัก(TT)');



--
-- Data for Name: r_epi_age_group_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_epi_age_group_type (r_epi_age_group_type_id, epi_age_group_type_number, epi_age_group_type_description) FROM stdin;');
insert into r_epi_age_group_type values ('8350000000001','1','กำหนดช่วงอายุ');
insert into r_epi_age_group_type values ('8350000000002','2','ไม่กำหนดช่วงอายุ');
insert into r_epi_age_group_type values ('8350000000003','3','กำหนดอายุเริ่มต้น');
insert into r_epi_age_group_type values ('8350000000004','4','กำหนดอายุสิ้นสุด');



--
-- Data for Name: r_epi_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_epi_group (r_epi_group_id, epi_group_number, r_epi_group_description) FROM stdin;');
insert into r_epi_group values ('8210000000001','DPT','คอตีบ ไอกรน บาดทะยัก');
insert into r_epi_group values ('8210000000002','BCG','BCG ของผู้รับบริการ รวมทั้ง BCG ทารกแรกเกิด');
insert into r_epi_group values ('8210000000003','MMR','MMR ตามแผนงานการให้ภูมิคุ้มกันโรคของประเทศ');
insert into r_epi_group values ('8210000000004','Hep.B','Hepatitis B ตามแผนงานการให้ภูมิคุ้มกันโรคของประเทศ');



--
-- Data for Name: r_epi_group_clinic; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_epi_group_clinic (r_epi_group_clinic_id, epi_group_clinic_number, epi_group_clinic_description) FROM stdin;');
insert into r_epi_group_clinic values ('8320000000001','wellbaby','คลินิกสุขภาพเด็กดี');
insert into r_epi_group_clinic values ('8320000000002','TT','ป้องกันโรคบาดทะยัก');



--
-- Data for Name: r_eye_disease_code; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_eye_disease_code (r_eye_disease_code_id, r_eye_group_id, eye_disease_code_begin, eye_disease_code_end) FROM stdin;');
insert into r_eye_disease_code values ('8140000000001','8130000000001','H25','H26.99');
insert into r_eye_disease_code values ('8140000000002','8130000000002','H40','H42.99');
insert into r_eye_disease_code values ('8140000000003','8130000000003','B02.3','B02.39');
insert into r_eye_disease_code values ('8140000000004','8130000000003','H10','H10.99');
insert into r_eye_disease_code values ('8140000000005','8130000000004','P39.1','P39.19');
insert into r_eye_disease_code values ('8140000000006','8130000000005','H16','H16.09');
insert into r_eye_disease_code values ('8140000000007','8130000000006','A71','A71.99');
insert into r_eye_disease_code values ('8140000000008','8130000000007','H11','H11.09');
insert into r_eye_disease_code values ('8140000000010','8130000000009','S05','S05.99');
insert into r_eye_disease_code values ('8140000000011','8130000000010','H52','H52.99');
insert into r_eye_disease_code values ('8140000000012','8130000000011','H00','H06.39');
insert into r_eye_disease_code values ('8140000000014','8130000000011','H13','H13.89');
insert into r_eye_disease_code values ('8140000000015','8130000000011','H15','H22.89');
insert into r_eye_disease_code values ('8140000000016','8130000000011','H30','H36.89');
insert into r_eye_disease_code values ('8140000000017','8130000000011','H42','H51.99');
insert into r_eye_disease_code values ('8140000000018','8130000000011','H53','H55.09');
insert into r_eye_disease_code values ('8140000000019','8130000000011','H57','H62.99');
insert into r_eye_disease_code values ('8140000000020','8130000000011','H65','H71.09');
insert into r_eye_disease_code values ('8140000000021','8130000000011','H72','H75.99');
insert into r_eye_disease_code values ('8140000000022','8130000000011','H80','H83.09');
insert into r_eye_disease_code values ('8140000000023','8130000000011','H90','H95.19');
insert into r_eye_disease_code values ('8140000000009','8130000000008','E50','E50.99');



--
-- Data for Name: r_eye_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_eye_group (r_eye_group_id, eye_group_number, eye_group_description_th, eye_group_description_en, eye_group_active) FROM stdin;');
insert into r_eye_group values ('8130000000001','1','ต้อกระจก','Cataract','1');
insert into r_eye_group values ('8130000000002','2','ต้อหิน','Glaucoma','1');
insert into r_eye_group values ('8130000000004','4','เยื่อตาอักเสบในเด็กแรกเกิด','Neonatal Ophthalmia','1');
insert into r_eye_group values ('8130000000005','5','แผลในตาดำ','Cornia Ulcer','1');
insert into r_eye_group values ('8130000000006','6','ริดสีดวงตา','Trachoma','1');
insert into r_eye_group values ('8130000000007','7','ต้อเนื้อ','Pterygium','1');
insert into r_eye_group values ('8130000000008','8','โรคตาจากการขาดสารอาหาร','Eye disease due to Malnutrition','1');
insert into r_eye_group values ('8130000000009','9','บาดเจ็บที่ตา','Eye Injuries','1');
insert into r_eye_group values ('8130000000010','10','สายตาผิดปกติ','Refractive error','1');
insert into r_eye_group values ('8130000000011','11','อื่นๆ','Others','1');
insert into r_eye_group values ('8130000000003','3','เยื่อตาอักเสบ','Conjunctivitis','1');



--
-- Data for Name: r_healthy_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_healthy_group (r_healthy_group_id, healthy_group_number, healthy_group_description) FROM stdin;');
insert into r_healthy_group values ('8250000000001','WellBaby','คลินิกสุขภาพเด็กดี');
insert into r_healthy_group values ('8250000000002','Healthy(6-14)','ได้รับการตรวจประเมินสุขภาพ สำหรับการดูแลเด็กโต และ เยาวชน(6-14 ปี)');
insert into r_healthy_group values ('8250000000003','Healthy(15Up)','ได้รับการตรวจประเมินสุขภาพ สำหรับการดูแลเด็ก และ ผู้ใหญ่(15 ปีขึ้นไป)');



--
-- Data for Name: r_healthy_group_survey; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_healthy_group_survey (r_healthy_group_survey_id, r_healthy_subgroup_id, b_health_survey_id, healthy_group_survey_description) FROM stdin;');
insert into r_healthy_group_survey values ('8260738783073','8270000000001','7860000000001','ซักประวัติ');
insert into r_healthy_group_survey values ('8269322531425','8270000000001','7860000000002','สัมภาษณ์');
insert into r_healthy_group_survey values ('8262995214631','8270000000001','7860000000003','ตรวจร่างกาย');
insert into r_healthy_group_survey values ('8263471249760','8270000000002','7860000000004','ประเมินพัฒนาการ/พฤติกรรม');
insert into r_healthy_group_survey values ('8268138126067','8270000000003','7860000000005','ชั่งน้ำหนัก');
insert into r_healthy_group_survey values ('8265629472902','8270000000003','7860000000006','วัดส่วนสูง');
insert into r_healthy_group_survey values ('8263024465582','8270000000003','7860000000007','ประเมินภาวะโภชนาการ');
insert into r_healthy_group_survey values ('8263292945327','8270000000004','7860000000008','วัดเส้นรอบศีรษะ');
insert into r_healthy_group_survey values ('8262797475435','8270000000005','7860000000009','วัดความดันโลหิต');
insert into r_healthy_group_survey values ('8268875643417','8270000000006','7860000000001','ซักประวัติ');
insert into r_healthy_group_survey values ('8269039281654','8270000000006','7860000000002','สัมภาษณ์');
insert into r_healthy_group_survey values ('8261502975339','8270000000009','7860000000002','สัมภาษณ์');
insert into r_healthy_group_survey values ('8260397837120','8270000000006','7860000000003','ตรวจร่างกาย');
insert into r_healthy_group_survey values ('8264429587513','8270000000007','7860000000004','ประเมินพัฒนาการ/พฤติกรรม');
insert into r_healthy_group_survey values ('8269519145510','8270000000008','7860000000005','ชั่งน้ำหนัก');
insert into r_healthy_group_survey values ('8262617375507','8270000000008','7860000000006','วัดส่วนสูง');
insert into r_healthy_group_survey values ('8266249577512','8270000000008','7860000000007','ประเมินภาวะโภชนาการ');
insert into r_healthy_group_survey values ('8268357868012','8270000000009','7860000000001','ซักประวัติ');
insert into r_healthy_group_survey values ('8266093109347','8270000000009','7860000000003','ตรวจร่างกาย');
insert into r_healthy_group_survey values ('8267848909669','8270000000010','7860000000011','ดัชนีมวลกายและเส้นรอบเอว');
insert into r_healthy_group_survey values ('8260841839924','8270000000011','7860000000009','วัดความดันโลหิต');
insert into r_healthy_group_survey values ('8265457599793','8270000000012','7860000000012','ตรวจสายตา');



--
-- Data for Name: r_healthy_subgroup; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_healthy_subgroup (r_healthy_subgroup_id, r_healthy_group_id, healthy_subgroup_number, healthy_subgroup_description) FROM stdin;');
insert into r_healthy_subgroup values ('8270000000001','8250000000001','w01','ซักประวัติ/สัมภาษณ์ และตรวจร่างกาย');
insert into r_healthy_subgroup values ('8270000000002','8250000000001','w02','ประเมินพัฒนาการ/พฤติกรรม');
insert into r_healthy_subgroup values ('8270000000003','8250000000001','w03','ชั่งน้ำหนักและวัดส่วนสูงและประเมินภาวะโภชนาการ');
insert into r_healthy_subgroup values ('8270000000004','8250000000001','w04','วัดเส้นรอบศีรษะ ในเด็กอายุน้อยกว่า 3 ปี');
insert into r_healthy_subgroup values ('8270000000005','8250000000001','w05','วัดความดันโลหิตในเด็ก 4-5 ปี');
insert into r_healthy_subgroup values ('8270000000006','8250000000002','h01_614','ซักประวัติ/สัมภาษณ์/และตรงจร่างกาย');
insert into r_healthy_subgroup values ('8270000000007','8250000000002','h02_614','ประเมินพัฒนาการ/พฤติกรรม');
insert into r_healthy_subgroup values ('8270000000008','8250000000002','h03_614','ชั่งน้ำหนักและวัดส่วนสูงและประเมินภาวะโภชนาการ');
insert into r_healthy_subgroup values ('8270000000009','8250000000003','h01_15','ซักประวัติ/สัมภาษณ์/และตรวจร่างกาย');
insert into r_healthy_subgroup values ('8270000000010','8250000000003','h02_15','วัดดัชนีมวลกายและเส้นรอบเอว');
insert into r_healthy_subgroup values ('8270000000011','8250000000003','h03_15','วัดความดันโลหิต');
insert into r_healthy_subgroup values ('8270000000012','8250000000003','h04_15','ตรวจสายตา');



--
-- Data for Name: r_ncd_item_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_ncd_item_group (r_ncd_item_group_id, ncd_item_group_number, ncd_item_group_description) FROM stdin;');
insert into r_ncd_item_group values ('8440000000001','1','FBS');
insert into r_ncd_item_group values ('8440000000002','2','ส่งพบโภชนากร');
insert into r_ncd_item_group values ('8440000000003','3','ส่งพบเภสัชกร');




--
-- Data for Name: r_nutrition_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_nutrition_type (r_nutrition_type_id, nutrition_type_number, nutrition_type_description) FROM stdin;');
insert into r_nutrition_type values ('8310000000001','N','ปกติ');
insert into  r_nutrition_type values ('8310000000002','1','ระดับ 1');
insert into  r_nutrition_type values ('8310000000003','2','ระดับ 2');
insert into  r_nutrition_type values ('8310000000004','3','ระดับ 3');




--
-- Data for Name: r_plan_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_plan_group (r_plan_group_id, plan_group_number, plan_group_description, plan_group_active) FROM stdin;');
insert into  r_plan_group values ('8030000000001','1','ข้าราชการ / รัฐวิสาหกิจ','1');
insert into  r_plan_group values ('8030000000002','2','ประกันสังคม','1');
insert into  r_plan_group values ('8030000000003','3','UC บัตรทองไม่มี ท.','1');
insert into  r_plan_group values ('8030000000004','4','UC บัตรทองมี ท.','1');
insert into  r_plan_group values ('8030000000005','5','แรงงานต่างด้าวที่ขึ้นทะเบียน','1');
insert into  r_plan_group values ('8030000000006','6','อื่นๆ (ต่างด้าวไม่ขึ้นทะเบียน,สิทธิไม่ชัดเจน,ไม่ใช้สิทธิ)','1');



--
-- Data for Name: r_plan_group_map_pttype; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_plan_group_map_pttype (r_plan_group_map_pttype_id, r_plan_group_id, plan_group_map_pttype_pttype) FROM stdin;');
insert into  r_plan_group_map_pttype values ('8040000000002','8030000000002','A7');
insert into  r_plan_group_map_pttype values ('8040000000003','8030000000003','UC');
insert into  r_plan_group_map_pttype values ('8040000000004','8030000000004','AE');
insert into  r_plan_group_map_pttype values ('8040000000005','8030000000004','AL');
insert into  r_plan_group_map_pttype values ('8040000000006','8030000000004','AF');
insert into  r_plan_group_map_pttype values ('8040000000007','8030000000004','AJ');
insert into  r_plan_group_map_pttype values ('8040000000008','8030000000004','AK');
insert into  r_plan_group_map_pttype values ('8040000000009','8030000000004','AB');
insert into  r_plan_group_map_pttype values ('8040000000010','8030000000004','AG');
insert into  r_plan_group_map_pttype values ('8040000000011','8030000000004','AA');
insert into  r_plan_group_map_pttype values ('8040000000012','8030000000004','AD');
insert into  r_plan_group_map_pttype values ('8040000000013','8030000000004','AC');
insert into  r_plan_group_map_pttype values ('8040000000015','8030000000005','AR');
insert into  r_plan_group_map_pttype values ('8040000000016','8030000000006','A1');
insert into  r_plan_group_map_pttype values ('8040000000017','8030000000006','AM');
insert into  r_plan_group_map_pttype values ('8040000000018','8030000000006','AN');
insert into  r_plan_group_map_pttype values ('8040000000019','8030000000006','AP');
insert into  r_plan_group_map_pttype values ('8040000000020','8030000000006','A9');
insert into  r_plan_group_map_pttype values ('8040000000001','8030000000001','A2');



--
-- Data for Name: r_refer_office_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_refer_office_type (r_refer_office_type_id, refer_office_type_description) FROM stdin;');
insert into  r_refer_office_type values ('01','สอ.');
insert into  r_refer_office_type values ('02','รพช.');
insert into  r_refer_office_type values ('03','รพท. หรือ รพศ.');




--
-- Data for Name: r_rp11_disease; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_rp11_disease (r_rp11_disease_id, rp11_disease_number, rp11_disease_description) FROM stdin;');
insert into  r_rp11_disease values ('8280000000001','PS','ตรวจคัดกรองมะเร็งปากมดลูก');
insert into  r_rp11_disease values ('8280000000002','BCS','ตรวจคัดกรอง Breast Cancer Screening');
insert into  r_rp11_disease values ('8280000000003','Thalassemia1','ตรวจคัดกรองโรคโลหิตจางธาลัสซีเมีย');
insert into  r_rp11_disease values ('8280000000004','Thalassemia2','ตรวจคัดกรองโรคโลหิตจางธาลัสซีเมีย(ทารกในครรภ์)');
insert into  r_rp11_disease values ('8280000000005','IT1','ตรวจคัดกรองภาวะพร่องไอโอดีนและไทรอยด์');
insert into  r_rp11_disease values ('8280000000006','IT2','รักษาภาวะพร่องไอโอดีนและไทรอยด์');



--
-- Data for Name: r_rp504_disease_code; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_rp504_disease_code (r_rp504_disease_code_id, f_group_rp504_id, rp504_disease_code_number, rp504_disease_code_begin, rp504_disease_code_end) FROM stdin;');
insert into  r_rp504_disease_code values ('8070000000001','3060000000001','1','A00','A99.99');
insert into  r_rp504_disease_code values ('8070000000002','3060000000001','1','B00','B99.99');
insert into  r_rp504_disease_code values ('8070000000003','3060000000002','2','C00','C97.99');
insert into  r_rp504_disease_code values ('8070000000004','3060000000002','2','D00','D48.99');
insert into  r_rp504_disease_code values ('8070000000005','3060000000003','3','D50','D89.99');
insert into  r_rp504_disease_code values ('8070000000007','3060000000005','5','F00','F99.99');
insert into  r_rp504_disease_code values ('8070000000008','3060000000006','6','G00','G99.99');
insert into  r_rp504_disease_code values ('8070000000009','3060000000007','7','H00','H59.99');
insert into  r_rp504_disease_code values ('8070000000010','3060000000008','8','H60','H95.99');
insert into  r_rp504_disease_code values ('8070000000011','3060000000009','9','I00','I99.99');
insert into  r_rp504_disease_code values ('8070000000012','3060000000010','10','J00','J99.99');
insert into  r_rp504_disease_code values ('8070000000013','3060000000011','11','K00','K93.99');
insert into  r_rp504_disease_code values ('8070000000014','3060000000012','12','L00','L99.99');
insert into  r_rp504_disease_code values ('8070000000015','3060000000013','13','M00','M99.99');
insert into  r_rp504_disease_code values ('8070000000016','3060000000014','14','N00','N99.99');
insert into  r_rp504_disease_code values ('8070000000017','3060000000015','15','O00','O79.99');
insert into  r_rp504_disease_code values ('8070000000018','3060000000015','15','O85','O99.99');
insert into  r_rp504_disease_code values ('8070000000019','3060000000016','16','P00','P96.99');
insert into  r_rp504_disease_code values ('8070000000020','3060000000017','17','Q00','Q99.99');
insert into  r_rp504_disease_code values ('8070000000021','3060000000018','18','R00','R99.99');
insert into  r_rp504_disease_code values ('8070000000022','3060000000019','19','X40','X49.99');
insert into  r_rp504_disease_code values ('8070000000023','3060000000019','19','X60','X69.99');
insert into  r_rp504_disease_code values ('8070000000024','3060000000019','19','X85','X90.99');
insert into  r_rp504_disease_code values ('8070000000026','3060000000020','20','V01','V99.99');
insert into  r_rp504_disease_code values ('8070000000027','3060000000020','20','Y85','Y85.99');
insert into  r_rp504_disease_code values ('8070000000028','3060000000021','21','W00','W99.99');
insert into  r_rp504_disease_code values ('8070000000029','3060000000021','21','X00','X19.99');
insert into  r_rp504_disease_code values ('8070000000030','3060000000021','21','X20','X29.99');
insert into  r_rp504_disease_code values ('8070000000031','3060000000021','21','X30','X39.99');
insert into  r_rp504_disease_code values ('8070000000032','3060000000021','21','X50','X59.99');
insert into  r_rp504_disease_code values ('8070000000033','3060000000021','21','X70','X84.99');
insert into  r_rp504_disease_code values ('8070000000034','3060000000021','21','X91','X99.99');
insert into  r_rp504_disease_code values ('8070000000035','3060000000021','21','Y00','Y09.99');
insert into  r_rp504_disease_code values ('8070000000036','3060000000021','21','Y20','Y36.99');
insert into  r_rp504_disease_code values ('8070000000037','3060000000021','21','Y40','Y84.99');
insert into  r_rp504_disease_code values ('8070000000038','3060000000021','21','Y86','Y89.99');
insert into  r_rp504_disease_code values ('8070000000025','3060000000019','19','Y10','Y10.99');
insert into  r_rp504_disease_code values ('8070000000006','3060000000004','4','E00','E90.99');



--
-- Data for Name: r_rp505_disease_code; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_rp505_disease_code (r_rp505_disease_code_id, f_group_rp505_id, rp505_disease_code_number, rp505_disease_code_begin, rp505_disease_code_end) FROM stdin;');
insert into  r_rp505_disease_code values ('8080000000002','3070000000002','2','A03','A09.99');
insert into  r_rp505_disease_code values ('8080000000003','3070000000003','3','A15','A19.99');
insert into  r_rp505_disease_code values ('8080000000004','3070000000004','4','A30','A30.99');
insert into  r_rp505_disease_code values ('8080000000005','3070000000005','5','A83','A86.99');
insert into  r_rp505_disease_code values ('8080000000006','3070000000006','6','A91','A92.99');
insert into  r_rp505_disease_code values ('8080000000007','3070000000007','7','B15','B19.99');
insert into  r_rp505_disease_code values ('8080000000008','3070000000008','8','B20','B24.99');
insert into  r_rp505_disease_code values ('8080000000009','3070000000009','9','B50','B54.99');
insert into  r_rp505_disease_code values ('8080000000010','3070000000010','10','A20','A28.99');
insert into  r_rp505_disease_code values ('8080000000011','3070000000010','10','A31','A32.99');
insert into  r_rp505_disease_code values ('8080000000018','3070000000010','10','A87','A90.99');
insert into  r_rp505_disease_code values ('8080000000028','3070000000011','11','C22','C22.99');
insert into  r_rp505_disease_code values ('8080000000029','3070000000012','12','C34','C34.99');
insert into  r_rp505_disease_code values ('8080000000030','3070000000013','13','C50','C50.99');
insert into  r_rp505_disease_code values ('8080000000031','3070000000014','14','C53','C53.99');
insert into  r_rp505_disease_code values ('8080000000032','3070000000015','15','D50','D53.99');
insert into  r_rp505_disease_code values ('8080000000033','3070000000015','15','D55','D55.99');
insert into  r_rp505_disease_code values ('8080000000034','3070000000015','15','D57','D77.99');
insert into  r_rp505_disease_code values ('8080000000035','3070000000015','15','D80','D84.99');
insert into  r_rp505_disease_code values ('8080000000036','3070000000015','15','D86','D86.99');
insert into  r_rp505_disease_code values ('8080000000037','3070000000015','15','D89','D89.99');
insert into  r_rp505_disease_code values ('8080000000038','3070000000016','16','D56','D56.99');
insert into  r_rp505_disease_code values ('8080000000039','3070000000017','17','E00','E07.99');
insert into  r_rp505_disease_code values ('8080000000040','3070000000018','18','E10','E14.99');
insert into  r_rp505_disease_code values ('8080000000041','3070000000019','19','E15','E16.99');
insert into  r_rp505_disease_code values ('8080000000042','3070000000019','19','E20','E32.99');
insert into  r_rp505_disease_code values ('8080000000043','3070000000019','19','E34','E35.99');
insert into  r_rp505_disease_code values ('8080000000044','3070000000019','19','E40','E46.99');
insert into  r_rp505_disease_code values ('8080000000045','3070000000019','19','E50','E56.99');
insert into  r_rp505_disease_code values ('8080000000046','3070000000019','19','E58','E61.99');
insert into  r_rp505_disease_code values ('8080000000047','3070000000019','19','E63','E68.99');
insert into  r_rp505_disease_code values ('8080000000048','3070000000019','19','E70','E80.99');
insert into  r_rp505_disease_code values ('8080000000049','3070000000019','19','E83','E90.99');
insert into  r_rp505_disease_code values ('8080000000050','3070000000020','20','F04','F07.99');
insert into  r_rp505_disease_code values ('8080000000051','3070000000020','20','F09','F09.99');
insert into  r_rp505_disease_code values ('8080000000056','3070000000024','24','F40','F45.99');
insert into  r_rp505_disease_code values ('8080000000057','3070000000024','24','F48','F48.99');
insert into  r_rp505_disease_code values ('8080000000058','3070000000025','25','F70','F73.99');
insert into  r_rp505_disease_code values ('8080000000059','3070000000025','25','F78','F79.99');
insert into  r_rp505_disease_code values ('8080000000060','3070000000026','26','G40','G41.99');
insert into  r_rp505_disease_code values ('8080000000061','3070000000027','27','G00','G13.99');
insert into  r_rp505_disease_code values ('8080000000062','3070000000027','27','G20','G26.99');
insert into  r_rp505_disease_code values ('8080000000063','3070000000027','27','G30','G32.99');
insert into  r_rp505_disease_code values ('8080000000064','3070000000027','27','G35','G37.99');
insert into  r_rp505_disease_code values ('8080000000065','3070000000027','27','G43','G47.99');
insert into  r_rp505_disease_code values ('8080000000066','3070000000027','27','G50','G64.99');
insert into  r_rp505_disease_code values ('8080000000067','3070000000027','27','G70','G73.99');
insert into  r_rp505_disease_code values ('8080000000068','3070000000027','27','G80','G83.99');
insert into  r_rp505_disease_code values ('8080000000069','3070000000027','27','G90','G99.99');
insert into  r_rp505_disease_code values ('8080000000082','3070000000030','30','I00','I02.99');
insert into  r_rp505_disease_code values ('8080000000090','3070000000037','37','J00','J06.99');
insert into  r_rp505_disease_code values ('8080000000092','3070000000040','40','J40','J44.99');
insert into  r_rp505_disease_code values ('8080000000093','3070000000040','40','J47','J47.99');
insert into  r_rp505_disease_code values ('8080000000094','3070000000041','41','J45','J46.99');
insert into  r_rp505_disease_code values ('8080000000095','3070000000042','42','J20','J22.99');
insert into  r_rp505_disease_code values ('8080000000101','3070000000043','43','K25','K27.99');
insert into  r_rp505_disease_code values ('8080000000103','3070000000045','45','K40','K46.99');
insert into  r_rp505_disease_code values ('8080000000104','3070000000046','46','K50','K52.99');
insert into  r_rp505_disease_code values ('8080000000105','3070000000046','46','K55','K55.99');
insert into  r_rp505_disease_code values ('8080000000106','3070000000046','46','K65','K67.99');
insert into  r_rp505_disease_code values ('8080000000107','3070000000047','47','K56','K56.99');
insert into  r_rp505_disease_code values ('8080000000108','3070000000048','48','K70','K70.99');
insert into  r_rp505_disease_code values ('8080000000109','3070000000049','49','K80','K81.99');
insert into  r_rp505_disease_code values ('8080000000110','3070000000050','50','K00','K14.99');
insert into  r_rp505_disease_code values ('8080000000111','3070000000050','50','K20','K23.99');
insert into  r_rp505_disease_code values ('8080000000112','3070000000050','50','K28','K31.99');
insert into  r_rp505_disease_code values ('8080000000113','3070000000050','50','K57','K63.99');
insert into  r_rp505_disease_code values ('8080000000114','3070000000050','50','K71','K77.99');
insert into  r_rp505_disease_code values ('8080000000115','3070000000050','50','K82','K83.99');
insert into  r_rp505_disease_code values ('8080000000116','3070000000050','50','K85','K87.99');
insert into  r_rp505_disease_code values ('8080000000117','3070000000050','50','K90','K93.99');
insert into  r_rp505_disease_code values ('8080000000127','3070000000052','52','M00','M03.99');
insert into  r_rp505_disease_code values ('8080000000128','3070000000052','52','M05','M25.99');
insert into  r_rp505_disease_code values ('8080000000129','3070000000052','52','M40','M43.99');
insert into  r_rp505_disease_code values ('8080000000130','3070000000052','52','M45','M51.99');
insert into  r_rp505_disease_code values ('8080000000131','3070000000052','52','M53','M54.99');
insert into  r_rp505_disease_code values ('8080000000132','3070000000052','52','M60','M63.99');
insert into  r_rp505_disease_code values ('8080000000133','3070000000052','52','M65','M68.99');
insert into  r_rp505_disease_code values ('8080000000134','3070000000052','52','M70','M73.99');
insert into  r_rp505_disease_code values ('8080000000135','3070000000052','52','M75','M77.99');
insert into  r_rp505_disease_code values ('8080000000136','3070000000052','52','M79','M96.99');
insert into  r_rp505_disease_code values ('8080000000137','3070000000052','52','M99','M99.99');
insert into  r_rp505_disease_code values ('8080000000138','3070000000053','53','M30','M36.99');
insert into  r_rp505_disease_code values ('8080000000140','3070000000057','57','N40','N51.99');
insert into  r_rp505_disease_code values ('8080000000141','3070000000058','58','N60','N64.99');
insert into  r_rp505_disease_code values ('8080000000142','3070000000059','59','N70','N77.99');
insert into  r_rp505_disease_code values ('8080000000143','3070000000059','59','N80','N97.99');
insert into  r_rp505_disease_code values ('8080000000144','3070000000059','59','N98','N98.99');
insert into  r_rp505_disease_code values ('8080000000145','3070000000060','60','N00','N08.99');
insert into  r_rp505_disease_code values ('8080000000146','3070000000060','60','N10','N16.99');
insert into  r_rp505_disease_code values ('8080000000147','3070000000060','60','N25','N37.99');
insert into  r_rp505_disease_code values ('8080000000148','3070000000060','60','N39','N39.99');
insert into  r_rp505_disease_code values ('8080000000149','3070000000060','60','N99','N99.99');
insert into  r_rp505_disease_code values ('8080000000150','3070000000061','61','O00','O08.99');
insert into  r_rp505_disease_code values ('8080000000151','3070000000063','63','O10','O16.99');
insert into  r_rp505_disease_code values ('8080000000152','3070000000063','63','O20','O26.99');
insert into  r_rp505_disease_code values ('8080000000153','3070000000063','63','O28','O36.99');
insert into  r_rp505_disease_code values ('8080000000154','3070000000063','63','O40','O48.99');
insert into  r_rp505_disease_code values ('8080000000155','3070000000063','63','O60','O75.99');
insert into  r_rp505_disease_code values ('8080000000156','3070000000063','63','O81','O92.99');
insert into  r_rp505_disease_code values ('8080000000157','3070000000063','63','O95','O99.99');
insert into  r_rp505_disease_code values ('8080000000158','3070000000064','64','P10','P15.99');
insert into  r_rp505_disease_code values ('8080000000159','3070000000065','65','P00','P05.99');
insert into  r_rp505_disease_code values ('8080000000160','3070000000065','65','P07','P08.99');
insert into  r_rp505_disease_code values ('8080000000161','3070000000065','65','P20','P29.99');
insert into  r_rp505_disease_code values ('8080000000162','3070000000065','65','P35','P39.99');
insert into  r_rp505_disease_code values ('8080000000163','3070000000065','65','P50','P61.99');
insert into  r_rp505_disease_code values ('8080000000164','3070000000065','65','P70','P72.99');
insert into  r_rp505_disease_code values ('8080000000165','3070000000065','65','P74','P78.99');
insert into  r_rp505_disease_code values ('8080000000166','3070000000065','65','P80','P81.99');
insert into  r_rp505_disease_code values ('8080000000167','3070000000065','65','P83','P83.99');
insert into  r_rp505_disease_code values ('8080000000168','3070000000065','65','P90','P96.99');
insert into  r_rp505_disease_code values ('8080000000176','3070000000067','67','R00','R07.99');
insert into  r_rp505_disease_code values ('8080000000177','3070000000067','67','R09','R23.99');
insert into  r_rp505_disease_code values ('8080000000178','3070000000067','67','R25','R27.99');
insert into  r_rp505_disease_code values ('8080000000179','3070000000067','67','R29','R36.99');
insert into  r_rp505_disease_code values ('8080000000180','3070000000067','67','R39','R64.99');
insert into  r_rp505_disease_code values ('8080000000181','3070000000067','67','R68','R87.99');
insert into  r_rp505_disease_code values ('8080000000182','3070000000067','67','R89','R96.99');
insert into  r_rp505_disease_code values ('8080000000183','3070000000067','67','R98','R99.99');
insert into  r_rp505_disease_code values ('8080000000186','3070000000070','70','Y85','Y85.99');
insert into  r_rp505_disease_code values ('8080000000187','3070000000071','71','X40','X49.99');
insert into  r_rp505_disease_code values ('8080000000188','3070000000071','71','X60','X69.99');
insert into  r_rp505_disease_code values ('8080000000190','3070000000071','71','Y10','Y19.99');
insert into  r_rp505_disease_code values ('8080000000201','3070000000073','73','X70','X84.99');
insert into  r_rp505_disease_code values ('8080000000001','3070000000001','1','A01','A02.99');
insert into  r_rp505_disease_code values ('8080000000012','3070000000010','10','A38','A49.99');
insert into  r_rp505_disease_code values ('8080000000014','3070000000010','10','A50','A79.99');
insert into  r_rp505_disease_code values ('8080000000017','3070000000010','10','A80','A82.99');
insert into  r_rp505_disease_code values ('8080000000019','3070000000010','10','A93','A99.99');
insert into  r_rp505_disease_code values ('8080000000020','3070000000010','10','B00','B09.99');
insert into  r_rp505_disease_code values ('8080000000021','3070000000010','10','B25','B49.99');
insert into  r_rp505_disease_code values ('8080000000023','3070000000010','10','B55','B99.99');
insert into  r_rp505_disease_code values ('8080000000216','3070000000021','21','F10','F19.99');
insert into  r_rp505_disease_code values ('8080000000053','3070000000022','22','F20','F29.99');
insert into  r_rp505_disease_code values ('8080000000055','3070000000023','23','F30','F39.99');
insert into  r_rp505_disease_code values ('8080000000070','3070000000028','28','H00','H59.99');
insert into  r_rp505_disease_code values ('8080000000079','3070000000029','29','H60','H95.99');
insert into  r_rp505_disease_code values ('8080000000083','3070000000031','31','I05','I09.99');
insert into  r_rp505_disease_code values ('8080000000084','3070000000032','32','I10','I15.99');
insert into  r_rp505_disease_code values ('8080000000085','3070000000033','33','I20','I25.99');
insert into  r_rp505_disease_code values ('8080000000086','3070000000035','35','I60','I69.99');
insert into  r_rp505_disease_code values ('8080000000087','3070000000036','36','I70','I99.99');
insert into  r_rp505_disease_code values ('8080000000088','3070000000034','34','I26','I52.99');
insert into  r_rp505_disease_code values ('8080000000091','3070000000037','37','J30','J39.99');
insert into  r_rp505_disease_code values ('8080000000245','3070000000038','38','J10','J11.99');
insert into  r_rp505_disease_code values ('8080000000097','3070000000039','39','J12','J18.99');
insert into  r_rp505_disease_code values ('8080000000096','3070000000042','42','J60','J99.99');
insert into  r_rp505_disease_code values ('8080000000102','3070000000044','44','K35','K38.99');
insert into  r_rp505_disease_code values ('8080000000118','3070000000051','51','L00','L99.99');
insert into  r_rp505_disease_code values ('8080000000139','3070000000054','54','N17','N17.99');
insert into  r_rp505_disease_code values ('8080000000169','3070000000066','66','Q00','Q99.99');
insert into  r_rp505_disease_code values ('8080000000173','3070000000055','55','N18','N19.99');
insert into  r_rp505_disease_code values ('8080000000174','3070000000056','56','N20','N23.99');
insert into  r_rp505_disease_code values ('8080000000175','3070000000062','62','O80','O80.99');
insert into  r_rp505_disease_code values ('8080000000184','3070000000068','68','V01','V19.99');
insert into  r_rp505_disease_code values ('8080000000189','3070000000071','71','X85','X90.99');
insert into  r_rp505_disease_code values ('8080000000191','3070000000072','72','W00','W99.99');
insert into  r_rp505_disease_code values ('8080000000192','3070000000069','69','V20','V29.99');
insert into  r_rp505_disease_code values ('8080000000196','3070000000072','72','X00','X39.99');
insert into  r_rp505_disease_code values ('8080000000197','3070000000072','72','X50','X59.99');
insert into  r_rp505_disease_code values ('8080000000198','3070000000072','72','Y86','Y86.99');
insert into  r_rp505_disease_code values ('8080000000202','3070000000074','74','X91','Y09.99');
insert into  r_rp505_disease_code values ('8080000000203','3070000000075','75','Y20','Y36.99');
insert into  r_rp505_disease_code values ('8080000000204','3070000000075','75','Y40','Y84.99');
insert into  r_rp505_disease_code values ('8080000000205','3070000000075','75','Y87','Y89.99');
insert into  r_rp505_disease_code values ('8080000000193','3070000000070','70','V30','V99.99');



--
-- Data for Name: r_service_point_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_service_point_type (r_service_point_type_id, service_point_type_number, service_point_type_description) FROM stdin;');
insert into  r_service_point_type values ('8420000000001','1','อุบัติเหตุ-ฉุกเฉิน');
insert into  r_service_point_type values ('8420000000002','2','OPD');
insert into  r_service_point_type values ('8420000000003','3','NCD');


-- Data for Name: r_uc_plan_group; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_uc_plan_group (r_uc_plan_group_id, uc_plan_group_number, uc_plan_group_description, uc_plan_group_active) FROM stdin;');
insert into  r_uc_plan_group values ('8050000000001','1','ข้าราชการ/รัฐวิสาหกิจ','1');
insert into  r_uc_plan_group values ('8050000000002','2','ประกันสังคม','1');
insert into  r_uc_plan_group values ('8050000000003','3','UC ในเครือข่าย','1  ');
insert into  r_uc_plan_group values ('8050000000004','4','UC นอกเครือข่าย','1');
insert into  r_uc_plan_group values ('8050000000005','5','สิทธิอื่น ๆ','1');



--
-- Data for Name: r_uc_plan_group_pttype; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY r_uc_plan_group_pttype (r_uc_plan_group_pttype_id, uc_plan_group_pttype_number, uc_plan_group_pttype_pttype) FROM stdin;');
insert into  r_uc_plan_group_pttype values ('8060000000001','1','UC');
insert into  r_uc_plan_group_pttype values ('8060000000002','2','AE');
insert into  r_uc_plan_group_pttype values ('8060000000003','3','AL');
insert into  r_uc_plan_group_pttype values ('8060000000004','4','AF');
insert into  r_uc_plan_group_pttype values ('8060000000005','5','AJ');
insert into  r_uc_plan_group_pttype values ('8060000000006','6','AK');
insert into  r_uc_plan_group_pttype values ('8060000000007','7','AB');
insert into  r_uc_plan_group_pttype values ('8060000000008','8','AG');
insert into  r_uc_plan_group_pttype values ('8060000000009','9','AA');
insert into  r_uc_plan_group_pttype values ('8060000000010','10','AD');
insert into  r_uc_plan_group_pttype values ('8060000000011','11','AC');



--
-- Data for Name: s_report_version; Type: TABLE DATA; Schema: public; Owner: postgres
--

--COPY s_report_version (s_report_version_id, report_version_number, report_version_description, report_version_application_number, report_version_notice, report_version_update_date_time) FROM stdin;');
insert into  s_report_version values ('8170000000001','01','HospitalOS Report','1.00.1048','for hospitalOS v3 db : 3.13.1048 and pcu db : 0.02.1048','2548-10-14 14:45:00');
insert into  s_report_version values ('9720000000002','02','HospitalOS Report','1.01.1148','for hospitalOS v3 db : 3.13.1048 and pcu db : 0.02.1048','2548-11-21 22:00:00');
insert into  s_report_version values ('9720000000003','03','HospitalOS Report','1.03.291206','for hospitalOS v3 db : 3.13.1048 and pcu db : 0.03.1148','2548-12-29 20:00:00');
insert into  s_report_version values ('9720000000004','04','HospitalOS Report','1.04.240306','for hospitalOS v3 db : 3.13.1048 and pcu db : 0.03.1148','2549-03-24 18:00:00');
insert into  s_report_version values ('9720000000005','05','HospitalOS Report','1.05.290406','for hospitalOS v3 db : 3.13.1048 and pcu db : 0.03.1148','2549-04-29 18:00:00');
insert into  s_report_version values ('9720000000006','06','HospitalOS Report For Nan','1.06.220606','for hospitalOS v3 db : 3.14n.050506 and pcu db : 0.04.0449','2549-06-22 11:16:00');




--
-- Name: r_accident_group_code_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_accident_group_code
    ADD CONSTRAINT r_accident_group_code_pk PRIMARY KEY (r_accident_group_code_id);


ALTER INDEX public.r_accident_group_code_pk OWNER TO postgres;

--
-- Name: r_accident_group_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_accident_group
    ADD CONSTRAINT r_accident_group_pk PRIMARY KEY (r_accident_group_id);


ALTER INDEX public.r_accident_group_pk OWNER TO postgres;

--
-- Name: r_aric_antibiotic_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_aric_antibiotic
    ADD CONSTRAINT r_aric_antibiotic_id PRIMARY KEY (r_aric_antibiotic_id);


ALTER INDEX public.r_aric_antibiotic_id OWNER TO postgres;

--
-- Name: r_aric_disease_code_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_aric_disease_code
    ADD CONSTRAINT r_aric_disease_code_id PRIMARY KEY (r_aric_disease_code_id);


ALTER INDEX public.r_aric_disease_code_id OWNER TO postgres;

--
-- Name: r_aric_group_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_aric_group
    ADD CONSTRAINT r_aric_group_id_pk PRIMARY KEY (r_aric_group_id);


ALTER INDEX public.r_aric_group_id_pk OWNER TO postgres;

--
-- Name: r_aric_subgroup_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_aric_subgroup
    ADD CONSTRAINT r_aric_subgroup_id PRIMARY KEY (r_aric_subgroup_id);


ALTER INDEX public.r_aric_subgroup_id OWNER TO postgres;

--
-- Name: r_dental_protect_item_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_dental_protect_item
    ADD CONSTRAINT r_dental_protect_item_id_pk PRIMARY KEY (r_dental_protect_item_id);


ALTER INDEX public.r_dental_protect_item_id_pk OWNER TO postgres;

--
-- Name: r_epi_activity_age_group_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_epi_activity_age_group
    ADD CONSTRAINT r_epi_activity_age_group_id_pk PRIMARY KEY (r_epi_activity_age_group_id);


ALTER INDEX public.r_epi_activity_age_group_id_pk OWNER TO postgres;

--
-- Name: r_epi_activity_group_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_epi_activity_group
    ADD CONSTRAINT r_epi_activity_group_id_pk PRIMARY KEY (r_epi_activity_group_id);


ALTER INDEX public.r_epi_activity_group_id_pk OWNER TO postgres;

--
-- Name: r_epi_activity_subgroup_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_epi_activity_subgroup
    ADD CONSTRAINT r_epi_activity_subgroup_id_pk PRIMARY KEY (r_epi_activity_subgroup_id);


ALTER INDEX public.r_epi_activity_subgroup_id_pk OWNER TO postgres;

--
-- Name: r_epi_age_group_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_epi_age_group
    ADD CONSTRAINT r_epi_age_group_id_pk PRIMARY KEY (r_epi_age_group_id);


ALTER INDEX public.r_epi_age_group_id_pk OWNER TO postgres;

--
-- Name: r_epi_age_group_item_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_epi_age_group_item
    ADD CONSTRAINT r_epi_age_group_item_id_pk PRIMARY KEY (r_epi_age_group_item_id);


ALTER INDEX public.r_epi_age_group_item_id_pk OWNER TO postgres;

--
-- Name: r_epi_age_group_type_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_epi_age_group_type
    ADD CONSTRAINT r_epi_age_group_type_id_pk PRIMARY KEY (r_epi_age_group_type_id);


ALTER INDEX public.r_epi_age_group_type_id_pk OWNER TO postgres;

--
-- Name: r_epi_group_clinic_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_epi_group_clinic
    ADD CONSTRAINT r_epi_group_clinic_id_pk PRIMARY KEY (r_epi_group_clinic_id);


ALTER INDEX public.r_epi_group_clinic_id_pk OWNER TO postgres;

--
-- Name: r_epi_group_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_epi_group
    ADD CONSTRAINT r_epi_group_id_pk PRIMARY KEY (r_epi_group_id);


ALTER INDEX public.r_epi_group_id_pk OWNER TO postgres;

--
-- Name: r_epi_group_item_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_epi_group_item
    ADD CONSTRAINT r_epi_group_item_id_pk PRIMARY KEY (r_epi_group_item_id);


ALTER INDEX public.r_epi_group_item_id_pk OWNER TO postgres;

--
-- Name: r_epi_measles_item_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_epi_measles_item
    ADD CONSTRAINT r_epi_measles_item_id_pk PRIMARY KEY (r_epi_measles_item_id);


ALTER INDEX public.r_epi_measles_item_id_pk OWNER TO postgres;

--
-- Name: r_epi_tt_item_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_epi_tt_item
    ADD CONSTRAINT r_epi_tt_item_id PRIMARY KEY (r_epi_tt_item_id);


ALTER INDEX public.r_epi_tt_item_id OWNER TO postgres;

--
-- Name: r_eye_disease_code_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_eye_disease_code
    ADD CONSTRAINT r_eye_disease_code_id PRIMARY KEY (r_eye_disease_code_id);


ALTER INDEX public.r_eye_disease_code_id OWNER TO postgres;

--
-- Name: r_eye_disease_group_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_eye_group
    ADD CONSTRAINT r_eye_disease_group_id PRIMARY KEY (r_eye_group_id);


ALTER INDEX public.r_eye_disease_group_id OWNER TO postgres;

--
-- Name: r_healthy_group_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_healthy_group
    ADD CONSTRAINT r_healthy_group_id_pk PRIMARY KEY (r_healthy_group_id);


ALTER INDEX public.r_healthy_group_id_pk OWNER TO postgres;

--
-- Name: r_healthy_group_survey_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_healthy_group_survey
    ADD CONSTRAINT r_healthy_group_survey_id_pk PRIMARY KEY (r_healthy_group_survey_id);


ALTER INDEX public.r_healthy_group_survey_id_pk OWNER TO postgres;

--
-- Name: r_healthy_subgroup_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_healthy_subgroup
    ADD CONSTRAINT r_healthy_subgroup_id_pk PRIMARY KEY (r_healthy_subgroup_id);


ALTER INDEX public.r_healthy_subgroup_id_pk OWNER TO postgres;

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
-- Name: r_nutrition_map_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_nutrition_map
    ADD CONSTRAINT r_nutrition_map_id_pk PRIMARY KEY (r_nutrition_map_id);


ALTER INDEX public.r_nutrition_map_id_pk OWNER TO postgres;

--
-- Name: r_nutrition_type_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_nutrition_type
    ADD CONSTRAINT r_nutrition_type_id_pk PRIMARY KEY (r_nutrition_type_id);


ALTER INDEX public.r_nutrition_type_id_pk OWNER TO postgres;

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
-- Name: r_plan_group_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_plan_group
    ADD CONSTRAINT r_plan_group_id PRIMARY KEY (r_plan_group_id);


ALTER INDEX public.r_plan_group_id OWNER TO postgres;

--
-- Name: r_plan_group_map_pttype_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_plan_group_map_pttype
    ADD CONSTRAINT r_plan_group_map_pttype_id_pk PRIMARY KEY (r_plan_group_map_pttype_id);


ALTER INDEX public.r_plan_group_map_pttype_id_pk OWNER TO postgres;

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
-- Name: r_resident_age_group_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_resident_age_group
    ADD CONSTRAINT r_resident_age_group_id_pk PRIMARY KEY (r_resident_age_group_id);


ALTER INDEX public.r_resident_age_group_id_pk OWNER TO postgres;

--
-- Name: r_rp11_disease_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_rp11_disease
    ADD CONSTRAINT r_rp11_disease_id_pk PRIMARY KEY (r_rp11_disease_id);


ALTER INDEX public.r_rp11_disease_id_pk OWNER TO postgres;

--
-- Name: r_rp11_item_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_rp11_item
    ADD CONSTRAINT r_rp11_item_id_pk PRIMARY KEY (r_rp11_item_id);


ALTER INDEX public.r_rp11_item_id_pk OWNER TO postgres;

--
-- Name: r_rp504_disease_code_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_rp504_disease_code
    ADD CONSTRAINT r_rp504_disease_code_id_pk PRIMARY KEY (r_rp504_disease_code_id);


ALTER INDEX public.r_rp504_disease_code_id_pk OWNER TO postgres;

--
-- Name: r_rp505_disease_code_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_rp505_disease_code
    ADD CONSTRAINT r_rp505_disease_code_id_pk PRIMARY KEY (r_rp505_disease_code_id);


ALTER INDEX public.r_rp505_disease_code_id_pk OWNER TO postgres;

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

--
-- Name: r_sql_template_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_sql_template
    ADD CONSTRAINT r_sql_template_id_pk PRIMARY KEY (r_sql_template_id);


ALTER INDEX public.r_sql_template_id_pk OWNER TO postgres;

--
-- Name: r_uc_plan_group_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_uc_plan_group
    ADD CONSTRAINT r_uc_plan_group_id_pk PRIMARY KEY (r_uc_plan_group_id);


ALTER INDEX public.r_uc_plan_group_id_pk OWNER TO postgres;

--
-- Name: r_uc_plan_group_pttype_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY r_uc_plan_group_pttype
    ADD CONSTRAINT r_uc_plan_group_pttype_id_pk PRIMARY KEY (r_uc_plan_group_pttype_id);


ALTER INDEX public.r_uc_plan_group_pttype_id_pk OWNER TO postgres;

--
-- Name: s_report_version_id_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY s_report_version
    ADD CONSTRAINT s_report_version_id_pk PRIMARY KEY (s_report_version_id);


ALTER INDEX public.s_report_version_id_pk OWNER TO postgres;

--
-- Name: aric_antibiotic_item; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX aric_antibiotic_item ON r_aric_antibiotic USING btree (b_item_id);


ALTER INDEX public.aric_antibiotic_item OWNER TO postgres;

--
-- Name: aric_code_begin; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX aric_code_begin ON r_aric_disease_code USING btree (aric_disease_code_begin);


ALTER INDEX public.aric_code_begin OWNER TO postgres;

--
-- Name: aric_code_end; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX aric_code_end ON r_aric_disease_code USING btree (aric_disease_code_end);


ALTER INDEX public.aric_code_end OWNER TO postgres;

--
-- Name: aric_group_id_ind; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX aric_group_id_ind ON r_aric_subgroup USING btree (r_aric_group_id);


ALTER INDEX public.aric_group_id_ind OWNER TO postgres;

--
-- Name: code_begin; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX code_begin ON r_accident_group_code USING btree (accident_group_code_begin);


ALTER INDEX public.code_begin OWNER TO postgres;

--
-- Name: code_end; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX code_end ON r_accident_group_code USING btree (accident_group_code_end);


ALTER INDEX public.code_end OWNER TO postgres;

--
-- Name: eye_code_begin; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX eye_code_begin ON r_eye_disease_code USING btree (eye_disease_code_begin);


ALTER INDEX public.eye_code_begin OWNER TO postgres;

--
-- Name: eye_code_end; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX eye_code_end ON r_eye_disease_code USING btree (eye_disease_code_end);


ALTER INDEX public.eye_code_end OWNER TO postgres;

--
-- Name: eye_disease_group_number; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX eye_disease_group_number ON r_eye_group USING btree (eye_group_number);


ALTER INDEX public.eye_disease_group_number OWNER TO postgres;

--
-- Name: plan_group_id_ide; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX plan_group_id_ide ON r_plan_group_map_pttype USING btree (r_plan_group_id);


ALTER INDEX public.plan_group_id_ide OWNER TO postgres;

--
-- Name: plan_group_map_ind; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX plan_group_map_ind ON r_plan_group_map_pttype USING btree (plan_group_map_pttype_pttype);


ALTER INDEX public.plan_group_map_ind OWNER TO postgres;

--
-- Name: r_eye_group_id_ind; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX r_eye_group_id_ind ON r_eye_disease_code USING btree (r_eye_group_id);


ALTER INDEX public.r_eye_group_id_ind OWNER TO postgres;

--
-- Name: up_plan_group_pttype_ind; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX up_plan_group_pttype_ind ON r_uc_plan_group_pttype USING btree (uc_plan_group_pttype_pttype);


ALTER INDEX public.up_plan_group_pttype_ind OWNER TO postgres;

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

