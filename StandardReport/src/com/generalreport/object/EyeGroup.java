/*
 * EyeGroup.java
 *
 * Created on 17 ตุลาคม 2548, 11:36 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.generalreport.object;
import com.generalreport.utility.StandardObject;
/**
 *
 * @author tong(Padungrat)
 */
public class EyeGroup extends Persist implements StandardObject{
    
    //public String r_eye_group_id;
    /**ลำดับของโรค*/
    public String eye_group_number;
    /**ชื่อภาษาไทย*/
    public String description_th;
    /**ชื่อภาษาอังกฤษ*/
    public String description_en;
    /**ใช้งานหรือไม่*/
    public String active;
    public EyeGroup() {
        idTable ="";
        tableName= "r_eye_group"; 
    }

    
    public void setInitData() {
        eye_group_number = "";
        description_th = "";
        description_en = "";
        active = "1";
    }

    public static String getStringTable()
    {
        return "r_eye_group";  
    }
    
    public static String getStringFieldPKTable() 
    {
        return "r_eye_group_id";    
    }
    public static String getStringFieldNumber() 
    {
        return "eye_group_number";    
    }
    public static String getStringFieldDescriptionThai() 
    {
        return "eye_group_description_th";    
    }
    public static String getStringFieldDescriptionEng() 
    {
        return "eye_group_description_en";    
    }
    public static String getStringFieldActive() 
    {
        return "eye_group_active";    
    }
    public Object setInitDataFieldName() {
        this.pk_table = getStringFieldPKTable();
        eye_group_number = getStringFieldNumber();
        description_th = getStringFieldDescriptionThai();
        description_en = getStringFieldDescriptionEng();
        active = getStringFieldActive();
        return this;
    }
    
}
