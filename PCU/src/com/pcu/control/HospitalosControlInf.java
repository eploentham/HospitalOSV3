/*
 * HospitalosControlInf.java
 *
 * Created on 7 กุมภาพันธ์ 2549, 10:46 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.control;
import javax.swing.*;
import java.util.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;
import com.hosv3.control.LookupControl;
import com.hosv3.control.BillingControl;
import com.hosv3.control.SetupControl;
import com.hosv3.control.VisitControl;
import com.hosv3.control.HosControl;
import com.hosv3.subject.HosSubject;
/**
 *
 * @author Jao
 */

public interface HospitalosControlInf {
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : บันทึกผู้ป่วย
     *@param : String=อายุ, Patient=Object ของผู้ป่วย
     */
    public int savePatient(Patient p,String age);
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : ปลดล็อคผู้ป่วย
     *@param : 
     */
    public void resetPatient();
        
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : ค้นหาผู้ป่วยจากเลขบัตรประชาชน
     *@param : String pid = เลขบัตรประชาชน
     */
    public void readPatientByPatientID(String pid);
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : ลบผู้ป่วย
     *@param : Patient=Object ของผู้ป่วย
     */
    public int deletePatient(Patient p);
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : หาค่า ConnectionInf ของ hosv3
     *@return : ConnectionInf ของ hosv3
     */
    public ConnectionInf getConnection();
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : ค้นหา Site 
     *@return : Object Site
     */
    public Site getSite();
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : ค้นหาคำนำหน้าชื่อ
     *@param : String ชื่อที่ใช้ในการค้นหา
     *@return : Object ของคำนำหน้าชื่อ
     */
    public Relation readRelationByName(String name);
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : บันทึกคำนำหน้าขื่อ
     *@param : Relation=Object ของคำนำหน้าชื่อ
     **/
    public void saveRelation(Relation r);
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : รับเวลาในปัจจุบัน
     *@return : String ของเวลา
     **/
    public String getTextCurrentDateTime();
    
    /**
     *@Author : jao
     *@date : 07/02/2549
     *@see : ค้นหารายการยา โดยใช้ key id ของยา
     *@param : itemid เป็น String ที่เก็บ key id ของยา
     */
    public Item listItemByPk(String itemid);   
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : แสดงคำเตือนที่ต้องการในแถบแสดงสถานะของ hosv3
     *@param : String=คำที่ต้องการแสดง, int=สถานะที่ต้องการแสดง เช่น normal=0,complete=1,waning=2,error=3
     */
    public void setStatus(String status,int istatus);
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : ค้นหาผู้ป่วยจากHN
     *@param : String=hn
     *@return : Vector ผู้ป่วย
     */
    public Vector listPatientByHn(String hn);
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : ค้นหาผู้ป่วยจากเลขบัตรประชาชน
     *@param : String=pid
     *@return : Vector ผู้ป่วย
     */
    public Vector listPatientByPID(String pid);
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : ค้นหาผู้ป่วยจากชื่อ
     *@param : String=name , String= fname , String lname
     *@return : Vector ผู้ป่วย
     */
    public Vector listPatientByName(String pname, String fname, String lname);
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : ค้นหาEmployeeจาก Primary Key
     *@param : String=pk
     *@return : Object ของ Employee
     */
    public Employee listEmployeeByPk(String pk);

    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : รับค่า JFram จาก Hosv3 
     *@return : JFram
     */
    public JFrame getJFrame();
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : รับ Object ของ Current Patient
     *@param : String=pk
     *@return : Object ของ Patient
     */
    public Patient getCurrentPatient();

    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : ค้นหาคำนำหน้าชื่อ
     *@param : String=id
     *@return : String คำนำหน้าชื่อ
     */
    public String listPrefix(String id);
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : รับ LookupControlInf จาก Hosv3     
     *@return : LookupControlInf
     */
    public LookupControlInf getVitalTemplate();
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : รับ LookupControl จาก Hosv3     
     *@return : LookupControl
     */
    public LookupControl getLookupControl();

    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : ค้นหาเลข ICD10    
     *@param : String=icd10Id
     *@return : Object ของ ICD10
     */
    public ICD10 listIcd10ById(String icd10Id);
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : รับวันที่ปัจุบันจาก Hosv3    
     *@return : String วันที่
     */

    public String getDateTime();
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : รับ BillingControl จาก Hosv3
     *@return : BillingControl
     */
    public BillingControl getBillingControl();
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : รับ Vector BillingPatient จาก Hosv3
     *@return : Vector
     */
    public Vector getvBillingPatient();
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : รับ Vector OrderItemReceiveDrug จาก Hosv3
     *@return : Vector
     */
    public Vector getvOrderItemReceiveDrug();

    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : รับ Vector PatientPayment จาก Hosv3
     *@param : String = patient_id
     *@return : Vector
     */
    public Vector listPatientPaymentByPatientId(String patient_id);
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : รับ SetupControl จาก Hosv3     
     *@return : SetupControl
     */
    public SetupControl getSetupControl();  
    
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : รับ VisitControl จาก Hosv3     
     *@return : VisitControl
     */
    public VisitControl getVisitControl();
    
      
    /**
     *@Author : jao 
     *@date : 07/02/2549
     *@see : แสดงข้อผู้ป่วย     
     *@param : String hn
     *@return : สถานะการแสดงผล
     */
    public int readPatientByHn(String hn);

    
    /**
     *@Author : jao 
     *@date : 27/02/2549
     *@see : รับ HosControl v3     
     *@param : 
     *@return : HosControl
     */
    public HosControl getHosControl();

    /**
     *@Author : jao 
     *@date : 27/02/2549
     *@see : บันทึก โรคเรื้อรัง
     *@param : 
     *@return : 
     */
    public void saveCronic(Chronic chronic);
    
    /**
     *@Author : jao 
     *@date : 27/02/2549
     *@see : แสดงสถานพยาบาลตามเลขที่ค้นหา
     *@param : String id
     *@return : Office 
     */
    public Office listOfficeByOffId(String id);
    
    /**
     *@Author : jao 
     *@date : 27/02/2549
     *@see : ค้าหา Icd10 จากเลข
     *@param : String code
     *@return : GroupIcd10 
     */
    public GroupIcd10 listGroupIcd10ByIcdCode(String code);
    
    /**
     *@Author : kingland
     *@date : 24/05/2549
     *@see : ส่งค่า ผู้ที่ใช้งานคนปัจจุบัน
     *@param : -
     *@return : Employee
     */
     public Employee getCurrentEmployee();
     
     /**
     *@Author : amp
     *@date : 27/05/2549
     *@see : คำระดับโภชนาการโดยใช้มาตรฐานใหม่
     *@param : -
     *@return : int
     */
     public int calculateNutrition(String sex,String month,String weight);
     
     /**
     *@Author : amp
     *@date : 27/05/2549
     *@see : คำระดับโภชนาการโดยใช้มาตรฐานใหม่
     *@param : -
     *@return : HosSubject
     */
     public HosSubject readHosSubject();
     
     /**
      *@Author : pu
      *@date : 18/09/2551
      *@see : แสดง Dialog ยืนยันสิ่งที่ต้องการของ hosv3
      *@param : String=คำที่ต้องการแสดง, int=สถานะที่ต้องการแสดง เช่น normal=0,complete=1,waning=2,error=3
      */
     public boolean confirmBox(String str,int status);
}

