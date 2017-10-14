/*
 * listGroupByVnReq.java
 *
 * Created on 26 กรกฎาคม 2547, 16:17 น.
 */

package com.hosv3.usecase.transaction;
/**
 *
 * @author  amp
 */

public interface ManageVisitResp 
{
    /*public void notifyManageDoctorTreament(String str,int status);
    public void notifyManageAutoVisitPatient(String str,int status);
    public void notifyManageVitalSign(String str,int status);
    public void notifyManageTransfer(String str,int status);
    public void notifyManageRefer(String str,int status);
    public void notifyPrimarySymptom(String str,int status);
    public void notifyPhysicalExam(String str,int status);
    public void notifyPayment(String str,int status);
    */
    public void notifyReadVisit(String str,int status);
    public void notifyReverseFinancial(String str,int status);
    public void notifyReverseDoctor(String str,int status);
    public void notifyVisitPatient(String str,int status);
    public void notifySendVisit(String str,int status);
    public void notifySendVisitBackWard(String str,int status);
    public void notifyObservVisit(String str,int status);
    public void notifyUnlockVisit(String str,int status);
    public void notifyDropVisit(String str,int status);
    public void notifyAdmitVisit(String str,int status);
    public void notifyCheckDoctorTreament(String msg,int state);
    public void notifyDischargeDoctor(String str,int status);
    public void notifyRemainDoctorDischarge(String str,int status);
    public void notifyDischargeFinancial(String str,int status);
    public void notifyDeleteVisitPayment(String str,int status);
    public void notifyReverseAdmit(String str,int status);
}


