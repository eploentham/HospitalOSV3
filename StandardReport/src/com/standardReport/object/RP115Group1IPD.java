/*
 * RP115Group1IPD.java
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
public class RP115Group1IPD implements StandardObject{
    
    /** Creates a new instance of RP115Group1IPD */
    public String ipd_plan_type;
    public String ipd_discharge_incup;
    public String ipd_discharge_outcup;
    public String ipd_day_stay_incup;
    public String ipd_day_stay_outcup;
    
    public RP115Group1IPD() {
    }
    
    public static String getStringFieldIPDPlanType()
    {
        return "ipd_plan_type";
    }
    
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

    public void setInitData() {
        ipd_plan_type = "";
        ipd_discharge_incup = "";
        ipd_discharge_outcup = "";
        ipd_day_stay_incup = ""; 
        ipd_day_stay_outcup = "";
    }

    public Object setInitDataFieldName() {
        
        ipd_plan_type = getStringFieldIPDPlanType();
        ipd_discharge_incup = getStringFieldIPDDischargeIncup();
        ipd_discharge_outcup = getStringFieldIPDDischargeOutcup();
        ipd_day_stay_incup = getStringFieldIPDDayStayIncup(); 
        ipd_day_stay_outcup = getStringFieldIPDDayStayOutcup();
        
        return this;
    }
    
}
