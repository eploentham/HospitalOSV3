/*
 * DentalProtectItem.java
 *
 * Created on 6 ¡’π“§¡ 2549, 17:19 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.object;

/**
 *
 * @author Ojika
 */
public class DentalProtectItem extends Persist
{
    
    /** Creates a new instance of DentalProtectItem */
    public String r_dental_protect_item_id;
    public String b_item_id;
    public String dental_protect_item_number;
    public String dental_protect_item_common_name;
    
    public DentalProtectItem()
    {
        idTable ="824";
        tableName = "r_dental_protect_item"; 
    }

    public void setInitData()
    {
        r_dental_protect_item_id = "";
        b_item_id = "";
        dental_protect_item_number = "";
        dental_protect_item_common_name = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_dental_protect_item_id";
        b_item_id = "b_item_id";
        dental_protect_item_number = "dental_protect_item_number";
        dental_protect_item_common_name = "dental_protect_item_common_name";
        return this;
    }
    
}
