set PATH=%PATH%;C:\Program Files\PostgreSQL\8.0\bin
psql hospital_osv3 -U postgres -f update_module.sql
psql hospital_osv3 -U postgres -f image_update3.sql
pause