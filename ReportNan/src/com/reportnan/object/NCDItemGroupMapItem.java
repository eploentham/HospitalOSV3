/*
 * RPNanNCDItemGroupMapItem.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2549, 12:08 ¹.
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
public class NCDItemGroupMapItem extends Persist implements StandardObject 
{
    public String r_ncd_item_group_map_item_id = "";
    public String r_ncd_item_group_id = "";
    public String b_item_id = "";
    public String ncd_item_group_map_item_number = "";
    public String ncd_item_group_map_item_description = "";
    
    public NCDItemGroupMapItem()
    {
        idTable = "839";
        tableName= "r_ncd_item_group_map_item";
    }

    public void setInitData()
    {
        r_ncd_item_group_map_item_id = "";
        r_ncd_item_group_id = "";
        b_item_id = "";
        ncd_item_group_map_item_number = "";
        ncd_item_group_map_item_description = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_ncd_item_group_map_item_id";
        r_ncd_item_group_id = "r_ncd_item_group_id";
        b_item_id = "b_item_id";
        ncd_item_group_map_item_number = "ncd_item_group_map_item_number";
        ncd_item_group_map_item_description = "ncd_item_group_map_item_description";
        return this;
    }
    
}
