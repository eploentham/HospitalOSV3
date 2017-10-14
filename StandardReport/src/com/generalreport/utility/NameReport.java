/*
 * NameReport.java
 *
 * Created on 3 ตุลาคม 2548, 15:10 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.utility;
import java.util.*;
/**
 *
 * @author tong(Padungrat)
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
              
        report = null;
        report = new Report();
        
        report.INDEX = "1";
        report.ENG_NAME = "ReportDrugDispense";
        report.THAI_NAME = "รายงานปริมาณที่จ่ายยา";
        report.DESCRIPTION = "ปริมาณที่จ่ายยาตามช่วงเวลาที่กำหนด (ปริมาณ และ มูลค่า)";
        
        theHashMapReport.put(report.INDEX,report );        
      
        report = null;
        report = new Report();
        
        report.INDEX = "2";
        report.ENG_NAME = "ReportChronic";
        report.THAI_NAME = "รายงานผู้ป่วยโรคเรื้อรัง";
        report.DESCRIPTION = "ผู้ป่วยโรคเรื้อรัง (คน-ครั้ง) แยกเป็นในเครือข่าย นอกเครือข่าย";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "3";
        report.ENG_NAME = "ReportCostPayment";
        report.THAI_NAME = "รายงานมูลค่าการรักษา";
        report.DESCRIPTION = "มูลค่าการรักษาที่เกิดขึ้นจริงแยกประเภทตามกลุ่มใบเสร็จมาตรฐาน";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "4";
        report.ENG_NAME = "ReportRevenueAndExpense";
        report.THAI_NAME = "รายงานทางการเงิน";
        report.DESCRIPTION = "รายงานทางการเงินแยกเป็น รายรับเงินสด เรียกเก็บจากสิทธิ์ และสรุปค่าใช้จ่าย";
        
        theHashMapReport.put(report.INDEX,report );

        report = null;
        report = new Report();
        
        report.INDEX = "5";
        report.ENG_NAME = "ReportOutPatientPaymentINOUTCUP";
        report.THAI_NAME = "รายงานจำนวนผู้ป่วยนอก(คน/ครั้ง)";
        report.DESCRIPTION = "จำนวนผู้ป่วยนอก (คน/ครั้ง) ในเครือข่าย - นอกเครือข่าย แยกตามสิทธิการรักษา";
        
        theHashMapReport.put(report.INDEX,report );

        
        report = null;
        report = new Report();
        
        report.INDEX = "6";
        report.ENG_NAME = "ReportPatientInServicePoint";
        report.THAI_NAME = "รายงานรายชื่อผู้ป่วยที่ผ่านจุดบริการ";
        report.DESCRIPTION = "รายงานรายชื่อผู้ป่วยที่ผ่านจุดบริการ";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "7";
        report.ENG_NAME = "ReportClinicINOUTCup";
        report.THAI_NAME = "จำนวนผู้ป่วยนอกแยกตามแผนก (เช่น อายุรกรรม,ศัลยกรรม)";
        report.DESCRIPTION = "รายงานจำนวนผู้ป่วยนอกแยกตามแผนก (เช่น อายุรกรรม,ศัลยกรรม)";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "8";
        report.ENG_NAME = "ReportCostTotalGroupByOrder";
        report.THAI_NAME = "ค่าใช้จ่ายที่เกิดขึ้นจริง";
        report.DESCRIPTION = "ค่าใช้จ่ายที่เกิดขึ้นจริง แยกตามกลุ่มรายการ";
        
        theHashMapReport.put(report.INDEX,report );     
        

        report = null;
        report = new Report();
        
        report.INDEX = "9";
        report.ENG_NAME = "ReportARIC";
        report.THAI_NAME = "ARIC (อายุต่ำกว่า 5 ปี)";
        report.DESCRIPTION = "รายงาน ARIC (อายุต่ำกว่า 5 ปี)";
        
        theHashMapReport.put(report.INDEX,report ); 
        
        report = null;
        report = new Report();
        
        report.INDEX = "10";
        report.ENG_NAME = "ReportPlentyDisease";
        report.THAI_NAME = "อันดับโรคที่ผู้ป่วยเป็นโรคมากที่สุด";
        report.DESCRIPTION = "รายงานอันดับโรคที่ผู้ป่วยเป็นโรคมากที่สุด ตาม ICD10,ICD9 CM ตามช่วงเวลาที่กำหนด";
        
        theHashMapReport.put(report.INDEX,report ); 
        
        report = null;
        report = new Report();
        
        report.INDEX = "11";
        report.ENG_NAME = "ReportEyeDisease";
        report.THAI_NAME = "รายงานจำนวนผู้ป่วยโรคตา";
        report.DESCRIPTION = "รายงานจำนวนผู้ป่วยโรคตา แยกชาย,หญิง";
        
        theHashMapReport.put(report.INDEX,report ); 
        
        report = null;
        report = new Report();
        
        report.INDEX = "12";
        report.ENG_NAME = "ReportCostPaymentShareOFA7";
        report.THAI_NAME = "รายงานค่าใช้จ่ายที่สิทธิชำระให้แยกผู้ป่วยในนอก";
        report.DESCRIPTION = "รายงานค่าใช้จ่ายที่สิทธิชำระให้แยกผู้ป่วยใน/นอก";
        
        theHashMapReport.put(report.INDEX,report ); 
        
        report = null;
        report = new Report();
        
        report.INDEX = "13";
        report.ENG_NAME = "ReportPatientAdminAndDischarge";
        report.THAI_NAME = "รายงานผู้ป่วยในที่รับใหม่ และจำหน่าย";
        report.DESCRIPTION = "รายงานผู้ป่วยในที่รับใหม่ (Admit) และจำหน่าย (Disc) ตามช่วงที่กำหนด";
        
        theHashMapReport.put(report.INDEX,report ); 
        
        report = null;
        report = new Report();
        
        report.INDEX = "14";
        report.ENG_NAME = "ReportPatientOverService";
        report.THAI_NAME = "รายงานรายชื่อผู้ป่วยนอกที่เข้ารับบริการมากกว่าจำนวนครั้งที่ระบุ";
        report.DESCRIPTION = "รายงานรายชื่อผู้ป่วยนอกที่เข้ารับบริการมากกว่าจำนวนครั้งที่ระบุ(จำนวนการ visit)";
        
        theHashMapReport.put(report.INDEX,report ); 
        
        report = null;
        report = new Report();
        
        report.INDEX = "15";
        report.ENG_NAME = "ReportCostDrugInServicePoint";
        report.THAI_NAME = "จำนวนยาที่จ่ายไปในแต่ละช่วงเวลา";
        report.DESCRIPTION = "รายงานจำนวนยาที่จ่ายไปในแต่ละช่วงเวลา";
        
        theHashMapReport.put(report.INDEX,report ); 
        
        report = null;
        report = new Report();
        
        report.INDEX = "16";
        report.ENG_NAME = "ReportOrderLab";
        report.THAI_NAME = "จำนวนรายการ LAB ";
        report.DESCRIPTION = "จำนวนรายการ LAB (ตรวจเอง,ส่งตรวจ,รับตรวจ)";
        
        theHashMapReport.put(report.INDEX,report );
        
        report = null;
        report = new Report();
        
        report.INDEX = "17";
        report.ENG_NAME = "ReportAccident19Cause";
        report.THAI_NAME = "รายงานอุบัติเหตุ 19 สาเหตุ ";
        report.DESCRIPTION = "จำนวนผู้ป่วยอุบัติเหตุ 19 สาเหตุ";
        
        theHashMapReport.put(report.INDEX,report );
    }
    
    
    
}
