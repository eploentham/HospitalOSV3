/*
 * SuitPatient.java
 *
 * Created on 10 เมษายน 2550, 14:38 น.
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.hosv3.control;

import com.hospital_os.object.DiagIcd10;
import com.hospital_os.object.MapVisitDx;
import com.hospital_os.object.Patient;
import com.hospital_os.object.Visit;
import com.hospital_os.object.X39Persistent;
import com.hosv3.object.HosObject;
import com.hosv3.utility.connection.UpdateStatus;
import java.util.Vector;

/**
 *
 * @author Administrator
 */
public class GPatientSuit {
    
    HosObject theHO;
    UpdateStatus theUS;

    private HosDB theHosDB;
    /** Creates a new instance of SuitPatient */
    public GPatientSuit(HosObject ho,HosDB hdb,UpdateStatus us) {
        theHO = ho;
        theUS = us;
        theHosDB = hdb;
    }
//    public Village intReadVillage(String village_id)throws Exception {
//        theHO.theVillage = theHosDB.theVillageDB.selectByPK(village_id);
//        return theHO.theVillage;
//    }
//    public Home intReadHome(String home_id)throws Exception {
//        theHO.theHome = theHosDB.theHomeDB.selectByPK(home_id);
//        intReadPatient(theHO.theHome.village_id);
//        return theHO.theHome;
//    }
//    public Family intReadFamily(String family_id)throws Exception {
//        theHO.theFamily = theHosDB.theFamilyDB.selectByPK(family_id);
//        intReadPatient(theHO.theFamily.home_id);
//        return theHO.theFamily;
//    }
//    public Patient intReadPatient(String patient_id)throws Exception {
//        theHO.thePatient = theHosDB.thePatientDB.selectByPK(patient_id);
//        intReadPatient(theHO.theVisit.patient_id);
//        return theHO.thePatient;
//    }
//    public Visit intReadVisit(String visit_id)throws Exception {
//        theHO.theVisit = theHosDB.theVisitDB.selectByPK(visit_id);
//        intReadPatient(theHO.theVisit.patient_id);
//        return theHO.theVisit;
//    }
    
    public Patient getPatient(){
        return theHO.thePatient;
    }
    public Vector<X39Persistent> listDiagIcd10(){
        return theHO.vDiagIcd10;
    }

    public MapVisitDx readMapVisitDx(int row) {
        return (MapVisitDx)theHO.vMapVisitDx.get(row);
    }    

    public Visit getVisit() {
        return theHO.theVisit;
    }


    public DiagIcd10 readDiagIcd10(int row) {
        return (DiagIcd10)theHO.vDiagIcd10.get(row);
    }



    public DiagIcd10 initDiagIcd10() {
        return theHO.initDiagIcd10();
    }

    public String getVisitDoctorID() {
        return theHO.getDoctorIDInVisit();
    }

    public Vector listMapVisitDx() {
        return theHO.vMapVisitDx;
    }

    String old_patient_id = "";
    String old_visit_id = "";
    public Vector listPatientPayment()throws Exception
    {    
        if(!theHO.thePatient.getObjectId().equals(old_patient_id))
        {
            theHO.vPatientPayment = theHosDB.thePatientPaymentDB.selectByFamilyPatient(theHO.theFamily,theHO.thePatient);
            old_patient_id = theHO.thePatient.getObjectId();
        }
        return theHO.vPatientPayment;
    }
    public Vector listVisitPaymentCancel()throws Exception
    {
        return theHosDB.thePaymentDB.selectVisitPaymentCancelByVisitID(theHO.theVisit.getObjectId());
    }

    public Vector listBillingPatient() throws Exception 
    {
        return theHosDB.theBillingDB.selectByPatientId(theHO.thePatient.getObjectId());
    }
}
