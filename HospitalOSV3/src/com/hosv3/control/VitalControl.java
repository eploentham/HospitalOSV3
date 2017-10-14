/*
 * VitalControl.java
 *
 * Created on 20 ���Ҥ� 2546, 12:14 �.
 */
package com.hosv3.control;
import com.hospital_os.usecase.connection.ConnectionInf;
import com.hospital_os.object.*;
import com.hospital_os.usecase.connection.Persistent;
import com.hospital_os.utility.Gutil;
import com.hosv3.utility.connection.*;
import com.hosv3.utility.*;
import com.hosv3.object.*;
//import com.hosv3.object.nan.*;
import com.hosv3.subject.*;
import java.sql.ResultSet;
import java.util.*;
/**        
 *
 * @author  Amp
 */
public class VitalControl {
    String theStatus;

    ConnectionInf theConnectionInf;
    
    HosDB theHosDB;
    HosObject theHO;
    HosSubject theHS;
    UpdateStatus theUS;
    LookupControl theLookupControl;
    VisitControl theVisitControl;
    OrderControl theOrderControl;
    DiagnosisControl theDiagnosisControl;
    SystemControl theSystemControl;
    /** Creates a new instance of LookupControl */
    public VitalControl(ConnectionInf con
    ,HosObject ho,HosDB hdb,HosSubject hs){
        theConnectionInf = con;
        theHO = ho;
        theHosDB = hdb;
        theHO = ho;
        theHS = hs;
    }
    public void setDepControl(LookupControl lc,VisitControl vc,DiagnosisControl dc,OrderControl oc){
        theLookupControl = lc;
        theVisitControl = vc;
        theDiagnosisControl = dc;
        theOrderControl = oc;
    }
    public void setUpdateStatus(UpdateStatus us){
        theUS = us;
    }
    public void setSystemControl(SystemControl systemControl)
    {
        theSystemControl = systemControl;
    }
/**
 *����դӺ�����
 *
    ������¡���ҷ���� ���ѧ�������㹰ҹ������ pu
    ���� Dx � Text field ��͹ save ŧ�ҹ������ pu
    amp:20/02/2549  
 *  ���ա������������ŧ� vector of vDxTemplate ��͹���ͨ���������Ҩе�ͧ�ʴ� icd dialog ������͡��������
 *  ���ա������������ŧ� vector of vMapVisitDx �������ͨ������ҧ��������Ѻ�����Ũҡ icd dialog ���
 *  
 */
    public void addDxTemplate(DxTemplate dx)
    {
        Constant.println("_________________________public void addDxTemplate(DxTemplate dx)");
        
        if(theHO.theVisit==null){
            theUS.setStatus(("��س����͡�����·������㹡�кǹ���"),UpdateStatus.WARNING);
            return;
        }
        if(dx != null){   
            if(theHO.vGuide==null)
                theHO.vGuide = new Vector(); 
            
            boolean same = false;
            //��Ǩ�ͺ��ҫ�ӡѺ�����������������
            Constant.println(dx.description);
            Constant.println(dx.getObjectId());
            for(int i=0,size=theHO.vDxTemplate.size();i<size;i++)
            {
                DxTemplate ho_dx = (DxTemplate)theHO.vDxTemplate.get(i);
                if(ho_dx.getObjectId().equals(dx.getObjectId())){
                    same = true;
                }
                Constant.println(ho_dx.getObjectId());
            }
            
            if(same){
                theUS.setStatus(("�ѹ�֡����ԹԨ����ä��ӡѺ��¡�á�͹˹��"),UpdateStatus.WARNING);
                return;
            }
            Constant.println(theHO.vMapVisitDx.size());
//            Constant.println("_________________________1public void addDxTemplate(DxTemplate dx)" + dx.description);
            MapVisitDx mvd = theHO.initMapVisitDx(dx);
            if(mvd!=null){
                theHO.vDxTemplate.add(dx);
                theHO.vMapVisitDx.add(mvd);
            }
            //������������� vDxTemplate
            if(!"".equals(dx.guide_after_dx)) 
                theHO.vGuide.addElement(dx.guide_after_dx); 
                
            theHS.theVitalSubject.notifyAddDxTemplate(Constant.getTextBundle("�ѹ�֡����ԹԨ����ä") + " " +
                    Constant.getTextBundle("�������"),UpdateStatus.COMPLETE);
        }
    }
    
/**
 *����դӺ�����
 *
    ������¡���ҷ���� ���ѧ�������㹰ҹ������ pu
    ���� Dx � Text field ��͹ save ŧ�ҹ������ pu
    amp:20/02/2549  
 *  ���ա������������ŧ� vector of vDxTemplate ��͹���ͨ���������Ҩе�ͧ�ʴ� icd dialog ������͡��������
 *  ���ա������������ŧ� vector of vMapVisitDx �������ͨ������ҧ��������Ѻ�����Ũҡ icd dialog ���
 *  
 */
    public void deleteDxTemplate(int index)
    {
        MapVisitDx mvd = (MapVisitDx)theHO.vMapVisitDx.get(index);
        if(mvd.getObjectId()==null){
            theHO.vMapVisitDx.remove(index);
            return;
        }
        mvd.visit_diag_map_staff_cancel = theHO.theEmployee.getObjectId();
        mvd.visit_diag_map_cancel_date_time = theHO.date_time;
        mvd.visit_diag_map_active = "0";
        try {
            theConnectionInf.open();
            theHosDB.theMapVisitDxDB.update(mvd);
            theHO.vMapVisitDx = theHosDB.theMapVisitDxDB.selectByVid(theHO.theVisit.getObjectId());
            theHS.theVitalSubject.notifyAddDxTemplate(Constant.getTextBundle("�ѹ�֡����ԹԨ����ä") + " " +
                    Constant.getTextBundle("�������"),UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("��úѹ�֡ Dx") + " " +
                    Constant.getTextBundle("�Դ��Ҵ"),UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
        
    }
    /**
     *@Author: amp
     *@date:07/04/2549
     *@see: ������õ�Ǩ��ҧ���ŧ� Vector ��͹ save ŧ�ҹ������
     */     
    public void addBodyOrgan(String body_organ)
    {
        PhysicalExam pe;
        for(int i=0,size=theHO.vPhysicalExam.size();i<size;i++)
        {
            pe = (PhysicalExam)theHO.vPhysicalExam.get(i);
            pe.physical_body = body_organ;
        }
        pe=null;
    }
    
    /*�����ҡ�����ͧ�� ����ҡ���Ӥѭ � TextArea ��͹ Save ŧ�ҹ������ pu*/
    public void addPrimarySymptom(VitalTemplate theVitalTemplate)
    {
        theHO.theVitalTemplate = theVitalTemplate;
        theHS.theVitalSubject.notifyAddPrimarySymptom(Constant.getTextBundle("��������ҡ���Ӥѭ") + " " +
                Constant.getTextBundle("�������"), UpdateStatus.COMPLETE);
    }
    /**
     * ��Ǩ�ͺ��� �����Ź�����餹����繼��ѹ�֡������� �ҡ�����������ж�����ʼ�ҹ������䢢����������
     * ����������¹�ŧ
     * �������դ�����ͧ�������������������Ң����š�͹˹�����繤�ҵ�駵������������
     * ����䢡�кѹ�֡�������繪��ͧ͢��᷹� Object ����
     * @param tro
     * @param recorder
     * @return
     * @throws java.lang.Exception
     */
    public boolean isObjModify(Persistent tro,String recorder)throws Exception
    {
        if( tro.getObjectId()!=null 
        && !recorder.equals(theHO.theEmployee.getObjectId()))
        {
            theUS.setStatus(("�س������Է�������¡�÷��ѹ�֡�¼���餹���"),UpdateStatus.WARNING);
            return false;
//            Employee checkE = theHosDB.theEmployeeDB.selectByPK(recorder);
//            boolean retb = DialogPasswd.showDialog(theHO,theUS,checkE.employee_id,checkE.password,false);
//            if(!retb) {
//                theUS.setStatus("�س������Է��㹡����䢢�������¡�ù��",UpdateStatus.WARNING);
//                return false;
//            }
        }
        return true;
    }
    
    public void deletePrimarySymptom(Vector vPrimarySymptom,int[] row)
    {
        Constant.println(UseCase.UCID_deletePrimarySymtomByVid);
        String objectid =   null;
        if(theHO.theVisit == null) {
            theUS.setStatus(("��س����͡�����·������㹡�кǹ���"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("�����¨���кǹ��������������ö��䢢�������"),UpdateStatus.WARNING);
            return ;
        }
        if(row == null || row.length == 0){
            theUS.setStatus(("��س����͡��¡���ҡ�����ͧ��"),UpdateStatus.WARNING);
            return;
        }
        if(!theUS.confirmBox(("�׹�ѹ����ź��¡�ù�����������"),UpdateStatus.WARNING)){
            return ;
        }
        theConnectionInf.open();
        try{
            for(int i=row.length-1;i>=0;i--){
                PrimarySymptom thePrimarySymptom = (PrimarySymptom)vPrimarySymptom.get(row[i]);
                if(theLookupControl.theLO.theOption.vitalsign_secure.equals(Active.isEnable())){
                if(!isObjModify(thePrimarySymptom,thePrimarySymptom.staff_record))
                    return;
                }
                thePrimarySymptom.staff_cancel = theHO.theEmployee.getObjectId(); 
                thePrimarySymptom.active = Active.isDisable();
                vPrimarySymptom.remove(row[i]);
                thePrimarySymptom.staff_modify = theHO.theEmployee.getObjectId();
                theHosDB.thePrimarySymptomDB.update(thePrimarySymptom);
            }
            theHS.theVitalSubject.notifyManagePrimarySymptom(Constant.getTextBundle("���ź�����š�õ�Ǩ��ҧ���") +" "+
                    Constant.getTextBundle("�������"),UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_deletePrimarySymtomByVid,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deletePrimarySymtomByVid,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_deletePrimarySymtomByVid,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deletePrimarySymtomByVid,objectid,ex,UpdateStatus.ERROR);
        }
    }    
    /**
     * �ѹ�֡ v/s ŧ�ҹ������
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     */
    public int saveVitalSign(VitalSign vitalSign) 
    {
        //����ҵðҹ����ҹ����ҡ��ö����͡�ͧ
        Constant.println(UseCase.UCID_saveVitalSign);
        String objectid =   null;
        if(theHO.theVisit==null) 
        {
            theUS.setStatus(("��س����͡�����·������㹡�кǹ���"),UpdateStatus.WARNING);
            return 1;
        }
        if(theHO.theVisit.isDischargeDoctor())
        {
            theUS.setStatus(("�����¨�˹��·ҧ���ᾷ�������������ö��䢢�������"),UpdateStatus.WARNING);
            return 1;
        }
        if(vitalSign.weight.equals("")  && vitalSign.height.equals("")
        && vitalSign.pressure.equals("")&& vitalSign.puls.equals("")
        && vitalSign.res.equals("")     && vitalSign.temp.equals("")
        && vitalSign.waistline.equals("")        )
        {
            theUS.setStatus(("��سҡ�͡���������ҧ���� 1 ��¡��"),UpdateStatus.WARNING);
            return 2;
        }
        if(!vitalSign.weight.equals(""))
        {
            float weight = Float.parseFloat(vitalSign.weight);
            if(weight<0 || weight>200){
                theUS.setStatus(("���˹ѡ�������㹪�ǧ 0-200"),UpdateStatus.WARNING);
                return 3;
            }
        }
        if(!vitalSign.height.equals(""))
        {
            float height = Float.parseFloat(vitalSign.height);
            if(height<30 || height>250){
                theUS.setStatus(("��ǹ�٧�������㹪�ǧ 30-250"),UpdateStatus.WARNING);
                return 4;
            }
        }
        int index = vitalSign.pressure.indexOf('/');
        if(index!=-1)
        {
            String pres1 = vitalSign.pressure.substring(0,index);
            String pres2 = vitalSign.pressure.substring(index+1);
            if(pres1.equals("") || pres2.equals(""))
            {
                theUS.setStatus(("��سҡ�͡��Ҥ����ѹ���ú��ǹ"),UpdateStatus.WARNING);
                return 5;
            }
            float pressure1 = Float.parseFloat(pres1);
            float pressure2 = Float.parseFloat(pres2);
            //���� requirement �ͧ��ͧ�� �������ѹ��ǧ�á������ 350
            if(pressure1<0 || pressure1>350)
            {
                theUS.setStatus(("�����ѹ����á�������㹪�ǧ 0-350"),UpdateStatus.WARNING);
                return 6;
            }
            if(pressure2<0 || pressure2>160)
            {
                theUS.setStatus(("�����ѹ�����ѧ�������㹪�ǧ 0-160"),UpdateStatus.WARNING);
                return 7;
            }
            if(pressure1<pressure2)
            {
                theUS.setStatus(("�����ѹ����á��ͧ�ҡ���Ҥ�Ҥ����ѹ�����ѧ"),UpdateStatus.WARNING);
                return 8;
            }
        }
        if(!vitalSign.puls.equals(""))
        {
            float puls = Float.parseFloat(vitalSign.puls);
            if(puls<0 || puls>200)
            {
                theUS.setStatus(("�ѵ�ҡ���鹢ͧ���㨤������㹪�ǧ 0-200"),UpdateStatus.WARNING);
                return 9;
            }
        }
        if(!vitalSign.res.equals(""))
        {
            float res = Float.parseFloat(vitalSign.res);
            if(res<0 || res>120)
            {
                if(!theUS.confirmBox(Constant.getTextBundle("�ѵ�ҡ�����㨤������㹪�ǧ 0-120 ��ҷ���͡�դ���ҡ���Ҥ�һ���") + " " +
                   Constant.getTextBundle("�׹�ѹ���кѹ�֡���������"),UpdateStatus.WARNING))
                {
                    return 10;
                }
            }
        }
        if(!vitalSign.temp.equals(""))
        {
            float temp = Float.parseFloat(vitalSign.temp); 
            if(temp<35 || temp>45){
                theUS.setStatus(("�س����Ԥ������㹪�ǧ 35-45"),UpdateStatus.WARNING);
                return 11;
            }
        }
        // Somprasong comment 
//        vitalSign.record_time = theLookupControl.getTextCurrentTime();
//        vitalSign.record_date = theLookupControl.getTextCurrentDate();
        theConnectionInf.open();
        ///////////////////////////////////////////////////////////////////////////
        //henbe:07/06/2550:��Ǩ�ͺ�ѹ����͹Ҥ�
        try{
            if(theLookupControl.isDateTimeFuture(vitalSign.check_date+",00:00:00")) {
                theUS.setStatus(("�ѹ������ҷ���Ǩ �������͹Ҥ������"),UpdateStatus.WARNING);
                return 12;
            }
        }
        catch(Exception e){
            theUS.setStatus(("�ѹ������ҷ���Ǩ ���١��ͧ"),UpdateStatus.WARNING);
            return 13;
        }
        ///////////////////////////////////////////////////////////////////////////
        
        try {
           if(theLookupControl.theLO.theOption.vitalsign_secure.equals(Active.isEnable())){
               // Somprasong comment 07122010 ��Ҥ��� user �ѹ������� ����÷ӧҹ
//            if(!isObjModify(vitalSign,vitalSign.reporter))
//                vitalSign.setObjectId(null);
               if (!isObjModify(vitalSign, vitalSign.reporter)) {
                   return 14;
               }
                vitalSign.staff_modify = theHO.theEmployee.getObjectId();
           }
            String interaction = "";//amp:04/04/2549
            if(vitalSign.getObjectId()==null) 
            {   
                vitalSign.reporter = theHO.theEmployee.getObjectId();
                vitalSign.record_time = theLookupControl.getTextCurrentTime();
                vitalSign.record_date = theLookupControl.getTextCurrentDate();
                theHosDB.theVitalSignDB.insert(vitalSign);
                //amp:16/05/2549
                interaction = intCheckBloodPresureInteraction(vitalSign.pressure);
            }
            else  
            {
                //amp:05/04/2549
                vitalSign.staff_modify = theHO.theEmployee.getObjectId();
                vitalSign.modify_date_time = theLookupControl.intReadDateTime();
                theHosDB.theVitalSignDB.update(vitalSign);
                //amp:16/05/2549
                interaction = intCheckBloodPresureInteraction(vitalSign.pressure);                
            }
            theHO.flagDoctorTreatment = true;
            // �֧������ VitalSign ���ʴ����������§����ѹ���ҷ���Ǩ  sumo 29/08/2549
            theHO.vVitalSign = theHosDB.theVitalSignDB.selectByVisitDesc(theHO.theVisit.getObjectId());
            //amp:04/04/2549
            if(!"".equals(interaction))
            {
                interaction = Constant.getTextBundle("��úѹ�֡������ VitalSign") + " " +
                        Constant.getTextBundle("�������") + " " +
                        Constant.getTextBundle("�¤����ѹ����ջ�ԡ����ҡѺ")+" " + interaction;
                theHS.theVitalSubject.notifyManageVitalSign(interaction,2);
                theSystemControl.setStatus(UseCase.TH_saveVitalSign,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_saveVitalSign,objectid,null,UpdateStatus.COMPLETE);
            }
            else
            {
                interaction = Constant.getTextBundle("��úѹ�֡������ VitalSign") + " " +
                        Constant.getTextBundle("�������");
                theHS.theVitalSubject.notifyManageVitalSign(interaction,1);
                if(vitalSign != null)
                    objectid = vitalSign.getObjectId();
                theSystemControl.setStatus(UseCase.TH_saveVitalSign,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_saveVitalSign,objectid,null,UpdateStatus.COMPLETE);
            }            
            interaction = null;
        }      
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_saveVitalSign,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveVitalSign,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
        return 0;
   }
    
    /**
     *@Author: amp
     *@date: 16/5/2549
     *@see: ��Ǩ�ͺ��Ҥ����ѹ�ռšѺ�ҷ�����������������
     */
    protected String intCheckBloodPresureInteraction(String vitalsignPresure) throws Exception
    {
        if(!theLookupControl.readOption().isUseDrugInteract())
            return "";

        String blood_presure_interaction = "";
        
        if(!"".equals(vitalsignPresure))
        {
            Vector vDrugInteraction;
            String pressure_vital[] = vitalsignPresure.split("/");
            String std_old = "";
            for(int i=0,size=theHO.vOrderItem.size(); i<size; i++)
            {
                OrderItem orderItem = (OrderItem)theHO.vOrderItem.get(i);
                DrugStandardMapItem drugStandardMapItem = theHosDB.theDrugStandardMapItemDB.selectByItem(orderItem.item_code);
                if(drugStandardMapItem!=null)
                {
                    DrugInteraction drugInteraction = theHosDB.theDrugInteractionDB
                        .readBloodPresureInteraction(drugStandardMapItem.drug_standard_id);
                    if(drugInteraction!=null)
                    {  
                        String pressure_drugInteraction[] = drugInteraction.blood_presure.split("/");
                        if(Double.parseDouble(pressure_vital[0]) >= Double.parseDouble(pressure_drugInteraction[0]) ||
                                Double.parseDouble(pressure_vital[1]) >= Double.parseDouble(pressure_drugInteraction[1]))
                        {
                            if("".equals(blood_presure_interaction))
                            {
                                blood_presure_interaction = blood_presure_interaction + " " + drugInteraction.drug_standard_original_description;
                                std_old = drugInteraction.drug_standard_original_id; 
                            }
                            else
                            {
                                if(!std_old.equals(drugInteraction.drug_standard_original_id))
                                {
                                    blood_presure_interaction = blood_presure_interaction + ", " + drugInteraction.drug_standard_original_description;
                                }
                                std_old = drugInteraction.drug_standard_original_id;
                            }
                        }
                        pressure_drugInteraction=null; 
                    }
                }
            }
        }
        return blood_presure_interaction;
    }
    
    /**
     * �ѹ�֡ �ҡ�����ͧ�� ŧ�ҹ������
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     */
    public void savePrimarySymptom(PrimarySymptom primarySymptom)
    {
        Constant.println(UseCase.UCID_savePrimarySymptom);
        String objectid =   null;
        if(theHO.theVisit==null){
            theUS.setStatus(("��س����͡�����·������㹡�кǹ���"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("�����¨���кǹ��������������ö��䢢�������"),UpdateStatus.WARNING);
            return ;
        }
        if(primarySymptom == null){
            theUS.setStatus(("����բ������ҡ�����ͧ��"),UpdateStatus.WARNING);
            return;
       }
        if(primarySymptom.main_symptom.equals("")
            && primarySymptom.current_illness.equals("")){
            theUS.setStatus(("��سҡ�͡���������ҧ���� 1 ��¡��"),UpdateStatus.WARNING);
            return;
        }
        if(primarySymptom.main_symptom.length()>1000
            || primarySymptom.current_illness.length()>1000){
            theUS.setStatus(("��سҡ�͡�����������¡��� 1000 ����ѡ��"),UpdateStatus.WARNING);
            return;
        }
       theConnectionInf.open();
       try{
           // Somprasong comment 07122010 �������� user ���������Է����� ����÷ӧҹ
//           //�ҡ����������褹����ǡѺ���ѹ�֡�кѹ�֡��¡���������ѹ��
           if(theLookupControl.theLO.theOption.vitalsign_secure.equals(Active.isEnable())){
            if(!isObjModify(primarySymptom,primarySymptom.staff_record)){
                return;
//                primarySymptom.setObjectId(null);
//                primarySymptom.staff_modify = theHO.theEmployee.getObjectId();
            }
           }
            
            String date_time = theLookupControl.intReadDateTime();
            primarySymptom.record_date_time = date_time;
            if(primarySymptom.getObjectId()==null ){
                primarySymptom.visit_id = theHO.theVisit.getObjectId();
                primarySymptom.patient_id = theHO.thePatient.getObjectId();
                primarySymptom.active = Active.isEnable();
                primarySymptom.staff_record = theHO.theEmployee.getObjectId();
                theHosDB.thePrimarySymptomDB.insert(primarySymptom);
                if(theHO.vPrimarySymptom==null)
                    theHO.vPrimarySymptom = new Vector();
                theHO.vPrimarySymptom.add(0,primarySymptom);
            }
            else{   
                primarySymptom.staff_modify = theHO.theEmployee.getObjectId();
                theHosDB.thePrimarySymptomDB.update(primarySymptom);                    
            }
            /*��Ǩ�ͺ���ᾷ���Ǩ�������*/
            /////////////////////////////////////////////////////
            theLookupControl.intConfirmDoctorTreatment(false,date_time);
            /////////////////////////////////////////////////////
            //theHC.theVisitControl.checkDoctorTreament(true);
            theHO.vPrimarySymptom = theHosDB.thePrimarySymptomDB.selectByVisitId(theHO.theVisit.getObjectId());
            theHO.flagDoctorTreatment = true;
            theHS.theVitalSubject.notifyManagePrimarySymptom(Constant.getTextBundle("��úѹ�֡�ҡ���Ӥѭ") + " "+
                    Constant.getTextBundle("�������"),UpdateStatus.COMPLETE);
            if(primarySymptom != null)
                objectid = primarySymptom.getObjectId();
            theSystemControl.setStatus(UseCase.TH_savePrimarySymptom,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_savePrimarySymptom,objectid,null,UpdateStatus.COMPLETE);
       }
       catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
    	    theSystemControl.setStatus(UseCase.TH_savePrimarySymptom,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_savePrimarySymptom,objectid,ex,UpdateStatus.ERROR);
       }
       finally{
            theConnectionInf.close();
       }
    }
   /**
     * ź vital sign �͡�ҡ�ҹ������
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     *�����䢢�����㹵��ҧ vital *
     */
    public void deleteVitalSign(Vector vVitalSign,int[] row)
    {
        Constant.println(UseCase.UCID_deleteVitalSign);
        String objectid =   null;
        if(row == null || row.length ==0)
        {
            theUS.setStatus(("��س����͡��¡�� VitalSign"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit == null) 
        {
            theUS.setStatus(("�������ѧ����Դ visit"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("�����¨���кǹ��������������ö��䢢�������"),UpdateStatus.WARNING);
            return ;
        }
        if(!theUS.confirmBox(Constant.getTextBundle("�׹�ѹ����ź��¡�ù�����������"),UpdateStatus.WARNING))
        {
            return ;
        }        
        theConnectionInf.open();
        try{
            for(int i=row.length-1;i>=0;i--)
            {
                VitalSign theVitalSign = (VitalSign)vVitalSign.get(row[i]);
                if(theLookupControl.theLO.theOption.vitalsign_secure.equals(Active.isEnable())){
                if(!isObjModify(theVitalSign,theVitalSign.reporter)){
                    return;
                }
                }
                
                vVitalSign.remove(row[i]);
                //theHosDB.theVitalSignDB.delete(theVitalSign);
                //amp:05/04/2549         
                if(theVitalSign.getObjectId() != null)
                {
                    theHosDB.theVitalSignDB.updateActiveById(theVitalSign.getObjectId());
                }
                
                //amp:03/04/2549 ������ա��ź vitalsign ���仵�Ǩ�ͺ
                if(theLookupControl.readOption().isUseDrugInteract() && !"".equals(theVitalSign.pressure))
                {
                    theHosDB.theOrderDrugInteractionDB.updateBloodPresureByVisitId(theHO.theVisit.getObjectId(), theVitalSign.pressure);
                }
            }
            theHO.vVitalSign = vVitalSign;
            theHS.theVitalSubject.notifyManageVitalSign(Constant.getTextBundle("���ź������ VitalSign") + " " +
                    Constant.getTextBundle("�������"),UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deleteVitalSign,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteVitalSign,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {
            theSystemControl.setStatus(UseCase.TH_deleteVitalSign,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deleteVitalSign,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    /**
     * ź �ҡ�����ͧ�� �͡�ҡ�ҹ������
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     * �ѧ�������ҹ
     
    private String deletePrimarySymptom(PrimarySymptom primarySymptom) 
    {         
            
       theConnectionInf.open();
       try{
           theHosDB.thePrimarySymptomDB.delete(primarySymptom);       
           theStatus = "Complete";
        }
       catch(Exception e)
       {
           e.printStackTrace(Constant.getPrintStream());
           theStatus =  "Error";
       }       
       theConnectionInf.close();
       return theStatus;
    }
    /**
     * �ʴ� �ҡ�����ͧ�� 
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     */
    public Vector listPrimarySymptom() 
    {
        theHO.vPrimarySymptom = listPrimarySymptom(theHO.theVisit.getObjectId());
        return theHO.vPrimarySymptom;
    }
    /**
     * �ʴ� �ҡ�����ͧ�� 
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     */
    public Vector listPrimarySymptom(String vn) 
    {
       Vector result = new Vector();
       theConnectionInf.open();
       try
       {             
            result = theHosDB.thePrimarySymptomDB.selectByVisitId(vn);
       }
       catch(Exception ex)
       {    
           ex.printStackTrace(Constant.getPrintStream());
       }      
       theConnectionInf.close();
       return result;
    }
    /**
     * �ʴ� vital sign 
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     */
    public Vector listVitalSign() 
    {
        theHO.vVitalSign = listVitalSign(theHO.theVisit.getObjectId());
        return theHO.vVitalSign;
    }
    /**
     * �ʴ� vital sign 
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     */
    public Vector listVitalSign(String vid) 
    {
       Vector result = new Vector();
       theConnectionInf.open();
       try{             
            result = theHosDB.theVitalSignDB.selectByVisitDesc(vid);
           }
       catch(Exception ex)
       {    
           ex.printStackTrace(Constant.getPrintStream());
       }
       theConnectionInf.close();
       return result;
    }
    /**
     * �ʴ� vital sign ���§����ѹ���ҷ��ѹ�֡�����§�ҡ������ҡ
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     */
    public Vector listVitalSignOrderByTime(String vid) 
    {
       Vector result = new Vector();
       theConnectionInf.open();
       try{             
            result = theHosDB.theVitalSignDB.selectByVidOrderByTime(vid);
       }
       catch(Exception ex)
       {    
           ex.printStackTrace(Constant.getPrintStream());
       }
       theConnectionInf.close();
       return result;
    }
    /**
     * �ʴ� vital sign ���� vital sign id
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     * �ѧ����ҹ
     */
    public VitalSign readVitalSignByVsId(String vitalSignId) 
    {
      
       VitalSign vs=null;
       theConnectionInf.open();
       try{
           vs = theHosDB.theVitalSignDB.selectByPK(vitalSignId);
        }
       catch(Exception e)
       {
           e.printStackTrace(Constant.getPrintStream());
       }
       theConnectionInf.close();
       return vs;
    }
    /**
     * �ʴ� VitalTemplate ���� vtId
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     * �ѧ�������ҹ
     */
    public VitalTemplate readVitalTemplateByVtId(String vtId) 
    {
       VitalTemplate vt=null;   
       ;
       theConnectionInf.open();
       try{
            vt = theHosDB.theVitalTemplateDB.selectByPK(vtId);                       
       }
       catch(Exception ex)
       {    
		ex.printStackTrace(Constant.getPrintStream());
       }      
       theConnectionInf.close();
       return vt; 
    }
    /**
     * �ʴ� �š�õ�Ǩ��ҧ��� ���� visit_id
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     */
    public Vector listPhysicalExam() 
    {
        theHO.vPhysicalExam = 
            listPhysicalExamByVn(theHO.theVisit.getObjectId());
        return theHO.vPhysicalExam;
    }
    /**
     * �ʴ� �š�õ�Ǩ��ҧ��� ���� visit_id
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     * result = theHosDB.thePhysicalExamDB.selectByVn(vn);
     */
    public Vector listPhysicalExamByVn(String vn) 
    {
       Vector result = null;
       theConnectionInf.open();
       try
       {             
            result = theHosDB.thePhysicalExamDB.selectByVisitId(vn);
       }
       catch(Exception ex)
       {     
           ex.printStackTrace(Constant.getPrintStream());
       }
       theConnectionInf.close();
       return result;
    }
    /**
     * �ʴ� �š�õ�Ǩ��ҧ��� ���� visit_id
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     * @deprecated
     */
    public Vector listPhysicalExamNan() 
    {
        return listPhysicalExamNan(theHO.theVisit,false);
    }
    /**
     * �ʴ� �š�õ�Ǩ��ҧ��� ���� visit_id
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     * @deprecated
     */
    public Vector listPhysicalExamNan(Visit visit,boolean for_display) 
    {
       Vector result = null;
       theConnectionInf.open();
       try{             
            result = theHosDB.thePhysicalExamDB.selectByVnNan(visit.getObjectId());
            if(for_display){
                PhysicalExamNan pen = new PhysicalExamNan();
                result = pen.getForTextArea(result);
            }
       }
       catch(Exception ex)
       {     
           ex.printStackTrace(Constant.getPrintStream());
       }
       theConnectionInf.close();
       return result;
    }
    /**
     * �ѹ�֡ �š�õ�Ǩ��ҧ��� 
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     */
    public void savePhysicalExam(Vector phyex) 
    {
        Constant.println("public void savePhysicalExam(Vector phyex) ");
        Constant.println(UseCase.UCID_savePhysicalExam);
        String objectid =   null;
        if(theHO.theVisit==null)
        {
            theUS.setStatus(("��س����͡�����·������㹡�кǹ���"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.is_discharge_doctor.equals("1"))
        {
            theUS.setStatus(("��س����͡�����·���ѧ����˹��·ҧ���ᾷ��"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("�����¨���кǹ��������������ö��䢢�������"),UpdateStatus.WARNING);
            return ;
        }
        if(phyex==null)
        {   
            theUS.setStatus(("�ѧ����բ����š�õ�Ǩ��ҧ���"),UpdateStatus.WARNING);
            return;
        }
        theConnectionInf.open();
        try{
            String date = theLookupControl.intReadDateTime();
            BodyOrgan bodyOrgan;
            theHosDB.thePhysicalExamDB.deleteByVid(theHO.theVisit.getObjectId());
            for(int i=phyex.size()-1; i>=0; i--)
            {
                PhysicalExam pscea = (PhysicalExam)phyex.get(i);
                pscea.detail = pscea.detail.trim();
                //pscea.physical_body = "";
                if(!pscea.detail.equals(""))
                {
                    String digit = "0" + i;
                    if(digit.length()==3)
                        digit.substring(1);
                    pscea.date_time = date.substring(0,date.lastIndexOf(":")) + digit;
                    pscea.executer = theHO.theEmployee.getObjectId();
                    pscea.visit_id = theHO.theVisit.getObjectId();
                    pscea.patient_id = theHO.thePatient.getObjectId();
                    pscea.detail = Gutil.CheckReservedWords(pscea.detail);
                    if(pscea.getObjectId()==null)
                    {
                        theHosDB.thePhysicalExamDB.insert(pscea);
                        //amp:10/04/2549 ��������������㹵��ҧ base �óշ������������й��
                        //henbe: comment ����͹������ҡѹ�����������ŧ� �����ա���
                        bodyOrgan = theHosDB.theBodyOrganDB.selectByName(pscea.physical_body);
                        if(bodyOrgan==null && !pscea.physical_body.equals("")) 
                        {
                            bodyOrgan = new BodyOrgan();
                            bodyOrgan.description = pscea.physical_body;                            
                            theLookupControl.intSaveBodyOrgan(bodyOrgan); 
                        }
                    }
                }
                else  
                {
                    phyex.remove(pscea);
                }
            }
            bodyOrgan = null;
            /*��Ǩ�ͺ���ᾷ���Ǩ�������*/
            /////////////////////////////////////////////////////
            theLookupControl.intConfirmDoctorTreatment(false,date);
            /////////////////////////////////////////////////////
            theHO.vPhysicalExam = theHosDB.thePhysicalExamDB.selectByVisitId(theHO.theVisit.getObjectId());
            //��Ǩ�ͺ���ᾷ���Ǩ�������
            //checkDoctorTreament(true);
            theHO.flagDoctorTreatment = true;
            theHS.theVitalSubject.notifyManagePhysicalExam(Constant.getTextBundle("�ѹ�֡�����š�õ�Ǩ��ҧ���") + " "+
                    Constant.getTextBundle("�������"),UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_savePhysicalExam,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_savePhysicalExam,objectid,null,UpdateStatus.COMPLETE);
        }   
        catch(Exception ex)
        {    
            theSystemControl.setStatus(UseCase.TH_savePhysicalExam,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_savePhysicalExam,objectid,ex,UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally
        {
            theConnectionInf.close();  
        }
    }
    
    /**
     * ź �š�õ�Ǩ��ҧ��� 
     * �Ѵ�͡�����������´ uc ���� 13/11/47 
     */
    public void deletePhysicalExam(Vector phyex,int[] row) 
    {
        Constant.println(UseCase.UCID_deletePhysicalExam);
        String objectid =   null;
        if(theHO.theVisit==null){   
            theUS.setStatus(("��س����͡�����·������㹡�кǹ���"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("�����¨���кǹ��������������ö��䢢�������"),UpdateStatus.WARNING);
            return ;
        }
        if(row.length == 0) {
            theUS.setStatus(("��س����͡��¡�÷���ͧ���ź"),UpdateStatus.WARNING);
            return;
        }
        if(!theUS.confirmBox(Constant.getTextBundle("�׹�ѹ����ź��¡�ù�����������"),UpdateStatus.WARNING)){
            return ;
        }
        theConnectionInf.open();
        try{
            for(int i=row.length-1;i>=0;i--){
                PhysicalExam pe = (PhysicalExam)phyex.get(row[i]);
                theHosDB.thePhysicalExamDB.delete(pe); 
                phyex.remove(row[i]);
            }
            theHO.vPhysicalExam = phyex;
            theHS.theVitalSubject.notifyManagePhysicalExam(Constant.getTextBundle("ź�����š�õ�Ǩ��ҧ���") + " "+
                    Constant.getTextBundle("�������"),UpdateStatus.COMPLETE);
            if(theHO.theVisit!=null)
                objectid = theHO.theVisit.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deletePhysicalExam,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deletePhysicalExam,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theSystemControl.setStatus(UseCase.TH_deletePhysicalExam,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deletePhysicalExam,objectid,ex,UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
    }
    
    /**
     * ��� ����ԹԨ����ä 
     */
    public void saveDxNote(String str)
    {
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("�����¨���кǹ��������������ö��䢢�������"),UpdateStatus.WARNING);
            return ;
        }
        theConnectionInf.open();
        try{
            str = Gutil.CheckReservedWords(str);
            theHO.theVisit.diagnosis_note = str;
            theHosDB.theVisitDB.updateDxNote(theHO.theVisit);
            //��� notify �͡ ����� refresh ᶺ�ҡ���纻���㹪�ͧ Dx sumo 31/8/2549
            //theHS.theVitalSubject.notifySaveDiagDoctor("��úѹ�֡�����˵آͧᾷ���������",UpdateStatus.COMPLETE);
         }
        catch(Exception ex) {    
            theUS.setStatus(Constant.getTextBundle("��úѹ�֡�����˵آͧᾷ��") + " " +
                    Constant.getTextBundle("�Դ��Ҵ"),UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     * ��� ����ԹԨ����ä 
     * �Ѵ�͡�����������´ uc ���� 13/11/47
     * @author ��  
     * @author ���
     * @author ����
     * @author ��
     * @deprecated 300 ��÷Ѵ �Ѻ��͹�ҡ�Թ������ҡ���ҡ�ҡ henbe comment �����
     * @aothor �� ���繵�ͧ��������鹪Ժ��������
     * Object �������Ǣ�ͧ   
            //theHosDB.theMapVisitDxDB.deleteByVid(theHO.theVisit.getObjectId()); 
            //�óշ�� Dx �դ���繪�ͧ��ҧ���ӡ�� update ����� 0 ������
            //����¡��ԡ��¡�÷�����������͡ŧ�
     * Visit  MapVisitDx DxTemplate Guide DiagIcd10 vHealthEducation       
     * //�óշ�� Dx ������ͧ��ҧ���ӡ������բ����� dx template �����
            //�ӡ��ǹ�ٻ��������Ҥ�ҷ������� DxTemplate ���������� doctor_dx(����Ҩҡ��á�͡) ����
            //���������� insert ���������ʴ���Ҷ١¡��ԡ�����
     * @deprecated bad pattern at Clinic clinic = theHosDB.theClinicDB.selectByCode("00:03");
     */
    public void saveDiagDoctor(Visit theVisit,DiagDoctorClinic ddc)
    {
        Constant.println("public void saveDiagDoctor(Visit theVisit,DiagDoctorClinic ddc)");
        Constant.println(UseCase.UCID_saveDx);
        String objectid =   null;
        if(theVisit!=null)
            objectid = theVisit.getObjectId();
        if(theVisit == null){
            theUS.setStatus(("��س����͡�����·������㹡�кǹ���"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.isDischargeDoctor())
        {
            theUS.setStatus(("�����¨�˹��·ҧ���ᾷ�������������ö��䢢�������"),UpdateStatus.WARNING);
            return ;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("�����¨���кǹ��������������ö��䢢�������"),UpdateStatus.WARNING);
            return ;
        }
//        if(theVisit.doctor_dx.length()==0){
//            theUS.setStatus("��سҡ�͡��ҡ�͹�ѹ�֡ DX",UpdateStatus.WARNING);
//            return;
//        }
        //��Ǩ�ͺ���ᾷ��ŧ Diagnosis ����������Ҷ�ҫ�ӡ�����ͧ�ѹ�֡ Diag ������ǡѹ
        theConnectionInf.open();
        try{
            String date_time = theLookupControl.intReadDateTime();
//            theVisit.diagnosis_note = Gutil.CheckReservedWords(theVisit.diagnosis_note);
//            theVisit.doctor_dx = Gutil.CheckReservedWords(theVisit.doctor_dx);
            ///////////////////////////////////////////////////////////////////////////////////////

            if(Gutil.setSelect(this.theLookupControl.readOption().auto_diag_icd10)
                && theVisit.doctor_dx.length()==0)
            {
                for(int i =0 ;i <theHO.vDxTemplate.size();i++ )
                {
                    DxTemplate dxt = (DxTemplate)theHO.vDxTemplate.get(i);
                    String guide_id = theHosDB.theMapVisitDxDB.updateActive(theHO.theVisit.getObjectId()
                        ,dxt.getObjectId(),"0",theHO.theEmployee.getObjectId(),date_time);
                    if(!guide_id.equals(""))
                        theHosDB.theGuideAfterDxTransactionDB.deleteByGuideid(guide_id);

                    theHosDB.theDiagIcd10DB.deleteByVidIcd10(theVisit.getObjectId()
                        , dxt.icd_code, theHO.date_time, theHO.theEmployee.getObjectId());
                    theHO.vDiagIcd10 = theHosDB.theDiagIcd10DB.selectByVNNoSort(theHO.theVisit.getObjectId());
                }
                theHO.vGuide = new Vector();
            }
            ///////////////////////////////////////////////////////////////////////////////////////
            String doctor_dx = theVisit.doctor_dx;
            theHosDB.theMapVisitDxDB.updateInActiveByVisit(
                    theHO.theVisit.getObjectId()
                    ,theHO.date_time
                    ,theHO.theEmployee.getObjectId());
                
            theHO.theDxTemplateNew = false;
            for(int i=0;!doctor_dx.equals("") && i<theHO.vMapVisitDx.size();i++)
            {   
                boolean sameindx = false;
                MapVisitDx mvd = (MapVisitDx)theHO.vMapVisitDx.get(i);
                if(mvd.getObjectId()==null)
                {
                    theHO.theDxTemplateNew = true;
                    theHosDB.theMapVisitDxDB.insert(mvd);
                }      
                else
                    theHosDB.theMapVisitDxDB.update(mvd);
                
                theHO.vDxTemplate = theHosDB.theDxTemplate2DB.selectByVid(theHO.theVisit.getObjectId());
                /////////////////////////////////////////////////////////////////
                // ��㹡�õ�Ǩ�ͺ��ҷ�����Ҩҡ���ŧ Dx ��ӡѺ����������������
                // henbe comment
//                    for(int j = 0;theHO.vGuide!=null && j<theHO.vGuide.size(); j++)
//                    {
//                        GuideAfterDxTransaction gu = new GuideAfterDxTransaction();
//                        gu.guide = ((String)theHO.vGuide.get(j));
//                        if(dxt.guide_after_dx.equals(gu.guide) && !gu.guide.equals(""))
//                        {
//                            gu.health_head = "";
//                            gu.visit_id = theVisit.getObjectId();
//                            theHosDB.theGuideAfterDxTransactionDB.insert(gu);
//                        }
//                    }
            /////////////////////////////////////////////////////////////////
                if(Gutil.setSelect(this.theLookupControl.readOption().auto_diag_icd10))
                {   
                   // ��Ǩ�ͺ��� ICD10 �����ҡ��� Map ������� DB ��������  sumo 29/08/2549
                   // ��ҵ�Ǩ�ͺ���������� DB �����ѹ�֡ ICD10 �ѵ��ѵ���  sumo 29/08/2549
                   ICD10 theICD10 = theHosDB.theICD10DB.selectByCode(mvd.visit_diag_map_icd);
                   if(theICD10 != null) {
                       //�Դ��õ�Ǩ�ͺ������ç�Ѻ����������͡�ҡ˹�Ҩ� vitalSign
//                       if(ddc.clinic_id.equals(Clinic.MED)
//                       && Integer.parseInt(theVisit.patient_age) < 16){
//                           Clinic clinic = theHosDB.theClinicDB.selectByCode("00:03");
//                           //henbe ��ѭ��੾��˹��
//                           if(clinic!=null)
//                                ddc.clinic_id = clinic.getObjectId();
//                       }
                       DiagIcd10 dx10 = theHO.initDiagICD10(mvd,ddc.doctor_id,ddc.clinic_id);
                       theDiagnosisControl.intSaveDiagIcd10(dx10,theHO.vDiagIcd10,theUS,false);
                   }
                   theHO.vDiagIcd10 = theHosDB.theDiagIcd10DB.selectByVidSort(theHO.theVisit.getObjectId());
//                    theHosDB.theDiagIcd10DB.deleteByVidIcd10(theVisit.getObjectId()
//                        , dxt.icd_code, theHO.date_time, theHO.theEmployee.getObjectId());
                }
            }
            /////////////////////////////////////////////////////
            theLookupControl.intConfirmDoctorTreatment(false,date_time);
            /////////////////////////////////////////////////////
//            theHO.theVisit = theVisit;
            //pu:�红�����ᾷ�����Ǩ �͹ᾷ��ѹ�֡ DX
            theHO.theVisit.visit_patient_self_doctor = this.theHO.theEmployee.getObjectId();
            theHosDB.theVisitDB.updateDiagnosis(theHO.theVisit);
            theHS.theVitalSubject.notifySaveDiagDoctor(GuiLang.setLanguage("��úѹ�֡�š�õ�Ǩ�ѡ��") + " " +
                    GuiLang.setLanguage("�������"),UpdateStatus.COMPLETE);
            theSystemControl.setStatus(UseCase.TH_saveDx,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveDx,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex)
        {    
            theSystemControl.setStatus(UseCase.TH_saveDx,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveDx,objectid,ex,UpdateStatus.ERROR);
        }
        finally
        {
            theConnectionInf.close();
        }
    }
    public DxTemplate readDxTemplateByDes(String des)
    {
        DxTemplate dxTemplate = null;
        theConnectionInf.open();
        try
        {
            dxTemplate = theHosDB.theDxTemplate2DB.readByDes(des);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return dxTemplate;
    }
 
    /**
     *�����¡�� order �ѵ��ѵ� ��� Dx ����к���������
     *@author Pu
     *@date 11/08/2549 
     *not @deprecated ����¹�繡�ä� item ����᷹������ա�����͡�������˹�� order
     */
    public Vector listItemByDxTemplate()
    {
//        Constant.println("public Vector listItemByDxTemplate()");
        if(theHO.vMapVisitDx.isEmpty())
            return new Vector();
        
        theConnectionInf.open();
        try{
            return intOrderItemByDxTemplate(theHO.vMapVisitDx);
        }
        catch(Exception e){
            e.printStackTrace(Constant.getPrintStream());
            return new Vector();
        }
        finally{
            theConnectionInf.close();
        }
        
    }
    public Vector intOrderItemByDxTemplate(Vector vMvd)throws Exception
    {
        Vector itemV = new Vector();
        for(int i=0,size=vMvd.size();i<size;i++)
        {
            //������¡�� ItemDx �ҡ Dx ���ᾷ��ŧ
            MapVisitDx mapdx = (MapVisitDx)vMvd.get(i);
            if(mapdx.visit_diag_map_active.equals(Active.isEnable()))
            {
                Vector vItemDx = theHosDB.theDxTemplateMapItemDB.selectByDtid(mapdx.dx_template_id);
                //��Ǩ�ͺ�Ѻ order ������������� ��ҫ�ӡ���������¡������
                DxTemplateMapItem:
                for(int j=0;j<vItemDx.size();j++)
                {
                    DxTemplateMapItem itemdx = (DxTemplateMapItem)vItemDx.get(j);
//                    for(int k=0;k<theHO.vOrderItem.size();k++){
//                        OrderItem oi = (OrderItem)theHO.vOrderItem.get(k);
//                        if(oi.item_code.equals(itemdx.item_id))
//                            continue DxTemplateMapItem;
//                    }
                    Item it = theHosDB.theItemDB.selectByPK(itemdx.item_id);
                    if(it!=null)
                        itemV.add(it);
//                    CategoryGroupItem ci = theHosDB.theCategoryGroupItemDB.selectByPK(it.item_group_code_category);
//                    ItemPrice ip = theOrderControl.intReadItemPriceByItemID(itemdx.item_id);
//                    OrderItem or = theHO.initOrderItem(it,ci,ip,theHO.date_time);
//                    OrderItemDrug oid = null;
//                    if(or.isDrug())
//                    {
//                        Drug drug = theHosDB.theDrugDB.selectByItem(it.getObjectId());
//                        oid = theHO.initOrderItemDrug(it,drug);
//                        oid.status = "0";
//                        or.qty = drug.qty;
//                    }
//                    theOrderControl.intSaveOrderItem(or,oid,theHO.date_time);
//                    theHO.vOrderItem = theHosDB.theOrderItemDB.selectByVisitId(theHO.theVisit.getObjectId());
                }
            }
        }
        return itemV;
    }
 
    /**
     * ��㹡�õ�Ǩ�ͺ��� dxTemplate ��������繡���� 2(���������ICD10) ������� ��� ��ͧ���������㹵��ҧ mapdiag  ���
     * ������� Dx �ͧᾷ��������� ��� ����������觤���� true ��������ҧ�����ҧ˹�������������� ���觤���� false
     * @param dxTemplate �� Object �ͧ DxTemplate(DxTemplate)
     * @return boolean �� true ����ʴ� Dialog �� ����� false �������ʴ� Dialog
     * @author padungrat(tong)
     * @date 21/04/2549,16:07
     */
    public boolean checkShowDialogChooseICD10FormTemplate(DxTemplate dxTemplate)
    {
        boolean result = false;
        theConnectionInf.open();
        try{
            if(dxTemplate != null)
            {   //��Ǩ�ͺ��� �繡���� 2(���������ICD10) ������� ��� ��ͧ�������㹵��ҧ t_visit_diag_map ��� ��ͧ������� dx �ͧᾷ�����
                boolean check_mvd = theHosDB.theMapVisitDxDB.checkDataInDB(
                        theHO.theVisit.getObjectId(),dxTemplate.getObjectId(),Active.isEnable());
                
                if(dxTemplate.icd_type.equals(Dxtype.getComorbidityDiagnosis()) && 
                   ! check_mvd &&
                    0 <= theHO.theVisit.doctor_dx.indexOf(dxTemplate.description)) {
//                    Constant.println("--------------------------------TRUE-----------------------------------");
                    result = true;
                }
            }
        }
        catch(Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally {
            theConnectionInf.close();
        }
        return result;
    }
    
    /**
     * ��㹡�õ�Ǩ�ͺ Dx �������ͧ�������դ� ����˹� �¨е�Ǩ�ͺ����ͨеѴ�ӹ���͡�
     * �¤�����ҹ�鹵�ͧ������� ˹�� ���� ������ѧ
     * @param word �� String ����ͧ��è���
     * @return �� String �ͧ�����ŷ��١��駤ӷ���˹��͡�����
     * @author padungrat(tong)
     * @date 19/04/2549,12:14
     */
    public String checkWordForDx(String word)
    {
        String[] aword = {"with","and"};
        String[] data = word.split(" ");
        int i =0,j =0;
        word = "";
        boolean result = true;
        for(j = 0; j < data.length;j++) 
        {
            if(j==0 || j == (data.length-1))
            {
                for(i =0 ; i <aword.length ; i++)
                {   
                    if(aword[i].equalsIgnoreCase(data[j].trim()))
                    {
                        data[j] = "";
                        result = false;
                    }
                }
            }
            word = word + data[j] + " ";
        }
        data = null;
        aword = null;
        //��Ң������ѧ����繨�ԧ �е�ͧ�Ң������ա����
        if(!result)
        {   
            word = checkWordForDx(word.trim());
        }
        
        return word.trim();
    }
    /** 
     * ��㹡���ҵ��˹觢ͧ������� vector ���ͨзӡ��ź
     * @param diagIcd10 �� Object �ͧ DiagIcd10 �����㹡��ŧ����
     * @return int �繤��˹觢ͧ�����ŷ�� Vector ��� return �� -1 ������������� Vector
     * @author padungrat(tong)
     * @date 24/03/49,16:25
     */
    private int checkIndexDiagICD10(DiagIcd10 diagIcd10)
    {   int result = -1;
        if(diagIcd10 != null)
        {   DiagIcd10 dicd10 = null;
            if(theHO.vDiagIcd10 != null)
            {
                for(int i =0,size=theHO.vDiagIcd10.size() ; i < size;i++)
                {
                    dicd10 = (DiagIcd10)theHO.vDiagIcd10.get(i);
                    if(diagIcd10.visit_id.equalsIgnoreCase(dicd10.visit_id))
                    {
                        if(diagIcd10.icd10_code.equalsIgnoreCase(dicd10.icd10_code))
                        {
                            result = i;
                            break;
                        }
                    }
                    dicd10 = null;
                }
              dicd10 = null;  
            }
        }
        return result ;
    }
    
 
    /**
     *��㹡���Ң����ŷ���ա�� map dx �ҡ���ҧ �� �����Ţ visit_id ��� ʶҹ�
     *@param visit_id �� String �ͧ ������ѡ�ͧ���ҧ t_visit
     *@return �� Vector �ͧ Object MapVisitDx
     *@auther padungrat(tong)
     *@date 23/03/49
     */
    public Vector selectMapVisitDxByVisitID(String visit_id)
    {   Vector vc = null;
        
           
        theConnectionInf.open();
        try
        {
            if(visit_id != null)
            {
                vc = theHosDB.theMapVisitDxDB.selectMapVisitDxByVisitID(visit_id,Active.isEnable());
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
        return vc;
    }
    /**
     *�������ҹ
     *�Ѵ�͡�����������´ uc ���� 27/11/47 
     * @deprecated henbe unused
     */
    public String deleteMapVisitDx(MapVisitDx mapVisitDx) 
    {       
       if(mapVisitDx.visit_id==null || mapVisitDx.visit_id.equals("")) return null;
       theConnectionInf.open();
       try{
           theHosDB.theMapVisitDxDB.delete(mapVisitDx);           
           theStatus = "Complete";
        }
       catch(Exception e)
       {
           e.printStackTrace(Constant.getPrintStream());
           theStatus =  "Error";
       }
       theConnectionInf.close();
       return theStatus;
    }
    
    /**
     * list ��¡�� Vitalsign ��� visit_id
     * �Ѵ�͡�����������´ uc ���� 27/11/47 
     */

    public Vector listWeightByVisitID(String visit_id) {
        Vector vt=null;   
       theConnectionInf.open();
       try{
            vt = theHosDB.theVitalSignDB.selectWeightByVisitID(visit_id);                       
       }
       catch(Exception ex)
       {    
		ex.printStackTrace(Constant.getPrintStream());
       }
            theConnectionInf.close();
       return vt; 
    }
    /**
     * �����¡�õ�����駷�����Ѻ��ԡ��
     * @param  visit
     * @return vector �ͧ Wound
     * @author kingland
     * @date 15/06/2549
     */
    public Vector listWoundByVisitID(Visit visit)
    {
        if(visit == null){
            theUS.setStatus(("��س����͡�����·������㹡�кǹ���"), 2);
            return null;
        }
        return listWoundByVisitID(visit.getObjectId());
    }
    /**
     * �����¡�õ�����駷�����Ѻ��ԡ��
     * @param  visit_id
     * @return vector �ͧ Wound
     * @author henbe
     * @date 5/09/2549
     */
    public Vector listWoundByVisitID(String visit_id)
    {
        theConnectionInf.open();
        try{
            if(visit_id.equals(""))
                return new Vector();
            else
                return theHosDB.theWoundDB.selectByVisit(visit_id);
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
     * ź�Ҵ��
     * @param  -
     * @return -
     * @author kingland
     * @date 15/06/2549
     */
    
    public int deleteWoundV(Vector woundv,UpdateStatus theUS)
    {
        if(woundv == null) {
            theUS.setStatus(("��س����͡��¡�úҴ�ŷ���ͧ���ź"),UpdateStatus.WARNING);
            return 0;
        }
        int result=0;
        theConnectionInf.open();
        try{
            for(int i=0;i<woundv.size();i++){
                Wound wound = (Wound)woundv.get(i);
                result = theHosDB.theWoundDB.delete(wound);
            }
            theUS.setStatus(Constant.getTextBundle("ź�����źҴ��") + " "+
                    Constant.getTextBundle("�������"),theUS.COMPLETE);
            return result;
        }
        catch(Exception ex){
            theUS.setStatus(Constant.getTextBundle("ź�����źҴ��") + " "+
                    Constant.getTextBundle("�Դ��Ҵ"),theUS.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
            return 0;
        }
        finally{
            theConnectionInf.close();
        }
    }
    public int deleteWound(Wound wound,UpdateStatus theUS)
    {
        Constant.println(UseCase.UCID_deleteWound);
        String objectid =   null;
        if(wound == null) {
            theUS.setStatus(("��س����͡��¡�úҴ�ŷ���ͧ���ź"),UpdateStatus.WARNING);
            return 0;
        }
        if(!theUS.confirmBox(Constant.getTextBundle("��ͧ���ź��¡�úҴ��������� ?"),UpdateStatus.WARNING)){
            return 0;
        }
        
        int result=0;
        theConnectionInf.open();
        try{
            result = theHosDB.theWoundDB.delete(wound);
            theUS.setStatus(Constant.getTextBundle("ź�����źҴ��") + " "+
                    Constant.getTextBundle("�������"),theUS.COMPLETE);
            if(wound != null)
                objectid = wound.getObjectId();
            theSystemControl.setStatus(UseCase.TH_deleteWound,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_deleteWound,objectid,null,UpdateStatus.COMPLETE);
            return result;
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_deleteWound,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_deleteWound,objectid,ex,UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
            return 0;
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     * �ѹ�֡�Ҵ��
     * @param  Wound
     * @return -
     * @author kingland
     * @date 15/06/2549
     */
    public int saveWound(Vector v,UpdateStatus us)
    {
        int result = 0;
        theConnectionInf.open();
        try{
            theLookupControl.intReadDateTime();
            for(int i=0,size=v.size();v!=null&&i<size;i++){          
                ImagePoint img = (ImagePoint)v.get(i);
                Wound wound = (Wound)img.getObject();              
                result = intSaveWound(wound,us);            
            }
            us.setStatus(Constant.getTextBundle("��úѹ�֡�����źҴ��") + " "+
                    Constant.getTextBundle("�������"),theUS.COMPLETE);
        }
        catch(Exception ex){
            us.setStatus(("�������ö�ѹ�֡�����źҴ����"),theUS.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
        return result;
    }
    public int saveWound(Wound wound,UpdateStatus us)
    {
        Constant.println(UseCase.UCID_saveWound);
        String objectid =   null;
        int result = 0;
        theConnectionInf.open();
        try{
            intSaveWound(wound,us);
            us.setStatus(Constant.getTextBundle("��úѹ�֡�����źҴ��") + " "+
                    Constant.getTextBundle("�������"),theUS.COMPLETE);
            if(wound != null)
                objectid = wound.getObjectId();
            theSystemControl.setStatus(UseCase.TH_saveWound,UpdateStatus.COMPLETE,null);
            theSystemControl.saveLog(UseCase.UCID_saveWound,objectid,null,UpdateStatus.COMPLETE);
        }
        catch(Exception ex){
            theSystemControl.setStatus(UseCase.TH_saveWound,UpdateStatus.ERROR,ex);
            theSystemControl.saveLog(UseCase.UCID_saveWound,objectid,ex,UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
        return result;
    }    
    public int intSaveWound(Wound wound,UpdateStatus us)throws Exception
    {
        int result = 0;
        if(wound == null){
            us.setStatus(("�������ö�ѹ�֡�����źҴ����"),theUS.WARNING);
            return result;
        }
        if(wound.visit_id.equals("")){
            us.setStatus(("��س����͡��¡���غѵ��˵ط���ͧ��úѹ�֡�Ҵ��"),theUS.WARNING);
            return result;
        }
        if(wound.wound_type == null || wound.wound_type.equals(""))  {
            us.setStatus(("��س��кت�Դ�ͧ�Ҵ��"),theUS.WARNING);
            return result;
        }
        //��ͧ�Ѻ�Ҩҡ˹�Ҩ͵͹��������͡������
//            wound.patient_id = theHO.thePatient.getObjectId();
//            wound.visit_id = theHO.theVisit.getObjectId();
        if(wound.getObjectId() == null){
            wound.staff_record = theHO.theEmployee.getObjectId();
            wound.record_date_time = theHO.date_time;
            result = theHosDB.theWoundDB.insert(wound);
        }
        else{
            wound.staff_modify = theHO.theEmployee.getObjectId();
            wound.modify_date_time = theHO.date_time;
            result = theHosDB.theWoundDB.update(wound);
        }
        return result;
    }
    public void closeAllBalloon(){
        this.theHS.theBalloonSubject.notifyCloseBalloon("", UpdateStatus.NORMAL);
    }

    public String readVitalHeight(String visit_id) {
        theConnectionInf.open();
        try{
            ResultSet rs = theConnectionInf.eQuery("select visit_vital_sign_height " +
                    "from t_visit_vital_sign where t_visit_id = '"+ visit_id +"'" +
                    " and visit_vital_sign_height<>''");
            String height = "";
            while(rs.next()){
                height = rs.getString(1);
            }
            return height;
        }
        catch(Exception ex){
            ex.printStackTrace(Constant.getPrintStream());
            return "";
        }
        finally{
            theConnectionInf.close();
        }
    }
}
