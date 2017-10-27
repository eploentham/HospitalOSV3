/*
 * HosDialog.java
 *
 * Created on 18 พฤษภาคม 2548, 10:32 น.
 *
 */ 

package com.hosv3.gui.dialog;

import com.hosv3.utility.Constant;
import javax.swing.*;
import javax.swing.text.*;
import java.util.*;
import com.hosv3.control.*;
import com.hosv3.object.*;
import com.hosv3.utility.connection.*;

import com.hospital_os.object.*; 
import com.hosv3.gui.panel.setup.PanelSetupMapNhsoPlan;
import com.hosv3.gui.panel.transaction.PanelOrder;
import th.go.nhso.rightsearch.RightData;

//import com.hosv3.control.DialogCause;
/**
 *
 * @author  administrator
 * 
 */
public class HosDialog {
    
    HosControl theHC;
    UpdateStatus theUS;    
    
     DialogSearchPatient theDialogSearchPatient; 
     DialogOffDrug theDialogOffDrug,theDialogContinueDrug;
        DialogDoctorClinic theDialogUseDoctor;
     
     DialogShowLabResult theDialogShowLabResult;    
     DialogOffice theDialogOffice;
     DialogHistoryBilling theDialogHistoryBilling;    
     DialogOrderHistory theDialogOrderHistory;
     DialogAccident theDialogAccident;  
     DialogAdmit theDialogAdmit; 
     DialogSurveil theDialogSurveil; 
     DialogAppointment theDialogAppointment;
     DialogAppointment theDialogAppointmentlist;     
//     DialogCause theDialogCause; 
     DialogChronic theDialogChronic;
     DialogReferIn theDialogReferIn;
     DialogViewSequence theDialogViewSequence;
     DialogGuideAfterDx dialogGuideAfterDx;
    /** add by sumo */
     DialogDischarge theDialogDischarge;
     DialogSeeIcd10 theDialogSeeIcd10;
     DialogDeath theDialogDeath;
     DialogBorrowFilmXray theDialogBorrowFilmXray;
     DialogBorrowOpdCard theDialogBorrowOpdCard;
     
    /** add by jao */
     DialogOrderItemLabByLab theDialogOrderItemLabByLab;
     //tong
     DialogOrderItemXRayByXRay theDialogOrderItemXRayByXRay;
     DialogSelectLabPrint theDialogSelectLabPrint;
     //tong 18/04/2549
     DialogShowHistoryXN theDialogShowHistoryXN;
     //DialogChooseICD10FromTemplate theDialogChooseICD10FromTemplate;
    /**@author tong
     * @
     */
     DialogDetailHospitalOS theDialogDetailHospitalOS;
     DialogSetPrefix theDialogSetPrefix;
     DialogHistoryPatient theDialogHistoryPatient;
     DialogOrderSet theDialogOrderSet;
     DialogOrderOldVisit theDialogOrderOldVisit;
     DialogReceiveDrug theDialogReceiveDrug;
     DialogHistoryOrderContinue theDialogHistoryOrderContinue;     
     PanelResultXray thePanelResultXray;
     DialogCalBilling2 theDialogCalBilling;
    
    //add code by noom
     DialogPatientPaid theDialogPatientPaid;
     DialogPasswdForCancelReceipt theDialogPasswdForCancelReceipt;
    
    //Add by Neung
     DialogQueueVisit theDialogQueueVisit;
     PanelSetupSearchSub thePanelSetupSearchSub3;
     PanelSetupSearchSub thePanelSetupSearchSub4;
     PanelSetupSearchSub thePanelSetupSearchSub5;
     PanelSetupSearchSub thePanelSetupSearchSub7;
     PanelSetupSearchSub thePanelSetupSearchSub8;
    //neung Print Dialog
     DialogChooseOrderItemPrint theDialogChooseOrderItemPrint;
    //-neung Dialog Labrefer   
     DialogLabReferIn theDialogLabReferIn;
    //neung Dialog ReverseAdmit
     DialogReverseAdmit theDialogReverseAdmit;
    //neung Dialog SelectPrintIpdNameCard
     DialogSelectPatientPrintNameCard theDialogSelectPatientPrintNameCard;
     // Somprasong add 20101007
     DialogNewNotifyNote theDialogNewNotifyNote;
     DialogListNotifyNote theDialogListNotifyNote;
     PanelNCD thePanelNCD;//amp:15/06/2549
     PanelLabList thePanelLabList;
     PanelServiceLocation thePanelServiceLocation;
     PanelMapPlan thePanelMapPlan;
     PanelSetupMapNhsoPlan thePanelSetupMapNhsoPlan;
    PanelScanOPDRecord thePanelScanOPDRecord;   //+1
     
     private PanelAllRoom thePanelAllRoom;
    private PanelAllRoom2 thePanelAllRoom2;
    public HosDialog(HosControl hc,UpdateStatus us) {
        setControl(hc,us);
    }
    
    public void setControl(HosControl hc,UpdateStatus us)
    {
        theHC = hc;
        theUS = us;   
    }
    
    /**
     * @Author amp
     * @date 21/02/49
     */
//    public boolean showDialogWarningDoctorDx()
//    {
//        theDialogWarningDoctorDx = new DialogWarningDoctorDx(theHC,theUS);
//        return theDialogWarningDoctorDx.showDialog();
//    } 
    
    public void showDialogParticipateOr(JTable jt,Vector parOrV){
        if(thePanelSetupSearchSub5==null) {
            thePanelSetupSearchSub5 = new PanelSetupSearchSub(theHC,theUS,5);
            thePanelSetupSearchSub5.setTitle("ค้นหาชื่อผู้เข้าร่วมผ่าตัด");            
        }
        thePanelSetupSearchSub5.showSearch(jt,parOrV);
    }
    
    public void showDialogSearchPatient(String fname,String lname,String hn,String pid){
        if(theDialogSearchPatient==null)
            theDialogSearchPatient = new DialogSearchPatient(theHC,theUS,1);
        
        if(!fname.equals("")||!lname.equals("")){
            theDialogSearchPatient.setFnameLname(fname,lname);
        }
        else if(!hn.equals("")|| !pid.equals("")){
            theDialogSearchPatient.setHNPID(hn,pid);
        }
        Constant.println(fname + ": " + lname);
        theDialogSearchPatient.showDialog(); 
        
    }
    
    
    public void showDialogShowLabResult(OrderItem oi)
    {
        Constant.println("public void showDialogShowLabResult()");
        if(theDialogShowLabResult==null)
            theDialogShowLabResult = new DialogShowLabResult(theUS.getJFrame(),theHC);        
        theDialogShowLabResult.showDialog(oi);        
    }
    
    public void showDialogViewSequence() {
        /** tong comment becouse can not run 
         *  date 24/05/48
         */
        if(theDialogViewSequence == null)
            theDialogViewSequence = new DialogViewSequence(theHC,theUS);
        theDialogViewSequence.showDialog();
        
    }
    /**@author tong
     * @date 24/05/48
     * @param Object Visit in transaction
     * @return void
     * ใช้แสดง Dialog referIn เมื่อต้องการจะกำหนดค่าข้อมูลการ refer In/Out ของผู้ป่วย
     */
    public boolean showDialogReferIn(Visit v) {
       
        if(theDialogReferIn == null)
           theDialogReferIn = new DialogReferIn(theHC,theUS);
        return theDialogReferIn.showDialogReferIn(v); 
    }  
 
    /**@author tong
     * @date 24/05/48
     * @param void
     * @return void
     * ใช้แสดง รายงานที่ผู้ใช้ต้องการจะทำรายงาน
     */
//    public void showDialogShowReport()
//    {
//        if(theDialogShowReport == null)
//            theDialogShowReport = new DialogShowReport(theHC,theUS);
//        theDialogShowReport.showDialog();
//            
//    }
    /**@author tong
     * @date 24/05/48
     * @param void
     * @return void
     * ใช้แสดง อุบัติเหตุหมู่
    public void showDialogShowAccidentGroup()
    {
        if(theDialogAccidentGroup == null)
            theDialogAccidentGroup = new DialogAccidentGroup(theHC,theUS);
        theDialogAccidentGroup.showDialog();
            
    }
     */
    /**@author tong
     * @date 24/05/48
     * @param void
     * @return void
     * ใช้แสดง Dialog ของการกำหนดคำนำหน้า
     */
    public void showDialogSetPrefix(Vector vprefix,String pf) {
        
       if(theDialogSetPrefix == null)
           theDialogSetPrefix = new DialogSetPrefix(theHC,theUS);
       theDialogSetPrefix.showDialog(vprefix,pf);
         
    }  
    /**@author tong
     * @date 24/05/48
     * @param Object Patient, Object Visit
     * @return void
     * ใช้แสดง Dialog การนัดของผู้ป่วย
     */
    public void showDialogAppointment(Patient thePatient,Visit theVisit)
    {     
        if(theDialogAppointment == null)
            theDialogAppointment =new DialogAppointment(theHC,theUS,false);
        theDialogAppointment.showDialog(thePatient,theVisit,false); 
    }
    
    /**@author tong
     * @date 24/05/48
     * @param Object Patient
     * @return void
     * ใช้แสดง Dialog ประวัติการรับบริการของผู้ป่วย
     */
    public void showDialogHistoryPatient(Patient thePatient){     
        if(theDialogHistoryPatient == null)
            theDialogHistoryPatient =new DialogHistoryPatient(theHC,theUS);
        theDialogHistoryPatient.showDialog(thePatient); 
        
    }
    /**@author LionHeart
     * @date 27/10/53
     * @param Object Patient
     * @return void
     * ใช้แสดง Dialog ระบุประเภท Visit
     */
    public void showPanelServiceLocation(Visit visit){

        if(thePanelServiceLocation == null)
            thePanelServiceLocation =new PanelServiceLocation();
        thePanelServiceLocation.showDialog(theHC.theUS.getJFrame(),visit);

    }
    /**@author LionHeart
     * @date 04/10/53
     * @param Object Patient
     * @return void
     * ใช้แสดง Dialog พิมพ์ Sticker ติด tube
     */
    public void showPanelLabList(HosControl hc,PanelOrder panelOrder)
    {
        if(thePanelLabList == null)
        {
            thePanelLabList = new PanelLabList();
            thePanelLabList.setControl( hc, hc.theLabControl);
            thePanelLabList.setCheck(false);
        }
        hc.theVisitControl.readVisitPatientByVn(hc.theHO.theVisit.vn);
        hc.theHP.theJTabbedPane.setSelectedComponent(panelOrder);
        Vector v = hc.theLabControl.listLabOrderByVn(hc.theHO.theVisit.getObjectId());
        if(v == null )
        {
            theHC.theUS.setStatus("ไม่พบข้อมูล", UpdateStatus.WARNING);
            return;
        }
        if(v.size() == 0)
        {
            theHC.theUS.setStatus("ไม่พบข้อมูล", UpdateStatus.WARNING);
            return;
        }
        if(v==null)
        {
            this.theUS.setStatus("ไม่พบข้อมูล", UpdateStatus.WARNING);
            return;
        }
        thePanelLabList.setCheck(false);
        thePanelLabList.showDialog(v,LabList.TYPE_NEW);
    }
    /**@author tong
     * @date 24/05/48
     * @param Object Patient
     * @return void
     * ใช้แสดง Dialog รายการยาชุด
     */
    public void showDialogOrderSet(Visit theVisit){     
        if(theDialogOrderSet == null)
            theDialogOrderSet =new DialogOrderSet(theHC,theUS);
        theDialogOrderSet.showDialog(theVisit); 
        
    }
    /**@author tong
     * @date 24/05/48
     * @param Object Patient
     * @return void
     * ใช้แสดง Dialog รายการยาเหมือนครั้งที่แล้ว ตาม vn
     */
    public void showDialogOrderOldVisitByVn(Patient thePatient,Visit theVisit){     
        if(theDialogOrderOldVisit == null)
            theDialogOrderOldVisit =new DialogOrderOldVisit(theHC,theUS);
        theDialogOrderOldVisit.showDialog(thePatient,theVisit,true); 
        
    }
    /**@author tong
     * @date 24/05/48
     * @param Object Patient
     * @return void
     * ใช้แสดง Dialog รายการยาเหมือนครั้งที่แล้ว ตาม วันที่
     */
    public void showDialogOrderOldVisitByDate(Patient thePatient,Visit theVisit){     
        if(theDialogOrderOldVisit == null)
            theDialogOrderOldVisit =new DialogOrderOldVisit(theHC,theUS);
        theDialogOrderOldVisit.showDialog(thePatient,theVisit,false); 
        
    }
    /**@author tong
     * @date 24/05/48
     * @param void
     * @return void
     * ใช้แสดง รายงานที่ผู้ใช้ต้องการจะทำรายงาน
     */
    public void showDialogReceiveDrug(Visit theVisit)
    {
        if(theDialogReceiveDrug == null)
            theDialogReceiveDrug = new DialogReceiveDrug(theHC,theUS);
        theDialogReceiveDrug.showDialog(theVisit);           

    }
    
    /**@author tong
     * @date 27/05/48
     * @param void
     * @return void
     * ใช้แสดง รายงานที่ผู้ใช้ต้องการจะทำรายงาน
     */
    public void showDialogHistoryOrderContinue(Visit theVisit)
    {
        if(theDialogHistoryOrderContinue == null)
            theDialogHistoryOrderContinue = new DialogHistoryOrderContinue(theHC,theUS);
        theDialogHistoryOrderContinue.showDialog(theVisit);
            
    }

    public void showDialogChronic(Visit theVisit,Chronic theChronic){
        if(theDialogChronic == null)
            theDialogChronic = new DialogChronic(theHC,theUS);
        theDialogChronic.showDialog( theVisit, theChronic); 
    }
    public void showDialogOrderHistory(Patient thePatient){    
        if(theDialogOrderHistory == null)
            theDialogOrderHistory = new DialogOrderHistory(theHC,theUS);
        theDialogOrderHistory.showDialog(thePatient);
        
    }
    public void showDialogOrderHistory(Patient thePatient,String datefrom,String dateto,String categoryGroupID){    
//        if(theDialogOrderHistory == null)
//            theDialogOrderHistory = new DialogOrderHistory(theHC,theUS);
//        theDialogOrderHistory.showDialog(thePatient,datefrom,dateto,categoryGroupID);
        DialogHistoryOrder d = new DialogHistoryOrder(theHC,theUS);
        d.showDialog(thePatient);
        
    }
    public void showDialogAccident(){    
        if(theDialogAccident == null)
            theDialogAccident = new DialogAccident(theHC,theUS);
        theDialogAccident.showDialog(theHC.theHO);
    }
    public void showDialogAppointmentlist(Patient thePatient,Visit theVisit){     
        if(theDialogAppointmentlist == null)
            theDialogAppointmentlist =new DialogAppointment(theHC,theUS,true);
        theDialogAppointmentlist.showDialog(thePatient,theVisit,true); 
    }
    
    public void showDialogAdmit(Visit theVisit)
    {
        if(theDialogAdmit == null) 
            theDialogAdmit = new DialogAdmit(theHC,theUS);
        theDialogAdmit.showDialog(theVisit);  
        theDialogAdmit.setDialog(this);     //+1
    }
    public void showDialogAdmit(Visit theVisit, HosDialog hd)
    {
        if(theDialogAdmit == null) 
            theDialogAdmit = new DialogAdmit(theHC,theUS);
        theDialogAdmit.showDialog(theVisit, hd);  
        theDialogAdmit.setDialog(hd);     //+1
    }
    
    public void showDialogHistoryBilling(Patient thePatient){
        if(theDialogHistoryBilling == null)
            theDialogHistoryBilling= new DialogHistoryBilling(theHC,theUS);
        theDialogHistoryBilling.showDialog(thePatient);
    }
    public void showDialogHistoryBilling(Patient thePatient,String billing_id){
        if(theDialogHistoryBilling == null)
            theDialogHistoryBilling= new DialogHistoryBilling(theHC,theUS);
        theDialogHistoryBilling.showDialog(thePatient, billing_id);
    }
    public void showDialogOrderItemLabByLab(Vector orderlab,Visit theVisit){  
       if(theDialogOrderItemLabByLab == null)
            theDialogOrderItemLabByLab= new DialogOrderItemLabByLab(theHC,theUS);       
       theDialogOrderItemLabByLab.showDialog(orderlab,theVisit); 
    }
    /**แสดง Dialog การเพิ่ม Item Xray
     *@param Orderxray เป็น vector ของ Object Order
     *@param theVisit เป็น Object ของ Visit
     *@author padungrat(tong)
     */
    public void showDialogOrderItemXrayByXray(Vector orderxray,Visit theVisit){  
       if(theDialogOrderItemXRayByXRay == null)
            theDialogOrderItemXRayByXRay= new DialogOrderItemXRayByXRay(theHC,theUS);       
       theDialogOrderItemXRayByXRay.showDialog(orderxray,theVisit); 
    }
    public void showDialogSelectLabPrint(Vector orderlab,Visit theVisit,Patient thePatient,Vector vResultLab){    
        if(theDialogSelectLabPrint == null)
            theDialogSelectLabPrint= new DialogSelectLabPrint(theUS.getJFrame(),theHC,theUS); 
        theDialogSelectLabPrint.showDialog(orderlab,theVisit,thePatient,orderlab,vResultLab);  
    }
         
    private void closeDialog()
    {
        if(theDialogHistoryBilling!=null)
        {
            theDialogHistoryBilling.setVisible(false);
            theDialogHistoryBilling = null;
        }
        if(theDialogOrderHistory!=null)
        {
            theDialogOrderHistory.setVisible(false);
            theDialogOrderHistory = null;
        }
        if(theDialogAccident!=null)//DialogAccident
        {
            theDialogAccident.setVisible(false);
            theDialogAccident = null;
        }        
        if(theDialogAdmit!=null)//DialogAdmit
        {
            theDialogAdmit.setVisible(false);
            theDialogAdmit = null;
        }
        if(theDialogAppointment!=null)//DialogAppointment
        {
            theDialogAppointment.setVisible(false);
            theDialogAppointment = null; 
        } 
//        if(theDialogCause!=null)//DialogCause
//        {
//            theDialogCause.setVisible(false);
//            theDialogCause = null; 
//        }
        if(theDialogChronic!=null)
        {
            theDialogChronic.setVisible(false);
            theDialogChronic = null; 
        }
        if(theDialogOrderItemLabByLab!=null)
        {
            theDialogOrderItemLabByLab.setVisible(false);
            theDialogOrderItemLabByLab = null; 
        }
        if(theDialogOrderItemXRayByXRay != null)
        {
            theDialogOrderItemXRayByXRay.setVisible(false);
            theDialogOrderItemXRayByXRay = null;
        }
        
        if(theDialogSelectLabPrint!=null)
        {
            theDialogSelectLabPrint.setVisible(false);
            theDialogSelectLabPrint = null; 
        }
    }    
     /**add code by noom
	*/
    public void showDialogPatientPaid(Billing billing,Visit visit){
        DialogPatientPaid.showDialog(theHC,theUS,billing,visit);
    }

      //add code by noom
    public boolean showDialogPasswdForCancelReceipt(){
        if(theDialogPasswdForCancelReceipt==null){
           theDialogPasswdForCancelReceipt = new DialogPasswdForCancelReceipt(theHC,theUS);
        }
       return theDialogPasswdForCancelReceipt.showDialog();
    } 
    
   /* //add code by noom
       public boolean showDialogCommonInf(Vector ciV,Plan ci){
        if(theDialogCommonInf==null){
           theDialogCommonInf = new DialogCommonInf(theHC,theUS);
        }
       return theDialogCommonInf.showDialog(ciV,ci);  
    }*/
     public boolean showDialogOffice(Office office){
        Constant.println("DialogOffice");
        if(theDialogOffice == null)
            theDialogOffice = new DialogOffice(theHC,theUS,office);
        return theDialogOffice.showDialog(office);
    }
    public void showPanelSetupMapNhsoPlan()
    {
        if(thePanelSetupMapNhsoPlan == null)
            thePanelSetupMapNhsoPlan= new PanelSetupMapNhsoPlan();
        thePanelSetupMapNhsoPlan.setControl(theHC, theUS);
        thePanelSetupMapNhsoPlan.showDialog();
    }
    public void showPanelScanOPDRecord() //+1
    {
        if(thePanelScanOPDRecord == null)
            thePanelScanOPDRecord= new PanelScanOPDRecord();
        thePanelScanOPDRecord.setControl(theHC, theUS);
        thePanelScanOPDRecord.showDialog();
    }
    public int showPanelMapPlan(RightData rightData,Payment paymentNow)
    {
        if(thePanelMapPlan == null)
            thePanelMapPlan = new PanelMapPlan();
        thePanelMapPlan.setControl(theHC, theUS);
        thePanelMapPlan.setNhsoPlan(rightData);
        thePanelMapPlan.setPaymentNow(paymentNow);
        thePanelMapPlan.showDialog();
        return thePanelMapPlan.res;
    }

     public void showDialogSurveil(Surveil s){
        if(theDialogSurveil == null)
            theDialogSurveil = new DialogSurveil(theHC,theUS,s);
        theDialogSurveil.showDialog(s);
     }
     
     public void showDialogDetailHospitalOS(Vector v){
        if(theDialogDetailHospitalOS == null)
            theDialogDetailHospitalOS = new DialogDetailHospitalOS(theHC,theUS);
        theDialogDetailHospitalOS.showDialog(v);
    }

     
     private void showPanelSetupSearchSub(int mode)
     {         
         
         if(mode == 7)
         {
            if(thePanelSetupSearchSub7==null)
            {
                thePanelSetupSearchSub7 = new PanelSetupSearchSub(theHC,theUS,mode);
            }
             thePanelSetupSearchSub7.setTitle(Constant.getTextBundle("ตัวช่วยอาการเบื้องต้น"));
             thePanelSetupSearchSub7.showSearch(); 
         }
         else if(mode == 8)
         {
             if(thePanelSetupSearchSub8==null)
            {
                thePanelSetupSearchSub8 = new PanelSetupSearchSub(theHC,theUS,mode);
            }
             thePanelSetupSearchSub8.setTitle(Constant.getTextBundle("ตัวช่วยอาการเบื้องต้น"));  
             thePanelSetupSearchSub8.showSearch();           
         }
        
     }
     
     public void showDialogDrugAllergyTemplate(JTable jt,Vector v)
     {
        if(thePanelSetupSearchSub3==null)
            thePanelSetupSearchSub3 = new PanelSetupSearchSub(theHC,theUS,3);
        thePanelSetupSearchSub3.setTitle(Constant.getTextBundle("ตัวช่วยเพิ่มรายการยาที่แพ้")); 
        thePanelSetupSearchSub3.showSearchAllergy(jt,v); 
     }
    public void showDialogDxTemplate(JTextComponent jf,Vector v)
    {
        thePanelSetupSearchSub4.showDialogDx(theHC,theUS,4,Constant.getTextBundle("ตัวช่วยการวินิจฉัย"),jf,v);
    }
//     public void showDialogPrimarySymptomTemplate()
//     {
//        showPanelSetupSearchSub(7);
//     }
     
     public void showDialogPrimarySymptomTemplate(JTextComponent component)
     {
        if(thePanelSetupSearchSub8==null)
        {
            thePanelSetupSearchSub8 = new PanelSetupSearchSub(theHC,theUS,7);
        }
        thePanelSetupSearchSub8.setTitle(Constant.getTextBundle("ตัวช่วยอาการเบื้องต้น")); 
        thePanelSetupSearchSub8.setComponent(component);
        thePanelSetupSearchSub8.showSearch(); 
       
       
     }
    public void showDialogResultXray(OrderItem theOrderItem)
    {
        if(thePanelResultXray == null)        
            thePanelResultXray = new PanelResultXray(theHC.theUS.getJFrame(),theHC); 
        thePanelResultXray.showSearch(theOrderItem);        
    }
    /**
     *@deprecated henbe unused
     **/
    public void showDialogCalBilling(Vector vOrderItem)
    {
        if(theDialogCalBilling == null)        
            theDialogCalBilling = new DialogCalBilling2(theHC,theUS); 
        theDialogCalBilling.showDialog(vOrderItem);        
    }
    public void showDialogCalBilling(Vector vOrderItem,boolean all)
    {
        if(theDialogCalBilling == null)        
            theDialogCalBilling = new DialogCalBilling2(theHC,theUS); 
        theDialogCalBilling.showDialog(vOrderItem);        
    }
      
    public void showDialogGuideAfterDx(Visit theVisit,Vector vGuide){
        if(dialogGuideAfterDx == null){
            dialogGuideAfterDx = new DialogGuideAfterDx(theHC,theUS
               ,theVisit,vGuide);
        }
        dialogGuideAfterDx.showDialog(theVisit,vGuide);
    }
    
    /**
     * ใช้ในการแสดงประวัติของเลข XN
     * @param theVisit เป็น Object ของ Visit
     * @return เป็น boolean 
     * @author padungrat(tong)
     * @date 18/04/2549,10:50
     */
    public boolean showDialogShowHistoryXN(HosObject hosObject)
    {
        if(theDialogShowHistoryXN == null)        
            theDialogShowHistoryXN = new DialogShowHistoryXN(theHC,theUS); 
        return theDialogShowHistoryXN.showDialog(hosObject);        
    }
    
    public boolean showDialogDischarge(Visit theVisit)
    {
        if(theDialogDischarge == null)        
            theDialogDischarge = new DialogDischarge(theHC,theUS,theVisit); 
        return theDialogDischarge.showDialog(theVisit);        
    }
    public boolean showDialogDischargeIPD(Vector v)
    {
        if(theDialogDischarge == null)        
            theDialogDischarge = new DialogDischarge(theHC,theUS,theHC.theHO.theVisit); 
        return theDialogDischarge.showDialogIPD(v);        
    }
    public void showDialogSeeIcd10(Vector see, JTextField jt)
    {
        if(theDialogSeeIcd10 == null)        
            theDialogSeeIcd10 = new DialogSeeIcd10(theHC,theUS,see,jt); 
        theDialogSeeIcd10.showDialog(see,jt);
    }
    
    public boolean showDialogQueueVisit(Visit theVisit,QueueVisit mqv,int mod,boolean useQueue
            ,Vector vWaitAppointment,Vector vCh)
    {
        if(theDialogQueueVisit == null)
        {
            theDialogQueueVisit = new DialogQueueVisit(theUS.getJFrame(),true);
            theDialogQueueVisit.setControl(theHC,theUS);
        }
        return theDialogQueueVisit.showDialog(theVisit,mqv,mod,useQueue,vWaitAppointment,vCh); 
    }
    public void showDialogChooseOrderItemPrint(JFrame jframe,int preview)
    {
        if(theDialogChooseOrderItemPrint == null)
        {
            theDialogChooseOrderItemPrint = new DialogChooseOrderItemPrint(theUS.getJFrame(),theHC,preview);     
        }
        else
        {
            theDialogChooseOrderItemPrint.setPreview(preview);
        }
    }
    /**
     * ใช้ในการแสดง Dialog เลือกแพทย์ และ clinic ที่ให้รหัสโรค
     * @param emp เป็น Object ของ Employee ที่ได้จากการเลือก (call by value)
     * @param clinic เป็น Object ของ Clinic ที่ได้จากการเลือก (call by value)
     * @author padungrat(tong)
     * @date 19/04/2549,10:55
    */
    public boolean showDialogDoctorDiag(DiagDoctorClinic ddc)
    {
        if(theDialogUseDoctor == null)
            theDialogUseDoctor = new DialogDoctorClinic(theHC,theUS);
        theDialogUseDoctor.setTitle(Constant.getTextBundle("เลือกแพทย์ผู้ให้รหัส"));
        return theDialogUseDoctor.showDialog(ddc);
    }
    /**
     * ใช้ในการแสดง Dialog เลือกแพทย์ และ clinic ที่ให้รหัสโรค
     * @param emp เป็น Object ของ Employee ที่ได้จากการเลือก (call by value)
     * @param clinic เป็น Object ของ Clinic ที่ได้จากการเลือก (call by value)
     * @author henbe
     * @date 5/9/2549,10:55
     * @not deprecated ต้องแก้ให้ใช้งานกับ theDiagDoctorClinic ที่เป็น local และต้องไม่ส่ง employee กับ clinic ไปให้ด้วย
     */
    public boolean showDialogDiag(DiagDoctorClinic ddc)
    {
        if(theDialogUseDoctor == null)
            theDialogUseDoctor = new DialogDoctorClinic(theHC,theUS);
        theDialogUseDoctor.setTitle(Constant.getTextBundle("เลือกแพทย์ผู้ให้รหัส"));
        return theDialogUseDoctor.showDialog(ddc);
    }
    
    /**
     * @deprecated henbe unused
     * ใช้ในการแสดง Dialog เลือกClinicที่ให้ระหัสโรค
     * @param clinic เป็น Object ของ Clinic ที่ได้จากการเลือก (call by value)
     * @author padungrat(tong)
     * @date 19/04/2549,10:55
     */
    public boolean showDialogClinicDiag(DiagDoctorClinic ddc)
    {
        if(theDialogUseDoctor == null)
            theDialogUseDoctor = new DialogDoctorClinic(theHC,theUS);
        theDialogUseDoctor.setTitle(Constant.getTextBundle("เลือกแพทย์ผู้ให้รหัส"));
        return theDialogUseDoctor.showDialog(ddc);
    }
    
    public boolean showDialogContinueDrug(DiagDoctorClinic ddc)
    {
        if(theDialogContinueDrug == null)
            theDialogContinueDrug = new DialogOffDrug(theUS.getJFrame(),true,theHC,theUS);
        theDialogContinueDrug.setTitle(Constant.getTextBundle("เลือกแพทย์ผู้สั่งตรวจต่อเนื่อง"));
        theDialogContinueDrug.setUseContinueOrOff(true);
        return theDialogContinueDrug.showDialog(ddc);
    }
    
    public boolean showDialogOffDrug(DiagDoctorClinic ddc)
    {
        if(theDialogOffDrug == null)
            theDialogOffDrug = new DialogOffDrug(theUS.getJFrame(),true,theHC,theUS);
        theDialogOffDrug.setTitle(Constant.getTextBundle("เลือกแพทย์ผู้ OFF"));
        theDialogOffDrug.setUseContinueOrOff(false);
        return theDialogOffDrug.showDialog(ddc);
    }
    
    
    public void showDialogDeath()
    {
        if(theDialogDeath == null)
            theDialogDeath = new DialogDeath(theHC,theUS);
        theDialogDeath.showDialog(theHC.theHO.theFamily,theHC.theHO.thePatient,theHC.theHO.theVisit);
    }
    public boolean showDialogDeath(Death dt)
    {
        if(theDialogDeath == null)
            theDialogDeath = new DialogDeath(theHC,theUS);
        return theDialogDeath.showDialog(dt);
    }
    
    public void showDialogLabReferIn(HosControl theHC,UpdateStatus theUS)
    {
        if(theDialogLabReferIn == null)
            theDialogLabReferIn = new DialogLabReferIn(theHC,theUS);
        theDialogLabReferIn.showDialog(theHC,theUS);
    }
    
    public void showDialogReverseAdmit(HosControl theHC,UpdateStatus theUS){
        if(theDialogReverseAdmit == null)
            theDialogReverseAdmit = new DialogReverseAdmit(theHC,theUS,true);
        theDialogReverseAdmit.showDialog(theHC.theHO.theVisit);
    }  
    
     public void showDialogSelectPatient(HosControl theHC,UpdateStatus theUS)
     {
        if(theDialogSelectPatientPrintNameCard == null)
            theDialogSelectPatientPrintNameCard = new DialogSelectPatientPrintNameCard(theHC,theUS);
        theDialogSelectPatientPrintNameCard.showDialog();
    }
     
     public void showDialogBorrowFilmXray(HosControl theHC,UpdateStatus theUS)
     {
        if(theDialogBorrowFilmXray == null)
        {
            theDialogBorrowFilmXray = new DialogBorrowFilmXray(theHC,theUS);
        }
        theDialogBorrowFilmXray.showDialog(theHC,theUS);
    }
     
     public void showDialogBorrowOpdCard(HosControl theHC,UpdateStatus theUS)
     {
        if(theDialogBorrowOpdCard == null)
        {
            theDialogBorrowOpdCard = new DialogBorrowOpdCard(theHC,theUS);
        }
        theDialogBorrowOpdCard.showDialog(theHC,theUS);
    }

     public void showDialogNewNotifyNote(String id) {
        if (theDialogNewNotifyNote == null) {
            theDialogNewNotifyNote = new DialogNewNotifyNote(theUS.getJFrame(), true);
            theDialogNewNotifyNote.setControl(theHC);
        }
        theDialogNewNotifyNote.openDialog(id);
    }


     public void showDialogListNotifyNote(String hn) {
        if (theDialogListNotifyNote == null) {
            theDialogListNotifyNote = new DialogListNotifyNote(theUS.getJFrame(), true);
            theDialogListNotifyNote.setControl(theHC, this);
        }
        theDialogListNotifyNote.openDialog(hn);
    }

     public void showDialogListNotifyNote(String hn, String visitId) {
        if (theDialogListNotifyNote == null) {
            theDialogListNotifyNote = new DialogListNotifyNote(theUS.getJFrame(), true);
            theDialogListNotifyNote.setControl(theHC, this);
        }
        theDialogListNotifyNote.openDialog(hn, visitId);
    }
     public static void main(String[] argc)
     {
         Vector v = new Vector();
         for(int i=0;i<100000;i++)
         {
            v.add("");
         }
         long time = System.currentTimeMillis();
         for(int i=0,size=v.size();i<size;i++)
         {
             Constant.print("1");
         }
         Constant.println(System.currentTimeMillis()-time);
         time = System.currentTimeMillis();
         for(int i=0,size=v.size();i<size;i++)
         {
             Constant.print("1");
         }
         Constant.println(System.currentTimeMillis()-time);
     }
     public void showPanelAllRoom()
     {
         if(thePanelAllRoom == null)
         {
             thePanelAllRoom = new PanelAllRoom();
             thePanelAllRoom.setHosControl(theHC);
             Vector v = theHC.theSetupControl.listSingleRoom();
             thePanelAllRoom.setRoomV(v, 4);
         }
         thePanelAllRoom.showDialog();
     }
     public void showPanelAllRoom2()
     {
         if(thePanelAllRoom2 == null)
         {
             thePanelAllRoom2 = new PanelAllRoom2();
             thePanelAllRoom2.setControl(theHC);
             Vector v = theHC.theSetupControl.listSingleRoom();
             thePanelAllRoom2.setRoomV(v, 6);
             Vector v2 = theHC.theSetupControl.listPublicRoom();
             thePanelAllRoom2.setPublicRoomV(v2, 1);
         }
         else
         {
             thePanelAllRoom2.refresh();
         }
         thePanelAllRoom2.showDialog();
     }
}



