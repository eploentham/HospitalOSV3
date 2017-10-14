
--ขอเพิ่มรหัส 3 = รหัสรายการค่าพาหนะ และบริการที่ยังไม่ได้จัดหมวดหมู่ (ตามที่ สปสช. กำหนด)
--ในตาราง r_rp1253_adpcode โดยเพิ่ม SQL ใน File Update ดังนี้
insert into r_rp1253_adpcode values('3','รหัสรายการค่าพาหนะ และบริการที่ยังไม่ได้จัดหมวดหมู่ (ตามที่ สปสช. กำหนด)');

--ข้อมูลการจับคู่ CHARITEM สามารถจับคู่ให้เบื้องต้น ได้แก่ กลุ่มเวชภัณฑ์ไม่ใช่ยา ใช้ SQL 
update b_item set r_rp1253_charitem_id = '5'
where b_item.b_item_id in
(select b_item_id from b_item inner join b_item_subgroup on b_item.b_item_subgroup_id = b_item_subgroup.b_item_subgroup_id
where b_item_subgroup.f_item_group_id = '4'); 

--ข้อมูลการจับคู่ CHARITEM สามารถจับคู่ให้เบื้องต้น ได้แก่ กลุ่มทันตกรรม ใช้ SQL  
update b_item set r_rp1253_charitem_id = 'D' 
where b_item.b_item_id in 
(select b_item_id from b_item inner join b_item_subgroup on b_item.b_item_subgroup_id = b_item_subgroup.b_item_subgroup_id 
where b_item_subgroup.f_item_group_id = '6'); 

--ข้อมูลการจับคู่ CHARITEM สามารถจับคู่ให้เบื้องต้น ได้แก่ กลุ่มยา ใช้ SQL  
update b_item set r_rp1253_charitem_id = '3' 
where b_item.b_item_id in 
(select b_item_id from b_item inner join b_item_subgroup on b_item.b_item_subgroup_id = b_item_subgroup.b_item_subgroup_id 
where b_item_subgroup.f_item_group_id = '1'); 

--ข้อมูลการจับคู่ CHARITEM สามารถจับคู่ให้เบื้องต้น ได้แก่ กลุ่ม XRay ใช้ SQL   
update b_item set r_rp1253_charitem_id = '8' 
where b_item.b_item_id in 
(select b_item_id from b_item inner join b_item_subgroup on b_item.b_item_subgroup_id = b_item_subgroup.b_item_subgroup_id 
where b_item_subgroup.f_item_group_id = '3'); 

INSERT INTO s_version VALUES ('9701000000034', '34', 'Hospital OS, Community Edition', '3.9.1', '3.17.241108', '2553-02-16 13:20:00');

INSERT INTO s_script_update_log VALUES ('hospitalOS','updateV3_91.sql',(select current_date) || ','|| (select current_time),'ปรับแก้สำหรับ hospitalOS3.9');
