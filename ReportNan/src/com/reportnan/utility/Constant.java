/*
 * Constant.java
 *
 * Created on 10 สิงหาคม 2548, 15:30 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.utility;
import java.util.HashMap;
/**
 *
 * @author Noom
 */
public class Constant {
    public final static HashMap Report  = new NameReport().getObject();
    public final static String[] REPORT_TYPE = {"text ไฟล์","xls ไฟล์"};
    public final static String[] REPORT_NAME = {"รายงาน 18 แฟ้ม"};
    public final static String ENCODE_TH = "TIS-620";
    public final static String PIPE = "|";
    public final static String DASH = "-";
    public final static String TAB = "\t";
    public final static String NEWLINE = "\r\n";
    public final static String SELECTED = "1";
    public final static String NON_SELECTED = "0";
    public final static String TXT_FILE = "txt";
    public final static String XLS_FILE = "xls";
    public final static String DBF_FILE = "dbf";
    public final static String STATUS_CANCEL_REPORT = "ยกเลิกการออกรายงาน";
    public final static String [] DISCHARGE_STATUS = {"0","1","2","3","4","5"};
}
