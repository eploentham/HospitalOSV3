/*
 * QueueLabStatus.java
 *
 * Created on 23 �ѹ��¹ 2548, 17:22 �.
 */

package com.hosv3.object;

import com.hospital_os.utility.*;
/**
 * 
 * @author kingland
 * @deprecated �� com.hospital_os.object.QueueLabStatus ᷹
 */
public class QueueLabStatus {
    
    /** Creates a new instance of QueueLabStatus */
    public static String  NOLAB = "0";
    public static String  WAIT = "1";
    public static String  SOMEREPORT = "2";
    public static String  REPORT = "3";
    public static String  NOLAB_STR = "������ź";
    public static String  WAIT_STR = "�ͼ�";
    public static String  SOMEREPORT_STR = "�ҧ��ǹ";
    public static String  REPORT_STR = "��§ҹ";
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
