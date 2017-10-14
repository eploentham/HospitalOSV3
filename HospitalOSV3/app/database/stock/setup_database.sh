PGPATH="/usr/local/pgsql/bin"
APATH="/home/postgres/stock"
DATABASE="stock_hosv37"
$PGPATH/psql $DATABASE -U postgres -f $APATH/1_iMed-Schema.sql 
$PGPATH/psql $DATABASE -U postgres -f $APATH/2_imed4hos.sql 
$PGPATH/psql $DATABASE -U postgres -f $APATH/3_abc_ven.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/4_item_price.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/5_base_service_point.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/6_ward.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/7_item.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/8_base_site.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/9_base_unit.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/10_employee.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/12_employee_role.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/11_Rule.sql
