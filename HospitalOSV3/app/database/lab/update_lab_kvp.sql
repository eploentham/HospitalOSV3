CREATE TABLE public.s_lab_version ( 
    s_lab_version_id              	varchar(255) NOT NULL,
    version_lab_number            	varchar(255) NULL,
    version_lab_description       	varchar(255) NULL,
    version_lab_application_number	varchar(255) NULL,
    version_lab_database_number   	varchar(255) NULL,
    version_lab_update_time       	varchar(255) NULL 
    );

CREATE TABLE public.t_lab_event_log ( 
    t_lab_event_log_id     	varchar(255) NOT NULL,
    lab_event_log_event    	varchar(1) NOT NULL,
    lab_event_log_file_name	varchar(255) NOT NULL,
    lab_event_log_file_data	text NOT NULL,
    lab_event_log_status   	varchar(1) NOT NULL,
    record_date_time       	varchar(255) NOT NULL,
    staff_record           	varchar(255) NOT NULL,
    PRIMARY KEY(t_lab_event_log_id)
);

CREATE TABLE public.t_lab_order_number_map_visit ( 
    t_lab_order_number_map_visit_id	varchar(255) NOT NULL,
    t_visit_id                     	varchar(255) NOT NULL,
    order_verify_date_time         	varchar(255) NOT NULL,
    order_executed_date_time       	varchar(255) NOT NULL,
    PRIMARY KEY(t_lab_order_number_map_visit_id)
);

UPDATE b_item_lab_result SET f_lab_result_type_id = '2' ;

INSERT INTO s_lab_version VALUES ('9750000000001', '1', 'LAB, Community Edition', '1.06.260509', '1.06.260509', '2552-05-26 12:00:00');
insert into s_script_update_log values ('LAB_KVP','update_lab_kvp.sql',(select current_date) || ','|| (select current_time),'เริ่มต้นการใช้งานระบบ LAB');
