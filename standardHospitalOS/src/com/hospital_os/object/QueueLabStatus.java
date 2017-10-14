/*
 * QueueLabStatus.java
 *
 * Created on 23 กันยายน 2548, 17:22 น.
 */

package com.hospital_os.object;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.utility.Constant;
/**
 *
 * @author  kingland
 */
public class QueueLabStatus {
    
    /** Creates a new instance of QueueLabStatus */
    public static String  NOLAB = "0";
    public static String  WAIT = "1";
    public static String  SOMEREPORT = "2";
    public static String  REPORT = "3";
    public static String  REMAIN = "4";
    
    public static String  NOLAB_STR = "ไม่มีแลบ";
    public static String  WAIT_STR = "รอผล";
    public static String  SOMEREPORT_STR = "บางส่วน";
    public static String  REPORT_STR = "รายงาน";
    public static String  REMAIN_STR = "ค้างผล";
    
    public static String  NOLAB_FN = "/com/hospital_os/images/lab_none.gif";
    public static String  WAIT_FN = "/com/hospital_os/images/lab_order.gif";
    public static String  SOMEREPORT_FN = "/com/hospital_os/images/lab_report.gif";
    public static String  REPORT_FN = "/com/hospital_os/images/lab_complete.gif";
    public static String  REMAIN_FN = "/com/hospital_os/images/lab_remain.gif";
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
        else if(code.equals(REMAIN))
            return (REMAIN_STR);
        else
        {
            Constant.println("public static String getString(String code){ " + code);
            return "";
        }
    }
    public static String getQStatus(int total, int report, int remain) {
        String status = "";
        if(total==0)
            status = QueueLabStatus.NOLAB;
        else if(report==0 && remain==0)
            status = QueueLabStatus.WAIT;
        else if(remain>0)
            status = QueueLabStatus.REMAIN;
        else if(report>0 && report<total && remain==0)
            status = QueueLabStatus.SOMEREPORT;
        else if(report==total)
            status = QueueLabStatus.REPORT;
        return status;
    }
}
