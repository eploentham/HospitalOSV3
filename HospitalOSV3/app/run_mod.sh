JDK_HOME=/data/app/j2sdk1.4.2_05
APP_HOME=/data/app/Hos3.7
cd $APP_HOME
echo "$JDK_HOME/bin/java -jar $APP_HOME/lib/ant-1.5.1.jar"
$JDK_HOME/bin/java -jar $APP_HOME/lib/ant-1.5.1.jar run_mod
