/*
 * BillingGroupItem.java
 *
 * Created on 17 ตุลาคม 2548, 16:58 น.
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
public class BillingGroupItem extends Persist implements StandardObject
{
    /**รหัสกลุ่มรายการใบเสร็จ*/
    public String billing_subgroup_id;
    /**ลำดับกลุ่มรายการใบเสร็จ*/
    public String subgroup_number;
    /**คำอธิบายรหัสกลุ่มรายการใบเสร็จ*/
    public String subgroup_description;
    /**กลุ่ม billing*/
    public String billing_group_id;

    /** Creates a new instance of BillingGroupItem */
    public BillingGroupItem()
    {
        idTable ="121";
        tableName= "b_item_billing_subgroup"; 
    }

    public void setInitData()
    {
        billing_subgroup_id = "";        
        subgroup_number = "";        
        subgroup_description = "";        
        billing_group_id = "";        
    }

    public static String getStringTable()
    {
        return "b_item_billing_subgroup";
    }
    
    public static String getStringFieldPKTable()
    {
        return "b_item_billing_subgroup_id";
    }
    
    public static String getStringFieldSubgroupNumber() 
    {
        return "item_billing_subgroup_number";    
    }
    
    public static String getStringFieldSubDescription() 
    {
        return "item_billing_subgroup_description";    
    }
    public static String getStringFieldBillingGroupID() 
    {
        return "f_item_billing_group_id";    
    }
    
    public Object setInitDataFieldName()
    {
        this.pk_table = getStringFieldPKTable();
        subgroup_number = getStringFieldSubgroupNumber();        
        subgroup_description = getStringFieldSubDescription();        
        billing_group_id = getStringFieldBillingGroupID(); 
        return this;
    }
    
}
