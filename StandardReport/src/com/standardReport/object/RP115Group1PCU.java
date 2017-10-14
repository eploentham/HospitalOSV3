/*
 * RP115Group1PCU.java
 *
 * Created on 12 กันยายน 2548, 16:28 น.
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
public class RP115Group1PCU implements StandardObject{
    
    /**
     * Creates a new instance of RP115Group1PCU 
     */
    public String pcu_plan_type;
    public String pcu_new_patient_incup;
    public String pcu_new_patient_outcup;
    public String pcu_visit_incup;
    public String pcu_visit_outcup;
    
    public RP115Group1PCU() {
    }
    
    public static String getStringFieldPCUPlanType()
    {
        return "pcu_plan_type";
    }
    
    public static String getStringFieldPCUPatientIncup()
    {
        return "pcu_new_patient_incup";
    }
    
    public static String getStringFieldPCUPatientOutcup()
    {
        return "pcu_new_patient_outcup";
    }
    
    public static String getStringFieldPCUVisitIncup()
    {
        return "pcu_visit_incup";
    }
    
    public static String getStringFieldPCUVisitOutcup()
    {
        return "pcu_visit_outcup";
    }

    public void setInitData() {
        pcu_plan_type = "";
        pcu_new_patient_incup = "";
        pcu_new_patient_outcup = "";
        pcu_visit_incup = ""; 
        pcu_visit_outcup = "";
    }

    public Object setInitDataFieldName() {      
        
        pcu_plan_type = getStringFieldPCUPlanType();
        pcu_new_patient_incup = getStringFieldPCUPatientIncup();
        pcu_new_patient_outcup = getStringFieldPCUPatientOutcup();
        pcu_visit_incup = getStringFieldPCUVisitIncup();
        pcu_visit_outcup = getStringFieldPCUVisitOutcup();
        
        return this; 
    }
    
}
