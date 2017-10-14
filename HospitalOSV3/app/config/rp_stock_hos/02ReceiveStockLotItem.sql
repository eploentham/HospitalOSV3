--02 รายงานการรับยาและเวชภัณฑ์เข้าคลัง
select item_name as name
    , uom as uom
    , count(*) as receive_times
    , sum(qty) as receive_qty
    , sum(qty*cost) as cost
    , sum(qty*price) as price
from
    (
        select b_item.item_common_name as item_name
            , b_item_drug_uom.item_drug_uom_description as uom
            , abs(to_number(to_char(t_stock_lot_item.stock_lot_item_qty,'9999990.00'),'9999990.00')) as qty
            , t_stock_lot_item.stock_lot_item_dispense_uom_cost as cost
            , item_price.item_price as price
        from t_stock_lot_item 
            inner join t_stock_item 
                on t_stock_lot_item.t_stock_item_id = t_stock_item.t_stock_item_id
            left join b_item on t_stock_item.b_item_id = b_item.b_item_id
            left join b_item_drug_uom 
                on t_stock_lot_item.stock_lot_item_dispense_uom_id = b_item_drug_uom_id
            inner join (
                --เลือกรายการราคาของ item อันล่าสุดออกมา
                select b_item_id
                    , item_price
                from b_item_price 
                    inner join (
                        --เลือกวันที่กำหนดราคายาครั้งล่าสุด เพื่อเอาไป join กับตารางเดิมให้ได้รายการล่าสุด
                        select b_item_id as item_id
                            , max(item_price_active_date) as last_date
                        from b_item_price
                        group by b_item_id
                    ) as last_price on b_item_price.b_item_id = last_price.item_id 
                        and b_item_price.item_price_active_date = last_price.last_date
                ) as item_price on t_stock_item.b_item_id = item_price.b_item_id
        where (t_stock_lot_item.stock_lot_item_receive_date_time >= ? --parameter
                and t_stock_lot_item.stock_lot_item_receive_date_time <= ?)--paramter
            and t_stock_lot_item.stock_lot_item_active = '1'
            and --(t_stock_lot_item.f_stock_sub_method_id = '0' or 
                t_stock_lot_item.f_stock_sub_method_id in (?)--parameter
                --)
    ) as dispense_data
group by name
    , uom
