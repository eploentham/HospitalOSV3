/*
 * Constant.java
 *
 * Created on 10 สิงหาคม 2548, 15:30 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.utility;
import java.util.HashMap;
/**
 *
 * @author tong(Padungrat)
 */
public class Constant {
    
    public final static HashMap Report  = new NameReport().getObject();
    public final static String[] REPORT_TYPE = {"text ไฟล์","xls ไฟล์"};
   /* public final static String[] REPORT_NAME = {"รายงาน 504 รายกลุ่ม","รายงาน 505 รายกลุ่ม","รายงาน 506 รายกลุ่ม",
                                                "รายงาน 504 รายโรค","รายงาน 505 รายโรค","รายงาน 506 รายโรค",
                                                "ข้อมูลติดตามผู้ป่วย 506","รายงาน 11รง5 ส่วนที่1","รายงาน 11รง5 ส่วนที่2",
                                                "รายงาน 11รง5 ส่วนที่4"};
    
    public final static String[] REPORT_NAME_EN = {"Report504Group","Report505Group","Report506Group",
                                                   "Report504Name","Report505Name","Report506Name",
                                                   "RP506Surveil","Report115Group1","Report115Group2",
                                                   "Report115Group4"};
    */
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
}
