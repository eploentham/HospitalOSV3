/*
 * EpiTTItem.java
 *
 * Created on 28 ¡’π“§¡ 2549, 17:20 π.
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
public class EpiTTItem extends Persist
{
    
    /** Creates a new instance of EpiTTItem */
    public String r_epi_TT_item_id;
    public String b_health_epi_group_id;
    public String epi_TT_item_description;
    
    public EpiTTItem()
    {
        idTable ="826";
        tableName = "r_epi_TT_item"; 
    }

    public void setInitData()
    {
        r_epi_TT_item_id = "";
        b_health_epi_group_id = "";
        epi_TT_item_description = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_epi_TT_item_id";
        b_health_epi_group_id = "b_health_epi_group_id";
        epi_TT_item_description = "epi_TT_item_description";
        return this;
    }
    
}
