/*
 * RPNanNCDItemGroup.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2549, 12:07 ¹.
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
public class NCDItemGroup extends Persist implements StandardObject 
{
    public String r_ncd_item_group_id = "";
    public String ncd_item_group_number = "";
    public String ncd_item_group_description = "";
    
    public NCDItemGroup()
    {
        idTable = "844";
        tableName= "r_ncd_item_group";
    }

    public void setInitData()
    {
        r_ncd_item_group_id = "";
        ncd_item_group_number = "";
        ncd_item_group_description = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_ncd_item_group_id";
        ncd_item_group_number = "ncd_item_group_number";
        ncd_item_group_description = "ncd_item_group_description";
        return this;
    }
    
}
