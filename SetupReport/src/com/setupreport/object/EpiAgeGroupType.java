/*
 * EpiAgeGroupType.java
 *
 * Created on 9 ¡’π“§¡ 2549, 11:56 π.
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
public class EpiAgeGroupType extends Persist
{
    
    /** Creates a new instance of EpiAgeGroupType */    
    
    public String r_epi_age_group_type_id;
    public String epi_age_group_type_number;
    public String epi_age_group_type_description;
    
    public EpiAgeGroupType()
    {
        idTable ="835";
        tableName = "r_epi_age_group_type"; 
    }

    public void setInitData()
    {
        r_epi_age_group_type_id = "";
        epi_age_group_type_number = "";
        epi_age_group_type_description = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_epi_age_group_type_id";
        epi_age_group_type_number = "epi_age_group_type_number";
        epi_age_group_type_description = "epi_age_group_type_description";
        return this;
    }
}
