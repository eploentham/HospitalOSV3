/*
 * AfterMCHMotherControl.java
 *
 * Created on 26 กรกฎาคม 2548, 13:09 น.
 * Modified on 25 กันยายน 2551
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.control;
import com.hospital_os.object.ICD10;
import com.hospital_os.object.MarryStatus;
import com.hospital_os.object.Patient;
import com.hospital_os.object.Prefix;
import com.hospital_os.object.Sex;
import com.hospital_os.object.Visit;
import com.hospital_os.object.VisitStatus;
import com.hospital_os.utility.Constant;
import com.hosv3.control.HosControl;
import com.hosv3.control.LookupControl;
import com.hosv3.control.PatientControl;
import com.hosv3.control.VisitControl;
import com.hosv3.gui.dialog.DialogOffDrug;
import com.hosv3.subject.HosSubject;
import com.pcu.object.*;
import com.pcu.utility.GutilPCU;
import com.hosv3.control.DiagnosisControl;
import com.hosv3.object.HosObject;
import com.hosv3.utility.connection.UpdateStatus;
import com.hospital_os.object.DiagIcd10;
import com.hospital_os.utility.Gutil;
import com.hospital_os.usecase.connection.*;
import com.hosv3.object.UseCase;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import java.util.*;

/**
 *
 * @author tong(Padungrat)
 * @modifier pu
 */
public class AfterMCHMotherControl
{
    ConnectionInf theConnectionInf;
    UpdateStatus theUS;
    HosDB thePcuDB;
    Vector vc;
    boolean result;
    int iresult;
    Object object;
    String sresult;
    AfterMchMother theAfterMchMother;
    BornMch theBornMch;
    PCUObject thePO;
    private DiagnosisControl theDiagnosisControl;
    private HosObject theHO;
    private LookupControl theLookupControl;
    private HosSubject theHS;
    private Visit theVisit;
    private Patient thePatient;
    private PatientControl thePatientControl;
    HosControl theHC;
    private VisitControl theVisitControl;
    
    /** Creates a new instance of AfterMCHMotherControl */
    public AfterMCHMotherControl(ConnectionInf con,HosDB hdb,PCUObject po,HosControl hc,UpdateStatus us)
    {
        theConnectionInf = con;
        thePcuDB = hdb;
        theHC = hc;
        theUS = us;
        thePO = po;
        theHO = hc.theHO;
        theDiagnosisControl = hc.theDiagnosisControl;
        theLookupControl = hc.theLookupControl;
        theHS = hc.theHS;
        thePatientControl = hc.thePatientControl;
        theVisitControl = hc.theVisitControl;
    }
    public AfterMCHMotherControl(ConnectionInf con,HosDB hdb,UpdateStatus us,PCUObject po)
    {
        theConnectionInf = con;
        thePcuDB = hdb;
        theUS = us;
        thePO = po;
    }
    public void setDepObject(HosObject ho,DiagnosisControl dc
            ,LookupControl lc, com.hosv3.subject.HosSubject hs,PatientControl pc)
    {
        theHO = ho;
        theDiagnosisControl = dc;
        theLookupControl = lc;
        theHS = hs;
        thePatientControl = pc;
    }
    
    public Family intSaveFamilyChild(Family fam,String sex_id,PP pp)throws Exception
    {
        Family fm_child;
        //ถ้าข้อมูลประชากรแม่และข้อมูลการคลอดของลูก เป็นเลขประชากรเดียวกันแสดงว่าเป็นเวอร์ชันเก่า
        //จะทำการสร้างประชากรให้ใหม่ แต่ถ้าเป็นคนละเลขก็จะค้นขึ้นมาแสดงผลให้
        if(!fam.getObjectId().equals(pp.family_id))
            fm_child = thePcuDB.theFamilyDB.selectByPK(pp.family_id);
        else
            fm_child = new Family();
        fm_child.f_sex_id = sex_id;
        fm_child.patient_name = "บุตรนาง" + fam.patient_name;
        fm_child.patient_last_name = fam.patient_last_name;
        fm_child.f_prefix_id = Prefix.BOY;
        if(sex_id.equals(Sex.isWOMAN()))
            fm_child.f_prefix_id = Prefix.GIRL;
        fm_child.patient_birthday_true = "1";
        fm_child.patient_birthday = pp.survey_date;
        fm_child.home_id = fam.home_id;
        //henbe modify technic for keep mother family_id
        fm_child.mother_firstname = fam.patient_name;
        fm_child.mother_lastname = fam.patient_last_name;
        fm_child.mother_pid = fam.pid;
        fm_child.mother_fid = fam.getObjectId();
        fm_child.marriage_status_id = MarryStatus.SINGLE;
        fm_child.nation_id = fam.nation_id;
        fm_child.race_id = fam.race_id;
        fm_child.religion_id = fam.religion_id;
        fm_child.occupation_id = "000";
        int ret = theHC.thePatientControl.intSaveFamily(fm_child,null);  
        pp.family_id = fm_child.getObjectId();      
        return fm_child;
    }
    /**
     * ฟังชันนี้อันตรายเพราะว่าโปรแกรมจะบันทึกข้อมูลให้ทันทีหากไม่พบเด็กที่ค้นหา
     * @deprecated henbe unused
     * @param pp
     * @param sex_id
     * @return
     */
    public Family readFamilyRet(PP pp,String sex_id) {
        theConnectionInf.open();
        try{
            Family fam = thePcuDB.theFamilyDB.selectByPK(pp.family_id);
            if(fam==null)
                intSaveFamilyChild(this.thePO.getFamily(),sex_id,pp);
            return fam;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     * บันทึกการ รายการการดูแลแม่หลังคลอด
     * ถ้าเป็น object ใหม่ ก็จะ insert
     * ถ้าเป็น object เก่า ก็จะ update
     * @return เป็น integer
     * @param AfterMchMother รับ Object
     * @author ผดุงรัฐ
     */
    public int saveAfterMCHMother(AfterMchMother aftermchmother)
    {
        if(this.theHC.theHO.theVisit==null)
        {
            this.theUS.setStatus("กรุณานำผู้ป่วยเข้าสู่กระบวนการ", UpdateStatus.WARNING);
            return 0;
        }
        Constant.println(UseCase.UCID_saveAfterMchMother);
        String objectid =   null;
        int result = 0;
//        if(aftermchmother.updatetime.equals(""))
//        {
//            theUS.setStatus("กรุณาระบุวันที่คลอด",UpdateStatus.WARNING);
//            return 0;
//        }
        if(aftermchmother.updatetime.startsWith("20"))
        {
            theUS.setStatus(Constant.getTextBundle("วันที่คลอดมีค่าไม่ถูกต้อง") +
                    " "+ aftermchmother.updatetime +" " +
                    Constant.getTextBundle("กรุณาตรวจสอบระบบ"),UpdateStatus.WARNING);
            return 0;
        }
        if(aftermchmother.updatetime.length()<10)
        {
            theUS.setStatus("กรุณาระบุวันที่บันทึก",UpdateStatus.WARNING);
            return 0;
        }
        if(theLookupControl.isDateFuture(aftermchmother.updatetime))
        {
            theUS.setStatus("กรุณาระบุวันที่บันทึกเป็นวันในอตีต",UpdateStatus.WARNING);
            return 0;
        }
        //09/12/2010 ต้นเอาออกเพื่อให้ผู้ใช้ทำงานง่ายขึ้น
//        if(thePO.getVisit()!=null && !aftermchmother.visit_id.equals("")){
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//            if(!thePO.getVisit().getObjectId().equals(aftermchmother.visit_id)
//            && !aftermchmother.visit_id.equals("")){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//        }
//        if(thePO.getVisit()==null && !aftermchmother.visit_id.equals("")){
//            theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//            return 0;
//        }
        theConnectionInf.open();
        try
        {
            if(aftermchmother!=null)
            {
                if(aftermchmother.getObjectId() == null)
                {   result = thePcuDB.theAfterMchMotherDB.insert(aftermchmother);
                }
                else
                {   result = thePcuDB.theAfterMchMotherDB.update(aftermchmother);
                }
                thePO.vAfterMchMother = thePcuDB.theAfterMchMotherDB.selectShowTableByFamilyID(aftermchmother.family_id);
            
                theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"), UpdateStatus.COMPLETE);
                if(aftermchmother != null)
                    objectid = aftermchmother.getObjectId();
                theHC.theSystemControl.setStatus(UseCase.TH_saveAfterMchMother,UpdateStatus.COMPLETE,null);
                theHC.theSystemControl.saveLog(UseCase.UCID_saveAfterMchMother,objectid,null,UpdateStatus.COMPLETE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveAfterMchMother,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveAfterMchMother,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    public int saveAfterMCHMother2(AfterMchMother aftermchmother)
    {
        if(this.theHC.theHO.theVisit==null)
        {
            this.theUS.setStatus("กรุณานำผู้ป่วยเข้าสู่กระบวนการ", UpdateStatus.WARNING);
            return 0;
        }
        Constant.println(UseCase.UCID_saveAfterMchMother);
        String objectid =   null;
        int result = 0;
//        if(aftermchmother.updatetime.equals(""))
//        {
//            theUS.setStatus("กรุณาระบุวันที่คลอด",UpdateStatus.WARNING);
//            return 0;
//        }
        if(aftermchmother.updatetime.startsWith("20"))
        {
            theUS.setStatus(Constant.getTextBundle("วันที่คลอดมีค่าไม่ถูกต้อง") +
                    " "+ aftermchmother.updatetime +" " +
                    Constant.getTextBundle("กรุณาตรวจสอบระบบ"),UpdateStatus.WARNING);
            return 0;
        }
        if(aftermchmother.updatetime.length()<10)
        {
            theUS.setStatus("กรุณาระบุวันที่บันทึก",UpdateStatus.WARNING);
            return 0;
        }
        if(theLookupControl.isDateFuture(aftermchmother.updatetime))
        {
            theUS.setStatus("กรุณาระบุวันที่บันทึกเป็นวันในอตีต",UpdateStatus.WARNING);
            return 0;
        }
        if(thePO.getVisit()!=null && !aftermchmother.visit_id.equals("")){
            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
                return 0;
            }
//            if(!thePO.getVisit().getObjectId().equals(aftermchmother.visit_id)
//            && !aftermchmother.visit_id.equals("")){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
        }
//        if(thePO.getVisit()==null && !aftermchmother.visit_id.equals("")){
//            theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//            return 0;
//        }
        theConnectionInf.open();
        try
        {
            if(aftermchmother!=null)
            {
                if(aftermchmother.getObjectId() == null)
                {   result = thePcuDB.theAfterMchMotherDB.insert(aftermchmother);
                }
                else
                {   result = thePcuDB.theAfterMchMotherDB.update(aftermchmother);
                }
                thePO.vAfterMchMother = thePcuDB.theAfterMchMotherDB.selectShowTableByFamilyID(aftermchmother.family_id);

                theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"), UpdateStatus.COMPLETE);
                if(aftermchmother != null)
                    objectid = aftermchmother.getObjectId();
                theHC.theSystemControl.setStatus(UseCase.TH_saveAfterMchMother,UpdateStatus.COMPLETE,null);
                theHC.theSystemControl.saveLog(UseCase.UCID_saveAfterMchMother,objectid,null,UpdateStatus.COMPLETE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveAfterMchMother,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveAfterMchMother,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    public int saveAfterMCHMotherN(AfterMchMother aftermchmother)
    {
        if(this.theHC.theHO.theVisit==null)
        {
            this.theUS.setStatus("กรุณานำผู้ป่วยเข้าสู่กระบวนการ", UpdateStatus.WARNING);
            return 0;
        }
//        if(!this.theHC.theHO.theVisit.service_location.equals("2"))
//        {
//            this.theUS.setStatus("กรุณาระบุประเภท Visit เป็นให้บริการนอกหน่วยบริการ", UpdateStatus.WARNING);
//            return 0;
//        }
        Constant.println(UseCase.UCID_saveAfterMchMother);
        String objectid =   null;
        int result = 0;
        if(aftermchmother.updatetime.equals(""))
        {
            theUS.setStatus("กรุณาระบุวันที่คลอด",UpdateStatus.WARNING);
            return 0;
        }
        if(aftermchmother.updatetime.startsWith("20"))
        {
            theUS.setStatus(Constant.getTextBundle("วันที่คลอดมีค่าไม่ถูกต้อง") +
                    " "+ aftermchmother.updatetime +" " +
                    Constant.getTextBundle("กรุณาตรวจสอบระบบ"),UpdateStatus.WARNING);
            return 0;
        }
        if(aftermchmother.updatetime.length()<10)
        {
            theUS.setStatus("กรุณาระบุวันที่บันทึก",UpdateStatus.WARNING);
            return 0;
        }
        if(theLookupControl.isDateFuture(aftermchmother.updatetime))
        {
            theUS.setStatus("กรุณาระบุวันที่บันทึกเป็นวันในอตีต",UpdateStatus.WARNING);
            return 0;
        }
        if(thePO.getVisit()!=null && !aftermchmother.visit_id.equals("")){
            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
                return 0;
            }
            if(!thePO.getVisit().getObjectId().equals(aftermchmother.visit_id)
            && !aftermchmother.visit_id.equals("")){
                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
                return 0;
            }
        }
        if(thePO.getVisit()==null && !aftermchmother.visit_id.equals("")){
            theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
            return 0;
        }
        theConnectionInf.open();
        try
        {
            if(aftermchmother!=null)
            {
                if(aftermchmother.getObjectId() == null)
                {   result = thePcuDB.theAfterMchMotherDB.insertN(aftermchmother);
                }
                else
                {   result = thePcuDB.theAfterMchMotherDB.updateN(aftermchmother);
                }
                thePO.vAfterMchMother = thePcuDB.theAfterMchMotherDB.selectShowTableByFamilyID(aftermchmother.family_id);

                theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"), UpdateStatus.COMPLETE);
                if(aftermchmother != null)
                    objectid = aftermchmother.getObjectId();
                theHC.theSystemControl.setStatus(UseCase.TH_saveAfterMchMother,UpdateStatus.COMPLETE,null);
                theHC.theSystemControl.saveLog(UseCase.UCID_saveAfterMchMother,objectid,null,UpdateStatus.COMPLETE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveAfterMchMother,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveAfterMchMother,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    /**
     * บันทึกรายละเอียดการตั้งครรภ์และการคลอด
     */
    public int saveMchDetail(MchDetail mchDetail)
    {
        String objectid = null;
        int result = 0;
        try
        {
            theConnectionInf.open();
            if(mchDetail!=null)
            {
                if(mchDetail.getObjectId()==null)
                {
                    mchDetail.t_health_family_id = this.thePO.theHO.theFamily.getObjectId();
                    mchDetail.active = "1";
                    result = thePcuDB.thePostpartumDetailDB.insert(mchDetail);
                }
                else
                {
                    result = thePcuDB.thePostpartumDetailDB.update(mchDetail);
                }
                theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"), UpdateStatus.COMPLETE);
                if(mchDetail != null)
                    objectid = mchDetail.getObjectId();
                theHC.theSystemControl.setStatus(UseCase.TH_saveAfterMchMother,UpdateStatus.COMPLETE,null);
                theHC.theSystemControl.saveLog(UseCase.UCID_saveAfterMchMother,objectid,null,UpdateStatus.COMPLETE);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveAfterMchMother,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveAfterMchMother,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    public Vector selectMchDetailByFamilyID(String f_id)
    {
        Vector v = null;
        try
        {
            v = this.thePcuDB.thePostpartumDetailDB.selectByFid(f_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return v;
    }
    public AfterMchMother readtMchByMchDetailID(String mch_detail_id)
    {
        AfterMchMother afterMchMother = null;
        Vector v = null;
        try
        {
            afterMchMother = this.thePcuDB.theAfterMchMotherDB.readPPCareByFamilyIDAndGravida(mch_detail_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return afterMchMother;
    }
    
    /**
     * ใช้ในการแสดงผลลงตารางของ GUI โดยจะ นำมาแสดงไม่ครบทุกส่วน
     * @author ผดุงรัฐ
     * @param visit_id Visit_id ของผู้ป่วยที่ต้องการ
     * @return เป็น Vector ของ Object FamilyPlaning
     */
    public Vector selectAfterMchMotherShowGUITableByVisitID(String visit_id)
    {
        theConnectionInf.open();
        vc = null;
        try
        {
            if(visit_id!= null)
            {
                
                vc = this.thePcuDB.theAfterMchMotherDB.selectShowTableByVisitID(visit_id);
                
                if(vc != null)
                {
                    int size = vc.size();
                    Constant.println("Size  : " + size);
                    for(int i =0 ; i < size;i++)
                    {
                        theAfterMchMother = (AfterMchMother)vc.get(i);
                        theAfterMchMother.setVN(selectVNByKeyID(theAfterMchMother.visit_id));
                        //Constant.println("remove at : " + i);
                        vc.remove(i);
                        vc.add(i,theAfterMchMother);
                        
                        theAfterMchMother =null;
                    }
                }
                
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
        return vc;
    }
    
    
    /**
     * ใช้ในการแสดงผลลงตารางของ GUI โดยจะ นำมาแสดงไม่ครบทุกส่วน
     * @author หนุ่ม
     * @param patient_id  ของผู้ป่วยที่ต้องการ
     * @return เป็น Vector ของ Object FamilyPlaning
     */
    public Vector selectAfterMchMotherShowGUITableByPatientID(String patient_id)
    {
        theConnectionInf.open();
        vc = null;
        try
        {
            if(patient_id!= null)
            {
                
                vc = this.thePcuDB.theAfterMchMotherDB.selectShowTableByPatientID(patient_id);
                
                if(vc != null)
                {
                    int size = vc.size();
                    Constant.println("Size  : " + size);
                    for(int i =0 ; i < size;i++)
                    {
                        theAfterMchMother = (AfterMchMother)vc.get(i);
                        theAfterMchMother.setVN(selectVNByKeyID(theAfterMchMother.visit_id));
                        //Constant.println("remove at : " + i);
                        vc.remove(i);
                        vc.add(i,theAfterMchMother);
                        
                        theAfterMchMother =null;
                    }
                }
                
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
        return vc;
    }
    
    /**
     * ใช้ในการตรวจสอบข้อมูลที่อยู่ในตาราง AfterMchMother(การเข้ารับบริการหลังคลอด)
     * ว่ามีข้อมูลอยู่หรือไม่ตามการค้นหาของ key id ของ bornmch(ข้อมูลการเกิด)
     * @param bornmch_id เป็น String ของ key id ของ bornmch(ข้อมูลการเกิด
     * @return เป็น boolean ถ้าเป็น true แสดงว่ามีข้อมูลอยู่ ถ้าเป็น false ไม่มีข้อมูล
     */
    public boolean checkDataAfterMchMotherByBornMchId(String bornmch_id)
    {     result = false;
          int count = -1;
          try
          {
              theConnectionInf.open();
              count = thePcuDB.theAfterMchMotherDB.countAfterMchMotherByBornMchKeyID(bornmch_id);
              if(count >0)
                  result = true;
              
          }
          catch(Exception ex)
          {
              ex.printStackTrace();
          }
          finally
          {
              theConnectionInf.close();
          }
          
          return result;
    }
    
    /**
     * ใช้ในการตรวจสอบข้อมูลที่อยู่ในตาราง AfterMchMother(การเข้ารับบริการหลังคลอด)
     * ว่ามีข้อมูลอยู่หรือไม่ตามการค้นหาของ key id ของ visit_id
     * @param visit_id เป็น String ของ key id ของ visit
     * @return เป็น boolean ถ้าเป็น true แสดงว่ามีข้อมูลอยู่ ถ้าเป็น false ไม่มีข้อมูล
     */
    public boolean checkDataAfterMchMotherByVisitID(String visit_id)
    {     result = false;
          int count = -1;
          try
          {
              theConnectionInf.open();
              count = thePcuDB.theAfterMchMotherDB.countAfterMchMotherByVisitID(visit_id);
              if(count >0)
                  result = true;
              
          }
          catch(Exception ex)
          {
              ex.printStackTrace();
          }
          finally
          {
              theConnectionInf.close();
          }
          
          return result;
    }
    
    /**
     * ใช้ในการค้นหาข้อมูลการรับบริการหลังคลอดด้วยรหัสของตาราง
     * @param key_id เป็น String ของ key หลักของตาราง
     * @return Object ของ AfterMchMother
     */
    public AfterMchMother selectAfterMchMotherByKeyID(String key_id)
    {
        theConnectionInf.open();
        
        vc = null;
        try
        {
            if(key_id!= null)
            {
                object = this.thePcuDB.theAfterMchMotherDB.selectByPK(key_id);
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
        return (AfterMchMother)object;
    }
    /**
     * ใช้ในการค้นหาข้อมูลการรับบริการหลังคลอดด้วยรหัสของตาราง
     * @param key_id เป็น String ของ key หลักของตาราง
     * @return Object ของ AfterMchMother
     */
    public Vector selectAfterMchMotherByBornMchKeyID(String key_id)
    {
        theConnectionInf.open();
        vc = null;
        try
        {
            if(key_id!= null)
            {
                
                vc = this.thePcuDB.theAfterMchMotherDB.selectShowTableByBornMchID(key_id);
                
                if(vc != null)
                {
                    int size = vc.size();
                    Constant.println("Size  : " + size);
                    for(int i =0 ; i < size;i++)
                    {
                        theAfterMchMother = (AfterMchMother)vc.get(i);
                        theAfterMchMother.setVN(selectVNByKeyID(theAfterMchMother.visit_id));
                        //Constant.println("remove at : " + i);
                        vc.remove(i);
                        vc.add(i,theAfterMchMother);
                        
                        theAfterMchMother =null;
                    }
                }
                
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
        return vc;
    }
    /**
     * ใช้ในการลบข้อมูลของ การเข้ารับบริการหลังคลอด โดยจะลบออกจากตาราง
     * หรือ จะ update active เป็น 0 จะขึ้นอยู่กับข้อมูลเวลาที่บันทึก
     * @param fp Object ของ AfterMchMother
     * @return int
     */
    public int deleteAfterMchMotherByKeyID(AfterMchMother fp)
    {
        Constant.println(UseCase.UCID_deleteAfterMchMother);
        String objectid =   null;
        //หากรายการที่เลือกเป็นของ visit เก่า
//        if(!fp.visit_id.equals("")){
//            //ต้องตรวจสอบว่าเขาได้เลือก visit นั้นอยู่หรือเปล่า
//            if(thePO.getVisit()==null || !thePO.getVisit().getObjectId().equals(fp.visit_id)){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//        }
        if(!theUS.confirmBox(GutilPCU.getTextBundle("VerifyBeforeDelete"),UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        iresult = 0;
        try{
            fp.active = "0";
            fp.user_cancel = thePO.getEmployee().getObjectId();
            fp.updatetime = thePO.getCurrentDateTime();
            if(Gutil.isToday(Gutil.getDateFromText(fp.recordtime)))
                iresult = this.thePcuDB.theAfterMchMotherDB.delete(fp);
            else
                iresult = this.thePcuDB.theAfterMchMotherDB.updateActive(fp);
            
            thePO.vAfterMchMother = this.thePcuDB.theAfterMchMotherDB.selectShowTableByFamilyID(thePO.getFamily().getObjectId());
            theUS.setStatus("การลบข้อมูลเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(fp != null)
                objectid = fp.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteAfterMchMother,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteAfterMchMother,objectid,null,UpdateStatus.COMPLETE);
            return iresult;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteAfterMchMother,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteAfterMchMother,objectid,ex,UpdateStatus.ERROR);
            return iresult;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     * ใช้ในการหาเลข VN ของการเข้ารับบริการตาม key หลักของตาราง
     * @param key_id รหัส หลักของตาราง
     * @return String เป็น VN ที่ได้จากการค้นหา
     */
    public String selectVNByKeyID(String key_id)
    {
        theConnectionInf.open();
        try
        {
            if(key_id!= null)
            {
                object = this.thePcuDB.theVisitPCUDB.selectVNByVisitID(key_id);
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
        return (String)object;
    }
    
    /**
     * บันทึกการ รายการข้อมูลการคลอดของแม่
     * ถ้าเป็น object ใหม่ ก็จะ insert
     * ถ้าเป็น object เก่า ก็จะ update
     * @param bornmch รับ Object ของ BornMch
     * @author ผดุงรัฐ
     */
    public BornMch saveBornMCHMother(BornMch bornmch)
    {
        Constant.println(UseCase.UCID_saveBornMch);
        String objectid =   null;
        int res= 0;
        if(this.theHC.theHO.theVisit==null)
        {
            this.theUS.setStatus("กรุณานำผู้ป่วยเข้าสู่กระบวนการ", UpdateStatus.WARNING);
            return null;
        }
//        if(!this.theHC.theHO.theVisit.service_location.equals("2"))
//        {
//            this.theUS.setStatus("กรุณาระบุประเภท Visit เป็นให้บริการนอกหน่วยบริการ", UpdateStatus.WARNING);
//            return null;
//        }
        if(bornmch==null)
        {
            theUS.setStatus("ข้อมูลผิดพลาดกรุณาตรวจสอบอีกครั้ง",UpdateStatus.WARNING);
            return null;
        }
        
        if(bornmch.bplace.equals(PostpartumBirthPlace.isHospital())
        || bornmch.bplace.equals(PostpartumBirthPlace.isPCU()))
        {
            if(bornmch.bhosp.equals("")) {
                theUS.setStatus("กรุณาระบุสถานที่คลอด",UpdateStatus.WARNING);
                return null;
            }
        }
        else{
            if(!theUS.confirmBox(Constant.getTextBundle("ยืนยันการบันทึก") + " " +
                    Constant.getTextBundle("การคลอดนอกสถานพยาบาล อาจมีผลต่อรายงานที่ออก"),UpdateStatus.WARNING))
                return null;
        }
        if(bornmch.bdate.equals(""))
        {
            theUS.setStatus("กรุณาระบุวันที่คลอด",UpdateStatus.WARNING);
            return null;
        }
        if(bornmch.bdate.startsWith("20"))
        {
            theUS.setStatus(Constant.getTextBundle("วันที่คลอดมีค่าไม่ถูกต้อง")+" "+ bornmch.bdate
                    +" " +
                    Constant.getTextBundle("กรุณาตรวจสอบระบบ"),UpdateStatus.WARNING);
            return null;
        }
        if(thePO.getVisit()!=null)
        {
            int age = Integer.parseInt(thePO.getVisit().patient_age);
            if(age<15){
                boolean ret = theUS.confirmBox(Constant.getTextBundle("ยืนยันการบันทึกข้อมูลการคลอดของผู้ป่วยอายุ") +
                        " " + age + " " +
                        Constant.getTextBundle("ปี"),UpdateStatus.WARNING);
                if(!ret)
                    return null;
            }
        }
        // 09/12/2010 ต้นเอาออกเพื่อให้ User ใช้งานง่ายขึ้น
//        if(thePO.getVisit()!=null && !bornmch.visit_id.equals("")){
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return null;
//            }
//            if(!thePO.getVisit().getObjectId().equals(bornmch.visit_id)
//            && !bornmch.visit_id.equals("")){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return null;
//            }
//        }
//        if(thePO.getVisit()==null && !bornmch.visit_id.equals("")){
//            theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//            return null;
//        }
        theConnectionInf.open();
        try
        {
            bornmch.family_id = this.thePO.getFamily().getObjectId();
            bornmch.user_modify = this.thePO.getEmployee().getObjectId();
            
            if(bornmch.getObjectId() == null)
            {
                if(this.thePO.getPatient()!=null)
                    bornmch.patient_id = this.thePO.getPatient().getObjectId();
                if(this.thePO.getVisit()!=null)
                    bornmch.visit_id = this.thePO.getVisit().getObjectId();
                bornmch.user_record = this.thePO.getEmployee().getObjectId();
                bornmch.recordtime = this.thePO.getCurrentDateTime();
                res = thePcuDB.theBornMchDB.insert(bornmch);
            }
            else
                res = thePcuDB.theBornMchDB.update(bornmch);
            
            thePO.vBornMch = thePcuDB.theBornMchDB.selectShowTableByFamily(bornmch.family_id);
            if(res>0)
            {
                //.bresult is key_id of icd10
                ICD10 icd10 = thePcuDB.theICD10DB.selectByPK(bornmch.bresult);
                if(icd10 !=null && theHO.theVisit!=null)
                {
                    DialogOffDrug theDialogUseDoctor = new DialogOffDrug(theUS.getJFrame(),true,theLookupControl,theUS);
                    theDialogUseDoctor.setTitle("เลือกแพทย์ผู้ให้รหัส");
                    theDialogUseDoctor.setuseMapDXICD10(true);
                    theDialogUseDoctor.showDialog(theHO.theDiagDoctorClinic);
                    
                    DiagIcd10 dx10 = theHO.initDiagICD10(theHO.theVisit.getObjectId()
                    ,icd10.icd10_id
                            ,theHO.theDiagDoctorClinic.doctor_id
                            ,theHO.theDiagDoctorClinic.clinic_id);
                    theDiagnosisControl.intSaveDiagIcd10(dx10,theHO.vDiagIcd10,theUS);
                    theHO.vDiagIcd10 = thePcuDB.theDiagIcd10DB.selectByVidSort(theHO.theVisit.getObjectId());
                    theHS.theDiagnosisSubject.notifyManageDiagIcd10("การบันทึกรหัสโรคเสร็จสิ้น",UpdateStatus.COMPLETE);
                }
            }
            theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"),UpdateStatus.COMPLETE);
            if(bornmch != null)
                objectid = bornmch.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveBornMch,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveBornMch,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveBornMch,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveBornMch,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {   theConnectionInf.close();
        }
        return bornmch;
    }
    public BornMch saveBornMCHMother2(BornMch bornmch)
    {
        Constant.println(UseCase.UCID_saveBornMch);
        String objectid =   null;
        int res= 0;
        if(this.theHC.theHO.theVisit==null)
        {
            this.theUS.setStatus("กรุณานำผู้ป่วยเข้าสู่กระบวนการ", UpdateStatus.WARNING);
            return null;
        }
//        if(!this.theHC.theHO.theVisit.service_location.equals("2"))
//        {
//            this.theUS.setStatus("กรุณาระบุประเภท Visit เป็นให้บริการนอกหน่วยบริการ", UpdateStatus.WARNING);
//            return null;
//        }
        if(bornmch==null)
        {
            theUS.setStatus("ข้อมูลผิดพลาดกรุณาตรวจสอบอีกครั้ง",UpdateStatus.WARNING);
            return null;
        }

        if(bornmch.bplace.equals(PostpartumBirthPlace.isHospital())
        || bornmch.bplace.equals(PostpartumBirthPlace.isPCU()))
        {
            if(bornmch.bhosp.equals("")) {
                theUS.setStatus("กรุณาระบุสถานที่คลอด",UpdateStatus.WARNING);
                return null;
            }
        }
        else{
            if(!theUS.confirmBox(Constant.getTextBundle("ยืนยันการบันทึก") + " " +
                    Constant.getTextBundle("การคลอดนอกสถานพยาบาล อาจมีผลต่อรายงานที่ออก"),UpdateStatus.WARNING))
                return null;
        }
        if(bornmch.bdate.equals(""))
        {
            theUS.setStatus("กรุณาระบุวันที่คลอด",UpdateStatus.WARNING);
            return null;
        }
        if(bornmch.bdate.startsWith("20"))
        {
            theUS.setStatus(Constant.getTextBundle("วันที่คลอดมีค่าไม่ถูกต้อง")+" "+ bornmch.bdate
                    +" " +
                    Constant.getTextBundle("กรุณาตรวจสอบระบบ"),UpdateStatus.WARNING);
            return null;
        }
        if(thePO.getVisit()!=null)
        {
            int age = Integer.parseInt(thePO.getVisit().patient_age);
            if(age<15){
                boolean ret = theUS.confirmBox(Constant.getTextBundle("ยืนยันการบันทึกข้อมูลการคลอดของผู้ป่วยอายุ") +
                        " " + age + " " +
                        Constant.getTextBundle("ปี"),UpdateStatus.WARNING);
                if(!ret)
                    return null;
            }
        }
        if(thePO.getVisit()!=null && !bornmch.visit_id.equals("")){
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return null;
//            }
//            if(!thePO.getVisit().getObjectId().equals(bornmch.visit_id)
//            && !bornmch.visit_id.equals("")){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return null;
//            }
        }
//        if(thePO.getVisit()==null && !bornmch.visit_id.equals("")){
//            theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//            return null;
//        }
        theConnectionInf.open();
        try
        {
            bornmch.family_id = this.thePO.getFamily().getObjectId();
            bornmch.user_modify = this.thePO.getEmployee().getObjectId();

            if(bornmch.getObjectId() == null)
            {
                if(this.thePO.getPatient()!=null)
                    bornmch.patient_id = this.thePO.getPatient().getObjectId();
                if(this.thePO.getVisit()!=null)
                    bornmch.visit_id = this.thePO.getVisit().getObjectId();
                bornmch.user_record = this.thePO.getEmployee().getObjectId();
                bornmch.recordtime = this.thePO.getCurrentDateTime();
                res = thePcuDB.theBornMchDB.insert(bornmch);
            }
            else
                res = thePcuDB.theBornMchDB.update(bornmch);

            thePO.vBornMch = thePcuDB.theBornMchDB.selectShowTableByFamily(bornmch.family_id);
            if(res>0)
            {
                //.bresult is key_id of icd10
                ICD10 icd10 = thePcuDB.theICD10DB.selectByPK(bornmch.bresult);
                if(icd10 !=null && theHO.theVisit!=null)
                {
                    DialogOffDrug theDialogUseDoctor = new DialogOffDrug(theUS.getJFrame(),true,theLookupControl,theUS);
                    theDialogUseDoctor.setTitle("เลือกแพทย์ผู้ให้รหัส");
                    theDialogUseDoctor.setuseMapDXICD10(true);
                    theDialogUseDoctor.showDialog(theHO.theDiagDoctorClinic);

                    DiagIcd10 dx10 = theHO.initDiagICD10(theHO.theVisit.getObjectId()
                    ,icd10.icd10_id
                            ,theHO.theDiagDoctorClinic.doctor_id
                            ,theHO.theDiagDoctorClinic.clinic_id);
                    theDiagnosisControl.intSaveDiagIcd10(dx10,theHO.vDiagIcd10,theUS);
                    theHO.vDiagIcd10 = thePcuDB.theDiagIcd10DB.selectByVidSort(theHO.theVisit.getObjectId());
                    theHS.theDiagnosisSubject.notifyManageDiagIcd10("การบันทึกรหัสโรคเสร็จสิ้น",UpdateStatus.COMPLETE);
                }
            }
            theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"),UpdateStatus.COMPLETE);
            if(bornmch != null)
                objectid = bornmch.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveBornMch,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveBornMch,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveBornMch,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveBornMch,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {   theConnectionInf.close();
        }
        return bornmch;
    }
    
    
    /**
     * ใช้ในการลบข้อมูลของ การเข้ารับบริการหลังคลอด โดยจะลบออกจากตาราง
     * หรือ จะ update active เป็น 0 จะขึ้นอยู่กับข้อมูลเวลาที่บันทึก
     * ถ้าข้อมูลที่ลบได้มีการปันทึกข้อมูลการเข้ารับบริการหลังคลอดแล้ว จะไม่ให้ลบ
     * @param bornmch Object ของ BornMch
     * @return เป็น int ถ้าผลการ return เป็น -2 แสดงว่าไม่สามารถลบได้ เพราะ
     * มีข้อมูลเข้ารับบริการหลังคลอดแล้ว
     */
    public int deleteBornMchMotherByKeyID(BornMch bornmch)
    {
        Constant.println(UseCase.UCID_deleteBornMch);
        String objectid =   null;
        iresult = 0;
        System.out.println("public int deleteBornMchMotherByKeyID(BornMch bornmch)");
        //หากรายการที่เลือกเป็นของ visit เก่า
//        if(!bornmch.visit_id.equals("")){
//            //ต้องตรวจสอบว่าเขาได้เลือก visit นั้นอยู่หรือเปล่า
//            if(thePO.getVisit()==null || !thePO.getVisit().getObjectId().equals(bornmch.visit_id)){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//            System.out.println("thePO.getVisit().visit_status" + thePO.getVisit().visit_status);
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//        }
        if(!theUS.confirmBox(GutilPCU.getTextBundle("VerifyBeforeDelete"),UpdateStatus.WARNING)) {
            return 0;
        }
        theConnectionInf.open();
        try{
            if(bornmch == null)
            {
                theUS.setStatus("กรุณาเลือกรายการที่ต้องการยกเลิก",UpdateStatus.WARNING);
                return 0;
            }
            iresult = -2;
            bornmch.active = "0";
            bornmch.user_cancel = thePO.getEmployee().getObjectId();
            bornmch.updatetime = thePO.getCurrentDateTime();
            if(Gutil.isToday(Gutil.getDateFromText(bornmch.recordtime)))
                iresult = this.thePcuDB.theBornMchDB.delete(bornmch);
            else
                iresult = this.thePcuDB.theBornMchDB.updateActive(bornmch);
            
            thePO.vBornMch = thePcuDB.theBornMchDB.selectShowTableByFamily(bornmch.family_id);
            theUS.setStatus("การลบข้อมูลเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(bornmch != null)
                objectid = bornmch.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteBornMch,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteBornMch,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteBornMch,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteBornMch,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return iresult;
    }
    
    /**
     * ใช้ในการค้นหาข้อมูลการคลอดตาม key ID ของตาราง
     * @param key_id เป็น String ของ key หลักของตาราง
     * @return Object ของ AfterMchMother
     */
    public BornMch selectBornMchMotherByKeyID(String key_id)
    {
        theConnectionInf.open();
        
        vc = null;
        try
        {
            if(key_id!= null)
            {
                object = this.thePcuDB.theBornMchDB.selectByPK(key_id);
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
        return (BornMch)object;
    }
    
    /**
     * ใช้ในการแสดงผลลงตารางของ GUI โดยจะ นำมาแสดงไม่ครบทุกส่วน
     * @author ผดุงรัฐ
     * @param visit_id Visit_id ของผู้ป่วยที่ต้องการ
     * @return เป็น Vector ของ Object BornMch
     */
    public Vector selectBornMchMotherShowGUITableByVisitID(String visit_id)
    {
        theConnectionInf.open();
        vc = null;
        try
        {
            if(visit_id!= null)
            {
                vc = this.thePcuDB.theBornMchDB.selectShowTableByVisitID(visit_id);
                
                if(vc != null)
                {
                    int size = vc.size();
                    Constant.println("Size  : " + size);
                    for(int i =0 ; i < size;i++)
                    {
                        theAfterMchMother = (AfterMchMother)vc.get(i);
                        theAfterMchMother.setVN(selectVNByKeyID(theAfterMchMother.visit_id));
                        //Constant.println("remove at : " + i);
                        vc.remove(i);
                        vc.add(i,theAfterMchMother);
                        
                        theAfterMchMother =null;
                    }
                }
                
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
        return vc;
    }
    
    
    /**
     * ใช้ในการแสดงผลลงตารางของ GUI โดยจะ นำมาแสดงไม่ครบทุกส่วน
     * @author ผดุงรัฐ
     *
     * @return เป็น Vector ของ Object BornMch
     */
    public Vector selectBornMchMotherShowGUITableByPatientID(String patient_id)
    {
        theConnectionInf.open();
        vc = null;
        try
        {
            if(patient_id != null)
            {
                vc = this.thePcuDB.theBornMchDB.selectShowTableByPatientID(patient_id);
                
                if(vc != null)
                {
                    int size = vc.size();
                    Constant.println("Size  : " + size);
                    for(int i =0 ; i < size;i++)
                    {
                        theBornMch = (BornMch)vc.get(i);
                        theBornMch.setVN(selectVNByKeyID(theBornMch.visit_id));
                        //Constant.println("remove at : " + i);
                        vc.remove(i);
                        vc.add(i,theBornMch);
                        
                        theBornMch =null;
                    }
                }
                
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
        return vc;
    }
    
    public Vector selectBornMchMotherShowGUITableByFamilyID()
    {
        return thePO.vBornMch;
    }
    /**
     * ค้นหาข้อมูลการคลอดโดยใช้ Family ID
     * @param
     * @return
     * @author kingland
     * @date 17-03-2549
     * @not deprecated henbe มันดึงมาตอนเลือกประชากรอยู่แล้วนะ
     *  ตอนเปิด dialog ต้องใช้อันนี้
     */
    public Vector selectBornMchMotherShowGUITableByFamilyID(String family_id)
    {
        theConnectionInf.open();
        try
        {
            thePO.vBornMch = this.thePcuDB.theBornMchDB.selectShowTableByFamily(family_id);
//              if(vc != null)
//              {
//                  int size = vc.size();
//                  Constant.println("Size  : " + size);
//                  for(int i =0 ; i < size;i++)
//                  {
//                      theBornMch = (BornMch)vc.get(i);
//                      if(theBornMch.visit_id != null && !"".equalsIgnoreCase(theBornMch.visit_id))
//                      {
//                          theBornMch.setVN(selectVNByKeyID(theBornMch.visit_id));
//                          //Constant.println("remove at : " + i);
//                          vc.remove(i);
//                          vc.add(i,theBornMch);
//                      }
//                      theBornMch =null;
//                  }
//              }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return thePO.vBornMch;
    }
    public BornMch readBornMchMotherByFamilyIDAndGravida(String family_id,String gravida)
    {
        BornMch bornMch = null;
        theConnectionInf.open();
        try
        {
            bornMch = this.thePcuDB.theBornMchDB.readByFamilyAndGravida(family_id,gravida);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return bornMch;
    }
    
    /**
     * ใช้ในการแสดงผลลงตารางของ GUI โดยจะ นำมาแสดงไม่ครบทุกส่วน
     * @author ผดุงรัฐ
     * @param visit_id Visit_id ของผู้ป่วยที่ต้องการ
     * @return เป็น Vector ของ Object BornMch
     *
     */
    private Vector selectBornMchMotherShowGUITableByPatientID1(String patient_id)
    {
        theConnectionInf.open();
        vc = null;
        try
        {
            if(patient_id!= null)
            {
                vc = this.thePcuDB.theBornMchDB.selectShowTableByPatientID(patient_id);
                
                if(vc != null)
                {
                    int size = vc.size();
                    Constant.println("Size  : " + size);
                    for(int i =0 ; i < size;i++)
                    {
                        theBornMch = (BornMch)vc.get(i);
                        theBornMch.setVN(selectVNByKeyID(theBornMch.visit_id));
                        
                        vc.remove(i);
                        vc.add(i,theBornMch);
                        
                        theBornMch =null;
                    }
                }
                
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
        return vc;
    }
    
    /**
     * ใช้ในการตรวจสอบข้อมูลที่อยู่ในตาราง AfterMchMother(การเข้ารับบริการหลังคลอด)
     * ว่ามีข้อมูลอยู่หรือไม่ตามการค้นหาของ key id ของ mch_id
     * @param visit_id เป็น String ของ key id ของ visit
     * @return เป็น boolean ถ้าเป็น true แสดงว่ามีข้อมูลอยู่ ถ้าเป็น false ไม่มีข้อมูล
     */
    public boolean checkDataAfterMchMotherByMCHID(String mch_id,String carenumber)
    {     result = false;
          int count = -1;
          try
          {
              theConnectionInf.open();
              count = thePcuDB.theAfterMchMotherDB.countAfterMchMotherByMCHID(mch_id,carenumber);
//          Constant.println("Result : 3.1 " + count);
              if(count >0)
                  result = true;
              
          }
          catch(Exception ex)
          {
              ex.printStackTrace();
          }
          finally
          {
              theConnectionInf.close();
          }
          
          return result;
    }
    
    public Vector selectAfterMchMotherShowGUITableByFamilyID()
    {
        return thePO.vAfterMchMother;
    }
    /**
     * ใช้ในการแสดงผลลงตารางของ GUI โดยจะ นำมาแสดงไม่ครบทุกส่วน
     * @author จ้าว
     * @param family_id  ของผู้ป่วยที่ต้องการ
     * @return เป็น Vector ของ Object FamilyPlaning
     * @not deprecated henbe มันดึงมาตอนเลือกประชากรอยู่แล้วนะ
     * หายแล้วเพราะต้องใช้ menu
     */
    public Vector selectAfterMchMotherShowGUITableByFamilyID(String family_id)
    {
        theConnectionInf.open();
        try
        {
            thePO.vAfterMchMother = this.thePcuDB.theAfterMchMotherDB.selectShowTableByFamilyID(family_id);
        }
        catch(Exception ex)
        { ex.printStackTrace();
        }
        finally
        { theConnectionInf.close();
        }
        return thePO.vAfterMchMother;
    }
    public AfterMchMother readAfterMchMotherByFamilyIDAndGravidaAndNumber(String family_id,String gravida,String number)
    {
        AfterMchMother afterMchMother = null;
        theConnectionInf.open();
        try
        {
            afterMchMother = thePcuDB.theAfterMchMotherDB.readByFamilyIDAndGravidaAndNumber(family_id,gravida,number);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return afterMchMother;
    }
    /**
     *
     * @param family_id
     * @return patient_id ของแม่
     */
    public String getMotherByBabyFamilyID(String family_id)
    {
        String res="";
        ResultSet rs = null;
        try
        {
            rs = theConnectionInf.eQuery("select t_patient_id from t_health_pp_care where t_health_family_id = '" + family_id + "'");
            while(rs.next())
            {
                if(!this.theHO.thePatient.getObjectId().equals(rs.getString("t_patient_id")))
                {
                    res = rs.getString("t_patient_id");
                    break;
                }
            }
                
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return res;
    }
    public BornMch readMchByFidAndGravida(String family_id,String gravida)
    {
        BornMch bornMch = null;
        theConnectionInf.open();
        try
        {
            bornMch = thePcuDB.theBornMchDB.readMchByFidAndGravida(family_id, gravida);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return bornMch;
    }
    public BornMch readMchByPidAndGravida(String patient_id,String gravida)
    {
        BornMch bornMch = null;
        theConnectionInf.open();
        try
        {
            bornMch = thePcuDB.theBornMchDB.readMchByPidAndGravida(patient_id, gravida);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return bornMch;
    }
    public PP readPPByPatientId(String search)
    {
        PP thePP = null;
        theConnectionInf.open();
        try
        {
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
        try
        {
            thePO.thePP = thePcuDB.thePPDB.readPPByFamilyID(family_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return thePO.thePP;
    }
    public Vector listPPByMotherID(String pid)
    {
        if(pid.equals(""))
            return new Vector();
        
        theConnectionInf.open();
        try
        {
            return thePcuDB.thePPDB.selectByMotherPid(pid);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return new Vector();
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public boolean checkStatusVisitOfPP(String search)
    {
        theConnectionInf.open();
        boolean temp = false;
        try
        {
            if(theVisit != null)
            {
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
        Constant.println(UseCase.UCID_savePP);
        String objectid =   null;
        fam = thePO.getFamily();
        if(this.theHC.theHO.theVisit==null)
        {
            this.theUS.setStatus("กรุณานำผู้ป่วยเข้าสู่กระบวนการ", UpdateStatus.WARNING);
            return null;
        }
//        if(!this.theHC.theHO.theVisit.service_location.equals("2"))
//        {
//            this.theUS.setStatus("กรุณาระบุประเภท Visit เป็นให้บริการนอกหน่วยบริการ", UpdateStatus.WARNING);
//            return null;
//        }
        if (thePO.getPatient() == null)
        {
            theUS.setStatus("กรุณาเลือกผู้ป่วยหรือบันทึกข้อมูลผู้ป่วยก่อน",UpdateStatus.WARNING);
            return null;
        }
        if(theHO.theVisit==null && pp.survey_date.length() != 10)
        {
            theUS.setStatus(GutilPCU.getTextBundle("WarningNotHaveSurVeyDate"), UpdateStatus.WARNING);
            return null;
        }
        if(("").equals(pp.pp_number) || ("0").equals(pp.pp_number))
        {
            theUS.setStatus(GutilPCU.getTextBundle("FillNumberPP"),UpdateStatus.WARNING);
            return null;
        }
        if(("").equals(pp.pp_gravida) || ("0").equals(pp.pp_gravida))
        {
            theUS.setStatus(GutilPCU.getTextBundle("FillGravida"),UpdateStatus.WARNING);
            return null;
        }
        if(pp.pp_mother_pid.equals(""))
        {
            if(!theUS.confirmBox("ยืนยันการไม่ระบุเลขบัตรประชาชนของมารดา",UpdateStatus.WARNING))
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
//        if(thePO.getFamily().f_sex_id.equals(Sex.isMAN()))
//        {
//            theUS.setStatus("ผู้ป่วยปัจจุบันเป็นชาย กรุณาเลือกผู้ป่วยที่เป็นมารดาของเด็ก",UpdateStatus.WARNING);
//            return null;
//        }
        if(pp.pp_modify_date_time.length()<10)
        {
            theUS.setStatus("กรุณาระบุวันที่บันทึก",UpdateStatus.WARNING);
            return null;
        }
        if(theLookupControl.isDateFuture(pp.pp_modify_date_time))
        {
            theUS.setStatus("กรุณาระบุวันที่บันทึกเป็นวันในอตีต",UpdateStatus.WARNING);
            return null;
        }
        //09/12/2010 ต้นเอาออกเพื่อให้ผู้ใช้ทำงานได้สะดวกขึ้น
//        if(thePO.getVisit()!=null && !pp.t_visit_id.equals("")){
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return null;
//            }
//            if(!thePO.getVisit().getObjectId().equals(pp.t_visit_id)
//            && !pp.t_visit_id.equals("")){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return null;
//            }
//        }
//        if(thePO.getVisit()==null && !pp.t_visit_id.equals("")){
//            theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//            return null;
//        }
        theConnectionInf.open();
        try{
            //ถ้าเลขบัตรประชาชนแม่เป็นเลขเดี่ยวกันกันผู้ป่วยแสดงว่าบันทึกข้อมูลของลูกจะต้อง
            //บันทึกประชากรใหม่ด้วยแล้วจึงระบุ family_id ของ pp เป็นของลูก
            pp.pp_staff_modify = thePO.getEmployee().getObjectId();
            if(pp.pp_modify_date_time.equals(""))
                pp.pp_modify_date_time = thePO.getCurrentDateTime();
            
            if(pp.getObjectId()==null)
            {
                if(thePO.getPatient() != null)
                    pp.t_patient_id = thePO.getPatient().getObjectId();
                if(thePO.getVisit() != null)
                    pp.t_visit_id = thePO.getVisit().getObjectId() ;
                
                Family fm_child = new Family();
                fm_child.f_sex_id = sex_id;
//                fm_child.f_sex_id = "3";//เพศไม่ระบุ
                fm_child.patient_name = "บุตรนาง" + fam.patient_name;
                fm_child.patient_last_name = fam.patient_last_name;
                fm_child.f_prefix_id = Prefix.UNDEFINE;
                if(sex_id.equals(Sex.isWOMAN()))
                    fm_child.f_prefix_id = Prefix.GIRL;
                if(sex_id.equals(Sex.isMAN()))
                    fm_child.f_prefix_id = Prefix.BOY;
                fm_child.patient_birthday_true = "1";
                fm_child.patient_birthday = thePO.getCurrentDateTime().substring(0, 10);
                fm_child.home_id = fam.home_id;
                //henbe modify technic for keep mother family_id
                fm_child.mother_firstname = fam.patient_name;
                fm_child.mother_lastname = fam.patient_last_name;
                fm_child.mother_pid = fam.pid;
                fm_child.mother_fid = fam.getObjectId();
                fm_child.marriage_status_id = MarryStatus.SINGLE;
                fm_child.nation_id = fam.nation_id;
                fm_child.race_id = fam.race_id;
                fm_child.religion_id = fam.religion_id;
                fm_child.occupation_id = "000";
                thePatientControl.intUDSaveFamily(fm_child,theHO.theHome,true,theUS);
                Patient pt = thePatientControl.intUDSavePatient(fm_child,theHO.date_time,true);
                Visit visit = new Visit();
                visit.visit_note = "เพิ่งคลอด";
                theVisitControl.intUDVisitPatient(pt,visit, theHO.vVisitPayment
                        ,theHO.theServicePoint,theHO.theQV,theHO.date_time);
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
            else{
                if(pp.pp_active.equals("0"))
                    theConnectionInf.eUpdate("update t_health_family" +
                            " set health_family_active = '0'" +
                            " where t_health_family_id = '"+pp.family_id+"'");
                thePcuDB.thePPDB.update(pp);
            }
            
            thePO.thePP = pp;
            theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"),UpdateStatus.COMPLETE);
            if(pp != null)
                objectid = pp.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_savePP,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_savePP,objectid,null,UpdateStatus.COMPLETE);
            return pp;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_savePP,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_savePP,objectid,ex,UpdateStatus.ERROR);
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public boolean deletePP(PP pp)
    {
        Constant.println(UseCase.UCID_deletePP);
        String objectid =   null;
        if(pp.getObjectId()==null){
            theUS.setStatus("กรุณาเลือกรายการที่ต้องการลบ",UpdateStatus.WARNING);
            return false;
        }
        //หากรายการที่เลือกเป็นของ visit เก่า
//        if(!pp.t_visit_id.equals("")){
//            //ต้องตรวจสอบว่าเขาได้เลือก visit นั้นอยู่หรือเปล่า
//            if(thePO.getVisit()==null || !thePO.getVisit().getObjectId().equals(pp.t_visit_id)){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return false;
//            }
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return false;
//            }
//        }
        if(!theUS.confirmBox(GutilPCU.getTextBundle("VerifyBeforeDelete"),UpdateStatus.WARNING)) {
            return false;
        }
        theConnectionInf.open();
        try{
            pp.pp_staff_cancel = thePO.getEmployee().getObjectId();
            pp.pp_cancel_date_time = thePO.getCurrentDateTime();
            pp.pp_active = "0";
            theConnectionInf.eUpdate("update t_health_family" +
                    " set health_family_active = '0'" +
                    " where t_health_family_id = '"+pp.family_id+"'");
            thePcuDB.thePPDB.update(pp);
            thePO.thePP = null;
            theUS.setStatus(GutilPCU.getTextBundle("การลบข้อมูลทารกเสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(pp != null)
                objectid = pp.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deletePP,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deletePP,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deletePP,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deletePP,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public Patient readPatientByHn(String search)
    {
        theConnectionInf.open();
        try
        {
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
        if(this.theHC.theHO.theVisit==null)
        {
            this.theUS.setStatus("กรุณานำผู้ป่วยเข้าสู่กระบวนการ", UpdateStatus.WARNING);
            return 0;
        }
//        if(!this.theHC.theHO.theVisit.service_location.equals("2"))
//        {
//            this.theUS.setStatus("กรุณาระบุประเภท Visit เป็นให้บริการนอกหน่วยบริการ", UpdateStatus.WARNING);
//            return 0;
//        }
        Constant.println(UseCase.UCID_savePPcare);
        String objectid =   null;
        int result = 0 ;
        if(ppCare.family_id.equals("")){
            theUS.setStatus("กรุณาเลือกประชากรก่อนการบันทึก",UpdateStatus.WARNING);
            return result;
        }
        if(ppCare.pp_care_modify_date_time.length()<10){
            theUS.setStatus("กรุณาระบุวันที่บันทึก",UpdateStatus.WARNING);
            return 0;
        }
        if(ppCare.pp_care_result.equals(FamilyPlaningTreatment.isNotTreatment())){
            theUS.setStatus("กรุณาระบุผลการตรวจว่าปกติหรือผิดปกติ",UpdateStatus.WARNING);
            return 0;
        }
        if(theLookupControl.isDateFuture(ppCare.pp_care_modify_date_time)) {
            theUS.setStatus("กรุณาระบุวันที่บันทึกเป็นวันในอตีต",UpdateStatus.WARNING);
            return 0;
        }
//        if(thePO.getVisit()!=null && !ppCare.visit_id.equals("")){
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//            if(!thePO.getVisit().getObjectId().equals(ppCare.visit_id)
//            && !ppCare.visit_id.equals("")){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//        }
//        if(thePO.getVisit()==null && !ppCare.visit_id.equals("")){
//            theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//            return 0;
//        }
        if(!thePO.getFamily().getObjectId().equals(ppCare.family_id) && !ppCare.pp_care_number.equals("1")){
            theUS.setStatus("กรุณานำลูกเข้าสู่กระบวนการแล้วบันทึกใน Visit ของลูก",UpdateStatus.WARNING);
            return 0;
        }
//        else if(!thePO.getFamily().getObjectId().equals(ppCare.family_id) && ppCare.pp_care_number.equals("1"))
//        {
//            ppCare.family_id = thePO.getFamily();
//        }
        theConnectionInf.open();
        try{
            ppCare.pp_care_staff_modify = thePO.getEmployee().getObjectId();
            //เป็นข้อมูลของลูกที่เลือกจากหน้าจอโปรแกรมได้บันทึกข้อมูล family เรียบร้อยแล้ว
//            if(thePO.getFamily() != null) {
//                ppCare.family_id  = thePO.getFamily().getObjectId();
//            }
            
            if(ppCare.getObjectId() == null)
            {
                if(thePO.getPatient() != null && ppCare.patient_id.equals("")) {
                    ppCare.patient_id = thePO.getPatient().getObjectId();
                }
                if(thePO.getVisit() != null)
                    ppCare.visit_id = thePO.getVisit().getObjectId() ;
                ppCare.pp_care_staff_record = thePO.getEmployee().getObjectId();
                ppCare.pp_care_record_date_time =  thePO.getCurrentDateTime();
                ppCare.pp_care_active = "1";
                result = thePcuDB.thePPCareDB.insert(ppCare);
            }
            else
                result = thePcuDB.thePPCareDB.update(ppCare);

            thePO.vPPCare = thePcuDB.thePPCareDB.listPPCareByFamilyID(ppCare.family_id);
            theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"),UpdateStatus.COMPLETE);
            if(ppCare != null)
                objectid = ppCare.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_savePPcare,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_savePPcare,objectid,null,UpdateStatus.COMPLETE);
            return result;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_savePPcare,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_savePPcare,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {    theConnectionInf.close();
        }
        return result;
    }
    public int saveOrUpdatePPCare2(PPCare ppCare)
    {
        if(this.theHC.theHO.theVisit==null)
        {
            this.theUS.setStatus("กรุณานำผู้ป่วยเข้าสู่กระบวนการ", UpdateStatus.WARNING);
            return 0;
        }
//        if(!this.theHC.theHO.theVisit.service_location.equals("2"))
//        {
//            this.theUS.setStatus("กรุณาระบุประเภท Visit เป็นให้บริการนอกหน่วยบริการ", UpdateStatus.WARNING);
//            return 0;
//        }
        Constant.println(UseCase.UCID_savePPcare);
        String objectid =   null;
        int result = 0 ;
        if(ppCare.family_id.equals("")){
            theUS.setStatus("กรุณาเลือกประชากรก่อนการบันทึก",UpdateStatus.WARNING);
            return result;
        }
        if(ppCare.pp_care_modify_date_time.length()<10){
            theUS.setStatus("กรุณาระบุวันที่บันทึก",UpdateStatus.WARNING);
            return 0;
        }
        if(ppCare.pp_care_result.equals(FamilyPlaningTreatment.isNotTreatment())){
            theUS.setStatus("กรุณาระบุผลการตรวจว่าปกติหรือผิดปกติ",UpdateStatus.WARNING);
            return 0;
        }
        if(theLookupControl.isDateFuture(ppCare.pp_care_modify_date_time)) {
            theUS.setStatus("กรุณาระบุวันที่บันทึกเป็นวันในอตีต",UpdateStatus.WARNING);
            return 0;
        }
        if(thePO.getVisit()!=null && !ppCare.visit_id.equals("")){
            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
                return 0;
            }
//            if(!thePO.getVisit().getObjectId().equals(ppCare.visit_id)
//            && !ppCare.visit_id.equals("")){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
        }
//        if(thePO.getVisit()==null && !ppCare.visit_id.equals("")){
//            theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//            return 0;
//        }
//        if(!thePO.getFamily().getObjectId().equals(ppCare.family_id) && !ppCare.pp_care_number.equals("1")){
//            theUS.setStatus("กรุณานำลูกเข้าสู่กระบวนการแล้วบันทึกใน Visit ของลูก",UpdateStatus.WARNING);
//            return 0;
//        }
//        else if(!thePO.getFamily().getObjectId().equals(ppCare.family_id) && ppCare.pp_care_number.equals("1"))
//        {
//            ppCare.family_id = thePO.getFamily();
//        }
        theConnectionInf.open();
        try{
            ppCare.pp_care_staff_modify = thePO.getEmployee().getObjectId();
            //เป็นข้อมูลของลูกที่เลือกจากหน้าจอโปรแกรมได้บันทึกข้อมูล family เรียบร้อยแล้ว
//            if(thePO.getFamily() != null) {
//                ppCare.family_id  = thePO.getFamily().getObjectId();
//            }

            if(ppCare.getObjectId() == null)
            {
                if(thePO.getPatient() != null && ppCare.patient_id.equals("")) {
                    ppCare.patient_id = thePO.getPatient().getObjectId();
                }
//                if(thePO.getVisit() != null)
//                    ppCare.visit_id = thePO.getVisit().getObjectId() ;
                ppCare.pp_care_staff_record = thePO.getEmployee().getObjectId();
                ppCare.pp_care_record_date_time =  thePO.getCurrentDateTime();
                ppCare.pp_care_active = "1";
                result = thePcuDB.thePPCareDB.insert(ppCare);
            }
            else
                result = thePcuDB.thePPCareDB.update(ppCare);

            thePO.vPPCare = thePcuDB.thePPCareDB.listPPCareByFamilyID(ppCare.family_id);
            theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"),UpdateStatus.COMPLETE);
            if(ppCare != null)
                objectid = ppCare.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_savePPcare,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_savePPcare,objectid,null,UpdateStatus.COMPLETE);
            return result;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_savePPcare,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_savePPcare,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {    theConnectionInf.close();
        }
        return result;
    }
    public int saveOrUpdatePPCareN(PPCare ppCare,PPCareChild ppCareChild)
    {
        if(this.theHC.theHO.theVisit==null)
        {
            this.theUS.setStatus("กรุณานำผู้ป่วยเข้าสู่กระบวนการ", UpdateStatus.WARNING);
            return 0;
        }
//        if(!this.theHC.theHO.theVisit.service_location.equals("2"))
//        {
//            this.theUS.setStatus("กรุณาระบุประเภท Visit เป็นให้บริการนอกหน่วยบริการ", UpdateStatus.WARNING);
//            return 0;
//        }
        Constant.println(UseCase.UCID_savePPcare);
        String objectid =   null;
        int result = 0 ;
        if(ppCare.family_id.equals("")){
            theUS.setStatus("กรุณาเลือกประชากรก่อนการบันทึก",UpdateStatus.WARNING);
            return result;
        }
        if(ppCare.pp_care_modify_date_time.length()<10){
            theUS.setStatus("กรุณาระบุวันที่บันทึก",UpdateStatus.WARNING);
            return 0;
        }
        if(ppCare.pp_care_result.equals(FamilyPlaningTreatment.isNotTreatment())){
            theUS.setStatus("กรุณาระบุผลการตรวจว่าปกติหรือผิดปกติ",UpdateStatus.WARNING);
            return 0;
        }
        if(theLookupControl.isDateFuture(ppCare.pp_care_modify_date_time)) {
            theUS.setStatus("กรุณาระบุวันที่บันทึกเป็นวันในอตีต",UpdateStatus.WARNING);
            return 0;
        }
        if(thePO.getVisit()!=null && !ppCare.visit_id.equals("")){
            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
                return 0;
            }
            if(!thePO.getVisit().getObjectId().equals(ppCare.visit_id)
            && !ppCare.visit_id.equals("")){
                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
                return 0;
            }
        }
        if(thePO.getVisit()==null && !ppCare.visit_id.equals("")){
            theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
            return 0;
        }
        if(!thePO.getFamily().getObjectId().equals(ppCare.family_id) && !ppCare.pp_care_number.equals("1")){
            theUS.setStatus("กรุณานำลูกเข้าสู่กระบวนการแล้วบันทึกใน Visit ของลูก",UpdateStatus.WARNING);
            return 0;
        }
//        else if(!thePO.getFamily().getObjectId().equals(ppCare.family_id) && ppCare.pp_care_number.equals("1"))
//        {
//            ppCare.family_id = thePO.getFamily();
//        }
        theConnectionInf.open();
        try{
            ppCare.pp_care_staff_modify = thePO.getEmployee().getObjectId();
            //เป็นข้อมูลของลูกที่เลือกจากหน้าจอโปรแกรมได้บันทึกข้อมูล family เรียบร้อยแล้ว
//            if(thePO.getFamily() != null) {
//                ppCare.family_id  = thePO.getFamily().getObjectId();
//            }

            if(ppCare.getObjectId() == null)
            {
                if(thePO.getPatient() != null && ppCare.patient_id.equals("")) {
                    ppCare.patient_id = thePO.getPatient().getObjectId();
                }
                if(thePO.getVisit() != null)
                    ppCare.visit_id = thePO.getVisit().getObjectId() ;
                ppCare.pp_care_staff_record = thePO.getEmployee().getObjectId();
                ppCare.pp_care_record_date_time =  thePO.getCurrentDateTime();
                ppCare.pp_care_active = "1";
                ppCareChild.generateOID("");
                ppCare.t_health_pp_care_child_id = ppCareChild.getObjectId();
                result = thePcuDB.thePPCareDB.insertN(ppCare);
                ppCareChild.t_health_pp_care_id = ppCare.getObjectId();
                ppCareChild.t_visit_id = thePO.getVisit().getObjectId();
                ppCareChild.t_health_family_id = ppCare.family_id;
                result = thePcuDB.thePPCareChildDB.insert(ppCareChild);
            }
            else
                result = thePcuDB.thePPCareDB.updateN(ppCare);
                result = thePcuDB.thePPCareChildDB.update(ppCareChild);
            thePO.vPPCare = thePcuDB.thePPCareDB.listPPCareByFamilyID(ppCare.family_id);
            theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"),UpdateStatus.COMPLETE);
            if(ppCare != null)
                objectid = ppCare.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_savePPcare,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_savePPcare,objectid,null,UpdateStatus.COMPLETE);
            return result;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_savePPcare,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_savePPcare,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {    theConnectionInf.close();
        }
        return result;
    }

    public int deletePPCare(PPCare ppCare)
    {
        Constant.println(UseCase.UCID_deletePPcare);
        String objectid =   null;
        int result = 0 ;
        //ถ้า Visit แล้วทำการลบ
        if(ppCare == null){
            theUS.setStatus("ไม่พบข้อมูลที่จะทำการลบ", UpdateStatus.WARNING);
            return result;
        }
        //หากรายการที่เลือกเป็นของ visit เก่า
//        if(!ppCare.visit_id.equals("")){
//            //ต้องตรวจสอบว่าเขาได้เลือก visit นั้นอยู่หรือเปล่า
//            if(thePO.getVisit()==null || !thePO.getVisit().getObjectId().equals(ppCare.visit_id)){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//        }
        if(!theUS.confirmBox(GutilPCU.getTextBundle("VerifyBeforeDelete"),UpdateStatus.WARNING)) {
            return result;
        }
        theConnectionInf.open();
        try{
            ppCare.pp_care_staff_cancel = thePO.getEmployee().getObjectId();
            ppCare.pp_care_cancel_date_time = thePO.getCurrentDateTime();
            ppCare.pp_care_active = "0";
            result = thePcuDB.thePPCareDB.update(ppCare);
            thePO.vPPCare = thePcuDB.thePPCareDB.listPPCareByFamilyID(ppCare.family_id);
            theUS.setStatus(GutilPCU.getTextBundle("การยกเลิกข้อมูลเสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(ppCare != null)
                objectid = ppCare.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deletePPcare,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deletePPcare,objectid,null,UpdateStatus.COMPLETE);
            return result;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deletePPcare,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deletePPcare,objectid,null,UpdateStatus.COMPLETE);
            return result;
        }
        finally{    
            theConnectionInf.close();
        }
    }
        
    public String readVnByVisitId(String search)
    {  String vn = "";
       if(search.equals(""))
           return "";
       theConnectionInf.open();
       try
       {
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
        Vector vVector = new Vector();
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
        try
        {
            thePO.vPPCare = thePcuDB.thePPCareDB.listPPCareByFamilyID(family_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return thePO.vPPCare;
    }
    public PPCare readPPCareByFamilyIDAndGravida(String family_id,String gravida)
    {
        PPCare ppCare = null;
        theConnectionInf.open();
        try
        {
            ppCare = thePcuDB.thePPCareDB.readPPCareByFamilyIDAndGravida(family_id, gravida);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        theConnectionInf.close();
        return ppCare;
    }
    /**
     * แสดงข้อความเตือน
     * @param message = ข้อความที่ต้องการให้แสดง
     *        status = สถานะที่แสดง
     * @return void
     * @author kingland
     * @date 28/08/2549
     */
    
    public Vector listPPCareByMotherID(String mother_id)
    {
        theConnectionInf.open();
        try
        {
            if(!mother_id.equals(""))
                return thePcuDB.thePPDB.selectByMotherPid(mother_id);
            return new Vector();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public Vector listPPByPatientID(String patient_id) {
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
    /**
     * @deprecated henbe unused
     * @param patient_id
     * @return
     */
    public Vector listPPCareByPatientID(String patient_id)
    {
        return listPPByMotherPtid(patient_id);
    }
    /**
     *it is PP data is not PPCare
     */
    public Vector listPPByMotherPtid(String patient_id)
    {
        theConnectionInf.open();
        try
        {
            if(!patient_id.equals(""))
                return thePcuDB.thePPDB.selectByPatientID(patient_id);
            return new Vector();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }

    public PP readPPByChildFid(String fid) {
        theConnectionInf.open();
        try{
            return thePcuDB.thePPDB.selectByFamilyID(fid);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }

    public PP readPPByFidAndGravida(String fid,String gravida) {
        theConnectionInf.open();
        try{
            return thePcuDB.thePPDB.selectByFamilyIDAndGravida(fid,gravida);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }

    public PP readPPByPidAndGravida(String pid,String gravida) {
        theConnectionInf.open();
        try{
            return thePcuDB.thePPDB.readPPByPidAndGravida(pid,gravida);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public String getPPNumberByPidAndGravida(String pid,String gravida) {
        theConnectionInf.open();
        try{
            return thePcuDB.thePPDB.getPPNumberByPidAndGravida(pid,gravida);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public PP readPPByPidAndGravidaAndPPNumber(String pid,String gravida,String number) {
        theConnectionInf.open();
        try{
            return thePcuDB.thePPDB.readPPByPidAndGravidaAndPPNumber(pid,gravida,number);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }

     public PPCare readPPByVid(String vid) {
        theConnectionInf.open();
        try{
            return thePcuDB.thePPCareDB.readByVidN(vid);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public PPCare readPPByPPChildId(String child_id) {
        theConnectionInf.open();
        try{
            return thePcuDB.thePPCareDB.readByPPChildId(child_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
     public PPCare readPPByFid(String fid) {
        theConnectionInf.open();
        try{
            return thePcuDB.thePPCareDB.readByVidN(fid);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public PPCareChild readPPChildByPPCare(String ppcareid) {
        theConnectionInf.open();
        try{
            return thePcuDB.thePPCareChildDB.readByPPCare(ppcareid);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
     }
     public PPCareChild readPPChildByFid(String fid) {
        theConnectionInf.open();
        try{
            return thePcuDB.thePPCareChildDB.readByFid(fid);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public PPCareChild readPPChildByGravida(String fid,String gravida) {
        theConnectionInf.open();
        try{
            return thePcuDB.thePPCareChildDB.readByFidAndGravida(fid,gravida);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public void readPPBySpecialQuery(String f_id)
    {
        theConnectionInf.open();
        String sql = "SELECT  SUB1.t_health_pp_care_id as t_health_pp_care_id"
                + ",b_site.b_visit_office_id AS PCUCODE "
                + ",t_health_family.health_family_hn_hcis as PID "
                + ",mother.health_family_hn_hcis AS MPID "
                + ", t_health_pp.pp_gravida AS GRAVIDA "
                + ", CASE WHEN length(t_health_delivery.health_delivery_born_date)>9   "
                + "THEN   (to_number(substring(t_health_delivery.health_delivery_born_date,1,4),'9999')-543)   "
                + "||  substring(t_health_delivery.health_delivery_born_date,6,2)   "
                + "||  substring(t_health_delivery.health_delivery_born_date,9,2)    "
                + "ELSE '' END as BDATE, t_health_delivery.f_health_postpartum_birth_place_id as BPLACE"
                + ", t_health_delivery.b_visit_office_birth_place as BHOSP"
                + ", CASE WHEN (t_health_delivery.f_health_birth_method_id='1'   "
                + "OR t_health_delivery.f_health_birth_method_id='2'   "
                + "OR t_health_delivery.f_health_birth_method_id='3'   "
                + "OR t_health_delivery.f_health_birth_method_id='4'   "
                + "OR t_health_delivery.f_health_birth_method_id='5')    "
                + "THEN t_health_delivery.f_health_birth_method_id    "
                + "ELSE '' END AS  BTYPE, CASE WHEN (t_health_delivery.f_health_pregnancy_birth_doctor_type_id='1'   "
                + "OR t_health_delivery.f_health_pregnancy_birth_doctor_type_id='2'   "
                + "OR t_health_delivery.f_health_pregnancy_birth_doctor_type_id='3'   "
                + "OR t_health_delivery.f_health_pregnancy_birth_doctor_type_id='4'   "
                + "OR t_health_delivery.f_health_pregnancy_birth_doctor_type_id='5')    "
                + "THEN t_health_delivery.f_health_pregnancy_birth_doctor_type_id   "
                + "ELSE '' END   as BDOCTOR    , t_health_pp.pp_weight AS BWEIGTH    "
                + ", CASE WHEN (t_health_pp.pp_lost_oxygen= '0')THEN '1'  WHEN (t_health_pp.pp_lost_oxygen = '1')  "
                + "THEN '0'  ELSE t_health_pp.pp_lost_oxygen END AS ASPHYXIA    , t_health_pp.pp_vit_k AS VITK    "
                + ", max(CASE WHEN (length(SUB1.pp_care_record_date_time)>=10   and SUB1.pp_care_number ='1')    "
                + "then to_char(to_date(to_number(    substr(SUB1.pp_care_record_date_time,1,4),'9999')-543 ||    "
                + "substr(SUB1.pp_care_record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd') ELSE ''   END) AS BCARE1 "
                + ", max(CASE WHEN (length(SUB1.pp_care_record_date_time)>=10   and SUB1.pp_care_number ='2')    "
                + "then to_char(to_date(to_number(    substr(SUB1.pp_care_record_date_time,1,4),'9999')-543 ||    "
                + "substr(SUB1.pp_care_record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd') ELSE ''   END) AS BCARE2 "
                + ", max(CASE WHEN (length(SUB1.pp_care_record_date_time)>=10   and SUB1.pp_care_number ='3')    "
                + "then to_char(to_date(to_number(    substr(SUB1.pp_care_record_date_time,1,4),'9999')-543 ||    "
                + "substr(SUB1.pp_care_record_date_time,5,6),'yyyy-mm-dd'),'yyyymmdd') ELSE ''   END) AS BCARE3     "
                + ", case when SUB1.pp_care_result<>'2' then '1' else '2' end AS BCRES     "
                + ", max(CASE WHEN (length(SUB1.pp_care_record_date_time)>=10)      "
                + "then (to_number(substring(SUB1.pp_care_record_date_time,1,4),'9999') - 543)     "
                + "|| substring(SUB1.pp_care_record_date_time,6,2)     || substring(SUB1.pp_care_record_date_time,9,2)     "
                + "|| substring(SUB1.pp_care_record_date_time,12,2)     || substring(SUB1.pp_care_record_date_time,15,2)     "
                + "|| substring(SUB1.pp_care_record_date_time,18,2)     ELSE ''  END) "
                + "AS D_UPDATE FROM t_health_pp_care AS SUB1     INNER JOIN t_patient  "
                + "ON SUB1.t_patient_id = t_patient.t_patient_id     INNER JOIN t_health_family  AS mother  "
                + "ON (mother.t_health_family_id = t_patient.t_health_family_id)     INNER JOIN t_health_family "
                + "ON t_health_family.t_health_family_id = SUB1.t_health_family_id     LEFT JOIN  t_health_pp   "
                + "ON (t_health_pp.t_health_family_id= SUB1.t_health_family_id    and t_health_pp.pp_active  = '1')     "
                + "LEFT JOIN t_health_delivery   ON (mother.t_health_family_id = t_health_delivery.t_health_family_id   "
                + " and t_health_pp.pp_gravida = t_health_delivery.gravida_number    "
                + "and t_health_delivery.health_delivery_active = '1')     ,b_site WHERE  SUB1.pp_care_active  = '1'     "
                + "AND mother.health_family_active = '1' and t_health_family.t_health_family_id ='" + f_id + "'"
                + "group by pcucode,pid,mpid,gravida,bdate,bplace,bhosp,btype,bdoctor,bweigth,asphyxia,vitk,BCRES,sub1.t_health_pp_care_id";
        try
        {
            theConnectionInf.eQuery(sql);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
