
/*
 * BillingSubject.java
 *
 * Created on 17 ตุลาคม 2546, 17:05 น.
 */
package com.hosv3.subject;
import com.hosv3.usecase.transaction.*;
import java.util.*;
/**
 *
 * @author tong
 */
public class BillingSubject implements ManageBillingResp{

    Vector theBillingObsV;
    
    /** Creates a new instance of BillingSubject */
    public BillingSubject() {
        theBillingObsV = new Vector();
    }
    public void removeAttach()
    {
        theBillingObsV.removeAllElements();
        
    }
    public void attachManageBilling(ManageBillingResp o) {
        theBillingObsV.add(o);
    }
    
    public void notifyCancelBillingInvoice(String str, int status) {
        for(int i=0,size=theBillingObsV.size();i<size;i++) {
            ((ManageBillingResp)theBillingObsV.get(i)).notifyCancelBillingInvoice(str,status);
        }
    }
    public void notifyCancelBill(String str, int status) {
        for(int i=0,size=theBillingObsV.size();i<size;i++) {
            ((ManageBillingResp)theBillingObsV.get(i)).notifyCancelBill(str,status);
        }
    }

    public void notifyCalculateAllBillingInvoice(String str,int status) {
        for(int i=0,size=theBillingObsV.size();i<size;i++)
            ((ManageBillingResp)theBillingObsV.get(i)).notifyCalculateAllBillingInvoice(str,status);
    }
    
    public void notifyPatientPaidMoney(String str, int status) {
        for(int i=0,size=theBillingObsV.size();i<size;i++)
            ((ManageBillingResp)theBillingObsV.get(i)).notifyPatientPaidMoney(str,status);
        
    }

    public void notifyBillingInvoice(String str, int status) {
        for(int i=0;i< theBillingObsV.size() ; i++)
            ((ManageBillingResp)theBillingObsV.get(i)).notifyBillingInvoice(str,status);
    }
    
/*henbe_error incomplete function    
    public void notifyRefreshDebt(boolean debt) {
        for(int i=0,size=theBillingObsV.size();i<size;i++)
            ((ManageBillingResp)theBillingObsV.get(i)).notifyRefreshDebt(debt);
    }
*/
  
}
