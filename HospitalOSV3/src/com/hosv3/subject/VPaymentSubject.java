/*
 * visitSubject.java
 *
 * Created on 17 ตุลาคม 2546, 17:09 น.
 */
package com.hosv3.subject;
import com.hosv3.usecase.transaction.*;
import java.util.*;
/**
 *
 * @author  tong
 */
public class VPaymentSubject {
  
    Vector thePaymentObsV;
    /** Creates a new instance of visitSubject */
    public VPaymentSubject()
    {
        thePaymentObsV = new Vector();
    }
    public void removeAttach()
    {
        thePaymentObsV.removeAllElements();
        
    }
    public void attach(ManageVPaymentResp o){
        thePaymentObsV.add(o);
    }
    /**
     * @roseuid 3F8400140271
     */
    public void notifySaveVPayment(String msg,int state)
    {
         for(int i=0,size=thePaymentObsV.size();i<size;i++) 
        {
            ((ManageVPaymentResp)thePaymentObsV.get(i)).notifySaveVPayment(msg,state);
        }
    }
    public void notifyDeleteVPayment(String msg,int state)
    {
         for(int i=0,size=thePaymentObsV.size();i<size;i++) 
        {
            ((ManageVPaymentResp)thePaymentObsV.get(i)).notifyDeleteVPayment(msg,state);
        }
    }
    public void notifyUpVPaymentPriority(String msg,int state)
    {
         for(int i=0,size=thePaymentObsV.size();i<size;i++) 
        {
            ((ManageVPaymentResp)thePaymentObsV.get(i)).notifyUpVPaymentPriority(msg,state);
        }
    }
    public void notifyDownVPaymentPriority(String msg,int state)
    {
         for(int i=0,size=thePaymentObsV.size();i<size;i++) 
        {
            ((ManageVPaymentResp)thePaymentObsV.get(i)).notifyDownVPaymentPriority(msg,state);
        }
    }

    public boolean detach(ManageVPaymentResp o)
    {
        return thePaymentObsV.remove(o);
    }
}
