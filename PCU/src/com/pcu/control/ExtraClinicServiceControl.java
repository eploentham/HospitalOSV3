/*
 * HealthControl.java
 *
 * Created on 17 มิถุนายน 2548, 15:46 น.
 * Modified on 25 กันยายน 2551
 */

package com.pcu.control;

import com.hospital_os.object.VisitStatus;
import com.hosv3.control.HosControl;
import com.hosv3.control.LookupControl;
import java.util.*;
import com.hospital_os.usecase.connection.*;
import com.hosv3.utility.connection.UpdateStatus;
import com.hospital_os.object.Visit;
import com.hosv3.object.UseCase;
import com.hosv3.utility.Constant;
import com.pcu.object.*;
import com.pcu.utility.GutilPCU;

/**
 *
 * @author Noom
 * @modifier pu
 */
public class ExtraClinicServiceControl
{
    ConnectionInf theConnectionInf;
    UpdateStatus theUS;
    private Visit theVisit = new Visit();
    HosDB thePcuDB;
    PCUObject thePO;    
    private Vector vc = null;    
    private LookupControl theLookupControl;
    HosControl theHC;
    
    /** Creates a new instance of HealthControl */
    public ExtraClinicServiceControl()
    {
    }    
    
    public ExtraClinicServiceControl(ConnectionInf con,HosDB hdb,PCUObject po,HosControl hc,UpdateStatus us)
    {
        theConnectionInf = con;
        thePcuDB = hdb;
        theHC = hc;
        theUS = us;
        thePO = po;
        theLookupControl = theHC.theLookupControl;
    }
    public ExtraClinicServiceControl(ConnectionInf con,HosDB hdb,UpdateStatus us,PCUObject po)
    {
        theConnectionInf = con;
        thePcuDB = hdb;
        theUS = us;
        thePO = po;
    }
    /**@deprecated pu : เซ็ต LookupControl ใน constuctor แล้ว*/    
    public void setDepControl(LookupControl lc)
    {
        theLookupControl = lc;
    }
    public int deleteVisitHome(VisitHome theVisitHome)
    {
        Constant.println(UseCase.UCID_deleteVisitHome);
        String objectid =   null;
        if(!theUS.confirmBox(GutilPCU.getTextBundle("ConfirmDeleteVisitHome"),UpdateStatus.WARNING)){
            return 0;
        }
        theConnectionInf.open();
        try{
            theVisitHome.visit_home_active = "0";
            theVisitHome.visit_home_staff_cancle = thePO.getEmployee().getObjectId();
            theVisitHome.visit_home_cancle_time = thePO.getCurrentDateTime();
            int ret = thePcuDB.theVisitHomeDB.update(theVisitHome);
            thePO.vVisitHome = thePcuDB.theVisitHomeDB.selectByFamilyID(theVisitHome.family_id);
            theUS.setStatus("การยกเลิกข้อมูลเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(theVisitHome != null)
                objectid = theVisitHome.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteVisitHome,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteVisitHome,objectid,null,UpdateStatus.COMPLETE);
            return ret;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteVisitHome,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteVisitHome,objectid,ex,UpdateStatus.ERROR);
            return 0;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public void saveVisitHome(VisitHome theVisitHome)
    {
        theConnectionInf.open();
        try
        {
            if(theVisitHome.getObjectId() == null)
            {
                thePcuDB.theVisitHomeDB.insert(theVisitHome);
            }
            else
            {
                thePcuDB.theVisitHomeDB.update(theVisitHome);
            }
            thePO.vVisitHome = thePcuDB.theVisitHomeDB.selectByFamilyID(theVisitHome.family_id);
            theUS.setStatus("การบันทึกข้อมูลเสร็จสิ้น",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            theUS.setStatus("การบันทึกข้อมูลผิดพลาด",UpdateStatus.ERROR);
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public int saveVitalsignHomeVisit(VisitHome visitHome,VitalsignHomeVisit vitalsingHomeVisit)
    {
        Vector vsV = new Vector();
        vsV.add(vitalsingHomeVisit);
        return saveVitalsignHomeVisit(visitHome, vsV);
    }
    /**
     *@author henbe
     *
     */
    public int saveVitalsignHomeVisit(VisitHome visitHome,Vector vsV)
    {
        Constant.println(UseCase.UCID_saveVisitHome);
        String objectid =   null;
        int result = 0;
        theConnectionInf.open();
        try{
            if(this.theHC.theHO.theVisit==null)
            {
                this.theUS.setStatus("กรุณานำผู้ป่วยเข้าสู่กระบวนการ", UpdateStatus.WARNING);
                return 0;
            }
//            if(!this.theHC.theHO.theVisit.service_location.equals("2"))
//            {
//                this.theUS.setStatus("กรุณาระบุประเภท Visit เป็นให้บริการนอกหน่วยบริการ", UpdateStatus.WARNING);
//                return 0;
//            }
            if(visitHome.getObjectId() == null)
                thePcuDB.theVisitHomeDB.insert(visitHome);
            else
                thePcuDB.theVisitHomeDB.update(visitHome);
            
            thePO.vVisitHome = thePcuDB.theVisitHomeDB.selectByFamilyID(visitHome.family_id);
            if(vsV == null)
                return result;

            for(int i=0,size=vsV.size();i<size;i++)
            {
                VitalsignHomeVisit vitalsingHomeVisit = (VitalsignHomeVisit)vsV.get(i);
                vitalsingHomeVisit.visitHome_id = visitHome.getObjectId();
                if(vitalsingHomeVisit.getObjectId() == null)
                    result = thePcuDB.theVitalsignHomeVisitDB.insert(vitalsingHomeVisit);
                else
                    result = thePcuDB.theVitalsignHomeVisitDB.update(vitalsingHomeVisit);
            }
            theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"),UpdateStatus.COMPLETE);
            if(visitHome != null)
                objectid = visitHome.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveVisitHome,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveVisitHome,objectid,null,UpdateStatus.COMPLETE);
            return result;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveVisitHome,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveVisitHome,objectid,ex,UpdateStatus.ERROR);
            return 0;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Vector listVitalsignHomeVisit(String visithome_id)
    {
        Vector result = null;
        theConnectionInf.open();
        try
        {
            result = thePcuDB.theVitalsignHomeVisitDB.selectByVisitHome(visithome_id);
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
    
    public Vector listVisitHomeByFamilyID()
    {
        return thePO.vVisitHome;
    }
    /**
     * @not deprecated henbe มันดึงมาตอนเลือกประชากรอยู่แล้วนะตอนบันทึกใหม่
     *
     */
    public Vector listVisitHomeByFamilyID(String FamilyID)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = thePcuDB.theVisitHomeDB.selectByFamilyID(FamilyID);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector listVisitHomeByPatientID(String PatientID)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = thePcuDB.theVisitHomeDB.selectByPatientID(PatientID);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public int delectVitalSignVisitHome(VitalsignHomeVisit vitalsignHomeVisit)
    {
        int result = 0;
        theConnectionInf.open();
        try
        {
            if(vitalsignHomeVisit != null && vitalsignHomeVisit.getObjectId().equals(""))
            {
                vitalsignHomeVisit.active = "0";
                thePcuDB.theVitalsignHomeVisitDB.update(vitalsignHomeVisit);
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
        return result;
    }
    public int deleteVitalSignVisitHome(Vector vVitalsignHomeVisit ,int select[])
    {
        int result = 0;
        theConnectionInf.open();
        try
        {
            for(int i=0; vVitalsignHomeVisit!=null && vVitalsignHomeVisit.size()>0
                    && select.length >0 && i< select.length;i++)
            {
                VitalsignHomeVisit vitalsign = (VitalsignHomeVisit)vVitalsignHomeVisit.get(i);
                if(vitalsign != null && !"".equals(vitalsign.getObjectId()))
                {
                    vitalsign.active = "0";
                    result += thePcuDB.theVitalsignHomeVisitDB.update(vitalsign);
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
        return result;
    }
    
    /**
     * บันทึกการให้คำปรึกษา
     * @param  theCounsel อ๊อปเจ็คConsel
     * @return int
     * @author kingland
     * @date 25/08/2549
     */
    public int saveCounsel(Counsel theCounsel)
    {
        Constant.println(UseCase.UCID_saveConsel);
        String objectid =   null;
        int result = 0;
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
        if(theCounsel.counsel_modify_time.length()<10)
        {
            theUS.setStatus("กรุณาระบุวันที่บันทึก",UpdateStatus.WARNING);
            return 0;
        }
        if(theLookupControl.isDateFuture(theCounsel.counsel_modify_time))
        {
            theUS.setStatus("กรุณาระบุวันที่บันทึกเป็นวันในอตีต",UpdateStatus.WARNING);
            return result;
        }
//        if(thePO.getVisit()!=null && !theCounsel.visit_id.equals("")){
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//            if(!thePO.getVisit().getObjectId().equals(theCounsel.visit_id)
//            && !theCounsel.visit_id.equals("")){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//        }
//        if(thePO.getVisit()==null && !theCounsel.visit_id.equals("")){
//            theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//            return 0;
//        }
        theConnectionInf.open();
        try{
            //kingland ตรวจสอบที่ว่ามีการให้คำปรึกษาซ้ำกันหรือไม่
            //requirement จาก พี่แอ้ม
            //ข้อมูลที่ไปสำรวจ
            if(theCounsel.getObjectId()==null)
            {
                if(!"".equals(theCounsel.survey_date))
                {
                    Vector v = thePcuDB.theCounselDB.selectByTypeAndSurverDate(
                            theCounsel.family_id,theCounsel.b_health_counsel_type_id
                            ,theCounsel.survey_date);
                    if(!v.isEmpty())
                    {
                        theUS.setStatus(GutilPCU.getTextBundle("CounselWarning"), UpdateStatus.WARNING);//ให้คำปรึกษาเรื่องนี้ไปแล้ว ไม่สามารถให้คำปรึกษาซ้ำได้
                        return 0;
                    }
                }
                if(!"".equals(theCounsel.visit_id))
                {
                    Vector v = thePcuDB.theCounselDB.selectByVisitIDAndCounselType(
                            theCounsel.visit_id,theCounsel.b_health_counsel_type_id);
                    if(!v.isEmpty())
                    {
                        theUS.setStatus(GutilPCU.getTextBundle("CounselWarning"), UpdateStatus.WARNING);//ให้คำปรึกษาเรื่องนี้ไปแล้ว ไม่สามารถให้คำปรึกษาซ้ำได้
                        return 0;
                    }
                }
                if(thePO.getVisit()!=null){
                    theCounsel.visit_id = thePO.getVisit().getObjectId();
                }
                theCounsel.counsel_active = "1";
                //ข้อมูลที่รับบริการ
                result = thePcuDB.theCounselDB.insert(theCounsel);
            }
            else
            {
                Counsel cs = thePcuDB.theCounselDB.selectByPK(theCounsel.getObjectId());
                if(!cs.b_health_counsel_type_id.equals(theCounsel.b_health_counsel_type_id))
                {
                    theUS.setStatus(GutilPCU.getTextBundle("ไม่สามารถเปลี่ยนแปลงประเภทการให้คำปรึกษาได้กรุณาเพิ่มใหม่"), UpdateStatus.WARNING);//ให้คำปรึกษาเรื่องนี้ไปแล้ว ไม่สามารถให้คำปรึกษาซ้ำได้
                    return 0;
                }
                result = thePcuDB.theCounselDB.update(theCounsel);
            }
            
            
            theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"), UpdateStatus.COMPLETE);//การบันทึกข้อมูลเสร็จสิ้น
            if(theCounsel != null)
                objectid = theCounsel.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveConsel,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveConsel,objectid,null,UpdateStatus.COMPLETE);
            return result;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveConsel,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveConsel,objectid,ex,UpdateStatus.ERROR);
            return result;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Vector listCounselByVisitID(String visitID)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = thePcuDB.theCounselDB.selectByVisitID(visitID);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    
    public Vector listCounselByFamilyID()
    {
        return thePO.vCounsel;
    }
    /**
     * @not deprecated henbe มันดึงมาตอนเลือกประชากรอยู่แล้วนะ
     * ใช้ใน menu
     */
    public Vector listCounselByFamilyID(String familyID,String type)
    {
        
        theConnectionInf.open();
        try
        {
            thePO.vCounsel = thePcuDB.theCounselDB.selectByFamilyID(familyID,type);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            theConnectionInf.close();
        }
        return thePO.vCounsel;
    }
    
    public boolean checkStatusVisit(String search)
    {
        theConnectionInf.open();
        boolean temp = false;
        try
        {
            theVisit = thePcuDB.theVisitDB.selectByPK(search);
            if(theVisit != null)
            {
                if(theVisit.visit_status.equals("2") || theVisit.visit_status.equals("3"))
                    temp = false;
                if(theVisit.visit_status.equals("1"))
                    temp = true;
            }
            else
                temp = true;
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
    
    public Vector listCounselByVisitIDAndCounselType(String visitID,String type)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = thePcuDB.theCounselDB.selectByVisitIDAndCounselType(visitID,type);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector listCounselByPatientIDAndCounselType(String patientID,String type)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = thePcuDB.theCounselDB.selectByPatinetIDAndCounselType(patientID,type);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    
    public int deleteCounsel(Counsel theCounsel)
    {
        Constant.println(UseCase.UCID_deleteConsel);
        String objectid =   null;
        //หากรายการที่เลือกเป็นของ visit เก่า
//        if(!theCounsel.visit_id.equals("")){
//            //ต้องตรวจสอบว่าเขาได้เลือก visit นั้นอยู่หรือเปล่า
//            if(thePO.getVisit()==null || !thePO.getVisit().getObjectId().equals(theCounsel.visit_id)){
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
        try{
            theCounsel.counsel_active = "0";
            theCounsel.counsel_staff_cancle = thePO.getEmployee().getObjectId();
            theCounsel.counsel_cancle_time = thePO.getCurrentDateTime();
            int result = thePcuDB.theCounselDB.update(theCounsel);
            thePO.vCounsel = thePcuDB.theCounselDB.selectByFamilyID(theCounsel.family_id);
            theUS.setStatus(GutilPCU.getTextBundle("การยกเลิกข้อมูลเสร็จสิ้น"), UpdateStatus.COMPLETE);//การบันทึกข้อมูลเสร็จสิ้น
            if(theCounsel != null)
                objectid = theCounsel.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteConsel,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteConsel,objectid,null,UpdateStatus.COMPLETE);
            return result;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteConsel,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteConsel,objectid,ex,UpdateStatus.ERROR);
            return 0;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    
    public int saveEfficiency(Efficiency theEfficiency)
    {
        Constant.println(UseCase.UCID_saveEfficiency);
        String objectid =   null;
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
        if(theEfficiency.efficiency_modify_time.length()<10)
        {
            theUS.setStatus("กรุณาระบุวันที่บันทึก",UpdateStatus.WARNING);
            return 0;
        }
        if(theLookupControl.isDateFuture(theEfficiency.efficiency_modify_time))
        {
            theUS.setStatus("กรุณาระบุวันที่รับวัคซีนเป็นวันในอตีต",UpdateStatus.WARNING);
            return 0;
        }
//        if(thePO.getVisit()!=null && !theEfficiency.visit_id.equals("")){
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//            if(!thePO.getVisit().getObjectId().equals(theEfficiency.visit_id)
//            && !theEfficiency.visit_id.equals("")){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//        }
//        if(thePO.getVisit()==null && !theEfficiency.visit_id.equals("")){
//            theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//            return 0;
//        }
        theConnectionInf.open();
        try
        {
            if(theEfficiency.getObjectId() == null)
            {   thePcuDB.theEfficiencyDB.insert(theEfficiency);
            }
            else
            {   thePcuDB.theEfficiencyDB.update(theEfficiency);
            }
            thePO.vEfficiency = thePcuDB.theEfficiencyDB.selectByFamilyID(theEfficiency.family_id);
            theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"),UpdateStatus.COMPLETE);
            if(theEfficiency != null)
                objectid = theEfficiency.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveEfficiency,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveEfficiency,objectid,null,UpdateStatus.COMPLETE);
            return 1;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveEfficiency,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveEfficiency,objectid,ex,UpdateStatus.ERROR);
            return 0;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Vector listEfficiencyByVisitID(String visitID)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = thePcuDB.theEfficiencyDB.selectByVisitID(visitID);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector listEfficiencyByFamilyID()
    {
        return thePO.vEfficiency;
    }
    /**
     * @deprecated henbe มันดึงมาตอนเลือกประชากรอยู่แล้วนะ
     *
     */
    public Vector listEfficiencyByFamilyID(String FamilyID)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = thePcuDB.theEfficiencyDB.selectByFamilyID(FamilyID);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public int deleteEfficiency(Efficiency theEfficiency)
    {
        Constant.println(UseCase.UCID_deleteEfficiency);
        String objectid =   null;
        //หากรายการที่เลือกเป็นของ visit เก่า
//        if(!theEfficiency.visit_id.equals("")){
//            //ต้องตรวจสอบว่าเขาได้เลือก visit นั้นอยู่หรือเปล่า
//            if(thePO.getVisit()==null || !thePO.getVisit().getObjectId().equals(theEfficiency.visit_id)){
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
        try{
            theEfficiency.efficiency_active = "0";
            theEfficiency.efficiency_staff_cancle = thePO.getEmployee().getObjectId();
            theEfficiency.efficiency_cancle_time = thePO.getCurrentDateTime();
            int ret = thePcuDB.theEfficiencyDB.update(theEfficiency);
            thePO.vEfficiency = thePcuDB.theEfficiencyDB.selectByFamilyID(theEfficiency.family_id);
            theUS.setStatus("การลบข้อมูลฟื้นฟูสมรรถภาพเสร็จสิ้น",UpdateStatus.COMPLETE);
            if(theEfficiency != null)
                objectid = theEfficiency.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteEfficiency,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteEfficiency,objectid,null,UpdateStatus.COMPLETE);
            return ret;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteEfficiency,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteEfficiency,objectid,ex,UpdateStatus.ERROR);
            theConnectionInf.rollback();
            return 0;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public int saveCheckHealthYear(CheckHealthYear theCheckHealthYear)
    {
        Constant.println(UseCase.UCID_saveCheckHealthYear);
        String objectid =   null;
        int result  = 0;
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
        if(theCheckHealthYear.check_health_year_modify_time.length()<10)
        {
            theUS.setStatus("กรุณาระบุวันที่บันทึก",UpdateStatus.WARNING);
            return 0;
        }
        if(theLookupControl.isDateFuture(theCheckHealthYear.check_health_year_modify_time))
        {
            theUS.setStatus("กรุณาระบุวันที่บันทึกเป็นวันในอตีต",UpdateStatus.WARNING);
            return 0;
        }
//        if(thePO.getVisit()!=null && !theCheckHealthYear.visit_id.equals("")){
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//            if(!thePO.getVisit().getObjectId().equals(theCheckHealthYear.visit_id)
//            && !theCheckHealthYear.visit_id.equals("")){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return 0;
//            }
//        }
//        if(thePO.getVisit()==null && !theCheckHealthYear.visit_id.equals("")){
//            theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//            return 0;
//        }
        theConnectionInf.open();
        try{
            if(theCheckHealthYear.getObjectId() == null)
            {
                if(!"".equals(theCheckHealthYear.survey_date))
                {
                    Vector v = thePcuDB.theCheckHealthYearDB.selectByTypeAndServeyDate(
                            theCheckHealthYear.family_id,
                            theCheckHealthYear.b_health_check_health_year_activity_id,
                            theCheckHealthYear.survey_date);
                    if(!v.isEmpty())
                    {
                        theUS.setStatus(GutilPCU.getTextBundle("CheckHealthYearWarning"), UpdateStatus.WARNING);//ทำการตรวจสุขภาพผู้ป่วยเรื่องนี้ไปแล้ว ไม่สามารถตรวจซ้ำได้
                        return 0;
                    }
                }
                if(!"".equals(theCheckHealthYear.visit_id))
                {
                    Vector v = thePcuDB.theCheckHealthYearDB.selectByVisitType(
                            theCheckHealthYear.visit_id
                            ,theCheckHealthYear.b_health_check_health_year_activity_id);
                    if(!v.isEmpty())
                    {
                        theUS.setStatus(GutilPCU.getTextBundle("CheckHealthYearWarning"), UpdateStatus.WARNING);//ทำการตรวจสุขภาพผู้ป่วยเรื่องนี้ไปแล้ว ไม่สามารถตรวจซ้ำได้
                        return 0;
                    }
                }
                result = thePcuDB.theCheckHealthYearDB.insert(theCheckHealthYear);
                
            }
            else
            {
                CheckHealthYear chy = thePcuDB.theCheckHealthYearDB.selectByPK(theCheckHealthYear.getObjectId());
                if(!chy.b_health_check_health_year_activity_id.equals(theCheckHealthYear.b_health_check_health_year_activity_id))
                {
                    theUS.setStatus("ไม่สามารถเปลี่ยนเรื่องที่ตรวจได้กรุณาเพิ่มใหม่", UpdateStatus.WARNING);//ทำการตรวจสุขภาพผู้ป่วยเรื่องนี้ไปแล้ว ไม่สามารถตรวจซ้ำได้
                    return 0;
                }
                result = thePcuDB.theCheckHealthYearDB.update(theCheckHealthYear);
            }
            
            thePO.vCheckHealthYear = thePcuDB.theCheckHealthYearDB.selectByFamilyID(theCheckHealthYear.family_id);
            
            theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"), UpdateStatus.COMPLETE);//การบันทึกข้อมูลเสร็จสิ้น
            if(theCheckHealthYear != null)
                objectid = theCheckHealthYear.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveCheckHealthYear,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveCheckHealthYear,objectid,null,UpdateStatus.COMPLETE);
            return result;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveCheckHealthYear,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveCheckHealthYear,objectid,ex,UpdateStatus.ERROR);
            return result;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public int deleteCheckHealthYear(CheckHealthYear theCheckHealthYear)
    {
        Constant.println(UseCase.UCID_deleteCheckHealthYear);
        String objectid =   null;
        //หากรายการที่เลือกเป็นของ visit เก่า
//        if(!theCheckHealthYear.visit_id.equals("")){
//            //ต้องตรวจสอบว่าเขาได้เลือก visit นั้นอยู่หรือเปล่า
//            if(thePO.getVisit()==null || !thePO.getVisit().getObjectId().equals(theCheckHealthYear.visit_id)){
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
        int result  = 0;
        theConnectionInf.open();
        try{
            theCheckHealthYear.check_health_year_active = "0";
            theCheckHealthYear.check_health_year_staff_cancle = thePO.getEmployee().getObjectId();
            theCheckHealthYear.check_health_year_cancle_time = thePO.getCurrentDateTime();
            
            result = thePcuDB.theCheckHealthYearDB.update(theCheckHealthYear);
            thePO.vCheckHealthYear = thePcuDB.theCheckHealthYearDB.selectByFamilyID(theCheckHealthYear.family_id);
            
            theUS.setStatus("การยกเลิกข้อมูลเสร็จสิ้น", UpdateStatus.COMPLETE);//การบันทึกข้อมูลเสร็จสิ้น
            if(theCheckHealthYear != null)
                objectid = theCheckHealthYear.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteCheckHealthYear,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteCheckHealthYear,objectid,null,UpdateStatus.COMPLETE);
            return result;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteCheckHealthYear,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteCheckHealthYear,objectid,ex,UpdateStatus.ERROR);
            return result;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public Vector listCheckHealthYearByVisitID(String visitID)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = thePcuDB.theCheckHealthYearDB.selectByVisitID(visitID);
        }
        catch(Exception ex)

        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    public Vector listCheckHealthYearByFamilyID()
    {
        return thePO.vCheckHealthYear;
    }
    /**
     * @deprecated henbe มันดึงมาตอนเลือกประชากรอยู่แล้วนะ
     *
     */
    public Vector listCheckHealthYearByFamilyID(String familyID)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = thePcuDB.theCheckHealthYearDB.selectByFamilyID(familyID);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    /*
    public void deleteCheckHealthYear(CheckHealthYear theCheckHealthYear)
    {
        theConnectionInf.open();
        try
        {
            thePcuDB.theCheckHealthYearDB.delete(theCheckHealthYear);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
    }*/
    
    public boolean saveDental(Dental theDental)
    {
        Constant.println(UseCase.UCID_saveDental);
        String objectid =   null;
        if(this.theHC.theHO.theVisit==null)
        {
            this.theUS.setStatus("กรุณานำผู้ป่วยเข้าสู่กระบวนการ", UpdateStatus.WARNING);
            return false;
        }
//        if(!this.theHC.theHO.theVisit.service_location.equals("2"))
//        {
//            this.theUS.setStatus("กรุณาระบุประเภท Visit เป็นให้บริการนอกหน่วยบริการ", UpdateStatus.WARNING);
//            return false;
//        }
        if(theDental.dental_modify_time.length()<10)
        {
            theUS.setStatus("กรุณาระบุวันที่บันทึก",UpdateStatus.WARNING);
            return false;
        }
        if(theLookupControl.isDateFuture(theDental.dental_modify_time))
        {
            theUS.setStatus("กรุณาระบุวันที่บันทึกเป็นวันในอตีต",UpdateStatus.WARNING);
            return false;
        }
//        if(thePO.getVisit()!=null && !theDental.visit_id.equals("")){
//            if(!thePO.getVisit().visit_status.equals(VisitStatus.isInProcess())){
//                theUS.setStatus("ข้อมูลนี้เป็นของการรับบริการที่จบกระบวนการแล้วไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return false;
//            }
//            if(!thePO.getVisit().getObjectId().equals(theDental.visit_id)
//            && !theDental.visit_id.equals("")){
//                theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//                return false;
//            }
//        }
//        if(thePO.getVisit()==null && !theDental.visit_id.equals("")){
//            theUS.setStatus("ข้อมูลนี้เป็นข้อมูลของการรับบริการครั้งก่อนไม่สามารถแก้ไขได้",UpdateStatus.WARNING);
//            return false;
//        }
        theConnectionInf.open();
        try
        {   if(theDental.getObjectId() == null)
            {   thePcuDB.theDentalDB.insert(theDental);
            }
            else
            {   thePcuDB.theDentalDB.update(theDental);
            }
            thePO.vDental = thePcuDB.theDentalDB.selectByFamilyID(theDental.family_id);
            theUS.setStatus(GutilPCU.getTextBundle("SaveComplete"),UpdateStatus.COMPLETE);
            if(theDental != null)
                objectid = theDental.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_saveDental,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveDental,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_saveDental,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_saveDental,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {   theConnectionInf.close();
        }
    }
    
    public Vector listDentalByVisitID(String visitID)
    {
        vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = thePcuDB.theDentalDB.selectByVisitID(visitID);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return vc;
    }
    
    public Vector listDentalByFamilyID()
    {
        return thePO.vDental;
    }
    /**
     * @not deprecated henbe มันดึงมาตอนเลือกประชากรอยู่แล้วนะ
     *
     */
    public Vector listDentalByFamilyID(String FamilyID)
    {
        theConnectionInf.open();
        try
        {
            thePO.vDental = thePcuDB.theDentalDB.selectByFamilyID(FamilyID);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theConnectionInf.rollback();
        }
        finally
        {
            theConnectionInf.close();
        }
        return thePO.vDental;
    }
    
    
    public int deleteDental(Dental theDental)
    {
        Constant.println(UseCase.UCID_deleteDental);
        String objectid =   null;
        //หากรายการที่เลือกเป็นของ visit เก่า
//        if(!theDental.visit_id.equals("")){
//            //ต้องตรวจสอบว่าเขาได้เลือก visit นั้นอยู่หรือเปล่า
//            if(thePO.getVisit()==null || !thePO.getVisit().getObjectId().equals(theDental.visit_id)){
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
        try{
            theDental.dental_active = "0";
            theDental.dental_staff_cancle = thePO.getEmployee().getObjectId();
            theDental.dental_cancle_time = thePO.getCurrentDateTime();
            int result = thePcuDB.theDentalDB.update(theDental);
            thePO.vDental = thePcuDB.theDentalDB.selectByFamilyID(theDental.family_id);
        
            theUS.setStatus(GutilPCU.getTextBundle("การยกเลิกข้อมูลเสร็จสิ้น"), UpdateStatus.COMPLETE);//การบันทึกข้อมูลเสร็จสิ้น
            if(theDental != null)
                objectid = theDental.getObjectId();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteDental,UpdateStatus.COMPLETE,null);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteDental,objectid,null,UpdateStatus.COMPLETE);
            return result;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            theHC.theSystemControl.setStatus(UseCase.TH_deleteDental,UpdateStatus.ERROR,ex);
            theHC.theSystemControl.saveLog(UseCase.UCID_deleteDental,objectid,ex,UpdateStatus.ERROR);
            return 0;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
/**
 * @deprecated ยังใช้งานไม่ได้เพราะว่าการเยี่ยมบ้านไม่ได้เก็บ visit_id เลยทำให้ไม่มีเลขการรับบริการ
 * @param select_visit
 * @return
 */
    public Vector listVisitHome(boolean select_visit)
    {
        theConnectionInf.open();
        try{
            String visit_id = null;
            if(select_visit)
                visit_id = thePO.getVisit().getObjectId();
            return thePcuDB.theVisitHomeDB.selectByPVid(thePO.getPatient().getObjectId(),visit_id);
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
}
