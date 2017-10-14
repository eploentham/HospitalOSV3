/*
 * NameReport.java
 *
 * Created on 3 ตุลาคม 2548, 15:10 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.utility;
import java.util.*;
/**
 *
 * @author tong(Padungrat)
 */
public class NameReport {
    
    HashMap theHashMapReport;
    public NameReport() {
        theHashMapReport = new HashMap();
        setObject();
    }
    
    /**
     *  ส่งข้อมูลรายงาน
     */
    public HashMap getObject()
    {
        return theHashMapReport;
    }
    
    /**
     * ถ้ามีรายงานใหม่ เข้ามา ต้องมาเพิ่ม ส่วนนี้ด้วยทุกครั้ง
     */
    private void setObject()
    {
        Report report = new Report();
        
        report.INDEX = "1";
        report.ENG_NAME = "Report504Group";
        report.THAI_NAME = "รายงาน 504 รายกลุ่ม";
        report.DESCRIPTION = "เป็นรายงานที่แสดงจำนวนผู้ป่วยในกลุ่มโรค 504";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "2";
        report.ENG_NAME = "Report505Group";
        report.THAI_NAME = "รายงาน 505 รายกลุ่ม";
        report.DESCRIPTION = "เป็นรายงานที่แสดงจำนวนผู้ป่วยในกลุ่มโรค 505";
        
        theHashMapReport.put(report.INDEX,report );
     
        
        report = null;
        report = new Report();
        
        report.INDEX = "3";
        report.ENG_NAME = "Report506Group";
        report.THAI_NAME = "รายงาน 506 รายกลุ่ม";
        report.DESCRIPTION = "เป็นรายงานที่แสดงจำนวนผู้ป่วยในกลุ่มโรค 506";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "4";
        report.ENG_NAME = "Report504Name";
        report.THAI_NAME = "รายงาน 504 รายโรค";
        report.DESCRIPTION = "เป็นรายงานที่แสดงจำนวนผู้ป่วยรายโรคของกลุ่มโรค 504";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "5";
        report.ENG_NAME = "Report505Name";
        report.THAI_NAME = "รายงาน 505 รายโรค";
        report.DESCRIPTION = "เป็นรายงานที่แสดงจำนวนผู้ป่วยรายโรคของกลุ่มโรค 505";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "6";
        report.ENG_NAME = "Report506Name";
        report.THAI_NAME = "รายงาน 506 รายโรค";
        report.DESCRIPTION = "เป็นรายงานที่แสดงจำนวนผู้ป่วยรายโรคของกลุ่มโรค 506";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "7";
        report.ENG_NAME = "RP506Surveil";
        report.THAI_NAME = "ข้อมูลติดตามผู้ป่วย 506";
        report.DESCRIPTION = "เป็นรายงานที่แสดงข้อมูลติดตามผู้ป่วย โดยเลือกจากสถานะที่ต้องการ";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "8";
        report.ENG_NAME = "Report115Group1_2549";
        report.THAI_NAME = "รายงาน 11รง5 ส่วนที่1/2549";
        report.DESCRIPTION = "เป็นรายงานที่แสดงจำนวนผู้ป่วยที่เข้ารับบริการ ของผู้ป่วยนอก ผู้ป่วยใน ข้อมูลวันนอน ผู้ป่วย PCU และ ข้อมูลการส่งต่อ";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "9";
        report.ENG_NAME = "Report115Group2_2549";
        report.THAI_NAME = "รายงาน 11รง5 ส่วนที่2/2549";
        report.DESCRIPTION = "รายงานจำนวนผู้ป่วยที่เข้ามารับบริการโดยแยกตามประเภทที่เข้ารับบริการ";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "10";
        report.ENG_NAME = "Report115Group3_2549";
        report.THAI_NAME = "รายงาน 11รง5 ส่วนที่3/2549";
        report.DESCRIPTION = "รายงานข้อมูลการดูแลสุขภาพประชากร";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "11";
        report.ENG_NAME = "Report115Group4_2549";
        report.THAI_NAME = "รายงาน 11รง5 ส่วนที่4/2549";
        report.DESCRIPTION = "รายงานการตรวจคัดกรองและการออกหน่วย";
        
        theHashMapReport.put(report.INDEX,report );
    }
    
    
    
}
