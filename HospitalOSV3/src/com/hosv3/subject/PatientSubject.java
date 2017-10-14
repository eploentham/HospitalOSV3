/*
 * PatientSubject.java
 *
 * Created on 17 ตุลาคม 2546, 17:03 น.
 */
package com.hosv3.subject;

import com.hosv3.utility.Constant;
import com.hosv3.usecase.transaction.*;
import java.util.*;
/**
 *
 * @author  tong
 */
public class PatientSubject implements ManagePatientResp{
    Vector theManagePatient;
    
    /** Creates a new instance of PatientSubject */
    public PatientSubject() {
        theManagePatient=new Vector();
    }
     public void removeAttach()
    {
        theManagePatient.removeAllElements();
        
    }
    public void attachManagePatient (ManagePatientResp o)
    {
        theManagePatient.add(o);
    }
    public boolean detach(ManagePatientResp o)
    {
        return theManagePatient.remove(o);
    }
    /** Creates a new instance of createPatientAllergy  */
   
    /** Creates a new instance of createPatientAllergy              */
    public void notifySavePatient(String str, int status) {
        for(int i=0,size=theManagePatient.size();i<size;i++){
            Constant.println("__SavePatient________________" + theManagePatient.get(i).getClass().toString());
            ((ManagePatientResp)theManagePatient.get(i)).notifySavePatient(str,status);
        }
    }
    public void notifyResetPatient(String str, int status) {
        for(int i=0,size=theManagePatient.size();i<size;i++){
            Constant.println("__ResetPatient________________" + theManagePatient.get(i).getClass().toString());
            ((ManagePatientResp)theManagePatient.get(i)).notifyResetPatient(str,status);
        }
    }
    public void notifyDeletePatient(String str, int status) {
        for(int i=0,size=theManagePatient.size();i<size;i++){
            ((ManagePatientResp)theManagePatient.get(i)).notifyDeletePatient(str,status);
        }
    }
    
    public void notifyReadPatient(String str, int status) {
        for(int i=0,size=theManagePatient.size();i<size;i++){
            Constant.println("__ReadPatient________________" + theManagePatient.get(i).getClass().toString());
            ((ManagePatientResp)theManagePatient.get(i)).notifyReadPatient(str,status);
        }
    }

    public void notifyReadFamily(String str, int status) {
        for(int i=0,size=theManagePatient.size();i<size;i++){
            Constant.println("__ReadFamily________________" + theManagePatient.get(i).getClass().toString());
            ((ManagePatientResp)theManagePatient.get(i)).notifyReadFamily(str,status);
        }
    }   
    
    public void notifyManageDrugAllergy(String str, int status) {
         for(int i=0,size=theManagePatient.size();i<size;i++){
            ((ManagePatientResp)theManagePatient.get(i)).notifyManageDrugAllergy(str,status);
        }
    }
    
    public void notifySavePatientPayment(String str, int status) {
        for(int i=0,size=theManagePatient.size();i<size;i++){
            ((ManagePatientResp)theManagePatient.get(i)).notifySavePatientPayment(str,status);
        }
    }
    
    public void notifyDeletePatientPayment(String str, int status) {
        for(int i=0,size=theManagePatient.size();i<size;i++){
            ((ManagePatientResp)theManagePatient.get(i)).notifyDeletePatientPayment(str,status);
        }
    }
    
    public void notifySaveAppointment(String str, int status) {
        for(int i=0,size=theManagePatient.size();i<size;i++){
            ((ManagePatientResp)theManagePatient.get(i)).notifySaveAppointment(str,status);
        }
    }
    
    public void notifySaveBorrowFilmXray(String str, int status) {
//        for(int i=0,size=theManagePatient.size();i<size;i++){
//            ((ManagePatientResp)theManagePatient.get(i)).notifySaveBorrowFilmXray(str,status);
//        }
    }
    
}
