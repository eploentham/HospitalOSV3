
-- ข้อมูลเดิม description ผิดอยู่ 
update b_sph_sequence_data set 
sequence_data_description = 'IPD credit number(changpuek module)' 
where b_sph_sequence_data_id = 'ccnipd';

--เก็บ code เพิ่ม 
ALTER TABLE b_sph_doctor_fee_item
	ADD COLUMN doctor_fee_item_code varchar(255) NULL;

-- ปรับให้เพิ่มให้มีข้อมูล Version  By Ojika  วันที่ 5 เมษายน 2553

CREATE TABLE public.s_sph_version ( 
    s_sph_version_id              	varchar(255) NOT NULL,
    version_sph_number            	varchar(255) NULL,
    version_sph_description       	varchar(255) NULL,
    version_sph_application_number	varchar(255) NULL,
    version_sph_database_number   	varchar(255) NULL,
    version_sph_update_time       	varchar(255) NULL,
    PRIMARY KEY(s_sph_version_id)
);

CREATE UNIQUE INDEX s_sph_version_id_pk ON public.s_sph_version(s_sph_version_id);

INSERT INTO s_sph_version VALUES ('shp101000000001', '01', 'SPH(CASH), CHANGPUEK HOSPITAL EDITION', '1.0.0410', '1.0.0410', '2553-04-05 13:51:31');

-- ปรับเพิ่มการสร้างฐานข้อมูลให้มี script log  
-- ที่เคยสร้างไปแล้วก่อนทำ version 3 Files ไม่ต้อง insert ลงไปเพราะว่า เป็นการนำไปใช้โดยที่ยังไม่มีการควบคุมจาก implement 
-- insert into s_script_update_log values ('SPH Module','support_hos_20100326.sql',(select current_date) || ','|| (select current_time),'เริ่มต้นการใช้งาน SPH แต่ไม่มีการเก็บ run script');
-- insert into s_script_update_log values ('SPH Module','update_sph_20100401.sql',(select current_date) || ','|| (select current_time),'ปรับปรุงฐานข้อมูลเก็บข้อมูลใบเสร็จ SPH แต่ไม่มีการเก็บ run script');
-- insert into s_script_update_log values ('SPH Module','update_sph_20100402.sql',(select current_date) || ','|| (select current_time),'แก้ปัญหาการสร้าง index ผิด field  SPH แต่ไม่มีการเก็บ run script');

-- การปรับปรุง Files นี้ 
insert into s_script_update_log values ('SPH Module','update_sph_20100405.sql',(select current_date) || ','|| (select current_time),'เพิ่ม version ของ Module SPH เพื่อเป็น Module ที่สมบูรณ์');
