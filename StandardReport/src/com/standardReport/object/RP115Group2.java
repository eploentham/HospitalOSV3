/*
 * RP115Group2.java
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
 * @author nu_ojika
 */
public class RP115Group2 implements StandardObject{
    
    /** Creates a new instance of RP115Group2 */
    public String plan_type;
    
    // ส่วนที่1
    public String chronic_patient;
    public String chronic_visit;
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
    public String protect_dental;
    public String treatment_dental;
    
    public RP115Group2() {
    }
    
    public static String getStringFieldPlanType()
    {
        return "plan_type";
    }
    
    public static String getStringFieldChronicPatient()
    {
        return "chronic_patient";
    }
    
    public static String getStringFieldChronicVisit()
    {
        return "chronic_visit";
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
    
    public static String getStringFieldProtectDental()
    {
        return "protect_dental";
    }
    
    public static String getStringFieldTreatmentDental()
    {
        return "treatment_dental";
    }
    
    public void setInitData() {
        plan_type = "";
        
        // ส่วนที่1
        chronic_patient = "";
        chronic_visit = "";
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
        protect_dental = "";
        treatment_dental = "";
    
    }
    
    public Object setInitDataFieldName() {
        
        plan_type = getStringFieldPlanType();
        
        // ส่วนที่1
        chronic_patient = getStringFieldChronicPatient();
        chronic_visit = getStringFieldChronicVisit();
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
        protect_dental = getStringFieldProtectDental();
        treatment_dental = getStringFieldTreatmentDental();
        
        return this;
    }
}
