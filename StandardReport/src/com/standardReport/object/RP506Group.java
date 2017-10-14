/*
 * RP506Group.java
 *
 * Created on 8 กันยายน 2548, 10:07 น.
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
public class RP506Group extends Persist implements StandardObject
{
    public String group_rp506_number;
    public String group_rp506_description_th;
    public String group_rp506_description_en;
    public String group_rp506_male;
    public String group_rp506_female;
    public String group_rp506_non_spec;
    public String group_rp506_total;
    
    /** Creates a new instance of RP506Group */
    public RP506Group()
    {
    }
    
    public void setInitData()
    {
        group_rp506_number = "";
        group_rp506_description_th = "";
        group_rp506_description_en = "";
        group_rp506_male = "";
        group_rp506_female = "";
        group_rp506_non_spec = "";
        group_rp506_total = "";
    }
    
    public static String getStringFieldGroup506Number()
    {
        return "group_rp506_number";
    }
    public static String getStringFieldGroup506DescTh()
    {
        return "group_rp506_description_th";
    }
    public static String getStringFieldGroup506DescEn()
    {
        return "group_rp506_description_en";
    }
    public static String getStringFieldGroup506Male()
    {
        return "group_rp506_male";
    }
    public static String getStringFieldGroup506Female()
    {
        return "group_rp506_female";
    }
    public static String getStringFieldGroup506NonSpec()
    {
        return "group_rp506_non_spec";
    }
    public static String getStringFieldGroup506Total()
    {
        return "group_rp506_total";
    }
    
     public Object setInitDataFieldName()
     {
         group_rp506_number = this.getStringFieldGroup506Number();
         group_rp506_description_th = this.getStringFieldGroup506DescTh();
         group_rp506_description_en = this.getStringFieldGroup506DescEn();
         group_rp506_male = this.getStringFieldGroup506Male();
         group_rp506_female = this.getStringFieldGroup506Female();
         group_rp506_non_spec = this.getStringFieldGroup506NonSpec();
         group_rp506_total = this.getStringFieldGroup506Total();
         return this;
     }
}
