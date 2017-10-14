-- create t_oppp54_notpass and insert data

CREATE TABLE t_oppp54_notpass (
    t_oppp54_notpass_id	varchar(255) NOT NULL,
    t_visit_id         	varchar(255) NULL,
    person_status      	varchar(1) NULL DEFAULT '0'::character varying,
    person_errcode     	varchar(255) NULL,
    service_status     	varchar(1) NULL DEFAULT '0'::character varying,
    service_errcode    	varchar(255) NULL,
    diag_status        	varchar(1) NULL DEFAULT '0'::character varying,
    diag_errcode       	varchar(255) NULL,
    drug_status        	varchar(1) NULL DEFAULT '0'::character varying,
    drug_errcode       	varchar(255) NULL,
    proced_status      	varchar(1) NULL DEFAULT '0'::character varying,
    proced_errcode     	varchar(255) NULL,
    epi_status         	varchar(1) NULL DEFAULT '0'::character varying,
    epi_errcode        	varchar(255) NULL,
    fp_status          	varchar(1) NULL DEFAULT '0'::character varying,
    fp_errcode         	varchar(255) NULL,
    anc_status         	varchar(1) NULL DEFAULT '0'::character varying,
    anc_errcode        	varchar(255) NULL,
    mch_status         	varchar(1) NULL DEFAULT '0'::character varying,
    mch_errcode        	varchar(255) NULL,
    pp_status          	varchar(1) NULL DEFAULT '0'::character varying,
    pp_errcode         	varchar(255) NULL,
    PRIMARY KEY(t_oppp54_notpass_id)
);

ALTER TABLE "t_oppp54_notpass" ADD CONSTRAINT "t_oppp54_notpass_ukey1" UNIQUE ("t_visit_id");

-- create b_oppp54_errcode
CREATE TABLE b_oppp54_errcode (
    code_id    	varchar(255) NOT NULL,
    description	varchar(255) NULL DEFAULT ''::character varying,
    remark     	varchar(255) NULL DEFAULT ''::character varying,
    hint       	varchar(255) NULL,
    PRIMARY KEY(code_id)
);
-- insert data b_oppp54_errcode
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN9200', 'ไม่สามารถเชื่อมโยงข้อมูลบุคคลได้ (ข้อมูลบุคคลต้องผ่านการตรวจสอบ)', 'ตรวจสอบข้อมูลการให้บริการว่าสามารถเชื่อมโยงกับข้อมูลบุคคลได้หรือไม่', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN9201', 'ไม่มีรหัสหน่วยบริการไม่มีอยู่ใน สนย. (PCUCODE)', 'ตรวจสอบความถูกต้องของรหัสหน่วยบริการที่มีอยู่ใน สนย. (PCUCODE)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN9205', 'ข้อมูลไม่อยู่ในปีงบประมาณที่กำหนด', 'ตรวจสอบข้อมูลวันที่ให้บริการ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN9207', 'ไม่ใช่เพศหญิง  (หรืออาจไม่สามารถเชื่อมโยงข้อมูลใน PERSON ได้)', 'ใส่ข้อมูลเพศเป็นเพศหญิง หรือ ตรวจสอบข้อมูลในแฟ้ม PERSON', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN9240', 'อายุไม่อยูระหว่าง 9-60 ปี', 'ระบุอายุระหว่าง 9-60 ปี', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN9241', 'อายุครรภ์ (GA) ไม่อยู่ระหว่าง 4 - 45 สัปดาห์', 'ระบุอายุครรภ์ (GA) ระหว่าง 4 - 45 สัปดาห์', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN9299', 'ข้อมูลซ้ำซ้อน', 'ตรวจสอบความซ้ำซ้อนของข้อมูล', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX1103', 'ข้อมูล PID มีค่าว่าง (null)', 'ใส่ข้อมูล PID ให้ไม่เป็นค่าว่าง (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX9200', 'ไม่สามารถเชื่อมโยงการให้บริการได้', 'ตรวจสอบข้อมูลการให้บริการว่าสามารถเชื่อมโยงการให้บริการได้หรือไม่ (PCUCODE,PID,SEQ,DATE_SERV,CLINIC)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX9230', 'ไม่มีรหัสโรคใน ICD10 (WHO 2007) หรือ ICD10 TM หรือแพทย์แผนไทย', 'ตรวจสอบรหัสโรคที่ใช้ต้องมีใน  ICD10 (WHO 2007) หรือ ICD10 TM หรือแพทย์แผนไทย', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX9231', 'ไม่มีประเภทการวินิจฉัยโรค (DIAGTYPE) = 1-5', 'ตรวจสอบ DIAGTYPE or DXTYPE  = 1-5', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX9232a', 'เป็นรหัสโรคที่ให้ในเพศหญิง (รหัสนี้ส่วนกลางจะเป็น DX9232 เท่านั้น)', 'ตรวจสอบรหัสโรคให้สอดคล้องตามหลักการให้รหัสโรค (ตาม Appendix A3- A4 ของ DRG 4.0)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX9232b', 'เป็นรหัสโรคที่ให้ในเพศชาย  (รหัสนี้ส่วนกลางจะเป็น DX9232 เท่านั้น)', 'ตรวจสอบรหัสโรคให้สอดคล้องตามหลักการให้รหัสโรค (ตาม Appendix A3- A4 ของ DRG 4.0) ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX9232c', 'อายุไม่สอดคล้องกับอายุที่กำหนดให้ใช้ในรหัสโรคนี้  (รหัสนี้ส่วนกลางจะเป็น DX9232 เท่านั้น)', 'ตรวจสอบรหัสโรคให้สอดคล้องตามหลักการให้รหัสโรค (ตาม Appendix A3- A4 ของ DRG 4.0) DX9232d', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP3103', 'ข้อมูล PID เป็นค่าว่าง', 'ใส่ข้อมูล PID ให้เป็นค่าไม่ว่าง (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3101', 'ข้อมูล PCUCODE ไม่ใช่ 5 หลัก', 'ตรวจสอบข้อมูล PCUCODE ต้องเป็น 5 หลัก', 'Set Up  > HospitalOS Setup  > รายการอื่นๆ >  สถานพยาบาลที่ติดตั้ง > รหัส');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3105', 'DATE_SERV ไม่อยู่ในรูปของ YYYYMMDD และปีไม่เป็นค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูป YYYYMMDD และปีต้องเป็นค.ศ.', 'แถบการรับบริการ > ปุ่ม visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3142', 'ข้อมูล ANCRES  มีค่าไม่เท่ากับ 1 หรือ 2', 'ใส่ข้อมูล ANCRES ให้มีค่าเท่ากับ 1 หรือ 2', 'เมนู ส่งเสริม  > ครอบครัว > แถบ ฝากครรภ์ > การรับบริการฝากครรภ์ >  ข้อมูลอื่นๆ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX1105', 'ข้อมูล DATE_SERV ไม่อยู่ในรูปของ YYYYMMDD และ YYYY ไม่เป็นค.ศ.', 'ใส่ข้อมูล DATE_SERV ให้อยู่ในรูป YYYYMMDD และ YYYY เป็นค.ศ.', 'แถบการรับบริการ > ปุ่ม visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX1130', 'ข้อมูล DIAGCODE มีค่าว่าง (null)', 'ใส่ข้อมูล DIAGCODE ให้ไม่เป็นค่าว่าง (not null)', 'แถบการวินิจฉัย > การลงรหัส ICD-10   >  รหัส ICD10');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP3101', 'ข้อมูล PCUCODE มีไม่ครบ 5 หลัก', 'ตรวจสอบข้อมูล PCUCODE ต้องเป็น 5 หลัก', 'Set Up  > HospitalOS Setup  > รายการอื่นๆ >  สถานพยาบาลที่ติดตั้ง > รหัส');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP3104', 'ข้อมูล SEQ เป็นค่าว่าง (null)', 'ใส่ข้อมูล SEQ ให้เป็นค่าไม่ว่าง (not null)', 'แถบการรับบริการ  > ปุ่ม visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP3105', 'DATE_SERV ไม่อยู่ในรูปของ YYYYMMDD และปีไม่เป็นค.ศ.', 'ใส่ค่าให้อยู่ในรูป YYYYMMDD และปีต้องเป็นค.ศ.', 'แถบการรับบริการ  > ปุ่ม visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP9200', 'ไม่สามารถเชื่อมโยงข้อมูลบุคคลได้ (ข้อมูลบุคคลต้องผ่านการตรวจสอบ)', 'ตรวจสอบข้อมูลการให้บริการว่าสามารถเชื่อมโยงกับข้อมูลบุคคลได้หรือไม่', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP9201', 'ไม่มีรหัสหน่วยบริการอยู่ใน สนย. (PCUCODE)', 'ตรวจสอบความถูกต้องของรหัสหน่วยบริการที่มีอยู่ใน สนย. (PCUCODE)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP9205', 'ข้อมูลไม่อยู่ในปีงบประมาณ', 'ตรวจสอบข้อมูลวันที่ให้บริการ (DATE_SERV)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP9230', 'ไม่มีการให้รหัสวัคซีนตามรหัสมาตรฐาน สนย.', 'ตรวจสอบรหัสวัคซีนตามรหัสมาตรฐาน สนย.', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP9299', 'ข้อมูลซ้ำซ้อน', 'ตรวจสอบความซ้ำซ้อนของข้อมูล', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3103', 'ข้อมูล PID เป็นค่าว่าง', 'ใส่ข้อมูล PID ให้เป็นค่าไม่ว่าง (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP9200', 'ไม่สามารถเชื่อมโยงข้อมูลบุคคลได้ (ข้อมูลบุคคลต้องผ่านการตรวจสอบ)', 'ตรวจสอบข้อมูลการให้บริการว่าสามารถเชื่อมโยงกับข้อมูลบุคคลได้หรือไม่', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP9201', 'ไม่มีรหัสหน่วยบริการไม่มีอยู่ใน สนย. (PCUCODE)', 'ตรวจสอบความถูกต้องของรหัสหน่วยบริการที่มีอยู่ใน สนย. (PCUCODE)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP9205', 'ข้อมูลไม่อยู่ในปีงบประมาณ', 'ตรวจสอบข้อมูลวันที่ให้บริการ (DATE_SERV)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP9207', 'ไม่ใช่เพศหญิง  (หรืออาจไม่สามารถเชื่อมโยงข้อมูลใน PERSON ได้)', 'ใส่ข้อมูลเพศเป็นเพศหญิง หรือ ตรวจสอบข้อมูลในแฟ้ม PERSON', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP9208', 'ไม่ใช่เพศชาย (หรืออาจไม่สามารถเชื่อมโยงข้อมูลใน PERSON ได้)', 'ใส่ข้อมูลเพศเป็นเพศชาย หรือ ตรวจสอบข้อมูลในแฟ้ม PERSON', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP9240', 'อายุไม่อยู่ระหว่าง 9-60 ปี', 'ใส่ข้อมูลอายุระหว่าง 9 - 60 ปี', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP9299', 'มีข้อมูลซ้ำซ้อน', 'ตรวจสอบความซ้ำซ้อนของข้อมูล', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3103', 'ข้อมูล PID เป็นค่าว่าง', 'ใส่ข้อมูล PID ให้เป็นค่าไม่ว่าง (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3130', 'ข้อมูล FPTYPE มีค่าที่ไม่ใช่ 1-7', 'ใส่ข้อมูล DIAGTYPE ให้มีค่า 1-7', 'เมนูส่งเสริม > ครอบครัว >แถบวางแผนครอบครัว  > วิธีการคุมกำเนิด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3101', 'ข้อมูล PCUCODE มีไม่ครบ 5 หลัก', 'ตรวจสอบข้อมูล PCUCODE ต้องเป็น 5 หลัก', 'Set Up  > HospitalOS Setup  > รายการอื่นๆ >  สถานพยาบาลที่ติดตั้ง > รหัส');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3144', 'ข้อมูล LMP ไม่อยู่ในรูปของ YYYYMMDD และปีเกิดไม่เป็นค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูป YYYYMMDD และปีเกิดต้องเป็นค.ศ.', 'เมนูส่งเสริม > ครอบครัว > แถบฝากครรภ์ > วันแรกของการมีประจำเดือนครั้งสุดท้าย');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC9200', 'ไม่สามารถเชื่อมโยงข้อมูลบุคคลได้ (ข้อมูลบุคคลต้องผ่านการตรวจสอบ)', 'ตรวจสอบข้อมูลการให้บริการว่าสามารถเชื่อมโยงกับข้อมูลบุคคลได้หรือไม่', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC9201', 'ไม่มีรหัสหน่วยบริการไม่มีอยู่ใน สนย. (PCUCODE)', 'ตรวจสอบความถูกต้องของรหัสหน่วยบริการที่มีอยู่ใน สนย. (PCUCODE)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC9205', 'ข้อมูลการดูแลแม่ครั้งหลังสุดไม่อยู่ในปีงบประมาณ', 'ตรวจสอบข้อมูลการดูแลแม่ครั้งหลังสุด', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC9207', 'ไม่ใช่เพศหญิง  (หรืออาจไม่สามารถเชื่อมโยงข้อมูลใน PERSON ได้)', 'ใส่ข้อมูลเพศเป็นเพศหญิง หรือ ตรวจสอบข้อมูลในแฟ้ม PERSON', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC9240', 'อายุไม่อยูระหว่าง 9-60 ปี', 'ระบุอายุระหว่าง 9-60 ปี', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC9241', 'ไม่มีวันที่ดูแลแม่ครั้งที่ 1 (PPCARE1)', 'ตรวจสอบวันที่ดูแลแม่ครั้งที่ 1', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC9242', 'วันที่ดูแลเด็กครั้งหลังไม่มากกว่าครั้งแรก', 'ตรวจสอบวันที่ดูแลแม่ในแต่ละครั้ง', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC9299', 'มีข้อมูลซ้ำซ้อน', 'ตรวจสอบความซ้ำซ้อนของข้อมูล', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('OP9200', 'ไม่สามารถเชื่อมโยงข้อมูลบุคคลได้ (ข้อมูลบุคคลต้องผ่านการตรวจสอบ)', 'ตรวจสอบข้อมูลบุคคลในตาราง PERSON ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('OP9201', 'ไม่มีรหัสหน่วยบริการไม่มีอยู่ใน สนย. (PCUCODE)', 'ตรวจสอบข้อมูลการให้บริการว่าสามารถเชื่อมโยงกับข้อมูลบุคคลได้หรือไม่', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('OP9202', 'ไม่มีรหัสหน่วยบริการอยู่ใน สนย.', 'ตรวจสอบข้อมูลใน  PCUCODE ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('OP9205', 'ข้อมูลไม่อยู่ในปีงบประมาณที่กำหนด', 'ตรวจสอบวันที่ให้บริการ (DATE_SERV)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('OP9206', 'รหัสคลินิก (CLINIC) ไม่ถูกต้อง', 'ตรวจสอบข้อมูลคลินิก (CLINIC)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1106', 'ข้อมูล CLINIC มีค่าว่าง (null) หรือไม่ครบ 5 หลัก', 'ใส่ข้อมูล CLINIC ให้ไม่เป็นค่าว่าง (not null) หรือตรวจสอบให้เป็นทศนิยม 2 ตำแหน่ง', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1140', 'ข้อมูล SERVICE_TYPE ไม่ใช่ 1 หรือ 2', 'ใส่ข้อมูล SERVICE_TYPE ให้มีค่า 1 หรือ 2', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('OP9230', 'ไม่มีข้อมูลการวินิจฉัยโรคที่ถูกต้อง', 'ตรวจสอบการให้รหัสการวินิจฉัยโรคให้ถูกต้อง', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('OP9299', 'ข้อมูลซ้ำซ้อน', 'ตรวจสอบความซ้ำซ้อนของข้อมูล', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1103', 'ข้อมูล PID มีค่าว่าง (null)', 'ใส่ข้อมูล PIDให้ไม่เป็นค่าว่าง (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE9203', 'มี PID ซ้ำซ้อน', 'ตรวจสอบข้อมูล PID ในไฟล์ว่ามีการซ้ำซ้อนหรือไม่', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE9200', 'ไม่สามารถเชื่อมโยงกับแฟ้มที่ให้บริการได้', 'ตรวจสอบความเชื่อมโยงกับแฟ้มที่ให้บริการ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1101', 'ข้อมูล PCUCODE ไม่ใช่ 5 หลัก', 'ตรวจสอบข้อมูล PCUCODE ต้องเป็น 5 หลัก', 'Set Up  > HospitalOS Setup  > รายการอื่นๆ >  สถานพยาบาลที่ติดตั้ง > รหัส');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1107', 'ข้อมูล SEX  มีค่าที่ไม่ใช่ 1 หรือ 2', 'ใส่ข้อมูล SEX  ให้มีค่า 1 หรือ 2', 'แถบข้อมูลประชากร > เพศ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1109', 'ข้อมูล BIRTH ไม่อยู่ในรูปของ YYYYMMDD และ YYYY ไม่เป็น ค.ศ.', 'ใส่ข้อมูล BIRTH ให้อยู่ในรูป YYYYMMDD และ YYYY เป็น ค.ศ.', 'แถบข้อมูลประชากร > วันเกิด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX1103', 'ข้อมูล PID มีค่าว่าง (null)', 'ใส่ข้อมูล PID ให้ไม่เป็นค่าว่าง (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX9200', 'ไม่สามารถเชื่อมโยงการให้บริการได้', 'ตรวจสอบข้อมูลการให้บริการว่าสามารถเชื่อมโยงการให้บริการได้หรือไม่', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX9230', 'ไม่มีรหัสหัตถการใน ICD9CM หรือ ICD10TM หรือแพทย์แผนไทย', 'ตรวจสอบรหัสหัตถการให้มี  ICD9CM หรือ ICD10TM หรือแพทย์แผนไทย', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX9299', 'มีข้อมูลซ้ำซ้อน', 'ตรวจสอบความซ้ำซ้อนของข้อมูล', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3109', 'ข้อมูล BIRTH ไม่อยู่ในรูปของ YYYYMMDD และ YYYY ไม่เป็น ค.ศ.', 'ใส่ข้อมูล BIRTH ให้อยู่ในรูป YYYYMMDD และ YYYY เป็น ค.ศ.', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3110', 'ข้อมูล MSTATUS มีค่าที่ไม่ใช่ 1-6 หรือ 9', 'ใส่ค่าข้อมูล MSTATUS ให้มีค่า 1-6 หรือ 9', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3111', 'ข้อมูล OCCUPA มีไม่ครบ 3 หลัก', 'ใส่ข้อมูล OCCUPA ให้ครบ 3 หลัก', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3112', 'ข้อมูล NATION มีไม่ครบ 3 หลัก', 'ใส่ข้อมูล NATION ให้ครบ 3 หลัก', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE9202', 'มี CID ซ้ำซ้อน', 'ตรวจสอบข้อมูล CID ในไฟล์ว่ามีการซ้ำซ้อนหรือไม่', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3103', 'ข้อมูล PID มีค่าว่าง (null)', 'ใส่ข้อมูล PIDให้ไม่เป็นค่าว่าง (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3107', 'ข้อมูล SEX  มีค่าที่ไม่ใช่ 1 หรือ 2', 'ใส่ข้อมูล SEX  ให้มีค่า 1 หรือ 2', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RF9225', 'รหัสสถานพยาบาลที่ส่งไม่มีใน สนย.(REFERINHOS)', 'ตรวจสอบความถูกต้องของรหัสหน่วยบริการที่มีอยู่ใน สนย. (REFERINHOS)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RF9226', 'รหัสสถานพยาบาลที่รับส่งต่อไม่มีใน สนย.(REFEROUHOS)', 'ตรวจสอบความถูกต้องของรหัสหน่วยบริการที่มีอยู่ใน สนย. (REFEROUHOS)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3101', 'ข้อมูล PCUCODE ไม่ใช่ 5 หลัก', 'ตรวจสอบข้อมูล PCUCODE ต้องเป็น 5 หลัก', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3102', 'ข้อมูล CID ไม่ใช่ 13 หลัก', 'ใส่ข้อมูล CID ให้เป็น 13 หลัก', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1103', 'ข้อมูล PID มีค่าว่าง (null)', 'ใส่ข้อมูล PID ให้ไม่เป็นค่าว่าง (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX9200', 'ไม่สามารถเชื่อมโยงการให้บริการได้', 'ตรวจสอบข้อมูลการให้บริการว่าสามารถเชื่อมโยงการให้บริการได้หรือไม่', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3131', 'ข้อมูล APLACE เป็นค่าว่าง (null)', 'ใส่ข้อมูล ACPLACE ให้เป็นค่าไม่ว่าง (not null)', 'Set up > HospitalOS Setup  > รายการอื่นๆ >  สถานพยาบาลที่ติดตั้ง > รหัส');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3131', 'ข้อมูล FPPLACE เป็นค่าว่าง', 'ใส่ข้อมูล FPPLACE ให้เป็นค่าไม่ว่าง (not null)', 'Set up > HospitalOS Setup  > รายการอื่นๆ >  สถานพยาบาลที่ติดตั้ง > รหัส');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1106', 'ข้อมูล CLINIC มีค่าว่าง (null) หรือไม่ครบ 5 หลัก', 'ใส่ข้อมูล CLINIC ให้ไม่เป็นค่าว่าง (not null) หรือตรวจสอบให้เป็นทศนิยม 2 ตำแหน่ง', 'แถบการวินิจฉัย  > การลงรหัส ICD-10  > ประเภทโรค');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1101', 'ข้อมูล PCUCODE ไม่ใช่ 5 หลัก', 'ตรวจสอบข้อมูล PCUCODE ต้องเป็น 5 หลัก', 'Set up > HospitalOS Setup  > รายการอื่นๆ >  สถานพยาบาลที่ติดตั้ง > รหัส');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX9230', 'ไม่มีหรือการให้รหัสยาถูกต้องตามมาตรฐาน 24 หลัก (DIDSTD)', 'ตรวจสอบรหัสยามาตรฐาน 24 หลัก', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX9299', 'ข้อมูลซ้ำซ้อน', 'ตรวจสอบความซ้ำซ้อนของข้อมูล', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1103', 'ข้อมูล PID มีค่าว่าง (null)', 'ใส่ข้อมูล PID ให้ไม่เป็นค่าว่าง (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM3101', 'ข้อมูล PCUCODE มีไม่ครบ 5 หลัก', 'ตรวจสอบข้อมูล PCUCODE ต้องเป็น 5 หลัก', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM3102', 'ข้อมูล CID มีไม่ครบ 13 หลัก', 'ใส่ข้อมูล CID  ให้ครบ 13 หลัก', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM3112', 'ข้อมูล D_UPDATE ไม่อยู่ในรูปของ YYYYMMDD และปีเกิดไม่เป็นค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูป YYYYMMDD และปีเกิดต้องเป็นค.ศ.', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM3130', 'ข้อมูล FTYPE มีค่าที่ไม่ใช่ 0-7', 'ใส่ข้อมูล FTYPE ให้มีค่า 0-7', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM3143', 'ข้อมูล NUMSON เป็นค่าว่าง (null)', 'ใส่ข้อมูล NUMSON ให้เป็นค่าไม่ว่าง (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM9200', 'ไม่สามารถเชื่อมโยงข้อมูลบุคคลได้', 'ตรวจสอบข้อมูลการให้บริการว่าสามารถเชื่อมโยงกับข้อมูลบุคคลได้หรือไม่', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM9201', 'ไม่มีรหัสหน่วยบริการไม่มีอยู่ใน สนย. (PCUCODE)', 'ตรวจสอบความถูกต้องของรหัสหน่วยบริการที่มีอยู่ใน สนย. (PCUCODE)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM9202', 'ไม่มีเลขประชาชนในฐานทะเบียนราษฎร์', 'ระบุเลขประชาชนในฐานทะเบียนราษฎร์', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM9207', 'ไม่ใช่เพศหญิง  (หรืออาจไม่สามารถเชื่อมโยงข้อมูลใน PERSON ได้)', 'ใส่ข้อมูลเพศเป็นเพศหญิง หรือ ตรวจสอบข้อมูลในแฟ้ม PERSON', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM9240', 'อายุไม่อยูระหว่าง 9-60 ปี', 'ระบุอายุระหว่าง 9-60 ปี', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM9241', 'ไม่มีสาเหตุการไม่คุมกำเนิด', 'ระบุรหัสการคุมกำเนิดให้มีค่า 1 หรือ 2 หรือ 3', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM9242', 'ข้อมูลไม่อยู่ในปีงบประมาณ 2553', 'ระบุข้อมูลให้อยู่ในปีงบประมาณ 2553', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM9299', 'มีข้อมูลซ้ำซ้อน', 'ตรวจสอบความซ้ำซ้อนของข้อมูล', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3140', 'ข้อมูล TYPEAREA มีค่าที่ไม่ใช่ 0-4', 'ใส่ค่าข้อมูลTYPEAREA ให้มีค่า 0-4', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX9299', 'มีข้อมูลการให้รหัสวินิจฉัยโรคซ้ำซ้อน', 'ตรวจสอบความซ้ำซ้อนของข้อมูล', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3102', 'ข้อมูล CID มีไม่ครบ 13 หลัก', 'ใส่ข้อมูล CID  ให้ครบ 13 หลัก', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP9200', 'ไม่สามารถเชื่อมโยงข้อมูลบุคคลได้ (ข้อมูลบุคคลต้องผ่านการตรวจสอบ)', 'ตรวจสอบข้อมูลการให้บริการว่าสามารถเชื่อมโยงกับข้อมูลบุคคลได้หรือไม่', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP9201', 'ไม่มีรหัสหน่วยบริการไม่มีอยู่ใน สนย. (PCUCODE)', 'ตรวจสอบความถูกต้องของรหัสหน่วยบริการที่มีอยู่ใน สนย. (PCUCODE)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP9205', 'ข้อมูลการดูแลเด็กครั้งหลังสุดไม่อยู่ในปีงบประมาณ', 'ตรวจสอบข้อมูลการดูแลเด็กครั้งหลังสุด', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP9240', 'ข้อมูลน้ำหนัก (BWEIGHT) มีค่าน้อยกว่า 500 กรัม ', 'ใส่ข้อมูลน้ำหนัก (BWEIGHT) ให้มีค่ามากกว่า 500 กรัม', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP9241', 'ไม่มีวันที่ดูแลgเด็กครั้งที่ 1 (BCARE1)', 'ตรวจสอบวันที่ดูแลเด็กครั้งที่ 1', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP9242', 'วันที่ดูแลเด็กครั้งหลังไม่มากกว่าครั้งแรก', 'ตรวจสอบวันที่ดูแลลูกในแต่ละครั้ง', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP9299', 'มีข้อมูลซ้ำซ้อน', 'ตรวจสอบความซ้ำซ้อนของข้อมูล', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3103', 'ไม่มี PID', 'ตรวจสอบข้อมูล PID', '');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3104', 'ไม่มีข้อมูล SEQ หรือเป็นค่าว่าง', 'ใส่ข้อมูล SEQ  ให้เป็นค่าไม่ว่าง (not null)', 'แถบการรับบริการ > ปุ่ม visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3141', 'ข้อมูล GA เป็นค่าว่าง', 'ใส่ข้อมูล GA  ให้เป็นค่าไม่ว่าง (not null)', 'เมนู ส่งเสริม  > ครอบครัว > แถบ ฝากครรภ์  ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3144', 'ข้อมูล GRAVIDA ความยาวเกิน', 'ใส่ข้อมูล GRAVIDA ไม่เกิน 2 หลัก', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3145', 'ข้อมูล ANCNO ความยาวเกิน', 'ใส่ข้อมูล ANCNO ไม่เกิน 1 หลัก', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX1101', 'ข้อมูล PCUCODE ไม่ใช่ 5 หลัก', 'ตรวจสอบข้อมูล PCUCODE ต้องเป็น 5 หลัก', 'Set Up  > HospitalOS Setup  > รายการอื่นๆ >  สถานพยาบาลที่ติดตั้ง > รหัส');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX1104', 'ข้อมูล SEQ มีค่าว่าง (null)', 'ใส่ข้อมูล SEQ ให้ไม่เป็นค่าว่าง (not null)', 'แถบการรับบริการ > ปุ่ม visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX1131', 'ข้อมูล DIAGTYPE มีค่าที่ไม่ใช่ 1-5', 'ใส่ข้อมูล DIAGTYPE ให้มีค่า 1-5', 'แถบการวินิจฉัย > การลงรหัส ICD-10   >  รหัส ICD10');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX1106', 'ข้อมูล CLINIC มีค่าว่าง (null) หรือไม่ครบ 5 หลัก', 'ใส่ข้อมูล CLINIC ให้ไม่เป็นค่าว่าง (not null) หรือตรวจสอบให้เป็นทศนิยม 2 ตำแหน่ง', 'แถบการวินิจฉัย  >การลงรหัส ICD-10   >  ประเภทโรค');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP3131', 'ข้อมูล VCCPLACE เป็นค่าว่าง (null)', 'ใส่ข้อมูล VCCPLACE ให้เป็นค่าไม่ว่าง (not null)', 'Set Up  > HospitalOS Setup  > รายการอื่นๆ >  สถานพยาบาลที่ติดตั้ง > รหัส)');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3101', 'ข้อมูล PCUCODE มีไม่ครบ 5 หลัก', 'ตรวจสอบข้อมูล PCUCODE ต้องเป็น 5 หลัก', 'Set Up  > HospitalOS Setup  > รายการอื่นๆ >  สถานพยาบาลที่ติดตั้ง > รหัส');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3104', 'ข้อมูล SEQ เป็นค่าว่าง (null)', 'ใส่ข้อมูล SEQ ให้เป็นค่าไม่ว่าง (not null)', 'แถบการรับบริการ  > ปุ่ม visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3105', 'DATE_SERV ไม่อยู่ในรูปของ YYYYMMDD และปีเกิดไม่เป็นค.ศ.', 'ใส่ค่าให้อยู่ในรูป YYYYMMDD และปีเกิดต้องเป็นค.ศ.', 'แถบการรับบริการ  > ปุ่ม visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3143', 'ข้อมูล SEQ เป็นค่าว่าง (null)', 'ใส่ข้อมูล SEQ ให้เป็นค่าไม่ว่าง (not null)', 'แถบการรับบริการ  > ปุ่ม visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3157', 'ข้อมูล PPCARE1 ไม่อยู่ในรูปของ YYYYMMDD และปีเกิดไม่เป็นค.ศ.', 'ใส่ข้อมูล ให้อยู่ในรูป YYYYMMDD และปีเกิดต้องเป็นค.ศ.', 'เมนูส่งเสริม > ครอบครัว > แถบข้อมูลการคลอด > ดูแลแม่หลังคลอด ครั้งที่ 1');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX1130', 'ข้อมูล PROCED มีค่าว่าง (null)', 'ใส่ข้อมูล PROCED ให้ไม่เป็นค่าว่าง (not null)', 'แถบการวินิจฉัย  > การลงรหัส ICD-9  > รหัส ICD9');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP3130', 'ข้อมูล VCCTYPE เป็นค่าว่าง  (null)', 'ใส่ข้อมูล VCCTYPE ให้เป็นค่าไม่ว่าง (not null)', 'Set up  > ส่งเสริม  > รายการวัคซีน >  รหัสสำหรับออกรายงาน');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3158', 'ข้อมูล PPCARE2 ไม่อยู่ในรูปของ YYYYMMDD และปีเกิดไม่เป็นค.ศ.', 'ใส่ข้อมูล ให้อยู่ในรูป YYYYMMDD และปีเกิดต้องเป็นค.ศ.', 'เมนูส่งเสริม > ครอบครัว > แถบข้อมูลการคลอด > ดูแลแม่หลังคลอด ครั้งที่ 2');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3159', 'ข้อมูล PPCARE3 ไม่อยู่ในรูปของ YYYYMMDD และปีเกิดไม่เป็นค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูป YYYYMMDD และปีเกิดต้องเป็นค.ศ.', 'เมนูส่งเสริม > ครอบครัว > แถบข้อมูลการคลอด > ดูแลแม่หลังคลอด ครั้งที่ 3');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3160', 'ข้อมูล PPRES มีค่าที่ไม่เท่ากับ 1 หรือ 2', 'ใส่ข้อมูล PPRES ให้มีค่าเท่ากับ 1 หรือ 2', 'เมนูส่งเสริม > ครอบครัว > แถบข้อมูลการคลอด > ผลการตรวจ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3145', 'ข้อมูล EDC ไม่อยู่ในรูปของ YYYYMMDD และปีเกิดไม่เป็นค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูป YYYYMMDD และปีเกิดต้องเป็นค.ศ.', 'เมนูส่งเสริม > ครอบครัว > แถบฝากครรภ์ > วันที่กำหนดคลอด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3146', 'ข้อมูล VDRL_RS มีค่าไม่ใช่ 1-2 และ 8-9', 'ใส่ข้อมูล VDRL_RS ให้มีค่า 1-2 และ 8-9', 'เมนูส่งเสริม > ครอบครัว > แถบฝากครรภ์ > การรับการฝากครรภ์ > ผล VDRL');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3147', 'ข้อมูล HB_RS มีค่าไม่ใช่ 1-2 และ 8-9', 'ใส่ข้อมูล HB_RS ให้มีค่า 1-2 และ 8-9', 'เมนูส่งเสริม > ครอบครัว > แถบฝากครรภ์ > การรับการฝากครรภ์ > ผล HB');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3148', 'ข้อมูล THALASS มีค่าไม่ใช่ 1-2 และ 8-9', 'ใส่ข้อมูลTHALASS ให้มีค่า 1-2 และ 8-9', 'เมนูส่งเสริม > ครอบครัว > แถบฝากครรภ์ > การรับการฝากครรภ์ > ผล THALASS');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3149', 'ข้อมูล DEATAL มีค่าไม่ใช่ 0 หรือ 1', 'ใส่ข้อมูล DEATAL ให้มีค่า 0 และ 1', 'เมนูส่งเสริม > ครอบครัว > แถบฝากครรภ์ > การรับการฝากครรภ์ > ตรวจสุขภาพฟัน');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3150', 'ข้อมูล TCARIES เป็นค่าว่าง (null)', 'ใส่ข้อมูล TCARIES ให้เป็นค่าไม่ว่าง (not null)', 'เมนูส่งเสริม > ครอบครัว > แถบฝากครรภ์ > การรับการฝากครรภ์ > ฟันผุ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3151', 'ข้อมูล TARTAR มีค่าไม่ใช่ 0 หรือ 1 หรือ 8 ', 'ใส่ข้อมูล TARTAR ให้มีค่า 0 หรือ 1 หรือ 8', 'เมนูส่งเสริม > ครอบครัว > แถบฝากครรภ์ > การรับการฝากครรภ์ >มีหินน้ำลาย');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3152', 'ข้อมูล GUMINF มีค่าไม่ใช่ 0 หรือ 1 หรือ 8', 'ใส่ข้อมูล GUMINF ให้มีค่า 0 หรือ 1 หรือ 8', 'เมนูส่งเสริม > ครอบครัว > แถบฝากครรภ์ > การรับการฝากครรภ์ > เหงือกอักเสบ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3153', 'ข้อมูล BDATE ไม่อยู่ในรูปของ YYYYMMDD และปีเกิดไม่เป็นค.ศ.', 'ใส่ข้อมูล ให้อยู่ในรูป YYYYMMDD และปีเกิดต้องเป็นค.ศ.', 'เมนูส่งเสริม > ครอบครัว > แถบข้อมูลการคลอด >วันที่คลอด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3154', 'ข้อมูล BPLACE มีค่าที่ไม่ใช่ 1-5 ', 'ใส่ข้อมูล BPLACE ให้มีค่า 1-5 ', 'เมนูส่งเสริม > ครอบครัว > แถบ ข้อมูลการคลอด >สถานที่คลอด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3156', 'ข้อมูล BDOCTOR มีค่าที่ไม่ใช่ 1-5', 'ใส่ข้อมูล BDOCTOR ให้มีค่า 1-5', 'เมนูส่งเสริม > ครอบครัว > แถบข้อมูลการคลอด > ประเภทผู้ทำคลอด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1110', 'ข้อมูล MSTATUS มีค่าที่ไม่ใช่ 1-6 หรือ 9', 'ใส่ค่าข้อมูล MSTATUS ให้มีค่า 1-6 หรือ 9', 'แถบข้อมูลประชากร > สถานะสมรส');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1111', 'ข้อมูล OCCUPA มีไม่ครบ 3 หลัก', 'ใส่ข้อมูล OCCUPA ให้ครบ 3 หลัก', 'แถบข้อมูลประชากร >  อาชีพ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1112', 'ข้อมูล NATION มีไม่ครบ 3 หลัก', 'ใส่ข้อมูล NATION ให้ครบ 3 หลัก', 'แถบข้อมูลประชากร > สัญชาติ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1140', 'ข้อมูล TYPEAREA มีค่าที่ไม่ใช่ 0-4', 'ใส่ค่าข้อมูลTYPEAREA ให้มีค่า 0-4', 'แถบข้อมูลประชากร > สถานะบุคคล');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX1101', 'ข้อมูล PCUCODE ไม่ใช่ 5 หลัก', 'ตรวจสอบข้อมูล PCUCODE ต้องเป็น 5 หลัก', 'Set Up  > HospitalOS Setup  > รายการอื่นๆ >  สถานพยาบาลที่ติดตั้ง > รหัส');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX1104', 'ข้อมูล SEQ มีค่าว่าง (null)', 'ใส่ข้อมูล SEQ ให้ไม่เป็นค่าว่าง (not null)', 'แถบการรับบริการ > ปุ่ม visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX1105', 'ข้อมูล DATE_SERV ไม่อยู่ในรูปของ YYYYMMDD และ YYYY ไม่เป็นค.ศ.', 'ใส่ข้อมูล DATE_SERV ให้อยู่ในรูป YYYYMMDD และ YYYY เป็นค.ศ.', 'แถบการรับบริการ > ปุ่ม visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX1106', 'ข้อมูล CLINIC มีค่าว่าง (null) หรือไม่ครบ 5 หลัก', 'ใส่ข้อมูล CLINIC ให้ไม่เป็นค่าว่าง (not null) หรือตรวจสอบให้เป็นทศนิยม 2 ตำแหน่ง', 'แถบการวินิจฉัย  > การลงรหัส ICD-9  >  ประเภทโรค');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1104', 'ข้อมูล SEQ มีค่าว่าง (null)', 'ใส่ข้อมูล SEQ ให้ไม่เป็นค่าว่าง (not null)', 'แถบการรับบริการ  > ปุ่ม visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1105', 'ข้อมูล DATE_SERV ไม่อยู่ในรูปของ YYYYMMDD และ YYYY ไม่เป็นค.ศ.', 'ใส่ข้อมูล DATE_SERV ให้อยู่ในรูป YYYYMMDD และ YYYY เป็นค.ศ.', 'แถบการรับบริการ  > ปุ่ม visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1120', 'ข้อมูล DRUGPRICE มีค่าว่าง (null) หรือไม่เป็นทศนิยม 2 ตำแหน่ง', 'ใส่ข้อมูล DRUGPRICE ให้ไม่เป็นค่าว่าง (not null) หรือตรวจสอบให้เป็นทศนิยม 2 ตำแหน่ง', 'Set up > HospitalOS Setup  > รายการตรวจรักษา  > แถบราคา >  ราคาขาย (บาท)');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1121', 'ข้อมูล DRUGCOST มีค่าว่าง (null) หรือไม่เป็นทศนิยม 2 ตำแหน่ง', 'ใส่ข้อมูล DRUGCOST ให้ไม่เป็นค่าว่าง (not null) หรือตรวจสอบให้เป็นทศนิยม 2 ตำแหน่ง', 'Set up > HospitalOS Setup   > รายการตรวจรักษา  > แถบราคา  >  ราคาทุน (บาท)');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1130', 'ข้อมูล DIDSTD มีไม่ครบ 24 หลัก', 'ใส่ข้อมูล DIDSTD ให้ครบ 24 หลัก', 'Set up > HospitalOS Setup  > รายงาน 18/2553  > จับคู่ยา 24 หลัก');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1131', 'ข้อมูล AMOUNT มีค่าว่าง (null)', 'ใส่ข้อมูล AMOUNT ให้ไม่เป็นค่าว่าง (not null)', 'แถบรายการตรวจ / รักษา > ปริมาณ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1101', 'ข้อมูล PCUCODE ไม่ใช่ 5 หลัก', 'ตรวจสอบข้อมูล PCUCODE ต้องเป็น 5 หลัก', 'Set Up  > HospitalOS Setup  > รายการอื่นๆ >  สถานพยาบาลที่ติดตั้ง > รหัส');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1104', 'ข้อมูล SEQ มีค่าว่าง (null)', 'ใส่ข้อมูล SEQ ให้ไม่เป็นค่าว่าง (not null)', 'แถบการรับบริการ > ปุ่ม visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1105', 'ข้อมูล DATE_SERV ไม่อยู่ในรูปของ YYYYMMDD และ YYYY ไม่เป็นค.ศ.', 'ใส่ข้อมูล DATE_SERV ให้อยู่ในรูป YYYYMMDD และ YYYY เป็นค.ศ.', 'แถบการรับบริการ > ปุ่ม visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1120', 'ข้อมูล PRICE มีค่าว่าง (null) หรือไม่เป็นทศนิยม 2 ตำแหน่ง', 'ใส่ข้อมูล PRICE ให้ไม่เป็นค่าว่าง (not null) หรือตรวจสอบให้เป็นทศนิยม 2 ตำแหน่ง', 'แถบการเงิน ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1121', 'ข้อมูล PAY มีค่าว่าง (null) หรือไม่เป็นทศนิยม 2 ตำแหน่ง', 'ใส่ข้อมูล PAY ให้ไม่เป็นค่าว่าง (not null)', 'แถบการเงิน ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1125', 'ข้อมูล REFERIN  มีค่าที่ไม่ใช่ 0 หรือ 1', 'ใส่ข้อมูล REFERIN ให้มีค่า 0 หรือ 1', 'เมนูเครื่องมือ  > การ refer ผู้ป่วย > Refer In');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1126', 'ข้อมูล REFINHOS มีไม่ครบ 5 หลัก', 'ใส่ข้อมูล REFINHOS ให้ครบ 5 หลัก', 'เมนูเครื่องมือ  > การ refer ผู้ป่วย > สถานพยาบาล');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1127', 'ข้อมูล REFEROUT  มีค่าที่ไม่ใช่ 0 หรือ 1', 'ใส่ข้อมูล REFEROUT ให้มีค่า 0 หรือ 1', 'เมนูเครื่องมือ > การ refer ผู้ป่วย  > Refer Out');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1128', 'ข้อมูล REFOUHOS มีไม่ครบ 5 หลัก', 'ใส่ข้อมูล REFOUHOS ให้ครบ 5 หลัก', 'เมนูเครื่องมือ  > การ refer ผู้ป่วย  > สถานพยาบาล');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3101', 'ข้อมูล PCUCODE มีไม่ครบ 5 หลัก', 'ตรวจสอบข้อมูล PCUCODE ต้องเป็น 5 หลัก', 'Set Up  > HospitalOS Setup  > รายการอื่นๆ >  สถานพยาบาลที่ติดตั้ง > รหัส');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3143', 'ข้อมูล GRAVIDA เป็นค่าว่าง (null)', 'ใส่ข้อมูล GRAVIDA ให้เป็นค่าไม่ว่าง (not null)', 'เมนูส่งเสริม > ครอบครัว > แถบข้อมูลการคลอด > ครรภ์ที่');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3144', 'ข้อมูล BPLACE มีค่าที่ไม่ใช่ 1-5 ', 'ใส่ข้อมูล BPLACE ให้มีค่า 1-5 ', 'เมนูส่งเสริม > ครอบครัว >แถบข้อมูลการคลอด >สถานที่คลอด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3145', 'ข้อมูล BTYPE มีค่าที่ไม่ใช่ 1-5', 'ใส่ข้อมูล BTYPE ให้มีค่า 1-5', 'เมนูส่งเสริม > ครอบครัว >แถบข้อมูลการคลอด > วิธีการคลอด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3146', 'ข้อมูล BDOCTOR มีค่าที่ไม่ใช่ 1-5', 'ใส่ข้อมูล BDOCTOR ให้มีค่า1-5', 'เมนูส่งเสริม > ครอบครัว >แถบข้อมูลการคลอด > ประเภทของผู้ทำคลอด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3148', 'ข้อมูล BCARE1 ไม่อยู่ในรูปของ YYYYMMDD และปีเกิดไม่เป็นค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูป YYYYMMDD และปีเกิดต้องเป็นค.ศ.', 'เมนูส่งเสริม > ครอบครัว >แถบข้อมูลการคลอด > ดูแลลูกครั้งที่ 1 ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3149', 'ข้อมูล BCARE2 ไม่อยู่ในรูปของ YYYYMMDD และปีเกิดไม่เป็นค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูป YYYYMMDD และปีเกิดต้องเป็นค.ศ.', 'เมนูส่งเสริม > ครอบครัว >แถบข้อมูลการคลอด > ดูแลลูกครั้งที่ 2');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3150', 'ข้อมูล BCARE3 ไม่อยู่ในรูปของ YYYYMMDD และปีเกิดไม่เป็นค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูป YYYYMMDD และปีเกิดต้องเป็นค.ศ.', 'เมนูส่งเสริม > ครอบครัว >แถบข้อมูลการคลอด > ดูแลลูกครั้งที่ 3');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3151', 'ข้อมูล BCRES มีค่าที่ไม่ใช่ 1 หรือ 2', 'ใส่ข้อมูล BCRES ให้มีค่า 1 หรือ 2', 'เมนูส่งเสริม > ครอบครัว >แถบข้อมูลการคลอด > ผลการตรวจทารกหลังคลอด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3140', 'ข้อมูล BWEIGHT เป็นค่าว่าง (null)', 'ใส่ข้อมูล BWEIGHT ให้เป็นค่าไม่ว่าง (not null)', 'เมนูส่งเสริม > ครอบครัว >แถบข้อมูลการคลอด >น้ำหนักแรกคลอด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1141', 'ข้อมูล PRENAME ไม่ใช่ 20 หลัก', 'ใส่ข้อมูล PRENAME ให้ครบ 20 หลัก', 'แถบข้อมูลประชากร > คำนำหน้า');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1142', 'ข้อมูล FSTATUS มีค่าที่ไม่ใช่ 1 หรือ 2 ', 'ใส่ข้อมูล FSTATUS ให้มีค่า 1 หรือ 2', 'แถบข้อมูลประชากร > ปุ่ม FM');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1143', 'ข้อมูล FATHER ไม่ใช่ 13 หลัก', 'ใส่ข้อ FATHER ให้ครบ 13 หลัก', 'แถบข้อมูลประชากร > ชื่อบิดา');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1144', 'ข้อมูล MOTHER ไม่ใช่ 13 หลัก', 'ใส่ข้อมูล MOTHER ให้ครบ 13 หลัก', 'แถบข้อมูลประชากร > มารดา');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1145', 'ข้อมูล COUPLE ไม่ใช่ 13 หลัก', 'ใส่ข้อมูล COUPLE ให้ครบ 13 หลัก', 'แถบข้อมูลประชากร > คู่สมรส');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1146', 'ข้อมูล MOVEIN ไม่อยู่ในรูปแบบ YYYYMMDD และปีไม่เป็น ค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูป YYYYMMDD และปีต้องเป็น ค.ศ.', 'แถบข้อมูลประชากร > ปุ่ม FM');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1147', 'ข้อมูล BGROUP มีค่าที่ไม่ใช่ 1-4', 'ใส่ข้อมูล BGOUP ให้มีค่า 1-4', 'แถบข้อมูลประชากร > หมู่เลือด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1148', 'ข้อมูล HOUSE ไม่ใช่ 75 หลัก', 'ใส่ข้อมูล HOUSE ให้ครบ 75 หลัก', 'แถบข้อมูลประชากร > บ้านเลขที่');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1149', 'ข้อมูล VILLAGE ไม่ใช่ 2 หลัก', 'ใส่ข้อมูล VILLAGE ให้ครบ 2 หลัก', 'แถบข้อมุลประชากร > หมู่ที่');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1141', 'ข้อมูล LOCATE มีค่าที่ไม่ใช่ 1 หรือ 2', 'ใส่ข้อมูล LOCATE ให้มีค่า 1 หรือ 2', 'การรับบริการ  > ประเภท visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1142', 'ข้อมูล PTTYPE มีค่าที่ไม่ใช่ 0-2', 'ใส่ข้อมูล PTTYPE ให้มีค่า 0-2', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1143', 'ข้อมูล INTIME มีค่าที่ไม่ใช่ 1 หรือ 2', 'ใส่ข้อมูล INTIME ให้มีค่า 1 หรือ 2', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1144', 'ข้อมูล INSTYPE ไม่ใช่ 2 หลัก', 'ใส่ข้อมูล INSTYPE ให้ครบ 2 หลัก', 'การรับบริการ > ข้อมูลการรับบริการ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1151', 'ข้อมูล VHID ไม่ใช่ 8 หลัก', 'ใส่ข้อมูล VHID ให้ครบ 8 หลัก', 'เมนูส่งเสริม > ข้อมูลหมู่บ้าน > รหัสหมู่บ้าน');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1145', 'ข้อมูล INSID ไม่ใช่ 18 หลัก', 'ใส่ข้อมูล INSID ให้ครบ 18 หลัก', 'การรับบริการ > เลขที่บัตร');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1146', 'ข้อมูล MAIN ไม่ใช่ 5 หลัก', 'ใส่ข้อมูล MAIN ให้ครบ 5 หลัก', 'แถบการรับบริการ > สถานพยาบาลหลัก');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1148', 'ข้อมูล COST ราคารวมต้องมากกว่า 0 และมีเลขทศนิยมหลังจุด 2 ตำแหน่ง', 'ใส่ข้อมูล COST มากกว่า 0 และเลขทศนิยมหลังจุดเป็น 2 ตำแหน่ง', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX1132', 'ข้อมูล D_UPDATE ไม่อยู่ในรูปของ YYYYMMDDHHMMSS และปีไม่เป็น ค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูปแบบ YYYYMMDDHHMMSS และต้องเป็นปี ค.ศ.', 'แถบการวินิจฉัย > ปุ่มบันทึก');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1147', 'ข้อมูล D_UPDATE ไม่อยู่ในรูปของ YYYYMMDDHHMMSS และปีไม่เป็น ค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูป YYYYMMDDHHMMSS และปีเป็น ค.ศ.', 'แถบการรับบริการ >ปุ่มบันทึก');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX1131', 'ข้อมูล SERVPRIC ไม่ใช่ทศนิยม 2 ตำแหน่ง และมีค่าน้อยกว่า 0', 'ใส่ข้อมูลให้เป็นทศนิยม 2 ตำแหน่งและมีค่ามากกว่า 0', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX1132', 'ข้อมูล D_UPDATE ไม่อยู่ในรูปของ YYYYMMDDHHMMSS และปีไม่เป็น ค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูป YYYYMMDDHHMMSS และปีต้องเป็น ค.ศ.', 'แถบการวินิจฉัย >การลง icd9>กดปุ่มบันทึก');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3106', 'ข้อมูล DID ไม่ใช่ 24 หลัก', 'ใส่ข้อมูล DID ให้ครบ 24 หลัก', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3107', 'ข้อมูล AMOUNT เป็นค่าว่าง', 'ใส่ข้อมูล AMOUNT ให้ไม่เป็นค่าว่าง หากไม่มีใส่ 0', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3108', 'ข้อมูล D_UPDATE ไม่อยู่ในรูปของ YYYYMMDDHHMMSS และปีไม่เป็น ค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูปแบบ YYYYMMDDHHMMSS และต้องเป็นปี ค.ศ.', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP3132', 'ข้อมูล D_UPDATE ไม่อยู่ในรูปของ YYYYMMDDHHMMSS และปีไม่เป็น ค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูปแบบ YYYYMMDDHHMMSS และต้องเป็นปี ค.ศ.', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3152', 'ข้อมูล PID มีค่าว่าง', 'ใส่ข้อมูล PID ให้ไม่เป็นค่าว่าง', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3153', 'ข้อมูล MPID มีค่าว่าง', 'ใส่ข้อมูล MPID ให้ไม่เป็นค่าว่าง', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3154', 'ข้อมูล BDATE ไม่อยู่ในรูปของ YYYYMMDD และปีไม่เป็น ค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูปแบบ YYYYMMDD และต้องเป็นปี ค.ศ.', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1102', 'ข้อมูล CID ไม่ใช่ 13 หลัก', 'ใส่ข้อมูล CID ให้เป็น 13 หลัก', 'แถบข้อมูลประชากร > เลขบัตรประชาชน');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3156', 'ข้อมูล BTYPE ไม่ใช่ค่า 1-5', 'ใส่ข้อมูล BTYPE ให้มีค่า 1-5', 'ส่งเสริม > ครอบครัว > ข้อมูลการคลอด > วิธีการคลอด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3157', 'ข้อมูล ASPHYXIA ไม่ใช่ค่า 0 หรือ 1', 'ใส่ข้อมูล ASPHYXIA ให้มีค่า 0 หรือ 1', 'ส่งเสริม > ครอบครัว > ข้อมูลเด็กทารก > ภาวะขาดออกซิเจน');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3158', '-ข้อมูล VITK ไม่ใช่ค่า 0 หรือ 1', 'ใส่ข้อมูล VITK ให้มีค่า 0 หรือ 1', 'ส่งเสริม > ครอบครัว > ข้อมูลเด็กทารก > รับ Vit K');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3161', 'ข้อมูล GRAVIDA เป็นค่าว่าง', 'ใส่ข้อมูล GRAVIDA ไม่เป็นค่าว่าง', 'เมนูส่งเสริม > ครอบครัว > แถบฝากครรภ์ > ครรภ์ที่');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3162', 'ข้อมูล HIV_RS มีค่าไม่ใช่ 1-2 หรือ 8-9', 'ใส่ข้อมูล HIV_RS ให้มีค่า 1-2 หรือ 8-9', 'เมนูส่งเสริม > ครอบครัว > แถบฝากครรภ์ > การรับการฝากครรภ์ > ผล HIV');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3164', 'ข้อมูล HCT_RS เป็นค่าว่าง ', 'ใส่ข้อมูล HCT_RS ไม่เป็นค่าว่าง', 'เมนูส่งเสริม > ครอบครัว > แถบฝากครรภ์ > การรับการฝากครรภ์ >ผล HCT');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3165', 'ข้อมูล BRESULT ไม่ใช่ 6 หลัก', 'ใส่ข้อมูล BRESULT ให้ครบ 6 หลัก', 'เมนูส่งเสริม > ครอบครัว > แถบข้อมูลการคลอด > ผลสิ้นสุดการตั้งครรภ์');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3166', 'ข้อมูล BHOSP ไม่ใช่ 5 หลัก', 'ใส่ข้อมูล BHOSP ให้ครบ 5 หลัก', 'เมนูส่งเสริม > ครอบครัว > แถบข้อมูลการคลอด > สถานที่คลอด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3167', 'ข้อมูล LBORN เป็นค่าว่าง', 'ใส่ข้อมูล LBORN ไม่เป็นค่าว่าง (ไม่มีใส่ 0)', 'เมนูส่งเสริม > ครอบครัว > แถบข้อมูลการคลอด > จำนวนเด็กเกิดมีชีพ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3155', 'ข้อมูล BHOSP ไม่ใช่ 5 หลัก', 'ใส่ข้อมูล BHOSP ให้ครบ 5 หลัก', 'เมนูส่งเสริม > ครอบครัว > ดูแลเด็กหลังคลอด >สถานที่คลอด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3163', 'ข้อมูล DATEHCT ไม่อยู่ในรูปของ YYYYMMDD และปีไม่เป็น ค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูปแบบ YYYYMMDD และต้องเป็นปี ค.ศ.', 'เมนูส่งเสริม > ครอบครัว > แถบฝากครรภ์ > การรับการฝากครรภ์ >วันที่ตรวจ HCT');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3155', 'ข้อมูล BTYPE มีค่าที่ไม่ใช่ 1-6', 'ใส่ข้อมูล BTYPE ให้มีค่า 1-6', ' เมนูส่งเสริม > ครอบครัว > แถบข้อมูลการคลอด >วิธีการคลอด/สิ้นสุดการตั้งครรภ์ ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3168', 'ข้อมูล SBORN เป็นค่าว่าง', 'ใส่ข้อมูล SBORN ไม่เป็นค่าว่าง(ไม่มีใส่ 0)', 'เมนูส่งเสริม > ครอบครัว > แถบข้อมูลการคลอด > จำนวนเด็กตายในการคลอด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1132', 'ข้อมูล DID ไม่ครบ 30 หลัก', 'ใส่ข้อมูล DID ให้ครบ 30 หลัก', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1133', 'ข้อมูล DNAME ไม่มี หรือไม่สัมพันธ์กับ DID', 'ใส่ข้อมูล DNAME ให้สัมพันธ์กับ DID', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1134', 'ข้อมูล UNIT เป็นค่าว่าง', 'ใส่ข้อมูล UNIT ไม่ให้เป็นค่าว่าง', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1135', 'ข้อมูล UNIT_PACKING เป็นค่าว่าง', 'ใส่ข้อมูล UNIT_PACKING ไม่ให้เป็นค่าว่าง(เป็นค่า Null ได้)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1136', 'ข้อมูล D_UPDATE ไม่อยู่ในรูปของ YYYYMMDDHHMMSS และปีไม่เป็น ค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูปแบบ YYYYMMDDHHMMSS และต้องเป็นปี ค.ศ.', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1150', 'ข้อมูล HN ไม่ใช่ 15 หลัก', 'ใส่ข้อมูล HN ให้ครบ 15 หลัก', 'แถบข้อมูลประชากร > ปุ่มบันทึก');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3159', 'ข้อมูล D_UPDATE ไม่อยู่ในรูปของ YYYYMMDDHHMMSS และปีไม่เป็น ค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูปแบบ YYYYMMDDHHMMSS และต้องเป็นปี ค.ศ.', 'เมนูส่งเสริม > ครอบครัว > ดูแลเด็กหลังคลอด >ปุ่มบันทึก');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3143', 'ข้อมูล D_UPDATE ไม่อยู่ในรูปของ YYYYMMDDHHMMSS และปีไม่เป็น ค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูปแบบ YYYYMMDDHHMMSS และต้องเป็นปี ค.ศ.', 'เมนูส่งเสริม > ครอบครัว > แถบฝากครรภ์ >การรับบริการฝากครรภ์>ปุ่มบันทึก');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3169', 'ข้อมูล D_UPDATE ไม่อยู่ในรูปของ YYYYMMDDHHMMSS และปีไม่เป็น ค.ศ.', 'ใส่ข้อมูลให้อยู่ในรูปแบบ YYYYMMDD และต้องเป็นปี ค.ศ.', 'เมนูส่งเสริม > ครอบครัว > ดูแลเด็กหลังคลอด >ปุ่มบันทึก');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1152', 'ข้อมูล HID ไม่ใช่ 14 หลัก', 'ใส่ข้อมูล HID ให้ครบ 14 หลัก', '');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1153', 'ข้อมูล NAME เป็นค่าว่าง', 'ใส่ข้อมูล NAME ไม่ใช่ค่าว่าง', 'แถบข้อมูลประชากร > ชื่อ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1154', 'ข้อมูล LNAME เป็นค่าว่าง', 'ใส่ข้อมูล LNAME ไม่ใช่ค่าว่าง', 'แถบข้อมูลประชากร >นามสกุล');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1156', 'ข้อมูล TAMBON ไม่ใช่ 2 หลัก', 'ใส่ข้อมูล TAMBON ให้ครบ 2 หลัก', 'แถบข้อมูลประชากร > ตำบล');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1157', 'ข้อมูล AMPUR ไม่ใช่ 2 หลัก', 'ใส่ข้อมูล AMPUR ให้ครบ 2 หลัก', 'แถบข้อมูลประชากร >อำเภอ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1158', 'ข้อมูล CHANGWAT ไม่ใช่ 2 หลัก', 'ใส่ข้อมูล CHANGWAT ให้ครบ 2 หลัก', 'แถบข้อมูลประชากร >จังหวัด');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1159', 'ข้อมูล RACE ไม่ใช่ 3 หลัก', 'ใส่ข้อมูล RACE ให้ครบ 3 หลัก', 'แถบข้อมูลประชากร >เชื้อชาติ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1160', 'ข้อมูล RELIGION ไม่ใช่ 1 หลัก', 'ใส่ข้อมูล RELIGION ให้ครบ 1 หลัก', 'แถบข้อมูลประชากร >ศาสนา');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1162', 'ข้อมูล EDUCATE ไม่ใช่ 7 หลัก', 'ใส่ข้อมูล EDUCATE ให้ครบ 7 หลัก', 'แถบข้อมูลประชากร >การศึกษา');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1163', 'ข้อมูล DISCHAR ไม่ใช่ค่า 1-3 หรือ 9', 'ใส่ข้อมูล DISCHAR เป็นค่า 1-3 หรือ 9', 'แถบข้อมูลประชากร > กดปุ่ม FM >สถานะ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1164', 'ข้อมูล DDISCH ไม่อยู่ในรูปของ YYYYMMDD และ YYYY ไม่เป็น ค.ศ.', 'ใส่ข้อมูลอยู่ในรูปของ YYYYMMDD และ YYYY เป็น ค.ศ.', 'แถบข้อมูลประชากร >กดปุ่ม FM >วันที่จำหน่าย');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1165', 'ข้อมูล LABOR ไม่ใช่ 2 หลัก', 'ใส่ข้อมูล LABOR ให้ครบ 2 หลัก', 'แถบข้อมูลประชากร >ประเภทคนต่างด้าว');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1166', 'ข้อมูล D_UPDATE ไม่อยู่ในรูปของ YYYYMMDDHHMMSS และ YYYY ไม่เป็น ค.ศ.', 'ใส่ข้อมูลอยู่ในรูปของ YYYYMMDDHHMMSS และ YYYY เป็น ค.ศ.', 'แถบข้อมูลประชากร >กดปุ่มบัน');;

-- create s_oppp54_version
CREATE TABLE s_oppp54_version (
    version_id            varchar(255) NOT NULL,
    version_number            	varchar(255) NULL,
    version_description       	varchar(255) NULL,
    version_application_number	varchar(255) NULL,
    version_database_number   	varchar(255) NULL,
    version_update_time       	varchar(255) NULL 
    );

ALTER TABLE "s_oppp54_version"
	ADD CONSTRAINT "s_oppp54_version_pkey"
	PRIMARY KEY ("version_id");


INSERT INTO s_oppp54_version VALUES ('9770000000001', '1', 'OPPP 54 Module', '1.0.140111', '1.0.140111', (select current_date) || ','|| (select current_time));

INSERT INTO s_script_update_log values ('OPPP54_Module','update_oppp54_001.sql',(select current_date) || ','|| (select current_time),'Initialize OPPP 54 Module');

