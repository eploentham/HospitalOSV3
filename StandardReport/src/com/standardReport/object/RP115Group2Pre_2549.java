/*
 * RP115Group2Pre.java
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
public class RP115Group2Pre_2549 implements StandardObject{
    
    /** Creates a new instance of RP115Group2Pre */
    public String plan_type;
    public String chronic_patient;
    public String chronic_visit;
    public String abort;
    public String birth_and_death;
    public String baby_death;
    public String mother_death;
    public String ipd_death;
    public String opd_death;
    
    public RP115Group2Pre_2549() {
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
    
     public void setInitData() {
        plan_type = "";
        chronic_patient = "";
        chronic_visit = "";
        abort = "";
        birth_and_death = "";
        baby_death = "";
        mother_death = "";
        ipd_death = "";
        opd_death = "";
     }
     
     public Object setInitDataFieldName() {
        
        plan_type = getStringFieldPlanType();
        chronic_patient = getStringFieldChronicPatient();
        chronic_visit = getStringFieldChronicVisit();
        abort = getStringFieldAbort();
        birth_and_death = getStringFieldBirthAndDeath();
        baby_death = getStringFieldBabyDeath();
        mother_death = getStringFieldMotherDeath();
        ipd_death = getStringFieldIpdDeath();
        opd_death = getStringFieldOpdDeath();
        
        return this;
     }
}
