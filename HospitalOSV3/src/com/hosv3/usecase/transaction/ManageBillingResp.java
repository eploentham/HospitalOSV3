/*
 * BillingInvoiceReq.java
 *
 * Created on 30 ÁÔ¶Ø¹ÒÂ¹ 2547, 17:27 ¹.
 */

package com.hosv3.usecase.transaction;
/**
 *
 * @author  tong
 */
public interface ManageBillingResp {
    
    public void notifyCancelBillingInvoice(String str, int status);
    public void notifyCancelBill(String str, int status);
    public void notifyCalculateAllBillingInvoice(String str, int status);
    public void notifyPatientPaidMoney(String str, int status);
    public void notifyBillingInvoice(String str, int status);
    /*
    public void notifyListBilling(Vector bill);
    public void notifyCancelReceipt(String str,int status);
    public void notifyBillingReceipt(String receiptId);
    public void notifyRefreshDebt(boolean debt);
    public int billingInvoice(Vector[] avector, Visit vs, Vector vorder);
    public Vector listPatientByRemainDispense(String d);
    public Vector listPatientRemainBillByHN(String hn);
    public void patientPaidMoney(Billing billing, Vector vBillingInvoiceSubgroup, String patientPaid);
    public void refreshDebt(boolean debt);
    public void removeBillingInvoice(Visit visit, BillingInvoice bi);
    public void saveBillReq(Vector bill);
     */
}
