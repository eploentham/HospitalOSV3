/*
 * HealthySubgroup.java
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
public class HealthySubgroup extends Persist
{
    
    /** Creates a new instance of HealthyGroup */    
    
    public String r_healthy_subgroup_id;
    public String r_healthy_group_id;
    public String healthy_subgroup_number;
    public String healthy_subgroup_description;
    
    public HealthySubgroup()
    {
        idTable ="827";
        tableName = "r_healthy_subgroup"; 
    }

    public void setInitData()
    {
        r_healthy_subgroup_id = "";
        r_healthy_group_id = "";
        healthy_subgroup_number = "";
        healthy_subgroup_description = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_healthy_subgroup_id";
        r_healthy_group_id = "r_healthy_group_id";
        healthy_subgroup_number = "healthy_subgroup_number";
        healthy_subgroup_description = "healthy_subgroup_description";
        return this;
    }
}
