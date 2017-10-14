select 
    t_stock_item.stock_item_name as ITEMNAME
    ,b_stock_distributor.stock_distributor_name as DISTRIBUTOR
    ,sum(t_stock_order_item.stock_order_item_qty) as BUY
    ,sum(t_stock_order_item.stock_order_free_qty) as "FREE" 
    ,b_item_drug_uom.item_drug_uom_description as UOM
    ,avg(t_stock_order_item.stock_order_uom_price) as PRICEPERUNIT
    ,AVG(t_stock_order_item.stock_order_uom_price - b_item_price.item_price) as COSTCHANGE
    ,substring(stock_order_purchasing_offer_date_time,9,2) || '/' ||substring(stock_order_purchasing_offer_date_time,6,2) || '/' || substring(stock_order_purchasing_offer_date_time,0,5) as "DATE"
    ,b_stock_distributor.stock_distributor_contact_person as "AGENT"
from t_stock_order_item
    inner join t_stock_item
        on t_stock_order_item.t_stock_item_id = t_stock_item.t_stock_item_id
    inner join t_stock_order
        on t_stock_order_item.t_stock_order_id = t_stock_order.t_stock_order_id
    inner join b_stock_distributor
        on t_stock_order.b_stock_distributor_id = b_stock_distributor.b_stock_distributor_id
    inner join b_item_price 
        on t_stock_item.b_item_id = b_item_price.b_item_id
    left join b_item_drug_uom
        on t_stock_order_item.stock_order_uom_id = b_item_drug_uom.b_item_drug_uom_id
where stock_order_purchasing_offer_date_time >= ?
    and stock_order_purchasing_offer_date_time <= ?
    and b_stock_distributor.stock_distributor_name in (?)
group by 
    ITEMNAME
    ,DISTRIBUTOR
    ,UOM
    ,"DATE"
    ,"AGENT"
order by 
    DISTRIBUTOR
    ,"DATE"
    ,ITEMNAME
