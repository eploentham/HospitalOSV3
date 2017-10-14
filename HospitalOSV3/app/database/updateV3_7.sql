------การปรับแก้ในเวอร์ชัน 3.07 สำหรับ update จากเวอร์ชัน 3.06
------
--INSERT INTO s_version VALUES ('9701000000014', '24', 'Hospital OS, Community Edition', '3.01.1148', '3.13.1048', '2548-11-08 22:25:00');
------
--ลบข้อมูลขยะที่อยู่ในตาราง result lab  henbe
delete from t_result_lab where result_lab_staff_record = 'null' and record_date_time = 'null'  and result_lab_value = '';

update f_patient_occupation set patient_occupation_description= 'ข้าราชการบำนาญ' where f_patient_occupation_id = '208';
update f_patient_occupation set patient_occupation_description= 'นักเรียน นักศึกษา' where f_patient_occupation_id = '900';


------amp modify----------------------------------------------------------------------------------------------------
--วันที่ 15/02/2549

--เปลี่ยนชื่อและขนาดการเก็บข้อมูลของฟิลด์ visit_vital_sign_main_symptom เป็น visit_vital_sign_note ขนาด 3000
ALTER TABLE t_visit_vital_sign  DROP COLUMN visit_vital_sign_main_symptom;
ALTER TABLE t_visit_vital_sign  ADD COLUMN  visit_vital_sign_note varchar (4000);

--ปัจจัยเสี่ยง
CREATE TABLE t_patient_risk_factor (
    t_patient_risk_factor_id character varying(255) NOT NULL,
    t_patient_id character varying(255),
    patient_risk_factor_description character varying(4000),
    patient_risk_factor_staff_record character varying(255),
    patient_risk_factor_record_date_time character varying(255)
);
ALTER TABLE ONLY t_patient_risk_factor
    ADD CONSTRAINT t_patient_risk_factor_pkey PRIMARY KEY (t_patient_risk_factor_id);
ALTER INDEX public.t_patient_risk_factor_pkey OWNER TO postgres;

--ประวัติครอบครัว
CREATE TABLE t_patient_family_history (
    t_patient_family_history_id character varying(255) NOT NULL,
    t_patient_id character varying(255),
    patient_family_history_description character varying(4000),
    patient_family_history_staff_record character varying(255),
    patient_family_history_record_date_time character varying(255)
);
ALTER TABLE ONLY t_patient_family_history
    ADD CONSTRAINT t_patient_family_history_pkey PRIMARY KEY (t_patient_family_history_id);
ALTER INDEX public.t_patient_family_history_pkey OWNER TO postgres;

--โรคประจำตัว
CREATE TABLE t_patient_personal_disease (
    t_patient_personal_disease_id character varying(255) NOT NULL,
    t_patient_id character varying(255),
    patient_personal_disease_description character varying(255),
    patient_personal_disease_sick_date character varying(255),
    patient_personal_disease_staff_record character varying(255),
    patient_personal_disease_record_date_time character varying(255)
);
ALTER TABLE ONLY t_patient_personal_disease
    ADD CONSTRAINT t_patient_personal_disease_pkey PRIMARY KEY (t_patient_personal_disease_id);
ALTER INDEX public.t_patient_personal_disease_pkey OWNER TO postgres;

--เพิ่ม authentication ของทันตกรรม
insert into f_employee_authentication values('14','ทันตกรรม');

 insert into  f_gui_action_authen values ('1271','0100','เมนูระบบ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1272','0101','ออกจากระบบ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1273','0200','เมนูมุมมอง','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1274','0201','รีเฟรช','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1275','0202','ปลดล็อก','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1276','0203','ค้นหาผู้ป่วย','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1277','0300','เมนูแถบ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1278','0400','เมนูเครื่องมือ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1279','0401','การนัดหมาย','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1280','0402','รายการนัดทั้งหมด','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1281','0403','ประวัติการรับบริการ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1282','0404','ประวัติรายการสั่งตรวจ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1283','0405','ประวัติการคิดเงิน','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1284','0406','จับคู่คำนำหน้ากับเพศ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1285','0407','ลำดับเลข Sequence ล่าสุด','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1286','0408','การ refer ผู้ป่วย','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1287','0409','รายงานต่าง ๆ ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1288','0500','เมนูผู้ป่วยทั่วไป','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1289','0501','กองทุนยา/การจำหน่ายยา','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1290','0502','ค้างบันทึก','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1291','0503','ยกเลิกการเข้ารับบริการ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1292','0504','ข้อมูลการเกิดอุบัติเหตุ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1293','0505','ข้อมูลการตาย','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1294','0506','ข้อมูลโรคเรื้อรัง','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1295','0507','ข้อมูลโรคเฝ้าระวัง','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1296','0600','เมนูผู้ป่วยใน','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1297','0601','Admit','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1298','0602','ย้อนกลับการ Admit','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1299','0603','ฝากนอน','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1300','0604','ยกเลิกการฝากนอน','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1301','0605','ส่งผู้ป่วยกลับวอร์ด','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1302','0606','ประวัติการสั่งยาต่อเนื่อง','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1303','0700','เมนูการสั่งตรวจ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1304','0701','การคืนยา','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1305','0702','สั่งชุดรายการตรวจรักษา','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1306','0703','สั่งรายการเหมือนครั้งที่แล้ว','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1307','0704','สั่งรายการเหมือนวันที่แล้ว','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1308','0705','สั่งยาต่อเนื่อง','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1309','0800','เมนูรายการพิมพ์','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1310','0801','ใบรายการตรวจรักษา','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1311','0802','ใบ Summary','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1312','0803','ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1313','0804','ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1314','0805','ใบสรุปค่าใช้จ่ายตามรายการ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1315','0806','รายการยาที่เลือก','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1316','0807','ใบ OPD Card','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1317','0808','พิมพ์สติ๊กเกอร์ยา','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1318','0809','ใบ Index','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1319','0900','เมนูการย้อนกลับ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1320','0901','ย้อนกลับทางการเงิน','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1321','0902','ย้อนกลับทางการแพทย์','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1322','1000','เมนูตัวช่วย','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1323','1001','การเชื่อมต่อฐานข้อมูล','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1324','1002','ข้อมูลโปรแกรม','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1325','1003','กำ้หนด path รูปแบบอักษร','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1326','1004','กำ้หนด path งานพิมพ์','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1327','2100','แถบรายชื่อ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1328','2200','แถบรายชื่อตามจุดบริการ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1329','2300','แถบรายชื่อในวอร์ด','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1330','2400','แถบข้อมูลผู้ป่วย','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1331','2500','แถบการรับบริการ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1332','2600','แถบบันทึกอาการเจ็บป่วย','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1333','2700','แถบรายการตรวจรักษา','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1334','2800','แถบการวินิจฉัย','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1335','2900','แถบการเงิน','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1336','3000','แถบแลบ','14','ทันตกรรม','0','1');
 insert into  f_gui_action_authen values ('1337','3100','แถบรังสี','14','ทันตกรรม','0','1');
 insert into  f_gui_action_authen values ('1338','5100','ผู้ดูแลระบบ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1339','5101','ตั้งค่าของระบบ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1340','5102','คนไข้ที่ถูกล็อก','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1341','5103','สิทธิการใช้งาน','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1342','5200','รายชื่อจุดบริการ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1343','5201','รายชื่อวอร์ด','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1344','5202','รายชื่อจุดบริการ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1345','5203','รายชื่อคลินิก','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1346','5204','สถานพยาบาล','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1347','5205','กำหนดคิว','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1348','5300','รายการกลุ่ม','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1349','5301','รายการ Oder','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1350','5302','รายการใบเสร็จ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1351','5400','รายการตรวจรักษา','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1352','5401','รายการตรวจรักษา','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1353','5402','รายการอัตโนมัติ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1354','5403','อาการที่พบบ่อย','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1355','5404','รายการยาชุด','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1356','5405','Dx ที่พบบ่อย','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1357','5406','ด้านการฉาย X-ray','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1358','5407','ท่าการฉาย X-ray','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1359','5408','ฟิล์มสำหรับ X-ray','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1360','5500','รายละเอียดยา','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1361','5501','ช่วงเวลาที่ใช้ยา','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1362','5502','วิธีการใช้ยา','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1363','5503','หน่วยยา','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1364','5600','รายการ ICD','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1365','5601','รายการ ICD-10','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1366','5602','รายการ ICD-9','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1367','5603','รายการโรคเรื้อรัง','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1368','5604','รายการกลุ่ม ICD-10','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1369','5700','การชำระเงิน','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1370','5701','ผู้ชำระเงิน','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1371','5702','รายการส่วนลด','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1372','5703','สิทธิการรักษา','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1373','5800','รายการอื่นๆ','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1374','5801','ผู้ใช้งาน','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1375','5802','แสดงเลข Sequence','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1376','5803','สถานพยาบาลที่ติดตั้ง','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1377','5900','รายงาน','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1378','5901','รายงาน SQL','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1379','5902','รายการคำสั่ง SQL','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1380','5903','รายงาน SQL ตามวันที่','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1381','5904','รายงานคำสั่ง SQL ตามวันที่','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1382','0410','Lab Refer Out','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1383','0411','Lab Refer In','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1384','0810','พิมพ์ใบ Index Xray','14','ทันตกรรม','1','1');
 insert into  f_gui_action_authen values ('1385','0811','พิมพ์ผลแลบ','14','ทันตกรรม','1','1');


delete from f_gui_action_authen where f_authentication_id ='14';
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000008103745362','0100','เมนูระบบ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000001273866125','0101','ออกจากระบบ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000006825744773','0200','เมนูมุมมอง','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000004532291544','0201','รีเฟรช','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000007673310992','0202','ปลดล็อก','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000002062377848','0203','ค้นหาผู้ป่วย','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000002683076389','0300','เมนูแถบ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000006678956097','0400','เมนูเครื่องมือ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000003231107340','0401','การนัดหมาย','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000000947987866','0402','รายการนัดทั้งหมด','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000003622809522','0403','ประวัติการรับบริการ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000005906239596','0404','ประวัติรายการสั่งตรวจ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000009239738089','0405','ประวัติการคิดเงิน','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000004867585977','0406','จับคู่คำนำหน้ากับเพศ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000008988452975','0407','ลำดับเลข Sequence ล่าสุด','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000008120595451','0408','การ refer ผู้ป่วย','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000006458468248','0409','รายงานต่าง ๆ ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000008294568345','0410','Lab Refer Out','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000000938929340','0411','Lab Refer In','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000005178492371','0500','เมนูผู้ป่วยทั่วไป','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000003901706738','0501','กองทุนยา/การจำหน่ายยา','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000009469868606','0502','ค้างบันทึก','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000002023098834','0503','ยกเลิกการเข้ารับบริการ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000007864658561','0504','ข้อมูลการเกิดอุบัติเหตุ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000007734602099','0505','ข้อมูลการตาย','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000004038623634','0506','ข้อมูลโรคเรื้อรัง','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000008768694614','0507','ข้อมูลโรคเฝ้าระวัง','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000007283191822','0600','เมนูผู้ป่วยใน','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000000458106561','0601','Admit','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000000121098458','0602','ย้อนกลับการ Admit','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000007572600740','0603','ฝากนอน','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000009320045363','0604','ยกเลิกการฝากนอน','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000005308130480','0605','ส่งผู้ป่วยกลับวอร์ด','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000008934386745','0606','ประวัติการสั่งยาต่อเนื่อง','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000003407910453','0700','เมนูการสั่งตรวจ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000005829538043','0701','การคืนยา','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000000178173707','0702','สั่งชุดรายการตรวจรักษา','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000005481794295','0703','สั่งรายการเหมือนครั้งที่แล้ว','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000008904223886','0704','สั่งรายการเหมือนวันที่แล้ว','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000000504176211','0705','สั่งยาต่อเนื่อง','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000002572089335','0800','เมนูรายการพิมพ์','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000004539700979','0801','ใบรายการตรวจรักษา','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000001211159801','0802','ใบ Summary','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000007006220223','0803','ใบสรุปค่าใช้จ่ายตามกลุ่มรายการ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000002103394066','0804','ใบสรุปค่าใช้จ่ายตามกลุ่มใบเสร็จ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000008197090558','0805','ใบสรุปค่าใช้จ่ายตามรายการ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000006721067322','0806','รายการยาที่เลือก','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000001871065483','0807','ใบ OPD Card','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000009879812110','0808','พิมพ์สติ๊กเกอร์ยา','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000002540827875','0809','ใบ Index','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000009205444520','0810','พิมพ์ใบ Index Xray','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000004936648514','0811','พิมพ์ผลแลบ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000004746502970','0812','พิมพ์ป้ายชื่อผู้ป่วยใน','14','0','0','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000002730149995','0900','เมนูการย้อนกลับ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000005210582125','0901','ย้อนกลับทางการเงิน','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000005609731581','0902','ย้อนกลับทางการแพทย์','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000004808685909','1000','เมนูตัวช่วย','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000007666733299','1001','การเชื่อมต่อฐานข้อมูล','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000001661469334','1002','ข้อมูลโปรแกรม','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000004149057335','1003','กำ้หนด path รูปแบบอักษร','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000008914143059','1004','กำ้หนด path งานพิมพ์','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000007742087226','2100','แถบรายชื่อ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000004539011357','2200','แถบรายชื่อตามจุดบริการ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000003327007481','2300','แถบรายชื่อในวอร์ด','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000006848349161','2400','แถบข้อมูลผู้ป่วย','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000001731912685','2500','แถบการรับบริการ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000004224128693','2600','แถบบันทึกอาการเจ็บป่วย','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000007325538721','2700','แถบรายการตรวจรักษา','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000008879738624','2701','ปุ่มคิดเงิน','14','1','0','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000001807462432','2702','ปุ่มยืนยัน','14','1','0','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000002643315224','2703','ปุ่มดำเนินการ','14','1','0','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000001528811767','2704','ปุ่มจ่าย','14','1','0','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000004989887834','2705','ปุ่มยกเลิก','14','1','0','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000008846819355','2800','แถบการวินิจฉัย','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000009703761079','2900','แถบการเงิน','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000008994067360','3000','แถบแลบ','14','0','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000001633156208','3100','แถบรังสี','14','0','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000003754410217','5100','ผู้ดูแลระบบ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000001672144712','5101','ตั้งค่าของระบบ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000006813945450','5102','คนไข้ที่ถูกล็อก','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000009192596520','5103','สิทธิการใช้งาน','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000004334957620','5200','รายชื่อจุดบริการ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000002129732660','5201','รายชื่อวอร์ด','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000008327583632','5202','รายชื่อจุดบริการ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000006433706271','5203','รายชื่อคลินิก','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000001418462597','5204','สถานพยาบาล','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000006927424813','5205','กำหนดคิว','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000005194473962','5300','รายการกลุ่ม','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000009204864496','5301','รายการ Oder','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000002395704482','5302','รายการใบเสร็จ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000008167910746','5400','รายการตรวจรักษา','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000006714192320','5401','รายการตรวจรักษา','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000009393054759','5402','รายการอัตโนมัติ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000005099133041','5403','อาการที่พบบ่อย','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000008658111000','5404','รายการยาชุด','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000002456761193','5405','Dx ที่พบบ่อย','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000006715751653','5406','ด้านการฉาย X-ray','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000000051919341','5407','ท่าการฉาย X-ray','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000007503594210','5408','ฟิล์มสำหรับ X-ray','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000008262544277','5500','รายละเอียดยา','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000000314602999','5501','ช่วงเวลาที่ใช้ยา','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000004508320286','5502','วิธีการใช้ยา','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000009557681767','5503','หน่วยยา','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000004977212668','5600','รายการ ICD','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000009313814040','5601','รายการ ICD-10','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000007421407855','5602','รายการ ICD-9','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000006291457699','5603','รายการโรคเรื้อรัง','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000003906460146','5604','รายการกลุ่ม ICD-10','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000001515899938','5700','การชำระเงิน','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000000499596535','5701','ผู้ชำระเงิน','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000003316927540','5702','รายการส่วนลด','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000002906794491','5703','สิทธิการรักษา','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000007229743092','5800','รายการอื่นๆ','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000006851300892','5801','ผู้ใช้งาน','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000000274076716','5802','แสดงเลข Sequence','14','0','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000002272858715','5803','สถานพยาบาลที่ติดตั้ง','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000000860650291','5900','รายงาน','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000005367441686','5901','รายงาน SQL','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000002083600940','5902','รายการคำสั่ง SQL','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000000138389848','5903','รายงาน SQL ตามวันที่','14','1','1','ทันตกรรม',' ');
insert into f_gui_action_authen (f_gui_action_authen_id ,f_gui_action_id ,gui_action_authen_gui_name ,f_authentication_id ,gui_action_authen_is_read ,gui_action_authen_is_write ,gui_action_authen_authentication_name ,gui_action_authen_note ) values ('906000006339382074','5904','รายงานคำสั่ง SQL ตามวันที่','14','1','1','ทันตกรรม',' ');

--เพิ่มผู้ใช้งานทันตกรรม
update b_employee set f_employee_authentication_id = '14' where b_employee_id = '1578036480561';
update b_employee set b_employee_default_tab = '' where b_employee_id = '1578036480561';
update b_employee set f_employee_level_id = '' where b_employee_id = '1578036480561';
update b_employee set f_employee_rule_id = '' where b_employee_id = '1578036480561';

--แก้ไขผู้ใช้งาน Field f_employee_level_id และ f_employee_rule_id ไม่ได้ใช้แล้ว จากการตรวจสอบทุก usecase จะใช้ f_employee_authentication_id แทน
update b_employee set f_employee_level_id = '';
update b_employee set f_employee_rule_id = '';

--วันที่ 20/02/2549

--เพิ่ม Field ในตาราง t_visit_service เพื่อเก็บว่าผู้ป่วยผ่านวอร์ดไหนมาบ้าง
ALTER TABLE t_visit_service  ADD COLUMN b_visit_ward_id varchar (255)  default '';

--แก้ไขจุดบริการ หอผู้ป่วยใน ให้ active เป็น 0 
--เพราะถ้าลบ Record นี้จะทำให้ Code เกิด Exception
update  b_service_point set service_point_active = '0' where b_service_point_id = '2409840463402';

--วันที่ 21/02/2549

--เพิ่ม Field ในตาราง b_employee  เพื่อใช้ในการเตือนแพทย์ให้ลง Dx
ALTER TABLE  b_employee  ADD COLUMN employee_warning_dx varchar (255)  default '0';
update  b_employee set employee_warning_dx = '1' where f_employee_authentication_id = '3';

--เพิ่ม Field ในตาราง b_item เพื่อใช้ในการทำ Lab ปกปิด
ALTER TABLE b_item  ADD COLUMN item_secret varchar (255)  default '0';

--วันที่ 23/02/2549

--เพิ่มตาราง t_patient_appointment_order เพื่อเก็บ order ล่วงหน้าที่ผูกกับการนัด
CREATE TABLE t_patient_appointment_order (
    t_patient_appointment_order_id character varying(255) NOT NULL,
    t_patient_id character varying(255),
    t_patient_appointment_id character varying(255),
    b_item_id character varying(255),
    patient_appointment_order_common_name character varying(255)
);
ALTER TABLE ONLY t_patient_appointment_order
    ADD CONSTRAINT t_patient_appointment_order_pkey PRIMARY KEY (t_patient_appointment_order_id);
ALTER INDEX public.t_patient_appointment_order_pkey OWNER TO postgres;

--วันที่ 02/03/2549

--เพิ่มตาราง เก็บรหัสของสิ่งส่งตรวจที่เป็นแลปปกปิด
CREATE TABLE t_order_lab_secret (
    t_order_lab_secret_id character varying(255) NOT NULL,
    t_order_id character varying(255),
    order_lab_secret_specimen_code character varying(255)
);
ALTER TABLE ONLY t_order_lab_secret
    ADD CONSTRAINT t_order_lab_secret_pkey PRIMARY KEY (t_order_lab_secret_id);
ALTER INDEX public.t_order_lab_secret_pkey OWNER TO postgres;

--วันที่ 03/03/2549

--เพิ่ม field เก็บว่าเป็นแลปปกปิดในตาราง  t_visit_queue_lab เพื่อให้สามารถแยกคิวได้ว่าเป็นคิวที่มีแลปปกปิด
ALTER TABLE t_visit_queue_lab ADD COLUMN visit_queue_order varchar (255);
update t_visit_queue_lab set visit_queue_order = '';
ALTER TABLE t_visit_queue_lab ADD COLUMN visit_queue_secret_code varchar (255);
update t_visit_queue_lab set visit_queue_secret_code = '';

--วันที่ 11/03/2549

--เพิ่มตารางเก็บยามาตรฐาน
CREATE TABLE b_item_drug_standard (
    b_item_drug_standard_id character varying(255) NOT NULL,
    item_drug_standard_number character varying(255),
    item_drug_standard_description character varying(255),
    item_drug_standard_active character varying(255)
);
INSERT INTO b_item_drug_standard VALUES ('2820000000001', 'DRS0001', '2 PAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000002', 'DRS0002', '2PAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000003', 'DRS0003', '3TC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000004', 'DRS0004', '5-AMINOSALICYLIC ACIDAND', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000005', 'DRS0005', '5-ASA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000006', 'DRS0006', '5-FC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000007', 'DRS0007', '5-FU', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000008', 'DRS0008', '5-ht antagonists', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000009', 'DRS0009', '6-MP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000010', 'DRS0010', '8-MOP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000011', 'DRS0011', 'ABBOKINASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000012', 'DRS0012', 'ABCIXIMAB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000013', 'DRS0013', 'ABCIXIMAB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000014', 'DRS0014', 'ABITRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000015', 'DRS0015', 'ACARBOSE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000016', 'DRS0016', 'ACB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000017', 'DRS0017', 'ACCOLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000018', 'DRS0018', 'ACCUPRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000019', 'DRS0019', 'ACCUTANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000020', 'DRS0020', 'ace inhibitors', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000021', 'DRS0021', 'ACEBUTOLOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000022', 'DRS0022', 'ACENOCARD', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000023', 'DRS0023', 'ACETAMINOPHEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000024', 'DRS0024', 'ACETAZOLAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000025', 'DRS0025', 'ACETAZOLAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000026', 'DRS0026', 'ACETOHEXAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000027', 'DRS0027', 'ACETOHYDROXAMIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000028', 'DRS0028', 'ACETOPHENAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000029', 'DRS0029', 'ACETOXYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000030', 'DRS0030', 'ACETYLCYSTEINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000031', 'DRS0031', 'ACHROMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000032', 'DRS0032', 'ACIPIMOX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000033', 'DRS0033', 'ACLOVATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000034', 'DRS0034', 'ACNE AID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000035', 'DRS0035', 'ACNE-AID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000036', 'DRS0036', 'ACNO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000037', 'DRS0037', 'ACNOMEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000038', 'DRS0038', 'ACNOTEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000039', 'DRS0039', 'ACREVASTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000040', 'DRS0040', 'ACRIFLAVINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000041', 'DRS0041', 'ACRITRETIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000042', 'DRS0042', 'ACRIVASTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000043', 'DRS0043', 'ACTIBINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000044', 'DRS0044', 'ACTICORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000045', 'DRS0045', 'ACTIDOSE-AQUA1', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000046', 'DRS0046', 'ACTIFED', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000047', 'DRS0047', 'ACTIMMUNE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000048', 'DRS0048', 'ACTIMOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000049', 'DRS0049', 'ACTINEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000050', 'DRS0050', 'ACTIVASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000051', 'DRS0051', 'ACTIVATED CHARCOAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000052', 'DRS0052', 'ACTRON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000053', 'DRS0053', 'ACUPRIN 81', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000054', 'DRS0054', 'ACYCLOVIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000055', 'DRS0055', 'ADAGEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000056', 'DRS0056', 'ADALAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000057', 'DRS0057', 'ADAMON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000058', 'DRS0058', 'ADAPALANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000059', 'DRS0059', 'ADAPALENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000060', 'DRS0060', 'ADENOCARD', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000061', 'DRS0061', 'ADENOCOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000062', 'DRS0062', 'ADENOSINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000063', 'DRS0063', 'ADH', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000064', 'DRS0064', 'ADIPEX-P', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000065', 'DRS0065', 'ADIPIODONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000066', 'DRS0066', 'ADIPOST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000067', 'DRS0067', 'ADRENALINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000068', 'DRS0068', 'ADRIAMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000069', 'DRS0069', 'ADRUCIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000070', 'DRS0070', 'AEROSEB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000071', 'DRS0071', 'AFRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000072', 'DRS0072', 'AFTER BURN DOUBLE STRENGTH', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000073', 'DRS0073', 'AIRET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000074', 'DRS0074', 'AK-CON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000075', 'DRS0075', 'AKINETON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000076', 'DRS0076', 'AK-PENTOLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000077', 'DRS0077', 'AK-T-CAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000078', 'DRS0078', 'AK-ZOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000079', 'DRS0079', 'ALBALON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000080', 'DRS0080', 'albendazole', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000081', 'DRS0081', 'ALBUMIN HUMAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000082', 'DRS0082', 'ALBUMINAR-25', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000083', 'DRS0083', 'ALBUTEIN 5%', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000084', 'DRS0084', 'ALBUTEROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000085', 'DRS0085', 'ALCLOMETASONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000086', 'DRS0086', 'alcuronium', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000087', 'DRS0087', 'ALCURONIUM DICHLORIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000088', 'DRS0088', 'ALDACTAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000089', 'DRS0089', 'ALDACTONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000090', 'DRS0090', 'ALDESLEUKIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000091', 'DRS0091', 'ALDOMET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000092', 'DRS0092', 'ALFACALCIDOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000093', 'DRS0093', 'ALFENTA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000094', 'DRS0094', 'ALFENTANIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000095', 'DRS0095', 'ALFERON N', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000096', 'DRS0096', 'ALFUZOSIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000097', 'DRS0097', 'ALGLUCERASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000098', 'DRS0098', 'alkanones', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000099', 'DRS0099', 'ALKERAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000100', 'DRS0100', 'alkyl sulfonates', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000101', 'DRS0101', 'ALLEGRA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000102', 'DRS0102', 'ALLERCORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000103', 'DRS0103', 'ALLERDRYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000104', 'DRS0104', 'ALLEREST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000105', 'DRS0105', 'ALLERMAX CAPLETS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000106', 'DRS0106', 'ALLER-MED', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000107', 'DRS0107', 'ALLOPURINOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000108', 'DRS0108', 'ALMITRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000109', 'DRS0109', 'ALOMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000110', 'DRS0110', 'alpha blockers', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000111', 'DRS0111', 'ALPHA1-PROTEINASE INHIBITOR,HUMAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000112', 'DRS0112', 'ALPHACAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000113', 'DRS0113', 'ALPHADERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000114', 'DRS0114', 'ALPHAGAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000115', 'DRS0115', 'ALPHANINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000116', 'DRS0116', 'ALPHATREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000117', 'DRS0117', 'ALPHOSYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000118', 'DRS0118', 'ALPRAZ', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000119', 'DRS0119', 'ALPRAZOLAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000120', 'DRS0120', 'ALPROSTADIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000121', 'DRS0121', 'ALTACE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000122', 'DRS0122', 'ALTEPLACE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000123', 'DRS0123', 'ALTI-BROMOCRIPTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000124', 'DRS0124', 'ALTI-VALPROIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000125', 'DRS0125', 'ALTRETAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000126', 'DRS0126', 'aluminium hydroxide', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000127', 'DRS0127', 'aluminium salts', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000128', 'DRS0128', 'ALURATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000129', 'DRS0129', 'AMANTADINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000130', 'DRS0130', 'AMARYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000131', 'DRS0131', 'AMBENONIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000132', 'DRS0132', 'AMBIEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000133', 'DRS0133', 'AMBROXOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000134', 'DRS0134', 'AMCINONIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000135', 'DRS0135', 'AMERICAINE TOPICAL ANESTHETIC SPRAY', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000136', 'DRS0136', 'AMETHOPTERIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000137', 'DRS0137', 'AMIDATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000138', 'DRS0138', 'amikacin', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000139', 'DRS0139', 'AMINEPTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000140', 'DRS0140', 'AMINOBENZOATE POTASSIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000141', 'DRS0141', 'AMINOCARPROIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000142', 'DRS0142', 'aminoglycosides', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000143', 'DRS0143', 'AMINOPHYLLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000144', 'DRS0144', 'aminoquinolines', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000145', 'DRS0145', 'AMINOSALICYLATE SODIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000146', 'DRS0146', 'amiodarone', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000147', 'DRS0147', 'AMIPAQUE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000148', 'DRS0148', 'amitriptyline', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000149', 'DRS0149', 'AMITRYPTYLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000150', 'DRS0150', 'AMLODIPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000151', 'DRS0151', 'AMMONIA N13', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000152', 'DRS0152', 'AMMONIA SPIRIT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000153', 'DRS0153', 'AMMONIATED MERCURY', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000154', 'DRS0154', 'AMMONIUM CARBONATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000155', 'DRS0155', 'AMMONIUM CHLORIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000156', 'DRS0156', 'AMOBARBITA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000157', 'DRS0157', 'AMOXAPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000158', 'DRS0158', 'AMOXI', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000159', 'DRS0159', 'AMOXICILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000160', 'DRS0160', 'AMOXICILLIN AND CLAVULANATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000161', 'DRS0161', 'AMOXIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000162', 'DRS0162', 'amoxycillin', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000163', 'DRS0163', 'AMPHETAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000164', 'DRS0164', 'AMPHOCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000165', 'DRS0165', 'AMPHOTERICIN B', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000166', 'DRS0166', 'AMPICILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000167', 'DRS0167', 'AMPICILLIN AND SULBACTAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000168', 'DRS0168', 'AMPICIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000169', 'DRS0169', 'AMRINONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000170', 'DRS0170', 'AMRINONE LACTATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000171', 'DRS0171', 'AMYL NITRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000172', 'DRS0172', 'ANACIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000173', 'DRS0173', 'ANAFRANIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000174', 'DRS0174', 'ANASTROZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000175', 'DRS0175', 'ANATRAST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000176', 'DRS0176', 'ANCALIXIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000177', 'DRS0177', 'ANDRO L.A. 200', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000178', 'DRS0178', 'ANDROCUR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000179', 'DRS0179', 'ANDRODERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000180', 'DRS0180', 'androgens', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000181', 'DRS0181', 'androgens (17-alkyl derivatives)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000182', 'DRS0182', 'ANDROID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000183', 'DRS0183', 'ANDROID-F', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000184', 'DRS0184', 'ANDRONATE 100', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000185', 'DRS0185', 'ANDROPOSITORY 200', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000186', 'DRS0186', 'ANDRYL 200', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000187', 'DRS0187', 'ANECTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000188', 'DRS0188', 'ANECTINE FLO-PACK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000189', 'DRS0189', 'ANERGAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000190', 'DRS0190', 'ANEXATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000191', 'DRS0191', 'ANHYDRON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000192', 'DRS0192', 'ANISINDIONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000193', 'DRS0193', 'ANISOTROPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000194', 'DRS0194', 'ANISTREPLASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000195', 'DRS0195', 'ANOREX SR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000196', 'DRS0196', 'ANTABUSE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000197', 'DRS0197', 'ANTACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000198', 'DRS0198', 'antacids', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000199', 'DRS0199', 'anthelmintic drugs', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000200', 'DRS0200', 'ANTHRA-DERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000201', 'DRS0201', 'ANTHRAFORTE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000202', 'DRS0202', 'ANTHRALIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000203', 'DRS0203', 'ANTHRANOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000204', 'DRS0204', 'ANTHRAQUINONE GLYCOSIDES EXTRACT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000205', 'DRS0205', 'ANTHRASCALP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000206', 'DRS0206', 'ANTHRA-TEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000207', 'DRS0207', 'antiandrogens', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000208', 'DRS0208', 'antiarrhythmic agents', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000209', 'DRS0209', 'antiarrhythmic agents, class i', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000210', 'DRS0210', 'antiarrhythmic agents, class ii', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000211', 'DRS0211', 'antiarrhythmic agents, class iii', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000212', 'DRS0212', 'antiarrhythmic agents, class iv', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000213', 'DRS0213', 'antiarrhythmics agents, class i', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000214', 'DRS0214', 'anticholinergics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000215', 'DRS0215', 'anticholinesterases', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000216', 'DRS0216', 'anticoagulants', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000217', 'DRS0217', 'anticonvulsant  barbiturates', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000218', 'DRS0218', 'ANTIDOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000219', 'DRS0219', 'antiepileptics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000220', 'DRS0220', 'antiestrogens', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000221', 'DRS0221', 'ANTIFLEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000222', 'DRS0222', 'antifungals', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000223', 'DRS0223', 'antihistamine, sedating', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000224', 'DRS0224', 'antihistamines, nonsedating', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000225', 'DRS0225', 'antihistamines, sedating', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000226', 'DRS0226', 'antimuscarinics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000227', 'DRS0227', 'antineoplastic agents', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000228', 'DRS0228', 'antineoplastic alkylating agents', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000229', 'DRS0229', 'antineoplastic antibiotics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000230', 'DRS0230', 'antineoplastic antimetabolites', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000231', 'DRS0231', 'antineoplastic corticosteroids', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000232', 'DRS0232', 'antineoplastic hormones & related a', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000233', 'DRS0233', 'antineoplastic natural products', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000234', 'DRS0234', 'antiplatelet drugs', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000235', 'DRS0235', 'antipsychotics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000236', 'DRS0236', 'ANTIPYRINE BENZOCAIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000237', 'DRS0237', 'ANTIRABIC VACCINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000238', 'DRS0238', 'antirabies hyperimmune serum', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000239', 'DRS0239', 'ANTIVENOM SERA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000240', 'DRS0240', 'ANTIVERT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000241', 'DRS0241', 'antivirals', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000242', 'DRS0242', 'ANTRYPOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000243', 'DRS0243', 'ANTURAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000244', 'DRS0244', 'ANTURANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000245', 'DRS0245', 'ANUCORT-HC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000246', 'DRS0246', 'ANUSOL-HC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000247', 'DRS0247', 'anxiolytics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000248', 'DRS0248', 'APHRODYNE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000249', 'DRS0249', 'APO-BROMOCRIPTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000250', 'DRS0250', 'APO-CHLORAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000251', 'DRS0251', 'APO-HYDRO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000252', 'DRS0252', 'APOMORPHINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000253', 'DRS0253', 'APO-PEN VK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000254', 'DRS0254', 'APO-TRIAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000255', 'DRS0255', 'APPECON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000256', 'DRS0256', 'APRACLONIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000257', 'DRS0257', 'APRESAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000258', 'DRS0258', 'APRESOLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000259', 'DRS0259', 'APROBARBITAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000260', 'DRS0260', 'APROTININ', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000261', 'DRS0261', 'APROZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000262', 'DRS0262', 'AQUACHLORAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000263', 'DRS0263', 'AQUASOL A', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000264', 'DRS0264', 'AQUATAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000265', 'DRS0265', 'ARALEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000266', 'DRS0266', 'ARAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000267', 'DRS0267', 'ARCO PAIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000268', 'DRS0268', 'AREDIA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000269', 'DRS0269', 'ARFONAD', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000270', 'DRS0270', 'ARISTOCORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000271', 'DRS0271', 'ARLIDIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000272', 'DRS0272', 'ARRESTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000273', 'DRS0273', 'ARTANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000274', 'DRS0274', 'ARTESUNATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000275', 'DRS0275', 'ARTICULOSE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000276', 'DRS0276', 'ARTIFICIAL TEARS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000277', 'DRS0277', 'ARTRIA S.R', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000278', 'DRS0278', 'ASACOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000279', 'DRS0279', 'ASBRON G', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000280', 'DRS0280', 'ASCABIOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000281', 'DRS0281', 'ASCORBIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000282', 'DRS0282', 'ASENDIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000283', 'DRS0283', 'ASPARAGINASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000284', 'DRS0284', 'ASPARTAME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000285', 'DRS0285', 'ASPERGUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000286', 'DRS0286', 'ASPIRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000287', 'DRS0287', 'ASPIRTAB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000288', 'DRS0288', 'ASTEMIZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000289', 'DRS0289', 'ASTONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000290', 'DRS0290', 'ASTRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000291', 'DRS0291', 'ATABRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000292', 'DRS0292', 'ATARAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000293', 'DRS0293', 'ATENOLOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000294', 'DRS0294', 'ATENOLOL AND CHLORTHALIDONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000295', 'DRS0295', 'ATIVAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000296', 'DRS0296', 'ATOVAQUONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000297', 'DRS0297', 'ATRACURIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000298', 'DRS0298', 'ATRETOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000299', 'DRS0299', 'ATROCARPUS LAKOOCHA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000300', 'DRS0300', 'ATROMID-S', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000301', 'DRS0301', 'atropine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000302', 'DRS0302', 'ATROPINE SULFATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000303', 'DRS0303', 'ATROVENT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000304', 'DRS0304', 'ATTAPULGITE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000305', 'DRS0305', 'AURALGAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000306', 'DRS0306', 'AURANOFIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000307', 'DRS0307', 'AVEENO ACNE BAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000308', 'DRS0308', 'AVENTYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000309', 'DRS0309', 'AVLOSULFON.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000310', 'DRS0310', 'AXID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000311', 'DRS0311', 'AXSAIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000312', 'DRS0312', 'AZACTAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000313', 'DRS0313', 'azathioprine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000314', 'DRS0314', 'AZELAIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000315', 'DRS0315', 'AZELASTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000316', 'DRS0316', 'AZELEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000317', 'DRS0317', 'AZEP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000318', 'DRS0318', 'AZITHROMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000319', 'DRS0319', 'AZLOCILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000320', 'DRS0320', 'AZMACORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000321', 'DRS0321', 'azole antifungal agents', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000322', 'DRS0322', 'AZT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000323', 'DRS0323', 'AZTREONAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000324', 'DRS0324', 'AZULFIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000325', 'DRS0325', 'BACAMPICILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000326', 'DRS0326', 'BACLOFEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000327', 'DRS0327', 'BACTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000328', 'DRS0328', 'BACTOCILL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000329', 'DRS0329', 'BACTRIM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000330', 'DRS0330', 'BACTROBAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000331', 'DRS0331', 'BAKING SODA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000332', 'DRS0332', 'BALMINIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000333', 'DRS0333', 'BALNETAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000334', 'DRS0334', 'BAMBEC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000335', 'DRS0335', 'BAMBUTEROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000336', 'DRS0336', 'BANAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000337', 'DRS0337', 'BANFLEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000338', 'DRS0338', 'BANOPHEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000339', 'DRS0339', 'BARBITA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000340', 'DRS0340', 'barbiturate anesthetics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000341', 'DRS0341', 'barbiturates', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000342', 'DRS0342', 'barbiturates anesthetics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000343', 'DRS0343', 'BARICON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000344', 'DRS0344', 'BARIUM SULFATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000345', 'DRS0345', 'BAROBAG', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000346', 'DRS0346', 'BARO-CA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000347', 'DRS0347', 'BARON-X', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000348', 'DRS0348', 'BAROSPERSE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000349', 'DRS0349', 'BARRIERE-HC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000350', 'DRS0350', 'BCG VACCINE(DRIED)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000351', 'DRS0351', 'BEBEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000352', 'DRS0352', 'BEBULIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000353', 'DRS0353', 'BECAMPENICILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000354', 'DRS0354', 'BECANTEX DS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000355', 'DRS0355', 'BECLODISK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000356', 'DRS0356', 'BECLOFORTE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000357', 'DRS0357', 'BECLOMETHASONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000358', 'DRS0358', 'BECLOVENT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000359', 'DRS0359', 'BECONASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000360', 'DRS0360', 'BEEPEN-VK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000361', 'DRS0361', 'BELDIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000362', 'DRS0362', 'BELGANYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000363', 'DRS0363', 'BELIX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000364', 'DRS0364', 'BELL/ANS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000365', 'DRS0365', 'BELLADONNA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000366', 'DRS0366', 'BENA-D', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000367', 'DRS0367', 'BENADRYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000368', 'DRS0368', 'BEN-AQUA MASQUE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000369', 'DRS0369', 'BENAZEPRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000370', 'DRS0370', 'BENCYCLANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000371', 'DRS0371', 'BENDROFLUMETHIAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000372', 'DRS0372', 'BENEFIX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000373', 'DRS0373', 'BENOXYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000374', 'DRS0374', 'BENSULFOID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000375', 'DRS0375', 'BENTIROMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000376', 'DRS0376', 'BENTOQUATAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000377', 'DRS0377', 'BENZAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000378', 'DRS0378', 'BENZACOT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000379', 'DRS0379', 'BENZAGEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000380', 'DRS0380', 'BENZALKONIUM CHLORIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000381', 'DRS0381', 'BENZASHAVE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000382', 'DRS0382', 'BENZATHINE BENZYLPENICILL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000383', 'DRS0383', 'benzathine penicillin g', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000384', 'DRS0384', 'BENZBROMARONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000385', 'DRS0385', 'benzimidazoles', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000386', 'DRS0386', 'BENZNIDAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000387', 'DRS0387', 'BENZOCAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000388', 'DRS0388', 'benzodiazepines', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000389', 'DRS0389', 'benzodiazepines (ox.)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000390', 'DRS0390', 'BENZONATATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000391', 'DRS0391', 'BENZOYL PEROXIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000392', 'DRS0392', 'BENZPHETAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000393', 'DRS0393', 'BENZTHIAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000394', 'DRS0394', 'BENZTROPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000395', 'DRS0395', 'BENZYL BENZOATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000396', 'DRS0396', 'BEPADIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000397', 'DRS0397', 'BEPENIUM HYDROXYNAPTHOATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000398', 'DRS0398', 'BEPRIDIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000399', 'DRS0399', 'BEROTEC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000400', 'DRS0400', 'beta blockers', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000401', 'DRS0401', 'beta blockers (cardioselective)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000402', 'DRS0402', 'beta blockers (nonselective)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000403', 'DRS0403', 'BETA-CAROTENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000404', 'DRS0404', 'BETACORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000405', 'DRS0405', 'BETA-HC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000406', 'DRS0406', 'BETAHISTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000407', 'DRS0407', 'BETAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000408', 'DRS0408', 'BETAMETHASONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000409', 'DRS0409', 'BETAPACE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000410', 'DRS0410', 'BETAPEN-VK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000411', 'DRS0411', 'BETATREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000412', 'DRS0412', 'BETA-VAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000413', 'DRS0413', 'BETAXOLOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000414', 'DRS0414', 'BETHANECHOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000415', 'DRS0415', 'BETNOVATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000416', 'DRS0416', 'BEZAFIBRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000417', 'DRS0417', 'BIAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000418', 'DRS0418', 'BIAXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000419', 'DRS0419', 'BICALUTAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000420', 'DRS0420', 'BICNU', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000421', 'DRS0421', 'biguanides', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000422', 'DRS0422', 'BIO-GAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000423', 'DRS0423', 'BION TEARS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000424', 'DRS0424', 'BIOTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000425', 'DRS0425', 'BIO-WELL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000426', 'DRS0426', 'BIPERIDEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000427', 'DRS0427', 'BISACODYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000428', 'DRS0428', 'BISACODYL AND DOCUSATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000429', 'DRS0429', 'BISMATROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000430', 'DRS0430', 'BISMED', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000431', 'DRS0431', 'BISMUTH SUBSALICYLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000432', 'DRS0432', 'BISOPROLOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000433', 'DRS0433', 'BISTMUTH SUBCARBONATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000434', 'DRS0434', 'BITOLTEROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000435', 'DRS0435', 'BLENOXANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000436', 'DRS0436', 'BLEOMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000437', 'DRS0437', 'BONAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000438', 'DRS0438', 'BONINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000439', 'DRS0439', 'BONTRIL PDM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000440', 'DRS0440', 'BORIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000441', 'DRS0441', 'BREONESIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000442', 'DRS0442', 'BRETYLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000443', 'DRS0443', 'BRETYLIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000444', 'DRS0444', 'BRETYLOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000445', 'DRS0445', 'BREVIBLOC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000446', 'DRS0446', 'BREVOXYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000447', 'DRS0447', 'BRICANYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000448', 'DRS0448', 'BRIMONIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000449', 'DRS0449', 'BRINERDIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000450', 'DRS0450', 'BROMAZEPAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000451', 'DRS0451', 'BROMHEXINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000452', 'DRS0452', 'BROMOCRIPTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000453', 'DRS0453', 'BROMPHENIRAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000454', 'DRS0454', 'BRONCHIAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000455', 'DRS0455', 'BRONCHO-GRIPPOL-DM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000456', 'DRS0456', 'BRONCOMAR GG', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000457', 'DRS0457', 'BRONKOSOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000458', 'DRS0458', 'BRUFEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000459', 'DRS0459', 'BUCLIZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000460', 'DRS0460', 'BUDESONIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000461', 'DRS0461', 'BUMETANIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000462', 'DRS0462', 'BUMINATE 5%', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000463', 'DRS0463', 'BUNASOZIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000464', 'DRS0464', 'BUPHENYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000465', 'DRS0465', 'BUPIVACAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000466', 'DRS0466', 'BUPRENORPHINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000467', 'DRS0467', 'BUPROPION', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000468', 'DRS0468', 'BUSCOPAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000469', 'DRS0469', 'BUSERELIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000470', 'DRS0470', 'BUSODIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000471', 'DRS0471', 'BUSPAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000472', 'DRS0472', 'BUSPIRONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000473', 'DRS0473', 'BUSULFAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000474', 'DRS0474', 'busulphan', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000475', 'DRS0475', 'BUTABARBITAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000476', 'DRS0476', 'BUTALAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000477', 'DRS0477', 'BUTALBITAL, ASPIRINC, CAFFEINE, AND', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000478', 'DRS0478', 'BUTAMBEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000479', 'DRS0479', 'BUTAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000480', 'DRS0480', 'BUTAZOLIDIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000481', 'DRS0481', 'BUTESIN PICRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000482', 'DRS0482', 'BUTISOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000483', 'DRS0483', 'BUTOCONAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000484', 'DRS0484', 'BUTORPHANOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000485', 'DRS0485', 'C2', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000486', 'DRS0486', 'CABERGOLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000487', 'DRS0487', 'CAFETRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000488', 'DRS0488', 'CAFFEINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000489', 'DRS0489', 'CALAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000490', 'DRS0490', 'CALAMOX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000491', 'DRS0491', 'CALAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000492', 'DRS0492', 'CALCIBIND', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000493', 'DRS0493', 'CALCIFEDIOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000494', 'DRS0494', 'CALCIFEROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000495', 'DRS0495', 'CALCIJEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000496', 'DRS0496', 'CALCILEAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000497', 'DRS0497', 'CALCIMAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000498', 'DRS0498', 'CALCIPARINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000499', 'DRS0499', 'CALCIPOTRIENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000500', 'DRS0500', 'CALCIPOTRIOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000501', 'DRS0501', 'CALCITONIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000502', 'DRS0502', 'CALCITRIOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000503', 'DRS0503', 'calcium carbonate', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000504', 'DRS0504', 'calcium channel blockers', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000505', 'DRS0505', 'CALCIUM CHLORIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000506', 'DRS0506', 'CALCIUM DISODIUM EDETATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000507', 'DRS0507', 'CALCIUM DISODIUM VERSENATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000508', 'DRS0508', 'CALCIUM FOLINATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000509', 'DRS0509', 'CALCIUM GLUCONATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000510', 'DRS0510', 'CALCIUM LACTATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000511', 'DRS0511', 'calcium salts', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000512', 'DRS0512', 'CALCIUM SANDOZ', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000513', 'DRS0513', 'CALCIUM SUPPLEMENTS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000514', 'DRS0514', 'CALCIUM-SANDOZ', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000515', 'DRS0515', 'CALDECORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000516', 'DRS0516', 'CALDEROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000517', 'DRS0517', 'CALMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000518', 'DRS0518', 'CALMYLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000519', 'DRS0519', 'CAMPHORATED OPIUM TINCTURE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000520', 'DRS0520', 'CAMPTOSAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000521', 'DRS0521', 'CAPASTAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000522', 'DRS0522', 'CAPITROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000523', 'DRS0523', 'CAPOTEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000524', 'DRS0524', 'CAPREOMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000525', 'DRS0525', 'CAPSAICIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000526', 'DRS0526', 'CAPTOPRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000527', 'DRS0527', 'CARAFATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000528', 'DRS0528', 'CARBACHOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000529', 'DRS0529', 'CARBAMAZEPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000530', 'DRS0530', 'CARBENICILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000531', 'DRS0531', 'CARBENOXOLONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000532', 'DRS0532', 'CARBIDOPA AND LEVODOPA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000533', 'DRS0533', 'CARBIMAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000534', 'DRS0534', 'CARBOCYSTEINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000535', 'DRS0535', 'carbonic anhydrase inhibitors', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000536', 'DRS0536', 'CARBOPLATIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000537', 'DRS0537', 'CARBOPROST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000538', 'DRS0538', 'CARBOPTIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000539', 'DRS0539', 'CARDENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000540', 'DRS0540', 'CARDILATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000541', 'DRS0541', 'CARDIOQUIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000542', 'DRS0542', 'CARDIZEM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000543', 'DRS0543', 'CARDURA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000544', 'DRS0544', 'CARISOPRODOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000545', 'DRS0545', 'CARMOL-HC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000546', 'DRS0546', 'CARMUSTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000547', 'DRS0547', 'CARNITOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000548', 'DRS0548', 'CARTEOLOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000549', 'DRS0549', 'CARTROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000550', 'DRS0550', 'CARVEDILOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000551', 'DRS0551', 'CASANTHRANOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000552', 'DRS0552', 'CASCARA SAGRADA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000553', 'DRS0553', 'CASODEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000554', 'DRS0554', 'CASTER OIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000555', 'DRS0555', 'castor oil', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000556', 'DRS0556', 'CATAFLAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000557', 'DRS0557', 'CATAPRES', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000558', 'DRS0558', 'CAVERJECT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000559', 'DRS0559', 'CCK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000560', 'DRS0560', 'CCNU', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000561', 'DRS0561', 'CEDAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000562', 'DRS0562', 'CEENU', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000563', 'DRS0563', 'CEFACLOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000564', 'DRS0564', 'CEFADROXIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000565', 'DRS0565', 'CEFAMANDOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000566', 'DRS0566', 'CEFAZOLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000567', 'DRS0567', 'CEFEPIME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000568', 'DRS0568', 'CEFIXIME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000569', 'DRS0569', 'CEFMETAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000570', 'DRS0570', 'CEFONICID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000571', 'DRS0571', 'CEFOPERAZONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000572', 'DRS0572', 'CEFOTAXIME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000573', 'DRS0573', 'CEFOTETAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000574', 'DRS0574', 'CEFOTIAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000575', 'DRS0575', 'CEFOXITIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000576', 'DRS0576', 'CEFPIROME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000577', 'DRS0577', 'CEFPODOXIME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000578', 'DRS0578', 'CEFPROZIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000579', 'DRS0579', 'CEFROM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000580', 'DRS0580', 'CEFSULODIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000581', 'DRS0581', 'CEFTAZIDIME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000582', 'DRS0582', 'CEFTIBUTEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000583', 'DRS0583', 'CEFTIZOXIME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000584', 'DRS0584', 'CEFTRIAXONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000585', 'DRS0585', 'CEFUROXIME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000586', 'DRS0586', 'CELLCEPT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000587', 'DRS0587', 'CELLULOSE SODIUM PHOSPHATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000588', 'DRS0588', 'CENTRAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000589', 'DRS0589', 'CEPHALEXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000590', 'DRS0590', 'CEPHALORIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000591', 'DRS0591', 'cephalosporins', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000592', 'DRS0592', 'CEPHALOTHIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000593', 'DRS0593', 'CEPHAPIRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000594', 'DRS0594', 'CEPHRADINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000595', 'DRS0595', 'CEREBYX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000596', 'DRS0596', 'CEREDASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000597', 'DRS0597', 'CERESPAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000598', 'DRS0598', 'CEREZYME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000599', 'DRS0599', 'CERUBIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000600', 'DRS0600', 'CERVIDIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000601', 'DRS0601', 'CESAMET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000602', 'DRS0602', 'CET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000603', 'DRS0603', 'CETAMOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000604', 'DRS0604', 'CETIRIZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000605', 'DRS0605', 'CETRAXATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000606', 'DRS0606', 'CETRIZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000607', 'DRS0607', 'CHARAC-501', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000608', 'DRS0608', 'CHARCOAID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000609', 'DRS0609', 'charcoal interactants', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000610', 'DRS0610', 'CHARCOAL, ACTIVATED', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000611', 'DRS0611', 'CHARCODOTE1', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000612', 'DRS0612', 'CHEMET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000613', 'DRS0613', 'CHENODEOXYCHOLIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000614', 'DRS0614', 'CHERACOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000615', 'DRS0615', 'CHIBROXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000616', 'DRS0616', 'CHLO-AMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000617', 'DRS0617', 'CHLOPHEDIANOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000618', 'DRS0618', 'CHLOPROMAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000619', 'DRS0619', 'CHLORAL HYDRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000620', 'DRS0620', 'CHLORAMBUCIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000621', 'DRS0621', 'CHLORAMPHENICOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000622', 'DRS0622', 'CHLORATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000623', 'DRS0623', 'CHLORDIAZEPOXIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000624', 'DRS0624', 'CHLORDIAZEPOXIDE AND AMITRIPTYLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000625', 'DRS0625', 'CHLORDIAZEPOXIDE AND CLIDINIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000626', 'DRS0626', 'CHLORHEXIDINE GLUCONATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000627', 'DRS0627', 'CHLORMETHINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000628', 'DRS0628', 'CHLORMEZANONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000629', 'DRS0629', 'CHLOROMYCETIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000630', 'DRS0630', 'CHLOROQUINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000631', 'DRS0631', 'CHLOROTHIAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000632', 'DRS0632', 'CHLOROXINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000633', 'DRS0633', 'CHLORPHED', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000634', 'DRS0634', 'CHLORPHENESIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000635', 'DRS0635', 'CHLORPHENIRAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000636', 'DRS0636', 'CHLORPROMAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000637', 'DRS0637', 'CHLORPROPAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000638', 'DRS0638', 'CHLORPROTHIXENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000639', 'DRS0639', 'CHLORSPAN-12', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000640', 'DRS0640', 'CHLORTAB-4', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000641', 'DRS0641', 'CHLORTETRACYCLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000642', 'DRS0642', 'CHLORTHALIDONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000643', 'DRS0643', 'CHLOR-TRIMETON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000644', 'DRS0644', 'chlorzoxazone', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000645', 'DRS0645', 'CHLORZOXAZONE AND ACETAMINOPHEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000646', 'DRS0646', 'CHOLECYSTOKININ', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000647', 'DRS0647', 'CHOLESTYRAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000648', 'DRS0648', 'cholinergic agonists', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000649', 'DRS0649', 'cholinomimetics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000650', 'DRS0650', 'CHOLOGRAFIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000651', 'DRS0651', 'CHOLOXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000652', 'DRS0652', 'CHORIONIC GONADOTROPIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000653', 'DRS0653', 'CHRISTMAS FACTOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000654', 'DRS0654', 'CHROMIC PHOSPHATE P 32', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000655', 'DRS0655', 'CHYMEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000656', 'DRS0656', 'CIBACALCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000657', 'DRS0657', 'CICLONIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000658', 'DRS0658', 'CICLOPIROX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000659', 'DRS0659', 'CICLOSPORIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000660', 'DRS0660', 'CIDOFOVIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000661', 'DRS0661', 'CILAZAPRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000662', 'DRS0662', 'CILOXAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000663', 'DRS0663', 'CIMETIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000664', 'DRS0664', 'CIN QUIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000665', 'DRS0665', 'CINOBAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000666', 'DRS0666', 'CINOXACIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000667', 'DRS0667', 'CIN-QUIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000668', 'DRS0668', 'CIPROFLOXACIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000669', 'DRS0669', 'CISAPRIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000670', 'DRS0670', 'CISATRACURIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000671', 'DRS0671', 'CISPLATIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000672', 'DRS0672', 'CITROCARBONATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000673', 'DRS0673', 'CLADRIBINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000674', 'DRS0674', 'CLARIPEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000675', 'DRS0675', 'CLARITHROMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000676', 'DRS0676', 'CLEAR BY DESIGN 2.5 GEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000677', 'DRS0677', 'CLEARASIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000678', 'DRS0678', 'CLEOCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000679', 'DRS0679', 'CLIDINIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000680', 'DRS0680', 'CLINDAMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000681', 'DRS0681', 'CLINDEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000682', 'DRS0682', 'CLINORIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000683', 'DRS0683', 'CLINOXIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000684', 'DRS0684', 'CLIOQUINOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000685', 'DRS0685', 'CLIPOXIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000686', 'DRS0686', 'CLOBETASOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000687', 'DRS0687', 'CLOFAZIMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000688', 'DRS0688', 'CLOFIBRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000689', 'DRS0689', 'CLOMID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000690', 'DRS0690', 'CLOMIFENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000691', 'DRS0691', 'CLOMIPHENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000692', 'DRS0692', 'CLOMIPRAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000693', 'DRS0693', 'CLONAZEPAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000694', 'DRS0694', 'CLONIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000695', 'DRS0695', 'CLONIDINE AND CHLORTHALIDONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000696', 'DRS0696', 'CLOPATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000697', 'DRS0697', 'CLOPRA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000698', 'DRS0698', 'CLORAZEPATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000699', 'DRS0699', 'clotrimazole', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000700', 'DRS0700', 'CLOTRIMAZOLE (TOPICAL)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000701', 'DRS0701', 'CLOXACILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000702', 'DRS0702', 'CLOXAPEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000703', 'DRS0703', 'CLOXI', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000704', 'DRS0704', 'CLOXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000705', 'DRS0705', 'CLOZAPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000706', 'DRS0706', 'CLOZARIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000707', 'DRS0707', 'COAL TAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000708', 'DRS0708', 'COCAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000709', 'DRS0709', 'codeine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000710', 'DRS0710', 'CODEINE PHOSPHATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000711', 'DRS0711', 'CODERGOCRINE MESYLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000712', 'DRS0712', 'COGENTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000713', 'DRS0713', 'COGNEX.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000714', 'DRS0714', 'COLCHICINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000715', 'DRS0715', 'COLESTID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000716', 'DRS0716', 'COLESTIPOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000717', 'DRS0717', 'COLFOSCERIL, CETYL ALCOHOL, AND TYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000718', 'DRS0718', 'COLISTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000719', 'DRS0719', 'COLLAGEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000720', 'DRS0720', 'COLOFAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000721', 'DRS0721', 'COMBIPRES', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000722', 'DRS0722', 'COMBIVENT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000723', 'DRS0723', 'CONJEC-B', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000724', 'DRS0724', 'CONJUGATED ESTROGENS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000725', 'DRS0725', 'CONJUGATED ESTROGENS AND MEDROXYPRO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000726', 'DRS0726', 'contraceptives, oral', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000727', 'DRS0727', 'COPHENE-B', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000728', 'DRS0728', 'CORDARONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000729', 'DRS0729', 'COREG', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000730', 'DRS0730', 'CORGARD', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000731', 'DRS0731', 'CORIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000732', 'DRS0732', 'CORTAID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000733', 'DRS0733', 'CORT-DOME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000734', 'DRS0734', 'CORTICAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000735', 'DRS0735', 'corticosteroids', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000736', 'DRS0736', 'CORTICOTROPIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000737', 'DRS0737', 'CORTIFAIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000738', 'DRS0738', 'CORTISOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000739', 'DRS0739', 'CORTISONE ACETATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000740', 'DRS0740', 'CORTODERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000741', 'DRS0741', 'CORTRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000742', 'DRS0742', 'CORVERT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000743', 'DRS0743', 'COSMEGEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000744', 'DRS0744', 'COSYNTROPIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000745', 'DRS0745', 'COTAZYM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000746', 'DRS0746', 'COTRIMOXAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000747', 'DRS0747', 'COTYLBUTAZONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000748', 'DRS0748', 'COUGH-X', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000749', 'DRS0749', 'COUMADIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000750', 'DRS0750', 'COZAAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000751', 'DRS0751', 'CPM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000752', 'DRS0752', 'CPN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000753', 'DRS0753', 'CREAMY SS SHAMPOO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000754', 'DRS0754', 'CREO-TERPIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000755', 'DRS0755', 'CRESOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000756', 'DRS0756', 'CRIXIVAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000757', 'DRS0757', 'CROMOLYN SODIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000758', 'DRS0758', 'CROTAMITON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000759', 'DRS0759', 'CUPRESSIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000760', 'DRS0760', 'CUPRIMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000761', 'DRS0761', 'CUTICURA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000762', 'DRS0762', 'CYCLANDELATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000763', 'DRS0763', 'CYCLIDROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000764', 'DRS0764', 'CYCLIZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000765', 'DRS0765', 'CYCLOBENZAPRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000766', 'DRS0766', 'CYCLOGYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000767', 'DRS0767', 'CYCLOMEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000768', 'DRS0768', 'CYCLOPENTOLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000769', 'DRS0769', 'CYCLOPHOSPHAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000770', 'DRS0770', 'CYCLOPROGYNOVA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000771', 'DRS0771', 'CYCLOSERINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000772', 'DRS0772', 'CYCLOSPASMOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000773', 'DRS0773', 'CYCLOSPORIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000774', 'DRS0774', 'CYCLOSPORIN A', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000775', 'DRS0775', 'CYCLOSPORINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000776', 'DRS0776', 'CYCLOTHIAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000777', 'DRS0777', 'CYCOFLEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000778', 'DRS0778', 'CYKLOKAPRON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000779', 'DRS0779', 'CYLERT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000780', 'DRS0780', 'cyproheptadine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000781', 'DRS0781', 'CYPROTERNONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000782', 'DRS0782', 'CYSTADANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000783', 'DRS0783', 'CYSTAGON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000784', 'DRS0784', 'CYSTEAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000785', 'DRS0785', 'CYSTOGRAFIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000786', 'DRS0786', 'CYTARABINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000787', 'DRS0787', 'CYTOMEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000788', 'DRS0788', 'CYTOSAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000789', 'DRS0789', 'CYTOSARZYLOPRIM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000790', 'DRS0790', 'CYTOVENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000791', 'DRS0791', 'CYTOXAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000792', 'DRS0792', 'D.H.E. 45', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000793', 'DRS0793', 'DACARBAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000794', 'DRS0794', 'dactinomycin', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000795', 'DRS0795', 'DAGENAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000796', 'DRS0796', 'DALACIN C', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000797', 'DRS0797', 'DALGAN.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000798', 'DRS0798', 'DALMANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000799', 'DRS0799', 'DALTEPARIN SODIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000800', 'DRS0800', 'DANAPAROID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000801', 'DRS0801', 'DANAZOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000802', 'DRS0802', 'DANOCRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000803', 'DRS0803', 'DANTHRON AND DOCUSATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000804', 'DRS0804', 'DANTRIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000805', 'DRS0805', 'DANTROLENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000806', 'DRS0806', 'DAPIPRAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000807', 'DRS0807', 'dapsone', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000808', 'DRS0808', 'DARANIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000809', 'DRS0809', 'DAUNORUBICIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000810', 'DRS0810', 'DAUNOXOME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000811', 'DRS0811', 'DAYPRO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000812', 'DRS0812', 'DAYTO HIMBIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000813', 'DRS0813', 'DAZAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000814', 'DRS0814', 'DDAVP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000815', 'DRS0815', 'DEC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000816', 'DRS0816', 'DECADERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000817', 'DRS0817', 'DECADRON TURBINAIRE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000818', 'DRS0818', 'DECASPRAY', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000819', 'DRS0819', 'DECLOMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000820', 'DRS0820', 'DEFEROXAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000821', 'DRS0821', 'DEFEROXAMINE MESYLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000822', 'DRS0822', 'DEGEST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000823', 'DRS0823', 'DEHIST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000824', 'DRS0824', 'DEHYDROBENZPERIDOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000825', 'DRS0825', 'DEHYDROCHOLIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000826', 'DRS0826', 'DELACORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000827', 'DRS0827', 'DELAPRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000828', 'DRS0828', 'DEL-AQUA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000829', 'DRS0829', 'DELATEST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000830', 'DRS0830', 'DELATESTRYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000831', 'DRS0831', 'DELSYM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000832', 'DRS0832', 'DELTA-9-TETRAHYDROCANNABINOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000833', 'DRS0833', 'DELTASONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000834', 'DRS0834', 'DEMADEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000835', 'DRS0835', 'DEMECARIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000836', 'DRS0836', 'DEMECLOCYCLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000837', 'DRS0837', 'DEMSER.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000838', 'DRS0838', 'DENOREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000839', 'DRS0839', 'DEPAKENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000840', 'DRS0840', 'DEPAKOTE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000841', 'DRS0841', 'DEPEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000842', 'DRS0842', 'DEPOJECT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000843', 'DRS0843', 'depolarizing muscle relaxants', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000844', 'DRS0844', 'DEPO-MEDROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000845', 'DRS0845', 'DEPONIT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000846', 'DRS0846', 'DEPO-PREDATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000847', 'DRS0847', 'DEPOTEST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000848', 'DRS0848', 'DEPO-TESTOSTERONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000849', 'DRS0849', 'DEPO-TESTOSTERONE CYPIONATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000850', 'DRS0850', 'DEPROIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000851', 'DRS0851', 'DERBAC-M', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000852', 'DRS0852', 'DERMACORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000853', 'DRS0853', 'DERMAFLEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000854', 'DRS0854', 'DERMOPLAST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000855', 'DRS0855', 'DERMOVATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000856', 'DRS0856', 'DERMOXYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000857', 'DRS0857', 'DERMTEX HC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000858', 'DRS0858', 'DES', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000859', 'DRS0859', 'DESERPIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000860', 'DRS0860', 'DESFERAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000861', 'DRS0861', 'DESFLURANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000862', 'DRS0862', 'DESIPRAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000863', 'DRS0863', 'DESMOPRESSIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000864', 'DRS0864', 'DESOXIMETHASONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000865', 'DRS0865', 'DESOXYCORTICOSTERONE ACETATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000866', 'DRS0866', 'desoxymethasone', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000867', 'DRS0867', 'DESQUAM-E', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000868', 'DRS0868', 'DESQUAM-X', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000869', 'DRS0869', 'DESYREL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000870', 'DRS0870', 'DETANTOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000871', 'DRS0871', 'DEXAMETHASONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000872', 'DRS0872', 'DEXFENFLURAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000873', 'DRS0873', 'DEXRAZOXANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000874', 'DRS0874', 'DEXTRAN IN 0.9%NSS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000875', 'DRS0875', 'DEXTROAMPHETAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000876', 'DRS0876', 'DEXTROMETHORPHAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000877', 'DRS0877', 'DEXTROTHYROXINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000878', 'DRS0878', 'DEZOCINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000879', 'DRS0879', 'DFMO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000880', 'DRS0880', 'DFP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000881', 'DRS0881', 'DHPG', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000882', 'DRS0882', 'DHS TAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000883', 'DRS0883', 'DHT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000884', 'DRS0884', 'DIABINESE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000885', 'DRS0885', 'DIAMICRON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000886', 'DRS0886', 'DIAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000887', 'DRS0887', 'DIAMOX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000888', 'DRS0888', 'DIAPID.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000889', 'DRS0889', 'DIAR-AID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000890', 'DRS0890', 'DIARREST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000891', 'DRS0891', 'DIASORB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000892', 'DRS0892', 'DIATRIZOATE AND IODIPAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000893', 'DRS0893', 'DIATRIZOATES', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000894', 'DRS0894', 'DIATROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000895', 'DRS0895', 'DIAZEMULS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000896', 'DRS0896', 'DIAZEPAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000897', 'DRS0897', 'DIAZOXIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000898', 'DRS0898', 'DIBEKACIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000899', 'DRS0899', 'DIBUCAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000900', 'DRS0900', 'DIBUNATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000901', 'DRS0901', 'DICHLORPHENAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000902', 'DRS0902', 'DICLOFENAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000903', 'DRS0903', 'DICLOXACILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000904', 'DRS0904', 'DICUMAROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000905', 'DRS0905', 'DICYCLOMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000906', 'DRS0906', 'didanosine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000907', 'DRS0907', 'DIDREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000908', 'DRS0908', 'DIDRONEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000909', 'DRS0909', 'DIENESTROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000910', 'DRS0910', 'DIETHYLCARBAMAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000911', 'DRS0911', 'DIETHYLPROPION', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000912', 'DRS0912', 'DIETHYLSTILBESTROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000913', 'DRS0913', 'DIETHYLSTILBESTROL AND METHYLTESTOS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000914', 'DRS0914', 'DIETHYLTOLUAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000915', 'DRS0915', 'DIFENIDOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000916', 'DRS0916', 'DIFENOXIN AND ATROPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000917', 'DRS0917', 'DIFFERIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000918', 'DRS0918', 'DIFLORASONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000919', 'DRS0919', 'DIFLUCAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000920', 'DRS0920', 'DIFLUNISAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000921', 'DRS0921', 'DIFLUOROPHATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000922', 'DRS0922', 'digitalis glycosides', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000923', 'DRS0923', 'DIGITOXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000924', 'DRS0924', 'DIGOXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000925', 'DRS0925', 'DIGOXIN IMMUNE FAB (OVINE)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000926', 'DRS0926', 'DIHYDRALAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000927', 'DRS0927', 'DIHYDROERGOTAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000928', 'DRS0928', 'DIHYDROERGOTAMINE-SANDOZ', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000929', 'DRS0929', 'DIHYDROTACHYSTEROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000930', 'DRS0930', 'DIIODOHYDROXYQUIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000931', 'DRS0931', 'DIIODOHYDROXYQUINOLINE.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000932', 'DRS0932', 'DILACOR-XR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000933', 'DRS0933', 'DILANTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000934', 'DRS0934', 'DILOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000935', 'DRS0935', 'DILOXANIDE FUROATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000936', 'DRS0936', 'DILTIAZEM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000937', 'DRS0937', 'DIMELOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000938', 'DRS0938', 'dimenhydrinate', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000939', 'DRS0939', 'dimercaprol', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000940', 'DRS0940', 'DIMERCAPTOSUCCINIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000941', 'DRS0941', 'DIMETANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000942', 'DRS0942', 'DIMETAPP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000943', 'DRS0943', 'DIMETHICONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000944', 'DRS0944', 'DIMETHYL SULFOXIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000945', 'DRS0945', 'DIMETRIOSE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000946', 'DRS0946', 'DINOPROST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000947', 'DRS0947', 'DINOPROSTONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000948', 'DRS0948', 'DIODOQUIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000949', 'DRS0949', 'DIOSPYROS MILLIS(มะเกลือ)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000950', 'DRS0950', 'DIPENTUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000951', 'DRS0951', 'DIPHENHYDRAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000952', 'DRS0952', 'DIPHENIDOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000953', 'DRS0953', 'DIPHENOXYLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000954', 'DRS0954', 'DIPHENOXYLATE AND ATROPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000955', 'DRS0955', 'DIPHENYLHYDANTOIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000956', 'DRS0956', 'DIPHTHERIA AND TETANUS TOXOIDS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000957', 'DRS0957', 'DIPIVEFRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000958', 'DRS0958', 'DIPIVEFRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000959', 'DRS0959', 'DIPRIDACOT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000960', 'DRS0960', 'DIPROPHYLLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000961', 'DRS0961', 'DIPTHERIA ANTITOXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000962', 'DRS0962', 'DIPYRIDAMOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000963', 'DRS0963', 'DIQUINOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000964', 'DRS0964', 'DIRITHROMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000965', 'DRS0965', 'DISIPAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000966', 'DRS0966', 'DISOPYRAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000967', 'DRS0967', 'DISULFIRAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000968', 'DRS0968', 'DITOIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000969', 'DRS0969', 'DITROPAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000970', 'DRS0970', 'DIUCHLOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000971', 'DRS0971', 'DIULO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000972', 'DRS0972', 'diuretics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000973', 'DRS0973', 'DIVALPROEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000974', 'DRS0974', 'DIXARIT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000975', 'DRS0975', 'DM SYRUP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000976', 'DRS0976', 'DMSA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000977', 'DRS0977', 'DMSO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000978', 'DRS0978', 'DOAK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000979', 'DRS0979', 'DOBUJECT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000980', 'DRS0980', 'DOBUTAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000981', 'DRS0981', 'DOBUTREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000982', 'DRS0982', 'DOCETAXEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000983', 'DRS0983', 'DOCTAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000984', 'DRS0984', 'DOCUSATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000985', 'DRS0985', 'DOLOBID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000986', 'DRS0986', 'DOLOMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000987', 'DRS0987', 'domperidone', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000988', 'DRS0988', 'DOM-VALPROIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000989', 'DRS0989', 'DONNAGEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000990', 'DRS0990', 'DONNAGEL-MB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000991', 'DRS0991', 'DONNATAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000992', 'DRS0992', 'DOPAMET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000993', 'DRS0993', 'DOPAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000994', 'DRS0994', 'dopamine d2 antagonists', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000995', 'DRS0995', 'dopaminergic agonists', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000996', 'DRS0996', 'DOPAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000997', 'DRS0997', 'DOPRAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000998', 'DRS0998', 'DORAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000000999', 'DRS0999', 'DORMICUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001000', 'DRS1000', 'DORNASE ALFA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001001', 'DRS1001', 'DORYX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001002', 'DRS1002', 'DORZOLAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001003', 'DRS1003', 'DOSTINEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001004', 'DRS1004', 'DOSULEPIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001005', 'DRS1005', 'DOTHIEPIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001006', 'DRS1006', 'DOVONEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001007', 'DRS1007', 'DOXACURIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001008', 'DRS1008', 'DOXAPRAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001009', 'DRS1009', 'DOXAZOSIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001010', 'DRS1010', 'DOXEPIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001011', 'DRS1011', 'DOXI', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001012', 'DRS1012', 'DOXIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001013', 'DRS1013', 'DOXORUBICIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001014', 'DRS1014', 'DOXY', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001015', 'DRS1015', 'DOXYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001016', 'DRS1016', 'DOXYCYCLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001017', 'DRS1017', 'DRAMAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001018', 'DRS1018', 'DRISDOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001019', 'DRS1019', 'DRISTAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001020', 'DRS1020', 'DRITHO-SCALP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001021', 'DRS1021', 'DRIXORAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001022', 'DRS1022', 'DRONABINOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001023', 'DRS1023', 'DROPERIDOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001024', 'DRS1024', 'DROTAVERINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001025', 'DRS1025', 'DRYOX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001026', 'DRS1026', 'DTIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001027', 'DRS1027', 'DUOTRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001028', 'DRS1028', 'DUPHASTON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001029', 'DRS1029', 'DURAMIST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001030', 'DRS1030', 'DURAQUIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001031', 'DRS1031', 'DUVOID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001032', 'DRS1032', 'DUXARIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001033', 'DRS1033', 'D-VAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001034', 'DRS1034', 'D-VERT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001035', 'DRS1035', 'DYAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001036', 'DRS1036', 'DYCILL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001037', 'DRS1037', 'DYDROGESTERONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001038', 'DRS1038', 'DYFLOS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001039', 'DRS1039', 'DYMELOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001040', 'DRS1040', 'DYNABAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001041', 'DRS1041', 'DYNACIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001042', 'DRS1042', 'DYNACIRC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001043', 'DRS1043', 'DYNAPEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001044', 'DRS1044', 'DYPHYLLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001045', 'DRS1045', 'DYRENIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001046', 'DRS1046', 'EASPRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001047', 'DRS1047', 'ECHOTHIOPHATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001048', 'DRS1048', 'ECONAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001049', 'DRS1049', 'ECOSTATIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001050', 'DRS1050', 'ECOTRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001051', 'DRS1051', 'EDATHAMIL CALCIUM DISODIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001052', 'DRS1052', 'ED-BRON G', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001053', 'DRS1053', 'EDETATE CALCIUM DISODIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001054', 'DRS1054', 'EDETATE DISODIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001055', 'DRS1055', 'EDEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001056', 'DRS1056', 'EDROPHONIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001057', 'DRS1057', 'EDROPHONIUM AND ATROPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001058', 'DRS1058', 'EDTA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001059', 'DRS1059', 'EFLORNITHINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001060', 'DRS1060', 'EHDP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001061', 'DRS1061', 'ELDEPRYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001062', 'DRS1062', 'ELIXOPHYLLIN-GG', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001063', 'DRS1063', 'ELSPAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001064', 'DRS1064', 'EMCYT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001065', 'DRS1065', 'EMETINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001066', 'DRS1066', 'EMEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001067', 'DRS1067', 'EMINASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001068', 'DRS1068', 'EMLA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001069', 'DRS1069', 'EMPIRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001070', 'DRS1070', 'ENALAPRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001071', 'DRS1071', 'ENARIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001072', 'DRS1072', 'ENCAINIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001073', 'DRS1073', 'ENDOCAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001074', 'DRS1074', 'ENECAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001075', 'DRS1075', 'ENFLURANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001076', 'DRS1076', 'ENGERIX B', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001077', 'DRS1077', 'ENLON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001078', 'DRS1078', 'ENLON-PLUS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001079', 'DRS1079', 'ENOXACIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001080', 'DRS1080', 'ENOXAPARIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001081', 'DRS1081', 'ENTERO-H', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001082', 'DRS1082', 'ENTROBAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001083', 'DRS1083', 'ENTROPHEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001084', 'DRS1084', 'ENZYMASE-16', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001085', 'DRS1085', 'EPHEDRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001086', 'DRS1086', 'EPI-C', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001087', 'DRS1087', 'EPIFOAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001088', 'DRS1088', 'EPIFRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001089', 'DRS1089', 'EPINAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001090', 'DRS1090', 'EPINEPHRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001091', 'DRS1091', 'EPINEPHRYL BORATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001092', 'DRS1092', 'EPIRUBICIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001093', 'DRS1093', 'EPITOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001094', 'DRS1094', 'EPIVAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001095', 'DRS1095', 'EPIVIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001096', 'DRS1096', 'EPOETIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001097', 'DRS1097', 'EPOGEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001098', 'DRS1098', 'EPPY/N', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001099', 'DRS1099', 'EPREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001100', 'DRS1100', 'EQUAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001101', 'DRS1101', 'EQUANIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001102', 'DRS1102', 'EQUIBRON G', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001103', 'DRS1103', 'ERGAMISOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001104', 'DRS1104', 'ERGOCALCIFEROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001105', 'DRS1105', 'ERGOMETRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001106', 'DRS1106', 'ERGONOVINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001107', 'DRS1107', 'ergot alkaloids', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001108', 'DRS1108', 'ergotamine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001109', 'DRS1109', 'ERGOTAMINE AND CAFFEINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001110', 'DRS1110', 'ERGOTAMINE TARTRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001111', 'DRS1111', 'ERGOTAMINE, CAFFEINE, AND DIMENHYDR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001112', 'DRS1112', 'ERGOTRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001113', 'DRS1113', 'ERYTHRITYL TETRANITRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001114', 'DRS1114', 'ERYTHROMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001115', 'DRS1115', 'ERYTHROMYCIN AND SULFISOXAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001116', 'DRS1116', 'ERYZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001117', 'DRS1117', 'ESIDRIX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001118', 'DRS1118', 'ESKAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001119', 'DRS1119', 'ESMOLOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001120', 'DRS1120', 'ESOBAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001121', 'DRS1121', 'ESOPHO-CAT ESOPHAGEAL CREAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001122', 'DRS1122', 'ESOPHOTRAST ESOPHAGEAL CREAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001123', 'DRS1123', 'ESTAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001124', 'DRS1124', 'ESTAZOLAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001125', 'DRS1125', 'ESTERIFIED ESTROGENS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001126', 'DRS1126', 'ESTIVIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001127', 'DRS1127', 'ESTRADIOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001128', 'DRS1128', 'ESTRAMUSTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001129', 'DRS1129', 'ESTROGEN+PROGESTOGEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001130', 'DRS1130', 'estrogens', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001131', 'DRS1131', 'ESTROGENS AND PROGESTINS ORAL CONTR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001132', 'DRS1132', 'ESTRONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001133', 'DRS1133', 'ESTROPIPATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001134', 'DRS1134', 'ETHACRYNIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001135', 'DRS1135', 'ETHAMBUTOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001136', 'DRS1136', 'ETHAMOLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001137', 'DRS1137', 'ETHANOLAMINE OLEATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001138', 'DRS1138', 'ETHCHLORVYNOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001139', 'DRS1139', 'ETHINAMATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001140', 'DRS1140', 'ETHINYLESTRADIOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001141', 'DRS1141', 'ETHIONAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001142', 'DRS1142', 'ETHMOZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001143', 'DRS1143', 'ETHOPROPAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001144', 'DRS1144', 'ETHOSUXIMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001145', 'DRS1145', 'ETHOTOIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001146', 'DRS1146', 'ETHYLESTRENOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001147', 'DRS1147', 'ETIBI', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001148', 'DRS1148', 'ETIDRONATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001149', 'DRS1149', 'ETODOLAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001150', 'DRS1150', 'ETODOLIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001151', 'DRS1151', 'ETOMIDATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001152', 'DRS1152', 'ETOPOSIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001153', 'DRS1153', 'ETRETINATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001154', 'DRS1154', 'EUFLEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001155', 'DRS1155', 'EULEXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001156', 'DRS1156', 'EURAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001157', 'DRS1157', 'EVACUPASTE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001158', 'DRS1158', 'EVERONE 200', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001159', 'DRS1159', 'EXACTA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001160', 'DRS1160', 'EXELDERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001161', 'DRS1161', 'EXNA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001162', 'DRS1162', 'EXSEL LOTION SHAMPOO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001163', 'DRS1163', 'EYELUBE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001164', 'DRS1164', 'E-Z-AC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001165', 'DRS1165', 'E-Z-CAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001166', 'DRS1166', 'E-Z-DISK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001167', 'DRS1167', 'E-Z-DOSE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001168', 'DRS1168', 'EZE-DS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001169', 'DRS1169', 'E-Z-HD', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001170', 'DRS1170', 'E-Z-JUG', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001171', 'DRS1171', 'E-Z-PAQUE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001172', 'DRS1172', 'E-Z-PAQUE ENEMA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001173', 'DRS1173', 'E-Z-PAQUE LIQUID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001174', 'DRS1174', 'FACTOR IX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001175', 'DRS1175', 'FACTREL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001176', 'DRS1176', 'FAMCICLOVIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001177', 'DRS1177', 'FAMOTIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001178', 'DRS1178', 'FAMVIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001179', 'DRS1179', 'FANSIDAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001180', 'DRS1180', 'FASTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001181', 'DRS1181', 'FBM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001182', 'DRS1182', 'FELBAMATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001183', 'DRS1183', 'FELBATOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001184', 'DRS1184', 'FELDENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001185', 'DRS1185', 'felodipine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001186', 'DRS1186', 'fenamates', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001187', 'DRS1187', 'FENESIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001188', 'DRS1188', 'fenfluramine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001189', 'DRS1189', 'FENOPROFEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001190', 'DRS1190', 'FENOTEROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001191', 'DRS1191', 'fentanyl', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001192', 'DRS1192', 'FERROUS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001193', 'DRS1193', 'FERROUS FUMARATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001194', 'DRS1194', 'FERROUS GLUCONATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001195', 'DRS1195', 'FERROUS SULFATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001196', 'DRS1196', 'FEXOFENADINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001197', 'DRS1197', 'fibrates', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001198', 'DRS1198', 'FILGRASTIM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001199', 'DRS1199', 'FINAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001200', 'DRS1200', 'FINASTERIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001201', 'DRS1201', 'FIORINAL WITH CODEINE NO.3', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001202', 'DRS1202', 'FLAGYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001203', 'DRS1203', 'FLAMAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001204', 'DRS1204', 'FLATULEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001205', 'DRS1205', 'FLAVOXATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001206', 'DRS1206', 'FLAXEDIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001207', 'DRS1207', 'FLECAINIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001208', 'DRS1208', 'FLEXERIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001209', 'DRS1209', 'FLEXOJECT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001210', 'DRS1210', 'FLINT SSD', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001211', 'DRS1211', 'FLO-COAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001212', 'DRS1212', 'FLOCTAFENINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001213', 'DRS1213', 'FLOROPRYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001214', 'DRS1214', 'FLOXACILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001215', 'DRS1215', 'FLOXURIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001216', 'DRS1216', 'FLOZENGES', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001217', 'DRS1217', 'FLUANXOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001218', 'DRS1218', 'FLUCONAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001219', 'DRS1219', 'FLUCYTOSINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001220', 'DRS1220', 'FLUDARA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001221', 'DRS1221', 'FLUDARABINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001222', 'DRS1222', 'FLUDEOXYGLUCOSE F 18', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001223', 'DRS1223', 'FLUDILAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001224', 'DRS1224', 'FLUDROCORTISONE ACETATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001225', 'DRS1225', 'FLUIMUCIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001226', 'DRS1226', 'FLUMADINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001227', 'DRS1227', 'FLUMAZENIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001228', 'DRS1228', 'FLUNARIZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001229', 'DRS1229', 'FLUNISOLIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001230', 'DRS1230', 'FLUNITRAZEPAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001231', 'DRS1231', 'FLUOCINONIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001232', 'DRS1232', 'FLUOR-A-DAY', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001233', 'DRS1233', 'FLUORITAB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001234', 'DRS1234', 'FLUORITABS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001235', 'DRS1235', 'FLUORODEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001236', 'DRS1236', 'FLUOROMETHOLONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001237', 'DRS1237', 'FLUOROSOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001238', 'DRS1238', 'FLUOROURACIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001239', 'DRS1239', 'FLUOXETINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001240', 'DRS1240', 'FLUOXYMESTERONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001241', 'DRS1241', 'FLUPAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001242', 'DRS1242', 'FLUPENTHIXOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001243', 'DRS1243', 'FLUPHENAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001244', 'DRS1244', 'FLUPREDNISOLONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001245', 'DRS1245', 'FLURA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001246', 'DRS1246', 'FLURAZEPAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001247', 'DRS1247', 'FLUTAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001248', 'DRS1248', 'FLUTEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001249', 'DRS1249', 'FLUTICASONE (NASAL)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001250', 'DRS1250', 'FLUVASTATIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001251', 'DRS1251', 'FLUVOXAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001252', 'DRS1252', 'FOILLECORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001253', 'DRS1253', 'FOLEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001254', 'DRS1254', 'folic acid analogs', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001255', 'DRS1255', 'FOLIC,FOLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001256', 'DRS1256', 'FORMALDEHYDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001257', 'DRS1257', 'FOSCARNET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001258', 'DRS1258', 'FOSCAVIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001259', 'DRS1259', 'FOSINOPRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001260', 'DRS1260', 'FOSPHENYTOIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001261', 'DRS1261', 'FOSTEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001262', 'DRS1262', 'FOSTEX MEDICATED CLEANSING CREAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001263', 'DRS1263', 'FOSTRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001264', 'DRS1264', 'FOURNEAU', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001265', 'DRS1265', 'FOWLER''S', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001266', 'DRS1266', 'FUDR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001267', 'DRS1267', 'FULVICIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001268', 'DRS1268', 'FURADANTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001269', 'DRS1269', 'FURALAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001270', 'DRS1270', 'FURATOIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001271', 'DRS1271', 'FURAZOLIDONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001272', 'DRS1272', 'FUROSEMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001273', 'DRS1273', 'FUROSIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001274', 'DRS1274', 'FUROXONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001275', 'DRS1275', 'GABAPENTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001276', 'DRS1276', 'GADODIAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001277', 'DRS1277', 'GADOPENTETATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001278', 'DRS1278', 'GADOTERIDOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001279', 'DRS1279', 'GALLAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001280', 'DRS1280', 'GAMMA BENZENE HEXACHLORIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001281', 'DRS1281', 'GAMMA GLOBULIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001282', 'DRS1282', 'GAMULIN RH', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001283', 'DRS1283', 'GANCICLOVIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001284', 'DRS1284', 'GANTANOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001285', 'DRS1285', 'GARAMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001286', 'DRS1286', 'GAS-GANGRENE ANTITOXIN(MIXED)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001287', 'DRS1287', 'GBH', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001288', 'DRS1288', 'GEE-GEE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001289', 'DRS1289', 'GEMCITABINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001290', 'DRS1290', 'GEMFIBROZIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001291', 'DRS1291', 'GEMONIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001292', 'DRS1292', 'GEMZAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001293', 'DRS1293', 'GENABID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001294', 'DRS1294', 'GEN-ALLERATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001295', 'DRS1295', 'GENASPORE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001296', 'DRS1296', 'GENASYME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001297', 'DRS1297', 'general anesthetics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001298', 'DRS1298', 'GENSAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001299', 'DRS1299', 'GENTAMAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001300', 'DRS1300', 'GENTAMICIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001301', 'DRS1301', 'GENTIAN VIOLET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001302', 'DRS1302', 'GEN-XENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001303', 'DRS1303', 'GEOCILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001304', 'DRS1304', 'GEOPEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001305', 'DRS1305', 'GERMANIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001306', 'DRS1306', 'GESTRINONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001307', 'DRS1307', 'GIL-PAQUE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001308', 'DRS1308', 'GLAUCON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001309', 'DRS1309', 'GLICLAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001310', 'DRS1310', 'GLIMEPIRIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001311', 'DRS1311', 'GLIPIZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001312', 'DRS1312', 'GLOBUMAN BERNA HEPATITIS A', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001313', 'DRS1313', 'GLO-SEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001314', 'DRS1314', 'GLUCAGON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001315', 'DRS1315', 'GLUCOPHAGE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001316', 'DRS1316', 'GLUCOTROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001317', 'DRS1317', 'GLUTARALDEHYDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001318', 'DRS1318', 'GLUTETHIMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001319', 'DRS1319', 'GLYCERIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001320', 'DRS1320', 'GLYCERYL GUAIACOLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001321', 'DRS1321', 'GLYCERYL-T', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001322', 'DRS1322', 'GLYCOPYRROLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001323', 'DRS1323', 'GLYCOTUSS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001324', 'DRS1324', 'GLYCYRRHIZA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001325', 'DRS1325', 'GLYROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001326', 'DRS1326', 'GLYSET.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001327', 'DRS1327', 'GOLD COMPOUNDS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001328', 'DRS1328', 'GONADORELIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001329', 'DRS1329', 'GONAK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001330', 'DRS1330', 'GONIOSOFT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001331', 'DRS1331', 'GOSERELIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001332', 'DRS1332', 'GRANISETRON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001333', 'DRS1333', 'GRIFULVIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001334', 'DRS1334', 'GRISACTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001335', 'DRS1335', 'GRISEOFULVIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001336', 'DRS1336', 'GRISOVIN-FP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001337', 'DRS1337', 'GUAIFENESIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001338', 'DRS1338', 'GUANABENZ', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001339', 'DRS1339', 'GUANADREL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001340', 'DRS1340', 'GUANETHIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001341', 'DRS1341', 'GUANFACINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001342', 'DRS1342', 'GUIATUSS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001343', 'DRS1343', 'G-WELL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001344', 'DRS1344', 'GYNECON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001345', 'DRS1345', 'GYNECORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001346', 'DRS1346', 'H2OXYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001347', 'DRS1347', 'HABITROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001348', 'DRS1348', 'HALAZEPAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001349', 'DRS1349', 'HALCION', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001350', 'DRS1350', 'HALDOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001351', 'DRS1351', 'HALFPRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001352', 'DRS1352', 'HALOPERIDOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001353', 'DRS1353', 'HALOPROGIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001354', 'DRS1354', 'HALOTESTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001355', 'DRS1355', 'HALOTEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001356', 'DRS1356', 'HALOTHANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001357', 'DRS1357', 'HALOTUSSIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001358', 'DRS1358', 'HARMONYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001359', 'DRS1359', 'HAVRIX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001360', 'DRS1360', 'HBVAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001361', 'DRS1361', 'HC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001362', 'DRS1362', 'HCTZ', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001363', 'DRS1363', 'HCTZ+AMILORIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001364', 'DRS1364', 'HCTZ+TRIAMTERINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001365', 'DRS1365', 'HD 200 PLUS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001366', 'DRS1366', 'HD 85', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001367', 'DRS1367', 'HEADACHE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001368', 'DRS1368', 'HEALTHPRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001369', 'DRS1369', 'heavy-metal antagonists', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001370', 'DRS1370', 'HEMABATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001371', 'DRS1371', 'HEMRIL-HC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001372', 'DRS1372', 'HEPALEAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001373', 'DRS1373', 'HEPARIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001374', 'DRS1374', 'HEPATECT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001375', 'DRS1375', 'HEPATITIS A VACCINE INACTIVATED', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001376', 'DRS1376', 'HEPATITIS B VACCINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001377', 'DRS1377', 'HEPAVAX B', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001378', 'DRS1378', 'HERBOPYRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001379', 'DRS1379', 'HERPLEX LIQUIFILM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001380', 'DRS1380', 'heteroaryl  acetic acids', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001381', 'DRS1381', 'HETRAZAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001382', 'DRS1382', 'HEVAC B PASTEUR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001383', 'DRS1383', 'HEXACHLOROPHENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001384', 'DRS1384', 'HEXALEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001385', 'DRS1385', 'HEXASTAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001386', 'DRS1386', 'HEXIT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001387', 'DRS1387', 'HEXYLRESORCINOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001388', 'DRS1388', 'HI-COR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001389', 'DRS1389', 'HIPPURAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001390', 'DRS1390', 'HIPPUTOPE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001391', 'DRS1391', 'HIPREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001392', 'DRS1392', 'HIP-REX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001393', 'DRS1393', 'HIPS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001394', 'DRS1394', 'HISMANAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001395', 'DRS1395', 'histamine h1 antagonists', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001396', 'DRS1396', 'histamine h2 antagonists', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001397', 'DRS1397', 'HISTANTIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001398', 'DRS1398', 'HOMATROPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001399', 'DRS1399', 'HOMOCHORCYCLIZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001400', 'DRS1400', 'HUMAN HEPATITIS B IMUNOGLOBULIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001401', 'DRS1401', 'HUMATROPE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001402', 'DRS1402', 'HUMIBID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001403', 'DRS1403', 'HUMORSOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001404', 'DRS1404', 'HYCAMTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001405', 'DRS1405', 'hydantoins', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001406', 'DRS1406', 'HYDELTRA T.B.A', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001407', 'DRS1407', 'HYDELTRASOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001408', 'DRS1408', 'HYDERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001409', 'DRS1409', 'HYDRALAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001410', 'DRS1410', 'HYDRALAZINE AND HYDROCHLOROTHIAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001411', 'DRS1411', 'HYDRA-ZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001412', 'DRS1412', 'HYDREA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001413', 'DRS1413', 'HYDREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001414', 'DRS1414', 'HYDROCHLOROTHIAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001415', 'DRS1415', 'HYDROCODONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001416', 'DRS1416', 'HYDROCORTISONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001417', 'DRS1417', 'HYDRO-D', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001418', 'DRS1418', 'HYDROFLUMETHIAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001419', 'DRS1419', 'HYDROGEN PEROXIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001420', 'DRS1420', 'HYDROMORPHONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001421', 'DRS1421', 'HYDROSINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001422', 'DRS1422', 'HYDROXYCHLOROQUINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001423', 'DRS1423', 'HYDROXYPROGESTERONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001424', 'DRS1424', 'HYDROXYPROPYL CELLULOSE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001425', 'DRS1425', 'HYDROXYPROPYL METHYLCELLULOSE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001426', 'DRS1426', 'HYDROXYUREA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001427', 'DRS1427', 'hydroxyzine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001428', 'DRS1428', 'HYGROTON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001429', 'DRS1429', 'HYLOREL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001430', 'DRS1430', 'HYOSCYAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001431', 'DRS1431', 'HYPAQUE SODIUM 20%', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001432', 'DRS1432', 'HYPERAB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001433', 'DRS1433', 'HYPRHO-D', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001434', 'DRS1434', 'HYTAKEROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001435', 'DRS1435', 'HYTONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001436', 'DRS1436', 'HYTRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001437', 'DRS1437', 'HYTUSS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001438', 'DRS1438', 'IBUPROFEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001439', 'DRS1439', 'IBUTILIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001440', 'DRS1440', 'IDAMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001441', 'DRS1441', 'IDARAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001442', 'DRS1442', 'IDARUBICIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001443', 'DRS1443', 'IDOQUINOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001444', 'DRS1444', 'IDOXURIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001445', 'DRS1445', 'IFEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001446', 'DRS1446', 'IFOSFAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001447', 'DRS1447', 'ILOTYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001448', 'DRS1448', 'ILOZYME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001449', 'DRS1449', 'IMDUR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001450', 'DRS1450', 'IMIGLUCERASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001451', 'DRS1451', 'IMIPENAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001452', 'DRS1452', 'IMIPENEM AND CILASTATIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001453', 'DRS1453', 'IMIPRAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001454', 'DRS1454', 'IMITREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001455', 'DRS1455', 'IMMUNINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001456', 'DRS1456', 'IMMUNOGLOBULIN,HUMAN NORMAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001457', 'DRS1457', 'IMODIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001458', 'DRS1458', 'IMOGAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001459', 'DRS1459', 'IMOVAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001460', 'DRS1460', 'IMPRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001461', 'DRS1461', 'IMURAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001462', 'DRS1462', 'I-NAPHLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001463', 'DRS1463', 'INDAMETH', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001464', 'DRS1464', 'INDAPAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001465', 'DRS1465', 'INDERAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001466', 'DRS1466', 'INDINAVIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001467', 'DRS1467', 'INDOCID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001468', 'DRS1468', 'INDOCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001469', 'DRS1469', 'indoleacetic acids', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001470', 'DRS1470', 'indomethacin', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001471', 'DRS1471', 'INH', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001472', 'DRS1472', 'inhalation anesthetics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001473', 'DRS1473', 'INOCOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001474', 'DRS1474', 'INOSIPLEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001475', 'DRS1475', 'INSTANTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001476', 'DRS1476', 'INSULIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001477', 'DRS1477', 'INSULIN LISPRO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001478', 'DRS1478', 'INSULIN ZINC (COMPOUND)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001479', 'DRS1479', 'INTERFERON ALFA-N3', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001480', 'DRS1480', 'INTERFERON, GAMMA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001481', 'DRS1481', 'INTERFERONS, ALPHA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001482', 'DRS1482', 'intravenous anesthetics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001483', 'DRS1483', 'INTROPIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001484', 'DRS1484', 'INULIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001485', 'DRS1485', 'INVERSINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001486', 'DRS1486', 'INVIRASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001487', 'DRS1487', 'INVORIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001488', 'DRS1488', 'IODINATED GLYCEROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001489', 'DRS1489', 'IODINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001490', 'DRS1490', 'IODINE, STRONG', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001491', 'DRS1491', 'IODIPAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001492', 'DRS1492', 'IODIXANOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001493', 'DRS1493', 'IODOCHLORHYDROXYQUIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001494', 'DRS1494', 'IODOHIPPURATE SODIUM I 123', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001495', 'DRS1495', 'IODOHIPPURATE SODIUM I 131', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001496', 'DRS1496', 'IODOQUINOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001497', 'DRS1497', 'IOFETAMINE I 123', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001498', 'DRS1498', 'IOHEXOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001499', 'DRS1499', 'IONAMIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001500', 'DRS1500', 'IONIL T PLUS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001501', 'DRS1501', 'IOPAMIDOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001502', 'DRS1502', 'IOPIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001503', 'DRS1503', 'IOXAGLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001504', 'DRS1504', 'IPECACUANHA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001505', 'DRS1505', 'I-PENTOLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001506', 'DRS1506', 'I-PICAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001507', 'DRS1507', 'IPRATROPIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001508', 'DRS1508', 'IPRATROPIUM AND ALBUTEROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001509', 'DRS1509', 'IRINOTECAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001510', 'DRS1510', 'IRON DEXTRAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001511', 'DRS1511', 'iron salts', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001512', 'DRS1512', 'IRON SORBITOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001513', 'DRS1513', 'IRON-POLYSACCHARIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001514', 'DRS1514', 'ISMELIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001515', 'DRS1515', 'ISOCAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001516', 'DRS1516', 'ISOCARBOXAZID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001517', 'DRS1517', 'ISOETHARINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001518', 'DRS1518', 'ISOFLURANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001519', 'DRS1519', 'ISOFLUROPHATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001520', 'DRS1520', 'ISOMETHEPTENE, DICHLORALPHENAZONE,', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001521', 'DRS1521', 'ISONIAZID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001522', 'DRS1522', 'ISOPHANE INSULIN, HUMAN, AND INSULI', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001523', 'DRS1523', 'ISOPROTERENOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001524', 'DRS1524', 'ISOPTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001525', 'DRS1525', 'ISOPTO HYOSCINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001526', 'DRS1526', 'ISOSORBIDE DINITRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001527', 'DRS1527', 'ISOSORBIDE MONONITRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001528', 'DRS1528', 'ISOTAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001529', 'DRS1529', 'ISOTHIPENDY', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001530', 'DRS1530', 'ISOTRETINOIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001531', 'DRS1531', 'ISOVUE-', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001532', 'DRS1532', 'ISOXSUPRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001533', 'DRS1533', 'ISRADIPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001534', 'DRS1534', 'ISUPREL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001535', 'DRS1535', 'ITRACONAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001536', 'DRS1536', 'IVERMECTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001537', 'DRS1537', 'IVYBLOCK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001538', 'DRS1538', 'JAPANESE ENCEPHALITIS VIRUS VACCINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001539', 'DRS1539', 'JE-VAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001540', 'DRS1540', 'JUMEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001541', 'DRS1541', 'KABIKINASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001542', 'DRS1542', 'KALMEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001543', 'DRS1543', 'KALPIREN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001544', 'DRS1544', 'KANAMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001545', 'DRS1545', 'KANTREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001546', 'DRS1546', 'KAOLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001547', 'DRS1547', 'KAOLIN AND PECTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001548', 'DRS1548', 'KAOPECTATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001549', 'DRS1549', 'KAOPEK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001550', 'DRS1550', 'KAO-SPEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001551', 'DRS1551', 'KAPECTOLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001552', 'DRS1552', 'KARIDIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001553', 'DRS1553', 'KAYEXALATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001554', 'DRS1554', 'KENAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001555', 'DRS1555', 'KENALOG', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001556', 'DRS1556', 'KENONEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001557', 'DRS1557', 'KERLONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001558', 'DRS1558', 'KETALAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001559', 'DRS1559', 'KETAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001560', 'DRS1560', 'KETANSERIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001561', 'DRS1561', 'KETAZOLAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001562', 'DRS1562', 'KETOCONAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001563', 'DRS1563', 'KETOPROFEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001564', 'DRS1564', 'KETOROLAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001565', 'DRS1565', 'KETOTIFEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001566', 'DRS1566', 'K-EXIT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001567', 'DRS1567', 'KIDROLASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001568', 'DRS1568', 'KILDANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001569', 'DRS1569', 'KINEVAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001570', 'DRS1570', 'KINIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001571', 'DRS1571', 'KIONEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001572', 'DRS1572', 'KLAVIKORDAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001573', 'DRS1573', 'KLONOPIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001574', 'DRS1574', 'KOFFEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001575', 'DRS1575', 'KONYNE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001576', 'DRS1576', 'K-P', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001577', 'DRS1577', 'KPAB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001578', 'DRS1578', 'K-PEK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001579', 'DRS1579', 'KU-ZYME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001580', 'DRS1580', 'KWELL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001581', 'DRS1581', 'KWELLADA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001582', 'DRS1582', 'KWILDANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001583', 'DRS1583', 'KYTRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001584', 'DRS1584', 'LABETALOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001585', 'DRS1585', 'LACRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001586', 'DRS1586', 'LACRISERT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001587', 'DRS1587', 'LACTULOSE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001588', 'DRS1588', 'LAGOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001589', 'DRS1589', 'LAMICTAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001590', 'DRS1590', 'LAMISIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001591', 'DRS1591', 'LAMIVUDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001592', 'DRS1592', 'LAMOTRIGINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001593', 'DRS1593', 'LAMPIT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001594', 'DRS1594', 'LAMPRENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001595', 'DRS1595', 'LANACORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001596', 'DRS1596', 'LANIAZID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001597', 'DRS1597', 'LANOXICAPS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001598', 'DRS1598', 'LANOXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001599', 'DRS1599', 'LANSOPRAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001600', 'DRS1600', 'LANVIS.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001601', 'DRS1601', 'LARIAM.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001602', 'DRS1602', 'LARODOPA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001603', 'DRS1603', 'LASAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001604', 'DRS1604', 'LASIX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001605', 'DRS1605', 'L-ASPARAGINASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001606', 'DRS1606', 'LATANOPROST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001607', 'DRS1607', 'LAVATAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001608', 'DRS1608', 'L-CARNITINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001609', 'DRS1609', 'LECTOPAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001610', 'DRS1610', 'LEMODERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001611', 'DRS1611', 'LENOGASTIM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001612', 'DRS1612', 'LESCOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001613', 'DRS1613', 'LEUPROLELIN ACETATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001614', 'DRS1614', 'LEUPROLIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001615', 'DRS1615', 'LEUSTATIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001616', 'DRS1616', 'LEVALLORPHAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001617', 'DRS1617', 'LEVAMISOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001618', 'DRS1618', 'LEVATOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001619', 'DRS1619', 'LEVOCABASTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001620', 'DRS1620', 'LEVOCARNITINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001621', 'DRS1621', 'LEVODOPA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001622', 'DRS1622', 'LEVOFLOXACIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001623', 'DRS1623', 'LEVOMETHADYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001624', 'DRS1624', 'LEVONORGESTREL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001625', 'DRS1625', 'LEVORPHANOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001626', 'DRS1626', 'levothyroxine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001627', 'DRS1627', 'LEVOTHYROXINE SODIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001628', 'DRS1628', 'LEXINOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001629', 'DRS1629', 'LH-RH', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001630', 'DRS1630', 'LIBRAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001631', 'DRS1631', 'LIBRITABS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001632', 'DRS1632', 'LIBRIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001633', 'DRS1633', 'LIDOCAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001634', 'DRS1634', 'LIDOCAINE AND PRILOCAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001635', 'DRS1635', 'LIDOX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001636', 'DRS1636', 'LIDOXIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001637', 'DRS1637', 'LIGNOCAIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001638', 'DRS1638', 'LIGNOCAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001639', 'DRS1639', 'LIMBITROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001640', 'DRS1640', 'LINCOCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001641', 'DRS1641', 'LINCOMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001642', 'DRS1642', 'LINCOREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001643', 'DRS1643', 'lincosamides', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001644', 'DRS1644', 'LINDANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001645', 'DRS1645', 'LIOTHYRONINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001646', 'DRS1646', 'LIOTRIX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001647', 'DRS1647', 'LIPONUCLEIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001648', 'DRS1648', 'LIQUAEMIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001649', 'DRS1649', 'LIQUID BAROSPERSE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001650', 'DRS1650', 'LIQUID PARAFFIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001651', 'DRS1651', 'LIQUI-JUG', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001652', 'DRS1652', 'LIQUIPAKE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001653', 'DRS1653', 'LIQUOR CARBONIS DETERGENS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001654', 'DRS1654', 'LISINOPRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001655', 'DRS1655', 'lithium', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001656', 'DRS1656', 'LITHIUM CARBONATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001657', 'DRS1657', 'lithium salts', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001658', 'DRS1658', 'LITHOSTAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001659', 'DRS1659', 'LIVOSTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001660', 'DRS1660', 'local anesthetics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001661', 'DRS1661', 'LODINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001662', 'DRS1662', 'LODOXAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001663', 'DRS1663', 'LOFENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001664', 'DRS1664', 'LOFTRAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001665', 'DRS1665', 'LOMEFLOXACIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001666', 'DRS1666', 'LOMOCOT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001667', 'DRS1667', 'LOMOTIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001668', 'DRS1668', 'LOMUSTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001669', 'DRS1669', 'LONITEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001670', 'DRS1670', 'LONOX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001671', 'DRS1671', 'loop diuretics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001672', 'DRS1672', 'LOPERAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001673', 'DRS1673', 'LOPID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001674', 'DRS1674', 'LOPRESS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001675', 'DRS1675', 'LOPROX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001676', 'DRS1676', 'LOPURIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001677', 'DRS1677', 'LORABID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001678', 'DRS1678', 'LORACARBEF', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001679', 'DRS1679', 'loratadine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001680', 'DRS1680', 'LORAZEPAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001681', 'DRS1681', 'LOROXIDE 5 LOTION WITH FLESH-TINTED', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001682', 'DRS1682', 'LOSARTAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001683', 'DRS1683', 'LOSEC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001684', 'DRS1684', 'LOTENSIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001685', 'DRS1685', 'LOTIO ALSULFA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001686', 'DRS1686', 'LOVASTATIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001687', 'DRS1687', 'LOVENOX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001688', 'DRS1688', 'LOXAPAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001689', 'DRS1689', 'LOXAPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001690', 'DRS1690', 'LOXITANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001691', 'DRS1691', 'LOZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001692', 'DRS1692', 'LOZOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001693', 'DRS1693', 'L-PAMAND PHENYLALANINE MUSTARD.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001694', 'DRS1694', 'LUDIOMIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001695', 'DRS1695', 'LUFYLLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001696', 'DRS1696', 'LUGOL''S SOLUTION', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001697', 'DRS1697', 'LUMINAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001698', 'DRS1698', 'LUMITENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001699', 'DRS1699', 'LUPRON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001700', 'DRS1700', 'LURIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001701', 'DRS1701', 'LUVOX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001702', 'DRS1702', 'LYPHOCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001703', 'DRS1703', 'LYPRESSIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001704', 'DRS1704', 'LYSODREN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001705', 'DRS1705', 'MACROBID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001706', 'DRS1706', 'MACRODANTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001707', 'DRS1707', 'macrolide antibiotics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001708', 'DRS1708', 'MAFENIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001709', 'DRS1709', 'MAGNESIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001710', 'DRS1710', 'magnesium hydroxide', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001711', 'DRS1711', 'MAGNESIUM OXIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001712', 'DRS1712', 'magnesium salts', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001713', 'DRS1713', 'MAGNESIUM SULFATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001714', 'DRS1714', 'MAGNEVIST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001715', 'DRS1715', 'MALATHION', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001716', 'DRS1716', 'MALOGEN IN OIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001717', 'DRS1717', 'MALT SOUP EXTRACT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001718', 'DRS1718', 'MANDELAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001719', 'DRS1719', 'MANGANESE CHLORIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001720', 'DRS1720', 'MANGANESE SULFATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001721', 'DRS1721', 'MANNITOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001722', 'DRS1722', 'MAOLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001723', 'DRS1723', 'MAPROTILINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001724', 'DRS1724', 'MARCAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001725', 'DRS1725', 'MAREZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001726', 'DRS1726', 'MARINOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001727', 'DRS1727', 'MARPLAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001728', 'DRS1728', 'MARZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001729', 'DRS1729', 'MASOPROCOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001730', 'DRS1730', 'MAVIK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001731', 'DRS1731', 'MAXAIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001732', 'DRS1732', 'MAX-CARO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001733', 'DRS1733', 'MAXERAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001734', 'DRS1734', 'MAXIBAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001735', 'DRS1735', 'MAXIPIME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001736', 'DRS1736', 'MAXZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001737', 'DRS1737', 'MAZANOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001738', 'DRS1738', 'MAZINDOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001739', 'DRS1739', 'M-CAPS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001740', 'DRS1740', 'MEASLES AND RUBELLA VIRUS VACCINE L', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001741', 'DRS1741', 'MEBARAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001742', 'DRS1742', 'MEBENDAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001743', 'DRS1743', 'MEBEVORINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001744', 'DRS1744', 'MECAMYLAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001745', 'DRS1745', 'MECHLORETHAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001746', 'DRS1746', 'MECLIZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001747', 'DRS1747', 'MECLOFENAMATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001748', 'DRS1748', 'MECLOMEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001749', 'DRS1749', 'MECLOZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001750', 'DRS1750', 'MECOBALAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001751', 'DRS1751', 'MECTIZAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001752', 'DRS1752', 'MED VALPROIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001753', 'DRS1753', 'MEDEBAG', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001754', 'DRS1754', 'MEDEBAR PLUS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001755', 'DRS1755', 'MEDE-SCAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001756', 'DRS1756', 'MEDICAMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001757', 'DRS1757', 'MEDIQUELL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001758', 'DRS1758', 'MEDOTAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001759', 'DRS1759', 'MEDROGESTONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001760', 'DRS1760', 'MEDROXYPROGESTERONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001761', 'DRS1761', 'MEFENAMIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001762', 'DRS1762', 'MEFLOQUINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001763', 'DRS1763', 'MEGESTROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001764', 'DRS1764', 'MEGLUMINE ANTIMONIATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001765', 'DRS1765', 'MEGUITAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001766', 'DRS1766', 'MELFIAT-105 UNICELLES', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001767', 'DRS1767', 'MELPHALAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001768', 'DRS1768', 'MENADIONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001769', 'DRS1769', 'MENI-D', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001770', 'DRS1770', 'MENINGOCOCCAL VACCINES', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001771', 'DRS1771', 'MENOTROPINS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001772', 'DRS1772', 'MENPOVAX 4', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001773', 'DRS1773', 'MENPOVAX A', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001774', 'DRS1774', 'MEPENZOLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001775', 'DRS1775', 'MEPERIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001776', 'DRS1776', 'MEPHENTERMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001777', 'DRS1777', 'MEPHENYTOIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001778', 'DRS1778', 'MEPHOBARBITAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001779', 'DRS1779', 'MEPIRIZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001780', 'DRS1780', 'MEPIVACAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001781', 'DRS1781', 'MEPIVACIANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001782', 'DRS1782', 'MEPREDNISOLONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001783', 'DRS1783', 'MEPROBAMATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001784', 'DRS1784', 'MEPRON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001785', 'DRS1785', 'MEPROSPAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001786', 'DRS1786', 'MERCAPTOPURINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001787', 'DRS1787', 'MERISLON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001788', 'DRS1788', 'MEROPENEM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001789', 'DRS1789', 'MERREM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001790', 'DRS1790', 'MESALAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001791', 'DRS1791', 'MESANTOIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001792', 'DRS1792', 'MESASAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001793', 'DRS1793', 'MESNA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001794', 'DRS1794', 'MESNEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001795', 'DRS1795', 'MESORIDAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001796', 'DRS1796', 'METANDREN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001797', 'DRS1797', 'METAPROTERENOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001798', 'DRS1798', 'METARAMINOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001799', 'DRS1799', 'METASTRON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001800', 'DRS1800', 'METAXALONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001801', 'DRS1801', 'METFORMIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001802', 'DRS1802', 'METHACHOLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001803', 'DRS1803', 'METHADONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001804', 'DRS1804', 'METHANTHELINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001805', 'DRS1805', 'METHARBITAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001806', 'DRS1806', 'METHAZOLAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001807', 'DRS1807', 'METHDILAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001808', 'DRS1808', 'METHENAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001809', 'DRS1809', 'METHICILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001810', 'DRS1810', 'METHIMAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001811', 'DRS1811', 'METHOCARBAMOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001812', 'DRS1812', 'METHOCEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001813', 'DRS1813', 'METHOTREXATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001814', 'DRS1814', 'METHOTRIMEPRAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001815', 'DRS1815', 'METHOXAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001816', 'DRS1816', 'METHOXSALEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001817', 'DRS1817', 'METHOXYFLURANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001818', 'DRS1818', 'METHSCOPOLAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001819', 'DRS1819', 'METHYCLOTHIAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001820', 'DRS1820', 'METHYLCELLULOSE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001821', 'DRS1821', 'METHYLCOBAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001822', 'DRS1822', 'METHYLDOPA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001823', 'DRS1823', 'METHYLENE BLUE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001824', 'DRS1824', 'methylergometrine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001825', 'DRS1825', 'METHYLERGONOVINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001826', 'DRS1826', 'METHYLPHENIDATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001827', 'DRS1827', 'METHYLPREDNISOLONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001828', 'DRS1828', 'METHYLSALICYLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001829', 'DRS1829', 'METHYLTESTOSTERONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001830', 'DRS1830', 'METHYPRYLON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001831', 'DRS1831', 'METHYSERGIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001832', 'DRS1832', 'METICORTEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001833', 'DRS1833', 'METOCLOPRAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001834', 'DRS1834', 'METOCURINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001835', 'DRS1835', 'METOLAZONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001836', 'DRS1836', 'metoprolol', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001837', 'DRS1837', 'METOPROLOL TARTRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001838', 'DRS1838', 'METRIZAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001839', 'DRS1839', 'METRONIDAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001840', 'DRS1840', 'METUBINE IODIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001841', 'DRS1841', 'METYROSINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001842', 'DRS1842', 'MEVACOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001843', 'DRS1843', 'MEXATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001844', 'DRS1844', 'mexiletine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001845', 'DRS1845', 'MEXITIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001846', 'DRS1846', 'MEZLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001847', 'DRS1847', 'MEZLOCILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001848', 'DRS1848', 'MIACALCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001849', 'DRS1849', 'mianserin', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001850', 'DRS1850', 'MIANSERINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001851', 'DRS1851', 'MICATIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001852', 'DRS1852', 'MICONAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001853', 'DRS1853', 'MICRHOGAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001854', 'DRS1854', 'MIDAZOLAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001855', 'DRS1855', 'MIDODRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001856', 'DRS1856', 'MIGLITOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001857', 'DRS1857', 'MIGREND', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001858', 'DRS1858', 'MILOPHENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001859', 'DRS1859', 'MILRINONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001860', 'DRS1860', 'MILTOWN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001861', 'DRS1861', 'MINI-GAMULIN RH', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001862', 'DRS1862', 'MINIMS CYCLOPENTOLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001863', 'DRS1863', 'MINIMS TETRACAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001864', 'DRS1864', 'MINIRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001865', 'DRS1865', 'MINITRAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001866', 'DRS1866', 'MINOCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001867', 'DRS1867', 'MINOCYCLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001868', 'DRS1868', 'MINOXIDIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001869', 'DRS1869', 'MINTEZOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001870', 'DRS1870', 'MIO-REL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001871', 'DRS1871', 'MIOSTAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001872', 'DRS1872', 'MIRADON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001873', 'DRS1873', 'MIRTAZAPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001874', 'DRS1874', 'MISOPROTOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001875', 'DRS1875', 'MITOMYCIN-C', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001876', 'DRS1876', 'MITOTANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001877', 'DRS1877', 'MITOXANTRONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001878', 'DRS1878', 'MIVACRON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001879', 'DRS1879', 'MIVACURIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001880', 'DRS1880', 'MOBENOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001881', 'DRS1881', 'MOBIFLEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001882', 'DRS1882', 'MOCLOBEMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001883', 'DRS1883', 'MOCTANIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001884', 'DRS1884', 'MODRASTANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001885', 'DRS1885', 'MOEXIPRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001886', 'DRS1886', 'MOGADON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001887', 'DRS1887', 'MOLINDONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001888', 'DRS1888', 'MONISTAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001889', 'DRS1889', 'MONISTAT-DERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001890', 'DRS1890', 'MONITAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001891', 'DRS1891', 'MONOCTANOIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001892', 'DRS1892', 'MONOKET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001893', 'DRS1893', 'MONONINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001894', 'DRS1894', 'MONOPRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001895', 'DRS1895', 'MORACIZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001896', 'DRS1896', 'MORANYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001897', 'DRS1897', 'MORICIZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001898', 'DRS1898', 'MORPHINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001899', 'DRS1899', 'MOTOFEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001900', 'DRS1900', 'MOXALACTAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001901', 'DRS1901', 'M-R-VAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001902', 'DRS1902', 'MTX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001903', 'DRS1903', 'MUCOFLUX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001904', 'DRS1904', 'MUCOMYST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001905', 'DRS1905', 'MUCOSIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001906', 'DRS1906', 'MUCOSOLVAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001907', 'DRS1907', 'MUDRANE GG-2', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001908', 'DRS1908', 'MUPIROCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001909', 'DRS1909', 'MUROMONAB-CD3', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001910', 'DRS1910', 'muscle relaxants', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001911', 'DRS1911', 'MUSE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001912', 'DRS1912', 'MUSKOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001913', 'DRS1913', 'MUSTARGEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001914', 'DRS1914', 'MYAMBUTOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001915', 'DRS1915', 'MYCELEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001916', 'DRS1916', 'MYCOBUTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001917', 'DRS1917', 'MYCOPHENOLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001918', 'DRS1918', 'MYCOSTATIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001919', 'DRS1919', 'MYDOCALM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001920', 'DRS1920', 'MYDRIACYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001921', 'DRS1921', 'MYDRIAFAIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001922', 'DRS1922', 'MYIDIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001923', 'DRS1923', 'MYKROX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001924', 'DRS1924', 'MYLANTA GAS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001925', 'DRS1925', 'MYLERAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001926', 'DRS1926', 'MYLICON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001927', 'DRS1927', 'MYOLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001928', 'DRS1928', 'MYOTROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001929', 'DRS1929', 'MYTELASE CAPLETS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001930', 'DRS1930', 'MZM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001931', 'DRS1931', 'N3 GESIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001932', 'DRS1932', 'NABILONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001933', 'DRS1933', 'nabumetone', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001934', 'DRS1934', 'NADOLOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001935', 'DRS1935', 'NADOPEN-V', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001936', 'DRS1936', 'NADOSTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001937', 'DRS1937', 'NAFARELIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001938', 'DRS1938', 'NAFAZAIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001939', 'DRS1939', 'NAFCIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001940', 'DRS1940', 'NAFCILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001941', 'DRS1941', 'NAFTIFINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001942', 'DRS1942', 'NAFTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001943', 'DRS1943', 'NAGANIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001944', 'DRS1944', 'NAGANOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001945', 'DRS1945', 'NALBUPHINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001946', 'DRS1946', 'NALDECON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001947', 'DRS1947', 'NALFON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001948', 'DRS1948', 'NALIDIXIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001949', 'DRS1949', 'NALLPEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001950', 'DRS1950', 'NALMEFENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001951', 'DRS1951', 'NALORPHINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001952', 'DRS1952', 'NALOXONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001953', 'DRS1953', 'NALTREXONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001954', 'DRS1954', 'NANDROLONE PHENYLPROPIONATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001955', 'DRS1955', 'NAPHAZOLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001956', 'DRS1956', 'NAPHCON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001957', 'DRS1957', 'NAPHURIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001958', 'DRS1958', 'NAPROXEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001959', 'DRS1959', 'NARCAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001960', 'DRS1960', 'NARCARICIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001961', 'DRS1961', 'narcotic analgesics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001962', 'DRS1962', 'narcotics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001963', 'DRS1963', 'NARDIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001964', 'DRS1964', 'NARIFEC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001965', 'DRS1965', 'NAROPIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001966', 'DRS1966', 'NASACORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001967', 'DRS1967', 'NASALIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001968', 'DRS1968', 'NATACYN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001969', 'DRS1969', 'NATAMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001970', 'DRS1970', 'NATURETIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001971', 'DRS1971', 'NAVANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001972', 'DRS1972', 'ND-STAT REVISED', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001973', 'DRS1973', 'NEDOCROMIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001974', 'DRS1974', 'NEFAZODONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001975', 'DRS1975', 'NEGGRAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001976', 'DRS1976', 'NEMBUTAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001977', 'DRS1977', 'NEO-CODEMA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001978', 'DRS1978', 'NEO-DM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001979', 'DRS1979', 'neomycin', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001980', 'DRS1980', 'NEOSAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001981', 'DRS1981', 'NEOSTIGMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001982', 'DRS1982', 'NEOTIGASON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001983', 'DRS1983', 'NEPHROPURE.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001984', 'DRS1984', 'NEPTAZANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001985', 'DRS1985', 'NERVINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001986', 'DRS1986', 'NETILMICIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001987', 'DRS1987', 'NEURONTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001988', 'DRS1988', 'NEUTREXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001989', 'DRS1989', 'NEUTROGENA ACNE MASK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001990', 'DRS1990', 'NEVER', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001991', 'DRS1991', 'NEVIRAPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001992', 'DRS1992', 'NIACIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001993', 'DRS1993', 'NICARDIPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001994', 'DRS1994', 'NICLOCIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001995', 'DRS1995', 'NICLOSAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001996', 'DRS1996', 'NICODERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001997', 'DRS1997', 'NICOLAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001998', 'DRS1998', 'NICORETTE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000001999', 'DRS1999', 'NICOTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002000', 'DRS2000', 'NICOTINIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002001', 'DRS2001', 'NICOTROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002002', 'DRS2002', 'NIFECARD', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002003', 'DRS2003', 'NIFEDIPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002004', 'DRS2004', 'NIFELAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002005', 'DRS2005', 'NIFURTIMOX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002006', 'DRS2006', 'NIGHT CAST R', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002007', 'DRS2007', 'NILSTAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002008', 'DRS2008', 'NIMBEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002009', 'DRS2009', 'NIMODIPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002010', 'DRS2010', 'NIMOTOP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002011', 'DRS2011', 'NIONG', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002012', 'DRS2012', 'NIPRIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002013', 'DRS2013', 'NISAVAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002014', 'DRS2014', 'nitrates', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002015', 'DRS2015', 'NITRAZEPAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002016', 'DRS2016', 'NITRO-BID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002017', 'DRS2017', 'NITROCAP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002018', 'DRS2018', 'NITRODISC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002019', 'DRS2019', 'NITRO-DUR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002020', 'DRS2020', 'NITROFURACOT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002021', 'DRS2021', 'NITROFURANTOIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002022', 'DRS2022', 'NITROFURAZONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002023', 'DRS2023', 'NITROGARD', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002024', 'DRS2024', 'nitrogen mustards', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002025', 'DRS2025', 'NITROGLYCERIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002026', 'DRS2026', 'NITROGLYN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002027', 'DRS2027', 'NITROJECT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002028', 'DRS2028', 'NITROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002029', 'DRS2029', 'NITROLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002030', 'DRS2030', 'NITROLINGUAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002031', 'DRS2031', 'NITROPRESS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002032', 'DRS2032', 'NITROPRUSSIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002033', 'DRS2033', 'NITROSTAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002034', 'DRS2034', 'NIZATIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002035', 'DRS2035', 'NIZORAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002036', 'DRS2036', 'NOLVADEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002037', 'DRS2037', 'nondepolarizing muscle relaxants', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002038', 'DRS2038', 'non-narcotic analgesics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002039', 'DRS2039', 'NOREPINEPHRINE BITARTRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002040', 'DRS2040', 'NORETHINDRONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002041', 'DRS2041', 'NORETHISTERONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002042', 'DRS2042', 'NORFLOXACIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002043', 'DRS2043', 'NORFRANI', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002044', 'DRS2044', 'NORGESIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002045', 'DRS2045', 'NORGESTREL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002046', 'DRS2046', 'NORMODYNE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002047', 'DRS2047', 'NORPACE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002048', 'DRS2048', 'NORPHADRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002049', 'DRS2049', 'NORPRAMIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002050', 'DRS2050', 'NORTRIPTYLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002051', 'DRS2051', 'NORVASC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002052', 'DRS2052', 'NORVIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002053', 'DRS2053', 'NORWOOD SUNBURN SPRAY', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002054', 'DRS2054', 'NORZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002055', 'DRS2055', 'NOSCAPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002056', 'DRS2056', 'NOSPA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002057', 'DRS2057', 'NOSTRILLA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002058', 'DRS2058', 'NOVA RECTAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002059', 'DRS2059', 'NOVANTRONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002060', 'DRS2060', 'NOVASEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002061', 'DRS2061', 'NOVO-CARBAMAZ', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002062', 'DRS2062', 'NOVOCHLOROCAP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002063', 'DRS2063', 'NOVOFIBRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002064', 'DRS2064', 'NOVO-HYDRAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002065', 'DRS2065', 'NOVOMETHACIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002066', 'DRS2066', 'NOVOPENTOBARB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002067', 'DRS2067', 'NOVO-PERIDOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002068', 'DRS2068', 'NOVOPRAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002069', 'DRS2069', 'NOVOPYRAZONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002070', 'DRS2070', 'NOVOSPIROTON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002071', 'DRS2071', 'NOVO-SPIROZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002072', 'DRS2072', 'NOVOTETRA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002073', 'DRS2073', 'NOVO-THALIDONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002074', 'DRS2074', 'NOXZEMA CLEAR-UPS ON-THE-SPOT 10 LO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002075', 'DRS2075', 'NPH INSULIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002076', 'DRS2076', 'nsaids', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002077', 'DRS2077', 'NTG', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002078', 'DRS2078', 'NTZ', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002079', 'DRS2079', 'NU-DICLO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002080', 'DRS2080', 'NU-DILTIAZ', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002081', 'DRS2081', 'NUPERCAINAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002082', 'DRS2082', 'NUROMAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002083', 'DRS2083', 'NUTROPIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002084', 'DRS2084', 'NYADERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002085', 'DRS2085', 'NYDRAZID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002086', 'DRS2086', 'NYLIDRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002087', 'DRS2087', 'NYSTATIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002088', 'DRS2088', 'O,P''-DDD', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002089', 'DRS2089', 'OBALAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002090', 'DRS2090', 'OBE-NIX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002091', 'DRS2091', 'OBEZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002092', 'DRS2092', 'OBY-CAP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002093', 'DRS2093', 'OCTAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002094', 'DRS2094', 'OCTOSTIM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002095', 'DRS2095', 'OCTREOTIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002096', 'DRS2096', 'OCUCOAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002097', 'DRS2097', 'OCUFLOX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002098', 'DRS2098', 'OCU-PENTOLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002099', 'DRS2099', 'OCU-TROPIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002100', 'DRS2100', 'OFF!', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002101', 'DRS2101', 'OFLOXACIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002102', 'DRS2102', 'OLANZAPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002103', 'DRS2103', 'OLBETAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002104', 'DRS2104', 'OLSALAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002105', 'DRS2105', 'OMEPRAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002106', 'DRS2106', 'OMNIPAQUE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002107', 'DRS2107', 'OMNIPEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002108', 'DRS2108', 'OMNISCAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002109', 'DRS2109', 'ONCASPAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002110', 'DRS2110', 'ONCOVIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002111', 'DRS2111', 'ONDANSETRON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002112', 'DRS2112', 'OPHTHETIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002113', 'DRS2113', 'OPIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002114', 'DRS2114', 'OPTICAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002115', 'DRS2115', 'OPTICYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002116', 'DRS2116', 'ORACORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002117', 'DRS2117', 'oral hypoglycemic agents', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002118', 'DRS2118', 'ORALONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002119', 'DRS2119', 'ORASONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002120', 'DRS2120', 'ORBENIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002121', 'DRS2121', 'ORETIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002122', 'DRS2122', 'ORETON METHYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002123', 'DRS2123', 'ORFRO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002124', 'DRS2124', 'ORGANIDIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002125', 'DRS2125', 'ORINASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002126', 'DRS2126', 'ORLAAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002127', 'DRS2127', 'ORNEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002128', 'DRS2128', 'ORNIDAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002129', 'DRS2129', 'ORNIDYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002130', 'DRS2130', 'ORPHENADRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002131', 'DRS2131', 'ORPHENADRINE AND ASPIRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002132', 'DRS2132', 'ORPHENAGESIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002133', 'DRS2133', 'ORPHENATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002134', 'DRS2134', 'ORS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002135', 'DRS2135', 'ORTHOCLONE OKT3', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002136', 'DRS2136', 'ORUDIS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002137', 'DRS2137', 'ORUVAIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002138', 'DRS2138', 'OSMITROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002139', 'DRS2139', 'OSMOGLYN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002140', 'DRS2140', 'osmotic diuretics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002141', 'DRS2141', 'osmotic laxatives', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002142', 'DRS2142', 'OSTOFORTE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002143', 'DRS2143', 'OVIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002144', 'DRS2144', 'OVOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002145', 'DRS2145', 'OXACILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002146', 'DRS2146', 'OXAMNIQUINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002147', 'DRS2147', 'OXANDRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002148', 'DRS2148', 'OXANDROLONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002149', 'DRS2149', 'OXAPROZIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002150', 'DRS2150', 'OXAZEPAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002151', 'DRS2151', 'oxicams', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002152', 'DRS2152', 'OXICONAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002153', 'DRS2153', 'OXISTAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002154', 'DRS2154', 'OXPRENOLOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002155', 'DRS2155', 'OXSORALEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002156', 'DRS2156', 'OXTRIPHYLLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002157', 'DRS2157', 'OXY', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002158', 'DRS2158', 'OXYBUTYNIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002159', 'DRS2159', 'OXYCODONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002160', 'DRS2160', 'OXYDERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002161', 'DRS2161', 'OXYMETAZOLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002162', 'DRS2162', 'OXYMORPHONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002163', 'DRS2163', 'oxytetracycline', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002164', 'DRS2164', 'oxytocic drugs', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002165', 'DRS2165', 'OXYTOCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002166', 'DRS2166', 'PACLITAXEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002167', 'DRS2167', 'PAIN AID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002168', 'DRS2168', 'PAMELOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002169', 'DRS2169', 'PAMIDRONATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002170', 'DRS2170', 'PANCOATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002171', 'DRS2171', 'PANCREASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002172', 'DRS2172', 'PANCREATIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002173', 'DRS2173', 'PANCRELIPASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002174', 'DRS2174', 'PANCREOZYMIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002175', 'DRS2175', 'PANCURONIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002176', 'DRS2176', 'PANECTYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002177', 'DRS2177', 'PANMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002178', 'DRS2178', 'PANOKASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002179', 'DRS2179', 'PANOXYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002180', 'DRS2180', 'PANSHAPE M', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002181', 'DRS2181', 'PANTOTHENIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002182', 'DRS2182', 'PANWARFIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002183', 'DRS2183', 'PAPAVERINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002184', 'DRS2184', 'PARA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002185', 'DRS2185', 'para-aminophenol derivatives', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002186', 'DRS2186', 'PARACETAMOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002187', 'DRS2187', 'paracetamol+chlorzoxazone', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002188', 'DRS2188', 'paracetamol+orphenadrine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002189', 'DRS2189', 'PARAFLEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002190', 'DRS2190', 'PARAFON FORTE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002191', 'DRS2191', 'PARAFON FORTE DSC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002192', 'DRS2192', 'PARAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002193', 'DRS2193', 'PARALDEHYDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002194', 'DRS2194', 'PARAMETHASONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002195', 'DRS2195', 'PARAPLATIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002196', 'DRS2196', 'PARATHAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002197', 'DRS2197', 'PAREGORIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002198', 'DRS2198', 'PAREPECTOLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002199', 'DRS2199', 'PARLODEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002200', 'DRS2200', 'PARNATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002201', 'DRS2201', 'PAROXETINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002202', 'DRS2202', 'PARSIDOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002203', 'DRS2203', 'PARSITAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002204', 'DRS2204', 'PARVOLEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002205', 'DRS2205', 'PARZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002206', 'DRS2206', 'PAS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002207', 'DRS2207', 'PATHOCIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002208', 'DRS2208', 'PAUPUROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002209', 'DRS2209', 'PAVABID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002210', 'DRS2210', 'PAVACELS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002211', 'DRS2211', 'PAVACOT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002212', 'DRS2212', 'PAVAGEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002213', 'DRS2213', 'PAVARINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002214', 'DRS2214', 'PAVASED', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002215', 'DRS2215', 'PAVATINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002216', 'DRS2216', 'PAVATYM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002217', 'DRS2217', 'PAVEROLAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002218', 'DRS2218', 'PAVULON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002219', 'DRS2219', 'PAXIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002220', 'DRS2220', 'PAXIPAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002221', 'DRS2221', 'PCC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002222', 'DRS2222', 'PCEC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002223', 'DRS2223', 'PDF', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002224', 'DRS2224', 'PEDAMETH', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002225', 'DRS2225', 'PEDIAFLOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002226', 'DRS2226', 'PEDIAPRED', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002227', 'DRS2227', 'PEDI-DENT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002228', 'DRS2228', 'PEDVAX HIB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002229', 'DRS2229', 'PEGADEMASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002230', 'DRS2230', 'PEGANONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002231', 'DRS2231', 'PEGASPARGASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002232', 'DRS2232', 'PEMOLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002233', 'DRS2233', 'PEN VEE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002234', 'DRS2234', 'PENBRITIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002235', 'DRS2235', 'PENBUTOLOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002236', 'DRS2236', 'PENECORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002237', 'DRS2237', 'PENGLOBE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002238', 'DRS2238', 'PENICILLAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002239', 'DRS2239', 'PENICILLIN G', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002240', 'DRS2240', 'PENICILLIN V', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002241', 'DRS2241', 'penicillins', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002242', 'DRS2242', 'PENTACT-HIB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002243', 'DRS2243', 'PENTAERYTHRITOL TETRANITRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002244', 'DRS2244', 'PENTAMIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002245', 'DRS2245', 'PENTASA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002246', 'DRS2246', 'PENTA-VALPROIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002247', 'DRS2247', 'PENTAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002248', 'DRS2248', 'PENTAZOCINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002249', 'DRS2249', 'PENTOBARBITAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002250', 'DRS2250', 'PENTOLAIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002251', 'DRS2251', 'PENTOSAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002252', 'DRS2252', 'PENTOSTATIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002253', 'DRS2253', 'PENTOXIFYLLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002254', 'DRS2254', 'PENTRAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002255', 'DRS2255', 'PENTYLAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002256', 'DRS2256', 'PEPCID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002257', 'DRS2257', 'PEPTO-BISMOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002258', 'DRS2258', 'PEPTOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002259', 'DRS2259', 'PERFLOXACIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002260', 'DRS2260', 'PERFLUOROCHEMICAL EMULSION', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002261', 'DRS2261', 'PERGOLIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002262', 'DRS2262', 'PERIACTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002263', 'DRS2263', 'PERICYAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002264', 'DRS2264', 'PERIDOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002265', 'DRS2265', 'PERINDOPRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002266', 'DRS2266', 'PERITRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002267', 'DRS2267', 'PERMETHRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002268', 'DRS2268', 'PERNOX LEMON MEDICATED SCRUB CLEANS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002269', 'DRS2269', 'PERPHENAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002270', 'DRS2270', 'PERSA-GEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002271', 'DRS2271', 'PERSANTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002272', 'DRS2272', 'PERTOFRANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002273', 'DRS2273', 'PETHIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002274', 'DRS2274', 'PFA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002275', 'DRS2275', 'PHARMAFLUR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002276', 'DRS2276', 'PHARMATEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002277', 'DRS2277', 'PHAZYME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002278', 'DRS2278', 'PHENACEMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002279', 'DRS2279', 'PHENAZOPYRIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002280', 'DRS2280', 'PHENCEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002281', 'DRS2281', 'PHENDIET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002282', 'DRS2282', 'PHENDIMET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002283', 'DRS2283', 'PHENDIMETRAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002284', 'DRS2284', 'PHENELZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002285', 'DRS2285', 'PHENERGAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002286', 'DRS2286', 'PHENERZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002287', 'DRS2287', 'PHENETRON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002288', 'DRS2288', 'phenobarbital', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002289', 'DRS2289', 'PHENOJECT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002290', 'DRS2290', 'PHENOLPHTHALEIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002291', 'DRS2291', 'phenothiazines', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002292', 'DRS2292', 'PHENOXYBENZAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002293', 'DRS2293', 'PHENOXYBENZAMINE (SYSTEMIC)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002294', 'DRS2294', 'PHENTERCOT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002295', 'DRS2295', 'PHENTERMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002296', 'DRS2296', 'PHENTOLAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002297', 'DRS2297', 'PHENTRIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002298', 'DRS2298', 'PHENYLBUTAZONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002299', 'DRS2299', 'PHENYLEPHRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002300', 'DRS2300', 'PHENYLPROPANOLAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002301', 'DRS2301', 'PHENYTEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002302', 'DRS2302', 'PHENYTOIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002303', 'DRS2303', 'PHISOAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002304', 'DRS2304', 'PHOS-FLUR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002305', 'DRS2305', 'PHOSPHATES', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002306', 'DRS2306', 'PHOSPHOCOL P 32', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002307', 'DRS2307', 'PHOSPHOLINE IODIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002308', 'DRS2308', 'PHOSPHONOFORMIC ACID(PPA)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002309', 'DRS2309', 'PHYSOSTIGMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002310', 'DRS2310', 'PICRIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002311', 'DRS2311', 'PILOCARPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002312', 'DRS2312', 'PIMOZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002313', 'DRS2313', 'PINAVERIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002314', 'DRS2314', 'PINDOLOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002315', 'DRS2315', 'PINDOLOL+CLOPAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002316', 'DRS2316', 'PIPECURONIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002317', 'DRS2317', 'PIPERACILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002318', 'DRS2318', 'PIPERACILLIN AND TAZOBACTAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002319', 'DRS2319', 'PIPERAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002320', 'DRS2320', 'PIPETHANATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002321', 'DRS2321', 'PIPOTIAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002322', 'DRS2322', 'PIRBUTEROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002323', 'DRS2323', 'PIRENZEPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002324', 'DRS2324', 'PIROXICAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002325', 'DRS2325', 'PITOCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002326', 'DRS2326', 'PITRESSIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002327', 'DRS2327', 'PITREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002328', 'DRS2328', 'PIVAMPICILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002329', 'DRS2329', 'PIVMECILLINAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002330', 'DRS2330', 'PIZOTIFEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002331', 'DRS2331', 'PLACIDYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002332', 'DRS2332', 'PLAQUENIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002333', 'DRS2333', 'PLASBUMIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002334', 'DRS2334', 'PLASIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002335', 'DRS2335', 'PLATINOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002336', 'DRS2336', 'PLAUNOTOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002337', 'DRS2337', 'PLEGINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002338', 'DRS2338', 'PLENDIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002339', 'DRS2339', 'PLICAMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002340', 'DRS2340', 'PMS-VALPROIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002341', 'DRS2341', 'PNEUMOMIST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002342', 'DRS2342', 'PODOPHYLLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002343', 'DRS2343', 'PODOPHYLLUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002344', 'DRS2344', 'POLIBAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002345', 'DRS2345', 'POLIOMYELITIS VACCINE(LIVE)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002346', 'DRS2346', 'POLIOVIRUS VACCINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002347', 'DRS2347', 'POLOXAMER188', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002348', 'DRS2348', 'POLYCARBOPHIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002349', 'DRS2349', 'POLYCILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002350', 'DRS2350', 'POLYETHYLENE GLYCOL AND ELECTROLYTE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002351', 'DRS2351', 'POLYGELINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002352', 'DRS2352', 'POLYMOX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002353', 'DRS2353', 'POLYMYXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002354', 'DRS2354', 'POLYPRESS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002355', 'DRS2355', 'POLYTHIAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002356', 'DRS2356', 'PONSTAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002357', 'DRS2357', 'PONTOCAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002358', 'DRS2358', 'PORFIMER', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002359', 'DRS2359', 'POTABA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002360', 'DRS2360', 'POTABA ENVULES', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002361', 'DRS2361', 'POTASSIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002362', 'DRS2362', 'POTASSIUM AMINOBENZOATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002363', 'DRS2363', 'POTASSIUM CHLORIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002364', 'DRS2364', 'POTASSIUM CITRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002365', 'DRS2365', 'POTASSIUM IODIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002366', 'DRS2366', 'POTASSIUM PARA-AMINOBENZOATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002367', 'DRS2367', 'POTASSIUM PERMANGANATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002368', 'DRS2368', 'potassium preparations', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002369', 'DRS2369', 'potassium sodium hydrogen citrate', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002370', 'DRS2370', 'potassium-sparing diuretics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002371', 'DRS2371', 'POXI', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002372', 'DRS2372', 'PRALIDOXIME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002373', 'DRS2373', 'PRAMEGEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002374', 'DRS2374', 'PRAMOXINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002375', 'DRS2375', 'PRAVACHOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002376', 'DRS2376', 'PRAVASTATIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002377', 'DRS2377', 'PRAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002378', 'DRS2378', 'PRAZEPAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002379', 'DRS2379', 'PRAZIQUANTEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002380', 'DRS2380', 'PRAZOSIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002381', 'DRS2381', 'PRECOSE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002382', 'DRS2382', 'PREDATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002383', 'DRS2383', 'PREDCOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002384', 'DRS2384', 'PREDNICEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002385', 'DRS2385', 'PREDNISOLONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002386', 'DRS2386', 'PREDNISONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002387', 'DRS2387', 'PRELONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002388', 'DRS2388', 'PRELU-2', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002389', 'DRS2389', 'PREMARIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002390', 'DRS2390', 'PREPCAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002391', 'DRS2391', 'PREPIDIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002392', 'DRS2392', 'PREPULSID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002393', 'DRS2393', 'PRESSYN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002394', 'DRS2394', 'PREVACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002395', 'DRS2395', 'PREVEX HC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002396', 'DRS2396', 'PRIFINIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002397', 'DRS2397', 'PRILOSEC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002398', 'DRS2398', 'PRIMACOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002399', 'DRS2399', 'PRIMAQUINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002400', 'DRS2400', 'PRIMAXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002401', 'DRS2401', 'PRIMIDONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002402', 'DRS2402', 'PRINCIPEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002403', 'DRS2403', 'PRINIVIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002404', 'DRS2404', 'PRISCOLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002405', 'DRS2405', 'PRO-AIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002406', 'DRS2406', 'PROAMATINE.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002407', 'DRS2407', 'PROBATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002408', 'DRS2408', 'PROBENECID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002409', 'DRS2409', 'PROBUCOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002410', 'DRS2410', 'PROCAIN BENZYL PENICILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002411', 'DRS2411', 'PROCAINAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002412', 'DRS2412', 'PROCARBAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002413', 'DRS2413', 'PROCATEROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002414', 'DRS2414', 'PROCHLORPERAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002415', 'DRS2415', 'PROCRIT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002416', 'DRS2416', 'PROCTOSEDYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002417', 'DRS2417', 'PROCYTOX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002418', 'DRS2418', 'PROFILNINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002419', 'DRS2419', 'PROGESTERONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002420', 'DRS2420', 'progestins', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002421', 'DRS2421', 'PROGLUMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002422', 'DRS2422', 'PROGLYCEM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002423', 'DRS2423', 'PROGRAF', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002424', 'DRS2424', 'PROGUANIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002425', 'DRS2425', 'PROHANCE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002426', 'DRS2426', 'PROHIM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002427', 'DRS2427', 'prokinetic benzamides', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002428', 'DRS2428', 'PROLEUKIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002429', 'DRS2429', 'PROLOPRIM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002430', 'DRS2430', 'PROMACOT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002431', 'DRS2431', 'PROMAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002432', 'DRS2432', 'PROMET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002433', 'DRS2433', 'PROMETHAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002434', 'DRS2434', 'PRONESTYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002435', 'DRS2435', 'PROPA P.H. 10 LIQUID ACNE SOAP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002436', 'DRS2436', 'PROPAFENONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002437', 'DRS2437', 'PROPANOLOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002438', 'DRS2438', 'propantheline', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002439', 'DRS2439', 'proparacaine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002440', 'DRS2440', 'PROPINE C CAP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002441', 'DRS2441', 'PROPIOMAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002442', 'DRS2442', 'propionic acid derivatives', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002443', 'DRS2443', 'PROPIRAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002444', 'DRS2444', 'PROPLEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002445', 'DRS2445', 'PROPOFOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002446', 'DRS2446', 'PROPOXYPHENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002447', 'DRS2447', 'PROPRANOLOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002448', 'DRS2448', 'PROPYLTHIOURACIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002449', 'DRS2449', 'PROPYL-THYRACIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002450', 'DRS2450', 'PROREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002451', 'DRS2451', 'PROSCAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002452', 'DRS2452', 'PROSOM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002453', 'DRS2453', 'PROSTAGLADINS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002454', 'DRS2454', 'PROSTAPHLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002455', 'DRS2455', 'PROSTEP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002456', 'DRS2456', 'PROSTIGMIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002457', 'DRS2457', 'PROSTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002458', 'DRS2458', 'PROSTIN E2', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002459', 'DRS2459', 'PROSTIN F2 ALPHA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002460', 'DRS2460', 'PROSTIN VR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002461', 'DRS2461', 'PROSTIN VR PEDIATRIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002462', 'DRS2462', 'PROTAMINE SULFATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002463', 'DRS2463', 'PROTHAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002464', 'DRS2464', 'PROTHIADEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002465', 'DRS2465', 'PROTILASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002466', 'DRS2466', 'PROTOSTAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002467', 'DRS2467', 'PROTRIPTYLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002468', 'DRS2468', 'PROTROPIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002469', 'DRS2469', 'PROVAMICINA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002470', 'DRS2470', 'PROVOCHOLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002471', 'DRS2471', 'PROZAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002472', 'DRS2472', 'PSEUDOEPHEDRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002473', 'DRS2473', 'PSORIGEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002474', 'DRS2474', 'PSORINAIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002475', 'DRS2475', 'PSYLLIUM HYDROPHILIC MUCILLOID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002476', 'DRS2476', 'PT 105', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002477', 'DRS2477', 'PTC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002478', 'DRS2478', 'PTU', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002479', 'DRS2479', 'PULMICORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002480', 'DRS2480', 'PULMOZYME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002481', 'DRS2481', 'PURINETHOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002482', 'DRS2482', 'PURINO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002483', 'DRS2483', 'PVF', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002484', 'DRS2484', 'PYOPEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002485', 'DRS2485', 'PYRALVEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002486', 'DRS2486', 'PYRANTEL PAMOATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002487', 'DRS2487', 'PYRAZINAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002488', 'DRS2488', 'PYRETHRINS AND PIPERONYL BUTOXIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002489', 'DRS2489', 'pyridostigmine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002490', 'DRS2490', 'PYRIDOSTIGMINE BR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002491', 'DRS2491', 'PYRIDOXINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002492', 'DRS2492', 'PYRILAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002493', 'DRS2493', 'PYRIMETHAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002494', 'DRS2494', 'pyrimidine analogs', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002495', 'DRS2495', 'PYRITHIONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002496', 'DRS2496', 'PYRROBUTAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002497', 'DRS2497', 'PYRVINIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002498', 'DRS2498', 'QUAZEPAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002499', 'DRS2499', 'QUELICIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002500', 'DRS2500', 'QUESTRAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002501', 'DRS2501', 'QUIBRON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002502', 'DRS2502', 'QUINACRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002503', 'DRS2503', 'QUINAGLUTE DURA-TAB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002504', 'DRS2504', 'QUINALAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002505', 'DRS2505', 'QUINAPRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002506', 'DRS2506', 'QUINATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002507', 'DRS2507', 'QUINETHAZONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002508', 'DRS2508', 'QUINIDEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002509', 'DRS2509', 'QUINIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002510', 'DRS2510', 'quinine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002511', 'DRS2511', 'quinine derivatives', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002512', 'DRS2512', 'quinolenes', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002513', 'DRS2513', 'quinolones', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002514', 'DRS2514', 'QUINORA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002515', 'DRS2515', 'RABIES ANTISERUM FROM HORSE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002516', 'DRS2516', 'RABIES ANTISERUM FROM HUMAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002517', 'DRS2517', 'RABIES IMMUNE GLOBULIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002518', 'DRS2518', 'rabies serum obtained from horse', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002519', 'DRS2519', 'RABIES VACCINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002520', 'DRS2520', 'RACEMETHIONINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002521', 'DRS2521', 'RADANIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002522', 'DRS2522', 'RADIOSTOL FORTE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002523', 'DRS2523', 'RAMIPRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002524', 'DRS2524', 'RANITIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002525', 'DRS2525', 'RAUDIXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002526', 'DRS2526', 'RAUVAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002527', 'DRS2527', 'RAUVERID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002528', 'DRS2528', 'RAUWOLFIA SERPENTINA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002529', 'DRS2529', 'REACTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002530', 'DRS2530', 'READI-CAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002531', 'DRS2531', 'RECLOMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002532', 'DRS2532', 'RECTO-BARIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002533', 'DRS2533', 'RECTOCORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002534', 'DRS2534', 'REDERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002535', 'DRS2535', 'REGLAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002536', 'DRS2536', 'RELAXAZONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002537', 'DRS2537', 'REMERON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002538', 'DRS2538', 'REMIFENTANIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002539', 'DRS2539', 'REMULAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002540', 'DRS2540', 'REMULAR-S', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002541', 'DRS2541', 'RENEDIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002542', 'DRS2542', 'REP-PRED', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002543', 'DRS2543', 'RESERFIA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002544', 'DRS2544', 'RESERPINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002545', 'DRS2545', 'RESERPINE AND HYDROCHLOROTHIAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002546', 'DRS2546', 'RESERPINE AND HYDROFLUMETHIAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002547', 'DRS2547', 'RESERPINE AND HYDROFLUMETHIAZIDERES', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002548', 'DRS2548', 'RESERPINE, HYDRALAZINE, AND HYDROCH', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002549', 'DRS2549', 'RESORCINOL AND SULFUR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002550', 'DRS2550', 'RESORSINOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002551', 'DRS2551', 'RESYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002552', 'DRS2552', 'RETEPLASE, RECOMBINANT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002553', 'DRS2553', 'RETROVIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002554', 'DRS2554', 'reverse transcriptase inhibitors', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002555', 'DRS2555', 'reversible anticholinesterases', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002556', 'DRS2556', 'REVERSOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002557', 'DRS2557', 'REVEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002558', 'DRS2558', 'REVIA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002559', 'DRS2559', 'REVIMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002560', 'DRS2560', 'REXIGEN FORTE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002561', 'DRS2561', 'REZAMID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002562', 'DRS2562', 'REZULIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002563', 'DRS2563', 'RHEABAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002564', 'DRS2564', 'RHINALAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002565', 'DRS2565', 'RHINOCORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002566', 'DRS2566', 'RHO(D) IMMUNE GLOBULIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002567', 'DRS2567', 'RHODIS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002568', 'DRS2568', 'RHOGAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002569', 'DRS2569', 'RHOTRIMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002570', 'DRS2570', 'RHULICORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002571', 'DRS2571', 'RIABAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002572', 'DRS2572', 'RIBAVIRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002573', 'DRS2573', 'RIBOFLAVIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002574', 'DRS2574', 'RIFABUTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002575', 'DRS2575', 'RIFADIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002576', 'DRS2576', 'RIFAMATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002577', 'DRS2577', 'RIFAMPIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002578', 'DRS2578', 'RIFAMPIN AND ISONIAZID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002579', 'DRS2579', 'RIFAMPIN, ISONIAZID, AND PYRAZINAMI', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002580', 'DRS2580', 'rifamycins', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002581', 'DRS2581', 'RIFATER', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002582', 'DRS2582', 'RILUTEK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002583', 'DRS2583', 'RILUZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002584', 'DRS2584', 'RIMACTANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002585', 'DRS2585', 'RIMANTADINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002586', 'DRS2586', 'RIMEXOLONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002587', 'DRS2587', 'RIMSO-50', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002588', 'DRS2588', 'RISPERDAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002589', 'DRS2589', 'RISPERIDONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002590', 'DRS2590', 'RITALIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002591', 'DRS2591', 'RITODRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002592', 'DRS2592', 'RITONAVIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002593', 'DRS2593', 'RIVOTRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002594', 'DRS2594', 'ROBIDEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002595', 'DRS2595', 'ROBITET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002596', 'DRS2596', 'ROBITUSSIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002597', 'DRS2597', 'ROCALTROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002598', 'DRS2598', 'ROCHAGAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002599', 'DRS2599', 'ROCURONIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002600', 'DRS2600', 'ROFACT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002601', 'DRS2601', 'ROMAZICON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002602', 'DRS2602', 'ROPECT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002603', 'DRS2603', 'ROPIVACAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002604', 'DRS2604', 'ROVAMICINA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002605', 'DRS2605', 'ROVAMYCINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002606', 'DRS2606', 'ROXITHROMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002607', 'DRS2607', 'RUBELLA VACCINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002608', 'DRS2608', 'RUBEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002609', 'DRS2609', 'RYTHMODAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002610', 'DRS2610', 'SALAZOPYRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002611', 'DRS2611', 'salbutamol', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002612', 'DRS2612', 'salicylates', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002613', 'DRS2613', 'SALICYLAZOSULFAPYRIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002614', 'DRS2614', 'SALICYLIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002615', 'DRS2615', 'SALICYLIC ACID AND SULFUR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002616', 'DRS2616', 'SALMETEROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002617', 'DRS2617', 'SALMETEROL XINAFOATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002618', 'DRS2618', 'SALMOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002619', 'DRS2619', 'SALOFALK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002620', 'DRS2620', 'SALVENT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002621', 'DRS2621', 'SANDIMMUNE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002622', 'DRS2622', 'SANDOSTATIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002623', 'DRS2623', 'SANOREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002624', 'DRS2624', 'SANSERT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002625', 'DRS2625', 'SAQUINAVIR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002626', 'DRS2626', 'SARGRAMOSTIM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002627', 'DRS2627', 'SARISOL NO. 2', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002628', 'DRS2628', 'SASTID (AL) SCRUB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002629', 'DRS2629', 'SCABENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002630', 'DRS2630', 'SCOPOLAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002631', 'DRS2631', 'SD DEPRENYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002632', 'DRS2632', 'SEBEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002633', 'DRS2633', 'SEBULEX ANTISEBORRHEIC TREATMENT SH', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002634', 'DRS2634', 'SECOBARBITAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002635', 'DRS2635', 'SECOBARBITAL AND AMOBARBITAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002636', 'DRS2636', 'SECTRAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002637', 'DRS2637', 'sedative-hypnotic drugs', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002638', 'DRS2638', 'SEDATUSS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002639', 'DRS2639', 'SELDANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002640', 'DRS2640', 'SELEGILINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002641', 'DRS2641', 'SELENIUM SULFIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002642', 'DRS2642', 'SELSUN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002643', 'DRS2643', 'SEMUSTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002644', 'DRS2644', 'SENNA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002645', 'DRS2645', 'SENNOSIDES', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002646', 'DRS2646', 'SENSORCAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002647', 'DRS2647', 'SENTIAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002648', 'DRS2648', 'SERAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002649', 'DRS2649', 'SEREVENT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002650', 'DRS2650', 'SEROMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002651', 'DRS2651', 'SEROPHENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002652', 'DRS2652', 'SERPALAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002653', 'DRS2653', 'SERPASIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002654', 'DRS2654', 'SERTRALINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002655', 'DRS2655', 'SERZONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002656', 'DRS2656', 'SEVOFLURANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002657', 'DRS2657', 'SEVORANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002658', 'DRS2658', 'SHIELD BURNASEPT SPRAY', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002659', 'DRS2659', 'SHOGAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002660', 'DRS2660', 'SIBELIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002661', 'DRS2661', 'SILDIMAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002662', 'DRS2662', 'SILICONE OIL 5000 CENTISTOKESB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002663', 'DRS2663', 'SILVADENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002664', 'DRS2664', 'SILVER NITRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002665', 'DRS2665', 'SILVER SULFADIAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002666', 'DRS2666', 'SIMETHICONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002667', 'DRS2667', 'SIMVASTATIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002668', 'DRS2668', 'SINCALIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002669', 'DRS2669', 'SINEMET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002670', 'DRS2670', 'SINEQUAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002671', 'DRS2671', 'SINOGRAFIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002672', 'DRS2672', 'SISOMICIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002673', 'DRS2673', 'SKELAXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002674', 'DRS2674', 'SKELID.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002675', 'DRS2675', 'SLO-PHYLLIN GG', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002676', 'DRS2676', 'SLOPRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002677', 'DRS2677', 'SLOW-TRASICOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002678', 'DRS2678', 'SODA MINT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002679', 'DRS2679', 'SODIUM BICARBONATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002680', 'DRS2680', 'SODIUM CALCIUM EDETATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002681', 'DRS2681', 'SODIUM CHLORIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002682', 'DRS2682', 'SODIUM CROMOGLYCATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002683', 'DRS2683', 'SODIUM FLUORIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002684', 'DRS2684', 'SODIUM NITRITE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002685', 'DRS2685', 'sodium nitroprusside', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002686', 'DRS2686', 'SODIUM PHENYLBUTYRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002687', 'DRS2687', 'SODIUM PHOSPHATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002688', 'DRS2688', 'SODIUM PHOSPHATE P 32', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002689', 'DRS2689', 'SODIUM POLYSTYRENE SULFONATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002690', 'DRS2690', 'SODIUM TETRADECOL SULFATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002691', 'DRS2691', 'SODIUM THIOSULFATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002692', 'DRS2692', 'SODIUM VALPROATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002693', 'DRS2693', 'SODIUMHYPOCHLORITE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002694', 'DRS2694', 'SOFARIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002695', 'DRS2695', 'SOLFOTON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002696', 'DRS2696', 'SOL-O-PAKE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002697', 'DRS2697', 'SOLU-FLUR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002698', 'DRS2698', 'SOMA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002699', 'DRS2699', 'SOMATREM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002700', 'DRS2700', 'SOMATROPIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002701', 'DRS2701', 'SOMNOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002702', 'DRS2702', 'SOTACOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002703', 'DRS2703', 'SOTALOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002704', 'DRS2704', 'SPARFLOXACIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002705', 'DRS2705', 'SPECTAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002706', 'DRS2706', 'SPECTAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002707', 'DRS2707', 'SPECTINOMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002708', 'DRS2708', 'SPECTROBID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002709', 'DRS2709', 'SPECTRO-CYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002710', 'DRS2710', 'SPECTRO-PENTOLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002711', 'DRS2711', 'SPIRAMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002712', 'DRS2712', 'SPIRAMYCINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002713', 'DRS2713', 'SPIRAPRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002714', 'DRS2714', 'spironolactone', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002715', 'DRS2715', 'SPIRONOLACTONE AND HYDROCHLOROTHIAZ', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002716', 'DRS2716', 'SPIROZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002717', 'DRS2717', 'SPORANOX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002718', 'DRS2718', 'SPS SUSPENSION', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002719', 'DRS2719', 'SSD', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002720', 'DRS2720', 'STAPHCILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002721', 'DRS2721', 'STAVUDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002722', 'DRS2722', 'STEMETIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002723', 'DRS2723', 'STERAPRED', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002724', 'DRS2724', 'STIMATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002725', 'DRS2725', 'stimulant laxatives', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002726', 'DRS2726', 'STORZOLAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002727', 'DRS2727', 'STOXIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002728', 'DRS2728', 'STRAMONIUM TINCTURE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002729', 'DRS2729', 'STREPTASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002730', 'DRS2730', 'STREPTOKINASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002731', 'DRS2731', 'streptomycin', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002732', 'DRS2732', 'STREPTOZOCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002733', 'DRS2733', 'STRIFON FORTE DSC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002734', 'DRS2734', 'STRONTIUM CHLORIDE SR 89', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002735', 'DRS2735', 'SUBLIMAZE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002736', 'DRS2736', 'SUCCIMER', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002737', 'DRS2737', 'SUCCINIMIDES', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002738', 'DRS2738', 'SUCCINYLCHOLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002739', 'DRS2739', 'SUCOSTRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002740', 'DRS2740', 'SUCOSTRIN HIGH POTENCY', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002741', 'DRS2741', 'SUCRALFATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002742', 'DRS2742', 'SUFENTA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002743', 'DRS2743', 'SUFENTANIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002744', 'DRS2744', 'SULCONAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002745', 'DRS2745', 'SULCRATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002746', 'DRS2746', 'SULEO-M', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002747', 'DRS2747', 'SULFACETAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002748', 'DRS2748', 'SULFACETAMIDE SODIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002749', 'DRS2749', 'SULFADIAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002750', 'DRS2750', 'SULFADOXINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002751', 'DRS2751', 'SULFADOXINE AND PYRIMETHAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002752', 'DRS2752', 'SULFAMETHOXAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002753', 'DRS2753', 'SULFAMYLON.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002754', 'DRS2754', 'SULFANILAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002755', 'DRS2755', 'SULFAPYRIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002756', 'DRS2756', 'SULFASALAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002757', 'DRS2757', 'SULFINPYRAZONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002758', 'DRS2758', 'sulfonamides', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002759', 'DRS2759', 'sulfones', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002760', 'DRS2760', 'sulfonylureas', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002761', 'DRS2761', 'SULFORCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002762', 'DRS2762', 'SULFUR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002763', 'DRS2763', 'SULFURATED LIME', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002764', 'DRS2764', 'SULINDAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002765', 'DRS2765', 'SULPHO-LAC.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002766', 'DRS2766', 'SULSAL SOAP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002767', 'DRS2767', 'SUMATRIPTAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002768', 'DRS2768', 'SUMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002769', 'DRS2769', 'SUNDAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002770', 'DRS2770', 'SUPRANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002771', 'DRS2771', 'SUPREFACT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002772', 'DRS2772', 'SURAMIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002773', 'DRS2773', 'surfactant laxatives', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002774', 'DRS2774', 'SURGAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002775', 'DRS2775', 'SURMONTIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002776', 'DRS2776', 'SURVECTOR 100', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002777', 'DRS2777', 'suxamethonium', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002778', 'DRS2778', 'SYMADINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002779', 'DRS2779', 'SYMMETREL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002780', 'DRS2780', 'sympatholytics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002781', 'DRS2781', 'sympathomimetics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002782', 'DRS2782', 'sympathomimetics (alpha-agonists)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002783', 'DRS2783', 'sympathomimetics (beta-agonists)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002784', 'DRS2784', 'SYNAREL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002785', 'DRS2785', 'SYNEPHRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002786', 'DRS2786', 'SYNOPHYLATE-GG', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002787', 'DRS2787', 'SYNTOCINON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002788', 'DRS2788', 'SYPRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002789', 'DRS2789', 'TACARYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002790', 'DRS2790', 'TACHOCOMB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002791', 'DRS2791', 'TACRINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002792', 'DRS2792', 'TACROLIMUS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002793', 'DRS2793', 'TAGAMET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002794', 'DRS2794', 'TAMBOCOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002795', 'DRS2795', 'TAMOFEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002796', 'DRS2796', 'TAMONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002797', 'DRS2797', 'TAMOPLEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002798', 'DRS2798', 'TAMOXIFEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002799', 'DRS2799', 'TAPAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002800', 'DRS2800', 'TAR DOAK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002801', 'DRS2801', 'TARACTAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002802', 'DRS2802', 'TARAPHILIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002803', 'DRS2803', 'TARPASTE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002804', 'DRS2804', 'TAXOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002805', 'DRS2805', 'TAXOTERE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002806', 'DRS2806', 'T-CYPIONATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002807', 'DRS2807', 'T-DIET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002808', 'DRS2808', 'TEARISOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002809', 'DRS2809', 'TEBAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002810', 'DRS2810', 'TEGAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002811', 'DRS2811', 'TEGISON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002812', 'DRS2812', 'TEGOPEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002813', 'DRS2813', 'TEGRETOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002814', 'DRS2814', 'TEGRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002815', 'DRS2815', 'TELACHLOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002816', 'DRS2816', 'TELDRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002817', 'DRS2817', 'TELFAST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002818', 'DRS2818', 'TEMARIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002819', 'DRS2819', 'TEMAZEPAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002820', 'DRS2820', 'TENEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002821', 'DRS2821', 'TENIPOSIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002822', 'DRS2822', 'TENORETIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002823', 'DRS2823', 'TENORMIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002824', 'DRS2824', 'TENOXICAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002825', 'DRS2825', 'TENSILON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002826', 'DRS2826', 'TENSIONMIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002827', 'DRS2827', 'TENUATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002828', 'DRS2828', 'TEPANIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002829', 'DRS2829', 'TERAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002830', 'DRS2830', 'TERAZOSIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002831', 'DRS2831', 'TERBINAFINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002832', 'DRS2832', 'TERBUTALINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002833', 'DRS2833', 'TERCONAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002834', 'DRS2834', 'TERFENADINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002835', 'DRS2835', 'TERIPARATIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002836', 'DRS2836', 'TERRAMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002837', 'DRS2837', 'TERSA-TAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002838', 'DRS2838', 'TESSALON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002839', 'DRS2839', 'TESTAMONE 100', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002840', 'DRS2840', 'TESTAQUA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002841', 'DRS2841', 'TESTEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002842', 'DRS2842', 'TESTODERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002843', 'DRS2843', 'TESTOPEL PELLETS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002844', 'DRS2844', 'TESTOSTERONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002845', 'DRS2845', 'TESTRED', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002846', 'DRS2846', 'TESTRED CYPIONATE 200', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002847', 'DRS2847', 'TESTRIN-P.A', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002848', 'DRS2848', 'TETANUS ANTITOXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002849', 'DRS2849', 'TETANUS TOXOID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002850', 'DRS2850', 'TETRACAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002851', 'DRS2851', 'TETRACHLORETHYLENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002852', 'DRS2852', 'TETRACYCLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002853', 'DRS2853', 'tetracyclines', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002854', 'DRS2854', 'TETRACYN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002855', 'DRS2855', 'TETRAMISOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002856', 'DRS2856', 'TEXACORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002857', 'DRS2857', 'T-GEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002858', 'DRS2858', 'T-GEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002859', 'DRS2859', 'THALIDOMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002860', 'DRS2860', 'THALITONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002861', 'DRS2861', 'THC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002862', 'DRS2862', 'THEOLATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002863', 'DRS2863', 'THEOPHYLLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002864', 'DRS2864', 'THEOPHYLLINE AND GLYCERYL GUAIACOLA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002865', 'DRS2865', 'THEOPHYLLINE AND GUAIFENESIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002866', 'DRS2866', 'THEOPHYLLINE(SUSTAIN)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002867', 'DRS2867', 'THEOPHYLLINE, EPHEDRINE, AND PHENOB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002868', 'DRS2868', 'theophyllines', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002869', 'DRS2869', 'THEOTAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002870', 'DRS2870', 'THERAC LOTION', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002871', 'DRS2871', 'THERMAZENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002872', 'DRS2872', 'THEROXIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002873', 'DRS2873', 'THIABENDAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002874', 'DRS2874', 'THIACETAZONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002875', 'DRS2875', 'THIAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002876', 'DRS2876', 'THIAMPHENICOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002877', 'DRS2877', 'thiazide diuretics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002878', 'DRS2878', 'THIETHYLPERAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002879', 'DRS2879', 'thioamides', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002880', 'DRS2880', 'THIOGUANINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002881', 'DRS2881', 'THIOLA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002882', 'DRS2882', 'THIONEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002883', 'DRS2883', 'thiopental', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002884', 'DRS2884', 'THIOPROPAZATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002885', 'DRS2885', 'THIOPROPERAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002886', 'DRS2886', 'thiopurines', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002887', 'DRS2887', 'THIORIDAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002888', 'DRS2888', 'THIOTEPA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002889', 'DRS2889', 'THIOTHIXENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002890', 'DRS2890', 'THIOTHIXENE HCL INTENSOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002891', 'DRS2891', 'thrombolytic drugs', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002892', 'DRS2892', 'THYBINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002893', 'DRS2893', 'THYROGLOBULIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002894', 'DRS2894', 'THYROID EXTRACT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002895', 'DRS2895', 'thyroid hormones', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002896', 'DRS2896', 'THYROTROPIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002897', 'DRS2897', 'THYROXINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002898', 'DRS2898', 'THYTROPAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002899', 'DRS2899', 'TIAFEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002900', 'DRS2900', 'TIAPROFENIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002901', 'DRS2901', 'TIBOLONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002902', 'DRS2902', 'TICARCILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002903', 'DRS2903', 'TICARCILLIN AND CLAVULANATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002904', 'DRS2904', 'TICLID.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002905', 'DRS2905', 'TICLOPIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002906', 'DRS2906', 'TICON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002907', 'DRS2907', 'TIGAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002908', 'DRS2908', 'TIJECT-20', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002909', 'DRS2909', 'TILADE.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002910', 'DRS2910', 'TILUDRONATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002911', 'DRS2911', 'timolol', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002912', 'DRS2912', 'TIMOLOL.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002913', 'DRS2913', 'TINACTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002914', 'DRS2914', 'TINCTURE CARDAMOM COMPOUND', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002915', 'DRS2915', 'TING', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002916', 'DRS2916', 'TINIDAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002917', 'DRS2917', 'TIOCONAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002918', 'DRS2918', 'TIOPRONIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002919', 'DRS2919', 'TIPRAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002920', 'DRS2920', 'TIROPRAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002921', 'DRS2921', 'TIZANIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002922', 'DRS2922', 'TOBRAMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002923', 'DRS2923', 'TOBREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002924', 'DRS2924', 'TOCAINIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002925', 'DRS2925', 'TOFRANIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002926', 'DRS2926', 'TOLAZAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002927', 'DRS2927', 'TOLAZOLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002928', 'DRS2928', 'TOLBUTAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002929', 'DRS2929', 'TOLECTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002930', 'DRS2930', 'TOLINASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002931', 'DRS2931', 'TOLMETIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002932', 'DRS2932', 'TOLNAFTATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002933', 'DRS2933', 'TOLPERISONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002934', 'DRS2934', 'TOL-TAB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002935', 'DRS2935', 'TOMOCAT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002936', 'DRS2936', 'TONOCARD', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002937', 'DRS2937', 'TONOCARD(1)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002938', 'DRS2938', 'TONOPAQUE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002939', 'DRS2939', 'TOPEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002940', 'DRS2940', 'TOPIRAMATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002941', 'DRS2941', 'TOPOTECAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002942', 'DRS2942', 'TORADOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002943', 'DRS2943', 'TORECAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002944', 'DRS2944', 'TORNALATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002945', 'DRS2945', 'TORSEMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002946', 'DRS2946', 'TOTACILLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002947', 'DRS2947', 'TOURO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002948', 'DRS2948', 'TRAMADOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002949', 'DRS2949', 'TRANCOT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002950', 'DRS2950', 'TRANDATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002951', 'DRS2951', 'TRANDOLAPRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002952', 'DRS2952', 'TRANEXAMIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002953', 'DRS2953', 'TRANSDERM-NITRO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002954', 'DRS2954', 'TRANXENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002955', 'DRS2955', 'TRANYLCYPROMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002956', 'DRS2956', 'TRASICOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002957', 'DRS2957', 'TRASYLOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002958', 'DRS2958', 'TRAVOGEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002959', 'DRS2959', 'TRAZODONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002960', 'DRS2960', 'TRAZON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002961', 'DRS2961', 'TRCS-VEROLAB', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002962', 'DRS2962', 'TRECATOR-SC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002963', 'DRS2963', 'TRETINOIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002964', 'DRS2964', 'TRIACET', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002965', 'DRS2965', 'TRIADAPIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002966', 'DRS2966', 'TRIADERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002967', 'DRS2967', 'TRIALODINE.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002968', 'DRS2968', 'TRIAMCINOLONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002969', 'DRS2969', 'TRIAMTERENE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002970', 'DRS2970', 'TRIAMTERENE AND HYDROCHLOROTHIAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002971', 'DRS2971', 'TRIAZO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002972', 'DRS2972', 'TRIAZOLAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002973', 'DRS2973', 'TRIBAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002974', 'DRS2974', 'TRIBENZAGAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002975', 'DRS2975', 'TRICHLORMETHIAZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002976', 'DRS2976', 'TRICHLOROACETIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002977', 'DRS2977', 'tricyclic antidepressants', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002978', 'DRS2978', 'TRIDERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002979', 'DRS2979', 'TRIDIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002980', 'DRS2980', 'TRIEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002981', 'DRS2981', 'TRIENTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002982', 'DRS2982', 'TRIFLUOPERAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002983', 'DRS2983', 'TRIFLUOROTHYMIDINE(F3T)', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002984', 'DRS2984', 'TRIFLUPROMAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002985', 'DRS2985', 'TRIFLURIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002986', 'DRS2986', 'TRIHEXANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002987', 'DRS2987', 'TRIHEXY', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002988', 'DRS2988', 'TRIHEXYPHENIDYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002989', 'DRS2989', 'TRIKACIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002990', 'DRS2990', 'TRILOSTANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002991', 'DRS2991', 'TRIMEBUTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002992', 'DRS2992', 'TRIMEPRAZINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002993', 'DRS2993', 'TRIMETHAPHAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002994', 'DRS2994', 'TRIMETHOBENZAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002995', 'DRS2995', 'TRIMETHOPRIM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002996', 'DRS2996', 'TRIMETREXATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002997', 'DRS2997', 'TRIMIP', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002998', 'DRS2998', 'TRIMIPRAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000002999', 'DRS2999', 'TRIMOX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003000', 'DRS3000', 'TRIMPEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003001', 'DRS3001', 'TRIOLAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003002', 'DRS3002', 'TRIOXSALEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003003', 'DRS3003', 'TRIOXYSALEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003004', 'DRS3004', 'TRIPOTASSIUM DICITRATO BISMUTHATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003005', 'DRS3005', 'TRIPROLIDINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003006', 'DRS3006', 'TRIPTIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003007', 'DRS3007', 'TRISODIUM PHOSPHONOFORMATE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003008', 'DRS3008', 'TRISORALEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003009', 'DRS3009', 'TROBICIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003010', 'DRS3010', 'TROCAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003011', 'DRS3011', 'TROGLITAZONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003012', 'DRS3012', 'TRONOTHANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003013', 'DRS3013', 'TROPICACYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003014', 'DRS3014', 'TROPICAMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003015', 'DRS3015', 'TRUSOPT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003016', 'DRS3016', 'TRYMEGEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003017', 'DRS3017', 'TUBARINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003018', 'DRS3018', 'TUBOCURARINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003019', 'DRS3019', 'TUINAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003020', 'DRS3020', 'TYLENOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003021', 'DRS3021', 'TYLOSTERONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003022', 'DRS3022', 'TYPHOID VI POLYSACCHARIDE VACCINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003023', 'DRS3023', 'ULONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003024', 'DRS3024', 'ULTANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003025', 'DRS3025', 'ULTIVA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003026', 'DRS3026', 'ULTRASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003027', 'DRS3027', 'UNDECYLENIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003028', 'DRS3028', 'UNIBAR-100', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003029', 'DRS3029', 'UNI-BRONCHIAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003030', 'DRS3030', 'UNIPEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003031', 'DRS3031', 'UNIVASC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003032', 'DRS3032', 'URABETH', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003033', 'DRS3033', 'URACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003034', 'DRS3034', 'URACIL MUSTARD', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003035', 'DRS3035', 'URECHOLINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003036', 'DRS3036', 'UREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003037', 'DRS3037', 'uricosurics', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003038', 'DRS3038', 'URIDON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003039', 'DRS3039', 'urinary acidifiers', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003040', 'DRS3040', 'urinary alkalinizers', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003041', 'DRS3041', 'URISPAS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003042', 'DRS3042', 'UROBAK', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003043', 'DRS3043', 'UROKINASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003044', 'DRS3044', 'UROLENE BLUE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003045', 'DRS3045', 'UROMITEXAN.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003046', 'DRS3046', 'UROVIST CYSTO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003047', 'DRS3047', 'UROVIST SODIUM 300', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003048', 'DRS3048', 'UROZIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003049', 'DRS3049', 'URSODEOXYCHOLIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003050', 'DRS3050', 'VALIUM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003051', 'DRS3051', 'VALPROIC ACID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003052', 'DRS3052', 'VALSARTAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003053', 'DRS3053', 'VANADOM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003054', 'DRS3054', 'VANCENASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003055', 'DRS3055', 'VANCERIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003056', 'DRS3056', 'VANCOCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003057', 'DRS3057', 'VANCOLED', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003058', 'DRS3058', 'VANCOMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003059', 'DRS3059', 'VANOXIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003060', 'DRS3060', 'VANSEB CREAM DANDRUFF SHAMPOO', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003061', 'DRS3061', 'VANTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003062', 'DRS3062', 'VAQTA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003063', 'DRS3063', 'VASCOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003064', 'DRS3064', 'VASOCLEAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003065', 'DRS3065', 'VASOCON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003066', 'DRS3066', 'VASODILAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003067', 'DRS3067', 'vasodilators', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003068', 'DRS3068', 'VASOPRESSIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003069', 'DRS3069', 'VASOTEC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003070', 'DRS3070', 'VASOXYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003071', 'DRS3071', 'V-CILLIN K', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003072', 'DRS3072', 'VECURONIUM BROMIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003073', 'DRS3073', 'VEETIDS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003074', 'DRS3074', 'VELBAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003075', 'DRS3075', 'VELBE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003076', 'DRS3076', 'VELSAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003077', 'DRS3077', 'VELTANE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003078', 'DRS3078', 'VENTOLIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003079', 'DRS3079', 'VEPESID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003080', 'DRS3080', 'VERAPAMIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003081', 'DRS3081', 'VERELAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003082', 'DRS3082', 'VERMOX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003083', 'DRS3083', 'VERSED', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003084', 'DRS3084', 'VERSEL LOTION', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003085', 'DRS3085', 'VESANOID', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003086', 'DRS3086', 'VEXOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003087', 'DRS3087', 'VIAGRA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003088', 'DRS3088', 'VIBRAMYCIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003089', 'DRS3089', 'VIBRA-TABS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003090', 'DRS3090', 'VICKS SINEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003091', 'DRS3091', 'VIDARABINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003092', 'DRS3092', 'VIDEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003093', 'DRS3093', 'VINBLASTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003094', 'DRS3094', 'VINCASAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003095', 'DRS3095', 'VINCREX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003096', 'DRS3096', 'VINCRISTINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003097', 'DRS3097', 'VINDESINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003098', 'DRS3098', 'VIOFORM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003099', 'DRS3099', 'VIOKASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003100', 'DRS3100', 'VIRA-A.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003101', 'DRS3101', 'VIRAMUNE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003102', 'DRS3102', 'VIRAZOLE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003103', 'DRS3103', 'VIRILON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003104', 'DRS3104', 'VIRILON IM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003105', 'DRS3105', 'VIROPTIC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003106', 'DRS3106', 'VISDERM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003107', 'DRS3107', 'VISIPAQUE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003108', 'DRS3108', 'VISKEN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003109', 'DRS3109', 'VISTIDE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003110', 'DRS3110', 'VITAMIN A', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003111', 'DRS3111', 'vitamin b complex', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003112', 'DRS3112', 'VITAMIN B1', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003113', 'DRS3113', 'VITAMIN B1.', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003114', 'DRS3114', 'VITAMIN B12', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003115', 'DRS3115', 'VITAMIN B1-6-12', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003116', 'DRS3116', 'VITAMIN B2', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003117', 'DRS3117', 'VITAMIN B3', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003118', 'DRS3118', 'VITAMIN B6', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003119', 'DRS3119', 'VITAMIN C', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003120', 'DRS3120', 'vitamin d', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003121', 'DRS3121', 'vitamin d3', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003122', 'DRS3122', 'vitamin k', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003123', 'DRS3123', 'VITAMIN K1', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003124', 'DRS3124', 'vitamin k3', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003125', 'DRS3125', 'VIVACTIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003126', 'DRS3126', 'VLEMASQUE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003127', 'DRS3127', 'VLEMINCKX''S SOLUTION', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003128', 'DRS3128', 'VM26', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003129', 'DRS3129', 'VOLTAREN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003130', 'DRS3130', 'VONTROL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003131', 'DRS3131', 'VP-16', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003132', 'DRS3132', 'WARFARIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003133', 'DRS3133', 'WARFILONE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003134', 'DRS3134', 'WEHLESS', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003135', 'DRS3135', 'WELLBUTRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003136', 'DRS3136', 'WESTCORT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003137', 'DRS3137', 'WHITFIELD''S OINTMENT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003138', 'DRS3138', 'WINPRED', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003139', 'DRS3139', 'WINRHO SD', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003140', 'DRS3140', 'WOLFINA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003141', 'DRS3141', 'WYAMINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003142', 'DRS3142', 'WYMOX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003143', 'DRS3143', 'WYTENSIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003144', 'DRS3144', 'XALATAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003145', 'DRS3145', 'XANAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003146', 'DRS3146', 'xanthines', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003147', 'DRS3147', 'XATRAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003148', 'DRS3148', 'XERAC', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003149', 'DRS3149', 'XYLOCAINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003150', 'DRS3150', 'XYLOCARD', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003151', 'DRS3151', 'XYLOCARD 100', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003152', 'DRS3152', 'YOCON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003153', 'DRS3153', 'YODOQUINOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003154', 'DRS3154', 'YODOXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003155', 'DRS3155', 'YOHIMAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003156', 'DRS3156', 'YOHIMBINE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003157', 'DRS3157', 'YOHIMEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003158', 'DRS3158', 'YOMAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003159', 'DRS3159', 'YOVITAL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003160', 'DRS3160', 'YUTOPAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003161', 'DRS3161', 'ZAFIRLUKAST', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003162', 'DRS3162', 'ZAGAM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003163', 'DRS3163', 'ZANAFLEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003164', 'DRS3164', 'ZANOSAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003165', 'DRS3165', 'ZANTRYL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003166', 'DRS3166', 'ZARONTIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003167', 'DRS3167', 'ZAROXOLYN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003168', 'DRS3168', 'ZEASORB-AF', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003169', 'DRS3169', 'ZEBETA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003170', 'DRS3170', 'ZEBRAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003171', 'DRS3171', 'ZEMURON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003172', 'DRS3172', 'ZENTEL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003173', 'DRS3173', 'ZERIT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003174', 'DRS3174', 'ZEROXIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003175', 'DRS3175', 'ZESTRIL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003176', 'DRS3176', 'ZETAR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003177', 'DRS3177', 'zidovudine', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003178', 'DRS3178', 'ZINECARD', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003179', 'DRS3179', 'ZITHROMAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003180', 'DRS3180', 'ZOCOR', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003181', 'DRS3181', 'ZOFRAN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003182', 'DRS3182', 'ZOLADEX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003183', 'DRS3183', 'ZOLOFT', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003184', 'DRS3184', 'ZOLPIDEM', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003185', 'DRS3185', 'ZONALON', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003186', 'DRS3186', 'ZORPRIN', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003187', 'DRS3187', 'ZOSTRIX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003188', 'DRS3188', 'ZOVIRAX', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003189', 'DRS3189', 'ZUCLOPENTHIXOL', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003190', 'DRS3190', 'ZYMASE', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003191', 'DRS3191', 'ZYPREXA', '1');
INSERT INTO b_item_drug_standard VALUES ('2820000003192', 'DRS3192', 'ZYRTEC', '1');
ALTER TABLE ONLY b_item_drug_standard
    ADD CONSTRAINT b_item_drug_standard_pkey PRIMARY KEY (b_item_drug_standard_id);
ALTER INDEX public.b_item_drug_standard_pkey OWNER TO postgres;


--วันที่ 14/03/2549

--เพิ่มตาราง map ยามาตรฐานกับ item
CREATE TABLE b_item_drug_standard_map_item (
    b_item_drug_standard_map_item_id character varying(255) NOT NULL,
    b_item_drug_standard_id character varying(255),
    b_item_id character varying(255)
);
ALTER TABLE ONLY b_item_drug_standard_map_item
    ADD CONSTRAINT b_item_drug_standard_map_item_pkey PRIMARY KEY (b_item_drug_standard_map_item_id);
ALTER INDEX public.b_item_drug_standard_map_item_pkey OWNER TO postgres;

--เพิ่มตาราง map ยาที่มีปฏิริยาต่อกัน
CREATE TABLE b_item_drug_interaction (
    b_item_drug_interaction_id character varying(255) NOT NULL,
    drug_standard_original_id character varying(255),
    drug_standard_interaction_id character varying(255),
    item_drug_interaction_blood_presure character varying(255),
    item_drug_interaction_pregnant character varying(255),
    item_drug_interaction_type_id character varying(255),
    item_drug_interaction_force character varying(255),
    item_drug_interaction_act character varying(255),
    item_drug_interaction_repair character varying(255)
);
ALTER TABLE ONLY b_item_drug_interaction
    ADD CONSTRAINT b_item_drug_interaction_pkey PRIMARY KEY (b_item_drug_interaction_id);
ALTER INDEX public.b_item_drug_interaction_pkey OWNER TO postgres;

--เพิ่มตารางเก็บรายละเอียดของยาที่่มีปฏิกิริยากัน
CREATE TABLE b_item_drug_interaction_detail (
    b_item_drug_interaction_detail_id character varying(255) NOT NULL,
    b_item_drug_interaction_id character varying(255),
    b_item_original_id character varying(255),
    b_item_interaction_id character varying(255)
);
ALTER TABLE ONLY b_item_drug_interaction_detail
    ADD CONSTRAINT b_item_drug_interaction_detail_pkey PRIMARY KEY (b_item_drug_interaction_detail_id);
ALTER INDEX public.b_item_drug_interaction_detail_pkey OWNER TO postgres;

--เพิ่มตารางเก็บข้อมูลชนิดของยาที่มีปฏิกิริยากัน
CREATE TABLE f_drug_interaction (
    f_drug_interaction_number character varying(255) NOT NULL,
    f_drug_interaction_description character varying(255)
);
INSERT INTO f_drug_interaction VALUES ('1', 'ยา');
INSERT INTO f_drug_interaction VALUES ('2', 'ความดัน');
INSERT INTO f_drug_interaction VALUES ('3', 'ตั้งครรภ์');
ALTER TABLE ONLY f_drug_interaction
    ADD CONSTRAINT f_drug_interaction_pkey PRIMARY KEY (f_drug_interaction_number);
ALTER INDEX public.f_drug_interaction_pkey OWNER TO postgres;

--วันท ี่17/03/2549

--เพิ่มข้อมูลของ item ทุกตัวในตาราง b_item_drug_standard_map_item เพื่อเป็นข้อมูลตั้งต้นให้
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000001', '', '1740000000002');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000002', '', '1741883788769');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000003', '', '1740000000003');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000004', '', '1745155103824');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000005', '', '1740000000004');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000006', '', '1747507275276');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000007', '', '1740000000005');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000008', '', '1740000000006');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000009', '', '1740000000007');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000010', '', '1740000000008');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000011', '', '1740000000009');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000012', '', '1745530678298');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000013', '', '1746592133149');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000014', '', '1740000000010');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000015', '', '1742277240582');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000016', '', '1740000000347');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000017', '', '1740000000011');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000018', '', '1740000000012');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000019', '', '1741974048143');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000020', '', '1740000000013');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000021', '', '1740000000014');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000022', '', '1740000000015');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000023', '', '1740000000016');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000024', '', '1747638192663');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000025', '', '1740000000017');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000026', '', '1745222672338');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000027', '', '1740000000019');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000028', '', '1740000000020');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000029', '', '1740000000021');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000030', '', '1740733193077');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000031', '', '1740000000022');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000032', '', '1740000000023');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000033', '', '1740000000024');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000034', '', '1740000000025');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000035', '', '1745938113207');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000036', '', '1746413088877');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000037', '', '1749074173366');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000038', '', '1740000000018');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000039', '', '1746349282403');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000040', '', '1740000000026');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000041', '', '1740000000027');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000042', '', '1741615010114');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000043', '', '1748554396939');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000044', '', '1740000000028');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000045', '', '1740000000030');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000046', '', '1740000000031');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000047', '', '1740000000032');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000048', '', '1746108597296');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000049', '', '1740000000033');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000050', '', '1740000000035');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000051', '', '1740000000034');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000052', '', '1740000000036');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000053', '', '1741420329351');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000054', '', '1740000000349');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000055', '', '1740000000348');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000056', '', '1740000000037');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000057', '', '1740000000038');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000058', '', '1744298624713');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000059', '', '1740000000039');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000060', '', '1740000000041');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000061', '', '1740000000042');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000062', '', '1740000000040');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000063', '', '1749948524689');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000064', '', '1740000000043');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000065', '', '1740000000044');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000066', '', '1742157223046');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000067', '', '1740000000045');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000068', '', '1740000000046');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000069', '', '1740511242656');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000070', '', '1740000000048');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000071', '', '1740000000047');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000072', '', '1740000000049');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000073', '', '1744916330781');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000074', '', '1744564734096');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000075', '', '1740000000050');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000076', '', '1743233839119');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000077', '', '1740000000051');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000078', '', '1740000000052');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000079', '', '1740000000053');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000080', '', '1740000000054');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000081', '', '1740000000055');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000082', '', '1740000000059');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000083', '', '1740000000061');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000084', '', '1740000000062');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000085', '', '1740000000060');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000086', '', '1748681540322');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000087', '', '1746107232564');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000088', '', '1745102754809');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000089', '', '1740000000063');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000090', '', '1740000000064');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000091', '', '1740000000065');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000092', '', '1740000000066');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000093', '', '1740000000067');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000094', '', '1742157172059');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000095', '', '1740000000068');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000096', '', '1743873228220');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000097', '', '1740000000069');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000098', '', '1740000000070');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000099', '', '1740000000071');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000100', '', '1740000000072');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000101', '', '1740000000073');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000102', '', '1740000000074');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000103', '', '1740000000075');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000104', '', '1740000000076');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000105', '', '1740000000350');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000106', '', '1749134467840');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000107', '', '1743233573291');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000108', '', '1740000000077');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000109', '', '1740000000308');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000110', '', '1740000000290');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000111', '', '1740000000289');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000112', '', '1740000000056');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000113', '', '1740000000057');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000114', '', '1740000000058');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000115', '', '1740000000078');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000116', '', '1749162760768');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000117', '', '1740000000079');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000118', '', '1740000000080');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000119', '', '1740000000081');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000120', '', '1740000000082');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000121', '', '1740000000083');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000122', '', '1740000000084');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000123', '', '1749781262323');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000124', '', '1747591702598');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000125', '', '1740000000087');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000126', '', '1740000000088');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000127', '', '1740000000089');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000128', '', '1748670007025');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000129', '', '1740000000091');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000130', '', '1740000000090');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000131', '', '1749745767492');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000132', '', '1745814826015');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000133', '', '1740000000092');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000134', '', '1740000000093');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000135', '', '1740000000094');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000136', '', '1740000000095');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000137', '', '1740000000096');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000138', '', '1748553447385');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000139', '', '1748943754738');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000140', '', '1740000000097');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000141', '', '1749840213579');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000142', '', '1740000000098');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000143', '', '1740000000099');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000144', '', '1740000000100');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000145', '', '1740000000101');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000146', '', '1740000000102');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000147', '', '1746273543741');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000148', '', '1749855597962');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000149', '', '1747448579498');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000150', '', '1740000000103');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000151', '', '1742077151936');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000152', '', '1740000000104');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000153', '', '1740000000105');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000154', '', '1740000000106');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000155', '', '1740000000232');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000156', '', '1740402781923');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000157', '', '1740000000107');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000158', '', '1743491905456');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000159', '', '1744273460828');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000160', '', '1740000000201');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000161', '', '1740000000108');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000162', '', '1746433861561');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000163', '', '1740000000109');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000164', '', '1740000000351');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000165', '', '1742450936236');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000166', '', '1745316259642');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000167', '', '1740563134630');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000168', '', '1744172084311');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000169', '', '1740000000110');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000170', '', '1740000000112');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000171', '', '1740000000113');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000172', '', '1740000000114');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000173', '', '1740000000115');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000174', '', '1740000000116');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000175', '', '1740000000117');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000176', '', '1749561384227');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000177', '', '1740000000118');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000178', '', '1742724932709');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000179', '', '1740000000120');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000180', '', '1745578703943');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000181', '', '1740000000086');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000182', '', '1740000000121');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000183', '', '1744533867327');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000184', '', '1740000000119');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000185', '', '1740000000122');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000186', '', '1740000000123');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000187', '', '1740877125153');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000188', '', '1740776611160');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000189', '', '1740000000125');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000190', '', '1740000000126');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000191', '', '1740000000127');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000192', '', '1740000000128');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000193', '', '1747240522171');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000194', '', '1746057671535');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000195', '', '1740000000129');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000196', '', '1740000000130');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000197', '', '1747637985390');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000198', '', '1740000000131');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000199', '', '1740000000133');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000200', '', '1740000000132');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000201', '', '1740000000234');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000202', '', '1742040847149');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000203', '', '1745647333584');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000204', '', '1740000000134');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000205', '', '1740000000135');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000206', '', '1740000000136');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000207', '', '1740000000137');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000208', '', '1740000000138');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000209', '', '1740502279975');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000210', '', '1740000000139');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000211', '', '1740000000140');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000212', '', '1740000000141');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000213', '', '1746390975703');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000214', '', '1740000000143');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000215', '', '1740000000145');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000216', '', '1740000000142');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000217', '', '1740000000144');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000218', '', '1740000000146');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000219', '', '1740000000147');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000220', '', '1740000000148');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000221', '', '1740000000149');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000222', '', '1740000000150');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000223', '', '1740000000151');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000224', '', '1740000000152');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000225', '', '1740000000153');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000226', '', '1740000000154');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000227', '', '1740000000155');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000228', '', '1740000000157');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000229', '', '1740000000158');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000230', '', '1740000000159');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000231', '', '1740000000160');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000232', '', '1740000000161');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000233', '', '1740000000164');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000234', '', '1740000000165');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000235', '', '1740000000166');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000236', '', '1740000000162');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000237', '', '1742094157973');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000238', '', '1746372408798');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000239', '', '1740000000167');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000240', '', '1748918355190');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000241', '', '1740000000168');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000242', '', '1740000000169');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000243', '', '1740000000352');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000244', '', '1740000000353');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000245', '', '1740000000001');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000246', '', '1741306043862');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000247', '', '1740000000240');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000248', '', '1740000000171');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000249', '', '1740000000172');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000250', '', '1740000000173');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000251', '', '1740000000174');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000252', '', '1740000000175');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000253', '', '1748630330161');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000254', '', '1740000000176');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000255', '', '1749055042348');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000256', '', '1742795539112');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000257', '', '1740000000177');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000258', '', '1740000000178');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000259', '', '1743098108309');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000260', '', '1740000000179');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000261', '', '1745909968103');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000262', '', '1740000000180');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000263', '', '1740000000181');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000264', '', '1740000000182');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000265', '', '1740000000183');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000266', '', '1740000000184');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000267', '', '1740000000185');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000268', '', '1740000000186');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000269', '', '1740000000187');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000270', '', '1740000000188');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000271', '', '1740000000189');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000272', '', '1740000000190');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000273', '', '1749278593750');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000274', '', '1740000000191');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000275', '', '1740309152514');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000276', '', '1740000000192');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000277', '', '1740066291745');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000278', '', '1748035640415');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000279', '', '1740000000193');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000280', '', '1746171101430');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000281', '', '1748547399365');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000282', '', '1740762140536');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000283', '', '1740000000194');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000284', '', '1740000000196');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000285', '', '1740000000195');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000286', '', '1740000000197');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000287', '', '1740000000198');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000288', '', '1740000000199');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000289', '', '1740000000200');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000290', '', '1744245237110');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000291', '', '1740000000111');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000292', '', '1740000000202');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000293', '', '1740000000203');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000294', '', '1740000000204');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000295', '', '1740000000205');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000296', '', '1741609179510');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000297', '', '1742000317952');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000298', '', '1740000000206');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000299', '', '1740000000207');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000300', '', '1745350192303');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000301', '', '1740000000208');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000302', '', '1740000000209');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000303', '', '1740000000211');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000304', '', '1749283340761');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000305', '', '1748086405655');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000306', '', '1740000000213');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000307', '', '1740000000214');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000308', '', '1740000000215');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000309', '', '1740000000216');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000310', '', '1740000000217');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000311', '', '1740000000218');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000312', '', '1740000000219');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000313', '', '1740000000220');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000314', '', '1740000000221');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000315', '', '1740000000222');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000316', '', '1743939727772');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000317', '', '1740000000354');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000318', '', '1740000000356');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000319', '', '1740000000355');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000320', '', '1744565198748');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000321', '', '1740000000223');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000322', '', '1740000000224');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000323', '', '1740000000225');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000324', '', '1740000000226');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000325', '', '1740000000357');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000326', '', '1740000000358');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000327', '', '1746869481645');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000328', '', '1740000000227');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000329', '', '1740000000228');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000330', '', '1748059351337');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000331', '', '1740000000212');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000332', '', '1740000000229');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000333', '', '1745428294467');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000334', '', '1740000000230');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000335', '', '1740000000231');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000336', '', '1740000000233');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000337', '', '1744959239398');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000338', '', '1741352961490');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000339', '', '1741924568344');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000340', '', '1740000000235');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000341', '', '1740000000236');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000342', '', '1743660785792');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000343', '', '1740000000237');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000344', '', '1740000000238');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000345', '', '1743894492806');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000346', '', '1749638657902');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000347', '', '1740000000239');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000348', '', '1746501556301');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000349', '', '1740000000242');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000350', '', '1740000000243');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000351', '', '1746832984408');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000352', '', '1748902586784');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000353', '', '1740000000241');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000354', '', '1740000000244');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000355', '', '1740000000245');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000356', '', '1740000000246');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000357', '', '1740000000247');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000358', '', '1740000000248');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000359', '', '1740000000249');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000360', '', '1740000000250');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000361', '', '1740416800843');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000362', '', '1740000000251');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000363', '', '1741459113857');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000364', '', '1743064917973');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000365', '', '1740000000359');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000366', '', '1749335661918');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000367', '', '1740000000360');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000368', '', '1749654977742');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000369', '', '1744207744498');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000370', '', '1740000000252');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000371', '', '1740000000253');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000372', '', '1740000000254');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000373', '', '1740000000255');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000374', '', '1740000000256');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000375', '', '1740000000257');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000376', '', '1740000000163');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000377', '', '1742582086953');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000378', '', '1740227429153');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000379', '', '1740000000258');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000380', '', '1740000000628');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000381', '', '1740000000361');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000382', '', '1740000000259');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000383', '', '1741364701591');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000384', '', '1740000000260');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000385', '', '1742618475894');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000386', '', '1747872066811');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000387', '', '1740000000261');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000388', '', '1740000000263');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000389', '', '1740000000262');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000390', '', '1740000000264');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000391', '', '1740095233491');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000392', '', '1740000000265');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000393', '', '1745722383630');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000394', '', '1741388790218');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000395', '', '1740000000266');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000396', '', '1740000000267');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000397', '', '1748562054300');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000398', '', '1740000000268');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000399', '', '1740000000269');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000400', '', '1744434673625');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000401', '', '1740000000270');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000402', '', '1740000000271');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000403', '', '1749537664691');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000404', '', '1742347431868');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000405', '', '1740000000272');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000406', '', '1740000000273');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000407', '', '1740000000274');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000408', '', '1740000000170');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000409', '', '1740000000275');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000410', '', '1740000000276');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000411', '', '1740000000277');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000412', '', '1740000000278');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000413', '', '1740000000279');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000414', '', '1740000000280');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000415', '', '1740000000281');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000416', '', '1740000000283');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000417', '', '1740000000282');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000418', '', '1745560294756');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000419', '', '1740000000284');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000420', '', '1740000000285');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000421', '', '1740000000286');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000422', '', '1740000000287');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000423', '', '1740000000288');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000424', '', '1740000000362');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000425', '', '1740000000363');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000426', '', '1740000000291');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000427', '', '1747780328546');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000428', '', '1740000000306');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000429', '', '1744633428597');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000430', '', '1745999523419');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000431', '', '1740000000307');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000432', '', '1744531027720');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000433', '', '1747393571173');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000434', '', '1743646511624');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000435', '', '1741409371739');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000436', '', '1740000000292');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000437', '', '1749562451901');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000438', '', '1740000000293');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000439', '', '1740000000294');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000440', '', '1740000000295');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000441', '', '1740000000296');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000442', '', '1741066740061');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000443', '', '1746873473188');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000444', '', '1740000000364');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000445', '', '1740000000297');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000446', '', '1741587700758');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000447', '', '1740000000365');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000448', '', '1746699894538');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000449', '', '1746238888159');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000450', '', '1740000000298');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000451', '', '1740000000299');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000452', '', '1740000000300');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000453', '', '1740000000301');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000454', '', '1740000000302');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000455', '', '1749855518524');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000456', '', '1740000000303');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000457', '', '1740000000029');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000458', '', '1740000000309');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000459', '', '1740000000310');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000460', '', '1740000000311');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000461', '', '1740000000312');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000462', '', '1740000000313');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000463', '', '1745949803948');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000464', '', '1740918982107');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000465', '', '1745413899756');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000466', '', '1746214705322');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000467', '', '1740000000341');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000468', '', '1740000000314');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000469', '', '1742046758161');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000470', '', '1740991595189');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000471', '', '1740654078048');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000472', '', '1740000000315');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000473', '', '1740000000316');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000474', '', '1740000000317');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000475', '', '1740000000318');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000476', '', '1740000000319');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000477', '', '1740000000320');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000478', '', '1740000000321');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000479', '', '1740000000322');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000480', '', '1740000000323');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000481', '', '1740000000324');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000482', '', '1744886671307');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000483', '', '1740000000325');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000484', '', '1746107705093');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000485', '', '1740000000326');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000486', '', '1740000000327');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000487', '', '1740000000329');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000488', '', '1740000000330');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000489', '', '1740000000331');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000490', '', '1740000000332');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000491', '', '1741287976648');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000492', '', '1741857160362');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000493', '', '1740000000333');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000494', '', '1740000000334');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000495', '', '1740000000335');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000496', '', '1740000000336');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000497', '', '1741665371266');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000498', '', '1740000000337');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000499', '', '1740000000338');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000500', '', '1740000000339');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000501', '', '1740000000340');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000502', '', '1740000000304');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000503', '', '1740000000328');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000504', '', '1740000000305');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000505', '', '1740000000342');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000506', '', '1743983870405');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000507', '', '1740000000343');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000508', '', '1740000000344');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000509', '', '1744555937193');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000510', '', '1740000000345');
INSERT INTO b_item_drug_standard_map_item VALUES ('2830000000511', '', '1740000000346');

--วันที่ 23/03/2549

--เพิ่ม field ในตาราง  b_setup_authorization เพื่อเก็บว่าจะใช้การตรวจสอบยาที่มีปฏิกิริยาต่อกันหรือไม่ หรือใช้แพ้ยามาตรฐานหรือไม่
ALTER TABLE b_setup_authorization ADD COLUMN setup_authorization_drug_interaction varchar (255)  default '0';
ALTER TABLE b_setup_authorization ADD COLUMN setup_authorization_drug_standard_allergy varchar (255)  default '0';

--วันที่ 25/03/2549

--สร้างตารางเพื่อเก็บประวัติการสั่งยาที่มีปฏิกิริยาต่อกัน
CREATE TABLE t_order_drug_interaction (
    t_order_drug_interaction_id character varying(255) NOT NULL,
    order_item_id character varying(255),
    order_item_drug_standard_id character varying(255),
    order_item_drug_standard_description character varying(255),
    interaction_item_id character varying(255),
    interaction_item_drug_standard_id character varying(255),
    interaction_item_drug_standard_description character varying(255),
    interaction_blood_presure character varying(255),
    interaction_pregnant character varying(255),
    interaction_type character varying(255),
    interaction_force character varying(255),
    interaction_act character varying(255),
    interaction_repair character varying(255),
    order_drug_interaction_active character varying(255),
    t_visit_id character varying(255)
);
ALTER TABLE ONLY t_order_drug_interaction
    ADD CONSTRAINT t_order_drug_interaction_pkey PRIMARY KEY (t_order_drug_interaction_id);
ALTER INDEX public.t_order_drug_interaction_pkey OWNER TO postgres;

--วันที่ 28/03/2549

--เพิ่ม field ในตาราง t_patient_drug_allergy เพื่อเก็บข้อมูลยามาตรฐาน
ALTER TABLE t_patient_drug_allergy ADD COLUMN b_item_drug_standard_id varchar (255)  default '';
ALTER TABLE t_patient_drug_allergy ADD COLUMN patient_drug_allergy_drug_standard_description varchar (255)  default '';

--วันที่ 30/03/2549
--เพิ่ม field ในตาราง t_order เพื่อเก็บข้อมูลการสั่งยาที่แพ้ และสั่งยาที่มีปฏิกิริยากัน
ALTER TABLE t_order ADD COLUMN order_drug_allergy varchar (255)  default '0';

--วันที่ 05/04/2549
--เพิ่ม field ในตาราง t_visit_vital_sign เพื่อเก็บข้อมูลวันที่และเวลาที่ตรวจ
ALTER TABLE t_visit_vital_sign ADD COLUMN visit_vital_sign_check_date varchar (255)  default '';
ALTER TABLE t_visit_vital_sign ADD COLUMN visit_vital_sign_check_time varchar (255)  default '';
ALTER TABLE t_visit_vital_sign ADD COLUMN visit_vital_sign_staff_modify varchar (255)  default '';
ALTER TABLE t_visit_vital_sign ADD COLUMN visit_vital_sign_modify_date_time varchar (255)  default '';
ALTER TABLE t_visit_vital_sign ADD COLUMN visit_vital_sign_active varchar (255)  default '1';

--วันที่ 07/04/2549
--เพิ่มตาราง b_body_organ เพื่อเก็บข้อมูลอวัยวะของร่างกาย
CREATE TABLE b_body_organ (
    b_body_organ_id character varying(255) NOT NULL,
    body_organ_number character varying(255),
    body_organ_description character varying(255),
    body_organ_active character varying(255)
);
INSERT INTO b_body_organ VALUES ('2870000000001', '01', 'CVS', '1');
INSERT INTO b_body_organ VALUES ('2870000000002', '02', 'Lung', '1');
INSERT INTO b_body_organ VALUES ('2870000000003', '03', 'Chest compression test', '1');
INSERT INTO b_body_organ VALUES ('2870000000004', '04', 'pelvic compression test', '1');
INSERT INTO b_body_organ VALUES ('2870000000005', '05', 'abdomen', '1');
INSERT INTO b_body_organ VALUES ('2870000000006', '06', 'Ext.', '1');
INSERT INTO b_body_organ VALUES ('2870000000007', '07', 'Neuro signs.', '1');
ALTER TABLE ONLY b_body_organ
    ADD CONSTRAINT b_body_organ_pkey PRIMARY KEY (b_body_organ_id);
ALTER INDEX public.b_body_organ_pkey OWNER TO postgres;

--วันที่ 12/04/2549
--เพิ่มข้อมูล Action+Author ของโปรแกรม
   insert into  f_gui_action_authen values ('414','5409','ชนิดรายงานผลแลป','1','เวชระเบียน','0','0');
  insert into  f_gui_action_authen values ('415','5409','ชนิดรายงานผลแลป','2','พยาบาล','0','0');
  insert into  f_gui_action_authen values ('416','5409','ชนิดรายงานผลแลป','3','แพทย์','0','0');
  insert into  f_gui_action_authen values ('417','5409','ชนิดรายงานผลแลป','4','LAB','1','1');
  insert into  f_gui_action_authen values ('418','5409','ชนิดรายงานผลแลป','5','X-RAY','0','0');
  insert into  f_gui_action_authen values ('419','5409','ชนิดรายงานผลแลป','6','เภสัชกร','0','0');
  insert into  f_gui_action_authen values ('420','5409','ชนิดรายงานผลแลป','7','แคชเชียร์','0','0');
  insert into  f_gui_action_authen values ('421','5409','ชนิดรายงานผลแลป','8','เวชสถิติ','0','0');
  insert into  f_gui_action_authen values ('422','5409','ชนิดรายงานผลแลป','9','ADMIN','1','1');
  insert into  f_gui_action_authen values ('423','5409','ชนิดรายงานผลแลป','10','ONE STOP SERVICE','0','0');
  insert into  f_gui_action_authen values ('424','5409','ชนิดรายงานผลแลป','11','งานประกัน','0','0');
  insert into  f_gui_action_authen values ('425','5409','ชนิดรายงานผลแลป','12','งานส่งเสริม','0','0');
  insert into  f_gui_action_authen values ('426','5409','ชนิดรายงานผลแลป','13','งานผู้ป่วยใน','0','0');
  insert into  f_gui_action_authen values ('427','5409','ชนิดรายงานผลแลป','14','ทันตกรรม','0','0');
  insert into  f_gui_action_authen values ('428','5410','อวัยวะร่างกาย','1','เวชระเบียน','0','0');
  insert into  f_gui_action_authen values ('429','5410','อวัยวะร่างกาย','2','พยาบาล','1','1');
  insert into  f_gui_action_authen values ('430','5410','อวัยวะร่างกาย','3','แพทย์','1','1');
  insert into  f_gui_action_authen values ('431','5410','อวัยวะร่างกาย','4','LAB','0','0');
  insert into  f_gui_action_authen values ('432','5410','อวัยวะร่างกาย','5','X-RAY','0','0');
  insert into  f_gui_action_authen values ('433','5410','อวัยวะร่างกาย','6','เภสัชกร','0','0');
  insert into  f_gui_action_authen values ('434','5410','อวัยวะร่างกาย','7','แคชเชียร์','0','0');
  insert into  f_gui_action_authen values ('435','5410','อวัยวะร่างกาย','8','เวชสถิติ','0','0');
  insert into  f_gui_action_authen values ('436','5410','อวัยวะร่างกาย','9','ADMIN','1','1');
  insert into  f_gui_action_authen values ('437','5410','อวัยวะร่างกาย','10','ONE STOP SERVICE','1','1');
  insert into  f_gui_action_authen values ('438','5410','อวัยวะร่างกาย','11','งานประกัน','0','0');
  insert into  f_gui_action_authen values ('439','5410','อวัยวะร่างกาย','12','งานส่งเสริม','1','1');
  insert into  f_gui_action_authen values ('440','5410','อวัยวะร่างกาย','13','งานผู้ป่วยใน','1','1');
  insert into  f_gui_action_authen values ('441','5410','อวัยวะร่างกาย','14','ทันตกรรม','1','1');
  insert into  f_gui_action_authen values ('442','5504','จำนวนยาที่ใช้สำหรับการพิมพ์','1','เวชระเบียน','0','0');
  insert into  f_gui_action_authen values ('443','5504','จำนวนยาที่ใช้สำหรับการพิมพ์','2','พยาบาล','1','1');
  insert into  f_gui_action_authen values ('444','5504','จำนวนยาที่ใช้สำหรับการพิมพ์','3','แพทย์','1','1');
  insert into  f_gui_action_authen values ('445','5504','จำนวนยาที่ใช้สำหรับการพิมพ์','4','LAB','0','0');
  insert into  f_gui_action_authen values ('446','5504','จำนวนยาที่ใช้สำหรับการพิมพ์','5','X-RAY','0','0');
  insert into  f_gui_action_authen values ('447','5504','จำนวนยาที่ใช้สำหรับการพิมพ์','6','เภสัชกร','1','1');
  insert into  f_gui_action_authen values ('448','5504','จำนวนยาที่ใช้สำหรับการพิมพ์','7','แคชเชียร์','0','0');
  insert into  f_gui_action_authen values ('449','5504','จำนวนยาที่ใช้สำหรับการพิมพ์','8','เวชสถิติ','0','0');
  insert into  f_gui_action_authen values ('450','5504','จำนวนยาที่ใช้สำหรับการพิมพ์','9','ADMIN','1','1');
  insert into  f_gui_action_authen values ('451','5504','จำนวนยาที่ใช้สำหรับการพิมพ์','10','ONE STOP SERVICE','1','1');
  insert into  f_gui_action_authen values ('452','5504','จำนวนยาที่ใช้สำหรับการพิมพ์','11','งานประกัน','0','0');
  insert into  f_gui_action_authen values ('453','5504','จำนวนยาที่ใช้สำหรับการพิมพ์','12','งานส่งเสริม','1','1');
  insert into  f_gui_action_authen values ('454','5504','จำนวนยาที่ใช้สำหรับการพิมพ์','13','งานผู้ป่วยใน','1','1');
  insert into  f_gui_action_authen values ('455','5504','จำนวนยาที่ใช้สำหรับการพิมพ์','14','ทันตกรรม','1','1');
  insert into  f_gui_action_authen values ('456','5505','ยามาตรฐาน','1','เวชระเบียน','0','0');
  insert into  f_gui_action_authen values ('457','5505','ยามาตรฐาน','2','พยาบาล','1','1');
  insert into  f_gui_action_authen values ('458','5505','ยามาตรฐาน','3','แพทย์','1','1');
  insert into  f_gui_action_authen values ('459','5505','ยามาตรฐาน','4','LAB','0','0');
  insert into  f_gui_action_authen values ('460','5505','ยามาตรฐาน','5','X-RAY','0','0');
  insert into  f_gui_action_authen values ('461','5505','ยามาตรฐาน','6','เภสัชกร','1','1');
  insert into  f_gui_action_authen values ('462','5505','ยามาตรฐาน','7','แคชเชียร์','0','0');
  insert into  f_gui_action_authen values ('463','5505','ยามาตรฐาน','8','เวชสถิติ','0','0');
  insert into  f_gui_action_authen values ('464','5505','ยามาตรฐาน','9','ADMIN','1','1');
  insert into  f_gui_action_authen values ('465','5505','ยามาตรฐาน','10','ONE STOP SERVICE','1','1');
  insert into  f_gui_action_authen values ('466','5505','ยามาตรฐาน','11','งานประกัน','0','0');
  insert into  f_gui_action_authen values ('467','5505','ยามาตรฐาน','12','งานส่งเสริม','1','1');
  insert into  f_gui_action_authen values ('468','5505','ยามาตรฐาน','13','งานผู้ป่วยใน','1','1');
  insert into  f_gui_action_authen values ('469','5505','ยามาตรฐาน','14','ทันตกรรม','1','1');
  insert into  f_gui_action_authen values ('470','5506','จัดกลุ่มยามาตรฐาน','1','เวชระเบียน','0','0');
  insert into  f_gui_action_authen values ('471','5506','จัดกลุ่มยามาตรฐาน','2','พยาบาล','1','1');
  insert into  f_gui_action_authen values ('472','5506','จัดกลุ่มยามาตรฐาน','3','แพทย์','1','1');
  insert into  f_gui_action_authen values ('473','5506','จัดกลุ่มยามาตรฐาน','4','LAB','0','0');
  insert into  f_gui_action_authen values ('474','5506','จัดกลุ่มยามาตรฐาน','5','X-RAY','0','0');
  insert into  f_gui_action_authen values ('475','5506','จัดกลุ่มยามาตรฐาน','6','เภสัชกร','1','1');
  insert into  f_gui_action_authen values ('476','5506','จัดกลุ่มยามาตรฐาน','7','แคชเชียร์','0','0');
  insert into  f_gui_action_authen values ('477','5506','จัดกลุ่มยามาตรฐาน','8','เวชสถิติ','0','0');
  insert into  f_gui_action_authen values ('478','5506','จัดกลุ่มยามาตรฐาน','9','ADMIN','1','1');
  insert into  f_gui_action_authen values ('479','5506','จัดกลุ่มยามาตรฐาน','10','ONE STOP SERVICE','1','1');
  insert into  f_gui_action_authen values ('480','5506','จัดกลุ่มยามาตรฐาน','11','งานประกัน','1','1');
  insert into  f_gui_action_authen values ('481','5506','จัดกลุ่มยามาตรฐาน','12','งานส่งเสริม','1','1');
  insert into  f_gui_action_authen values ('482','5506','จัดกลุ่มยามาตรฐาน','13','งานผู้ป่วยใน','1','1');
  insert into  f_gui_action_authen values ('483','5506','จัดกลุ่มยามาตรฐาน','14','ทันตกรรม','1','1');
  insert into  f_gui_action_authen values ('484','5507','จับคู่ยาที่มีปฏิกิริยาต่อกัน','1','เวชระเบียน','0','0');
  insert into  f_gui_action_authen values ('485','5507','จับคู่ยาที่มีปฏิกิริยาต่อกัน','2','พยาบาล','1','1');
  insert into  f_gui_action_authen values ('486','5507','จับคู่ยาที่มีปฏิกิริยาต่อกัน','3','แพทย์','1','1');
  insert into  f_gui_action_authen values ('487','5507','จับคู่ยาที่มีปฏิกิริยาต่อกัน','4','LAB','0','0');
  insert into  f_gui_action_authen values ('488','5507','จับคู่ยาที่มีปฏิกิริยาต่อกัน','5','X-RAY','0','0');
  insert into  f_gui_action_authen values ('489','5507','จับคู่ยาที่มีปฏิกิริยาต่อกัน','6','เภสัชกร','1','1');
  insert into  f_gui_action_authen values ('490','5507','จับคู่ยาที่มีปฏิกิริยาต่อกัน','7','แคชเชียร์','0','0');
  insert into  f_gui_action_authen values ('491','5507','จับคู่ยาที่มีปฏิกิริยาต่อกัน','8','เวชสถิติ','0','0');
  insert into  f_gui_action_authen values ('492','5507','จับคู่ยาที่มีปฏิกิริยาต่อกัน','9','ADMIN','1','1');
  insert into  f_gui_action_authen values ('493','5507','จับคู่ยาที่มีปฏิกิริยาต่อกัน','10','ONE STOP SERVICE','1','1');
  insert into  f_gui_action_authen values ('494','5507','จับคู่ยาที่มีปฏิกิริยาต่อกัน','11','งานประกัน','1','1');
  insert into  f_gui_action_authen values ('495','5507','จับคู่ยาที่มีปฏิกิริยาต่อกัน','12','งานส่งเสริม','1','1');
  insert into  f_gui_action_authen values ('496','5507','จับคู่ยาที่มีปฏิกิริยาต่อกัน','13','งานผู้ป่วยใน','1','1');
  insert into  f_gui_action_authen values ('497','5507','จับคู่ยาที่มีปฏิกิริยาต่อกัน','14','ทันตกรรม','1','1');

--วันที่ 18/04/2549
--เพิ่ม field ในตาราง b_group_icd10 เพื่อเก็บข้อมูลโรคทั่วไป โดย Default เป็น N/A
ALTER TABLE b_group_icd10 ADD COLUMN b_health_disease_id varchar (255)  default '7830000000000';

-----------------10/04/2549------------------------------------------------------------------------------
--เพิ่ม Field แสดงมาตรฐานของภาวะโภชนาการของแต่ละตัว
alter table f_visit_nutrition_level add  visit_nutrition_level_standard character varying(255) default '0';
--------------------------------------------------------------------------------------------------------
--วันที่ 26/04/2549
--เพิ่ม record ในตาราง f_visit_nutrition_level เพื่อเก็บข้อมูลมาตรฐานแบบใหม่
INSERT INTO f_visit_nutrition_level VALUES ('08', 'มากเกินเกณฑ์', '', '', '1');
INSERT INTO f_visit_nutrition_level VALUES ('09', 'ค่อนข้างมาก', '', '', '1');
INSERT INTO f_visit_nutrition_level VALUES ('10', 'ตามเกณฑ์', '', '', '1');
INSERT INTO f_visit_nutrition_level VALUES ('11', 'ค่อนข้างน้อย', '', '', '1');
INSERT INTO f_visit_nutrition_level VALUES ('12', 'น้อยกว่าเกณฑ์', '', '', '1');

--เพิ่มตาราง Map ระดับโภชนาการแบบเก่ากับแบบใหม่
CREATE TABLE b_nutrition_map (
    b_nutrition_map_id character varying(255) NOT NULL,
    f_visit_nutrition_level_new character varying(255),
    f_visit_nutrition_level_old character varying(255)
);
INSERT INTO b_nutrition_map VALUES ('2880000000001', '11', '1');
INSERT INTO b_nutrition_map VALUES ('2880000000002', '11', '2');
INSERT INTO b_nutrition_map VALUES ('2880000000003', '12', '3');
INSERT INTO b_nutrition_map VALUES ('2880000000004', '12', '4');
INSERT INTO b_nutrition_map VALUES ('2880000000005', '10', 'N');
INSERT INTO b_nutrition_map VALUES ('2880000000006', '09', '5');
INSERT INTO b_nutrition_map VALUES ('2880000000007', '09', '6');
INSERT INTO b_nutrition_map VALUES ('2880000000008', '08', '7');
ALTER TABLE ONLY b_nutrition_map
    ADD CONSTRAINT b_nutrition_map_pkey PRIMARY KEY (b_nutrition_map_id);
ALTER INDEX public.b_nutrition_map_pkey OWNER TO postgres;

--เพิ่มข้อมูล Action+Author ของโปรแกรม
insert into  f_gui_action_authen values ('498','5411','จับคู่ระดับโภชนาการ','1','เวชระเบียน','0','0');
insert into  f_gui_action_authen values ('499','5411','จับคู่ระดับโภชนาการ','2','พยาบาล','1','1');
insert into  f_gui_action_authen values ('500','5411','จับคู่ระดับโภชนาการ','3','แพทย์','1','1');
insert into  f_gui_action_authen values ('501','5411','จับคู่ระดับโภชนาการ','4','LAB','0','0');
insert into  f_gui_action_authen values ('502','5411','จับคู่ระดับโภชนาการ','5','X-RAY','0','0');
insert into  f_gui_action_authen values ('503','5411','จับคู่ระดับโภชนาการ','6','เภสัชกร','0','0');
insert into  f_gui_action_authen values ('504','5411','จับคู่ระดับโภชนาการ','7','แคชเชียร์','0','0');
insert into  f_gui_action_authen values ('505','5411','จับคู่ระดับโภชนาการ','8','เวชสถิติ','0','0');
insert into  f_gui_action_authen values ('506','5411','จับคู่ระดับโภชนาการ','9','ADMIN','1','1');
insert into  f_gui_action_authen values ('507','5411','จับคู่ระดับโภชนาการ','10','ONE STOP SERVICE','1','1');
insert into  f_gui_action_authen values ('508','5411','จับคู่ระดับโภชนาการ','11','งานประกัน','0','0');
insert into  f_gui_action_authen values ('509','5411','จับคู่ระดับโภชนาการ','12','งานส่งเสริม','1','1');
insert into  f_gui_action_authen values ('510','5411','จับคู่ระดับโภชนาการ','13','งานผู้ป่วยใน','1','1');
insert into  f_gui_action_authen values ('511','5411','จับคู่ระดับโภชนาการ','14','ทันตกรรม','0','0');


--วันที่ 14/06/2549
--เพิ่มตารางเก็บโรค NCD
CREATE TABLE b_ncd_group (
    b_ncd_group_id character varying(255) NOT NULL,
    ncd_group_number character varying(255),
    ncd_group_description character varying(255),
    ncd_group_pattern character varying(255),
    ncd_group_value character varying(255),
    b_group_chronic_id character varying(255)
);
ALTER TABLE ONLY b_ncd_group
    ADD CONSTRAINT b_ncd_group_pkey PRIMARY KEY (b_ncd_group_id);
ALTER INDEX public.b_ncd_group_pkey OWNER TO postgres;

--เพิ่ม field ในตาราง b_item_lab_result เพื่อเก็บข้อมูลโรค NCD
ALTER TABLE b_item_lab_result ADD COLUMN item_ncd_fbs varchar (255)  default '0';
ALTER TABLE b_item_lab_result ADD COLUMN item_ncd_hct varchar (255)  default '0';

--เพิ่ม ตาราง t_patient_ncd เพื่อเก็บข้อมูลโรค NCD
CREATE TABLE t_patient_ncd (
    t_patient_ncd_id character varying(255) NOT NULL,
    t_patient_id character varying(255),
    b_ncd_group_id character varying(255),
    patient_ncd_number character varying(255),
    patient_ncd_staff_record character varying(255),
    patient_ncd_record_date_time character varying(255),
    patient_ncd_staff_modify character varying(255),
    patient_ncd_modify_date_time character varying(255)
);
ALTER TABLE ONLY t_patient_ncd
    ADD CONSTRAINT t_patient_ncd_pkey PRIMARY KEY (t_patient_ncd_id);
ALTER INDEX public.t_patient_ncd_pkey OWNER TO postgres;

--เพิ่ม field ในตาราง t_visit เพื่อเก็บข้อมูลเกี่ยวกับโรค NCD
ALTER TABLE t_visit ADD COLUMN visit_ncd varchar (255)  default '0';
ALTER TABLE t_visit ADD COLUMN b_ncd_group_id varchar (255)  default '';

-----------ซัม modify----------------------------------------------------------------------------------------------------------

--วันที่ 23/02/2549

--เพิ่ม Field เก็บจำนวนฟิล์มเสีย ในตาราง t_result_xray_size
alter table t_result_xray_size add result_xray_size_damaging_xray_film_amount varchar(255);

--เพิ่มตารางเก็บข้อมูลการยืมคืนฟิล์ม Xray
CREATE TABLE t_borrow_film_xray
(
   t_borrow_film_xray_id varchar(255), 
   patient_hn varchar(255),
   patient_xn varchar(255),
   borrower_prefix varchar(255),
   borrower_name varchar(255),
   borrower_lastname varchar(255),
   borrow_film_date varchar(255),
   amount_date varchar(255),
   return_film_date varchar(255),
   borrow_status varchar(255) DEFAULT '0'::character varying,
   permissibly_borrow varchar(255),
   borrow_cause varchar(255),
   borrow_to varchar(255),
   borrow_to_other varchar(255) default '',
   date_serv varchar(255),
   borrow_staff_record varchar(255),
   borrow_record_date_time varchar(255), 
   borrow_staff_update varchar(255),
   borrow_update_date_time varchar(255),
   borrow_staff_cancel varchar(255),
   borrow_cancel_date_time varchar(255),
   borrow_active varchar(255) default '1',
   CONSTRAINT t_borrow_film_xray_pkey PRIMARY KEY (t_borrow_film_xray_id)
) ;

--เพิ่มตารางเก็บข้อมูลการยืมคืน OpdCard
CREATE TABLE t_borrow_opdcard
(
   t_borrow_opdcard_id varchar(255), 
   patient_hn varchar(255),
   borrower_opd_prefix varchar(255),
   borrower_opd_name varchar(255),
   borrower_opd_lastname varchar(255),
   borrow_opd_date varchar(255),
   amount_date_opd varchar(255),
   return_opd_date varchar(255),
   borrow_opd_status varchar(255) DEFAULT '0'::character varying,
   permissibly_borrow_opd varchar(255),
   borrow_opd_cause varchar(255),
   borrow_opd_to varchar(255),
   borrow_opd_to_other varchar(255) default '',
   date_serv_opd varchar(255),
   borrow_opdcard_staff_record varchar(255),
   borrow_opdcard_record_date_time varchar(255), 
   borrow_opdcard_staff_update varchar(255),
   borrow_opdcard_update_date_time varchar(255),
   borrow_opdcard_staff_cancel varchar(255),
   borrow_opdcard_cancel_date_time varchar(255),
   borrow_opdcard_active varchar(255) default '1',
   CONSTRAINT t_borrow_opdcard_pkey PRIMARY KEY (t_borrow_opdcard_id)
) ;
 
-------------sumo modify-------------------------------------
-----------27/2/2549----------------------------------------
--เพิ่ม Field เก็บสถานที่ส่งผู้ป่วยที่อยู่นอกเหนือจากที่มีใน b_visit_office ในตาราง t_lab_refer_out_order_form
alter table t_lab_refer_out_order_form add lab_refer_out_form_other_place varchar(255);  

-------------sumo modify-------------------------------------
-----------6/3/2549----------------------------------------
--เพิ่ม Field เก็บสาเหตุในการยกเลิกผลแลปในตาราง t_order
alter table t_order add order_cause_cancel_resultlab varchar(255);  

--เพิ่มข้อมูล Action ของโปรแกรม
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('0412', 'การยืมคืนฟิล์ม Xray', '');
INSERT INTO f_gui_action (f_gui_action_id,gui_action_name,gui_action_note) VALUES ('0413', 'การยืมคืนฟิล์ม OpdCard','' );

--เพิ่มข้อมูล Action+Author ของโปรแกรม
 insert into  f_gui_action_authen values ('386','0412','การยืมคืนฟิล์ม Xray','1','เวชระเบียน','1','1');
 insert into  f_gui_action_authen values ('387','0412','การยืมคืนฟิล์ม Xray','2','พยาบาล','1','1');
 insert into  f_gui_action_authen values ('388','0412','การยืมคืนฟิล์ม Xray','3','แพทย์','1','1');
 insert into  f_gui_action_authen values ('389','0412','การยืมคืนฟิล์ม Xray','4','LAB','0','0');
 insert into  f_gui_action_authen values ('390','0412','การยืมคืนฟิล์ม Xray','5','X-RAY','1','1');
 insert into  f_gui_action_authen values ('391','0412','การยืมคืนฟิล์ม Xray','6','เภสัชกร','0','0');
 insert into  f_gui_action_authen values ('392','0412','การยืมคืนฟิล์ม Xray','7','แคชเชียร์','0','0');
 insert into  f_gui_action_authen values ('393','0412','การยืมคืนฟิล์ม Xray','8','เวชสถิติ','0','0');
 insert into  f_gui_action_authen values ('394','0412','การยืมคืนฟิล์ม Xray','9','ADMIN','1','1');
 insert into  f_gui_action_authen values ('395','0412','การยืมคืนฟิล์ม Xray','10','ONE STOP SERVICE','1','1');
 insert into  f_gui_action_authen values ('396','0412','การยืมคืนฟิล์ม Xray','11','งานประกัน','1','1');
 insert into  f_gui_action_authen values ('397','0412','การยืมคืนฟิล์ม Xray','12','งานส่งเสริม','0','0');
 insert into  f_gui_action_authen values ('398','0412','การยืมคืนฟิล์ม Xray','13','งานผู้ป่วยใน','0','0');
 insert into  f_gui_action_authen values ('412','0412','การยืมคืนฟิล์ม Xray','14','ทันตกรรม','0','0');
 insert into  f_gui_action_authen values ('399','0413','การยืมคืนฟิล์ม OpdCard','1','เวชระเบียน','1','1');
 insert into  f_gui_action_authen values ('400','0413','การยืมคืนฟิล์ม OpdCard','2','พยาบาล','1','1');
 insert into  f_gui_action_authen values ('401','0413','การยืมคืนฟิล์ม OpdCard','3','แพทย์','1','1');
 insert into  f_gui_action_authen values ('402','0413','การยืมคืนฟิล์ม OpdCard','4','LAB','1','1');
 insert into  f_gui_action_authen values ('403','0413','การยืมคืนฟิล์ม OpdCard','5','X-RAY','1','1');
 insert into  f_gui_action_authen values ('404','0413','การยืมคืนฟิล์ม OpdCard','6','เภสัชกร','1','1');
 insert into  f_gui_action_authen values ('405','0413','การยืมคืนฟิล์ม OpdCard','7','แคชเชียร์','1','1');
 insert into  f_gui_action_authen values ('406','0413','การยืมคืนฟิล์ม OpdCard','8','เวชสถิติ','1','1');
 insert into  f_gui_action_authen values ('407','0413','การยืมคืนฟิล์ม OpdCard','9','ADMIN','1','1');
 insert into  f_gui_action_authen values ('408','0413','การยืมคืนฟิล์ม OpdCard','10','ONE STOP SERVICE','1','1');
 insert into  f_gui_action_authen values ('409','0413','การยืมคืนฟิล์ม OpdCard','11','งานประกัน','1','1');
 insert into  f_gui_action_authen values ('410','0413','การยืมคืนฟิล์ม OpdCard','12','งานส่งเสริม','1','1');
 insert into  f_gui_action_authen values ('411','0413','การยืมคืนฟิล์ม OpdCard','13','งานผู้ป่วยใน','1','1');
 insert into  f_gui_action_authen values ('413','0413','การยืมคืนฟิล์ม OpdCard','14','ทันตกรรม','0','0');

--เพิ่ม Field เก็บรหัส Family_id ในตาราง Patient ของ PCU 
alter table t_patient add  t_health_family_id character varying(255) default '';
---henbe       อัพเดต Family_id ใน ตาราง Patient
UPDATE t_patient SET t_health_family_id = t_health_family.t_health_family_id FROM t_health_family WHERE t_health_family.t_patient_id <>'' AND   t_health_family.t_patient_id = t_patient.t_patient_id ;

-------------sumo modify-------------------------------------
-----------13/3/2549----------------------------------------
--เพิ่ม Field เก็บสถานะของการ refer ในตาราง t_visit_refer_in_out
alter table t_visit_refer_in_out add visit_refer_in_out_status character varying(255) default '0'; 

--เพิ่มตารางข้อมูลพื้นฐานของสาเหตุการ Refer
CREATE TABLE f_refer_cause
(
   f_refer_cause_id varchar(255), 
   refer_cause_description varchar(255),
   CONSTRAINT f_refer_cause_pkey PRIMARY KEY (f_refer_cause_id)
) ;
--ข้อมูลพื้นฐานของสาเหตุการ Refer
insert into f_refer_cause values('0','ไม่ระบุ');
insert into f_refer_cause values('1','วินิจฉัย ชันสูตรและส่งกลับ');
insert into f_refer_cause values('2','เพื่อรักษา เนื่องจากขาดบุคลากร');
insert into f_refer_cause values('3','เพื่อรักษา เนื่องจากขาดเครื่องมือ,สถานที่');
insert into f_refer_cause values('4','เพื่อรักษา เนื่องจากขาดบุคลากรและเครื่องมือ');
insert into f_refer_cause values('5','เพื่อรักษา ความสามารถไม่พอด้านวิชาการ');
insert into f_refer_cause values('6','เพื่อรักษา ความสามารถพอ แต่ไม่จำเป็น');
insert into f_refer_cause values('7','เพื่อรักษา ผู้ป่วย/ญาติต้องการ');
insert into f_refer_cause values('8','เพื่อรักษา ความสามารถพอ แต่ต้องการใช้สิทธิ');

--เพิ่ม Field เก็บสถานะของการ refer ในตาราง t_visit
alter table t_visit add f_refer_cause_id character varying(255) default '0'; 

-------------sumo modify-------------------------------------
-----------21/3/2549----------------------------------------
--แก้ไขชื่อ Field ที่เก็บแพทย์สั่งยกเลิกยาต่อเนื่องในตาราง t_order_continue
alter table t_order_continue rename column order_continue_staff_off to order_continue_staff_doctor_set_off;

--เพิ่ม Field เก็บเจ้าหน้าที่เป็นคนกดยกเลิกสั่งยาต่อเนื่องในตาราง t_order_continue
alter table t_order_continue add order_continue_staff_off character varying(255) default ''; 

-------------sumo modify-------------------------------------
-----------25/3/2549----------------------------------------
--เพิ่ม Field เก็บรหัส Family_id ในตาราง Death
alter table t_death add  t_health_family_id character varying(255) default '';

---henbe       อัพเดต Family_id ใน ตาราง Patient
UPDATE t_death SET t_health_family_id = t_health_family.t_health_family_id FROM t_health_family WHERE t_health_family.t_patient_id <>'' AND   t_health_family.t_patient_id = t_death.t_patient_id ;

--เพิ่ม Field เก็บรหัส Family_id ในตาราง Patient_Payment
alter table t_patient_payment add  t_health_family_id character varying(255) default '';

---henbe       อัพเดต Family_id ใน ตาราง Patient
UPDATE t_patient_payment SET t_health_family_id = t_health_family.t_health_family_id FROM t_health_family WHERE t_health_family.t_patient_id <>'' AND   t_health_family.t_patient_id = t_patient_payment.t_patient_id ;

-------------sumo modify-------------------------------------
-----------27/3/2549----------------------------------------
--เพิ่ม Field เก็บข้อมูลการนัดที่ Inactive และข้อมูลการบันทึกแก้ไขการนัดในตาราง t_appointment
alter table t_patient_appointment add patient_appointment_staff_record varchar(255) default '';
alter table t_patient_appointment add patient_appointment_record_date_time varchar(255) default '';
alter table t_patient_appointment add patient_appointment_staff_update varchar(255) default '';
alter table t_patient_appointment add patient_appointment_update_date_time varchar(255) default '';
alter table t_patient_appointment add patient_appointment_staff_cancel varchar(255) default '';
alter table t_patient_appointment add patient_appointment_cancel_date_time varchar(255) default '';
alter table t_patient_appointment add patient_appointment_active character varying(255) default '1';

-------------sumo modify-------------------------------------
-----------29/3/2549----------------------------------------
--เพิ่มตารางข้อมูล Item ที่เป็นค่าบริการ
CREATE TABLE b_item_service
(
   b_item_service_id varchar(255), 
    b_item_id varchar(255),
   item_service_description varchar(255),
   icd9_number varchar(255),
   CONSTRAINT b_item_service_pkey PRIMARY KEY (b_item_service_id)
) ;

-------------sumo modify-------------------------------------
-----------30/3/2549----------------------------------------
--เพิ่ม Field เก็บรหัส Family_id ในตาราง Patient_Payment
alter table t_surveil add  t_health_family_id character varying(255) default '';
---henbe       อัพเดต Family_id ใน ตาราง Patient
UPDATE t_surveil SET t_health_family_id = t_health_family.t_health_family_id FROM t_health_family WHERE t_health_family.t_patient_id <>'' AND   t_health_family.t_patient_id = t_surveil.t_patient_id ;

----เพิ่ม fileld ในตาราง Chronic สำหรับ PCU
alter table t_chronic add  chronic_site_treat character varying(255) default '';

alter table t_chronic add  t_health_family_id character varying(255) default '';
---henbe       อัพเดต Family_id ใน ตาราง Patient
UPDATE t_chronic SET t_health_family_id = t_health_family.t_health_family_id FROM t_health_family WHERE t_health_family.t_patient_id <>'' AND   t_health_family.t_patient_id = t_chronic.t_patient_id ;

alter table t_chronic add  modify_date_time character varying(255) default '';
alter table t_chronic add  cancel_date_time character varying(255) default '';
alter table t_chronic add  staff_record character varying(255) default '';
alter table t_chronic add  staff_modify character varying(255) default '';
alter table t_chronic add  staff_cancel character varying(255) default '';
alter table t_chronic add  chronic_active character varying(255) default '';
alter table t_chronic add  chronic_survey_date character varying(255) default '';

---tong
--t_order
alter table t_order add  order_staff_report  varchar(255) default '';
alter table t_order add  order_report_date_time  varchar(255) default '';
-- f_patient_prefix
alter table  f_patient_prefix add active   varchar(255) default '1';
alter table  t_order_continue  add order_continue_staff_doctor  varchar(255) default '';
--แก้ไขส่วนของ visit_map_dx เพื่อให้เก็บข้อมูลได้สมบูรณ์ และหาได้ว่าเป็นของใคร

alter table t_visit_diag_map add  t_visit_diag_map_id varchar(255) default '';
alter table t_visit_diag_map add  visit_diag_map_dx varchar(255) default '';
alter table t_visit_diag_map add  visit_diag_map_icd varchar(255) default '';
alter table t_visit_diag_map add  visit_diag_map_staff varchar(255) default '';
alter table t_visit_diag_map add  visit_diag_map_date_time varchar(255) default '';
alter table t_visit_diag_map add  t_patient_id varchar(255) default '';
alter table t_visit_diag_map add  visit_diag_map_active varchar(255) default '1';
alter table t_visit_diag_map add  visit_diag_map_staff_cancel varchar(255) default '';
ALTER TABLE t_visit_diag_map ADD COLUMN visit_diag_map_cancel_date_time varchar(255) DEFAULT '';
alter table t_visit_diag_map drop constraint t_visit_diag_map_pkey cascade;

update t_visit_diag_map set t_visit_diag_map_id = t_visit_id;
update t_visit_diag_map set t_visit_id = substring(t_visit_id,0,length(t_visit_id) -length(b_template_dx_id) +1);

alter table only t_visit_diag_map  add constraint t_visit_diag_map_id PRIMARY KEY (t_visit_diag_map_id);

--เพิ่มส่วนของการใช้ map dx
ALTER TABLE b_setup_authorization ADD COLUMN setup_authorization_set_icd10_form_map_dx varchar(255) DEFAULT '0';
--เพิ่ม ส่วนของ visit_payment เก็บผู้กระทำ
alter table t_visit_payment add visit_payment_staff_record varchar(255) default '';
alter table t_visit_payment add visit_payment_record_date_time varchar(255) default '';
alter table t_visit_payment add visit_payment_staff_update varchar(255) default '';
alter table t_visit_payment add visit_payment_update_date_time varchar(255) default '';
alter table t_visit_payment add visit_payment_staff_cancel varchar(255) default '';
alter table t_visit_payment add visit_payment_cancel_date_time varchar(255) default '';
alter table t_visit_payment add visit_payment_active varchar(255) default '1';

--เพิ่ม ตารางที่เก็บเลข XN
CREATE TABLE t_patient_xn (
    t_patient_xn_id character varying(255) NOT NULL,
    patient_xray_number character varying(255) DEFAULT '',
    patient_xn_year character varying(255) DEFAULT '',
    t_patient_id character varying(255) DEFAULT '',
    patient_xn_active character varying(255) DEFAULT '1'
);

ALTER TABLE ONLY t_patient_xn
    ADD CONSTRAINT t_patient_xn_id PRIMARY KEY (t_patient_xn_id);

CREATE INDEX t_patient_xn_patient_id ON t_patient_xn USING btree (t_patient_id);
CREATE INDEX patient_xray_number ON t_patient_xn USING btree (patient_xray_number);
CREATE INDEX patient_xn_year ON t_patient_xn USING btree (patient_xn_year);
CREATE INDEX patient_xn_patient_id ON t_patient_xn USING btree (t_patient_id);
--เพิ่มข้อมูลลงตาราง f_gui
INSERT INTO f_gui_action VALUES ('0414', 'แก้ไขข้อมูลการตัวช่วยDx', '');
--เพิ่มข้อมูลลงตาราง f_gui_action_authen
INSERT INTO f_gui_action_authen VALUES ('000000005411111101', '0414', 'แก้ไขข้อมูลการตัวช่วยDx', '1', 'เวชระเบียน', '0', '0', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005411111102', '0414', 'แก้ไขข้อมูลการตัวช่วยDx', '2', 'พยาบาล', '1', '1', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005411111103', '0414', 'แก้ไขข้อมูลการตัวช่วยDx', '3', 'แพทย์', '1', '1', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005411111104', '0414', 'แก้ไขข้อมูลการตัวช่วยDx', '4', 'LAB', '0', '0', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005411111105', '0414', 'แก้ไขข้อมูลการตัวช่วยDx', '5', 'X-RAY', '0', '0', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005411111106', '0414', 'แก้ไขข้อมูลการตัวช่วยDx', '6', 'เภสัชกร', '0', '0', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005411111107', '0414', 'แก้ไขข้อมูลการตัวช่วยDx', '7', 'แคชเชียร์', '0', '0', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005411111108', '0414', 'แก้ไขข้อมูลการตัวช่วยDx', '8', 'เวชสถิติ', '0', '0', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005411111109', '0414', 'แก้ไขข้อมูลการตัวช่วยDx', '9', 'ADMIN', '1', '1', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005411111110', '0414', 'แก้ไขข้อมูลการตัวช่วยDx', '10', 'ONE STOP SERVICE', '1', '1', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005411111111', '0414', 'แก้ไขข้อมูลการตัวช่วยDx', '11', 'งานประกัน', '0', '0', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005411111112', '0414', 'แก้ไขข้อมูลการตัวช่วยDx', '12', 'งานส่งเสริม', '1', '1', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005411111113', '0414', 'แก้ไขข้อมูลการตัวช่วยDx', '13', 'งานผู้ป่วยใน', '1', '1', ' ');

--เพิ่มข้อมูลการเก็บผู้บันทึึก ในตาราง t_diag_icd10
alter table t_diag_icd10 add diag_icd10_staff_record varchar(255) default '';
alter table t_diag_icd10 add diag_icd10_record_date_time varchar(255) default '';
alter table t_diag_icd10 add diag_icd10_staff_update varchar(255) default '';
alter table t_diag_icd10 add diag_icd10_update_date_time varchar(255) default '';
alter table t_diag_icd10 add diag_icd10_staff_cancel varchar(255) default '';
alter table t_diag_icd10 add diag_icd10_cancel_date_time varchar(255) default '';
alter table t_diag_icd10 add diag_icd10_active varchar(255) default '1';
--เพิ่มข้อมูลการเก็บผู้บันทึึก ในตาราง t_diag_icd9
alter table t_diag_icd9 add diag_icd9_staff_record varchar(255) default '';
alter table t_diag_icd9 add diag_icd9_record_date_time varchar(255) default '';
alter table t_diag_icd9 add diag_icd9_staff_update varchar(255) default '';
alter table t_diag_icd9 add diag_icd9_update_date_time varchar(255) default '';
alter table t_diag_icd9 add diag_icd9_staff_cancel varchar(255) default '';
alter table t_diag_icd9 add diag_icd9_cancel_date_time varchar(255) default '';
alter table t_diag_icd9 add diag_icd9_active varchar(255) default '1';
--เพิ่มขอมูลการเก็บประวัติเลข XN ใน ตาราง t_result_xray
alter table t_result_xray add t_patient_xn_id varchar(255) default '';
CREATE INDEX t_result_xray_patient_xn_id ON t_result_xray USING btree (t_patient_xn_id);


----henbe เพิ่มหน้าจอแลบให้มีความสมบูรณในการบันทึกข้อมูลและการแสดงผล
----17/03/2549
alter table  t_result_lab add     f_lab_result_type_id  varchar(255) default '';
alter table  t_result_lab add     result_lab_max    varchar(255) default '';
alter table  t_result_lab add     result_lab_min     varchar(255) default '';
alter table  t_result_lab add     b_lab_result_group_id   varchar(255) default '';
alter table  t_result_lab add     result_lab_index   varchar(255) default '';
-- ที่อยู่ต่างประเทศ
alter table  t_patient add     patient_other_country_address  varchar(255) default '0';
alter table  t_patient add     patient_is_other_country   varchar(255) default '';

--เพิ่ม Column ในตาราง b_item_lab_result','
--เพิ่ม Column กลุ่มผลแลบที่เป็นไปได้ ในตาราง t_result_lab
alter table b_item_lab_result add item_lab_result_default character varying(255) default '';
alter table t_result_lab add b_item_lab_result_id character varying(255) default '';

---เพิ่ม Index  หนี่ง 29-03-49
CREATE INDEX result_lab_result_item_id
  ON t_result_lab
  USING btree
  (b_item_id);

CREATE INDEX result_lab_result_visit_id
  ON t_result_lab
  USING btree
  (t_visit_id);

  
CREATE INDEX patient_payment_family_id
  ON t_patient_payment
  USING btree
  (t_health_family_id);

---------------------------------------------------------------------------------------------------------

------Henbe การปรับแก้ในเวอร์ชัน 3.07 สำหรับ น่าน

CREATE TABLE b_option_detail (
    b_option_detail_id character varying(255) NOT NULL,
    option_detail_name character varying(255),
    option_detail_note character varying(255)
);

ALTER TABLE ONLY b_option_detail
    ADD CONSTRAINT b_option_detail_pkey PRIMARY KEY (b_option_detail_id);
    
CREATE INDEX option_detail_name ON b_option_detail USING btree (option_detail_name);

insert into b_option_detail values ('admit', (select 	setup_authorization_admit	  from b_setup_authorization) ,'');
insert into b_option_detail values ('autounlock', (select 	setup_authorization_autounlock 	  from b_setup_authorization) ,'');
insert into b_option_detail values ('cancel_receipt', (select 	setup_authorization_cancel_receipt 	  from b_setup_authorization) ,'');
insert into b_option_detail values ('cancel_receipt_password', (select 	setup_authorization_cancel_receipt_password	  from b_setup_authorization)  ,'');
insert into b_option_detail values ('commit', (select 	setup_authorization_commit	  from b_setup_authorization) ,'');
insert into b_option_detail values ('del_patient', (select 	setup_authorization_del_patient	  from b_setup_authorization) ,'');
insert into b_option_detail values ('discharge', (select 	setup_authorization_discharge	  from b_setup_authorization) ,'');
insert into b_option_detail values ('discontinue', (select 	setup_authorization_discontinue	  from b_setup_authorization)  ,'');
insert into b_option_detail values ('dispense', (select 	setup_authorization_dispense	  from b_setup_authorization)  ,'');
insert into b_option_detail values ('execute', (select 	setup_authorization_execute	  from b_setup_authorization) ,'');
insert into b_option_detail values ('in_queue_visit', (select 	setup_authorization_in_queue_visit 	  from b_setup_authorization) ,'');
insert into b_option_detail values ('jasper_print', (select 	setup_authorization_jasper_print	  from b_setup_authorization) ,'');
insert into b_option_detail values ('training_system', (select 	setup_authorization_training_system	  from b_setup_authorization) ,'');
insert into b_option_detail values ('verify', (select 	setup_authorization_verify	  from b_setup_authorization) ,'');
insert into b_option_detail values ('drug_interaction', (select 	setup_authorization_drug_interaction	  from b_setup_authorization)  ,'');
insert into b_option_detail values ('drug_standard_allergy', (select 	setup_authorization_drug_standard_allergy	  from b_setup_authorization) ,'');
insert into b_option_detail values ('set_icd10_form_map_dx', (select 	setup_authorization_set_icd10_form_map_dx	  from b_setup_authorization) ,'');

CREATE TABLE t_patient_past_history (
    t_patient_past_history_id character varying(255) NOT NULL,
    t_patient_id character varying(255),
    patient_past_history_topic character varying(255),
    patient_past_history_date_description character varying(255),
    patient_past_history_description character varying(4000),
    patient_past_history_staff_record character varying(255),
    patient_past_history_record_date_time character varying(255)
);
ALTER TABLE ONLY t_patient_past_history
    ADD CONSTRAINT t_patient_past_history_pkey PRIMARY KEY (t_patient_past_history_id);
ALTER INDEX public.t_patient_past_history_pkey OWNER TO postgres;

alter table  t_patient_family_history add     patient_family_history_topic  varchar(255) default '';

alter table  t_patient_risk_factor add     patient_risk_factor_topic  varchar(255) default '';

alter table  t_patient_drug_allergy add     patient_drug_allergy_record_date_time  varchar(255) default '';

------------------------- ojika 23/5/2549 --------------------
------------------------------------------------------------------------------------------------
--------------------------------------กลุ่มสิทธิการรักษา------------------------------------
------------------------------------------------------------------------------------------------

--ok1-- ลบรายการที่ไม่มี b_contract จากตาราง b_contract_condition
delete from b_contract_condition 
where b_contract_condition.b_contract_condition_id 
 IN (select b_contract_condition.b_contract_condition_id 
 from b_contract_condition
 where b_contract_condition.b_contract_id 
 NOT IN (select b_contract_id from b_contract) );

------------------------------------------------------------------------------------------
--ok2-- ลบรายการที่ ไม่มีใน b_item_subgroup จากตาราง b_contract_condition

delete from b_contract_condition 
where b_contract_condition.b_contract_condition_id 
 IN (select b_contract_condition.b_contract_condition_id 
  from b_contract_condition
  where b_contract_condition.b_item_subgroup_id 
   NOT IN (select b_item_subgroup_id from b_item_subgroup) );

-- ข้อมูลไม่เหมาะสม เพราะมีข้อมูลที่จำเพาะ รพ เกินไป เช่น โรงพยาบาลอ่าวลึก
update  b_contract_payer set contract_payer_description = 'โรงพยาบาล' where contract_payer_description like 'โรงพยาบาลอ่าวลึก';

--ok5-- ลบข้อมูลของ chronic ที่ไม่มีอยู่จริง

update b_group_icd10 set
b_group_chronic_id  = '' 
, group_icd10_b_group_chronic_id = '' 
where (b_group_chronic_id  
  IN (select  b_group_chronic_id
   from b_group_icd10
   where (b_group_icd10.b_group_chronic_id 
   NOT IN (select b_group_chronic_id from b_group_chronic) )
   AND b_group_icd10.b_group_chronic_id  <> ''))
  OR
      (group_icd10_b_group_chronic_id  
  IN (select  group_icd10_b_group_chronic_id
   from b_group_icd10
   where (b_group_icd10.group_icd10_b_group_chronic_id 
   NOT IN (select group_chronic_number from b_group_chronic) )
   AND b_group_icd10.group_icd10_b_group_chronic_id  <> ''));

-- ข้อมูลที่ควรมีดังนี้
-- ','99 ','N/A
--','โรคอื่นๆ ที่หามาได้ เช่น เบาหวาน --> ต้องหาโรคเรื้อรัง และ รหัส เพื่อมาทำเป็นข้อมูลมาตรฐาน
--SQL :  รหัสตาราง คือ 165
insert into b_group_chronic (b_group_chronic_id,group_chronic_number,group_chronic_description_th,group_chronic_description_en,group_chronic_active)
 values ('1650000000001','99','N/A','N/A','1');

--ok6-- ลบรายการของ b_item_drug  ที่มีไม่มีใน b_item

delete from b_item_drug 
where  b_item_drug_id 
  IN (select b_item_drug_id
   from b_item_drug
   where (b_item_drug.b_item_id 
    NOT IN (select b_item_id from b_item) ));

--ok7-- ลบรายการของ b_item_set  ที่มีไม่มีใน b_item_group

delete from b_item_set 
where b_item_set.b_item_set_id 
  IN (select  b_item_set_id
   from b_item_set
    where (b_item_set.b_item_group_id 
     NOT IN (select b_item_group_id from b_item_group) ));

------------------------------------------------------------------------------------------------
--ok8-- ลบรายการของ b_item_set  ที่มีไม่มีใน b_item


------------------------------------------------------------------------------------------------
--ok9-- ลบรายการของ b_item_set_drug_setup  ที่มีไม่มีใน b_item

delete from b_item_set_drug_setup 
where b_item_set_drug_setup_id
  IN (select  b_item_set_drug_setup_id
   from b_item_set_drug_setup
   where (b_item_set_drug_setup.b_item_set_id 
    NOT IN (select b_item_set_id from b_item_set) ));

--ok10-- ลบรายการของ b_item_lab_set  ที่มีไม่มีใน b_item

delete from  b_item_lab_set
where  b_item_lab_set_id
  IN (select b_item_lab_set_id
   from b_item_lab_set
   where (b_item_lab_set.b_item_id 
    NOT IN (select b_item_id from b_item) ));

------------------------------------------------------------------------------------------------
--ok11-- ลบรายการของ b_item_lab_group  ที่มีไม่มีใน b_item

delete from b_item_lab_group
where b_item_lab_group_id
  IN (select b_item_lab_group_id
   from b_item_lab_group
   where (b_item_lab_group.b_item_id 
    NOT IN (select b_item_id from b_item) ));
------------------------------------------------------------------------------------------------
--ok12-- ลบรายการของ b_item_lab_group  ที่มีไม่มีใน b_item_lab_set

delete from b_item_lab_group
where b_item_lab_group_id
  IN (select b_item_lab_group_id
   from b_item_lab_group
   where (b_item_lab_group.b_item_lab_set_id 
    NOT IN (select b_item_lab_set_id from b_item_lab_set) ));

------------------------------------------------------------------------------------------------
--ok13-- ลบรายการของ b_item_lab_result  ที่มีไม่มีใน b_item

delete from b_item_lab_result
where b_item_lab_result_id
  IN (select b_item_lab_result_id
   from b_item_lab_result
   where (b_item_lab_result.b_item_id 
    NOT IN (select b_item_id from b_item) ));

--ok14-- ลบรายการของ b_template_sign_symptom  ที่มีไม่มีใน b_service_point

delete from b_template_sign_symptom
where b_template_sign_symptom_id 
  IN (select  b_template_sign_symptom_id
    from b_template_sign_symptom
   where (b_template_sign_symptom.b_service_point_id 
    NOT IN (select b_service_point_id from b_service_point) ));

-- ตาราง b_sequence_data
-- ข้อมูลที่ไม่เหมาะสม
update b_sequence_data set
sequence_data_pattern = 'yy00000'
where b_sequence_data_id = 'rn';

update b_sequence_data set
sequence_data_pattern = '0yy00000'
where b_sequence_data_id = 'vn';

update b_sequence_data set
sequence_data_pattern = '000000000'
where b_sequence_data_id = 'hn';


------------------------------------------------------------------------------------------------
----------------------------------------กลุ่ม 504 --------------------------------------------
------------------------------------------------------------------------------------------------

-- กลุ่มที่ 19 ไม่มีรหัส Y11 - Y19
update  b_group_icd10  set  group_icd10_group_rp504 = '19' 
where trim(group_icd10_number) = 'Y11' 
OR  trim(group_icd10_number) = 'Y12' 
OR  trim(group_icd10_number) = 'Y13' 
OR  trim(group_icd10_number) = 'Y14' 
OR  trim(group_icd10_number) = 'Y15'
OR  trim(group_icd10_number) = 'Y16' 
OR  trim(group_icd10_number) = 'Y17' 
OR  trim(group_icd10_number) = 'Y18' 
OR  trim(group_icd10_number) = 'Y19' ;


--  noom 1
update  b_group_icd10  set  f_group_rp504_id = '3060000000019' 
where trim(group_icd10_number) = 'Y11' 
OR  trim(group_icd10_number) = 'Y12' 
OR  trim(group_icd10_number) = 'Y13' 
OR  trim(group_icd10_number) = 'Y14' 
OR  trim(group_icd10_number) = 'Y15'
OR  trim(group_icd10_number) = 'Y16' 
OR  trim(group_icd10_number) = 'Y17' 
OR  trim(group_icd10_number) = 'Y18' 
OR  trim(group_icd10_number) = 'Y19' ;

-- กลุ่มที่ 21 รหัส Y90 - Y98 เกินมา
update  b_group_icd10  set  group_icd10_group_rp504 = '99' 
where trim(group_icd10_number) = 'Y90' 
OR  trim(group_icd10_number) = 'Y91' 
OR  trim(group_icd10_number) = 'Y92' 
OR  trim(group_icd10_number) = 'Y93' 
OR  trim(group_icd10_number) = 'Y94'
OR  trim(group_icd10_number) = 'Y95' 
OR  trim(group_icd10_number) = 'Y96' 
OR  trim(group_icd10_number) = 'Y97' 
OR  trim(group_icd10_number) = 'Y98' ;

--  noom 2
update  b_group_icd10  set  f_group_rp504_id = '3060000000022' 
where trim(group_icd10_number) = 'Y90' 
OR  trim(group_icd10_number) = 'Y91' 
OR  trim(group_icd10_number) = 'Y92' 
OR  trim(group_icd10_number) = 'Y93' 
OR  trim(group_icd10_number) = 'Y94'
OR  trim(group_icd10_number) = 'Y95' 
OR  trim(group_icd10_number) = 'Y96' 
OR  trim(group_icd10_number) = 'Y97' 
OR  trim(group_icd10_number) = 'Y98' ;

-- noom insert  เพิ่ม กลุ่มของ C80 เข้ามา
INSERT INTO b_group_icd10(b_group_icd10_id, group_icd10_number, group_icd10_group_rp504, group_icd10_group_rp505, group_icd10_group_rp506, group_icd10_b_group_chronic_id, group_icd10_other, f_group_rp504_id, f_group_rp505_id, f_group_rp506_id, b_group_chronic_id, b_health_disease_id) 
    VALUES('3100000009999', 'C08', '2', '99', '99', '', '', '3060000000002', '3070000000076', '3080000000076', '', '7830000000000');

------------------------------------------------------------------------------------------------
----------------------------------------กลุ่ม 505 --------------------------------------------
------------------------------------------------------------------------------------------------

-- กลุ่มที่ 1 รหัส A00 เกินมา
update  b_group_icd10  set  group_icd10_group_rp505 = '99' 
where trim(group_icd10_number) = 'A00' ;

--noom3
update  b_group_icd10  set  f_group_rp505_id = '3070000000076' 
where trim(group_icd10_number) = 'A00' ;

-- กลุ่มที่ 10 รหัส หาย ได้แก่ 
-- A47,A61,A62,A72,A73,A97
-- ,B28,B29,B31,B32,B61,B62,B63,B84,B93,B98
--** แต่ไม่มีกลุ่มรหัสนี้ใน ตาราง icd10

-- กลุ่มที่ 15 รหัส หาย ได้แก่ 
-- D54,D78,D79,D85,D87,D88
--** แต่ไม่มีกลุ่มรหัสนี้ใน ตาราง icd10

-- กลุ่มที่ 19 รหัส หาย ได้แก่ 
-- E17-E19,E33,E36-E39,E47-E49,E57,E62,E69,E81,E82
--** แต่ไม่มีกลุ่มรหัสนี้ใน ตาราง icd10

-- กลุ่มที่ 20 รหัส หาย ได้แก่ 
--','F08
--** แต่ไม่มีกลุ่มรหัสนี้ใน ตาราง icd10

-- ไม่มีกลุ่ม 21 ในตาราง b_group_icd10
update  b_group_icd10  set  group_icd10_group_rp505 = '21' 
where trim(group_icd10_number) = 'F10' 
OR trim(group_icd10_number) = 'F11' 
OR trim(group_icd10_number) = 'F12' 
OR trim(group_icd10_number) = 'F13' 
OR trim(group_icd10_number) = 'F14' 
OR trim(group_icd10_number) = 'F15' 
OR trim(group_icd10_number) = 'F16' 
OR trim(group_icd10_number) = 'F17' 
OR trim(group_icd10_number) = 'F18' 
OR trim(group_icd10_number) = 'F19' ;

-- noom4
update  b_group_icd10  set  f_group_rp505_id = '3070000000021' 
where trim(group_icd10_number) = 'F10' 
OR trim(group_icd10_number) = 'F11' 
OR trim(group_icd10_number) = 'F12' 
OR trim(group_icd10_number) = 'F13' 
OR trim(group_icd10_number) = 'F14' 
OR trim(group_icd10_number) = 'F15' 
OR trim(group_icd10_number) = 'F16' 
OR trim(group_icd10_number) = 'F17' 
OR trim(group_icd10_number) = 'F18' 


OR trim(group_icd10_number) = 'F19' ;



------------------------------------------------------------------------------------------------



------------------------------------------------------------------------------------------------



-- รายการคลินิกที่ยังไม่มีใน HospitalOS
--02','ศัลยกรรม','','','','
--03','สูติกรรม','','','','','
--04','นรีเวชกรรม','','','','
--08','ศัลยกรรมกระดูก
--23','รังสีวิทยา เดิมต้องเป็น 10 แต่มีการใช้ 10 ไปแล้วจึงเลือกรหัสที่ไม่ใช้แทน 
--ไม่เพิ่มเพราะ รพ อาจจะมีการเพิ่มเข้าไปเองแล้วจะทำให้ ข้อมุ,ซ้ำซ้อนได้ แต่ต้องมีในเอกสารการ Update บอกให้เพิ่มข้อมูลเหล่านี้ไปด้วย
--insert into b_visit_clinic (b_visit_clinic_id,visit_clinic_number,visit_clinic_description,f_service_group_id,visit_clinic_active) 
--','values('1310000000011','02','ศัลยกรรม','7','1');
--insert into b_visit_clinic (b_visit_clinic_id,visit_clinic_number,visit_clinic_description,f_service_group_id,visit_clinic_active) 
--','values('1310000000012','03','สูติกรรม','7','1');
--insert into b_visit_clinic (b_visit_clinic_id,visit_clinic_number,visit_clinic_description,f_service_group_id,visit_clinic_active) 
--','values('1310000000013','04','นรีเวชกรรม','7','1');
--insert into b_visit_clinic (b_visit_clinic_id,visit_clinic_number,visit_clinic_description,f_service_group_id,visit_clinic_active) 
--','values('1310000000014','08','ศัลยกรรมกระดูก','7','1');
--insert into b_visit_clinic (b_visit_clinic_id,visit_clinic_number,visit_clinic_description,f_service_group_id,visit_clinic_active) 
--','values('1310000000015','23','รังสีวิทยา','7','1');

-----------------------------------

--รหัส ICD10 ที่ซ้ำกันในฐานข้อมูล
--','M05.1','','550000006069','','550000006070
--','F20.0','','550000002929','','550000002930','
--','F00.0','','550000002364','','550000002365','
--','G13.0','','550000003418','','550000003417
--','F02.0','','550000002428','','550000002427
--','M01.0','','550000005856','','550000005857
--','F30.0','','550000003034','','550000003033
--','G32.0','','550000003469','','550000003470
--','T80.0','','550000013811','','550000013812
--','G02.0','','550000003373','','550000003374
--','L99.0','','550000005796','','550000005797
--','I79.0','','550000004570','','550000004571
--','M40.0','','550000007150','','550000007151
--','F80.0','','550000003291','','550000003292
--','V74.38','','550000018564','','550000018565
--','K87.0','','550000005373','','550000005374','
--','F60.0','','550000003205','','550000003206

delete from b_icd10 where b_icd10_id = '550000006070';
delete from b_icd10 where b_icd10_id = '550000002930';
delete from b_icd10 where b_icd10_id = '550000002365';
delete from b_icd10 where b_icd10_id = '550000003417';
delete from b_icd10 where b_icd10_id = '550000002427';
delete from b_icd10 where b_icd10_id = '550000005857';
delete from b_icd10 where b_icd10_id = '550000003033';
delete from b_icd10 where b_icd10_id = '550000003470';
delete from b_icd10 where b_icd10_id = '550000013812';
delete from b_icd10 where b_icd10_id = '550000003374';
delete from b_icd10 where b_icd10_id = '550000005797';
delete from b_icd10 where b_icd10_id = '550000004571';
delete from b_icd10 where b_icd10_id = '550000007151';
delete from b_icd10 where b_icd10_id = '550000003292';
delete from b_icd10 where b_icd10_id = '550000018565';
delete from b_icd10 where b_icd10_id = '550000005374';
delete from b_icd10 where b_icd10_id = '550000003206';

-------------------------End of Ojika -------------------------->>>>>>> 1.51


-------------------------Henbe 25/5/2549 -เพิ่มการเก็บ active ของ refer-------------------

alter table  t_visit_refer_in_out add     visit_refer_in_out_active  varchar(255) default '1';

----------------------------------------------------------------------------------------------------
--tong
--date 23/05/49 ข้อมูลของอุบัติิเหตุ
alter table t_accident add  accident_to_hos_date character varying(255) default '';
alter table t_accident add  accident_to_hos_time character varying(255) default '';
alter table t_accident add  accident_staff_refer_patient character varying(255) default '';
alter table t_accident add  accident_staff_refer_patient_tel character varying(255) default '';
alter table t_accident add  accident_staff_history_patient character varying(255) default '';
alter table t_accident add  accident_staff_history_patient_tel character varying(255) default '';
alter table t_accident add  accident_proprietress_ring character varying(3000) default '';
alter table t_accident add  accident_proprietress_money character varying(3000) default '';
alter table t_accident add  accident_proprietress_clock character varying(3000) default '';
alter table t_accident add  accident_proprietress_necklace character varying(3000) default '';
alter table t_accident add  accident_proprietress_other character varying(3000) default '';
--date 01/06/49 เพิ่มรายการกลับบ้าน
alter table t_order add order_home  character varying(255) default '0';


-------------------------Henbe 31/5/2549 -เพิ่มการเก็บ active ของ refer-------------------

alter table  t_visit add     visit_emergency  varchar(255) default '0';
alter table  t_visit add     visit_emergency_staff  varchar(255) default '';

--สถานะของฉุกเฉิน
CREATE TABLE f_emergency_status (
    f_emergency_status_id character varying(255) NOT NULL,
    emergency_status_description character varying(255)
);
ALTER TABLE ONLY f_emergency_status
    ADD CONSTRAINT f_emergency_status_pkey PRIMARY KEY (f_emergency_status_id);

insert into f_emergency_status values('0','Undefine');
insert into f_emergency_status values('1','Non Urgent');
insert into f_emergency_status values('2','Urgent');
insert into f_emergency_status values('3','Emergency');
-----------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------

--date 02/06/49 รายละเอียดของ trauma (ประเภทการเกิดอุบัติเหตุ
CREATE TABLE b_setup_accident_type (
    b_setup_accident_type_id character varying(255) NOT NULL,
    setup_accident_type_number character varying(255) default '',
    setup_accident_type_description character varying(255) default '',
    setup_accident_type_trauma character varying(255) default '',
    setup_accident_type_active character varying(255) default '1'
);

ALTER TABLE ONLY b_setup_accident_type
    ADD CONSTRAINT b_setup_accident_id PRIMARY KEY (b_setup_accident_type_id);

CREATE INDEX setup_accident_type_description ON b_setup_accident_type USING btree (setup_accident_type_description);

insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000001','00','ไม่ใช่อุบัติเหตุ','00','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000002','V01','คนเดินถนนถูกจักรยานชน','V01','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000003','V02','คนเดินถนนถูกรถมอเตอร์ไซค์ชน','V02','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000004','V03','คนเดินถนนถูกรถยนต์,ปิคอัพ,รถตู้ชน','V03','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000005','V04','คนเดินถนนถูกรถบรรทุก,รถเมล์ชน','V04','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000006','V05','คนเดินถนนถูกรถไฟชน','V05','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000007','V06','คนเดินถนนถูกยานพาหนะที่ไม่มีเครื่องยนต์ชน','V06','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000008','V09','คนเดินถนนถูกยานพาหนะอื่นๆชน','V09','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000009','V10','รถจักรยานชนคนหรือสัตว์','V10','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000010','V11','รถจักรยานชนรถจักรยาน','V11','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000011','V12','รถจักรยานชนรถมอเตอร์ไซค์','V12','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000012','V13','รถจักรยานชนรถยนต์,ปิคอัพ,รถตู้','V13','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000013','V14','รถจักรยานชนรถบรรทุกรถเมล์','V14','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000014','V15','รถจักรยานชนรถไฟ','V15','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000015','V16','รถจักรยานชนยานพาหนะที่ไม่มีเครื่องยนต์','V16','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000016','V17','รถจักรยานชนวัตถุนิ่งต่างๆ','V17','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000017','V18','รถจักรยาน อุบัติเหตุที่ไม่ใช่การชน','V18','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000018','V19','รถจักรยาน อุบัติเหตุที่ไม่ทราบชัด','V19','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000019','V20','รถมอเตอร์ไซค์ชนคนหรือสัตว์','V20','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000020','V21','รถมอเตอร์ไซค์ชนรถจักรยาน','V21','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000021','V22','รถมอเตอร์ไซค์ชนรถมอเตอร์ไซค์','V22','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000022','V23','รถมอเตอร์ไซค์ชนรถยนต์,ปิคอัพ,รถตู้','V23','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000023','V24','รถมอเตอร์ไซค์ชนรถบรรทุก,รถเมล์','V24','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000024','V25','รถมอเตอร์ไซค์ชนรถไฟ','V25','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000025','V26','รถมอเตอร์ไซค์ชนยานพาหนะอื่นๆที่ไม่มีเครื่องยนต์','V26','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000026','V27','รถมอเตอร์ไซค์ชนวัตถุนิ่งต่างๆ','V27','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000027','V28','รถมอเตอร์ไซค์ อุบัติเหตุที่ไม่ใช่การชน','V28','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000028','V29','รถมอเตอร์ไซค์ อุบัติเหตุที่ไม่ทราบชัดเจน','V29','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000029','V30','รถมอเตอร์ไซค์สามล้อชนคนหรือสัตว์','V30','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000030','V31','รถมอเตอร์ไซค์สามล้อชนรถจักรยาน','V31','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000031','V32','รถมอเตอร์ไซค์สามล้อชนรถมอเตอร์ไซค์','V32','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000032','V33','รถมอเตอร์ไซค์สามล้อชนรถยนต์,ปิคอัพ,รถตู้','V33','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000033','V34','รถมอเตอร์ไซค์สามล้อชนรถบรรทุก,รถเมล์','V34','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000034','V35','รถมอเตอร์ไซค์สามล้อชนรถไฟ','V35','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000035','V36','รถมอเตอร์ไซค์สามล้อชนยานพาหนะอื่นๆที่ไม่มีเครื่องยนต์','V36','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000036','V37','รถมอเตอร์ไซค์สามล้อชนวัตถุนิ่งต่างๆ','V37','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000037','V38','รถมอเตอร์ไซค์สามล้อ อุบัติเหตุที่ไม่ใช่การชน','V38','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000038','V39','รถมอเตอร์ไซค์สามล้อ อุบัติเหตุที่ไม่ทราบชัดเจน','V39','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000039','V40','รถยนต์ชนคนหรือสัตว์','V40','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000040','V41','รถยนต์ชนรถจักรยาน','V41','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000041','V42','รถยนต์ชนรถมอเตอร์ไซค์','V42','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000042','V43','รถยนต์ชนรถยนต์,ปิคอัพ,รถตู้','V43','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000043','V44','รถยนต์ชนรถบรรทุก,รถเมล์','V44','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000044','V45','รถยนต์ชนรถไฟ','V45','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000045','V46','รถยนต์ชนยานพาหนะอื่นๆที่ไม่มีเครื่องยนต์','V46','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000046','V47','รถยนต์ชนวัตถุนิ่งต่างๆ','V47','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000047','V48','รถยนต์ อุบัติเหตุที่ไม่ใช่การชน','V48','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000048','V49','รถยนต์ อุบัติเหตุที่ไม่ทราบชัดเจน','V49','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000049','V50','รถปิคอัพ,รถตู้ชนคนหรือสัตว์','V50','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000050','V51','รถปิคอัพ,รถตู้ชนรถจักรยาน','V51','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000051','V52','รถปิคอัพ,รถตู้ชนรถมอเตอร์ไซค์','V52','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000052','V53','รถปิคอัพ,รถตู้ชนรถยนต์,ปิคอัพ,รถตู้','V53','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000053','V54','รถปิคอัพ,รถตู้ชนรถบรรทุก,รถเมล์','V54','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000054','V55','รถปิคอัพ,รถตู้ชนรถไฟ','V55','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000055','V56','รถปิคอัพ,รถตู้ชนยานพาหนะอื่นๆที่ไม่มีเครื่องยนต์','V56','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000056','V57','รถปิคอัพ,รถตู้ชนวัตถุนิ่งต่างๆ','V57','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000057','V58','รถปิคอัพ,รถตู้ อุบัติเหตุที่ไม่ใช่การชน','V58','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000058','V59','รถปิคอัพ,รถตู้ อุบัติเหตุที่ไม่ทราบชัดเจน','V59','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000059','V60','รถบรรทุกชนคนหรือสัตว์','V60','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000060','V61','รถบรรทุกชนรถจักรยาน','V61','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000061','V62','รถบรรทุกชนรถมอเตอร์ไซค์','V62','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000062','V63','รถบรรทุกชนรถยนต์,ปิคอัพ,รถตู้','V63','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000063','V64','รถบรรทุกชนรถบรรทุก,รถเมล์','V64','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000064','V65','รถบรรทุกชนรถไฟ','V65','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000065','V66','รถบรรทุกชนยานพาหนะอื่นๆที่ไม่มีเครื่องยนต์','V66','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000066','V67','รถบรรทุกชนวัตถุนิ่งต่างๆ','V67','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000067','V68','รถบรรทุก อุบัติเหตุที่ไม่ใช่การชน','V68','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000068','V69','รถบรรทุก อุบัติเหตุที่ไม่ทราบชัดเจน','V69','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000069','V70','รถเมล์ชนคนหรือสัตว์','V70','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000070','V71','รถเมล์ชนรถจักรยาน','V71','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000071','V72','รถเมล์ชนรถมอเตอร์ไซค์','V72','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000072','V73','รถเมล์ชนรถยนต์,ปิคอัพ,รถตู้','V73','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000073','V74','รถเมล์ชนรถบรรทุก,รถเมล์','V74','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000074','V75','รถเมล์ชนรถไฟ','V75','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000075','V76','รถเมล์ชนยานพาหนะอื่นๆที่ไม่มีเครื่องยนต์','V76','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000076','V77','รถเมล์ชนวัตถุนิ่งต่างๆ','V77','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000077','V78','รถเมล์ อุบัติเหตุที่ไม่ใช่การชน','V78','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000078','V79','รถเมล์ อุบัติเหตุที่ไม่ทราบชัดเจน','V79','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000079','V80','ยานพาหนะที่ใช้สัตว์ขับเคลื่อน เกิดอุบัติเหตุจราจร','V80','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000080','V81','ผู้โดยสารรถไฟ เกิดอุบัติเหตุจราจร','V81','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000081','V82','ผู้ใช้รถยนต์ดัดแปลง เกิดอุบัติเหตุจราจร','V82','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000082','V83','ผู้ใช้ยานพาหนะทางอุตสาหกรรม เกิดอุบัติเหตุจราจร','V83','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000083','V84','ผู้ใช้ยานพาหนะทางเกษตรกรรม เกิดอุบัติเหตุจราจร','V84','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000084','V85','ผู้ใช้ยานพาหนะทางการก่อสร้าง เกิดอุบัติเหตุจราจร','V85','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000085','V86','ผู้ใช้ยานพาหนะดัดแปลงเพื่อใช้ขับขี่ เกิดอุบัติเหตุ','V86','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000086','V87','อุบัติเหตุจราจรทราบสาเหตุแต่ไม่ทราบยานพาหนะ','V87','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000087','V88','อุบัติเหตุจราจรสาเหตุอื่นๆที่ไม่ได้ระบุ ไม่ทราบยาน','V88','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000088','V89','อุบัติเหตุจราจรไม่ทราบสาเหตุและยานพาหนะ','V89','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000089','V90','ยานพาหนะทางน้ำ อุบัติเหตุจากการจม','V90','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000090','V91','ยานพาหนะทางน้ำ อุบัติเหตุอื่นๆต่อยานพาหนะ','V91','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000091','V92','ยานพาหนะทางน้ำ จมโดยไม่มีอุบัติเหตุต่อยานพาหนะ','V92','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000092','V93','ยานพาหนะทางน้ำ ไม่มีอุบัติเหตุต่อยานพาหนะและไม่จม','V93','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000093','V94','ยานพาหนะทางน้ำ เกิดอุบัติเหตุอื่นๆ','V94','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000094','V95','ยานพาหนะทางอากาศ เกิดอุบัติเหตุต่อผู้โดยสาร','V95','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000095','V96','ยานพาหนะทางอากาศที่ไม่ได้บิน เกิดอุบัติเหตุ','V96','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000096','V97','ยานพาหนะทางอากาศเกิดอุบัติเหตุอื่นๆ','V97','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000097','V98','อุบัติเหตุการขนส่งโดยสารอื่นๆที่ไม่ได้ระบุ','V98','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000098','V99','อุบัติเหตุการขนส่งโดยสารอื่นๆที่ทราบชัดเจน','V99','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000099','W00','พลัดตกหกล้ม จากระดับเดียวกันบนพื้นน้ำแข็ง','W00','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000100','W01','พลัดตกหกล้ม จากระดับเดียวกันจากการลื่มล้ม','W01','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000101','W02','พลัดตกหกล้ม จากสเก็ต,สกี,สเก็ตน้ำแข็ง','W02','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000102','W03','พลัดตกหกล้ม จากากรชนหรือผลักโดยบุคคลอื่น','W03','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000103','W04','พลัดตกหกล้ม จากการอุ้ม,แบกโดยบุคคลอื่น','W04','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000104','W05','พลัดตกหกล้ม จากรถเข็น','W05','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000105','W06','พลัดตกหกล้ม จากเตียง','W06','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000106','W07','พลัดตกหกล้ม จากเก้าอี้','W07','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000107','W08','พลัดตกหกล้ม จากฟอร์นิเจอร์อื่นๆ','W08','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000108','W09','พลัดตกหกล้ม จากเครื่องเล่นในสนาม','W09','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000109','W10','พลัดตกหกล้ม จากบันได','W10','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000110','W11','พลัดตกหกล้ม จากบันไดลิง','W11','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000111','W12','พลัดตกหกล้ม จากพื้นยกระดับ','W12','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000112','W13','พลัดตกหกล้ม จากตึก,อาคาร','W13','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000113','W14','พลัดตกหกล้ม จากต้นไม้','W14','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000114','W15','พลัดตกหกล้ม จากหน้าผา','W15','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000115','W16','พลัดตกหกล้ม จากการตกน้ำ (ไม่มีการจม)','W16','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000116','W17','พลัดตกหกล้ม จากระดับต่างกัน','W17','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000117','W18','พลัดตกหกล้ม จากระดับเดียวกัน','W18','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000118','W19','พลัดตกหกล้ม ไม่ทราบรายละเอียด','W19','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000119','W20','แรงกลวัตถุสิ่งของ ตกใส่,ขว้างใส่','W20','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000120','W21','แรงกลวัตถุสิ่งของ อุปกรณ์กีฬา','W21','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000121','W22','แรงกลวัตถุสิ่งของ ชนกับวัตถุอืนๆ','W22','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000122','W23','แรงกลวัตถุสิ่งของ จับ,ชน,ติดระหว่างวัตถุ','W23','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000123','W24','แรงกลวัตถุสิ่งของ วัตถุในการขนย้ายอื่นๆ','W24','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000124','W25','แรงกลวัตถุสิ่งของ แก้วบาด','W25','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000125','W26','แรงกลวัตถุสิ่งของ มีด,อาวุธมีคม','W26','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000126','W27','แรงกลวัตถุสิ่งของ อุปกรณ์ไม่มีเครื่องยนต์','W27','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000127','W28','แรงกลวัตถุสิ่งของ เครื่องจักร','W28','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000128','W29','แรงกลวัตถุสิ่งของ อุปกรณ์มีเครื่องยนต์','W29','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000129','W30','แรงกลวัตถุสิ่งของ อุปกรณ์การเกษตร','W30','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000130','W31','แรงกลวัตถุสิ่งของ เครื่องจักรไม่ทราบชนิด','W31','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000131','W32','แรงกลวัตถุสิ่งของ ปืนพก','W32','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000132','W33','แรงกลวัตถุสิ่งของ ปืนยาว,ปืนลูกซอง','W33','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000133','W34','แรงกลวัตถุสิ่งของ ปืนชนิดอื่นๆ','W34','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000134','W35','แรงกลวัตถุสิ่งของ ระเบิดจากการต้ม','W35','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000135','W36','แรงกลวัตถุสิ่งของ ระเบิดจากถังแก๊ซ','W36','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000136','W37','แรงกลวัตถุสิ่งของ ระเบิดจากยางรถ,ท่อน้ำ','W37','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000137','W38','แรงกลวัตถุสิ่งของ ระเบิดจากอุปกรณ์ไม่ทราบชนิด','W38','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000138','W39','แรงกลวัตถุสิ่งของ ระเบิดจากประทัด','W39','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000139','W40','แรงกลวัตถุสิ่งของ ระเบิดจากวัตถุอื่นๆ','W40','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000140','W41','แรงกลวัตถุสิ่งของ ระเบิดจากเครื่องเจ็ทความดันสูง','W41','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000141','W42','แรงกลวัตถุสิ่งของ ระเบิดจากเสียง','W42','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000142','W43','แรงกลวัตถุสิ่งของ ระเบิดดจากการสั่นสะเทือน','W43','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000143','W44','แรงกลวัตถุสิ่งของ วัตถุเข้าตา,ช่องอื่นๆในร่างกาย','W44','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000144','W45','แรงกลวัตถุสิ่งของ วัตถุแปลกปลอมเข้าผิวหนัง','W45','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000145','W49','แรงกลวัตถุสิ่งของ ไม่ทราบรายละเอียด','W49','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000146','W50','แรงกลสิ่งมีชีวิต ถูกคนกัด,เตะ,ข่วน ฯลฯ','W50','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000147','W51','แรงกลสิ่งมีชีวิต ชนกระแทกกับบุคคลอื่น','W51','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000148','W52','แรงกลสิ่งมีชีวิต ถูกฝูงคนชน,กระแทก,เหยียบ','W52','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000149','W53','แรงกลสิ่งมีชีวิต หนูกัด','W53','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000150','W54','แรงกลสิ่งมีชีวิต สุนัขกัด','W54','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000151','W55','แรงกลสิ่งมีชีวิต สัตว์เลี้ยงลูกด้วยนมอื่นๆกัด','W55','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000152','W56','แรงกลสิ่งมีชีวิต สัตว์น้ำกัด','W56','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000153','W57','แรงกลสิ่งมีชีวิต แมลง,สัตว์หลายขาที่ไม่มีพิษกัด','W57','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000154','W58','แรงกลสิ่งมีชีวิต จรเข้กัด','W58','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000155','W59','แรงกลสิ่งมีชีวิต สัตว์เลื้อยคลานอื่นๆกัด','W59','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000156','W60','แรงกลสิ่งมีชีวิต หนาม,ส่วนคมอื่นๆของพืช','W60','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000157','W64','แรงกลสิ่งมีชีวิต ไม่ทราบรายละเอียด','W64','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000158','W65','จมน้ำ ขณะอยู่ในอ่างอาบน้ำ','W65','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000159','W66','จมน้ำ ตกลงในอ่างอาบน้ำ','W66','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000160','W67','จมน้ำ ขณะอยู่ในสระว่ายน้ำ','W67','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000161','W68','จมน้ำ ตกลงในสระว่ายน้ำ','W68','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000162','W69','จมน้ำ ขณะอยู่ในแหล่งน้ำธรรมชาติ','W69','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000163','W70','จมน้ำ ตกลงในแหล่งน้ำธรรมชาติ','W70','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000164','W73','จมน้ำ ในบริเวณอื่นๆที่ไม่ได้ระบุ','W73','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000165','W74','จมน้ำ ไม่ทราบรายละเอียด','W74','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000166','W75','การหายใจสูดดม อุบัติเหตุถูกอุดการหายใจบนเตียง','W75','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000167','W76','การหายใจสูดดม อุบัติเหตุถูกแขวนคอรักคอ','W76','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000168','W77','การหายใจสูดดม จากการเข้าถ้ำ,ดินถล่ม','W77','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000169','W78','การหายใจสูดดม สำลักของเหลวในกระเพาะ','W78','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000170','W79','การหายใจสูดดม สำลักเศษอาหารอุดกั้นทางเดินหายใจ','W79','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000171','W80','การหายใจสูดดม สำลักวัตถุอื่นๆอุดกั้นทางเดินหายใจ','W80','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000172','W81','การหายใจสูดดม จากการอยู่ในที่อับ','W81','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000173','W83','การหายใจสูดดม สาเหตุอื่นที่ไม่ได้ระบุ','W83','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000174','W84','การหายใจสูดดม ไม่ทราบรายละเอียด','W84','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000175','W85','ไฟฟ้า,รังสี,อุณหภูมิ กระแสไฟฟ้าช๊อตจากสายไฟฟ้า','W85','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000176','W86','ไฟฟ้า,รังสี,อุณหภูมิ กระแสไฟฟ้าช๊อตจากแหล่งอื่นๆ','W86','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000177','W87','ไฟฟ้า,รังสี,อุณหภูมิ กระแสไฟฟ้าไม่ทราบแหล่ง','W87','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000178','W88','ไฟฟ้า,รังสี,อุณหภูมิ รังสี Ioninzing','W88','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000179','W89','ไฟฟ้า,รังสี,อุณหภูมิ รังสีอุลตร้าไวโอเลต,แสงทั่วไป','W89','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000180','W90','ไฟฟ้า,รังสี,อุณหภูมิ รังสี Nonionizing','W90','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000181','W91','ไฟฟ้า,รังสี,อุณหภูมิ รังสีไม่ทราบชนิด','W91','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000182','W92','ไฟฟ้า,รังสี,อุณหภูมิ ความร้อนสูงจากแหล่งที่สร้างโดยมนุษย์','W92','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000183','W93','ไฟฟ้า,รังสี,อุณหภูมิ ความเย็นจัดจากแหล่งที่สร้างโดยมนุษย์','W93','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000184','W94','ไฟฟ้า,รังสี,อุณหภูมิ ความดันอากาศศสูง,ต่ำ.เปลี่ยน','W94','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000185','W99','ไฟฟ้า,รังสี,อุณหภูมิ ภาวะอื่นๆที่สร้างโดยมนุษย์','W99','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000186','X00','ไฟ,เปลวไฟ ไฟไหม้ตึก,อาคาร','X00','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000187','X01','ไฟ,เปลวไฟ ไฟไหม้ที่ไม่ใช่ตึก,อาคาร','X01','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000188','X02','ไฟ,เปลวไฟ ไฟที่จุดเองภายในตึก,อาคาร','X02','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000189','X03','ไฟ,เปลวไฟ ไฟที่จุดเองจากที่ไม่ใช่ตึก,อาคาร','X03','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000190','X04','ไฟ,เปลวไฟ วัตถุติดไฟ','X04','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000191','X05','ไฟ,เปลวไฟ ชุดนอนที่ติดไฟ','X05','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000192','X06','ไฟ,เปลวไฟ เสื่อผ้าที่ติดไฟ','X06','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000193','X08','ไฟ,เปลวไฟ จากแหล่งอื่นๆที่ไม่ได้ระบุ','X08','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000194','X09','ไฟ,เปลวไฟ ไม่ทราบรายละเอียด','X09','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000195','X10','วัตถุสารร้อน อาหาร,เครื่องดื่มร้อนๆ','X10','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000196','X11','วัตถุสารร้อน น้ำประปาร้อนๆ','X11','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000197','X12','วัตถุสารร้อน ของเหลวร้อนอื่นๆ','X12','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000198','X13','วัตถุสารร้อน ไอน้ำร้อน','X13','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000199','X14','วัตถุสารร้อน อากาศ,แก๊ซร้อนๆ','X14','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000200','X15','วัตถุสารร้อน อุปกรณ์,เครื่องใช้ร้อนๆ','X15','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000201','X16','วัตถุสารร้อน อุปกรณ์ผลิตความร้อน','X16','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000202','X17','วัตถุสารร้อน เครื่องจักร,เครื่องยนต์ร้อน','X17','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000203','X18','วัตถุสารร้อน โลหะร้อน','X18','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000204','X19','วัตถุสารร้อน แหล่งอื่นๆที่ไม่ได้ระบุ','X19','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000205','X20','พิษจากสิ่งมีชีวิต งูพิษ,สัตว์เลื้อยคลานอื่นๆ','X20','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000206','X21','พิษจากสิ่งมีชีวิต แมงมุมพิษ','X21','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000207','X22','พิษจากสิ่งมีชีวิต แมงป่อง','X22','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000208','X23','พิษจากสิ่งมีชีวิต ผึ้ง,ต่อ,แตน','X23','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000209','X24','พิษจากสิ่งมีชีวิต ตะขาบ,กิ้งกือ','X24','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000210','X25','พิษจากสิ่งมีชีวิต สัตว์(หลายขา)มีพิษอื่นๆ','X25','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000211','X26','พิษจากสิ่งมีชีวิต สัตว์น้ำมีพิษ,พืชมีพิษ','X26','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000212','X27','พิษจากสิ่งมีชีวิต สัตว์มีพิษอื่นๆที่ไม่ได้ระบุ','X27','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000213','X28','พิษจากสิ่งมีชีวิต พืชมีพิษอื่นๆที่ไม่ได้ระบุ','X28','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000214','X29','พิษจากสิ่งมีชีวิต ไม่ทราบรายละเอียด','X29','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000215','X30','ภัยธรรมชาติ ความร้อนสูงตามธรรมชาติ','X30','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000216','X31','ภัยธรรมชาติ ความเย็นจัดตามธรรมชาติ','X31','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000217','X32','ภัยธรรมชาติ แสงแดด','X32','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000218','X33','ภัยธรรมชาติ ฟ้าผ่า','X33','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000219','X34','ภัยธรรมชาติ แผ่นดินไหว','X34','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000220','X35','ภัยธรรมชาติ ภูเขาไฟระเบิด','X35','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000221','X36','ภัยธรรมชาติ ดินถล่ม','X36','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000222','X37','ภัยธรรมชาติ พายุ','X37','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000223','X38','ภัยธรรมชาติ น้ำท่วม','X38','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000224','X39','ภัยธรรมชาติ อื่นๆที่ไม่ได้ระบุ','X39','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000225','X40','รับสารพิษโดยอุบัติเหตุ ยาแก้ปวดลดไข้','X40','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000226','X41','รับสารพิษโดยอุบัติเหตุ ยากนชัก,ยานอนหลับ','X41','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000227','X42','รับสารพิษโดยอุบัติเหตุ ยาแก้ปวด narcotic','X42','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000228','X43','รับสารพิษโดยอุบัติเหตุ ยาที่มีผลต่อระบบประสาทอัตโน','X43','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000229','X44','รับสารพิษโดยอุบัติเหตุ ยาอื่นๆที่ไม่ทราบชนิด','X44','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000230','X45','รับสารพิษโดยอุบัติเหตุ แอลกอฮอล์','X45','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000231','X46','รับสารพิษโดยอุบัติเหตุ สารและผลิตภัณฑ์จากน้ำมัน','X46','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000232','X47','รับสารพิษโดยอุบัติเหตุ แก๊ซ,ไอระเหยต่างๆ','X47','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000233','X48','รับสารพิษโดยอุบัติเหตุ ยาฆ่าแมลง,ยาฆ่าหญ้า','X48','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000234','X49','รับสารพิษโดยอุบัติเหตุ สารเคมีอื่นๆที่ไม่ได้ระบุ','X49','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000235','X50','ออกแรงเกิน ออกแรงซ้ำๆนานๆ','X50','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000236','X51','ออกแรงเกิน ระหว่างการเดินทาง','X51','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000237','X52','ออกแรงเกิน ยืนนานๆ','X52','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000238','X53','ออกแรงเกิน ขาดอาหาร','X53','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000239','X54','ออกแรงเกิน ขาดน้ำ','X54','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000240','X57','ออกแรงเกิน สาเหตุส่วนตัวอื่นๆ','X57','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000241','X58','ปัจจัยอื่นๆที่ไม่ได้ระบุ','X58','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000242','X59','ปัจจัยอื่นๆที่ไม่ทราบรายละเอียด','X59','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000243','X60','ทำร้ายตัวเอง ด้วยยาแก้ปวดลดไข้','X60','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000244','X61','ทำร้ายตัวเอง ด้วยยากันชัก,ยานอนหลับ,ยาระงับประสาท','X61','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000245','X62','ทำร้ายตัวเอง ด้วยยาแก้ปวด narcotic','X62','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000246','X63','ทำร้ายตัวเอง ด้วยยาที่มีผลต่อระบบประสาทอัตโนมัติ','X63','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000247','X64','ทำร้ายตัวเอง ด้วยยาชนิดอื่นๆ','X64','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000248','X65','ทำร้ายตัวเอง ด้วยแอลกอฮอล์','X65','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000249','X66','ทำร้ายตัวเอง ด้วยสาร,ผลิตภัณฑ์จากน้ำมัน','X66','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000250','X67','ทำร้ายตัวเอง ด้วยแก๊ซ,ไอระเหย','X67','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000251','X68','ทำร้ายตัวเอง ด้วยยาฆ่าแมลง,ยาฆ่าหญ้า','X68','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000252','X69','ทำร้ายตัวเอง ด้วยสารเคมีอื่นๆ','X69','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000253','X70','ทำร้ายตัวเอง ด้วยการแขวนคอ,รัดคอ,อุดทางเดินหายใจ','X70','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000254','X71','ทำร้ายตัวเอง ด้วยการกระโดดน้ำตาย','X71','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000255','X72','ทำร้ายตัวเอง ด้วยปืนพก','X72','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000256','X73','ทำร้ายตัวเอง ด้วยปืนยาว,ปืนลูกซอง','X73','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000257','X74','ทำร้ายตัวเอง ด้วยปืนชนิดอื่นๆ','X74','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000258','X75','ทำร้ายตัวเอง ด้วยวัตถุระเบิด','X75','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000259','X76','ทำร้ายตัวเอง ด้วยไฟ,เปลวไฟ,ควัน','X76','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000260','X77','ทำร้ายตัวเอง ด้วยไอน้ำร้อน,วัตถุของร้อน','X77','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000261','X78','ทำร้ายตัวเอง ด้วยของมีคม','X78','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000262','X79','ทำร้ายตัวเอง ด้วยของไม่มีคม','X79','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000263','X80','ทำร้ายตัวเอง ด้วยกระโดดจากที่สูง','X80','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000264','X81','ทำร้ายตัวเอง ด้วยกระโดดจากวัตถุที่เคลื่อนที่','X81','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000265','X82','ทำร้ายตัวเอง ด้วยพุ่งไปชนกับยานพาหนะต่างๆ','X82','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000266','X83','ทำร้ายตัวเอง ด้วยวิธีการอื่นๆที่ไม่ได้ระบุ','X83','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000267','X84','ทำร้ายตัวเอง ไม่ทราบรายละเอียด','X84','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000268','X85','ถูกทำร้าย ด้วยยาชนิดต่างๆ','X85','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000269','X86','ถูกทำร้าย ด้วยสารระคายเคืองสูง เช่นน้ำกรด','X86','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000270','X87','ถูกทำร้าย ด้วยยาฆ่าแมลง,ยาฆ่าหญ้า','X87','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000271','X88','ถูกทำร้าย ด้วยแก๊ซ,ไอระเหย','X88','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000272','X89','ถูกทำร้าย ด้วยสารเคมีอื่นๆที่ไม่ได้ระบุ','X89','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000273','X90','ถูกทำร้าย ด้วยสารเคมีที่ไม่ทราบรายละเอียด','X90','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000274','X91','ถูกทำร้าย ด้วยการแขวนคอ,รัดคอ,อุดทางเดินหายใจ','X91','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000275','X92','ถูกทำร้าย ด้วยการกดให้จมน้ำ','X92','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000276','X93','ถูกทำร้าย ด้วยปืนพก','X93','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000277','X94','ถูกทำร้าย ด้วยปืนยาว,ปืนลูกซอง','X94','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000278','X95','ถูกทำร้าย ด้วยปืนชนิดอื่นๆ','X95','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000279','X96','ถูกทำร้าย ด้วยวัตถุระเบิด','X96','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000280','X97','ถูกทำร้าย ด้วยไฟ,เปลวไฟ,ควันไฟ','X97','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000281','X98','ถูกทำร้าย ด้วยไอน้ำร้อน,วัตถุร้อน','X98','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000282','X99','ถูกทำร้าย ด้วยของมีคม','X99','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000283','Y00','ถูกทำร้าย ด้วยของไม่มีคม','Y00','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000284','Y01','ถูกทำร้าย ด้วยการผลักจากที่สูง','Y01','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000285','Y02','ถูกทำร้าย ด้วยผลักจากวัตถุเคลื่อนที่','Y02','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000286','Y03','ถูกทำร้าย ด้วยผลักให้ไปชนกับยานพาหนะ','Y03','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000287','Y04','ถูกทำร้าย ด้วยกำลังจากร่างกาย','Y04','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000288','Y05','ถูกทำร้าย ด้วยกำลังจากร่างกายและมีการล่วงเกินทางเพศ','Y05','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000289','Y06','ถูกทำร้าย ด้วยการละทิ้ง','Y06','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000290','Y07','ถูกทำร้าย ด้วยการดูแลรักษาที่ผิด','Y07','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000291','Y08','ถูกทำร้าย ด้วยวิธีการอื่นๆที่ไม่ได้ระบุ','Y08','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000292','Y09','ถูกทำร้าย ไม่ทราบรายละเอียด','Y09','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000293','Y10','ไม่ทราบเจตนา ถูกพิษจากยาแก้ปวดลดไข้','Y10','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000294','Y11','ไม่ทราบเจตนา ถูกพิษจากยากันชัก,ยานอนหลับ','Y11','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000295','Y12','ไม่ทราบเจตนา ถูกยาแก้ปวด narcotic','Y12','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000296','Y13','ไม่ทราบเจตนา ถูกยาที่มีผลต่อระบบประสาทอัตโนมัติ','Y13','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000297','Y14','ไม่ทราบเจตนา ถูกพิษจากยาชนิดอื่นๆ','Y14','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000298','Y15','ไม่ทราบเจตนา ถูกพิษจากอแลกอฮอล์','Y15','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000299','Y16','ไม่ทราบเจตนา ถูกพิษจากสาร,ผลิตภัณฑ์จากน้ำมัน','Y16','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000300','Y17','ไม่ทราบเจตนา ถูกแก๊ซ,ไอระเหย','Y17','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000301','Y18','ไม่ทราบเจตนา ถูกยาฆ่าแมลง,ยาฆ่าหญ้า','Y18','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000302','Y19','ไม่ทราบเจตนา ถูกสารเคมีอื่นๆที่ไม่ได้ระบุ','Y19','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000303','Y20','ไม่ทราบเจตนา ถูกแขวนคอ,รัดคอ,อุดกั้นทางเดินหายใจ','Y20','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000304','Y21','ไม่ทราบเจตนา จมน้ำ','Y21','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000305','Y22','ไม่ทราบเจตนา ถูกปืนพกยิง','Y22','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000306','Y23','ไม่ทราบเจตนา ถูกปืนยาว,ปืนลูกซองยิง','Y23','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000307','Y24','ไม่ทราบเจตนา ถูกปืนชนิดอื่นๆยิง','Y24','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000308','Y25','ไม่ทราบเจตนา ถูกวัตถุระเบิด','Y25','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000309','Y26','ไม่ทราบเจตนา ถูกไฟ,เปลวไฟ,ควัน','Y26','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000310','Y27','ไม่ทราบเจตนา ถูกไอน้ำร้อน,วัตถุร้อน','Y27','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000311','Y28','ไม่ทราบเจตนา ถูกของมีคม','Y28','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000312','Y29','ไม่ทราบเจตนา ถูกของไม่มีคม','Y29','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000313','Y30','ไม่ทราบเจตนา ตกจากที่สูง','Y30','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000314','Y31','ไม่ทราบเจตนา ตกจากวัตถุเคลื่อนที่','Y31','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000315','Y32','ไม่ทราบเจตนา ชนกับยานพาหนะ','Y32','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000316','Y33','ไม่ทราบเจตนา เกิดเหตุอื่นๆที่ไม่ได้ระบุ','Y33','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000317','Y34','ไม่ทราบเจตนา ไม่ทราบรายละเอียด','Y34','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000318','Y35','การดำเนินการทางกฎหมาย','Y35','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000319','Y36','การปฏิบัติการในสงคราม','Y36','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000320','Y40','ผลข้างเคียงของยา ยาปฏิชีวนะ','Y40','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000321','Y41','ผลข้างเคียงของยา ยาแก้อักเสบ,ยาฆ่าพยาธิอื่นๆ','Y41','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000322','Y42','ผลข้างเคียงของยา ยาฮอร์โมน','Y42','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000323','Y43','ผลข้างเคียงของยา สารใช้ในการฉีด','Y43','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000324','Y44','ผลข้างเคียงของยา สารเลือด','Y44','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000325','Y45','ผลข้างเคียงของยา ยาแก้ปวด,ลดไข้','Y45','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000326','Y46','ผลข้างเคียงของยา ยากันชัก,antiparkinson','Y46','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000327','Y47','ผลข้างเคียงของยา ยานอนหลับ,ระงับประสาท','Y47','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000328','Y48','ผลข้างเคียงของยา ยาใช้ในการดมยาสลบ','Y48','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000329','Y49','ผลข้างเคียงของยา ยารักษาโรคจิตอื่นๆ','Y49','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000330','Y50','ผลข้างเคียงของยา ยากระตุ้นประสาทส่วนกลางอื่นๆ','Y50','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000331','Y51','ผลข้างเคียงของยา ยาที่มีผลต่อระบบประสาทอัตโนมัติ','Y51','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000332','Y52','ผลข้างเคียงของยา ยาระบบไหลเวียนดลหิต','Y52','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000333','Y53','ผลข้างเคียงของยา ยาระบบทางเดินอาหาร','Y53','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000334','Y54','ผลข้างเคียงของยา ยาที่มีผลกับสมดุลเกลือแร่,uric acid','Y54','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000335','Y55','ผลข้างเคียงของยา ยาที่มีผลกับกล้ามเนื้อ,ระบบทางเดินหายใจ','Y55','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000336','Y56','ผลข้างเคียงของยา ยาทาผิวหนัง,ยาใช้กับตา,หู,คอ,จมูก','Y56','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000337','Y57','ผลข้างเคียงของยา ยาชนิดอื่นๆที่ไม่ได้ระบุ','Y57','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000338','Y58','ผลข้างเคียงของยา วัคซีน','Y58','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000339','Y59','ผลข้างเคียงของยา ไม่ทราบรายละเอียด','Y59','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000340','Y60','การบกพร่องทางการรักษา การตัด,ทำให้ทะลุ,มีเลือดออกในการผ่าตัด','Y60','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000341','Y61','การบกพร่องทางการรักษา ลืมของไว้ในผู้ป่วยในการผ่าตัด','Y61','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000342','Y62','การบกพร่องทางการรักษา ผิดพลาดทาง Sterile ในการผ่าตัด','Y62','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000343','Y63','การบกพร่องทางการรักษา ให้ยาผิดขนาด','Y63','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000344','Y64','การบกพร่องทางการรักษา ทำการ Contamination','Y64','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000345','Y65','การบกพร่องทางการรักษา อื่นๆที่ไม่ได้ระบุ','Y65','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000346','Y66','การบกพร่องทางการรักษา โดยไม่ให้การรักษา','Y66','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000347','Y69','การบกพร่องทางการรักษา ไม่ทราบรายละเอียด','Y69','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000348','Y70','ความผิดพลาดทางอุปกรณ์การรักษา อุปกรณ์ดมยาสลบ','Y70','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000349','Y71','ความผิดพลาดทางอุปกรณ์การรักษา อุปกรณ์ระบบไหลเวียน','Y71','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000350','Y72','ความผิดพลาดทางอุปกรณ์การรักษา อุปกรณ์หู,คอ,จมูก','Y72','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000351','Y73','ความผิดพลาดทางอุปกรณ์การรักษา อุปกรณ์ทางเดินอาหาร,ปัสสาวะ','Y73','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000352','Y74','ความผิดพลาดทางอุปกรณ์การรักษา อุปกรณ์ทั่วไปในรพ.','Y74','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000353','Y75','ความผิดพลาดทางอุปกรณ์การรักษา อุปกรณ์ระบบประสาท','Y75','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000354','Y76','ความผิดพลาดทางอุปกรณ์การรักษา อุปกรณ์ทางสูตินรีเวช','Y76','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000355','Y77','ความผิดพลาดทางอุปกรณ์การรักษา อุปกรณ์ทางตา','Y77','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000356','Y78','ความผิดพลาดทางอุปกรณ์การรักษา อุปกรณ์ทางรัวสี','Y78','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000357','Y79','ความผิดพลาดทางอุปกรณ์การรักษา อุปกรณ์ทางศัลยกรรมกระดูก','Y79','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000358','Y80','ความผิดพลาดทางอุปกรณ์การรักษา อุปกรณ์กายภาพบำบัด','Y80','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000359','Y81','ความผิดพลาดทางอุปกรณ์การรักษา อุปกรณ์ศัลยกรรมตกแต่ง','Y81','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000360','Y82','ความผิดพลาดทางอุปกรณ์การรักษา อุปกรณ์อื่นๆที่ไม่ได้ระบุ','Y82','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000361','Y83','การรักษาผิดพลาดที่เป็นอุบัติเหตุอื่นๆที่ไม่ได้ระบุ','Y83','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000362','Y84','การรักษาผิดพลาดที่เป็นอุบัติเหตุไม่ทราบรายละเอียด','Y84','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000363','Y85','ผลที่ตามมาจากอุบัติเหตุจราจร','Y85','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000364','Y86','ผลที่ตามมาจากอุบัติเหตุอื่นๆ','Y86','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000365','Y87','ผลที่ตามมาจากการทำร้ายร่างกาย,ถูกทำร้าย','Y87','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000366','Y88','ผลที่ตามมาจากการรักษา','Y88','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000367','Y89','ผลที่ตามมาจากสาเหตุอื่นๆ','Y89','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000368','Y90','ผลเกี่ยวข้องจากการมีระดับแอลกอฮอล์ในเลือดสูง','Y90','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000369','Y91','ผลเกี่ยวข้องจากการมีแอลกอฮอล์ในเลือดสูงในระดับเป็นพิษ','Y91','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000370','Y95','ผลเกี่ยวข้องจากภาวะทางสังคม','Y95','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000371','Y96','ผลเกี่ยวข้องจากการทำงาน','Y96','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000372','Y97','ผลเกี่ยวข้องจากสภาพแวดล้อมเป็นพิษ','Y97','1');
insert into b_setup_accident_type(b_setup_accident_type_id,setup_accident_type_number,setup_accident_type_description,setup_accident_type_trauma,setup_accident_type_active) values('3150000000373','Y98','ผลเกี่ยวข้องจากลักษณะการใช้ชีวิต','Y98','1');
-------------------------Sumo 2/6/2549 -เพิ่มการเก็บรายละเอียดของผู้เข้าร่วมผ่าตัด-------------------
alter table  t_diag_icd9_participate_or add     diag_icd9_participate_or_staff_insert  varchar(255) default '';
alter table  t_diag_icd9_participate_or add     diag_icd9_participate_or_time_insert  varchar(255) default '';
alter table  t_diag_icd9_participate_or add     diag_icd9_participate_or_staff_update  varchar(255) default '';
alter table  t_diag_icd9_participate_or add     diag_icd9_participate_or_time_update  varchar(255) default '';
alter table  t_diag_icd9_participate_or add     diag_icd9_participate_or_active  varchar(255) default '0';
-------------------------Sumo 3/6/2549 -เพิ่ม Option การลง ICD9 ที่ Map กับค่าบริการให้อัตโนมัติ-------------------
alter table  b_setup_authorization add     setup_authorization_set_icd9_map_service  varchar(255) default '0';
--เพิ่มข้อมูลเลข Sequence ของการ Refer เป็นเลข rfin กับ rfon
INSERT INTO b_sequence_data (b_sequence_data_id, sequence_data_description, sequence_data_pattern, sequence_data_value)  VALUES ('rfin', 'refer in number', 'INyy00000', '1');
INSERT INTO b_sequence_data (b_sequence_data_id, sequence_data_description, sequence_data_pattern, sequence_data_value)  VALUES ('rfon', 'refer out number', 'OUyy00000', '1');
--เพิ่มข้อมูลกลุ่มรายการมาตรฐานลงตาราง f_gui
INSERT INTO f_gui_action VALUES ('5303', 'รายการกลุ่มมาตรฐาน', '');
--เพิ่มข้อมูลลงตาราง f_gui_action_authen
INSERT INTO f_gui_action_authen VALUES ('000000005303111101', '5303', 'รายการกลุ่มมาตรฐาน', '1', 'เวชระเบียน', '0', '0', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005303111102', '5303', 'รายการกลุ่มมาตรฐาน', '2', 'พยาบาล', '1', '1', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005303111103', '5303', 'รายการกลุ่มมาตรฐาน', '3', 'แพทย์', '1', '1', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005303111104', '5303', 'รายการกลุ่มมาตรฐาน', '4', 'LAB', '0', '0', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005303111105', '5303', 'รายการกลุ่มมาตรฐาน', '5', 'X-RAY', '0', '0', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005303111106', '5303', 'รายการกลุ่มมาตรฐาน', '6', 'เภสัชกร', '1', '1', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005303111107', '5303', 'รายการกลุ่มมาตรฐาน', '7', 'แคชเชียร์', '1', '1', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005303111108', '5303', 'รายการกลุ่มมาตรฐาน', '8', 'เวชสถิติ', '1', '1', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005303111109', '5303', 'รายการกลุ่มมาตรฐาน', '9', 'ADMIN', '1', '1', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005303111110', '5303', 'รายการกลุ่มมาตรฐาน', '10', 'ONE STOP SERVICE', '1', '1', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005303111111', '5303', 'รายการกลุ่มมาตรฐาน', '11', 'งานประกัน', '1', '1', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005303111112', '5303', 'รายการกลุ่มมาตรฐาน', '12', 'งานส่งเสริม', '1', '1', ' ');
INSERT INTO f_gui_action_authen VALUES ('000000005303111113', '5303', 'รายการกลุ่มมาตรฐาน', '13', 'งานผู้ป่วยใน', '1', '1', ' ');
----เพิ่มตาราง b_item_16_group เก็บรายการกลุ่มมาตรฐาน
CREATE TABLE b_item_16_group (
    b_item_16_group_id character varying(255) NOT NULL,
   item_16_group_number character varying(255) default '',
    item_16_group_description character varying(255) default '',
    item_16_group_active character varying(255) default '1',
    CONSTRAINT b_item_16_group_pkey PRIMARY KEY (b_item_16_group_id)
);
----เพิ่มข้อมูลตั้งต้นรายการกลุ่มมาตรฐานลงให้ตาราง b_standard_group
INSERT INTO b_item_16_group VALUES ('3120000000001','STI01', 'ชันสูตรทางห้องปฏิบัติการ', '1');
INSERT INTO b_item_16_group VALUES ('3120000000002','STI02', 'X-ray', '1');
INSERT INTO b_item_16_group VALUES ('3120000000003','STI03', 'ตรวจชันสูตรอื่น ๆ', '1');
INSERT INTO b_item_16_group VALUES ('3120000000004','STI04', 'ผ่าตัด', '1');
INSERT INTO b_item_16_group VALUES ('3120000000005','STI05', 'การรักษาอื่น ๆ', '1');
INSERT INTO b_item_16_group VALUES ('3120000000006','STI06', 'ยา เวชภัณฑ์', '1');
INSERT INTO b_item_16_group VALUES ('3120000000007','STI07', 'ห้อง ICU', '1');
INSERT INTO b_item_16_group VALUES ('3120000000008','STI08', 'ค่าห้อง', '1');
INSERT INTO b_item_16_group VALUES ('3120000000009','STI09', 'ค่าอาหาร', '1');
INSERT INTO b_item_16_group VALUES ('3120000000010','STI10', 'อื่น ๆ', '1');
INSERT INTO b_item_16_group VALUES ('3120000000011','STI11', 'อวัยวะเทียม,อุปกรณ์รักษาโรค', '1');
INSERT INTO b_item_16_group VALUES ('3120000000012','STI12', 'ค่าบริการทางโลหิต', '1');
INSERT INTO b_item_16_group VALUES ('3120000000013','STI13', 'ค่าอุปกรณ์เครื่องมือทางการแพทย์', '1');
INSERT INTO b_item_16_group VALUES ('3120000000014','STI14', 'ค่าบริการทันตกรรม', '1');
INSERT INTO b_item_16_group VALUES ('3120000000015','STI15', 'ค่าบริการกายภาพบำบัด', '1');
INSERT INTO b_item_16_group VALUES ('3120000000016','STI16', 'ค่าฝังเข็ม,เวชกรรมสาขาอื่น', '1');
---เพิ่มการเก็บข้อมูลของตาราง b_item กับ t_order 
alter table  b_item add  b_item_16_group_id  varchar(255) default '';
alter table  t_order add  b_item_16_group_id  varchar(255) default '';
---สร้าง Index ของ b_item_16_group_id ให้ตาราง b_item กับ t_order 
CREATE INDEX item_item_16_group ON b_item USING btree (b_item_16_group_id);
CREATE INDEX order_item_16_group ON t_order USING btree (b_item_16_group_id);
----------------------------------------------------------------------------------------------------------------------------------------------

--tong date 05/05/2549 รายละเอียดของการบันทึกข้อมูลอุบัติเหตุ
alter table t_accident add  accident_staff_record_date_time character varying(255) default '';
alter table t_accident add  accident_staff_update character varying(255) default '';
alter table t_accident add  accident_update_date_time character varying(255) default '';
alter table t_accident add  accident_staff_cancel character varying(255) default '';
alter table t_accident add  accident_cancel_date_time character varying(255) default '';
alter table t_accident add  accident_active character varying(255) default '1';
alter table t_accident add  b_setup_accident_type_id character varying(255) default '';


---henbe แก้ไขชื่อ field เพิ่มเติม 06/06/06

alter table t_visit rename visit_emergency to f_emergency_status_id;


---henbe แก้ไขข้อมูล เพิ่มเติม 16/06/06
-- มีปัญหาเกี่ยวกับข้อมูลวันเกิดจริงของผู้ป่วยนะครับ ให้แก้ไขฐานข้อมูลต่อไปนี้นะครับ
update t_patient set patient_birthday_true = '0' where patient_birthday_true is null;
update t_patient set patient_birthday_true = '0' where patient_birthday_true ='null'; 


-------------------------Sumo 12/6/2549 -----------------เพิ่มการเก็บ Path ของเภสัชตำหรับ-------------------
alter table  b_setup_authorization add  setup_path_pharm  varchar(255) default '';
----เพิ่ม field ในตาราง t_visit_discharge_advice เก็บข้อมูลสุขศึกษา-------------13/6/2549----------------------
alter table t_visit_discharge_advice add visit_discharge_advice_advice_head varchar(255) default '';


-------------------------neung 20/6/2549 -------------------------
CREATE TABLE t_wound
(
  t_wound_id varchar(255) NOT NULL,
  wound_position varchar(255),
  describe varchar(255),
  wound_width varchar(255),
  wound_long varchar(255),
  wound_deep varchar(255),
  t_patient_id varchar(255),
  t_visit_id varchar(255),
  position_x varchar(255),
  position_y varchar(255),
  f_wound_picture varchar(255),
  record_date_time varchar(255),
  staff_record varchar(255),
  modify_date_time varchar(255),
  staff_modify varchar(255),
  f_wound_type varchar(255),
  CONSTRAINT t_wound_pkey PRIMARY KEY (t_wound_id)
) 
WITHOUT OIDS;

ALTER TABLE t_wound OWNER TO postgres;
CREATE INDEX wound_patient_id
  ON t_wound
  USING btree
  (t_visit_id);

------ amp 20/7/2549 ----------------------------
--------------- รายการ โรค NCD ---------------
delete from b_ncd_group;
insert into  b_ncd_group values ('2892021678342','HT','01.Hypertension','รหัส00000','1','1653982813966');
insert into  b_ncd_group values ('2891399440937','G','04.Gout','รหัส00000','1','1653982813966');
insert into  b_ncd_group values ('2890299668530','Tha','05.Thalassemia','รหัส00000','1','1651977959760');
insert into  b_ncd_group values ('2897389127774','DM','06.DM','รหัส00000','1','1651785488352');
insert into  b_ncd_group values ('2891497002173','H','07.Heart','รหัส00000','1','1653822786273');
insert into  b_ncd_group values ('2896961106128','C','02.COPD','รหัส00000','1','1654956883354');
insert into  b_ncd_group values ('2899167805640','Thy','08.Thyroid','รหัส00000','1','1652980610607');
insert into  b_ncd_group values ('2897370218695','NS','09.Nephro','รหัส00000','1','1659611064039');
insert into  b_ncd_group values ('2895478536034','An','14.Anemia','รหัส00000','1','1653408220866');
insert into  b_ncd_group values ('2894757623132','R','13.Renal','รหัส00000','1','1651800286238');
insert into  b_ncd_group values ('2892568641652','Psy','03.Psychiatry','รหัส00000','1','1650000000001');
insert into  b_ncd_group values ('2896220365463','E','10.Epilepsy','รหัส00000','1','1650000000001');
insert into  b_ncd_group values ('2899957321157','TB','11.TB','รหัส00000','1','1650000000001');
insert into  b_ncd_group values ('2892486968508','Asth','12.Asthma','รหัส00000','1','1650000000001');


------------------------Sumo 28/7/2549----------------------
-------Update ข้อมูลคำแนะนำที่ใน field  visit_discharge_advice_advice_head เป็นช่องว่าง ให้มีหัวข้อเป็น 'อื่นๆ' ในตาราง t_visit_discharge_advice ------------
update t_visit_discharge_advice set visit_discharge_advice_advice_head = 'อื่นๆ' where visit_discharge_advice_advice != '' and visit_discharge_advice_advice_head = '' ;


------เป็นความผิดพลาดหลังจากไปขึ้นระบบให้ที่เวียงสาแล้วพบบักของโปรแกรมที่เกิดจากฐานข้อมูล

----------- Tuk  08/09/2549 -------------------
----------- เพิ่ม column xray_film_size_number ให้ตาราง f_xray_film_size -------------
alter table  f_xray_film_size add  xray_film_size_number  varchar(255) default '';
alter table  f_xray_film_size add  xray_film_size_price  varchar(255) default '70';

------------------
------การปรับแก้ในเวอร์ชัน 3.07 สำหรับ update จากเวอร์ชัน 3.06
create table s_script_update_log(
 script_update_log_modname varchar(255),
 script_update_log_filename varchar(255),
 script_update_log_datetime varchar(255),
 script_update_log_note varchar(255)
);
insert into s_script_update_log values ('hospitalOS','updateV3_7.sql',(select current_date) || ','|| (select current_time),'เปลี่ยนเวอร์ชันจาก 3.6 เป็น 3.7 น่าน');

------การปรับแก้ในเวอร์ชัน 3.7 สำหรับ update จากเวอร์ชัน 3.06
CREATE INDEX herb_home_id ON t_health_home_herb USING btree (t_health_home_id);
INSERT INTO s_version VALUES ('9701000000017', '27', 'Hospital OS, Community Edition', '3.7.250406', '3.14.300406', '2549-06-12 12:25:00');
------  
---------------------------------------------------------------------------------------------------------------------------------------------------------
