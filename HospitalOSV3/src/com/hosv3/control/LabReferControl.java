/*
 * LabReferControl.java
 *
 * Created on 22 พฤษภาคม 2547, 09:34 น.
 */
package com.hosv3.control;
//import com.henbe.connection.UpdateStatus;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.object.*;
import com.hosv3.utility.Constant;

import com.hosv3.object.*;
import com.hosv3.subject.*;
import com.hosv3.utility.GuiLang;
import com.hosv3.utility.connection.*;
//import com.hosv3.utility.*;

import java.util.*;

/**
 *
 * @author  Amp
 */
public class LabReferControl {
    
    ConnectionInf theConnectionInf;
    UpdateStatus theUS;
    HosDB theHosDB;
    HosObject theHO;
    HosSubject theHS;
    LookupObject theLO;
    LookupControl theLookupControl;
    String theStatus;
    SystemControl theSystemControl;
    /** Creates a new instance of BillingControl */
    
    public LabReferControl(ConnectionInf c
    ,HosObject ho,HosDB hdb,HosSubject hs,LookupObject lo) {
        theConnectionInf = c;
        theHosDB = hdb;
        theHO = ho;
        theHS = hs;
        theLO = lo;
    }
    public void setSystemControl(SystemControl systemControl)
    {
        theSystemControl = systemControl;
    }
    public void setDepControl(LookupControl lc){
        theLookupControl = lc;
    }
    public void setUpdateStatus(UpdateStatus us){
        theUS = us;
    }
    /**
     * ตรวจสอบว่าเคยมี pid ขงผู้ป่วยคนนี้ในฐานข้อมูลแล้วหรือยัง
     * จัดเอกสารรายละเอียด uc แล้ว 27/11/47 
     */
    public boolean checkPidPatientLabreferinWithPatient(String pid) 
    {    
       theConnectionInf.open();
       boolean result=true;
       try{     
           Vector answer = null;
           answer = theHosDB.thePatientDB.queryPid(pid); 
           theConnectionInf.close();
           if(answer == null || answer.size()==0)
           {
               Constant.println("(answer == null || answer.size()==0)");
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
           theConnectionInf.close();
       }
       return result;
     }
     public boolean checkPidPatientLabreferinWithPatientLabreferin(String pid) 
    {    
        theConnectionInf.open(); 
         boolean result=true;
        try
        {
            Vector answer1 = null; 
            answer1 = theHosDB.thePatientLabreferinDB.queryPid(pid);
            if(answer1 == null || answer1.size()==0)
            {
              Constant.println("(answer1 == null || answer1.size()==0)");
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
           theConnectionInf.close();
        }
        return result;
   }
    /**
     * เพิ่มข้อมูลผู้ป่วยที่มีการส่ง labreferin
     * จัดเอกสารรายละเอียด uc แล้ว 27/11/47 
     */
    /**
    *@Author: sumo
    *@date: 25/08/2549
    *@see: บันทึกผู้ป่วย LabReferIn
    *@param: Object PatientLabreferin,Object UpdateStatus
    *@return: boolean
    */
    public boolean savePatientLabreferin(PatientLabreferin thePatientLabreferin,UpdateStatus theUS) 
    {
        Constant.println(UseCase.UCID_savePatientLabreferin);
        String objectid =   null;
        // เพิ่มตรวจสอบ trim ของชื่อและนามสกุล sumo 25/08/2549
        if((thePatientLabreferin.fname.trim().equals("")) || (thePatientLabreferin.lname.trim().equals("")))
        {   
            theUS.setStatus(Constant.getTextBundle("ยังไม่ได้กรอก") + " " +
                    Constant.getTextBundle("ชื่อ สกุล ของผู้ป่วย"),UpdateStatus.WARNING);
            return false;
        }
        // เพิ่มตรวจสอบหมายเลขประชาชนบัตรกรอกไม่ครบ sumo 25/08/2549
        if(thePatientLabreferin.pid.trim().length() > 0 && thePatientLabreferin.pid.trim().length() < 13)
        {
            theUS.setStatus(Constant.getTextBundle("กรอกหมายเลขประชาชนบัตรไม่ครบ") + " " +
                    Constant.getTextBundle("กรุณากรอกให้ครบก่อนทำการบันทึก"),UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try{
            if(!thePatientLabreferin.pid.equals(""))
            {
                Vector pidv = theHosDB.thePatientDB.queryPid(thePatientLabreferin.pid);
                String hn_pid_many = "";
                for(int i=0,size=pidv.size();i<size;i++){
                    Patient pt_pidv = (Patient)pidv.get(i);
                    hn_pid_many = hn_pid_many + " "+ pt_pidv.hn;
                    //มีเลขบัตรประชาชน และยังไม่มีข้อมูลผู้ป่วยคนนี้ ต้องเช็คเลขบัตรประชาชน
                }
                //มีเลขบัตรประชาชน และยังไม่มีข้อมูลผู้ป่วยคนนี้ ต้องเช็คเลขบัตรประชาชน
                if(thePatientLabreferin.getObjectId()==null && !pidv.isEmpty()){
                    theUS.setStatus(Constant.getTextBundle("หมายเลขบัตรประชาชนซ้ำกับผู้ป่วย") +
                            " HN:"+hn_pid_many,UpdateStatus.WARNING);
                    return false;
                }
                //มีเลขบัตรประชาชน ของผู้ป่วยเก่าที่ซ้ำกันมากกว่า 1 คน
                if(thePatientLabreferin.getObjectId()!=null && pidv.size()>1){
                    theUS.setStatus(Constant.getTextBundle("หมายเลขบัตรประชาชนซ้ำกับผู้ป่วย") +
                            " HN:"+hn_pid_many,UpdateStatus.WARNING);
                    return false;
                }
                //มีเลขบัตรประชาชน ของผู้ป่วยเก่าเลขนี้อยู่แล้ว
                if(thePatientLabreferin.getObjectId()!=null && !pidv.isEmpty())
                {
                    Patient pt_pidv = (Patient)pidv.get(0);
                    if(!pt_pidv.getObjectId().equals(thePatientLabreferin.getObjectId()))
                    {
                        theUS.setStatus(Constant.getTextBundle("หมายเลขบัตรประชาชนซ้ำกับผู้ป่วย") +
                            " HN:"+hn_pid_many,UpdateStatus.WARNING);
                        return false;
                    }
                }
                Vector plridv = theHosDB.thePatientLabreferinDB.queryPid(thePatientLabreferin.pid);
                //มีเลขบัตรประชาชน และยังไม่มีข้อมูลผู้ป่วยคนนี้ ต้องเช็คเลขบัตรประชาชน
                if(thePatientLabreferin.getObjectId()==null && !plridv.isEmpty()){
                    theUS.setStatus("หมายเลขบัตรประชาชนซ้ำ",UpdateStatus.WARNING);
                    return false;
                }
                //มีเลขบัตรประชาชน ของผู้ป่วยเก่าที่ซ้ำกันมากกว่า 1 คน
                if(thePatientLabreferin.getObjectId()!=null && plridv.size()>1){
                    theUS.setStatus("หมายเลขบัตรประชาชนซ้ำ",UpdateStatus.WARNING);
                    return false;
                }
                //มีเลขบัตรประชาชน ของผู้ป่วยเก่าเลขนี้อยู่แล้ว
                if(thePatientLabreferin.getObjectId()!=null && !plridv.isEmpty())
                {
                    PatientLabreferin pt_plridv = (PatientLabreferin)plridv.get(0);
                    if(!pt_plridv.getObjectId().equals(thePatientLabreferin.getObjectId()))
                    {
                        theUS.setStatus("หมายเลขบัตรประชาชนซ้ำ",UpdateStatus.WARNING);
                        return false;
                    }
                }
                int result1 = Constant.isCorrectPID(thePatientLabreferin.pid);                                                   
                if(thePatientLabreferin.pid.length()==13 && result1 != 1){ //incorrect pid standard
                    if(!theUS.confirmBox(Constant.getTextBundle("หมายเลขบัตรประชาชนยังไม่ถูกต้อง") + " " +
                            Constant.getTextBundle("ต้องการบันทึกข้อมูลหรือไม่")
                        ,UpdateStatus.WARNING)){
                        return false;
                    }
                }
            }
            Prefix pfx = theLookupControl.readPrefixById(thePatientLabreferin.prefix_id);
            if(pfx!=null && thePatientLabreferin.sex != pfx.sex && pfx.tlock == Active.isEnable())
            {
                theUS.setStatus("คำนำหน้าไม่สัมพันธ์กับเพศที่เลือก",UpdateStatus.WARNING);
                return false;
            }
            if(thePatientLabreferin.getObjectId()==null)
            {               
                theHosDB.thePatientLabreferinDB.insert(thePatientLabreferin);                    
            }
            else
            {     
                theHosDB.thePatientLabreferinDB.update(thePatientLabreferin);  
            }
            theConnectionInf.close(); 
            theUS.setStatus(Constant.getTextBundle("การบันทึกข้อมูลการ Refer") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
            if(thePatientLabreferin != null)
                objectid = thePatientLabreferin.getObjectId();
            theSystemControl.setStatus(UseCase.TH_savePatientLabreferin,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_savePatientLabreferin,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_savePatientLabreferin,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_savePatientLabreferin,objectid,ex,UpdateStatus.ERROR);
            return false;
            //theHS.theLabReferSubject.notifysavePatientLabreferin(null,"Found Error");
        }
        finally
        {
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
           else 
               result= theHosDB.thePatientLabreferinDB.queryByFName("%");
        }
       catch(Exception e)
       {
           e.printStackTrace(Constant.getPrintStream());
           theConnectionInf.close();           
       }
       theConnectionInf.close();
       return result;
    }
    
    /**
     * ลบผู้ป่วยออกจากฐานข้อมูล
     * จัดเอกสารรายละเอียด uc แล้ว 27/11/47 
     */
    public void deletePatientLabreferIn(PatientLabreferin patientLabreferin)
    {
        theConnectionInf.open();
        try
        {  
             theHosDB.thePatientLabreferinDB.delete(patientLabreferin);            
        }
        catch(Exception ex)
        {   
            ex.printStackTrace(Constant.getPrintStream());                   
        }
        theConnectionInf.close();
    }
    
    /**
     * บันทึกข้อมูลการเข้ารับบริการของผู้ป่วย
     * จัดเอกสารรายละเอียด uc แล้ว 29/11/47 
     */
    public void saveVisitPatientLabreferin(VisitLabreferin visitLabreferin,UpdateStatus theUS)
    {
     
        if(visitLabreferin.getObjectId()!=null)
        {
            //theHS.theLabReferSubject.notifysaveVisitPatientLabreferin(visitLabreferin,"Patient is in Process");            
            return;
        }
        
        theConnectionInf.open();
        try {
                visitLabreferin.begin_visit_time = theLookupControl.intReadDateTime();
            
                theHosDB.theVisitLabreferinDB.insert(visitLabreferin);
                theUS.setStatus(Constant.getTextBundle("ทำการ Visit ผู้ป่วยRefer") + " " +
                        Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
                return;
                //theHS.theLabReferSubject.notifysaveVisitPatientLabreferin(visitLabreferin,"Complete");
        }
        catch(Exception ex)
        {    
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การ Visit ผู้ป่วยRefer") + " " +
                    Constant.getTextBundle("ผิดพลาด"), UpdateStatus.ERROR);
            return;
            //theHS.theLabReferSubject.notifysaveVisitPatientLabreferin(visitLabreferin,"Error");
        }
        finally
        {
           theConnectionInf.close();  
        }
    }
    
    /**
     * ตรวจสอบว่าผู้ป่วยเคยเข้ารับบริการแล้วหรือยัง เพื่อนำไปใช้ประโยชน์ในการลบข้อมูลผู้ป่วยอีกทีหนึ่ง
     * จัดเอกสารรายละเอียด uc แล้ว 29/11/47 
     */
    public boolean checkPatientInTableVisit(String patientLabreferinId) 
    {    
       theConnectionInf.open();
       boolean result=true;
       try{     
           Vector answer = null;          
           answer = theHosDB.theVisitLabreferinDB.queryPid(patientLabreferinId);           
           theConnectionInf.close();
           if(answer == null || answer.size()==0)
              result = false;
           else
              result = true;           
       }
       catch(Exception ex)
       {    
           ex.printStackTrace(Constant.getPrintStream()); 
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
        try{
            result= theHosDB.theVisitLabreferinDB.queryVisitInProcess();                         
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
            theConnectionInf.close();           
        }
        theConnectionInf.close();
        return result;
    }
    
    /**
     * ค้นหาข้อมูลผู้ป่วยจาก Primary Key
     * จัดเอกสารรายละเอียด uc แล้ว 29/11/47 
     */
    public PatientLabreferin readPatientLabByPk(String plriid) 
    {
        PatientLabreferin p=null; 
        theConnectionInf.open();
        try
        {
           p = theHosDB.thePatientLabreferinDB.selectByPK(plriid);
           return p;
        }
        catch(Exception e)
        {    
            e.printStackTrace(Constant.getPrintStream());
            return null;
        } 
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     * ค้นหาข้อมูลการรับบริการครั้งล่าสุดที่ยังมีสถานะเข้ารับบริการอยู่
     * จัดเอกสารรายละเอียด uc แล้ว 29/11/47 
     */
    public VisitLabreferin readVisitInProcessByPatientId(String plriid) 
    {
        VisitLabreferin v=null; 
        theConnectionInf.open();
        try{
           v = theHosDB.theVisitLabreferinDB.selectVisitByPId(plriid);           
        }
        catch(Exception e)
        {    
		e.printStackTrace(Constant.getPrintStream());
        } 
        theConnectionInf.close();
        return v;
    }
    /**
     * ค้นหาข้อมูลการรับบริการทั้งหมดของผู้ป่วย
     * จัดเอกสารรายละเอียด uc แล้ว 29/11/47 
     */
    public Vector listVisitLabreferinByPId(String pid) 
    {
        Vector result = new Vector();
        theConnectionInf.open();
        try{
            result= theHosDB.theVisitLabreferinDB.queryVisitInProcessByPid(pid);                         
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
            theConnectionInf.close();           
        }
        theConnectionInf.close();
        return result;
    }
    
    /**
     * ค้นหารายการ order จากการคลิกเลือกครั้งการเข้ารับบริการที่ต้องดู
     * จัดเอกสารรายละเอียด uc แล้ว 29/11/47 
     */
    public Vector listOrderByVisitId(String VId) 
    {
        Vector result = new Vector();
        theConnectionInf.open();
        try{
            result= theHosDB.theOrderItemLabreferinDB.queryOrderByVid(VId);                         
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
            theConnectionInf.close();           
        }
        theConnectionInf.close();
        return result;
    }
    
    /**
     * ลบรายการ order 
     * จัดเอกสารรายละเอียด uc แล้ว 29/11/47 
     */
    public void deleteOrderItemLabReferIn(OrderItemLabreferin orderItemLabreferin) 
    {
        theConnectionInf.open();
        try
        {  
             theHosDB.theOrderItemLabreferinDB.delete(orderItemLabreferin); 
             theHosDB.theOrderResultLabreferinDB.deleteById(orderItemLabreferin.getObjectId());
        }
        catch(Exception ex)
        {   
            ex.printStackTrace(Constant.getPrintStream());                  
        }
        theConnectionInf.close();
    }
    
    /**
     * บันทึกรายการ order 
     * จัดเอกสารรายละเอียด uc แล้ว 30/11/47 
     */
    public void saveOrderItemLabReferin(Vector orderItemLabreferin) 
    {
        theConnectionInf.open();
        try
        {  
            if(orderItemLabreferin != null)
            {   
                for(int i=0 ; i<orderItemLabreferin.size(); i++)
                {   
                    if( ((OrderItemLabreferin)orderItemLabreferin.get(i)).getObjectId() == null  )
                    {         
                         theHosDB.theOrderItemLabreferinDB.insert( (OrderItemLabreferin)orderItemLabreferin.get(i) ) ;                            
                    }
                    else
                    {  
                         theHosDB.theOrderItemLabreferinDB.update( (OrderItemLabreferin)orderItemLabreferin.get(i) );                            
                    }
                }
            }
        }
        catch(Exception ex)
        {   
            ex.printStackTrace(Constant.getPrintStream());                          
        }
        theConnectionInf.close();    
    }
    
    /**
     * แสดงผล lab ตามรายการ order 
     * จัดเอกสารรายละเอียด uc แล้ว 30/11/47 
     * ยังไม่ได้ใช้งาน
     */
    public OrderResultLabreferin readResultLabReferinByOrderID(OrderResultLabreferin resultlab) 
    {
        OrderResultLabreferin rl = new OrderResultLabreferin();
        theConnectionInf.open();
        try{
            rl = theHosDB.theOrderResultLabreferinDB.selectByOrderItem(resultlab);
        }
        catch(Exception ex)
        {   ex.printStackTrace(Constant.getPrintStream());
            rl = null;
        }
        theConnectionInf.close();
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
        try{
            oilri = theHosDB.theOrderItemLabreferinDB.selectByItemIDAndVID(itemID,visitID);
        }
        catch(Exception ex)
        {   
            ex.printStackTrace(Constant.getPrintStream());
            oilri = null;
        }
        theConnectionInf.close();
        return oilri;
    }    
    
    public String updateOrderItemLabreferin(OrderItemLabreferin orderItemLabreferin) 
    {
        theConnectionInf.open();
        try{         
                    theHosDB.theOrderItemLabreferinDB.update(orderItemLabreferin);
                    theStatus = "Complete";
        }
        catch(Exception ex)
        {    
           ex.printStackTrace(Constant.getPrintStream());
           theStatus =  "Error";
        }      
        theConnectionInf.close();
        return theStatus;
    }    
    
    public void saveOrderResultLabrefrin(OrderResultLabreferin p) 
    {
        theConnectionInf.open();
        try{
            if(p.getObjectId() == null) 
            {
                theHosDB.theOrderResultLabreferinDB.insert(p); 
                theConnectionInf.close();                
            }
            else
            {
                theHosDB.theOrderResultLabreferinDB.update(p);
            }            
        }
        catch(Exception ex)
        {    
            ex.printStackTrace(Constant.getPrintStream());           
        }
        theConnectionInf.close();
    }
    
    public void deleteOrderResultLabReferIn(String oilriId) 
    {
        theConnectionInf.open();
        try
        {  
             theHosDB.theOrderResultLabreferinDB.deleteById(oilriId);             
        }
        catch(Exception ex)
        {   
            ex.printStackTrace(Constant.getPrintStream());                          
        }
        theConnectionInf.close();
    }
    
    public boolean checkVisitInResult(String visitLabreferinId) 
    {
        theConnectionInf.open();
        boolean result=true;
        try{     
           Vector answer = null;          
           answer = theHosDB.theOrderResultLabreferinDB.queryVid(visitLabreferinId);           
           theConnectionInf.close();
           if(answer == null || answer.size()==0)
              result = false;
           else
              result = true;           
        }
        catch(Exception ex)
        {    
           ex.printStackTrace(Constant.getPrintStream()); 
           theConnectionInf.close();
        }
        return result;     
    }
    
    public Vector listResultByVId(String vId) 
    {
        Vector result = new Vector();
        theConnectionInf.open();
        try{
            result= theHosDB.theOrderResultLabreferinDB.queryresultByVid(vId);                         
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
            theConnectionInf.close();           
        }
        theConnectionInf.close();
        return result;
    }
    
    public String updateVisitLabreferin(VisitLabreferin visitLabreferin)
    {
        theConnectionInf.open();
        try{         
                    theHosDB.theVisitLabreferinDB.update(visitLabreferin);
                    theStatus = "Complete";
        }
        catch(Exception ex)
        {    
           ex.printStackTrace(Constant.getPrintStream());
           theStatus =  "Error";
        }      
        theConnectionInf.close();
        return theStatus;
    }
    
    public boolean checkVisitInOrder(String visitLabreferinId) 
    {
        theConnectionInf.open();
        boolean result=true;
        try{     
           Vector answer = null;          
           answer = theHosDB.theOrderItemLabreferinDB.queryVid(visitLabreferinId);           
           theConnectionInf.close();
           if(answer == null || answer.size()==0)
              result = false;
           else
              result = true;           
        }
        catch(Exception ex)
        {    
           ex.printStackTrace(Constant.getPrintStream()); 
           theConnectionInf.close();
        }
        return result;   
    }
    
    public OrderItemLabreferin readOrderItemByOrderItemIdAndVisitId(String orderItemID) 
    {
        OrderItemLabreferin oilri = new OrderItemLabreferin();
        theConnectionInf.open();
        try{
            oilri = theHosDB.theOrderItemLabreferinDB.selectByPK(orderItemID);
        }
        catch(Exception ex)
        {   
            ex.printStackTrace(Constant.getPrintStream());
            oilri = null;
        }
        theConnectionInf.close();
        return oilri;
    }
    
    public Vector listLabSetByItemId(String itemId) 
    {
        Vector result = new Vector();
        theConnectionInf.open();
        try{
            result= theHosDB.theLabSetDB.selectByItemId(itemId);                         
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
            theConnectionInf.close();           
        }
        theConnectionInf.close();
        return result;
    }
    
    public LabGroup readLabGroupByPk(String labGroupId) 
    {
        LabGroup p=null; 
        theConnectionInf.open();
        try{
           p = theHosDB.theLabGroupDB.selectByPK(labGroupId);           
        }
        catch(Exception e)
        {    e.printStackTrace(Constant.getPrintStream());
        } 
        theConnectionInf.close();
        return p;
    }
    
    public Vector listLabSetByLabGroupId(String lgId) 
    {
        Vector result = new Vector();
        theConnectionInf.open();
        try{
            result= theHosDB.theLabSetDB.selectByLabGroupID(lgId);                         
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
            theConnectionInf.close();           
        }
        theConnectionInf.close();
        return result;
    }
    
    public boolean checkOrderItemIdInResult(String orderItemLabreferinId) 
    {
        theConnectionInf.open();
        boolean result=true;
        try{     
           Vector answer = null;          
           answer = theHosDB.theOrderResultLabreferinDB.queryOrderItemId(orderItemLabreferinId);           
           theConnectionInf.close();
           if(answer == null || answer.size()==0)
              result = false;
           else
              result = true;           
        }
        catch(Exception ex)
        {    
           ex.printStackTrace(Constant.getPrintStream()); 
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
        {    e.printStackTrace(Constant.getPrintStream());
        } 
        theConnectionInf.close();
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
        {    e.printStackTrace(Constant.getPrintStream());
        } 
        theConnectionInf.close();
        return p;
    }
    
    public void printResultLab() {
        //theHS.theLabReferSubject.notifyprintResultLab();
    }
    
    public void addLabReferOut(Vector vLabReferOut){
    theHO.vLabReferOut = null;
    if(vLabReferOut == null || vLabReferOut.size() == 0){
        theUS.setStatus("ไม่มีข้อมูลรายการแลป", UpdateStatus.WARNING);
        return;
    }
    theHO.vLabReferOut = vLabReferOut;
    theHS.theResultSubject.notifyAddLabReferOut(Constant.getTextBundle("เพิ่มข้อมูลรายการแลป") + " " +
            Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
    }
    
    public void addLabReferIn(Vector vLabReferIn){
        theHO.vLabReferIn = null;
        if(vLabReferIn == null || vLabReferIn.size() == 0){
            theUS.setStatus("ไม่มีข้อมูลรายการแลป", UpdateStatus.WARNING);
            return;
        }
        theHO.vLabReferIn = vLabReferIn;
        theHS.theResultSubject.notifyAddLabReferIn(Constant.getTextBundle("เพิ่มข้อมูลรายการแลป") + " " +
                Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
    }
}
