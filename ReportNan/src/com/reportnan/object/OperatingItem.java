/*
 * OperatingItem.java
 *
 * Created on 21 ÁÔ¶Ø¹ÒÂ¹ 2549, 14:58 ¹.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.reportnan.object;

import com.reportnan.usecase.StandardObject;
/**
 *
 * @author ojika
 */
public class OperatingItem extends Persist implements StandardObject 
{
    public String r_operating_item_id = "";
    public String b_item_id = "";
    public String operating_item_number = "";
    public String operating_item_description = "";
    public String operating_item_common_name = "";
    
    public OperatingItem()
    {
        idTable = "841";
        tableName= "r_operating_item";
    }

    public void setInitData()
    {
        r_operating_item_id = "";
        b_item_id = "";
        operating_item_number = "";
        operating_item_description = "";
        operating_item_common_name = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_operating_item_id";
        b_item_id = "b_item_id";
        operating_item_number = "operating_item_number";
        operating_item_description = "operating_item_description";
        operating_item_common_name = "operating_item_common_name";
        return this;
    }
    
}
