/*
 * RP115Group2Post.java
 *
 * Created on 13 กันยายน 2548, 15:38 น.
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
public class RP115Group2Post implements StandardObject{ 
    
    /** Creates a new instance of RP115Group2Post */
    public String plan_type;
    public String before_mch_patient;
    public String before_mch_visit;
    public String after_mch_patient;
    public String after_mch_visit;    
    public String birth_usual_infant;
    public String birth_un_usual_infant;
    public String protect_dental;
    public String treatment_dental;
    
    public RP115Group2Post() {
    }
    
    public static String getStringFieldPlanType()
    {
        return "plan_type";
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
