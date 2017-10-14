/*
 * HealthyGroup.java
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
public class HealthyGroup extends Persist
{
    
    /** Creates a new instance of HealthyGroup */    
    
    public String r_healthy_group_id;
    public String healthy_group_number;
    public String healthy_group_description;
    
    public HealthyGroup()
    {
        idTable ="825";
        tableName = "r_healthy_group"; 
    }

    public void setInitData()
    {
        r_healthy_group_id = "";
        healthy_group_number = "";
        healthy_group_description = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_healthy_group_id";
        healthy_group_number = "healthy_group_number";
        healthy_group_description = "healthy_group_description";
        return this;
    }
}
