/*
 * RP115Group4.java
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
public class RP115Group4 implements StandardObject{
    
    /** Creates a new instance of RP115Group4 */
    public String plan_type;
    
    // ส่วนที่1
    public String clinic_family_planing;
    public String clinic_pap_smear;
    public String clinic_cancer;
    public String clinic_consult;
    
    // ส่วนที่2
    public String visit_home_count_home;
    public String visit_home_patient;
    /*public String visit_school_times;
    public String visit_school_patient;
    public String visit_other_times;
    public String visit_other_patient;*/
    
    public RP115Group4() {
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
    
    public static String getStringFieldVisitHomeCountHome()
    {
        return "visit_home_count_home";
    }
    
    public static String getStringFieldVisitHomePatient()
    {
        return "visit_home_patient";
    }
    /*
    public static String getStringFieldVisitSchoolTimes()
    {
        return "visit_school_times";
    }
    
    public static String getStringFieldVisitSchoolPatient()
    {
        return "visit_school_patient";
    }
    
    public static String getStringFieldVisitOtherTimes()
    {
        return "visit_other_times";
    }
    
    public static String getStringFieldVisitOtherPatient()
    {
        return "visit_other_patient";
    }
    */
    public void setInitData() {
        plan_type = "";
        
        // ส่วนที่1
        clinic_family_planing = "";
        clinic_pap_smear = "";
        clinic_cancer = "";
        clinic_consult = "";

        // ส่วนที่2
        visit_home_count_home = "";
        visit_home_patient = "";
       /* visit_school_times = "";
        visit_school_patient = "";
        visit_other_times = "";
        visit_other_patient = "";*/
    
    }
    
    public Object setInitDataFieldName() {
        
        plan_type = getStringFieldPlanType();
        
        // ส่วนที่1
        clinic_family_planing = getStringFieldClinicFP();
        clinic_pap_smear = getStringFieldClinicPapSmear();
        clinic_cancer = getStringFieldClinicCancer();
        clinic_consult = getStringFieldClinicConsult();

        // ส่วนที่2
        visit_home_count_home = getStringFieldVisitHomeCountHome();
        visit_home_patient = getStringFieldVisitHomePatient();
      /*  visit_school_times = getStringFieldVisitSchoolTimes();
        visit_school_patient = getStringFieldVisitSchoolPatient();
        visit_other_times = getStringFieldVisitOtherTimes();
        visit_other_patient = getStringFieldVisitOtherPatient();*/
        
        return this;
    }
}
