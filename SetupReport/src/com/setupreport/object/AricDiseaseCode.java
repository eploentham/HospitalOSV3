/*
 * AricDiseaseCode.java
 *
 * Created on 22 ตุลาคม 2548, 14:06 น.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.object;
import com.setupreport.usecase.connection.StandardObject;
/**
 *
 * @author tong(Padungrat)
 */
public class AricDiseaseCode   extends Persist implements StandardObject
{
    
    //public String id ;//r_aric_disease_code_id;
    /**key หลักของตาราง aric group id*/
    public String aricgroupid ;//r_aric_group_id;
    /** รหัส icd 10 เริ่มต้นของ กลุ่มนี้*/
    public String codebegin;//aric_disease_code_begin;
    /** รหัส icd 10 สิ้นสุดของ กลุ่มนี้*/
    public String codeend; //aric_disease_code_end;
  
    public AricDiseaseCode() {
        idTable ="812";
        tableName= "r_aric_disease_code"; 
    }
    
    public static String getStringTable()
    {
        return "r_aric_disease_code";  
    }
    
    public static String getStringFieldPKTable() 
    {
        return "r_aric_disease_code_id";    
    }
    
    public static String getStringFieldAricGroupID()
    {
        return "r_aric_group_id";
    }
    public static String getStringFieldCodeICD10Begin()
    {
        return "aric_disease_code_begin";
    }
    public static String getStringFieldCodeICD10End()
    {
        return "aric_disease_code_end";
    }
    public void setInitData() {
        aricgroupid = "";
        codebegin = "";
        codeend = "";
                
    }

    public Object setInitDataFieldName() {
        this.pk_table = getStringFieldPKTable();
        aricgroupid = getStringFieldAricGroupID();
        codebegin = getStringFieldCodeICD10Begin();
        codeend = getStringFieldCodeICD10End();
        
        return this;
    }
    
}
