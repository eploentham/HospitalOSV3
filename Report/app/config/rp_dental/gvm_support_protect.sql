select f_dentalrp_government_part2_item.f_dentalrp_government_part2_item_description as ประเภทงาน
    , sum(case when(q1.pregnant = '1') then 1 else 0 end) as หญิงตั้งครรภ์
    , sum(case when(to_number(q1.age,'999') between 0 and 2) then 1 else 0 end) as เด็ก0ถึง2ปี
    , sum(case when(to_number(q1.age,'999') between 3 and 5) then 1 else 0 end) as เด็ก3ถึง5ปี
    , sum(case when(to_number(q1.age,'999') between 6 and 14) then 1 else 0 end) as เด็ก6ถึง14ปี
from f_dentalrp_government_part2_item 
left join 
    (
        select distinct
             b_dentalrp_government_part2_support_protect.f_dentalrp_government_part2_item_id as item
             , t_visit.t_visit_id as visit_id
             , t_visit.visit_pregnant as pregnant
             , t_visit.visit_patient_age as age
      
         from t_visit 
             inner join t_order 
                on t_visit.t_visit_id = t_order.t_visit_id
             inner join b_dentalrp_government_part2_support_protect 
                on b_dentalrp_government_part2_support_protect.b_item_id = t_order.b_item_id
             inner join t_visit_service 
                on (t_visit.t_visit_id = t_visit_service.t_visit_id 
                and (t_visit_service.b_service_point_id 
                    in (select b_dentalrp_service_point.b_service_point_id from b_dentalrp_service_point)))
         where (substring(t_visit.visit_begin_visit_time,1,10) between ? and ?)
             and t_visit.visit_money_discharge_status = '1') as q1
on f_dentalrp_government_part2_item.f_dentalrp_government_part2_item_id = q1.item
 
group by f_dentalrp_government_part2_item.f_dentalrp_government_part2_item_description
    , f_dentalrp_government_part2_item.f_dentalrp_government_part2_item_id
order by f_dentalrp_government_part2_item.f_dentalrp_government_part2_item_id 