/*
 * RP115Group4Pre.java
 *
 * Created on 13 กันยายน 2548, 16:07 น.
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
public class RP115Group4Pre implements StandardObject{
    
    /** Creates a new instance of RP115Group4Pre */
    public String plan_type;
    public String clinic_family_planing;
    public String clinic_pap_smear;
    public String clinic_cancer;
    public String clinic_consult;
    
    public RP115Group4Pre() {
    }
    
    public static String getStringFieldPlanType()
    {
        return "plan_type";
    }
    
    public static String getStringFieldClinicFP()
    {
        return "clinic_family_planing";
    }
    
    public static String getStringFieldClinicPapSmear()
    {
        return "clinic_pap_smear";
    }
    
    public static String getStringFieldClinicCancer()
    {
        return "clinic_cancer";
    }
    
    public static String getStringFieldClinicConsult()
    {
        return "clinic_consult";
    }

    public void setInitData() {
        plan_type = "";
        clinic_family_planing = "";
        clinic_pap_smear = "";
        clinic_cancer = "";
        clinic_consult = "";
    }

    public Object setInitDataFieldName() {
        plan_type = getStringFieldPlanType();
        clinic_family_planing = getStringFieldClinicFP();
        clinic_pap_smear = getStringFieldClinicPapSmear();
        clinic_cancer = getStringFieldClinicCancer();
        clinic_consult = getStringFieldClinicConsult();
        return this;
    }
    
}
