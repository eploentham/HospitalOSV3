CREATE TABLE imed.abc_ven (
	abc_ven_id VARCHAR(255) NOT NULL,
	fix_abc_id VARCHAR(255),
	fix_ven_id VARCHAR(255),
	range_of_day VARCHAR(255),
	range_re_order VARCHAR(255),
	range_max VARCHAR(255),
	range_min VARCHAR(255)
);
ALTER TABLE imed.abc_ven ADD CONSTRAINT abc_ven_pkey PRIMARY KEY (abc_ven_id);

CREATE TABLE imed.accident_emergency_report (
	accident_emergency_report_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	patient_id VARCHAR(255),
	claim_code VARCHAR(255),
	outside_changwat VARCHAR(255),
	accident_date VARCHAR(255),
	accident_time VARCHAR(255),
	accident_payment VARCHAR(255),
	ucae VARCHAR(255),
	emergency_type VARCHAR(255),
	dz8 VARCHAR(255),
	hc9 VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.accident_emergency_report ADD CONSTRAINT accident_emergency_report_pkey PRIMARY KEY (accident_emergency_report_id);
CREATE INDEX visit_id_accident_emergency_report_key ON imed.accident_emergency_report(visit_id);

CREATE TABLE imed.admission_note (
	admission_note_id VARCHAR(254) NOT NULL,
	admit_id VARCHAR(254),
	main_symptom TEXT,
	current_illness TEXT,
	patient_history TEXT,
	family_history TEXT,
	drug_allergy TEXT,
	patient_examine TEXT,
	temperature VARCHAR(254),
	pressure_max VARCHAR(254),
	pressure_min VARCHAR(254),
	pulse VARCHAR(254),
	respiration VARCHAR(254),
	diagnosis TEXT,
	plan TEXT,
	doctor_eid VARCHAR(254),
	modify_eid VARCHAR(254),
	modify_date VARCHAR(254),
	modify_time VARCHAR(254)
);
ALTER TABLE imed.admission_note ADD CONSTRAINT admission_note_pkey PRIMARY KEY (admission_note_id);
CREATE INDEX admission_note_admit_id ON imed.admission_note(admit_id);

CREATE TABLE imed.admit (
	admit_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	patient_id VARCHAR(255),
	hn VARCHAR(255),
	an VARCHAR(255),
	admit_doctor_eid VARCHAR(255),
	admit_eid VARCHAR(255),
	admit_spid VARCHAR(255),
	admit_date VARCHAR(255),
	admit_time VARCHAR(255),
	patient_relate VARCHAR(255),
	patient_relate_note VARCHAR(255),
	base_department_id VARCHAR(255),
	length_of_stay VARCHAR(255),
	ipd_discharge VARCHAR(255),
	ipd_discharge_eid VARCHAR(255),
	ipd_discharge_date VARCHAR(255),
	ipd_discharge_time VARCHAR(255),
	doctor_allow VARCHAR(255),
	doctor_allow_eid VARCHAR(255),
	doctor_allow_date VARCHAR(255),
	doctor_allow_time VARCHAR(255),
	times_admit VARCHAR(255),
	assess_stay VARCHAR(255),
	assess_price VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255),
	active VARCHAR(255),
	base_clinic_id VARCHAR(255)
);
ALTER TABLE imed.admit ADD CONSTRAINT admit_pkey PRIMARY KEY (admit_id);
CREATE INDEX admit_active ON imed.admit(active);
CREATE INDEX admit_an ON imed.admit(an);
CREATE INDEX admit_ipd_discharge ON imed.admit(ipd_discharge);
CREATE INDEX admit_visit_id ON imed.admit(visit_id);

CREATE TABLE imed.appointment (
	appointment_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	base_spid VARCHAR(255),
	doctor_eid VARCHAR(255),
	doctor_assigner_eid VARCHAR(255),
	base_department_id VARCHAR(255),
	basic_advice VARCHAR(255),
	note VARCHAR(255),
	appoint_date VARCHAR(255),
	appoint_time VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255),
	fix_appointment_status_id VARCHAR(255),
	appointment_schedule_id VARCHAR(255),
	fix_appointment_type_id VARCHAR(255),
	continue_ref_no VARCHAR(255),
	continue_note VARCHAR(255),
	visit_id VARCHAR(255),
	close_status_date VARCHAR(255),
	close_status_time VARCHAR(255),
	base_clinic_id VARCHAR(255)
);
ALTER TABLE imed.appointment ADD CONSTRAINT appointment_pkey PRIMARY KEY (appointment_id);
CREATE INDEX appointment_base_spid ON imed.appointment(base_spid);
CREATE INDEX appointment_continue_ref_no ON imed.appointment(continue_ref_no);
CREATE INDEX appointment_doctor_assigner_eid ON imed.appointment(doctor_assigner_eid);
CREATE INDEX appointment_doctor_eid ON imed.appointment(doctor_eid);
CREATE INDEX appointment_patient_id ON imed.appointment(patient_id);
CREATE INDEX idx_appoint_visit_id ON imed.appointment(visit_id);

CREATE TABLE imed.appointment_doctor (
	appointment_doctor_id VARCHAR(255) NOT NULL,
	appointment_id VARCHAR(255),
	doctor_eid VARCHAR(255),
	base_clinic_id VARCHAR(255),
	basic_advice VARCHAR(255),
	note VARCHAR(255),
	appoint_date VARCHAR(255),
	appoint_time VARCHAR(255)
);
ALTER TABLE imed.appointment_doctor ADD CONSTRAINT appointment_doctor_pkey PRIMARY KEY (appointment_doctor_id);
CREATE INDEX appointment_doctor_appoin ON imed.appointment_doctor(appointment_id);

CREATE TABLE imed.appointment_order_item (
	appointment_order_item_id VARCHAR(255) NOT NULL,
	appointment_id VARCHAR(255),
	item_id VARCHAR(255),
	fix_item_type_id VARCHAR(255),
	base_drug_usage_code VARCHAR(255),
	instruction_format VARCHAR(255),
	base_drug_instruction_id VARCHAR(255),
	dose_quantity VARCHAR(255),
	base_dose_unit_id VARCHAR(255),
	base_drug_frequency_id VARCHAR(255),
	instruction_text_line1 VARCHAR(255),
	instruction_text_line2 VARCHAR(255),
	instruction_text_line3 VARCHAR(255),
	quantity VARCHAR(255),
	base_unit_id VARCHAR(255),
	description VARCHAR(255),
	caution VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.appointment_order_item ADD CONSTRAINT appointment_order_item_pkey PRIMARY KEY (appointment_order_item_id);
CREATE INDEX appointment_order_item_ap ON imed.appointment_order_item(appointment_id);
CREATE INDEX appointment_order_item_it ON imed.appointment_order_item(item_id);

CREATE TABLE imed.assign_lab (
	assign_lab_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	ln VARCHAR(255),
	assign_doctor_eid VARCHAR(255),
	assign_eid VARCHAR(255),
	assign_spid VARCHAR(255),
	assign_date VARCHAR(255),
	assign_time VARCHAR(255),
	receive_spid VARCHAR(255),
	assign_order_status VARCHAR(255),
	keep_specimen_eid VARCHAR(255),
	receive_specimen_eid VARCHAR(255),
	receive_specimen_date VARCHAR(255),
	receive_specimen_time VARCHAR(255),
	complete_date VARCHAR(255),
	complete_time VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255),
	max_print_times VARCHAR(255),
	receive_all_specimen VARCHAR(255)
);
ALTER TABLE imed.assign_lab ADD CONSTRAINT assign_lab_pkey PRIMARY KEY (assign_lab_id);
CREATE INDEX assign_lab_assign_date ON imed.assign_lab(assign_date);
CREATE INDEX assign_lab_assign_status ON imed.assign_lab(assign_order_status);
CREATE INDEX assign_lab_complete_date ON imed.assign_lab(complete_date);
CREATE INDEX assign_lab_doctor_eid ON imed.assign_lab(assign_doctor_eid);
CREATE INDEX assign_lab_ln ON imed.assign_lab(ln);
CREATE INDEX assign_lab_recieve_specimen_date ON imed.assign_lab(receive_specimen_date);
CREATE INDEX assign_lab_visit_id ON imed.assign_lab(visit_id);

CREATE TABLE imed.assign_times (
	assign_times_id VARCHAR(254) NOT NULL,
	patient_id VARCHAR(254),
	xray_times VARCHAR(254),
	ultrasound_times VARCHAR(254),
	ekg_times VARCHAR(254)
);
ALTER TABLE imed.assign_times ADD CONSTRAINT assign_times_pkey PRIMARY KEY (assign_times_id);
CREATE INDEX assign_times_patient_id ON imed.assign_times(patient_id);

CREATE TABLE imed.assign_xray (
	assign_xray_id VARCHAR(254) NOT NULL,
	visit_id VARCHAR(254),
	assign_order_number VARCHAR(254),
	assign_doctor_eid VARCHAR(254),
	assign_eid VARCHAR(254),
	assign_spid VARCHAR(254),
	assign_date VARCHAR(254),
	assign_time VARCHAR(254),
	receive_spid VARCHAR(254),
	xray_times VARCHAR(254),
	ultrasound_times VARCHAR(254),
	ekg_times VARCHAR(254),
	assign_order_status VARCHAR(254),
	complete_execute_date VARCHAR(254),
	complete_execute_time VARCHAR(254),
	complete_date VARCHAR(254),
	complete_time VARCHAR(254),
	is_wheel_chair_type VARCHAR(254),
	is_stretcher_type VARCHAR(254),
	is_portable_type VARCHAR(254),
	is_o2_type VARCHAR(254),
	is_slide_type VARCHAR(254),
	is_wheel_bed_type VARCHAR(254),
	modify_eid VARCHAR(254),
	modify_date VARCHAR(254),
	modify_time VARCHAR(254),
	max_print_times VARCHAR(255)
);
ALTER TABLE imed.assign_xray ADD CONSTRAINT assign_xray_pkey PRIMARY KEY (assign_xray_id);
CREATE INDEX assign_xray_assign_date ON imed.assign_xray(assign_date);
CREATE INDEX assign_xray_assign_status ON imed.assign_xray(assign_order_status);
CREATE INDEX assign_xray_complete_date ON imed.assign_xray(complete_date);
CREATE INDEX assign_xray_doctor_eid ON imed.assign_xray(assign_doctor_eid);
CREATE INDEX assign_xray_execute_date ON imed.assign_xray(complete_execute_date);
CREATE INDEX assign_xray_receive_spid ON imed.assign_xray(receive_spid);
CREATE INDEX assign_xray_visit_id ON imed.assign_xray(visit_id);

CREATE TABLE imed.attending_physician (
	attending_physician_id VARCHAR(255) NOT NULL,
	employee_id VARCHAR(255),
	visit_id VARCHAR(255),
	priority VARCHAR(255),
	base_department_id VARCHAR(255),
	fix_doctor_visit_status VARCHAR(255),
	begin_date VARCHAR(255),
	begin_time VARCHAR(255),
	finish_date VARCHAR(255),
	finish_time VARCHAR(255),
	base_clinic_id VARCHAR(255)
);
ALTER TABLE imed.attending_physician ADD CONSTRAINT attending_physician_pkey PRIMARY KEY (attending_physician_id);
CREATE INDEX att_physician_doctor_status ON imed.attending_physician(fix_doctor_visit_status);
CREATE INDEX atten_phy_eid ON imed.attending_physician(employee_id);
CREATE INDEX atten_priority ON imed.attending_physician(priority);
CREATE INDEX visit_id_attending_physician_ke ON imed.attending_physician(visit_id);

CREATE TABLE imed.baiya_q (
	hn VARCHAR(255),
	name VARCHAR(255),
	age VARCHAR(255),
	on_case VARCHAR(255),
	on_date VARCHAR(255),
	on_time VARCHAR(255),
	position_fox VARCHAR(255),
	doc_code VARCHAR(255),
	doc_name VARCHAR(255),
	credit VARCHAR(255),
	insur VARCHAR(255),
	health VARCHAR(255),
	remark1 VARCHAR(255),
	remark2 VARCHAR(255),
	id_no VARCHAR(255),
	type VARCHAR(255),
	age_txt VARCHAR(255),
	address VARCHAR(255),
	tel VARCHAR(255),
	r_name VARCHAR(255),
	blood VARCHAR(255),
	id VARCHAR(255),
	dental VARCHAR(255),
	city VARCHAR(255),
	amper VARCHAR(255),
	tumbal VARCHAR(255),
	type_pat VARCHAR(255),
	pressure_h VARCHAR(255),
	pressure_l VARCHAR(255),
	tall VARCHAR(255),
	weight VARCHAR(255),
	temper VARCHAR(255),
	drug_algy VARCHAR(255),
	q_print VARCHAR(255),
	xray_no VARCHAR(255),
	company VARCHAR(255),
	no_emp VARCHAR(255),
	name_emp VARCHAR(255),
	plan1 VARCHAR(255),
	plan2 VARCHAR(255),
	plan3 VARCHAR(255),
	is_updated VARCHAR(255)
);

CREATE TABLE imed.base_accept_labor_reason (
	base_accept_labor_reason_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_accept_labor_reason ADD CONSTRAINT base_accept_labor_reason_pkey PRIMARY KEY (base_accept_labor_reason_id);

CREATE TABLE imed.base_address (
	fullcode VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	type VARCHAR(255),
	ampcode VARCHAR(255),
	cgwcode VARCHAR(255),
	region VARCHAR(255)
);
ALTER TABLE imed.base_address ADD CONSTRAINT base_address_pkey PRIMARY KEY (fullcode);
CREATE INDEX base_address_ampcode ON imed.base_address(ampcode);
CREATE INDEX base_address_cgwcode ON imed.base_address(cgwcode);
CREATE INDEX base_address_description ON imed.base_address(description);
CREATE INDEX base_address_type ON imed.base_address(type);

CREATE TABLE imed.base_amniotic_fluid (
	base_amniotic_fluid_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_amniotic_fluid ADD CONSTRAINT base_amniotic_fluid_pkey PRIMARY KEY (base_amniotic_fluid_id);

CREATE TABLE imed.base_anc_place (
	base_anc_place_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_anc_place ADD CONSTRAINT base_anc_place_pkey PRIMARY KEY (base_anc_place_id);

CREATE TABLE imed.base_antibiotic (
	base_antibiotic_id VARCHAR(254) NOT NULL,
	description VARCHAR(254),
	show_default VARCHAR(254)
);
ALTER TABLE imed.base_antibiotic ADD CONSTRAINT base_antibiotic_pkey PRIMARY KEY (base_antibiotic_id);

CREATE TABLE imed.base_bad_film_reason (
	base_bad_film_reason_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_bad_film_reason ADD CONSTRAINT base_bad_film_reason_pkey PRIMARY KEY (base_bad_film_reason_id);

CREATE TABLE imed.base_bank (
	base_bank_id VARCHAR(255) NOT NULL,
	bank_name_th VARCHAR(255),
	bank_name_en VARCHAR(255)
);
ALTER TABLE imed.base_bank ADD CONSTRAINT base_bank_pkey PRIMARY KEY (base_bank_id);

CREATE TABLE imed.base_billing_group (
	base_billing_group_id VARCHAR(255) NOT NULL,
	fix_item_type_id VARCHAR(255),
	code VARCHAR(255),
	law_code VARCHAR(255),
	description_th VARCHAR(255),
	description_en VARCHAR(255),
	law_sum VARCHAR(255),
	assu_code VARCHAR(255),
	description_assu_th VARCHAR(255),
	description_assu_en VARCHAR(255),
	assu_sum VARCHAR(255),
	fax_claim_code VARCHAR(255),
	description_fc_th VARCHAR(255),
	description_fc_en VARCHAR(255),
	fax_claim_sum VARCHAR(255),
	map2ss VARCHAR(255),
	acc_group VARCHAR(255)
);
ALTER TABLE imed.base_billing_group ADD CONSTRAINT base_billing_group_pkey PRIMARY KEY (base_billing_group_id);

CREATE TABLE imed.base_bladder (
	base_bladder_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_bladder ADD CONSTRAINT base_bladder_pkey PRIMARY KEY (base_bladder_id);

CREATE TABLE imed.base_bleeding_per_vagina (
	base_bleeding_per_vagina_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_bleeding_per_vagina ADD CONSTRAINT base_bleeding_per_vagina_pkey PRIMARY KEY (base_bleeding_per_vagina_id);

CREATE TABLE imed.base_blood_group (
	base_blood_group_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_blood_group ADD CONSTRAINT base_blood_group_pkey PRIMARY KEY (base_blood_group_id);

CREATE TABLE imed.base_branch_bank (
	base_branch_bank_id VARCHAR(255) NOT NULL,
	base_bank_id VARCHAR(255),
	branch_bank_code VARCHAR(255),
	branch_bank_name VARCHAR(255),
	branch_address TEXT,
	fix_tambol_id VARCHAR(255),
	fix_amphur_id VARCHAR(255),
	fix_changwat_id VARCHAR(255),
	comments TEXT
);
ALTER TABLE imed.base_branch_bank ADD CONSTRAINT base_branch_bank_pkey PRIMARY KEY (base_branch_bank_id);
CREATE INDEX base_branch_bank_amphur_id ON imed.base_branch_bank(fix_amphur_id);
CREATE INDEX base_branch_bank_bank_id ON imed.base_branch_bank(base_bank_id);
CREATE INDEX base_branch_bank_changwat_id ON imed.base_branch_bank(fix_changwat_id);
CREATE INDEX base_branch_bank_tambol_id ON imed.base_branch_bank(fix_tambol_id);

CREATE TABLE imed.base_category_group (
	base_category_group_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_category_group ADD CONSTRAINT base_category_group_pkey PRIMARY KEY (base_category_group_id);

CREATE TABLE imed.base_clinic (
	base_clinic_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_clinic ADD CONSTRAINT base_clinic_pkey PRIMARY KEY (base_clinic_id);

CREATE TABLE imed.base_country (
	base_country_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_country ADD CONSTRAINT base_country_pkey PRIMARY KEY (base_country_id);

CREATE TABLE imed.base_custom_tab (
	base_custom_tab_id VARCHAR(255) NOT NULL,
	custom_tab_name VARCHAR(255),
	custom_tab_url VARCHAR(255),
	custom_tab_priority VARCHAR(255)
);
ALTER TABLE imed.base_custom_tab ADD CONSTRAINT base_custom_tab_pkey PRIMARY KEY (base_custom_tab_id);

CREATE TABLE imed.base_death_status (
	base_death_status_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_death_status ADD CONSTRAINT base_death_status_pkey PRIMARY KEY (base_death_status_id);
CREATE INDEX stk_death_base_death_status_id ON imed.base_death_status(base_death_status_id);

CREATE TABLE imed.base_deliver_document (
	base_deliver_document_id VARCHAR(255) NOT NULL,
	description_th VARCHAR(255),
	description_en VARCHAR(255)
);
ALTER TABLE imed.base_deliver_document ADD CONSTRAINT base_deliver_document_pkey PRIMARY KEY (base_deliver_document_id);

CREATE TABLE imed.base_delivery_type (
	base_delivery_type_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_delivery_type ADD CONSTRAINT base_delivery_type_pkey PRIMARY KEY (base_delivery_type_id);

CREATE TABLE imed.base_dent_diag_detail (
	base_dent_diag_detail_id VARCHAR(255) NOT NULL,
	description TEXT
);
ALTER TABLE imed.base_dent_diag_detail ADD CONSTRAINT base_dent_diag_detail_pkey PRIMARY KEY (base_dent_diag_detail_id);

CREATE TABLE imed.base_dent_diagnosis (
	base_dent_diagnosis_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_dent_diagnosis ADD CONSTRAINT base_dent_diagnosis_pkey PRIMARY KEY (base_dent_diagnosis_id);

CREATE TABLE imed.base_dent_operation (
	base_dent_operation_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_dent_operation ADD CONSTRAINT base_dent_operation_pkey PRIMARY KEY (base_dent_operation_id);

CREATE TABLE imed.base_dent_organic (
	base_dent_organic_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_dent_organic ADD CONSTRAINT base_dent_organic_pkey PRIMARY KEY (base_dent_organic_id);

CREATE TABLE imed.base_department (
	base_department_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_department ADD CONSTRAINT base_department_pkey PRIMARY KEY (base_department_id);

CREATE TABLE imed.base_deposit_bank (
	base_deposit_bank_id VARCHAR(255) NOT NULL,
	bank_code VARCHAR(255),
	bank_branch_code VARCHAR(255),
	account_number VARCHAR(255),
	description VARCHAR(255)
);
ALTER TABLE imed.base_deposit_bank ADD CONSTRAINT base_deposit_bank_pkey PRIMARY KEY (base_deposit_bank_id);

CREATE TABLE imed.base_df_mode (
	base_df_mode_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_df_mode ADD CONSTRAINT base_df_mode_pkey PRIMARY KEY (base_df_mode_id);

CREATE TABLE imed.base_diagnosis_employee (
	base_diagnosis_employee_id VARCHAR(255) NOT NULL,
	base_diagnosis_set_id VARCHAR(255),
	employee_id VARCHAR(255),
	base_clinic_id VARCHAR(255)
);
ALTER TABLE imed.base_diagnosis_employee ADD CONSTRAINT base_diagnosis_employee_pkey PRIMARY KEY (base_diagnosis_employee_id);
CREATE INDEX base_diagnosis_employee_clinic_id ON imed.base_diagnosis_employee(base_clinic_id);
CREATE INDEX base_diagnosis_employee_id ON imed.base_diagnosis_employee(employee_id);

CREATE TABLE imed.base_diagnosis_set (
	base_diagnosis_set_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_diagnosis_set ADD CONSTRAINT base_diagnosis_set_pkey PRIMARY KEY (base_diagnosis_set_id);

CREATE TABLE imed.base_disc_command (
	base_disc_command_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_disc_command ADD CONSTRAINT base_disc_command_pkey PRIMARY KEY (base_disc_command_id);

CREATE TABLE imed.base_disc_special_card (
	base_disc_special_card_id VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	template_discount_id VARCHAR(255),
	inspire_date VARCHAR(255),
	expire_date VARCHAR(255),
	active VARCHAR(255)
);
ALTER TABLE imed.base_disc_special_card ADD CONSTRAINT base_disc_special_card_pkey PRIMARY KEY (base_disc_special_card_id);

CREATE TABLE imed.base_discount_reason (
	base_discount_reason_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_discount_reason ADD CONSTRAINT base_discount_reason_pkey PRIMARY KEY (base_discount_reason_id);

CREATE TABLE imed.base_dose_unit (
	base_dose_unit_id VARCHAR(255) NOT NULL,
	description_th VARCHAR(255),
	description_en VARCHAR(255)
);
ALTER TABLE imed.base_dose_unit ADD CONSTRAINT base_dose_unit_pkey PRIMARY KEY (base_dose_unit_id);

CREATE TABLE imed.base_drug_caution (
	base_drug_caution_id VARCHAR(255) NOT NULL,
	description_th VARCHAR(255),
	description_en VARCHAR(255)
);
ALTER TABLE imed.base_drug_caution ADD CONSTRAINT base_drug_caution_pkey PRIMARY KEY (base_drug_caution_id);

CREATE TABLE imed.base_drug_dose (
	base_drug_dose_id VARCHAR(255) NOT NULL,
	description_th VARCHAR(255),
	description_en VARCHAR(255),
	is_dose_unit_include VARCHAR(255)
);
ALTER TABLE imed.base_drug_dose ADD CONSTRAINT base_drug_dose_pkey PRIMARY KEY (base_drug_dose_id);

CREATE TABLE imed.base_drug_format (
	base_drug_format_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_drug_format ADD CONSTRAINT base_drug_format_pkey PRIMARY KEY (base_drug_format_id);

CREATE TABLE imed.base_drug_frequency (
	base_drug_frequency_id VARCHAR(255) NOT NULL,
	description_th VARCHAR(255),
	description_en VARCHAR(255)
);
ALTER TABLE imed.base_drug_frequency ADD CONSTRAINT base_drug_frequency_pkey PRIMARY KEY (base_drug_frequency_id);

CREATE TABLE imed.base_drug_generic_group_name (
	base_drug_name_id VARCHAR(255) NOT NULL,
	base_drug_generic_name VARCHAR(255),
	base_drug_group_name VARCHAR(255)
);
ALTER TABLE imed.base_drug_generic_group_name ADD CONSTRAINT base_drug_generic_group_name_pkey PRIMARY KEY (base_drug_name_id);
CREATE INDEX bdggn_generic_name ON imed.base_drug_generic_group_name(base_drug_generic_name);
CREATE INDEX bdggn_group_name ON imed.base_drug_generic_group_name(base_drug_group_name);

CREATE TABLE imed.base_drug_generic_name (
	base_drug_generic_name VARCHAR(255) NOT NULL
);
ALTER TABLE imed.base_drug_generic_name ADD CONSTRAINT base_drug_generic_name_pkey PRIMARY KEY (base_drug_generic_name);

CREATE TABLE imed.base_drug_generic_trade_name (
	base_drug_name_id VARCHAR(255) NOT NULL,
	base_drug_generic_name VARCHAR(255),
	base_drug_trade_name VARCHAR(255)
);
ALTER TABLE imed.base_drug_generic_trade_name ADD CONSTRAINT base_drug_generic_trade_name_pkey PRIMARY KEY (base_drug_name_id);
CREATE INDEX bdgtn_generic_name ON imed.base_drug_generic_trade_name(base_drug_generic_name);
CREATE INDEX bdgtn_trade_name ON imed.base_drug_generic_trade_name(base_drug_trade_name);

CREATE TABLE imed.base_drug_group (
	base_drug_group_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_drug_group ADD CONSTRAINT base_drug_group_pkey PRIMARY KEY (base_drug_group_id);

CREATE TABLE imed.base_drug_group_name (
	base_drug_group_name VARCHAR(255) NOT NULL
);
ALTER TABLE imed.base_drug_group_name ADD CONSTRAINT base_drug_group_name_pkey PRIMARY KEY (base_drug_group_name);

CREATE TABLE imed.base_drug_instruction (
	base_drug_instruction_id VARCHAR(255) NOT NULL,
	description_th VARCHAR(255),
	description_en VARCHAR(255),
	base_dose_unit_id VARCHAR(255)
);
ALTER TABLE imed.base_drug_instruction ADD CONSTRAINT base_drug_instruction_pkey PRIMARY KEY (base_drug_instruction_id);

CREATE TABLE imed.base_drug_pain (
	base_drug_pain_id VARCHAR(255) NOT NULL,
	description_th VARCHAR(255),
	description_en VARCHAR(255)
);
ALTER TABLE imed.base_drug_pain ADD CONSTRAINT base_drug_pain_pkey PRIMARY KEY (base_drug_pain_id);

CREATE TABLE imed.base_drug_special (
	base_drug_special_id VARCHAR(255) NOT NULL,
	description_th VARCHAR(255),
	description_th_line2 VARCHAR(255),
	description_en VARCHAR(255),
	description_en_line2 VARCHAR(255)
);
ALTER TABLE imed.base_drug_special ADD CONSTRAINT base_drug_special_pkey PRIMARY KEY (base_drug_special_id);

CREATE TABLE imed.base_drug_time (
	base_drug_time_id VARCHAR(255) NOT NULL,
	description_th VARCHAR(255),
	description_en VARCHAR(255)
);
ALTER TABLE imed.base_drug_time ADD CONSTRAINT base_drug_time_pkey PRIMARY KEY (base_drug_time_id);

CREATE TABLE imed.base_drug_trade_name (
	base_drug_trade_name VARCHAR(255) NOT NULL
);
ALTER TABLE imed.base_drug_trade_name ADD CONSTRAINT base_drug_trade_name_pkey PRIMARY KEY (base_drug_trade_name);

CREATE TABLE imed.base_drug_type (
	base_drug_type_id VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	base_dose_unit_id VARCHAR(255),
	base_drug_instruction_id VARCHAR(255),
	icon_name VARCHAR(255)
);
ALTER TABLE imed.base_drug_type ADD CONSTRAINT base_drug_type_pkey PRIMARY KEY (base_drug_type_id);

CREATE TABLE imed.base_drug_usage (
	base_drug_usage_id VARCHAR(255) NOT NULL,
	base_drug_usage_code VARCHAR(255),
	instruction_format VARCHAR(255),
	base_drug_instruction_id VARCHAR(255),
	dose_quantity VARCHAR(255),
	base_dose_unit_id VARCHAR(255),
	base_drug_frequency_id VARCHAR(255),
	instruction_text_line1 VARCHAR(255),
	instruction_text_line2 VARCHAR(255),
	instruction_text_line3 VARCHAR(255),
	instruction_text_line1_en VARCHAR(255),
	instruction_text_line2_en VARCHAR(255),
	instruction_text_line3_en VARCHAR(255)
);
ALTER TABLE imed.base_drug_usage ADD CONSTRAINT base_drug_usage_pkey PRIMARY KEY (base_drug_usage_id);
CREATE INDEX base_drug_usag_ebase_drug_usage_code ON imed.base_drug_usage(base_drug_usage_code);

CREATE TABLE imed.base_employee_role (
	base_employee_role_id VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	tabmenu_auth VARCHAR(255),
	mainmenu_auth VARCHAR(255),
	patient_data_submenu_auth VARCHAR(255),
	service_submenu_auth VARCHAR(255),
	order_submenu_auth VARCHAR(255),
	cash_submenu_auth VARCHAR(255),
	appointment_submenu_auth VARCHAR(255),
	opd_submenu_auth VARCHAR(255),
	ipd_submenu_auth VARCHAR(255),
	medical_submenu_auth VARCHAR(255),
	xray_submenu_auth VARCHAR(255),
	schedule_submenu_auth VARCHAR(255),
	print_submenu_auth VARCHAR(255),
	system_submenu_auth VARCHAR(255),
	patient_social_button_auth VARCHAR(255),
	operation_button_auth VARCHAR(255),
	labor_button_auth VARCHAR(255),
	emergency_button_auth VARCHAR(255),
	patient_medical_button_auth VARCHAR(255),
	lab_button_auth VARCHAR(255),
	xray_button_auth VARCHAR(255),
	vital_sign_button_auth VARCHAR(255),
	order_button_auth VARCHAR(255),
	icd10_button_auth VARCHAR(255),
	icd9_button_auth VARCHAR(255),
	billing_opd_button_auth VARCHAR(255),
	billing_ipd_button_auth VARCHAR(255),
	admit_button_auth VARCHAR(255),
	emr_auth VARCHAR(255),
	order_auth VARCHAR(255),
	extra_auth VARCHAR(255),
	page_default VARCHAR(255),
	admin_module_auth VARCHAR(255),
	admin_system_auth VARCHAR(255),
	admin_user_and_sp_auth VARCHAR(255),
	admin_item_auth VARCHAR(255),
	admin_drug_detail_auth VARCHAR(255),
	admin_stock_auth VARCHAR(255),
	admin_stock_manage_auth VARCHAR(255),
	admin_stock_report_auth VARCHAR(255),
	admin_medical_auth VARCHAR(255),
	admin_lab_auth VARCHAR(255),
	admin_xray_auth VARCHAR(255),
	admin_operation_auth VARCHAR(255),
	admin_register_auth VARCHAR(255),
	admin_dental_auth VARCHAR(255),
	admin_billing_auth VARCHAR(255),
	admin_other_auth VARCHAR(255),
	admin_report_auth VARCHAR(255),
	admin_manage_item_auth VARCHAR(255),
	admin_manage_base_auth VARCHAR(255),
	admin_manage_help_auth VARCHAR(255),
	report_tab_module_auth VARCHAR(255),
	custom_tab_auth VARCHAR(255)
);
ALTER TABLE imed.base_employee_role ADD CONSTRAINT base_employee_role_pkey PRIMARY KEY (base_employee_role_id);

CREATE TABLE imed.base_episiotomy_repaired (
	base_episiotomy_repaired_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_episiotomy_repaired ADD CONSTRAINT base_episiotomy_repaired_pkey PRIMARY KEY (base_episiotomy_repaired_id);

CREATE TABLE imed.base_episiotomy_type (
	base_episiotomy_type_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_episiotomy_type ADD CONSTRAINT base_episiotomy_type_pkey PRIMARY KEY (base_episiotomy_type_id);

CREATE TABLE imed.base_episiotomy_wound (
	base_episiotomy_wound_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_episiotomy_wound ADD CONSTRAINT base_episiotomy_wound_pkey PRIMARY KEY (base_episiotomy_wound_id);

CREATE TABLE imed.base_fetal_position (
	base_fetal_position_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_fetal_position ADD CONSTRAINT base_fetal_position_pkey PRIMARY KEY (base_fetal_position_id);

CREATE TABLE imed.base_gross_appearance (
	base_gross_appearance_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_gross_appearance ADD CONSTRAINT base_gross_appearance_pkey PRIMARY KEY (base_gross_appearance_id);

CREATE TABLE imed.base_indication (
	base_indication_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_indication ADD CONSTRAINT base_indication_pkey PRIMARY KEY (base_indication_id);

CREATE TABLE imed.base_induction_method (
	base_induction_method_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_induction_method ADD CONSTRAINT base_induction_method_pkey PRIMARY KEY (base_induction_method_id);

CREATE TABLE imed.base_infant_activity (
	base_infant_activity_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_infant_activity ADD CONSTRAINT base_infant_activity_pkey PRIMARY KEY (base_infant_activity_id);

CREATE TABLE imed.base_infant_aspiration (
	base_infant_aspiration_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_infant_aspiration ADD CONSTRAINT base_infant_aspiration_pkey PRIMARY KEY (base_infant_aspiration_id);

CREATE TABLE imed.base_infant_breathing (
	base_infant_breathing_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_infant_breathing ADD CONSTRAINT base_infant_breathing_pkey PRIMARY KEY (base_infant_breathing_id);

CREATE TABLE imed.base_infant_color (
	base_infant_color_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_infant_color ADD CONSTRAINT base_infant_color_pkey PRIMARY KEY (base_infant_color_id);

CREATE TABLE imed.base_infant_condition (
	base_infant_condition_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_infant_condition ADD CONSTRAINT base_infant_condition_pkey PRIMARY KEY (base_infant_condition_id);

CREATE TABLE imed.base_infant_crying (
	base_infant_crying_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_infant_crying ADD CONSTRAINT base_infant_crying_pkey PRIMARY KEY (base_infant_crying_id);

CREATE TABLE imed.base_infant_death_cause (
	base_infant_death_cause_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_infant_death_cause ADD CONSTRAINT base_infant_death_cause_pkey PRIMARY KEY (base_infant_death_cause_id);

CREATE TABLE imed.base_infant_death_type (
	base_infant_death_type_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_infant_death_type ADD CONSTRAINT base_infant_death_type_pkey PRIMARY KEY (base_infant_death_type_id);

CREATE TABLE imed.base_infant_eye_drugs (
	base_infant_eye_drugs_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_infant_eye_drugs ADD CONSTRAINT base_infant_eye_drugs_pkey PRIMARY KEY (base_infant_eye_drugs_id);

CREATE TABLE imed.base_infant_resuscitation (
	base_infant_resuscitation_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_infant_resuscitation ADD CONSTRAINT base_infant_resuscitation_pkey PRIMARY KEY (base_infant_resuscitation_id);

CREATE TABLE imed.base_infant_status (
	base_infant_status_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_infant_status ADD CONSTRAINT base_infant_status_pkey PRIMARY KEY (base_infant_status_id);

CREATE TABLE imed.base_infant_vit_k1 (
	base_infant_vit_k1_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_infant_vit_k1 ADD CONSTRAINT base_infant_vit_k1_pkey PRIMARY KEY (base_infant_vit_k1_id);

CREATE TABLE imed.base_insertion_of_cord (
	base_insertion_of_cord_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_insertion_of_cord ADD CONSTRAINT base_insertion_of_cord_pkey PRIMARY KEY (base_insertion_of_cord_id);

CREATE TABLE imed.base_invoice_group (
	base_invoice_group_id VARCHAR(255) NOT NULL,
	description_th VARCHAR(255),
	description_en VARCHAR(255)
);
ALTER TABLE imed.base_invoice_group ADD CONSTRAINT base_invoice_group_pkey PRIMARY KEY (base_invoice_group_id);

CREATE TABLE imed.base_item_xray_film (
	base_item_xray_film_id VARCHAR(254) NOT NULL,
	item_id VARCHAR(254),
	base_xray_film_id VARCHAR(254),
	film_quantity VARCHAR(254)
);
ALTER TABLE imed.base_item_xray_film ADD CONSTRAINT base_item_xray_film_pkey PRIMARY KEY (base_item_xray_film_id);

CREATE TABLE imed.base_lab_type (
	base_lab_type_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_lab_type ADD CONSTRAINT base_lab_type_pkey PRIMARY KEY (base_lab_type_id);

CREATE TABLE imed.base_ldap (
	base_ldap_id VARCHAR(255) NOT NULL,
	dn VARCHAR(255),
	ldap_server VARCHAR(255),
	ldap_port VARCHAR(255),
	active VARCHAR(255)
);
ALTER TABLE imed.base_ldap ADD CONSTRAINT base_ldap_pkey PRIMARY KEY (base_ldap_id);

CREATE TABLE imed.base_long_waiting_reason (
	base_long_waiting_reason_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_long_waiting_reason ADD CONSTRAINT base_long_waiting_reason_pkey PRIMARY KEY (base_long_waiting_reason_id);

CREATE TABLE imed.base_micro_result (
	base_micro_result_id VARCHAR(254) NOT NULL,
	description VARCHAR(254)
);
ALTER TABLE imed.base_micro_result ADD CONSTRAINT base_micro_result_pkey PRIMARY KEY (base_micro_result_id);

CREATE TABLE imed.base_office (
	base_office_id VARCHAR(255) NOT NULL,
	off_name VARCHAR(255) NOT NULL,
	full_name VARCHAR(255) NOT NULL,
	changwat VARCHAR(255) NOT NULL,
	amphur VARCHAR(255) NOT NULL
);
ALTER TABLE imed.base_office ADD CONSTRAINT base_office_pkey PRIMARY KEY (base_office_id);
CREATE INDEX base_office_amphur_key ON imed.base_office(amphur);
CREATE INDEX base_office_changwat_key ON imed.base_office(changwat);

CREATE TABLE imed.base_onset_type (
	base_onset_type_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_onset_type ADD CONSTRAINT base_onset_type_pkey PRIMARY KEY (base_onset_type_id);

CREATE TABLE imed.base_op_category (
	base_op_category_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_op_category ADD CONSTRAINT base_op_category_pkey PRIMARY KEY (base_op_category_id);

CREATE TABLE imed.base_op_role (
	base_op_role_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_op_role ADD CONSTRAINT base_op_role_pkey PRIMARY KEY (base_op_role_id);

CREATE TABLE imed.base_op_room (
	base_op_room_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_op_room ADD CONSTRAINT base_op_room_pkey PRIMARY KEY (base_op_room_id);

CREATE TABLE imed.base_operation_employee (
	base_operation_employee_id VARCHAR(255) NOT NULL,
	base_operation_set_id VARCHAR(255),
	base_clinic_id VARCHAR(255)
);
ALTER TABLE imed.base_operation_employee ADD CONSTRAINT base_operation_employee_pkey PRIMARY KEY (base_operation_employee_id);
CREATE INDEX base_op_emp_clinic_id ON imed.base_operation_employee(base_clinic_id);

CREATE TABLE imed.base_operation_set (
	base_operation_set_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_operation_set ADD CONSTRAINT base_operation_set_pkey PRIMARY KEY (base_operation_set_id);

CREATE TABLE imed.base_organic (
	base_organic_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_organic ADD CONSTRAINT base_organic_pkey PRIMARY KEY (base_organic_id);

CREATE TABLE imed.base_organisms (
	base_organisms_id VARCHAR(254) NOT NULL,
	description VARCHAR(254)
);
ALTER TABLE imed.base_organisms ADD CONSTRAINT base_organisms_pkey PRIMARY KEY (base_organisms_id);

CREATE TABLE imed.base_out_doctor (
	base_out_doctor_id VARCHAR(255) NOT NULL,
	prename VARCHAR(255),
	firstname VARCHAR(255),
	lastname VARCHAR(255),
	note TEXT
);
ALTER TABLE imed.base_out_doctor ADD CONSTRAINT base_out_doctor_pkey PRIMARY KEY (base_out_doctor_id);
CREATE INDEX out_doctor_firstname ON imed.base_out_doctor(firstname);

CREATE TABLE imed.base_paid_method (
	base_paid_method_id VARCHAR(255) NOT NULL,
	description_th VARCHAR(255),
	description_en VARCHAR(255),
	fix_paid_method_id VARCHAR(255)
);
ALTER TABLE imed.base_paid_method ADD CONSTRAINT base_paid_method_pkey PRIMARY KEY (base_paid_method_id);

CREATE TABLE imed.base_patient_type (
	base_patient_type_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_patient_type ADD CONSTRAINT base_patient_type_pkey PRIMARY KEY (base_patient_type_id);

CREATE TABLE imed.base_placenta_removed (
	base_placenta_removed_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_placenta_removed ADD CONSTRAINT base_placenta_removed_pkey PRIMARY KEY (base_placenta_removed_id);

CREATE TABLE imed.base_plan_group (
	base_plan_group_id VARCHAR(255) NOT NULL,
	base_plan_group_code VARCHAR(255),
	description VARCHAR(255),
	print_form_cr VARCHAR(255)
);
ALTER TABLE imed.base_plan_group ADD CONSTRAINT base_plan_group_pkey PRIMARY KEY (base_plan_group_id);

CREATE TABLE imed.base_pregnancy_comorbidity (
	base_pregnancy_comorbidity_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_pregnancy_comorbidity ADD CONSTRAINT base_pregnancy_comorbidity_pkey PRIMARY KEY (base_pregnancy_comorbidity_id);

CREATE TABLE imed.base_pregnancy_complication (
	base_pregnancy_complication_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_pregnancy_complication ADD CONSTRAINT base_pregnancy_complication_pkey PRIMARY KEY (base_pregnancy_complication_id);

CREATE TABLE imed.base_pregnancy_status (
	base_pregnancy_status_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_pregnancy_status ADD CONSTRAINT base_pregnancy_status_pkey PRIMARY KEY (base_pregnancy_status_id);

CREATE TABLE imed.base_prename (
	base_prename_id VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	fix_gender_id VARCHAR(255)
);
ALTER TABLE imed.base_prename ADD CONSTRAINT base_prename_pkey PRIMARY KEY (base_prename_id);

CREATE TABLE imed.base_receipt_category (
	base_receipt_category_id VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	receipt_title TEXT
);
ALTER TABLE imed.base_receipt_category ADD CONSTRAINT base_receipt_category_pkey PRIMARY KEY (base_receipt_category_id);

CREATE TABLE imed.base_religion (
	base_religion_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_religion ADD CONSTRAINT base_religion_pkey PRIMARY KEY (base_religion_id);

CREATE TABLE imed.base_room (
	base_room_id VARCHAR(255) NOT NULL,
	base_service_point_id VARCHAR(255),
	room_number VARCHAR(255),
	bed_number VARCHAR(255),
	fix_bed_status_id VARCHAR(255),
	fix_service_status_id VARCHAR(255),
	base_room_type_id VARCHAR(255)
);
ALTER TABLE imed.base_room ADD CONSTRAINT base_room_pkey PRIMARY KEY (base_room_id);
CREATE INDEX base_room_base_service_point_id ON imed.base_room(base_service_point_id);
CREATE INDEX base_room_bed_number ON imed.base_room(bed_number);
CREATE INDEX base_room_fix_bed_status_id ON imed.base_room(fix_bed_status_id);
CREATE INDEX base_room_room_number ON imed.base_room(room_number);
CREATE INDEX base_room_service_status ON imed.base_room(fix_service_status_id);
CREATE INDEX base_room_type_id ON imed.base_room(base_room_type_id);

CREATE TABLE imed.base_room_item (
	base_room_item_id VARCHAR(254) NOT NULL,
	base_room_id VARCHAR(254),
	item_id VARCHAR(254),
	is_room_order VARCHAR(255)
);
ALTER TABLE imed.base_room_item ADD CONSTRAINT base_room_item_pkey PRIMARY KEY (base_room_item_id);
CREATE INDEX base_room_item_room_id ON imed.base_room_item(base_room_id);

CREATE TABLE imed.base_room_type (
	base_room_type_id VARCHAR(254) NOT NULL,
	description VARCHAR(254)
);
ALTER TABLE imed.base_room_type ADD CONSTRAINT base_room_type_pkey PRIMARY KEY (base_room_type_id);

CREATE TABLE imed.base_service_point (
	base_service_point_id VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	fix_service_point_type_id VARCHAR(255),
	fix_service_point_subtype_id VARCHAR(255),
	fix_service_point_group_id VARCHAR(255),
	ward_code VARCHAR(255),
	base_department_id VARCHAR(255),
	stock_id VARCHAR(255),
	max_waiting_time VARCHAR(255),
	setup_queue_column VARCHAR(255),
	sort_queue_type VARCHAR(255),
	active VARCHAR(255),
	base_clinic_id VARCHAR(255)
);
ALTER TABLE imed.base_service_point ADD CONSTRAINT base_service_point_pkey PRIMARY KEY (base_service_point_id);

CREATE TABLE imed.base_service_point_role (
	base_service_point_role_id VARCHAR(255) NOT NULL,
	base_service_point_id VARCHAR(255),
	tabmenu_auth VARCHAR(255),
	mainmenu_auth VARCHAR(255)
);
ALTER TABLE imed.base_service_point_role ADD CONSTRAINT base_service_point_role_pkey PRIMARY KEY (base_service_point_role_id);

CREATE TABLE imed.base_site (
	base_site_id VARCHAR(255) NOT NULL,
	site_name VARCHAR(255),
	address VARCHAR(255),
	village VARCHAR(255),
	tambol VARCHAR(255),
	amphur VARCHAR(255),
	changwat VARCHAR(255),
	postcode VARCHAR(255),
	tel VARCHAR(255),
	protect_table VARCHAR(255),
	login_message_active VARCHAR(255),
	login_message TEXT
);
ALTER TABLE imed.base_site ADD CONSTRAINT base_site_pkey PRIMARY KEY (base_site_id);

CREATE TABLE imed.base_specimen (
	base_specimen_id VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	fix_specimen_category_id VARCHAR(255)
);
ALTER TABLE imed.base_specimen ADD CONSTRAINT base_specimen_pkey PRIMARY KEY (base_specimen_id);

CREATE TABLE imed.base_sql_group (
	base_sql_group_id VARCHAR(255) NOT NULL,
	base_sql_group_code VARCHAR(255),
	base_sql_group_name VARCHAR(255)
);
ALTER TABLE imed.base_sql_group ADD CONSTRAINT base_sql_group_pkey PRIMARY KEY (base_sql_group_id);

CREATE TABLE imed.base_tariff (
	base_tariff_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_tariff ADD CONSTRAINT base_tariff_pkey PRIMARY KEY (base_tariff_id);

CREATE TABLE imed.base_template_advice (
	base_template_advice_id VARCHAR(255) NOT NULL,
	description TEXT
);
ALTER TABLE imed.base_template_advice ADD CONSTRAINT base_template_advice_pkey PRIMARY KEY (base_template_advice_id);
CREATE INDEX base_template_advice_description ON imed.base_template_advice(description);

CREATE TABLE imed.base_template_app_detail (
	base_template_app_detail_id VARCHAR(255) NOT NULL,
	description TEXT
);
ALTER TABLE imed.base_template_app_detail ADD CONSTRAINT base_template_app_detail_pkey PRIMARY KEY (base_template_app_detail_id);
CREATE INDEX base_template_app_description ON imed.base_template_app_detail(description);

CREATE TABLE imed.base_template_diagnosis (
	base_template_diagnosis_id VARCHAR(255) NOT NULL,
	base_diagnosis_set_id VARCHAR(255),
	description VARCHAR(255),
	description_th VARCHAR(255),
	icd10_code VARCHAR(255)
);
ALTER TABLE imed.base_template_diagnosis ADD CONSTRAINT base_template_diagnosis_pkey PRIMARY KEY (base_template_diagnosis_id);
CREATE INDEX template_diagnosis_description ON imed.base_template_diagnosis(description);
CREATE INDEX template_diagnosis_icd10_code ON imed.base_template_diagnosis(icd10_code);
CREATE INDEX template_diagnosis_set ON imed.base_template_diagnosis(base_diagnosis_set_id);

CREATE TABLE imed.base_template_doctor_note (
	base_template_doctor_note_id VARCHAR(255) NOT NULL,
	description TEXT,
	employee_id VARCHAR(255),
	base_clinic_id VARCHAR(255)
);
ALTER TABLE imed.base_template_doctor_note ADD CONSTRAINT base_template_doctor_note_pkey PRIMARY KEY (base_template_doctor_note_id);
CREATE INDEX doctor_note_base_clinic_id ON imed.base_template_doctor_note(base_clinic_id);
CREATE INDEX doctor_note_description ON imed.base_template_doctor_note(description);
CREATE INDEX doctor_note_employee_id ON imed.base_template_doctor_note(employee_id);

CREATE TABLE imed.base_template_illness (
	base_template_illness_id VARCHAR(255) NOT NULL,
	description TEXT,
	base_clinic_id VARCHAR(255)
);
ALTER TABLE imed.base_template_illness ADD CONSTRAINT base_template_illness_pkey PRIMARY KEY (base_template_illness_id);
CREATE INDEX base_template_illness_clinic ON imed.base_template_illness(base_clinic_id);
CREATE INDEX base_template_illness_description ON imed.base_template_illness(description);

CREATE TABLE imed.base_template_lab_result (
	base_template_lab_result_id VARCHAR(255) NOT NULL,
	description TEXT
);
ALTER TABLE imed.base_template_lab_result ADD CONSTRAINT base_template_lab_result_pkey PRIMARY KEY (base_template_lab_result_id);
CREATE INDEX template_lab_result_description ON imed.base_template_lab_result(description);

CREATE TABLE imed.base_template_operation (
	base_template_operation_id VARCHAR(255) NOT NULL,
	base_operation_set_id VARCHAR(255),
	description VARCHAR(255),
	description_th VARCHAR(255),
	icd9_code VARCHAR(255),
	item_id VARCHAR(255)
);
ALTER TABLE imed.base_template_operation ADD CONSTRAINT base_template_operation_pkey PRIMARY KEY (base_template_operation_id);
CREATE INDEX template_operation_description ON imed.base_template_operation(description);
CREATE INDEX template_operation_icd9_code ON imed.base_template_operation(icd9_code);
CREATE INDEX template_operation_item_id ON imed.base_template_operation(item_id);
CREATE INDEX template_operation_set ON imed.base_template_operation(base_operation_set_id);

CREATE TABLE imed.base_template_phyex (
	base_template_phyex_id VARCHAR(255) NOT NULL,
	description TEXT,
	employee_id VARCHAR(255),
	base_clinic_id VARCHAR(255),
	base_organic_id VARCHAR(255)
);
ALTER TABLE imed.base_template_phyex ADD CONSTRAINT base_template_phyex_pkey PRIMARY KEY (base_template_phyex_id);
CREATE INDEX template_phyex_clinic ON imed.base_template_phyex(base_clinic_id);
CREATE INDEX template_phyex_description ON imed.base_template_phyex(description);
CREATE INDEX template_phyex_employee_id ON imed.base_template_phyex(employee_id);
CREATE INDEX template_phyex_organic ON imed.base_template_phyex(base_organic_id);

CREATE TABLE imed.base_template_symptom (
	base_template_symptom_id VARCHAR(255) NOT NULL,
	description TEXT,
	base_clinic_id VARCHAR(255)
);
ALTER TABLE imed.base_template_symptom ADD CONSTRAINT base_template_symptom_pkey PRIMARY KEY (base_template_symptom_id);
CREATE INDEX base_template_symptom_clinic ON imed.base_template_symptom(base_clinic_id);
CREATE INDEX base_template_symptom_description ON imed.base_template_symptom(description);

CREATE TABLE imed.base_template_xray_result (
	base_template_xray_result_id VARCHAR(255) NOT NULL,
	description TEXT
);
ALTER TABLE imed.base_template_xray_result ADD CONSTRAINT base_template_xray_result_pkey PRIMARY KEY (base_template_xray_result_id);
CREATE INDEX template_xray_result_description ON imed.base_template_xray_result(description);

CREATE TABLE imed.base_unit (
	base_unit_id VARCHAR(255) NOT NULL,
	description_th VARCHAR(255),
	description_en VARCHAR(255)
);
ALTER TABLE imed.base_unit ADD CONSTRAINT base_unit_pkey PRIMARY KEY (base_unit_id);

CREATE TABLE imed.base_uterine_contraction (
	base_uterine_contraction_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.base_uterine_contraction ADD CONSTRAINT base_uterine_contraction_pkey PRIMARY KEY (base_uterine_contraction_id);

CREATE TABLE imed.base_xray_film (
	base_xray_film_id VARCHAR(254) NOT NULL,
	description VARCHAR(254),
	unit_price_cost VARCHAR(254),
	unit_price_sale VARCHAR(254)
);
ALTER TABLE imed.base_xray_film ADD CONSTRAINT base_xray_film_pkey PRIMARY KEY (base_xray_film_id);

CREATE TABLE imed.base_xray_type (
	base_xray_type_id VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	fix_xray_type_id VARCHAR(255)
);
ALTER TABLE imed.base_xray_type ADD CONSTRAINT base_xray_type_pkey PRIMARY KEY (base_xray_type_id);

CREATE TABLE imed.bed_booking (
	bed_booking_id VARCHAR(254) NOT NULL,
	booking_name VARCHAR(254),
	patient_id VARCHAR(254),
	note VARCHAR(50),
	move_date VARCHAR(254),
	move_time VARCHAR(254),
	base_room_id VARCHAR(254),
	base_service_point_id VARCHAR(254),
	room_number VARCHAR(254),
	bed_number VARCHAR(254),
	base_room_type_id VARCHAR(254),
	booking_date VARCHAR(254),
	booking_time VARCHAR(254),
	modify_eid VARCHAR(254),
	modify_date VARCHAR(254),
	modify_time VARCHAR(254)
);
ALTER TABLE imed.bed_booking ADD CONSTRAINT bed_booking_pkey PRIMARY KEY (bed_booking_id);
CREATE INDEX bed_booking_booking_name ON imed.bed_booking(booking_name);
CREATE INDEX bed_booking_patient_id ON imed.bed_booking(patient_id);

CREATE TABLE imed.bed_management (
	bed_management_id VARCHAR(254) NOT NULL,
	admit_id VARCHAR(254),
	base_room_id VARCHAR(254),
	base_service_point_id VARCHAR(254),
	room_number VARCHAR(254),
	bed_number VARCHAR(254),
	base_room_type_id VARCHAR(254),
	move_date VARCHAR(254),
	move_time VARCHAR(254),
	move_out_date VARCHAR(254),
	move_out_time VARCHAR(254),
	current_bed VARCHAR(254),
	ward_code VARCHAR(254),
	base_department_id VARCHAR(254),
	note TEXT,
	modify_eid VARCHAR(254),
	modify_date VARCHAR(254),
	modify_time VARCHAR(254),
	order_continue_id VARCHAR(50),
	base_clinic_id VARCHAR(255)
);
ALTER TABLE imed.bed_management ADD CONSTRAINT bed_management_pkey PRIMARY KEY (bed_management_id);
CREATE INDEX bed_management_admit_id ON imed.bed_management(admit_id);
CREATE INDEX bed_management_base_sp_id ON imed.bed_management(base_service_point_id);
CREATE INDEX bed_management_current_bed ON imed.bed_management(current_bed);
CREATE INDEX idx_bed_mgnt_room_id ON imed.bed_management(base_room_id);

CREATE TABLE imed.bed_mgnt_order_continue (
	bed_mgnt_order_continue_id VARCHAR(255) NOT NULL,
	bed_management_id VARCHAR(255),
	order_continue_id VARCHAR(255)
);
ALTER TABLE imed.bed_mgnt_order_continue ADD CONSTRAINT bed_mgnt_order_continue_pkey PRIMARY KEY (bed_mgnt_order_continue_id);
CREATE INDEX bed_mgnt_order_cont_id ON imed.bed_mgnt_order_continue(bed_management_id);

CREATE TABLE imed.connection_profile (
	connection_profile_id VARCHAR(255) NOT NULL,
	connection_profile_name VARCHAR(255),
	driver_jdbc VARCHAR(255),
	server_ip VARCHAR(255),
	port VARCHAR(255),
	database_name VARCHAR(255),
	username VARCHAR(255),
	password VARCHAR(255)
);
ALTER TABLE imed.connection_profile ADD CONSTRAINT connection_profile_pkey PRIMARY KEY (connection_profile_id);

CREATE TABLE imed.current_drug_use (
	current_drug_use_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	drug_name VARCHAR(255),
	note VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.current_drug_use ADD CONSTRAINT current_drug_use_pkey PRIMARY KEY (current_drug_use_id);
CREATE INDEX patient_id_current_drug_use_key ON imed.current_drug_use(patient_id);

CREATE TABLE imed.daily_record_ipd (
	daily_record_ipd_id VARCHAR(255) NOT NULL,
	admit_id VARCHAR(255),
	weight VARCHAR(255),
	height VARCHAR(255),
	diet VARCHAR(255),
	intake_oral_fluide VARCHAR(255),
	intake_parenteral VARCHAR(255),
	intake_other VARCHAR(255),
	output_urine VARCHAR(255),
	output_emesis VARCHAR(255),
	output_drainage VARCHAR(255),
	output_aspiration VARCHAR(255),
	output_other VARCHAR(255),
	stools_times VARCHAR(255),
	urine_times VARCHAR(255),
	medications VARCHAR(255),
	note VARCHAR(255),
	bmi VARCHAR(255),
	measure_eid VARCHAR(255),
	measure_date VARCHAR(255),
	measure_time VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.daily_record_ipd ADD CONSTRAINT daily_record_ipd_pkey PRIMARY KEY (daily_record_ipd_id);
CREATE INDEX daily_record_ipd_admit_id ON imed.daily_record_ipd(admit_id);

CREATE TABLE imed.death (
	death_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	visit_id VARCHAR(255),
	base_death_status_id VARCHAR(255),
	death_cause VARCHAR(255),
	death_date VARCHAR(255),
	death_time VARCHAR(255),
	comment VARCHAR(255)
);
ALTER TABLE imed.death ADD CONSTRAINT death_pkey PRIMARY KEY (death_id);

CREATE TABLE imed.dental (
	dental_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	visit_id VARCHAR(255),
	base_dent_organic_id VARCHAR(255),
	base_dent_diagnosis_id VARCHAR(255),
	diagnosis_date VARCHAR(255),
	diagnosis_time VARCHAR(255),
	note TEXT,
	doctor_eid VARCHAR(255),
	dental_date VARCHAR(255),
	dental_time VARCHAR(255),
	plan_status VARCHAR(255),
	plan_priority VARCHAR(255),
	make_plan_eid VARCHAR(255),
	continue_type VARCHAR(255),
	continue_ref_no VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.dental ADD CONSTRAINT dental_pkey PRIMARY KEY (dental_id);
CREATE INDEX dental_cont_ref_no ON imed.dental(continue_ref_no);
CREATE INDEX dental_continue_type ON imed.dental(continue_type);
CREATE INDEX dental_dent_organic ON imed.dental(base_dent_organic_id);
CREATE INDEX dental_dental_date ON imed.dental(dental_date);
CREATE INDEX dental_dental_time ON imed.dental(dental_time);
CREATE INDEX dental_patient_id ON imed.dental(patient_id);
CREATE INDEX dental_visit_id ON imed.dental(visit_id);

CREATE TABLE imed.dental_order (
	dental_order_id VARCHAR(255) NOT NULL,
	dental_id VARCHAR(255),
	order_item_id VARCHAR(255),
	item_id VARCHAR(255),
	base_dent_operation_id VARCHAR(255),
	description VARCHAR(255),
	doctor_eid VARCHAR(255),
	operation_date VARCHAR(255),
	operation_time VARCHAR(255),
	continue_ref_no VARCHAR(255),
	beginning_price VARCHAR(255)
);
ALTER TABLE imed.dental_order ADD CONSTRAINT dental_order_pkey PRIMARY KEY (dental_order_id);
CREATE INDEX dental_order_cont_ref_no ON imed.dental_order(continue_ref_no);
CREATE INDEX dental_order_dental_id ON imed.dental_order(dental_id);
CREATE INDEX dental_order_operation_date ON imed.dental_order(operation_date);
CREATE INDEX dental_order_operation_time ON imed.dental_order(operation_time);
CREATE INDEX dental_order_order_item ON imed.dental_order(order_item_id);

CREATE TABLE imed.dental_plan_order (
	dental_plan_order_id VARCHAR(255) NOT NULL,
	dental_id VARCHAR(255),
	item_id VARCHAR(255),
	fix_item_type_id VARCHAR(255),
	base_dent_operation_id VARCHAR(255),
	base_drug_usage_code VARCHAR(255),
	instruction_format VARCHAR(255),
	base_drug_instruction_id VARCHAR(255),
	dose_quantity VARCHAR(255),
	base_dose_unit_id VARCHAR(255),
	base_drug_frequency_id VARCHAR(255),
	instruction_text_line1 VARCHAR(255),
	instruction_text_line2 VARCHAR(255),
	instruction_text_line3 VARCHAR(255),
	quantity VARCHAR(255),
	base_unit_id VARCHAR(255),
	description TEXT,
	caution TEXT,
	beginning_price VARCHAR(255),
	doctor_eid VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.dental_plan_order ADD CONSTRAINT dental_plan_order_pkey PRIMARY KEY (dental_plan_order_id);
CREATE INDEX dental_plan_order_dental_id ON imed.dental_plan_order(dental_id);

CREATE TABLE imed.dental_teeth (
	dental_teeth_id VARCHAR(255) NOT NULL,
	dental_id VARCHAR(255),
	tooth_number VARCHAR(255),
	tooth_part VARCHAR(255)
);
ALTER TABLE imed.dental_teeth ADD CONSTRAINT dental_teeth_pkey PRIMARY KEY (dental_teeth_id);
CREATE INDEX dental_teeth_dental_id ON imed.dental_teeth(dental_id);
CREATE INDEX dental_teeth_tooth_number ON imed.dental_teeth(tooth_number);

CREATE TABLE imed.df_order_item (
	df_order_item_id VARCHAR(255) NOT NULL,
	item_id VARCHAR(255),
	employee_id VARCHAR(255),
	quantity VARCHAR(255),
	base_drug_usage_code VARCHAR(255),
	instruction_text_line1 VARCHAR(255),
	instruction_text_line2 VARCHAR(255),
	instruction_text_line3 VARCHAR(255)
);
ALTER TABLE imed.df_order_item ADD CONSTRAINT df_order_item_pkey PRIMARY KEY (df_order_item_id);
CREATE INDEX df_order_item_employee_id ON imed.df_order_item(employee_id);
CREATE INDEX df_order_item_item_id ON imed.df_order_item(item_id);

CREATE TABLE imed.df_order_item_set (
	df_order_item_set_id VARCHAR(255) NOT NULL,
	item_set_id VARCHAR(255),
	item_id VARCHAR(255),
	quantity VARCHAR(255),
	order_position VARCHAR(255),
	base_drug_usage_code VARCHAR(255),
	instruction_text_line1 VARCHAR(255),
	instruction_text_line2 VARCHAR(255),
	instruction_text_line3 VARCHAR(255),
	set_price VARCHAR(255),
	doctor_share VARCHAR(255)
);
ALTER TABLE imed.df_order_item_set ADD CONSTRAINT df_order_item_set_pkey PRIMARY KEY (df_order_item_set_id);
CREATE INDEX df_order_item_set_item_id ON imed.df_order_item_set(item_id);
CREATE INDEX df_order_item_set_item_set_id ON imed.df_order_item_set(item_set_id);

CREATE TABLE imed.diagnosis_icd10 (
	diagnosis_icd10_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	icd10_code VARCHAR(255),
	icd10_description VARCHAR(255),
	fix_diagnosis_type_id VARCHAR(255),
	doctor_eid VARCHAR(255),
	base_clinic_id VARCHAR(255),
	diagnosis_date VARCHAR(255),
	diagnosis_time VARCHAR(255),
	comments VARCHAR(255),
	beginning_diagnosis VARCHAR(255),
	beginning_diagnosis_th VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.diagnosis_icd10 ADD CONSTRAINT diagnosis_icd10_pkey PRIMARY KEY (diagnosis_icd10_id);
CREATE INDEX diagnosis_icd10_fix_diagn ON imed.diagnosis_icd10(fix_diagnosis_type_id);
CREATE INDEX diagnosis_icd10_visit_id ON imed.diagnosis_icd10(visit_id);

CREATE TABLE imed.diagnosis_icd9 (
	diagnosis_icd9_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	icd9_code VARCHAR(255),
	icd9_description VARCHAR(255),
	fix_operation_type_id VARCHAR(255),
	doctor_eid VARCHAR(255),
	base_clinic_id VARCHAR(255),
	date_in VARCHAR(255),
	time_in VARCHAR(255),
	date_out VARCHAR(255),
	time_out VARCHAR(255),
	comments VARCHAR(255),
	beginning_operation VARCHAR(255),
	beginning_operation_th VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.diagnosis_icd9 ADD CONSTRAINT diagnosis_icd9_pkey PRIMARY KEY (diagnosis_icd9_id);
CREATE INDEX diagnosis_icd9_fix_operat ON imed.diagnosis_icd9(fix_operation_type_id);
CREATE INDEX diagnosis_icd9_visit_id ON imed.diagnosis_icd9(visit_id);

CREATE TABLE imed.distributor (
	distributor_id VARCHAR(255) NOT NULL,
	distributor_name VARCHAR(255),
	tax_payer_number VARCHAR(255),
	fix_stock_pay_method_id VARCHAR(255),
	credit_on_pay VARCHAR(255),
	grade VARCHAR(255),
	address TEXT,
	office_tel VARCHAR(255),
	office_fax VARCHAR(255),
	office_mobile VARCHAR(255),
	mail VARCHAR(255),
	contact_person VARCHAR(255),
	deliver_day VARCHAR(255),
	member_number VARCHAR(255)
);
ALTER TABLE imed.distributor ADD CONSTRAINT distributor_pkey PRIMARY KEY (distributor_id);

CREATE TABLE imed.doctor_absent (
	doctor_absent_id VARCHAR(255) NOT NULL,
	doctor_schedule_id VARCHAR(255),
	employee_id VARCHAR(255),
	spid VARCHAR(255),
	absent_date VARCHAR(255),
	fix_day_of_week VARCHAR(255),
	start_time VARCHAR(255),
	end_time VARCHAR(255)
);
ALTER TABLE imed.doctor_absent ADD CONSTRAINT doctor_absent_pkey PRIMARY KEY (doctor_absent_id);

CREATE TABLE imed.doctor_diagnosis (
	doctor_diagnosis_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	beginning_diagnosis VARCHAR(255),
	beginning_diagnosis_th VARCHAR(255),
	note TEXT,
	secret_status VARCHAR(255),
	doctor_eid VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.doctor_diagnosis ADD CONSTRAINT doctor_diagnosis_pkey PRIMARY KEY (doctor_diagnosis_id);
CREATE INDEX doctor_diagnosis_doctor_eid ON imed.doctor_diagnosis(doctor_eid);
CREATE INDEX visit_id_doctor_diagnosis_key ON imed.doctor_diagnosis(visit_id);

CREATE TABLE imed.doctor_discharge_ipd (
	doctor_discharge_ipd_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	discharge_doctor_eid VARCHAR(255),
	discharge_eid VARCHAR(255),
	discharge_date VARCHAR(255),
	discharge_time VARCHAR(255),
	fix_ipd_discharge_status_id VARCHAR(255),
	fix_ipd_discharge_type_id VARCHAR(255),
	note VARCHAR(255)
);
ALTER TABLE imed.doctor_discharge_ipd ADD CONSTRAINT doctor_discharge_ipd_pkey PRIMARY KEY (doctor_discharge_ipd_id);
CREATE INDEX doctor_ds_ipd_visit_id ON imed.doctor_discharge_ipd(visit_id);

CREATE TABLE imed.doctor_discharge_opd (
	doctor_discharge_opd_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	discharge_eid VARCHAR(255),
	discharge_date VARCHAR(255),
	discharge_time VARCHAR(255),
	fix_opd_discharge_status_id VARCHAR(255),
	note VARCHAR(255)
);
ALTER TABLE imed.doctor_discharge_opd ADD CONSTRAINT doctor_discharge_opd_pkey PRIMARY KEY (doctor_discharge_opd_id);
CREATE INDEX doctor_discharge_opd_visi ON imed.doctor_discharge_opd(visit_id);

CREATE TABLE imed.doctor_schedule (
	doctor_schedule_id VARCHAR(255) NOT NULL,
	employee_id VARCHAR(255),
	spid VARCHAR(255),
	fix_day_of_week VARCHAR(255),
	start_time VARCHAR(255),
	end_time VARCHAR(255),
	limit_num_appoint VARCHAR(255)
);
ALTER TABLE imed.doctor_schedule ADD CONSTRAINT doctor_schedule_pkey PRIMARY KEY (doctor_schedule_id);
CREATE INDEX doctor_sche_dow ON imed.doctor_schedule(fix_day_of_week);
CREATE INDEX doctor_sche_employee ON imed.doctor_schedule(employee_id);
CREATE INDEX doctor_sche_spid ON imed.doctor_schedule(spid);

CREATE TABLE imed.drawing_details (
	drawing_details_id VARCHAR(255) NOT NULL,
	drawing_physical_exam_id VARCHAR(255),
	mode VARCHAR(255),
	sub_mode VARCHAR(255),
	x_axis VARCHAR(255),
	y_axis VARCHAR(255),
	width VARCHAR(255),
	height VARCHAR(255),
	rotation VARCHAR(255),
	color VARCHAR(255),
	text_desc VARCHAR(255),
	line_width VARCHAR(255)
);
ALTER TABLE imed.drawing_details ADD CONSTRAINT drawing_details_pkey PRIMARY KEY (drawing_details_id);
CREATE INDEX drawing_details_phyex_id ON imed.drawing_details(drawing_physical_exam_id);

CREATE TABLE imed.drawing_physical_exam (
	drawing_physical_exam_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	attending_physician_id VARCHAR(255),
	template_drawing_id VARCHAR(255),
	doctor_eid VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.drawing_physical_exam ADD CONSTRAINT drawing_physical_exam_pkey PRIMARY KEY (drawing_physical_exam_id);
CREATE INDEX drawing_phyex_attphy_id ON imed.drawing_physical_exam(attending_physician_id);
CREATE INDEX drawing_phyex_visit_id ON imed.drawing_physical_exam(visit_id);

CREATE TABLE imed.drug_allergy (
	drug_allergy_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	generic_name VARCHAR(255),
	trade_name VARCHAR(255),
	adr_group_name VARCHAR(255),
	assess_result VARCHAR(255),
	approve VARCHAR(255),
	symptom VARCHAR(255),
	approve_eid VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.drug_allergy ADD CONSTRAINT drug_allergy_pkey PRIMARY KEY (drug_allergy_id);
CREATE INDEX drug_allergy_adr_group ON imed.drug_allergy(adr_group_name);
CREATE INDEX drug_allergy_generic ON imed.drug_allergy(generic_name);
CREATE INDEX drug_allergy_patient_id ON imed.drug_allergy(patient_id);
CREATE INDEX drug_allergy_trade ON imed.drug_allergy(trade_name);

CREATE TABLE imed.drug_allergy_naranjo (
	drug_allergy_naranjo_id VARCHAR(255) NOT NULL,
	drug_allergy_id VARCHAR(255),
	fix_naranjo_question_id VARCHAR(255),
	naranjo_score VARCHAR(255),
	fix_naranjo_answer_id VARCHAR(255)
);
ALTER TABLE imed.drug_allergy_naranjo ADD CONSTRAINT drug_allergy_naranjo_pkey PRIMARY KEY (drug_allergy_naranjo_id);
CREATE INDEX naranjo_drug_allergy ON imed.drug_allergy_naranjo(drug_allergy_id);

CREATE TABLE imed.drug_allergy_temp (
	drug_allergy_temp_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	note VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.drug_allergy_temp ADD CONSTRAINT drug_allergy_temp_pkey PRIMARY KEY (drug_allergy_temp_id);
CREATE INDEX drug_allergy_temp_patient ON imed.drug_allergy_temp(patient_id);

CREATE TABLE imed.emergency_accident (
	emergency_accident_id VARCHAR(255) NOT NULL,
	emergency_data_id VARCHAR(255),
	visit_id VARCHAR(255),
	fix_accident_type_id VARCHAR(255),
	appearance_place VARCHAR(255),
	appearance_date VARCHAR(255),
	appearance_time VARCHAR(255),
	sender VARCHAR(255),
	risk_alcohol VARCHAR(255),
	risk_safty_helmet VARCHAR(255),
	risk_safty_belt VARCHAR(255),
	risk_other VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.emergency_accident ADD CONSTRAINT emergency_accident_pkey PRIMARY KEY (emergency_accident_id);
CREATE INDEX emergency_accident_emergency_data ON imed.emergency_accident(emergency_data_id);

CREATE TABLE imed.emergency_data (
	emergency_data_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	height VARCHAR(255),
	weight VARCHAR(255),
	bmi VARCHAR(255),
	blood_sugar VARCHAR(255),
	hct VARCHAR(255),
	main_symptom VARCHAR(255),
	current_illness VARCHAR(255),
	patient_examine VARCHAR(255),
	fix_emergency_type_id VARCHAR(255),
	fix_coming_type_id VARCHAR(255),
	record_eid VARCHAR(255),
	record_date VARCHAR(255),
	record_time VARCHAR(255),
	doctor_eid VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.emergency_data ADD CONSTRAINT emergency_data_pkey PRIMARY KEY (emergency_data_id);
CREATE INDEX emergency_data_visit_id ON imed.emergency_data(visit_id);

CREATE TABLE imed.emergency_vital_sign (
	emergency_vital_sign_id VARCHAR(255) NOT NULL,
	emergency_data_id VARCHAR(255),
	pressure_max VARCHAR(255),
	pressure_min VARCHAR(255),
	pulse VARCHAR(255),
	respiration VARCHAR(255),
	temperature VARCHAR(255),
	sat_o2 VARCHAR(255),
	abnormal_pressure VARCHAR(255),
	abnormal_pulse VARCHAR(255),
	measure_eid VARCHAR(255),
	measure_date VARCHAR(255),
	measure_time VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.emergency_vital_sign ADD CONSTRAINT emergency_vital_sign_pkey PRIMARY KEY (emergency_vital_sign_id);
CREATE INDEX emergency_vs_emergency_data ON imed.emergency_vital_sign(emergency_data_id);

CREATE TABLE imed.employee (
	employee_id VARCHAR(255) NOT NULL,
	employee_code VARCHAR(255),
	prename VARCHAR(255),
	firstname VARCHAR(255),
	lastname VARCHAR(255),
	password VARCHAR(255),
	profession_code VARCHAR(255),
	alias_name VARCHAR(255),
	base_clinic_id VARCHAR(255),
	base_service_point_id VARCHAR(255),
	fix_employee_type_id VARCHAR(255),
	employee_position VARCHAR(255),
	connection_profile_id VARCHAR(255),
	on_work VARCHAR(255),
	print_doctor_schedule TEXT,
	queue_xray_type VARCHAR(255),
	doctor_type VARCHAR(255),
	fix_clinic_id VARCHAR(255)
);
ALTER TABLE imed.employee ADD CONSTRAINT employee_pkey PRIMARY KEY (employee_id);
CREATE INDEX employee_employee_code ON imed.employee(employee_code);
CREATE INDEX employee_fix_employee_type_id ON imed.employee(fix_employee_type_id);

CREATE TABLE imed.employee_report_role (
	employee_report_role_id VARCHAR(255) NOT NULL,
	employee_id VARCHAR(255),
	report_sql_query_auth VARCHAR(255),
	report_module_auth VARCHAR(255),
	report_opd_billing_auth VARCHAR(255),
	report_ipd_billing_auth VARCHAR(255),
	report_drug_and_supply_auth VARCHAR(255),
	report_xray_auth VARCHAR(255),
	report_ipd_auth VARCHAR(255),
	report_account_auth VARCHAR(255),
	report_other_auth VARCHAR(255),
	report_setup_auth VARCHAR(255)
);
ALTER TABLE imed.employee_report_role ADD CONSTRAINT employee_report_role_pkey PRIMARY KEY (employee_report_role_id);
CREATE INDEX idx_employee_report_role_eid ON imed.employee_report_role(employee_id);

CREATE TABLE imed.employee_role (
	employee_role_id VARCHAR(255) NOT NULL,
	employee_id VARCHAR(255),
	tabmenu_auth VARCHAR(255),
	mainmenu_auth VARCHAR(255),
	patient_data_submenu_auth VARCHAR(255),
	service_submenu_auth VARCHAR(255),
	order_submenu_auth VARCHAR(255),
	cash_submenu_auth VARCHAR(255),
	appointment_submenu_auth VARCHAR(255),
	opd_submenu_auth VARCHAR(255),
	ipd_submenu_auth VARCHAR(255),
	medical_submenu_auth VARCHAR(255),
	xray_submenu_auth VARCHAR(255),
	schedule_submenu_auth VARCHAR(255),
	print_submenu_auth VARCHAR(255),
	system_submenu_auth VARCHAR(255),
	patient_social_button_auth VARCHAR(255),
	operation_button_auth VARCHAR(255),
	labor_button_auth VARCHAR(255),
	emergency_button_auth VARCHAR(255),
	patient_medical_button_auth VARCHAR(255),
	lab_button_auth VARCHAR(255),
	xray_button_auth VARCHAR(255),
	vital_sign_button_auth VARCHAR(255),
	order_button_auth VARCHAR(255),
	icd10_button_auth VARCHAR(255),
	icd9_button_auth VARCHAR(255),
	billing_opd_button_auth VARCHAR(255),
	billing_ipd_button_auth VARCHAR(255),
	admit_button_auth VARCHAR(255),
	emr_auth VARCHAR(255),
	order_auth VARCHAR(255),
	extra_auth VARCHAR(255),
	page_default VARCHAR(255),
	admin_module_auth VARCHAR(255),
	admin_system_auth VARCHAR(255),
	admin_user_and_sp_auth VARCHAR(255),
	admin_item_auth VARCHAR(255),
	admin_drug_detail_auth VARCHAR(255),
	admin_stock_auth VARCHAR(255),
	admin_stock_manage_auth VARCHAR(255),
	admin_stock_report_auth VARCHAR(255),
	admin_medical_auth VARCHAR(255),
	admin_lab_auth VARCHAR(255),
	admin_xray_auth VARCHAR(255),
	admin_operation_auth VARCHAR(255),
	admin_register_auth VARCHAR(255),
	admin_dental_auth VARCHAR(255),
	admin_billing_auth VARCHAR(255),
	admin_other_auth VARCHAR(255),
	admin_report_auth VARCHAR(255),
	admin_manage_item_auth VARCHAR(255),
	admin_manage_base_auth VARCHAR(255),
	admin_manage_help_auth VARCHAR(255),
	report_tab_module_auth VARCHAR(255),
	custom_tab_auth VARCHAR(255)
);
ALTER TABLE imed.employee_role ADD CONSTRAINT employee_role_pkey PRIMARY KEY (employee_role_id);
CREATE INDEX employee_role_employee_id ON imed.employee_role(employee_id);

CREATE TABLE imed.employee_type_default (
	employee_type_default_id VARCHAR(255) NOT NULL,
	fix_employee_type_id VARCHAR(255),
	list_order_condition VARCHAR(255),
	show_visit_cost VARCHAR(255),
	search_item_option VARCHAR(255)
);
ALTER TABLE imed.employee_type_default ADD CONSTRAINT employee_type_default_pkey PRIMARY KEY (employee_type_default_id);

CREATE TABLE imed.family_history (
	family_history_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	name VARCHAR(255),
	note VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.family_history ADD CONSTRAINT family_history_pkey PRIMARY KEY (family_history_id);
CREATE INDEX patient_id_family_history_key ON imed.family_history(patient_id);

CREATE TABLE imed.feature_cash_doc (
	feature_cash_doc_id VARCHAR(255) NOT NULL,
	fix_receipt_type_id VARCHAR(255),
	fix_visit_type_id VARCHAR(255),
	active VARCHAR(255),
	print_num VARCHAR(255)
);
ALTER TABLE imed.feature_cash_doc ADD CONSTRAINT feature_cash_doc_pkey PRIMARY KEY (feature_cash_doc_id);
CREATE INDEX ft_cash_doc_rcpt_type_id ON imed.feature_cash_doc(fix_receipt_type_id);
CREATE INDEX ft_cash_doc_visit_type_id ON imed.feature_cash_doc(fix_visit_type_id);

CREATE TABLE imed.feature_doctor_assign (
	feature_doctor_assign_id VARCHAR(255) NOT NULL,
	base_service_point_id VARCHAR(255),
	finish_assign_type VARCHAR(255),
	lab_xray_assign_type VARCHAR(255),
	after_seen_doctor_spid VARCHAR(255),
	default_pharm_spid VARCHAR(255),
	default_cash_spid VARCHAR(255)
);
ALTER TABLE imed.feature_doctor_assign ADD CONSTRAINT feature_doctor_assign_pkey PRIMARY KEY (feature_doctor_assign_id);
CREATE INDEX fda_base_service_point_id ON imed.feature_doctor_assign(base_service_point_id);

CREATE TABLE imed.feature_exception (
	feature_exception_id VARCHAR(255) NOT NULL,
	persistence_id VARCHAR(255),
	fix_exception_type_id VARCHAR(255)
);
ALTER TABLE imed.feature_exception ADD CONSTRAINT feature_exception_pkey PRIMARY KEY (feature_exception_id);
CREATE INDEX feature_exception_type_id ON imed.feature_exception(fix_exception_type_id);

CREATE TABLE imed.feature_option (
	feature_option_id VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	enable_feature VARCHAR(255),
	addition_feature VARCHAR(255)
);
ALTER TABLE imed.feature_option ADD CONSTRAINT feature_option_pkey PRIMARY KEY (feature_option_id);

CREATE TABLE imed.feature_order_short_key (
	feature_order_short_key_id VARCHAR(255) NOT NULL,
	item_id VARCHAR(255)
);
ALTER TABLE imed.feature_order_short_key ADD CONSTRAINT feature_order_short_key_pkey PRIMARY KEY (feature_order_short_key_id);

CREATE TABLE imed.file4_chronic (
	file4_chronic_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	datedx VARCHAR(255),
	chronic VARCHAR(255),
	detail VARCHAR(255),
	typedx VARCHAR(255),
	daterx VARCHAR(255),
	typerx VARCHAR(255),
	current VARCHAR(255),
	datedis VARCHAR(255),
	typedis VARCHAR(255),
	resdis VARCHAR(255),
	update VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.file4_chronic ADD CONSTRAINT file4_chronic_pkey PRIMARY KEY (file4_chronic_id);
CREATE INDEX visit_id_file4_chronic_key ON imed.file4_chronic(visit_id);

CREATE TABLE imed.file4_death (
	file4_death_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	death_date VARCHAR(255),
	death_time VARCHAR(255),
	cdeath_a VARCHAR(255),
	tdeath_a_date VARCHAR(255),
	tdeath_a_time VARCHAR(255),
	cdeath_b VARCHAR(255),
	tdeath_b_date VARCHAR(255),
	tdeath_b_time VARCHAR(255),
	cdeath_c VARCHAR(255),
	tdeath_c_date VARCHAR(255),
	tdeath_c_time VARCHAR(255),
	cdeath_d VARCHAR(255),
	tdeath_d_date VARCHAR(255),
	tdeath_d_time VARCHAR(255),
	odisease VARCHAR(255),
	cdeath VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.file4_death ADD CONSTRAINT file4_death_pkey PRIMARY KEY (file4_death_id);
CREATE INDEX visit_id_file4_death_key ON imed.file4_death(visit_id);

CREATE TABLE imed.file4_promote (
	file4_promote_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	hptype VARCHAR(255),
	hpcode VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.file4_promote ADD CONSTRAINT file4_promote_pkey PRIMARY KEY (file4_promote_id);
CREATE INDEX visit_id_file4_promote_key ON imed.file4_promote(visit_id);

CREATE TABLE imed.file4_surveil (
	file4_surveil_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	diagcode VARCHAR(255),
	code506 VARCHAR(255),
	illdate VARCHAR(255),
	illhouse VARCHAR(255),
	illvill VARCHAR(255),
	illtamb VARCHAR(255),
	illampu VARCHAR(255),
	illchan VARCHAR(255),
	ptstat VARCHAR(255),
	ddeath VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.file4_surveil ADD CONSTRAINT file4_surveil_pkey PRIMARY KEY (file4_surveil_id);
CREATE INDEX visit_id_file4_surveil_key ON imed.file4_surveil(visit_id);

CREATE TABLE imed.fix_bed_status (
	fix_bed_status_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.fix_bed_status ADD CONSTRAINT fix_bed_status_pkey PRIMARY KEY (fix_bed_status_id);

CREATE TABLE imed.fix_film_status (
	fix_film_status_id VARCHAR(254),
	description VARCHAR(254)
);

CREATE TABLE imed.fix_gender (
	fix_gender_id VARCHAR(255) NOT NULL,
	gender_name VARCHAR(255)
);
ALTER TABLE imed.fix_gender ADD CONSTRAINT fix_gender_pkey PRIMARY KEY (fix_gender_id);

CREATE TABLE imed.fix_icd10 (
	fix_icd10_id VARCHAR(255) NOT NULL,
	code VARCHAR(255),
	description VARCHAR(255),
	detail TEXT,
	nick_name TEXT
);
ALTER TABLE imed.fix_icd10 ADD CONSTRAINT fix_icd10_pkey PRIMARY KEY (fix_icd10_id);

CREATE TABLE imed.fix_icd10_4 (
	fix_icd10_4_id VARCHAR(255) NOT NULL,
	code_begin VARCHAR(255),
	code_end VARCHAR(255),
	description TEXT
);
ALTER TABLE imed.fix_icd10_4 ADD CONSTRAINT fix_icd10_4_pkey PRIMARY KEY (fix_icd10_4_id);

CREATE TABLE imed.fix_icd10_5 (
	fix_icd10_5_id VARCHAR(255) NOT NULL,
	code_begin VARCHAR(255),
	code_end VARCHAR(255),
	description TEXT
);
ALTER TABLE imed.fix_icd10_5 ADD CONSTRAINT fix_icd10_5_pkey PRIMARY KEY (fix_icd10_5_id);

CREATE TABLE imed.fix_icd9 (
	fix_icd9_id VARCHAR(255) NOT NULL,
	code VARCHAR(255),
	description VARCHAR(255),
	detail TEXT,
	nick_name TEXT
);
ALTER TABLE imed.fix_icd9 ADD CONSTRAINT fix_icd9_pkey PRIMARY KEY (fix_icd9_id);
CREATE INDEX fix_icd9_code ON imed.fix_icd9(code);
CREATE INDEX fix_icd9_nick_name ON imed.fix_icd9(nick_name);

CREATE TABLE imed.fix_idx10v3 (
	fix_idx10v3_id VARCHAR(255) NOT NULL,
	code1 VARCHAR(255),
	code2 VARCHAR(255),
	description VARCHAR(255),
	see VARCHAR(255),
	char4 VARCHAR(255),
	char5 VARCHAR(255)
);
ALTER TABLE imed.fix_idx10v3 ADD CONSTRAINT fix_idx10v3_pkey PRIMARY KEY (fix_idx10v3_id);
CREATE INDEX fix_idx10v3_description ON imed.fix_idx10v3(description);

CREATE TABLE imed.fix_lab_test_type (
	fix_lab_test_type_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.fix_lab_test_type ADD CONSTRAINT fix_lab_test_type_pkey PRIMARY KEY (fix_lab_test_type_id);

CREATE TABLE imed.fix_marriage (
	fix_marriage_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.fix_marriage ADD CONSTRAINT fix_marriage_pkey PRIMARY KEY (fix_marriage_id);

CREATE TABLE imed.fix_nationality (
	fix_nationality_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.fix_nationality ADD CONSTRAINT fix_nationality_pkey PRIMARY KEY (fix_nationality_id);

CREATE TABLE imed.fix_occupation (
	fix_occupation_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.fix_occupation ADD CONSTRAINT fix_occupation_pkey PRIMARY KEY (fix_occupation_id);

CREATE TABLE imed.fix_pindex99 (
	fix_pindex99_id VARCHAR(255) NOT NULL,
	code VARCHAR(255),
	description TEXT
);
ALTER TABLE imed.fix_pindex99 ADD CONSTRAINT fix_pindex99_pkey PRIMARY KEY (fix_pindex99_id);
CREATE INDEX fix_pindex99_description ON imed.fix_pindex99(description);

CREATE TABLE imed.fix_race (
	fix_race_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.fix_race ADD CONSTRAINT fix_race_pkey PRIMARY KEY (fix_race_id);

CREATE TABLE imed.fix_visit_type (
	fix_visit_type_id VARCHAR(255) NOT NULL,
	visit_type_name_th VARCHAR(255),
	visit_type_name_en VARCHAR(255)
);
ALTER TABLE imed.fix_visit_type ADD CONSTRAINT fix_visit_type_pkey PRIMARY KEY (fix_visit_type_id);

CREATE TABLE imed.fix_xray_type (
	fix_xray_type_id VARCHAR(254),
	description VARCHAR(254)
);

CREATE TABLE imed.hist (
	hist_id BPCHAR(27) NOT NULL,
	hn BPCHAR(12),
	doc_no BPCHAR(4),
	store_no BPCHAR(3),
	date_in BPCHAR(10),
	time_in BPCHAR(8),
	room_in BPCHAR(6),
	dr_code BPCHAR(6),
	dr_fee BPCHAR(8),
	dr_diag BPCHAR(50),
	staff_no BPCHAR(6),
	ya_code BPCHAR(10),
	ya_name BPCHAR(80),
	ya_um BPCHAR(8),
	ya_amount BPCHAR(9),
	ya_pack BPCHAR(10),
	ya_uprice BPCHAR(12),
	ya_ucost BPCHAR(12),
	item_code BPCHAR(24),
	usage BPCHAR(15),
	is_updated BPCHAR(1),
	is_takehome BPCHAR(1)
);
ALTER TABLE imed.hist ADD CONSTRAINT hist_pkey PRIMARY KEY (hist_id);

CREATE TABLE imed.ipd_attending_physician (
	ipd_attending_physician_id VARCHAR(254) NOT NULL,
	admit_id VARCHAR(254),
	employee_id VARCHAR(254),
	priority VARCHAR(254)
);
ALTER TABLE imed.ipd_attending_physician ADD CONSTRAINT ipd_attending_physician_pkey PRIMARY KEY (ipd_attending_physician_id);
CREATE INDEX ipd_att_physician_admit_id ON imed.ipd_attending_physician(admit_id);

CREATE TABLE imed.item (
	item_id VARCHAR(255) NOT NULL,
	item_code VARCHAR(255),
	common_name VARCHAR(255),
	drug_generic_name VARCHAR(255),
	drug_group_name VARCHAR(255),
	drug_trade_name VARCHAR(255),
	nick_name VARCHAR(255),
	note VARCHAR(255),
	fix_item_type_id VARCHAR(255),
	fix_chrgitem_id VARCHAR(255),
	base_category_group_id VARCHAR(255),
	base_billing_group_opd_id VARCHAR(255),
	base_billing_group_ipd_id VARCHAR(255),
	base_billing_group_home_id VARCHAR(255),
	base_invoice_group_id VARCHAR(255),
	base_unit_id VARCHAR(255),
	pack_size VARCHAR(255),
	pack_unit_id VARCHAR(255),
	base_lab_type_id VARCHAR(255),
	fix_lab_result_type_id VARCHAR(255),
	base_specimen_id VARCHAR(255),
	fix_report_type_id VARCHAR(255),
	description TEXT,
	description_en TEXT,
	base_drug_instruction_id VARCHAR(255),
	base_dose_unit_id VARCHAR(255),
	caution TEXT,
	caution_en TEXT,
	base_drug_type_id VARCHAR(255),
	base_drug_group_id VARCHAR(255),
	base_drug_format_id VARCHAR(255),
	film_charge VARCHAR(255),
	base_xray_type_id VARCHAR(255),
	hospital_item VARCHAR(255),
	stock_critical VARCHAR(255),
	mid_price1 VARCHAR(255),
	mid_price1_note VARCHAR(255),
	mid_price2 VARCHAR(255),
	mid_price2_note VARCHAR(255),
	mid_price3 VARCHAR(255),
	mid_price3_note VARCHAR(255),
	unit_price_cost VARCHAR(255),
	ned_group VARCHAR(255),
	nled_group_id VARCHAR(255),
	reg_no VARCHAR(255),
	gpo_price1 VARCHAR(255),
	gpo_price2 VARCHAR(255),
	gpo_price3 VARCHAR(255),
	fix_fund_enable_id VARCHAR(255),
	fund_enable_value VARCHAR(255),
	base_dent_operation_id VARCHAR(255),
	dental_color_code VARCHAR(255),
	fix_set_type_id VARCHAR(255),
	fix_sticker_type_id VARCHAR(255),
	print_report VARCHAR(255),
	report_price VARCHAR(255),
	edit_price VARCHAR(255),
	use_stock VARCHAR(255),
	doctor_share VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255),
	active VARCHAR(255),
	secret_status VARCHAR(255),
	max_price VARCHAR(255)
);
ALTER TABLE imed.item ADD CONSTRAINT item_pkey PRIMARY KEY (item_id);
CREATE INDEX idx_item_billgrp_home_id ON imed.item(base_billing_group_home_id);
CREATE INDEX idx_item_billgrp_ipd_id ON imed.item(base_billing_group_ipd_id);
CREATE INDEX idx_item_billgrp_opd_id ON imed.item(base_billing_group_opd_id);
CREATE INDEX item_active ON imed.item(active);
CREATE INDEX item_base_category_group ON imed.item(base_category_group_id);
CREATE INDEX item_base_invoice_group ON imed.item(base_invoice_group_id);
CREATE INDEX item_base_xray_type ON imed.item(base_xray_type_id);
CREATE INDEX item_common_name ON imed.item(common_name);
CREATE INDEX item_drug_generic_name ON imed.item(drug_generic_name);
CREATE INDEX item_drug_group_name ON imed.item(drug_group_name);
CREATE INDEX item_drug_trade_name ON imed.item(drug_trade_name);
CREATE INDEX item_fix_chrgitem_id ON imed.item(fix_chrgitem_id);
CREATE INDEX item_fix_item_type_id ON imed.item(fix_item_type_id);
CREATE INDEX item_item_code ON imed.item(item_code);
CREATE INDEX item_nick_name ON imed.item(nick_name);

CREATE TABLE imed.item_discount (
	item_discount_id VARCHAR(255) NOT NULL,
	item_id VARCHAR(255),
	plan_id VARCHAR(255)
);
ALTER TABLE imed.item_discount ADD CONSTRAINT item_discount_pkey PRIMARY KEY (item_discount_id);
CREATE INDEX item_discount_item_id ON imed.item_discount(item_id);
CREATE INDEX item_discount_plan_id ON imed.item_discount(plan_id);

CREATE TABLE imed.item_dispensed (
	item_dispense_id VARCHAR(255) NOT NULL,
	item_id VARCHAR(255),
	stock_id VARCHAR(255),
	sp_id VARCHAR(255),
	dispensed_quantity VARCHAR(255),
	dispensed_date VARCHAR(255),
	update_date VARCHAR(255),
	update_time VARCHAR(255),
	update_eid VARCHAR(255),
	active VARCHAR(255)
);
ALTER TABLE imed.item_dispensed ADD CONSTRAINT item_dispensed_pkey PRIMARY KEY (item_dispense_id);
CREATE INDEX item_dispensed_active ON imed.item_dispensed(active);
CREATE INDEX item_dispensed_item_id ON imed.item_dispensed(item_id);
CREATE INDEX item_dispensed_stock_id ON imed.item_dispensed(stock_id);

CREATE TABLE imed.item_plan_group (
	item_plan_group_id VARCHAR(255) NOT NULL,
	item_id VARCHAR(255),
	base_plan_group_id VARCHAR(255)
);
ALTER TABLE imed.item_plan_group ADD CONSTRAINT item_plan_group_pkey PRIMARY KEY (item_plan_group_id);
CREATE INDEX item_plan_group_group_id ON imed.item_plan_group(base_plan_group_id);
CREATE INDEX item_plan_grp_item_id ON imed.item_plan_group(item_id);
CREATE INDEX item_plan_grp_plan_grp_id ON imed.item_plan_group(base_plan_group_id);

CREATE TABLE imed.item_price (
	item_price_id VARCHAR(255) NOT NULL,
	item_id VARCHAR(255),
	base_tariff_id VARCHAR(255),
	active_date VARCHAR(255),
	unit_price VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255),
	modify_eid VARCHAR(255)
);
ALTER TABLE imed.item_price ADD CONSTRAINT item_price_pkey PRIMARY KEY (item_price_id);
CREATE INDEX item_price_base_tariff_id ON imed.item_price(base_tariff_id);
CREATE INDEX item_price_item_id ON imed.item_price(item_id);
CREATE INDEX item_price_modify_date ON imed.item_price(modify_date);
CREATE INDEX item_price_modify_eid ON imed.item_price(modify_eid);
CREATE INDEX item_price_modify_time ON imed.item_price(modify_time);

CREATE TABLE imed.item_repack (
	item_repack_id VARCHAR(255) NOT NULL,
	item_src_id VARCHAR(255),
	item_dest_id VARCHAR(255),
	rate VARCHAR(255)
);
ALTER TABLE imed.item_repack ADD CONSTRAINT item_repack_pkey PRIMARY KEY (item_repack_id);
CREATE INDEX item_rpk_item_dest_id ON imed.item_repack(item_dest_id);
CREATE INDEX item_rpk_item_src_id ON imed.item_repack(item_src_id);

CREATE TABLE imed.item_set (
	item_set_id VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	fix_order_set_type_id VARCHAR(255),
	item_id VARCHAR(255),
	order_price VARCHAR(255),
	item_set_note TEXT
);
ALTER TABLE imed.item_set ADD CONSTRAINT item_set_pkey PRIMARY KEY (item_set_id);

CREATE TABLE imed.item_set_print_out (
	item_set_print_out_id VARCHAR(255) NOT NULL,
	item_set_id VARCHAR(255),
	detail VARCHAR(255),
	detail_price VARCHAR(255)
);
ALTER TABLE imed.item_set_print_out ADD CONSTRAINT item_set_print_out_pkey PRIMARY KEY (item_set_print_out_id);
CREATE INDEX print_out_item_set_id ON imed.item_set_print_out(item_set_id);

CREATE TABLE imed.lab_microbiology (
	lab_microbiology_id VARCHAR(254) NOT NULL,
	lab_result_id VARCHAR(254),
	organisms_position VARCHAR(254),
	base_organisms VARCHAR(254),
	base_antibiotic VARCHAR(254),
	value VARCHAR(254)
);
ALTER TABLE imed.lab_microbiology ADD CONSTRAINT lab_microbiology_pkey PRIMARY KEY (lab_microbiology_id);
CREATE INDEX lab_micro_lab_result ON imed.lab_microbiology(lab_result_id);
CREATE INDEX lab_micro_organisms_position ON imed.lab_microbiology(organisms_position);

CREATE TABLE imed.lab_result (
	lab_result_id VARCHAR(254) NOT NULL,
	assign_lab_id VARCHAR(254),
	visit_id VARCHAR(254),
	order_item_id VARCHAR(254),
	item_id VARCHAR(254),
	base_lab_type_id VARCHAR(254),
	patient_id VARCHAR(254),
	ln VARCHAR(254),
	start_date VARCHAR(254),
	start_time VARCHAR(254),
	finish_date VARCHAR(254),
	finish_time VARCHAR(254),
	note TEXT,
	times_reported VARCHAR(254),
	fix_lab_result_type_id VARCHAR(254),
	reported VARCHAR(254),
	report_eid VARCHAR(254),
	secret_status VARCHAR(254),
	secret_eid VARCHAR(254),
	modify_eid VARCHAR(254),
	modify_spid VARCHAR(254),
	modify_date VARCHAR(254),
	modify_time VARCHAR(254)
);
ALTER TABLE imed.lab_result ADD CONSTRAINT lab_result_pkey PRIMARY KEY (lab_result_id);
CREATE INDEX index_lab_result_assign_lab_id ON imed.lab_result(assign_lab_id);
CREATE INDEX index_lab_result_item_id ON imed.lab_result(item_id);
CREATE INDEX index_lab_result_order_item_id ON imed.lab_result(order_item_id);
CREATE INDEX index_lab_result_patient_id ON imed.lab_result(patient_id);

CREATE TABLE imed.lab_specimen (
	lab_specimen_id VARCHAR(255) NOT NULL,
	order_item_id VARCHAR(255),
	base_specimen_id VARCHAR(255),
	receive_eid VARCHAR(255),
	receive_date VARCHAR(255),
	receive_time VARCHAR(255)
);
ALTER TABLE imed.lab_specimen ADD CONSTRAINT lab_specimen_pkey PRIMARY KEY (lab_specimen_id);
CREATE INDEX lab_specimen_order_item_id ON imed.lab_specimen(order_item_id);

CREATE TABLE imed.lab_test (
	lab_test_id VARCHAR(254) NOT NULL,
	lab_result_id VARCHAR(254),
	order_item_id VARCHAR(254),
	name VARCHAR(254),
	value TEXT,
	value_addition VARCHAR(254),
	normal_value_max VARCHAR(254),
	normal_value_min VARCHAR(254),
	abnormal VARCHAR(254),
	unit_text VARCHAR(254),
	result_position VARCHAR(254),
	fix_lab_test_type_id VARCHAR(254),
	template_lab_test_id VARCHAR(254),
	reported VARCHAR(254),
	is_repeat VARCHAR(254),
	modify_eid VARCHAR(254),
	modify_date VARCHAR(254),
	modify_time VARCHAR(254),
	active VARCHAR(254)
);
ALTER TABLE imed.lab_test ADD CONSTRAINT lab_test_pkey PRIMARY KEY (lab_test_id);
CREATE INDEX lab_test_lab_result_id ON imed.lab_test(lab_result_id);
CREATE INDEX lab_test_order_item_id ON imed.lab_test(order_item_id);
CREATE INDEX lab_test_template_lab_test_id ON imed.lab_test(template_lab_test_id);

CREATE TABLE imed.labor_basic_data (
	labor_basic_data_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	examine_date VARCHAR(255),
	examine_time VARCHAR(255),
	gestational VARCHAR(255),
	gestational_age VARCHAR(255),
	pregnancy VARCHAR(255),
	pregnancy_before VARCHAR(255),
	abortion VARCHAR(255),
	living_children VARCHAR(255),
	last_gestational VARCHAR(255),
	base_anc_place_id VARCHAR(255),
	anc_place_name VARCHAR(255),
	anc_rule VARCHAR(255),
	tt_rule VARCHAR(255),
	fix_blood_group_id VARCHAR(255),
	blood_test_rh VARCHAR(255),
	blood_test_hct VARCHAR(255),
	blood_test_anti_hiv VARCHAR(255),
	blood_test_vdrl VARCHAR(255),
	blood_test_hbs_ag VARCHAR(255),
	base_accept_labor_reason_id VARCHAR(255),
	base_pregnancy_complication_id VARCHAR(255),
	base_pregnancy_comorbidity_id VARCHAR(255),
	base_pregnancy_status_id VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.labor_basic_data ADD CONSTRAINT labor_basic_data_pkey PRIMARY KEY (labor_basic_data_id);
CREATE INDEX labor_basic_data_visit ON imed.labor_basic_data(visit_id);

CREATE TABLE imed.labor_deliver_data (
	labor_deliver_data_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	base_fetal_position_id VARCHAR(255),
	rupture_date VARCHAR(255),
	rupture_time VARCHAR(255),
	fix_rupture_type_id VARCHAR(255),
	cervical_dilatation VARCHAR(255),
	base_amniotic_fluid_id VARCHAR(255),
	base_onset_type_id VARCHAR(255),
	base_induction_method_id VARCHAR(255),
	onset_true_labor_date VARCHAR(255),
	onset_true_labor_time VARCHAR(255),
	full_dilatation_date VARCHAR(255),
	full_dilatation_time VARCHAR(255),
	child_born_date VARCHAR(255),
	child_born_time VARCHAR(255),
	placenta_delivered_date VARCHAR(255),
	placenta_delivered_time VARCHAR(255),
	base_delivery_type_id VARCHAR(255),
	base_indication_id VARCHAR(255),
	base_placenta_removed_id VARCHAR(255),
	base_gross_appearance_id VARCHAR(255),
	placenta_weight VARCHAR(255),
	base_insertion_of_cord_id VARCHAR(255),
	placenta_length VARCHAR(255),
	total_blood_loss VARCHAR(255),
	base_episiotomy_type_id VARCHAR(255),
	base_episiotomy_repaired_id VARCHAR(255),
	episiotomy_tear VARCHAR(255),
	base_uterine_contraction_id VARCHAR(255),
	base_bladder_id VARCHAR(255),
	base_episiotomy_wound_id VARCHAR(255),
	base_bleeding_per_vagina_id VARCHAR(255),
	partum_temperature VARCHAR(255),
	partum_pulse VARCHAR(255),
	partum_respiration VARCHAR(255),
	partum_pressure_max VARCHAR(255),
	partum_pressure_min VARCHAR(255),
	a5dn VARCHAR(255),
	a5dn_synto VARCHAR(255),
	methergin VARCHAR(255),
	pethidine_phenergan VARCHAR(255),
	drip_5dn VARCHAR(255),
	drip_5dn_synto VARCHAR(255),
	pethidine VARCHAR(255),
	phenergan VARCHAR(255),
	medication_peth_phen_type VARCHAR(255),
	medication_methergin_type VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.labor_deliver_data ADD CONSTRAINT labor_deliver_data_pkey PRIMARY KEY (labor_deliver_data_id);
CREATE INDEX labor_deliver_data_visit ON imed.labor_deliver_data(visit_id);

CREATE TABLE imed.labor_infant_data (
	labor_infant_data_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	infant_number VARCHAR(255),
	eab_initial_condition VARCHAR(255),
	eab_color VARCHAR(255),
	eab_activity VARCHAR(255),
	eab_breathing_time VARCHAR(255),
	eab_crying_time VARCHAR(255),
	eab_aspiration VARCHAR(255),
	eab_resuscitation VARCHAR(255),
	eab_sex VARCHAR(255),
	eab_weight VARCHAR(255),
	eab_eye_drugs VARCHAR(255),
	eab_vit_k1 VARCHAR(255),
	eab_diseases VARCHAR(255),
	ags_heart_rate_1 VARCHAR(255),
	ags_heart_rate_5 VARCHAR(255),
	ags_heart_rate_10 VARCHAR(255),
	ags_respiration_effort_1 VARCHAR(255),
	ags_respiration_effort_5 VARCHAR(255),
	ags_respiration_effort_10 VARCHAR(255),
	ags_muscle_tone_1 VARCHAR(255),
	ags_muscle_tone_5 VARCHAR(255),
	ags_muscle_tone_10 VARCHAR(255),
	ags_reflex_irritability_1 VARCHAR(255),
	ags_reflex_irritability_5 VARCHAR(255),
	ags_reflex_irritability_10 VARCHAR(255),
	ags_skin_color_1 VARCHAR(255),
	ags_skin_color_5 VARCHAR(255),
	ags_skin_color_10 VARCHAR(255),
	circumference_fronto VARCHAR(255),
	circumference_chest VARCHAR(255),
	length VARCHAR(255),
	temperature VARCHAR(255),
	infant_status VARCHAR(255),
	death_date VARCHAR(255),
	death_time VARCHAR(255),
	death_type VARCHAR(255),
	death_cause VARCHAR(255),
	comments VARCHAR(255),
	delivery VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.labor_infant_data ADD CONSTRAINT labor_infant_data_pkey PRIMARY KEY (labor_infant_data_id);
CREATE INDEX labor_infant_data_visit ON imed.labor_infant_data(visit_id);

CREATE TABLE imed.location_record (
	location_record_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	location_spid VARCHAR(255),
	operate_eid VARCHAR(255),
	incomming_date VARCHAR(255),
	incomming_time VARCHAR(255),
	outgoing_date VARCHAR(255),
	outgoing_time VARCHAR(255)
);
ALTER TABLE imed.location_record ADD CONSTRAINT location_record_pkey PRIMARY KEY (location_record_id);
CREATE INDEX location_record_location_spid ON imed.location_record(location_spid);
CREATE INDEX location_record_operate_eid ON imed.location_record(operate_eid);
CREATE INDEX location_record_visit_id ON imed.location_record(visit_id);

CREATE TABLE imed.manufacturer (
	manufacturer_id VARCHAR(255) NOT NULL,
	manufacturer_name VARCHAR(255),
	office_tel VARCHAR(255),
	office_fax VARCHAR(255),
	office_mobile VARCHAR(255),
	contact_person VARCHAR(255)
);
ALTER TABLE imed.manufacturer ADD CONSTRAINT manufacturer_pkey PRIMARY KEY (manufacturer_id);

CREATE TABLE imed.medical_certificate (
	medical_certificate_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	doctor_eid VARCHAR(255),
	diagnosis_date VARCHAR(255),
	patient_disease VARCHAR(255),
	doctor_note TEXT,
	stay_length VARCHAR(255),
	stay_start VARCHAR(255),
	stay_finish VARCHAR(255),
	make_date VARCHAR(255),
	print_eid VARCHAR(255),
	print_date VARCHAR(255),
	print_time VARCHAR(255),
	fix_medcert_type_id VARCHAR(255),
	patient_rank VARCHAR(255),
	patient_amphur VARCHAR(255),
	patient_changwat VARCHAR(255),
	patient_department VARCHAR(255),
	patient_ministry VARCHAR(255),
	patient_symptom TEXT,
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.medical_certificate ADD CONSTRAINT medical_certificate_pkey PRIMARY KEY (medical_certificate_id);
CREATE INDEX medical_certificate_type_id ON imed.medical_certificate(fix_medcert_type_id);
CREATE INDEX medical_certificate_visit_id ON imed.medical_certificate(visit_id);

CREATE TABLE imed.medical_history (
	medical_history_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	name VARCHAR(255),
	note VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.medical_history ADD CONSTRAINT medical_history_pkey PRIMARY KEY (medical_history_id);
CREATE INDEX patient_id_medical_history_key ON imed.medical_history(patient_id);

CREATE TABLE imed.medication_error (
	medication_error_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	record_date VARCHAR(255),
	record_time VARCHAR(255),
	quantity_drug VARCHAR(255),
	quantity_error VARCHAR(255),
	rec_type VARCHAR(255),
	rec_format VARCHAR(255),
	rec_strength VARCHAR(255),
	rec_quantity VARCHAR(255),
	rec_instruction VARCHAR(255),
	rec_frequency VARCHAR(255),
	rec_dose VARCHAR(255),
	rec_over VARCHAR(255),
	rec_other VARCHAR(255),
	chk_type VARCHAR(255),
	chk_format VARCHAR(255),
	chk_strength VARCHAR(255),
	chk_quantity VARCHAR(255),
	chk_instruction VARCHAR(255),
	chk_frequency VARCHAR(255),
	chk_dose VARCHAR(255),
	chk_over VARCHAR(255),
	chk_other VARCHAR(255),
	pack_type VARCHAR(255),
	pack_format VARCHAR(255),
	pack_strength VARCHAR(255),
	pack_quantity VARCHAR(255),
	pack_over VARCHAR(255),
	pack_mixed VARCHAR(255),
	pack_other VARCHAR(255),
	pack_rec_eid VARCHAR(255),
	disp_miss_pat VARCHAR(255),
	disp_miss_pat_pat_complain VARCHAR(255),
	disp_miss_pat_self_check VARCHAR(255),
	disp_not_complete VARCHAR(255),
	disp_rec_eid VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.medication_error ADD CONSTRAINT medication_error_pkey PRIMARY KEY (medication_error_id);
CREATE INDEX med_err_visit_id ON imed.medication_error(visit_id);

CREATE TABLE imed.nled_group (
	nled_group_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.nled_group ADD CONSTRAINT nled_group_pkey PRIMARY KEY (nled_group_id);

CREATE TABLE imed.omega_general_data (
	omega_general_data_id VARCHAR(255) NOT NULL,
	sequence_number VARCHAR(255),
	hn VARCHAR(255),
	ln VARCHAR(255),
	patient_firstname VARCHAR(255),
	patient_lastname VARCHAR(255),
	patient_birthday VARCHAR(255),
	patient_gender VARCHAR(255),
	date_time_register VARCHAR(255),
	priority VARCHAR(255),
	is_file_created VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.omega_general_data ADD CONSTRAINT omega_general_data_pkey PRIMARY KEY (omega_general_data_id);
CREATE INDEX omg_general_hn ON imed.omega_general_data(hn);
CREATE INDEX omg_general_ln ON imed.omega_general_data(ln);
CREATE INDEX omg_general_seq ON imed.omega_general_data(sequence_number);

CREATE TABLE imed.omega_order_and_result_data (
	omega_order_and_result_data_id VARCHAR(255) NOT NULL,
	omega_general_data_id VARCHAR(255),
	sequence_number VARCHAR(255),
	item_name VARCHAR(255),
	test_code VARCHAR(255),
	action_code VARCHAR(255),
	specimen_code VARCHAR(255),
	priority VARCHAR(255),
	section_code VARCHAR(255),
	report_type VARCHAR(255),
	result_value VARCHAR(255),
	result_unit VARCHAR(255),
	result_min_normal VARCHAR(255),
	result_max_normal VARCHAR(255),
	result_final VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.omega_order_and_result_data ADD CONSTRAINT omega_order_and_result_data_pkey PRIMARY KEY (omega_order_and_result_data_id);
CREATE INDEX omg_order_rlt_hn ON imed.omega_order_and_result_data(omega_general_data_id);
CREATE INDEX omg_order_rlt_ln ON imed.omega_order_and_result_data(item_name);
CREATE INDEX omg_order_rlt_seq ON imed.omega_order_and_result_data(sequence_number);

CREATE TABLE imed.op_registered (
	op_registered_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	patient_id VARCHAR(255),
	op_set_id VARCHAR(255),
	registered_eid VARCHAR(255),
	registered_date VARCHAR(255),
	registered_time VARCHAR(255),
	start_date VARCHAR(255),
	start_time VARCHAR(255),
	finish_date VARCHAR(255),
	finish_time VARCHAR(255),
	used_time VARCHAR(255),
	base_op_room_id VARCHAR(255),
	fix_op_type_id VARCHAR(255),
	base_clinic_id VARCHAR(255),
	note VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.op_registered ADD CONSTRAINT op_registered_pkey PRIMARY KEY (op_registered_id);
CREATE INDEX op_registered_patient ON imed.op_registered(patient_id);
CREATE INDEX op_registered_visit ON imed.op_registered(visit_id);

CREATE TABLE imed.op_registered_order_item (
	op_registered_order_item_id VARCHAR(255) NOT NULL,
	op_registered_id VARCHAR(255),
	order_item_id VARCHAR(255)
);
ALTER TABLE imed.op_registered_order_item ADD CONSTRAINT op_registered_order_item_pkey PRIMARY KEY (op_registered_order_item_id);
CREATE INDEX op_reg_order_op_reg ON imed.op_registered_order_item(op_registered_id);

CREATE TABLE imed.op_registered_physician (
	op_registered_physician_id VARCHAR(255) NOT NULL,
	op_registered_id VARCHAR(255),
	employee_id VARCHAR(255),
	base_op_role_id VARCHAR(255)
);
ALTER TABLE imed.op_registered_physician ADD CONSTRAINT op_registered_physician_pkey PRIMARY KEY (op_registered_physician_id);
CREATE INDEX op_reg_phy_op_reg ON imed.op_registered_physician(op_registered_id);

CREATE TABLE imed.op_registered_specimen (
	op_registered_specimen_id VARCHAR(255) NOT NULL,
	op_registered_id VARCHAR(255),
	specimen_id VARCHAR(255)
);
ALTER TABLE imed.op_registered_specimen ADD CONSTRAINT op_registered_specimen_pkey PRIMARY KEY (op_registered_specimen_id);
CREATE INDEX op_reg_spc_op_reg ON imed.op_registered_specimen(op_registered_id);

CREATE TABLE imed.op_set (
	op_set_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	visit_id VARCHAR(255),
	assigned_eid VARCHAR(255),
	assigned_spid VARCHAR(255),
	assigned_date VARCHAR(255),
	assigned_time VARCHAR(255),
	base_op_room_id VARCHAR(255),
	op_date VARCHAR(255),
	op_time VARCHAR(255),
	dx_before_op VARCHAR(255),
	note VARCHAR(255),
	fix_op_status_id VARCHAR(255),
	fix_op_set_status_id VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.op_set ADD CONSTRAINT op_set_pkey PRIMARY KEY (op_set_id);
CREATE INDEX op_set_fix_op_set_status ON imed.op_set(fix_op_set_status_id);
CREATE INDEX op_set_fix_op_status ON imed.op_set(fix_op_status_id);
CREATE INDEX op_set_op_date ON imed.op_set(op_date);
CREATE INDEX op_set_patient ON imed.op_set(patient_id);
CREATE INDEX op_set_visit ON imed.op_set(visit_id);

CREATE TABLE imed.op_set_category (
	op_set_category_id VARCHAR(255) NOT NULL,
	op_set_id VARCHAR(255),
	op_category VARCHAR(255),
	priority VARCHAR(255)
);
ALTER TABLE imed.op_set_category ADD CONSTRAINT op_set_category_pkey PRIMARY KEY (op_set_category_id);
CREATE INDEX op_set_category_op_set ON imed.op_set_category(op_set_id);

CREATE TABLE imed.op_set_physician (
	op_set_physician_id VARCHAR(255) NOT NULL,
	op_set_id VARCHAR(255),
	employee_id VARCHAR(255),
	base_op_role_id VARCHAR(255)
);
ALTER TABLE imed.op_set_physician ADD CONSTRAINT op_set_physician_pkey PRIMARY KEY (op_set_physician_id);
CREATE INDEX op_set_physician_op_set ON imed.op_set_physician(op_set_id);

CREATE TABLE imed.opd_stat (
	id VARCHAR(255),
	hn_search VARCHAR(255),
	on_date VARCHAR(255),
	on_time VARCHAR(255),
	on_case VARCHAR(255),
	new_fox VARCHAR(255),
	sex VARCHAR(255),
	staff VARCHAR(255),
	sf VARCHAR(255),
	memo VARCHAR(255),
	ret_date VARCHAR(255),
	emergency VARCHAR(255),
	vn VARCHAR(255),
	check_fox VARCHAR(255),
	icd VARCHAR(255),
	usr_no VARCHAR(255),
	refer VARCHAR(255),
	pressure_h VARCHAR(255),
	pressure_l VARCHAR(255),
	tall VARCHAR(255),
	weight VARCHAR(255),
	temper VARCHAR(255),
	ss_id VARCHAR(255),
	uc_id VARCHAR(255),
	pulse VARCHAR(255),
	respirat VARCHAR(255),
	opd_place VARCHAR(255),
	check_out VARCHAR(255),
	on_side VARCHAR(255),
	visittype VARCHAR(255),
	is_updated VARCHAR(255)
);

CREATE TABLE imed.order_continue (
	order_continue_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	item_id VARCHAR(255),
	fix_item_type_id VARCHAR(255),
	base_drug_usage_code VARCHAR(255),
	instruction_format VARCHAR(255),
	base_drug_instruction_id VARCHAR(255),
	dose_quantity VARCHAR(255),
	base_dose_unit_id VARCHAR(255),
	base_drug_frequency_id VARCHAR(255),
	instruction_text_line1 VARCHAR(255),
	instruction_text_line2 VARCHAR(255),
	instruction_text_line3 VARCHAR(255),
	quantity VARCHAR(255),
	base_unit_id VARCHAR(255),
	description VARCHAR(255),
	caution VARCHAR(255),
	unit_price_sale VARCHAR(255),
	order_eid VARCHAR(255),
	order_spid VARCHAR(255),
	start_date VARCHAR(255),
	start_time VARCHAR(255),
	finish_date VARCHAR(255),
	finish_time VARCHAR(255),
	interval VARCHAR(255),
	next_start_prepared_date VARCHAR(255),
	next_start_prepared_time VARCHAR(255),
	fix_order_continue_status_id VARCHAR(255),
	discontinue_eid VARCHAR(255),
	discontinue_date VARCHAR(255),
	discontinue_time VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.order_continue ADD CONSTRAINT order_continue_pkey PRIMARY KEY (order_continue_id);
CREATE INDEX idx_order_continue_item_id ON imed.order_continue(item_id);
CREATE INDEX order_continue_order_continue_status ON imed.order_continue(fix_order_continue_status_id);
CREATE INDEX order_continue_visit_id ON imed.order_continue(visit_id);

CREATE TABLE imed.order_continue_prepared (
	order_continue_prepared_id VARCHAR(255) NOT NULL,
	order_continue_id VARCHAR(255),
	visit_id VARCHAR(255),
	item_id VARCHAR(255),
	fix_item_type_id VARCHAR(255),
	base_drug_usage_code VARCHAR(255),
	instruction_format VARCHAR(255),
	base_drug_instruction_id VARCHAR(255),
	dose_quantity VARCHAR(255),
	base_dose_unit_id VARCHAR(255),
	base_drug_frequency_id VARCHAR(255),
	instruction_text_line1 VARCHAR(255),
	instruction_text_line2 VARCHAR(255),
	instruction_text_line3 VARCHAR(255),
	quantity VARCHAR(255),
	base_unit_id VARCHAR(255),
	description VARCHAR(255),
	caution VARCHAR(255),
	take_home VARCHAR(255),
	unit_price_sale VARCHAR(255),
	order_eid VARCHAR(255),
	order_spid VARCHAR(255),
	order_date VARCHAR(255),
	order_time VARCHAR(255),
	active VARCHAR(255)
);
ALTER TABLE imed.order_continue_prepared ADD CONSTRAINT order_continue_prepared_pkey PRIMARY KEY (order_continue_prepared_id);
CREATE INDEX order_continue_prepared_item_id ON imed.order_continue_prepared(item_id);
CREATE INDEX order_continue_prepared_order_continue_id ON imed.order_continue_prepared(order_continue_id);
CREATE INDEX order_continue_prepared_order_date ON imed.order_continue_prepared(order_date);
CREATE INDEX order_continue_prepared_order_time ON imed.order_continue_prepared(order_time);
CREATE INDEX order_continue_prepared_visit_id ON imed.order_continue_prepared(visit_id);

CREATE TABLE imed.order_item (
	order_item_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	item_id VARCHAR(255),
	patient_id VARCHAR(255),
	fix_item_type_id VARCHAR(255),
	base_category_group_id VARCHAR(255),
	base_billing_group_id VARCHAR(255),
	base_lab_type_id VARCHAR(255),
	base_drug_usage_code VARCHAR(255),
	instruction_format VARCHAR(255),
	base_drug_instruction_id VARCHAR(255),
	dose_quantity VARCHAR(255),
	base_dose_unit_id VARCHAR(255),
	base_drug_frequency_id VARCHAR(255),
	instruction_text_line1 VARCHAR(255),
	instruction_text_line2 VARCHAR(255),
	instruction_text_line3 VARCHAR(255),
	quantity VARCHAR(255),
	base_unit_id VARCHAR(255),
	description TEXT,
	caution TEXT,
	take_home VARCHAR(255),
	doctor_fee_eid VARCHAR(255),
	verify_eid VARCHAR(255),
	verify_date VARCHAR(255),
	verify_time VARCHAR(255),
	verify_spid VARCHAR(255),
	execute_eid VARCHAR(255),
	execute_date VARCHAR(255),
	execute_time VARCHAR(255),
	execute_spid VARCHAR(255),
	dispense_eid VARCHAR(255),
	dispense_date VARCHAR(255),
	dispense_time VARCHAR(255),
	dispense_spid VARCHAR(255),
	dispense_pin_code VARCHAR(255),
	remain_report_eid VARCHAR(255),
	remain_report_date VARCHAR(255),
	remain_report_note TEXT,
	ignore_report_eid VARCHAR(255),
	ignore_report_date VARCHAR(255),
	ignore_report_time VARCHAR(255),
	ignore_report_note TEXT,
	submit_ignore_eid VARCHAR(255),
	unit_price_cost VARCHAR(255),
	unit_price_sale VARCHAR(255),
	charge_complete VARCHAR(255),
	fix_order_status_id VARCHAR(255),
	di_status VARCHAR(255),
	fix_dispense_type_id VARCHAR(255),
	ned_group VARCHAR(255),
	fix_lab_result_type_id VARCHAR(255),
	base_specimen_id VARCHAR(255),
	keep_specimen_eid VARCHAR(255),
	dental_id VARCHAR(255),
	fix_assign_status_id VARCHAR(255),
	is_deposit VARCHAR(255),
	deposit_number VARCHAR(255),
	com_number VARCHAR(255),
	print_deposit_eid VARCHAR(255),
	print_deposit_date VARCHAR(255),
	print_deposit_time VARCHAR(255),
	fix_set_type_id VARCHAR(255),
	set_order_id VARCHAR(255),
	set_unit_price VARCHAR(255),
	fix_order_type_id VARCHAR(255),
	order_doctor_eid VARCHAR(255),
	order_spid VARCHAR(255),
	assigned_user VARCHAR(255),
	assigned_sp VARCHAR(255),
	assigned_date VARCHAR(255),
	assigned_time VARCHAR(255),
	assigned_ref_no VARCHAR(255),
	print_caution VARCHAR(255),
	fix_mix_drug_type_id VARCHAR(255),
	mix_drug_no VARCHAR(255),
	visit_payment_id VARCHAR(255),
	plan_id VARCHAR(255),
	fix_except_plan_id VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255),
	original_unit_price VARCHAR(255),
	base_df_mode_id VARCHAR(255),
	base_xray_type_id VARCHAR(255),
	diagnosis_icd9_id VARCHAR(255),
	fix_language_type_id VARCHAR(255),
	sticker_quantity VARCHAR(255),
	out_doctor VARCHAR(255),
	out_doctor_hospital_id VARCHAR(255),
	out_doctor_note TEXT,
	print_description VARCHAR(255),
	receive_specimen VARCHAR(255)
);
ALTER TABLE imed.order_item ADD CONSTRAINT order_item_pkey PRIMARY KEY (order_item_id);
CREATE INDEX order_assigned_ref_no ON imed.order_item(assigned_ref_no);
CREATE INDEX order_item_bill_grp_id ON imed.order_item(base_billing_group_id);
CREATE INDEX order_item_dental_id ON imed.order_item(dental_id);
CREATE INDEX order_item_df_mode ON imed.order_item(base_df_mode_id);
CREATE INDEX order_item_doctor_eid ON imed.order_item(order_doctor_eid);
CREATE INDEX order_item_fix_order_stat ON imed.order_item(fix_order_status_id);
CREATE INDEX order_item_fix_set_type ON imed.order_item(fix_set_type_id);
CREATE INDEX order_item_item_id ON imed.order_item(item_id);
CREATE INDEX order_item_mix_drug_no ON imed.order_item(mix_drug_no);
CREATE INDEX order_item_mix_drug_type ON imed.order_item(fix_mix_drug_type_id);
CREATE INDEX order_item_order_doctor_eid ON imed.order_item(order_doctor_eid);
CREATE INDEX order_item_order_spid ON imed.order_item(order_spid);
CREATE INDEX order_item_pat_id ON imed.order_item(patient_id);
CREATE INDEX order_item_payment_id ON imed.order_item(visit_payment_id);
CREATE INDEX order_item_set_order_id ON imed.order_item(set_order_id);
CREATE INDEX order_item_spid ON imed.order_item(order_spid);
CREATE INDEX order_item_verify_date ON imed.order_item(verify_date);
CREATE INDEX order_item_verify_time ON imed.order_item(verify_time);
CREATE INDEX order_item_visit_id ON imed.order_item(visit_id);
CREATE INDEX order_item_xray_type ON imed.order_item(base_xray_type_id);

CREATE TABLE imed.p48 (
	hn VARCHAR(255),
	id VARCHAR(255),
	xray_no VARCHAR(255),
	title VARCHAR(255),
	name VARCHAR(255),
	lastname VARCHAR(255),
	nsd VARCHAR(255),
	lsd VARCHAR(255),
	age VARCHAR(255),
	race VARCHAR(255),
	nation VARCHAR(255),
	type VARCHAR(255),
	sex VARCHAR(255),
	address VARCHAR(255),
	tumbal VARCHAR(255),
	amper VARCHAR(255),
	city VARCHAR(255),
	tel VARCHAR(255),
	occ VARCHAR(255),
	worktel VARCHAR(255),
	ref VARCHAR(255),
	r_name VARCHAR(255),
	on_date VARCHAR(255),
	kind VARCHAR(255),
	problem VARCHAR(255),
	iden_card VARCHAR(255),
	blood VARCHAR(255),
	dental_no VARCHAR(255),
	cancel VARCHAR(255),
	is_updated VARCHAR(255)
);

CREATE TABLE imed.p49 (
	hn VARCHAR(255),
	id VARCHAR(255),
	xray_no VARCHAR(255),
	title VARCHAR(255),
	name VARCHAR(255),
	lastname VARCHAR(255),
	nsd VARCHAR(255),
	lsd VARCHAR(255),
	age VARCHAR(255),
	race VARCHAR(255),
	nation VARCHAR(255),
	type VARCHAR(255),
	sex VARCHAR(255),
	address VARCHAR(255),
	tumbal VARCHAR(255),
	amper VARCHAR(255),
	city VARCHAR(255),
	tel VARCHAR(255),
	occ VARCHAR(255),
	worktel VARCHAR(255),
	ref VARCHAR(255),
	r_name VARCHAR(255),
	on_date VARCHAR(255),
	kind VARCHAR(255),
	problem VARCHAR(255),
	iden_card VARCHAR(255),
	blood VARCHAR(255),
	dental_no VARCHAR(255),
	cancel VARCHAR(255),
	is_updated VARCHAR(255)
);

CREATE TABLE imed.patient (
	patient_id VARCHAR(255) NOT NULL,
	hn VARCHAR(255),
	prename VARCHAR(255),
	firstname VARCHAR(255),
	lastname VARCHAR(255),
	nsd VARCHAR(255),
	lsd VARCHAR(255),
	nickname VARCHAR(255),
	pid VARCHAR(255),
	any_id VARCHAR(255),
	fix_gender_id VARCHAR(255),
	birthdate VARCHAR(255),
	birthdate_true VARCHAR(255),
	blood_group VARCHAR(255),
	email VARCHAR(255),
	mobile VARCHAR(255),
	fix_marriage_id VARCHAR(255),
	fix_nationality_id VARCHAR(255),
	fix_race_id VARCHAR(255),
	fix_occupation_id VARCHAR(255),
	religion VARCHAR(255),
	couple_firstname VARCHAR(255),
	couple_lastname VARCHAR(255),
	father_firstname VARCHAR(255),
	father_lastname VARCHAR(255),
	mother_firstname VARCHAR(255),
	mother_lastname VARCHAR(255),
	patient_noname VARCHAR(255),
	fix_new_in_year_id VARCHAR(255),
	base_tariff_id VARCHAR(255),
	fix_opd_file_status_id VARCHAR(255),
	fix_ipd_file_status_id VARCHAR(255),
	xn VARCHAR(255),
	un VARCHAR(255),
	en VARCHAR(255),
	total_admit_times VARCHAR(255),
	base_patient_type_id VARCHAR(255),
	place_name VARCHAR(255),
	place_nearly VARCHAR(255),
	home_id VARCHAR(255),
	road VARCHAR(255),
	village VARCHAR(255),
	fix_tambol_id VARCHAR(255),
	fix_amphur_id VARCHAR(255),
	fix_changwat_id VARCHAR(255),
	postcode VARCHAR(255),
	telephone VARCHAR(255),
	foreign_address TEXT,
	base_country_id VARCHAR(255),
	generate_id VARCHAR(255),
	proxy_status VARCHAR(255),
	proxy_user VARCHAR(255),
	proxy_date VARCHAR(255),
	proxy_time VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255),
	active VARCHAR(255),
	is_death VARCHAR(255),
	rtfn VARCHAR(255),
	rtln VARCHAR(255),
	crm TEXT,
	passport_id VARCHAR(255)
);
ALTER TABLE imed.patient ADD CONSTRAINT patient_pkey PRIMARY KEY (patient_id);
CREATE INDEX patient_firstname ON imed.patient(firstname);
CREATE INDEX patient_fix_amphur_id ON imed.patient(fix_amphur_id);
CREATE INDEX patient_fix_changwat_id ON imed.patient(fix_changwat_id);
CREATE INDEX patient_fix_occupation_id ON imed.patient(fix_occupation_id);
CREATE INDEX patient_hn ON imed.patient(hn);
CREATE INDEX patient_lastname ON imed.patient(lastname);
CREATE INDEX patient_lsd ON imed.patient(lsd);
CREATE INDEX patient_nickname ON imed.patient(nickname);
CREATE INDEX patient_nsd ON imed.patient(nsd);
CREATE INDEX patient_pid ON imed.patient(pid);
CREATE INDEX patient_un ON imed.patient(un);
CREATE INDEX patient_xn ON imed.patient(xn);
CREATE INDEX stk_death_patient_id ON imed.patient(patient_id);

CREATE TABLE imed.patient_address (
	patient_address_id VARCHAR(254) NOT NULL,
	patient_id VARCHAR(254),
	place_name VARCHAR(254),
	place_nearly VARCHAR(254),
	home_id VARCHAR(254),
	road VARCHAR(254),
	village VARCHAR(254),
	fix_tambol_id VARCHAR(254),
	fix_amphur_id VARCHAR(254),
	fix_changwat_id VARCHAR(254),
	postcode VARCHAR(254),
	telephone VARCHAR(254),
	fix_address_type_id VARCHAR(254)
);
ALTER TABLE imed.patient_address ADD CONSTRAINT patient_address_pkey PRIMARY KEY (patient_address_id);
CREATE INDEX patient_address_patient_i ON imed.patient_address(patient_id);

CREATE TABLE imed.patient_contact (
	patient_contact_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	firstname VARCHAR(255),
	lastname VARCHAR(255),
	fix_gender_id VARCHAR(255),
	relate_text TEXT,
	email VARCHAR(255),
	mobile VARCHAR(255),
	place_name VARCHAR(255),
	place_nearly VARCHAR(255),
	home_id VARCHAR(255),
	road VARCHAR(255),
	village VARCHAR(255),
	fix_tambol_id VARCHAR(255),
	fix_amphur_id VARCHAR(255),
	fix_changwat_id VARCHAR(255),
	postcode VARCHAR(255),
	telephone VARCHAR(255)
);
ALTER TABLE imed.patient_contact ADD CONSTRAINT patient_contact_pkey PRIMARY KEY (patient_contact_id);
CREATE INDEX patient_contact_patient_id ON imed.patient_contact(patient_id);

CREATE TABLE imed.patient_file_borrow (
	patient_file_borrow_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	hn VARCHAR(255),
	file_type VARCHAR(255),
	fix_person_type_id VARCHAR(255),
	borrow_name VARCHAR(255),
	borrow_pid VARCHAR(255),
	borrow_eid VARCHAR(255),
	borrow_spid VARCHAR(255),
	borrow_patient_id VARCHAR(255),
	borrow_date VARCHAR(255),
	borrow_time VARCHAR(255),
	borrow_address TEXT,
	telephone VARCHAR(255),
	fix_borrow_file_type_id VARCHAR(255),
	borrow_detail TEXT,
	approve_eid VARCHAR(255),
	return_person_type VARCHAR(255),
	return_eid VARCHAR(255),
	return_name VARCHAR(255),
	return_date VARCHAR(255),
	return_time VARCHAR(255),
	receive_eid VARCHAR(255),
	fix_file_status_id VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.patient_file_borrow ADD CONSTRAINT patient_file_borrow_pkey PRIMARY KEY (patient_file_borrow_id);
CREATE INDEX patient_file_borrow_borrow_date ON imed.patient_file_borrow(borrow_date);
CREATE INDEX patient_file_borrow_borrow_eid ON imed.patient_file_borrow(borrow_eid);
CREATE INDEX patient_file_borrow_borrow_file_type ON imed.patient_file_borrow(fix_borrow_file_type_id);
CREATE INDEX patient_file_borrow_borrow_spid ON imed.patient_file_borrow(borrow_spid);
CREATE INDEX patient_file_borrow_file_type ON imed.patient_file_borrow(file_type);
CREATE INDEX patient_file_borrow_hn ON imed.patient_file_borrow(hn);
CREATE INDEX patient_file_borrow_pat_id ON imed.patient_file_borrow(patient_id);

CREATE TABLE imed.patient_film (
	patient_film_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	hn VARCHAR(255),
	film_number VARCHAR(255),
	fix_film_type_id VARCHAR(255),
	fix_film_status_id VARCHAR(255)
);
ALTER TABLE imed.patient_film ADD CONSTRAINT patient_film_pkey PRIMARY KEY (patient_film_id);
CREATE INDEX patient_film_film_number ON imed.patient_film(film_number);
CREATE INDEX patient_film_film_status ON imed.patient_film(fix_film_status_id);
CREATE INDEX patient_film_film_type ON imed.patient_film(fix_film_type_id);
CREATE INDEX patient_film_hn ON imed.patient_film(hn);
CREATE INDEX patient_film_patient_id ON imed.patient_film(patient_id);

CREATE TABLE imed.patient_film_borrow (
	patient_film_borrow_id VARCHAR(255) NOT NULL,
	patient_film_id VARCHAR(255),
	fix_person_type_id VARCHAR(255),
	borrow_name VARCHAR(255),
	borrow_pid VARCHAR(255),
	borrow_eid VARCHAR(255),
	borrow_spid VARCHAR(255),
	borrow_patient_id VARCHAR(255),
	borrow_doctor_eid VARCHAR(255),
	borrow_date VARCHAR(255),
	borrow_time VARCHAR(255),
	borrow_address TEXT,
	telephone VARCHAR(255),
	borrow_detail TEXT,
	approve_eid VARCHAR(255),
	return_person_type VARCHAR(255),
	return_eid VARCHAR(255),
	return_name VARCHAR(255),
	return_date VARCHAR(255),
	return_time VARCHAR(255),
	receive_eid VARCHAR(255),
	fix_film_status_id VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.patient_film_borrow ADD CONSTRAINT patient_film_borrow_pkey PRIMARY KEY (patient_film_borrow_id);
CREATE INDEX patient_film_borrow_borrow_date ON imed.patient_film_borrow(borrow_date);
CREATE INDEX patient_film_borrow_borrow_eid ON imed.patient_film_borrow(borrow_eid);
CREATE INDEX patient_film_borrow_borrow_spid ON imed.patient_film_borrow(borrow_spid);
CREATE INDEX patient_film_borrow_film_status ON imed.patient_film_borrow(fix_film_status_id);
CREATE INDEX patient_film_borrow_patient_film_id ON imed.patient_film_borrow(patient_film_id);
CREATE INDEX patient_film_person_type ON imed.patient_film_borrow(fix_person_type_id);

CREATE TABLE imed.patient_name_history (
	patient_name_history_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	firstname VARCHAR(255),
	lastname VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.patient_name_history ADD CONSTRAINT patient_name_history_pkey PRIMARY KEY (patient_name_history_id);
CREATE INDEX patient_name_history_patient_id ON imed.patient_name_history(patient_id);

CREATE TABLE imed.patient_payment (
	patient_payment_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	plan_id VARCHAR(255),
	card_id VARCHAR(255),
	inspire_date VARCHAR(255),
	expire_date VARCHAR(255),
	main_hospital_code VARCHAR(255),
	sub_hospital_code VARCHAR(255),
	subtype VARCHAR(255)
);
ALTER TABLE imed.patient_payment ADD CONSTRAINT patient_payment_pkey PRIMARY KEY (patient_payment_id);
CREATE INDEX patient_payment_patient_i ON imed.patient_payment(patient_id);
CREATE INDEX patient_payment_plan_id ON imed.patient_payment(plan_id);

CREATE TABLE imed.payer (
	payer_id VARCHAR(255) NOT NULL,
	prename VARCHAR(255),
	description VARCHAR(255),
	more_description VARCHAR(255),
	contract_sub VARCHAR(255),
	contract_sub_code VARCHAR(255),
	contact_name VARCHAR(255),
	telephone VARCHAR(255),
	home_id VARCHAR(255),
	road VARCHAR(255),
	village VARCHAR(255),
	fix_tambol_id VARCHAR(255),
	fix_amphur_id VARCHAR(255),
	fix_changwat_id VARCHAR(255),
	postcode VARCHAR(255),
	last_update VARCHAR(255)
);
ALTER TABLE imed.payer ADD CONSTRAINT payer_pkey PRIMARY KEY (payer_id);

CREATE TABLE imed.personal_illness (
	personal_illness_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	illness_name VARCHAR(255),
	note VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.personal_illness ADD CONSTRAINT personal_illness_pkey PRIMARY KEY (personal_illness_id);
CREATE INDEX patient_id_personal_illness_key ON imed.personal_illness(patient_id);

CREATE TABLE imed.physical_examination (
	physical_examination_id VARCHAR(255) NOT NULL,
	vital_sign_extend_id VARCHAR(255),
	organ VARCHAR(255),
	detail TEXT,
	result_position VARCHAR(255)
);
ALTER TABLE imed.physical_examination ADD CONSTRAINT physical_examination_pkey PRIMARY KEY (physical_examination_id);
CREATE INDEX physical_examination_position ON imed.physical_examination(result_position);
CREATE INDEX physical_examination_vs_extend_id ON imed.physical_examination(vital_sign_extend_id);

CREATE TABLE imed.plan (
	plan_id VARCHAR(255) NOT NULL,
	plan_code VARCHAR(255),
	prename VARCHAR(255),
	description VARCHAR(255),
	nsd VARCHAR(255),
	alias_name VARCHAR(255),
	payer_id VARCHAR(255),
	contract_office_id VARCHAR(255),
	base_plan_group_id VARCHAR(255),
	fix_plan_type_id VARCHAR(255),
	fix_contract_type_id VARCHAR(255),
	inscl VARCHAR(255),
	subtype VARCHAR(255),
	pttype VARCHAR(255),
	fix_0110_5_id VARCHAR(255),
	auto_order VARCHAR(255),
	inspire_date VARCHAR(255),
	expire_date VARCHAR(255),
	last_update VARCHAR(255),
	plan_set_billing_group_id VARCHAR(255),
	template_discount_id VARCHAR(255),
	active VARCHAR(255),
	check_expire VARCHAR(255)
);
ALTER TABLE imed.plan ADD CONSTRAINT plan_pkey PRIMARY KEY (plan_id);
CREATE INDEX plan_0110_5 ON imed.plan(fix_0110_5_id);
CREATE INDEX plan_fix_contract_type ON imed.plan(fix_contract_type_id);
CREATE INDEX plan_inscl ON imed.plan(inscl);
CREATE INDEX plan_payer_id ON imed.plan(payer_id);
CREATE INDEX plan_plan_group ON imed.plan(base_plan_group_id);
CREATE INDEX plan_pttype ON imed.plan(pttype);

CREATE TABLE imed.plan_auto_order (
	plan_auto_order_id VARCHAR(255) NOT NULL,
	plan_id VARCHAR(255),
	item_id VARCHAR(255),
	order_day VARCHAR(255),
	begin_order_time VARCHAR(255),
	finish_order_time VARCHAR(255)
);
ALTER TABLE imed.plan_auto_order ADD CONSTRAINT plan_auto_order_pkey PRIMARY KEY (plan_auto_order_id);
CREATE INDEX plan_auto_order_begin_time ON imed.plan_auto_order(begin_order_time);
CREATE INDEX plan_auto_order_finish_time ON imed.plan_auto_order(finish_order_time);
CREATE INDEX plan_auto_order_order_day ON imed.plan_auto_order(order_day);
CREATE INDEX plan_auto_order_plan_id ON imed.plan_auto_order(plan_id);

CREATE TABLE imed.plan_billing_group (
	plan_billing_group_id VARCHAR(255) NOT NULL,
	plan_set_billing_group_id VARCHAR(255),
	description VARCHAR(255),
	base_billing_group_id VARCHAR(255),
	priority VARCHAR(255)
);
ALTER TABLE imed.plan_billing_group ADD CONSTRAINT plan_billing_group_pkey PRIMARY KEY (plan_billing_group_id);

CREATE TABLE imed.plan_document (
	plan_document_id VARCHAR(255) NOT NULL,
	plan_id VARCHAR(255),
	description TEXT,
	discount VARCHAR(255),
	fix_visit_type_id VARCHAR(255)
);
ALTER TABLE imed.plan_document ADD CONSTRAINT plan_document_pkey PRIMARY KEY (plan_document_id);
CREATE INDEX plan_document_plan_id ON imed.plan_document(plan_id);

CREATE TABLE imed.plan_dx_except (
	plan_dx_except_id VARCHAR(255) NOT NULL,
	plan_id VARCHAR(255),
	dx_code VARCHAR(255),
	dx_description TEXT,
	fix_visit_type_id VARCHAR(255)
);
ALTER TABLE imed.plan_dx_except ADD CONSTRAINT plan_dx_except_pkey PRIMARY KEY (plan_dx_except_id);
CREATE INDEX plan_dx_except_plan_id ON imed.plan_dx_except(plan_id);

CREATE TABLE imed.plan_external (
	plan_external_id VARCHAR(255) NOT NULL,
	plan_code VARCHAR(255),
	hn VARCHAR(255),
	pid VARCHAR(255),
	prename VARCHAR(255),
	firstname VARCHAR(255),
	lastname VARCHAR(255),
	nsd VARCHAR(255),
	lsd VARCHAR(255),
	card_id VARCHAR(255),
	inspire_date VARCHAR(255),
	expire_date VARCHAR(255),
	main_hospital_code VARCHAR(255),
	sub_hospital_code VARCHAR(255),
	last_update VARCHAR(255),
	remark VARCHAR(255),
	employee_id VARCHAR(255),
	credit_limit VARCHAR(255),
	current_credit_limit VARCHAR(255),
	active VARCHAR(255)
);
ALTER TABLE imed.plan_external ADD CONSTRAINT plan_external_pkey PRIMARY KEY (plan_external_id);
CREATE INDEX plan_ext_pat_id ON imed.plan_external(hn);
CREATE INDEX plan_ext_plan_code ON imed.plan_external(plan_code);
CREATE INDEX plan_external_active ON imed.plan_external(active);
CREATE INDEX plan_external_employee_id ON imed.plan_external(plan_external_id);
CREATE INDEX plan_external_fsoundex ON imed.plan_external(nsd);
CREATE INDEX plan_external_lsoundex ON imed.plan_external(lsd);
CREATE INDEX plan_external_pid ON imed.plan_external(pid);

CREATE TABLE imed.plan_external_visit (
	plan_external_visit_id VARCHAR(255) NOT NULL,
	plan_external_id VARCHAR(255),
	visit_id VARCHAR(255)
);
ALTER TABLE imed.plan_external_visit ADD CONSTRAINT plan_external_visit_pkey PRIMARY KEY (plan_external_visit_id);
CREATE INDEX plan_external_visit_id ON imed.plan_external_visit(visit_id);

CREATE TABLE imed.plan_set_billing_group (
	plan_set_billing_group_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.plan_set_billing_group ADD CONSTRAINT plan_set_billing_group_pkey PRIMARY KEY (plan_set_billing_group_id);

CREATE TABLE imed.prepack_item (
	prepack_item_id VARCHAR(255) NOT NULL,
	item_id VARCHAR(255),
	quantity VARCHAR(255),
	base_unit_id VARCHAR(255),
	produce_date VARCHAR(255),
	expire_date VARCHAR(255),
	lot_number VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.prepack_item ADD CONSTRAINT prepack_item_pkey PRIMARY KEY (prepack_item_id);
CREATE INDEX prepack_item_item_id ON imed.prepack_item(item_id);

CREATE TABLE imed.prescription (
	prescription_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	patient_id VARCHAR(255),
	pn VARCHAR(255),
	assign_doctor_eid VARCHAR(255),
	assign_eid VARCHAR(255),
	assign_spid VARCHAR(255),
	assign_date VARCHAR(255),
	assign_time VARCHAR(255),
	receive_spid VARCHAR(255),
	assign_order_status VARCHAR(255),
	execute_eid VARCHAR(255),
	dispense_eid VARCHAR(255),
	complete_date VARCHAR(255),
	complete_time VARCHAR(255),
	take_home VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255),
	proxy_status VARCHAR(255),
	proxy_user VARCHAR(255)
);
ALTER TABLE imed.prescription ADD CONSTRAINT prescription_pkey PRIMARY KEY (prescription_id);
CREATE INDEX pres_ass_doctor_eid ON imed.prescription(assign_doctor_eid);
CREATE INDEX pres_assign_eid ON imed.prescription(assign_eid);
CREATE INDEX pres_assign_spid ON imed.prescription(assign_spid);
CREATE INDEX prescription_assign_date ON imed.prescription(assign_date);
CREATE INDEX prescription_assign_status ON imed.prescription(assign_order_status);
CREATE INDEX prescription_patient_id ON imed.prescription(patient_id);
CREATE INDEX prescription_pn ON imed.prescription(pn);
CREATE INDEX prescription_visit_id ON imed.prescription(visit_id);

CREATE TABLE imed.previous_drug_used (
	previous_drug_used_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	drug_name VARCHAR(255),
	note VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.previous_drug_used ADD CONSTRAINT previous_drug_used_pkey PRIMARY KEY (previous_drug_used_id);
CREATE INDEX patient_id_previous_drug_used_key ON imed.previous_drug_used(patient_id);

CREATE TABLE imed.previous_test (
	previous_test_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	name VARCHAR(255),
	note VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.previous_test ADD CONSTRAINT previous_test_pkey PRIMARY KEY (previous_test_id);
CREATE INDEX patient_id_previous_test_key ON imed.previous_test(patient_id);

CREATE TABLE imed.progress_note (
	progress_note_id VARCHAR(255) NOT NULL,
	admit_id VARCHAR(255),
	note TEXT,
	doctor_eid VARCHAR(255),
	record_date VARCHAR(255),
	record_time VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.progress_note ADD CONSTRAINT progress_note_pkey PRIMARY KEY (progress_note_id);
CREATE INDEX progress_note_admit_id ON imed.progress_note(admit_id);

CREATE TABLE imed.queue_mgnt (
	queue_mgnt_id VARCHAR(255) NOT NULL,
	base_service_point_id VARCHAR(255),
	employee_id VARCHAR(255),
	max_queue_number_opd VARCHAR(255),
	max_queue_number_ipd VARCHAR(255)
);
ALTER TABLE imed.queue_mgnt ADD CONSTRAINT queue_mgnt_pkey PRIMARY KEY (queue_mgnt_id);
CREATE INDEX queue_mgnt_base_service_point_id ON imed.queue_mgnt(base_service_point_id);
CREATE INDEX queue_mgnt_employee_id ON imed.queue_mgnt(employee_id);

CREATE TABLE imed.receipt (
	receipt_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	visit_id VARCHAR(255),
	receipt_number VARCHAR(255),
	com_number VARCHAR(255),
	fax_claim_number VARCHAR(255),
	office_number VARCHAR(255),
	head_office_number VARCHAR(255),
	document_number VARCHAR(255),
	debt_number VARCHAR(255),
	document_date VARCHAR(255),
	diagnosis VARCHAR(255),
	fix_visit_type_id VARCHAR(255),
	fix_receipt_type_id VARCHAR(255),
	fix_receipt_status_id VARCHAR(255),
	receive_eid VARCHAR(255),
	receive_date VARCHAR(255),
	receive_time VARCHAR(255),
	last_print_eid VARCHAR(255),
	last_print_date VARCHAR(255),
	last_print_time VARCHAR(255),
	prepare_cancel_eid VARCHAR(255),
	prepare_cancel_date VARCHAR(255),
	prepare_cancel_time VARCHAR(255),
	cancel_eid VARCHAR(255),
	cancel_date VARCHAR(255),
	cancel_time VARCHAR(255),
	return_eid VARCHAR(255),
	return_date VARCHAR(255),
	return_time VARCHAR(255),
	max_print_times VARCHAR(255),
	discharge VARCHAR(255),
	fix_payment_type_id VARCHAR(255),
	sum_paid_receipt_bill_grp VARCHAR(255),
	discount VARCHAR(255),
	special_discount VARCHAR(255),
	decimal_discount VARCHAR(255),
	expense VARCHAR(255),
	cost VARCHAR(255),
	paid VARCHAR(255),
	pay VARCHAR(255),
	change VARCHAR(255),
	deposit VARCHAR(255),
	cut_from_receipt_id VARCHAR(255),
	template_discount_id VARCHAR(255),
	template_discount_name VARCHAR(255),
	base_deliver_document_id VARCHAR(255),
	base_discount_reason_id VARCHAR(255),
	discount_sub_reason_id VARCHAR(255),
	special_discount_reason_id VARCHAR(255),
	special_disc_sub_reason_id VARCHAR(255),
	fix_billing_group_type_id VARCHAR(255),
	begin_date VARCHAR(255),
	begin_time VARCHAR(255),
	end_date VARCHAR(255),
	end_time VARCHAR(255),
	num_date VARCHAR(255),
	plan_id VARCHAR(255),
	calculate_times_receipt VARCHAR(255),
	sp_id VARCHAR(255),
	fix_language_type_id VARCHAR(255),
	note VARCHAR(255),
	is_print_over_cost VARCHAR(255),
	debt_change VARCHAR(255),
	base_receipt_category_id VARCHAR(255),
	invoice_status VARCHAR(255),
	invoice_remain VARCHAR(255),
	receipt_name VARCHAR(255)
);
ALTER TABLE imed.receipt ADD CONSTRAINT receipt_pkey PRIMARY KEY (receipt_id);
CREATE INDEX rcpt_com_number ON imed.receipt(com_number);
CREATE INDEX rcpt_debt_change ON imed.receipt(debt_change);
CREATE INDEX rcpt_invoice_status ON imed.receipt(invoice_status);
CREATE INDEX rcpt_patient_id ON imed.receipt(patient_id);
CREATE INDEX rcpt_payment_type_id ON imed.receipt(fix_payment_type_id);
CREATE INDEX rcpt_plan_id ON imed.receipt(plan_id);
CREATE INDEX rcpt_receipt_number ON imed.receipt(receipt_number);
CREATE INDEX rcpt_receipt_status_id ON imed.receipt(fix_receipt_status_id);
CREATE INDEX rcpt_receipt_type_id ON imed.receipt(fix_receipt_type_id);
CREATE INDEX rcpt_receive_date ON imed.receipt(receive_date);
CREATE INDEX rcpt_receive_time ON imed.receipt(receive_time);
CREATE INDEX rcpt_visit_id ON imed.receipt(visit_id);

CREATE TABLE imed.receipt_attach (
	receipt_attach_id VARCHAR(255) NOT NULL,
	receipt_id VARCHAR(255),
	fix_billing_group_type_id VARCHAR(255),
	cost VARCHAR(255)
);
ALTER TABLE imed.receipt_attach ADD CONSTRAINT receipt_attach_pkey PRIMARY KEY (receipt_attach_id);
CREATE INDEX receipt_attach_rcpt_id ON imed.receipt_attach(receipt_id);

CREATE TABLE imed.receipt_attach_bill_grp (
	receipt_attach_bill_grp_id VARCHAR(255) NOT NULL,
	receipt_attach_id VARCHAR(255),
	base_billing_group_id VARCHAR(255),
	cost VARCHAR(255)
);
ALTER TABLE imed.receipt_attach_bill_grp ADD CONSTRAINT receipt_attach_bill_grp_pkey PRIMARY KEY (receipt_attach_bill_grp_id);
CREATE INDEX rcpt_atch_grp_rcpt_atch_id ON imed.receipt_attach_bill_grp(receipt_attach_id);

CREATE TABLE imed.receipt_billing_group (
	receipt_billing_group_id VARCHAR(255) NOT NULL,
	receipt_id VARCHAR(255),
	base_billing_group_id VARCHAR(255),
	patient_id VARCHAR(255),
	visit_id VARCHAR(255),
	expense VARCHAR(255),
	cost VARCHAR(255),
	discount_percent VARCHAR(255),
	discount VARCHAR(255),
	paid VARCHAR(255),
	fund_enable_value VARCHAR(255),
	fund_disable_value VARCHAR(255),
	debt_change VARCHAR(255)
);
ALTER TABLE imed.receipt_billing_group ADD CONSTRAINT receipt_billing_group_pkey PRIMARY KEY (receipt_billing_group_id);
CREATE INDEX rcpt_bill_grp_grpid ON imed.receipt_billing_group(base_billing_group_id);
CREATE INDEX rcpt_bill_grp_pat ON imed.receipt_billing_group(patient_id);
CREATE INDEX rcpt_bill_grp_rcptid ON imed.receipt_billing_group(receipt_id);
CREATE INDEX rcpt_bill_grp_visit ON imed.receipt_billing_group(visit_id);

CREATE TABLE imed.receipt_paid_method (
	receipt_paid_method_id VARCHAR(255) NOT NULL,
	receipt_id VARCHAR(255),
	base_paid_method_id VARCHAR(255),
	paid VARCHAR(255),
	reference_number VARCHAR(255),
	transfer_date VARCHAR(255),
	approve_number VARCHAR(255),
	base_credit_card_id VARCHAR(255),
	charge_percent VARCHAR(255),
	charge VARCHAR(255),
	total_paid VARCHAR(255),
	bank_code VARCHAR(255),
	bank_branch_code VARCHAR(255),
	is_multi_receipt VARCHAR(255),
	value VARCHAR(255),
	due_date VARCHAR(255),
	comments VARCHAR(255)
);
ALTER TABLE imed.receipt_paid_method ADD CONSTRAINT receipt_paid_method_pkey PRIMARY KEY (receipt_paid_method_id);
CREATE INDEX rcpt_paid_mtd_rcpt_id ON imed.receipt_paid_method(receipt_id);

CREATE TABLE imed.receipt_pay_invoice (
	receipt_pay_invoice_id VARCHAR(255) NOT NULL,
	pay_receipt_id VARCHAR(255),
	invoice_receipt_id VARCHAR(255)
);
ALTER TABLE imed.receipt_pay_invoice ADD CONSTRAINT receipt_pay_invoice_pkey PRIMARY KEY (receipt_pay_invoice_id);
CREATE INDEX rcpt_invoice_receipt_id ON imed.receipt_pay_invoice(invoice_receipt_id);

CREATE TABLE imed.receipt_print_group (
	receipt_print_group_id VARCHAR(255) NOT NULL,
	receipt_id VARCHAR(255),
	print_code VARCHAR(255),
	print_description VARCHAR(255),
	expense VARCHAR(255),
	cost VARCHAR(255),
	discount_percent VARCHAR(255),
	discount VARCHAR(255),
	paid VARCHAR(255),
	print_position VARCHAR(255)
);
ALTER TABLE imed.receipt_print_group ADD CONSTRAINT receipt_print_group_pkey PRIMARY KEY (receipt_print_group_id);
CREATE INDEX rcpt_prn_grp_rcptid ON imed.receipt_print_group(receipt_id);

CREATE TABLE imed.receipt_print_history (
	receipt_print_history_id VARCHAR(255) NOT NULL,
	receipt_id VARCHAR(255),
	receipt_print_eid VARCHAR(255),
	receipt_print_date VARCHAR(255),
	receipt_print_time VARCHAR(255),
	print_times VARCHAR(255),
	note TEXT
);
ALTER TABLE imed.receipt_print_history ADD CONSTRAINT receipt_print_history_pkey PRIMARY KEY (receipt_print_history_id);
CREATE INDEX receipt_print_history_receipt_id ON imed.receipt_print_history(receipt_id);

CREATE TABLE imed.refer_in (
	refer_in_id VARCHAR(255) NOT NULL,
	base_office_id VARCHAR(255),
	visit_id VARCHAR(255),
	refer_eid VARCHAR(255),
	refer_date VARCHAR(255),
	refer_time VARCHAR(255),
	refer_type VARCHAR(255),
	note TEXT,
	doctor_eid VARCHAR(255)
);
ALTER TABLE imed.refer_in ADD CONSTRAINT refer_in_pkey PRIMARY KEY (refer_in_id);
CREATE INDEX visit_id_refer_in_key ON imed.refer_in(visit_id);

CREATE TABLE imed.refer_out (
	refer_out_id VARCHAR(255) NOT NULL,
	base_office_id VARCHAR(255),
	visit_id VARCHAR(255),
	refer_eid VARCHAR(255),
	refer_date VARCHAR(255),
	refer_time VARCHAR(255),
	refer_type VARCHAR(255),
	note TEXT
);
ALTER TABLE imed.refer_out ADD CONSTRAINT refer_out_pkey PRIMARY KEY (refer_out_id);
CREATE INDEX visit_id_refer_out_key ON imed.refer_out(visit_id);

CREATE TABLE imed.report_drug_interaction (
	report_drug_interaction_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	item_id1 VARCHAR(255),
	item_id2 VARCHAR(255),
	drug1 VARCHAR(255),
	drug2 VARCHAR(255),
	significance VARCHAR(255),
	onset VARCHAR(255),
	severity VARCHAR(255),
	document VARCHAR(255),
	effects VARCHAR(255),
	mechanism VARCHAR(255),
	management VARCHAR(255),
	ref VARCHAR(255),
	page VARCHAR(255)
);
ALTER TABLE imed.report_drug_interaction ADD CONSTRAINT report_drug_interaction_pkey PRIMARY KEY (report_drug_interaction_id);
CREATE INDEX idx_report_drug_interaction_visit_id ON imed.report_drug_interaction(visit_id);

CREATE TABLE imed.report_drug_pregnancy (
	report_drug_pregnancy_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	order_item_id VARCHAR(255),
	effect_level VARCHAR(255),
	comments VARCHAR(255)
);
ALTER TABLE imed.report_drug_pregnancy ADD CONSTRAINT report_drug_pregnancy_pkey PRIMARY KEY (report_drug_pregnancy_id);
CREATE INDEX idx_report_drug_pregnancy_visit_id ON imed.report_drug_pregnancy(visit_id);
CREATE INDEX report_drug_pregnancy_order_item_id ON imed.report_drug_pregnancy(order_item_id);

CREATE TABLE imed.report_group (
	report_group_id VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	fix_report_id VARCHAR(255)
);
ALTER TABLE imed.report_group ADD CONSTRAINT report_group_pkey PRIMARY KEY (report_group_id);

CREATE TABLE imed.report_group_billing (
	report_group_billing_id VARCHAR(255) NOT NULL,
	report_group_id VARCHAR(255),
	base_billing_group_id VARCHAR(255)
);
ALTER TABLE imed.report_group_billing ADD CONSTRAINT report_group_billing_pkey PRIMARY KEY (report_group_billing_id);

CREATE TABLE imed.report_group_item (
	report_group_item_id VARCHAR(255) NOT NULL,
	report_group_id VARCHAR(255),
	item_id VARCHAR(255)
);
ALTER TABLE imed.report_group_item ADD CONSTRAINT report_group_item_pkey PRIMARY KEY (report_group_item_id);

CREATE TABLE imed.report_group_plan (
	report_group_plan_id VARCHAR(255),
	report_gruop_id VARCHAR(255),
	plan_id VARCHAR(255)
);

CREATE TABLE imed.report_range_time (
	report_range_time VARCHAR(255),
	description VARCHAR(255),
	time_from VARCHAR(255),
	time_to VARCHAR(255)
);

CREATE TABLE imed.return_drug (
	return_drug_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	patient_id VARCHAR(255),
	item_id VARCHAR(255),
	return_quantity VARCHAR(255),
	return_note VARCHAR(255),
	return_drug_allergy VARCHAR(255),
	return_eid VARCHAR(255),
	return_spid VARCHAR(255),
	return_date VARCHAR(255),
	return_time VARCHAR(255),
	receive_quantity VARCHAR(255),
	approve_quantity VARCHAR(255),
	lot_no VARCHAR(255),
	receive_note VARCHAR(255),
	receive_eid VARCHAR(255),
	receive_spid VARCHAR(255),
	receive_date VARCHAR(255),
	receive_time VARCHAR(255),
	order_item_id VARCHAR(255),
	dispense_unit_price VARCHAR(255),
	dispense_visit_id VARCHAR(255)
);
ALTER TABLE imed.return_drug ADD CONSTRAINT return_drug_pkey PRIMARY KEY (return_drug_id);
CREATE INDEX idx_return_drug_pat_id ON imed.return_drug(patient_id);
CREATE INDEX return_drug_item_id ON imed.return_drug(item_id);
CREATE INDEX return_drug_order_id ON imed.return_drug(order_item_id);
CREATE INDEX return_drug_visit_id ON imed.return_drug(visit_id);
CREATE INDEX rtd_dispense_unit_price ON imed.return_drug(dispense_unit_price);
CREATE INDEX rtd_dispense_visit_id ON imed.return_drug(dispense_visit_id);

CREATE TABLE imed.risk_factor (
	risk_factor_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	name VARCHAR(255),
	note VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255)
);
ALTER TABLE imed.risk_factor ADD CONSTRAINT risk_factor_pkey PRIMARY KEY (risk_factor_id);
CREATE INDEX patient_id_risk_factor_key ON imed.risk_factor(patient_id);

CREATE TABLE imed.seq_an (
	seq_an_id VARCHAR(254),
	value VARCHAR(254)
);

CREATE TABLE imed.seq_axn (
	seq_axn_id VARCHAR(254),
	value VARCHAR(254)
);

CREATE TABLE imed.seq_en (
	seq_en_id VARCHAR(255) NOT NULL,
	value VARCHAR(255) NOT NULL
);
ALTER TABLE imed.seq_en ADD CONSTRAINT seq_en_pkey PRIMARY KEY (seq_en_id);

CREATE TABLE imed.seq_hn (
	seq_hn_id VARCHAR(255) NOT NULL,
	value VARCHAR(255) NOT NULL
);
ALTER TABLE imed.seq_hn ADD CONSTRAINT seq_hn_pkey PRIMARY KEY (seq_hn_id);

CREATE TABLE imed.seq_ln (
	seq_ln_id VARCHAR(254) NOT NULL,
	value VARCHAR(254)
);
ALTER TABLE imed.seq_ln ADD CONSTRAINT seq_ln_pkey PRIMARY KEY (seq_ln_id);

CREATE TABLE imed.seq_pn (
	seq_pn_id VARCHAR(255) NOT NULL,
	value VARCHAR(255) NOT NULL
);
ALTER TABLE imed.seq_pn ADD CONSTRAINT seq_pn_pkey PRIMARY KEY (seq_pn_id);

CREATE TABLE imed.seq_receipt_number (
	seq_receipt_number_id VARCHAR(255) NOT NULL,
	com_number VARCHAR(255) NOT NULL,
	ip_address VARCHAR(255) NOT NULL,
	value VARCHAR(255) NOT NULL,
	last_print_maximum VARCHAR(255),
	last_print_minimum VARCHAR(255),
	fix_visit_type_id VARCHAR(255),
	fix_receipt_type_id VARCHAR(255),
	base_receipt_category_id VARCHAR(255)
);
ALTER TABLE imed.seq_receipt_number ADD CONSTRAINT seq_receipt_number_pkey PRIMARY KEY (seq_receipt_number_id);

CREATE TABLE imed.seq_un (
	seq_un_id VARCHAR(255) NOT NULL,
	value VARCHAR(255) NOT NULL
);
ALTER TABLE imed.seq_un ADD CONSTRAINT seq_un_pkey PRIMARY KEY (seq_un_id);

CREATE TABLE imed.seq_vn (
	seq_vn_id VARCHAR(255) NOT NULL,
	value VARCHAR(255) NOT NULL
);
ALTER TABLE imed.seq_vn ADD CONSTRAINT seq_vn_pkey PRIMARY KEY (seq_vn_id);

CREATE TABLE imed.seq_xn (
	seq_xn_id VARCHAR(255) NOT NULL,
	value VARCHAR(255) NOT NULL
);
ALTER TABLE imed.seq_xn ADD CONSTRAINT seq_xn_pkey PRIMARY KEY (seq_xn_id);

CREATE TABLE imed.service_point_schedule (
	service_point_schedule_id VARCHAR(255) NOT NULL,
	base_service_point_id VARCHAR(255),
	fix_day_of_week VARCHAR(255),
	fix_time_of_day VARCHAR(255),
	open_service VARCHAR(255)
);
ALTER TABLE imed.service_point_schedule ADD CONSTRAINT service_point_schedule_pkey PRIMARY KEY (service_point_schedule_id);
CREATE INDEX idx_service_point_schedule_sp_id ON imed.service_point_schedule(base_service_point_id);

CREATE TABLE imed.service_time_stamp (
	service_time_stamp_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	spid VARCHAR(255),
	doctor_eid VARCHAR(255),
	assign_date VARCHAR(255),
	assign_time VARCHAR(255),
	begin_date VARCHAR(255),
	begin_time VARCHAR(255),
	finish_date VARCHAR(255),
	finish_time VARCHAR(255)
);
ALTER TABLE imed.service_time_stamp ADD CONSTRAINT service_time_stamp_pkey PRIMARY KEY (service_time_stamp_id);
CREATE INDEX idx_servtimestamp_assign ON imed.service_time_stamp(assign_date, assign_time);
CREATE INDEX idx_servtimestamp_begin ON imed.service_time_stamp(begin_date, begin_time);
CREATE INDEX idx_servtimestamp_doctor_eid ON imed.service_time_stamp(doctor_eid);
CREATE INDEX idx_servtimestamp_finish ON imed.service_time_stamp(finish_date, finish_time);
CREATE INDEX idx_servtimestamp_spid ON imed.service_time_stamp(spid);

CREATE TABLE imed.sql_except (
	sql_except_id VARCHAR(255) NOT NULL,
	sql_statement_id VARCHAR(255),
	sql_group_role_id VARCHAR(255)
);
ALTER TABLE imed.sql_except ADD CONSTRAINT sql_except_pkey PRIMARY KEY (sql_except_id);

CREATE TABLE imed.sql_group_role (
	sql_group_role_id VARCHAR(255) NOT NULL,
	base_sql_group_id VARCHAR(255),
	employee_id VARCHAR(255)
);
ALTER TABLE imed.sql_group_role ADD CONSTRAINT sql_group_role_pkey PRIMARY KEY (sql_group_role_id);

CREATE TABLE imed.sql_statement (
	sql_statement_id VARCHAR(255) NOT NULL,
	sql_statement_name VARCHAR(255),
	sql_statement_detail VARCHAR(255),
	base_sql_group_id VARCHAR(255)
);
ALTER TABLE imed.sql_statement ADD CONSTRAINT sql_statement_pkey PRIMARY KEY (sql_statement_id);

CREATE TABLE imed.ss_money (
	in_date VARCHAR(255),
	out_date VARCHAR(255),
	on_time VARCHAR(255),
	hn VARCHAR(255),
	department VARCHAR(255),
	accident VARCHAR(255),
	co_code VARCHAR(255),
	dr_name VARCHAR(255),
	dr_mode VARCHAR(255),
	doc_no VARCHAR(255),
	dr_fee VARCHAR(255),
	dr_fee_fin VARCHAR(255),
	branch VARCHAR(255),
	dr_opera VARCHAR(255),
	dr_op_sale VARCHAR(255),
	anaes VARCHAR(255),
	anaes_sale VARCHAR(255),
	medicine VARCHAR(255),
	opd_price VARCHAR(255),
	ipd_price VARCHAR(255),
	lab VARCHAR(255),
	lab_sale VARCHAR(255),
	xray VARCHAR(255),
	xray_sale VARCHAR(255),
	ct VARCHAR(255),
	ct_sale VARCHAR(255),
	ekg VARCHAR(255),
	ekg_sale VARCHAR(255),
	ultra VARCHAR(255),
	ultra_sale VARCHAR(255),
	laser VARCHAR(255),
	laser_sale VARCHAR(255),
	operation VARCHAR(255),
	op_sale VARCHAR(255),
	op_equip VARCHAR(255),
	equip_sale VARCHAR(255),
	dressing VARCHAR(255),
	dress_sale VARCHAR(255),
	pt VARCHAR(255),
	pt_sale VARCHAR(255),
	dental VARCHAR(255),
	den_sale VARCHAR(255),
	med_supply VARCHAR(255),
	sup_sale VARCHAR(255),
	nursery VARCHAR(255),
	nurse_sale VARCHAR(255),
	room VARCHAR(255),
	room_sale VARCHAR(255),
	meals VARCHAR(255),
	meals_sale VARCHAR(255),
	kidney VARCHAR(255),
	kid_sale VARCHAR(255),
	stone VARCHAR(255),
	stone_sale VARCHAR(255),
	law VARCHAR(255),
	law_sale VARCHAR(255),
	others VARCHAR(255),
	other_sale VARCHAR(255),
	car VARCHAR(255),
	car_sale VARCHAR(255),
	prosthesis VARCHAR(255),
	pros_sale VARCHAR(255),
	paid VARCHAR(255),
	netprice VARCHAR(255),
	icd_code VARCHAR(255),
	ipd_opd VARCHAR(255),
	new_fox VARCHAR(255),
	give_serv VARCHAR(255),
	network VARCHAR(255),
	backdate VARCHAR(255),
	is_updated VARCHAR(255)
);

CREATE TABLE imed.stock (
	stock_id VARCHAR(255) NOT NULL,
	stock_name VARCHAR(255),
	fix_stock_type_id VARCHAR(255),
	fix_stock_update_type_id VARCHAR(255),
	limit_per_year VARCHAR(255),
	limit_summary VARCHAR(255),
	limit_order_value VARCHAR(255),
	seq_request_number VARCHAR(255),
	seq_transfer_number VARCHAR(255),
	seq_return_number VARCHAR(255),
	seq_pr_number VARCHAR(255),
	is_to_period VARCHAR(255)
);
ALTER TABLE imed.stock ADD CONSTRAINT stock_pkey PRIMARY KEY (stock_id);

CREATE TABLE imed.stock_auth (
	stock_auth_id VARCHAR(255) NOT NULL,
	stock_id VARCHAR(255),
	employee_id VARCHAR(255),
	template_name VARCHAR(255),
	stock_module VARCHAR(255),
	manage_item VARCHAR(255),
	dispense_patient VARCHAR(255),
	receive_item VARCHAR(255),
	receive_by_request VARCHAR(255),
	receive_by_order VARCHAR(255),
	receive_by_manufact VARCHAR(255),
	dispense_stock VARCHAR(255),
	make_request VARCHAR(255),
	make_order VARCHAR(255)
);
ALTER TABLE imed.stock_auth ADD CONSTRAINT stock_auth_pkey PRIMARY KEY (stock_auth_id);
CREATE INDEX stock_auth_employee_id ON imed.stock_auth(employee_id);
CREATE INDEX stock_auth_stock_id ON imed.stock_auth(stock_id);

CREATE TABLE imed.stock_auto_receive (
	stock_auto_receive_id VARCHAR(255) NOT NULL,
	disp_stock_id VARCHAR(255),
	recv_stock_id VARCHAR(255),
	auto_receive VARCHAR(255)
);
ALTER TABLE imed.stock_auto_receive ADD CONSTRAINT stock_auto_receive_pkey PRIMARY KEY (stock_auto_receive_id);

CREATE TABLE imed.stock_card (
	stock_card_id VARCHAR(255) NOT NULL,
	stock_mgnt_id VARCHAR(255),
	fix_stock_method_id VARCHAR(255),
	request_or_order_id VARCHAR(255),
	invoice_number VARCHAR(255),
	in_id VARCHAR(255),
	out_id VARCHAR(255),
	manufacturer_id VARCHAR(255),
	stock_id VARCHAR(255),
	item_id VARCHAR(255),
	lot_number VARCHAR(255),
	update_date VARCHAR(255),
	update_time VARCHAR(255),
	update_eid VARCHAR(255),
	previous_qty VARCHAR(255),
	previous_qty_lot VARCHAR(255),
	max_qty VARCHAR(255),
	min_qty VARCHAR(255),
	qty VARCHAR(255),
	free_qty VARCHAR(255),
	update_qty VARCHAR(255),
	update_qty_lot VARCHAR(255),
	is_arrear VARCHAR(255),
	arrear_qty VARCHAR(255),
	small_unit_price_cost VARCHAR(255),
	small_unit_price_sale VARCHAR(255),
	small_unit_id VARCHAR(255),
	big_unit_price_cost VARCHAR(255),
	cost_purchase_no_discount VARCHAR(255),
	cost_purchase VARCHAR(255),
	discount VARCHAR(255),
	discount_percent VARCHAR(255),
	big_unit_id VARCHAR(255),
	unit_rate VARCHAR(255),
	mid_unit_rate VARCHAR(255),
	fiscal_year VARCHAR(255),
	comments VARCHAR(255),
	is_transfer VARCHAR(255),
	transfer_date_from VARCHAR(255),
	transfer_time_from VARCHAR(255),
	transfer_date_to VARCHAR(255),
	transfer_time_to VARCHAR(255),
	have_lot_analyse VARCHAR(255),
	have_fda VARCHAR(255),
	fda_number VARCHAR(255),
	have_gmp VARCHAR(255),
	gmp_number VARCHAR(255),
	return_befor_expire_day VARCHAR(255),
	expire_date VARCHAR(255)
);
ALTER TABLE imed.stock_card ADD CONSTRAINT stock_card_pkey PRIMARY KEY (stock_card_id);
CREATE INDEX stk_card_in_id ON imed.stock_card(in_id);
CREATE INDEX stk_card_item_id ON imed.stock_card(item_id);
CREATE INDEX stk_card_lot_number ON imed.stock_card(lot_number);
CREATE INDEX stk_card_method_id ON imed.stock_card(fix_stock_method_id);
CREATE INDEX stk_card_mgnt_id ON imed.stock_card(stock_mgnt_id);
CREATE INDEX stk_card_out_id ON imed.stock_card(out_id);
CREATE INDEX stk_card_req_or_order_id ON imed.stock_card(request_or_order_id);
CREATE INDEX stk_card_stock_id ON imed.stock_card(stock_id);
CREATE INDEX stk_card_update_date ON imed.stock_card(update_date);
CREATE INDEX stk_card_update_time ON imed.stock_card(update_time);

CREATE TABLE imed.stock_department (
	stock_department_id VARCHAR(255) NOT NULL,
	department_name VARCHAR(255)
);
ALTER TABLE imed.stock_department ADD CONSTRAINT department_pkey PRIMARY KEY (stock_department_id);

CREATE TABLE imed.stock_free_item (
	stock_free_item_id VARCHAR(255) NOT NULL,
	stock_order_id VARCHAR(255),
	item_id VARCHAR(255),
	small_unit_id VARCHAR(255),
	unit_rate VARCHAR(255),
	mid_unit_rate VARCHAR(255),
	big_unit_id VARCHAR(255),
	free_qty VARCHAR(255),
	fix_stock_free_item_method_id VARCHAR(255),
	fix_stock_free_item_status_id VARCHAR(255)
);
ALTER TABLE imed.stock_free_item ADD CONSTRAINT stock_free_item_pkey PRIMARY KEY (stock_free_item_id);
CREATE INDEX stk_free_item_stock_order_id ON imed.stock_free_item(stock_order_id);

CREATE TABLE imed.stock_item_trade_name (
	stock_item_trade_name_id VARCHAR(255) NOT NULL,
	trade_name VARCHAR(255),
	item_id VARCHAR(255),
	distributor_id VARCHAR(255)
);
ALTER TABLE imed.stock_item_trade_name ADD CONSTRAINT stock_item_trade_name_pkey PRIMARY KEY (stock_item_trade_name_id);
CREATE INDEX stk_distributor_id ON imed.stock_item_trade_name(distributor_id);
CREATE INDEX stk_item_id ON imed.stock_item_trade_name(item_id);

CREATE TABLE imed.stock_mgnt (
	stock_mgnt_id VARCHAR(255) NOT NULL,
	stock_id VARCHAR(255),
	item_id VARCHAR(255),
	item_code VARCHAR(255),
	lot_number VARCHAR(255),
	manufacturer_id VARCHAR(255),
	distributor_id VARCHAR(255),
	base_drug_format_id VARCHAR(255),
	produce_date VARCHAR(255),
	expire_date VARCHAR(255),
	range_to_expire VARCHAR(255),
	place VARCHAR(255),
	fix_abc_id VARCHAR(255),
	fix_ven_id VARCHAR(255),
	safety_stock VARCHAR(255),
	stock_day VARCHAR(255),
	fix_stock_method_id VARCHAR(255),
	receive_date VARCHAR(255),
	receive_time VARCHAR(255),
	last_update_date VARCHAR(255),
	last_update_time VARCHAR(255),
	last_request_stock VARCHAR(255),
	unit_price VARCHAR(255),
	unit_price_no_vat VARCHAR(255),
	mid_unit_price VARCHAR(255),
	mid_unit_price_no_vat VARCHAR(255),
	big_unit_price VARCHAR(255),
	big_unit_price_no_vat VARCHAR(255),
	cost_purchase_no_discount VARCHAR(255),
	cost_purchase VARCHAR(255),
	discount VARCHAR(255),
	discount_percent VARCHAR(255),
	vat VARCHAR(255),
	fix_receive_method_id VARCHAR(255),
	cur_quantity VARCHAR(255),
	max_quantity VARCHAR(255),
	min_quantity VARCHAR(255),
	receive_quantity VARCHAR(255),
	free_quantity VARCHAR(255),
	update_qty VARCHAR(255),
	big_unit_id VARCHAR(255),
	small_unit_id VARCHAR(255),
	unit_rate VARCHAR(255),
	mid_unit_rate VARCHAR(255),
	have_lot_analyse VARCHAR(255),
	have_fda VARCHAR(255),
	fda_number VARCHAR(255),
	have_gmp VARCHAR(255),
	gmp_number VARCHAR(255),
	return_befor_expire_day VARCHAR(255),
	active VARCHAR(255),
	is_editable_lot VARCHAR(255),
	fix_tax_type_id VARCHAR(255),
	cost_purchase_no_discount_no_vat VARCHAR(255)
);
ALTER TABLE imed.stock_mgnt ADD CONSTRAINT stock_mgnt_pkey PRIMARY KEY (stock_mgnt_id);
CREATE INDEX stk_drug_format_id ON imed.stock_mgnt(base_drug_format_id);
CREATE INDEX stk_mgnt_dist_id ON imed.stock_mgnt(distributor_id);
CREATE INDEX stk_mgnt_expire_date ON imed.stock_mgnt(expire_date);
CREATE INDEX stk_mgnt_fix_abc_id ON imed.stock_mgnt(fix_abc_id);
CREATE INDEX stk_mgnt_fix_ven_id ON imed.stock_mgnt(fix_ven_id);
CREATE INDEX stk_mgnt_item_id ON imed.stock_mgnt(item_id);
CREATE INDEX stk_mgnt_lot_number ON imed.stock_mgnt(lot_number);
CREATE INDEX stk_mgnt_manufact_id ON imed.stock_mgnt(manufacturer_id);
CREATE INDEX stk_mgnt_produce_date ON imed.stock_mgnt(produce_date);
CREATE INDEX stk_mgnt_receive_date ON imed.stock_mgnt(receive_date);
CREATE INDEX stk_mgnt_stock_id ON imed.stock_mgnt(stock_id);

CREATE TABLE imed.stock_mgnt_period (
	stock_mgnt_period_id VARCHAR(255) NOT NULL,
	stock_id VARCHAR(255),
	item_id VARCHAR(255),
	item_code VARCHAR(255),
	lot_number VARCHAR(255),
	manufacturer_id VARCHAR(255),
	distributor_id VARCHAR(255),
	base_drug_format_id VARCHAR(255),
	produce_date VARCHAR(255),
	expire_date VARCHAR(255),
	range_to_expire VARCHAR(255),
	place VARCHAR(255),
	fix_abc_id VARCHAR(255),
	fix_ven_id VARCHAR(255),
	safety_stock VARCHAR(255),
	stock_day VARCHAR(255),
	fix_stock_method_id VARCHAR(255),
	receive_date VARCHAR(255),
	receive_time VARCHAR(255),
	last_update_date VARCHAR(255),
	last_update_time VARCHAR(255),
	last_request_stock VARCHAR(255),
	unit_price VARCHAR(255),
	unit_price_no_vat VARCHAR(255),
	mid_unit_price VARCHAR(255),
	mid_unit_price_no_vat VARCHAR(255),
	big_unit_price VARCHAR(255),
	big_unit_price_no_vat VARCHAR(255),
	cost_purchase VARCHAR(255),
	vat VARCHAR(255),
	fix_receive_method_id VARCHAR(255),
	cur_quantity VARCHAR(255),
	max_quantity VARCHAR(255),
	min_quantity VARCHAR(255),
	receive_quantity VARCHAR(255),
	free_quantity VARCHAR(255),
	update_qty VARCHAR(255),
	big_unit_id VARCHAR(255),
	small_unit_id VARCHAR(255),
	unit_rate VARCHAR(255),
	mid_unit_rate VARCHAR(255),
	active VARCHAR(255),
	is_editable_lot VARCHAR(255),
	period VARCHAR(225)
);
ALTER TABLE imed.stock_mgnt_period ADD CONSTRAINT stock_mgnt_period_pkey PRIMARY KEY (stock_mgnt_period_id);
CREATE INDEX stk_mgnt_period_dist_id ON imed.stock_mgnt_period(distributor_id);
CREATE INDEX stk_mgnt_period_drug_format_id ON imed.stock_mgnt_period(base_drug_format_id);
CREATE INDEX stk_mgnt_period_expire_date ON imed.stock_mgnt_period(expire_date);
CREATE INDEX stk_mgnt_period_fix_abc_id ON imed.stock_mgnt_period(fix_abc_id);
CREATE INDEX stk_mgnt_period_fix_ven_id ON imed.stock_mgnt_period(fix_ven_id);
CREATE INDEX stk_mgnt_period_item_id ON imed.stock_mgnt_period(item_id);
CREATE INDEX stk_mgnt_period_lot_number ON imed.stock_mgnt_period(lot_number);
CREATE INDEX stk_mgnt_period_manufact_id ON imed.stock_mgnt_period(manufacturer_id);
CREATE INDEX stk_mgnt_period_produce_date ON imed.stock_mgnt_period(produce_date);
CREATE INDEX stk_mgnt_period_receive_date ON imed.stock_mgnt_period(receive_date);
CREATE INDEX stk_mgnt_period_stock_id ON imed.stock_mgnt_period(stock_id);

CREATE TABLE imed.stock_order (
	stock_order_id VARCHAR(255) NOT NULL,
	order_from_stock_id VARCHAR(255),
	distributor_id VARCHAR(255),
	manufacturer_id VARCHAR(255),
	plan_code VARCHAR(255),
	unit_code VARCHAR(255),
	fund_code VARCHAR(255),
	fund_name VARCHAR(255),
	item_id VARCHAR(255),
	small_unit_id VARCHAR(255),
	unit_rate VARCHAR(255),
	mid_unit_rate VARCHAR(255),
	big_unit_id VARCHAR(255),
	order_unit_id VARCHAR(255),
	payer VARCHAR(255),
	payer_unit VARCHAR(255),
	payer_subunit VARCHAR(255),
	cash_limit VARCHAR(255),
	chairman_eid VARCHAR(255),
	chairman_position VARCHAR(255),
	commitee1_eid VARCHAR(255),
	commitee1_position VARCHAR(255),
	commitee2_eid VARCHAR(255),
	commitee2_position VARCHAR(255),
	commitee3_eid VARCHAR(255),
	commitee3_position VARCHAR(255),
	purchasing_offer_code VARCHAR(255),
	purchasing_offer_number VARCHAR(255),
	purchasing_offer_title VARCHAR(255),
	purchasing_offer_to_officer VARCHAR(255),
	purchasing_offer_unit VARCHAR(255),
	purchasing_offer_team VARCHAR(255),
	purchasing_offer_person VARCHAR(255),
	purchasing_offer_cause VARCHAR(255),
	purchasing_offer_quantity VARCHAR(255),
	purchasing_offer_price_cost VARCHAR(255),
	purchasing_offer_price VARCHAR(255),
	purchasing_offer_price_no_vat VARCHAR(255),
	purchasing_offer_discount VARCHAR(255),
	purchasing_offer_discount_percent VARCHAR(255),
	purchasing_offer_vat VARCHAR(255),
	purchasing_offer_eid VARCHAR(255),
	purchasing_offer_date VARCHAR(255),
	purchasing_offer_time VARCHAR(255),
	needs_order_date VARCHAR(255),
	fix_stock_order_status_id VARCHAR(255),
	free_quantity VARCHAR(255),
	fiscal_year VARCHAR(255),
	fix_stock_pay_method_id VARCHAR(255),
	credit_on_pay VARCHAR(255),
	fix_tax_type_id VARCHAR(255),
	purchasing_offer_price_cost_no_vat VARCHAR(255),
	comments VARCHAR(255)
);
ALTER TABLE imed.stock_order ADD CONSTRAINT stock_order_pkey PRIMARY KEY (stock_order_id);
CREATE INDEX idx_stk_order_distributor_id ON imed.stock_order(distributor_id);
CREATE INDEX idx_stk_order_from_stock_id ON imed.stock_order(order_from_stock_id);
CREATE INDEX idx_stk_order_item_id ON imed.stock_order(item_id);
CREATE INDEX idx_stk_order_status ON imed.stock_order(fix_stock_order_status_id);
CREATE INDEX idx_stk_poffer_date ON imed.stock_order(purchasing_offer_date);
CREATE INDEX idx_stk_poffer_number ON imed.stock_order(purchasing_offer_number);
CREATE INDEX idx_stk_poffer_time ON imed.stock_order(purchasing_offer_time);

CREATE TABLE imed.stock_place (
	stock_place_id VARCHAR(255) NOT NULL,
	place VARCHAR(255)
);
ALTER TABLE imed.stock_place ADD CONSTRAINT stock_place_pkey PRIMARY KEY (stock_place_id);

CREATE TABLE imed.stock_request (
	stock_request_id VARCHAR(255) NOT NULL,
	disp_stock_id VARCHAR(255),
	recv_stock_id VARCHAR(255),
	req_number VARCHAR(255),
	req_spid VARCHAR(255),
	req_eid VARCHAR(255),
	cancel_eid VARCHAR(255),
	req_date VARCHAR(255),
	req_time VARCHAR(255),
	cancel_date VARCHAR(255),
	cancel_time VARCHAR(255),
	item_id VARCHAR(255),
	req_quantity VARCHAR(255),
	fix_stock_request_status_id VARCHAR(255),
	fix_stock_request_method_id VARCHAR(255),
	is_request_from_dispense VARCHAR(255),
	request_date_from VARCHAR(255),
	request_time_from VARCHAR(255),
	request_date_to VARCHAR(255),
	request_time_to VARCHAR(255),
	req_unit_id VARCHAR(255),
	req_unit_price VARCHAR(255)
);
ALTER TABLE imed.stock_request ADD CONSTRAINT stock_request_pkey PRIMARY KEY (stock_request_id);
CREATE INDEX stk_req_date ON imed.stock_request(req_date);
CREATE INDEX stk_req_eid ON imed.stock_request(req_eid);
CREATE INDEX stk_req_from_stock_id ON imed.stock_request(disp_stock_id);
CREATE INDEX stk_req_item_id ON imed.stock_request(item_id);
CREATE INDEX stk_req_number ON imed.stock_request(req_number);
CREATE INDEX stk_req_status ON imed.stock_request(fix_stock_request_status_id);
CREATE INDEX stk_req_time ON imed.stock_request(req_time);
CREATE INDEX stk_req_to_stock_id ON imed.stock_request(recv_stock_id);

CREATE TABLE imed.stock_return (
	stock_return_id VARCHAR(255) NOT NULL,
	disp_stock_id VARCHAR(255),
	recv_stock_id VARCHAR(255),
	return_number VARCHAR(255),
	return_eid VARCHAR(255),
	return_date VARCHAR(255),
	return_time VARCHAR(255),
	item_id VARCHAR(255),
	lot_number VARCHAR(255),
	return_quantity VARCHAR(255),
	fix_stock_return_status_id VARCHAR(255)
);
ALTER TABLE imed.stock_return ADD CONSTRAINT stock_return_pkey PRIMARY KEY (stock_return_id);
CREATE INDEX stk_ret_date ON imed.stock_return(return_date);
CREATE INDEX stk_ret_eid ON imed.stock_return(return_eid);
CREATE INDEX stk_ret_from_stock_id ON imed.stock_return(disp_stock_id);
CREATE INDEX stk_ret_item_id ON imed.stock_return(item_id);
CREATE INDEX stk_ret_number ON imed.stock_return(return_number);
CREATE INDEX stk_ret_status ON imed.stock_return(fix_stock_return_status_id);
CREATE INDEX stk_ret_time ON imed.stock_return(return_time);
CREATE INDEX stk_ret_to_stock_id ON imed.stock_return(recv_stock_id);

CREATE TABLE imed.stock_team_work (
	stock_team_work_id VARCHAR(255) NOT NULL,
	team_work_name VARCHAR(255)
);
ALTER TABLE imed.stock_team_work ADD CONSTRAINT team_work_pkey PRIMARY KEY (stock_team_work_id);

CREATE TABLE imed.sub_drawing_details (
	sub_drawing_details_id VARCHAR(255) NOT NULL,
	drawing_details_id VARCHAR(255),
	sub_x_axis VARCHAR(255),
	sub_y_axis VARCHAR(255)
);
ALTER TABLE imed.sub_drawing_details ADD CONSTRAINT sub_drawing_details_pkey PRIMARY KEY (sub_drawing_details_id);

CREATE TABLE imed.sum_cost (
	sum_cost_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	visit_id VARCHAR(255),
	sum_cost_number VARCHAR(255),
	created_eid VARCHAR(255),
	created_date VARCHAR(255),
	created_time VARCHAR(255),
	last_print_eid VARCHAR(255),
	last_print_date VARCHAR(255),
	last_print_time VARCHAR(255),
	max_print_times VARCHAR(255),
	begin_date VARCHAR(255),
	begin_time VARCHAR(255),
	end_date VARCHAR(255),
	end_time VARCHAR(255),
	num_date VARCHAR(255),
	cost VARCHAR(255),
	discount VARCHAR(255),
	paid VARCHAR(255),
	deposit VARCHAR(255),
	invoice_paid VARCHAR(255),
	general_paid VARCHAR(255),
	remain_cost VARCHAR(255)
);
ALTER TABLE imed.sum_cost ADD CONSTRAINT sum_cost_pkey PRIMARY KEY (sum_cost_id);
CREATE INDEX sc_patient_id ON imed.sum_cost(patient_id);
CREATE INDEX sc_receipt_number ON imed.sum_cost(sum_cost_number);
CREATE INDEX sc_visit_id ON imed.sum_cost(visit_id);

CREATE TABLE imed.sum_cost_billing_group (
	sum_cost_billing_group_id VARCHAR(255) NOT NULL,
	sum_cost_id VARCHAR(255),
	base_billing_group_id VARCHAR(255),
	patient_id VARCHAR(255),
	visit_id VARCHAR(255),
	cost VARCHAR(255),
	discount VARCHAR(255),
	paid VARCHAR(255)
);
ALTER TABLE imed.sum_cost_billing_group ADD CONSTRAINT sum_cost_billing_group_pkey PRIMARY KEY (sum_cost_billing_group_id);
CREATE INDEX sc_bill_grp_deid ON imed.sum_cost_billing_group(sum_cost_id);
CREATE INDEX sc_bill_grp_grpid ON imed.sum_cost_billing_group(base_billing_group_id);
CREATE INDEX sc_bill_grp_pat ON imed.sum_cost_billing_group(patient_id);
CREATE INDEX sc_bill_grp_visit ON imed.sum_cost_billing_group(visit_id);

CREATE TABLE imed.temp_item (
	item_id VARCHAR(255),
	item_code VARCHAR(255),
	common_name VARCHAR(255),
	drug_generic_name VARCHAR(255),
	drug_group_name VARCHAR(255),
	drug_trade_name VARCHAR(255),
	nick_name VARCHAR(255),
	note VARCHAR(255),
	fix_item_type_id VARCHAR(255),
	fix_chrgitem_id VARCHAR(255),
	base_category_group_id VARCHAR(255),
	base_billing_group_opd_id VARCHAR(255),
	base_billing_group_ipd_id VARCHAR(255),
	base_billing_group_home_id VARCHAR(255),
	base_invoice_group_id VARCHAR(255),
	base_unit_id VARCHAR(255),
	pack_size VARCHAR(255),
	pack_unit_id VARCHAR(255),
	base_lab_type_id VARCHAR(255),
	fix_lab_result_type_id VARCHAR(255),
	base_specimen_id VARCHAR(255),
	fix_report_type_id VARCHAR(255),
	description TEXT,
	description_en TEXT,
	base_drug_instruction_id VARCHAR(255),
	base_dose_unit_id VARCHAR(255),
	caution TEXT,
	caution_en TEXT,
	base_drug_type_id VARCHAR(255),
	base_drug_group_id VARCHAR(255),
	base_drug_format_id VARCHAR(255),
	film_charge VARCHAR(255),
	base_xray_type_id VARCHAR(255),
	hospital_item VARCHAR(255),
	stock_critical VARCHAR(255),
	mid_price1 VARCHAR(255),
	mid_price1_note VARCHAR(255),
	mid_price2 VARCHAR(255),
	mid_price2_note VARCHAR(255),
	mid_price3 VARCHAR(255),
	mid_price3_note VARCHAR(255),
	unit_price_cost VARCHAR(255),
	ned_group VARCHAR(255),
	nled_group_id VARCHAR(255),
	reg_no VARCHAR(255),
	gpo_price1 VARCHAR(255),
	gpo_price2 VARCHAR(255),
	gpo_price3 VARCHAR(255),
	fix_fund_enable_id VARCHAR(255),
	fund_enable_value VARCHAR(255),
	base_dent_operation_id VARCHAR(255),
	dental_color_code VARCHAR(255),
	fix_set_type_id VARCHAR(255),
	fix_sticker_type_id VARCHAR(255),
	print_report VARCHAR(255),
	report_price VARCHAR(255),
	edit_price VARCHAR(255),
	use_stock VARCHAR(255),
	doctor_share VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255),
	active VARCHAR(255),
	secret_status VARCHAR(255)
);

CREATE TABLE imed.template_appointment (
	template_appointment_id VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	basic_advice VARCHAR(255),
	note VARCHAR(255),
	active VARCHAR(255)
);
ALTER TABLE imed.template_appointment ADD CONSTRAINT template_appointment_pkey PRIMARY KEY (template_appointment_id);
CREATE INDEX template_appointment_active ON imed.template_appointment(active);

CREATE TABLE imed.template_appointment_detail (
	template_appointment_detail_id VARCHAR(255) NOT NULL,
	template_appointment_id VARCHAR(255),
	continue_note VARCHAR(255),
	length_of_day VARCHAR(255),
	priority VARCHAR(255)
);
ALTER TABLE imed.template_appointment_detail ADD CONSTRAINT template_appointment_detail_pkey PRIMARY KEY (template_appointment_detail_id);
CREATE INDEX template_appointment_detail_template_id ON imed.template_appointment_detail(template_appointment_id);

CREATE TABLE imed.template_appointment_order (
	template_appointment_order_id VARCHAR(255) NOT NULL,
	template_appointment_detail_id VARCHAR(255),
	item_id VARCHAR(255),
	fix_item_type_id VARCHAR(255),
	base_drug_usage_code VARCHAR(255),
	instruction_text_line1 VARCHAR(255),
	instruction_text_line2 VARCHAR(255),
	instruction_text_line3 VARCHAR(255),
	quantity VARCHAR(255),
	base_unit_id VARCHAR(255),
	description VARCHAR(255),
	caution VARCHAR(255),
	priority VARCHAR(255)
);
ALTER TABLE imed.template_appointment_order ADD CONSTRAINT template_appointment_order_pkey PRIMARY KEY (template_appointment_order_id);
CREATE INDEX template_appointment_order_detail_id ON imed.template_appointment_order(template_appointment_detail_id);
CREATE INDEX template_appointment_order_item_id ON imed.template_appointment_order(item_id);
CREATE INDEX template_appointment_order_priority ON imed.template_appointment_order(priority);

CREATE TABLE imed.template_dent_operation (
	template_dent_operation_id VARCHAR(255) NOT NULL,
	template_code VARCHAR(255),
	description TEXT,
	base_dent_operation_id VARCHAR(255),
	employee_id VARCHAR(255)
);
ALTER TABLE imed.template_dent_operation ADD CONSTRAINT template_dent_operation_pkey PRIMARY KEY (template_dent_operation_id);

CREATE TABLE imed.template_discount (
	template_discount_id VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	base_billing_group_id TEXT,
	discount_percent TEXT
);
ALTER TABLE imed.template_discount ADD CONSTRAINT template_discount_pkey PRIMARY KEY (template_discount_id);

CREATE TABLE imed.template_drug_usage (
	template_drug_usage_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.template_drug_usage ADD CONSTRAINT template_drug_usage_pkey PRIMARY KEY (template_drug_usage_id);

CREATE TABLE imed.template_item_set (
	template_item_set_id VARCHAR(255) NOT NULL,
	item_set_id VARCHAR(255),
	employee_id VARCHAR(255),
	base_service_point_id VARCHAR(255)
);
ALTER TABLE imed.template_item_set ADD CONSTRAINT template_item_set_pkey PRIMARY KEY (template_item_set_id);
CREATE INDEX template_item_set_employee ON imed.template_item_set(employee_id);
CREATE INDEX template_item_set_service_point ON imed.template_item_set(base_service_point_id);

CREATE TABLE imed.template_lab_choice (
	template_lab_choice_id VARCHAR(254) NOT NULL,
	template_lab_test_id VARCHAR(254),
	description VARCHAR(254),
	abnormal VARCHAR(254)
);
ALTER TABLE imed.template_lab_choice ADD CONSTRAINT template_lab_choice_pkey PRIMARY KEY (template_lab_choice_id);
CREATE INDEX template_lab_choice_test ON imed.template_lab_choice(template_lab_test_id);

CREATE TABLE imed.template_lab_item (
	template_lab_item_id VARCHAR(254) NOT NULL,
	template_lab_test_id VARCHAR(254),
	item_id VARCHAR(254),
	result_position VARCHAR(254)
);
ALTER TABLE imed.template_lab_item ADD CONSTRAINT template_lab_item_pkey PRIMARY KEY (template_lab_item_id);
CREATE INDEX template_lab_item_item_id ON imed.template_lab_item(item_id);

CREATE TABLE imed.template_lab_test (
	template_lab_test_id VARCHAR(254) NOT NULL,
	name VARCHAR(254),
	normal_value_max VARCHAR(254),
	normal_value_min VARCHAR(254),
	unit_text VARCHAR(254),
	fix_lab_test_type_id VARCHAR(254)
);
ALTER TABLE imed.template_lab_test ADD CONSTRAINT template_lab_test_pkey PRIMARY KEY (template_lab_test_id);

CREATE TABLE imed.template_personal_illness (
	template_personal_illness_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.template_personal_illness ADD CONSTRAINT template_personal_illness_pkey PRIMARY KEY (template_personal_illness_id);
CREATE INDEX template_pi_description ON imed.template_personal_illness(description);

CREATE TABLE imed.template_phyex (
	template_phyex_id VARCHAR(255) NOT NULL,
	description VARCHAR(255),
	show_default VARCHAR(255)
);
ALTER TABLE imed.template_phyex ADD CONSTRAINT template_phyex_pkey PRIMARY KEY (template_phyex_id);

CREATE TABLE imed.template_phyex_default (
	template_phyex_default_id VARCHAR(255) NOT NULL,
	template_phyex_id VARCHAR(255),
	employee_id VARCHAR(255),
	base_clinic_id VARCHAR(255)
);
ALTER TABLE imed.template_phyex_default ADD CONSTRAINT template_phyex_default_pkey PRIMARY KEY (template_phyex_default_id);
CREATE INDEX tp_default_clinic_id ON imed.template_phyex_default(base_clinic_id);
CREATE INDEX tp_default_employee_id ON imed.template_phyex_default(employee_id);

CREATE TABLE imed.template_phyex_detail (
	template_phyex_detail_id VARCHAR(255) NOT NULL,
	template_phyex_id VARCHAR(255),
	base_organic_id VARCHAR(255),
	organ_position VARCHAR(255)
);
ALTER TABLE imed.template_phyex_detail ADD CONSTRAINT template_phyex_detail_pkey PRIMARY KEY (template_phyex_detail_id);

CREATE TABLE imed.template_risk_factor (
	template_risk_factor_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.template_risk_factor ADD CONSTRAINT template_risk_factor_pkey PRIMARY KEY (template_risk_factor_id);
CREATE INDEX template_rf_description ON imed.template_risk_factor(description);

CREATE TABLE imed.template_symptom (
	template_symptom_id VARCHAR(255) NOT NULL,
	template_symptom_group_id VARCHAR(255),
	description VARCHAR(255)
);
ALTER TABLE imed.template_symptom ADD CONSTRAINT template_symptom_pkey PRIMARY KEY (template_symptom_id);
CREATE INDEX ts_symptom_group_id ON imed.template_symptom(template_symptom_group_id);

CREATE TABLE imed.template_symptom_default (
	template_symptom_default_id VARCHAR(255) NOT NULL,
	template_symptom_group_id VARCHAR(255),
	base_clinic_id VARCHAR(255),
	is_shortcut VARCHAR(255)
);
ALTER TABLE imed.template_symptom_default ADD CONSTRAINT template_symptom_default_pkey PRIMARY KEY (template_symptom_default_id);
CREATE INDEX ts_default_clinic_id ON imed.template_symptom_default(base_clinic_id);
CREATE INDEX ts_default_group_id ON imed.template_symptom_default(template_symptom_group_id);

CREATE TABLE imed.template_symptom_group (
	template_symptom_group_id VARCHAR(255) NOT NULL,
	description VARCHAR(255)
);
ALTER TABLE imed.template_symptom_group ADD CONSTRAINT template_symptom_group_pkey PRIMARY KEY (template_symptom_group_id);

CREATE TABLE imed.template_xray_result (
	template_xray_result_id VARCHAR(254) NOT NULL,
	code VARCHAR(254),
	employee_id VARCHAR(254),
	result TEXT,
	impression TEXT
);
ALTER TABLE imed.template_xray_result ADD CONSTRAINT template_xray_result_pkey PRIMARY KEY (template_xray_result_id);
CREATE INDEX template_xray_result_code ON imed.template_xray_result(code);

CREATE TABLE imed.time_stamp_details (
	time_stamp_details_id VARCHAR(255) NOT NULL,
	base_time_stamp_id VARCHAR(255),
	fix_service_point_group_id VARCHAR(255),
	begin_stamp_time VARCHAR(255),
	finish_stamp_time VARCHAR(255)
);
ALTER TABLE imed.time_stamp_details ADD CONSTRAINT time_stamp_details_pkey PRIMARY KEY (time_stamp_details_id);
CREATE INDEX idx_timestampdetail_basetimestampid ON imed.time_stamp_details(base_time_stamp_id);

CREATE TABLE imed.visit (
	visit_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	old_patient_id VARCHAR(255),
	hn VARCHAR(255),
	vn VARCHAR(255),
	an VARCHAR(255),
	visit_eid VARCHAR(255),
	visit_spid VARCHAR(255),
	visit_date VARCHAR(255),
	visit_time VARCHAR(255),
	base_tariff_id VARCHAR(255),
	special_care_note VARCHAR(255),
	fix_visit_type_id VARCHAR(255),
	pregnancy VARCHAR(255),
	pregnancy_interval VARCHAR(255),
	fix_pregnancy_risk_category_id VARCHAR(255),
	pregnancy_risk_comment VARCHAR(255),
	drug_in_lactation VARCHAR(255),
	financial_discharge VARCHAR(255),
	financial_discharge_eid VARCHAR(255),
	financial_discharge_date VARCHAR(255),
	financial_discharge_time VARCHAR(255),
	reverse_discharge_visit VARCHAR(255),
	doctor_discharge VARCHAR(255),
	doctor_discharge_date VARCHAR(255),
	doctor_discharge_time VARCHAR(255),
	opd_card_no VARCHAR(255),
	fix_new_in_year_id VARCHAR(255),
	new_patient VARCHAR(255),
	patient_age VARCHAR(255),
	ignore_drug_allergy VARCHAR(255),
	from_appointment VARCHAR(255),
	admit_times VARCHAR(255),
	proxy_status VARCHAR(255),
	proxy_date VARCHAR(255),
	proxy_time VARCHAR(255),
	last_location_spid VARCHAR(255),
	location_spid VARCHAR(255),
	last_operate_eid VARCHAR(255),
	operate_eid VARCHAR(255),
	assign_lab_status VARCHAR(255),
	assign_xray_status VARCHAR(255),
	assign_pharmacy_status VARCHAR(255),
	base_patient_type_id VARCHAR(255),
	max_calculate_times VARCHAR(255),
	last_calculate_date VARCHAR(255),
	last_calculate_time VARCHAR(255),
	move_visit_eid VARCHAR(255),
	move_visit_date VARCHAR(255),
	move_visit_time VARCHAR(255),
	is_no_pay VARCHAR(255),
	patient_file_borrow_id VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255),
	active VARCHAR(255),
	fix_coming_type VARCHAR(255),
	fix_emergency_type_id VARCHAR(255)
);
ALTER TABLE imed.visit ADD CONSTRAINT visit_pkey PRIMARY KEY (visit_id);
CREATE INDEX stk_death_visit_id ON imed.visit(visit_id);
CREATE INDEX visit_an ON imed.visit(an);
CREATE INDEX visit_assign_lab ON imed.visit(assign_lab_status);
CREATE INDEX visit_assign_xray ON imed.visit(assign_xray_status);
CREATE INDEX visit_doctor_discharge ON imed.visit(doctor_discharge);
CREATE INDEX visit_fdiscd ON imed.visit(financial_discharge_date);
CREATE INDEX visit_fdisct ON imed.visit(financial_discharge_time);
CREATE INDEX visit_financial_discharge ON imed.visit(financial_discharge);
CREATE INDEX visit_hn ON imed.visit(hn);
CREATE INDEX visit_old_pat_id ON imed.visit(old_patient_id);
CREATE INDEX visit_patient_id ON imed.visit(patient_id);
CREATE INDEX visit_proxy_status ON imed.visit(proxy_status);
CREATE INDEX visit_visit_date ON imed.visit(visit_date);
CREATE INDEX visit_visit_time ON imed.visit(visit_time);
CREATE INDEX visit_vn ON imed.visit(vn);

CREATE TABLE imed.visit_clinic (
	visit_clinic_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	base_clinic_id VARCHAR(255)
);
ALTER TABLE imed.visit_clinic ADD CONSTRAINT visit_clinic_pkey PRIMARY KEY (visit_clinic_id);
CREATE INDEX visit_clinic_visit_id ON imed.visit_clinic(visit_id);

CREATE TABLE imed.visit_deliver (
	visit_deliver_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	deliver_name VARCHAR(255),
	fix_gender_id VARCHAR(255),
	home_id VARCHAR(255),
	road VARCHAR(255),
	village VARCHAR(255),
	fix_tambol_id VARCHAR(255),
	fix_amphur_id VARCHAR(255),
	fix_changwat_id VARCHAR(255),
	postcode VARCHAR(255),
	telephone VARCHAR(255),
	note TEXT
);
ALTER TABLE imed.visit_deliver ADD CONSTRAINT visit_deliver_pkey PRIMARY KEY (visit_deliver_id);
CREATE INDEX visit_deliver_visit_id ON imed.visit_deliver(visit_id);

CREATE TABLE imed.visit_payment (
	visit_payment_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	plan_id VARCHAR(255),
	plan_code VARCHAR(255),
	fix_contract_type_id VARCHAR(255),
	base_plan_group_id VARCHAR(255),
	card_id VARCHAR(255),
	inspire_date VARCHAR(255),
	expire_date VARCHAR(255),
	main_hospital_code VARCHAR(255),
	sub_hospital_code VARCHAR(255),
	subtype VARCHAR(255),
	priority VARCHAR(255),
	credit_limit VARCHAR(255),
	fix_credit_type_id VARCHAR(255),
	note VARCHAR(255),
	visit_credit_limit VARCHAR(255),
	current_credit_limit VARCHAR(255),
	start_payment_id VARCHAR(255),
	cont_credit_active VARCHAR(255),
	plan_external_id VARCHAR(255),
	employee_welfare_eid VARCHAR(255),
	agent_name VARCHAR(255),
	agent_lastname VARCHAR(255)
);
ALTER TABLE imed.visit_payment ADD CONSTRAINT visit_payment_pkey PRIMARY KEY (visit_payment_id);
CREATE INDEX visit_payment_cont_active ON imed.visit_payment(cont_credit_active);
CREATE INDEX visit_payment_credit_type ON imed.visit_payment(fix_credit_type_id);
CREATE INDEX visit_payment_plan_code ON imed.visit_payment(plan_code);
CREATE INDEX visit_payment_plan_group ON imed.visit_payment(base_plan_group_id);
CREATE INDEX visit_payment_plan_id ON imed.visit_payment(plan_id);
CREATE INDEX visit_payment_priority ON imed.visit_payment(priority);
CREATE INDEX visit_payment_start_payment ON imed.visit_payment(start_payment_id);
CREATE INDEX visit_payment_visit_id ON imed.visit_payment(visit_id);

CREATE TABLE imed.visit_queue (
	visit_queue_id VARCHAR(255) NOT NULL,
	patient_id VARCHAR(255),
	visit_id VARCHAR(255),
	fix_visit_type_id VARCHAR(255),
	next_location_spid VARCHAR(255),
	next_operate_eid VARCHAR(255),
	next_location_date VARCHAR(255),
	next_location_time VARCHAR(255),
	assign_location_spid VARCHAR(255),
	assign_operate_eid VARCHAR(255),
	sort_queue_number VARCHAR(255),
	queue_number VARCHAR(255),
	queue_status VARCHAR(255),
	next_department_id VARCHAR(255),
	next_clinic_id VARCHAR(255)
);
ALTER TABLE imed.visit_queue ADD CONSTRAINT visit_queue_pkey PRIMARY KEY (visit_queue_id);
CREATE INDEX visit_q_assign_loca_spid ON imed.visit_queue(assign_location_spid);
CREATE INDEX visit_q_assign_oper_eid ON imed.visit_queue(assign_operate_eid);
CREATE INDEX visit_q_next_clinic ON imed.visit_queue(next_department_id);
CREATE INDEX visit_q_next_location_spid ON imed.visit_queue(next_location_spid);
CREATE INDEX visit_q_next_operate_eid ON imed.visit_queue(next_operate_eid);
CREATE INDEX visit_q_patient_id ON imed.visit_queue(patient_id);
CREATE INDEX visit_q_queue_status ON imed.visit_queue(queue_status);
CREATE INDEX visit_q_visit_id ON imed.visit_queue(visit_id);
CREATE INDEX visit_q_visit_type ON imed.visit_queue(fix_visit_type_id);

CREATE TABLE imed.visit_special_card (
	visit_special_card_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	base_disc_special_card_id VARCHAR(255),
	card_id VARCHAR(255),
	inspire_date VARCHAR(255),
	expire_date VARCHAR(255),
	note TEXT
);
ALTER TABLE imed.visit_special_card ADD CONSTRAINT visit_special_card_pkey PRIMARY KEY (visit_special_card_id);
CREATE INDEX idx_visit_spccard_disc_spccard_id ON imed.visit_special_card(base_disc_special_card_id);
CREATE INDEX special_card_visit_id ON imed.visit_special_card(visit_id);

CREATE TABLE imed.visit_waiting_reason (
	visit_waiting_reason_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	employee_id VARCHAR(255),
	base_long_waiting_reason_id VARCHAR(255)
);
ALTER TABLE imed.visit_waiting_reason ADD CONSTRAINT visit_waiting_reason_pkey PRIMARY KEY (visit_waiting_reason_id);
CREATE INDEX visit_waiting_reason_employee ON imed.visit_waiting_reason(employee_id);
CREATE INDEX visit_waiting_reason_visit ON imed.visit_waiting_reason(visit_id);

CREATE TABLE imed.vital_sign_extend (
	vital_sign_extend_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	main_symptom TEXT,
	current_illness TEXT,
	doctor_eid VARCHAR(255),
	examine_date VARCHAR(255),
	examine_time VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255),
	attending_physician_id VARCHAR(255)
);
ALTER TABLE imed.vital_sign_extend ADD CONSTRAINT vital_sign_extend_pkey PRIMARY KEY (vital_sign_extend_id);
CREATE INDEX vital_sign_extend_visit_id ON imed.vital_sign_extend(visit_id);

CREATE TABLE imed.vital_sign_ipd (
	vital_sign_ipd_id VARCHAR(254) NOT NULL,
	admit_id VARCHAR(254),
	pressure_max VARCHAR(254),
	pressure_min VARCHAR(254),
	temperature VARCHAR(254),
	pulse VARCHAR(254),
	respiration VARCHAR(254),
	note TEXT,
	abnormal_pressure VARCHAR(254),
	abnormal_pulse VARCHAR(254),
	measure_eid VARCHAR(254),
	measure_date VARCHAR(254),
	measure_time VARCHAR(254),
	modify_eid VARCHAR(254),
	modify_date VARCHAR(254),
	modify_time VARCHAR(254)
);
ALTER TABLE imed.vital_sign_ipd ADD CONSTRAINT vital_sign_ipd_pkey PRIMARY KEY (vital_sign_ipd_id);
CREATE INDEX vital_sign_ipd_admit_id ON imed.vital_sign_ipd(admit_id);

CREATE TABLE imed.vital_sign_opd (
	vital_sign_opd_id VARCHAR(255) NOT NULL,
	visit_id VARCHAR(255),
	weight VARCHAR(255),
	height VARCHAR(255),
	pressure_max VARCHAR(255),
	pressure_min VARCHAR(255),
	temperature VARCHAR(255),
	pulse VARCHAR(255),
	respiration VARCHAR(255),
	bmi VARCHAR(255),
	abnormal_pressure VARCHAR(255),
	abnormal_pulse VARCHAR(255),
	measure_eid VARCHAR(255),
	measure_date VARCHAR(255),
	measure_time VARCHAR(255),
	modify_eid VARCHAR(255),
	modify_date VARCHAR(255),
	modify_time VARCHAR(255),
	sat_o2 VARCHAR(255),
	measure_spid VARCHAR(255)
);
ALTER TABLE imed.vital_sign_opd ADD CONSTRAINT vital_sign_opd_pkey PRIMARY KEY (vital_sign_opd_id);
CREATE INDEX vital_sign_opd_visit_id ON imed.vital_sign_opd(visit_id);

CREATE TABLE imed.xray_execute_detail (
	xray_execute_detail_id VARCHAR(254) NOT NULL,
	assign_xray_id VARCHAR(254),
	order_item_id VARCHAR(254),
	execute_eid VARCHAR(254),
	execute_date VARCHAR(254),
	execute_time VARCHAR(254),
	xray_point VARCHAR(254),
	note TEXT,
	film_charge VARCHAR(254),
	modify_eid VARCHAR(254),
	modify_date VARCHAR(254),
	modify_time VARCHAR(254)
);
ALTER TABLE imed.xray_execute_detail ADD CONSTRAINT xray_execute_detail_pkey PRIMARY KEY (xray_execute_detail_id);
CREATE INDEX index_xray_execute_detail_assign_id ON imed.xray_execute_detail(assign_xray_id);
CREATE INDEX index_xray_execute_detail_order_item_id ON imed.xray_execute_detail(order_item_id);

CREATE TABLE imed.xray_film (
	xray_film_id VARCHAR(254) NOT NULL,
	xray_execute_detail_id VARCHAR(254),
	patient_id VARCHAR(254),
	base_xray_film_id VARCHAR(254),
	fix_film_status_id VARCHAR(254),
	unit_price_sale VARCHAR(254),
	base_bad_film_reason_id VARCHAR(255)
);
ALTER TABLE imed.xray_film ADD CONSTRAINT xray_film_pkey PRIMARY KEY (xray_film_id);
CREATE INDEX index_xray_film_execute_detail ON imed.xray_film(xray_execute_detail_id);
CREATE INDEX index_xray_film_status ON imed.xray_film(fix_film_status_id);

CREATE TABLE imed.xray_result (
	xray_result_id VARCHAR(254) NOT NULL,
	assign_xray_id VARCHAR(254),
	visit_id VARCHAR(254),
	patient_id VARCHAR(254),
	order_item_id VARCHAR(254),
	item_id VARCHAR(254),
	fix_xray_type_id VARCHAR(254),
	times_reported VARCHAR(254),
	reported VARCHAR(254),
	report_eid VARCHAR(254),
	modify_eid VARCHAR(254),
	modify_spid VARCHAR(254),
	modify_date VARCHAR(254),
	modify_time VARCHAR(254),
	title TEXT,
	result TEXT
);
ALTER TABLE imed.xray_result ADD CONSTRAINT xray_result_pkey PRIMARY KEY (xray_result_id);
CREATE INDEX index_xray_reported ON imed.xray_result(reported);
CREATE INDEX index_xray_result_assign_id ON imed.xray_result(assign_xray_id);
CREATE INDEX index_xray_result_item_id ON imed.xray_result(item_id);
CREATE INDEX index_xray_result_order_item_id ON imed.xray_result(order_item_id);
CREATE INDEX index_xray_result_visit_id ON imed.xray_result(visit_id);
CREATE INDEX xray_result_patient_id ON imed.xray_result(patient_id);
