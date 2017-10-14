CREATE TABLE	t_lab_user_log ( 
    t_lab_user_log_id              	varchar(255) NOT NULL,
    t_visit_id            	varchar(255) NULL,
    lab_user_log_operate            	varchar(255) NULL,
    lab_user_log_confirm       	varchar(255) NULL,
    lab_user_log_operate_date_time	varchar(255) NULL,
    lab_user_log_confirm_date_time   	varchar(255) NULL,
    lab_user_log_record_date_time   	varchar(255) NULL,
    lab_user_log_lab_no       	varchar(255) NULL ,
    PRIMARY KEY (t_lab_user_log_id)
);

INSERT INTO s_lab_version VALUES ('9750000000005', '5', 'LAB, Community Edition', '1.07.090610', '1.07.090610', (select current_date) || ','|| (select current_time));

INSERT INTO s_script_update_log VALUES ('LAB_for Clinic Niranam','update_lab_kvp_5.sql',(select current_date) || ','|| (select current_time),'alter table s_lab_version add primary key (s_lab_version_id)');

