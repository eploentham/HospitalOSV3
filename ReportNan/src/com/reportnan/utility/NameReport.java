/*
 * NameReport.java
 *
 * Created on 03 มิถุนายน 2549, 15:10 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.utility;
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
        report.ENG_NAME = "ReportResident";
        report.THAI_NAME = "รายงานจำนวนประชากร";
        report.DESCRIPTION = "รายงานจำนวนประชากร หลังคาเรือน ครอบครัว แยกตามหมู่บ้าน";
        
        theHashMapReport.put(report.INDEX,report );        
      
        report = null;
        
        report = new Report(); 
        
        report.INDEX = "2";
        report.ENG_NAME = "ReportPatientInClinic";
        report.THAI_NAME = "รายงานผู้ป่วยที่เข้ารับบริการคลินิกต่าง ๆ";
        report.DESCRIPTION = "รายงานผู้ป่วยที่เข้ารับบริการคลินิกต่าง ๆ";
        
        theHashMapReport.put(report.INDEX,report );        
      
        report = null;
        
        report = new Report(); 
        
        report.INDEX = "3";
        report.ENG_NAME = "ReportPatientOPDRemain";
        report.THAI_NAME = "รายงานลูกหนี้รายวันประเภทผู้ป่วยนอก";
        report.DESCRIPTION = "รายงานลูกหนี้รายวันประเภทผู้ป่วยนอก";
        
        theHashMapReport.put(report.INDEX,report );    
        
        report = null;
        
        report = new Report(); 
        
        report.INDEX = "4";
        report.ENG_NAME = "ReportAccident19Cause";
        report.THAI_NAME = "รายงานอุบัติเหตุ 19 สาเหตุ";
        report.DESCRIPTION = "รายงานอุบัติเหตุ 19 สาเหตุแบบฟอร์มของน่าน";
        
        theHashMapReport.put(report.INDEX,report );    
        
        report = null;
        
        report = new Report(); 
        
        report.INDEX = "5";
        report.ENG_NAME = "ReportEmergencyPatient";
        report.THAI_NAME = "รายงานผู้ป่วยฉุกเฉิน-ไม่ฉุกเฉิน";
        report.DESCRIPTION = "รายงานผู้ป่วยฉุกเฉิน-ไม่ฉุกเฉิน จำแนกตามเวรของ ER";
        
        theHashMapReport.put(report.INDEX,report ); 
    
        report = null;
        
        report = new Report();
        
        report.INDEX = "6";
        report.ENG_NAME = "ReportPatientOperated";
        report.THAI_NAME = "รายงานผู้ป่วยที่รับบริการหัตถการ";
        report.DESCRIPTION = "รายงานผู้ป่วยที่รับบริการหัตถการ แยกตามจุดบริการ";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        
        report = new Report();
        
        report.INDEX = "7";
        report.ENG_NAME = "ReportDailyOPDPatient";
        report.THAI_NAME = "รายงานประจำวัน งานผู้ป่วยนอก";
        report.DESCRIPTION = "รายงานประจำวัน งานผู้ป่วยนอก แยกตามจุดบริการ";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        
        report = new Report();
        
        report.INDEX = "8";
        report.ENG_NAME = "ReportIPDPatient";
        report.THAI_NAME = "รายงานผู้ป่วยใน";
        report.DESCRIPTION = "รายงานผู้ป่วยใน";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        
        report = new Report();
        
        report.INDEX = "9";
        report.ENG_NAME = "Report505INClinic";
        report.THAI_NAME = "รายงาน 505 แยกตามคลินิก";
        report.DESCRIPTION = "รายงานมาตรฐาน กลุ่มโรค 505 แยกตามคลินิก";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        
        report = new Report();
        
        report.INDEX = "10";
        report.ENG_NAME = "ReportPatientNCD";
        report.THAI_NAME = "รายงาน NCD";
        report.DESCRIPTION = "รายงาน NCD";
        
        theHashMapReport.put(report.INDEX,report );
    }
    
    
    
}
