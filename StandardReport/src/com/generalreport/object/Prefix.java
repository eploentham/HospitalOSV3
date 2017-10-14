/*
 * Prefix.java
 *
 * Created on 25 ตุลาคม 2548, 15:30 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.object;
import com.generalreport.utility.StandardObject;
/**
 *
 * @author americus
 */
public class Prefix extends Persist implements StandardObject
{
    public String prefix_id;
    public String description;
    public String sex;
    public String tlock;
   
    /** Creates a new instance of Prefix */
    public Prefix()
    {
        idTable ="215";
        tableName= "f_patient_prefix";
    }

    public void setInitData()
    {
        prefix_id = "";
        description = "";
        sex = "";
        tlock = "";
    }
    
    public static String getStringTable()
    {
        return "f_patient_prefix";
    }
    
    public static String getStringFieldPKTable()
    {
        return "f_patient_prefix_id";
    }
    
    public static String getStringFieldDescription()
    {
        return "patient_prefix_description";
    }
    
    public static String getStringFieldSex()
    {
        return "f_sex_id";
    }
    
    public static String getStringFieldTlock()
    {
        return "f_tlock_id";
    }
    
    
    public Object setInitDataFieldName()
    {
        this.pk_table = this.getStringFieldPKTable();
        description = this.getStringFieldDescription();
        sex = this.getStringFieldSex();
        tlock = this.getStringFieldTlock();
        return this;
    }
    
}
