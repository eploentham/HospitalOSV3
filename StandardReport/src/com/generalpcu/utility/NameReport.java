/*
 * NameReport.java
 *
 * Created on 3 ตุลาคม 2548, 15:10 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalpcu.utility;
import java.util.*;
/**
 *
 * @author pu
 */
public class NameReport {
    
    HashMap theHashMapReport;
    public NameReport()
    {
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
        report.ENG_NAME = "ReportChronicPCU";
        report.THAI_NAME = "รายงานโรคเรื้อรัง";
        report.DESCRIPTION = "จำนวนผู้ป่วยโรคเรื้อรัง และ รายชื่อผู้ป่วยโรคเรื้อรัง ";
        
        theHashMapReport.put(report.INDEX,report );        
      
        report = null;
        report = new Report();
        
        report.INDEX = "2";
        report.ENG_NAME = "ReportNutritionPCU";
        report.THAI_NAME = "รายงานโภชนาการ";
        report.DESCRIPTION = "รายละเอียดโภชนาการของเด็กอายุ 0-5 ปีของแต่ละหมู่บ้าน ในเขตตำบลที่รับผิดชอบ";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "3";
        report.ENG_NAME = "ReportUncontagiousPCU";
        report.THAI_NAME = "รายงานโรคไม่ติดต่อ";
        report.DESCRIPTION = "จำนวนผู้ป่วยโรคไม่ติดต่อ และ รายชื่อผู้ป่วยโรคไม่ติดต่อ แยกตามหมู่บ้าน";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "4";
        report.ENG_NAME = "ReportResidentPCU";
        report.THAI_NAME = "รายงานประชากร";
        report.DESCRIPTION = "รายละเอียดของประชากรที่อยู่ในเขตรับผิดชอบ";
        
        theHashMapReport.put(report.INDEX,report );

        report = null;
        report = new Report();
        
        report.INDEX = "5";
        report.ENG_NAME = "ReportPregnancePCU";
        report.THAI_NAME = "รายงานการฝากครรภ์";
        report.DESCRIPTION = "รายชื่อหญิงตั้งครรภ์ ที่มารับบริการ ANC แยกตามหมู่บ้าน";
        
        theHashMapReport.put(report.INDEX,report );

        report = null;
        report = new Report();
       
        report.INDEX = "6";
        report.ENG_NAME = "ReportPersonMaimPCU";
        report.THAI_NAME = "รายงานผู้พิการ";
        report.DESCRIPTION = "รายชื่อผู้พิการ จำแนกตามประเภทความพิการ และหมู่บ้าน";
        
        theHashMapReport.put(report.INDEX,report );
      
        report = null;
        report = new Report();
        
        report.INDEX = "7";
        report.ENG_NAME = "ReportEpiPCU";
        report.THAI_NAME = "รายงานการรับวัคซีน";
        report.DESCRIPTION = "รายชื่อเด็กที่รับวัคซีน และ จำนวนของผู้รับวัคซีน";
        
        theHashMapReport.put(report.INDEX,report );     
        
        report = null;
        report = new Report();
        
        report.INDEX = "8";
        report.ENG_NAME = "ReportFpPCU";
        report.THAI_NAME = "รายงานการวางแผนครอบครัว";
        report.DESCRIPTION = "รายชื่อหรือจำนวนของผู้ที่ได้รับวางแผนครอบครัว และ รายชื่อผู้ที่ไม่ได้รับการวางแผนครอบครัว";
        
        theHashMapReport.put(report.INDEX,report );     
    
    }
    
    
    
}
