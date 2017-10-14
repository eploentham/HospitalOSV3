/*
 * RP115Group1Refer.java
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
public class RP115Group1Refer implements StandardObject{
    
    /**
     * Creates a new instance of RP115Group1Refer 
     */
    public String refer_plan_type;
    public String refer_in_incup;
    public String refer_in_inchangwat;
    public String refer_in_outchangwat;
    public String refer_out_incup;
    public String refer_out_inchangwat;
    public String refer_out_outchangwat;    
    
    public RP115Group1Refer() {
    }

    public static String getStringFieldReferPlanType()
    {
        return "refer_plan_type";
    }
    
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
        refer_plan_type = "";
        refer_in_incup = "";
        refer_in_inchangwat = "";
        refer_in_outchangwat = "";
        refer_out_incup = "";
        refer_out_inchangwat = "";
        String refer_out_outchangwat = "";    
    }

    public Object setInitDataFieldName() {
        
        refer_plan_type = getStringFieldReferPlanType();
        refer_in_incup = getStringFieldReferInIncup();
        refer_in_inchangwat = getStringFieldReferInInChangwat();
        refer_in_outchangwat = getStringFieldReferInOutChangwat();
        refer_out_incup = getStringFieldReferOutIncup();
        refer_out_inchangwat = getStringFieldReferOutInChangwat();
        String refer_out_outchangwat = getStringFieldReferOutOutChangwat();    
        
        return this;
    }
    
}
