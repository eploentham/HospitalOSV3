/*
 * RP115Group4Post.java
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
public class RP115Group4Post implements StandardObject{
    
    /** Creates a new instance of RP115Group4Post */
    public String plan_type;
    public String visit_home_count_home;
    public String visit_home_patient;
   /* public String visit_school_times;
    public String visit_school_patient;
    public String visit_other_times;
    public String visit_other_patient;*/
    
    public RP115Group4Post() {
    }
    
    public static String getStringFieldPlanType()
    {
        return "plan_type";
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
        visit_home_count_home = "";
        visit_home_patient = "";
    /*    visit_school_times = "";
        visit_school_patient = "";
        visit_other_times = "";
        visit_other_patient = "";*/
    }

    public Object setInitDataFieldName() {
        plan_type = getStringFieldPlanType();
        visit_home_count_home = getStringFieldVisitHomeCountHome();
        visit_home_patient = getStringFieldVisitHomePatient();
       /* visit_school_times = getStringFieldVisitSchoolTimes();
        visit_school_patient = getStringFieldVisitSchoolPatient();
        visit_other_times = getStringFieldVisitOtherTimes();
        visit_other_patient = getStringFieldVisitOtherPatient();*/
        
        return this;
    }
    
}
