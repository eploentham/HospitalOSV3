/*
 * RP505Group.java
 *
 * Created on 7 กันยายน 2548, 11:00 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.standardReport.object;

import com.standardReport.subject.StandardObject;

/**
 *
 * @author americus
 */
public class RP505Group extends Persist implements StandardObject
{
    
    /** Creates a new instance of RP505Group */
    public String group_rp505_number;
    public String group_rp505_description_th;
    public String group_rp505_description_en;
    public String group_rp505_male;
    public String group_rp505_female;
    public String group_rp505_non_spec;
    public String group_rp505_total;
    
    /** Creates a new instance of RP505Group */
    public RP505Group()
    {
    
    }

    public void setInitData()
    {
        group_rp505_number = "";
        group_rp505_description_th = "";
        group_rp505_description_en = "";
        group_rp505_male = ""; 
        group_rp505_female = "";
        group_rp505_non_spec = "";
        group_rp505_total = "";
    }   

    public static String getStringFieldGroup505Number()
    {
        return "group_rp505_number";
    }
    public static String getStringFieldGroup505DescTh()
    {
        return "group_rp505_description_th";
    }
    public static String getStringFieldGroup505DescEn()
    {
        return "group_rp505_description_en";
    }
    public static String getStringFieldGroup505Male()
    {
        return "group_rp505_male";
    }
    public static String getStringFieldGroup505Female()
    {
        return "group_rp505_female";
    }
    public static String getStringFieldGroup505NonSpec()
    {
        return "group_rp505_non_spec";
    }
    public static String getStringFieldGroup505Total()
    {
        return "group_rp505_total";
    }

    
    public Object setInitDataFieldName()
    {
        group_rp505_number = this.getStringFieldGroup505Number();
        group_rp505_description_th = this.getStringFieldGroup505DescTh();
        group_rp505_description_en = this.getStringFieldGroup505DescEn();
        group_rp505_male = this.getStringFieldGroup505Male(); 
        group_rp505_female = this.getStringFieldGroup505Female();
        group_rp505_non_spec = this.getStringFieldGroup505NonSpec();
        group_rp505_total = this.getStringFieldGroup505Total();
        return this;
    }  
    
}
