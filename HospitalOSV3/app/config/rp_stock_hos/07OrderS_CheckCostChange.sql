--sql check ราคาทุนครั้งก่อนหน้า 
select 
   t_stock_order.stock_order_purchasing_offer_date_time as "DATE" 
   ,t_stock_item.stock_item_name as ITEMNAME 
   ,t_stock_order_item.stock_order_uom_price as "COST" 
from t_stock_order 
    inner join t_stock_order_item 
       on t_stock_order_item.t_stock_order_id = t_stock_order.t_stock_order_id 
    inner join t_stock_item 
       on t_stock_item.t_stock_item_id = t_stock_order_item.t_stock_item_id 
    left join t_stock_lot_item 
       on t_stock_lot_item.t_stock_order_item_id = t_stock_order_item.t_stock_order_item_id 
       and t_stock_lot_item.f_stock_sub_method_id = '1' 
       and t_stock_lot_item.stock_lot_item_active = '1' 
where stock_order_purchasing_offer_date_time < ?
    and t_stock_order.f_stock_order_status_id in (?)
    and t_stock_order.stock_order_active = '1'
    and t_stock_item.stock_item_name = ?
order by 
    "DATE" 
desc limit 1