/*
 * Report.java
 *
 * Created on 3 ตุลาคม 2548, 15:11 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.utility;

/**
 *  เป็น Object ของ รายงานทุกตัวที่จะต้องมีรายละเอียด ดังนี้
 *  ชื่อภาษาไทย
 *  ชื่อภาษาอังกฤษ
 *  คำอธิบาย
 *  Idex ของรายงาน
 * @author tong(Padungrat)
 */
public class Report {
    
    public String THAI_NAME;
    public String ENG_NAME;
    public String DESCRIPTION;
    public String INDEX;
    public Report() {
        THAI_NAME = "";
        ENG_NAME = "";
        DESCRIPTION = "";
        INDEX = "";
    }
    
    
}
