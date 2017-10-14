/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hospital_os.object;

/**
 *
 * @author LionHearth
 */
public class AutoReportBug extends X39Persistent{
    final private String idtable = "";
    String initString = "";
    public String smtp_host = initString;
    public String user_name = initString;
    public String password = initString;
    public String mail_from = initString;
    public String mail_to = initString;
    public String message = initString;
    public AutoReportBug()
    {
    }
    public static AutoReportBug initConfig() {
        AutoReportBug dbObj = new AutoReportBug();
        dbObj.table = "b_auto_report_bug";
        dbObj.setObjectId("b_auto_report_bug_id");
        dbObj.pk_field = "b_auto_report_bug_id";
        dbObj.smtp_host = "report_bug_smtp_host";
        dbObj.user_name = "report_bug_user_name";
        dbObj.password = "report_bug_password";
        dbObj.mail_from = "report_bug_mail_from";
        dbObj.mail_to = "report_bug_mail_to";
        return dbObj;
    }
    @Override
    public X39Persistent getInstant(String[] strd) {
        AutoReportBug tmp = new AutoReportBug();
        tmp.setStringArray(strd);
        return tmp;
    }

    @Override
    public String[] getStringArray() {
        return new String[]{
                    getObjectId(), smtp_host, user_name, password, mail_from, mail_to
                };
    }

    @Override
    public void setStringArray(String[] array) {
        setObjectId(array[0]);
        smtp_host = array[1];
        user_name = array[2];
        password = array[3];
        mail_from = array[4];
        mail_to = array[5];
    }

    @Override
    public String getIdTable() {
        return this.idtable;
    }
}
