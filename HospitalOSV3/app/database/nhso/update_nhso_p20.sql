CREATE TABLE tmp_f18_opa (
    opa_id character varying(20),
    pcucode character varying(5),
    cid character varying(13),
    seq character varying(16),
    chrgitem character varying(2),
    service_id character varying(20),
    new_record character varying(1),
    insert_status character varying(5),
    date_serv date,
    notedate date,
    amount numeric(10,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_opa OWNER TO postgres;

--
-- TOC entry 2102 (class 1259 OID 1070942670)
-- Dependencies: 5
-- Name: tmp_f18_service; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_diag (
    diag_id character varying(20),
    pcucode character varying(5),
    cid character varying(13),
    seq character varying(16),
    diagtype character varying(1),
    diagcode character varying(5),
    service_id character varying(20),
    new_record character varying(1),
    insert_status character varying(5),
    date_serv date,
    notedate date,
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_diag OWNER TO postgres;

--
-- TOC entry 2090 (class 1259 OID 1070940871)
-- Dependencies: 5
-- Name: tmp_f18_opa; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_service (
    service_id character varying(20),
    pcucode character varying(5),
    cid character varying(13),
    seq character varying(16),
    locate character varying(1),
    pttype character varying(1),
    intime character varying(1),
    instype character varying(2),
    insid character varying(18),
    main character varying(5),
    referin character varying(1),
    refinhos character varying(5),
    referout character varying(1),
    refouhos character varying(5),
    hn character varying(9),
    time_visit character varying(4),
    relation_status character varying(1),
    is_send character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    date_serv date,
    notedate date,
    price numeric(10,2),
    pay numeric(10,2),
    stmp_id character varying(25)
);


ALTER TABLE public.tmp_f18_service OWNER TO postgres;

--
-- TOC entry 2103 (class 1259 OID 1070943011)
-- Dependencies: 5 
--

CREATE TABLE tmp_f18_anc (
    anc_id character varying(20),
    pcucode character varying(5),
    cid character varying(13),
    seq character varying(16),
    aplace character varying(5),
    gravida character varying(2),
    ancno character varying(1),
    ancres character varying(1),
    service_id character varying(20),
    new_record character varying(1),
    insert_status character varying(5),
    date_serv date,
    notedate date,
    ga numeric(2,0),
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_anc OWNER TO postgres;

--
-- TOC entry 2067 (class 1259 OID 1070938981)
-- Dependencies: 5  
-- TOC entry 2078 (class 1259 OID 1070939707)
-- Dependencies: 5
-- Name: tmp_f18_drug; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_drug (
    drug_id character varying(20),
    pcucode character varying(5),
    cid character varying(13),
    seq character varying(16),
    did character varying(40),
    unit character varying(20),
    unit_packing character varying(20),
    service_id character varying(20),
    new_record character varying(1),
    insert_status character varying(5),
    date_serv date,
    notedate date,
    amount numeric(5,0),
    drugpric numeric(10,2),
    drugcost numeric(10,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_drug OWNER TO postgres;

--
-- TOC entry 2080 (class 1259 OID 1070940813)
-- Dependencies: 5
-- Name: tmp_f18_epi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_epi (
    epi_id character varying(20),
    pcucode character varying(5),
    cid character varying(13),
    seq character varying(16),
    vcctype character varying(3),
    vccplace character varying(5),
    service_id character varying(20),
    new_record character varying(1),
    insert_status character varying(5),
    date_serv date,
    notedate date,
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_epi OWNER TO postgres;

--
-- TOC entry 2081 (class 1259 OID 1070940820)
-- Dependencies: 5 
-- TOC entry 2082 (class 1259 OID 1070940825)
-- Dependencies: 5
-- Name: tmp_f18_fp; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_fp (
    fp_id character varying(20),
    pcucode character varying(5),
    cid character varying(13),
    seq character varying(16),
    fptype character varying(1),
    did character varying(40),
    fpplace character varying(5),
    service_id character varying(20),
    new_record character varying(1),
    insert_status character varying(5),
    date_serv date,
    notedate date,
    amount numeric(3,0),
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_fp OWNER TO postgres;

--
-- TOC entry 2083 (class 1259 OID 1070940834)
-- Dependencies: 5 
--
-- TOC entry 2088 (class 1259 OID 1070940859)
-- Dependencies: 5
-- Name: tmp_f18_nutri; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_nutri (
    nutri_id character varying(20),
    pcucode character varying(5),
    cid character varying(13),
    seq character varying(16),
    agemonth character varying(5),
    nlevel character varying(1),
    service_id character varying(20),
    new_record character varying(1),
    insert_status character varying(5),
    date_serv date,
    notedate date,
    weight numeric(6,0),
    height numeric(5,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_nutri OWNER TO postgres;

--
-- TOC entry 2089 (class 1259 OID 1070940866)
-- Dependencies: 5 
-- TOC entry 2094 (class 1259 OID 1070942289)
-- Dependencies: 5
-- Name: tmp_f18_pp; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_pp (
    pp_id character varying(20),
    pcucode character varying(5),
    cid character varying(13),
    m_cid character varying(13),
    gravida character varying(2),
    bplace character varying(1),
    bhosp character varying(5),
    btype character varying(1),
    bdoctor character varying(1),
    asphyxia character varying(1),
    vitk character varying(1),
    bcres character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    bdate date,
    bcare1 date,
    bcare2 date,
    bcare3 date,
    update_date date,
    notedate date,
    date_serv date,
    bweigth numeric(4,0),
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_pp OWNER TO postgres;

--
-- TOC entry 2095 (class 1259 OID 1070942294)
-- Dependencies: 5 
--
-- TOC entry 2118 (class 1259 OID 1071348394)
-- Dependencies: 5
-- Name: tmp_f12_drug; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f12_drug (
    hcode character varying(5),
    seq character varying(15),
    hn character varying(9),
    drug_code character varying(20),
    drug_name character varying(100),
    office_id character varying(5),
    new_record character varying(1),
    drug_std character varying(24),
    unit character varying(20),
    unit_pack character varying(20),
    insert_status character varying(25),
    dateopd date,
    price numeric(12,0),
    quantity numeric(6,0),
    stmp_id character varying
);


ALTER TABLE public.tmp_f12_drug OWNER TO postgres;

--
-- TOC entry 2119 (class 1259 OID 1071352546)
-- Dependencies: 5 
 
--
-- TOC entry 2108 (class 1259 OID 1071341175)
-- Dependencies: 5
-- Name: tmp_f12_ins; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f12_ins (
    hn character varying(9),
    inscl character varying(3),
    subtype character varying(2),
    cid character varying(16),
    hospmain character varying(5),
    hospsub character varying(5),
    office_id character varying(5),
    new_record character varying(1),
    is_send character varying(1),
    insert_status character varying(25),
    datein date,
    dateexp date,
    stmp_id character varying
);


ALTER TABLE public.tmp_f12_ins OWNER TO postgres;

--
-- TOC entry 2109 (class 1259 OID 1071341180)
-- Dependencies: 5 
--
-- TOC entry 2110 (class 1259 OID 1071341185)
-- Dependencies: 5
-- Name: tmp_f12_oop; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f12_oop (
    hn character varying(9),
    clinic character varying(4),
    oper character varying(7),
    person_id character varying(13),
    seq character varying(15),
    office_id character varying(5),
    new_record character varying(1),
    "drop" character varying(6),
    insert_status character varying(25),
    dateopd date,
    stmp_id character varying
);


ALTER TABLE public.tmp_f12_oop OWNER TO postgres;

--
-- TOC entry 2111 (class 1259 OID 1071341408)
-- Dependencies: 5 
--
-- TOC entry 2116 (class 1259 OID 1071348384)
-- Dependencies: 5
-- Name: tmp_f12_orf; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f12_orf (
    hn character varying(9),
    clinic character varying(4),
    refer character varying(5),
    refertype character varying(1),
    seq character varying(15),
    office_id character varying(5),
    new_record character varying(1),
    is_send character varying(1),
    insert_status character varying(25),
    dateopd date,
    stmp_id character varying
);


ALTER TABLE public.tmp_f12_orf OWNER TO postgres;

--
-- TOC entry 2117 (class 1259 OID 1071348389)
-- Dependencies: 5 
--
-- TOC entry 2122 (class 1259 OID 1071354575)
-- Dependencies: 5
-- Name: tmp_f12_pat; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f12_pat (
    hcode character varying(5),
    hn character varying(9),
    changwat character varying(2),
    amphur character varying(2),
    sex character varying(1),
    marriage character varying(1),
    occupa character varying(3),
    nation character varying(3),
    person_id character varying(13),
    namepat character varying(36),
    title character varying(30),
    fname character varying(40),
    lname character varying(40),
    idtype character varying(1),
    office_id character varying(5),
    new_record character varying(1),
    is_send character varying(1),
    insert_status character varying(25),
    dob date,
    stmp_id character varying
);


ALTER TABLE public.tmp_f12_pat OWNER TO postgres;

--
-- TOC entry 2123 (class 1259 OID 1071354580)
-- Dependencies: 5 

--
-- TOC entry 2068 (class 1259 OID 1070938986)
-- Dependencies: 5
-- Name: tmp_f18_appoint; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_appoint (
    pcucode character varying(5),
    cid character varying(13),
    seq character varying(16),
    aptype character varying(3),
    apdiag character varying(5),
    is_send character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    date_serv date,
    apdate date,
    update_date date,
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_appoint OWNER TO postgres;

--
-- TOC entry 2069 (class 1259 OID 1070938991)
-- Dependencies: 5 

--
-- TOC entry 2070 (class 1259 OID 1070938996)
-- Dependencies: 5
-- Name: tmp_f18_card; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_card (
    pcucode character varying(5),
    cid character varying(13),
    pid character varying(13),
    instype character varying(2),
    insid character varying(18),
    main character varying(5),
    sub character varying(5),
    is_send character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    update_date date,
    start_date date,
    expir_date date,
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_card OWNER TO postgres;

--
-- TOC entry 2071 (class 1259 OID 1070939001)
-- Dependencies: 5 

--
-- TOC entry 2072 (class 1259 OID 1070939029)
-- Dependencies: 5
-- Name: tmp_f18_chronic; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_chronic (
    chronic_id character varying(20),
    pcucode character varying(5),
    cid character varying(13),
    pid character varying(13),
    chronic character varying(6),
    typedis character varying(1),
    is_send character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    datedx date,
    datedis date,
    date_serv date,
    update_date date,
    notedate date,
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_chronic OWNER TO postgres;

--
-- TOC entry 2073 (class 1259 OID 1070939034)
-- Dependencies: 5 

--
-- TOC entry 2074 (class 1259 OID 1070939073)
-- Dependencies: 5
-- Name: tmp_f18_death; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_death (
    pcucode character varying(5),
    cid character varying(13),
    pid character varying(13),
    cdeath_a character varying(6),
    cdeath_b character varying(6),
    cdeath_c character varying(6),
    cdeath_d character varying(6),
    odisease character varying(6),
    cdeath character varying(6),
    pdeath character varying(1),
    is_send character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    ddeath date,
    update_date date,
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_death OWNER TO postgres;

--
-- TOC entry 2075 (class 1259 OID 1070939078)
-- Dependencies: 5 

--
-- TOC entry 2084 (class 1259 OID 1070940839)
-- Dependencies: 5
-- Name: tmp_f18_home; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_home (
    home_id character varying(20),
    pcucode character varying(5),
    hid character varying(5),
    house_id character varying(16),
    house character varying(30),
    village character varying(2),
    road character varying(25),
    tambon character varying(2),
    ampur character varying(2),
    changwat character varying(2),
    nfamily numeric(1,0),
    locatype character varying(1),
    vhvid character varying(6),
    headid character varying(6),
    toilet character varying(1),
    water character varying(1),
    wattype character varying(1),
    garbage character varying(1),
    hcare character varying(1),
    durable character varying(1),
    clean character varying(1),
    ventila character varying(1),
    light character varying(1),
    watertm character varying(1),
    mfood character varying(1),
    bctrl character varying(1),
    actrl character varying(1),
    is_send character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    update_date date,
    notedate date,
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_home OWNER TO postgres;

--
-- TOC entry 2085 (class 1259 OID 1070940844)
-- Dependencies: 5 
--
-- TOC entry 2086 (class 1259 OID 1070940849)
-- Dependencies: 5
-- Name: tmp_f18_mch; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_mch (
    mch_id character varying(20),
    pcucode character varying(5),
    cid character varying(13),
    gravida character varying(2),
    vdrl_rs character varying(1),
    hb_rs character varying(1),
    hiv_rs character varying(1),
    thalass character varying(1),
    dental character varying(1),
    tartar character varying(1),
    guminf character varying(1),
    bresult character varying(6),
    bplace character varying(1),
    bhosp character varying(5),
    btype character varying(1),
    bdoctor character varying(1),
    ppres character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    lmp date,
    edc date,
    datehct date,
    bdate date,
    ppcare1 date,
    ppcare2 date,
    ppcare3 date,
    update_date date,
    notedate date,
    date_serv date,
    hct_rs numeric(2,0),
    tcaries numeric(2,0),
    lborn numeric(1,0),
    sborn numeric(1,0),
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_mch OWNER TO postgres;

--
-- TOC entry 2087 (class 1259 OID 1070940854)
-- Dependencies: 5 

--
-- TOC entry 2092 (class 1259 OID 1070941960)
-- Dependencies: 5
-- Name: tmp_f18_person; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_person (
    person_id character varying(20),
    pcucode character varying(5),
    cid character varying(13),
    pid character varying(13),
    hid character varying(14),
    prename character varying(3),
    name character varying(50),
    lname character varying(50),
    hn character varying(10),
    sex character varying(1),
    house character varying(150),
    village character varying(2),
    tambon character varying(2),
    ampur character varying(2),
    changwat character varying(2),
    mstatus character varying(1),
    occupa character varying(3),
    race character varying(3),
    nation character varying(3),
    religion character varying(1),
    educate character varying(1),
    fstatus character varying(1),
    father character varying(13),
    mother character varying(13),
    couple character varying(13),
    dischar character varying(1),
    bgroup character varying(1),
    labor character varying(1),
    is_send character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    typearea character varying(1),
    birth date,
    movein date,
    ddisch date,
    update_date date,
    notedate date,
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_person OWNER TO postgres;

--
-- TOC entry 2093 (class 1259 OID 1070941965)
-- Dependencies: 5 

--
-- TOC entry 2096 (class 1259 OID 1070942299)
-- Dependencies: 5
-- Name: tmp_f18_proced; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_proced (
    proced_id character varying(20),
    pcucode character varying(5),
    cid character varying(13),
    seq character varying(16),
    proced character varying(7),
    service_id character varying(20),
    new_record character varying(1),
    insert_status character varying(5),
    date_serv date,
    notedate date,
    servpric numeric(10,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_proced OWNER TO postgres;

--
-- TOC entry 2097 (class 1259 OID 1070942624)
-- Dependencies: 5 
--
-- TOC entry 2098 (class 1259 OID 1070942629)
-- Dependencies: 5
-- Name: tmp_f18_surveil; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_surveil (
    surveil_id character varying(20),
    pcucode character varying(5),
    cid character varying(13),
    pid character varying(13),
    seq character varying(16),
    diagcode character varying(6),
    code506 character varying(2),
    illhouse character varying(150),
    illvill character varying(2),
    illtamb character varying(2),
    illampu character varying(2),
    illchan character varying(2),
    ptstat character varying(1),
    complica character varying(3),
    organism character varying(3),
    service_id character varying(20),
    new_record character varying(1),
    insert_status character varying(5),
    date_serv date,
    illdate date,
    date_death date,
    notedate date,
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_surveil OWNER TO postgres;

--
-- TOC entry 2099 (class 1259 OID 1070942634)
-- Dependencies: 5 

--
-- TOC entry 2100 (class 1259 OID 1070942653)
-- Dependencies: 5
-- Name: tmp_f18_woman; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f18_woman (
    woman_id character varying(20),
    pcucode character varying(5),
    cid character varying(13),
    fptype character varying(1),
    nofp character varying(1), 
    is_send character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    update_date date,
    notedate date,
    date_serv date,
    numson numeric(2,0),
    stmp_id character varying
);


ALTER TABLE public.tmp_f18_woman OWNER TO postgres;

--
-- TOC entry 2101 (class 1259 OID 1070942658)
-- Dependencies: 5 
--
-- TOC entry 2029 (class 1259 OID 1069500595)
-- Dependencies: 5
-- Name: tmp_pp_anc; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_anc (
    anc_id character varying(20),
    t_service_id character varying(20),
    bodycheck character varying(1),
    pregnancy character varying(1),
    strait character varying(1),
    tt character varying(1),
    tt_no character varying(1),
    uprosuga character varying(1),
    of_mcv character varying(1),
    dcip_escreen character varying(1),
    cbc character varying(1),
    hct character varying(1),
    vdrl character varying(1),
    hbsag character varying(1),
    hiv character varying(1),
    bloodgroup character varying(1),
    rhgroup character varying(1),
    other_lab character varying(255),
    thalas_id character varying(20),
    ultrasound character varying(1),
    other_xray character varying(255),
    dent_id character varying(20),
    hcode_anc character varying(5),
    i2 character varying(1),
    vitb9 character varying(1),
    feso4 character varying(1),
    consult_person character varying(1),
    consult_checkblood character varying(1),
    checkbloodhiv character varying(1),
    checkbloodthalas character varying(1),
    consult_other character varying(255),
    consult_group character varying(1),
    insert_status character varying(5),
    usuga character varying(1),
    upro character varying(1),
    new_record character varying(1),
    totalpay numeric(9,2),
    pregnancy_number numeric(2,0),
    anc_number numeric(2,0),
    gravida_week numeric(2,0),
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_anc OWNER TO postgres;

--
-- TOC entry 2030 (class 1259 OID 1069500600)
-- Dependencies: 5 
-- TOC entry 2031 (class 1259 OID 1069500605)
-- Dependencies: 5
-- Name: tmp_pp_cancer; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_cancer (
    breast_id character varying(20),
    t_service_id character varying(20),
    resultbreast character varying(1),
    result character varying(255),
    resultpapsmear character varying(1),
    resultbreast_inform character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    totalpay numeric(9,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_cancer OWNER TO postgres;

--
-- TOC entry 2032 (class 1259 OID 1069500611)
-- Dependencies: 5 
--
-- TOC entry 2033 (class 1259 OID 1069500616)
-- Dependencies: 5
-- Name: tmp_pp_consult; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_consult (
    consult_id character varying(20),
    t_service_id character varying(20),
    cronic character varying(1),
    tension character varying(1),
    drug character varying(1),
    counseling character varying(1),
    counseling_detail character varying(255),
    counsel_detail character varying(255),
    new_record character varying(1),
    insert_status character varying(5),
    totalpay numeric(9,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_consult OWNER TO postgres;

--
-- TOC entry 2034 (class 1259 OID 1069500699)
-- Dependencies: 5 
--
-- TOC entry 2035 (class 1259 OID 1069500771)
-- Dependencies: 5
-- Name: tmp_pp_dent; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_dent (
    dent_id character varying(20),
    t_service_id character varying(20),
    consult_dent character varying(1),
    othere_dent character varying(255),
    tooth_impaction character varying(1),
    residual_ridge_gum character varying(1),
    dentalcaries character varying(1),
    dentalcaries_no character varying(100),
    gum_normal character varying(1),
    calculus character varying(1),
    periodontall character varying(1),
    gun_other character varying(255),
    tooth_root_scrape character varying(1),
    otherdent character varying(255),
    service_tartar_scrape character varying(1),
    certificate_id_dentist character varying(10),
    new_record character varying(1),
    insert_status character varying(5),
    anc character varying(1),
    crown numeric(2,0),
    pull_tooth numeric(2,0),
    rct numeric(2,0),
    wte numeric(2,0),
    denture numeric(2,0),
    calculus_scrape numeric(2,0),
    totalpay numeric(9,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_dent OWNER TO postgres;

--
-- TOC entry 2036 (class 1259 OID 1069500778)
-- Dependencies: 5 
 

--
-- TOC entry 2037 (class 1259 OID 1069500788)
-- Dependencies: 5
-- Name: tmp_pp_dentchild; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_dentchild (
    dentchild_id character varying(20),
    t_service_id character varying(20),
    consult_dent character varying(1),
    check_sealant character varying(1),
    sealant character varying(24),
    fluolide character varying(1),
    periodontall character varying(1),
    dentclean character varying(1),
    certificate_id_dentist character varying(10),
    new_record character varying(1),
    insert_status character varying(5),
    goal_sealant numeric(2,0),
    dent_no numeric(2,0),
    permant numeric(2,0),
    permantcar numeric(2,0),
    milktooth numeric(2,0),
    milktoothcar numeric(2,0),
    totalpay numeric(9,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_dentchild OWNER TO postgres;

--
-- TOC entry 2038 (class 1259 OID 1069500806)
-- Dependencies: 5 

--
-- TOC entry 2039 (class 1259 OID 1069500811)
-- Dependencies: 5
-- Name: tmp_pp_depression; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_depression (
    depres_id character varying(20),
    t_service_id character varying(20),
    check2q character varying(1),
    check9q character varying(1),
    check8q character varying(1),
    consult_depres character varying(1),
    forrow9q1 character varying(1),
    forrow9q2 character varying(1),
    forrow9q3 character varying(1),
    forrow9q4 character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    totalpay numeric(9,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_depression OWNER TO postgres;

--
-- TOC entry 2040 (class 1259 OID 1069500816)
-- Dependencies: 5 

--
-- TOC entry 2041 (class 1259 OID 1069500821)
-- Dependencies: 5
-- Name: tmp_pp_dmht; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_dmht (
    dmht_id character varying(20),
    t_service_id character varying(20),
    diabetesonly character varying(1),
    diabetescomplication character varying(1),
    diabetesicd10 character varying(5),
    diabetesfood character varying(1),
    diabetesexercise character varying(1),
    diabetesfootcheck character varying(1),
    diabetesdrug character varying(1),
    diabetesknowcomplication character varying(1),
    diabetesother character varying(1),
    diabetesotherstr character varying(255),
    diabetesdtx character varying(5),
    diabetesfbs character varying(5),
    hbonly character varying(1),
    hbcomplication character varying(1),
    hbicd10 character varying(5),
    hbfood character varying(1),
    hbexercise character varying(1),
    hbdrinksmoke character varying(1),
    hbdrug character varying(1),
    hbstrain character varying(1),
    hbother character varying(1),
    hbotherstr character varying(255),
    new_record character varying(1),
    insert_status character varying(5),
    totalpay numeric(9,2),
    hbsystolic numeric(5,2),
    hbdiastolic numeric(5,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_dmht OWNER TO postgres;

--
-- TOC entry 2042 (class 1259 OID 1069500930)
-- Dependencies: 5 

--
-- TOC entry 2043 (class 1259 OID 1069501062)
-- Dependencies: 5
-- Name: tmp_pp_epi; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_epi (
    epi_id character varying(20),
    t_service_id character varying(20),
    bcg character varying(1),
    hb1 character varying(1),
    opv1 character varying(1),
    dtp1 character varying(1),
    hb2 character varying(1),
    opv2 character varying(1),
    dtp2 character varying(1),
    opv3 character varying(1),
    dtp3 character varying(1),
    hb3 character varying(1),
    m character varying(1),
    mmr character varying(1),
    opv4 character varying(1),
    dtp4 character varying(1),
    je1 character varying(1),
    je2 character varying(1),
    je3 character varying(1),
    opv5 character varying(1),
    dtp5 character varying(1),
    dtphb2 character varying(1),
    dtphb4 character varying(1),
    dtphb6 character varying(1),
    dtphbopv2 character varying(1),
    dtphbopv4 character varying(1),
    dtphbopv6 character varying(1),
    mmr2 character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    totalpay numeric(9,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_epi OWNER TO postgres;

--
-- TOC entry 2044 (class 1259 OID 1069501069)
-- Dependencies: 5 

--
-- TOC entry 2045 (class 1259 OID 1069501074)
-- Dependencies: 5
-- Name: tmp_pp_fp; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_fp (
    fp_id character varying(20),
    t_service_id character varying(20),
    drug character varying(1),
    drug_ijt character varying(1),
    hemp_women character varying(1),
    hemp_men character varying(1),
    hoop character varying(1),
    drug_burty character varying(1),
    condom character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    drug_dispend numeric(1,0),
    condom_dispend numeric(2,0),
    totalpay numeric(9,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_fp OWNER TO postgres;

--
-- TOC entry 2046 (class 1259 OID 1069501083)
-- Dependencies: 5 

--
-- TOC entry 2047 (class 1259 OID 1069501088)
-- Dependencies: 5
-- Name: tmp_pp_grow; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_grow (
    grow_id character varying(20),
    t_service_id character varying(20),
    weight_growup character varying(1),
    height_growup character varying(1),
    check_body character varying(1),
    assess_grow character varying(1),
    assess_comment character varying(255),
    thyroid character varying(1),
    takecare character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    growth_year numeric(3,0),
    growth_month numeric(2,0),
    growth_weight numeric(5,2),
    growth_height numeric(5,2),
    totalpay numeric(9,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_grow OWNER TO postgres;

--
-- TOC entry 2048 (class 1259 OID 1069501095)
-- Dependencies: 5 
--
-- TOC entry 2049 (class 1259 OID 1069501100)
-- Dependencies: 5
-- Name: tmp_pp_hhc; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_hhc (
    hhc_id character varying(20),
    t_service_id character varying(20),
    heightbloodpressure character varying(1),
    diabetes character varying(1),
    heartdisease character varying(1),
    stroke character varying(1),
    copd character varying(1),
    asthma character varying(1),
    tuberculosis character varying(1),
    renalfail character varying(1),
    aids character varying(1),
    patientfinal character varying(1),
    childandmom character varying(1),
    forrowchild character varying(1),
    otherdisease character varying(1),
    disease_detail character varying(255),
    suggest character varying(1),
    drug character varying(1),
    skill character varying(1),
    physio character varying(1),
    wound character varying(1),
    ng character varying(1),
    urine_tube character varying(1),
    neck_pierce character varying(1),
    renalcare character varying(1),
    other_activity character varying(1),
    other_detail character varying(255),
    person_service character varying(1),
    other_service character varying(255),
    pid_service character varying(13),
    certno character varying(13),
    channel character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    totalpay numeric(9,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_hhc OWNER TO postgres;

--
-- TOC entry 2050 (class 1259 OID 1069501105)
-- Dependencies: 5 
 

--
-- TOC entry 2051 (class 1259 OID 1069501110)
-- Dependencies: 5
-- Name: tmp_pp_hv; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_hv (
    hv_id character varying(20),
    t_service_id character varying(20),
    consult_pp character varying(1),
    consult_fp character varying(1),
    consult_anc character varying(1),
    consult_npc character varying(1),
    consult_mum character varying(1),
    consult_epi character varying(1),
    consult_grow character varying(1),
    consult_old character varying(1),
    consult_cripple character varying(1),
    consult_other character varying(1),
    person_servey character varying(1),
    person_other character varying(255),
    followup character varying(1),
    followup_other character varying(255),
    new_record character varying(1),
    insert_status character varying(5),
    totalpay numeric(9,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_hv OWNER TO postgres;

--
-- TOC entry 2052 (class 1259 OID 1069501115)
-- Dependencies: 5 
 
--
-- TOC entry 2053 (class 1259 OID 1069501120)
-- Dependencies: 5
-- Name: tmp_pp_papsmear; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_papsmear (
    pap_id character varying(20),
    t_service_id character varying(20),
    name_check character varying(255),
    name_record character varying(255),
    verginal character varying(1),
    cervical character varying(1),
    endocervical character varying(1),
    endometrial character varying(1),
    otherthink character varying(1),
    other_comment character varying(255),
    para character varying(4),
    "last" character varying(4),
    previoustreatment character varying(100),
    clinicalfindings character varying(100),
    clinicaldx character varying(100),
    lastpapin5year character varying(4),
    satisfacid character varying(1),
    specimen character varying(1),
    presence character varying(1),
    absence character varying(1),
    smear character varying(1),
    patial character varying(1),
    scant character varying(1),
    othercyto character varying(1),
    other_comment_cyto character varying(255),
    unsat character varying(1),
    specimen_regict character varying(1),
    specimen_processed character varying(1),
    general_categerization character varying(1),
    navigat character varying(1),
    trichspp character varying(1),
    candidaspp character varying(1),
    bactvagin character varying(1),
    actinomy character varying(1),
    hsv character varying(1),
    otherorganism character varying(1),
    inflam character varying(1),
    radiation character varying(1),
    iud character varying(1),
    glandr character varying(1),
    atrophy character varying(1),
    epithelial character varying(1),
    ofunsign character varying(1),
    cehsil character varying(1),
    cehhpv character varying(1),
    cin_i character varying(1),
    cin_ii character varying(1),
    cin_iii character varying(1),
    featurssus character varying(1),
    sil character varying(1),
    commt character varying(255),
    squamcell character varying(1),
    atyendo character varying(1),
    atyendom character varying(1),
    atynotoths character varying(1),
    afavorneoc character varying(1),
    afaneome character varying(1),
    afaneopla character varying(1),
    endocervic character varying(1),
    aenemaence character varying(1),
    aenemaenme character varying(1),
    aenmanotsp character varying(1),
    otrmal character varying(1),
    adqcarcino character varying(1),
    pdifscinoma character varying(1),
    smallundif character varying(1),
    cinoma character varying(1),
    cimors character varying(1),
    malignant character varying(1),
    malignantl character varying(1),
    extrcarcin character varying(1),
    resultpapsmear_infrom character varying(1),
    followup character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    date_record date,
    lmp date,
    totalpay numeric(9,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_papsmear OWNER TO postgres;

--
-- TOC entry 2054 (class 1259 OID 1069501125)
-- Dependencies: 5 
 

--
-- TOC entry 2055 (class 1259 OID 1069501130)
-- Dependencies: 5
-- Name: tmp_pp_pnc; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_pnc (
    pnc_id character varying(20),
    t_service_id character varying(20),
    checkbody character varying(1),
    fp character varying(1),
    mum character varying(1),
    papsmear character varying(1),
    papid character varying(20),
    referpap character varying(1),
    breast character varying(1),
    breastid character varying(20),
    new_record character varying(1),
    insert_status character varying(5),
    son_date date,
    pregnancy_number numeric(2,0),
    totalpay numeric(9,2),
    pnc numeric(1,0),
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_pnc OWNER TO postgres;

--
-- TOC entry 2056 (class 1259 OID 1069501135)
-- Dependencies: 5 
 

--
-- TOC entry 2057 (class 1259 OID 1069501140)
-- Dependencies: 5
-- Name: tmp_pp_pp; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_pp (
    pp_id character varying(20),
    t_service_id character varying(20),
    cordb character varying(1),
    babyfoot character varying(1),
    serum character varying(1),
    forowup character varying(1),
    new_record character varying(1),
    insert_status character varying(5),
    son_date date,
    dateforow date,
    totalpay numeric(9,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_pp OWNER TO postgres;

--
-- TOC entry 2058 (class 1259 OID 1069501145)
-- Dependencies: 5 
--
-- TOC entry 2061 (class 1259 OID 1069501160)
-- Dependencies: 5
-- Name: tmp_pp_service; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_service (
    t_service_id character varying(20),
    pcucode character varying(5),
    pid character varying(13),
    addno character varying(10),
    moo character varying(2),
    mooname character varying(50),
    soi character varying(50),
    road character varying(50),
    tambon character varying(2),
    amphur character varying(2),
    province character varying(4),
    phone_home character varying(10),
    phone_work character varying(10),
    hand_phone character varying(10),
    responsible character varying(1),
    pttype character varying(1),
    recompen character varying(1),
    basetype character varying(255),
    is_relation character varying(255),
    status_insert character varying(3),
    hn character varying(15),
    upload_date character varying(255),
    pp_name character varying(255),
    community character varying(50),
    new_record character varying(1),
    insert_status character varying(5),
    relation_status character varying(1),
    is_send character varying(1),
    date_serv date,
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_service OWNER TO postgres;
 
--
-- TOC entry 2059 (class 1259 OID 1069501150)
-- Dependencies: 5
-- Name: tmp_pp_thalassemia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_pp_thalassemia (
    thalas_id character varying(20),
    t_service_id character varying(20),
    hbtyping character varying(1),
    alphathalassemia character varying(1),
    pnd character varying(1),
    mutation character varying(1),
    pid_hb character varying(13),
    pid_type character varying(1),
    of_mcv_hb character varying(1),
    dcip_escreen_hb character varying(1),
    hbtyping_hb character varying(1),
    alphathalassemia_hb character varying(1),
    pnd_hb character varying(255),
    mutation_hb character varying(1),
    pid_type_note character varying(255),
    new_record character varying(1),
    insert_status character varying(5),
    totalpay_hb numeric(9,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_pp_thalassemia OWNER TO postgres;

--
-- TOC entry 2060 (class 1259 OID 1069501155)
-- Dependencies: 5 

CREATE TABLE tmp_f12_cha (
    hn character varying(9),
    an character varying(9),
    chrgitem character varying(2),
    person_id character varying(13),
    seq character varying(15),
    office_id character varying(5),
    new_record character varying(1),
    insert_status character varying(25),
    date_serv date,
    amount numeric(9,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_f12_cha OWNER TO postgres;

--
-- TOC entry 2114 (class 1259 OID 1071346832)
-- Dependencies: 5
-- Name: tmp_f12_cht; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tmp_f12_cht (
    hn character varying(9),
    an character varying(9),
    pttype character varying(2),
    person_id character varying(13),
    seq character varying(15),
    office_id character varying(5),
    new_record character varying(1),
    insert_status character varying(25),
    date_serv date,
    total numeric(9,2),
    paid numeric(9,2),
    stmp_id character varying
);


ALTER TABLE public.tmp_f12_cht OWNER TO postgres;

--
-- TOC entry 2106 (class 1259 OID 1071338897)
-- Dependencies: 5
-- Name: tmp_f12_odx; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--


CREATE TABLE tmp_f12_odx (
    hn character varying(9),
    clinic character varying(4),
    diag character varying(5),
    dxtype character varying(1),
    drdx character varying(6),
    person_id character varying(13),
    seq character varying(15),
    office_id character varying(5),
    new_record character varying(1),
    insert_status character varying(25),
    datedx date,
    stmp_id character varying
);


ALTER TABLE public.tmp_f12_odx OWNER TO postgres;

--
-- TOC entry 2120 (class 1259 OID 1071353023)
-- Dependencies: 5
-- Name: tmp_f12_opd; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--


CREATE TABLE tmp_f12_opd (
    hn character varying(9),
    clinic character varying(4),
    person_id character varying(13),
    seq character varying(15),
    timeopd character varying(4),
    uuc character varying(1),
    office_id character varying(5),
    new_record character varying(1),
    insert_status character varying(25),
    relation_status character varying(25),
    is_send character varying(1),
    dateopd date,
    stmp_id character varying
);


CREATE TABLE axisnhso2010_sendlog (
    log_id character varying(255) NOT NULL,
    username character varying(255),
    filename character varying(255),
    date_upload character varying(255),
    time_upload character varying(255),
    status character varying(255)
);

ALTER TABLE public.tmp_f12_opd OWNER TO postgres;

--
-- TOC entry 2121 (class 1259 OID 1071354570)
-- Dependencies: 5 
----henbe update
alter table tmp_f18_anc add active varchar(255) default '1' ;
alter table tmp_f18_card add active varchar(255) default '1' ;
alter table tmp_f18_chronic add active varchar(255) default '1' ;
alter table tmp_f18_death add active varchar(255) default '1' ;
alter table tmp_f18_diag add active varchar(255) default '1' ;
alter table tmp_f18_drug add active varchar(255) default '1' ;
alter table tmp_f18_epi add active varchar(255) default '1' ;
alter table tmp_f18_fp add active varchar(255) default '1' ;
alter table tmp_f18_home add active varchar(255) default '1' ;
alter table tmp_f18_mch add active varchar(255) default '1' ;
alter table tmp_f18_nutri add active varchar(255) default '1' ;
alter table tmp_f18_opa add active varchar(255) default '1' ;
alter table tmp_f18_person add active varchar(255) default '1' ;
alter table tmp_f18_pp add active varchar(255) default '1' ;
alter table tmp_f18_proced add active varchar(255) default '1' ;
alter table tmp_f18_surveil add active varchar(255) default '1' ;
alter table tmp_f18_woman add active varchar(255) default '1' ;
alter table tmp_f18_service add active varchar(255) default '1' ; 
alter table tmp_f12_drug add unit_packing varchar(255) default '1' ; 

 INSERT INTO s_nhso_version("s_version_id", "version_number", "version_description", "version_application_number",  "version_database_number", "version_update_time")
 VALUES('9701000000120', '20', 'Hospital OS, Community Edition', '3.14.1051', '0.8.110110', '2553-01-11,12:00:00');

insert into s_script_update_log values ('nhso','update_nhso_ph20.sql',(select current_date) || ','|| (select  current_time),'update NHSO ph19 -> ph20');

