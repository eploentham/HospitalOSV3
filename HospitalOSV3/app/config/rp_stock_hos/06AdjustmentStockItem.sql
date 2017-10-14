--06 รายงานรายการปรับยอดยาและเวชภัณฑ์
select 
    t_stock_item.stock_item_name as ITEMNAME
    ,b_item_drug_uom.item_drug_uom_description as UOM
    ,t_stock_lot_item.stock_lot_item_lot_no as LOTNO
    ,substring(t_stock_adjust.stock_adjust_date_time,9,2) || '/' ||substring(t_stock_adjust.stock_adjust_date_time,6,2) || '/' || substring(t_stock_adjust.stock_adjust_date_time,0,5) as ADJUSTDATE
    ,f_stock_sub_method.stock_sub_method_description as ADJUSTNOTE
    ,sum(t_stock_lot_item.stock_lot_item_previous_qty) as PREVIOUSQTY
    ,sum(abs(t_stock_lot_item.stock_lot_item_qty)) as "ADJUSTQTY"
    ,sum(t_stock_lot_item.stock_lot_item_update_qty) as UPDATEQTY
    ,sum(abs(b_item_price.item_price * t_stock_lot_item.stock_lot_item_qty)) as "ADJUSTVALUE"
    ,b_employee.employee_firstname || ' ' || b_employee.employee_lastname as ADJUSTOR
    ,t_stock_lot_item.stock_lot_item_note as ITEMNOTE
    
from t_stock_lot_item
    inner join t_stock_adjust
        on t_stock_adjust.t_stock_adjust_id = t_stock_lot_item.t_stock_adjust_id
    inner join t_stock_item
        on t_stock_item.t_stock_item_id = t_stock_lot_item.t_stock_item_id
    left join b_item_drug_uom
        on t_stock_lot_item.stock_lot_item_order_uom_id = b_item_drug_uom.b_item_drug_uom_id
    inner join f_stock_sub_method
        on t_stock_adjust.stock_adjust_note = f_stock_sub_method.f_stock_sub_method_id
    left join b_item_price 
        on t_stock_item.b_item_id = b_item_price.b_item_id
    left join b_employee
        on b_employee.b_employee_id = t_stock_adjust.stock_adjust_eid
where t_stock_adjust.stock_adjust_date_time >= ?
and t_stock_adjust.stock_adjust_date_time <= ?
and t_stock_adjust.stock_adjust_note in (?)
group by 
    ITEMNAME
    ,UOM
    ,LOTNO
    ,ADJUSTDATE
    ,ADJUSTNOTE
    ,ADJUSTOR
    ,ITEMNOTE
order by 
    ITEMNAME
    ,ADJUSTDATE
    ,ADJUSTNOTE