set PATH=%PATH%;C:\Program Files\PostgreSQL\8.0\bin

psql hospital_osv3 -U postgres -f fileServer.sql > result.log

pause