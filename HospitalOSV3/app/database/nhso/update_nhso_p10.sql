--21-01-08
--ton
--ตาราง t_nhso_family_offline
--เพิ่ม ตาราง t_nhso_family_offline
CREATE TABLE t_nhso_family_offline (
    t_nhso_family_offline_id character varying(255) NOT NULL,
    t_nhso_family_offline_pid character varying(255),
    t_nhso_family_offline_title character varying(255),
    t_nhso_family_offline_fname character varying(255),
    t_nhso_family_offline_lname character varying(255),
    t_nhso_family_offline_sex character varying(255),
    t_nhso_family_offline_birthdate character varying(255),
    t_nhso_family_offline_chat character varying(255),
    t_nhso_family_offline_address character varying(255),
    t_nhso_family_offline_status character varying(255),
    t_nhso_family_offline_update_date character varying(255),
    t_nhso_family_offline_age character varying(255),PRIMARY KEY (t_nhso_family_offline_id)
);

--21-01-08
--ton
--ตาราง t_nhso_payment_offline
--เพิ่ม ตาราง t_nhso_payment_offline
CREATE TABLE t_nhso_payment_offline (
    t_nhso_payment_offline_id character varying(255) NOT NULL,
    t_nhso_family_offline_id character varying(255),
    t_nhso_payment_offline_maininscl character varying(255),
    t_nhso_payment_offline_subinscl character varying(255),
    t_nhso_payment_offline_startdate character varying(255),
    t_nhso_payment_offline_expdate character varying(255),
    t_nhso_payment_offline_hmain character varying(255),
    t_nhso_payment_offline_hsub character varying(255),
    t_nhso_payment_offline_purchaseprovince character varying(255),
    t_nhso_payment_offline_primaryprovince character varying(255),
    t_nhso_payment_offline_cardid character varying(255),
    t_nhso_payment_offline_paidmodel character varying(255),	
    t_nhso_payment_offline_update_date character varying(255),
    t_nhso_payment_offline_period_date character varying(255),PRIMARY KEY (t_nhso_payment_offline_id)
);

insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13814','โรงพยาบาลศิริราช, 14971 (PCU)');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13781','โรงพยาบาลรามาธิบดี');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13756','โรงพยาบาลจุฬาลงกรณ์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('14173','โรงพยาบาลตำรวจ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11482','โรงพยาบาลภูมิพลอดุลยเดช พอ.บนอ.');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11478','โรงพยาบาลสมเด็จพระปิ่นเกล้า');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11481','โรงพยาบาลพระมงกุฏเกล้า');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11469','โรงพยาบาลเลิดสิน');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11470','โรงพยาบาลนพรัตนราชธานี');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11472','โรงพยาบาลราชวิถี');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11535','วิทยาลัยแพทยศาสตร์ กทม.และวชิรพยาบาล');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11537','โรงพยาบาลกลาง');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11541','โรงพยาบาลเจริญกรุงประชารักษ์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11539','โรงพยาบาลตากสิน');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11540','โรงพยาบาลหลวงพ่อทวีศักดิ์ ชุตินธิโร อุทิศ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('14641','โรงพยาบาลราชพิพัฒน์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15049','โรงพยาบาลสิรินธร');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11536','โรงพยาบาลหนองจอก');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11538','โรงพยาบาลลาดกระบัง กรุงเทพมหานคร');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11468','ทัณฑสถานโรงพยาบาลราชทัณฑ์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11583','โรงพยาบาลกล้วยน้ำไท');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11708','โรงพยาบาลบางมด ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11615','โรงพยาบาลเยาวรักษ์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11641','โรงพยาบาลศรีวิชัย 1');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11647','โรงพยาบาลพระราม 2');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11656','โรงพยาบาลเกษมราษฎร์บางแค');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11687','โรงพยาบาลเกษมราษฎร์ประชาชื่น');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11581','โรงพยาบาลกล้วยน้ำไท 2 ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11592','โรงพยาบาลบางนา 1 ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13807','โรงพยาบาลซังฮี้');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11552','โรงพยาบาลมเหสักข์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11770','โรงพยาบาลปิยะมินทร์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11750','โรงพยาบาลหัวเฉียว');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11621','โรงพยาบาลบางไผ่');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11595','โรงพยาบาลนวมินทร์ ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11668','โรงพยาบาลกรุงธน 2');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11667','โรงพยาบาลนวมินทร์ 2');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11703','โรงพยาบาลแพทย์ปัญญา');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('14908','โรงพยาบาลเกษมราษฎร์สุขาภิบาล 3');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11661','โรงพยาบาลศรีวิชัย 2');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11626','โรงพยาบาลคลองตัน');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11629','โรงพยาบาลเพชรเวช');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11786','โรงพยาบาลอนันต์พัฒนา 2');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11706','สถานพยาบาลบางขุนเทียน');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('14348','สถานพยาบาลบางปะกอก 5');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11652','สถานพยาบาลเพชรเกษม-บางแค');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15182','สถานพยาบาลบางมด 3');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15156','คลินิกเวชกรรมกล้วยน้ำไท สาขาชุมชน 70 ไร่ ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15157','คลินิกเวชกรรมคลองเตย');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22159','ธรรมสุนทรคลินิกเวชกรรม สาขาคลองสาน');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15158','คลินิกเวชกรรมเกษมราษฏร์ สาขาหทัยราษฎร์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22000','นวมินทร์คลินิกเวชกรรม สาขาเคซีรามอินทรา');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15195','คลินิกเวชกรรมเกษมราษฎร์ สาขาจตุจักร');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('20448','คลินิกเปรมประชาการแพทย์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21716','คลินิกเวชกรรมวิภาวดี ซอย 2');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22094','ทับสุวรรณคลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('16142','รื่นฤดีคลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('16161','ธรรมสุนทรคลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15142','ศรีวิชัยฉิมพลีคลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15160','คลินิกเวชกรรมเกษมราษฎร์ สาขาพุทธมณฑลสาย 2');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21337','คลินิกเวชกรรมอนันต์พัฒนา สาขาทวีวัฒนา');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15188','คลินิกเวชกรรมทุ่งครุ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15143','นวมินทร์ 2 เวชกรรมคลินิก');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('16391','ว.พ.คลินิกการแพทย์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15141','คลินิกแพทย์ปัญญา 2');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15190','คลินิกเวชกรรมแพทย์ปัญญา สาขารามคำแหง 37');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21710','นวมินทร์คลินิกเวชกรรม สาขารัตนบัณฑิต');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21647','มิตรชุมชนคลินิกเวชกรรม (สาขาลาดพร้าว 111)');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15144','เทียนทะเลคลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15145','บางขุนเทียน 2 คลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15196','เพชรทองคำคลินิกเวชกรรม (สาขาพระราม 2)');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15161','เรือพระร่วงคลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21839','คลินิกเวชกรรมเกษมราษฎร์ สาขาหลักสี่');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21664','สหคลินิกกล้วยน้ำไท สาขาตลาดยิ่งเจริญ22974');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22001','คลินิกเวชกรรมเพชรเกษม 102');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22005','คลินิกเวชกรรมเกษมราษฎร์ สาขาตลาดเตาปูน');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21820','สหคลินิกเกษมราษฎร์ สาขาบางซื่อ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22092','คลินิกเวชกรรมอนันต์พัฒนา สาขาบางซื่อ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15181','เตาปูนสหแพทย์คลินิก');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15162','คลินิกเวชกรรมลาซาล');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22007','คลินิกเวชกรรมพุทธบูชา (15183)');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22356','คลินิกสุขาภิบาล 1 เวชกรรม(15193)');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15146','บางบอนคลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('11675','คลินิกเวชกรรมอนันต์พัฒนา');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21338','คลินิกเวชกรรมอนันต์พัฒนา สาขาบางอ้อ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15187','สามย่านคลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15148','คลินิกแพทย์ศรีนครินทร์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15164','คลินิกเวชกรรมกล้วยน้ำไท สาขาสุภาพงษ์ 3   ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15165','คลินิกเวชกรรมปิยะมินทร์ สาขาราม 2');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15167','คลินิกเวชกรรมปิยะมินทร์ สาขาวัดตะกล่ำ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15163','สหคลินิกกล้วยน้ำไท สาขาราม 2');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15168','คลินิกเวชกรรมกล้วยน้ำไท สาขาสุขุมวิท 56');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15170','คลินิกเวชกรรมกล้วยน้ำไท สาขาสุขุมวิท 93');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15171','คลินิกเวชกรรมปิยะมินทร์ อุดมสุข');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15169','สหคลินิกกล้วยน้ำไท สาขาสุขุมวิท 101/1');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21336','คลินิกเวชกรรมบางไผ่');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22160','คลินิกเวชกรรมบางไผ่-กระโจมทอง(15192)');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15172','เพชรเกษม 54 คลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15201','คลินิกเวชกรรมจันทน์สะพาน 4');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('16141','ดอกไม้เวชคลินิก');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21967','คลินิกนวมินทร์ 2 เวชกรรม สาขาราษฎร์บูรณะ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15191','คลินิกเวชกรรมกรุงธน 2');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21422','คลินิกเวชกรรมมหาชนสามแยกพระประแดง');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15185','ธนารมย์เวชกรรมคลินิก');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15149','บางปะกอกคลินิกเวชการ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15150','คลินิกนวมินทร์การแพทย์ (สาขาหน้านิคมอุตสาหกรรมลาดกระบัง)');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15184','คลินิกแพทย์เวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15173','คลินิกเวชกรรมเกษมราษฎร์ สาขาเคหะร่มเกล้า');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15151','นวมินทร์คลินิกเวชกรรม (สาขาเคหะร่มเกล้า)');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15152','นวมินทร์คลินิกเวชกรรม (สาขาหัวตะเข้)');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21722','คลินิกเวชกรรมโชคชัย 4');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21833','คลินิกเวชกรรมลาดพร้าว 122');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22006','เรือพระร่วงคลินิกเวชกรรม สาขารามคำแหง 39');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15174','สหคลินิกกล้วยน้ำไท สาขาทองหล่อ ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15153','คลินิกแพทย์ฉัตรลดา 1');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15189','คลินิกเวชกรรมแพทย์ปัญญา สาขาอ่อนนุช 39');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15155','สหคลินิกกล้วยน้ำไท สาขาสาทร');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15176','นวมินทร์คลินิกเวชกรรม (สาขา ม.มหานคร) ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22161','นวมินทร์คลินิกเวชกรรม สาขาลำผักชี');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15177','คลินิกเวชกรรมกล้วยน้ำไท สาขาทุ่งสองห้อง');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15203','สหคลินิกเกษมราษฎร์ สาขา ม.ธุรกิจบัณฑิตย์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15178','สหคลินิกเกษมราษฎร์  สาขาทุ่งสองห้อง');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21393','เมดิโปรการแพทย์และสุขภาพ สาขาเมืองไทยภัทรคอมเพล็กซ์สหคลินิก');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21444','สหคลินิกกล้วยน้ำไท สาขาห้วยขวาง');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22693','มิตรชุมชนคลินิกเวชกรรม (สาขาจันทรุเบกษา)');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22682','คลินิกเวชกรรมสรงประภา');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22691','สุนทรีคลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22687','คลินิกเวชกรรมวัดไผ่เขียว');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22679','วิภาวดี 49 คลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22708','ภานพคลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22707','อินทัชเมดิแคร์คลินิกเวชกรรม สาขาบางเขน');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22705','โพธิ์สุวรรณคลินิกเวชกรรม สาขาบางเขน');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22694','มิตรชุมชนคลินิกเวชกรรม (สาขารามินทรา กม.2)');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22066','คลินิกเวชกรรมสะพานใหม่');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22675','คลินิกเวชกรรมห้าแยกวัชรพล');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22697','เกรียงศักดิ์คลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22704','โพธิ์สุวรรณคลินิกเวชกรรม สาขาถนนลาดปลาเค้า');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22695','คลินิกเวชกรรมตลาดบัว');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22709','สายไหมคลินิกเวชกรรม     สาขาตลาดวงศกร');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22690','สายไหมคลินิกเวชกรรม     สาขาออเงิน');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22684','ศิริพัฒน์คลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22706','พัฒนเวชกคลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22696','คลินิกเวชกรรมวิภาวดี 60');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('99999','คลินิกเวชกรรมเยนเนอรัลธนินทร');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22881','คลินิกเวชกรรมซอยสายหยุด');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22880','พหลโยธิน 65 คลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15526','วัชรพลเวชกรรมคลินิก');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22897','เสนานิเวศน์คลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22995','สหคลินิกเกษมราษฎร์ สาขาเสรีไทย');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22548','บางขุนเทียน 5 คลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22686','นวมินทร์คลินิกเวชกรรม สาขารามอินทรา กม.8');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22911','สหคลินิกเกษมราษฎร์ สาขาคลองขวาง');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('15749','ยานนาวาคลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22966','พหล ฯ 58 คลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('23436','คลินิกเวชกรรมเพชรบุรี  12');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('23437','คลินิกเวชกรรมทวิมาศ สาขารามคำแหง 40');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('23438','คลินิกเวชกรรมทวิมาศ สาขารามคำแหง 53');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('23439','งามเจริญคลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('23555','มเหสักข์คลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('23556','เจริญนครธนบุรีคลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('23557','สี่แยกประเวศคลินิกเวชกรรม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('23561','อินทัชเมดิแคร์คลินิกเวชกรรม สาขาบางแค');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13646','ศูนย์บริการสาธารณสุข 1 สะพานมอญ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13647','ศูนย์บริการสาธารณสุข 2 ราชปรารถ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13648','ศูนย์บริการสาธารณสุข 3 บางซื่อ ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13649','ศูนย์บริการสาธารณสุข 4 ดินแดง');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13650','ศูนย์บริการสาธารณสุข 5 จุฬาลงกรณ์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13651','ศูนย์บริการสาธารณสุข 6 สโมสรวัฒนธรรมหญิง');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13652','ศูนย์บริการสาธารณสุข 7 บุญมี ปุรุราชรังสรรค์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13653','ศูนย์บริการสาธารณสุข 8 บุญรอด รุ่งเรือง');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13654','ศูนย์บริการสาธารณสุข 9 ประชาธิปไตย');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13655','ศูนย์บริการสาธารณสุข 10 สุขุมวิท');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13656','ศูนย์บริการสาธารณสุข 11 ประดิพัทธ์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13657','ศูนย์บริการสาธารณสุข 12 จันทร์เที่ยง เนตรวิเศษ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13658','ศูนย์บริการสาธารณสุข 13 ไมตรีวานิช');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13659','ศูนย์บริการสาธารณสุข 14 แก้ว สีบุญเรือง');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13660','ศูนย์บริการสาธารณสุข 15 ลาดพร้าว ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13661','ศูนย์บริการสาธารณสุข 16 ลุมพินี');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13662','ศูนย์บริการสาธารณสุข 17 ประชานิเวศน์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13663','ศูนย์บริการสาธารณสุข 18 มงคล วอน วังตาล ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13664','ศูนย์บริการสาธารณสุข 19 วงศ์สว่าง');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13665','ศูนย์บริการสาธารณสุข 20 บมจ.ธนาคารนครหลวงไทย');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13666','ศูนย์บริการสาธารณสุข 21 วัดธาตุทอง');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13667','ศูนย์บริการสาธารณสุข 22 วัดปากบ่อ ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13668','ศูนย์บริการสาธารณสุข 23 สี่พระยา');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13669','ศูนย์บริการสาธารณสุข 24 บางเขน');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13670','ศูนย์บริการสาธารณสุข 25 ห้วยขวาง');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13671','ศูนย์บริการสาธารณสุข 26 เจ้าคุณพระประยูรวงศ์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13672','ศูนย์บริการสาธารณสุข 27 จันทร์ ฉิมไพบูลย์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13673','ศูนย์บริการสาธารณสุข 28 กรุงธนบุรี');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13674','ศูนย์บริการสาธารณสุข 29 ช่วง นุชเนตร');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13675','ศูนย์บริการสาธารณสุข 30 วัดเจ้าอาม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13676','ศูนย์บริการสาธารณสุข 31 เอิบ-จิตร ทังสุบุตร');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13677','ศูนย์บริการสาธารณสุข 32 มาริษ ตินตมุสิก');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13678','ศูนย์บริการสาธารณสุข 33 วัดหงส์รัตนาราม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13679','ศูนย์บริการสาธารณสุข 34 โพธิ์ศรี');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13680','ศูนย์บริการสาธารณสุข 35 หัวหมาก');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13681','ศูนย์บริการสาธารณสุข 36 บุคคโล');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13682','ศูนย์บริการสาธารณสุข 37 ประสงค์ สุดสาคร ตู้จินดา');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13683','ศูนย์บริการสาธารณสุข 38 จิ๊ด ทองคำ บำเพ็ญ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13684','ศูนย์บริการสาธารณสุข 39 ราษฎร์บูรณะ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13685','ศูนย์บริการสาธารณสุข 40 บางแค');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13686','ศูนย์บริการสาธารณสุข 41 คลองเตย');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13687','ศูนย์บริการสาธารณสุข 42 ถนอม ทองสิมา');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13688','ศูนย์บริการสาธารณสุข 43 มีนบุรี');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21526','ศูนย์บริการสาธารณสุข 44 ลำผักชี หนองจอก');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21755','ศูนย์บริการสาธารณสุข 45 ร่มเกล้า ลาดกระบัง');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13689','ศูนย์บริการสาธารณสุข 46 กันตารัติอุทิศ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13690','ศูนย์บริการสาธารณสุข 47 คลองขวาง');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13691','ศูนย์บริการสาธารณสุข 48 นาควัชระอุทิศ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13692','ศูนย์บริการสาธารณสุข 49 วัดชัยพฤกษมาลา');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13693','ศูนย์บริการสาธารณสุข 50 บึงกุ่ม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13694','ศูนย์บริการสาธารณสุข 51 วัดไผ่ตัน');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13695','ศูนย์บริการสาธารณสุข 52 สามเสนนอก');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13696','ศูนย์บริการสาธารณสุข 53 ทุ่งสองห้อง');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13697','ศูนย์บริการสาธารณสุข 54 ทัศน์เอี่ยม ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13698','ศูนย์บริการสาธารณสุข 55 เตชะสัมพันธ์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13699','ศูนย์บริการสาธารณสุข 56 ทับเจริญ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13700','ศูนย์บริการสาธารณสุข 57 บุญเรือง ล้ำเลิศ ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13701','ศูนย์บริการสาธารณสุข 58 ล้อม-พิมเสน ฟักอุดม');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13702','ศูนย์บริการสาธารณสุข 59 ทุ่งครุ');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13703','ศูนย์บริการสาธารณสุข 60 รสสุคนธ์ มโนชญากร');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13704','ศูนย์บริการสาธารณสุข 61 สังวาลย์ ทัสนารมย์');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('13705','ศูนย์บริการสาธารณสุข 62 ตวงรัชฎ์ฯ ศศะนาวิน ภักดี ฐานปัญญา');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('14818','ศูนย์บริการสาธารณสุข 63 สมาคมแต้จิ๋วแห่งประเทศไทย');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('22455','ศูนย์บริการสาธารณสุข 64 คลองสามวา');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21486','ศูนย์บริการสาธารณสุข 65 รักษาศุข บางบอน');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('21748','ศูนย์บริการสาธารณสุข 66 ตำหนักพระแม่กวนอิม โชคชัย 4');
insert into b_visit_office (b_visit_office_id,visit_office_name) values ('23159','ศูนย์บริการสาธารณสุข 68 สะพานสูง');


--28-01-08
--ton
--บันทึกเป็น UTF-8 เท่านั้นนะ t_nhso_trans_date_offline
-- t_nhso_trans_date_offline
CREATE TABLE t_nhso_trans_date_offline ( 
    t_nhso_trans_date_offline_id         	character varying(255) NOT NULL ,
    t_nhso_trans_date_offline_current_date       	character varying(255) NULL,
    t_nhso_trans_date_offline_data_date	character varying(255) NULL,
    t_nhso_trans_date_offline_round    		character varying(255) NULL,PRIMARY KEY(t_nhso_trans_date_offline_id)
);


--version 

INSERT INTO s_nhso_version("s_version_id", "version_number", "version_description", "version_application_number", "version_database_number", "version_update_time")
VALUES('9701000000110',	'10',	'Hospital OS, Community Edition',	'3.13.1150',	'0.2.220108',	'2551-01-22 08:00:00');

