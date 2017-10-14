--HN	Text	9
--AN	Text	9
--DATE_SERV	Date	8
--CHRGITEM	Text	2
--AMOUNT	Number	7,2
--PERSON_ID	Text	13
--SEQ	Text	15
CREATE TABLE temp_f12_cha (
    hn character varying(255) DEFAULT ''::character varying,
    an character varying(255) DEFAULT ''::character varying,
    date_serv character varying(255) DEFAULT ''::character varying,
    chrgitem character varying(255) DEFAULT ''::character varying,
    amount character varying(255) DEFAULT ''::character varying,
    person_id character varying(255) DEFAULT ''::character varying,
    seq character varying(255) DEFAULT ''::character varying  

);

--HN	Text	9
--AN	Text	9
--DATE_SERV	Date	8
--TOTAL	Number	7,2
--PAID	Number	7,2
--PTTYPE	Text	2
--PERSON_ID	Text	13
--SEQ	Text	15	
CREATE TABLE temp_f12_cht (
    hn character varying(255) DEFAULT ''::character varying,
    an character varying(255) DEFAULT ''::character varying,
    date_serv character varying(255) DEFAULT ''::character varying,
    total character varying(255) DEFAULT ''::character varying,
    paid character varying(255) DEFAULT ''::character varying,
    pttype character varying(255) DEFAULT ''::character varying,
    person_id character varying(255) DEFAULT ''::character varying,
    seq character varying(255) DEFAULT ''::character varying   
);

--HCODE	Text	5
--SEQ	Text	15
--HN	Text	9
--DATEOPD	Date	8
--DRUG_STD	Text	24
--QUANTITY	Number	6
--UNIT	Text	20
--UNIT_PACKING	Text	20
--PRICE	Number	12
--DRUG_CODE		Text	20
--DRUG_NAME	Text	100
CREATE TABLE temp_f12_drug (
    hcode character varying(255) DEFAULT ''::character varying,
    seq character varying(255) DEFAULT ''::character varying,
    hn character varying(255) DEFAULT ''::character varying,
    dateopd character varying(255) DEFAULT ''::character varying,
    drug_std character varying(255) DEFAULT ''::character varying,
    quantity character varying(255) DEFAULT ''::character varying,
    unit character varying(255) DEFAULT ''::character varying,
    unit_pack character varying(255) DEFAULT ''::character varying,
    price character varying(255) DEFAULT ''::character varying,
    drug_code character varying(255) DEFAULT ''::character varying,
    drug_name character varying(255) DEFAULT ''::character varying
);


--HN	Text	9
--INSCL	Text	3
--SUBTYPE	Text	2
--CID	Text	16
--DATEIN	Date	8
--DATEEXP	Date	8
--HOSPMAIN	Text	5
--HOSPSUB	Text	5
CREATE TABLE temp_f12_ins (
    hn character varying(255) DEFAULT ''::character varying,
    inscl character varying(255) DEFAULT ''::character varying,
    subtype character varying(255) DEFAULT ''::character varying,
    cid character varying(255) DEFAULT ''::character varying,
    datein character varying(255) DEFAULT ''::character varying,
    dateexp character varying(255) DEFAULT ''::character varying,
    hospmain character varying(255) DEFAULT ''::character varying,
    hospsub character varying(255) DEFAULT ''::character varying
);

--HN	Text	9
--DATEDX	Date	8
--CLINIC	Text	4
--DIAG	Text	5
--DXTYPE	Text	1		
--DRDX	Text	6
--PERSON_ID	Text	13
--SEQ	Text	15
CREATE TABLE temp_f12_odx (
    hn character varying(255) DEFAULT ''::character varying,
    datedx character varying(255) DEFAULT ''::character varying,
    clinic character varying(255) DEFAULT ''::character varying,
    diag character varying(255) DEFAULT ''::character varying,
    dxtype character varying(255) DEFAULT ''::character varying,
    drdx character varying(255) DEFAULT ''::character varying,
    person_id character varying(255) DEFAULT ''::character varying,
    seq character varying(255) DEFAULT ''::character varying
);

--HN	Text	9
--DATEOPD	Date	8
--CLINIC	Text	4
--OPER	Text	7
--DROP	Text	6
--PERSON_ID	Text	13
--SEQ	Text	15
CREATE TABLE temp_f12_oop (
    hn character varying(255) DEFAULT ''::character varying,
    dateopd character varying(255) DEFAULT ''::character varying,
    clinic character varying(255) DEFAULT ''::character varying,    
    oper character varying(255) DEFAULT ''::character varying,
    "drop" character varying(255) DEFAULT ''::character varying,
    person_id character varying(255) DEFAULT ''::character varying,
    seq character varying(255) DEFAULT ''::character varying    
);


--HN	Text	9
--CLINIC	Text	4
--DATEOPD	Date	8
--PERSON_ID	Text	13
--SEQ	Text	15
--TIMEOPD	Text	4
--UUC	Text	1
CREATE TABLE temp_f12_opd (
    hn character varying(255) DEFAULT ''::character varying,
    clinic character varying(255) DEFAULT ''::character varying,
    dateopd character varying(255) DEFAULT ''::character varying,
    person_id character varying(255) DEFAULT ''::character varying,
    seq character varying(255) DEFAULT ''::character varying,
    timeopd character varying(255) DEFAULT ''::character varying,
    uuc character varying(255) DEFAULT ''::character varying
);

--HN	Text	9
--DATEOPD	Date	8
--CLINIC	Text	4
--REFER	Text	5
--REFERTYPE	Text	1
--SEQ	Text	15
CREATE TABLE temp_f12_orf (
    hn character varying(255) DEFAULT ''::character varying,
    dateopd character varying(255) DEFAULT ''::character varying,
    clinic character varying(255) DEFAULT ''::character varying,
    refer character varying(255) DEFAULT ''::character varying,
    refertype character varying(255) DEFAULT ''::character varying,
    seq character varying(255) DEFAULT ''::character varying
);


--HCODE	Text	5
--HN	Text	9
--CHANGWAT	Text	2
--AMPHUR	Text	2
--DOB	Date	8
--SEX	Text	1
--MARRIAGE	Text	1
--OCCUPA	Text	3
--NATION	Text	3
--PERSON_ID	Text	13
--NAMEPAT	Text	36
--TITLE	Text	30
--FNAME	Text	40
--LNAME	Text	40
--IDTYPE	Text	1
CREATE TABLE temp_f12_pat (
    hcode character varying(255) DEFAULT ''::character varying,
    hn character varying(255) DEFAULT ''::character varying,
    changwat character varying(255) DEFAULT ''::character varying,
    amphur character varying(255) DEFAULT ''::character varying,
    dob character varying(255) DEFAULT ''::character varying,
    sex character varying(255) DEFAULT ''::character varying,
    marriage character varying(255) DEFAULT ''::character varying,
    occupa character varying(255) DEFAULT ''::character varying,
    nation character varying(255) DEFAULT ''::character varying,
    person_id character varying(255) DEFAULT ''::character varying,
    namepat character varying(255) DEFAULT ''::character varying,
    title character varying(255) DEFAULT ''::character varying,
    fname character varying(255) DEFAULT ''::character varying,
    lname character varying(255) DEFAULT ''::character varying,
    idtype character varying(255) DEFAULT ''::character varying
);

 INSERT INTO s_nhso_version("s_version_id", "version_number", "version_description", "version_application_number",  "version_database_number", "version_update_time")
 VALUES('9701000000121', '21', 'Hospital OS, Community Edition', '3.14.1051', '0.9.120110', '2553-01-12,19:00:00');

insert into s_script_update_log values ('nhso','update_nhso_ph21.sql',(select current_date) || ','|| (select  current_time),'update NHSO ph20 -> ph21');

