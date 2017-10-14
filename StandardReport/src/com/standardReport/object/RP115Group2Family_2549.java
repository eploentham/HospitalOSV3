/*
 * RP115Group2Family_2549.java
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
 * @author pu
 */
public class RP115Group2Family_2549 implements StandardObject{ 
    
    /** Creates a new instance of RP115Group2Family_2549 */
    public String plan_type;
    public String childless_female;
    public String childless_male;
    public String childless_drug;
    public String childless_other;    
    
    public RP115Group2Family_2549() {
    }
    
    public static String getStringFieldPlanType()
    {
        return "plan_type";
    }
    
    public static String getStringFieldChildlessFemale()
    {
        return "childless_female";
    }
    
    public static String getStringFieldChildlessMale()
    {
        return "childless_male";
    }
    
    public static String getStringFieldChildlessDrug()
    {
        return "childless_drug";
    }
    
    public static String getStringFieldChildlessOther()
    {
        return "childless_other";
    }    
    
    public void setInitData() {
        plan_type = "";
        childless_female = "";
        childless_male = "";
        childless_drug = "";
        childless_other = "";
    }

    public Object setInitDataFieldName() {
        plan_type = getStringFieldPlanType();
        childless_female = getStringFieldChildlessFemale();
        childless_male = getStringFieldChildlessMale();
        childless_drug = getStringFieldChildlessDrug();
        childless_other = getStringFieldChildlessOther();

        return this;
    }
    
}
