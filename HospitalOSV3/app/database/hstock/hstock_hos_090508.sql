ALTER TABLE t_stock_request 
	ADD COLUMN  f_stock_order_status_id character varying(255) DEFAULT ''::character varying;

ALTER TABLE t_stock_request_item 
	ADD COLUMN  f_stock_order_status_id character varying(255) DEFAULT ''::character varying;

UPDATE  f_stock_sub_method SET stock_sub_method_description= 'ปรับเพิ่ม' WHERE f_stock_sub_method_id='14';

INSERT INTO s_stock_hos_version VALUES ('sth0190000000000002', '02', 'Stock Hos Module', '1.0.090508', '1.0.090508', '2551-05-09 18:00:00');
