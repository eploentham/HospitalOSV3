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

public interface ManageVPaymentResp 
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
    public void notifyUpVPaymentPriority(String str,int status);
    public void notifyDownVPaymentPriority(String str,int status);
    public void notifySaveVPayment(String str,int status);
    public void notifyDeleteVPayment(String str,int status);
}


