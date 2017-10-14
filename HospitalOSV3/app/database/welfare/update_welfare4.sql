DROP TABLE b_welfare_direct_draw_map_item;
DROP TABLE b_welfare_direct_draw_map_plan;
DROP TABLE t_welfare_billtrans;
DROP TABLE f_welfare_particular_status_type;
DROP TABLE f_welfare_billtrans_checkcode_type;
DROP TABLE t_welfare_directdraw_patient;
DROP TABLE f_welfare_directdraw_patient_sent_type;
DROP TABLE f_welfare_direct_patient_checkcode_type;
DROP TABLE f_welfare_organization;
DROP TABLE f_welfare_occupation_type;
DROP TABLE f_welfare_plan_owner_status_type;

--ตารางข้อมูลกำหนดรหัสและราคามาตรฐานของ item ของเบิกจ่ายตรง
CREATE TABLE b_welfare_direct_draw_map_item (
    b_welfare_direct_draw_map_item_id character varying(255) NOT NULL,
    b_item_id character varying(255),
    map_item_standard_number character varying(255),
    map_item_price character varying(255),
    CONSTRAINT b_welfare_direct_draw_map_item_pkey PRIMARY KEY (b_welfare_direct_draw_map_item_id)
);

--ตารางข้อมูลสิทธิที่เป็นสิทธิเบิกจ่ายตรง
CREATE TABLE b_welfare_direct_draw_map_plan (
    b_welfare_direct_draw_map_plan_id character varying(255) NOT NULL,
    b_contract_plans_id character varying(255),
    map_plan_is_plan character varying(255),
    map_plan_description character varying(255),
    CONSTRAINT b_welfare_direct_draw_map_plan_pkey PRIMARY KEY (b_welfare_direct_draw_map_plan_id)
);

--เก็บข้อมูล bill แต่ละรายการ ของ billtrans ที่ส่งไปให้ สกส.
CREATE TABLE t_welfare_billtrans (
    t_welfare_billtrans_id character varying(255) NOT NULL,
    f_welfare_billtrans_checkcode_type_id character varying(255),
    billtrans_response_date_time character varying(255),
    billtrans_response_number character varying(255),
    billtrans_sent_date_time character varying(255),
    f_welfare_particular_status_type_id character varying(255),
    t_billing_id character varying(255),
    billtrans_sent_staff character varying(255),
    billtrans_response_staff character varying(255),
    billtrans_invoice_number character varying(255),
    CONSTRAINT t_welfare_billtrans_pkey PRIMARY KEY (t_welfare_billtrans_id)
); 

--สถานะรายการ
CREATE TABLE f_welfare_particular_status_type (
    f_welfare_particular_status_type_id character varying(255) NOT NULL,
    particular_status_type_description character varying(255),
    CONSTRAINT f_welfare_particular_status_type_pkey PRIMARY KEY (f_welfare_particular_status_type_id)
);

insert into f_welfare_particular_status_type values ('1','รอส่ง');
insert into f_welfare_particular_status_type values ('2','ส่งแล้ว');
insert into f_welfare_particular_status_type values ('3','ตอบกลับ');

--สถานะรายการตอบกลับ
CREATE TABLE f_welfare_billtrans_checkcode_type (
    f_welfare_billtrans_checkcode_type_id character varying(255) NOT NULL,
    billtrans_checkcode_type_description character varying(255),
    CONSTRAINT f_welfare_billtrans_checkcode_type_pkey PRIMARY KEY (f_welfare_billtrans_checkcode_type_id)
);

INSERT INTO f_welfare_billtrans_checkcode_type values ('A','รับไว้ในทะเบียนแล้ว');
INSERT INTO f_welfare_billtrans_checkcode_type values ('C','มีรายการที่ต้องแก้ไข');
INSERT INTO f_welfare_billtrans_checkcode_type values ('11','ไม่ระบุ InvNo');
INSERT INTO f_welfare_billtrans_checkcode_type values ('12','InvNo ซ้ำ');
INSERT INTO f_welfare_billtrans_checkcode_type values ('16','ไม่ระบุ AuthCode');
INSERT INTO f_welfare_billtrans_checkcode_type values ('17','Authcode ไม่ถูกต้อง');
INSERT INTO f_welfare_billtrans_checkcode_type values ('21','ไม่ระบุ HN ในรายการ');
INSERT INTO f_welfare_billtrans_checkcode_type values ('22','HN ไม่อยู่ในทะเบียนผู้มีสิทธิ');
INSERT INTO f_welfare_billtrans_checkcode_type values ('26','ไม่ระบุ MemberNo หรือ Member No ไม่ตรงกับ HN');
INSERT INTO f_welfare_billtrans_checkcode_type values ('27','MemberNo ไม่อยู่ในทะเบียนผู้มีสิทธิ');
INSERT INTO f_welfare_billtrans_checkcode_type values ('28','MemberNo ถูกระงับสิทธิทำธุรกรรม');
INSERT INTO f_welfare_billtrans_checkcode_type values ('31','ไม่มีข้อมูลลายนิ้วมือส่งไป');
INSERT INTO f_welfare_billtrans_checkcode_type values ('32','ข้อมูลลายนิ้วมือไม่ถูกต้อง');
INSERT INTO f_welfare_billtrans_checkcode_type values ('33','ข้อมูลลายนิ้วมือไม่ตรงกับทะเบียน');
INSERT INTO f_welfare_billtrans_checkcode_type values ('51','ไม่ระบุ Station');
INSERT INTO f_welfare_billtrans_checkcode_type values ('52','DTTran วันที่ไม่อยู่ในเกณฑ์ที่กำหนด');
INSERT INTO f_welfare_billtrans_checkcode_type values ('53','DTTran อยู่นอกช่วงมีสิทธิเบิก');
INSERT INTO f_welfare_billtrans_checkcode_type values ('61','Amount ไม่ใช่ข้อมูลชนิดตัวเลข, เป็น 0 หรือ ติดลบ');
INSERT INTO f_welfare_billtrans_checkcode_type values ('66','ไม่ระบุ BillNo');
INSERT INTO f_welfare_billtrans_checkcode_type values ('67','Paid มากกว่า Amount');


--เก็บข้อมูลสถานะผู้ป่วยที่ขอใช้สิทธิเ่บิกตรง 
CREATE TABLE t_welfare_directdraw_patient (
    t_welfare_directdraw_patient_id character varying(255) NOT NULL,
    t_patient_id character varying(255),
    f_welfare_directdraw_patient_sent_type_id character varying(255),
    f_welfare_direct_patient_checkcode_type_id character varying(255),
    f_welfare_particular_status_type_id character varying(255),
    directdraw_patient_hn character varying(255),
    directdraw_patient_sent_date_time character varying(255),
    directdraw_patient_response_date_time character varying(255),
    directdraw_patient_assurance_number character varying(255),
    directdraw_patient_register_date_time character varying(255),
    directdraw_patient_response_staff character varying(255),
    directdraw_patient_register_staff character varying(255),
    directdraw_patient_sent_staff character varying(255),
    directdraw_patient_approve_date_time character varying(255),
    directdraw_patient_expire_date_time character varying(255),
    f_organization_id character varying(255),
    f_address_id character varying(255),
    f_welfare_occupation_type_id character varying(255),
    f_welfare_plan_owner_status_type_id character varying(255),
    directdraw_patient_owner_fname character varying(255),
    directdraw_patient_owner_lname character varying(255),
    directdraw_patient_owner_pid character varying(255),
    f_prefix_id character varying(255),
    directdraw_patient_member_number character varying(255),
    CONSTRAINT t_welfare_directdraw_patient_pkey PRIMARY KEY (t_welfare_directdraw_patient_id)
);

--อาชีพของผู้ขอใช้สิทธิเบิกจ่ายตรง
CREATE TABLE f_welfare_occupation_type (
    f_welfare_occupation_type_id character varying(255) NOT NULL,
    occupation_type_description character varying(255),
    CONSTRAINT f_welfare_occupation_type_pkey PRIMARY KEY (f_welfare_occupation_type_id)
);

insert into f_welfare_occupation_type values ('1','ข้าราชการ/ลูกจ้างประจำ');
insert into f_welfare_occupation_type values ('2','บำนาญ เบี้ยหวัด');
insert into f_welfare_occupation_type values ('3','พนักงานของรัฐ สธ.');

--สถานะความสัมพันธ์ระหว่างผู้ขอใช้สิทธิกับสิทธิ
CREATE TABLE f_welfare_plan_owner_status_type (
    f_welfare_plan_owner_status_type_id character varying(255) NOT NULL,
    plan_owner_status_type_description character varying(255),
    CONSTRAINT f_welfare_plan_owner_status_type_pkey PRIMARY KEY (f_welfare_plan_owner_status_type_id)
);

insert into f_welfare_plan_owner_status_type values ('1','ตัวผู้ป่วย');
insert into f_welfare_plan_owner_status_type values ('2','บุตร');
insert into f_welfare_plan_owner_status_type values ('3','บิดา/มารดา');
insert into f_welfare_plan_owner_status_type values ('4','คู่สมรส');

--สถานะการส่งข้อมูล
CREATE TABLE f_welfare_directdraw_patient_sent_type (
    f_welfare_directdraw_patient_sent_type_id character varying(255) NOT NULL,
    directdraw_patient_sent_type_description character varying(255),
    CONSTRAINT f_welfare_directdraw_patient_sent_type_pkey PRIMARY KEY (f_welfare_directdraw_patient_sent_type_id)
);

insert into f_welfare_directdraw_patient_sent_type values ('#','รายการ ลงทะเบียนใหม่');
insert into f_welfare_directdraw_patient_sent_type values ('U','รายการ ปรับปรุงแก้ไข');

--สถานะรายการตอบกลับ
CREATE TABLE f_welfare_direct_patient_checkcode_type (
    f_welfare_direct_patient_checkcode_type_id character varying(255) NOT NULL,
    direct_patient_checkcode_description character varying(255),
    CONSTRAINT f_welfare_direct_patient_checkcode_type_pkey PRIMARY KEY (f_welfare_direct_patient_checkcode_type_id)
);

INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('U','รายการเปลี่ยนแปลงโดย สกส. หรือ บก');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('V','รายการเปลี่ยนแปลงตัวข้อมูลจากการแจ้งโดย รพ.');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('E','ยกเลิก/ระงับสิทธิ');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('X','ตัดออก(ตัด HN เดิมออก  เนื่องจากรพ. ขอเปลี่ยน HN)');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('A','ข้อมูลถูกต้องไม่ต้องแก้ไข');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('C','มีรายการที่ต้องแก้ไข');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('11','ข้อมูลดรรชนี');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('12','ข้อมูลดรรชนี');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('13','ข้อมูลดรรชนี');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('14','ข้อมูลดรรชนี');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('15','ข้อมูลดรรชนี');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('16','ข้อมูลดรรชนี');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('17','ข้อมูลดรรชนี');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('18','ข้อมูลดรรชนี');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('21','ข้อมูลการรับรองสิทธิ์สวัสดิการฯ');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('22','ข้อมูลการรับรองสิทธิ์สวัสดิการฯ');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('23','ข้อมูลการรับรองสิทธิ์สวัสดิการฯ');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('24','ข้อมูลการรับรองสิทธิ์สวัสดิการฯ');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('25','ข้อมูลการรับรองสิทธิ์สวัสดิการฯ');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('26','ข้อมูลการรับรองสิทธิ์สวัสดิการฯ');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('27','ข้อมูลการรับรองสิทธิ์สวัสดิการฯ');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('28','เลขที่หนังสือรับรองสิทธิ์ไม่มี/เลขที่ไม่ถูกต้อง');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('29','แจ้งเป็นทะเบียนล่วงหน้า แต่ไม่พบเลขอ้างอิงในทะเบียน');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('2A','เลขอนุมัติไม่ตรงกับข้อมูลธุรกรรม');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('2B','เลขอนุมัติผิดแบบ');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('2C','สิทธิถูกยกเลิกโดยกรมบัญชีกลาง(สิ้นสุดสิทธิสวัสดิการข้าราชการ)');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('2D','ไม่ใช่ผู้มีสิทธิ/สิทธิยังไม่เริ่ม/สิทธิซ้ำซ้อน');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('2E','ข้อมูลธุรกรรมที่ส่งไปไม่ตรงกับข้อมูลที่ใช้ขอเลขอนุมัติ');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('31','ข้อมูลการรับรองการวินิจฉัย');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('32','ข้อมูลการรับรองการวินิจฉัย');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('33','ข้อมูลการรับรองการวินิจฉัย');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('41','ข้อมูลลายนิ้วมือผู้ป่วย');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('42','ข้อมูลลายนิ้วมือผู้ป่วย');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('43','ไม่พบลายนิ้วมือ');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('51','ข้อมูลประเภทการลงทะเบียน');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('52','ลงทะเบียนกลุ่มโรคเฉพาะมีข้อมูลดัชนีไม่ถูกต้อง');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('53','ลงทะเบียนกลุ่มโรคเฉพาะซ้ำ');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('71','ข้อมูลลายนิ้วมือผู้รับยาแทน');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('72','ข้อมูลลายนิ้วมือผู้รับยาแทน');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('81','ข้อมูลที่ควรมี');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('82','ข้อมูลที่ควรมี');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('83','ข้อมูลที่ควรมี');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('84','ข้อมูลที่ควรมี');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('85','ข้อมูลที่ควรมี');
INSERT INTO f_welfare_direct_patient_checkcode_type VALUES ('86','ข้อมูลที่ควรมี');

--ต้นสังกัดของผู้มีสิทธิเบิกจ่ายตรง
CREATE TABLE f_welfare_organization (
    f_welfare_organization_id character varying(255) NOT NULL,
    organization_description character varying(255),
    CONSTRAINT f_welfare_organization_pkey PRIMARY KEY (f_welfare_organization_id)
);

INSERT INTO f_welfare_organization VALUES ('08003','กรมการขนส่งทางน้ำและพาณิชยนาวี');
INSERT INTO f_welfare_organization VALUES ('08004','กรมการขนส่งทางบก');
INSERT INTO f_welfare_organization VALUES ('08005','กรมการขนส่งทางอากาศ');
INSERT INTO f_welfare_organization VALUES ('13003','กรมการค้าต่างประเทศ');
INSERT INTO f_welfare_organization VALUES ('13004','กรมการค้าภายใน');
INSERT INTO f_welfare_organization VALUES ('17003','กรมการจัดหางาน'); 
INSERT INTO f_welfare_organization VALUES ('15003','กรมการปกครอง');
INSERT INTO f_welfare_organization VALUES ('13005','กรมการประกันภัย');
INSERT INTO f_welfare_organization VALUES ('15004','กรมการพัฒนาชุมชน');
INSERT INTO f_welfare_organization VALUES ('21003','กรมการแพทย์');
INSERT INTO f_welfare_organization VALUES ('18003','กรมการศาสนา');
INSERT INTO f_welfare_organization VALUES ('09003','กรมควบคุมมลพิษ');
INSERT INTO f_welfare_organization VALUES ('21004','กรมควบคุมโรค');
INSERT INTO f_welfare_organization VALUES ('16004','กรมคุ้มครองสิทธิและเสรีภาพ');
INSERT INTO f_welfare_organization VALUES ('16003','กรมคุมประพฤติ');
INSERT INTO f_welfare_organization VALUES ('13006','กรมเจรจาการค้าระหว่างประเทศ');
INSERT INTO f_welfare_organization VALUES ('07003','กรมชลประทาน');
INSERT INTO f_welfare_organization VALUES ('12003','กรมเชื้อเพลิงธรรมชาติ');
INSERT INTO f_welfare_organization VALUES ('07004','กรมตรวจบัญชีสหกรณ์');
INSERT INTO f_welfare_organization VALUES ('13007','กรมทรัพย์สินทางปํญญา');
INSERT INTO f_welfare_organization VALUES ('09004','กรมทรัพยากรทางทะเลและชายฝั่ง');
INSERT INTO f_welfare_organization VALUES ('09005','กรมทรัพยากรธรณี');
INSERT INTO f_welfare_organization VALUES ('09006','กรมทรัพยากรน้ำ');
INSERT INTO f_welfare_organization VALUES ('09007','กรมทรัพยากรน้ําบาดาล');
INSERT INTO f_welfare_organization VALUES ('08006','กรมทางหลวง');
INSERT INTO f_welfare_organization VALUES ('08007','กรมทางหลวงชนบท');
INSERT INTO f_welfare_organization VALUES ('15005','กรมที่ดิน');
INSERT INTO f_welfare_organization VALUES ('03003','กรมธนารักษ์');
INSERT INTO f_welfare_organization VALUES ('12004','กรมธุรกิจพลังงาน');
INSERT INTO f_welfare_organization VALUES ('16005','กรมบังคับคดี');
INSERT INTO f_welfare_organization VALUES ('03004','กรมบัญชีกลาง');
INSERT INTO f_welfare_organization VALUES ('01002','กรมประชาสัมพันธ์');
INSERT INTO f_welfare_organization VALUES ('07005','กรมประมง');
INSERT INTO f_welfare_organization VALUES ('07006','กรมปศุสัตว์');
INSERT INTO f_welfare_organization VALUES ('15006','กรมป้องกันและบรรเทาสาธารณภัย');
INSERT INTO f_welfare_organization VALUES ('09012','กรมป่าไม้ (ย้ายมาจากกระทรวงเกษตรและสหกรณ์)');
INSERT INTO f_welfare_organization VALUES ('21005','กรมพัฒนาการแพทย์แผนไทยและการแพทย์ทางเลือก');
INSERT INTO f_welfare_organization VALUES ('07008','กรมพัฒนาที่ดิน');
INSERT INTO f_welfare_organization VALUES ('13008','กรมพัฒนาธุรกิจการค้า');
INSERT INTO f_welfare_organization VALUES ('17004','กรมพัฒนาฝีมือแรงงาน');
INSERT INTO f_welfare_organization VALUES ('12005','กรมพัฒนาพลังงานทดแทนและอนุรักษ์พลังงาน');
INSERT INTO f_welfare_organization VALUES ('06003','กรมพัฒนาสังคมและสวัสดิการ');
INSERT INTO f_welfare_organization VALUES ('16006','กรมพินิจและคุ้มครองเด็กและเยาวชน');
INSERT INTO f_welfare_organization VALUES ('15007','กรมโยธาธิการและผังเมือง');
INSERT INTO f_welfare_organization VALUES ('16007','กรมราชทัณฑ์');
INSERT INTO f_welfare_organization VALUES ('02002','กรมราชองครักษ์');
INSERT INTO f_welfare_organization VALUES ('22003','กรมโรงงานอุตสาหกรรม');
INSERT INTO f_welfare_organization VALUES ('07009','กรมวิชาการเกษตร');
INSERT INTO f_welfare_organization VALUES ('19003','กรมวิทยาศาตร์บริการ');
INSERT INTO f_welfare_organization VALUES ('21006','กรมวิทยาศาสตร์การแพทย์');
INSERT INTO f_welfare_organization VALUES ('18004','กรมศิลปากร');
INSERT INTO f_welfare_organization VALUES ('03005','กรมศุลกากร');
INSERT INTO f_welfare_organization VALUES ('07011','กรมส่งเสริมการเกษตร');
INSERT INTO f_welfare_organization VALUES ('15008','กรมส่งเสริมการปกครองท้องถิ่น');
INSERT INTO f_welfare_organization VALUES ('13009','กรมส่งเสริมการส่งออก');
INSERT INTO f_welfare_organization VALUES ('09008','กรมส่งเสริมคุณภาพสิ่งแวดล?อม');
INSERT INTO f_welfare_organization VALUES ('07012','กรมส่งเสริมสหกรณ์');
INSERT INTO f_welfare_organization VALUES ('22004','กรมส่งเสริมอุตสาหกรรม');
INSERT INTO f_welfare_organization VALUES ('21007','กรมสนับสนุนบริการสุขภาพ');
INSERT INTO f_welfare_organization VALUES ('03006','กรมสรรพสามิต');
INSERT INTO f_welfare_organization VALUES ('03007','กรมสรรพากร');
INSERT INTO f_welfare_organization VALUES ('17005','กรมสวัสดิการและคุ้มครองแรงงาน');
INSERT INTO f_welfare_organization VALUES ('16008','กรมสอบสวนคดีพิเศษ');
INSERT INTO f_welfare_organization VALUES ('21008','กรมสุขภาพจิต');
INSERT INTO f_welfare_organization VALUES ('21009','กรมอนามัย');
INSERT INTO f_welfare_organization VALUES ('22005','กรมอุตสาหกรรมพื้นฐานและการเหมืองแร่');
INSERT INTO f_welfare_organization VALUES ('11004','กรมอุตุนิยมวิทยา');
INSERT INTO f_welfare_organization VALUES ('09009','กรมอุทยานแห่งชาติ สัตว์ป่า และพันธุ์พืช');
INSERT INTO f_welfare_organization VALUES ('02004','กองทัพบก');
INSERT INTO f_welfare_organization VALUES ('02005','กองทัพเรือ');
INSERT INTO f_welfare_organization VALUES ('02006','กองทัพอากาศ');
INSERT INTO f_welfare_organization VALUES ('02003','กองบัญชาการทหารสูงสุด');
INSERT INTO f_welfare_organization VALUES ('20101','จุฬาลงกรณ์มหาวิทยาลัย');
INSERT INTO f_welfare_organization VALUES ('20102','มหาวิทยาลัยเกษตรศาสตร์');
INSERT INTO f_welfare_organization VALUES ('20103','มหาวิทยาลัยขอนแก่น');
INSERT INTO f_welfare_organization VALUES ('20104','มหาวิทยาลัยเชียงใหม่');
INSERT INTO f_welfare_organization VALUES ('20105','มหาวิทยาลัยทักษิณ');
INSERT INTO f_welfare_organization VALUES ('20166','มหาวิทยาลัยเทคโนโลยีราชมงคลกรุงเทพ');
INSERT INTO f_welfare_organization VALUES ('20167','มหาวิทยาลัยเทคโนโลยีราชมงคลตะวันออก');
INSERT INTO f_welfare_organization VALUES ('20165','มหาวิทยาลัยเทคโนโลยีราชมงคลธัญบุรี');
INSERT INTO f_welfare_organization VALUES ('20168','มหาวิทยาลัยเทคโนโลยีราชมงคลพระนคร');
INSERT INTO f_welfare_organization VALUES ('20169','มหาวิทยาลัยเทคโนโลยีราชมงคลรัตนโกสินทร์');
INSERT INTO f_welfare_organization VALUES ('20170','มหาวิทยาลัยเทคโนโลยีราชมงคลล้านนา');
INSERT INTO f_welfare_organization VALUES ('20171','มหาวิทยาลัยเทคโนโลยีราชมงคลศรีวิชัย');
INSERT INTO f_welfare_organization VALUES ('20172','มหาวิทยาลัยเทคโนโลยีราชมงคลสุวรรณภูมิ');
INSERT INTO f_welfare_organization VALUES ('20173','มหาวิทยาลัยเทคโนโลยีราชมงคลอีสาน');
INSERT INTO f_welfare_organization VALUES ('20106','มหาวิทยาลัยธรรมศาสตร์');
INSERT INTO f_welfare_organization VALUES ('20175','มหาวิทยาลัยนราธิวาสราชนครินทร์');
INSERT INTO f_welfare_organization VALUES ('20107','มหาวิทยาลัยนเรศวร');
INSERT INTO f_welfare_organization VALUES ('20108','มหาวิทยาลัยบูรพา');
INSERT INTO f_welfare_organization VALUES ('20109','มหาวิทยาลัยมหาสารคาม');
INSERT INTO f_welfare_organization VALUES ('20110','มหาวิทยาลัยมหิดล');
INSERT INTO f_welfare_organization VALUES ('20111','มหาวิทยาลัยแม่โจ้');
INSERT INTO f_welfare_organization VALUES ('20124','มหาวิทยาลัยราชภัฏกาญจนบุรี');
INSERT INTO f_welfare_organization VALUES ('20125','มหาวิทยาลัยราชภัฏกาฬสินธุ์');
INSERT INTO f_welfare_organization VALUES ('20126','มหาวิทยาลัยราชภัฏกําแพงเพชร');
INSERT INTO f_welfare_organization VALUES ('20127','มหาวิทยาลัยราชภัฏจันทรเกษม');
INSERT INTO f_welfare_organization VALUES ('20128','มหาวิทยาลัยราชภัฏชัยภูมิ');
INSERT INTO f_welfare_organization VALUES ('20129','มหาวิทยาลัยราชภัฏเชียงราย');
INSERT INTO f_welfare_organization VALUES ('20130','มหาวิทยาลัยราชภัฏเชี ยงใหม่');
INSERT INTO f_welfare_organization VALUES ('20131','มหาวิทยาลัยราชภัฏเทพสตรี');
INSERT INTO f_welfare_organization VALUES ('20132','มหาวิทยาลัยราชภัฏธนบุรี');
INSERT INTO f_welfare_organization VALUES ('20133','มหาวิทยาลัยราชภัฏนครปฐม');
INSERT INTO f_welfare_organization VALUES ('20134','มหาวิทยาลัยราชภัฏนครพนม');
INSERT INTO f_welfare_organization VALUES ('20135','มหาวิทยาลัยราชภัฏนครราชสีมา');
INSERT INTO f_welfare_organization VALUES ('20136','มหาวิทยาลัยราชภัฏนครศรีธรรมราช');
INSERT INTO f_welfare_organization VALUES ('20137','มหาวิทยาลัยราชภัฏนครสวรรค์');
INSERT INTO f_welfare_organization VALUES ('20138','มหาวิทยาลัยราชภัฏบ้านสมเด็จเจ้าพระยา');
INSERT INTO f_welfare_organization VALUES ('20139','มหาวิทยาลัยราชภัฏบุรีรัมย์');
INSERT INTO f_welfare_organization VALUES ('20140','มหาวิทยาลัยราชภัฏพระนคร');
INSERT INTO f_welfare_organization VALUES ('20141','มหาวิทยาลัยราชภัฏพระนครศรีอยุธยา');
INSERT INTO f_welfare_organization VALUES ('20142','มหาวิทยาลัยราชภัฏพิบูลสงคราม');
INSERT INTO f_welfare_organization VALUES ('20143','มหาวิทยาลัยราชภัฏเพชรบุรี');
INSERT INTO f_welfare_organization VALUES ('20145','มหาวิทยาลัยราชภัฏเพชรบูรณ์');
INSERT INTO f_welfare_organization VALUES ('20146','มหาวิทยาลัยราชภัฏภูเก็ต');
INSERT INTO f_welfare_organization VALUES ('20147','มหาวิทยาลัยราชภัฏมหาสารคาม');
INSERT INTO f_welfare_organization VALUES ('20148','มหาวิทยาลัยราชภัฏยะลา');
INSERT INTO f_welfare_organization VALUES ('20150','มหาวิทยาลัยราชภัฏร้อยเอ็ด');
INSERT INTO f_welfare_organization VALUES ('20149','มหาวิทยาลัยราชภัฏราชนครินทร์');
INSERT INTO f_welfare_organization VALUES ('20151','มหาวิทยาลัยราชภัฏรําไพพรรณี');
INSERT INTO f_welfare_organization VALUES ('20153','มหาวิทยาลัยราชภัฏลําปาง');
INSERT INTO f_welfare_organization VALUES ('20152','มหาวิทยาลัยราชภัฏเลย');
INSERT INTO f_welfare_organization VALUES ('20144','มหาวิทยาลัยราชภัฏวไลยอลงกรณ์ในพระบรมราชูปถัมภ์ จังหวัดปทุมธานี');
INSERT INTO f_welfare_organization VALUES ('20154','มหาวิทยาลัยราชภัฏศรีสะเกษ');
INSERT INTO f_welfare_organization VALUES ('20155','มหาวิทยาลัยราชภัฏสกลนคร');
INSERT INTO f_welfare_organization VALUES ('20156','มหาวิทยาลัยราชภัฏสงขลา');
INSERT INTO f_welfare_organization VALUES ('20157','มหาวิทยาลัยราชภัฏสวนดุสิต');
INSERT INTO f_welfare_organization VALUES ('20158','มหาวิทยาลัยราชภัฏสวนสุนันทา');
INSERT INTO f_welfare_organization VALUES ('20159','มหาวิทยาลัยราชภัฏสุราษฎร์ธานี');
INSERT INTO f_welfare_organization VALUES ('20160','มหาวิทยาลัยราชภัฏสุรินทร์');
INSERT INTO f_welfare_organization VALUES ('20161','มหาวิทยาลัยราชภัฏหมู่บ้านจอมบึง');
INSERT INTO f_welfare_organization VALUES ('20162','มหาวิทยาลัยราชภัฏอุดรธานี');
INSERT INTO f_welfare_organization VALUES ('20163','มหาวิทยาลัยราชภัฏอุตรดิตถ์');
INSERT INTO f_welfare_organization VALUES ('20164','มหาวิทยาลัยราชภัฏอุบลราชธานี');
INSERT INTO f_welfare_organization VALUES ('20112','มหาวิทยาลัยรามคําแหง');
INSERT INTO f_welfare_organization VALUES ('20113','มหาวิทยาลัยศรีนครินทรวิโรฒ');
INSERT INTO f_welfare_organization VALUES ('20114','มหาวิทยาลัยศิลปากร');
INSERT INTO f_welfare_organization VALUES ('20115','มหาวิทยาลัยสงขลานครินทร์');
INSERT INTO f_welfare_organization VALUES ('20116','มหาวิทยาลัยสุโขทัยธรรมาธิราช');
INSERT INTO f_welfare_organization VALUES ('17002','สํานักงานปลัดกระทรวงแรงงาน');
INSERT INTO f_welfare_organization VALUES ('18002','สํานักงานปลัดกระทรวงวัฒนธรรม');
INSERT INTO f_welfare_organization VALUES ('19002','สํานักงานปลัดกระทรวงวิทยาศาตร์และเทคโนโลยี');
INSERT INTO f_welfare_organization VALUES ('20002','สํานักงานปลัดกระทรวงศึกษาธิการ');
INSERT INTO f_welfare_organization VALUES ('21002','สํานักงานปลัดกระทรวงสาธารณสุข');
INSERT INTO f_welfare_organization VALUES ('22002','สํานักงานปลัดกระทรวงอุตสาหกรรม');
INSERT INTO f_welfare_organization VALUES ('25008','สํานักงานป้องกันและปราบปรามการฟอกเงิน');
INSERT INTO f_welfare_organization VALUES ('05003','สํานักงานพัฒนาการกีฬาและนันทนาการ');
INSERT INTO f_welfare_organization VALUES ('05004','สํานักงานพัฒนาการท่องเที่ยว');
INSERT INTO f_welfare_organization VALUES ('22007','สํานักงานมาตรฐานผลิตภัณฑ์อุตสาหกรรม');
INSERT INTO f_welfare_organization VALUES ('07014','สํานักงานมาตรฐานสินค้าเกษตรและอาหารแห่งชาติ');
INSERT INTO f_welfare_organization VALUES ('25001','สํานักงานราชเลขาธิการ');
INSERT INTO f_welfare_organization VALUES ('25010','สํานักงานเลขาธิการวุฒิสภา');
INSERT INTO f_welfare_organization VALUES ('20003','สํานักงานเลขาธิการสภาการศึกษา');
INSERT INTO f_welfare_organization VALUES ('25011','สํานักงานเลขาธิการสภาผู้แทนราษฎร');
INSERT INTO f_welfare_organization VALUES ('26004','สํานักงานศาลปกครอง');
INSERT INTO f_welfare_organization VALUES ('26008','สํานักงานศาลยุติธรรม');
INSERT INTO f_welfare_organization VALUES ('26002','สํานักงานศาลรัฐธรรมนูญ');
INSERT INTO f_welfare_organization VALUES ('18006','สํานักงานศิลปวัฒนธรรมร่วมสมัย');
INSERT INTO f_welfare_organization VALUES ('07015','สํานักงานเศรษฐกิจการเกษตร');
INSERT INTO f_welfare_organization VALUES ('03011','สํานักงานเศรษฐกิจการคลัง');
INSERT INTO f_welfare_organization VALUES ('22008','สํานักงานเศรษฐกิจอุตสาหกรรม');
INSERT INTO f_welfare_organization VALUES ('06005','สํานักงานส่งเสริมสวัสดิภาพและพิทักษ์เด็ก เยาวชน ผู้ด้อยโอกาส');
INSERT INTO f_welfare_organization VALUES ('11005','สํานักงานสถิติแห่งชาติ');
INSERT INTO f_welfare_organization VALUES ('01008','สํานักงานสภาความมั่นคงแห่งชาติ');
INSERT INTO f_welfare_organization VALUES ('25015','สํานักงานสภาที่ปรึกษาเศรษฐกิจและสังคมแห่งชาติ');
INSERT INTO f_welfare_organization VALUES ('25009','สํานักงานอัยการสูงสุด');
INSERT INTO f_welfare_organization VALUES ('02001','สํานักปลัดกระทรวงกลาโหม');
INSERT INTO f_welfare_organization VALUES ('03002','สํานักปลัดกระทรวงการคลัง');
INSERT INTO f_welfare_organization VALUES ('04002','สํานักปลัดกระทรวงการต่างประเทศ');
INSERT INTO f_welfare_organization VALUES ('05002','สํานักปลัดกระทรวงการท่องเที่ยวและกีฬา');
INSERT INTO f_welfare_organization VALUES ('01001','สํานักปลัดสํานักนายกรัฐมนตรี');
INSERT INTO f_welfare_organization VALUES ('25002','สํานักพระราชวัง');
INSERT INTO f_welfare_organization VALUES ('01005','สํานักเลขาธิการคณะรัฐมนตรี');
INSERT INTO f_welfare_organization VALUES ('01004','สํานักเลขาธิการนายกรัฐมนตร');

delete from s_welfare_version where version_number = '3';

insert into  s_welfare_version (s_version_id, version_number, version_description, version_application_number, version_database_number, version_update_time) values (
'9701000000016','3','Hospital OS, Community Edition','1.03.24072007','3.15.0550','2550-01-04 22:25:00');


CREATE TABLE b_welfare_sequence_data (
    b_welfare_sequence_data_id character varying(255) NOT NULL,
    sequence_data_description character varying(255),
    sequence_data_pattern character varying(255), 
    sequence_data_value character varying(255),
    sequence_data_active character varying(255),
    CONSTRAINT b_welfare_sequence_data_pkey PRIMARY KEY (b_welfare_sequence_data_id)
);

INSERT INTO b_welfare_sequence_data VALUES ('ddpn','direct draw patient lot number','1000','1','0');
INSERT INTO b_welfare_sequence_data VALUES ('btn','billtrans lot number','1000','1','0');