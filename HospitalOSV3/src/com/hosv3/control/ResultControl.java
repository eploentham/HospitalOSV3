/*
 * LabReferControl.java
 *
 * Created on 22 ����Ҥ� 2547, 09:34 �.
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
     * ��Ǩ�ͺ������� pid �������¤����㹰ҹ���������������ѧ
     * �Ѵ�͡�����������´ uc ���� 27/11/47 
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
     * ���������ż����·���ա���� labreferin
     * �Ѵ�͡�����������´ uc ���� 27/11/47 
     */
    public void savePatientLabreferin(PatientLabreferin p) 
    {
        if(p==null){
            theUS.setStatus("�����żԴ��Ҵ",UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{                                       
            if(p.getObjectId()==null)               
                    theHosDB.thePatientLabreferinDB.insert(p);                    
            else    theHosDB.thePatientLabreferinDB.update(p);  
            theHS.theResultSubject.notifyManagePatientLabReferIn(Constant.getTextBundle("��ù���Ң������ź") + " " +
                    Constant.getTextBundle("�������"),com.hosv3.utility.connection.UpdateStatus.COMPLETE);
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("��ù���Ң������ź") + " " +
                    Constant.getTextBundle("�Դ��Ҵ"),UpdateStatus.ERROR);
        } 
        finally {
            theConnectionInf.close();
        }
    } 
    /**
     * ���Ҽ����¨ҡ�������͹��ʡ��
     * �Ѵ�͡�����������´ uc ���� 27/11/47 
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
     * ź�������͡�ҡ�ҹ������
     * �Ѵ�͡�����������´ uc ���� 27/11/47 
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
     * ��㹡���ʴ�����ѵ� XN �ͧ������
     * @param patient_id �� �Ţ patient_id �ͧ������
     * @param showinactive �� boolean ����˹��������ʴ��������(�ʴ��ŷ���� active ����)
     * @return �� Vector �ͧ Object PatientXN
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
     * @return �� boolean ��� �� true �ʴ���Ҥ�ҫ�� ����� false �����
     * @author padungrat(tong)
     * @date 04/04/2549,11:30
           //��Ǩ�ͺ��Ҥ�� str �繤����ҧ�������
                //����繤����ҧ
                //��Ǩ�ͺ��Ҥ�� str �Ѻ���� object �ͧ patient �繤�����ǡѹ�������
                    // ����繤�����ǡѺ
                    // �ӡ�� update ŧ���ҧ t_patient_xn �ͧ�Ţ xn � object �ͧ patient ��� active �� 0
                //����õ�Ǩ�ͺ
                //��Ǩ�ͺ��� str ���������� t_patient_xn �������
                    // ������
                    // ��Ǩ�ͺ�� �.�. ����繹��¡��������繻ջѨ�غѹ�������
                        // ����� ������ҧ�Ţ xn ���� ��зӡ�� insert ������ŧ���ҧ t_patient_xn
                        // �������ŧ� object �ͧ patient
                    // ���������    
                        //��Ǩ�ͺ��Ҥ�� str �Ѻ���� object �ͧ patient �繤�����ǡѹ�������
                        // ����繤�����ǡѺ
                        // �ӡ�� update ŧ���ҧ t_patient_xn �ͧ�Ţ xn � object �ͧ patient ��� active �� 0
                //�繤����ҧ
                // ���ҧ�Ţ xn ����
     *
     * ����¹��÷ӧҹ�ͧ XN �����Ǥ����Դ���
     * �������鹻��Ţ xn �ͧ�����¨ж١ set �繤����ҧ�����������͡�úѹ�֡����
     * 㹡óַ��������Ѻ��ԡ�á�� xray 
     *     ��觡�úѹ�֡�ҡ˹�� xray �� gen �Ţ�������
     *     ��ǹ��úѹ�֡�ҡ˹�� ������ ���������
     *
     * �ҡ��ͧ�������¹�ŧ�Ţ�ͧ������¡������¹�Ţ ������Ш��ӷء�Ţ���
     * �ա������¹�ŧ �ҡ����¹�繤����ҧ���
     *     ��úѹ�֡�ҡ˹�� xray �� gen �Ţ�������
     *     ��úѹ�֡�ҡ˹�� ������ ���������
     */
    public boolean savePatientXn(String xn,boolean gen)
    {
        Constant.println(UseCase.UCID_savePatientXn);
        String objectid =   null;
        String year_now = this.theLookupControl.getTextCurrentDate();
        boolean bresult = false;
            year_now = year_now.substring(0, 4);
        //��Ǩ�ͺ����Ţ Xn �繤����ҧ������� ��������
        if(theHO.thePatient==null)
            return false;
            
        theConnectionInf.open();
        try {
            //�������繤����ҧ����礫��
            bresult = intCheckSamePatientXN(xn);
            if(!xn.equals("") && bresult){
                theUS.setStatus("�Ţ XN ����ӡѺ�����¤��������",UpdateStatus.WARNING);
                return bresult;
            }
            //����繤����ҧ����������ա�� generate
            if(xn.equals("") && gen)
            {
                xn = theHosDB.theSequenceDataDB.updateSequence("xn",true);
                bresult = intCheckSamePatientXN(xn);
                if(!xn.equals("") && bresult) {
                    theUS.setStatus("�Ţ XN ����ӡѺ�����¤��������",UpdateStatus.WARNING);
                    return bresult;
                }
            }
            //��� xn ����繤����ҧ ��� �Ţ xn ����ѧ����� ���� �ҡ�����ǡ��ͧ����ӡѺ xn ���
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
            //���������ӡ� set ���Ѻ������
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
     * ��㹡�õ�Ǩ�ͺ��� �Ţ xn ��������ҡ�ë�ӡѹ������� ��ҫ�ӡѹ�е�ͧ������� true �������ӡѹ��������� false
     * @param patient_xn �� String �ͧ �Ţ xn ����ͧ��ä���
     * @return �� bolean ��ҫ�ӡѹ�е�ͧ������� true �������ӡѹ��������� false
     * @author padungrat(tong)
     * @date 21/04/2549,11:30
     */
    private boolean intCheckSamePatientXN(String patient_xn)throws Exception
    {
        boolean result = false;
        Vector vhn;
            vhn = this.theHosDB.thePatientDB.checkSamePatientXn(patient_xn, this.theHO.thePatient.getObjectId());
            this.theHO.vSameXN = vhn;
            //��ӡѹ
            if(vhn != null && vhn.size() >0)
            {
                result = true;
            }
        return result;
    }
    
     /**
     *��㹡�����ҧxn ���Ѻpatient
     *��Ǩ�ͺ������Object patient ��xn ������� �������ա����ӡ�� gen ���
     *������� xn ��з� save
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
     * �ѹ�֡�����š������Ѻ��ԡ�âͧ������
     * �Ѵ�͡�����������´ uc ���� 29/11/47 
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
            theUS.setStatus(Constant.getTextBundle("��ù���Ң������ź") + " " +
                    Constant.getTextBundle("�������"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex) {    
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("��ù���Ң������ź") + " " +
                    Constant.getTextBundle("�Դ��Ҵ"),UpdateStatus.ERROR);
        }
        finally {
            theConnectionInf.close();      
        }
    }
    
    /**
     * ��Ǩ�ͺ��Ҽ�����������Ѻ��ԡ�����������ѧ ���͹�������ª��㹡��ź�����ż������ա��˹��
     * �Ѵ�͡�����������´ uc ���� 29/11/47 
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
     * �ʴ���ª��ͼ����·����ѧ����Ѻ��ԡ��
     * �Ѵ�͡�����������´ uc ���� 29/11/47 
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
     * ���Ң����ż����¨ҡ Primary Key
     * �Ѵ�͡�����������´ uc ���� 29/11/47 
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
     * ���Ң����š���Ѻ��ԡ�ä�������ش����ѧ��ʶҹ�����Ѻ��ԡ������
     * �Ѵ�͡�����������´ uc ���� 29/11/47 
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
     * ���Ң����š���Ѻ��ԡ�÷������ͧ������
     * �Ѵ�͡�����������´ uc ���� 29/11/47 
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
     * ������¡�� order �ҡ��ä�ԡ���͡���駡������Ѻ��ԡ�÷���ͧ��
     * �Ѵ�͡�����������´ uc ���� 29/11/47 
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
     * ź��¡�� order 
     * �Ѵ�͡�����������´ uc ���� 29/11/47 
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
     * �ѹ�֡��¡�� order 
     * �Ѵ�͡�����������´ uc ���� 30/11/47 
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
     * �ʴ��� lab �����¡�� order 
     * �Ѵ�͡�����������´ uc ���� 30/11/47 
     * �ѧ�������ҹ
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
     * �ʴ��� lab �����¡�� order 
     * �Ѵ�͡�����������´ uc ���� 30/11/47   
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
     *@see : �鹼��ź�ٻẺ��������ա�ä������Ǣ��
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
                    Vector v = theHosDB.theResultLabDB.selectNewByOidActive(oi.getObjectId(),Active.isEnable());//�͹�׹�ѹ��¡�è��ա�úѹ�֡���ź�������͹���ǡóչ�����¤�������ҡ���ź�ѧ
                    //�պҧ��¡�÷���ѧ�����ѹ�֡�š��ʴ�����ѧ�բ������ź����ŧ���������
                    //��§ҹ�š��������繤����ҧ����
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
//                    Vector v = theHosDB.theResultLabDB.selectNewByOidActive(oi.getObjectId(),Active.isEnable());//�͹�׹�ѹ��¡�è��ա�úѹ�֡���ź�������͹���ǡóչ�����¤�������ҡ���ź�ѧ
//                    //�պҧ��¡�÷���ѧ�����ѹ�֡�š��ʴ�����ѧ�բ������ź����ŧ���������
//                    //��§ҹ�š��������繤����ҧ����
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
     *@see: ������Ѻ���Ҽ��Ż������ʴ��˹�Ҩ� Refer
     *@param: Vector �ͧ OrderItem
     *@return: Vector �ͧ���ź
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
                if(oi.status.equals(OrderStatus.REPORT))//��¡�� Lab �����§ҹ��������ҹ��
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
     *@see : �鹼��ź�ٻẺ��������ա�ä������Ǣ�� ���Ƿ���ش  �ѧ��������Ϳѧ�ѹ� db
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
     *�ѹ�֡���Ż�繿ѧ�ѹ��ҧ���������¡��ҡ�ء˹�Ҩ�
     *��ѹ�֡�ҡ˹�Ҩ������ 
     * �зӡ���觼ŷ�������������ǹ�ͧ control ������ͧ���������ҧ
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
            theUS.setStatus("��س����͡�����·������㹡�кǹ���",UpdateStatus.WARNING);
            return false;
        }
        /* tong �����͡��������´��������������� �ѹ���������� henbe */
        if(voi==null || voi.isEmpty()) {
            theUS.setStatus("��س����͡�������ź���зӡ�úѹ�֡",UpdateStatus.WARNING);
            return false;
        }
        if(vresultlab==null || vresultlab.isEmpty()) {
            theUS.setStatus("����բ����ż��ź���зӡ�úѹ�֡",UpdateStatus.WARNING);
            return false;
        }
        Vector vvresultlab = getVVResultLab(voi,vresultlab);
        theConnectionInf.open();  
        try{
            theHO.date_time = theLookupControl.intReadDateTime();
            /*�ѹ�֡�����ż��Ż ૵������Ѻresultlab*/
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
                //theUS.setStatus("����բ����ż��ź���зӡ�úѹ�֡",UpdateStatus.WARNING);
                return false;
            }
            //�Ѵ��áѺ���ź㹤������繡���ͼ� ��§ҹ�� ���ͤ�ҧ��
            //add comment by neung
            String status = refreshLabStatus(theHO.theVisit.getObjectId());
//            if(status.equals(QueueLabStatus.REPORT)
//            && theHO.theEmployee.authentication_id.equals(Authentication.LAB))
//            {
//                theVisitControl.intUnlockVisit(theHO.theVisit);
//                return true;
//            }
            theHS.theResultSubject.notifySaveLabResult(Constant.getTextBundle("��úѹ�֡�Ūѹ�ٵ��ä") + " " +
                    Constant.getTextBundle("�������")
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
     * ��ͧ�繼��ź���������зӡ�úѹ�֡�ҡ��駤�������Ф�������������������зӡ��ź���������
     * ��������
     */
    protected boolean intCheckResultLab(OrderItem oi,Vector resultlab,UpdateStatus theUS,boolean allow_empty)
    throws Exception 
    {
        StringBuffer warning = new StringBuffer();
        boolean encode = false;
        //�ѹ�֡���źŧ���ҹ�������������͹䢴ѧ���仹��
        for(int j=0;j<resultlab.size();j++)
        {   
            ResultLab rl = (ResultLab)resultlab.get(j);
            //��Ǩ�ͺ���͹���Ҽ��ź�ж١��ͧ������͹���������ҡ���ç��������ѹ�֡���ѹ�Ҵ
            if(!isLabResultMath(rl,warning,allow_empty))
            {
                theUS.setStatus(Constant.getTextBundle("��úѹ�֡���ź") + " " + warning,UpdateStatus.WARNING);
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
            //����͵ç������͹䢡�кѹ�֡�� 
            //����Ѻ��¡�÷���繤����ҧ�����ҧ�� ��ͧ�ѹ�֡���ú�ء��¡�è֧����§ҹ����
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
                //amp:22/02/2549 ���͵�Ǩ�ͺ������Ż���Դ�������� ������Ż���Դ����������������
                if("1".equals(item.secret))
                    rl.result = Gutil.CheckReservedWords(Secure.encode(rl.result));   
                Constant.println("rl.result : " + rl.result);
                theHosDB.theResultLabDB.insert(rl);
            //}
        }
        //��� order ����� verify ������������¹�� execute ���
        if(oi.status.equals(OrderStatus.VERTIFY))
        {
            oi.executer =  theHO.theEmployee.getObjectId();
            oi.executed_time = theHO.date_time;
            oi.status = OrderStatus.EXECUTE;
            theHosDB.theOrderItemDB.update(oi); 
        }
        //��� order �����§ҹ������ ������������¹�� ��§ҹ�� ���
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
     * �������ŧ�����ż��ź������ѧ�ѹ��÷ӧҹ�ҵðҹ
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
    * ��Ǩ�ͺ���ź��ҵç����ͺࢵ����˹��������
    * �ҡ�ç��ӡ�úѹ�֡������ҡ������ç������ѹ�֡���� �ѹ�֡�繤����ҧ᷹
    * save ���¶֧ ��Ǩ�ͺ�����������ͺѹ�֡�������  
    * encode ���¶֧ ���Ż����������������   
    *  �ҡ���ͺѹ�֡��������� ����礤����ҧ  
    *  �ҡ�����觼� ��������礤����ҧ����
    */

    protected boolean isLabResultMath(ResultLab rsl,StringBuffer warning,boolean allow_empty) throws Exception
    {       
        String data = rsl.result;
        String lab_result_type = rsl.result_type_id;        
        //����繤����ҧ������ͧ����Ҷ١���ͼԴ        
        if(rsl.result.equals(""))
        {
             if(!allow_empty)
             {
                 warning.append(" ���ź " + rsl.name + " ��ͧ�ա�á�͡������");
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
                warning.append(" ���ź " + rsl.name + " ��ͧ�դ���繵���Ţ�ӹǹ���");
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
                warning.append(" ���ź " + rsl.name + " ��ͧ�դ���繵���Ţ�ȹ���");
                return false;
            }
        }
        else if(lab_result_type.equals(LabResultType.TEXT_ONE))
        {
            boolean ret = (data.indexOf('\n')==-1);
            if(ret==false)
                warning.append(" ���ź " + rsl.name + " ��ͧ�繢�ͤ��� 1 ��÷Ѵ");
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
            //�ջѭ����Ż���Դ
            if(!check)
            {
                /////henbe trick///////////////////////////////////////////////////////////
                warning.append(" ���ź " + rsl.name + " ��ͧ�դ�����������¡�÷�����͡");
                return false;
            }
        }
        return true;
    }
    
    ////////////////////////////////////////////////////////////////////////////
      /**
       *@Author : amp henbe
       *@date : 08/03/2549  21/03/2549
       *@see : ��ҧ���ź
       *������ѹź����ͧ��¶���������¡�÷���ͧ��§ҹ�����ǹ�
       */
     public void saveRemainResultLab(Vector orderitem ,int[] select)
     {
        Constant.println(UseCase.UCID_saveRemainResultLab);
        String objectid =   null;
        if(select == null || select.length == 0){
            theUS.setStatus("��س����͡��¡���Ż", UpdateStatus.WARNING);
            return;
        }
        for(int i=0;i<select.length;i++){
            OrderItem oi = (OrderItem) orderitem.get(select[i]);
            if(oi.status.equals(OrderStatus.REMAIN)){
               theUS.setStatus("��¡���Ż�ҧ��¡�������ʶҹФ�ҧ������", UpdateStatus.WARNING);
               return;
            }
        }
        for(int i=0;i<select.length;i++){
            OrderItem oi = (OrderItem) orderitem.get(select[i]);
            if(oi.status.equals(OrderStatus.REPORT)){
               theUS.setStatus("��¡���Ż�ҧ��¡�������ʶҹ���§ҹ������", UpdateStatus.WARNING);
               return;
            }
        }
        String orderId = "";
        theConnectionInf.open();
        try{
             String date_time = theLookupControl.intReadDateTime();
             /*૵ʶҹ���¡������繤�ҧ��*/
             int ordersize = select.length;
             for(int i=0;i<ordersize;i++)
             {
                 OrderItem oi = (OrderItem) orderitem.get(select[i]);
                 oi.status = OrderStatus.REMAIN;
                 theHosDB.theOrderItemDB.update(oi);
                 //amp:08/03/2549 ������Ż���Դ�� record ����
                 if("1".equals(oi.secret))
                     orderId = oi.getObjectId();
             }

            //amp:08/03/2549 code ���� 
            //�鹤�Ǥ�ҧ�����������������Ҷ���ա�������ҧ���Ƕ������ա����ҧ����
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
                //�ó��ź���Դ�����Ң����Ũҡ������
                if(ql!=null)
                {
                    ql_new.number_order = ql.number_order;
                    ql_new.order_id = ql.order_id;
                    ql_new.secret_code = ql.secret_code;
                }
                //�ó��ź����������Ң����Ũҡ��äӹǹ
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
            //��Ǩ�ͺ��Ҷ����¡�� order �������������¡�÷�� �׹�ѹ���ʹ��Թ�������
            if(HosObject.countOrderVerifyExe(orderitem)==0)
            {
                theHosDB.theQueueLabDB.deleteByOrderId(theHO.theVisit.getObjectId(),"0", orderId);
                theVisitControl.intUnlockVisit(theHO.theVisit);
                theHO.clearFamily();
            }
            ////////////////////////////////////////////////////////////////////////////////////
            theHS.theResultSubject.notifySaveRemainLabResult(Constant.getTextBundle("��úѹ�֡��ä�ҧ���Ż") + " " +
                    Constant.getTextBundle("�������"), UpdateStatus.COMPLETE);
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
    //�ӡ���觼��Ż
     public void sendDataResultLab(Vector voi ,Vector vresultlab,int[] select)
     {  
        Constant.println("public void sendDataResultLab(Vector v_order ,Vector rl,int[] select)");
        Constant.println(UseCase.UCID_sendResultLab);
        String objectid =   null;
        if(theHO.theVisit == null){
            theUS.setStatus("��س����͡�����·������㹡�кǹ���",UpdateStatus.WARNING);
            return;
        }
        if(select.length == 0){
            theUS.setStatus("�ѧ��������͡��¡���Ż����ͧ��è���",UpdateStatus.WARNING);
            return;
        }
        Vector vvresultlab = getVVResultLab(voi,vresultlab);
        theConnectionInf.open();  
        try{
            theHO.date_time = theLookupControl.intReadDateTime();
            int count_exec = 0;
            //////////////////////////////////////////////////////////////////////////
            //��Ǩ�ͺ���Ż��������ҵç������͹��������
            /*�ѹ�֡�����ż��Ż ૵������Ѻresultlab*/
            String order_status = "";
            if(select.length==0)
            {
                theUS.setStatus("�������¡���Ż����ͧ��è���",UpdateStatus.WARNING);
                return;
            }
            for(int i=0;i<select.length;i++)
            {  
                OrderItem oi = (OrderItem)voi.get(select[i]);
                if(oi.status.equals(OrderStatus.REPORT)
                || oi.status.equals(OrderStatus.NOT_VERTIFY)
                || oi.status.equals(OrderStatus.DIS_CONTINUE)
                || oi.status.equals(OrderStatus.DISPENSE)){
                    theUS.setStatus("��س����͡��¡���Ż��������ʶҹд��Թ���",UpdateStatus.WARNING);
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
                theOrderControl.intSaveQueueOfOrder(oi,false,theHO.date_time);//�觼��Ż
            }
            if(count_exec==0)
            {
//                theUS.setStatus("�������¡���Ż����ͧ��è���",UpdateStatus.WARNING);     
                return;
            }
            //////////////////////////////////////////////////////////////////////////
            //��ҹ�����Ţͧ���Transfer�ͧ������ �ҡ���ź���Դ���ź���������
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
                theHS.theResultSubject.notifySendResultLab(Constant.getTextBundle("����觼Ūѹ�ٵ��ä") + " " +
                        Constant.getTextBundle("�������"),UpdateStatus.COMPLETE);
            }
            else
            {
                theHS.theResultSubject.notifySendResultLab(Constant.getTextBundle("����觼Ūѹ�ٵúҧ��¡��") + " " +
                         Constant.getTextBundle("�������"),UpdateStatus.COMPLETE);
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
     
   
    
    /**Return Vector �ͧ Lab_Result_Item */
    
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
               //int i = theHosDB.theQueueLabDB.deleteByVisitID(visit_id); amp:08/03/2549 �����Ż���Դ
               int i = theHosDB.theQueueLabDB.deleteByOrderId(visit_id,theHO.labQueueRemain,theHO.orderSecret); 
               theVisitControl.intUnlockVisit(theHO.theVisit);
               theHO.clearFamily();
               if(i ==1)
               {    
                   theHS.theResultSubject.notifyDeleteQueueLab(Constant.getTextBundle("ź����Ż") +" " +
                           Constant.getTextBundle("�������"),UpdateStatus.COMPLETE);
               }
        }
        catch(Exception ex)
        {    
             ex.printStackTrace(Constant.getPrintStream());
             theUS.setStatus(Constant.getTextBundle("ź����Ż") + " " +
                     Constant.getTextBundle("�Դ��Ҵ"),UpdateStatus.ERROR);
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
               theHS.theResultSubject.notifyXrayReportComplete(Constant.getTextBundle("���ź�����硫����") + " " +
                       Constant.getTextBundle("�������"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex){    
             ex.printStackTrace(Constant.getPrintStream());
             theUS.setStatus(Constant.getTextBundle("���ź�����硫����") + " " +
                     Constant.getTextBundle("�Դ��Ҵ"),UpdateStatus.ERROR);
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
               theHS.theResultSubject.notifyDeleteQueueLab(Constant.getTextBundle("�ѹ�֡��ä�ҧ���Ż") + " " +
                       Constant.getTextBundle("�������"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {    
             ex.printStackTrace(Constant.getPrintStream());
             theUS.setStatus(Constant.getTextBundle("�ѹ�֡��ä�ҧ���Ż") + " " +
                     Constant.getTextBundle("�Դ��Ҵ"),UpdateStatus.ERROR);
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
            theUS.setStatus("��س����͡��¡�õ�Ǩ�ѡ��",UpdateStatus.WARNING);
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
            theHS.theResultSubject.notifyDeleteLabOrder(Constant.getTextBundle("���ź��������¡�êѹ�ٵ��ä") + " " +
                    Constant.getTextBundle("�������"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {   
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("���ź��������¡�êѹ�ٵ��ä") + " " +
                    Constant.getTextBundle("�Դ��Ҵ"),UpdateStatus.WARNING);
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
