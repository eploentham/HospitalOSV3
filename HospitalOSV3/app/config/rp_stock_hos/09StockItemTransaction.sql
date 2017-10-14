--09 รายงานรับเข้า/จ่ายออก/ปรับยอด/คงเหลือ ยาและเวชภัณฑ์
select
    t_stock_item.stock_item_name as ITEMNAME
    ,t_stock_lot_item.stock_lot_item_lot_no as LOTNO
    ,b_item_drug_uom.item_drug_uom_description as DESCRIPTION
    ,f_stock_method.stock_method_description as UOMDESCRIPTION
    ,t_stock_lot_item.stock_lot_item_previous_qty_lot as PREVIOUSLOTQTY
    ,abs(t_stock_lot_item.stock_lot_item_qty) as ITEMLOTQTY
    ,t_stock_lot_item.stock_lot_item_update_qty_lot as UPDATELOTQTY
    ,b_employee.employee_firstname || ' ' || b_employee.employee_lastname as ADJUSTOR
    ,substring(t_stock_lot_item.stock_lot_item_update_date_time,9,2) || '/' ||substring(t_stock_lot_item.stock_lot_item_update_date_time,6,2) || '/' || substring(t_stock_lot_item.stock_lot_item_update_date_time,0,5) as "DATE"
    ,t_stock_lot_item.stock_lot_item_note as ITEMNOTE
from t_stock_lot_item
    inner join t_stock_item
        on t_stock_item.t_stock_item_id = t_stock_lot_item.t_stock_item_id
left join b_item_drug_uom
        on t_stock_lot_item.stock_lot_item_order_uom_id = b_item_drug_uom.b_item_drug_uom_id
left join f_stock_method
        on t_stock_lot_item.f_stock_method_id = f_stock_method.f_stock_method_id
left join b_employee
        on b_employee.b_employee_id = t_stock_lot_item.stock_lot_item_update_eid
where t_stock_lot_item.stock_lot_item_update_date_time >= ?
    and t_stock_lot_item.stock_lot_item_update_date_time <= ?
    and t_stock_lot_item.f_stock_method_id in (?)