/*
 * HosModel.java
 *
 * Created on 9 พฤษภาคม 2548, 13:44 น.
 */   

package com.hosv3.object;

import java.util.*;
import com.hospital_os.object.*;
import com.hospital_os.object.QueueLabStatus;
import com.hospital_os.object.specialQuery.*;

import com.hospital_os.usecase.connection.Persistent;
import com.hosv3.utility.*;
import com.hosv3.object.printobject.PrintSelectDrugList;

import com.pcu.object.*;
/**
 *
 * @author  administrator
 */
public class HosObject {
    
    public static String DATE_VERIFY = GuiLang.setLanguage("วันที่ยืนยัน") ;
    public static String VERIFIER = GuiLang.setLanguage("ผู้ยืนยัน") ;
    public static String EXECUTER = GuiLang.setLanguage("ผู้ดำเนินการ") ;
    public static String REPORTOR = GuiLang.setLanguage("ผู้รายงานผล") ;
    public static String DISPENSER = GuiLang.setLanguage("ผู้จ่าย") ;
    public static String CANCEL = GuiLang.setLanguage("ผู้ยกเลิก") ;
    public static String ALLERGY = GuiLang.setLanguage("ผู้ป่วยแพ้") ;
    public static String MAINAPP = "MAINAPP";
    public static String SETUPAPP = "SETUPAPP";
    public static String REPORTAPP = "REPORTAPP";
    
    //Labrefer Out In Object
    //print object -neung
    //public LabResultItem theLabResultItem;
    //ใช้ตรวจสอบว่าเป็นคิวค้างผลแลปหรือไม่
    public boolean flag;
    public boolean flagDoctorTreatment;
    public boolean is_admit = false;
    public boolean is_cancel_admit = false;
    public boolean is_sound_enabled = false;
    public boolean is_auto_report_bug = false;
    public boolean is_attach_file_to_mail = false;
    /**
     * กำลังรันโปรแกรมไหนอยู่
     * MAINAPP คือ โปรแกรม Hos หลัก
     * SETUPAPP คือ โปรแกรม Setup Hos
     * REPORTAPP คือ โปรแกรม Report
     */
    public String running_program = "";
    /**
     * @deprecated ผิดหลักการจะต้องส่ง appointment ผ่านเป็น parameter มาให้ด้วย
     **/
    public Hashtable theAppointment;//amp:25/02/2549 -> เก็บนัดที่มาตามนัด และ order ของการนัดล่วงหน้านั้น

    /**
     * hospital_group
     */
    public Employee theEmployee;
    public GActionAuthV theGActionAuthV;
    public ListTransfer theListTransfer;
    public MapQueueVisit theMapQueueVisit;
    public LookupObject theLO;
    public String home_out_side;
    public Version theVersion;
    public String date_time;
    public DiagDoctorClinic theDiagDoctorClinic;
    public Ward theWard;
    public ServicePoint theServicePoint;
    /**
     * amp:thePatientHistory เก็บปัจจัยเสี่ยง,ประวัติครอบครัว,โรคประจำตัว 
     * สามารถ get ค่าปัจจัยเสี่ยง โดยใช้คีย์ risk_factor
     * สามารถ get ค่าประวัติครอบครัว โดยใช้คีย์ family_history
     * สามารถ get ค่าโรคประจำตัว โดยใช้คีย์ personal_disease
     * main group
     */
    public Village theVillage;
    public Home theHome;
    public Family theFamily;
    public Patient thePatient;
    public Visit theVisit;
    /**
     * family group
     */
    public Death theDeath;
    public Vector vBillingPatient;
    public Vector vDrugAllergy;
    public Vector vPatientPayment;
    public PatientXN thePatientXN;
    public Vector vRiskFactor;
    public Vector vFamilyHistory;
    public Vector vPersonalDisease;
    public Vector vVisit;
    public Vector vNCD;
    public Vector vDrugAllergyStd;
    /**
     * visit group
     */
    public Vector vBilling;
    public Vector vBillingInvoice;
    public Vector vBillingVisit;
    public Vector vDiadIcd10Cancel;//amp:18/02/2549 -> เก็บ keyid ของ diagIcd10 ที่ถูกยกเลิก
    public Vector<X39Persistent> vDiagIcd10;
    public Vector vDiagIcd9;
    public Vector vGuide;
    public Vector vLabReferIn;
    public Vector vLabReferOut;
    public Vector vOrderCancel;//amp:18/02/2549 -> เก็บ keyid ของ order ที่ถูกยกเลิก
    public Vector vOrderItem;
    public Vector vOrderItemReceiveDrug;
    public Vector vPhysicalExam;
    public Vector vPrimarySymptom;
    public Vector vTransfer;
    public Vector vVisitPayment;
    public Vector vVitalSign;
    public VitalSign theVitalSign;
    public VitalTemplate theVitalTemplate;
    public Vector vOldVisitPayment = null;
    public Vector vMapVisitDx;
    public Vector vPastHistory;
    public Vector vHealthEducation;
    public Refer theReferIn;
    public Refer theReferOut;
    public OrderItem theOrderItem;
    /**
     * unknow group
     */
    public boolean theDxTemplateNew;
    public Vector vOrderDrugInteraction;//amp:27/03/2549 เก็บประวัติการสั่งยาที่มีปฏิกิริยากัน
    public Vector vIcd9;// เก็บ Icd9 ที่ใช้กับ Balloon  sumo 29/03/2549
    public Vector vServicePoint;// เก็บ ServicePoint ที่ใช้กับ Balloon  sumo 28/04/2549
    public GuideAfterDxTransaction theGuideAfterDxTransaction;
    public PrintSelectDrugList thePrintSelectDrugList;
    public Receipt thePrintReceipt;
    public Site theSite;//ไม่ใช้แล้วครับ 14/01/2549 แก้ไข sumo ยังต้องใช้อยู่หลายจุดโดยเฉพาะโมดูลเสริม
    public String labQueueRemain;
    public String orderSecret="";//amp:07/03/2549 -> คือ order_id ใช้ตรวจสอบว่าเป้นคิวแลปปกปิดหรือเปล่า
    public String specimenCode="";//amp:07/03/2549
    /**
     * @author henbe
     * @deprecated henbe มันจะใช้ vMapVisitDx แทนแล้วนี่นาไม่จำเป็นต้องใช้อันนี้แล้วก็ได้
     */
    public Vector vDxTemplate;
    /**
     * @deprecated ไม่ได้ใช้งานแล้วเพราะซ้ำซ้อน
     */
    public Vector vPhysicalExamNan;
    /**ใช้ในการระบุว่าได้มีการเลือก ให้แสดง สิทธิการรักษาที่ถูกยกเลิกหรือไม่ ถ้าเป็น false ไม่ได้เลือก ถ้าเป็น true ได้เลือก*/
    public boolean showVisitPaymentCancel =false;
    /*amp:04/04/2549 ที่เพิ่มเพราะเจอว่าในการเพิ่มยาที่แพ้แบบกด + มากกว่า 1 ครั้งขึ้นไปในการเปิด Dialog ครั้งเดียว 
     *เมื่อ save มันจะ save ให้เฉพาะตัวแรกตัวเดียว*/
    public Vector vDrugAllergyTemp = null;
    /**
     * amp:27/03/2549:is_order:เก็บว่าเป็นเลือก item หรือเลือก order 
     * ถ้าเลือกจาก item จะเป็น true
     * ถ้าเลือกจาก order จะเป็น false
     * เพื่อใช้ตรวจสอบว่ายังต้องขึ้นเตือนแพ้ยา หรือ drug interaction อีกหรือไม่
     * เช่น ถ้าคลิกจาก item จะต้องขึ้นเตือนก่อนสั่ง
     * แต่ถ้าคลิกเลือกจาก order ก็ไม่ควรจะขึ้นเตือนอีกแล้ว
     */
    public boolean is_order=false;
    /**
     * ใช้ในการเป็กข้อมูลที่มีเลข xn ซ้ำกัน เก็บ String ของ HN
     * @author padungrat(tong)
     * @date 21/04/2549,15:11
     */
    public Vector vSameXN;
    public Object theJasperViewer;
    
    
    /**เก็บ Item ที่ค้นหามาเพื่อ Map กับDx
     *@author Pu
     *@date 09/08/2549
     */
    public Vector vItemDx;
    
    /**เก็บ ICD10 ที่ค้นหามาเพื่อ Map กับ Chronic
     *@author Pu
     *@date 09/09/2551
     */
    public Vector vICD10GCGroup;
    public Vector vICD10GCSpecifyCode;
    /**เก็บ ICD10 ที่ค้นหามาเพื่อ Map กับ Surveil
     *@author Pu
     *@date 10/09/2551
     */
    public Vector vICD10GSGroup;
    public Vector vICD10GSSpecifyCode;
    
    /**
     * เป็น Object ที่กำลังจัดการอยู่ในขณะนั้น
     */
    public Persistent theXObject;
    public Vector vXObject;
    public int[] vxo_index;
    // SOmprasong add 241209
    public Surveil theSurveil;
    public Chronic theChronic;
    public String[] target_db;
    public String[] local_db;
    public Family theFamilyFather;
    public Family theFamilyMother;
    public Family theFamilyCouple;
    public QueueVisit theQV;
    public String objectid;
    public WSConfig theWSConfig;
    /*
     * การรัดครั้งล่าสุด
     */
    public Appointment theFinalAppointMent;
    /** Creates a new instance of HosModel */
    public HosObject(LookupObject lo) 
    {
        theLO = lo;
        theEmployee = new Employee();
        //theSite = new Site();
        theServicePoint = new ServicePoint();
        vDxTemplate = new Vector();
        theVitalSign = new VitalSign();
        theVitalTemplate = new VitalTemplate();
        flag = false;  
        vGuide = new Vector();
        vOrderCancel = new Vector();
        vDiadIcd10Cancel = new Vector();         
        theDiagDoctorClinic = new DiagDoctorClinic();        
    }
    public void initSite(){
        theSite = theLO.theSite;
    }
    ///////////////////////////////////////////////////////////////////////////
    /* getEmployeeUnDefine
     *@Author : henbe pongtorn
     *@date : 21/03/2549
     *@see : ให้ค่า ไม่ระบุ สำหรับใส่ใน combobox ของ Employee,Doctor,Nurse
     */    
    public static Employee getEmployeeUD()
    {
        Employee undefine = new Employee();
        undefine.setObjectId("");
        undefine.fname = Constant.getTextBundle("ไม่ระบุ");   
        undefine.lname = "";   
        return undefine;
    }
    ///////////////////////////////////////////////////////////////////////////
    /* get ServicePoint UnDefine
     *@Author : henbe pongtorn
     *@date : 21/03/2549
     *@see : ให้ค่า ไม่ระบุ สำหรับใส่ใน combobox 
     */       
    public static ServicePoint getServicePointUD()
    {
        ServicePoint p=new ServicePoint();
        p.setObjectId("");
        p.name = Constant.getTextBundle("ไม่ระบุ");
        return p;
    }
    
    public void printValue(){
       
        Constant.println("thePatient==null" + (thePatient==null));
        Constant.println("theVisit==null" + (theVisit==null));
        Constant.println("theListTransfer==null" + (theListTransfer==null));
        Constant.println("thePatient==null" + (thePatient==null));
        //Constant.println("theSite==null" + (theSite==null));
        Constant.println("theServicePoint2==null" + (theServicePoint==null));
        Constant.println("theVersion==null" + (theVersion==null));
        Constant.println("theEmployee==null" + (theEmployee==null));
    }
    public void clearPatient()
    {
        thePatient = null;
        theDeath = null;
        thePatientXN = null;
        vDrugAllergy = null;
        vPastHistory = null;
        vDrugAllergy = null;
        vPatientPayment = null;
        vBillingPatient = null;
        vNCD = null;
        vPersonalDisease = null;
        vDrugAllergyStd = null;
        vRiskFactor = null;
        vFamilyHistory = null;
        vPersonalDisease = null;
        vVisit = null;
        clearVisit();
    }    
    
    public void clearFamily()
    {
        theFamily = null;
        theVillage = null;
        theHome = null;
        theFamilyFather = null;
        theFamilyMother = null;
        theFamilyCouple = null;
        clearPatient();
    }
    /**
     *
     * เอาไว้ใช้ทำอะไรนิไม่ค่อยมีประโยชน์เลยไร้สาระ วันหลังให้บอกด้วยว่าเอาไว้ทำอะไร henbe
     * @not deprecated henbe said bad pattern มันเป็นการบันทึกผู้ป่วยครั้งแรกดังนั้นข้อมูลอื่นๆ จะไม่มีเป็นค่าว่าง
     * ให้ใช้คำสั่ง clearFamily() เลย
     */
    public void setPatient(Patient pt){
        //ยังไม่แน่ใจว่าจะมีบักที่จุดอื่นๆ อีกหรือเปล่าต้องเช็คดู
        //theFamily = null;
        //theVillage = null;
        //theHome = null;
        this.clearPatient();
        thePatient = pt;
        if(theFamily==null)
            vPatientPayment = null;
        
        clearVisit();
    }
    public void clearVisit(){
        orderSecret="";
        specimenCode="";
        theVisit = null;
        theAppointment=null;
        theGuideAfterDxTransaction = null;
        theListTransfer = null;
        theReferIn = null;
        theReferOut = null;
        theVitalSign = null;
        theOrderItem = null;
        vBilling = null;
        vBillingInvoice = null;
        vDiadIcd10Cancel=null;
        vDiagIcd10 = null;
        vDiagIcd9=null;
        vDxTemplate = null;
        vGuide=null;
        vHealthEducation = null;
        vMapVisitDx = null;
        vOrderCancel=null;
        vOrderDrugInteraction=null;
        vOrderItem = null;
        vOrderItemReceiveDrug = null;
        vPhysicalExam = null;
        vPhysicalExamNan = null;
        vPrimarySymptom = null;
        vTransfer = null;
        vVisitPayment = null;
        vVitalSign = null;
        // SOmprasong add 241209
        theSurveil = null;
        theChronic = null;
    }
    public void initVisitExt(){
        vBilling = new Vector();
        vBillingInvoice = new Vector();
        vDiadIcd10Cancel=new Vector();
        vDiagIcd10 = new Vector<X39Persistent>();
        vDiagIcd9=new Vector();
        vDxTemplate = new Vector();
        vGuide=new Vector();
        vHealthEducation = new Vector();
        vMapVisitDx = new Vector();
        vOrderCancel=new Vector();
        vOrderDrugInteraction=new Vector();
        vOrderItem = new Vector();
        vOrderItemReceiveDrug = new Vector();
        vPhysicalExam = new Vector();
        vPhysicalExamNan = new Vector();
        vPrimarySymptom = new Vector();
        vTransfer = new Vector();
        vVisitPayment = new Vector();
        vVitalSign = new Vector();
    }
    public void setFamily(Family fm){
        clearPatient();
        theFamily = fm;
    }
    public void setVisit(Visit visit){
        clearVisit();
        theVisit = visit;
    }
    public Home initHome(String number,Village vill){
        if(vill==null)
            return null;
        Home home = new Home();
        home.home_number = number;
        home.home_house = number;
        home.home_record_date_time = date_time;
        home.home_staff_record = theEmployee.getObjectId();
        home.village_id = vill.getObjectId();
        home.home_moo = vill.village_moo;
        home.home_amphur = vill.village_ampur;
        home.home_changwat = vill.village_changwat;
        home.home_tambol = vill.village_tambon;
        return home;
    }

    public Village initVillage(String moo){
        Village vill = new Village();
        vill.village_number = moo;
        vill.village_name = moo;
        vill.village_moo = moo;
        vill.village_ampur = theLO.theSite.amphor;
        vill.village_tambon = theLO.theSite.tambon;
        vill.village_changwat = theLO.theSite.changwat;
        vill.village_record_date_time = date_time;
        vill.village_staff_record = theEmployee.getObjectId();
        return vill;
    }
    public DiagIcd10 initDiagIcd10()
    {
        DiagIcd10 theDiagIcd10 = new DiagIcd10();
        theDiagIcd10.doctor_kid = getDoctorIDInVisit();
        theDiagIcd10.clinic_kid = "";
        if(theDiagIcd10.doctor_kid.length()==0
                && vDiagIcd10!=null && !vDiagIcd10.isEmpty())
        {
            DiagIcd10 dx10 = (DiagIcd10)vDiagIcd10.get(0);
            theDiagIcd10.doctor_kid = dx10.doctor_kid;
            theDiagIcd10.clinic_kid = dx10.clinic_kid;
        } 
        
        if(vDiagIcd10!=null && vDiagIcd10.size()>0)
            theDiagIcd10.type = Dxtype.getComorbidityDiagnosis();
        else 
            theDiagIcd10.type = Dxtype.getPrimaryDiagnosis();
        
        theDiagIcd10.diagnosis_date = date_time.substring(0,10);
        theDiagIcd10.dischange_note = "";
        theDiagIcd10.diag_icd10_staff_record = theEmployee.getObjectId();
        theDiagIcd10.diag_icd10_record_date_time = date_time;
        
        return theDiagIcd10;
    }
    public DiagIcd9 initDiagIcd9(ICD9 icd9,DiagDoctorClinic ddc)
    {
        
        DiagIcd9 theDiagIcd9 = initDiagIcd9();
        theDiagIcd9.icd9_code = icd9.icd9_id;
        if(ddc!=null){
            theDiagIcd9.clinic_kid = ddc.clinic_id;
            theDiagIcd9.doctor_kid = ddc.doctor_id;
        }
        //แก้บักแล้ว
        if(theDiagIcd9.doctor_kid==null || theDiagIcd9.doctor_kid.equals(""))
            theDiagIcd9.doctor_kid = getDoctorIDInVisit();
        // ตรวจสอบว่า DiagIcd9 ที่บันทึกไปมีประเภทเป็น Principal หรือไม่ sumo 05/09/2549
        boolean have_principal = false;
        boolean have_second = false;
        for(int k=0;k<vDiagIcd9.size(); k++){
            DiagIcd9 diagicd9 = (DiagIcd9)vDiagIcd9.get(k);
            if(diagicd9.type.equals(Optype.PRINCIPAL))
                have_principal = true;
            if(diagicd9.type.equals(Optype.SECONDARY))
                have_second = true;
        }
        if(!have_principal)
            theDiagIcd9.type = Optype.PRINCIPAL;
        else if(!have_second)
            theDiagIcd9.type = Optype.SECONDARY;
        else
            theDiagIcd9.type = Optype.OTHER;
        theDiagIcd9.time_in = date_time;
        theDiagIcd9.time_out = date_time;
        return theDiagIcd9;
    }
    
    public DiagIcd9 initDiagIcd9()
    {
        DiagIcd9 theDiagIcd9 = new DiagIcd9();
        theDiagIcd9.time_in = date_time;
        theDiagIcd9.time_out = date_time;
        theDiagIcd9.diag_icd9_staff_record = theEmployee.getObjectId();
        theDiagIcd9.diag_icd9_record_date_time = date_time;
        theDiagIcd9.vn = theVisit.getObjectId();
        theDiagIcd9.diag_icd9_active = "1";
        theDiagIcd9.clinic_kid = "";    
        if(vTransfer!=null){
            for(int i=vTransfer.size()-1;i>=0;i--)
            {
                Transfer theTransfer = (Transfer)vTransfer.get(i);
                if(!theTransfer.doctor_code.equals("")
                && !theTransfer.doctor_code.equalsIgnoreCase("null"))
                {
                    theDiagIcd9.doctor_kid = theTransfer.doctor_code;
                    break; 
                }
            }
        }
        return theDiagIcd9;
    }
    
    public VitalSign initVitalSign()
    {
        VitalSign theVitalSign = new VitalSign();
        theVitalSign.weight = "";
        theVitalSign.bmi = "";
        theVitalSign.height = "";
        theVitalSign.pressure =  "/";
        theVitalSign.temp =  "";
        theVitalSign.puls = "";
        theVitalSign.res = "";
        theVitalSign.note = "";
        
        //amp:05/04/2549
        theVitalSign.check_date = date_time.substring(0,10);
        theVitalSign.check_time = date_time.substring(11,16);
        theVitalSign.staff_modify = "";
        theVitalSign.modify_date_time = "";
        theVitalSign.active = "1";
        
        theVitalSign.nutrition = NutritionType.NORMAL;
        if(theVisit!=null)
            theVitalSign.visit_id = theVisit.getObjectId();
        if(thePatient!=null)
            theVitalSign.patient_id = thePatient.getObjectId();
        return theVitalSign;
    }
    public Accident initAccident(String date)
    {
        Accident acc = new Accident();
        acc.vn_id = "";
        acc.vn = "";
        if(theVisit!=null){
            acc.vn_id = theVisit.getObjectId();
            acc.vn = theVisit.vn;
        }
        acc.patient_id = "";
        acc.hn = "";
        if(thePatient!=null){
            acc.patient_id = thePatient.getObjectId();
            acc.hn = thePatient.hn;
        }
        acc.acctb = theLO.theSite.tambon;
        acc.accam = theLO.theSite.amphor;
        acc.acccw = theLO.theSite.changwat;
        acc.acctime = "";
        acc.date_accident = date_time.substring(0,10);
        acc.acctime = date_time.substring(11,16);
        acc.to_hos_date = date_time.substring(0,10);
        acc.to_hos_time = date_time.substring(11,16);
        acc.name_rd = "";
        acc.acc_rd = "";
        acc.in_out = "";
        acc.kilo = "";
        acc.accmu = "";
        acc.ptstatus = "";
        acc.ptmobie = "0";
        acc.acc_use = "0";
        acc.acc_alc = "0";
        acc.acc_pro = "0";
        acc.reporter = "";
        acc.accident_type = "";
        acc.occur_type = "";
        acc.emergency_type = "";
        acc.cost_detail = "";
        acc.hitch_constitution = "0";
        acc.claim_code = "";        
        return acc;
    }
/****************************************************************************/ 

    public Payment initPayment(Plan p)
    {
        return initPayment(p,date_time.substring(0,10),theSite.off_id,theVisit);
    }
    public Payment initPayment(Plan p,String date,String off_id,Visit visit)
    {
        Payment thePaymentNow =new Payment();
        thePaymentNow.plan_kid = p.getObjectId();
        if(visit!=null)
            thePaymentNow.visit_id = visit.getObjectId();
        thePaymentNow.card_id = "";
        thePaymentNow.money_limit = p.money_limit;
        thePaymentNow.contract_kid = p.contract_id;
        thePaymentNow.money_limit = p.money_limit;
        thePaymentNow.use_money_limit = "0";
        thePaymentNow.card_ins_date = date;
        thePaymentNow.card_exp_date = "";
        thePaymentNow.hosp_main = off_id;
        try{
            if(Double.parseDouble(p.money_limit) > 0)
                thePaymentNow.use_money_limit = "1";
        }
        catch(Exception e){ 
            Constant.println("Exception if(Double.parseDouble(p.money_limit) > 0) exception");
        }
        return thePaymentNow;
    }

    ////////////////////////////////////////////////////////////////////
    public OrderItemDrug initOrderItemDrug(Item item,DoseDrugSet doseDrugSet){
        OrderItemDrug oid = new OrderItemDrug();
        oid.instruction = doseDrugSet.instruction;                        
        oid.printing = item.printable;
        oid.purch_uom = doseDrugSet.purch_uom;
        oid.usage_special = doseDrugSet.usage_special;
        oid.usage_text = doseDrugSet.usage_text;
        oid.use_uom = doseDrugSet.use_uom;
        oid.frequency  = doseDrugSet.frequency;
        oid.caution = doseDrugSet.caution;
        oid.day_time = doseDrugSet.day_time;
        oid.description = doseDrugSet.description;
        oid.dose = doseDrugSet.dose;
        oid.item_id = doseDrugSet.item_code;
        return oid;
    }
    ////////////////////////////////////////////////////////////////////
    public OrderItemDrug initOrderItemDrug(Drug drug)
    {
        return initOrderItemDrug(null,drug);
    }
    ////////////////////////////////////////////////////////////////////
    public OrderItemDrug initOrderItemDrug(Item item,Drug drug)
    {
        if(drug==null)
            return null;
        Constant.println("_________________initOrderItemDrug:" + drug.dose);
        OrderItemDrug oid = new OrderItemDrug();
        oid.caution = drug.caution;
        oid.day_time = drug.day_time;
        oid.description = drug.description;
        oid.dose = drug.dose;
        oid.frequency  = drug.frequency;
        oid.instruction = drug.instruction;
        oid.item_id = drug.item_id;
        oid.printing = drug.printting;
        oid.purch_uom = drug.purch_uom;
        oid.usage_special = "0"; //tuk:26/07/2549 ให้ default เป็น 0 ไปเลยเนื่องจากไม่มีหน้าจอรับค่า field นี้
        oid.usage_text = drug.usage_text;
        oid.use_uom = drug.use_uom;
        return oid;
    }
    ////////////////////////////////////////////////////////////////////
    public OrderItem initOrderItem(Item item,CategoryGroupItem cg,ItemPrice ip
        ,String date_time)
    {
        OrderItem oi = new OrderItem();
        oi.item_code = item.getObjectId();
        oi.common_name = item.common_name;
        oi.item_group_code_category = item.item_group_code_category;
        oi.specified = item.specified;
        oi.item_group_code_billing = item.item_group_code_billing; 
        oi.item_16_group = item.item_16_group; // sumo 06/06/2549 กลุ่มรายการมาตรฐาน
        oi.category_group = cg.category_group_code;
        //ส่วนรายการอื่นๆ กำหนดให้เป็นค่าว่าง หรือ เป็น 0
        oi.price = "0";
        oi.order_cost = "0";
        if(ip!=null){
            if(ip.price!=null)
                oi.price = ip.price;
            if(ip.price_cost!=null)
                oi.order_cost = ip.price_cost;
        }
        oi.status = OrderStatus.NOT_VERTIFY;
        oi.qty = "1";
        oi.continue_order = "0";
        oi.charge_complete = "0";
        oi.secret = item.secret;
        oi.discontinue = "";
        oi.discontinue_time = "";
        oi.dispense = "";
        oi.dispense_time = "";
        oi.vertifier = "";
        oi.vertify_time = "";
        oi.executer = "";
        oi.executed_time = "";
        oi.order_time = date_time;
        if(theVisit!=null){
            oi.visit_id = theVisit.getObjectId();
            oi.hn = theVisit.patient_id;
        }
        if(theEmployee!=null)
            oi.order_user = theEmployee.getObjectId();
        if(theServicePoint!=null)
            oi.clinic_code = theServicePoint.getObjectId();  
        return oi;
    }
    public static Visit initVisit(){
        Visit vst = new Visit();
        vst.patient_id = "";
        vst.hn = "";
        vst.visit_note = "";
        vst.is_discharge_doctor = "0";
        vst.is_discharge_ipd = "0";
        vst.is_discharge_money = "0";
        vst.visit_status = VisitStatus.isInProcess();
        vst.begin_admit_time = "";
        vst.locking = "0";
        vst.deny_allergy = "0";
        return vst;
    }
    public static boolean isVisitDeath(Visit theVisit)
    {
        if(theVisit.discharge_opd_status.equals("52") 
            ||theVisit.discharge_opd_status.equals("55")
            ||theVisit.discharge_ipd_type.equals("8")
            ||theVisit.discharge_ipd_type.equals("9"))
        {
            return true;
        }
        return false;
    }
    public static boolean isVisitRefer(Visit theVisit)
    {
        if(theVisit.discharge_opd_status.equals("54")||theVisit.discharge_ipd_type.equals("4"))
            return true;
        return false;
    }
    public static ListTransfer initListTransfer(Patient p,Visit v,Transfer t
    ,ServicePoint sp){
        ListTransfer lt = new ListTransfer();
        updateListTransfer(p,lt);
        lt.name = sp.name;
        lt.queue = "0";
        lt.locking = "1";
        lt.description = "";
        lt.color = "r=255,g=255,b=255";
        lt.vn = v.vn;
        lt.visit_type = v.visit_type;
        lt.visit_id = v.getObjectId();
        lt.doctor = t.doctor_code;
        lt.assign_time = t.assign_time;
        lt.servicepoint_id = t.service_point_id;
        lt.labstatus = QueueLabStatus.NOLAB;
        return lt;
    }
    public static boolean updateListTransfer(Patient p,ListTransfer lt)
    {
        if(p==null || lt==null)
            return false;
        lt.hn = p.hn;
        lt.sex = p.f_sex_id;
        lt.fname = p.patient_name;
        lt.lname = p.patient_last_name;
        lt.prefix = p.f_prefix_id;
        lt.patient_id = p.getObjectId();
        lt.patient_allergy = p.patient_drugallergy;
        return false;
    }
    
    public static OfficeInCup initOfficeInCup(Office of){
        OfficeInCup oic = new OfficeInCup();
        oic.code = of.getObjectId();
        oic.name = of.name;
        return oic;
    }
    public Death initDeath(){
        return initDeath(date_time);    
    }
    public Death initDeath(String datetime){
        Death d = new Death();
        if(theFamily!=null){
            d.family_id = theFamily.getObjectId();
            d.family_name = theFamily.patient_name + "  " + theFamily.patient_last_name;
        }
        if(thePatient!=null){
            d.hn = thePatient.hn;
            d.patient_id = thePatient.getObjectId();
        }
        if(theVisit!=null){
            d.vn = theVisit.vn;
            d.vn_id = theVisit.getObjectId();
        }
	d.ddeath = datetime;
	d.cdeath_a = "";
	d.cdeath_b = "";
	d.cdeath_c = "";
	d.cdeath_d = "";
	d.odiseae = "";
	d.cdeath = "";
	d.pdeath = "1";//ตายในโรงพยาบาล
	d.nogreg = "";
	d.wpreg = "";
	d.user_record = theEmployee.getObjectId();
	d.active = "1";        
        return d;
    }
    public Appointment2 initAppointment(String datetime){
        Appointment2 appointment = new Appointment2();
        if(thePatient!=null)
            appointment.patient_id = thePatient.getObjectId();
        if(theVisit!=null)
            appointment.visit_id = theVisit.getObjectId(); 
        appointment.date_serv = datetime;
        appointment.appoint_date = date_time;
        appointment.appoint_time = "";
        appointment.aptype =  "F/U";
        appointment.clinic_code = theServicePoint.getObjectId();
        appointment.description = "";
        appointment.auto_visit = "1";
        appointment.queue_visit_id  = "";
        appointment.status  =AppointmentStatus.WAIT;
        appointment.doctor_code = theEmployee.getObjectId();
        appointment.aptype53 = "181";
        Vector vDoctor = getDoctorInVisit();
        if(!vDoctor.isEmpty())
            appointment.doctor_code = (String)vDoctor.get(0);
        
        return appointment;
    }
    public static QueueVisit initQueueVisit(){
        QueueVisit qv = new QueueVisit();
        return qv;
    }
    public static Billing initBilling(){
        Billing billing = new Billing();
        billing.receipt_date = "";
        billing.active = "";
        billing.payback = "";
        billing.receipt_no = "";
        billing.financial_date = "";
        billing.staff_financial = "";
        billing.patient_id = "";
        billing.visit_id = "";
        billing.paid = "0";
        billing.deduct = "0";
        billing.patient_share = "0";
        billing.payer_share = "0";
        billing.remain = "0";
        billing.total = "0";
        billing.active = Active.isEnable();
        billing.payback = "";
        billing.receipt_no = "";
        billing.financial_date = "";
        billing.paid = "0";
        billing.deduct = "0";
        return billing;
    }
    public Payment getVPayment(String pmid){
        for(int i=0,size=vVisitPayment.size();i<size;i++){
            Payment pm = (Payment)vVisitPayment.get(i);
            if(pmid.equals(pm.getObjectId())){
                return pm;
            }
        }
        return null;
    }
    public Payment getVPaymentByPlan(String plid){
        for(int i=0,size=vVisitPayment.size();i<size;i++){
            Payment pm = (Payment)vVisitPayment.get(i);
            if(plid.equals(pm.plan_kid)){
                return pm;
            }
        }
        return null;
    }
    public Patient initPatient(Family family,Home home)
    {
        if(family==null) return null;
        Patient patient = new Patient();
        patient.setFamily(family);
        patient.family_id = family.getObjectId();
        patient.pid= family.pid;
        //patient.hn= family.hn;
        patient.f_prefix_id = family.f_prefix_id ;
        patient.patient_name = family.patient_name;
        patient.patient_last_name= family.patient_last_name;
        patient.patient_birthday = family.patient_birthday;
        patient.patient_birthday_true = family.patient_birthday_true;
        patient.f_sex_id = family.f_sex_id;
        patient.marriage_status_id = family.marriage_status_id;
        patient.education_type_id = family.education_type_id;
        patient.occupation_id = family.occupation_id;
        patient.nation_id= family.nation_id;
        patient.race_id= family.race_id;
        patient.religion_id= family.religion_id;
        patient.father_firstname= family.father_firstname;
        patient.father_lastname= family.father_lastname;
        String[] mom = family.mother_firstname.split(" ");
        if(mom.length>0)
            patient.mother_firstname= mom[0];
        if(mom.length>1)
            patient.mother_lastname= mom[1];
        
        patient.couple_firstname= family.couple_firstname;
        patient.couple_lastname= family.couple_lastname;
        patient.blood_group_id = family.blood_group_id;
        patient.record_date_time= family.record_date_time;
        patient.update_date_time = family.modify_date_time;
        patient.staff_record= family.staff_record;
        patient.staff_modify= family.staff_modify;
        patient.staff_cancel= family.staff_cancel;
        patient.active= family.active;  
        
        patient.ampur_contact = theLO.theSite.amphor;
        patient.changwat_contact = theLO.theSite.changwat;
        patient.tambon_contact =  theLO.theSite.tambon; 
        patient.discharge_status_id = family.discharge_status_id;
        if(home==null)
        {
            patient.ampur = theLO.theSite.amphor;
            patient.changwat = theLO.theSite.changwat;
            patient.tambon =  theLO.theSite.tambon;
        }
        else
        {
            patient.house = home.home_house;
            patient.village = home.home_moo;
            patient.road = home.home_road;
            patient.tambon = home.home_tambol;
            patient.ampur = home.home_amphur;
            patient.changwat = home.home_changwat;           
        }
        patient.updateF2P();
        return patient;
    }
    
    public Patient initPatient()
    {
        Patient thePatient = new Patient();
        initPatient(thePatient);
        return thePatient;
    }
    public boolean initPatient(Patient thePatient){
        
        thePatient.couple_id = "";
        thePatient.father_pid = "";
        thePatient.mother_pid = "";
        thePatient.patient_birthday = "";
        thePatient.ampur = theLO.theSite.amphor;
        thePatient.changwat = theLO.theSite.changwat;
        thePatient.tambon =  theLO.theSite.tambon;
        thePatient.blood_group_id = "1";
        thePatient.education_type_id = "11";
        thePatient.status_id =  "2";
        thePatient.f_sex_id =  "1";
        thePatient.labor =  "1";
        thePatient.marriage_status_id = "1";
        thePatient.nation_id =  "99";
        thePatient.occupation_id =  "000";
        thePatient.f_prefix_id =  "000";
        thePatient.race_id =  "99";
        thePatient.religion_id =  "1";
//        thePatient.typearea =  "1";
        thePatient.couple_firstname =  "";
        thePatient.couple_lastname =  "";
        thePatient.father_firstname =  "";
        thePatient.father_lastname =  "";
        thePatient.patient_name =  "";
        thePatient.house =  "";
        thePatient.patient_last_name =  "";
        thePatient.mother_firstname =  "";
        thePatient.mother_lastname =  "";
        thePatient.private_doc =  "";
        thePatient.p_type =  "";
        thePatient.road =  "";
        thePatient.village =  "";
        thePatient.pid =  "";
        thePatient.phone =  "";
        thePatient.relation =  "00";
        thePatient.sex_contact =  "1";
        thePatient.house_contact =  "";
        thePatient.village_contact =  "";
        thePatient.road_contact =  "";
        thePatient.phone_contact =  "";
        thePatient.ampur_contact = theLO.theSite.amphor;
        thePatient.changwat_contact = theLO.theSite.changwat;
        thePatient.tambon_contact =  theLO.theSite.tambon;
        thePatient.xn =  "";
        thePatient.pid =  "";
        thePatient.contact_fname =  "";
        thePatient.contact_lname =  "";
        thePatient.patient_birthday_true = "0";
        thePatient.record_date_time =  "";
        thePatient.staff_record = theEmployee.getObjectId();
        return true;
    }    
    
    //public ListTransfer initListTransfer(Transfer t,ServicePoint sp,MapQueueVisit mqv,QueueVisit qv)
    public ListTransfer initListTransfer(Transfer t,String servicePointName,MapQueueVisit mqv,QueueVisit qv)//amp:18/02/2549
    {
            ListTransfer lt = new ListTransfer();
            lt.hn = thePatient.hn;
            lt.sex = thePatient.f_sex_id;
            lt.fname = thePatient.patient_name;
            lt.lname = thePatient.patient_last_name;
            lt.prefix = thePatient.f_prefix_id;
            lt.patient_id = thePatient.getObjectId();
            lt.patient_allergy = thePatient.patient_drugallergy;
            lt.name = servicePointName;
            lt.queue = "0";
            lt.locking = "1";
            lt.description = "";
            lt.color = "r=255,g=255,b=255";
            lt.vn = theVisit.vn;
            lt.visit_type = theVisit.visit_type;
            lt.visit_id = theVisit.getObjectId();
            lt.doctor = t.doctor_code;
            lt.assign_time = t.assign_time;
            lt.servicepoint_id = t.service_point_id;
            lt.labstatus = QueueLabStatus.NOLAB;
            if((qv != null)) {
                lt.color = qv.color;
                lt.queue = mqv.queue;
                lt.description = qv.description;
            }
            return lt;
    }
    public static Vector initContractAdjustV(Contract c,Vector cgiv){
        return initContractAdjustV(c.getObjectId(),cgiv);
    }
    public static Vector initContractAdjustV(String contract_id,Vector cgiv){
        Vector vContract = new Vector();
        for(int i=0,size=cgiv.size();i<size;i++){
            CategoryGroupItem cgi = (CategoryGroupItem)cgiv.get(i);
            ContractAdjust ca = new ContractAdjust();
            ca.adjustment = "-";
            ca.contract_id = contract_id;
            ca.covered_id = cgi.getObjectId();
            ca.draw = "0";
            vContract.add(ca);
        }
        return vContract;
    }
    
    public BorrowFilmXray initBorrowFilmXray(String datetime){
        BorrowFilmXray bor = new BorrowFilmXray();
        if(thePatient!=null)
        {
            bor.patient_hn = thePatient.hn;
        }
        else
        {
            bor.patient_hn = "";
        }
        bor.borrower_prefix = "0";
        bor.borrower_name = "";
        bor.borrower_lastname =  "";
        bor.borrow_film_date = "";
        bor.amount_date = "";
        bor.return_film_date = "";
        bor.borrow_status = "0";
        bor.permissibly_borrow  = "";
        bor.borrow_cause = "";
        bor.borrow_to  = "";
        bor.date_serv = datetime;
        return bor;
    }
    
    public BorrowOpdCard initBorrowOpdCard(String datetime){
        BorrowOpdCard bor = new BorrowOpdCard();
        if(thePatient!=null)
        {
            bor.patient_hn = thePatient.hn;
        }
        else
        {
            bor.patient_hn = "";
        }
        bor.borrower_opd_prefix = "0";
        bor.borrower_opd_name = "";
        bor.borrower_opd_lastname =  "";
        bor.borrow_opd_date = "";
        bor.amount_date_opd = "";
        bor.return_opd_date = "";
        bor.borrow_opd_status = "0";
        bor.permissibly_borrow_opd  = "";
        bor.borrow_opd_cause = "";
        bor.borrow_opd_to  = "";
        bor.date_serv_opd = datetime;
        return bor;
    }
   ///////////////////////////////////////////////////////////////////////////
    /*
     *@Author : henbe pongtorn
     *@date : 18/03/2549
     *@see : ค้นข้อมูล Order จากเวคเตอร์ โดย object_id
     */    
    public static OrderItem getOrderItemFromV(Vector vi,String order_id)
    {
        for(int i=0,size=vi.size();i<size;i++)
        {   
            OrderItem it = (OrderItem)vi.get(i);
            if(it.getObjectId().equals(order_id))
                return it;
        }
        return null;
    }   
   ///////////////////////////////////////////////////////////////////////////
    /*
     *@Author : henbe pongtorn
     *@date : 18/03/2549
     *@see : นับจำนวน Order ที่ยืนยันและดำเนินการทั้งหมด
     */    
    public static int countOrderVerifyExe(Vector orderitem)
    {
        return countOrderStatus(orderitem,OrderStatus.EXECUTE)
            + countOrderStatus(orderitem,OrderStatus.VERTIFY);
     }
    public static int countOrderVerExeRem(Vector orderitem)
    {
        return countOrderStatus(orderitem,OrderStatus.EXECUTE)
            + countOrderStatus(orderitem,OrderStatus.VERTIFY)
            + countOrderStatus(orderitem,OrderStatus.REMAIN);
     }
   ///////////////////////////////////////////////////////////////////////////    
    /*
     *@Author : henbe pongtorn
     *@date : 18/03/2549
     *@see : นับจำนวน Order ที่ค้างบันทึกทั้งหมด
     */    
    public static int countOrderReport(Vector orderitem)
    {
        return countOrderStatus(orderitem,OrderStatus.REPORT);
    }
    /*
     *@Author : henbe pongtorn
     *@date : 18/03/2549
     *@see : นับจำนวน Order ที่ค้างบันทึกทั้งหมด
     */    
    public static int countOrderRemain(Vector orderitem)
    {
        return countOrderStatus(orderitem,OrderStatus.REMAIN);
    }        
        
    /*
     *@Author : henbe pongtorn
     *@date : 18/03/2549
     *@see : นับจำนวน Order ที่ค้างบันทึกทั้งหมด
     */    
    public static int countOrderStatus(Vector orderitem,String status)
    {
        int count_ver_exe = 0;
        if(orderitem==null)
        {
            return count_ver_exe;
        }
        for(int i=0,size=orderitem.size();i<size;i++)
        {
             OrderItem oi = (OrderItem) orderitem.get(i);
             if(oi.isLab())
             {
                 if(oi.status.equals(status))
                 {
                     count_ver_exe++;
                 }
             }
        }
         return count_ver_exe;
     }    
    /*
     *@Author : henbe pongtorn
     *@date : 18/03/2549
     *@see : แปลงข้อมูลผู้ป่วยเป็นข้อมูลหมู่บ้าน
     */    
    public static Village getVillage(Patient pt)
    {
        Village vil = new Village();
        vil.village_number = pt.village;
        vil.village_name = pt.village;
        vil.village_moo = pt.village;
        vil.village_active = "1";
        return vil;
    }
    /*
     *@Author : henbe pongtorn
     *@date : 18/03/2549
     *@see : แปลงข้อมูลผู้ป่วยเป็นข้อมูลบ้าน
     */    
    public static Home getHome(Patient pt)
    {
        Home home = new Home();
        home.home_number = pt.house;
        home.home_house = pt.house;   
        home.home_moo = pt.village;
        home.home_road = pt.road;   
        home.home_tambol = pt.tambon;  
        home.home_amphur = pt.ampur;
        home.home_changwat = pt.changwat;
        home.active = "1";
        return home;
    }
    /*
     *@Author : henbe pongtorn
     *@date : 18/03/2549
     *@see : แปลงข้อมูลผู้ป่วยเป็นข้อมูลประชากร
     */    
    public static Family getFamily(Patient pt)
    {
        Family fm = new Family();
        fm.active = "1";  
        getFamily(pt,fm);
        return fm;
    }
    /*
     *@Author : henbe pongtorn
     *@date : 18/03/2549
     *@see : แปลงข้อมูลผู้ป่วยเป็นข้อมูลประชากร
     *@deprecated use pt.getFamily() instead
     */    
    public static Family getFamily(Patient pt,Family fm){        
        return pt.getFamily();
    }
    /*
     *@author Pongtorn (henbe)
     *@name ใช้ในการตรวจสอบว่าภายในเวคเตอร์ของ order นี้มีรายการ Order ที่มีสถานะดังกล่าวบ้างหรือไม่
     */
    public static boolean isOrderStatus(Vector vOrder,int[] rows,String status)
    {
        boolean has_status=false;
        for(int i =0 ; i < rows.length ;i++)
        {  
            OrderItem theOrderItem = (OrderItem)vOrder.get(rows[i]); 
            if(theOrderItem.status.equals(status)) 
            {
                has_status = true;
            }
        }
        return has_status;
    }
    /*
     *@author Pongtorn (henbe)
     *@name ใช้ในการกำหนดค่าเริ่มต้นของ object result xray
     */
    public ResultXRay initResultXRay(String order_id)
    {
        ResultXRay rx = new ResultXRay();
        rx.xn = thePatient.xn;
        rx.xray_point = "";
        rx.description = "";
        rx.hn = thePatient.getObjectId();
        rx.vn = theVisit.getObjectId();
        rx.record_time = date_time;
        rx.order_item_id = order_id;
        rx.reporter = "";
        rx.active = Active.isEnable();
        return rx;
    }
    public Transfer initTransfer(String service_point_id,String date_time)
    {
        return initTransfer(service_point_id,date_time, theEmployee.getObjectId(),"2");
    }
    public Transfer initUDTransfer(String service_point_id,String date_time
            ,String doctor,String status,String ptid,String vid)
    {
        Transfer newTransfer = new Transfer();
        newTransfer.assign_time = date_time;
        newTransfer.doctor_code = doctor;
        newTransfer.patient_id = ptid;
        newTransfer.visit_id = vid;
        newTransfer.service_point_id = service_point_id;
        newTransfer.status = status;
        newTransfer.service_start_time = "";//date_time.substring(date_time.indexOf(',')+1);
        newTransfer.service_finish_time = "";
        return newTransfer;

    }
    public Transfer initTransfer(String service_point_id,String date_time,String doctor,String status)
    {
        return initUDTransfer(service_point_id,date_time,doctor,status
                ,thePatient.getObjectId(),theVisit.getObjectId());
    }
    /*
     *@author Pongtorn (henbe)
     *@name ใช้ในการคิดเงิน
     *@deprecated henbe unsued
     */
    public BillingOrderItem initBillingOrderItem(OrderItem orderItem,Payment payment
            ,SpecialContrctAdjustByVGaCT special)
    {
            /////////////////////////////////////////////////////////////////
         //หาส่วนลดของสิทธินั้น หารายการ orderitem นั้นว่าอยู๋ในสิทธหรือเปล่า
         String adjust = "0";
         String draw = "0";
         if(special != null){
             adjust = special.adjustment;
             draw = special.draw;
         }
         //กรองเฉพาะรายการที่มีส่วนลด และ รายการ order ที่ไม่ใช่ขอตรวจ
         BillingOrderItem boi = new BillingOrderItem();
         boi.theOrderItem = orderItem;
         boi.order_item_id = orderItem.getObjectId();
         boi.common_name = orderItem.common_name;
         boi.plan_id = payment.plan_kid;
         boi.payment_id = payment.getObjectId();
         boi.request = orderItem.request;
         boi.visit_id = orderItem.visit_id;
         boi.patient_id = theVisit.patient_id;
         boi.item_group_code_category = orderItem.item_group_code_category;
         boi.charge_complete = orderItem.charge_complete;
         boi.item_group_code_billing  = orderItem.item_group_code_billing;
         boi.item_id = orderItem.item_code;
         boi.billing_id = "";
         boi.draw = draw;
         updateBillingOrderItem(boi,orderItem, adjust);
         return boi;
    }
     public BillingOrderItem initBillingOrderItem(OrderItem orderItem,Payment payment
            ,Vector contractV)
    {
            /////////////////////////////////////////////////////////////////
         //หาส่วนลดของสิทธินั้น หารายการ orderitem นั้นว่าอยู๋ในสิทธหรือเปล่า
         ContractAdjust ca = null;
         for(int i=0;i<contractV.size();i++){
             ca = (ContractAdjust)contractV.get(i);
             if(ca.covered_id.equals(orderItem.item_group_code_category))
                 break;
         }
         String adjust = "0";
         String draw = "0";
         if(ca != null){
             adjust = ca.adjustment;
             draw = ca.draw;
         }
         //กรองเฉพาะรายการที่มีส่วนลด และ รายการ order ที่ไม่ใช่ขอตรวจ
         BillingOrderItem boi = new BillingOrderItem();
         boi.theOrderItem = orderItem;
         boi.order_item_id = orderItem.getObjectId();
         boi.common_name = orderItem.common_name;
         boi.plan_id = payment.plan_kid;
         boi.payment_id = payment.getObjectId();
         boi.request = orderItem.request;
         boi.visit_id = orderItem.visit_id;
         boi.patient_id = theVisit.patient_id;
         boi.item_group_code_category = orderItem.item_group_code_category;
         boi.charge_complete = orderItem.charge_complete;
         boi.item_group_code_billing  = orderItem.item_group_code_billing;
         boi.item_id = orderItem.item_code;
         boi.billing_id = "";
         boi.draw = draw;
         updateBillingOrderItem(boi,orderItem, adjust);
         return boi;
    }
    /*
     *@author Pongtorn (henbe)
     *@name ใช้ในการคิดเงิน
     */    
    public void updateBillingOrderItem(BillingOrderItem boi,OrderItem orderItem,String adjust)
    {
         if(orderItem.qty.equals(""))      orderItem.qty = "0";
         if(orderItem.price.equals(""))    orderItem.price = "0";

         double price = Math.ceil(Double.parseDouble(orderItem.qty)
                * Double.parseDouble(orderItem.price));
         double payerprice = Math.floor(price * Double.parseDouble(adjust)/100);
         double patientprice = price - payerprice;
         //ถ้าขอตรวจแม้ว่าลดก็จะคิดเงิน
         if(orderItem.request.equals("1")) 
         {
             patientprice = price;
             payerprice = 0;
         }
         boi.payershare= String.valueOf(payerprice);
         boi.patientshare = String.valueOf(patientprice);
    }   
    
    /**
     * ใช้ในการกรองสิทธิการรักษาของ payment ที่เป็นข้อมูลจริงๆ ที่ไม่เอาสิทธิที่ถูกยกเลิกมาแสดง
     * @return Vector ของ Payment
     * @author padungrat(tong)
     * @date 07/04/2549,11:38
     */
    public Vector getVisitPaymentActive()
    {
        if(vVisitPayment != null)
        {
            //if(showVisitPaymentCancel)
           // {
                Payment payment = null;
                for(int i = vVisitPayment.size()-1 ; i >=0;i--)
                {
                    payment = (Payment)vVisitPayment.get(i);
                    if(!com.hospital_os.utility.Gutil.isSelected(payment.visit_payment_active))
                    {
                        vVisitPayment.remove(i);
                    }
                    payment = null;
                }
                payment = null;
            //}
        }
        return vVisitPayment;
    }
    /*
     *@author Pongtorn (henbe)
     *@name ใช้ในการดึงข้อมูลสิทธิมาตรวจสอบ
     *@date 15/05/2549,11:38
     */    
    public Payment getPayment()
    {
        Payment pm=null;
        if(vVisitPayment!=null && !vVisitPayment.isEmpty())
        {
            pm = (Payment)vVisitPayment.get(0);
        }      
        if(pm==null && vPatientPayment!=null && !vPatientPayment.isEmpty())
        {
            pm = (Payment)vPatientPayment.get(0);
        } 
        return pm;
    }
    /*
     *@author Pongtorn (henbe)
     *@name ใช้ในการดึงข้อมูลสิทธิมาตรวจสอบ
     *@date 15/05/2549,11:38
     */ 
    public Refer initReferOut(Vector vrl)
    {        
        return getUpdateRefer(initRefer(),vrl, true);
    }

    
    public String getFamilyHistory()
    {
        String hf = "";
        if(vPersonalDisease != null && !vPersonalDisease.isEmpty()){
            if(!hf.equals(""))
                hf+= "\n";
            hf += "โรคประจำตัว" +": ";
            for(int i=0;i<vPersonalDisease.size();i++){
                PersonalDisease ph = (PersonalDisease)vPersonalDisease.get(i);
                hf += " " + ph.description;
            }
        }
        if(vRiskFactor != null && !vRiskFactor.isEmpty()){
            if(!hf.equals(""))
                hf+= "\n";
            hf += "ปัจจัยเสี่ยง" +": ";
            for(int i=0;i<vRiskFactor.size();i++){
                RiskFactor ph = (RiskFactor)vRiskFactor.get(i);
                hf += " " + ph.topic;
                if(!ph.description.equals(""))
                    hf += "-" + ph.description;
            }
        }
        if(vPastHistory != null && !vPastHistory.isEmpty()){
            if(!hf.equals(""))
                hf+= "\n";
            hf += "ประวัติในอดีต" +": ";
            for(int i=0;i<vPastHistory.size();i++){
                PastHistory ph = (PastHistory)vPastHistory.get(i);
                hf += " " + ph.topic;
                if(!ph.description.equals(""))
                    hf += "-" + ph.description;
            }
        }
        if(vFamilyHistory != null && !vFamilyHistory.isEmpty()){
            if(!hf.equals(""))
                hf+= "\n";
            hf += "ประวัติครอบครัว" +": ";
            for(int i=0;i<vFamilyHistory.size();i++){
                FamilyHistory ph = (FamilyHistory)vFamilyHistory.get(i);
                hf += " " + ph.topic;
                if(!ph.description.equals(""))
                    hf += "-" + ph.description;
            }
        }
        return hf;
    }
    public Refer getUpdateRefer(Refer theRefer,Vector vrl,boolean is_refer_out)
    {
        ///////////////////////////////////////////////////////////////////////////
        String current_symb="";
        String treatment = "";
        String lab = "";
        ///////////////////////////////////////////////////////////////////////////
        if(vPrimarySymptom!=null && !vPrimarySymptom.isEmpty()){
            PrimarySymptom ps = (PrimarySymptom)vPrimarySymptom.get(0);
            current_symb = ps.current_illness +  " \n " + ps.main_symptom;
        }
        ///////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////
        for(int i=0;vOrderItem!=null && i<vOrderItem.size();i++) {
            OrderItem oi = (OrderItem)vOrderItem.get(i);
            treatment = treatment + ",\n " + oi.common_name;
        }
        if(treatment.length()>3) {
            treatment = treatment.substring(3);
        }
        ///////////////////////////////////////////////////////////////////////////////    
        //tuk: 07/08/2549 ต้องการดึงข้อมูล Lab มาแสดงในส่วนของ refer 
        if(vrl!=null)
        {
            for(int j = 0, sizevrl = vrl.size(); j < sizevrl; j++)
            {
                ResultLab resultlab = (ResultLab)vrl.get(j);
                lab = lab  + ",\n " + resultlab.name + " : " + resultlab.result;
            }
        }
        if(lab.length() > 3)
            lab = lab.substring(3);
        ///////////////////////////////////////////////////////////////////////////////    
        if(is_refer_out)
        {
            theRefer.date_refer=date_time.substring(0,10);
            theRefer.first_dx = theVisit.doctor_dx;
            theRefer.doctor_refer = getDoctorIDInVisit();
            theRefer.reporter_refer = theEmployee.getObjectId();
            theRefer.history_current = current_symb;
            theRefer.history_family = getFamilyHistory();
            theRefer.first_treatment = treatment;
            theRefer.history_lab = lab;
        }
        else{
//            henbe comment for Data of Refer in
            theRefer.date_result=date_time.substring(0,10);
            theRefer.final_dx = theVisit.doctor_dx;
            theRefer.doctor_result = getDoctorIDInVisit();
            theRefer.reporter_result = theEmployee.getObjectId();
            theRefer.result_treatment = treatment;
            theRefer.result_lab = lab;
        }
        if(theRefer.first_treatment.length()>4000){
           theRefer.first_treatment = theRefer.first_treatment.substring(0,3900);
        }
        if(theRefer.result_lab.length()>4000){
           theRefer.result_lab = theRefer.result_lab.substring(0,3900);
        }
        return theRefer;
    }
    private Refer initRefer()
    {
        Refer theRefer = new Refer();
        if(thePatient==null){
            return null;
        }
        if(theVisit==null){
            return null;
        }
        theRefer.hn = thePatient.hn;
        theRefer.patient_id = thePatient.getObjectId();
        theRefer.vn = theVisit.vn;
        theRefer.vn_id = theVisit.getObjectId();
        theRefer.cause_refer="";
        theRefer.continue_treatment="";
        theRefer.refer_out = "1";
        theRefer.infectious="";
        theRefer.near_localtion="";
        theRefer.other_detail="";
        theRefer.refer_lab="";
        theRefer.refer_number="";
        theRefer.refer_observe="";
        theRefer.refer_result="";
        theRefer.refer_treatment="";
        theRefer.reporter_refer=theEmployee.getObjectId();
        theRefer.date_refer = date_time;
        theRefer.office_refer = theSite.off_id;
        theRefer.active = "1";
        ///////////////////////////////////////////////////////////////////////////////
        return theRefer;
    }
    public String getDoctorIDInVisit()
    {
        Vector v = getDoctorInVisit();
        if(!v.isEmpty())
            return (String)v.get(0);
        return "";
    }
    public Vector getDoctorOrder()
    {
        Vector vdoctor = new Vector();
        for(int i=0;vOrderItem != null && i<vOrderItem.size();i++){
            OrderItem oi = (OrderItem)vOrderItem.get(i);
            Employee emp = readEmployeeById(oi.vertifier);
            if(emp!=null && emp.authentication_id.equals(Authentication.DOCTOR))
                vdoctor.add(emp.getObjectId());
        }
        return vdoctor;
    }
    public Vector getDoctorDiag()
    {
        Vector vdoctor = new Vector();
        for(int i=0;vDiagIcd10 != null && i<vDiagIcd10.size();i++){
            DiagIcd10 oi = (DiagIcd10)vDiagIcd10.get(i);
            if(oi.type.equals(Dxtype.getPrimaryDiagnosis())){
                Employee emp = readEmployeeById(oi.doctor_kid);
                if(emp!=null)
                    vdoctor.add(emp.getObjectId());
            }
        }
        return vdoctor;
    }
    
    //pu:แพทย์ผู้ตรวจจากตาราง MapVisitDx กรณี Dx โดยใช้ตัวช่วย
    public Vector getDoctorMapVisitDx()
    {
        Vector vdoctor = new Vector();
        for(int i=0; vMapVisitDx != null && i<vMapVisitDx.size();i++)
        {
            MapVisitDx mvd = (MapVisitDx)vMapVisitDx.get(i);
            Employee emp = readEmployeeById(mvd.visit_diag_map_staff);
            //pu:เนื่องจาก MapVisitDx เก็บทั้งพยาบาลและแพทย์ แต่ visit slip ต้องการเฉพาะชื่อแพทย์
            if(emp!=null && emp.authentication_id.equals(Authentication.DOCTOR))
                vdoctor.add(emp.getObjectId());
        }
        return vdoctor;
    }
    
    //pu:แพทย์ผู้ตรวจจากตาราง Visit กรณี Dx โดยไม่ใช้ตัวช่วย
    public Vector getDoctorDx()
    {
        Vector vdoctor = new Vector();
        if(theVisit==null)
            return vdoctor;
        
        if(!this.theVisit.visit_patient_self_doctor.equals(""))
            vdoctor.add(this.theVisit.visit_patient_self_doctor);
        return vdoctor;
    }
    
    public Employee readEmployeeById(String id){
        for(int i=0;i<theLO.theEmployee.size();i++){
            Employee emp = (Employee)theLO.theEmployee.get(i);
            if(emp.getObjectId().equals(id))
                return emp;
        }
        return null;
    }
    /**
     *modify by pu:การแสดงชือแพทย์ใน visitSlip ให้ priority จาก 
     * แพทย์ผู้ให้ ICD10 ที่เป็น primary ,แพทย์ผู้ให้ยา ,แพทย์ผู้ diag ,และแพทย์ผู้ยืนยันการตรวจ ตามลำดับ
     */
    public Vector getDoctorInVisit()
    {
        Vector vDoctor = new Vector();
        //การส่งตัวผู้ป่วย
        vDoctor = this.getDoctorTransfer();
        if(!vDoctor.isEmpty())    return vDoctor;
        //pu:แพทย์ผู้ให้รหัส ICD10 ประเภท primary diag
        vDoctor = this.getDoctorDiag();
        if(!vDoctor.isEmpty())    return vDoctor;
        //pu:แพทย์ผู้ให้ยา
        vDoctor = getDoctorOrder();
        if(!vDoctor.isEmpty())    return vDoctor;
        //pu:แพทย์ผู้ตรวจจากตาราง MapVisitDx กรณี Dx โดยใช้ตัวช่วย
        vDoctor = this.getDoctorMapVisitDx();
        if(!vDoctor.isEmpty())    return vDoctor;
        //pu:แพทย์ผู้ตรวจจากตาราง Visit กรณี Dx โดยไม่ใช้ตัวช่วย
        vDoctor = this.getDoctorDx();
        if(!vDoctor.isEmpty())    return vDoctor;
//        if(vDoctor.isEmpty())
//            return this.getDoctorDiag();
//        if(vDoctor.isEmpty())
//            return this.getDoctorOrder();
        return vDoctor;
    }
    /**
     *
     * เมื่อรับข้อมูลจากผู้ใช้แล้วก็จะทำการกำหนดค่าตั้งต้นให้กับ visit ก่อนบันทึกลงฐานข้อมูล
     */
    public void initVisit(Visit visit,Patient patient)
    {
        updateVisit(visit,patient);
        if(visit.begin_visit_time.equals(""))
            visit.begin_visit_time = date_time;
        visit.lock_time = date_time;
        visit.lock_user = theEmployee.getObjectId();
        visit.locking = "1";
        visit.visit_type = "0";
        visit.queue_lab_status = QueueLabStatus.NOLAB;
        visit.deny_allergy = "0";
        visit.is_hospital_service= "0";
        if(theEmployee.authentication_id.equals(Authentication.ONE))
            visit.is_hospital_service= "1";
        visit.is_pcu_service= "0";
        if(theEmployee.authentication_id.equals(Authentication.HEALTH))
            visit.is_pcu_service= "1";
        //ตรวจสอบว่าเป็นการมาครั้งแรกหรือไม่
        visit.is_first = "0";
        if(patient.record_date_time!=null
        && patient.record_date_time.startsWith(date_time.substring(0,10)))
            visit.is_first = "1";
    }
    

    /**
     *
     * เมื่อรับข้อมูลจากผู้ใช้แล้วก็จะทำการกำหนดค่าตั้งต้นให้กับ visit ก่อนบันทึกลงฐานข้อมูล
     */
    public void updateVisit(Visit visit,Patient patient)
    {
        visit.patient_id = patient.getObjectId();
        visit.hn = patient.hn;
    }
    
    
    /**
     *@deprecated henbe unused
     **/
    public static String calculateAge(Patient patient,String date_time)
    {
        String patient_age = "";
        if(!patient.patient_birthday.equals("")) {
            if(patient.patient_birthday_true!=null && patient.patient_birthday_true.equals("1")){
                patient_age = DateUtil.calculateAgeShort1(patient.patient_birthday,date_time);
            }
            else {
                patient_age = DateUtil.calculateAge(patient.patient_birthday,date_time);
            }
        } 
        return patient_age;
    }
    public Refer initReferIn(Visit visit)
    {
        Refer theRefer = new Refer();
        theRefer.date_result=date_time;
        theRefer.date_refer= visit.begin_visit_time;
        theRefer.office_refer = visit.refer_in;
        theRefer.patient_id = visit.patient_id;
        theRefer.hn = visit.hn;
        theRefer.refer_out = "0";
        theRefer.cause_refer = visit.visit_note;
        theRefer.refer_treatment = "";
        theRefer.vn = visit.vn;
        theRefer.vn_id= visit.getObjectId();
        theRefer.active = "1";
        //tuk: 02/08/2549 เพิ่มการกำหนดค่าเริ่มต้นให้ Object
        theRefer.final_dx = visit.doctor_dx;
        if(theEmployee.authentication_id.equals(Authentication.DOCTOR))
            theRefer.doctor_result=theEmployee.getObjectId();
        else
        {
            Vector vDoctor = getDoctorInVisit();
            if(vDoctor!=null && !vDoctor.isEmpty())
            {
                String doctor = String.valueOf(vDoctor.get(0));
                theRefer.doctor_result=doctor;
            }
        } 
        //ถ้า vOrderItem ไม่เท่ากับ null sumo 25/08/2549
        if(vOrderItem != null)
        {
            for(int i=0,size=vOrderItem.size();i<size;i++)
            {
                OrderItem oi = (OrderItem)vOrderItem.get(i);
                theRefer.result_treatment = theRefer.result_treatment + ",\n " + oi.common_name;
            }
        }
        if(theRefer.result_treatment.length()>3)
            theRefer.result_treatment = theRefer.result_treatment.substring(3);
        return theRefer;
    }
    //คนๆ นั้นเป็นคนล้อกผู้ป่วยคนนี้หรือไม่
    public boolean isLockingVisit(){
        if(theVisit==null)
            return false;
        boolean is_lockbyme = theVisit.locking.equals("1")
            && theVisit.lock_user.equals(theEmployee.getObjectId());
        return is_lockbyme;
    }
    //คนไข้คนนี้ถูกล้อกอยู่โดยคนอื่นๆหรือไม่
    public boolean isLockingByOther(){
        return isLockingByOther(theVisit,theEmployee.getObjectId());
    }

    //คนไข้คนนี้ถูกล้อกอยู่โดยคนอื่นๆหรือไม่
    public static boolean isLockingByOther(Visit v,String eid){
        if(v==null)
            return false;
        boolean is_lockby_other = v.locking.equals("1")
            && !v.lock_user.equals(eid);
        return is_lockby_other;
    }
    String patient_age;
    public String getPatientAge(Patient thePatient)
    {
        if(thePatient.patient_birthday != null && !thePatient.patient_birthday.equals(""))
        {
            if(thePatient.patient_birthday_true.equals("0"))
            {
                patient_age = DateUtil.calculateAge(thePatient.patient_birthday,date_time);
                patient_age = patient_age + " " + Constant.getTextBundle("ปี");
                return patient_age;
            }
            else
            {
                patient_age = DateUtil.calculateAgeLong(thePatient.patient_birthday,date_time);
                return patient_age;
            }
        }
        return "";        
    }
    public String getYearAge()
    {
        return getYearAge(this.thePatient);
    }
    public String getYearAge(Patient thePatient)
    {
        if(thePatient.patient_birthday_true.equals("0"))
            return patient_age + " " + Constant.getTextBundle("ปี");
        else
            return Constant.getYear(patient_age);
        
    }
    
    public String getMonthAge()
    {
        return getMonthAge(this.thePatient);
    }

    public String getMonthAge(Patient thePatient)
    {
        if(thePatient.patient_birthday_true.equals("0"))
            return "";
        else
            return Constant.getMonth(patient_age);
    }
    
    public String getDayAge()
    {
        return getDayAge(this.thePatient);
    }
    public String getDayAge(Patient thePatient)
    {
        if(thePatient.patient_birthday_true.equals("0"))
            return "";
        else
            return Constant.getDay(patient_age);
    }
    
   /** 
     * ใช้ในการ add ข้อมูลลงตาราง DiagICD10 ของผู้ป่วยเลย
     * @param mapVisitDx เป็น Object ของ MapVisitDx ที่ใช้เก็บข้อมูลการ Dx
     * @author padungrat(tong)
     * @date 24/03/49,16:25
     */
    public DiagIcd10 initDiagICD10(MapVisitDx mapVisitDx,String doctor_id
    ,String clinic_id)
    {
        return initDiagICD10(mapVisitDx.visit_id,mapVisitDx.visit_diag_map_icd
                ,doctor_id,clinic_id);
    }
    public DiagIcd10 initDiagICD10(String visit_id,String icd_kid,String doctor_id
            ,String clinic_id)
    {
        DiagIcd10 diagICD10 = initDiagIcd10();
        diagICD10.clinic_kid = clinic_id;
        diagICD10.doctor_kid = doctor_id;
        diagICD10.icd10_code = icd_kid;
        diagICD10.visit_id = visit_id;
        return diagICD10;
    }
    public MapVisitDx initMapVisitDx(DxTemplate dxt)
    {
        for(int i=0;!vMapVisitDx.isEmpty()&& i<vMapVisitDx.size();i++)
        {
            MapVisitDx mvd = (MapVisitDx)vMapVisitDx.get(i);
            if(dxt.getObjectId().equals(mvd.dx_template_id)){
                return null;
            }
        }
        MapVisitDx mvd = new MapVisitDx();
        mvd.visit_id = theVisit.getObjectId();
        mvd.dx_template_id = dxt.getObjectId();
        mvd.t_patient_id = thePatient.getObjectId();
        mvd.visit_diag_map_active = Active.isEnable();
        mvd.visit_diag_map_date_time = date_time;
        mvd.visit_diag_map_dx = dxt.description;
        mvd.visit_diag_map_icd = dxt.icd_code;
        mvd.visit_diag_map_staff = theEmployee.getObjectId();
        return mvd;
    }
    public String getDrugAllergyString()
    {
        if(vDrugAllergy == null) 
            return "";
        
        String std_old = "";
        for(int j=0,sizej=vDrugAllergy.size(); j<sizej; j++) 
        {    
            DrugAllergy drugAllergy = (DrugAllergy)vDrugAllergy.get(j);
            if(j==0)
                std_old = drugAllergy.common_name ;
            std_old += "," + drugAllergy.common_name ;
        }
        return std_old;
    }
    public String getPastHistoryString()
    {
        if(vPastHistory == null) 
            return "";
        String past_history = "";
        for(int j=0,sizej=vPastHistory.size(); j<sizej; j++) 
        {
            PastHistory pastHistory = (PastHistory)vPastHistory.get(j);
            if(past_history.equals("")) {
                past_history = pastHistory.topic + " : " +pastHistory.description;
            } else {
                past_history = past_history + "\n" +pastHistory.topic + " : " +pastHistory.description;
            }
        }
        return past_history;
    }
    public String getFamilyHistoryString()
    {
        if(vFamilyHistory == null) {
            return "";
        }
        String family_history = "";
        for(int j=0,sizej=vFamilyHistory.size(); j<sizej; j++) {
            FamilyHistory familyHistory = (FamilyHistory)vFamilyHistory.get(j);
            if(family_history.equals(""))
                family_history = familyHistory.topic + " : " +familyHistory.description;
            else 
                family_history = family_history + "\n" +familyHistory.topic + " : " +familyHistory.description;
        }
        return family_history;
    }
    public String getPersonalDiseaseString()
    {
        if(vPersonalDisease == null) {
            return "";
        }
        String personal_disease = "";
        for(int j=0,sizej=vPersonalDisease.size(); j<sizej; j++) {
            PersonalDisease personalDisease = (PersonalDisease)vPersonalDisease.get(j);
            if(personal_disease.equals("")) {
                personal_disease = personalDisease.description;
            } else {
                personal_disease = personal_disease + "\n" + personalDisease.description;
            }
        }
        return personal_disease;
    }
    public String getRiskFactorString()
    {
        if(vRiskFactor == null) {
            return "";
        }
        String risk_factor = "";
        for(int j=0,sizej=vRiskFactor.size(); j<sizej; j++) {
            RiskFactor riskFactor = (RiskFactor)vRiskFactor.get(j);
            if(risk_factor.equals("")) {
                risk_factor = riskFactor.topic + " : " + riskFactor.description;
            } else {
                risk_factor = risk_factor + "\n" + riskFactor.topic + " : " +riskFactor.description;
            }
        }        
        return risk_factor;
    }
    public ReceiptItem initReceiptItem(BillingInvoiceItem bii,Receipt receipt)
    {
        ReceiptItem ri = initReceiptItem(bii,receipt,0);
        ri.paid = bii.patient_share;
        return ri;
    }
    public ReceiptItem initReceiptItem(BillingInvoiceItem bii,Receipt receipt,double paid)
    {
        ReceiptItem bri = new ReceiptItem();
        bri.active = "1";
        bri.hn = "";
        bri.vn = "";
        bri.draw = bii.draw;
        bri.visit_id = bii.visit_id;
        bri.item_id = bii.item_id;
        bri.billing_invoice_item_id = bii.getObjectId();
        bri.patient_id = bii.patient_id;
        bri.payment_id = bii.payment_id;
        bri.receipt_id = receipt.getObjectId();
        bri.paid = String.valueOf(paid);
        return bri;
    }
    public Receipt initReceipt(Billing billing)
    {
        Receipt receipt = initReceipt(billing, 0);
        receipt.paid = billing.patient_share;
        return receipt;
    }
    public Receipt initReceipt(Billing billing,double get_paid)
    {
        Receipt theReceipt = new Receipt();
        theReceipt.active = Active.isEnable();
        theReceipt.hn = billing.patient_id;
        theReceipt.paid = String.valueOf(get_paid);
        theReceipt.receipt_date = date_time;
        theReceipt.vn = billing.visit_id;
        theReceipt.billing_id = billing.getObjectId();
        theReceipt.staff_receipt = theEmployee.getObjectId();
        return theReceipt;
    }
    public ReceiptSubgroup initReceiptSubgroup(BillingInvoiceSubgroup bis,Receipt theReceipt)
    {
        ReceiptSubgroup receipt = initReceiptSubgroup(bis, theReceipt,0);
        receipt.paid = bis.patient_share;
        return receipt;
    }
    public ReceiptSubgroup initReceiptSubgroup(BillingInvoiceSubgroup bis,Receipt theReceipt,double paid)
    {
        ReceiptSubgroup rs = new ReceiptSubgroup();
        rs.billing_invoice_subgroup_id = bis.getObjectId();
        rs.receipt_id = theReceipt.getObjectId();
        rs.hn = bis.patient_id;
        rs.vn = bis.visit_id;
        rs.item_group_code_billing =  bis.billing_group_item_id;
        rs.draw = bis.draw;
        rs.active = Active.isEnable();
        rs.paid = String.valueOf(paid);
        return rs;
    }

    public String getClinicByPrimaryDx(Vector vDiagIcd10) {
        for(int i=0;i<vDiagIcd10.size();i++){
            DiagIcd10 dx10 = (DiagIcd10)vDiagIcd10.get(i);
            if(dx10.type.equals(Dxtype.getPrimaryDiagnosis()))
                return dx10.clinic_kid;
        }
        return null;
    }

    public Visit getVisit(){
        return theVisit;
    }
    public boolean readSuitPatientByHN(String hn){
        return false;
    }
    public boolean readSuitPatientByPID(String pid){
        return false;
    }
    public boolean isVisitLocking() {
        return isLockingByOther();
    }
 /**
     *@Author amp
     *@date 27/03/2549
     *@see init object OrderDrugInteraction
     */
    public OrderDrugInteraction initOrderDrugInteraction(DrugInteraction drugInteraction,String interactionItemId,boolean is_interaction) throws Exception
    {
        OrderDrugInteraction theOrderDrugInteraction = new OrderDrugInteraction();
        theOrderDrugInteraction.interaction_item_id = interactionItemId;
        if(is_interaction)//ตัวตั้งต้นเป็นตัวที่มีปฏิกิริยาด้วย
        {
            theOrderDrugInteraction.order_item_drug_standard_id = drugInteraction.drug_standard_interaction_id;
            theOrderDrugInteraction.order_item_drug_standard_description = drugInteraction.drug_standard_interaction_description;
            if(!"".equals(interactionItemId))
            {
                theOrderDrugInteraction.interaction_item_drug_standard_id = drugInteraction.drug_standard_original_id;
                theOrderDrugInteraction.interaction_item_drug_standard_description = drugInteraction.drug_standard_original_description;
            }
        }
        else
        {
            theOrderDrugInteraction.order_item_drug_standard_id = drugInteraction.drug_standard_original_id;
            theOrderDrugInteraction.order_item_drug_standard_description = drugInteraction.drug_standard_original_description;
            if(!"".equals(interactionItemId))
            {
                theOrderDrugInteraction.interaction_item_drug_standard_id = drugInteraction.drug_standard_interaction_id;
                theOrderDrugInteraction.interaction_item_drug_standard_description = drugInteraction.drug_standard_interaction_description;
            }
        }
        theOrderDrugInteraction.interaction_blood_presure = drugInteraction.blood_presure;
        theOrderDrugInteraction.interaction_pregnant = drugInteraction.pregnant;
        theOrderDrugInteraction.interaction_type = drugInteraction.type;
        theOrderDrugInteraction.interaction_force = drugInteraction.force;
        theOrderDrugInteraction.interaction_act = drugInteraction.act;
        theOrderDrugInteraction.interaction_repair = drugInteraction.repair;
        theOrderDrugInteraction.active = Active.isEnable();
        theOrderDrugInteraction.visit_id = theVisit.getObjectId();
        return  theOrderDrugInteraction;              
    }  
    
    public Surveil initSurveil()
    {
        Surveil theSurveil = new Surveil();
        theSurveil.vn_id = "";
        theSurveil.vn = "";
        if(theVisit!=null)
        {
            theSurveil.vn_id = theVisit.getObjectId();
            theSurveil.vn = theVisit.vn;
        }
        theSurveil.patient_id = "";
        theSurveil.hn = "";
        if(thePatient!=null)
        {
            theSurveil.patient_id = thePatient.getObjectId();
            theSurveil.hn = thePatient.hn;
        }
        return theSurveil;
      }

    private Vector getDoctorTransfer() {
        
        Vector vDoctor = new Vector();
        for(int i=0;vTransfer!=null && i<vTransfer.size(); i++)
        {
            Transfer transfer = (Transfer)vTransfer.get(i);
            if(!"".equals(transfer.doctor_code) && !"null".equals(transfer.doctor_code))
            {   //check ซ้ำ
                if(!vDoctor.isEmpty())
                {
                    for(int j=0; j<vDoctor.size(); j++)
                    {
                        if(transfer.doctor_code.equals(vDoctor.get(j)))
                            vDoctor.remove(j);
                    }
                    vDoctor.addElement(transfer.doctor_code);
                }
                else vDoctor.addElement(transfer.doctor_code);
            }
        }
        return vDoctor;
    }

    public String getDiagIcd9Code(String optype) {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<vDiagIcd9.size();i++){
            DiagIcd9 dx10 = (DiagIcd9)vDiagIcd9.get(i);
            if(dx10.type.equals(optype)){
                if(i>0)
                    sb.append(",");
                sb.append(dx10.icd9_code);
            }
        }
        return sb.toString();
    }

    public String getDiagIcd10Code(String dxtype) {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<vDiagIcd10.size();i++){
            DiagIcd10 dx10 = (DiagIcd10)vDiagIcd10.get(i);
            if(dx10.type.equals(dxtype)){
                if(i>0)
                    sb.append(",");
                sb.append(dx10.icd10_code);
            }
        }
        return sb.toString();
    }
    public boolean scrollPaneDrug;
    public boolean scrollPaneVitalSign;
    public boolean scrollPaneLab;
    public boolean scrollPaneXray;
    public static String opatient=null;
 
}
