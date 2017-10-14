/*
 * HospitalosGui.java
 *
 * Created on 6 กุมภาพันธ์ 2549, 17:46 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu;

import com.hospital_os.utility.Constant;
import com.pcu.control.HosManage;
import com.pcu.control.PCUObject;
import com.pcu.MenuPCU;

import com.hosv3.control.*;
import com.hosv3.usecase.transaction.ManagePatientResp;
import com.hosv3.usecase.transaction.ManageVisitResp;    
import com.hosv3.usecase.transaction.ManageVitalResp;
import com.hosv3.usecase.transaction.ManageBalloon;
import com.hosv3.usecase.transaction.ManageOrderResp;
import com.hosv3.usecase.transaction.ManageBillingResp;
import com.hosv3.usecase.transaction.ManageLabXrayResp;
import com.hosv3.usecase.transaction.ManageVPaymentResp;
import com.pcu.subject.ManegePersonResp;
import com.pcu.object.*;

/**
 *
 * @author Jao
 */
public class HospitalosGui implements ManagePatientResp,ManageVisitResp,ManageVitalResp
 ,ManageBalloon,ManageOrderResp,ManageBillingResp,ManageLabXrayResp,ManageVPaymentResp,ManegePersonResp{//,ManagePersonResp{
    
    private HosControl theHHC;
    public PCUObject thePCUObject;
    private HosManage theHM; 
    private MenuPCU theMenuPCU ;
    
    /** Creates a new instance of HospitalosGui */
    public HospitalosGui(PCUObject pcuO,HosControl hhc,HosManage hm) {
        theHHC = hhc;
        theHM = hm;
        thePCUObject = pcuO;
        theHHC.theHS.thePatientSubject.attachManagePatient(this);
        theHHC.theHS.theVisitSubject.attachManageVisit(this);
        theHHC.theHS.theVitalSubject.attachManageVital(this);
        theHHC.theHS.theBalloonSubject.attachBalloon(this);
        theHHC.theHS.theOrderSubject.attachManageOrder(this);
        theHHC.theHS.theBillingSubject.attachManageBilling(this);  
        theHHC.theHS.theResultSubject.attachManageLab(this);
        theHHC.theHS.theResultSubject.attachManageXray(this);
        theHHC.theHS.theVPaymentSubject.attach(this);
        theHM.theHosSubject.thePersonSubject.attachManagePerson(this);
//        theHHC.theHS.thePersonSubject.attachManagePerson(this);
//        thePanelSurvey = new PanelSurvey(theHM,hd);
        setObjectTransaction();
    }
    public void setMenuPCU(MenuPCU menu){
        theMenuPCU  = menu;
    }
    
    private void setObjectTransaction()
    {   
        Constant.println("private void setObjectTransaction()");
        if(this.theHHC == null)
            return;
        thePCUObject.setHosObject(theHHC.theHO);
        if(theMenuPCU==null)
            return;
        theMenuPCU.setObject(thePCUObject); 
        
        if(!theMenuPCU.isPanelPCUVisible())
            return;
        theHM.theHosControl.readFamilySuit(thePCUObject.getFamily(),true);
        if(theMenuPCU.thePanelPCU!=null)
            theMenuPCU.thePanelPCU.setObject(thePCUObject);
        Constant.println("private void setObjectTransaction()");
    }
    
    public void notifyAddDxTemplate(String str, int param) {
    }

    public void notifyAddPrimarySymptom(String str, int param) {
    }

    public void notifyAdmitVisit(String str, int param) {
        setObjectTransaction();
    }

    public void notifyCheckDoctorTreament(String str, int param) {
    }

    public void notifyDeleteMapVisitDxByVisitId(String str, int param) {
    }

    public void notifyDeletePatient(String str, int param) {
        setObjectTransaction();
    }

    public void notifyDeletePatientPayment(String str, int param) {
    }

    public void notifyDeleteVisitPayment(String str, int param) {
    }

    public void notifyDischargeDoctor(String str, int param) {
        setObjectTransaction();
    }

    public void notifyDischargeFinancial(String str, int param) {
        setObjectTransaction();
    }

    public void notifyDropVisit(String str, int param) {
        setObjectTransaction();
    }

    public void notifyManageDrugAllergy(String str, int param) {
    }

    public void notifyManagePhysicalExam(String str, int param) {
        
    }

    public void notifyManagePrimarySymptom(String str, int param) {
    }

    public void notifyManageVitalSign(String str, int param) {

    }

    public void notifyObservVisit(String str, int param) {
    }

    public void notifyReadPatient(String str, int param) 
    {
        setObjectTransaction();
    }

    public void notifyReadVisit(String str, int param) {
        setObjectTransaction();
    }

    public void notifyRemainDoctorDischarge(String str, int param) {
        setObjectTransaction();
    }

    public void notifyResetPatient(String str, int param) {
    }

    public void notifyReverseAdmit(String str, int param) {
    }

    public void notifyReverseDoctor(String str, int param) {
        setObjectTransaction();
    }

    public void notifyReverseFinancial(String str, int param) {
        setObjectTransaction();
    }

    public void notifySaveAppointment(String str, int param) {
    }

    public void notifySaveDiagDoctor(String str, int param) {
    }

    public void notifySavePatient(String str, int param) {
        setObjectTransaction();
    }

    public void notifySavePatientPayment(String str, int param) {
    }

    public void notifySendVisit(String str, int param) {
        setObjectTransaction();
    }

    public void notifySendVisitBackWard(String str, int param) {
        setObjectTransaction();
    }

    public void notifyUnlockVisit(String str, int param) 
    {
        setObjectTransaction();
    }

    public void notifyVisitPatient(String str, int param) {
        setObjectTransaction();
    }

    public void notifyCloseBalloon(String str, int param) {
        for(int j=0;j<theHM.theHosControl.balloon.size();j++){
            ManageBalloon mb = (ManageBalloon)theHM.theHosControl.balloon.get(j);
            mb.notifyCloseBalloon("close", 1);
        }
    }

    public void notifyAddLabReferIn(String str, int param) {
    }

    public void notifyAddLabReferOut(String str, int param) {
    }

    public void notifyBillingInvoice(String str, int param) {
    }

    public void notifyCalculateAllBillingInvoice(String str, int param) {
    }

    public void notifyCancelBill(String str, int param) {
    }

    public void notifyCancelBillingInvoice(String str, int param) {
    }

    public void notifyCancelOrderItem(String str, int param) {
    }

    public void notifyCheckAutoOrder(String str, int param) {
    }

    public void notifyContinueOrderItem(String str, int param) {
    }

    public void notifyDeleteFilmXray(String str, int param) {
    }

    public void notifyDeleteLabOrder(String str, int param) {
    }

    public void notifyDeleteLabResult(String str, int param) {
    }

    public void notifyDeleteQueueLab(String str, int param) {
        notifyUnlockVisit(str,param);
    }

    public void notifyDeleteResultXray(String str, int param) {
    }

    public void notifyDeleteVPayment(String str, int param) {
    }

    public void notifyDeleteXrayPosition(String str, int param) {
    }

    public void notifyDispenseOrderItem(String str, int param) {
    }

    public void notifyDoctorOffDrug(String str, int param) {
    }

    public void notifyDownVPaymentPriority(String str, int param) {
    }

    public void notifyExecuteOrderItem(String str, int param) {
    }

    public void notifyManagePatientLabReferIn(String str, int param) {
    }

    public void notifyPatientPaidMoney(String str, int param) {
    }

    public void notifyReceiveReturnDrug(String str, int param) {
    }

    public void notifyReferOutLab(String str, int param) {
    }

    public void notifyReportResultLab(String str, int param) {
    }

    public void notifySaveFilmXray(String str, int param) {
    }

    public void notifySaveLabResult(String str, int param) {
    }

    public void notifySaveOrderItem(String str, int param) {
    }

    public void notifySaveOrderItemInLab(String str, int param) {
    }

    public void notifySaveOrderRequest(String str, int param) {
    }

    public void notifySaveRemainLabResult(String str, int param) {
    }

    public void notifySaveResultXray(String str, int param) {
    }

    public void notifySaveReturnDrug(String str, int param) {
    }

    public void notifySaveVPayment(String str, int param) {
    }

    public void notifySaveXrayPosition(String str, int param) {
    }

    public void notifySendResultLab(String str, int param) {
    }

    public void notifyUpVPaymentPriority(String str, int param) {
    }

    public void notifyVerifyOrderItem(String str, int param) {
    }

    public void notifyXrayReportComplete(String str, int param) {
    }

    public void notifysaveFamily(Family family) {
        this.setObjectTransaction();
    }

    public void notifyselectFamily(Family family) 
    {
    }

    public void notifySavePersonPayment(String str, int status) {
    }

    public void notifyDeletePersonPayment(String str, int status) {
    }

    public void notifyReadFamily(String str, int status) 
    {
        setObjectTransaction();
    }
    
    
}
