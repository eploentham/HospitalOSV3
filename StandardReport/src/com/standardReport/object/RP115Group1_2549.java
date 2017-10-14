/*
 * RP115Group1_2549.java
 *
 * Created on 12 กันยายน 2548, 17:43 น.
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
public class RP115Group1_2549 implements StandardObject{ 
    
    /** Creates a new instance of RP115Group1_2549 */
    public String plan_type;
    
    // OPD
    public String opd_new_patient_incup;
    public String opd_new_patient_outcup;
    public String opd_visit_incup;
    public String opd_visit_outcup;
    
    //IPD
    public String ipd_discharge_incup;
    public String ipd_discharge_outcup;
    public String ipd_day_stay_incup;
    public String ipd_day_stay_outcup;
    
    // PCU
    public String pcu_new_patient_incup;
    public String pcu_new_patient_outcup;
    public String pcu_visit_incup;
    public String pcu_visit_outcup;
    
    // Refer
    public String refer_in_incup;
    public String refer_in_inchangwat;
    public String refer_in_outchangwat;
    public String refer_out_incup;
    public String refer_out_inchangwat;
    public String refer_out_outchangwat;  
    
    public RP115Group1_2549() {
    }
    
    public static String getStringFieldPlanType()
    {
        return "plan_type";
    }
    
    // OPD
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
    
    // IPD
    public static String getStringFieldIPDDischargeIncup()
    {
        return "ipd_discharge_incup";
    }
    
    public static String getStringFieldIPDDischargeOutcup()
    {
        return "ipd_discharge_outcup";
    }
    
    public static String getStringFieldIPDDayStayIncup()
    {
        return "ipd_day_stay_incup";
    }
    
    public static String getStringFieldIPDDayStayOutcup()
    {
        return "ipd_day_stay_outcup";
    }
    
    // PCU
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
    
    // Refer
    public static String getStringFieldReferInIncup()
    {
        return "refer_in_incup";
    }
    
    public static String getStringFieldReferInInChangwat()
    {
        return "refer_in_inchangwat";
    }
    
    public static String getStringFieldReferInOutChangwat()
    {
        return "refer_in_outchangwat";
    }
    
    public static String getStringFieldReferOutIncup()
    {
        return "refer_out_incup";
    }
    
    public static String getStringFieldReferOutInChangwat()
    {
        return "refer_out_inchangwat";
    }
    
    public static String getStringFieldReferOutOutChangwat()
    {
        return "refer_out_outchangwat";
    }

    public void setInitData() {
        plan_type = "";
        
        // OPD
        opd_new_patient_incup = "";
        opd_new_patient_outcup = "";
        opd_visit_incup = ""; 
        opd_visit_outcup = "";
        
        // IPD
        ipd_discharge_incup = "";
        ipd_discharge_outcup = "";
        ipd_day_stay_incup = ""; 
        ipd_day_stay_outcup = "";
        
        // PCU
        pcu_new_patient_incup = "";
        pcu_new_patient_outcup = "";
        pcu_visit_incup = ""; 
        pcu_visit_outcup = "";
        
        // Refer
        refer_in_incup = "";
        refer_in_inchangwat = "";
        refer_in_outchangwat = "";
        refer_out_incup = "";
        refer_out_inchangwat = "";
        String refer_out_outchangwat = "";    
    }

    public Object setInitDataFieldName() {
        
        plan_type = getStringFieldPlanType();
        
        // OPD
        opd_new_patient_incup = getStringFieldOPDPatientIncup();
        opd_new_patient_outcup = getStringFieldOPDPatientOutcup();
        opd_visit_incup = getStringFieldOPDVisitIncup();
        opd_visit_outcup = getStringFieldOPDVisitOutcup();
        
        // IPD
        ipd_discharge_incup = getStringFieldIPDDischargeIncup();
        ipd_discharge_outcup = getStringFieldIPDDischargeOutcup();
        ipd_day_stay_incup = getStringFieldIPDDayStayIncup(); 
        ipd_day_stay_outcup = getStringFieldIPDDayStayOutcup();
        
        // PCU
        pcu_new_patient_incup = getStringFieldPCUPatientIncup();
        pcu_new_patient_outcup = getStringFieldPCUPatientOutcup();
        pcu_visit_incup = getStringFieldPCUVisitIncup();
        pcu_visit_outcup = getStringFieldPCUVisitOutcup();
        
        // Refer
        refer_in_incup = getStringFieldReferInIncup();
        refer_in_inchangwat = getStringFieldReferInInChangwat();
        refer_in_outchangwat = getStringFieldReferInOutChangwat();
        refer_out_incup = getStringFieldReferOutIncup();
        refer_out_inchangwat = getStringFieldReferOutInChangwat();
        String refer_out_outchangwat = getStringFieldReferOutOutChangwat();  
        
        return this;        
    }
    
}
