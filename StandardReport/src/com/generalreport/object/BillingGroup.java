/*
 * BillingGroup.java
 *
 * Created on 17 ตุลาคม 2548, 17:32 น.
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
public class BillingGroup extends Persist implements StandardObject
{
    /**รหัสของกลุ่มรายการคิดเงิน*/
    public String billing_group_id;
    /**คำอธิบายของรหัสของกลุ่มรายการคิดเงิน*/
    public String group_description;

    /** Creates a new instance of BillingGroup */
    public BillingGroup()
    {
        idTable ="120";
        tableName= "f_item_billing_group"; 
    }

    public void setInitData()
    {
       billing_group_id = "";
       group_description = "";
    }

    public static String getStringTable()
    {
        return "f_item_billing_group";  
    }
    
    public static String getStringFieldPKTable() 
    {
        return "f_item_billing_group_id";    
    }
   
    public static String getStringFieldGroupDescription() 
    {
        return "item_billing_group_description";    
    }
    
    public Object setInitDataFieldName()
    {
        this.pk_table = getStringFieldPKTable();
        group_description = getStringFieldGroupDescription();
        return this;
    }
    
}
