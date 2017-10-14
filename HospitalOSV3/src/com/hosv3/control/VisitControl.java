    /*
     *��������� transfer ��������������鹡�ú�ԡ��੾�����¡���ش������ҹ��
     *�ͧ�Ѻ���� transfer ����յ���������������� ʶҹШ��� 1 ����ͧ������
     *����ѧ����� transfer ���������ʶҹШ��� 2 ��ͧ����¹�� 3
     */
/*
 * VisitControl.java
 *
 * Created on 17 ���Ҥ� 2546, 22:59 �.
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
    /**��㹡�ä��Ң����Ţͧ�����ҹ*/
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
     * ��䢢�����㹵��ҧ visit  �¡������ҡ�� admit ŧ�
     * reverseAdmit ����ա�����Ţ AN ���¡��ԡ �������� �������� ����� null
     * �����䢢�����㹵��ҧ visit
     * ��Ǩ�ͺ����� Admit �����ѧ ��� ��� Admit
     * Admit ����
     * ��Ǩ�ͺ����� �Ţ an ��������������ͧ gen ���� �����Ҩ�����Ҩҡ������Ţ an �������������
     * ��觾���ҡ�� comment �ͧ�����繺ѡ�͹¡��ԡ AN henbe 01/03/2550
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
                theUS.setStatus(("�����¨�˹��·ҧ���ᾷ�������������ö��䢢�������"), theUS.WARNING);
                return false;
            }
            if(DateUtil.countDateDiff(admit.begin_admit_time,admit.begin_visit_time)<0){
                theUS.setStatus(("��س��к��ѹ Admit ��ѹ���ǡѹ������ѧ�ҡ�ѹ����Ѻ��ԡ��"),UpdateStatus.WARNING);
                return false;
            }
            if(!admit.doctor_discharge_time.equals("") && admit.doctor_discharge_time!=null
            && DateUtil.countDateDiff(admit.begin_admit_time,admit.doctor_discharge_time)>0){
                theUS.setStatus(("��س��к��ѹ Admit ��͹�ѹ��˹��·ҧ���ᾷ��"),UpdateStatus.WARNING);
                return false;
            }
            if(theLookupControl.readOption().admit.equals(Active.isEnable())) {
                boolean retb = DialogPasswd.showDialog(theHO,theUS,theHO.theEmployee.password);
                if(!retb) {
                    theUS.setStatus(("���ʼ�ҹ���١��ͧ"),UpdateStatus.WARNING);
                    return false;
                }
            }
            if(admit.bed.length()!=0)
                v_bed = theHosDB.theVisitDB.selectByBed(admit.bed,admit.ward);
            //�������§����դ����褹 ����� 011,023
            if(v_bed!=null && !v_bed.isEmpty()){
                Visit vadmit = (Visit)v_bed.get(0);
                if(!vadmit.getObjectId().equals(admit.getObjectId())){
                    //�����¡�� visit ����������㹪�ǧ�ͧ��� admit ������������Ǩ������� ���
                    //������� �ҡ���� ������Ңͧ visit ��������� ���������ҹ��¡��� ���Ңͧ�͡ visit ���������
                    if(!theUS.confirmBox(Constant.getTextBundle("�׹�ѹ������Ţ��§��� ���ͧ�ҡ��§�ѧ������դ�����������")
                            ,UpdateStatus.WARNING))
                        return false;
                }
                //����� 011 ���ѹ�Ѻ�á ��з������ҡ��� 011
                if(v_bed.size()>1){
                    theUS.setStatus(("�Ţ��§����դ����ҡ���� 1 �� ��س�������١��ͧ")
                            ,UpdateStatus.WARNING);
                    return false;
                }
            }
            String date_time = theLookupControl.intReadDateTime();
            //������ ADMIT
            if(admit.visit_type.equals(VisitType.OPD)) 
            {                
                String an_number = "";
                if(theReverseAdmit == null){
                    an_number = theHosDB.theSequenceDataDB.updateSequence("an",true); 
                }
                else{
                    Visit v_indb = theHosDB.theVisitDB.selectByVn(theReverseAdmit.an);
                    if(v_indb!=null){
                        theUS.setStatus(("�������ö���Ţ AN ���¡��ԡ�����ͧ�ҡ���Ţ�ѧ�����㹰ҹ����������")
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
                
                //����ͧź��Ǽ����´�����
                if(theHO.theListTransfer!=null)
                    theHosDB.theQueueTransferDB.delete(theHO.theListTransfer);
                theHO.theListTransfer = null;
                
                theHosDB.theTransferDB.updateFinishTimeVisit(admit.getObjectId(),date_time);
            }
            intSaveTransferThrow(theHO.theEmployee,date_time);
            intSaveTransaction(theHO.theServicePoint,date_time,admit.ward);
            theHosDB.theVisitDB.update(admit);
            theHS.theVisitSubject.notifyAdmitVisit(Constant.getTextBundle("��ùӼ�����������ʶҹм�������������"),UpdateStatus.COMPLETE);
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
     * �Դ visit ੾�м����·��١�Ѵ
     * ���������: Vector �ͧ appointment
     * �������͡:�����·��١�Ѵ���������Ţ VN
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
            theUS.setStatus(("��س����͡��¡�ùѴ���·���ͧ����Դ visit"),UpdateStatus.WARNING);
            return;
        }
        ////////////////////////////////////////////////////////////////////
        int error = 0;//check ��Ҽ������� visit ��������
        int visit = 0;//check ��Ҽ���������㹡�кǹ�����������
        int day = 0;//check ����ѹ���Ѵ�ç�Ѻ�ѹ���� visit ��������
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

                //��Ǩ�ͺ��� �� Visit �������� ����㹡�кǹ��������ѧ ���ͨ����ʴ� hn ��͹�����
                Visit theVisit = theHosDB.theVisitDB.selectVisitByPatientIDLast(thePatient.getObjectId());
                if(theVisit!=null && theVisit.visit_status.equals(VisitStatus.isInProcess())){
                    in_process = in_process + ", " + theVisit.hn;
                    visit = visit + 1;
                    continue;
                }
                //��ҡ�ùѴ��鹼������ѧ����� visit ����ѧ��ʶҹ��͡�ùѴ
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
                    theUS.setStatus(Constant.getTextBundle("�������ö visit �����Ţ HN")+" " +  in_process
                    + " " + Constant.getTextBundle("��")+" "+
                    Constant.getTextBundle("���ͧ�ҡ����������㹡�кǹ�������"),UpdateStatus.WARNING);
                    return;
                }
                else{
                    theUS.setStatus(Constant.getTextBundle("�������¡�ùѴ���͹���ҡ�кǹ��á�سҵ�Ǩ�ͺʶҹС�ùѴ"),UpdateStatus.WARNING);
                    return;
                }    
            }
            theUS.setStatus(("��ùӼ����¨ҡ��ùѴ�������кǹ����������"),UpdateStatus.COMPLETE);
            theHS.thePatientSubject.notifyResetPatient(Constant.getTextBundle("��ùӼ����¨ҡ��ùѴ�������кǹ����������"),UpdateStatus.COMPLETE);
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
     * @Author ��
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
            theHS.theVisitSubject.notifyUnlockVisit(Constant.getTextBundle("���ź����ź�������"),UpdateStatus.COMPLETE);
            return 1;
        }
        catch(Exception ex){
            theUS.setStatus(("���ź����ź�Դ��Ҵ"),UpdateStatus.ERROR);
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
     * @Author ��
     * @date 01/07/2549
     * @param remain 
     * @return 
     */
    public int deleteQueueTransfer(Vector vQueue,int[] select)
    {
        boolean ret = theUS.confirmBox("�׹�ѹ���ź�������͡�ҡ���",UpdateStatus.WARNING);
        if(!ret)
            return 2;

        theConnectionInf.open();
        try{
            for(int i=0;i<select.length;i++){
                ListTransfer qt = (ListTransfer)vQueue.get(i);
                theHosDB.theQueueTransferDB.deleteByVisitID(qt.visit_id);
            }
            theHS.theVisitSubject.notifyUnlockVisit(Constant.getTextBundle("���ź����ź�������"),UpdateStatus.COMPLETE);
            return 1;
        }
        catch(Exception ex){    
            theUS.setStatus(("���ź��ǼԴ��Ҵ"),UpdateStatus.ERROR);
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
     * @see �ѹ�֡������� NCD ����֧ Update Visit ����ǡѺ NCD
     * @Author amp
     * @date 19/06/2549
     * @duplicate : ¡��ԡ���������������� sumo 28/08/2549
     * @param theNCD 
     * @return 
     */
    public int savePatientNCD(NCD theNCD)
    {      
        int result = 0;
        theConnectionInf.open();
        try{
            result = intSaveNCD(theNCD, true, true,false);
            theHS.thePatientSubject.notifySavePatient(Constant.getTextBundle("��úѹ�֡������ NCD �ͧ�������������"),UpdateStatus.COMPLETE);
            return 1;
        }
        catch(Exception ex){    
            theUS.setStatus(("��úѹ�֡������ NCD �ͧ�����¼Դ��Ҵ"),UpdateStatus.ERROR);
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
     * @see �ѹ�֡������� NCD ����֧ Update Visit ����ǡѺ NCD
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
            //amp:16/08/2549:�����ʴ���ͤ�����͹��١��ͧ
            if(result == 1)
            {
                theHS.theVisitSubject.notifyVisitPatient(Constant.getTextBundle("��úѹ�֡������ NCD 㹡���Ѻ��ԡ���������")
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
     * @duplicate : ¡��ԡ���������������� sumo 28/08/2549
     * @param theNCD 
     * @return 
     * @deprecated ���ͺ¡��ԡ
     */
    public int deletePatientNCD(NCD theNCD)
    {
        int result = 0;
        theConnectionInf.open();
        try{
            theHosDB.theNCDDB.delete(theNCD);
            theHO.vNCD = theHosDB.theNCDDB.selectByPatientId(theHO.thePatient.getObjectId());
            theHS.theVisitSubject.notifyVisitPatient(Constant.getTextBundle("���ź������ NCD �ͧ�������������")
                    ,UpdateStatus.COMPLETE);
            return 1;
        }
        catch(Exception ex){    
            theUS.setStatus(("���ź������ NCD �ͧ�����¼Դ��Ҵ")
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
                theUS.setStatus(("��辺������ NCD ���зӡ�úѹ�֡"),UpdateStatus.WARNING);
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
                //㹡ó������ Gen ���
                boolean get_sequence = false;
                if(theHO.thePatient!=null)
                {
                    if(theNCD.ncd_number.equals("") && theNCD.getObjectId() != null)
                    {
                        theUS.setStatus(("��س��к������Ţ NCD"),UpdateStatus.WARNING);
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
                        theUS.setStatus(Constant.getTextBundle("�������Ţ�������") +
                                " " +
                                Constant.getTextBundle("��سҵ�Ǩ�ͺ�����Ţ�ա����"),UpdateStatus.WARNING);
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
     * �Ӽ������������кǹ����繿ѧ�ѹ����
     * �Ѻ����÷����繷������ҡ�ѧ�ѹ��ѡintReadVisitRet
     * �ӧҹ��ҡ visitcontrol ŧ��
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
            throw new Exception("UC:��س����͡������");
        }
        if(payment == null || payment.size()==0)  {
             throw new Exception("UC:�ѧ������Է������ѡ��");
        }
        Payment pm = (Payment)payment.get(0);
//        if(!pm.card_exp_date.equals("")) {
//            int diff = DateUtil.countDateDiff(pm.card_exp_date,theHO.date_time);
//            if(diff<0) {
//                throw new Exception("UC:�Է��������ء�سҵ�����آͧ�Է�ԡ�͹�Ѻ��ԡ��");
//            }
//        }
        Plan plan_active = theHosDB.thePlanDB.selectByPK(pm.plan_kid);
        if(!plan_active.isActive()){
            throw new Exception("UC:�Է�Զ١¡��ԡ��س����͡�Է�������͹�Ѻ��ԡ��");
        }
        if(patient.discharge_status_id.equals(Dischar.DEATH))  {
            throw new Exception("UC:���������ª��Ե�����������ö�Ӽ������������кǹ�����");
        }
        if(patient.patient_birthday.equals(""))  {
            throw new Exception("UC:������������ѹ�Դ�������ö�Ӽ������������кǹ�����");
        }
        if(patient.active.equals(Active.isDisable()))  {
            throw new Exception("UC:�����ż����¶١¡��ԡ�����������ö�Ӽ������������кǹ�����");
        }
        if(thePatientControl.intReadVisitRet(patient.getObjectId())!=null){
            throw new Exception("UC:�����ż���������㹡�кǹ��������������ö�Ӽ������������кǹ����ա��");
        }
        
    //VISIT /////////////////////////////////////////////////////////////////
        if(patient.hn.length()==0)  {
            throw new Exception("UC:���Ţ HN �ͧ�������繤����ҧ��س�������١��ͧ");
        }
        int count_hn = theHosDB.thePatientDB.selectCountHN(patient.hn);
        if(count_hn > 1)  {
            throw new Exception("UC:���Ţ HN �ͧ�����«�ӡ�س�¡��ԡ�����ż���������������§�����ǡ�͹");
        }
        if(!patient.pid.equals("")){
            int count_pid = theHosDB.thePatientDB.selectCountPID(patient.pid);
            if(count_pid > 1 && !patient.pid.equals(""))  {
                throw new Exception("UC:���Ţ�ѵû�ЪҪ��ͧ�����«�ӡ�س�¡��ԡ�����ż���������������§�����ǡ�͹");
            }
        }
//        Patient pt_src = theHosDB.thePatientDB.selectByPK(patient.getObjectId());
//        Family pt_des = patient.getFamily();
//        if(pt_src!=null && pt_des!=null){
//            if(!pt_src.pid.equals(pt_des.pid)
//            || !pt_src.fname.equals(pt_des.patient_name)
//            || !pt_src.lname.equals(pt_des.patient_last_name)){
//                boolean confirm = theUS.confirmBox("�����ż��������ç�Ѻ" +
//                        "\n �����Ż�Ъҡ� " + pt_des.patient_name + " " + pt_des.patient_last_name
//                        + " �Ţ�ѵ� " + pt_des.pid +" �ѹ�Դ " + pt_des.patient_birthday +
//                        "\n �����ż�����  " + pt_src.fname + " " + pt_src.lname
//                        + " �Ţ�ѵ� " + pt_src.pid+" �ѹ�Դ " + pt_src.birthday
//                        , UpdateStatus.WARNING);
////                if(!confirm)
////                    return -1;
//                throw new Exception("UC:�������Ż�Ъҡ���м���������ʹ���ͧ�ѹ��سҺѹ�֡�����ż������ա����");
//            }
//        }
        if(visit.begin_visit_time.startsWith("20"))  {
            throw new Exception("UC:�ѹ���㹡������Ѻ��ԡ�� �դ�Ңͧ�����١��ͧ "+ visit.begin_visit_time +" ��سҵ�Ǩ�ͺ�к���Ժѵԡ�âͧ����ͧ");
        }
    //VISIT /////////////////////////////////////////////////////////////////
        visit.vn = theHosDB.theSequenceDataDB.updateSequence("vn",true);
        Visit v_indb = theHosDB.theVisitDB.selectByVn(visit.vn);
        if(v_indb!=null)  {
            throw new Exception("UC:���Ţ VN �ѧ�����㹰ҹ�����š�سҵ�Ǩ�ͺ�Ţ VN ����ش");
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
                    Constant.getTextBundle("���Ţ�բͧ VN ����繻Ѩ�غѹ��õ�Ǩ�ͺ�Ţ�ӴѺ") +
                    " " +
                    Constant.getTextBundle("�׹�ѹ�����ҹ���")
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
//        //konshow �������Ш�����ö�֧��§ҹ���дǡ��� ���������ŷ��١��ͧ ������Ŵ������� sql updatev3_92.sql
        visit.visit_record_date_time = theHO.date_time;
//        //konshow �������Ш�����ö�֧��§ҹ���дǡ��� ���������ŷ��١��ͧ ������Ŵ������� sql updatev3_92.sql
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
    //Refer /////////////////////////////////////// �ѹ�֡������ Refer in
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
        {   //����
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
        //amp:06/04/2549 �������������ҹ�� ����� FN:theHO.setVisit(visit) �ѹ������� theHO.theAppointment
        //amp: 25/02/2549 ��� order ��ǧ˹�Ңͧ��ùѴ
        //tuk: 25/07/2549 ��������������ö��� Xray ��ǧ˹���� ���ͧ�ҡ initResultXray ��ͧ�� theHO.theVisit
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
                theHO.thePatient.pid = JOptionPane.showInputDialog(this.theUS.getJFrame(),"��س��к������Ţ�ѵû�ЪҪ� 13 ��ѡ");
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
                       , "�׹�ѹ�������¹�������ç����ҹ������ ʻʪ �ҡ "
                       +theHO.thePatient.patient_name
                       +" "+theHO.thePatient.patient_last_name + " ��� "
                       +rightData.getFname() + " " + rightData.getLname(), "�׹�ѹ�������¹�������ç����ҹ������ ʻʪ"
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
                   theUS.setStatus("�ѹ�֡�������������", UpdateStatus.COMPLETE);
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
            this.theUS.setStatus("������������������",UpdateStatus.WARNING);
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
            //��Ǩ�ͺ��Ъҡ����������������ա� �繼������ѵ��ѵ�
            if(theHO.theFamily==null){
                theUS.setStatus("��سҵ�Ǩ�ͺ�����繻�Ъҡá�͹��ù��������кǹ���",UpdateStatus.WARNING);
                return false;
            }
            if(theHO.thePatient==null){
                theUS.setStatus("��سҵ�Ǩ�ͺ�����繼����¡�͹��ù��������кǹ���",UpdateStatus.WARNING);
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
            //��Ǩ�ͺ��Ъҡ����������������ա� �繼������ѵ��ѵ�
            if(theHO.theFamily==null){
                theUS.setStatus(("��س����͡��Ъҡ�"),UpdateStatus.WARNING);
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
            
            theHS.theVisitSubject.notifyVisitPatient(Constant.getTextBundle("��ùӼ������������кǹ����������"),UpdateStatus.COMPLETE);
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
                theUS.setStatus(("��ùӼ������������кǹ��üԴ��Ҵ"),UpdateStatus.ERROR);
            status = false;
            return status;
        }
        finally{
//          theSystemControl.saveLog(HospitalosLog.UC_visitFamily, visit.getObjectId(), "�ӻ�Ъҡ��������кǹ���", status);
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
            theHS.theVisitSubject.notifyVisitPatient(Constant.getTextBundle("��ùӼ������������кǹ����������")
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
                theUS.setStatus(("��ùӼ������������кǹ��üԴ��Ҵ"),UpdateStatus.ERROR);
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
            theUS.setStatus(("�ѧ��������͡������"),UpdateStatus.WARNING);
            return false;
        }
        //amp:3/6/2549 ���㹡óդ�ҧ�ѹ�֡������ Drop Visit
        if(theHO.theVisit.visit_status.equals("2"))
        {
            theUS.setStatus(("�����������ʶҹФ�ҧ�ѹ�֡����"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals("3"))
        {
            theUS.setStatus(("�����������ʶҹШ���кǹ�������"),UpdateStatus.WARNING);
            return false;
        }
        if(!theHO.theVisit.lock_user.equals(theHO.theEmployee.getObjectId()))
        {
            theUS.setStatus(("�����¶١��͡�����������ö¡��ԡ�������Ѻ��ԡ����"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_type.equals(VisitType.IPD))
        {
            theUS.setStatus(Constant.getTextBundle("�������ö¡��ԡ��") +
                    " " +
                    Constant.getTextBundle("��س���͹��Ѻ��� Admit ��͹¡��ԡ����Ѻ��ԡ��"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.vBilling != null && !theHO.vBilling.isEmpty())
        {
            theUS.setStatus(Constant.getTextBundle("�������ö¡��ԡ��") +
                    " " +
                    Constant.getTextBundle("���ͧ�ҡ�ա���Ѻ�����Թ����"),UpdateStatus.WARNING);
            return false;
        }
//        boolean ret_confirm = theUS.confirmBox( ("��ͧ���¡��ԡ�������Ѻ��ԡ��")
//        + " " + Constant.getTextBundle("�׹�ѹ¡��ԡ�������Ѻ��ԡ��"),UpdateStatus.WARNING);
//        if(!ret_confirm){
//            return false;
//        }
//        if(theHO.vOrderItem!=null && theHO.vOrderItem.size()>0 ){
//            ret_confirm = theUS.confirmBox(("��ͧ���¡��ԡ��¡����觵�Ǩ�ѡ�Ҵ���")
//                    + " " + Constant.getTextBundle("�׹�ѹ"),UpdateStatus.WARNING);
//            if(!ret_confirm){
//                return false;
//            }
//        }
        String cause = DialogCause.showDialog(theUS.getJFrame(),Constant.getTextBundle("���¡��ԡ�������Ѻ��ԡ��"));
        if(cause.equals(""))
        {   
            theUS.setStatus(("��سҡ�͡���˵ء��¡��ԡ�������Ѻ��ԡ��"),UpdateStatus.WARNING);
            return  false;
        } 
        else if(cause.equals("CancelDialog"))
            return false;
        
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            //¡��ԡ�������Ѻ��ԡ��
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
            //�ӡ��ź��¡�èҡ��Ǽ�����
            theHosDB.theQueueTransferDB.deleteByVisitID(theHO.theVisit.getObjectId());
            //amp:21/7/2549:ź��Ǣͧ��ͧ�Ż�����ͧxray
            theHosDB.theQueueLabDB.deleteByVisitID(theHO.theVisit.getObjectId()); 
            theHosDB.theQueueXrayDB.deleteByVisitID(theHO.theVisit.getObjectId());  
            //����¹ʶҹС�ùѴ��Ѻ���͡�ùѴ����͹���
            Vector vApp = theHosDB.theAppointmentDB.selectByVN(theHO.theVisit.vn);
            for(int i=0,size=vApp.size();i<size;i++)
            {
                Appointment app = (Appointment)vApp.get(i);
                app.status = AppointmentStatus.WAIT;
                app.visit_id = "";
                app.vn = "";
                theHosDB.theAppointmentDB.update(app);
            }
            //�ӡ��ź��¡�� order �������ѧ����ա���ֹ�ѹ
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
            theHS.theVisitSubject.notifyDropVisit(Constant.getTextBundle("���¡��ԡ����Ѻ��ԡ�ü������������"),UpdateStatus.COMPLETE);
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
     * �� Function ��ҵ�ͧ��������͡ ��ͧ������� 1 
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
                theUS.setStatus(("��辺�����š���Ѻ��ԡ�âͧ������㹰ҹ������"),UpdateStatus.WARNING);
                return;
            }
            Patient patient = theHosDB.thePatientDB.selectByPK(visit.patient_id);
            if(patient==null)
            {
                theUS.setStatus(("��辺�����ż�����㹰ҹ������"),UpdateStatus.WARNING);
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

            //��Ǩ�ͺ����繼����·�����ź���Դ�������
            theHO.labQueueRemain = remain;               
            theHS.theVisitSubject.notifyReadVisit(Constant.getTextBundle("���¡�٢����š���Ѻ��ԡ�âͧ�������������"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(("���¡�٢����š���Ѻ��ԡ�âͧ�������Դ�����Դ��Ҵ"),UpdateStatus.ERROR);
        }        
        finally{
            theConnectionInf.close();
        }     
    }
    /**
     *hosv4
     *@author henbe
     *@see �ҡ�ա�����㹹���ͧ���� readNextVisit ���¹�
     */
    public void readPreviousVisit(Patient pt,Visit visit)
    {
        Constant.println("private void readPreviousVisit(Patient pt,Visit visit)");
        Constant.println(UseCase.UCID_readPreviousVisit);
        String objectid =   null;
        theConnectionInf.open();
        try{
            if(pt==null){
                theUS.setStatus(("��س����͡�����¡�͹��ôٻ���ѵԡ���ѡ��"),UpdateStatus.WARNING);
                return;
            }
            //�ѧ�ѹ����繡�ä鹵���ӴѺ�ѹ���ҡʹյ任Ѩ�غѹ �ѧ����ӴѺ�ش���¤�ͻѨ�غѹ
            //������� index ��͡���͹Ҥ�
            //theHosDB.theVisitDB.selectListByPtid(pt.getObjectId());
            Vector vvisit = theHO.vVisit;
            if(vvisit.isEmpty()){
                theUS.setStatus(("�����¤��������ջ���ѵԡ���Ѻ��ԡ��"),UpdateStatus.WARNING);
                return;
            }
            //i ��� index �ͧ visit �ش���·����Ҩд٢�����
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
                    theUS.setStatus(("����Ѻ��ԡ�ù���͡���Ѻ��ԡ�ä����á�������ö�٢����š�͹˹�ҹ����"),UpdateStatus.WARNING);
                    return;
                }
                //�������������� i �繤�ҷ���令����ʴ����
                i = i-1; 
            }
            else{
                if(vvisit.isEmpty()){
                    theUS.setStatus(("�ѧ����ջ���ѵԡ���Ѻ��ԡ�âͧ�����¤����"),UpdateStatus.WARNING);
                    return;
                }
                i=vvisit.size()-1;
            }
            if(theHO.isLockingVisit()) {
                theHO.theVisit.locking = "0";
                theHosDB.theVisitDB.update(theHO.theVisit);
            }
            theHO.clearVisit();
            //��� visit �������� visit �á����͹ʹյ����������
            theLookupControl.intReadDateTime();
            Visit in_visit = (Visit)vvisit.get(i);
            in_visit = theHosDB.theVisitDB.selectByPK(in_visit.getObjectId());
            theLookupControl.intReadDateTime();
            thePatientControl.intReadVisitSuit(in_visit);
            intLockVisit(theHO.date_time);
            intSaveTransferCatch(theHO.theEmployee, theHO.date_time);
            theHS.theVisitSubject.notifyReadVisit(Constant.getTextBundle("���¡�٢����š���Ѻ��ԡ�âͧ�������������"),UpdateStatus.COMPLETE);
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
     *@see �ҡ�ա�����㹹���ͧ���� readPreviousVisit ���¹�
     */
    public void readNextVisit(Patient pt,Visit visit)
    {   
        Constant.println("private void readPreviousVisit(Patient pt,Visit visit)");
        Constant.println(UseCase.UCID_readNextVisit);
        String objectid =   null;
        theConnectionInf.open();
        try{
            if(pt==null){
                theUS.setStatus(("��س����͡�����¡�͹��ôٻ���ѵԡ���ѡ��"),UpdateStatus.WARNING);
                return;
            }
            //�ѧ�ѹ����繡�ä鹵���ӴѺ�ѹ���ҡʹյ任Ѩ�غѹ �ѧ����ӴѺ�ش���¤�ͻѨ�غѹ
            //������� index ��͡���͹Ҥ�
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
                    theUS.setStatus(("����Ѻ��ԡ�ù���͡���Ѻ��ԡ�ä����ش�����������ö�٢�������ѧ�ҡ�����"),UpdateStatus.WARNING);
                    return;
                }
                //�������������� i �繤�ҷ���令����ʴ����
                i = i+1; 
                theLookupControl.intReadDateTime();
                if(theHO.isLockingVisit()) {
                    theHO.theVisit.locking = "0";
                    theHosDB.theVisitDB.update(theHO.theVisit);
                }
                //��� visit �������� visit �á����͹ʹյ����������
                Visit in_visit = (Visit)vvisit.get(i);
                in_visit = theHosDB.theVisitDB.selectByPK(in_visit.getObjectId());
                theLookupControl.intReadDateTime();
                thePatientControl.intReadVisitSuit(in_visit);
                intLockVisit(theHO.date_time);
                intSaveTransferCatch(theHO.theEmployee, theHO.date_time);
                theHS.theVisitSubject.notifyReadVisit(Constant.getTextBundle("���¡�٢����š���Ѻ��ԡ�âͧ�������������"),UpdateStatus.COMPLETE);
                if(theHO.theVisit != null)
                    objectid = theHO.theVisit.getObjectId();
                theSystemControl.setStatus(UseCase.TH_readNextVisit,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_readNextVisit,objectid,null,UpdateStatus.COMPLETE);
            }
            else{
                theUS.setStatus(("�����¨���кǹ�����������ա���Ѻ��ԡ����ѧ�ҡ���"),UpdateStatus.WARNING);
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
                theUS.setStatus(("��辺�����š���Ѻ��ԡ�âͧ������㹰ҹ������"),UpdateStatus.WARNING);
                return;
            }       
            Patient patient = theHosDB.thePatientDB.selectByPK(visit.patient_id);   
            if(patient==null)
            {
                theUS.setStatus(("��辺�����ż�����㹰ҹ������"),UpdateStatus.WARNING);
                return;
            }       
            theLookupControl.intReadDateTime();
            thePatientControl.intReadFamilySuit(patient.getFamily(),patient);
            thePatientControl.intReadPatientSuit(patient); 
            thePatientControl.intReadVisitSuit(visit);
            intLockVisit(theHO.date_time);
            intSaveTransferCatch(theHO.theEmployee, theHO.date_time);
            theHS.theVisitSubject.notifyReadVisit(Constant.getTextBundle("���¡�٢����š���Ѻ��ԡ�âͧ�������������"),UpdateStatus.COMPLETE);
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

            //�к��е�ͧ��Ǩ�ͺ����ҡ��˹��·ҧ���ᾷ�컡��
            //����˹�������� stat �е�ͧ���㹤�� stat ʶҹ��繤�ҧ�ѹ�֡
            //����˹��� stat ����ͧ���㹤�� stat ʶҹ��繨���кǹ���
            //Option ��˹���������ҹ stat ����ͧ���㹤�� stat ʶҹ��繨���кǹ���
        //��˹��¡���Թ��͹���ᾷ�� �稺Ẻ���;
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
            //��������ᾷ�줹�������ᾷ�줹����繤���Ǩ����
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
     * list �Է�ԡ���ѡ�Ңͧ�����µ�� Visit ID
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
        //�׹�ѹ��õ�Ǩ�ѡ�Ңͧᾷ��
        //henbe_just theHC.theVisitControl.confirmDoctorTreament();
        //�ش��ԡ�÷������ ��Ҩش��ԡ�ù������ͧ��Ǩ����ʴ���ª���ᾷ�����
        //��㹡óռ������繼��������������Թ 24 �� ���� ���繵�ͧź queue OPD �͡
                //old version call control function
                //theVisitControl.confirmDoctorTreament();
                ////theVisitControl.removeMapVisitInIPD(theVisit);
                //theVisitControl.unlockVisitForSendPatient(theVisit);
                //theVisitControl.sentPatientServicePoint(theTransfer,t);
                //notifyshowPanelAuthentication(authentication_id,1);
     //� �������ǡѹ�����ż����¨�������ش��ԡ�����ǡѹ��ҹ���������ö�������·����¡��� �������*
     */
    public void sendVisit(Transfer t)
    {
        Constant.println(UseCase.UCID_sendVisit);
        String objectid =   null;
        if(theHO.theVisit == null) {
            theUS.setStatus(("��س����͡�����·������㹡�кǹ���"),UpdateStatus.WARNING);
            return ;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("�����¨���кǹ��������������ö��䢢�������"),UpdateStatus.WARNING);
            return ;
        }
        if(!theHO.theVisit.lock_user.equals(theHO.theEmployee.getObjectId()))
        {
            theUS.setStatus(("�����¤����١��͡�¼���餹���"),UpdateStatus.WARNING);
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
                theUS.setStatus(("�����¤��������㹨ش��ԡ�����ᾷ�����ͧ���������"),UpdateStatus.WARNING);
                return;
            }
        }
        if(theHO.theVisit.doctor_dx.equals("")
        && theHO.theEmployee.authentication_id.equals(Authentication.DOCTOR) 
        && theHO.theEmployee.warning_dx.equals("1")){
            theUS.setStatus(("��س�ŧ�š�õ�Ǩ�ä�ͧ�����¤�����͹����ѧ�ش��ԡ�����"),UpdateStatus.WARNING);
            return;
        }
            
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            //�������áѹ���� henbe_ask
            int result = intRemoveMapVisitInIPD(theHO.theVisit);            
            //clear old transfer//////////////////////////////////////////////////
            intSaveTransferThrow(theHO.theEmployee, date_time);
            ////////////////////////////////////////////////////////////////////
            //��Ǩ�ͺ���ᾷ���Ǩ�������  ��ҵ�Ǩ�����ӡ���觼�ҹ��ͧᾷ���͹////////////////////////////
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
            theHS.theVisitSubject.notifySendVisit(Constant.getTextBundle("����觼�������ѧ�ش��ԡ���������"),UpdateStatus.COMPLETE);
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
     *@param  String visit_id ��� �Ţ Visit_id �� key ��ѡ�ͧ���ҧ,
     *        String lock ʶҹТͧ��� lock 㹤��駹�� ���� 0 ��� ��� lock ��� 1 ��� lock
     *@return   void
     *@since     version 2 bould 7
     *@version  0.0.1
     *@date 2/2/48
     *@update 
     *@description ��㹡�� update �����Ţͧ���ҧ t_queue_visit_transfer ��� visit_id �ͧ��� visit ���
     *             ��Т�鹡ѺʶҹС�� lock
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
            theHS.theVisitSubject.notifyUnlockVisit(Constant.getTextBundle("���������˹�Ҩ��������")
                ,UpdateStatus.COMPLETE);
            return;
        }
        if(visit == null){
            theHS.theVisitSubject.notifyUnlockVisit(Constant.getTextBundle("��س����͡�����·������㹡�кǹ���"),UpdateStatus.WARNING);
            return;
        }
        if(!visit.lock_user.equals(theHO.theEmployee.getObjectId())){
            if(!theHO.theEmployee.authentication_id.equals(Authentication.ONE)
            && !theHO.theEmployee.authentication_id.equals(Authentication.ADMIN)){
//                theUS.setStatus("�س������Է��㹡�ûŴ��͡�����¤���� ",UpdateStatus.WARNING);
                theHO.clearFamily();
                theHS.theVisitSubject.notifyUnlockVisit(Constant.getTextBundle("���������˹�Ҩ��������"),UpdateStatus.COMPLETE);
                return;
            }
        }
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            /*��Ǩ�ͺ���ᾷ���Ǩ�������*/
//            Constant.println("11111111111111111111111111111111111111111" + theHO.theEmployee.authentication_id);
            /////////////////////////////////////////////////////
            intSaveTransferThrow(theHO.theEmployee, date_time);
            theLookupControl.intConfirmDoctorTreatment(true,date_time);
            intUnlockVisit(visit);
            theHO.clearFamily();
            theHS.theVisitSubject.notifyUnlockVisit(Constant.getTextBundle("��ûŴ��͡�������������"),UpdateStatus.COMPLETE);
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
     *����� ADMIN ���͡����˹�� unlock �����
     *����� Lab ���͡�����˹����� unlock �����͡
     *����� Xray���͡�����˹����� unlock �����͡
     *����� ���� ���͡�����˹��  unlock �����͹������繤� lock
     * stock ������ҹ����
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
            Constant.println("������Է��㹡�ûŴ��͡�����¤����  The employee is not unlock");
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
            theUS.setStatus(("��س����͡��¡��"),UpdateStatus.WARNING);
            return false;
        }
        if(theVisitPayment == null){
            theUS.setStatus(("�������¡�÷���ͧ����"),UpdateStatus.WARNING);
            return false;
        }
        
        if(theVisitPayment.size()-1 == select){
            theUS.setStatus(("��¡�÷�����͡����¡���ش���¨֧�������¡�÷���ͧ����"),UpdateStatus.WARNING);
            return false;
        }
        
        Payment p = (Payment)theVisitPayment.get(select +1);
        if(!com.hospital_os.utility.Gutil.isSelected(p.visit_payment_active))
        {   theUS.setStatus(("�������ö����͹�Է�����"),UpdateStatus.WARNING);
            p = null;
            return false;
        }
        p = (Payment)theVisitPayment.get(select);
        if(p == null){
            theUS.setStatus(("��س����͡��¡��"),UpdateStatus.WARNING);
            return false;
        }
        if(p.visit_payment_active.equals("0")){
            theUS.setStatus(("�Է�Է��١¡��ԡ�����������ö������"),UpdateStatus.WARNING);
            return false;
        }
        //���˹����
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
                    Constant.getTextBundle("�������¹�ŧ�Է�ԡ���ѡ�Ңͧ�������������"),UpdateStatus.COMPLETE);
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
            theUS.setStatus(("��س����͡��¡��"),UpdateStatus.WARNING);
            return false;
        }
        if(theVisitPayment == null){
            theUS.setStatus(("�������¡�÷���ͧ����"),UpdateStatus.WARNING);
            return false;
        }
        if(select == 0){
            theUS.setStatus(("��¡�÷�����͡����¡���á�֧�������¡�÷���ͧ����"),UpdateStatus.WARNING);
            return false;
        }
        Payment p = (Payment)theVisitPayment.get(select);
        if(p == null){
            theUS.setStatus(("��س����͡��¡��"),UpdateStatus.WARNING);
            return false;
        }
        if(p.visit_payment_active.equals("0")){
            theUS.setStatus(("�Է�Է��١¡��ԡ�����������ö������"),UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try{
            theLookupControl.intReadDateTime();
            //change current payment
            //��ҵ�ǶѴ�������¹ priority ����¡��Ŵ���ŧ
            p.priority = String.valueOf(select-1);
            p.visit_payment_update_date_time = theHO.date_time;
            p.visit_payment_staff_update = this.theHO.theEmployee.getObjectId();
            
            theHosDB.thePaymentDB.update(p);
            
            theVisitPayment.remove(select);
            theVisitPayment.add(select - 1,p);
            //��ҵ�ǶѴ�������¹ priority ����¡��������Ң��
            //change next payment
            p = (Payment)theVisitPayment.get(select);
            p.priority = String.valueOf(select);
            p.visit_payment_update_date_time = theHO.date_time;
            p.visit_payment_staff_update = this.theHO.theEmployee.getObjectId();
            theHosDB.thePaymentDB.update(p);
            theHS.theVPaymentSubject.notifyUpVPaymentPriority(
                    Constant.getTextBundle("�������¹�ŧ�Է�ԡ���ѡ�Ңͧ�������������")
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
/*   set priority ��� �ա�èմ���§����ӴѺ
    //vector ��Ƿ�� 1 �� priority = n
    //vector ��Ƿ�� n �� priority = 1
    //������ա��ź���㴵�Ƿ�������ӡ��ҡ��������ҵ���ӴѺ
    //vector ��������� 1 ��� �� priority = 0
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
            theUS.setStatus(("��س����͡�Է�Է���ͧ������"),UpdateStatus.WARNING);
            return false;
        }
        if(theVisitPayment==null){
            theUS.setStatus(("��辺�������Է�ԡ���ѡ��"),UpdateStatus.WARNING);
            return false;
        }
        if(theVisitPayment.size()==1){
            theUS.setStatus(("�������öź�Է�ԡ���ѡ����е�ͧ�����Է�ԡ�͹ ���Ƕ֧��ź�Է�Է������ͧ�����"),UpdateStatus.WARNING);
            return false;
        }
        if(theVisitPayment.size()==row.length){
            theUS.setStatus(("�������öź�Է�ԡ���ѡ����"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit!=null && theHO.theVisit.is_discharge_money.equals("1")){
             theUS.setStatus(("�����¨�˹��·ҧ����Թ�����������ö����Է����"),UpdateStatus.WARNING);
             return false;
        }
        if(theHO.theVisit!=null && billing != null && billing.size()>0) {
            theUS.setStatus(("��س�ź��¡������稡�͹ �������Է�Լ����µ�ͧ�ѧ�������¡�äԴ�Թ"),UpdateStatus.WARNING);
            return false;
        }
        Payment payment= null;
        Payment paymentcancel = null;
        //��Ǩ�ͺ�óշ�� Vector ������� �����Ţͧ�Է�Է��¡��ԡ�Ҵ���
        //��ͧ��ͧ���੾�з����ҹ��ҹ��
        Vector vVisitPaymentCancel = new Vector();
        for(int i=theVisitPayment.size() -1 ; i>=0; i--) {
            payment = (Payment)theVisitPayment.get(i);
            //��Ǩ�ͺ�����¡�ù�� �� Active ������� �������� �������͡
            if(!com.hospital_os.utility.Gutil.isSelected(payment.visit_payment_active))
            {
                theVisitPayment.remove(i);
                vVisitPaymentCancel.add(payment);
            }
        }
        //��Ǩ�ͺ��͹��� �Է�Թ�����Է�Ե�ͧ������������
        //�������� ���ź�͡�ҡVector ���
        theConnectionInf.open();
        try{
            for(int j=row.length-1;j>=0;j--)
            {
                payment = (Payment)theVisitPayment.get(row[j]);
                 if(payment == null) {
                    theUS.setStatus(("�Դ�����Դ��Ҵ㹡�����͡�Է�����͡�����"),UpdateStatus.WARNING);
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
            theHS.theVPaymentSubject.notifyDeleteVPayment(Constant.getTextBundle("���ź��¡���Է�ԡ���ѡ��Ңͧ�������������"),UpdateStatus.COMPLETE);
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
     * ��㹡���ŧ�����Ţͧ�Է�ԡ���ѡ������� tooltiptext
     * @param payment �� Object �ͧ Payment
     * @param plan �� Object �ͧ Plan 
     * @return �� Object �ͧ ComboFix
     * @author padungrat(tong)
     * @date 04/04/2549,13:29
     */
    public com.hospital_os.utility.ComboFix getTextVisitPayment(Payment payment,Plan plan)
    {
        com.hospital_os.utility.ComboFix combofix = new com.hospital_os.utility.ComboFix();
        combofix.code = Constant.getTextBundle("����к�");
        
        if(plan != null)
        {   combofix.code = plan.description;
            if(!com.hospital_os.utility.Gutil.isSelected(payment.visit_payment_active))
            {
                combofix.code =Constant.getTextBundle("�١¡��ԡ�Է��") + " - " +  combofix.code ;
            }
        }
        String record ="";
        
        record = record + combofix.code;
        if(payment.visit_payment_staff_record.trim().length() != 0)
        {   record = record + "<BR>";
            record = record + Constant.getTextBundle("���ѹ�֡") + "  : " + theLookupControl.readEmployeeNameById(payment.visit_payment_staff_record) + ", " + Constant.getTextBundle("���ҷ��ѹ�֡") + " : " + DateUtil.getDateToString(DateUtil.getDateFromText(payment.visit_payment_record_date_time),true); ;
        }
        if(payment.visit_payment_staff_update.trim().length() != 0)
        {   record = record + "<BR>";
            record = record + Constant.getTextBundle("������") + "   : " + theLookupControl.readEmployeeNameById(payment.visit_payment_staff_update) + ", " + Constant.getTextBundle("���ҷ�����") + " : " + DateUtil.getDateToString(DateUtil.getDateFromText(payment.visit_payment_update_date_time),true); ;
        }
        if(payment.visit_payment_staff_cancel.trim().length() != 0)
        {   record = record + "<BR>";
            record = record + Constant.getTextBundle("���¡��ԡ") + ": " + theLookupControl.readEmployeeNameById(payment.visit_payment_staff_cancel) + ", " + Constant.getTextBundle("���ҷ��¡��ԡ") + " : " + DateUtil.getDateToString(DateUtil.getDateFromText(payment.visit_payment_cancel_date_time),true); ;
        }
        
        
        combofix.name = record;
        
        record = null;
        return combofix;
        
        
        
    }
    /**
     * ��㹡���Ҫ���ʡ�Ţͧ�����ҹ�ҡ key id
     * @param emid �� id �ͧ ���ҧ employee
     * @return �� String ����ժ��� ʡ�Ţͧ����� ���������ͨ� return �� ""
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
     * ��㹡���Ҫ��ͼ����ҹ
     * @return �� Vector 
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
     * ��㹡������¡���Է�Է��١¡��ԡ��� visit
     * @param visit_id �� ������ѡ�ͧ���ҧ t_visit
     * @return �� Vector �ͧ Object Payment
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
            theUS.setStatus(("��س����͡�����·������㹡�кǹ���"),UpdateStatus.WARNING);
            return ;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("�����¨���кǹ��������������ö��䢢�������"),UpdateStatus.WARNING);
            return ;
        }
        if(theHO.theVisit.visit_type.equalsIgnoreCase(VisitType.IPD)){
            theUS.setStatus(("������ Admit �繼�����������������ö�ҡ�͹��"),UpdateStatus.WARNING);
            return;
        }        
        theConnectionInf.open();
        try{
            p.observe = status;
            p.observe_user = theHO.theEmployee.getObjectId();//.employee_id;
            theHosDB.theVisitDB.update(p);
            if(status.equals(Active.isEnable()))
                theHS.theVisitSubject.notifyObservVisit(Constant.getTextBundle("�ѹ�֡��ýҡ�͹�ͧ�������������"),UpdateStatus.COMPLETE);
            else
                theHS.theVisitSubject.notifyObservVisit(Constant.getTextBundle("¡��ԡ��ýҡ�͹�ͧ�������������"),UpdateStatus.COMPLETE);
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
 * ��˹��·ҧ����Թ
 //�Ѻ�ӹǹ ��¡�õ�Ǩ�ѡ�ҷ���ѧ���Դ�Թ
            // 0 �� 2 �������� ��� 1 ����¡�� order ��Դ�Թ���� 2 �������¡�� order ����
            //1 ����¡�� order ��Դ�Թ����
            //��Ǩ�ͺ��� ��¡�ù�� ��ӹǳ����������������ѧ
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
                theUS.setStatus(("��س����͡�����·������㹡�кǹ���"),UpdateStatus.WARNING);
                return false;
            }
            String date_time = theLookupControl.intReadDateTime();
            //��Ǩ�ͺ��¡�� Order ����ѧ������º���¡�����Ǩ�ͺ�������������Һ
            ///////////////////////////////////////////////////////////////////////////////////////
            Vector vorder = theHosDB.theOrderItemDB.selectByVisitId(v.getObjectId());
            int checkorder = 0;
            for(int i=0;i<vorder.size();i++){
                OrderItem oi = (OrderItem)vorder.get(i);
                if(!oi.status.equals(OrderStatus.DIS_CONTINUE) && !oi.isChargeComplete())
                    checkorder++;
            }
            //����¡�÷���ѧ�����Դ�Թ�ҡ���� 1 ��¡��
            if(checkorder>0){
                theUS.setStatus(Constant.getTextBundle("�ѧ�����Դ�Թ") + " "+ checkorder + " " +
                        Constant.getTextBundle("��¡��") +
                        Constant.getTextBundle("�������ö��˹��·ҧ����Թ��"),UpdateStatus.WARNING);
                return false;
            }
            ///////////////////////////////////////////////////////////////////////////////////////
            //�������¡�÷���ѧ�����Դ�Թ ��С��Ǩ�ͺ�����¡�����˹�����ѧ������Ѻ��������������ҡ�ա��ͧ�ӹǹ�����������¡�͹
            Vector vbinv = theHosDB.theBillingInvoiceDB.selectByVisitId(v.getObjectId());
            int corder = 0;
            for(int i=0;i<vbinv.size();i++){
                BillingInvoice binv = (BillingInvoice)vbinv.get(i);
                if(binv.isActive() && binv.isBillingComplete())
                    checkorder++;
            }
            if(corder != 0) {
                theUS.setStatus(("�ѧ�����ӹǳ����������� ���������ö��˹��·ҧ����Թ��"),UpdateStatus.WARNING);
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
            //�óը�˹��·ҧ���ᾷ�� auto------------------------------------------------
            //henbe comment281006 intSaveQueueDispenseOPD(theHO.vOrderItem,date_time,v);
            this.intAutoCompleteVisit(v);

            if(v.visit_financial_record_date_time != null && !v.visit_financial_record_date_time.equals(""))
                v.visit_financial_record_staff = theHO.theEmployee.getObjectId();

            theHosDB.theVisitDB.update(v);
            theHO.clearFamily();
            return true;
    }
    /*
     *�繡�� ����Ҥ���ǪʶԵ�
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
     * �觼����¡�Ѻ ����
     */
    public void sendVisitBackWard(Visit visit)
    {
        Constant.println("public void sendVisitBackWard(Visit visit)");
        Constant.println(UseCase.UCID_sendVisitBackWard);
        String objectid =   null;
        if(theHO.theVisit == null) {
            theUS.setStatus(("��س����͡�����·������㹡�кǹ���"),UpdateStatus.WARNING);
            return ;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("�����¨���кǹ��������������ö��䢢�������"),UpdateStatus.WARNING);

            return ;
        }
        if(!theHO.theVisit.visit_type.equals(VisitType.IPD))
        {   
            theUS.setStatus(("�����·�����͡������繼�������������ö�觡�Ѻ������"),UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            //amp:20/02/2549
            Transfer transfer = (Transfer)theHO.vTransfer.get(theHO.vTransfer.size()-1);
            if(!transfer.ward_id.equals("")){
                theHosDB.theQueueTransferDB.deleteByVisitID(visit.getObjectId());
                theUS.setStatus(("��������������������������ö�觡�Ѻ�������ա"),UpdateStatus.WARNING);
                return;
            }
            theHosDB.theQueueTransferDB.deleteByVisitID(visit.getObjectId());
            //theHosDB.theTransferDB.updateFinishTimeVisit(visit.getObjectId(),date_time);//amp:20/02/2549
            intSaveTransferThrow(theHO.theEmployee,date_time);
            intSaveTransaction(theHO.theServicePoint,date_time,visit.ward);
            
            intUnlockVisit(visit);
            theHO.clearFamily();
            theHS.theVisitSubject.notifySendVisitBackWard("�觼����¡�Ѻ ���� �������",UpdateStatus.COMPLETE);
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
            theUS.setStatus("��س����͡��¡���غѵ��˵�����",UpdateStatus.WARNING);
            return 0;
        }
        if(accidentgroup.date_accident.equals("")){
            theUS.setStatus("��س��к��ѹ����Դ�غѵ��˵�",UpdateStatus.WARNING);
            return 0;
        }
        int result_loc = 0;
        theConnectionInf.open();
        try{
            theLookupControl.intReadDateTime();
            if(accidentgroup.getObjectId() == null)
            {
             /*   if(theHosDB.theAccidentGroupDB.selectByVN(accident.vn)!=null){
                     theUS.setStatus("�к��������ö�ѹ�֡�������غѵ��˵��ҡ���� 1 ����㹡���Ѻ��ԡ�ä���������"
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

                //���ҷ�� update
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
                    theUS.setStatus("�Դ�����Դ��Ҵ�ͧ������ visit", theUS.WARNING);
                    result = false;
                }
                //��Ǩ�ͺ��Ҷ١��˹��·ҧ���ᾷ�������ѧ
                if(visit.is_discharge_doctor.equals(Active.isEnable()))
                {
                    theUS.setStatus("�����¶١��˹��·ҧ���ᾷ�������������ö��䢢����Ź����", theUS.WARNING);
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
            theUS.setStatus("��س����͡�����·������㹡�кǹ���",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus("�����¨���кǹ��������������ö��䢢�������",UpdateStatus.WARNING);
            return false;
        }
            if(!theHO.theVisit.visit_type.equals(VisitType.IPD)){
                theUS.setStatus("��س����͡�������",UpdateStatus.WARNING); 
                return false;
            }
            if(theHO.theVisit.doctor_discharge_time.equals("")){
                theUS.setStatus("��س��к��ѹ���ҷ���˹���",UpdateStatus.WARNING); 
                return false;
            }
            if(DateUtil.countDateDiff(theHO.theVisit.doctor_discharge_time
                    ,theHO.theVisit.begin_admit_time)<0){
                theUS.setStatus("��س��к��ѹ��˹�����ѧ�ҡ�ѹ ADMIT",UpdateStatus.WARNING); 
                return false;
            }
                
            theHO.theVisit.is_discharge_ipd = "1";
            //theHO.theVisit.doctor_discharge_time = date_time;
            theHO.theVisit.doctor_discharge_user = theHO.theEmployee.getObjectId();
            theHosDB.theVisitDB.updateDischargeDoctor(theHO.theVisit);
            theHS.theVisitSubject.notifyDischargeDoctor("��è�˹��¼�������������",UpdateStatus.COMPLETE);
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
 *��˹��·ҧ���ᾷ��ͧ������ 
 * �Ѻ Visit �ѹ���ǡѺ theHO.theVisit
 * Death �Ѻ�Ҩҡ dialog ��õ�·�������͡�繤�� null ��
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
        ////����ǡѺ��õ��//////////////////////////////////////////////////////
        theConnectionInf.open();
        try{
            boolean ret = intDischargeDoctor(visit,dt,unlock);
            if(!ret)
                return;
            theHS.theVisitSubject.notifyDischargeDoctor("��è�˹��·ҧ���ᾷ���������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus("��è�˹��·ҧ���ᾷ��Դ��Ҵ",UpdateStatus.ERROR); 
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
            theUS.setStatus("��س����͡�����·������㹡�кǹ���",UpdateStatus.WARNING);
            return false;
        }
//        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
//        {
//            theUS.setStatus("�����¨���кǹ��������������ö��䢢�������",UpdateStatus.WARNING);
//            return false;
//        }
        if(!theHO.theEmployee.authentication_id.equals(Authentication.STAT)
        && theHO.theVisit.is_discharge_doctor.equals(Active.isEnable())){
            theUS.setStatus("�����¨�˹��·ҧ���ᾷ�������������ö��˹����ա��",UpdateStatus.WARNING); 
            return false;
        }
        if(theHO.theVisit.doctor_discharge_time.equals("")
        ||theHO.theVisit.doctor_discharge_time.equals(",")){
            theUS.setStatus("��س��к��ѹ���ҷ���˹���",UpdateStatus.WARNING); 
            return false;
        }
        if(!theHO.theVisit.begin_visit_time.equals("") && 
           !theHO.theVisit.doctor_discharge_time.equals("") && 
           DateUtil.countDateDiff(theHO.theVisit.doctor_discharge_time
                ,theHO.theVisit.begin_visit_time)<0){
            theUS.setStatus("��س��к��ѹ��˹�����ѧ�ҡ�ѹ����Ѻ��ԡ��",UpdateStatus.WARNING); 
            return false;
        }
        //����Ǫ�ѧ���ŧ�����ä������˹���
        if(theHO.vDiagIcd10 == null || theHO.vDiagIcd10.isEmpty() ){
            if(theHO.theEmployee.authentication_id.equals(Authentication.STAT)){
                theUS.setStatus("��سҺѹ�֡���� ICD10 ��͹��˹��·ҧ���ᾷ��",UpdateStatus.WARNING);
                return false;
            }
            if(!theUS.confirmBox("�׹�ѹ��è�˹��·ҧ���ᾷ�������ŧ�����ä",UpdateStatus.WARNING))
                return false;
        }
        
        boolean is_death1 = HosObject.isVisitDeath(visit);
//        if(theHO.thePatient.dischar.equals("1") && is_death1)
//        {
//            theUS.setStatus("���������ª��Ե�����������ö��˹��µ�����ա",UpdateStatus.WARNING);
//            return false;
//        }
        //�����ѡ�͡��ҡ�è�˹��·ҧ���ᾷ�������繵�ͧ�٢����� refer ���Ф����Ҩ��Ѻ���ѡ�ҷ��þ �ա
            Refer refer = theHosDB.theReferDB.selectByVisitIdType(visit.getObjectId(),Refer.REFER_OUT);
//            if(refer!=null && !HosObject.isVisitRefer(visit)){
//                theUS.setStatus("��س�¡��ԡ�����š���觵�͡�͹��˹��¤��� �����ҧ���",UpdateStatus.WARNING); 
//                return false;
//            }
            dt = theHosDB.theDeathDB.selectByPatientId(theHO.thePatient.getObjectId());
            if(dt!=null && !HosObject.isVisitDeath(visit)){
                theUS.setStatus("��س�¡��ԡ�����š�õ�¡�͹��˹��¤��� �����ҧ���",UpdateStatus.WARNING); 
                return false;
            }    
            if(theLookupControl.readOption().discharge.equals(Active.isEnable())) {
                boolean retb = DialogPasswd.showDialog(theHO,theUS,theHO.theEmployee.password);
                if(!retb) {
                    theUS.setStatus("���ʼ�ҹ���١��ͧ",UpdateStatus.WARNING);
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
            //�ҡ���˹�ҷ�� stat ��˹��¡��ź�͡�ҡ�к���� �ҡ��˹��·ҧ�Թ����
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
    
    /*�ٻ���ѵԡ���Ѻ��ԡ�âͧ������*/
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
            theUS.setStatus("��س����͡�����·������㹡�кǹ���",UpdateStatus.WARNING);
            return;
        }
        if(!visit.is_discharge_doctor.equals(Active.isEnable())) {
            theUS.setStatus("�������ѧ������˹��·ҧ���ᾷ��",UpdateStatus.WARNING);
            return;
        }
//        if(theHO.theDeath!=null && !theHO.theDeath.vn_id.equals(visit.getObjectId())){
//            theUS.setStatus("���������ª��Ե�����������ö��͹��Ѻ���䢢������� ���ա",UpdateStatus.WARNING);
//            return;
//        }
        boolean retb = DialogPasswd.showDialog(theHO,theUS,theHO.theEmployee.password);
        if(!retb) {
            theUS.setStatus("���ʼ�ҹ���١��ͧ",UpdateStatus.WARNING);
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
                if(theUS.confirmBox("�������š���觵�� Refer ��ͧ���¡��ԡ�����š���觵���������"
                        ,UpdateStatus.WARNING)){
                    theHosDB.theReferDB.updateActiveByVidOut("0",visit.getObjectId(),Active.isEnable());
                    visit.refer_out = "";
                }
            }
            ////����ǡѺ��õ��//////////////////////////////////////////////////////
            
            if(theHO.thePatient.discharge_status_id.equals("1")
            && theUS.confirmBox("�������š�õ�� ��ͧ���¡��ԡ�����š�õ���������",UpdateStatus.WARNING)) {
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
            theHS.theVisitSubject.notifyReverseDoctor("��͹��Ѻ��è�˹��·ҧ���ᾷ���������",UpdateStatus.COMPLETE);
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
   /*��͹��Ѻ��è�˹��·ҧ����Թ*/
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
             theUS.setStatus("��س����͡�����·������㹡�кǹ���",UpdateStatus.WARNING);
             return ;
        }
        if(!visit.is_discharge_money.equals(Active.isEnable())){
             theUS.setStatus("�������ѧ������˹��·ҧ����Թ",UpdateStatus.WARNING);
             return ;
        }
        boolean retb = DialogPasswd.showDialog(theHO,theUS,theHO.theEmployee.password);
        if(!retb) {
            theUS.setStatus("���ʼ�ҹ���١��ͧ",UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            visit.is_discharge_money = "0";
            visit.visit_status = VisitStatus.isInProcess();
            theHosDB.theVisitDB.update(visit);
            intEditVisitInDespenseToZero(visit);
            //�е�ͧ�����͹���ź���Code                         
            intSaveTransaction(theHO.theServicePoint,date_time, "");                         
            theHosDB.theQueueICDDB.deleteByVisitID(visit.getObjectId()); 
            theHosDB.theQueueDespenseDB.deleteByVisitID(visit.getObjectId()); 

            theHS.theVisitSubject.notifyReverseFinancial("��͹��Ѻ��è�˹��·ҧ����Թ�������"
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
  *base function from ��㹡�úѹ�֡��Ǽ�������������͡��Ƿ���Ѻ��ԡ��
  
    private void intSaveMapQueueVisit(MapQueueVisit mapQueueVisit) throws Exception
    {
        if(!theLookupControl.readOption().inqueuevisit.equals(Active.isEnable()))
            return ;
        if(mapQueueVisit==null){
            throw new Exception("�����Ť������Ѻ�������Ѻ��ԡ�üԴ��Ҵ");
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
 
    //����繼������ ���Ǥ�ҧ������ش��ԡ�ù���Թ 24 �������������зӡ�� inactive mapQueueVisit 
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
            // �ӡ���Ң����Ţ�����ʴ��ͧ��� Map
            MapQueueVisit mapQueueVisit = theHosDB.theMapQueueVisitDB.selectByVisitID(visit.getObjectId());
            if((mapQueueVisit != null))
            {
                mapQueueVisit.active = Active.isDisable();
                theHosDB.theMapQueueVisitDB.update(mapQueueVisit);
            }
            return 1;
        }
    }
    
    /*���Է�����ѡ�һ�Шӡ���Ѻ��ԡ�ä��駹�鹨ҡ list*/    
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
            theUS.setStatus("��س����͡�����·������㹡�кǹ���",UpdateStatus.WARNING);
            return ;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus("�����¨���кǹ��������������ö��䢢�������",UpdateStatus.WARNING);
            return ;
        }
        if(thePaymentNow==null) {
            theUS.setStatus("����Ѻ�������Է�ԡ���ѡ�Ҩҡ˹�Ҩ��Դ�����Դ��Ҵ",UpdateStatus.WARNING);
            return;
        }
        if(thePaymentNow.plan_kid==null || thePaymentNow.plan_kid.equals("")){
            theUS.setStatus("��س����͡�Է�ԡ���ѡ��",UpdateStatus.WARNING);
            return;
        }
        if(thePaymentNow.card_ins_date.length()==10) {
            if(DateUtil.countDateDiff(thePaymentNow.card_ins_date,theConnectionInf)==1) {
                theUS.setStatus("��س��к��ѹ�͡�ѵ����ѹ�ʹյ",UpdateStatus.WARNING);
                return;
            }
        }
        Date dateins = DateUtil.getDateFromText(thePaymentNow.card_ins_date);
        Date dateexp = DateUtil.getDateFromText(thePaymentNow.card_exp_date);
        if(dateins != null && dateexp != null)
        {
            int date_valid = DateUtil.countDateDiff(thePaymentNow.card_ins_date,thePaymentNow.card_exp_date);
            if(date_valid>0){
                theUS.setStatus("�ѹ����͡�ѵ�����ѹ�����������ժ�ǧ������١��ͧ",UpdateStatus.WARNING);
                return;
            }
        }
        if(thePaymentNow.hosp_main.equals("")){
            theUS.setStatus("��سҡ�͡����ʶҹ��Һ����ѡ",UpdateStatus.WARNING);
            return;
        }
        //��ͧ��ͧ���੾�з����ҹ��ҹ��
        Vector vVisitPaymentCancel = new Vector();
        for(int i=vVisitPayment.size() -1 ; i>=0; i--) {
            Payment p = (Payment)vVisitPayment.get(i);
            //��Ǩ�ͺ�����¡�ù�� �� Active ������� �������� �������͡

            if(!com.hospital_os.utility.Gutil.isSelected(p.visit_payment_active))
            {
                vVisitPayment.remove(i);
                vVisitPaymentCancel.add(p);
            }
        }
        //Payment pp = (Payment)vVisitPayment.get(0);
        //����Ҷ���Է�ԫ�ӡѹ�е�ͧ�ѹ�֡�Ѻŧ���Է����������
       // Vector theVisitPayment = vVisitPayment;
        boolean samepayment = false;
        for(int i=0; i<vVisitPayment.size(); i++) {
            Payment p = (Payment)vVisitPayment.get(i);
            if(p.plan_kid.equals(thePaymentNow.plan_kid)) {
                if(thePaymentNow.getObjectId()==null) {
                    samepayment = true;
                    theUS.setStatus("�����Է�ԡ���ѡ�ҫ�������",UpdateStatus.WARNING);
                    return;
                }
            }
        }
        //��Ǩ�ͺ����ѧ����¡�÷��١¡��ԡ���������������������������Ѻ�ͧ�������
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
            //��Ǩ�ͺ����ѧ����¡�÷��١¡��ԡ���������������������������Ѻ�ͧ�������
            if(vVisitPaymentCancel.size() >0)
            {
                 for(int i=0; i<vVisitPaymentCancel.size(); i++) {
                    Payment p = (Payment)vVisitPaymentCancel.get(i);
                    vVisitPayment.add(p);
                }   
            }
            //����Է�Ի�Шӵ�Ǽ������繤����ҧ��кѹ�֡�Է�Թ�����Է�Ի�Шӵ�����ѹ��
            if(theHO.vPatientPayment.isEmpty())
            {
                PatientPayment pp = new PatientPayment(thePaymentNow);
                thePatientControl.intSavePatientPayment(theHO.thePatient,theHO.theFamily,theHO.vPatientPayment,pp);
            }
            theHO.vVisitPayment = theHosDB.thePaymentDB.selectByVisitId(theHO.theVisit.getObjectId());
            theUS.setStatus("��úѹ�֡�Է�ԡ���ѡ���������",UpdateStatus.COMPLETE);
            theHS.theVPaymentSubject.notifySaveVPayment(Constant.getTextBundle("��úѹ�֡�Է�ԡ���ѡ���������"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            theUS.setStatus("��úѹ�֡�Է�ԡ���ѡ�ҼԴ��Ҵ",UpdateStatus.ERROR);
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
            theUS.setStatus("��سҡ�͡�Ţ��Ƿ���繵���Ţ",UpdateStatus.WARNING);
            return false;
        }
        theConnectionInf.open();
        try{
            list.queue = value;
            int ret = theHosDB.theQueueTransferDB.update(list);
            theUS.setStatus("��úѹ�֡�Ţ����������",UpdateStatus.COMPLETE);
            return ret>0;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus("��úѹ�֡�Ţ��ǼԴ��Ҵ",UpdateStatus.ERROR);
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
     *  ��㹡�� list �����·������㹨ش��ԡ�� �¨��觢��������
     *  service_point_id = key ��ѡ�ͧ���ҧ �¨��� key ��ѡ�ͧ���ҧ service_point
     *  employee_id_doctor = key ��ѡ�ͧ���ҧ �¨��� key ��ѡ�ͧ���ҧ Employee ੾�Шش��ԡ�÷������ͧ��Ǩ
     *  choose = ���͡����繼������ ���� �����¹͡
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
     *  ��㹡�� list �����·������㹨ش��ԡ�� �¨��觢��������
     *  service_point_id = key ��ѡ�ͧ���ҧ �¨��� key ��ѡ�ͧ���ҧ service_point
     *  employee_id_doctor = key ��ѡ�ͧ���ҧ �¨��� key ��ѡ�ͧ���ҧ Employee ੾�Шش��ԡ�÷������ͧ��Ǩ
     *  choose = ���͡����繼������ ���� �����¹͡
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
     *  ��㹡�� list �����·������㹨ش��ԡ�� �¨��觢��������
     *  service_point_id = key ��ѡ�ͧ���ҧ �¨��� key ��ѡ�ͧ���ҧ service_point
     *  employee_id_doctor = key ��ѡ�ͧ���ҧ �¨��� key ��ѡ�ͧ���ҧ Employee ੾�Шش��ԡ�÷������ͧ��Ǩ
     *  choose = ���͡����繼������ ���� �����¹͡
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
            theUS.setStatus("��س����͡�����·������㹡�кǹ���",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus("�����¨���кǹ��������������ö��䢢�������",UpdateStatus.WARNING);
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
            theUS.setStatus("��س����͡�����·������㹡�кǹ���",UpdateStatus.WARNING);
            return false;
        }
//        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
//        {
//            theUS.setStatus("�����¨���кǹ��������������ö��䢢�������",UpdateStatus.WARNING);
//            return false;
//        }
        /*
         *amp:16/5/2549 ���ͧ�ҡ����������͡��ҵ�駤���� �����ա������¹��������������������ѹ��Ѻ��õ�駤���� �������ö��ҵ�駤�����͡��
         *����������ҵ�駤�����͡ ������ͧ��Ǩ�ͺ���������
        */
        if(pregnant)
        {
            if(theHO.thePatient.f_sex_id.equals(Sex.isMAN()))
            {   
                theUS.setStatus("�������繪�µ�駤���������",UpdateStatus.WARNING);
                return false;
            }
            if(Integer.parseInt(DateUtil.calculateAge(theHO.thePatient.patient_birthday,theHO.date_time)) < 10)
            {
                theUS.setStatus("�����������ع��¡��� 10 ���������ö��駤������",UpdateStatus.WARNING);
                return false;
            }
        }
        theConnectionInf.open();
        try{
            String preg = "0";
            if(pregnant) preg = "1";
            theHO.theVisit.pregnant = preg;
            theHosDB.theVisitDB.updateVisitPregnant(preg,theHO.theVisit.getObjectId());
            
            //amp:01/04/2549 ��Ǩ����ա��������ҷ���ջ�ԡ����ҡѺ��õ�駺�ҧ�������� �������� set active �ͧ�ҷ���ջ�ԡ����Ҵ����� 0
            //㹡ó� Update
            if(theLookupControl.readOption().isUseDrugInteract())
            {
            int is_interaction = theHosDB.theOrderDrugInteractionDB.updatePregnantByVisitId(theHO.theVisit.getObjectId(), theHO.theVisit.pregnant);            
            //amp:04/04/2549 㹡ó� ��������            
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
                    theUS.setStatus(Constant.getTextBundle("��õ�駤�����ջ�ԡ����ҡѺ")+" " + interaction ,UpdateStatus.WARNING);
                }
                orderItem=null;
                drugStandardMapItem=null;
                drugInteraction=null;
                interaction=null;
                std_old=null;
            }
            }
            theUS.setStatus("�ѹ�֡��õ�駤�����������" ,UpdateStatus.COMPLETE);
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
   
    /*���ź�������Է�������㹡���Ѻ��ԡ��*/
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
            theHS.theVisitSubject.notifyDeleteVisitPayment("ź�Է���������������º����", UpdateStatus.COMPLETE);
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
     *��ҧ�ѹ�֡ saveCause
     ** ����Ҥ���ǪʶԵ� 
     * ����稡���觼����·���к�
     * ź�������͡�ҡ��� opd
     ** ź�������͡�ҡ��� lab
     *�繡�è�˹��·ҧ����Թ
     */
    public boolean remainDoctorDischarge()
    {   
        Constant.println("public void endVisitForStat(Visit theVisit,Vector theTransfer)");
        Constant.println(UseCase.UCID_remainVisit);
        String objectid =   null;
        if(theHO.theVisit != null)
            objectid = theHO.theVisit.getObjectId();
        if(theHO.theVisit == null) {
            theUS.setStatus("��س����͡�����·������㹡�кǹ���",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus("�����¨���кǹ��������������ö��䢢�������",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals("2"))
        {   
            theUS.setStatus(("�����������ʶҹФ�ҧ�ѹ�֡����"),UpdateStatus.WARNING);
            return  false;
        }
        if(theLookupControl.readOption().commit.equals(Active.isEnable())) {
            boolean retb = DialogPasswd.showDialog(theHO,theUS,theHO.theEmployee.password);
            if(!retb) {
                theUS.setStatus("���ʼ�ҹ���١��ͧ",UpdateStatus.WARNING);
                return false;
            }
        }
        String cause = DialogCause.showDialog(theUS.getJFrame(),Constant.getTextBundle("��ä�ҧ�ѹ�֡"));
        if(cause.equals(""))
        {   
            theUS.setStatus(("��سҡ�͡���˵ء�ä�ҧ�ѹ�֡"),UpdateStatus.WARNING);
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

        //���Ŵ��ʹ��͹/////////////////////////////////////////////////
            theHO.theVisit.visit_status = VisitStatus.isInStat();
            theHO.theVisit.financial_discharge_user = theHO.theEmployee.getObjectId();
            theHO.theVisit.financial_discharge_time = dt;
            theHO.theVisit.locking = "0";
            theHO.theVisit.is_discharge_money = "1";
            theHO.theVisit.stat_dx = cause;
            theHO.theVisit.visit_status = "2";        
            theHosDB.theVisitDB.update(theHO.theVisit);
        //���Ŵ��ʹ��͹/////////////////////////////////////////////////
            theHO.clearVisit();
            theHS.theVisitSubject.notifyRemainDoctorDischarge(
                "��ä�ҧ�ѹ�֡�������������",UpdateStatus.COMPLETE);
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
            theUS.setStatus("��س����͡������ Refer ��͹ź",UpdateStatus.WARNING);
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
     * ��Ǩ�ͺ����٧�ش�ͧ�Ţ sequence
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
     *@see : �ѹ�֡���й�
     *@param : Vector ���й�
     *@return void
     */
    public void saveGuideHealthEducation(Vector guide)
    {
        Constant.println(UseCase.UCID_saveGuideHealthEducation);
        String objectid =   null;
        if(theHO.theVisit == null) {
            theUS.setStatus("��س����͡�����·������㹡�кǹ���",UpdateStatus.WARNING);
            return ;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus("�����¨���кǹ��������������ö��䢢�������",UpdateStatus.WARNING);
            return ;
        }
        if(guide==null)
        {   
            theUS.setStatus("�ѧ����բ����Ť��й�",UpdateStatus.WARNING);            
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
            theUS.setStatus("�ѹ�֡���й��������",UpdateStatus.COMPLETE);
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
     * ��ѧ�ѹ������ѹ�֡��� refer �ѹ�շ�衴�����������ᾷ���Ǩ�ͺ�����䢷ѹ�� ����
     * �ѹ�ջѭ���������ҡ㹡�úѹ�֡����� case �����ش������ҡ
     * @name �ѹ�֡����觵�ͼ�����
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
            theUS.setStatus("��س����͡�����·���ͧ��� Refer ��͹�ӡ�úѹ�֡", theUS.WARNING);
            return false;
        }
        if(thePatient.discharge_status_id!=null && thePatient.discharge_status_id.equals(Active.isEnable()))
        {
            theUS.setStatus("�������ö Refer ���������·����Ե������", theUS.WARNING);
            return false;
        }
        if(theVisit == null)
        {
            theUS.setStatus("��س����͡�����·������㹡�кǹ���", theUS.WARNING);
            return false;
        }
//        if(theVisit.is_discharge_doctor.equals(Active.isEnable()) && refer.getObjectId()==null)
//        {
//            theUS.setStatus("�����¨�˹��·ҧ���ᾷ�������������ö�ѹ�֡�觵����", theUS.WARNING);
//            return false;
//        }
        if(DateUtil.countDateDiff(refer.date_refer,theVisit.begin_visit_time)<0){
            theUS.setStatus("��س��к��ѹ����觵����ѧ�ѹ�������Ѻ��ԡ��",UpdateStatus.WARNING); 
            return false;
        }
        if( !refer.date_refer.equals("") 
            && !theVisit.doctor_discharge_time.equals("")
            && DateUtil.countDateDiff(refer.date_refer,theVisit.doctor_discharge_time)>0){
            theUS.setStatus("��س��к��ѹ����觵�͡�͹�ѹ��˹��·ҧ���ᾷ��",UpdateStatus.WARNING); 
            return false;
        }
//        if(refer.getObjectId()==null){
//            if(theVisit.is_discharge_doctor.equals(Active.isEnable()) && refer.refer_out.equals(Active.isEnable())) {
//                theUS.setStatus("�����¶١��˹��·ҧ���ᾷ�������������ö�ѹ�֡����觵���� ��س���͹��Ѻ��è�˹���", theUS.WARNING);
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
                //comment ������ҵ͹��˹��·ҧ���ᾷ��óռ�����㹨����͡�ͧ  
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
            //��� dialog ������¡�ѹ��繺�ҧ
            theUS.setStatus("��úѹ�֡�����š�� Refer �������", theUS.COMPLETE);
            //theHS.theVisitSubject.notifyDischargeDoctor("��úѹ�֡�����š�� Refer �������", theUS.COMPLETE);
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
                theUS.setStatus("��س����͡ʶҹ��Һ�ŷ���觵�ͼ�����", theUS.WARNING);
                return false;
            }
            if(refer.office_refer.equals("") && refer.refer_out.equals(Active.isDisable()))
            {
                theUS.setStatus("��س����͡ʶҹ��Һ�ŷ���Ѻ��", theUS.WARNING);
                return false;
            }
            if(refer.office_refer.equals(theHO.theSite.off_id)) {
                theUS.setStatus("ʶҹ��Һ�ŷ�� Refer ��ͧ��ʶҹ��Һ�����", UpdateStatus.WARNING);
                return false;
            }            
            if(refer_check!=null && refer.getObjectId()==null && refer.refer_out.equals(refer_check.refer_out)){
                theUS.setStatus("�բ����š�� refer ����㹡���Ѻ��ԡ�ä��駹���س���䢢��������", UpdateStatus.WARNING);
                return false;
            }
            if(refer.history_lab.length()>4000){
                theUS.setStatus(Constant.getTextBundle("�����ż��Ż�ҡ�Թ���ҷ��������кѹ�֡��")+" "+
                        Constant.getTextBundle("��س�Ŵ����ҳ�����ŷ��ѹ�֡"), UpdateStatus.WARNING);
                return false;
            }
            if(refer.first_treatment.length()>4000){
                theUS.setStatus(Constant.getTextBundle("��������¡�ú�ԡ�÷������ҡ�Թ���ҷ��������кѹ�֡��")+" "+
                        Constant.getTextBundle("��س�Ŵ����ҳ�����ŷ��ѹ�֡"), UpdateStatus.WARNING);
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
                //�������͡���˵ش��� 
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
     *  ��㹡�� list �����·������㹨ش��ԡ�� �¨��觢��������
     *  service_point_id = key ��ѡ�ͧ���ҧ �¨��� key ��ѡ�ͧ���ҧ service_point
     *  employee_id_doctor = key ��ѡ�ͧ���ҧ �¨��� key ��ѡ�ͧ���ҧ Employee ੾�Шش��ԡ�÷������ͧ��Ǩ
     *  choose = ���͡����繼������ ���� �����¹͡
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
     *  ��㹡�� list �����·������㹨ش��ԡ�� �¨��觢��������
     *  service_point_id = key ��ѡ�ͧ���ҧ �¨��� key ��ѡ�ͧ���ҧ service_point
     *  employee_id_doctor = key ��ѡ�ͧ���ҧ �¨��� key ��ѡ�ͧ���ҧ Employee ੾�Шش��ԡ�÷������ͧ��Ǩ
     *  choose = ���͡����繼������ ���� �����¹͡
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
    *@see: ��Ǩ�ͺ��Ҽ����·�����͡���Ҩҡ������͡㹤���ͨ������������
    *@param: �����
    *@return: boolean true-���͡�Ҩҡ����ͨ�����,false-��������͡�Ҩҡ����ͨ�����
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
            theUS.setStatus("��س����͡�������غѵ��˵����ͧ���ź��͹������ź", theUS.WARNING);
            return result_loc;
        }
         
        Visit visit = readVisitByVidRet(accident.vn_id);
        if(visit == null)
        {
            theUS.setStatus("�Դ�����Դ��Ҵ�ͧ������ visit", theUS.WARNING);
            return result_loc;
        }
        //��Ǩ�ͺ��Ҷ١��˹��·ҧ���ᾷ�������ѧ
        if(visit.is_discharge_doctor.equals(Active.isEnable()))
        {
            theUS.setStatus("�����¶١��˹��·ҧ���ᾷ�������������öź��������", theUS.WARNING);
            visit = null;
            return result_loc;
        }
        if(!theUS.confirmBox("�׹�ѹ���ź�������غѵ��˵�",UpdateStatus.WARNING))
            return result_loc;
        
        //��Ǩ�ͺ��Ҷ١��˹��·ҧ����Թ���������ѧ��Ҷ١��˹������ǡ�����ʴ���ͤ�����͹
        theConnectionInf.open();
        try
        {
            accident.active = Active.isDisable();
            accident.staff_cancel = theHO.theEmployee.getObjectId();
            accident.cancel_date_time = theLookupControl.intReadDateTime();
            result_loc = theHosDB.theAccidentDB.delete(accident);
            AccidentType tempAccidentType = null;
            tempAccidentType = getAccidentTypeByID(accident.b_setup_accident_type_id);
            // ¡��ԡ仡�͹���� � ��ͧ Dx ��Ҽ����ź��¡���͡���� ������ mapdx �ж١ź仴���
           // theHosDB.theMapVisitDxDB.updateActive(accident.vn_id,"","0",accident.staff_cancel,accident.cancel_date_time,tempAccidentType.accident_type_trauma);  
            tempAccidentType = null;
            theUS.setStatus("���ź�������غѵ��˵��������", theUS.COMPLETE);
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
     * Use Case: ��㹡�ä����Ţ Admit �ҡ���¡��ԡ��� Admit �����§�ҡ������ҡ
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
            theUS.setStatus("��سҡ�͡���˵ء��¡��ԡ",UpdateStatus.WARNING);
            return false;
        }
        //�����䢢�����㹵��ҧ visit
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            // an �����Ţ��Ңͧ vn ���㹢�з�� vn ���Ţ AN
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
            
            //amp:15/08/2549: �����¡��ԡ��� admit ��� off ��������
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
            
            theHS.theVisitSubject.notifyReverseAdmit("¡��ԡʶҹм�������������", UpdateStatus.COMPLETE);
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
     *���ѹ�����������ѹ���� vector of patient �͡����ѹ�շ�����������ͧ令�������¤���
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
  * @detail ��˹��·ҧ���ᾷ��ͧ������ Ẻ���¤� �����������ö��˹��������ҧ�Ǵ����
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
                theUS.setStatus("��è�˹��¼��������ª��Ե�������ö��˹����˹�ҹ����",UpdateStatus.WARNING);
                return ;
            }
            
            if(visit_in.discharge_opd_status.equals(DischargeOpd.REFER))
            {
                theUS.setStatus("��è�˹��¼����·���ͧ�觵���������ö��˹����˹�ҹ����",UpdateStatus.WARNING);
                return ;
            }
            if(row.length==0){
                theUS.setStatus("��س����͡����ҡ��¡��㹤��",UpdateStatus.WARNING);
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
                theUS.setStatus("�������¡�÷���˹����ô��Ǩ�ͺ���ŧ�����ä",UpdateStatus.WARNING);
                return;
            }
            theHS.theVisitSubject.notifyDischargeDoctor("��è�˹��·ҧ���ᾷ���������",UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus("��è�˹��·ҧ���ᾷ��Դ��Ҵ",UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    } 
    
 /**
  * @detail ��˹��·ҧ���ᾷ��ͧ������ Ẻ���¤� �����������ö��˹��������ҧ�Ǵ�����繿ѧ�ѹ����
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
     *@see �֧����ѵ� NCD
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
                theUS.setStatus("����ջ���ѵ� NCD �ͧ�����¤����",UpdateStatus.WARNING);
                return null;
            }
     //       String[] columnName = new String[vVisit.size()+1];  
                   String[] columnName = new String[vVisit.size()];  
                //�ͺ�á
                rowDatas = new Object[21];
                columnName[0] = "�ѹ ��͹ ��";
                rowDatas[0] = "�дѺ����ҡ��";
                rowDatas[1] = "�����ѹ���Ե (��һ��� 120/80 ��.��ͷ)";
                rowDatas[2] = "�վ��(��һ��� 60-100 ����/�ҷ�)";
                rowDatas[3] = "�дѺ��ӵ������ʹ (��һ��� 80-100 ��%)";
                rowDatas[4] = "��������鹢ͧ������ʹᴧ (��һ��� 35-42%)";
                rowDatas[5] = "";
                rowDatas[6] = "����ԹԨ���";
                rowDatas[7] = "1.";
                rowDatas[8] = "2.";
                rowDatas[9] = "3.";
                rowDatas[10] = "4.";
                rowDatas[11] = "����ѡ�Ҵ�����";
                rowDatas[12] = "1.";
                rowDatas[13] = "2.";
                rowDatas[14] = "3.";
                rowDatas[15] = "4.";
                rowDatas[16] = "";
                rowDatas[17] = "��ô��Ŵ�ҹ��� �";
                rowDatas[18] = "�Ѵ���駵���";
                rowDatas[19] = "ʶҹ���Ѵ/����";
                rowDatas[20] = "���Ѵ";
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
     *@see : �ѹ�֡�����¹Ѵ
     *@param : boolean ����� �ͧ Checkbox
     *@return boolean ��úѹ�֡������
     */
    public boolean updateVisitAppointment(boolean appointment)
    {
        Constant.println(UseCase.UCID_updateVisitAppointment);
        String objectid =   null;
        if(theHO.theVisit != null)
            objectid = theHO.theVisit.getObjectId();
        if(theHO.theVisit == null) {
            theUS.setStatus("��س����͡�����·������㹡�кǹ���",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus("�����¨���кǹ��������������ö��䢢�������",UpdateStatus.WARNING);
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
            // ¡��ԡ��ùѴ���������ͧ�١�͡ �µ�ͧ��ʶҹС�ùѴ���͹Ѵ ���ʶҹС����ҹ�� 1(��ҹ)
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
     *@see : �ѹ�֡������ admit
     *@param : boolean ����� �ͧ Checkbox
     *@return boolean ��úѹ�֡������
     */
    public boolean updateVisitAdmit(boolean admit)
    {
        Constant.println(UseCase.UCID_admit);
        String objectid =   null;
        if(theHO.theVisit != null)
            objectid = theHO.theVisit.getObjectId();
        if(theHO.theVisit == null) {
            theUS.setStatus("��س����͡�����·������㹡�кǹ���",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus("�����¨���кǹ��������������ö��䢢�������",UpdateStatus.WARNING);
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
     *@see : �ѹ�֡������ refer
     *@param : boolean ����� �ͧ Checkbox
     *@return boolean ��úѹ�֡������
     */
    public boolean updateVisitRefer(boolean refer)
    {
        Constant.println(UseCase.UCID_updateVisitRefer);
        String objectid =   null;
        if(theHO.theVisit != null)
            objectid = theHO.theVisit.getObjectId();
        if(theHO.theVisit == null) {
            theUS.setStatus("��س����͡�����·������㹡�кǹ���",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus("�����¨���кǹ��������������ö��䢢�������",UpdateStatus.WARNING);
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
     *@see : �֧�����Ť�ǵ�� Visit_id
     *@param : String �ͧ Visit_id
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
     *@see : �ѹ�֡�����š�ùѴ�����¨ҡᶺ�ҡ���纻���
     *@param : Object Visit(�����š������Ѻ��ԡ��),Object Appointment(�����š�ùѴ),String �ѹ���ҷ��ѹ�֡��ùѴ
     *@return void
     */
    public void saveAppointment(Visit theVisit,Appointment theAppointment,String date_time)
    {
        if(theVisit==null)
        {
            theUS.setStatus("��س����͡�����¡�͹�ӡ�úѹ�֡", theUS.WARNING);
            return;
        }
        if(theAppointment.aptype.equals("")){
            theUS.setStatus("��سҡ�͡�����ŹѴ������",UpdateStatus.WARNING);
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
            theHS.thePatientSubject.notifySaveAppointment("��úѹ�֡�Ѵ�������������",UpdateStatus.COMPLETE);
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
   *  �����ҹ�����ż������繿ѧ�ѹ������ҹ��
 * @deprecated henbe unused
 *  ¡��ԡ�����ҹ�������������ª���ҡ�Թ价�������㨡�÷ӧҹ�ͧ�ѹ���ҡ
 */
    protected void intReadVisitPatient(Visit visit) throws Exception
    {
        theLookupControl.intReadDateTime();
        thePatientControl.intReadVisitSuit(visit);
        intLockVisit(theHO.date_time);
        intSaveTransferCatch(theHO.theEmployee, theHO.date_time);
     }
    
    /**
     *�����͡�������繾ѧ�ѹ����������ҡ��¹͡
     *������� lab ���� xray ���¶���繡�������͡�������������㹡�÷ӧҹ����͹���
     * @deprecated henbe unused use patientcontrol.intLockVisit()
     */
    private void intLockVisit(String date_time)throws Exception
    {
        thePatientControl.intLockVisit(date_time);
   }

   /**
    *@deprecated ���������㨡�÷ӧҹ�������ѹ�Դ��ѡ���
    */ 
   protected void intSaveQueueDispenseOPD(Vector order,String date_time,Visit visit)throws Exception
   {
        int disp_qty = 0;
        Vector vc = theHosDB.theOrderItemDB.selectOrderItemDrugByVisitId(visit.getObjectId());
        theOrderControl.intSaveQueueOfOrder(vc.size());
   }    
   
    /**
     *@deprecated ���������������
     */
    protected void intEditVisitInDespenseToZero(Visit visit)throws Exception
    {
            //��Ǩ�ͺ�����¡�� order ����¡������������������੾�з���ѧ�������� ����� ��� list �� vector
            Vector vc = theHosDB.theOrderItemDB.selectOrderItemDrugByVisitId(visit.getObjectId());
            //�������� null
            if((vc != null))
            {
                // ����դ���ҡ���� 0 ���Ѵ���udate ŧ� HosDB.theQueueDespenseDB
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
    *@deprecated henbe unused ���ǹ��᷹
    *theLookupControl.readQueueVisitById(queueVisitID);
    *
    */
    public QueueVisit readQueueVisitByQueueVisitID(String queueVisitID)
    {
        return theLookupControl.readQueueVisitById(queueVisitID);
    }

    /**
     *�������������������ѹ��ͧ����� 2 �ͺ�鹼������ա
     *��������
     * not @deprecated �ѧ��ͧ������
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
            theUS.setStatus("������¡�������������",UpdateStatus.COMPLETE);
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
            theUS.setStatus("�ѧ��������͡������",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.isLockingByOther()){
            theUS.setStatus("�����¶١��͡�¼���餹���",UpdateStatus.WARNING);
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
            theUS.setStatus("�ѧ��������͡������",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.isLockingByOther()){
            theUS.setStatus("�����¶١��͡�¼���餹���",UpdateStatus.WARNING);
            return false;
        }
        String objectid =   null;
        if(theHO.theVisit != null)
            objectid = theHO.theVisit.getObjectId();
        //amp:3/6/2549 ���㹡óդ�ҧ�ѹ�֡������ Drop Visit
        if(theHO.theVisit.visit_status.equals("2"))
        {
            theUS.setStatus("�����������ʶҹФ�ҧ�ѹ�֡����",UpdateStatus.WARNING);
            return false;
        }
        if(theHO.theVisit.visit_status.equals("3"))
        {
            theUS.setStatus("�����������ʶҹШ���кǹ�������",UpdateStatus.WARNING);
            return false;
        }
        if(!theHO.theVisit.visit_type.equals(VisitType.SURVEY))
        {
            theUS.setStatus(Constant.getTextBundle("�������ö¡��ԡ��")+" "+
                    Constant.getTextBundle("��سҵ�Ǩ�ͺ����繢��������Ǩ"),UpdateStatus.WARNING);
            return false;
        }
        String cause = DialogCause.showDialog(theUS.getJFrame(),Constant.getTextBundle("���¡��ԡ�������Ѻ��ԡ��"));
        if(cause.equals(""))
        {
            theUS.setStatus("��سҡ�͡���˵ء��¡��ԡ�������Ѻ��ԡ��",UpdateStatus.WARNING);
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
            //�ӡ��ź��¡�èҡ��Ǽ�����
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
                theUS.setStatus("��س����͡��Ъҡ�",UpdateStatus.WARNING);
                return false;
            }
            if(theHO.theVisit!=null && theHO.theVisit.visit_type.equals(VisitType.SURVEY)){
                theUS.setStatus("��Ъҡ�����㹡�кǹ������Ǩ����",UpdateStatus.WARNING);
                return false;
            }
            if(theHO.theVisit!=null && !theHO.theVisit.visit_type.equals(VisitType.SURVEY)){
                theUS.setStatus("��Ъҡ�����㹡�кǹ��âͧʶҹ��Һ�š�سҤ�ҧ�ѹ�֡���ǹ������������Ǩ�ա����",UpdateStatus.WARNING);
                return false;
            }
            String db_year = theHosDB.theVisitYearDB.selectCurrenctYear();
            if(!db_year.equals(theHO.date_time.substring(2,4)))  {
                boolean ret = theUS.confirmBox(Constant.getTextBundle("���Ţ�բͧ VN ����繻Ѩ�غѹ��õ�Ǩ�ͺ�Ţ�ӴѺ")+" "+
                    Constant.getTextBundle("�׹�ѹ�����ҹ���"),UpdateStatus.WARNING);
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
