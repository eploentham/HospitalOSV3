/*
 * EpiAgeGroupItem.java
 *
 * Created on 29 ¡’π“§¡ 2549, 17:20 π.
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
public class EpiAgeGroupItem extends Persist
{
    
    /** Creates a new instance of EpiGroupItem */
    public String r_epi_age_group_item_id;
    public String r_epi_age_group_id;
    public String r_epi_group_clinic_id;
    public String b_health_epi_group_id;
    public String epi_age_group_item_description;
    
    public EpiAgeGroupItem()
    {
        idTable ="834";
        tableName = "r_epi_age_group_item"; 
    }

    public void setInitData()
    {
        r_epi_age_group_item_id = "";
        r_epi_age_group_id = "";
        r_epi_group_clinic_id = "";
        b_health_epi_group_id = "";
        epi_age_group_item_description = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_epi_age_group_item_id";
        r_epi_age_group_id = "r_epi_age_group_id";
        r_epi_group_clinic_id = "r_epi_group_clinic_id";
        b_health_epi_group_id = "b_health_epi_group_id";
        epi_age_group_item_description = "epi_age_group_item_description";
        return this;
    }
}
