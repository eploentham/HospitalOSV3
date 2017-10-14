--05 รายงานรายการยาและเวชภัณฑ์ไม่เคลื่อนไหว
select b_item.item_common_name as name
    , b_item_drug_uom.item_drug_uom_description as uom
    , t_stock_lot_item.stock_lot_item_lot_no as lot_no
    , t_stock_lot_item.stock_lot_item_expire_date as str_expire_date
    , last_lot.stock_lot_item_update_qty_lot as remain
    , last_lot.stock_lot_item_update_date_time as update_date
    , f_stock_sub_method.stock_sub_method_description as method
    , abs(last_lot.stock_lot_item_qty) as change_qty --จำนวนที่เปลี่ยนแปลงครั้งล่าสุด
--    , abs(last_lot.qty) as change_qty --จำนวนรวมที่มีการเปลี่ยนแปลง
from t_stock_lot_item
    inner join (
        select distinct *
        from t_stock_lot_item
            inner join (
                select t_stock_lot_item.stock_lot_item_lot_no_in as lot_no_in
                    , max(t_stock_lot_item.stock_lot_item_update_date_time) as upd_date
                    , count(*) as change_time
                    , sum(stock_lot_item_qty) as qty
                from t_stock_lot_item
                where t_stock_lot_item.stock_lot_item_active = '1'
                    and (t_stock_lot_item.stock_lot_item_update_date_time >= ?--parameter
                    and t_stock_lot_item.stock_lot_item_update_date_time <= ?)--parameter
                group by lot_no_in
                having case when '1'=? --parameter
                        then count(*) >= to_number(?,'9999') --parameter
                        else count(*) <= to_number(?,'9999') --parameter
                    end
            ) as last_update
                on t_stock_lot_item.stock_lot_item_lot_no_in = last_update.lot_no_in
                    and t_stock_lot_item.stock_lot_item_update_date_time = last_update.upd_date
    ) as last_lot
        on t_stock_lot_item.t_stock_lot_item_id = last_lot.stock_lot_item_lot_no_in
    inner join t_stock_item on t_stock_item.t_stock_item_id = t_stock_lot_item.t_stock_item_id
    left join b_item on t_stock_item.b_item_id = b_item.b_item_id
    left join b_item_drug_uom 
        on t_stock_lot_item.stock_lot_item_dispense_uom_id = b_item_drug_uom.b_item_drug_uom_id
    left join f_stock_sub_method 
        on last_lot.f_stock_sub_method_id = f_stock_sub_method.f_stock_sub_method_id
where t_stock_lot_item.stock_lot_item_active = '1'