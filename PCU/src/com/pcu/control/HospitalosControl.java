/*
 * HospitalosControl.java
 *
 * Created on 7 กุมภาพันธ์ 2549, 10:29 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.control;

import java.util.Vector;

import com.hospital_os.utility.Constant;
import com.hosv3.control.*;
import com.hosv3.control.lookup.VitalTemplateLookup;

import com.hospital_os.usecase.connection.*;
import com.hospital_os.object.*;

/**
 *
 * @author Jao
 * @deprecated henbe unused
 */
public class HospitalosControl implements HospitalosControlInf{  
      
    /**เป็นข้อมูลของ Object HosControl ของ HosV3*/
    HosControl theHHC;   
    /** Creates a new instance of HospitalosControl */
    
    public HospitalosControl(HosControl hhc) {
        theHHC = hhc;
    }
    
    public int deletePatient(Patient p) {
        return theHHC.thePatientControl.deletePatient(p);
    }

    public void readPatientByPatientID(String pid) {
        theHHC.thePatientControl.readPatientByPatientID(pid);
    }

    public void resetPatient() {
        theHHC.thePatientControl.resetPatient();
    }

    public int savePatient(Patient p, String age) {   
        
        return theHHC.thePatientControl.savePatient(p,age);
    }

    public ConnectionInf getConnection() {
        ConnectionInf theConnectionInf = theHHC.theConnectionInf;
        return theConnectionInf;
    }

    public Site getSite() {
        return theHHC.theLookupControl.readSite();
    }

    public String getTextCurrentDateTime() {
        return theHHC.theLookupControl.getTextCurrentDateTime();
    }
   

    public Item listItemByPk(String itemid) {
        return theHHC.theSetupControl.listItemByPk(itemid);
    }    

    public Relation readRelationByName(String name) {  
        return theHHC.theLookupControl.readRelationByName(name);
    }

    public void saveRelation(Relation r) {
        theHHC.theLookupControl.saveRelation(r);
    }

    public void setStatus(String status, int istatus) {
        theHHC.theUS.setStatus(status,istatus);
    }

    public Vector listPatientByHn(String hn) {
        return theHHC.thePatientControl.listPatientByHn(hn);
    }

    public Vector listPatientByName(String pname, String fname, String lname) {
        return theHHC.thePatientControl.listPatientByName(pname, fname, lname);
    }

    public Vector listPatientByPID(String pid) {
        return theHHC.thePatientControl.listPatientByPID(pid);
    }

    public Employee listEmployeeByPk(String pk) {
        return theHHC.theLookupControl.readEmployeeById(pk);   
    }

    public javax.swing.JFrame getJFrame() {  
        return theHHC.theUS.getJFrame();  
    }

    public Patient getCurrentPatient() {
        return theHHC.theHO.thePatient;
    }

    public String listPrefix(String id) {
        return theHHC.theLookupControl.readPrefixString(id);
    }

    public com.hospital_os.usecase.connection.LookupControlInf getVitalTemplate() {
        return new VitalTemplateLookup(getLookupControl());
    }

    public com.hosv3.control.LookupControl getLookupControl() {
        return theHHC.theLookupControl;
    }


    public ICD10 listIcd10ById(String icd10Id) {
        return theHHC.theDiagnosisControl.listIcd10ById(icd10Id);
    }

    public String getDateTime() {
        return theHHC.theHO.date_time;
    }

    public com.hosv3.control.BillingControl getBillingControl() {
        return theHHC.theBillingControl;
    }

    public Vector getvBillingPatient() {
        return theHHC.theHO.vBillingPatient;
    }

    public Vector getvOrderItemReceiveDrug() {
        return theHHC.theHO.vOrderItemReceiveDrug;
    }

    public Vector listPatientPaymentByPatientId(String patient_id) {
        return theHHC.thePatientControl.listPatientPaymentByPatientId(patient_id);
    }

    public SetupControl getSetupControl() {
        return theHHC.theSetupControl;
    }    

    public VisitControl getVisitControl() {
        return theHHC.theVisitControl;
    }
    
    public int readPatientByHn(String hn) {
        return theHHC.thePatientControl.readPatientByHn(hn);
    }

    public HosControl getHosControl() {
        return theHHC;
    }

    public void saveCronic(Chronic chronic) {
        theHHC.theDiagnosisControl.saveChronic(chronic);
    }
    /**
     *@deprecated henbe unused
     */
    public Office listOfficeByOffId(String id) {
        return theHHC.theLookupControl.readHospitalByCode(id);
    }
    
    public GroupIcd10 listGroupIcd10ByIcdCode(String code) {
        return theHHC.theDiagnosisControl.readGroupIcd10ByIcdCode(code);
    }

    public Employee getCurrentEmployee() {
        return theHHC.theHO.theEmployee;
    }

    public int calculateNutrition(String sex, String month, String weight)
    {
        return Constant.calculateIndexComboBoxNutrition(sex,month,weight);
    }

    public com.hosv3.subject.HosSubject readHosSubject()
    {
        return theHHC.theHS;
    }

    public boolean confirmBox(String str, int status)
    {
       return theHHC.theUS.confirmBox(str,status);
    }

    
}
