set PATH=%PATH%;C:\Program Files\PostgreSQL\8.0\bin
set DB=stock_hosv37
psql %DB% -U postgres -f 1_iMed-Schema.sql
psql %DB% -U postgres -f 2_imed4hos.sql
psql %DB% -U postgres -f 3_abc_ven.sql
psql %DB% -U postgres -f 4_item_price.sql
psql %DB% -U postgres -f 5_base_service_point.sql
psql %DB% -U postgres -f 6_ward.sql
psql %DB% -U postgres -f 7_item.sql
psql %DB% -U postgres -f 8_base_site.sql
psql %DB% -U postgres -f 9_base_unit.sql
psql %DB% -U postgres -f 10_employee.sql
psql %DB% -U postgres -f 12_employee_role.sql
psql %DB% -U postgres -f 11_Rule.sql
pause