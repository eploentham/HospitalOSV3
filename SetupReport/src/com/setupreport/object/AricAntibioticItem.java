/*
 * AricAntibioticItem.java
 *
 * Created on 28 ตุลาคม 2548, 9:41 น.
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
public class AricAntibioticItem extends Persist implements StandardObject{
    
    //public String id ; /r_aric_antibiotic_id
    /**รหัสหลักของตาราง Item*/
    public String item_id ;//b_item_id
    /**รหัส number ของ Item*/
    public String antibiotic_code ;//aric_antibiotic_code
    /**ชื่อ สามัญของยา*/
    public String common_name ; //aric_antibiotic_common_name
    public AricAntibioticItem() {
        idTable ="811";        
        tableName= "r_aric_antibiotic";
    }

    public static String getStringTable()
    {
        return "r_aric_antibiotic";
    }   
    public static String getStringFieldPKTable()
    {
        return "r_aric_antibiotic_id";
    }
    public static String getStringFieldItemID()
    {
        return "b_item_id";
    }
    public static String getStringFieldAntibioticCode()
    {
        return "aric_antibiotic_code";
    }
    public static String getStringFieldCommonName()
    {
        return "aric_antibiotic_common_name";
    }
    public void setInitData() {
        item_id = "";
        antibiotic_code = "";
        common_name = "";
    }

    public Object setInitDataFieldName() {
        
        this.pk_table =  this.getStringFieldPKTable();
        item_id = getStringFieldItemID();
        antibiotic_code = getStringFieldAntibioticCode();
        common_name = getStringFieldCommonName();
        
        return this;
    }
    
}
