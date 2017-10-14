--03 รายงานยาและเวชภัณฑ์ใกล้มหมดอายุ
--select ข้อมูลให้อยู่ในรูปที่แสดงในรายงาน
select drug_name as name
    , lot_no
    , uom
    , remain
    , remain * cost as buy_value
    , str_expire_date
    , --ดึงจำนวนปีโดยเทียบจาก expire_date ถึงวันปัจจุบัน
      case when str_expire_date = ''
        then ''
        else extract(year from ages)||''
      end as "year"
    , --ดึงจำนวนเดือนโดยเทียบจาก expire_date ถึงวันปัจจุบัน
      case when str_expire_date = ''
        then ''
        else extract(month from ages)||''
      end as "month"
    , --ดึงจำนวนวันโดยเทียบจาก expire_date ถึงวันปัจจุบัน
      case when str_expire_date = ''
        then ''
        else extract(day from ages)||''
      end as "day"
from (
    --select ข้อมูลดิบที่ยังไม่ผ่านการคำนวณ
    select b_item.item_common_name as drug_name
        , t_stock_lot_item.stock_lot_item_lot_no as lot_no
        , b_item_drug_uom.item_drug_uom_description as uom
        , latest_update_lot.stock_lot_item_update_qty_lot as remain
        , t_stock_lot_item.stock_lot_item_dispense_uom_cost as cost
        , t_stock_lot_item.stock_lot_item_expire_date as str_expire_date
        , --คำนวณอายุจากวันเริ่มต้นถึงวันหมดอายุ
          age(to_date(t_stock_lot_item.stock_lot_item_expire_date,'yyyy-mm-dd')
                , to_date('2551-01-01','yyyy-mm-dd')--parameter
          ) as ages    
    from t_stock_lot_item 
        inner join t_stock_item on t_stock_lot_item.t_stock_item_id = t_stock_item.t_stock_item_id
        --join กับข้อมูลล่าสุดของ lot นั้นๆ
        inner join (
            --ค้นหาข้อมูล record ที่ถูก update ล่าสุดในแต่ละ lot
            select distinct *
            from t_stock_lot_item 
                inner join (
                    --หาวันที่ update ล่าสุดในแต่ละ lot เพื่อเอามา join กับตัวเองเพื่อให้ได้ข้อมูล record ล่าสุด
                    select t_stock_lot_item.stock_lot_item_lot_no_in as lot_no_in
                        , max(t_stock_lot_item.stock_lot_item_update_date_time) as update_date
                    from t_stock_lot_item
                    group by t_stock_lot_item.stock_lot_item_lot_no_in
                ) as last_update
                    on t_stock_lot_item.stock_lot_item_lot_no_in = last_update.lot_no_in
                        and t_stock_lot_item.stock_lot_item_update_date_time 
                            = last_update.update_date 
        ) as latest_update_lot
            on t_stock_lot_item.t_stock_lot_item_id = latest_update_lot.stock_lot_item_lot_no_in
        left join b_item on t_stock_item.b_item_id = b_item.b_item_id
        left join b_item_drug_uom on t_stock_lot_item.stock_lot_item_dispense_uom_id = b_item_drug_uom_id
    where (t_stock_lot_item.stock_lot_item_expire_date >= ?--'2551-01-01' --parameter
            and t_stock_lot_item.stock_lot_item_expire_date <= ?--'2553-03-31'--parameter
            or t_stock_lot_item.stock_lot_item_expire_date = '')
        and t_stock_lot_item.stock_lot_item_active = '1'
        and (t_stock_lot_item.f_stock_method_id = '0'
            or t_stock_lot_item.f_stock_method_id = '1')
        and (t_stock_lot_item.f_stock_sub_method_id = '0'
            or t_stock_lot_item.f_stock_sub_method_id = '1'
            or t_stock_lot_item.f_stock_sub_method_id = '2'
            or t_stock_lot_item.f_stock_sub_method_id = '5')
) as lot_data
order by drug_name
    , lot_no