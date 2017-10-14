/*
 * EyeDiseaseCode.java
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
public class EyeDiseaseCode extends Persist implements StandardObject
{
    /**รหัสช่วงของรหัสโรคของกลุ่มโรคตา*/
    public String disease_code_id;
    /**รหัสกลุ่มโรคตา*/
    public String group_id;
    /**รหัสเริ่ม*/
    public String code_begin;
    /**รหัสสิ้นสุด*/
    public String code_end;
    
    /** Creates a new instance of EyeDiseaseCode */
    public EyeDiseaseCode()
    {
        idTable ="814";        
        tableName= "r_eye_disease_code"; 
    }
    
    public void setInitData()
    {
        disease_code_id = "";
        group_id = "";
        code_begin = "";
        code_end = "";
    }
    public static String getStringTable()
    {
        return "r_eye_disease_code";
    }    
    public static String getStringFieldPKTable()
    {
        return "r_eye_disease_code_id";
    }
    public static String getStringFieldGroupID()
    {
        return "r_eye_group_id";
    }
    public static String getStringFieldBeginCode()
    {
        return "eye_disease_code_begin";
    }
    public static String getStringFieldEndCode()
    {
        return "eye_disease_code_end";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = this.getStringFieldPKTable();
        group_id = this.getStringFieldGroupID();
        code_begin = this.getStringFieldBeginCode();
        code_end = this.getStringFieldEndCode();
        return this;
    }    
}
