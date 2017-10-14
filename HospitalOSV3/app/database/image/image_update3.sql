ALTER TABLE t_transfer_file_patient
	ADD COLUMN "transfer_file_patient_image" oid NULL;

CREATE TABLE b_image_user ( 
	"b_image_user_id"                      	varchar(255) NULL,
	"b_employee_id"                        	varchar(255) NULL,
	"image_user_login"                     	varchar(255) NULL,
	"image_user_fname"                     	varchar(255) NULL,
	"image_user_lname"                     	varchar(255) NULL,
	"image_user_note"                      	varchar(255) NULL,
	"image_user_gui_action_authen_id"    	varchar(255) NULL,
	"image_user_gui_action_authen_name"    	varchar(255) NULL,
	"image_user_gui_action_authen_is_read" 	varchar(255) NULL,
	"image_user_gui_action_authen_is_write"	varchar(255) NULL,
	"image_user_record_date_time"          	varchar(255) NULL,
	"image_user_record_eid"                	varchar(255) NULL,
	"image_user_update_date_time"          	varchar(255) NULL,
	"image_user_update_eid"                	varchar(255) NULL,
	"image_user_cancel_date_time"          	varchar(255) NULL,
	"image_user_cancel_eid"                	varchar(255) NULL, 
	"image_user_active"                	varchar(255) NULL, 
    PRIMARY KEY(b_image_user_id)     
);     

INSERT INTO b_image_user(b_image_user_id, b_employee_id, image_user_fname, image_user_lname, image_user_note, image_user_gui_action_authen_name, image_user_gui_action_authen_is_read, image_user_gui_action_authen_is_write, image_user_record_date_time, image_user_record_eid, image_user_update_date_time, image_user_update_eid, image_user_cancel_date_time, image_user_cancel_eid, image_user_active, image_user_login, image_user_gui_action_authen_id) 
    VALUES('001', '00000', 'image_imp', 'image_imp', 'implement', 'Main Program', '1', '1', '', '', '', '', '', '', '1', '00000', '01000');

INSERT INTO b_image_user(b_image_user_id, b_employee_id, image_user_fname, image_user_lname, image_user_note, image_user_gui_action_authen_name, image_user_gui_action_authen_is_read, image_user_gui_action_authen_is_write, image_user_record_date_time, image_user_record_eid, image_user_update_date_time, image_user_update_eid, image_user_cancel_date_time, image_user_cancel_eid, image_user_active, image_user_login, image_user_gui_action_authen_id)  
    VALUES('002', '00000', 'image_imp', 'image_imp', 'implement', 'รูปภาพผู้ป่วย', '1', '1', '', '', '', '', '', '', '1', '00000', '01100');

INSERT INTO b_image_user(b_image_user_id, b_employee_id, image_user_fname, image_user_lname, image_user_note, image_user_gui_action_authen_name, image_user_gui_action_authen_is_read, image_user_gui_action_authen_is_write, image_user_record_date_time, image_user_record_eid, image_user_update_date_time, image_user_update_eid, image_user_cancel_date_time, image_user_cancel_eid, image_user_active, image_user_login, image_user_gui_action_authen_id) 
    VALUES('003', '00000', 'image_imp', 'image_imp', 'implement', 'หน้าหลักโมดูล', '1', '1', '', '', '', '', '', '', '1', '00000', '01200');

INSERT INTO b_image_user(b_image_user_id, b_employee_id, image_user_fname, image_user_lname, image_user_note, image_user_gui_action_authen_name, image_user_gui_action_authen_is_read, image_user_gui_action_authen_is_write, image_user_record_date_time, image_user_record_eid, image_user_update_date_time, image_user_update_eid, image_user_cancel_date_time, image_user_cancel_eid, image_user_active, image_user_login, image_user_gui_action_authen_id) 
    VALUES('004', '00000', 'image_imp', 'image_imp', 'implement', 'รายละเอียดภาพ', '1', '1', '', '', '', '', '', '', '1', '00000', '01300');

INSERT INTO public.b_image_user(b_image_user_id, b_employee_id, image_user_fname, image_user_lname, image_user_note, image_user_gui_action_authen_name, image_user_gui_action_authen_is_read, image_user_gui_action_authen_is_write, image_user_record_date_time, image_user_record_eid, image_user_update_date_time, image_user_update_eid, image_user_cancel_date_time, image_user_cancel_eid, image_user_active, image_user_login, image_user_gui_action_authen_id) 
    VALUES('005', '00000', 'image_imp', 'image_imp', 'implement', 'Setup Program', '1', '1', '', '', '', '', '', '', '1', '00000', '02000');

INSERT INTO b_image_user(b_image_user_id, b_employee_id, image_user_fname, image_user_lname, image_user_note, image_user_gui_action_authen_name, image_user_gui_action_authen_is_read, image_user_gui_action_authen_is_write, image_user_record_date_time, image_user_record_eid, image_user_update_date_time, image_user_update_eid, image_user_cancel_date_time, image_user_cancel_eid, image_user_active, image_user_login, image_user_gui_action_authen_id) 
    VALUES('006', '00000', 'image_imp', 'image_imp', 'implement', 'ตั้ง Path ภาพ', '1', '1', '', '', '', '', '', '', '1', '00000', '02100');

INSERT INTO b_image_user(b_image_user_id, b_employee_id, image_user_fname, image_user_lname, image_user_note, image_user_gui_action_authen_name, image_user_gui_action_authen_is_read, image_user_gui_action_authen_is_write, image_user_record_date_time, image_user_record_eid, image_user_update_date_time, image_user_update_eid, image_user_cancel_date_time, image_user_cancel_eid, image_user_active, image_user_login, image_user_gui_action_authen_id) 
    VALUES('007', '00000', 'image_imp', 'image_imp', 'implement', 'ตั้งค่าผู้ใช้งานโมดูลภาพ', '1', '1', '', '', '', '', '', '', '1', '00000', '02200');

insert into image_version values ('3','1.0.030708','Start From Hospital 3.7.160907');