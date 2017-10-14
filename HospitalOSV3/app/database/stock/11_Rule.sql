------------------------------------------- TABLE b_employee BEGIN -------------------------------------------------
CREATE RULE b_employee_on_ins AS ON INSERT TO b_employee 
DO 
	INSERT INTO imed.employee(employee_id, password, firstname, lastname, base_service_point_id)
	VALUES(NEW.employee_login, NEW.employee_password, NEW.employee_firstname, NEW.employee_lastname, NEW.b_service_point_id);

CREATE RULE b_employee_on_ins_for_role AS ON INSERT TO b_employee 
DO 
	INSERT INTO imed.employee_role(employee_role_id, employee_id, admin_manage_item_auth, admin_module_auth, admin_stock_auth, admin_stock_manage_auth, admin_stock_report_auth)
	VALUES(NEW.employee_login, NEW.employee_login, '11111111111', '00001000000000', '1111111111', '11111', '11111111111111111111111111');

CREATE RULE b_employee_on_upd AS ON UPDATE TO b_employee 
DO 
	UPDATE imed.employee 
	SET	employee_id = NEW.employee_login,
		password = NEW.employee_password,
		firstname = NEW.employee_firstname,
		lastname = NEW.employee_lastname,
		base_service_point_id = NEW.b_service_point_id
	WHERE employee_id = OLD.employee_login;

CREATE RULE b_employee_on_del AS ON DELETE TO b_employee 
DO 
	DELETE FROM imed.employee WHERE employee_id = OLD.b_employee_id;
------------------------------------------- TABLE b_employee END -------------------------------------------------

------------------------------------------- TABLE b_item BEGIN -------------------------------------------------
--CREATE RULE b_item_on_ins AS ON INSERT TO b_item 
--DO 
--	INSERT INTO imed.item(item_id, item_code, common_name, active, nick_name) 
--	VALUES (NEW.b_item_id, NEW.item_number, NEW.item_common_name, NEW.item_active, NEW.item_nick_name);
CREATE RULE b_item_on_ins AS ON INSERT TO b_item 
DO 
	INSERT INTO imed.item(item_id, item_code, common_name, active, nick_name,fix_item_type_id,base_unit_id,hospital_item,pack_size,stock_critical,fix_fund_enable_id,print_report) 
	VALUES (NEW.b_item_id, NEW.item_number, NEW.item_common_name, NEW.item_active, NEW.item_nick_name, 
	    (select  case when f_item_group_id  = '1' 
	     then '0'
	     else f_item_group_id
	     end as f_item_group
             from b_item inner join b_item_subgroup on b_item.b_item_subgroup_id = b_item_subgroup.b_item_subgroup_id 
             where b_item.b_item_id =  NEW.b_item_id),
             (select 
		case when b_item_drug_uom.item_drug_uom_number <> ''
		and b_item_drug_uom.item_drug_uom_number is not null
		then b_item_drug_uom.item_drug_uom_number
		else ''
		end as item_drug_uom
		from b_item left join b_item_drug on b_item.b_item_id = b_item_drug.b_item_id
		left join b_item_drug_uom on b_item_drug.item_drug_purch_uom = b_item_drug_uom.b_item_drug_uom_id
		where b_item.b_item_id =  NEW.b_item_id)
		,'1','1','0','1','1');
CREATE RULE b_item_on_upd AS ON UPDATE TO b_item 
DO 
	UPDATE imed.item 
	SET	item_code = NEW.item_number, 
		common_name = NEW.item_common_name, 
		active = NEW.item_active, 
		nick_name = NEW.item_nick_name,
		fix_item_type_id =  (select  case when f_item_group_id  = '1' 
					     then '0'
					     else f_item_group_id
					     end as f_item_group
					     from b_item inner join b_item_subgroup on b_item.b_item_subgroup_id = b_item_subgroup.b_item_subgroup_id 
					     where b_item.b_item_id =  NEW.b_item_id),
		base_unit_id = (select 
						case when b_item_drug_uom.item_drug_uom_number <> ''
						and b_item_drug_uom.item_drug_uom_number is not null
						then b_item_drug_uom.item_drug_uom_number
						else ''
						end as item_drug_uom
						from b_item left join b_item_drug on b_item.b_item_id = b_item_drug.b_item_id
						left join b_item_drug_uom on b_item_drug.item_drug_purch_uom = b_item_drug_uom.b_item_drug_uom_id
						where b_item.b_item_id =  NEW.b_item_id)
	WHERE item_id = OLD.b_item_id;

CREATE RULE b_item_on_del AS ON DELETE TO b_item 
DO 
	DELETE FROM imed.item WHERE item_id = OLD.b_item_id;
--------------------------------------------- TABLE b_item END --------------------------------------------------

----------------------------------------- TABLE b_item_price BEGIN -------------------------------------------------
CREATE RULE b_item_price_on_ins AS ON INSERT TO b_item_price 
DO 
	INSERT INTO imed.item_price(item_price_id, item_id, base_tariff_id, active_date, unit_price) 
	VALUES (NEW.b_item_price_id, NEW.b_item_id, '1', (select  (to_number(substring(NEW.item_price_active_date,0,5),9999)-543)
													|| '-' ||substring(NEW.item_price_active_date,6,2)
													|| '-' ||substring(NEW.item_price_active_date,9,2)  as dt), NEW.item_price);

CREATE RULE b_item_price_on_upd AS ON UPDATE TO b_item_price 
DO 
	UPDATE imed.item_price 
	SET	item_price_id = NEW.b_item_price_id, 
		item_id = NEW.b_item_id, 
		base_tariff_id = '1', 
		active_date =  (select  (to_number(substring(NEW.item_price_active_date,0,5),9999)-543)
													|| '-' ||substring(NEW.item_price_active_date,6,2)
													|| '-' ||substring(NEW.item_price_active_date,9,2)  as dt), 
		unit_price = NEW.item_price
	WHERE item_price_id = OLD.b_item_price_id;

CREATE RULE b_item_price_on_del AS ON DELETE TO b_item_price 
DO 
	DELETE FROM imed.item_price WHERE item_price_id = OLD.b_item_price_id;
------------------------------------------- TABLE b_item_price END --------------------------------------------------

----------------------------------------- TABLE b_service_point BEGIN -------------------------------------------------
CREATE RULE b_service_point_on_ins AS ON INSERT TO b_service_point 
DO 
	INSERT INTO imed.base_service_point(base_service_point_id, description, active) 
	VALUES (NEW.b_service_point_id, NEW.service_point_description, NEW.service_point_active);

CREATE RULE b_service_point_on_upd AS ON UPDATE TO b_service_point 
DO 
	UPDATE imed.base_service_point 
	SET	base_service_point_id = NEW.b_service_point_id, 
		description = NEW.service_point_description, 
		active = NEW.service_point_active
	WHERE base_service_point_id = OLD.b_service_point_id;

CREATE RULE b_service_point_on_del AS ON DELETE TO b_service_point 
DO 
	DELETE FROM imed.base_service_point WHERE base_service_point_id = OLD.b_service_point_id;
------------------------------------------- TABLE b_service_point END --------------------------------------------------

----------------------------------------- TABLE b_visit_ward BEGIN -------------------------------------------------
CREATE RULE b_visit_ward_on_ins AS ON INSERT TO b_visit_ward 
DO 
	INSERT INTO imed.base_service_point(base_service_point_id, description, active) 
	VALUES (NEW.b_visit_ward_id, NEW.visit_ward_description, NEW.visit_ward_active);

CREATE RULE b_visit_ward_on_upd AS ON UPDATE TO b_service_point 
DO 
	UPDATE imed.base_service_point 
	SET	base_service_point_id = NEW.b_service_point_id, 
		description = NEW.service_point_description, 
		active = NEW.service_point_active
	WHERE base_service_point_id = OLD.b_service_point_id;

CREATE RULE b_visit_ward_on_del AS ON DELETE TO b_service_point 
DO 
	DELETE FROM imed.base_service_point WHERE base_service_point_id = OLD.b_service_point_id;
------------------------------------------- TABLE b_visit_ward END --------------------------------------------------

----------------------------------------- TABLE b_item_drug_uom BEGIN -------------------------------------------------
CREATE RULE b_item_drug_uom_on_ins AS ON INSERT TO b_item_drug_uom 
DO 
	INSERT INTO imed.base_unit(base_unit_id, description_th) 
	VALUES (NEW.item_drug_uom_number, NEW.item_drug_uom_description);

CREATE RULE b_item_drug_uom_on_upd AS ON UPDATE TO b_item_drug_uom 
DO 
	UPDATE imed.base_unit 
	SET	base_unit_id = NEW.item_drug_uom_number, 
		description_th = NEW.item_drug_uom_description
	WHERE base_unit_id = OLD.item_drug_uom_number;

CREATE RULE b_item_drug_uom_on_del AS ON DELETE TO b_item_drug_uom 
DO 
	DELETE FROM imed.base_unit WHERE base_unit_id = OLD.item_drug_uom_number;
------------------------------------------- TABLE b_item_drug_uom END --------------------------------------------------

----------------------------------------- TABLE base_site BEGIN -------------------------------------------------
CREATE RULE b_site_on_ins AS ON INSERT TO b_site 
DO 
	INSERT INTO imed.base_site(base_site_id, site_name, tel, login_message_active) 
	VALUES (NEW.b_visit_office_id, NEW.site_full_name, NEW.site_phone_number, '1');

CREATE RULE b_site_on_upd AS ON UPDATE TO b_site 
DO 
	UPDATE imed.base_site 
	SET	base_site_id = NEW.b_visit_office_id, 
		site_name = NEW.site_full_name, 
		tel = NEW.site_phone_number, 
		login_message_active = '1'
	WHERE base_site_id = OLD.b_visit_office_id;

CREATE RULE b_site_on_del AS ON DELETE TO b_site 
DO 
	DELETE FROM imed.base_site WHERE base_site_id = OLD.b_visit_office_id;
------------------------------------------- TABLE base_site END --------------------------------------------------
