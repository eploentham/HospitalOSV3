drop table b_health_epi_group;
drop table b_health_epi_item;
drop table b_health_epi_item_drug_setup;
drop table f_health_anc_section;
drop table f_health_family_planing;
drop table f_health_family_planing_method;
drop table f_health_home_charactor;
drop table f_health_home_community_charac;
drop table f_health_home_garbage_method;
drop table f_health_home_water_type;
drop table f_health_postpartum_birth_method;
drop table f_health_postpartum_birth_place;
drop table f_health_postpartum_episiotomy_type;
drop table f_health_postpartum_givebirth_result;
drop table f_health_postpartum_status_result;
drop table f_health_pregnancy_birth_doctor_type;
drop table f_health_pregnancy_birth_high_risk;
drop table f_health_pregnancy_conduct;
drop table f_health_pregnancy_posture_baby_status;
drop table f_health_pregnancy_pregnant_level;
drop table f_health_pregnancy_uterus_level;
drop table t_health_anc;
drop table t_health_anc_detail;
drop table t_health_delivery;
drop table t_health_epi;
drop table t_health_epi_detail;
drop table t_health_family_planing;
drop table t_health_home;
drop table t_health_postpartum;
drop table t_health_pp;
drop table t_health_pregnancy;


CREATE TABLE b_health_agr_code (
    b_health_agr_code_id character varying(255) NOT NULL,
    agr_code_number character varying(255),
    agr_code_description character varying(255),
    agr_code_active character varying(255)
);
INSERT INTO b_health_agr_code VALUES ('7640864859004', '01', 'ชมรมสร้างเสริมสุขภาพ', '1');
INSERT INTO b_health_agr_code VALUES ('7645867038858', '02', 'ชมรมร้านอาหาร', '1');
INSERT INTO b_health_agr_code VALUES ('7645532361206', '03', 'ชมรมสมุนไพร', '1');
INSERT INTO b_health_agr_code VALUES ('7644136738364', '04', 'ชมรมหมอพื้นบ้าน/แผนไทย', '1');
INSERT INTO b_health_agr_code VALUES ('7643585476920', '05', 'ชมรมผู้สูงอายุ', '1');
INSERT INTO b_health_agr_code VALUES ('7646322647037', '06', 'ประชาคม', '1');
INSERT INTO b_health_agr_code VALUES ('7644162108320', '07', 'ชมรมอนุรักษ์สิ่งแวดล้อม', '1');
INSERT INTO b_health_agr_code VALUES ('7649284241529', '08', 'กองทุน', '1');
INSERT INTO b_health_agr_code VALUES ('7644314835965', '09', 'ชมรมออกกำลังกาย/กีฬา', '1');
INSERT INTO b_health_agr_code VALUES ('7648783292931', '10', 'ชมรม To Be Number One', '1');

CREATE TABLE b_health_agr_type (
    b_health_agr_type_id character varying(255) NOT NULL,
    agr_type_number character varying(255),
    agr_type_description character varying(255),
    agr_type_active character varying(255)
);
INSERT INTO b_health_agr_type VALUES ('7652237645205', '01', 'ส่งเสริมสุขภาพ/ออกกำลังกาย/กีฬา', '1');
INSERT INTO b_health_agr_type VALUES ('7651658698360', '02', 'ป้องกันโรค', '1');
INSERT INTO b_health_agr_type VALUES ('7657424683582', '03', 'นันทนาการ', '1');
INSERT INTO b_health_agr_type VALUES ('7656008642913', '08', 'ระดมทุน/กองทุน/สหกรณ์', '1');
INSERT INTO b_health_agr_type VALUES ('7651465196119', '07', 'อนุรักษ์สิ่งแวดล้อม', '1');
INSERT INTO b_health_agr_type VALUES ('7652087669607', '06', 'พัฒนาชุมชน', '1');
INSERT INTO b_health_agr_type VALUES ('7656691345125', '05', 'วิชาการ', '1');
INSERT INTO b_health_agr_type VALUES ('7653053635239', '04', 'พัฒนา/ส่งเสริมวิชาชีพ', '1');
INSERT INTO b_health_agr_type VALUES ('7654277726438', '09', 'พัฒนาสังคม', '1');
INSERT INTO b_health_agr_type VALUES ('7654194665353', '10', 'ต่อต้านยาเสพติด', '1');
INSERT INTO b_health_agr_type VALUES ('7650027958088', '11', 'บริการสาธารณประโยชน์', '1');

CREATE TABLE b_health_check_health_year_activity (
    b_health_check_health_year_activity_id character varying(255) NOT NULL,
    check_health_year_activity_number character varying(255),
    check_health_year_activity_description character varying(255),
    check_health_year_activity_active character varying(255)
);
INSERT INTO b_health_check_health_year_activity VALUES ('7321284243848', '1', 'EKG', '1');
INSERT INTO b_health_check_health_year_activity VALUES ('7329397577343', '2', 'X-ray ปอด', '1');
INSERT INTO b_health_check_health_year_activity VALUES ('7328337726462', '3', 'เบาหวาน', '1');
INSERT INTO b_health_check_health_year_activity VALUES ('7326016172617', '4', 'ความดันโลหิตสูง', '1');


CREATE TABLE b_health_company_type (
    b_health_company_type_id character varying(255) NOT NULL,
    company_type_number character varying(255),
    company_type_description character varying(255),
    company_type_active character varying(255)
);
INSERT INTO b_health_company_type VALUES ('7023248651590', '01', 'ร้านขายของชำ', '1');
INSERT INTO b_health_company_type VALUES ('7023347181370', '02', 'ร้านขายอาหาร', '1');
INSERT INTO b_health_company_type VALUES ('7024877778807', '03', 'แผงลอย', '1');
INSERT INTO b_health_company_type VALUES ('7028552971515', '04', 'ตลาดสด', '1');
INSERT INTO b_health_company_type VALUES ('7027255557328', '05', 'ร้านขายยา', '1');
INSERT INTO b_health_company_type VALUES ('7025828310908', '06', 'รพ.เอกชน', '1');
INSERT INTO b_health_company_type VALUES ('7029420321139', '07', 'คลีนิคแพทย์', '1');
INSERT INTO b_health_company_type VALUES ('7025166561214', '08', 'คลีนิคทันตแพทย์', '1');
INSERT INTO b_health_company_type VALUES ('7025721235709', '09', 'รพ.สัตว์', '1');
INSERT INTO b_health_company_type VALUES ('7022943901387', '10', 'โรงงานอุตสาหกรรมขนาดใหญ่', '1');
INSERT INTO b_health_company_type VALUES ('7029307562103', '11', 'โรงงานอุตสาหกรรมขนาดกลาง', '1');
INSERT INTO b_health_company_type VALUES ('7026881903241', '12', 'โรงงานอุตสาหกรรมขนาดเล็ก', '1');
INSERT INTO b_health_company_type VALUES ('7025186080167', '13', 'โรงแรม', '1');
INSERT INTO b_health_company_type VALUES ('7022341697363', '14', 'สวนสนุก', '1');

CREATE TABLE b_health_epi_group (
    b_health_epi_group_id character varying(255) NOT NULL,
    health_epi_group_description character varying(255),
    health_epi_group_description_particular character varying(255)
);
INSERT INTO b_health_epi_group VALUES ('7404187616551', 'แรกเกิด(BCG,HBV1)');
INSERT INTO b_health_epi_group VALUES ('7402067609457', '2 เดือน(DTP1,OPV1,HBV2)');
INSERT INTO b_health_epi_group VALUES ('7403612695916', '4 เดือน(DTP2,OPV2)');
INSERT INTO b_health_epi_group VALUES ('7402407144045', '6 เดือน(DTP3,OPV3,HBV3)');
INSERT INTO b_health_epi_group VALUES ('7403445242105', '9 เดือน(Measles)');
INSERT INTO b_health_epi_group VALUES ('7409928669199', '1 ปี 6 เดือน(ครั้งที่ 1)(JE1,DTP4,OPV4)');
INSERT INTO b_health_epi_group VALUES ('7404471099375', '1 ปี 6 เดือน(ครั้งที่ 2)(JE2)');
INSERT INTO b_health_epi_group VALUES ('7408892041819', '2 ปี 6 เดือน(JE3)');
INSERT INTO b_health_epi_group VALUES ('7401610503828', '4 ปี(DTP5,OPV5)');
INSERT INTO b_health_epi_group VALUES ('7402784391181', 'BCG');
INSERT INTO b_health_epi_group VALUES ('7405554204029', 'HBV');
INSERT INTO b_health_epi_group VALUES ('7405735322361', 'DTP');
INSERT INTO b_health_epi_group VALUES ('7401215845660', 'OPV');
INSERT INTO b_health_epi_group VALUES ('7405877284015', 'Measles(หัด)');
INSERT INTO b_health_epi_group VALUES ('7409820380169', 'JE');
INSERT INTO b_health_epi_group VALUES ('7404718314813', 'TT');
INSERT INTO b_health_epi_group VALUES ('7404439719453', 'TT1(ครั้งแรกที่มาฝากครรภ์)');
INSERT INTO b_health_epi_group VALUES ('7403718666738', 'TTเข็ม2(ห่างจากเข็มแรก 1 เดือน)');

CREATE TABLE b_health_epi_item (
    b_health_epi_item_id character varying(25) NOT NULL,
    b_health_epi_group_id character varying(255),
    b_item_id character varying(255)
);
INSERT INTO b_health_epi_item VALUES ('7414812566847', '7402067609457', '1745999523419');
INSERT INTO b_health_epi_item VALUES ('7412150593052', '7402067609457', '1741409371739');
INSERT INTO b_health_epi_item VALUES ('7413456712515', '7402067609457', '1740000000307');
INSERT INTO b_health_epi_item VALUES ('7412082212875', '7402067609457', '1741860301167');
INSERT INTO b_health_epi_item VALUES ('7411675427216', '7402067609457', '1740000000597');
INSERT INTO b_health_epi_item VALUES ('7413546445953', '7402067609457', '1740000000692');
INSERT INTO b_health_epi_item VALUES ('7417010537712', '7403612695916', '1745999523419');
INSERT INTO b_health_epi_item VALUES ('7413787865998', '7403612695916', '1741409371739');
INSERT INTO b_health_epi_item VALUES ('7418501372752', '7403612695916', '1741860301167');
INSERT INTO b_health_epi_item VALUES ('7415572486151', '7403612695916', '1740000000597');
INSERT INTO b_health_epi_item VALUES ('7415742455349', '7403612695916', '1740000000692');
INSERT INTO b_health_epi_item VALUES ('7417357192703', '7402407144045', '1745999523419');
INSERT INTO b_health_epi_item VALUES ('7415823148592', '7402407144045', '1741409371739');
INSERT INTO b_health_epi_item VALUES ('7416659836884', '7402407144045', '1740000000307');
INSERT INTO b_health_epi_item VALUES ('7412840793469', '7402407144045', '1741860301167');
INSERT INTO b_health_epi_item VALUES ('7410271499327', '7402407144045', '1740000000597');
INSERT INTO b_health_epi_item VALUES ('7414428535320', '7402407144045', '1740000000692');
INSERT INTO b_health_epi_item VALUES ('7417081693546', '7403445242105', '1747393571173');
INSERT INTO b_health_epi_item VALUES ('7419865612183', '7403445242105', '1741860301167');
INSERT INTO b_health_epi_item VALUES ('7417236525240', '7403445242105', '1740000000552');
INSERT INTO b_health_epi_item VALUES ('7413651106156', '7403445242105', '1740000000692');
INSERT INTO b_health_epi_item VALUES ('7413541809920', '7409928669199', '1744531027720');
INSERT INTO b_health_epi_item VALUES ('7419378802224', '7409928669199', '1745999523419');
INSERT INTO b_health_epi_item VALUES ('7419043008684', '7409928669199', '1741409371739');
INSERT INTO b_health_epi_item VALUES ('7415677641911', '7409928669199', '1741860301167');
INSERT INTO b_health_epi_item VALUES ('7412269294270', '7409928669199', '1740000000597');
INSERT INTO b_health_epi_item VALUES ('7411738201790', '7409928669199', '1740000000552');
INSERT INTO b_health_epi_item VALUES ('7417949256905', '7409928669199', '1740000000692');
INSERT INTO b_health_epi_item VALUES ('7411632700707', '7409928669199', '1740000000703');
INSERT INTO b_health_epi_item VALUES ('7411438280460', '7404471099375', '1744531027720');
INSERT INTO b_health_epi_item VALUES ('7416843646768', '7404471099375', '1741860301167');
INSERT INTO b_health_epi_item VALUES ('7418039459193', '7404471099375', '1740000000552');
INSERT INTO b_health_epi_item VALUES ('7415290572780', '7404471099375', '1740000000704');
INSERT INTO b_health_epi_item VALUES ('7412069911004', '7408892041819', '1744531027720');
INSERT INTO b_health_epi_item VALUES ('7415615313518', '7408892041819', '1741860301167');
INSERT INTO b_health_epi_item VALUES ('7411603946437', '7408892041819', '1740000000552');
INSERT INTO b_health_epi_item VALUES ('7418175253383', '7408892041819', '1740000000704');
INSERT INTO b_health_epi_item VALUES ('7411235989483', '7401610503828', '1745999523419');
INSERT INTO b_health_epi_item VALUES ('7413545900733', '7401610503828', '1741409371739');
INSERT INTO b_health_epi_item VALUES ('7414530817878', '7401610503828', '1741860301167');
INSERT INTO b_health_epi_item VALUES ('7411109012478', '7401610503828', '1740000000597');
INSERT INTO b_health_epi_item VALUES ('7419535034267', '7401610503828', '1740000000692');
INSERT INTO b_health_epi_item VALUES ('7419672474794', '7402784391181', '1740000000306');
INSERT INTO b_health_epi_item VALUES ('7418005057092', '7405554204029', '1740000000307');
INSERT INTO b_health_epi_item VALUES ('7413631598670', '7405735322361', '1745999523419');
INSERT INTO b_health_epi_item VALUES ('7416637601888', '7401215845660', '1741409371739');
INSERT INTO b_health_epi_item VALUES ('7410238429960', '7405877284015', '1747393571173');
INSERT INTO b_health_epi_item VALUES ('7411036623614', '7409820380169', '1744531027720');
INSERT INTO b_health_epi_item VALUES ('7410516499219', '7404718314813', '1740000000294');
INSERT INTO b_health_epi_item VALUES ('7412578086035', '7404439719453', '1740000000294');
INSERT INTO b_health_epi_item VALUES ('7411937011407', '7403718666738', '1740000000294');

CREATE TABLE b_health_epi_item_drug_setup (
    b_health_epi_item_drug_setup_id character varying(255) NOT NULL,
    b_health_epi_item_id character varying(255),
    b_health_epi_item_drug_setup_description character varying(255),
    health_epi_item_drug_setup_use_uom character varying(255),
    health_epi_item_drug_setup_purch_uom character varying(255),
    health_epi_item_drug_setup_caution character varying(255),
    f_item_day_time_id character varying(255),
    health_epi_item_drug_setup_printable character varying(255) DEFAULT 1,
    b_item_drug_instruction_id character varying(255),
    b_item_drug_frequency_id character varying(255),
    health_epi_item_drug_setup_special_prescription character varying(255),
    health_epi_item_drug_setup_usage_text character varying(255),
    b_item_id character varying(255),
    health_epi_item_drug_setup_dose double precision,
    health_epi_item_drug_setup_qty double precision
);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7694027921006', '7414812566847', '', '2520000000006', '2520000000006', '', '', '1', '1510000000026', '1500000000001', '0', '', '1745999523419', 0.5, 0.5);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7692664169227', '7412150593052', '', '2520000000003', '2520000000003', '', '', '1', '1510000000050', '1500000000001', '0', '', '1741409371739', 2, 2);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7697974622563', '7413456712515', '', '2520000000006', '2520000000006', '', '', '1', '1510000000026', '1500000000001', '0', '', '1740000000307', 0.5, 0.5);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7698713597062', '7417010537712', '', '2520000000006', '2520000000006', '', '', '1', '1510000000026', '1500000000001', '0', '', '1745999523419', 0.5, 0.5);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7693182667916', '7413787865998', '', '2520000000003', '2520000000003', '', '', '1', '1510000000050', '1500000000001', '0', '', '1741409371739', 2, 2);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7697065441088', '7417357192703', '', '2520000000006', '2520000000006', '', '', '1', '1510000000026', '1500000000001', '0', '', '1745999523419', 0.5, 0.5);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7698754633625', '7415823148592', '', '2520000000003', '2520000000003', '', '', '1', '1510000000050', '1500000000001', '0', '', '1741409371739', 2, 2);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7692155681323', '7416659836884', '', '2520000000006', '2520000000006', '', '', '1', '1510000000026', '1500000000001', '0', '', '1740000000307', 0.5, 0.5);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7692328713346', '7417081693546', '', '2520000000006', '2520000000006', '', '', '1', '1510000000075', '1500000000001', '0', '', '1747393571173', 0.5, 0.5);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7697432682133', '7413541809920', '', '2520000000006', '2520000000006', '', '', '1', '1510000000075', '1500000000001', '0', '', '1744531027720', 0.25, 0.25);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7690581202769', '7419378802224', '', '2520000000006', '2520000000006', '', '', '1', '1510000000026', '1500000000001', '0', '', '1745999523419', 0.5, 0.5);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7691794596448', '7419043008684', '', '2520000000003', '2520000000003', '', '', '1', '1510000000050', '1500000000001', '0', '', '1741409371739', 2, 2);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7693797788141', '7411438280460', '', '2520000000006', '2520000000006', '', '', '1', '1510000000075', '1500000000001', '0', '', '1744531027720', 0.25, 0.25);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7695160832260', '7412069911004', '', '2520000000006', '2520000000006', '', '', '1', '1510000000075', '1500000000001', '0', '', '1744531027720', 0.25, 0.25);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7693705253043', '7411235989483', '', '2520000000006', '2520000000006', '', '', '1', '1510000000026', '1500000000001', '0', '', '1745999523419', 0.5, 0.5);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7692929991351', '7413545900733', '', '2520000000003', '2520000000003', '', '', '1', '1510000000050', '1500000000001', '0', '', '1741409371739', 2, 2);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7697076010392', '7419672474794', '', '2520000000006', '2520000000006', '', '', '1', '1510000000024', '1500000000001', '0', '', '1740000000306', 0.10000000000000001, 0.10000000000000001);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7696620768902', '7418005057092', '', '2520000000006', '2520000000006', '', '', '1', '1510000000026', '1500000000001', '0', '', '1740000000307', 0.5, 0.5);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7693964478968', '7413631598670', '', '2520000000006', '2520000000006', '', '', '1', '1510000000026', '1500000000001', '0', '', '1745999523419', 0.5, 0.5);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7695152106659', '7416637601888', '', '2520000000003', '2520000000003', '', '', '1', '1510000000050', '1500000000001', '0', '', '1741409371739', 2, 2);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7699400923276', '7410238429960', '', '2520000000006', '2520000000006', '', '', '1', '1510000000075', '1500000000001', '0', '', '1747393571173', 0.5, 0.5);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7692409264641', '7411036623614', '', '2520000000006', '2520000000006', '', '', '1', '1510000000075', '1500000000001', '0', '', '1744531027720', 0.25, 0.25);
INSERT INTO b_health_epi_item_drug_setup VALUES ('7692955369669', '7410516499219', '', '2520000000006', '2520000000006', '', '', '1', '1510000000026', '1500000000001', '0', '', '1740000000294', 0.5, 0.5);

CREATE TABLE b_health_family_planing_group (
    b_health_family_planing_group_id character varying(255) NOT NULL,
    health_family_planing_group_number character varying(255),
    health_family_planing_group_description character varying(255),
    health_family_planing_group_factor character varying(255),
    f_health_family_planing_method_id character varying(255),
    health_family_planing_group_add_order character varying(255),
    health_family_planning_group_active character varying(255)
);
INSERT INTO b_health_family_planing_group VALUES ('7371426318460', 'D001', 'ยาเม็ดคุมกำเนิด', '0', '1', '0', '1');
INSERT INTO b_health_family_planing_group VALUES ('7379883129358', 'D002', 'ถุงยางอนามัย', '0', '5', '1', '1');

CREATE TABLE b_health_family_planing_item (
    b_health_family_planing_item_id character varying(255) NOT NULL,
    b_health_family_planing_group_id character varying(255),
    b_item_id character varying(255)
);
INSERT INTO b_health_family_planing_item VALUES ('7399632027360', '7379883129358', '1740000000768');
INSERT INTO b_health_family_planing_item VALUES ('7399541255587', '7379883129358', '1740000001306');
INSERT INTO b_health_family_planing_item VALUES ('7393609745597', '7371426318460', '1740000000211');
INSERT INTO b_health_family_planing_item VALUES ('7396483112651', '7371426318460', '1740000000004');
INSERT INTO b_health_family_planing_item VALUES ('7392231799019', '7379883129358', '1740000001297');

CREATE TABLE b_health_family_planing_item_drug_setup (
    b_health_family_planing_item_drug_setup_id character varying(255) NOT NULL,
    b_health_family_planing_item_id character varying(255),
    b_health_family_planing_item_drug_setup_description character varying(255),
    health_family_planing_item_drug_setup_use_uom character varying(255),
    health_family_planning_item_drug_setup_purch_uom character varying(255),
    health_family_planning_item_drug_setup_caution character varying(255),
    f_item_day_time_id character varying(255),
    health_family_planing_item_drug_setup_printable character varying(255),
    b_item_drug_instruction_id character varying(255),
    b_item_drug_frequency_id character varying(255),
    health_family_planing_item_drug_setup_special_prescription character varying(255),
    health_family_planning_item_drug_setup_usage_text character varying(255),
    b_item_id character varying(255),
    health_family_planing_item_drug_setup_dose double precision DEFAULT 0,
    health_family_planing_item_drug_setup_qty double precision DEFAULT 0
);
INSERT INTO b_health_family_planing_item_drug_setup VALUES ('7386310449176', '7396483112651', '', '2520000000002', '2520000000002', '', '', '1', '1510000000028', '1500000000001', '', '', '1740000000004', 1, 0);
INSERT INTO b_health_family_planing_item_drug_setup VALUES ('7384531242880', '7399541255587', 'ไม่ควรใช้ติดต่อกันนานเกิน 7 วัน', '2520000000009', '2520000000009', '', '', '1', '1510000000042', '1500000000022', '0', '', '1740000000211', 2, 20);

CREATE TABLE b_health_home_pet_type (
    b_health_home_pet_type_id character varying(255) NOT NULL,
    health_home_pet_type_number character varying(255),
    health_home_pet_type_name character varying(255),
    health_home_pet_type_active character varying(255)
);
INSERT INTO b_health_home_pet_type VALUES ('7220000000001', '1', 'สุนัข', '1');
INSERT INTO b_health_home_pet_type VALUES ('7220000000002', '2', 'แมว', '1');
INSERT INTO b_health_home_pet_type VALUES ('7220000000003', '3', 'ลิง', '1');

CREATE TABLE b_health_market_type (
    b_health_market_type_id character varying(255) NOT NULL,
    market_type_number character varying(255),
    market_type_description character varying(255),
    market_type_active character varying(255)
);
INSERT INTO b_health_market_type VALUES ('7472466221527', '01', 'ตลาดสดประเภท 1', '1');
INSERT INTO b_health_market_type VALUES ('7477111391399', '02', 'ตลาดสดประเภท 2', '1');
INSERT INTO b_health_market_type VALUES ('7475403254880', '03', 'ตลาดสดประเภท 3', '1');
INSERT INTO b_health_market_type VALUES ('7470206709991', '11', 'ร้านอาหาร < 200 ตร.ม.', '1');
INSERT INTO b_health_market_type VALUES ('7475306580068', '12', 'ร้านอาหารตั้งแต่ 200 ตร.ม.', '1');
INSERT INTO b_health_market_type VALUES ('7473232269698', '21', 'แผงลอย', '1');

CREATE TABLE b_health_market_type_lock (
    b_health_market_type_lock_id character varying(255),
    market_type_lock_number character varying(255),
    market_type_lock_description character varying(255),
    market_type_lock_active character varying(255)
);
INSERT INTO b_health_market_type_lock VALUES ('7486198226439', '001', 'อาหารสด', '1');
INSERT INTO b_health_market_type_lock VALUES ('7489812785246', '002', 'อาหารแปรรูป', '1');
INSERT INTO b_health_market_type_lock VALUES ('7485231099990', '003', 'อาหารสำเร็จรูป', '1');
INSERT INTO b_health_market_type_lock VALUES ('7484901183023', '011', 'อาหารคาว', '1');
INSERT INTO b_health_market_type_lock VALUES ('7489466833058', '012', 'อาหารหวาน', '1');
INSERT INTO b_health_market_type_lock VALUES ('7484560682762', '013', 'เครื่องดื่ม/น้ำดื่ม', '1');
INSERT INTO b_health_market_type_lock VALUES ('7487845610254', '014', 'อาหารคาว+อาหารหวาน', '1');
INSERT INTO b_health_market_type_lock VALUES ('7481541332068', '015', 'อาหารคาว+เครื่องดื่ม/น้ำดื่ม', '1');
INSERT INTO b_health_market_type_lock VALUES ('7487279037707', '016', 'อาหารหวาน+เครื่องดิ่ม/น้ำดื่ม', '1');
INSERT INTO b_health_market_type_lock VALUES ('7487460686124', '019', 'อาหารทั้ง 3 ประเภท', '1');

CREATE TABLE b_health_resource (
    b_health_resource_id character varying(255) NOT NULL,
    resource_number character varying(255),
    resource_description character varying(255),
    resource_active character varying(255)
);
INSERT INTO b_health_resource VALUES ('7614827179321', '01', 'หอกระจายข่าว', '1');
INSERT INTO b_health_resource VALUES ('7618963938213', '02', 'เสียงตามสาย', '1');
INSERT INTO b_health_resource VALUES ('7614476041405', '03', 'ที่อ่านหนังสือ', '1');
INSERT INTO b_health_resource VALUES ('7619203185009', '04', 'สวนสมุนไพร', '1');
INSERT INTO b_health_resource VALUES ('7614825123262', '05', 'สวนสาธารณะ', '1');
INSERT INTO b_health_resource VALUES ('7611550271787', '06', 'สนามกีฬา', '1');
INSERT INTO b_health_resource VALUES ('7616740835965', '07', 'ลานกีฬา', '1');
INSERT INTO b_health_resource VALUES ('7619980954685', '08', 'ศสมช.', '1');

CREATE TABLE b_health_service_type (
    b_health_service_type_id character varying(255) NOT NULL,
    service_type_number character varying(255),
    service_type_description character varying(255),
    service_type_active character varying(255)
);
INSERT INTO b_health_service_type VALUES ('7252364274730', '1', 'เอดส์', '1');
INSERT INTO b_health_service_type VALUES ('7254932134393', '2', 'มีบุตรยาก', '1');
INSERT INTO b_health_service_type VALUES ('7251413984077', '3', 'ยาเสพติด', '1');
INSERT INTO b_health_service_type VALUES ('7250449221385', '4', 'โรคเรื้อรัง', '1');
INSERT INTO b_health_service_type VALUES ('7253937639085', '5', 'คลายเครียด', '1');
INSERT INTO b_health_service_type VALUES ('7251847018394', '6', 'การใช้ยา (รักษาโรค)', '1');

CREATE TABLE b_health_temple_personel (
    b_health_temple_personel_id character varying(255) NOT NULL,
    temple_personel_number character varying(255),
    temple_personel_description character varying(255),
    temple_personel_active character varying(255)
);
INSERT INTO b_health_temple_personel VALUES ('7365651748410', '01', 'พระ', '1');
INSERT INTO b_health_temple_personel VALUES ('7361950870847', '02', 'เณร', '1');
INSERT INTO b_health_temple_personel VALUES ('7367299350485', '03', 'ชี', '1');
INSERT INTO b_health_temple_personel VALUES ('7360656020669', '04', 'เด็กวัด', '1');
INSERT INTO b_health_temple_personel VALUES ('7369480107533', '05', 'บาทหลวง', '1');
INSERT INTO b_health_temple_personel VALUES ('7368693914042', '06', 'ซิสเตอร์', '1');
INSERT INTO b_health_temple_personel VALUES ('7366433344398', '07', 'โต๊ะอิหม่าม', '1');
INSERT INTO b_health_temple_personel VALUES ('7361567372456', '08', 'กอเต็บ', '1');
INSERT INTO b_health_temple_personel VALUES ('7362129948180', '09', 'บิหลั่น', '1');

CREATE TABLE b_health_temple_type (
    b_health_temple_type_id character varying(255) NOT NULL,
    temple_type_number character varying(255),
    temple_type_description character varying(255),
    temple_type_active character varying(255)
);
INSERT INTO b_health_temple_type VALUES ('7093089289189', '01', 'วัด', '1');
INSERT INTO b_health_temple_type VALUES ('7097431373383', '02', 'สำนักสงฆ์', '1');
INSERT INTO b_health_temple_type VALUES ('7099183349904', '03', 'โบสถ์', '1');
INSERT INTO b_health_temple_type VALUES ('7093271076667', '04', 'มัสยิด', '1');
INSERT INTO b_health_temple_type VALUES ('7095015436753', '05', 'สุเหร่า', '1');

CREATE TABLE b_health_village_location (
    b_health_village_location_id character varying(255) NOT NULL,
    village_location_number character varying(255),
    village_location_description character varying(255),
    village_location_active character varying(255)
);
INSERT INTO b_health_village_location VALUES ('7130000000001', '0', 'อบต', '1');
INSERT INTO b_health_village_location VALUES ('7130000000002', '1', 'เทศบาล', '1');
INSERT INTO b_health_village_location VALUES ('7130000000003', '2', 'ทั้งสองเขต', '1');
INSERT INTO b_health_village_location VALUES ('7135547057349', '3', 'สภาตำบล', '1');

CREATE TABLE b_health_water_type (
    b_health_water_type_id character varying(255) NOT NULL,
    water_type_number character varying(255),
    water_type_description character varying(255),
    water_type_active character varying(255)
);
INSERT INTO b_health_water_type VALUES ('7106677306324', '11', 'น้ำฝน', '1');
INSERT INTO b_health_water_type VALUES ('7104411077356', '21', 'ประปาหมู่บ้าน(บาดาล)', '1');
INSERT INTO b_health_water_type VALUES ('7107836092866', '22', 'ประปาหมู่บ้าน(ผิวดิน)', '1');
INSERT INTO b_health_water_type VALUES ('7109184833377', '23', 'ประปานครหลวง', '1');
INSERT INTO b_health_water_type VALUES ('7108021685043', '31', 'น้ำซื้อได้มาตรฐาน', '1');
INSERT INTO b_health_water_type VALUES ('7102661664788', '32', 'น้ำซื้อ ไม่ได้มาตรฐาน', '1');
INSERT INTO b_health_water_type VALUES ('7109374737340', '41', 'น้ำที่ปรับปรุงคุณภาพ(ผ่านเครื่องกรอง)', '1');
INSERT INTO b_health_water_type VALUES ('7108355065261', '42', 'น้ำที่ปรับปรุงคุณภาพ(ต้ม)', '1');
INSERT INTO b_health_water_type VALUES ('7102445590407', '51', 'บ่อบาดาลถูกหลักสุขาฯ', '1');
INSERT INTO b_health_water_type VALUES ('7108780995956', '52', 'บ่อบาดาล ไม่ถูกหลักสุขาฯ', '1');
INSERT INTO b_health_water_type VALUES ('7100796298896', '61', 'บ่อน้ำตื้นถูกหลักสุขาฯ', '1');
INSERT INTO b_health_water_type VALUES ('7105150390239', '62', 'บ่อน้ำตื้น ไม่ถูกหลักสุขาฯ', '1');
INSERT INTO b_health_water_type VALUES ('7107284530912', '91', 'แหล่งน้ำธรรมชาติ/แม่น้ำ/ห้วย/…', '1');

CREATE TABLE b_school_class (
    b_school_class_id character varying(255) NOT NULL,
    school_class_number character varying(255),
    school_class_description character varying(255),
    school_class_active character varying(255),
    school_max_class_status character varying(255)
);
INSERT INTO b_school_class VALUES ('7337072875882', '01', 'อนุบาล 1', '1', '0');
INSERT INTO b_school_class VALUES ('7330169694226', '31', 'ม.1', '1', '0');
INSERT INTO b_school_class VALUES ('7330300508398', '32', 'ม.2', '1', '0');
INSERT INTO b_school_class VALUES ('7333567540907', '33', 'ม.3', '1', '1');
INSERT INTO b_school_class VALUES ('7335256936476', '51', 'ปวส.1', '1', '0');
INSERT INTO b_school_class VALUES ('7333149467647', '52', 'ปวส.2', '1', '0');
INSERT INTO b_school_class VALUES ('7333953539728', '41', 'ม.4', '1', '0');
INSERT INTO b_school_class VALUES ('7331978449929', '41', 'ปวช.1', '1', '0');
INSERT INTO b_school_class VALUES ('7330991019611', '42', 'ม.5', '1', '0');
INSERT INTO b_school_class VALUES ('7338390182342', '42', 'ปวช.2', '1', '0');
INSERT INTO b_school_class VALUES ('7339831960239', '43', 'ม.6', '1', '1');
INSERT INTO b_school_class VALUES ('7330193592659', '43', 'ปวช.3', '1', '1');
INSERT INTO b_school_class VALUES ('7334230084658', '02', 'อนุบาล 2', '1', '0');
INSERT INTO b_school_class VALUES ('7330321409040', '03', 'อนุบาล 3', '1', '1');
INSERT INTO b_school_class VALUES ('7337839240943', '11', 'ป.1', '1', '0');
INSERT INTO b_school_class VALUES ('7331450006821', '12', 'ป.2', '1', '0');
INSERT INTO b_school_class VALUES ('7336248048270', '13', 'ป.3', '1', '0');
INSERT INTO b_school_class VALUES ('7332490877509', '21', 'ป.4', '1', '0');
INSERT INTO b_school_class VALUES ('7335114289534', '22', 'ป.5', '1', '0');
INSERT INTO b_school_class VALUES ('7339521901880', '23', 'ป.6', '1', '1');
INSERT INTO b_school_class VALUES ('7337986584727', '53', 'ปวส.3', '1', '1');

CREATE TABLE f_health_abnormal (
    f_health_abnormal_id character varying(255) NOT NULL,
    health_abnormal_description character varying(255)
);
INSERT INTO f_health_abnormal VALUES ('0', 'ไม่พบอาการผิดปกติ');
INSERT INTO f_health_abnormal VALUES ('1', 'อาเจียน');
INSERT INTO f_health_abnormal VALUES ('2', 'เวียนศรีษะ');
INSERT INTO f_health_abnormal VALUES ('3', 'ตาพร่ามัว');
INSERT INTO f_health_abnormal VALUES ('4', 'ท้องผูก');
INSERT INTO f_health_abnormal VALUES ('5', 'หลอดเลือดดำพอง');
INSERT INTO f_health_abnormal VALUES ('6', 'ริดสีดวงทวาร');

CREATE TABLE f_health_anc_section (
    f_health_anc_section_id character varying(255) NOT NULL,
    health_anc_section_description character varying(255)
);
INSERT INTO f_health_anc_section VALUES ('1', ' ช่วงที่ 1 อายุครรภ์น้อยกว่า 6 เดือน');
INSERT INTO f_health_anc_section VALUES ('2', ' ช่วงที่  2 อายุครรภ์ระหว่าง 6 - 7 เดือน');
INSERT INTO f_health_anc_section VALUES ('3', ' ช่วงที่  3 อายุครรภ์ระหว่าง 7 - 8 เดือน');
INSERT INTO f_health_anc_section VALUES ('4', ' ช่วงที่  4 อายุครรภ์มากกว่า 8  เดือน');

CREATE TABLE f_health_birth_method (
    f_health_birth_method_id character varying(255) NOT NULL,
    birth_method_description character varying(255)
);
INSERT INTO f_health_birth_method VALUES ('1', 'NORMAL');
INSERT INTO f_health_birth_method VALUES ('2', 'CESAREAN');
INSERT INTO f_health_birth_method VALUES ('3', 'VACUUM');
INSERT INTO f_health_birth_method VALUES ('4', 'FORCEPS');
INSERT INTO f_health_birth_method VALUES ('5', 'BREECH ท่าก้น');
INSERT INTO f_health_birth_method VALUES ('6', 'ABORTION');
INSERT INTO f_health_birth_method VALUES ('7', 'คลอดแฝดหลายวิธี');
INSERT INTO f_health_birth_method VALUES ('0', 'ไม่ระบุ');

CREATE TABLE f_health_check_result (
    f_health_check_result_id character varying(255) NOT NULL,
    health_check_result_description character varying(255)
);
INSERT INTO f_health_check_result VALUES ('1', 'ปกติ');
INSERT INTO f_health_check_result VALUES ('2', 'ผิดปกติ');
INSERT INTO f_health_check_result VALUES ('3', 'ผิดปกติซ้าย');
INSERT INTO f_health_check_result VALUES ('4', 'ผิดปกติขวา');
INSERT INTO f_health_check_result VALUES ('5', 'ผิดปกติทั้ง 2 ข้าง');

CREATE TABLE f_health_community_resorce_owner (
    f_health_community_resorce_owner_id character varying(255),
    community_resorce_owner_description character varying(255)
);
INSERT INTO f_health_community_resorce_owner VALUES ('01', 'อบต.');
INSERT INTO f_health_community_resorce_owner VALUES ('02', 'เทศบาล');
INSERT INTO f_health_community_resorce_owner VALUES ('03', 'ผู้ใหญ่บ้าน/กำนัน');
INSERT INTO f_health_community_resorce_owner VALUES ('04', 'โรงเรียน');
INSERT INTO f_health_community_resorce_owner VALUES ('05', 'วัด,มัสยิคม,โบถส์');
INSERT INTO f_health_community_resorce_owner VALUES ('11', 'กระทรวงมหาดไทย');
INSERT INTO f_health_community_resorce_owner VALUES ('06', 'เอกชน/ชุมชน ระบุชื่อเจ้าของ');
INSERT INTO f_health_community_resorce_owner VALUES ('12', 'กระทรวงศึกษาธิการ');
INSERT INTO f_health_community_resorce_owner VALUES ('13', 'กระทรวงสาธารณสุข');
INSERT INTO f_health_community_resorce_owner VALUES ('14', 'กระทรวงพัฒนาสังคม');
INSERT INTO f_health_community_resorce_owner VALUES ('15', 'กระทรวงวัฒนธรรม');
INSERT INTO f_health_community_resorce_owner VALUES ('16', 'กระทรวงกีฬาและการท่องเที่ยว');
INSERT INTO f_health_community_resorce_owner VALUES ('17', 'ทบวงมหาวิทยาลัย');
INSERT INTO f_health_community_resorce_owner VALUES ('20', 'ส่วนราชการภูมิภาค/ส่วนกลางอื่นๆ');
INSERT INTO f_health_community_resorce_owner VALUES ('30', 'อื่นๆ ระบุ');

CREATE TABLE f_health_community_standard (
    f_health_community_standard_id character varying(255),
    community_standard_description character varying(255)
);
INSERT INTO f_health_community_standard VALUES ('0', 'ไม่ผ่านเกณฑ์');
INSERT INTO f_health_community_standard VALUES ('1', 'ผ่านเกณฑ์');
INSERT INTO f_health_community_standard VALUES ('9', 'อยู่ระหว่างดำเนินการ');
INSERT INTO f_health_community_standard VALUES ('2', 'ผ่านเกณฑ์ระดับทองแดง หรือ C');
INSERT INTO f_health_community_standard VALUES ('3', 'ผ่านเกณฑ์ระดับเงิน หรือ B');
INSERT INTO f_health_community_standard VALUES ('4', 'ผ่านเกณฑ์ระดับทอง หรือ A');

CREATE TABLE f_health_community_standard_type (
    f_health_community_standard_type_id character varying(255),
    community_standard_type_description character varying(255)
);
INSERT INTO f_health_community_standard_type VALUES ('00', 'ไม่เข้าร่วมโครงการ');
INSERT INTO f_health_community_standard_type VALUES ('01', 'HA');
INSERT INTO f_health_community_standard_type VALUES ('02', 'PSO');
INSERT INTO f_health_community_standard_type VALUES ('03', 'ISO');
INSERT INTO f_health_community_standard_type VALUES ('04', '5ส.');
INSERT INTO f_health_community_standard_type VALUES ('05', 'TQM');
INSERT INTO f_health_community_standard_type VALUES ('06', 'ไคเซน');
INSERT INTO f_health_community_standard_type VALUES ('10', 'โรงเรียนเสริมสุขภาพ');
INSERT INTO f_health_community_standard_type VALUES ('11', 'โรงเรียนปลอดลูกน้ำยุงลาย');

CREATE TABLE f_health_family_planing (
    f_health_family_planing_id character varying(255) NOT NULL,
    health_family_planing_description character varying(255)
);
INSERT INTO f_health_family_planing VALUES ('1', 'ต้องการบุตร');
INSERT INTO f_health_family_planing VALUES ('2', 'หมันธรรมชาติ');
INSERT INTO f_health_family_planing VALUES ('3', 'อื่นๆ');

CREATE TABLE f_health_family_planing_method (
    f_health_family_planing_method_id character varying(255) NOT NULL,
    health_family_planing_method_description character varying(255)
);
INSERT INTO f_health_family_planing_method VALUES ('0', 'ไม่ได้คุม');
INSERT INTO f_health_family_planing_method VALUES ('1', 'ยาเม็ด');
INSERT INTO f_health_family_planing_method VALUES ('2', 'ยาฉีด');
INSERT INTO f_health_family_planing_method VALUES ('3', 'ห่วงอนามัย');
INSERT INTO f_health_family_planing_method VALUES ('4', 'ยาฝัง');
INSERT INTO f_health_family_planing_method VALUES ('5', 'ถุงยางอนามัย');
INSERT INTO f_health_family_planing_method VALUES ('6', 'หมันชาย');
INSERT INTO f_health_family_planing_method VALUES ('7', 'หมันหญิง');
INSERT INTO f_health_family_planing_method VALUES ('9', 'เคย(วิธีอื่นๆ)');

CREATE TABLE f_health_grow (
    f_health_grow_id character varying(255) NOT NULL,
    health_grow_description character varying(255),
    health_grow_standard character varying(255)
);
INSERT INTO f_health_grow VALUES ('1', 'จ้องหน้า สบตา', '1');
INSERT INTO f_health_grow VALUES ('2', 'หยุดฟังเสียง', '2');
INSERT INTO f_health_grow VALUES ('3', 'ส่งเสียงโต้ตอบ ยิ้ม คุยอ้อแอ้ในคอ', '2');
INSERT INTO f_health_grow VALUES ('4', 'สบตามองตาม 180 องศา', '2');
INSERT INTO f_health_grow VALUES ('5', 'ชูคอในท่าคว่ำ', '4');
INSERT INTO f_health_grow VALUES ('6', 'คว้าของมือเดียว คืบ พลิกคว่ำ/หงาย', '6');
INSERT INTO f_health_grow VALUES ('7', 'เลียนเสียงพูดคุย หันตามเสียงเรียก', '6');
INSERT INTO f_health_grow VALUES ('8', 'นั่งทรงตัวได้เอง', '9');
INSERT INTO f_health_grow VALUES ('9', 'มองตามของตก', '9');
INSERT INTO f_health_grow VALUES ('10', 'เล่นจ๊ะเอ๋ ตบมือ', '9');
INSERT INTO f_health_grow VALUES ('11', 'เกาะยืน', '10');
INSERT INTO f_health_grow VALUES ('12', 'ตั้งไข่', '12');
INSERT INTO f_health_grow VALUES ('13', 'เดินได้เอง', '15');
INSERT INTO f_health_grow VALUES ('14', 'พูดคำที่มีความหมาย 1-2 คำ', '18');
INSERT INTO f_health_grow VALUES ('15', 'ชี้อวัยวะต่างๆของร่างกาย', '18');
INSERT INTO f_health_grow VALUES ('16', 'ตักอาหารกินเอง', '24');
INSERT INTO f_health_grow VALUES ('17', 'พูด 2-3 คำติดกัน', '24');
INSERT INTO f_health_grow VALUES ('18', 'ขีดเส้นตรง เรียกชื่อสิ่งต่างๆ  ซักถาม', '36');
INSERT INTO f_health_grow VALUES ('19', 'บอกชื่อตนเองได้', '36');
INSERT INTO f_health_grow VALUES ('20', 'บอกเพศของตนเองได้', '36');
INSERT INTO f_health_grow VALUES ('21', 'วาดวงกลมตามตัวอย่างได้', '42');
INSERT INTO f_health_grow VALUES ('22', 'บอกสีได้ 1 สี', '42');
INSERT INTO f_health_grow VALUES ('23', 'ล้างหน้า แปรงฟัน', '48');
INSERT INTO f_health_grow VALUES ('24', 'รู้จักสี ยืนขาเดียว', '60');

CREATE TABLE f_health_gum_level (
    f_health_gum_level_id character varying(255) NOT NULL,
    health_gum_level_description character varying(255)
);
INSERT INTO f_health_gum_level VALUES ('0', 'ปกติ');
INSERT INTO f_health_gum_level VALUES ('1', 'ระดับที่ 1');
INSERT INTO f_health_gum_level VALUES ('2', 'ระดับที่ 2');
INSERT INTO f_health_gum_level VALUES ('3', 'ระดับที่ 3');
INSERT INTO f_health_gum_level VALUES ('9', 'ไม่ได้รับการตรวจ');

CREATE TABLE f_health_home_charactor (
    f_health_home_charactor_id character varying(255) NOT NULL,
    health_home_charactor_description character varying(255)
);
INSERT INTO f_health_home_charactor VALUES ('01', 'เพิงพัก');
INSERT INTO f_health_home_charactor VALUES ('02', 'บ้านยกพิ้นสูง');
INSERT INTO f_health_home_charactor VALUES ('03', 'ห้องแถว');
INSERT INTO f_health_home_charactor VALUES ('04', 'ทาวน์เฮาส์');
INSERT INTO f_health_home_charactor VALUES ('05', 'บ้านเดี่ยว/บ้านแฝด');
INSERT INTO f_health_home_charactor VALUES ('06', 'แฟลต/คอนโด');
INSERT INTO f_health_home_charactor VALUES ('08', 'อื่นๆ');
INSERT INTO f_health_home_charactor VALUES ('07', 'เรือนแพ เรือ');

CREATE TABLE f_health_home_community_charac (
    f_health_home_community_charac_id character varying(255) NOT NULL,
    health_home_community_charac_description character varying(255)
);
INSERT INTO f_health_home_community_charac VALUES ('01', 'ชุมชนเกษตร');
INSERT INTO f_health_home_community_charac VALUES ('02', 'ชุมชนบ้านจัดสรร');
INSERT INTO f_health_home_community_charac VALUES ('03', 'ชุมชนพาณิชยกรรม');
INSERT INTO f_health_home_community_charac VALUES ('04', 'ชุมชนอาศัยในเมือง');
INSERT INTO f_health_home_community_charac VALUES ('05', 'ชุมชนแออัด');
INSERT INTO f_health_home_community_charac VALUES ('06', ' แคมป์คนงานหรือเพิงพักคนงานชั่วคราว');
INSERT INTO f_health_home_community_charac VALUES ('07', 'ชุมชนแฟลต/คอนโด');
INSERT INTO f_health_home_community_charac VALUES ('08', 'เกาะ/เรือนแพ');
INSERT INTO f_health_home_community_charac VALUES ('09', 'อื่นๆ');

CREATE TABLE f_health_home_dung_control (
    f_health_home_dung_control_id character varying(255) NOT NULL,
    health_home_dung_control_clean character varying(255)
);
INSERT INTO f_health_home_dung_control VALUES ('0', 'ไม่ถูกหลัก');
INSERT INTO f_health_home_dung_control VALUES ('1', 'ถูกหลัก');
INSERT INTO f_health_home_dung_control VALUES ('2', 'ไม่เลี้ยง');

CREATE TABLE f_health_home_garbage_method (
    f_health_home_garbage_method_id character varying(255) NOT NULL,
    health_home_garbage_method_description character varying(255)
);
INSERT INTO f_health_home_garbage_method VALUES ('1', 'ฝัง');
INSERT INTO f_health_home_garbage_method VALUES ('2', 'เผา');
INSERT INTO f_health_home_garbage_method VALUES ('3', 'หมักทำปุ๋ย');
INSERT INTO f_health_home_garbage_method VALUES ('4', 'ส่งไปกำจัดที่อื่น');

CREATE TABLE f_health_home_pet_sex (
    f_health_home_pet_sex_id character varying(255) NOT NULL,
    health_home_pet_sex_description character varying(255)
);
INSERT INTO f_health_home_pet_sex VALUES ('0', 'ไม่ระบุ');
INSERT INTO f_health_home_pet_sex VALUES ('1', 'ผู้');
INSERT INTO f_health_home_pet_sex VALUES ('2', 'เมีย');

CREATE TABLE f_health_home_water_type (
    f_health_home_water_type_id character varying(255) NOT NULL,
    health_home_water_type_description character varying(255),
    health_home_water_type_drink character varying(255),
    health_home_water_type_use character varying(255)
);
INSERT INTO f_health_home_water_type VALUES ('1', 'น้ำฝน', '1', '2');
INSERT INTO f_health_home_water_type VALUES ('2', 'น้ำประปา', '1', '2');
INSERT INTO f_health_home_water_type VALUES ('3', 'น้ำบาดาล ', '1', '2');
INSERT INTO f_health_home_water_type VALUES ('4', 'บ่อน้ำตื้น', '1', '2');
INSERT INTO f_health_home_water_type VALUES ('5', 'สระน้ำ แม่น้ำ', '1', '2');
INSERT INTO f_health_home_water_type VALUES ('6', 'น้ำบรรจุเสร็จ', '1', '0');

CREATE TABLE f_health_nutrition_level (
    f_health_nutrition_level_id character varying(255) NOT NULL,
    health_nutrition_level_description character varying(255)
);
INSERT INTO f_health_nutrition_level VALUES ('N', 'ปกติ');
INSERT INTO f_health_nutrition_level VALUES ('1', 'ระดับที่ 1');
INSERT INTO f_health_nutrition_level VALUES ('2', 'ระดับที่ 2');
INSERT INTO f_health_nutrition_level VALUES ('3', 'ระดับที่ 3');

CREATE TABLE f_health_postpartum_birth_place (
    f_health_postpartum_birth_place_id character varying(255) NOT NULL,
    health_postpartum_birth_place_description character varying(255) NOT NULL
);
INSERT INTO f_health_postpartum_birth_place VALUES ('1', 'โรงพยาบาล');
INSERT INTO f_health_postpartum_birth_place VALUES ('2', 'สถานีอนามัย');
INSERT INTO f_health_postpartum_birth_place VALUES ('3', 'บ้าน');
INSERT INTO f_health_postpartum_birth_place VALUES ('4', 'กลางทาง');
INSERT INTO f_health_postpartum_birth_place VALUES ('5', 'อื่นๆ');
INSERT INTO f_health_community_standard_type VALUES ('12', 'โรงเรียนสีขาว');
INSERT INTO f_health_community_standard_type VALUES ('20', 'สถานที่ทำงานน่าอยู่');
INSERT INTO f_health_community_standard_type VALUES ('30', 'โรงพยาบาลส่งเสริมสุขภาพ');
INSERT INTO f_health_community_standard_type VALUES ('31', 'โรงพยาบาลอนามัยแม่และเด็ก');
INSERT INTO f_health_community_standard_type VALUES ('40', 'Clean Food Good Taste');
INSERT INTO f_health_community_standard_type VALUES ('50', 'คุณภาพน้ำ');
INSERT INTO f_health_community_standard_type VALUES ('90', 'อื่นๆ ระบุ');

CREATE TABLE f_health_postpartum_episiotomy_type (
    f_health_postpartum_episiotomy_type_id character varying(255) NOT NULL,
    health_postpartum_episiotomy_type_description character varying(255)
);
INSERT INTO f_health_postpartum_episiotomy_type VALUES ('1', 'ปกติ');
INSERT INTO f_health_postpartum_episiotomy_type VALUES ('2', 'แผลมีเลือดซืม');
INSERT INTO f_health_postpartum_episiotomy_type VALUES ('3', 'แผลเป็นหนอง');
INSERT INTO f_health_postpartum_episiotomy_type VALUES ('4', 'ผิดปกติ อื่นๆ');

CREATE TABLE f_health_postpartum_givebirth_result (
    f_health_postpartum_givebirth_result_id character varying(255) NOT NULL,
    health_postpartum_givebirth_result_description character varying(255)
);
INSERT INTO f_health_postpartum_givebirth_result VALUES ('1', 'ปกติ(เกิดมีชีพ)');
INSERT INTO f_health_postpartum_givebirth_result VALUES ('2', 'แท้ง');
INSERT INTO f_health_postpartum_givebirth_result VALUES ('3', 'อื่นๆ');

CREATE TABLE f_health_postpartum_status_result (
    f_health_postpartum_status_result_id character varying(255) NOT NULL,
    health_postpartum_status_result_description character varying(255)
);
INSERT INTO f_health_postpartum_status_result VALUES ('1', 'ปกติ');
INSERT INTO f_health_postpartum_status_result VALUES ('2', 'ผิดปกติ');
INSERT INTO f_health_postpartum_status_result VALUES ('0', 'ไม่ได้รับตรวจ');

CREATE TABLE f_health_pp_activity (
    f_health_pp_activity_id character varying(255) NOT NULL,
    pp_activity_description character varying(255)
);

INSERT INTO f_health_pp_activity VALUES ('1', 'Good');
INSERT INTO f_health_pp_activity VALUES ('2', 'Weak');
INSERT INTO f_health_pp_activity VALUES ('0', 'ไม่ระบุ');

CREATE TABLE f_health_pp_asphyxia (
    f_health_pp_asphyxia_id character varying(255) NOT NULL,
    pp_asphyxia_description character varying(255)
);

INSERT INTO f_health_pp_asphyxia VALUES ('0', 'ไม่ระบุ');
INSERT INTO f_health_pp_asphyxia VALUES ('1', 'ขาด');
INSERT INTO f_health_pp_asphyxia VALUES ('2', 'ไม่ขาด');

CREATE TABLE f_health_pp_aspiration (
    f_health_pp_aspiration_id character varying(255) NOT NULL,
    pp_aspiration_description character varying(255)
);
INSERT INTO f_health_pp_aspiration VALUES ('1', 'Rubber bulb');
INSERT INTO f_health_pp_aspiration VALUES ('2', 'Electric Suction');
INSERT INTO f_health_pp_aspiration VALUES ('0', 'ไม่ระบุ');

CREATE TABLE f_health_pp_breathing_time (
    f_health_pp_breathing_time_id character varying(255) NOT NULL,
    pp_breathing_time_description character varying(255)
);
INSERT INTO f_health_pp_breathing_time VALUES ('1', 'At birth');
INSERT INTO f_health_pp_breathing_time VALUES ('2', 'After birth 1 min');
INSERT INTO f_health_pp_breathing_time VALUES ('3', 'After birth 2 min');
INSERT INTO f_health_pp_breathing_time VALUES ('4', 'After birth 3 min');
INSERT INTO f_health_pp_breathing_time VALUES ('0', 'ไม่ระบุ');

CREATE TABLE f_health_pp_color (
    f_health_pp_color_id character varying(255) NOT NULL,
    pp_color_description character varying(255)
);
INSERT INTO f_health_pp_color VALUES ('1', 'Pink');
INSERT INTO f_health_pp_color VALUES ('2', 'Blue');
INSERT INTO f_health_pp_color VALUES ('0', 'ไม่ระบุ');

CREATE TABLE f_health_pp_condition (
    f_health_pp_condition_id character varying(255) NOT NULL,
    pp_condition_description character varying(255)
);
INSERT INTO f_health_pp_condition VALUES ('1', 'Good');
INSERT INTO f_health_pp_condition VALUES ('2', 'Poor');
INSERT INTO f_health_pp_condition VALUES ('0', 'ไม่ระบุ');

CREATE TABLE f_health_pp_dead_cause (
    f_health_pp_dead_cause_id character varying(255) NOT NULL,
    pp_dead_cause_description character varying(255)
);
INSERT INTO f_health_pp_dead_cause VALUES ('0', 'ไม่ระบุ');
INSERT INTO f_health_pp_dead_cause VALUES ('2', 'การติดเชื้อ');
INSERT INTO f_health_pp_dead_cause VALUES ('5', 'ภาวะแทรกซ้อนหลังคลอด');
INSERT INTO f_health_pp_dead_cause VALUES ('1', 'ความพิการแต่กำเนิด');
INSERT INTO f_health_pp_dead_cause VALUES ('3', 'ภาวะแทรกซ้อนจากการตั้งครรภ์');
INSERT INTO f_health_pp_dead_cause VALUES ('4', 'ภาวะแทรกซ้อนขณะคลอด');

CREATE TABLE f_health_pp_dead_type (
    f_health_pp_dead_type_id character varying(255) NOT NULL,
    pp_dead_type_description character varying(255)
);
INSERT INTO f_health_pp_dead_type VALUES ('0', 'ไม่ระบุ');

CREATE TABLE f_health_pp_eye_drug (
    f_health_pp_eye_drug_id character varying(255) NOT NULL,
    pp_eye_drug_description character varying(255)
);
INSERT INTO f_health_pp_eye_drug VALUES ('0', 'ไม่ระบุ');
INSERT INTO f_health_pp_eye_drug VALUES ('1', 'Terramycin Ointment');

CREATE TABLE f_health_pp_state (
    f_health_pp_state_id character varying(255) NOT NULL,
    pp_state_description character varying(255)
);
INSERT INTO f_health_pp_state VALUES ('1', 'มีชีวิต');
INSERT INTO f_health_pp_state VALUES ('3', 'ตายอายุ 0-7 วัน');
INSERT INTO f_health_pp_state VALUES ('2', 'เกิดไร้ชีพ');
INSERT INTO f_health_pp_state VALUES ('0', 'ไม่ระบุ');

CREATE TABLE f_health_pp_vitk (
    f_health_pp_vitk_id character varying(255) NOT NULL,
    pp_vitk_description character varying(255)
);
INSERT INTO f_health_pp_vitk VALUES ('0', 'ไม่ระบุ');
INSERT INTO f_health_pp_vitk VALUES ('1', 'ได้รับ');
INSERT INTO f_health_pp_vitk VALUES ('2', 'ไม่ได้รับ');

CREATE TABLE f_health_pregnancy_birth_doctor_type (
    f_health_pregnancy_birth_doctor_type_id character varying(255) NOT NULL,
    health_pregnancy_birth_doctor_type_description character varying(255)
);
INSERT INTO f_health_pregnancy_birth_doctor_type VALUES ('1', 'แพทย์');
INSERT INTO f_health_pregnancy_birth_doctor_type VALUES ('2', 'พยาบาล');
INSERT INTO f_health_pregnancy_birth_doctor_type VALUES ('3', 'จนท สส.');
INSERT INTO f_health_pregnancy_birth_doctor_type VALUES ('4', 'ผด.โบราณ');
INSERT INTO f_health_pregnancy_birth_doctor_type VALUES ('5', 'คลอดเอง');
INSERT INTO f_health_pregnancy_birth_doctor_type VALUES ('6', 'อื่นๆ');

CREATE TABLE f_health_pregnancy_birth_high_risk (
    f_health_pregnancy_birth_high_risk_id character varying(255) NOT NULL,
    health_pregnancy_birth_high_risk_description character varying(255)
);
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('01', 'ตั้งครรภ์อายุน้อยกว่า 17 ปี');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('02', 'ตั้งครรภ์อายุมากกว่า 35 ปี');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('03', 'ประวัติเคยคลอดก่อนกำหนด');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('04', 'ครรภ์แรกหรือครรภ์ที่ 4 ขึ้นไป');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('05', 'เคยคลอดลูกนน.น้อยกว่า 2500 กรัมหรือน้ำหนักมากกว่า 4000 กรัม');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('06', 'เคยผ่าตัดที่มดลูก');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('07', 'มีประวัติเป็นโรคหัวใจ');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('08', 'เคยมีประวัติแท้งมากว่า 3 ครั้งขึ้นไป');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('09', 'เป็นโรคโลหิติจาง(Hb<11gms Hct <33%)');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('10', 'VDRL +ve');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('11', 'UA alb+ve,sugar+ve');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('12', 'Hypertension(>140/90mmHg)');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('13', 'ต่อมไทรอยด์โต');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('14', 'โรคหัวใจ');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('15', 'ขนาดมดลูกไม่สัมพันธ์กับอายุครรภ์');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('16', 'ครรภ์แฝด');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('17', 'ทารกท่าผิดปกติ(ไม่ใช่ท่าศีรษะ เมื่ออายุครรภ์ >34 สัปดาห์ ');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('18', 'เลือดออกขณะตั้งครรภ์');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('19', 'ตั้งครรภ์เกิน 40 สัปดาห์');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('20', 'น้ำหนักขึ้นน้อยกว่า 1 กก./เดือนตั้งแต่อายุครรภ์ 24 สัปดาห์ขึ้นไป');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('21', 'เด็กดิ้นน้อยกว่า 10 ครั้ง/วัน ตั้งแต่อายุครรภ์ 32 สัปดาห์ขึ้นไป');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('22', 'ผลตรวจเลือดตับอักเสบเป็นบวก');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('23', 'ผลการคัดกรองธาลาสฃิเมียเป็นบวก');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('24', 'อื่นๆ');
INSERT INTO f_health_pregnancy_birth_high_risk VALUES ('25', 'เบาหวานในภาวะตั้งครรภ์');

CREATE TABLE f_health_pregnancy_conduct (
    f_health_pregnancy_conduct_id character varying(255) NOT NULL,
    health_pregnancy_conduct_description character varying(255)
);
INSERT INTO f_health_pregnancy_conduct VALUES ('1', 'HF');
INSERT INTO f_health_pregnancy_conduct VALUES ('2', 'HE');
INSERT INTO f_health_pregnancy_conduct VALUES ('3', 'HPE');
INSERT INTO f_health_pregnancy_conduct VALUES ('0', 'ไม่ระบุ');

CREATE TABLE f_health_pregnancy_posture_baby_status (
    f_health_pregnancy_posture_baby_status_id character varying(255) NOT NULL,
    health_pregnancy_posture_baby_status_description character varying(255)
);
INSERT INTO f_health_pregnancy_posture_baby_status VALUES ('1', 'LOA');
INSERT INTO f_health_pregnancy_posture_baby_status VALUES ('2', 'ROA');
INSERT INTO f_health_pregnancy_posture_baby_status VALUES ('3', 'OPP');
INSERT INTO f_health_pregnancy_posture_baby_status VALUES ('4', 'Transverse');
INSERT INTO f_health_pregnancy_posture_baby_status VALUES ('5', 'Breech');
INSERT INTO f_health_pregnancy_posture_baby_status VALUES ('6', 'Foot');
INSERT INTO f_health_pregnancy_posture_baby_status VALUES ('9', 'ไม่ทราบ');

CREATE TABLE f_health_pregnancy_pregnant_level (
    f_health_pregnancy_pregnant_level_id character varying(255) NOT NULL,
    health_pregnancy_pregnant_level_description character varying(255)
);
INSERT INTO f_health_pregnancy_pregnant_level VALUES ('0', 'ปกติ');
INSERT INTO f_health_pregnancy_pregnant_level VALUES ('1', 'ระดับที่ 1');
INSERT INTO f_health_pregnancy_pregnant_level VALUES ('2', 'ระดับที่ 2');
INSERT INTO f_health_pregnancy_pregnant_level VALUES ('3', 'ระดับที่ 3');
INSERT INTO f_health_pregnancy_pregnant_level VALUES ('4', 'ระดับที่ 4');
INSERT INTO f_health_pregnancy_pregnant_level VALUES ('5', 'ระดับที่ 5');
INSERT INTO f_health_pregnancy_pregnant_level VALUES ('6', 'ระดับที่ 6');

CREATE TABLE f_health_pregnancy_uterus_level (
    f_health_pregnancy_uterus_level_id character varying(255) NOT NULL,
    health_pregnancy_uterus_level_description character varying(255)
);
INSERT INTO f_health_pregnancy_uterus_level VALUES ('1', 'SP');
INSERT INTO f_health_pregnancy_uterus_level VALUES ('2', '1/3 > SP');
INSERT INTO f_health_pregnancy_uterus_level VALUES ('3', '2/3 > SP');
INSERT INTO f_health_pregnancy_uterus_level VALUES ('4', 'สะดือ');
INSERT INTO f_health_pregnancy_uterus_level VALUES ('5', '1/4 > สะดือ');
INSERT INTO f_health_pregnancy_uterus_level VALUES ('6', '2/4 > สะดือ');
INSERT INTO f_health_pregnancy_uterus_level VALUES ('7', '3/4 > สะดือ');
INSERT INTO f_health_pregnancy_uterus_level VALUES ('0', 'ไม่ระบุ');

CREATE TABLE f_health_school_service_type (
    f_health_school_service_type_id character varying(255) NOT NULL,
    health_school_service_type_description character varying(255)
);
INSERT INTO f_health_school_service_type VALUES ('10', 'เหา');
INSERT INTO f_health_school_service_type VALUES ('01', 'ตรวจสุขภาพ');
INSERT INTO f_health_school_service_type VALUES ('02', 'ตรวจสายตา');
INSERT INTO f_health_school_service_type VALUES ('03', 'ตรวจการได้ยิน');
INSERT INTO f_health_school_service_type VALUES ('04', 'โภชนาการ');
INSERT INTO f_health_school_service_type VALUES ('05', 'คอพอก');
INSERT INTO f_health_school_service_type VALUES ('06', 'โลหิตจางขาดธาตุเหล็ก');
INSERT INTO f_health_school_service_type VALUES ('07', 'ทันตกรรม');
INSERT INTO f_health_school_service_type VALUES ('08', 'หนอนพยาธิ');
INSERT INTO f_health_school_service_type VALUES ('09', 'ธาลาสซิเมีย');
INSERT INTO f_health_school_service_type VALUES ('20', 'อื่นๆ');
INSERT INTO f_health_school_service_type VALUES ('18', 'สมรรถภาพร่างกาย');

drop table f_patient_religion;
CREATE TABLE f_patient_religion (
    f_patient_religion_id character varying(255) NOT NULL,
    patient_religion_description character varying(255)
);
INSERT INTO f_patient_religion VALUES ('1', 'พุทธ');
INSERT INTO f_patient_religion VALUES ('2', 'อิสลาม');
INSERT INTO f_patient_religion VALUES ('3', 'คริสต์');
INSERT INTO f_patient_religion VALUES ('4', 'ฮินดู');
INSERT INTO f_patient_religion VALUES ('5', 'ขงจื้อ');

CREATE TABLE t_health_agr (
    t_health_agr_id character varying(255) NOT NULL,
    t_health_village_id character varying(255),
    agr_number character varying(255),
    agr_name character varying(255),
    agr_staff_record character varying(255),
    agr_staff_modify character varying(255),
    agr_staff_cancel character varying(255),
    agr_record_date_time character varying(255),
    agr_modify_date_time character varying(255),
    agr_cancel_date_time character varying(255),
    agr_close character varying(255),
    agr_close_date_time character varying(255),
    agr_close_note character varying(255),
    agr_active character varying(255)
);

CREATE TABLE t_health_agr_history (
    t_health_agr_history_id character varying(255) NOT NULL,
    t_health_agr_id character varying(255),
    agr_history_register character varying(255),
    agr_history_group character varying(255),
    agr_history_type character varying(255),
    agr_history_start_year character varying(255),
    agr_history_home_number character varying(255),
    agr_history_moo character varying(255),
    agr_history_road character varying(255),
    agr_history_phone character varying(255),
    agr_history_tambol character varying(255),
    agr_history_amphur character varying(255),
    agr_history_changwat character varying(255),
    agr_history_chairman_name character varying(255),
    agr_history_man_member character varying(255),
    agr_history_woman_member character varying(255),
    agr_history_capital character varying(255),
    agr_history_standard_type character varying(255),
    agr_history_standard_type_description character varying(255),
    agr_history_standard character varying(255),
    agr_history_time_of_pass character varying(255),
    agr_history_date_of_pass character varying(255),
    agr_history_period_of_pass character varying(255),
    agr_history_staff_record character varying(255),
    agr_history_staff_modify character varying(255),
    agr_history_record_date_time character varying(255),
    agr_history_modify_date_time character varying(255)
);

CREATE TABLE t_health_anc (
    t_health_anc_id character varying(255) NOT NULL,
    health_anc_vn character varying(255),
    t_health_pregnancy_id character varying(255),
    f_health_anc_section character varying(255),
    health_anc_gravida_week character varying(255),
    health_anc_exam character varying(255),
    f_health_pregnancy_birth_high_risk_id character varying(255),
    health_anc_vdrl character varying(255),
    health_anc_thalassemia character varying(255),
    health_anc_hb character varying(255),
    health_anc_hiv character varying(255),
    health_anc_hct character varying(255),
    health_anc_hct_date character varying(255),
    health_anc_ancres character varying(255),
    health_anc_tt character varying(255),
    health_anc_dental_exam character varying(255),
    health_anc_dental_gum character varying(255),
    health_anc_dental_tartar character varying(255),
    health_anc_dental_caries character varying(255),
    health_anc_weight character varying(255),
    health_anc_high character varying(255),
    health_anc_bmi character varying(255),
    health_anc_notice character varying(3000),
    t_visit_id character varying(255),
    t_patient_id character varying(255),
    health_anc_hn character varying(255),
    health_anc_staff_record character varying(255),
    health_anc_staff_modify character varying(255),
    health_anc_staff_cancel character varying(255),
    record_date_time character varying(255),
    modify_date_time character varying(255),
    cancel_date_time character varying(255),
    health_anc_active character varying(255),
    health_anc_delivery_status character varying(255)
);

CREATE TABLE t_health_anc_detail (
    t_health_anc_detail_id character varying(255) NOT NULL,
    t_patient_id character varying(255),
    t_visit_id character varying(255),
    t_health_pregnancy_id character varying(255),
    health_anc_detail_sugar character varying(255),
    health_anc_detail_cramp character varying(255),
    health_anc_detail_headache character varying(255),
    health_anc_detail_fundus_height character varying(255),
    health_anc_detail_fetal_movement character varying(255),
    health_anc_detail_risk character varying(255),
    health_anc_detail_vaginal_breeding character varying(255),
    health_anc_detail_thyroid character varying(255),
    health_anc_detail_heart_disease character varying(255),
    health_anc_detail_edema character varying(255),
    health_anc_detail_fetal_heart_sound character varying(255),
    health_anc_detail_urine_alblumin character varying(255),
    health_anc_detail_albumin character varying(255),
    f_health_pregnancy_posture_baby_status_id character varying(255),
    health_anc_detail_nausea character varying(255),
    f_health_pregnancy_conduct_id character varying(255),
    health_anc_detail_vaginal_discharge character varying(255),
    health_anc_detail_staff_record character varying(255),
    health_anc_detail_staff_modify character varying(255),
    health_anc_detail_staff_cancel character varying(255),
    record_date_time character varying(255),
    modify_date_time character varying(255),
    cancel_date_time character varying(255),
    t_health_anc_id character varying(255),
    health_anc_detail_active character varying(255)    
);

CREATE TABLE t_health_check_body (
    t_health_check_body_id character varying(255) NOT NULL,
    t_health_student_id character varying(255),
    t_health_visit_school_id character varying(255),
    f_answer_id character varying(255),
    f_answer_check_body_id character varying(255),
    b_visit_refer_office_id character varying(255),
    check_body_note character varying(255),
    check_body_remark character varying(255),
    check_body_record_time character varying(255),
    check_body_modify_time character varying(255),
    check_body_cancle_time character varying(255),
    check_body_staff_record character varying(255),
    check_body_staff_modify character varying(255),
    check_body_staff_cancle character varying(255),
    check_body_active character varying(255)
);

CREATE TABLE t_health_check_ears (
    t_health_check_ears_id character varying(255) NOT NULL,
    t_health_student_id character varying(255),
    t_health_visit_school_id character varying(255),
    f_answer_id character varying(255),
    f_health_check_ears_id character varying(255),
    check_ears_note character varying(255),
    check_ears_remark character varying(255),
    check_ears_record_time character varying(255),
    check_ears_modify_time character varying(255),
    check_ears_cancle_time character varying(255),
    check_ears_staff_record character varying(255),
    check_ears_staff_modify character varying(255),
    check_ears_staff_cancle character varying(255),
    check_ears_active character varying(255),
    b_visit_refer_office_id character varying(255)
);

CREATE TABLE t_health_check_eyes (
    t_health_check_eyes_id character varying(255) NOT NULL,
    t_health_student_id character varying(255),
    t_health_visit_school_id character varying(255),
    f_answer_id character varying(255),
    f_health_check_eyes_id character varying(255),
    check_eyes_note character varying(255),
    check_eyes_remark character varying(255),
    check_eyes_record_time character varying(255),
    check_eyes_modify_time character varying(255),
    check_eyes_cancle_time character varying(255),
    check_eyes_staff_record character varying(255),
    check_eyes_staff_modify character varying(255),
    check_eyes_staff_cancle character varying(255),
    check_eyes_active character varying(255),
    b_visit_refer_office_id character varying(255)
);

CREATE TABLE t_health_check_fe_blood (
    t_health_check_fe_blood_id character varying(255) NOT NULL,
    t_health_student_id character varying(255),
    t_health_visit_school_id character varying(255),
    f_answer_id character varying(255),
    f_answer_check_fe_blood_id character varying(255),
    b_visit_refer_office_id character varying(255),
    check_fe_blood_note character varying(255),
    check_fe_blood_remark character varying(255),
    check_fe_blood_record_time character varying(255),
    check_fe_blood_modify_time character varying(255),
    check_fe_blood_cancle_time character varying(255),
    check_fe_blood_staff_record character varying(255),
    check_fe_blood_staff_modify character varying(255),
    check_fe_blood_staff_cancle character varying(255),
    check_fe_blood_active character varying(255)
);

CREATE TABLE t_health_check_goiter (
    t_health_check_goiter_id character varying(255) NOT NULL,
    t_health_student_id character varying(255),
    t_health_visit_school_id character varying(255),
    f_answer_id character varying(255),
    f_answer_check_goiter_id character varying(255),
    b_visit_refer_office_id character varying(255),
    check_goiter_note character varying(255),
    check_goiter_remark character varying(255),
    check_goiter_record_time character varying(255),
    check_goiter_modify_time character varying(255),
    check_goiter_cancle_time character varying(255),
    check_goiter_staff_record character varying(255),
    check_goiter_staff_modify character varying(255),
    check_goiter_staff_cancle character varying(255),
    check_goiter_active character varying(255)
);

CREATE TABLE t_health_check_health_year (
    t_health_check_health_year_id character varying(255) NOT NULL,
    b_health_check_health_year_activity_id character varying(255),
    check_health_year_result character varying(255),
    check_health_year_remark character varying(255),
    check_health_year_record_time character varying(255),
    check_health_year_modify_time character varying(255),
    check_health_year_cancle_time character varying(255),
    check_health_year_staff_record character varying(255),
    check_health_year_staff_modify character varying(255),
    check_health_year_staff_cancle character varying(255),
    check_health_year_active character varying(255),
    t_visit_id character varying(255),
    t_patient_id character varying(255)
);

CREATE TABLE t_health_check_lice (
    t_health_check_lice_id character varying(255) NOT NULL,
    t_health_student_id character varying(255),
    t_health_visit_school_id character varying(255),
    f_answer_id character varying(255),
    f_answer_check_lice_id character varying(255),
    b_visit_refer_office_id character varying(255),
    check_lice_note character varying(255),
    check_lice_remark character varying(255),
    check_lice_record_time character varying(255),
    check_lice_modify_time character varying(255),
    check_lice_cancle_time character varying(255),
    check_lice_staff_record character varying(255),
    check_lice_staff_modify character varying(255),
    check_lice_staff_cancle character varying(255),
    check_lice_active character varying(255)
);

CREATE TABLE t_health_check_nutrition (
    t_health_check_nutrition_id character varying(255) NOT NULL,
    t_health_student_id character varying(255),
    t_health_visit_school_id character varying(255),
    f_answer_id character varying(255),
    f_health_nutrition_level_id character varying(255),
    b_visit_refer_office_id character varying(255),
    check_nutrition_note character varying(255),
    check_nutrition_remark character varying(255),
    check_nutrition_record_time character varying(255),
    check_nutrition_modify_time character varying(255),
    check_nutrition_cancle_time character varying(255),
    check_nutrition_staff_record character varying(255),
    check_nutrition_staff_modify character varying(255),
    check_nutrition_staff_cancle character varying(255),
    check_nutrition_active character varying(255)
);

CREATE TABLE t_health_check_other (
    t_health_check_other_id character varying(255) NOT NULL,
    t_health_student_id character varying(255),
    t_health_visit_school_id character varying(255),
    f_answer_id character varying(255),
    b_visit_refer_office_id character varying(255),
    check_other_note character varying(255),
    check_other_remark character varying(255),
    check_other_record_time character varying(255),
    check_other_modify_time character varying(255),
    check_other_cancle_time character varying(255),
    check_other_staff_record character varying(255),
    check_other_staff_modify character varying(255),
    check_other_staff_cancle character varying(255),
    check_other_active character varying(255)
);

CREATE TABLE t_health_check_student_dental (
    t_health_check_student_dental_id character varying(255) NOT NULL,
    t_health_student_id character varying(255),
    t_health_visit_school_id character varying(255),
    f_answer_id character varying(255),
    check_student_dental_num_tooth character varying(255),
    check_student_dental_num_bad_tooth character varying(255),
    check_student_dental_num_milktooth character varying(255),
    check_student_dental_num_bad_milktooth character varying(255),
    f_health_gum_level_id character varying(255),
    b_visit_refer_office_id character varying(255),
    check_student_dental_note character varying(255),
    check_student_dental_remark character varying(255),
    check_student_dental_record_time character varying(255),
    check_student_dental_modify_time character varying(255),
    check_student_dental_cancle_time character varying(255),
    check_student_dental_staff_record character varying(255),
    check_student_dental_staff_modify character varying(255),
    check_student_dental_staff_cancle character varying(255),
    check_student_dental_active character varying(255)
);

CREATE TABLE t_health_check_student_health (
    t_health_check_student_health_id character varying(255) NOT NULL,
    t_health_student_id character varying(255),
    t_health_visit_school_id character varying(255),
    f_answer_id character varying(255),
    f_answer_check_student_health_id character varying(255),
    b_visit_refer_office_id character varying(255),
    check_student_health_tall character varying(255),
    check_student_health_weight character varying(255),
    check_student_health_note character varying(255),
    check_student_health_remark character varying(255),
    check_student_health_record_time character varying(255),
    check_student_health_modify_time character varying(255),
    check_student_health_cancle_time character varying(255),
    check_student_health_staff_record character varying(255),
    check_student_health_staff_modify character varying(255),
    check_student_health_staff_cancle character varying(255),
    check_student_health_active character varying(255)
);

CREATE TABLE t_health_check_thalassemia (
    t_health_check_thalassemia_id character varying(255) NOT NULL,
    t_health_student_id character varying(255),
    t_health_visit_school_id character varying(255),
    f_answer_id character varying(255),
    f_answer_check_thalassemia_id character varying(255),
    b_visit_refer_office_id character varying(255),
    check_thalassemia_note character varying(255),
    check_thalassemia_remark character varying(255),
    check_thalassemia_record_time character varying(255),
    check_thalassemia_modify_time character varying(255),
    check_thalassemia_cancle_time character varying(255),
    check_thalassemia_staff_record character varying(255),
    check_thalassemia_staff_modify character varying(255),
    check_thalassemia_staff_cancle character varying(255),
    check_thalassemia_active character varying(255)
);

CREATE TABLE t_health_check_worm (
    t_health_check_worm_id character varying(255) NOT NULL,
    t_health_student_id character varying(255),
    t_health_visit_school_id character varying(255),
    f_answer_id character varying(255),
    f_answer_check_worm_id character varying(255),
    b_visit_refer_office_id character varying(255),
    check_worm_note character varying(255),
    check_worm_remark character varying(255),
    check_worm_record_time character varying(255),
    check_worm_modify_time character varying(255),
    check_worm_cancle_time character varying(255),
    check_worm_staff_record character varying(255),
    check_worm_staff_modify character varying(255),
    check_worm_staff_cancle character varying(255),
    check_worm_active character varying(255)
);

CREATE TABLE t_health_company (
    t_health_company_id character varying(255) NOT NULL,
    t_health_village_id character varying(255),
    company_number character varying(255),
    company_name character varying(255),
    company_staff_record character varying(255),
    company_staff_modify character varying(255),
    company_staff_cancel character varying(255),
    company_record_date_time character varying(255),
    company_modify_date_time character varying(255),
    company_cancel_date_time character varying(255),
    company_active character varying(255),
    company_close character varying(255),
    company_close_date_time character varying(255),
    company_close_note character varying(255)
);

CREATE TABLE t_health_company_history (
    t_health_company_history_id character varying(255) NOT NULL,
    t_health_company_id character varying(255),
    company_amount_man character varying(255),
    company_amount_woman character varying(255),
    company_history_staff_record character varying(255),
    company_history_record_date_time character varying(255),
    f_health_register_id character varying(255),
    b_health_company_type_id character varying(255),
    company_owner_name character varying(255),
    company_history_home_number character varying(255),
    company_history_moo character varying(255),
    company_history_road character varying(255),
    company_history_phone character varying(255),
    company_history_tambol character varying(255),
    company_history_amphur character varying(255),
    company_history_changwat character varying(255),
    company_history_company_co character varying(255),
    company_history_company_co_date_time character varying(255),
    f_health_community_standard_type_id character varying(255),
    company_history_standard_type_description character varying(255),
    f_health_community_standard_id character varying(255),
    company_history_time_of_pass character varying(255),
    company_history_date_of_pass character varying(255),
    company_history_period_of_pass character varying(255),
    company_history_in_market character varying(255),
    company_history_staff_modify character varying(255),
    company_history_modify_date_time character varying(255)
);

CREATE TABLE t_health_counsel (
    t_health_counsel_id character varying(255) NOT NULL,
    b_health_counsel_type_id character varying(255),
    counsel_detail character varying(255),
    counsel_remark character varying(255),
    counsel_record_time character varying(255),
    counsel_modify_time character varying(255),
    counsel_cancle_time character varying(255),
    counsel_staff_record character varying(255),
    counsel_staff_modify character varying(255),
    counsel_staff_cancle character varying(255),
    counsel_active character varying(255),
    t_visit_id character varying(255),
    t_patient_id character varying(255)
);

CREATE TABLE t_health_delivery (
    t_health_delivery_id character varying(255) NOT NULL,
    t_visit_id character varying(255),
    t_patient_id character varying(255),
    gravida_number character varying(255),
    health_delivery_born_date character varying(255),
    b_icd10_id character varying(255),
    f_health_postpartum_birth_place_id character varying(255),
    b_visit_office_birth_place character varying(255),
    f_health_postpartum_givebirth_result_id character varying(255),
    f_health_pregnancy_birth_doctor_type_id character varying(255),
    health_delivery_parity character varying(255),
    health_delivery_stillborn character varying(255),
    f_health_postpartum_status_result_id character varying(255),
    record_date_time character varying(255),
    health_delivery_mother_standard character varying(255),
    health_delivery_born_time character varying(255),
    abnormal_pregnancy_description character varying(3000),
    health_delivery_notice character varying(3000),
    health_delivery_active character varying(255),
    health_delivery_staff_record character varying(255),
    health_delivery_staff_modify character varying(255),
    health_delivery_staff_cancel character varying(255),
    update_date_time character varying(255),
    f_health_birth_method_id character varying(255),
    health_delivery_without_addressborn_center character varying(1000)
);

CREATE TABLE t_health_dental (
    t_health_dental_id character varying(255) NOT NULL,
    dental_num_tooth character varying(255),
    dental_num_bad_tooth character varying(255),
    dental_num_milktooth character varying(255),
    dental_num_bad_milktooth character varying(255),
    f_health_gum_level_id character varying(255),
    dental_detail character varying(255),
    dental_remark character varying(255),
    dental_record_time character varying(255),
    dental_modify_time character varying(255),
    dental_cancle_time character varying(255),
    dental_staff_record character varying(255),
    dental_staff_modify character varying(255),
    dental_staff_cancle character varying(255),
    dental_active character varying(255),
    t_visit_id character varying(255),
    t_patient_id character varying(255)
);

CREATE TABLE t_health_drug_history (
    t_health_drug_history_id character varying(255) NOT NULL,
    t_health_drug_id character varying(255),
    health_drug_history_tranfer character varying(255),
    health_drug_history_quantity character varying(255),
    health_drug_history_note character varying(3000),
    health_drug_history_staff_record character varying(255),
    record_date_time character varying(255)
);

CREATE TABLE t_health_drug_stock (
    t_health_drug_stock_id character varying(255) NOT NULL,
    b_visit_office_id character varying(255),
    b_item_id character varying(255),
    item_common_name character varying(255),
    health_drug_amount character varying(255),
    health_drug_staff_record character varying(255),
    health_drug_staff_modify character varying(255),
    health_drug_staff_cancel character varying(255),
    record_date_time character varying(255),
    modify_date_time character varying(255),
    cancel_date_time character varying(255),
    health_drug_active character varying(255)
);

CREATE TABLE t_health_efficiency (
    t_health_efficiency_id character varying(255) NOT NULL,
    efficiency_detail character varying(255),
    efficiency_remark character varying(255),
    efficiency_record_time character varying(255),
    efficiency_modify_time character varying(255),
    efficiency_cancle_time character varying(255),
    efficiency_staff_record character varying(255),
    efficiency_staff_modify character varying(255),
    efficiency_staff_cancle character varying(255),
    efficiency_active character varying(255),
    t_visit_id character varying(255),
    t_patient_id character varying(255)
);

CREATE TABLE t_health_epi (
    t_health_epi_id character varying(255) NOT NULL,
    t_patient_id character varying(255),
    health_epi_vn character varying(255),
    b_visit_office_id character varying(255),
    t_visit_id character varying(255),
    health_epi_hn character varying(255),
    health_epi_nextapp character varying(255),
    health_epi_notice character varying(3000),
    record_date_time character varying(255),
    modify_date_time character varying(255),
    cancel_date_time character varying(255),
    health_epi_staff_record character varying(255),
    health_epi_staff_modify character varying(255),
    health_epi_staff_cancel character varying(255),
    health_epi_active character varying(255)
);

CREATE TABLE t_health_epi_detail (
    t_health_epi_detail_id character varying(255) NOT NULL,
    t_health_epi_id character varying(255),
    b_health_epi_set_id character varying(255),
    health_epi_start character varying(255),
    health_epi_exp character varying(255),
    t_patient_id character varying(255),
    t_visit_id character varying(255),
    health_epi_detail_staff_record character varying(255),
    record_date_time character varying(255),
    health_epi_detail_active character varying(255)
);

CREATE TABLE t_health_epi_outsite (
    t_health_epi_outsite_id character varying(255) NOT NULL,
    t_health_epi_outsite_name character varying(255),
    b_epi_outsite_office_id character varying(255),
    epi_outsite_date character varying(255),
    epi_outsite_remark character varying(3000),
    t_patient_id character varying(255),
    epi_outsite_hn character varying(255),
    b_visit_office_id character varying(255),
    record_date_time character varying(255),
    modify_date_time character varying(255),
    cancel_date_time character varying(255),
    health_epi_outsite_staff_record character varying(255),
    health_epi_outsite_staff_modify character varying(255),
    health_epi_outsite_staff_cancel character varying(255),
    epi_outsite_active character varying(255)
);

CREATE TABLE t_health_family (
    t_health_family_id character varying(255) NOT NULL,
    t_health_home_id character varying(255),
    t_patient_id character varying(255),
    patient_hn character varying(255),
    t_health_family_number character varying(255),    
    patient_pid character varying(255),    
    f_prefix_id character varying(255),
    patient_name character varying(255),
    patient_last_name character varying(255),
    patient_birthday character varying(255),
    patient_birthday_true character varying(255),
    f_sex_id character varying(255),
    f_patient_marriage_status_id character varying(255),
    f_patient_education_type_id character varying(255),
    f_patient_occupation_id character varying(255),
    f_patient_nation_id character varying(255),
    f_patient_race_id character varying(255),
    f_patient_religion_id character varying(255),
    f_patient_family_status_id character varying(255),    
    patient_father_firstname character varying(255),
    patient_father_lastname character varying(255),
    patient_father_pid character varying(255),
    patient_mother_firstname character varying(255),
    patient_mother_lastname character varying(255),
    patient_mother_pid character varying(255),
    patient_couple_firstname character varying(255),
    patient_couple_lastname character varying(255),
    patient_couple_id character varying(255),
    patient_work_office character varying(255),
    f_patient_blood_group_id character varying(255),
    f_patient_area_status_id character varying(255),
    record_date_time character varying(255),
    modify_date_time character varying(255),
    cancel_date_time character varying(255),
    health_family_staff_record character varying(255),
    health_family_staff_modify character varying(255),
    health_family_staff_cancel character varying(255),
    health_family_active character varying(255),
    health_family_hn_hcis character varying(255)
);

CREATE TABLE t_health_family_planing (
    t_health_family_planing_id character varying(255) NOT NULL,
    t_visit_id character varying(255),
    t_patient_id character varying(255),
    f_health_family_planing_method_id character varying(255),
    f_health_family_planing_id character varying(255),
    health_family_planing_parity character varying(255),
    health_family_planing_staff_record character varying(255),
    record_date_time character varying(255),
    health_family_planing_vn character varying(255),
    health_family_planing_hn character varying(255),
    health_family_planing_breast_exam character varying(255),
    health_family_planing_cervix_exam character varying(255),
    health_famlily_planing_pregnant_exam character varying(255),
    b_health_family_planing_group_id character varying(255),
    health_famlily_planing_supply_qty character varying(255),
    health_famlily_planing_appointment character varying(255),
    health_family_planing_staff_update character varying(255),
    update_record_date_time character varying(255),
    health_family_planing_staff_remove character varying(255),
    health_family_planing_active character varying(255),
    health_family_planing_breast_exam_notice character varying(3000),
    health_family_planing_cervix_exam_notice character varying(3000),
    health_family_planing_notice character varying(3000),
    health_family_planing_order_status character varying(255) DEFAULT 0
);

CREATE TABLE t_health_grow (
    t_health_grow_id character varying(255) NOT NULL,
    t_patient_id character varying(255),
    health_grow_vn character varying(255),
    b_visit_office_id character varying(255),
    t_visit_id character varying(255),
    health_grow_hn character varying(255),
    record_date_time character varying(255),
    health_grow_staff_record character varying(255),
    health_grow_active character varying(255)
);

CREATE TABLE t_health_grow_history (
    t_health_grow_history_id character varying(255) NOT NULL,
    t_health_grow_id character varying(255),
    t_patient_id character varying(255),
    t_visit_id character varying(255),
    f_health_grow_id character varying(255),
    standard_grow_history_age character varying(255),
    real_grow_history_age character varying(255),
    health_grow_history_notice character varying(3000),
    record_date_time character varying(255),
    modify_date_time character varying(255),
    cancel_date_time character varying(255),
    health_grow_history_staff_record character varying(255),
    health_grow_history_staff_modify character varying(255),
    health_grow_history_staff_cancel character varying(255),
    health_grow_history_active character varying(255)
);

CREATE TABLE t_health_home (
    t_health_home_id character varying(255) NOT NULL,
    b_visit_office_id character varying(255),
    health_home_number character varying(255),
    health_home_house character varying(255),
    health_home_moo character varying(255),
    health_home_road character varying(255),
    health_home_tambon character varying(255),
    health_home_amphur character varying(255),
    health_home_changwat character varying(255),
    health_home_family character varying(255),
    health_home_responsible_zone character varying(255),
    health_home_volunteer character varying(255),
    health_home_volunteer_name character varying(255),
    health_home_owner character varying(255),
    health_home_owner_number character varying(255),
    f_health_home_charactor_id character varying(255),
    f_health_home_community_charac_id character varying(255),
    t_health_village_id character varying(255),
    home_staff_record character varying(255),
    home_staff_modify character varying(255),
    home_staff_cancel character varying(255),
    home_record_date_time character varying(255),
    home_modify_date_time character varying(255),
    home_cancel_date_time character varying(255),
    home_active character varying(255)
);
INSERT INTO t_health_home VALUES ('7140000000000','','','0','0','','','','','0','','','','','','','','7120000000000','','','','','','','1');


CREATE TABLE t_health_home_bug_control (
    t_health_home_bug_control_id character varying(255) NOT NULL,
    t_health_sub_home_id character varying(255),
    health_home_rat_control character varying(255),
    health_home_cockroach_control character varying(255),
    health_home_fly_control character varying(255),
    health_home_bug_control character varying(255),
    health_home_pet character varying(255),
    f_health_home_dung_control_id character varying(255),
    health_home_animal_control character varying(255)
);



CREATE TABLE t_health_home_food_standard (
    t_health_home_food_standard_id character varying(255) NOT NULL,
    t_health_sub_home_id character varying(255),
    health_home_mixture_food character varying(255),
    health_home_food_vessel character varying(255),
    health_home_food_cover character varying(255),
    health_home_food_wash character varying(255),
    health_home_food_keep character varying(255),
    health_home_kitchen_garbage character varying(255),
    health_home_kitchen_cleanness character varying(255),
    health_home_food_standard character varying(255),
    health_home_iodine character varying(255),
    health_home_salt_iodine character varying(255),
    health_home_product_iodine character varying(255)
);

CREATE TABLE t_health_home_herb (
    t_health_home_herb_id character varying(255) NOT NULL,
    t_health_home_id character varying(255),
    health_home_herb_name character varying(255),
    health_home_herb_use character varying(255),
    herb_staff_record character varying(255),
    herb_staff_modify character varying(255),
    herb_staff_cancel character varying(255),
    herb_record_date_time character varying(255),
    herb_modify_date_time character varying(255),
    herb_cancel_date_time character varying(255),
    herb_active character varying(255)
);

CREATE TABLE t_health_home_house_standard (
    t_health_home_house_standard_id character varying(255) NOT NULL,
    t_health_sub_home_id character varying(255),
    health_home_durability character varying(255),
    health_home_care character varying(255),
    health_home_light character varying(255),
    health_home_cleanness character varying(255),
    health_home_ventilation character varying(255),
    health_home_house_standard character varying(255)
);

CREATE TABLE t_health_home_pet (
    t_health_home_pet_id character varying(255) NOT NULL,
    t_health_home_id character varying(255),
    b_health_home_pet_type_id character varying(255),
    health_home_pet_name character varying(255),
    f_health_home_pet_sex_id character varying(255),
    health_home_pet_birthday character varying(255),
    health_home_pet_vaccine_lastdate character varying(255),
    health_home_pet_birth_control_lastdate character varying(255),
    pet_staff_record character varying(255),
    pet_staff_modify character varying(255),
    pet_staff_cancel character varying(255),
    pet_record_date_time character varying(255),
    pet_modify_date_time character varying(255),
    pet_cancel_date_time character varying(255),
    pet_active character varying(255)
);

CREATE TABLE t_health_home_water_eradicate (
    t_health_home_water_eradicate_id character varying(255) NOT NULL,
    t_health_sub_home_id character varying(255),
    f_health_home_water_type_id character varying(255),
    f_health_home_water_supply_id character varying(255),
    health_home_water character varying(255),
    health_home_water_supply character varying(255),
    health_home_toilet character varying(255),
    health_home_garbage character varying(255),
    health_home_water_eradicate character varying(255),
    f_health_home_garbage_method_id character varying(255)
);

CREATE TABLE t_health_market (
    t_health_market_id character varying(255) NOT NULL,
    t_health_village_id character varying(255),
    market_number character varying(255),
    market_name character varying(255),
    market_staff_record character varying(255),
    market_staff_modify character varying(255),
    market_staff_cancel character varying(255),
    market_record_date_time character varying(255),
    market_modify_date_time character varying(255),
    market_cancel_date_time character varying(255),
    market_close character varying(255),
    market_close_date_time character varying(255),
    market_close_note character varying(3000),
    market_active character varying(255)
);

CREATE TABLE t_health_market_history (
    t_health_market_history_id character varying(255) NOT NULL,
    t_health_market_id character varying(255),
    b_health_register_id character varying(255),
    b_health_market_type_id character varying(255),
    market_history_market_type_description character varying(255),
    b_health_market_type_lock_id character varying(255),
    market_history_market_type_lock_description character varying(255),
    market_history_home_number character varying(255),
    market_history_moo character varying(255),
    market_history_road character varying(255),
    market_history_phone character varying(255),
    market_history_tambol character varying(255),
    market_history_amphur character varying(255),
    market_history_changwat character varying(255),
    f_health_community_resorce_owner_id character varying(255),
    market_history_owner_description character varying(255),
    market_history_market_co character varying(255),
    market_history_market_co_date_time character varying(255),
    f_health_community_standard_type_id character varying(255),
    market_history_standard_type_description character varying(255),
    f_health_community_standard_id character varying(255),
    market_history_time_of_pass character varying(255),
    market_history_date_of_pass character varying(255),
    market_history_period_of_pass character varying(255),
    market_history_in_market character varying(255),
    market_history_staff_record character varying(255),
    market_history_staff_modify character varying(255),
    market_history_record_date_time character varying(255),
    market_history_modify_date_time character varying(255)
);

CREATE TABLE t_health_nutrition (
    t_health_nutrition_id character varying(255) NOT NULL,
    t_patient_id character varying(255),
    health_nutrition_vn character varying(255),
    b_visit_office_id character varying(255),
    t_visit_id character varying(255),
    health_nutrition_hn character varying(255),
    health_nutrition_age character varying(255),
    health_nutrition_rim character varying(255),
    health_nutrition_weight character varying(255),
    health_nutrition_high character varying(255),
    health_nutrition_newtooth character varying(255),
    health_nutrition_badtooth character varying(255),
    f_answer_id character varying(255),
    f_health_nutrition_level_id character varying(255),
    health_nutrition_bmi character varying(255),
    health_nutrition_result character varying(3000),
    health_nutrition_notice character varying(3000),
    health_nutrition_staff_record character varying(255),
    health_nutrition_staff_modify character varying(255),
    health_nutrition_staff_cancel character varying(255),
    record_date_time character varying(255),
    modify_date_time character varying(255),
    cancel_date_time character varying(255),
    health_nutrition_active character varying(255)
);

CREATE TABLE t_health_postpartum (
    t_health_postpartum_id character varying(255) NOT NULL,
    t_visit_id character varying(255),
    t_patient_id character varying(255),
    record_date_time character varying(255),
    health_postpartum_notice character varying(3000),
    health_postpartum_active character varying(255),
    health_postpartum_staff_record character varying(255),
    health_postpartum_staff_modify character varying(255),
    health_postpartum_staff_cancel character varying(255),
    health_postpartum_fundus_height character varying(255),
    health_postpartum_lactation character varying(255),
    health_postpartum_sugar_level character varying(255),
    health_postpartum_lochia character varying(255),
    health_postpartum_menstruation character varying(255),
    f_health_postpartum_episiotomy_type_id character varying(255),
    health_postpartum_nipple character varying(255),
    health_postpartum_albumin character varying(255),
    health_postpartum_summary_verify character varying(255),
    health_postpartum_visit character varying(255),
    t_health_delivery_id character varying(255),
    health_postpartum_asphyxia character varying(255),
    health_postpartum_vit_k character varying(255),
    health_postpartum_bcres character varying(255),
    update_date_time character varying(255),
    health_postpartum_appointment character varying(255),
    health_postpartum_symptom_notice character varying(3000),
    f_health_postpartum_birth_place_id character varying(255),
    health_postpartum_pregnant_number character varying(255)
);

CREATE TABLE t_health_pp (
    t_health_pp_id character varying(255) NOT NULL,
    t_patient_id character varying(255),
    t_visit_id character varying(255),
    pp_number character varying(255),
    pp_mother_patient_id character varying(255),
    pp_gravida character varying(255),
    pp_initial_condition character varying(255),
    pp_color character varying(255),
    pp_activity character varying(255),
    pp_breathing_time character varying(255),
    pp_crying_time character varying(255),
    pp_aspiration character varying(255),
    pp_weight character varying(255),
    pp_eye_drug character varying(255),
    pp_method_bearing character varying(255),
    pp_lost_oxygen character varying(255),
    pp_vit_k character varying(255),
    pp_resuscitation character varying(255),
    pp_diseases_abnormal character varying(255),
    pp_apgar_heart_rate_one_minute character varying(255),
    pp_apgar_respiration_effort_one_minute character varying(255),
    pp_apgar_muscle_tone_one_minute character varying(255),
    pp_apgar_reflex_irritability_one_minute character varying(255),
    pp_apgar_skin_color_one_minute character varying(255),
    pp_apgar_total_score_one_minute character varying(255),
    pp_apgar_heart_rate_five_minute character varying(255),
    pp_apgar_respiration_effort_five_minute character varying(255),
    pp_apgar_muscle_tone_five_minute character varying(255),
    pp_apgar_reflex_irritability_five_minute character varying(255),
    pp_apgar_skin_color_five_minute character varying(255),
    pp_apgar_total_score_five_minute character varying(255),
    pp_apgar_heart_rate_ten_minute character varying(255),
    pp_apgar_respiration_effort_ten_minute character varying(255),
    pp_apgar_muscle_tone_ten_minute character varying(255),
    pp_apgar_reflex_irritability_ten_minute character varying(255),
    pp_apgar_skin_color_ten_minute character varying(255),
    pp_apgar_total_score_ten_minute character varying(255),
    pp_fronto_occipital character varying(255),
    pp_length character varying(255),
    pp_chest character varying(255),
    pp_tempperature character varying(255),
    pp_state character varying(255),
    pp_dead_type character varying(255),
    pp_dead_cause character varying(255),
    pp_dead_date character varying(255),
    pp_dead_time character varying(255),
    pp_comment character varying(255),
    pp_staff_record character varying(255),
    pp_staff_modify character varying(255),
    pp_staff_cancel character varying(255),
    pp_record_date_time character varying(255),
    pp_modify_date_time character varying(255),
    pp_cancel_date_time character varying(255),
    pp_active character varying(255),
    pp_apgar_heart_rate_per_minute_one_minute character varying(255),
    pp_apgar_heart_rate_per_minute_five_minute character varying(255),
    pp_apgar_heart_rate_per_minute_ten_minute character varying(255)
);

CREATE TABLE t_health_pp_care (
    t_health_pp_care_id character varying(255) NOT NULL,
    t_patient_id character varying(255),
    t_visit_id character varying(255),
    pp_care_number character varying(255),
    pp_care_survey_place character varying(255),
    pp_care_deliver_place character varying(255),
    pp_care_mom_age character varying(255),
    pp_care_dermis character varying(255),
    pp_care_navel character varying(255),
    pp_care_feces character varying(255),
    pp_care_urine character varying(255),
    pp_care_next_appointment character varying(255),
    pp_care_health character varying(255),
    pp_care_result character varying(255),
    pp_care_state character varying(255),
    pp_care_comment character varying(255),
    pp_care_staff_record character varying(255),
    pp_care_staff_modify character varying(255),
    pp_care_staff_cancel character varying(255),
    pp_care_record_date_time character varying(255),
    pp_care_modify_date_time character varying(255),
    pp_care_cancel_date_time character varying(255),
    pp_care_active character varying(255)
);


CREATE TABLE t_health_pregnancy (
    t_health_pregnancy_id character varying(255) NOT NULL,
    health_pregnancy_hn character varying(255),
    health_pregnancy_vn character varying(255),
    b_visit_office_id character varying(255),
    health_pregnancy_gravida_number character varying(255),
    health_pregnancy_menses_issue_date character varying(255),
    health_pregnancy_menses_expire_date character varying(255),
    health_pregnancy_birthcontrol character varying(255),
    health_pregnancy_abnomal character varying(255),
    health_pregnancy_result character varying(3000),
    health_pregnancy_notice character varying(3000),
    health_pregnancy_staff_record character varying(255),
    health_pregnancy_staff_modify character varying(255),
    health_pregnancy_staff_cancel character varying(255),
    record_date_time character varying(255),
    modify_date_time character varying(255),
    cancel_date_time character varying(255),
    t_visit_id character varying(255),
    t_patient_id character varying(255),
    health_pregnancy_active character varying(255)
);

CREATE TABLE t_health_resource (
    t_health_resource_id character varying(255) NOT NULL,
    t_health_village_id character varying(255),
    resource_number character varying(255),
    resource_name character varying(255),
    resource_staff_record character varying(255),
    resource_staff_modify character varying(255),
    resource_staff_cancel character varying(255),
    resource_record_date_time character varying(255),
    resource_modify_date_time character varying(255),
    resource_cancel_date_time character varying(255),
    resource_active character varying(255)
);



CREATE TABLE t_health_resource_history (
    t_health_resource_history_id character varying(255) NOT NULL,
    t_health_resource_id character varying(255),
    resorce_history_owner_id character varying(255),
    resource_history_owner_description character varying(255),
    resorce_history_supervise_id character varying(255),
    resource_history_supervise_description character varying(255),
    resource_history_home_number character varying(255),
    resource_history_moo character varying(255),
    resource_history_road character varying(255),
    resource_history_phone character varying(255),
    resource_history_tambol character varying(255),
    resource_history_amphur character varying(255),
    resource_history_changwat character varying(255),
    resource_history_state character varying(255),
    f_health_community_standard_type_id character varying(255),
    resource_history_standard_type_description character varying(255),
    f_health_community_standard_id character varying(255),
    resource_history_time_of_pass character varying(255),
    resource_history_date_of_pass character varying(255),
    resource_history_period_of_pass character varying(255),
    resource_history_staff_record character varying(255),
    resource_history_staff_modify character varying(255),
    resource_history_record_date_time character varying(255),
    resource_history_modify_date_time character varying(255)
);

CREATE TABLE t_health_school (
    t_health_school_id character varying(255) NOT NULL,
    t_health_village_id character varying(255),
    school_number character varying(255),
    school_staff_record character varying(255),
    school_staff_modify character varying(255),
    school_staff_cancel character varying(255),
    school_record_date_time character varying(255),
    school_modify_date_time character varying(255),
    school_cancel_date_time character varying(255),
    school_active character varying(255),
    school_name character varying(255),
    school_close character varying(255),
    school_close_date_time character varying(255),
    school_close_note character varying(3000)
);

CREATE TABLE t_health_school_history (
    t_health_school_history_id character varying(255) NOT NULL,
    t_health_school_id character varying(255) NOT NULL,
    b_health_school_max_class_id character varying(255),
    f_health_community_resorce_owner_id character varying(255),
    f_health_community_standard_type_id character varying(255),
    f_health_community_standard_id character varying(255),
    school_history_time_of_pass character varying(255),
    school_history_date_of_pass character varying(255),
    school_history_period_of_pass character varying(255),
    school_history_amount_student_m character varying(255),
    school_history_amount_student_fm character varying(255),
    school_history_amount_teacher_m character varying(255),
    school_history_amount_teacher_fm character varying(255),
    school_history_staff_record character varying(255),
    school_history_record_date_time character varying(255),
    school_history_staff_modify character varying(255),
    school_history_modify_date_time character varying(255),
    school_history_owner_other character varying(255),
    school_history_standard_type_other character varying(255)
);

CREATE TABLE t_health_student (
    t_health_student_id character varying(255) NOT NULL,
    t_health_visit_school_id character varying(255),
    f_patient_prefix_id character varying(255),
    f_sex_id character varying(255),
    student_number character varying(255),
    student_pid character varying(255),
    student_firstname character varying(255),
    student_surname character varying(255),
    student_note character varying(255),
    student_record_time character varying(255),
    student_modify_time character varying(255),
    student_cancle_time character varying(255),
    student_staff_record character varying(255),
    student_staff_modify character varying(255),
    student_staff_cancle character varying(255),
    student_active character varying(255)
);

CREATE TABLE t_health_sub_home (
    t_health_sub_home_id character varying(255) NOT NULL,
    t_health_home_id character varying(255),
    sub_home_record_date_time character varying(255),
    health_sub_home_staff_record character varying(255),
    health_sub_home_staff_modify character varying(255),
    health_sub_home_staff_cancel character varying(255),
    sub_home_modify_date_time character varying(255),
    sub_home_cancel_date_time character varying(255),
    sub_home_active character varying(255)
);

CREATE TABLE t_health_temple (
    t_health_temple_id character varying(255) NOT NULL,
    t_health_village_id character varying(255),
    temple_number character varying(255),
    temple_name character varying(255),
    temple_staff_record character varying(255),
    temple_staff_modify character varying(255),
    temple_staff_cancel character varying(255),
    temple_record_date_time character varying(255),
    temple_modify_date_time character varying(255),
    temple_cancel_date_time character varying(255),
    temple_active character varying(255),
    temple_home_number character varying(255),
    temple_moo character varying(255),
    temple_road character varying(255),
    temple_phone character varying(255),
    temple_tambol character varying(255),
    temple_amphur character varying(255),
    temple_changwat character varying(255),
    temple_religion_type character varying(255),
    temple_type character varying(255)
);

CREATE TABLE t_health_temple_history (
    t_health_temple_history_id character varying(255) NOT NULL,
    t_health_temple_id character varying(255),
    temple_staff_record character varying(255),
    temple_staff_modify character varying(255),
    temple_record_date_time character varying(255),
    temple_modify_date_time character varying(255)
);

CREATE TABLE t_health_temple_history_detail (
    t_health_temple_history_detail_id character varying(255) NOT NULL,
    t_health_temple_history_id character varying(255),
    temple_personel character varying(255),
    temple_amount_personel character varying(255)
);

CREATE TABLE t_health_village (
    t_health_village_id character varying(255) NOT NULL,
    village_name character varying(255),
    village_moo character varying(255),
    b_health_village_location_id character varying(255),
    village_staff_record character varying(255),
    village_staff_modify character varying(255),
    village_staff_cancel character varying(255),
    village_record_date_time character varying(255),
    village_modify_date_time character varying(255),
    village_cancel_date_time character varying(255),
    village_active character varying(255),
    village_number character varying(255)
);
INSERT INTO t_health_village VALUES ('7120000000000','นอกเขต(ใช้บันทึกคนนอกเขตฯ)','0','','','','','','','','1','');

CREATE TABLE t_health_visit_home (
    t_health_visit_home_id character varying(255) NOT NULL,
    visit_home_problem character varying(255),
    visit_home_object character varying(255),
    visit_home_maintain character varying(255),
    visit_home_assess character varying(255),
    visit_home_plane character varying(255),
    visit_home_date character varying(255),
    visit_home_nextdate character varying(255),
    visit_home_remark character varying(255),
    visit_home_record_time character varying(255),
    visit_home_modify_time character varying(255),
    visit_home_cancle_time character varying(255),
    visit_home_staff_record character varying(255),
    visit_home_staff_modify character varying(255),
    visit_home_staff_cancle character varying(255),
    visit_home_active character varying(255),
    t_patient_id character varying(255)
);

CREATE TABLE t_health_visit_school (
    t_health_visit_school_id character varying(255) NOT NULL,
    t_health_school_id character varying(255),
    b_school_class_id character varying(255),
    visit_school_room character varying(255),
    f_health_school_service_type_id character varying(255),
    visit_school_term character varying(255),
    visit_school_other_service character varying(255),
    visit_school_service_date character varying(255),
    visit_school_record_time character varying(255),
    visit_school_modify_time character varying(255),
    visit_school_cancle_time character varying(255),
    visit_school_staff_record character varying(255),
    visit_school_staff_modify character varying(255),
    visit_school_staff_cancle character varying(255),
    visit_school_active character varying(255)
);

CREATE TABLE t_health_water (
    t_health_water_id character varying(255) NOT NULL,
    t_health_village_id character varying(255),
    water_number character varying(255),
    b_health_water_type_id character varying(255),
    water_construct_id character varying(255),
    water_staff_record character varying(255),
    water_staff_modify character varying(255),
    water_staff_cancel character varying(255),
    water_record_date_time character varying(255),
    water_modify_date_time character varying(255),
    water_cancel_date_time character varying(255),
    water_active character varying(255),
    water_supervise_id character varying(255),
    water_construct_description character varying(255),
    water_supervise_description character varying(255)
);

CREATE TABLE t_health_water_history (
    t_health_water_history_id character varying(255) NOT NULL,
    t_health_water_id character varying(255),
    f_health_water_state_id character varying(255),
    water_history_staff_record character varying(255),
    water_history_record_date_time character varying(255)
);

CREATE TABLE s_health_version (
    s_health_version_id character varying(255) NOT NULL,
    version_health_number character varying(255),
    version_health_description character varying(255),
    version_health_application_number character varying(255),
    version_health_database_number character varying(255),
    version_health_update_time character varying(255)
);
INSERT INTO s_health_version VALUES ('9710000000001', '1', 'Health, Community Edition', '0.01.1048', '0.01.1048', '2548-10-08 12:00:00');
------เตรียมที่ท้ายเหมือง-----------------------------------------------------------------------------------------0.01

UPDATE f_health_pp_aspiration SET f_health_pp_aspiration_id = '3' WHERE pp_aspiration_description = 'Electric Suction';
INSERT INTO f_health_pp_aspiration VALUES ('2', 'Nasograstric Tube');

drop table f_health_pp_activity;
drop table f_health_pp_asphyxia;
drop table f_health_pp_color;
drop table f_health_pp_condition;
drop table f_health_pp_vitk;

ALTER TABLE t_health_pp RENAME COLUMN pp_mother_patient_id TO pp_mother_pid;

INSERT INTO f_health_pp_dead_cause VALUES ('6', 'ตายเปื่อยยุ่ย');
INSERT INTO f_health_pp_dead_cause VALUES ('7', 'ขาดออกซิเจนขณะคลอด');

UPDATE f_health_postpartum_episiotomy_type SET f_health_postpartum_episiotomy_type_id = '5' WHERE health_postpartum_episiotomy_type_description = 'ผิดปกติ อื่นๆ';
INSERT INTO f_health_postpartum_episiotomy_type VALUES ('4', 'แผลแยก');

INSERT INTO f_health_pregnancy_conduct VALUES ('4', 'Breech');   
INSERT INTO f_health_postpartum_status_result VALUES ('3', 'รอฟังผลตรวจ');
alter table t_health_anc add  health_anc_pressure  varchar(255) default '';
alter table t_health_anc_detail add  health_anc_detail_heart_rate varchar(255) default '';
UPDATE f_health_pregnancy_pregnant_level SET f_health_pregnancy_pregnant_level_id = '7' WHERE health_pregnancy_pregnant_level_description = 'ระดับที่ 6';
UPDATE f_health_pregnancy_pregnant_level SET f_health_pregnancy_pregnant_level_id = '6' WHERE health_pregnancy_pregnant_level_description = 'ระดับที่ 5';
UPDATE f_health_pregnancy_pregnant_level SET f_health_pregnancy_pregnant_level_id = '5' WHERE health_pregnancy_pregnant_level_description = 'ระดับที่ 4';
UPDATE f_health_pregnancy_pregnant_level SET f_health_pregnancy_pregnant_level_id = '4' WHERE health_pregnancy_pregnant_level_description = 'ระดับที่ 3';
UPDATE f_health_pregnancy_pregnant_level SET f_health_pregnancy_pregnant_level_id = '3' WHERE health_pregnancy_pregnant_level_description = 'ระดับที่ 2';
UPDATE f_health_pregnancy_pregnant_level SET f_health_pregnancy_pregnant_level_id = '2' WHERE health_pregnancy_pregnant_level_description = 'ระดับที่ 1';
UPDATE f_health_pregnancy_pregnant_level SET f_health_pregnancy_pregnant_level_id = '1' WHERE health_pregnancy_pregnant_level_description = 'ปกติ';
INSERT INTO f_health_pregnancy_pregnant_level VALUES ('0', 'ไม่ได้รับตรวจ');

UPDATE f_health_abnormal SET f_health_abnormal_id = '8' WHERE health_abnormal_description = 'ริดสีดวงทวาร';
UPDATE f_health_abnormal SET f_health_abnormal_id = '7' WHERE health_abnormal_description = 'หลอดเลือดดำพอง';
UPDATE f_health_abnormal SET f_health_abnormal_id = '6' WHERE health_abnormal_description = 'ท้องผูก';
UPDATE f_health_abnormal SET f_health_abnormal_id = '5' WHERE health_abnormal_description = 'ตาพร่ามัว';
UPDATE f_health_abnormal SET f_health_abnormal_id = '4' WHERE health_abnormal_description = 'เวียนศรีษะ';
INSERT INTO f_health_abnormal VALUES ('2', 'คลื่นไส้');
INSERT INTO f_health_abnormal VALUES ('3', 'คลื่นไส้และอาเจียน');

alter table t_health_epi_detail add  health_epi_detail_lot varchar(255) default '';
alter table t_health_anc add  health_anc_exam_description varchar(255) default '';
alter table t_health_anc add  health_anc_no varchar(255) default '';
alter table t_health_anc_detail add  health_anc_detail_uteruscm varchar(255) default '';
alter table t_health_anc add  health_anc_gravida_day varchar(255) default '';

insert into s_script_update_log values ('pcu','update_pcu_ph2.sql',(select current_date) || ','|| (select current_time),'update PCU  -> ph2');

INSERT INTO s_health_version VALUES ('9710000000002', '2', 'Health, Community Edition', '0.02.1048', '0.02.1048', '2548-10-23 22:00:00');
------เตรียมที่ท้ายเหมือง-----------------------------------------------------------------------------------------0.02

