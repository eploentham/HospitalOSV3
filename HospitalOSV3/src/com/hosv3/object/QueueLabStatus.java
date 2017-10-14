/*
 * QueueLabStatus.java
 *
 * Created on 23 กันยายน 2548, 17:22 น.
 */

package com.hosv3.object;

import com.hospital_os.utility.*;
/**
 * 
 * @author kingland
 * @deprecated ใช้ com.hospital_os.object.QueueLabStatus แทน
 */
public class QueueLabStatus {
    
    /** Creates a new instance of QueueLabStatus */
    public static String  NOLAB = "0";
    public static String  WAIT = "1";
    public static String  SOMEREPORT = "2";
    public static String  REPORT = "3";
    public static String  NOLAB_STR = "ไม่มีแลบ";
    public static String  WAIT_STR = "รอผล";
    public static String  SOMEREPORT_STR = "บางส่วน";
    public static String  REPORT_STR = "รายงาน";
    public QueueLabStatus() {
    }
    public static String getString(String code){
        if(code.equals(NOLAB))
            return (NOLAB_STR);
        else if(code.equals(WAIT))
            return (WAIT_STR);
        else if(code.equals(SOMEREPORT))
            return (SOMEREPORT_STR);
        else if(code.equals(REPORT))
            return (REPORT_STR);
        else
        {
            Constant.println("public static String getString(String code){ " + code);
            return "";
        }
    }
}
