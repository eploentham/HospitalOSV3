/*
 * Rp115Group4Item.java
 *
 * Created on 14 ¡’π“§¡ 2549, 18:10 π.
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.setupreport.object;

import com.setupreport.usecase.connection.StandardObject;
/**
 *
 * @author pu
 */
public class Rp115Group4Item extends Persist implements StandardObject
{
    public String r_rp11_item_id;
    public String r_rp11_disease_id;
    public String b_item_id;
    public String rp11_item_number;
    public String rp11_item_description;

    /** Creates a new instance of Rp115Group4Item */
    public Rp115Group4Item()
    {
         idTable = "829";
         tableName = "r_rp11_item";
    }

    public void setInitData()
    {
        r_rp11_item_id = "";
        r_rp11_disease_id = "";
        b_item_id = "";
        rp11_item_number = "";
        rp11_item_description = "";
    }
    
    public Object setInitDataFieldName()
    {
        this.pk_table = "r_rp11_item_id";
        r_rp11_disease_id = "r_rp11_disease_id";
        b_item_id = "b_item_id";
        rp11_item_number = "rp11_item_number";
        rp11_item_description = "rp11_item_description";      
        return this;
    }


}
