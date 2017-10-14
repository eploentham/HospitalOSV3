/*
 * ManageVitalSignResp.java
 *
 * Created on 17 พฤษภาคม 2548, 18:55 น.
 */

package com.hosv3.usecase.transaction;

/**
 *
 * @author  americus
 */
public interface ManageVitalResp {
    
/** Creates a new instance of createPatientAllergy  */
    public void notifyManageVitalSign(String str, int status);   
    
    /*บันทึกการแพ้ยา สำหรับเซ็ตค่าใน visittransfer pu*/
    public void notifyManagePhysicalExam(String str,int status);

    /*เพิ่มอาการเบื้องต้น และอาการสำคัญ ใน TextArea หน้า VitalSign รับ notify pu*/
    public void notifyManagePrimarySymptom(String str, int status);
    
    /*แพทย์ลง Diagnosis ที่หน้า Vital sign pu*/
    public void notifySaveDiagDoctor(String str,int status);
    
    /*ลบรายการ Map Dx pu*/
    public void notifyDeleteMapVisitDxByVisitId(String str,int status);
    
    /*แพทย์เพิ่มรายการโรค จาก panel Searchsub หน้า Vital รับ notify pu*/
    public void notifyAddDxTemplate(String str,int status);
    
     /*เมื่อมีการเพิ่มอาการปัจจุบันจากDialog searchsub -neung*/
    public void notifyAddPrimarySymptom(String str,int status);
    
  
}
