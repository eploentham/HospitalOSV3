/*
 * EyeDisease.java
 *
 * Created on 17 ตุลาคม 2548, 11:42 น.
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
public class EyeDisease  extends Persist implements StandardObject {
    
    //public String r_eye_disease_code_id;
    /**รหัสของกลุ่มโรคตา*/
    public String eye_group;
    /**รหัส ICD10 เริ่มต้นของโรคตาของกลุ่มโรค*/
    public String eye_start_code;
     /**รหัส ICD10 สิ้นสุดของโรคตาของกลุ่มโรค*/
    public String eye_end_code ;
    public EyeDisease() {
        idTable ="";
        tableName= "r_eye_disease_code"; 
    }

    public void setInitData() {
        eye_group = "";
        eye_start_code = "";
        eye_end_code = "";
    }
    public static String getStringTable()
    {
        return "r_eye_disease_code";  
    }
    public static String getStringFieldPKTable() 
    {
        return "r_eye_disease_code_id";    
    }
    public static String getStringFieldEyeGroupID() 
    {
        return "r_eye_group_id";    
    }
    public static String getStringFieldEyeDiseaseCodeStart() 
    {
        return "eye_disease_code_start_code";    
    }
    public static String getStringFieldEyeDiseaseCodeEnd() 
    {
        return "eye_disease_code_end_code";    
    }
    public Object setInitDataFieldName() {
        this.pk_table = getStringFieldPKTable();
        eye_group = getStringFieldEyeGroupID();
        eye_start_code = getStringFieldEyeDiseaseCodeStart();
        eye_end_code = getStringFieldEyeDiseaseCodeEnd();
        return this;
    }
    
}
