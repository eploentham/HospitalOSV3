--07 รายงานบันทึกรายละเอียดการสั่งซื้อยาและเวชภัณฑ์ 
select 
   substring(t_stock_order.stock_order_purchasing_offer_date_time,9,2) || '/' ||substring(t_stock_order.stock_order_purchasing_offer_date_time,6,2) || '/' || substring(t_stock_order.stock_order_purchasing_offer_date_time,0,5) as "DATE" 
   ,b_stock_distributor.stock_distributor_name as DISTRIBUTOR 
   ,t_stock_order.stock_order_no as ORDERNO 
   ,t_stock_order.stock_order_cash_limit as CASHLIMIT 
   ,t_stock_item.stock_item_name as ITEMNAME 
   ,item_drug_uom_description as UOM
   ,stock_item_mid_uom_rate || ' x ' || stock_item_dispense_uom_rate as PACKAGECAPACITY 
   ,t_stock_order_item.stock_order_uom_price as PRICE
   ,t_stock_order_item.stock_order_item_qty as BUY 
   ,t_stock_order_item.stock_order_free_qty as "FREE"
   ,t_stock_order_item.stock_order_item_qty * t_stock_order_item.stock_order_uom_price as TOTALPRICE 
   ,t_stock_order_item.stock_order_comments as "COMMENT" 
   ,t_stock_order_item.stock_order_uom_price as AVGCOST
from t_stock_order 
    inner join t_stock_order_item 
       on t_stock_order_item.t_stock_order_id = t_stock_order.t_stock_order_id 
    inner join t_stock_item 
       on t_stock_item.t_stock_item_id = t_stock_order_item.t_stock_item_id 
    left join t_stock_lot_item 
       on t_stock_lot_item.t_stock_order_item_id = t_stock_order_item.t_stock_order_item_id 
       and t_stock_lot_item.f_stock_sub_method_id = '1' 
       and t_stock_lot_item.stock_lot_item_active = '1' 
    left join b_item_drug_uom 
       on t_stock_order_item.stock_order_uom_id = b_item_drug_uom.b_item_drug_uom_id 
    left join b_stock_distributor 
       on t_stock_order.b_stock_distributor_id = b_stock_distributor.b_stock_distributor_id 
where stock_order_purchasing_offer_date_time >= ?
    and stock_order_purchasing_offer_date_time <= ?
    and t_stock_order.f_stock_order_status_id in (?)
    and t_stock_order.stock_order_active = '1'
group by 
    "DATE" 
   ,DISTRIBUTOR 
   ,ORDERNO 
   ,CASHLIMIT 
   ,ITEMNAME 
   ,UOM
   ,PACKAGECAPACITY 
   ,PRICE
   ,BUY 
   ,"FREE"
   ,TOTALPRICE 
   ,"COMMENT" 
   ,AVGCOST
order by 
    ORDERNO
    ,ITEMNAME