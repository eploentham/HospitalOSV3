PGPATH="/usr/local/pgsql/bin"
APATH="/home/postgres/stock"
DATABASE="hospital_osv3"
$PGPATH/psql $DATABASE -U postgres -f $APATH/1_iMed-Schema.sql 
$PGPATH/psql $DATABASE -U postgres -f $APATH/2_imed4hos.sql 
$PGPATH/psql $DATABASE -U postgres -f $APATH/3_abc_ven.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/11_Rule.sql
$PGPATH/pg_dump -i -U  postgres  -F p -a -D -v  -t b_employee  $DATABASE  >  $APATH/b_employee.sql
$PGPATH/pg_dump -i -U  postgres  -F p -a -D -v  -t b_item  $DATABASE  >  $APATH/b_item.sql
$PGPATH/pg_dump -i -U  postgres  -F p -a -D -v  -t b_item_price  $DATABASE  >  $APATH/b_item_price.sql
$PGPATH/pg_dump -i -U  postgres  -F p -a -D -v  -t b_service_point  $DATABASE  >  $APATH/b_service_point.sql
$PGPATH/pg_dump -i -U  postgres  -F p -a -D -v  -t b_visit_ward  $DATABASE  >  $APATH/b_visit_ward.sql
$PGPATH/pg_dump -i -U  postgres  -F p -a -D -v  -t b_item_drug_uom  $DATABASE  >  $APATH/b_item_drug_uom.sql
$PGPATH/pg_dump -i -U  postgres  -F p -a -D -v  -t b_site  $DATABASE  >  $APATH/b_site.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/deleteTable.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/b_employee.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/b_item.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/b_item_price.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/b_service_point.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/b_visit_ward.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/b_item_drug_uom.sql
$PGPATH/psql $DATABASE -U postgres -f $APATH/b_site.sql
rm $APATH/b_employee.sql
rm $APATH/b_item.sql
rm $APATH/b_item_price.sql
rm $APATH/b_service_point.sql
rm $APATH/b_visit_ward.sql
rm $APATH/b_item_drug_uom.sql
rm $APATH/b_site.sql