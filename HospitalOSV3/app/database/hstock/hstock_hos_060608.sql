CREATE TABLE t_stock_offer ( 
	t_stock_offer_id varchar(255) NOT NULL,
	stock_offer_no varchar(255) DEFAULT ''::character varying,
	f_stock_order_status_id varchar(255) DEFAULT ''::character varying,
	stock_offer_record_date_time varchar(255) DEFAULT ''::character varying,
	stock_offer_record_eid varchar(255) DEFAULT ''::character varying,
	stock_offer_update_date_time varchar(255) DEFAULT ''::character varying,
	stock_offer_update_eid varchar(255) DEFAULT ''::character varying,
	PRIMARY KEY (t_stock_offer_id) 
);

CREATE TABLE t_stock_offer_item ( 
	t_stock_offer_item_id varchar(255) NOT NULL,
	t_stock_offer_id varchar(255) DEFAULT ''::character varying,
	t_stock_item_id varchar(255) DEFAULT ''::character varying,
	b_distributor_id varchar(255) DEFAULT ''::character varying,
	stock_offer_item_cur_qty double precision,
	stock_offer_item_qty double precision,
	stock_offer_uom_id varchar(255) DEFAULT ''::character varying,
	stock_offer_dispense_uom_rate double precision,
	stock_offer_mid_uom_rate double precision,
	stock_offer_uom_price double precision,
	stock_offer_comments varchar(255) DEFAULT ''::character varying,
	f_stock_order_status_id varchar(255) DEFAULT ''::character varying,
	stock_offer_item_record_date_time varchar(255) DEFAULT ''::character varying,
	stock_offer_item_record_eid varchar(255) DEFAULT ''::character varying,
	stock_offer_item_update_date_time varchar(255) DEFAULT ''::character varying,
	stock_offer_item_update_eid varchar(255) DEFAULT ''::character varying,
	PRIMARY KEY (t_stock_offer_item_id) 
);

CREATE TABLE t_stock_order_item_history ( 
	t_stock_order_item_history_id varchar(25) NOT NULL,
	t_stock_offer_id varchar(25) DEFAULT ''::character varying,
	t_stock_order_id varchar(25) DEFAULT ''::character varying,
	t_stock_item_id varchar(25) DEFAULT ''::character varying,
	stock_order_item_history_cur_qty double precision,
	stock_order_item_history_qty double precision,
	stock_order_item_history_free_qty double precision,
	stock_order_uom_id varchar(25) DEFAULT ''::character varying,
	stock_order_item_history_offer_discount varchar(25) DEFAULT ''::character varying,
	stock_order_item_history_offer_discount_percent varchar(25) DEFAULT ''::character varying,
	stock_order_item_history_comments varchar(25) DEFAULT ''::character varying,
	f_stock_order_status_id varchar(25) DEFAULT ''::character varying,
	stock_order_item_history_uom_price double precision,
	stock_order_item_history_dispense_uom_rate double precision,
	stock_order_item_history_mid_uom_rate double precision,
	stock_order_item_history_record_date_time varchar(25) DEFAULT ''::character varying,
	stock_order_item_history_record_eid varchar(25) DEFAULT ''::character varying ,
	PRIMARY KEY (t_stock_order_item_history_id) 
);

ALTER TABLE t_stock_order
	ADD COLUMN t_stock_offer_id varchar(255) DEFAULT ''::character varying;
	
ALTER TABLE t_stock_order_item
	ADD COLUMN t_stock_offer_id varchar(255) DEFAULT ''::character varying,
	ADD COLUMN stock_order_item_cur_qty double precision;

ALTER TABLE t_stock_request
	ADD COLUMN stock_request_ename varchar(255) DEFAULT ''::character varying;

INSERT INTO f_stock_sub_method(f_stock_sub_method_id, stock_sub_method_description) VALUES('15', 'รอรับเข้า');
INSERT INTO f_stock_order_status(f_stock_order_status_id,stock_order_status_description) VALUES('12', 'รอรับเข้า');

INSERT INTO s_stock_hos_version VALUES ('sth0190000000000004', '04', 'Stock Hos Module', '1.0.060608', '1.0.060608', '2551-06-06 18:30:00');
