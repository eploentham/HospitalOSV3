/*
 * PrintSubject.java
 *
 * Created on 2 กรกฎาคม 2548, 15:21 น.
 */

package com.hosv3.subject;
import java.util.*;
import com.hosv3.usecase.transaction.*;

/**
 *
 * @author kingland
 */
public class PrintSubject implements ManagePrintResp{
    
    private Vector vPrintObserv;
    /** Creates a new instance of PrintSubject */
    public PrintSubject() {
        vPrintObserv = new Vector();
    }
    public void removeAttach()
    {
        vPrintObserv.removeAllElements();
        
    }
    public void attachManagePrint (ManagePrintResp o)
    {
        vPrintObserv.add(o);
    }
    public void notifyPrintDrugSticker(String msg, int status) {
        for(int i=0,size=vPrintObserv.size();i<size;i++) {
            ((ManagePrintResp)vPrintObserv.get(i)).notifyPrintDrugSticker(msg,status); 
        }
    }

    public void notifyPrintOPDCard(String msg, int status) {
        for(int i=0,size=vPrintObserv.size();i<size;i++) {
            ((ManagePrintResp)vPrintObserv.get(i)).notifyPrintOPDCard(msg,status); 
        }
    }
    
     public void notifyPrintAppointmentList(String msg, int status) {
        for(int i=0,size=vPrintObserv.size();i<size;i++) {
            ((ManagePrintResp)vPrintObserv.get(i)).notifyPrintAppointmentList(msg,status); 
        }
    }
     
     public void notifyPreviewAppointmentList(String msg, int status) {
        for(int i=0,size=vPrintObserv.size();i<size;i++) {
            ((ManagePrintResp)vPrintObserv.get(i)).notifyPreviewAppointmentList(msg,status); 
        }
    }
     
      public void notifyPrintChronicList(String msg, int status) {
        for(int i=0,size=vPrintObserv.size();i<size;i++) {
            ((ManagePrintResp)vPrintObserv.get(i)).notifyPrintChronicList(msg,status); 
        }
    }
      public void notifyPriviewChronicList(String msg, int status) {
        for(int i=0,size=vPrintObserv.size();i<size;i++) {
            ((ManagePrintResp)vPrintObserv.get(i)).notifyPriviewChronicList(msg,status); 
        }
    }
      
      public void notifyPreviewSelectDrugList(String msg, int status) {
        for(int i=0,size=vPrintObserv.size();i<size;i++) {
            ((ManagePrintResp)vPrintObserv.get(i)).notifyPreviewSelectDrugList(msg,status); 
        }
    }    
      
     public void notifyPrintSelectDrugList(String msg, int status) {
        for(int i=0,size=vPrintObserv.size();i<size;i++) {
            ((ManagePrintResp)vPrintObserv.get(i)).notifyPrintSelectDrugList(msg,status); 
        }
    }  
     public void notifyPrintSumByBillingGroup(String msg, int status) {
        for(int i=0,size=vPrintObserv.size();i<size;i++) {
            ((ManagePrintResp)vPrintObserv.get(i)).notifyPrintSumByBillingGroup(msg,status); 
        }
    } 
    public void notifyPreviewSumByBillingGroup(String msg, int status) {
        for(int i=0,size=vPrintObserv.size();i<size;i++) {
            ((ManagePrintResp)vPrintObserv.get(i)).notifyPreviewSumByBillingGroup(msg,status); 
        }
    }     
}
