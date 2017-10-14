/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hosv3.object;

import com.hospital_os.object.X39Persistent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LionHearth
 */
public class HosLog extends X39Persistent {
    public static final String idtable = "260";
    private static String initString = "";

    public String object_id = initString;
    public String object_note = initString;
    public String table_name = initString;
    public String uc_name = initString;
    public String ip_address = initString;
    public String record_staff = initString;
    public String record_date_time = initString;
    public String status = initString;

    public HosLog(int ucname){
        UseCase uc = UseCase.initUC(ucname);
        uc_name = uc.uc_name;
        table_name = uc.table_name; 
    }
    public HosLog(String uc,String table){
        uc_name = uc;
        table_name = table;
    }
    public HosLog(){
    }
    @Override
    public String getIdTable() {
        return idtable;
    }

    @Override
    public String[] getStringArray() {
    return new String[]{getObjectId()
        ,object_id
        ,object_note
        ,table_name
        ,uc_name
        ,ip_address
        ,record_staff
        ,record_date_time
        ,status};
    }

    @Override
    public void setStringArray(String[] array) {
        setObjectId(array[0]);
        object_id = array[1];
        object_note = array[2];
        table_name = array[3];
        uc_name = array[4];
        ip_address = array[5];
        record_staff = array[6];
        record_date_time = array[7];
        status = array[8];
    }

    @Override
    public X39Persistent getInstant(String[] strd) {
        HosLog hl = new HosLog();
        hl.setStringArray(strd);
        return hl;
    }
 
    public static HosLog initConfig()
    {
        HosLog dbObj = new HosLog();
        dbObj.table="t_hospitalos_log";
        dbObj.pk_field="t_hospitalos_log_id";
        dbObj.setObjectId("t_hospitalos_log_id");
        dbObj.object_id = "hospitalos_log_object_id";
        dbObj.object_note = "hospitalos_log_object_note";
        dbObj.table_name = "hospitalos_log_table_name";
        dbObj.uc_name = "hospitalos_log_uc_name";
        dbObj.ip_address = "hospitalos_log_ip_address";
        dbObj.record_staff = "hospitalos_log_record_staff";
        dbObj.record_date_time = "hospitalos_log_record_date_time";
        dbObj.status = "hospitalos_log_status";
        return dbObj;
    }
    public static void main(String[] argc){
        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println(ip.getHostAddress());
            System.out.println(ip.getHostName());
        } catch (UnknownHostException ex) {
            Logger.getLogger(HosLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
