/*
 * Uom.java
 *
 * Created on 17 ตุลาคม 2548, 13:34 น.
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
public class Uom extends Persist implements StandardObject
{
    /**รหัสหน่วยของการใช้ยา*/
    public String b_item_drug_uom_id;
    /**ลำดับหน่วยของการใช้ยา*/
    public String item_drug_uom_number;
    /**คำอธิบายรหัสหน่วยของการใช้ยา*/
    public String item_drug_uom_description;
    /**แสดงหรือไม่แสดง*/
    public String item_drug_uom_active;
    
    /** Creates a new instance of Uom */
    public Uom()
    {
        idTable ="252";
        tableName= "b_item_drug_uom";  
    }
    public void setInitData()
    {
        b_item_drug_uom_id = "";
        item_drug_uom_number = "";
        item_drug_uom_description = "";
        item_drug_uom_active = "";        
    }
    
    public static String getStringTable()
    {
        return "b_item_drug_uom";
    }
    
    public static String getStringFieldPKTable()
    {
        return "b_item_drug_uom_id";
    }
    
    public static String getStringFieldUomNumber()
    {
        return "item_drug_uom_number";
    }
    
    public static String getStringFieldUomDescription()
    {
        return "item_drug_uom_description";
    }
     public static String getStringFieldUomActive()
    {
        return "item_drug_uom_active";
    }
        
    public Object setInitDataFieldName()
    {
        this.pk_table = getStringFieldPKTable();
        item_drug_uom_number = getStringFieldUomNumber();
        item_drug_uom_description = getStringFieldUomDescription();
        item_drug_uom_active = getStringFieldUomActive();
        return this;
    }
    
}
