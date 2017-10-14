cd "C:\Program Files\HospitalOS\Hos3.7"
set PATH=%PATH%;C:\Program Files\PostgreSQL\8.0\bin
psql DATABASE_NAME -h DATABASE_SERVER -U postgres -f database/hstock/hstock_hos_060508.sql  > log.txt 
psql DATABASE_NAME -h DATABASE_SERVER -U postgres -f database/hstock/hstock_hos_090508.sql  > log.txt 
psql DATABASE_NAME -h DATABASE_SERVER -U postgres -f database/hstock/hstock_hos_280508.sql  > log.txt 
psql DATABASE_NAME -h DATABASE_SERVER -U postgres -f database/hstock/hstock_hos_060608.sql  > log.txt 
psql DATABASE_NAME -h DATABASE_SERVER -U postgres -f database/hstock/hstock_hos_110808.sql  > log.txt 
