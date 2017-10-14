/*
 * RPNanOPDItem.java
 *
 * Created on 20 ÁÔ¶Ø¹ÒÂ¹ 2549, 12:02 ¹.
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
public class OPDItem extends Persist implements StandardObject 
{
    
    public String r_opd_item_id = "";
    public String b_item_id = "";
    public String opd_item_number = "";
    public String opd_item_common_name = "";
    public String opd_item_description = "";
    
    public OPDItem()
    {
        idTable = "839";
        tableName= "r_opd_item";
    }
    
    public void setInitData()
    {
        r_opd_item_id = "";
        b_item_id = "";
        opd_item_number = "";
        opd_item_common_name = "";
        opd_item_description = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_opd_item_id";
        b_item_id = "b_item_id";
        opd_item_number = "opd_item_number";
        opd_item_common_name = "opd_item_common_name";
        opd_item_description = "opd_item_description";
        return this;
    }
}
