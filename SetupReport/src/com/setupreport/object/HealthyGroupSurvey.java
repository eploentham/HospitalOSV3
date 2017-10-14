/*
 * HealthyGroupSurvey.java
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
public class HealthyGroupSurvey extends Persist
{
    
    /** Creates a new instance of HealthyGroupSurvey */
    
    public String r_healthy_group_survey_id;
    public String r_healthy_subgroup_id;
    public String b_health_survey_id;
    public String healthy_group_survey_description;
    
    public HealthyGroupSurvey()
    {
        idTable ="826";
        tableName = "r_healthy_group_survey"; 
    }

    public void setInitData()
    {
        r_healthy_group_survey_id = "";
        r_healthy_subgroup_id = "";
        b_health_survey_id = "";
        healthy_group_survey_description = "";
    }

    public Object setInitDataFieldName()
    {
        this.pk_table = "r_healthy_group_survey_id";
        r_healthy_subgroup_id = "r_healthy_subgroup_id";
        b_health_survey_id = "b_health_survey_id";
        healthy_group_survey_description = "healthy_group_survey_description";
        
        return this;
    }
    
}
