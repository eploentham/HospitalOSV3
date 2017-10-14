/*
 * EpiGroup.java
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
public class EpiGroup extends Persist
{
    
    /** Creates a new instance of EpiGroup */
    public String r_epi_group_id;
    public String epi_group_number;
    public String epi_group_description;
    
    public EpiGroup()
    {
        idTable ="821";
        tableName = "r_epi_group"; 
    }

    public void setInitData()
    {
        r_epi_group_id = "";
        epi_group_number = "";
        epi_group_description = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_epi_group_id";
        epi_group_number = "epi_group_number";
        epi_group_description = "epi_group_description";
        return this;
    }
    
}
