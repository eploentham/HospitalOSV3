--04 ��§ҹ��ػ��Ť��������Ǫ�ѳ�줧��ѧ
--���͡����������ش�ͧ���� lot ���ǹ� update_qty_lot ����� item ���ǡѹ�Һǡ�ѹ
select remain_data.item_name as name
    , remain_data.item_code as code
    , sum(remain_data.remain) as remain
    , case
        when sum(remain_data.remain) < remain_data.min_qty
            then '���¡��� Min'
        when sum(remain_data.remain) > remain_data.max_qty
            then '�ҡ���� Max'
        else ''
      end as remain_status
    , uom
    , sum(remain*cost) as buy_value
    , sum(remain*price) as sell_value
    , remain_data.min_qty
    , remain_data.max_qty
from (
    --select �����ŴԺ����ѧ����ҹ��äӹǳ
    select b_item.item_common_name as item_name
        , t_stock_item.stock_item_code as item_code
        , latest_update_lot.qty_lot as remain
        , b_item_drug_uom.item_drug_uom_description as uom
        , t_stock_lot_item.stock_lot_item_dispense_uom_cost as cost
        , item_price.item_price as price
        , latest_update_lot.upd_date as update_date
        , t_stock_item.stock_item_max_qty as max_qty
        , t_stock_item.stock_item_min_qty as min_qty
        , t_stock_lot_item.f_stock_method_id
        , t_stock_lot_item.f_stock_sub_method_id
        , t_stock_lot_item.stock_lot_item_active as active
    from t_stock_lot_item
        inner join t_stock_item
            on t_stock_lot_item.t_stock_item_id = t_stock_item.t_stock_item_id
        --join �Ѻ����������ش�ͧ lot ����
        inner join (
            --���Ң����� record ���١ update ����ش����� lot
            select t_stock_lot_item.stock_lot_item_lot_no_in
                , t_stock_lot_item.stock_lot_item_update_qty_lot as qty_lot
                , t_stock_lot_item.stock_lot_item_update_date_time as upd_date
            from t_stock_lot_item
                inner join (
                    --���ѹ��� update ����ش����� lot ��������� join �Ѻ����ͧ�������������� record ����ش
                    select t_stock_lot_item.stock_lot_item_lot_no_in as lot_no_in
                        , max(t_stock_lot_item.stock_lot_item_update_date_time) as update_date
                    from t_stock_lot_item
                    where t_stock_lot_item.stock_lot_item_lot_no_in <> ''
                        and t_stock_lot_item.stock_lot_item_update_date_time <= ?--parameter\
                    group by t_stock_lot_item.stock_lot_item_lot_no_in
                ) as last_update
                    on t_stock_lot_item.stock_lot_item_lot_no_in = last_update.lot_no_in
                        and t_stock_lot_item.stock_lot_item_update_date_time = last_update.update_date
        ) as latest_update_lot
            on t_stock_lot_item.t_stock_lot_item_id = latest_update_lot.stock_lot_item_lot_no_in
        --join �Ѻ�Ҥ�������ش�ͧ item ���
        inner join (
            --���͡��¡���ҤҢͧ item �ѹ����ش�͡��
            --konshow ��� Distinct ���ͻ�ͧ�ѹ�Դ��ë�Ӣͧ������
            select Distinct(b_item_id)
                , item_price
            from b_item_price
                inner join (
                    --���͡�ѹ����˹��Ҥ��Ҥ�������ش �������� join �Ѻ���ҧ���������Ҥ�����ش
                    select b_item_id as item_id
                        , max(item_price_active_date) as last_date
                    from b_item_price
                    group by b_item_id
                ) as last_price on b_item_price.b_item_id = last_price.item_id
                    and b_item_price.item_price_active_date = last_price.last_date
            order by b_item_id
        ) as item_price
            on t_stock_item.b_item_id = item_price.b_item_id
        left join b_item on t_stock_item.b_item_id = b_item.b_item_id
        left join b_item_drug_uom on t_stock_lot_item.stock_lot_item_dispense_uom_id = b_item_drug_uom_id
) as remain_data
where remain_data.update_date <= ?--parameter
    and remain_data.active = '1'
    and (remain_data.f_stock_method_id = '0'
        or remain_data.f_stock_method_id = '1')
    and (remain_data.f_stock_sub_method_id = '0'
        or remain_data.f_stock_sub_method_id = '1'
        or remain_data.f_stock_sub_method_id = '2'
        or remain_data.f_stock_sub_method_id = '5')
group by item_name
    , item_code
    , min_qty
    , max_qty
    , uom
order by item_name
    , item_code
--having sum(remain_data.remain)>remain_data.max_qty