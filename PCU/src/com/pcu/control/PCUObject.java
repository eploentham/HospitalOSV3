/*
 * PCUObject.java
 *
 * Created on 19 กรกฎาคม 2548, 14:58 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.pcu.control;
import java.util.*;
import com.hosv3.object.*;
import com.hospital_os.object.*;
import com.pcu.object.*;
import com.pcu.utility.DateUtil;

/**
 *
 * @author tong(Padungrat)
 */
public class PCUObject {
    
    public HosObject theHO;
    public Vector vPPCare;
    public Vector vPregnancy;
    public Vector vEfficiency;
    public Vector vCheckHealthYear;
    public Vector vVisitHome;
    public Vector vCounsel;
    public Vector vDental;
    public PP thePP;
    public Vector vBornMch;
    public Vector vAfterMchMother;         
    public Vector vEpiDetail;
    public Vector vNutrition;
    public Vector vEpiOutSite;
    public Vector vFamilyPlaning;
    public Vector vEpi;
    public Vector vGrowPcu;
    public Vector vGrowHistory;

    
    
    public Visit getVisit(){
        if(theHO==null)
            return null;
        return theHO.theVisit;
    }
    public Patient getPatient(){        
        if(theHO==null)
            return null;
        return theHO.thePatient;
    }
    public String getCurrentDateTime(){
        return theHO.date_time;
    }
    public Employee getEmployee(){
        if(theHO==null)
            return null;
        return theHO.theEmployee;
    }
    public Site getSite(){
        if(theHO==null)
            return null;
        return theHO.theSite;
    }
    public VitalSign getVitalSign(){
        if(theHO==null)
            return null;
        return theHO.theVitalSign;
    }
    public ServicePoint getServicePoint(){
        if(theHO==null)
            return null;
        return theHO.theServicePoint;
    }
    public Vector getDrugAllergyV(){
        if(theHO==null)
            return null;
        return theHO.vDrugAllergy;
    }
    public Vector getVisitPaymentV(){
        if(theHO==null)
            return null;
        return theHO.vVisitPayment;
    }
    public Vector getTransferV(){
        if(theHO==null)
            return null;
        return theHO.vTransfer;
    }
    public Family getFamily(){
        if(theHO==null)
            return null;
        return theHO.theFamily;
    }
    public Family getFamilyFather(){
        return theHO.theFamilyFather;
    }
    public Family getFamilyMother(){
        return theHO.theFamilyMother;
    }
    public Family getFamilyCouple(){
        return theHO.theFamilyCouple;
    }
    public Home getHome(){
        if(theHO==null)
            return null;
//        System.out.println("theHO.theHome==null");
//        System.out.println(theHO.theHome==null);
        return theHO.theHome;
    }
    public Village getVillage(){
        if(theHO==null)
            return null;
        //System.out.println("theHO.theVillage==null");
        //System.out.println(theHO.theVillage==null);
        return theHO.theVillage;
    }
    
    /** Creates a new instance of PCUObject */
    public PCUObject(HosObject ho) {
        setHosObject(ho);
    }
    public PCUObject() {
    }
    public void setHosObject(HosObject ho){
        theHO = ho;
    }
    /**
     *@deprecated henbe unused
     */
    public String getDateTime() {
       return this.getCurrentDateTime();
    }

    public Nutrition initNutrition() {
        Nutrition nutrition = new Nutrition();
        int age_month = DateUtil.calculateAgeMonth(getFamily().patient_birthday,getCurrentDateTime());
        nutrition.answer_id = "1";
        nutrition.nutrition_level_id = "N";
//        nutrition.nutrition53 = "1";
        nutrition.nutrition_age = String.valueOf(age_month);
        nutrition.modify_date_time = getCurrentDateTime();
        Vector vVital = theHO.vVitalSign;
        if(vVital != null && !vVital.isEmpty())
        {
            VitalSign current_vitalSign = (VitalSign) vVital.get(vVital.size()-1);
            nutrition.nutrition_high = current_vitalSign.height;
            nutrition.nutrition_weight = current_vitalSign.weight;
            nutrition.nutrition_bmi = current_vitalSign.bmi;
        }
        return nutrition;
    }
}
