/*
 * EpiMeaslesItem.java
 *
 * Created on 6 ¡’π“§¡ 2549, 17:20 π.
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
public class EpiMeaslesItem extends Persist
{
    
    /** Creates a new instance of EpiMeaslesItem */
    public String r_epi_measles_item_id;
    public String b_health_epi_group_id;
    public String epi_measles_item_description;
    
    public EpiMeaslesItem()
    {
        idTable ="823";
        tableName = "r_epi_measles_item"; 
    }

    public void setInitData()
    {
        r_epi_measles_item_id = "";
        b_health_epi_group_id = "";
        epi_measles_item_description = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_epi_measles_item_id";
        b_health_epi_group_id = "b_health_epi_group_id";
        epi_measles_item_description = "epi_measles_item_description";
        return this;
    }
    
}
