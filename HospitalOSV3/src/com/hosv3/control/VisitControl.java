    /*
     *เคลียร์ค่าใน transfer เก่าให้เป็นเสร็จสิ้นการบริการเฉพาะในรายการสุดท้ายเท่านั้น
     *รองรับได้ทั้ง transfer ที่มีตัวใหม่เพิ่มมาแล้ว สถานะจะเป็น 1 ไม่ต้องทำอะไร
     *ถ้ายังไม่มี transfer ใหม่เข้ามาสถานะจะเป็น 2 ต้องเปลี่ยนเป็น 3
     */
/*
 * VisitControl.java
 *
 * Created on 17 ตุลาคม 2546, 22:59 น.
 */
package com.hosv3.control;

import com.hosv3.object.*;
import com.hosv3.subject.*;
import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;
//import com.hosv3.utility.DateUtil.*;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.object.*;
import com.hospital_os.utility.Gutil;
import com.hosv3.utility.Constant;
import com.hospital_os.object.specialQuery.*; 
import com.pcu.object.Family;
import java.sql.ResultSet;

import java.util.*;
import com.hospital_os.object.AppointmentStatus;
import com.hosv3.gui.dialog.HosDialog;
import java.net.URL;
import javax.swing.JOptionPane;
import th.go.nhso.rightsearch.RightData;

/**
 *f
 * @author  tong
 */
public class VisitControl {
    ConnectionInf theConnectionInf;
    HosDB theHosDB;
    HosObject theHO;
    HosSubject theHS;
    UpdateStatus theUS;
    PatientControl thePatientControl;
    OrderControl theOrderControl;
    LookupControl theLookupControl;
    SystemControl theSystemControl;
    /**ใช้ในการคำหาข้อมูลของผู้ใช้งาน*/
    Vector vEmployee = null;
    Vector vObject = null;
    /**
     * Creates a new instance of LookupControl
     * @param con 
     * @param ho 
     * @param hdb 
     * @param hs 
     */
    public VisitControl(ConnectionInf con,HosObject ho,HosDB hdb,HosSubject hs){
        theConnectionInf = con;
        theHosDB = hdb;
        theHS = hs;
        theHO = ho;
    }
    
    /**
     * 
     * @param us 
     */
    public void setUpdateStatus(UpdateStatus us){
        theUS = us;
    }
    /**
     * 
     * @param lc 
     * @param oc 
     * @param pc 
     */
    public void setDepControl(LookupControl lc,OrderControl oc,PatientControl pc,SystemControl sc){
        theOrderControl = oc;
        theLookupControl = lc;
        thePatientControl = pc;
        theSystemControl = sc;
    }
    /*
     * gui_used
     * แก้ไขข้อมูลในตาราง visit  โดยการใส่ค่าการ admit ลงไป
     * reverseAdmit ถ้ามีการใช้เลข AN ที่ยกเลิก ก็ให้ค่าไป ถ้าไม่มี ให้เป็น null
     * การแก้ไขข้อมูลในตาราง visit
     * ตรวจสอบว่าเคย Admit หรือยัง แก้ไข การ Admit
     * Admit ใหม่
     * ตรวจสอบเมื่อ เลข an มีอยู่แล้วไม่ต้อง gen ใหม่ เพราะอาจจะได้มาจากการใช้เลข an ที่มีอยู่แล้ว
     * เพิ่งพบว่าการ comment ของแอ้มเป็นบักตอนยกเลิก AN henbe 01/03/2550
     */
    /**
     * 
     * @param admit 
     * @param theReverseAdmit 
     * @return 
     */
    public boolean admitVisit(Visit admit,ReverseAdmit theReverseAdmit)//amp:5/8/2549
    {
        Constant.println("public void admitVisit(Visit admit,Vector vReverseAdmit)");
        Constant.println(UseCase.UCID_saveAdmitVisit);
        String objectid =   null;
        theConnectionInf.open();
        try{
            Vector v_bed = null;
            if(admit.is_discharge_doctor.equals(Active.isEnable())){
                theUS.setStatus(("ผู้ป่วยจำหน่ายทางการแพทย์แล้วไม่สามารถแก้ไขข้อมูลได้"), theUS.WARNING);
                return false;
            }
            if(DateUtil.countDateDiff(admit.begin_admit_time,admit.begin_visit_time)<0){
                theUS.setStatus(("กรุณาระบุวัน Admit ในวันเดียวกันหรือหลังจากวันเข้ารับบริการ"),UpdateStatus.WARNING);
                return false;
            }
            if(!admit.doctor_discharge_time.equals("") && admit.doctor_discharge_time!=null
            && DateUtil.countDateDiff(admit.begin_admit_time,admit.doctor_discharge_time)>0){
                theUS.setStatus(("กรุณาระบุวัน Admit ก่อนวันจำหน่ายทางการแพทย์"),UpdateStatus.WARNING);
                return false;
            }
            if(theLookupControl.readOption().admit.equals(Active.isEnable())) {
                boolean retb = DialogPasswd.showDialog(theHO,theUS,theHO.theEmployee.password);
                if(!retb) {
                    theUS.setStatus(("รหัสผ่านไม่ถูกต้อง"),UpdateStatus.WARNING);
                    return false;
                }
            }
            if(admit.bed.length()!=0)
                v_bed = theHosDB.theVisitDB.selectByBed(admit.bed,admit.ward);
            //ค้นว่าเตียงนี้มีคนไข้กี่คน ถ้ามี 011,023
            if(v_bed!=null && !v_bed.isEmpty()){
                Visit vadmit = (Visit)v_bed.get(0);
                if(!vadmit.getObjectId().equals(admit.getObjectId())){
                    //ถ้ารายการ visit ที่แก้ไขอยู่ในช่วงของการ admit ที่มีอยู่แล้วจะไม่ให้ แก้ไข
                    //เวลาเข้า มากกว่า เวลาเข้าของ visit ที่มีอยู่ และเวลาเข้าน้อยกว่า เวลาของออก visit ที่มีอยู่
                    if(!theUS.confirmBox(Constant.getTextBundle("ยืนยันการใช้เลขเตียงนี้ เนื่องจากเตียงดังกล่าวมีคนไข้อยู่แล้ว")
                            ,UpdateStatus.WARNING))
                        return false;
                }
                //ถ้าเป็น 011 เป็นอันดับแรก และที่เข้ามาก็เป็น 011
                if(v_bed.size()>1){
                    theUS.setStatus(("เลขเตียงนี้มีคนไข้มากกว่า 1 คน กรุณาแก้ไขให้ถูกต้อง")
                            ,UpdateStatus.WARNING);
                    return false;
                }
            }
            String date_time = theLookupControl.intReadDateTime();
            //ถ้าเพิ่ง ADMIT
            if(admit.visit_type.equals(VisitType.OPD)) 
            {                
                String an_number = "";
                if(theReverseAdmit == null){
                    an_number = theHosDB.theSequenceDataDB.updateSequence("an",true); 
                }
                else{
                    Visit v_indb = theHosDB.theVisitDB.selectByVn(theReverseAdmit.an);
                    if(v_indb!=null){
                        theUS.setStatus(("ไม่สามารถใช้เลข AN ที่ยกเลิกได้เนื่องจากพบเลขดังกล่าวในฐานข้อมูลแล้ว")
                                ,UpdateStatus.WARNING);
                        return false;
                    }
                    an_number = theReverseAdmit.an;
                    theReverseAdmit.used = "1";
                    theHosDB.theReverseAdmitDB.updateUsedAdmitNumber(theReverseAdmit);
                }
                admit.an = admit.vn;
                admit.vn = an_number;
                admit.visit_type = "1";
                admit.begin_admit_time = date_time;
                theHO.is_admit = true;
                
                //ไม่ต้องลบคิวผู้ป่วยดีมั้ย
                if(theHO.theListTransfer!=null)
                    theHosDB.theQueueTransferDB.delete(theHO.theListTransfer);
                theHO.theListTransfer = null;
                
                theHosDB.theTransferDB.updateFinishTimeVisit(admit.getObjectId(),date_time);
            }
            intSaveTransferThrow(theHO.theEmployee,date_time);
            intSaveTransaction(theHO.theServicePoint,date_time,admit.ward);
            theHosDB.theVisitDB.update(admit);
            theHS.theVisitSubject.notifyAdmitVisit(Constant.getTextBundle("การนำผู้ป่วยเข้าสู่สถานะผู้ป่วยในเสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(admit != null)
                objectid = admit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveAdmitVisit,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveAdmitVisit,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveAdmitVisit,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveAdmitVisit,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }
    }

    /**
     * เปิด visit เฉพาะผู้ป่วยที่ถูกนัด
     * ข้อมูลเข้า: Vector ของ appointment
     * ข้อมูลออก:ผู้ป่วยที่ถูกนัดจะได้หมายเลข VN
     * @param theUS 
     * @param row 
     * @param vappointment 
     */
    public void visitFromVAppointment(UpdateStatus theUS,int[] row,Vector vappointment)
    {
///////////////////////////////////////////////////////////////////////////
        Constant.println(UseCase.UCID_visitFromVAppointment);
        String objectid =   null;
        boolean no_app_wait = true;
        String in_process = "";
        if(vappointment == null || vappointment.size() == 0){
            theUS.setStatus(("กรุณาเลือกรายการนัดหมายที่ต้องการเปิด visit"),UpdateStatus.WARNING);
            return;
        }
        ////////////////////////////////////////////////////////////////////
        int error = 0;//check ว่าผู้ป่วยเคย visit หรือเปล่า
        int visit = 0;//check ว่าผู้ป่วยอยู่ในกระบวนการหรือเปล่า
        int day = 0;//check ว่าวันที่นัดตรงกับวันที่จะ visit หรือเปล่า
        boolean is_visit = false;
        boolean is_indate = true;
        int count_visit = 0;
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            for(int i=0;i<row.length;i++)
            {
                SpecialQueryAppointment spappointment = (SpecialQueryAppointment)vappointment.get(row[i]);
                Appointment app = theHosDB.theAppointmentDB.select2ByPK(spappointment.t_patient_appointment_id);
                Patient thePatient = theHosDB.thePatientDB.selectByPK(app.patient_id);

                //ตรวจสอบว่า เคย Visit หรือเปล่า อยู่ในกระบวนการหรือยัง เพื่อจะได้แสดง hn เตือนผู้ใช้
                Visit theVisit = theHosDB.theVisitDB.selectVisitByPatientIDLast(thePatient.getObjectId());
                if(theVisit!=null && theVisit.visit_status.equals(VisitStatus.isInProcess())){
                    in_process = in_process + ", " + theVisit.hn;
                    visit = visit + 1;
                    continue;
                }
                //ถ้าการนัดนั้นผู้ป่วยยังไม่มี visit และยังเป็นสถานะรอการนัด
                //////////////////////////////////////////////
                Visit vst = theHO.initVisit();
                vst.patient_id = thePatient.getObjectId();
                vst.hn = thePatient.hn;
                //////////////////////////////////////////////
                Vector vPatientPayment = theHosDB.thePatientPaymentDB.selectByPatientId(thePatient.getObjectId());
                if(vPatientPayment.isEmpty()){
                    Plan plan = theHosDB.thePlanDB.selectByPK(Plan.SELF_PAY);
                    Payment p = theHO.initPayment(plan);
                    vPatientPayment.add(p);
                }
                else{
                    for(int j=0;j<vPatientPayment.size();j++){
                        Payment pm = (Payment)vPatientPayment.get(j);
                        pm.visit_id = "";
                        pm.priority = String.valueOf(j);
                    }
                }
                //////////////////////////////////////////////
                //amp:25/02/2549
                QueueVisit qv = theHosDB.theQueueVisitDB.selectByPK(app.queue_visit_id);
                //visitPatientAppointment(vst,thePatient,vPatientPayment,theHO.theServicePoint,qv);
                ServicePoint sp = theLookupControl.readServicePointById(app.clinic_code);
//                vst.locking = "0";
//                vst.lock_user = "";
//                vst.lock_time = "";
                try{
                    intVisitPatient(thePatient,vst,vPatientPayment,sp,qv,date_time);
                    theOrderControl.intCheckAppointmentOrder(thePatient,vst,date_time,app);
                }catch(Exception e){
                    e.printStackTrace(Constant.getPrintStream());
                    continue;
                }
                vst.locking = "0";
                vst.lock_user = "";
                vst.lock_time = "";
                theHosDB.theVisitDB.updateLocking(vst);
                theHosDB.theQueueTransferDB.updateLockByVid(vst.getObjectId());
                ///////////////////////////////////////////////////////////
                app.status = AppointmentStatus.COMPLETE;
                app.vn = vst.vn;
                app.visit_id = vst.getObjectId();
                theHosDB.theAppointmentDB.update(app);
                ///////////////////////////////////////////////////////////
                count_visit++;
                if(app != null)
                    objectid = app.getObjectId();
                else
                    objectid = null;
                theSystemControl.saveLog(UseCase.UCID_visitFromVAppointment,objectid,null,UpdateStatus.COMPLETE);
            }
            theHO.clearFamily();
            if(count_visit==0){
                if(visit != 0 ){
                    theUS.setStatus(Constant.getTextBundle("ไม่สามารถ visit หมายเลข HN")+" " +  in_process
                    + " " + Constant.getTextBundle("ได้")+" "+
                    Constant.getTextBundle("เนื่องจากผู้ป่วยอยู่ในกระบวนการแล้ว"),UpdateStatus.WARNING);
                    return;
                }
                else{
                    theUS.setStatus(Constant.getTextBundle("ไม่มีรายการนัดเพื่อนำเข้ากระบวนการกรุณาตรวจสอบสถานะการนัด"),UpdateStatus.WARNING);
                    return;
                }    
            }
            theUS.setStatus(("การนำผู้ป่วยจากการนัดเข้าสู่กระบวนการเสร็จสิ้น"),UpdateStatus.COMPLETE);
            theHS.thePatientSubject.notifyResetPatient(Constant.getTextBundle("การนำผู้ป่วยจากการนัดเข้าสู่กระบวนการเสร็จสิ้น"),UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_visitFromVAppointment,UpdateStatus.COMPLETE,null);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_visitFromVAppointment,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_visitFromVAppointment,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
     /**
     *
     *
     * @see
     * @Author เบะ
     * @date 01/07/2549
     * @param remain
     * @return
     */
    public int deleteQueueLab(boolean remain)
    {
        theConnectionInf.open();
        try{
            String remain_str = remain?"1":"0";
            theHosDB.theQueueLabDB.deleteByVisitID(theHO.theVisit.getObjectId(),remain_str);
            theHS.theVisitSubject.notifyUnlockVisit(Constant.getTextBundle("การลบคิวแลบเสร็จสิ้น"),UpdateStatus.COMPLETE);
            return 1;
        }
        catch(Exception ex){
            theUS.setStatus(("การลบคิวแลบผิดพลาด"),UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
            return 0;
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     * 
     * 
     * @see 
     * @Author เบะ
     * @date 01/07/2549
     * @param remain 
     * @return 
     */
    public int deleteQueueTransfer(Vector vQueue,int[] select)
    {
        boolean ret = theUS.confirmBox("ยืนยันการลบผู้ป่วยออกจากคิว",UpdateStatus.WARNING);
        if(!ret)
            return 2;

        theConnectionInf.open();
        try{
            for(int i=0;i<select.length;i++){
                ListTransfer qt = (ListTransfer)vQueue.get(i);
                theHosDB.theQueueTransferDB.deleteByVisitID(qt.visit_id);
            }
            theHS.theVisitSubject.notifyUnlockVisit(Constant.getTextBundle("การลบคิวแลบเสร็จสิ้น"),UpdateStatus.COMPLETE);
            return 1;
        }
        catch(Exception ex){    
            theUS.setStatus(("การลบคิวผิดพลาด"),UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());            
            return 0;
        }
        finally{
            theConnectionInf.close();
        }    
    }    
    /**
     * 
     * 
     * @see บันทึกหรือแก้ไข NCD รวมถึง Update Visit เกี่ยวกับ NCD
     * @Author amp
     * @date 19/06/2549
     * @duplicate : ยกเลิกได้เลยไม่ได้ใช้แล้ว sumo 28/08/2549
     * @param theNCD 
     * @return 
     */
    public int savePatientNCD(NCD theNCD)
    {      
        int result = 0;
        theConnectionInf.open();
        try{
            result = intSaveNCD(theNCD, true, true,false);
            theHS.thePatientSubject.notifySavePatient(Constant.getTextBundle("การบันทึกข้อมูล NCD ของผู้ป่วยเสร็จสิ้น"),UpdateStatus.COMPLETE);
            return 1;
        }
        catch(Exception ex){    
            theUS.setStatus(("การบันทึกข้อมูล NCD ของผู้ป่วยผิดพลาด"),UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());            
            return 0;
        }
        finally{
            theConnectionInf.close();
        }    
    }       
    
    /**
     * 
     * 
     * @see บันทึกหรือแก้ไข NCD รวมถึง Update Visit เกี่ยวกับ NCD
     * @Author amp
     * @date 19/06/2549
     * @param theNCD 
     * @param is_ncd 
     * @return 
     */
    public int saveVisitNCD(NCD theNCD,boolean is_ncd)
    {
        Constant.println(UseCase.UCID_saveVisitNCD);
        String objectid =   null;
        int result = 0;
        theConnectionInf.open();
        try{
            result = intSaveNCD(theNCD, is_ncd, true,true);
            //amp:16/08/2549:เพื่อแสดงข้อความตือนได้ถูกต้อง
            if(result == 1)
            {
                theHS.theVisitSubject.notifyVisitPatient(Constant.getTextBundle("การบันทึกข้อมูล NCD ในการรับบริการเสร็จสิ้น")
                        ,UpdateStatus.COMPLETE);
                if(theNCD != null)
                    objectid = theNCD.getObjectId();
                theSystemControl.setStatus(UseCase.TH_saveVisitNCD,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_saveVisitNCD,objectid,null,UpdateStatus.COMPLETE);
                return 1;
            }
            else
            {
                return 0;
            }
        }
        catch(Exception ex)
        {    
            theSystemControl.setStatus(UseCase.TH_saveVisitNCD,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveVisitNCD,objectid,ex,UpdateStatus.ERROR);
            return 0;
        }
        finally
        {
            theConnectionInf.close();
        }    
    }
    
    /**
     * 
     * @duplicate : ยกเลิกได้เลยไม่ได้ใช้แล้ว sumo 28/08/2549
     * @param theNCD 
     * @return 
     * @deprecated ทดสอบยกเลิก
     */
    public int deletePatientNCD(NCD theNCD)
    {
        int result = 0;
        theConnectionInf.open();
        try{
            theHosDB.theNCDDB.delete(theNCD);
            theHO.vNCD = theHosDB.theNCDDB.selectByPatientId(theHO.thePatient.getObjectId());
            theHS.theVisitSubject.notifyVisitPatient(Constant.getTextBundle("การลบข้อมูล NCD ของผู้ป่วยเสร็จสิ้น")
                    ,UpdateStatus.COMPLETE);
            return 1;
        }
        catch(Exception ex){    
            theUS.setStatus(("การลบข้อมูล NCD ของผู้ป่วยผิดพลาด")
                    ,UpdateStatus.COMPLETE);
            ex.printStackTrace(Constant.getPrintStream());            
            return 0;
        }
        finally{
            theConnectionInf.close();
        }    
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * 
     * @param theNCD 
     * @param is_ncd 
     * @param set_patient 
     * @param set_visit 
     * @throws java.lang.Exception 
     * @return 
     */
    public int intSaveNCD(NCD theNCD,boolean is_ncd,boolean set_patient,boolean set_visit)
    throws Exception
    {      
            Constant.println("intSaveNCD");
            if(theNCD==null){
                theUS.setStatus(("ไม่พบข้อมูล NCD ที่จะทำการบันทึก"),UpdateStatus.WARNING);
                return 0;
            }
            if(!is_ncd){
                if(theHO.theVisit!=null)
                {
                    theHO.theVisit.ncd = "0";
                    theHO.theVisit.ncd_group = "";
                    theHosDB.theVisitDB.updateNCD(theHO.theVisit); 
                }
            }
            else
            {
                //ในกรณีโปรแกรม Gen ให้
                boolean get_sequence = false;
                if(theHO.thePatient!=null)
                {
                    if(theNCD.ncd_number.equals("") && theNCD.getObjectId() != null)
                    {
                        theUS.setStatus(("กรุณาระบุหมายเลข NCD"),UpdateStatus.WARNING);
                        return 0;
                    }
                    if(theNCD.ncd_number.equals("")){
                        theNCD.ncd_number = theHosDB.theNCDGroupDB.updateSequence(theNCD.ncd_group_id);
                        get_sequence = true;
                    }
                    
                    NCD ncd = theHosDB.theNCDDB.selectByNCDNumber(theNCD.ncd_number);
                    if(ncd!=null && !ncd.patient_id.equals(theHO.thePatient.getObjectId()))
                    {
        //                if(get_sequence){
        //                    theHosDB.theNCDGroupDB.updateSequenceBack(theNCD.ncd_group_id);
        //                }
                        theUS.setStatus(Constant.getTextBundle("มีหมายเลขนี้แล้ว") +
                                " " +
                                Constant.getTextBundle("กรุณาตรวจสอบหมายเลขอีกครั้ง"),UpdateStatus.WARNING);
                        return 0;
                    }
                    
                    if(theHO.vNCD==null)
                        theHO.vNCD = new Vector();
                    
                    if(theNCD.getObjectId()==null)
                    {
                        theNCD.staff_record = theHO.theEmployee.getObjectId();
                        theNCD.record_date_time = theHO.date_time;
                        theHosDB.theNCDDB.insert(theNCD);
                        theHO.vNCD.add(theNCD);
                    }
                    else
                    {
                        theNCD.staff_modify = theHO.theEmployee.getObjectId();
                        theNCD.modify_date_time = theHO.date_time;
                        theHosDB.theNCDDB.update(theNCD);
                    }   
                }
                if(theHO.theVisit!=null)
                {
                    theHO.theVisit.ncd = "1";
                    theHO.theVisit.ncd_group = theNCD.ncd_group_id;
                    theHosDB.theVisitDB.updateNCD(theHO.theVisit); 
                }
            }
            theHO.vNCD = theHosDB.theNCDDB.selectByPatientId(theHO.thePatient.getObjectId());
            return 1;
    }
    
     /**
     * pongtorn Henbe
     * นำผู้ป่วยเข้าสู่กระบวนการเป็นฟังชันภายใน
     * รับตัวแปรที่จำเป็นทั้งหมดจากฟังชันหลักintReadVisitRet
     * ทำงานได้จาก visitcontrol ลงมา
     * @param patient 
     * @param visit 
     * @param payment 
     * @param sp 
     * @param qv 
     * @param date_time 
     * @throws java.lang.Exception 
     */
    public void intCheckVisitPatient(Patient patient,Visit visit,Vector payment
    ,ServicePoint sp,QueueVisit qv,String date_time) throws Exception
    {
        if(patient == null) {   
            throw new Exception("UC:กรุณาเลือกผู้ป่วย");
        }
        if(payment == null || payment.size()==0)  {
             throw new Exception("UC:ยังไม่มีสิทธิ์การรักษา");
        }
        Payment pm = (Payment)payment.get(0);
//        if(!pm.card_exp_date.equals("")) {
//            int diff = DateUtil.countDateDiff(pm.card_exp_date,theHO.date_time);
//            if(diff<0) {
//                throw new Exception("UC:สิทธิหมดอายุกรุณาต่ออายุของสิทธิก่อนรับบริการ");
//            }
//        }
        Plan plan_active = theHosDB.thePlanDB.selectByPK(pm.plan_kid);
        if(!plan_active.isActive()){
            throw new Exception("UC:สิทธิถูกยกเลิกกรุณาเลือกสิทธิใหม่ก่อนรับบริการ");
        }
        if(patient.discharge_status_id.equals(Dischar.DEATH))  {
            throw new Exception("UC:ผู้ป่วยเสียชีวิตแล้วไม่สามารถนำผู้ป่วยเข้าสู่กระบวนการได้");
        }
        if(patient.patient_birthday.equals(""))  {
            throw new Exception("UC:ผู้ป่วยไม่มีวันเกิดไม่สามารถนำผู้ป่วยเข้าสู่กระบวนการได้");
        }
        if(patient.active.equals(Active.isDisable()))  {
            throw new Exception("UC:ข้อมูลผู้ป่วยถูกยกเลิกแล้วไม่สามารถนำผู้ป่วยเข้าสู่กระบวนการได้");
        }
        if(thePatientControl.intReadVisitRet(patient.getObjectId())!=null){
            throw new Exception("UC:ข้อมูลผู้ป่วยอยู่ในกระบวนการแล้วไม่สามารถนำผู้ป่วยเข้าสู่กระบวนการอีกได้");
        }
        
    //VISIT /////////////////////////////////////////////////////////////////
        if(patient.hn.length()==0)  {
            throw new Exception("UC:พบเลข HN ของผู้ป่วยเป็นค่าว่างกรุณาแก้ไขให้ถูกต้อง");
        }
        int count_hn = theHosDB.thePatientDB.selectCountHN(patient.hn);
        if(count_hn > 1)  {
            throw new Exception("UC:พบเลข HN ของผู้ป่วยซ้ำกรุณายกเลิกข้อมูลผู้ป่วยให้เหลือเพียงคนเดียวก่อน");
        }
        if(!patient.pid.equals("")){
            int count_pid = theHosDB.thePatientDB.selectCountPID(patient.pid);
            if(count_pid > 1 && !patient.pid.equals(""))  {
                throw new Exception("UC:พบเลขบัตรประชาชนของผู้ป่วยซ้ำกรุณายกเลิกข้อมูลผู้ป่วยให้เหลือเพียงคนเดียวก่อน");
            }
        }
//        Patient pt_src = theHosDB.thePatientDB.selectByPK(patient.getObjectId());
//        Family pt_des = patient.getFamily();
//        if(pt_src!=null && pt_des!=null){
//            if(!pt_src.pid.equals(pt_des.pid)
//            || !pt_src.fname.equals(pt_des.patient_name)
//            || !pt_src.lname.equals(pt_des.patient_last_name)){
//                boolean confirm = theUS.confirmBox("ข้อมูลผู้ป่วยไม่ตรงกับ" +
//                        "\n ข้อมูลประชากร " + pt_des.patient_name + " " + pt_des.patient_last_name
//                        + " เลขบัตร " + pt_des.pid +" วันเกิด " + pt_des.patient_birthday +
//                        "\n ข้อมูลผู้ป่วย  " + pt_src.fname + " " + pt_src.lname
//                        + " เลขบัตร " + pt_src.pid+" วันเกิด " + pt_src.birthday
//                        , UpdateStatus.WARNING);
////                if(!confirm)
////                    return -1;
//                throw new Exception("UC:พบข้อมูลประชากรและผู้ป่วยไม่สอดคล้องกันกรุณาบันทึกข้อมูลผู้ป่วยอีกครั้ง");
//            }
//        }
        if(visit.begin_visit_time.startsWith("20"))  {
            throw new Exception("UC:วันที่ในการเข้ารับบริการ มีค่าของปีไม่ถูกต้อง "+ visit.begin_visit_time +" กรุณาตรวจสอบระบบปฏิบัติการของเครื่อง");
        }
    //VISIT /////////////////////////////////////////////////////////////////
        visit.vn = theHosDB.theSequenceDataDB.updateSequence("vn",true);
        Visit v_indb = theHosDB.theVisitDB.selectByVn(visit.vn);
        if(v_indb!=null)  {
            throw new Exception("UC:พบเลข VN ดังกล่าวในฐานข้อมูลกรุณาตรวจสอบเลข VN ล่าสุด");
        }
    }
    /**
     *
     * UD = undependency with HO
     * @param patient
     * @param visit
     * @param payment
     * @param sp
     * @param qv
     * @param date_time
     * @throws Exception
     */
    public ListTransfer intUDVisitPatient(Patient patient,Visit visit,Vector payment
    ,ServicePoint sp,QueueVisit qv,String date_time) throws Exception
    {
        try{
            intCheckVisitPatient(patient,visit,payment,sp,qv,date_time);
        }catch(Exception e){
            throw e;
        }
        String db_year = theHosDB.theVisitYearDB.selectCurrenctYear();
        if(!db_year.equals(theHO.date_time.substring(2,4)))  {
            boolean ret = theUS.confirmBox(
                    Constant.getTextBundle("พบเลขปีของ VN ไม่เป็นปัจจุบันควรตรวจสอบเลขลำดับ") +
                    " " +
                    Constant.getTextBundle("ยืนยันการใช้งานต่อ")
                    ,UpdateStatus.WARNING);
            if(!ret)
                return null;
        }
        theHO.initVisit(visit,patient);
        Vector v_inyear = theHosDB.theVisitDB.selectByPtidYear(patient.getObjectId(),date_time.substring(0, 5));
        visit.is_first = "0";
        visit.emergency = "0";
        if(v_inyear.isEmpty())
            visit.is_first = "1";

        visit.patient_id = patient.getObjectId();
        visit.hn = patient.hn;
        visit.patient_age = DateUtil.calculateAgeShort1(patient.patient_birthday,visit.begin_visit_time);
        visit.visit_note = Gutil.CheckReservedWords(visit.visit_note);
        visit.visit_status=VisitStatus.isInProcess();
//        //konshow เพิ่มเพราะจะสามารถดึงรายงานได้สะดวกขึ้น และได้ข้อมูลที่ถูกต้อง เพิ่มฟิลด์นี้ในไฟล์ sql updatev3_92.sql
        visit.visit_record_date_time = theHO.date_time;
//        //konshow เพิ่มเพราะจะสามารถดึงรายงานได้สะดวกขึ้น และได้ข้อมูลที่ถูกต้อง เพิ่มฟิลด์นี้ในไฟล์ sql updatev3_92.sql
        visit.visit_record_staff = theHO.theEmployee.getObjectId();
        theHosDB.theVisitDB.insert(visit);
        //PAYMENT ////////////////////////////////////////manage Object Payment
        for(int i= 0,size=payment.size(); i< size;i++)
        {
            Payment p = (Payment)payment.get(i);
            Plan plan = theHosDB.thePlanDB.selectByPK(p.plan_kid);
            if(plan!=null)
                p.contract_kid = plan.contract_id;
            else
                p.contract_kid = Plan.SELF_PAY;
            p.priority  = String.valueOf(i);
            p.visit_id = visit.getObjectId();
            p.visit_payment_staff_record = theHO.theEmployee.getObjectId();
            p.visit_payment_record_date_time = theHO.date_time;
            p.generateOID(i);
            theHosDB.thePaymentDB.insert(p);
        }
    //Refer /////////////////////////////////////// บันทึกข้อมูล Refer in
        if(!visit.refer_in.equals(""))
        {
            Refer theRefer = theHO.initReferIn(visit);
            theHosDB.theReferDB.insert(theRefer);
        }
        Transfer t = theHO.initUDTransfer(sp.getObjectId(),date_time, "", Active.isEnable()
                ,patient.getObjectId(),visit.getObjectId());
        t.service_start_time = date_time;
        theHosDB.theTransferDB.insert(t);
        ListTransfer lt = HosObject.initListTransfer(patient,visit,t,sp);
        if(theLookupControl.readOption().inqueuevisit.equals(Active.isEnable()))
        {   //ใช้คิว
            if(qv != null && qv.getObjectId()!=null)
            {
                qv = intReadSeqQueueVisit(qv.getObjectId());
                MapQueueVisit mqv = new MapQueueVisit();
                mqv.visit_id = visit.getObjectId();
                mqv.queue_visit = qv.getObjectId();
                mqv.queue = qv.queue;
                mqv.active = Active.isEnable();
                lt.color = qv.color;
                lt.queue = mqv.queue;
                lt.description = qv.description;
                theHosDB.theMapQueueVisitDB.save(mqv);
            }
        }
        theHosDB.theQueueTransferDB.insert(lt);
    ///////////////////////////////////////////////////////////////////
        return lt;
    }
    /**
     *
     * @param patient
     * @param visit
     * @param payment
     * @param sp
     * @param qv
     * @param date_time
     * @throws Exception
     */
    public void intVisitPatient(Patient patient,Visit visit,Vector payment
    ,ServicePoint sp,QueueVisit qv,String date_time) throws Exception
    {
        ListTransfer lt = intUDVisitPatient(patient,visit,payment,sp,qv,date_time);
        if(lt==null)
            return;
        ////////////////////////////////////////////////////////////////////
        //amp:06/04/2549 ที่ย้ายมาไว้ด้านบน เพราะใน FN:theHO.setVisit(visit) มันไปเคลียร์ theHO.theAppointment
        //amp: 25/02/2549 สั่ง order ล่วงหน้าของการนัด
        //tuk: 25/07/2549 เพื่อให้สั่งสามารถสั่ง Xray ล่วงหน้าได้ เนื่องจาก initResultXray ต้องใช้ theHO.theVisit
        theHO.thePatient = patient;
        theHO.setVisit(visit);
        theHO.initVisitExt();
        theHO.vVisitPayment = payment;
        theHO.vTransfer = theHosDB.theTransferDB.selectByVisitId(visit.getObjectId());

        if(theLookupControl.readOption().inqueuevisit.equals(Active.isEnable())) 
            if(qv != null && qv.getObjectId()!=null)
                theHO.theQV = qv;
        theHO.theListTransfer = lt;

        theOrderControl.intCheckAutoOrder(patient,visit,payment,date_time);
    /////////////////////////////////////////////////////////////////// 
    }
   
    /*************************************************************************/
    public boolean visitPatient(Visit visit,Vector payment,QueueVisit qv)
    {
        if(theHO.thePatient==null)
            return visitFamily(visit,payment,qv);
        else
            return visitPatient(visit,payment,qv,false);
    }

    public int seacrhWSRightByPid(HosDialog hd) {
        RightData rightData = null;
        int res2 = 0;
        int input_pid = 0;
        try
        {
//            System.err.println("theHO.theFamily.pid " + theHO.theFamily.pid);
//            System.err.println("theHO.theWSConfig.user_name " + theHO.theWSConfig.user_name);
//            System.err.println("theHO.theWSConfig.password " + theHO.theWSConfig.password);
            URL url = new URL(this.theHO.theWSConfig.url);
            th.go.nhso.rightsearch.RightsSearchServiceService service = new th.go.nhso.rightsearch.RightsSearchServiceService(url);
            th.go.nhso.rightsearch.RightsSearchService port = service.getRightsSearchServicePort();
            if(theHO.thePatient == null)
            {
                return 11;
            }
            if(theHO.thePatient.pid.equals("") || theHO.thePatient.pid.length() != 13)
            {
                theHO.thePatient.pid = JOptionPane.showInputDialog(this.theUS.getJFrame(),"กรุณาระบุหมายเลขบัตรประชาชน 13 หลัก");
                if(theHO.thePatient.pid==null)
                {
                    theHO.thePatient.pid = "";
                    return 10;
                }
                input_pid = 1;
            }
            rightData = port.seacrhByPid(theHO.thePatient.pid, theHO.theWSConfig.user_name, theHO.theWSConfig.password);
            
            if(input_pid==1)
                theHO.thePatient.pid = "";
            if(rightData.getWsStatus().equals("WS003"))
            {
                return 5;
            }
            if(rightData.getWsStatus().equals("WS002")||rightData.getFname()==null)
            {
                return 0;
            }
            if(!rightData.getWsStatus().equals("WS001"))
            {
                return 8;
            }
            if(!theHO.thePatient.patient_name.equals(rightData.getFname())
                    || !theHO.thePatient.patient_last_name.equals(rightData.getLname()))
            {
               int res = JOptionPane.showConfirmDialog(theUS.getJFrame()
                       , "ยืนยันการเปลี่ยนชื่อให้ตรงตามฐานข้อมูล สปสช จาก "
                       +theHO.thePatient.patient_name
                       +" "+theHO.thePatient.patient_last_name + " ไปเป็น "
                       +rightData.getFname() + " " + rightData.getLname(), "ยืนยันการเปลี่ยนชื่อให้ตรงตามฐานข้อมูล สปสช"
                       ,JOptionPane.YES_NO_OPTION);
               if(res == JOptionPane.YES_OPTION)
               {
                   theHO.thePatient.f_prefix_id = rightData.getTitle();
                   theHO.thePatient.f_sex_id = rightData.getSex();
                   theHO.thePatient.patient_name = rightData.getFname();
                   theHO.thePatient.patient_last_name = rightData.getLname();
                   String age = DateUtil.calculateAge(theHO.thePatient.patient_birthday,theHO.date_time);
                   int ret=thePatientControl.savePatient(theHO.thePatient,age
                           ,theHO.thePatient.relation,theHO.vNCD);
//                   readVisitPatientByVn(this.theHO.theVisit.vn);
                   thePatientControl.readPatientByHn(theHO.thePatient.hn);
                   theUS.setStatus("บันทึกข้อมูลเสร็จสิ้น", UpdateStatus.COMPLETE);
               }
               
               /*theHO.vPatientPayment
                thePaymentNow.card_ins_date =  dateComboBoxFrom.getText();
                thePaymentNow.card_exp_date = dateComboBoxTo.getText();
                thePaymentNow.card_id = Gutil.CheckReservedWords(jTextFieldCardID.getText());
                thePaymentNow.visit_payment_active = "1";
                thePaymentNow.hosp_main = this.integerTextFieldHosMainCode.getText();
                thePaymentNow.hosp_sub = this.integerTextFieldHosSubCode.getText();
                */

            }
            if(rightData.getMaininsclName()==null || rightData.getMaininsclName().equals("") || rightData.getMaininsclName().equals("null"))
            {
               return 4;
            }
//            System.err.println("rightData.getFname() = "+rightData.getFname());
//            System.err.println("rightData.getLname() = "+rightData.getLname());
//            System.err.println("rightData.getStartdate() = "+rightData.getStartdate());
//            System.err.println("rightData.getExpdate() = "+rightData.getExpdate());
//            System.err.println("rightData.getCardId() = "+rightData.getCardId());
//            System.err.println("rightData.getHmain() = "+rightData.getHmain());
//            System.err.println("rightData.getHsub() = "+rightData.getHsub());
//
//            System.err.println(rightData.getChat());
//            System.err.println(rightData.getCountSelect());
//            System.err.println(rightData.getMaininscl());
//            System.err.println(rightData.getMaininsclMain());
//            System.err.println(rightData.getMaininsclName());
//            System.err.println(rightData.getMastercupId());
//            System.err.println(rightData.getPaidModel());
//            System.err.println(rightData.getStartdateSss());
//            System.err.println(rightData.getCountSelect());
//            System.err.println(rightData.getWsStatus());
//            System.err.println(rightData.getWsid());
//            System.err.println(rightData.getWsidBatch());
//
//            System.err.println("rightData.getPaidModel() = "+rightData.getPaidModel());
//            System.err.println("rightData.getMaininscl() = "+rightData.getMaininscl());
//            System.err.println("rightData.getMaininsclMain() = "+rightData.getMaininsclMain());
//            System.err.println("rightData.getMaininsclName() = "+rightData.getMaininsclName());
//            System.err.println("rightData.getMastercupId() = "+rightData.getMastercupId());
//            System.err.println("rightData.getNewExpdate() = "+rightData.getNewExpdate());
//            System.err.println("rightData.getNewHmain() = "+rightData.getNewHmain());
//            System.err.println("rightData.getNewHsub() = "+rightData.getNewHsub());
//            System.err.println("rightData.getNewMaininscl() = "+rightData.getNewMaininscl());
//            System.err.println("rightData.getNewStartdate() = "+rightData.getNewStartdate());
            Payment thePaymentNow = new Payment();
            thePaymentNow.card_id = rightData.getCardId();
            if(thePaymentNow.card_id == null || thePaymentNow.card_id.equals("") || thePaymentNow.card_id.equals("null"))
                thePaymentNow.card_id = rightData.getPersonId();
            if(thePaymentNow.card_id==null || thePaymentNow.card_id.equals("null"))
                thePaymentNow.card_id = "";
            thePaymentNow.visit_payment_active = "1";
            thePaymentNow.hosp_main = rightData.getHmain();
            thePaymentNow.hosp_sub = rightData.getHsub();
            thePaymentNow.priority = "0";
            if(rightData.getStartdate()!=null && !rightData.getStartdate().equals("") 
                    && !rightData.getStartdate().equals("null")
                    && rightData.getStartdate().length()>=8)
            {
                thePaymentNow.card_ins_date = rightData.getStartdate().substring(0,4)
                       +"-"+rightData.getStartdate().substring(4,6)+"-"+rightData.getStartdate().substring(6,8);
            }
            if(rightData.getExpdate()!=null && !rightData.getExpdate().equals("") 
                    && !rightData.getExpdate().equals("null") 
                    && !rightData.getExpdate().equals("NoExp")
                    && rightData.getExpdate().length()>=8)
            {
                thePaymentNow.card_exp_date = rightData.getExpdate().substring(0,4)
                       +"-"+rightData.getExpdate().substring(4,6)+"-"+rightData.getExpdate().substring(6,8);
            }
            if(theHO.vPatientPayment==null)
                theHO.vPatientPayment = new Vector();
            res2 = hd.showPanelMapPlan(rightData,thePaymentNow);
        }
//        catch(com.sun.xml.internal.ws.client.ClientTransportException e2)
//        {
//            e2.printStackTrace();
//            System.err.println("rightData.getWsStatus()1 = "+rightData.getWsStatus());
//            return 7;
//        }
//        catch(java.net.UnknownHostException bb)
//        {
//            System.err.println("rightData.getWsStatus()0 = "+rightData.getWsStatus());
//            bb.printStackTrace();
//        }
        catch(javax.xml.ws.WebServiceException e)
        {
            e.printStackTrace();
            if(e.getMessage().indexOf("Failed to access the WSDL")>=0)
                return 6;
            if(e.getMessage().indexOf("Connection reset")>=0)
                return 9;
            else if(e.getMessage().indexOf("HTTP transport error")>=0)
                return 7;
            return 6;
        }

        catch(Exception ex)
        {
            ex.printStackTrace();
            this.theUS.setStatus("การเชื่อมต่อล้มเหลว",UpdateStatus.WARNING);
        }

//        catch(Throwable aa)
//        {
//            System.err.println("rightData.getWsStatus()2 = "+rightData.getWsStatus());
//            aa.printStackTrace();
//        }
        return res2;
    }
    public boolean visitPatient(Visit visit,Vector payment,QueueVisit qv,boolean mode)
    {
        Constant.println(UseCase.UCID_visitPatient);
        String objectid =   null;
        try{
            ///////////////////////////////////////////////////////////////////
            //ตรวจสอบประชากรว่ามีหรือไม่ถ้ามีก็ เป็นผู้ป่วยอัตโนมัติ
            if(theHO.theFamily==null){
                theUS.setStatus("กรุณาตรวจสอบความเป็นประชากรก่อนการนำเข้าสู่กระบวนการ",UpdateStatus.WARNING);
                return false;
            }
            if(theHO.thePatient==null){
                theUS.setStatus("กรุณาตรวจสอบความเป็นผู้ป่วยก่อนการนำเข้าสู่กระบวนการ",UpdateStatus.WARNING);
                return false;
            }
            theConnectionInf.open();
            theLookupControl.intReadDateTime();
            intVisitPatient(theHO.thePatient,visit,payment,theHO.theServicePoint,qv,theHO.date_time);
            theHO.vVisit = theHosDB.theVisitDB.selectListByPtid(theHO.thePatient.getObjectId());
            ///////////////////////////////////////////////////////////////////
            if(visit != null)
                objectid = visit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_visitPatient,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_visitPatient,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex) {
            theSystemControl.setStatus(UseCase.TH_visitPatient,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_visitPatient,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally {
            theConnectionInf.close();
        }
    }
    
    public boolean visitFamily(Visit visit,Vector payment,QueueVisit qv)
    {
        Constant.println("public void visitFamily(Visit visit,Vector payment)");
        theConnectionInf.open();//begin();
        boolean status = false;
        try{
            theLookupControl.intReadDateTime();
            //ตรวจสอบประชากรว่ามีหรือไม่ถ้ามีก็ เป็นผู้ป่วยอัตโนมัติ
            if(theHO.theFamily==null){
                theUS.setStatus(("กรุณาเลือกประชากร"),UpdateStatus.WARNING);
                return false;
            }
            int ret = thePatientControl.intSavePatient(theHO.theFamily,theHO.date_time,true);
            if(theHO.thePatient==null){
                return false;
            }
            theHosDB.theChronicDB.updatePtidByFid(theHO.thePatient.getObjectId(),theHO.theFamily.getObjectId());
            theHosDB.theSurveilDB.updatePtidByFid(theHO.thePatient.getObjectId(),theHO.theFamily.getObjectId());
            theHosDB.theDeathDB.updatePtidByFid(theHO.thePatient.getObjectId(),theHO.theFamily.getObjectId());
            theHosDB.thePatientPaymentDB.updatePtidByFid(theHO.thePatient.getObjectId(),theHO.theFamily.getObjectId());
            thePatientControl.intReadPatientSuit(theHO.thePatient);
            
            intVisitPatient(theHO.thePatient,visit,payment,theHO.theServicePoint,qv,theHO.date_time);
            //theConnectionInf.commit();
            
            theHS.theVisitSubject.notifyVisitPatient(Constant.getTextBundle("การนำผู้ป่วยเข้าสู่กระบวนการเสร็จสิ้น"),UpdateStatus.COMPLETE);
            status = true;
            return status;
        }
        catch(Exception ex)
        {    
//            theConnectionInf.rollback();
            ex.printStackTrace(Constant.getPrintStream());
            if(ex.getMessage().startsWith("UC"))
                theUS.setStatus(ex.getMessage().substring(3),UpdateStatus.WARNING);
            else
                theUS.setStatus(("การนำผู้ป่วยเข้าสู่กระบวนการผิดพลาด"),UpdateStatus.ERROR);
            status = false;
            return status;
        }
        finally{
//          theSystemControl.saveLog(HospitalosLog.UC_visitFamily, visit.getObjectId(), "นำประชากรเข้าสู่กระบวนการ", status);
            theConnectionInf.close();
        }
    }
  /**
   *@visit from appointment 
   */

    /*************************************************************************/
    public void visitPatientApp(Visit visit,Vector payment,QueueVisit qv,Appointment app)
    {
        Constant.println(UseCase.UCID_visitPatient);
        String objectid =   null;
        Constant.println("public void visitPatient(Visit visit,Vector payment)");
        //////////////////////////////////////////////////////////////
        theConnectionInf.open();//begin();
        try{
            theLookupControl.intReadDateTime();
            visit.patient_id = theHO.thePatient.getObjectId();
            visit.hn = theHO.thePatient.hn;
            //////////////////////////////////////////////
            ServicePoint sp = theLookupControl.readServicePointById(app.clinic_code);
            intVisitPatient(theHO.thePatient,visit,payment,sp,qv,theHO.date_time);
            theOrderControl.intCheckAppointmentOrder(theHO.thePatient,visit,theHO.date_time,app); 
            ///////////////////////////////////////////////////////////
            app.status = AppointmentStatus.COMPLETE;
            app.vn = visit.vn;
            app.visit_id = visit.getObjectId();
            theHosDB.theAppointmentDB.update(app);            
            //theConnectionInf.commit();
            theHS.theVisitSubject.notifyVisitPatient(Constant.getTextBundle("การนำผู้ป่วยเข้าสู่กระบวนการเสร็จสิ้น")
                    ,UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_visitPatient,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_visitPatient,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {    
            theConnectionInf.rollback();
            ex.printStackTrace(Constant.getPrintStream());
            if(ex.getMessage().startsWith("UC"))
                theUS.setStatus(ex.getMessage().substring(3),UpdateStatus.WARNING);
            else
                theUS.setStatus(("การนำผู้ป่วยเข้าสู่กระบวนการผิดพลาด"),UpdateStatus.ERROR);
            theSystemControl.setStatus(UseCase.TH_visitPatient,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_visitPatient,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     *
     * @return
     */
    public boolean dropVisitPatient()
    {
        Constant.println("public void dropVisitPatient(Visit vnPatient)");
        Constant.println(UseCase.UCID_dropVisit);
        String objectid =   null;
        if(theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
        if(theHO.theVisit==null)
        { 
            theUS.setStatus(("ยังไม่ได้เลือกผู้ป่วย"),UpdateStatus.WARNING);
            return false;
        }
        //amp:3/6/2549 เพ่มในกรณีค้างบันทึกก็ห้าม Drop Visit
        if(theHO.theVisit.visit_status.equals("2"))
        {
            theUS.setStatus(("ผู้ป่วยอยู่ในสถานะค้างบันทึกแล้ว"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals("3"))
        {
            theUS.setStatus(("ผู้ป่วยอยู่ในสถานะจบกระบวนการแล้ว"),UpdateStatus.WARNING);
            return false;
        }
        if(!theHO.theVisit.lock_user.equals(theHO.theEmployee.getObjectId()))
        {
            theUS.setStatus(("ผู้ป่วยถูกล็อกอยู่ไม่สามารถยกเลิกการเข้ารับบริการได้"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_type.equals(VisitType.IPD))
        {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถยกเลิกได้") +
                    " " +
                    Constant.getTextBundle("กรุณาย้อนกลับการ Admit ก่อนยกเลิกการรับบริการ"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.vBilling != null && !theHO.vBilling.isEmpty())
        {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถยกเลิกได้") +
                    " " +
                    Constant.getTextBundle("เนื่องจากมีการรับชำระเงินแล้ว"),UpdateStatus.WARNING);
            return false;
        }
//        boolean ret_confirm = theUS.confirmBox( ("ต้องการยกเลิกการเข้ารับบริการ")
//        + " " + Constant.getTextBundle("ยืนยันยกเลิกการเข้ารับบริการ"),UpdateStatus.WARNING);
//        if(!ret_confirm){
//            return false;
//        }
//        if(theHO.vOrderItem!=null && theHO.vOrderItem.size()>0 ){
//            ret_confirm = theUS.confirmBox(("ต้องการยกเลิกรายการสั่งตรวจรักษาด้วย")
//                    + " " + Constant.getTextBundle("ยืนยัน"),UpdateStatus.WARNING);
//            if(!ret_confirm){
//                return false;
//            }
//        }
        String cause = DialogCause.showDialog(theUS.getJFrame(),Constant.getTextBundle("การยกเลิกการเข้ารับบริการ"));
        if(cause.equals(""))
        {   
            theUS.setStatus(("กรุณากรอกสาเหตุการยกเลิกการเข้ารับบริการ"),UpdateStatus.WARNING);
            return  false;
        } 
        else if(cause.equals("CancelDialog"))
            return false;
        
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            //ยกเลิกการเข้ารับบริการ
            theOrderControl.intCancelOrderItem(theHO.vOrderItem);
            theHO.theVisit.visit_status = VisitStatus.isDropVisit(); 
            theHO.theVisit.financial_discharge_time = date_time;
            theHO.theVisit.financial_discharge_user = theHO.theEmployee.getObjectId();
            theHO.theVisit.locking = Active.isDisable();
            theHO.theVisit.is_discharge_doctor = Active.isEnable();
            theHO.theVisit.is_discharge_money = Active.isEnable();
            theHO.theVisit.stat_dx = cause;
            theHosDB.theVisitDB.update(theHO.theVisit);
//            objectid = theHO
            //ทำการลบรายการจากคิวผู้ป่วย
            theHosDB.theQueueTransferDB.deleteByVisitID(theHO.theVisit.getObjectId());
            //amp:21/7/2549:ลบคิวของห้องแลปและห้องxray
            theHosDB.theQueueLabDB.deleteByVisitID(theHO.theVisit.getObjectId()); 
            theHosDB.theQueueXrayDB.deleteByVisitID(theHO.theVisit.getObjectId());  
            //เปลี่ยนสถานะการนัดกลับเป็นรอการนัดเหมือนเดิม
            Vector vApp = theHosDB.theAppointmentDB.selectByVN(theHO.theVisit.vn);
            for(int i=0,size=vApp.size();i<size;i++)
            {
                Appointment app = (Appointment)vApp.get(i);
                app.status = AppointmentStatus.WAIT;
                app.visit_id = "";
                app.vn = "";
                theHosDB.theAppointmentDB.update(app);
            }
            //ทำการลบรายการ order ที่ไม่ยังไม่มีการยึนยัน
            //Vector theOrder = theHosDB.theOrderItemDB.deleteByVidOS(theHO.theVisit.getObjectId(),OrderStatus.NOT_VERTIFY);
            Vector theOrder = theHosDB.theOrderItemDB.selectOrderItemByVisitID(theHO.theVisit.getObjectId());
            if(theOrder!=null)
            {
                for(int i=theOrder.size()-1; i>=0; i--)
                {
                    OrderItem oi = (OrderItem)theOrder.get(i);
                    if(oi.vertifier.equals(""))
                    {
                        if(oi.category_group.equals(Active.isEnable()))
                            theHosDB.theOrderItemDrugDB.deleteByOrderItemId(oi.getObjectId());
                        theHosDB.theOrderItemDB.delete(oi);
                    }
                }
            }
            //////////////////////////////////////////////////////////////////////
            theHO.clearFamily();
            if(theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
            theHS.theVisitSubject.notifyDropVisit(Constant.getTextBundle("การยกเลิกการรับบริการผู้ป่วยเสร็จสิ้น"),UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_dropVisit,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_dropVisit,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex) {   
            theSystemControl.setStatus(UseCase.TH_dropVisit,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_dropVisit,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }
    }

    /**
     * @roseuid 3F83F9ED0138
     */
    
    /**
     * @not deprecated henbe unused
     * เป็น Function ถ้าต้องการให้ล้อก ต้องให้ค่าเป็น 1 
     **/
    public void readVisitPatientByVid(String vid){
        readVisitPatientByVid(vid,"0", null);
    }
    /**
     *@deprecated henbe unused
     */
    public void readVisitPatientByVid(String vid,String remain)
    {  
        readVisitPatientByVid(vid,remain,null);
    }
    public void checkFamily()
    {
        ResultSet rs;
        try
        {
            if(theHO.theFamily!=null && theHO.thePatient!=null
                    && this.theHO.theFamily.getObjectId().equals(theHO.thePatient.getObjectId()))
            {
                rs = theConnectionInf.eQuery("select * from t_health_family where patient_name = '"
                        +theHO.thePatient.patient_name+"' and patient_last_name = '"+theHO.thePatient.patient_last_name+"'");
                String fid = "";
                int i = 0;
                if(rs.next())
                {
                    fid = rs.getString("t_health_family_id");
                    i++;
                }
                if(i==1)
                {
                    this.theHO.thePatient.family_id = fid;
                    theConnectionInf.eUpdate("update t_patient set t_health_family_id = '"
                            +fid+"' where t_patient_id = '"+theHO.thePatient.getObjectId()+"'");
                    thePatientControl.readPatientByHn(theHO.thePatient.hn);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void readVisitPatientByVid(String vid,String remain,ListTransfer t2)
    {
        Constant.println("private void readVisitPatient(String vid,int mode)");
        theConnectionInf.open();
        try{
            checkFamily();
            intUnlockVisit(theHO.theVisit);
            theHO.clearFamily();
            Visit visit = theHosDB.theVisitDB.selectByPK(vid);
            if(visit==null)
            {
                theUS.setStatus(("ไม่พบข้อมูลการรับบริการของผู้ป่วยในฐานข้อมูล"),UpdateStatus.WARNING);
                return;
            }
            Patient patient = theHosDB.thePatientDB.selectByPK(visit.patient_id);
            if(patient==null)
            {
                theUS.setStatus(("ไม่พบข้อมูลผู้ป่วยในฐานข้อมูล"),UpdateStatus.WARNING);
                return;
            }
            thePatientControl.intReadFamilySuit(patient.getFamily(),patient);
            thePatientControl.intReadPatientSuit(patient);
            theLookupControl.intReadDateTime();
            thePatientControl.intReadVisitSuit(visit);
            thePatientControl.intLockVisit(theHO.date_time);
            //intConfirmDoctorTreatment(true,date_time);
            
            intSaveTransferCatch(theHO.theEmployee, theHO.date_time);
            
            if(t2!=null)
            {
                if("".equals(t2.order_id))
                {
                    theHO.orderSecret = "";
                    theHO.specimenCode = "";
                }
                else
                {
                    theHO.orderSecret = t2.order_id;
                    theHO.specimenCode = t2.specimen_code;
                    theHO.vOrderItem = theHosDB.theOrderItemDB.selectOrderItemByVNAndOrderNotSecret(
                            visit.getObjectId(),CategoryGroup.isLab(),true,true,false);
                }                 
            }

            //ตรวจสอบว่าเป็นผู้ป่วยที่มีแลบปกปิดหรือไม่
            theHO.labQueueRemain = remain;               
            theHS.theVisitSubject.notifyReadVisit(Constant.getTextBundle("เรียกดูข้อมูลการรับบริการของผู้ป่วยเสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(("เรียกดูข้อมูลการรับบริการของผู้ป่วยเกิดความผิดพลาด"),UpdateStatus.ERROR);
        }        
        finally{
            theConnectionInf.close();
        }     
    }
    /**
     *hosv4
     *@author henbe
     *@see หากมีการแก้ไขในนี้ต้องไปแก้ใน readNextVisit ด้วยนะ
     */
    public void readPreviousVisit(Patient pt,Visit visit)
    {
        Constant.println("private void readPreviousVisit(Patient pt,Visit visit)");
        Constant.println(UseCase.UCID_readPreviousVisit);
        String objectid =   null;
        theConnectionInf.open();
        try{
            if(pt==null){
                theUS.setStatus(("กรุณาเลือกผู้ป่วยก่อนการดูประวัติการรักษา"),UpdateStatus.WARNING);
                return;
            }
            //ฟังชันนี้เป็นการค้นตามลำดับวันที่จากอดีตไปปัจจุบัน ดังนั้นลำดับสุดท้ายคือปัจจุบัน
            //การเพิ่ม index คือการไปอนาคต
            //theHosDB.theVisitDB.selectListByPtid(pt.getObjectId());
            Vector vvisit = theHO.vVisit;
            if(vvisit.isEmpty()){
                theUS.setStatus(("ผู้ป่วยคนนี้ไม่มีประวัติการรับบริการ"),UpdateStatus.WARNING);
                return;
            }
            //i คือ index ของ visit สุดท้ายที่เราจะดูข้อมูล
            int i=vvisit.size()-1;
            int size=0;
            if(visit!=null)
            {
                for(i=0,size=vvisit.size();i<size;i++)
                {
                    Visit in_visit = (Visit)vvisit.get(i);
                    if(in_visit.getObjectId().equals(visit.getObjectId())) {
                        break;
                    }
                }                
                if(i<=0){
                    theUS.setStatus(("การรับบริการนี้คือการรับบริการครั้งแรกไม่สามารถดูข้อมูลก่อนหน้านี้ได้"),UpdateStatus.WARNING);
                    return;
                }
                //เพิ่มค่าเพื่อให้ i เป็นค่าที่จะไปค้นมาแสดงเลย
                i = i-1; 
            }
            else{
                if(vvisit.isEmpty()){
                    theUS.setStatus(("ยังไม่มีประวัติการรับบริการของผู้ป่วยคนนี้"),UpdateStatus.WARNING);
                    return;
                }
                i=vvisit.size()-1;
            }
            if(theHO.isLockingVisit()) {
                theHO.theVisit.locking = "0";
                theHosDB.theVisitDB.update(theHO.theVisit);
            }
            theHO.clearVisit();
            //ถ้า visit นั้นไม่ใช่ visit แรกก็ย้อนอดีตได้อยู่แล้ว
            theLookupControl.intReadDateTime();
            Visit in_visit = (Visit)vvisit.get(i);
            in_visit = theHosDB.theVisitDB.selectByPK(in_visit.getObjectId());
            theLookupControl.intReadDateTime();
            thePatientControl.intReadVisitSuit(in_visit);
            intLockVisit(theHO.date_time);
            intSaveTransferCatch(theHO.theEmployee, theHO.date_time);
            theHS.theVisitSubject.notifyReadVisit(Constant.getTextBundle("เรียกดูข้อมูลการรับบริการของผู้ป่วยเสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_readPreviousVisit,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_readPreviousVisit,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_readPreviousVisit,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_readPreviousVisit,objectid,ex,UpdateStatus.ERROR);
        }        
        finally{
            theConnectionInf.close();
        }
    }
    /**
     *hosv4
     *@author henbe
     *@see หากมีการแก้ไขในนี้ต้องไปแก้ใน readPreviousVisit ด้วยนะ
     */
    public void readNextVisit(Patient pt,Visit visit)
    {   
        Constant.println("private void readPreviousVisit(Patient pt,Visit visit)");
        Constant.println(UseCase.UCID_readNextVisit);
        String objectid =   null;
        theConnectionInf.open();
        try{
            if(pt==null){
                theUS.setStatus(("กรุณาเลือกผู้ป่วยก่อนการดูประวัติการรักษา"),UpdateStatus.WARNING);
                return;
            }
            //ฟังชันนี้เป็นการค้นตามลำดับวันที่จากอดีตไปปัจจุบัน ดังนั้นลำดับสุดท้ายคือปัจจุบัน
            //การเพิ่ม index คือการไปอนาคต
            //Vector vvisit = theHosDB.theVisitDB.selectListByPtid(pt.getObjectId());
            Vector vvisit = theHO.vVisit;            
            int i=vvisit.size()-1;
            int size=0;
            if(visit!=null)
            {
                for(i=0,size=vvisit.size();i<size;i++)
                {
                    Visit in_visit = (Visit)vvisit.get(i);
                    if(in_visit.getObjectId().equals(visit.getObjectId())) {
                        break;
                    }
                }
                if(i>=vvisit.size()-1){
                    theUS.setStatus(("การรับบริการนี้คือการรับบริการครั้งสุดท้ายไม่สามารถดูข้อมูลหลังจากนี้ได้"),UpdateStatus.WARNING);
                    return;
                }
                //เพิ่มค่าเพื่อให้ i เป็นค่าที่จะไปค้นมาแสดงเลย
                i = i+1; 
                theLookupControl.intReadDateTime();
                if(theHO.isLockingVisit()) {
                    theHO.theVisit.locking = "0";
                    theHosDB.theVisitDB.update(theHO.theVisit);
                }
                //ถ้า visit นั้นไม่ใช่ visit แรกก็ย้อนอดีตได้อยู่แล้ว
                Visit in_visit = (Visit)vvisit.get(i);
                in_visit = theHosDB.theVisitDB.selectByPK(in_visit.getObjectId());
                theLookupControl.intReadDateTime();
                thePatientControl.intReadVisitSuit(in_visit);
                intLockVisit(theHO.date_time);
                intSaveTransferCatch(theHO.theEmployee, theHO.date_time);
                theHS.theVisitSubject.notifyReadVisit(Constant.getTextBundle("เรียกดูข้อมูลการรับบริการของผู้ป่วยเสร็จสิ้น"),UpdateStatus.COMPLETE);
                if(theHO.theVisit != null)
                    objectid = theHO.theVisit.getObjectId();
                theSystemControl.setStatus(UseCase.TH_readNextVisit,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_readNextVisit,objectid,null,UpdateStatus.COMPLETE);
            }
            else{
                theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่มีการรับบริการหลังจากนี้"),UpdateStatus.WARNING);
                return;
            }
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_readNextVisit,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_readNextVisit,objectid,ex,UpdateStatus.ERROR);
        }        
        finally{
            theConnectionInf.close();
        }
    }
    /**
     *hosv4
     */
    public void readVisitPatientByVn(String vn)
    {
        Constant.println("private void readVisitPatient(String vid,int mode)");
        Constant.println(UseCase.UCID_readVisitPatientByVn);
        String objectid =   null;
            theConnectionInf.open();
        try{
            intUnlockVisit(theHO.theVisit);            
            vn = theLookupControl.getNormalTextVN(vn);
            Visit visit = theHosDB.theVisitDB.selectByVn(vn);
            if(visit==null){
                theUS.setStatus(("ไม่พบข้อมูลการรับบริการของผู้ป่วยในฐานข้อมูล"),UpdateStatus.WARNING);
                return;
            }       
            Patient patient = theHosDB.thePatientDB.selectByPK(visit.patient_id);   
            if(patient==null)
            {
                theUS.setStatus(("ไม่พบข้อมูลผู้ป่วยในฐานข้อมูล"),UpdateStatus.WARNING);
                return;
            }       
            theLookupControl.intReadDateTime();
            thePatientControl.intReadFamilySuit(patient.getFamily(),patient);
            thePatientControl.intReadPatientSuit(patient); 
            thePatientControl.intReadVisitSuit(visit);
            intLockVisit(theHO.date_time);
            intSaveTransferCatch(theHO.theEmployee, theHO.date_time);
            theHS.theVisitSubject.notifyReadVisit(Constant.getTextBundle("เรียกดูข้อมูลการรับบริการของผู้ป่วยเสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_readVisitPatientByVn,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_readVisitPatientByVn,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_readVisitPatientByVn,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_readVisitPatientByVn,objectid,ex,UpdateStatus.ERROR);
        }        
        finally{
            theConnectionInf.close();
        }
    }
 
  
    
    /**
     * @not deprecated henbe unused
     * @param emp 
     * @param date_time 
     * @throws java.lang.Exception 
     * @return 
     */
     protected boolean intSaveTransferCatch(Employee emp,String date_time)
     throws Exception{
         return intSaveTransfer(true, emp,date_time);
     }
    /**
     * @not deprecated henbe unused
     * @param emp 
     * @param date_time 
     * @throws java.lang.Exception 
     * @return 
     */
     protected boolean intSaveTransferThrow(Employee emp,String date_time)
     throws Exception{
         return intSaveTransfer(false, emp,date_time);
     }

    protected void intAutoCompleteVisit(Visit visit) throws Exception {

            //ระบบจะต้องตรวจสอบว่าหากจำหน่ายทางการแพทย์ปกติ
            //ผู้จำหน่ายไม่ใช่ stat จะต้องไปรอในคิว stat สถานะเป็นค้างบันทึก
            //ผู้จำหน่าย stat ไม่ต้องไปรอในคิว stat สถานะเป็นจบกระบวนการ
            //Option จำหน่ายโดยไม่ผ่าน stat ไม่ต้องไปรอในคิว stat สถานะเป็นจบกระบวนการ
        //จำหน่ายการเงินก่อนการแพทย์ ก็จบแบบนี้;
            if(!theLookupControl.readOption().auto_complete_visit.equals("1"))
                return;
            if(!visit.visit_type.equals(VisitType.OPD))
                return;
            if(theHO.vDiagIcd10==null)
                return;
            if(theHO.vDiagIcd10.isEmpty())
                return; 
            if(visit.isDischargeDoctor()) 
                theHosDB.theQueueICDDB.deleteByVisitID(visit.getObjectId()); 
            
            if(visit.isDischargeMoney() && visit.isDischargeDoctor()){
                visit.visit_status = VisitStatus.isOutProcess();
            }
    }
     private boolean intSaveTransfer(boolean visit_catch,Employee emp,String date_time)
     throws Exception
     {
         String auth = emp.authentication_id;
        if(!auth.equals(Authentication.LAB)
        && !auth.equals(Authentication.XRAY)
        && !auth.equals(Authentication.STAT)
        && theHO.vTransfer!=null
        && !theHO.vTransfer.isEmpty())
        {
            Transfer transfer = (Transfer)theHO.vTransfer.get(theHO.vTransfer.size()-1);
            //ถ้าส่งให้แพทย์คนนั้นแล้วแพทย์คนนั้นเป็นคนตรวจแล้ว
//            if((!transfer.doctor_code.equals("") 
//                    && transfer.doctor_code.equals(theHO.theEmployee.getObjectId()))
//            || transfer.doctor_code.equals(""))
                if(visit_catch){
                    //transfer.service_start_time = date_time.substring(date_time.indexOf(',')+1);
                    transfer.service_start_time = date_time;
                    transfer.service_finish_time = "";
                    transfer.status = Transfer.STATUS_PROCESS;
                    theHosDB.theTransferDB.update(transfer);
                    return true;
                }
                else{
                    //transfer.service_finish_time = date_time.substring(date_time.indexOf(',')+1);
                    transfer.service_finish_time = date_time;
                    transfer.status = Transfer.STATUS_COMPLETE;
                    theHosDB.theTransferDB.update(transfer);
                    return true;
                }
        } 
        return false;
     }
       
    
    /**
     * list สิทธิการรักษาของผู้ป่วยตาม Visit ID
     */
    public Vector listVisitPaymentByVisitId(String visit_id)
    {
        if((((visit_id == null)) || ((visit_id.length() == 0)))) return null;
        Vector v=null;
        theConnectionInf.open();
        try
        {
            v = theHosDB.thePaymentDB.selectByVisitId(visit_id);
        }
        catch(Exception ex)
        {    ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return v;
    }
  
 
    /**
     *hosv4
        //ยืนยันการตรวจรักษาของแพทย์
        //henbe_just theHC.theVisitControl.confirmDoctorTreament();
        //จุดบริการที่จะส่งไป ถ้าจุดบริการนั้นเป็นห้องตรวจให้แสดงรายชื่อแพทย์ด้วย
        //ใช้ในกรณีผู้ป่วยเป็นผู้ป่วยในและอยู่เกิน 24 ชม แล้ว จำเป็นต้องลบ queue OPD ออก
                //old version call control function
                //theVisitControl.confirmDoctorTreament();
                ////theVisitControl.removeMapVisitInIPD(theVisit);
                //theVisitControl.unlockVisitForSendPatient(theVisit);
                //theVisitControl.sentPatientServicePoint(theTransfer,t);
                //notifyshowPanelAuthentication(authentication_id,1);
     //ณ เวลาเดียวกันข้อมูลผู้ป่วยจะอยู่ที่จุดบริการเดียวกันเท่านั้นไม่สามารถอยู่หลายที่ได้ยกเว้น ผู้ป่วยใน*
     */
    public void sendVisit(Transfer t)
    {
        Constant.println(UseCase.UCID_sendVisit);
        String objectid =   null;
        if(theHO.theVisit == null) {
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return ;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return ;
        }
        if(!theHO.theVisit.lock_user.equals(theHO.theEmployee.getObjectId()))
        {
            theUS.setStatus(("ผู้ป่วยคนนี้ถูกล้อกโดยผู้ใช้คนอื่น"),UpdateStatus.WARNING);
            return;
        }        
        int t_size = theHO.vTransfer.size();
        if(t_size>0)
        {
            Transfer last_t = (Transfer)theHO.vTransfer.get(t_size-1);

            if(theHO.theVisit.visit_type.equals(VisitType.OPD)
                && t.service_point_id.equals(last_t.service_point_id)
                && t.doctor_code.equals(last_t.doctor_code)
                && !last_t.status.equals(Transfer.STATUS_COMPLETE)){
                theUS.setStatus(("ผู้ป่วยคนนี้อยู่ในจุดบริการและแพทย์ที่ต้องการส่งแล้ว"),UpdateStatus.WARNING);
                return;
            }
        }
        if(theHO.theVisit.doctor_dx.equals("")
        && theHO.theEmployee.authentication_id.equals(Authentication.DOCTOR) 
        && theHO.theEmployee.warning_dx.equals("1")){
            theUS.setStatus(("กรุณาลงผลการตรวจโรคของผู้ป่วยคนนี้ก่อนส่งไปยังจุดบริการอื่น"),UpdateStatus.WARNING);
            return;
        }
            
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            //เพื่ออะไรกันเนี่ย henbe_ask
            int result = intRemoveMapVisitInIPD(theHO.theVisit);            
            //clear old transfer//////////////////////////////////////////////////
            intSaveTransferThrow(theHO.theEmployee, date_time);
            ////////////////////////////////////////////////////////////////////
            //ตรวจสอบว่าแพทย์ตรวจหรือไม่  ถ้าตรวจก็ให้ทำการส่งผ่านห้องแพทย์ก่อน////////////////////////////
            boolean ret = theLookupControl.intConfirmDoctorTreatment(true,date_time); 
            if(ret){
                Transfer newTransfer = new Transfer();
                newTransfer.assign_time = date_time;
                newTransfer.doctor_code = theHO.theEmployee.getObjectId();
                newTransfer.patient_id = theHO.theVisit.patient_id;
                newTransfer.visit_id = theHO.theVisit.getObjectId();
                newTransfer.service_point_id = theHO.theServicePoint.getObjectId();
                newTransfer.status = Transfer.STATUS_COMPLETE;
                newTransfer.service_start_time = date_time.substring(date_time.indexOf(',')+1);
                long time = DateUtil.getDateFromText(date_time).getTime()+1000;
                date_time = DateUtil.getTextDB(new Date(time),true);
                newTransfer.service_finish_time = date_time.substring(date_time.indexOf(',')+1);
                theHosDB.theTransferDB.insert(newTransfer); 
            }
            /////////////////////////////////////////////////////////////////////
            ServicePoint sp = theLookupControl.readServicePointById(t.service_point_id);
            //insert new transfer //////////////////////////////////////////////
            //t.service_point_id = xxxx from user 
            t.visit_id = theHO.theVisit.getObjectId();
            t.patient_id = theHO.thePatient.getObjectId();
            t.status = "1";
            t.assign_time = date_time;
            t.service_start_time = "";//date_time.substring(date_time.indexOf(',')+1);
            t.service_finish_time = "";
            theHosDB.theTransferDB.insert(t);
            ////////////////////////////////////////////////////////////////////
            theHO.theVisit.locking = "0";
            theHO.theVisit.lock_user = "";
            theHO.theVisit.lock_time = "";
            theHosDB.theVisitDB.updateLocking(theHO.theVisit);
            if(sp.service_point_id.startsWith("pcu")
            || sp.getObjectId().equals(ServicePoint.HEALTH))
                    theHO.theVisit.is_pcu_service = "1";
            else    theHO.theVisit.is_hospital_service = "1";
            theHosDB.theVisitDB.updateServiceStation(theHO.theVisit);

            ////////////////////////////////////////////////////////////////////
            //update listTransfer unlock and servicePoint/////////////////////////
            //henbe_check_28oct05
            if(theHO.theListTransfer ==null){
                theHO.theListTransfer = HosObject.initListTransfer(
                    theHO.thePatient,theHO.theVisit,t,sp);
            }
            theHO.theListTransfer.assign_time = t.assign_time;
            theHO.theListTransfer.doctor = t.doctor_code;
            theHO.theListTransfer.servicepoint_id = sp.getObjectId();
            theHO.theListTransfer.name = sp.name;
            theHO.theListTransfer.locking = "0";
            theHO.theListTransfer.labstatus = theHO.theVisit.queue_lab_status;
            if(theHO.theListTransfer.getObjectId()==null)
                    theHosDB.theQueueTransferDB.insert(theHO.theListTransfer);
            else    theHosDB.theQueueTransferDB.update(theHO.theListTransfer);
            ///////////////////////////////////////////////////////////
            theHO.clearFamily();
            theHS.theVisitSubject.notifySendVisit(Constant.getTextBundle("การส่งผู้ป่วยไปยังจุดบริการเสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(t != null)
                objectid = t.getObjectId();
            theSystemControl.setStatus(UseCase.TH_sendVisit,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_sendVisit,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {    
            theSystemControl.setStatus(UseCase.TH_sendVisit,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_sendVisit,objectid,ex,UpdateStatus.ERROR);
        }
        theConnectionInf.close();
    }
/*
 *
 *
 */

    /**
     *hosv4
     *@author  tong
     *@param  String visit_id คือ เลข Visit_id เป็น key หลักของตาราง,
     *        String lock สถานะของการ lock ในครั้งนั้น จะมี 0 คือ ไม่ lock และ 1 คือ lock
     *@return   void
     *@since     version 2 bould 7
     *@version  0.0.1
     *@date 2/2/48
     *@update 
     *@description ใช้ในการ update ข้อมูลของตาราง t_queue_visit_transfer ตาม visit_id ของการ visit นั้น
     *             และขึ้นกับสถานะการ lock
     */
    public void unlockVisit()
    {
        unlockVisit(theHO.theVisit);
    }
    
    /**
     * 
     * @param visit 
     */
    public void unlockVisit(Visit visit)
    {
        Constant.println("public void unlockVisit()");
        Constant.println(UseCase.UCID_unlockVisit);
        String objectid =   null;
        if(visit != null)
            objectid = visit.getObjectId();
        if(visit == null && (theHO.thePatient!=null || theHO.theFamily!=null)){
            theHO.clearFamily();
            theHS.theVisitSubject.notifyUnlockVisit(Constant.getTextBundle("การเคลียร์หน้าจอเสร็จสิ้น")
                ,UpdateStatus.COMPLETE);
            return;
        }
        if(visit == null){
            theHS.theVisitSubject.notifyUnlockVisit(Constant.getTextBundle("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return;
        }
        if(!visit.lock_user.equals(theHO.theEmployee.getObjectId())){
            if(!theHO.theEmployee.authentication_id.equals(Authentication.ONE)
            && !theHO.theEmployee.authentication_id.equals(Authentication.ADMIN)){
//                theUS.setStatus("คุณไม่มีสิทธิในการปลดล๊อกผู้ป่วยคนนี้ ",UpdateStatus.WARNING);
                theHO.clearFamily();
                theHS.theVisitSubject.notifyUnlockVisit(Constant.getTextBundle("การเคลียร์หน้าจอเสร็จสิ้น"),UpdateStatus.COMPLETE);
                return;
            }
        }
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            /*ตรวจสอบว่าแพทย์ตรวจหรือไม่*/
//            Constant.println("11111111111111111111111111111111111111111" + theHO.theEmployee.authentication_id);
            /////////////////////////////////////////////////////
            intSaveTransferThrow(theHO.theEmployee, date_time);
            theLookupControl.intConfirmDoctorTreatment(true,date_time);
            intUnlockVisit(visit);
            theHO.clearFamily();
            theHS.theVisitSubject.notifyUnlockVisit(Constant.getTextBundle("การปลดล๊อกผู้ป่วยเสร็จสิ้น"),UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_unlockVisit,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_unlockVisit,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_unlockVisit,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_unlockVisit,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    /*
     *ถ้าเป็น ADMIN เลือกค้นไขไหนก็ unlock ได้หมด
     *ถ้าเป็น Lab เลือกคนไข้ไหนก็ไม่ unlock ให้หรอก
     *ถ้าเป็น Xrayเลือกคนไข้ไหนก็ไม่ unlock ให้หรอก
     *ถ้าเป็น อื่นๆ เลือกคนไข้ไหนก็  unlock ให้แน่นอนถ้าเขาเป็นคน lock
     * stock เอาไปใช้งานแล้ว
     */
    /**
     * 
     * @param v 
     * @throws java.lang.Exception 
     */
    public void intUnlockVisit(Visit v) throws Exception 
    {
        if(v==null){
            theHO.clearFamily();
            return;
        }
        if(v.lock_user!=null && !v.lock_user.equals(theHO.theEmployee.getObjectId())
        && !theHO.theEmployee.authentication_id.equals(Authentication.ADMIN)){
            Constant.println("ไม่มีสิทธิในการปลดล็อกผู้ป่วยคนนี้  The employee is not unlock");
            theHO.clearFamily();
            return;
        }
        v.locking = "0";
        //v.lock_user = "";
        //v.lock_time = "";
        theHosDB.theVisitDB.updateLocking(v);
        theHosDB.theQueueTransferDB.updateLockByVid(v.getObjectId());
        
        if(theHO.theListTransfer!=null){
            theHO.theListTransfer.locking = "0";
        }
        else
            Constant.println("unlockVisit() QueueTransfer is null");
    }
    
    /**
     * hosv4
     * @param theVisitPayment 
     * @param select 
     * @return 
     */
    public boolean downVPaymentPriority(Vector theVisitPayment,int select){
        Constant.println(UseCase.UCID_downVPaymentPriority);
        String objectid =   null;
        if(select == -1){
            theUS.setStatus(("กรุณาเลือกรายการ"),UpdateStatus.WARNING);
            return false;
        }
        if(theVisitPayment == null){
            theUS.setStatus(("ไม่มีรายการที่ต้องย้าย"),UpdateStatus.WARNING);
            return false;
        }
        
        if(theVisitPayment.size()-1 == select){
            theUS.setStatus(("รายการที่เลือกเป็นรายการสุดท้ายจึงไม่มีรายการที่ต้องย้าย"),UpdateStatus.WARNING);
            return false;
        }
        
        Payment p = (Payment)theVisitPayment.get(select +1);
        if(!com.hospital_os.utility.Gutil.isSelected(p.visit_payment_active))
        {   theUS.setStatus(("ไม่สามารถเลื่อนสิทธิ์ได้"),UpdateStatus.WARNING);
            p = null;
            return false;
        }
        p = (Payment)theVisitPayment.get(select);
        if(p == null){
            theUS.setStatus(("กรุณาเลือกรายการ"),UpdateStatus.WARNING);
            return false;
        }
        if(p.visit_payment_active.equals("0")){
            theUS.setStatus(("สิทธิที่ถูกยกเลิกแล้วไม่สามารถย้ายได้"),UpdateStatus.WARNING);
            return false;
        }
        //ตำแหน่งเก่า
        theConnectionInf.open();
        try{
            theLookupControl.intReadDateTime();
            //change current payment
            p.priority = String.valueOf(select+1);
            p.visit_payment_update_date_time = theHO.date_time;
            p.visit_payment_staff_update = this.theHO.theEmployee.getObjectId();
            theHosDB.thePaymentDB.update(p);

            theVisitPayment.remove(select);
            theVisitPayment.add(select + 1,p);
            //change next payment
            p = (Payment)theVisitPayment.get(select);
            p.priority = String.valueOf(select);
            p.visit_payment_update_date_time = theHO.date_time;
            p.visit_payment_staff_update = this.theHO.theEmployee.getObjectId();
            theHosDB.thePaymentDB.update(p);
            theHS.theVPaymentSubject.notifyDownVPaymentPriority(
                    Constant.getTextBundle("การเปลี่ยนแปลงสิทธิการรักษาของผู้ป่วยเสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(p != null)
                objectid = p.getObjectId();
            theSystemControl.setStatus(UseCase.TH_downVPaymentPriority,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_downVPaymentPriority,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_downVPaymentPriority,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_downVPaymentPriority,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }
    }

    
    /**
     * hosv4
     * @param theVisitPayment 
     * @param select 
     * @return 
     */
    public boolean upVPaymentPriority(Vector theVisitPayment,int select){
        Constant.println(UseCase.UCID_upVPaymentPriority);
        String objectid =   null;
        if(select == -1){
            theUS.setStatus(("กรุณาเลือกรายการ"),UpdateStatus.WARNING);
            return false;
        }
        if(theVisitPayment == null){
            theUS.setStatus(("ไม่มีรายการที่ต้องย้าย"),UpdateStatus.WARNING);
            return false;
        }
        if(select == 0){
            theUS.setStatus(("รายการที่เลือกเป็นรายการแรกจึงไม่มีรายการที่ต้องย้าย"),UpdateStatus.WARNING);
            return false;
        }
        Payment p = (Payment)theVisitPayment.get(select);
        if(p == null){
            theUS.setStatus(("กรุณาเลือกรายการ"),UpdateStatus.WARNING);
            return false;
        }
        if(p.visit_payment_active.equals("0")){
            theUS.setStatus(("สิทธิที่ถูกยกเลิกแล้วไม่สามารถย้ายได้"),UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try{
            theLookupControl.intReadDateTime();
            //change current payment
            //เอาตัวถัดไปมาเปลี่ยน priority เดิมโดยการลดค่าลง
            p.priority = String.valueOf(select-1);
            p.visit_payment_update_date_time = theHO.date_time;
            p.visit_payment_staff_update = this.theHO.theEmployee.getObjectId();
            
            theHosDB.thePaymentDB.update(p);
            
            theVisitPayment.remove(select);
            theVisitPayment.add(select - 1,p);
            //เอาตัวถัดไปมาเปลี่ยน priority เดิมโดยการเพิ่มค่าขึ้น
            //change next payment
            p = (Payment)theVisitPayment.get(select);
            p.priority = String.valueOf(select);
            p.visit_payment_update_date_time = theHO.date_time;
            p.visit_payment_staff_update = this.theHO.theEmployee.getObjectId();
            theHosDB.thePaymentDB.update(p);
            theHS.theVPaymentSubject.notifyUpVPaymentPriority(
                    Constant.getTextBundle("การเปลี่ยนแปลงสิทธิการรักษาของผู้ป่วยเสร็จสิ้น")
                    ,UpdateStatus.COMPLETE);
            if(p != null)
                objectid = p.getObjectId();
            theSystemControl.setStatus(UseCase.TH_upVPaymentPriority,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_upVPaymentPriority,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_upVPaymentPriority,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_upVPaymentPriority,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }
    }
/*   set priority ใหม มีการจีดเรียงตามลำดับ
    //vector ตัวที่ 1 มี priority = n
    //vector ตัวที่ n มี priority = 1
    //เมื่อมีการลบตัวใดตัวที่อยู่ต่ำกว่าก็จะเพิ่มค่าตามลำดับ
    //vector มีเหลือแค่ 1 ตัว มี priority = 0
 */
    /**
     * hosv4
     * @param theVisitPayment 
     * @param row 
     * @param billing 
     * @return 
     */
    public boolean deleteVPayment(Vector theVisitPayment,int[] row,Vector billing)
    {
        Constant.println("public void deleteVPayment(Vector theVisitPayment,int row,Vector billing)");
        Constant.println(UseCase.UCID_deleteVPayment);
        String objectid =   null;
        if(row.length==0){ 
            theUS.setStatus(("กรุณาเลือกสิทธิที่ต้องการแก้ไข"),UpdateStatus.WARNING);
            return false;
        }
        if(theVisitPayment==null){
            theUS.setStatus(("ไม่พบข้อมูลสิทธิการรักษา"),UpdateStatus.WARNING);
            return false;
        }
        if(theVisitPayment.size()==1){
            theUS.setStatus(("ไม่สามารถลบสิทธิการรักษาได้จะต้องเพิ่มสิทธิก่อน แล้วถึงจะลบสิทธิที่ไม่ต้องการได้"),UpdateStatus.WARNING);
            return false;
        }
        if(theVisitPayment.size()==row.length){
            theUS.setStatus(("ไม่สามารถลบสิทธิการรักษาได้"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit!=null && theHO.theVisit.is_discharge_money.equals("1")){
             theUS.setStatus(("ผู้ป่วยจำหน่ายทางการเงินแล้วไม่สามารถแก้ไขสิทธิได้"),UpdateStatus.WARNING);
             return false;
        }
        if(theHO.theVisit!=null && billing != null && billing.size()>0) {
            theUS.setStatus(("กรุณาลบรายการใบเสร็จก่อน การแก้ไขสิทธิผู้ป่วยต้องยังไม่มีรายการคิดเงิน"),UpdateStatus.WARNING);
            return false;
        }
        Payment payment= null;
        Payment paymentcancel = null;
        //ตรวจสอบกรณีที่ Vector นั้นได้เก็บ ข้อมูลของสิทธิที่ยกเลิกมาด้วย
        //ต้องกรองเอาเฉพาะที่ใช้งานเท่านั้น
        Vector vVisitPaymentCancel = new Vector();
        for(int i=theVisitPayment.size() -1 ; i>=0; i--) {
            payment = (Payment)theVisitPayment.get(i);
            //ตรวจสอบว่ารายการนั้น เป็น Active หรือไม่ ถ้าไม่ใช้ ให้เอาออก
            if(!com.hospital_os.utility.Gutil.isSelected(payment.visit_payment_active))
            {
                theVisitPayment.remove(i);
                vVisitPaymentCancel.add(payment);
            }
        }
        //ตรวจสอบก่อนว่า สิทธินั้นเป็นสิทธิต้องห้ามหรือเปล่า
        //ถ้าไม่ใช้ ให้ลบออกจากVector เลย
        theConnectionInf.open();
        try{
            for(int j=row.length-1;j>=0;j--)
            {
                payment = (Payment)theVisitPayment.get(row[j]);
                 if(payment == null) {
                    theUS.setStatus(("เกิดความผิดพลาดในการเลือกสิทธิเพื่อการแก้ไข"),UpdateStatus.WARNING);
                    return false;
                }
                int old_priority=0;
                try{ 
                    old_priority = Integer.parseInt(payment.priority);
                }catch(Exception e){
                    Constant.println(e.getMessage());
                }
                theLookupControl.intReadDateTime();
                payment.visit_payment_active = Active.isDisable();
                payment.visit_payment_staff_cancel = this.theHO.theEmployee.getObjectId();
                payment.visit_payment_cancel_date_time = theHO.date_time;
                paymentcancel = payment;
                theHosDB.thePaymentDB.update(payment);
                theVisitPayment.remove(row[j]);
            }
            for(int i=0,size=theVisitPayment.size();i<size;i++) 
            {
                payment = (Payment)theVisitPayment.get(i);
                payment.priority = String.valueOf(i);
                theHosDB.thePaymentDB.update(payment);
            }
            theHO.vVisitPayment = theHosDB.thePaymentDB.selectByVisitId(theHO.theVisit.getObjectId());
            theHS.theVPaymentSubject.notifyDeleteVPayment(Constant.getTextBundle("การลบรายการสิทธิการรักษาาของผู้ป่วยเสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(theHO.theVisit != null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deleteVPayment,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteVPayment,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex){ 
            theSystemControl.setStatus(UseCase.TH_deleteVPayment,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deleteVPayment,objectid,ex,UpdateStatus.ERROR);
            return true;
        }
        finally{
            theConnectionInf.close();
            payment = null;
        }
    }

    /**
     *hosv4
     * ใช้ในการแปลงข้อมูลของสิทธิการรักษาให้เป็น tooltiptext
     * @param payment เป็น Object ของ Payment
     * @param plan เป็น Object ของ Plan 
     * @return เป็น Object ของ ComboFix
     * @author padungrat(tong)
     * @date 04/04/2549,13:29
     */
    public com.hospital_os.utility.ComboFix getTextVisitPayment(Payment payment,Plan plan)
    {
        com.hospital_os.utility.ComboFix combofix = new com.hospital_os.utility.ComboFix();
        combofix.code = Constant.getTextBundle("ไม่ระบุ");
        
        if(plan != null)
        {   combofix.code = plan.description;
            if(!com.hospital_os.utility.Gutil.isSelected(payment.visit_payment_active))
            {
                combofix.code =Constant.getTextBundle("ถูกยกเลิกสิทธิ") + " - " +  combofix.code ;
            }
        }
        String record ="";
        
        record = record + combofix.code;
        if(payment.visit_payment_staff_record.trim().length() != 0)
        {   record = record + "<BR>";
            record = record + Constant.getTextBundle("ผู้บันทึก") + "  : " + theLookupControl.readEmployeeNameById(payment.visit_payment_staff_record) + ", " + Constant.getTextBundle("เวลาที่บันทึก") + " : " + DateUtil.getDateToString(DateUtil.getDateFromText(payment.visit_payment_record_date_time),true); ;
        }
        if(payment.visit_payment_staff_update.trim().length() != 0)
        {   record = record + "<BR>";
            record = record + Constant.getTextBundle("ผู้แก้ไข") + "   : " + theLookupControl.readEmployeeNameById(payment.visit_payment_staff_update) + ", " + Constant.getTextBundle("เวลาที่แก้ไข") + " : " + DateUtil.getDateToString(DateUtil.getDateFromText(payment.visit_payment_update_date_time),true); ;
        }
        if(payment.visit_payment_staff_cancel.trim().length() != 0)
        {   record = record + "<BR>";
            record = record + Constant.getTextBundle("ผู้ยกเลิก") + ": " + theLookupControl.readEmployeeNameById(payment.visit_payment_staff_cancel) + ", " + Constant.getTextBundle("เวลาที่ยกเลิก") + " : " + DateUtil.getDateToString(DateUtil.getDateFromText(payment.visit_payment_cancel_date_time),true); ;
        }
        
        
        combofix.name = record;
        
        record = null;
        return combofix;
        
        
        
    }
    /**
     * ใช้ในการหาชื่อสกุลของผู้ใช้งานจาก key id
     * @param emid เป็น id ของ ตาราง employee
     * @return เป็น String ที่มีชื่อ สกุลของผู้ใช้ ถ้าหาไม่เจอจะ return เป็น ""
     * @author padungrat(tong)
     * @date 4/4/49,13:27
     */
    public String getEmployeeNameByID(String emid)
    {   com.hospital_os.utility.ComboFix combofixemployee =null;//  theLookupControl.readEmployeeById(emid);
        if(vEmployee == null)
        {
           vEmployee = getEmployee();
        }    
        String name = "";
        
        if(vEmployee != null)
        {
            for(int i = 0,size=vEmployee.size(); i < size;i++)
            {
                combofixemployee = (com.hospital_os.utility.ComboFix)vEmployee.get(i);
                if(emid.equalsIgnoreCase(combofixemployee.code))
                {
                    name = combofixemployee.name;
                }
                combofixemployee = null;
            }
        }
        
        combofixemployee = null;
        return name;
    }
    /** 
     * ใช้ในการหาชื่อผู้ใช้งาน
     * @return เป็น Vector 
     * @author padungrat(tong)
     * @date 04/04/2549,17:26
     * @deprecated henbe unused
     */
    public Vector getEmployee()
    {   
        return theLookupControl.listEmployee();
    }
    
    /**
     * hosv4
     * ใช้ในการหารายการสิทธิที่ถูกยกเลิกตาม visit
     * @param visit_id เป็น รหัสหลักของตาราง t_visit
     * @return เป็น Vector ของ Object Payment
     *@not deprecated henbe unused
     */
    public Vector listVisitPaymentCancel(String visit_id)
    {
        Constant.println(UseCase.UCID_showVisitPaymentCancel);
        String objectid = visit_id;
        theConnectionInf.open();
        try
        {
           theSystemControl.setStatus(UseCase.TH_showVisitPaymentCancel,UpdateStatus.COMPLETE,null);
           theSystemControl.saveLog(UseCase.UCID_showVisitPaymentCancel,objectid,null,UpdateStatus.COMPLETE);
           return this.theHosDB.thePaymentDB.selectVisitPaymentCancelByVisitID(visit_id);
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_showVisitPaymentCancel,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_showVisitPaymentCancel,objectid,ex,UpdateStatus.ERROR);
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    
    /** Creates a new instance of editPatientpaymentReq  */
    public void observVisit(Visit p,String status)
    {
        Constant.println("public String observVisit(Visit p)");
        if(status.equals("1"))
            Constant.println(UseCase.UCID_observVisit);
        if(status.equals("0"))
            Constant.println(UseCase.UCID_cancelObservVisit);
        String objectid =   null;
        if(theHO.theVisit == null) {
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return ;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return ;
        }
        if(theHO.theVisit.visit_type.equalsIgnoreCase(VisitType.IPD)){
            theUS.setStatus(("ผู้ป่วย Admit เป็นผู้ป่วยในแล้วไม่สามารถฝากนอนได้"),UpdateStatus.WARNING);
            return;
        }        
        theConnectionInf.open();
        try{
            p.observe = status;
            p.observe_user = theHO.theEmployee.getObjectId();//.employee_id;
            theHosDB.theVisitDB.update(p);
            if(status.equals(Active.isEnable()))
                theHS.theVisitSubject.notifyObservVisit(Constant.getTextBundle("บันทึกการฝากนอนของผู้ป่วยเสร็จสิ้น"),UpdateStatus.COMPLETE);
            else
                theHS.theVisitSubject.notifyObservVisit(Constant.getTextBundle("ยกเลิกการฝากนอนของผู้ป่วยเสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(p != null)
                objectid = p.getObjectId();
            if(status.equals("1"))
            {
                theSystemControl.setStatus(UseCase.TH_observVisit,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_observVisit,objectid,null,UpdateStatus.COMPLETE);
            }
            if(status.equals("0"))
            {
                theSystemControl.setStatus(UseCase.TH_cancelObservVisit,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_cancelObservVisit,objectid,null,UpdateStatus.COMPLETE);
            }
        }
        catch(Exception ex)
        {
            if(status.equals("1"))
            {
                theSystemControl.setStatus(UseCase.TH_observVisit,UpdateStatus.ERROR,ex);
                theSystemControl.saveLog(UseCase.UCID_observVisit,objectid,ex,UpdateStatus.ERROR);
            }
            if(status.equals("0"))
            {
                theSystemControl.setStatus(UseCase.TH_cancelObservVisit,UpdateStatus.ERROR,ex);
                theSystemControl.saveLog(UseCase.UCID_cancelObservVisit,objectid,ex,UpdateStatus.ERROR);
            }
        }
        theConnectionInf.close();
    }
 
/**
 * hosv4
 * จำหน่ายทางการเงิน
 //นับจำนวน รายการตรวจรักษาที่ยังไม่คิดเงิน
            // 0 มี 2 ความหมาย คือ 1 มีรายการ order แต่คิดเงินแล้ว 2 ไม่มีรายการ order แล้ว
            //1 มีรายการ order แต่คิดเงินแล้ว
            //ตรวจสอบว่า รายการนั้น ได้คำนวณค่าใช้จ่ายรวมหรือยัง
    * //// with notify
 *
 **/
    public void dischargeFinancial() {
        Constant.println(UseCase.UCID_dischargeFinancial);
        String objectid =   null;
        if(theHO.theVisit != null)
            objectid = theHO.theVisit.getObjectId();
        try {
            ///////////////////////////////////////////////////////////////////
            theConnectionInf.open();
            objectid = theHO.theVisit.getObjectId();
            boolean ret = intDischargeFinancial();
            if (!ret) {
                return;
            }
            ///////////////////////////////////////////////////////////////////
            theSystemControl.setStatus(UseCase.TH_dischargeFinancial,UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_dischargeFinancial,objectid, null, UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_dischargeFinancial,UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_dischargeFinancial, objectid, ex,UpdateStatus.ERROR);
        } finally {
            theConnectionInf.close();
        }
    }
    public boolean intDischargeFinancial()throws Exception 
    {
            Visit v = theHO.theVisit;
            if(v == null) {
                theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
                return false;
            }
            String date_time = theLookupControl.intReadDateTime();
            //ตรวจสอบรายการ Order ที่ยังไม่เรียบร้อยก็ให้ตรวจสอบและแจ้งให้ผู้ใช้ทราบ
            ///////////////////////////////////////////////////////////////////////////////////////
            Vector vorder = theHosDB.theOrderItemDB.selectByVisitId(v.getObjectId());
            int checkorder = 0;
            for(int i=0;i<vorder.size();i++){
                OrderItem oi = (OrderItem)vorder.get(i);
                if(!oi.status.equals(OrderStatus.DIS_CONTINUE) && !oi.isChargeComplete())
                    checkorder++;
            }
            //มีรายการที่ยังไม่ได้คิดเงินมากกว่า 1 รายการ
            if(checkorder>0){
                theUS.setStatus(Constant.getTextBundle("ยังไม่ได้คิดเงิน") + " "+ checkorder + " " +
                        Constant.getTextBundle("รายการ") +
                        Constant.getTextBundle("ไม่สามารถจำหน่ายทางการเงินได้"),UpdateStatus.WARNING);
                return false;
            }
            ///////////////////////////////////////////////////////////////////////////////////////
            //ไม่มีรายการที่ยังไม่ได้คิดเงิน และก็ตรวจสอบว่ารายการใบแจ้งหนี้ที่ยังไม่ได้รับชำระมีหรือไม่หากมีก็ต้องคำนวนค่าใช้จ่ายเสียก่อน
            Vector vbinv = theHosDB.theBillingInvoiceDB.selectByVisitId(v.getObjectId());
            int corder = 0;
            for(int i=0;i<vbinv.size();i++){
                BillingInvoice binv = (BillingInvoice)vbinv.get(i);
                if(binv.isActive() && binv.isBillingComplete())
                    checkorder++;
            }
            if(corder != 0) {
                theUS.setStatus(("ยังไม่ได้คำนวณค่าใช้จ่ายรวม จะไม่สามารถจำหน่ายทางการเงินได้"),UpdateStatus.WARNING);
                return false;
            }
            ///////////////////////////////////////////////////////////////////////////////////////
            if(v.financial_discharge_time == null
            || v.financial_discharge_time.equalsIgnoreCase("")
            || v.financial_discharge_time.equalsIgnoreCase("null")){
                v.financial_discharge_time = date_time;
            }
            v.visit_status = VisitStatus.isInStat();
            v.is_discharge_money = "1";
            v.financial_discharge_user = theHO.theEmployee.getObjectId();
            v.locking = "0";
            QueueICD qicd = new QueueICD();
            if(!theLookupControl.readOption().auto_complete_visit.equals("1"))
                intSaveVisitInQueueICD(v,qicd,date_time);
            intSaveQueueDispenseOPD(theHO.vOrderItem, date_time, v);
            if(theHO.theListTransfer!=null)
                theHosDB.theQueueTransferDB.delete(theHO.theListTransfer);
            intSaveTransferThrow(theHO.theEmployee, date_time);
            //กรณีจำหน่ายทางการแพทย์ auto------------------------------------------------
            //henbe comment281006 intSaveQueueDispenseOPD(theHO.vOrderItem,date_time,v);
            this.intAutoCompleteVisit(v);

            if(v.visit_financial_record_date_time != null && !v.visit_financial_record_date_time.equals(""))
                v.visit_financial_record_staff = theHO.theEmployee.getObjectId();

            theHosDB.theVisitDB.update(v);
            theHO.clearFamily();
            return true;
    }
    /*
     *เป็นการ ส่งเข้าคิวเวชสถิติ
     //usage
    */
    private void intSaveVisitInQueueICD(Visit visit,QueueICD qicd,String date_time) throws Exception
    {
            QueueICD theQueueICD = qicd;
            theQueueICD.patient_id = visit.patient_id;
            theQueueICD.visit_id = visit.getObjectId();
            if(visit.financial_discharge_time == null
            || visit.financial_discharge_time.equalsIgnoreCase(""))
            {  
                theQueueICD.assign_time = date_time;
            }
            else 
            {
                theQueueICD.assign_time = visit.financial_discharge_time;
            }
            theQueueICD.last_service = theHO.theServicePoint.getObjectId();
            theHosDB.theQueueICDDB.insert(theQueueICD);
    }

    /**
     * ส่งผู้ป่วยกลับ วอร์ด
     */
    public void sendVisitBackWard(Visit visit)
    {
        Constant.println("public void sendVisitBackWard(Visit visit)");
        Constant.println(UseCase.UCID_sendVisitBackWard);
        String objectid =   null;
        if(theHO.theVisit == null) {
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return ;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);

            return ;
        }
        if(!theHO.theVisit.visit_type.equals(VisitType.IPD))
        {   
            theUS.setStatus(("ผู้ป่วยที่เลือกไม่ได้เป็นผู้ป่วยในไม่สามารถส่งกลับวอร์ดได้"),UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            //amp:20/02/2549
            Transfer transfer = (Transfer)theHO.vTransfer.get(theHO.vTransfer.size()-1);
            if(!transfer.ward_id.equals("")){
                theHosDB.theQueueTransferDB.deleteByVisitID(visit.getObjectId());
                theUS.setStatus(("ผู้ป่วยอยู่ในวอร์ดแล้วไม่สามารถส่งกลับวอร์ดได้อีก"),UpdateStatus.WARNING);
                return;
            }
            theHosDB.theQueueTransferDB.deleteByVisitID(visit.getObjectId());
            //theHosDB.theTransferDB.updateFinishTimeVisit(visit.getObjectId(),date_time);//amp:20/02/2549
            intSaveTransferThrow(theHO.theEmployee,date_time);
            intSaveTransaction(theHO.theServicePoint,date_time,visit.ward);
            
            intUnlockVisit(visit);
            theHO.clearFamily();
            theHS.theVisitSubject.notifySendVisitBackWard("ส่งผู้ป่วยกลับ วอร์ด เสร็จสิ้น",UpdateStatus.COMPLETE);
            if(visit != null)
                objectid = visit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_sendVisitBackWard,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_sendVisitBackWard,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_sendVisitBackWard,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_sendVisitBackWard,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
 
    public Vector searchAccidentGroupByDate(String startDate,String finishDate)
    {
        theConnectionInf.open();
        vObject = null;
        try
        {
            vObject = theHosDB.theAccidentGroupDB.selectByAccidentDate(startDate,finishDate);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return vObject;
    }
    
    /**
     * @author Padungrat(tong)
     */
    public int saveAccidentGroup(Accident accidentgroup,UpdateStatus theUS)
    {
        
        if(accidentgroup == null)
        {
            theUS.setStatus("กรุณาเลือกรายการอุบัติเหตุหมู่",UpdateStatus.WARNING);
            return 0;
        }
        if(accidentgroup.date_accident.equals("")){
            theUS.setStatus("กรุณาระบุวันที่เกิดอุบัติเหตุ",UpdateStatus.WARNING);
            return 0;
        }
        int result_loc = 0;
        theConnectionInf.open();
        try{
            theLookupControl.intReadDateTime();
            if(accidentgroup.getObjectId() == null)
            {
             /*   if(theHosDB.theAccidentGroupDB.selectByVN(accident.vn)!=null){
                     theUS.setStatus("ระบบไม่สามารถบันทึกข้อมูลอุบัติเหตุมากกว่า 1 ครั้งในการรับบริการครั้งเดียวได้"
                     ,UpdateStatus.WARNING);
                     return 0;
                }
              */
                accidentgroup.reporter = theHO.theEmployee.getObjectId();
                accidentgroup.record_date_time = theHO.date_time;
                result_loc = theHosDB.theAccidentGroupDB.insert(accidentgroup);
            }
            else { 
                accidentgroup.staff_update = theHO.theEmployee.getObjectId();
                accidentgroup.update_date_time  = theHO.date_time;

                //เวลาที่ update
                result_loc = theHosDB.theAccidentGroupDB.update(accidentgroup);
            }
            
        }
        catch(Exception ex)
        {    ex.printStackTrace(Constant.getPrintStream());
            
        }
        finally{
            theConnectionInf.close();
        }
        return result_loc;
    }
  
    
 
     /**
     *@author Padungrat(tong)
     */
    public boolean checkValueChangeAccidentType(String valueChange,Accident accident,UpdateStatus theUS)
    {   boolean result = true;
        if(accident != null && accident.getObjectId() != null)
        {
            if(!valueChange.equals(accident.b_setup_accident_type_id))
            {
               Visit visit = readVisitByVidRet(accident.vn_id);
               if(visit == null)
                {
                    theUS.setStatus("เกิดความผิดพลาดของข้อมูล visit", theUS.WARNING);
                    result = false;
                }
                //ตรวจสอบว่าถูกจำหน่ายทางการแพทย์หรือยัง
                if(visit.is_discharge_doctor.equals(Active.isEnable()))
                {
                    theUS.setStatus("ผู้ป่วยถูกจำหน่ายทางการแพทย์แล้วไม่สามารถแก้ไขข้อมูลนี้ได้", theUS.WARNING);
                    visit = null;
                    result = false;
                }
            }
        }
        
        return result;
    }
    /**
     *@author Padungrat(tong)
     */
    private AccidentType getAccidentTypeByID(String accident_type_id)
    {     
            Vector vc = this.theLookupControl.listAccidentType();
            AccidentType tempAccidentType = null;
            if(vc != null)
            {   
                for(int i=0,size=vc.size();i < size;i++)
                {
                    tempAccidentType = (AccidentType)vc.get(i);
                    if(accident_type_id.equals(tempAccidentType.getObjectId()))
                    {
                        break;
                    }
                    tempAccidentType = null;
                }
            }
            vc = null;
            return tempAccidentType;
    }
     /**
     *@author Padungrat(tong)
     */
    private void saveMapDx(Accident accident)
    {
        
        if(accident == null)
        {
            return ;
        }
        try
        {   Vector vc = this.theLookupControl.listAccidentType();
            if(vc != null)
            {   AccidentType tempAccidentType = null,accidenttype1= null;;
                boolean result = true;
                tempAccidentType = getAccidentTypeByID(accident.b_setup_accident_type_id);
                if(tempAccidentType !=null)
                {   
                    MapVisitDx mvd =  new MapVisitDx();
                    if(accident.getObjectId() != null)
                    {    
                        Accident tempacident = theHosDB.theAccidentDB.selectByPK(accident.getObjectId());
                        result = false;
                        if(!tempacident.b_setup_accident_type_id.equals(accident.b_setup_accident_type_id))
                        {   accidenttype1 = getAccidentTypeByID(tempacident.b_setup_accident_type_id);
                            String date_time = theLookupControl.intReadDateTime();
                            theHosDB.theMapVisitDxDB.updateActive(
                                    accident.vn_id,"","0",theHO.theEmployee.getObjectId()
                                    ,date_time,accidenttype1.accident_type_trauma);  
                            accidenttype1 = null;
                            result = true;
                        }
                        tempacident = null;
                    }
                    theConnectionInf.open();
                    if(result)
                    {
                        mvd.visit_id = theHO.theVisit.getObjectId();// + dxt.getObjectId();
                        mvd.dx_template_id = "";
                        mvd.t_patient_id = theHO.thePatient.getObjectId();
                        mvd.visit_diag_map_active = Active.isEnable();
                        mvd.visit_diag_map_date_time = theLookupControl.intReadDateTime();
                        mvd.visit_diag_map_dx = tempAccidentType.accident_type_description;//dxt.description;
                        mvd.visit_diag_map_icd = tempAccidentType.accident_type_trauma;//dxt.icd_code;
                        mvd.visit_diag_map_staff = theHO.theEmployee.getObjectId();
                    
                        theHosDB.theMapVisitDxDB.insert(mvd);
                    
                    }
                    mvd = null;
                }
            }
            vc = null;
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    /**
     * 
     * @param theVisit 
     * @return 
     */
    public boolean dischargeIPD(Visit theVisit)
    {
        Constant.println(UseCase.UCID_dischargeIPD);
        String objectid =   null;
        if(theVisit != null)
            objectid = theVisit.getObjectId();
        theConnectionInf.open();
        try{

        if(theHO.theVisit == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้",UpdateStatus.WARNING);
            return false;
        }
            if(!theHO.theVisit.visit_type.equals(VisitType.IPD)){
                theUS.setStatus("กรุณาเลือกผู้ป่วยใน",UpdateStatus.WARNING); 
                return false;
            }
            if(theHO.theVisit.doctor_discharge_time.equals("")){
                theUS.setStatus("กรุณาระบุวันเวลาที่จำหน่าย",UpdateStatus.WARNING); 
                return false;
            }
            if(DateUtil.countDateDiff(theHO.theVisit.doctor_discharge_time
                    ,theHO.theVisit.begin_admit_time)<0){
                theUS.setStatus("กรุณาระบุวันจำหน่ายหลังจากวัน ADMIT",UpdateStatus.WARNING); 
                return false;
            }
                
            theHO.theVisit.is_discharge_ipd = "1";
            //theHO.theVisit.doctor_discharge_time = date_time;
            theHO.theVisit.doctor_discharge_user = theHO.theEmployee.getObjectId();
            theHosDB.theVisitDB.updateDischargeDoctor(theHO.theVisit);
            theHS.theVisitSubject.notifyDischargeDoctor("การจำหน่ายผู้ป่วยในเสร็จสิ้น",UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_dischargeIPD,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_dischargeIPD,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_dischargeIPD,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_dischargeIPD,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }
    }
/*
 *
 *จำหน่ายทางการแพทย์ของผู้ป่วย 
 * รับ Visit อันเดียวกับ theHO.theVisit
 * Death รับมาจาก dialog การตายที่ผู้ใช้กรอกเป็นค่า null ได้
 */
    /**
     * 
     * @param visit 
     * @param dt 
     *@deprecated henbe unused
     */
    public void dischargeDoctor(Visit visit,Death dt)
    {
        dischargeDoctor(visit,dt,false);
    }
    /**
     * 
     * @param visit 
     * @param dt 
     * @param unlock 
     *@deprecated henbe unused
     */
    public void dischargeDoctor(Visit visit,Death dt,boolean unlock)
    {
        ////เกี่ยวกับการตาย//////////////////////////////////////////////////////
        theConnectionInf.open();
        try{
            boolean ret = intDischargeDoctor(visit,dt,unlock);
            if(!ret)
                return;
            theHS.theVisitSubject.notifyDischargeDoctor("การจำหน่ายทางการแพทย์เสร็จสิ้น",UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus("การจำหน่ายทางการแพทย์ผิดพลาด",UpdateStatus.ERROR); 
        }
        finally{
            theConnectionInf.close();
        }
        
    }
    /**
     *
     * @param visit
     * @param dt
     * @param unlock
     *@deprecated henbe unused
     */
    public boolean intDischargeDoctor(Visit visit,Death dt,boolean unlock)throws Exception
    {

        if(theHO.theVisit == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return false;
        }
//        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
//        {
//            theUS.setStatus("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้",UpdateStatus.WARNING);
//            return false;
//        }
        if(!theHO.theEmployee.authentication_id.equals(Authentication.STAT)
        && theHO.theVisit.is_discharge_doctor.equals(Active.isEnable())){
            theUS.setStatus("ผู้ป่วยจำหน่ายทางการแพทย์แล้วไม่สามารถจำหน่ายอีกได้",UpdateStatus.WARNING); 
            return false;
        }
        if(theHO.theVisit.doctor_discharge_time.equals("")
        ||theHO.theVisit.doctor_discharge_time.equals(",")){
            theUS.setStatus("กรุณาระบุวันเวลาที่จำหน่าย",UpdateStatus.WARNING); 
            return false;
        }
        if(!theHO.theVisit.begin_visit_time.equals("") && 
           !theHO.theVisit.doctor_discharge_time.equals("") && 
           DateUtil.countDateDiff(theHO.theVisit.doctor_discharge_time
                ,theHO.theVisit.begin_visit_time)<0){
            theUS.setStatus("กรุณาระบุวันจำหน่ายหลังจากวันเข้ารับบริการ",UpdateStatus.WARNING); 
            return false;
        }
        //ถ้าเวชยังไม่ลงรหัสโรคห้ามจำหน่าย
        if(theHO.vDiagIcd10 == null || theHO.vDiagIcd10.isEmpty() ){
            if(theHO.theEmployee.authentication_id.equals(Authentication.STAT)){
                theUS.setStatus("กรุณาบันทึกรหัส ICD10 ก่อนจำหน่ายทางการแพทย์",UpdateStatus.WARNING);
                return false;
            }
            if(!theUS.confirmBox("ยืนยันการจำหน่ายทางการแพทย์โดยไม่ลงรหัสโรค",UpdateStatus.WARNING))
                return false;
        }
        
        boolean is_death1 = HosObject.isVisitDeath(visit);
//        if(theHO.thePatient.dischar.equals("1") && is_death1)
//        {
//            theUS.setStatus("ผู้ป่วยเสียชีวิตแล้วไม่สามารถจำหน่ายตายได้อีก",UpdateStatus.WARNING);
//            return false;
//        }
        //หล่มสักบอกว่าการจำหน่ายทางการแพทย์ไม่จำเป็นต้องดูข้อมูล refer เพราะคนไข้อาจกลับมารักษาที่รพ อีก
            Refer refer = theHosDB.theReferDB.selectByVisitIdType(visit.getObjectId(),Refer.REFER_OUT);
//            if(refer!=null && !HosObject.isVisitRefer(visit)){
//                theUS.setStatus("กรุณายกเลิกข้อมูลการส่งต่อก่อนจำหน่ายคนไข้ เป็นอย่างอื่น",UpdateStatus.WARNING); 
//                return false;
//            }
            dt = theHosDB.theDeathDB.selectByPatientId(theHO.thePatient.getObjectId());
            if(dt!=null && !HosObject.isVisitDeath(visit)){
                theUS.setStatus("กรุณายกเลิกข้อมูลการตายก่อนจำหน่ายคนไข้ เป็นอย่างอื่น",UpdateStatus.WARNING); 
                return false;
            }    
            if(theLookupControl.readOption().discharge.equals(Active.isEnable())) {
                boolean retb = DialogPasswd.showDialog(theHO,theUS,theHO.theEmployee.password);
                if(!retb) {
                    theUS.setStatus("รหัสผ่านไม่ถูกต้อง",UpdateStatus.WARNING);
                    return false;
                }
            }
            String date_time = theLookupControl.intReadDateTime();
            String is_death = (is_death1)?"1":"0";
            if(is_death1 && dt==null)
                dt = theHO.initDeath(visit.doctor_discharge_time);

            if(refer==null && HosObject.isVisitRefer(visit)){
                refer = theHO.initReferOut(new Vector());
                refer.office_refer = visit.refer_out;
                boolean ret = intSaveRefer(refer,visit,theUS); 
                if(!ret)
                    return false;
            }
            ///////////////////////////////////////////////////////////////////////
            visit = theHO.theVisit;
            visit.is_discharge_doctor = "1";
            visit.doctor_discharge_user = theHO.theEmployee.getObjectId();        
            if(visit.doctor_discharge_time.equals(""))
                visit.doctor_discharge_time = date_time;

            Patient patient = theHO.thePatient;
            patient.dischar = is_death;
            patient.ddisch = visit.doctor_discharge_time;
            theHosDB.thePatientDB.updatePatientDischar(patient);
            /////////////////////////////////////////////////
            theHO.theFamily.discharge_status_id = is_death;
            theHosDB.theFamilyDB.updateDischarge(theHO.theFamily);
            /////////////////////////////////////////////
            if(dt!=null && dt.getObjectId()==null){
                if(is_death.equals(Active.isEnable())){
                    dt.active = "1";
                    dt.user_record = theHO.theEmployee.getObjectId();
                    dt.family_id = theHO.theFamily.getObjectId();
                    theHosDB.theDeathDB.insert(dt);
                }
                else{
                    dt.active = "0";
                    dt.user_record = theHO.theEmployee.getObjectId();
                    theHosDB.theDeathDB.update(dt);
                }
            }
            //หากเจ้าหน้าที่ stat จำหน่ายก็จะลบออกจากระบบเลย หากจำหน่ายทางเงินแล้ว
            if(theHO.theEmployee.authentication_id.equals(Authentication.STAT)){
                theHosDB.theQueueICDDB.deleteByVisitID(visit.getObjectId());
                if(visit.isDischargeMoney()){
                    visit.visit_status = VisitStatus.isOutProcess();
                }
            }
            else
                intAutoCompleteVisit(visit);
            if(visit.isDischargeMoney() && visit.isDischargeDoctor()){
                visit.visit_status = VisitStatus.isOutProcess();
            }
            theHosDB.theVisitDB.updateDischargeDoctor(theHO.theVisit);
            if(unlock) {
                this.intUnlockVisit(visit);
                theHO.clearFamily();
            }
            if(theHO.is_admit && theHO.is_cancel_admit && theLookupControl.readOption().admit.equals(Active.isEnable())){
                theUS.setStatus("\u0E19\u0E32\u0E22\u0E1E\u0E07\u0E28\u0E4C\u0E18\u0E23  \u0E15\u0E31\u0E19\u0E18\u0E19\u0E01\u0E34\u0E08  \u0E42\u0E1B\u0E23\u0E41\u0E01\u0E23\u0E21\u0E40\u0E21\u0E2D\u0E23\u0E4C",UpdateStatus.ERROR);
                theHO.is_admit = false;
                theHO.is_cancel_admit = false;
            }
            return true;
    }
    
    /*ดูประวัติการรับบริการของผู้ป่วย*/
    public Vector listHistoryVisit(String vn)
    {
        Constant.println("public Vector listHistoryVisit(String vn)");
         Vector result_loc=null;
        theConnectionInf.open();
        try
        {
            result_loc = theHosDB.theTransferDB.selectByVisitId(vn);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return result_loc;
    }
    
    
    public Vector listAccident(String dateFrom, String dateTo, String hn)
    {
        theConnectionInf.open();
        try{
            String sql = "select *,patient_firstname ||'  '|| patient_lastname as patient_name from t_accident "
                +"left join t_patient on t_patient.t_patient_id = t_accident.t_patient_id"
                + " where accident_active = '1' ";
            if(!dateFrom.equals(""))
                sql += " and accident_date >= '" + dateFrom + "' " +
                    " and accident_date <= '" + dateTo + "'";
            if(!hn.equals(""))
                sql += " and t_patient.patient_hn like '%" + theLookupControl.getNormalTextHN(hn) + "'";
            sql += " limit 500";
            
            ResultSet rs = theConnectionInf.eQuery(sql);
            Vector list = new Vector();
            while(rs.next()){
                Accident p = theHosDB.theAccidentDB.rs2Object(rs);
                p.mask_patient_name = rs.getString("patient_name");
                //Constant.println("p.mask_patient_name" + p.mask_patient_name);
                list.add(p);
            }
            rs.close();
            return list;
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
  
    public Accident readAccidentByVn(String vn)
    {
        Accident ac=null;
        theConnectionInf.open();
        try
        {
            ac = theHosDB.theAccidentDB.selectByVN(vn);
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return ac;
    }

    /**
     * 
     * @param visit 
     */
    public void reverseDoctor(Visit visit)
    {
        Constant.println(UseCase.UCID_reverseDoctor);
        String objectid =   null;
        if(visit != null)
            objectid = visit.getObjectId();
        if(visit==null){    
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return;
        }
        if(!visit.is_discharge_doctor.equals(Active.isEnable())) {
            theUS.setStatus("ผู้ป่วยยังไม่ได้จำหน่ายทางการแพทย์",UpdateStatus.WARNING);
            return;
        }
//        if(theHO.theDeath!=null && !theHO.theDeath.vn_id.equals(visit.getObjectId())){
//            theUS.setStatus("ผู้ป่วยเสียชีวิตแล้วไม่สามารถย้อนกลับไปแก้ไขข้อมูลใดๆ ได้อีก",UpdateStatus.WARNING);
//            return;
//        }
        boolean retb = DialogPasswd.showDialog(theHO,theUS,theHO.theEmployee.password);
        if(!retb) {
            theUS.setStatus("รหัสผ่านไม่ถูกต้อง",UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            visit.is_discharge_doctor = "0";
            visit.visit_status = VisitStatus.isInProcess();
            theHosDB.theVisitDB.update(visit);
            theHosDB.theQueueICDDB.deleteByVisitID(visit.getObjectId());
            if(visit.is_discharge_money.equals(Active.isEnable())){
                QueueICD theQueueICD = new QueueICD();
                theQueueICD.patient_id = visit.patient_id;
                theQueueICD.visit_id = visit.getObjectId();
                theQueueICD.assign_time = visit.financial_discharge_time;
                theQueueICD.last_service = theHO.theServicePoint.getObjectId();
                theHosDB.theQueueICDDB.insert(theQueueICD);
            }
            if(visit.discharge_opd_status.equals(DischargeOpd.REFER)){
                if(theUS.confirmBox("พบข้อมูลการส่งต่อ Refer ต้องการยกเลิกข้อมูลการส่งต่อหรือไม่"
                        ,UpdateStatus.WARNING)){
                    theHosDB.theReferDB.updateActiveByVidOut("0",visit.getObjectId(),Active.isEnable());
                    visit.refer_out = "";
                }
            }
            ////เกี่ยวกับการตาย//////////////////////////////////////////////////////
            
            if(theHO.thePatient.discharge_status_id.equals("1")
            && theUS.confirmBox("พบข้อมูลการตาย ต้องการยกเลิกข้อมูลการตายหรือไม่",UpdateStatus.WARNING)) {
                theHosDB.thePatientDB.updatePatientDischar(theHO.thePatient);
                /////////////////////////////////////////////////
                theHO.theFamily.discharge_status_id = "0";
                theHosDB.theFamilyDB.updateDischarge(theHO.theFamily);
                /////////////////////////////////////////////
                Death dt = theHO.theDeath;
                if(dt!=null){
                    dt.active = Active.isDisable();
                    theHosDB.theDeathDB.update(dt);
                }
            }
            //////////////////////////////////////////////////////////
            theHS.theVisitSubject.notifyReverseDoctor("ย้อนกลับการจำหน่ายทางการแพทย์เสร็จสิ้น",UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_reverseDoctor,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_reverseDoctor,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_reverseDoctor,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_reverseDoctor,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
   /*ย้อนกลับการจำหน่ายทางการเงิน*/
    /**
     * 
     * @see 
     */
    public void reverseFinancial()
    {
        Constant.println("public void reverseFinancial()");
        Constant.println(UseCase.UCID_reverseFinancial);
        String objectid =   null;
        if(theHO.theVisit != null)
            objectid =  theHO.theVisit.getObjectId();
        Visit visit = theHO.theVisit;
        if(visit == null){   
             theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
             return ;
        }
        if(!visit.is_discharge_money.equals(Active.isEnable())){
             theUS.setStatus("ผู้ป่วยยังไม่ได้จำหน่ายทางการเงิน",UpdateStatus.WARNING);
             return ;
        }
        boolean retb = DialogPasswd.showDialog(theHO,theUS,theHO.theEmployee.password);
        if(!retb) {
            theUS.setStatus("รหัสผ่านไม่ถูกต้อง",UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            visit.is_discharge_money = "0";
            visit.visit_status = VisitStatus.isInProcess();
            theHosDB.theVisitDB.update(visit);
            intEditVisitInDespenseToZero(visit);
            //จะต้องอยู่ก่อนการลบคิวCode                         
            intSaveTransaction(theHO.theServicePoint,date_time, "");                         
            theHosDB.theQueueICDDB.deleteByVisitID(visit.getObjectId()); 
            theHosDB.theQueueDespenseDB.deleteByVisitID(visit.getObjectId()); 

            theHS.theVisitSubject.notifyReverseFinancial("ย้อนกลับการจำหน่ายทางการเงินเสร็จสิ้น"
                ,UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_reverseFinancial,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_reverseFinancial,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_reverseFinancial,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_reverseFinancial,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
  
 /*
  *base function from ใช้ในการบันทึกคิวผู้ป่วยเมื่อเลือกคิวที่รับบริการ
  
    private void intSaveMapQueueVisit(MapQueueVisit mapQueueVisit) throws Exception
    {
        if(!theLookupControl.readOption().inqueuevisit.equals(Active.isEnable()))
            return ;
        if(mapQueueVisit==null){
            throw new Exception("ข้อมูลคิวสำหรับการเข้ารับบริการผิดพลาด");
        }
        theHosDB.theMapQueueVisitDB.save(mapQueueVisit);
        theHO.theMapQueueVisit = mapQueueVisit;
    }
    */
 
    
    /**
     * 
     * @param queueVisitId 
     * @return 
     */
    public int resetQueueVisit(String queueVisitId)
    {
         int result_loc = 0;
        theConnectionInf.open();
        try
        {
            result_loc = theHosDB.theQueueVisitDB.reset(queueVisitId);
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return result_loc;
    }
 
    //ถ้าเป็นผู้ป่วยใน แล้วค้างอยู่ที่จุดบริการนี้เกิน 24 ชั่วโมงโปรแกรมจะทำการ inactive mapQueueVisit 
    private int intRemoveMapVisitInIPD(Visit visit) throws Exception
    {
        if(visit == null && visit.getObjectId() == null)
            return 0;
        if(visit.visit_type.equalsIgnoreCase(VisitType.OPD))
            return 0;
        if((DateUtil.countHour(visit.begin_admit_time,theConnectionInf) < 24))
        {
            return 0;
        }
        else
        {
            // ทำการหาข้อมูลขึ้นมาแสดงของการ Map
            MapQueueVisit mapQueueVisit = theHosDB.theMapQueueVisitDB.selectByVisitID(visit.getObjectId());
            if((mapQueueVisit != null))
            {
                mapQueueVisit.active = Active.isDisable();
                theHosDB.theMapQueueVisitDB.update(mapQueueVisit);
            }
            return 1;
        }
    }
    
    /*ดูสิทธการรักษาประจำการรับบริการครั้งนั้นจาก list*/    
    public Payment listVisitPaymentByKeyID(String payment_id)
    {
        if((((payment_id == null)) || ((payment_id.length() == 0)))) return null;
        Payment p =null;
        theConnectionInf.open();
        try
        {
            p = theHosDB.thePaymentDB.selectByPK(payment_id);
        }
        catch(Exception ex)
        {    ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return p;
    }

    /**
     *hosv4
     */
    public void saveVPayment(Payment thePaymentNow,Vector vVisitPayment)
    {

        if(theHO.theVisit == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return ;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้",UpdateStatus.WARNING);
            return ;
        }
        if(thePaymentNow==null) {
            theUS.setStatus("การรับข้อมูลสิทธิการรักษาจากหน้าจอเกิดความผิดพลาด",UpdateStatus.WARNING);
            return;
        }
        if(thePaymentNow.plan_kid==null || thePaymentNow.plan_kid.equals("")){
            theUS.setStatus("กรุณาเลือกสิทธิการรักษา",UpdateStatus.WARNING);
            return;
        }
        if(thePaymentNow.card_ins_date.length()==10) {
            if(DateUtil.countDateDiff(thePaymentNow.card_ins_date,theConnectionInf)==1) {
                theUS.setStatus("กรุณาระบุวันออกบัตรเป็นวันในอดีต",UpdateStatus.WARNING);
                return;
            }
        }
        Date dateins = DateUtil.getDateFromText(thePaymentNow.card_ins_date);
        Date dateexp = DateUtil.getDateFromText(thePaymentNow.card_exp_date);
        if(dateins != null && dateexp != null)
        {
            int date_valid = DateUtil.countDateDiff(thePaymentNow.card_ins_date,thePaymentNow.card_exp_date);
            if(date_valid>0){
                theUS.setStatus("วันที่ออกบัตรและวันที่หมดอายุมีช่วงที่ไม่ถูกต้อง",UpdateStatus.WARNING);
                return;
            }
        }
        if(thePaymentNow.hosp_main.equals("")){
            theUS.setStatus("กรุณากรอกรหัสสถานพยาบาลหลัก",UpdateStatus.WARNING);
            return;
        }
        //ต้องกรองเอาเฉพาะที่ใช้งานเท่านั้น
        Vector vVisitPaymentCancel = new Vector();
        for(int i=vVisitPayment.size() -1 ; i>=0; i--) {
            Payment p = (Payment)vVisitPayment.get(i);
            //ตรวจสอบว่ารายการนั้น เป็น Active หรือไม่ ถ้าไม่ใช้ ให้เอาออก

            if(!com.hospital_os.utility.Gutil.isSelected(p.visit_payment_active))
            {
                vVisitPayment.remove(i);
                vVisitPaymentCancel.add(p);
            }
        }
        //Payment pp = (Payment)vVisitPayment.get(0);
        //เช้คว่าถ้าสิทธิซ้ำกันจะต้องบันทึกทับลงไปในสิทธิเดิมได้เลย
       // Vector theVisitPayment = vVisitPayment;
        boolean samepayment = false;
        for(int i=0; i<vVisitPayment.size(); i++) {
            Payment p = (Payment)vVisitPayment.get(i);
            if(p.plan_kid.equals(thePaymentNow.plan_kid)) {
                if(thePaymentNow.getObjectId()==null) {
                    samepayment = true;
                    theUS.setStatus("เพิ่มสิทธิการรักษาซ้ำไม่ได้",UpdateStatus.WARNING);
                    return;
                }
            }
        }
        //ตรวจสอบว่ายังมีรายการที่ถูกยกเลิกอยู่หรือไม่ถ้ามีให้เอามารวมกับของเดิมด้วย
        if(samepayment){
            if(vVisitPaymentCancel.size() >0){
                 for(int i=0; i<vVisitPaymentCancel.size(); i++) {
                    Payment p = (Payment)vVisitPaymentCancel.get(i);
                    vVisitPayment.add(p);
                }   
            }
            return;
        }
        theConnectionInf.open();
        try{
            theLookupControl.intReadDateTime();
            thePaymentNow.visit_id = theHO.theVisit.getObjectId();
            thePaymentNow.priority = "0";
            if(vVisitPayment!=null && !vVisitPayment.isEmpty())
                thePaymentNow.priority = String.valueOf(vVisitPayment.size());
           

            if(thePaymentNow.getObjectId()== null)
            {
                thePaymentNow.visit_payment_staff_record = this.theHO.theEmployee.getObjectId();
                thePaymentNow.visit_payment_record_date_time = theHO.date_time;
            }
            else
            {
                thePaymentNow.visit_payment_staff_update = this.theHO.theEmployee.getObjectId();
                thePaymentNow.visit_payment_update_date_time = theHO.date_time;
            }
            for(int i=0; i<vVisitPayment.size(); i++) {
                Payment p = (Payment)vVisitPayment.get(i);
                p.priority = String.valueOf(i);
                theHosDB.thePaymentDB.update(p);
            }
            if(thePaymentNow.getObjectId()==null){
                thePaymentNow.priority = String.valueOf(vVisitPayment.size());
                thePaymentNow.visit_id = theHO.theVisit.getObjectId();
                theHosDB.thePaymentDB.insert(thePaymentNow);
                vVisitPayment.add(thePaymentNow);
            }
            //ตรวจสอบว่ายังมีรายการที่ถูกยกเลิกอยู่หรือไม่ถ้ามีให้เอามารวมกับของเดิมด้วย
            if(vVisitPaymentCancel.size() >0)
            {
                 for(int i=0; i<vVisitPaymentCancel.size(); i++) {
                    Payment p = (Payment)vVisitPaymentCancel.get(i);
                    vVisitPayment.add(p);
                }   
            }
            //ถ้าสิทธิประจำตัวผู้ป่วยเป็นค่าว่างก็จะบันทึกสิทธินี้เป็นสิทธิประจำตัวให้ทันที
            if(theHO.vPatientPayment.isEmpty())
            {
                PatientPayment pp = new PatientPayment(thePaymentNow);
                thePatientControl.intSavePatientPayment(theHO.thePatient,theHO.theFamily,theHO.vPatientPayment,pp);
            }
            theHO.vVisitPayment = theHosDB.thePaymentDB.selectByVisitId(theHO.theVisit.getObjectId());
            theUS.setStatus("การบันทึกสิทธิการรักษาเสร็จสิ้น",UpdateStatus.COMPLETE);
            theHS.theVPaymentSubject.notifySaveVPayment(Constant.getTextBundle("การบันทึกสิทธิการรักษาเสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            theUS.setStatus("การบันทึกสิทธิการรักษาผิดพลาด",UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
    }
 
    
    
/********************VisitControl***********************************/
    public Vector listQueueXray(String choose)
    {
        Vector vc = new Vector();
        theHosDB.c2.open();
        try
        {
            vc = theHosDB.theListTransferC2DB.listQueueXray(choose);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theHosDB.c2.close();
        return vc;
    }
/********************VisitControl***********************************/
    public Vector listQueueLab(String choose)
    {
        Vector vc = new Vector();
        theHosDB.c2.open();
        try
        {
            vc = theHosDB.theListTransferC2DB.listQueueLab(choose);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theHosDB.c2.close();
        return vc;
    }
   /********************VisitControl***********************************/
    public Vector listRemainQueueLab(String choose){
        Vector vc = new Vector();
        theHosDB.c2.open();
        try
        {
            vc = theHosDB.theListTransferC2DB.listRemainQueueLab(choose);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theHosDB.c2.close();
        return vc;
    }
    
    /**
     * 
     * @param list 
     * @param value 
     * @return 
     */
    public boolean saveQueueValue(ListTransfer list,String value)
    {
        int int_value = 0;
        try{
            int_value = Integer.parseInt(value);
        }
        catch(Exception e){
            theUS.setStatus("กรุณากรอกเลขคิวที่เป็นตัวเลข",UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try{
            list.queue = value;
            int ret = theHosDB.theQueueTransferDB.update(list);
            theUS.setStatus("การบันทึกเลขคิวเสร็จสิ้น",UpdateStatus.COMPLETE);
            return ret>0;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus("การบันทึกเลขคิวผิดพลาด",UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();    
        }
    }
    /**
     *  function
     *  tong test list transfer
     *
     *  ใช้ในการ list ผู้ป่วยที่อยู่ในจุดบริการ โดยจะส่งข้อมูลเข้า
     *  service_point_id = key หลักของตาราง โดยจะเป็น key หลักของตาราง service_point
     *  employee_id_doctor = key หลักของตาราง โดยจะเป็น key หลักของตาราง Employee เฉพาะจุดบริการที่เป็นห้องตรวจ
     *  choose = เลือกว่าเป็นผู้ป่วยใน หรือ ผู้ป่วยนอก
     */
    public Vector listQueueXray()
    {
        Vector vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = theHosDB.theListTransferDB.listQueueXray();
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }
    /**
     *  function
     *  tong test list transfer
     *
     *  ใช้ในการ list ผู้ป่วยที่อยู่ในจุดบริการ โดยจะส่งข้อมูลเข้า
     *  service_point_id = key หลักของตาราง โดยจะเป็น key หลักของตาราง service_point
     *  employee_id_doctor = key หลักของตาราง โดยจะเป็น key หลักของตาราง Employee เฉพาะจุดบริการที่เป็นห้องตรวจ
     *  choose = เลือกว่าเป็นผู้ป่วยใน หรือ ผู้ป่วยนอก
     */
    public Vector listQueueICD(String choose){
        return listQueueICD(choose,"","");
    }
    public Vector listQueueICD(String choose,String date_from,String date_to)
    {
        if(choose.equals(""))
            choose = "%";
        theConnectionInf.open();
        try{
//            vc = theHosDB.theListTransferC2DB.selectByTypeDate(choose,date_from,date_to);
            String sql = "select " +
            "     t_visit.visit_locking  " +
             "    ,t_patient.patient_drugallergy" +
             "    ,t_visit.visit_hn" +
             "    ,t_visit.visit_vn" +
             "    ,f_patient_prefix.patient_prefix_description" +
             " || ' '  || t_patient.patient_firstname" +
             " || '  '|| t_patient.patient_lastname" +
             "    ,t_visit_queue_coding.assign_date_time" +
             "    ,case when diag_icd10_number is not null" +
             "        then diag_icd10_number || ' : ' || t_visit.visit_dx" +
             "        else t_visit.visit_dx end as b_service_point_id   " +
             "    ,t_visit.visit_lab_status_id  " +
              "   ,b_visit_queue_setup.visit_queue_setup_queue_color " +
                    ", b_visit_queue_setup.visit_queue_setup_description "+
            "     ,t_visit.t_visit_id " +
             " from t_visit_queue_coding" +
             "    INNER JOIN t_visit on t_visit.t_visit_id = t_visit_queue_coding.t_visit_id  " +
             "    INNER JOIN t_patient on t_patient.t_patient_id = t_visit.t_patient_id " +
             "    LEFT JOIN t_visit_queue_map on t_visit_queue_map.t_visit_id = t_visit_queue_coding.t_visit_id"+
             "    LEFT JOIN b_visit_queue_setup on b_visit_queue_setup.b_visit_queue_setup_id = t_visit_queue_map.b_visit_queue_setup_id"+
             "    LEFT JOIN t_diag_icd10  on (t_diag_icd10.diag_icd10_vn = t_visit.t_visit_id  " +
             "        and t_diag_icd10.f_diag_icd10_type_id = '1' " +
             "        and t_diag_icd10.diag_icd10_active = '1')" +
             "    LEFT JOIN f_patient_prefix  on f_patient_prefix.f_patient_prefix_id = t_patient.f_patient_prefix_id " +
             "where visit_hn <> '' ";
            if( choose.equals(VisitType.IPD) || choose.equals(VisitType.OPD) )
                sql += " and t_visit.f_visit_type_id  = '" + choose + "' ";

            if(!date_from.equals("") && !date_to.equals("")){
                date_to = DateUtil.addDay(date_to,1);
                sql += " and assign_date_time > '"+ date_from +"'"+
                    " and assign_date_time < '"+ date_to +"'";
            }
            sql += " order by assign_date_time";    
            java.sql.ResultSet rs = theConnectionInf.eQuery(sql);
            Vector vResult = new Vector();
            int row_count=0;
            while(rs.next()){
                String[] data = new String[12];
                data[0] = String.valueOf(++row_count);
                for(int i=1;i<data.length;i++)
                    data[i] = rs.getString(i);    
                vResult.add(data);
            }
            return vResult;
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     *  function
     *  tong test list transfer
     *
     *  ใช้ในการ list ผู้ป่วยที่อยู่ในจุดบริการ โดยจะส่งข้อมูลเข้า
     *  service_point_id = key หลักของตาราง โดยจะเป็น key หลักของตาราง service_point
     *  employee_id_doctor = key หลักของตาราง โดยจะเป็น key หลักของตาราง Employee เฉพาะจุดบริการที่เป็นห้องตรวจ
     *  choose = เลือกว่าเป็นผู้ป่วยใน หรือ ผู้ป่วยนอก
     */
    public Vector listQueueLab()
    {
        Vector vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = theHosDB.theListTransferDB.listQueueLab();
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }
    
    public Vector listRemainQueueLab(){
        Vector vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = theHosDB.theListTransferDB.listRemainQueueLab();
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return vc;
    }


    /**
     * 
     * @param er 
     * @return 
     */
     public boolean updateVisitEmergency(String er)
    {
        Constant.println(UseCase.UCID_updateVisitEmergency);
        String objectid =   null;
        if(theHO.theVisit != null)
            objectid = theHO.theVisit.getObjectId();
        if(theHO.theVisit == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้",UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try{
            theHO.theVisit.emergency = er;
            theHO.theVisit.emergency_staff = theHO.theEmployee.getObjectId();
            theHosDB.theVisitDB.update(theHO.theVisit);
            theSystemControl.setStatus(UseCase.TH_updateVisitEmergency,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_updateVisitEmergency,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_updateVisitEmergency,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_updateVisitEmergency,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }
    }

    /**
     * 
     * @param pregnant 
     * @return 
     */
    public boolean updateVisitPregnant(boolean pregnant)
    {
        String objectid =   null;
        Constant.println(UseCase.UCID_updateVisitPregnant);
        if(theHO.theVisit != null)
            objectid = theHO.theVisit.getObjectId();
        if(theHO.theVisit == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return false;
        }
//        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
//        {
//            theUS.setStatus("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้",UpdateStatus.WARNING);
//            return false;
//        }
        /*
         *amp:16/5/2549 เนื่องจากเมื่อมีเลือกว่าตั้งครรภ์ แล้วมีการเปลี่ยนอายุหรือเพศให้ไม่สัมพันธ์กับการตั้งครรภ์ ไม่สามารถเอาตั้งครรภ์ออกได้
         *คือเมื่อเอาตั้งครรภ์ออก ก็ไม่ต้องตรวจสอบเพศและอายุ
        */
        if(pregnant)
        {
            if(theHO.thePatient.f_sex_id.equals(Sex.isMAN()))
            {   
                theUS.setStatus("ผู้ป่วยเป็นชายตั้งครรภ์ไม่ได้",UpdateStatus.WARNING);
                return false;
            }
            if(Integer.parseInt(DateUtil.calculateAge(theHO.thePatient.patient_birthday,theHO.date_time)) < 10)
            {
                theUS.setStatus("ผู้ป่วยมีอายุน้อยกว่า 10 ปีไม่สามารถตั้งครรภ์ได้",UpdateStatus.WARNING);
                return false;
            }
        }
        theConnectionInf.open();
        try{
            String preg = "0";
            if(pregnant) preg = "1";
            theHO.theVisit.pregnant = preg;
            theHosDB.theVisitDB.updateVisitPregnant(preg,theHO.theVisit.getObjectId());
            
            //amp:01/04/2549 ตรวจว่ามีการมีสั่งยาที่มีปฏิกิริยากับการตั้งบ้างหรือเปล่า ถ้ามีให้ set active ของยาที่มีปฏิกิริยาด้วยเป็น 0
            //ในกรณี Update
            if(theLookupControl.readOption().isUseDrugInteract())
            {
            int is_interaction = theHosDB.theOrderDrugInteractionDB.updatePregnantByVisitId(theHO.theVisit.getObjectId(), theHO.theVisit.pregnant);            
            //amp:04/04/2549 ในกรณี เพิ่มใหม่            
            if(is_interaction==0 && "1".equals(theHO.theVisit.pregnant))
            {
                OrderItem orderItem;
                DrugStandardMapItem drugStandardMapItem;
                DrugInteraction drugInteraction;
                String interaction = "";
                String std_old = "";
                for(int i=0,size=theHO.vOrderItem.size(); i<size; i++)
                {
                    orderItem = (OrderItem)theHO.vOrderItem.get(i);
                    drugStandardMapItem = theHosDB.theDrugStandardMapItemDB.selectByItem(orderItem.item_code);
                    if(drugStandardMapItem!=null)
                    {
                        drugInteraction = theHosDB.theDrugInteractionDB
                                    .readPregnantInteraction(drugStandardMapItem.drug_standard_id);
                        if(drugInteraction!=null)
                        {  
                            if("".equals(interaction))
                            {
                                interaction = interaction + " " + drugInteraction.drug_standard_original_description;
                                std_old = drugInteraction.drug_standard_original_id; 
                            }
                            else
                            {
                                if(!std_old.equals(drugInteraction.drug_standard_original_id))
                                {
                                    interaction = interaction + ", " + drugInteraction.drug_standard_original_description;
                                }
                                std_old = drugInteraction.drug_standard_original_id;
                            }
                        }
                    }
                }
                if(!"".equals(interaction))
                {
                    theUS.setStatus(Constant.getTextBundle("การตั้งครรภ์มีปฏิกิริยากับ")+" " + interaction ,UpdateStatus.WARNING);
                }
                orderItem=null;
                drugStandardMapItem=null;
                drugInteraction=null;
                interaction=null;
                std_old=null;
            }
            }
            theUS.setStatus("บันทึกการตั้งครรภ์เสร็จสิ้น" ,UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_updateVisitPregnant,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_updateVisitPregnant,objectid,null,UpdateStatus.COMPLETE);
            return true;                        
        }        
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_updateVisitPregnant,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_updateVisitPregnant,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }    
   
    /*การลบข้อมูลสิทธิ์ที่ใช้ในการรับบริการ*/
    /**
     * 
     * @param p 
     * @return 
     */
    public String deleteVisitPayment(Payment p)
    {
        Constant.println(UseCase.UCID_deleteVisitPayment);
        String objectid =   null;
        theConnectionInf.open();
        try{
            theHosDB.thePaymentDB.delete(p);
            theHS.theVisitSubject.notifyDeleteVisitPayment("ลบสิทธิ์ผู้ป่วยเสร็จเรัยบร้อย", UpdateStatus.COMPLETE);
            if(p != null)
                objectid = p.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deleteVisitPayment,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteVisitPayment,objectid,null,UpdateStatus.COMPLETE);
            return null;
        }
        catch(Exception ex){   
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_deleteVisitPayment,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deleteVisitPayment,objectid,ex,UpdateStatus.ERROR);
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**Function tong
    private void intSetStartVNinNowYears()throws Exception 
    {
        String vn = null;
        String year = null;
        String maxvn = theHosDB.theVisitDB.selectMaxVN();
        if(maxvn == null) {   
            Constant.println("setStartVNinNowYears() Max VN is null");
            return;
        }
        year = maxvn.substring(0,3);
        vn = maxvn.substring(3);
        int oldyear = Integer.parseInt(year);
        String newyearS = Timing.getYear2Digit(theConnectionInf);
        int newyear = Integer.parseInt(newyearS);
        if(newyear < oldyear){
            Constant.println("setStartVNinNowYears() NewYear less than OldYear");
            return;
        }
        SequenceData sequenceData = theHosDB.theSequenceDataDB.selectByPK("vn");
        int newvn = Integer.parseInt(sequenceData.value);
        int oldvn = Integer.parseInt(vn);
        if(oldvn > newvn){
            Constant.println("setStartVNinNowYears() NewVN less than OldVN");
            return;
        }
        sequenceData.value = "1";
        theHosDB.theSequenceDataDB.update(sequenceData);
    }
*/
 
  
    
    
   
  
    /*
     *ค้างบันทึก saveCause
     ** ส่งเข้าคิวเวชสถิติ 
     * สำเร็จการส่งผู้ป่วยทั้งระบบ
     * ลบข้อมูลออกจากคิว opd
     ** ลบข้อมูลออกจากคิว lab
     *เป็นการจำหน่ายทางการเงิน
     */
    public boolean remainDoctorDischarge()
    {   
        Constant.println("public void endVisitForStat(Visit theVisit,Vector theTransfer)");
        Constant.println(UseCase.UCID_remainVisit);
        String objectid =   null;
        if(theHO.theVisit != null)
            objectid = theHO.theVisit.getObjectId();
        if(theHO.theVisit == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals("2"))
        {   
            theUS.setStatus(("ผู้ป่วยอยู่ในสถานะค้างบันทึกแล้ว"),UpdateStatus.WARNING);
            return  false;
        }
        if(theLookupControl.readOption().commit.equals(Active.isEnable())) {
            boolean retb = DialogPasswd.showDialog(theHO,theUS,theHO.theEmployee.password);
            if(!retb) {
                theUS.setStatus("รหัสผ่านไม่ถูกต้อง",UpdateStatus.WARNING);
                return false;
            }
        }
        String cause = DialogCause.showDialog(theUS.getJFrame(),Constant.getTextBundle("การค้างบันทึก"));
        if(cause.equals(""))
        {   
            theUS.setStatus(("กรุณากรอกสาเหตุการค้างบันทึก"),UpdateStatus.WARNING);
            return  false;
        }
        else if(cause.equals("CancelDialog"))
            return false;
        
        theConnectionInf.open();
        try{
            String dt = theLookupControl.intReadDateTime();
            theHosDB.theQueueTransferDB.deleteByVisitID(theHO.theVisit.getObjectId());
            QueueICD qicd = new QueueICD();
            intSaveVisitInQueueICD(theHO.theVisit,qicd,dt);
            theHosDB.theTransferDB.updateFinishTimeVisit(
                theHO.theVisit.getObjectId(),dt);

        //ให้ปลดล็อดก่อน/////////////////////////////////////////////////
            theHO.theVisit.visit_status = VisitStatus.isInStat();
            theHO.theVisit.financial_discharge_user = theHO.theEmployee.getObjectId();
            theHO.theVisit.financial_discharge_time = dt;
            theHO.theVisit.locking = "0";
            theHO.theVisit.is_discharge_money = "1";
            theHO.theVisit.stat_dx = cause;
            theHO.theVisit.visit_status = "2";        
            theHosDB.theVisitDB.update(theHO.theVisit);
        //ให้ปลดล็อดก่อน/////////////////////////////////////////////////
            theHO.clearVisit();
            theHS.theVisitSubject.notifyRemainDoctorDischarge(
                "การค้างบันทึกผู้ป่วยเสร็จสิ้น",UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_remainVisit,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_remainVisit,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_remainVisit,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_remainVisit,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }
    }
    

    /**
     * 
     * @param referIn 
     * @return 
     */
    public int deleteReferIn(Refer referIn)
    {
        Constant.println(UseCase.UCID_deleteRefer);
        String objectid =   null;
        if(referIn != null)
            objectid = referIn.getObjectId();
        if(referIn==null){
            theUS.setStatus("กรุณาเลือกข้อมูล Refer ก่อนลบ",UpdateStatus.WARNING);
            return 0;
        }
        
         int result_loc = 0;
        theConnectionInf.open();
        try
        {
            referIn.active = Active.isDisable();
            result_loc = theHosDB.theReferDB.update(referIn);
            theSystemControl.setStatus(UseCase.TH_deleteRefer,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteRefer,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_deleteRefer,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deleteRefer,objectid,ex,UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return result_loc;
    }
    

    public Vector listRefer(String hn,String rn,String dateFrom,String dateTo,String status,String is_refer_out)
    {
        Vector rf = new Vector();
        theConnectionInf.open();
        try
        {
            rf = theHosDB.theReferDB.selectByHnReferNumberDate("%" + hn,"%"+ rn, dateFrom, dateTo, status, is_refer_out);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return rf;
    }
    
    
    /**
     * Function tong 2/1/48
     * ตรวจสอบค่าสูงสุดของเลข sequence
     *
     **/
    public void maxSequenceNumber(String[] max,String[] name) {
        theConnectionInf.open();
        try{
            
            SequenceData sd = theHosDB.theSequenceDataDB.selectByPK("an");
            if(sd != null) {
                max[2] = String.valueOf((Integer.parseInt(sd.value) -1 ));
                name[2] = sd.name;
            }
            
            sd = theHosDB.theSequenceDataDB.selectByPK("vn");
            if(sd != null) {
                max[1] = String.valueOf((Integer.parseInt(sd.value) -1 ));
                name[1] = sd.name;
            }
            
            sd = theHosDB.theSequenceDataDB.selectByPK("hn");
            if(sd != null) {
                max[0] = String.valueOf((Integer.parseInt(sd.value) -1 ));
                name[0] = sd.name;
            }
            
            sd = theHosDB.theSequenceDataDB.selectByPK("rn");
            if(sd != null) {
                max[3] = String.valueOf((Integer.parseInt(sd.value) -1 ));
                name[3] = sd.name;
            }
            
            sd = theHosDB.theSequenceDataDB.selectByPK("xn");
            if(sd != null) {
                max[4] = String.valueOf((Integer.parseInt(sd.value) -1 ));
                name[4] = sd.name;
            }
            
            sd = theHosDB.theSequenceDataDB.selectByPK("rfin");
            if(sd != null) {
                max[5] = String.valueOf((Integer.parseInt(sd.value) -1 ));
                name[5] = sd.name;
            }
            
            sd = theHosDB.theSequenceDataDB.selectByPK("rfon");
            if(sd != null) {
                max[6] = String.valueOf((Integer.parseInt(sd.value) -1 ));
                name[6] = sd.name;
            }
            
            sd = theHosDB.theSequenceDataDB.selectByPK("dfn");
            if(sd != null) {
                max[7] = String.valueOf((Integer.parseInt(sd.value) -1 ));
                name[7] = sd.name;
            }
            
            sd = theHosDB.theSequenceDataDB.selectByPK("hn_hcis");
            if(sd != null) {
                max[8] = String.valueOf((Integer.parseInt(sd.value) -1 ));
                name[8] = sd.name;
            }
        } catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally{
            theConnectionInf.close();
        }
        
    }
    
    /**
     * 
     * @param guide 
     */
    public void saveGuideDxTR(GuideAfterDxTransaction guide)
    {
        theConnectionInf.open();
        try
        {
            if(guide.getObjectId() == null) 
                    theHosDB.theGuideAfterDxTransactionDB.insert(guide);
            else    theHosDB.theGuideAfterDxTransactionDB.update(guide);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
    }
    
    /**
     *@author: sumo
     *@date: 10/08/2549
     *@see : บันทึกคำแนะนำ
     *@param : Vector คำแนะนำ
     *@return void
     */
    public void saveGuideHealthEducation(Vector guide)
    {
        Constant.println(UseCase.UCID_saveGuideHealthEducation);
        String objectid =   null;
        if(theHO.theVisit == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return ;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้",UpdateStatus.WARNING);
            return ;
        }
        if(guide==null)
        {   
            theUS.setStatus("ยังไม่มีข้อมูลคำแนะนำ",UpdateStatus.WARNING);            
            return;
        }
        theConnectionInf.open();
        try{
            theHosDB.theGuideAfterDxTransactionDB.deleteByVid(theHO.theVisit.getObjectId()); 
//            for(int i=guide.size()-1; i>=0; i--)
            for(int i=0;i<guide.size();i++)
            {
                GuideAfterDxTransaction gu = new GuideAfterDxTransaction();
                gu = (GuideAfterDxTransaction)guide.get(i);
                gu.guide = gu.guide.trim();
                gu.health_head = gu.health_head.trim();
                gu.visit_id = theHO.theVisit.getObjectId();
                if(gu.getObjectId()==null)
                {
                    theHosDB.theGuideAfterDxTransactionDB.insert(gu);
                }
            }
            theHO.vHealthEducation = theHosDB.theGuideAfterDxTransactionDB.selectGuideByHealthEducation(theHO.theVisit.getObjectId()); 
            theUS.setStatus("บันทึกคำแนะนำเสร็จสิ้น",UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_saveGuideHealthEducation,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveGuideHealthEducation,objectid,null,UpdateStatus.COMPLETE);
        }   
        catch(Exception ex)
        {    
            theSystemControl.setStatus(UseCase.TH_saveGuideHealthEducation,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveGuideHealthEducation,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();  
        }
    }

    
    /**
     * 
     * @param visitId 
     * @return 
     */
    public GuideAfterDxTransaction listGuideByVisitId(String visitId)
    {
        theConnectionInf.open();
        try
        {
            return theHosDB.theGuideAfterDxTransactionDB.selectByVisitId(visitId);
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
     * @author henbe
     * แก้ฟังชันนี้ให้บันทึกการ refer ทันทีที่กดปุ่มเพื่อให้แพทย์ตรวจสอบและแก้ไขทันที เพราะ
     * มันมีปัญหาว่ายุ่งยากในการบันทึกทำให้ case นี้หลุดไปเยอะมาก
     * @name บันทึกการส่งต่อผู้ป่วย
     * @date 17/05/06
     **/
      
    public boolean saveReferIn(Patient thePatient,Visit theVisit,Refer refer,UpdateStatus theUS)
    {
        Constant.println("public boolean saveReferIn(Visit theVisit,Refer refer,UpdateStatus theUS)");
        Constant.println(UseCase.UCID_saveRefer);
        String objectid =   null;
        if(theHO.theVisit != null)
            objectid = theHO.theVisit.getObjectId();
        if(thePatient == null)
        {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่ต้องการ Refer ก่อนทำการบันทึก", theUS.WARNING);
            return false;
        }
        if(thePatient.discharge_status_id!=null && thePatient.discharge_status_id.equals(Active.isEnable()))
        {
            theUS.setStatus("ไม่สามารถ Refer ผู้ป่วยเสียที่ชีวิตแล้วได้", theUS.WARNING);
            return false;
        }
        if(theVisit == null)
        {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ", theUS.WARNING);
            return false;
        }
//        if(theVisit.is_discharge_doctor.equals(Active.isEnable()) && refer.getObjectId()==null)
//        {
//            theUS.setStatus("ผู้ป่วยจำหน่ายทางการแพทย์แล้วไม่สามารถบันทึกส่งต่อได้", theUS.WARNING);
//            return false;
//        }
        if(DateUtil.countDateDiff(refer.date_refer,theVisit.begin_visit_time)<0){
            theUS.setStatus("กรุณาระบุวันที่ส่งต่อหลังวันที่เข้ารับบริการ",UpdateStatus.WARNING); 
            return false;
        }
        if( !refer.date_refer.equals("") 
            && !theVisit.doctor_discharge_time.equals("")
            && DateUtil.countDateDiff(refer.date_refer,theVisit.doctor_discharge_time)>0){
            theUS.setStatus("กรุณาระบุวันที่ส่งต่อก่อนวันจำหน่ายทางการแพทย์",UpdateStatus.WARNING); 
            return false;
        }
//        if(refer.getObjectId()==null){
//            if(theVisit.is_discharge_doctor.equals(Active.isEnable()) && refer.refer_out.equals(Active.isEnable())) {
//                theUS.setStatus("ผู้ป่วยถูกจำหน่ายทางการแพทย์แล้วไม่สามารถบันทึกการส่งต่อได้ กรุณาย้อนกลับการจำหน่าย", theUS.WARNING);
//                return false;
//            }
//        }
        theConnectionInf.open();
        try{
            
            boolean result = intSaveRefer(refer,theVisit,theUS);
            //////////////////////////////////////////////////////////////////////
            if(refer.refer_out.equals(Active.isEnable()))
            {
                //theVisit.is_discharge_doctor = "1";
                if(theVisit.doctor_discharge_time.equals(""))
                    theVisit.doctor_discharge_time = theHO.date_time;
                theVisit.doctor_discharge_user = refer.doctor_refer;
                theVisit.discharge_opd_status = DischargeOpd.REFER;
                //comment เพราะว่าตอนจำหน่ายทางการแพทย์กรณีผู้ป่วยในจะเลือกเอง  
                //theVisit.discharge_ipd_type = "";
                //theVisit.discharge_ipd_status = "";  
                theVisit.refer_out = refer.office_refer;
                //theVisit.is_discharge_doctor = "1";
            }
            else
                theVisit.refer_in = refer.office_refer;
            theHosDB.theVisitDB.update(theVisit);
            
            if(!result)
                return false;
            //ให้ dialog ที่เรียกมันเห็นบ้าง
            theUS.setStatus("การบันทึกข้อมูลการ Refer เสร็จสิ้น", theUS.COMPLETE);
            //theHS.theVisitSubject.notifyDischargeDoctor("การบันทึกข้อมูลการ Refer เสร็จสิ้น", theUS.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_saveRefer,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveRefer,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_saveRefer,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveRefer,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally {
            theConnectionInf.close();
        }
    }
    /**
     * 
     * @param refer 
     * @param theVisit 
     * @throws java.lang.Exception 
     * @return 
     */
    protected boolean intSaveRefer(Refer refer,Visit theVisit,UpdateStatus theUS)throws Exception 
    {
            theLookupControl.intReadDateTime();
            Refer refer_check = theHosDB.theReferDB.selectByVisitIdType(theVisit.getObjectId(),refer.refer_out);
            if(refer.office_refer.equals("") && refer.refer_out.equals(Active.isEnable()))
            {
                theUS.setStatus("กรุณาเลือกสถานพยาบาลที่ส่งต่อผู้ป่วย", theUS.WARNING);
                return false;
            }
            if(refer.office_refer.equals("") && refer.refer_out.equals(Active.isDisable()))
            {
                theUS.setStatus("กรุณาเลือกสถานพยาบาลที่รับมา", theUS.WARNING);
                return false;
            }
            if(refer.office_refer.equals(theHO.theSite.off_id)) {
                theUS.setStatus("สถานพยาบาลที่ Refer ต้องเป็นสถานพยาบาลอื่น", UpdateStatus.WARNING);
                return false;
            }            
            if(refer_check!=null && refer.getObjectId()==null && refer.refer_out.equals(refer_check.refer_out)){
                theUS.setStatus("มีข้อมูลการ refer แล้วในการรับบริการครั้งนี้กรุณาแก้ไขข้อมูลเดิม", UpdateStatus.WARNING);
                return false;
            }
            if(refer.history_lab.length()>4000){
                theUS.setStatus(Constant.getTextBundle("ข้อมูลผลแลปมากเกินกว่าที่โปรแกรมจะบันทึกได้")+" "+
                        Constant.getTextBundle("กรุณาลดปริมาณข้อมูลที่บันทึก"), UpdateStatus.WARNING);
                return false;
            }
            if(refer.first_treatment.length()>4000){
                theUS.setStatus(Constant.getTextBundle("ข้อมูลรายการบริการที่ให้มากเกินกว่าที่โปรแกรมจะบันทึกได้")+" "+
                        Constant.getTextBundle("กรุณาลดปริมาณข้อมูลที่บันทึก"), UpdateStatus.WARNING);
                return false;
            }
            refer.result_treatment = Gutil.CheckReservedWords(refer.result_treatment);
            refer.result_lab = Gutil.CheckReservedWords(refer.result_lab);
            refer.continue_treatment = Gutil.CheckReservedWords(refer.continue_treatment);
            refer.other_detail = Gutil.CheckReservedWords(refer.other_detail);
            refer.history_family = Gutil.CheckReservedWords(refer.history_family);
            refer.history_current = Gutil.CheckReservedWords(refer.history_current);
            refer.history_lab = Gutil.CheckReservedWords(refer.history_lab);
            refer.first_treatment = Gutil.CheckReservedWords(refer.first_treatment);
            Constant.println("2"+refer.refer_observe);
            //////////////////////////////////////////////////////////////////////
            if(refer.getObjectId()==null){
                if(refer.date_refer.equals(""))
                    refer.date_refer = theHO.date_time.substring(0,10);
                refer.reporter_refer = theHO.theEmployee.getObjectId();
                refer.vn=theVisit.vn;
                refer.vn_id=theVisit.getObjectId();
                refer.patient_id = theVisit.patient_id;
                refer.hn = theVisit.hn;
                theHosDB.theReferDB.insert(refer);
                //ทำไมไม่บอกสาเหตุด้วย 
                //theVisit.refer_cause = refer.cause_refer;
                //////////////////////////////////////////////////////////////////////
            }
            else
                theHosDB.theReferDB.update(refer);
            
            Constant.println("3"+refer.refer_observe);
            return true;
    }
    
    /**
     *  function
     *  tong test list transfer
     *
     *  ใช้ในการ list ผู้ป่วยที่อยู่ในจุดบริการ โดยจะส่งข้อมูลเข้า
     *  service_point_id = key หลักของตาราง โดยจะเป็น key หลักของตาราง service_point
     *  employee_id_doctor = key หลักของตาราง โดยจะเป็น key หลักของตาราง Employee เฉพาะจุดบริการที่เป็นห้องตรวจ
     *  choose = เลือกว่าเป็นผู้ป่วยใน หรือ ผู้ป่วยนอก
     */
    public Vector listTransferByServicePoint(String service_point_id
    ,String employee_id_doctor,String choose)
    {
        
        Vector vc = null;
        theHosDB.c2.open();
        try{   
            if(service_point_id.equals(""))
                service_point_id = "%";
            vc = theHosDB.theQueueTransferC2DB.listTransferVisitQueueByServicePoint(
                service_point_id,employee_id_doctor,choose);
            this.theLookupControl.intReadDateTime(theHosDB.c2);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theHosDB.c2.close();
        return vc;
    }
    
   
    /**
     *  function
     *  tong test list transfer
     *
     *  ใช้ในการ list ผู้ป่วยที่อยู่ในจุดบริการ โดยจะส่งข้อมูลเข้า
     *  service_point_id = key หลักของตาราง โดยจะเป็น key หลักของตาราง service_point
     *  employee_id_doctor = key หลักของตาราง โดยจะเป็น key หลักของตาราง Employee เฉพาะจุดบริการที่เป็นห้องตรวจ
     *  choose = เลือกว่าเป็นผู้ป่วยใน หรือ ผู้ป่วยนอก
     */
    public Vector listQueueVisitInWard(String ward_id)
    {
        theConnectionInf.open();
        try{
            return theHosDB.theListTransferDB.listQueueVisitInWard(ward_id);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();    
        }
    }
    
    /**
     * 
     * @param str 
     * @return 
     */
   public Vector listVisitInQueueDispense2(String str)
    {
        Vector vc = new Vector();
        theHosDB.c2.open();
        try{
            if(str.equals(""))
                vc = theHosDB.theQueueDispenseC2DB.selectAll();
            if(str.equals(Active.isEnable()))
                vc = theHosDB.theQueueDispenseC2DB.selectByIPD();
            if(str.equals(Active.isDisable()))
                vc = theHosDB.theQueueDispenseC2DB.selectByOPD();
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theHosDB.c2.close();
        }
        return vc;
    }
    
    /**
    *@Author: sumo
    *@date: 18/07/2549
    *@see: ตรวจสอบว่าผู้ป่วยที่เลือกได้มาจากการเลือกในคิวรอจ่ายยาหรือไม่
    *@param: ไม่มี
    *@return: boolean true-เลือกมาจากคิวรอจ่ายยา,false-ไม่ได้เลือกมาจากคิวรอจ่ายยา
     *@deprecated henbe unused
    */
    public boolean ReadQueueDispense2ByVid()
    {
        QueueDispense2 qd = new QueueDispense2();
        theConnectionInf.open();
        try {
            if(theHO.theVisit==null)
                return false;
            qd = theHosDB.theQueueDispense2DB.selectByVisitID(theHO.theVisit.getObjectId());
            if(qd == null){
                return false;    
            }
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return false;
        }
        finally {
            theConnectionInf.close();
        }
    }
   
    public Refer listReferByVNReq(String vn)
    {
        Refer rf = new Refer();
        theConnectionInf.open();
        try
        {
            //not vn it is visit_id data
            rf = theHosDB.theReferDB.selectByVN(vn);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return rf;
    }
    
    public Vector listVisitPaymentByVid(String vn)
    {
        if((((vn == null)) || ((vn.length() == 0)))) return null;
        theConnectionInf.open();
        Vector v=null;
        try
        {
            v = theHosDB.thePaymentDB.selectByVisitId(vn);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return v;
    }

    /**
     *
     * @param vn
     * @return
     */
    public Visit readVisitByVn(String vn) {
        Visit v = new Visit();
        theConnectionInf.open();
        try {
            v = theHosDB.theVisitDB.getVisitByVn(vn);
        } catch (Exception e) {
            e.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return v;
    }
    
    /**
     * 
     * @param vn 
     * @return 
     */
    public Visit readVisitByVnRet(String vn)
    {
        Visit v = new Visit();
        theConnectionInf.open();
        try
        {
            v = theHosDB.theVisitDB.selectByVn(vn);
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return v;
    }
    
    public String readMaxVnByPatientId(String patient_id)
    {
        String mv = "";
        theConnectionInf.open();
        try
        {
            mv = theHosDB.theVisitDB.selectMaxVnByPK(patient_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return mv;
    }
    
    /**
     * 
     * @param queueVisit 
     * @return 
     */
   public String getQueueVisit(QueueVisit queueVisit)
    {
        String queue = "0";
        theConnectionInf.open();
        try
        {
            QueueVisit qv = theHosDB.theQueueVisitDB.selectByPK(queueVisit.getObjectId());
            int q = Integer.parseInt(qv.queue);
            queue =  String.valueOf(q);
            q = q + 1;
            qv.queue = String.valueOf(q);
            theHosDB.theQueueVisitDB.update(qv);
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return queue;
    }

   public QueueVisit readSeqQueueVisit(String id)
   {
       QueueVisit qv=null;
       theConnectionInf.open();
       try{
            qv = intReadSeqQueueVisit(id);
       }
       catch(Exception e){
           e.printStackTrace(Constant.getPrintStream());
       }
       theConnectionInf.close();
       return qv;
   }   
   
    /**
     * 
     * @param id 
     * @throws java.lang.Exception 
     * @return 
     */
   public QueueVisit intReadSeqQueueVisit(String id)throws Exception
    {
        QueueVisit qv=null;
        qv = theHosDB.theQueueVisitDB.selectByPK(id);
        if(qv==null)
            return null;
        String old = qv.queue;
        int q = Integer.parseInt(qv.queue);
        q = q + 1;
        qv.queue = String.valueOf(q);
        theHosDB.theQueueVisitDB.update(qv);
        qv.queue = old;
        return qv;
    }   
   
    /**
     * 
     * @param accident 
     * @param theUS 
     * @return 
     */
   public int deleteAccident(Accident accident,UpdateStatus theUS)
    {
        Constant.println(UseCase.UCID_deleteAccident);
        String objectid =   null;
        int result_loc = -1;
        if(accident != null)
            objectid = accident.getObjectId();
        if(accident == null)
        {
            theUS.setStatus("กรุณาเลือกข้อมูลอุบัติเหตที่ต้องการลบก่อนกดปุ่มลบ", theUS.WARNING);
            return result_loc;
        }
         
        Visit visit = readVisitByVidRet(accident.vn_id);
        if(visit == null)
        {
            theUS.setStatus("เกิดความผิดพลาดของข้อมูล visit", theUS.WARNING);
            return result_loc;
        }
        //ตรวจสอบว่าถูกจำหน่ายทางการแพทย์หรือยัง
        if(visit.is_discharge_doctor.equals(Active.isEnable()))
        {
            theUS.setStatus("ผู้ป่วยถูกจำหน่ายทางการแพทย์แล้วไม่สามารถลบข้อมูลได้", theUS.WARNING);
            visit = null;
            return result_loc;
        }
        if(!theUS.confirmBox("ยืนยันการลบข้อมูลอุบัติเหตุ",UpdateStatus.WARNING))
            return result_loc;
        
        //ตรวจสอบว่าถูกจำหน่ายทางการเงินแล้วหรือยังถ้าถูกจำหน่ายแล้วก็ให้แสดงข้อความเตือน
        theConnectionInf.open();
        try
        {
            accident.active = Active.isDisable();
            accident.staff_cancel = theHO.theEmployee.getObjectId();
            accident.cancel_date_time = theLookupControl.intReadDateTime();
            result_loc = theHosDB.theAccidentDB.delete(accident);
            AccidentType tempAccidentType = null;
            tempAccidentType = getAccidentTypeByID(accident.b_setup_accident_type_id);
            // ยกเลิกไปก่อนเพราะ ใน ช่อง Dx ถ้าผู้ใช้ลบรายการออกไปหมด ข้อมูล mapdx จะถูกลบไปด้วย
           // theHosDB.theMapVisitDxDB.updateActive(accident.vn_id,"","0",accident.staff_cancel,accident.cancel_date_time,tempAccidentType.accident_type_trauma);  
            tempAccidentType = null;
            theUS.setStatus("การลบข้อมูลอุบัติเหตุเสร็จสิ้น", theUS.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_deleteAccident,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteAccident,objectid,null,UpdateStatus.COMPLETE);
            return 1;
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_deleteAccident,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deleteAccident,objectid,ex,UpdateStatus.ERROR);
            return 0;
        }
        finally{
            theConnectionInf.close();
        }
    }
   
   
    /**
     * 
     * @param vid 
     * @return 
     */
    public Visit readVisitByVidRet(String vid)
    {
        Visit v = new Visit();
        theConnectionInf.open();
        try
        {
            v = theHosDB.theVisitDB.selectByPK(vid);
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return v;
    }
    
  /**
     * Use Case: ใช้ในการค้นหาเลข Admit จากการยกเลิกการ Admit จะเรียงจากน้อยไปมาก
     */
    public Vector searchAdmitNumberInReverseAdmit()
    {
        theConnectionInf.open();
        try{
            return theHosDB.theReverseAdmitDB.selectAllByNotUsed();
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return new Vector();
        }
        finally{
            theConnectionInf.close();    
        }
    }
    
    /**
     * 
     * @param reverseAdmit 
     *@deprecated henbe unused
     */
    public void saveReverseAdmit(ReverseAdmit reverseAdmit)
    {
        theConnectionInf.open();
        try{
                theHosDB.theReverseAdmitDB.update(reverseAdmit);
        }
        catch(Exception ex){    
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     */
    public void saveReverseAdmit(Vector vReverseAdmit,int[] row)
    {
        theConnectionInf.open();
        try{
            for(int i=0;i<row.length;i++){
                ReverseAdmit ra = (ReverseAdmit)vReverseAdmit.get(row[i]);
                ra.used = "1";
                theHosDB.theReverseAdmitDB.update(ra);
            }
        }
        catch(Exception ex){    
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     * 
     * @param admit 
     * @param reverseAdmit 
     * @param emp_doctor 
     * @return 
     */
    public boolean reverseAdmit(Visit admit, ReverseAdmit reverseAdmit,String emp_doctor)
    {
        Constant.println("public void reverseAdmit(Visit admit, ReverseAdmit reverseAdmit)");
        Constant.println(UseCase.UCID_reverseAdmit);
        String objectid =   null;
        if(admit != null)
            objectid = admit.getObjectId();
        if(reverseAdmit.reverse_admit_cause.length()==0){
            theUS.setStatus("กรุณากรอกสาเหตุการยกเลิก",UpdateStatus.WARNING);
            return false;
        }
        //การแก้ไขข้อมูลในตาราง visit
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            // an จะเก็บเลขเก่าของ vn ไว้ในขณะที่ vn เก็บเลข AN
            admit.vn = admit.an;
            admit.an = "";
            admit.visit_type = VisitType.OPD;
            admit.ward = "";
            admit.admit_clinic ="";
            admit.bed ="";
            admit.begin_admit_time = "";
            theHosDB.theVisitDB.update(admit);
            
            theHosDB.theReverseAdmitDB.insert(reverseAdmit);
            theHO.is_cancel_admit = true;
            intSaveTransferThrow(theHO.theEmployee, date_time);
            intSaveTransaction(theHO.theServicePoint,date_time,"");
            if(admit!=null ){
                admit.locking = "0";
                admit.lock_user = "";
                admit.lock_time = "";
                theHosDB.theVisitDB.updateLocking(admit);
                theHosDB.theQueueTransferDB.updateLockByVid(admit.getObjectId());
            }
            
            //amp:15/08/2549: เมื่อยกเลิกการ admit ให้ off ยาให้ด้วย
            for(int i=0,size=theHO.vOrderItem.size();theHO.vOrderItem!=null && i<size;i++)
            {
                OrderItem oi = (OrderItem)theHO.vOrderItem.get(i);
                if(oi.continue_order.equals(Active.isEnable()))
                {
                    oi.continue_order = "0";
                    theHosDB.theOrderItemDB.update(oi);
                    OrderContinue oc = theHosDB.theOrderContinueDB.selectByOrid(oi.getObjectId());    
                    oc.date_off = date_time;                    
                    oc.user_off = theHO.theEmployee.getObjectId();
                    oc.doctor_set_off = emp_doctor;
                    theHosDB.theOrderContinueDB.update(oc);
               }
            }
            
            theHS.theVisitSubject.notifyReverseAdmit("ยกเลิกสถานะผู้ป่วยในเสร็จสิ้น", UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_reverseAdmit,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_reverseAdmit,objectid,null,UpdateStatus.COMPLETE);
            return true;
            //theHS.theVisitSubject.notifyreadVisitPatientByVn(admit,"Complete");
        }
        catch(Exception ex){    
            theSystemControl.setStatus(UseCase.TH_reverseAdmit,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_reverseAdmit,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }
        
    }
    
    public Vector listQueueVisit(String pk,String active){
        Vector vc = new Vector();
        theConnectionInf.open();
        try
        {
            vc = theHosDB.theQueueVisitDB.selectAllByName(pk,active);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            //     
        }
        theConnectionInf.close();
        return vc;
    }
    
    public Vector listVisitByPid(String patient_id)
    {
        Constant.println(UseCase.UCID_showDialogHistoryVisit);
        theHO.objectid = patient_id;
        Vector result_loc = new Vector();
        theConnectionInf.open();
        try
        {
            result_loc = theHosDB.theVisitDB.selectVisitByPatientID(patient_id);
            theSystemControl.setStatus(UseCase.TH_showDialogHistoryVisit,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_showDialogHistoryVisit,theHO.objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_showDialogHistoryVisit,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_showDialogHistoryVisit,theHO.objectid,ex,UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return result_loc;
    }
    public Vector listVisitByDatePid(String from , String to,String patient_id)
    {
         Vector result_loc = new Vector();
        theConnectionInf.open();
        try
        {
            result_loc = theHosDB.theVisitDB.selectVnByDateHn(from,to,patient_id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return result_loc;
    }
    public Vector listVisitLabByDatePid(boolean all,String from, String to,String patient_id)
    {
         Vector result_loc = new Vector();
        theConnectionInf.open();
        try{
            result_loc = theHosDB.theSpecialQueryVisit2DB.selectLabByDatePid(all,from,to,patient_id);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return result_loc;
    }    
    public Vector listVisitXrayByDatePid(boolean all,String from, String to,String patient_id)
    {
        Constant.println(UseCase.UCID_listVisitXrayByDatePid);
        String objectid =   null;
         Vector result_loc = new Vector();
        theConnectionInf.open();
        try{
            result_loc = theHosDB.theSpecialQueryVisit2DB.selectXrayByDatePid(all,from,to,patient_id);
            theSystemControl.setStatus(UseCase.TH_listVisitXrayByDatePid,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_listVisitXrayByDatePid,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_listVisitXrayByDatePid,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_listVisitXrayByDatePid,objectid,ex,UpdateStatus.ERROR);
        }
        theConnectionInf.close();
        return result_loc;
    }    
    /**
     *ใช้อันนี้เพราะว่ามันจะได้ vector of patient ออกมาได้ทันทีทำให้ง่ายไม่ต้องไปคิวรีหลายครั้ง
     *@deprecated henbe unused
     */
    public Vector listPatientLocking(String hn,boolean in_process)
    {
        theConnectionInf.open();
        try{
            if(!hn.equals("")) 
                return theHosDB.thePatientDB.selectLockingByHN("%" + hn);
            
            if(in_process)
                return theHosDB.thePatientDB.selectLocking(VisitStatus.isInProcess(),VisitStatus.isInStat());            
            else
                return theHosDB.thePatientDB.selectLocking(VisitStatus.isOutProcess(),VisitStatus.isDropVisit());       
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();    
        }
        
        
    }    
    ///////////////////////////////////////////////////////////////////
    public Vector listVisitLockingByHN(String hn){
         Vector result_loc = new Vector();
        theConnectionInf.open();
        try{
            result_loc = theHosDB.theVisitDB.selectLocking(hn);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return result_loc;
    }
    ///////////////////////////////////////////////////////////////////
    public QueueVisit listQueueVisitByCode(String code)
    {
        QueueVisit theQueueVisit= new QueueVisit();
        theConnectionInf.open();
        try
        {
            theQueueVisit = theHosDB.theQueueVisitDB.selectByCode(code);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return theQueueVisit;
    }
    
    private void intSaveTransaction(ServicePoint sp,String date_time,String ward_id) throws Exception 
    {
        Transfer t = new Transfer();
        if("".equals(ward_id)) t.service_point_id = sp.getObjectId();
        else
        {
            t.service_point_id = ServicePoint.isIPD();
            t.ward_id = ward_id;            
        } 
        theLookupControl.intSaveTransaction(t,date_time);
    }


/**
  *
  * @detail จำหน่ายทางการแพทย์ของผู้ป่วย แบบหลายคน เพื่อให้สามารถจำหน่ายได้อย่างรวดเร็ว
  * @author pongtorn(henbe)
  */
    public void  dischargeDoctor(Visit visit_in,Vector vQueueT,int[] row)
    {
        theConnectionInf.open();
        try{      
            if(visit_in.discharge_opd_status.equals(DischargeOpd.DEATH_OPD)
            ||visit_in.discharge_opd_status.equals(DischargeOpd.DEATH_OUTSIDE)
            ||visit_in.discharge_ipd_type.equals("8")
            ||visit_in.discharge_ipd_type.equals("9"))
            {
                theUS.setStatus("การจำหน่ายผู้ป่วยเสียชีวิตไม่สามารถจำหน่ายในหน้านี้ได้",UpdateStatus.WARNING);
                return ;
            }
            
            if(visit_in.discharge_opd_status.equals(DischargeOpd.REFER))
            {
                theUS.setStatus("การจำหน่ายผู้ป่วยที่ต้องส่งต่อไม่สามารถจำหน่ายในหน้านี้ได้",UpdateStatus.WARNING);
                return ;
            }
            if(row.length==0){
                theUS.setStatus("กรุณาเลือกคนไข้จากรายการในคิว",UpdateStatus.WARNING);
                return ;
            }
            int total = 0;
            for(int i=0,size=row.length;i<size;i++)
            {
                String[] qt = (String[])vQueueT.get(row[i]);
                String visit_id = qt[qt.length-1];
                Visit visit = theHosDB.theVisitDB.selectByPK(visit_id);
                visit.discharge_ipd_type = visit_in.discharge_ipd_type;
                visit.discharge_opd_status = visit_in.discharge_opd_status;
                visit.discharge_ipd_status = visit_in.discharge_ipd_status;
                visit.refer_out = visit_in.refer_out;
                Vector diag10 = theHosDB.theDiagIcd10DB.selectByVisitId(visit_id);
                if(diag10!=null && !diag10.isEmpty())
                {
                    total++;
                    intDischargeDoctor(visit,true);
                }
            }
            if(total==0)
            {
                theUS.setStatus("ไม่มีรายการที่จำหน่ายโปรดตรวจสอบการลงรหัสโรค",UpdateStatus.WARNING);
                return;
            }
            theHS.theVisitSubject.notifyDischargeDoctor("การจำหน่ายทางการแพทย์เสร็จสิ้น",UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus("การจำหน่ายทางการแพทย์ผิดพลาด",UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    } 
    
 /**
  * @detail จำหน่ายทางการแพทย์ของผู้ป่วย แบบหลายคน เพื่อให้สามารถจำหน่ายได้อย่างรวดเร็วเป็นฟังชันภายใน
  * @author pongtorn(henbe)
  * @deprecated henbe unused theHosDB.theVisitDB.updateDischargeDoctor(visit);
  */   
    protected void intDischargeDoctor(Visit visit,boolean end) throws Exception 
    {
        visit.is_discharge_doctor = "1";
        if(visit.doctor_discharge_time.equals(""))
            visit.doctor_discharge_time = theHO.date_time;
        ////////////////////////////////////////////////////////////////////////
        visit.doctor_discharge_user = theHO.theEmployee.getObjectId();
        visit.locking = "0";
        ///////////////////////////////////////////////////////////////////////            
        if(end){
            if(visit.isDischargeMoney())
                visit.visit_status = VisitStatus.isOutProcess();
            theHosDB.theQueueICDDB.deleteByVisitID(visit.getObjectId());
        }
        theHosDB.theVisitDB.updateDischargeDoctor(visit);
    }
    
    /**
     *@Author amp
     *@date 15/06/2549
     *@see ดึงประวัติ NCD
     **/
    public Hashtable listNCD()
    {
        Constant.println(UseCase.UCID_listNCDHistory);
        String objectid =   null;
        Hashtable ht = new Hashtable();
        Vector vNCD = new Vector(); 
        Object[] rowDatas;
        theConnectionInf.open();
        try{  
            Vector vVisit = theHosDB.theVisitDB.selectVisitByPatientID(theHO.thePatient.getObjectId());
            if(vVisit==null || vVisit.isEmpty())
            {
                theUS.setStatus("ไม่มีประวัติ NCD ของผู้ป่วยคนนี้",UpdateStatus.WARNING);
                return null;
            }
     //       String[] columnName = new String[vVisit.size()+1];  
                   String[] columnName = new String[vVisit.size()];  
                //รอบแรก
                rowDatas = new Object[21];
                columnName[0] = "วัน เดือน ปี";
                rowDatas[0] = "ระดับโภชนาการ";
                rowDatas[1] = "ความดันโลหิต (ค่าปกติ 120/80 มม.ปรอท)";
                rowDatas[2] = "ชีพจร(ค่าปกติ 60-100 ครั้ง/นาที)";
                rowDatas[3] = "ระดับน้ำตาลในเลือด (ค่าปกติ 80-100 มก%)";
                rowDatas[4] = "ความเข้มข้นของเม็ดเลือดแดง (ค่าปกติ 35-42%)";
                rowDatas[5] = "";
                rowDatas[6] = "การวินิจฉัย";
                rowDatas[7] = "1.";
                rowDatas[8] = "2.";
                rowDatas[9] = "3.";
                rowDatas[10] = "4.";
                rowDatas[11] = "การรักษาด้วยยา";
                rowDatas[12] = "1.";
                rowDatas[13] = "2.";
                rowDatas[14] = "3.";
                rowDatas[15] = "4.";
                rowDatas[16] = "";
                rowDatas[17] = "การดูแลด้านอื่น ๆ";
                rowDatas[18] = "นัดครั้งต่อไป";
                rowDatas[19] = "สถานที่นัด/เวลา";
                rowDatas[20] = "ผู้นัด";
                Constant.println("rowDatas[0]");
                Constant.println(rowDatas[0]);
                //vNCD.add(rowDatas);
                
                Vector vc; 
                Visit theVisit;
                VitalSign theVitalSign;
                String str = "";
                ResultLab theResultLab;
                LabResultItem theLabResultItem;
                Appointment theAppointment;
                Employee theEmployee;
                for(int i=0,size=vVisit.size(); i<size; i++)
                {
                    theVisit = (Visit)vVisit.get(i);                    
                    //columnName[i+1] = DateUtil.getDateShotToString(DateUtil.getDateFromText(theVisit.begin_visit_time),false);
                    columnName[i] = DateUtil.getDateShotToString(DateUtil.getDateFromText(theVisit.begin_visit_time),false);
                    rowDatas = new Object[21];
                    //VitalSign
                    rowDatas[0] = "";
                    rowDatas[1] = ""; 
                    rowDatas[2] = "";
                    vc = theHosDB.theVitalSignDB.selectByVisitId(theVisit.getObjectId());
                    if(vc!=null && !vc.isEmpty())
                    {
                        theVitalSign = (VitalSign)vc.get(0);                        
                        rowDatas[0] = theHosDB.theNutritionTypeDB.selectByPK(theVitalSign.nutrition).description;
                        rowDatas[1] = theVitalSign.pressure; 
                        rowDatas[2] = theVitalSign.puls;
                    }                   
                    theVitalSign=null;
                    //Lab  
                    rowDatas[3] = "";
                    rowDatas[4] = "";
                    vc = theHosDB.theResultLabDB.selectOrderItemByVisit_ID(theVisit.getObjectId());                    
                    if(vc!=null)
                    {
                        for(int m=0,sizem=vc.size(); m<sizem; m++)
                        {
                            theResultLab = (ResultLab)vc.get(m);
                            theLabResultItem = theHosDB.theLabResultItemDB.selectByPK(theResultLab.lab_result_item_id);
                            if(theLabResultItem!=null)
                            {
                                if("1".equals(theLabResultItem.ncd_fbs))
                                {
                                    rowDatas[3] = theResultLab.result;
                                }
                                if("1".equals(theLabResultItem.ncd_hct))
                                {
                                    rowDatas[4] = theResultLab.result;
                                }
                            }
                            
                        }
                    }                    
                    theResultLab=null;
                    theLabResultItem=null;
                    rowDatas[5] = "";
                    rowDatas[6] = "";
                    //Dx
                    rowDatas[7] = "";
                    rowDatas[8] = "";
                    rowDatas[9] = "";
                    rowDatas[10] = "";
                    vc = theHosDB.theMapVisitDxDB.selectMapVisitDxByVisitID(theVisit.getObjectId(),Active.isEnable());
                    if(vc!=null)
                    {
                        for(int j=0,sizej=vc.size(); j<4 && j<sizej; j++)
                        {
                            str = "";
                            str = ((MapVisitDx)vc.get(j)).visit_diag_map_dx;
                            if(j==0) 
                                rowDatas[7] = str;                                
                            else if(j==1)  
                                rowDatas[8] = str;                               
                            else if(j==2)  
                                rowDatas[9] = str;                                
                            else if(j==3) 
                                rowDatas[10] = str;                                
                        } 
                    }                    
                    rowDatas[11] = "";
                    //Order 
                    rowDatas[12] = "";
                    rowDatas[13] = "";
                    rowDatas[14] = "";
                    rowDatas[15] = "";
                    vc = theHosDB.theOrderItemDB.selectByVidTypeCancel(theVisit.getObjectId(),Active.isEnable(),false);
                    if(vc!=null)
                    {
                        for(int k=0,sizek=vc.size(); k<4 && k<sizek; k++)
                        {
                            str = "";
                            str = ((OrderItem)vc.get(k)).common_name;
                            if(k==0)
                                rowDatas[12] = str;                               
                            else if(k==1) 
                                rowDatas[13] = str;                                
                            else if(k==2) 
                                rowDatas[14] = str;                                
                            else if(k==3) 
                                rowDatas[15] = str;                               
                        } 
                    }                   
                    rowDatas[16] = "";
                    rowDatas[17] = "";
                    //Appointment
                    rowDatas[18] = "";
                    rowDatas[19] = "";
                    rowDatas[20] = "";
                    vc = theHosDB.theAppointmentDB.selectByRecordDate(theVisit.getObjectId(), theVisit.begin_visit_time);
                    if(vc!=null && !vc.isEmpty())
                    {
                        theAppointment = (Appointment)vc.get(0);        
                        theEmployee = theLookupControl.readEmployeeById(theAppointment.appoint_staff_record);
                        rowDatas[18] = DateUtil.getDateToString(DateUtil.getDateFromText(theAppointment.appoint_date),false);
                        rowDatas[19] = theHosDB.theServicePointDB.selectByPK(theAppointment.clinic_code).name;
                        if(theEmployee!=null)
                            rowDatas[20] = theEmployee.fname + " " + theEmployee.lname;
                    }  
                    theAppointment=null;
                    theEmployee=null;
                    vNCD.add(rowDatas);                    
                }            
                Constant.println("rowDatas[i]");
                Constant.println(rowDatas[0]);
                Constant.println("vncd.size()");
                Constant.println(vNCD.size());
                ht.put("columnName", columnName);
                ht.put("value", vNCD);
                theSystemControl.setStatus(UseCase.TH_listNCDHistory,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_listNCDHistory,objectid,null,UpdateStatus.COMPLETE);
                return ht;
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_listNCDHistory,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_listNCDHistory,objectid,ex,UpdateStatus.ERROR);
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     *@author: sumo
     *@date: 07/08/2549
     *@see : บันทึกผู้ป่วยนัด
     *@param : boolean การเช็ค ของ Checkbox
     *@return boolean การบันทึกข้อมูล
     */
    public boolean updateVisitAppointment(boolean appointment)
    {
        Constant.println(UseCase.UCID_updateVisitAppointment);
        String objectid =   null;
        if(theHO.theVisit != null)
            objectid = theHO.theVisit.getObjectId();
        if(theHO.theVisit == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้",UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try
        {
            String app = "0";
            if(appointment)
            {
                app = "1";
            }
            theHO.theVisit.have_appointment = app;
            theHosDB.theVisitDB.updateVisitAppointment(app,theHO.theVisit.getObjectId());
            // ยกเลิกการนัดถ้าเช็คเครื่องถูกออก โดยต้องมีสถานะการนัดเป็นรอนัด และสถานะการใช้งานเป็น 1(ใช้งาน)
            if(!theHO.theVisit.appointment_id.equals("") && theHO.theVisit.appointment_id != null)
            {
                Appointment theAppointment = theHosDB.theAppointmentDB.selectByPK(theHO.theVisit.appointment_id);
                if(app.equals(Active.isDisable()) && theAppointment != null 
                   && theAppointment.status.equals(Active.isDisable()) && theAppointment.appoint_active.equals(Active.isEnable()))
                {
                    theAppointment.appoint_active = Active.isDisable();
                    theAppointment.appoint_staff_cancel = theHO.theEmployee.getObjectId();
                    theAppointment.appoint_cancel_date_time = theLookupControl.intReadDateTime();
                    theHosDB.theAppointmentDB.update(theAppointment);
                }
            }
            theSystemControl.setStatus(UseCase.TH_updateVisitAppointment,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_updateVisitAppointment,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_updateVisitAppointment,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_updateVisitAppointment,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     *@author: sumo
     *@date: 07/08/2549
     *@see : บันทึกผู้ป่วย admit
     *@param : boolean การเช็ค ของ Checkbox
     *@return boolean การบันทึกข้อมูล
     */
    public boolean updateVisitAdmit(boolean admit)
    {
        Constant.println(UseCase.UCID_admit);
        String objectid =   null;
        if(theHO.theVisit != null)
            objectid = theHO.theVisit.getObjectId();
        if(theHO.theVisit == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้",UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try
        {
            String adm = "0";
            if(admit)
            {
                adm = "1";
            }
            theHO.theVisit.have_admit = adm;
            theHosDB.theVisitDB.updateVisitAdmit(adm,theHO.theVisit.getObjectId());
            theSystemControl.setStatus(UseCase.TH_admit,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_admit,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_admit,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_admit,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     *@author: sumo
     *@date: 07/08/2549
     *@see : บันทึกผู้ป่วย refer
     *@param : boolean การเช็ค ของ Checkbox
     *@return boolean การบันทึกข้อมูล
     */
    public boolean updateVisitRefer(boolean refer)
    {
        Constant.println(UseCase.UCID_updateVisitRefer);
        String objectid =   null;
        if(theHO.theVisit != null)
            objectid = theHO.theVisit.getObjectId();
        if(theHO.theVisit == null) {
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้",UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try
        {
            String ref = "0";
            if(refer)
            {
                ref = "1";
            }
            theHO.theVisit.have_refer = ref;
            theHosDB.theVisitDB.updateVisitRefer(ref,theHO.theVisit.getObjectId());
            theSystemControl.setStatus(UseCase.TH_updateVisitRefer,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_updateVisitRefer,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_updateVisitRefer,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_updateVisitRefer,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     *@author: sumo
     *@date: 07/08/2549
     *@see : ดึงข้อมูลคิวตาม Visit_id
     *@param : String ของ Visit_id
     *@return Object MapQueueVisit
     */
    public MapQueueVisit readQueueVisitByVisitID(String vid)
    {
        if(vid.equals(""))
        {
            return null;
        }
        theConnectionInf.open();
        try
        {
            MapQueueVisit mapqueuevisit = theHosDB.theMapQueueVisitDB.selectByVisitID(vid);
            return mapqueuevisit;
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
     *@author: sumo
     *@date: 08/08/2549
     *@see : บันทึกข้อมูลการนัดผู้ป่วยจากแถบอาการเจ็บป่วย
     *@param : Object Visit(ข้อมูลการเข้ารับบริการ),Object Appointment(ข้อมูลการนัด),String วันเวลาที่บันทึกการนัด
     *@return void
     */
    public void saveAppointment(Visit theVisit,Appointment theAppointment,String date_time)
    {
        if(theVisit==null)
        {
            theUS.setStatus("กรุณาเลือกผู้ป่วยก่อนทำการบันทึก", theUS.WARNING);
            return;
        }
        if(theAppointment.aptype.equals("")){
            theUS.setStatus("กรุณากรอกข้อมูลนัดมาเพื่อ",UpdateStatus.WARNING);
            return;
        }        
        theAppointment.clinic_code = theHO.theServicePoint.getObjectId();
        if(theAppointment.appoint_staff_record == null || theAppointment.appoint_staff_record.equals(""))
        {
            theAppointment.appoint_staff_record = theHO.theEmployee.getObjectId();
            theAppointment.appoint_record_date_time = date_time;
        }
        if(theHO.theEmployee.authentication_id.equals(Authentication.DOCTOR))
        {
            theAppointment.doctor_code = theHO.theEmployee.getObjectId();
        }
        else
        {
            Vector vDoc = theHO.getDoctorInVisit();
            if(vDoc!=null && !vDoc.isEmpty())
            {
                Employee em = theLookupControl.readEmployeeById((String)vDoc.get(vDoc.size()-1));
                if(em!=null)
                {
                    theAppointment.doctor_code = em.getObjectId();
                }
            }  
        }
        MapQueueVisit mapQueueVisit = readQueueVisitByVisitID(theHO.theVisit.getObjectId());
        if(mapQueueVisit != null)
        {
            theAppointment.queue_visit_id  = mapQueueVisit.queue_visit;
        }
        theConnectionInf.open();
        try
        {
            CalDateAppointment caldate = theHosDB.theCalDateAppointmentDB.selectByCalDateAppointmentNumber(theVisit.cal_date_appointment);
            String[] date = caldate.description.split(" W");
            String appoint_date = DateUtil.getGuiBDate(DateUtil.calDateByWeek(Integer.parseInt(date[0])));
            theAppointment.appoint_date = appoint_date;
            theAppointment.appoint_time = "09:00";
            thePatientControl.intSaveAppointment(theAppointment,null, theUS);
            theVisit.appointment_id = theAppointment.getObjectId();
            theVisit.cause_appointment = theAppointment.aptype;
            theHosDB.theVisitDB.updateVisitPatientAppointment(theVisit);
            theHS.thePatientSubject.notifySaveAppointment("การบันทึกนัดผู้ป่วยเสร็จสิ้น",UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return;
    }
    

/**
   *  การอ่านข้อมูลผู้ป่วยเป็นฟังชันภายในเท่านั้น
 * @deprecated henbe unused
 *  ยกเลิกการใช้งานแล้วเพราะมีหลายชั้นมากเกินไปทำให้เข้าใจการทำงานของมันได้ยาก
 */
    protected void intReadVisitPatient(Visit visit) throws Exception
    {
        theLookupControl.intReadDateTime();
        thePatientControl.intReadVisitSuit(visit);
        intLockVisit(theHO.date_time);
        intSaveTransferCatch(theHO.theEmployee, theHO.date_time);
     }
    
    /**
     *การล้อกผู้ป่วยเป็นพังชันภายในห้ามใช้จากภายนอก
     *เช็คว่าเป็น lab หรือ xray ด้วยถ้าเป็นก็จะไม่ล้อกให้เพื่อให้ง่ายในการทำงานเหมือนเดิม
     * @deprecated henbe unused use patientcontrol.intLockVisit()
     */
    private void intLockVisit(String date_time)throws Exception
    {
        thePatientControl.intLockVisit(date_time);
   }

   /**
    *@deprecated ไม่ค่อยเข้าใจการทำงานวะเพราะมันผิดหลักการ
    */ 
   protected void intSaveQueueDispenseOPD(Vector order,String date_time,Visit visit)throws Exception
   {
        int disp_qty = 0;
        Vector vc = theHosDB.theOrderItemDB.selectOrderItemDrugByVisitId(visit.getObjectId());
        theOrderControl.intSaveQueueOfOrder(vc.size());
   }    
   
    /**
     *@deprecated ไม่ใช่แล้วใช่มั้ย
     */
    protected void intEditVisitInDespenseToZero(Visit visit)throws Exception
    {
            //ตรวจสอบว่ารายการ order มีรายการยาอยู่หรือไม่เอาเฉพาะที่ยังไม่ได้จ่าย ถ้ามี ให้ list เป็น vector
            Vector vc = theHosDB.theOrderItemDB.selectOrderItemDrugByVisitId(visit.getObjectId());
            //ถ้าไม่เป็น null
            if((vc != null))
            {
                // ถ้ามีค่ามากกว่า 0 ให้จัดการudate ลงใน HosDB.theQueueDespenseDB
                if((vc.size() > 0) )
                    theHosDB.theQueueDespenseDB.updateDespenseToZero(visit.getObjectId());
                else
                    theHosDB.theQueueDespenseDB.deleteByVisitID(visit.getObjectId());
            }
            else
            {
                theHosDB.theQueueDespenseDB.deleteByVisitID(visit.getObjectId());
            }
            vc  = null;
    }    
    
   /**
    *@deprecated henbe unused ใช้ตัวนี้แทน
    *theLookupControl.readQueueVisitById(queueVisitID);
    *
    */
    public QueueVisit readQueueVisitByQueueVisitID(String queueVisitID)
    {
        return theLookupControl.readQueueVisitById(queueVisitID);
    }

    /**
     *ไม่ได้ใช้แล้วเพราะว่ามันต้องคิวรี 2 รอบค้นผู้ป่วยอีก
     *เสียเวลา
     * not @deprecated ยังต้องค้นอยู่
     * henbe
     */
    public Vector listVisitLocking(boolean in_process)
    {
        return listVisitLocking(in_process,"");
    }
    public Vector listVisitLocking(boolean in_process,String hn)
    {
         Vector result_loc = new Vector();
        theConnectionInf.open();
        try{
            if(!hn.equals("")) 
                return theHosDB.theVisitPatientDB.selectLocking(hn);
            
            if(in_process)
                result_loc = theHosDB.theVisitPatientDB.selectLocking(VisitStatus.isInProcess(),VisitStatus.isInStat()); 
            else
                result_loc = theHosDB.theVisitPatientDB.selectLocking(VisitStatus.isOutProcess(),VisitStatus.isDropVisit());           
            theUS.setStatus("การเรียกดูใหม่เสร็จสิ้น",UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        theConnectionInf.close();
        return result_loc;
    }

    public boolean commitVisitSurvey(UpdateStatus theUS) {
        theConnectionInf.open();
        if(theHO.theVisit==null)
        {
            theUS.setStatus("ยังไม่ได้เลือกผู้ป่วย",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.isLockingByOther()){
            theUS.setStatus("ผู้ป่วยถูกล้อกโดยผู้ใช้คนอื่น",UpdateStatus.WARNING);
            return false;
        }
        Constant.println(UseCase.UCID_commitVisitSurvey);
        String objectid =   null;
        if(theHO.theVisit != null)
        {
            objectid = theHO.theVisit.getObjectId();
        }
        try{
            objectid = theHO.theVisit.getObjectId();
            Visit visit = theHO.theVisit;
            intUnlockVisit(visit);
            visit.is_discharge_doctor = "1";
            if(visit.doctor_discharge_time.equals(""))
                visit.doctor_discharge_time = theHO.date_time;
            ////////////////////////////////////////////////////////////////////////
            visit.doctor_discharge_user = theHO.theEmployee.getObjectId();
            visit.visit_status = VisitStatus.isOutProcess();
            theHosDB.theVisitDB.updateDischargeDoctor(visit);
            theHO.clearFamily();
            ///////////////////////////////////////////////////////////////////
            theSystemControl.setStatus(UseCase.TH_commitVisitSurvey,UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_commitVisitSurvey,objectid, null, UpdateStatus.COMPLETE);
            return true;
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_commitVisitSurvey,UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_commitVisitSurvey, objectid, ex,UpdateStatus.ERROR);
            return false;
        } finally {
            theConnectionInf.close();
        }
    }

    public boolean dropVisitSurvey(UpdateStatus theUS) {
        Constant.println(UseCase.UC_dropVisitSurvey);
        if(theHO.theVisit==null)
        {
            theUS.setStatus("ยังไม่ได้เลือกผู้ป่วย",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.isLockingByOther()){
            theUS.setStatus("ผู้ป่วยถูกล้อกโดยผู้ใช้คนอื่น",UpdateStatus.WARNING);
            return false;
        }
        String objectid =   null;
        if(theHO.theVisit != null)
            objectid = theHO.theVisit.getObjectId();
        //amp:3/6/2549 เพ่มในกรณีค้างบันทึกก็ห้าม Drop Visit
        if(theHO.theVisit.visit_status.equals("2"))
        {
            theUS.setStatus("ผู้ป่วยอยู่ในสถานะค้างบันทึกแล้ว",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals("3"))
        {
            theUS.setStatus("ผู้ป่วยอยู่ในสถานะจบกระบวนการแล้ว",UpdateStatus.WARNING);
            return false;
        }
        if(!theHO.theVisit.visit_type.equals(VisitType.SURVEY))
        {
            theUS.setStatus(Constant.getTextBundle("ไม่สามารถยกเลิกได้")+" "+
                    Constant.getTextBundle("กรุณาตรวจสอบว่าเป็นข้อมูลสำรวจ"),UpdateStatus.WARNING);
            return false;
        }
        String cause = DialogCause.showDialog(theUS.getJFrame(),Constant.getTextBundle("การยกเลิกการเข้ารับบริการ"));
        if(cause.equals(""))
        {
            theUS.setStatus("กรุณากรอกสาเหตุการยกเลิกการเข้ารับบริการ",UpdateStatus.WARNING);
            return  false;
        }
        else if(cause.equals("CancelDialog"))
            return false;

        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            theHO.theVisit.visit_status = VisitStatus.isDropVisit();
            theHO.theVisit.financial_discharge_time = date_time;
            theHO.theVisit.financial_discharge_user = theHO.theEmployee.getObjectId();
            theHO.theVisit.locking = Active.isDisable();
            theHO.theVisit.is_discharge_doctor = Active.isEnable();
            theHO.theVisit.is_discharge_money = Active.isEnable();
            theHO.theVisit.stat_dx = cause;
            theHosDB.theVisitDB.update(theHO.theVisit);
            //ทำการลบรายการจากคิวผู้ป่วย
            //////////////////////////////////////////////////////////////////////
            intUnlockVisit(theHO.theVisit);
            theHO.clearFamily();
            ///////////////////////////////////////////////////////////////////
            theSystemControl.setStatus(UseCase.TH_dropVisitSurvey,UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_dropVisitSurvey,objectid, null, UpdateStatus.COMPLETE);
            return true;
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_dropVisitSurvey,UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_dropVisitSurvey, objectid, ex,UpdateStatus.ERROR);
            return false;
        } finally {
            theConnectionInf.close();
        }
    }

    public boolean visitSurvey(Family family,String note,UpdateStatus theUS)
    {
        Constant.println(UseCase.UC_visitSurvey);
        String objectid =   null;
        if(theHO.theVisit != null)
            objectid = theHO.theVisit.getObjectId();
        theConnectionInf.open();
        try{
            theLookupControl.intReadDateTime();
//            thePatientControl.intReadFPV(family.getObjectId(),VisitType.SURVEY);
            if(theHO.theFamily==null){
                theUS.setStatus("กรุณาเลือกประชากร",UpdateStatus.WARNING);
                return false;
            }
            if(theHO.theVisit!=null && theHO.theVisit.visit_type.equals(VisitType.SURVEY)){
                theUS.setStatus("ประชากรอยู่ในกระบวนการสำรวจแล้ว",UpdateStatus.WARNING);
                return false;
            }
            if(theHO.theVisit!=null && !theHO.theVisit.visit_type.equals(VisitType.SURVEY)){
                theUS.setStatus("ประชากรอยู่ในกระบวนการของสถานพยาบาลกรุณาค้างบันทึกแล้วนำเข้าสู่การสำรวจอีกครั้ง",UpdateStatus.WARNING);
                return false;
            }
            String db_year = theHosDB.theVisitYearDB.selectCurrenctYear();
            if(!db_year.equals(theHO.date_time.substring(2,4)))  {
                boolean ret = theUS.confirmBox(Constant.getTextBundle("พบเลขปีของ VN ไม่เป็นปัจจุบันควรตรวจสอบเลขลำดับ")+" "+
                    Constant.getTextBundle("ยืนยันการใช้งานต่อ"),UpdateStatus.WARNING);
                if(!ret) return false;
            }
            Visit visit = HosObject.initVisit();
            if(theHO.thePatient!=null){
                visit.patient_id = theHO.thePatient.getObjectId();
                visit.hn = theHO.thePatient.hn;
            }
                int ret = thePatientControl.intSavePatient(theHO.theFamily,theHO.date_time,false);
            theHosDB.theChronicDB.updatePtidByFid(theHO.thePatient.getObjectId(),theHO.theFamily.getObjectId());
            theHosDB.theSurveilDB.updatePtidByFid(theHO.thePatient.getObjectId(),theHO.theFamily.getObjectId());
            theHosDB.theDeathDB.updatePtidByFid(theHO.thePatient.getObjectId(),theHO.theFamily.getObjectId());
            theHosDB.thePatientPaymentDB.updatePtidByFid(theHO.thePatient.getObjectId(),theHO.theFamily.getObjectId());
            thePatientControl.intReadPatientSuit(theHO.thePatient);

            visit.vn = theHosDB.theSequenceDataDB.updateSequence("vn_survey",true);
            visit.begin_visit_time = theHO.date_time;
            visit.patient_age = DateUtil.calculateAgeShort1(theHO.theFamily.patient_birthday,visit.begin_visit_time);
            visit.visit_note = Gutil.CheckReservedWords(note);
            visit.visit_status=VisitStatus.isInProcess();
            visit.visit_type = VisitType.SURVEY;
            visit.locking = "1";
            visit.lock_user = theHO.theEmployee.getObjectId();
            visit.lock_time = theHO.date_time;
            visit.patient_id = theHO.thePatient.getObjectId();
            theHosDB.theVisitDB.insert(visit);
            theHO.setVisit(visit);
            theHO.initVisitExt();
            theHO.vVisitPayment = new Vector();
            ///////////////////////////////////////////////////////////////////
            theSystemControl.setStatus(UseCase.TH_visitSurvey,UpdateStatus.COMPLETE, null);
            theSystemControl.saveLog(UseCase.UCID_visitSurvey,objectid, null, UpdateStatus.COMPLETE);
            return true;
        } catch (Exception ex) {
            theSystemControl.setStatus(UseCase.TH_visitSurvey,UpdateStatus.ERROR, ex);
            theSystemControl.saveLog(UseCase.UCID_visitSurvey, objectid, ex,UpdateStatus.ERROR);
            return false;
        } finally {
            theConnectionInf.close();
        }
    }
    public Vector listVisitSurvey(){
        try{
            Vector vret = new Vector();
            String sql = "select f_patient_prefix.patient_prefix_description" +
                    ",patient_name" +
                    ",patient_last_name" +
                    ",visit_patient_age" +
                    ",t_health_family.t_health_family_id " +
                    " from t_visit " +
                    " inner join t_patient on t_patient.t_patient_id = t_visit.t_patient_id" +
                    " inner join t_health_family on t_health_family.t_health_family_id = t_patient.t_health_family_id" +
                    " inner join f_patient_prefix on f_patient_prefix.f_patient_prefix_id = f_prefix_id" +
                    " where f_visit_type_id = 'S' and f_visit_status_id = '1'" +
                    " order by patient_name,patient_last_name ";
            ResultSet rs = theConnectionInf.eQuery(sql);
            while(rs.next()){
                String[] str = new String[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
                };
                vret.add(str);
            }
            rs.close();
//            theSystemControl.setStatus(UseCase.TH_listVisitSurvey,UpdateStatus.COMPLETE, null);
//            theSystemControl.saveLog(UseCase.UCID_listVisitSurvey,objectid, null, UpdateStatus.COMPLETE);
            return vret;
        }
        catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
//            theSystemControl.setStatus(UseCase.TH_listVisitSurvey,UpdateStatus.ERROR, ex);
//            theSystemControl.saveLog(UseCase.UCID_listVisitSurvey, objectid, ex,UpdateStatus.ERROR);
            return null;
        }
    }

    public static void main(String[] argc){
        Patient pt = new Patient();
        pt.setObjectId("henbe");
        String id = pt.getObjectId();
        pt=null;
        System.out.println("oid is "+ id);
    }
}
