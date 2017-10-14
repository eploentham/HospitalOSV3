/*
 * VisitAControl.java
 *
 * Created on 20 ตุลาคม 2546, 13:33 น.
 */
package com.hosv3.control;
   
import com.hosv3.object.*;
import com.hosv3.subject.*;
import com.hosv3.utility.connection.*;
import com.hosv3.utility.*;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.object.specialQuery.*;
import com.hospital_os.object.*;
import com.hosv3.utility.Constant;
import com.pcu.object.*;
import com.hospital_os.utility.Gutil;

import java.util.*;
import java.util.logging.Logger;

/**
 *
 * @author  henbe
 */
public class DiagnosisControl {

    ConnectionInf theConnectionInf;
    HosDB theHosDB;
    LookupControl theLookupControl;
    HosObject theHO;
    HosSubject theHS;
    UpdateStatus theUS;
    SystemControl theSystemControl;
    public Family theFamily;
    public Patient thePatient;
    // henbe comment 230210 kong เวลาประกาศตัวแปรใหม่ขึ้นมาให้เขียนอธิบายด้วยว่าเอาไว้ใช้ทำอะไร
    /**
     * เอาไว้ใช้เก็บว่า ต้องการจะแสดง dialog ของโรคเรื้อรัง หรือ เฝ้าระวังหรือไม่
     * true =  แสดง
     * false = ไม่แสดง
     */
    //ปรับ pattern ให้เหมาะสม ตัวแปรตัวนี้ไม่จำเป็นต้องเป็น global
    //private boolean isShowDialog = true;
    private VisitControl theVisitControl;

    /** Creates a new instance of LookupControl */
    public DiagnosisControl(ConnectionInf con, HosObject ho, HosDB hdb, HosSubject hs){
        theConnectionInf = con;
        theHosDB = hdb;
        theHS = hs;
        theHO = ho;
    }
    public void setPatient(Patient patient)
    {
        thePatient = patient;
    }
    public void setFamily(Family family)
    {
        theFamily = family;
    }
    public void setSystemControl(SystemControl systemControl)
    {
        theSystemControl = systemControl;
    }
    public void setDepControl(LookupControl lc,VisitControl vc){
        theLookupControl = lc;
        theVisitControl = vc;
    }
    public void setUpdateStatus(UpdateStatus us){
        theUS = us;
    }
    
    public boolean deleteChronic(Chronic v,UpdateStatus theUS)
    {
        Constant.println(UseCase.UCID_deleteChronic);
        String objectid =   null;
        if(v==null) {
            theUS.setStatus(("ยังไม่มีข้อมูลโรคเรื้อรัง"),UpdateStatus.WARNING);
            return false;
        }
        if(true)
        {
           theUS.setStatus(Constant.getTextBundle("ข้อมูลนี้สัมพันธ์กับรหัสโรคที่บันทึก") + " " +
                   Constant.getTextBundle("กรุณายกเลิกรหัสโรคที่ลงไปแล้วระบบจึงจะทำการลบข้อมูลโรคเฝ้าระวังให้อัตโนมัติ")
                   ,UpdateStatus.WARNING);
           return false;
        }
        if(!theUS.confirmBox(Constant.getTextBundle("ข้อมูลนี้สัมพันธ์กับรหัสโรคที่บันทึกในการตรวจรักษา") + " " +
                Constant.getTextBundle("ยืนยันที่ลบข้อมูลโรคเรื้อรังของผู้ป่วยคนนี้ใช่หรือไม่"),UpdateStatus.WARNING)){
            return false;
        }
        
        theConnectionInf.open();
        try{
            theHosDB.theChronicDB.delete(v);
            if(v != null)
                objectid = v.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deleteChronic,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteChronic,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex) {    
            theSystemControl.setStatus(UseCase.TH_deleteChronic,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deleteChronic,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    public boolean deleteDeath(Death v,Visit visit,Patient patient,Family family,UpdateStatus theUS)
    {
        Constant.println(UseCase.UCID_deleteDeath);
        String objectid =   null;
        if(!v.vn_id.equals("") && visit.is_discharge_doctor.equals("1")){
            theUS.setStatus(("กรุณาย้อนกลับการรับบริการครั้งล่าสุดของผู้ป่วยแล้วจำหน่ายใหม่เพื่อยกเลิกการตาย")
                ,UpdateStatus.WARNING);
            return false;
        }
        boolean confirm = theUS.confirmBox(Constant.getTextBundle("ยืนยันการยกเลิกการตาย"), UpdateStatus.WARNING);
        if(!confirm){
            return false;
        }
        theConnectionInf.open();
        try {
            if(family!=null)
            {
                theHO.theFamily.discharge_status_id = Active.isDisable();
                theHO.theFamily.discharge_date_time = theHO.date_time;
                theHosDB.theFamilyDB.updateDischarge(theHO.theFamily);
            }
            if(patient!=null)
            {
                theHO.thePatient.discharge_status_id = "0";
                theHO.thePatient.discharge_date_time = theHO.date_time;
                theHosDB.thePatientDB.updatePatientDischar(theHO.thePatient);
            }
            v.active = Active.isDisable();
            theHosDB.theDeathDB.update(v);
            theUS.setStatus(Constant.getTextBundle("การยกเลิกการตาย") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            theHS.theVisitSubject.notifyDischargeDoctor(Constant.getTextBundle("การยกเลิกการตาย") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(v != null)
                objectid = v.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deleteDeath,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteDeath,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex){    
	     theSystemControl.setStatus(UseCase.TH_deleteDeath,UpdateStatus.ERROR,ex);
             theSystemControl.saveLog(UseCase.UCID_deleteDeath,objectid,ex,UpdateStatus.ERROR);
             return false;
        }
        finally{
            theConnectionInf.close();
        }
    }

    /**
     * amp:19/04/2549
     */
    public boolean deleteDiagnosisIcd10(Visit visit,Vector vDx10,int[] row)
    {
        Constant.println(UseCase.UCID_deleteDxIcd10);
        String objectid =   null;
        if(row.length==0){
            theUS.setStatus(("กรุณาเลือกรายการที่ต้องการลบ"),UpdateStatus.WARNING);
            return false;
        }
        if(!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบข้อมูลการลงรหัสโรครายการนี้ใช่หรือไม่")
        ,UpdateStatus.WARNING)){
            return false;
        }
        
        theConnectionInf.open();
        try  {  
            if(visit == null) {   
                theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
                return false;
            }
            for(int i=0;i<row.length;i++)
            {   
                DiagIcd10 diagicd10 = (DiagIcd10)vDx10.get(row[i]);
                if (diagicd10 == null){
                    theUS.setStatus(("ยังไม่มีข้อมูล ICD10"),UpdateStatus.WARNING);
                    continue;
                }
                int ret = intDeleteDiagIcd10(diagicd10,true);
                if(ret!=0)
                    return false;
            }
            //ตรวจสอบกรณีลบรายการ Icd10 หมด แล้วต้องเตือนผู้ใช้ตอนจำหน่ายทางการแพทย์
            theHO.vDiagIcd10 = theHosDB.theDiagIcd10DB.selectByVidSort(visit.getObjectId());  
            theHS.theDiagnosisSubject.notifyManageDiagIcd10(Constant.getTextBundle("การลบรหัสโรค") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deleteDxIcd10,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteDxIcd10,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex){    
             theHO.vDiadIcd10Cancel = null;
	     ex.printStackTrace(Constant.getPrintStream());
             theSystemControl.setStatus(UseCase.TH_deleteDxIcd10,UpdateStatus.ERROR,ex);
             theSystemControl.saveLog(UseCase.UCID_deleteDxIcd10,objectid,ex,UpdateStatus.ERROR);
             return false;
        }
        finally{   
            theConnectionInf.close();
        }
        
    }
    /**
     *ตรวจสอบได้ว่ามีปัญหาการลบข้อมูลจะให้ตรวจสอบผู้ลบได้ และจะยอมให้ลบก็ได้
     */
    public int intDeleteDiagIcd10(DiagIcd10 diagicd10,boolean can_delete)throws Exception
    {
        String cur_emp = theHO.theEmployee.getObjectId();
        if(cur_emp.equals(diagicd10.diag_icd10_staff_record) && !can_delete){
            theUS.setStatus(("ไม่สามารถลบข้อมูลการลงรหัสโรคของผู้อื่นได้"),UpdateStatus.WARNING);
            return 1;
        }
        diagicd10.diag_icd10_active = Active.isDisable();
        diagicd10.diag_icd10_cancel_date_time = this.theLookupControl.intReadDateTime();
        diagicd10.diag_icd10_staff_cancel = this.theHO.theEmployee.getObjectId();
        theHosDB.theDiagIcd10DB.update(diagicd10);

// pu:17/09/08        Surveil surveil = intCheckSurveil(theHO.thePatient.getObjectId(),diagicd10);
        //pu:ค้นหาว่าผู้ป่วยเคยมีข้อมูลโรคเฝ้าระวังในตาราง t_surveil หรือไม่
        Surveil surveil = theHosDB.theSurveilDB.selectByPatientIdAndIcd(theHO.thePatient.getObjectId(), diagicd10.icd10_code);
        if(surveil!=null && surveil.getObjectId()!=null)
        {
            if(surveil.vn_id.equals(diagicd10.visit_id))
                theHosDB.theSurveilDB.delete(surveil);
            else
            {
                surveil.active = "0";
                surveil.staff_cancel = theHO.theEmployee.getObjectId();
                surveil.cancel_date_time = theHO.date_time;
                theHosDB.theSurveilDB.update(surveil);                
            }
        }
        
// pu:17/09/08       Chronic chronic = intCheckChronic(theHO.thePatient.getObjectId(),diagicd10);
        //pu:ค้นหาว่าผู้ป่วยเคยมีข้อมูลโรคเรื้อรังในตาราง t_chronic หรือไม่
        Chronic chronic = theHosDB.theChronicDB.selectByPatientAndIcd(theHO.thePatient.getObjectId(), diagicd10.icd10_code);
        if(chronic!=null && chronic.getObjectId()!=null)
        {
            if(chronic.vn_id.equals(diagicd10.visit_id))
                theHosDB.theChronicDB.delete(chronic);
            else
            {
                chronic.active = "0";
                chronic.staff_cancel = theHO.theEmployee.getObjectId();
                chronic.cancel_datetime = theHO.date_time;
                theHosDB.theChronicDB.update(chronic);
            }
        }

        Uncontagious uncontagious = intCheckUncontagious(theHO.thePatient.family_id,diagicd10);
        if(uncontagious!=null
                && uncontagious.getObjectId()!=null
                && uncontagious.visit_id.equals(diagicd10.visit_id)){
            theHosDB.theUncontagiousDB.delete(uncontagious);
        }
        //ถ้ามีตัวอื่นที่อยู่ใน Vector เป็นโรคที่ไม่ซ้ำกับที่มีอยู่ ก็ให้ insert เข้าไป
//        DiagIcd10 diagicd10Temp = diagicd10; aut:พอลบแล้วมันจะ insert ตัวที่ลบเข้าไปอีก
//        uncontagious = intCheckUncontagious(theHO.thePatient.family_id,diagicd10Temp);
//        if(uncontagious!=null)
//            intSaveUncontagious(uncontagious);
        for (int i = 0; i < theHO.vDiagIcd10.size(); i++) {
            DiagIcd10 diagicd10Temp = (DiagIcd10)theHO.vDiagIcd10.get(i);
            if(diagicd10Temp.getObjectId().equals(diagicd10.getObjectId()))
                continue;
            uncontagious = intCheckUncontagious(theHO.thePatient.family_id,diagicd10Temp);
            if(uncontagious!=null) {
                intSaveUncontagious(uncontagious);
                break;
            }
        }        
        return 0;
    }
    
    public void deleteDiagnosisIcd9(Vector dx9,int row,Vector paticipateor) 
    {
        Constant.println(UseCase.UCID_deleteDxIcd9);
        String objectid =   null;
        if (row == -1){
            theUS.setStatus(("กรุณาเลือกรายการที่ต้องการลบ"),UpdateStatus.WARNING);
            return;
        }
        DiagIcd9 diagicd9 = (DiagIcd9)dx9.get(row);
        if(!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะยกเลิกรายการหัตถการรายการนี้ใช่หรือไม่"),UpdateStatus.WARNING)){
            return ;
        }
        theConnectionInf.open();
        try {
            intDeleteDiagnosisIcd9(diagicd9,paticipateor);
            theHS.theDiagnosisSubject.notifyManageDiagIcd9(Constant.getTextBundle("การลบรหัสหัตถการ") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(diagicd9!=null)
                objectid = diagicd9.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deleteDxIcd9,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteDxIcd9,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
	     ex.printStackTrace(Constant.getPrintStream());
             theSystemControl.setStatus(UseCase.TH_deleteDxIcd9,UpdateStatus.ERROR,ex);
             theSystemControl.saveLog(UseCase.UCID_deleteDxIcd9,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {   diagicd9 = null;
            theConnectionInf.close();
        }
    }        
    
    public void intDeleteDiagnosisIcd9(DiagIcd9 diagicd9,Vector paticipateor) throws Exception
    {
        if(diagicd9==null)
            return ;
        diagicd9.diag_icd9_active = Active.isDisable();
        diagicd9.diag_icd9_cancel_date_time = theLookupControl.intReadDateTime();
        diagicd9.diag_icd9_staff_cancel = this.theHO.theEmployee.getObjectId();
        theHosDB.theDiagIcd9DB.updateActive(diagicd9);
        theHO.vDiagIcd9 = theHosDB.theDiagIcd9DB.selectByVid(theHO.theVisit.getObjectId(),Active.isEnable());
        intDeleteParticipate(diagicd9,paticipateor);
    }
    
    public boolean deleteSurveil(Surveil v,UpdateStatus theUS)
    {
        if(v==null || v.getObjectId()==null)
        {
           theUS.setStatus(("ยังไม่มีข้อมูลโรคเฝ้าระวัง"),UpdateStatus.WARNING);
           return false; 
        }
        if(!v.vn_id.equals(theHO.theVisit.getObjectId()))
        {
           theUS.setStatus(("ระบบไม่อนุญาตให้แก้ไขข้อมูลการรับบริการครั้งก่อนได้"),UpdateStatus.WARNING);
           return false;
        }
        if(!theUS.confirmBox(Constant.getTextBundle("ข้อมูลนี้สัมพันธ์กับรหัสโรคที่บันทึกในการตรวจรักษา")+" "
            + Constant.getTextBundle("ยืนยันที่ลบข้อมูลโรคเรื้อรังของผู้ป่วยคนนี้ใช่หรือไม่"),UpdateStatus.WARNING)){
            return false;
        }
        theConnectionInf.open();
        try{
            theHosDB.theSurveilDB.delete(v);
            return true;
        }
        catch(Exception ex) {    
	     ex.printStackTrace(Constant.getPrintStream());
             return false;
        }
        finally{
            theConnectionInf.close();
        }
    }

   /* public Vector listChronicByIcd10(String pk) {
        Vector vc = new Vector();
        ChronicDB theChronicDB = new ChronicDB(theConnectionInf);
       theConnectionInf.open();
        try{
           vc = theChronicDB.selectChronicByIcd10(pk);
       theConnectionInf.close();
        }
        catch(Exception ex)
        { 
	  ex.printStackTrace(Constant.getPrintStream());
          theConnectionInf.close();
          return null;
        }
        return vc;
    }*/
    
    /** Creates a new instance of editChronicReq  */
    private int intSaveChronic(Chronic chronic, UpdateStatus theUS)throws Exception
    {
//        if(chronic==null)
//            return 1;
//        if(chronic.vn_id!=null && !chronic.vn_id.equals("")
//                && !chronic.vn_id.equals(theHO.theVisit.getObjectId()))
//        {
//           theUS.setStatus(("ระบบไม่อนุญาตให้แก้ไขข้อมูลการรับบริการครั้งก่อนได้"),UpdateStatus.WARNING);
//           return 1;
//        }
//        int date_valid = DateUtil.countDateDiff(chronic.date_dx
//                ,DateUtil.getTextCurrentDate(theConnectionInf));
//        if(date_valid >0){
//            theUS.setStatus(("วันที่เริ่มป่วยต้องไม่เป็นวันที่ในอนาคต"),UpdateStatus.WARNING);
//            return 2;
//        }
//        chronic.modify_datetime = theHO.date_time;
//        chronic.staff_modify = theHO.theEmployee.getObjectId();
//        if(chronic.getObjectId()==null){
//            chronic.active = Active.isEnable();
//            chronic.family_id = theHO.theFamily.getObjectId();
//            if(theHO.thePatient!=null){
//                chronic.patient_id = theHO.thePatient.getObjectId();
//                chronic.hn = theHO.thePatient.hn;
//            }
//            chronic.staff_record = theHO.theEmployee.getObjectId();
//            chronic.date_update = theHO.date_time;
//            if(theHO.theVisit!=null)
//            {
//                chronic.vn = theHO.theVisit.vn;
//                chronic.vn_id = theHO.theVisit.getObjectId();
//            }
//            theHosDB.theChronicDB.insert(chronic);
//        }
//        else{
//            theHosDB.theChronicDB.update(chronic);
//        }
//        return 0;
        return intSaveChronic(chronic, theUS, 0);
    }

    private int intSaveChronic(Chronic chronic, UpdateStatus theUS, int panel) throws Exception {
        if (chronic == null) {
            return 1;
        }
        if (panel == 0 && chronic.vn_id != null && !chronic.vn_id.equals("")
                && !chronic.vn_id.equals(theHO.theVisit.getObjectId())) {
            theUS.setStatus(("ระบบไม่อนุญาตให้แก้ไขข้อมูลการรับบริการครั้งก่อนได้"), UpdateStatus.WARNING);
            return 1;
        }
        int date_valid = DateUtil.countDateDiff(chronic.date_dx, DateUtil.getTextCurrentDate(theConnectionInf));
        if (date_valid > 0) {
            theUS.setStatus(("วันที่เริ่มป่วยต้องไม่เป็นวันที่ในอนาคต"), UpdateStatus.WARNING);
            return 2;
        }
        chronic.modify_datetime = theHO.date_time;
        chronic.staff_modify = theHO.theEmployee.getObjectId();
        if (chronic.getObjectId() == null) {
            chronic.active = Active.isEnable();
            if(theFamily==null)
                chronic.family_id = this.theHO.theFamily.getObjectId();
            else
                chronic.family_id = theFamily.getObjectId();
            if (theHO.thePatient != null) {
                chronic.patient_id = this.theHO.thePatient.getObjectId();
                chronic.hn = this.theHO.thePatient.hn;
            }
            chronic.staff_record = theHO.theEmployee.getObjectId();
            chronic.date_update = theHO.date_time;
            if (theHO.theVisit != null) {
                chronic.vn = theHO.theVisit.vn;
                chronic.vn_id = theHO.theVisit.getObjectId();
            }
            theHosDB.theChronicDB.insert(chronic);
        } else {
            theHosDB.theChronicDB.update(chronic);
        }
        return 0;
    }
    
    /**
     *@Author amp
     *@date 18/04/2549
     * @deprecated henbe unused non complex function not need tobe function
     */
    private void intSaveUncontagious(Uncontagious uncontagious)throws Exception 
    {
        if(uncontagious.getObjectId()==null)
            theHosDB.theUncontagiousDB.insert(uncontagious);
        else 
            theHosDB.theUncontagiousDB.update(uncontagious);
    }
    
    /** Creates a new instance of editDeathReq  */
    public String saveDeath(Death death,UpdateStatus theUS)
    {
        Constant.println(UseCase.UCID_saveDeath);
        String objectid =   null;
        if(death==null){
            theUS.setStatus(("ไม่มีข้อมูลการตายที่จะทำการบันทึก"),UpdateStatus.WARNING);
            return "";
        }
        int date_valid = 0;
        theConnectionInf.open();
        try{
            if(death.ddeath.length()<10){
                theUS.setStatus(("กรุณาระบุวัน เวลา ที่เสียชีวิต"),UpdateStatus.WARNING);
                return "";
            }
            if(death.ddeath.length() >= 16)
            {
                date_valid = DateUtil.countDateDiff(theLookupControl.intReadDateTime()
                ,death.ddeath);
            }
            if(date_valid<0){
                theUS.setStatus(("วันที่เสียชีวิตต้องไม่เป็นวันในอนาคต"),UpdateStatus.WARNING);
                return "";
            }        
            if(death.cdeath.length() > 6) {
                theUS.setStatus(("กรุณาระบุสาเหตุการตาย เป็นรหัส ICD-10 5 หลัก"),UpdateStatus.WARNING);
                return "";
            }
            if(death.odiseae.length() > 6) {
                theUS.setStatus(("กรุณาระบุโรคหรือภาวะอื่นที่เป็นเหตุหนุน เป็นรหัส ICD-10 5 หลัก"),UpdateStatus.WARNING);
                return "";
            }
            /////////////////////////////////////////////////
            if(death.getObjectId()==null)
            {
                if(theHO.thePatient!=null)
                {
                    theHO.thePatient.discharge_status_id = Active.isEnable();
                    theHO.thePatient.discharge_date_time = theHO.date_time;
                    theHosDB.thePatientDB.updatePatientDischar(theHO.thePatient);
                    death.patient_id = theHO.thePatient.getObjectId();
                }
                theHO.theFamily.discharge_status_id = Active.isEnable();
                theHosDB.theFamilyDB.updateDischarge(theHO.theFamily);
                death.family_id = theHO.theFamily.getObjectId();
                theHO.theDeath = death;
                theHosDB.theDeathDB.insert(death);
                
                if(theHO.theVisit!=null && theHO.theVisit.getObjectId().equals(death.vn_id)){
                    if(theHO.theVisit.visit_type.equals(VisitType.OPD))
                        theHO.theVisit.discharge_opd_status = DischargeOpd.DEATH_OPD;
                    else{
                        theHO.theVisit.discharge_ipd_type = DischargeType.DEATH;
                        theHO.theVisit.discharge_ipd_status = DischargeStatus.DEATH;
                    }
                    theHosDB.theVisitDB.updateDischargeDoctor(theHO.theVisit);
                }
            }
            else
            {
                //pu เก็บข้อมูลการตายขณะยังไม่จำหน่ายทางการเงิน
                theHO.theDeath = death;
                theHosDB.theDeathDB.update(death);
            }
            theUS.setStatus(Constant.getTextBundle("บันทึกข้อมูลการตาย") + " "+
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            theHS.thePatientSubject.notifySavePatient(Constant.getTextBundle("บันทึกข้อมูลการตาย") + " "+
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(death != null)
                objectid = death.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveDeath,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveDeath,objectid,null,UpdateStatus.COMPLETE);
            return "Complete";
        }
        catch(Exception ex)
        {    
	     theSystemControl.setStatus(UseCase.TH_saveDeath,UpdateStatus.ERROR,ex);
             theSystemControl.saveLog(UseCase.UCID_saveDeath,objectid,ex,UpdateStatus.ERROR);
             return "Error Exception";
        }
        finally
        {
            theConnectionInf.close();
        }
    }

    public ICD10 getICD10ByNumber(String code){
        theConnectionInf.open();
        try {
            return theHosDB.theICD10DB.selectEqCode(code);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        } finally {
            theConnectionInf.close();
        }
    }
    /*
     *private Surveil intCheckSurveil(DiagIcd10 dx10) throws Exception
     *นำรหัส ICD10 ที่บันทึกไปตรวจสอบกับตารางกลุ่มโรค ICD10
     * - ค้นข้อมูลโรคเฝ้าระวังของผู้ป่วยในรหัสโรคเดียวกัน และทำการ update วันที่รักษาให้เป็นวันปัจจุบัน หากไม่มีก็จะบันทึกข้อมูลโรค surveil ใหม่ให้
     * - ปลี่ยนให้มีการส่งค่าใหม่ไปให้เพื่อบันทึกเป็น record ใหม่เพราะว่าแฟ้มนี้จะต้องมีการเก้บเป็นประวัติไปเรื่อยๆ
     *@modifier pu
     *@date 17/09/2008
     */
    private Surveil intCheckSurveil(String patient_id,DiagIcd10 dx10) throws Exception
    {
        
        if(dx10.icd10_code.equals("")){
            return null;
        }
        //pu 15/10/2551 : หารหัสICD10 ในตาราง b_group_icd10
        String code3d = dx10.icd10_code.substring(0,3);
        Vector vIcd10SurveilAll = theHosDB.theGroupIcd10DB.selectByIcd(code3d);
        //pu: ถ้าไม่มี ICD10 ในตาราง b_group_icd10 แสดงว่าไม่ใช่โรคเฝ้าระวัง
        if(vIcd10SurveilAll == null || vIcd10SurveilAll.isEmpty()){
            return null;
        }
        GroupIcd10 groupicd10 = (GroupIcd10)vIcd10SurveilAll.get(0);
        //pu:ถ้ารหัสที่เจอไม่เป็นค่าว่าง ทำการบันทึกข้อมูลใน t_surveil ได้เลย
        if(groupicd10==null
                || groupicd10.group506.equals("")
                || groupicd10.group506.equals("99")){
            return null;
        }
        Surveil old_surveil = theHosDB.theSurveilDB.selectByPatientIdAndIcd(
                patient_id, dx10.icd10_code);
        //มีการปรับแก้เพิ่มคือให้ตรวจสอบในระดับ visit เท่านั้นหากไม่ซ้ำใน visit เดียวกันจะบันทึกให้ใหม่
        if(old_surveil!=null && old_surveil.vn_id.equals(theHO.theVisit.getObjectId())){
            return old_surveil;
        }
        //โปรแกรมจะตรวจสอบและเพิ่มรายการใหม่ให้เพื่อให้ข้อมูลเก่ายังคงอยู่
        Surveil yes = new Surveil();
        yes.vn_id = theHO.theVisit.getObjectId();
        yes.patient_id = theHO.thePatient.getObjectId();
        yes.family_id = theHO.theFamily.getObjectId();
        yes.hn = theHO.thePatient.hn;
        yes.vn = theHO.theVisit.vn;
        yes.icd_code = dx10.icd10_code;
        if(old_surveil == null || old_surveil.illdate.equals(""))
            yes.illdate = dx10.diagnosis_date;
        else
            yes.illdate = old_surveil.illdate;
        yes.patient_status = ChronicDischargeStatus.NoData;
        yes.complica = "";
        yes.organism = "";
        return yes;
    }

    /*
     *private Chronic intCheckChronic(DiagIcd10 dx10) throws Exception
     *นำรหัส ที่บันทึกไปตรวจสอบกับตารางที่ตั้งค่าโรคเรื้อรัง
     * - ค้นข้อมูล Chronic ของผู้ป่วยในรหัสโรคเดียวกัน และทำการ update วันที่รักษาให้เป็นวันปัจจุบัน หากไม่มีก็จะบันทึกข้อมูลโรค chronic ใหม่ให้
     *@modifier pu
     *@date 17/09/2008
     */
    private Chronic intCheckChronic(String patient_id,DiagIcd10 dx10) throws Exception
    {
        if(dx10.icd10_code.equals("")){
            return null;
        }
        //pu 16/10/2551 : หารหัสICD10 ในตาราง b_group_icd10 โดยที่กลุ่มโรคเรื้อรังต้องไม่ถูกยกเลิก
        String code3d = dx10.icd10_code.substring(0,3);
          Vector vcIcdChronicAll = theHosDB.theGroupIcd10DB.selectByChonicActive(code3d);
        //pu: ถ้าไม่มี ICD10 ในตาราง b_group_icd10 แสดงว่าไม่ใช่โรคเรื้อรัง
        if(vcIcdChronicAll == null || vcIcdChronicAll.isEmpty()){
            return null;
        }
        GroupIcd10 icd10_chronic = (GroupIcd10)vcIcdChronicAll.get(0);
        //pu:ถ้ารหัสที่เจอไม่เป็นค่าว่าง ทำการบันทึกข้อมูลใน t_chronic ได้เลย
        if(icd10_chronic==null || icd10_chronic.groupchronic.equals("")
                || icd10_chronic.groupchronic.equals("99")){
            return null;
        }
        Chronic old_chronic = theHosDB.theChronicDB.selectByPatientAndIcd(patient_id, dx10.icd10_code);
        if(old_chronic!=null && old_chronic.vn_id.equals(theHO.theVisit.getObjectId())) {
            return old_chronic;
        }
                
        //โปรแกรมจะตรวจสอบและเพิ่มรายการใหม่ให้เพื่อให้ข้อมูลเก่ายังคงอยู่
        Chronic yes = new Chronic();
        yes.family_id = theHO.theFamily.getObjectId();
        yes.hn = theHO.thePatient.hn;
        yes.patient_id = theHO.thePatient.getObjectId();
        yes.vn = theHO.theVisit.vn;
        yes.vn_id = theHO.theVisit.getObjectId();
        yes.chronic_icd = dx10.icd10_code;
        if(old_chronic == null || old_chronic.date_dx.equals(""))
            yes.date_dx = dx10.diagnosis_date;
        else
            yes.date_dx = old_chronic.date_dx;//วันที่เริ่มป่วยเอามาจากข้อมูลเดิม
        yes.type_dish = ChronicDischargeStatus.NoData;
        yes.date_update = "";
        yes.detail = "";
        return yes;
    }
    
    /*
     *@author amp
     *@date 18/04/2549
     *@see ตรวจสอบว่ารหัส Icd10 ที่บันทึกไปตรงกับโรคติดต่อหรือโรคไม่ติดต่ออะไรบ้าง
     *@param patient_id,DiagIcd10
     *@return Uncontagious
     */
    private Uncontagious intCheckUncontagious(String family_id,DiagIcd10 dx10) throws Exception
    {
        if(dx10.icd10_code.equals(""))
        {
            return null;
        }
        String key = dx10.icd10_code.substring(0,3);
        GroupIcd10 groupicd10 = theHosDB.theGroupIcd10DB.selectByIcdCode(key);
        if(groupicd10==null)
        {
            return null;
        }
        if("7830000000000".equals(groupicd10.group_disease))
        {
            return null;
        }
        Uncontagious yes = theHosDB.theUncontagiousDB.selectByFamilyIdAndDiseaseId(family_id,groupicd10.group_disease);
        if(yes==null)
        {
            yes = new Uncontagious();
            yes.active = Active.isEnable();
            yes.cancel_datetime = "";
            Disease di = theHosDB.theDiseaseDB.selectByPK(groupicd10.group_disease);
            if(di!=null)
                yes.contagious_type = di.isContagiousDisease;
            else
                yes.contagious_type = "";
            yes.disease_id = groupicd10.group_disease;
            yes.family_id = family_id;
            yes.getwell = "0";
            yes.icd10 = dx10.icd10_code;
            yes.modify_datetime = "";
            yes.patient_id = theHO.thePatient.getObjectId();
            yes.record_datetime = theLookupControl.intReadDateTime();
            yes.staff_cancel = "";
            yes.staff_modify = "";
            yes.staff_recode = theHO.theEmployee.getObjectId();
            yes.survey_date = "";
            yes.visit_id = theHO.theVisit.getObjectId();
        }
        return yes;
    }


    /**
     * pattern ที่ถูกต้องคือฟังชันที่เรียกลึกลงไปเรื่อย parameter จะต้องมากขึ้นเรื่อย
     * ส่วนฟังชันเดิมจะต้องคงไว้แล้วส่งค่า default ไปให้ฟังชันใหม่แทน
     * @deprecated used new interface function
     * @param diagicd10
     * @param vDx10
     * @param theUS
     * @param isShow
     * @return
     * @throws Exception
     */
    public int intSaveDiagIcd10(DiagIcd10 diagicd10,Vector vDx10
        ,UpdateStatus theUS) throws Exception{
        return intSaveDiagIcd10(diagicd10,vDx10,theUS,false);
    }
    /**
     *
     * pattern ที่ถูกต้องคือฟังชันที่เรียกลึกลงไปเรื่อย parameter จะต้องมากขึ้นเรื่อย
     * ส่วนฟังชันเดิมจะต้องคงไว้แล้วส่งค่า default ไปให้ฟังชันใหม่แทน
     * @param diagicd10
     * @param vDx10
     * @param theUS
     * @return
     * @throws Exception
     */
    public int intSaveDiagIcd10(DiagIcd10 diagicd10,Vector vDx10
        ,UpdateStatus theUS, boolean isShowDialog) throws Exception
    {
        String visit_id = theHO.theVisit.getObjectId();
        String patient_id = theHO.thePatient.getObjectId();
        String family_id = theHO.theFamily.getObjectId();
        
        if(diagicd10 == null || diagicd10.icd10_code.equals("")){
            theUS.setStatus(Constant.getTextBundle("กรุณาเลือกรหัสโรค ICD10") +
                    Constant.getTextBundle("...."),UpdateStatus.WARNING);
            return 1;
        }
        if(diagicd10.doctor_kid.equals("")){
            theUS.setStatus(("กรุณาระบุชื่อแพทย์"),UpdateStatus.WARNING);
            return 2;
        }
        Employee doctor = theLookupControl.readEmployeeById(diagicd10.doctor_kid);
        if(doctor!=null && !doctor.authentication_id.equals(Authentication.DOCTOR)){
            theUS.setStatus(("ผู้ลงรหัสต้องเป็นแพทย์เท่านั้น"),UpdateStatus.WARNING);
            return 3;
        }
        if(vDx10==null)
            vDx10 = theHosDB.theDiagIcd10DB.selectByVidSort(visit_id);
        if(checkCODEDx10Same(diagicd10,vDx10)){
            theUS.setStatus(("มีรหัสโรคที่บันทึกให้กับผู้ป่วยซ้ำกัน"),UpdateStatus.WARNING);
            return 4;
        }
        //ตรวจสอบว่า diagnosis icd10 นี้มี Primary มากกว่า 1 ตัวหรือไม่
        if(diagicd10.type.equals(Dxtype.getPrimaryDiagnosis())&& diagicd10.getObjectId()==null) {
            diagicd10.primary_report = "1";
            for(int i=0,size=vDx10.size();i<size;i++) {
                DiagIcd10 ic = (DiagIcd10)vDx10.get(i);
                //พบว่ามีค่ารหัสโรคเก่ามีค่าเป็น 1 แล้วออกรายงานหลักด้วยก็ต้องแก้ตัวนี้ให้เป็น 0
                if(ic.type.equals(Dxtype.getPrimaryDiagnosis())&&ic.primary_report.equals("1")){
                    diagicd10.primary_report = "0";
                    break;
                }
            }
        }
        if(diagicd10.icd10_code.startsWith("V")
        || diagicd10.icd10_code.startsWith("X")
        || diagicd10.icd10_code.startsWith("W")
        || diagicd10.icd10_code.startsWith("Y")) {
            if(diagicd10.type.equals(Dxtype.getPrimaryDiagnosis())) {
                theUS.setStatus(("ไม่สามารถบันทึกรหัสโรคในกลุ่ม ExternalCause ให้เป็นรหัสโรคหลักได้"),UpdateStatus.WARNING);
                return 7;
            }
        }
        ICD10 icd = theHosDB.theICD10DB.selectEqCode(diagicd10.icd10_code);
        if(icd==null){
            theUS.setStatus(("ไม่พบรหัส ICD10 ที่กรอกกรุณาค้นรหัสจากหน้าจอด้านซ้าย"),UpdateStatus.WARNING);
            return 7;
        }
        icd = theHosDB.theICD10DB.selectActive53(diagicd10.icd10_code);
        if(icd==null){
            if(!theUS.confirmBox(Constant.getTextBundle("รหัส") + " "+diagicd10.icd10_code+" " +
                    Constant.getTextBundle("ไม่ผ่านมาตรฐานรหัสโรคปี 53") + " " +
                    Constant.getTextBundle("ยืนยันการบันทึก"),UpdateStatus.WARNING))
                return 7;
        }
        //amp:03/01/2550:ตรวจสอบวันที่ในอนาคต
        try{
            if(theLookupControl.isDateFuture(diagicd10.diagnosis_date)) {
                theUS.setStatus(("วันและเวลาที่ลงรหัสโรค เป็นเวลาในอนาคตไม่ได้"),UpdateStatus.WARNING);
                return 9;
            }
        }
        catch(Exception e){
            Constant.println("diagicd10.diagnosis_date" + diagicd10.diagnosis_date);
            theUS.setStatus(("วันและเวลาที่ลงรหัสโรคไม่ถูกต้อง"),UpdateStatus.WARNING);
            return 8;
        }
        if(diagicd10.getObjectId()==null) 
        {
            diagicd10.visit_id = visit_id;
            if(diagicd10.diag_icd10_staff_record.equals(""))
                diagicd10.diag_icd10_staff_record = this.theHO.theEmployee.getObjectId();
            diagicd10.diag_icd10_record_date_time = theLookupControl.intReadDateTime();
            theHosDB.theDiagIcd10DB.insert(diagicd10);
            Surveil surveil = intCheckSurveil(patient_id,diagicd10);
            theHO.theSurveil = surveil;// SOmprasong add 241209
            intSaveSurveil(surveil,theUS);
            Chronic chronic = intCheckChronic(patient_id,diagicd10);
            theHO.theChronic = chronic;// SOmprasong add 241209
            intSaveChronic(chronic,theUS);
            //amp18/04/2549 ตรวจสอบกลุ่มโรคทั่วไป(ติดต่อหรือไม่ติดต่อ)
            //henbe 5/9/2549 ไม่ต้องตรวจสอบในกรณีที่เป็นอุบัติเหตุเพราะไม่ใช่โรคติดต่อแน่นอนโดย
            //มันจะส่ง null ใน family มาให้
            if(family_id!=null){
                Uncontagious uncontagious = intCheckUncontagious(family_id,diagicd10);
                if(uncontagious!=null) {
                    intSaveUncontagious(uncontagious);
                }
            }
            if(!isShowDialog){
                theHO.theSurveil = null;
                theHO.theChronic = null;
            }
        }
        else
        {
            diagicd10.visit_id = visit_id;
            if(diagicd10.diag_icd10_staff_update.equals(""))
                diagicd10.diag_icd10_staff_update = this.theHO.theEmployee.getObjectId();
            diagicd10.diag_icd10_update_date_time = theLookupControl.intReadDateTime();
            theHosDB.theDiagIcd10DB.update(diagicd10);
        }     
        return 0;
    }
    /*
     *
     *
     */
    public void saveDxByStat(Visit visit,String auth)
    {
        Constant.println(UseCase.UCID_saveDxByStat);
        String objectid =   null;
	if(visit == null){
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return;
        }
        if(!auth.equals(Authentication.STAT) && visit.is_discharge_doctor.equals(Active.isEnable()))   {
            theUS.setStatus(("การรับบริการของผู้ป่วยถูกจำหน่ายทางการแพทย์แล้ว"),UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try
        {
            if(auth.equals(Authentication.STAT))
            {
                theHosDB.theVisitDB.updateDxByStat(visit);
                theUS.setStatus(Constant.getTextBundle("การบันทึก Dx โดยเวชสถิติ") + " " +
                        Constant.getTextBundle("เสร็จสิ้น"), theUS.COMPLETE);
            }
            else
            {
                theUS.setStatus(("ไม่อนุญาตให้ผู้ใช้งานอื่นที่ไม่ใช่งานเวชสถิติบันทึกDx"), theUS.WARNING);
            }
            if(visit != null)
                objectid = visit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveDxByStat,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveDxByStat,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveDxByStat,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveDxByStat,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public int saveSurveil(Surveil s,UpdateStatus theUS)
    {
//        Constant.println(UseCase.UCID_saveSurveil);
//        String objectid =   null;
//        theConnectionInf.open();
//        try{
//            int ret = intSaveSurveil(s,theUS);
//            if(ret>0){
//                return ret;
//            }
//            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลโรคเฝ้าระวัง") + " " +
//                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
//            if(s != null)
//                objectid = s.getObjectId();
//            theSystemControl.setStatus(UseCase.TH_saveSurveil,UpdateStatus.COMPLETE,null);
//            theSystemControl.saveLog(UseCase.UCID_saveSurveil,objectid,null,UpdateStatus.COMPLETE);
//            return ret;
//        }
//        catch(Exception ex){
//            theSystemControl.setStatus(UseCase.TH_saveSurveil,UpdateStatus.ERROR,ex);
//            theSystemControl.saveLog(UseCase.UCID_saveSurveil,objectid,ex,UpdateStatus.ERROR);
//            return 99;
//        }
//        finally {
//            theConnectionInf.close();
//        }
        return saveSurveil(s, theUS, 0);
    }

    public int saveSurveil(Surveil s, UpdateStatus theUS, int panel) {
        Constant.println(UseCase.UCID_saveSurveil);
        String objectid = null;
        theConnectionInf.open();
        try {
            int ret = intSaveSurveil(s, theUS, panel);
            if (ret > 0) {
                return ret;
            }
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลโรคเฝ้าระวัง") + " "
                    + Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if (s != null) {
                objectid = s.getObjectId();
            }
            theSystemControl.setStatus(UseCase.TH_saveSurveil, UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_saveSurveil, objectid, null, UpdateStatus.COMPLETE);
            return ret;
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_saveSurveil, UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_saveSurveil, objectid, ex, UpdateStatus.ERROR);
            return 99;
        } finally {
            theConnectionInf.close();
        }
    }

    public int saveChronic(Chronic s,UpdateStatus theUS)
    {
//        Constant.println(UseCase.UCID_saveChronic);
//        String objectid =   null;
//        theConnectionInf.open();
//        try{
//            int ret = intSaveChronic(s,theUS);
//            if(ret>0){
//                return ret;
//            }
//            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลโรคเรื้อรัง")+" " +
//                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
//            if(s != null)
//                objectid = s.getObjectId();
//            theSystemControl.setStatus(UseCase.TH_saveChronic,UpdateStatus.COMPLETE,null);
//            theSystemControl.saveLog(UseCase.UCID_saveChronic,objectid,null,UpdateStatus.COMPLETE);
//            return 0;
//        }
//        catch(Exception ex){
//            theSystemControl.setStatus(UseCase.TH_saveChronic,UpdateStatus.ERROR,ex);
//            theSystemControl.saveLog(UseCase.UCID_saveChronic,objectid,ex,UpdateStatus.ERROR);
//            return -1;
//        }
//        finally {
//            theConnectionInf.close();
//        }
        return saveChronic(s, theUS, 0);
    }

    public int saveChronic(Chronic s,UpdateStatus theUS, int panel)
    {
        Constant.println(UseCase.UCID_saveChronic);
        String objectid =   null;
        theConnectionInf.open();
        try{
            int ret = intSaveChronic(s,theUS, panel);
            if(ret>0){
                return ret;
            }
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลโรคเรื้อรัง")+" " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(s != null)
                objectid = s.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveChronic,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveChronic,objectid,null,UpdateStatus.COMPLETE);
            return 0;
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_saveChronic,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveChronic,objectid,ex,UpdateStatus.ERROR);
            return -1;
        }
        finally {
            theConnectionInf.close();
        }
    }
   
    /**
     *
     *@modifier pu
     *@date 17/09/2008
     */
    private int intSaveSurveil(Surveil surveil,UpdateStatus theUS)throws Exception
    {
//        if(surveil==null)
//            return 5;
//        if(surveil.vn_id!=null && !surveil.vn_id.equals("")
//                && !surveil.vn_id.equals(theHO.theVisit.getObjectId()))
//        {
//           theUS.setStatus(("ระบบไม่อนุญาตให้แก้ไขข้อมูลการรับบริการครั้งก่อนได้"),UpdateStatus.WARNING);
//           return 1;
//        }
//        if(surveil.illdate.equals("")){
//            theUS.setStatus(("กรุณาระบุวันที่เริ่มป่วย"),UpdateStatus.WARNING);
//            return 2;
//        }
//        if(surveil.illdate.startsWith("20")){
//            theUS.setStatus(Constant.getTextBundle("วันที่เริ่มป่วย มีค่าไม่ถูกต้อง")+" "+ surveil.illdate
//                    +" "+Constant.getTextBundle("กรุณาตรวจสอบระบบ"),UpdateStatus.WARNING);
//            return 3;
//        }
//        if(surveil.illdate.compareTo(theHO.date_time)>0)
//        {
//            theUS.setStatus(("วันที่เริ่มป่วยต้องไม่เป็นวันที่ในอนาคต"),UpdateStatus.WARNING);
//            return 4;
//        }
//        surveil.modify_date_time = theHO.date_time;
//        surveil.staff_modify = theHO.theEmployee.getObjectId();
//        if(surveil.getObjectId()==null)
//        {
//            surveil.active = Active.isEnable();
//            surveil.staff_record = theHO.theEmployee.getObjectId();
//            surveil.record_date_time = theHO.date_time;
//            if(surveil.family_id.equals("")){
//                surveil.family_id = theHO.theFamily.getObjectId();
//            }
//            if(theHO.theVisit!=null){
//                surveil.vn = theHO.theVisit.vn;
//                surveil.vn_id = theHO.theVisit.getObjectId();
//            }
//            if(theHO.thePatient!=null)
//            {
//                surveil.patient_id = theHO.thePatient.getObjectId();
//                surveil.hn = theHO.thePatient.hn;
//            }
//            theHosDB.theSurveilDB.insert(surveil);
//        }
//        else {
//            theHosDB.theSurveilDB.update(surveil);
//        }
//        return 0;
        return intSaveSurveil(surveil, theUS, 0);
    }

    private int intSaveSurveil(Surveil surveil, UpdateStatus theUS, int panel) throws Exception {
        if (surveil == null) {
            return 5;
        }
        if (panel == 0 && surveil.vn_id != null && !surveil.vn_id.equals("")
                && !surveil.vn_id.equals(theHO.theVisit.getObjectId())) {
            theUS.setStatus(("ระบบไม่อนุญาตให้แก้ไขข้อมูลการรับบริการครั้งก่อนได้"), UpdateStatus.WARNING);
            return 1;
        }
        if (surveil.illdate.equals("")) {
            theUS.setStatus(("กรุณาระบุวันที่เริ่มป่วย"), UpdateStatus.WARNING);
            return 2;
        }
        if (surveil.illdate.startsWith("20")) {
            theUS.setStatus(Constant.getTextBundle("วันที่เริ่มป่วย มีค่าไม่ถูกต้อง") + " " + surveil.illdate
                    + " " + Constant.getTextBundle("กรุณาตรวจสอบระบบ"), UpdateStatus.WARNING);
            return 3;
        }
        if (surveil.illdate.compareTo(theHO.date_time) > 0) {
            theUS.setStatus(("วันที่เริ่มป่วยต้องไม่เป็นวันที่ในอนาคต"), UpdateStatus.WARNING);
            return 4;
        }
        surveil.modify_date_time = theHO.date_time;
        surveil.staff_modify = theHO.theEmployee.getObjectId();
        if (surveil.getObjectId() == null) {
            surveil.active = Active.isEnable();
            surveil.staff_record = theHO.theEmployee.getObjectId();
            surveil.record_date_time = theHO.date_time;
            if (surveil.family_id.equals("")) {
                surveil.family_id = theHO.theFamily.getObjectId();
            }
            if (theHO.theVisit != null) {
                surveil.vn = theHO.theVisit.vn;
                surveil.vn_id = theHO.theVisit.getObjectId();
            }
            if (theHO.thePatient != null) {
                surveil.patient_id = theHO.thePatient.getObjectId();
                surveil.hn = theHO.thePatient.hn;
            }
            theHosDB.theSurveilDB.insert(surveil);
        } else {
            theHosDB.theSurveilDB.update(surveil);
        }
        return 0;
    }
    
    /**
     * @roseuid 3F83EA240222
     *public void saveDiagnosisIcd9(Vector participateor,DiagIcd9 diagicd9,Vector dx9)
     */
    public void saveDiagnosisIcd9(Vector participateor,DiagIcd9 diagicd9,Vector dx9)
    {
       Constant.println("public void saveDiagnosisIcd9(Vector participateor,DiagIcd9 diagicd9,Vector dx9)");
       Constant.println(UseCase.UCID_saveDiagIcd9);
       boolean duplicate = false;
       for(Object object : dx9){
           DiagIcd9 icd9 = (DiagIcd9)object;
           if(icd9.icd9_code.equals(diagicd9.icd9_code) && !icd9.getObjectId().equals(diagicd9.getObjectId())){
               duplicate = true;
           }
       }
       if(duplicate){
           theUS.setStatus(("ไม่สามารถบันทึกรหัสซ้ำกันได้"),UpdateStatus.WARNING);
           return;
       }
       String objectid =   null;
       theConnectionInf.open();
       try{
            boolean result = intSaveDiagIcd9(participateor,diagicd9,dx9);
            if(!result)
                return;
            theHS.theDiagnosisSubject.notifyManageDiagIcd9(Constant.getTextBundle("การบันทึกรหัสหัตถการ") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(diagicd9 != null)
                objectid = diagicd9.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveDiagIcd9,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveDiagIcd9,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {   
	    theSystemControl.setStatus(UseCase.TH_saveDiagIcd9,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveDiagIcd9,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }

    public boolean intSaveDiagIcd9(Vector participateor,DiagIcd9 diagicd9,Vector dx9) throws Exception
    {
        if(theHO.theVisit == null) {
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return false;
        }
            if(diagicd9 == null)
            {
                theUS.setStatus(Constant.getTextBundle("ไม่พบข้อมูลการลงรหัสหัตถการ")+" "+
                        Constant.getTextBundle("กรุณาตรวจสอบ"),UpdateStatus.WARNING);
                return false;
            }
           if(diagicd9.icd9_code.equals("")){
                theUS.setStatus(("กรุณาเลือก ICD9 ที่ต้องการก่อนทำการบันทึก"),UpdateStatus.WARNING);
                return false;
           }
            if(diagicd9.time_in.length()<16)
            {
                theUS.setStatus(("กรุณาระบุวันและเวลาที่เริ่มหัตถการที่ต้องการก่อนทำการบันทึก"), theUS.WARNING);
                return false;
            }
            if(diagicd9.time_out.length()<16)
            {
                theUS.setStatus(("กรุณาระบุวันและเวลาที่สิ้นสุดหัตถการที่ต้องการก่อนทำการบันทึก"), theUS.WARNING);
                return false;
            }
            
            ///////////////////////////////////////////////////////////////////////////
            //henbe:07/06/2550:ตรวจสอบวันที่ในอนาคต
            try{
                if(theLookupControl.isDateFuture(diagicd9.time_in)) {
                    theUS.setStatus(("วันและเวลาที่เริ่มหัตถการ เป็นเวลาในอนาคตไม่ได้"),UpdateStatus.WARNING);
                    return false;
                }
            }
            catch(Exception e){
                theUS.setStatus(("วันและเวลาที่เริ่มหัตถการ ไม่ถูกต้อง"),UpdateStatus.WARNING);
                return false;
            }
            ///////////////////////////////////////////////////////////////////////////
            //henbe:07/06/2550:ตรวจสอบวันที่ในอนาคต
            try{
                if(theLookupControl.isDateFuture(diagicd9.time_out)) {
                    theUS.setStatus(("วันและเวลาที่สิ้นสุดหัตถการ เป็นเวลาในอนาคตไม่ได้"),UpdateStatus.WARNING);
                    return false;
                }
            }
            catch(Exception e){
                theUS.setStatus(("วันและเวลาที่สิ้นสุดหัตถการ ไม่ถูกต้อง"),UpdateStatus.WARNING);
                return false;
            }
            ///////////////////////////////////////////////////////////////////////////
            Date datein = DateUtil.getDateFromText(diagicd9.time_in);
            Date dateout = DateUtil.getDateFromText(diagicd9.time_out);
            if(datein != null && dateout != null)
            {
                boolean date_invalid = DateUtil.isDateTimeFuture(diagicd9.time_in,diagicd9.time_out);
                if(date_invalid)
                {
                    theUS.setStatus(("วันที่เริ่มหัตถการและวันที่สิ้นสุดหัตถการมีช่วงที่ไม่ถูกต้อง"),UpdateStatus.WARNING);
                    return false;
                }
            }
           if(checkCODEDx9Same(diagicd9,dx9)){
                theUS.setStatus(Constant.getTextBundle("กรุณากรอกรหัสหัตถการใหม่")+" "+
                        Constant.getTextBundle("เนื่องจากรหัสนี้มีการบันทึกอยู่แล้ว"),UpdateStatus.WARNING);
                return false;
           }
            if(diagicd9.doctor_kid==null || diagicd9.doctor_kid.equals("")){
                theUS.setStatus(("กรุณาระบุชื่อแพทย์"),UpdateStatus.WARNING);
                return false ;
            }
           String auth = theHO.theEmployee.authentication_id;
           diagicd9.vn = theHO.theVisit.getObjectId();
           if(!auth.equals(Authentication.STAT) && theHO.theVisit.is_discharge_doctor.equals(Active.isEnable()))   {
                theUS.setStatus(("จำหน่ายทางการแพทย์แล้ว"),UpdateStatus.WARNING);
                auth = null;
                return false;
           }
           if(diagicd9.type.equals(Active.isEnable()) && dx9!=null){
               DiagIcd9 ic = null; 
               for(int i=0,size=dx9.size();i<size;i++)
                {
                    ic = (DiagIcd9)dx9.get(i);
                    if(ic.type.equals(Active.isEnable())
                    && diagicd9.getObjectId()!=null 
                    && !diagicd9.getObjectId().equals(ic.getObjectId()))   
                    {   ic = null;
                        theUS.setStatus(("มีรายการหัตถการ Principal procedure 2 รายการไม่ได้"),UpdateStatus.WARNING);
                        return false;
                    }
                    if(ic.type.equals(Active.isEnable()) && diagicd9.getObjectId()==null )
                    {   ic = null;
                        theUS.setStatus(("มีรายการหัตถการ Principal procedure 2 รายการไม่ได้"),UpdateStatus.WARNING);
                        return false;
                    }
                    ic = null;
                }
           }
            diagicd9.diag_icd9_staff_update = this.theHO.theEmployee.getObjectId();
            diagicd9.diag_icd9_update_date_time  = theHO.date_time;
            if(diagicd9.getObjectId()==null){
                diagicd9.diag_icd9_record_date_time = theLookupControl.intReadDateTime();
                diagicd9.diag_icd9_staff_record  = this.theHO.theEmployee.getObjectId();
                theHosDB.theDiagIcd9DB.insert(diagicd9);
                if(dx9==null)
                    dx9 = new Vector();
                dx9.add(diagicd9);
            }
            else{
                theHosDB.theDiagIcd9DB.update(diagicd9);
            }
            theHO.vDiagIcd9 = theHosDB.theDiagIcd9DB.selectByVid(theHO.theVisit.getObjectId(),Active.isEnable());
            intSaveParticipateOr(participateor,diagicd9,theHO.theVisit);
            return true;
    }    
    public Vector listICD9ByIdName(String pk)
    {
        theConnectionInf.open();
        try {
            return theHosDB.theICD9DB.selectLikeCodeName("%"+pk+"%");
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    public Vector listDiagnosisIcd9(String vn)
    {
        Vector result = null;
        theConnectionInf.open();
        try
        {
            result = theHosDB.theDiagIcd9DB.selectByVid(vn,Active.isEnable());
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    
    public ICD9 listIcd9ByPk(String pk)
    {
        ICD9 ic9 = new ICD9();
        theConnectionInf.open();
        try
        {
            ic9 = theHosDB.theICD9DB.selectEqCode(pk);
        }
        catch(Exception ex)
        { 
	  ex.printStackTrace(Constant.getPrintStream());
          return null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return ic9;
    }
    
//    public Vector listIcd10ByIdAll(String ic)
//    {
//        Vector result = null;
//        theConnectionInf.open();
//        try
//        {
//            result = theHosDB.theICD10DB.selectById(ic);
//        }
//        catch(Exception ex)
//        {
//            ex.printStackTrace(Constant.getPrintStream());
//        }
//        finally         
//        {             
//            theConnectionInf.close();         
//        }
//        return result;
//    }
    public Vector listIcd10ByCode(String code)
    {
        return theLookupControl.listIcd10ByIdNameGroup(code,"",true);
    }

/**
 *สำหรับ list รายการ icd โดยเช็ค option ว่าใช้ icd10tm หรือไม่
 *@authur aut
 */    
//    public Vector listIcd10ByCode(String code)
//    {
//        Vector v;
//        if(Gutil.isSelected(theLookupControl.readOption().used_icd10_tm)) {
//            v = theLookupControl.listIcd10TMByIdName(code,true);
//        } else {
//            v = theLookupControl.listIcd10ByIdNameGroup(code,"",true);
//        }
//        return v;
//    }
    
/**
 *สำหรับ list รายการ icd โดยเช็ค option ว่าใช้ icd10tm หรือไม่
 *@authur aut
 */    
//    public Vector listIcd10ByIdNameGroup(String pk, String group)
//    {
//        Vector v;
//        if(Gutil.isSelected(theLookupControl.readOption().used_icd10_tm)) {
//            v = theLookupControl.listIcd10TMByIdName(pk,true);
//        } else {
//            v = theLookupControl.listIcd10ByIdNameGroup(pk,group,false);
//        }
//        return v;
//    }
    
    public Vector listIcd10ByIdNameGroup(String pk, String group)
    {
            return theLookupControl.listIcd10ByIdNameGroup(pk,group,false);
    }
    
    public Vector listDiagnosisIcd10(String vid)
    {
        theConnectionInf.open();
        try{
            return theHosDB.theDiagIcd10DB.selectByVidSort(vid);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    public ICD10 listIcd10ByPk(String pk)
    {
        ICD10 ic10 = new ICD10();
        theConnectionInf.open();
        try
        {
            ic10 = theHosDB.theICD10DB.selectByPK(pk);
        }
        catch(Exception ex)
        { 
	  ex.printStackTrace(Constant.getPrintStream());
          return null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return ic10;
    }
    
    public Dxtype listDxtypeByPk(String pk)
    {
        Dxtype dx = new Dxtype();
        theConnectionInf.open();
        try
        {
            dx = theHosDB.theDxtypeDB.selectByPK(pk);            
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return dx;
    }
    
    /**
     *@Author: sumo
     *@date: 2/6/2549
     *@see: ปรับแก้การลบผู้เข้าร่วมผ่าตัดโดยเก็บประวัตผู้ลบ, เวลาที่ลบ และสถานะของผู้เข้าร่วมผ่าตัดเป็น '0'
     */
    public void deleteParticipateOr(Vector participateor,int[] row)
    {
        Constant.println(UseCase.UCID_deleteParticipateOr);
        String objectid =   null;
        if(row.length==0){
            theUS.setStatus(Constant.getTextBundle("กรุณาเลือกผู้เข้าร่วม")+" "+
                    Constant.getTextBundle("ก่อนทำการลบผู้เข้าร่วม"),UpdateStatus.WARNING);
            return;
        }
        if(!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะยกเลิกพยาบาลผู้เข้าร่วม ใช่หรือไม่"),UpdateStatus.WARNING)){
            return ;
        }
        theConnectionInf.open();
        try{
            for(int i=row.length-1;i>=0;i--){
                ParticipateOr nurse =(ParticipateOr)participateor.get(row[i]);
                nurse.staff_update = theHO.theEmployee.getObjectId();
                nurse.time_update = theLookupControl.intReadDateTime();
                nurse.active = Active.isDisable();
                theHosDB.theParticipateOrDB.update(nurse);
                participateor.remove(row[i]);
            }
            theHS.theDiagnosisSubject.notifyDeleteParticipateOr(Constant.getTextBundle("การลบพยาบาลผู้เข้าร่วมผ่าตัด")
                    +" " + Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deleteParticipateOr,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteParticipateOr,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {    
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_deleteParticipateOr,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deleteParticipateOr,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Vector listParticipateOrByDiagIcd9(String dx9id)
    {
        Vector result = null;
        theConnectionInf.open();
        try{
            result = theHosDB.theParticipateOrDB.selectByDxIcd9(dx9id);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
        return result;
    }
    
    public Vector listParticipateOr(String code,String vn)
    {
        Vector result = null;
        theConnectionInf.open();
        try
        {
            result = theHosDB.theParticipateOrDB.selectByIcd9(code,vn);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    
    /**
     *@Author: sumo
     *@date: 2/6/2549
     *@see: ปรับแก้การเพิ่มผู้เข้าร่วมผ่าตัดโดยเก็บประวัตผู้เพิ่ม, เวลาที่เพิ่ม และสถานะของผู้เข้าร่วมผ่าตัดเป็น '1'
     */
    public void intSaveParticipateOr(Vector participateor
        ,DiagIcd9 diagicd9,Visit visit)  throws Exception
    {
        if(participateor==null || participateor.isEmpty()) {
            return;
        }
        ParticipateOr par = (ParticipateOr)participateor.get(0);
        Vector beforPar = theHosDB.theParticipateOrDB.selectByIcd9(par.icd9_id,visit.getObjectId());
        for(int n=0; n<participateor.size(); n++)
        {
            ParticipateOr part =(ParticipateOr)participateor.get(n);
            ((ParticipateOr)participateor.get(n)).t_diag_icd9_id = diagicd9.getObjectId();
            part.t_diag_icd9_id = diagicd9.getObjectId();
                //bad pattern ทำไมต้องทำอย่างนี้เก็บรหัสเพื่ออะไรกันในเมื่อเก็บ diag_id แล้วนี่นา
            ICD9 icd = theHosDB.theICD9DB.selectByCode(diagicd9.icd9_code);
            part.icd9_id = icd.getObjectId();
            part.vn = visit.getObjectId();
            part.staff_insert = theHO.theEmployee.getObjectId();
            part.time_insert = theLookupControl.intReadDateTime();
            part.active = Active.isEnable();
            if(beforPar==null)
            {
                theHosDB.theParticipateOrDB.insert(part);
            }
            else
            {
                int savePO= 0 ;
                int end = beforPar.size();
                participateor.remove(n);
                participateor.add(n,part);
                for(int i=0;  i< beforPar.size(); i++)
                {
                    if(((ParticipateOr)beforPar.get(i)).employee.equals(part.employee))
                    {                              
                        savePO=1;
                        i= end;
                    }
                }                   
                if(savePO==0)
                {
                    theHosDB.theParticipateOrDB.insert(part);
                }
            }
        }
    }
    
    public ParticipateOr listParticipateOrByEM(ParticipateOr o)
    {
        ParticipateOr po = new ParticipateOr();
        theConnectionInf.open();
        try
        {
            po = theHosDB.theParticipateOrDB.selectByEmAndIcd(o);
        }
        catch(Exception ex)
        { 
	  ex.printStackTrace(Constant.getPrintStream());
          return null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return po;
    }
    
    /**
     *@Author: sumo
     *@date: 2/6/2549
     *@deprecated henbe unused add in delete diagicd10 not call from gui
     *
     *@see: ปรับแก้การลบผู้เข้าร่วมผ่าตัดจากการลบ ICD9 โดยเก็บประวัตผู้ลบ, เวลาที่ลบ และสถานะของผู้เข้าร่วมผ่าตัดเป็น '0'
     */
    
    public void deleteParticipateOrByIcd(DiagIcd9 diagicd9,Vector paticipateor)
    {
        theConnectionInf.open();
        try{
            intDeleteParticipate(diagicd9,paticipateor);
        }
        catch(Exception ex){    
	     ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
             theConnectionInf.close();
        }
    }
    
    public void intDeleteParticipate(DiagIcd9 diagicd9,Vector paticipateor) throws Exception
    {
        if(paticipateor==null)
            return;
        for(int i= paticipateor.size()-1;i>=0;i--)
        {
            ParticipateOr nurse =(ParticipateOr)paticipateor.get(i);
            nurse.staff_update = theHO.theEmployee.getObjectId();
            nurse.time_update = theLookupControl.intReadDateTime();
            nurse.active = Active.isDisable();
            theHosDB.theParticipateOrDB.update(nurse);
        }
        if(paticipateor!=null)
        {
            paticipateor.removeAllElements();
        }
    }
    
    public Group506 listGroup506ByIcdCode(String code)
    {
        Group506 po = new Group506();
        theConnectionInf.open();
        try
        {
            po = theHosDB.theGroup506DB.selectByGroup506(code);
        }
        catch(Exception ex)
        { 
	  ex.printStackTrace(Constant.getPrintStream());
          return null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return po;
    }
    /**
     *@deprecated henbe bad function name
     */
    public GroupIcd10 listGroupIcd10ByIcdCode(String code)
    {
        return readGroupIcd10ByIcdCode(code);
    }
    public GroupIcd10 readGroupIcd10ByIcdCode(String code)
    {
        theConnectionInf.open();
        try
        {
            return theHosDB.theGroupIcd10DB.selectByIcdCode(code);
        }
        catch(Exception ex)
        { 
	  ex.printStackTrace(Constant.getPrintStream());
          return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Surveil listSurveil(String vn, String icd)
    {
        Surveil su = new Surveil();
        theConnectionInf.open();
        try
        {
            su = theHosDB.theSurveilDB.selectByVnAndIcd(vn, icd);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return su;
    }
    
    /*
     *function
     */
    public Surveil listSurveilByPatientIdAndIcd(String patientId, String icd)
    {
        Surveil su = new Surveil();
        theConnectionInf.open();
        try
        {
            su = theHosDB.theSurveilDB.selectByPatientIdAndIcd(patientId, icd);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return su;
    }
    
    public Vector listIdx10V3All(String pk)
    {
        Vector icd10v3 = new Vector() ;
        theConnectionInf.open();
        try
        {
            icd10v3 = theHosDB.theIdx10V3DB.selectByDescription(pk);
        }
        catch(Exception ex)
        {   
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return icd10v3;
    }
    
    public ICD10 listIcd10ById(String icd10Id)
    {
        ICD10 id = new ICD10();
        theConnectionInf.open();
        try
        {
            id = theHosDB.theICD10DB.selectByIdGroupOnly(icd10Id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return id;
    }
    
    public Vector selectMapVisitDx(String vn)
    {
        Vector v = new Vector();
        theConnectionInf.open();
        try
        {
            v = theHosDB.theMapVisitDxDB.selectByVisit(vn);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return v;
    }
    
    public DxTemplate selectDxTemplateByPk(String pk)
    {
        DxTemplate dt = new DxTemplate();
        theConnectionInf.open();
        try
        {
            dt = theHosDB.theDxTemplate2DB.selectByPK(pk);
        }
        catch(Exception ex)
        {    
		ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return dt;
    }
    public Vector listIcd10ByIdName(String ic)
    {
        Vector icd10 = new Vector() ;
        theConnectionInf.open();
        try
        {
            icd10 = theHosDB.theICD10DB.selectByIdName(ic);
        }
        catch(Exception ex)
        {   
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return icd10;
    }
    public Vector listDeath(String hn)
    {
        return listDeath("0000","9999",hn);
    }
    public Vector listDeath(String dateFrom, String dateTo, String hn)
    {
        
        if(dateFrom.equals("") && dateTo.equals("") && hn.equals("")){
            theUS.setStatus(("กรุณาระบุหมายเลข HN หรือช่วงวันที่ที่ต้องการค้นหาอย่างใดอย่างหนึ่ง"),UpdateStatus.WARNING);
            return null;
        }
        theConnectionInf.open();
        try{
                String sql="select t_death.*," +
                "t_health_family.patient_name ||'  '|| t_health_family.patient_last_name as family_name" +
                " from t_death " +
                        "left join t_health_family on t_health_family.t_health_family_id = t_death.t_health_family_id " + 
                        "left join t_patient on t_patient.t_patient_id = t_death.t_patient_id " + 
                        " where death_active  = '1'" +
                        " and (t_patient.patient_hn like '%" + hn + "' " +
                        " or t_health_family.patient_name like '" + hn + "%')";
                if(!dateFrom.equals("0000"))
                        sql+=" and death_date_time >= '" + dateFrom + "' " +
                        " and death_date_time <= '" + dateTo + ",23:59'";
                sql+= " order by family_name ";
                return theHosDB.theDeathDB.eQuery(sql);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally {
            theConnectionInf.close();
        }
    }
    
    public Vector listSurveilByHn(String hn)
    {
        return listSurveilByHnDate("0000","9999",hn);
    }
//    public Vector listSurveilByHnDate(String dateFrom,String dateTo, String hn)
//    {
//        Vector su = new Vector() ;
//        theConnectionInf.open();
//        int value;
//        try
//        {
//            if(hn.equals(""))
//            {
//                su = theHosDB.theSurveilDB.selectByHnDate(hn, dateFrom, dateTo);
//                return su;
//            }
//            else
//            {
//                value = Integer.parseInt(hn);
//                DecimalFormat d=new DecimalFormat();
//                d.applyPattern("000000000");
//                su = theHosDB.theSurveilDB.selectByHnDate(d.format(value), dateFrom, dateTo);
//                if(su==null)
//                {
//                    d.applyPattern("0000000");
//                    su = theHosDB.theSurveilDB.selectByHnDate(d.format(value), dateFrom, dateTo);
//                }
//            }
//        }
//        catch(Exception ex)
//        {
//            ex.printStackTrace(Constant.getPrintStream());
//            return null;
//        }
//        finally
//        {
//            theConnectionInf.close();
//        }
//        return su;
//    }
    public Vector listSurveilByHnDate(String dateFrom,String dateTo, String hn)
    {
        Vector su = new Vector() ;
        if(dateFrom.equals("") && dateTo.equals("") && hn.equals("")){
            theUS.setStatus(("กรุณาระบุหมายเลข HN หรือช่วงวันที่ที่ต้องการค้นหาอย่างใดอย่างหนึ่ง"),UpdateStatus.WARNING);
            return su;
        }
        theConnectionInf.open();
        int value;
        try{
            String sql="select * from t_surveil" +
                    " left join t_visit on t_visit.t_visit_id = t_surveil.t_visit_id where t_surveil_id <> ''";
            
            if(hn.trim().length() !=0)
                sql += " and surveil_hn like '%" + hn + "' ";
            
            if(!dateFrom.equals("") && !dateTo.equals("")){
                sql += " and substr(t_visit.visit_begin_visit_time,1,10) >= '" + dateFrom + "'" +
                " and substr(t_visit.visit_begin_visit_time,1,10) <= '" + dateTo + "'";
            }
//            sql += " order by surveil_hn,surveil_icd10_number "; // Somprasong comment
            sql += " order by surveil_hn,surveil_vn desc,surveil_icd10_number ";
            return theHosDB.theSurveilDB.eQuery(sql);        
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally {
            theConnectionInf.close();
        }
    }
    public Chronic listChronic(String hn, String icd)
    {
        Chronic cn = new Chronic();
        theConnectionInf.open();
        try
        {
            cn = theHosDB.theChronicDB.selectByHnAndIcd(hn, icd);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }finally
        {
            theConnectionInf.close();
        }
        return cn;
    }
    
     /*
      *function
      */
    public Chronic listChronicForDelete(String visit_id, String icd)
    {
        Chronic cn = new Chronic();
        theConnectionInf.open();
        try
        {
            cn = theHosDB.theChronicDB.selectByVisitIdAndIcd(visit_id, icd);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return cn;
    }
    public Vector listChronicByHn(String hn)
    {
        return listChronicByHnDate("","",hn);
    }
    public Vector listChronicByHnDate(String dateFrom, String dateTo ,String hn)
    {
        
        if(dateFrom.equals("") && dateTo.equals("") && hn.equals("")){
            theUS.setStatus(("กรุณาระบุหมายเลข HN หรือช่วงวันที่ที่ต้องการค้นหาอย่างใดอย่างหนึ่ง"),UpdateStatus.WARNING);
            return null;
        }
        theConnectionInf.open();
        int value;
        try  {
            return theHosDB.theChronicDB.selectByHnDate("%" + hn, dateFrom, dateTo);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally  {
            theConnectionInf.close();
        }
    }
    public Vector listChronicByPtidVid(String ptid,String vid)
    {

        theConnectionInf.open();
        try  {
            String sql = "select * from t_chronic"
            + " where t_patient_id = '" + ptid + "'";
            if(vid!=null)
                sql += " and t_visit_id = '" + vid + "'" ;
            sql += " order by chronic_vn,chronic_icd10";
            return theHosDB.theChronicDB.eQuery(sql);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally  {
            theConnectionInf.close();
        }
    }

    public Optype listOptypeByPk(String pk)
    {
        Optype op = new Optype();
        theConnectionInf.open();
        try
        {
            op = theHosDB.theOptypeDB.selectByPK(pk);
        }
        catch(Exception ex)
        {
	  ex.printStackTrace(Constant.getPrintStream());
          return null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return op;
    }
    
    public Death listDeathByVNHN(String vn_id, String patient_id)
    {
        theConnectionInf.open();
        try
        {
            return theHosDB.theDeathDB.selectByVNHN(vn_id, patient_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Transfer listDoctorTransferByVn(String vn)
    {
        Transfer tf = new Transfer();
        theConnectionInf.open();
        try
        {
            tf = theHosDB.theTransferDB.selectDoctorByVN(vn);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return tf;
    }
    
    public ICD10 readIcd10ByCode(String code)
    {
        ICD10 icd10=null;
        theConnectionInf.open();
        try
        {
            icd10 = theHosDB.theICD10DB.selectByCode(code);
        }
        catch(Exception e)
        {    e.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return icd10;
    }
    public ICD9 readIcd9ByCode(String code)
    {
        ICD9 icd9=null;
        theConnectionInf.open();
        try
        {
            icd9 = theHosDB.theICD9DB.selectByCode(code);
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return icd9;
    }
    /**
     * ojika
     * ค้นหาผู้ป้วยที่เป็นโรค Chronic ตามข้อมูลที่กำหนด
     **/
    public Vector listChronicByStatusDate(String dateStart, String dateEnd, String status)
    {
        Vector vListChronic = new Vector() ;
        theConnectionInf.open();
        try{
            Vector vcChronic = theHosDB.theChronicDB.selectByStatusDate(status, dateStart, dateEnd);
            for(int i=0;i<vcChronic.size();i++)
            {
                Chronic theChronic = (Chronic)vcChronic.get(i);   
                ChronicReport theChronicReport = new ChronicReport();
                if(theChronic != null)
                {
                    theChronicReport.hn = theChronic.hn; 
                    theChronicReport.vn = theChronic.vn; 
                    theChronicReport.patient_address = "";
                    theChronicReport.ban = "";
                    theChronicReport.moo = "" ;
                    theChronicReport.road = "";
                    theChronicReport.tambon = "";
                    theChronicReport.amphur = "";
                    theChronicReport.province = "";
                    if(theChronic.patient_id.equals(""))
                        continue;
                    Patient thePatient = theHosDB.thePatientDB.selectByPK(theChronic.patient_id);
                    if(thePatient==null)
                        continue;
                    {
                        theChronicReport.fname = thePatient.patient_name;
                        theChronicReport.lname = thePatient.patient_last_name;
                        // ที่อยู่ผู้ป่วย
                        String address = theLookupControl.intReadPatientAddress(thePatient);
                        if(!address.equalsIgnoreCase("null") && !address.equals("")) 
                        {
                            theChronicReport.patient_address = address;
                            theChronicReport.ban = thePatient.house;
                            theChronicReport.moo = thePatient.village;
                            theChronicReport.road = thePatient.road;
                            theChronicReport.tambon = theLookupControl.intReadAddressString(" ต.",thePatient.tambon);
                            theChronicReport.amphur = theLookupControl.intReadAddressString(" อ.",thePatient.ampur);
                            theChronicReport.province = theLookupControl.intReadAddressString(" จ.",thePatient.changwat);
                        }
                        theChronicReport.sex = theLookupControl.readSexSById(thePatient.f_sex_id);
                        theChronicReport.age = DateUtil.calculateAge(thePatient.getBirthDay(),theHO.date_time);
                        if(thePatient.getBirthDay() != null && !thePatient.getBirthDay().equals(""))
                        {
                            if(thePatient.patient_birthday_true.equals("0"))
                            {
                                String age = DateUtil.calculateAge(thePatient.getBirthDay(),theHO.date_time);
                                theChronicReport.age = age + " " + Constant.getTextBundle("ปี");
                                theChronicReport.age_year = age + " " + Constant.getTextBundle("ปี");
                                theChronicReport.age_month = "";
                                theChronicReport.age_day = "";
                            }
                            else
                            {
                                String age1 = DateUtil.calculateAgeLong(thePatient.getBirthDay(),theHO.date_time);
                                theChronicReport.age = age1;
                                theChronicReport.age_year = Constant.getYear(age1);
                                theChronicReport.age_month = Constant.getMonth(age1);
                                theChronicReport.age_day = Constant.getDay(age1);
                            }
                        }
                    }
                    theChronicReport.icd10 = theChronic.chronic_icd;
                    theChronicReport.date_dx = DateUtil.getDateToString(DateUtil.getDateFromText(theChronic.date_dx),false);
                    theChronicReport.date_discharge = theChronic.date_dish;                     
                    theChronicReport.status = Gutil.getVectorName(theLookupControl.listTypeDish(),theChronic.type_dish);
                    if(!theChronic.date_update.equalsIgnoreCase(""))
                    {
                        theChronicReport.date_update = DateUtil.getDateToString(DateUtil.getDateFromText(theChronic.date_update),false);  
                    }
                    else
                    {
                        theChronicReport.date_update = "";
                    }
                    theChronicReport.patient_id = theChronic.patient_id;
                    theChronicReport.visit_id = theChronic.vn_id;
                    vListChronic.add(theChronicReport);
                }
            }
            return vListChronic;
        }
        catch(Exception ex)
        {  
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     * ojika
     * ค้นหาผู้ป้วยที่เป็นโรค Surveil ตามข้อมูลที่กำหนด
     **/
    public Vector listSurveilByStatusDate(String dateStart, String dateEnd, String status)
    {
        theConnectionInf.open();
        try{
            Vector vSurveil = theHosDB.theSurveilDB.selectByStatusDate(status, dateStart, dateEnd);
    // ดึงค่าจัดเก็บใน Object chronicReport
            Vector vListSurveil = new Vector();
            for(int i=0,size=vSurveil.size();i<size;i++)
            {
                Surveil theSurveil = (Surveil)vSurveil.get(i);
                SurveilReport theSurveilReport = new SurveilReport();
                theSurveilReport.hn = theSurveil.hn;
                theSurveilReport.vn = theSurveil.vn;
                theSurveilReport.icd10 = theSurveil.icd_code;
                theSurveilReport.date_dx = DateUtil.getDateToString(DateUtil.getDateFromText(theSurveil.illdate),false);
                theSurveilReport.date_discharge = theSurveil.illdate;
                if(theSurveil.patient_id.equals(""))
                    continue;
                
                Patient thePatient = theHosDB.thePatientDB.selectByPK(theSurveil.patient_id);
                theSurveilReport.fname = thePatient.patient_name;
                theSurveilReport.lname = thePatient.patient_last_name;
                theSurveilReport.contact = thePatient.contact_fname + " " + thePatient.contact_lname;
                Relation rl = theHosDB.theRelationDB.selectByPK(thePatient.relation);
                theSurveilReport.relation = (rl!=null)?rl.description:"";
                // ที่อยู่ผู้ป่วย
                String address = theLookupControl.intReadPatientAddress(thePatient);
                if(!address.equalsIgnoreCase("null") && !address.equals("")) 
                {
                    theSurveilReport.patient_address = address;
                    theSurveilReport.ban = thePatient.house;
                    theSurveilReport.moo = thePatient.village;
                    theSurveilReport.road = thePatient.road;
                    theSurveilReport.tambon = theLookupControl.intReadAddressString(" ต.",thePatient.tambon);
                    theSurveilReport.amphur = theLookupControl.intReadAddressString(" อ.",thePatient.ampur);
                    theSurveilReport.province = theLookupControl.intReadAddressString(" จ.",thePatient.changwat);
                }
                theSurveilReport.sex = theLookupControl.readSexSById(thePatient.f_sex_id);
                if(thePatient.getBirthDay() != null && !thePatient.getBirthDay().equals(""))
                {
                    if(thePatient.patient_birthday_true.equals(Active.isDisable()))
                    {
                        String age = DateUtil.calculateAgeShort(thePatient.getBirthDay().substring(0,4),theHO.date_time.substring(0,4));
                        theSurveilReport.age = age + " ปี";
                        theSurveilReport.age_year = age + " ปี";
                        theSurveilReport.age_month = "";
                        theSurveilReport.age_day = "";
                    }
                    else
                    {
                        String age1 = DateUtil.calculateAgeLong(thePatient.getBirthDay(),theHO.date_time);
                        theSurveilReport.age = age1;
                        theSurveilReport.age_year = Constant.getYear(age1);
                        theSurveilReport.age_month = Constant.getMonth(age1);
                        theSurveilReport.age_day =  Constant.getDay(age1);
                    }
                }
                Visit theVisit = theHosDB.theVisitDB.selectByPK(theSurveil.vn_id);
                if(theVisit!=null)
                {
                    theSurveilReport.visit_type = theVisit.visit_type.equals(Active.isEnable())?"IPD":"OPD";///////////////////////
                    theSurveilReport.diagnosis = theVisit.doctor_dx.replace('\n',',');
                    theSurveilReport.date_discharge = theVisit.doctor_discharge_time;
                    theSurveilReport.visit_date = DateUtil.getDateToString(DateUtil.getDateFromText(theVisit.begin_visit_time),false);  
                }
                TypeDish td = theHosDB.theTypeDishDB.selectByPK(theSurveil.patient_status);
                theSurveilReport.status = (td!=null)?td.description:"";///////////////////////
                if(!theSurveil.illdate.equals(""))
                {
                    theSurveilReport.date_update = DateUtil.getDateToString(DateUtil.getDateFromText(theSurveil.illdate),false);  
                }
                theSurveilReport.patient_id = theSurveil.patient_id;
                theSurveilReport.visit_id = theSurveil.vn_id;
                
                MarryStatus ms = theHosDB.theMarryStatusDB.selectByPK(thePatient.marriage_status_id);///////////////////////
                theSurveilReport.marriage = (ms!=null)?ms.description:"";
                Nation na = theHosDB.theNationDB.selectByPK(thePatient.nation_id);
                theSurveilReport.nation = (na!=null)?na.description:"";
                Occupat occ = theHosDB.theOccupatDB.selectByPK(thePatient.occupation_id);
                theSurveilReport.occupation = (occ!=null)?occ.description:"";
                vListSurveil.add(theSurveilReport);
            }
            return vListSurveil;
        }
        catch(Exception ex)
        {   
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    /**
     * ปรับแก้ให้ตรวจสอบว่ารหัสซ้ำและเวลาซ้ำหรือไม่หากยังซ้ำกันก็ให้เช็คในส่วนของเวลาด้วย
     * @param diagicd9
     * @param dx9
     * @return
     */
    private boolean checkCODEDx9Same(DiagIcd9 diagicd9,Vector dx9)
    {
        boolean same = false;
        if(diagicd9.getObjectId() == null) {
            for(int i = 0 ;dx9!=null && i < dx9.size() ; i++) {
                DiagIcd9 ic = (DiagIcd9)dx9.get(i);
                if(diagicd9.icd9_code.equals(ic.icd9_code.trim())){
                    if(diagicd9.time_in.equals(ic.time_in)){
                        same = true;
                        break;
                    }
                }
            }
        }
        return same;
    }
    
    public boolean checkCODEDx10Same(DiagIcd10 diagicd10,Vector Dx10)
    {   
        String diag = diagicd10.icd10_code.trim();
        if(Dx10==null){
            return false;
        }
        DiagIcd10 ic = null;
        boolean same = false;
        if(diagicd10.getObjectId() == null)
        {
            for(int i = 0 ; i < Dx10.size() ; i++)
            {
                ic = (DiagIcd10)Dx10.get(i);
                if(diag.equalsIgnoreCase(ic.icd10_code.trim()))
                {   same = true;
                    break;
                }
                ic = null;
            }
        }
        diag = null;
        ic = null;
        return same;
    }
   /**
    * @author henbe
    * บันทึกข้อมูลอุบัติเหตุ
    * ย้ายมาจาก VisitControl
    */
    public int saveAccident(Accident accident,DiagIcd10 dx10,UpdateStatus theUS)
    {
        Constant.println(UseCase.UCID_saveAccident);
        String objectid =   null;
        int result_loc = 0;
        if(accident==null){
             theUS.setStatus(("กรุณาเลือกรายการอุบัติเหตุ"),UpdateStatus.WARNING);
             return 0;
         }
         if(accident.patient_id.equals("")){
             theUS.setStatus(("กรุณาเลือกผู้ป่วย"),UpdateStatus.WARNING);
             return 0;
         }
         if(accident.vn_id.equals("")){
             theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
             return 0;
         }
        if(accident.date_accident.equals("")){
            theUS.setStatus(("กรุณาระบุวันที่เกิดอุบัติเหตุ"),UpdateStatus.WARNING);
            return 0;
        }
        int date_valid = DateUtil.countDateDiff(theLookupControl.getTextCurrentDate()
            ,accident.date_accident);
        if(date_valid<0){
            theUS.setStatus(("วันที่เกิดอุบัติเหตุต้องเป็นวันที่ในอดีต"),UpdateStatus.WARNING);
            return 0;
        }         
        theConnectionInf.open();
        try{
            theLookupControl.intReadDateTime();
            //////////////หาค่าเก่าเพื่อจะได้ลบได้ถูก////////////////////////////////////
            String icd_old = "";
            if(accident.getObjectId()!=null)
            {
                Accident accOld = theHosDB.theAccidentDB.selectByPK(accident.getObjectId());
                AccidentType atOld = theHosDB.theAccidentTypeDB.selectByPK2(accOld.b_setup_accident_type_id);
                if(atOld!=null)
                    icd_old = atOld.accident_type_number;
            }
            //////////////////////////////////////////////////////////////////////
            AccidentType at = theHosDB.theAccidentTypeDB.selectByPK2(accident.b_setup_accident_type_id);
            String icd = at.accident_type_number;
            if(accident.getObjectId() == null)
            {
                if(theHosDB.theAccidentDB.selectByVN(accident.vn)!=null){
                     theUS.setStatus(("ระบบไม่สามารถบันทึกข้อมูลอุบัติเหตุมากกว่า 1 ครั้งในการรับบริการครั้งเดียวได้")
                     ,UpdateStatus.WARNING);
                     return 0;
                }
                accident.reporter = theHO.theEmployee.getObjectId();
                accident.record_date_time = theHO.date_time;
                result_loc = theHosDB.theAccidentDB.insert(accident);
            }
            else
            {
                accident.staff_update = theHO.theEmployee.getObjectId();
                accident.update_date_time = theHO.date_time;
                result_loc = theHosDB.theAccidentDB.update(accident);
            }
            theHosDB.theDiagIcd10DB.deleteByIcdCode(accident.vn_id,icd_old,theHO.date_time,theHO.theEmployee.getObjectId());
            if(dx10!=null)
            {
                dx10.icd10_code = icd;
                dx10.type = Dxtype.getExternalCauseofInjuryDiagnosis();
                dx10.diagnosis_date = accident.to_hos_date;
                if(theHO.theVisit.getObjectId().equals(accident.vn_id))
                {
                    intSaveDiagIcd10(dx10, null, theUS,false);
                    theHO.vDiagIcd10 = theHosDB.theDiagIcd10DB.selectByVidSort(theHO.theVisit.getObjectId());     
                }
            }
            
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลอุบัติเหตุ")+" "+
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            theHS.theDiagnosisSubject.notifyManageDiagIcd10(Constant.getTextBundle("การบันทึกข้อมูลอุบัติเหตุ")+" "+
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(accident != null)
                objectid = accident.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveAccident,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveAccident,objectid,null,UpdateStatus.COMPLETE);
            return result_loc;
        }
        catch(Exception ex) {
            theSystemControl.setStatus(UseCase.TH_saveAccident,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveAccident,objectid,ex,UpdateStatus.ERROR);
            return 0;
        }
        finally{
            theConnectionInf.close();
        }
    }    

    public Vector listOrderIcd9(String visit_id) 
    {
        theConnectionInf.open();
        String sql = "select * from b_icd9 " +
                "where icd9_number in " +
                "    (select b_item_service.icd9_number from t_order" +
                "        inner join b_item_service on b_item_service.b_item_id = t_order.b_item_id and item_service_active = '1'" +
                "    where t_visit_id = '"+visit_id+"')";
        try {
            return theHosDB.theICD9DB.eQuery(sql);
        } 
        catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }

    public boolean saveReDiag() 
    {
        if(theHO.theVisit == null) {
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return false;
        }
        Visit last_visit = null;
        if(theHO.vVisit!=null && theHO.vVisit.size()< 2){
            theUS.setStatus(("ผู้ป่วยยังไม่มีประวัติการรับบริการก่อนหน้า"),UpdateStatus.WARNING);
            return false;
        }
        last_visit = (Visit)theHO.vVisit.get(theHO.vVisit.size()-2);
        theConnectionInf.open();
        try {
            theHO.theVisit.doctor_dx = last_visit.doctor_dx;
            theHosDB.theVisitDB.updateDiagnosis(theHO.theVisit);
            Vector vDx = theHosDB.theMapVisitDxDB.selectMapVisitDxByVisitID(last_visit.getObjectId(),Active.isEnable());
            for(int i=0;i<vDx.size();i++){
                MapVisitDx mvd = (MapVisitDx)vDx.get(i);
                mvd.visit_id = theHO.theVisit.getObjectId();
                theHosDB.theMapVisitDxDB.update(mvd);
            }
            this.theHS.theVitalSubject.notifySaveDiagDoctor(Constant.getTextBundle("บันทึกการตรวจของแพทย์จากครั้งก่อน") +" "+
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            return true;
        } 
        catch (Exception ex) {
            theUS.setStatus(Constant.getTextBundle("บันทึกการตรวจของแพทย์จากครั้งก่อน") +" "+
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
            return false;
        }
        finally{
            theConnectionInf.close();
        }
    }
    public boolean dischargeDoctor(boolean unlock) 
    {
        Constant.println(UseCase.UCID_dischargeDoctor);
        String objectid =   null;
        theConnectionInf.open();
        try{
            boolean ret = theVisitControl.intDischargeDoctor(theHO.theVisit,null,unlock);
            if(!ret)
                return false;
            theHS.theVisitSubject.notifyDischargeDoctor(Constant.getTextBundle("การจำหน่ายทางการแพทย์") +" "+
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_dischargeDoctor,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_dischargeDoctor,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_dischargeDoctor,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_dischargeDoctor,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }
    }

    public boolean saveDxByStat(String string) {
        theHO.theVisit.stat_dx = Gutil.CheckReservedWords(string);
        saveDxByStat(theHO.theVisit, theHO.theEmployee.authentication_id);
        return true;
    }
    public boolean deleteDiagIcd10(int[] row) {
        return deleteDiagnosisIcd10(theHO.theVisit,theHO.vDiagIcd10,row);
    }
    /**
     *
     * ตรวจสอบถ้ามีรายการ Icd10 รหัส V,W,X,Y สาเหตุอุบัติเหตุ
     * ก็จะไม่ต้องเช็คเรื่องของการลง Icd10 การบาดเจ็บ S,T
     **/
    public boolean saveDiagIcd10(DiagIcd10 theDiagIcd10)
    {
        return saveDiagIcd10(theDiagIcd10,false);
    }

    /**
     * ตรวจสอบถ้ามีรายการ Icd10 รหัส V,W,X,Y สาเหตุอุบัติเหตุ 
     * ก็จะไม่ต้องเช็คเรื่องของการลง Icd10 การบาดเจ็บ S,T
     **/
    public boolean saveDiagIcd10(DiagIcd10 theDiagIcd10, boolean isshow)
    {
        Constant.println(UseCase.UCID_saveDiagIcd10);
        String objectid =   null;
        try {
            Vector diag = new Vector();
            diag.add(theDiagIcd10);
            boolean is_vwxy = false;
            boolean is_st = false;
            // ตรวจสอบถ้ามีรายการ Icd10 ที่มีอยู่แล้วว่าเป็นรหัส V,W,X,Y หรือไม่ sumo 16/08/2549
            for (int i = 0; i < theHO.vDiagIcd10.size(); i++) {
                DiagIcd10 diagicd10 = (DiagIcd10) theHO.vDiagIcd10.get(i);
                if (diagicd10.icd10_code.startsWith("V")
                        || diagicd10.icd10_code.startsWith("W")
                        || diagicd10.icd10_code.startsWith("X")
                        || diagicd10.icd10_code.startsWith("Y")) {
                    // ถ้ามีก็จะไม่ต้องเช็คเรื่องของการลง Icd10 อุบัติเหตุ sumo 16/08/2549
                    is_vwxy = true;
                    break;
                }
            }
            // ตรวจสอบถ้ามีรายการ Icd10 ที่จะบันทึกมีรหัส S,T หรือไม่ sumo 16/08/2549
            for (int i = 0; i < diag.size(); i++) {
                DiagIcd10 diagicd10 = (DiagIcd10) diag.get(i);
                if (diagicd10.icd10_code.startsWith("S") || diagicd10.icd10_code.startsWith("T")) {
                    is_st = true;
                }
            }
            if (!is_vwxy && is_st) {
                DialogWarningDiagIcd10 theDWDI = new DialogWarningDiagIcd10(theHO, theLookupControl, theUS);
                if (!theDWDI.showDialog(diag)) {
                    return false;
                }
            }
            theConnectionInf.open();
            int ret_int = intSaveDiagIcd10(theDiagIcd10, theHO.vDiagIcd10, theUS, isshow);
            if (ret_int > 0) 
                return false;
            
            theHO.vDiagIcd10 = theHosDB.theDiagIcd10DB.selectByVidSort(theHO.theVisit.getObjectId());
            theHS.theDiagnosisSubject.notifyManageDiagIcd10(Constant.getTextBundle("การบันทึกรหัสโรค") + " "+
                    Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if(theDiagIcd10 != null)
                objectid = theDiagIcd10.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveDiagIcd10,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveDiagIcd10,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_saveDiagIcd10,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveDiagIcd10,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }
    }

    /**
     * การบันทึกรหัสโรคหลักสำหรับออกรายงาน
     * @param theIcd9
     * @return
     */
    public int savePrimaryReport(DiagIcd9 theIcd9)
    {
        String uc_name = Constant.getTextBundle("การบันทึกรหัสโรคหลักสำหรับออกรายงาน");
        try {
            if(theIcd9.getObjectId()==null){
                theUS.setStatus(("กรุณาเลือกรายการที่บันทึกแล้ว"),UpdateStatus.WARNING);
                return 11;
            }
            if(!theIcd9.type.equals(Dxtype.getPrimaryDiagnosis())){
                theUS.setStatus(("กรุณาเลือกรายการที่เป็น Primary"),UpdateStatus.WARNING);
                return 12;
            }
            for(int i=0;i<theHO.vDiagIcd10.size();i++){
                DiagIcd10 dx10 = (DiagIcd10)theHO.vDiagIcd10.get(i);
                if(dx10.primary_report.equals("1")){
                    dx10.primary_report = "0";
                    theHosDB.theDiagIcd10DB.update(dx10);
                }
            }
            theIcd9.primary_report = "1";
            theHosDB.theDiagIcd9DB.update(theIcd9);
            theHO.vDiagIcd10 = theHosDB.theDiagIcd10DB.selectByVidSort(theHO.theVisit.getObjectId());
            theHS.theDiagnosisSubject.notifyManageDiagIcd10(uc_name+Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            return 0;
        }
        catch (Exception ex) {
            theUS.setStatus(uc_name+Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
            return 1;
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     * การบันทึกรหัสโรคหลักสำหรับออกรายงาน
     * @param theDx10
     * @return
     */
    public int savePrimaryReport(DiagIcd10 theDx10)
    {
        String uc_name = Constant.getTextBundle("การบันทึกรหัสโรคหลักสำหรับออกรายงาน");
        try {
            if(theDx10.getObjectId()==null){
                theUS.setStatus(("กรุณาเลือกรายการที่บันทึกแล้ว"),UpdateStatus.WARNING);
                return 11;
            }
            if(!theDx10.type.equals(Dxtype.getPrimaryDiagnosis())){
                theUS.setStatus(("กรุณาเลือกรายการที่เป็น Primary"),UpdateStatus.WARNING);
                return 12;
            }
            for(int i=0;i<theHO.vDiagIcd10.size();i++){
                DiagIcd10 dx10 = (DiagIcd10)theHO.vDiagIcd10.get(i);
                if(dx10.primary_report.equals("1")){
                    dx10.primary_report = "0";
                    theHosDB.theDiagIcd10DB.update(dx10);
                }
            }
            theDx10.primary_report = "1";
            theHosDB.theDiagIcd10DB.update(theDx10);
            theHO.vDiagIcd10 = theHosDB.theDiagIcd10DB.selectByVidSort(theHO.theVisit.getObjectId());
            theHS.theDiagnosisSubject.notifyManageDiagIcd10(uc_name+"เสร็จสิ้น", UpdateStatus.COMPLETE);
            return 0;
        }
        catch (Exception ex) {
            theUS.setStatus(uc_name+"ผิดพลาด", UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
            return 1;
        }
        finally{
            theConnectionInf.close();
        }
    }
/**
 * @deprecate henbe unused  must send theUS
 * @param chronic
 */
    public void saveChronic(Chronic chronic) {
        saveChronic(chronic,theUS);
    }

    public void saveChronic(Chronic chronic, int panel) {
        saveChronic(chronic,theUS, panel);
    }
/**
 *
 * @deprecated henbe unused  must send theUS
 * @param surveil
 */
    public void saveSurveil(Surveil surveil) {
        saveSurveil(surveil,theUS);
    }

    public Vector listSurveilPVid(String patient_id, String visit_id) {
        
        theConnectionInf.open();
        try{
            String sql="select * from t_surveil" +
                    " where t_patient_id = '"+patient_id+"'";
            if(visit_id!=null)
                sql+=" and t_visit_id = '"+visit_id+"'" ;
            sql += " order by surveil_hn,surveil_icd10_number ";
            return theHosDB.theSurveilDB.eQuery(sql);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally {
            theConnectionInf.close();
        }
    }
}
