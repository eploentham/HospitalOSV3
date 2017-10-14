/*
 * RP505Name.java
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
public class RP505Name extends Persist implements StandardObject
{
    public String name_rp505_ICD_code;
    public String name_rp505_ICD_name;
    public String name_rp505_male;
    public String name_rp505_female;
    public String name_rp505_non_spec;
    public String name_rp505_total;
    
    /** Creates a new instance of RP505Name */
    public RP505Name()
    {
    }

    
    public void setInitData()
    {
        name_rp505_ICD_code = "";
        name_rp505_ICD_name = "";
        name_rp505_male = "";
        name_rp505_female = "";
        name_rp505_non_spec = "";
        name_rp505_total = "";
    }
    
    public static String getStringFieldName505ICD_Code()
    {
        return "name_rp505_ICD_code";
    }
    public static String getStringFieldName505ICD_Name()
    {
        return "name_rp505_ICD_name";
    }
    public static String getStringFieldName505Male()
    {
        return "name_rp505_male";
    }
    public static String getStringFieldName505Female()
    {
        return "name_rp505_female";
    }
    public static String getStringFieldName505NonSpec()
    {
        return "name_rp505_non_spec";
    }
    public static String getStringFieldName505Total()
    {
        return "name_rp505_total";
    }

    public Object setInitDataFieldName()
    {
        name_rp505_ICD_code = this.getStringFieldName505ICD_Code();
        name_rp505_ICD_name = this.getStringFieldName505ICD_Name();
        name_rp505_male = this.getStringFieldName505Male();
        name_rp505_female = this.getStringFieldName505Female();
        name_rp505_non_spec = this.getStringFieldName505NonSpec();
        name_rp505_total = this.getStringFieldName505Total();
        return this;
    }
    
}
