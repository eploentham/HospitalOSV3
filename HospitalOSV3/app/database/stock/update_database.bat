set PATH=%PATH%;C:\Program Files\PostgreSQL\8.0\bin
set DB=hospital_osv3
psql %DB% -U postgres -f 1_iMed-Schema.sql 
psql %DB% -U postgres -f 2_imed4hos.sql 
psql %DB% -U postgres -f 3_abc_ven.sql 
psql %DB% -U postgres -f 11_Rule.sql 
pg_dump -i -U  postgres  -F p -a -D -v  -t b_employee  %DB%  >  b_employee.sql
pg_dump -i -U  postgres  -F p -a -D -v  -t b_item  %DB%  >  b_item.sql
pg_dump -i -U  postgres  -F p -a -D -v  -t b_item_price  %DB%  >  b_item_price.sql
pg_dump -i -U  postgres  -F p -a -D -v  -t b_service_point  %DB%  >  b_service_point.sql
pg_dump -i -U  postgres  -F p -a -D -v  -t b_visit_ward  %DB%  > b_visit_ward.sql
pg_dump -i -U  postgres  -F p -a -D -v  -t b_item_drug_uom  %DB%  >  b_item_drug_uom.sql
pg_dump -i -U  postgres  -F p -a -D -v  -t b_site  %DB%  >  b_site.sql
psql %DB% -U postgres -f deleteTable.sql
psql %DB% -U postgres -f b_employee.sql
psql %DB% -U postgres -f b_item.sql
psql %DB% -U postgres -f b_item_price.sql
psql %DB% -U postgres -f b_service_point.sql
psql %DB% -U postgres -f b_visit_ward.sql
psql %DB% -U postgres -f b_item_drug_uom.sql
psql %DB% -U postgres -f b_site.sql
del b_employee.sql
del b_item.sql
del b_item_price.sql
del b_service_point.sql
del b_visit_ward.sql
del b_item_drug_uom.sql
del b_site.sql
pause