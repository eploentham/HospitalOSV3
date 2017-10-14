/*
 * PPControl.java
 *
 * Created on 28 กรกฎาคม 2548, 10:59 น.
 */

package com.pcu.control;

import com.hospital_os.object.MarryStatus;
import com.hospital_os.object.Prefix;
import com.hospital_os.object.Sex;
import com.hosv3.control.LookupControl;
import com.hosv3.control.PatientControl;
import java.util.*;
import java.text.*;
import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.Visit;
import com.hospital_os.object.Patient;
import com.hosv3.utility.connection.UpdateStatus;
import com.pcu.object.*;
import com.pcu.utility.*;

/**
 *
 * @author amp
 *@deprecated henbe unused , use AfterMCHMotherControl replace
 */
public class PPControl
{
    ConnectionInf theConnectionInf;
    HosDB thePcuDB;
    PCUObject thePO;
    UpdateStatus theUS;
    PatientControl thePatientControl;
    private Vector vVector;
    private Visit theVisit = new Visit();
    private Patient thePatient = new Patient();   

    private LookupControl theLookupControl;

    
    /** Creates a new instance of PPControl */
    public PPControl()
    {
    }
    
    public PPControl(ConnectionInf con,HosDB hdb,UpdateStatus us,PCUObject po)
    {
        theConnectionInf = con;
        thePcuDB = hdb;
        theUS = us;
        thePO = po;
    }
    public void setDepControl(PatientControl pc,LookupControl lc){
        thePatientControl = pc;
        theLookupControl = lc;
    }
    public PP readPPByPatientId(String search)
     {
        PP thePP = null;
        theConnectionInf.open();
        try{        
            thePP = thePcuDB.thePPDB.readPPByPatientId(search);                   
        }
        catch(Exception ex)
        {    
            ex.printStackTrace();           
        }
        finally
        {
            theConnectionInf.close();             
        } 
        return thePP;
     }
    public PP readPPByFamilyID()
    {
        return thePO.thePP;
    }
    /**
     * อ่านข้อมูลเด็กทารก จาก Family_id
     * @param  Family_id
     * @return  PP
     * @author kingland
     * @date 20-03-2549
    * @not deprecated henbe มันดึงมาตอนเลือกประชากรอยู่แล้วนะ
     * ใช้กับ dialog
     */ 
    public PP readPPByFamilyID(String family_id)
     {
        theConnectionInf.open();
        try{
            thePO.thePP = thePcuDB.thePPDB.readPPByFamilyID(family_id);                   
        }
        catch(Exception ex){    
            ex.printStackTrace();           
        }
        finally{
            theConnectionInf.close();             
        } 
        return thePO.thePP;
     }    
    /**
     *@deprecated henbe unused
     */
    public Vector listPPByMotherID(String family_id)
     {
        if(family_id.equals(""))
            return new Vector();
        
        theConnectionInf.open();
        try{
            return thePcuDB.thePPDB.selectByMotherPid(family_id);                   
        }
        catch(Exception ex){    
            ex.printStackTrace();           
            return new Vector();
        }
        finally{
            theConnectionInf.close();             
        } 
     }
    public boolean checkStatusVisitOfPP(String search)
     {
        theConnectionInf.open();
        boolean temp = false;
        try{                    
            if(theVisit != null){                    
                if(theVisit.visit_status.equals("2") || theVisit.visit_status.equals("3"))
                    temp = false;
                if(theVisit.visit_status.equals("1"))
                    temp = true;
            }            
            else
                temp = true;  /* add for notvisit 01/01/49 by jao*/
        }
        catch(Exception ex)
        {    
            ex.printStackTrace();           
        }
        finally
        {
            theConnectionInf.close();             
        }         
        return temp;
     }
    
    public PP saveOrUpdatePP(PP pp,Family fam,String sex_id) 
    {   
        
        if (thePO.getFamily() == null){
            theUS.setStatus("กรุณาเลือกประชากรหรือบันทึกข้อมูลประชากรก่อน",UpdateStatus.WARNING);
            return null;
        }
        if(thePO.getVisit()==null && pp.survey_date.length() != 10)
        {
            theUS.setStatus(GutilPCU.getTextBundle("WarningNotHaveSurVeyDate"), UpdateStatus.WARNING);
            return null;
        }
        if(("").equals(pp.pp_number))
        {
            theUS.setStatus(GutilPCU.getTextBundle("FillNumberPP"),UpdateStatus.WARNING);
            return null;
        }
        if(("").equals(pp.pp_gravida))
        {
            theUS.setStatus(GutilPCU.getTextBundle("FillGravida"),UpdateStatus.WARNING);
            return null;
        }
        if(pp.pp_mother_pid.equals("")){
            theUS.setStatus("กรุณาระบุเลขบัตรประชาชนของมารดาไม่เช้นนั้นโปรแกรมอาจแสดงความสัมพันธ์ผิดพลาด"
                    ,UpdateStatus.WARNING);
            return null;
        }
        if(!pp.pp_mother_pid.equals("") && pp.pp_mother_pid.length()!=13)
        {   
            theUS.setStatus(GutilPCU.getTextBundle("Pidlength"),UpdateStatus.WARNING);
            return null;
        }
        if(pp.pp_modify_date_time.length()<10)
        {
            theUS.setStatus("กรุณาระบุวันและเวลาที่บันทึก",UpdateStatus.WARNING);
            return null;
        }
        if(fam.f_sex_id.equals(Sex.isMAN())){
            theUS.setStatus("ผู้ป่วยปัจจุบันเป็นชาย กรุณาเลือกผู้ป่วยที่เป็นมารดาของเด็ก",UpdateStatus.WARNING);
            return null;
        }
        if(pp.pp_modify_date_time.length()<10){
            theUS.setStatus("กรุณาระบุวันที่บันทึก",UpdateStatus.WARNING);
            return null;
        }
        if(theLookupControl.isDateFuture(pp.pp_modify_date_time)){
            theUS.setStatus("กรุณาระบุวันที่บันทึกเป็นวันในอตีต",UpdateStatus.WARNING);
            return null;
        }
        theConnectionInf.open();
        try{
            
            if(thePO.getPatient() != null)
                pp.t_patient_id = thePO.getPatient().getObjectId();
            if(thePO.getVisit() != null)
                pp.t_visit_id = thePO.getVisit().getObjectId() ;    
            //ถ้าเลขบัตรประชาชนแม่เป็นเลขเดี่ยวกันกันผู้ป่วยแสดงว่าบันทึกข้อมูลของลูกจะต้อง
            //บันทึกประชากรใหม่ด้วยแล้วจึงระบุ family_id ของ pp เป็นของลูก
            pp.pp_staff_modify = thePO.getEmployee().getObjectId();
            if(pp.pp_modify_date_time.equals(""))
                pp.pp_modify_date_time = thePO.getCurrentDateTime();
            
            if(pp.getObjectId()==null){
                Family fm_child = new Family();
                fm_child.f_sex_id = sex_id;
                fm_child.patient_name = "บุตรนาง" + fam.patient_name;
                fm_child.patient_last_name = fam.patient_last_name;
                fm_child.f_prefix_id = Prefix.BOY;
                if(sex_id.equals(Sex.isWOMAN()))
                    fm_child.f_prefix_id = Prefix.GIRL;
                fm_child.patient_birthday_true = "1";
                fm_child.patient_birthday = pp.pp_record_date_time;
                fm_child.home_id = fam.home_id;
                fm_child.mother_firstname = fam.patient_name;
                fm_child.mother_lastname = fam.patient_last_name;
                fm_child.mother_fid = fam.getObjectId();
                fm_child.marriage_status_id = MarryStatus.SINGLE;
                fm_child.nation_id = fam.nation_id;
                fm_child.race_id = fam.race_id;
                fm_child.religion_id = fam.religion_id;
                fm_child.occupation_id = "000";
                thePatientControl.intSaveFamily(fm_child,null);
                pp.family_id = fm_child.getObjectId();
                //หากเพิ่งจะรู้เลขบัตรของแม่ก็ให้บันทึกลงไปตอนนี้
                if(fam.pid.equals("") && !pp.pp_mother_pid.equals(""))
                {
                    fam.pid = pp.pp_mother_pid;
                    thePcuDB.theFamilyDB.update(fam);
                }  
                pp.pp_staff_record = thePO.getEmployee().getObjectId();
                pp.pp_record_date_time = thePO.getCurrentDateTime();
                pp.pp_active = "1";
                thePcuDB.thePPDB.insert(pp);
            }
            else
                thePcuDB.thePPDB.update(pp); 
            
            thePO.thePP = pp;
            setStatus(GutilPCU.getTextBundle("SaveComplete"),UpdateStatus.COMPLETE);
            return pp;
       }
       catch(Exception ex)  {    
           setStatus(GutilPCU.getTextBundle("SaveNotComplete"),UpdateStatus.ERROR);
           ex.printStackTrace();           
           return null;
       }
       finally{
           theConnectionInf.close();             
       }
    }
    
    public Patient readPatientByHn(String search)
     {
        theConnectionInf.open();
        try{        
            int value = Integer.parseInt(search);
            DecimalFormat d=new DecimalFormat();
            d.applyPattern("000000");
            thePatient = thePcuDB.thePatientDB.selectByHn(d.format(value));
        }
        catch(Exception ex)
        {    
            ex.printStackTrace();           
        }
        finally
        {
            theConnectionInf.close();             
        } 
        return thePatient;
     }
    
    /*ยกเลิกเพราะเก็บ Pid ของแม่แทน
    public Patient readPatientByPatientId(String search)
     {
        theConnectionInf.open();
        try{        
            thePatient = thePcuDB.thePatientDB.selectByPK(search);                   
        }
        catch(Exception ex)
        {    
            ex.printStackTrace();           
        }
        finally
        {
            theConnectionInf.close();             
        } 
        return thePatient;
     }*/
    
    public int saveOrUpdatePPCare(PPCare ppCare) 
    {
       int result = 0 ;
       theConnectionInf.open();
       try{       
           if(ppCare.family_id.equals("")){
               setStatus("กรุณาเลือกประชากรก่อนการบันทึก",UpdateStatus.WARNING);
               return result;
           }  
            if(ppCare.pp_care_modify_date_time.length()<10){
                theUS.setStatus("กรุณาระบุวันที่บันทึก",UpdateStatus.WARNING);
                return 0;
            }
            if(theLookupControl.isDateFuture(ppCare.pp_care_modify_date_time)){
                theUS.setStatus("กรุณาระบุวันที่รับวัคซีนเป็นวันในอตีต",UpdateStatus.WARNING);
                return 0;
            }    
            if(ppCare.getObjectId() == null || "".equals(ppCare.getObjectId()))
            {
                result = thePcuDB.thePPCareDB.insert(ppCare);
            }
            else
            {
                result = thePcuDB.thePPCareDB.update(ppCare); 
            }
            thePO.vPPCare = thePcuDB.thePPCareDB.listPPCareByFamilyID(ppCare.family_id);
           if(result>0)
               setStatus(GutilPCU.getTextBundle("SaveComplete"),UpdateStatus.COMPLETE);
           return result;
       }
       catch(Exception ex)
       {    
            ex.printStackTrace();          
            setStatus("เกิดความผิดพลาดในการบันทึกข้อมูล",UpdateStatus.ERROR);
       }
       finally
       {    theConnectionInf.close();             
       }   
       return result;
    }
    
    public String readVnByVisitId(String search)
     {  String vn = "";
        if(search.equals(""))
            return "";
        theConnectionInf.open();
        try{        
            theVisit = thePcuDB.theVisitDB.selectByPK(search);
            if(theVisit != null)
            {
                vn = theVisit.vn;
            }
             
        }
        catch(Exception ex)
        {    
            ex.printStackTrace();           
        }
        finally
        {
            theConnectionInf.close();             
        } 
        return vn;
     }
    
    public Vector listPPCareByPatientId(String search)
    {
        vVector = new Vector();   
        theConnectionInf.open();
        try
        {  
            vVector = thePcuDB.thePPCareDB.listPPCareByPatientId(search);
        }
        catch(Exception ex)
        {    
            ex.printStackTrace();
            theConnectionInf.rollback();
        }       
        theConnectionInf.close();
        return vVector;        
    }
    public Vector listPPCareByFamilyID()
    {
        return thePO.vPPCare;
    }
    
  /**
    * @dnot eprecated henbe มันดึงมาตอนเลือกประชากรอยู่แล้วนะ
    *
    */
    public Vector listPPCareByFamilyID(String family_id)
    {
        theConnectionInf.open();
        try{  
            thePO.vPPCare = thePcuDB.thePPCareDB.listPPCareByFamilyID(family_id);
        }
        catch(Exception ex)
        {    
            ex.printStackTrace();
        }       
        theConnectionInf.close();
        return thePO.vPPCare;        
    }
    /**
     * แสดงข้อความเตือน
     * @param message = ข้อความที่ต้องการให้แสดง
     *        status = สถานะที่แสดง
     * @return void
     * @author kingland
     * @date 28/08/2549
     */
    private void setStatus(String message,int status)
    {   theUS.setStatus(message, status);
    }

    public Vector listPPCareByMotherID(String mother_id) {
        theConnectionInf.open();
        try{  
            if(!mother_id.equals(""))
                return thePcuDB.thePPDB.selectByMotherPid(mother_id);
            return new Vector();
        }
        catch(Exception ex) {    
            ex.printStackTrace();
            return null;
        }       
        finally{
            theConnectionInf.close();
        }
    }
    public Vector listPPCareByPatientID(String patient_id) {
        theConnectionInf.open();
        try{  
            if(!patient_id.equals(""))
                return thePcuDB.thePPDB.selectByPatientID(patient_id);
            return new Vector();
        }
        catch(Exception ex) {    
            ex.printStackTrace();
            return null;
        }       
        finally{
            theConnectionInf.close();
        }
    }
}
