--deprecated henbe unused เป็นโมดูลเสริมมาแก้ข้อมูลในตารางของ Hos ไม่ได้
UPDATE public.b_item_16_group 
    SET item_16_group_number='1'
    WHERE item_16_group_number = 'STI01'
    AND item_16_group_active = '1';

UPDATE public.b_item_16_group 
    SET item_16_group_number='2'
    WHERE item_16_group_number = 'STI02'
    AND item_16_group_active = '1';

UPDATE public.b_item_16_group 
    SET item_16_group_number='3'
    WHERE item_16_group_number = 'STI03'
    AND item_16_group_active = '1';

UPDATE public.b_item_16_group 
    SET item_16_group_number='4_OH'
    WHERE item_16_group_number = 'STI04_OH'
    AND item_16_group_active = '1';

UPDATE public.b_item_16_group 
    SET item_16_group_number='5'
    WHERE item_16_group_number = 'STI05'
    AND item_16_group_active = '1';

UPDATE public.b_item_16_group 
    SET item_16_group_number='6'
    WHERE item_16_group_number = 'STI06'
    AND item_16_group_active = '1';

UPDATE public.b_item_16_group 
    SET item_16_group_number='7'
    WHERE item_16_group_number = 'STI07'
    AND item_16_group_active = '1';

UPDATE public.b_item_16_group 
    SET item_16_group_number='8'
    WHERE item_16_group_number = 'STI08'
    AND item_16_group_active = '1';

UPDATE public.b_item_16_group 
    SET item_16_group_number='9'
    WHERE item_16_group_number = 'STI09'
    AND item_16_group_active = '1';

UPDATE public.b_item_16_group 
    SET item_16_group_number='A'
    WHERE item_16_group_number = 'STI10'
    AND item_16_group_active = '1';

UPDATE public.b_item_16_group 
    SET item_16_group_number='B'
    WHERE item_16_group_number = 'STI11'
    AND item_16_group_active = '1';

UPDATE public.b_item_16_group 
    SET item_16_group_number='C'
    WHERE item_16_group_number = 'STI12'
    AND item_16_group_active = '1';

UPDATE public.b_item_16_group 
    SET item_16_group_number='D'
    WHERE item_16_group_number = 'STI13'
    AND item_16_group_active = '1';

UPDATE public.b_item_16_group 
    SET item_16_group_number='E'
    WHERE item_16_group_number = 'STI14'
    AND item_16_group_active = '1';

UPDATE public.b_item_16_group 
    SET item_16_group_number='F'
    WHERE item_16_group_number = 'STI15'
    AND item_16_group_active = '1';

UPDATE public.b_item_16_group 
    SET item_16_group_number='G'
    WHERE item_16_group_number = 'STI16'
    AND item_16_group_active = '1';

UPDATE public.b_item_16_group 
    SET item_16_group_number='1'
    WHERE item_16_group_number = 'STI01'
    AND item_16_group_active = '1';

INSERT INTO s_welfare_version("s_version_id", "version_number", "version_description", "version_application_number", "version_database_number", "version_update_time")
VALUES('9701000000020', '7', 'Hospital OS, Community Edition', '1.07.240908', '3.15.240908', '2551-09-24 12:00:00');
