/*
 * VitalControl.java
 *
 * Created on 20 ตุลาคม 2546, 12:14 น.
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
 *ไม่มีคำบรรยาย
 *
    เพิ่มรายการยาที่แพ้ โดยยังไม่เพิ่มในฐานข้อมูล pu
    เพิ่ม Dx ใน Text field ก่อน save ลงฐานข้อมูล pu
    amp:20/02/2549  
 *  จะมีการเพิ่มข้อมูลลงใน vector of vDxTemplate ก่อนเพื่อจะได้เช็คได้ว่าจะต้องแสดง icd dialog ให้เลือกหรือเปล่า
 *  จะมีการเพิ่มข้อมูลลงใน vector of vMapVisitDx ด้วยเพื่อจะได้สร้างตัวแปรรอรับข้อมูลจาก icd dialog เลย
 *  
 */
    public void addDxTemplate(DxTemplate dx)
    {
        Constant.println("_________________________public void addDxTemplate(DxTemplate dx)");
        
        if(theHO.theVisit==null){
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return;
        }
        if(dx != null){   
            if(theHO.vGuide==null)
                theHO.vGuide = new Vector(); 
            
            boolean same = false;
            //ตรวจสอบว่าซ้ำกับที่มีอยู่หรือเปล่า
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
                theUS.setStatus(("บันทึกการวินิจฉัยโรคซ้ำกับรายการก่อนหน้า"),UpdateStatus.WARNING);
                return;
            }
            Constant.println(theHO.vMapVisitDx.size());
//            Constant.println("_________________________1public void addDxTemplate(DxTemplate dx)" + dx.description);
            MapVisitDx mvd = theHO.initMapVisitDx(dx);
            if(mvd!=null){
                theHO.vDxTemplate.add(dx);
                theHO.vMapVisitDx.add(mvd);
            }
            //การเพิ่มข้อมูล vDxTemplate
            if(!"".equals(dx.guide_after_dx)) 
                theHO.vGuide.addElement(dx.guide_after_dx); 
                
            theHS.theVitalSubject.notifyAddDxTemplate(Constant.getTextBundle("บันทึกการวินิจฉัยโรค") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
        }
    }
    
/**
 *ไม่มีคำบรรยาย
 *
    เพิ่มรายการยาที่แพ้ โดยยังไม่เพิ่มในฐานข้อมูล pu
    เพิ่ม Dx ใน Text field ก่อน save ลงฐานข้อมูล pu
    amp:20/02/2549  
 *  จะมีการเพิ่มข้อมูลลงใน vector of vDxTemplate ก่อนเพื่อจะได้เช็คได้ว่าจะต้องแสดง icd dialog ให้เลือกหรือเปล่า
 *  จะมีการเพิ่มข้อมูลลงใน vector of vMapVisitDx ด้วยเพื่อจะได้สร้างตัวแปรรอรับข้อมูลจาก icd dialog เลย
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
            theHS.theVitalSubject.notifyAddDxTemplate(Constant.getTextBundle("บันทึกการวินิจฉัยโรค") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
        } catch (Exception ex) {
            ex.printStackTrace(Constant.getPrintStream());
            theUS.setStatus(Constant.getTextBundle("การบันทึก Dx") + " " +
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
        }
        finally{
            theConnectionInf.close();
        }
        
    }
    /**
     *@Author: amp
     *@date:07/04/2549
     *@see: เพิ่มการตรวจร่างกายลงใน Vector ก่อน save ลงฐานข้อมูล
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
    
    /*เพิ่มอาการเบื้องต้น และอาการสำคัญ ใน TextArea ก่อน Save ลงฐานข้อมูล pu*/
    public void addPrimarySymptom(VitalTemplate theVitalTemplate)
    {
        theHO.theVitalTemplate = theVitalTemplate;
        theHS.theVitalSubject.notifyAddPrimarySymptom(Constant.getTextBundle("การเพิ่มอาการสำคัญ") + " " +
                Constant.getTextBundle("เสร็จสิ้น"), UpdateStatus.COMPLETE);
    }
    /**
     * ตรวจสอบว่า ข้อมูลนี้ผู้ใช้คนนี้เป็นผู้บันทึกหรือไม่ หากไม่ใช่โปรแกรมจะถามรหัสผ่านเพื่อแก้ไขข้อมูลั้นให้
     * ตามที่เปลี่ยนแปลง
     * แต่ผู้ใช้มีความต้องการเพิ่มเติมคือให้นำเอาข้อมูลก่อนหน้ามาเป็นค่าตั้งต้นให้ด้วยเมื่อ
     * เขาแก้ไขก็จะบันทึกข้อมูลเป็นชื่อของเขาแทนใน Object ใหม่
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
            theUS.setStatus(("คุณไม่มีสิทธิแก้ไขรายการที่บันทึกโดยผู้ใช้คนอื่น"),UpdateStatus.WARNING);
            return false;
//            Employee checkE = theHosDB.theEmployeeDB.selectByPK(recorder);
//            boolean retb = DialogPasswd.showDialog(theHO,theUS,checkE.employee_id,checkE.password,false);
//            if(!retb) {
//                theUS.setStatus("คุณไม่มีสิทธิในการแก้ไขข้อมูลรายการนี้",UpdateStatus.WARNING);
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
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return ;
        }
        if(row == null || row.length == 0){
            theUS.setStatus(("กรุณาเลือกรายการอาการเบื้องต้น"),UpdateStatus.WARNING);
            return;
        }
        if(!theUS.confirmBox(("ยืนยันที่จะลบรายการนี้ใช่หรือไม่"),UpdateStatus.WARNING)){
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
            theHS.theVitalSubject.notifyManagePrimarySymptom(Constant.getTextBundle("การลบข้อมูลการตรวจร่างกาย") +" "+
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
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
     * บันทึก v/s ลงฐานข้อมูล
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
     */
    public int saveVitalSign(VitalSign vitalSign) 
    {
        //ค่ามาตรฐานเหล่านี้ได้จากการถามหมอก้อง
        Constant.println(UseCase.UCID_saveVitalSign);
        String objectid =   null;
        if(theHO.theVisit==null) 
        {
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return 1;
        }
        if(theHO.theVisit.isDischargeDoctor())
        {
            theUS.setStatus(("ผู้ป่วยจำหน่ายทางการแพทย์แล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return 1;
        }
        if(vitalSign.weight.equals("")  && vitalSign.height.equals("")
        && vitalSign.pressure.equals("")&& vitalSign.puls.equals("")
        && vitalSign.res.equals("")     && vitalSign.temp.equals("")
        && vitalSign.waistline.equals("")        )
        {
            theUS.setStatus(("กรุณากรอกข้อมูลอย่างน้อย 1 รายการ"),UpdateStatus.WARNING);
            return 2;
        }
        if(!vitalSign.weight.equals(""))
        {
            float weight = Float.parseFloat(vitalSign.weight);
            if(weight<0 || weight>200){
                theUS.setStatus(("น้ำหนักควรอยู่ในช่วง 0-200"),UpdateStatus.WARNING);
                return 3;
            }
        }
        if(!vitalSign.height.equals(""))
        {
            float height = Float.parseFloat(vitalSign.height);
            if(height<30 || height>250){
                theUS.setStatus(("ส่วนสูงควรอยู่ในช่วง 30-250"),UpdateStatus.WARNING);
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
                theUS.setStatus(("กรุณากรอกค่าความดันให้ครบถ้วน"),UpdateStatus.WARNING);
                return 5;
            }
            float pressure1 = Float.parseFloat(pres1);
            float pressure2 = Float.parseFloat(pres2);
            //แก้ตาม requirement ของน้องเก๋ ให้ความดันช่วงแรกอยู่ที่ 350
            if(pressure1<0 || pressure1>350)
            {
                theUS.setStatus(("ความดันค่าแรกควรอยู่ในช่วง 0-350"),UpdateStatus.WARNING);
                return 6;
            }
            if(pressure2<0 || pressure2>160)
            {
                theUS.setStatus(("ความดันค่าหลังควรอยู่ในช่วง 0-160"),UpdateStatus.WARNING);
                return 7;
            }
            if(pressure1<pressure2)
            {
                theUS.setStatus(("ความดันค่าแรกต้องมากกว่าค่าความดันค่าหลัง"),UpdateStatus.WARNING);
                return 8;
            }
        }
        if(!vitalSign.puls.equals(""))
        {
            float puls = Float.parseFloat(vitalSign.puls);
            if(puls<0 || puls>200)
            {
                theUS.setStatus(("อัตราการเต้นของหัวใจควรอยู่ในช่วง 0-200"),UpdateStatus.WARNING);
                return 9;
            }
        }
        if(!vitalSign.res.equals(""))
        {
            float res = Float.parseFloat(vitalSign.res);
            if(res<0 || res>120)
            {
                if(!theUS.confirmBox(Constant.getTextBundle("อัตราการหายใจควรอยู่ในช่วง 0-120 ค่าที่กรอกมีค่ามากกว่าค่าปกติ") + " " +
                   Constant.getTextBundle("ยืนยันที่จะบันทึกใช่หรือไม่"),UpdateStatus.WARNING))
                {
                    return 10;
                }
            }
        }
        if(!vitalSign.temp.equals(""))
        {
            float temp = Float.parseFloat(vitalSign.temp); 
            if(temp<35 || temp>45){
                theUS.setStatus(("อุณหภูมิควรอยู่ในช่วง 35-45"),UpdateStatus.WARNING);
                return 11;
            }
        }
        // Somprasong comment 
//        vitalSign.record_time = theLookupControl.getTextCurrentTime();
//        vitalSign.record_date = theLookupControl.getTextCurrentDate();
        theConnectionInf.open();
        ///////////////////////////////////////////////////////////////////////////
        //henbe:07/06/2550:ตรวจสอบวันที่ในอนาคต
        try{
            if(theLookupControl.isDateTimeFuture(vitalSign.check_date+",00:00:00")) {
                theUS.setStatus(("วันและเวลาที่ตรวจ เป็นเวลาในอนาคตไม่ได้"),UpdateStatus.WARNING);
                return 12;
            }
        }
        catch(Exception e){
            theUS.setStatus(("วันและเวลาที่ตรวจ ไม่ถูกต้อง"),UpdateStatus.WARNING);
            return 13;
        }
        ///////////////////////////////////////////////////////////////////////////
        
        try {
           if(theLookupControl.theLO.theOption.vitalsign_secure.equals(Active.isEnable())){
               // Somprasong comment 07122010 ถ้าคนละ user กันห้ามแก้ไข จบการทำงาน
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
            // ดึงข้อมูล VitalSign มาแสดงใหม่โดยเรียงตามวันเวลาที่ตรวจ  sumo 29/08/2549
            theHO.vVitalSign = theHosDB.theVitalSignDB.selectByVisitDesc(theHO.theVisit.getObjectId());
            //amp:04/04/2549
            if(!"".equals(interaction))
            {
                interaction = Constant.getTextBundle("การบันทึกข้อมูล VitalSign") + " " +
                        Constant.getTextBundle("เสร็จสิ้น") + " " +
                        Constant.getTextBundle("โดยความดันนี้มีปฏิกิริยากับ")+" " + interaction;
                theHS.theVitalSubject.notifyManageVitalSign(interaction,2);
                theSystemControl.setStatus(UseCase.TH_saveVitalSign,UpdateStatus.COMPLETE,null);
                theSystemControl.saveLog(UseCase.UCID_saveVitalSign,objectid,null,UpdateStatus.COMPLETE);
            }
            else
            {
                interaction = Constant.getTextBundle("การบันทึกข้อมูล VitalSign") + " " +
                        Constant.getTextBundle("เสร็จสิ้น");
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
     *@see: ตรวจสอบว่าความดันมีผลกับยาที่สั้งไปแล้วหรือไม่
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
     * บันทึก อาการเบื้องต้น ลงฐานข้อมูล
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
     */
    public void savePrimarySymptom(PrimarySymptom primarySymptom)
    {
        Constant.println(UseCase.UCID_savePrimarySymptom);
        String objectid =   null;
        if(theHO.theVisit==null){
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return ;
        }
        if(primarySymptom == null){
            theUS.setStatus(("ไม่มีข้อมูลอาการเบื้องต้น"),UpdateStatus.WARNING);
            return;
       }
        if(primarySymptom.main_symptom.equals("")
            && primarySymptom.current_illness.equals("")){
            theUS.setStatus(("กรุณากรอกข้อมูลอย่างน้อย 1 รายการ"),UpdateStatus.WARNING);
            return;
        }
        if(primarySymptom.main_symptom.length()>1000
            || primarySymptom.current_illness.length()>1000){
            theUS.setStatus(("กรุณากรอกข้อมูลให้น้อยกว่า 1000 ตัวอักษร"),UpdateStatus.WARNING);
            return;
        }
       theConnectionInf.open();
       try{
           // Somprasong comment 07122010 ถ้าไม่ใช้ user เดิมไม่มีสิทธิ์แก้ จบการทำงาน
//           //หากผู้แก้ไขไม่ใช่คนเดี่ยวกับผู้บันทึกจะบันทึกรายการใหม่ให้ทันที
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
            /*ตรวจสอบว่าแพทย์ตรวจหรือไม่*/
            /////////////////////////////////////////////////////
            theLookupControl.intConfirmDoctorTreatment(false,date_time);
            /////////////////////////////////////////////////////
            //theHC.theVisitControl.checkDoctorTreament(true);
            theHO.vPrimarySymptom = theHosDB.thePrimarySymptomDB.selectByVisitId(theHO.theVisit.getObjectId());
            theHO.flagDoctorTreatment = true;
            theHS.theVitalSubject.notifyManagePrimarySymptom(Constant.getTextBundle("การบันทึกอาการสำคัญ") + " "+
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
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
     * ลบ vital sign ออกจากฐานข้อมูล
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
     *การแก้ไขข้อมูลในตาราง vital *
     */
    public void deleteVitalSign(Vector vVitalSign,int[] row)
    {
        Constant.println(UseCase.UCID_deleteVitalSign);
        String objectid =   null;
        if(row == null || row.length ==0)
        {
            theUS.setStatus(("กรุณาเลือกรายการ VitalSign"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit == null) 
        {
            theUS.setStatus(("ผู้ป่วยยังไม่เปิด visit"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return ;
        }
        if(!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้ใช่หรือไม่"),UpdateStatus.WARNING))
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
                
                //amp:03/04/2549 เมื่อมีการลบ vitalsign ให้ไปตรวจสอบ
                if(theLookupControl.readOption().isUseDrugInteract() && !"".equals(theVitalSign.pressure))
                {
                    theHosDB.theOrderDrugInteractionDB.updateBloodPresureByVisitId(theHO.theVisit.getObjectId(), theVitalSign.pressure);
                }
            }
            theHO.vVitalSign = vVitalSign;
            theHS.theVitalSubject.notifyManageVitalSign(Constant.getTextBundle("การลบข้อมูล VitalSign") + " " +
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
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
     * ลบ อาการเบื้องต้น ออกจากฐานข้อมูล
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
     * ยังไม่ได้ใช้งาน
     
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
     * แสดง อาการเบื้องต้น 
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
     */
    public Vector listPrimarySymptom() 
    {
        theHO.vPrimarySymptom = listPrimarySymptom(theHO.theVisit.getObjectId());
        return theHO.vPrimarySymptom;
    }
    /**
     * แสดง อาการเบื้องต้น 
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
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
     * แสดง vital sign 
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
     */
    public Vector listVitalSign() 
    {
        theHO.vVitalSign = listVitalSign(theHO.theVisit.getObjectId());
        return theHO.vVitalSign;
    }
    /**
     * แสดง vital sign 
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
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
     * แสดง vital sign เรียงตามวันเวลาที่บันทึกโดยเรียงจากน้อยไปมาก
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
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
     * แสดง vital sign โดยใช้ vital sign id
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
     * ยังได้ใช้งาน
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
     * แสดง VitalTemplate โดยใช้ vtId
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
     * ยังไม่ได้ใช้งาน
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
     * แสดง ผลการตรวจร่างกาย โดยใช้ visit_id
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
     */
    public Vector listPhysicalExam() 
    {
        theHO.vPhysicalExam = 
            listPhysicalExamByVn(theHO.theVisit.getObjectId());
        return theHO.vPhysicalExam;
    }
    /**
     * แสดง ผลการตรวจร่างกาย โดยใช้ visit_id
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
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
     * แสดง ผลการตรวจร่างกาย โดยใช้ visit_id
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
     * @deprecated
     */
    public Vector listPhysicalExamNan() 
    {
        return listPhysicalExamNan(theHO.theVisit,false);
    }
    /**
     * แสดง ผลการตรวจร่างกาย โดยใช้ visit_id
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
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
     * บันทึก ผลการตรวจร่างกาย 
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
     */
    public void savePhysicalExam(Vector phyex) 
    {
        Constant.println("public void savePhysicalExam(Vector phyex) ");
        Constant.println(UseCase.UCID_savePhysicalExam);
        String objectid =   null;
        if(theHO.theVisit==null)
        {
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.is_discharge_doctor.equals("1"))
        {
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่ยังไม่จำหน่ายทางการแพทย์"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return ;
        }
        if(phyex==null)
        {   
            theUS.setStatus(("ยังไม่มีข้อมูลการตรวจร่างกาย"),UpdateStatus.WARNING);
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
                        //amp:10/04/2549 เพื่อเพิ่มอวัยวะในตาราง base กรณีที่ไม่มีอวัยวะนั้น
                        //henbe: comment ไว้ก่อนเพราะว่ากันคนพิมพ์มั่วลงไป แต่ไม่ดีกว่า
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
            /*ตรวจสอบว่าแพทย์ตรวจหรือไม่*/
            /////////////////////////////////////////////////////
            theLookupControl.intConfirmDoctorTreatment(false,date);
            /////////////////////////////////////////////////////
            theHO.vPhysicalExam = theHosDB.thePhysicalExamDB.selectByVisitId(theHO.theVisit.getObjectId());
            //ตรวจสอบว่าแพทย์ตรวจหรือไม่
            //checkDoctorTreament(true);
            theHO.flagDoctorTreatment = true;
            theHS.theVitalSubject.notifyManagePhysicalExam(Constant.getTextBundle("บันทึกข้อมูลการตรวจร่างกาย") + " "+
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
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
     * ลบ ผลการตรวจร่างกาย 
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47 
     */
    public void deletePhysicalExam(Vector phyex,int[] row) 
    {
        Constant.println(UseCase.UCID_deletePhysicalExam);
        String objectid =   null;
        if(theHO.theVisit==null){   
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return ;
        }
        if(row.length == 0) {
            theUS.setStatus(("กรุณาเลือกรายการที่ต้องการลบ"),UpdateStatus.WARNING);
            return;
        }
        if(!theUS.confirmBox(Constant.getTextBundle("ยืนยันที่จะลบรายการนี้ใช่หรือไม่"),UpdateStatus.WARNING)){
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
            theHS.theVitalSubject.notifyManagePhysicalExam(Constant.getTextBundle("ลบข้อมูลการตรวจร่างกาย") + " "+
                    Constant.getTextBundle("เสร็จสิ้น"),UpdateStatus.COMPLETE);
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
     * แก้ไข การวินิจฉัยโรค 
     */
    public void saveDxNote(String str)
    {
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return ;
        }
        theConnectionInf.open();
        try{
            str = Gutil.CheckReservedWords(str);
            theHO.theVisit.diagnosis_note = str;
            theHosDB.theVisitDB.updateDxNote(theHO.theVisit);
            //เอา notify ออก เพราะไป refresh แถบอาการเจ็บป่วยในช่อง Dx sumo 31/8/2549
            //theHS.theVitalSubject.notifySaveDiagDoctor("การบันทึกหมายเหตุของแพทย์เสร็จสิ้น",UpdateStatus.COMPLETE);
         }
        catch(Exception ex) {    
            theUS.setStatus(Constant.getTextBundle("การบันทึกหมายเหตุของแพทย์") + " " +
                    Constant.getTextBundle("ผิดพลาด"),UpdateStatus.ERROR);
            ex.printStackTrace(Constant.getPrintStream());
        }
        finally{
            theConnectionInf.close();
        }
    }
    /**
     * แก้ไข การวินิจฉัยโรค 
     * จัดเอกสารรายละเอียด uc แล้ว 13/11/47
     * @author โต้ง  
     * @author ซัม
     * @author แอ้ม
     * @author ปู
     * @deprecated 300 บรรทัด ซับซ้อนมากเกินไปเข้าใจยากแก้ยากมาก henbe comment ไม่แก้
     * @aothor เบะ จำเป็นต้องแก้วะไม่งั้นชิบหายแน่เลย
     * Object ที่เกี่ยวข้อง   
            //theHosDB.theMapVisitDxDB.deleteByVid(theHO.theVisit.getObjectId()); 
            //กรณีที่ Dx มีค่าเป็นช่องว่างให้ทำการ update ให้เป็น 0 ทั้งหมด
            //เพื่อยกเลิกรายการทั้งหมดที่ได้กรอกลงไป
     * Visit  MapVisitDx DxTemplate Guide DiagIcd10 vHealthEducation       
     * //กรณีที่ Dx ไม่ใช่ช่องว่างให้ทำการและมีข้อมูล dx template ก็ให้
            //ทำการวนลูปเพื่อหาว่าค่าที่อยู่ใน DxTemplate นั้นมีอยู่ใน doctor_dx(ที่มาจากการกรอก) หรือ
            //ไม่ถ้ามีให้ insert ถ้าไม่มีแสดงว่าถูกยกเลิกไปแล้ว
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
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"),UpdateStatus.WARNING);
            return;
        }
        if(theHO.theVisit.isDischargeDoctor())
        {
            theUS.setStatus(("ผู้ป่วยจำหน่ายทางการแพทย์แล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return ;
        }
        if(theHO.theVisit.visit_status.equals(VisitStatus.isOutProcess()))
        {
            theUS.setStatus(("ผู้ป่วยจบกระบวนการแล้วไม่สามารถแก้ไขข้อมูลได้"),UpdateStatus.WARNING);
            return ;
        }
//        if(theVisit.doctor_dx.length()==0){
//            theUS.setStatus("กรุณากรอกค่าก่อนบันทึก DX",UpdateStatus.WARNING);
//            return;
//        }
        //ตรวจสอบว่าแพทย์ลง Diagnosis ซ้ำหรือเปล่าถ้าซ้ำก็ไม่ต้องบันทึก Diag เลยแล้วกัน
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
                // ใช้ในการตรวจสอบว่าที่ได้มาจากการลง Dx ซ้ำกับที่มีอยู่หรือไม่
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
                   // ตรวจสอบว่า ICD10 ที่ได้จากการ Map มีอยู่ใน DB หรือเปล่า  sumo 29/08/2549
                   // ถ้าตรวจสอบแล้วว่ามีใน DB ก็ให้บันทึก ICD10 อัตโนมัติได้  sumo 29/08/2549
                   ICD10 theICD10 = theHosDB.theICD10DB.selectByCode(mvd.visit_diag_map_icd);
                   if(theICD10 != null) {
                       //เกิดการตรวจสอบว่าไม่ตรงกับที่ผู้ใช้เลือกจากหน้าจอ vitalSign
//                       if(ddc.clinic_id.equals(Clinic.MED)
//                       && Integer.parseInt(theVisit.patient_age) < 16){
//                           Clinic clinic = theHosDB.theClinicDB.selectByCode("00:03");
//                           //henbe แก้ปัญหาเฉพาะหน้า
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
            //pu:เก็บข้อมูลแพทย์ผู้ตรวจ ตอนแพทย์บันทึก DX
            theHO.theVisit.visit_patient_self_doctor = this.theHO.theEmployee.getObjectId();
            theHosDB.theVisitDB.updateDiagnosis(theHO.theVisit);
            theHS.theVitalSubject.notifySaveDiagDoctor(GuiLang.setLanguage("การบันทึกผลการตรวจรักษา") + " " +
                    GuiLang.setLanguage("เสร็จสิ้น"),UpdateStatus.COMPLETE);
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
     *สั่งรายการ order อัตโนมัติ ตาม Dx ที่ระบุให้ผู้ป่วย
     *@author Pu
     *@date 11/08/2549 
     *not @deprecated เปลี่ยนเป็นการค้น item มารอแทนเมื่อมีการเลือกผู้ป่วยในหน้า order
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
            //ค้นหารายการ ItemDx จาก Dx ที่แพทย์ลง
            MapVisitDx mapdx = (MapVisitDx)vMvd.get(i);
            if(mapdx.visit_diag_map_active.equals(Active.isEnable()))
            {
                Vector vItemDx = theHosDB.theDxTemplateMapItemDB.selectByDtid(mapdx.dx_template_id);
                //ตรวจสอบกับ order ที่มีอยู่แล้ว ถ้าซ้ำก็ไม่สั่งรายการเพิ่ม
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
     * ใช้ในการตรวจสอบว่า dxTemplate ที่ได้นั้นเป็นกลุ่ม 2(กลุ่มรหัสICD10) หรือไม่ และ ต้องไม่มีอยู่ในตาราง mapdiag  และ
     * มีอยู่ใน Dx ของแพทย์หรือไม่ ถ้า ใช้ทั้งหมดจะส่งค้าเป็น true ถ้าใช้อย่างได้อย่างหนึ่งหรือไม่ใช้เลย จะส่งค่าเป็น false
     * @param dxTemplate เป็น Object ของ DxTemplate(DxTemplate)
     * @return boolean เป็น true ให้แสดง Dialog ได้ ถ้าเป็น false ไม่ให้แสดง Dialog
     * @author padungrat(tong)
     * @date 21/04/2549,16:07
     */
    public boolean checkShowDialogChooseICD10FormTemplate(DxTemplate dxTemplate)
    {
        boolean result = false;
        theConnectionInf.open();
        try{
            if(dxTemplate != null)
            {   //ตรวจสอบว่า เป็นกลุ่ม 2(กลุ่มรหัสICD10) หรือไม่ และ ต้องไม่อยู่ในตาราง t_visit_diag_map และ ต้องมีอยู่ใน dx ของแพทย์ด้วย
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
     * ใช้ในการตรวจสอบ Dx ที่ไม่ต้องการให้มีคำ ที่กำหนด โดยจะตรวจสอบถ้าเจอจะตัดคำนั้นออกไป
     * โดยคำเหล่านั้นต้องไม่อยู่ หน้า หรือ อยู่หลัง
     * @param word เป็น String ที่ต้องการจะหา
     * @return เป็น String ของข้อมูลที่ถูกตั้งคำที่กำหนดออกไปแล้ว
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
        //ถ้าข้อมูลยังไม่เป็นจริง จะต้องหาข้อมูลอีกครั้ง
        if(!result)
        {   
            word = checkWordForDx(word.trim());
        }
        
        return word.trim();
    }
    /** 
     * ใช้ในการหาตำแหน่งของข้อมูลใน vector เพื่อจะทำการลบ
     * @param diagIcd10 เป็น Object ของ DiagIcd10 ที่ใช้ในการลงรหัส
     * @return int เป็นคำแหน่งของข้อมูลที่ Vector ถ้า return เป็น -1 คือไม่มีอยู่ใน Vector
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
     *ใช้ในการหาข้อมูลที่มีการ map dx จากตาราง โดย หมายเลข visit_id และ สถานะ
     *@param visit_id เป็น String ของ รหัสหลักของตาราง t_visit
     *@return เป็น Vector ของ Object MapVisitDx
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
     *ไม่ได้ใช้งาน
     *จัดเอกสารรายละเอียด uc แล้ว 27/11/47 
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
     * list รายการ Vitalsign ตาม visit_id
     * จัดเอกสารรายละเอียด uc แล้ว 27/11/47 
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
     * ลิสรายการตามครั้งที่มารับบริการ
     * @param  visit
     * @return vector ของ Wound
     * @author kingland
     * @date 15/06/2549
     */
    public Vector listWoundByVisitID(Visit visit)
    {
        if(visit == null){
            theUS.setStatus(("กรุณาเลือกผู้ป่วยที่อยู่ในกระบวนการ"), 2);
            return null;
        }
        return listWoundByVisitID(visit.getObjectId());
    }
    /**
     * ลิสรายการตามครั้งที่มารับบริการ
     * @param  visit_id
     * @return vector ของ Wound
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
     * ลบบาดแผล
     * @param  -
     * @return -
     * @author kingland
     * @date 15/06/2549
     */
    
    public int deleteWoundV(Vector woundv,UpdateStatus theUS)
    {
        if(woundv == null) {
            theUS.setStatus(("กรุณาเลือกรายการบาดแผลที่ต้องการลบ"),UpdateStatus.WARNING);
            return 0;
        }
        int result=0;
        theConnectionInf.open();
        try{
            for(int i=0;i<woundv.size();i++){
                Wound wound = (Wound)woundv.get(i);
                result = theHosDB.theWoundDB.delete(wound);
            }
            theUS.setStatus(Constant.getTextBundle("ลบข้อมูลบาดแผล") + " "+
                    Constant.getTextBundle("เสร็จสิ้น"),theUS.COMPLETE);
            return result;
        }
        catch(Exception ex){
            theUS.setStatus(Constant.getTextBundle("ลบข้อมูลบาดแผล") + " "+
                    Constant.getTextBundle("ผิดพลาด"),theUS.ERROR);
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
            theUS.setStatus(("กรุณาเลือกรายการบาดแผลที่ต้องการลบ"),UpdateStatus.WARNING);
            return 0;
        }
        if(!theUS.confirmBox(Constant.getTextBundle("ต้องการลบรายการบาดแผลหรือไม่ ?"),UpdateStatus.WARNING)){
            return 0;
        }
        
        int result=0;
        theConnectionInf.open();
        try{
            result = theHosDB.theWoundDB.delete(wound);
            theUS.setStatus(Constant.getTextBundle("ลบข้อมูลบาดแผล") + " "+
                    Constant.getTextBundle("เสร็จสิ้น"),theUS.COMPLETE);
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
     * บันทึกบาดแผล
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
            us.setStatus(Constant.getTextBundle("การบันทึกข้อมูลบาดแผล") + " "+
                    Constant.getTextBundle("เสร็จสิ้น"),theUS.COMPLETE);
        }
        catch(Exception ex){
            us.setStatus(("ไม่สามารถบันทึกข้อมูลบาดแผลได้"),theUS.ERROR);
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
            us.setStatus(Constant.getTextBundle("การบันทึกข้อมูลบาดแผล") + " "+
                    Constant.getTextBundle("เสร็จสิ้น"),theUS.COMPLETE);
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
            us.setStatus(("ไม่สามารถบันทึกข้อมูลบาดแผลได้"),theUS.WARNING);
            return result;
        }
        if(wound.visit_id.equals("")){
            us.setStatus(("กรุณาเลือกรายการอุบัติเหตุที่ต้องการบันทึกบาดแผล"),theUS.WARNING);
            return result;
        }
        if(wound.wound_type == null || wound.wound_type.equals(""))  {
            us.setStatus(("กรุณาระบุชนิดของบาดแผล"),theUS.WARNING);
            return result;
        }
        //ต้องรับมาจากหน้าจอตอนที่เขาเลือกแล้วละ
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
