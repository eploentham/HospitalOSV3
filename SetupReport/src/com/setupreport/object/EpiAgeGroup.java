/*
 * EpiAgeGroup.java
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
public class EpiAgeGroup extends Persist
{
    
    /** Creates a new instance of EpiGroup */
    public String r_epi_age_group_id;
    public String epi_age_group_number;
    public String epi_age_group_description;
    public String epi_age_group_start;
    public String epi_age_group_end;
    public String r_epi_age_group_type_id;
    public String r_epi_group_clinic_id;
    
    public EpiAgeGroup()
    {
        idTable ="833";
        tableName = "r_epi_age_group"; 
    }

    public void setInitData()
    {
        r_epi_age_group_id = "";
        epi_age_group_number = "";
        epi_age_group_description = "";
        epi_age_group_start = "";
        epi_age_group_end = "";
        r_epi_age_group_type_id = "";
        r_epi_group_clinic_id = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_epi_age_group_id";
        epi_age_group_number = "epi_age_group_number";
        epi_age_group_description = "epi_age_group_description";
        epi_age_group_start = "epi_age_group_start";
        epi_age_group_end = "epi_age_group_end";
        r_epi_age_group_type_id = "r_epi_age_group_type_id";
        r_epi_group_clinic_id = "r_epi_group_clinic_id";
        return this;
    }
    
}
