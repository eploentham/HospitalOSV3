/*
 * EyeDisease.java
 *
 * Created on 17 ���Ҥ� 2548, 11:42 �.
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
    /**���ʢͧ������ä��*/
    public String eye_group;
    /**���� ICD10 ������鹢ͧ�ä�Ңͧ������ä*/
    public String eye_start_code;
     /**���� ICD10 ����ش�ͧ�ä�Ңͧ������ä*/
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
