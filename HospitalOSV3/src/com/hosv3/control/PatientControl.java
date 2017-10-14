
/*
 * PatientControl.java
 *
 * Created on 17 ���Ҥ� 2546, 15:33 �.
 */
package com.hosv3.control;
   
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.object.*;
import com.hospital_os.object.specialQuery.*;
//import com.hospital_os.objdb.*;
import com.hospital_os.usecase.connection.Persistent;

import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;
import com.hosv3.subject.*;
import com.hosv3.object.*;
import com.pcu.object.*;
import java.sql.ResultSet;
//import com.pcu.objdb.objdbclass.*;4

import java.util.*;
import java.text.*;
import javax.swing.*;
import com.hospital_os.object.AppointmentStatus;
/**
 * patient control function
 * @author henbe
 */
public class PatientControl {
    /**
     * what it used for ?
     * @deprecated henbe unused
     */
    String theStatus;
    /**
     * Connection Interface
     */
    ConnectionInf theConnectionInf;
    /**
     * HosDB
     */
    HosDB theHosDB;
    /**
     * HosObject
     */
    HosObject theHO;
    /**
     * Lookup Object
     */
    LookupObject theLO;
    /**
     * HosSubject
     */
    HosSubject theHS;
    /**
     * UpdateStatus
     */
    UpdateStatus theUS;
    /**
     * VisitControl dependency control
     */
    VisitControl theVisitControl;
    /**
     * dependency control
     */
    LookupControl theLookupControl;
    SystemControl theSystemControl;
    /**
     * contrucstors
     * @param con ConnectionInf
     * @param ho HosObject
     * @param hdb HosDB
     * @param hs HosSubject
     * @param lo LookupObject
     */
    public PatientControl(ConnectionInf con
    ,HosObject ho,HosDB hdb,HosSubject hs,LookupObject lo){
        theConnectionInf = con;
        theHosDB = hdb;
        theHS = hs;
        theHO = ho;
        theLO = lo;
    }
    
    /**
     * display status of result
     * @param us UpdateStatus
     */
    
    public void setUpdateStatus(UpdateStatus us){
        theUS = us;
    }
    public void setSystemControl(SystemControl systemControl)
    {
        theSystemControl = systemControl;
    }
    /**
     * set Dependency control of this PatientControl
     * @param lc LookupControl
     * @param vc VisitControl
     */
    public void setDepControl(LookupControl lc,VisitControl vc){
        theVisitControl = vc;
        theLookupControl = lc;
    }
    
    //�ҡ���� + �˹�Ҩ͢����ż�����
    /**
     * clear patient in system
     */
   public void resetPatient()
    {
        Constant.println("public void resetPatient(Visit visit)");
        Constant.println(UseCase.UCID_resetPatient);
        String objectid =   null;
        if(theHO.theVisit == null && theHO.thePatient!=null){
            theHO.clearFamily();
            theHS.thePatientSubject.notifyResetPatient(Constant.getTextBundle("�������������ż������������"),UpdateStatus.COMPLETE);
            return;
        }
        // ��� ������� Administrator ���� one ����ö�Ŵ��͡�� 
        try{
            theConnectionInf.open();
            theVisitControl.intUnlockVisit(theHO.theVisit);
            theHO.clearFamily();
            theHS.thePatientSubject.notifyResetPatient(Constant.getTextBundle("�������������ż������������"),UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_resetPatient,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_resetPatient,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_resetPatient,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_resetPatient,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }    
    /**
     * 
     * ��Ǩ�ͺ����ա�����������������
     * �������ա�����ҡ�������������
     * ����ա�������������١�͹����ա�ë�ӡѹ�������*
     * @date 12/09/2549
     * @param itemdrug drug data
     */   
    public void addItemDrugAllergy(Vector itemdrug) 
    {       
        Constant.println("public void addItemDrugAllergy(Vector itemdrug) {");        
        if(itemdrug == null)
        {
            theUS.setStatus(("����բ������ҷ����"),UpdateStatus.WARNING);
            return;
        }      
        Item item = new Item();        
        int itemsize = itemdrug.size();
        int i = 0;
        int j = 0;            
        if(theHO.vDrugAllergy != null)
        {
            int vDrugAllergySize = theHO.vDrugAllergy.size();
            for (i=0; i < itemdrug.size(); i++)
            {
                item = (Item)itemdrug.get(i);
                for(j=0;j<vDrugAllergySize;j++)
                {
                     DrugAllergy da;
                     da = (DrugAllergy) theHO.vDrugAllergy.get(j);
                     if(da.item_code.equalsIgnoreCase(item.getObjectId()))
                     {
                         itemdrug.remove(i);
                         i--;
                     }
                }
            }
            itemsize = itemdrug.size();
            for(i=0; i < itemsize ;i++) 
            {
                DrugAllergy da = new DrugAllergy();
                item = (Item)itemdrug.get(i);
                da.item_code = item.getObjectId();
                da.common_name = item.common_name;
                da.patient_id = theHO.thePatient.getObjectId();
                if(theHO.vDrugAllergy==null)
                    theHO.vDrugAllergy=new Vector();
                theHO.vDrugAllergy.add(da);
            }
        }
        else
        { 
            for(i=0; i<itemsize ;i++) 
            {
                DrugAllergy da = new DrugAllergy();
                item = (Item)itemdrug.get(i);
                da.item_code = item.getObjectId();
                da.common_name = item.common_name;
                da.patient_id = theHO.thePatient.getObjectId();
                if(theHO.vDrugAllergy==null)
                    theHO.vDrugAllergy=new Vector();
                theHO.vDrugAllergy.add(da);
            }
        }
        theHS.thePatientSubject.notifyManageDrugAllergy(Constant.getTextBundle("���������š������㹵��ҧ") + " "+
                Constant.getTextBundle("�������"),1);
    }
    public Patient readPatientByFamilyID(String fid)
    {
        Patient p = null;
        try
        {
            p = this.theHosDB.thePatientDB.selectByFamilyID(fid);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return p;
    }
   /**
     * Creates a new instance of createPatientAllergy
     * @param pid person identification number
     */
    //readPatientByPatientID
    //henbe_check
    
    public void readPatientByPatientID(String pid)
    {
        Constant.println("public void readPatientByPatientID(String pid)");
        if(pid.trim().equals("")){
            theUS.setStatus(("�����Ţ�����µ�ͧ����繤����ҧ"),UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            ///unlock old visit//////////////////////////////////
            theVisitControl.intUnlockVisit(theHO.theVisit);
            theHO.clearFamily();
            //////////////////////////////////////////////////////
            Patient pt = theHosDB.thePatientDB.selectByPK(pid);
            if(pt==null){
                theUS.setStatus(("��辺�����ż�����"),UpdateStatus.WARNING);
                return;
            }
            intReadFamilySuit(pt.getFamily(),pt);
            intReadPatientSuit(pt);
            //��Ǩ�ͺ����� visit �����������Ңͧ���餹���///////////////////////////////
            Visit visit = intReadVisitRet(pt.getObjectId());
            if(visit==null){
                theHS.thePatientSubject.notifyReadPatient(Constant.getTextBundle(
                        "������¡�٢����ż������������"),UpdateStatus.COMPLETE);
                return;
            }
            //////////////////////////////////////////////////////////////////
            theLookupControl.intReadDateTime();
            intReadVisitSuit(visit);
            intLockVisit(theHO.date_time);
            theVisitControl.intSaveTransferCatch(theHO.theEmployee, theHO.date_time);

            theHS.theVisitSubject.notifyReadVisit(Constant.getTextBundle("���¡�٢����š���Ѻ��ԡ�âͧ�������������")
                    ,UpdateStatus.COMPLETE);
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(("��ô֧�����ż����¼Դ��Ҵ"),UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    
    public void readVisitByFamily(String fid)
    {
        Constant.println("public void readPatientByPatientID(String pid)");
        if(fid==null){
            theUS.setStatus(("�����Ţ�����µ�ͧ����繤����ҧ"),UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            ///unlock old visit//////////////////////////////////
            theVisitControl.intUnlockVisit(theHO.theVisit);
            theHO.clearFamily();
            //////////////////////////////////////////////////////
            Family family = theHosDB.theFamilyDB.selectByPK(fid);
            if(family==null){
                theUS.setStatus(("��辺�����Ż�Ъҡ�"),UpdateStatus.WARNING);
                return;
            }
            theHO.theFamily = family;
            intReadFamilySuit(family,null);
            Patient pt = theHosDB.thePatientDB.selectByFid(fid);
            if(pt==null){
                theHS.thePatientSubject.notifyReadFamily(Constant.getTextBundle(
                        "������¡�٢����Ż�Ъҡ��������"),UpdateStatus.COMPLETE);
                return;
            }
            intReadPatientSuit(pt);
            //��Ǩ�ͺ����� visit �����������Ңͧ���餹���///////////////////////////////
            Visit visit = intReadVisitRet(pt.getObjectId());
            if(visit==null){
                theHS.thePatientSubject.notifyReadPatient(Constant.getTextBundle(
                        "������¡�٢����ż������������"),UpdateStatus.COMPLETE);
                return;
            }
            //////////////////////////////////////////////////////////////////
            theLookupControl.intReadDateTime();
            intReadVisitSuit(visit);
            intLockVisit(theHO.date_time);
            theVisitControl.intSaveTransferCatch(theHO.theEmployee, theHO.date_time);

            theHS.theVisitSubject.notifyReadVisit(Constant.getTextBundle("���¡�٢����š���Ѻ��ԡ�âͧ�������������")
                    ,UpdateStatus.COMPLETE);
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(("��ô֧�����ż����¼Դ��Ҵ"),UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    
    protected boolean intLockVisit(String date_time) throws Exception
    {
        if(theHO.theVisit == null){
            Constant.println("lockVisitInt() Visit is null"); 
            return false;
        }
        if(theHO.theVisit.locking.equals(Active.isEnable())){
            Constant.println("lockVisitInt() Visit is locked"); 
            return false;
        }
        if(theHO.theEmployee.authentication_id.equals(Authentication.LAB)
        || theHO.theEmployee.authentication_id.equals(Authentication.XRAY)){
            return false;
        }
        theHO.theVisit.locking = "1";
        theHO.theVisit.lock_user = theHO.theEmployee.getObjectId();
        theHO.theVisit.lock_time = date_time;
        theHosDB.theVisitDB.updateLocking(theHO.theVisit);
        if(theHO.theListTransfer==null){
            Constant.println("lockVisitPaitentInt QueueTransfer is null");
            return true;
        }
        theHO.theListTransfer.locking = "1";
        theHosDB.theQueueTransferDB.updateLock(theHO.theListTransfer);        
        return true;
    }
  /*��Ǩ�ͺ�����Ţ�ѵû�ЪҪ��ͧ��������ҫ�ӡѺ���������㹰ҹ�������������*/
    
    /**
     * check personal id from database before save new
     * @param pid person identification number
     * @return result is boolean
     */
    public boolean checkPatientPid(String pid)
    {
        theConnectionInf.open();
        boolean result=true;
        try{
            if(pid.equals(""))
                return false;
            Vector answer = theHosDB.thePatientDB.queryPid(pid);
            if(answer == null || answer.size()==0)
                result = false;
            else
                result = true;
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////
    
    /**
     * 
     * @param pid 
     * @throws java.lang.Exception 
     * @return
     *
     */
    protected boolean intCheckPatientPid(String pid)throws Exception 
    {
        boolean result=true;
            Vector answer = theHosDB.thePatientDB.queryPid(pid);
            if(answer == null || answer.size()==0)
                result = false;
            else
                result = true;
        return result;
    }  
////////////////////////////////////////////////////////////////////////////
    /**
     * 
     * @param id 
     * @return 
     */
    private static boolean checkID(String id)
    {
        if(id.length()!=13)
            return false;
        return true;
    }
  ////////////////////////////////////////////////////////////////////////////
    /**
     * 
     * @param all_period 
     * @param dateFrom 
     * @param dateTo 
     * @param sp 
     * @param pid 
     * @return 
     */
    public Vector listAppointmentByDateSP(boolean all_period,String dateFrom, String dateTo, String sp,String pid)
    {
        return listAppointmentByDateSP(all_period,dateFrom,dateTo,sp,pid, null,null,theUS);
    }
    ////////////////////////////////////////////////////////////////////////////
    /**
     * 
     * @param all_period 
     * @param dateFrom 
     * @param dateTo 
     * @param sp 
     * @param pid 
     * @param sta 
     * @param active 
     * @param theUS 
     * @return 
     */
    public Vector listAppointmentByDateSP(boolean all_period,String dateFrom, String dateTo
            , String sp,String pid,String sta,String active,UpdateStatus theUS)
    {
        Vector result = null;
        if(all_period && pid==null)
        {
            boolean ret = theUS.confirmBox(Constant.getTextBundle("��ä���¡�ùѴ�������Ҩ�����ҹҹ �׹�ѹ��ä鹹Ѵ"),UpdateStatus.WARNING);
            if(!ret)
                return null;
        }
        theConnectionInf.open();
        try{
            result = theHosDB.theSpecialQueryAppointmentDB.queryDataOrderbyDateHN(all_period,dateFrom,dateTo,sp,pid,sta,active);
        }
        catch(Exception ex){  
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
        return result;
    }
    public String getFinalAppointDate(UpdateStatus theUS)
    {
        String res = "";
        Vector result = null;
        theConnectionInf.open();
        try{
            result = theHosDB.theAppointmentDB.selectFinalDate(this.theHO.thePatient.getObjectId());
            if(result==null)
                return "";
            if(result.isEmpty())
                return "";
            Appointment app = (Appointment) result.get(0);
            theHO.theFinalAppointMent = app;
            return app.appoint_date;
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
        return res;
    }
    public String getFinalAppointDate2(UpdateStatus theUS)
    {
        String res = "";
        Vector result = null;
        theConnectionInf.open();
        try{
            result = theHosDB.theAppointmentDB.selectFinalDate2(this.theHO.thePatient.getObjectId());
            if(result==null)
                return "";
            if(result.isEmpty())
                return "";
            Appointment app = (Appointment) result.get(0);
            theHO.theFinalAppointMent = app;
            return app.appoint_date;
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
        return res;
    }
    /**
     * 
     * 
     * @return : Vector �ͧ���Ң����š���������� Xray
     * @see : ���Ң����š���������� Xray
     * @Author : sumo
     * @date : 20/02/2549
     * @param all_period 
     * @param dateFrom 
     * @param dateTo 
     * @param hn 
     * @param xn 
     * @param active 
     */
    public Vector listBorrowFilmXrayByDate(boolean all_period,String dateFrom, String dateTo,String hn,String xn,String active)
    {
        Vector result = null;
        theConnectionInf.open();
        try{
            result = theHosDB.theSpecialQueryBorrowFilmXrayDB.queryDataOrderbyDate(all_period,dateFrom,dateTo,hn, xn,active);
        }
        catch(Exception ex){  
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
        return result;
    }
      
    /**
     * ���Ҽ����¨ҡ hn
     * @param hn 
     * @return 
     */
    
    public Vector listPatientByHn(String hn)
    {
        hn = hn.trim();
        if(hn.equals("")){
            theUS.setStatus(("��سҡ�͡�����Ţ HN"),UpdateStatus.WARNING);
            return null;
        }
        Vector p=null;
        theConnectionInf.open();
        try{
            //int value = Integer.parseInt(hn);
            //DecimalFormat d = new DecimalFormat();
            //p= theHosDB.thePatientDB.queryByHn(d.format(value));
            int value = Integer.parseInt(hn);
            DecimalFormat d=new DecimalFormat();
            d.applyPattern("000000");
            hn = d.format(value);
            return intListPatient("","","",hn,"");
            //p= theHosDB.thePatientDB.queryByHn(hn);
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
            return new Vector();
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /*
     * @author pongtorn Henbe
     * ���Ҽ����¨ҡ���ͷ����鹧��·���ش�¨�令鹨ҡ��Ъҡ���������
     * �ҡ��һ�Ъҡù���繼������������Ǩ��������ʴ�������Ҥ�����������
     *
     * �Ըա�÷��ͺ���
     * ���ҧ��Ъҡ� ���� ���ǡѹ
     * ���ҧ�����ª��� ���ǡѹ
     * �鹼����´��¤���� ���ǡѹ
     * �е�ͧ�ͼ����·����� 2 ����ǹ��������������͡���Ǩ������ҧ�á��
     * ���Ţ����������繤����ҧ��������ҡ��
     * �ҡ�繡�зӡ�� �ʴ������Ţͧ���������͹�������繡�á�͡������ �������ӡ�á������ѹ�֡
     * �ҡ����繡�зӧҹ�������
     * Creates a new instance of createPatientAllergy
     */
    /**
     * 
     * @param pname 
     * @param fname 
     * @param lname 
     * @return 
     */
    public Vector listPatientByName(String pname, String fname, String lname)
    {
        fname = fname.trim();
        pname = pname.trim();
        lname = lname.trim();
        if(fname.equals("") && lname.equals(""))
        {
            theUS.setStatus(("��سҡ�͡���� ���͹��ʡ��"),UpdateStatus.WARNING);
            return null;
        }        
        theConnectionInf.open();
        try{
            return intListPatient("",fname,lname,"","");
//            Vector patient_v = theHosDB.thePatientDB.queryByFLName(fname + "%","%" + lname + "%");
//            Vector person_v = theHosDB.theFamilyDB.queryByFLName(fname + "%","%" + lname + "%");
//            return addPersonNotInPatient(person_v,patient_v);
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
            return new Vector();
        }
        finally
        {
            theConnectionInf.close();
        }
    }
/**
 *��һ�Ъҡ��繵�ǵ�� �������բ����ż����¡��ʴ������ż������͡�����
 * �����㹤�������ҹ�����ú�ҧ
 * ���������    ��Ъҡ�   ������
 *                  �����  �����     �����
 *                  ��    �����     ��         ��
 *                  ��    ��       ��      ��
 *
 *                  �����  ��       ��     ��ͧ����� case ���
 *
 **/
    protected Vector addPersonNotInPatient(Vector person_v,Vector patient_v)
    {
        Vector patient_ret = new Vector();
        for(int i=0,size=person_v.size();i<size;i++)
        {
            Family person = (Family)person_v.get(i);
            boolean match = false;
            Patient pt = null;
            for(int j=0;j<patient_v.size();j++)
            {
                pt = (Patient)patient_v.get(j);
                if(person.getObjectId().equals(pt.family_id))
                {
                    match=true;
                    break;
                }
            }
            if(!match)
                patient_ret.add(theHO.initPatient(person,null));
            else
                patient_ret.add(pt);
        }
 //           �����  ��       ��ͧ��     ��������� �����������
//        if(person_v.isEmpty()) { 
//            for(int j=0;j<patient_v.size();j++)  {
//                Patient pt = (Patient)patient_v.get(j);
//                patient_ret.add(pt);
//            }
//        }
        return patient_ret;
    }
    /**
     *  function
     * ���Ҽ����´��� patient_id
     * �ѭ�Ңͧ�ѧ�ѹ�������� �͹��餹�� ��л�Ъҡ� �Һ����ǡѹ
     * ������ԧ���� ��Ъҡèӵ�ͧ��ͺ���� ��������������
     * ��㹰ҹ��������������ҧ���������Ҽ������Դ�ҡ�͹���ǻ�Ъҡ��Դ����ѧ���������ŵ�����ѹ
     * �֧�Դ��äҺ����Ǣͧ�����Ŵѧ��� ��ä鹨е�ͧ����� union �ѹ���ǹ����ӡѹ��е�ͧ�����˹��
     * @param pid 
     * @return 
     */
    public Vector listPatientByPID(String pid){
        pid = pid.trim();
        if(pid.length() != 13){
            theUS.setStatus(("�Ţ�ѵû�ЪҪ�����ͧ��ä������ú 13 ��ѡ"),UpdateStatus.WARNING);
            return null;
        }
        theConnectionInf.open();
        try{
            return intListPatient(pid,"","","","");
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally {
            theConnectionInf.close();
        }
    }
    /**
     * @deprecated no
     * @param pid
     * @param fname
     * @param lname
     * @return
     * @throws Exception
     */
    public Vector intListPatient(String pid,String fname,String lname)throws Exception
    {
        return intListPatient(pid,fname,lname,"","");
    }
    public Vector intListPatient(String pid,String fname,String lname,String hn,String hcis)throws Exception
    {
            String sql=" select t_patient.t_patient_id" +
                    ",t_health_family.t_health_family_id" +
                    ",t_patient.patient_hn" +
                    ",t_health_family.patient_name" +
                    ",t_health_family.patient_last_name" +
                    ",t_health_family.patient_pid" +
                    ",t_health_family.patient_mother_firstname" +
                    ",t_health_family.patient_birthday" +
                    ",t_patient.patient_xn" +
                " from t_health_family " +
                " left join t_patient on (t_patient.t_health_family_id = t_health_family.t_health_family_id" +
                    " and t_patient.patient_active = '1') where health_family_active ='1'";
            if(!pid.equals(""))
                sql += " and t_health_family.patient_pid ='"+pid+"' ";
            if(!fname.equals(""))
                sql += " and t_health_family.patient_name ilike '" + fname + "%' ";
            if(!lname.equals(""))            
                sql += " and t_health_family.patient_last_name ilike '" + lname + "%' ";
            if(!hn.equals(""))            
                sql += " and t_patient.patient_hn like '%" + hn + "' ";
            if(!hcis.equals(""))            
                sql += " and t_health_family.health_family_hn_hcis like '%" + hcis + "' ";
                        
            ResultSet rs = theConnectionInf.eQuery(sql);
            Vector vlist = new Vector();
            while(rs.next()){
                Patient pt = new Patient();
                pt.setObjectId(rs.getString("t_patient_id"));
                pt.family_id = rs.getString("t_health_family_id");
                pt.hn = rs.getString("patient_hn");
                if(pt.hn==null)
                    pt.hn = "";
                pt.patient_name = rs.getString("patient_name");
                pt.patient_last_name = rs.getString("patient_last_name");
                pt.pid = rs.getString("patient_pid");
                pt.mother_firstname = rs.getString("patient_mother_firstname");
                pt.patient_birthday = rs.getString("patient_birthday");
                pt.xn = rs.getString("patient_xn");
                pt.updateF2P();
                vlist.add(pt);
            }
            return vlist;
    }
    /**
     * ���Ҽ����´��� xn
     * @param xn 
     * @return 
     */
    public Vector listPatientByXn(String xn){
        xn = xn.trim();
        if(xn.length() <= 0 && xn.equals(""))
        {
            theUS.setStatus(("��سҡ�͡�����Ţ XN"),UpdateStatus.WARNING);
            return null;
        }        
        Vector vc = null;
        theConnectionInf.open();
        try
        {
           vc = theHosDB.thePatientDB.selectLikeXn("%" + xn +"%");
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }     
        return vc;
    }
    /**
     * ���Է����Шӵ�Ǽ�����
     * @return 
     * @not deprecated use gps instead
     */
    public Vector listPatientPayment()
    {
        theConnectionInf.open();
        try {
            theHO.vPatientPayment = theHosDB.thePatientPaymentDB.selectByFamilyPatient(theHO.theFamily,theHO.thePatient);
//            if(theHO.theFamily!=null)
//                theHO.vPatientPayment = theHosDB.thePatientPaymentDB.selectByFamilyId(theHO.theFamily.getObjectId());
//            else if(theHO.thePatient!=null)
//                theHO.vPatientPayment = theHosDB.thePatientPaymentDB.selectByPatientId(theHO.thePatient.getObjectId());
            return theHO.vPatientPayment;
        }
        catch(Exception ex){    
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }

//listPatientlistPatientlistPatientlistPatientlistPatientlistPatientlistPatie    
    /**
     * ���Է����Шӵ�Ǽ�����
     * @param hn 
     * @return 
     */
    public Vector listPatientPaymentByPatientId(String hn)
    {
        if(hn==null)
             return null;
        hn = hn.trim();
        if(hn.equals(""))
            return null;
        theConnectionInf.open();
        Vector v=null;
        try
        {
            v = theHosDB.thePatientPaymentDB.selectByPatientId(hn);
        }
        catch(Exception ex){    
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return v;
    }
    
    /**
     * ���Է����Шӵ�Ǽ����¨ҡ family_id
     * Jao
     * @param family_id 
     * @return 
     */
    public Vector listPatientPaymentByFamilyId(String family_id)
    {        
        family_id = family_id.trim();
        if(family_id==null || family_id.equals(""))
            return null;
        
        theConnectionInf.open();
        Vector v=null;
        try
        {
            v = theHosDB.thePatientPaymentDB.selectByFamilyId(family_id);
        }
        catch(Exception ex){    
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return v;
    }

    /**
     * 
     * @param hn 
     * @return
     *@deprecated henbe unused
     */
    public Patient readPatientByPatientIdRet(String hn)
    {
        return readPatientByPatientIdRet(hn,null);
    }
    /*
     ������͡���������͡�зӡ�úҧ���ҧ��������㹡�����͡�����µ������
     *�����ѹ����ա�� notify read patient
     */
    /**
     * 
     * @param hn 
     * @param pid 
     * @return 
     * �֧�����Ť��� ��� �鹢������Է�Է�褹�����Ҥ��駷���ҹ��
     *@deprecated henbe unused 
     */
    public Patient readPatientByPatientIdRet(String hn,String pid)
    {
        theConnectionInf.open();
        try
        { 
            Patient p = theHosDB.thePatientDB.selectByPK(hn);
            if(pid != null && !pid.equals(""))
            {
                Visit theVisit;
                theVisit = theHosDB.theVisitDB.selectVisitByPatientIDLast(pid);
                if(theVisit != null)
                {
                    theHO.vOldVisitPayment = theHosDB.thePaymentDB.selectByVisitId(theVisit.getObjectId());
                }
            }
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
    public Patient readPatientByHNRet(String hn)
    {
        theConnectionInf.open();
        try{ 
            return theHosDB.thePatientDB.selectByHnEqual(hn);
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
    
    /*�ѹ�֡��ùѴ���¼�����
             //�ѧ����������觵��仹��
        //�������չѴ��ѹ������������ѧ
        //�ѹ�Ѵ���ѹ��ش�������
        //�����š�ùѴ������������������ 
        //������ö�ԴʶҹС�ùѴ���ҧ
     */
   
    /**
     * 
     * @param vapp 
     * @param vAppointmentOrder 
     * @param theUS 
     */
    public void saveAppointment(Vector vapp,Vector vAppointmentOrder,UpdateStatus theUS)
    {
        if(vapp == null)
        {
             theUS.setStatus(("��سҡ������ǡ�ա�������͹Ѵ�����ǧ�ѹ���"),UpdateStatus.WARNING);
             return;
        }
        //amp:14/8/2549:�����������ͧ�ѧ �Ҩ�ա�ùѴ�Դ��͡ѹ����͹
        /*if(vapp.size() > 10){
             theUS.setStatus(Constant.getTextBundle("��ǧ�ѹ���Ѵ�ҹ�Թ� (�ҡ���� 10 �ѹ) ������������ö �ѹ�֡��ùѴ�����"),UpdateStatus.WARNING);
             return;
        }*/
        if(vapp.isEmpty()){
             theUS.setStatus(("����բ����ŷ��кѹ�֡����Ѻ��ùѴ�繪�ǧ"),UpdateStatus.WARNING);
             return;
        }
        theConnectionInf.open();
        try{
            for(int i=0,size=vapp.size();i<size;i++)
            {
                Appointment app = (Appointment)vapp.get(i);
                intSaveAppointment(app,vAppointmentOrder,theUS);
            }
        }
        catch(Exception ex){    
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("��úѹ�֡�Ѵ������") + " "+
                    Constant.getTextBundle("�Դ��Ҵ"),UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    /**
     * 
     * @param appointment 
     * @param vAppointmentOrder 
     * @param theUS 
     */
    public void saveAppointment(Appointment appointment,Vector vAppointmentOrder,UpdateStatus theUS)
    {
        Constant.println("public void saveAppointment(Appointment appointment,UpdateStatus us)");
        Constant.println(UseCase.UCID_saveAppointment);
        String objectid =   null;
        theConnectionInf.open();
        try{
            
            boolean result = intSaveAppointment(appointment,vAppointmentOrder,theUS);
            if(!result)
                return;
            theHS.thePatientSubject.notifySaveAppointment(Constant.getTextBundle("��úѹ�֡�Ѵ������") + " "+
                    Constant.getTextBundle("�������"),UpdateStatus.COMPLETE);
            if(appointment != null)
                objectid = appointment.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveAppointment,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveAppointment,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){    
            theSystemControl.setStatus(UseCase.TH_saveAppointment,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveAppointment,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     * 
     * @param appointment 
     * @param vAppointmentOrder 
     * @param theUS 
     * @throws java.lang.Exception 
     * @return 
     */
    protected boolean intSaveAppointment(Appointment appointment
            ,Vector vAppointmentOrder,UpdateStatus theUS)throws Exception
    {
        Appointment app = theHosDB.theAppointmentDB.selectByPK(appointment.getObjectId());
        if(app!=null && app.isStatusClosed()){
            theUS.setStatus(("��ùѴ�Դʶҹ������������ö�����"),UpdateStatus.WARNING);
            return false;
        }
        if(theHO.thePatient!=null){
            if(theHO.thePatient.discharge_status_id.equals(Dischar.DEATH))  {
                theUS.setStatus(("���������ª��Ե�����������ö�ӡ�ùѴ��"),UpdateStatus.WARNING);
                return false;
            }
            if(theHO.thePatient.active.equals(Active.isDisable()))  {
                theUS.setStatus(("�����ż����¶١¡��ԡ�����������ö�ӡ�ùѴ��"),UpdateStatus.WARNING);
                return false;
            }
        }
        if(appointment.aptype.equals("")){
             theUS.setStatus(("��سҡ�͡ ��Ǣ�͡�ùѴ(�Ѵ������)"),UpdateStatus.WARNING);
             return false;
        }
        if(appointment.appoint_date.equals("")) {
             theUS.setStatus(("��سҡ�͡�ѹ���Ѵ"),UpdateStatus.WARNING);
             return false;
        }
        if(appointment.appoint_active.equals(Active.isDisable())){

             theUS.setStatus(("�������ö�����¡�ùѴ���¡��ԡ�������"),UpdateStatus.WARNING);
             return false;
        }
        try{
            int cnt = theHosDB.theAppointmentDB.countByDateDoctor(appointment.appoint_date,appointment.doctor_code);
            if(cnt>100){
                if(!theUS.confirmBox(Constant.getTextBundle("���ա�ùѴᾷ�줹�����ѹ�ѧ������Թ 100 �������׹�ѹ��ùѴ"),UpdateStatus.WARNING))
                    return false;
            }
        }
        catch(Exception e){
            Constant.println(e.getMessage());
        }
//            if(vAppointmentOrder!=null)
        Constant.println("__________vAppointment.size():" + (vAppointmentOrder==null));
        if(appointment.status.equals(AppointmentStatus.COMPLETE)
        && appointment.vn.equals(""))
        {
            String cur_vn = "";
            if(theHO.theVisit!=null) cur_vn = theHO.theVisit.vn;
            cur_vn = JOptionPane.showInputDialog(theUS.getJFrame()
                ,Constant.getTextBundle("��سҺѹ�֡�����Ţ VN �ͧ�����¡óշ��������ҵ���Ѵ"),cur_vn);
            if(cur_vn==null) {
                theUS.setStatus(("¡��ԡ�ѹ�֡��ùѴ"),UpdateStatus.WARNING);
                return false;
            }
            if(cur_vn.trim().equals("")) {
                theUS.setStatus(Constant.getTextBundle("����к������Ţ VN") + " " +
                        Constant.getTextBundle("¡��ԡ�ѹ�֡��ùѴ"),UpdateStatus.WARNING);
                return false;
            }
            appointment.vn = cur_vn;
            Visit v = theHosDB.theVisitDB.selectByVn(appointment.vn);
            if(v!=null)
                 appointment.visit_id = v.getObjectId();
            else
            {
                 appointment.vn = "";
                 theUS.setStatus(Constant.getTextBundle("�����Ţ VN") +
                         " "+ appointment.vn +" " +
                         Constant.getTextBundle("��辺㹰ҹ������"),UpdateStatus.WARNING);
                 return false;
            }
        }
        if(appointment.getObjectId()==null)
        {
            appointment.appointmenter = theHO.theEmployee.getObjectId();
            theHosDB.theAppointmentDB.insert(appointment);
            //amp:24/02/2549
            if(vAppointmentOrder!=null)
            {
                for(int i=0,size=vAppointmentOrder.size(); i<size; i++)
                {
                    AppointmentOrder apor = (AppointmentOrder)vAppointmentOrder.get(i);
                    apor.appointment_id = appointment.getObjectId();
                    theHosDB.theAppointmentOrderDB.insert(apor);
                }
            }
        }
        else
        {
            theHosDB.theAppointmentOrderDB.deleteByAppid(appointment.getObjectId());
            appointment.appoint_staff_update = theHO.theEmployee.getObjectId();
            appointment.appoint_update_date_time = theLookupControl.intReadDateTime();
            theHosDB.theAppointmentDB.update(appointment);
            //amp:24/02/2549
            if(vAppointmentOrder!=null)
            {
                for(int i=0,size=vAppointmentOrder.size(); i<size; i++)
                {
                    AppointmentOrder apor = (AppointmentOrder)vAppointmentOrder.get(i);
                    apor.appointment_id = appointment.getObjectId();
                    theHosDB.theAppointmentOrderDB.insert(apor);
                }
            }
        }
        return true;
    }  
    
    /**
     * 
     * 
     * @Author: amp
     * @date: 7/8/2549
     * @param: �ѹ���Ѵ
     * @return: �ӹǹ��ùѴ�ͧ�ѹ���
     * @see: �ӹǳ�ӹǹ�ͧ��ùѴ������ѹ
     * @param date_appointment 
     * @return 
     */
    public String countAppointment(String date_appointment)
    {
        String count_appintment = "";
        theConnectionInf.open();
        try 
        {
            count_appintment = theHosDB.theAppointmentDB.countAppointmentByDate(date_appointment);            
        }
        catch(Exception ex)
        {    
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(("��äӹǳ�ӹǹ�Ѵ�Դ��Ҵ"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return count_appintment;
    } 
    
    /**
     * @Author: amp
     * @date: 7/8/2549
     * @param: �ѹ���Ѵ,Key_id �ͧ employee �ͧᾷ�������͡
     * @return: �ӹǹ��ùѴ�ͧ�ѹ���ᾷ�����к�
     * @see: �ӹǳ�ӹǹ�ͧ��ùѴ������ѹ�ͧ����ᾷ��
     * @param date_appointment 
     * @param doctor_id 
     * @return 
     */
    public String countAppointmentSP(String date_appointment,String spid)
    {
        String count_appintment = "";
        theConnectionInf.open();
        try 
        {
            return theHosDB.theAppointmentDB.countAppointmentByDateSpid(date_appointment, spid);  
        }
        catch(Exception ex)
        {    
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(("��äӹǳ�ӹǹ�Ѵ�Դ��Ҵ"),UpdateStatus.ERROR);
            return "";
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public String countDoctor(String date_appointment,String doctor_id)
    {
        String count_appintment = "";
        theConnectionInf.open();
        try 
        {
            count_appintment = theHosDB.theAppointmentDB.countAppointmentByDateAndDoctor(date_appointment, doctor_id);  
        }
        catch(Exception ex)
        {    
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(("��äӹǳ�ӹǹ�Ѵ�Դ��Ҵ"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return count_appintment;
    }
    
    /**
     * 
     * @param appointment 
     * @param theUS 
     */
    public void closeAppointment(Appointment appointment,UpdateStatus theUS)
    {
        Constant.println("public void closeAppointment(Appointment appointment,UpdateStatus us)");
        theConnectionInf.open();
        try {
            //Vector appPt_date = theHosDB.theAppointmentDB.selectByDatePid(
              //  appointment.appoint_date,appointment.patient_id);
            if(appointment.status.equals(AppointmentStatus.COMPLETE)
            && appointment.vn.equals("")){
                String cur_vn = "";
                if(theHO.theVisit!=null) cur_vn = theHO.theVisit.vn;
                appointment.vn = JOptionPane.showInputDialog(theUS.getJFrame()
                    ,Constant.getTextBundle("��سҺѹ�֡�����Ţ VN �ͧ�����¡óշ��������ҵ���Ѵ"),cur_vn);
                Visit v = theHosDB.theVisitDB.selectByVn(appointment.vn);
                if(v!=null)
                     appointment.visit_id = v.getObjectId();
                else{
                     appointment.vn = "";
                     theUS.setStatus(Constant.getTextBundle("�����Ţ VN") +
                             " "+ appointment.vn +" " +
                             Constant.getTextBundle("��辺㹰ҹ������"),UpdateStatus.WARNING);
                     return;
                }
            }
            theHosDB.theAppointmentDB.update(appointment);
            theHS.thePatientSubject.notifySaveAppointment(Constant.getTextBundle("��ûԴʶҹС�ùѴ�������")
                ,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){    
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(("��ûԴʶҹС�ùѴ�Դ��Ҵ"),UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    } 
/*����͹�����Ӥѭ�ͧ�Է�Ի�Шӵ�Ǽ�����ŧ*/
    /**
     * 
     * @param vpp 
     * @param row 
     */
    public void downPriorityPatientPayment(Vector vpp,int row)
    {
        if(row == -1){
            theUS.setStatus(("��س����͡��¡���Է�Է���ͧ���"),UpdateStatus.WARNING);
            return;
        }
        if(row >= vpp.size()-1)
        {
            theUS.setStatus(("��س����͡��¡���Է�Է���������¡���ش����"),UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            PatientPayment pp = (PatientPayment)vpp.get(row);  
            pp.priority = String.valueOf(row+1);
            theHosDB.thePatientPaymentDB.update(pp);
            PatientPayment pp1 = (PatientPayment)vpp.get(row+1);  
            pp1.priority = String.valueOf(row);
            theHosDB.thePatientPaymentDB.update(pp1);
            vpp.remove(row);
            vpp.add(row+1,pp);
            theHO.vPatientPayment = vpp;
//            if(theHO.thePatient!=null)
                theHS.thePatientSubject.notifySavePatientPayment(
                        Constant.getTextBundle("���Ŵ�ӴѺ�ͧ�������Է�Ի�Шӵ�Ǽ������������"),UpdateStatus.COMPLETE);
//            else
//                theHS.thePersonSubject.notifySavePersonPayment(
//                        Constant.getTextBundle("���Ŵ�ӴѺ�ͧ�������Է�Ի�Шӵ�Ǽ������������"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(("���Ŵ�ӴѺ�ͧ�������Է�Ի�Шӵ�Ǽ����¼Դ��Ҵ"),UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    /*����͹�����Ӥѭ�ͧ�Է����Шӵ�Ǽ����¢��*/
    /**
     * 
     * @param vpp 
     * @param row 
     */
    public void upPriorityPatientPayment(Vector vpp,int row)
    {
        if(row == -1){
            theUS.setStatus(("��س����͡��¡���Է�Է���ͧ���"),UpdateStatus.WARNING);
            return;
        }
        if(row == 0)
        {
            theUS.setStatus(("��س����͡��¡���Է�Է���������¡���á"),UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            PatientPayment pp = (PatientPayment)vpp.get(row);  
            pp.priority = String.valueOf(row-1);
            theHosDB.thePatientPaymentDB.update(pp);
            PatientPayment pp1 = (PatientPayment)vpp.get(row-1);  
            pp1.priority = String.valueOf(row);
            theHosDB.thePatientPaymentDB.update(pp1);
            vpp.remove(row-1);
            vpp.add(row,pp1);  
            theHO.vPatientPayment = vpp;
//            if(theHO.thePatient!=null)
                theHS.thePatientSubject.notifySavePatientPayment(
                    Constant.getTextBundle("��������ӴѺ�ͧ�������Է�Ի�Шӵ�Ǽ������������"),UpdateStatus.COMPLETE);
//            else
//                theHS.thePersonSubject.notifySavePersonPayment(
//                    Constant.getTextBundle("��úѹ�֡�������Է�Ի�Шӵ���������"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(("��������ӴѺ�ͧ�������Է�Ի�Шӵ�Ǽ����¼Դ��Ҵ"),UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
/*�ѹ�֡�Է����Шӵ�Ǽ�����*/
    /**
     * 
     * @param vPPayment 
     * @param visitPayment 
     */
    public void savePatientPayment(Vector vPPayment,Payment visitPayment)
    {
        PatientPayment pp = new PatientPayment(visitPayment);
        savePatientPayment(vPPayment,pp);
    }
    /*�ѹ�֡�Է����Шӵ�Ǽ�����*/
    /**
     *
     * @param vPPayment
     * @param visitPayment
     */
    public void savePatientPayment2(Vector vPPayment,Payment visitPayment)
    {
        PatientPayment pp = new PatientPayment(visitPayment);
        savePatientPayment2(vPPayment,pp);
    }
/*�ѹ�֡�Է����Шӵ�Ǽ�����*/
    /**
     * 
     * @param vPPayment 
     * @param pPayment 
     */
    public void savePatientPayment(Vector vPPayment,PatientPayment pPayment)
    {
        Constant.println(UseCase.UCID_savePatientPayment);
        String objectid =   null;
        theConnectionInf.open();
        try{
            theUS.setStatus("�ѹ�֡�������",UpdateStatus.COMPLETE);
            boolean res = intSavePatientPayment(theHO.thePatient,theHO.theFamily,vPPayment,pPayment);
            if(!res)
                return;
            theHO.vPatientPayment = vPPayment;
            theHS.thePatientSubject.notifySavePatientPayment(
                Constant.getTextBundle("��úѹ�֡�������Է�Ի�Шӵ�Ǽ������������"),UpdateStatus.COMPLETE);
            if(pPayment != null)
                objectid = pPayment.getObjectId();
            theSystemControl.setStatus(UseCase.TH_savePatientPayment,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_savePatientPayment,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theUS.setStatus("�ѹ�֡�Դ��Ҵ",UpdateStatus.ERROR);
            theSystemControl.setStatus(UseCase.TH_savePatientPayment,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_savePatientPayment,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    /*�ѹ�֡�Է����Шӵ�Ǽ�����*/
    /**
     *
     * @param vPPayment
     * @param pPayment
     */
    public void savePatientPayment2(Vector vPPayment,PatientPayment pPayment)
    {
        Constant.println(UseCase.UCID_savePatientPayment);
        String objectid =   null;
        theConnectionInf.open();
        try{
            pPayment.checkplan_date = this.theHO.date_time.substring(0,10);
            boolean res = intSavePatientPayment2(theHO.thePatient,theHO.theFamily,vPPayment,pPayment);
            if(!res)
                return;
            theHO.vPatientPayment = vPPayment;
            theHS.thePatientSubject.notifySavePatientPayment(
                Constant.getTextBundle("��úѹ�֡�������Է�Ի�Шӵ�Ǽ������������"),UpdateStatus.COMPLETE);
            if(pPayment != null)
                objectid = pPayment.getObjectId();
            theSystemControl.setStatus(UseCase.TH_savePatientPayment,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_savePatientPayment,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_savePatientPayment,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_savePatientPayment,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
/*�ѹ�֡�Է����Шӵ�Ǽ�����*/
    /**
     * 
     * @param pt 
     * @param fm 
     * @param vPPayment 
     * @param pPayment 
     */
    public void savePatientPayment(Patient pt,Family fm,Vector vPPayment,PatientPayment pPayment)
    {
        theConnectionInf.open();
        try{
            intSavePatientPayment(pt, fm,vPPayment,pPayment);
            theHS.thePatientSubject.notifySavePatientPayment(
                    Constant.getTextBundle("��úѹ�֡�������Է�Ի�Шӵ�Ǽ������������"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(("��úѹ�֡�������Է�Ի�Шӵ�Ǽ����¼Դ��Ҵ"),UpdateStatus.ERROR);
        }
        finally {
            theConnectionInf.close();
        }
    }

    /**
     * 
     * 
     * @param pt 
     * @param fm 
     * @param vPPayment 
     * @param pPayment 
     * @throws java.lang.Exception 
     * @return 
     */
    public boolean intSavePatientPayment(Patient pt,Family fm,Vector vPPayment,PatientPayment pPayment)
    throws Exception
    {
        if(vPPayment==null) {
             theUS.setStatus(("����բ������Է�ԡ���ѡ�Ңͧ������"),UpdateStatus.WARNING);
             return false;
        }    
        if(pPayment.plan_kid.equals("")) {
             theUS.setStatus(("��س����͡�Է�ԡ���ѡ��"),UpdateStatus.WARNING);
             return false;
        }    
        if(pPayment.family_id==null && pPayment.patient_id==null) {
             theUS.setStatus(("��س����͡��Ъҡ����ͤ�����Ңͧ�Է��"),UpdateStatus.WARNING);
             return false;
        }  
        if(vPPayment==null)
                vPPayment = new Vector();
        //��Ǩ�ͺ������Է�ԫ�ӡѺ����������������������
        boolean is_exist = false;
        String exist_id = "";
        for(int i=0,size=vPPayment.size();i<size;i++)
        {
            Payment pm = (Payment)vPPayment.get(i);
            if(pm.plan_kid.equals(pPayment.plan_kid)){
                is_exist = true;
                exist_id = pm.getObjectId();
//                if(pPayment.getObjectId()==null) {
//                    theUS.setStatus(Constant.getTextBundle("�������ö�ѹ�֡�Է�Ի�Шӵ�ǫ����"),UpdateStatus.WARNING);
//                    return;
//                }
            }
        }
        if(vPPayment==null)
            vPPayment = new Vector();


        pPayment.visit_payment_update_date_time = theHO.date_time;
        pPayment.visit_payment_staff_update = theHO.theEmployee.getObjectId();
        if(pPayment.getObjectId()==null)
        {
            if(pt!=null)
                pPayment.patient_id = pt.getObjectId();
            if(fm!=null)
                pPayment.family_id = fm.getObjectId();       
            pPayment.record_date = theHO.date_time.substring(0,10);
            pPayment.visit_payment_record_date_time = theHO.date_time;
            pPayment.visit_payment_staff_record = theHO.theEmployee.getObjectId();
            pPayment.priority = String.valueOf(vPPayment.size());
            theHosDB.thePatientPaymentDB.deleteByPK(exist_id);
            theHosDB.thePatientPaymentDB.insert(pPayment);
        }
        else
        {
            pPayment.record_date = theHO.date_time.substring(0,10);
            theHosDB.thePatientPaymentDB.update(pPayment);
        }
        vPPayment.add(pPayment);
        return true;
    }
    /**
     *
     *
     * @param pt
     * @param fm
     * @param vPPayment
     * @param pPayment
     * @throws java.lang.Exception
     * @return
     */
    public boolean intSavePatientPayment2(Patient pt,Family fm,Vector vPPayment,PatientPayment pPayment)
    throws Exception
    {
        if(vPPayment==null) {
             theUS.setStatus(("����բ������Է�ԡ���ѡ�Ңͧ������"),UpdateStatus.WARNING);
             return false;
        }
        if(pPayment.plan_kid.equals("")) {
             theUS.setStatus(("��س����͡�Է�ԡ���ѡ��"),UpdateStatus.WARNING);
             return false;
        }
        if(pPayment.family_id==null && pPayment.patient_id==null) {
             theUS.setStatus(("��س����͡��Ъҡ����ͤ�����Ңͧ�Է��"),UpdateStatus.WARNING);
             return false;
        }
        if(vPPayment==null)
                vPPayment = new Vector();
        //��Ǩ�ͺ������Է�ԫ�ӡѺ����������������������
        boolean is_exist = false;
        String exist_id = "";
        for(int i=0,size=vPPayment.size();i<size;i++)
        {
            Payment pm = (Payment)vPPayment.get(i);
            if(pm.plan_kid.equals(pPayment.plan_kid)){
                is_exist = true;
                exist_id = pm.getObjectId();
//                if(pPayment.getObjectId()==null) {
//                    theUS.setStatus(Constant.getTextBundle("�������ö�ѹ�֡�Է�Ի�Шӵ�ǫ����"),UpdateStatus.WARNING);
//                    return;
//                }
            }
        }
        if(vPPayment==null)
            vPPayment = new Vector();


        pPayment.visit_payment_update_date_time = theHO.date_time;
        pPayment.visit_payment_staff_update = theHO.theEmployee.getObjectId();
        if(pPayment.getObjectId()==null)
        {
            if(pt!=null)
                pPayment.patient_id = pt.getObjectId();
            if(fm!=null)
                pPayment.family_id = fm.getObjectId();
            pPayment.record_date = theHO.date_time.substring(0,10);
            pPayment.visit_payment_record_date_time = theHO.date_time;
            pPayment.visit_payment_staff_record = theHO.theEmployee.getObjectId();
//            pPayment.priority = String.valueOf(vPPayment.size());
            theHosDB.thePatientPaymentDB.deleteByPK(exist_id);
            theHosDB.thePatientPaymentDB.insert(pPayment);
        }
        else
        {
            pPayment.record_date = theHO.date_time.substring(0,10);
            theHosDB.thePatientPaymentDB.update(pPayment);
        }
        vPPayment.add(pPayment);
        return true;
    }
    /*ź�Է����Шӵ�Ǽ�����*/
    /**
     * 
     * @param vPatientPayment 
     * @param select 
     * hosv4
     */
    public void deletePatientPayment(Vector vPatientPayment,int[] select)
    {
        Constant.println(UseCase.UCID_deletePatientPayment);
        String objectid =   null;
        // PCU �е�ͧ��㹡��ź
//        if(theHO.thePatient == null){    
//            theUS.setStatus(Constant.getTextBundle("�ѧ��������͡������"),UpdateStatus.WARNING);
//            return;
//        }
        if(vPatientPayment==null || vPatientPayment.size()==0){
            theUS.setStatus(("����բ������Է�ԡ���ѡ�Ңͧ������"),UpdateStatus.WARNING);
            return;
        }
        if(select.length == 0 ){
            theUS.setStatus(("�ѧ��������͡��¡���Է�ԡ���ѡ�Ңͧ������"),UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            String family_id = null;
            String patient_id = null;
            for(int i = 0;i < select.length; i++) {   
                PatientPayment p = (PatientPayment)vPatientPayment.get(select[i]);
                family_id = p.family_id;
                patient_id = p.patient_id;
                theHosDB.thePatientPaymentDB.delete(p);
            }
            theHO.vPatientPayment = theHosDB.thePatientPaymentDB.selectByFamilyPatient(theHO.theFamily,theHO.thePatient);
            
            for(int i = 0;theHO.vPatientPayment!=null && i < theHO.vPatientPayment.size(); i++) {
                PatientPayment p = (PatientPayment)theHO.vPatientPayment.get(i);
                p.priority = String.valueOf(i);
                theHosDB.thePatientPaymentDB.update(p);
                if(p!=null)
                    objectid = p.getObjectId();
                else
                    objectid = null;
                theSystemControl.setStatus(UseCase.TH_deletePatientPayment,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_deletePatientPayment,objectid,null,UpdateStatus.COMPLETE);
            }   
            theHO.vPatientPayment = theHosDB.thePatientPaymentDB.selectByFamilyPatient(theHO.theFamily,theHO.thePatient);
            theHS.thePatientSubject.notifyDeletePatientPayment(
                    Constant.getTextBundle("ź�Է�Ի�Шӵ�Ǽ������������º����"),UpdateStatus.COMPLETE);
            theUS.setStatus(Constant.getTextBundle("ź�Է�Ի�Шӵ�Ǽ������������º����"),UpdateStatus.COMPLETE);
        }
        catch(Exception ex){    
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(("ź�Է�Ի�Шӵ�Ǽ����¼Դ��Ҵ"), UpdateStatus.ERROR);
            theSystemControl.setStatus(UseCase.TH_deletePatientPayment,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deletePatientPayment,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     * 
     * @param str 
     * @return 
     * @deprecated henbe unused when
     */
    public int savePatientXN(String str)
    {
        if(str.equals("")){
            theUS.setStatus(("��س��к������Ţ XN �ͧ������"),UpdateStatus.WARNING);
            return 0;
        }
        theConnectionInf.open();
        try{
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
            
            
            
            theHO.thePatient.xn = str;
            return theHosDB.thePatientDB.updateXN(theHO.thePatient);
        }
        catch(Exception ex){    
            ex.printStackTrace(Constant.getPrintStream());
            return 0;
        }
        finally{
            theConnectionInf.close();
        }
    }
/**
     *  function
     *  ��㹡�� �� �Է�ԡ���ѡ�Ңͧ�����·���� Visit ����
     * @param patient_id 
     * @return 
     */
    public Vector listOldPaymentVisitBypatientID(String patient_id)
    {
        Vector vpayment = new Vector();
        Visit theVisit;
        theConnectionInf.open();
        try {
            theVisit = theHosDB.theVisitDB.selectVisitByPatientIDLast(patient_id);
            if(theVisit != null)
               vpayment = theHosDB.thePaymentDB.selectByVisitId(theVisit.getObjectId());
            return vpayment;
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return vpayment;
        }
        finally{
            theConnectionInf.close();
        }
    }
/**
     * 
     * 
     * @param da 
     * @throws java.lang.Exception 
     * @return 
     */     
    protected String intUpdateOrderAllergy(DrugAllergy da)throws Exception
    {
        //Constant.println("protected String intUpdateOrderAllergy(DrugAllergy da)throws Exception");
        if(!theLookupControl.readOption().isUseDrugInteract())
            return "";
        if("".equals(da.drug_standard_id))
            return "";
        if(theHO.vOrderItem==null)
            return "";
        
        String interaction = "";
        String std_old = "";
        for(int j=0,sizej=theHO.vOrderItem.size(); j<sizej; j++)
        {
            OrderItem orderItem = (OrderItem)theHO.vOrderItem.get(j);
            DrugStandardMapItem drugStandardMapItem
                    = theHosDB.theDrugStandardMapItemDB.selectByItem(orderItem.item_code);
            if(drugStandardMapItem!=null 
            && da.drug_standard_id.equals(drugStandardMapItem.drug_standard_id))
            {
                if("".equals(interaction))
                {
                    interaction = interaction + " " + da.drug_standard_description;
                    std_old = da.drug_standard_id; 
                }
                else
                {
                    if(!std_old.equals(da.drug_standard_id))
                    {
                        interaction = interaction + ", " + da.drug_standard_description;
                    }
                    std_old = da.drug_standard_id;
                }
            }
        } 
        return interaction;
    }
/**
     * 
     * 
     * @param not_allergy 
     * @param drugAllergy 
     * @throws java.lang.Exception 
        //���������� deny_allergy ��ͧ�� 0 , ������� �� 1 sumo 04/08/2549
        //�óդ�һ���ʸ���ҷ�������� false(�ҡ��õ�꡻���ʸ��������͡�ᶺ�ҡ���纻���) �������բ������ҷ����
        //¡��ԡ�к� ����ʸ���� �ѵ��ѵ� ���������˹�ҷ��ͧ��Һ�ŷ���ͧ�������ء case �ء����
        //����բ�������¡���ҷ���� ��� deny_allergy �� 0 sumo 04/09/2549
        //�óդ�һ���ʸ���ҷ�������� true(�ҡ��õ�꡻���ʸ��������ᶺ�ҡ���纻���) �ʴ���Ҽ������������ sumo 04/09/2549
     * @return 
     */
    protected String intSavePatientAllergy(boolean not_allergy) throws Exception
    {
        String deny_allergy = Active.isEnable();
        String patient_allergy = "0";
        if(not_allergy == true)
        {
            deny_allergy = "1";
            patient_allergy = "0";
        }
        else
        {
            deny_allergy = "0";
            patient_allergy = "1";
        }
        if(theHO.theVisit!=null)
        {
            theHO.theVisit.deny_allergy = deny_allergy; /*����ʸ������� ��� �������*/
            theHosDB.theVisitDB.updateDenyAllergy(theHO.theVisit);
            theHosDB.theQueueTransferDB.updateTransferPatientDenyAllergy(
                 theHO.thePatient.getObjectId(),patient_allergy);
        }        
        return null;
    }
    
    /**
     * �ѹ�֡��¡���ҷ����ͧ������
     * @param not_allergy 
     * @param drugAllergy 
     * @return 
     */
    public int savePatientAllergy(boolean not_allergy,Vector drugAllergy)
    {
        theConnectionInf.open();
        try{
            if(theHO.thePatient==null){
                theUS.setStatus(("��س����͡������"), UpdateStatus.WARNING);
                return 1;
            }
            if(drugAllergy!=null && drugAllergy.size()>0 && not_allergy)
            {
                boolean ret = theUS.confirmBox(Constant.getTextBundle("����������¡���ҷ��������") +" "+
                        Constant.getTextBundle("�׹�ѹ��û���ʸ����"), UpdateStatus.WARNING);
                if(!ret) 
                    return 1;
                // ���������������� Vector ����� null ������׹�ѹ���ź��¡���ҷ���� sumo 04/08/2549
                drugAllergy = null;
            }
            String interaction = intSavePatientAllergy(not_allergy);
            if(interaction ==null || interaction.equals(""))
            {
                interaction = Constant.getTextBundle("��úѹ�֡�ҷ�����������");
                theHS.thePatientSubject.notifyManageDrugAllergy(interaction,UpdateStatus.COMPLETE);
            }
            else
            {
                interaction = Constant.getTextBundle("��úѹ�֡�ҷ�����������")+" "+
                        Constant.getTextBundle("���ռšѺ") +
                        " " + interaction;
                theHS.thePatientSubject.notifyManageDrugAllergy(interaction,UpdateStatus.WARNING);
            }
            
        }
        catch(Exception ex)
        {   
            theHO.flag = true;
            theUS.setStatus(("��úѹ�֡�ҷ����Դ��Ҵ"),UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());  
        }
        finally
        {
            theConnectionInf.close();
        }
        return 0;
    }
    /**
     * 
     * 
     * @Author: amp
     * @date: 31/03/2549
     * ����Ҫش������ҵ�Ǩ�ͺ����� standard_id ����������ǡ�ӡ�����ҧ Allergy �ش����
     * �������������´�ͧ DrugAllergy ���ú���Ѻ object ���ҧ�����������ǡѺ�ҹ���������
     * @param vAllergy 
     * @throws java.lang.Exception 
     */
    protected void intFilterVectorDrugAllergy(Vector vAllergy) throws Exception
    {
        if(vAllergy!=null)
        {
            Vector vDrugAllergyTemp = new Vector();
            for(int i=0,size=vAllergy.size(); i<size; i++)
            {
                DrugAllergy drugAllergyTemp = (DrugAllergy)vAllergy.get(i);
                if(!"".equals(drugAllergyTemp.drug_standard_id))
                {
                    Vector vDrugStandardMapItemTemp = theHosDB.theDrugStandardMapItemDB
                            .selectItemByStandardId(drugAllergyTemp.drug_standard_id);
                    if(vDrugStandardMapItemTemp!=null)
                    {//���ҵðҹ���Ѻ��� item ����                        
                        for(int k=0,sizek=vDrugStandardMapItemTemp.size(); k<sizek; k++)
                        {
                            DrugStandardMapItem drugStandardMapItem
                                    = (DrugStandardMapItem)vDrugStandardMapItemTemp.get(k);
                            DrugAllergy dal1 = new DrugAllergy();
                            dal1.setObjectId(drugAllergyTemp.getObjectId());
                            dal1.item_code = drugStandardMapItem.item_id;
                            dal1.common_name = drugStandardMapItem.item_description;
                            dal1.patient_id = theHO.thePatient.getObjectId();
                            dal1.drug_standard_id = drugStandardMapItem.drug_standard_id;
                            dal1.drug_standard_description = drugStandardMapItem.drug_standard_description;
                            dal1.symptom = drugAllergyTemp.symptom;
                            vDrugAllergyTemp.addElement(dal1);
                        }
                    }
                    else
                    {
                        vDrugAllergyTemp.addElement(drugAllergyTemp);
                    }
                }
                else
                {
                    vDrugAllergyTemp.addElement(drugAllergyTemp);
                }
            }
            theHO.vDrugAllergy = vDrugAllergyTemp;
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////////
    /**
     * ź��¡���ҷ����ͧ������
     * @param vDrugallergy 
     * @param row 
     * @not deprecated henbe unused
     */
    public void deletePatientAllergy(Vector vDrugallergy,int[] row)
    {
        Constant.println(UseCase.UCID_deletePatientAllergy);
        String objectid =   null;
        if(row.length==0) {
            theUS.setStatus(("�ѧ����բ�����"),UpdateStatus.WARNING);
            return;
        }
        
        try{
            theConnectionInf.open();
            for(int i=0;i<row.length;i++){
                vDrugallergy.remove(row[i]);
            }
            intSavePatientAllergy(vDrugallergy);
            theHS.thePatientSubject.notifyManageDrugAllergy(Constant.getTextBundle("���ź�����������������"),
                    UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deletePatientAllergy,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deletePatientAllergy,objectid,null,UpdateStatus.COMPLETE);
            //////////////////////////////////////////////////////////////////////////////////////////////
        }
        catch(Exception ex){  
            theSystemControl.setStatus(UseCase.TH_deletePatientAllergy,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deletePatientAllergy,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    public String intSavePatientAllergy(Vector vDrugallergy)throws Exception
    {
        String interaction = "";
        //////////////////////////////////////////////////////////////////////////
        theHosDB.theDrugAllergyDB.deleteByPtid(theHO.thePatient.getObjectId());
        for(int i=0;i<vDrugallergy.size(); i++)
        {
            DrugAllergy da = (DrugAllergy)vDrugallergy.get(i);
            da.patient_id = theHO.thePatient.getObjectId();
            if(da.record_date_time.equals(""))
                da.record_date_time = theHO.date_time;
            theHosDB.theDrugAllergyDB.insert(da);
            //amp:04/04/2549
            interaction = intUpdateOrderAllergy(da);
        }
        //////////////////////////////////////////////////////////////////////////
        String patient_allergy = "0";
        if(!vDrugallergy.isEmpty())
            patient_allergy = "1";
        
        theHosDB.thePatientDB.updateAllergy(theHO.thePatient.getObjectId(),patient_allergy);
        //////////////////////////////////////////////////////////////////////////////////////////////
        if(theHO.theVisit!=null && theHO.theVisit.visit_status.equals(VisitStatus.isInProcess()))
        {
            for(int i=0;i<vDrugallergy.size();i++)
            {
                DrugAllergy da = (DrugAllergy)vDrugallergy.get(i); 
                theHosDB.theOrderItemDB.updateDrugAllergyByItemIdAndVisitId(
                        da.item_code, theHO.theVisit.getObjectId());
                //��Ǩ�ͺ��������ҵðҹ�������ա���·�褹�������ѹ��ͺ����
                //������ú��ǹ�е�ͧ��Ǩ�ͺ
                //�ҷء��Ƿ�褹�����Ѻ ��º�Ѻ��������ҵðҹ��褹����
            }
//            theHO.theVisit.deny_allergy = Active.isEnable();
//            theHosDB.theVisitDB.updateDenyAllergy(theHO.theVisit);
            theHosDB.theQueueTransferDB.updateTransferPatientDenyAllergy(
                theHO.thePatient.getObjectId(),patient_allergy);
        }
        theHO.vDrugAllergy = theHosDB.theDrugAllergyDB.selectByPatientId(theHO.thePatient.getObjectId());  
        return interaction;
    }
    
    /**
     * 
     * 
     * @return : Vector �ͧ AppointmentOrder
     * @see : ź��¡�� order ����������ǧ˹�ҡѺ��ùѴ
     * @Author : amp
     * @date : 24/02/2549
     * @param vAppointmentOrder 
     * @param row 
     */
    public Vector deleteAppointmentOrder(Vector vAppointmentOrder,int[] row)
    {
        if(row.length==0) 
        {
            theUS.setStatus(("�ѧ����բ�����"),UpdateStatus.WARNING);
            return vAppointmentOrder;
        }
        try
        {
            theConnectionInf.open();
            for(int i=row.length-1; i>=0; i--)
            {
                AppointmentOrder apor = (AppointmentOrder)vAppointmentOrder.get(row[i]); 
                if(apor.getObjectId()!= null)
                {   
                    theHosDB.theAppointmentOrderDB.delete(apor);
                }
                vAppointmentOrder.remove(row[i]);
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
        return vAppointmentOrder;
    }
    
    /**
     * 
     * 
     * @return : Vector �ͧ AppointmentOrder
     * @see : ź��¡�� order ����������ǧ˹�ҡѺ��ùѴ
     * @Author : amp
     * @date : 24/02/2549
     * @param patient_id 
     * @param appointment_id 
     */
    public Vector listAppointmentOrder(String patient_id,String appointment_id)
    {
        Vector result = null;
        theConnectionInf.open();
        try
        {
            result = theHosDB.theAppointmentOrderDB.selectByPatientAndAppointment(patient_id,appointment_id);
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

   
    public boolean readPatientHistory()
    {
        String patient_id = theHO.thePatient.getObjectId();
        this.readPatientHistory(patient_id);
        return true;
    }
    /**
     * 
     * @param patient 
     * @throws java.lang.Exception 
     * @return 
     * @deprecated used         
     *  intReadFamilySuit(patient.getFamily(),patient);
        intReadPatientSuit(patient);
     * instead
    protected int intReadPatient(Patient patient) throws Exception
    {   
        Constant.println("protected int intReadPatient(Patient patient) throws Exception");
        intReadFamilySuit(patient.getFamily(),patient);
        intReadPatientSuit(patient);
        return 0;
    }
     */
   
    /////////////////////////////////////////////////////////////////////////////

    /**
     * 
     * 
     * @return : �����
     * @see : ź��¡�ùѴ ���繡�� Update ����� Inactive ���᷹����ź�͡仨ҡ DB
     * @Author : sumo
     * @date : 27/03/2549
     * @param appointment 
     * @param theUS 
     */
    public boolean deleteAppointment(Appointment appointment,UpdateStatus theUS)
    { 
        if(!theUS.confirmBox(("�׹�ѹ���¡��ԡ��ùѴ"),UpdateStatus.WARNING))
            return false;
        
        theConnectionInf.open();
        try{
            boolean b = intDeleteAppointment(appointment,theUS);
            theUS.setStatus(("¡��ԡ��¡�ùѴ�������"),UpdateStatus.COMPLETE);
            return b;
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(("¡��ԡ��¡�ùѴ�Դ��Ҵ"),UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    public boolean deleteVAppointment(Vector vAppointment,int[] row,UpdateStatus theUS) 
    {
        Constant.println(UseCase.UCID_deleteVAppointment);
        String objectid =   null;
        if(row.length==0)      {
            theUS.setStatus(("��س����͡��¡�÷���ͧ���¡��ԡ"),UpdateStatus.WARNING);
            return false;
        }
        if(vAppointment.isEmpty()){
            theUS.setStatus(("����բ����ŷ���ͧ���¡��ԡ"),UpdateStatus.WARNING);
            return false;
        }
        if(!theUS.confirmBox(Constant.getTextBundle("�׹�ѹ���¡��ԡ��ùѴ"),UpdateStatus.WARNING))
            return false;
        theConnectionInf.open();
        try{
            boolean b=false;
            for(int i=0;i<row.length;i++) {
                SpecialQueryAppointment appointment = (SpecialQueryAppointment)vAppointment.get(row[i]);
                Appointment app = theHosDB.theAppointmentDB.selectByPK(appointment.t_patient_appointment_id);
                if(intDeleteAppointment(app,theUS)) {
                    theUS.setStatus(("¡��ԡ��¡�ùѴ�������"),UpdateStatus.COMPLETE);
                    b=true;
                }                    
            }
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deleteVAppointment,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteVAppointment,objectid,null,UpdateStatus.COMPLETE);
            return b;
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_deleteVAppointment,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deleteVAppointment,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
                
        {
            theConnectionInf.close();
        }
    }
    
    public boolean intDeleteAppointment(Appointment appointment,UpdateStatus theUS) throws Exception
    {
        if(appointment  == null || appointment.getObjectId()==null)
        {
            theUS.setStatus(("��س����͡��¡�ùѴ����ͧ���¡��ԡ��͹������ź"),UpdateStatus.WARNING);
            return false;
        }
        if(!appointment.status.equals(Active.isDisable()))
        {
            theUS.setStatus(("���͹حҵ���ź��¡�ùѴ�����ʶҹС�ùѴ���������͡�ùѴ"),UpdateStatus.WARNING);
            return false;
        }  
        if(appointment.appoint_active.equals(Active.isDisable()))
        {
            theUS.setStatus(("��¡�ùѴ��١¡��ԡ�����"),UpdateStatus.WARNING);
            return false;
        }
        
        appointment.appoint_active = "0";
        appointment.appoint_staff_cancel = theHO.theEmployee.getObjectId();
        appointment.appoint_cancel_date_time = theLookupControl.intReadDateTime();
        theHosDB.theAppointmentDB.update(appointment);
        
        //amp:13/05/2549 ź��¡�ùѴ���� ���ź��¡�� order ��ǧ˹�Ҵ���
        Vector app_order = theHosDB.theAppointmentOrderDB.selectByAppointment(appointment.getObjectId());
        if(app_order!=null){
            for(int i=app_order.size()-1; i>=0; i--)
            {
                theHosDB.theAppointmentOrderDB.delete((AppointmentOrder)app_order.get(i));
            }
        }
        
        return true;
    }
    /**
     * 
     * 
     * @Author: sumo
     * @date: 08/08/2549
     * @param: String ���ʢ����š�ùѴ
     * @return: Object Appointment
     * @see: ��ҹ�����š�ùѴ�ҡ���ҧ
     * @param pk 
     * @return 
     */
    public Appointment readAppointmentByPK(String pk)
    {
        Appointment pa;
        theConnectionInf.open();
        try{
            pa = theHosDB.theAppointmentDB.select2ByPK(pk);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
             return null;
        }
        finally{
            theConnectionInf.close();
        }
        return pa;
    }   

    public Hashtable readHAppointmentByPK(String pk)
    {
        theConnectionInf.open();
        try{
            Appointment pa = theHosDB.theAppointmentDB.select2ByPK(pk);
            Vector v = theHosDB.theAppointmentOrderDB.selectByAppointment(pk);
            if(pa!=null){
                Hashtable ht = new Hashtable();
                ht.put("Appointment", pa);
                ht.put("AppointmentOrderV",v);
                return ht;
            }
            return null;
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
             return null;
        }
        finally{
            theConnectionInf.close();
        }
    }   
    /**
     * 
     * @param pk 
     * @return 
     */
   public Vector listNCDByPatientId(String pk)
    {
        theConnectionInf.open();
        try{
            theHO.vNCD = theHosDB.theNCDDB.selectByPatientId(pk);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
             return null;
        }
        finally{
            theConnectionInf.close();
        }
        return theHO.vNCD;
    }   
   /**
     * @description ��㹡��¡��ԡ��Ъҡ� ���͵�ͧ�������¹��èѺ��褹�餹˹����繻�Ъҡ��ա��˹��
     * @param family 
     * @param pt 
     * @return 
     */
    public int deleteFamily(Family family,Patient pt) 
    {
        Constant.println(UseCase.UCID_deleteFamily);
        String objectid =   null;
        theConnectionInf.open();
        try{
            int result = intDeleteFamily(family,pt);
            if(result==0)
                return result;
            theVisitControl.intUnlockVisit(theHO.theVisit);
            theHO.clearFamily();
            theUS.setStatus(("���¡��ԡ�����Ż�Ъҡ��������"),theUS.COMPLETE);
            if(family != null)
                objectid = family.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deleteFamily,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteFamily,objectid,null,UpdateStatus.COMPLETE);
            return result;
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_deleteFamily,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deleteFamily,objectid,ex,UpdateStatus.ERROR);
            return 0;
        }
        finally{
            theConnectionInf.close();    
        }
    }

   /**
     * @description ��㹡��¡��ԡ��Ъҡ� ���͵�ͧ�������¹��èѺ��褹�餹˹����繻�Ъҡ��ա��˹��
     * @param family
     * @param pt
     * @return
     */
    public int deletePerson(Family family)
    {
        Constant.println(UseCase.UCID_deleteFamily);
        String objectid =   null;
        if(family != null && family.active.equals("0")){
            theUS.setStatus("�����������ʶҹ�¡��ԡ�����������ö¡��ԡ���ա",UpdateStatus.WARNING);
            return 3;
        }
        if(theHO.theVisit!=null){
            theUS.setStatus("����������㹡�кǹ��á�سҨ���кǹ��á�͹�ӡ��¡��ԡ",UpdateStatus.WARNING);
            return 4;
        }
        String pid = JOptionPane.showInputDialog(theUS.getJFrame()
                    ,Constant.getTextBundle("��ͧ����������ѵ���������ҡ��ͧ��á�س��к��Ţ�ѵû�ЪҪ�")
                    ,Constant.getTextBundle("¡��ԡ��Ъҡ�")
                    ,JOptionPane.YES_NO_OPTION);
        if(pid == null){
            return 1;
        }
        theConnectionInf.open();
        try{
//            String pid = JOptionPane.showInputDialog(theUS.getJFrame()
//                    ,Constant.getTextBundle("��ͧ����������ѵ���������ҡ��ͧ��á�س��к��Ţ�ѵû�ЪҪ�")
//                    ,Constant.getTextBundle("¡��ԡ��Ъҡ�")
//                    ,JOptionPane.YES_NO_OPTION);
//            if(pid == null){
//                return 0;
//            }
            if(pid!=null && !pid.equals("")){
                Vector famV = theHosDB.theFamilyDB.selectByPid(pid,"1");
                String des_family_id = null;
                if(famV.isEmpty()){
                    theUS.confirmBox("��辺��Ъҡ÷���ͧ����������ѵԡ�س��������кǹ��������ա����", UpdateStatus.WARNING);
                    return 1;
                } 
                else {
                    Vector retV = new Vector();
                    for(int i=0;i<famV.size();i++){
                        Family fam = (Family)famV.get(i);
                        String dis = fam.patient_name + " " + fam.patient_last_name
                            + " �ѹ�Դ " + DateUtil.getDateShotToString(DateUtil.getDateFromText(fam.patient_birthday),false);
                        if(!fam.mother_firstname.equals("")){
                            dis+= " ��ôҪ��� "+ fam.mother_firstname;
                        }
                        String[] data = new String[]{
                            fam.getObjectId(),dis
                        };
                        retV.add(data);
                    }
                    des_family_id = DialogList.showDialog("�׹�ѹ������ͧ����������ѵ�",theUS.getJFrame(), retV);
                    if(des_family_id==null)
                        return 2;
                }
                //�������ѵԻ�Ъҡ�
                theHosDB.thePatientPaymentDB.updateFidByFid(des_family_id,family.getObjectId());//no log
                theHosDB.theChronicDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theSurveilDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theDeathDB.updateFidByFid(des_family_id,family.getObjectId());//no log
                theHosDB.theAncDetailPcuDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theAfterMchMotherDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theAncPcuDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theBornMchDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theCheckHealthDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theCheckHealthYearDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theCounselDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theDentalDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theEfficiencyDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theEpiDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theEpiDetailDB.updateFidByFid(des_family_id,family.getObjectId());//no log
                theHosDB.theEpiOutSiteDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theFamilyPlaningDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theGrowHistoryDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theGrowPcuDB.updateFidByFid(des_family_id,family.getObjectId());//no log
                theHosDB.theMaimDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theNutritionDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.thePPCareDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.thePPDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.thePregnancyDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theUncontagiousDB.updateFidByFid(des_family_id,family.getObjectId());
                theHosDB.theVisitHomeDB.updateFidByFid(des_family_id,family.getObjectId());
                family.work_office = " ���»���ѵ���Ţ�ѵ� " + des_family_id;
                //////////////////////////////////////////////////////////////////
                Patient pdel = theHosDB.thePatientDB.selectByFid(family.getObjectId());
                Patient pdes = theHosDB.thePatientDB.selectByFid(des_family_id);
                //������ջ���ѵԼ����� �褹���·ҧ����ջ���ѵԼ����������Ҽ����¤�����᷹�����
                if(pdel!=null && pdes==null){
                    theHosDB.thePatientDB.updateFidByFid(des_family_id,pdel.family_id);
                    pdes = theHosDB.thePatientDB.selectByFid(des_family_id);// SOmprasong add 130810
                }
                // Somprasong comment 130810 ��ҷ�Ẻ����������������١ inactive ��ͧ���鴴�ҹ��ҧ����
//                else if(pdel!=null && pdes!=null){
                
                if(pdel!=null && pdes!=null){
                    theHosDB.theVisitDB.updatePatientByPatient(pdes.getObjectId(),pdel.getObjectId());
                    theHosDB.theDrugAllergyDB.updatePatientByPatient(pdes.getObjectId(),pdel.getObjectId());
                    theHosDB.theAppointmentDB.updatePatientByPatient(pdes.getObjectId(),pdel.getObjectId());
                    theHosDB.theAccidentDB.updatePatientByPatient(pdes.getObjectId(),pdel.getObjectId());
                    theHosDB.thePatientPaymentDB.updatePatientByPatient(pdes.getObjectId(),pdel.getObjectId());
                    theHosDB.thePatientXNDB.updatePatientByPatient(pdes.getObjectId(),pdel.getObjectId());
                    pdel.active = "0";
                    pdel.staff_cancel = theHO.theEmployee.getObjectId();
                    pdel.cancel_date_time = theHO.date_time;
                    theHosDB.thePatientDB.updateActiveByPatientID(pdel);
                }
            }
            family.staff_cancel = theHO.theEmployee.getObjectId();
            family.cancel_date_time = theHO.date_time;
            family.active = "0";
            theHosDB.theFamilyDB.updateActive(family);
            ////////////////////////////////////////////////////////////////////////
            // Somprasong comment 130810 
//            theVisitControl.intUnlockVisit(theHO.theVisit);
            theVisitControl.unlockVisit();
            theHO.clearFamily();
            theUS.setStatus(("���¡��ԡ�����Ż�Ъҡ��������"),theUS.COMPLETE);
            objectid = family.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deleteFamily,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteFamily,objectid,null,UpdateStatus.COMPLETE);
            return 0;
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_deleteFamily,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deleteFamily,objectid,ex,UpdateStatus.ERROR);
            return -1;
        }
        finally{
            theConnectionInf.close();
        }
    }

    public int intDeleteFamily(Family family,Patient pt) throws Exception
    {
        if(family==null ){
            theUS.setStatus(("��س����͡��Ъҡ÷���ͧ���ź"),UpdateStatus.WARNING);
            return 0;
        }
        String message = Constant.getTextBundle("�׹�ѹ����¡��ԡ��Ъҡä�������������");
        if(!theUS.confirmBox(message,UpdateStatus.WARNING)) {
            return 0;
        }
            boolean merge_patient = false;
            String pid = "";
//            Patient pt1 = theHosDB.thePatientDB.selectByFid(family.getObjectId());
            if(true)//pt1!=null && pt1.active.equals("1")) {
            {
                if (!theHO.theEmployee.authentication_id.equals(Authentication.ADMIN)) {
                    theUS.setStatus(Constant.getTextBundle("��Ъҡ��ջ���ѵԼ���������" + " " +
                            Constant.getTextBundle("�������к���ҹ�鹷�����Է��¡��ԡ����������ѵ�")),UpdateStatus.WARNING);
                    return 0;
                }
                message = Constant.getTextBundle("��Ъҡ��ջ���ѵԼ���������" +
                        "\n " +
                        Constant.getTextBundle("��ͧ������¢����ż�������ѧ��Ъҡä�����������") +
                        "\n " +
                        Constant.getTextBundle("�ҡ���سҡ�͡�Ţ�ѵû�ЪҪ� �ͧ��Ъҡä����"));
                pid = JOptionPane.showInputDialog(theUS.getJFrame(),message,Constant.getTextBundle("��������Ż�Ъҡ�")
                        ,JOptionPane.YES_NO_OPTION);
                if(pid!=null && !pid.equals(""))
                    merge_patient = true;
                else{
                    theUS.setStatus(Constant.getTextBundle("�ѧ����ա��¡��ԡ�����Ż�Ъҡ�")+
                            Constant.getTextBundle("�ҡ�ѧ�����¡��ԡ������"), UpdateStatus.WARNING);
                    return 0;
                }
            }
            Family des_fm = null;
            if(merge_patient){
                Constant.println("____________hn is " + pid);
                des_fm = theHosDB.theFamilyDB.selectByPid(pid);
                if(des_fm==null){
                    theUS.setStatus(Constant.getTextBundle("��辺�����Ż�Ъҡ÷�����Ţ�ѵû�ЪҪ�")+" " +pid+
                            " " + Constant.getTextBundle("�������öź�����Ż�Ъҡ���"), UpdateStatus.WARNING);
                    return 0;
                }
            }
            family.staff_cancel = theHO.theEmployee.getObjectId();
            family.cancel_date_time = theHO.date_time;
            family.active = "0";
            if(merge_patient)
                family.work_office = "¡��ԡ ���»���ѵ���ѧ�Ţ�ѵû�ЪҪ� " + des_fm.pid;
            theHosDB.theFamilyDB.update(family);
            
            if(merge_patient){
                theHosDB.thePatientDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.thePatientPaymentDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theChronicDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theSurveilDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theDeathDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                
                theHosDB.theAncDetailPcuDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theAfterMchMotherDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theAncPcuDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theBornMchDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theCheckHealthDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theCheckHealthYearDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theCounselDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theDentalDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theEfficiencyDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theEpiDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theEpiDetailDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theEpiOutSiteDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theFamilyPlaningDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theGrowHistoryDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theGrowPcuDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theMaimDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theNutritionDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.thePPCareDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.thePPDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.thePregnancyDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theUncontagiousDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theVisitHomeDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
            }
            return 1;
    }   
    /**
     *@deprecated henbe unused
     */
    public int intDeleteFamily(Family family,boolean merge_patient,Family des_fm) throws Exception
    {
            family.staff_cancel = theHO.theEmployee.getObjectId();
            family.cancel_date_time = theHO.date_time;
            family.active = "0";
            if(merge_patient)
                family.work_office = "¡��ԡ ���»���ѵ���ѧ�Ţ�ѵû�ЪҪ� " + des_fm.pid;
            int result = theHosDB.theFamilyDB.update(family);
            
            if(merge_patient){
                theHosDB.thePatientDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.thePatientPaymentDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theChronicDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theSurveilDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theDeathDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                
                theHosDB.theAncDetailPcuDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theAfterMchMotherDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theAncPcuDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theBornMchDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theCheckHealthDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theCheckHealthYearDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theCounselDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theDentalDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theEfficiencyDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theEpiDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theEpiDetailDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theEpiOutSiteDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theFamilyPlaningDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theGrowHistoryDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theGrowPcuDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theMaimDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theNutritionDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.thePPCareDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.thePPDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.thePregnancyDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theUncontagiousDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
                theHosDB.theVisitHomeDB.updateFidByFid(des_fm.getObjectId(),family.getObjectId());
            }
            return 1;
    }    
    
    /**
     * @deprecated henbe unused
     * @param family 
     * @param home 
     * @throws java.lang.Exception 
     * @return 
     */
    public int intSaveFamily(Family family,Home home) throws Exception 
    {
        return intSaveFamily(family,home,true,theUS);
    }
    /**
     * @deprecated henbe unused
     * @param family 
     * @param home 
     * @throws java.lang.Exception 
     * @return 
     */
    public int intSaveFamily(Family family,Home home,boolean check_pid) throws Exception 
    {
        return intSaveFamily(family,home,true,theUS);
    }
    /**
     * �繿ѧ�ѹ������ҧ����¡����Шҡ�����ҧ����÷���� Transaction � HO
     * @param family
     * @param home
     * @param check_pid
     * @param theUS
     * @return
     * @throws Exception
     */
    public int intUDSaveFamily(Family family,Home home,boolean check_pid,UpdateStatus theUS) throws Exception
    {
 
        int result = 0;
        if(family==null){
            theUS.setStatus(("��辺�����Ż�Ъҡ÷��зӡ�úѹ�֡"),UpdateStatus.WARNING);
            return 0;
        }

        if(family.getObjectId()==null && !family.pid.equals("") && check_pid) {
            Vector v = theHosDB.theFamilyDB.selectFamilyByPId(family.pid);
            if(!v.isEmpty()){
                Family fm = (Family)v.get(0);
                theUS.setStatus(Constant.getTextBundle("��سҵ�Ǩ�ͺ�Ţ�ѵû�ЪҪ��ͧ��Ъҡê���")
                + fm.patient_name + " " + fm.patient_last_name ,UpdateStatus.WARNING);
                return -1;
            }
        }
        if(family.patient_birthday.equals("")) {
            theUS.setStatus(("��س��к��ѹ�Դ�ͧ��Ъҡ�"),UpdateStatus.WARNING);
            return 0;
//            throw new Exception("��س��к��ѹ�Դ�ͧ��Ъҡ�");
        }
        ////////////////////////////////////////////////////////////////
        if(home==null){

            theUS.setStatus(("��س����͡��ҹ����Ъҡü�������������"),UpdateStatus.WARNING);
            return 0;
        }
        family.home_id = home.getObjectId();
        if(family.active.equals(""))
            family.active = "1";

        if(!theLO.theOption.auto_add_prefix.equals("1") && family.f_prefix_id.startsWith("add:")){
            theUS.setStatus(("��س����͡�ӹ�˹�Ҫ��ͷ����������ҹ��"),UpdateStatus.WARNING);
            return 0;
        }
        family.modify_date_time = theHO.date_time;
        family.staff_modify =  theHO.theEmployee.getObjectId();
        if(family.hn_hcis.equals(""))
            family.hn_hcis = theHosDB.theSequenceDataDB.updateSequence("hn_hcis",true);
        //��Ѻ���Ţ hn hcis ��������������Ţ�ҵðҹ 6 ��ѡ
        if(family.hn_hcis.length()!=6){
            while(family.hn_hcis.length()<6)
                family.hn_hcis = "0"+family.hn_hcis;
            while(family.hn_hcis.length()>6)
                family.hn_hcis = family.hn_hcis.substring(1);
        }
        if(family.getObjectId()==null||family.getObjectId().length()==0){
//            System.out.println("family "+family.getObjectId());
            family.record_date_time = theHO.date_time;
            family.staff_record =  theHO.theEmployee.getObjectId();
            result = theHosDB.theFamilyDB.insert(family);
        }
        else{
            result = theHosDB.theFamilyDB.update(family);
        }
        if(family.status_id.equals("1")){
            theConnectionInf.eUpdate("update t_health_home set " +
                    " health_home_owner_number = '"+family.hn_hcis+"' " +
                    ", health_home_owner = '"+family.patient_name+" "+family.patient_last_name+"' " +
                    " where t_health_home_id = '"+home.getObjectId()+"'");
        }
        return 1;
    }
    // �ѧ�ѹ����Ҩ�١���¡��ҹ�ҡ˹�Ҩ�������֧������ա����� theHO.xx ���Шз�����к��֧����Դ��Ҵ
    public int intSaveFamily(Family family,Home home,boolean check_pid,UpdateStatus theUS) throws Exception 
    {
        int ret = intUDSaveFamily(family,home,check_pid,theUS);
        theHO.theFamilyFather = theHosDB.theFamilyDB.selectByPK(family.father_fid);
        theHO.theFamilyMother = theHosDB.theFamilyDB.selectByPK(family.mother_fid);
        theHO.theFamilyCouple = theHosDB.theFamilyDB.selectByPK(family.couple_fid);
        return ret;
    }   
            
    /**tong used it*/
    /**
     * Creates a new instance of createPatientAllergy
     *  ��Ǩ�ͺ �����¡�͹��� ������Ѻ��ԡ��������� ����¨����ź�͡���
     * @param p 
     * @return 
     */
    public int deletePatient(Patient p)
    {
        Constant.println("public int deletePatient(Patient p)");
        Constant.println(UseCase.UCID_deletePatient);
        String objectid =   null;
        theConnectionInf.open();
        try{
            if(p==null || p.getObjectId()==null){
                if(p.family_id.equals("")){
                    theUS.setStatus(("��س����͡������"), UpdateStatus.WARNING);
                    return 0;
                }
            }
            if(theHO.theVisit!=null) {
                theUS.setStatus(("����������㹡�кǹ��� �������öź�����ż�������"), UpdateStatus.WARNING);
                return 0;
            }
            if(!theUS.confirmBox(Constant.getTextBundle("�׹�ѹ����¡��ԡ�����¤�������������"),UpdateStatus.WARNING))
                return 0;

            if(theLookupControl.readOption().del_patient.equals(Active.isEnable())) {
                boolean retb = DialogPasswd.showDialog(theHO,theUS,theHO.theEmployee.password);
                if(!retb) {
                    theUS.setStatus(("���ʼ�ҹ���١��ͧ"),UpdateStatus.WARNING);
                    return 0;
                }
            }
            Vector vc = theHosDB.theVisitDB.selectVisitByPatientID(p.getObjectId());
            boolean is_visit_cancel = true;
            for(int i=0,size=vc.size();vc!=null &&i<size;i++){
                Visit visit = (Visit)vc.get(i);
                if(!visit.visit_status.equals(VisitStatus.isDropVisit()))
                    is_visit_cancel = false;
            }
            String hn = "";
            if(vc.size()>0 && !is_visit_cancel) {
                String message = Constant.getTextBundle("���������ջ���ѵԡ���Ѻ��ԡ������") +
                        " \n " +
                        Constant.getTextBundle("��ͧ������¢����š���Ѻ��ԡ����ѧ������������") +
                        " \n" +
                        Constant.getTextBundle("�ҡ�� ��سҡ�͡�Ţ HN �ͧ�����¤����");
                hn = JOptionPane.showInputDialog(theUS.getJFrame(),message,
                        Constant.getTextBundle("��������ż�����"),JOptionPane.YES_NO_OPTION);
            }            
            int res = intDeletePatient(p,hn);
            if(res==-1)
                return res;
            
            if(!p.family_id.equals("")) {
                res = intDeleteFamily(p.getFamily(),p);
            }
            theHS.thePatientSubject.notifyDeletePatient(Constant.getTextBundle("���¡��ԡ�����ż������������"),
                    UpdateStatus.COMPLETE);
            if(p != null)
                objectid = p.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deletePatient,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deletePatient,objectid,null,UpdateStatus.COMPLETE);
            return res;
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_deletePatient,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deletePatient,objectid,ex,UpdateStatus.ERROR);
            return 0;
        }
        finally 
        {
            theConnectionInf.close();
        }
    }
    public int intDeletePatient(Patient p_src,String hn)throws Exception 
    {
            int res = 0;
            if(hn!=null && !hn.equals("")){
//                Constant.println("____________hn is " + hn);
                Patient pt_des = theHosDB.thePatientDB.selectByHnEqual(hn);
                if(pt_des==null){
                    theUS.setStatus(Constant.getTextBundle("��辺�����ż�����")+" "+
                            Constant.getTextBundle("HN")+" " +hn+ " " +
                            Constant.getTextBundle("�������öź�����ż�������"), UpdateStatus.WARNING);
                    return -1;
                }
                int ret = intMovePatientHistory(theUS,pt_des,p_src);
                //��Ҿ���Ң����ż��������ç�ѹ������ж������ա����˹���ҡ�ͺ���������੾�Тͧ�����
                if(ret==-1)
                    return -1;
//                res = theHosDB.theVisitDB.updateStatusStatDxByPatient(VisitStatus.isDropVisit()
//                ,"¡��ԡ������ " + theHO.date_time,p_src.getObjectId());
            }
            else
            {
//                res = theHosDB.theVisitDB.updateStatusStatDxByPatient(VisitStatus.isDropVisit()
//                    ,"¡��ԡ������ " + theHO.date_time,p_src.getObjectId());
                p_src.active = "0";
                p_src.staff_cancel = theHO.theEmployee.getObjectId();
                theHosDB.thePatientDB.updateActiveByPatientID(p_src);
            }
            theHO.thePatient = null;
            // ���ź�����ż��������� update � field patient_id,hn ��� staff_modify ����  sumo 30/08/2549
            Family fm = theHO.theFamily;
            theHO.clearFamily();
            return res;
    }       

    /**
     * �ѧ�ѹ����ѹ����������������ѹ�Ҵ henbe comment
     * @deprecated henbe unused
     * @param theUS
     * @param pt_des
     * @param pt_src
     * @return
     * @throws java.lang.Exception
     */
    public int intMovePatientHistory(UpdateStatus theUS,Patient pt_des, Patient pt_src) throws Exception 
    {
            if(pt_des==null){
                theUS.setStatus(("����բ����ż����»��·ҧ"), UpdateStatus.WARNING);
                return -1;
            }
            if(!pt_src.pid.equals(pt_des.pid) || !pt_src.patient_name.equals(pt_des.patient_name)  || !pt_src.patient_last_name.equals(pt_des.patient_last_name)){
                boolean confirm = theUS.confirmBox(Constant.getTextBundle("�����ż��������ç�Ѻ") +
                        "\n "+Constant.getTextBundle("HN ���·ҧ")+" " +pt_des.hn+ " " + pt_des.patient_name + " " +
                        pt_des.patient_last_name + " "+Constant.getTextBundle("�Ţ�ѵ�")+" " + pt_des.pid +
                        "\n"+Constant.getTextBundle("HN ���")+" " +pt_src.hn+ " " + pt_src.patient_name + " " +
                        pt_src.patient_last_name + " "+Constant.getTextBundle("�Ţ�ѵ�")+" " + pt_src.pid, UpdateStatus.WARNING);
                if(!confirm)
                    return -1;
            }
            int ret = 0;
            ret += theHosDB.theVisitDB.updatePatientByPatient(pt_des.getObjectId(),pt_src.getObjectId());
//            ret += theHosDB.theDrugAllergyDB.updatePatientByPatient(pt_des.getObjectId(),pt_src.getObjectId());
//            ret += theHosDB.theAppointmentDB.updatePatientByPatient(pt_des.getObjectId(),pt_src.getObjectId());
//            ret += theHosDB.theAccidentDB.updatePatientByPatient(pt_des.getObjectId(),pt_src.getObjectId());
//            ret += theHosDB.thePatientPaymentDB.updatePatientByPatient(pt_des.getObjectId(),pt_src.getObjectId());
//            ret += theHosDB.thePatientXNDB.updatePatientByPatient(pt_des.getObjectId(),pt_src.getObjectId());
            pt_src.active = "0";
            pt_src.staff_cancel = theHO.theEmployee.getObjectId();
            pt_src.p_type = "¡��ԡ ���»���ѵ���ѧ HN " + pt_des.hn;
            ret += theHosDB.thePatientDB.updateActiveByPatientID(pt_src);
            return ret;
    }
    /**
     * 
     * @param p 
     * @param age 
     * @throws java.lang.Exception 
     * @return 
     */
    public int intCheckPatient(Patient patient,String age) throws Exception
    {
        Constant.println("if(p.getObjectId()!=null){" + patient.getObjectId());
        String date_time = theHO.date_time;
        if(patient==null){
            theUS.setStatus(("����բ����ż����·���ͧ��úѹ�֡"),UpdateStatus.WARNING);
            return 1;
        }
        if(patient.active.equals("0")){
            theUS.setStatus(("������¡��ԡ�����ҹ�����������ö��䢢��������ա"),UpdateStatus.WARNING);
            return 10;
        }
        patient.patient_name = patient.patient_name.trim();
        patient.patient_last_name = patient.patient_last_name.trim();
        if(patient.patient_name.equals("") || patient.patient_last_name.equals("")){
            theUS.setStatus(("�ѧ������͡ ���� ���� ���ʡ�� �ͧ������"),UpdateStatus.WARNING);
            return 2;
        }
        if(!theLO.theOption.auto_add_prefix.equals("1") && patient.f_prefix_id.startsWith("add:")){
            theUS.setStatus(("��س����͡�ӹ�˹�Ҫ��ͷ����������ҹ��"),UpdateStatus.WARNING);
            return 3;
        }        
        ////////////////////////////////////////////////////////////////////////////
        //��Ǩ�ͺ�ѹ�Դ        
        if(patient.patient_birthday_true.equals(Active.isEnable())){
            if(patient.patient_birthday.equals("")){
                theUS.setStatus(("�ѹ�Դ��ԧ��ͧ����繤����ҧ"),UpdateStatus.WARNING);
                return 5;
            }
//            if(p.patient_birthday.length()>0 && p.patient_birthday.length()!=10) {
//                theUS.setStatus(Constant.getTextBundle("��سҡ�͡�ѹ�Դ���١�ٻẺ ��/��/����"),UpdateStatus.WARNING);
//                return 5;
//            }
            if(DateUtil.countDateDiff(patient.patient_birthday,date_time) > 0 ) {
                theUS.setStatus(("��س��к��ѹ�Դ���ѹ�ʹյ"),UpdateStatus.WARNING);
                return 5; 
            }
        }
        if(age!=null){
            if(patient.patient_birthday_true.equals(Active.isDisable()) && age.equals("")){
                theUS.setStatus(("��سҡ�͡���آͧ�������»���ҳ"),UpdateStatus.WARNING);
                return 9;
            }
            try{
                int age_i = Integer.parseInt(age);
                if(age_i > 150){
                    theUS.setStatus(("��سҵ�Ǩ�ͺ�ѹ�Դ ������آͧ�������ա����"),UpdateStatus.WARNING);
                    return 9;
                }
            }
            catch(Exception e){
                Constant.println(e.getMessage());
            }
        }
        ////////////////////////////////////////////////////////////////////////////
        //��Ǩ�ͺ�Ţ�ѵû�ЪҪ�
        boolean result4 = checkID(patient.father_pid);
        if(result4==false && !patient.father_pid.equals("")){
            theUS.setStatus(("�����Ţ�ѵû�ЪҪ��ͧ�Դ��ѧ��͡���ú"),UpdateStatus.WARNING);
            return 8;
        }
        boolean result5 = checkID(patient.mother_pid);
        if(result5==false && !patient.mother_pid.equals("")){
            theUS.setStatus(("�����Ţ�ѵû�ЪҪ��ͧ��ô��ѧ��͡���ú"),UpdateStatus.WARNING);
            return 8;
        }
        boolean result6 = checkID(patient.couple_id);
        if(result6==false && !patient.couple_id.equals("")){
            theUS.setStatus(("�����Ţ�ѵû�ЪҪ��ͧ��������ѧ��͡���ú"),UpdateStatus.WARNING);
            return 8;
        }
        boolean result2 = checkID(patient.pid);
        if(result2==false && !patient.pid.equals("")) {
            theUS.setStatus(("�����Ţ�ѵû�ЪҪ��ѧ��͡���ú"),UpdateStatus.WARNING);
            return 3;
        }
        if(!patient.pid.equals(""))
        {
            Vector<Patient> pidv = theHosDB.thePatientDB.selectByPID(patient.pid);
            if (!pidv.isEmpty()) {
                String strHn = "";
                for (Patient pat : pidv) {
                    if (!patient.hn.equals(pat.hn)) {
                        strHn += pat.hn + ", ";
                    }
                }
                if(!strHn.isEmpty()) {
                    theUS.setStatus(("�����Ţ�ѵû�ЪҪ���ӡѺ������ HN : ") + strHn.substring(0, strHn.lastIndexOf(",")), UpdateStatus.WARNING);
                    return 3;
                }
            }
//            Vector pidv = theHosDB.theFamilyDB.selectByPid(patient.pid,"1");
//            String hn_pid_many = "";
//            for(int i=0,size=pidv.size();i<size;i++){
//                Family pt_pidv = (Family)pidv.get(i);
//                if(!pt_pidv.getObjectId().equals(patient.family_id))
//                    hn_pid_many = hn_pid_many + " "+ pt_pidv.hn_hcis;
//            }
//            //���Ţ�ѵû�ЪҪ� ����ѧ����բ����ż����¤���� ��ͧ���Ţ�ѵû�ЪҪ�
//            if(patient.family_id==null && !pidv.isEmpty()){
//                theUS.setStatus(("�����Ţ�ѵû�ЪҪ���ӡѺ������ ID:")+hn_pid_many,UpdateStatus.WARNING);
//                return 3;
//            }
//            //���Ţ�ѵû�ЪҪ� �ͧ��������ҷ���ӡѹ�ҡ���� 1 ��
//            if(patient.family_id!=null && pidv.size()>1){
//                theUS.setStatus(("�����Ţ�ѵû�ЪҪ���ӡѺ������ ID:")+hn_pid_many,UpdateStatus.WARNING);
//                return 3;
//            }
//            //���Ţ�ѵû�ЪҪ� �ͧ����������Ţ�����������
//            if(patient.family_id!=null && !pidv.isEmpty()){
//                Family pt_pidv = (Family)pidv.get(0);
//                if(!pt_pidv.getObjectId().equals(patient.family_id)){
//                    theUS.setStatus(("�����Ţ�ѵû�ЪҪ����"),UpdateStatus.WARNING);
//                    return 3;
//                }
//            }
        }
        
        int result1 = Constant.isCorrectPID(patient.pid);
        if(patient.pid.length()==13 && result1 != 1){ //incorrect pid standard
            if(!theUS.confirmBox(Constant.getTextBundle("�����Ţ�ѵû�ЪҪ����١��ѡࡳ�� �׹�ѹ��úѹ�֡")
                , UpdateStatus.WARNING)){
                theUS.setStatus(("�������ѧ���١�ѹ�֡"),UpdateStatus.WARNING);
                return 4;
            }
        }
        ////////////////////////////////////////////////////////////////////////////
        //��Ǩ�ͺ�����Ţ xn
        patient.xn = patient.xn.trim();
        if(!patient.xn.equals("")){
            Vector vpxn = theHosDB.thePatientDB.selectByXN(patient.xn);
            String hn_xn = "";
            boolean already = false;
            for(int i=0;i<vpxn.size();i++){
                Patient pt = (Patient)vpxn.get(i);
                if(!pt.getObjectId().equals(patient.getObjectId())) {
                    already = true;
                    hn_xn += pt.hn + " ";
                }
            }
            if(already){
                theUS.setStatus(Constant.getTextBundle("�������Ţ XN ����ӡѺ������ HN") + " " +
                        hn_xn + " " + ("��س��駼������к�"),UpdateStatus.WARNING);
                return 6;
            }
        }
        Prefix pfx = theLookupControl.readPrefixById(patient.f_prefix_id);
        if(pfx!=null && !patient.f_sex_id.equals(pfx.sex) && pfx.tlock.equals(Active.isEnable())){
            theUS.setStatus(("�ӹ�˹���������ѹ��Ѻ�ȷ�����͡"),UpdateStatus.WARNING);
            return 8;
        }
        //////////////////////////////////////////////////////////////////////////
        if(patient.occupation_id.trim().equals("")){
            theUS.setStatus(("��س��к��Ҫվ"),UpdateStatus.WARNING);
            return 10;
        }
        if(!warningPregnant(patient.f_sex_id,age,patient.patient_birthday))
            return 11;

        String db_year = theHosDB.theVisitYearDB.selectCurrenctYear();
        if(patient.hn.equals("") && !db_year.equals(theHO.date_time.substring(2,4)))  {
            if(!theUS.confirmBox(Constant.getTextBundle("���Ţ�բͧ HN ����繻Ѩ�غѹ��á����� ResetYear �ҡ˹�Ҩ͡�˹��Ţ�ӴѺ")
                    + " " +      Constant.getTextBundle("�׹�ѹ�����ҹ���"),UpdateStatus.WARNING))
                return 12;
        }
        //////////////////////////////////////////////////////////////////////////
        if(patient.hn.equals("")){
            patient.hn = theHosDB.theSequenceDataDB.updateSequence("hn",true);
        }
        Patient p_check = theHosDB.thePatientDB.selectByHn(patient.hn);
        if(p_check!=null){
            if(patient.getObjectId()!=null && !patient.getObjectId().equals(p_check.getObjectId())){
                    theUS.setStatus(Constant.getTextBundle("�������Ţ HN") +" "+ patient.hn +" " +
                            Constant.getTextBundle("��س��駼������к� �������Ţ HN ����ش"),UpdateStatus.WARNING);
                    return 9;
            }
            if(patient.getObjectId()==null){
                Constant.println("if(patient.getObjectId()==null){");
                theUS.setStatus(Constant.getTextBundle("�������Ţ HN") +" "+ patient.hn +" " +
                            Constant.getTextBundle("��س��駼������к� �������Ţ HN ����ش"),UpdateStatus.WARNING);
                return 9;
            }
        }
        //////////////////////////////////////////////////////////////////////////
        return 0;
    }
    /**
     * �ѹ�֡�����ż�����
     * @param p 
     * @param age 
     * @return
     * @deprecated henbe unused
     */
    public int savePatient(Patient patient,String age)
    {
        return savePatient(patient,age,null, null);
    }
 
    /**
     * 
     * 
     * @see ������պ�ҹ�������������ա��������ҹ 0 ����� ��������ҹ 0 ����ա����ҧ�����ҹ 0 ���������
     * @auhor henbe
     * @param village_id 
     * @param moo 
     * @param tambon 
     * @throws java.lang.Exception 
     * @return 
     */
    public Village intReadVillage(String village_id,String moo,String tambon) throws Exception
    {
        Village vill = null;
        if(theHO.theVillage!=null
        && theHO.theVillage.getObjectId().equals(village_id)
        && theHO.theVillage.village_moo.equals(moo)
        && theHO.theVillage.village_tambon.equals(tambon)){
            vill = theHO.theVillage;
        }
        if(vill==null && village_id!=null){
            vill = theHosDB.theVillageDB.selectByPK(village_id);
        }
        if(vill==null && moo!=null && tambon!=null){
            vill = theHosDB.theVillageDB.selectByNo(moo,tambon);
        }
        if(vill==null){
            vill = theHosDB.theVillageDB.selectMoo0();
        }
        if(vill==null){
            vill = theHO.initVillage("0");
            theHosDB.theVillageDB.insert(vill);
        }
        theHO.theVillage = vill;
        return vill;
    }
    /**
     * 
     * @param home_id 
     * @param number 
     * @param vill 
     * @throws java.lang.Exception 
     * @return 
     */
    public Home intReadHome(String home_id,String number,Village vill) throws Exception
    {
        Home home = theHosDB.theHomeDB.selectByPK(home_id);
        if(home!=null)
            return home;
        Village vil = this.intReadVillage(null,"0",theHO.theSite.tambon);
        home = theHosDB.theHomeDB.selectByNo("0",vil.getObjectId());
        if(home!=null)
            return home;
        home = theHO.initHome("0",vill);
        theHosDB.theHomeDB.insert(home);
        return home;
    }
    /**
     * not@deprecated henbe ���͡��Ъҡ��Թ 1 ���Ҩ����褹�����ҵ�ͧ��á���
     * @param family_id 
     * @param pid 
     * @param fname 
     * @param lname 
     * @param home 
     * @param read 
     * @throws java.lang.Exception 
     * @return 
     * @deprecated used intReadFamilySuit instead
     */
    public Family intReadFamily(String family_id,String pid
            ,String fname,String lname,Home home,boolean read) throws Exception
    {
        theLookupControl.intReadDateTime();
        Family fm = null;
        if(theHO.theFamily!=null
        && theHO.theFamily.patient_name.equals(fname)
        && theHO.theFamily.patient_last_name.equals(lname) 
        && theHO.theFamily.pid.equals(pid)){
            fm = theHO.theFamily;
        }
        if(fm==null && family_id!=null){
            fm = theHosDB.theFamilyDB.selectByPK(family_id);
        }
        if(fm==null && pid!=null && pid.length()==13){
            fm = theHosDB.theFamilyDB.selectByPid(pid);
        }
        if(fm==null && fname!=null && lname!=null){
            Vector v = theHosDB.theFamilyDB.queryByFLName(fname,lname);
            if(!v.isEmpty())
                fm = (Family)v.get(0);
        }
        /////////////////////////////////////////////////////////////////////////
        return fm;
    }
  
    /**
     * 
     * 
     * @return result of savePatient
     * @see : �ѹ�֡������ Pattern �ѧ���յ�ͧ��Ѻ��ا����ҡ���ҹ���ѧ�����ҡ�ҡ��� Henbe call him self
     * @Author : sumo
     * @date : 28/08/2549
     * @param p 
     * @param age 
     * @param relation 
     * @param vNCD 
     */
    public int savePatient(Patient patient,String age,String relation,Vector vNCD)
    {
        Constant.println(UseCase.UCID_savePatient);
        String objectid =   null;
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
            int ret = intCheckPatient(patient, age);
            if(ret!=0)
                return ret;
            ret = intSavePatient(patient,date_time,patient.relation,false,age);
            if(ret!=0)
                return ret;
            
            intSaveNCD(patient,vNCD);
            //�ҡ����������㹡�кǹ��èе�ͧ��䢪���㹤�Ǵ���
            if(theHO.theVisit!=null
              && !theHO.theVisit.isDischargeDoctor()){
                Visit visit = theHO.theVisit;
                visit.patient_age = DateUtil.calculateAge(patient.patient_birthday,theHO.date_time);
                theHosDB.theVisitDB.update(theHO.theVisit);
                //////////////////////////////////////////////////////
                if(theHO.theListTransfer!=null) {
                    HosObject.updateListTransfer(patient,theHO.theListTransfer);
                    theHosDB.theQueueTransferDB.update(theHO.theListTransfer);
                }
            }
            objectid = patient.getObjectId();
            theHS.thePatientSubject.notifySavePatient(Constant.getTextBundle("��úѹ�֡�����ż������������"),UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_savePatient,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_savePatient,objectid,null,UpdateStatus.COMPLETE);
            return 0;
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_savePatient,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_savePatient,objectid,ex,UpdateStatus.ERROR);
            return -1;
        }
        finally {
            theConnectionInf.close();
        }
    }
    /**
     * 
     * 
     * @see : ��÷ӧҹ���¢ͧ �ѧ�ѹ�ѹ�֡�����ż����� ���ǹ�ҹ PCU
     * @deprecated �����ҹ������ź���ҧ����
     * @Author : henbe
     * @date : 11/02/2549
     * @param p 
     * @param date_time 
     * @throws java.lang.Exception 
     * @return 
     */

    protected int intSavePatientPCU(Patient patient,String date_time) throws Exception
    {
        ///////////////////////////////////////////////////////////////////////////        
        //����繤�������ͧ��Ǩ�ͺ��ҹ��������˹ �¤������ҹ ����͡��� ����͡����ҧ
        //������ͧ set�����ҡѺ theHO ���� sumo 22/04/2549
        //���繵�ͧ ����� Null ��������������ͧ��Ǩ�ͺ��Һ�ҹ��������͡�������¹�����������
        Village vill = null;
        Home home = null;
        //�͡ࢵ�Ӻ��������͡��ҹ�� ����¹
        //����������ҹ����Ѻ�Դ�ͺ�ͧ pcu ������ҡ�����Ҩ���褹�еӺš��� �ѧ��鹨֧��ͧ�鹨ҡ�����ҹ᷹
        //�óռ������������ѧ����繻�Ъҡè��ѧ����պ�ҹ㹰ҹ������
        boolean init_home = false;
        if(vill==null || home==null)
        {
            //��Ǩ�ͺ�ҡ�����ҹ�ͧ���������������������ҹ����Ѻ�Դ�ͺ��������
            init_home = true;
            if(vill==null)
            {   vill = theHosDB.theVillageDB.selectByNo(patient.village,patient.tambon);
            }
            if(vill==null || patient.house.trim().equals(""))
            {   vill = theHosDB.theVillageDB.selectMoo0();
                if(vill != null) home = theHosDB.theHomeDB.selectByNo("0",vill.getObjectId());
            }
            if(vill!=null)
            {
                //��Ǩ�ͺ��ҹ������ҹ���������������Ҷ������ա�����ҧ���鹷�
                if(home==null)
                    home = theHosDB.theHomeDB.selectByNo(patient.house.trim(),vill.getObjectId());
                if(home==null)
                    home = HosObject.getHome(patient);
                //////////////////////////////////////////
                if(home.getObjectId()==null)
                {
                    home.village_id = vill.getObjectId();
                    home.home_staff_record = theHO.theEmployee.getObjectId();
                    home.home_record_date_time = date_time;
                    theHosDB.theHomeDB.insert(home);    
                } 
            }
        }
        ///////////////////////////////////////////////////////////////////////////        
        //��Ǩ�ͺ��Ҽ����¤�����繻�Ъҡ����������ѧ ����ѧ��ѹ�֡ ������ǡ稺
        //������ͧ set�����ҡѺ theHO ���� ���Դ��ͼԴ��Ҵ�ó����������������ѹ�� family_id ��ӡѺ����͹˹��
        // sumo 22/04/2549
        //��������Ǿ����͡��������������ͨ�������� Null �� �� 24/04/2549
        //���һ�Ъҡ� 㹡óշ���ѹ�����Դ�Ҵ���  ��к�ҹ��ͧ�դ�� ��������ҹ��ͧ�ա�͹�֧�кѹ�֡��Ъҡ�
        Family fm = theHO.theFamily;        
        if(fm==null && home!=null)
        {
            if(!patient.pid.trim().equals(""))
                fm = theHosDB.theFamilyDB.selectByPid(patient.pid);
            if(fm==null){
                Vector v = theHosDB.theFamilyDB.selectByFnameLnameHome(patient.patient_name,patient.patient_last_name,home.getObjectId());
                if(!v.isEmpty())
                    fm = (Family)v.get(0);
            }
            //��Ҥ�����ͨзӡ�����ҧ����ҡ���������Ҥ������ǡ�зӡ�� update family ������
            if(fm==null)
                fm = HosObject.getFamily(patient);
            else
                fm = HosObject.getFamily(patient,fm);
        }
        //////////////////////////////////////////
        if(fm!=null && init_home)
        {
            fm.home_id = home.getObjectId();
            if(!patient.pid.equals(""))
                fm.pid = patient.pid;
            fm.record_date_time = date_time;
            fm.staff_record =  theHO.theEmployee.getObjectId();    
            if(fm.hn_hcis.equals(""))
                fm.hn_hcis = theHosDB.theSequenceDataDB.updateSequence("hn_hcis",true);
        }
        
        if(fm!=null)
        {
            if(fm.getObjectId()==null)
            {
                theHosDB.theFamilyDB.insert(fm);
            }
            else
            {
                // ����ա�� Update �����ż����¡��ͧ Update � Family ����  sumo 29/08/2549 
                fm = HosObject.getFamily(patient,fm);
                fm.modify_date_time = date_time;
                fm.staff_modify =  theHO.theEmployee.getObjectId(); 
                theHosDB.theFamilyDB.update(fm);
            }
            if(patient.family_id.equals(""))
            {
                patient.family_id = fm.getObjectId();
                patient.has_health_home = Active.isEnable();
                theHosDB.thePatientDB.updateFamilyHome(patient);
                theHosDB.theChronicDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
                theHosDB.theSurveilDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
                theHosDB.theDeathDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
                theHosDB.thePatientPaymentDB.updateFidByPtid(fm.getObjectId(),patient.getObjectId());
            }
        }
        ////////////////////////////////////////////////////////////////////
        //�礪��� ���ʡ�� ���� ��ҹ �ҡ��ӡѹ������ͧ�ѹ�֡
        theHO.theFamily = fm;
        theHO.theVillage = vill;
        theHO.theHome = home;       
        ////////////////////////////////////////////////////////////////////
        return 0;
    }
    /**
     * 
     * 
     * @see : �ѹ�֡��������ӹ�˹�Ҫ���
     * @Author : henbe
     * @date : 5/9/2549
     * @param prefix_id 
     * @throws java.lang.Exception 
     * @return 
     */
   public String intSaveNewPrefix(String prefix_id) throws Exception 
   {
        if(prefix_id.startsWith("add:")){
            String pff = prefix_id.substring(4);
            //�˵ط���ͧ�������� 000 ��ҧ˹��������ҵ͹�ѹ�֡ŧ�ҹ�����������������������
            //����ѡ�������·���鹵鹵�����ǡѹ���� primary key �ç�ѹ insert ��������
            Vector prefix_v = theHosDB.thePrefixDB.selectAll();
            Prefix2 pf = new Prefix2("000:" + prefix_v.size() + pff,pff);
            theHosDB.thePrefixDB.insert(pf);
            theLO.thePrefix = theHosDB.thePrefixDB.selectAll(Active.isEnable());
            return pf.getObjectId();
        }
        return prefix_id;
   }   
    /**
     * 
     * 
     * @see : �ѹ�֡��������ӹ�˹�Ҫ���
     * @Author : henbe
     * @date : 5/9/2549
     * @param relation 
     * @param pt_relation 
     * @throws java.lang.Exception 
     * @return 
     */
   public String intSaveRelation(String relation,String pt_relation) throws Exception 
   {
        ////////////////////////////////////////////////////////////////////////////
        //������ʤ�������ѹ���繤����ҧ�ʴ�����ա�úѹ�ա�����Ź͡�ҡ�����㹰ҹ������
        if( relation!=null && !relation.equals("") && pt_relation.equals(""))
        {   
            Relation theRelation = theHosDB.theRelationDB.selectByName(relation);
            if(theRelation==null) 
            {//�����������ѹ�֡����
                theRelation = new Relation();
                theRelation.description = relation;
                theLookupControl.intSaveRelation(theRelation); 
            }
            return theRelation.getObjectId();
        }
        return pt_relation;
   }
   /**
    *@deprecated henbe unused
    **/
   
    public int intSavePatient(Family fm,Patient patient,String date_time,String relation) throws Exception
    {
        return intSavePatient(patient,date_time,patient.relation,true,null);
    }
   /**
    *@deprecated henbe unused
    **/
    public int intSavePatient(Patient patient,String date_time,String relation) throws Exception
    {
        return intSavePatient(patient,date_time,relation,true,null);
    }
    /**
     * @deprecated henbe unused
     * @date : 12/09/2549
     * @param fm 
     * @param patient
     * @param date_time 
     * @param relation 
     * @throws java.lang.Exception 
     * @return 
     * �ѧ�ѹ����繡�úѹ�֡�����ż�������������е�ͧ�礴�����Ҽ����¤�����繻�Ъҡ����������ѧ
     * �ҡ�����ǡ���� update ��Ъҡ����ҧ����
     * �ҡ�ѧ����繡���� ���ҧ��Ъҡâ���� ��Ǩ�ͺ��ҹ ����Һ�ҹ����������º����
     * �ѹ�֡�����Ť���������
     */
    public int intSavePatient(Patient p,String date_time,String relation,boolean merge_fmpt) throws Exception
    {
        return intSavePatient(p,date_time,relation,merge_fmpt,null);
    }
    /**
     * @date : 12/09/2549
     * @param fm 
     * @param p 
     * @param date_time 
     * @param relation 
     * @throws java.lang.Exception 
     * @return 
     * �ѧ�ѹ����繡�úѹ�֡�����ż�������������е�ͧ�礴�����Ҽ����¤�����繻�Ъҡ����������ѧ
     * �ҡ�����ǡ���� update ��Ъҡ����ҧ����
     * �ҡ�ѧ����繡���� ���ҧ��Ъҡâ���� ��Ǩ�ͺ��ҹ ����Һ�ҹ����������º����
     * �ѹ�֡�����Ť���������        //��Ǩ�ͺ��úѹ�֡�ӹ�˹�Ҫ���
        //����������繤����ҧ���ͺ͡������ա�úѹ�֡����������������ǹ����֧�����Ũҡ�ҹ�������������
        //�˵ط���ͧ comment ������ҵ͹�ѹ�֡�������ѹ������¡�ѹ�֡��Ъҡ�������������� intSaveFamily
        //�������¡�ѹ�֡�ӹ�˹�Ҫ����ͧ
     */
    public int intSavePatient(Patient patient,String date_time,String relation
            ,boolean merge_fmpt,String age) throws Exception
    {
        String old_family_id = patient.family_id;
        Home home = intReadHome(patient.home_id,null,null);
        if(patient.patient_birthday_true.equals("0") && age!=null){
            patient.patient_birthday = DateUtil.calBirthdateDB(theHO.date_time,age);
        }
        Family family = patient.getFamily();
        int ret = intUDSaveFamily(family,home,false,theUS);
        if(ret==0)
            return 2;
        /////////////////////////////////////////////////////////////////
        patient.family_id = family.getObjectId();
        patient.update_date_time = date_time;
        patient.staff_modify = theHO.theEmployee.getObjectId();
        patient.relation = intSaveRelation(relation,patient.relation);
        patient.hn = patient.hn.trim();
        patient.village = patient.village.trim();
        
        if(patient.getObjectId()==null) {
            patient.move_in = date_time;
            patient.record_date_time = date_time;
            theHosDB.thePatientDB.insert(patient);
        }
        else{  
            theHosDB.thePatientDB.update(patient);
        }
        //��ѧ�ҡ�ѹ�֡���������Ǩ����Ъҡ���͹
        ////////////////////////////////////////////////////////////////////////////
        if((old_family_id==null || old_family_id.equals(""))
                && family.record_date_time.length()>=10
                && !family.record_date_time.substring(0,10).equals(date_time.substring(0,10)))
        {
            theHosDB.theChronicDB.updateFidByPtid(family.getObjectId(),patient.getObjectId());
            theHosDB.theSurveilDB.updateFidByPtid(family.getObjectId(),patient.getObjectId());
            theHosDB.theDeathDB.updateFidByPtid(family.getObjectId(),patient.getObjectId());
            theHosDB.thePatientPaymentDB.updateFidByPtid(family.getObjectId(),patient.getObjectId());
        }
        //������Ŵ dependency ���ѹ�֡੾�Тͺࢵ�����Ţͧ��������ҹ����ǹ�������Ǣ�ͧ�Ѻ visit �������������ҡ �ѧ�ѹ���
        ////////////////////////////////////////////////////////////////////////////
        theHO.theFamily = family;
        theHO.thePatient = patient;
        theHO.theHome = home;
        return 0;
    }
    /**
     *         ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        //��Ǩ�ͺ��úѹ�֡�ӹ�˹�Ҫ���
        //����������繤����ҧ���ͺ͡������ա�úѹ�֡����������������ǹ����֧�����Ũҡ�ҹ�������������
        //�˵ط���ͧ comment ������ҵ͹�ѹ�֡�������ѹ������¡�ѹ�֡��Ъҡ�������������� intSaveFamily
        //�������¡�ѹ�֡�ӹ�˹�Ҫ����ͧ
     * @param date_time
     * @param relation
     * @param age
     * @return
     * @throws java.lang.Exception
     * @deprecated henbe unused
     */
    public int intSavePatientRoot(String date_time,String relation
           ,String age) throws Exception
    {
        Patient p = theHO.thePatient;
        if(p.patient_birthday_true.equals("0") && age!=null)
            p.patient_birthday = DateUtil.calBirthdateDB(theHO.date_time,age);
        
        p.update_date_time = date_time;
        p.staff_modify = theHO.theEmployee.getObjectId();
        p.relation = intSaveRelation(relation,p.relation);
        p.hn = p.hn.trim();
        p.village = p.village.trim();
        theHosDB.thePatientDB.updateParent(p);
        return 0;
    }
    /**
     * ��ѭ������ͧ��úѹ�֡���������Ǩ�������ͧ�������� hn �ͧ����
     * @param fm
     * @param date_time
     * @return
     * @throws Exception
     * @deprecated henbe unused
     */
    public int intSavePatient(Family fm,String date_time) throws Exception
    {
        return intSavePatient(fm,date_time,true);
    }
    public Patient intUDSavePatient(Family fm,String date_time,boolean run_hn) throws Exception
    {
        Patient p = new Patient();
        if(p==null){
            theUS.setStatus("��س����͡��Ъҡ÷���������ԧ",UpdateStatus.WARNING);
            return null;
        }
        p.setFamily(fm,true);
        if(run_hn){
            p.hn = theHosDB.theSequenceDataDB.updateSequence("hn",true);
        }
        p.update_date_time = date_time;
        p.staff_modify = theHO.theEmployee.getObjectId();
        p.relation = intSaveRelation("",p.relation);
        p.village = p.village.trim();
        p.move_in = date_time;
        p.record_date_time = date_time;
        p.staff_record = theHO.theEmployee.getObjectId();
        theHosDB.thePatientDB.insert(p);
        ////////////////////////////////////////////////////////////////////////////
        if(fm.record_date_time.length()>=10
        && !fm.record_date_time.substring(0,10).equals(date_time.substring(0,10)))
        {
            theHosDB.theChronicDB.updateFidByPtid(fm.getObjectId(),p.getObjectId());
            theHosDB.theSurveilDB.updateFidByPtid(fm.getObjectId(),p.getObjectId());
            theHosDB.theDeathDB.updateFidByPtid(fm.getObjectId(),p.getObjectId());
            theHosDB.thePatientPaymentDB.updateFidByPtid(fm.getObjectId(),p.getObjectId());
        }
        //������Ŵ dependency ���ѹ�֡੾�Тͺࢵ�����Ţͧ��������ҹ����ǹ�������Ǣ�ͧ�Ѻ visit �������������ҡ �ѧ�ѹ���
        ////////////////////////////////////////////////////////////////////////////
        return p;
    }
    /**
     * ��ѭ������ͧ��úѹ�֡���������Ǩ�������ͧ�������� hn �ͧ����
     * @param fm
     * @param date_time
     * @return
     * @throws Exception
     */
    public int intSavePatient(Family fm,String date_time,boolean run_hn) throws Exception
    {
        if(theHO.thePatient!=null && theHO.thePatient.family_id.equals(fm.getObjectId())){
            theUS.setStatus(("��Ъҡ��繼�������������"),UpdateStatus.WARNING);
            return 0;
        }
        Patient p = intUDSavePatient(fm,date_time,run_hn);
        theHO.setPatient(p);
        theHO.thePatient = p;
        return 0;
    }
    public void checkFamily()
    {
        ResultSet rs;
        try
        {
            if(this.theHO.theFamily.getObjectId().equals(theHO.thePatient.getObjectId()))
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
                    readPatientByHn2(theHO.thePatient.hn);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
     /**
     * hosv4
      * ���͡���������� HN
     * ����ͤ����� hn ����ǡѹ�ҡ���� 1 �����ʴ�˹�ҨͶ����Ҩ����͡���˹
     * 
     * @return int �ѧ��������ö�ӧҹ������� = 1 �ѧ���蹷ӧҹ�������� = 0
     * @date 16/08/2549
     * @param id Hospital Number of Patient HN
     */
    public int readPatientByHn(String id)
    {
        Constant.println("public int readPatientByHn(String id)");
        Constant.println(UseCase.UCID_readPatientByHn);
        String objectid =   null;
        if(id.trim().equals("")) {
            theUS.setStatus(("��سҡ�͡�����Ţ HN"),UpdateStatus.WARNING);
            return 0;
        }
        
        try{
            theConnectionInf.open();
            ///unlock old visit//////////////////////////////////
            theVisitControl.intUnlockVisit(theHO.theVisit);
            theHO.clearFamily();
            String str = theLookupControl.getNormalTextHN(id);
            ////////////////////////////////////////////////////////////////////////
            // �礨ӹǹ�����·��ç�Ѻ HN ������ҡ���� 1 ������觤�ҡ�Ѻ���Ф�����ª��ͼ�����������͡ sumo 18/7/2549
            Vector vPatient = theHosDB.thePatientDB.selectLikeHN("%" + str,"");
            int total_hn = vPatient.size();
            if(total_hn>1){
                return total_hn;
            }
            if(vPatient.isEmpty()){
                theUS.setStatus(("��辺�����ż�����"),UpdateStatus.WARNING);
                return 0;
            }
            ////////////////////////////////////////////////////////////////////////
            Patient pt = (Patient)vPatient.get(0);
            // SOmprasong 120810 commentu use same search dialog
//            intReadFPV(pt.family_id,false);
            if (pt.getObjectId() != null) {
                readPatientByPatientID(pt.getObjectId());
            } else {
                readFamilyByFamilyId(pt.family_id);
            }
            checkFamily();
//     henbe comment for wait new place to go
//��������������㹡óշ����������ؤú 15 �� ��������Ѿഷ�����Ź�˹�Ҫ������ ������Ѿഷ��ª���㹤��
//            String date_time = theHO.date_time;
//            String age = DateUtil.calculateAge(theHO.thePatient.patient_birthday,date_time);
//            if((!age.equals("")) && Integer.parseInt(age) == 15)
//            {
//                if(theHO.thePatient.prefix_id.equals("001"))
//                {
//                    theHO.thePatient.prefix_id = "003";
//                }
//                else if(theHO.thePatient.prefix_id.equals("002"))
//                {
//                    theHO.thePatient.prefix_id = "004";
//                }
//                intSavePatient(theHO.thePatient,date_time,theHO.thePatient.relation,true,age);
//                if(theHO.theListTransfer!=null)
//                {
//                    HosObject.updateListTransfer(theHO.thePatient,theHO.theListTransfer);
//                    theHosDB.theQueueTransferDB.update(theHO.theListTransfer);
//                }
//            }
            if(pt != null)
                objectid = pt.getObjectId();
            theSystemControl.setStatus(UseCase.TH_readPatientByHn,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_readPatientByHn,objectid,null,UpdateStatus.COMPLETE);
            return 1;
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_readPatientByHn,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_readPatientByHn,objectid,ex,UpdateStatus.ERROR);
            return 0;
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     * hosv4
      * ���͡���������� HN
     * ����ͤ����� hn ����ǡѹ�ҡ���� 1 �����ʴ�˹�ҨͶ����Ҩ����͡���˹
     *
     * @return int �ѧ��������ö�ӧҹ������� = 1 �ѧ���蹷ӧҹ�������� = 0
     * @date 16/08/2549
     * @param id Hospital Number of Patient HN
     */
    public int readPatientByHn2(String id)
    {
        Constant.println("public int readPatientByHn(String id)");
        Constant.println(UseCase.UCID_readPatientByHn);
        String objectid =   null;
        if(id.trim().equals("")) {
            theUS.setStatus(("��سҡ�͡�����Ţ HN"),UpdateStatus.WARNING);
            return 0;
        }

        try{
            theConnectionInf.open();
            ///unlock old visit//////////////////////////////////
            theVisitControl.intUnlockVisit(theHO.theVisit);
            theHO.clearFamily();
            String str = theLookupControl.getNormalTextHN(id);
            ////////////////////////////////////////////////////////////////////////
            // �礨ӹǹ�����·��ç�Ѻ HN ������ҡ���� 1 ������觤�ҡ�Ѻ���Ф�����ª��ͼ�����������͡ sumo 18/7/2549
            Vector vPatient = theHosDB.thePatientDB.selectLikeHN("%" + str,"");
            int total_hn = vPatient.size();
            if(total_hn>1){
                return total_hn;
            }
            if(vPatient.isEmpty()){
                theUS.setStatus(("��辺�����ż�����"),UpdateStatus.WARNING);
                return 0;
            }
            ////////////////////////////////////////////////////////////////////////
            Patient pt = (Patient)vPatient.get(0);
            // SOmprasong 120810 commentu use same search dialog
//            intReadFPV(pt.family_id,false);
            if (pt.getObjectId() != null) {
                readPatientByPatientID(pt.getObjectId());
            } else {
                readFamilyByFamilyId(pt.family_id);
            }
//     henbe comment for wait new place to go
//��������������㹡óշ����������ؤú 15 �� ��������Ѿഷ�����Ź�˹�Ҫ������ ������Ѿഷ��ª���㹤��
//            String date_time = theHO.date_time;
//            String age = DateUtil.calculateAge(theHO.thePatient.patient_birthday,date_time);
//            if((!age.equals("")) && Integer.parseInt(age) == 15)
//            {
//                if(theHO.thePatient.prefix_id.equals("001"))
//                {
//                    theHO.thePatient.prefix_id = "003";
//                }
//                else if(theHO.thePatient.prefix_id.equals("002"))
//                {
//                    theHO.thePatient.prefix_id = "004";
//                }
//                intSavePatient(theHO.thePatient,date_time,theHO.thePatient.relation,true,age);
//                if(theHO.theListTransfer!=null)
//                {
//                    HosObject.updateListTransfer(theHO.thePatient,theHO.theListTransfer);
//                    theHosDB.theQueueTransferDB.update(theHO.theListTransfer);
//                }
//            }
            if(pt != null)
                objectid = pt.getObjectId();
            theSystemControl.setStatus(UseCase.TH_readPatientByHn,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_readPatientByHn,objectid,null,UpdateStatus.COMPLETE);
            return 1;
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_readPatientByHn,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_readPatientByHn,objectid,ex,UpdateStatus.ERROR);
            return 0;
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     * 
     * @param patient_id 
     * @return 
     * @see �����ҹ������ visit ���ʴ�
     * @deprecated henbeunused bad pattern use intReadVisitRet instead
     * @throws java.lang.Exception 
     */
    protected Visit intReadVisit(String patient_id)throws Exception
    {
        return intReadVisitRet(patient_id);
    }
    protected Visit intReadVisitRet(String patient_id)throws Exception
    {
        Visit theVisit = null;
        ////�ǪʶԵԨФ���¡�÷���˹��·ҧ����Թ����������͹////////////////////////////
        if(theHO.theEmployee.authentication_id.equals(Authentication.STAT)){
            theVisit = theHosDB.theVisitDB.selectStatByPtid(patient_id,VisitStatus.isInStat());
        }
        ////�ź�Ф���¡�÷���ҧ����㹤��������͹///////////////////////////////////
        else if(theHO.theEmployee.authentication_id.equals(Authentication.LAB))
        {
            //����ͼ������ѧ�դ�Ǥ�ҧ���� ��������Ҩ�Ш���кǹ��������
            QueueLab2 ql = theHosDB.theQueueLabDB.selectByPatientID(patient_id);
            if(ql!=null)
                theVisit = theHosDB.theVisitDB.selectByPK(ql.visit_id);
        }
        ////��硫����Ф���¡�÷���ҧ����㹤��������͹///////////////////////////////////
        else if(theHO.theEmployee.authentication_id.equals(Authentication.XRAY))
        {
            //����ͼ������ѧ�դ�Ǥ�ҧ���� ��������Ҩ�Ш���кǹ��������
            QueueXray ql = theHosDB.theQueueXrayDB.selectByPatientID(patient_id);
            if(ql!=null)
                theVisit = theHosDB.theVisitDB.selectByPK(ql.visit_id);
        }
        ////��硫����Ф���¡�÷���ҧ����㹤��������͹///////////////////////////////////
        else if(theHO.theEmployee.authentication_id.equals(Authentication.PHARM))
        {
            QueueDispense2 ql = theHosDB.theQueueDispense2DB.selectByPatientID(patient_id);
            if(ql!=null)
                theVisit = theHosDB.theVisitDB.selectByPK(ql.visit_id);
        }
        ////�������ͨФ鹷������㹡�кǹ��������//////////////////////////////////////
        if(theVisit==null){
            theVisit = theHosDB.theVisitDB.selectByPtid(patient_id);
        }
        return theVisit;
    }
    /**
     * 
     * 
     * @param family_id 
     * @return 
     */
     
    public Family readFamilyByFamilyIdRet(String family_id)
    {
        theConnectionInf.open();
        try{
            return theHosDB.theFamilyDB.selectByPK(family_id);
        }
        catch(Exception e){
            theUS.setStatus(("������¡�٢����Ż�ЪҡüԴ��Ҵ"),UpdateStatus.ERROR);
            e.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     *
     * @param family_id
     */
    public void readFamilySurvey(String family_id)
    {
        Constant.println("public int readFamilyByFamilyId(String family_id)");
        theConnectionInf.open();
        try{
            theVisitControl.intUnlockVisit(theHO.theVisit);
            theHO.clearFamily();
            //��Ǩ�ͺ��һ�Ъҡ��������������Ңͧ���餹���//////////////////////////////////
            intReadFPV(family_id,true);
        }
        catch(Exception e){
            theUS.setStatus(("������¡�٢����Ż�ЪҡüԴ��Ҵ"),UpdateStatus.ERROR);
            e.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     * 
     * @param family_id 
     */
    public void readFamilyByFamilyId(String family_id)
    {
        Constant.println("public int readFamilyByFamilyId(String family_id)");
        Constant.println(UseCase.UCID_readFamilyByFamilyId);
        String objectid =   null;
        objectid = family_id;
        theConnectionInf.open();
        try{
            theVisitControl.intUnlockVisit(theHO.theVisit);
            theHO.clearFamily();
            //��Ǩ�ͺ��һ�Ъҡ��������������Ңͧ���餹���//////////////////////////////////
            intReadFPV(family_id,false);
            theSystemControl.setStatus(UseCase.TH_readFamilyByFamilyId,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_readFamilyByFamilyId,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_readFamilyByFamilyId,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_readFamilyByFamilyId,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    public String getPatientIDByFamilyID(String fid)
    {
        String pid = "";
        String sql = "select t_patient_id from t_patient where t_health_family_id = '"+fid+"'";
        try
        {
            ResultSet rs = theConnectionInf.eQuery(sql);
            if(rs.next())
                pid = rs.getString("t_patient_id");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return pid;
    }
    protected void intReadFPV(String family_id,boolean survey) throws Exception
    {
        String sql = "select t_visit.t_visit_id,t_patient.t_patient_id,t_visit.f_visit_type_id" +
                    " from t_health_family " +
                    " left join t_patient on t_patient.t_health_family_id = t_health_family.t_health_family_id" +
                    " left join t_visit on (t_visit.t_patient_id = t_patient.t_patient_id " +
                    " and t_visit.f_visit_status_id = '1'";
        if(survey)
            sql+=" and t_visit.f_visit_type_id='S')";
        else
            sql+=" and t_visit.f_visit_type_id<>'S')";

        sql+=" where t_health_family.t_health_family_id = '"+family_id+"'";

            ResultSet rs = theConnectionInf.eQuery(sql);
            String visit_id = null;
            String patient_id = null;
            String visit_type = null;
            while(rs.next())
            {
                visit_id = rs.getString(1);
                patient_id = rs.getString(2);
                visit_type = rs.getString(3);
            }
            theLookupControl.intReadDateTime();
            if(visit_id!=null){
                Visit visit = theHosDB.theVisitDB.selectByPK(visit_id);
                Patient pt = theHosDB.thePatientDB.selectByPK(patient_id);

                intReadFamilySuit(pt,null);
                intReadPatientSuit(pt);
                intReadVisitSuit(visit);
                intLockVisit(theHO.date_time);
                theVisitControl.intSaveTransferCatch(theHO.theEmployee, theHO.date_time);
                theHS.theVisitSubject.notifyReadVisit(Constant.getTextBundle("���¡�٢����š���Ѻ��ԡ���������")
                        ,UpdateStatus.COMPLETE);
                return;
            }
            if(patient_id!=null){
                Patient pt = theHosDB.thePatientDB.selectByPK(patient_id);
                intReadFamilySuit(pt,null);
                intReadPatientSuit(pt);
                theHS.thePatientSubject.notifyReadPatient(Constant.getTextBundle(
                        "������¡�٢����ż������������"),UpdateStatus.COMPLETE);
                return;
            }
            if(family_id!=null){
                Family fm = theHosDB.theFamilyDB.selectByPK(family_id);
                if(fm==null){
                    throw new Exception("��辺�����Ż�Ъҡá�سҡ����� Family");
                }
                intReadFamilySuit(fm,null);
                theHS.thePatientSubject.notifyReadPatient(Constant.getTextBundle(
                            "������¡�٢����Ż�Ъҡ��������"),UpdateStatus.COMPLETE);
                return;
            }
    } 
    /**
     * @see : �ѹ�֡�Ѩ�������§ ����ѵԤ�ͺ���� ����ä��Шӵ��
     * @Author : henbe
     * @date : 21/05/2549
     * @param patient_id 
     * @deprecated henbe unused use intReadPatientSuit instead
     */
    public void readPatientHistory(String patient_id)
    {
        theConnectionInf.open();
        try{
            //for nan 050506 by henbe
            theHO.vRiskFactor = theHosDB.theRiskFactorDB.selectByPatientId(patient_id);
            theHO.vFamilyHistory = theHosDB.theFamilyHistoryDB.selectByPatientId(patient_id);
            theHO.vPersonalDisease = theHosDB.thePersonalDiseaseDB.selectByPatientId(patient_id);
            theHO.vPastHistory = theHosDB.thePastHistoryDB.selectByPatientId(patient_id);
            theHO.vDrugAllergy = theHosDB.theDrugAllergyDB.selectByPatientId(patient_id);
            theHS.thePatientSubject.notifyReadPatient("test", UpdateStatus.COMPLETE);
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    /**
     * 
     * 
     * @see : �ѹ�֡�Ѩ�������§ ����ѵԤ�ͺ���� ����ä��Шӵ��
     * @Author : amp
     * @date : 11/02/2549
     * @param past_hx_v 
     * @param family_hx_v 
     * @param person_dss_v 
     * @param risk_factor_v 
     * @param drug_alg_v 
     */
    public boolean savePatientHistory(Vector past_hx_v,Vector family_hx_v
            ,Vector person_dss_v,Vector risk_factor_v,Vector drug_alg_v)
    {
        if(drug_alg_v != null && drug_alg_v.size() > 0)
            Constant.println(UseCase.UCID_savePatientAllergy);
        Constant.println(UseCase.UCID_savePatientHistory);
        String objectid =   null;
        if(this.theHO.theVisit!=null)
            objectid = this.theHO.theVisit.getObjectId();
        //��Ǩ�ͺ���͹�
        if(!checkSavePatientHistory(past_hx_v,family_hx_v,person_dss_v,risk_factor_v,drug_alg_v))
            return false;
       
        theConnectionInf.open();
        try{
            if(theHO.vPastHistory!=null)
            {
                theHO.date_time = theLookupControl.intReadDateTime();
                theHosDB.thePastHistoryDB.deleteByPtid(theHO.thePatient.getObjectId());
                theHO.vPastHistory.removeAllElements();
                
                for(int i=0,size=past_hx_v.size();i<size;i++)
                {
                    PastHistory ph = (PastHistory)past_hx_v.get(i);
                    if(!ph.description.equals(""))
                    {
                        ph.patient_id = theHO.thePatient.getObjectId();
                        ph.staff_record = theHO.theEmployee.getObjectId();
                        ph.record_date_time = theHO.date_time;
                        theHosDB.thePastHistoryDB.insert(ph);
                        theHO.vPastHistory.add(ph);
                    }
                }
            }
            if(theHO.vFamilyHistory!=null)
            {
                theHosDB.theFamilyHistoryDB.deleteByPtid(theHO.thePatient.getObjectId());
                theHO.vFamilyHistory.removeAllElements();
                for(int i=0,size=family_hx_v.size();i<size;i++)
                {
                    FamilyHistory ph = (FamilyHistory)family_hx_v.get(i);
    //                if(!ph.description.equals(""))
    //                {
                        ph.patient_id = theHO.thePatient.getObjectId();
                        ph.staff_record = theHO.theEmployee.getObjectId();
                        ph.record_date_time = theHO.date_time;
                        theHosDB.theFamilyHistoryDB.insert(ph);
                        theHO.vFamilyHistory.add(ph);
    //                }
                }
            }
            if(theHO.vPersonalDisease!=null)
            {
                theHosDB.thePersonalDiseaseDB.deleteByPtid(theHO.thePatient.getObjectId());
                theHO.vPersonalDisease.removeAllElements();
                for(int i=0,size=person_dss_v.size();i<size;i++)
                {
                    PersonalDisease ph = (PersonalDisease)person_dss_v.get(i);
                    if(!ph.description.equals(""))
                    {
                        ph.patient_id = theHO.thePatient.getObjectId();
                        ph.staff_record = theHO.theEmployee.getObjectId();
                        ph.record_date_time = theHO.date_time;
                        theHosDB.thePersonalDiseaseDB.insert(ph);
                        theHO.vPersonalDisease.add(ph);
                    }
                }
            }
            if(theHO.vRiskFactor!=null)
            {
                theHosDB.theRiskFactorDB.deleteByPtid(theHO.thePatient.getObjectId());
                theHO.vRiskFactor.removeAllElements();

                if(risk_factor_v==null)
                    risk_factor_v = new Vector();
                for(int i=0,size=risk_factor_v.size();i<size;i++){
                    RiskFactor ph = (RiskFactor)risk_factor_v.get(i);
    //                if(!ph.description.equals(""))
    //                {
                        ph.patient_id = theHO.thePatient.getObjectId();
                        ph.staff_record = theHO.theEmployee.getObjectId();
                        ph.record_date_time = theHO.date_time;
                        theHosDB.theRiskFactorDB.insert(ph);
                        theHO.vRiskFactor.add(ph);
    //                }
                }
            }
            intSavePatientAllergy(drug_alg_v);
            theHS.thePatientSubject.notifyManageDrugAllergy(Constant.getTextBundle("�ѹ�֡����ѵԼ������������")
                    ,UpdateStatus.COMPLETE);
            if(drug_alg_v != null && drug_alg_v.size() > 0)
            {
                theSystemControl.setStatus(UseCase.TH_savePatientAllergy,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_savePatientAllergy,objectid,null,UpdateStatus.COMPLETE);
            }
            theSystemControl.setStatus(UseCase.TH_savePatientHistory,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_savePatientHistory,objectid,null,UpdateStatus.COMPLETE);

        }
        catch(Exception ex)
        {
            if(drug_alg_v != null && drug_alg_v.size() > 0)
            {
                theSystemControl.setStatus(UseCase.TH_savePatientAllergy,UpdateStatus.ERROR,ex);
                theSystemControl.saveLog(UseCase.UCID_savePatientAllergy,objectid,ex,UpdateStatus.ERROR);
            }
            theSystemControl.setStatus(UseCase.TH_savePatientHistory,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_savePatientHistory,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally {
            theConnectionInf.close();       
        }   
        return true;
    }
    
    /**
     * 
     * 
     * @return : �����
     * @see : ź�����š������׹����� Xray
     * @Author : sumo
     * @date : 20/02/2549
     * @param borrow 
     * @param theUS 
     */
    public boolean deleteBorrowFilmXray(BorrowFilmXray borrow,UpdateStatus theUS)
    {
        Constant.println(UseCase.UCID_deleteBorrowFilmXray);
        String objectid =   null;
        theConnectionInf.open();
        if(borrow == null)
        {
            theUS.setStatus(("��س����͡��¡������׹����� Xray ����ͧ���ź��͹"),UpdateStatus.WARNING);
            return false;
        }
        borrow.borrow_active = "0";
        borrow.borrow_staff_cancel = theHO.theEmployee.getObjectId();
        try {
            borrow.borrow_cancel_date_time = theLookupControl.intReadDateTime();
//            theHosDB.theBorrowFilmXrayDB.delete(borrow);
            theHosDB.theBorrowFilmXrayDB.update(borrow);
            theUS.setStatus(("���ź��¡������׹����� Xray �������"),UpdateStatus.COMPLETE);
            if(borrow != null)
                objectid = borrow.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deleteBorrowFilmXray,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteBorrowFilmXray,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_deleteBorrowFilmXray,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deleteBorrowFilmXray,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     * 
     * 
     * @return : boolean �ͧ��÷ӧҹ㹿ѧ����
     * @see : �ѹ�֡�����š������׹����� Xray
     * @Author : sumo
     * @date : 20/02/2549
     * @param borrow 
     * @param theUS 
     */
    public boolean saveBorrowFilmXray(BorrowFilmXray borrow,UpdateStatus theUS)
    {
        Constant.println("public void saveBorrowFilmXray(BorrowFilmXray borrow,UpdateStatus us)");
        Constant.println(UseCase.UCID_saveBorrowFilmXray);
        String objectid =   null;
        if(("").equals(borrow.patient_hn))
        {
            theUS.setStatus(("��س��к������Ţ HN �ͧ ����� Xray ����ͧ������"),UpdateStatus.WARNING);
            return false;
        }
        if(borrow.borrower_name.trim().equals("") || borrow.borrower_lastname.trim().equals(""))
        {
             theUS.setStatus(("��سҡ�͡����-������ ��͹�ӡ�úѹ�֡"),UpdateStatus.WARNING);
             return false;
        }
        if(borrow.borrow_film_date.trim().equals(""))
        {
             theUS.setStatus(("��سҡ�͡�ѹ����������� Xray ��͹�ӡ�úѹ�֡"),UpdateStatus.WARNING);
             return false;
        }
        if(borrow.borrow_cause.trim().equals(""))
        {
             theUS.setStatus(("��سҡ�͡���˵ء�������͹�ӡ�úѹ�֡"),UpdateStatus.WARNING);
             return false;
        }
        if(borrow.permissibly_borrow.trim().equals(""))
        {
             theUS.setStatus(("��سҡ�͡���ͼ��͹حҵ����������� Xray ��͹�ӡ�úѹ�֡"),UpdateStatus.WARNING);
             return false;
        }
        if(borrow.amount_date.equals(Active.isDisable()))
        {
            theUS.setStatus(("�ӹǹ�ѹ����������դ���ҡ�����ٹ��"),UpdateStatus.WARNING);
            return false;
        }
        if(borrow.amount_date.trim().equals(""))
        {
            theUS.setStatus(("��سҡ�͡�ӹǹ�ѹ��������͹�ӡ�úѹ�֡"),UpdateStatus.WARNING);
            return false;
        }
        if(borrow.borrow_status.equals(Active.isEnable()) && borrow.return_film_date.trim().equals(""))
        {
            theUS.setStatus(("��س��к��ѹ���׹��͹�ӡ�úѹ�֡"),UpdateStatus.WARNING);
            return false;
        }
        if(borrow.borrow_to.trim().equals("") && borrow.borrow_to_other.trim().equals(""))
        {
            theUS.setStatus(("��س��к�ʶҹ���������仡�͹�ӡ�úѹ�֡"),UpdateStatus.WARNING);
            return false;
        }
        Date datebor = DateUtil.getDateFromText(borrow.borrow_film_date);
        Date dateret = DateUtil.getDateFromText(borrow.return_film_date);
        if(datebor != null && dateret != null){
            int date_valid = DateUtil.countDateDiff(borrow.borrow_film_date
                ,borrow.return_film_date);
            if(date_valid >0){
                theUS.setStatus(("�ѹ����������ѹ���׹�ժ�ǧ������١��ͧ"),UpdateStatus.WARNING);
                return false;
            }
        }
        theConnectionInf.open();
        try 
        {
            if(borrow.getObjectId()==null)
            {
                theHosDB.theBorrowFilmXrayDB.insert(borrow);
            }
            else
            {
                borrow.borrow_staff_update = theHO.theEmployee.getObjectId();
                borrow.borrow_update_date_time = theLookupControl.intReadDateTime();
                theHosDB.theBorrowFilmXrayDB.update(borrow);
            }
//            theHS.thePatientSubject.notifySaveBorrowFilmXray("��úѹ�֡�������׹����� Xray �������"
//                ,UpdateStatus.COMPLETE);
             theUS.setStatus(("��úѹ�֡�������׹����� Xray �������"),UpdateStatus.COMPLETE);
             if(borrow != null)
                 objectid = borrow.getObjectId();
             theSystemControl.setStatus(UseCase.TH_saveBorrowFilmXray,UpdateStatus.COMPLETE,null);
             theSystemControl.saveLog(UseCase.UCID_saveBorrowFilmXray,objectid,null,UpdateStatus.COMPLETE);
            return true;
       }
        catch(Exception ex)
        {    
            theSystemControl.setStatus(UseCase.TH_saveBorrowFilmXray,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveBorrowFilmXray,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    /**
     * 
     * 
     * @return : Vector �ͧ�ä��Шӵ�Ƿ��ź��������
     * @see : ź�ä��Шӵ�Ǽ�����
     * @Author : amp
     * @date : 14/02/2549
     * @param vPersonalDisease 
     * @param row 
     */
    public Vector deletePersonalDisease(Vector vPersonalDisease, int[] row)
    {
        if(row.length==0) 
        {
            theUS.setStatus(("�ѧ����բ�����"),UpdateStatus.WARNING);
            return vPersonalDisease;
        }
        try
        {
            theConnectionInf.open();
            for(int i=row.length-1; i>=0; i--)
            {
                PersonalDisease pd = (PersonalDisease)vPersonalDisease.get(row[i]); 
                if(pd.getObjectId()!= null) theHosDB.thePersonalDiseaseDB.delete(pd);
                vPersonalDisease.remove(row[i]);
            }            
        }
        catch(Exception ex)
        {  
            theUS.setStatus(("���ź�������ä��Шӵ�ǼԴ��Ҵ"),UpdateStatus.WARNING);
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();
        }
        return vPersonalDisease;
    }
    
    /**
     * 
     * 
     * @return : Object �ͧ�����·�������ҡ Hn
     * @see : ���� Hn ����ͧ��úѹ�֡�����š������׹����� Xray
     * @Author : sumo
     * @date : 21/02/2549
     * @param hn 
     * @param theUS 
     */
    public Patient readPatientByHnToBorrowFilm(String hn,UpdateStatus theUS)
    {
        Constant.println("public int readPatientByHn(String id)");
        if(hn.trim().equals("")) {
            theUS.setStatus(("��سҡ�͡�����Ţ HN"),UpdateStatus.WARNING);
            return null;
        }
        String str=null;
        try
        {
            int value = Integer.parseInt(hn);
            DecimalFormat d=new DecimalFormat();
            d.applyPattern("000000");
            str = d.format(value);
        }
        catch(Exception e)
        { 
            e.printStackTrace(Constant.getPrintStream());  
        }
        try
        {
            theConnectionInf.open();
            Patient pat = theHosDB.thePatientDB.selectByHnToBorrowFilm(str);

            if(pat == null)
            {
               theUS.setStatus(("����������տ���� X-ray"),UpdateStatus.WARNING);
               return null;
            }
            return pat;
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
     * 
     * 
     * @return : Object �ͧ�����·�������ҡ Xn
     * @see : ���� Xn ����ͧ��úѹ�֡�����š������׹����� Xray
     * @Author : sumo
     * @date : 20/02/2549
     * @param xn 
     * @param theUS 
     */
    public Patient readPatientByXnToBorrowFilm(String xn,UpdateStatus theUS)
    {
        Patient pat = new Patient();
        if(xn.trim().equals("")) 
        {
            theUS.setStatus(("��سҡ�͡�����Ţ XN"),UpdateStatus.WARNING);
            return null;
        }
        try
        {
            theConnectionInf.open();

            pat = theHosDB.thePatientDB.selectByXnToBorrowFilm(xn);
            if(pat == null)
            {
               theUS.setStatus(("��辺�����ż�����"),UpdateStatus.WARNING);
               return null;
            }
            theUS.setStatus(("��ô֧�����ż����¨ҡ XN �������"),UpdateStatus.COMPLETE);
            return pat;
        }
        catch(Exception e)
        {
            theUS.setStatus(("��ô֧�����ż����¨ҡ XN �Դ��Ҵ"),UpdateStatus.ERROR);
            e.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally
        {
            theConnectionInf.close();
        }
    }         
    
    /**
     * 
     * 
     * @return : Object �ͧ�����š������׹����� Xray �����������͡
     * @see : ��ҹ�����š������׹����� Xray �ҡ���ҧ
     * @Author : sumo
     * @date : 20/02/2549
     * @param pk 
     */
   public BorrowFilmXray readBorrowFilmXrayByPK(String pk)
    {
        BorrowFilmXray bor;
        theConnectionInf.open();
        try
        {
            bor = theHosDB.theBorrowFilmXrayDB.selectByPK(pk);
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
        return bor;
    }
   
    /**
     * 
     * 
     * @return : Object �ͧ�����·�������ҡ Hn
     * @see : ���� Hn ����ͧ��úѹ�֡�����š������׹ OpdCard
     * @Author : sumo
     * @date : 21/02/2549
     * @param hn 
     * @param theUS 
     */
    public Patient readPatientByHnToBorrowOpd(String hn,UpdateStatus theUS)
    {
        Patient pat = new Patient();
        if(hn.trim().equals("")) 
        {
            theUS.setStatus(("��سҡ�͡�����Ţ HN"),UpdateStatus.WARNING);
            return null;
        }
        String str=null;
        try
        {
            int value = Integer.parseInt(hn);
            DecimalFormat d=new DecimalFormat();
            d.applyPattern("000000");
            str = d.format(value);
        }
        catch(Exception e)
        { 
            e.printStackTrace(Constant.getPrintStream());  
        }
        try
        {
            theConnectionInf.open();
            pat = theHosDB.thePatientDB.selectByHn(str);
            if(pat == null)
            {
               theUS.setStatus(("��辺�����ż�����"),UpdateStatus.WARNING);
               return null;
            }
            return pat;
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
     * 
     * 
     * @return : Object �ͧ�����š������׹ OpdCard �����������͡
     * @see : ��ҹ�����š������׹ OpdCard �ҡ���ҧ
     * @Author : sumo
     * @date : 21/02/2549
     * @param pk 
     */
    public BorrowOpdCard readBorrowOpdCardByPK(String pk)
    {
        BorrowOpdCard bor;
        theConnectionInf.open();
        try
        {
            bor = theHosDB.theBorrowOpdCardDB.selectByPK(pk);
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
        return bor;
    }
    
    /**
     * 
     * 
     * @return : boolean
     * @see : ź�����š������׹ OpdCard
     * @Author : sumo
     * @date : 21/02/2549
     * @param borrow 
     * @param theUS 
     */
    public boolean deleteBorrowOpdCard(BorrowOpdCard borrow,UpdateStatus theUS)
    {
        Constant.println(UseCase.UCID_deleteBorrowOpdCard);
        String objectid =   null;
        theConnectionInf.open();
        if(borrow == null)
        {
            theUS.setStatus(("��س����͡��¡����� OpdCard ����ͧ���ź��͹"),UpdateStatus.WARNING);
            return false;
        }
        borrow.borrow_opdcard_active = "0";
        borrow.borrow_opdcard_staff_cancel = theHO.theEmployee.getObjectId();
        try
        {
            borrow.borrow_opdcard_cancel_date_time = theLookupControl.intReadDateTime();
            theHosDB.theBorrowOpdCardDB.update(borrow);
//            theHosDB.theBorrowOpdCardDB.delete(borrow);
            theUS.setStatus(("���ź��¡������׹ OpdCard �������"),UpdateStatus.COMPLETE);
            if(borrow != null)
                objectid = borrow.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deleteBorrowOpdCard,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteBorrowOpdCard,objectid,null,UpdateStatus.COMPLETE);
            return true;
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_deleteBorrowOpdCard,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deleteBorrowOpdCard,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     * 
     * 
     * @return : boolean �ͧ��÷ӧҹ㹿ѧ����
     * @see : �ѹ�֡�����š������׹ OpdCard
     * @Author : sumo
     * @date : 20/02/2549
     * @param borrow 
     * @param theUS 
     */
    public boolean saveBorrowOpdCard(BorrowOpdCard borrow,UpdateStatus theUS)
    {
        Constant.println("public void saveBorrowOpdCard(BorrowOpdCard borrow,UpdateStatus us)");
        Constant.println(UseCase.UCID_saveBorrowOpdCard);
        String objectid =   null;
        if(("").equals(borrow.patient_hn))
        {
            theUS.setStatus(("��س��к������Ţ HN �ͧ OpdCard ����ͧ������"),UpdateStatus.WARNING);
            return false;
        }
        if(borrow.borrower_opd_name.trim().equals("") || borrow.borrower_opd_lastname.trim().equals(""))
        {
             theUS.setStatus(("��سҡ�͡����-������ ��͹�ӡ�úѹ�֡"),UpdateStatus.WARNING);
             return false;
        }
        if(borrow.borrow_opd_date.trim().equals(""))
        {
             theUS.setStatus(("��سҡ�͡�ѹ������ OpdCard ��͹�ӡ�úѹ�֡"),UpdateStatus.WARNING);
             return false;
        }
        if(borrow.borrow_opd_cause.trim().equals(""))
        {
             theUS.setStatus(("��سҡ�͡���˵ء�������͹�ӡ�úѹ�֡"),UpdateStatus.WARNING);
             return false;
        }
        if(borrow.permissibly_borrow_opd.trim().equals(""))
        {
             theUS.setStatus(("��سҡ�͡���ͼ��͹حҵ������ OpdCard ��͹�ӡ�úѹ�֡"),UpdateStatus.WARNING);
             return false;
        }
        if(borrow.amount_date_opd.trim().equals(Active.isDisable()))
        {
            theUS.setStatus(("�ӹǹ�ѹ����������դ���ҡ�����ٹ��"),UpdateStatus.WARNING);
            return false;
        }
        if(borrow.amount_date_opd.trim().equals(""))
        {
            theUS.setStatus(("��سҡ�͡�ӹǹ�ѹ��������͹�ӡ�úѹ�֡"),UpdateStatus.WARNING);
            return false;
        }
        if(borrow.borrow_opd_status.equals(Active.isEnable()) && borrow.return_opd_date.trim().equals(""))
        {
            theUS.setStatus(("��س��к��ѹ���׹��͹�ӡ�úѹ�֡"),UpdateStatus.WARNING);
            return false;
        }
        if(borrow.borrow_opd_to.trim().equals("") && borrow.borrow_opd_to_other.trim().equals(""))
        {
            theUS.setStatus(("��س��к�ʶҹ���������仡�͹�ӡ�úѹ�֡"),UpdateStatus.WARNING);
            return false;
        }
        Date datebor = DateUtil.getDateFromText(borrow.borrow_opd_date);
        Date dateret = DateUtil.getDateFromText(borrow.return_opd_date);
        if(datebor != null && dateret != null){
            int date_valid = DateUtil.countDateDiff(borrow.borrow_opd_date
                ,borrow.return_opd_date);
            if(date_valid >0){
                theUS.setStatus(("��سҡ�͡�ѹ��������͹�ѹ���׹"),UpdateStatus.WARNING);
                return false;
            }
        }
        theConnectionInf.open();
        try 
        {
            if(borrow.getObjectId()==null)
            {
                theHosDB.theBorrowOpdCardDB.insert(borrow);
            }
            else
            {
                borrow.borrow_opdcard_staff_update = theHO.theEmployee.getObjectId();
                borrow.borrow_opdcard_update_date_time = theLookupControl.intReadDateTime();
                theHosDB.theBorrowOpdCardDB.update(borrow);
            }
            theUS.setStatus(("��úѹ�֡�������׹ OpdCard �������"),UpdateStatus.COMPLETE);
            if(borrow != null)
                objectid = borrow.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveBorrowOpdCard,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveBorrowOpdCard,objectid,null,UpdateStatus.COMPLETE);
            return true;
       }
        catch(Exception ex)
        {    
            theSystemControl.setStatus(UseCase.TH_saveBorrowOpdCard,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveBorrowOpdCard,objectid,ex,UpdateStatus.ERROR);
            return false;
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     * 
     * 
     * @return : Vector �ͧ���Ң����š����� OpdCard
     * @see : ���Ң����š����� OpdCard
     * @Author : sumo
     * @date : 21/02/2549
     * @param all_period 
     * @param dateFrom 
     * @param dateTo 
     * @param hn 
     * @param active 
     */
    public Vector listBorrowOpdCardByDate(boolean all_period,String dateFrom, String dateTo,String hn,String active)
    {
        Vector result = null;
        theConnectionInf.open();
        try
        {
            result = theHosDB.theSpecialQueryBorrowOpdCardDB.queryDataOrderbyDate(all_period,dateFrom,dateTo,hn,active);
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
     * 
     * 
     * @return : Vector �ͧ��¡�ùѴ����͹Ѵ
     * @see : ���ҹѴ�����ʶҹ��͹Ѵ
     * @Author : amp
     * @date : 25/02/2549
     * @param patient_id 
     */
    public Vector listWaitAppointment(String patient_id)
    {
        Vector result = null;
        theConnectionInf.open();
        try
        {
            result = theHosDB.theAppointmentDB.selectWait(patient_id);
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
     * 
     * 
     * @Author: amp
     * @date: 11/05/2549
     * @param: ���� ����� flag
     * @return: ��ͤ�����͹
     * @see: �ʴ���ͤ�����͹
     * @param sex 
     * @param age 
     * @param flag 
     */
    public boolean warningPregnant(String sex,String age,String birthday)
    {   
        //flag=0 ��͹����ͺѹ�֡�����ż�����
        //flag=0 ��͹��������͡������
        if(theHO.theVisit==null)
            return true;
        
        if(age==null || age.equals(""))
            age = DateUtil.calculateAge(birthday,theHO.date_time);

        if("1".equals(theHO.theVisit.pregnant)){
            if(Integer.parseInt(age) < 10){
                return theUS.confirmBox(Constant.getTextBundle("����(���¡��� 10 ��) ") +" " +
                        Constant.getTextBundle("�׹�ѹ��úѹ�֡"), theUS.WARNING);
            }
            if(Sex.isMAN().equals(sex)){
                return theUS.confirmBox(Constant.getTextBundle("���������ѹ��Ѻ��õ�駤����") + " " +
                        Constant.getTextBundle("�׹�ѹ��úѹ�֡"), theUS.WARNING);
            }
        }
        return true;
    }
   
    
    /**
     * 
     * 
     * @Author: amp
     * @date: 10/08/2549
     * @param: ���͵�Ǫ��·���к�
     * @see: ���ҵ�Ǫ��¹Ѵ
     * @param name 
     * @return 
     */
   public Vector listAppointmentTemplateByName(String name) 
   {
        Vector vc = new Vector();
        theConnectionInf.open();
        try 
        {            
            name = "%" + name.trim() + "%";            
            vc = theHosDB.theAppointmentTemplateDB.selectAppointmentTemplate(name);            
        }
        catch(Exception ex) 
        {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally 
        {
            theConnectionInf.close();
        }
        return vc;
    }
   
   /**
     * 
     * 
     * @Author: amp
     * @date: 10/08/2549
     * @param: key_id �ͧ AppointmentTemplate
     * @see: ���ҵ�Ǫ��� item �Ѵ
     * @param appointment_template_id 
     * @return 
     */
   public Vector listAppointmentTemplateItem(String appointment_template_id)
    {
        Vector vc;
        theConnectionInf.open();
        try{
            vc = theHosDB.theAppointmentTemplateItemDB.selectAppointmentTemplateItem(appointment_template_id);
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
        return vc;
    }  
   
   /**
     * 
     * 
     * @see : ź��Ǫ��¹Ѵ��� item ���١����Ѻ��Ǫ���
     * @Author : amp
     * @date : 10/08/2549
     * @param theAppointmentTemplate 
     * @param vAppointmentTemplateItem 
     */
   public void deleteAppointmentTemplate(AppointmentTemplate theAppointmentTemplate, Vector vAppointmentTemplateItem)
    {
        Constant.println(UseCase.UCID_deleteAppointmentTemplate);
        String objectid =   null;
        theConnectionInf.open();
        try
        {            
            theHosDB.theAppointmentTemplateDB.delete(theAppointmentTemplate);
            if(vAppointmentTemplateItem != null)
            {
                for(int i=vAppointmentTemplateItem.size()-1; i>=0; i--)
                {
                    theHosDB.theAppointmentTemplateItemDB.delete((AppointmentTemplateItem)vAppointmentTemplateItem.get(i));                    
                }
            }
            if(theAppointmentTemplate != null)
                objectid = theAppointmentTemplate.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deleteAppointmentTemplate,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteAppointmentTemplate,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_deleteAppointmentTemplate,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deleteAppointmentTemplate,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
   
   /**
     * 
     * 
     * @see : ź��¡�� item
     * @Author : amp
     * @date : 10/08/2549
     * @param vAppointmentTemplateItem 
     * @param row 
     */
    public void deleteAppointmentTemplateItem(Vector vAppointmentTemplateItem,int[] row)
    {
        theConnectionInf.open();
        try{
            AppointmentTemplateItem apti;
            for(int i=row.length-1; i>=0; i--)
            {
                apti = (AppointmentTemplateItem)vAppointmentTemplateItem.get(row[i]); 
                if(apti.getObjectId()!= null)
                {   
                    theHosDB.theAppointmentTemplateItemDB.delete(apti);
                }
                vAppointmentTemplateItem.remove(row[i]);
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
     * 
     * 
     * @see : �ѹ�֡��Ǫ��¹Ѵ
     * @Author : amp
     * @date : 10/08/2549
     * @param theAppointmentTemplate 
     * @param vAppointmentTemplateItem 
     */
    public void saveAppointmentTemplate(AppointmentTemplate theAppointmentTemplate,Vector vAppointmentTemplateItem )
    {
        Constant.println(UseCase.UCID_saveAppointmentTemplate);
        String objectid =   null;
        theConnectionInf.open();
        try{
            if(theAppointmentTemplate.getObjectId()==null)
            {
                theAppointmentTemplate.appoint_staff_record = theHO.theEmployee.getObjectId();
                theAppointmentTemplate.appoint_record_date_time = theHO.date_time;
                theHosDB.theAppointmentTemplateDB.insert(theAppointmentTemplate);                
            }
            else
            {
                theAppointmentTemplate.appoint_staff_update = theHO.theEmployee.getObjectId();
                theAppointmentTemplate.appoint_update_date_time = theHO.date_time;
                theHosDB.theAppointmentTemplateDB.update(theAppointmentTemplate);                
            }
            if(vAppointmentTemplateItem!=null)
            {
                AppointmentTemplateItem apti;
                for(int i=0,size=vAppointmentTemplateItem.size(); i<size; i++)
                {
                    apti = (AppointmentTemplateItem)vAppointmentTemplateItem.get(i);
                    if(apti.getObjectId()==null)
                    {
                        apti.appointment_template_id = theAppointmentTemplate.getObjectId();                    
                        theHosDB.theAppointmentTemplateItemDB.insert(apti);
                    }
                }    
            }
            theHS.thePatientSubject.notifySaveAppointment("��úѹ�֡��Ǫ��¹Ѵ�������"
                ,UpdateStatus.COMPLETE);//�������Ѵ����ö��觵�Ǫ��·�������������������
            if(theAppointmentTemplate != null)
                objectid = theAppointmentTemplate.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveAppointmentTemplate,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveAppointmentTemplate,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {    
            theSystemControl.setStatus(UseCase.TH_saveAppointmentTemplate,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveAppointmentTemplate,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    
    /**
     * 
     * 
     * @Author: amp
     * @date: 11/08/2549
     * @param: key_id �ͧ AppointmentTemplate
     * @return: Hashtable �ͧ AppointmentTemplate ��� Vector �ͧ AppointmentTemplateItem
     * @see: ���ҵ�Ǫ��¹Ѵ��е�Ǫ��� item �Ѵ
     * @param appointment_template_id 
     * @return 
     */
    public Hashtable listAppointmentTemplateAndItem(String appointment_template_id)
    {
        Hashtable ht = new Hashtable();
        theConnectionInf.open();
        try{            
            AppointmentTemplate apti = theHosDB.theAppointmentTemplateDB.selectAppointmentTemplateByPK(appointment_template_id);    
            Vector vc = theHosDB.theAppointmentTemplateItemDB.selectAppointmentTemplateItem(appointment_template_id);
            if(apti!=null) ht.put("AppointmentTemplate", apti);
            if(vc!=null) ht.put("vAppointmentTemplateItem", vc);
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
        return ht;
    }
    
    /*
     * @author pongtorn Henbe
     * ���Ҽ����¨ҡ���ͷ����鹧��·���ش�¨�令鹨ҡ��Ъҡ���������
     * �ҡ��һ�Ъҡù���繼������������Ǩ��������ʴ�������Ҥ�����������
     */
    /**
     * 
     * @param keyword 
     * @return 
     */
    public Vector listPatientByKeyword(String keyword)
    {
        if(keyword.equals(""))
        {
            theUS.setStatus(("��������������ҧ���� 1 ���"),UpdateStatus.WARNING);
            return null;
        }
        String[] key = keyword.split(" ");
        theConnectionInf.open();
        try{
            Vector result = new Vector();
            Vector result_fam = new Vector();
            for(int i=0;i<key.length;i++)
            {
                result.add(theHosDB.thePatientDB.selectByHnXnFnameLnamePid(key[i]));
                result_fam.add(theHosDB.theFamilyDB.queryByFLName(key[i],key[i]));
            }
            Vector res_ret=new Vector();
            intersectXVector(result,res_ret);
//            for(int i=0,size=result.size();i<size;i++){
//                Family person = (Family)person_v.get(i);
//                if(person.patient_id.equals(""))
//                    p.add(theHO.initPatient(person,null));
//            }
            return res_ret;
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
     * 
     * @param result 
     * @param res_ret 
     */
    public static void intersectXVector(Vector result,Vector res_ret)
    {
        if(result.size()==0)
            return ;
        
        Vector sub_res0 = (Vector)result.get(0);
        for(int i=1,size=result.size();i<size;i++)
        {
            Vector sub_res1 = (Vector)result.get(i);
            Constant.println(sub_res0.size() +":"+ sub_res1.size());
            for(int size1=sub_res0.size(),j=size1-1;j>=0;j--)
            {
                boolean is_x_ok = false;
                Persistent x = (Persistent)sub_res0.get(j);
                for(int size2=sub_res1.size(),k=size2-1;k>=0;k--)
                {
                    Persistent y = (Persistent)sub_res1.get(k);
                    //Constant.println(x.getObjectId() +":"+ y.getObjectId());
                    if(x.getObjectId().equals(y.getObjectId()))
                    {
                        is_x_ok = true;
                    }
                }
                if(!is_x_ok)
                    sub_res0.remove(j);
            }
            Constant.println(":" + sub_res0.size());
        }
        for(int i=0,size=sub_res0.size();i<size;i++)
        {
            res_ret.add(sub_res0.get(i));
        }
    }
    
    /**
     * 
     * 
     * @return : boolean �ѹ�֡���������
     * @see : �ѹ�֡���ŧ����¹������ NCD
     * @Author : sumo
     * @date : 28/08/2549
     * @modify : henbe 120906
     * @param pt 
     * @param vNCD 
     */
    public boolean saveNCD(Patient pt,Vector vNCD)
    {
        Constant.println(UseCase.UCID_saveNCD);
        String objectid =   null;
        theConnectionInf.open();
        try{
            theSystemControl.setStatus(UseCase.TH_saveNCD,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveNCD,objectid,null,UpdateStatus.COMPLETE);
            return intSaveNCD(pt,vNCD);
        }
        catch(Exception ex){  
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_saveNCD,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveNCD,objectid,ex,UpdateStatus.ERROR);
            return true;
        }
        finally{
            theConnectionInf.close();
        }
        
    }
     /**
     * 
     * 
     * @return : boolean �ѹ�֡���������
     * @see : �ѹ�֡���ŧ����¹������ NCD
     * @Author : sumo
     * @date : 28/08/2549
     * @modify : henbe 120906
     * @param pt 
     * @param vNCD 
     * @throws java.lang.Exception 
     */
    public boolean intSaveNCD(Patient pt,Vector vNCD)throws Exception
    {
            if(vNCD == null) {
                return false;
            }
            theHosDB.theNCDDB.deleteByPatientid(pt.getObjectId()); 
            if(vNCD.isEmpty()){
                return true;
            }
            for(int i=0,size = vNCD.size(); i < size; i++)
            {
                NCD theNCD = (NCD)vNCD.get(i);
                //���������к������Ţ NCD ������� Gen ���  sumo 28/08/2549
                if(theNCD.ncd_number.equals(""))
                {
                    theNCD.ncd_number = theHosDB.theNCDGroupDB.updateSequence(theNCD.ncd_group_id);
                } 
                theNCD.staff_record = theHO.theEmployee.getObjectId();
                theNCD.staff_modify = theHO.theEmployee.getObjectId();
                theNCD.modify_date_time = theHO.date_time;
                theNCD.record_date_time = theHO.date_time;
                theNCD.patient_id = pt.getObjectId();
                theHosDB.theNCDDB.insert(theNCD);
            }
            theHO.vNCD = theHosDB.theNCDDB.selectByPatientId(pt.getObjectId());
            return true;
    }
    /**
     * ��ҹ�����������ҹ
     * 
     * @return Home
     * @date 06/09/2549
     * @not deprecated henbe �ѧ��鹹���ѧ�Դ pattern ��ͧ�ա���Դ�Դ connection ����
     * @param id �����Ţid�����ҹ
     */
    public Village readVillage(String id)
    {
        theConnectionInf.open();
        try{
            return theHosDB.theVillageDB.selectByPK(id);
        }
        catch(Exception ex)
        {
            ex.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     * ��ҹ�����ź�ҹ
     * 
     * @return Home
     * @date 06/09/2549
     * @param id 
     */
    public Home readHomeByID(String id)
    {   Home home = null;
        theConnectionInf.open();
        try
        {   home = theHosDB.theHomeDB.selectByPK(id);
        }
        catch(Exception ex)
        {   ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {   theConnectionInf.close();
        }
        return home;
    }
    /**
     * ��ҹ�����Ż�Ъҡ����� PID
     * 
     * @return family
     * @date 06/09/2549
     * @param pid �����Ţ�ѵû�ЪҪ�
     */
    public Family readFamilyByPid(String pid)
    {   Family family = null;
        theConnectionInf.open();
        try
        {   family = theHosDB.theFamilyDB.selectByPid(pid);
        }
        catch(Exception ex)
        {   ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {   theConnectionInf.close();
        }
        return family;
    }
    /**
     * ���һ�Ъҡ� ���觢����ż������Ҫ����Ҵ��¶���� ������ա���
     **/
    public boolean intReadFamilySuit(Family fm,Patient patient) throws Exception
    {
        System.out.println("public boolean intReadFamilySuit(Family fm,Patient patient) throws Exception");
        theHO.clearFamily();
        Family family=fm;
        // �ҡ��Ъҡä���鹹���Ҩҡ����� import �������պ�ҹ�������Һ�ҹ�������
        if(family.home_id==null || family.home_id.equals("")){
            intReadVillage("","","");
            intReadHome("","",null); 
        }
        else{
            theHO.theHome = intReadHome(family.home_id,null,null);
            intReadVillage(theHO.theHome.village_id,null,null);
        }
        /////////////////////////////////////////////////////
        theHO.theFamily = family;
        theHO.vPatientPayment = theHosDB.thePatientPaymentDB.selectByFamilyPatient(family,patient);
        theHO.theFamilyFather = theHosDB.theFamilyDB.selectByPK(family.father_fid);
        theHO.theFamilyMother = theHosDB.theFamilyDB.selectByPK(family.mother_fid);
        theHO.theFamilyCouple = theHosDB.theFamilyDB.selectByPK(family.couple_fid);
        return true;
    }
 /**
     *�鹢����ŷ��١�Ѻ�����ż����¤����������������ŷ�����
     **/
    public boolean intReadPatientSuit(Patient pt) throws Exception
    {
        theHO.thePatient = pt;
        if(pt==null){
            theHO.vDrugAllergy = null;
            theHO.vBillingPatient = null;
            //theHO.vPatientPayment = null;
            theHO.theDeath = null;
            theHO.vRiskFactor = null;
            theHO.vFamilyHistory = null;
            theHO.vPersonalDisease = null;
            theHO.vPastHistory = null;
            theHO.vNCD = null;
            theHO.thePatientXN = null;
            theHO.vVisit = null;
            theHO.clearVisit();
            return false;
        }
        String patient_id = pt.getObjectId();
        theHO.vDrugAllergy = theHosDB.theDrugAllergyDB.selectByPatientId(patient_id);
        theHO.vBillingPatient = theHosDB.theBillingDB.selectByPatientId(patient_id);
        //theHO.vPatientPayment = theHosDB.thePatientPaymentDB.selectByFamilyPatient(theHO.theFamily,theHO.thePatient);
        theHO.theDeath = theHosDB.theDeathDB.selectByPatientId(patient_id);
        theHO.vRiskFactor = theHosDB.theRiskFactorDB.selectByPatientId(patient_id);
        theHO.vFamilyHistory = theHosDB.theFamilyHistoryDB.selectByPatientId(patient_id);
        theHO.vPersonalDisease = theHosDB.thePersonalDiseaseDB.selectByPatientId(patient_id);
        theHO.vPastHistory = theHosDB.thePastHistoryDB.selectByPatientId(patient_id);
        theHO.vNCD = theHosDB.theNCDDB.selectByPatientId(patient_id);
        theHO.thePatientXN = theHosDB.thePatientXNDB.selectCurrentByPatientID(patient_id);
        theHO.vVisit = theHosDB.theVisitDB.selectListByPtid(patient_id);
        //intFilterVectorDrugAllergy(theHO.vDrugAllergy);
        return true;
    }
    /**
     *�鹢����ŷ������Ǣ�ͧ�Ѻ visit ���
     **/
    public boolean intReadVisitSuit(Visit visit) throws Exception
    {
        theHO.theVisit = visit;
        if(theHO.theVisit==null){
            theHO.clearVisit();
            return false;
        }
        //��ͧ�ѹ�������բ������ҡ�Թ仵͹�֧�����š�� map diagnosis
        theHO.vDxTemplate = theHosDB.theDxTemplate2DB.selectByVid(theHO.theVisit.getObjectId());
        theHO.vDiagIcd9 = theHosDB.theDiagIcd9DB.selectByVid(theHO.theVisit.getObjectId(),Active.isEnable());
        theHO.vDiagIcd10 = theHosDB.theDiagIcd10DB.selectByVidSort(theHO.theVisit.getObjectId());
        theHO.vBillingInvoice = theHosDB.theBillingInvoiceDB.selectByVisitId(theHO.theVisit.getObjectId());
        theHO.vBilling = theHosDB.theBillingDB.selectByVisitId(theHO.theVisit.getObjectId());
        theHO.vTransfer = theHosDB.theTransferDB.selectByVisitId(theHO.theVisit.getObjectId());
        theHO.vVisitPayment = theHosDB.thePaymentDB.selectByVisitId(theHO.theVisit.getObjectId());
        theHO.vVitalSign = theHosDB.theVitalSignDB.selectByVisitDesc(theHO.theVisit.getObjectId());
        if(theHO.vVitalSign!=null && theHO.vVitalSign.size()>0)
            theHO.theVitalSign = (VitalSign) theHO.vVitalSign.get(0);
        theHO.vPhysicalExam = theHosDB.thePhysicalExamDB.selectByVisitId(theHO.theVisit.getObjectId());
        theHO.vPrimarySymptom = theHosDB.thePrimarySymptomDB.selectByVisitId(theHO.theVisit.getObjectId());
        theHO.vOrderItem = theHosDB.theOrderItemDB.selectByVidTypeCancel(theHO.theVisit.getObjectId(),"",false);
        theHO.theListTransfer = theHosDB.theQueueTransferDB.select2ByVisitID(theHO.theVisit.getObjectId());
        theHO.vHealthEducation = theHosDB.theGuideAfterDxTransactionDB.selectGuideByHealthEducation(theHO.theVisit.getObjectId());
        theHO.vMapVisitDx = theHosDB.theMapVisitDxDB.selectMapVisitDxByVisitID(theHO.theVisit.getObjectId(),Active.isEnable());
        theHO.theReferIn = theHosDB.theReferDB.selectByVisitIdType(theHO.theVisit.getObjectId(),Refer.REFER_IN);
        theHO.theReferOut = theHosDB.theReferDB.selectByVisitIdType(theHO.theVisit.getObjectId(),Refer.REFER_OUT);
        theHO.vOrderItemReceiveDrug = null;
        if(theHO.theVisit.visit_type.equals(VisitType.IPD))
            theHO.vOrderItemReceiveDrug = theHosDB.theOrderItemReceiveDrugDB.selectOIRDByVId(theHO.theVisit.getObjectId());
        /////////////////////////////////////////////////////////////////
        return true;
    }

    public Vector listPatientByNCD(String group, String number) 
    {
        if(number.equals("")){
            theUS.setStatus(("��سҡ�͡�����Ţ NCD"),UpdateStatus.WARNING);
            return null;
        }        
        theConnectionInf.open();
        try{
           String sql = "select * from t_patient_ncd " +
                   " inner join t_patient on t_patient.t_patient_id = t_patient_ncd.t_patient_id" +
                   " where patient_ncd_number like '%"+ number +"' ";
           if(group!=null)
                   sql += " and b_ncd_group_id = '"+group+"'";
           
           return theHosDB.thePatientDB.eQuery(sql);
        }
        catch(Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally {
            theConnectionInf.close();
        }     
    }

    /**
     * �Ӽ����·��¡��ԡ���ǡ�Ѻ����ҹ����
     * @return
     */
    public int activePatient() 
    {
        if(theHO.thePatient==null){
            theUS.setStatus(("��س����͡������"),UpdateStatus.WARNING);
            return -1;
        }
        if(theHO.thePatient.active.equals("1")){
            theUS.setStatus(("��س����͡�����·��¡��ԡ�����ҹ"),UpdateStatus.WARNING);
            return -1;
        }   
//        if(!theHO.theEmployee.authentication_id.equals(Authentication.ADMIN)){
//            theUS.setStatus(Constant.getTextBundle("����Ѻ�������к���ҹ��"),UpdateStatus.WARNING);
//            return 0;
//        }
        //���ӻ���ѵԢͧ������·ҧ������Ѻ����鹷ҧ
        if(!theUS.confirmBox(Constant.getTextBundle("������йӻ���ѵ����Ѻ��ԡ�÷�����������Ѻ����� ��Ѻ���繢ͧ����������͹���"),UpdateStatus.WARNING))
            return 0;
        
        theConnectionInf.open();
        try{
            Vector visit_v = theHosDB.theVisitDB.selectEqualHn(theHO.thePatient.hn);
            if(!visit_v.isEmpty()){
                for(int i=0;i<visit_v.size();i++){
                    Visit vvisit = (Visit)visit_v.get(i);
                    vvisit.patient_id = theHO.thePatient.getObjectId();
                    vvisit.visit_note = vvisit.visit_note + " �ӻ���ѵԡ�Ѻ���ѧ�����¤����";
                    theHosDB.theVisitDB.update(vvisit);
                }
            }
            theHO.thePatient.active = "1";
            theHO.thePatient.staff_modify = theHO.theEmployee.getObjectId();
            theHO.thePatient.update_date_time = theHO.date_time;
            theHosDB.thePatientDB.update(theHO.thePatient);
            theHO.thePatient.getFamily().active = "1";
            theHO.thePatient.getFamily().staff_modify = theHO.theEmployee.getObjectId();
            theHO.thePatient.getFamily().modify_date_time = theHO.date_time;
            theHosDB.theFamilyDB.update(theHO.thePatient.getFamily());
            theHS.thePatientSubject.notifySavePatient(Constant.getTextBundle("��ùӼ����·��١¡��ԡ����ǡ�Ѻ���������������"),UpdateStatus.COMPLETE);
           return visit_v.size();
        }
        catch(Exception e) {
            e.printStackTrace(Constant.getPrintStream());
            return -1;
        }
        finally {
            theConnectionInf.close();
        }     
    }

    public Vector listPatientByHcis(String pid) 
    {
        pid = pid.trim();
        if(pid.length() == 0){
            theUS.setStatus(("��سҡ�͡�����Ţ HCIS"),UpdateStatus.WARNING);
            return null;
        }
        theConnectionInf.open();
        try{
            return intListPatient("","","","",pid);
        }
        catch(Exception e)
        {
            e.printStackTrace(Constant.getPrintStream());
            return null;
        }
        finally {
            theConnectionInf.close();
        }
    }
    public static void main(String[] argc){
        System.out.println("3.9.2beta".compareTo("3.9.2build10"));
        System.out.println("3.9.2beta10".compareTo("3.9.2beta9"));
    }

    private boolean checkSavePatientHistory(Vector past_hx_v, Vector family_hx_v, Vector person_dss_v, Vector risk_factor_v, Vector drug_alg_v) {
        boolean isSave = true;
        for (int i = 0; i<past_hx_v.size() && isSave; i++) {
            PastHistory ph = (PastHistory)past_hx_v.get(i);
            if(ph.topic.equals("�»�����"))
            {
                if(com.hosv3.utility.DateUtil.countDateDiff(ph.date_desc,theHO.date_time)> 0)
                {
                    theUS.setStatus(Constant.getTextBundle("�ѹ�����������¢ͧ�ä") +
                            " "+ph.description+" " +
                            Constant.getTextBundle("���ѹ�͹Ҥ�") +
                            " " +
                            Constant.getTextBundle("�������ö�ѹ�֡��"),UpdateStatus.WARNING);
                    return false;
                }
            }
        }
        return isSave;
    }
    /**
     *��ѡ��÷ӧҹ�ͧ�ѧ�ѹ����͡�õ�Ǩ�ͺ��ǧ˹��
     *�ҡ��Ъҡ÷��������繼�����������зӧҹ� step ��͹˹�Ҥ�ͤ��һ�Ъҡ�
     *�ҡ�����·�����������㹡�кǹ���������зӧҹ� step ��͹˹�Ҥ�ͤ��Ҽ�����
     **/
    public void readVisit(String family_id,String patient_id,String visit_id)
    {
        Constant.println("public void readPatientByPatientID(String pid)");
        
        theConnectionInf.open();
        try{
            Family family = null;
            Patient patient = null;
            Visit visit = null;
            ///unlock old visit//////////////////////////////////
            theVisitControl.intUnlockVisit(theHO.theVisit);
            theHO.clearFamily();
            
            if(family_id!=null)
                family = theHosDB.theFamilyDB.selectByPK(family_id);
            
            if(family==null){
                theUS.setStatus(("��辺�����Ż�Ъҡ�"),UpdateStatus.WARNING);
                return;
            }
            ///////////////////////////////////////////////////////////////////
            
            if(patient_id!=null)
                patient = theHosDB.thePatientDB.selectByPK(patient_id);
            else if(family_id!=null)
                patient = theHosDB.thePatientDB.selectByFid(family_id);
            
            if(patient==null){
                intReadFamilySuit(family,null);
                theHS.thePatientSubject.notifyReadFamily(Constant.getTextBundle(
                            "������¡�٢����Ż�Ъҡ��������"),UpdateStatus.COMPLETE);
                return;
            }
            ///////////////////////////////////////////////////////////////////
            if(visit_id!=null)
                visit = theHosDB.theVisitDB.selectByPK(visit_id);
            else if(patient.getObjectId()!=null)
                visit = intReadVisitRet(patient.getObjectId());
            
            if(visit==null){
                intReadFamilySuit(family,null);
                intReadPatientSuit(patient);
                theHS.thePatientSubject.notifyReadPatient(Constant.getTextBundle(
                        "������¡�٢����ż������������"),UpdateStatus.COMPLETE);
                return;
            }
            theLookupControl.intReadDateTime();
            intReadFamilySuit(family,null);
            intReadPatientSuit(patient);
            intReadVisitSuit(visit);
            intLockVisit(theHO.date_time);
            theVisitControl.intSaveTransferCatch(theHO.theEmployee, theHO.date_time);
            theHS.theVisitSubject.notifyReadVisit("���¡�٢����š���Ѻ��ԡ�âͧ�������������"
                    ,UpdateStatus.COMPLETE);
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(("��ô֧�����ż����¼Դ��Ҵ"),UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }

    public String countAppointmentTime(String date, String time, String doctor) {

        theConnectionInf.open();
        try{
            StringBuffer sb = new StringBuffer("select count(*)  from t_patient_appointment")
                    .append(" where patient_appointment_date = '").append(date).append("' ")
                    .append(" and patient_appointment_time = '").append(time).append("'")
                    .append(" and patient_appointment_doctor = '").append(doctor).append("'");
            ResultSet rs = theConnectionInf.eQuery(sb.toString());
            int ret = 0;
            while(rs.next())
                ret = rs.getInt(1);
            rs.close();
            return String.valueOf(ret);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(("��äӹǳ�ӹǹ�Ѵ�Դ��Ҵ"),UpdateStatus.ERROR);
            return "";
        }
        finally{
            theConnectionInf.close();
        }
    }

    // Somprasong
    public Vector getAddressBySMC(SmartCardControl smartCardControl) {
        Vector vData = new Vector();
        String str_addrTumbol = smartCardControl.getAddressTumbon();
        String str_addrAmphur = smartCardControl.getAddressAmphur();
        String str_addrChangwat = smartCardControl.getAddressChangwat();
        theConnectionInf.open();
        try {
            Address2 c_addr = theHosDB.theAddressDB.selectChangwatByName(str_addrChangwat);
            vData.add(0, c_addr);
            Address2 a_addr = theHosDB.theAddressDB.selectAmphurByCAddress(c_addr, str_addrAmphur);
            vData.add(1, a_addr);
            Address2 t_addr = theHosDB.theAddressDB.selectTambolByAAddress(a_addr, str_addrTumbol);
            vData.add(2, t_addr);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        } finally {
            theConnectionInf.close();
        }
        return vData;

    } 
}
