/*
 * PanelCurrentVisit.java
 *
 * Created on 8 พฤษภาคม 2548, 21:09 น.
 */

package com.hosv3.gui.panel.transaction;

import com.hosv3.object.*;
import com.hosv3.subject.*;
import com.hosv3.control.*;
import com.hosv3.gui.dialog.*;
import com.hosv3.utility.*;
import com.hosv3.utility.connection.*;
import com.hosv3.usecase.transaction.*;

import com.hospital_os.object.*; 
import com.hospital_os.utility.ComboboxModel;
import com.hospital_os.utility.Gutil;
import com.hosv3.utility.Constant;
import com.pcu.object.Family;
import java.awt.*;
import java.text.*;
import java.util.*;

/**l.
 *
  * @author  administrator
 */
/**
 * 
 * @author ekapop
 * 1. เรื่อง AttachNote  60-10-22
 * 
 * Modify doc 5.
 * 9. 60-10-25 เรื่อง แสดงอายุ ผู้ป่วย ให้แสดงเป็น ปี เดือน วัน
 * Modify doc 9.
 */
public class PanelCurrentVisit extends javax.swing.JPanel 
implements ManagePatientResp,ManageVisitResp,ManageVitalResp
,ManageOrderResp,ManageBillingResp,ManageLabXrayResp,ManageVPaymentResp
{
	static final long serialVersionUID = 0;
    HosObject theHO;
    HosControl theHC;
    HosSubject theHS;
    HosDialog theHD;
    UpdateStatus theUS;
    DiagnosisControl theDiagnosisControl;
    
    String auth;
    Visit theVisit;
    Patient thePatient;
    Vector theTransferV;
    Transfer theTransfer = new Transfer();


    private boolean show = false;

    private LookupControl theLookupControl;

    private Family theFamily;

    private PanelPatientHistory thePPH;
    
    /** Creates new form PanelCurrentVisit */
    public PanelCurrentVisit() 
    {
        initComponents();
        //jTextFieldIOpd.setEnabled(false);
        //jTextFieldDBilling.setEnabled(false);
        //jTextFieldDDoctor.setEnabled(false);
        jLabelReturnDrug.setVisible(false);
        jLabelPtAllergyValue.setVisible(false);
        jLabelDischarge.setVisible(false);
        jLabelRemain.setVisible(false);
        jLabelDoctor.setVisible(false);
        jLabelLockUser.setVisible(false);
        jLabelPersonalDisease.setVisible(false);
        jLabelAppointment.setVisible(false);
        btnNote.setEnabled(false);
        jLabelAttachNote.setText("");       //+1
    }
    public void setControl(HosControl hc,UpdateStatus us)
    {
        theHC = hc;
        theHO = hc.theHO;
        theHS = hc.theHS;
        theUS = us;
        auth = theHO.theEmployee.authentication_id;
        
        theLookupControl = hc.theLookupControl;
        
        theHS.thePatientSubject.attachManagePatient(this);
        theHS.theVisitSubject.attachManageVisit(this);
        theHS.theVitalSubject.attachManageVital(this);
        theHS.theOrderSubject.attachManageOrder(this);
        theHS.theBillingSubject.attachManageBilling(this);  
        theHS.theResultSubject.attachManageLab(this);
        theHS.theResultSubject.attachManageXray(this);
        theHS.theVPaymentSubject.attach(this);
        if(theHO.theEmployee.authentication_id.equals(Authentication.XRAY) 
            || theHO.theEmployee.authentication_id.equals(Authentication.LAB))
        {
            jComboBoxSendToLocation.setEnabled(false);
            jButtonSend.setEnabled(false);
        }
        initComboBox();
        setVisit(null);
        setLanguage(null);
     }
/*
        *neung
        *ทำการตรวจสอบว่าผู้ใช้เป็นใคร
        *และไม่สามาทำรถอะไรได้บ้าง ก็จะทำการเซตvisitbleปุ่มนั้นไป
        */
    public void initComboBox()
    {
        ComboboxModel.initComboBox(jComboBoxSendToLocation,theHC.theLookupControl.listServicePointwithOutXL());
    }
    public void setDialog(HosDialog hd){
        theHD = hd;
    }
    
    public void setEnabled(boolean b)
    { 
        boolean is_labxray = theHO.theEmployee.authentication_id.equals(Authentication.LAB)
                         || theHO.theEmployee.authentication_id.equals(Authentication.XRAY);
        b = b && !is_labxray;
        jComboBoxSendToLocation.setEnabled(b);
        ServicePoint sp = (ServicePoint)jComboBoxSendToLocation.getSelectedItem();
        boolean is_diag = sp.service_type_id.equals(ServiceType.DIAG);
        jComboBoxSendToDoctor.setEnabled(b && is_diag);
        //henbe comment 21/3/2549
        jButtonSend.setEnabled(b);
        //jButtonUnlock.setEnabled(b);
    }
    
    private void getTransfer(Transfer t)
    {
        t.service_point_id = Gutil.getGuiData(jComboBoxSendToLocation);
        t.doctor_code = "";
        if(jComboBoxSendToDoctor.isEnabled())
            t.doctor_code = Gutil.getGuiData(jComboBoxSendToDoctor);
        
    }
    
    private void setAllergyV(Vector allergy)
    {      
        if(!"".equals(theHO.orderSecret))//amp:07/03/2549 เพื่อซ่อนชื่อผู้ป่วยในกรณีมีแลปปกปิด
        {
             jLabelPtAllergyValue.setVisible(false); 
             return;
        }
        if(allergy==null || allergy.size()==0)
        {
            jLabelPtAllergyValue.setVisible(false);            
            this.jLabelPtAllergyValue.setToolTipText(""); 
            return;
        }
        jLabelPtAllergyValue.setVisible(true);
        String drugAllergy = "<HTML><BODY BGCOLOR = #FFFFFF ><FONT COLOR = RED><B>"
                + theHC.thePrintControl.getPatientAllergy(allergy,"<br>")
                + "<B></FONT></BODY></HTML>";
        jLabelPtAllergyValue.setToolTipText(drugAllergy); 
    }    
    
    private void setPersonalDiseaseV(Vector allergy)
    {      
        jLabelPersonalDisease.setVisible(false);
        if(allergy==null || allergy.isEmpty())
            return;
        String drug = "";
        jLabelPersonalDisease.setVisible(true);
        for(int i=0 ;i<allergy.size(); i++)
        {  
            PersonalDisease da = (PersonalDisease)allergy.get(i);
            drug = drug  + (i+1) + ". " + da.description + "<BR>";
        }
        drug = "<HTML><BODY>"+ drug + "</BODY></HTML>";
        jLabelPersonalDisease.setToolTipText(drug); 
    }    
    /**
     *@Author : amp
     *@date : 15/02/2549
     *@see : แสดงชื่อแพทย์ที่ผู้ป่วยเข้าพบ
     */
    private void setDoctor(Vector vTransfer)
    {
        if(this.theVisit==null)
        {
            jLabelDoctor.setVisible(false);
            return;
        }
        if(vTransfer == null)
        { 
            jLabelDoctor.setVisible(false);
            return;
        }
        // Henbe ย้ายฟังชันค้นแพทย์ของแอ้มไปไว้ใน theHO.getDoctorInVisit(); เพื่อให้เพื่อนๆ ได้นำไปใช้บ้างนะ
        // เพราะว่ามันมีประโยชน์มากถ้าอยู่ที่นั้น
        Vector vDoctor = theHO.getDoctorInVisit();
        if(vDoctor.isEmpty() || vDoctor == null) 
        {
            jLabelDoctor.setVisible(false); 
//            Constant.println("--------------------------- vDoctor.isEmpty()");
            return;
        }
        jLabelDoctor.setVisible(true);
        String nameDoctor = "";
        for(int i=0,size=vDoctor.size(); i<size; i++)
        {            
            Employee em = theHC.theLookupControl.readEmployeeById((String)vDoctor.get(i));
            if(em!=null)
            {
                if(i==0) nameDoctor = em.fname + " " + em.lname;
                else nameDoctor = nameDoctor + "<BR>" + em.fname + " " + em.lname;
            }
        }
        jLabelDoctor.setToolTipText("<HTML><BODY BGCOLOR = #FFFFFF ><FONT COLOR = RED><B>"+ nameDoctor + "<B></FONT></BODY></HTML>");
    }

        
    private void setPatient(Patient p)
    {   
        thePatient = p;
        jTextFieldHN.setText("");
        jLabelNCDCode.setText("");
        jLabelRemain.setVisible(false);
        if(p==null) 
            return;

        String name = "";
        if(p.active.equals("0"))
            name = " (ยกเลิกผู้ป่วย)";
        
//        jLabelPatientNameValue.setText(jLabelPatientNameValue.getText() + name);
        if(!"".equals(p.hn))
            jTextFieldHN.setText(theLookupControl.getRenderTextHN(p.hn));
        setTextNCD();
        String data = theHC.theBillingControl.getBillRemaining(theHO.vBillingPatient);
        if(data.length()>0) {
            jLabelRemain.setVisible(true);
            jLabelRemain.setToolTipText(data);
        }
        
    }
    
    private void setFamily(Family person)
    {
        theFamily = person;
        if(theFamily==null){
            jLabelPatientNameValue.setText("");
            jLabelAge.setText("");
            jLabelDischarge.setVisible(false);
            return;
        }
        String name = theHC.theLookupControl.readPrefixString(theFamily.f_prefix_id)
            + theFamily.patient_name + "  " + theFamily.patient_last_name + " ";
        if(theFamily.active.equals("0"))
            name += " (ยกเลิก)";

        jLabelPatientNameValue.setText(name);
        if(theFamily.patient_birthday != null && !theFamily.patient_birthday.equals(""))
        {
            if(theFamily.patient_birthday_true.equals("1"))
            {
                String age = DateUtil.calculateAgeLong(theFamily.patient_birthday,theHO.date_time);
                jLabelAge.setText(Constant.getTextBundle("อายุ") + " " + age);
            }
            else
            {
                String age = DateUtil.calculateAge(theFamily.patient_birthday,theHO.date_time);
                jLabelAge.setText(Constant.getTextBundle("อายุ") + " "
                        + age+" " + Constant.getTextBundle("ปี"));
            }
        }
        jLabelDischarge.setVisible(theFamily.discharge_status_id.equals(Dischar.DEATH)); 
    }

    private boolean setPaymentV(Vector p)
    {
        if(theVisit==null){
            jLabelPlan.setText("");
            jLabelPlan.setToolTipText("");
            return false;
        }
        jLabelPlan.setText(Constant.getTextBundle("ไม่พบสิทธิ์การรักษา"));
        jLabelPlan.setToolTipText(null);
        //////////////////////////////////////////////////////////
        Vector thePayment = p;
        String ttt = new String();
        String plan_desc = Constant.getTextBundle("ไม่พบในฐานข้อมูล");
        if(thePayment==null)
            thePayment = new Vector();
        
        for(int i=0,size=thePayment.size();i<size;i++)
        {
            Payment pm = (Payment)thePayment.get(i);
            if(pm.visit_payment_active.equals("1"))
            {
                plan_desc = theHC.theLookupControl.readPlanString(pm.plan_kid);
                if(i==0)
                    jLabelPlan.setText(plan_desc);    
                ttt = ttt +","+ plan_desc;
            }
        }
        //remove , from ttt
        if(ttt.length()>1)
            ttt = ttt.substring(1);
        jLabelPlan.setToolTipText(ttt);
        return true;
    }
   
    private void setPatientPaymentV(Vector p)
    {
        if(p==null || p.isEmpty()){
            jLabelPlan.setText("");
            jLabelPlan.setToolTipText("");
            return;
        }
        jLabelPlan.setText(Constant.getTextBundle("ไม่พบสิทธิ์การรักษา"));
        jLabelPlan.setToolTipText(null);
        //////////////////////////////////////////////////////////
        Vector thePayment = p;
        String ttt = new String();
        String plan_desc = Constant.getTextBundle("ไม่พบในฐานข้อมูล");
        if(thePayment==null)
            thePayment = new Vector();
        for(int i=0,size=thePayment.size();i<size;i++)
        {
            String plan_kid_visit = ((PatientPayment)thePayment.get(i)).plan_kid;                 
            Plan plan = theHC.theLookupControl.readPlanById(plan_kid_visit);    
        ///////////////////////////////////////////////////////////
            if(plan != null)  plan_desc = plan.description;
        ///////////////////////////////////////////////////////////
            if(i==0) jLabelPlan.setText(plan_desc);
            ttt = ttt + "," + plan_desc;
        }
        //remove , from ttt
        if(ttt.length()>1)
            ttt = ttt.substring(1);
        jLabelPlan.setToolTipText(ttt);
    }
   
    private void setVisit(Visit visit)
    {
        theVisit = visit;
        if(theVisit==null)
        {            
            jTextFieldVN.setText("");
            jLabelDx.setText("...");
            jTextFieldDDoctor.setVisible(false);
            jTextFieldDBilling.setVisible(false);
            jTextFieldIOpd.setText("");
            jTextFieldIOpd.setToolTipText(null);
            jLabelReturnDrug.setVisible(false);
            jLabelDoctor.setVisible(false);
            //sumo:17/06/2549 เพื่อซ่อนสถานะการล็อกผู้ป่วย
            jLabelLockUser.setToolTipText("");
            jLabelLockUser.setVisible(false);
            jLabelRefer.setVisible(false);
            jLabelVisitDate.setVisible(false);
            jLabelAppointment.setVisible(false);
            setEnabled(false);
            return;
        }        
            jLabelDx.setText(Gutil.getTextLabel(theVisit.doctor_dx));  
            jLabelDx.setToolTipText(Gutil.getToolTipText(theVisit.doctor_dx));  
            jTextFieldVN.setText(theLookupControl.getRenderTextVN(theVisit.vn));
        ///////////////////////////////////////////////////////////////////
        Employee e=theHC.theLookupControl.readEmployeeById(theVisit.lock_user);
        String name;
        if(e!=null)  name = e.fname + "   " + e.lname;
        else         name = theVisit.lock_user;
        if(theVisit.isDropVisit())
            jLabelPatientNameValue.setText(jLabelPatientNameValue.getText() + " (ยกเลิกการรับบริการ)");
        String final_appoint_date = theHC.thePatientControl.getFinalAppointDate2(theUS);
        int appoint_day = DateUtil.countDateDiff(final_appoint_date,theHC.theConnectionInf);
        if(appoint_day > 0)
        {
            jLabelAppointment.setVisible(true);
//            jLabelAppointment.setText("วันนัดอีก " + appoint_day + " วัน");
            jLabelAppointment.setText(appoint_day +" "+ Constant.getTextBundle(" วัน ถึงวันที่นัด"));
//            jLabelAppointment.setToolTipText("อีก " + appoint_day + " วัน ถึงกำหนดวันนัดครั้งล่าสุด (" + final_appoint_date + ")");
        }
        else
        {
            jLabelAppointment.setVisible(false);
        }
        if(theHO.theFinalAppointMent!=null&&theHO.theFinalAppointMent.status.equals(AppointmentStatus.CANCEL))
        {
            jLabelAppointment.setVisible(false);
        }
        jLabelLockUser.setVisible(false);
        if(theVisit.locking.equals("1")) 
        {   
            if(!theVisit.lock_user.equals(theHO.theEmployee.getObjectId())){
                jLabelLockUser.setVisible(true);
                jLabelLockUser.setText(name);
                jLabelLockUser.setToolTipText(Constant.getTextBundle("ล็อกโดย ")+ name + " " + Constant.getTextBundle("เมื่อ") + " "
                    +  DateUtil.getDateToString(DateUtil.getDateFromText(theVisit.lock_time),true));
            }
        }
        
//        jLabelPlan.setText(Constant.getTextBundle("ไม่ระบุสิทธิ์การรักษา"));          

 
        jLabelRefer.setVisible(false);
        if(theHO.theReferIn!=null){
                Office office = theHC.theLookupControl.readHospitalByCode(theHO.theReferIn.office_refer);
                if(office!=null)
                {
                    jLabelRefer.setToolTipText(Constant.getTextBundle("รับจาก") + " "+office.name);
                }
                
                jLabelRefer.setVisible(true);
        }
        if(theHO.theReferOut!=null){
                Office office = theHC.theLookupControl.readHospitalByCode(theHO.theReferOut.office_refer);
                if(office!=null)
                {
                    if(theHO.theReferIn!=null)
                        jLabelRefer.setToolTipText(jLabelRefer.getToolTipText() + Constant.getTextBundle("ส่งต่อ") + " "+office.name);
                    else
                        jLabelRefer.setToolTipText(Constant.getTextBundle("ส่งต่อ") + " "+office.name);
                }
                jLabelRefer.setVisible(true);
        }
        ///////////////////////////////////////////////////////////////////
        jLabelVisitDate.setVisible(true);
        Date date = DateUtil.getDateFromText(visit.begin_visit_time);
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy");
        jLabelVisitDate.setText(formatter.format(date));
        ///////////////////////////////////////////////////////////////////
        if(visit.is_discharge_money.equals(Active.isEnable())) {
            jTextFieldDBilling.setToolTipText(Constant.getTextBundle("จำหน่ายทางการเงินเสร็จสิ้น"));
            jTextFieldDBilling.setBackground(Color.red);
        }
        else {
            jTextFieldDBilling.setToolTipText(Constant.getTextBundle("รอจำหน่ายทางการเงิน"));
            jTextFieldDBilling.setBackground(Color.green);
        }
        jTextFieldDBilling.setVisible(true);
        ///////////////////////////////////////////////////////////////////
        jTextFieldDDoctor.setToolTipText(Constant.getTextBundle("รอจำหน่ายทางการแพทย์"));
        jTextFieldDDoctor.setBackground(Color.green);
        jTextFieldDDoctor.setVisible(true);
        if(visit.is_discharge_doctor.equals(Active.isEnable())) {
            String display = Constant.getTextBundle("จำหน่ายทางการแพทย์แล้ว");
            if(theVisit.visit_type.equals(VisitType.IPD))
            {
                display = theVisit.discharge_ipd_type;
                DischargeIpd disI = theHC.theLookupControl.readDischargeIpdById(theVisit.discharge_ipd_type);
                if(disI!=null)
                    display = disI.description;      
            }
            else {
                display = theVisit.discharge_opd_status;
                DischargeOpd disO = theHC.theLookupControl.readDischargeOpdById(theVisit.discharge_opd_status);
                if(disO!=null){
                    display = disO.description;
                    if(disO.getObjectId().equals(DischargeOpd.REFER))
                        display +=theHC.theLookupControl.readHospitalSByCode(theVisit.refer_out);
                }
            }
            jTextFieldDDoctor.setToolTipText(display);
            jTextFieldDDoctor.setBackground(Color.red);
        }
        ///////////////////////////////////////////////////////////////////
        

        if(visit.visit_type.equals(VisitType.IPD)){
            String status_ipd = " IPD ";
            if(visit.is_discharge_ipd.equals("1"))
                status_ipd = " IPD Disch ";
            jTextFieldIOpd.setText(status_ipd);
            jTextFieldIOpd.setToolTipText(status_ipd);
            jTextFieldVN.setForeground(Color.RED);
        }
        else{
            jTextFieldVN.setForeground(Color.BLACK);
            jTextFieldIOpd.setText(" OPD ");
            jTextFieldIOpd.setToolTipText("OPD");
            if(visit.observe.equals("1"))
            {
                jTextFieldIOpd.setText(" " + Constant.getTextBundle("ฝากนอน") + " ");
                jTextFieldIOpd.setToolTipText(Constant.getTextBundle("ฝากนอน"));
            }
        }
        ///////////////////////////////////////////////////////////////////
        //list OrderItemReceiveDrug by Visit_id ReceiveQty
        Vector voird = theHO.vOrderItemReceiveDrug;
        
        boolean is_return_drug = false;
        // ถ้า voird ไม่เท่ากับ null sumo 25/08/2549
        if(voird!=null )
        {
            for(int i=0,size=voird.size();i<size;i++)
            {
                OrderItemReceiveDrug oird = (OrderItemReceiveDrug)voird.get(i);
                if(oird.qty_receive.equals("null") || oird.qty_receive.equals("")
                || Double.parseDouble(oird.qty_receive) < Double.parseDouble(oird.qty_return))
                        is_return_drug = true;
            }
        }
        jLabelReturnDrug.setVisible(is_return_drug);
        //เมื่อย้อนกลับทางการแพทย์ ผู้ป่วยที่เคยมีสถานะเสียชีวิต จะต้องไม่แสดงรูปหัวกะโหลก
        if(theHO.theFamily.discharge_status_id.equals("0"))
        {
            jLabelDischarge.setVisible(false);
        }
        ///////////////////////////////////////////////////////////////////
        if(theVisit.isLockingByOther(theHO.theEmployee.getObjectId())
        || theVisit.isOutProcess()
        || theVisit.isDischargeMoney()
        || theVisit.isInStat()
        || theVisit.isDropVisit()) {
            setEnabled(false);
            return;
        }
        
        jLabelAttachNote.setText(theHC.theNotifyNoteControl.listNotifyNoteFirst(visit.hn));     //+1
        setEnabled(true);
    }
    
    /**
     *@Author: amp
     *date: 4/8/2549
     *see: แสดงหมายเลข NCD ถ้าผู้ป่วยอยู่ในกลุ่ม NCD
     */
    private void setTextNCD()
    {
        jLabelNCDCode.setText("");
        if(theHO.vNCD != null /*&& theHO.theVisit.ncd.equals("1")*/)
        {
            String ncd_code = "";
            for(int i=0,size=theHO.vNCD.size(); i<size; i++)   
            {
                NCD ncd_data = (NCD)theHO.vNCD.get(i);
                if(i==0)
                {
                    ncd_code = ncd_code + ncd_data.ncd_number;
                    jLabelNCDCode.setText("NCD: " + ncd_code);
                }
                else
                {
                    jLabelNCDCode.setText(jLabelNCDCode.getText()+"...");
                    ncd_code = ncd_code + ", " + ncd_data.ncd_number;
                }
            }
            jLabelNCDCode.setToolTipText("NCD: " + ncd_code);
        }
    }
    public String getServicePointName(Transfer tran)
    {
        ServicePoint sp = (ServicePoint)theHC.theLookupControl.readServicePointById(tran.service_point_id);   
        if(sp!=null){
            if(!ServicePoint.IPD.equals(sp.getObjectId()))
                return sp.name;
        }
        else{
            Ward ward = theHC.theLookupControl.readWardById(tran.ward_id);
            if(ward!=null)
                return ward.description;
        }
        return "ไม่มีข้อมูล";
    }
    private void setTransferV(Vector theTransfer)
    {
        theTransferV = theTransfer;
        jLabelLocationValue.setText("");
        jLabelLocationValue.setToolTipText(null);
        jLabelLastLocationValue.setText("");
        jLabelLastLocationValue.setToolTipText(null);
        setDoctor(theTransfer);
        if(theTransfer==null || theTransfer.isEmpty()) return ; 
        
        Transfer t = (Transfer)theTransferV.get(theTransferV.size()-1);
        String sp_name = this.getServicePointName(t);
        jLabelLocationValue.setText(sp_name); 
        jLabelLocationValue.setToolTipText(sp_name);
        
        if(theTransferV.size()<2) return;
        t = (Transfer)theTransferV.get(theTransferV.size()-2);
        sp_name = this.getServicePointName(t);
        jLabelLastLocationValue.setText(sp_name); 
        jLabelLastLocationValue.setToolTipText(sp_name);
        
    }    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        colorBackground1 = new com.hospital_os.gui.font.ColorBackground();
        defaultFont1 = new com.hospital_os.gui.font.DefaultFont();
        jPanelPatientInfo1 = new javax.swing.JPanel();
        jLabelHN = new javax.swing.JLabel();
        jTextFieldHN = new javax.swing.JTextField();
        jLabelVN = new javax.swing.JLabel();
        jLabelPatientNameValue = new javax.swing.JLabel();
        jTextFieldVN = new javax.swing.JTextField();
        jTextFieldIOpd = new javax.swing.JTextField();
        jLabelAN = new javax.swing.JLabel();
        jLabelAge = new javax.swing.JLabel();
        jLabelNCDCode = new javax.swing.JLabel();
        jTextFieldDBilling = new javax.swing.JLabel();
        jTextFieldDDoctor = new javax.swing.JLabel();
        jLabelVisitDate = new javax.swing.JLabel();
        jPanelPatientInfo2 = new javax.swing.JPanel();
        jLabelLocation = new javax.swing.JLabel();
        jLabelLocationValue = new javax.swing.JLabel();
        jLabelLastLocation = new javax.swing.JLabel();
        jLabelLastLocationValue = new javax.swing.JLabel();
        jLabelDxLbl = new javax.swing.JLabel();
        jLabelDx = new javax.swing.JLabel();
        jLabelPtAllergyValue = new javax.swing.JLabel();
        jLabelPlanLbl = new javax.swing.JLabel();
        jLabelPlan = new javax.swing.JLabel();
        jLabelReturnDrug = new javax.swing.JLabel();
        jLabelDischarge = new javax.swing.JLabel();
        jLabelDoctor = new javax.swing.JLabel();
        jLabelPersonalDisease = new javax.swing.JLabel();
        jPanelLine = new javax.swing.JPanel();
        jLabelSendToLocation = new javax.swing.JLabel();
        jLabelSendToDoctor = new javax.swing.JLabel();
        jComboBoxSendToDoctor = new javax.swing.JComboBox();
        jButtonSend = new javax.swing.JButton();
        jButtonUnlock = new javax.swing.JButton();
        jLabelLockUser = new javax.swing.JLabel();
        jLabelRemain = new javax.swing.JLabel();
        jComboBoxSendToLocation = new javax.swing.JComboBox();
        jLabelRefer = new javax.swing.JLabel();
        jButtonNext = new javax.swing.JButton();
        jButtonPrev = new javax.swing.JButton();
        jLabelAppointment = new javax.swing.JLabel();
        btnNote = new javax.swing.JButton();
        jLabelAttachNote = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(896, 95));
        setPreferredSize(new java.awt.Dimension(896, 95));
        setLayout(new java.awt.GridBagLayout());

        jPanelPatientInfo1.setMinimumSize(new java.awt.Dimension(358, 26));
        jPanelPatientInfo1.setPreferredSize(new java.awt.Dimension(358, 26));
        jPanelPatientInfo1.setLayout(new java.awt.GridBagLayout());

        jLabelHN.setFont(defaultFont1);
        jLabelHN.setText("HN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        jPanelPatientInfo1.add(jLabelHN, gridBagConstraints);

        jTextFieldHN.setFont(defaultFont1);
        jTextFieldHN.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldHN.setMinimumSize(new java.awt.Dimension(80, 20));
        jTextFieldHN.setPreferredSize(new java.awt.Dimension(80, 20));
        jTextFieldHN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHNActionPerformed(evt);
            }
        });
        jPanelPatientInfo1.add(jTextFieldHN, new java.awt.GridBagConstraints());

        jLabelVN.setFont(defaultFont1);
        jLabelVN.setText("VN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        jPanelPatientInfo1.add(jLabelVN, gridBagConstraints);

        jLabelPatientNameValue.setFont(defaultFont1);
        jLabelPatientNameValue.setText("     ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        jPanelPatientInfo1.add(jLabelPatientNameValue, gridBagConstraints);

        jTextFieldVN.setFont(defaultFont1);
        jTextFieldVN.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextFieldVN.setToolTipText("");
        jTextFieldVN.setMinimumSize(new java.awt.Dimension(80, 20));
        jTextFieldVN.setPreferredSize(new java.awt.Dimension(80, 20));
        jTextFieldVN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldVNActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        jPanelPatientInfo1.add(jTextFieldVN, gridBagConstraints);

        jTextFieldIOpd.setFont(defaultFont1);
        jTextFieldIOpd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldIOpd.setText("\n");
        jTextFieldIOpd.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelPatientInfo1.add(jTextFieldIOpd, gridBagConstraints);

        jLabelAN.setFont(defaultFont1);
        jLabelAN.setForeground(new java.awt.Color(255, 0, 0));
        jLabelAN.setText("AN");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        jPanelPatientInfo1.add(jLabelAN, gridBagConstraints);

        jLabelAge.setFont(defaultFont1);
        jLabelAge.setText("     ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        jPanelPatientInfo1.add(jLabelAge, gridBagConstraints);

        jLabelNCDCode.setFont(defaultFont1);
        jLabelNCDCode.setForeground(java.awt.Color.blue);
        jLabelNCDCode.setText("     ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelPatientInfo1.add(jLabelNCDCode, gridBagConstraints);

        jTextFieldDBilling.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTextFieldDBilling.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/mo_ney.gif"))); // NOI18N
        jTextFieldDBilling.setMaximumSize(new java.awt.Dimension(48, 24));
        jTextFieldDBilling.setMinimumSize(new java.awt.Dimension(48, 24));
        jTextFieldDBilling.setOpaque(true);
        jTextFieldDBilling.setPreferredSize(new java.awt.Dimension(48, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        jPanelPatientInfo1.add(jTextFieldDBilling, gridBagConstraints);

        jTextFieldDDoctor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jTextFieldDDoctor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/dd_wait.gif"))); // NOI18N
        jTextFieldDDoctor.setMaximumSize(new java.awt.Dimension(48, 24));
        jTextFieldDDoctor.setMinimumSize(new java.awt.Dimension(48, 24));
        jTextFieldDDoctor.setOpaque(true);
        jTextFieldDDoctor.setPreferredSize(new java.awt.Dimension(48, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        jPanelPatientInfo1.add(jTextFieldDDoctor, gridBagConstraints);

        jLabelVisitDate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelVisitDate.setForeground(java.awt.Color.blue);
        jLabelVisitDate.setText("วันที่รับบริการ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanelPatientInfo1.add(jLabelVisitDate, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(3, 2, 1, 2);
        add(jPanelPatientInfo1, gridBagConstraints);

        jPanelPatientInfo2.setLayout(new java.awt.GridBagLayout());

        jLabelLocation.setFont(defaultFont1);
        jLabelLocation.setForeground(java.awt.Color.red);
        jLabelLocation.setText("จุดปัจจุบัน");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 1, 1);
        jPanelPatientInfo2.add(jLabelLocation, gridBagConstraints);

        jLabelLocationValue.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanelPatientInfo2.add(jLabelLocationValue, gridBagConstraints);

        jLabelLastLocation.setFont(defaultFont1);
        jLabelLastLocation.setForeground(java.awt.Color.red);
        jLabelLastLocation.setText("จุดที่ผ่านมา");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 1);
        jPanelPatientInfo2.add(jLabelLastLocation, gridBagConstraints);

        jLabelLastLocationValue.setFont(defaultFont1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanelPatientInfo2.add(jLabelLastLocationValue, gridBagConstraints);

        jLabelDxLbl.setFont(defaultFont1);
        jLabelDxLbl.setForeground(java.awt.Color.red);
        jLabelDxLbl.setText("Dx: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 1);
        jPanelPatientInfo2.add(jLabelDxLbl, gridBagConstraints);

        jLabelDx.setFont(defaultFont1);
        jLabelDx.setText("....");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanelPatientInfo2.add(jLabelDx, gridBagConstraints);

        jLabelPtAllergyValue.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelPtAllergyValue.setForeground(java.awt.Color.red);
        jLabelPtAllergyValue.setText("แพ้ยา");
        jLabelPtAllergyValue.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabelPtAllergyValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelPtAllergyValueMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelPtAllergyValueMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanelPatientInfo2.add(jLabelPtAllergyValue, gridBagConstraints);

        jLabelPlanLbl.setFont(defaultFont1);
        jLabelPlanLbl.setForeground(new java.awt.Color(255, 0, 0));
        jLabelPlanLbl.setText("สิทธิ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 5, 1, 1);
        jPanelPatientInfo2.add(jLabelPlanLbl, gridBagConstraints);

        jLabelPlan.setFont(defaultFont1);
        jLabelPlan.setText("....");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanelPatientInfo2.add(jLabelPlan, gridBagConstraints);

        jLabelReturnDrug.setForeground(java.awt.Color.red);
        jLabelReturnDrug.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/return_drug.jpg"))); // NOI18N
        jLabelReturnDrug.setToolTipText("คืนยา");
        jLabelReturnDrug.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanelPatientInfo2.add(jLabelReturnDrug, gridBagConstraints);

        jLabelDischarge.setFont(defaultFont1);
        jLabelDischarge.setForeground(java.awt.Color.red);
        jLabelDischarge.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/Skull.gif"))); // NOI18N
        jLabelDischarge.setToolTipText("ผู้ป่วยเสียชีวิต");
        jLabelDischarge.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabelDischarge.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelDischargeMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanelPatientInfo2.add(jLabelDischarge, gridBagConstraints);

        jLabelDoctor.setFont(defaultFont1);
        jLabelDoctor.setForeground(new java.awt.Color(0, 0, 204));
        jLabelDoctor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/meet_doctor.jpg"))); // NOI18N
        jLabelDoctor.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabelDoctor.setAlignmentY(0.0F);
        jLabelDoctor.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        jPanelPatientInfo2.add(jLabelDoctor, gridBagConstraints);

        jLabelPersonalDisease.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPersonalDisease.setForeground(new java.awt.Color(255, 0, 0));
        jLabelPersonalDisease.setText("โรคประจำตัว");
        jLabelPersonalDisease.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelPersonalDiseaseMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        jPanelPatientInfo2.add(jLabelPersonalDisease, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 1, 2);
        add(jPanelPatientInfo2, gridBagConstraints);

        jPanelLine.setMinimumSize(new java.awt.Dimension(516, 30));
        jPanelLine.setPreferredSize(new java.awt.Dimension(556, 30));
        jPanelLine.setLayout(new java.awt.GridBagLayout());

        jLabelSendToLocation.setFont(defaultFont1);
        jLabelSendToLocation.setText("ส่งไปยัง");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelLine.add(jLabelSendToLocation, gridBagConstraints);

        jLabelSendToDoctor.setFont(defaultFont1);
        jLabelSendToDoctor.setText("แพทย์");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelLine.add(jLabelSendToDoctor, gridBagConstraints);

        jComboBoxSendToDoctor.setEditable(true);
        jComboBoxSendToDoctor.setFont(defaultFont1);
        jComboBoxSendToDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSendToDoctorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelLine.add(jComboBoxSendToDoctor, gridBagConstraints);

        jButtonSend.setFont(defaultFont1);
        jButtonSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/send.gif"))); // NOI18N
        jButtonSend.setToolTipText("ส่งผู้ป่วย");
        jButtonSend.setMaximumSize(new java.awt.Dimension(52, 28));
        jButtonSend.setMinimumSize(new java.awt.Dimension(52, 28));
        jButtonSend.setPreferredSize(new java.awt.Dimension(52, 28));
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        jPanelLine.add(jButtonSend, gridBagConstraints);

        jButtonUnlock.setFont(defaultFont1);
        jButtonUnlock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hosv3/gui/images/Unlock.png"))); // NOI18N
        jButtonUnlock.setToolTipText("ปลดล็อก");
        jButtonUnlock.setMaximumSize(new java.awt.Dimension(52, 28));
        jButtonUnlock.setMinimumSize(new java.awt.Dimension(52, 28));
        jButtonUnlock.setPreferredSize(new java.awt.Dimension(52, 28));
        jButtonUnlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUnlockActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelLine.add(jButtonUnlock, gridBagConstraints);

        jLabelLockUser.setBackground(java.awt.Color.white);
        jLabelLockUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelLockUser.setForeground(java.awt.Color.red);
        jLabelLockUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLockUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/lock.gif"))); // NOI18N
        jLabelLockUser.setToolTipText("ผู้ป่วยถูกล้อก");
        jLabelLockUser.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelLine.add(jLabelLockUser, gridBagConstraints);

        jLabelRemain.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelRemain.setForeground(java.awt.Color.red);
        jLabelRemain.setText("ค้างชำระ");
        jLabelRemain.setToolTipText("ค้างชำระ");
        jLabelRemain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelRemainMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelLine.add(jLabelRemain, gridBagConstraints);

        jComboBoxSendToLocation.setFont(defaultFont1);
        jComboBoxSendToLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSendToLocationActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanelLine.add(jComboBoxSendToLocation, gridBagConstraints);

        jLabelRefer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelRefer.setForeground(new java.awt.Color(102, 102, 0));
        jLabelRefer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelRefer.setText("Refer");
        jLabelRefer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelReferMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 15, 0, 2);
        jPanelLine.add(jLabelRefer, gridBagConstraints);

        jButtonNext.setFont(defaultFont1);
        jButtonNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/forward.gif"))); // NOI18N
        jButtonNext.setToolTipText("ประวัติครั้งถัดไป");
        jButtonNext.setMaximumSize(new java.awt.Dimension(32, 32));
        jButtonNext.setMinimumSize(new java.awt.Dimension(32, 32));
        jButtonNext.setPreferredSize(new java.awt.Dimension(32, 32));
        jButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelLine.add(jButtonNext, gridBagConstraints);

        jButtonPrev.setFont(defaultFont1);
        jButtonPrev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hospital_os/images/back.gif"))); // NOI18N
        jButtonPrev.setToolTipText("ประวัติครั้งก่อนหน้า");
        jButtonPrev.setMaximumSize(new java.awt.Dimension(32, 32));
        jButtonPrev.setMinimumSize(new java.awt.Dimension(32, 32));
        jButtonPrev.setPreferredSize(new java.awt.Dimension(32, 32));
        jButtonPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrevActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelLine.add(jButtonPrev, gridBagConstraints);

        jLabelAppointment.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelAppointment.setForeground(new java.awt.Color(0, 51, 255));
        jLabelAppointment.setText("วัน ถึงวันที่นัด");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanelLine.add(jLabelAppointment, gridBagConstraints);

        btnNote.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnNote.setForeground(java.awt.Color.blue);
        btnNote.setText("บันทึกข้อความ");
        btnNote.setToolTipText("บันทึกข้อความ");
        btnNote.setMaximumSize(new java.awt.Dimension(120, 28));
        btnNote.setMinimumSize(new java.awt.Dimension(120, 28));
        btnNote.setPreferredSize(new java.awt.Dimension(120, 28));
        btnNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        jPanelLine.add(btnNote, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 1, 2);
        add(jPanelLine, gridBagConstraints);

        jLabelAttachNote.setText("jLabel1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        add(jLabelAttachNote, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelDischargeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDischargeMouseReleased
        theHD.showDialogDeath();
    }//GEN-LAST:event_jLabelDischargeMouseReleased

    private void jLabelReferMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelReferMouseReleased
        theHD.showDialogReferIn(theHO.theVisit);
    }//GEN-LAST:event_jLabelReferMouseReleased

    private void jLabelRemainMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelRemainMouseReleased
        if(theHO.thePatient == null){      
            theUS.setStatus("ยังไม่ได้เลือกผู้ป่วย",UpdateStatus.WARNING);
            return;
        }
        theHD.showDialogHistoryBilling(theHO.thePatient);  
    }//GEN-LAST:event_jLabelRemainMouseReleased

    private void jLabelPersonalDiseaseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPersonalDiseaseMouseReleased
        if(thePPH==null)
            thePPH = new PanelPatientHistory(theHC,theUS);
        thePPH.showDialog();
    }//GEN-LAST:event_jLabelPersonalDiseaseMouseReleased

    private void jLabelPtAllergyValueMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPtAllergyValueMouseReleased
        if(thePPH==null)
            thePPH = new PanelPatientHistory(theHC,theUS);
        thePPH.showDialog();
    }//GEN-LAST:event_jLabelPtAllergyValueMouseReleased

    private void jLabelPtAllergyValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPtAllergyValueMouseClicked
        
    }//GEN-LAST:event_jLabelPtAllergyValueMouseClicked

    private void jTextFieldHNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHNActionPerformed
        setCursor(Constant.CUR_WAIT);
        
        int i = theHC.thePatientControl.readPatientByHn(jTextFieldHN.getText());
        if(i>1){
            theHD.showDialogSearchPatient("","",jTextFieldHN.getText(),"");
        }
        else if(i==0){
            jTextFieldHN.setText("");
        }
        setCursor(Constant.CUR_DEFAULT);
    }//GEN-LAST:event_jTextFieldHNActionPerformed

    private void jButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextActionPerformed
        theHC.theVisitControl.readNextVisit(thePatient,theVisit);
    }//GEN-LAST:event_jButtonNextActionPerformed

    private void jButtonPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrevActionPerformed
        theHC.theVisitControl.readPreviousVisit(thePatient,theVisit);
    }//GEN-LAST:event_jButtonPrevActionPerformed

    private void jComboBoxSendToLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSendToLocationActionPerformed
        ServicePoint sp = (ServicePoint)jComboBoxSendToLocation.getSelectedItem();
        if(sp==null) return;
        
        if(sp.service_type_id.equals(ServiceType.DIAG))
        {   
            Vector v = theHC.theLookupControl.listDoctorSP(sp.getObjectId());
            if(v!=null && !v.isEmpty() && v.size()>1)
            {
                Employee undefine = new Employee();
                undefine.setObjectId("");
                undefine.fname = Constant.getTextBundle("ไม่ระบุ");
                v.add(0,undefine);
            }
            if(v==null){
                v = new Vector();
            }
            jComboBoxSendToDoctor.setEnabled(true);
            ComboboxModel.initComboBox(jComboBoxSendToDoctor,v);
        }
        else  jComboBoxSendToDoctor.setEnabled(false);
    }//GEN-LAST:event_jComboBoxSendToLocationActionPerformed

    private void jButtonUnlockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUnlockActionPerformed
        theHC.theVisitControl.unlockVisit();
           
    }//GEN-LAST:event_jButtonUnlockActionPerformed

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
        getTransfer(theTransfer);
        theHC.theVisitControl.sendVisit(theTransfer); 
    }//GEN-LAST:event_jButtonSendActionPerformed

    private void jTextFieldVNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldVNActionPerformed
        theHC.theVisitControl.readVisitPatientByVn(jTextFieldVN.getText());
    }//GEN-LAST:event_jTextFieldVNActionPerformed

    private void jComboBoxSendToDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSendToDoctorActionPerformed
        if(evt.getActionCommand().equals("comboBoxEdited"))
        {
            String keyword = String.valueOf(jComboBoxSendToDoctor.getSelectedItem());
            ServicePoint sp = (ServicePoint)jComboBoxSendToLocation.getSelectedItem();
            if(sp==null) return;
            Vector v = theHC.theLookupControl.listDoctorSPAndName(sp.getObjectId(),keyword);
            if(v!=null && !v.isEmpty() && v.size()>1)
            {
                Employee undefine = new Employee();
                undefine.setObjectId("");
                undefine.fname = Constant.getTextBundle("ไม่ระบุ");
                if(keyword.trim().equals(""))
                    v.add(0,undefine);
            }
            if(v==null){
                v = new Vector();
            }
            jComboBoxSendToDoctor.setEnabled(true);
            ComboboxModel.initComboBox(jComboBoxSendToDoctor,v);
        }
    }//GEN-LAST:event_jComboBoxSendToDoctorActionPerformed

    private void btnNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoteActionPerformed
        theHD.showDialogListNotifyNote(theHO.thePatient.hn);
    }//GEN-LAST:event_btnNoteActionPerformed
/*
 //เรียก function ค้นหาผู้ป่วยจากเลข HN จากนั้น notify ไปยัง panel ต่างๆ
 //กรณีที่พบคนไข้มากกว่า 1 คนโปรแกรมจะขึ้นหน้าจอค้นหาผู้ป่วยมาให้
*/
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNote;
    private com.hospital_os.gui.font.ColorBackground colorBackground1;
    private com.hospital_os.gui.font.DefaultFont defaultFont1;
    private javax.swing.JButton jButtonNext;
    private javax.swing.JButton jButtonPrev;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JButton jButtonUnlock;
    private javax.swing.JComboBox jComboBoxSendToDoctor;
    private javax.swing.JComboBox jComboBoxSendToLocation;
    private javax.swing.JLabel jLabelAN;
    private javax.swing.JLabel jLabelAge;
    private javax.swing.JLabel jLabelAppointment;
    private javax.swing.JLabel jLabelAttachNote;
    private javax.swing.JLabel jLabelDischarge;
    private javax.swing.JLabel jLabelDoctor;
    private javax.swing.JLabel jLabelDx;
    private javax.swing.JLabel jLabelDxLbl;
    private javax.swing.JLabel jLabelHN;
    private javax.swing.JLabel jLabelLastLocation;
    private javax.swing.JLabel jLabelLastLocationValue;
    private javax.swing.JLabel jLabelLocation;
    private javax.swing.JLabel jLabelLocationValue;
    private javax.swing.JLabel jLabelLockUser;
    private javax.swing.JLabel jLabelNCDCode;
    private javax.swing.JLabel jLabelPatientNameValue;
    private javax.swing.JLabel jLabelPersonalDisease;
    private javax.swing.JLabel jLabelPlan;
    private javax.swing.JLabel jLabelPlanLbl;
    private javax.swing.JLabel jLabelPtAllergyValue;
    private javax.swing.JLabel jLabelRefer;
    private javax.swing.JLabel jLabelRemain;
    private javax.swing.JLabel jLabelReturnDrug;
    private javax.swing.JLabel jLabelSendToDoctor;
    private javax.swing.JLabel jLabelSendToLocation;
    private javax.swing.JLabel jLabelVN;
    private javax.swing.JLabel jLabelVisitDate;
    private javax.swing.JPanel jPanelLine;
    private javax.swing.JPanel jPanelPatientInfo1;
    private javax.swing.JPanel jPanelPatientInfo2;
    private javax.swing.JLabel jTextFieldDBilling;
    private javax.swing.JLabel jTextFieldDDoctor;
    private javax.swing.JTextField jTextFieldHN;
    private javax.swing.JTextField jTextFieldIOpd;
    private javax.swing.JTextField jTextFieldVN;
    // End of variables declaration//GEN-END:variables
  
    private void setHosObject(HosObject ho)
    {   
        theHO = ho;
        setFamily(theHO.theFamily);
        setPatient(theHO.thePatient);
        setVisit(theHO.theVisit);
        boolean res = setPaymentV(theHO.vVisitPayment);
        if(!res)
            setPatientPaymentV(theHO.vPatientPayment);
        setTransferV(theHO.vTransfer);
        setAllergyV(theHO.vDrugAllergy);
        setPersonalDiseaseV(theHO.vPersonalDisease);
        //เพื่อซ่อนชื่อผู้ป่วยในกรณีมีแลปปกปิด
        if(theHO.theVisit!=null 
                && theHO.orderSecret != null
                && !"".equals(theHO.orderSecret))
        {
            jTextFieldIOpd.setText(" *** ");
            jLabelDx.setText("*********");
            jTextFieldHN.setText("*********");//มันจะแสดงเป็นช่องว่างแทน เพราะมันจะรับเฉพาะตัวเลขเท่านั้น
            jTextFieldVN.setText("*********");
            jLabelPatientNameValue.setText(" *** " + theHO.specimenCode);
            jLabelAge.setText(Constant.getTextBundle("อายุ *** ปี "));
            jLabelPlan.setText("*********");
            jLabelDischarge.setVisible(false);
            jLabelRemain.setVisible(false);
            jLabelDoctor.setVisible(false);
            this.jLabelNCDCode.setText("");
            this.jLabelPlan.setText("");
        }
    }
    public void notifyManageDrugAllergy(String str, int status) {
        setAllergyV(theHO.vDrugAllergy);
        setPersonalDiseaseV(theHO.vPersonalDisease);
    }

    public void notifyReadVisit(String str, int status) 
    {
        setHosObject(theHO);
        btnNote.setEnabled(true);
    }
    
    public void notifyUnlockVisit(String str, int status) 
    {
        setHosObject(theHO);
        this.jTextFieldHN.requestFocus();
        btnNote.setEnabled(false);
    }

    public void notifyVisitPatient(String str, int status) 
    {
        setHosObject(theHO);
        btnNote.setEnabled(true);
        if(theHO.theVisit != null)
            theHD.showDialogListNotifyNote(theHO.thePatient.hn, theHO.theVisit.getObjectId());
    }

    public void notifyAdmitVisit(String str, int status) 
    {
        setHosObject(theHO);
    }
    
    public void notifyDischargeDoctor(String str, int status) 
    {
        setHosObject(theHO);
    }
    
    public void notifyAddItemDrugAllergy(String str, int status) {
    }
    
    public void notifyDropVisit(String str, int status) 
    {
        setHosObject(theHO);
        btnNote.setEnabled(false);
    }
    
    public void notifySendVisit(String str, int status) 
    {
        setHosObject(theHO);
        btnNote.setEnabled(false);
    }
    
    public void notifyDischargeFinancial(String str, int status)
    {
        setHosObject(theHO);
        btnNote.setEnabled(false);
    }
        
    public void notifyReverseFinancial(String str, int status) {
        setVisit(theHO.theVisit);
    }
    
    public void notifyReverseDoctor(String str, int status) {
        setVisit(theHO.theVisit);
    }
    
    public void notifyObservVisit(String str, int status) {
        setVisit(theHO.theVisit);
    }
    
    public void notifyCheckDoctorTreament(String str, int status) {
    }    
    
    public void notifyManageAppointment(String str, int status) {
    }    
    
    public void notifySavePatientPayment(String str, int status) {
    }
    
    public void notifyDeletePatientPayment(String str, int status) {
    }
    
    public void notifyDeleteVisitPayment(String str, int status) {
    }

    public void notifyAddDxTemplate(String str, int status) {
    }
    
    public void notifyReadPatient(String str, int status) {
        setHosObject(theHO);
    }
    
    public void notifyReadFamily(String str, int status) 
    {  
        setHosObject(theHO);
    }
    
    public void notifySavePatient(String str, int status) 
    {
        setHosObject(theHO);
    }
    
    public void notifyDeletePatient(String str, int status) 
    {
        setFamily(null);
        setPatient(null);
        setAllergyV(null);
        setPersonalDiseaseV(null);
    }
    
    public void notifyAddPrimarySymptom(String str, int status) {
    }
    
    public void notifyDeleteMapVisitDxByVisitId(String str, int status) {
    }
    
    public void notifyManagePhysicalExam(String str, int status) {
    }
    
    public void notifyManagePrimarySymptom(String str, int status) {
    }
    
    public void notifyManageVitalSign(String str, int status) {
    }
    
    public void notifySaveDiagDoctor(String str, int status) {
        setVisit(theHO.theVisit);
    }
    
    public void notifySendVisitBackWard(String str, int status) {
        setHosObject(theHO);        
    }
    
    public void notifyRemainDoctorDischarge(String str, int status) {
        setHosObject(theHO);
    }
    
    public void notifyCancelOrderItem(String str, int status) {
    }
    
    public void notifyCheckAutoOrder(String str, int status) {
    }
    
    public void notifyContinueOrderItem(String str, int status) {
    }
    
    public void notifyDispenseOrderItem(String str, int status) {
        setHosObject(theHO);
    }
    
    public void notifyDoctorOffDrug(String DoctorId, int status) {
    }
    
    public void notifyExecuteOrderItem(String str, int status) {
    }
    
    public void notifyReceiveReturnDrug(String str, int status) {
        setVisit(theHO.theVisit);
    }
    
    public void notifyReferOutLab(String msg, int status) {
    }
    
    public void notifySaveOrderItem(String str, int status) {
    }
    
    public void notifySaveOrderItemInLab(String str, int status) {
    }
    
    public void notifySaveReturnDrug(String str, int status) {
        setVisit(theHO.theVisit);
    }
    
    public void notifyVerifyOrderItem(String str, int status) {
    }
    
    public void notifyBillingInvoice(String str, int status) {
    }
    
    public void notifyCalculateAllBillingInvoice(String str, int status) {
    }
    
    public void notifyCancelBill(String str, int status) {
        setHosObject(theHO);        
    }
    
    public void notifyCancelBillingInvoice(String str, int status) {
    }
    
    public void notifyPatientPaidMoney(String str, int status) {
        setHosObject(theHO);        
    }
    
    public void notifyDeleteFilmXray(String str, int status) {
    }
    
    public void notifyDeleteLabOrder(String str, int status) {
    }
    
    public void notifyDeleteLabResult(String str, int status) {
    }
    
    public void notifyDeleteXrayPosition(String str, int status) {
    }
    
    public void notifyManagePatientLabReferIn(String str, int status) {
    }
    
    public void notifyReportResultLab(String str, int status) {
        setHosObject(theHO);        
    }
    
    public void notifySaveFilmXray(String str, int status) {
    }
    
    public void notifySaveLabResult(String str, int status) {
    }
    
    public void notifySaveResultXray(String str, int status) {
    }
    
    public void notifySaveXrayPosition(String str, int status) {
    }
    
    public void notifyXrayReportComplete(String str, int status) {
        setHosObject(theHO);        
    }
    
    public void notifyDeleteResultXray(String str, int status) {
    }
    
    public void notifySaveOrderRequest(String str, int status) {
    }
    
    public void notifySaveAppointment(String str, int status) {
    }
    
    public void notifyAddLabReferOut(String str, int status) {
    }
    
    public void notifyAddLabReferIn(String str, int status) {
    }
    
    public void notifyReverseAdmit(String str, int status) {
        setVisit(theHO.theVisit);
    }
    
    public void notifyResetPatient(String str, int status) {
        setHosObject(theHO);
    }
    
    public void notifySaveRemainLabResult(String str, int status) {
        setHosObject(theHO);
    }
    
    public void notifySendResultLab(String str, int status) {
        setHosObject(theHO);
    }
    
    public void notifyDeleteVPayment(String str, int status) {
        setPaymentV(theHO.vVisitPayment);        
    }
    
    public void notifyDownVPaymentPriority(String str, int status) {
        setPaymentV(theHO.vVisitPayment);        
    }
    
    public void notifySaveVPayment(String str, int status) {
        setPaymentV(theHO.vVisitPayment);        
    }
    
    public void notifyUpVPaymentPriority(String str, int status) {
        setPaymentV(theHO.vVisitPayment);
    }
    
    public void notifyDeleteQueueLab(String str, int status) {
        setHosObject(theHO);
    }
    public void setLanguage(String str)
    {
	GuiLang.setLanguage(jButtonSend);
	GuiLang.setLanguage(jButtonUnlock);
	GuiLang.setLanguage(jLabelAge);
	GuiLang.setLanguage(jLabelAN);
	GuiLang.setLanguage(jLabelDischarge);
	GuiLang.setLanguage(jLabelDx);
	GuiLang.setLanguage(jLabelDxLbl);
	GuiLang.setLanguage(jLabelHN);
	GuiLang.setLanguage(jLabelLastLocation);
	GuiLang.setLanguage(jLabelLastLocationValue);
	GuiLang.setLanguage(jLabelLocation);
	GuiLang.setLanguage(jLabelLocationValue);
	GuiLang.setLanguage(jLabelLockUser);
	GuiLang.setLanguage(jLabelPatientNameValue);
        GuiLang.setLanguage(jLabelPlan);
	GuiLang.setLanguage(jLabelPlanLbl);
	GuiLang.setLanguage(jLabelPtAllergyValue);
	GuiLang.setLanguage(jLabelRemain);
	GuiLang.setLanguage(jLabelReturnDrug);
	GuiLang.setLanguage(jLabelSendToDoctor);
	GuiLang.setLanguage(jLabelSendToLocation);
	GuiLang.setLanguage(jLabelVN);
        GuiLang.setLanguage(jLabelRemain);
        GuiLang.setLanguage(jButtonNext);
        GuiLang.setLanguage(jButtonPrev);
        GuiLang.setLanguage(jLabelAttachNote);
        Font font = jLabelPatientNameValue.getFont();
        jLabelPatientNameValue.setFont(new Font(font.getFontName(),Font.BOLD,font.getSize()));
    }

    public void notifySaveBorrowFilmXray(String str, int status) {
    }

}
