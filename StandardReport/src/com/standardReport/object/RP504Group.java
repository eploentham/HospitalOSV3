/*
 * RP504Group.java
 *
 * Created on 7 กันยายน 2548, 10:56 น.
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
public class RP504Group extends Persist implements StandardObject
{
    public String group_rp504_number;
    public String group_rp504_description_th;
    public String group_rp504_description_en;
    public String group_rp504_male;
    public String group_rp504_female;
    public String group_rp504_non_spec;
    public String group_rp504_total;
    
    /** Creates a new instance of RP504Group */
    public RP504Group()
    {
    
    }

    public void setInitData()
    {
        group_rp504_number = "";
        group_rp504_description_th = "";
        group_rp504_description_en = "";
        group_rp504_male = ""; 
        group_rp504_female = "";
        group_rp504_non_spec = "";
        group_rp504_total = "";
    }   

    public static String getStringFieldGroup504Number()
    {
        return "group_rp504_number";
    }
    public static String getStringFieldGroup504DescTh()
    {
        return "group_rp504_description_th";
    }
    public static String getStringFieldGroup504DescEn()
    {
        return "group_rp504_description_en";
    }
    public static String getStringFieldGroup504Male()
    {
        return "group_rp504_male";
    }
    public static String getStringFieldGroup504Female()
    {
        return "group_rp504_female";
    }
    public static String getStringFieldGroup504NonSpec()
    {
        return "group_rp504_non_spec";
    }
    public static String getStringFieldGroup504Total()
    {
        return "group_rp504_total";
    }

    
    public Object setInitDataFieldName()
    {
        group_rp504_number = this.getStringFieldGroup504Number();
        group_rp504_description_th = this.getStringFieldGroup504DescTh();
        group_rp504_description_en = this.getStringFieldGroup504DescEn();
        group_rp504_male = this.getStringFieldGroup504Male(); 
        group_rp504_female = this.getStringFieldGroup504Female();
        group_rp504_non_spec = this.getStringFieldGroup504NonSpec();
        group_rp504_total = this.getStringFieldGroup504Total();
        return this;
    }    
}
