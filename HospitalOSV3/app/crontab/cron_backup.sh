#!/bin/bash
# File: cron_v3-1.0.sh
# Version: 1.0 
# Hospital OS crontab install for Hospital OS V3
# by Sothorn Rordkongtee RHCE <srk@hospital_os.com>
# 05 Dec 2003
# 06 Jan 2004 Edit Bug wrong PATH for unlock
# 29 Mar 2004 Add Postgres 7.1.3 backup PATH
# 31 Oct 2004 Modify Bug on Sunday
# 05 May 2005 Modify Check File, Directory and Database exist
# 07 Nov 2005 gzip Before write to disk
# 15 Nov 2005 Add Backup Daily & Edit wrong PATH check DB
# 17 Nov 2005 add to .sql befoe gzip

##########################################

# Check root

if [ `whoami` != "root" ]; then
        echo "Must be root to install postgres's crontab"
        exit -1
fi

# Check File Exist

if [ -f "/var/spool/cron/postgres" ]; then
   echo ""
   echo " ------------------------------------"
   echo -e '\033[31m| ### Postgres crontab exists!!!###  |\033[m'
   echo " ------------------------------------"
   echo ""
   echo "Are you sure to install new postgres's crontab?"
   echo ""
   echo "####!!!Please Backup Your PostgreSQL Before Answer Y!!!####" 
   echo ""
   echo -n "Please Answer Y or N : "
   read ans
      
   case "$ans" in
      Y)
       echo "Continue..."   
       ;;
      y)
       echo "Continue..." 
       ;;
      N)
       echo "Thank you."  
       exit -1
       ;;
      n)
       echo "Thank you."
       exit -1
       ;;
      *)
       echo "Please Answer   Y, y or N, n"
       exit -1 
esac

fi

##########################################################

echo ""
echo "********************************************"
echo " Crontab Install for Hospital OS Version 3 "
echo "********************************************"
echo ""
echo "PostgreSQL Version?"
echo ""
echo "1) PostgreSQL 7.3.2 or 7.4.6 with Redhat 9 or Fedora"
echo "   PostgreSQL HOME = /var/lib/pgsql"
echo "   PostgreSQL PATH = /usr/bin"	
echo ""
echo "2) PostgreSQL Compile (7.4.6) or 8.x"
echo "   PostgreSQL HOME = /usr/local/pgsql"
echo "   PostgreSQL PATH = /usr/local/pgsql/bin"  
echo ""
echo "3) Compile Other PATH"

echo ""
echo -n "Please Enter the number of your PostgreSQL Version.  1, 2 or 3 : "
read version

case "$version" in
  1)
     PGHOME=/var/lib/pgsql
     PGPATH=/usr/bin
     
     if [ ! -d "$PGHOME" ] && [ ! -f /usr/bin/psql ]; then
        echo "Not Found PostgreSQL "
        echo "Please Run Script Again and select the other version"
        exit -1   
     fi

     ;;

  2)
    PGHOME=/usr/local/pgsql
    PGPATH=/usr/local/pgsql/bin
    
     if [ ! -d "$PGHOME" ] && [ ! -f /usr/local/pgsql/bin/psql ]; then
        echo "Not Found PostgreSQL "
        echo "Please Run Script Again and select the other version"
        exit -1
     fi
     ;;

  3)
    echo "Please Enter Your PostgreSQL HOME"
    echo -n "Example : /var/lib/pgsql/pgsql-7.4.6 : "
    read PGHOME
    echo "Your PGHOME is $PGHOME" 
    
    while [ ! -d "$PGHOME" ]
    do
       echo -n "Your Directory Not Found Please Enter Again : "
       read PGHOME 
    done
    echo "PGHOME is $PGHOME"   

    PGPATH="$PGHOME/bin"  
    while [ ! -d "$PGPATH" ]
    do
       echo -n "Your Directory Not Found Please Enter Again : "
       read PGHOME
    done
    PGPATH="$PGHOME/bin" 


   ;;

   *)
     echo "Please Enter 1, 2 or 3  ."
     echo "Run script again!"
     exit 1
esac

#########################################################
echo ""   
echo "Please enter directory name that you want to keep backup files"
echo -n "Example /backup : "
read PGBACKUP
echo "Your backup directory is $PGBACKUP"

echo ""

### Check DB Exist
$PGPATH/psql -U postgres -l >/tmp/dbname.txt
echo -n "Please Enter Your Database's name : "
read DB
echo "";

FILE="grep $DB /tmp/dbname.txt" >/tmp/tmp.txt

if ! $FILE; then
   echo ""
   echo "Not Found Database : $DB "
   echo "Run this script again"
   echo ""
   exit -1
else 
   echo ""
   echo "Found Database : $DB"                                                                                


#################

PGBACKUP=$PGBACKUP/postgres_backup
mkdir -p $PGBACKUP/Sunday
mkdir -p $PGBACKUP/Monday
mkdir -p $PGBACKUP/Tuesday
mkdir -p $PGBACKUP/Wednesday
mkdir -p $PGBACKUP/Thursday
mkdir -p $PGBACKUP/Friday
mkdir -p $PGBACKUP/Saturday
mkdir -p $PGBACKUP/Daily
mkdir -p $PGHOME/util/backup
mkdir -p $PGHOME/util/clean
chown -R postgres:postgres $PGBACKUP
chown -R postgres:postgres $PGHOME/util/backup
chown -R postgres:postgres $PGHOME/util/clean
chown -R postgres:postgres $PGBACKUP 
# Backup
rm -f $PGHOME/util/backup/*.sh

echo "#!/bin/sh" > $PGHOME/util/backup/sunday.sh
echo "/bin/rm -f $PGBACKUP/Sunday/*" >> $PGHOME/util/backup/sunday.sh
echo "$PGPATH/pg_dump $DB | gzip -9 > $PGBACKUP/Sunday/\$(date +%Y%m%d).sql.gz" >> $PGHOME/util/backup/sunday.sh
echo "#!/bin/sh" > $PGHOME/util/backup/monday.sh
echo "/bin/rm -f $PGBACKUP/Monday/*" >> $PGHOME/util/backup/monday.sh
echo "$PGPATH/pg_dump $DB | gzip -9 > $PGBACKUP/Monday/\$(date +%Y%m%d).sql.gz" >> $PGHOME/util/backup/monday.sh
echo "#!/bin/sh" > $PGHOME/util/backup/tuesday.sh
echo "/bin/rm -f $PGBACKUP/Tuesday/*" >> $PGHOME/util/backup/tuesday.sh
echo "$PGPATH/pg_dump $DB | gzip -9 > $PGBACKUP/Tuesday/\$(date +%Y%m%d).sql.gz" >> $PGHOME/util/backup/tuesday.sh
echo "#!/bin/sh" > $PGHOME/util/backup/wednesday.sh
echo "/bin/rm -f $PGBACKUP/Wednesday/*" >> $PGHOME/util/backup/wednesday.sh
echo "$PGPATH/pg_dump $DB | gzip -9 > $PGBACKUP/Wednesday/\$(date +%Y%m%d).sql.gz" >> $PGHOME/util/backup/wednesday.sh
echo "#!/bin/sh" > $PGHOME/util/backup/thursday.sh
echo "/bin/rm -f $PGBACKUP/Thursday/*" >> $PGHOME/util/backup/thursday.sh
echo "$PGPATH/pg_dump $DB | gzip -9 > $PGBACKUP/Thursday/\$(date +%Y%m%d).sql.gz" >> $PGHOME/util/backup/thursday.sh
echo "#!/bin/sh" > $PGHOME/util/backup/friday.sh
echo "/bin/rm -f $PGBACKUP/Friday/*" >> $PGHOME/util/backup/friday.sh
echo "$PGPATH/pg_dump $DB | gzip -9 > $PGBACKUP/Friday/\$(date +%Y%m%d).sql.gz" >> $PGHOME/util/backup/friday.sh
echo "#!/bin/sh" > $PGHOME/util/backup/saturday.sh
echo "/bin/rm -f $PGBACKUP/Saturday/*" >> $PGHOME/util/backup/saturday.sh
echo "$PGPATH/pg_dump $DB | gzip -9 > $PGBACKUP/Saturday/\$(date +%Y%m%d).sql.gz" >> $PGHOME/util/backup/saturday.sh
echo "#!/bin/sh" > $PGHOME/util/backup/daily.sh
echo "/bin/rm -f $PGBACKUP/Daily/*" >> $PGHOME/util/backup/daily.sh
echo "$PGPATH/pg_dump $DB > $PGBACKUP/Daily/$DB.sql" >> $PGHOME/util/backup/daily.sh
chown postgres:postgres $PGHOME/util/backup/*.sh
chmod 755 $PGHOME/util/backup/*.sh

#Create VACUUM
rm -f $PGHOME/util/clean/vacuum.sh
echo "$PGPATH/psql -c \"VACUUM\" $DB" >  $PGHOME/util/clean/vacuum.sh
echo "$PGPATH/psql -c \"VACUUM ANALYZE\" $DB" >>  $PGHOME/util/clean/vacuum.sh
chmod 755 $PGHOME/util/clean/vacuum.sh
chown postgres:postgres $PGHOME/util/clean/vacuum.sh

#Create Crontab
rm -f $PGHOME/util/backup/cron_file
crontab -u postgres -r
echo "00 00 * * 0 $PGHOME/util/backup/sunday.sh" > $PGHOME/util/backup/cron_file
echo "00 00 * * 1 $PGHOME/util/backup/monday.sh" >> $PGHOME/util/backup/cron_file
echo "00 00 * * 2 $PGHOME/util/backup/tuesday.sh" >> $PGHOME/util/backup/cron_file
echo "00 00 * * 3 $PGHOME/util/backup/wednesday.sh" >> $PGHOME/util/backup/cron_file
echo "00 00 * * 4 $PGHOME/util/backup/thursday.sh" >> $PGHOME/util/backup/cron_file
echo "00 00 * * 5 $PGHOME/util/backup/friday.sh" >> $PGHOME/util/backup/cron_file
echo "00 00 * * 6 $PGHOME/util/backup/saturday.sh" >> $PGHOME/util/backup/cron_file
echo "00 00 * * * $PGHOME/util/backup/daily.sh" >> $PGHOME/util/backup/cron_file
echo "00 00 * * * $PGHOME/util/clean/vacuum.sh" >> $PGHOME/util/backup/cron_file
chown postgres:postgres $PGHOME/util/backup/cron_file
crontab -u postgres $PGHOME/util/backup/cron_file
echo ""
echo "Your backup database crontab files keep in :$PGHOME/util/backup"
echo "Your maintainance database crontab files keep in :$PGHOME/util/clean"
echo "Your database backup file will keep in :$PGBACKUP"
echo "Already setup Hospital OS crontab."
echo "Thank you."
echo ""
fi
