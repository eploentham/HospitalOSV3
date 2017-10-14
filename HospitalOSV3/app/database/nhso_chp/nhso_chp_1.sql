
CREATE TABLE s_nhso_chp_version ( 
    s_version_id              	varchar(255) NOT NULL,
    version_number            	varchar(255) NULL,
    version_description       	varchar(255) NULL,
    version_application_number	varchar(255) NULL,
    version_database_number   	varchar(255) NULL,
    version_update_time       	varchar(255) NULL,
    PRIMARY KEY(s_version_id)
);

--list รายการยาที่ยังไม่ Map เอาคำสั่ง ไป insert ใน ตาราง r_sql_template 
INSERT INTO public.r_sql_template(r_sql_template_id, sql_template_number, sql_template_description, sql_template_sql, sql_template_is_query_by_date, sql_template_active)
    VALUES('00000000000999', 'CHPV1', 'CHPV1.รายการยาที่ยังไม่ map กับยามาตรฐาน 24 หลัก', 'select distinct b_item.b_item_id,b_item.item_number,b_item.item_common_name
from b_item inner join b_item_subgroup
    on b_item.b_item_subgroup_id = b_item_subgroup.b_item_subgroup_id
    left join b_nhso_map_drug
    on b_item.b_item_id = b_nhso_map_drug.b_item_id
where b_item_subgroup.f_item_group_id in (''1'',''4'')
and b_item.item_active = ''1''
and b_nhso_map_drug.b_nhso_map_drug_id is null', '0', '1');

INSERT INTO s_nhso_chp_version VALUES ('NHSOCHP000001', '01', 'NHSO CHP', '1.09.050909', '1.09.050909', (select current_date) || ','|| (select current_time));
