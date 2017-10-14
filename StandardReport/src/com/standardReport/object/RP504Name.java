/*
 * RP504Name.java
 *
 * Created on 8 กันยายน 2548, 15:25 น.
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
public class RP504Name extends Persist implements StandardObject
{
    public String name_rp504_ICD_code;
    public String name_rp504_ICD_name;
    public String name_rp504_male;
    public String name_rp504_female;
    public String name_rp504_non_spec;
    public String name_rp504_total;
    
    /** Creates a new instance of RP504Name */
    public RP504Name()
    {
    }

    
    public void setInitData()
    {
        name_rp504_ICD_code = "";
        name_rp504_ICD_name = "";
        name_rp504_male = "";
        name_rp504_female = "";
        name_rp504_non_spec = "";
        name_rp504_total = "";
    }

    public static String getStringFieldName504ICD_Code()
    {
        return "name_rp504_ICD_code";
    }
    public static String getStringFieldName504ICD_Name()
    {
        return "name_rp504_ICD_name";
    }
    public static String getStringFieldName504Male()
    {
        return "name_rp504_male";
    }
    public static String getStringFieldName504Female()
    {
        return "name_rp504_female";
    }
    public static String getStringFieldName504NonSpec()
    {
        return "name_rp504_non_spec";
    }
    public static String getStringFieldName504Total()
    {
        return "name_rp504_total";
    }
              
    public Object setInitDataFieldName()
    {
        name_rp504_ICD_code = this.getStringFieldName504ICD_Code();
        name_rp504_ICD_name = this.getStringFieldName504ICD_Name();
        name_rp504_male = this.getStringFieldName504Male();
        name_rp504_female = this.getStringFieldName504Female();
        name_rp504_non_spec = this.getStringFieldName504NonSpec();
        name_rp504_total = this.getStringFieldName504Total();
        return this;
    }
    
}
