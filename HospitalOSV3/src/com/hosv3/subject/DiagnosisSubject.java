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
public class DiagnosisSubject {
    Vector theDiagnosisV;
    /** Creates a new instance of visitSubject */
    public DiagnosisSubject(){
        theDiagnosisV = new Vector();
    }
    public void removeAttach()
    {
        theDiagnosisV.removeAllElements();
        
    }
    public void attachManageDiagnosis(ManageDiagnosisResp o){
        theDiagnosisV.add(o);
    }
    /**
     * @roseuid 3F8400140271
     */
    public void notifyManageDiagIcd9(String msg,int status){
        for(int i=0;i<theDiagnosisV.size();i++) {
            ((ManageDiagnosisResp)theDiagnosisV.get(i)).notifyManageDiagIcd9(msg,status);
        }
    }
    public void notifyManageDiagIcd10(String msg,int status){
        for(int i=0;i<theDiagnosisV.size();i++) {
            ((ManageDiagnosisResp)theDiagnosisV.get(i)).notifyManageDiagIcd10(msg,status);
        }
    }
    public void notifyDeleteParticipateOr(String msg,int status){
        for(int i=0;i<theDiagnosisV.size();i++) {
            ((ManageDiagnosisResp)theDiagnosisV.get(i)).notifyDeleteParticipateOr(msg,status);
        }
    }
    
  }
