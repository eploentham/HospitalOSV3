/*
 * LabReferControl.java
 *
 * Created on 22 พฤษภาคม 2547, 09:34 น.
 */
package com.hosv3.control;
import com.hosv3.object.*;
import com.hosv3.subject.*;
import com.hosv3.utility.connection.*;
//import com.hosv3.utility.*;

import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.object.*;
import com.hospital_os.utility.Gutil;
import com.hosv3.utility.Constant;
import com.hospital_os.utility.Secure;
import com.hospital_os.utility.ComboFix;
import java.sql.ResultSet;
import java.util.*;
import com.hospital_os.object.QueueLabStatus;
import com.hosv3.utility.GuiLang;


/**
 *
 * @author  Amp
 */
public class ResultControl {
    
    ConnectionInf theConnectionInf;
    HosDB theHosDB;
    HosObject theHO;
    HosSubject theHS;
    UpdateStatus theUS;
    String theStatus;
    LookupControl theLookupControl;
    VisitControl theVisitControl;
    OrderControl theOrderControl;
    SystemControl theSystemControl;
    /** Creates a new instance of BillingControl */
    
    public ResultControl(ConnectionInf c, HosObject ho, HosDB hdb, HosSubject hs) {
        theConnectionInf = c;
        theHosDB = hdb;
        theHO = ho;
        theHS = hs;
    }
    public void setSystemControl(SystemControl systemControl)
    {
        theSystemControl = systemControl;
    }
    public void setDepControl(LookupControl lc,VisitControl vc,OrderControl oc){
        theLookupControl = lc;
        theVisitControl = vc;
        theOrderControl = oc;
    }
    public void setUpdateStatus(UpdateStatus us){
        theUS = us;
    }
  
    /**
     * ตรวจสอบว่าเคยมี pid ขงผู้ป่วยคนนี้ในฐานข้อมูลแล้วหรือยัง
     * จัดเอกสารรายละเอียด uc แล้ว 27/11/47 
     */
    public boolean checkPidPatientLabreferin(String pid) 
    {    
       theConnectionInf.open();
       boolean result=true;
       try
       {     
           Vector answer = null;          
           answer = theHosDB.thePatientLabreferinDB.queryPid(pid);           
           theConnectionInf.close();
           if(answer == null || answer.size()==0)
           {
              result = false;
           }
           else
           {
              result = true;           
           }
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
     * เพิ่มข้อมูลผู้ป่วยที่มีการส่ง labreferin
     * จัดเอกสารรายละเอียด uc แล้ว 27/11/47 
     */
    public void savePatientLabreferin(PatientLabreferin p) 
    {
        if(p==null){
            theUS.setStatus("ข้อมูลผิดพลาด",UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{                                       
            if(p.getObjectId()==null)               
                    theHosDB.thePatientLabreferinDB.insert(p);                    
            else    theHosDB.thePatientLabreferinDB.update(p);  
            theHS.theResultSubject.notifyManagePatientLabReferIn(Constant.getTextBundle("การนำเข้าข้อมูลแลบ") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),com.hosv3.utility.connection.UpdateStatus.COMPLETE);
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การนำเข้าข้อมูลแลบ") + " " +
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
        } 
        finally {
            theConnectionInf.close();
        }
    } 
    /**
     * ค้นหาผู้ป่วยจากชื่อหรือนามสกุล
     * จัดเอกสารรายละเอียด uc แล้ว 27/11/47 
     */
    public Vector listPatientLabreferinByName(String fname, String lname) 
    {       
       Vector result = new Vector();
       theConnectionInf.open();
       try{
           if((!fname.equals("")) && (!lname.equals("")))
                result= theHosDB.thePatientLabreferinDB.queryByFLName("%" + fname + "%","%" + lname + "%"); 
           else if(!fname.equals(""))
                result= theHosDB.thePatientLabreferinDB.queryByFName("%" + fname + "%");           
           else if(!lname.equals(""))
                result= theHosDB.thePatientLabreferinDB.queryBySName("%" + lname + "%");                  
        }
       catch(Exception e)
       {
           e.printStackTrace(Constant.getPrintStream());
       }
       finally
       {
            theConnectionInf.close();
       }
       return result;
    }
    
    /**
     * ลบผู้ป่วยออกจากฐานข้อมูล
     * จัดเอกสารรายละเอียด uc แล้ว 27/11/47 
     */
    public void deletePatientLabreferIn(PatientLabreferin patientLabreferin)
    {
        if(patientLabreferin==null)
        {
            theUS.setStatus("patientLabreferin=null",UpdateStatus.WARNING);
        }
        theConnectionInf.open();
        try
        {  
             theHosDB.thePatientLabreferinDB.delete(patientLabreferin);            
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
     * ใช้ในการแสดงประวัติ XN ของผู้ป่วย
     * @param patient_id เป็น เลข patient_id ของผู้ป่วย
     * @param showinactive เป็น boolean ที่กำหนดว่าให้แสดงหรือไม่(แสดงผลที่เป็น active ด้วย)
     * @return เป็น Vector ของ Object PatientXN
     * @author padungrat(tong)
     * @date 18/04/2549,11:12
     */
     public Vector listPatientXnByPatientID(String patient_id,boolean showinactive)
    {
        Constant.println(UseCase.UCID_showHistoryXN);
        String objectid =   null;
        objectid = patient_id;
        theConnectionInf.open();
        try{
            theSystemControl.setStatus(UseCase.TH_showHistoryXN,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_showHistoryXN,objectid,null,UpdateStatus.COMPLETE);
            return theHosDB.thePatientXNDB.selectByPatientID(patient_id, showinactive);
        }
        catch(Exception ex) {
            theSystemControl.setStatus(UseCase.TH_showHistoryXN,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_showHistoryXN,objectid,ex,UpdateStatus.ERROR);
            return new Vector();
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    /**
     * @return เป็น boolean ถ้า เป็น true แสดงว่าค่าซ้ำ ถ้าเป็น false ไม่ซ้ำ
     * @author padungrat(tong)
     * @date 04/04/2549,11:30
           //ตรวจสอบว่าค่า str เป็นค่าว่างหรือไม่
                //ไม่เป็นค่าว่าง
                //ตรวจสอบว่าค่า str กับค่าใน object ของ patient เป็นค่าเดียวกันหรือไม่
                    // ไม่เป็นค่าเดียวกับ
                    // ทำการ update ลงตาราง t_patient_xn ของเลข xn ใน object ของ patient ให้ active เป็น 0
                //จบการตรวจสอบ
                //ตรวจสอบค่า str ว่ามีอยู่ใน t_patient_xn หรือไม่
                    // มีอยู่
                    // ตรวจสอบปี พ.ศ. ว่าเป็นน้อยกว่าหรือเป็นปีปัจจุบันหรือไม่
                        // ไม่ใช้ ให้สร้างเลข xn ใหม่ และทำการ insert ข้อมูลลงตาราง t_patient_xn
                        // และเพิ่มลงใน object ของ patient
                    // ไม่มีอยู่    
                        //ตรวจสอบว่าค่า str กับค่าใน object ของ patient เป็นค่าเดียวกันหรือไม่
                        // ไม่เป็นค่าเดียวกับ
                        // ทำการ update ลงตาราง t_patient_xn ของเลข xn ใน object ของ patient ให้ active เป็น 0
                //เป็นค่าว่าง
                // สร้างเลข xn ใหม่
     *
     * เปลี่ยนการทำงานของ XN โดยมีแนวความคิดว่า
     * เมื่อสิ้นปีเลข xn ของผู้ป่วยจะถูก set เป็นค่าว่างทั้งหมดเพื่อรอการบันทึกใหม่
     * ในกรณึที่เข้ามารับบริการการ xray 
     *     ซึ่งการบันทึกจากหน้า xray จะ gen เลขใหม่ให้
     *     ส่วนการบันทึกจากหน้า ผู้ป่วย จะไม่ทำให้
     *
     * หากต้องการเปลี่ยนแปลงเลขเองก็ทำได้โดยการเปลี่ยนเลข โปรแกรมจะจดจำทุกเลขที่
     * มีการเปลี่ยนแปลง หากเปลี่ยนเป็นค่าว่างก็จะ
     *     การบันทึกจากหน้า xray จะ gen เลขใหม่ให้
     *     การบันทึกจากหน้า ผู้ป่วย จะไม่ทำให้
     */
    public boolean savePatientXn(String xn,boolean gen)
    {
        Constant.println(UseCase.UCID_savePatientXn);
        String objectid =   null;
        String year_now = this.theLookupControl.getTextCurrentDate();
        boolean bresult = false;
            year_now = year_now.substring(0, 4);
        //ตรวจสอบว่าเลข Xn เป็นค่าว่างหรือไม่ ถ้าไม่เป็น
        if(theHO.thePatient==null)
            return false;
            
        theConnectionInf.open();
        try {
            //ถ้าไม่เป็นค่าว่างก็จะเช็คซ้ำ
            bresult = intCheckSamePatientXN(xn);
            if(!xn.equals("") && bresult){
                theUS.setStatus("เลข XN นี้ซ้ำกับผู้ป่วยคนอื่นแล้ว",UpdateStatus.WARNING);
                return bresult;
            }
            //ถ้าเป็นค่าว่างจะเช็คเมื่อมีการ generate
            if(xn.equals("") && gen)
            {
                xn = theHosDB.theSequenceDataDB.updateSequence("xn",true);
                bresult = intCheckSamePatientXN(xn);
                if(!xn.equals("") && bresult) {
                    theUS.setStatus("เลข XN นี้ซ้ำกับผู้ป่วยคนอื่นแล้ว",UpdateStatus.WARNING);
                    return bresult;
                }
            }
            //ถ้า xn ไม่เป็นค่าว่าง และ เลข xn เดิมยังไม่มี หรือ หากมีแล้วก็ต้องไม่ซ้ำกับ xn นี้
            if(!xn.equals("") && (theHO.thePatientXN==null || !theHO.thePatientXN.patient_xray_number.equals(xn)))
            {
                    theHosDB.thePatientXNDB.updateActiveByPtid(theHO.thePatient.getObjectId(),"0");
                    PatientXN patientxn = new PatientXN();
                    patientxn.patient_xn_active = Active.isEnable();
                    patientxn.patient_xn_year = year_now;
                    patientxn.patient_xray_number  = xn;
                    patientxn.t_patient_id = theHO.thePatient.getObjectId();
                    theHosDB.thePatientXNDB.insert(patientxn);
                    theHO.thePatientXN = (PatientXN)patientxn.clone();
            }
            //เมื่อไม่ซ้ำก็ set ให้กับผู้ป่วย
            theHO.thePatient.xn = xn;
            theHosDB.thePatientDB.updateXN(theHO.thePatient);
            if(theHO.thePatient != null)
                objectid = theHO.thePatient.getObjectId();
            theSystemControl.setStatus(UseCase.TH_savePatientXn,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_savePatientXn,objectid,null,UpdateStatus.COMPLETE);
            return bresult;       
        }
        catch(Exception ex){
             ex.printStackTrace(Constant.getPrintStream());
             theSystemControl.setStatus(UseCase.TH_savePatientXn,UpdateStatus.ERROR,ex);
             theSystemControl.saveLog(UseCase.UCID_savePatientXn,objectid,ex,UpdateStatus.ERROR);
             return bresult;
        }
        finally {
             theConnectionInf.close();
        }
    }
    
    /**
     * ใช้ในการตรวจสอบว่า เลข xn ที่ได้นั้นมาการซ้ำกันหรือไม่ ถ้าซ้ำกันจะต้องให้ค่าเป็น true ถ้าไม่ซ้ำกันจะให้ค่าเป็น false
     * @param patient_xn เป็น String ของ เลข xn ที่ต้องการค้นหา
     * @return เป็น bolean ถ้าซ้ำกันจะต้องให้ค่าเป็น true ถ้าไม่ซ้ำกันจะให้ค่าเป็น false
     * @author padungrat(tong)
     * @date 21/04/2549,11:30
     */
    private boolean intCheckSamePatientXN(String patient_xn)throws Exception
    {
        boolean result = false;
        Vector vhn;
            vhn = this.theHosDB.thePatientDB.checkSamePatientXn(patient_xn, this.theHO.thePatient.getObjectId());
            this.theHO.vSameXN = vhn;
            //ซ้ำกัน
            if(vhn != null && vhn.size() >0)
            {
                result = true;
            }
        return result;
    }
    
     /**
     *ใช้ในการสร้างxn ให้กับpatient
     *ตรวจสอบดูว่าในObject patient มีxn หรือไม่ ถ้าไม่มีก็ให้ทำการ gen ให้
     *เมื่อได้ xn ก็จะทำ save
     * @deprecated henbe unused when used ?
     **/
    public String savePatientXn1(String textxn)
    {   
        String xn = textxn;
        if(!xn.equals(""))
        {
            theConnectionInf.open();
            try
            {
                theHO.thePatient.xn = xn;
                theHosDB.thePatientDB.updateXN(theHO.thePatient);
            }
            catch(Exception ex){
                ex.printStackTrace(Constant.getPrintStream());
            }
            finally{
                theConnectionInf.close();
            }
        }
        else if(theHO.thePatient.xn.trim().length() == 0)
        {  
            theConnectionInf.open();
            try
            {
                xn = theHosDB.theSequenceDataDB.updateSequence("xn",true);
                theHO.thePatient.xn = xn;
                theHosDB.thePatientDB.updateXN(theHO.thePatient);
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
        else
        {   
            xn = theHO.thePatient.xn;
        }
      
         return xn;       
    }
    /**
     * บันทึกข้อมูลการเข้ารับบริการของผู้ป่วย
     * จัดเอกสารรายละเอียด uc แล้ว 29/11/47 
     */
   public void saveVisitPatientLabreferin(VisitLabreferin visitLabreferin)
    {
        if(visitLabreferin==null){
            theUS.setStatus("vistiLabreferin=null",UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try {
            visitLabreferin.begin_visit_time = theLookupControl.intReadDateTime();
            theHosDB.theVisitLabreferinDB.insert(visitLabreferin);
            theUS.setStatus(Constant.getTextBundle("การนำเข้าข้อมูลแลบ") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex) {    
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การนำเข้าข้อมูลแลบ") + " " +
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
        }
        finally {
            theConnectionInf.close();      
        }
    }
    
    /**
     * ตรวจสอบว่าผู้ป่วยเคยเข้ารับบริการแล้วหรือยัง เพื่อนำไปใช้ประโยชน์ในการลบข้อมูลผู้ป่วยอีกทีหนึ่ง
     * จัดเอกสารรายละเอียด uc แล้ว 29/11/47 
     */
    public boolean checkPatientInTableVisit(String patientLabreferinId) 
    {    
       if(patientLabreferinId==null)
        {
            theUS.setStatus("patientLabreferinId=null",UpdateStatus.WARNING);
        }
       theConnectionInf.open();
       boolean result=true;
       try{     
           Vector answer = null;          
           answer = theHosDB.theVisitLabreferinDB.queryPid(patientLabreferinId);           
           theConnectionInf.close();
           if(answer == null || answer.size()==0)
           {
              result = false;
           }
           else
           {
              result = true;           
           }
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
     * แสดงรายชื่อผู้ป่วยที่กำลังเข้ารับบริการ
     * จัดเอกสารรายละเอียด uc แล้ว 29/11/47 
     */
    public Vector listVisitLabreferin() 
    {
        Vector result = new Vector();
        theConnectionInf.open();
        try
        {
            result= theHosDB.theVisitLabreferinDB.queryVisitInProcess();                         
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    
    /**
     * ค้นหาข้อมูลผู้ป่วยจาก Primary Key
     * จัดเอกสารรายละเอียด uc แล้ว 29/11/47 
     */
    public PatientLabreferin readPatientLabByPk(String plriid) 
    {
        if(plriid==null)
        {
            theUS.setStatus("plriid=null",UpdateStatus.WARNING);
        }
        PatientLabreferin p=null; 
        theConnectionInf.open();
        try
        {
           p = theHosDB.thePatientLabreferinDB.selectByPK(plriid);           
        }
        catch(Exception e)
        {    
            e.printStackTrace(Constant.getPrintStream());
        } 
        finally
        {
            theConnectionInf.close();
        }
        return p;
    }
    
    /**
     * ค้นหาข้อมูลการรับบริการครั้งล่าสุดที่ยังมีสถานะเข้ารับบริการอยู่
     * จัดเอกสารรายละเอียด uc แล้ว 29/11/47 
     */
    public VisitLabreferin readVisitInProcessByPatientId(String plriid) 
    {
        if(plriid==null)
        {
            theUS.setStatus("plriid=null",UpdateStatus.WARNING);
        }
        VisitLabreferin v=null; 
        theConnectionInf.open();
        try
        {
           v = theHosDB.theVisitLabreferinDB.selectVisitByPId(plriid);           
        }
        catch(Exception e)
        {    
            e.printStackTrace(Constant.getPrintStream());
        } 
        finally
        {
            theConnectionInf.close();
        }
        return v;
    }
    /**
     * ค้นหาข้อมูลการรับบริการทั้งหมดของผู้ป่วย
     * จัดเอกสารรายละเอียด uc แล้ว 29/11/47 
     */
    public Vector listVisitLabreferinByPId(String pid) 
    {
        if(pid==null)
        {
            theUS.setStatus("pid=null",UpdateStatus.WARNING);
        }
        Vector result = new Vector();
        theConnectionInf.open();
        try
        {
            result= theHosDB.theVisitLabreferinDB.queryVisitInProcessByPid(pid);                         
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    
    /**
     * ค้นหารายการ order จากการคลิกเลือกครั้งการเข้ารับบริการที่ต้องดู
     * จัดเอกสารรายละเอียด uc แล้ว 29/11/47 
     */
    public Vector listOrderByVisitId(String VId) 
    {
        if(VId==null)
        {
            theUS.setStatus("VId=null",UpdateStatus.WARNING);
        }
        Vector result = new Vector();
        theConnectionInf.open();
        try
        {
            result= theHosDB.theOrderItemLabreferinDB.queryOrderByVid(VId);                         
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    
    /**
     * ลบรายการ order 
     * จัดเอกสารรายละเอียด uc แล้ว 29/11/47 
     */
    public void deleteOrderItemLabReferIn(OrderItemLabreferin orderItemLabreferin) 
    {
        if(orderItemLabreferin==null)
        {
            theUS.setStatus("orderItemLabreferin=null",UpdateStatus.WARNING);
        }
        theConnectionInf.open();
        try
        {  
             theHosDB.theOrderItemLabreferinDB.delete(orderItemLabreferin);             
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
     * บันทึกรายการ order 
     * จัดเอกสารรายละเอียด uc แล้ว 30/11/47 
     */
    public void saveOrderItemLabReferin(Vector orderItemLabreferin) 
    {
        if(orderItemLabreferin==null)
        {
            theUS.setStatus("orderItemLabreferin=null",UpdateStatus.WARNING);
        }
        theConnectionInf.open();
        try
        {  
            if(orderItemLabreferin != null) {   
                for(int i=0 ; i<orderItemLabreferin.size(); i++) {   
                    OrderItemLabreferin olr = (OrderItemLabreferin)orderItemLabreferin.get(i);
                    if(olr.getObjectId() == null)
                            theHosDB.theOrderItemLabreferinDB.insert(olr);
                    else    theHosDB.theOrderItemLabreferinDB.update(olr);
                }
            }
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
     * แสดงผล lab ตามรายการ order 
     * จัดเอกสารรายละเอียด uc แล้ว 30/11/47 
     * ยังไม่ได้ใช้งาน
     */
    public OrderResultLabreferin readResultLabReferinByOrderID(OrderResultLabreferin resultlab) 
    {
        if(resultlab==null)
        {
            theUS.setStatus("resultlab=null",UpdateStatus.WARNING);
        }
        OrderResultLabreferin rl = new OrderResultLabreferin();
        theConnectionInf.open();
        try{
            rl = theHosDB.theOrderResultLabreferinDB.selectByOrderItem(resultlab);
        }
        catch(Exception ex)
        {   
            ex.printStackTrace(Constant.getPrintStream());
            rl = null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return rl;
    }
    
    /**
     * แสดงผล lab ตามรายการ order 
     * จัดเอกสารรายละเอียด uc แล้ว 30/11/47   
     */
    public OrderItemLabreferin readOrderItemByItemIdAndVisitId(String itemID, String visitID) 
    {
        
        OrderItemLabreferin oilri = new OrderItemLabreferin();
        theConnectionInf.open();
        try
        {
            oilri = theHosDB.theOrderItemLabreferinDB.selectByItemIDAndVID(itemID,visitID);
        }
        catch(Exception ex)
        {   
            ex.printStackTrace(Constant.getPrintStream());
            oilri = null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return oilri;
    }    
    
    public String updateOrderItemLabreferin(OrderItemLabreferin orderItemLabreferin) 
    {
        if(orderItemLabreferin==null)
        {
            theUS.setStatus("orderItemLabreferin=null",UpdateStatus.WARNING);
        }
        theConnectionInf.open();
        try
        {         
            theHosDB.theOrderItemLabreferinDB.update(orderItemLabreferin);
            theStatus = "Complete";
        }
        catch(Exception ex)
        {    
           ex.printStackTrace(Constant.getPrintStream());
           theStatus =  "Error";
        }      
        finally
        {
            theConnectionInf.close();
        }
        return theStatus;
    }    
    
    public void saveOrderResultLabrefrin(OrderResultLabreferin p) 
    {
        if(p==null) {
            theUS.setStatus("OrderResultLabreferin=null",UpdateStatus.WARNING);
        }
        theConnectionInf.open();
        try{
            if(p.getObjectId() == null) 
                    theHosDB.theOrderResultLabreferinDB.insert(p);
            else    theHosDB.theOrderResultLabreferinDB.update(p);
        }
        catch(Exception ex){    
            ex.printStackTrace(Constant.getPrintStream());           
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    
    public void deleteOrderResultLabReferIn(String oilriId) 
    {
        if(oilriId==null)
        {
            theUS.setStatus("oilriId=null",UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try
        {  
             theHosDB.theOrderResultLabreferinDB.deleteById(oilriId);             
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
 
    ///////////////////////////////////////////////////////////////////////////////
    public boolean checkVisitInResult(String visitLabreferinId) 
    {
        theConnectionInf.open();
        boolean result=true;
        try
        {     
           Vector answer = null;          
           answer = theHosDB.theOrderResultLabreferinDB.queryVid(visitLabreferinId);           
           theConnectionInf.close();
           if(answer == null || answer.size()==0)
           {
               result = false;
           }
           else
           {
               result = true;           
           }
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
    
    public Vector listResultByVId(String vId) 
    {
        Vector result = new Vector();
        theConnectionInf.open();
        try
        {
            result= theHosDB.theOrderResultLabreferinDB.queryresultByVid(vId);                         
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;    
    }
    ///////////////////////////////////////////////////////////////////////////
    /**
     *@Author : henbe pongtorn
     *@date : 18/03/2549
     *@see : ค้นผลแลบรูปแบบใหม่ให้มีการค้นหาเร็วขึ้น
     */    
    public Vector listResultLabByOid(Vector v_oid) 
    {
        Constant.println("_________________public Vector listResultLabByOid(Vector v_oid)");
        Vector result = new Vector();
        if(v_oid==null || v_oid.isEmpty())
        {
            return result;
            
        }
        theConnectionInf.open();
        try{
            if(v_oid.size()>0) {
                String sql =  "select * from t_result_lab "
                + " left join t_order on t_order.t_order_id = t_result_lab.t_order_id "
                + " where (";
                //OrderItem oi = (OrderItem)v_oid.get(0);
                //sql+=" t_order.t_visit_id = '" + oi.visit_id +"' "
                for(int i=0;i<v_oid.size();i++){
                    OrderItem oi = (OrderItem)v_oid.get(i);
                    sql+=" t_order.t_order_id = '" + oi.getObjectId() +"' or";
                }
                sql = sql.substring(0,sql.length()-2) + ") ";
                sql+= " and f_item_group_id = '2' "
                //+ " and (f_order_status_id = '1' "
                //+ " or f_order_status_id = '2'  "
                //+ " or f_order_status_id = '4' "
                //+ " or f_order_status_id = '6') "
//                + " and order_secret <> '1' "
                 //--and b_item_lab_result_id <> '' and result_lab_active = '1' 
                + " order by "
                + " order_date_time,order_common_name "
                + " ,result_lab_index";
                ResultSet rs = theConnectionInf.eQuery(sql);
                result = theHosDB.theResultLabDB.eQuery(rs);
                if(!theHO.orderSecret.equals(""))
                {
                    for(int j=0;j<result.size();j++)
                    {
                        ResultLab res = (ResultLab)result.get(j);    
                        Constant.println("res.result Before : " + res.result);
                        res.result = Secure.decode(res.result);
                        Constant.println("res.result After : " + res.result);
                    }
                }
            }
            else{
            for(int i=0,size=v_oid.size();i<size;i++)
            {
                OrderItem oi = (OrderItem)v_oid.get(i);
//                if(oi.status.equals(OrderStatus.REMAIN)
//                || oi.status.equals(OrderStatus.EXECUTE)
//                || oi.status.equals(OrderStatus.VERTIFY)
//                || oi.status.equals(OrderStatus.REPORT))
//                {
                    Vector v = theHosDB.theResultLabDB.selectNewByOidActive(oi.getObjectId(),Active.isEnable());//ตอนยืนยันรายการจะมีการบันทึกผลแลบไว้ให้ก่อนแล้วกรณีนี้หมายความว่าหากผลแลบยัง
                    //มีบางรายการที่ยังไม่ได้บันทึกผลก็แสดงว่ายังมีข้อมูลแลบเก่าหลงเหลืออยู่
                    //รายงานผลก็จะให้ค่าเป็นค่าว่างไปเลย
                    if(v.isEmpty())
                    {
                        Constant.println("_______________________________oi.name" + oi.common_name);
                        return new Vector();
                    }                    
                    for(int j=0;j<v.size();j++)
                    {
                        ResultLab rs = (ResultLab)v.get(j);
                        if(oi.secret.equals(Active.isEnable()))
                            rs.result = Secure.decode(rs.result);
                        result.add(v.get(j));
                    }
             //   }
            }
            }
            return result;
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
            return result;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
//   public Vector listResultLabByOid(Vector v_oid) 
//    {
//        Constant.println("_________________public Vector listResultLabByOid(Vector v_oid)");
//        Vector result = new Vector();
//        if(v_oid==null || v_oid.isEmpty())
//        {
//            return result;
//            
//        }
//        theConnectionInf.open();
//        try{
//            for(int i=0,size=v_oid.size();i<size;i++)
//            {
//                OrderItem oi = (OrderItem)v_oid.get(i);
//                if(oi.status.equals(OrderStatus.REMAIN)
//                || oi.status.equals(OrderStatus.EXECUTE)
//                || oi.status.equals(OrderStatus.VERTIFY)
//                || oi.status.equals(OrderStatus.REPORT))
//                {
//                    Vector v = theHosDB.theResultLabDB.selectNewByOidActive(oi.getObjectId(),Active.isEnable());//ตอนยืนยันรายการจะมีการบันทึกผลแลบไว้ให้ก่อนแล้วกรณีนี้หมายความว่าหากผลแลบยัง
//                    //มีบางรายการที่ยังไม่ได้บันทึกผลก็แสดงว่ายังมีข้อมูลแลบเก่าหลงเหลืออยู่
//                    //รายงานผลก็จะให้ค่าเป็นค่าว่างไปเลย
//                    if(v.isEmpty())
//                    {
//                        Constant.println("_______________________________oi.name" + oi.common_name);
//                        return new Vector();
//                    }                    
//                    for(int j=0;j<v.size();j++)
//                    {
//                        ResultLab rs = (ResultLab)v.get(j);
//                        if(oi.secret.equals(Active.isEnable()))
//                            rs.result = Secure.decode(rs.result);
//                        result.add(v.get(j));
//                    }
//                }
//            }
//            return result;
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace(Constant.getPrintStream());
//            return result;
//        }
//        finally
//        {
//            theConnectionInf.close();
//        }
//    }    
    ///////////////////////////////////////////////////////////////////////////
    /**
     *@Author: tuk
     *@date: 07/08/2549
     *@see: ใช้สำหรับค้นหาผลแลปเพื่อไปแสดงในหน้าจอ Refer
     *@param: Vector ของ OrderItem
     *@return: Vector ของผลแลบ
     */
    public Vector listResultLabRefer(Vector v_oid) 
    {
        Constant.println("_________________public Vector listResultLabRefer(Vector v_oid)");
        Vector result = new Vector();
        OrderItem oi;
        Vector v;
        ResultLab rs;
        if(v_oid==null || v_oid.isEmpty())
        {
            return result;   
        }
        theConnectionInf.open();
        try{
            for(int i=0,size=v_oid.size();i<size;i++)
            {
                oi = (OrderItem)v_oid.get(i);
                if(oi.status.equals(OrderStatus.REPORT))//รายการ Lab ที่รายงานผลแล้วเท่านั้น
                {
                    v = theHosDB.theResultLabDB.selectNewByOidActive(oi.getObjectId(),Active.isEnable());
                    if(v != null)
                    {
                        for(int j=0;j<v.size();j++)
                        {
                        rs = (ResultLab)v.get(j);
                        if(oi.secret.equals(Active.isEnable()))
                            rs.result = Secure.decode(rs.result);
                        result.add(v.get(j));
                        }
                    }
                }
            }
            return result;
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
            return result;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    ///////////////////////////////////////////////////////////////////////////
    /*
     *@Author : henbe pongtorn
     *@date : 18/03/2549
     *@see : ค้นผลแลบรูปแบบใหม่ให้มีการค้นหาเร็วขึ้น เร็วที่สุด  ยังไม่เสร็จรอฟังชันใน db
     */    
    public Vector listResultLabByVid(String vid) 
    {
        Vector result = new Vector();
        if(vid==null || vid.equals(""))
        {
            return result;
        }
        theConnectionInf.open();
        try{
            return theHosDB.theResultLabDB.selectNewByVid(vid);
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
            return result;
        }
        finally
        {

            theConnectionInf.close();
        }
    }     
    public String updateVisitLabreferin(VisitLabreferin visitLabreferin)
    {
        theConnectionInf.open();
        try
        {         
            theHosDB.theVisitLabreferinDB.update(visitLabreferin);
            theStatus = "Complete";
        }
        catch(Exception ex)
        {    
           ex.printStackTrace(Constant.getPrintStream());
           theStatus =  "Error";
        }      
        finally
        {
            theConnectionInf.close();
        }
        return theStatus;
    }
    
    public boolean checkVisitInOrder(String visitLabreferinId) 
    {
        theConnectionInf.open();
        boolean result=true;
        try
        {     
           Vector answer = null;       
           answer = theHosDB.theOrderItemLabreferinDB.queryVid(visitLabreferinId);           
           theConnectionInf.close();
           if(answer == null || answer.size()==0)
           {
              result = false;
           }
           else
           {
              result = true; 
           }
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
    
    public OrderItemLabreferin readOrderItemByOrderItemIdAndVisitId(String orderItemID) 
    {
        OrderItemLabreferin oilri = new OrderItemLabreferin();
        theConnectionInf.open();
        try
        {
            oilri = theHosDB.theOrderItemLabreferinDB.selectByPK(orderItemID);
        }
        catch(Exception ex)
        {   
            ex.printStackTrace(Constant.getPrintStream());
            oilri = null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return oilri;
    }
    
    public Vector listLabSetByItemId(String itemId) 
    {
        Vector result = new Vector();
        theConnectionInf.open();
        try
        {
            result= theHosDB.theLabSetDB.selectByItemId(itemId);                         
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    
    public LabGroup readLabGroupByPk(String labGroupId) 
    {
        LabGroup p=null; 
        theConnectionInf.open();
        try
        {
           p = theHosDB.theLabGroupDB.selectByPK(labGroupId);           
        }
        catch(Exception e)
        {    
            e.printStackTrace(Constant.getPrintStream());
        } 
        finally
        {
            theConnectionInf.close();
        }
        return p;
    }
    
    public Vector listLabSetByLabGroupId(String lgId) 
    {
        Vector result = new Vector();
        theConnectionInf.open();
        try
        {
            result= theHosDB.theLabSetDB.selectByLabGroupID(lgId);                         
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    
    public boolean checkOrderItemIdInResult(String orderItemLabreferinId) 
    {
        theConnectionInf.open();
        boolean result=true;
        try
        {     
           Vector answer = null;          
           answer = theHosDB.theOrderResultLabreferinDB.queryOrderItemId(orderItemLabreferinId);           
           if(answer == null || answer.size()==0)
           {
              result = false;
           }
           else
           {
              result = true;           
           }
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
    
    public OrderItemLabreferin readOrderItemByPk(String pk) 
    {
        OrderItemLabreferin p=null; 
        theConnectionInf.open();
        try{
           p = theHosDB.theOrderItemLabreferinDB.selectByPK(pk);           
        }
        catch(Exception e)
        {    
            e.printStackTrace(Constant.getPrintStream());
        } 
        finally
        {
            theConnectionInf.close();
        }
        return p;
    }
    
    public LabGroup readLabGroupByItemId(String itemId) 
    {
        LabGroup p=null; 
        theConnectionInf.open();
        try{
           p = theHosDB.theLabGroupDB.selectByItemID(itemId);           
        }
        catch(Exception e)
        {    
            e.printStackTrace(Constant.getPrintStream());
        } 
        finally
        {
            theConnectionInf.close();
        }
        return p;
    }
 
 
     /**
     *@Author : henbe pongtorn
     *@date : 18/03/2549
     *บันทึกผลแลปเป็นฟังชันกลางที่ให้เรียกได้จากทุกหน้าจอ
     *ใช้บันทึกจากหน้าจอโปรแกรม 
     * จะทำการส่งผลทั้งหมดมาให้ในส่วนของ control ทำให้ต้องเช็คหลายอย่าง
     */
    public boolean saveResultLab(Vector voi,Vector vresultlab,UpdateStatus theUS)
    {
        int[] row = new int[voi.size()];
        for(int i=0;i<row.length;i++){
            row[i] = i;
        }
        return saveResultLab(voi,vresultlab,theUS,row);
    }
    public boolean saveResultLab(Vector voi,Vector vresultlab,UpdateStatus theUS,int[] rows)
    {
        Constant.println("public boolean saveResultLab(Vector voi,Vector vresultlab,UpdateStatus theUS)");
        Constant.println(UseCase.UCID_saveResultLab);
        String objectid =   null;
        if(theHO.theVisit == null){  
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return false;
        }
        /* tong ทำไมบอกรายละเอียดด้วยว่าเพราะอะไร มันก็ได้นี่หว่า henbe */
        if(voi==null || voi.isEmpty()) {
            theUS.setStatus("กรุณาเลือกข้อมูลแลบที่จะทำการบันทึก",UpdateStatus.WARNING);
            return false;
        }
        if(vresultlab==null || vresultlab.isEmpty()) {
            theUS.setStatus("ไม่มีข้อมูลผลแลบที่จะทำการบันทึก",UpdateStatus.WARNING);
            return false;
        }
        Vector vvresultlab = getVVResultLab(voi,vresultlab);
        theConnectionInf.open();  
        try{
            theHO.date_time = theLookupControl.intReadDateTime();
            /*บันทึกข้อมูลผลแลป เซตค่าให้กับresultlab*/
            int save = 0;
            for(int i=0;i<rows.length;i++)
            {  
                OrderItem oi = (OrderItem)voi.get(rows[i]);
                Vector vrl = (Vector)vvresultlab.get(rows[i]);
//                Constant.println(i + "____________oi" + oi.common_name + " and size__" + vrl.size());
                if(oi.status.equals(OrderStatus.REPORT))
                    continue;
                boolean is_ok = intCheckResultLab(oi,vrl, theUS, true);
                if(!is_ok)
                    continue;
                boolean ret = intSaveResultLab(oi,vrl, theUS);
                if(!ret)
                    return false;
                save++;
            }
            if(save==0){
                //theUS.setStatus("ไม่มีข้อมูลผลแลบที่จะทำการบันทึก",UpdateStatus.WARNING);
                return false;
            }
            //จัดการกับผลแลบในคิวว่าเป็นการรอผล รายงานผล หรือค้างผล
            //add comment by neung
            String status = refreshLabStatus(theHO.theVisit.getObjectId());
//            if(status.equals(QueueLabStatus.REPORT)
//            && theHO.theEmployee.authentication_id.equals(Authentication.LAB))
//            {
//                theVisitControl.intUnlockVisit(theHO.theVisit);
//                return true;
//            }
            theHS.theResultSubject.notifySaveLabResult(Constant.getTextBundle("การบันทึกผลชันสูตรโรค") + " " +
                    Constant.getTextBundle("เสร็จสิ้น")
                    ,UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveResultLab,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveResultLab,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex) {   
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveResultLab,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveResultLab,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }        
    }  
    
    protected String refreshLabStatus(String visit_id) throws Exception 
    {
        Vector patient_order = theHosDB.theOrderItemDB.selectByVisitId(visit_id);
        String status = OrderControl.checkLabStatus(patient_order);
        if(theHO.theListTransfer!=null){
            theHO.theListTransfer.labstatus = status;
            theHosDB.theQueueTransferDB.update(theHO.theListTransfer);
        }
        theHO.theVisit.queue_lab_status = status;
        theHosDB.theVisitDB.updateLabStatus(theHO.theVisit);  
        if(status.equals(QueueLabStatus.REPORT))
        {
              theHosDB.theQueueLabDB.deleteByVisitID(theHO.theVisit.getObjectId()); 
//            if(theHO.theEmployee.authentication_id.equals(Authentication.LAB))
//            {
//                theVisitControl.intUnlockVisit(theHO.theVisit);
//            }
        }
        else if(status.equals(QueueLabStatus.REMAIN))
        {
            theHosDB.theQueueLabDB.deleteByVisitID(theHO.theVisit.getObjectId(), "0"); 
//            if(theHO.theEmployee.authentication_id.equals(Authentication.LAB))
//            {
//                theVisitControl.intUnlockVisit(theHO.theVisit);
//            }
        }
        return status;
    }
    /**
     * ต้องเป็นผลแลบทั้งหมดที่จะทำการบันทึกหากทั้งค่าเดิมและค่าใหม่เพราะว่าโปรแกรมจะทำการลบข้อมูลเดิม
     * จนหมดสิ้น
     */
    protected boolean intCheckResultLab(OrderItem oi,Vector resultlab,UpdateStatus theUS,boolean allow_empty)
    throws Exception 
    {
        StringBuffer warning = new StringBuffer();
        boolean encode = false;
        //บันทึกผลแลบลงบนฐานข้อมูลโดยมีเงื่อนไขดังต่อไปนี้
        for(int j=0;j<resultlab.size();j++)
        {   
            ResultLab rl = (ResultLab)resultlab.get(j);
            //ตรวจสอบเงื่อนไขว่าผลแลบจะถูกต้องตามเงื่อนไขหรือไม่หากไม่ตรงจะไม่ให้บันทึกเป็นอันขาด
            if(!isLabResultMath(rl,warning,allow_empty))
            {
                theUS.setStatus(Constant.getTextBundle("การบันทึกผลแลบ") + " " + warning,UpdateStatus.WARNING);
                return false;
            }
        }
        return true;
    }
    protected boolean intSaveResultLab(OrderItem oi,Vector resultlab,UpdateStatus theUS)
    throws Exception 
    {
        Constant.println("protected boolean intSaveResultLab(OrderItem oi");
        theHosDB.theResultLabDB.deleteByOid(oi.getObjectId());
        boolean is_report = true;
        Item item = theHosDB.theItemDB.selectByPK(oi.item_code);
        for(int j=0;j<resultlab.size();j++)
        {   
            ResultLab rl = (ResultLab)resultlab.get(j);
            //เมื่อตรงตามเงื่อนไขก็จะบันทึกได้ 
            //สำหรับรายการที่เป็นค่าว่างทำอย่างไร ต้องบันทึกให้ครบทุกรายการจึงจะรายงานผลได้
            rl.reported_time = theHO.date_time;
            rl.order_item_id = oi.getObjectId();
            rl.reporter = theHO.theEmployee.getObjectId();
            rl.active = Active.isEnable();
            rl.item_id = oi.item_code;
            if(!rl.result.equals(""))
                rl.result_complete = Active.isDisable();
            else
                is_report = false;
            
            //if(!rl.result.equals("")){            
                //amp:22/02/2549 เพื่อตรวจสอบว่าเป็นแลปปกปิดหรือเปล่า ถ้าเป็นแลปปกปิดให้เข้ารหัสไว้ด้วย
                if("1".equals(item.secret))
                    rl.result = Gutil.CheckReservedWords(Secure.encode(rl.result));   
                Constant.println("rl.result : " + rl.result);
                theHosDB.theResultLabDB.insert(rl);
            //}
        }
        //ถ้า order นั้นเป็น verify อยู่ก็ให้เปลี่ยนเป็น execute เลย
        if(oi.status.equals(OrderStatus.VERTIFY))
        {
            oi.executer =  theHO.theEmployee.getObjectId();
            oi.executed_time = theHO.date_time;
            oi.status = OrderStatus.EXECUTE;
            theHosDB.theOrderItemDB.update(oi); 
        }
        //ถ้า order นั้นรายงานผลแล้ว อยู่ก็ให้เปลี่ยนเป็น รายงานผล เลย
        if(is_report && !resultlab.isEmpty()
        && !theHO.theEmployee.authentication_id.equals(Authentication.LAB))
        {
            oi.reporter = theHO.theEmployee.getObjectId();
            oi.reported_time = theHO.date_time;
            oi.status = OrderStatus.REPORT;
            theHosDB.theOrderItemDB.update(oi); 
        }  
        return true;
    }
    /**
     * เอาไว้แปลงข้อมูลผลแลบเข้าสู่ฟังชันการทำงานมาตรฐาน
     * 
     */
    public static Vector getVVResultLab(Vector vor,Vector vres)
    {
        Vector vv = new Vector();
        for(int j=0;j<vor.size();j++)
        {
            OrderItem oi = (OrderItem)vor.get(j);
            Vector v = new Vector();
            for(int i=0;vres!=null && i<vres.size();i++)
            {
                ResultLab rl = (ResultLab)vres.get(i);
                if(rl.order_item_id.equals(oi.getObjectId()))
                    v.add(rl);
            }
            vv.add(v);
        }
        return vv;
    }
    
   /**
    * ตรวจสอบผลแลบว่าตรงตามขอบเขตที่กำหนดหรือไม่
    * หากตรงก็ทำการบันทึกให้แต่หากว่าไม่ตรงก็จะไม่บันทึกหรือ บันทึกเป็นค่าว่างแทน
    * save หมายถึง ตรวจสอบเพื่ออะไรเพื่อบันทึกหรือไม่  
    * encode หมายถึง ผลแลปได้เข้ารหัสไว้แล้ว   
    *  หากเพื่อบันทึกก็โปรแกรมจะ ไม่เช็คค่าว่าง  
    *  หากเพื่อส่งผล โปรแกรมจะเช็คค่าว่างด้วย
    */

    protected boolean isLabResultMath(ResultLab rsl,StringBuffer warning,boolean allow_empty) throws Exception
    {       
        String data = rsl.result;
        String lab_result_type = rsl.result_type_id;        
        //ถ้าเป็นค่าว่างก็ไม่ต้องเช็คว่าถูกหรือผิด        
        if(rsl.result.equals(""))
        {
             if(!allow_empty)
             {
                 warning.append(" ผลแลบ " + rsl.name + " ต้องมีการกรอกข้อมูล");
                 return false;
             }
             else
                 return true;
        }
        if(lab_result_type.equals(LabResultType.INTEGER))
        {
            try{
                Integer.parseInt(data);
            }
            catch(Exception e){
                warning.append(" ผลแลบ " + rsl.name + " ต้องมีค่าเป็นตัวเลขจำนวนเต็ม");
                return false;
            }
        }
        else if(lab_result_type.equals(LabResultType.FLOAT))
        {
            try {
                Double.parseDouble(data);
            }
            catch(Exception e)
            {
                warning.append(" ผลแลบ " + rsl.name + " ต้องมีค่าเป็นตัวเลขทศนิยม");
                return false;
            }
        }
        else if(lab_result_type.equals(LabResultType.TEXT_ONE))
        {
            boolean ret = (data.indexOf('\n')==-1);
            if(ret==false)
                warning.append(" ผลแลบ " + rsl.name + " ต้องเป็นข้อความ 1 บรรทัด");
            return ret;
        }
        else if(lab_result_type.equals(LabResultType.TEXT_MANY))
        {

        }
        else if(lab_result_type.equals(LabResultType.LIST))
        {
            boolean check = false;
            ////henbe trick////////////////////////////////////////////////////////////
            if(data.equals("-"))
                return true;
            Vector vd = theHosDB.theLabResultDetailDB.selectCByResulteCode(rsl.result_group_id);
            loop : for(int j=0;j<vd.size();j++)
            {
                ComboFix cf = (ComboFix) vd.get(j);
                if(cf.getName().trim().equals(String.valueOf(data.trim())))
                {
                    check = true;
                    break loop;
                }
                else
                {
                    check = false;
                }
            }
            //มีปัญหาในแลปปกปิด
            if(!check)
            {
                /////henbe trick///////////////////////////////////////////////////////////
                warning.append(" ผลแลบ " + rsl.name + " ต้องมีค่ามีอยู่ในรายการที่เลือก");
                return false;
            }
        }
        return true;
    }
    
    ////////////////////////////////////////////////////////////////////////////
      /**
       *@Author : amp henbe
       *@date : 08/03/2549  21/03/2549
       *@see : ค้างผลแลบ
       *แก้ให้มันลบคิวเองเลยถ้าไม่มีรายการที่ต้องรายงานผลแล้วนะ
       */
     public void saveRemainResultLab(Vector orderitem ,int[] select)
     {
        Constant.println(UseCase.UCID_saveRemainResultLab);
        String objectid =   null;
        if(select == null || select.length == 0){
            theUS.setStatus("กรุณาเลือกรายการแลป", UpdateStatus.WARNING);
            return;
        }
        for(int i=0;i<select.length;i++){
            OrderItem oi = (OrderItem) orderitem.get(select[i]);
            if(oi.status.equals(OrderStatus.REMAIN)){
               theUS.setStatus("รายการแลปบางรายการอยู่ในสถานะค้างผลแล้ว", UpdateStatus.WARNING);
               return;
            }
        }
        for(int i=0;i<select.length;i++){
            OrderItem oi = (OrderItem) orderitem.get(select[i]);
            if(oi.status.equals(OrderStatus.REPORT)){
               theUS.setStatus("รายการแลปบางรายการอยู่ในสถานะรายงานผลแล้ว", UpdateStatus.WARNING);
               return;
            }
        }
        String orderId = "";
        theConnectionInf.open();
        try{
             String date_time = theLookupControl.intReadDateTime();
             /*เซตสถานะรายการให้เป็นค้างผล*/
             int ordersize = select.length;
             for(int i=0;i<ordersize;i++)
             {
                 OrderItem oi = (OrderItem) orderitem.get(select[i]);
                 oi.status = OrderStatus.REMAIN;
                 theHosDB.theOrderItemDB.update(oi);
                 //amp:08/03/2549 ถ้าเป็นแลปปกปิดจะ record เดียว
                 if("1".equals(oi.secret))
                     orderId = oi.getObjectId();
             }

            //amp:08/03/2549 code ใหม่ 
            //ค้นคิวค้างมาให้ว่ามีหรือเปล่าถ้ามีก็แก้ไขอย่างเดียวถ้าไม่มีก็สร้างใหม่
            QueueLab2 ql = theHosDB.theQueueLabDB.select2ByVisitIDAndOrderId(
                    theHO.theVisit.getObjectId(), orderId);
            QueueLab2 ql_remain = theHosDB.theQueueLabDB.selectByVidRmnOid(
                    theHO.theVisit.getObjectId(),Active.isEnable(), orderId);
            if(ql_remain==null)
            {
                QueueLab2 ql_new = new QueueLab2();
                ql_new.visit_id = theHO.theVisit.getObjectId();
                ql_new.patient_id = theHO.theVisit.patient_id;                        
                ql_new.remain = Active.isEnable();
                ql_new.assign_time = date_time;
                ql_new.last_service = theHO.theServicePoint.getObjectId();
                //กรณีแลบปกปิดให้เอาข้อมูลจากคิวเก่า
                if(ql!=null)
                {
                    ql_new.number_order = ql.number_order;
                    ql_new.order_id = ql.order_id;
                    ql_new.secret_code = ql.secret_code;
                }
                //กรณีแลบทั่วไปให้เอาข้อมูลจากการคำนวน
                else
                {
                    ql_new.number_order = String.valueOf(ordersize);
                    ql_new.order_id = "";
                    ql_new.secret_code = "";
                }
                theHosDB.theQueueLabDB.insert(ql_new);
                theHO.labQueueRemain = Active.isEnable();
            } 
            /////////////////////////////////////////////////////
            //QueueLabStatus/////////////////////////////////////////////
            String status = refreshLabStatus(theHO.theVisit.getObjectId());
            ////////////////////////////////////////////////////////////////////////////////////
            //ตรวจสอบว่าถ้ารายการ order ทั้งหมดไม่มีรายการที่ ยืนยันหรือดำเนินการอยู่
            if(HosObject.countOrderVerifyExe(orderitem)==0)
            {
                theHosDB.theQueueLabDB.deleteByOrderId(theHO.theVisit.getObjectId(),"0", orderId);
                theVisitControl.intUnlockVisit(theHO.theVisit);
                theHO.clearFamily();
            }
            ////////////////////////////////////////////////////////////////////////////////////
            theHS.theResultSubject.notifySaveRemainLabResult(Constant.getTextBundle("การบันทึกการค้างผลแลป") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveRemainResultLab,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveRemainResultLab,objectid,null,UpdateStatus.COMPLETE);
         }
         catch(Exception ex)
         {
            theSystemControl.setStatus(UseCase.TH_saveRemainResultLab,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveRemainResultLab,objectid,ex,UpdateStatus.ERROR);
         }
         finally
         {
             theConnectionInf.close();
         }
     }

     ///////////////////////////////////////////////////////////////////////////
    //ทำการส่งผลแลป
     public void sendDataResultLab(Vector voi ,Vector vresultlab,int[] select)
     {  
        Constant.println("public void sendDataResultLab(Vector v_order ,Vector rl,int[] select)");
        Constant.println(UseCase.UCID_sendResultLab);
        String objectid =   null;
        if(theHO.theVisit == null){
            theUS.setStatus("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ",UpdateStatus.WARNING);
            return;
        }
        if(select.length == 0){
            theUS.setStatus("ยังไม่ได้เลือกรายการแลปที่ต้องการจะส่ง",UpdateStatus.WARNING);
            return;
        }
        Vector vvresultlab = getVVResultLab(voi,vresultlab);
        theConnectionInf.open();  
        try{
            theHO.date_time = theLookupControl.intReadDateTime();
            int count_exec = 0;
            //////////////////////////////////////////////////////////////////////////
            //ตรวจสอบผลแลปทั้งหมดว่าตรงตามเงื่อนไขหรือไม่
            /*บันทึกข้อมูลผลแลป เซตค่าให้กับresultlab*/
            String order_status = "";
            if(select.length==0)
            {
                theUS.setStatus("ไม่มีรายการแลปที่ต้องการจะส่ง",UpdateStatus.WARNING);
                return;
            }
            for(int i=0;i<select.length;i++)
            {  
                OrderItem oi = (OrderItem)voi.get(select[i]);
                if(oi.status.equals(OrderStatus.REPORT)
                || oi.status.equals(OrderStatus.NOT_VERTIFY)
                || oi.status.equals(OrderStatus.DIS_CONTINUE)
                || oi.status.equals(OrderStatus.DISPENSE)){
                    theUS.setStatus("กรุณาเลือกรายการแลปที่อยู่ในสถานะดำเนินการ",UpdateStatus.WARNING);
                    continue;
                }
                Vector vrl = (Vector)vvresultlab.get(select[i]);
                if(!intCheckResultLab(oi,vrl,theUS,false))
                    continue;
                        
                count_exec++;
                oi.reporter = theHO.theEmployee.getObjectId();
                oi.reported_time = theHO.date_time;
                order_status = oi.status;
                oi.status = OrderStatus.REPORT;
                theHosDB.theOrderItemDB.update(oi);  
                theOrderControl.intSaveQueueOfOrder(oi,false,theHO.date_time);//ส่งผลแลป
            }
            if(count_exec==0)
            {
//                theUS.setStatus("ไม่มีรายการแลปที่ต้องการจะส่ง",UpdateStatus.WARNING);     
                return;
            }
            //////////////////////////////////////////////////////////////////////////
            //อ่านข้อมูลของคิวTransferของผู้ป่วย หากเป็นแลบปกปิดก็จะลบคิวให้เลย
            OrderItem ori = (OrderItem)voi.get(0);
            if(ori.secret.equals("1"))
            {
                if(!order_status.equals(OrderStatus.REMAIN))
                    theHosDB.theQueueLabDB.deleteByOrderId(theHO.theVisit.getObjectId(),"0",ori.getObjectId());
                else
                    theHosDB.theQueueLabDB.deleteByOrderId(theHO.theVisit.getObjectId(),Active.isEnable(),ori.getObjectId());
            }
            ///////////////////////////////////////////////////////////////////////////
            //QueueLabStatus/////////////////////////////////////
            String status = refreshLabStatus(theHO.theVisit.getObjectId());
            if(status.equals(QueueLabStatus.REPORT)
            || status.equals(QueueLabStatus.REMAIN)
            || ori.secret.equals(Active.isEnable()))
            {
                theVisitControl.intUnlockVisit(theHO.theVisit);
                theHO.clearFamily();
                theHS.theResultSubject.notifySendResultLab(Constant.getTextBundle("การส่งผลชันสูตรโรค") + " " +
                        Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            }
            else
            {
                theHS.theResultSubject.notifySendResultLab(Constant.getTextBundle("การส่งผลชันสูตรบางรายการ") + " " +
                         Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            }
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_sendResultLab,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_sendResultLab,objectid,null,UpdateStatus.COMPLETE);
            /////////////////////////////////////////////////////
        }
        catch(Exception ex)
        {   
            theSystemControl.setStatus(UseCase.TH_sendResultLab,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_sendResultLab,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    } 
     
   
    
    /**Return Vector ของ Lab_Result_Item */
    
     public ResultLab readResultLabByOrderID(ResultLab resultlab)
     {
        ResultLab rl = new ResultLab();
        resultlab.name = Gutil.CheckReservedWords(resultlab.name);
        theConnectionInf.open();
        try{
            rl = theHosDB.theResultLabDB.selectByOrderItem(resultlab);
        }
        catch(Exception ex)
        {   
            ex.printStackTrace(Constant.getPrintStream());
            rl = null;
        }
        finally
        {
            theConnectionInf.close();
        }
        return rl;
    }
     //////////////////////////////////////////////////////////////////////////
    public void deleteQueueLabByVisitID(String visit_id) 
    {
        theConnectionInf.open();
        try{    
               //int i = theHosDB.theQueueLabDB.deleteByVisitID(visit_id); amp:08/03/2549 เพื่อแลปปกปิด
               int i = theHosDB.theQueueLabDB.deleteByOrderId(visit_id,theHO.labQueueRemain,theHO.orderSecret); 
               theVisitControl.intUnlockVisit(theHO.theVisit);
               theHO.clearFamily();
               if(i ==1)
               {    
                   theHS.theResultSubject.notifyDeleteQueueLab(Constant.getTextBundle("ลบคิวแลป") +" " +
                           Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
               }
        }
        catch(Exception ex)
        {    
             ex.printStackTrace(Constant.getPrintStream());
             theUS.setStatus(Constant.getTextBundle("ลบคิวแลป") + " " +
                     Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
   //////////////////////////////////////////////////////////////////////////
    public void deleteQueueXrayByVisitID(String visit_id) 
    {
        theConnectionInf.open();
        try{    
               int i = theHosDB.theQueueXrayDB.deleteByVisitID(visit_id); 
               if(theHO.isLockingVisit())
               {
                   theVisitControl.intUnlockVisit(theHO.theVisit);
                   theHO.clearFamily();
               }
               theHS.theResultSubject.notifyXrayReportComplete(Constant.getTextBundle("การลบคิวเอ็กซเรย์") + " " +
                       Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex){    
             ex.printStackTrace(Constant.getPrintStream());
             theUS.setStatus(Constant.getTextBundle("การลบคิวเอ็กซเรย์") + " " +
                     Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
        }
        finally {
            theConnectionInf.close();
        }
    }
//      
       ////////////////////////////////////////////////////////////////////////
      public void deleteQueueLab(String visit_id,String remain) 
      {
        theConnectionInf.open();
        try{    
               theHosDB.theQueueLabDB.deleteByVisitID(visit_id,remain);
               theHS.theResultSubject.notifyDeleteQueueLab(Constant.getTextBundle("บันทึกการค้างผลแลป") + " " +
                       Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {    
             ex.printStackTrace(Constant.getPrintStream());
             theUS.setStatus(Constant.getTextBundle("บันทึกการค้างผลแลป") + " " +
                     Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
      }
       
      public void deleteOrderItemLab(Vector vOrderlab,int[] row) 
      {
        if(vOrderlab == null)
        {    
            theUS.setStatus("กรุณาเลือกรายการตรวจรักษา",UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try
        {  
            for(int i=row.length-1;i>=0;i--){
                OrderItem theOrderItemLab = (OrderItem)vOrderlab.get(row[i]);
                vOrderlab.remove(row[i]);
                theHosDB.theOrderItemDB.delete(theOrderItemLab); 
            }
            theHO.vOrderItem = theHosDB.theOrderItemDB.selectByVidTypeCancel(theHO.theVisit.getObjectId(),"",false);
            theHS.theResultSubject.notifyDeleteLabOrder(Constant.getTextBundle("การลบข้อมูลรายการชันสูตรโรค") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {   
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การลบข้อมูลรายการชันสูตรโรค") + " " +
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.WARNING);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
      
    public void deleteOrderItemLabByLab(OrderItem orderItem,Visit visit) 
    {
        theConnectionInf.open();
        try
        {  
             theHosDB.theOrderItemDB.delete(orderItem);             
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

    public Vector listResultLabByVisit(Visit visit)
    {
        if(visit==null)
            return new Vector();
        theConnectionInf.open();
        String active = "<>'1'";
        if(theHO.orderSecret != null && !theHO.orderSecret.equals(""))
            active = "='1'";
        try{
            String sql =  "select t_result_lab.* from t_result_lab " +
                    "inner join t_order on t_order.t_order_id = t_result_lab.t_order_id " +
                    "where t_order.t_visit_id = '"+visit.getObjectId()+"' " +
                    "and f_item_group_id = '2'" +
                    "and (f_order_status_id = '1' or f_order_status_id = '2' " +
                    " or f_order_status_id = '4' or f_order_status_id = '6') " +
                    "and order_secret "+ active;
                if(theHO.orderSecret != null && !theHO.orderSecret.equals(""))
                    sql += " and  t_order.t_order_id = '" + theHO.orderSecret + "'";
                sql += " Order by order_date_time,order_common_name,result_lab_index";                   
                Vector result = theHosDB.theResultLabDB.eQuery(sql);
                if(!theHO.orderSecret.equals(""))
                {
                    for(int j=0;j<result.size();j++)
                    {
                        ResultLab res = (ResultLab)result.get(j);
                        res.result = Secure.decode(res.result);
                    }
                }
            return result;
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
            return new Vector();
        }
        finally {
            theConnectionInf.close();
        }
    }
}
