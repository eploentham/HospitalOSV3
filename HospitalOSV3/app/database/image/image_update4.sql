DELETE FROM b_image_user WHERE b_image_user_id = '001';
DELETE FROM b_image_user WHERE b_image_user_id = '002';
DELETE FROM b_image_user WHERE b_image_user_id = '003';
DELETE FROM b_image_user WHERE b_image_user_id = '004';
DELETE FROM b_image_user WHERE b_image_user_id = '005';
DELETE FROM b_image_user WHERE b_image_user_id = '006';
DELETE FROM b_image_user WHERE b_image_user_id = '007';

INSERT INTO b_image_user(b_image_user_id, b_employee_id, image_user_fname, image_user_lname, image_user_note, image_user_gui_action_authen_name, image_user_gui_action_authen_is_read, image_user_gui_action_authen_is_write, image_user_record_date_time, image_user_record_eid, image_user_update_date_time, image_user_update_eid, image_user_cancel_date_time, image_user_cancel_eid, image_user_active, image_user_login, image_user_gui_action_authen_id) 
    VALUES('001', '00000', 'image_imp', 'image_imp', 'implement', 'Main Program', '1', '1', '', '', '', '', '', '', '1', '00000', '01000');

INSERT INTO b_image_user(b_image_user_id, b_employee_id, image_user_fname, image_user_lname, image_user_note, image_user_gui_action_authen_name, image_user_gui_action_authen_is_read, image_user_gui_action_authen_is_write, image_user_record_date_time, image_user_record_eid, image_user_update_date_time, image_user_update_eid, image_user_cancel_date_time, image_user_cancel_eid, image_user_active, image_user_login, image_user_gui_action_authen_id)  
    VALUES('002', '00000', 'image_imp', 'image_imp', 'implement', 'ภาพหน้าผู้ป่วย', '1', '1', '', '', '', '', '', '', '1', '00000', '01100');

INSERT INTO b_image_user(b_image_user_id, b_employee_id, image_user_fname, image_user_lname, image_user_note, image_user_gui_action_authen_name, image_user_gui_action_authen_is_read, image_user_gui_action_authen_is_write, image_user_record_date_time, image_user_record_eid, image_user_update_date_time, image_user_update_eid, image_user_cancel_date_time, image_user_cancel_eid, image_user_active, image_user_login, image_user_gui_action_authen_id) 
    VALUES('003', '00000', 'image_imp', 'image_imp', 'implement', 'แสดงภาพของผู้ป่วย', '1', '1', '', '', '', '', '', '', '1', '00000', '01200');

INSERT INTO b_image_user(b_image_user_id, b_employee_id, image_user_fname, image_user_lname, image_user_note, image_user_gui_action_authen_name, image_user_gui_action_authen_is_read, image_user_gui_action_authen_is_write, image_user_record_date_time, image_user_record_eid, image_user_update_date_time, image_user_update_eid, image_user_cancel_date_time, image_user_cancel_eid, image_user_active, image_user_login, image_user_gui_action_authen_id) 
    VALUES('004', '00000', 'image_imp', 'image_imp', 'implement', 'รายละเอียดภาพ', '1', '1', '', '', '', '', '', '', '1', '00000', '01300');

INSERT INTO public.b_image_user(b_image_user_id, b_employee_id, image_user_fname, image_user_lname, image_user_note, image_user_gui_action_authen_name, image_user_gui_action_authen_is_read, image_user_gui_action_authen_is_write, image_user_record_date_time, image_user_record_eid, image_user_update_date_time, image_user_update_eid, image_user_cancel_date_time, image_user_cancel_eid, image_user_active, image_user_login, image_user_gui_action_authen_id) 
    VALUES('005', '00000', 'image_imp', 'image_imp', 'implement', 'Setup Program', '1', '1', '', '', '', '', '', '', '1', '00000', '02000');

INSERT INTO b_image_user(b_image_user_id, b_employee_id, image_user_fname, image_user_lname, image_user_note, image_user_gui_action_authen_name, image_user_gui_action_authen_is_read, image_user_gui_action_authen_is_write, image_user_record_date_time, image_user_record_eid, image_user_update_date_time, image_user_update_eid, image_user_cancel_date_time, image_user_cancel_eid, image_user_active, image_user_login, image_user_gui_action_authen_id) 
    VALUES('006', '00000', 'image_imp', 'image_imp', 'implement', 'ที่เก็บภาพ', '1', '1', '', '', '', '', '', '', '1', '00000', '02100');

INSERT INTO b_image_user(b_image_user_id, b_employee_id, image_user_fname, image_user_lname, image_user_note, image_user_gui_action_authen_name, image_user_gui_action_authen_is_read, image_user_gui_action_authen_is_write, image_user_record_date_time, image_user_record_eid, image_user_update_date_time, image_user_update_eid, image_user_cancel_date_time, image_user_cancel_eid, image_user_active, image_user_login, image_user_gui_action_authen_id) 
    VALUES('007', '00000', 'image_imp', 'image_imp', 'implement', 'สิทธิการใช้งาน', '1', '1', '', '', '', '', '', '', '1', '00000', '02200');

insert into image_version values ('4','1.0.160708','Start From Hospital 3.7.160907');