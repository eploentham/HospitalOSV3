select item_name as name
    , uom as uom
    , count(*) as dispense_times
    , abs(sum(qty)) as dispense_qty
    , abs(sum(qty*cost)) as cost
    , abs(sum(qty*price)) as price
from
    (
        select b_item.item_common_name as item_name
            , b_item_drug_uom.item_drug_uom_description as uom
            , to_number(to_char(dispense.stock_lot_item_qty,'9999990.00'),'9999990,00') as qty
            , receive.stock_lot_item_dispense_uom_cost as cost
            , item_price.item_price as price
        from
            --ดึงข้อมูลการจ่ายออกจากแต่ละตารางมา unioun กัน
            (
                --จ่ายให้ผู้ป่วย
                select t_stock_patient_dispense.b_stock_id
                    , t_stock_lot_item.stock_lot_item_lot_no_in
                    , t_stock_lot_item.t_stock_item_id
                    , t_stock_lot_item.stock_lot_item_update_date_time
                    , t_stock_lot_item.f_stock_sub_method_id
                    --, stock_patient_dispense_qty + stock_patient_dispense_return_qty 
                    --    as stock_lot_item_qty
                    , t_stock_lot_item.stock_lot_item_qty
                from t_stock_patient_dispense
                    inner join t_stock_lot_item 
                        on t_stock_patient_dispense.t_stock_patient_dispense_id
                            = t_stock_lot_item.t_stock_pat_disp_id
                where t_stock_patient_dispense.stock_patient_active = '1'
--                    and (t_stock_lot_item.stock_lot_item_update_date_time >= '2551-02-22,09:08' --parameter
--                       and t_stock_lot_item.stock_lot_item_update_date_time <= '2551-04-24,09:08') --parameter

                union

                --จ่ายให้กองทุนยา
                select t_stock_drugfund_dispense.b_stock_id as stock
                    , t_stock_lot_item.stock_lot_item_lot_no_in
                    , t_stock_lot_item.t_stock_item_id
                    , t_stock_lot_item.stock_lot_item_update_date_time
                    , t_stock_lot_item.f_stock_sub_method_id
                    , t_stock_lot_item.stock_lot_item_qty
                from t_stock_drugfund_dispense
                    inner join t_stock_lot_item on t_stock_drugfund_dispense.t_stock_drugfund_dispense_id 
                        = t_stock_lot_item.t_stock_df_disp_id
                where t_stock_drugfund_dispense.stock_drugfund_active = '1'
--                    and (t_stock_lot_item.stock_lot_item_update_date_time >= '2551-02-22,09:08' --parameter
--                       and t_stock_lot_item.stock_lot_item_update_date_time <= '2551-04-24,09:08') --parameter

                union

                --ปรับยอด
                select t_stock_adjust.b_stock_id as stock
                    , t_stock_lot_item.stock_lot_item_lot_no_in
                    , t_stock_lot_item.t_stock_item_id
                    , t_stock_lot_item.stock_lot_item_update_date_time
                    , t_stock_lot_item.f_stock_sub_method_id
                    , t_stock_lot_item.stock_lot_item_qty
                from t_stock_adjust
                    inner join t_stock_lot_item on t_stock_adjust.t_stock_adjust_id
                        = t_stock_lot_item.t_stock_adjust_id
                where t_stock_adjust.stock_adjust_active = '1'
--                    and (t_stock_lot_item.stock_lot_item_update_date_time >= '2551-02-22,09:08' --parameter
--                        and t_stock_lot_item.stock_lot_item_update_date_time <= '2551-04-24,09:08') --parameter

                union

                --ขอเบิก
                select t_stock_request.stock_request_disp_id as stock
                    , t_stock_lot_item.stock_lot_item_lot_no_in
                    , t_stock_lot_item.t_stock_item_id
                    , t_stock_lot_item.stock_lot_item_update_date_time
                    , t_stock_lot_item.f_stock_sub_method_id
                    , t_stock_lot_item.stock_lot_item_qty
                from t_stock_request
                    inner join t_stock_request_item
                        on t_stock_request.t_stock_request_id = t_stock_request_item.t_stock_request_id
                    inner join t_stock_lot_item 
                        on t_stock_request_item.t_stock_request_id
                            = t_stock_lot_item.t_stock_request_item_id
                where t_stock_request.stock_request_active= '1'  
--                    and (t_stock_lot_item.stock_lot_item_update_date_time >= '2551-02-22,09:08' --parameter
--                        and t_stock_lot_item.stock_lot_item_update_date_time <= '2551-04-24,09:08') --parameter
            ) as dispense
            inner join t_stock_lot_item as receive 
                on dispense.stock_lot_item_lot_no_in = receive.t_stock_lot_item_id
            inner join t_stock_item 
                on dispense.t_stock_item_id = t_stock_item.t_stock_item_id
            left join b_item on t_stock_item.b_item_id = b_item.b_item_id
            left join b_item_drug_uom 
                on receive.stock_lot_item_dispense_uom_id = b_item_drug_uom_id
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
        where (dispense.stock_lot_item_update_date_time >= ? --parameter
                and dispense.stock_lot_item_update_date_time <= ?)--parameter
            and receive.stock_lot_item_active = '1'
            and dispense.f_stock_sub_method_id in (?)--parameter
    ) as dispense_data
group by name
    , uom
