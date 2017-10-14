CREATE TABLE t_stock_drug_return_sp ( 
    t_stock_drug_return_sp_id          	varchar(255) NOT NULL,
    t_order_drug_return_id  	varchar(255) NULL,
    b_service_point_id     	varchar(255) NULL,
    t_order_item_id                     	varchar(255) NULL,
   CONSTRAINT t_stock_drug_return_sp_pkey PRIMARY KEY (t_stock_drug_return_sp_id) 
);


INSERT INTO s_stock_hos_version VALUES ('sth0190000000000003', '03', 'Stock Hos Module', '1.0.280508', '1.0.280508', '2551-05-28 11:00:00');
