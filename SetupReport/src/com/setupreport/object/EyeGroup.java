/*
 * EyeGroup.java
 *
 * Created on 26 ตุลาคม 2548, 14:23 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.object;
import com.setupreport.usecase.connection.StandardObject;
/**
 *
 * @author americus
 */
public class EyeGroup  extends Persist implements StandardObject
{
    /**รหัสของกลุ่มโรคตา*/
    public String group_id;
    /**code ของ กลุ่มโรคตา*/
    public String number;
    /**ชื่อภาษาไทย*/
    public String desc_th;
    /**ชื่อภาษาไทย*/
    public String desc_eng;
    /**ใช้งานหรือไม่ใช้ (1 คือ ใช้, 0 คือไม่ใช้)*/
    public String active;
    
    /** Creates a new instance of EyeGroup */
    public EyeGroup()
    {
        idTable ="813";        
        tableName= "r_eye_group";
    }

    public void setInitData()
    {
        group_id = "";
        number = "";
        desc_th = "";
        desc_eng = "";
        active = "";
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
    public static String getStringFieldDescThai()
    {
        return "eye_group_description_th";
    }
    public static String getStringFieldDescEng()
    {
        return "eye_group_description_en";
    }
    public static String getStringFieldActive()
    {
        return "eye_group_active";
    }
    public Object setInitDataFieldName()
    {
        this.pk_table =  this.getStringFieldPKTable();
        number = this.getStringFieldNumber();
        desc_th =this.getStringFieldDescThai();
        desc_eng = this.getStringFieldDescEng();
        active = this.getStringFieldActive();
        return this;
    }
    
}
