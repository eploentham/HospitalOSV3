/*
 * visitSubject.java
 *
 *
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
public class VitalSubject implements ManageVitalResp{
  
    Vector theVitalObsV;
    Vector thesee;
    /** Creates a new instance of visitSubject */
    public VitalSubject()
    {
        theVitalObsV = new Vector();
        thesee = new Vector();
    }
    
    public void removeAttach()
    {
        theVitalObsV.removeAllElements();
        thesee.removeAllElements();
        
    }
    public void attachManageVital(ManageVitalResp o)
    {
        theVitalObsV.add(o);
    }
   /**
     * @roseuid 3F8400140271
     */
    public void notifyManageVitalSign(String msg,int state)
    {
         for(int i=0,size=theVitalObsV.size();i<size;i++) 
        {
            ((ManageVitalResp)theVitalObsV.get(i)).notifyManageVitalSign(msg,state);
        }
    }
    public void notifySaveDiagDoctor(String msg, int state)
    { 
       for(int i=0,size=theVitalObsV.size();i<size;i++)
       {
          ((ManageVitalResp)theVitalObsV.get(i)).notifySaveDiagDoctor(msg,state); 
       }
    }
    public void notifyAddDxTemplate(String msg,int state)
   {
        for(int i=0,size=theVitalObsV.size();i<size;i++) 
        {
           ((ManageVitalResp)theVitalObsV.get(i)).notifyAddDxTemplate(msg,state); 
        }
   }
    public void notifyManagePrimarySymptom(String msg,int state)
    {
         for(int i=0,size=theVitalObsV.size();i<size;i++) 
        {
           ((ManageVitalResp)theVitalObsV.get(i)).notifyManagePrimarySymptom(msg,state);  
        }
    }    
    public void notifyDeleteMapVisitDxByVisitId(String msg,int state)
    {
         for(int i=0,size=theVitalObsV.size();i<size;i++) 
        {
           ((ManageVitalResp)theVitalObsV.get(i)).notifyDeleteMapVisitDxByVisitId(msg,state);  
        }
    }
    
    public void notifyManagePhysicalExam(String str, int status) {
         for(int i=0,size=theVitalObsV.size();i<size;i++)  {
            ((ManageVitalResp)theVitalObsV.get(i)).notifyManagePhysicalExam(str,status);  
        }
    }    
    
     /*เมื่อมีการเพิ่มอาการปัจจุบันจากDialog searchsub*/
    public void notifyAddPrimarySymptom(String str, int status) {
         for(int i=0,size=theVitalObsV.size();i<size;i++)  {
            ((ManageVitalResp)theVitalObsV.get(i)).notifyAddPrimarySymptom(str,status);  
        }
    } 
    
  }
