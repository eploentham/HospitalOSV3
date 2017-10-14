
if [ `whoami` != "root" ]; then
        echo "Must be root to install postgres's crontab"
        exit -1
fi

echo -n "Please Enter the path of JAVA Home [/usr/java/j2sdk1.4.2]:"
read JDK_HOME
    while [ ! -d "$JDK_HOME" ]
    do
        echo -n "Please Enter the path of JAVA Home [/usr/java/j2sdk1.4.2]:"
        read JDK_HOME
    done
    echo "JDK_HOME is $JDK_HOME"   

echo -n "Please Enter the path of your HospitalOS [/usr/local/hospitalOS/Hos3.7] : "
read APP_PATH
    while [ ! -d "$APP_PATH" ]
    do
        echo -n "Please Enter the path of your HospitalOS [/usr/local/hospitalOS/Hos3.7] : "
        read APP_PATH
    done
    echo "APP_PATH is $APP_PATH"   


echo "JDK_HOME=$JDK_HOME" > $APP_PATH/crontab/cron_cleanday.sh
echo "APP_PATH=$APP_PATH" >> $APP_PATH/crontab/cron_cleanday.sh

rm -rf $APP_PATH/crontab/cron_cleanday.sh
echo "cd $APP_PATH" >> $APP_PATH/crontab/cron_cleanday.sh
echo "$JDK_HOME/bin/java -cp $APP_PATH/lib/HospitalOSV3.jar:$APP_PATH/lib/standardHospitalOS.jar:$APP_PATH/lib/postgresql-8.1dev-400.jdbc3.jar com.hosv3.control.CleanTrans " >> $APP_PATH/crontab/cron_cleanday.sh
 
crontab -l -u postgres > /tmp/cron_old.cron
echo "01  0  *  *  * $APP_HOME/crontab/cron_cleanday.sh" >> /tmp/cron_old.cron
crontab -u postgres /tmp/cron_old.cron
chmod 755  $APP_PATH/crontab/cron_cleanday.sh

echo ""
echo "test run by  $APP_PATH/crontab/cron_cleanday.sh "
echo "Your backup clean day and year is in same cron"
echo "Already setup Hospital OS crontab."
echo "Thank you."
echo ""

