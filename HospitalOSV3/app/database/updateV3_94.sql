--ต้องปรับเรื่อง drug.item_id index ไม่ให้เป็น unique key 
drop index item_id_drug_key;

ALTER TABLE b_item_lab_result ALTER COLUMN item_lab_result_default TYPE text;

INSERT INTO s_version VALUES ('9701000000037', '37', 'Hospital OS, Community Edition', '3.9.4', '3.18.160410', '2553-07-07 18:20:00');

INSERT INTO s_script_update_log VALUES ('hospitalOS','updateV3_94.sql',(select current_date) || ','|| (select current_time),'ปรับแก้สำหรับ hospitalOS3.9.4');

