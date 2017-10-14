/*
 * RP115Group1OPD.java
 *
 * Created on 12 กันยายน 2548, 10:21 น.
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
public class RP115Group1OPD implements StandardObject{
    
    /** Creates a new instance of RP115Group1OPD */
    public String opd_plan_type;
    public String opd_new_patient_incup;
    public String opd_new_patient_outcup;
    public String opd_visit_incup;
    public String opd_visit_outcup;
    
    public RP115Group1OPD() {
    }

    public void setInitData()
    {
        opd_plan_type = "";
        opd_new_patient_incup = "";
        opd_new_patient_outcup = "";
        opd_visit_incup = ""; 
        opd_visit_outcup = "";
    }       
    
    public static String getStringFieldOPDPlanType()
    {
        return "opd_plan_type";
    }
    
    public static String getStringFieldOPDPatientIncup()
    {
        return "opd_new_patient_incup";
    }
    
    public static String getStringFieldOPDPatientOutcup()
    {
        return "opd_new_patient_outcup";
    }
    
    public static String getStringFieldOPDVisitIncup()
    {
        return "opd_visit_incup";
    }
    
    public static String getStringFieldOPDVisitOutcup()
    {
        return "opd_visit_outcup";
    }

    public Object setInitDataFieldName() {
        
        opd_plan_type = getStringFieldOPDPlanType();
        opd_new_patient_incup = getStringFieldOPDPatientIncup();
        opd_new_patient_outcup = getStringFieldOPDPatientOutcup();
        opd_visit_incup = getStringFieldOPDVisitIncup();
        opd_visit_outcup = getStringFieldOPDVisitOutcup();
        
        return this;
    }
    
}
