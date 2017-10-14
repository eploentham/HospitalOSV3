/*
 * RP115Group2_2549.java
 *
 * Created on 13 กันยายน 2548, 10:47 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.object;

import com.standardReport.subject.StandardObject;
/**
 *
 * @author pu
 */
public class RP115Group2_2549 implements StandardObject{
    
    /** Creates a new instance of RP115Group2_2549 */
    public String plan_type;
    
    // ส่วนที่1
    public String abort;
    public String birth_and_death;
    public String baby_death;
    public String mother_death;
    public String ipd_death;
    public String opd_death;
    
    // ส่วนที่2
    public String before_mch_patient;
    public String before_mch_visit;
    public String after_mch_patient;
    public String after_mch_visit;    
    public String birth_usual_infant;
    public String birth_un_usual_infant;
    public String treatment_dental;
    
    // วางแผนครอบครัว
    public String childless_female;
    public String childless_male;
    public String childless_drug;
    public String childless_other;    
    
    
    public RP115Group2_2549() {
    }
    
    public static String getStringFieldPlanType()
    {
        return "plan_type";
    }
    
    public static String getStringFieldAbort()
    {
        return "abort";
    }
    
    public static String getStringFieldBirthAndDeath()
    {
        return "birth_and_death";
    }
    
    public static String getStringFieldBabyDeath()
    {
        return "baby_death";
    }
    
    public static String getStringFieldMotherDeath()
    {
        return "mother_death";
    }
    
    public static String getStringFieldIpdDeath()
    {
        return "ipd_death";
    }
    
    public static String getStringFieldOpdDeath()
    {
        return "opd_death";
    }
    
    public static String getStringFieldBeforeMchPatient()
    {
        return "before_mch_patient";
    }
    
    public static String getStringFieldBeforeMchVisit()
    {
        return "before_mch_visit";
    }
    
    public static String getStringFieldAfterMchPatient()
    {
        return "after_mch_patient";
    }
    
    public static String getStringFieldAfterMchVisit()
    {
        return "after_mch_visit";
    }
    
    public static String getStringFieldBirthUsualInfant()
    {
        return "birth_usual_infant";
    }
    
    public static String getStringFieldBirthUnUsualInfant()
    {
        return "birth_un_usual_infant";
    }

    public static String getStringFieldTreatmentDental()
    {
        return "treatment_dental";
    }
    
     public static String getStringFieldChildlessFemale()
    {
        return "childless_female";
    }
    
    public static String getStringFieldChildlessMale()
    {
        return "childless_male";
    }
    
    public static String getStringFieldChildlessDrug()
    {
        return "childless_drug";
    }
    
    public static String getStringFieldChildlessOther()
    {
        return "childless_other";
    }    
    
    public void setInitData() {
        plan_type = "";
        
        // ส่วนที่1
        abort = "";
        birth_and_death = "";
        baby_death = "";
        mother_death = "";
        ipd_death = "";
        opd_death = "";

        // ส่วนที่2
        before_mch_patient = "";
        before_mch_visit = "";
        after_mch_patient = "";
        after_mch_visit = "";
        birth_usual_infant = "";
        birth_un_usual_infant = "";
        treatment_dental = "";
        
        // วางแผนครอบครัว
        childless_female = "";
        childless_male = "";
        childless_drug = "";
        childless_other = "";
        
    }
    
    public Object setInitDataFieldName() {
        
        plan_type = getStringFieldPlanType();
        
        // ส่วนที่1
        abort = getStringFieldAbort();
        birth_and_death = getStringFieldBirthAndDeath();
        baby_death = getStringFieldBabyDeath();
        mother_death = getStringFieldMotherDeath();
        ipd_death = getStringFieldIpdDeath();
        opd_death = getStringFieldOpdDeath();

        // ส่วนที่2
        before_mch_patient = getStringFieldBeforeMchPatient();
        before_mch_visit = getStringFieldBeforeMchVisit();
        after_mch_patient = getStringFieldAfterMchPatient();
        after_mch_visit = getStringFieldAfterMchVisit();
        birth_usual_infant = getStringFieldBirthUsualInfant();
        birth_un_usual_infant = getStringFieldBirthUnUsualInfant();
        treatment_dental = getStringFieldTreatmentDental();
        
        // วางแผนครอบครัว
        childless_female = getStringFieldChildlessFemale();
        childless_male = getStringFieldChildlessMale();
        childless_drug = getStringFieldChildlessDrug();
        childless_other = getStringFieldChildlessOther();
        
        return this;
    }
}
