/*
 * RP506Surveil.java
 *
 * Created on 8 กันยายน 2548, 11:56 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.object;

import com.standardReport.subject.StandardObject;

/**
 *
 * @author americus
 */
public class RP506Surveil extends Persist implements StandardObject  
{
    public String rp506follow_hn; // HN
    public String rp506follow_prefix; // คำนำหน้า
    public String rp506follow_fname; // ชื่อ 
    public String rp506follow_lname; // สกุล
    public String rp506follow_sex; // เพศ
    public String rp506follow_age; // อายุ
    public String rp506follow_icd_code; // รหัส icd10
    public String rp506follow_startdate_treat; // วันที่เข้าป่วย
    public String rp506follow_status_treat; // สภาพผู้ป่วย
    public String rp506follow_address; // ที่อยู่ผู้ป่วย
    
    // update on 04 Jan 2006 
    // Author Ojika
    public String rp506follow_date_visit; // วันที่เข้ารับการรักษา
    public String rp506follow_age_year; // อายุ(ปี)
    public String rp506follow_age_month; // อายุ(เดือน)
    public String rp506follow_age_day; // อายุ(วัน)
    public String rp506follow_vn_an; // เลขที่ visit หรือ เลข admit
    public String rp506follow_father_name; // ชื่อสกุลบิดา
    public String rp506follow_mother_name; // ชื่อสกุลมารดา
    public String rp506follow_salf_doctor; // แพทย์ผู้ตรวจ
    public String rp506follow_code506; // รหัสกลุ่ม 506
    public String rp506follow_visit_dx; // การวินิจฉัยเบื้องต้นของแพทย์
    public String rp506follow_occupation; // อาชีพ
    public String rp506follow_nation; // สัญชาติ
    public String rp506follow_marriage; // สถานภาพสมรส
    public String rp506follow_visit_type; // ประเภทผู้ป่วย (ใน,นอก)
    /**
     * Creates a new instance of RP506Surveil 
     */
    public RP506Surveil()
    {        
    }

    public void setInitData()
    {
        rp506follow_hn = ""; 
        rp506follow_prefix = "";
        rp506follow_fname = "";
        rp506follow_lname = "";
        rp506follow_sex = "";
        rp506follow_age = "";
        rp506follow_icd_code = "";
        rp506follow_startdate_treat = "";
        rp506follow_status_treat = "";
        rp506follow_address = "";
        
        rp506follow_date_visit = ""; // วันที่เข้ารับการรักษา
        rp506follow_age_year = ""; // อายุ(ปี)
        rp506follow_age_month = ""; // อายุ(เดือน)
        rp506follow_age_day = ""; // อายุ(วัน)
        rp506follow_vn_an = ""; // เลขที่ visit หรือ เลข admit
        rp506follow_father_name = ""; // ชื่อสกุลบิดา
        rp506follow_mother_name = ""; // ชื่อสกุลมารดา
        rp506follow_salf_doctor = ""; // แพทย์ผู้ตรวจ
        rp506follow_code506 = ""; // รหัสกลุ่ม 506
        rp506follow_visit_dx = ""; // การวินิจฉัยเบื้องต้นของแพทย์
        rp506follow_occupation = ""; // อาชีพ
        rp506follow_nation = ""; // สัญชาติ
        rp506follow_marriage = ""; // สถานภาพสมรส
        rp506follow_visit_type = ""; // ประเภทผู้ป่วย (ใน,นอก)
    }

     public static String getStringField506followHN()
     {
         return "rp506follow_hn";
     }
     public static String getStringField506followPrefix()
     {
         return "rp506follow_prefix";
     }
     public static String getStringField506followFName()
     {
         return "rp506follow_fname";
     }
     public static String getStringField506followLName()
     {
         return "rp506follow_lname";
     }
     public static String getStringField506followICD()
     {
         return "rp506follow_icd_code";
     }
     public static String getStringField506followSex()
     {
         return "rp506follow_sex";
     }
     public static String getStringField506followAge()
     {
         return "rp506follow_age";
     }
     public static String getStringField506followStartdate()
     {
         return "rp506follow_startdate_treat";
     }
     public static String getStringField506followStatusTreat()
     {
         return "rp506follow_status_treat";
     }
     public static String getStringField506followAddress()
     {
         return "rp506follow_address";
     }
     
     public static String getStringField506followDateVisit()
     {
        return "rp506follow_date_visit";
     }
     
     public static String getStringField506followAgeYear()
     {
        return "rp506follow_age_year";
     }
     
     public static String getStringField506followAgeMonth()
     {
        return "rp506follow_age_month";
     }
     
     public static String getStringField506followAgeDay()
     {
        return "rp506follow_age_day";
     }
     
     public static String getStringField506followVnAn()
     {
        return "rp506follow_vn_an";
     }
     
     public static String getStringField506followFatherName()
     {
        return "rp506follow_father_name";
     }
     
     public static String getStringField506followMotherName()
     {
        return "rp506follow_mother_name";
     }
     
     public static String getStringField506followSalfDoctor()
     {
        return "rp506follow_salf_doctor";
     }
     
     public static String getStringField506followCode506()
     {
        return "rp506follow_code506";
     }
     
     public static String getStringField506followVisitDx()
     {
        return "rp506follow_visit_dx";
     }
     
     public static String getStringField506followOccupation()
     {
        return "rp506follow_occupation";
     }
     
     public static String getStringField506followNation()
     {
        return "rp506follow_nation";
     }
     
     public static String getStringField506followMarriage()
     {
        return "rp506follow_marriage";
     }
     
     public static String getStringField506followVisitType()
     {
        return "rp506follow_visit_type";
     }
                              
    public Object setInitDataFieldName()
    {        
        rp506follow_hn = this.getStringField506followHN();
        rp506follow_prefix = this.getStringField506followPrefix();
        rp506follow_fname = this.getStringField506followFName();
        rp506follow_lname = this.getStringField506followLName();
        rp506follow_sex = this.getStringField506followSex();
        rp506follow_age = this.getStringField506followAge();
        rp506follow_icd_code = this.getStringField506followICD();
        rp506follow_startdate_treat = this.getStringField506followStartdate();
        rp506follow_status_treat = this.getStringField506followStatusTreat();
        rp506follow_address = this.getStringField506followAddress();
        
        rp506follow_date_visit = this.getStringField506followDateVisit(); // วันที่เข้ารับการรักษา
        rp506follow_age_year = this.getStringField506followAgeYear(); // อายุ(ปี)
        rp506follow_age_month = this.getStringField506followAgeMonth(); // อายุ(เดือน)
        rp506follow_age_day = this.getStringField506followAgeDay(); // อายุ(วัน)
        rp506follow_vn_an = this.getStringField506followVnAn(); // เลขที่ visit หรือ เลข admit
        rp506follow_father_name = this.getStringField506followFatherName(); // ชื่อสกุลบิดา
        rp506follow_mother_name = this.getStringField506followMotherName(); // ชื่อสกุลมารดา
        rp506follow_salf_doctor = this.getStringField506followSalfDoctor(); // แพทย์ผู้ตรวจ
        rp506follow_code506 = this.getStringField506followCode506(); // รหัสกลุ่ม 506
        rp506follow_visit_dx = this.getStringField506followVisitDx(); // การวินิจฉัยเบื้องต้นของแพทย์
        rp506follow_occupation = this.getStringField506followOccupation(); // อาชีพ
        rp506follow_nation = this.getStringField506followNation(); // สัญชาติ
        rp506follow_marriage = this.getStringField506followMarriage(); // สถานภาพสมรส
        rp506follow_visit_type = this.getStringField506followVisitType(); // ประเภทผู้ป่วย (ใน,นอก)
        
        return this;
    }
    
}
