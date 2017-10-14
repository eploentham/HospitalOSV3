--SQL สำหรับการลบรายการ item ทันตกรรมของ Hospital OS
DELETE FROM b_item_subgroup WHERE b_item_subgroup_id >= '1300000000028' AND b_item_subgroup_id <= '1300000000029';
DELETE FROM b_item_subgroup WHERE b_item_subgroup_id >= '1300000000031' AND b_item_subgroup_id <= '1300000000039';
DELETE FROM b_item_subgroup WHERE b_item_subgroup_id >= '1300000000041' AND b_item_subgroup_id <= '1300000000048';

DELETE FROM b_item WHERE b_item_subgroup_id >= '1300000000028' AND b_item_subgroup_id <= '1300000000029';
DELETE FROM b_item WHERE b_item_subgroup_id >= '1300000000031' AND b_item_subgroup_id <= '1300000000039';
DELETE FROM b_item WHERE b_item_subgroup_id >= '1300000000041' AND b_item_subgroup_id <= '1300000000048';

--- เพิ่มข้อมูลของ 16 กลุ่มมาตรฐาน ในตาราง b_item
UPDATE b_item SET b_item_16_group_id = '3120000000013' WHERE b_item_id  >=  '1740000001452' AND b_item_id  <= '1740000001790';
