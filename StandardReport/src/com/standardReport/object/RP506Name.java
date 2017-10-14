/*
 * RP506Name.java
 *
 * Created on 8 กันยายน 2548, 15:26 น.
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
public class RP506Name extends Persist implements StandardObject
{
    public String name_rp506_ICD_code;
    public String name_rp506_ICD_name;
    public String name_rp506_male;
    public String name_rp506_female;
    public String name_rp506_non_spec;
    public String name_rp506_total;
    /** Creates a new instance of RP506Name */
    public RP506Name()
    {
    }

    public void setInitData()
    {
        name_rp506_ICD_code = "";
        name_rp506_ICD_name = "";
        name_rp506_male = "";
        name_rp506_female = "";
        name_rp506_non_spec = "";
        name_rp506_total = "";
    }

    public static String getStringFieldName506ICD_Code()
    {
        return "name_rp506_ICD_code";
    }
    public static String getStringFieldName506ICD_Name()
    {
        return "name_rp506_ICD_name";
    }
    public static String getStringFieldName506Male()
    {
        return "name_rp506_male";
    }
    public static String getStringFieldName506Female()
    {
        return "name_rp506_female";
    }
    public static String getStringFieldName506NonSpec()
    {
        return "name_rp506_non_spec";
    }
    public static String getStringFieldName506Total()
    {
        return "name_rp506_total";
    }
    public Object setInitDataFieldName()
    {
        name_rp506_ICD_code = this.getStringFieldName506ICD_Code();
        name_rp506_ICD_name = this.getStringFieldName506ICD_Name();
        name_rp506_male = this.getStringFieldName506Male();
        name_rp506_female = this.getStringFieldName506Female();
        name_rp506_non_spec = this.getStringFieldName506NonSpec();
        name_rp506_total = this.getStringFieldName506Total();
        return this;
    }
    
}
