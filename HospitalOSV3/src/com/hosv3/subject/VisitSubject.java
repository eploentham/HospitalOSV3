/*
 * visitSubject.java
 *
 * Created on 17 ตุลาคม 2546, 17:09 น.
 */
package com.hosv3.subject;
import com.hosv3.usecase.transaction.*;
//import com.hosv3.utility.*;
import java.util.*;
import com.hosv3.utility.Constant;
/**
 *
 * @author  tong
 */
public class VisitSubject implements ManageVisitResp{
  
    Vector theVisitObsV;
    /** Creates a new instance of visitSubject */
    public VisitSubject()
    {
        theVisitObsV = new Vector();
    }
    
    public void removeAttach()
    {
        theVisitObsV.removeAllElements();
    }
    
    public void attachManageVisit(ManageVisitResp o)
    {
        theVisitObsV.add(o);
    }
   /**
     * @roseuid 3F8400140271
     */
    public void notifyVisitPatient(String msg,int state)
    {
        for(int i=0,size=theVisitObsV.size();i<size;i++) 
        {
            Constant.println("__VisitPatient________________" + theVisitObsV.get(i).getClass().toString());
            ((ManageVisitResp)theVisitObsV.get(i)).notifyVisitPatient(msg,state);
        }
    }
    public void notifyReverseFinancial(String msg,int state)
    {
        for(int i=0,size=theVisitObsV.size();i<size;i++) 
        {
            ((ManageVisitResp)theVisitObsV.get(i)).notifyReverseFinancial(msg,state);
        }
    }
    public void notifyReadVisit(String msg,int state)
    {
        for(int i=0,size=theVisitObsV.size();i<size;i++) 
        {
            Constant.println("__ReadVisit________________" + theVisitObsV.get(i).getClass().toString());
            ((ManageVisitResp)theVisitObsV.get(i)).notifyReadVisit(msg,state);
        }
    }
    public void notifyObservVisit(String msg,int state)
    {
        for(int i=0,size=theVisitObsV.size();i<size;i++) 
        {
            ((ManageVisitResp)theVisitObsV.get(i)).notifyObservVisit(msg,state);
        }
    }
    public void notifySendVisit(String msg,int state)
    {
        for(int i=0,size=theVisitObsV.size();i<size;i++) 
        {
            Constant.println("__SendVisit________________" + theVisitObsV.get(i).getClass().toString());
            ((ManageVisitResp)theVisitObsV.get(i)).notifySendVisit(msg,state);
        }
    }
    public void notifyUnlockVisit(String msg,int state)
    {
        for(int i=0,size=theVisitObsV.size();i<size;i++) 
        {
            Constant.println("__UnlockVisit________________" + theVisitObsV.get(i).getClass().toString());
            ((ManageVisitResp)theVisitObsV.get(i)).notifyUnlockVisit(msg,state);
        }
    }
    public void notifyDropVisit(String msg,int state)
    {
        for(int i=0,size=theVisitObsV.size();i<size;i++) 
        {
            ((ManageVisitResp)theVisitObsV.get(i)).notifyDropVisit(msg,state);
        }
    }
    public void notifyAdmitVisit(String msg,int state)
    {
        for(int i=0,size=theVisitObsV.size();i<size;i++) 
        {
            ((ManageVisitResp)theVisitObsV.get(i)).notifyAdmitVisit(msg,state);
        }
    }
    public void notifyDischargeFinancial(String msg,int state)
    {
        for(int i=0,size=theVisitObsV.size();i<size;i++) 
        {
            ((ManageVisitResp)theVisitObsV.get(i)).notifyDischargeFinancial(msg,state);
        }
    }
   public void notifyCheckDoctorTreament(String msg,int state)
   {
       for(int i=0,size=theVisitObsV.size();i<size;i++) 
        {
           ((ManageVisitResp)theVisitObsV.get(i)).notifyCheckDoctorTreament(msg,state); 
        }
   }

   
    public void notifyReverseDoctor(String str, int status) {
        for(int i=0,size=theVisitObsV.size();i<size;i++) {
           ((ManageVisitResp)theVisitObsV.get(i)).notifyReverseDoctor(str,status);
        }
    }
   
    public void notifyDeleteVisitPayment(String str, int status) {
        for(int i=0,size=theVisitObsV.size();i<size;i++) {
           ((ManageVisitResp)theVisitObsV.get(i)).notifyDeleteVisitPayment(str,status);
        }
    }
    
    public void notifyDischargeDoctor(String str, int status) {
        for(int i=0,size=theVisitObsV.size();i<size;i++) 
        {
            ((ManageVisitResp)theVisitObsV.get(i)).notifyDischargeDoctor(str,status); 
        }
    }
    
    public void notifySendVisitBackWard(String str, int status) {
        for(int i=0,size=theVisitObsV.size();i<size;i++) 
        {
            ((ManageVisitResp)theVisitObsV.get(i))
                .notifySendVisitBackWard(str,status); 
        }
    }    
    
    public void notifyRemainDoctorDischarge(String str, int status) {
        for(int i=0,size=theVisitObsV.size();i<size;i++) 
        {
            ((ManageVisitResp)theVisitObsV.get(i))
                .notifyRemainDoctorDischarge(str,status); 
        }
    }
    
    public void notifyReverseAdmit(String str, int status) {
        for(int i=0,size=theVisitObsV.size();i<size;i++) 
        {
            ((ManageVisitResp)theVisitObsV.get(i))
                .notifyReverseAdmit(str,status); 
        }
    }


    public boolean detach(ManageVisitResp o)
    {
        return theVisitObsV.remove(o);
    }
    
}
